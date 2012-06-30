/**
 * OPSerAction.java
 * kevin 2012-6-27
 * @version 0.1
 */
package com.kevin.group.action.member;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.kevin.common.action.AbstractBaseAction;
import com.kevin.common.exception.CommonServiceException;
import com.kevin.group.pojo.member.OPSer;
import com.kevin.group.service.member.IOPSerService;
import com.opensymphony.xwork2.Action;

/**
 * @author kevin
 * @since jdk1.6
 */
@Component("opserAction")
@Scope("prototype")
public class OPSerAction extends AbstractBaseAction {

    private IOPSerService opserService;
    private OPSer ops;
    private List<OPSer> opsList;
    private List<Serializable> ides;

    /**
     * @param opserService
     *            the opserService to set
     */
    @Autowired
    public final void setOpserService(IOPSerService opserService) {
        this.opserService = opserService;
    }

    /**
     * @return the ops
     */
    public final OPSer getOps() {
        return ops;
    }

    /**
     * @param ops
     *            the ops to set
     */
    public final void setOps(OPSer ops) {
        this.ops = ops;
    }

    /**
     * @return the opsList
     */
    public final List<OPSer> getOpsList() {
        return opsList;
    }

    /**
     * @param opsList
     *            the opsList to set
     */
    public final void setOpsList(List<OPSer> opsList) {
        this.opsList = opsList;
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

    public String addOPSer() {// add new ops
        try {
            Serializable id = opserService.save(ops);
            setResultCode((Integer) id);
        } catch (CommonServiceException e) {
            setMessage(e.getMessage());
            setResultCode(-1);
        }
        return Action.SUCCESS;
    }

    public String searchOPSer() {// search ops
        try {
            this.ops = opserService.search(ops);
            setResultCode(1);
        } catch (CommonServiceException e) {
            setMessage(e.getMessage());
            setResultCode(-1);
        }
        return Action.SUCCESS;
    }

    public String listOPSer() {// list all the ops
        try {
            opsList = opserService.listAll();
            setResultCode(1);
        } catch (CommonServiceException e) {
            setMessage(e.getMessage());
            setResultCode(-1);
        }
        return Action.SUCCESS;
    }

    public String updateOPSer() {// update the ops info
        try {
            int result = opserService.update(ops);
            setResultCode(result);
        } catch (CommonServiceException e) {
            setMessage(e.getMessage());
            setResultCode(-1);
        }
        return Action.SUCCESS;
    }

    public String deleteOPSer() {// delete the ops
        try {
            int result = opserService.delete(ops);
            setResultCode(result);
        } catch (CommonServiceException e) {
            setMessage(e.getMessage());
            setResultCode(-1);
        }
        return Action.SUCCESS;
    }

    public String batchDeleteOPSer() {// bath delete the ops
        try {
            int result = opserService.batchDelete(ides);
            setResultCode(result);
        } catch (CommonServiceException e) {
            setMessage(e.getMessage());
            setResultCode(-1);
        }
        return Action.SUCCESS;
    }
}
