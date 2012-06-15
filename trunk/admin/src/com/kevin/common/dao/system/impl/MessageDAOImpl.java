package com.kevin.common.dao.system.impl;

import org.springframework.stereotype.Repository;

import com.kevin.common.dao.AbstractBaseDAO;
import com.kevin.common.dao.system.IMessageDAO;
import com.kevin.common.exception.BaseSqlMapException;
import com.kevin.common.pojo.system.Message;



@Repository("messageDAO")
public class MessageDAOImpl extends AbstractBaseDAO<Message> implements IMessageDAO {

    @Override
    public Long getNewMsgsCount(Message news) throws BaseSqlMapException {
        try {
            return super.getTotalCount("getNewMsgCount", news);
        } catch (BaseSqlMapException e) {
            throw  e;
        }
    }

    @Override
    public int batchReadNews(Message news) throws BaseSqlMapException {
        try {
            return super.update("batchUpdate", news);
        } catch (BaseSqlMapException e) {
            throw  e;
        }
    }
}