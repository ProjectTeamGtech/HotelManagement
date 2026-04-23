<%@page import="com.apm.common.web.common.helper.LoginHelper"%>
<%@page import="com.apm.common.web.common.helper.LoginInfo"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" type="text/css" href="print.css" media="print" />
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
	
		<!-- Favicon -->
        <link rel="shortcut icon" type="image/x-icon" href="payrollnew/assets/img/favicon.png">
		
		<!-- Bootstrap CSS -->
        <link rel="stylesheet" href="payrollnew/assets/css/bootstrap.min.css">
		
		<!-- Fontawesome CSS -->
        <link rel="stylesheet" href="payrollnew/assets/css/font-awesome.min.css">
		
		<!-- Lineawesome CSS -->
        <link rel="stylesheet" href="payrollnew/assets/css/line-awesome.min.css">
		
		<!-- Select2 CSS -->
		<link rel="stylesheet" href="payrollnew/assets/css/select2.min.css">
		
		<!-- Datetimepicker CSS -->
		<link rel="stylesheet" href="payrollnew/assets/css/bootstrap-datetimepicker.min.css">
		
		<!-- Main CSS -->
        <link rel="stylesheet" href="payrollnew/assets/css/style.css">
</head>
<body>
          <div class="">
			
			</div>
			<script type="text/javascript">
			function onmedenu() {
				
				
				$(document).on('click', '#toggle_btn', function() {
					if($('body').hasClass('mini-sidebar')) {
						$('body').removeClass('mini-sidebar');
						$('.subdrop + ul').slideDown();
					} else {
						$('body').addClass('mini-sidebar');
						$('.subdrop + ul').slideUp();
					}
					return false;
				});
				$(document).on('mouseover', function(e) {
					e.stopPropagation();
					if($('body').hasClass('mini-sidebar') && $('#toggle_btn').is(':visible')) {
						var targ = $(e.target).closest('.sidebar').length;
						if(targ) {
							$('body').addClass('expand-menu');
							$('.subdrop + ul').slideDown();
						} else {
							$('body').removeClass('expand-menu');
							$('.subdrop + ul').slideUp();
						}
						return false;
					}
				});
				}
			</script>
</body>
</html>