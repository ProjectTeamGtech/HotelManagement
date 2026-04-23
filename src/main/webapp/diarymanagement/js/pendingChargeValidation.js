$(document).ready(function(){
	
	
	var validator = $("#pendingCharge_form").validate({
		onsubmit: true,
		rules : {
			
			diaryUser :{ 
		 		selected : true
		 		
		 	},
          	
		 	location : { 
		 		selected : true
		 		
		 	},
		 	commencing : {
		 		required : true
		 	}
				
		},
		
		
		messages : {	
			diaryUser : {
				selected : error.diaryUser.selected
			},
			location : {
				selected : error.location.selected
			},
			
			commencing : {
				required : error.commencing.required
			},
		},
		
	});
	
	
	
	
});	
