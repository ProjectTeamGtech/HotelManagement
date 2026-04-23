$(document).ready(function(){
	
	var validator = $("#apmtDiaryReport_frm").validate({
		onsubmit: true,
		rules : {
			diaryUser : {
				selected : true
				
			},	
			
			fromDate : {
				required : true
			},
			
			toDate : {
              required: true
             
          	},
          	
          	
 },
		messages : {	
			diaryUser : {
				selected : error.diaryUser.selected
			},	
			fromDate : {
				required : error.fromDate.required
			},	
			toDate : {
				required : error.toDate.required
				
			},
			
			
			
			
		},
		
	});
	
	$( "#fromDate" ).datepicker({
		 
		 dateFormat:'dd/mm/yy',
		 yearRange: yearrange,
		 minDate : '30/12/1880',
		 changeMonth: true,
	     changeYear: true
	 });
	 $( "#toDate" ).datepicker({
		 
		 dateFormat:'dd/mm/yy',
		 yearRange: yearrange,
		 minDate : '30/12/1880',
		 changeMonth: true,
	     changeYear: true
	 });
	
	
});	