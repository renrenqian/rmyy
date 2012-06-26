/**
 * IDeptDAO.java
 * kevin 2012-6-16
 * @version 0.1
 */
package com.kevin.group.dao.dept;

import java.util.List;

import com.kevin.common.dao.IBaseDAO;
import com.kevin.common.exception.BaseSqlMapException;
import com.kevin.group.pojo.dept.Dept;

/**
 * @author kevin
 * @since jdk1.6
 */
public interface IDeptDAO extends IBaseDAO<Dept> {

    List<Dept> listDeptNames() throws BaseSqlMapException;

}
