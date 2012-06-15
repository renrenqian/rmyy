/**
 * ZIPUtil.java
 */
package com.kevin.common.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.tools.zip.ZipEntry;
import org.apache.tools.zip.ZipOutputStream;
/**
 * @Title: GZIPUtil.java
 * @Description: gzip文件压缩和解压缩工具类
 * @author yaniuniu
 * @date 2011-12-17 18:10:29
 * @version V1.0
 */
public class ZIPUtil {
    public static boolean CreateZipFile(String filePath, String zipFilePath) {
        FileOutputStream fos = null;
        ZipOutputStream zos = null;
        try {
            fos = new FileOutputStream(zipFilePath);
            zos = new ZipOutputStream(fos);
            boolean flag = writeZipFile(new File(filePath), zos, "");
            if(!flag){
                return flag;
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return false;
        } finally {
            try {
                if (zos != null){
                    zos.close();//关闭流
                }
            } catch (IOException e) {
                e.printStackTrace();//关闭流异常
                return false;
            }
            try {
                if (fos != null){
                    fos.close();//关闭流
                }
            } catch (IOException e) {
                e.printStackTrace();//关闭流异常
                return false;
            }
        }
        return true;
    }
    private static boolean writeZipFile(File f, ZipOutputStream zos, String hiberarchy) {
        if (f.exists()) {
            if (f.isDirectory()) {
                hiberarchy += f.getName() + "/";
                File[] fif = f.listFiles();
                for (int i = 0; i < fif.length; i++) {
                    writeZipFile(fif[i], zos, hiberarchy);
                }

            } else {
                FileInputStream fis = null;
                try {
                    fis = new FileInputStream(f);
                    ZipEntry ze = new ZipEntry(hiberarchy + f.getName());
                    zos.putNextEntry(ze);
                    byte[] b = new byte[1024];
                    while (fis.read(b) != -1) {
                        zos.write(b);
                        b = new byte[1024];
                    }
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                    return false;
                } catch (IOException e) {
                    e.printStackTrace();
                    return false;
                } finally {
                    try {
                        if (fis != null){
                            fis.close();
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                        return false;
                    }
                }

            }
        }
        return true;
    }
    public static void main(String[] args) {
        ZIPUtil.CreateZipFile("E:/zl",
                "e:/zl.zip");
    }
}