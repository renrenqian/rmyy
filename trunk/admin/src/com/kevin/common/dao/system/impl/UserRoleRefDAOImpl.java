/**
 * UserRoleRefDAOImpl.java
 */
package com.kevin.common.dao.system.impl;

import java.sql.SQLException;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.kevin.common.dao.AbstractBaseDAO;
import com.kevin.common.dao.system.IUserRoleRefDAO;
import com.kevin.common.exception.BaseSqlMapException;
import com.kevin.common.pojo.system.UserRoleRef;
 
@Repository("userRoleRefDAO")
public class UserRoleRefDAOImpl extends AbstractBaseDAO<UserRoleRef> implements
        IUserRoleRefDAO {

    @SuppressWarnings("unchecked")
    @Override
    public List<UserRoleRef> getRoleListByUserId(Integer userId)
            throws BaseSqlMapException {
        List<UserRoleRef> roleList = null;
        String statement = nameSpace.concat(".").concat("getRoleListByUserId");
        try {
            roleList = getSqlMapClient().queryForList(statement, userId);
        } catch (SQLException e) {
            logger.error("Exception while list statement(" + statement + "):"
                    + e.getMessage());
            throw new BaseSqlMapException("查询用户关联角色数据错误！");
        }
        return roleList;
    }

    @Override
    public int deleteUserRoleRefByUserId(Integer uid)
            throws BaseSqlMapException {
        return super.delete("deleteUserRoleRefByUserId", uid);
    }

    @Override
    public int saveUserRole(List<UserRoleRef> userRoleRefList)
            throws BaseSqlMapException {
        // 批量保存角色用户信息
        int result = 0;
        try {
            // 开始批处理
            getSqlMapClient().startBatch();
            for (UserRoleRef ref : userRoleRefList) {
                // 插入操作
                getSqlMapClient().insert("UserRoleRef.insert", ref);
                result++;
            }
            // 执行批处理
            getSqlMapClient().executeBatch();
        } catch (SQLException e) {
            logger.error("批量添加角色用户关联关系出错: " + e.getMessage());
            throw new BaseSqlMapException("批量添加角色用户关联关系出错！");
        }
        return result;
    }

    @Override
    public int findRoleCountByRIID(Integer riId) throws BaseSqlMapException {
        int rel = 0;
        if(null == riId)
            throw new BaseSqlMapException("通过角色ID查询该角色被使用次数参数为空！");
        try {
            rel = (Integer)getSqlMapClient().queryForObject("UserRoleRef.listRoleCount",riId);
        } catch (SQLException e) {
            logger.error("通过角色ID查询该角色被使用次数出错: " + e.getMessage());
            throw new BaseSqlMapException("通过角色ID查询该角色被使用次数出错！");
        }
        return rel;
    }

}
