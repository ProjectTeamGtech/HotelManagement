$(document).ready(function(){
	
	var validator = $("#thirdPartyType_form").validate({
		onsubmit: true,
		rules : {
			
			type :{ 
		 		required : true
		 		
		 	},
		 	
			
			
			
				
		},
		
		
		messages : {	
			
			type : {
				required : error.type.required
			},
			
			
		},
		
	});
	
	
});