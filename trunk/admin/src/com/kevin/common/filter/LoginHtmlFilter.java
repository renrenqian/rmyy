/**
 * LoginHtmlFilter.java
 */
package com.kevin.common.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.kevin.common.constant.SystemConstant;

public class LoginHtmlFilter implements Filter {

    private Logger logger = Logger.getLogger(LoginHtmlFilter.class.getClass());
    @Override
    public void destroy() {
        //nothing to do
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response,
            FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest)request;
        HttpServletResponse httpResponse = (HttpServletResponse)response;
        HttpSession session = httpRequest.getSession(false);
        String requestURL = httpRequest.getRequestURI();
        //System.out.println("文件路径:"+requestURL);
        logger.warn("User try to acess the URL:" + requestURL);
        if(requestURL.endsWith("index.html") || requestURL.lastIndexOf(".js")>-1){
            filterChain.doFilter(httpRequest, httpResponse);
            return;
        }
        Object userId = null;
        if(session != null){
            userId = session.getAttribute(SystemConstant.CURRENT_LOGIN_USER_ID);
        }
        if(userId == null){//未登录跳动登陆页面
            httpResponse.sendRedirect(httpRequest.getContextPath()+"/nologin.htm");
        }else{
            filterChain.doFilter(httpRequest, httpResponse);
        }
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        //nothing to do
    }

}