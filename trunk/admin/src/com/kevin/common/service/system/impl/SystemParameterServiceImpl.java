/**
 * 
 */
package com.kevin.common.service.system.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kevin.common.constant.SystemConstant;
import com.kevin.common.dao.system.ISystemParameterDAO;
import com.kevin.common.exception.BaseSqlMapException;
import com.kevin.common.exception.CommonServiceException;
import com.kevin.common.pojo.system.SystemParameter;
import com.kevin.common.service.AbstractBaseService;
import com.kevin.common.service.system.ISystemParameterService;
import com.kevin.common.util.SystemParameterUtil;

/**
 * @author wj
 * 
 */
@Service("sysParameterService")
public class SystemParameterServiceImpl extends
        AbstractBaseService<SystemParameter> implements ISystemParameterService {

    private ISystemParameterDAO sysParameterDAO;

    /**
     * @param sysParameterDAO
     *            the sysParameterDAO to set
     */
    @Autowired
    public final void setSysParameterDAO(ISystemParameterDAO sysParameterDAO) {
        setAbsBaseDao(sysParameterDAO);
        this.sysParameterDAO = sysParameterDAO;
    }

    @Override
    public int update(SystemParameter sp) throws CommonServiceException {
        if(null == sp || (null == sp.getSpValue()) || (null == sp.getSpCode())){
            throw new CommonServiceException("修改系统参数，参数丢失");
        }
        int resultCode=-1;
        //String oldValue = CmcpseConstant.SYS_PARAM_MAP.get(sp.getSpCode());
        try {
            resultCode = sysParameterDAO.update(sp);
            if(resultCode>0){
                SystemParameterUtil.freshParameters(sp.getSpCode(), sp.getSpValue());
//                if(sp.getSpCode().equals("REDIS_DB_IP")){
//                    EncodingConstants.REDIS_SERVER_IP=sp.getSpValue(); 
//                }
//                if(sp.getSpCode().equals("REDIS_DB_PORT")){
//                    EncodingConstants.REDIS_SERVER_PORT = Integer.parseInt(sp.getSpValue());
//                }
//                if(sp.getSpCode().equals("CONCURR_TASK_MAX"))
//                    EncodingConstants.MAX_TASK_NO = Integer.parseInt(sp.getSpValue());
//                
//                // SchedularConstatnt value
//                if(sp.getSpCode().equals("ENCODING_SYNC"))// 
//                    SchedularConstatnt.SYNC_ENCODINGINFO = Boolean.valueOf(sp.getSpValue());
//                if(sp.getSpCode().equals("TASK_SYNC"))// 
//                    SchedularConstatnt.DISPATCH_ENCODINGTASK = Boolean.valueOf(sp.getSpValue());
//                if(sp.getSpCode().equals("SYNC_SOURCEINFO"))// 
//                    SchedularConstatnt.SYNC_SOURCEINFO = Boolean.valueOf(sp.getSpValue());
//                if(sp.getSpCode().equals("STATIC_SYNC"))// 
//                    SchedularConstatnt.SYNC_STATISTICS = Boolean.valueOf(sp.getSpValue());
//                // cache to the map
//                CmcpseConstant.SYS_PARAM_MAP.put(sp.getSpCode(),sp.getSpValue());
            }
        } catch (BaseSqlMapException e) {
            /*
            CmcpseConstant.SYS_PARAM_MAP.put(sp.getSpCode(),oldValue);
            if(sp.getSpCode().equals("REDIS_DB_IP")){
                EncodingConstants.REDIS_SERVER_IP=sp.getSpValue(); 
            }
            if(sp.getSpCode().equals("REDIS_DB_PORT")){
                EncodingConstants.REDIS_SERVER_PORT = Integer.parseInt(oldValue);
            }
            */
            throw new CommonServiceException("修改系统参数错误:"+e.getMessage());
        }
        return resultCode;
    }

    @Override
    public String getParamValueByParamCode(String code)
            throws CommonServiceException {
        String value = SystemConstant.SYS_PARAM_MAP.get(code);
        if(value==null){
            SystemParameter sp = new SystemParameter();
            sp.setSpCode(code);
            try {
                sp = sysParameterDAO.search(sp);
                if(sp!=null){
                    return sp.getSpValue();
                }else{
                    return null; 
                }
            } catch (BaseSqlMapException e) {
                return null; 
            }
        }
        return value;
    }

}
