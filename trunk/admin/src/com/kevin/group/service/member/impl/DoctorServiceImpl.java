/**
 * IDoctorServiceImpl.java
 * kevin 2012-6-17
 * @version 0.1
 */
package com.kevin.group.service.member.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kevin.common.exception.CommonServiceException;
import com.kevin.common.pojo.PageBean;
import com.kevin.common.service.AbstractBaseService;
import com.kevin.group.dao.member.IDoctorDAO;
import com.kevin.group.pojo.member.DoctorInfo;
import com.kevin.group.service.member.IDoctorService;

/**
 * @author kevin
 * @since jdk1.6
 */
@Service("doctorService")
public class DoctorServiceImpl extends AbstractBaseService<DoctorInfo> implements
        IDoctorService {
     private IDoctorDAO  doctorDAO;

    /**
     * @param doctorDAO the doctorDAO to set
     */
     @Autowired
    public final void setDoctorDAO(IDoctorDAO doctorDAO) {
         setAbsBaseDao(doctorDAO);
        this.doctorDAO = doctorDAO;
    }

     @Override
     public PageBean<DoctorInfo> list(PageBean<DoctorInfo> page)
             throws CommonServiceException {
         return super.list(page);
     }
}
