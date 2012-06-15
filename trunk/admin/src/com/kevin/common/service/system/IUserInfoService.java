/**
 * IUserInfoService.java
 */
package com.kevin.common.service.system;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.kevin.common.exception.CommonServiceException;
import com.kevin.common.pojo.Menu;
import com.kevin.common.pojo.system.PrivilegeInfo;
import com.kevin.common.pojo.system.RoleInfo;
import com.kevin.common.pojo.system.UserInfo;
import com.kevin.common.service.IBaseService;
/**
 * 系统管理-用户管理业务逻辑层接口
 * @author jiangyn
 *
 */
public interface IUserInfoService extends IBaseService<UserInfo> {
    /**
     * 用户权限缓存
     */
   public final static Map<String, Set<PrivilegeInfo>> PRIVILEGEMAP = new HashMap<String, Set<PrivilegeInfo>>() ;    
   /**
    * 在线用户缓存
    */
   public final static Map<String, OnlineUser> ONLINEUSERMAP = new HashMap<String, OnlineUser>();
   /**
    * 登入    
    * @param user
    * @return false不可登入,true可登入
    * @throws CommonServiceException
    */
   int login(UserInfo user)throws CommonServiceException;
   /**
    * 登出
    * @return true成功,false失败
    * @throws CommonServiceException
    */
   boolean logout()throws CommonServiceException;
   /**
    * 修改用户密码    
    * @param user
    * @return
    * @throws CommonServiceException
    */
   int updateUserPwd(UserInfo user)throws CommonServiceException;
   /**
    * 创建用户
    * @param user
    * @param roleList
    * @return 1成功,0失败
    * @throws CommonServiceException
    */
   int createUser(UserInfo user)throws CommonServiceException;
   /**
    * 更新用户基本信息
    * @param user
    * @param roleList
    * @return 1成功,0失败
    * @throws CommonServiceException
    */
   int updateUserInfo(UserInfo user)throws CommonServiceException;
   /**
    * 某用户是否具有某操作的权限
    * @param uid
    * @param priName
    * @return true 有权限,false无权限
    * @throws CommonServiceException
    */
   boolean isHavePri(Integer uid,String priName)throws CommonServiceException;
   /**
    * 启用，停用用户登录
    * @param user
    * @return 1成功,0失败
    * @throws CommonServiceException
    */
   int toggleEnable(UserInfo user)throws CommonServiceException;
   /**
    * 修改个人信息
    * @param user
    * @return 1成功,0失败
    * @throws CommonServiceException
    */
   int updatePersonInfo(UserInfo user)throws CommonServiceException;
   /**
    * 获得用户所具有的角色
    * @param user
    * @return 1成功,0失败
    * @throws CommonServiceException
    */
   List<RoleInfo> listRoleByUser(UserInfo user)throws CommonServiceException;
   /**
    * 获得用户拥有的菜单项
    * @return 菜单列表
    * @throws CommonServiceException
    */
   public List<Menu> getMenuList() throws CommonServiceException; 
}