$(document).ready(function(){
	
	var validator = $("#clientfrm").validate({
		onsubmit: true,
		rules : {
			title1 : {
				selected : true
			},
			firstName : {
          		required : true,
          		minlength: 2,
          		regex :/^[a-zA-Z ]+$/
          	},
          	gptypeName : {
          		selected : true
          	}
          	
          	
				
		},
		
		
		messages : {	
			title1 : {
				selected : "Please select title"
			},
			firstName : {
				required : "Please Enter First Name",
				minlength : "Min 2 Charector",
				regex : "Alphabeds only"
			},
			gptypeName : {
				selected : "Please Select GP"
			}
			
			
		},
		
	});
	
	
});	
