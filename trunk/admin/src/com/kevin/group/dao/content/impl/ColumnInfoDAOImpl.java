/**
 * ColumnInfoDAOImpl.java
 * kevin 2012-6-20
 * @version 0.1
 */
package com.kevin.group.dao.content.impl;

import java.util.List;

import org.springframework.stereotype.Component;

import com.kevin.common.dao.AbstractBaseDAO;
import com.kevin.common.exception.BaseSqlMapException;
import com.kevin.group.dao.content.IColumnInfoDAO;
import com.kevin.group.pojo.content.ColumnInfo;

/**
 * @author kevin
 * @since jdk1.6
 */
@Component("colInfoDAO")
public class ColumnInfoDAOImpl extends AbstractBaseDAO<ColumnInfo> implements
        IColumnInfoDAO {

    @Override
    public List<ColumnInfo> listColumnNames() throws BaseSqlMapException {
        List<ColumnInfo> colList = null;
        try {
            colList = super.list("listColumnNames");
        } catch (BaseSqlMapException e) {
            throw new BaseSqlMapException(e.getMessage());
        }
        return colList;
    }

}
