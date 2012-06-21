// JavaScript Document
$(document).ready(function() {
    searchUser();
    $('#form1,#form2').sdValidate();
    /* 保存个人信息设置 */
    $('#J_PerInfoSetOk').die().live("click", function() {
        if ($.fn.sdSubmitValidate("#form1")) {
            // validate passed : put your code here
            var params = "&user.uiAccount=" + $("#userAccount").val() + "&user.uiName=" + $("#userName").val() + "&user.uiId=" + $("#userId").val() +
                    "&user.uiOfficePhone=" + $("#officePhone").val() + "&user.uiMail=" + $("#mail").val();

            $.post("../system/updatePersonInfo.action", params, function(data) {
                if (data.resultCode && data.resultCode > 0) {
                    $.fn.sdInfo({
                        type:"success",
                        content:"修改信息成功!"
                    });
                } else {
                    $.fn.sdInfo({
                        type:"fail",
                        content:data.message + "用户失败!"
                    });
                }
            });
        }
    });

    /* 修改密码 */
    $("#J_PerInfoPwdReset").click(function() {
        $('#J_PerInfoPwdResetDiv').css("display", "").window({
            title: '修改密码',
            modal: true,
            minimizable:false,
            maximizable:false,
            collapsible:false,
            closed: false,
            width:600
        });
    });

    /* 修改密码确认按钮 */
    $('#J_PerInfoPwdResetOk').click(function() {
        var old = $("#oldPassword").val();
        var real = $("#realPassword").val();
        if (MD5(old) == real) {

        } else {
            $.fn.sdInfo({
                type:"fail",
                content:"原始密码错误"
            });
            return;
        }
        
        if ($.fn.sdSubmitValidate("#form2")) {
            $("#newPwd").val(MD5($("#newPwd").val()));
            var params   = $("#form2").serialize();
            $.post("../system/updateUserPwd.action", params, function(data) {
                if (data.resultCode && data.resultCode > 0) {
                    $('.error').remove();

                    $.fn.sdInfo({
                        type:"success",
                        content:"密码修改成功!"
                    });
                    $('#J_PerInfoPwdResetDiv').window('close');
                } else {
                    $.fn.sdInfo({
                        type:"fail",
                        content:data.message + "修改密码失败!"
                    });
                }
            });
        }
    });

    /*  */
    $('#J_PerInfoPwdResetClose').click(function() {
        $('#J_PerInfoPwdResetDiv').window('close');
    });
});


function searchUser() {
    $.getJSON("../system/searchUserById.action", {"user.uiAccount":$.cookie("cookie_uiAccount")}, function(data) {
        //绑定到页面
        if (data.resultCode && data.resultCode > 0) {
        	$("#userId").val(data.user.uiId);
            $("#userName").val(data.user.uiName);
            $("#userAccount").val(data.user.uiAccount);
            $("#uiPwd").val(data.user.uiPwd);
            $("#realPassword").val(data.user.uiPwd);
            $("#uiId_mod_pwd").val(data.user.uiId);
            $("#officePhone").val(data.user.uiOfficePhone);
            $("#mail").val(data.user.uiMail);
            $("#confirmPwd").val(data.user.uiPwd);
        } else {
            //错误提示
            $.fn.sdInfo({
                type:"fail",
                content:data.message + "信息失败!"
            });
        }
    });
}





