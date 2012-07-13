/**
 * TalentTypeDAOImpl.java
 * kevin 2012-7-14
 * @version 0.1
 */
package com.kevin.group.dao.member.impl;

import org.springframework.stereotype.Component;

import com.kevin.common.dao.AbstractBaseDAO;
import com.kevin.group.dao.member.ITalentTypeDAO;
import com.kevin.group.pojo.member.TalentType;

/**
 * @author kevin
 * @since jdk1.6
 */
@Component("tatDAO")
public class TalentTypeDAOImpl extends AbstractBaseDAO<TalentType> implements
        ITalentTypeDAO {

}
