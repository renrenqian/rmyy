/**
 * UserInfoAction.java
 */
package com.kevin.common.action.system;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.kevin.common.action.AbstractBaseAction;
import com.kevin.common.exception.CommonServiceException;
import com.kevin.common.pojo.system.RoleInfo;
import com.kevin.common.pojo.system.UserInfo;
import com.kevin.common.service.system.IUserInfoService;

import com.opensymphony.xwork2.Action;
@Component("userInfoAction")
@Scope("prototype")
public class UserInfoAction extends AbstractBaseAction {
    private IUserInfoService userInfoService;
    private UserInfo user;
    private List<UserInfo> userList;
    private List<RoleInfo> roleList;
    private List<Serializable> ides;
    @Autowired
    public void setUserInfoService(IUserInfoService userInfoService) {
        this.userInfoService = userInfoService;
    }
    public List<RoleInfo> getRoleList() {
        return roleList;
    }
    public void setRoleList(List<RoleInfo> roleList) {
        this.roleList = roleList;
    }
    public UserInfo getUser() {
        return user;
    }
    public void setUser(UserInfo user) {
        this.user = user;
    }
    public List<UserInfo> getUserList() {
        return userList;
    }
    public void setUserList(List<UserInfo> userList) {
        this.userList = userList;
    }
    
    public final List<Serializable> getIdes() {
        return ides;
    }
    public final void setIdes(List<Serializable> ides) {
        this.ides = ides;
    }
    public String login(){
        try {
            this.user = userInfoService.login(user);
            setResultCode(1);
        } catch (CommonServiceException e) {
            setResultCode(-1);
            setMessage(e.getMessage());
        }
        return Action.SUCCESS;
    }
    public String logout(){
        try {
            if(userInfoService.logout()){
                setResultCode(1);
            }else{
                setResultCode(-1);
            }
        } catch (CommonServiceException e) {
            setResultCode(-1);
            setMessage(e.getMessage());
        }
        return Action.SUCCESS;
    }
    public String addUser(){//新增用户
        try {
            Serializable id = userInfoService.createUser(user);
            setResultCode((Integer)id);
        } catch (CommonServiceException e) {
            setMessage(e.getMessage());
            setResultCode(-1);
        }
        return Action.SUCCESS;
    }
    public String deleteUser(){//删除用户
        try {
            int result = userInfoService.delete(user);
            setResultCode(result);
        } catch (CommonServiceException e) {
            setMessage(e.getMessage());
            setResultCode(-1);
        }
        return Action.SUCCESS;
    }
    public String batchDeleteUser(){//批量删除用户
        try {
            int result = userInfoService.batchDelete(ides);
            setResultCode(result);
        } catch (CommonServiceException e) {
            setMessage(e.getMessage());
            setResultCode(-1);
        }
        return Action.SUCCESS;
    }
    public String updateUser(){//更新用户信息
        try {
            int result = userInfoService.updateUserInfo(user);
            setResultCode(result);
        } catch (CommonServiceException e) {
            setMessage(e.getMessage());
            setResultCode(-1);
        }
        return Action.SUCCESS;
    }
    public String searchUserById(){//获得用户基本信息
        try {
            user  = userInfoService.search(user);
            setResultCode(1);
        } catch (CommonServiceException e) {
            setMessage(e.getMessage());
            setResultCode(-1);
        }
        return Action.SUCCESS;
    }
    public String listUser(){//查找所有用户
        try {
            userList = userInfoService.listAll();
            setResultCode(1);
        } catch (CommonServiceException e) {
            setMessage(e.getMessage());
            setResultCode(-1);
        }
        return Action.SUCCESS;
    }
    public String listSelectUser(){//查找所有用户
        try {
            userList = userInfoService.listAll();
            setResultCode(1);
        } catch (CommonServiceException e) {
            setMessage(e.getMessage());
            setResultCode(-1);
        }
        return Action.SUCCESS;
    }
    public String listRoleByUserId(){//获得用户具有的角色信息
        try {
            roleList = userInfoService.listRoleByUser(user);
            setResultCode(1);
        } catch (CommonServiceException e) {
            setMessage(e.getMessage());
            setResultCode(-1);
        }
        return Action.SUCCESS;
    }
    public String updateUserPwd(){//修改个人密码
        try {
            int result = userInfoService.updateUserPwd(user);
            setResultCode(result);
        } catch (CommonServiceException e) {
            setMessage(e.getMessage());
            setResultCode(-1);
        }
        return Action.SUCCESS;
    }
    public String updatePersonInfo(){//修改个人信息
        try {
            int result = userInfoService.updatePersonInfo(user);
            setResultCode(result);
        } catch (CommonServiceException e) {
            setMessage(e.getMessage());
            setResultCode(-1);
        }
        return Action.SUCCESS;
    }
    /**
     * 启停用户
     * 
     */
    public String toggleEnableUser(){
        try {
            int result = userInfoService.toggleEnable(user);
            setResultCode(result);
        } catch (CommonServiceException e) {
            setMessage(e.getMessage());
            setResultCode(-1);
        }
        return Action.SUCCESS;
    }
}