package com.kevin.common.dao.system.impl;

import java.util.List;

import org.springframework.stereotype.Component;

import com.kevin.common.dao.AbstractBaseDAO;
import com.kevin.common.dao.system.IPrivilegeInfoDAO;
import com.kevin.common.exception.BaseSqlMapException;
import com.kevin.common.pojo.system.PrivilegeInfo;

@Component("privilegeInfoDAO")
public class PrivilegeInfoDAOImpl extends AbstractBaseDAO<PrivilegeInfo>
implements IPrivilegeInfoDAO {

    @Override
    public List<PrivilegeInfo> getAll() throws BaseSqlMapException {
        List<PrivilegeInfo> temp;
        temp=super.list("listAll");
        return temp;
    }

}
