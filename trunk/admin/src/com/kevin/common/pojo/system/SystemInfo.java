/**
 * SystemInfo.java
 */
package com.kevin.common.pojo.system;

import java.io.Serializable;
import java.util.Date;
 /**
  * 系统属性，参数
  * @author jiangyaniu
  * @since 2011-12-08 18:31
  */
public class SystemInfo implements Serializable {
    /**
     * 
     */
    private static final long serialVersionUID = 59882451023732125L;
    private Integer sysId;
    private String sysName;
    private String sysCode;
    private String sysVersion;
    private String redisIp;
    private String redisPort;
    private String ftpAccount;
    private String ftpPwd;
    private String srcRoot;
    private String dstRoot;
    private Date addTime;
    private String addUser;
    private Date updateTime;
    private String updateUser;
    public Integer getSysId() {
        return sysId;
    }
    public void setSysId(Integer sysId) {
        this.sysId = sysId;
    }
    public String getSysName() {
        return sysName;
    }
    public void setSysName(String sysName) {
        this.sysName = sysName;
    }
    public String getSysCode() {
        return sysCode;
    }
    public void setSysCode(String sysCode) {
        this.sysCode = sysCode;
    }
    public String getSysVersion() {
        return sysVersion;
    }
    public void setSysVersion(String sysVersion) {
        this.sysVersion = sysVersion;
    }
    public String getRedisIp() {
        return redisIp;
    }
    public void setRedisIp(String redisIp) {
        this.redisIp = redisIp;
    }
    public String getRedisPort() {
        return redisPort;
    }
    public void setRedisPort(String redisPort) {
        this.redisPort = redisPort;
    }
    public String getFtpAccount() {
        return ftpAccount;
    }
    public void setFtpAccount(String ftpAccount) {
        this.ftpAccount = ftpAccount;
    }
    public String getFtpPwd() {
        return ftpPwd;
    }
    public void setFtpPwd(String ftpPwd) {
        this.ftpPwd = ftpPwd;
    }
    public String getSrcRoot() {
        return srcRoot;
    }
    public void setSrcRoot(String srcRoot) {
        this.srcRoot = srcRoot;
    }
    public String getDstRoot() {
        return dstRoot;
    }
    public void setDstRoot(String dstRoot) {
        this.dstRoot = dstRoot;
    }
    public Date getAddTime() {
        return addTime;
    }
    public void setAddTime(Date addTime) {
        this.addTime = addTime;
    }
    public String getAddUser() {
        return addUser;
    }
    public void setAddUser(String addUser) {
        this.addUser = addUser;
    }
    public Date getUpdateTime() {
        return updateTime;
    }
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
    public String getUpdateUser() {
        return updateUser;
    }
    public void setUpdateUser(String updateUser) {
        this.updateUser = updateUser;
    }
}
