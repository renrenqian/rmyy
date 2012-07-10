/**
 * ILeaderDAO.java
 * kevin 2012-6-17
 * @version 0.1
 */
package com.kevin.group.dao.member;

import java.util.List;

import com.kevin.common.dao.IBaseDAO;
import com.kevin.common.exception.BaseSqlMapException;
import com.kevin.group.pojo.member.LeaderInfo;

/**
 * @author kevin
 * @since jdk1.6
 */
public interface ILeaderDAO extends IBaseDAO<LeaderInfo> {

    List<LeaderInfo> listHistory(LeaderInfo leader) throws BaseSqlMapException;

}
