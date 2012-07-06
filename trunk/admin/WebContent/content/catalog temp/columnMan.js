var contentDataTable;
var g_colInfoNodes, g_selectedColInfo,g_flag=false,g_click_tree=false;;

var zTreeObj;
//zTree 的参数配置，深入使用请参考 API 文档（setting 配置详解）
var setting = {
 data:{
     simpleData:{
         enable:true,
         idKey: "id",
         pIdKey: "pId"
     }
 },
 view: {
     addHoverDom: null,
     removeHoverDom: removeHoverDom,
     selectedMulti: false
 },
 edit:{
     enable:false,
     showRenameBtn:false,
     removeTitle: "删除节点"
 },
 callback: {
     beforeRemove: beforeRemove,
     onRemove: onRemove,
     onRightClick: null,
     onClick:function(event, treeId, treeNode){
          for(var i=0;i<g_colInfoNodes.length;i++){
              if(g_colInfoNodes[i].ciId==treeNode.id){
                  g_selectedColInfo=g_colInfoNodes[i];
                  g_click_tree=true;
                  //initColumnInfo();//显示栏目内容，并且可修改
                  break;
              }
           }
     }
 }
};


$(document).ready(function() {
    initColumnTree(true);

});


function initColumnTree(flag){// zTree 的数据属性，深入使用请参考 API 文档（zTreeNode 节点数据详解）
    $.getJSON("../group/listColumn.action", function(data) {
        if (data.resultCode && data.resultCode > 0) {
            if(data.colList){
                g_colInfoNodes = data.colList;
                if(g_colInfoNodes){
                    var zNodes=[];
                    for(var i=0;i<g_colInfoNodes.length;i++){
                        zNodes.push({id:g_colInfoNodes[i].ciId,pId:g_colInfoNodes[i].ciParent,name:g_colInfoNodes[i].ciTital});
                    }
                    //if(!flag){
                        zTreeObj = $.fn.zTree.init($("#J_ColTree"), setting, zNodes);
                    //}
                }
            }
        } else {
            $.fn.sdInfo({type:"fail",content:data.message?data.message:"查询栏目失败"});
        }
    });
}

/* zTree edit go! */
function beforeRemove(treeId, treeNode) {
//    if(treeNode.pId == null){
//        $.fn.sdInfo({type:"fail",content:"根目录不允许删除"});
//        return false;
//    }
//     var parentNode = treeNode.getParentNode();
//     var isDelSuc = true;
//     $.ajax({
//            type: 'POST',
//            url: "../content/deleteDir.action",
//            data:"dir.diId="+treeNode.id,
//            async: false,
//            success: function(json, statusText, xhr) {
//                 if (json.resultCode > 0) {
//                     initDirInfoTree(true);
//                 } else {
//                     isDelSuc = false;
//                     $.fn.sdInfo({type:"fail",content:json.message ? json.message : "删除目录失败!"});
//                 }
//             },
//             dataType: 'json'
//     });
//     if(!isDelSuc){
//         return false;
//     }
//    return true;
}
function onRemove(e, treeId, treeNode) {
}
function beforeRename(treeId, treeNode, newName) {
}
function onRename(e, treeId, treeNode) {
}

//var newCount = 1;
function addHoverDom(treeId, treeNode) {
//    var sObj = $("#" + treeNode.tId + "_span");
//    if ($("#addBtn_" + treeNode.id).length > 0) return;
//    var addStr = "<button type='button' class='add' id='addBtn_" + treeNode.id
//            + "' title='新增目录' onfocus='this.blur();'></button><button type='button' class='edit' id='editBtn_" + treeNode.id
//            + "' title='编辑目录' onfocus='this.blur();'></button>";
//    sObj.append(addStr);
//    var addBtn = $("#addBtn_" + treeNode.id);
//    if (addBtn) {
//        addBtn.bind("click", function() {
//            $.fn.sdResetForm("#dirInfoForm");
//            popCatalogWin("add");
//            $("#diParent").val(treeNode.id);
//            //zTree.addNodes(treeNode, {id:(100 + newCount), pId:treeNode.id, name:"new node" + (newCount++)});
//        });
//    }
//    var editBtn=$("#editBtn_"+treeNode.id);
//    if(editBtn){
//        editBtn.bind("click",function(){
//            $.fn.sdResetForm("#dirInfoForm");
//            for(var i=0;i<g_colInfoNodes.length;i++){
//                if(g_colInfoNodes[i].diId==treeNode.id){
//                    formUnSerialize("dirInfoForm", "dir", g_colInfoNodes[i]);break;
//                }
//            }
//            popCatalogWin("edit");
//        });
//    }
}
function removeHoverDom(treeId, treeNode) {
//    $("#addBtn_" + treeNode.id).unbind().remove();
//    $("#editBtn_" + treeNode.id).unbind().remove();
}
function popCatalogWin(type) {
//    if(type=="edit"){
//       type="编辑目录";
//       $("#diFolder_edit").attr("readonly",true);    
//    }
//    if(type=="add"){
//        type="添加新目录"
//        $("#diFolder_edit").attr("readonly",false);    
//        $("#dirId_edit").val("");
//    }
//    $('#J_CatalogEditWin').css("display", "").window({
//        title: type,
//        modal: true,
//        minimizable:false,
//        maximizable:false,
//        collapsible:false,
//        shadow:false
//    });
}

function setEdit(flag) {
//    var zTree = $.fn.zTree.getZTreeObj("J_ColTree");
//    if (flag) {
//        zTree.setting.edit.enable = false;
//        zTree.setting.view.addHoverDom = null;
//    } else {
//        zTree.setting.edit.enable = true;
//        zTree.setting.view.addHoverDom = addHoverDom;
//    }
}


function initColumnInfo() {
//    if (!dataTable) {
//        dataTable = $("#contentList").dataTable({
//            bProcessing: false,
//            bServerSide:true,
//            bDestory:false,
//            bRetrieve:true,
//            sAjaxSource: "../content/listContent.action",
//            sAjaxDataProp: "page.dataList",
//            oSearch: {"sSearch": ""},
//            bAutoWidth:false,
//            fnServerData:function(sSource, aoData, fnCallback) {
//                var params = [];
//                var iDisplayStart,iDisplayLength,sEcho,sSearch;
//                for (var i = 0; i < aoData.length; i++) {
//                    if (aoData[i].name == "iDisplayStart") {
//                        iDisplayStart = aoData[i].value;
//                    }
//                    if (aoData[i].name == "iDisplayLength") {
//                        iDisplayLength = aoData[i].value;
//                    }
//                    if (aoData[i].name == "sEcho") {
//                        sEcho = aoData[i].value;
//                    }
//                    if (aoData[i].name == "sSearch") {
//                        sSearch = aoData[i].value;
//                    }
//                }
//                params.push({ "name": "page.pageSize", "value": iDisplayLength });
//                var currentPageNo = Math.floor(iDisplayStart / iDisplayLength) + 1;
//                params.push({ "name": "page.currentPageNo", "value": currentPageNo });
//                if(g_selectedColInfo){
//                	params.push({ "name": "cm.cmDiId", "value": g_selectedColInfo.diId});
//                }
//                if(sSearch!=null && sSearch!=""){
//                	params.push({ "name": "cm.cmName", "value": sSearch});
//                }else{
//                	if(g_click_tree){
//                		g_click_tree=false;
//                	}else{
//                		if (window.setSearchParams) {
//                			setSearchParams(params);
//                		}
//                	}
//                }
//                $.ajax({
//                    dataType: 'json',
//                    type: "POST",
//                    url: sSource,
//                    data: params,
//                    success: function(json) {
//                        if (json.resultCode > 0) {
//                        } else {
//                            $.fn.sdInfo({type:'fail', content:json.message ? json.message : "查询内容列表错误!"});
//                        }
//                        if(!json.page){//处理返回结果
//                        	json.page = {};
//                        }
//                        if (!json.page.dataList) {
//                            json.page.dataList = [];
//                        }
//                        json.sEcho = sEcho;
//                        json.iTotalRecords = json.page.totalItemCount;
//                        json.iTotalDisplayRecords = json.page.totalItemCount;
//                        fnCallback(json);
//                        $('#contentList input[type=checkbox]').sdCheckBox();
//                        setTableTrColor();
//                        $('.J_DelAll').attr('class', 'unit unabled J_DelAll');
//                        $.fn.checkTest('J_ContentListDiv');
//                    }
//                });
//            },
//            aoColumns: [
//                {
//                    fnRender:function(obj) {
//                        return "<input type='checkbox' value='" + obj.aData.cmId + "'/>";
//                    }
//                },
//                {
//                    fnRender:function(obj) {
//                        return "<span>" + obj.aData.cmId + "</span>";
//                    }
//                },
//                {
//                    fnRender:function(obj) {
//                        return "<span class='hidden2 tl'>" + obj.aData.cmName + "</span>";
//                    }
//                },
//                {
//                    fnRender:function(obj) {
//                        return "<span class='hidden2 tl'>" + obj.aData.di.diName + "</span>";
//                    }
//                },
//                {
//                    fnRender:function(obj) {
//                        return '<a class="detail J_ContentDetail"></a>';
//                    }
//                },
//                {
//                    fnRender:function(obj) {
//                        return "<a class='del J_ContentDel'></a>";
//                    }
//                }
//            ],
//            sPaginationType: "full_numbers",
//            aoColumnDefs: [
//                { "bSortable": false, "aTargets": [0,1,2,3,4,5]}
//            ]
//        });
//    } else {
//        dataTable.fnDraw();
//    }
}
