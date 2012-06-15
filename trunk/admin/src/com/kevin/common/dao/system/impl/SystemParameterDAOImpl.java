/**
 * 
 */
package com.kevin.common.dao.system.impl;

import org.springframework.stereotype.Component;

import com.kevin.common.dao.AbstractBaseDAO;
import com.kevin.common.dao.system.ISystemParameterDAO;
import com.kevin.common.pojo.system.SystemParameter;
 
@Component("sysParameterDAO")
public class SystemParameterDAOImpl extends AbstractBaseDAO<SystemParameter>
        implements ISystemParameterDAO {

}
