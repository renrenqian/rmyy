/**
 * IDoctorServiceImpl.java
 * kevin 2012-6-17
 * @version 0.1
 */
package com.kevin.group.service.member.impl;

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
import com.kevin.group.constance.GroupConstance;
import com.kevin.group.dao.dept.IDeptDAO;
import com.kevin.group.dao.member.IDoctorDAO;
import com.kevin.group.pojo.dept.Dept;
import com.kevin.group.pojo.member.DoctorInfo;
import com.kevin.group.service.member.IDoctorService;

/**
 * @author kevin
 * @since jdk1.6
 */
@Service("doctorService")
public class DoctorServiceImpl extends AbstractBaseService<DoctorInfo> implements
        IDoctorService {
     private IDoctorDAO  doctorDAO;
     private IDeptDAO  deptDAO;

    /**
     * @param doctorDAO the doctorDAO to set
     */
     @Autowired
    public final void setDoctorDAO(IDoctorDAO doctorDAO) {
         setAbsBaseDao(doctorDAO);
        this.doctorDAO = doctorDAO;
    }

     /**
     * @param deptDAO the deptDAO to set
     */
     @Autowired
    public final void setDeptDAO(IDeptDAO deptDAO) {
        this.deptDAO = deptDAO;
    }

    @Override
     public PageBean<DoctorInfo> list(PageBean<DoctorInfo> page)
             throws CommonServiceException {
         return super.list(page);
     }
     
     public int generateDoctJson(String savePath) throws CommonServiceException{
         try {
         List<Dept>  deptList =  deptDAO.listDeptNames();
         StringBuilder jsonBuff = null;
         String CTRF = GroupConstance.CTRF;
         int result = 0;
         if(null != deptList){
             jsonBuff = new StringBuilder();
             jsonBuff.append("{\"deptIds\":[");
             for(Dept dept : deptList)
                 jsonBuff.append(CTRF).append(dept.generateJSON()).append(",");
             if(deptList.size() > 0)
                 jsonBuff.deleteCharAt(jsonBuff.length() -1 );
             jsonBuff.append(CTRF).append("],").append(CTRF);
             result = zjylDoctJson(deptList, jsonBuff.toString(), savePath);
             result = mymzDoctJson(deptList, jsonBuff.toString(), savePath);
//             DoctorInfo doct = new DoctorInfo();
//             for(Dept dept : deptList){
//                 doct.setDiDeptType(dept.getDpId());
//                 jsonBuff.append("\"doctList_" + dept.getDpId() + "\":[");
//                 List<DoctorInfo> doctList = doctorDAO.list(doct);
//                 if(null!= doctList){
//                     for(DoctorInfo doctj : doctList) 
//                         jsonBuff.append(CTRF).append(doctj.generateJSON()).append(",");
//                     if(doctList.size() > 0)
//                         jsonBuff.deleteCharAt(jsonBuff.length() -1);
//                 }
//                 jsonBuff.append(CTRF).append("],").append(CTRF);
//                 
//             }
//             jsonBuff.append(" \"resultCode\": 1}");
//             writeJsonFile(savePath + File.separator + "zjyl", jsonBuff);// generate the zjyl file
             jsonBuff.setLength(0);//clear the buffer
         }
         return result;
        } catch (BaseSqlMapException e) {
            throw new CommonServiceException(e.getMessage());
        }
     }

     private int zjylDoctJson(List<Dept>  deptList, String jsonBuffDept, String savePath) throws CommonServiceException{
         try {
             String CTRF = GroupConstance.CTRF;
             StringBuilder jsonBuff = new StringBuilder();
             jsonBuff.append(jsonBuffDept);
             DoctorInfo doct = new DoctorInfo();
             for(Dept dept : deptList){
                 doct.setDiDeptType(dept.getDpId());
                 jsonBuff.append("\"doctList_" + dept.getDpId() + "\":[");
                 List<DoctorInfo> doctList = doctorDAO.listzjyl(doct);
                 if(null!= doctList){
                     for(DoctorInfo doctj : doctList) 
                         jsonBuff.append(CTRF).append(doctj.generateJSON()).append(",");
                     if(doctList.size() > 0)
                         jsonBuff.deleteCharAt(jsonBuff.length() -1);
                 }
                 jsonBuff.append(CTRF).append("],").append(CTRF);
                 
             }
             jsonBuff.append(" \"resultCode\": 1}");
             writeJsonFile(savePath + File.separator + "zjyl", jsonBuff);// generate the zjyl file
             jsonBuff.setLength(0);//clear the buffer
             return 1;
         }catch (BaseSqlMapException e) {
             throw new CommonServiceException(e.getMessage());
         } catch (CommonServiceException e) {
             throw new CommonServiceException(e.getMessage());
        }
     }

     private int mymzDoctJson(List<Dept>  deptList, String jsonBuffDept, String savePath) throws CommonServiceException{
         try {
             String CTRF = GroupConstance.CTRF;
             StringBuilder jsonBuff = new StringBuilder();
             jsonBuff.append(jsonBuffDept);
             DoctorInfo doct = new DoctorInfo();
             for(Dept dept : deptList){
                 doct.setDiDeptType(dept.getDpId());
                 jsonBuff.append("\"doctList_" + dept.getDpId() + "\":[");
                 List<DoctorInfo> doctList = doctorDAO.listmymz(doct);
                 if(null!= doctList){
                     for(DoctorInfo doctj : doctList) 
                         jsonBuff.append(CTRF).append(doctj.generateJSON()).append(",");
                     if(doctList.size() > 0)
                         jsonBuff.deleteCharAt(jsonBuff.length() -1);
                 }
                 jsonBuff.append(CTRF).append("],").append(CTRF);
                 
             }
             jsonBuff.append(" \"resultCode\": 1}");
             writeJsonFile(savePath + File.separator + "mymz", jsonBuff);// generate the mymz file
             jsonBuff.setLength(0);//clear the buffer
             return 1;
         }catch (BaseSqlMapException e) {
             throw new CommonServiceException(e.getMessage());
         } catch (CommonServiceException e) {
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
