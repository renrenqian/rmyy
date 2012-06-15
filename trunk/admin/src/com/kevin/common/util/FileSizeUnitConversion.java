/**
 * FileSizeUnitConversion.java
 * kevin 2012-5-16
 * @version 0.1
 */
package com.kevin.common.util;

import java.math.BigDecimal;

/**
 * @author kevin
 * @since jdk1.6
 */
public class FileSizeUnitConversion {

    // bt byte refer
    public static final long SIZE_BT = 1024L;
    // KB byte refer
    public static final long SIZE_KB = SIZE_BT * 1024L;
    // MB byte refer
    public static final long SIZE_MB = SIZE_KB * 1024L;
    // GB byte refer
    public static final long SIZE_GB = SIZE_MB * 1024L;
    // TB byte refer
    public static final long SIZE_TB = SIZE_GB * 1024L;

    public static final int SACLE = 2;

    // file size property
    private long longSize;

    /**
     * if file size contain non number return empty string, other convert the string
     * @param fileSize
     * @return
     */
    public String convertUnit(String fileSize){
        try{
            longSize = Long.valueOf(fileSize);
        } catch(NumberFormatException e) {
            return "";
        }
        return convert();
    }
    
    private String convert() throws RuntimeException {
        if (this.longSize >= 0 && this.longSize < SIZE_BT) {
            return this.longSize + "B";
        } else if (this.longSize >= SIZE_BT && this.longSize < SIZE_KB) {
            return this.longSize / SIZE_BT + "KB";
        } else if (this.longSize >= SIZE_KB && this.longSize < SIZE_MB) {
            return this.longSize / SIZE_KB + "MB";
        } else if (this.longSize >= SIZE_MB && this.longSize < SIZE_GB) {
            BigDecimal longs = new BigDecimal(Double
                    .valueOf(this.longSize + "").toString());
            BigDecimal sizeMB = new BigDecimal(Double.valueOf(SIZE_MB + "")
                    .toString());
            String result = longs.divide(sizeMB, SACLE,
                    BigDecimal.ROUND_HALF_UP).toString();
            // double result=this.longSize/(double)SIZE_MB;
            return result + "GB";
        } else {
            BigDecimal longs = new BigDecimal(Double
                    .valueOf(this.longSize + "").toString());
            BigDecimal sizeMB = new BigDecimal(Double.valueOf(SIZE_GB + "")
                    .toString());
            String result = longs.divide(sizeMB, SACLE,
                    BigDecimal.ROUND_HALF_UP).toString();
            return result + "TB";
        }
    }

}
