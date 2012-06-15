/**
 * SystemInfoServiceImpl.java
 */
package com.kevin.common.service.system.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kevin.common.annotation.NoLog;
import com.kevin.common.dao.system.ISystemInfoDAO;
import com.kevin.common.exception.BaseSqlMapException;
import com.kevin.common.exception.CommonServiceException;
import com.kevin.common.pojo.system.SystemInfo;
import com.kevin.common.service.AbstractBaseService;
import com.kevin.common.service.system.ISystemInfoService;
/**
 * 系统参数业务类
 * @author jiangyaniu
 *
 */
@NoLog
@Service("systemInfoService")
public class SystemInfoServiceImpl extends AbstractBaseService<SystemInfo>
        implements ISystemInfoService {
    private ISystemInfoDAO sysDAO;

    @Autowired
    public void setSysDAO(ISystemInfoDAO sysDAO) {
        setAbsBaseDao(sysDAO);
        this.sysDAO = sysDAO;
    }

    @Override
    public SystemInfo getSystemInfo() throws CommonServiceException {
        List<SystemInfo> sysList;
        try {
            sysList = sysDAO.listAll();
            if(sysList!=null && sysList.size()>0){
                return sysList.get(0);
            }
        } catch (BaseSqlMapException e) {
            throw new CommonServiceException(e.getMessage());
        }
        return null;
    }
    
}
