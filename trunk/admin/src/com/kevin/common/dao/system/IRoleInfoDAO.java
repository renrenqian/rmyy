package com.kevin.common.dao.system;

import java.util.List;

import com.kevin.common.dao.IBaseDAO;
import com.kevin.common.exception.BaseSqlMapException;
import com.kevin.common.pojo.system.RoleInfo;

public interface IRoleInfoDAO  extends IBaseDAO<RoleInfo>{
    /**
     * 获取全部数据
     * @return
     * @throws BaseSqlMapException
     */
    public List<RoleInfo> getAll() throws BaseSqlMapException;
    public int batchDeleteRole(List<Integer> riList) throws BaseSqlMapException;
}
