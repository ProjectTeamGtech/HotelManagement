
<%@page import="com.apm.common.web.common.helper.LoginHelper"%>
<%@page import="com.apm.common.web.common.helper.LoginInfo"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<script type="text/javascript" src="diarymanagement/js/finder.js"></script>

<%-- <script type="text/javascript" src="diarymanagement/js/nonavailableslot.js"></script>
<script type="text/javascript" src="diarymanagement/js/notavailableslotpopupscript.js"></script> 
<script type="text/javascript" src="diarymanagement/js/commonAppointmentView.js"></script>
 --%>
<script src="notification/js/bootstrap-select.js"></script>
<link href="diarymanagement/css/popupstyle.css" rel="stylesheet"
	type="text/css" />
<link href="diarymanagement/css/subModal.css" rel="stylesheet"
	type="text/css" />
<!--  <link href="_assets/newtheme/css/main.css" rel="stylesheet" />	 -->
<script type="text/javascript"
	src="assesmentForms/js/jquery.table2excel.js"></script>
<%-- <script type="text/javascript" src="accounts/js/printpreview.js"></script> --%>
<script type="text/javascript" src="common/js/pagination.js"></script>
<%
	LoginInfo loginInfo = LoginHelper.getLoginInfo(request);
%>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<style>
.newbtnyes {
	position: absolute !important;
	font-size: 14px !important;
	color: #fff !important;
	background-color: #38a538 !important;
	border-color: #15536E !important;
	margin-right: -203px !important;
	border-radius: 0.50rem;
}

.newbtnno {
	position: absolute !important;
	font-size: 14px !important;
	color: #fff !important;
	background-color: #e22626 !important;
	border-color: #15536E !important;
	margin-right: -203px !important;
	border-radius: 0.50rem;
}

.newbtn {
	position: absolute !important;
	font-size: 14px !important;
	color: #fff !important;
	background-color: #15536E !important;
	border-color: #15536E !important;
	margin-right: -203px !important;
	border-radius: 0.50rem;
}

.btn {
	background-color: #15536E !important;
	border-radius: 0.50rem;
}

.table-hover>tbody>tr:hover>td, .table-hover>tbody>tr:hover>th {
	background-color: #c7e7e8;
}
.hhmm{
			width: 100%;
		}
</style>



<script>
	$(document).ready(function() {

		$("#fromdate").datepicker({

			dateFormat : 'dd-mm-yy',
			yearRange: yearrange,
			minDate : '30-12-1880',
			changeMonth : true,
			changeYear : true

		});

		$("#todate").datepicker({

			dateFormat : 'dd-mm-yy',
			yearRange: yearrange,
			minDate : '30-12-1880',
			changeMonth : true,
			changeYear : true

		});
		
		$("#dischargedate").datepicker({

			dateFormat : 'dd-mm-yy',
			yearRange : yearrange,
			minDate : '30-12-1880',
			changeMonth : true,
			changeYear : true

		});
		
		
		
		

	});

	function printExcel() {

		$(".xlstable").table2excel({
			exclude : ".noExl",
			name : "OPD TAT Report",
			filename : "DailyRegistrationReport",
			fileext : ".xls",
			exclude_img : true,
			exclude_links : true,
			exclude_inputs : true
		});
	}
</script>


<%
	LoginInfo loginfo = LoginHelper.getLoginInfo(request);
%>
<div class="reportprint">




	<div class="">


		<div
			class="col-lg-12 col-md-12 col-xs-12 col-sm-12 hidden-lg hidden-md visible-print">
			<div class="row letterheadhgt" style="height: 135px;">
				<div id="newpost"
					class="col-lg-12 col-md-12 col-xs-12 col-sm-12 borderbot">
					<link href="common/css/printpreview.css" rel="stylesheet" />
					<%@ include file="/accounts/pages/letterhead.jsp"%>
				</div>
			</div>
		</div>

		<div class="row">
			<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12"
				style="background-color: #41cfd6;">
				<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12"
					style="display: -webkit-inline-box; padding: 0px;">
					<h4 style="font-size: 17px; font-weight: 600; color: white">Patient Daily Transaction Summary
						</h4>
				</div>
			</div>
		</div>
		<div class="row ">
			<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12">
				<div>
					<s:form action="patienttransactionrptIpdDashboard" id="deptfrm" theme="simple">
						<s:hidden name="clientId" id="clientId"></s:hidden>
						<s:hidden name="IpdpatientType" value="0"/>
						<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12 topback2  hidden-print"
							style="display: flex; flex-wrap: wrap;">
						<div class="col-lg-2 col-md-2" style="margin-left: -19px;margin-top: 20px;">
                                       <s:textfield  name="Searchtext" id="SearchText" 
				                             cssClass="form-control" placeholder="Search by ClientId Name and UHID"></s:textfield>			
				    
		                    </div>
							
							<div class="col-lg-2 col-md-2 col-xs-12 col-sm-12">
								<label>From Date : </label>
								<s:textfield readonly="true" name="fromdate" id="fromdate"
									cssClass="form-control" theme="simple" placeholder="From date"></s:textfield>
							</div>
							<div class="col-lg-2 col-md-2 col-xs-12 col-sm-12">
								<label>To Date : </label>
								<s:textfield readonly="true" name="todate" id="todate"
									cssClass="form-control" theme="simple" placeholder="To date"></s:textfield>
							</div>
							
							<div class="col-lg-2 col-md-2">
		                        <label>Payments Type</label> 		                           
                                    <s:select list="#{'1':'Advance', '2':'Outstanding', '3':'Paid'}"  name="paymentid" id="paymentid" headerKey="0" headerValue="All" cssClass="form-control chosen-select"></s:select>	                   
                            </div> 
							
							
						 <div class="col-lg-2 col-md-2 col-xs-12 col-sm-12">
								<label></label><br> 
									<input type="submit" value="Go" class="btn btn-primary active" /> 
									<a type="button" class="btn btn-primary" title="Print" onclick="printpage()">
									<i class="fa fa-print"></i></a> 
									<a type="button" id="btnxls" title="Download Excel" onclick="printExcel()"
									class="btn btn-primary"><i class="fa fa-file-excel-o"></i></a>
							</div>

						</div>
					</s:form>
					<br />
					
					
		
                    <%
						int i = 1;
					%>

					<div class="row">
						<div class="col-lg-12">
							<div class="table-responsive">
								<table
									class="table xlstable table-hover table-condensed table-bordered">
									<thead style="text-align: center;">


										<tr>
											<td>Serial No</td>
											<td>Patient Name</td>
											<td>DateTime</td>
											<td>Advance</td>
											<td>Outstanding</td>
											<td>Paid</td>
											
										</tr>

									</thead>
									<tbody style="text-align: center;" >

										<s:iterator value="patienttransactionlist">
											<tr>
											<td><%=i++%></td>
											<td><s:property value="name" /></td>
									        <td><s:property value="date" /></td>
									        <td style="color: green;"><s:property value="advance" /></td>
											<td style="color: red;"><s:property value="balance" /></td>
											<s:if test="advance==0.0 && balance==0.0">
											 <td>0</td>
											</s:if>
											<s:else>
											 <td><s:property value="" />-</td>
											</s:else>
										   
										<s:if test="ischeck==0 ">
 											<td  style="vertical-align: middle;"><a href="#" onclick="editdate(<s:property value="Clientid"/>,<s:property value="id"/>)"><s:property value="DischargeDate" /></a></td>
 										</s:if>
 										<s:else>
 										    <td><s:property value="DischargeDate" /></td>
 										</s:else>
											                            
											</tr>
										</s:iterator>
									</tbody>
								</table>
							</div>
						</div>
					</div>




				</div>
			</div>
		</div>
	</div>

</div>


 
   

<script src="common/chosen_v1.1.0/chosen.jquery.js"
	type="text/javascript"></script>
<script type="text/javascript">
	var config = {
		'.chosen-select' : {},
		'.chosen-select-deselect' : {
			allow_single_deselect : true
		},
		'.chosen-select-no-single' : {
			disable_search_threshold : 10
		},
		'.chosen-select-no-results' : {
			no_results_text : 'Oops, nothing found!'
		},
		'.chosen-select-width' : {
			width : "95%"
		}
	}
	for ( var selector in config) {
		$(selector).chosen(config[selector]);
	}
</script>
