$(document).ready(function() {
     $('#contentForm').sdValidate();//添加验证规则 
    /* 编辑、新增 确认按钮 */
    $('#J_EmployeeOk').die().live("click", function() {
        if ($(this).sdSubmitValidate("#contentForm")) {
            var erId = $("#erId").val();
            var url,actionName;
            if (!erId || erId == "") {
                actionName = "新增";
                url = "../online/addEmployee.action"; 
            } else {
                actionName = "编辑";
                url = "../online/updateEmployee.action";
            }
        $('#contentForm').form('submit', {
          url:url,
          dataType : 'json',
          onSubmit: function(){
            //alert("onSubmit");
         },
         error:function (json) {
             alert("内容异常:" + json.message) ; 
         }, 
         success:function(json){
             json = eval('(' + json + ')');
             if (json.resultCode > 0 ) {
                 //$('#contentForm').window("close");
                 $(window.parent.document).find("#centerIFrame").attr("src", "content/employMan.html");
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
    $('#J_EmployeeClose').die().live("click", function() {
        $(window.parent.document).find("#centerIFrame").attr("src", "content/employMan.html");
    });
});


//日期控件
(function(S) {
    KISSY.use('calendar', function(S) {
        //创建发布日期
        var cPubTime = new S.Calendar('#erPublish_date', {
            popup:true,
            showTime:true
        }).on('timeSelect', function(e) {
            var dateFormat = new DateFormat();
            $('#erPublish_date').val(dateFormat.isoDateTime(e.date));
            dateFormat = null;
            cPubTime.hide();
        });
        cPubTime.render({minDate:new Date()});
        
        
        //创建截止日期
        var cEndTime = new S.Calendar('#erExpiry_date', {
            popup:true,
            showTime:true
        }).on('timeSelect', function(e) {
            var dateFormat = new DateFormat();
            $('#erExpiry_date').val(dateFormat.isoDateTime(e.date));
            dateFormat = null;
            cEndTime.hide();
        });    
        cEndTime.render({minDate:new Date()});
    });
})(KISSY);