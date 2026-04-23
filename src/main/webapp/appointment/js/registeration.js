// refresh captcha by clicking refresh button
	/*$("#captcha_refresh").click(function() {
	
		var date = new Date();
		// get new image captcha, and set it as a source of captcha image
		// date.getTime() is added to make request different from previous requests
		// since requests are cached by browser, so captcha does not change if random 
		// parameters not added to request, in our case random parameter is time in milliseconds
		$("#captcha_image").attr("src","/tiles/images/jcaptcha.jpg?"+ date.getTime());
	});*/
	
	function go(){
	
	$("#captcha_image").attr("src","/hospitalmanagement/images/jcaptcha.jpg?");
	}
	
	
	$(document).ready(function(){
	var validator = $("#registerfrm").validate({
		onsubmit: true,
		rules : {
			userId : {
				required : true,
				//minlength : constraint.userId.minlength,
				nowhitespace : true,
				remote: 'CheckUserId'
			},	
			password : {
				required : true,
				minlength : constraint.password.minlength
			},
			confirmPassword : {
				required : true,
				equalTo : "#password"	// compare with password
			},
			name : {
				required : true,
				regex :/^[a-zA-Z ]+$/
			},
			pinCode : {
				integer : true,
				required : true
			},
			email : {
				required : true,
				email : true,
				//remote: '/UnlockJobs/user/CheckEmailId' // Action will be invoked to check if user with this email id already exist (Ajax call)
			},
			city : {
				selected : true
			},
			
			mobileNo : {
				required : true,
				mobilenumber : true
			},
			
			captchaCode : {
				required : true
			}	
			
				
		},
		messages : {	
			userId : {
				required : error.userId.required,
				minlength : jQuery.format(error.userId.minLength),	// format function replaces {0} with actual minimum length
				nowhitespace : error.userId.nowhitespace,
				remote: error.userId.alreadyExist
			},	
			password : {
				required : error.password.required,
				minlength : jQuery.format(error.password.minlength)
			},
			confirmPassword : {
				required : error.confirmPassword.required,
				minlength : jQuery.format(error.confirmPassword.minlength),	// format function replaces {0} with actual minimum length
				equalTo : error.confirmPassword.equalTo
			},
			name : {
				required : error.name.required,
				regex : error.name.alphabets
			},
			pinCode : {
				integer : error.pinCode.integer,
				required : error.pinCode.required
			},
			email : {
				required : error.email.required,
				email : error.email.email,
				remote: error.email.alreadyExist
			},
			city : {
				selected : error.city.selected
			},
			mobileNo : {
				required : error.mobileNo.required,
				mobilenumber : error.mobileNo.mobilenumber
			},
			
			captchaCode : {
				required : error.captchaCode.required
			}
			
		},
		
	});
	
	
});	