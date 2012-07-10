/**
 * DoctorInfo.java
 * kevin 2012-6-17
 * @version 0.1
 */
package com.kevin.group.pojo.member;

import com.google.gson.Gson;

/**
 * @author kevin
 * @since jdk1.6
 */
public class DoctorInfo {
    private Integer diId;
    private String diName;
    private String diPosition;
    private Integer diDeptType;
    private String diDeptName;
    private String diPortrait;
    private Integer osExpertId;
    private Integer osFamousId;
    private Integer osFamousStatus;
//    private String osIdName;
    private String diOrder;
    private Integer diSex;
    private Integer diEducation;
    private String diResume;
    private String diMajor;
    private String diResearch_direction;
    private String diAccomplishment;
    private String doctType;// user for id array

    /**
     * @return the diId
     */
    public final Integer getDiId() {
        return diId;
    }

    /**
     * @param diId
     *            the diId to set
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
     * @param diName
     *            the diName to set
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
     * @param diPosition
     *            the diPosition to set
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
     * @param diDeptType
     *            the diDeptType to set
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
     * @param diDeptName
     *            the diDeptName to set
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
     * @param diPortrait
     *            the diPortrait to set
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
     * @param osExpertId
     *            the osExpertId to set
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
     * @param osFamousId
     *            the osFamousId to set
     */
    public final void setOsFamousId(Integer osFamousId) {
        this.osFamousId = osFamousId;
    }

//    /**
//     * @return the osIdName
//     */
//    public final String getOsIdName() {
//        return osIdName;
//    }
//
//    /**
//     * @param osIdName
//     *            the osIdName to set
//     */
//    public final void setOsIdName(String osIdName) {
//        this.osIdName = osIdName;
//    }

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
     * @param diOrder
     *            the diOrder to set
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
     * @param diSex
     *            the diSex to set
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
     * @param diEducation
     *            the diEducation to set
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
     * @param diResume
     *            the diResume to set
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
     * @param diMajor
     *            the diMajor to set
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
     * @param diResearch_direction
     *            the diResearch_direction to set
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
     * @param diAccomplishment
     *            the diAccomplishment to set
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
     * @param doctType
     *            the doctType to set
     */
    public final void setDoctType(String doctType) {
        this.doctType = doctType;
    }
    
    /**
     * Generate the json format Doct
     * @return
     */
    public Object generateJSON() {
        Gson gson = new Gson();
        return gson.toJson(this);
    }

}
