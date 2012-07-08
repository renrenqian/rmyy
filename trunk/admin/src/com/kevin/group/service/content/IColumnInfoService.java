/**
 * IColumnInfoService.java
 * kevin 2012-6-20
 * @version 0.1
 */
package com.kevin.group.service.content;

import java.util.List;

import com.kevin.common.exception.CommonServiceException;
import com.kevin.common.service.IBaseService;
import com.kevin.group.pojo.content.ColumnInfo;

/**
 * @author kevin
 * @since jdk1.6
 */
public interface IColumnInfoService extends IBaseService<ColumnInfo> {

    List<ColumnInfo> listColumnNames() throws CommonServiceException;

}
