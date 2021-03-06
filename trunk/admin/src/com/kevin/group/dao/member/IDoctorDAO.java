/**
 * IDoctorDAO.java
 * kevin 2012-6-17
 * @version 0.1
 */
package com.kevin.group.dao.member;

import java.util.List;

import com.kevin.common.dao.IBaseDAO;
import com.kevin.common.exception.BaseSqlMapException;
import com.kevin.group.pojo.member.DoctorInfo;

/**
 * @author kevin
 * @since jdk1.6
 */
public interface IDoctorDAO extends IBaseDAO<DoctorInfo> {

    int updateExpertId(DoctorInfo doci) throws BaseSqlMapException;

    int updateFamourceId(DoctorInfo doci) throws BaseSqlMapException;

    List<DoctorInfo> listmymz(DoctorInfo doct) throws BaseSqlMapException;

    List<DoctorInfo> listzjyl(DoctorInfo doct) throws BaseSqlMapException;

    List<DoctorInfo> listgjrc(DoctorInfo doct) throws BaseSqlMapException;

}
