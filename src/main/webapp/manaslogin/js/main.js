		function signup(){

        var username = $("#email").val();
        var password = $("#password-field").val().trim();
        var confirm_password = $("#password-field-2").val();
        var agree = $("input[name='test']:checked").val();
        var regx= /^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/;

        if(regx.test(username)){
            

        
        if(password.length<6){
            $("#password_error").css('color','#ff6711')
            $("#password-field").css('border', '1px solid #bd2130' );

        
            $("#password-field").addClass("run-animation");
            
        $("#password-field").keydown(function() {
            $("#password_error").css('color','#ff000000')
            $("#password-field").css('border', 'none' );
            $("#password-field").css('border-bottom', '1px solid #adb5bd');
            $("#password-field").removeClass("run-animation");
            });
        }
		else if (password != confirm_password)
        {
               $("#confirm_password_error").css('color','#ff6711')
			 $("#password-field-2").css('border', '1px solid #bd2130' );


            $("#password-field-2").addClass("run-animation");

        $("#password-field-2").keydown(function() {
            $("#confirm_password_error").css('color','#ff000000')
            $("#password-field-2").css('border', 'none' );
            $("#password-field-2").css('border-bottom', '1px solid #adb5bd');
            $("#password-field-2").removeClass("run-animation");
            });
		}
		else if (agree != "true"){
            $("#agree_error").css('color','#ff6711')
        }
		else{
			
        

        $.ajax({
            url: "/customer_save/",
            method: "POST",
            dataType: "json",
            data:{
                'username':username,
                'password':password,
                

            },
            success : function(loginobj){
                console.log(loginobj)
                if (loginobj['status'] == "error"){
                    $("#agree_error").css('color','#ff000000')
                    $('#error_login').css('color','#ff6711');

                }
                else{
                  
                  
					 location.href = "/login/"

                }

            }
        });
       
        }

    }
    else{
        $("#email_error").css('color','#ff6711')
        $("#email").css('border', '1px solid #bd2130' );
        $("#email").addClass("run-animation");
        $("#email").keydown(function() {
            $("#email_error").css('color','#ff000000')
            $("#email").css('border', 'none' );
            $("#email").css('border-bottom', '1px solid #adb5bd');
            $("#email").removeClass("run-animation");
            });
    }
 }



     function signin(){

            var username = $("#email").val();
            var password = $("#password-field").val();
            

            if(username == ""){
                $("#email_error").css('color','#ff6711')
                $("#email").css('border', '1px solid #bd2130' );
                $("#email").addClass("run-animation");
                $("#email").keydown(function() {
                 $("#email_error").css('color','#ff000000')
                $("#email").css('border', 'none' );
                $("#email").css('border-bottom', '1px solid #adb5bd');
                $("#email").removeClass("run-animation");
                });
            }
            
           else if(password == ""){
                $("#password_error").css('color','#ff6711')
                $("#password-field").css('border', '1px solid #bd2130' );


                $("#password-field").addClass("run-animation");

                 $("#password-field").keydown(function() {
                 $("#password_error").css('color','#ff000000')
                $("#password-field").css('border', 'none' );
                $("#password-field").css('border-bottom', '1px solid #adb5bd');
                $("#password-field").removeClass("run-animation");
                });
            }
          
        else{
                
            
    
            $.ajax({
                url: "/verify/",
                method: "POST",
                dataType: "json",
                data:{
                    'username':username,
                    'password':password,
    
                },
                success : function(loginobj){
                    console.log(loginobj)
                    if (loginobj['status'] == "error"){
                        $("#error_login").css('color','#ff6711');
                    }
                    else{
                      
                         location.href = "/in_progress_2/"
    
                    }
    
                }
            });
           
            }
    
        }

    


// .................................password-validation...............


// ..........................end......................
 function login(){

            var username = $("#email").val();
            var password = $("#password-field").val();


            if(username == ""){
                $("#email_error").show();
            }

           else if(password == ""){
                $("#password_error").show();
            }

        else{


            $.ajax({
                url: "/upload_dashboard/",
                method: "POST",
                dataType: "json",
                data:{
                    'username':username,
                    'password':password,

                },
                success : function(loginobj){
                    console.log(loginobj)
                    if (loginobj['status'] == "error"){
                        location.href = "/admin_login/"
                    }
                    else{

                         location.href = "/admin_dashboard/"

                    }

                }
            });

}

        }
// ..................................

function contact(){

            var fullname = $("#fullname").val();
            var email = $("#email").val();
            var number = $("#number").val();
            var message = $("#message").val();
            var regx= /^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/;



           if(fullname == ""){
                $("#name_error").css('color','#ff6711')
                $("#fullname").css('border', '1px solid #ff6711' );
                $("#fullname").addClass("run-animation");
                $("#fullname").keydown(function() {
                 $("#name_error").css('color','#ff000000')
                $("#fullname").css('border', 'none' );
                $("#fullname").css('border-bottom', '1px solid #adb5bd');
                $("#fullname").removeClass("run-animation");
                });
            }

           else if(regx.test(email)){

             if (message == "" ){

                $("#message").css('border', '1px solid #ff6711' );
                $("#message").addClass("run-animation");
                $("#message").keydown(function() {

                $("#message").css('border', '1px solid #ced4da' );
                $("#message").removeClass("run-animation");
                });

             }



             else{


                        $.ajax({
                            url: "/contact_save/",
                            method: "POST",
                            dataType: "json",
                            data:{
                                'fullname':fullname,
                                'email':email,
                                'number':number,
                                'message':message,
                            },
                            success : function(loginobj){
                                console.log(loginobj)
                                if (loginobj['status'] == "error"){
                                    location.href = "/contact_us/"
                                }
                                else{

                                     location.href = "/contact_us/"

                                }

                            }
                        });

                }

        }
      else{
      $("#email_error").css('color','#ff6711')
                $("#email").css('border', '1px solid #ff6711' );
                $("#email").addClass("run-animation");
                $("#email").keydown(function() {
                 $("#email_error").css('color','#ff000000')
                $("#email").css('border', 'none' );
                $("#email").css('border-bottom', '1px solid #adb5bd');
                $("#email").removeClass("run-animation");
                });
      }
   }
 //.....................................