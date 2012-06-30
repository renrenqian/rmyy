/**
 * OPServiceServiceImpl.java
 * kevin 2012-6-27
 * @version 0.1
 */
package com.kevin.group.service.member.impl;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kevin.common.exception.BaseSqlMapException;
import com.kevin.common.exception.CommonServiceException;
import com.kevin.common.pojo.PageBean;
import com.kevin.common.service.AbstractBaseService;
import com.kevin.group.dao.member.IDoctorDAO;
import com.kevin.group.dao.member.IOPServiceDAO;
import com.kevin.group.pojo.member.DoctorInfo;
import com.kevin.group.pojo.member.OPSer;
import com.kevin.group.service.member.IOPSerService;

/**
 * @author kevin
 * @since jdk1.6
 */
@Service("opserService")
public class OPSerServiceImpl extends AbstractBaseService<OPSer> implements
        IOPSerService {

    private IOPServiceDAO opsDAO;
    private IDoctorDAO doctorDAO;

    /**
     * @param opsDAO
     *            the opsDAO to set
     */
    @Autowired
    public final void setOpsDAO(IOPServiceDAO opsDAO) {
        setAbsBaseDao(opsDAO);
        this.opsDAO = opsDAO;
    }

    /**
     * @param doctorDAO
     *            the doctorDAO to set
     */
    @Autowired
    public final void setDoctorDAO(IDoctorDAO doctorDAO) {
        this.doctorDAO = doctorDAO;
    }

    @Override
    public PageBean<OPSer> list(PageBean<OPSer> page)
            throws CommonServiceException {
        return super.list(page);
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.kevin.common.service.AbstractBaseService#save(java.lang.Object)
     */
    @Override
    public Serializable save(OPSer obj) throws CommonServiceException {
        int opsId = (Integer) super.save(obj);
        try {
            if (0 < opsId) {
                if (1 == obj.getOsCate()) {// add expert
                    DoctorInfo doci = new DoctorInfo();
                    doci.setDiId(obj.getDoctId());
                    doci.setOsExpertId(opsId);
                    doctorDAO.updateExpertId(doci);
                } else if (2 == obj.getOsCate()) {// add famouse
                    DoctorInfo doci = new DoctorInfo();
                    doci.setDiId(obj.getDoctId());
                    doci.setOsFamousId(opsId);
                    doctorDAO.updateFamourceId(doci);
                }
            }
        } catch (BaseSqlMapException e) {
            throw new CommonServiceException(e.getMessage());
        }
        return opsId;
    }
 
}
