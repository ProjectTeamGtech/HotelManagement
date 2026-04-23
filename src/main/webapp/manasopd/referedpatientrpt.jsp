
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
		<%-- <div
			class="col-lg-12 col-md-12 col-xs-12 col-sm-12 hidden-lg hidden-md visible-print">
			<div class="row letterheadhgt" style="height: 135px;">
				<div id="newpost"
					class="col-lg-12 col-md-12 col-xs-12 col-sm-12 borderbot">
					<link href="common/css/printpreview.css" rel="stylesheet" />
					<%@ include file="/accounts/pages/letterhead.jsp"%>
				</div>
			</div>
		</div> --%>

		<div class="row">
			<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12"
				style="background-color: #41cfd6;">
				<div class="col-lg-8 col-md-8 col-sm-12 col-xs-12"
					style="display: -webkit-inline-box; padding: 0px;">
					<h4 style="font-size: 17px; font-weight: 600; color: white">Patient
						Refer Dashboard (PRO)</h4>
				</div>
				<%
					if ((loginfo.isSmallClinic() && loginInfo.getSuperadminid() != 1) || !loginfo.isReferHospital()) {
				%>
				<div class="col-lg-4 col-md-4 col-sm-12 col-xs-12 clearfix">
					<ul class="notification-area pull-right">
						<li>
							<div class="dropdown show" style="margin-left: 0px;">
								<a class="btn btn-danger dropdown-toggle" href="#" role="button"
									id="dropdownMenuLink" data-toggle="dropdown"
									aria-haspopup="true" aria-expanded="false"> 
									<%
									 	if (loginInfo.getSuperadminid() == 1) {
									 %>
									Welcome, <%=loginInfo.getFirstName()%> <i
									class="fa fa-angle-down"></i> <%
 									} else {
 									%> Hi, <%=loginInfo.getFirstName()%>
										<i class="fa fa-angle-down"></i> 
									<%
									 	}
									 %>

								</a>

								<div class="dropdown-menu" aria-labelledby="dropdownMenuLink"
									style="min-width: 5rem !important">
									<%
										if (loginInfo.getSuperadminid() == 1) {
									%>
									<a class="dropdown-item" href="logoutwithsessionLogout"><i
										class="fa fa-sign-out"></i>&nbsp;Logout</a>
									<%
										} else {
									%>
									<!-- <a class="dropdown-item" href="#"
										onclick="opencPopup('settingMainDashBoard')"><i
										class="fa fa-user"></i>&nbsp;Profile</a> -->
									<%
										if (!loginInfo.getLoginType().equals("mob")) {
									%>
									<%
										if (loginInfo.isLmh()) {
									%>
									<a class="dropdown-item" href="logoutwithsessionLogout"><i
										class="fa fa-sign-out"></i>&nbsp;Logout</a>

									<%
										} else {
									%>

									<a class="dropdown-item" href="Logout"><i
										class="fa fa-sign-out"></i>&nbsp;Logout</a>

									<%
										}
									%>
									<%
										}
									%>
									<%
										}
									%>
									<!-- <a class="dropdown-item" href="#">Something else here</a> -->
								</div>

							</div>
						</li>
					</ul>
				</div>
				<%
					}
				%>
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




					
					<s:form action="referedpatientreportFinder" id="deptfrm">
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


							<div class="col-lg-2 col-md-2 col-xs-12 col-sm-12 hidden">
								<label>Search by Patient Name : </label>
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
							<div class="col-lg-6 col-md-6 col-xs-12 col-sm-12">
								<label></label><br> 
									<%if(loginfo.isSmallClinic()){ %>
										<a type="button" 
									 		onclick="showReferPatientPopUp()"
											class="btn btn-primary mobile-bottom-margin">Refer Patient</a>
										<s:if test="isAccnotPresent==1">
											<a href="#" onclick="openViewAccount(0,'<%=loginInfo.getClinicUserid() %>')" class="btn btn-primary mobile-bottom-margin">View Account Details</a>
										</s:if>
										<s:else>
											<a href="#" onclick="openViewAccount(0,'<%=loginInfo.getClinicUserid() %>')" class="btn btn-primary mobile-bottom-margin">Add Account Details</a>
										</s:else>
										<a href="#" data-toggle="modal" onclick="showClinicHospitalComercial(1)" class="btn btn-primary mobile-bottom-margin">Commercial Setup</a>
									<%} %>
									<%if(loginfo.isCommon_db_clinic()){ %>
										<a href="calNotAvailableSlot?actionType=newopd" class="btn btn-primary mobile-bottom-margin">Opd Dashboard</a>
									<%} %>
									<a href="#" data-toggle="modal" data-target="#suportPopup" class="btn btn-primary mobile-bottom-margin">Support</a>
									<a href="#" data-toggle="modal" data-target="#offersPopup" class="btn btn-primary mobile-bottom-margin">Offers <span style="color:red;">(<s:property value="offerCount"/>)</span></a>
									
									
							
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
											<td>Sr. No</td>
											<td>Patient Name</td>
											<td>Age / Gender</td>
											<td>Address</td>
											<td>City</td>
											<td>Clinic Name</td>
											<td hidden>Referred By</td>
											<td>Referred Userid</td>
											<td>Referred hospital - City - Commercial</td>
											<td>Referred date</td>
											<!-- <td>Commercial</td> -->
											<td>Confirm</td>
											<td>Patient Status</td>
											<td>Patient Disease</td>
											<td>Payment Status</td>
											<td>Cancel</td>
											<%if(!loginfo.isSmallClinic()){ %>
											<td class="hidden-print">Account</td>
											<%} %>
										</tr>
									</thead>
									<tbody style="text-align: center;">
										<s:iterator value="Referedpatientlist">
											<tr>
												<td><%=i++%></td>
												<td><s:property value="ClientName" /></td>
												<td><s:property value="agegender" /></td>
												<td><s:property value="address" /></td>
												<td><s:property value="city" /></td>
												<td><s:property value="smallClinicName" /></td>
												<td hidden><s:property value="clinic_refer_userid" /></td>
												<td><s:property value="Referuserid" /></td>
												<td><s:property value="Referetohospital" /> - <s:property
														value="referCity" />
														 - <s:property value="commercial" /></td>
												<td><s:property value="Referdate" /></td>
												<%-- <td><s:property value="commercial" /></td> --%>
												<td>
													<%if(!loginfo.isSmallClinic()){ %> 
														<s:if test="deleted==0">
															<s:if test="confirm==1">
																<label style="color: green">Confirmed</label>
															</s:if>
															<s:else>
																<a href="#"
																	onclick="confirmReferPatient(<s:property value="id"/>)"
																	class="btn btn-primary">Confirm</a>
															</s:else>
														</s:if> 
													<%}else{ %> 
														<s:if test="deleted==0">
															<s:if test="confirm==1">
																<label style="color: green">The patient has arrived at the Hospital.</label>
															</s:if> 
															<s:else>
																<p style="color: red;">You will be informed shortly.</p>
															</s:else>
														</s:if>
														
													<%} %>
												</td>

												<td>
													<%if(!loginfo.isSmallClinic()){ %> <s:if test="deleted==0">
														<s:if test="confirm==1">
															<s:if test="patient_status>0">
																<s:property value="patient_status_name" />
															</s:if>
															<s:else>
																<a href="#"
																	onclick="referPatientStatus(<s:property value="id"/>)"
																	class="btn btn-primary">Status</a>
															</s:else>
														</s:if>
													</s:if> <%}else{ %> <s:if test="patient_status>0">
														<s:property value="patient_status_name" />
													</s:if> <%} %>
												</td>
												
												<%if(loginfo.isSmallClinic()){ %>
	                                                    <td><a href="#" onclick="editdisease(<s:property value="id"/>)"><s:property value="Disease" /></a></td>
										         <%}else{ %>
												       <td><s:property value="Disease" /></td>
												<%} %>
												<td>
													<%if(!loginfo.isSmallClinic()){ %> <s:if
														test="patient_status>0">
														<s:if test="paid_status==1">
															<label style="color: green">Paid</label>
															<br>
																Total Bill Amount: Rs.<s:property value="total_bill_amt" />
															<br>
																Clinic Paid Amount: Rs.<s:property
																value="paid_clinic_amt" />
															<br>
																Consultant Paid Amount: Rs.<s:property
																value="paid_consulatant_amt" />
														</s:if>
														<s:else>
															<a href="#"
																onclick="referPatientPayment(<s:property value="id"/>,<s:property value="clinicShare" />,<s:property value="consultantShare"/>)"
																class="btn btn-primary">Pay</a>
														</s:else>
													</s:if> <%}else{ %> <s:if test="paid_status==1">
														<label style="color: green">Paid</label>
														<br>
															Paid Amount: Rs.<s:property value="paid_clinic_amt" />
														<br>
													</s:if> <%} %>
												</td>
												<td><s:if test="deleted==1">
														<label style="color: red">Cancelled</label>
													</s:if> <s:else>
														<s:if test="confirm==0">
															<%if(loginfo.isSmallClinic()){ %>
															<a href="#"
																onclick="cancelReferPatient(<s:property value="id"/>)"><i
																class="fa fa-trash text-danger"></i></a>
															<%} %>
														</s:if>
													</s:else></td>
												<%if(!loginfo.isSmallClinic()){ %>
												<td class="hidden-print"><a href="#"
													onclick="openViewAccount('<s:property value="clinic_refer_userid" />','<s:property value="Sourceclinic_id"/>')"
													class="btn btn-primary">View</a></td>
												<%} %>

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


<!-- Modal -->
<div class="modal fade" id="clientSearchDiv" tabindex="-1" role="dialog"
	aria-labelledby="myModalLabel" aria-hidden="true">
	<div class="modal-dialog modal-lg">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal">
					<span aria-hidden="true">&times;</span><span class="sr-only">Close</span>
				</button>
				<h4 class="modal-title" id="myModalLabel">Patient Search</h4>
			</div>
			<div class="modal-body">
				<%@ include file="/ipd/pages/ipdPatientList.jsp"%>
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-primary" data-dismiss="modal">Close</button>
			</div>
		</div>
	</div>
</div>

<!-- Add New Patient -->
<div class="modal fade" id="addPatientDiv" tabindex="-1" role="dialog"
	aria-labelledby="myModalLabel" aria-hidden="true">
	<div class="modal-dialog modal-lg">
		<div class="modal-content">
			<div class="modal-header bg-primary">
				<button type="button" class="close" data-dismiss="modal">
					<span aria-hidden="true">&times;</span><span class="sr-only">Close</span>
				</button>
				<h4 class="modal-title" id="myModalLabel">Add New Patient</h4>
			</div>
			<div class="modal-body addnewpat">
				<%@ include file="/diarymanagement/pages/addPatientPage.jsp"%>

			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-primary"
					onclick="return savePatient()">Save</button>
				<button type="button" class="btn btn-primary hidden"
					data-dismiss="modal">Close</button>
			</div>
		</div>
	</div>
</div>




<!-- Add Treatment Episode -->
<div class="modal fade" id="addTreatmentEpisodeDiv" tabindex="-1"
	role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
	<div class="modal-dialog modal-lg">
		<div class="modal-content">
			<div class="modal-header bg-primary">
				<button type="button" class="close" data-dismiss="modal">
					<span aria-hidden="true">&times;</span><span class="sr-only">Close</span>
				</button>
				<h4 class="modal-title" id="myModalLabel">Create Treatment
					Episode</h4>
			</div>
			<div class="modal-body">
				<%@ include file="/treatmentEpisode/pages/addTreatmentEpisode.jsp"%>
			</div>
			<div class="modal-footer">

				<button type="button" class="btn btn-primary disblebtnone"
					onclick="saveTreatment();">Save</button>
				<button type="button" class="btn btn-primary" data-dismiss="modal">Close</button>
			</div>
		</div>
	</div>
</div>
<div id="referpatient" class="modal fade" role="dialog">

	<div class="modal-dialog modal-sm">

		<!-- Modal content-->
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal">&times;</button>
				<h4 class="modal-title">Refer To</h4>
			</div>
			<div class="modal-body">
				<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12">
					<s:hidden id="clientid"></s:hidden>
					<div class="col-lg-12 col-md-12 col-xs-12">
						<p style="font-size: 15px; color: green;">Patient Name:</p>
						<p style="font-size: 15px;" id="refer_clientname">Test User</p>
					</div>
					<div class="col-lg-12 col-md-12 col-xs-12">
						<p style="font-size: 15px; color: green;">Abrivation:</p>
						<p style="font-size: 15px;" id="refer_abrivation">BG12345464585</p>
					</div>
					<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12">
						<div class="form-group">
							<label style="font-size: 15px; color: green;">Hospital
								List </label><label class="text-danger">*</label>
							<s:select list="Referhospitallist" name='Hosp_name' id='hname'
								listKey="id" listValue="Hosp_name" headerKey=""
								headerValue="All Hospital" cssClass="form-control chosen-select"></s:select>
						</div>
					</div>
					
				<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12">
      			     <div class="form-group">
			            <label>Patient Disease</label><label class="text-danger">*</label>
				       <s:textfield id="disease" name="disease"
				        cssClass="showToolTip form-control" data-toggle="tooltip"
					   title="Enter Disease " placeholder="Enter Disease "/>
				   </div>
			    </div>
					
		   </div>
			</div>
			<div class="modal-footer">
				<a class="btn btn-primary" href="#" onclick="savereferpatient()">Save</a>
			</div>
		</div>
	</div>
</div>

<!-- Edit Disease -->
<div id="editdisease" class="modal fade" role="dialog">
     <div class="modal-dialog modal-sm">
   <!-- Modal content-->
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal">&times;</button>
				<h4 class="modal-title">Edit Disease</h4>
			</div>
		<div class="modal-body">
			<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12">
					<s:hidden id="editid"></s:hidden>
				<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12">
      			      <div class="form-group">
			              <label>Patient Disease</label><label class="text-danger">*</label>
				             <s:textfield id="edit_disease" name="disease"
				              cssClass="showToolTip form-control" data-toggle="tooltip"
					          title="Enter Disease " placeholder="Enter Disease "/>
				      </div>
			    </div>
		   </div>
	   </div>
			<div class="modal-footer">
				<a class="btn btn-primary" href="#" onclick="updatedisease()">Update</a>
			</div>
		</div>
	</div>
</div>

<!-- Modal -->
<div id="cancelmodel" class="modal fade" role="dialog">
  <div class="modal-dialog modal-sm">

    <!-- Modal content-->
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal">&times;</button>
        <h4 class="modal-title">Are You Sure To Cancel?</h4>
      </div>
      <div class="modal-body">
      		<input type="hidden" id="referParentId">
        	<textarea rows="3"  class="form-control" id="delete_reason" placeholder="Cancel Reason" style="background-color: beige;"></textarea>
      </div>
      <div class="modal-footer">
        <input type="button" class="btn btn-danger" onclick="cancelReferPatient1()"  value="Ok">
      </div>
    </div>

  </div>
</div>

<!-- Modal -->
<div id="patientstatusmodel" class="modal fade" role="dialog">
  <div class="modal-dialog modal-sm">

    <!-- Modal content-->
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal">&times;</button>
        <h4 class="modal-title">Refer Patient Status Modal</h4>
      </div>
      <div class="modal-body">
    		<input type="hidden" id="referParentStatusId">
    		<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12">
    			<s:select list="patientStatusList" id="patientStatus" listKey="id" listValue="name" headerKey="0" headerValue="Select Patient Status"  cssClass="form-control chosen-select"></s:select>
    		</div>
      </div>
      <div class="modal-footer" style="margin-top: 50px;">
        <input type="button" class="btn btn-danger" onclick="referPatientStatus1()"  value="Save">
      </div>
    </div>

  </div>
</div>

<!-- Modal -->
<div id="referPaymentModel" class="modal fade" role="dialog">
  <div class="modal-dialog modal-sm">

    <!-- Modal content-->
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal">&times;</button>
        <h4 class="modal-title">Refer Patient Payment Modal</h4>
      </div>
      <div class="modal-body">
    		<input type="hidden" id="referParentPaymentId">
    		<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12">
    			<label>Total Bill Amount</label>
    			<input type="number" onchange="calculateClinicPayment(this.value)" id="totalBillAmt" class="form-control">
    		</div>
    		<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12">
    			<label>Clinic Share</label>
    			<input type="text" readonly="readonly" id="clinicShare" class="form-control">
    		</div>
    		<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12">
    			<label>Clinic Share Amount</label>
    			<input type="text" readonly="readonly" id="clinicShareAmt" class="form-control">
    		</div>
    		<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12">
    			<label>Consultant Share</label>
    			<input type="text" readonly="readonly" id="consultantShare" class="form-control">
    		</div>
    		<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12">
    			<label>Consultant Share Amount</label>
    			<input type="text" readonly="readonly" id="consultantShareAmt" class="form-control">
    		</div>
    		
      </div>
      <div class="modal-footer" >
        <input type="button" style="margin-top: 25px;" class="btn btn-danger" onclick="referPatientPayment1()"  value="Pay">
      </div>
    </div>

  </div>
</div>



<!-- Modal -->
<div id="viewAccount" class="modal fade" role="dialog">
  <div class="modal-dialog modal-sm">
    <!-- Modal content-->
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal">&times;</button>
        <h4 class="modal-title">Account Details</h4>
      </div>
      <div class="modal-body">
    		<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12">
    			<label><b>Bank Name</b></label>
    			<div id="bankNameDiv1">
    				<label id="bankName1"></label>
    			</div>
    			<div id="bankNameDiv2">
    				<s:textfield id="bankName" name="bankName" cssClass="form-control"></s:textfield>
    			</div>
    		</div>
    		<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12">
    			<label><b>Account Number</b></label>
    			<div id="accountNoDiv1">
    				<label id="accountNo1"></label>
    			</div>
    			<div id="accountNoDiv2">
    				<s:textfield id="accountNo" name="accountNo" cssClass="form-control"></s:textfield>
    			</div>
    			
    		</div>
    		<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12">
    			<label><b>IFSC Code</b></label>
    			<div id="ifscCodeDiv1">
    				<label id="ifscCode1"></label>
    			</div>
    			<div id="ifscCodeDiv2">
    				<s:textfield id="ifscCode" name="ifscCode" cssClass="form-control"></s:textfield>
    			</div>
    			
    		</div>
    		<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12">
    			<label><b>UPI ID</b></label>
    			<div id="upidId1">
    				<label id="upiId1"></label>
    			</div>
    			<div id="upidId2">
    				<s:textfield id="upiId" name="upiId" cssClass="form-control"></s:textfield>
    			</div>
    			
    		</div>
      </div>
      <div class="modal-footer" >
      	<%if(loginfo.isSmallClinic()){ %>
    					<div id="editDiv">
    						<a href="#" class="btn btn-danger" onclick="editAccountDetails(0)">Edit</a>
    					</div>
    				<%} %>
      	<div id="bankbtnId">
      		<input type="button" style="margin-top: 25px;" class="btn btn-danger" onclick="updateAccountDeatils()"  value="Save">
      	</div>
      </div>
    </div>

  </div>
</div>

<!-- Modal -->
<div id="viewAccount1" class="modal fade" role="dialog">
  <div class="modal-dialog modal-sm">
    <!-- Modal content-->
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal">&times;</button>
        <h4 class="modal-title">Account Details</h4>
      </div>
      <div class="modal-body">
    		<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12">
    			<label><b>Bank Name:</b></label>
    			<label id="viewBankName"></label>
    		</div>
    		<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12">
    			<label><b>Account Number:</b></label>
    			<label id="viewAccountNo"></label>
    		</div>
    		<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12">
    			<label><b>IFSC Code:</b></label>
    			<label id="viewIfscCode"></label>
    		</div>
    		<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12">
    			<label><b>UPI ID:</b></label>
    			<label id="viewupiId"></label>
    		</div>
      </div>
      <div class="modal-footer" >
      </div>
    </div>

  </div>
</div>

<div id="suportPopup" class="modal fade" role="dialog">
  <div class="modal-dialog modal-sm">
    <!-- Modal content-->
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal">&times;</button>
        <h4 class="modal-title">Support</h4>
      </div>
      <div class="modal-body">
	   		<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12">
	   			<label><b>Contact No:</b></label><br>
	   			<table>
	   				<tr>
	   					<td>Mr. Sarfraj Sayyed</td>
	   					<td>&nbsp;&nbsp;-&nbsp;&nbsp;</td>
	   					<td >9699836133, 8390445230</td>
	   				</tr>
	   				<tr>
	   					<td>Mr. Vinod Mishra</td>
	   					<td>&nbsp;&nbsp;-&nbsp;&nbsp;</td>
	   					<td>9579037238</td>
	   				</tr>
	   				<tr>
	   					<td>Mr. Manoj Mishra</td>
	   					<td>&nbsp;&nbsp;-&nbsp;&nbsp;</td>
	   					<td>9156248809</td>
	   				</tr>
	   			</table>
	   		</div>
	   		<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12" style="margin-top: 10px;">
	   			<label><b>Whats App No:</b></label><br>
	   			<table>
	   				<tr>
	   					<td>Manas Yuvicare</td>
	   					<td>&nbsp;&nbsp;-&nbsp;&nbsp;</td>
	   					<td >9699836133</td>
	   				</tr>
	   			</table>
	   		</div>
	   		<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12" style="margin-top: 10px;">
	   			<label><b>Email:</b></label><br>
	   			<label>info@yuvicare.in</label>
	   		</div>
      </div>
      <div class="modal-footer" >
      	 <button type="button" class="btn btn-primary" data-dismiss="modal">Close</button>
      </div>
    </div>

  </div>
</div>

<div id="offersPopup" class="modal fade" role="dialog">
  <div class="modal-dialog modal-md">
    <!-- Modal content-->
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal">&times;</button>
        <h4 class="modal-title">Offers</h4>
      </div>
      <div class="modal-body">
	   		<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12">
	   			<%int offCount=0; %>
	   			<s:iterator value="offerList">
   					<p><s:property value="name"/></p>
   					<p style="border:1px solid #dfd8d4; "></p>
   				</s:iterator>
	   		</div>
	   		
      </div>
      <div class="modal-footer" >
      	 <button type="button" class="btn btn-primary" data-dismiss="modal">Close</button>
      </div>
    </div>

  </div>
</div>
<div id="commercialPopup" class="modal fade" role="dialog">
  <div class="modal-dialog modal-md">
    <!-- Modal content-->
    <div class="modal-content">
      <div class="modal-header">
      	<button type="button" class="close" data-dismiss="modal">&times;</button>
        <h4 class="modal-title">Commercial Setup</h4>
      </div>
      <div class="modal-body">
	   		<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12" style="margin-bottom: 10px;">
	   			<div class="col-lg-6 col-md-6 col-xs-12 col-sm-12">
	   				<s:select cssClass="form-control chosen" id="referHospitalId" list="referhospitallist" headerKey="0" headerValue="Select Hospital" listKey="id" listValue="hosp_name">
	   				</s:select>	
	   			</div>
	   			<div class="col-lg-6 col-md-6 col-xs-12 col-sm-12">
	   				<a href="#" class="btn btn-primary" onclick="addCommercial()">Add</a>
	   			</div>
	   		</div>
	   		<form action="saveclinichospitalBookAppointmentAjax" id="clinicHospitalForm">
	   			<input type="hidden" name="clinicHospTempIds" id="clinicHospTempIds" >
	   			<div class="col-lg-12 col-xs-12 col-md-12" style="padding: 0px;">
	        		<table class="table my-table xlstable table-striped table-bordered" style="width: 100%;">
	                	<thead>
	                    	<tr>
	                        	<th style="width: 10%">Sr No</th>
	                            <th style="width: 70%">Hospital Name</th>
	                            <th style="width: 20%">Commercial</th>
	                        </tr>
	                   </thead>
	                   <tbody id="commercialbody">
	                   		<tr></tr> 
	                   </tbody>
	               </table>
	        	</div>
	   		</form>
      </div>
      <div class="modal-footer" >
      	 <!-- <button type="button" class="btn btn-primary" data-dismiss="modal">Close</button> -->
      	 <a href="#" class="btn btn-primary" onclick="saveCommercialClinic()">Save</a>
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
<script>
$('#whatever').on('touchstart click', function(){ /* do something... */ });
</script>
