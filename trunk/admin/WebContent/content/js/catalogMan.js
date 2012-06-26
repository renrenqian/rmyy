// JavaScript Document
var tb_riList = [];
var dataTable;
$(document).ready(function() {
    initRoleManList();
    addZTreeObj = null;
    editZTreeObj = null;
    initRoleTree("add");

   
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
    });

    /* 角色编辑  ok */
    $('#J_EditRoleOk').die().live("click", function() {
        var name = $("#editRoleName").val();
        var desc = $("#editRoleDesc").val();
        var id = $("#editRoleId").val();
        if ($(this).sdValidate("#J_EditRoleForm")) {
            var length = editZTreeObj.getCheckedNodes().length;
            var params = new StringBuffer();
            params.append("ri.riId=" + id).append("&").append("ri.riName=" + name).append("&").append("ri.riDesc=" + desc).append("&");
            for (var i = 0; i < length; i++) {
                params.append("refList[" + i + "].privilegeInfo.piId=").append(editZTreeObj.getCheckedNodes()[i].piId).append("&");
                params.append("refList[" + i + "].roleInfo.riId=").append($("#editRoleId").val()).append("&");
            }
            params = params.toString();
            params = params.substring(0, params.length - 1);
            $.ajax({
                type: 'POST',
                url: "../system/updateRole.action",
                data: params,
                success: function(jsonObject, statusText, xhr) {
                    if (jsonObject.resultCode && jsonObject.resultCode > 0) {
                        initRoleManList();
                    } else {
                        //错误提示
                        if (jsonObject.message) {
                            $.fn.sdInfo({
                                type:"fail",
                                content:jsonObject.message ? jsonObject.message : '编辑角色失败!'
                            });
                            return;
                        }
                    }
                },
                dataType: 'json'
            });        
        }
    });
 
});


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