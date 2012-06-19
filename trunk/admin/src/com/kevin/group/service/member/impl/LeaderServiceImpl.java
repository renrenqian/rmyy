/**
 * ILeaderServiceImpl.java
 * kevin 2012-6-17
 * @version 0.1
 */
package com.kevin.group.service.member.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kevin.common.exception.CommonServiceException;
import com.kevin.common.pojo.PageBean;
import com.kevin.common.service.AbstractBaseService;
import com.kevin.group.dao.member.ILeaderDAO;
import com.kevin.group.pojo.member.LeaderInfo;
import com.kevin.group.service.member.ILeaderService;

/**
 * @author kevin
 * @since jdk1.6
 */
@Service("leaderService")
public class LeaderServiceImpl extends AbstractBaseService<LeaderInfo>
        implements ILeaderService {

    private ILeaderDAO leaderDAO;

    /**
     * @param leaderDAO
     *            the leaderDAO to set
     */
    @Autowired
    public final void setLeaderDAO(ILeaderDAO leaderDAO) {
        setAbsBaseDao(leaderDAO);
        this.leaderDAO = leaderDAO;
    }

    @Override
    public PageBean<LeaderInfo> list(PageBean<LeaderInfo> page)
            throws CommonServiceException {
        return super.list(page);
    }
}
