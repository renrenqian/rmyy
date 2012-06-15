package com.kevin.common.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URI;
import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;

public final class FileMD5Util {
    public static String getMD5(String fileName) {
        return getMD5(new File(fileName));
    }

    public static String getMD5(File file) {
        FileInputStream fis = null;
        try {
            fis = new FileInputStream(file);
            return getMD5(fis);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
    public static String writeFile(File file,String fileName) throws IOException {
        FileInputStream fis = null;
        try {
            fis = new FileInputStream(file);
            return writeFile(fis,"MD5",fileName);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    } 
    public static String getMD5(URL url) {
        try {
            return getMD5(url.openStream());
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String getMD5(URI uri) {
        try {
            return getMD5(uri.toURL());
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String getMD5(InputStream in) {
        return getMD5ORSHA(in, "MD5");

    }

    private static String getMD5ORSHA(InputStream in, String md5) {
        try {
            MessageDigest md = MessageDigest.getInstance(md5);
            byte[] buffer = new byte[8192];
            int length = -1;
            while ((length = in.read(buffer)) != -1) {
                md.update(buffer, 0, length);
            }
            return bytesToString(md.digest());
        } catch (IOException ex) {
            return null;
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(FileMD5Util.class.getName()).log(Level.SEVERE,
                    null, ex);
            return null;
        } finally {
            try {
                in.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }
    private static String writeFile(InputStream in, String md5,String fileName) throws IOException {
        OutputStream out = null;
        try {
            MessageDigest md = MessageDigest.getInstance(md5);
            out = new FileOutputStream(new File(fileName));
            byte[] buffer = new byte[1024*64];
            int length = -1;
            while ((length = in.read(buffer)) != -1) {
                out.write(buffer);
                md.update(buffer, 0, length);
            }
            return bytesToString(md.digest());
        } catch (NoSuchAlgorithmException ex) {
            ex.printStackTrace();
            return null;
        } finally {
            try {
                if(in != null){
                    in.close();
                }
                if(out != null){
                    out.close();
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }
    public static String getSHA(InputStream in) {
        return getMD5ORSHA(in, "SHA");
    }

    public static String getSHA(File file) {
        try {
            return getSHA(new FileInputStream(file));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String getSHA(String fileName) {
        return getSHA(new File(fileName));
    }

    public static String getSHA(URL url) {
        try {
            return getSHA(url.openStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String getSHA(URI uri) {
        try {
            return getSHA(uri.toURL().openStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String bytesToString(byte[] data) {
        char hexDigits[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
                'a', 'b', 'c', 'd', 'e', 'f' };
        char[] temp = new char[data.length * 2];
        for (int i = 0; i < data.length; i++) {
            byte b = data[i];
            temp[i * 2] = hexDigits[b >>> 4 & 0x0f];
            temp[i * 2 + 1] = hexDigits[b & 0x0f];
        }
        return new String(temp);
    }
}