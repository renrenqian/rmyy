package com.kevin.common.dao.datadict.impl;

import org.springframework.stereotype.Component;

import com.kevin.common.dao.AbstractBaseDAO;
import com.kevin.common.dao.datadict.IDataDictDAO;
import com.kevin.common.pojo.system.DataDict;
 
@Component("dataDictDAO")
public class DataDictDAOImpl extends AbstractBaseDAO<DataDict> implements IDataDictDAO{

}
