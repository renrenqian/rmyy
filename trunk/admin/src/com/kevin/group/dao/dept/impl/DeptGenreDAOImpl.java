/**
 * DeptGenreDAOImpl.java
 * kevin 2012-7-5
 * @version 0.1
 */
package com.kevin.group.dao.dept.impl;

import org.springframework.stereotype.Component;

import com.kevin.common.dao.AbstractBaseDAO;
import com.kevin.group.dao.dept.IDeptGenreDAO;
import com.kevin.group.pojo.dept.DeptGenre;

/**
 * @author kevin
 * @since jdk1.6
 */
@Component("deptGenreDAO")
public class DeptGenreDAOImpl extends AbstractBaseDAO<DeptGenre> implements
        IDeptGenreDAO {

}
