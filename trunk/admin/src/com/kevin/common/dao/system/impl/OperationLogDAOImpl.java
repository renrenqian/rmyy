package com.kevin.common.dao.system.impl;

import org.springframework.stereotype.Component;

import com.kevin.common.dao.AbstractBaseDAO;
import com.kevin.common.dao.system.IOperationLogDAO;
import com.kevin.common.pojo.system.OperationLog;
@Component("operationLogDAO")
public class OperationLogDAOImpl extends AbstractBaseDAO<OperationLog> implements IOperationLogDAO{

}