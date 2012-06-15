package com.kevin.common.service.system;

import java.util.List;

import com.kevin.common.action.system.MessageAction;
import com.kevin.common.exception.CommonServiceException;
import com.kevin.common.pojo.system.Message;
import com.kevin.common.service.IBaseService;


public interface IMessageService extends IBaseService<Message>{
    public int sendSMS(Message news);
    public Message readSMS(Long id)throws CommonServiceException;
    public int deleteSMS(Long id) throws CommonServiceException;
    public int deleteSMS(Message news) throws CommonServiceException;
    public List<Message> listSMS(Message news)throws CommonServiceException;
    public long isNewSMS()throws CommonServiceException;
public int batchReadNews(MessageAction news)throws CommonServiceException;
int batchReadNews(Message news) throws CommonServiceException;
    
}
