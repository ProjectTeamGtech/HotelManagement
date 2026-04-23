<%@page import="com.apm.common.web.common.helper.LoginHelper"%>
<%@page import="com.apm.common.web.common.helper.LoginInfo"%>
<%@page import="com.apm.main.common.constants.Constants"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib uri="/struts-tags" prefix="s"%>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0, user-scalable=0">
        <meta name="description" content="Smarthr - Bootstrap Admin Template">
		<meta name="keywords" content="admin, estimates, bootstrap, business, corporate, creative, management, minimal, modern, accounts, invoice, html5, responsive, CRM, Projects">
        <meta name="author" content="Dreamguys - Bootstrap Admin Template">
        <meta name="robots" content="noindex, nofollow">
        <script type="text/javascript" src="common/js/fullscreen.js"></script>
       <link rel="stylesheet" href="_assets/newtheme/css/main.css">
       <link href="common/css/printpreview.css" rel="stylesheet" />
<link href="style.css" media="all" rel="stylesheet">

<script type="text/javascript" src="accounts/js/printpreview.js"></script>
<script type="text/javascript" src="tools/js/emailTemplate.js"></script>
<%-- <style type="text/css">
@media print { 
               .noprint { 
                  visibility: hidden; 
               } 
</style> --%>
        <title>Salary - HRMS admin template</title>
    </head>
    <script type="text/javascript">
     window.onload =function(){ 
	      
	      		
                                            			      var tt= Number(document.getElementById("netpay").value);
                                            			      document.getElementById("word").innerHTML=convertNumberToWords(tt);
                                            			      
                                            			     /*  $('.hercomment').each(function() { 
                                            					this.innerHTML="Hi<br>"+this.innerHTML;						
                                            					}); */
                                            			      
                                            			      
	      };
	      </script>
    <%LoginInfo loginInfo=LoginHelper.getLoginInfo(request); %>
    <body>
			
				<!-- Page Content -->
                <div class="content container-fluid" id="page_printer">
				
					<!-- Page Title -->
					<div class="row letterheadhgt">
					<div id="newpost" class="col-lg-12 col-md-12 col-xs-12 col-sm-12 borderbot">
							
						<%if(!loginInfo.isHidelogobillinv()){ %>
							<%@ include file="/accounts/pages/letterhead.jsp" %>
						<%} %>
					</div>
				</div>
					<div class="row">
						<div class="col-sm-5 col-4">
							<h4 class="page-title">Payslip</h4>
						</div>
						<div class="col-sm-7 col-8 text-right m-b-30">
							<div class="btn-group btn-group-sm">
								<!-- <button class="btn btn-white">CSV</button>
								<button class="btn btn-white">PDF</button> -->
								<button class="btn btn-white d-print-none" onclick="printDiv('page_printer')"><i class="fa fa-print fa-lg" ></i> Print</button>
							</div>
						</div>
					</div>
					<!-- /Page Title -->
					
					<div class="row">
						<div class="col-md-12">
						
							<div class="card-box">
								<h4 class="payslip-title">Payslip for the month of <s:property value="Monthandyear"/></h4>
								<div class="row">
										<div class="col-sm-6 m-b-20">
										<!-- <img src="assets/img/logo.png" class="inv-logo" alt="">
										<ul class="list-unstyled mb-0">
											<li>Dreamguy's Technologies</li>
											<li>3864 Quiet Valley Lane,</li>
											<li>Sherman Oaks, CA, 91403</li>
										</ul> -->
									</div> 
									<div class="col-sm-6 m-b-20">
										<div class="invoice-details">
										<!-- 	<h3 class="text-uppercase">Payslip #49029</h3> -->
											<ul class="list-unstyled">
												<li>Salary Month: <span><s:property value="Monthandyear"/></span></li>
											</ul>
										</div>
									</div>
								</div>
								<div class="row">
									<div class="col-sm-6">
										<ul class="list-unstyled">
											<li>Name :  <strong><s:property value="emp_name"/></strong></li>
										<%-- 	<li><span> <s:property value="emp_role"/></span></li> --%>
											<li>Employee ID : <strong><s:property value="emp_id"/></strong></li>
											<li>Total Worked Days : <strong><s:property value="days"/></strong></li>
										</ul>
									</div>
									<div class="col-sm-6">
										<ul class="list-unstyled">
											<li>Designation :  <strong><s:property value="emp_role"/></strong></li>
											
											<li>Joining Date : <strong><s:property value="Date_join"/></strong></li>
										</ul>
									</div>
								</div>
								<div class="row">
									<div class="col-sm-6">
										<div>
											<h4 class="m-b-10" style="background-color: #81bee6"><strong>Earnings</strong></h4>
											<table class="table table-bordered" style="color: black;">
												<tbody>
													<tr>
														<td><strong>Basic Salary</strong> <span class="float-right"><%=Constants.getCurrency(loginInfo)%><s:property value="basic"/></span></td>
													</tr>
													<tr>
														<td><strong>House Rent Allowance (H.R.A.)</strong> <span class="float-right"><%=Constants.getCurrency(loginInfo)%><s:property value="hra"/></span></td>
													</tr>
													<tr>
														<td><strong>DA</strong> <span class="float-right"><%=Constants.getCurrency(loginInfo)%><s:property value="da"/></span></td>
													</tr>
													<tr>
														<td><strong>Conveyance</strong> <span class="float-right"><%=Constants.getCurrency(loginInfo)%><s:property value="conveyance"/></span></td>
													</tr>
													<tr>
														<td><strong>Allowances</strong> <span class="float-right"><%=Constants.getCurrency(loginInfo)%><s:property value="perdevallow"/></span></td>
													</tr>
													<tr>
														<td><strong>Medical Allowances</strong> <span class="float-right"><%=Constants.getCurrency(loginInfo)%><s:property value="medical_allowance"/></span></td>
													</tr>
													<tr>
														<td><strong>Over Time</strong> <span class="float-right"><%=Constants.getCurrency(loginInfo)%><s:property value="ot"/></span></td>
													</tr>
													<tr>
														<td><strong>Advance</strong> <span class="float-right"><%=Constants.getCurrency(loginInfo)%><s:property value="advance"/></span></td>
													</tr>
													<tr>
														<td style="background-color: #81bee6"><strong>Total Earnings</strong> <span class="float-right"><strong><%=Constants.getCurrency(loginInfo)%><s:property value="totearn"/></strong></span></td>
													</tr>
												</tbody>
											</table>
										</div>
									</div>
									<div class="col-sm-6">
										<div>
											<h4 class="m-b-10" style="background-color: #81bee6"><strong>Deductions</strong></h4>
											<table class="table table-bordered" style="color: black;">
												<tbody>
													<tr>
														<td><strong>Tax Deducted at Source (T.D.S.)</strong> <span class="float-right"><%=Constants.getCurrency(loginInfo)%><s:property value="tds"/></span></td>
													</tr>
													<tr>
														<td><strong>Provident Fund</strong> <span class="float-right"><%=Constants.getCurrency(loginInfo)%><s:property value="emp_pf"/></span></td>
													</tr>
													<tr>
														<td><strong>ESI</strong> <span class="float-right"><%=Constants.getCurrency(loginInfo)%><s:property value="emp_esi"/></span></td>
													</tr>
													<tr>
														<td><strong>Leaves</strong> <span class="float-right"><%=Constants.getCurrency(loginInfo)%><s:property value="total_leaves"/></span></td>
													</tr> 
													<tr>
														<td><strong>Professional Tax</strong> <span class="float-right"><%=Constants.getCurrency(loginInfo)%><s:property value="prefessional_tax"/></span></td>
													</tr>
													<tr>
														<td><strong>Loan</strong> <span class="float-right"><%=Constants.getCurrency(loginInfo)%><s:property value="loan"/></span></td>
													</tr>
													<tr>
														<td style="background-color: #81bee6"><strong>Total Deductions</strong> <span class="float-right"><strong><%=Constants.getCurrency(loginInfo)%><s:property value="totded"/></strong></span></td>
													</tr>
												</tbody>
											</table>
										</div>
									</div><br><br>
									<div class="col-sm-12">
									<div class="col-sm-6">
									</div>
									<div class="col-sm-6" style="margin-left: 613px;">
									<s:hidden name="netpay" id="netpay"/>
										<p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<h4>Net Salary: <%=Constants.getCurrency(loginInfo)%><s:property value="netpay"/></h4> (<span id="word" > </span> <span> Only</span>)</p>
									</div>
									</div>
								</div>
							</div>
						</div>
					</div>
                </div>
				<!-- /Page Content -->
				

		<!-- Sidebar Overlay -->
		<div class="sidebar-overlay" data-reff=""></div>

		<!-- jQuery -->
        <script src="assets/js/jquery-3.2.1.min.js"></script>

		<!-- Bootstrap Core JS -->
        <script src="assets/js/popper.min.js"></script>
        <script src="assets/js/bootstrap.min.js"></script>

		<!-- Slimscroll JS -->
		<script src="assets/js/jquery.slimscroll.min.js"></script>

		<!-- Custom JS -->
		<script src="assets/js/app.js"></script>

    </body>
    <script>
	function convertNumberToWords(amount) {
    var words = new Array();
    words[0] = '';
    words[1] = 'One';
    words[2] = 'Two';
    words[3] = 'Three';
    words[4] = 'Four';
    words[5] = 'Five';
    words[6] = 'Six';
    words[7] = 'Seven';
    words[8] = 'Eight';
    words[9] = 'Nine';
    words[10] = 'Ten';
    words[11] = 'Eleven';
    words[12] = 'Twelve';
    words[13] = 'Thirteen';
    words[14] = 'Fourteen';
    words[15] = 'Fifteen';
    words[16] = 'Sixteen';
    words[17] = 'Seventeen';
    words[18] = 'Eighteen';
    words[19] = 'Nineteen';
    words[20] = 'Twenty';
    words[30] = 'Thirty';
    words[40] = 'Forty';
    words[50] = 'Fifty';
    words[60] = 'Sixty';
    words[70] = 'Seventy';
    words[80] = 'Eighty';
    words[90] = 'Ninety';
    amount = amount.toString();
    var atemp = amount.split(".");
    var number = atemp[0].split(",").join("");
    var n_length = number.length;
    var words_string = "";
    if (n_length <= 9) {
        var n_array = new Array(0, 0, 0, 0, 0, 0, 0, 0, 0);
        var received_n_array = new Array();
        for (var i = 0; i < n_length; i++) {
            received_n_array[i] = number.substr(i, 1);
        }
        for (var i = 9 - n_length, j = 0; i < 9; i++, j++) {
            n_array[i] = received_n_array[j];
        }
        for (var i = 0, j = 1; i < 9; i++, j++) {
            if (i == 0 || i == 2 || i == 4 || i == 7) {
                if (n_array[i] == 1) {
                    n_array[j] = 10 + parseInt(n_array[j]);
                    n_array[i] = 0;
                }
            }
        }
        value = "";
        for (var i = 0; i < 9; i++) {
            if (i == 0 || i == 2 || i == 4 || i == 7) {
                value = n_array[i] * 10;
            } else {
                value = n_array[i];
            }
            if (value != 0) {
                words_string += words[value] + " ";
            }
            if ((i == 1 && value != 0) || (i == 0 && value != 0 && n_array[i + 1] == 0)) {
                words_string += "Crores ";
            }
            if ((i == 3 && value != 0) || (i == 2 && value != 0 && n_array[i + 1] == 0)) {
                words_string += "Lakhs ";
            }
            if ((i == 5 && value != 0) || (i == 4 && value != 0 && n_array[i + 1] == 0)) {
                words_string += "Thousand ";
            }
            if (i == 6 && value != 0 && (n_array[i + 1] != 0 && n_array[i + 2] != 0)) {
                words_string += "Hundred and ";
            } else if (i == 6 && value != 0) {
                words_string += "Hundred ";
            }
        }
        words_string = words_string.split("  ").join(" ");
    }
    return words_string;
}
</script>  
<script>
	function printDiv(divName) {
	     var printContents = document.getElementById(divName).innerHTML;
	     var originalContents = document.body.innerHTML;

	     document.body.innerHTML = printContents;

	     window.print();

	     document.body.innerHTML = originalContents;
	}
	</script>
</html>