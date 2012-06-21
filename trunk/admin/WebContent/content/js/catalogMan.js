// JavaScript Document
var tb_riList = [];
var dataTable;
$(document).ready(function() {
    initRoleManList();
    addZTreeObj = null;
    editZTreeObj = null;
 
    /* 角色添加 div1 next */
    $('#J_AddRole1Next').die().live("click", function() {
        if ($.fn.sdSubmitValidate("#J_AddRoleStep01")) {
            $('#J_AddRoleDiv1').slideUp(0);
            $('#J_AddRoleDiv2').slideDown(0);
            initRoleTree("add");
            $('.fullWindowBtnArea').html("<a class='button btn-grey' id='J_AddRole2Prev'>上一步</a>" +
                    "<a class='button btn-green' id='J_AddRole2Ok'>确定</a>" +
                    "<a class='button btn-grey' id='J_AddRole2Close'>取消</a>");
        }
    });

    /* 角色添加 div2 ok */
    $('#J_AddRole2Ok').die().live("click", function() {
        var length = addZTreeObj.getCheckedNodes().length;
        if (Number(length) == 0) {
            $.fn.sdInfo({
                type:"warn",
                content:"请选择权限"
            });
            return;
        }
        var params = new StringBuffer();
        params.append("ri.riName=" + $("input[name='addRoleName']").val() + "&ri.riDesc=" + $("#addRoleDesc").val()).append("&");
        for (var i = 0; i < length; i++) {
            params.append("refList[" + i + "].privilegeInfo.piId=").append(addZTreeObj.getCheckedNodes()[i].piId).append("&");
        }
        params = params.toString();
        params = params.substring(0, params.length - 1);
        $.ajax({
            type: 'POST',
            url: "../system/addRole.action",
            data: params,
            success: function(jsonObject, statusText, xhr) {
                if (jsonObject.resultCode && jsonObject.resultCode > 0) {
                    initRoleManList();
                } else {
                    //错误提示
                    if (jsonObject.message) {
                        $.fn.sdInfo({
                            content:jsonObject.message ? jsonObject.message : '添加角色失败!',
                            type:"fail"
                        });
                        return;
                    }
                }
            },
            dataType: 'json'
        });
        $('.fullWindowBtnArea').slideUp(500, function() {
            $('.fullWindowBtnArea').remove();
        });
        $('#J_RoleManageDiv').slideDown(0);
        $('#J_AddRoleDiv2').slideUp(0, function() {
            $('#center').css({"overflow":""});
        });
    });

    /* 角色添加 div2 close */
    $('#J_AddRole2Close').die().live("click", function() {
        $('.fullWindowBtnArea').slideUp(500, function() {
            $('.fullWindowBtnArea').remove();
        });
        $('#J_AddRoleDiv2').slideUp(0);
        $('#J_RoleManageDiv').slideDown(0, function() {
            $('#center').css({"overflow":""});
        });
    });

    /* 角色编辑 */
    $('.J_RoleEdit').die().live("click", function() {
        $('#center').css({"overflow":"hidden"});
        initRoleTree("edit");
        //加载数据
        var id = $(this).parent().parent().children().eq(1).text();//id
        var name = $(this).parent().parent().children().eq(2).text();//name
        var desc = $(this).parent().parent().children().eq(3).text();//desc
        $("#editRoleName").val(name);
        $("#editRoleId").val(id);
        $("#editRoleDesc").val(desc);
        var params = new StringBuffer();
        params.append("ri.riId=" + id);
        $.ajax({
            type: 'POST',
            url: "../system/findPrivilegeByRoleId.action",
            data: params.toString(),
            async: false,
            success: function(jsonObject, statusText, xhr) {
                if (jsonObject.resultCode && jsonObject.resultCode > 0) {
                    var length = jsonObject.piList.length;
                    //全部置为未选择
                    for (var i = 0; i < length; i++) {
                        if (jsonObject.piList[i].piLevel == "3") {
                            var treeNode = editZTreeObj.getNodeByParam("piId", jsonObject.piList[i].piId);
                            if (null != treeNode) {
                                treeNode.checked = true;
                                editZTreeObj.updateNode(treeNode, true);
                            }
                        }
                    }
                } else {
                    // 错误提示
                    if (jsonObject.message) {
                        $.fn.sdInfo({
                            type:"fail",
                            content:jsonObject.message ? jsonObject.message : '查询角色关联权限失败!'
                        });
                        return;
                    }
                }
            },
            dataType: 'json'
        });
        $('#J_EditRoleDiv').slideDown(0);
        $('#J_RoleManageDiv').slideUp(0);
        $('body').append("<div class='fullWindowBtnArea none'>"
                + "<a class='button btn-green'id='J_EditRoleOk' ><span class='icon ico-blub'></span>确认</a>"
                + "<a class='button btn-grey'id='J_EditRoleClose'>取消</a>"
                + "</div>");
        $('.fullWindowBtnArea').slideDown(1000);
    });
});
function resetForm(formObjId) {
    document.getElementById(formObjId).reset();
}
function initRoleManList() {
    if (!dataTable) {
        dataTable = $("#roleManList").dataTable(
        {
            bProcessing: false,
            bServerSide:false,
            bDestory:true,
            sAjaxSource: "../system/listRole.action",
            sAjaxDataProp: "riList",
            oSearch: {"sSearch": ""},
            bAutoWidth:false,
            fnServerData:function(sSource, aoData, fnCallback) {
                //aoData提交请求参数
                //处理参数
                $.ajax({
                    dataType: 'json',
                    type: "GET",
                    url: sSource,
                    data: aoData,
                    success: function(json) {
                        if (json.resultCode < 0) {
                            //sendMessage(leftDocument,json.message?json.message:"查询角色列表失败",-1);
                        }
                        fnCallback(json);
                        $('#roleManList input[type=checkbox]').sdCheckBox();
                    }
                });
            },
            aoColumns: [
                {
                    bSortable:false,
                    fnRender:function(obj) {
                        return "<input type='checkbox' value='" + obj.aData.riId + "'/>";
                    }
                },
                { "sTitle": "角色编号",   "mDataProp": "riId" },
//                              { "sTitle": "角色名称",  "mDataProp": "riName" },
                {
                    fnRender:function(obj) {
                        return "<span class='hidden2 tl'>" + obj.aData.riName + "</span>";
                    }
                },
                {
                    fnRender:function(obj) {
                        return obj.aData.riDesc ? "<span class='hidden1 tl'>" + obj.aData.riDesc + "</span>" : "<span class='hidden1 tl'>无</span>";
                    }
                },
                {
                    fnRender:function(obj) {
                        return "<a class='edit J_RoleEdit' href='#' id='" + obj.aData.riId + "'></a>";
                    }
                },
                {
                    fnRender:function(obj) {
                        return "<a class='del' href='#' name='" + obj.aData.riName + "' id='" + obj.aData.riId + "'></a>";
                    }
                }
            ]
            ,sPaginationType: "full_numbers"
        }
                );
    } else {
        //重新获取数据
        dataTable.fnClearTable();
        $.ajax({
            dataType: 'json',
            type: "GET",
            url: '../system/listRole.action',
            async: false,
            success: function(json) {
                if (json.resultCode < 0) {
                    //sendMessage(leftDocument,json.message?json.message:"查询角色列表失败",-1);
                }
                dataTable.fnAddData(json.riList);
                //dataTable.fnDraw();
                $('#roleManList input[type=checkbox]').sdCheckBox();
            }
        });
    }
    setTableTrColor();
}

var addZTreeObj;
var editZTreeObj;
var setting;
function initRoleTree(type) {
    // 获取权限数据
    $.ajax({
        type: 'GET',
        url: "../system/listPrivilege.action",
        data: '',
        async: false,
        success: function(data, statusText, xhr) {
            setting = {
                check:{
                    enable:true,
                    chkStyle:"checkbox",
                    chkboxType: { "Y": "ps", "N": "ps" }
                },
                data:{
                    key:{
                        //childs:"piId",
                        name:"piName",
                        title:"piName"//name,title两个属性必须同时存在
                    },
                    simpleData:{
                        enable: true,
                        idKey: "piId",
                        pIdKey: "piParent"
                    }
                }
            };
            var newData = [];
            for (var i = 0; i < data.piList.length; i++) {
                if (data.piList[i].piShow == 0) {
                    //data.piList.splice(i,1);
                    //i--;
                } else {
                    newData.push(data.piList[i]);
                }
            }
            if (type == "add") {
                addZTreeObj = $.fn.zTree.init($("#addTree"), setting, newData);
                addZTreeObj.checkAllNodes(false);
            } else {
                editZTreeObj = $.fn.zTree.init($("#editTree"), setting, newData);
                editZTreeObj.checkAllNodes(false);
            }
        },
        dataType: 'json'
    });
}