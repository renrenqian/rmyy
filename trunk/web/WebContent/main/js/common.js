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



//替换#$为%字符
function fixP(str){
	// add replace all function
    var reg = new RegExp("%", "g"); // g means replace all
    str=str.replace(reg, 'zq');
    return str;
}
//替换%为#$字符
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
	var docBodyHeight=$('.rightCol2').css('height').replace('px','')-120;
	$('.docBody').css('minHeight',docBodyHeight+'px');
	var newsBodyHeight=$('.rightCol2').css('height').replace('px','')-120;
	$('.newsBody').css('minHeight',newsBodyHeight+'px');
});