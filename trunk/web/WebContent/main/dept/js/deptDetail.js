$(document).ready(function(){
	 $.getJSON('http://124.160.67.194:8520/admin/group/searchDept.action?dept.dpId=1032', function(json) {		 		 	
         if (json.resultCode > 0) {            
             var deptDetail = json.dept;
             $(deptDetail).each(function(i, item) {                  
             	$('.J_DpName').html(item.dpName);   
             	$('#J_DpIn_charge').html(item.dpIn_charge); 
             	$('#J_DpBed_counter').html(item.dpBed_counter); 
             	$('#J_DpLocation').html(item.dpLocation); 
             	$('#J_DpOd_telephone').html(item.dpOd_telephone); 
             	$('#J_DpEmail').html(item.dpEmail); 
             	               
             	$('#J_DpDesc').html("<p>"+fixTAFormat(reP(item.dpDesc))+"</p>");  
             	$('#J_DpAcademic_position').html("<p>"+fixTAFormat(reP(item.dpAcademic_position))+"</p>");   
             	$('#J_DpTech_adv').html("<p>"+fixTAFormat(reP(item.dpTech_adv))+"</p>");   
             	$('#J_DpResearch_direction').html("<p>"+fixTAFormat(reP(item.dpResearch_direction))+"</p>");                	           	         
             });     
         }
	 });
});