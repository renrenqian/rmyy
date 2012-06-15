/**
 * 
 */
package com.kevin.common.service.system;

import com.kevin.common.exception.CommonServiceException;
import com.kevin.common.pojo.system.SystemParameter;
import com.kevin.common.service.IBaseService;

/**
 * @author wj
 * 
 */
public interface ISystemParameterService extends IBaseService<SystemParameter> {
   public String getParamValueByParamCode(String code)throws CommonServiceException;
}
