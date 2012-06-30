/**
 * ColumnNavGeneration.java
 * kevin 2012-6-23
 * @version 0.1
 */
package com.kevin.group.util;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.net.URL;
import java.util.logging.Logger;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.XMLWriter;

/**
 * @author kevin
 * @since jdk1.6
 */
public class ColumnNavGeneration {

    private Logger logger = Logger.getLogger(ColumnNavGeneration.class.getName());
    private String CTRF = "\r\n";
    private String END_DIV = "</div>";
    private String END_A = "</a>";
    private String END_LI = "</li>";
    private String END_UL = "</ul>";
    private Document document = null;
    private StringBuilder  strContent = null;
    private String navModullePath = null;
    private boolean isNodeStart = false;
    /**
     * @param args
     */
    public static void main(String[] args) {
        ColumnNavGeneration cng = new ColumnNavGeneration();
        URL filePath = cng.getClass().getClassLoader().getResource("module.nav.ini");
        System.out.println("filePath:" + filePath.getPath());
        cng.navModullePath = filePath.getPath();
        //File iniFile = new File(filePath.getPath());
        cng.readNavIni();
        //cng.writeDOM();
    }

    private void readNavIni(){
        try {
            File iniFile = new File(navModullePath);
            if(iniFile.exists()){
                strContent = new StringBuilder();
                FileInputStream fis = new FileInputStream(navModullePath);
                DataInputStream ins = new DataInputStream(fis);
                BufferedReader buffReader = new BufferedReader(new InputStreamReader(ins, "UTF-8"));
                String line, nodeName = null;
                //boolean isNodeStart = false;
                int lineNo = 0;
                while((line = buffReader.readLine())!= null){
                    lineNo++;
                    //strContent.append(line + "\n");
                    //  if("[header]".equals(line)){
                        //dealNodes(nodeName, line, buffReader);
                    if(line.startsWith("[")){
                        isNodeStart = true;
                        nodeName = line;
                        //System.out.println(line + " node start");
                    }
                    if(isNodeStart && "[header]".equals(nodeName)){
                        addHeader(buffReader);
                    } else if(isNodeStart && "[home]".equals(nodeName)){
                        addHome(buffReader);
                    } else if(isNodeStart && "[group]".equals(nodeName)){
                        addGroup(buffReader);
                    } else if(isNodeStart && "[information]".equals(nodeName)){
                        
                    } else if(isNodeStart && "[service]".equals(nodeName)){
                        
                    } else if(isNodeStart && "[department]".equals(nodeName)){
                        
                    } else if(isNodeStart && "[talent]".equals(nodeName)){
                        
                    } else if(isNodeStart && "[research]".equals(nodeName)){
                        
                    } else if(isNodeStart && "[culture]".equals(nodeName)){
                        
                    }
                    if("".equals(line)){
                        isNodeStart = false;
                        System.out.println(nodeName + " node end:");
                        //System.out.println("lineNo:" + lineNo);
                    }
                    //System.out.println("Line Content: " + line);
                }
                buffReader.close();
                ins.close();
            } else {
                System.out.println("nav File not existed");
            }
            strContent.append(END_DIV);// close the root node
            writeFileWithEncoding("top.html", strContent);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
     
    private void addHeader(BufferedReader buffReader){
        strContent = new StringBuilder();
        strContent.append("<div ");
        String line= "";
        while(isNodeStart){
            try {
                line = buffReader.readLine();
                if(line.startsWith("header_class")){
                    strContent.append("class=\"" + line.split("=")[1] + "\">").append(CTRF);
                }else if(line.startsWith("logo_class")){
                    strContent.append("<div class=\"" + line.split("=")[1] + "\">").append(END_DIV).append(CTRF);
                } else if("".equals(line)){
                    isNodeStart = false;
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    
    private void addHome(BufferedReader buffReader){
        String line= "";
        while(isNodeStart){
            try {
                line = buffReader.readLine();
                if(line.startsWith("li_id")){
                    strContent.append("<div id=\"" + line.split("=")[1] + "\"");
                } else if(line.startsWith("li_class")){
                        strContent.append(" class=\"" + line.split("=")[1] + "\">");
                }else if(line.startsWith("a_href")){
                    strContent.append("<a href=\"" + "/web/main/index.shtml" + "\"");// to get from db
                }else if(line.startsWith("a_class")){
                    strContent.append(" class=\"" + line.split("=")[1] + "\" ");
                }else if(line.startsWith("a_bread")){
                    strContent.append(" bread=\"" + "首页" + "\">").append(END_A);// to get from db
                } else if("".equals(line)){
                    isNodeStart = false;
                    strContent.append(END_DIV).append(CTRF);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    
    private void addGroup(BufferedReader buffReader){
        String line= "";
        while(isNodeStart){
            try {
                line = buffReader.readLine();
                if(line.startsWith("li_id")){
                    strContent.append("<div id=\"" + line.split("=")[1] + "\"");
                } else if(line.startsWith("li_class")){
                        strContent.append(" class=\"" + line.split("=")[1] + "\">");
                }else if(line.startsWith("div_drop_class")){
                    strContent.append("<div class=\"" + line.split("=")[1] + "\">").append(CTRF);
                }else if(line.startsWith("div2_col_class")){
                    strContent.append("<div class=\"" + line.split("=")[1] + "\">").append(CTRF);
                }else if(line.startsWith("ul_class")){
                    strContent.append("<ul class=\"" + line.split("=")[1] + "\"");// to get from db
                }else if(line.startsWith("ul_bread")){
                    strContent.append(" bread=\"" + "医院概览" + "\"").append(CTRF);// to get from db
                }else if(line.startsWith("li_bread")){//loop
                    
                    
                } else if("".equals(line)){
                    isNodeStart = false;
                    strContent.append(END_DIV).append(CTRF);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    
    private void writeHtml(){
        try{
            DataOutputStream out = new DataOutputStream(new BufferedOutputStream(new FileOutputStream("top.html")));
//            out.writeDouble(3.14159);
//            out.writeBytes("That was the value of pi\n");
//            out.writeBytes("This is pi/2:\n");
//            out.writeDouble(3.14159);
//            out.writeUTF(document.getText());
            System.out.println("XML:" + document.asXML());
            out.writeUTF(document.asXML());
            out.flush();
            out.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    private void writeDOM(){
        //Document document = DocumentHelper.createDocument();
        Element root = document.addElement( "root" );

        Element author1 = root.addElement( "author" )
            .addAttribute( "name", "James" )
            .addAttribute( "location", "UK" );
        
        try {
            write(document);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private void write() throws IOException{
        write(document);
    }
    
    private void write(Document document) throws IOException {

        // lets write to a file
        XMLWriter writer = new XMLWriter(
            new FileWriter( "top.html" )
        );
        writer.write( document );
        writer.close();

        // Pretty print the document to System.out
        OutputFormat format = OutputFormat.createPrettyPrint();
        writer = new XMLWriter( System.out, format );
        writer.write( document );
        //writer.close();

        // Compact format to System.out
//        format = OutputFormat.createCompactFormat();
//        writer = new XMLWriter( System.out, format );
//        writer.write( document );
    }
    
    /**
     * Write the file with the pointed charset encoding.
     * 
     * @param filePath
     * @param fileContent
     * @throws DistributionException
     */
    private void writeFileWithEncoding(String filePath,
            StringBuilder fileContent) {
        try {
            FileOutputStream fos = new FileOutputStream(filePath);
            Writer out = new OutputStreamWriter(fos, "UTF-8");
            out.write(fileContent.toString());
            out.close();
            fos.close();
        } catch (FileNotFoundException e) {
            //e.printStackTrace();
            logger.log(logger.getLevel().ALL, "writeFileWithEncoding FileNotFoundException" + e.getMessage());
            //throw new DistributionException("文件读取失败。");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            //logger.error("writeFileWithEncoding UnsupportedEncodingException"
            //      + e.getMessage());
            //throw new DistributionException("编码格式不支持。");
        } catch (IOException e) {
            e.printStackTrace();
            //logger.error("writeFileWithEncoding IOException" + e.getMessage());
            //throw new DistributionException("文件流关闭失败。");
        }
    }
}
