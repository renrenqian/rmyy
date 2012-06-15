$(document).ready(function() {
    $.ajax({
        dataType: 'json',
        type: "POST",
        data:"",
        url: '../system/listAllparameters.action',
        success: function(json) {
            if (json.resultCode > 0) {
                var sysParameterList = json.sysParameterList;
                var length = sysParameterList.length;
                var _table = $("#sysParamform table");
                var strTbody = [""];
                for (var i = 0; i < length; i++) {
                    strTbody.push("<tr>");
                    strTbody.push("<td class='rowName w2' id='" + sysParameterList[i].spId + "' codeMark='" + sysParameterList[i].spCode + "'>" + sysParameterList[i].spName + "</td>");
                    strTbody.push("<td class='w5'><span class='p10 blue'>" + sysParameterList[i].spValue + "</span></td>");
                    if (1 == sysParameterList[i].spEditable) {
                        //strTbody.push("<td><span class='unl J_Modify' mark='" + sysParameterList[i].spMark + "'>修改</span><span class='unl ml10 J_Refresh' mark='" + sysParameterList[i].spMark + "'>刷新</span></td>");
                        strTbody.push("<td><span class='unl J_Modify' mark='" + sysParameterList[i].spMark + "'>修改</span><span class='unl ml10 J_Refresh' mark='" + sysParameterList[i].spMark + "'></span></td>");

                    } else {
                        strTbody.push("<td></td>");
                    }
                    strTbody.push("</tr>");
                }
                _table.append(strTbody.join(""));
            } else {
                $.fn.sdInfo({
                    type:"fail",
                    content:json.message ? json.message : '查询参数配置出错!'
                });
            }
        }
    });

    $('.J_Modify').live("click", function(){
        var spMark=$(this).attr("mark");
        var myRule={"IP":"required ip","Port":"required num small0 big65535","Task":"required num small1","User":"","Pwd":""};
        var myTr = $(this).parent().parent();
        var myValue = myTr.find('td').eq(1).find('span').html();
        myTr.find('td').eq(1).html("<input type='text' class='input_type1' rule='"+myRule[spMark]+"' value='" + myValue + "' />");
        $(myTr).sdValidate();
        $(this).html("保存").attr("class", "unl green J_Save");
        myTr.find('.J_Refresh').html("取消").attr("class", "unl green ml10 J_Cancel").attr("value", myValue);
    });

    $('.J_Save').live("click", function() {
        var myTr = $(this).parent().parent();
        if ($.fn.sdSubmitValidate(myTr)) {
            var myValue = myTr.find('td').eq(1).find('input').val();
            myTr.find('td').eq(1).html("<span class='p10 blue'>" + myValue + "</span>");
            $(this).html("修改").attr("class", "unl J_Modify");
           // myTr.find('.J_Cancel').html("刷新").attr("class", "unl ml10 J_Refresh");
            myTr.find('.J_Cancel').html("").attr("class", "unl ml10 J_Refresh");
            var _myId = myTr.find('td').eq(0).attr("id");
            var _myCode = myTr.find('td').eq(0).attr("codeMark");
            //console.log("Id:" + _myId + ", value:" + myValue);
            updateSysParam(_myId, myValue, _myCode);
        }
    });

    $('.J_Cancel').live("click", function() {
        var myTr = $(this).parent().parent();
        var myValue = $(this).attr("value");
        myTr.find('td').eq(1).html("<span class='p10 blue'>" + myValue + "</span>");
        // myTr.find('.J_Cancel').html("刷新").attr("class", "unl ml10 J_Refresh");
        myTr.find('.J_Cancel').html("").attr("class", "unl ml10 J_Refresh");
        myTr.find('.J_Save').html("修改").attr("class", "unl J_Modify");
    });
});

function updateSysParam(id, value, code) {
    var params = new StringBuffer();
    var url = "";
    params.append("sysparameter.spId=" + id);
    params.append("&sysparameter.spValue=" + value);
    params.append("&sysparameter.spCode=" + code);
    url = "../system/updatParameter.action";
    params = params.toString();
    $.ajax({
        type: 'POST',
        url: url,
        data: params,
        success: function(jsonObject, statusText, xhr) {
            if (jsonObject.resultCode && jsonObject.resultCode > 0) {
                //intiProfileList();
            } else {
                //set the error info
                if (jsonObject.message) {
                    $.fn.sdInfo({
                         type:"fail",
                         content:jsonObject.message ? jsonObject.message : '更新系统参数失败!'
                    });
                }
            }
        },
        dataType: 'json'
    });
}

function initSysParam(){
    
}