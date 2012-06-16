/**
 * DeptDAOImpl.java
 * kevin 2012-6-16
 * @version 0.1
 */
package com.kevin.group.dao.dept.impl;

import org.springframework.stereotype.Component;

import com.kevin.common.dao.AbstractBaseDAO;
import com.kevin.group.dao.dept.IDeptDAO;
import com.kevin.group.pojo.dept.Dept;

/**
 * @author kevin
 * @since jdk1.6
 */
@Component("deptDAO")
public class DeptDAOImpl extends AbstractBaseDAO<Dept> implements IDeptDAO {
 
}
