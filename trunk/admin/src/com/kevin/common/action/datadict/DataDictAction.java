package com.kevin.common.action.datadict;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.kevin.common.action.AbstractBaseAction;
import com.kevin.common.exception.CommonServiceException;
import com.kevin.common.pojo.system.DataDict;
import com.kevin.common.service.datadict.IDataDictService;
import com.opensymphony.xwork2.Action;

@Controller("dataDictAction")
@Scope("prototype")
public class DataDictAction extends AbstractBaseAction {
    private IDataDictService ddService;
    private List<DataDict> ddList;
    private DataDict dd;

    /**
     * @return the dd
     */
    public DataDict getDd() {
        return dd;
    }

    /**
     * @param dd the dd to set
     */
    public void setDd(DataDict dd) {
        this.dd = dd;
    }

    /**
     * @return the ddList
     */
    public List<DataDict> getDdList() {
        return ddList;
    }

    /**
     * @param ddList
     *            the ddList to set
     */
    public void setDdList(List<DataDict> ddList) {
        this.ddList = ddList;
    }

    /**
     * @param ddService
     *            the ddService to set
     */
    @Autowired
    public void setDdService(IDataDictService ddService) {
        this.ddService = ddService;
    }
    /*********************************ACTIONS************************************/
    public String listDataDict(){
        try {
            ddList = ddService.listAll(dd);
            setResultCode(1);
        } catch (CommonServiceException e) {
            setMessage(e.getMessage());
            setResultCode(-1);
        }
        return Action.SUCCESS;
    }
    public String listAllDataDict(){
        try {
            ddList = ddService.listAll();
            setResultCode(1);
        } catch (CommonServiceException e) {
            setMessage(e.getMessage());
            setResultCode(-1);
        }
        return Action.SUCCESS;
    }
}