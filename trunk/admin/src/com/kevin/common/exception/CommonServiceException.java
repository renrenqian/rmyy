/**
 * CmcpseServiceException.java
 */
package com.kevin.common.exception;
 
public class CommonServiceException extends Exception{

    /**
     * 
     */
    private static final long serialVersionUID = -6033102635917529849L;

    /**
     * 
     */
    public CommonServiceException() {
    }

    /**
     * @param message
     */
    public CommonServiceException(String message) {
        super(message);
    }

    /**
     * @param cause
     */
    public CommonServiceException(Throwable cause) {
        super(cause);
    }

    /**
     * @param message
     * @param cause
     */
    public CommonServiceException(String message, Throwable cause) {
        super(message, cause);
    }
}
