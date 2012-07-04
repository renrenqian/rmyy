$(document).ready(function() {
     $('#contentForm').sdValidate();//添加验证规则 
      // here to get the contId passed from parent page contentMan.page and js file, then search the page fileds.
     var contId=GetParameter('contId');     
     $.getJSON('../group/searchContent.action?t=' + new Date().getTime() + '&continfo.contId=' + contId, function(json) {
         if (json.resultCode > 0) {
             formUnSerialize("contentForm", "continfo", json.continfo);
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
                url = "../group/addContent.action"; 
            } else {
                actionName = "编辑";
                url = "../group/updateContent.action";
            }
       //  alert("contDetail:" + editor.getData());
        $('#contentForm').form('submit', {
          url:url,
          dataType : 'json',
          onSubmit: function(){
             //$("#contDetail").val(editor.getData()); 
            // alert("contDetail:" + $("#contDetail").val());
         },
         error:function (json) {
             alert("内容异常:" + json.message) ; 
         }, 
         success:function(json){
             json = eval('(' + json + ')');
             if (json.resultCode > 0 ) {
                 $(window.parent.document).find("#centerIFrame").attr("src", "content/contentMan.html");
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