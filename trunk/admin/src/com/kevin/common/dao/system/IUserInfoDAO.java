/**
 * IUserInfoDAO.java
 */
package com.kevin.common.dao.system;

import java.util.List;

import com.kevin.common.dao.IBaseDAO;
import com.kevin.common.exception.BaseSqlMapException;
import com.kevin.common.pojo.system.UserInfo;
/**
 * 用户管理数据访问层接口
 * @author jiangyn
 *
 */
public interface IUserInfoDAO extends IBaseDAO<UserInfo> {
    /**
     * 保存用户与CP关联关系
     * @param user
     * @return
     * @throws BaseSqlMapException
     */
    java.io.Serializable saveUserCpRef(UserInfo user)throws BaseSqlMapException;
    
    /**
     * 保存用户与发布关联关系
     * @param user
     * @return
     * @throws BaseSqlMapException
     */
    java.io.Serializable saveUserPubRef(UserInfo user)throws BaseSqlMapException;
    /**
     * 删除用户与发布关联关系
     * @param user
     * @return
     * @throws BaseSqlMapException
     */
    int deleteUserPubRef(UserInfo user)throws BaseSqlMapException;
    /**
     * 根据CP查用户
     * @param user
     * @return
     * @throws BaseSqlMapException
     */
    List<UserInfo> listUserByCp(UserInfo user)throws BaseSqlMapException;
    List<UserInfo> listUserByPubConfig(UserInfo user)throws BaseSqlMapException;
}