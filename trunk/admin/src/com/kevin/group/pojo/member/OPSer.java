/**
 * OutService.java
 * kevin 2012-6-27
 * @version 0.1
 */
package com.kevin.group.pojo.member;

/**
 * @author kevin
 * @since jdk1.6
 */
public class OPSer {

    private Integer osId;
    private Integer osCate;
    private String osTime;
    private String osLocation;
    private Integer osLimit;
    private Integer osStatus;
    private String osCost;
    private String osBook_link;
    private Integer doctId;

    private Integer diDeptId;
 
    // for list paginate of famous only
    private Integer osfCate;
    private String osfTime;
    private String osfLocation;
    private Integer osfLimit;
    private Integer osfStatus;
    private String osfCost;

    // for doct column duplic 

    private Integer diId;
    private String diName;
    private String diPosition;
    private Integer diDeptType;
    private String diDeptName;
    private String diPortrait;
    private Integer osExpertId;
    private Integer osFamousId;
    private Integer osFamousStatus;
    // private String osIdName;
    private String diOrder;
    private Integer diSex;
    private Integer diEducation;
    private String diResume;
    private String diMajor;
    private String diResearch_direction;
    private String diAccomplishment;
    private String doctType;// user for id array

    private Integer isfamWork; // is work same with the opser osStatus
    private Integer isexpWork; // is work same with the opser osStatus
    
    /**
     * @return the osId
     */
    public final Integer getOsId() {
        return osId;
    }

    /**
     * @param osId
     *            the osId to set
     */
    public final void setOsId(Integer osId) {
        this.osId = osId;
    }

    /**
     * @return the osCate
     */
    public final Integer getOsCate() {
        return osCate;
    }

    /**
     * @param osCate
     *            the osCate to set
     */
    public final void setOsCate(Integer osCate) {
        this.osCate = osCate;
    }

    /**
     * @return the osTime
     */
    public final String getOsTime() {
        return osTime;
    }

    /**
     * @param osTime
     *            the osTime to set
     */
    public final void setOsTime(String osTime) {
        this.osTime = osTime;
    }

    /**
     * @return the osLocation
     */
    public final String getOsLocation() {
        return osLocation;
    }

    /**
     * @param osLocation
     *            the osLocation to set
     */
    public final void setOsLocation(String osLocation) {
        this.osLocation = osLocation;
    }

    /**
     * @return the osLimit
     */
    public final Integer getOsLimit() {
        return osLimit;
    }

    /**
     * @param osLimit
     *            the osLimit to set
     */
    public final void setOsLimit(Integer osLimit) {
        this.osLimit = osLimit;
    }

    /**
     * @return the osStatus
     */
    public final Integer getOsStatus() {
        return osStatus;
    }

    /**
     * @param osStatus
     *            the osStatus to set
     */
    public final void setOsStatus(Integer osStatus) {
        this.osStatus = osStatus;
    }

    /**
     * @return the osCost
     */
    public final String getOsCost() {
        return osCost;
    }

    /**
     * @param osCost
     *            the osCost to set
     */
    public final void setOsCost(String osCost) {
        this.osCost = osCost;
    }

    /**
     * @return the osBook_link
     */
    public final String getOsBook_link() {
        return osBook_link;
    }

    /**
     * @param osBook_link
     *            the osBook_link to set
     */
    public final void setOsBook_link(String osBook_link) {
        this.osBook_link = osBook_link;
    }

    /**
     * @return the doctId
     */
    public final Integer getDoctId() {
        return doctId;
    }

    /**
     * @param doctId
     *            the doctId to set
     */
    public final void setDoctId(Integer doctId) {
        this.doctId = doctId;
    }

    /**
     * @return the diDeptId
     */
    public final Integer getDiDeptId() {
        return diDeptId;
    }

    /**
     * @param diDeptId
     *            the diDeptId to set
     */
    public final void setDiDeptId(Integer diDeptId) {
        this.diDeptId = diDeptId;
    }

    /**
     * @return the osfCate
     */
    public final Integer getOsfCate() {
        return osfCate;
    }

    /**
     * @param osfCate the osfCate to set
     */
    public final void setOsfCate(Integer osfCate) {
        this.osfCate = osfCate;
    }

    /**
     * @return the osfTime
     */
    public final String getOsfTime() {
        return osfTime;
    }

    /**
     * @param osfTime the osfTime to set
     */
    public final void setOsfTime(String osfTime) {
        this.osfTime = osfTime;
    }

    /**
     * @return the osfLocation
     */
    public final String getOsfLocation() {
        return osfLocation;
    }

    /**
     * @param osfLocation the osfLocation to set
     */
    public final void setOsfLocation(String osfLocation) {
        this.osfLocation = osfLocation;
    }

    /**
     * @return the osfLimit
     */
    public final Integer getOsfLimit() {
        return osfLimit;
    }

    /**
     * @param osfLimit the osfLimit to set
     */
    public final void setOsfLimit(Integer osfLimit) {
        this.osfLimit = osfLimit;
    }

    /**
     * @return the osfStatus
     */
    public final Integer getOsfStatus() {
        return osfStatus;
    }

    /**
     * @param osfStatus the osfStatus to set
     */
    public final void setOsfStatus(Integer osfStatus) {
        this.osfStatus = osfStatus;
    }

    /**
     * @return the osfCost
     */
    public final String getOsfCost() {
        return osfCost;
    }

    /**
     * @param osfCost the osfCost to set
     */
    public final void setOsfCost(String osfCost) {
        this.osfCost = osfCost;
    }

    /**
     * @return the diId
     */
    public final Integer getDiId() {
        return diId;
    }

    /**
     * @param diId the diId to set
     */
    public final void setDiId(Integer diId) {
        this.diId = diId;
    }

    /**
     * @return the diName
     */
    public final String getDiName() {
        return diName;
    }

    /**
     * @param diName the diName to set
     */
    public final void setDiName(String diName) {
        this.diName = diName;
    }

    /**
     * @return the diPosition
     */
    public final String getDiPosition() {
        return diPosition;
    }

    /**
     * @param diPosition the diPosition to set
     */
    public final void setDiPosition(String diPosition) {
        this.diPosition = diPosition;
    }

    /**
     * @return the diDeptType
     */
    public final Integer getDiDeptType() {
        return diDeptType;
    }

    /**
     * @param diDeptType the diDeptType to set
     */
    public final void setDiDeptType(Integer diDeptType) {
        this.diDeptType = diDeptType;
    }

    /**
     * @return the diDeptName
     */
    public final String getDiDeptName() {
        return diDeptName;
    }

    /**
     * @param diDeptName the diDeptName to set
     */
    public final void setDiDeptName(String diDeptName) {
        this.diDeptName = diDeptName;
    }

    /**
     * @return the diPortrait
     */
    public final String getDiPortrait() {
        return diPortrait;
    }

    /**
     * @param diPortrait the diPortrait to set
     */
    public final void setDiPortrait(String diPortrait) {
        this.diPortrait = diPortrait;
    }

    /**
     * @return the osExpertId
     */
    public final Integer getOsExpertId() {
        return osExpertId;
    }

    /**
     * @param osExpertId the osExpertId to set
     */
    public final void setOsExpertId(Integer osExpertId) {
        this.osExpertId = osExpertId;
    }

    /**
     * @return the osFamousId
     */
    public final Integer getOsFamousId() {
        return osFamousId;
    }

    /**
     * @param osFamousId the osFamousId to set
     */
    public final void setOsFamousId(Integer osFamousId) {
        this.osFamousId = osFamousId;
    }

    /**
     * @return the osFamousStatus
     */
    public final Integer getOsFamousStatus() {
        return osFamousStatus;
    }

    /**
     * @param osFamousStatus the osFamousStatus to set
     */
    public final void setOsFamousStatus(Integer osFamousStatus) {
        this.osFamousStatus = osFamousStatus;
    }

    /**
     * @return the diOrder
     */
    public final String getDiOrder() {
        return diOrder;
    }

    /**
     * @param diOrder the diOrder to set
     */
    public final void setDiOrder(String diOrder) {
        this.diOrder = diOrder;
    }

    /**
     * @return the diSex
     */
    public final Integer getDiSex() {
        return diSex;
    }

    /**
     * @param diSex the diSex to set
     */
    public final void setDiSex(Integer diSex) {
        this.diSex = diSex;
    }

    /**
     * @return the diEducation
     */
    public final Integer getDiEducation() {
        return diEducation;
    }

    /**
     * @param diEducation the diEducation to set
     */
    public final void setDiEducation(Integer diEducation) {
        this.diEducation = diEducation;
    }

    /**
     * @return the diResume
     */
    public final String getDiResume() {
        return diResume;
    }

    /**
     * @param diResume the diResume to set
     */
    public final void setDiResume(String diResume) {
        this.diResume = diResume;
    }

    /**
     * @return the diMajor
     */
    public final String getDiMajor() {
        return diMajor;
    }

    /**
     * @param diMajor the diMajor to set
     */
    public final void setDiMajor(String diMajor) {
        this.diMajor = diMajor;
    }

    /**
     * @return the diResearch_direction
     */
    public final String getDiResearch_direction() {
        return diResearch_direction;
    }

    /**
     * @param diResearch_direction the diResearch_direction to set
     */
    public final void setDiResearch_direction(String diResearch_direction) {
        this.diResearch_direction = diResearch_direction;
    }

    /**
     * @return the diAccomplishment
     */
    public final String getDiAccomplishment() {
        return diAccomplishment;
    }

    /**
     * @param diAccomplishment the diAccomplishment to set
     */
    public final void setDiAccomplishment(String diAccomplishment) {
        this.diAccomplishment = diAccomplishment;
    }

    /**
     * @return the doctType
     */
    public final String getDoctType() {
        return doctType;
    }

    /**
     * @param doctType the doctType to set
     */
    public final void setDoctType(String doctType) {
        this.doctType = doctType;
    }

    /**
     * @return the isfamWork
     */
    public final Integer getIsfamWork() {
        return isfamWork;
    }

    /**
     * @param isfamWork the isfamWork to set
     */
    public final void setIsfamWork(Integer isfamWork) {
        this.isfamWork = isfamWork;
    }

    /**
     * @return the isexpWork
     */
    public final Integer getIsexpWork() {
        return isexpWork;
    }

    /**
     * @param isexpWork the isexpWork to set
     */
    public final void setIsexpWork(Integer isexpWork) {
        this.isexpWork = isexpWork;
    }

}
