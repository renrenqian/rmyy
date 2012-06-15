/**
 * AbstractBaseAction.java
 */
package com.kevin.common.action;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;

import com.googlecode.jsonplugin.annotations.JSON;

/**
 * @author chiang
 * @since 2011-11-16 13:00
 * @version 1.0
 */
public abstract class AbstractBaseAction{

    private String message;
    protected Integer resultCode = 1;
    protected Logger logger = Logger.getLogger(AbstractBaseAction.class.getName());
    /**
     * @return the resultCode
     */
    public final Integer getResultCode() {
        return resultCode;
    }

    /**
     * @param resultCode the resultCode to set
     */
    public final void setResultCode(Integer resultCode) {
        this.resultCode = resultCode;
    }
    /**
     * @return the message
     */
    public final String getMessage() {
        return message;
    }
    /**
     * @param message
     *            the message to set
     */
    public final void setMessage(String message) {
        this.message = message;
    }

    /**
     * Base action get the request for each request.
     * 
     * @return HttpServletRequest
     */
    @JSON(serialize = false)
    public HttpServletRequest getRequest() {
        return ServletActionContext.getRequest();
    }
}