/**
 * OPServiceDAOImpl.java
 * kevin 2012-6-27
 * @version 0.1
 */
package com.kevin.group.dao.member.impl;

import org.springframework.stereotype.Component;

import com.kevin.common.dao.AbstractBaseDAO;
import com.kevin.group.dao.member.IOPServiceDAO;
import com.kevin.group.pojo.member.OPSer;

/**
 * @author kevin
 * @since jdk1.6
 */
@Component("opsDAO")
public class OPServiceDAOImpl extends AbstractBaseDAO<OPSer> implements
        IOPServiceDAO {

}
