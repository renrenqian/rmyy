/**
 * OperateLogAspect.java
 */
package com.kevin.common.aspect;

import java.util.Date;

import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;

import com.kevin.common.pojo.system.OperationLog;
import com.kevin.common.service.system.IOperationLogService;
import com.kevin.common.util.ServletActionContextUtil;

@Aspect
public class OperateLogAspect {
    private Logger logger = Logger.getLogger(OperateLogAspect.class.getName());
    private IOperationLogService operationLogService;
    public final void setOperationLogService(
            IOperationLogService operationLogService) {
        this.operationLogService = operationLogService;
    }
    @AfterReturning("execution(public * com.kevin.common.service..*(..))"
            + " and not execution(* com.kevin.common.service..*.search*(..))"
            + " and not execution(* com.kevin.common.service..*.find*(..))"
            + " and not execution(* com.kevin.common.service..*.get*(..))"
            + " and not execution(* com.kevin.common.service..*.set*(..))"
            + " and not execution(* com.kevin.common.service..*.list*(..))"
            + " and not execution(* com.kevin.common.service..*.query*(..))"
            + " and not @annotation(com.kevin.common.annotation.NoLog)"
            + " and not @target(com.kevin.common.annotation.NoLog)")
    public void after(JoinPoint jp) {
        addLog(jp,1);
    }
    @AfterThrowing(pointcut="execution(public * com.kevin.common.service..*(..))"
            + " and not execution(* com.kevin.common.service..*.search*(..))"
            + " and not execution(* com.kevin.common.service..*.find*(..))"
            + " and not execution(* com.kevin.common.service..*.get*(..))"
            + " and not execution(* com.kevin.common.service..*.set*(..))"
            + " and not execution(* com.kevin.common.service..*.list*(..))"
            + " and not @annotation(com.kevin.common.annotation.NoLog)"
            + " and not @target(com.kevin.common.annotation.NoLog)",throwing="e")
    public void whenExceptionLog(JoinPoint jp,Exception e){
        addLog(jp,0);
        if(e.getMessage()==null){
            e.printStackTrace();
        }else{
            logger.error("异常信息:" + e.getMessage());
        }
    }
    private void addLog(JoinPoint jp,Integer state){
        OperationLog log = new OperationLog();
        log.setUserId(ServletActionContextUtil.getCurrentUserId());
        log.setTime(new Date());
        log.setIp(ServletActionContextUtil.getRemoteIP());
        String actionName = ServletActionContextUtil.getActionName();
        if(logger.isDebugEnabled()){
            logger.debug("-----------------log aspect method name:" + actionName + "-------------------------");
            logger.debug("-----------------log aspect action name:" + actionName + "-------------------------");
            logger.debug("-----------------log aspect params:" + log.getParams() + "-------------------------");
        }
        log.setAction(actionName);
        log.setState(state);
        try {
            if(log.getUserId() == null){//得不到用户id不记录日志
                return;
            }
            if(log.getIp() == null){//得不到IP不记录日志
                return;
            }
            operationLogService.save(log);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}