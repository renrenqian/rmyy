$(document).ready(function(){
	 $.getJSON('http://124.160.67.194:8520/admin/group/searchDept.action?t=1340243950475&dept.dpId=1025&_=1340243950478', function(json) {
         if (json.resultCode > 0) {            
//             var deptList = json.dept;
//             $(deptList).each(function(i, item) {                  
//             	$('#deptName').val(item.dpName);    
//             	$('#dpIn_charge').val(item.dpIn_charge);   
//             	$('#dpOd_telephone').val(item.dpOd_telephone);   
//             	$('#dpEmail').val(item.dpEmail);   
//             	$('#dpSite').val(item.dpSite);   
//             	$('#dpLocation').val(item.dpLocation);   
//             	$('#dpBed_counter').val(item.dpBed_counter);   
//             	$('#dpNote').val(item.dpNote);   
//             	$('#dpDesc').val(item.dpDesc);   
//             	$('#dpAcademic_position').val(item.dpAcademic_position);   
//             	$('#dpTech_adv').val(item.dpTech_adv);   
//             	$('#dpResearch_direction').val(item.dpResearch_direction);
//             	
//             	var dpTypeArray=item.dpType.split(',');
//             	for(var i=0;i<dpTypeArray.length;i++){
//             		$(".J_DeptType[value='"+dpTypeArray[i]+"']").attr("checked", true);
//             	}
//             });     
        	 console.log('1');
         } else {
//             $.fn.sdInfo({
//                 type:"fail",
//                 content:json.message ? json.message : "查询科室信息错误!"
//             });
        	 console.log('0');
         }
	 });
});