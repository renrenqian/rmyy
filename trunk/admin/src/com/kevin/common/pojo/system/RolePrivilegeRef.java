/**
 * RolePrivilegeRef.java
 */
package com.kevin.common.pojo.system;
/**
 * 权限角色关联信息实体类
 * @author jiangyn
 *
 */
public class RolePrivilegeRef implements java.io.Serializable {
    /**
     * 
     */
    private static final long serialVersionUID = 7388334505056252058L;
    private RoleInfo roleInfo;
    private PrivilegeInfo privilegeInfo;

    /**
     * @return the roleInfo
     */
    public RoleInfo getRoleInfo() {
        return roleInfo;
    }

    /**
     * @param roleInfo
     *            the roleInfo to set
     */
    public void setRoleInfo(RoleInfo roleInfo) {
        this.roleInfo = roleInfo;
    }

    /**
     * @return the privilegeInfo
     */
    public PrivilegeInfo getPrivilegeInfo() {
        return privilegeInfo;
    }

    /**
     * @param privilegeInfo
     *            the privilegeInfo to set
     */
    public void setPrivilegeInfo(PrivilegeInfo privilegeInfo) {
        this.privilegeInfo = privilegeInfo;
    }

}