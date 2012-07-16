/**
 * ServletActionContextUtil.java
 */
package com.kevin.common.util;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Map;
import java.util.Set;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.kevin.common.constant.SystemConstant;
@SuppressWarnings("unchecked")
public class ServletActionContextUtil {
     private static transient WebApplicationContext context;
    @SuppressWarnings("rawtypes")
    public static String getReferer() {
        HttpServletRequest request = ServletActionContext.getRequest();
        String referer = request.getHeader("referer");
        Map paramMap = request.getParameterMap();
        Set<Map.Entry> set = (Set<Map.Entry>) paramMap.entrySet();
        StringBuilder params = new StringBuilder();
        for (Map.Entry param : set) {
            String value;
            try {
                value = URLEncoder.encode(request.getParameter(param.getKey()
                        .toString()), "utf-8");
                params.append(param.getKey()).append("=").append(value).append(
                        "&");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
        if (referer != null) {
            return referer + "?" + params.substring(0, params.length() - 1);
        }
        return null;
    }
    public static String getRemoteIP(){
        HttpServletRequest request = ServletActionContext.getRequest();
        if(request == null){
            return null;
        }
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        return ip;
    }
    public static String getRealPath(String path){
        HttpServletRequest request = ServletActionContext.getRequest();
        return request.getSession().getServletContext().getRealPath(path);
    }
    public static String getActionName(){
        HttpServletRequest request = ServletActionContext.getRequest();
        //"/AdInfo/system/changeUserPwd.action" return "changeUserPwd"
        String requestURI = request.getRequestURI();
        return requestURI.substring(requestURI.lastIndexOf("/") + 1,requestURI.indexOf("."));
    }
    public static Integer getCurrentUserId(){
        HttpServletRequest request = ServletActionContext.getRequest();
        if(request == null){
            return null;
        }
        HttpSession session = request.getSession(false);
        if (session == null){
            return null;
        }
        Object userId = session.getAttribute(SystemConstant.CURRENT_LOGIN_USER_ID);
        if (userId == null){
            return null;
        }else{
            return Integer.parseInt(userId.toString());
        }
    }
    public static HttpServletRequest getRequest(){
        return ServletActionContext.getRequest();
    }
    public static String getCurrentUserName(){
        HttpServletRequest request = ServletActionContext.getRequest();
        if(request == null){
            return null;
        }
        HttpSession session = request.getSession(false);
        if (session == null){
            return null;
        }
        Object userName = session.getAttribute(SystemConstant.CURRENT_LOGIN_USER_NAME);
        return userName == null ? "":userName.toString();
    }
    public static Object getBean(String id){
        if(context == null){
            context = WebApplicationContextUtils.getWebApplicationContext(ServletActionContext.getServletContext());
        }
        return context.getBean(id);
    }
    public static Object getBean(String id,ServletContext sc){
        if(context == null){
            context = WebApplicationContextUtils.getWebApplicationContext(sc);
        }
        return context.getBean(id);
    }
}
