package com.kevin.common.dao.system.impl;

import java.sql.SQLException;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.kevin.common.dao.AbstractBaseDAO;
import com.kevin.common.dao.system.IRolePrivilegeRefDAO;
import com.kevin.common.exception.BaseSqlMapException;
import com.kevin.common.pojo.system.PrivilegeInfo;
import com.kevin.common.pojo.system.RolePrivilegeRef;

@Repository("rolePrivilegeInfoDAO")
public class RolePrivilegeRefDAOImpl extends AbstractBaseDAO<RolePrivilegeRef>
        implements IRolePrivilegeRefDAO {

    @SuppressWarnings("unchecked")
    @Override
    public List<PrivilegeInfo> getPrivilegeListByRoleId(Integer roleId)
            throws BaseSqlMapException {
        List<PrivilegeInfo> privilegeList = null;
        String statement = nameSpace.concat(".").concat("getPrivilegeListByRoleId");
        try {
            privilegeList = getSqlMapClient().queryForList(statement, roleId);
        } catch (SQLException e) {
            logger.error("Exception while list statement(" + statement + "):"
                    + e.getMessage());
            throw new BaseSqlMapException("查询角色关联权限数据错误！");
        }
        return privilegeList;
    }

    @Override
    public int deleteRolePrivilegeRefByRoleId(Integer riId)
            throws BaseSqlMapException {
        return super.delete("deleteRolePrivilegeRefByRoleId", riId);
    }

    @Override
    public int saveRolePrivilege(List<RolePrivilegeRef> rpList)
            throws BaseSqlMapException {
        // 批量保存角色权限信息
        int result = 0;
        try {
            // 开始批处理
            getSqlMapClient().startBatch();
            for (RolePrivilegeRef ref : rpList) {
                // 插入操作
                getSqlMapClient().insert("RolePrivilegeRef.insert", ref);
                result++;
            }
            // 执行批处理
            getSqlMapClient().executeBatch();
        } catch (SQLException e) {
            logger.error("批量添加角色权限关联关系出错: " + e.getMessage());
            throw new BaseSqlMapException("批量添加角色权限关联关系出错！");
        }
        return result;
    }
}

