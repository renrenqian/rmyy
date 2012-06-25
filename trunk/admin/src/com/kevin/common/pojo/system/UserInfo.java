package com.kevin.common.pojo.system;

public class UserInfo implements java.io.Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = -3201058454361154213L;
    private Integer uiId;
    private String uiAccount;
    private String uiName;
    private String uiPwd;
    private String oldPwd;
    private Integer uiEnable;
    private String uiDesc;
    private String uiOfficePhone;
    private String uiCellPhone;
    private String uiMail;
    private String uiHome;
    private String uiCompany;
    private String roleNames;// 关联的角色名列表
    private Integer[] roleId;
    private String online;// 是否在线

    /**
     * @return the uiId
     */
    public final Integer getUiId() {
        return uiId;
    }

    /**
     * @param uiId
     *            the uiId to set
     */
    public final void setUiId(Integer uiId) {
        this.uiId = uiId;
    }

    /**
     * @return the uiAccount
     */
    public final String getUiAccount() {
        return uiAccount;
    }

    /**
     * @param uiAccount
     *            the uiAccount to set
     */
    public final void setUiAccount(String uiAccount) {
        this.uiAccount = uiAccount;
    }

    /**
     * @return the uiName
     */
    public final String getUiName() {
        return uiName;
    }

    /**
     * @param uiName
     *            the uiName to set
     */
    public final void setUiName(String uiName) {
        this.uiName = uiName;
    }

    /**
     * @return the uiPwd
     */
    public final String getUiPwd() {
        return uiPwd;
    }

    /**
     * @param uiPwd
     *            the uiPwd to set
     */
    public final void setUiPwd(String uiPwd) {
        this.uiPwd = uiPwd;
    }

    /**
     * @return the oldPwd
     */
    public final String getOldPwd() {
        return oldPwd;
    }

    /**
     * @param oldPwd
     *            the oldPwd to set
     */
    public final void setOldPwd(String oldPwd) {
        this.oldPwd = oldPwd;
    }

    /**
     * @return the uiEnable
     */
    public final Integer getUiEnable() {
        return uiEnable;
    }

    /**
     * @param uiEnable
     *            the uiEnable to set
     */
    public final void setUiEnable(Integer uiEnable) {
        this.uiEnable = uiEnable;
    }

    /**
     * @return the uiDesc
     */
    public final String getUiDesc() {
        return uiDesc;
    }

    /**
     * @param uiDesc
     *            the uiDesc to set
     */
    public final void setUiDesc(String uiDesc) {
        this.uiDesc = uiDesc;
    }

    /**
     * @return the uiOfficePhone
     */
    public final String getUiOfficePhone() {
        return uiOfficePhone;
    }

    /**
     * @param uiOfficePhone
     *            the uiOfficePhone to set
     */
    public final void setUiOfficePhone(String uiOfficePhone) {
        this.uiOfficePhone = uiOfficePhone;
    }

    /**
     * @return the uiCellPhone
     */
    public final String getUiCellPhone() {
        return uiCellPhone;
    }

    /**
     * @param uiCellPhone
     *            the uiCellPhone to set
     */
    public final void setUiCellPhone(String uiCellPhone) {
        this.uiCellPhone = uiCellPhone;
    }

    /**
     * @return the uiMail
     */
    public final String getUiMail() {
        return uiMail;
    }

    /**
     * @param uiMail
     *            the uiMail to set
     */
    public final void setUiMail(String uiMail) {
        this.uiMail = uiMail;
    }

    /**
     * @return the uiHome
     */
    public final String getUiHome() {
        return uiHome;
    }

    /**
     * @param uiHome
     *            the uiHome to set
     */
    public final void setUiHome(String uiHome) {
        this.uiHome = uiHome;
    }

    /**
     * @return the uiCompany
     */
    public final String getUiCompany() {
        return uiCompany;
    }

    /**
     * @param uiCompany
     *            the uiCompany to set
     */
    public final void setUiCompany(String uiCompany) {
        this.uiCompany = uiCompany;
    }

    /**
     * @return the roleNames
     */
    public final String getRoleNames() {
        return roleNames;
    }

    /**
     * @param roleNames
     *            the roleNames to set
     */
    public final void setRoleNames(String roleNames) {
        this.roleNames = roleNames;
    }

    /**
     * @return the roleId
     */
    public final Integer[] getRoleId() {
        return roleId;
    }

    /**
     * @param roleId
     *            the roleId to set
     */
    public final void setRoleId(Integer[] roleId) {
        this.roleId = roleId;
    }

    /**
     * @return the online
     */
    public final String getOnline() {
        return online;
    }

    /**
     * @param online
     *            the online to set
     */
    public final void setOnline(String online) {
        this.online = online;
    }

    /**
     * 
     * @return
     */
    public final UserInfo loginSuccess(){
        this.uiPwd = null;
        
        //this.
        return this;
    }
}