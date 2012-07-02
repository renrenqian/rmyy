var contentDataTable;
$(document).ready(function() {
    initEmployeeList();//初始化列表
    //$('#contentForm').sdValidate();//添加验证规则
    /* 新增 */
    $("#J_AddEmployee").die().live("click", function() {
        $(window.parent.document).find("#centerIFrame").attr("src", "content/addEmploy.html");
    });

    /* 编辑 */
    $(".J_EmployeeEdit").die().live("click", function() {
        var id = $(this).parent().parent().children().eq(0).children().eq(0).val();
        $.getJSON('../online/searchEmployee.action?t=' + new Date().getTime() + '&emp.erId=' + id, function(json) {
            if (json.resultCode > 0) {
                //formUnSerialize("contentForm", "emp", json.emp);
            } else {
                $.fn.sdInfo({
                    type:"fail",
                    content:json.message ? json.message : "查询招聘信息错误!"
                });
            }
        });
        $(window.parent.document).find("#centerIFrame").attr("src", "content/addEmploy.html");
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
            bServerSide:false,
            bDestory:false,
            bRetrieve:true,
            sAjaxSource:"../online/listEmployee.action",
            sAjaxDataProp: "empList",
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
                        if (!json.empList) {
                            json.empList = [];
                        }
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
                        return "<span >" + obj.aData.erPosition + "</span>";
                    }
                },
                {
                    fnRender:function(obj) {
                        return "<span >" + obj.aData.erRecruit_no + "</span>";
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