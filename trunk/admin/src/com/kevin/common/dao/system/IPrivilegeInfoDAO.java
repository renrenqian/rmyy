package com.kevin.common.dao.system;

import java.util.List;

import com.kevin.common.dao.IBaseDAO;
import com.kevin.common.exception.BaseSqlMapException;
import com.kevin.common.pojo.system.PrivilegeInfo;

public interface IPrivilegeInfoDAO extends IBaseDAO<PrivilegeInfo>{
    /**
     * 获取全部数据
     * @return
     * @throws BaseSqlMapException
     */
    public List<PrivilegeInfo> getAll() throws BaseSqlMapException;
}
