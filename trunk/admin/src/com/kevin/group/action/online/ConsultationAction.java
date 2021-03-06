/**
 * ConsultationAction.java
 * kevin 2012-6-17
 * @version 0.1
 */
package com.kevin.group.action.online;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.kevin.common.action.AbstractBaseAction;
import com.kevin.common.exception.CommonServiceException;
import com.kevin.common.pojo.PageBean;
import com.kevin.group.pojo.online.Consultation;
import com.kevin.group.service.online.IConsultationService;
import com.opensymphony.xwork2.Action;

/**
 * @author kevin
 * @since jdk1.6
 */
@Component("consultationfoAction")
@Scope("prototype")
public class ConsultationAction extends AbstractBaseAction {

    private IConsultationService consultationService;
    private Consultation cons;
    private List<Consultation> consList;
    private PageBean<Consultation> page;
    private List<Serializable> ides;
 
    /**
     * @return the cons
     */
    public final Consultation getCons() {
        return cons;
    }

    /**
     * @param cons the cons to set
     */
    public final void setCons(Consultation cons) {
        this.cons = cons;
    }

    /**
     * @return the consList
     */
    public final List<Consultation> getConsList() {
        return consList;
    }

    /**
     * @param consList
     *            the consList to set
     */
    public final void setConsList(List<Consultation> consList) {
        this.consList = consList;
    }

    /**
     * @return the page
     */
    public final PageBean<Consultation> getPage() {
        return page;
    }

    /**
     * @param page the page to set
     */
    public final void setPage(PageBean<Consultation> page) {
        this.page = page;
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
     * @param consultationService
     *            the consultationService to set
     */
    @Autowired
    public final void setConsultationService(
            IConsultationService consultationService) {
        this.consultationService = consultationService;
    }

    public String addConsultation() {// add new cons
        try {
            Serializable id = consultationService.save(cons);
            setResultCode((Integer) id);
        } catch (CommonServiceException e) {
            setMessage(e.getMessage());
            setResultCode(-1);
        }
        return Action.SUCCESS;
    }

    public String searchConsultation() {// search cons
        try {
            this.cons = consultationService.search(cons);
            setResultCode(1);
        } catch (CommonServiceException e) {
            setMessage(e.getMessage());
            setResultCode(-1);
        }
        return Action.SUCCESS;
    }

    public String listConsultation() {// list all the cons
        try {
            if(page!=null){
                page.setCondition(cons);
            }
            page = consultationService.list(page);
            setResultCode(1);
        } catch (CommonServiceException e) {
            setMessage(e.getMessage());
            setResultCode(-1);
        }
        return Action.SUCCESS;
    }

    public String updateConsultation() {// update the cons info
        try {
            int result = consultationService.update(cons);
            setResultCode(result);
        } catch (CommonServiceException e) {
            setMessage(e.getMessage());
            setResultCode(-1);
        }
        return Action.SUCCESS;
    }
    
    public String updateClickCons() {// update the cons info
        try {
            int result = consultationService.updateClickCons(cons);
            setResultCode(result);
        } catch (CommonServiceException e) {
            setMessage(e.getMessage());
            setResultCode(-1);
        }
        return Action.SUCCESS;
    }

    public String deleteConsultation() {// delete the cons
        try {
            int result = consultationService.delete(cons);
            setResultCode(result);
        } catch (CommonServiceException e) {
            setMessage(e.getMessage());
            setResultCode(-1);
        }
        return Action.SUCCESS;
    }

    public String batchDeleteConsultation() {// bath delete the cons
        try {
            int result = consultationService.batchDelete(ides);
            setResultCode(result);
        } catch (CommonServiceException e) {
            setMessage(e.getMessage());
            setResultCode(-1);
        }
        return Action.SUCCESS;
    }
}
