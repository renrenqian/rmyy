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
        <link href="style/adminPart/easyui.css" rel="stylesheet" />
        <link href="style/adminPart/common.css" rel="stylesheet" />
        <link href="plugins/calendar/calendar/calendar.css" rel="stylesheet" />
        <script src="js/jquery-1.7.1.min.js"></script>
        <script src="js/adminPart/jquery.easyui.min.js"></script>
        <script src="js/adminPart/common.js"></script>
        <script src="plugins/form/jquery.sdForm.js"></script>
        <script src="plugins/ckeditor/ckeditor.js"></script>
        <script src="plugins/ckeditor/adapters/jquery.js"></script>
        <script src="plugins/ckfinder/ckfinder.js"></script>
        <script src="plugins/calendar/seed-min.js"></script>
        <!--
    <link rel="stylesheet" type="text/css" href="styles.css"/>
    -->
	<script type="text/javascript">
	  $(document).ready(function() {
     $('#contentForm').sdValidate();//添加验证规则 
      // here to get the contId passed from parent page contentMan.page and js file, then search the page fileds.
     var contId=GetParameter('contId');     
     $.getJSON('group/searchContent.action?t=' + new Date().getTime() + '&continfo.contId=' + contId, function(json) {
         if (json.resultCode > 0) {
             //formUnSerialize("contentForm", "continfo", json.continfo);
             //CKEDITOR.replace('contDetail');
            // CKEDITOR.instances.setData($("#contDetail").val());
            // CKEDITOR.instances.caseText.setData($("#contDetail").val());
            
            
         } else {
             $.fn.sdInfo({
                 type:"fail",
                 content:json.message ? json.message : "查询内容信息错误!"
             });
         }
     });
     
    /* 编辑、新增 确认按钮 */
    $('#J_ContentOk').die().live("click", function() {
        if ($(this).sdSubmitValidate("#contentForm")) {
            var contInfoId = $("#contId").val();
            var url,actionName;
            if (!contInfoId || contInfoId == "") {
                actionName = "新增";
                url = "group/addContent.action"; 
            } else {
                actionName = "编辑";
                url = "group/updateContent.action";
            }
        $('#contentForm').form('submit', {
          url:url,
          dataType : 'json',
          onSubmit: function(){        	
        	  $("#contDetail").val(CKEDITOR.instances.editor1.getData());
        	  console.log($("#contDetail").val());
         },
         error:function (json) {
             alert("内容异常:" + json.message) ; 
         }, 
         success:function(json){
             json = eval('(' + json + ')');
             if (json.resultCode > 0 ) {
                 $(window.parent.document).find("#centerIFrame").attr("src", "content/contentMan.html");
                 return false;
                } else  {
                    $.fn.sdInfo({
                        type : "fail",
                        content : json.message ? actionName+"内容错误:"+json.message : actionName+"内容错误:"
                    });
                }
         }
     });  
        }       
    });

    /* 编辑、新增 取消按钮 */
    $('#J_ContentClose').die().live("click", function() {
        $(window.parent.document).find("#centerIFrame").attr("src", "content/contentMan.html");
    });
});


//日期控件
(function(S) {
    KISSY.use('calendar', function(S) {
        //创建日期
        var cPubTime = new S.Calendar('#contPubTime', {
            popup:true,
            showTime:true
        }).on('timeSelect', function(e) {
            var dateFormat = new DateFormat();
            $('#contPubTime').val(dateFormat.isoDateTime(e.date));
            dateFormat = null;
            cPubTime.hide();
        });
        cPubTime.render({minDate:new Date()});
    });
})(KISSY);
	</script>

    </head>

    <body>
	<div class="mainSection">
		<form id="contentForm" name="contentForm" action=""
			enctype="multipart/form-data" method="post" target="hidden_frame">
			<table class="form w mt10 mb10">
				<tr>
					<td class="rowName w1">内容标题</td>
					<td>
					     <input id="contTitle" name="continfo.contTitle" type="text" class="input_type1" rule="required" />
                         <input id="contId" name="continfo.contId" type="hidden" />
                         <input id="contOrder" name="continfo.contOrder" type="hidden" value="1" />
                         <input id="contAudit_Result" name="continfo.contAudit_Result" type="hidden" value="0" />
					</td>
					<td class="rowName">关键词</td>
					<td><input id="contKey" type="text" name="continfo.contKey"
						class="input_type1" /> <input id="contAudit_Result" type="hidden"
						name="continfo.contAudit_Result" value="1" /></td>
				</tr>
				<tr>
					<td class="rowName w1">作者或来源</td>
					<td><input id="contAuthor" type="text" class="input_type1"
						name="continfo.contAuthor"/></td>
					<td class="rowName">发布日期</td>
					<td><input id="contPubTime" type="text"
						name="continfo.contPubTime" class="input_type_date" /></td>
				</tr>
				<tr>
					<td class="rowName">媒体报道</td>
					<td><select id="contMedium" class="selectType1"
						name="continfo.contMedium">
							<option value="0">否</option>
							<option value="1">是</option>
					</select></td>
					<td class="rowName">分享院区</td>
					<td><select id="gmShareId" class="selectType1"
						name="continfo.gmShareId">
							<option value="0">无</option>
					</select></td>
				</tr>
				<tr>
					<td class="rowName">首页图片展示</td>
					<td><select id="contHome_Page" class="selectType1"
						name="continfo.contHome_Page">
							<option value="0">否</option>
							<option value="1">是</option>
					</select></td>
					<td class='rowName' id='J_IndexPic'>展示图片上传</td>
					<td><input type="file" name="file" id="indexPic_add" style='margin:6px; margin-left:10px;' /></td>
				</tr>
				<tr>
					<td class="rowName">附件上传</td>
					<td id="file_td"><input type="file" name="file" id="file_add" style='margin:6px; margin-left:10px;' /></td>
				</tr>
				<tr>
				<td><input type='hidden' value=''/></td>
				</tr>
				<tr>
					<td class="rowName">内容编辑</td>
					<td colspan="3" style='padding-left: 10px;'>
					   <textarea id="contDetail" name="continfo.contDetail" style="visibility: hidden; display: none;"></textarea> 
                       <ckfinder:setupCKEditor editor="editor1" basePath="ckfinder/" />
                       <ckeditor:editor  basePath="ckeditor/" editor="editor1" value=" " /> 
					</td>
				</tr>
				<tr>
					<td></td>
					<td class="p10"><a class="button btn-blue" id="J_ContentOk"><span class="icon ico-bulb"></span>确认</a>
					                <a class="button btn-grey" id="J_ContentClose">取消</a>
				</tr>
			</table>
		</form>
	</div>
    </body>
    
</html>

