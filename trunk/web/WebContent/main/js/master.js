$(document).ready(function() {	
	var cata1 = $('.J_Cata1').html();
	var title;
	if(cata1 == '医院概览'){
		$('.titleType4').html('医院概览');
		title=" <li><a href='#'>集团导航</a></li>"+
                "<li><a href='/web/main/overview/overview.shtml'>医院简介</a></li>"+
                "<li><a href='/web/main/overview/structure.shtml'>组织结构</a></li>"+
                "<li><a href='/web/main/overview/dean.shtml'>院长寄语</a></li>"+
                "<li><a href='/web/main/overview/leaderNow.shtml'>领导班子</a>"+
                    "<ul class='subMenu'>"+
                        "<li><a href='/web/main/overview/leaderNow.shtml'>现任领导</a></li>"+
                        "<li><a href='/web/main/overview/leaderPast.shtml'>历任领导</a></li>"+
                    "</ul>"+
                "</li>"+
                "<li><a href='/web/main/news/newsList.shtml?cata1=yygl&cata2=ywgk'>院务公开</a></li>"+
                "<li><a href='/web/main/overview/history.shtml'>医院历史</a></li>"+
                "<li><a href='/web/main/news/newsList.shtml?cata1=yygl&cata2=zdjs'>重大记事</a></li>"+
                "<li><a href='/web/main/overview/contact.shtml'>联系我们</a></li>";
	}
	if(cata1 =="信息中心"){
		$('.titleType4').html('信息中心');
		title="<li><a href='/web/main/news/newsList.shtml?cata1=xxzx&cata2=yygg'>医院公告</a></li>"+
		"<li><a href='/web/main/news/newsList.shtml?cata1=xxzx&cata2=yyxw'>医院新闻</a></li>"+
		"<li><a href='/web/main/news/newsList.shtml?cata1=xxzx&cata2=jtdt'>集团动态</a></li>"+
		"<li><a href='/web/main/news/mediaCenter.shtml'>多媒体中心</a></li>"+
		"<li><a href='/web/main/news/newsList.shtml?cata1=xxzx&cata2=rczp'>人才招聘</a></li>"+
		"<li><a href='/web/main/news/newsList.shtml?cata1=xxzx&cata2=zbcg'>招标采购</a></li>";
	}
	if(cata1 =="患者服务中心"){
		$('.titleType4').html('医院规章');
		title="<li><a href='#'>门诊查询</a>"+
			        "<ul class='subMenu'>"+
			        "<li><a href='/web/main/patient/doctorSearch.shtml'>专家查询</a></li>"+
			    "</ul>"+
			"</li>"+
			"<li><a href='#'>就诊指南</a>"+
			    "<ul class='subMenu'>"+
			        "<li><a href='/web/main/patient/process.shtml'>就医流程</a></li>"+
			        "<li><a href='/web/main/patient/attention.shtml'>就医须知</a></li>"+
			        "<li><a href='/web/main/patient/building.shtml'>楼宇分布</a></li>"+
			        "<li><a href='/web/main/news/newsList.shtml?cata1=hzfwzx&cata2=yygz'>医院规章</a></li>"+                       
			        "<li><a href='/web/main/news/newsList.shtml?cata1=hzfwzx&cata2=tsfw'>特色服务</a></li>"+
			        "<li><a href='/web/main/patient/add.shtml'>交通指南</a></li>"+
			    "</ul>"+
			"</li>"+
			"<li><a href='Order.aspx'>其它服务</a>"+
			    "<ul class='subMenu'>"+
			        "<li><a href='/web/main/patient/order.shtml'>预约挂号</a></li>"+
			        "<li><a href='/web/main/patient/consultIndex.shtml'>在线咨询中心</a></li>"+
			        "<li><a href='/web/main/patient/assay.shtml'>化验查询</a></li>"+
			    "</ul>"+
			"</li>";
	}
	if(cata1 =="科研学术"){
		$('.titleType4').html('科研学术');
		title="<li><a href='/web/main/news/newsList.shtml?cata1=kyxs&cata2=kydt'>科研动态</a></li>"+
		"<li><a href='/web/main/news/newsList.shtml?cata1=kyxs&cata2=kjcg'>科技成果</a></li>"+
		"<li><a href='/web/main/news/newsList.shtml?cata1=kyxs&cata2=gxsb'>高新设备</a></li>"+
		"<li><a href='/web/main/news/newsList.shtml?cata1=kyxs&cata2=xsjl'>学术交流</a></li>";		
	}
	if(cata1 =="医院文化和教育"){
		$('.titleType4').html('医院文化和教育');
		title="<li><a href='/web/main/news/newsList.shtml?cata1=yywh&cata2=djtj'>党建团建</a></li>"+
			   "<li><a href='/web/main/news/newsList.shtml?cata1=yywh&cata2=hltd'>护理天地</a></li>"+
			   "<li><a href='/web/main/news/newsList.shtml?cata1=yywh&cata2=qnwmh'>青年文明号</a></li>"+
			   "<li><a href='/web/main/culture/periodical.shtml?cata1=yywh&cata2=yyqk'>医苑期刊</a></li>"+
			   "<li><a href='/web/main/news/newsList.shtml?cata1=yywh&cata2=jkdjt'>健康大讲堂</a></li>"+
			   "<li><a href='/web/main/news/newsList.shtml?cata1=yywh&cata2=kfsl'>康复沙龙</a></li>";		
	}
	
	$('#J_TitleLi').html(title);
});