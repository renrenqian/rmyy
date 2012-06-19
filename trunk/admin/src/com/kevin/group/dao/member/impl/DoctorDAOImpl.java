/**
 * DoctorDAOImpl.java
 * kevin 2012-6-17
 * @version 0.1
 */
package com.kevin.group.dao.member.impl;

import org.springframework.stereotype.Component;

import com.kevin.common.dao.AbstractBaseDAO;
import com.kevin.group.dao.member.IDoctorDAO;
import com.kevin.group.pojo.member.DoctorInfo;

/**
 * @author kevin
 * @since jdk1.6
 */
@Component("doctorDAO")
public class DoctorDAOImpl extends AbstractBaseDAO<DoctorInfo> implements
        IDoctorDAO {

}