/**
 * IBaseDAO.java
 */
package com.kevin.common.dao;

import java.io.Serializable;
import java.util.List;

import com.kevin.common.exception.BaseSqlMapException;
import com.kevin.common.pojo.PageBean;

/**
 * 
 * 工程名称:  cmcpse 
 * 包名:     com.stardream.cmcpse.dao
 * 类型名:   IBaseDAO
 * 描述:     手机流媒体系统数据访问层基类接口
 *
 * 例子:
 * @version 1.0
 * @see     
 * @since 2011-11-16 12:59:20
 */
public interface IBaseDAO<T> {

    /**
     * The generic functions for all the object to search one data.
     * 
     * @param obj
     * @throws BaseSqlMapException
     */
    T search(T entity) throws BaseSqlMapException;

    /**
     * 
     * 日期: Mar 23, 2011 9:20:06 AM
     * 描述: 根据条件查询数据
     * @param t 泛型条件对象
     * @throws
     * @return List<T> 返回符合条件的数据
     * @see 
     * @since 1.0
     */
    List<T> list(T entity) throws BaseSqlMapException;
    /**
     * The generic functions for all the object to create the data.
     * 
     * @param obj
     * @throws BaseSqlMapException
     */
    Serializable save(T entity) throws BaseSqlMapException;

    /**
     * The generic functions for all the object to update the data.
     * 
     * @param obj
     * @return int
     * @throws BaseSqlMapException
     */
    int update(T entity) throws BaseSqlMapException;
    /**
     * The generic functions for all the object to delete the data.
     * 
     * @param obj
     * @return int
     * @throws BaseSqlMapException
     */
    int delete(T entity) throws BaseSqlMapException;
    /**
     * 
     * 日期: Feb 17, 2011 10:35:59 AM
     * 描述:根据id删除数据
     * @param id
     * @throws BaseSqlMapException
     * @return int 返回删除数据的个数
     * @see 
     * @since 1.0
     */
    int delete(Serializable id) throws BaseSqlMapException;
    /**
     * 
     * 日期: Feb 17, 2011 10:36:32 AM
     * 描述: 根据id批量删除数据
     * @param idList列表
     * @throws BaseSqlMapException
     * @return int 返回删除数据的个数
     * @see 
     * @since 1.0
     */
    int deleteList(List<Serializable> idList) throws BaseSqlMapException;
    /**
     * The generic functions for all the object to list results,
     * It mainly use in data dict cache.
     * 
     * @return List<T>
     * @throws BaseSqlMapException
     */
    List<T> listAll() throws BaseSqlMapException;
    /**
     * 
     * 日期: Feb 11, 2011 2:07:06 PM
     * 描述:查询符合条件的数据的条目数
     * @param T 
     * @throws BaseSqlMapException
     * @return Long
     * @see 
     * @since 1.0
     */
    Long getTotalCount(T entity) throws BaseSqlMapException;
    /**
     * 
     * 日期: Feb 11, 2011 4:53:05 PM
     * 描述: 分页查询
     * @param page 分页对象(包含查询条件，当前页，每页大小，查询数据的起始记录)
     * @throws BaseSqlMapException
     * @return List<T> 返回符合条件的一页数据(<= pageSize)
     * @see 
     * @since 1.0
     */
    List<T> listAll(PageBean<T> pageBean) throws BaseSqlMapException;
}
