/**
 * ContentInfoAction.java
 * kevin 2012-6-16
 * @version 0.1
 */
package com.kevin.group.action.content;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.googlecode.jsonplugin.annotations.JSON;
import com.kevin.common.action.AbstractBaseAction;
import com.kevin.common.exception.CommonServiceException;
import com.kevin.group.pojo.content.ContentInfo;
import com.kevin.group.service.content.IContentInfoService;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ModelDriven;

/**
 * @author kevin
 * @since jdk1.6
 */
@Component("contentInfoAction")
@Scope("prototype")
public class ContentInfoAction extends AbstractBaseAction implements
        ModelDriven<ContentInfo> {

    private IContentInfoService contInfoService;
    private ContentInfo continfo;
    private List<ContentInfo> contList;
    private List<Serializable> ides;
    private String attfileContentType;
    private String savePath;
    private File file;
    private String fileFileName;
    private InputStream fileInputStream;
    private String fileContentType;

    /**
     * @return the continfo
     */
    public final ContentInfo getContinfo() {
        return continfo;
    }

    /**
     * @param continfo
     *            the continfo to set
     */
    public final void setContinfo(ContentInfo continfo) {
        this.continfo = continfo;
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
     * @return the file
     */
    public final File getFile() {
        return file;
    }

    /**
     * @param file
     *            the file to set
     */
    public final void setFile(File file) {
        this.file = file;
    }

    /**
     * @return the fileFileName
     */
    public final String getFileFileName() {
        return fileFileName;
    }

    /**
     * @param fileFileName
     *            the fileFileName to set
     */
    public final void setFileFileName(String fileFileName) {
        this.fileFileName = fileFileName;
    }

    /**
     * @return the fileContentType
     */
    public final String getFileContentType() {
        return fileContentType;
    }

    /**
     * @param fileContentType
     *            the fileContentType to set
     */
    public final void setFileContentType(String fileContentType) {
        this.fileContentType = fileContentType;
    }

    /**
     * @param fileInputStream
     *            the fileInputStream to set
     */
    public final void setFileInputStream(InputStream fileInputStream) {
        this.fileInputStream = fileInputStream;
    }

    /**
     * @param attFileContentType
     *            the attFileContentType to set
     */
    public final void setAttFileContentType(String attFileContentType) {
        this.attfileContentType = attFileContentType;
    }

    /**
     * @return the attfileContentType
     */
    public final String getAttfileContentType() {
        return attfileContentType;
    }

    /**
     * @param attfileContentType
     *            the attfileContentType to set
     */
    public final void setAttfileContentType(String attfileContentType) {
        this.attfileContentType = attfileContentType;
    }

    /**
     * @return the savePath
     */
    public final String getSavePath() {
        return savePath;
    }

    /**
     * @param savePath
     *            the savePath to set
     */
    public final void setSavePath(String savePath) {
        this.savePath = savePath;
    }

    /**
     * @param contInfoService
     *            the contInfoService to set
     */
    @Autowired
    public final void setContInfoService(IContentInfoService contInfoService) {
        this.contInfoService = contInfoService;
    }

    @JSON(serialize = false)
    public InputStream getFileInputStream() {
        // 以服务器的文件保存地址和原文件名建立上传文件输出流
        try {
            FileOutputStream fos = new FileOutputStream(this.getSavePath()
                    + "\\" + file.getName());
            FileInputStream fis = new FileInputStream(getFile());
            byte[] buffer = new byte[1024];
            int len = 0;
            while ((len = fis.read(buffer)) > 0) {
                fos.write(buffer, 0, len);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        // String path = getRequest().getParameter("ac.AcPostUrl");
        // File materialFile = null;
        // String content = getRequest().getParameter("content");
        // try {
        // if(content!=null && content.equals("content")){
        // String basePath = "files";
        // materialFile = new File(basePath,new
        // String(path.getBytes("iso-8859-1"),"utf-8"));
        // }else{
        // materialFile = new File(new
        // String(path.getBytes("iso-8859-1"),"utf-8"));
        // }
        // } catch (UnsupportedEncodingException e1) {
        // setMessage("utf-8编码不支持");
        // }
        // try {
        // fileInputStream = new FileInputStream(materialFile);
        // setResultCode(1);
        // } catch (FileNotFoundException e) {
        // setResultCode(-1);
        // setMessage("素材预览异常,可能要预览的素材文件丢失");
        // }
        return fileInputStream;
    }

    public String addContent() {// add new contInfo
        try {
            Serializable id = contInfoService.save(continfo);
            setResultCode((Integer) id);
        } catch (CommonServiceException e) {
            setMessage(e.getMessage());
            setResultCode(-1);
        }
        return Action.SUCCESS;
    }

    public String searchContent() {// search contInfo
        try {
            this.continfo = contInfoService.search(continfo);
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
            int result = contInfoService.update(continfo);
            setResultCode(result);
        } catch (CommonServiceException e) {
            setMessage(e.getMessage());
            setResultCode(-1);
        }
        return Action.SUCCESS;
    }

    public String deleteContent() {// delete the contInfo
        try {
            int result = contInfoService.delete(continfo);
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

    @Override
    public ContentInfo getModel() {
        // TODO Auto-generated method stub
        return continfo;
    }
}
