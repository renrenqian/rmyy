package com.kevin.common.service.system.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;




import com.kevin.common.action.system.MessageAction;
import com.kevin.common.annotation.NoLog;
import com.kevin.common.constant.SystemConstant;
import com.kevin.common.dao.system.IMessageDAO;
import com.kevin.common.exception.BaseSqlMapException;
import com.kevin.common.exception.CommonServiceException;
import com.kevin.common.pojo.system.Message;
import com.kevin.common.service.AbstractBaseService;
import com.kevin.common.service.system.IMessageService;
import com.kevin.common.util.ServletActionContextUtil;



@NoLog
@Service("messageService")
public  class MessageServiceImpl extends AbstractBaseService<Message> implements
        IMessageService {
    private IMessageDAO newsDAO;
    @Autowired
    public final void setNewsDAO(IMessageDAO newsDAO) {
        this.newsDAO = newsDAO;
        setAbsBaseDao(newsDAO);
    }

    @Override
    public int deleteSMS(Long id) throws CommonServiceException {
        try {
            if (id == null) {
                return -1;
            }
            return newsDAO.delete(id);
        } catch (BaseSqlMapException e) {
            logger.error("删除短消息异常:" + e.getMessage());
            throw new CommonServiceException(e.getMessage());
        }
    }

    @Override
    public int deleteSMS(Message news) throws CommonServiceException {
        if (news == null) {
            return -1;
        }
        return deleteSMS(news.getMsgId());
    }

    @Override
    public List<Message> listSMS(Message news) throws CommonServiceException {
        try {
            if (null == news) {
                news = new Message();
            }
            news.setMsgReceiver(ServletActionContextUtil.getCurrentUserId());
            return newsDAO.list(news);
        } catch (BaseSqlMapException e) {
            logger.error("查看短消息列表异常:" + e.getMessage());
            throw new CommonServiceException(e.getMessage());
        }
    }

    @Override
    public Message readSMS(Long id) throws CommonServiceException {
        Message condition = new Message();
        condition.setMsgId(id);
        Message news = null;
        try {
            news = newsDAO.search(condition);
            if (news == null) {
                logger.error("短消息已丢失");
                throw new CommonServiceException("短消息已丢失");
            }
            // 修改相关属性,设置为已阅读(如果是新消息)
            if (news.getMsgRead().equals(SystemConstant.MSG_NEW)) {
                news.setMsgRead(SystemConstant.MSG_READ);// 设置为已阅读
                news.setMsgReadTime(new Date());// 设置阅读日期
                newsDAO.update(news);
            }
        } catch (BaseSqlMapException e) {
            logger.error("查看短消息异常:" + e.getMessage());
            throw new CommonServiceException(e.getMessage());
        }
        return news;

    }

    @Override
    public int sendSMS(Message news) {
        try {
            news.setMsgSendTime(new Date());// 设置发送日期
            news.setMsgSender(ServletActionContextUtil.getCurrentUserId());// 设置消息的发送者
            news.setMsgRead(SystemConstant.MSG_NEW);// 新消息
            Integer receiver = news.getMsgReceiver().intValue();
            Integer sender = news.getMsgSender().intValue();
            if (receiver == null || sender == null) {
                return -1;
            }
            newsDAO.save(news);
        } catch (Exception e) {
            logger.error("发送短消息异常:" + e.getMessage());
        }
        return 1;
    }

    @Override
    @NoLog
    public long isNewSMS() throws CommonServiceException {
        Integer uid = ServletActionContextUtil.getCurrentUserId();
        Message news = new Message();
        news.setMsgReceiver(uid);
        Long count;
        try {
            count = newsDAO.getNewMsgsCount(news);
            return count != null ? count : 0;
        } catch (BaseSqlMapException e) {
            throw new CommonServiceException(e.getMessage());
        }
    }

    @Override
    public int batchReadNews(Message news) throws CommonServiceException {
        try {
            // 修改相关属性,设置为已阅读(如果是新消息)
            news.setMsgRead(SystemConstant.MSG_READ);// 设置为已阅读
            news.setMsgReadTime(new Date());// 设置阅读日期
            String[] msIds = news.getMsgName().split(",");
            for(int i=0;i<msIds.length;i++){
                news.setMsgId(Long.valueOf(msIds[i]).longValue());
                newsDAO.update(news);
            }
        } catch (BaseSqlMapException e) {
            logger.error("查看短消息异常:" + e.getMessage());
            throw new CommonServiceException(e.getMessage());
        }
        return 0;
    }

    @Override
    public int batchReadNews(MessageAction news) throws CommonServiceException {
        // TODO Auto-generated method stub
        return 0;
    }
}