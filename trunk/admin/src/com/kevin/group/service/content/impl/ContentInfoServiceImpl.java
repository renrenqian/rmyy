/**
 * ContentInfoServiceImpl.java
 * kevin 2012-6-16
 * @version 0.1
 */
package com.kevin.group.service.content.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kevin.common.exception.CommonServiceException;
import com.kevin.common.pojo.PageBean;
import com.kevin.common.service.AbstractBaseService;
import com.kevin.group.dao.content.IContentInfoDAO;
import com.kevin.group.pojo.content.ContentInfo;
import com.kevin.group.service.content.IContentInfoService;

/**
 * @author kevin
 * @since jdk1.6
 */
@Service("contInfoService")
public class ContentInfoServiceImpl extends AbstractBaseService<ContentInfo>
        implements IContentInfoService {

    private IContentInfoDAO contInfoDAO;

    /**
     * @param contInfoDAO the contInfoDAO to set
     */
    @Autowired
    public final void setContInfoDAO(IContentInfoDAO contInfoDAO) {
        setAbsBaseDao(contInfoDAO);
        this.contInfoDAO = contInfoDAO;
    }

    @Override
    public PageBean<ContentInfo> list(PageBean<ContentInfo> page)
            throws CommonServiceException {
        return super.list(page);
    }
}
