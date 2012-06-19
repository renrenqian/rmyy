/**
 * ColumnInfoAction.java
 * kevin 2012-6-20
 * @version 0.1
 */
package com.kevin.group.action.content;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.kevin.common.action.AbstractBaseAction;
import com.kevin.common.exception.CommonServiceException;
import com.kevin.group.pojo.content.ColumnInfo;
import com.kevin.group.service.content.IColumnInfoService;
import com.opensymphony.xwork2.Action;

/**
 * @author kevin
 * @since jdk1.6
 */
@Component("conlInfoAction")
@Scope("prototype")
public class ColumnInfoAction extends AbstractBaseAction {

    private IColumnInfoService colInfoService;
    private ColumnInfo col;
    private List<ColumnInfo> colList;
    private List<Serializable> ides;

    /**
     * @return the col
     */
    public final ColumnInfo getCol() {
        return col;
    }

    /**
     * @param col
     *            the col to set
     */
    public final void setCol(ColumnInfo col) {
        this.col = col;
    }

    /**
     * @return the colList
     */
    public final List<ColumnInfo> getColList() {
        return colList;
    }

    /**
     * @param colList
     *            the colList to set
     */
    public final void setColList(List<ColumnInfo> colList) {
        this.colList = colList;
    }

    /**
     * @return the ides
     */
    public final List<Serializable> getIdes() {
        return ides;
    }

    /**
     * @param ides
     *            the ides to set
     */
    public final void setIdes(List<Serializable> ides) {
        this.ides = ides;
    }

    /**
     * @param colInfoService
     *            the colInfoService to set
     */
    @Autowired
    public final void setColInfoService(IColumnInfoService colInfoService) {
        this.colInfoService = colInfoService;
    }

    public String addColumn() {// add new col
        try {
            Serializable id = colInfoService.save(col);
            setResultCode((Integer) id);
        } catch (CommonServiceException e) {
            setMessage(e.getMessage());
            setResultCode(-1);
        }
        return Action.SUCCESS;
    }

    public String searchColumn() {// search col
        try {
            this.col = colInfoService.search(col);
            setResultCode(1);
        } catch (CommonServiceException e) {
            setMessage(e.getMessage());
            setResultCode(-1);
        }
        return Action.SUCCESS;
    }

    public String listColumn() {// list all the col
        try {
            colList = colInfoService.listAll();
            setResultCode(1);
        } catch (CommonServiceException e) {
            setMessage(e.getMessage());
            setResultCode(-1);
        }
        return Action.SUCCESS;
    }

    public String updateColumn() {// update the col info
        try {
            int result = colInfoService.update(col);
            setResultCode(result);
        } catch (CommonServiceException e) {
            setMessage(e.getMessage());
            setResultCode(-1);
        }
        return Action.SUCCESS;
    }

    public String deleteColumn() {// delete the col
        try {
            int result = colInfoService.delete(col);
            setResultCode(result);
        } catch (CommonServiceException e) {
            setMessage(e.getMessage());
            setResultCode(-1);
        }
        return Action.SUCCESS;
    }

    public String batchDeleteColumn() {// bath delete the col
        try {
            int result = colInfoService.batchDelete(ides);
            setResultCode(result);
        } catch (CommonServiceException e) {
            setMessage(e.getMessage());
            setResultCode(-1);
        }
        return Action.SUCCESS;
    }
}
