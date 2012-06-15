/**
 * FtpUploader.java
 */
package com.kevin.common.util;

import it.sauronsoftware.ftp4j.FTPClient;
import it.sauronsoftware.ftp4j.FTPFile;

import java.io.File;
/**
 * 文件上传者
 * @author jiangyaniu
 * @since 2011-12-05 11:48
 */
public class FtpUploader {
    private String host;
    private int port;
    private String user;
    private String pwd;
    private String encoding;
    private String destFolder;
    public FtpUploader(String host, int port, String user, String pwd,String destFolder) {
        this.host = host;
        this.port = port;
        this.user = user;
        this.pwd = pwd;
        this.destFolder=destFolder;
    }
    public boolean uploadFtp(File file) {
        boolean flag = false;
        FTPClient ftpClient = new FTPClient();
        try {
            ftpClient.connect(host, port);
            ftpClient.setPassive(true);
            ftpClient.setType(FTPClient.TYPE_BINARY);//设置以二进制流的方式传输
            if(encoding==null){
                encoding = "utf-8";
            }
            ftpClient.setCharset(encoding);
            ftpClient.setAutoNoopTimeout(5*60*1000);
            ftpClient.login(user, pwd);
            if(ftpClient.isConnected()){
                if (destFolder != null&& !"".equals(destFolder)) {
                    FTPFile[] ftpFiles = ftpClient.list();
                    boolean exist=false;
                    if(ftpFiles!=null){
                        for(FTPFile ff:ftpFiles){
                            if(ff.getType() == FTPFile.TYPE_DIRECTORY){
                                if(ff.getName().equals(destFolder)){
                                    exist = true;
                                    break;
                                }
                            }
                        }
                    }
                    if(!exist){
                        ftpClient.createDirectory(destFolder);
                    }
                    ftpClient.changeDirectory(destFolder);
                } //判断目录存不存在?
                ftpClient.upload(file);
                flag = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
            //NOThing to do
            return false;
        }finally{
            if (ftpClient.isConnected()) {
                try {
                    ftpClient.disconnect(true);
                } catch (Exception e) {
                    System.out.println("关闭FTP连接失败:"+e.getMessage());
                    e.printStackTrace();
                }
            }
        }
        return flag;
    }
}