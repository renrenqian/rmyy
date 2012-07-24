var contentDataTable;
var startIndex;
$(document).ready(function() {   
    initContentList();//初始化列表
    $('#contentForm').sdValidate();//添加验证规则
    /* 新增 */
    $("#J_AddContent").die().live("click", function() {
        $(window.parent.document).find("#centerIFrame").attr("src", "addVol.jsp");          
    });

    /* 编辑 */
    $(".J_ContentEdit").die().live("click", function() {
        var id = $(this).parent().parent().children().eq(0).children().eq(0).val();       
        $(window.parent.document).find("#centerIFrame").attr("src", "addVol.jsp?contId=" + id);             
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
    
    /* J_GenerateHomeJson */
    $('#J_GenerateHomeJson').die().live("click", function() {
    	var THIS = this;
    	$.messager.confirm('更新', '是否确认网站首页新闻?', function(r) {
    		if (r) {
    			var id = $(THIS).parent().parent().children().eq(0).children().eq(0).val();
    			var params = "continfo.contId=" + id;
    			$.post("../group/generateHomeJson.action", params, function(json) {
    				if (json.resultCode > 0) {
                        alert("更新完成");
    				} else {
    					$.fn.sdInfo({
    						type:"fail",
    						content:json.message ? json.message : '更新网站新闻失败!'
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
        var auditorResult = $("#auditorOption").val();
      $.getJSON('../group/auditorContent.action?t=' + new Date().getTime() + '&continfo.contId=' + auditorId + '&continfo.contAuditResult=' + auditorResult,  function(json) {
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
        var url="../group/listContent.action?continfo.ciCate=3000";//其它栏目      
        url = url + "&continfo.contAuditResult=-1";// admin manager list all the content with set -1, and site list only the audit success content without this
        contentDataTable = $("#J_ContentTable").dataTable({
            bProcessing: false,
            bServerSide:true,//设置服务端分页
            bDestory:false,
            //bRetrieve:true,
            sAjaxSource:url,
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
                params.push({ "name": "continfo.sSearch", "value": sSearch });
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
                        return "<span>" + obj.aData.contPublish_Time + "</span>";
                    }
                },
                {
                    fnRender:function(obj) {
                        return obj.aData.contAuthor?"<span class='hidden3 tl'>" + obj.aData.contAuthor + "</span>":"<span class='hidden3 tl'>未填写</span>";
                    }
                },
                {
                    fnRender:function(obj) {
                        var state = "未通过";
                        className = "red";
                        if ( 1 == obj.aData.contAuditResult ) {
                            state = "已通过";
                            className = "green";
                        }
                        return "<span class='" + className + " unl J_Audit'>" + state + "</span>";                       
                    }
                },
                {
                    fnRender:function(obj) {
                        return "<a class='view' href='/admin/volunteer/newsDetail.shtml?contId="+obj.aData.contId+"' target='_blank'></a>";
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
                         { "bSortable": false, "aTargets": [0,5,6,7]}
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
                    var oSettings = contentDataTable.fnSettings();
                    oSettings.iInitDisplayStart = startIndex;
                    contentDataTable.fnAddData(json.contList);
                    setTableTrColor();
                    $('#J_ContentTable input[type=checkbox]').sdCheckBox();
                }
                );
    }
}