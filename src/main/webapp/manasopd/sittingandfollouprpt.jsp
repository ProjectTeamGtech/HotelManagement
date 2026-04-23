
<%@page import="com.apm.common.web.common.helper.LoginHelper"%>
<%@page import="com.apm.common.web.common.helper.LoginInfo"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<script type="text/javascript" src="diarymanagement/js/finder.js"></script>

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
			name : "Sitting And Follow Up",
			filename : "Sitting_And_Follow_Up",
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
					<h4 style="font-size: 17px; font-weight: 600; color: white">Sitting And Follow Up Report</h4>
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
				<span class="error"><s:actionerror id="server_side_error" /></span>
				<s:form action="sittingandfollouprptChargesRpt" id="deptfrm" theme="simple">
					<s:hidden name="clientId" id="clientId"></s:hidden>
					<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12 topback2  hidden-print"
							style="display: flex; flex-wrap: wrap;">
							<div class="col-lg-1 col-md-1 col-xs-12 col-sm-12">
								<label>Report Type</label>
								<s:select list="#{'0':'Sitting','1':'Follow Up' }"
									cssClass="form-control chosen-select"
									name="sittingOrFollowUp"></s:select>
							</div>
							
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
							<div class="col-lg-2 col-md-2 col-xs-12 col-sm-12">
								<label>Patient Category : </label>
								<s:select cssClass="form-control showToolTip chosen-select"
									id="patientcategory" name="patientCategory"
									list="thirdPartyTypeNameList" listKey="thirdPartyCompanyName"
									listValue="thirdPartyCompanyName" headerKey="" theme="simple"
									headerValue="Select Category" />
							</div>
							<div  class="col-lg-2 col-md-2 col-xs-12 col-sm-12">
								<label>Department</label>
								<%-- <s:select list="disciplineList" name='dept' listKey="id" listValue="discipline" headerKey="" headerValue="All Department" cssClass="form-control chosen-select"></s:select> --%>
								<%
									if (loginInfo.getSuperadminid() == 1 || loginInfo.getUserType() == 2
												|| !loginInfo.getShow_dept_opd_list().equals("0")) {
								%>
								<s:select list="departmentList"
									onchange="getSittingListFromDepartment(this.value)" name='departmentName' listKey="id"
									listValue="discipline" headerKey=""
									headerValue="Select Department"
									cssClass="form-control chosen-select"></s:select>

								<%
									} else {
								%>
								<s:select list="departmentList" disabled="true" name='departmentName'
									listKey="id" listValue="discipline" headerKey=""
									headerValue="Select Department"
									cssClass="form-control chosen-select"></s:select>

								<%
									}
								%>
							</div>
							<div class="col-lg-1 col-md-2 col-xs-12 col-sm-12" id="sittingDiv">
								<label>Sitting</label>
								<s:select list="sittingList" onchange='setProcedureMasterList(this.value)'
									name='sittingName' listKey="id"
									listValue="SittingFollowup" headerKey=""
									headerValue="Select Sitting"
									cssClass="form-control chosen-select"></s:select>
							</div>
							<div class="col-lg-2 col-md-2 col-xs-12 col-sm-12" id="procedureMasterDiv">
								<label>Procedure Master</label>
								<s:select list="procedureMasterList" onchange='setProcedureList(this.value)'
									name='proceduerMasterName' listKey="id"
									listValue="name" headerKey=""
									headerValue="Select Procedure Master"
									cssClass="form-control chosen-select"></s:select>
							</div>
							<div class="col-lg-2 col-md-2 col-xs-12 col-sm-12" id="procedureDiv">
								<label>Procedure Name</label>
								<s:select list="procedureList" 
									name='procedureName' listKey="id"
									listValue="name" headerKey=""
									headerValue="Select Procedure"
									cssClass="form-control chosen-select"></s:select>
							</div>
							<!-- For Physio Ipd -->
							<div class="col-lg-1 col-md-1 col-xs-12 col-sm-12">
								<label>Type</label>
								<s:select list="#{'0':'Opd','1':'Ipd' }"
									cssClass="form-control chosen-select"
									name="ipdoropd"></s:select>
							</div>
							<div class="col-lg-2 col-md-2 col-xs-12 col-sm-12">
								<label></label><br> <input type="submit" value="Go"
									class="btn btn-primary active" /> <a type="button"
									class="btn btn-primary" title="Print" onclick="printpage()"><i
									class="fa fa-print"></i></a> <a type="button" id="btnxls"
									title="Download Excel" onclick="printExcel()"
									class="btn btn-primary"><i class="fa fa-file-excel-o"></i></a>
							</div>
							
							<div class="col-lg-2 col-md-2 col-xs-12 col-sm-12" style="padding-top: 22px; text-align: right; margin-right: 22px;">
			                    <label>Total Count of Procedure</label> :- <s:property value="Procedurecount"/>
		                    </div>
							<div class="col-lg-2 col-md-2 col-xs-12 col-sm-12">
							
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
											<td>Sr No</td>
											<td>UHID</td>
											<td>Patient Name</td>
											<td>Patient Type</td>
											<td>Age/Gender</td>
											<td>Contact No</td>
											<td>Address</td>
											
											<td>Department</td>
											<td>Sitting</td>
											<td>Procedure Master Name</td>
											<td>Procedure Name</td>
											<td>Sitting No</td>
											<td>Remark</td>
											<%if(loginfo.isPhysio()){ %>
											<td>Diagnosis</td>
											<%} %>
											<td>Date and Time</td>
											<td>Follow Up Date</td>
										</tr>
									</thead>
									<tbody style="text-align: center;" >

										<s:iterator value="sittingAndFolloUpList">
											<tr>
												<td><%=i++%></td>
												<td><s:property value="abrivationid" /></td>
												<td><s:property value="clientName" /></td>
												<td><s:property value="Patientcategory" /></td>
												<td><s:property value="agegender" /></td>
												<td><s:property value="mobno" /></td>
												<td><s:property value="address" /></td>
												
												<td><s:property value="disciplineName" /></td>
												<td><s:property value="sittingName" /></td>
												<td><s:property value="procedureMasterName" /></td>
												<td><s:property value="procedureName" /></td>
												<td><s:property value="Sittingnum" /></td>
												<td><s:property value="remark" /></td>
												<%if(loginfo.isPhysio()){ %>
												<td><s:property value="diagnosisarea"/></td>
												<%} %>
												<td><s:property value="datetime" /></td>
												<td><s:property value="followupDate" /></td>
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

<s:form action="sittingandfollouprptChargesRpt" name="paginationForm"
		id="paginationForm" theme="simple">
		<s:hidden name="sittingOrFollowUp"></s:hidden>
		<s:hidden name="fromDate"></s:hidden>
		<s:hidden name="toDate"></s:hidden>
		<s:hidden name="departmentName"></s:hidden>
		<s:hidden name="patientCategory"></s:hidden>
		<s:hidden name="patienttype"></s:hidden>
		<div class="col-lg-12 col-md-12 hidden-print"
			style="margin-top: 15px;">
			<div class="col-lg-4 col-md-4 text-left" style="padding: 0px;">
				Total:<label class="text-info"><s:property
						value="totalRecords" /></label>
			</div>
			<jsp:include page="/common/pages/pagination.jsp"></jsp:include>
		</div>
	</s:form>
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
