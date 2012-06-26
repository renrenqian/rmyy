/**
 * DoctorAction.java
 * kevin 2012-6-17
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
import com.kevin.group.pojo.member.DoctorInfo;
import com.kevin.group.service.member.IDoctorService;
import com.opensymphony.xwork2.Action;

/**
 * @author kevin
 * @since jdk1.6
 */
@Component("doctorAction")
@Scope("prototype")
public class DoctorAction extends AbstractBaseAction {

    private IDoctorService doctorService;
    private DoctorInfo doct;
    private List<DoctorInfo> doctList;
    private List<Serializable> ides;
 
    /**
     * @return the doct
     */
    public final DoctorInfo getDoct() {
        return doct;
    }

    /**
     * @param doct
     *            the doct to set
     */
    public final void setDoct(DoctorInfo doct) {
        this.doct = doct;
    }

    /**
     * @return the doctList
     */
    public final List<DoctorInfo> getDoctList() {
        return doctList;
    }

    /**
     * @param doctList
     *            the doctList to set
     */
    public final void setDoctList(List<DoctorInfo> doctList) {
        this.doctList = doctList;
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
     * @param doctorService
     *            the doctorService to set
     */
    @Autowired
    public final void setDoctorService(IDoctorService doctorService) {
        this.doctorService = doctorService;
    }

    public String addDoctor() {// add new doct
        try {
            Serializable id = doctorService.save(doct);
            setResultCode((Integer) id);
        } catch (CommonServiceException e) {
            setMessage(e.getMessage());
            setResultCode(-1);
        }
        return Action.SUCCESS;
    }

    public String searchDoctor() {// search doct
        try {
            this.doct = doctorService.search(doct);
            setResultCode(1);
        } catch (CommonServiceException e) {
            setMessage(e.getMessage());
            setResultCode(-1);
        }
        return Action.SUCCESS;
    }

    public String listDoctor() {// list all the doct
        try {
            doctList = doctorService.listAll();
            setResultCode(1);
        } catch (CommonServiceException e) {
            setMessage(e.getMessage());
            setResultCode(-1);
        }
        return Action.SUCCESS;
    }

    public String updateDoctor() {// update the doct info
        try {
            int result = doctorService.update(doct);
            setResultCode(result);
        } catch (CommonServiceException e) {
            setMessage(e.getMessage());
            setResultCode(-1);
        }
        return Action.SUCCESS;
    }

    public String deleteDoctor() {// delete the doct
        try {
            int result = doctorService.delete(doct);
            setResultCode(result);
        } catch (CommonServiceException e) {
            setMessage(e.getMessage());
            setResultCode(-1);
        }
        return Action.SUCCESS;
    }

    public String batchDeleteDoctor() {// bath delete the doct
        try {
            int result = doctorService.batchDelete(ides);
            setResultCode(result);
        } catch (CommonServiceException e) {
            setMessage(e.getMessage());
            setResultCode(-1);
        }
        return Action.SUCCESS;
    }
}
