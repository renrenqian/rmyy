/**
 * PrivilegeInfo.java
 */
package com.kevin.common.pojo.system;
/**
 * 权限信息实体类
 * @author jiangyn
 *
 */
public class PrivilegeInfo  implements java.io.Serializable{
/*
                  权限ID    PI_ID    NUMBER
            权限名称    PI_NAME    VARCHAR
            权限父级    PI_PARENT    NUMBER
            权限级别    PI_LEVEL    NUMBER
            权限描述    PI_DESC    VARCHAR
            权限显隐    PI_SHOW    NUMBER
 */
    private static final long serialVersionUID = -7224488733622590895L;
    private Integer piId;
    private String piName;
    private String piMethod;
    private Integer piParent;
    private Integer piLevel;
    private String piDesc;
    private Integer piShow;
    /**
     * @return the piId
     */
    public Integer getPiId() {
        return piId;
    }
    /**
     * @param piId the piId to set
     */
    public void setPiId(Integer piId) {
        this.piId = piId;
    }
    /**
     * @return the piName
     */
    public String getPiName() {
        return piName;
    }
    /**
     * @param piName the piName to set
     */
    public void setPiName(String piName) {
        this.piName = piName;
    }
    /**
     * @return the piMethod
     */
    public String getPiMethod() {
        return piMethod;
    }
    /**
     * @param piMethod the piMethod to set
     */
    public void setPiMethod(String piMethod) {
        this.piMethod = piMethod;
    }
    /**
     * @return the piParent
     */
    public Integer getPiParent() {
        return piParent;
    }
    /**
     * @param piParent the piParent to set
     */
    public void setPiParent(Integer piParent) {
        this.piParent = piParent;
    }
    /**
     * @return the piLevel
     */
    public Integer getPiLevel() {
        return piLevel;
    }
    /**
     * @param piLevel the piLevel to set
     */
    public void setPiLevel(Integer piLevel) {
        this.piLevel = piLevel;
    }
    /**
     * @return the piDesc
     */
    public String getPiDesc() {
        return piDesc;
    }
    /**
     * @param piDesc the piDesc to set
     */
    public void setPiDesc(String piDesc) {
        this.piDesc = piDesc;
    }
    /**
     * @return the piShow
     */
    public Integer getPiShow() {
        return piShow;
    }
    /**
     * @param piShow the piShow to set
     */
    public void setPiShow(Integer piShow) {
        this.piShow = piShow;
    }
    
}
