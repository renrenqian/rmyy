package com.kevin.common.pojo.system;

import java.io.Serializable;
import java.util.Date;

import com.googlecode.jsonplugin.annotations.JSON;


    public class OperationLog implements Serializable{

        /**
         * 
         */
        private static final long serialVersionUID = 2741985828561550166L;
        private Long id;
        private Date time;
        private String userName;
        private Integer userId;
        private String ip;
        private Integer state;
        private String action;
        private String params;
        private Integer userType;
        public final Long getId() {
            return id;
        }
        public final void setId(Long id) {
            this.id = id;
        }
        @JSON(format="yyyy-MM-dd HH:mm:ss")
        public final Date getTime() {
            return time;
        }
        public final void setTime(Date time) {
            this.time = time;
        }
        public final String getUserName() {
            return userName;
        }
        public final void setUserName(String userName) {
            this.userName = userName;
        }
        public final String getIp() {
            return ip;
        }
        public final void setIp(String ip) {
            this.ip = ip;
        }
        public final Integer getState() {
            return state;
        }
        public final void setState(Integer state) {
            this.state = state;
        }
        public final String getAction() {
            return action;
        }
        public final void setAction(String action) {
            this.action = action;
        }
        public final String getParams() {
            return params;
        }
        public final void setParams(String params) {
            this.params = params;
        }
        public final Integer getUserId() {
            return userId;
        }
        public final void setUserId(Integer userId) {
            this.userId = userId;
        }
        public Integer getUserType() {
            return userType;
        }
        public void setUserType(Integer userType) {
            this.userType = userType;
        }
    }


