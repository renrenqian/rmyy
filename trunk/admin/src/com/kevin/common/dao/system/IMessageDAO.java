package com.kevin.common.dao.system;

import com.kevin.common.dao.IBaseDAO;
import com.kevin.common.exception.BaseSqlMapException;
import com.kevin.common.pojo.system.Message;



public interface IMessageDAO extends IBaseDAO<Message> {
     Long getNewMsgsCount(Message msg)throws BaseSqlMapException;
     int batchReadNews(Message news)throws BaseSqlMapException;
}
