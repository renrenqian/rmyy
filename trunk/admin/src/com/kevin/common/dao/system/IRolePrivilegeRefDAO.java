package com.kevin.common.dao.system;

import java.util.List;

import com.kevin.common.dao.IBaseDAO;
import com.kevin.common.exception.BaseSqlMapException;
import com.kevin.common.pojo.system.PrivilegeInfo;
import com.kevin.common.pojo.system.RolePrivilegeRef;

public interface IRolePrivilegeRefDAO extends IBaseDAO<RolePrivilegeRef>{
     List<PrivilegeInfo> getPrivilegeListByRoleId(Integer roleId)throws BaseSqlMapException;
      int deleteRolePrivilegeRefByRoleId(Integer riId)throws BaseSqlMapException;
      int saveRolePrivilege(List<RolePrivilegeRef> rpList)throws BaseSqlMapException;
}
