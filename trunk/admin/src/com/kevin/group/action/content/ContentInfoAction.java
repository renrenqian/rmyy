/**
 * ContentInfoAction.java
 * kevin 2012-6-16
 * @version 0.1
 */
package com.kevin.group.action.content;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.kevin.common.action.AbstractBaseAction;
import com.kevin.common.exception.CommonServiceException;
import com.kevin.group.pojo.content.ContentInfo;
import com.kevin.group.service.content.IContentInfoService;
import com.opensymphony.xwork2.Action;

/**
 * @author kevin
 * @since jdk1.6
 */
@Component("contentInfoAction")
@Scope("prototype")
public class ContentInfoAction extends AbstractBaseAction {

    private IContentInfoService contInfoService;
    private ContentInfo contInfo;
    private List<ContentInfo> contList;
    private List<Serializable> ides;

    /**
     * @return the contInfo
     */
    public final ContentInfo getContInfo() {
        return contInfo;
    }

    /**
     * @param contInfo
     *            the contInfo to set
     */
    public final void setContInfo(ContentInfo contInfo) {
        this.contInfo = contInfo;
    }

    /**
     * @return the contList
     */
    public final List<ContentInfo> getContList() {
        return contList;
    }

    /**
     * @param contList
     *            the contList to set
     */
    public final void setContList(List<ContentInfo> contList) {
        this.contList = contList;
    }

    /**
     * @param contInfoService
     *            the contInfoService to set
     */
    @Autowired
    public final void setContInfoService(IContentInfoService contInfoService) {
        this.contInfoService = contInfoService;
    }

    public String addContent() {// add new contInfo
        try {
            Serializable id = contInfoService.save(contInfo);
            setResultCode((Integer) id);
        } catch (CommonServiceException e) {
            setMessage(e.getMessage());
            setResultCode(-1);
        }
        return Action.SUCCESS;
    }

    public String searchContent() {// search contInfo
        try {
            this.contInfo = contInfoService.search(contInfo);
            setResultCode(1);
        } catch (CommonServiceException e) {
            setMessage(e.getMessage());
            setResultCode(-1);
        }
        return Action.SUCCESS;
    }

    public String listContent() {// list all the contInfo
        try {
            contList = contInfoService.listAll();
            setResultCode(1);
        } catch (CommonServiceException e) {
            setMessage(e.getMessage());
            setResultCode(-1);
        }
        return Action.SUCCESS;
    }

    public String updateContent() {// update the contInfo info
        try {
            int result = contInfoService.update(contInfo);
            setResultCode(result);
        } catch (CommonServiceException e) {
            setMessage(e.getMessage());
            setResultCode(-1);
        }
        return Action.SUCCESS;
    }

    public String deleteContent() {// delete the contInfo
        try {
            int result = contInfoService.delete(contInfo);
            setResultCode(result);
        } catch (CommonServiceException e) {
            setMessage(e.getMessage());
            setResultCode(-1);
        }
        return Action.SUCCESS;
    }

    public String batchDeleteContent() {// bath delete the contInfo
        try {
            int result = contInfoService.batchDelete(ides);
            setResultCode(result);
        } catch (CommonServiceException e) {
            setMessage(e.getMessage());
            setResultCode(-1);
        }
        return Action.SUCCESS;
    }
}
