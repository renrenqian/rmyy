/**
 * ConsultationDAOImpl.java
 * kevin 2012-6-17
 * @version 0.1
 */
package com.kevin.group.dao.online.impl;

import org.springframework.stereotype.Component;

import com.kevin.common.dao.AbstractBaseDAO;
import com.kevin.group.dao.online.IConsultationDAO;
import com.kevin.group.pojo.online.Consultation;

/**
 * @author kevin
 * @since jdk1.6
 */
@Component("consultationDAO")
public class ConsultationDAOImpl extends AbstractBaseDAO<Consultation>
        implements IConsultationDAO {
 
}
