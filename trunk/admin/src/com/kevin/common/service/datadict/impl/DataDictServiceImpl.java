package com.kevin.common.service.datadict.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kevin.common.dao.datadict.IDataDictDAO;
import com.kevin.common.pojo.system.DataDict;
import com.kevin.common.service.AbstractBaseService;
import com.kevin.common.service.datadict.IDataDictService;

@Service("dataDictService")
public class DataDictServiceImpl  extends AbstractBaseService<DataDict> implements IDataDictService{
private IDataDictDAO dataDictDAO;

/**
 * @param dataDictDAO the dataDictDAO to set
 */
@Autowired
public void setDataDictDAO(IDataDictDAO dataDictDAO) {
    setAbsBaseDao(dataDictDAO);
    this.dataDictDAO = dataDictDAO;
}
}
