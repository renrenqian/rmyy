/**
 * Created by IntelliJ IDEA. User: ZQ Date: 12-6-9 Time: 下午6:36 To change this
 * template use File | Settings | File Templates.
 */

// =========================================================
// zq:cookie：copied from jquery:cookie.js
// =========================================================
jQuery.cookie = function(name, value, options) {
	if (typeof value != 'undefined') { // name and value given, set cookie
		options = options || {};
		if (value === null) {
			value = '';
			options.expires = -1;
		}
		var expires = '';
		if (options.expires
				&& (typeof options.expires == 'number' || options.expires.toUTCString)) {
			var date;
			if (typeof options.expires == 'number') {
				date = new Date();
				date.setTime(date.getTime()
						+ (options.expires * 24 * 60 * 60 * 1000));
			} else {
				date = options.expires;
			}
			expires = '; expires=' + date.toUTCString(); // use expires
			// attribute,
			// max-age is not
			// supported by IE
		}
		var path = options.path ? '; path=' + options.path : '';
		var domain = options.domain ? '; domain=' + options.domain : '';
		var secure = options.secure ? '; secure' : '';
		document.cookie = [ name, '=', encodeURIComponent(value), expires,
				path, domain, secure ].join('');
	} else { // only name given, get cookie
		var cookieValue = null;
		if (document.cookie && document.cookie != '') {
			var cookies = document.cookie.split(';');
			for ( var i = 0; i < cookies.length; i++) {
				var cookie = jQuery.trim(cookies[i]);
				// Does this cookie string begin with the name we want?
				if (cookie.substring(0, name.length + 1) == (name + '=')) {
					cookieValue = decodeURIComponent(cookie
							.substring(name.length + 1));
					break;
				}
			}
		}
		return cookieValue;
	}
};

$(document).ready(function() {
					// just for demo
					var myCata2=GetParameter('cata2');
//					if(myCata2=='rczp'){
//						$('.newsTitle').not('.consultList').attr('href',
//						'/admin/main/news/employDetail.shtml?cata1=xxzx&cata2='+myCata2);
//					}else{
//						$('.newsTitle').not('.consultList').attr('href',
//						'/admin/main/news/newsDetail.shtml?cata1=xxzx&cata2='+myCata2);
//					}
					
					$('.consultList li a').attr('href',
							'/admin/main/patient/result.shtml?cata1=hzfwzx&cata2=qtfw&cata3=zxzxzx').attr('target',
							'_blank');
					$('.Schedule').find('.paTr:odd').addClass('SchTrPaOdd');
					$('.Schedule').find('.paTr:even').addClass('SchTrPaEven');
					$('.docIntro a').attr('href',
							'/admin/main/doctor/docPer.shtml');
				});

// 导航部分
$(document).ready(function() {
	// li跳转
	$('#J_TopNav ul li').die().live().bind("click", function(event) {
		var href = $(this).find('a').attr('href');
		var target = $(this).find('a').attr('target');
		if (target == '_blank') {
			window.open(href);
			return false;
		} else {
			window.location.href = href;
		}
	});	
	
	// 设置bread
	if (window.location.search) {
		var myCata1 = GetParameter('cata1');
		var myCata2 = GetParameter('cata2');
		var myCata3 = GetParameter('cata3');
		var myCata2Href;
		switch (myCata1) {
			case 'yygl':myCata1 = '医院概览';break;
			case 'xxzx':myCata1 = '信息中心';break;
			case 'hzfwzx':myCata1 = '患者服务中心';break;
			case 'ksdh':myCata1 = '科室导航';break;
			case 'zjjs':myCata1 = '专家介绍';break;
			case 'kyxs':myCata1 = '科研学术';break;
			case 'yywh':myCata1 = '医院文化和教育';break;
		}
		switch (myCata2) {		
			case 'yyjj':myCata2 = '医院简介';break;
			case 'zzjg':myCata2 = '组织结构';break;
			case 'yzjy':myCata2 = '院长寄语';break;
			case 'ldbz':myCata2 = '领导班子';break;
			case 'ywgk':myCata2 = '院务公开';break;
			case 'yyls':myCata2 = '医院历史';break;
			case 'zdjs':myCata2 = '重大记事';break;
			case 'lxwm':myCata2 = '联系我们';break;
			case 'yygg':myCata2 = '医院公告';myCata2Href='/admin/main/news/newsList.shtml?cata1=xxzx&cata2=yygg&colId=1201';break;
			case 'yyxw':myCata2 = '医院新闻';myCata2Href='/admin/main/news/newsList.shtml?cata1=xxzx&cata2=yyxw&colId=1202';break;
			case 'jtdt':myCata2 = '集团动态';myCata2Href='/admin/main/news/newsList.shtml?cata1=xxzx&cata2=jtdt&colId=1203';break;
			case 'dmtzx':myCata2 = '多媒体中心';break;
			case 'rczp':myCata2 = '人才招聘';break;
			case 'zbcg':myCata2 = '招标采购';myCata2Href='/admin/main/news/newsList.shtml?cata1=xxzx&cata2=zbcg&colId=1206';break;	
			case 'xzzx':myCata2 = '下载中心';break;	
			case 'mzcx':myCata2 = '门诊查询';break;
			case 'jzzn':myCata2 = '就诊指南';break;
			case 'qtfw':myCata2 = '其它服务';break;
			case 'lcks':myCata2 = '临床科室';myCata2Href='/admin/main/dept/deptList.shtml?cata1=ksdh&cata2=lcks';break;
			case 'sszd':myCata2 = '省市重点';myCata2Href='/admin/main/dept/deptList.shtml?cata1=ksdh&cata2=sszd';break;
			case 'tszk':myCata2 = '特色专科';myCata2Href='/admin/main/dept/deptList.shtml?cata1=ksdh&cata2=tszk';break;
			case 'yjks':myCata2 = '医技科室';myCata2Href='/admin/main/dept/deptList.shtml?cata1=ksdh&cata2=yjks';break;
			case 'znks':myCata2 = '职能科室';myCata2Href='/admin/main/dept/deptList.shtml?cata1=ksdh&cata2=znks';break;
			case 'zjyl':myCata2 = '专家一览';break;
			case 'mymz':myCata2 = '名医门诊';break;
			case 'gjrc':myCata2 = '各级人才';break;
			case 'mzpb':myCata2 = '门诊排班信息';break;
			case 'kydt':myCata2 = '科研动态';break;
			case 'kjcg':myCata2 = '科技成果';break;
			case 'gxsb':myCata2 = '高新设备';break;
			case 'xsjl':myCata2 = '学术交流';break;
			case 'djtj':myCata2 = '党建团建';break;
			case 'hltd':myCata2 = '护理天地';break;
			case 'qnwmh':myCata2 = '青年文明号';break;
			case 'yyqk':myCata2 = '医院期刊';break;
			case 'jkdjt':myCata2 = '健康大讲堂';break;
			case 'kfsl':myCata2 = '康复沙龙';break;
		}
		switch (myCata3) {
			case 'xrld':myCata3 = '现任领导';break;
			case 'lrld':myCata3 = '历任领导';break;
			case 'zjcx':myCata3 = '专家查询';break;
			case 'jylc':myCata3 = '就医流程';break;
			case 'jyxz':myCata3 = '就医须知';break;
			case 'lyfb':myCata3 = '楼宇分布';break;
			case 'yygz':myCata3 = '医院规章';myCata2Href='/admin/main/news/newsList2.shtml?cata1=hzfwzx&cata2=yygz';break;
			case 'tsfw':myCata3 = '特色服务';myCata2Href='/admin/main/news/newsList2.shtml?cata1=hzfwzx&cata2=tsfw&colId=1306';break;
			case 'jtzn':myCata3 = '交通指南';break;
			case 'yygh':myCata3 = '预约挂号';break;
			case 'zxzxzx':myCata3 = '在线咨询中心';break;
			case 'hycx':myCata3 = '化验查询';break;
			case 'bssds':myCata3 = '博士生导师';break;	
			case 'sssds':myCata3 = '硕士生导师';break;	
			case '151':myCata3 = '151人才';break;	
			case '131':myCata3 = '131人才';break;	
			case 'nydjs':myCata3 = '南医大教授';break;	
			case 'nydfjs':myCata3 = '南医大副教授';break;	
			case 'zjsyxh':myCata3 = '浙江省医学会主委/副主委';break;	
			case 'hzsyxh':myCata3 = '杭州市医学会主委';break;	
		}
		$('.J_Cata1').html(myCata1);
		$('.J_Cata2').html(myCata2).attr('href',myCata2Href);	
		$('.J_Cata3').html(myCata3);	
	}
	
	//设置右侧导航
	var cata1 = $('.J_Cata1').html();
	var title;
	if(cata1 == '医院概览'){
		$('.titleType4').html('医院概览');
		title=" <li><a href='http://www.baidu.com'>集团导航</a></li>"+
                "<li><a href='/admin/main/overview/overview.shtml?cata1=yygl&cata2=yyjj'>医院简介</a></li>"+
                "<li><a href='/admin/main/overview/structure.shtml?cata1=yygl&cata2=zzjg'>组织结构</a></li>"+
                "<li><a href='/admin/main/overview/dean.shtml?cata1=yygl&cata2=yzjy'>院长寄语</a></li>"+
                "<li><a href='/admin/main/overview/leaderNow.shtml?cata1=yygl&cata2=ldbz&cata3=xrld'>领导班子</a>"+
                    "<ul class='subMenu'>"+
                        "<li><a href='/admin/main/overview/leaderNow.shtml?cata1=yygl&cata2=ldbz&cata3=xrld'>现任领导</a></li>"+
                        "<li><a href='/admin/main/overview/leaderPast.shtml?cata1=yygl&cata2=ldbz&cata3=lrld'>历任领导</a></li>"+
                    "</ul>"+
                "</li>"+
                "<li><a href='/admin/main/news/newsList.shtml?cata1=yygl&cata2=ywgk&colId=1107'>院务公开</a></li>"+
                "<li><a href='/admin/main/overview/history.shtml?cata1=yygl&cata2=yyls'>医院历史</a></li>"+
                "<li><a href='/admin/main/news/newsList.shtml?cata1=yygl&cata2=zdjs&colId=1109'>重大记事</a></li>"+
                "<li><a href='/admin/main/overview/contact.shtml?cata1=yygl&cata2=lxwm'>联系我们</a></li>";
	}
	if(cata1 =="信息中心"){
		$('.titleType4').html('信息中心');
		title="<li><a href='/admin/main/news/newsList.shtml?cata1=xxzx&cata2=yygg&colId=1201'>医院公告</a></li>"+
		"<li><a href='/admin/main/news/newsList.shtml?cata1=xxzx&cata2=yyxw&colId=1202'>医院新闻</a></li>"+
		"<li><a href='/admin/main/news/newsList.shtml?cata1=xxzx&cata2=jtdt&colId=1203'>集团动态</a></li>"+
		"<li><a href='/admin/main/news/mediaCenter.shtml?cata1=xxzx&cata2=dmtzx'>多媒体中心</a></li>"+
		"<li><a href='/admin/main/news/newsList.shtml?cata1=xxzx&cata2=rczp&colId=1205'>人才招聘</a></li>"+
		"<li><a href='/admin/main/news/newsList.shtml?cata1=xxzx&cata2=zbcg&colId=1206'>招标采购</a></li>"+
		"<li><a href='/admin/main/news/download.shtml?cata1=xxzx&cata2=xzzx'>下载中心</a></li>";
	}
	if(cata1 =="患者服务中心"){
		$('.titleType4').html('患者服务中心');
		title="<li><a href='/admin/main/patient/doctorSearch.shtml?cata1=hzfwzx&cata2=mzcx&cata3=zjcx'>门诊查询</a>"+
			        "<ul class='subMenu'>"+
			        "<li><a href='/admin/main/patient/doctorSearch.shtml?cata1=hzfwzx&cata2=mzcx&cata3=zjcx'>专家查询</a></li>"+
			    "</ul>"+
			"</li>"+
			"<li><a href='/admin/main/patient/process.shtml?cata1=hzfwzx&cata2=jzzn&cata3=jylc'>就诊指南</a>"+
			    "<ul class='subMenu'>"+
			        "<li><a href='/admin/main/patient/process.shtml?cata1=hzfwzx&cata2=jzzn&cata3=jylc'>就医流程</a></li>"+
			        "<li><a href='/admin/main/patient/attention.shtml?cata1=hzfwzx&cata2=jzzn&cata3=jyxz'>就医须知</a></li>"+
			        "<li><a href='/admin/main/patient/building.shtml?cata1=hzfwzx&cata2=jzzn&cata3=lyfb'>楼宇分布</a></li>"+
			        "<li><a href='/admin/main/news/newsList2.shtml?cata1=hzfwzx&cata2=jzzn&cata3=yygz&colId=1305'>医院规章</a></li>"+                       
			        "<li><a href='/admin/main/news/newsList2.shtml?cata1=hzfwzx&cata2=jzzn&cata3=tsfw&colId=1306'>特色服务</a></li>"+
			        "<li><a href='/admin/main/patient/add.shtml?cata1=hzfwzx&cata2=jzzn&cata3=jtzn'>交通指南</a></li>"+
			    "</ul>"+
			"</li>"+
			"<li><a href='/admin/main/patient/order.shtml?cata1=hzfwzx&cata2=qtfw&cata3=yygh'>其它服务</a>"+
			    "<ul class='subMenu'>"+
			        "<li><a href='/admin/main/patient/order.shtml?cata1=hzfwzx&cata2=qtfw&cata3=yygh'>预约挂号</a></li>"+
			        "<li><a href='/admin/main/patient/consultIndex.shtml?cata1=hzfwzx&cata2=qtfw&cata3=zxzxzx'>在线咨询中心</a></li>"+
			        "<li><a href='/admin/main/patient/assay.shtml?cata1=hzfwzx&cata2=qtfw&cata3=hycx'>化验查询</a></li>"+
			    "</ul>"+
			"</li>";
	}
	if(cata1 =="科室导航"){
		$('.titleType4').html('科室导航');
		title="<li><a href='/admin/main/dept/deptList.shtml?cata1=ksdh&cata2=lcks'>临床科室</a></li>"+
		"<li><a href='/admin/main/dept/deptList.shtml?cata1=ksdh&cata2=sszd'>省市重点</a></li>"+
		"<li><a href='/admin/main/dept/deptList.shtml?cata1=ksdh&cata2=tszk'>特色专科</a></li>"+
		"<li><a href='/admin/main/dept/deptList.shtml?cata1=ksdh&cata2=yjks'>医技科室</a></li>"+
		"<li><a href='/admin/main/dept/deptList.shtml?cata1=ksdh&cata2=znks'>职能科室</a></li>";
	}
	if(cata1 =="专家介绍"){
		$('.titleType4').html('专家介绍');
		title="	<li><a href='../doctor/zjyl.shtml?cata1=zjjs&cata2=zjyl'>专家一览</a></li>"+
				"<li><a href='../doctor/mymz.shtml?cata1=zjjs&cata2=mymz'>名医门诊</a></li>"+
				"<li><a href='../doctor/gjrc.shtml?cata1=zjjs&cata2=gjrc&cata3=bssds'>各级人才</a>"+
					"<ul class='subMenu'>"+
						"<li><a href='../doctor/gjrc.shtml?cata1=zjjs&cata2=gjrc&cata3=bssds'>博士生导师</a></li>"+
						"<li><a href='../doctor/gjrc.shtml?cata1=zjjs&cata2=gjrc&cata3=sssds'>硕士生导师</a></li>"+
						"<li><a href='../doctor/gjrc.shtml?cata1=zjjs&cata2=gjrc&cata3=151'>151人才</a></li>"+
						"<li><a href='../doctor/gjrc.shtml?cata1=zjjs&cata2=gjrc&cata3=131'>131人才</a></li>"+
						"<li><a href='../doctor/gjrc.shtml?cata1=zjjs&cata2=gjrc&cata3=nydjs'>南医大教授</a></li>"+
						"<li><a href='../doctor/gjrc.shtml?cata1=zjjs&cata2=gjrc&cata3=nydfjs'>南医大副教授</a></li>"+
						"<li><a href='../doctor/gjrc.shtml?cata1=zjjs&cata2=gjrc&cata3=zjsyxh'>浙江省医学会主委/副主委</a></li>"+
						"<li><a href='../doctor/gjrc.shtml?cata1=zjjs&cata2=gjrc&cata3=hzsyxh'>杭州市医学会主委</a></li>"+
					"</ul></li>"+
				"<li><a href='../doctor/mzpb.shtml?cata1=zjjs&cata2=mzpb'>门诊排班信息</a></li>";		
	}
	if(cata1 =="科研学术"){
		$('.titleType4').html('科研学术');
		title="<li><a href='/admin/main/news/newsList.shtml?cata1=kyxs&cata2=kydt&colId=1601'>科研动态</a></li>"+
		"<li><a href='/admin/main/news/newsList.shtml?cata1=kyxs&cata2=kjcg&colId=1602'>科技成果</a></li>"+
		"<li><a href='/admin/main/news/newsList.shtml?cata1=kyxs&cata2=gxsb&colId=1603'>高新设备</a></li>"+
		"<li><a href='/admin/main/news/newsList.shtml?cata1=kyxs&cata2=xsjl&colId=1604'>学术交流</a></li>";		
	}
	if(cata1 =="医院文化和教育"){
		$('.titleType4').html('医院文化和教育');
		title="<li><a href='/admin/main/news/newsList.shtml?cata1=yywh&cata2=djtj&colId=1701'>党建团建</a></li>"+
		       "<li><a href='/admin/volunteer/index.shtml' target='_blank'>志愿者之家</a></li>"+
			   "<li><a href='/admin/main/news/newsList.shtml?cata1=yywh&cata2=hltd&colId=1703'>护理天地</a></li>"+
			   "<li><a href='/admin/main/news/newsList.shtml?cata1=yywh&cata2=qnwmh&colId=1704'>青年文明号</a></li>"+
			   "<li><a href='/admin/main/culture/periodical.shtml?cata1=yywh&cata2=yyqk'>医苑期刊</a></li>"+
			   "<li><a href='/admin/main/news/newsList.shtml?cata1=yywh&cata2=jkdjt&colId=1706'>健康大讲堂</a></li>"+
			   "<li><a href='/admin/main/news/newsList.shtml?cata1=yywh&cata2=kfsl&colId=1707'>康复沙龙</a></li>";		
	}
	
	$('#J_TitleLi').html(title);
	
	//右侧导航当前项定位	
	$('#J_TitleLi li a').each(function(){
		var myCata2 = GetParameter('cata2');
		var myCata3 = GetParameter('cata3');
		var isCata3=false;//是否为3级目录
		if($(this).parent().parent().hasClass('subMenu')){
			isCata3=true;
		}
		if(myCata3 && isCata3){			
			if(getParaStr($(this).attr('href'),'cata3')==myCata3){
				$(this).addClass('now');					
			}		
		}
		if(myCata2 && !isCata3){				
			if(getParaStr($(this).attr('href'),'cata2')==myCata2){
				$(this).addClass('now');				
			}	
		}
	});
});


//zq:链接指定参数获取
function GetParameter(param) {
	var query = window.location.search;
	var iLen = param.length;
	var iStart = query.indexOf(param);
	if (iStart == -1) {
		return "";
	}
	// 取得开始搜索的位置。
	iStart += iLen + 1;
	var iEnd = query.indexOf("&", iStart);
	// 如果只有一个参数传进来
	if (iEnd == -1) {
		return query.substring(iStart);
	} else {
		return query.substring(iStart, iEnd);
	}
}

//zq:普通字符串指定参数获取
function getParaStr(str,param){
	var query = str;
	var iLen = param.length;
	var iStart = query.indexOf(param);
	if (iStart == -1) {
		return "";
	}
	// 取得开始搜索的位置。
	iStart += iLen + 1;
	var iEnd = query.indexOf("&", iStart);
	// 如果只有一个参数传进来
	if (iEnd == -1) {
		return query.substring(iStart);
	} else {
		return query.substring(iStart, iEnd);
	}
}

// 替换%为#$字符
function fixP(str) {
	// add replace all function
	var reg = new RegExp("%", "g"); // g means replace all
	str = str.replace(reg, 'zq');
	return str;
}
// 替换#$为%字符
function reP(str) {
	var reg = new RegExp("zq", "g");
	str = str.replace(reg, '%');
	return str;
}

// 修复textarea编辑文本的格式问题
function fixTAFormat(str) {
	var reg = new RegExp("\n", "g"); // g means replace all
	str = str.replace(reg, '</p><p>');
	return str;
}

$(document).ready(function() {
	if ($('.rightCol2').css('height')) {
		var bodyHeight = $('.rightCol2').css('height').replace('px', '')-120;
		$('.docBody').css('minHeight', bodyHeight + 'px');
		$('.newsBody').css('minHeight', bodyHeight + 'px');
	}
});

// 自定义StringBuffer类
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
function getSpaceValue(size) {// 计算文件或目录占用空间大小
	var value;
	if (size < 1024) {
		value = size + "B";
	} else if (size < 1024 * 1024) {
		value = (size / 1024).toFixed(2) + "KB";
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
			timeOut : '3000',
			type : 'success',
			content : "操作成功！",
			autoClose : true,
			callBack : null
		};
		var options = $.extend(defaultSettings, settings);
		var eventScheduler = new EventScheduler();
		var info = new $.Info(options);
		if (info) {
			info.show();
			if (options.autoClose) {
				eventScheduler.set(function() {
					info.hide();
					eventScheduler.clear();
					if (defaultSettings.callBack != null) {
						defaultSettings.callBack();
					}
				}, options.timeOut);
			}
		}
	};

	/* constructor for sdTip */
	$.Info = function(options) {
		this._settings = $.extend(true, {}, $.Info.defaults, options);
		this.init();
	};

	$
			.extend(
					$.Info,
					{
						defaults : {
							// 校正用户输入的tip颜色，若未输入或输入错误，则使用系统默认颜色
							supportedTypes : [ 'success', 'fail', 'warn',
									'loading' ],
							fixType : function(obj) {
								var hasType = false;
								var supportedTypes = $.Info.defaults.supportedTypes;
								for ( var i = 0; i < supportedTypes.length; i++) {
									if (obj._settings.type == supportedTypes[i]) {
										hasType = true;
									}
								}
								obj._settings.type = hasType ? obj._settings.type
										: supportedTypes[0];
							}
						},
						prototype : {
							/* init the info */
							init : function() {
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
								return this.info
										|| (this.info = $("<div id='q_Msgbox' class='sd_msgbox_layer_wrap none'>"
												+ "<span id='mode_tips_v2' class='sd_msgbox_layer' style='z-index: 10000;'>"
												+ iconString
												+ this._settings.content
												+ " <span class='gtl_end'></span> "
												+ "</span>" + "</div>"));
							},

							/* show tip */
							show : function() {
								$('body').append(this.info);
								this.info.show();
							},

							/* hide tip */
							hide : function() {
								this.info.hide();
								$('#q_Msgbox').remove();

							}
						}
					});

	/* 定时器类 */
	function EventScheduler() {
	}

	EventScheduler.prototype = {
		set : function(func, timeout) {
			this.timer = setTimeout(func, timeout);
		},
		clear : function() {
			clearTimeout(this.timer);
		}
	}
})(jQuery);

// =========================================================
// zq:日期格式化
// =========================================================
function DateFormat() {

}
DateFormat.prototype = {
	_getHour : function(date) {
		return date.getHours();
	},
	_getMin : function(date) {
		return date.getMinutes();
	},
	_getSec : function(date) {
		return date.getSeconds();
	},
	_getDay : function(date) {
		return date.getDate();
	},
	_getMonth : function(date) {
		return date.getMonth() + 1;
	},
	_getYear : function(date) {
		return date.getFullYear();
	},
	isoTime : function(date) {
		// return type:12:12:12
		var myDate = "";
		this._getHour(date) < 10 ? myDate = "0" + this._getHour(date) + ":"
				: myDate = this._getHour(date) + ":";
		this._getMin(date) < 10 ? myDate = myDate + "0" + this._getMin(date)
				+ ":" : myDate = myDate + this._getMin(date) + ":";
		this._getSec(date) < 10 ? myDate = myDate + "0" + this._getSec(date)
				: myDate = myDate + this._getSec(date);
		return myDate;
	},
	isoDate : function(date) {
		// return type:2011-04-05
		var myDate = this._getYear(date) + "-";
		this._getMonth(date) < 10 ? myDate = myDate + "0"
				+ this._getMonth(date) + "-" : myDate = myDate
				+ this._getMonth(date) + "-";
		this._getDay(date) < 10 ? myDate = myDate + "0" + this._getDay(date)
				: myDate = myDate + this._getDay(date);
		return myDate;
	},
	isoDateTime : function(date) {
		// return type:2011-09-09 12:12:12
		var myDate = "";
		myDate = this.isoDate(date) + " " + this.isoTime(date);
		return myDate;
	}
}
