function onUploadImgChange(sender){        
    if( !sender.value.match( /.jpg|.gif|.png|.bmp/i ) ){        
        alert('图片格式无效！');        
        return false;        
    }        
            
    var objPreview = document.getElementById( 'preview' );        
    var objPreviewFake = document.getElementById( 'preview_fake' );        
    var objPreviewSizeFake = document.getElementById( 'preview_size_fake' );
    
            
    if( sender.files &&  sender.files[0] ){        
        objPreview.style.display = 'block';        
        objPreview.style.width = 'auto';        
        objPreview.style.height = 'auto';        
                
        // Firefox 因安全性问题已无法直接通过 input[file].value 获取完整的文件路径          
		objPreview.src = window.URL.createObjectURL(sender.files[0]);  
    }else if( objPreviewFake.filters ){         
        // IE7,IE8 在设置本地图片地址为 img.src 时出现莫名其妙的后果        
        //（相同环境有时能显示，有时不显示），因此只能用滤镜来解决        
                
        // IE7, IE8因安全性问题已无法直接通过 input[file].value 获取完整的文件路径        
        sender.select();        
        var imgSrc = document.selection.createRange().text;        
                
        objPreviewFake.filters.item(        
            'DXImageTransform.Microsoft.AlphaImageLoader').src = imgSrc;        
        objPreviewSizeFake.filters.item(        
            'DXImageTransform.Microsoft.AlphaImageLoader').src = imgSrc;        
                
        autoSizePreview( objPreviewFake,         
            objPreviewSizeFake.offsetWidth, objPreviewSizeFake.offsetHeight );        
        objPreview.style.display = 'none';        
    }        
}        
       
function onPreviewLoad(sender){
    autoSizePreview( sender, sender.offsetWidth, sender.offsetHeight );        
}
       
function autoSizePreview( objPre, originalWidth, originalHeight ){     
   // var zoomParam = clacImgZoomParam( 120, 180, originalWidth, originalHeight );      
	var zoomParam = clacImgZoomParam( 120, 180, 120, 180 );      
    objPre.style.width = zoomParam.width + 'px';        
    objPre.style.height = zoomParam.height + 'px';        
    objPre.style.marginTop = zoomParam.top + 'px';        
    objPre.style.marginLeft = zoomParam.left + 'px';        
}        
       
function clacImgZoomParam( maxWidth, maxHeight, width, height ){        
    var param = { width:width, height:height, top:0, left:0 };        
            
    if( width>maxWidth || height>maxHeight ){        
        rateWidth = width / maxWidth;        
        rateHeight = height / maxHeight;        
                
        if( rateWidth > rateHeight ){        
            param.width =  maxWidth;        
            param.height = height / rateWidth;        
        }else{        
            param.width = width / rateHeight;        
            param.height = maxHeight;        
        }        
    }        
            
    param.left = (maxWidth - param.width) / 2;        
    param.top = (maxHeight - param.height) / 2;        
            
    return param;        
}     