/**
 * ImageMetaDataUtils.java
 */
package com.kevin.common.util;

import java.awt.color.ColorSpace;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;

/**
 * 获得图片的元数据工具
 * 
 * @author jiangyn
 * 
 */
public class ImageMetaDataUtils {
    public static Map<String, String> getMetaData(String fullFileName) {
        File _file = new File(fullFileName); // 读入文件
        BufferedImage src;
        Map<String, String> metaDataMap = new HashMap<String, String>();
        metaDataMap.put("format", getExtName(fullFileName));
        try {
            src = ImageIO.read(_file);
            int width = src.getWidth(); // 得到源图宽
            int height = src.getHeight(); // 得到源图长
            int type = src.getColorModel().getColorSpace().getType();
            System.out.println("清晰度吗?:"+src.getColorModel().getPixelSize());
            metaDataMap.put("colorModel", getColorModel(type));
            metaDataMap.put("height", height + "");
            metaDataMap.put("width", width + "");
        } catch (IOException e) {
            // Nothing
        } // 构造Image对象
        return metaDataMap;
    }

    private static String getExtName(String fullFileName) {
        int pos = fullFileName.lastIndexOf(".");
        return fullFileName.substring(pos + 1);
    }

    private static String getColorModel(int type) {
        String cm;
        switch (type) {
        case ColorSpace.TYPE_XYZ:
            cm = "Xyz";
            break;
        case ColorSpace.TYPE_Lab:
            cm = "Lab";
            break;
        case ColorSpace.TYPE_Luv:
            cm = "Luv";
            break;
        case ColorSpace.TYPE_YCbCr:
            cm = "YCbCr";
            break;
        case ColorSpace.TYPE_Yxy:
            cm = "Yxy";
            break;
        case ColorSpace.TYPE_RGB:
            cm = "RGB";
            break;
        case ColorSpace.TYPE_GRAY:
            cm = "GRAY";
            break;
        case ColorSpace.TYPE_HSV:
            cm = "Hsv";
            break;
        case ColorSpace.TYPE_HLS:
            cm = "Hls";
            break;
        case ColorSpace.TYPE_CMYK:
            cm = "Cmyk";
            break;
        case ColorSpace.TYPE_CMY:
            cm = "Cmy";
            break;
        default:
            cm = "RGB";
        }

        return cm;
    }
//    public static void main(String[]args){
//         System.out.println("色彩模式:"+getMetaData("E:\\rgb.jpg").get("colorModel")); 
//         System.out.println("色彩模式:"+getMetaData("E:\\cmyk.jpg").get("colorModel")); 
//         System.out.println("色彩模式:"+getMetaData("E:\\huidu.jpg").get("colorModel")); 
//    }
}