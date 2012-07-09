<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://ckeditor.com" prefix="ckeditor"%>
<%@ taglib uri="http://ckfinder.com" prefix="ckfinder"%>
<%@page import="com.ckeditor.CKEditorConfig"%>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<html>
    <head>
        <base href="<%=basePath%>"/>
        <title>在线编辑器</title>
        <meta http-equiv="pragma" content="no-cache" />
        <meta http-equiv="cache-control" content="no-cache"/>
        <meta http-equiv="expires" content="0"/>
        <meta http-equiv="keywords" content="editor,keyword2,keyword3"/>
        <meta http-equiv="description" content="editor"/>
        <!--
    <link rel="stylesheet" type="text/css" href="styles.css"/>
    -->

    </head>

    <body>
        <ckfinder:setupCKEditor editor="editor1" basePath="ckfinder/" />
        <ckeditor:editor basePath="ckeditor/" 
            editor="editor1" value=" " />
    </body>
</html>

