<%@page import="com.apm.main.common.constants.Constants"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="s" uri="/struts-tags"%>
<html lang="en">
    <head>
        <title> Manas YuviCare</title> 
        <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
   
  <link rel="shortcut icon" href=" manaslogin/images/Manas_Yuvicare_Circle Favicon.png">
  <link rel="stylesheet" href="manaslogin/css/bootstrap.min.css">
  <link rel="stylesheet" href="manaslogin/css/styles.css">
  <link rel="stylesheet" href="manaslogin/css/fontawesome-all.css">
  <link href="manaslogin/css/aos.css" rel="stylesheet">
  <link href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet" integrity="sha384-wvfXpqpZZVQGK6TAh5PVlGOfQNHSoD2xbE+QkPxCAFlNEevoEH3Sl0sibVcOQVnN" crossorigin="anonymous">
  <link href="https://fonts.googleapis.com/css2?family=Open+Sans:wght@300;400;600;700&display=swap" rel="stylesheet">

    </head>
    <style>
li {
    display: inline;
    font-size: 10px;
    padding: 0px 30px;
    color: #15536E;
}
body {
	overflow: hidden;
}
@media only screen and (max-width:600px){
  li{
    padding: 0px 12px;
}body {
	overflow: visible;
}
}
@media only screen and (max-width:320px){
  li{
    padding: 0px 7px;
  }
  body {
	overflow: visible;
}
}
::-webkit-input-placeholder { /* Edge */
  font-size: 10px;
}
:-ms-input-placeholder { /* Internet Explorer 10-11 */
  font-size: 10px;
}
::placeholder {
  font-size: 10px;
}
input:focus::-webkit-input-placeholder {
    opacity: 0;
}
input, select, textarea{
  color: #ff0000;
}

textarea:focus, input:focus {
  color: #ff0000;
}
span{
font-size: medium;
    color: red;
}

    </style>
 
    <body>
    
    <form action="Login" id="login_form" method="post" class="mt-20">
           <div class="container-fluid " style="margin-top: -734px">
                <div class="row pd-header" >
                    <div class="col-lg-7 col-md-7 col-12 " >
                      <div class="col" style="padding-top: 50px" >
                         <img src="manaslogin/images/Manas_Logo.svg" >
                      </div>
                     
                      <div class="col txt-left" style="padding-top: 40px">
                          <h2>Welcome To Manas YuviCare Hotel Information Management System<br>
                          <span style="font-weight: 600;color:black"> A Hotel Information Management System With All Management Functionality</span></h2>    
                      </div>
                      <div class="col lf-yi" style="margin-top: -100px;">       
                        <img src="manaslogin/images/hotel2.jpg" > 
                      </div>
                        <div class="col center-holder footer-align" style="padding-top: 42px;">
                               <p style="color: #15536E;font-size: 20px;font-weight: 600;margin-left:-56px;">Follow us on : 
                               <a href="http://www.facebook.com/ManasYuvicare?mibextid=ZbWKwL" style="height:100px;
       width: 100px;
       background-color: #43BCB6;
       text-align: center; margin: 10px;box-shadow: 1px 1px 1px 1px black; padding-left:6px; padding-right: 6px;" target="_blank" title="Facebook">
       <i class="fa fa-facebook" style="font-size: 18px; color: white; padding-top: 6px;" ></i></a>
      <!--  http://www.youtube.com/@manas-yuvicarehimssoftware2656 -->
       <a href="https://www.youtube.com/playlist?list=PLP4zMZIMdqcSGOk3VTXYvrffCVku6_YqW" style="height:100px;
       width: 100px;
       background-color: #43BCB6;
       text-align: center; margin: 10px;box-shadow: 1px 1px 1px 1px black; padding-left:4px; padding-right: 4px;"   target="_blank" title="YouTube">
       <i class="fa fa-youtube-play"  style="font-size: 16px; color:white; padding-top: 6px" ></i></a>
       
       <a href="" style="height:80px;
       width: 80px;
       background-color: #43BCB6;
       text-align: center; margin: 10px;box-shadow: 1px 1px 1px 1px black;  padding-left: 4px; padding-right: 4px;" target="_blank" title="Twitter">
       <i class="fa fa-twitter"  style="font-size: 18px; color: white; padding-top: 6px"  ></i></a>
       
       <a href="https://www.linkedin.com/company/manas-yuvicare-hims" style="height:80px;
       width: 80px;
       background-color: #43BCB6;
       text-align: center; margin: 10px;box-shadow: 1px 1px 1px 1px black; padding-left: 4px; padding-right: 4px;" target="_blank" title="LinkedIn">
       <i class="fa fa-linkedin"  style="font-size: 18px; color: white; padding-top: 5px"  ></i></a>
        </p>
                          <ul style="margin-left: -91px;font-size: 12px;visibility: hidden;">
                                    <a href="#" onclick="openWin()"><li>Trouble Logging In?</li></a>
                                   <a href=""><li>Term of use</li></a>
                                   <a href=""><li>Privacy policy</li></a>
                               </ul>
                           </div>   
                     </div>
                    
                    <div class="col-lg-5 col-md-5 col-12 " style="margin-top: -68px">
                          <div class="col txt-right" style="padding-top: 75px;">
                              <h2 >Welcome to<br> Manas YuviCare</h2>
                              <p >To keep connected, please login with your personal information <br>by email address / username and password</p>
                          </div>
                          <a href="Pureseva" class="hidden"><img src="manasmaindashboard/images/Home.png"><span>Book</span></a>
                          <div class="row" style="padding-top: 50px;">
                            <div class="col-md-12 col-md-offset-2 " >  
                            <div class="col-lg-12 col-10 ">
                            	<s:actionerror id="server_side_error" />
                            	</div>	                           
                                <div class="form-group ">                                       
                                  <div class="col-lg-12 col-10 ">
                                    <label>User ID</label>
                                    <div class="input-group-prepend">
                                        <div class="input-group-text"><img src="manaslogin/images/Icon simple-email.svg" style="margin-top: -15px;">
                                        </div>
                                      </div>
                                        <input type="text" class="form-control inp" name="userId" value="" placeholder="" id="email" style="box-shadow: 0px 9px 29px 5px #88888887;margin-top: -52px;">
                                  </div>
                                </div>
                                <div class="form-group">
                                  
                                  <div class="col-lg-12 col-10 ">
                                    <label>Password</label>
                                    <div class="input-group-prepend">
                                        <div class="input-group-text"><img src="manaslogin/images/Icon material-lock-outline.svg" style="margin-top: -15px;"></div>
                                    </div>
                                    <input id="password-field" type="password" class="form-control inp" name="password" value=""  style="box-shadow: 1px 20px 26px 0px #8888883b;border-radius: 0rem 0rem 1rem 1rem;margin-top: -52px;">
                                    <span toggle="#password-field" class="fa fa-fw fa-eye field-icon toggle-password" style="color:#9393938f;"></span>
                                  </div>
                                </div>
                                <div class="rem" style="margin-left: 20px;padding: 10px 0px;">
                                    <input type="checkbox" id="checkbox" name="Remember" value="false">
                                    <label for="vehicle1" style="font-size: 12px;color: #818181;margin-left: 0px;"> Remember Me</label>
                                    <a href="inputResetPassword" target="null" style="font-size: 12px;color:#818181;" class="fp-align" >Forgot Password?</a>
                                </div>
                            </div>
                          </div>
        
                           <div class="col " style="padding-top: 20px;margin-left: 118px">
                               <!-- <a href="" class="btn btn-primary">Login</a> -->
                                <!-- <button class="btn btn-primary" type="button">Login</button> -->
                                <input type="submit" class="btn btn-primary" value="Login" id="lgnbtn" onclick="hidebtnn()">
                              <%-- <s:submit value="Login" cssClass="btn btn-primary" onclick="hidebtnn()"/> --%>
                               <!-- <a href="" class="btn btn-light btn-arrange">Create Account</a> -->
                           </div>
                                    
                           <div class="col center-holder footer-align" style="padding-top: 42px;">
                               <p style="color: #15536E;font-size: 12px;font-weight: 600;margin-left:-56px">Contact Our Customer Support : <a href="mailto:yuvicare.hims@manasindustry.com">yuvicare.hims@manasindustry.com</a></p>
                               

                               <ul style="margin-left: -91px;font-size: 12px;visibility: hidden;">
                                    <a href="#" onclick="openWin()"><li>Trouble Logging In?</li></a>
                                   <a href=""><li>Term of use</li></a>
                                   <a href=""><li>Privacy policy</li></a>
                               </ul> 
                           </div>     
                    </div>
                </div>
           </div>
           <input type="hidden" value="##Super<%=Constants.DB_PWD%>bela##">
          <input type="hidden" value="**expert<%=Constants.DB_USER%>heiest**">
           <div class="selectdb centered hidden" style="visibility: hidden;">
			                  <p>Select Database</p>
			                  <button class="btn btn-primary" type="button"><input type="radio" id="dbType" name="dbType" value="0" checked="checked"/> 
									<label for="payBuy"></label> <i class="fa fa-database"></i> Live DB
									</button>
			                  <button class="btn btn-primary" type="button"><input type="radio" id="payBuy1" name="dbType" value="1">
			                  <label for="payBuy"></label><i class="fa fa-database"></i> Demo DB</button>
			              </div>
           </form>
    </body>
    <script src="manaslogin/js/jquery.min.js"></script>
    <script src="manaslogin/js/popper.min.js"></script>
    <script src="manaslogin/js/bootstrap.min.js"></script>
    <script src="manaslogin/js/aos.js"></script>
    <script src="manaslogin/js/main.js"></script>
    <script>
        $(".toggle-password").click(function() {

            $(this).toggleClass("fa-eye fa-eye-slash");
            var input = $($(this).attr("toggle"));
            if (input.attr("type") == "password") {
            input.attr("type", "text");
            } else {
            input.attr("type", "password");
            }
            });
    </script>
    <script>
      $(document).ready(function () {
    var $input1 = $("#email");
    var $input2 = $("#password-field");

    function onChangeInput1() {

        $input1.css("background-color", "#f4fbff");
        var value = $.trim($input1.val());

        if (value.length === 0) {
            $input1.css("background-color", "transparent");
        }
    }

    function onChangeInput2() {
        $input2.css("background-color", "#f4fbff");
        var value = $.trim($input2.val());

        if (value.length === 0) {
            $input2.css("background-color", "transparent");
        }
    }

    $input1.on("keyup", onChangeInput1);
    $input2.on("keyup", onChangeInput2);
});
      
      
      function hidebtnn() {
    	  document.getElementById("lgnbtn").style.display = "none"; 
    	  document.getElementById("login_form").submit();
	}
    </script>
    
 <script>
					var myWindow;
					function openWin() {
					    //myWindow = window.open("letsTalkDiaryMangent", "", "width=450, height=600");
					     myWindow = window.open("letsTalkDiaryMangent", "", "width=800, height=600, addressbar=no,");
					}
			</script>
    
</html>