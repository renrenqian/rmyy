/**
 * Last Editor: ZQ
 * Date: 11-11-22
 * Time: 上午9:30
 * Note: common层
 */
var GLOBAL = {};
GLOBAL.namespace = function(str) {
    var arr = str.split("."),o = GLOBAL;
    for (var i = (arr[0] == "GLOBAL") ? 1 : 0; i < arr.length; i++) {
        o[arr[i]] = o[arr[i]] || {};
        o = o[arr[i]];
    }
};

//=========================================================
// zq:cookie：copied from jquery:cookie.js
//=========================================================
jQuery.cookie = function(name, value, options) {
    if (typeof value != 'undefined') { // name and value given, set cookie
        options = options || {};
        if (value === null) {
            value = '';
            options.expires = -1;
        }
        var expires = '';
        if (options.expires && (typeof options.expires == 'number' || options.expires.toUTCString)) {
            var date;
            if (typeof options.expires == 'number') {
                date = new Date();
                date.setTime(date.getTime() + (options.expires * 24 * 60 * 60 * 1000));
            } else {
                date = options.expires;
            }
            expires = '; expires=' + date.toUTCString(); // use expires attribute, max-age is not supported by IE
        }
        var path = options.path ? '; path=' + options.path : '';
        var domain = options.domain ? '; domain=' + options.domain : '';
        var secure = options.secure ? '; secure' : '';
        document.cookie = [name, '=', encodeURIComponent(value), expires, path, domain, secure].join('');
    } else { // only name given, get cookie
        var cookieValue = null;
        if (document.cookie && document.cookie != '') {
            var cookies = document.cookie.split(';');
            for (var i = 0; i < cookies.length; i++) {
                var cookie = jQuery.trim(cookies[i]);
                // Does this cookie string begin with the name we want?
                if (cookie.substring(0, name.length + 1) == (name + '=')) {
                    cookieValue = decodeURIComponent(cookie.substring(name.length + 1));
                    break;
                }
            }
        }
        return cookieValue;
    }
};

//=========================================================
// zq:浏览器功能：
// 1.屏蔽指定区域文本被选择
// 2.禁止右键菜单
// 3.阻止事件默认行为(?)
// 4.禁用页面刷新
//=========================================================
GLOBAL.namespace("BrowserAction");
GLOBAL.BrowserAction = {
    //zq:屏蔽指定区域文本被选择
    disableSelection:function(target) {
        //zq:convert jQuery object to dom object
        target = target.get(0);
        if (typeof target.onselectstart != "undefined") //IE route
            target.onselectstart = function() {
                return false
            }
        else if (typeof target.style.MozUserSelect != "undefined") //Firefox route
            target.style.MozUserSelect = "none"
        else //All other route (ie: Opera)
            target.onmousedown = function() {
                return false
            }
        target.style.cursor = "default"
    },

    //禁止右键菜单
    /*
    forbidContextMenu:function() {
        $(document).bind("contextmenu", function(e) {
            return false;
        });
    },*/

    //阻止事件默认行为
    preDefault:function(e) {
        /* IE浏览器 */
        if ($.browser.msie) {
            e.keyCode = 0;
            e.returnValue = false;
        } else {
            e.preventDefault();
        }
    },

    //禁用页面刷新
    noRefresh:function(e) {
        // F5刷新
        if (e.keyCode == 116) {
            prDefault(e);
            return false;
        }
        //ctrl+r or ctrl+n 刷新
        if (e.ctrlKey && (e.keyCode == 78 || e.keyCode == 82)) {
            prDefault(e);
            return false;
        }
        // shitf +f10 右键菜单
        if (e.shiftKey && e.keyCode == 121) {
            prDefault(e);
            return false;
        }
        return true;
    }
};
/*
$(document).ready(function() {
    GLOBAL.BrowserAction.forbidContextMenu();
});*/
//=========================================================
// zq:返回客户浏览器常见参数
// 1.屏幕分辨率宽、高
//=========================================================
GLOBAL.namespace("ClientScreen");
GLOBAL.ClientScreen = {
    clientHeight:function() {
        return document.documentElement.clientHeight;
    },
    clientWidth:function() {
        return document.documentElement.clientWidth;
    },
    popWindowWidth:function(window, style) {
        switch (style) {
            case "border":
                return $(window).outerWidth() + 16;
                break;
            case "validate":
                return $(window).outerWidth() + 50;
                break;
        }
    }
};

//=========================================================
// zq:日期格式化
//=========================================================
function DateFormat() {

}
DateFormat.prototype = {
    _getHour:function(date) {
        return date.getHours();
    },
    _getMin:function(date) {
        return date.getMinutes();
    },
    _getSec:function(date) {
        return date.getSeconds();
    },
    _getDay:function(date) {
        return date.getDate();
    },
    _getMonth:function(date) {
        return date.getMonth() + 1;
    },
    _getYear:function(date) {
        return date.getFullYear();
    },
    isoTime:function(date) {
        //return type:12:12:12
        var myDate = "";
        this._getHour(date) < 10 ? myDate = "0" + this._getHour(date) + ":" : myDate = this._getHour(date) + ":";
        this._getMin(date) < 10 ? myDate = myDate + "0" + this._getMin(date) + ":" : myDate = myDate + this._getMin(date) + ":";
        this._getSec(date) < 10 ? myDate = myDate + "0" + this._getSec(date) : myDate = myDate + this._getSec(date);
        return myDate;
    },
    isoDate:function(date) {
        //return type:2011-04-05
        var myDate = this._getYear(date) + "-";
        this._getMonth(date) < 10 ? myDate = myDate + "0" + this._getMonth(date) + "-" : myDate = myDate + this._getMonth(date) + "-";
        this._getDay(date) < 10 ? myDate = myDate + "0" + this._getDay(date) : myDate = myDate + this._getDay(date);
        return myDate;
    },
    isoDateTime:function(date) {
        //return type:2011-09-09 12:12:12
        var myDate = "";
        myDate = this.isoDate(date) + " " + this.isoTime(date);
        return myDate;
    }
}

//=========================================================
// zq:菜单有关链接点击操作
//适用项:leftMenu , logo pic , topFunc
//父级区域:J_Menu
//子链接点:J_MenuItem（若无层级关系，则此项可无）
//class:触发条件
// folder:匹配具体项
// name:中文显示内容
// param:config.root:父级菜单jQuery对象
//param:config.handler:回调函数
//=========================================================
function Menu(config) {
    this._root = config.root;
    var menuItem = this._root.find('.J_MenuItem');
    this._handler = config.handler;
    var This = this;
    //判断是否存在子菜单项
    if (menuItem.length > 0) {
        for (var i = 0; i < menuItem.length; i++) {
            (function(_i) {
                menuItem.eq(_i).bind("click", function() {
                    This.loadTarget(menuItem.eq(_i));
                    This.setTitle(menuItem.eq(_i));
                    This.setCurMenu(menuItem.eq(_i));
                    This.btnAreaCtrl();
                });
            })(i);
        }
    } else {
        this._root.bind("click", function() {
            This.loadTarget();
            This.setTitle();
            This.setCurMenu();
            This.btnAreaCtrl();
        });
    }
}
Menu.prototype = {
    //加载具体页面
    loadTarget:function(menuItem) {
        if (menuItem) {
            var myFolder = this._root.attr("folder");
            myFolder = myFolder.substring(0, myFolder.length - 6);
            var myFile = menuItem.attr("id");
            $(window.parent.document).find('#centerIFrame').attr("src", myFolder + "/" + myFile + ".html");

        } else {
            switch (this._root.attr("name")) {
                case "welcome":
                    $(window.parent.document).find("#centerIFrame").attr("src", "welcome.html");
                    break;
                case "help":
                    $(window.parent.document).find("#centerIFrame").attr("src", "help/center.html");
                    break;
                case "msg":
                    $(window.parent.document).find("#centerIFrame").attr("src", "msgManage/receiveBox.html");
                    break;
                case "perInfo":
                    $(window.parent.document).find("#centerIFrame").attr("src", "sysManage/perInfoSet.html");
                    break;
                default:
                    break;
            }
        }
        if (this._handler) {
            this._handler();
        }
    },
    //当前菜单项高亮显示
    setCurMenu:function(menuItem) {
        $('body').find('.choose').attr('class', '');
        if (menuItem) {
            menuItem.addClass('choose');
        }
    },
    //设置导航栏题目
    setTitle:function(menuItem) {
        if (menuItem) {
            var myFolder = this._root.attr("name");
            var myFile = menuItem.attr("name");
            $(window.parent.document).find('.layout-panel-center .panel-title').html(myFolder + "&nbsp;>&nbsp;" + myFile);
        } else {
            switch (this._root.attr("name")) {
                case "welcome":
                    $(window.parent.document).find('.layout-panel-center .panel-title').text("欢迎使用");
                    break;
                case "help":
                    $(window.parent.document).find('.layout-panel-center .panel-title').text("帮助中心");
                    break;
                case "msg":
                    $(window.parent.document).find('.layout-panel-center .panel-title').text("我的消息");
                    break;
                case "perInfo":
                    $(window.parent.document).find('.layout-panel-center .panel-title').html("系统管理&nbsp;>&nbsp;个人信息设置");
                    break;
                default:
                    break;
            }
        }
    },
    //清除所有已打开窗口
    resetWindow:function() {
        $('.window,.window-shadow,.window-mask').remove();
    },
    btnAreaCtrl:function() {
        if ($('.fullWindowBtnArea').length > 0) {
            $('.fullWindowBtnArea').slideUp(1000, function() {
                $(this).remove();
            });
        }
    }
}

//=========================================================
// zq:top area func
//=========================================================
var sysManOutId,sysConfigOutId;
$(document).ready(function() {
    /* topFunc msg,help reset show */
    $('#topAreaFunc10,#topAreaFunc14').hover(function() {
        removeTopNav();
    });

    /* topFunc sysManage hover */
    $('#topAreaFunc7').hover(function() {
        removeTopNav();
        $('body').append("" +
                "<div class='topNav J_Menu' id='sysManageFolder' folder='sysManageFolder' name='系统管理'>" +
                "<ul>" +
                "<li><a href='#'id='roleManage' name='角色管理'  class='J_MenuItem'>角色管理</a></li>" +
                "<li><a href='#'id='userManage' name='用户管理' class='J_MenuItem'>用户管理</a></li>" +
                "<li><a href='#'id='sysLog' name='日志查看' class='J_MenuItem'>日志查看</a></li>" +
                "<li><a href='#'id='perInfoSet' name='个人信息设置' class='J_MenuItem'>个人信息设置</a></li>" +
                "</ul>" +
                "</div>");
        var sysManageFolder = $('#sysManageFolder');
        new Menu({
            root:sysManageFolder
        });
    }, function() {
        sysManOutId = setTimeout(removeTopNav, 1000);
    });

    $('#sysManageFolder').live("mouseover",
            function() {
                clearTimeout(sysManOutId);
            }).live("mouseout", function() {
        sysManOutId = setTimeout(removeTopNav, 1000);
    });

    /* topFunc sysConfig hover */
    $('#topAreaFunc8').hover(function() {
        removeTopNav();
        $('body').append("" +
                "<div class='topNav J_Menu' id='sysConfigFolder' folder='sysConfigFolder' name='系统配置'>" +
                "<ul>" +
                "<li><a href='#'id='sysParamConfig' name='系统参数配置'  class='J_MenuItem'>系统参数配置</a></li>" +
                "</ul>" +
                "</div>");
        var sysConfigFolder = $('#sysConfigFolder');
        alert("sysConfigFolder: " + sysConfigFolder);
        //Todo:zq-new too much?
        new Menu({
            root:sysConfigFolder
        });
    }, function() {
        sysConfigOutId = setTimeout(removeTopNav, 1000);
    });
    $('#sysConfigFolder').live("mouseover",
            function() {
                clearTimeout(sysConfigOutId);
            }).live("mouseout", function() {
        sysConfigOutId = setTimeout(removeTopNav, 1000);
    });

    function removeTopNav() {
        if (sysManOutId) {
            $("#sysManageFolder").remove();
            clearTimeout(sysManOutId);
        }
        if (sysConfigOutId) {
            $("#sysConfigFolder").remove();
            clearTimeout(sysConfigOutId);
        }
    }
});

/* checkbox全选、全不选控制 */
/* 自动调整全选控件；*/
/* toolbox功能显示控制 */
(function($) {
    $.fn.sdCheckBox = function() {
        return this.each(function() {
            var checkBox = new $.Checkbox($(this));
        });
    };

    $.Checkbox = function($elem) {
        this._checkbox = $elem;
        this.init(this);
    };

    $.extend($.Checkbox, {
        prototype:{
            init:function(obj) {
                var ctrlTable = obj._checkbox.attr("ctrl");
                if (ctrlTable) {
                    obj._checkbox.bind("click", function() {
                        if (this.checked == false) {
                            $('#' + ctrlTable).find('input[type=checkbox]').each(function() {
                                this.checked = false;
                            });
                        } else {
                            $('#' + ctrlTable).find('input[type=checkbox]').each(function() {
                                this.checked = true;
                            });
                        }
                        $.fn.toolBoxCtrl(ctrlTable);
                    });
                } else {
                    var ctrlTable = obj._checkbox.parent().parent().parent().parent().find('.checkBoss').attr('ctrl');
                    $.fn.triggerDelegate(obj._checkbox, "click", $.fn.checkTest, ctrlTable);
                }
            }
        }
    });

    $.extend($.fn, {
        triggerDelegate:function($elem, trigger, handler, param) {
            $elem.bind(trigger, function(event) {
                var target = $(event.target);
                if (target.is($elem)) {
                    return handler.call(target, param);
                }
            });
        },
        /*工具箱状态自动调整*/
        toolBoxCtrl:function(ctrlTable) {
            var checkbox = $('#' + ctrlTable).find('input[type=checkbox]:not(.checkBoss)');
            var sum = 0;
            for (var i = 0; i < checkbox.length; i++) {
                if (checkbox[i].checked == true) {
                    sum++;
                }
            }
            if (sum >= 2) {
                $('.toolBox .unit').removeClass('unabled').addClass('abled');
                $('.toolBox .unit').attr('title', '');
            } else {
                $('.toolBox .unit').removeClass('abled').addClass('unabled');
                $('.toolBox .unit').attr('title', '请先从下表中选择操作对象');
            }
        },
        /*全选框状态自动调整*/
        checkTest:function(ctrlTable) {
            var flag = true;
            var checkBoss = $('#' + ctrlTable).find('.checkBoss');
            //无任何复选框，则直接置“未全选状态”
            if ($('#' + ctrlTable).find('input:checkbox:not(.checkBoss)').length == 0) {
                flag = false;
            } else {
                $('#' + ctrlTable).find('input:checkbox:not(.checkBoss)').each(function() {
                    if (this.checked == false) {
                        flag = false;
                    }
                });
            }
            if (flag) {
                checkBoss[0].checked = true;
            } else {
                checkBoss[0].checked = false;
            }
            $.fn.toolBoxCtrl(ctrlTable);
        }
    });
})(jQuery);

/* 为.myTable类的表格，添加隔行变色效果 */
function setTableTrColor() {
    $('.display tbody tr:odd').removeClass().addClass("odd");
    $('.display tbody tr:even').removeClass().addClass("even");
}

//zq:dataTable paginate update checkbox state
$('.paginate_button').live("click", function() {
    $('input:checkbox').attr("checked", false);
    //$.fn.toolBoxCtrl();
    $('.toolBox .unit').removeClass('abled').addClass('unabled');
    $('.toolBox .unit').attr('title', '请先从下表中选择操作对象');
});


/* 内容检索 */
$(document).ready(function() {
    $(".J_AdvanceSerh").live("click", function() {
        $('#J_AdvanceSerhDiv').load("../content/contentSearch.html");
        $('#J_AdvanceSerhDiv').css("display", "").window({
            title:'内容检索',
            width:GLOBAL.ClientScreen.clientWidth() * 0.9,
            height:380,
            modal: true,
            minimizable:false,
            maximizable:false,
            collapsible:false,
            shadow:false,
            closed: false,
            onClose:function() {
                //若kissy calendar 未关闭，则推出窗口时，强制关闭，亦可防止重复创建canlendar
                $('.ks-cal-call').remove();
            }
        });
        $('.window').css("top", "10px");
    });
});

/* zq: input text focus */
$(document).ready(function() {
    var ctrl_input = $(".input_type1,.input_type2,.input_type3,.textArea_type1,.textArea_type2");
    ctrl_input.css("opacity", 0.8).live("focus",
            function() {
                $(this).addClass("textFocus").animate({opacity:"1"}, 300);
            }).live("blur", function() {
        $(this).removeClass("textFocus").animate({opacity:"0.5"}, 300);
    });
});


//自定义StringBuffer类
function StringBuffer() {
    this._strings = [];
    if (arguments.length == 1) {
        this._strings.push(arguments[0]);
    }
}
StringBuffer.prototype.append = function(str) {
    this._strings.push(str);
    return this;
};
StringBuffer.prototype.toString = function() {
    return this._strings.join("");
};
function getSpaceValue(size) {//计算文件或目录占用空间大小
    var value;
    if (size < 1024) {
        value = size + "B";
    } else if (size < 1024 * 1024) {
        value = ( size / 1024 ).toFixed(2) + "KB";
    } else if (size < 1024 * 1024 * 1024) {
        value = (size / (1024 * 1024)).toFixed(2) + "MB";
    } else if (size < 1024 * 1024 * 1024 * 1024) {
        value = (size / (1024 * 1024 * 1024)).toFixed(2) + "GB";
    } else {
        value = (size / (1024 * 1024 * 1024 * 1024)).toFixed(2) + "TB";
    }
    return value;
}
/**
 *
 * @param formId 表单id
 * @param eleNamePrefix 表单元素名的前缀,无表单元素名前缀填写null
 * @param obj 从服务器端获取的数据(实体对象属性)
 */
function formUnSerialize(formId, eleNamePrefix, obj) {//bindJsonToForm
    var form = document.getElementById(formId);
    //form.reset();//表单初始化
    var name = null;
    for (var key in obj) {//遍历实体对象的属性名称
        if (obj[key] || obj[key] == 0) {//判断属性是否有值
            if (eleNamePrefix && eleNamePrefix != "") {
                name = eleNamePrefix + "." + key;
            } else {
                name = key;
            }
            var jsonType = $.type(obj[key]);//toString.call(obj[key]).slice(8, -1).toLowerCase();//typeof(obj[key]).toString().toLowerCase();
            if (form.elements[name]) {
                var _eles_ = document.getElementsByName(name);
                if (_eles_.length > 1) {
                    var type = form.elements[name][0].type;
                } else {
                    var type = form.elements[name].type;
                }
                type = type.toLowerCase();
                if (type == "text" || type == "password" || type == "hidden" || type == "file" || type == "textarea") {
                    if (jsonType == "string" || jsonType == "number") {
                        form.elements[name].value = obj[key];
                    }
                    if (jsonType == "array" && obj[key].length > 0) {
                        form.elements[name].value = obj[key][0];
                    }//obj[key]为其它json类型暂不处理
                } else if (type == "select-one" || (type == "select" && !form.elements[name].multiple)) {//select-one
                    if (jsonType == "string" || jsonType == "number") {
                        form.elements[name].value = obj[key];//单选 select
                    }
                    if (jsonType == "array" && obj[key].length > 0) {
                        form.elements[name].value = obj[key][0];
                    }//obj[key]为其它json类型暂不处理
                } else if (type == "select-multiple" || (type == "select" && form.elements[name].multiple)) {//多选select
                    var options = form.elements[name].getElementsByName("option");
                    if (options) {
                        for (var i = 0; i < options.length; i++) {
                            options[i].selected = false;
                            if (jsonType == "string" || jsonType == "number") {//字符串
                                if (options[i].value == obj[key]) {
                                    options[i].selected = true;
                                }
                            }
                            if (jsonType == "array") {//数组
                                for (var j = 0; j < obj[key].length; j++) {
                                    if (obj[key][j] == options[i].value) {
                                        options[i].selected = true;
                                    }
                                }
                            }
                        }
                    }//obj[key]为其它json类型暂不处理
                } else if (type == "radio" || type == "checkbox") {//radio or checkbox
                    var eles = form.elements[name];
                    if (eles.length) {
                        for (var i = 0; i < eles.length; i++) {
                            //判断obj[key]是否为数组或者string或者数字
                            eles[i].checked = false;
                            if (jsonType == "string" || jsonType == "number") {//字符串
                                if (eles[i].value == obj[key]) {
                                    eles[i].checked = true;
                                }
                            }
                            if (jsonType == "array") {//数组
                                for (var j = 0; j < obj[key].length; j++) {
                                    if (obj[key][j] == eles[i].value) {//选中checkbox或者radio
                                        eles[i].checked = true;
                                    }
                                }
                            }//obj[key]为其它json类型暂不处理
                        }
                    } else {
                        if (jsonType == "string" || jsonType == "number") {
                            if (eles.value == obj[key]) {
                                eles.checked = true;
                            }
                        }
                        if (jsonType == "array" && obj[key].length > 0) {
                            if (eles.value == obj[key][0]) {
                                eles.checked = true;
                            }
                        }//obj[key]为其它json类型暂不处理
                    }
                }
            }
        }
    }
}

/* info msgBox */
(function($) {
    $.fn.sdInfo = function(settings) {
        var defaultSettings = {
            timeOut:'3000',
            type:'success',
            content:"操作成功！",
            autoClose:true
        }
        var options = $.extend(defaultSettings, settings);
        var eventScheduler = new EventScheduler();
        var info = new $.Info(options);
        if (info) {
            info.show();
            if (options.autoClose) {
                eventScheduler.set(function() {
                    info.hide();
                    eventScheduler.clear();
                }, options.timeOut);
            }
        }
    };

    /* constructor for sdTip */
    $.Info = function(options) {
        this._settings = $.extend(true, {}, $.Info.defaults, options);
        this.init();
    };

    $.extend($.Info, {
        defaults:{
            //校正用户输入的tip颜色，若未输入或输入错误，则使用系统默认颜色
            supportedTypes:['success','fail','warn','loading'],
            fixType:function(obj) {
                var hasType = false;
                var supportedTypes = $.Info.defaults.supportedTypes;
                for (var i = 0; i < supportedTypes.length; i++) {
                    if (obj._settings.type == supportedTypes[i]) {
                        hasType = true;
                    }
                }
                obj._settings.type = hasType ? obj._settings.type : supportedTypes[0];
            }
        },
        prototype:{
            /* init the info */
            init:function() {
                this._settings.fixType(this);
                var iconString;
                if (this._settings.type == "success") {
                    iconString = "<span class='gtl_ico_succ'></span>";
                }
                if (this._settings.type == "fail") {
                    iconString = "<span class='gtl_ico_fail'></span>";
                }
                if (this._settings.type == "warn") {
                    iconString = "<span class='gtl_ico_hits'></span>";
                }
                if (this._settings.type == "loading") {
                    iconString = " <span class='gtl_ico_clear'></span><img alt='' src='build/info/loading.gif'>";
                }
                return this.info || (this.info = $("<div id='q_Msgbox' class='sd_msgbox_layer_wrap none'>" +
                        "<span id='mode_tips_v2' class='sd_msgbox_layer' style='z-index: 10000;'>" +
                        iconString +
                        this._settings.content +
                        " <span class='gtl_end'></span> " +
                        "</span>" +
                        "</div>"));
            },

            /* show tip */
            show:function() {
                $('body').append(this.info);
                this.info.show();
            },

            /* hide tip */
            hide:function() {
                this.info.hide();
                $('#q_Msgbox').remove();

            }
        }
    });

    /* 定时器类 */
    function EventScheduler() {
    }

    EventScheduler.prototype = {
        set:function(func, timeout) {
            this.timer = setTimeout(func, timeout);
        },
        clear:function() {
            clearTimeout(this.timer);
        }
    }
})(jQuery);
$.ajaxSetup({
    cache: false,
    global: true,
    type: "POST"
});
if ($.messager) {
    $.messager.defaults.ok = '确定';
    $.messager.defaults.cancel = '取消';
}

//替换#$为%字符
function fixP(str){
    // add replace all function
    var reg = new RegExp("%", "g"); // g means replace all
    //str=str.replace(reg, 'zq');
    str=str.replace(reg, '#$');// should use this, as the java implemented as this
    return str;
}
//替换%为#$字符
function reP(str){
    //var reg =new RegExp("zq","g");
    var reg =new RegExp("#$","g");// should use this, as the java implemented as this
    str=str.replace(reg,'%');
    return str;
}