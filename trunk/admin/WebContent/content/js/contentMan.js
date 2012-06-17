var contentDataTable;
$(document).ready(function() {
    //查数据字典
//     $.ajax({
//            type: 'POST',
//            url: "../system/listAllDataDict.action",
//            async: false,
//            success:
//            function(json) {
//                if (json.resultCode > 0) {
//                    var dataDict = json.ddList;
//                    //填充页面的下拉 J_pcTransType,J_pcState
//                    var pcTransType=[]; g_pcTransType=[];
//                    var pcState=[];g_pcState=[];
//
//                    if(dataDict){
//                        for(var i=0;i<dataDict.length;i++){
//                            if(dataDict[i].DICT_KIND=="pc_trans_type"){
//                                g_pcTransType.push(dataDict[i]);
//                                pcTransType.push("<option value='"+dataDict[i].DICT_KEY+"'>"+dataDict[i].DICT_DISVALUE+"</option>");
//                            }
//                            if(dataDict[i].DICT_KIND=="pc_state"){
//                                g_pcState.push(dataDict[i]);
//                                pcState.push("<option value='"+dataDict[i].DICT_KEY+"'>"+dataDict[i].DICT_DISVALUE+"</option>");
//                            }
//                        }
//                    }
//                    $("#J_pcTransType").html(pcTransType.join(""));
//                    $("#J_pcState").html(pcState.join(""));
//                } else {
//                     $.fn.sdInfo({ type:"fail",content:json.message ? json.message : "查询数据字典数据错误!" });
//                }
//     }});
    initContentList();//初始化列表
    $('#contentForm').sdValidate();//添加验证规则
    /* 新增 */
    $("#J_AddContent").die().live("click", function() {
        $(window.parent.document).find("#centerIFrame").attr("src", "content/addContent.html");
    });

    /* 编辑 */
    $(".J_LeaderEdit").die().live("click", function() {
//        var id = $(this).parent().parent().children().eq(0).children().eq(0).val();
//        $.getJSON('../config/searchCatalogConfig.action?t=' + new Date().getTime() + '&lc.lcId=' + id, function(json) {
//            if (json.resultCode > 0) {
//                formUnSerialize("contentForm", "lc", json.lc);
//
//            } else {
//                $.fn.sdInfo({
//                    type:"fail",
//                    content:json.message ? json.message : "查询内容信息错误!"
//                });
//            }
//        });
        $(window.parent.document).find("#centerIFrame").attr("src", "content/addContent.html");
    });

    /* del */
    $('.J_ContentDel').die().live("click", function() {
        var THIS = this;
        $.messager.confirm('删除', '是否确认删除所选内容信息?', function(r) {
            if (r) {
                var id = $(THIS).parent().parent().children().eq(0).children().eq(0).val();
                var params = "lc.LcId=" + id;
                $.post("../config/deleteContent.action", params, function(json) {
                    if (json.resultCode > 0) {
                        initContentList();
                    } else {
                        $.fn.sdInfo({
                            type:"fail",
                            content:json.message ? json.message : '删除内容信息失败!'
                        });
                    }
                });
            }
        });
    });
});

$('#J_ContentDelAll').click(function() {
    if ($(this).hasClass("abled")) {
        //获取参数
        var checkbox = $('#J_ContentTable input:checkbox:not(.checkBoss):checked');
        var ides = "";
        checkbox.each(function() {
            ides = ides + "ides=" + $(this).val() + "&";
        });
        $.messager.confirm('批量删除', '是否确认删除所选内容信息?', function(r) {
            if (r) {
                $.post("../group/batchDeleteContent.action", ides, function(data) {
                    if (data.resultCode && data.resultCode > 0) {
                        initContentList();
                    } else {
                        $.fn.sdInfo({
                            type:"fail",
                            content:data.message ? data.message : "批量删除内容信息失败"
                        });
                    }
                });
                $.fn.checkTest("J_ContentTable");
                $('.checkBoss').attr("checked", false);
            }
        });
    }
});

/**
 * 初始列表数据
 */
function initContentList() {
    if (!contentDataTable) {
        contentDataTable = $("#J_ContentTable").dataTable({
            bProcessing: false,
            bServerSide:false,
            bDestory:false,
            bRetrieve:true,
            sAjaxSource:"../group/listContent.action",
            sAjaxDataProp: "contList",
            oSearch: {"sSearch": ""},
            bAutoWidth:false,
            fnServerData:function(sSource, aoData, fnCallback) {
                $.ajax({
                    dataType: 'json',
                    type: "GET",
                    url: sSource,
                    data: aoData,
                    success: function(json) {
                        if (json.resultCode > 0) {
                        } else {
                            $.fn.sdInfo({
                                type:"fail",
                                content:json.message ? json.message : "查询列表错误!"
                            });
                        }
                        //处理返回结果
                        if (!json.lcList) {
                            json.lcList = [];
                        }
                        fnCallback(json);
                        setTableTrColor();
                        $('#J_ContentTable input[type=checkbox]').sdCheckBox();
                    }
                });
            },
            aoColumns: [
                {
                    fnRender:function(obj) {
                        return "<input type='checkbox' value='" + obj.aData.contId + "'/>";
                    }
                },
                {
                    fnRender:function(obj) {
                        return "<span>" + obj.aData.contTital + "</span>";
                    }
                },
                {
                    fnRender:function(obj) {
                        return "<span >" + obj.aData.colId + "</span>";
                    }
                },
                {
                    fnRender:function(obj) {
                        return "<span>" + obj.aData.contPublish_Time + "</span>";
                    }
                },
                {
                    fnRender:function(obj) {
                        return "<span >" + obj.aData.gmId + "</span>";
                    }
                },
                {
                    fnRender:function(obj) {
                        return "<span>" + obj.aData.contAuthor + "</span>";
                    }
                },
                {
                    fnRender:function(obj) {
                        return "<a class='del J_ContentEditor'></a>";
                    }
                },
                {
                    fnRender:function(obj) {
                        return "<a class='del J_ContentDel'></a>";
                    }
                }
            ],
            sPaginationType: "full_numbers"
            //       aoColumnDefs: [
            //             { "bSortable": false, "aTargets": [0,1,2]}
            //         ]
        });
    } else {
        contentDataTable.fnClearTable();
        $.getJSON(
                "../group/listContent.action",
                function(json) {
                    if (json.resultCode > 0) {
                    } else {
                        $.fn.sdInfo({
                            type:"fail",
                            content:json.message ? json.message : "查询列表错误!"
                        });
                    }
                    if (!json.lcList) {
                        json.lcList = [];
                    }
                    contentDataTable.fnAddData(json.lcList);
                    setTableTrColor();
                    $('#J_ContentTable input[type=checkbox]').sdCheckBox();
                }
                );
    }
}