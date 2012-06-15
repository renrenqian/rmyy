/**
 * RoleInfo.java
 */
package com.kevin.common.pojo.system;
/**
 * 角色信息实体类
 * @author jiangyn
 *
 */
public class RoleInfo implements java.io.Serializable{

    /**
     * 
     */
    private static final long serialVersionUID = -7224488733622590896L;
    
    private Integer riId;
    private String riName;
    private Integer riParent;
    private Integer riLevel;
    private String riDesc;
    private Integer riShow;
    /**
     * @return the riId
     */
    public Integer getRiId() {
        return riId;
    }
    /**
     * @param riId the riId to set
     */
    public void setRiId(Integer riId) {
        this.riId = riId;
    }
    /**
     * @return the riName
     */
    public String getRiName() {
        return riName;
    }
    /**
     * @param riName the riName to set
     */
    public void setRiName(String riName) {
        this.riName = riName;
    }
    /**
     * @return the riParent
     */
    public Integer getRiParent() {
        return riParent;
    }
    /**
     * @param riParent the riParent to set
     */
    public void setRiParent(Integer riParent) {
        this.riParent = riParent;
    }
    /**
     * @return the riLevel
     */
    public Integer getRiLevel() {
        return riLevel;
    }
    /**
     * @param riLevel the riLevel to set
     */
    public void setRiLevel(Integer riLevel) {
        this.riLevel = riLevel;
    }
    /**
     * @return the riDesc
     */
    public String getRiDesc() {
        return riDesc;
    }
    /**
     * @param riDesc the riDesc to set
     */
    public void setRiDesc(String riDesc) {
        this.riDesc = riDesc;
    }
    /**
     * @return the riShow
     */
    public Integer getRiShow() {
        return riShow;
    }
    /**
     * @param riShow the riShow to set
     */
    public void setRiShow(Integer riShow) {
        this.riShow = riShow;
    }
}