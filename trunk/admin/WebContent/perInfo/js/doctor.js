var doctorDataTable;
var startIndex;
$(document).ready(function() {
    //查数据字典
    initDoctorList();//初始化列表
    $('#doctorForm').sdValidate();//添加验证规则
    /* 新增 */
    $("#J_AddDoc").die().live("click", function() {
        $.fn.sdResetForm("#doctorForm");
        //$("#lcId").val("");
        $("#diId").val("");
        $('#preview').attr('src','');
        //zq:填充科室下拉列表
        $.getJSON('../group/listDept.action', function(json) {
            if (json.resultCode > 0) {               
                var deptList = json.deptList;
                var optioin="<option>请选择...</option>";
                $(deptList).each(function(i, item) { 
                    optioin+="<option value="+item.dpId+">"+item.dpName+"</option>";
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
            width:GLOBAL.ClientScreen.clientWidth() * 0.95,
            height:GLOBAL.ClientScreen.clientHeight() * 0.95
        });
    });

    /* 编辑 */
    $(".J_DoctorEdit").die().live("click", function() {
        $.fn.sdResetForm("#doctorForm");
        $("#preview").attr("src",'');
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
                      $('#diOrder').val(item.diOrder);
                      $('#diResearch_direction').val(item.diResearch_direction);
                      $('#diResume').val(item.diResume); 
                      $('#diResearch_direction').val(item.diResearch_direction);
                      $('#diAccomplishment').val(item.diAccomplishment);
                      
                      //preview the image             
                      if(item.diPortrait){
                           $("#preview").attr({"src":"../" + replaceStr(item.diPortrait, "\\\\", "/" )});
                      }                          
                       //人才类型字段取值
                      var rcTypeArray=item.doctType.split(',');
                      for(var i=0;i<rcTypeArray.length;i++){
                          $(".J_RcType[value='"+rcTypeArray[i]+"']").attr("checked", true);
                      }
                    //zq:填充科室下拉列表
                    $.getJSON('../group/listDeptNames.action', function(json) {
                        if (json.resultCode > 0) {
                            //formUnSerialize("deptForm", "dept", json.dept);
                            var deptList = json.deptList;
                            var optioin="";
                            $(deptList).each(function(i, item) { 
                               optioin+="<option value=\"" + item.dpId +"\">"+item.dpName+"</option>";
                            });  
                            $('#diDeptType').html(optioin);
                            //zq：科室定位
                            $('#diDeptType option').each(function(){
                                if($(this).html()==item.diDeptName){
                                    $(this).attr('selected',true);
                                }                                
                            });
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
            width:GLOBAL.ClientScreen.clientWidth() * 0.95,
            height:GLOBAL.ClientScreen.clientHeight() * 0.95
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
            } else {
                action = "编辑";
                url = "../member/updateDoctor.action";
            }  
            $('#doctorForm').form('submit', {
                  url:url,
                  dataType :'json',
                  onSubmit: function(){
                      // set some special column before submit
                      var doctTypeStr = "";
                      $(".J_RcType:checked").each(function(){
                          doctTypeStr+=$(this).val()+',';
                      });
                      doctTypeStr=doctTypeStr.substr("0",doctTypeStr.length-1);
                      $('#doctType').val(doctTypeStr);
                 },
                 error:function (json) {
                     alert("内容异常:" + json.message) ; 
                 }, 
                 success:function(json){
                     json = eval('(' + json + ')');
                     if (json.resultCode > 0 ) {
                         initDoctorList();
                         $('#J_DoctorDiv').window("close");
                        } else  {
                            $.fn.sdInfo({
                                type : "fail",
                                content : json.message ? action+"内容错误:"+json.message : action+"内容错误:"
                            });
                        }
                 }
             });  
            $('#diId').val("");//clear the edit id while open edit.
            $('#osExpertId').val("0");//clear the ExpertId id while open edit.
            $('#osFamousId').val("0");//clear the FamousId id while open edit.
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
    
    /* J_GenerateDoctJson */
    $('#J_GenerateDoctJson').die().live("click", function() {
        var THIS = this;
        $.messager.confirm('更新', '是否确认更新网站数据?', function(r) {
            if (r) {
                var id = $(THIS).parent().parent().children().eq(0).children().eq(0).val();
                var params = "doct.diId=" + id;
                $.post("../member/generateDoctJson.action", params, function(json) {
                    if (json.resultCode > 0) {
                        alert("更新完成");
                        //initDoctorList();
                    } else {
                        $.fn.sdInfo({
                            type:"fail",
                            content:json.message ? json.message : '更新医师信息失败!'
                        });
                    }
                });
            }
        });
    });
    

    //批量删除
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
                            $.fn.checkTest("J_DoctorTable");
                        } else {
                            $.fn.sdInfo({
                                type:"fail",
                                content:data.message ? data.message : "批量删除医师信息失败"
                            });
                            $.fn.checkTest("J_DoctorTable");
                        }
                    });                                
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
    
    /* 新增/编辑 专家、名医门诊信息 */
    $(".J_ZJClick,.J_MYClick").die().live("click", function() {
        $.fn.sdResetForm("#treatForm");
        //zq:reset    
        $('.J_OSTime').each(function(){
            $(this).attr('checked',false);
        });     
                
        var doctId = $(this).parent().parent().children().eq(0).children().eq(0).val();
        $("#doctId").val(doctId);            
        
        //var type=$(this).hasClass('J_ZJClick')?'专家门诊':'名医门诊';
        var type = '';
        //1:专家门诊， 2：名医门诊
        if($(this).hasClass('J_ZJClick')){
            type = '专家门诊';
            var osExpertId = $(this).parent().parent().children().eq(0).children().eq(0).attr('osexpertid');
            $("#osId").val(osExpertId);   
            $("#osCate").val(1); 
        } else {
            type = '名医门诊';
            var osfamousid = $(this).parent().parent().children().eq(0).children().eq(0).attr('osfamousid');
            $("#osId").val(osfamousid); 
            $("#osCate").val(2);
        }
        $('#J_TreatType').val(type);
        
        if( undefined != $("#osId").val() && $("#osId").val()!='0'){
            //修改
             $.getJSON('../member/searchOPSer.action?t=' + new Date().getTime() + '&ops.osId='+ $("#osId").val(),function(json) {
                 var ops = json.ops;
                 if (json.resultCode > 0) {
                     $('#osLocation').val(ops.osLocation);
                     $('#osLimit').val(ops.osLimit);
                     $('#osStatus').val(ops.osStatus);
                     $('#osCost').val(ops.osCost);
                     $('#osBook_link').val(ops.osBook_link);
                     //checkbox:
                     var osTimeArr=ops.osTime.split(',');
                    for(var i=0;i<osTimeArr.length;i++){
                        $(".J_OSTime[value='"+osTimeArr[i]+"']").attr("checked", true);
                    }                              
                 }                   
             });
        }
       
        
        
        $('#J_TreatDiv').css("display", "").window({
            title: type+'信息编辑',
            modal: true,
            minimizable:false,
            maximizable:false,
            collapsible:false,
            shadow:false,
            width:GLOBAL.ClientScreen.clientWidth() * 0.95,
            height:GLOBAL.ClientScreen.clientHeight() * 0.95
        });
    });
    
    /* 编辑、新增 门诊信息确认按钮 */
    $('#J_TreatOk').die().live("click", function() {
        if ($(this).sdSubmitValidate("#treatForm")) {
            var osId = $("#osId").val();
            var url,action;
            if (!osId || undefined == osId ||osId == "" || osId=='0') {
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
            var osTimeStr = "";
            $(".J_OSTime:checked").each(function(){
                osTimeStr+=$(this).val()+',';
            });
            osTimeStr=osTimeStr.substr("0",osTimeStr.length-1);
            
            
            params.append("ops.osTime=" + osTimeStr).append("&");
            params.append("ops.osLocation=" + $("#osLocation").val()).append("&");
            params.append("ops.osLimit=" + $("#osLimit").val()).append("&");
            params.append("ops.osStatus=" + $("#osStatus").val()).append("&");
            params.append("ops.osCost=" + $("#osCost").val()).append("&");
            params.append("ops.osBook_link=" + $("#osBook_link").val()).append("&");           
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
            bServerSide:true,//设置服务端分页
            bDestory:false,
           // bRetrieve:true,
            sAjaxSource:"../member/listDoctor.action",
            sAjaxDataProp: "page.dataList",
            //oSearch: {"sSearch": ""},
            oSearch : {
                "sSearch" : "","bSmart": true 
            },
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
                params.push({ "name": "doct.sSearch", "value": sSearch });
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
                        json.sEcho = sEcho;
                        json.iTotalRecords = json.page.totalItemCount;
                        json.iTotalDisplayRecords = json.page.totalItemCount;

                        fnCallback(json);
                        setTableTrColor();
                        $('#J_DoctorTable input[type=checkbox]').sdCheckBox();
                    }
                });
            },
            aoColumns: [
                {
                    fnRender:function(obj) {
                        return "<input type='checkbox' value='" + obj.aData.diId + "' osExpertId='"+obj.aData.osExpertId+"' osFamousId='"+obj.aData.osFamousId+"'/>";
                    }
                },
                {
                    fnRender:function(obj) {
                        return "<span class='hidden3 tl'>" + obj.aData.diName + "</span>";
                    }
                },
                {
                    fnRender:function(obj) {
                        return obj.aData.diDeptName?"<span class='hidden2 tl'>" + obj.aData.diDeptName + "</span>":"<span class='hidden2 tl'>未填</span>";
                    }
                },
                {
                    fnRender:function(obj) {
                        return obj.aData.diPosition?"<span class='hidden2 tl'>" + obj.aData.diPosition + "</span>":"<span class='hidden2 tl'>未填</span>";
                    }
                },
                {
                    fnRender:function(obj) { 
                        //zq:此处需判断一个值，即是否为专家门诊（可对应查询是否有门诊编号，若有，是否为专家门诊），点击后允许用户新增或编辑专家门诊信息
                        var temp= obj.aData.osExpertId;
                        if(temp==0 || "" == temp || undefined == temp){
                            return "<span class='blue unl J_ZJClick'>否</span>";
                        }else{
                            return "<span class='green unl J_ZJClick'>是</span>";
                        }
                        
                    }
                },
                {
                    fnRender:function(obj) { 
                        //zq:此处需判断一个值，即是否为名医门诊（可对应查询是否有门诊编号，若有，是否为名医门诊），点击后允许用户新增或编辑名医门诊信息
                        var temp= obj.aData.osFamousId;// if 0 not point, if other id, true
                        if(temp==0 || "" == temp || undefined == temp){
                            return "<span class='blue unl J_MYClick'>否</span>";
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
            sPaginationType: "full_numbers",
                   aoColumnDefs: [
                         { "bSortable": false, "aTargets": [0,7,8]}
                     ]
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
                    if (!json.page.dataList) {
                        json.page.dataList = [];
                    }
                    var oSettings = doctorDataTable.fnSettings();
                    oSettings.iInitDisplayStart = startIndex;
                    doctorDataTable.fnAddData(json.page.dataList);
                    setTableTrColor();
                    $('#J_DoctorTable input[type=checkbox]').sdCheckBox();
                }
                );
    }
}