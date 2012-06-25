// JavaScript Document
if (!window.g_userDataTable) {
    window.g_userDataTable = null;
}

$(document).ready(function() {
    initUserManList();
    $('#addUserForm1').sdValidate();//添加验证规则
    $('#editUserForm').sdValidate();//添加验证规则

    $("#J_roleList_edit input[type='checkbox']").live("click", function() {
        var checkList = $("#J_roleList_edit input[type='checkbox']");
        checkAllTrigger(checkList, "#editUserRoleSel_all");
    });

    $("#J_roleList_new input[type='checkbox']").live("click", function() {
        var checkList = $("#J_roleList_new input[type='checkbox']");
        checkAllTrigger(checkList, "#newUserRoleSel_all");
    });

    $("#newUserRoleSel_all").click(function() {
        $("#addUserForm2 input[name='user.roleId']").attr("checked",$(this).attr("checked") ? true : false);
    });

    $("#editUserRoleSel_all").click(function() {
        $("#editUserForm input[name='user.roleId']").attr("checked", $(this).attr("checked") ? true : false);
    });

    /* del all */
    $('#J_UserManDelAll').click(function() {
        if ($(this).hasClass("abled")) {
            //获取参数
            var checkbox = $('#userManList input:checkbox:not(.checkBoss):checked');
            var ides = "";
            checkbox.each(function() {
                ides = ides + "ides=" + $(this).val() + "&";
            });
            $.messager.confirm('批量删除', '是否确认删除所选用户?', function(r) {
                if (r) {
                    $.post("../system/batchDeleteUser.action", ides, function(data) {
                        if (data.resultCode && data.resultCode > 0) {
                            initUserManList();//重新获得数据
                        } else {
                            $.fn.sdInfo({
                                type:"fail",
                                content:data.message ? data.message : "批量删除用户失败"
                            });
                        }
                        $.fn.checkTest('userManList');
                        $('.checkBoss').attr("checked", false);
                    });
                }
            });
        }
    });
    /* del */
    $('.J_UserManDel').die().live("click", function() {
        var THIS = this;
        $.messager.confirm('删除', '是否确认删除所选用户?', function(r) {
            if (r) {
                var id = $(THIS).parent().parent().children().eq(0).children().eq(0).val();
                var params = "user.uiId=" + id;
                $.ajax({
                    dataType: 'json',
                    type: "POST",
                    data:params,
                    url: '../system/deleteUser.action',
                    success: function(json) {
                        if (json.resultCode > 0) {
                            initUserManList();
                            setTableTrColor();
                        } else {
                            $.fn.sdInfo({
                                type:"fail",
                                content:json.message ? json.message : '删除用户失败!'
                            });
                        }
                    }
                });
            }
        });
    });

    /* 用户添加 div1 open */
    $('#J_UserAdd').die().live("click", function() {
        //判断是否有验证class  validateThis
        $.fn.sdResetForm("#addUserForm1");
        //查角色
        $.getJSON("../system/listRole.action", function(data) {
            if (data.resultCode && data.resultCode > 0) {
                //查用户所具有的角色列表
                var id = $("#uiId").val();
                var riList = data.riList;
                //产生复选框
                var roles = [];
                $(riList).each(function(i, item) {
                    roles.push(
                            "<li>"
                                    + "<input  id=\"newUserRoleSel_" + item.riId + "\"/ name='user.roleId' type=\"checkbox\"  value=\"" + item.riId + "\">"
                                    + "<label for=\"newUserRoleSel_" + item.riId + "\">" + item.riName + "</label>"
                                    + "</li>"
                            );
                });
                $("#J_roleList_new").html(roles.join(""));
            } else {
                $.fn.sdInfo({
                    type:"fail",
                    content:data.message ? data.message : '查询角色列表错误'
                });
            }
        });
        //查CP,填充cp作用域
        /*
        $.getJSON("../config/listSelectCp.action?t=" + new Date().getTime(), function(data) {
            if (data.resultCode && data.resultCode > 0) {
                var cpList = data.cpList;
                //产生复选框
                var cpOptions = ["<option>请选择CP</option>"];
                $(cpList).each(function(i, item) {
                    cpOptions.push("<option value='" + item.cpId + "'>" + item.cpName + "</option>");
                });
                $("#cpId_add").html(cpOptions.join(""));
            } else {
                $.fn.sdInfo({
                    type:"fail",
                    content:data.message ? data.message : '查询CP列表错误'
                });
            }
        });
        */
        //查发布 配置-目标作用域
        /*
        $.getJSON("../config/listSelectPubConfig.action?t=" + new Date().getTime(), function(data) {
            if (data.resultCode && data.resultCode > 0) {
                var pcList = data.pcList;
                //产生复选框
                var pcOptions = ["<option>请选择发布配置</option>"];
                $(pcList).each(function(i, item) {
                    pcOptions.push("<option value='" + item.pcId + "'>" + item.pcName + "</option>");
                });
                $("#publishId_add").html(pcOptions.join(""));
            } else {
                $.fn.sdInfo({
                    type:"fail",
                    content:data.message ? data.message : '查询发布配置列表错误'
                });
            }
        });
        */
        $('#center').css({"overflow":"hidden"});
        $('#J_UserManageDiv').slideUp(0);
        $('#J_AddUserDiv1').slideDown(0);
        $('body').append("<div class='fullWindowBtnArea none'>"
                + "<a class='button btn-orange' id='J_AddUser1Next'>下一步</a>"
                + "<a class='button btn-grey' id='J_AddUser1Close'>取消</a>"
                + "</div>");
        $('.fullWindowBtnArea').slideDown(1000);
    });

    /* 用户添加 div1 next */
    $('#J_AddUser1Next').die().live("click", function() {
        if ($(this).sdSubmitValidate("#addUserForm1")) {
            $('#J_AddUserDiv1').slideUp(0);
            $('#J_AddUserDiv2').slideDown(0);
            $('.fullWindowBtnArea').html("<a class='button btn-grey' id='J_AddUser2Prev'>上一步</a>" +
                    "<a class='button btn-green' id='J_AddUser2Ok'>" +
                    "<span class='icon ico-blub'></span>确定</a>" +
                    "<a class='button btn-grey' id='J_AddUser2Close'>取消</a>");
        }
    });

    /* 用户添加 div1 close */
    $('#J_AddUser1Close').die().live("click", function() {
        $('.fullWindowBtnArea').slideUp(500, function() {
            $('.fullWindowBtnArea').remove();
        });
        $('#J_AddUserDiv1').slideUp(0);
        $('#J_UserManageDiv').slideDown(0, function() {
            $('#center').css({"overflow":""});
        });
    });

    /* 用户添加 div2 prev */
    $('#J_AddUser2Prev').die().live("click", function() {
        //$("#editUserForm").removeClass("validateThis");
        //todo:validate this??
        if (!$('#addUserForm1').hasClass('validateThis')) {
            $('#addUserForm1').addClass('validateThis');
        }
        $('#J_AddUserDiv1').slideDown(0);
        $('#J_AddUserDiv2').slideUp(0);
        $('.fullWindowBtnArea').html("<a class='button btn-orange' id='J_AddUser1Next'>下一步</a>" +
                "<a class='button btn-grey' id='J_AddUser1Close'>取消</a>");
    });

    /* 用户添加 div2 ok */
    $('#J_AddUser2Ok').die().live("click", function() {
        //提交表单
        if ($(this).sdSubmitValidate("#addUserForm1")) {//表单校验
            if ($("#uiPwd").val() != $("#confirmPwd").val()) {
                $.fn.sdInfo({
                    type:"warn",
                    content:data.message ? data.message : '确认密码与密码不一致'
                });
                return;
            }
            //密码加密
            $("#uiPwd").val(MD5($("#uiPwd").val()));
            var params = $("#addUserForm1").serialize();
            var roleIds = $("#addUserForm2").serialize();
//            if(!roleIds || roleIds==""){
//                return;
//            }
            params = params + "&user.uiEnable=1&" + roleIds;
            $.post("../system/addUser.action", params, function(data) {
                if (data.resultCode && data.resultCode > 0) {
                    initUserManList();//刷新表格
                    $('.fullWindowBtnArea').slideUp(500, function() {
                        $('.fullWindowBtnArea').remove();
                    });
                    $('#J_UserManageDiv').slideDown(0);
                    $('#J_AddUserDiv2').slideUp(0, function() {
                        $('#center').css({"overflow":""});
                    });
                } else {
                    $.fn.sdInfo({
                        type:"fail",
                        content:data.message ? data.message : '新增用户失败'
                    });
                }
            });
        } else {
            //验证失败
        }
    });

    /* 用户添加 div2 close */
    $('#J_AddUser2Close').die().live("click", function() {
        $('.fullWindowBtnArea').slideUp(500, function() {
            $('.fullWindowBtnArea').remove();
        });
        $('#J_AddUserDiv2').slideUp(0);
        $('#J_UserManageDiv').slideDown(0, function() {
            $('#center').css({"overflow":""});
        });
    });

    /* 用户编辑 */
    $('.J_UserEdit').die().live("click", function() {
        $.fn.sdResetForm("#editUserForm");
        //查CP,填充cp作用域，cp目标作用域
        /*
        $.getJSON("../config/listSelectCp.action", function(data) {
            if (data.resultCode && data.resultCode > 0) {
                var cpList = data.cpList;
                //产生复选框
                var cpOptions = ["<option>请选择CP</option>"];
                $(cpList).each(function(i, item) {
                    cpOptions.push("<option value='" + item.cpId + "'>" + item.cpName + "</option>");
                });
                $("#cpId_edit").html(cpOptions.join(""));
            } else {
                $.fn.sdInfo({
                    type:"fail",
                    content:data.message ? data.message : '查询CP列表错误'
                });
            }
        });
        */
        //查发布 配置-目标作用域
        /*
        $.getJSON("../config/listSelectPubConfig.action?t=" + new Date().getTime(), function(data) {
            if (data.resultCode && data.resultCode > 0) {
                var pcList = data.pcList;
                //产生复选框
                var pcOptions = ["<option>请选择发布配置</option>"];
                $(pcList).each(function(i, item) {
                    pcOptions.push("<option value='" + item.pcId + "'>" + item.pcName + "</option>");
                });
                $("#publishId_edit").html(pcOptions.join(""));
            } else {
                $.fn.sdInfo({
                    type:"fail",
                    content:data.message ? data.message : '查询发布配置列表错误'
                });
            }
        });*/
        //查用户数据
        var userId = $(this).parent().parent().children().eq(0).children().eq(0).val();
        $.getJSON("../system/searchUserById.action?t=" + new Date().getTime(), "user.uiId=" + userId, function(data) {
            //填充表单
            if (data.resultCode && data.resultCode > 0) {
                _formUnSerialize("editUserForm", "user", data.user);
                if (data.user && data.user.cp && data.user.cp.cpId) {
                    $("#cpId_edit").val(data.user.cp.cpId);
                }
                if (data.user && data.user.pc && data.user.pc.pcId) {
                    $("#publishId_edit").val(data.user.pc.pcId);
                }
                //查角色列表
                $.getJSON("../system/listRole.action?t=" + new Date().getTime(), function(data) {
                    if (data.resultCode && data.resultCode > 0) {
                        //查用户所具有的角色列表
                        var id = $("#uiId").val();
                        var riList = data.riList;
                        //产生复选框
                        var roles = [];
                        $(riList).each(function(i, item) {
                            roles.push(
                                    "<li>"
                                            + "<input  id=\"editUserRoleSel_" + item.riId + "\"/ name='user.roleId' type=\"checkbox\"  value=\"" + item.riId + "\">"
                                            + "<label for=\"editUserRoleSel_" + item.riId + "\">" + item.riName + "</label>"
                                            + "</li>"
                                    );
                        });
                        $("#J_roleList_edit").html(roles.join(""));
                        $.getJSON("../system/listRoleByUserId.action?t=" + new Date().getTime(), {"user.uiId":id}, function(data) {
                            if (data.resultCode && data.resultCode > 0) {
                                var roleList = data.roleList;
                                $(roleList).each(function(i, item) {
                                    $("#editUserRoleSel_" + item.riId).attr("checked", true);
                                });
                            } else {
                                //错误提示
                                $.fn.sdInfo({
                                    type:"fail",
                                    content:data.message ? data.message : '查询用户所具有角色失败'
                                });
                            }
                            var checkList = $("#J_roleList_edit input[type='checkbox']");
                            checkAllTrigger(checkList, "#editUserRoleSel_all");
                        });
                    } else {
                        //错误提示
                        $.fn.sdInfo({
                            type:"fail",
                            content:data.message ? data.message : '查询所具有角色失败'
                        });
                    }
                });
            } else {
                //错误提示
                $.fn.sdInfo({
                    type:"fail",
                    content:data.message ? data.message : '查询用户信息失败'
                });
            }
        });
        $('#center').css({"overflow":"hidden"});
        $('#J_EditUserDiv').slideDown(0);
        $('#J_UserManageDiv').slideUp(0);
        $('body').append("<div class='fullWindowBtnArea none'>"
                + "<a class='button btn-green'id='J_EditUserOk'><span class='icon ico-blub'></span>确认</a>"
                + "<a class='button btn-grey' id='J_EditUserClose'>取消</a>"
                + "</div>");
        $('.fullWindowBtnArea').slideDown(1000);
    });

    /* 用户编辑 close */
    $('#J_EditUserClose').die().live("click", function() {
        $('.fullWindowBtnArea').slideUp(500, function() {
            $('.fullWindowBtnArea').remove();
        });
        $('#J_EditUserDiv').slideUp(0);
        $('#J_UserManageDiv').slideDown(0, function() {
            $('#center').css({"overflow":""});
        });
    });

    /* 用户编辑 ok */
    $('#J_EditUserOk').die().live("click", function() {
        if ($(this).sdSubmitValidate("#editUserForm")) {//表单校验
            $('.fullWindowBtnArea').slideUp(500, function() {
                $('.fullWindowBtnArea').remove();
            });
            var params = $("#editUserForm").serialize();
            $.post("../system/updateUser.action", params, function(data) {
                if (data.resultCode && data.resultCode > 0) {
                    initUserManList();//刷新表格
                    $('#J_EditUserDiv').slideUp(0);
                    $('#J_UserManageDiv').slideDown(0, function() {
                        $('#center').css({"overflow":""});
                    });
                } else {
                    $.fn.sdInfo({
                        type:"fail",
                        content:data.message ? data.message : '编辑用户信息失败'
                    });
                }
            });
        }
    });

    /* online state */
    $('.J_UserState').die().live("click", function() {
        var myHandle = $(this);
        var enable = null,action = null;
        var uid = myHandle.parent().parent().children().eq(0).children().eq(0).val();
        if (myHandle.hasClass("green")) {
            enable = 0;
            action = "停用";
        } else {
            enable = 1;
            action = "启用";
        }
        var params = "user.uiId=" + uid + "&user.uiEnable=" + enable;
        $.messager.confirm('更改启停状态', "是否" + action + "该用户？", function(r) {
            if (r) {
                $.post("../system/toggleEnableUser.action", params, function(data) {
                    if (data.resultCode && data.resultCode > 0) {
                        initUserManList();//重新获得数据
                        $.fn.sdInfo({
                            type:"success",
                            content:data.message ? data.message : action + "用户成功!"
                        });
                    } else {
                        $.fn.sdInfo({
                            type:"fail",
                            content:data.message ? data.message : action + "用户失败!"
                        });
                    }
                });
            }
        });
    });
});

/* checkbox 群控制 checkboss 显示状态*/
function checkAllTrigger(checkList, checkBoss) {
    var length = checkList.length;
    var flag = true;
    for (var i = 0; i < length; i++) {
        if (checkList.eq(i).attr("checked") != "checked") {
            flag = false;
        }
    }
    if (!flag) {
        $(checkBoss).attr("checked", false);
    } else {
        $(checkBoss).attr("checked", true);
    }
}
/**
 * 初始化用户列表
 */
function initUserManList() {
    if (!g_userDataTable) {
        g_userDataTable = $("#userManList").dataTable({
            bProcessing: false,
            bServerSide:false,
            bDestory:false,
            bRetrieve:true,
            sAjaxSource: "../system/listUser.action",
            sAjaxDataProp: "userList",
            oSearch: {"sSearch": ""},
            bAutoWidth:false,
            fnServerData:function(sSource, aoData, fnCallback) {
                $.ajax({
                    dataType: 'json',
                    type: "POST",
                    url: sSource,
                    data: aoData,
                    success: function(json) {
                        if (json.resultCode > 0) {
                        } else {
                            $.fn.sdInfo({
                                type:"fail",
                                content:json.message ? json.message : "查询用户列表错误"
                            });
                        }
                        //处理返回结果
                        if (!json.userList) {
                            json.userList = [];
                        }
                        fnCallback(json);
                        $('#userManList input[type=checkbox]').sdCheckBox();
                    }
                });
            },
            aoColumns: [
                {
                    fnRender:function(obj) {
                        return "<input type='checkbox' value='" + obj.aData.uiId + "'/>";
                    }
                },
                {
                    fnRender:function(obj) {
                        return "<span class='hidden2 tl''>" + obj.aData.uiAccount + "</span>";
                    }
                },
                {
                    fnRender:function(obj) {
                        return "<span class='hidden2 tl''>" + obj.aData.uiName + "</span>";
                    }
                },
                {
                    fnRender:function(obj) {
                        return "<span class='hidden2 tl'>" + obj.aData.roleNames + "</span>";
                    }
                },
                {
                    fnRender:function(obj) {
                        if (obj.aData.online == "在线") {
                            return "<span class='green'>" + obj.aData.online + "</span>";
                        } else {
                            return "<span class='red'>" + obj.aData.online + "</span>";
                        }
                    }
                },
                {
                    fnRender:function(obj) {
                        if (obj.aData.uiEnable == 1) {
                            return "<a class='green unl J_UserState'>启用</a>";
                        } else {
                            return "<a class='red unl J_UserState'>停用</a>";
                        }
                    }
                },
                {
                    fnRender:function(obj) {
                        return '<a class="edit J_UserEdit"></a>';
                    }
                },
                {
                    fnRender:function(obj) {
                        return '<a class="del J_UserManDel"></a>';
                    }
                }
            ],
            sPaginationType: "full_numbers",
            aoColumnDefs: [
                { "bSortable": false, "aTargets": [0,6,6]}
            ]
        });
    } else {
        //重新获取数据
        g_userDataTable.fnClearTable();
        $.ajax({
            dataType: 'json',
            type: "POST",
            url: '../system/listUser.action',
            success: function(json) {
                if (json.resultCode > 0) {
                } else {
                    $.fn.sdInfo({
                        type:"fail",
                        content:json.message ? json.message : "查询用户列表错误"
                    });
                }
                if (!json.userList) {
                    json.userList = [];
                }
                g_userDataTable.fnAddData(json.userList);
                $('#userManList input[type=checkbox]').sdCheckBox();
                //g_userDataTable.fnDraw();
                //回到原来的页数
            }
        });
    }
}
//填充表单
function _formUnSerialize(formId, eleNamePrefix, obj) {
    var form = document.getElementById(formId);
    var name = null;
    for (var key in obj) {
        if (obj[key] || obj[key] == 0) {
            if (eleNamePrefix) {
                name = eleNamePrefix + "." + key;
            } else {
                name = key;
            }
            if (form.elements[name]) {
                form.elements[name].value = obj[key];
            }
        }
    }
}
/**
 * 初始化密码
 */
function initPwd() {
    var uiId = $("#uiId").val();
    var params = "user.uiId=" + uiId + "&user.uiPwd=" + MD5("888888");
    $.post("../system/updateUserPwd.action", params, function(data) {
        if (data.resultCode && data.resultCode > 0) {
            $.fn.sdInfo({
                type:"success",
                content:"初始化密码成功!"
            });
        } else {
            $.fn.sdInfo({
                type:"fail",
                content:"初始化密码失败!"
            });
        }
    });
}