package com.kevin.common.service.system;

import java.util.List;

import com.kevin.common.exception.CommonServiceException;
import com.kevin.common.pojo.system.PrivilegeInfo;
import com.kevin.common.pojo.system.RoleInfo;
import com.kevin.common.pojo.system.RolePrivilegeRef;
import com.kevin.common.service.IBaseService;

public interface IRoleInfoService  extends IBaseService<RoleInfo>{
    /************************************Role*************************************/
    int addRole(RoleInfo ri,List<RolePrivilegeRef> refList) throws CommonServiceException;
    int updateRole(RoleInfo ri,List<RolePrivilegeRef> refList) throws CommonServiceException;
    int deleteRole(RoleInfo ri) throws CommonServiceException;
    int bacthDeleteRole(List<RoleInfo> riList) throws CommonServiceException;
    /************************************Privilege*************************************/
    List<PrivilegeInfo> listPrivilege() throws CommonServiceException;
    List<PrivilegeInfo> findPrivilegeByRoleId(RoleInfo ri) throws CommonServiceException;
}
