/**
 * DeptServices.java
 * kevin 2012-6-16
 * @version 0.1
 */
package com.kevin.group.service.dept;

import java.util.List;

import com.kevin.common.exception.CommonServiceException;
import com.kevin.common.service.IBaseService;
import com.kevin.group.pojo.dept.Dept;

/**
 * @author kevin
 * @since jdk1.6
 */
public interface IDeptServices extends IBaseService<Dept>{

    List<Dept> listDeptNames() throws CommonServiceException;

    int generateDeptJson(String savePath) throws CommonServiceException;

}
