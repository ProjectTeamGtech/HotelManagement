$(document).ready(function(){
	var validator = $("#category_form").validate({
		onsubmit: true,
		rules : {
			
				
			categoryName : {
				required : true,
				remote: 'CheckCategoryName'
				
				},
				
		  /* description : {
		   		required : true
				}*/
		 
		
		
		
		},
		messages : {
				
				
				categoryName : {
						required : error.categoryName.required,
						remote : error.categoryName.alreadyExist
					  },
		
		      /*  description : {
						required : error.description.required
					  }*/
		
		
		
		
		
		},
		
	});
	
	
});	