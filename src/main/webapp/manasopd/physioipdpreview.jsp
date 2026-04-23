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
			name : "OPD TAT Report",
			filename : "DepartmentOPDPreview",
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
					<h4 style="font-size: 17px; font-weight: 600; color: white">Department
						IPD Preview</h4>
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



					<input type="hidden" id="invoicee" name="invoicee" /> <input
						type="hidden" id="commencing" name="commencing" /> <input
						type="hidden" id="caldate" name="caldate" /> <input type="hidden"
						id='setp'>
					<s:form action="physioipdpreviewFinder" id="deptfrm" theme="simple">
						<s:hidden name="clientId" id="clientId"></s:hidden>
						<div
							class="col-lg-12 col-md-12 col-xs-12 col-sm-12 topback2  "
							style="display: flex; flex-wrap: wrap;">


							<div class="col-lg-1 col-md-1 col-xs-12 col-sm-12">
								<label>From Date : </label>
								<%-- <s:textfield readonly="true" name="fromDate" id="fromDate" placeholder="Search by Date"
				cssClass="form-control" theme="simple" onchange="document.getElementById('deptfrm').submit()"></s:textfield> --%>
								<s:textfield readonly="true" name="fromDate" id="fromDate"
									cssClass="form-control" theme="simple" placeholder="From date"></s:textfield>
							</div>

							<div class="col-lg-1 col-md-1 col-xs-12 col-sm-12">
								<label>To Date : </label>
								<s:textfield readonly="true" name="toDate" id="toDate"
									cssClass="form-control" theme="simple" placeholder="To date"></s:textfield>

							</div>
                             <div class="hidden-print">
							<div class="col-lg-2 col-md-2 col-xs-12 col-sm-12">
								<label>Department List</label>
								<%-- <s:select list="disciplineList" name='dept' listKey="id" listValue="discipline" headerKey="" headerValue="All Department" cssClass="form-control chosen-select"></s:select> --%>
								<%
									if (loginInfo.getSuperadminid() == 1 || loginInfo.getUserType() == 2
												|| !loginInfo.getShow_dept_opd_list().equals("0")) {
								%>
								<s:select list="disciplineList"
									onchange="getprimarylist(this.value)" name='dept' listKey="id"
									listValue="discipline" headerKey=""
									headerValue="All Department"
									cssClass="form-control chosen-select"></s:select>

								<%
									} else {
								%>
								<s:select list="disciplineList" disabled="true" name='dept'
									listKey="id" listValue="discipline" headerKey=""
									headerValue="All Department"
									cssClass="form-control chosen-select"></s:select>

								<%
									}
								%>
							</div>
							<div class="col-lg-2 col-md-2 col-xs-12 col-sm-12">
								<label>Primary Doctor : </label>
								<div id="primarydiv">
									<s:select list="userList" theme="simple" listKey="id"
										listValue="diaryUser" cssClass="form-control chosen-select"
										headerKey="" headerValue="Select Doctor" name="diaryUser"
										id="diaryUser" />
								</div>
							</div>
							<div class="col-lg-2 col-md-2 col-xs-12 col-sm-12">
								<label>Secondary Doctor : </label>
								<div id="secondarydiv">
									<s:select list="secondaryuserList" theme="simple" listKey="id"
										listValue="diaryUser" cssClass="form-control chosen-select"
										headerKey="" headerValue="Select Doctor" name="secondarydoc"
										id="secondarydoc" />
								</div>
							</div>

							<div class="col-lg-2 col-md-2 col-xs-12 col-sm-12">
								<label>Refer To Department List</label>
								<s:select list="disciplineList" name='referto' listKey="id"
									listValue="discipline" headerKey=""
									headerValue="All Department"
									cssClass="form-control chosen-select"></s:select>
							</div>
							<%-- <div class="col-lg-2 col-md-2 col-xs-12 col-sm-12">
		<label>Payer Type</label>
		<s:select id="type"  name="type" list="thirdPartyTypeList" listKey="id" listValue="type" headerValue="Select Payer Type" 
			headerKey="0"  cssClass="form-control showToolTip chosen" data-toggle="tooltip" onchange="setTPName(this.value)" />
		 </div> --%>
							<div class="col-lg-2 col-md-2 col-xs-12 col-sm-12">
								<label>Patient Category : </label>
								<s:select cssClass="form-control showToolTip chosen-select"
									id="patientcategory" name="patientcategory"
									list="thirdPartyTypeNameList" listKey="thirdPartyCompanyName"
									listValue="thirdPartyCompanyName" headerKey="" theme="simple"
									headerValue="Select Category" />
							</div>
							<div class="col-lg-2 col-md-2 col-xs-12 col-sm-12">
								<label>Patient Type : </label>
								<s:select list="#{'1':'NEW','2':'OLD'}" id="patienttype"
									headerKey="" headerValue="Select Patient Type"
									name="patienttype"
									cssClass="form-control showToolTip chosen-select"></s:select>
							</div>
							<div class="col-lg-2 col-md-2 col-xs-12 col-sm-12">
								<label>Order by : </label>
								<s:select list="#{'desc':'Descending','asc':'Ascending'}"
									id="orderby" name="orderby" cssClass="form-control"></s:select>
							</div>
							<div class="col-lg-2 col-md-2 col-xs-12 col-sm-12">
								<label>Search by Patient Name : </label>
								<s:textfield theme="simple" name="searchText" placeholder="Search By name"  cssClass="form-control" />
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
						</div>
					</s:form>
					<br />
					<%-- <%
						int i = 1;
					%> --%>

					<div class="row">
						<div class="col-lg-12">
							<div class="table-responsive">
							  <s:iterator value="DepartmentIPDList">
									<td><h1><b><s:property value="Departmentname" /></b></h1></td>
								<table
									class="table xlstable table-hover table-condensed table-bordered">
									<thead style="text-align: center;">


										<tr>
											<td>Serial No</td>
											<td>Booking Date</td>
											<td>U H I D</td>
											<td>Patient Name</td>
											<td>Patient Type</td>
											<td>Age/Gender</td>
											<td>Contact No</td>
											<%if(loginInfo.isAmravati()){ %>
											<td>Address</td>
											<%} %>
											<%if(!loginInfo.isAmravati()){ %>
											<td>Patient Category</td>
											<%} %>
											<!-- <td>Camp Area</td>
											<td>Enrollment No</td> -->
											<td>Allotted Department</td>
											<td>Assign Doctor</td>
											<!-- <td>Secondary Doctor</td>
											<td>Refer To Department</td> -->
										</tr>

									</thead>
									<tbody style="text-align: center;" id="newopdbodyid">
									  <%
						                int i = 1;
					                  %>
										<s:iterator value="DepartmentIPDPreviewList">
											<s:if test="diaryUserId==1">
												<tr>
											</s:if>
											<s:else>
												<tr style="background-color: #f5c286;">
											</s:else>


											<td><%=i++%></td>
											<td><s:property value="datetime" /></td>
											<td><s:property value="abrivationid" /></td>
											<td><s:property value="clientName" /></td>
											<td><s:if test="patienttype==1">
												NEW
												</s:if> 
												<s:else>
													OLD
												</s:else>
											</td>
											<td><s:property value="agegender" /></td>
											<td><s:property value="mobno" /></td>
											<%if(loginInfo.isAmravati()) {%>
											<td><s:property value="Address" /></td>
											<%} %>
											<%if(!loginInfo.isAmravati()) {%>
											<td><s:property value="patientcategory" /></td>
											<%} %>
											<%-- <td><s:property value="campArea" /></td>
											<td><s:property value="enrollcode" /></td> --%>
											<td><s:property value="departmentname" /></td>
											<td><s:if test="diaryUserId==1">
											Doctor Not Assign
										</s:if> <s:else>
													<s:property value="diaryUser" />
												</s:else></td>
											<%-- <td><s:iterator value="secondarylist">
													<span><s:property value="secondarydr" /></span>
													<br>

												</s:iterator></td> --%>
												<%-- <td><s:property value="Secondarydoc" /></td>
											<td><s:iterator value="deptlist">
													<span><s:property value="departmentname" /></span> --%>
													

												<%-- </s:iterator></td> --%>
											</tr>
										</s:iterator>
									</tbody>
								     </table>
							   </s:iterator>
							</div>
						</div>
					</div>




				</div>
			</div>
		</div>
	</div>





	<%-- <s:form action="lmhopdpreviewFinder" name="paginationForm"
		id="paginationForm" theme="simple">

		<s:hidden name="fromDate"></s:hidden>
		<s:hidden name="toDate"></s:hidden>
		<s:hidden name="dept"></s:hidden>
		<s:hidden name="patientcategory"></s:hidden>
		<s:hidden name="patienttype"></s:hidden>
		<div class="col-lg-12 col-md-12 hidden-print"
			style="margin-top: 15px;">
			<div class="col-lg-4 col-md-4 text-left" style="padding: 0px;">
				Total:<label class="text-info"><s:property
						value="totalRecords" /></label>
			</div>
			<jsp:include page="/common/pages/pagination.jsp"></jsp:include>
		</div>
	</s:form> --%>
</div>

<div class="col-lg-12 col-md-12 hidden-print"
			style="margin-top: 15px;">
			<div class="col-lg-4 col-md-4 text-left" style="padding: 0px;">
				Total:<label class="text-info"><s:property
						value="totalRecords" /></label>
			</div>
			<%-- <jsp:include page="/common/pages/pagination.jsp"></jsp:include> --%>
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





