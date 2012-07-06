var contentDataTable;
var contentType;
$(document).ready(function() {
    //zq：判断栏目类型是“医院新闻”，还是“其它栏目”
    contentType=$(window.parent.document).find('.layout-panel-center .panel-title').html();
    if(contentType=='网站内容管理&nbsp;&gt;&nbsp;其它栏目'){
        contentType="其他";
    }else{
        contentType="新闻";
    }
    
    initContentList();//初始化列表
    $('#contentForm').sdValidate();//添加验证规则
    /* 新增 */
    $("#J_AddContent").die().live("click", function() {
        if(contentType=='新闻'){
             $(window.parent.document).find("#centerIFrame").attr("src", "content/addContent.html");
        }else{
             $(window.parent.document).find("#centerIFrame").attr("src", "content/addCommon.html");
        }       
    });

    /* 编辑 */
    $(".J_ContentEdit").die().live("click", function() {
        var id = $(this).parent().parent().children().eq(0).children().eq(0).val();
        if(contentType=='新闻'){
                $(window.parent.document).find("#centerIFrame").attr("src", "content/addContent.html?contId=" + id);
           }else{
                $(window.parent.document).find("#centerIFrame").attr("src", "content/addCommon.html");
           }         
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
    
    
    /* 审核 */
    $(".J_Audit").die().live("click", function() {     
        var id = $(this).parent().parent().children().eq(0).children().eq(0).val();
        $("#auditorId").val(id);
//        $.getJSON('../group/searchDept.action?t=' + new Date().getTime() + '&dept.dpId=' + id, function(json) {
//            if (json.resultCode > 0) {
//                //formUnSerialize("deptForm", "dept", json.dept);
//                var deptList = json.dept;                
//            } else {
//                $.fn.sdInfo({
//                    type:"fail",
//                    content:json.message ? json.message : "查询科室信息错误!"
//                });
//            }
//        });
        $('#J_AuditDiv').css("display", "").window({
            title: '审核',
            modal: true,
            minimizable:false,
            maximizable:false,
            collapsible:false,
            shadow:false            
        });
    });
    
    /* 审核确认按钮 */
    $('#J_AuditOk').die().live("click", function() { 
        //code here
        var auditorId = $("#auditorId").val();
        var auditorResult = $("#auditorOption :checked").val();
      $.getJSON('../group/auditorContent.action?t=' + new Date().getTime() + '&continfo.contId=' + auditorId + '&continfo.contAudit_Result=' + auditorResult,  function(json) {
          if (json.resultCode > 0) {
              //formUnSerialize("deptForm", "dept", json.dept);
            initContentList();
            $('#J_AuditDiv').window("close");
          } else {
              $.fn.sdInfo({
                  type:"fail",
                  content:json.message ? json.message : "审核内容错误!"
              });
          }
      });
    });
    
    /* 审核取消按钮 */
    $('#J_AuditClose').die().live("click", function() {
        $('#J_AuditDiv').window("close");
    });
});

//批量删除
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
        //zq:此处需判断栏目类型加载列表数据
        var url;
        if(contentType=='新闻'){
            url="../group/listContent.action";//此处需修改
        }else{
            url="../group/listContent.action";//此处需修改
        }
        
        contentDataTable = $("#J_ContentTable").dataTable({
            bProcessing: false,
            bServerSide:true,//设置服务端分页
            bDestory:false,
            //bRetrieve:true,
            sAjaxSource:url,
            sAjaxDataProp: "page.dataList",
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
                    type: "GET",
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
                        //处理返回结果
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
                        return "<span class='hidden1 tl' style='width:100%;'>" + obj.aData.contTitle + "</span>";
                    }
                },
                {
                    fnRender:function(obj) {
                        return obj.aData.colName?"<span class='hidden3 tl'>" + obj.aData.colName + "</span>":"<span class='hidden2 tl'>未选择</span>";
                    }
                },
                {
                    fnRender:function(obj) {
                        return "<span>" + obj.aData.contPublish_Time + "</span>";
                    }
                },
                {
                    fnRender:function(obj) {
                        return obj.aData.contAuthor?"<span class='hidden3 tl'>" + obj.aData.contAuthor + "</span>":"<span class='hidden3 tl'>未填写</span>";
                    }
                },
                //隐藏作者列
//                {
//                    fnRender:function(obj) {
//                       var contAuthorName = obj.aData.contAuthorName;
//                       if(undefined == contAuthorName || !contAuthorName){
//                           return "<span>未知</span>";
//                       }else {
//                           return "<span>" + obj.aData.contAuthorName + "</span>";
//                       }
//                    }
//                },
                {
                    fnRender:function(obj) {
                        var state = "未通过";
                        className = "red";
                        if ( 1 == obj.aData.contAudit_Result ) {
                            state = "已通过";
                            className = "green";
                        }
                        return "<span class='" + className + " unl J_Audit'>" + state + "</span>";                       
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
            sPaginationType: "full_numbers",
                   aoColumnDefs: [
                         { "bSortable": false, "aTargets": [0,6,7]}
                     ]
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