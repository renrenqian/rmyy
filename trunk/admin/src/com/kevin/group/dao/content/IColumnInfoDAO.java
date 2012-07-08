/**
 * IColumnInfoDAO.java
 * kevin 2012-6-20
 * @version 0.1
 */
package com.kevin.group.dao.content;

import java.util.List;

import com.kevin.common.dao.IBaseDAO;
import com.kevin.common.exception.BaseSqlMapException;
import com.kevin.group.pojo.content.ColumnInfo;

/**
 * @author kevin
 * @since jdk1.6
 */
public interface IColumnInfoDAO extends IBaseDAO<ColumnInfo> {

    List<ColumnInfo> listColumnNames() throws BaseSqlMapException;

}
