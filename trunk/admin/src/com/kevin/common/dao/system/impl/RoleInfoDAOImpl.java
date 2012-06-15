package com.kevin.common.dao.system.impl;

import java.sql.SQLException;
import java.util.List;

import org.springframework.stereotype.Component;

import com.kevin.common.dao.AbstractBaseDAO;
import com.kevin.common.dao.system.IRoleInfoDAO;
import com.kevin.common.exception.BaseSqlMapException;
import com.kevin.common.pojo.system.RoleInfo;

@Component("roleInfoDAO")
public class RoleInfoDAOImpl extends AbstractBaseDAO<RoleInfo> implements IRoleInfoDAO{

    @Override
    public List<RoleInfo> getAll() throws BaseSqlMapException {
        return super.list("listAll");
    }

    @Override
    public int batchDeleteRole(List<Integer> riList) throws BaseSqlMapException {
        try {
            getSqlMapClient().delete("RoleInfo.deleteList", riList);
        } catch (SQLException e) {
            logger.error("批量删除角色出错: " + e.getMessage());
            throw new BaseSqlMapException("批量删除角色出错！");
        }
        return 0;
    }

}
