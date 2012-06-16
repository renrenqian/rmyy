/**
 * DeptInfoAction.java
 * kevin 2012-6-16
 * @version 0.1
 */
package com.kevin.group.action.dept;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.kevin.common.action.AbstractBaseAction;
import com.kevin.common.exception.CommonServiceException;
import com.kevin.group.pojo.dept.Dept;
import com.kevin.group.service.dept.IDeptServices;
import com.opensymphony.xwork2.Action;

/**
 * @author kevin
 * @since jdk1.6
 */
@Component("deptInfoAction")
@Scope("prototype")
public class DeptInfoAction extends AbstractBaseAction {

    private IDeptServices deptService;
    private Dept dept;
    private List<Dept> deptList;
    private List<Serializable> ides;

    /**
     * @return the dept
     */
    public final Dept getDept() {
        return dept;
    }

    /**
     * @param dept
     *            the dept to set
     */
    public final void setDept(Dept dept) {
        this.dept = dept;
    }

    /**
     * @return the deptList
     */
    public final List<Dept> getDeptList() {
        return deptList;
    }

    /**
     * @param deptList
     *            the deptList to set
     */
    public final void setDeptList(List<Dept> deptList) {
        this.deptList = deptList;
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
     * @param deptService
     *            the deptService to set
     */
    @Autowired
    public final void setDeptService(IDeptServices deptService) {
        this.deptService = deptService;
    }

    public String addDept() {// add new dept
        try {
            Serializable id = deptService.save(dept);
            setResultCode((Integer) id);
        } catch (CommonServiceException e) {
            setMessage(e.getMessage());
            setResultCode(-1);
        }
        return Action.SUCCESS;
    }

    public String listDept() {// list all the dept
        try {
            deptList = deptService.listAll();
            setResultCode(1);
        } catch (CommonServiceException e) {
            setMessage(e.getMessage());
            setResultCode(-1);
        }
        return Action.SUCCESS;
    }

    public String updateDept() {// update the dept info
        try {
            int result = deptService.update(dept);
            setResultCode(result);
        } catch (CommonServiceException e) {
            setMessage(e.getMessage());
            setResultCode(-1);
        }
        return Action.SUCCESS;
    }

    public String deleteDept() {// delete the dept
        try {
            int result = deptService.delete(dept);
            setResultCode(result);
        } catch (CommonServiceException e) {
            setMessage(e.getMessage());
            setResultCode(-1);
        }
        return Action.SUCCESS;
    }

    public String batchDeleteDept() {// bath delete the dept
        try {
            int result = deptService.batchDelete(ides);
            setResultCode(result);
        } catch (CommonServiceException e) {
            setMessage(e.getMessage());
            setResultCode(-1);
        }
        return Action.SUCCESS;
    }
}
