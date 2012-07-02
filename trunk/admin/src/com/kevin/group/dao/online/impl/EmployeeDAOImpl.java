/**
 * EmployeeDAOImpl.java
 * kevin 2012-7-2
 * @version 0.1
 */
package com.kevin.group.dao.online.impl;

import org.springframework.stereotype.Component;

import com.kevin.common.dao.AbstractBaseDAO;
import com.kevin.group.dao.online.IEmployeeDAO;
import com.kevin.group.pojo.online.Employee;

/**
 * @author kevin
 * @since jdk1.6
 */
@Component("empDAO")
public class EmployeeDAOImpl extends AbstractBaseDAO<Employee> implements
        IEmployeeDAO {
 
}
