/**
 * IUserRoleRefDAO.java
 */
package com.kevin.common.dao.system;

import java.util.List;

import com.kevin.common.dao.IBaseDAO;
import com.kevin.common.exception.BaseSqlMapException;
import com.kevin.common.pojo.system.UserRoleRef;
/**
 * 系统管理-用户角色关系DAO接口
 * @author jiangyn
 *
 */
public interface IUserRoleRefDAO extends IBaseDAO<UserRoleRef>{
  /**
   * 根据用户id获得该用户具有的角色    
   * @param userId
   * @return 用户角色关联信息列表
   * @throws BaseSqlMapException
   */
  List<UserRoleRef> getRoleListByUserId(Integer userId)throws BaseSqlMapException;
  /**
   * 根据用户id删除用户所关联的角色
   * @param uid 用户id
   * @return 1成功,0失败
   * @throws BaseSqlMapException
   */
  int deleteUserRoleRefByUserId(Integer uid)throws BaseSqlMapException;
  int saveUserRole(List<UserRoleRef> urList)throws BaseSqlMapException;
  /**
   * 通过角色ID查询该角色被使用次数 add by lyt 2011/8/18
   * @param riId
   * @return
   * @throws BaseSqlMapException
   */
  int findRoleCountByRIID(Integer riId)throws BaseSqlMapException;
}
