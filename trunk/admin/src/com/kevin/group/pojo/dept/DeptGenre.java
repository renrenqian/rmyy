/**
 * DeptGenre.java
 * kevin 2012-6-16
 * @version 0.1
 */
package com.kevin.group.pojo.dept;

/**
 * @author kevin
 * @since jdk1.6
 */
public class DeptGenre {

    private Integer dgId;
    private String dgName;
    private String dgCode;
    private String dgDesc;
    private Integer dgLevel;
    private Integer dgParent;

    /**
     * @return the dgId
     */
    public final Integer getDgId() {
        return dgId;
    }

    /**
     * @param dgId
     *            the dgId to set
     */
    public final void setDgId(Integer dgId) {
        this.dgId = dgId;
    }

    /**
     * @return the dgName
     */
    public final String getDgName() {
        return dgName;
    }

    /**
     * @param dgName
     *            the dgName to set
     */
    public final void setDgName(String dgName) {
        this.dgName = dgName;
    }

    /**
     * @return the dgCode
     */
    public final String getDgCode() {
        return dgCode;
    }

    /**
     * @param dgCode
     *            the dgCode to set
     */
    public final void setDgCode(String dgCode) {
        this.dgCode = dgCode;
    }

    /**
     * @return the dgDesc
     */
    public final String getDgDesc() {
        return dgDesc;
    }

    /**
     * @param dgDesc
     *            the dgDesc to set
     */
    public final void setDgDesc(String dgDesc) {
        this.dgDesc = dgDesc;
    }

    /**
     * @return the dgLevel
     */
    public final Integer getDgLevel() {
        return dgLevel;
    }

    /**
     * @param dgLevel
     *            the dgLevel to set
     */
    public final void setDgLevel(Integer dgLevel) {
        this.dgLevel = dgLevel;
    }

    /**
     * @return the dgParent
     */
    public final Integer getDgParent() {
        return dgParent;
    }

    /**
     * @param dgParent
     *            the dgParent to set
     */
    public final void setDgParent(Integer dgParent) {
        this.dgParent = dgParent;
    }

}
