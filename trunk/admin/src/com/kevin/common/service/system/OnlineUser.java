/**
 * OnlineUser.java
 */
package com.kevin.common.service.system;

import javax.servlet.http.HttpSession;

import com.kevin.common.pojo.system.UserInfo;

/**
 * 在线用户基本信息实体类
 * @author jiangyn
 *
 */
public class OnlineUser implements java.io.Serializable{

    /**
     * 
     */
    private static final long serialVersionUID = 2409774943127773154L;
    private HttpSession session;
    private UserInfo userInfo;
    public final HttpSession getSession() {
        return session;
    }
    public final void setSession(HttpSession session) {
        this.session = session;
    }
    public final UserInfo getUserInfo() {
        return userInfo;
    }
    public final void setUserInfo(UserInfo userInfo) {
        this.userInfo = userInfo;
    }
}
