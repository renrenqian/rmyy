var deptDataTable;
$(document).ready(function() {
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
        $("#dpId").val(id);
        $.getJSON('../group/searchDept.action?t=' + new Date().getTime() + '&dept.dpId=' + id, function(json) {
            if (json.resultCode > 0) {
                //formUnSerialize("deptForm", "dept", json.dept);
                var deptList = json.dept;
                $(deptList).each(function(i, item) {
                    $('#dpId').val(item.dpId);    
                    $('#deptName').val(item.dpName);    
                    $('#dpIn_charge').val(item.dpIn_charge);   
                    $('#dpOd_telephone').val(item.dpOd_telephone);   
                    $('#dpEmail').val(item.dpEmail);   
                    $('#dpSite').val(item.dpSite);   
                    $('#dpLocation').val(item.dpLocation);   
                    $('#dpBed_counter').val(item.dpBed_counter);   
                    $('#dpNote').val(item.dpNote);   
                    $('#dpDesc').val(reP(item.dpDesc));   
                    $('#dpAcademic_position').val(reP(item.dpAcademic_position));   
                    $('#dpTech_adv').val(reP(item.dpTech_adv));   
                    $('#dpResearch_direction').val(reP(item.dpResearch_direction));
                    
                    var dpTypeArray=item.dpType.split(',');
                    for(var i=0;i<dpTypeArray.length;i++){
                        $(".J_DeptType[value='"+dpTypeArray[i]+"']").attr("checked", true);
                    }
                });     
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
            if (!deptId || undefined == deptId || deptId == "" ) {
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
            params.append("dept.dpId=" + deptId).append("&");
            params.append("dept.dpName=" + $("#deptName").val()).append("&");
            params.append("dept.dpIn_charge=" + $("#dpIn_charge").val()).append("&");
            var dpTypeStr = [];
            $(".J_DeptType:checked").each(function(){
                dpTypeStr.push($(this).val());
            });
            params.append("dept.dpType=" + dpTypeStr).append("&");
            params.append("dept.dpNote=" + $("#dpNote").val()).append("&");
            params.append("dept.dpOd_telephone=" + $("#dpOd_telephone").val()).append("&");
            params.append("dept.dpEmail=" + $("#dpEmail").val()).append("&");
            params.append("dept.dpOrder=" + 1).append("&");
            params.append("dept.dpSite=" + $("#dpSite").val()).append("&");
            params.append("dept.dpLocation=" + $("#dpLocation").val()).append("&");
            params.append("dept.dpBed_counter=" + $("#dpBed_counter").val()).append("&");
            params.append("dept.dpBelong=" + $("#dpBelong").val()).append("&");

            //以下四个字段，进行%转换
            params.append("dept.dpDesc=" +fixP($("#dpDesc").val()) ).append("&");
            params.append("dept.dpAcademic_position=" + fixP($("#dpAcademic_position").val())).append("&");
            params.append("dept.dpResearch_direction=" + fixP($("#dpResearch_direction").val())).append("&");
            params.append("dept.dpTech_adv=" + fixP($("#dpTech_adv").val())).append("&"); 
                       
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
            $('#dpId').val("");//clear the edit id while open edit.
           // var params = $("#deptForm").serialize();                   
        }
    });      
    
    /* 编辑、新增科室的取消按钮 */
    $('#J_DeptClose').die().live("click", function() {
        $('#J_DeptDiv').window("close");
        $(".J_DeptType").attr("checked", false);
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
                        return "<span class='hidden1 tl'>" + obj.aData.dpName + "</span>";
                    }
                },
                {
                    fnRender:function(obj) {
                        return "<span class='hidden1 tl'>" + obj.aData.dpgmName + "</span>";
                    }
                },
                {
                    fnRender:function(obj) {
                        return "<span class='hidden1 tl'>" + obj.aData.dpType + "</span>";
                    }
                },
                {
                    fnRender:function(obj) {
                        return "<span class='hidden2 tl'>" + obj.aData.dpIn_charge + "</span>";
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
            sPaginationType: "full_numbers",
          aoColumnDefs: [
               { "bSortable": false, "aTargets": [0,6,7]}
           ]
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