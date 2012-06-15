/**
 * PrivilegeInterceptor.java
 */
package com.kevin.common.interceptor;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;

import com.kevin.common.action.AbstractBaseAction;
import com.kevin.common.constant.SystemConstant;
import com.kevin.common.service.system.IUserInfoService;
import com.kevin.common.service.system.OnlineUser;
import com.kevin.common.util.ServletActionContextUtil;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.MethodFilterInterceptor;


public class PrivilegeInterceptor extends MethodFilterInterceptor{
    /**
     * 
     */
    private static final long serialVersionUID = -7432933121020448426L;
    private static Logger logger = Logger.getLogger(PrivilegeInterceptor.class.getClass());
    private transient IUserInfoService userService;
    public PrivilegeInterceptor() {
        super();
        if(logger.isDebugEnabled()){
           logger.debug("---------------------创建权限拦截器:"+com.kevin.common.util.DateUtils.formatNow()+"-------------------------");
        }
    }
    @Override
    protected String doIntercept(ActionInvocation actioninvocation)
            throws Exception {
        if(userService == null){//获得业务逻辑bean
            userService = (IUserInfoService)ServletActionContextUtil.getBean("userInfoService");
        }
        String actionname = actioninvocation.getProxy().getActionName();
        AbstractBaseAction myaction =    (AbstractBaseAction)actioninvocation.getAction();//被拦截的Action对象
        if(logger.isDebugEnabled()){
            logger.debug("******************************Action Class:"+myaction.getClass().getSimpleName() +" action name:"+actionname+"  menthod:"+ actioninvocation.getProxy().getMethod()+"*******************************");
        }
        //判断是否登入开始
        Integer userId = ServletActionContextUtil.getCurrentUserId();
        if(userId == null){
            myaction.setResultCode(SystemConstant.NO_LOGIN);
            myaction.setMessage("用户未登录或会话已过期,请重新登陆");
            return Action.SUCCESS;
        }//判断是否登入结束
        
        
        //判断是否会话超时开始
        HttpSession session = ServletActionContext.getRequest().getSession(false);
        if(session == null){
            myaction.setResultCode(SystemConstant.SESSION_TIMEOUT);
            myaction.setMessage("会话已过期,请重新登陆");//408 time out
            return Action.SUCCESS;
        }
        OnlineUser onlineUser = IUserInfoService.ONLINEUSERMAP.get(session.getId());
        if(onlineUser == null || onlineUser.getSession() == null || onlineUser.getSession() != session){
            myaction.setResultCode(SystemConstant.SESSION_TIMEOUT);
            myaction.setMessage("会话已过期,请重新登陆");//408 time out
            return Action.SUCCESS;
        }//判断是否会话超时结束
        
        //判断用户是否拥有权限
        if(userService.isHavePri(userId, actionname)){//有权
            if(logger.isDebugEnabled()){
                logger.debug("***************************有权访问("+actionname+")****************************");
            }
            return actioninvocation.invoke();
        }else{//无权
            if(logger.isDebugEnabled()){
                logger.debug("***************************无权访问("+actionname+")****************************");
            }
            myaction.setResultCode(SystemConstant.NO_PRIVILEGE);
            myaction.setMessage("无权操作，请管理员分配权限");
            return Action.SUCCESS;
        }
        //return actioninvocation.invoke();
    }
}