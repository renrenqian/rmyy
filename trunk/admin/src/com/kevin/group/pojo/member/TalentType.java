/**
 * TalentType.java
 * kevin 2012-7-14
 * @version 0.1
 */
package com.kevin.group.pojo.member;

import com.google.gson.Gson;

/**
 * @author kevin
 * @since jdk1.6
 */
public class TalentType {

    private Integer tgId;
    private Integer tgType;
    private String tgName;
    private String tgCode;
    private String tgDesc;

    /**
     * @return the tgId
     */
    public final Integer getTgId() {
        return tgId;
    }

    /**
     * @param tgId
     *            the tgId to set
     */
    public final void setTgId(Integer tgId) {
        this.tgId = tgId;
    }

    /**
     * @return the tgType
     */
    public final Integer getTgType() {
        return tgType;
    }

    /**
     * @param tgType
     *            the tgType to set
     */
    public final void setTgType(Integer tgType) {
        this.tgType = tgType;
    }

    /**
     * @return the tgName
     */
    public final String getTgName() {
        return tgName;
    }

    /**
     * @param tgName
     *            the tgName to set
     */
    public final void setTgName(String tgName) {
        this.tgName = tgName;
    }

    /**
     * @return the tgCode
     */
    public final String getTgCode() {
        return tgCode;
    }

    /**
     * @param tgCode
     *            the tgCode to set
     */
    public final void setTgCode(String tgCode) {
        this.tgCode = tgCode;
    }

    /**
     * @return the tgDesc
     */
    public final String getTgDesc() {
        return tgDesc;
    }

    /**
     * @param tgDesc
     *            the tgDesc to set
     */
    public final void setTgDesc(String tgDesc) {
        this.tgDesc = tgDesc;
    }

    public final String generateJSON() {
        Gson gson = new Gson();
        return gson.toJson(this);
    }
}
