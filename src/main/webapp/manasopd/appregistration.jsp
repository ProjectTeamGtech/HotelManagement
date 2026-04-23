<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="/struts-tags"  prefix="s"%>
    <%@page import="com.apm.common.web.common.helper.LoginHelper"%>
<%@page import="com.apm.common.web.common.helper.LoginInfo"%>
    
<!DOCTYPE html>
<html class="no-js" lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="x-ua-compatible" content="ie=edge">
    <title>Manas - Yuvicare Dashboard</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
     <link rel="shortcut icon" href=" manaslogin/images/Manas_Yuvicare_Circle Favicon.png">
</head>

<link href="diarymanagement/css/popupstyle.css" rel="stylesheet" type="text/css" />

<link href="diarymanagement/css/subModal.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="diarymanagement/js/common.js"></script>
<script type="text/javascript" src="diarymanagement/js/subModal.js"></script>
<script type="text/javascript" src="report/js/report.js"></script>
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.bundle.min.js"></script>


<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.min.js"></script>
<link rel="stylesheet" href="https://code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
<%
				LoginInfo loginInfo = LoginHelper.getLoginInfo(request);
		   %>
    


<script type="text/javascript" src="diarymanagement/js/dayUsers.js"></script>
<script type="text/javascript" src="diarymanagement/js/nonavailableslot.js"></script>
<script type="text/javascript" src="diarymanagement/js/notavailableslotpopupscript.js"></script>
<script type="text/javascript">

$(document).ready(function() {
	$( "#initialdob" ).datepicker({
		 
		 dateFormat:'dd/mm/yy',
		 yearRange: yearrange,
		 minDate : '30/12/1880',
		 changeMonth: true,
		 changeYear: true,
		 maxDate: new Date(new Date().setDate(todayDate))
	 });
});

</script>
<style>
th, td {
  white-space: nowrap;
  padding: 8px;
  border: 1px solid #ddd;
}
.modal-body {
  overflow-y: auto;
  max-height: 80vh; /* Allow scrolling inside modal */
}
.manasnewipdimg{

    display: flex;
    justify-content: flex-end;
    align-items: flex-end;
    }

html{
height: 48px !important;
}
.goog-te-banner-frame.skiptranslate {
    display: none !important;
    } 
body {
    top: 0px !important;
    min-height: 0px !important; 
    }
    .main-content-inner {
    padding: 0 2px 50px;
}

.fa-plus{
	vertical-align: unset ;
}

::-webkit-scrollbar { 
  width: 0px!important;
} 


</style>




<style>
  body {
  margin: 0;
  padding: 0;
  height: 50vh;
  background-color: #f0f4f8; /* light background */
  display: flex;
  justify-content: center;
  align-items: center; /* Center the button vertically */
  font-family: Arial, sans-serif;
}

/* Centering the form group */
#newreg {
  text-align: center; /* Horizontally center the form group */
}

/* Stylish button */
.modern-btn {
  padding: 12px 28px;
  font-size: 16px;
  font-weight: 600;
  color: #fff;
  background: linear-gradient(135deg, #6e8efb, #a777e3);
  border: none;
  border-radius: 8px;
  box-shadow: 0 8px 20px rgba(0, 0, 0, 0.15);
  cursor: pointer;
  transition: all 0.3s ease;
  backdrop-filter: blur(5px);
}

.modern-btn:hover {
  transform: translateY(-3px);
  box-shadow: 0 12px 24px rgba(0, 0, 0, 0.2);
}

.modern-btn:active {
  transform: translateY(1px);
  box-shadow: 0 6px 16px rgba(0, 0, 0, 0.1);
}


.modern-btn {
  display: inline-block;
  padding: 0.8em 1.2em;
  font-size: 16px;
  max-width: 100%;
  white-space: normal;
}

/* Container styling */
.form-group {
  display: flex;
  align-items: center;
  justify-content: center;
}

/* Mobile-specific adjustments */
@media (max-width: 767px) {
  .form-group {
    flex-direction: column;
    padding: 10px;
  }

  .modern-btn {
    width: 100%;
    font-size: 14px;
    padding: 12px;
    text-align: center;
    white-space: normal;
  }
}
  </style>

<body>
<s:form action="calNotAvailableSlot"  method="post">
<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12">
<div class="form-group" id="newreg" align="center">
<input type="submit" value="Welcome to Manas Yuvicare Mobile Application for Patient's Online Appointment Booking" class="modern-btn" >
<input type="hidden" id="actionType" name="actionType" value="mob">
</div>
</div>
</s:form>








   <script src="common/assets/js/jquery.fileupload.js"></script>
   
   
    <!-- jquery latest version -->
    <!-- bootstrap 4 js -->
    <script src="manasopd/assets/js/popper.min.js"></script> 
     <script src="manasopd/assets/js/bootstrap.min.js"></script> 
     <script src="manasopd/assets/js/owl.carousel.min.js"></script>
    <script src="manasopd/assets/js/metisMenu.min.js"></script>
    <script src="manasopd/assets/js/jquery.slimscroll.min.js"></script>
    <script src="manasopd/assets/js/jquery.slicknav.min.js"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/jqueryui/1.11.2/jquery-ui.min.js"></script> 
  
    <!-- others plugins -->
    <script src="manasopd/assets/js/plugins.js"></script>
    <script src="manasopd/assets/js/scripts.js"></script>
    <script src="common/chosen_v1.1.0/chosen.jquery.js" type="text/javascript"></script>
	  <script src="common/chosen_v1.1.0/docsupport/prism.js" type="text/javascript" charset="utf-8"></script>
			  <script src="common/chosen_v1.1.0/docsupport/prism.js" type="text/javascript" charset="utf-8"></script>
			  <script type="text/javascript">
			    var config = {
			      '.chosen-select'           : {},
			      '.chosen-select-deselect'  : {allow_single_deselect:true},
			      '.chosen-select-no-single' : {disable_search_threshold:10},
			      '.chosen-select-no-results': {no_results_text:'Oops, nothing found!'},
			      '.chosen-select-width'     : {width:"100%"}
			    }
			    for (var selector in config) {
			      $(selector).chosen(config[selector]);
			    }
			  </script>
    <script>
    $(function() {
		$('.setreferscroll').slimScroll({
			height : '115px',
			width : '100%',
			railVisible : true,
			alwaysVisible : true
		});
	});
    </script>
</body>
</html>