/**
 * DeptInfoAction.java
 * kevin 2012-6-16
 * @version 0.1
 */
package com.kevin.group.action.dept;

import java.io.File;
import java.io.Serializable;
import java.util.List;

import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.kevin.common.action.AbstractBaseAction;
import com.kevin.common.exception.CommonServiceException;
import com.kevin.common.pojo.PageBean;
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
    private PageBean<Dept> page;
    private List<Serializable> ides;
    private String savePath;

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
     * @return the page
     */
    public final PageBean<Dept> getPage() {
        return page;
    }

    /**
     * @param page the page to set
     */
    public final void setPage(PageBean<Dept> page) {
        this.page = page;
    }

    /**
     * @return the savePath
     */
    public final String getSavePath() {
        return savePath;
    }

    /**
     * @param savePath the savePath to set
     */
    public final void setSavePath(String savePath) {
        this.savePath = savePath;
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
            dept.setDpAcademic_position(dept.getDpAcademic_position().replaceAll("#$", "%"));
            dept.setDpDesc(dept.getDpDesc().replaceAll("#$", "%"));
            dept.setDpResearch_direction(dept.getDpResearch_direction().replaceAll("#$", "%"));
            dept.setDpTech_adv(dept.getDpTech_adv().replaceAll("#$", "%"));
            Serializable id = deptService.save(dept);
            setResultCode((Integer) id);
        } catch (CommonServiceException e) {
            setMessage(e.getMessage());
            setResultCode(-1);
        }
        return Action.SUCCESS;
    }

    public String searchDept() {// search dept
        try {
            this.dept = deptService.search(dept);
            setResultCode(1);
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

    public String listDeptPage() {// list all the dept by paginate
        try {
            if(null != page)
                page.setCondition(dept);
            page = deptService.list(page);
            setResultCode(1);
        } catch (CommonServiceException e) {
            setMessage(e.getMessage());
            setResultCode(-1);
        }
        return Action.SUCCESS;
    }
    
    public String listDeptNames() {// list all the dept
        try {
            deptList = deptService.listDeptNames();
            setResultCode(1);
        } catch (CommonServiceException e) {
            setMessage(e.getMessage());
            setResultCode(-1);
        }
        return Action.SUCCESS;
    }
    
    public String listClinicalNames() {// list all the dept for web
        try {
            deptList = deptService.listClinicalNames();
            setResultCode(1);
        } catch (CommonServiceException e) {
            setMessage(e.getMessage());
            setResultCode(-1);
        }
        return Action.SUCCESS;
    }

    public String updateDept() {// update the dept info
        try {
            dept.setDpAcademic_position(dept.getDpAcademic_position().replaceAll("#$", "%"));
            dept.setDpDesc(dept.getDpDesc().replaceAll("#$", "%"));
            dept.setDpResearch_direction(dept.getDpResearch_direction().replaceAll("#$", "%"));
            dept.setDpTech_adv(dept.getDpTech_adv().replaceAll("#$", "%"));
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
    
    public String generateDeptJson() {// generate json format for the depts
        try {
            String realPath = ServletActionContext.getServletContext().getRealPath(this.getSavePath());
            File storeFolder = new File(realPath);
            if (!storeFolder.exists())
                storeFolder.mkdirs();
            int result = deptService.generateDeptJson(storeFolder.getAbsolutePath());
            setResultCode(result);
        } catch (CommonServiceException e) {
            setMessage(e.getMessage());
            setResultCode(-1);
        }
        return Action.SUCCESS;
    }
}
