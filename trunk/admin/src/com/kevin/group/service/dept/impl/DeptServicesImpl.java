/**
 * DeptServicesImpl.java
 * kevin 2012-6-16
 * @version 0.1
 */
package com.kevin.group.service.dept.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kevin.common.exception.BaseSqlMapException;
import com.kevin.common.exception.CommonServiceException;
import com.kevin.common.pojo.PageBean;
import com.kevin.common.service.AbstractBaseService;
import com.kevin.group.dao.dept.IDeptDAO;
import com.kevin.group.pojo.dept.Dept;
import com.kevin.group.service.dept.IDeptServices;

/**
 * @author kevin
 * @since jdk1.6
 */
@Service("deptService")
public class DeptServicesImpl extends AbstractBaseService<Dept> implements IDeptServices {

    private IDeptDAO deptDAO;

    /**
     * @param deptDAO the deptDAO to set
     */
    @Autowired
    public final void setDeptDAO(IDeptDAO deptDAO) {
        setAbsBaseDao(deptDAO);
        this.deptDAO = deptDAO;
    }

    @Override
    public PageBean<Dept> list(PageBean<Dept> page)
            throws CommonServiceException {
        return super.list(page);
    }

    @Override
    public List<Dept> listDeptNames() throws CommonServiceException   {
          try {
            return  deptDAO.listDeptNames();
        } catch (BaseSqlMapException e) {
            throw new CommonServiceException(e.getMessage());
        }
    }
}
