/**
 * UnitsConversion.java
 * kevin 2012-5-10
 * @version 0.1
 */
package com.kevin.common.util;

import java.text.DecimalFormat;
import java.util.Hashtable;

/**
 * @author kevin
 * @since jdk1.6
 */
public class UnitsConversion extends DecimalFormat {

    private static final long serialVersionUID = 3168068393840262910L;
    /**
     * store all the effect array
     */
    private static Hashtable<String, String> validUnits = new Hashtable<String, String>();
    /**
     * limited file size 1G
     */
    private static long GB_MAX_SIZE = 1;
    /**
     * max MB value
     */
    private static long MB_MAX_SIZE = GB_MAX_SIZE * 1024L;
    /**
     * max KB value
     */
    private static long KB_MAX_SIZE = MB_MAX_SIZE * 1024L;
    /**
     * max Bytes value
     */
    private static long BYTES_MAX_SIZE = KB_MAX_SIZE * 1024L;
    /**
     * num value
     */
    private Double numPart;
    /**
     * original unit
     */
    private String originalUnit;
    /**
     * stand byte unit 
     */
    private String unit;
    /**
     * result from convert
     */
    private String result;

    // add all the unit
    static {
        validUnits.put("字节", "Bytes");
        validUnits.put("bytes", "Bytes");
        validUnits.put("byte", "Bytes");
        validUnits.put("kb", "KB");
        validUnits.put("k", "KB");
        validUnits.put("兆", "MB");
        validUnits.put("mb", "MB");
        validUnits.put("m", "MB");
        validUnits.put("gb", "GB");
        validUnits.put("g", "GB");
    }
 
    public UnitsConversion() {
        super("########.##");
        numPart = null;
        result = null;
        unit = null;
        originalUnit = null;
    }

    /**
     * convert with file size and unit
     * 
     * @param input
     * @return converted size null if fail
     */
    public String defaultConversion(String input) {
        analyzeString(input);
        if (result != null) {
            return result;
        }
        // unit Bytes
        if (unit.equals("Bytes")) {
            int numPart2Int = numPart.intValue();
            // if differ in 0.5M with 1G return 1GB
            if ((BYTES_MAX_SIZE - numPart2Int) < (1024 * 1024) / 2) {
                return "1 GB";
            }
            // (0,1KB)
            if (numPart2Int < 1024) {
                return numPart2Int + " Bytes";
            }
            // [1KB,1023KB]
            if (numPart2Int >= 1024 && numPart2Int <= (1024 - 1) * 1024) {
                return format(numPart / 1024) + " KB";
            }
            // (1023KB,1GB)
            if (numPart2Int > (1024 - 1) * 1024 && numPart2Int < BYTES_MAX_SIZE) {
                return format(numPart / (1024 * 1024)) + " MB";
            } else
                result = "";
            return result;
        }

        if (unit.equals("KB")) {
            return "还没实现....";
        }

        if (unit.equals("MB")) {
            return "还没实现....";
        }

        if (unit.equals("GB")) {
            return "还没实现....";
        }
        result = "";
        return result;
    }

    /** * 把字符串的数字部分与单位分离，并对数字、单位的有效性进行检验， 若有非法状况，把结果赋值为 "" ，将其返回给用户 * * @param input
     */
    public void analyzeString(String input) {
        // 初步检验输入的字符串
        if (input == null || input.trim().length() < 2) {
            System.out.println("输入的字符串有误");
            result = "";
            return;
        }
        input = input.replaceAll(" ", "");
        int firstIndexOfUnit;// 单位的起始位置
        String strOfNum;// 数字部分的字符串
        // 从尾部开始遍历字符串
        for (int i = input.length() - 1; i >= 0; i--) {
            if (Character.isDigit(input.charAt(i))) {
                firstIndexOfUnit = i + 1;
                originalUnit = input.substring(firstIndexOfUnit,
                        input.length()).toLowerCase();
                if (!isValidUnit(originalUnit)) {
                    System.out.println("无效单位。");
                    result = "";
                    return;
                }
                unit = validUnits.get(originalUnit);
                strOfNum = input.substring(0, firstIndexOfUnit);
                numPart = Double.parseDouble(strOfNum);
                if (!isValidNum(numPart, unit)) {
                    System.out.println("文件大小非法");
                    result = "";
                    return;
                }
                if (numPart == 0) {
                    result = "0 Bytes";
                    return;
                }
                break;
            }
        }
        if (unit == null || numPart == null) {
            //System.out.println("输入的字符串有误");
            result = "";
            return;
        }

    }

    /**
     * 文件大小越界检查
     * 
     * @param num
     * @param unit
     * @return 在1G范围内（包括1G），返回true；否则返回false
     */
    public boolean isValidNum(Double num, String unit) {
        if (num == null || num < 0 || num > BYTES_MAX_SIZE) {
            return false;
        }
        if (unit.equals("KB") && num > KB_MAX_SIZE) {
            return false;
        }
        if (unit.equals("MB") && num > MB_MAX_SIZE) {
            return false;
        }
        if (unit.equals("GB") && num > GB_MAX_SIZE) {
            return false;
        }
        return true;
    }

    /**
     * 检查原始单位originalUnit是否有效
     * 
     * @param originalUnit
     * @return 若originalUnit为空，那么会给他赋默认值 bytes ，并返回true；

     *         若originalUnit是有效单位集合中之一，返回true。
     */
    public boolean isValidUnit(String originalUnit) {
        if (originalUnit == null || originalUnit.trim().length() < 1) {
            originalUnit = "bytes";
            return true;
        }
        for (String validUnit : validUnits.keySet()) {
            if (validUnit.equalsIgnoreCase(originalUnit)) {
                return true;
            }
        }
        return false;
    }
    

//    public String toString()throws RuntimeException
//    {
//       try
//       {
//        //调用计算文件或目录大小方法
//        getFileSize();
//       
//        if(this.longSize>=0&&this.longSize<SIZE_BT)
//        {
//         return this.longSize+"B";
//        }else if(this.longSize>=SIZE_BT&&this.longSize<SIZE_KB)
//        {
//         return this.longSize/SIZE_BT+"KB";
//        }else if(this.longSize>=SIZE_KB&&this.longSize<SIZE_MB)
//        {
//         return this.longSize/SIZE_KB+"MB";
//        }else if(this.longSize>=SIZE_MB&&this.longSize<SIZE_GB)
//        {
//         BigDecimal longs=new BigDecimal(Double.valueOf(this.longSize+"").toString());
//         BigDecimal sizeMB=new BigDecimal(Double.valueOf(SIZE_MB+"").toString());
//         String result=longs.divide(sizeMB, SACLE,BigDecimal.ROUND_HALF_UP).toString();
//         //double result=this.longSize/(double)SIZE_MB;
//         return result+"GB";
//        }else
//        {
//         BigDecimal longs=new BigDecimal(Double.valueOf(this.longSize+"").toString());
//         BigDecimal sizeMB=new BigDecimal(Double.valueOf(SIZE_GB+"").toString());
//         String result=longs.divide(sizeMB, SACLE,BigDecimal.ROUND_HALF_UP).toString();
//         return result+"TB";
//        }   
//       }
//       catch(IOException ex)
//       {
//        ex.printStackTrace();
//        throw new RuntimeException(ex.getMessage());
//       }
//    }

    // test
    public static void main(String[] args) {
        System.out.println("-------------");
//        for (int i = 1020 * 1024; i <= 1024 * 1111; i += 9) {
//            String input = i + " ";
//            System.out.println(input + " ---> "
//                    + new UnitsConversion().defaultConversion(input));
//        }
          String input = 123456 + " ";
          System.out.println(123456 + " ---> "
                    + new UnitsConversion().defaultConversion(input));
    }
}
