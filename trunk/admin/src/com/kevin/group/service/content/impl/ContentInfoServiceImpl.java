/**
 * ContentInfoServiceImpl.java
 * kevin 2012-6-16
 * @version 0.1
 */
package com.kevin.group.service.content.impl;

import java.io.Serializable;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kevin.common.exception.BaseSqlMapException;
import com.kevin.common.exception.CommonServiceException;
import com.kevin.common.pojo.PageBean;
import com.kevin.common.service.AbstractBaseService;
import com.kevin.common.service.system.IUserInfoService;
import com.kevin.common.service.system.OnlineUser;
import com.kevin.common.util.ServletActionContextUtil;
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

    /* (non-Javadoc)
     * @see com.kevin.common.service.AbstractBaseService#save(java.lang.Object)
     */
    @Override
    public Serializable save(ContentInfo cont) throws CommonServiceException {
        HttpServletRequest request = ServletActionContextUtil.getRequest();
        HttpSession session = request.getSession(false);
        //session.getId();
        OnlineUser onlineUser = IUserInfoService.ONLINEUSERMAP.get(session.getId());
        if(null == onlineUser){
            cont.setContAuthor(0);
        } else {
            cont.setContAuthor(onlineUser.getUserInfo().getUiId());
        }
        return super.save(cont);
    }

    /* (non-Javadoc)
     * @see com.kevin.group.service.content.IContentInfoService#auditorContent(com.kevin.group.pojo.content.ContentInfo)
     */
    @Override
    public int auditorContent(ContentInfo continfo) throws CommonServiceException {
        try {
            return contInfoDAO.auditorContent(continfo);
        } catch (BaseSqlMapException e) {
            throw new CommonServiceException(e.getMessage());
        }
    }
}
