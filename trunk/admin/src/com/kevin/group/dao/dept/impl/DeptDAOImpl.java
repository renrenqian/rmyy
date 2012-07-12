/**
 * DeptDAOImpl.java
 * kevin 2012-6-16
 * @version 0.1
 */
package com.kevin.group.dao.dept.impl;

import java.util.List;

import org.springframework.stereotype.Component;

import com.kevin.common.dao.AbstractBaseDAO;
import com.kevin.common.exception.BaseSqlMapException;
import com.kevin.group.dao.dept.IDeptDAO;
import com.kevin.group.pojo.dept.Dept;

/**
 * @author kevin
 * @since jdk1.6
 */
@Component("deptDAO")
public class DeptDAOImpl extends AbstractBaseDAO<Dept> implements IDeptDAO {

    @Override
    public List<Dept> listDeptNames() throws BaseSqlMapException {
        List<Dept> DeptNames = null;
        DeptNames = super.list("listDeptNames");
        return DeptNames;
    }

    /* (non-Javadoc)
     * @see com.kevin.group.dao.dept.IDeptDAO#listClinicalNames()
     */
    @Override
    public List<Dept> listClinicalNames() throws BaseSqlMapException {
        List<Dept> DeptNames = null;
        DeptNames = super.list("listClinicalNames");
        return DeptNames;
    }
    
}
