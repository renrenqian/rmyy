/**
 * SessionExpireListener.java
 */
package com.kevin.common.listener;

import java.util.Date;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import org.apache.log4j.Logger;

import com.kevin.common.constant.SystemConstant;
import com.kevin.common.exception.CommonServiceException;
import com.kevin.common.pojo.system.OperationLog;
import com.kevin.common.pojo.system.SystemParameter;
import com.kevin.common.service.system.IOperationLogService;
import com.kevin.common.service.system.ISystemParameterService;
import com.kevin.common.service.system.IUserInfoService;
import com.kevin.common.util.ServletActionContextUtil;
import com.kevin.common.util.SystemParameterUtil;
 
public class SessionExpireListener implements HttpSessionListener,
        HttpSessionAttributeListener,ServletContextListener {
    private Logger logger = Logger.getLogger(SessionExpireListener.class.getClass());
    private IOperationLogService logService;
    private ISystemParameterService sysInfoService;

    public SessionExpireListener() {
        super();
    }
    @Override
    public void sessionCreated(HttpSessionEvent se) {
        logger.debug("**************************:会话已创建:"+ se.getSession().getId());
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
        logger.debug("**************************:会话已过期:"+ se.getSession().getId());
    }

    @Override
    public void attributeAdded(HttpSessionBindingEvent se) {
        if (se.getName().equals(SystemConstant.CURRENT_LOGIN_USER_ID)) {
            Object value = se.getValue();
            // 在线用户管理，也在此?
            // 登入日志 start
            OperationLog log = new OperationLog();
            log.setTime(new Date());
            log.setUserId((Integer)value);
            log.setState(1);
            log.setIp(ServletActionContextUtil.getRemoteIP());
            log.setAction("login");
            getLogService(se.getSession().getServletContext());
            try {
                logService.save(log);
            } catch (Exception e) {
                logger.error("新增用户登陆日志异常:" + e.getMessage());
                e.printStackTrace();
            }
            // 登入日志 start end
        }
    }

    @Override
    public void attributeRemoved(HttpSessionBindingEvent se) {
        if(se.getName().equals(SystemConstant.CURRENT_LOGIN_USER_ID)){
            Object value = se.getValue();
            Integer userId = value != null ? Integer.parseInt(value.toString()) : -1;
            getLogService(se.getSession().getServletContext());
            // 登出日志 start
            OperationLog log = new OperationLog();
            log.setTime(new Date());
            log.setUserId(userId);
            log.setState(1);
            try{
                String ip = ServletActionContextUtil.getRemoteIP();
                log.setIp(ip);
            }catch(Exception e){
                //会话自动过期（非用户主动注销会产生异常）
            }
            log.setAction("logout");
            try {
                if(log.getIp()!=null){
                    logService.save(log);
                }
            } catch (Exception e) {
                logger.error("新增用户注销日志异常:" + e.getMessage());
                //e.printStackTrace();
            }
            // 登出日志 end
        }
        if (se.getName().equals(SystemConstant.CURRENT_LOGIN_USER_NAME)) {
            Object value = se.getValue();
            String userName = value != null ? value.toString() : "";
            IUserInfoService.PRIVILEGEMAP.remove("$"+userName+"$");// 移除用户的缓存数据
            IUserInfoService.ONLINEUSERMAP.remove(se.getSession().getId());
            logger.debug("**************************:用户(" + userName + ")注销;会话ID:" + se.getSession().getId());
        }
    }

    @Override
    public void attributeReplaced(HttpSessionBindingEvent se) {
        // Nothing to do
    }
    private void getLogService(ServletContext servletContext) {
        if (logService == null) {
            logService = (IOperationLogService) ServletActionContextUtil.getBean("operationLogService");
        }
    }
    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        //
    }
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        sysInfoService = (ISystemParameterService) ServletActionContextUtil.getBean("sysParameterService",sce.getServletContext());

        try {
            List<SystemParameter> sysParaList = sysInfoService.listAll();

            if(sysParaList!=null){
                for(SystemParameter sp:sysParaList){
                    SystemParameterUtil.freshParameters(sp.getSpCode(), sp.getSpValue());
//                    SystemConstant.SYS_PARAM_MAP.put(sp.getSpCode(),sp.getSpValue());
//                    if(sp.getSpCode().equals("REDIS_DB_IP")){
//                        EncodingConstants.REDIS_SERVER_IP=sp.getSpValue();
//                    }
//                    if(sp.getSpCode().equals("REDIS_DB_PORT")){
//                        EncodingConstants.REDIS_SERVER_PORT = Integer.parseInt(sp.getSpValue());
//                    }
//                    if(sp.getSpCode().equals("SCAN_PATH")){
//                        EncodingConstants.SCAN_PATH = sp.getSpValue();
//                    }
//                    if(sp.getSpCode().equals("SERVICE_ENCODER")){
//                        Constants.SYSTEM_ENCODING = sp.getSpValue();
//                    }
//                    if(sp.getSpCode().equals("CONCURR_TASK_MAX")){
//                        EncodingConstants.MAX_TASK_NO = Integer.parseInt(sp.getSpValue());
//                    }
//                    if(sp.getSpCode().equals("PREVIEW_PORT")){
//                        EncodingConstants.PREVIEW_PORT = Integer.valueOf(sp.getSpValue());
//                    }
//                    if(sp.getSpCode().equals("PREVIEW_IP")){
//                        EncodingConstants.PREVIEW_IP = sp.getSpValue();
//                        EncodingConstants.PREVIEW_URL = "http://" + EncodingConstants.PREVIEW_IP + ":" + EncodingConstants.PREVIEW_PORT + "/";
//                        EncodingConstants.DEFAULT_POSTER_URL = EncodingConstants.PREVIEW_URL + EncodingConstants.DEFAULT_POSTER_URL ;
//                    }
//                    if(sp.getSpCode().equals("DATA_SRC_ROOT")){
//                        String _storeRoot = sp.getSpCode();
//                        if(!_storeRoot.endsWith("/"))
//                            _storeRoot.concat("/");
//                        EncodingConstants.DATA_SRC_ROOT = _storeRoot;
//                    }
                }
            } 
        } catch (CommonServiceException e) {
            //缓存数据
            logger.error("查询系统参数数据错误:"+e.getMessage());
        }
    }
}