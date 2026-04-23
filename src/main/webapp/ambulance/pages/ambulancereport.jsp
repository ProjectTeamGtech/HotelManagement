
<%@page import="com.apm.common.web.common.helper.LoginHelper"%>
<%@page import="com.apm.common.web.common.helper.LoginInfo"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<script type="text/javascript" src=""></script>

<script src="notification/js/bootstrap-select.js"></script>
<link href="diarymanagement/css/popupstyle.css" rel="stylesheet"
	type="text/css" />
<link href="diarymanagement/css/subModal.css" rel="stylesheet"
	type="text/css" />
<script type="text/javascript"
	src="assesmentForms/js/jquery.table2excel.js"></script>
<script type="text/javascript" src="accounts/js/printpreview.js"></script>
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

@media (max-device-width: 480px) {
	.mobile-bottom-padding{
		padding-bottom: 12px;
	}
	.mobile-bottom-margin{
		margin-bottom: 5px;
	}
}
</style>



<script>
	$(document).ready(function() {

		$("#fromDate").datepicker({

			dateFormat : 'dd-mm-yy',
			yearRange : yearrange,
			minDate : '30-12-1880',
			changeMonth : true,
			changeYear : true

		});

		$("#toDate").datepicker({

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
			name : "PRO Patients",
			filename : "PROPatientsReport",
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
		<div class="row">
			<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12"
				style="background-color: #41cfd6;">
				<div class="col-lg-8 col-md-8 col-sm-12 col-xs-12"
					style="display: -webkit-inline-box; padding: 0px;">
					<h4 style="font-size: 17px; font-weight: 600; color: white">Ambulance Report</h4>
				</div>
		
			</div>
		</div>
		<div class="row ">
			<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12">
				<div>


					<div class="col-lg-12 col-md-12">
						<s:hidden name="message" id="message"></s:hidden>
						<s:if test="hasActionMessages()">
							<script>
								var msg = " "
										+ document.getElementById('message').value;
								showGrowl('', msg, 'success', 'fa fa-check');
							</script>



						</s:if>
					</div>




					
					<s:form action="ambulancereportDuty">
						<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12 topback2  hidden-print"
							style="display: flex; flex-wrap: wrap;z-index: 99">
							<img src="manasmaindashboard/images/Manas_Yuvicare_Logo.svg" style="height: 56px;width: 89px;">

							<div class="col-lg-1 col-md-1 col-xs-12 col-sm-12">
								<label>From Date : </label>

								<s:textfield readonly="true" name="fromDate" id="fromDate"
									cssClass="form-control" theme="simple" placeholder="From date"></s:textfield>
							</div>

							<div class="col-lg-1 col-md-1 col-xs-12 col-sm-12">
								<label>To Date : </label>
								<s:textfield readonly="true" name="toDate" id="toDate"
									cssClass="form-control" theme="simple" placeholder="To date"></s:textfield>

							</div>


							<div class="col-lg-2 col-md-2 col-xs-12 col-sm-12 ">
								<label>Search by Driver Name : </label>
								<s:textfield theme="simple" name="searchText"
									placeholder="Search By name" cssClass="form-control" />
							</div>
							<div class="col-lg-2 col-md-2 col-xs-12 col-sm-12">
								<label></label><br> <input type="submit" value="Go"
									class="btn btn-primary active" /> <a type="button"
									class="btn btn-primary" title="Print" onclick="printpage()"><i
									class="fa fa-print"></i></a> <a type="button" id="btnxls"
									title="Download Excel" onclick="printExcel()"
									class="btn btn-primary"><i class="fa fa-file-excel-o"></i></a>
							</div>
							</div>
							</s:form>
							
<div class="row">
<div class="col-lg-12">
<div class="">
<table id="results"
	class="table table-hove table-bordered table-striped table-condensed">
	<thead>
		<tr>
			<th class="text-center">ID</th>
			<th class="text-center">Patient Name</th>
			<th class="text-center">City</th>
			<th class="text-center">Drop to</th>
			<th class="text-center">Hospital Name</th>
			<th class="text-center">Driver Name</th>
			
		</tr>
	</thead>
	 <tbody>
		<s:if test="ambreportlist!=null">
			<s:iterator value="ambreportlist" status="rowstatus">
				<tr>
					<td class="text-center"><s:property value="id" /></td>
					<td class="text-center"><s:property value="patient" /></td>
					<td class="text-center"><s:property value="city" /></td>
					<td class="text-center"><s:property value="pickdrop"/></td>
				    <td class="text-center"><s:property value="hosp"/></td>
				    <td class="text-center"><s:property value="drivername"/></td> 
				    
					
					
				</tr>
			</s:iterator>
		</s:if>
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
							