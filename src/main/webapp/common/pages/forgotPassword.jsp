<%@taglib uri="/struts-tags" prefix="s"%>

 		<!-- ============================================
        ================= Stylesheets ===================
        ============================================= -->
        <!-- vendor css files -->
        <link rel="stylesheet" href="_assets/newtheme/css/vendor/bootstrap.min.css">
         <link rel="stylesheet" href="_assets/newtheme/css/vendor/animate.css">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.5.0/css/font-awesome.min.css">
        <link rel="stylesheet" href="_assets/newtheme/js/vendor/animsition/css/animsition.min.css">

        <!-- project main css files -->
        <link rel="stylesheet" href="_assets/newtheme/css/main.css">
        <!--/ stylesheets -->



<script type="text/javascript" src="common/js/resetPassword.js"></script>



  <div id="wrap" class="animsition">
                <div class="container w-420 p-15 bg-white mt-40 text-center">

                    <h2 class="text-light text-greensea">Forgot Password?</h2>
					
					<s:form action = "forgotPaswdResetPassword" theme="simple">
					<p class="help-block text-left">
                            Enter the username you use to sign in to your YUVICARE Account.
                        </p>
                        <p class="help-block text-left">
                            YUVICARE will send instructions on how to reset your password to the email address associated with your account.
                        </p>
                        <p class="help-block text-left">
                            The instructions will be valid for one day.
                        </p>
                        <div class="form-group">
                            <s:textfield name="emailId" id="emailId" cssClass="form-control" placeholder="Email" onchange="return checkExist()" />
                            <label id = "emailIdError" class="text-danger"></label>		
                        </div>
                        <div class="lt wrap-reset mt-40 text-left">
                        <p class="m-0">
                            <input type="submit" value="Submit" class="btn btn-primary text-uppercase pull-right" style="line-height: 10px;" onclick = "return checkExist()" />
                        </p>
                    </div>
					
					</s:form>

                </div>

        </div>
        
        
        
         <!-- ============================================
        ============== Vendor JavaScripts ===============
        ============================================= -->
        
        	
        <script src="_assets/newtheme/js/vendor/jquery/jquery-1.11.2.min.js"></script>
       

        <script src="_assets/newtheme/js/vendor/bootstrap/bootstrap.min.js"></script>

        <script src="_assets/newtheme/js/vendor/jRespond/jRespond.min.js"></script>

        <script src="_assets/newtheme/js/vendor/sparkline/jquery.sparkline.min.js"></script>

        <script src="_assets/newtheme/js/vendor/slimscroll/jquery.slimscroll.min.js"></script>

        <script src="_assets/newtheme/js/vendor/animsition/js/jquery.animsition.min.js"></script>

       
        <!--/ vendor javascripts -->




        <!-- ============================================
        ============== Custom JavaScripts ===============
        ============================================= -->
        <script src="_assets/newtheme/js/main.js"></script>
        <!--/ custom javascripts -->






        <!-- ===============================================
        ============== Page Specific Scripts ===============
        ================================================ -->
        <script>
            $(window).load(function(){


            });
        </script>
        <!--/ Page Specific Scripts -->