/**
 * DeptServicesImpl.java
 * kevin 2012-6-16
 * @version 0.1
 */
package com.kevin.group.service.dept.impl;

import java.io.File;
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
import com.kevin.group.dao.dept.IDeptDAO;
import com.kevin.group.dao.dept.IDeptGenreDAO;
import com.kevin.group.pojo.dept.Dept;
import com.kevin.group.pojo.dept.DeptGenre;
import com.kevin.group.service.dept.IDeptServices;

/**
 * @author kevin
 * @since jdk1.6
 */
@Service("deptService")
public class DeptServicesImpl extends AbstractBaseService<Dept> implements IDeptServices {

    private IDeptDAO deptDAO;
    private IDeptGenreDAO deptGenreDAO;

    /**
     * @param deptDAO the deptDAO to set
     */
    @Autowired
    public final void setDeptDAO(IDeptDAO deptDAO) {
        setAbsBaseDao(deptDAO);
        this.deptDAO = deptDAO;
    }

    @Override
    public PageBean<Dept> list(PageBean<Dept> page)
            throws CommonServiceException {
        return super.list(page);
    }

    /**
     * @param deptGenreDAO the deptGenreDAO to set
     */
    @Autowired
    public final void setDeptGenreDAO(IDeptGenreDAO deptGenreDAO) {
        this.deptGenreDAO = deptGenreDAO;
    }

    @Override
    public List<Dept> listDeptNames() throws CommonServiceException   {
          try {
            return  deptDAO.listDeptNames();
        } catch (BaseSqlMapException e) {
            throw new CommonServiceException(e.getMessage());
        }
    }

    /* (non-Javadoc)
     * @see com.kevin.group.service.dept.IDeptServices#generateDeptJson()
     */
    @Override
    public int generateDeptJson(String savePath) throws CommonServiceException {
       try {
        List<DeptGenre> dglist = deptGenreDAO.listAll();
        StringBuilder jsonBuff = null;
        String CTRF = "\r\n";
        if(null != dglist){
            jsonBuff = new StringBuilder();
            Dept dept = new Dept();
            for(DeptGenre dg : dglist){
                dept.setDpType(dg.getDgName());
                List<Dept> deptList = deptDAO.list(dept);
                if(null != deptList){
                    jsonBuff.append("{\"deptList\":[");
                    for(Dept dp : deptList)
                        jsonBuff.append(CTRF).append(dp.generateJSON()).append(",");
                    if(deptList.size()> 0)// avoid to trim the [ char when the dept list size is 0 and not null
                        jsonBuff.deleteCharAt(jsonBuff.length() -1);
                    jsonBuff.append(CTRF).append("],").append(CTRF).append("\"resultCode\": 1}");
                }
                writeJsonFile(savePath + File.separator + dg.getDgCode(), jsonBuff);
                jsonBuff.setLength(0);//clear the buffer
            }
        }
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
