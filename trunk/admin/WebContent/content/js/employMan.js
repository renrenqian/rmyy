var contentDataTable;
$(document).ready(function() {
    initEmployeeList();//初始化列表
    /* 新增 */
    $("#J_AddEmployee").die().live("click", function() {
        $(window.parent.document).find("#centerIFrame").attr("src", "addEmploy.jsp");
    });

    /* 编辑 */
    $(".J_EmployeeEdit").die().live("click", function() {
        var id = $(this).parent().parent().children().eq(0).children().eq(0).val();
//        $.getJSON('../online/searchEmployee.action?t=' + new Date().getTime() + '&emp.erId=' + id, function(json) {
//            if (json.resultCode > 0) {
//                //formUnSerialize("contentForm", "emp", json.emp);
//            } else {
//                $.fn.sdInfo({
//                    type:"fail",
//                    content:json.message ? json.message : "查询招聘信息错误!"
//                });
//            }
//        });
        $(window.parent.document).find("#centerIFrame").attr("src", "addEmploy.jsp?erId="+id);
    });

    /* del */
    $('.J_EmployeeDel').die().live("click", function() {
        var THIS = this;
        $.messager.confirm('删除', '是否确认删除所选招聘信息?', function(r) {
            if (r) {
                var id = $(THIS).parent().parent().children().eq(0).children().eq(0).val();
                var params = "emp.erId=" + id;
                $.post("../online/deleteEmployee.action", params, function(json) {
                    if (json.resultCode > 0) {
                        initEmployeeList();
                    } else {
                        $.fn.sdInfo({
                            type:"fail",
                            content:json.message ? json.message : '删除招聘信息失败!'
                        });
                    }
                });
            }
        });
    });
});


/**
 * 初始列表数据
 */
function initEmployeeList() {
    if (!contentDataTable) {
        contentDataTable = $("#J_EmployeeTable").dataTable({
            bProcessing: false,
            bServerSide:true,//设置服务端分页
            bDestory:false,
            //bRetrieve:true,
            sAjaxSource:"../online/listEmployee.action",
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
                        $('#J_EmployeeTable input[type=checkbox]').sdCheckBox();
                    }
                });
            },
            aoColumns: [
                {
                    fnRender:function(obj) {
                        return "<input type='checkbox' value='" + obj.aData.erId + "'/>";
                    }
                },
                {
                    fnRender:function(obj) {
                        return "<span class='tl hidden1'>" + obj.aData.erPosition + "</span>";
                    }
                },
                {
                    fnRender:function(obj) {
                        return "<span class='tl hidden3'>" + obj.aData.erRecruit_no + "</span>";
                    }
                },
                {
                    fnRender:function(obj) {
                        return "<span>" + obj.aData.erPublish_date + "</span>";
                    }
                },
                {
                    fnRender:function(obj) {
                        return "<span>" + obj.aData.erExpiry_date + "</span>";
                    }
                },           
                {
                    fnRender:function(obj) {
                        return "<a class='edit J_EmployeeEdit'></a>";
                    }
                },
                {
                    fnRender:function(obj) {
                        return "<a class='del J_EmployeeDel'></a>";
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
                "../online/listEmployee.action",
                function(json) {
                    if (json.resultCode > 0) {
                    } else {
                        $.fn.sdInfo({
                            type:"fail",
                            content:json.message ? json.message : "查询列表错误!"
                        });
                    }
                    if (!json.empList) {
                        json.empList = [];
                    }
                    contentDataTable.fnAddData(json.empList);
                    setTableTrColor();
                    $('#J_EmployeeTable input[type=checkbox]').sdCheckBox();
                }
                );
    }
}