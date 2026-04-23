$(document).ready(function(){
	
	var validator = $("#walkInPreBooked_frm").validate({
		onsubmit: true,
		rules : {
			diaryUser : {
				selected : true
				
			},	
			
			dept : {
				selected : true
			},
			
			date : {
              required: true
             
          	},
          	status : {
          		required : true
          	},
          	
 },
		
		
		messages : {	
			diaryUser : {
				selected : error.diaryUser.selected
			},	
			
			dept : {
				selected : error.dept.selected
			},	
			date : {
				required : error.date.required,
				
			},
			status: {
				required : error.status.required
			},
			
			
			
		},
		
	});
	
	
});	