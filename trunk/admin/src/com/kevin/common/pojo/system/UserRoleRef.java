/**
 * UserRoleRef.java
 */
package com.kevin.common.pojo.system;
/**
 * 用户，角色信息关联实体类
 * @author chiang
 *
 */
public class UserRoleRef implements java.io.Serializable {
    /**
     * 
     */
    private static final long serialVersionUID = -6367830227914252008L;
    private UserInfo userInfo;
    private RoleInfo roleInfo;

    public UserInfo getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(UserInfo userInfo) {
        this.userInfo = userInfo;
    }

    public RoleInfo getRoleInfo() {
        return roleInfo;
    }

    public void setRoleInfo(RoleInfo roleInfo) {
        this.roleInfo = roleInfo;
    }
}