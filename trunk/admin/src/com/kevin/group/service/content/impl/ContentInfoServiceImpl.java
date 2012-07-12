/**
 * ContentInfoServiceImpl.java
 * kevin 2012-6-16
 * @version 0.1
 */
package com.kevin.group.service.content.impl;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kevin.common.exception.BaseSqlMapException;
import com.kevin.common.exception.CommonServiceException;
import com.kevin.common.pojo.PageBean;
import com.kevin.common.service.AbstractBaseService;
import com.kevin.group.constance.GroupConstance;
import com.kevin.group.dao.content.IContentInfoDAO;
import com.kevin.group.pojo.content.ContentInfo;
import com.kevin.group.service.content.IContentInfoService;

/**
 * @author kevin
 * @since jdk1.6
 */
@Service("contInfoService")
public class ContentInfoServiceImpl extends AbstractBaseService<ContentInfo>
        implements IContentInfoService {

    private IContentInfoDAO contInfoDAO;

    /**
     * @param contInfoDAO the contInfoDAO to set
     */
    @Autowired
    public final void setContInfoDAO(IContentInfoDAO contInfoDAO) {
        setAbsBaseDao(contInfoDAO);
        this.contInfoDAO = contInfoDAO;
    }

    @Override
    public PageBean<ContentInfo> list(PageBean<ContentInfo> page)
            throws CommonServiceException {
        return super.list(page);
    }

    /* (non-Javadoc)
     * @see com.kevin.common.service.AbstractBaseService#save(java.lang.Object)
     */
    @Override
    public Serializable save(ContentInfo cont) throws CommonServiceException {
//        HttpServletRequest request = ServletActionContextUtil.getRequest();
//        HttpSession session = request.getSession(false);
//        //session.getId();
//        OnlineUser onlineUser = IUserInfoService.ONLINEUSERMAP.get(session.getId());
//        if(null == onlineUser){
//            cont.setContAuthor(0);
//        } else {
//            cont.setContAuthor(onlineUser.getUserInfo().getUiId());
//        }
        return super.save(cont);
    }

    /* (non-Javadoc)
     * @see com.kevin.group.service.content.IContentInfoService#auditorContent(com.kevin.group.pojo.content.ContentInfo)
     */
    @Override
    public int auditorContent(ContentInfo continfo) throws CommonServiceException {
        try {
            return contInfoDAO.auditorContent(continfo);
        } catch (BaseSqlMapException e) {
            throw new CommonServiceException(e.getMessage());
        }
    }

    /* (non-Javadoc)
     * @see com.kevin.group.service.content.IContentInfoService#updateClickContent(com.kevin.group.pojo.content.ContentInfo)
     */
    @Override
    public int updateClickContent(ContentInfo continfo) throws CommonServiceException {
        try {
            return contInfoDAO.updateClickContent(continfo);
        } catch (BaseSqlMapException e) {
            throw new CommonServiceException(e.getMessage());
        }
    }

    /* (non-Javadoc)
     * @see com.kevin.group.service.content.IContentInfoService#generateHomeJson(com.kevin.group.pojo.content.ContentInfo)
     */
    @Override
    public int generateHomeJson(String savePath) throws CommonServiceException {
        try {
            ContentInfo cont = new ContentInfo();
            cont.setColId(1201);// 医院公告
            List<ContentInfo> contList = contInfoDAO.generateHomeyyggJson(cont);
            StringBuilder jsonBuff = null;
            String CTRF = GroupConstance.CTRF;
            if(null != contList) {
                jsonBuff = new StringBuilder();
                jsonBuff.append("{\"newsyygg\":[");
                for(ContentInfo anounce : contList){
                    jsonBuff.append(CTRF).append(anounce.generateJSON()).append(",");
                }
                if(contList.size() > 0)
                    jsonBuff.deleteCharAt(jsonBuff.length() -1 );
                jsonBuff.append(CTRF).append("],").append(CTRF);
            }
            cont.setColId(1202);// 医院新闻
            contList = contInfoDAO.generateHomeyyxwJson(cont);
            if(null != contList) {
                // add the detail info
                int contSize = 0;
                jsonBuff.append("\"newsyyxw0\":[");
                if(contList.size() > 0){
                    jsonBuff.append(CTRF).append(contList.get(0).generateJSON());
                }
                jsonBuff.append(CTRF).append("],").append(CTRF);
                // do the next list data
                jsonBuff.append("\"newsyyxw\":[");
                int total = contList.size() - 1;
                for(contSize = 1; contSize < total; contSize++){
                    cont = contList.get(contSize);
                    cont.setContDetail(null);
                    jsonBuff.append(CTRF).append(cont.generateJSON()).append(",");
                }
                if(contList.size() > 0)
                    jsonBuff.deleteCharAt(jsonBuff.length() -1 );
                jsonBuff.append(CTRF).append("],").append(CTRF);
            }
            jsonBuff.append(" \"resultCode\": 1}");
            writeJsonFile(savePath + File.separator + "homeNews", jsonBuff);
            jsonBuff.setLength(0);//clear the buffer
            return 1;
        } catch (BaseSqlMapException e) {
            throw new CommonServiceException(e.getMessage());
        }
    }

    private void writeJsonFile(String filePath, StringBuilder fileContent) throws CommonServiceException{
        try {
            FileOutputStream fos = new FileOutputStream(filePath);
            Writer out = new OutputStreamWriter(fos, "UTF-8");
            out.write(fileContent.toString());
            out.close();
            fos.close();
        } catch (FileNotFoundException e) {
            throw new CommonServiceException("文件读取失败。");
        } catch (UnsupportedEncodingException e) {
            throw new CommonServiceException("编码格式不支持。");
        } catch (IOException e) {
            throw new CommonServiceException("文件流关闭失败。");
        }
    }
}
