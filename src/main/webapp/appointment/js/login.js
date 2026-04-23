
$(document).ready(function() {   

  

    var validator =  $("#login_form").validate({                                                
	  rules : {				
		userId : {
			required : true,
			minlength : 6,
			alphanumeric : true,
			nowhitespace : true,
			firstcharalpha : true,
			regex : /^[a-zA-Z0-9]([a-zA-Z0-9][_a-zA-Z0-9]?){0,}$/
		},
		password : {
			required : true,
			minlength : 6
		}
    },
    messages : {			
		userId : {
			required : error.login.userId.required,
			minlength : jQuery.format(error.login.userId.minLength),	// format function replaces {0} with actual minimum length
			alphanumeric : error.login.userId.alphanumeric,
			nowhitespace : error.login.userId.nowhitespace,
			firstcharalpha : error.login.userId.firstcharalpha,
			regex : error.login.userId.doubleunderscore
		},
		password : {
			required : error.login.password.required,
			minlength : jQuery.format(error.login.password.minLength)		// format function replaces {0} with actual minimum length
		}
    }
   
  }); 

  $("#login_submit input[type='reset']").click(function() {
	  validator.resetForm();
	   $(this).removeClass("wwctrl_login_form_label_reset").addClass("submit_button");  
  });
  
   $("#login_submit input[type='submit']").click(function() {
	  validator.resetForm();
	   $(this).removeClass("wwctrl_login_form_label_reset").addClass("submit_button");  
  });
 
  
  $("#userId_txt").change(function(){
	  $("#server_side_error").html("");
  });
  $("#password_txt").change(function(){
	  $("#server_side_error").html("");
  });
  
});   








