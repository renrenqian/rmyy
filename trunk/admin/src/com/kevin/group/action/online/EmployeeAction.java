/**
 * EmployeeAction.java
 * kevin 2012-7-2
 * @version 0.1
 */
package com.kevin.group.action.online;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.kevin.common.action.AbstractBaseAction;
import com.kevin.common.exception.CommonServiceException;
import com.kevin.common.pojo.PageBean;
import com.kevin.group.constance.GroupConstance;
import com.kevin.group.pojo.online.Employee;
import com.kevin.group.service.online.IEmployeeService;
import com.opensymphony.xwork2.Action;

/**
 * @author kevin
 * @since jdk1.6
 */
@Component("empAction")
@Scope("prototype")
public class EmployeeAction extends AbstractBaseAction {

    private IEmployeeService empService;
    private Employee emp;
    private List<Employee> empList;
    private PageBean<Employee> page;
    private List<Serializable> ides;
    private File file;
    private String fileFileName;
    private String fileContentType;
    private String savePath;

    /**
     * @return the emp
     */
    public final Employee getEmp() {
        return emp;
    }

    /**
     * @param emp
     *            the emp to set
     */
    public final void setEmp(Employee emp) {
        this.emp = emp;
    }

    /**
     * @return the empList
     */
    public final List<Employee> getEmpList() {
        return empList;
    }

    /**
     * @param empList
     *            the empList to set
     */
    public final void setEmpList(List<Employee> empList) {
        this.empList = empList;
    }

    /**
     * @return the page
     */
    public final PageBean<Employee> getPage() {
        return page;
    }

    /**
     * @param page
     *            the page to set
     */
    public final void setPage(PageBean<Employee> page) {
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
     * @param empService
     *            the empService to set
     */
    @Autowired
    public final void setEmpService(IEmployeeService empService) {
        this.empService = empService;
    }

    /**
     * @return the file
     */
    public final File getFile() {
        return file;
    }

    /**
     * @param file
     *            the file to set
     */
    public final void setFile(File file) {
        this.file = file;
    }

    /**
     * @return the fileFileName
     */
    public final String getFileFileName() {
        return fileFileName;
    }

    /**
     * @param fileFileName
     *            the fileFileName to set
     */
    public final void setFileFileName(String fileFileName) {
        this.fileFileName = fileFileName;
    }

    /**
     * @return the fileContentType
     */
    public final String getFileContentType() {
        return fileContentType;
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
     * @param fileContentType
     *            the fileContentType to set
     */
    public final void setFileContentType(String fileContentType) {
        this.fileContentType = fileContentType;
    }

    public String addEmployee() {// add new cons
        try {
            if (null != file) {
                // make the parent folder when each month
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMM", Locale.ENGLISH);
                String monthlize = dateFormat.format(new Date());
                String realPath = ServletActionContext.getServletContext().getRealPath(this.getSavePath());
                File storeFolder = new File(realPath + File.separator + GroupConstance.UPLOAD_EMPLOY + File.separator + monthlize);
                //File storeFolder = new File("/upload/employee/" + monthlize);
                if(!storeFolder.exists()) storeFolder.mkdirs();
                File storeFile = new File(storeFolder, System.currentTimeMillis() + "_" + fileFileName);
                String storePath = storeFile.getAbsolutePath();
                emp.setErAttachement(storePath.substring(storePath.indexOf(GroupConstance.UPLOAD_ROOT)).replaceAll("\\", "/"));
                //storeFile.createNewFile();
                //FileUtils.copyFile(file[i], storeFile);
                FileUtils.moveFile(file, storeFile);
            }
            Serializable id = empService.save(emp);
            setResultCode((Integer) id);
        } catch (CommonServiceException e) {
            setMessage(e.getMessage());
            setResultCode(-1);
        } catch (IOException e) {
            setMessage(e.getMessage());
            setResultCode(-1);
        }
        return Action.SUCCESS;
    }

    public String searchEmployee() {// search emp
        try {
            this.emp = empService.search(emp);
            setResultCode(1);
        } catch (CommonServiceException e) {
            setMessage(e.getMessage());
            setResultCode(-1);
        }
        return Action.SUCCESS;
    }

    public String listEmployee() {// list all the emp
        try {
            if (page != null) {
                page.setCondition(emp);
            }
            page = empService.list(page);
            setResultCode(1);
        } catch (CommonServiceException e) {
            setMessage(e.getMessage());
            setResultCode(-1);
        }
        return Action.SUCCESS;
    }

    public String updateEmployee() {// update the emp info
        try {
            if (null != file) {
                // make the parent folder when each month
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMM", Locale.ENGLISH);
                String monthlize = dateFormat.format(new Date());
                String realPath = ServletActionContext.getServletContext().getRealPath(this.getSavePath());
                File storeFolder = new File(realPath + File.separator + GroupConstance.UPLOAD_EMPLOY + File.separator + monthlize);
                //File storeFolder = new File("/upload/employee/" + monthlize);
                if(!storeFolder.exists()) storeFolder.mkdirs();
                File storeFile = new File(storeFolder, System.currentTimeMillis() + "_" + fileFileName);
                String storePath = storeFile.getAbsolutePath();
                emp.setErAttachement(storePath.substring(storePath.indexOf(GroupConstance.UPLOAD_ROOT)).replaceAll("\\", "/"));
                //storeFile.createNewFile();
                //FileUtils.copyFile(file[i], storeFile);
                FileUtils.moveFile(file, storeFile);
            }
            int result = empService.update(emp);
            setResultCode(result);
        } catch (CommonServiceException e) {
            setMessage(e.getMessage());
            setResultCode(-1);
        } catch (IOException e) {
            setMessage(e.getMessage());
            setResultCode(-1);
        }
        return Action.SUCCESS;
    }

    public String deleteEmployee() {// delete the emp
        try {
            int result = empService.delete(emp);
            setResultCode(result);
        } catch (CommonServiceException e) {
            setMessage(e.getMessage());
            setResultCode(-1);
        }
        return Action.SUCCESS;
    }

    public String batchDeleteEmployee() {// bath delete the emp
        try {
            int result = empService.batchDelete(ides);
            setResultCode(result);
        } catch (CommonServiceException e) {
            setMessage(e.getMessage());
            setResultCode(-1);
        }
        return Action.SUCCESS;
    }
}
