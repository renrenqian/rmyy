package com.kevin.common.pojo.system;

import java.io.Serializable;
import java.util.Date;

import com.googlecode.jsonplugin.annotations.JSON;



public class Message implements Serializable{
    private static final long serialVersionUID = -877870460530146400L;
    /**
     * 默认构造方法
     */
    public Message() {
        super();
    }
    private Long msgId;
    private String msgName;
    private String msgContent;
    private Integer msgSender;
    private String senderName;
    private Integer msgReceiver;
    private Integer msgRead;
    private Date msgSendTime;
    private Date msgReadTime;
    public Integer getMsgRead() {
        return msgRead;
    }
    public void setMsgRead(Integer msgRead) {
        this.msgRead = msgRead;
    }
    public void setMsgReadTime(Date msgReadTime) {
        this.msgReadTime = msgReadTime;
    }
    public final Long getMsgId() {
        return msgId;
    }
    public final void setMsgId(Long msgId) {
        this.msgId = msgId;
    }
    public final String getMsgName() {
        return msgName;
    }
    public final void setMsgName(String msgName) {
        this.msgName = msgName;
    }
    public final String getMsgContent() {
        return msgContent;
    }
    public final void setMsgContent(String msgContent) {
        this.msgContent = msgContent;
    }
    public final Integer getMsgSender() {
        return msgSender;
    }
    public final void setMsgSender(Integer msgSender) {
        this.msgSender = msgSender;
    }
    public final Integer getMsgReceiver() {
        return msgReceiver;
    }
    public final void setMsgReceiver(Integer msgReceiver) {
        this.msgReceiver = msgReceiver;
    }
    @JSON(format="yyyy-MM-dd HH:mm:ss")
    public Date getMsgSendTime() {
        return msgSendTime;
    }
    public void setMsgSendTime(Date msgSendTime) {
        this.msgSendTime = msgSendTime;
    }
    @JSON(format="yyyy-MM-dd HH:mm:ss")
    public Date getMsgReadTime() {
        return msgReadTime;
    }
    public final String getSenderName() {
        return senderName;
    }
    public final void setSenderName(String senderName) {
        this.senderName = senderName;
    }
    
}

