package com.kevin.common.service.system.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kevin.common.dao.system.IPrivilegeInfoDAO;
import com.kevin.common.dao.system.IRoleInfoDAO;
import com.kevin.common.dao.system.IRolePrivilegeRefDAO;
import com.kevin.common.dao.system.IUserRoleRefDAO;
import com.kevin.common.exception.BaseSqlMapException;
import com.kevin.common.exception.CommonServiceException;
import com.kevin.common.pojo.system.PrivilegeInfo;
import com.kevin.common.pojo.system.RoleInfo;
import com.kevin.common.pojo.system.RolePrivilegeRef;
import com.kevin.common.service.AbstractBaseService;
import com.kevin.common.service.system.IRoleInfoService;

@Service("roleInfoService")
public class RoleInfoServiceImpl extends AbstractBaseService<RoleInfo>
        implements IRoleInfoService {
    private IPrivilegeInfoDAO privilegeDAO;
    private IRoleInfoDAO roleDAO;
    private IUserRoleRefDAO userRoleRefDAO;
    private IRolePrivilegeRefDAO rolePrivilegeDAO;

    /**
     * @param rolePrivilegeDAO
     *            the rolePrivilegeDAO to set
     */
    @Autowired
    public void setRolePrivilegeDAO(IRolePrivilegeRefDAO rolePrivilegeDAO) {
        this.rolePrivilegeDAO = rolePrivilegeDAO;
    }

    /**
     * @param userRoleRefDAO
     *            the userRoleRefDAO to set
     */
    @Autowired
    public void setUserRoleRefDAO(IUserRoleRefDAO userRoleRefDAO) {
        this.userRoleRefDAO = userRoleRefDAO;
    }

    /**
     * @param privilegeDAO
     *            the privilegeDAO to set
     */
    @Autowired
    public void setPrivilegeDAO(IPrivilegeInfoDAO privilegeDAO) {
        this.privilegeDAO = privilegeDAO;
    }

    /**
     * @param roleDAO
     *            the roleDAO to set
     */
    @Autowired
    public void setRoleDAO(IRoleInfoDAO roleDAO) {
        setAbsBaseDao(roleDAO);
        this.roleDAO = roleDAO;
    }

    /********************************* Method ***************************************/
    @Override
    public List<RoleInfo> listAll() throws CommonServiceException {
        List<RoleInfo> riList = null;
        try {
            riList = roleDAO.getAll();
        } catch (BaseSqlMapException e) {
            throw new CommonServiceException(e.getMessage());
        }
        return riList;
    }

    @Override
    public int deleteRole(RoleInfo ri) throws CommonServiceException {
        // 是否被使用判断
        int rel = 0;
        if (null == ri)
            throw new CommonServiceException("删除角色参数为空！");
        try {
            rel = userRoleRefDAO.findRoleCountByRIID(ri.getRiId());
            if (rel > 0)
                throw new CommonServiceException("角色正在被使用,请在用户管理中先删除关联！");
            // 删除角色
            roleDAO.delete(ri);
            // 删除角色权限关联
            rolePrivilegeDAO.deleteRolePrivilegeRefByRoleId(ri.getRiId());
        } catch (BaseSqlMapException e) {
            throw new CommonServiceException(e.getMessage());
        }
        return 0;
    }

    @Override
    public int addRole(RoleInfo ri, List<RolePrivilegeRef> refList)
            throws CommonServiceException {
        try {
            if (null == ri)
                throw new CommonServiceException("角色参数为空");
            // 判断唯一性
            RoleInfo existRole = roleDAO.search(ri);
            if (null != existRole)
                throw new CommonServiceException("角色名(" + ri.getRiName()
                        + ")已存在");
            // 增加角色
            roleDAO.save(ri);
            // 增加关联关系
            if (null == refList)
                throw new CommonServiceException("角色权限关联关系参数为空");
            ri = roleDAO.search(ri);
            for (RolePrivilegeRef ref : refList) {
                ref.setRoleInfo(ri);
            }
            rolePrivilegeDAO.saveRolePrivilege(refList);
        } catch (BaseSqlMapException e) {
            throw new CommonServiceException(e.getMessage());
        }
        return 0;
    }

    @Override
    public int updateRole(RoleInfo ri, List<RolePrivilegeRef> refList)
            throws CommonServiceException {
        // 判断唯一性
        RoleInfo existRole;
        try {
            if (null != ri) {
                existRole = roleDAO.search(ri);
                if (null != existRole
                        && existRole.getRiId().intValue() != ri.getRiId()
                                .intValue())
                    throw new CommonServiceException("角色名(" + ri.getRiName()
                            + ")已存在");
                // 更新角色
                roleDAO.update(ri);
            }
            if (null != refList) {
                // 更新角色关联的权限，先删除后添加
                rolePrivilegeDAO.deleteRolePrivilegeRefByRoleId(refList.get(0)
                        .getRoleInfo().getRiId());
                rolePrivilegeDAO.saveRolePrivilege(refList);
            }
        } catch (BaseSqlMapException e) {
            throw new CommonServiceException(e.getMessage());
        }
        return 0;
    }

    
    @Override
    public int bacthDeleteRole(List<RoleInfo> riList)
            throws CommonServiceException {
        List<Integer> riIdList = new ArrayList<Integer>();
        try {
            for (RoleInfo ri : riList) {
                int rel = userRoleRefDAO.findRoleCountByRIID(ri.getRiId());
                if (rel > 0)
                    throw new CommonServiceException("角色正在被使用,请在用户管理中先删除关联！");
                riIdList.add(ri.getRiId());
            }
            roleDAO.batchDeleteRole(riIdList);
        } catch (BaseSqlMapException e) {
            throw new CommonServiceException(e.getMessage());
        }
        return 0;
    }

    @Override
    public List<PrivilegeInfo> listPrivilege() throws CommonServiceException {
        List<PrivilegeInfo> piList = null;
        try {
            piList = privilegeDAO.listAll();
        } catch (BaseSqlMapException e) {
            throw new CommonServiceException(e.getMessage());
        }
        return piList;
    }

    @Override
    public List<PrivilegeInfo> findPrivilegeByRoleId(RoleInfo ri)
            throws CommonServiceException {
        List<PrivilegeInfo> piList = null;
        if (null == ri)
            throw new CommonServiceException("角色参数为空");
        try {
            piList = rolePrivilegeDAO.getPrivilegeListByRoleId(ri.getRiId());
        } catch (BaseSqlMapException e) {
            throw new CommonServiceException(e.getMessage());
        }
        return piList;
    }
}
