/*
Copyright (c) 2003-2011, CKSource - Frederico Knabben. All rights reserved.
For licensing, see LICENSE.html or http://ckfinder.com/license
*/

CKFinder.customConfig = function( config )
{
	// Define changes to default configuration here.
	// For the list of available options, check:
	// http://docs.cksource.com/ckfinder_2.x_api/symbols/CKFinder.config.html

	// Sample configuration options:
	// config.uiColor = '#BDE31E';
	// config.language = 'fr';
	// config.removePlugins = 'basket';
	// Define changes to default configuration here. For example:
    // config.language = 'fr';
    // config.uiColor = '#AADC6E';
    config.language = 'zh-cn'; // 配置语言

    config.uiColor = '#fff'; // 背景颜色

    config.width = '600px'; // 宽度

    config.height = '400px'; // 高度

    // lead the issue
    //config.skin = 'office2003';// 界面v2,kama,office2003 

    config.toolbar = 'Full';// 工具栏风格Full,Basic
    
    config.font_names='宋体/宋体;黑体/黑体;仿宋/仿宋_GB2312;楷体/楷体_GB2312;' +
    '隶书/隶书;幼圆/幼圆;微软雅黑/微软雅黑;'+ config.font_names;
    
    config.filebrowserBrowseUrl ='ckfinder/ckfinder.html';
    
    config.filebrowserImageBrowseUrl ='ckfinder/ckfinder.html?Type=Images';
    
    config.filebrowserFlashBrowseUrl = 'ckfinder/ckfinder.html?Type=Flash';
    
    config.filebrowserUploadUrl = 'ckfinder/core/connector/java/connector.java?command=QuickUpload&type=Files';
    
    config.filebrowserImageUploadUrl = 'ckfinder/core/connector/java/connector.java?command=QuickUpload&type=Images';
    
    config.filebrowserFlashUploadUrl = 'ckfinder/core/connector/java/connector.java?command=QuickUpload&type=Flash';
};
