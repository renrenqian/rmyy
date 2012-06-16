/**
 * ISystemInfoService.java
 */
package com.kevin.common.service.system;

import com.kevin.common.exception.CommonServiceException;
import com.kevin.common.pojo.system.SystemInfo;
import com.kevin.common.service.IBaseService;

public interface ISystemInfoService extends IBaseService<SystemInfo> {
    SystemInfo getSystemInfo()throws CommonServiceException;
}
