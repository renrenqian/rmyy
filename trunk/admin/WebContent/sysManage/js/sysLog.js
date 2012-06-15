var table;
//var leftDocument = $(window.parent.frames["leftFrame"].document);
$(document).ready(function(){
    intiTable();
});
function intiTable(){
    if(!table){
        table = $("#SysLogList").dataTable(
        {
                    bProcessing: false,
                    bServerSide:true,//设置服务端分页
                    bDestory:false,
                    sAjaxSource: "../system/listOperationLog.action",
                    sAjaxDataProp: "page.dataList",
                    oSearch: {"sSearch": ""},
                    bAutoWidth:false,
                    fnServerData:function(sSource, aoData, fnCallback){
                    //iDisplayStart,iDisplayLength    
                    var params = [];
                    var iDisplayStart,iDisplayLength,sEcho,sSearch;
                    for(var i = 0; i< aoData.length;i++){
                        if(aoData[i].name == "iDisplayStart"){
                            iDisplayStart = aoData[i].value;
                        }
                        if(aoData[i].name == "iDisplayLength"){
                            iDisplayLength = aoData[i].value;
                        }
                        if(aoData[i].name == "sEcho"){
                            sEcho = aoData[i].value;
                        }
                        if(aoData[i].name == "sSearch"){
                            sSearch = aoData[i].value;
                            
                            
                        }
                    }
                    params.push( { "name": "page.pageSize", "value": iDisplayLength } );
                    //aoData.push( { "name": "page.pageSize", "value": iDisplayLength } );
                    var currentPageNo = Math.floor(iDisplayStart/iDisplayLength) + 1;
                    //aoData.push( { "name": "page.currentPageNo", "value": currentPageNo } );
                    params.push( { "name": "page.currentPageNo", "value": currentPageNo } );
                    if(sSearch!=null && sSearch!=""){
                        params.push( { "name": "log.userName", "value": sSearch } );
                    }
                    aoData = params;
                    $.ajax({
                        dataType: 'json', 
                        type: "POST", 
                        url: sSource, 
                        data: aoData, 
                        success: function(json){
                           if(json.resultCode < 0){
                              // sendMessage(leftDocument,json.message?json.message:"查询日志列表失败",-1);
                           }
                            //判断是否有权访问
//                            "sEcho": 页面发来的参数，原样返回, 
//                            "iTotalRecords": 过滤前总记录数, 
//                            "iTotalDisplayRecords": 过滤后总记录数，我没有使用过滤，不太清楚和iTotalRecords的区别, 
//                            "aaData": 包含数据的2维数组 
                        json.sEcho = sEcho;
                        json.iTotalRecords = json.page.totalItemCount;
                        json.iTotalDisplayRecords = json.page.totalItemCount;    
                            fnCallback(json);
                        }
                        });
                    },
                    aoColumns: [
                        
                        {bSortable: false, mDataProp: "id"},
                        { //mDataProp: "time",
                          bSortable: false,
                          fnRender:function(obj){
                            var time = obj.aData.time;
                            if(time){
                                if(time.length == 14){
                                    return time.substring(0,4)+"-"+time.substring(4,6)+"-"+time.substring(6,8)+" "+time.substring(8,10)+":"+time.substring(10,12)+":"+time.substring(12,14);
                                }else{
                                    return time;
                                }
                            }else{
                                return "";
                            }
                          }
                        },
                        {bSortable: false, mDataProp: "userName",sDefaultContent:"未知用户" },
                         /* { bSortable: false,
                            fnRender:function(obj){
                            if(obj.aData.userType == 0){
                                return "用户";
                            }else{
                                return "客户";
                            }
                        }},*/
                        {bSortable: false, mDataProp: "ip" ,sDefaultContent:"未知IP"},
                        {   bSortable: false,
                            fnRender:function(obj){
                            if(obj.aData.action){
                                //return obj.aData.action;
                                return "<span class='textAlign_left textLength120'>"+obj.aData.action+"</span>";
                            }else{
                                //return "未知操作";
                                return "<span class='textAlign_left textLength120'>未知操作</span>";

                            }
                        } 
                        },
                        
                        { 
                            bSortable: false,
                            fnRender:function(obj){
                                if(obj.aData.state==1){
                                    return "<span class='green'>成功</span>";
                                }else{
                                    return "<span class='red'>失败</span>";
                                }
                            } 
                        }
                    ],
                  sPaginationType: "full_numbers"
        });
        //var setting = table.fnSettings();
        //alert(table.fnSettings()._iDisplayLength);
    }else{
        //重新获取数据
        
        //var setting = table.fnSettings();
        //setting._iDisplayLength;
        //setting._iDisplayStart;
        // tableId_filter
        //var sSearch = $("#myTable_filter").children().eq(0).children().eq(0).val();
        /*table.fnClearTable();
        $.ajax({
                dataType: 'json', 
                type: "GET", 
                url: '../system/listOperationLog.action', 
                success: function(json){
                    //判断是否有权访问
                table.fnAddData(json.page.dataList);
                table.fnDraw();
                }
            });*/
        
    }
    
}