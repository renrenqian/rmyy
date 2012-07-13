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
public class OPSer extends DoctorInfo {

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

}
