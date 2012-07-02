/**
 * EmployeeServiceImpl.java
 * kevin 2012-7-2
 * @version 0.1
 */
package com.kevin.group.service.online.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kevin.common.exception.CommonServiceException;
import com.kevin.common.pojo.PageBean;
import com.kevin.common.service.AbstractBaseService;
import com.kevin.group.dao.online.IEmployeeDAO;
import com.kevin.group.pojo.online.Employee;
import com.kevin.group.service.online.IEmployeeService;

/**
 * @author kevin
 * @since jdk1.6
 */
@Service("empService")
public class EmployeeServiceImpl extends AbstractBaseService<Employee>
        implements IEmployeeService {
 
    private IEmployeeDAO empDAO;

    /**
     * @param empDAO the empDAO to set
     */
    @Autowired
    public final void setEmpDAO(IEmployeeDAO empDAO) {
        setAbsBaseDao(empDAO);
        this.empDAO = empDAO;
    }

    @Override
    public PageBean<Employee> list(PageBean<Employee> page)
            throws CommonServiceException {
        return super.list(page);
    }
}
