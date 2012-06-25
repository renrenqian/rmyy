/**
 * UserInfoServiceImpl.java
 */
package com.kevin.common.service.system.impl;

import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import com.kevin.common.annotation.NoLog;
import com.kevin.common.constant.SystemConstant;
import com.kevin.common.dao.system.IPrivilegeInfoDAO;
import com.kevin.common.dao.system.IRolePrivilegeRefDAO;
import com.kevin.common.dao.system.IUserInfoDAO;
import com.kevin.common.dao.system.IUserRoleRefDAO;
import com.kevin.common.exception.BaseSqlMapException;
import com.kevin.common.exception.CommonServiceException;
import com.kevin.common.pojo.ItemLink;
import com.kevin.common.pojo.Menu;
import com.kevin.common.pojo.system.PrivilegeInfo;
import com.kevin.common.pojo.system.RoleInfo;
import com.kevin.common.pojo.system.UserInfo;
import com.kevin.common.pojo.system.UserRoleRef;
import com.kevin.common.service.AbstractBaseService;
import com.kevin.common.service.system.IUserInfoService;
import com.kevin.common.service.system.OnlineUser;
import com.kevin.common.util.ServletActionContextUtil;
import com.kevin.common.util.XmlUtil;


@Service("userInfoService")
public class UserInfoServiceImpl extends AbstractBaseService<UserInfo>
        implements IUserInfoService {
    private IUserInfoDAO userInfoDAO;
    private IUserRoleRefDAO userRoleRefDAO;
    private IPrivilegeInfoDAO privilegeDAO;
    private IRolePrivilegeRefDAO rolePrivilegeDAO;

    @Autowired
    public void setUserInfoDAO(IUserInfoDAO userInfoDAO) {
        setAbsBaseDao(userInfoDAO);
        this.userInfoDAO = userInfoDAO;
    }

    @Autowired
    public void setUserRoleRefDAO(IUserRoleRefDAO userRoleRefDAO) {
        this.userRoleRefDAO = userRoleRefDAO;
    }

    @Autowired
    public final void setPrivilegeDAO(IPrivilegeInfoDAO privilegeDAO) {
        this.privilegeDAO = privilegeDAO;
    }

    @Autowired
    public final void setRolePrivilegeDAO(IRolePrivilegeRefDAO rolePrivilegeDAO) {
        this.rolePrivilegeDAO = rolePrivilegeDAO;
    }

    @Override
    public List<UserInfo> listAll() throws CommonServiceException {// 查所有用户
        List<UserInfo> userList = super.listAll();
        List<UserRoleRef> userRoleList = null;
        try {
            userRoleList = userRoleRefDAO.listAll();
        } catch (BaseSqlMapException e) {
            throw new CommonServiceException(e.getMessage());
        }
        if (userList != null) {
            for (UserInfo user : userList) {
                // 设置用户的在线状态
                if (isOnline(user.getUiName())) {
                    user.setOnline("在线");
                } else {
                    user.setOnline("离线");
                }
                // 设置用户关联的角色
                String role = "";
                if (userRoleList != null && userRoleList.size() > 0) {
                    for (UserRoleRef urr : userRoleList) {
                        if (urr.getUserInfo().getUiId().intValue() == user
                                .getUiId().intValue()) {
                            role = role + urr.getRoleInfo().getRiName() + " ";
                        }
                    }
                }
                user.setRoleNames(role);
            }
        }
        return userList;
    }

    @NoLog
    private boolean isOnline(String userName) {// 判断是否在线
        // key为sessionid 这样同一个用户可以在不同的客户端登陆
        for (Map.Entry<String, OnlineUser> entry : ONLINEUSERMAP.entrySet()) {
            if (entry.getValue().getUserInfo().getUiName().equals(userName)) {
                return true;
            }
        }
        return false;
    }

    // private boolean isOnline(Integer userId){//判断是否在线
    // //key为sessionid 这样同一个用户可以在不同的客户端登陆
    // for(Map.Entry<String, OnlineUser> entry:ONLINEUSERMAP.entrySet()){
    // if(entry.getValue().getUserInfo().getUiId().intValue()==
    // userId.intValue()){
    // return true;
    // }
    // }
    // return false;
    // }

    // private boolean forceLogout(Integer userId){//强怕离线
    // //key为sessionid 这样同一个用户可以在不同的客户端登陆
    // for(Map.Entry<String, OnlineUser> entry:ONLINEUSERMAP.entrySet()){
    // OnlineUser onlineUser = entry.getValue();
    // if(onlineUser.getUserInfo().getUiId().intValue()== userId.intValue()){
    // String sid = onlineUser.getSession().getId();
    // onlineUser.getSession().invalidate();//强迫session无效
    // ONLINEUSERMAP.remove(sid);//从在线用户列表移除用户
    // return true;
    // }
    // }
    // return false;
    // }
    private void addUserRoleRef(UserInfo user) throws CommonServiceException {// 关联角色(新增用户，更新用户用)
        Integer[] roleList = user.getRoleId();
        List<UserRoleRef> userRoleRefList = new ArrayList<UserRoleRef>();
        UserRoleRef ref;
        RoleInfo role;
        // 设置默认角色(包含一些基本权限如登入，登出,查看个人信息，修改个人信息，修改密码登)
        ref = new UserRoleRef();
        role = new RoleInfo();
        role.setRiId(999);
        ref.setUserInfo(user);
        ref.setRoleInfo(role);
        userRoleRefList.add(ref);
        if (roleList != null) {
            for (Integer roleId : roleList) {
                ref = new UserRoleRef();
                role = new RoleInfo();
                role.setRiId(roleId);
                ref.setUserInfo(user);
                ref.setRoleInfo(role);
                userRoleRefList.add(ref);
            }
        }
        try {
            userRoleRefDAO.saveUserRole(userRoleRefList);// 保存关联的角色
        } catch (BaseSqlMapException e) {
            throw new CommonServiceException(e.getMessage());
        }
    }

    @Override
    public int createUser(UserInfo user) throws CommonServiceException {// 新增用户
        int result = -1;
        UserInfo existUser = null;
        try {
            existUser = userInfoDAO.search(user);
            if (existUser != null) {
                throw new CommonServiceException("用户名【" + user.getUiName()
                        + "】已存在");
            } else {
                userInfoDAO.save(user);// 保存用户(会返回用户ID的,返回ID后关联CP和发布配置)
                addUserRoleRef(user);// 关联角色
                result = 1;
            }
        } catch (BaseSqlMapException e) {
            throw new CommonServiceException(e.getMessage());
        }
        return result;
    }

    @Override
    public int updateUserInfo(UserInfo user) throws CommonServiceException {
        int result = -1;
        UserInfo existUser;
        try {
            UserInfo condition = new UserInfo();
            condition.setUiName(user.getUiName());
            existUser = userInfoDAO.search(condition);// 根据用户名查找用户
            if (existUser != null
                    && existUser.getUiId().intValue() != user.getUiId()
                            .intValue()) {
                // 用户名存在抛出异常
                throw new CommonServiceException("用户名【" + user.getUiName()
                        + "】已存在");
            } else {
                // 更新角色,先删除关联角色，后添加关联角色
                userRoleRefDAO.deleteUserRoleRefByUserId(user.getUiId());
                addUserRoleRef(user);
                condition.setUiId(user.getUiId());
                existUser = userInfoDAO.search(condition);
                existUser.setUiMail(user.getUiMail());
                existUser.setUiAccount(user.getUiAccount());
                existUser.setUiName(user.getUiName());
                existUser.setUiOfficePhone(user.getUiOfficePhone());
                result = userInfoDAO.update(existUser);
            }
            return result;
        } catch (BaseSqlMapException e) {
            throw new CommonServiceException(e.getMessage());
        }

    }

    @Override
    public int delete(UserInfo user) throws CommonServiceException {
        try {
            // 可强迫用户下线 (如果在线)TODO?
            // 在线用户不允许删除 TODO?
            userRoleRefDAO.deleteUserRoleRefByUserId(user.getUiId());// 先删除关联的角色
            // userInfoDAO.deleteUserCpRef(user);//删除关联的CP
            userInfoDAO.deleteUserPubRef(user);// 删除关联的发布配置
        } catch (BaseSqlMapException e) {
            throw new CommonServiceException(e.getMessage());
        }
        return super.delete(user);// 删除用户
    }

    @Override
    public int batchDelete(List<Serializable> idList)
            throws CommonServiceException {
        if (idList == null || idList.size() == 0) {
            throw new CommonServiceException("无任何用户可删除，无ID无传入服务器端");
        }
        try {
            UserInfo user = new UserInfo();
            for (Serializable id : idList) {
                // 先删除关联的角色
                userRoleRefDAO.deleteUserRoleRefByUserId(Integer.valueOf(id
                        .toString()));
                user.setUiId(Integer.valueOf(id.toString()));
                // userInfoDAO.deleteUserCpRef(user);//删除关联的CP
                userInfoDAO.deleteUserPubRef(user);// 删除关联的发布配置
            }
        } catch (BaseSqlMapException e) {
            throw new CommonServiceException(e.getMessage());
        }
        return super.batchDelete(idList);// 批量删除用户
    }

    @Override
    public int updateUserPwd(UserInfo user) throws CommonServiceException {
        int result = -1;
        UserInfo existUser;
        try {
            existUser = userInfoDAO.search(user);
            if (existUser != null) {
                existUser.setUiPwd(user.getUiPwd());
                result = userInfoDAO.update(existUser);
            } else {
                throw new CommonServiceException("用户可能已被删除");
            }
        } catch (BaseSqlMapException e) {
            throw new CommonServiceException(e.getMessage());
        }
        return result;
    }

    @Override
    @NoLog
    public boolean isHavePri(Integer uid, String priName)
            throws CommonServiceException {
        try {
            Set<PrivilegeInfo> allPriSet = IUserInfoService.PRIVILEGEMAP
                    .get(SystemConstant.CMCPSE_ALL_PRIVILEGE);
            List<PrivilegeInfo> allPriList = null;
            if (allPriSet == null) {// 判断是否已获得所有权限
                allPriList = privilegeDAO.getAll();
                allPriSet = new HashSet<PrivilegeInfo>(allPriList);
                IUserInfoService.PRIVILEGEMAP.put(
                        SystemConstant.CMCPSE_ALL_PRIVILEGE, allPriSet);// 将所有权限放入缓存
            }
            String userName = ServletActionContextUtil.getCurrentUserName();
            String userNameKey = "$" + userName + "$";
            Set<PrivilegeInfo> userPriSet = null;
            userPriSet = IUserInfoService.PRIVILEGEMAP.get(userNameKey);// 获得用户权限
            if (userPriSet == null) {// 判断用户权限是否已获得,若未获得则重新获得
                List<UserRoleRef> roleList = userRoleRefDAO
                        .getRoleListByUserId(uid);// 根据用户ID获得某用户拥有的角色
                if (roleList != null && roleList.size() > 0) {
                    // 根据角色获得用户的权限
                    userPriSet = new HashSet<PrivilegeInfo>();
                    for (UserRoleRef role : roleList) {// 循环获取每个角色的权限,然后与"$用户名$"关联到权限缓存
                        userPriSet.addAll(rolePrivilegeDAO
                                .getPrivilegeListByRoleId(role.getRoleInfo()
                                        .getRiId()));
                    }
                }
                // 用户权限以"$用户名$"为键存放在用户权限缓存里
                IUserInfoService.PRIVILEGEMAP.put(userNameKey, userPriSet);
            }
            // 判断是否有权限
            if (isExistInAll(priName) && !isExistInUser(priName)) {
                // 操作在权限表，但用户无权限，则返回false
                return false;
            } else {
                return true;
            }

        } catch (BaseSqlMapException e) {
            throw new CommonServiceException(e.getMessage());
        }
    }

    @NoLog
    private boolean isExistInAll(String method) {
        Set<PrivilegeInfo> allPriSet = IUserInfoService.PRIVILEGEMAP
                .get(SystemConstant.CMCPSE_ALL_PRIVILEGE);
        if (allPriSet != null) {
            for (PrivilegeInfo p : allPriSet) {
                if (p.getPiMethod().trim().equals(method)) {
                    return true;
                }
            }
        }
        return false;
    }

    @NoLog
    private boolean isExistInUser(String method) {
        String userName = ServletActionContextUtil.getCurrentUserName();
        Set<PrivilegeInfo> userPriSet = IUserInfoService.PRIVILEGEMAP.get("$"
                + userName + "$");
        if (userPriSet != null) {
            for (PrivilegeInfo p : userPriSet) {
                if (p.getPiMethod().trim().equals(method)) {
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public int toggleEnable(UserInfo user) throws CommonServiceException {
        int result = -1;
        UserInfo existUser;
        try {
            existUser = userInfoDAO.search(user);
            if (existUser != null) {
                existUser.setUiEnable(user.getUiEnable());
                result = userInfoDAO.update(existUser);
                if (isOnline(existUser.getUiName())) {
                    // 删除在线用户缓存列表
                    for (Map.Entry<String, OnlineUser> entry : ONLINEUSERMAP
                            .entrySet()) {
                        if (entry.getValue().getUserInfo().getUiName()
                                .equals(existUser.getUiName())) {
                            ONLINEUSERMAP.remove(entry.getKey());
                        }
                    }
                }
            } else {
                throw new CommonServiceException("用户可能已被删除");
            }
        } catch (BaseSqlMapException e) {
            throw new CommonServiceException(e.getMessage());
        }
        return result;
    }

    @Override
    public int updatePersonInfo(UserInfo user) throws CommonServiceException {
        int result = -1;
        UserInfo existUser;
        try {
            existUser = userInfoDAO.search(user);
            if (existUser != null) {
                existUser.setUiAccount(user.getUiAccount());
                existUser.setUiName(user.getUiName());
                existUser.setUiMail(user.getUiMail());
                existUser.setUiOfficePhone(user.getUiOfficePhone());
                result = userInfoDAO.update(existUser);
            } else {
                throw new CommonServiceException("用户可能已被删除");
            }
        } catch (BaseSqlMapException e) {
            throw new CommonServiceException(e.getMessage());
        }
        return result;
    }

    @Override
    public List<RoleInfo> listRoleByUser(UserInfo user)
            throws CommonServiceException {
        List<RoleInfo> roleList = new ArrayList<RoleInfo>();
        if (user == null || user.getUiId() != null) {
            try {
                List<UserRoleRef> refList = userRoleRefDAO
                        .getRoleListByUserId(user.getUiId());
                if (refList != null) {
                    for (UserRoleRef ref : refList) {
                        roleList.add(ref.getRoleInfo());
                    }
                }
            } catch (BaseSqlMapException e) {
                throw new CommonServiceException(e.getMessage());
            }
        }
        return roleList;
    }

    @NoLog
    @Override
    public UserInfo login(UserInfo userInfo) throws CommonServiceException {
        //int flag = 1;
        HttpServletRequest request = ServletActionContextUtil.getRequest();
        try {
            if (userInfo != null) {
                UserInfo condition = new UserInfo();
                //condition.setUiName(userInfo.getUiName());
                condition.setUiAccount(userInfo.getUiAccount());
                UserInfo existUser = userInfoDAO.search(condition);
                if (existUser != null
                        && existUser.getUiPwd().equals(userInfo.getUiPwd())
                        && existUser.getUiEnable() == SystemConstant.USER_ENABLE) {
                    HttpSession session = request.getSession(true);
                    session.setAttribute(
                            SystemConstant.CURRENT_LOGIN_USER_NAME,
                            existUser.getUiName());
                    session.setAttribute(SystemConstant.CURRENT_LOGIN_USER_ID,
                            existUser.getUiId());
                    userInfo.setUiName(existUser.getUiName());// add the name to bean and return to page show on header
                    // 放入在线用户列表
                    OnlineUser onlineUser = new OnlineUser();
                    onlineUser.setUserInfo(existUser);
                    onlineUser.setSession(session);
                    IUserInfoService.ONLINEUSERMAP.put(session.getId(),
                            onlineUser);
                    //flag = 1;
                } else if (existUser != null
                        && existUser.getUiPwd().equals(userInfo.getUiPwd())
                        && existUser.getUiEnable().intValue() != SystemConstant.USER_ENABLE) {
                    throw new CommonServiceException("用户已被停用，请联系管理员");
                }
            } else {
                //flag = -1;
            }
        } catch (BaseSqlMapException e) {
            throw new CommonServiceException(e.getMessage());
        }
        return userInfo.loginSuccess();
    }

    @NoLog
    @SuppressWarnings("unchecked")
    @Override
    public boolean logout() throws CommonServiceException {
        HttpSession session = ServletActionContextUtil.getRequest().getSession(
                false);
        if (session != null) {
            String userName = ServletActionContextUtil.getCurrentUserName();
            IUserInfoService.PRIVILEGEMAP.remove(userName);
            IUserInfoService.ONLINEUSERMAP.remove(session.getId());
            // 移除session内的数据
            Enumeration<String> enumeration = (Enumeration<String>) session
                    .getAttributeNames();
            while (enumeration.hasMoreElements()) {
                session.removeAttribute(enumeration.nextElement());
            }
            session.invalidate();
        }
        return true;
    }

    @Override
    public synchronized List<Menu> getMenuList() throws CommonServiceException {
        List<Menu> menuList = new ArrayList<Menu>();
        Integer uid = ServletActionContextUtil.getCurrentUserId();
        InputStream in = this.getClass().getResourceAsStream(
                "/config/menu/menu.xml");
        try {
            Document dom = XmlUtil.getDom(in);
            NodeList navNodeList = dom.getElementsByTagName("navGroup");
            if (navNodeList != null) {
                int len = navNodeList.getLength();
                Menu menu = null;
                Node navNode = null;
                Node itemNode = null;
                int itemLen = 0;
                NodeList itemNodeList = null;
                ItemLink itemLink = null;
                Node parentNode = null;
                List<ItemLink> navItems;
                for (int i = 0; i < len; i++) {
                    menu = new Menu();
                    navNode = navNodeList.item(i);
                    // 设置导航菜单属性
                    menu.setNavName(XmlUtil.getAttributeValue(navNode, "name"));
                    menu.setDirection(XmlUtil.getAttributeValue(navNode,
                            "direction"));
                    String navId = XmlUtil.getAttributeValue(navNode, "id");
                    if (!isHavePri(uid, navId)) {
                        continue;
                    }
                    menu.setNavId(navId);
                    String order = XmlUtil.getAttributeValue(navNode, "order");
                    order = order == null ? "0" : order;
                    menu.setOrder(new Integer(order));
                    itemNodeList = dom.getElementsByTagName("item");
                    itemLen = itemNodeList.getLength();
                    // 寻找其子菜单
                    navItems = new ArrayList<ItemLink>();
                    for (int j = 0; j < itemLen; j++) {
                        itemNode = itemNodeList.item(j);
                        parentNode = itemNode.getParentNode();
                        if (menu.getNavId().equals(
                                XmlUtil.getAttributeValue(parentNode, "id"))) {
                            String itemLinkId = XmlUtil.getAttributeValue(
                                    itemNode, "id");
                            if (!isHavePri(uid, itemLinkId)) {
                                continue;
                            }
                            itemLink = new ItemLink();
                            // 设置导航菜单项属性
                            itemLink.setItemId(itemLinkId);
                            itemLink.setItemName(XmlUtil.getAttributeValue(
                                    itemNode, "name"));
                            itemLink.setHref(XmlUtil.getAttributeValue(
                                    itemNode, "href"));
                            String index = XmlUtil.getAttributeValue(itemNode,
                                    "order");
                            index = index == null ? "0" : index;
                            itemLink.setOrder(new Integer(index));
                            navItems.add(itemLink);
                        }
                    }
                    // 设置子菜单
                    menu.setNavItems(navItems);
                    menuList.add(menu);
                }
            }
        } catch (SAXException e) {
            throw new CommonServiceException("获取用户导航菜单错误");
        } catch (IOException e) {
            throw new CommonServiceException("获取用户导航菜单错误");
        } finally {
            try {
                in.close();
            } catch (IOException e) {
                // Nothing
            }// 释放资源
        }
        return menuList;
    }
}