/**
 * IContentInfoService.java
 * kevin 2012-6-16
 * @version 0.1
 */
package com.kevin.group.service.content;

import com.kevin.common.exception.CommonServiceException;
import com.kevin.common.service.IBaseService;
import com.kevin.group.pojo.content.ContentInfo;

/**
 * @author kevin
 * @since jdk1.6
 */
public interface IContentInfoService extends IBaseService<ContentInfo> {

    int auditorContent(ContentInfo continfo) throws CommonServiceException;

}
