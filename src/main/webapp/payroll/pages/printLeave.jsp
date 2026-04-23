<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@page import="com.apm.common.web.common.helper.LoginInfo"%>
<%@page import="com.apm.common.web.common.helper.LoginHelper"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>


<style>
p {
	margin: 0 0 1.5px;
}

.table {
	width: 100%;
	max-width: 100%;
	margin-bottom: 5px;
}

.savebigbtn {
	height: 61px !important;
	font-size: 20px;
	background-color: #339966 !important;
	margin-bottom: 15px;
	line-height: 40px;
}

@media print {
	body {
		font-size: 9px !important;
	}
	.table>thead>tr>th, .table>tbody>tr>th, .table>tfoot>tr>th, .table>thead>tr>td,
		.table>tbody>tr>td, .table>tfoot>tr>td {
		padding: 2px 2px 2px 2px !important;
		line-height: 1.42857143;
		vertical-align: top;
		border-top: 1px solid #ddd;
		font-weight: normal;
		font-size: 9px !important;
		border-right: none !important;
		border-left: none !important;
	}
	.settopbac {
		background-color: #ddd !important;
	}
	.totalbor {
		background-color: #f5f5f5 !important;
	}
	.print_special {
		border: none !important;
	}
	label {
		font-size: 9px !important;
	}
	p {
		margin: 0 0 2.5px !important;
		font-size: 9px !important;
	}
	.form-group {
		margin-bottom: 0px !important;
	}
	.setotas {
		padding: 0px;
		text-align: right;
		color: #008000 !important;
		font-size: 10px;
	}
	.wordscolr {
		color: #d07878 !important;
		text-transform: uppercase;
	}
	.titleset {
		margin: 0px;
		color: #6699cc;
		border-bottom: 1px dashed #efefef;
		font-size: 12px !important;
		line-height: 20px;
	}
	h4, .h4 {
		font-size: 10px;
	}
	.backcolor {
		background-color: rgba(91, 192, 222, 0.16) !important;
	}
	.setticolors {
		border-bottom: 4px double #ddd;
		font-size: 10px !important;
		color: firebrick !important;
	}
	.titleset {
		margin: 0px;
		color: #6699cc !important;
		border-bottom: 1px dashed #efefef;
		font-size: 15px;
		line-height: 20px;
	}
	.table>thead>tr>th {
		vertical-align: bottom;
		border-bottom: transparent;
		background-color: #4E7894 !important;
		color: #fff !important;
		font-size: 9px !important;
	}
	.setotas {
		padding: 0px 6px 4px 0px;
		text-align: right;
		color: green;
		font-size: 10px !important;
	}
}
<
SCRIPT
 
type
="text/javascript"
 
src
="payroll/js/payrollmaster
.js
"
>
</
SCRIPT
>
</style>
<SCRIPT type="text/javascript" src="inventory/js/addproduct.js"></SCRIPT>
<SCRIPT type="text/javascript" src="inventory/js/indentproduct.js"></SCRIPT>
</head>
<body>
	<div class="col-lg-12 col-xs-12 col-md-12" >
		<%
			LoginInfo loginInfo = LoginHelper.getLoginInfo(request);
		%>
<div class="row" id="page_printer2">
		<form action="leavePrintPayrollDashBoard">

			<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
				<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12"
					style="padding: 0px;">
					<div class=""
						style="border-bottom: 2px solid #6699cc; padding-top: 36px; padding-bottom: 15px; height: 135px;">
						<div id="newpost" class="col-lg-12 col-md-12 col-xs-12 col-sm-12">
							<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12"
								style="padding-left: 0px; padding-right: 0px;">
								<link href="common/css/printpreview.css" rel="stylesheet" />
								<%@ include file="/accounts/pages/letterhead.jsp"%>
							</div>
						</div>
					</div>
					<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12 text-center">
						<h4 style="color: red">
							<b>LEAVE REQUEST NOTE</b>
						</h4>
						<%-- <p style="margin: 0 0 2.5px;"><b>Requsted User : </b><span id="userid"></span></p>
										        				<p style="margin: 0 0 2.5px;"><b>Requsted Date : </b><span id="requestdate"></span></p> --%>
						<%-- <p style="margin: 0 0 2.5px;"><b>Name : </b><span id="requestname"></span></p>
										        				<p style="margin: 0 0 2.5px;"><b>Contact : </b><span id="requestcontact"></span></p>
										        				<p style="margin: 0 0 2.5px;"><b>Department : </b><span id="requestdepartment"></span></p> --%>



						<%-- <p style="margin: 0 0 2.5px;">
							<b>Name : </b><span><s:property value="requestname" /></span>
						</p>
						<p style="margin: 0 0 2.5px;">
							<b>Contact : </b><span><s:property value="requestcontact" /></span>
						</p>
						<p style="margin: 0 0 2.5px;">
							<b>Department : </b><span><s:property
									value="requestdepartment" /></span>
						</p>
 --%>


					</div>
					<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12"
						style="border-bottom: 1px solid #ddd; padding: 0px; margin-bottom: 15px;"
						id="billanddata">
						<input type="hidden" name="id" id="id"
							value="<s:property value="id"/>">
						<%-- 		<div class="col-lg-6 col-md-6 col-xs-6 col-sm-6" style="text-align: left;">
					<p class="marboset" style="margin-bottom:0px;"><b>Issue Number :</b>&nbsp;<span><s:property value="issueno"/></span></p>
					<p class="marboset" style="margin-bottom:0px;"><b>Requested From  :</b>&nbsp;<span ><s:property value="from_location"/></span></p>
					<p class="marboset" style="margin-bottom:0px;"><b>Indent Number :</b>&nbsp;<span><s:property value="indentid"/></span></p>
				</div> --%>
						<div class="col-lg-6 col-md-6 col-xs-6 col-sm-6"
							style="text-align: left;">
							<%-- <p class="marboset" style="margin-bottom:0px;"><b>Issue Date :</b>&nbsp;<span><s:property value="issued_date"/></span></p> --%>
							<%-- <p class="marboset" style="margin-bottom:0px;"><b>Request Date :</b>&nbsp;<span ><s:property value="requestdate"/></span></p>
					<p class="marboset" style="margin-bottom:0px;"><b>Request User :</b>&nbsp;<span><s:property value="userid"/></span></p> --%>
						</div>
					</div>

				</div>
			</div>


			<div class="col-lg-12 col-md-12 col-xs-12"
				style="background-color: rgba(239, 239, 239, 0.42); padding: 9px; border: 1px dashed #ddd; margin-bottom: 15px;">
				<h5
					style="color: chocolate; text-transform: uppercase; margin: 0px 0px 3px 0px; text-align: center;"><b>Request
					Leave :-</b></h5>
				<%-- <table class="table table-striped table-bordered"
					style="width: 100%;">
					<thead>
						<tr>
							<!-- <th style="width: 9%;">Name</th>
                                    <th style="width: 13%;">Leave Type</th>
                                    <th style="width: 13%;">Reason For Leave</th>
                                  
                                <th style="text-align: center;width: 8%;">From Date</th> 
                                
                                	<th style="text-align: center;width: 10%;">To Date</th>
                                	<th style="text-align: center;width: 10%;">Requested Date</th>
                                	<th style="text-align: center;width: 10%;">Approved Date</th>
                                	<th style="text-align: center;width: 10%;">Approved By</th> -->
							<!-- <th style="text-align: center;width: 7%;">Status</th> -->
							<!-- <th style="text-align:center;width: 8%;">Action</th>
                                	<th style="text-align:center;width: 1%;">Print</th> -->

							<th style="width: 9%;">Name</th>
							<th style="width: 13%;">Leave Type</th>
							<th style="width: 13%;">Reason For Leave</th>
							<th style="text-align: center; width: 10%;">Requested Date</th>
							<th style="text-align: center; width: 8%;">From Date</th>

							<th style="text-align: center; width: 10%;">To Date</th>
							<th style="text-align: center; width: 5%;">Days</th>
							<th style="text-align: center; width: 10%;">Status</th>
							<th style="text-align: center; width: 10%;">Approved Date</th>
							<th style="text-align: center; width: 10%;">Approved By</th>


						</tr>
					</thead>
					<tbody>
						<%
							int i = 0;
						%>
						<s:iterator value="printleavelist">
							<tr>
								<td><%=++i%></td>
								       <td><s:property value="name"/></td>
              					<td><s:property value="leave_type"/></td>
              					<td><s:property value="leave_reason"/></td>
              					<td><s:property value="fromdate"/></td>
              					<td><s:property value="todate"/></td>
              					<td><s:property value="date"/></td>
              					<td><s:property value="approveddate"/></td>
              					<td><s:property value="userid"/></td>


								<td style="text-align: center;"><s:property value="name" /></td>
								<td ><s:property value="leave_type" /></td>
								<td ><s:property value="leave_reason" /></td>
								<td style="text-align: center;"><s:property value="date" /></td>
								<td style="text-align: center;"><s:property value="fromdate" /></td>
								<td style="text-align: center;"><s:property value="todate" /></td>
								<td style="text-align: center;"><s:property value="days" /></td>
								
								
								
								
								
								<td style="text-align: center;">
								<s:if test="status==0">
								Request
								</s:if>
								<s:elseif test="status==1">
								Approved By HOD
								</s:elseif>
								<s:elseif test="status==3">Approved By HR</s:elseif>
								<s:else>Rejected</s:else>
								</td>
								<td style="text-align: center;"><s:property value="approveddate" /></td>
								<td style="text-align: center;"><s:property value="userid" /></td>





									<s:if test="mainstatus!=4">
          				 <s:if test="status==0">
          					
          					<s:if test="cancel_req==0">
            					<span><b>- NA</b></span>
            				</s:if>
            				<s:else>
            					<span><b>Cancelled</b></span>
            				</s:else>
          				</s:if> 
          			</s:if>
								<!-- </td> -->
								<s:if test="mainstatus==4">
          			<td>
            			<s:if test="status==0">
            				<s:if test="cancel_req==0">
            					<span><b>Not Available</b></span>
            				</s:if>
            				<s:else>
            					<span><b>Cancelled</b></span>
            				</s:else>
          					
          				</s:if>
          				<s:else>
          					<span><b>Transfered</b></span>
          				</s:else>
          				</td>
            		</s:if>
								<td><s:property value="totaltransferqt"/></td>
            	<td><s:property value="transfer_date"/></td>
            	
							</tr>
						</s:iterator>
					</tbody>
				</table> --%>
				<s:iterator value="printleavelist">
				<span style="font-family: cursive;font-size: large;">
				<strong>Subject: Application for <s:if test="leave_type==1">Casual Leave</s:if> <s:elseif test="leave_type==2">Medical Leave</s:elseif><s:else>Loss Of Pay Leave</s:else></strong><br>
				<strong>Name: </strong><s:property value="name" /><br>

					<span>Respected Sir/Mam,<br>
					
					I am writing to you to let you know that  I will not be able to come to the office 
					from <strong><s:property value="fromdate" /></strong> to <strong><s:property value="todate" /></strong>.<br>
					I will not be able to come to the office <strong><s:property value="days" /></strong> Days.<br>
					Thanking You !<br><br>
					
					&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;<span>Yours Sincerely,</span><br>
					<span>Req. Date: <s:property value="date"/></span>&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&nbsp;<strong><s:property value="name" /></strong> <br>
					<br><br><strong>Approved By: -&emsp;</strong><s:property value="approvedby" /> <br>
					<strong>Approved Date: -</strong><s:property value="approveddate" /> 
					</span>
					</span>
				</s:iterator>
			</div>
			<s:if test="printbeforeapprove==1">
			</s:if>
			<s:else>


				
				
			</s:else>
			<s:if test="printbeforeapprove==1">
			</s:if>
			<s:else>
				<div class="" style="padding-top: 80px;">
					<div class="col-lg-12 col-xs-12 col-md-12" style="padding: 0px;">
						<div class="col-lg-6 col-md-6 col-xs-6 col-sm-6"
							style="padding: 0px;">
							<label></label>
							<%
								int x = 1;
							%>
							<s:iterator value="termsandconditionlist">
								<p style="margin-bottom: 0px;"><%=x++%>
									<s:property value="name" />
								</p>
							</s:iterator>
							<p style="margin-bottom: 0px;" class="hidden">1) Delivery
								Time: Within one week from the date of order.</p>
							<p style="margin-bottom: 0px;" class="hidden">2) Item
								Inspection has to be done before receiving it.</p>
							<!-- <br> -->
							<!-- <a class="btn btn-primary" href="#" onclick="opencPopup('TermsConditionMaster')">Add Terms And Condition</a> -->
						</div>

						<div class="col-lg-6 col-md-6 col-xs-6 col-sm-6"
							style="padding: 0px; margin-left: 1124px;">
							<p style="margin-bottom: 0px;">
							</p>
							<%-- <p style="margin-bottom:0px;">Transfered by : <s:property value="check_avail_userid"/> | <s:property value="check_availability_date"/></p> --%>
							<p style="margin-bottom: 0px;">
								Printed by :
								<s:property value="curr_user" />
								|
								<s:property value="date" />
							</p>
							<input type="button" onclick="printDiv2('page_printer2')"
								class="btn btn-primary savebigbtn hidden-print" value="PRINT" />
						</div>
					</div>
				</div>
			</s:else>

			

		</form>
		</div>
	</div>




	<script>
		function printDiv2(divID) {
			//Get the HTML of div
			var divElements = document.getElementById(divID).innerHTML;
			//Get the HTML of whole page
			var oldPage = document.body.innerHTML;

			//Reset the page's HTML with div's HTML only
			document.body.innerHTML = "<html><head><title></title></head><body>"
					+ divElements + "</body>";

			//window.print();
			//document.body.innerHTML = oldPage;

			//Print Page
			setTimeout(function() {
				print_page();
			}, 2000);

			function print_page() {
				window.print();
			}

			//Restore orignal HTML
			setTimeout(function() {
				restore_page();
			}, 3000);

			function restore_page() {
				document.body.innerHTML = oldPage;
			}
		}
	</script>
</body>
</html>