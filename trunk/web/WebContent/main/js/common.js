/**
 * Created by IntelliJ IDEA.
 * User: ZQ
 * Date: 12-6-9
 * Time: 下午6:36
 * To change this template use File | Settings | File Templates.
 */

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

$(document).ready(function () {
    //just for demo
    $('.newsTitle').not('.consultList').attr('href', '/web/main/news/newsDetail.shtml').attr('target', '_blank');
    $('.consultList li a').attr('href', '/web/main/patient/result.shtml').attr('target', '_blank');
    $('.Schedule').find('.paTr:odd').addClass('SchTrPaOdd');
    $('.Schedule').find('.paTr:even').addClass('SchTrPaEven');
    $('.docIntro a').attr('href', '/web/main/doctor/docPer.shtml'); 
});


// 导航部分
$(document).ready(function(){		
	//cookie 保存当前路径名称及链接地址	
	$('#J_TopNav ul li').die().live().bind("click",function(event){
		var myTitle1=$(this).parent().attr('bread');
		var myTitle2=$(this).attr('bread');
		$.cookie('myTitle3',null,{ path: '/'});
		
		$.cookie('myTitle1',myTitle1,{ path: '/'});
		$.cookie('myTitle2',myTitle2,{ path: '/'});
//		$.cookie('myTitle1',myTitle1);
//		$.cookie('myTitle2',myTitle2);	
		var href = $(this).find('a').attr('href');
        window.location.href = href;		
	});				
});


setMyBread();
function setMyBread(){
	myTitle1=$.cookie('myTitle1');
	
	myTitle2=$.cookie('myTitle2');	

	myTitle3=$.cookie('myTitle3');
	
	//设置面包导航
	var myBread="<a href='/web/main/index.shtml' class='breadB'>首页</a>";
	
	if(myTitle1){
		myBread+="<span>></span><a href='#' class='breadN'>"+myTitle1+"</a>";
		
//		if(myHref1){
//			myBread+="<span>></span><a href='#' class='breadB'>"+myTitle1+"</a>";
//		}else{
//			myBread+="<span>></span><a href='#' class='breadN'>"+myTitle1+"</a>";
//		}		
	}
	if(myTitle2){
		myBread+="<span>></span><a href='#' class='breadN'>"+myTitle2+"</a>";
//		
//		if(myHref2){
//			myBread+="<span>></span><a href='#' class='breadN'>"+myTitle2+"</a>";
//		}else{
//			myBread+="<span>></span><a href='#' class='breadN'>"+myTitle2+"</a>";
//		}		
	}
	if(myTitle3){
		myBread+="<span>></span><a href='#' class='breadN'>"+myTitle3+"</a>";
		
		
//		if(myHref3){
//			myBread+="<span>></span><a href='#' class='breadN'>"+myTitle3+"</a>";
//		}else{
//			myBread+="<span>></span><a href='#' class='breadN'>"+myTitle3+"</a>";
//		}		
	}
	$('.J_Bread').html(myBread);
}


//替换%为#$字符
function fixP(str){
	// add replace all function
    var reg = new RegExp("%", "g"); // g means replace all
    str=str.replace(reg, 'zq');
    return str;
}
//替换#$为%字符
function reP(str){ 
	var reg =new RegExp("zq","g");
	str=str.replace(reg,'%');
	return str;
}

//修复textarea编辑文本的格式问题
function fixTAFormat(str){
	var reg = new RegExp("\n", "g"); // g means replace all
	str=str.replace(reg,'</p><p>');  
	return str;
}


$(document).ready(function(){
	if($('.rightCol2').css('height')){
		var bodyHeight=$('.rightCol2').css('height').replace('px','')-120;
		$('.docBody').css('minHeight',bodyHeight+'px');
		$('.newsBody').css('minHeight',bodyHeight+'px');
	}	
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
//$.ajaxSetup({
//    cache: false,
//    global: true,
//    type: "POST"
//});
//if ($.messager) {
//    $.messager.defaults.ok = '确定';
//    $.messager.defaults.cancel = '取消';
//}
