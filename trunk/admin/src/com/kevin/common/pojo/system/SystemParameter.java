/**
 * SystemInfo.java
 */
package com.kevin.common.pojo.system;

import java.io.Serializable;

/**
 * 系统属性，参数
 * 
 * @author jiangyaniu
 * @since 2011-12-08 18:31
 */
public class SystemParameter implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = -8055854973930117944L;
    private Integer spId;
    private String spName;
    private String spCode;
    private String spMark;
    private String spType;
    private String spValue;
    private Integer spEditable;
    private String spDesc;

    /**
     * @return the spId
     */
    public final Integer getSpId() {
        return spId;
    }

    /**
     * @param spId
     *            the spId to set
     */
    public final void setSpId(Integer spId) {
        this.spId = spId;
    }

    /**
     * @return the spName
     */
    public final String getSpName() {
        return spName;
    }

    /**
     * @param spName
     *            the spName to set
     */
    public final void setSpName(String spName) {
        this.spName = spName;
    }

    /**
     * @return the spCode
     */
    public final String getSpCode() {
        return spCode;
    }

    /**
     * @param spCode
     *            the spCode to set
     */
    public final void setSpCode(String spCode) {
        this.spCode = spCode;
    }

    /**
     * @return the spMark
     */
    public final String getSpMark() {
        return spMark;
    }

    /**
     * @param spMark
     *            the spMark to set
     */
    public final void setSpMark(String spMark) {
        this.spMark = spMark;
    }

    /**
     * @return the spType
     */
    public final String getSpType() {
        return spType;
    }

    /**
     * @param spType
     *            the spType to set
     */
    public final void setSpType(String spType) {
        this.spType = spType;
    }

    /**
     * @return the spValue
     */
    public final String getSpValue() {
        return spValue;
    }

    /**
     * @param spValue
     *            the spValue to set
     */
    public final void setSpValue(String spValue) {
        this.spValue = spValue;
    }

    /**
     * @return the spEditable
     */
    public final Integer getSpEditable() {
        return spEditable;
    }

    /**
     * @param spEditable
     *            the spEditable to set
     */
    public final void setSpEditable(Integer spEditable) {
        this.spEditable = spEditable;
    }

    /**
     * @return the spDesc
     */
    public final String getSpDesc() {
        return spDesc;
    }

    /**
     * @param spDesc
     *            the spDesc to set
     */
    public final void setSpDesc(String spDesc) {
        this.spDesc = spDesc;
    }

}