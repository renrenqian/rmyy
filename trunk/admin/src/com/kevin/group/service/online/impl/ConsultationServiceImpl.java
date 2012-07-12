/**
 * ConsultationServiceImpl.java
 * kevin 2012-6-17
 * @version 0.1
 */
package com.kevin.group.service.online.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kevin.common.exception.BaseSqlMapException;
import com.kevin.common.exception.CommonServiceException;
import com.kevin.common.pojo.PageBean;
import com.kevin.common.service.AbstractBaseService;
import com.kevin.group.dao.online.IConsultationDAO;
import com.kevin.group.pojo.online.Consultation;
import com.kevin.group.service.online.IConsultationService;

/**
 * @author kevin
 * @since jdk1.6
 */
@Service("consultationService")
public class ConsultationServiceImpl extends AbstractBaseService<Consultation>
        implements IConsultationService {

    private IConsultationDAO consultationDAO;

    /**
     * @param consultationDAO
     *            the consultationDAO to set
     */
    @Autowired
    public final void setConsultationDAO(IConsultationDAO consultationDAO) {
        setAbsBaseDao(consultationDAO);
        this.consultationDAO = consultationDAO;
    }

    @Override
    public PageBean<Consultation> list(PageBean<Consultation> page)
            throws CommonServiceException {
        return super.list(page);
    }

    /* (non-Javadoc)
     * @see com.kevin.group.service.online.IConsultationService#updateClickCons(com.kevin.group.pojo.online.Consultation)
     */
    @Override
    public int updateClickCons(Consultation cons) throws CommonServiceException {
        int clicks;
        try {
            clicks = consultationDAO.updateClickCons(cons);
        } catch (BaseSqlMapException e) {
            throw new CommonServiceException(e.getMessage());
        }
        return clicks;
    }
    
}
