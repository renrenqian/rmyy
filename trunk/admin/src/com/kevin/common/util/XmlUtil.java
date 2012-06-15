/**
 * XmlUtil.java
 */
package com.kevin.common.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParserFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.xml.sax.ErrorHandler;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;
import org.xml.sax.XMLReader;
/**
 * 
 * 工程名称:  vsp 
 * 包名:     com.kevin.common.util
 * 类型名:   XmlUtil
 * 描述:    读写xml文件的工具类
 *
 * 例子:
 * @author  <a href="mailto:yaniu_jiang@stardream.tv">蒋亚牛</a>
 * @version 1.0
 * @see     
 * @since 2011-11-16 15:30:31
 */
public class XmlUtil {
    private static DocumentBuilder dbuilder = null;
    private static XMLReader reader = null;
    private XmlUtil() {

    }

    private static DocumentBuilder getBuilder(boolean validation,
            boolean namespaceAware) throws ParserConfigurationException {
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        dbf.setValidating(validation);
        dbf.setNamespaceAware(namespaceAware);
        DocumentBuilder db = dbf.newDocumentBuilder();
        db.setErrorHandler(new GeneralErrorHandler());
        return db;
    }

    /**
     * Returns the single copy of DocumentBuilder or creates one if necessary.
     * 
     * @return the single copy of DocumentBuilder
     */
    public static DocumentBuilder getDocumentBuilder() {
        synchronized (XmlUtil.class) {
            if (dbuilder == null) {
                try {
                    dbuilder = getBuilder(false, false);
                } catch (ParserConfigurationException pce) {
                    throw new RuntimeException("ParserConfigurationException",
                            pce);
                }
            }
        }
        return dbuilder;
    }
    public static Document getDom(InputStream in) throws SAXException, IOException{
        return getDocumentBuilder().parse(in);
    }
    public static Document getDom(File file) throws SAXException, IOException{
        return getDom(new FileInputStream(file));
    }
    public static Document getDom(String file) throws SAXException, IOException{
        return getDom(new File(file));
    }
    /**
     * writes the supplied DOM representation to XML in the supplied file
     * 
     * @param document
     *            the Document to be written
     * @param outputFile
     *            the file to write the Document to
     * @param doctypeSystem
     *            a string specifiying the DTD e.g
     * @since 1.0
     */
    public static void writeDom(Document document, OutputStream outputStream,
            String doctypeSystem, String encoding) {
        TransformerFactory factory = TransformerFactory.newInstance();
        Transformer writer;
        try {
            writer = factory.newTransformer();
        } catch (TransformerConfigurationException tce) {
            throw new RuntimeException(tce.getMessage());
        }
        writer.setOutputProperty(OutputKeys.INDENT, "yes");
        writer.setOutputProperty(OutputKeys.STANDALONE, "no");
        writer.setOutputProperty(OutputKeys.DOCTYPE_SYSTEM, doctypeSystem);
        writer.setOutputProperty(OutputKeys.ENCODING, encoding == null
                || encoding.trim().equals("") ? "UTF-8" : encoding);
        Source source = new DOMSource(document);
        Result result = new StreamResult(outputStream);
        try {
            writer.transform(source, result);
        } catch (TransformerException te) {
            throw new RuntimeException(te.getMessage());
        }
    }

    public static void writeDom(Document document, OutputStream outputStream,
            String doctypeSystem) {
        writeDom(document, outputStream, doctypeSystem, "UTF-8");
    }

    public static XMLReader getReader() {
        synchronized (XmlUtil.class) {
            if (reader == null) {
                try {
                    reader = SAXParserFactory.newInstance().newSAXParser()
                            .getXMLReader();
                } catch (SAXException sexc) {
                    throw new RuntimeException(" could not create XML Reader "
                            + sexc.getMessage(), sexc);
                } catch (ParserConfigurationException pcexc) {
                    throw new RuntimeException(" could not create XML Reader "
                            + pcexc.getMessage(), pcexc);
                }
            }
        }
        return reader;
    }
    public static String getNodeText(Node node) {
        String nodeText;
        if (node.getFirstChild() == null) {
            nodeText = null;
        } else {
            nodeText = node.getFirstChild().getNodeValue().trim();
            if (nodeText.length() < 1) {
                nodeText = null;
            }
        }
        return nodeText;
    }

    public static String getAttributeValue(Element e, String attributeName) {
        String attText = e.getAttribute(attributeName).trim();
        if (attText.length() < 1) {
            attText = null;
        }
        return attText;
    }

    public static String getAttributeValue(Node node, String attributeName) {
        Element tempElement = (Element) node;
        String attText = tempElement.getAttribute(attributeName).trim();
        if (attText.length() < 1) {
            return null;
        }
        return attText;
    }

    public static Element makeElement(Document doc, String elementName,
            String elementText) {
        if (elementText == null) {
            elementText = "";
        }
        Element element = doc.createElement(elementName);
        element.appendChild(doc.createTextNode(elementText));
        return element;
    }

    public static Element makeElement(Document doc, String elementName) {
        Element element = doc.createElement(elementName);
        return element;
    }

    public static Element makeElement(Document doc, String elementName,
            String attributeName, String attributeValue) {
        Element element = doc.createElement(elementName);
        element.setAttribute(attributeName, attributeValue);
        return element;
    }
}

class GeneralErrorHandler implements ErrorHandler {

    public void error(SAXParseException exception) {
        exception.printStackTrace();
    }

    public void fatalError(SAXParseException exception) {
        exception.printStackTrace();
    }

    public void warning(SAXParseException exception) {
        exception.printStackTrace();
    }
}