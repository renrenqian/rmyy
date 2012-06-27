var doctorDataTable;
$(document).ready(function() {
    //查数据字典
    initDoctorList();//初始化列表
    $('#doctorForm').sdValidate();//添加验证规则
    /* 新增 */
    $("#J_AddDoc").die().live("click", function() {
        $.fn.sdResetForm("#doctorForm");
        //$("#lcId").val("");
        $("#diId").val("");
        //zq:填充科室下拉列表
        $.getJSON('../group/listDept.action', function(json) {
            if (json.resultCode > 0) {               
                var deptList = json.deptList;
                var optioin="<option>请选择...</option>";
                $(deptList).each(function(i, item) { 
                    optioin+="<option value="+item.dpId+">"+item.dpName+"</option>"
                });  
                $('#diDeptType').html(optioin);
                
            } else {
                $.fn.sdInfo({
                    type:"fail",
                    content:json.message ? json.message : "查询科室信息错误!"
                });
            }
        });
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
        $("#diId").val(id);
        $.getJSON('../member/searchDoctor.action?t=' + new Date().getTime() + '&doct.diId=' + id, function(json) {
            if (json.resultCode > 0) {
               // formUnSerialize("doctorForm", "doct", json.lc);
                  var docList = json.doct;
                  $(docList).each(function(i, item) {
                    $('#diId').val(item.diId);
                      $('#diName').val(item.diName);
                      $('#diSex').val(item.diSex);
                      $('#osExpertId').val(item.osExpertId);
                      $('#osFamousId').val(item.osFamousId);
                      $('#diEducation').val(item.diEducation);
                      $('#diPosition').val(item.diPosition);
                      $('#diResume').val(item.dpSite);   
                      $('#diMajor').val(item.diMajor);   
                      $('#diResearch_direction').val(item.diResearch_direction);
                      $('#diResume').val(item.diResume); 
                      $('#diResearch_direction').val(item.diResearch_direction);
                      $('#diAccomplishment').val(item.diAccomplishment);
//人才类型字段取值
//                      var rcTypeArray=item.dpType.split(',');
//                      for(var i=0;i<rcTypeArray.length;i++){
//                          $(".J_RcType[value='"+rcTypeArray[i]+"']").attr("checked", true);
//                      }
                      
                       
                    //zq:填充科室下拉列表
                    $.getJSON('../group/listDeptNames.action', function(json) {
                        if (json.resultCode > 0) {
                            //formUnSerialize("deptForm", "dept", json.dept);
                            var deptList = json.deptList;
                            var optioin="";
                            $(deptList).each(function(i, item) { 
//                               optioin+="<option value=\"" + item.dpId +"\">"+item.dpName+"</option>";
                            	 optioin+="<option value='"+item.dpId+"'>"+item.dpName+"</option>";
                            });  
                            $('#diDeptType').html(optioin);
                            
                            //zq:根据医生ID查找该医生所在科室
//                            $.getJSON('../group/listDeptByDoctId.action', function(json) {
//                                if(json.resultCode>0){
//                                }
//                            });
                        } else {
                            $.fn.sdInfo({
                                type:"fail",
                                content:json.message ? json.message : "查询科室信息错误!"
                            });
                        }
                    });
                  });     
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
            params.append("doct.osExpertId=" + $("#osExpertId").val()).append("&");
            params.append("doct.osFamousId=" + $("#osFamousId").val()).append("&");
            params.append("doct.diOrder=" + 1).append("&");
            params.append("doct.diSex=" + $("#diSex").val()).append("&");
            params.append("doct.diEducation=" + $("#diEducation").val()).append("&");
            params.append("doct.diResume=" + $("#diResume").val()).append("&");
            params.append("doct.diMajor=" + $("#diMajor").val()).append("&");
            params.append("doct.diResearch_direction=" + $("#diResearch_direction").val()).append("&");
            params.append("doct.diAccomplishment=" + $("#diAccomplishment").val()).append("&");

//            zq:人才类型字段取值
            var doctTypeArray = [];
            var doctTypeStr = "";
            $(".J_RcType:checked").each(function(){
               // doctTypeStr.concat($(this).val()).concat(",");
            	doctTypeStr+=$(this).val()+',';
            });

            doctTypeStr=doctTypeStr.substr(0,doctTypeStr.length-1);
           // params.append("doct.doctType=" + doctTypeStr).append("&");

            //alert(doctTypeStr.join(","));
            params.append("doct.doctType=" + doctTypeStr);//.append("&");

            params = params.toString();
            //zq:此处字符串拼接正确，但下方返回json中，无doctType信息
            
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
            $('#diId').val("");//clear the edit id while open edit.
            $('#osExpertId').val("0");//clear the edit id while open edit.
            $('#osFamousId').val("0");//clear the edit id while open edit.
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
    
    /*
     *   门诊信息部分开始
     *   门诊信息部分开始
     *   门诊信息部分开始
     * 
     */
    
    /* 新增医师、名医信息 */
    $(".J_ZJClick,.J_MYClick").die().live("click", function() {
        $.fn.sdResetForm("#treatForm");
        var doctId = $(this).parent().parent().children().eq(0).children().eq(0).val();
        $("#doctId").val(doctId);
              
        //var type=$(this).hasClass('J_ZJClick')?'专家门诊':'名医门诊';
        var type = '';
        //1:专家门诊， 2：名医门诊
        if($(this).hasClass('J_ZJClick')){
            type = '专家门诊';
            $("#osCate").val(1); 
        } else {
            type = '名医门诊';
            $("#osCate").val(2);
        }
        $('#J_TreatType').val(type);
        $('#J_TreatDiv').css("display", "").window({
            title: type+'信息编辑',
            modal: true,
            minimizable:false,
            maximizable:false,
            collapsible:false,
            shadow:false,
            width:GLOBAL.ClientScreen.clientWidth() * 0.9,
            height:GLOBAL.ClientScreen.clientHeight() * 0.9
        });
    });
    
    /* 编辑、新增 门诊信息确认按钮 */
    $('#J_TreatOk').die().live("click", function() {
        if ($(this).sdSubmitValidate("#treatForm")) {
            var osId = $("#osId").val();
            var url,action;
            if (!osId || undefined == osId ||osId == "") {
                action = "新增";
                url = "../member/addOPSer.action"; 
            } else {
                action = "编辑";
                url = "../member/updateOPSer.action";
            }
            var params = new StringBuffer();
            params.append("ops.doctId=" + $("#doctId").val()).append("&");
            params.append("ops.osId=" + osId).append("&");
            params.append("ops.osCate=" + $("#osCate").val()).append("&");
            //get the time
            //params.append("ops.osTime=" + $("#osTime").val()).append("&");
            params.append("ops.osLocation=" + $("#osLocation").val()).append("&");
            params.append("ops.osLimit=" + $("#osLimit").val()).append("&");
            params.append("ops.osStatus=" + $("#osStatus").val()).append("&");
            params.append("ops.osCost=" + $("#osCost").val()).append("&");
            params.append("ops.osBook_link=" + $("#osBook_link").val()).append("&");
            var osTimeStr = "";
            $(".radio_Type1:checked").each(function(){
               osTimeStr.concat($(this).val()).concat(",");
            });
            params.append("ops.osTime=" + osTimeStr.substring(0, osTimeStr.length-1));//.append("&");
            params = params.toString();
            $.post(url, params, function(json) {
                if (json.resultCode > 0) {
                    initDoctorList();
                    $('#J_TreatDiv').window("close");
                } else {
                    $.fn.sdInfo({
                        type:"fail",
                        content:json.message ? json.message : action + '医师信息失败!'
                    });
                }
            });
             //var params = $("#treatForm").serialize();
             $("#doctId").val(0);// clear the doct id while finish the edit
        }
    });  
    
    /* 编辑、新增 门诊信息 取消按钮 */
    $('#J_TreatClose').die().live("click", function() {
        $('#J_TreatDiv').window("close");
    });
    
    /*
     *   门诊信息部分结束
     *   门诊信息部分结束
     *   门诊信息部分结束
     * 
     */
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
                        return "<span class='hidden3 tl'>" + obj.aData.diName + "</span>";
                    }
                },
                {
                    fnRender:function(obj) {
                        return "<span class='hidden2 tl'>" + obj.aData.diDeptName + "</span>";
                    }
                },
                {
                    fnRender:function(obj) {
                        return "<span class='hidden2 tl'>" + obj.aData.diPosition + "</span>";
                    }
                },
                {
                    fnRender:function(obj) { 
                        //zq:此处需判断一个值，即是否为专家门诊（可对应查询是否有门诊编号，若有，是否为专家门诊），点击后允许用户新增或编辑专家门诊信息
                        var temp= obj.aData.osExpertId;
                        if(temp==0){
                            return "<span class='blue'>否</span>";
                        }else{
                            return "<span class='green unl J_ZJClick'>是</span>";
                        }
                        
                    }
                },
                {
                    fnRender:function(obj) { 
                        //zq:此处需判断一个值，即是否为名医门诊（可对应查询是否有门诊编号，若有，是否为名医门诊），点击后允许用户新增或编辑名医门诊信息
                        var temp= obj.aData.osFamousId;// if 0 not point, if other id, true
                        if(temp==0){
                            return "<span class='blue'>否</span>";
                        }else{
                            return "<span class='green unl J_MYClick'>是</span>";
                        }                       
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