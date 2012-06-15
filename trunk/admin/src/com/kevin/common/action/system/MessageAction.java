package com.kevin.common.action.system;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.kevin.common.action.AbstractBaseAction;
import com.kevin.common.exception.CommonServiceException;
import com.kevin.common.pojo.system.Message;
import com.kevin.common.service.system.IMessageService;

import com.opensymphony.xwork2.Action;

@Controller("messageAction")
@Scope("prototype")
public class MessageAction extends AbstractBaseAction {
    private Message news;
    private List<Serializable> idList;
    private List<Message> newsList;
    private IMessageService newsService;
    private long newSmsCount;
     @Autowired
        public final void setNewsService(IMessageService newsService) {
            this.newsService = newsService;
        }
        public final long getNewSmsCount() {
            return newSmsCount;
        }

        public final void setNewSmsCount(long newSmsCount) {
            this.newSmsCount = newSmsCount;
        }

        public final Message getNews() {
            return news;
        }

        public final void setNews(Message news) {
            this.news = news;
        }

        public final List<Serializable> getIdList() {
            return idList;
        }

        public final void setIdList(List<Serializable> idList) {
            this.idList = idList;
        }

        public final List<Message> getNewsList() {
            return newsList;
        }

        public final void setNewsList(List<Message> newsList) {
            this.newsList = newsList;
        }
        
        public String readNews(){
            try {
                newsService.readSMS(news.getMsgId());
                this.resultCode = 1;
            } catch (CommonServiceException e) {
                setMessage(e.getMessage());
                this.resultCode = -1;
            }
            return Action.SUCCESS;
        }
        
        public String listNews(){
            try {
                newsList = newsService.listSMS(news);
                this.resultCode = 1;
            } catch (CommonServiceException e) {
                setMessage(e.getMessage());
                this.resultCode = -1;
            }
            return Action.SUCCESS;
        }
        
        public String haveNewSms(){
            try {
                newSmsCount = newsService.isNewSMS();
                this.resultCode = 1;
            } catch (CommonServiceException e) {
                setMessage(e.getMessage());
                this.resultCode = -1;
            }
            return Action.SUCCESS;
        }
        
        public String deleteNews(){
            try {
                this.resultCode = newsService.deleteSMS(news.getMsgId());
            } catch (CommonServiceException e) {
                this.resultCode = -1;
                setMessage(e.getMessage());
            }
            return Action.SUCCESS;
        }
        
        public String batchDeleteNews(){
            try {
                this.resultCode = newsService.batchDelete(idList);
            } catch (CommonServiceException e) {
                this.resultCode = -1;
                setMessage(e.getMessage());
            }
            return Action.SUCCESS;
        }
        public String batchReadNews(){
            try {
                this.resultCode = newsService.batchReadNews(news);
            } catch (CommonServiceException e) {
                this.resultCode = -1;
                setMessage(e.getMessage());
            }
            return Action.SUCCESS;
        }
    }


