/**
 * AbstractBaseDAO.java
 */
package com.kevin.common.dao;
import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.sql.SQLException;
import java.util.List;

import javax.annotation.PostConstruct;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.kevin.common.exception.BaseSqlMapException;
import com.kevin.common.pojo.PageBean;

import com.ibatis.sqlmap.client.SqlMapClient;


@SuppressWarnings("unchecked")
public class AbstractBaseDAO<T> extends SqlMapClientDaoSupport implements IBaseDAO<T>{
    @Autowired
    private SqlMapClient sqlMapClient;
    @PostConstruct
    public void initSqlMapClient() {
        super.setSqlMapClient(sqlMapClient);
    }
    protected Class<?> entityClass;
    protected String nameSpace;
    protected Logger logger = Logger.getLogger(AbstractBaseDAO.class.getName());
    public AbstractBaseDAO() {
        // Get the generic type and name space
        Type genType = getClass().getGenericSuperclass();
        Type[] params = ((ParameterizedType) genType).getActualTypeArguments();
        entityClass = (Class<?>) params[0];
        nameSpace = entityClass.getSimpleName();
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.stardream.adinfo.dao.IBaseDAO#search(java.lang.Object)
     */
    public T search(T entity) throws BaseSqlMapException {
        return getSingleEntity("search", entity);
    }
    public List<T> list(T entity) throws BaseSqlMapException {
        return list("list", entity);
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.stardream.adinfo.dao.IBaseDAO#list(int, int, java.lang.Object)
     */
    public List<T> listAll() throws BaseSqlMapException {
        return list("listAll");
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.stardream.adinfo.dao.IBaseDAO#save(java.lang.Object)
     */
    public Serializable save(T entity) throws BaseSqlMapException {
        return save("insert", entity);
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.stardream.adinfo.dao.IBaseDAO#update(java.lang.Object)
     */
    public int update(T entity) throws BaseSqlMapException {
        try {
            return update("update", entity);
        } catch (BaseSqlMapException e) {
            logger.error("Exception while list: " + e.getMessage());
            throw new BaseSqlMapException(e.getMessage());
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.stardream.adinfo.dao.IBaseDAO#delete(java.lang.Object)
     */
    public int delete(T entity) throws BaseSqlMapException {
        return delete("delete", entity);
    }

    public int delete(Serializable id) throws BaseSqlMapException {
        return delete("deleteById", id);
    }
    public Long getTotalCount(T entity) throws BaseSqlMapException {
        return getTotalCount("getTotalCount", entity);
    }
    public List<T> listAll(PageBean<T> pageBean) throws BaseSqlMapException {
        return list("listAllByPageBean", pageBean);
    }
    public int deleteList(List<Serializable> idList) throws BaseSqlMapException {
        return deleteList("deleteList",idList);
    }

    /******************************************受保护方法,提供给子类或自己调用****************************************************/
    protected List<T> listAll(String statement,PageBean<T> pageBean) throws BaseSqlMapException {
        return list(statement, pageBean);
    }
    protected int deleteList(String statement,List<Serializable> idList) throws BaseSqlMapException {
        int result = -1;
        String st = nameSpace.concat(".").concat(statement);
        try {
            result = getSqlMapClient().delete(st, idList);
        } catch (SQLException e) {
            logger.error("Exception while deleteList statement(" + st + "): " + e.getMessage());
            throw new BaseSqlMapException("批量删除数据错误！");
        }
        return result;
    }
    protected Long getTotalCount(String statementName, T entity)
            throws BaseSqlMapException {
        Long result = 0L;
        String statememt = nameSpace.concat(".").concat(statementName);
        try {
            if (entity == null) {
                result = (Long) getSqlMapClient().queryForObject(statememt);
            } else {
                result = (Long) getSqlMapClient().queryForObject(statememt,
                        entity);
            }
        } catch (SQLException e) {
            logger.error("Exception while getTotalCount(" + statememt + "):"
                    + e.getMessage());
            throw new BaseSqlMapException("查询总数据数错误！");
        }
        return result;
    }
    
    protected Long getTotalCount(String statementName)
            throws BaseSqlMapException {
        return getTotalCount(statementName, null);
    }

    // 分页查询
    protected List<T> list(String statementName, PageBean<T> pageBean)
            throws BaseSqlMapException {
        List<T> adInfos = null;
        String statement = nameSpace.concat(".").concat(statementName);
        try {
            adInfos = getSqlMapClient().queryForList(statement, pageBean);
        } catch (SQLException e) {
            logger.error("Exception while list statement(" + statement + "): "
                    + e.getMessage());
            throw new BaseSqlMapException("分页查询数据错误！");
        }
        return adInfos;
    }

    protected List<T> list(String statementName, T entity)
            throws BaseSqlMapException {
        List<T> adInfos = null;
        String statement = nameSpace.concat(".").concat(statementName);
        try {
            adInfos = getSqlMapClient().queryForList(statement, entity);
        } catch (SQLException e) {
            logger.error("Exception while list statement(" + statement + "): "
                    + e.getMessage());
            throw new BaseSqlMapException("查询数据错误！");
        }
        return adInfos;
    }

    protected List<T> list(String statementName) throws BaseSqlMapException {
        List<T> adInfos = null;
        String statement = nameSpace.concat(".").concat(statementName);
        try {
            adInfos = getSqlMapClient().queryForList(statement);
        } catch (SQLException e) {
            logger.error("Exception while list statement(" + statement + "):"
                    + e.getMessage());
            throw new BaseSqlMapException("查询数据错误！");
        }
        return adInfos;
    }

    protected T getSingleEntity(String ns, String statementName, T entity)
            throws BaseSqlMapException {
        T adinfo = null;
        String statement = ns.concat(".").concat(statementName);
        try {
            if (entity != null) {
                adinfo = (T)getSqlMapClient().queryForObject(statement, entity);
            } else {
                adinfo = (T)getSqlMapClient().queryForObject(statement);
            }
        } catch (SQLException e) {
            logger.error("Exception while getSingleEntity statement("
                    + statement + "): " + e.getMessage());
            throw new BaseSqlMapException("查询数据错误！");
        }
        return adinfo;
    }

    protected T getSingleEntity(String statementName, T entity)
            throws BaseSqlMapException {
        return getSingleEntity(nameSpace, statementName, entity);
    }

    protected T getSingleEntity(String statementName)
            throws BaseSqlMapException {
        return getSingleEntity(nameSpace, statementName, null);
    }

    protected int update(String statementName, T entity)
            throws BaseSqlMapException {
        int result = -1;
        String statement = nameSpace.concat(".").concat(statementName);
        try {
            if (entity != null) {
                result = getSqlMapClient().update(statement, entity);
            } else {
                result = getSqlMapClient().update(statement);
            }
        } catch (SQLException e) {
            logger.error("Exception while update statement (" + statement
                    + "): " + e.getMessage());
            throw new BaseSqlMapException("更新数据错误！");
        }
        return result;
    }

    protected int update(String statementName) throws BaseSqlMapException {
        return update(statementName, null);
    }
    protected Serializable save(String statementName, T entity)
            throws BaseSqlMapException {
        Serializable id = null;
        String statement = nameSpace.concat(".").concat(statementName);
        try {
            if (entity != null) {
                id = (Serializable) getSqlMapClient().insert(statement, entity);
            } else {
                id = (Serializable) getSqlMapClient().insert(statement);
            }
        } catch (SQLException e) {
            logger.error("Exception while insert statement(" + statement + "): " + e.getMessage());
            throw new BaseSqlMapException("新增数据错误！");
        }
        return id;
    }
    protected Serializable save(String statementName)
            throws BaseSqlMapException {
        return save(statementName, null);
    }
    // 执行删除语句
    protected int delete(String statementName) throws BaseSqlMapException {
        int result = -1;
        String statement = nameSpace.concat(".").concat(statementName);
        try {
            result = getSqlMapClient().delete(statement);
        } catch (SQLException e) {
            logger.error("Exception while delete statement(" + statement + "):"
                    + e.getMessage());
            throw new BaseSqlMapException("删除数据错误！");
        }
        return result;
    }

    // 根据id删除
    protected int delete(String statementName, Serializable id)
            throws BaseSqlMapException {
        int result = -1;
        String statement = nameSpace.concat(".").concat(statementName);
        try {
            result = getSqlMapClient().delete(statement, id);
        } catch (SQLException e) {
            logger.error("Exception while delete statement(" + statement + "):"
                    + e.getMessage());
            throw new BaseSqlMapException("删除数据错误！");
        }
        return result;
    }

    // 根据实体对象(作为条件)删除
    protected int delete(String statementName, T entity)
            throws BaseSqlMapException {
        int result = -1;
        String statement = nameSpace.concat(".").concat(statementName);
        try {
            result = getSqlMapClient().delete(statement, entity);
        } catch (SQLException e) {
            logger.error("Exception while delete statement(" + statement
                    + "): " + e.getMessage());
            throw new BaseSqlMapException("删除数据错误！");
        }
        return result;
    }
}