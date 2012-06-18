$(document).ready(function() {
    /* 编辑、新增 确认按钮 */
    $('#J_ContentOk').die().live("click", function() {
        if ($(this).sdSubmitValidate("#contentForm")) {
            var contInfoId = $("#contId").val();
            var url,action;
            if (!contInfoId || contInfoId == "") {
                action = "新增";
                url = "../group/addContent.action";
                var params = new StringBuffer();
                params.append("contInfo.contTital=" + $("#contTital").val()).append("&");
                params.append("contInfo.contHome_Page=" + $("#contHome_Page :selected").val()).append("&");
                params.append("contInfo.colId=" + $("#colId").val()).append("&");
                params.append("contInfo.contShow_Days=" + $("#contShow_Days :selected").val()).append("&");
                params.append("contInfo.contMedium=" + $("#contMedium :selected").val()).append("&");
                params.append("contInfo.contKey=" + $("#contKey").val()).append("&");
                //params.append("contInfo.contPublish_Time=" + $("#contPublish_Time").val()).append("&");// get the time at the backend
                params.append("contInfo.contAudit_Result=" + 0).append("&");// as page not have, so use the constant
                //params.append("contInfo.contOrder=" + 1).append("&");// as page not have, so use the constant
                params.append("contInfo.gmId=" + $("#gmId").val()).append("&");
                //params.append("contInfo.contAuthor=" + $("#contAuthor").val()).append("&"); // get it from session
                params.append("contInfo.contDetail=" + $("#contDetail").val()).append("&");
                params.append("contInfo.contAttachment=" + $("#contAttachment").val()).append("&");
                params = params.toString();
                $.post(url, params, function(json) {
                    if (json.resultCode > 0) {
                        initContentList();
                        $('#J_ContentDiv').window("close");
                    } else {
                        $.fn.sdInfo({
                            type:"fail",
                            content:json.message ? json.message : action + '内容信息失败!'
                        });
                    }
                });

            } else {
                action = "编辑";
                url = "../group/updateContent.action";
                var params = new StringBuffer();
                params.append("contInfo.contId=" + contInfoId).append("&");
                params.append("contInfo.contTital=" + $("#contTitle").val()).append("&");
                params.append("contInfo.contHome_Page=" + $("#contHome_Page").val()).append("&");
                params.append("contInfo.colId=" + $("#colId").val()).append("&");
                params.append("contInfo.contShow_Days=" + $("#contShow_Days").val()).append("&");
                params.append("contInfo.contMedium=" + $("#contMedium").val()).append("&");
                params.append("contInfo.contKey=" + $("#contKey").val()).append("&");
                params.append("contInfo.contPublish_Time=" + $("#contPublish_Time").val()).append("&");
                params.append("contInfo.contAudit_Result=" + $("#contAudit_Result").val()).append("&");
                params.append("contInfo.contOrder=" + $("#contOrder").val()).append("&");
                params.append("contInfo.gmId=" + $("#gmId").val()).append("&");
                params.append("contInfo.contAuthor=" + $("#contAuthor").val()).append("&");
                params.append("contInfo.contDetail=" + $("#contDetail").val()).append("&");
                params.append("contInfo.contAttachment=" + $("#contAttachment").val()).append("&");
                params = params.toString();
                $.post(url, params, function(json) {
                    if (json.resultCode > 0) {
                        initContentList();
                        $('#J_ContentDiv').window("close");
                    } else {
                        $.fn.sdInfo({
                            type:"fail",
                            content:json.message ? json.message : action + '内容信息失败!'
                        });
                    }
                });
            }
            // var params = $("#contentForm").serialize();
        }
        $(window.parent.document).find("#centerIFrame").attr("src", "content/contentMan.html");
    });

    /* 编辑、新增 取消按钮 */
    $('#J_ContentClose').die().live("click", function() {
        $(window.parent.document).find("#centerIFrame").attr("src", "content/contentMan.html");
    });
});