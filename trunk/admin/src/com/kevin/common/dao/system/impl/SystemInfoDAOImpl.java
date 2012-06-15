/**
 * SystemInfoDAOImpl.java
 */
package com.kevin.common.dao.system.impl;

import org.springframework.stereotype.Repository;

import com.kevin.common.dao.AbstractBaseDAO;
import com.kevin.common.dao.system.ISystemInfoDAO;
import com.kevin.common.pojo.system.SystemInfo;
/**
 * 系统参数DAO
 * @author jiangyaniu
 *
 */
@Repository("systemInfoDAO")
public class SystemInfoDAOImpl extends AbstractBaseDAO<SystemInfo> implements ISystemInfoDAO {

}
