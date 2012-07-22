/**
 * Consultation.java
 * kevin 2012-6-17
 * @version 0.1
 */
package com.kevin.group.pojo.online;

/**
 * @author kevin
 * @since jdk1.6
 */
public class Consultation {
    private Integer ocId;
    private String ocPost_time;
    private String ocPostStartTime;
    private String ocPostEndTime;
    private Integer ocRequest_office;
    private String ocRequestOfficeName;
    private Integer ocReceive_office;
    private String ocReceiveOfficeName;
    private String ocPost_name;
    private Integer ocPost_age;
    private Integer ocSex;
    private String ocPost_subject;
    private String ocDesc;
    private Integer osAnswered;
    private String osAnswer;
    private Integer osAnswer_office;
    private String osAnswerOfficeName;
    private String osAnswer_time;
    private Integer osTypical;

    private Integer consClick;
    private String sSearch; // for condition sSearch from datatable

    /**
     * @return the ocId
     */
    public final Integer getOcId() {
        return ocId;
    }

    /**
     * @param ocId
     *            the ocId to set
     */
    public final void setOcId(Integer ocId) {
        this.ocId = ocId;
    }

    /**
     * @return the ocPost_time
     */
    public final String getOcPost_time() {
        return ocPost_time;
    }

    /**
     * @param ocPost_time
     *            the ocPost_time to set
     */
    public final void setOcPost_time(String ocPost_time) {
        this.ocPost_time = ocPost_time;
    }

    /**
     * @return the ocPostStartTime
     */
    public final String getOcPostStartTime() {
        return ocPostStartTime;
    }

    /**
     * @param ocPostStartTime
     *            the ocPostStartTime to set
     */
    public final void setOcPostStartTime(String ocPostStartTime) {
        this.ocPostStartTime = ocPostStartTime;
    }

    /**
     * @return the ocPostEndTime
     */
    public final String getOcPostEndTime() {
        return ocPostEndTime;
    }

    /**
     * @param ocPostEndTime
     *            the ocPostEndTime to set
     */
    public final void setOcPostEndTime(String ocPostEndTime) {
        this.ocPostEndTime = ocPostEndTime;
    }

    /**
     * @return the ocRequest_office
     */
    public final Integer getOcRequest_office() {
        return ocRequest_office;
    }

    /**
     * @param ocRequest_office
     *            the ocRequest_office to set
     */
    public final void setOcRequest_office(Integer ocRequest_office) {
        this.ocRequest_office = ocRequest_office;
    }

    /**
     * @return the ocRequestOfficeName
     */
    public final String getOcRequestOfficeName() {
        return ocRequestOfficeName;
    }

    /**
     * @param ocRequestOfficeName
     *            the ocRequestOfficeName to set
     */
    public final void setOcRequestOfficeName(String ocRequestOfficeName) {
        this.ocRequestOfficeName = ocRequestOfficeName;
    }

    /**
     * @return the ocReceive_office
     */
    public final Integer getOcReceive_office() {
        return ocReceive_office;
    }

    /**
     * @param ocReceive_office
     *            the ocReceive_office to set
     */
    public final void setOcReceive_office(Integer ocReceive_office) {
        this.ocReceive_office = ocReceive_office;
    }

    /**
     * @return the ocReceiveOfficeName
     */
    public final String getOcReceiveOfficeName() {
        return ocReceiveOfficeName;
    }

    /**
     * @param ocReceiveOfficeName
     *            the ocReceiveOfficeName to set
     */
    public final void setOcReceiveOfficeName(String ocReceiveOfficeName) {
        this.ocReceiveOfficeName = ocReceiveOfficeName;
    }

    /**
     * @return the ocPost_name
     */
    public final String getOcPost_name() {
        return ocPost_name;
    }

    /**
     * @param ocPost_name
     *            the ocPost_name to set
     */
    public final void setOcPost_name(String ocPost_name) {
        this.ocPost_name = ocPost_name;
    }

    /**
     * @return the ocPost_age
     */
    public final Integer getOcPost_age() {
        return ocPost_age;
    }

    /**
     * @param ocPost_age
     *            the ocPost_age to set
     */
    public final void setOcPost_age(Integer ocPost_age) {
        this.ocPost_age = ocPost_age;
    }

    /**
     * @return the ocSex
     */
    public final Integer getOcSex() {
        return ocSex;
    }

    /**
     * @param ocSex
     *            the ocSex to set
     */
    public final void setOcSex(Integer ocSex) {
        this.ocSex = ocSex;
    }

    /**
     * @return the ocPost_subject
     */
    public final String getOcPost_subject() {
        return ocPost_subject;
    }

    /**
     * @param ocPost_subject
     *            the ocPost_subject to set
     */
    public final void setOcPost_subject(String ocPost_subject) {
        this.ocPost_subject = ocPost_subject;
    }

    /**
     * @return the ocDesc
     */
    public final String getOcDesc() {
        return ocDesc;
    }

    /**
     * @param ocDesc
     *            the ocDesc to set
     */
    public final void setOcDesc(String ocDesc) {
        this.ocDesc = ocDesc;
    }

    /**
     * @return the osAnswered
     */
    public final Integer getOsAnswered() {
        return osAnswered;
    }

    /**
     * @param osAnswered
     *            the osAnswered to set
     */
    public final void setOsAnswered(Integer osAnswered) {
        this.osAnswered = osAnswered;
    }

    /**
     * @return the osAnswer
     */
    public final String getOsAnswer() {
        return osAnswer;
    }

    /**
     * @param osAnswer
     *            the osAnswer to set
     */
    public final void setOsAnswer(String osAnswer) {
        this.osAnswer = osAnswer;
    }

    /**
     * @return the osAnswer_office
     */
    public final Integer getOsAnswer_office() {
        return osAnswer_office;
    }

    /**
     * @param osAnswer_office
     *            the osAnswer_office to set
     */
    public final void setOsAnswer_office(Integer osAnswer_office) {
        this.osAnswer_office = osAnswer_office;
    }

    /**
     * @return the osAnswerOfficeName
     */
    public final String getOsAnswerOfficeName() {
        return osAnswerOfficeName;
    }

    /**
     * @param osAnswerOfficeName
     *            the osAnswerOfficeName to set
     */
    public final void setOsAnswerOfficeName(String osAnswerOfficeName) {
        this.osAnswerOfficeName = osAnswerOfficeName;
    }

    /**
     * @return the osAnswer_time
     */
    public final String getOsAnswer_time() {
        return osAnswer_time;
    }

    /**
     * @param osAnswer_time
     *            the osAnswer_time to set
     */
    public final void setOsAnswer_time(String osAnswer_time) {
        this.osAnswer_time = osAnswer_time;
    }

    /**
     * @return the osTypical
     */
    public final Integer getOsTypical() {
        return osTypical;
    }

    /**
     * @param osTypical
     *            the osTypical to set
     */
    public final void setOsTypical(Integer osTypical) {
        this.osTypical = osTypical;
    }

    /**
     * @return the consClick
     */
    public final Integer getConsClick() {
        return consClick;
    }

    /**
     * @param consClick
     *            the consClick to set
     */
    public final void setConsClick(Integer consClick) {
        this.consClick = consClick;
    }

    /**
     * @return the sSearch
     */
    public final String getsSearch() {
        return sSearch;
    }

    /**
     * @param sSearch
     *            the sSearch to set
     */
    public final void setsSearch(String sSearch) {
        this.sSearch = sSearch;
    }

}
