/**
 * BaseSqlMapException.java
 */
package com.kevin.common.exception;
 
public final class BaseSqlMapException extends Exception {
 
    /**
     * 
     */
    private static final long serialVersionUID = -6033102635917529849L;

    /**
     * 
     */
    public BaseSqlMapException() {
    }

    /**
     * @param message
     */
    public BaseSqlMapException(String message) {
        super(message);
    }

    /**
     * @param cause
     */
    public BaseSqlMapException(Throwable cause) {
        super(cause);
    }

    /**
     * @param message
     * @param cause
     */
    public BaseSqlMapException(String message, Throwable cause) {
        super(message, cause);
    }

}
