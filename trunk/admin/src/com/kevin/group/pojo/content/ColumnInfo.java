/**
 * ColumnInfo.java
 * kevin 2012-6-20
 * @version 0.1
 */
package com.kevin.group.pojo.content;

/**
 * @author kevin
 * @since jdk1.6
 */
public class ColumnInfo {
    private Integer ciId;
    private String ciTital;
    private String ciIcon;
    private Integer ciLevel;
    private String ciUrl;
    private Integer ciParent;
    private Integer ciNews;
    private String ciDesc;
    private String ciAdd_time;
    private Integer ciAdder;
    private ColumnInfo colList;

    /**
     * @return the ciId
     */
    public final Integer getCiId() {
        return ciId;
    }

    /**
     * @param ciId
     *            the ciId to set
     */
    public final void setCiId(Integer ciId) {
        this.ciId = ciId;
    }

    /**
     * @return the ciTital
     */
    public final String getCiTital() {
        return ciTital;
    }

    /**
     * @param ciTital
     *            the ciTital to set
     */
    public final void setCiTital(String ciTital) {
        this.ciTital = ciTital;
    }

    /**
     * @return the ciIcon
     */
    public final String getCiIcon() {
        return ciIcon;
    }

    /**
     * @param ciIcon
     *            the ciIcon to set
     */
    public final void setCiIcon(String ciIcon) {
        this.ciIcon = ciIcon;
    }

    /**
     * @return the ciLevel
     */
    public final Integer getCiLevel() {
        return ciLevel;
    }

    /**
     * @param ciLevel
     *            the ciLevel to set
     */
    public final void setCiLevel(Integer ciLevel) {
        this.ciLevel = ciLevel;
    }

    /**
     * @return the ciUrl
     */
    public final String getCiUrl() {
        return ciUrl;
    }

    /**
     * @param ciUrl
     *            the ciUrl to set
     */
    public final void setCiUrl(String ciUrl) {
        this.ciUrl = ciUrl;
    }

    /**
     * @return the ciParent
     */
    public final Integer getCiParent() {
        return ciParent;
    }

    /**
     * @param ciParent
     *            the ciParent to set
     */
    public final void setCiParent(Integer ciParent) {
        this.ciParent = ciParent;
    }

    /**
     * @return the ciNews
     */
    public final Integer getCiNews() {
        return ciNews;
    }

    /**
     * @param ciNews
     *            the ciNews to set
     */
    public final void setCiNews(Integer ciNews) {
        this.ciNews = ciNews;
    }

    /**
     * @return the ciDesc
     */
    public final String getCiDesc() {
        return ciDesc;
    }

    /**
     * @param ciDesc
     *            the ciDesc to set
     */
    public final void setCiDesc(String ciDesc) {
        this.ciDesc = ciDesc;
    }

    /**
     * @return the ciAdd_time
     */
    public final String getCiAdd_time() {
        return ciAdd_time;
    }

    /**
     * @param ciAdd_time
     *            the ciAdd_time to set
     */
    public final void setCiAdd_time(String ciAdd_time) {
        this.ciAdd_time = ciAdd_time;
    }

    /**
     * @return the ciAdder
     */
    public final Integer getCiAdder() {
        return ciAdder;
    }

    /**
     * @param ciAdder
     *            the ciAdder to set
     */
    public final void setCiAdder(Integer ciAdder) {
        this.ciAdder = ciAdder;
    }

    /**
     * @return the colList
     */
    public final ColumnInfo getColList() {
        return colList;
    }

    /**
     * @param colList
     *            the colList to set
     */
    public final void setColList(ColumnInfo colList) {
        this.colList = colList;
    }

}
