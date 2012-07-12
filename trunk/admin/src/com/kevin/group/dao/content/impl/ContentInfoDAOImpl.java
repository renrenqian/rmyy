/**
 * ContentInfoDAOImpl.java
 * kevin 2012-6-16
 * @version 0.1
 */
package com.kevin.group.dao.content.impl;

import java.util.List;

import org.springframework.stereotype.Component;

import com.kevin.common.dao.AbstractBaseDAO;
import com.kevin.common.exception.BaseSqlMapException;
import com.kevin.group.dao.content.IContentInfoDAO;
import com.kevin.group.pojo.content.ContentInfo;

/**
 * @author kevin
 * @since jdk1.6
 */
@Component("contInfoDAO")
public class ContentInfoDAOImpl extends AbstractBaseDAO<ContentInfo> implements
        IContentInfoDAO {

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.kevin.group.dao.content.IContentInfoDAO#auditorContent(com.kevin.
     * group.pojo.content.ContentInfo)
     */
    @Override
    public int auditorContent(ContentInfo continfo) throws BaseSqlMapException {
        int updateResult = 0;
        try {
            updateResult = super.update("auditorContent", continfo);
        } catch (BaseSqlMapException e) {
            throw new BaseSqlMapException(e.getMessage());
        }
        return updateResult;
    }

    /* (non-Javadoc)
     * @see com.kevin.group.dao.content.IContentInfoDAO#updateClickContent(com.kevin.group.pojo.content.ContentInfo)
     */
    @Override
    public int updateClickContent(ContentInfo continfo) throws BaseSqlMapException {
        int updateResult = 0;
        try {
            updateResult = super.update("updateClickContent", continfo);
        } catch (BaseSqlMapException e) {
            throw new BaseSqlMapException(e.getMessage());
        }
        return updateResult;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.kevin.group.dao.content.IContentInfoDAO#generateHomeJson()
     */
    @Override
    public List<ContentInfo> generateHomeyyggJson(ContentInfo continfo)
            throws BaseSqlMapException {
        List<ContentInfo> continfoList = null;
        try {
            continfoList = super.list("generateHomeyyggJson", continfo);
        } catch (BaseSqlMapException e) {
            throw new BaseSqlMapException(e.getMessage());
        }
        return continfoList;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.kevin.group.dao.content.IContentInfoDAO#generateHomeyyxwJson(com.
     * kevin.group.pojo.content.ContentInfo)
     */
    @Override
    public List<ContentInfo> generateHomeyyxwJson(ContentInfo continfo)
            throws BaseSqlMapException {
        List<ContentInfo> continfoList = null;
        try {
            continfoList = super.list("generateHomeyyxwJson", continfo);
        } catch (BaseSqlMapException e) {
            throw new BaseSqlMapException(e.getMessage());
        }
        return continfoList;
    }

}
