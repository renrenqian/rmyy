$(document).ready(function(){
	 $.getJSON('http://124.160.67.194:8520/admin/group/searchDept.action?t=1340268677471&dept.dpId=1032&_=1340268677472', function(json) {
         if (json.resultCode > 0) {            
             var deptDetail = json.dept;
             $(deptDetail).each(function(i, item) {                  
             	$('.J_DpName').html(item.dpName);   
             	$('#J_DpIn_charge').html(item.dpIn_charge); 
             	$('#J_DpBed_counter').html(item.dpBed_counter); 
             	$('#J_DpLocation').html(item.dpLocation); 
             	$('#J_DpOd_telephone').html(item.dpOd_telephone); 
             	$('#J_DpEmail').html(item.dpEmail); 
             	$('#J_DpDesc').html(item.dpDesc); 
             	$('#J_DpAcademic_position').html(item.dpAcademic_position); 
             	$('#J_DpTech_adv').html(item.dpTech_adv); 
             	$('#J_DpResearch_direction').html(item.dpResearch_direction); 
             	$('#J_DpIn_charge').html(item.dpIn_charge);              
             });     
         }
	 });
});