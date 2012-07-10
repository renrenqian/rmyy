/**
 * LeaderDAOImpl.java
 * kevin 2012-6-17
 * @version 0.1
 */
package com.kevin.group.dao.member.impl;

import java.util.List;

import org.springframework.stereotype.Component;

import com.kevin.common.dao.AbstractBaseDAO;
import com.kevin.common.exception.BaseSqlMapException;
import com.kevin.group.dao.member.ILeaderDAO;
import com.kevin.group.pojo.member.LeaderInfo;

/**
 * @author kevin
 * @since jdk1.6
 */
@Component("leaderDAO")
public class LeaderDAOImpl extends AbstractBaseDAO<LeaderInfo> implements
        ILeaderDAO {

    /* (non-Javadoc)
     * @see com.kevin.group.dao.member.ILeaderDAO#listHistory(com.kevin.group.pojo.member.LeaderInfo)
     */
    @Override
    public List<LeaderInfo> listHistory(LeaderInfo leader) throws BaseSqlMapException {
        try {
            return super.list("listHistory", leader);
        } catch (BaseSqlMapException e) {
            throw new BaseSqlMapException(e.getMessage());
        }
    }
    
}
