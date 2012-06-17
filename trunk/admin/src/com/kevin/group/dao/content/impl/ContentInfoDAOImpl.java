/**
 * ContentInfoDAOImpl.java
 * kevin 2012-6-16
 * @version 0.1
 */
package com.kevin.group.dao.content.impl;

import org.springframework.stereotype.Component;

import com.kevin.common.dao.AbstractBaseDAO;
import com.kevin.group.dao.content.IContentInfoDAO;
import com.kevin.group.pojo.content.ContentInfo;

/**
 * @author kevin
 * @since jdk1.6
 */
@Component("contInfoDAO")
public class ContentInfoDAOImpl extends AbstractBaseDAO<ContentInfo> implements
        IContentInfoDAO {
 
}
