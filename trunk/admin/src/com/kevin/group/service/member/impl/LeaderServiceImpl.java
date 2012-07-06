/**
 * ILeaderServiceImpl.java
 * kevin 2012-6-17
 * @version 0.1
 */
package com.kevin.group.service.member.impl;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kevin.common.exception.BaseSqlMapException;
import com.kevin.common.exception.CommonServiceException;
import com.kevin.common.pojo.PageBean;
import com.kevin.common.service.AbstractBaseService;
import com.kevin.group.dao.member.ILeaderDAO;
import com.kevin.group.pojo.member.LeaderInfo;
import com.kevin.group.service.member.ILeaderService;

/**
 * @author kevin
 * @since jdk1.6
 */
@Service("leaderService")
public class LeaderServiceImpl extends AbstractBaseService<LeaderInfo>
        implements ILeaderService {

    private ILeaderDAO leaderDAO;

    /**
     * @param leaderDAO
     *            the leaderDAO to set
     */
    @Autowired
    public final void setLeaderDAO(ILeaderDAO leaderDAO) {
        setAbsBaseDao(leaderDAO);
        this.leaderDAO = leaderDAO;
    }

    @Override
    public PageBean<LeaderInfo> list(PageBean<LeaderInfo> page)
            throws CommonServiceException {
        return super.list(page);
    }

    /* (non-Javadoc)
     * @see com.kevin.group.service.member.ILeaderService#generateLeaderJson(java.lang.String)
     */
    @Override
    public int generateLeaderJson(String absolutePath) throws CommonServiceException {
        try {
            LeaderInfo leader = new LeaderInfo();
            // get the current leader info
            leader.setLiCate(1);
            StringBuilder jsonBuff = null;
            String CTRF = "\r\n";
            jsonBuff = new StringBuilder();
            List<LeaderInfo> leaderList1 = leaderDAO.list(leader);
            jsonBuff.append("{").append(CTRF);
            if(null != leaderList1){
                jsonBuff.append("\"leaderList1\":[");
                for(LeaderInfo lead: leaderList1)
                    jsonBuff.append(CTRF).append(lead.generateJSON()).append(",");
                if(leaderList1.size() > 0)
                    jsonBuff.deleteCharAt(jsonBuff.length() -1);
                jsonBuff.append(CTRF).append("],").append(CTRF);
            }
            // get the passed leader info
            leader.setLiCate(0);
            List<LeaderInfo> leaderList0 = leaderDAO.list(leader);
            if(null != leaderList0){
                jsonBuff.append("\"leaderList0\":[");
                for(LeaderInfo lead: leaderList0)
                    jsonBuff.append(CTRF).append(lead.generateJSON()).append(",");
                if(leaderList0.size() > 0)
                    jsonBuff.deleteCharAt(jsonBuff.length() -1);
                jsonBuff.append(CTRF).append("],").append(CTRF).append("\"resultCode\": 1}").append(CTRF);
            }
            writeJsonFile(absolutePath, jsonBuff);
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
