/**
 * AbstractBaseService.java
 */
package com.kevin.common.service;



import java.io.Serializable;
import java.util.List;

import org.apache.log4j.Logger;

import com.kevin.common.dao.AbstractBaseDAO;
import com.kevin.common.dao.IBaseDAO;
import com.kevin.common.exception.BaseSqlMapException;
import com.kevin.common.exception.CommonServiceException;
import com.kevin.common.pojo.PageBean;

public  abstract class AbstractBaseService<T> implements IBaseService<T> {
    protected final Logger logger = Logger.getLogger(AbstractBaseDAO.class.getName());
    private IBaseDAO<T> absBaseDao;
    /**
     * @param absBaseDao
     *            the absBaseDao to set
     */
    public final void setAbsBaseDao(IBaseDAO<T> absBaseDao) {
        this.absBaseDao = absBaseDao;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.stardream.adinfo.service.IBaseService#search(java.lang.Object)
     */
    @Override
    public T search(T obj) throws CommonServiceException {
        if(obj==null){
            throw new CommonServiceException("参数为NULL");
        }
        try {
            return absBaseDao.search(obj);
        } catch (BaseSqlMapException e) {
            throw new CommonServiceException(e.getMessage());
        }
    }



    /*
     * (non-Javadoc)
     * 
     * @see com.stardream.adinfo.service.IBaseService#save(java.lang.Object)
     */
    @Override
    public Serializable save(T obj) throws CommonServiceException {
        try {
            if(obj == null){
                return -1;
            }
            return  absBaseDao.save(obj);
        } catch (BaseSqlMapException e) {
            throw new CommonServiceException(e.getMessage());
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.stardream.adinfo.service.IBaseService#update(java.lang.Object)
     */
    @Override
    public int update(T obj) throws CommonServiceException {
        try {
            if(obj == null){
                return -1;
            }
            return absBaseDao.update(obj);
        } catch (BaseSqlMapException e) {
            throw new CommonServiceException(e.getMessage());
        }
    }


    /*
     * (non-Javadoc)
     * 
     * @see com.stardream.adinfo.service.IBaseService#delete(java.lang.Object)
     */
    @Override
    public int delete(T obj) throws CommonServiceException {
        try {
            if(obj == null){
                return -1;
            }
            return absBaseDao.delete(obj);
        } catch (BaseSqlMapException e) {
            throw new CommonServiceException(e.getMessage());
        }
    }

    @Override
    public int batchDelete(List<Serializable> idList)
            throws CommonServiceException {
        int resultCode = -1;
        if(idList != null && idList.size()>0){
            try {
                if(idList.size()>1){
                    resultCode = absBaseDao.deleteList(idList);
                }else{
                    resultCode = absBaseDao.delete(idList.get(0));
                }
            } catch (BaseSqlMapException e) {
                throw new CommonServiceException(e.getMessage());
            }
        }
        return resultCode;
    }

    @Override
    public PageBean<T> list(PageBean<T> page) throws CommonServiceException {
        PageBean<T> pageBean = null;
        if(page == null){
            pageBean = new PageBean<T>();
            pageBean.setPageSize(20);//默认查询出20条
            pageBean.setCurrentPageNo(1);
        }else{ 
            pageBean = page;
        }
        Long totalCount;
        try {
            totalCount = absBaseDao.getTotalCount(pageBean.getCondition());
        } catch (BaseSqlMapException e) {
            throw new CommonServiceException(e.getMessage());
        }
        long totalPageCount = 0;
        if(totalCount == null){
            totalPageCount = 0;
        }else{
            if (totalCount % pageBean.getPageSize() == 0){
                totalPageCount = totalCount / pageBean.getPageSize();
            }else{
                totalPageCount =totalCount / pageBean.getPageSize() + 1;
            }
            pageBean.setTotalPageCount(totalPageCount);
            pageBean.setTotalItemCount(totalCount);
            try {
                pageBean.setDataList(absBaseDao.listAll(pageBean));
            } catch (BaseSqlMapException e) {
                throw new CommonServiceException(e.getMessage());
            }
        }
        return pageBean;
    }
    @Override
    public List<T> listAll() throws CommonServiceException {
        try {
            return absBaseDao.listAll();
        } catch (BaseSqlMapException e) {
            throw new CommonServiceException(e.getMessage());
        }
    }

    @Override
    public List<T> listAll(T t) throws CommonServiceException {
        try {
            return absBaseDao.list(t);
        } catch (BaseSqlMapException e) {
            throw new CommonServiceException(e.getMessage());
        }
    }
}