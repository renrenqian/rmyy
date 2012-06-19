var doctorDataTable;
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
    initDoctorList();//初始化列表
    $('#doctorForm').sdValidate();//添加验证规则
    /* 新增 */
    $("#J_AddDoc").die().live("click", function() {
        $.fn.sdResetForm("#doctorForm");
        $("#lcId").val("");
        $('#J_DoctorDiv').css("display", "").window({
            title: '新增医师',
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
    $(".J_DoctorEdit").die().live("click", function() {
        $.fn.sdResetForm("#doctorForm");
        var id = $(this).parent().parent().children().eq(0).children().eq(0).val();
        $.getJSON('../member/searchDoctor.action?t=' + new Date().getTime() + '&doct.diId=' + id, function(json) {
            if (json.resultCode > 0) {
                formUnSerialize("doctorForm", "doct", json.lc);
            } else {
                $.fn.sdInfo({
                    type:"fail",
                    content:json.message ? json.message : "查询医师信息错误!"
                });
            }
        });
        $('#J_DoctorDiv').css("display", "").window({
            title: '编辑医师信息',
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
    $('#J_DoctorOk').die().live("click", function() {
        if ($(this).sdSubmitValidate("#doctorForm")) {
            var diId = $("#diId").val();
            var url,action;
            if (!diId || undefined == diId ||diId == "") {
                action = "新增";
                url = "../member/addDoctor.action";
//                var params = new StringBuffer();
//                params.append("doct.diName=" + $("#diName").val()).append("&");
//                params = params.toString();
//                $.post(url, params, function(json) {
//                    if (json.resultCode > 0) {
//                        initDoctorList();
//                        $('#J_DoctorDiv').window("close");
//                    } else {
//                        $.fn.sdInfo({
//                            type:"fail",
//                            content:json.message ? json.message : action + '医师信息失败!'
//                        });
//                    }
//                });
            } else {
                action = "编辑";
                url = "../member/updateDoctor.action";
            }
            var params = new StringBuffer();
            params.append("doct.diId=" + diId).append("&");
            params.append("doct.diName=" + $("#diName").val()).append("&");
            params.append("doct.diPosition=" + $("#diPosition").val()).append("&");
            params.append("doct.diDeptType=" + $("#diDeptType").val()).append("&");
            params.append("doct.diPortrait=" + $("#diPortrait").val()).append("&");
            params.append("doct.osId=" + $("#osId").val()).append("&");
            params.append("doct.diOrder=" + 1).append("&");
            params.append("doct.diSex=" + $("#diSex").val()).append("&");
            params.append("doct.diEducation=" + $("#diEducation").val()).append("&");
            params.append("doct.diResume=" + $("#diResume").val()).append("&");
            params.append("doct.diMajor=" + $("#diMajor").val()).append("&");
            params.append("doct.diResearch_direction=" + $("#diResearch_direction").val()).append("&");
            params.append("doct.diAccomplishment=" + $("#diAccomplishment").val()).append("&");
            params = params.toString();
            $.post(url, params, function(json) {
                if (json.resultCode > 0) {
                    initDoctorList();
                    $('#J_DoctorDiv').window("close");
                } else {
                    $.fn.sdInfo({
                        type:"fail",
                        content:json.message ? json.message : action + '医师信息失败!'
                    });
                }
            });
            // var params = $("#doctorForm").serialize();
        }
    });


    /* 编辑、新增 取消按钮 */
    $('#J_DoctorClose').die().live("click", function() {
        $('#J_DoctorDiv').window("close");
    });

    /* del */
    $('.J_DoctorDel').die().live("click", function() {
        var THIS = this;
        $.messager.confirm('删除', '是否确认删除所选医师信息?', function(r) {
            if (r) {
                var id = $(THIS).parent().parent().children().eq(0).children().eq(0).val();
                var params = "doct.diId=" + id;
                $.post("../member/deleteDoctor.action", params, function(json) {
                    if (json.resultCode > 0) {
                        initDoctorList();
                    } else {
                        $.fn.sdInfo({
                            type:"fail",
                            content:json.message ? json.message : '删除医师信息失败!'
                        });
                    }
                });
            }
        });
    });
});

$('#J_DoctorDelAll').click(function() {
    if ($(this).hasClass("abled")) {
        //获取参数
        var checkbox = $('#J_DoctorTable input:checkbox:not(.checkBoss):checked');
        var ides = "";
        checkbox.each(function() {
            ides = ides + "ides=" + $(this).val() + "&";
        });
        $.messager.confirm('批量删除', '是否确认删除所选医师信息?', function(r) {
            if (r) {
                $.post("../member/batchDeleteDoctor.action", ides, function(data) {
                    if (data.resultCode && data.resultCode > 0) {
                        initDoctorList();
                    } else {
                        $.fn.sdInfo({
                            type:"fail",
                            content:data.message ? data.message : "批量删除医师信息失败"
                        });
                    }
                });
                $.fn.checkTest("J_DoctorTable");
                $('.checkBoss').attr("checked", false);
            }
        });
    }
});

/**
 * 初始列表数据
 */
function initDoctorList() {
    if (!doctorDataTable) {
        doctorDataTable = $("#J_DoctorTable").dataTable({
            bProcessing: false,
            bServerSide:false,
            bDestory:false,
            bRetrieve:true,
            sAjaxSource:"../member/listDoctor.action",
            sAjaxDataProp: "doctList",
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
                        if (!json.doctList) {
                            json.doctList = [];
                        }
                        fnCallback(json);
                        setTableTrColor();
                        $('#J_DoctorTable input[type=checkbox]').sdCheckBox();
                    }
                });
            },
            aoColumns: [
                {
                    fnRender:function(obj) {
                        return "<input type='checkbox' value='" + obj.aData.diId + "'/>";
                    }
                },
                {
                    fnRender:function(obj) {
                        return "<span>" + obj.aData.diName + "</span>";
                    }
                },
                {
                    fnRender:function(obj) {
                        return "<span>" + obj.aData.diDeptName + "</span>";
                    }
                },
                {
                    fnRender:function(obj) {
                        return "<span>" + obj.aData.diPosition + "</span>";
                    }
                },
                {
                    fnRender:function(obj) { 
                        return "<span >" + '专家门诊' + "</span>";
                    }
                },
                {
                    fnRender:function(obj) { 
                        return "<span>" + '名医门诊' + "</span>";
                    }
                },
                {
                    fnRender:function(obj) {
                        return "<span>" + obj.aData.diOrder + "</span>";
                    }
                },
                {
                    fnRender:function(obj) {
                        return "<a class='edit J_DoctorEdit'></a>";
                    }
                },
                {
                    fnRender:function(obj) {
                        return "<a class='del J_DoctorDel'></a>";
                    }
                }
            ],
            sPaginationType: "full_numbers"
            //       aoColumnDefs: [
            //             { "bSortable": false, "aTargets": [0,1,2]}
            //         ]
        });
    } else {
        doctorDataTable.fnClearTable();
        $.getJSON(
                "../member/listDoctor.action",
                function(json) {
                    if (json.resultCode > 0) {
                    } else {
                        $.fn.sdInfo({
                            type:"fail",
                            content:json.message ? json.message : "查询列表错误!"
                        });
                    }
                    if (!json.doctList) {
                        json.doctList = [];
                    }
                    doctorDataTable.fnAddData(json.doctList);
                    setTableTrColor();
                    $('#J_DoctorTable input[type=checkbox]').sdCheckBox();
                }
                );
    }
}