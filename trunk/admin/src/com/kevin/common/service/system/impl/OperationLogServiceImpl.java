package com.kevin.common.service.system.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kevin.common.annotation.NoLog;
import com.kevin.common.dao.system.IOperationLogDAO;
import com.kevin.common.exception.CommonServiceException;
import com.kevin.common.pojo.PageBean;
import com.kevin.common.pojo.system.OperationLog;
import com.kevin.common.service.AbstractBaseService;
import com.kevin.common.service.system.IOperationLogService;

@NoLog
@Service("operationLogService")
public class OperationLogServiceImpl extends AbstractBaseService<OperationLog> implements IOperationLogService{
    private IOperationLogDAO operationLogDAO;
    @Autowired
    public final void setOperationLogDAO(IOperationLogDAO operationLogDAO) {
        this.operationLogDAO = operationLogDAO;
        setAbsBaseDao(this.operationLogDAO);
    }
    @Override
    public PageBean<OperationLog> list(PageBean<OperationLog> page)
            throws CommonServiceException {
        return super.list(page);
    }
}
