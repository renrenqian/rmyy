var contentDataTable;
$(document).ready(function() {
    initContentList();//初始化列表
    $('#contentForm').sdValidate();//添加验证规则
    /* 新增 */
    $("#J_AddContent").die().live("click", function() {
        $(window.parent.document).find("#centerIFrame").attr("src", "content/addContent.html");
    });

    /* 编辑 */
    $(".J_ContentEdit").die().live("click", function() {
        var id = $(this).parent().parent().children().eq(0).children().eq(0).val();
        $.getJSON('../group/searchContent.action?t=' + new Date().getTime() + '&continfo.contId=' + id, function(json) {
            if (json.resultCode > 0) {
                formUnSerialize("contentForm", "continfo", json.continfo);
            } else {
                $.fn.sdInfo({
                    type:"fail",
                    content:json.message ? json.message : "查询内容信息错误!"
                });
            }
        });
        $(window.parent.document).find("#centerIFrame").attr("src", "content/addContent.html");
    });

    /* del */
    $('.J_ContentDel').die().live("click", function() {
        var THIS = this;
        $.messager.confirm('删除', '是否确认删除所选内容信息?', function(r) {
            if (r) {
                var id = $(THIS).parent().parent().children().eq(0).children().eq(0).val();
                var params = "continfo.contId=" + id;
                $.post("../group/deleteContent.action", params, function(json) {
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
                        if (!json.contList) {
                            json.contList = [];
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
                        return "<span class='hidden2 tl'>" + obj.aData.contTitle + "</span>";
                    }
                },
                {
                    fnRender:function(obj) {
                        return "<span class='hidden2 tl'>" + obj.aData.colName + "</span>";
                    }
                },
                {
                    fnRender:function(obj) {
                        return "<span>" + obj.aData.contPublish_Time + "</span>";
                    }
                },
                {
                    fnRender:function(obj) {
                        return "<span class='hidden2 tl'>" + obj.aData.gmName + "</span>";
                    }
                },
                {
                    fnRender:function(obj) {
                       var contAuthorName = obj.aData.contAuthorName;
                       if(undefined == contAuthorName || !contAuthorName){
                           return "<span>未知</span>";
                       }else {
                           return "<span>" + obj.aData.contAuthorName + "</span>";
                       }
                    }
                },
                {
                    fnRender:function(obj) {
                        return "<a class='edit J_ContentEdit'></a>";
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
                    if (!json.contList) {
                        json.contList = [];
                    }
                    contentDataTable.fnAddData(json.contList);
                    setTableTrColor();
                    $('#J_ContentTable input[type=checkbox]').sdCheckBox();
                }
                );
    }
}