/**
 * ContentInfo.java
 * kevin 2012-6-16
 * @version 0.1
 */
package com.kevin.group.pojo.content;

import java.io.Serializable;

import com.google.gson.Gson;

/**
 * @author kevin
 * @since jdk1.6
 */
public class ContentInfo implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 593206827926878359L;
    private Integer contId;
    private String contTitle;
    private String contHome_Page;
    private Integer colId;
    private Integer ciCate;
    private String colName;
    private Integer contShow_Days;
    private Integer contMedium;
    private String contKey;
    private String contPublish_Time;
    private String contSearchStartTime;
    private String contSearchEndTime;
    private Integer contAudit_Result;
    private String contAudit_DESC;
    private Integer contOrder;
    private Integer gmId;// belong to group
    private String gmName;
    private String contAuthor;
    private String contDetail;
    private String contAttachment;// store the upload file path
    private String displayImage;// store the upload file path

    /**
     * @return the contId
     */
    public final Integer getContId() {
        return contId;
    }

    /**
     * @param contId
     *            the contId to set
     */
    public final void setContId(Integer contId) {
        this.contId = contId;
    }
 
    /**
     * @return the contTitle
     */
    public final String getContTitle() {
        return contTitle;
    }

    /**
     * @param contTitle the contTitle to set
     */
    public final void setContTitle(String contTitle) {
        this.contTitle = contTitle;
    }

    /**
     * @return the contHome_Page
     */
    public final String getContHome_Page() {
        return contHome_Page;
    }

    /**
     * @param contHome_Page
     *            the contHome_Page to set
     */
    public final void setContHome_Page(String contHome_Page) {
        this.contHome_Page = contHome_Page;
    }

    /**
     * @return the colId
     */
    public final Integer getColId() {
        return colId;
    }

    /**
     * @param colId
     *            the colId to set
     */
    public final void setColId(Integer colId) {
        this.colId = colId;
    }

    /**
     * @return the ciCate
     */
    public final Integer getCiCate() {
        return ciCate;
    }

    /**
     * @param ciCate the ciCate to set
     */
    public final void setCiCate(Integer ciCate) {
        this.ciCate = ciCate;
    }

    /**
     * @return the colName
     */
    public final String getColName() {
        return colName;
    }

    /**
     * @param colName
     *            the colName to set
     */
    public final void setColName(String colName) {
        this.colName = colName;
    }

    /**
     * @return the contShow_Days
     */
    public final Integer getContShow_Days() {
        return contShow_Days;
    }

    /**
     * @param contShow_Days
     *            the contShow_Days to set
     */
    public final void setContShow_Days(Integer contShow_Days) {
        this.contShow_Days = contShow_Days;
    }

    /**
     * @return the contMedium
     */
    public final Integer getContMedium() {
        return contMedium;
    }

    /**
     * @param contMedium
     *            the contMedium to set
     */
    public final void setContMedium(Integer contMedium) {
        this.contMedium = contMedium;
    }

    /**
     * @return the contKey
     */
    public final String getContKey() {
        return contKey;
    }

    /**
     * @param contKey
     *            the contKey to set
     */
    public final void setContKey(String contKey) {
        this.contKey = contKey;
    }

    /**
     * @return the contPublish_Time
     */
    public final String getContPublish_Time() {
        return contPublish_Time;
    }

    /**
     * @param contPublish_Time
     *            the contPublish_Time to set
     */
    public final void setContPublish_Time(String contPublish_Time) {
        this.contPublish_Time = contPublish_Time;
    }

    /**
     * @return the contAudit_Result
     */
    public final Integer getContAudit_Result() {
        return contAudit_Result;
    }

    /**
     * @param contAudit_Result
     *            the contAudit_Result to set
     */
    public final void setContAudit_Result(Integer contAudit_Result) {
        this.contAudit_Result = contAudit_Result;
    }

    /**
     * @return the contAudit_DESC
     */
    public final String getContAudit_DESC() {
        return contAudit_DESC;
    }

    /**
     * @param contAudit_DESC
     *            the contAudit_DESC to set
     */
    public final void setContAudit_DESC(String contAudit_DESC) {
        this.contAudit_DESC = contAudit_DESC;
    }

    /**
     * @return the contOrder
     */
    public final Integer getContOrder() {
        return contOrder;
    }

    /**
     * @param contOrder
     *            the contOrder to set
     */
    public final void setContOrder(Integer contOrder) {
        this.contOrder = contOrder;
    }

    /**
     * @return the gmId
     */
    public final Integer getGmId() {
        return gmId;
    }

    /**
     * @param gmId
     *            the gmId to set
     */
    public final void setGmId(Integer gmId) {
        this.gmId = gmId;
    }

    /**
     * @return the gmName
     */
    public final String getGmName() {
        return gmName;
    }

    /**
     * @param gmName
     *            the gmName to set
     */
    public final void setGmName(String gmName) {
        this.gmName = gmName;
    }
 
    /**
     * @return the contAuthor
     */
    public final String getContAuthor() {
        return contAuthor;
    }

    /**
     * @param contAuthor the contAuthor to set
     */
    public final void setContAuthor(String contAuthor) {
        this.contAuthor = contAuthor;
    }

    /**
     * @return the contDetail
     */
    public final String getContDetail() {
        return contDetail;
    }

    /**
     * @param contDetail
     *            the contDetail to set
     */
    public final void setContDetail(String contDetail) {
        this.contDetail = contDetail;
    }

    /**
     * @return the contAttachment
     */
    public final String getContAttachment() {
        return contAttachment;
    }

    /**
     * @param contAttachment
     *            the contAttachment to set
     */
    public final void setContAttachment(String contAttachment) {
        this.contAttachment = contAttachment;
    }

    /**
     * @return the displayImage
     */
    public final String getDisplayImage() {
        return displayImage;
    }

    /**
     * @param displayImage the displayImage to set
     */
    public final void setDisplayImage(String displayImage) {
        this.displayImage = displayImage;
    }

    /**
     * @return the contSearchStartTime
     */
    public final String getContSearchStartTime() {
        return contSearchStartTime;
    }

    /**
     * @param contSearchStartTime the contSearchStartTime to set
     */
    public final void setContSearchStartTime(String contSearchStartTime) {
        this.contSearchStartTime = contSearchStartTime;
    }

    /**
     * @return the contSearchEndTime
     */
    public final String getContSearchEndTime() {
        return contSearchEndTime;
    }

    /**
     * @param contSearchEndTime the contSearchEndTime to set
     */
    public final void setContSearchEndTime(String contSearchEndTime) {
        this.contSearchEndTime = contSearchEndTime;
    }

    public String generateJSON() {
        Gson gson = new Gson();
        return gson.toJson(this);
    }
}
