package com.kevin.common.pojo;

import java.io.Serializable;
import java.util.List;

public class PageBean<T> implements Serializable{
    /**
     * 
     */
    private static final long serialVersionUID = -6637534122335852142L;
    private List<T> dataList;
    private long totalPageCount;
    private int pageSize;
    private int currentPageNo;
    private long totalItemCount;
    /**
     * 分页查询时开始索引
     */
    private int start;//mysql从0开始
    /**
     * 分页查询时结束索引
     */
    private int end;
    private T condition;
    public PageBean() {
        super();
    }
    public PageBean(List<T> dataList, int totalPageCount) {
        super();
        this.dataList = dataList;
        this.totalPageCount = totalPageCount;
    }
    public final List<T> getDataList() {
        return dataList;
    }
    public final void setDataList(List<T> dataList) {
        this.dataList = dataList;
    }
    public final long getTotalPageCount() {
        return totalPageCount;
    }
    public final void setTotalPageCount(long totalPageCount) {
        this.totalPageCount = totalPageCount;
    }
    public final int getPageSize() {
        return pageSize;
    }
    public final void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }
    public final int getCurrentPageNo() {
        return currentPageNo;
    }
    public final void setCurrentPageNo(int currentPageNo) {
        this.currentPageNo = currentPageNo;
    }
    public final T getCondition() {
        return condition;
    }
    public final void setCondition(T condition) {
        this.condition = condition;
    }
    public final int getStart() {
        this.start = (this.getCurrentPageNo() - 1) * this.getPageSize();
        return this.start;
    }
    public final void setStart(int start) {
        this.start = start;
    }
    public final int getEnd() {
        this.end = this.getCurrentPageNo()* this.getPageSize();
        return this.end;
    }
    public final void setEnd(int end) {
        this.end = end;
    }
    public final long getTotalItemCount() {
        return totalItemCount;
    }
    public final void setTotalItemCount(long totalItemCount) {
        this.totalItemCount = totalItemCount;
    }
}