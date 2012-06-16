/**
 * IBaseService.java
 */
package com.kevin.common.service;

import java.io.Serializable;
import java.util.List;

import com.kevin.common.exception.BaseSqlMapException;
import com.kevin.common.exception.CommonServiceException;
import com.kevin.common.pojo.PageBean;

public interface IBaseService<T> {

    /**
     * The generic functions for all the object to search one data.
     * 
     * @param obj
     * @return T
     * @throws BaseSqlMapException
     */
    T search(T obj) throws CommonServiceException;

    /**
     * 
     * 作者: <a href="mailto:yaniu_jiang@stardream.tv">蒋亚牛</a>
     * 日期: Feb 11, 2011 4:57:39 PM
     * 描述: 分页查询方法
     * @param page分页对象包含(查询的数据开始和结束索引)
     * @throws BaseSqlMapException
     * @return PageBean<T> 返回分页对象(包含查询出的符合条件的数据)
     * @see 
     * @since 1.0
     */
    PageBean<T> list(PageBean<T> page) throws CommonServiceException;
    List<T> listAll()throws CommonServiceException;
    List<T> listAll(T t)throws CommonServiceException;
    /**
     * The generic functions for all the object to create the data.
     * 
     * @param obj
     * @throws BaseSqlMapException
     */
    Serializable save(T obj) throws CommonServiceException;

    /**
     * The generic functions for all the object to update the data.
     * 
     * @param obj
     * @return int
     * @throws BaseSqlMapException
     */
    int update(T obj) throws CommonServiceException;
    /**
     * The generic functions for all the object to delete the data.
     * 
     * @param obj
     * @return int
     * @throws BaseSqlMapException
     */
    int delete(T obj) throws CommonServiceException;
    int batchDelete(List<Serializable> idList)throws CommonServiceException;
}