var leaderDataTable;
$(document).ready(function() {
    initLeaderList();//初始化列表
    $('#leaderForm').sdValidate();//添加验证规则
    /* 新增 */
    $("#J_AddLeader").die().live("click", function() {
        $.fn.sdResetForm("#leaderForm");
        $("#leaderId").val("");
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
        $.getJSON('../member/searchLeader.action?t=' + new Date().getTime() + '&leader.liId=' + id, function(json) {
            if (json.resultCode > 0) {
                //formUnSerialize("leaderForm", "leader", json.leader);
                var leader = json.leader;
                $(leader).each(function(i, item) { 
                    $('#liId').val(item.liId); 
                    $('#liName').val(item.liName);
                    $('#ligmId').val(item.ligmId);
                    $('#dpOd_telephone').val(item.dpOd_telephone);
                    $('#liOrder').val(item.liOrder);
                    $('#liQuarters').val(item.liQuarters);
                    $('#liHold_period').val(item.liHold_period);
                    $('#liTelephone').val(item.liTelephone);
                    $('#liEmail').val(item.liEmail);
                    $('#liTech_position').val(item.liTech_position);
                    $('#liRang').val(item.liRang);
                    $('#liResume').val(item.liResume);
                    $('#liDesc').val(item.liDesc);
                });
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
            var leaderId = $("#liId").val();
            var url,actionName;
            if (!leaderId || leaderId == "") {
                actionName = "新增";
                url = "../member/addLeader.action";
            } else {
               actionName = "编辑";
                url = "../member/updateLeader.action";
            }

            $('#leaderForm').form('submit', {
              url:url,
              dataType : 'json',
              onSubmit: function(){
                 //$("#contDetail").val(editor.getData()); 
                // alert("contDetail:" + $("#contDetail").val());
                 $("#liCurrent").val($.trim( $("#liCate").find("option:selected").text()));
             },
             error:function (json) {
                 alert("内容异常:" + json.message) ; 
             }, 
             success:function(json){
                 json = eval('(' + json + ')');
                 if (json.resultCode > 0 ) {
                     initLeaderList();
                     $('#J_LeaderDiv').window("close");
                    } else  {
                        $.fn.sdInfo({
                            type : "fail",
                            content : json.message ? actionName+"内容错误:"+json.message : actionName+"内容错误:"
                        });
                    }
             }
         });  
//            var params = new StringBuffer();
//            params.append("leader.liId=" + leaderId).append("&");
//            params.append("leader.liName=" + $("#liName").val()).append("&");
//            params.append("leader.ligmId=" + $("#ligmId").val()).append("&");
//            params.append("leader.liCate=" + $("#liCate").val()).append("&");
//            params.append("leader.liCurrent=" + $.trim( $("#liCate").find("option:selected").text())).append("&");
//            params.append("leader.liOrder=" + 1).append("&");
//            params.append("leader.liHold_period=" + $("#liHold_period").val()).append("&");
//            params.append("leader.liQuarters=" + $("#liQuarters").val()).append("&");
//            params.append("leader.liTelephone=" + $("#liTelephone").val()).append("&");
//            params.append("leader.liEmail=" + $("#liEmail").val()).append("&");
//            params.append("leader.liTech_position=" + $("#liTech_position").val()).append("&");
//            params.append("leader.liRang=" + $("#liRang").val()).append("&");
//            params.append("leader.liResume=" + $("#liResume").val()).append("&");
//            params.append("leader.liDesc=" + $("#liDesc").val()).append("&");
//            params = params.toString();
//            $.post(url, params, function(json) {
//                if (json.resultCode > 0) {
//                    initLeaderList();
//                    $('#J_LeaderDiv').window("close");
//                } else {
//                    $.fn.sdInfo({
//                        type:"fail",
//                        content:json.message ? json.message : action + '领导信息失败!'
//                    });
//                }
//            });
            $('#liId').val(""); //clear the edit id while open edit.
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
                var params = "leader.liId=" + id;
                $.post("../member/deleteLeader.action", params, function(json) {
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
                $.post("../member/batchDeleteLeader.action", ides, function(data) {
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
            sAjaxSource:"../member/listLeader.action",
            sAjaxDataProp: "leaderList",
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
                        if (!json.leaderList) {
                            json.leaderList = [];
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
                        return "<input type='checkbox' value='" + obj.aData.liId + "'/>";
                    }
                },
                {
                    fnRender:function(obj) {
                        return "<span class='hidden1 tl'>" + obj.aData.liName + "</span>";
                    }
                },
                {
                    fnRender:function(obj) {
                        return "<span class='hidden1 tl'>" + obj.aData.ligmName + "</span>";
                    }
                },
                {
                    fnRender:function(obj) {
                        return "<span>" + obj.aData.liCurrent + "</span>";
                    }
                },
                {
                    fnRender:function(obj) {
                        return "<span>" + obj.aData.liOrder + "</span>";
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
                "../member/listLeader.action",
                function(json) {
                    if (json.resultCode > 0) {
                    } else {
                        $.fn.sdInfo({
                            type:"fail",
                            content:json.message ? json.message : "查询列表错误!"
                        });
                    }
                    if (!json.leaderList) {
                        json.leaderList = [];
                    }
                    leaderDataTable.fnAddData(json.leaderList);
                    setTableTrColor();
                    $('#J_LeaderTable input[type=checkbox]').sdCheckBox();
                }
                );
    }
}