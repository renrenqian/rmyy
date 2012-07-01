/**
 * EmployeeAction.java
 * kevin 2012-7-2
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

    public String addEmployee() {// add new cons
        try {
            Serializable id = empService.save(emp);
            setResultCode((Integer) id);
        } catch (CommonServiceException e) {
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
            int result = empService.update(emp);
            setResultCode(result);
        } catch (CommonServiceException e) {
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
