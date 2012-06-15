/**
 * UserInfoDAOImpl.java
 */
package com.kevin.common.dao.system.impl;

import java.util.List;

import org.springframework.stereotype.Component;

import com.kevin.common.dao.AbstractBaseDAO;
import com.kevin.common.dao.system.IUserInfoDAO;
import com.kevin.common.exception.BaseSqlMapException;
import com.kevin.common.pojo.system.UserInfo;
 
@Component("userInfoDAO")
public class UserInfoDAOImpl extends AbstractBaseDAO<UserInfo> implements IUserInfoDAO {

    @Override
    public java.io.Serializable saveUserCpRef(UserInfo user) throws BaseSqlMapException {
        return super.save("insertUserCpRef", user);
    }

    

    @Override
    public java.io.Serializable saveUserPubRef(UserInfo user) throws BaseSqlMapException {
        return super.save("insertUserPubRef", user);
    }

    @Override
    public int deleteUserPubRef(UserInfo user) throws BaseSqlMapException {
        return super.delete("deleteUserPubRef", user);
    }

    @Override
    public List<UserInfo> listUserByCp(UserInfo user)
            throws BaseSqlMapException {
        return list("listUserByCp", user);
    }

    @Override
    public List<UserInfo> listUserByPubConfig(UserInfo user)
            throws BaseSqlMapException {
//        return list("listUserByPC", user);
        return null;
    }
    
}