/**
 * IContentInfoDAO.java
 * kevin 2012-6-16
 * @version 0.1
 */
package com.kevin.group.dao.content;

import com.kevin.common.dao.IBaseDAO;
import com.kevin.common.exception.BaseSqlMapException;
import com.kevin.group.pojo.content.ContentInfo;

/**
 * @author kevin
 * @since jdk1.6
 */
public interface IContentInfoDAO extends IBaseDAO<ContentInfo> {

    int auditorContent(ContentInfo continfo) throws BaseSqlMapException;

}
