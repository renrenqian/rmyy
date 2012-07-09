/**
 * ILeaderService.java
 * kevin 2012-6-17
 * @version 0.1
 */
package com.kevin.group.service.member;

import com.kevin.common.exception.CommonServiceException;
import com.kevin.common.service.IBaseService;
import com.kevin.group.pojo.member.LeaderInfo;

/**
 * @author kevin
 * @since jdk1.6
 */
public interface ILeaderService extends IBaseService<LeaderInfo> {

    int generateLeaderJson(String absolutePath) throws CommonServiceException;

}
