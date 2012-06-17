var leaderDataTable;
$(document).ready(function() {
    //查数据字典
//	 $.ajax({
//	        type: 'POST',
//	        url: "../system/listAllDataDict.action",
//	        async: false,
//	        success:
//            function(json) {
//                if (json.resultCode > 0) {
//                	var dataDict = json.ddList;
//                	//填充页面的下拉 J_pcTransType,J_pcState
//                	var pcTransType=[]; g_pcTransType=[];
//                	var pcState=[];g_pcState=[];
//
//                	if(dataDict){
//                		for(var i=0;i<dataDict.length;i++){
//                			if(dataDict[i].DICT_KIND=="pc_trans_type"){
//                				g_pcTransType.push(dataDict[i]);
//                				pcTransType.push("<option value='"+dataDict[i].DICT_KEY+"'>"+dataDict[i].DICT_DISVALUE+"</option>");
//                			}
//                            if(dataDict[i].DICT_KIND=="pc_state"){
//                            	g_pcState.push(dataDict[i]);
//                            	pcState.push("<option value='"+dataDict[i].DICT_KEY+"'>"+dataDict[i].DICT_DISVALUE+"</option>");
//                			}
//                		}
//                	}
//                	$("#J_pcTransType").html(pcTransType.join(""));
//                	$("#J_pcState").html(pcState.join(""));
//                } else {
//                	 $.fn.sdInfo({ type:"fail",content:json.message ? json.message : "查询数据字典数据错误!" });
//                }
//     }});
    initLeaderList();//初始化列表
    $('#leaderForm').sdValidate();//添加验证规则
    /* 新增 */
    $("#J_AddLeader").die().live("click", function() {
        $.fn.sdResetForm("#leaderForm");
        $("#lcId").val("");
        $('#J_LeaderDiv').css("display", "").window({
            title: '新增领导信息',
            modal: true,
            minimizable:false,
            maximizable:false,
            collapsible:false,
            shadow:false,
            width:GLOBAL.ClientScreen.clientWidth() * 0.9,
            height:GLOBAL.ClientScreen.clientHeight() * 0.9
        });
    });

    /* 编辑 */
    $(".J_LeaderEdit").die().live("click", function() {
        $.fn.sdResetForm("#leaderForm");
        var id = $(this).parent().parent().children().eq(0).children().eq(0).val();
        $.getJSON('../config/searchCatalogConfig.action?t=' + new Date().getTime() + '&lc.lcId=' + id, function(json) {
            if (json.resultCode > 0) {
                formUnSerialize("leaderForm", "lc", json.lc);

            } else {
                $.fn.sdInfo({
                    type:"fail",
                    content:json.message ? json.message : "查询领导信息错误!"
                });
            }
        });
        $('#J_LeaderDiv').css("display", "").window({
            title: '编辑领导信息',
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
    $('#J_LeaderOk').die().live("click", function() {
        if ($(this).sdSubmitValidate("#leaderForm")) {
            var lcId = $("#lcId").val();
            var url,action;
            if (!lcId || lcId == "") {
                action = "新增";
                url = "../config/addCatalogConfig.action";
                var params = new StringBuffer();
                params.append("lc.LcName=" + $("#lcName").val()).append("&");
                params = params.toString();
                $.post(url, params, function(json) {
                    if (json.resultCode > 0) {
                        initLeaderList();
                        $('#J_LeaderDiv').window("close");
                    } else {
                        $.fn.sdInfo({
                            type:"fail",
                            content:json.message ? json.message : action + '领导信息失败!'
                        });
                    }
                });

            } else {
                action = "编辑";
                url = "../config/updateCatalogConfig.action";
                var params = new StringBuffer();
                params.append("lc.LcName=" + $("#lcName").val()).append("&");
                params.append("lc.LcId=" + $("#lcId").val()).append("&");
                params = params.toString();
                $.post(url, params, function(json) {
                    if (json.resultCode > 0) {
                        initLeaderList();
                        $('#J_LeaderDiv').window("close");
                    } else {
                        $.fn.sdInfo({
                            type:"fail",
                            content:json.message ? json.message : action + '领导信息失败!'
                        });
                    }
                });
            }
            // var params = $("#leaderForm").serialize();
        }
    });


    /* 编辑、新增 取消按钮 */
    $('#J_LeaderClose').die().live("click", function() {
        $('#J_LeaderDiv').window("close");
    });

    /* del */
    $('.J_LeaderDel').die().live("click", function() {
        var THIS = this;
        $.messager.confirm('删除', '是否确认删除所选领导信息?', function(r) {
            if (r) {
                var id = $(THIS).parent().parent().children().eq(0).children().eq(0).val();
                var params = "lc.LcId=" + id;
                $.post("../config/deleteCatalogConfig.action", params, function(json) {
                    if (json.resultCode > 0) {
                        initLeaderList();
                    } else {
                        $.fn.sdInfo({
                            type:"fail",
                            content:json.message ? json.message : '删除领导信息失败!'
                        });
                    }
                });
            }
        });
    });
});

$('#J_LeaderDelAll').click(function() {
    if ($(this).hasClass("abled")) {
        //获取参数
        var checkbox = $('#J_LeaderTable input:checkbox:not(.checkBoss):checked');
        var ides = "";
        checkbox.each(function() {
            ides = ides + "ides=" + $(this).val() + "&";
        });
        $.messager.confirm('批量删除', '是否确认删除所选领导信息?', function(r) {
            if (r) {
                $.post("../config/batchDeleteCatalogConfig.action", ides, function(data) {
                    if (data.resultCode && data.resultCode > 0) {
                        initLeaderList();
                    } else {
                        $.fn.sdInfo({
                            type:"fail",
                            content:data.message ? data.message : "批量删除领导信息失败"
                        });
                    }
                });
                $.fn.checkTest("J_LeaderTable");
                $('.checkBoss').attr("checked", false);
            }
        });
    }
});

/**
 * 初始列表数据
 */
function initLeaderList() {
    if (!leaderDataTable) {
        leaderDataTable = $("#J_LeaderTable").dataTable({
            bProcessing: false,
            bServerSide:false,
            bDestory:false,
            bRetrieve:true,
            sAjaxSource:"../config/listCatalogConfig.action",
            sAjaxDataProp: "lcList",
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
                        $('#J_LeaderTable input[type=checkbox]').sdCheckBox();
                    }
                });
            },
            aoColumns: [
                {
                    fnRender:function(obj) {
                        return "<input type='checkbox' value='" + obj.aData.lcId + "'/>";
                    }
                },
                {
                    fnRender:function(obj) {
                        return "<span class='hidden1 tl'>" + obj.aData.lcId + "</span>";
                    }
                },
                {
                    fnRender:function(obj) {
                        return "<span class='hidden1 tl'>" + obj.aData.lcId + "</span>";
                    }
                },
                {
                    fnRender:function(obj) {
                        return "<span>" + obj.aData.lcId + "</span>";
                    }
                },
                {
                    fnRender:function(obj) {
                        return "<span>" + obj.aData.lcId + "</span>";
                    }
                },
                {
                    fnRender:function(obj) {
                        return "<a class='edit J_LeaderEdit'></a>";
                    }
                },
                {
                    fnRender:function(obj) {
                        return "<a class='del J_LeaderDel'></a>";
                    }
                }
            ],
            sPaginationType: "full_numbers"
            //       aoColumnDefs: [
            //             { "bSortable": false, "aTargets": [0,1,2]}
            //         ]
        });
    } else {
        leaderDataTable.fnClearTable();
        $.getJSON(
                "../config/listCatalogConfig.action",
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
                    leaderDataTable.fnAddData(json.lcList);
                    setTableTrColor();
                    $('#J_LeaderTable input[type=checkbox]').sdCheckBox();
                }
                );
    }
}