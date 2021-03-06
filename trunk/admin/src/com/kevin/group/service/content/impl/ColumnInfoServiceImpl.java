/**
 * ColumnInfoServiceImpl.java
 * kevin 2012-6-20
 * @version 0.1
 */
package com.kevin.group.service.content.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kevin.common.exception.BaseSqlMapException;
import com.kevin.common.exception.CommonServiceException;
import com.kevin.common.pojo.PageBean;
import com.kevin.common.service.AbstractBaseService;
import com.kevin.group.dao.content.IColumnInfoDAO;
import com.kevin.group.pojo.content.ColumnInfo;
import com.kevin.group.service.content.IColumnInfoService;

/**
 * @author kevin
 * @since jdk1.6
 */
@Service("colInfoService")
public class ColumnInfoServiceImpl extends AbstractBaseService<ColumnInfo>
        implements IColumnInfoService {

    private IColumnInfoDAO contInfoDAO;

    /**
     * @param contInfoDAO
     *            the contInfoDAO to set
     */
    @Autowired
    public final void setContInfoDAO(IColumnInfoDAO contInfoDAO) {
        setAbsBaseDao(contInfoDAO);
        this.contInfoDAO = contInfoDAO;
    }

    @Override
    public PageBean<ColumnInfo> list(PageBean<ColumnInfo> page)
            throws CommonServiceException {
        return super.list(page);
    }

    /* (non-Javadoc)
     * @see com.kevin.group.service.content.IColumnInfoService#listColumnNames()
     */
    @Override
    public List<ColumnInfo> listColumnNames() throws CommonServiceException {
        try {
            return contInfoDAO.listColumnNames();
        } catch (BaseSqlMapException e) {
            throw new CommonServiceException(e.getMessage());
        }
    }
}
