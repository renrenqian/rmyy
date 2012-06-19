var deptDataTable;
$(document).ready(function() {
    //查数据字典
// $.ajax({
//        type: 'POST',
//        url: "../group/listAlldepts.action",
//        async: false,
//        success:
//            function(json) {
//                if (json.resultCode > 0) {
//                var dataDict = json.ddList;
//                //填充页面的下拉 J_pcTransType,J_pcState
//                var pcTransType=[]; g_pcTransType=[];
//                var pcState=[];g_pcState=[];
//
//                if(dataDict){
//                for(var i=0;i<dataDict.length;i++){
//                if(dataDict[i].DICT_KIND=="pc_trans_type"){
//                g_pcTransType.push(dataDict[i]);
//                pcTransType.push("<option value='"+dataDict[i].DICT_KEY+"'>"+dataDict[i].DICT_DISVALUE+"</option>");
//                }
//                            if(dataDict[i].DICT_KIND=="pc_state"){
//                            g_pcState.push(dataDict[i]);
//                            pcState.push("<option value='"+dataDict[i].DICT_KEY+"'>"+dataDict[i].DICT_DISVALUE+"</option>");
//                }
//                }
//                }
//                $("#J_pcTransType").html(pcTransType.join(""));
//                $("#J_pcState").html(pcState.join(""));
//                } else {
//                 $.fn.sdInfo({ type:"fail",content:json.message ? json.message : "查询数据字典数据错误!" });
//                }
//     }});
    initDeptList();//初始化列表
    $('#deptForm').sdValidate();//添加验证规则
    /* 新增 */
    $("#J_AddDept").die().live("click", function() {
        $.fn.sdResetForm("#deptForm");
        $("#dpId").val("");
        $('#J_DeptDiv').css("display", "").window({
            title: '新增科室',
            modal: true,
            minimizable:false,
            maximizable:false,
            collapsible:false,
            shadow:false,
            width:GLOBAL.ClientScreen.clientWidth() * 0.9,
            height:GLOBAL.ClientScreen.clientHeight()*0.9
        });
    });

    /* 编辑 */
    $(".J_DeptEdit").die().live("click", function() {
        $.fn.sdResetForm("#deptForm");
        var id = $(this).parent().parent().children().eq(0).children().eq(0).val();
        $.getJSON('../group/searchDept.action?t=' + new Date().getTime() + '&dept.dpId=' + id, function(json) {
            if (json.resultCode > 0) {
                formUnSerialize("deptForm", "dept", json.dept);
            
            } else {
                $.fn.sdInfo({
                    type:"fail",
                    content:json.message ? json.message : "查询科室信息错误!"
                });
            }
        });
        $('#J_DeptDiv').css("display", "").window({
            title: '编辑科室信息',
            modal: true,
            minimizable:false,
            maximizable:false,
            collapsible:false,
            shadow:false,
            width:GLOBAL.ClientScreen.clientWidth() * 0.9,
            height:GLOBAL.ClientScreen.clientHeight()*0.9
        });
    });

    /* 编辑、新增 确认按钮 */
    $('#J_DeptOk').die().live("click", function() {
       if ($(this).sdSubmitValidate("#deptForm")) {
           var deptId = $("#dpId").val();
            var url,action;
            if (!deptId || deptId == "") {
                action = "新增";
                url = "../group/addDept.action";
            
            } else {
                action = "编辑";
                url = "../group/updateDept.action";
//                var params = new StringBuffer();
//                params.append("dept.dpName=" + $("#dpName").val()).append("&");
//                params.append("dept.dpId=" + $("#dpId").val()).append("&");
//                params = params.toString();
//                $.post(url, params, function(json) {
//                if (json.resultCode > 0) {
//                initDeptList();
//                $('#J_DeptOk').window("close");
//                } else {
//                $.fn.sdInfo({
//                type:"fail",
//                content:json.message ? json.message : action + '科室失败!'
//                });
//                }
//                });
            }
            // below is the same for add and edit
            var params = new StringBuffer();
            params.append("dept.dpName=" + $("#deptName").val()).append("&");
            params.append("dept.dpIn_charge=" + $("#dpIn_charge").val()).append("&");
            var dpTypeStr = "";
            $("input[@type=checkbox][@checked]").each(function(){ //由于复选框一般选中的是多个,所以可以循环输出
             //alert($(this).val());
              dpTypeStr = $(this).val();
             },
             dpTypeStr += ","
            );
            params.append("dept.dpType=" + dpTypeStr).append("&");
            params.append("dept.dpNote=" + $("#dpNote").val()).append("&");
            params.append("dept.dpOd_telephone=" + $("#dpOd_telephone").val()).append("&");
            params.append("dept.dpEmail=" + $("#dpEmail").val()).append("&");
            params.append("dept.dpOrder=" + 1).append("&");
            params.append("dept.dpSite=" + $("#dpSite").val()).append("&");
            params.append("dept.dpLocation=" + $("#dpLocation").val()).append("&");
            params.append("dept.dpBed_counter=" + $("#dpBed_counter").val()).append("&");
            params.append("dept.dpBelong=" + $("#dpBelong").val()).append("&");
            params.append("dept.dpDesc=" + $("#dpDesc").val()).append("&");
            params.append("dept.dpAcademic_position=" + $("#dpAcademic_position").val()).append("&");
            params.append("dept.dpTech_adv=" + $("#dpTech_adv").val()).append("&");
            params = params.toString();
            $.post(url, params, function(json) {
            if (json.resultCode > 0) {
            initDeptList();
            $('#J_DeptDiv').window("close");
            } else {
            $.fn.sdInfo({
            type:"fail",
            content:json.message ? json.message : action + '科室失败!'
            });
            }
            });
           // var params = $("#deptForm").serialize();
        }
    });
    
    

    /* 编辑、新增科室的取消按钮 */
    $('#J_DeptClose').die().live("click", function() {
        $('#J_DeptDiv').window("close");
    });

    /* del */
    $('.J_DeptDel').die().live("click", function() {
        var THIS = this;
        $.messager.confirm('删除', '是否确认删除所选科室?', function(r) {
            if (r) {
                var id = $(THIS).parent().parent().children().eq(0).children().eq(0).val();
                var params = "dept.dpId=" + id;
                $.post("../group/deleteDept.action", params, function(json) {
                    if (json.resultCode > 0) {
                    initDeptList();
                    } else {
                        $.fn.sdInfo({
                            type:"fail",
                            content:json.message ? json.message : '删除科室失败!'
                        });
                    }
                });
            }
        });
    });
});

/* 批量删除 */
$('#J_DeptDelAll').click(function() {
    if ($(this).hasClass("abled")) {
        //获取参数
        var checkbox = $('#J_DeptTable input:checkbox:not(.checkBoss):checked');
        var ides = "";
        checkbox.each(function() {
            ides = ides + "ides=" + $(this).val() + "&";
        });
        $.messager.confirm('批量删除', '是否确认删除所选科室?', function(r) {
            if (r) {
                $.post("../group/batchDeleteDept.action", ides, function(data) {
                    if (data.resultCode && data.resultCode > 0) {
                    initDeptList();
                    } else {
                        $.fn.sdInfo({
                            type:"fail",
                            content:data.message ? data.message : "批量删除科室失败"
                        });
                    }
                });
                $.fn.checkTest("J_DeptTable");
                $('.checkBoss').attr("checked", false);
            }
        });
    }
});

/**
 * 初始列表数据
 */
function initDeptList() {
    if (!deptDataTable) {
        deptDataTable = $("#J_DeptTable").dataTable({
            bProcessing: false,
            bServerSide:false,
            bDestory:false,
            bRetrieve:true,
            sAjaxSource:"../group/listDept.action",
            sAjaxDataProp: "deptList",
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
                                content:json.message ? json.message : "查询科室列表错误!"
                            });
                        }
                        //处理返回结果
                        if (!json.deptList) {
                            json.deptList = [];
                        }
                        fnCallback(json);
                        setTableTrColor();
                        $('#J_DeptTable input[type=checkbox]').sdCheckBox();
                    }
                });
            },
            aoColumns: [
                {
                    fnRender:function(obj) {
                        return "<input type='checkbox' value='" + obj.aData.dpId + "'/>";
                    }
                },
                {
                    fnRender:function(obj) {
                        return "<span'>" + obj.aData.dpName + "</span>";
                    }
                },
                {
                    fnRender:function(obj) {
                        return "<span>" + obj.aData.dpgmName + "</span>";
                    }
                },
                {
                    fnRender:function(obj) {
                        return "<span>" + obj.aData.dpType + "</span>";
                    }
                },
                {
                    fnRender:function(obj) {
                        return "<span>" + obj.aData.dpIn_charge + "</span>";
                    }
                },
               {
                    fnRender:function(obj) {
                        return "<span>" + obj.aData.dpOrder + "</span>";
                    }
                },
                {
                    fnRender:function(obj) {
                        return "<a class='edit J_DeptEdit'></a>";
                    }
                },
                {
                    fnRender:function(obj) {
                        return "<a class='del J_DeptDel'></a>";
                    }
                }
            ],
            sPaginationType: "full_numbers"
   //       aoColumnDefs: [
  //             { "bSortable": false, "aTargets": [0,1,2]}
  //         ]
        });
    } else {
        deptDataTable.fnClearTable();
        $.getJSON(
        "../group/listDept.action",
                function(json) {
                    if (json.resultCode > 0) {
                    } else {
                        $.fn.sdInfo({
                            type:"fail",
                            content:json.message ? json.message : "查询科室列表错误!"
                        });
                    }
                    if (!json.deptList) {
                        json.deptList = [];
                    }
                    deptDataTable.fnAddData(json.deptList);
                    setTableTrColor();
                    $('#J_DeptTable input[type=checkbox]').sdCheckBox();
                }
                );
    }
}