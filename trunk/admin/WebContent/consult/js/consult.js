var startIndex;
var consultTable;
$(document).ready(function() {
    //查数据字典
    initConsultList();//初始化列表
    $('#consultForm').sdValidate();//添加验证规则

    /* 编辑 */
    $(".J_ConsultEdit").die().live("click", function() {
        $.fn.sdResetForm("#consultForm");
        var id = $(this).parent().parent().children().eq(0).children().eq(0).val();
        $.getJSON('../online/searchConsultation.action?t=' + new Date().getTime() + '&cons.ocId=' + id, function(json) {
            if (json.resultCode > 0) {
                //formUnSerialize("consultForm", "cons", json.cons);
                var cons = json.cons;
                $(cons).each(function(i, item) {
                    $('#ocId').val(item.ocId);
                    $('#ocSex').val(item.ocSex);
                    $('#ocPost_subject').val(item.ocPost_subject);
                    $('#ocPost_age').val(item.ocPost_age);
                    $('#ocDesc').val(item.ocDesc);
                    $('#osAnswer').val(item.osAnswer);
                    $('#osTypical').val(item.osTypical);
                });
            } else {
                $.fn.sdInfo({
                    type:"fail",
                    content:json.message ? json.message : "查询咨询信息错误!"
                });
            }
        });
        $('#J_ConsultDiv').css("display", "").window({
            title: '编辑咨询信息',
            modal: true,
            minimizable:false,
            maximizable:false,
            collapsible:false,
            shadow:false,
            width:GLOBAL.ClientScreen.clientWidth() * 0.9,
            height:GLOBAL.ClientScreen.clientHeight() * 0.9
        });
    });

    /* 编辑、新增 确认按钮 */
    $('#J_ConsultOk').die().live("click", function() {
        if ($(this).sdSubmitValidate("#consultForm")) {
            var ocId = $("#ocId").val();
            var url,action;
            if (!ocId || ocId == "") {
                action = "新增";
                url = "../online/addConsultation.action";
            } else {
                action = "编辑";
                url = "../online/updateConsultation.action";
//                var params = new StringBuffer();
//                params.append("cons.consName=" + $("#consName").val()).append("&");
//                params.append("cons.ocId=" + $("#ocId").val()).append("&");
//                params = params.toString();
//                $.post(url, params, function(json) {
//                    if (json.resultCode > 0) {
//                        initConsultList();
//                        $('#J_ConsultDiv').window("close");
//                    } else {
//                        $.fn.sdInfo({
//                            type:"fail",
//                            content:json.message ? json.message : action + '咨询信息失败!'
//                        });
//                    }
//                });
            }

            var params = new StringBuffer();
            params.append("cons.ocId=" + ocId).append("&");
            params.append("cons.ocPost_subject=" + $("#ocPost_subject").val()).append("&");
            params.append("cons.ocPost_age=" + $("#ocPost_age").val()).append("&");
            params.append("cons.ocSex=" + $("#ocSex").val()).append("&");
            params.append("cons.ocDesc=" + $("#ocDesc").val()).append("&");
            params.append("cons.osAnswer=" + $("#osAnswer").val()).append("&");
            params.append("cons.osTypical=" + $("#osTypical").val()).append("&");
            if(2== $("#isAnswer").val())
                params.append("cons.ocReceive_office=" + $("#ocReceive_office").val()).append("&");
            else 
                params.append("cons.osAnswered=" + 1).append("&");
            params = params.toString();
            $.post(url, params, function(json) {
                if (json.resultCode > 0) {
                    initConsultList();
                    $('#J_ConsultDiv').window("close");
                } else {
                    $.fn.sdInfo({
                        type:"fail",
                        content:json.message ? json.message : action + '咨询信息失败!'
                    });
                }
            });

            // var params = $("#consultForm").serialize();
        }
    });


    /* 编辑、新增 取消按钮 */
    $('#J_ConsultClose').die().live("click", function() {
        $('#J_ConsultDiv').window("close");
    });

    /* del */
    $('.J_ConsultDel').die().live("click", function() {
        var THIS = this;
        $.messager.confirm('删除', '是否确认删除所选咨询信息?', function(r) {
            if (r) {
                var id = $(THIS).parent().parent().children().eq(0).children().eq(0).val();
                var params = "cons.ocId=" + id;
                $.post("../online/deleteConsultation.action", params, function(json) {
                    if (json.resultCode > 0) {
                        initConsultList();
                    } else {
                        $.fn.sdInfo({
                            type:"fail",
                            content:json.message ? json.message : '删除咨询信息失败!'
                        });
                    }
                });
            }
        });
    });
});

$('#J_ConsultDelAll').click(function() {
    if ($(this).hasClass("abled")) {
        //获取参数
        var checkbox = $('#J_ConsultTable input:checkbox:not(.checkBoss):checked');
        var ides = "";
        checkbox.each(function() {
            ides = ides + "ides=" + $(this).val() + "&";
        });
        $.messager.confirm('批量删除', '是否确认删除所选咨询信息?', function(r) {
            if (r) {
                $.post("../online/batchDeleteConsultation.action", ides, function(data) {
                    if (data.resultCode && data.resultCode > 0) {
                        initConsultList();
                    } else {
                        $.fn.sdInfo({
                            type:"fail",
                            content:data.message ? data.message : "批量删除咨询信息失败"
                        });
                    }
                });
                $.fn.checkTest("J_ConsultTable");
                $('.checkBoss').attr("checked", false);
            }
        });
    }
});

/**
 * 初始列表数据
 */
function initConsultList() {
    if (!consultTable) {
        consultTable = $("#J_ConsultTable").dataTable({
            bProcessing: false,
            bServerSide:false,
            bDestory:false,
            bRetrieve:true,
            sAjaxSource:"../online/listConsultation.action",
            sAjaxDataProp: "consList",
            oSearch: {"sSearch": ""},
            bAutoWidth:false,
            fnServerData:function(sSource, aoData, fnCallback) {
                var params = [];
                var iDisplayStart,iDisplayLength,sEcho,sSearch;
                for (var i = 0; i < aoData.length; i++) {
                    if (aoData[i].name == "iDisplayStart") {
                        iDisplayStart = aoData[i].value;
                    }
                    if (aoData[i].name == "iDisplayLength") {
                        iDisplayLength = aoData[i].value;
                    }
                    if (aoData[i].name == "sEcho") {
                        sEcho = aoData[i].value;
                    }
                    if (aoData[i].name == "sSearch") {
                        sSearch = aoData[i].value;
                    }
                }
                params.push({ "name": "page.pageSize", "value": iDisplayLength });
                var currentPageNo = Math.floor(iDisplayStart / iDisplayLength) + 1;
                params.push({ "name": "page.currentPageNo", "value": currentPageNo });
                $.ajax({
                    dataType: 'json',
                    type: "POST",
                    url: sSource,
                    data: params,
                    success: function(json) {
                        if (json.resultCode > 0) {
                        } else {
                            $.fn.sdInfo({
                                type:"fail",
                                content:json.message ? json.message : "查询列表错误!"
                            });
                        }
                        if (!json.page) {
                            json.page = {};
                        }
                        if (!json.page.dataList) {//处理返回结果
                            json.page.dataList = [];
                        }
                        //json.sEcho = sEcho;
                        json.iTotalRecords = json.page.totalItemCount;
                        json.iTotalDisplayRecords = json.page.totalItemCount;

                        fnCallback(json);
                        setTableTrColor();
                        $('#J_ConsultTable input[type=checkbox]').sdCheckBox();
                    }
                });
            },
            aoColumns: [
                {
                    fnRender:function(obj) {
                        return "<input type='checkbox' value='" + obj.aData.ocId + "'/>";
                    }
                },
                {
                    fnRender:function(obj) {
                        return "<span>" + obj.aData.ocPost_time + "</span>";
                    }
                },
                {
                    fnRender:function(obj) {
                        return "<span>" + obj.aData.ocRequestOfficeName + "</span>";
                    }
                },
                {
                    fnRender:function(obj) {
                        return "<span>" + obj.aData.ocReceiveOfficeName + "</span>";
                    }
                },
                      {
                    fnRender:function(obj) {
                        return "<span>" + obj.aData.ocPost_subject + "</span>";
                    }
                },
                {
                    fnRender:function(obj) {
                        var state = "未回复",className = "red";
                        if (obj.aData.osAnswered == 1) {
                            state = "已回复";
                            className = "green";
                        }
                        return "<span class='J_ReplyState unl " + className + "'>" + state + "</span>";
                    }
                },
                {
                    fnRender:function(obj) {
                        return "<a class='edit J_ConsultEdit'></a>";
                    }
                },
                {
                    fnRender:function(obj) {
                        return "<a class='del J_ConsultDel'></a>";
                    }
                }
            ],
            sPaginationType: "full_numbers"
            //       aoColumnDefs: [
            //             { "bSortable": false, "aTargets": [0,1,2]}
            //         ]
        });
    } else {
        consultTable.fnClearTable();
        $.getJSON(
                "../online/listConsultation.action",
                function(json) {
                    if (json.resultCode > 0) {
                    } else {
                        $.fn.sdInfo({
                            type:"fail",
                            content:json.message ? json.message : "查询列表错误!"
                        });
                    }
                    if (!json.consList) {
                        json.consList = [];
                    }
                    consultTable.fnAddData(json.consList);
                    setTableTrColor();
                    $('#J_ConsultTable input[type=checkbox]').sdCheckBox();
                }
                );
    }
}