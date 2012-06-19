/**
 * ColumnInfoDAOImpl.java
 * kevin 2012-6-20
 * @version 0.1
 */
package com.kevin.group.dao.content.impl;

import org.springframework.stereotype.Component;

import com.kevin.common.dao.AbstractBaseDAO;
import com.kevin.group.dao.content.IColumnInfoDAO;
import com.kevin.group.pojo.content.ColumnInfo;

/**
 * @author kevin
 * @since jdk1.6
 */
@Component("colInfoDAO")
public class ColumnInfoDAOImpl extends AbstractBaseDAO<ColumnInfo> implements
        IColumnInfoDAO {

}
