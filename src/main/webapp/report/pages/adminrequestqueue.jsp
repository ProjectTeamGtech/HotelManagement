<%@taglib uri="/struts-tags" prefix="s"%>
<%@page import="java.util.Date"%>
<script type="text/javascript" src="report/js/report.js"></script>
<script type="text/javascript"
	src="assesmentForms/js/jquery.table2excel.js"></script>

<script type="text/javascript" src="accounts/js/commonaddcharge.js"></script>
<script type="text/javascript"
	src="diarymanagement/js/addpriscription.js"></script>
<script type="text/javascript" src="emr/js/addInvestigation.js"></script>
<script type="text/javascript" src="common/js/pagination.js"></script>
<script type="text/javascript" src="pharmacy/js/pharmacy.js"></script>
<script type="text/javascript"
	src="assesmentForms/js/jquery.table2excel.js">
	
</script>

<style>
.my-table td {
	border: 3px solid #c7bdb7 !important;
	padding: 5px !important;
	font-size: 11px !important;
}

.spancl {
	font-size: 13px;
}

.bgll {
	background-color: ;
	size: 10px;
}
td a{
color: #822828fa;
}

#crmodel{
margin-left: -90px !important;
margin-right: -90px !important;
margin-bottom: -90px !important;
}

.point{
cursor: pointer;
}
</style>
<script>
	function printExcel() {

		$(".xlstable").table2excel({
			exclude : ".noExl",
			name : "report",
			filename : "newgst",
			fileext : ".xls",
			exclude_img : true,
			exclude_links : true,
			exclude_inputs : true
		});
	}

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
</script>

<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12">


	<div class="row details" style="background-color: #3c6ea0 !important">
		<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12">

			<h4>Support Request Queue (Admin)</h4>

		</div>

	</div>

	<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12 "
		style="padding-top: 20px">
		<s:form action="adminrequestqueueSupport" theme="simple"
			id="invoicerportfrm">

			<div class="col-lg-12 col-md-12 topback2 hidden-print"
				style="border: 3px solid #DFD8D4 !important;">

				<div class="form-inline">

					<div class="form-group" style="width: 7%;">
						<label>From Date</label>
						<s:textfield readonly="true" name="fromdate" id="fromDate"
							cssClass="form-control" theme="simple" style="width:100%;"
							placeholder="from date"></s:textfield>
					</div>
					&nbsp;&nbsp;&nbsp;
					<div class="form-group" style="width: 7%;">
						<label>To Date</label>
						<s:textfield readonly="true" name="todate" id="toDate"
							cssClass="form-control" theme="simple" style="width:100%;"
							placeholder="to date"></s:textfield>
					</div>
					&nbsp;&nbsp;&nbsp;
					<div class="form-group" style="width: 9%;">
						<label>Status</label>
						<s:select name="status" id="status"
							list="#{'':'All','0':'Requested','1':'Work In Progress','2':'Fixed','3':'Not Possible'}"
							cssClass="form-control chosen-select"></s:select>
					</div>
					<div class="form-group" style="width: 11%; padding-left: 20px;">
						<label>Hospital</label>
						
						<s:select list="cliniclist" listKey="clinicID"
							listValue="clinicID" headerKey="" headerValue="Select Clinic"
							cssClass="form-control chosen-select"
							onchange="getuserlistsupport(this.value)" name='clinicid'
							id='clinicid'></s:select>
							
					</div>
					<div class="form-group" style=" padding-left: 10px;width: 9%;margin-left: -8px;">
					<label>Assigned To</label>
						<s:select list="executivelist" 
						cssClass="form-control chosen-select" listKey="support_executive"
						listValue="support_executive" name="assignedTo" headerKey="" headerValue="All" cssStyle="width:100%"
						onchange="setSupportReqExecutive(this.value)"></s:select>
					</div>
					&nbsp;&nbsp;&nbsp;
					<div class="form-group" style=" padding-left: 10px;width: 11%;margin-left: -19px;">
						<label for="exampleInputEmail1">Query Type </label>
											   <%--  <select id="query_type" name="querytype" class="form-control chosen-select">
												  <option value="">Select Query Type</option>
												  <option value="Improvement">Improvement</option>
												  <option value="Compatibility Issue">Compatibility Issue</option>
												  <option value="User Training">User Training</option>
												  <option value="Billing Support">Billing Support</option>
												  <option value="Data Correction">Data Correction</option>
												  <option value="Report Requirement">Report Requirement</option>
												  <option value="Performance Issue">Performance Issue</option>
												  <option value="Feedback">Feedback</option>
												</select> --%>
												
					<s:select id="query_type" name="querytype"
					list="#{'':'Select Query Type','Improvement':'Improvement','Compatibility Issue':'Compatibility Issue',
					'User Training':'User Training','Billing Support':'Billing Support','Data Correction':'Data Correction',
					'Report Requirement':'Report Requirement','Performance Issue':'Performance Issue','Feedback':'Feedback'}"
							cssClass="form-control chosen-select"></s:select>
					</div>
					&nbsp;&nbsp;&nbsp;
					<div class="form-group" style="margin-left: 15px;">
						<br>
						<s:submit value="Go" theme="simple" cssClass="btn btn-primary"></s:submit>

					</div>
					<div class="form-group" style="padding-left: 10px;">
					<br>
						<input type="button" class='btn btn-success' value='New Ticket + '
							onclick="createticket()">
					</div>
					<div class="form-group" style="width:28% ">

						<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12 spancl"
							>
							<div class="col-lg-6 col-md-6 col-xs-6 col-sm-6">
							 <span style="color: blue" class="bgll"><b>Total
										:<s:property value="totalcount" />
								</b></span><br>
								<span style="color: green;" class="bgll"><b>Requested
										:<s:property value="requseted" />
								</b></span> 
							</div>
							<div class="col-lg-6 col-md-6 col-xs-6 col-sm-6">
								<span style="color: blue" class="bgll"><b>Fixed :<s:property
											value="fixed" /></b></span> <br> <span style="color: red"
									class="bgll"><b>Not Possible : <s:property
											value="notPossible" /></b></span>
							</div>
							<div class="col-lg-6 col-md-6 col-xs-6 col-sm-6">
								<span style="color: black" class="bgll"><b> Work In
										Progress : <s:property value="wip" />
								</b></span> <br>
							</div>
						</div>
					</div>

					

				</div>
			</div>
		</s:form>

	</div>
	<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12">
		<table class="my-table xlstable"
			style="width: 100%; height: 15px !important;">
			<tr bgcolor="#3c6ea0"
				style="color: white; border: 3px dashed #DFD8D4 !important; padding: 5px !important;">
				<td>Sr.</td>

				<td  style="width: 5%;">Ticket No.</td>
				<td style="width: 11%;">Date/Time</td>
				<td style="width: 5%;">Hospital</td>
				<td style="width: 8%;">User </td>

				<td>Request</td>
				<td style="width: 8%;">Remark</td>
				<td style="width: 5%;">Conversation</td>
				<td style="width: 8%;">Status / Priority</td>
				<td style="width: 8%;">Assigned to</td>
				
				<!-- <td style="width: 2%;">Change Status</td>
								<td style="width: 2%;">Msg</td> -->
			</tr>
			<%
				int i = 0;
			%>
			<s:iterator value="supportrequestlist">
				<tr style="">
					<td><%=++i%></td>

					<td>SC<s:property value="id" /></td>
					<td><s:property value="date" />/<s:property value="time" /></td>

					<td><b><s:property value="clinicname" /></b></td>
					<td><b><s:property value="fullname" /></b></td>
					<td class='point'>Type : <label style="color: black"><s:property
								value="query_type" /></label><s:if test="modulename!=null">&nbsp;&nbsp;&nbsp;[<b><s:property value="modulename"/></b>]</s:if><br> <a
						 onclick="getconversationall(<s:property value="id"/>,'Admin')"><b><s:property
									value="query" /></b></a> <s:if test="msgread">
							<img src="dashboardicon/newdiet.gif"></img>
						</s:if>
						
						<div><span><b style="color: blue">Mob No.:</b><b> <s:property value="mobile"/></b></span>
						<s:if test="altMobNo!=''"><span style="padding-left:60px;"><b style="color: blue">Alt Mob No.:</b><b><s:property value="altMobNo"/></b></span></s:if></div>
						
					</td>
					<td><b><s:property value="remark" /></b></td>
					<td style="text-align: center;"><a class='point' onclick="getconversationall(<s:property value="id"/>,'Admin')"><i class='fa fa-comments fa-3x' ></i></a>
					<s:if test="attached">
					<b><i class='fa fa-paperclip  fa-3x ' ></i></b>
					</s:if>
					</td>
									

					<td ><input type="hidden" value='<s:property value="query"/>'
						id='query<s:property value="id"/>'> <a href="#" 
						onclick="setdata('<s:property value="status"/>','<s:property value="id"/>','<s:property value="clinicname"/>','<s:property value="priorityclient"/>','<s:property value="handledby"/>','<s:property value="remark"/>',<s:property value="chargable"/>,'<s:property value="timeTaken"/>')">
							<s:if test="status==0">
								<label class='point' style="color: green">Requested</label>
							</s:if> <s:elseif test="status==1">
								<label class='point' style="color: black">Work In Progress</label>
							</s:elseif> <s:elseif test="status==2">
								<label class='point' style="color: blue">Fixed</label>  <span><s:property value="fixedDate"/></span>
							</s:elseif> <s:else>
								<label class='point' style="color: red">Not Possible</label>
							</s:else>
					</a>
					
					/
					<!--Priority  -->
					<s:if test="priorityclient==1">
							<label style="color: green">P3</label>
						</s:if> <s:elseif test="priorityclient==2">
							<label style="color: black">P2</label>
						</s:elseif> <s:elseif test="priorityclient==3">
							<label style="color: red">P1</label>
						</s:elseif> <s:elseif test="priorityclient==4">
							<label style="color: maroon;">Analysis</label>
						</s:elseif> <s:else>
							<label style="color: blue">New</label>
						</s:else>
					
					</td>
					
					<td><b><s:property value="handledby" /></b></td>
				</tr>
				
				
				
				<tr class='hidden' id='newrow<s:property value='id' />'> 
								<td colspan="10"  ><div id='newcol<s:property value='id' />' class=''></div>
								
							<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12" style="padding: 20px;">
								<form method="post" action="savescreenshotSupport" id='form<s:property value='id' />'  enctype='multipart/form-data'>
								<s:hidden name="subuploadfilesContentType"></s:hidden>
								<s:hidden name="subuploadfilesFileName"></s:hidden>
							
								<s:hidden value='admin'  name='side'/>
								<p class='hidden' id='ppp<s:property value='id' />'></p>
								<s:file accept="image/*" cssClass="form-control col-lg-4 col-md-4  " theme="simple" name="subuploadfiles" id="filesub_uploadfiles" style='width:20%'></s:file>
								
								<input type="button" class='btn btn-primary ' value="Save Screenshot" onclick="submitl(<s:property value='id' />)">
								</form>
								</div>
							
								
								</td></tr>
				
				
			</s:iterator>
		</table>
		<div style="margin-top: 20px !important">
			<!-- pagination div -->
			<s:form action="adminrequestqueueSupport" name="paginationForm"
				id="paginationForm" theme="simple">
				<s:hidden name="fromdate"></s:hidden>
				<s:hidden name="todate"></s:hidden>
				<s:hidden name="status"></s:hidden>
				<s:hidden name="clinicid"></s:hidden>
				<s:hidden name="totalcount"></s:hidden>
				<div class="col-lg-12 col-md-12" style="padding: 0px;">
					<div class="col-lg-4 col-md-4 text-left" style="padding: 0px;">
						Total:<label class="text-info"><s:property
								value="totalcount" /></label>
					</div>
					<%@ include file="/common/pages/pagination.jsp"%>


				</div>
			</s:form>

		</div>
	</div>




</div>

<!-- Modal for  status - lokesh -->
<div id="changesta" class="modal fade" role="dialog">
	<div class="modal-dialog modal-sm">

		<!-- Modal content-->
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal">&times;</button>
				<h4 class="modal-title">Select status</h4>
			</div>
			<div class="modal-body">

				<input type="hidden" name="ticketid" id="ticketid">

				<div class="col-lg-12 col-md-12 col-sm-12">

					<p>
						<label style="color: green">Hospital:</label> <b id="clinicname"></b>
					</p>
					<p>
						<label style="color: green">Query :</label> <b id="queryname"></b>
					</p>
					<label style="color: blue">Select Status</label><br>

					<s:select name="newstatus" id="newstatus"
						cssClass="form-control chosen-select"
						onchange="setSupportReqStatus(this.value)"
						list="#{'':'All','0':'Requested','1':'Work In Progress','2':'Fixed','3':'Not Possible'}"></s:select>
					<br> <br> <label style="color: blue">Assigned
						To </label>
					<s:select list="executivelist" name="execut" id='execut'
						cssClass="form-control chosen-select" listKey="support_executive"
						listValue="support_executive" headerKey="" headerValue="All"
						onchange="setSupportReqExecutive(this.value)"></s:select>
					<br> <br> <label style="color: blue">Select
						Priority</label><br>

					<s:select name="priority" id="priority"
						cssClass="form-control chosen-select"
						list="#{'0':'New','1':'P3','2':'P2','3':'P1','4':'Analysis'}"></s:select>
						
					<br><br>
					<input type="checkbox" id='is_charged'  >
					<label style="color: blue">&nbsp;&nbsp;Is Charged</label>
					<br>
					<br> 	
					
					
					<label style="color: blue">Time For The Task in Hrs :</label>
					<input type="number" class='form-control' placeholder="Add Time In Hrs"  id='time_taken' min="0">
					<br> 
					
					 <br> <label style="color: blue">Add Remark :</label><br>
					
					
					
					<textarea rows="" cols="" class="form-control" name="remark"
						id="remark"></textarea>
					<input type="button" value="Save" class="btn btn-success"
						onclick="setsupportremark()">
					<div></div>
				</div>
				<div class="modal-footer">
					<!--  <input type="button" class="btn btn-danger" onclick="openIpd()" data-dismiss="modal" value="Ok"> -->


				</div>
			</div>

		</div>
	</div>
</div>


<!-- Modal for  Adding Ticket - lokesh -->
<div id="ticketadd" class="modal fade" role="dialog">
	<div class="modal-dialog modal-sm">

		<!-- Modal content-->
		<div class="modal-content" id="crmodel">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal">&times;</button>
				<h4 class="modal-title">
					<b>Create Ticket</b>
				</h4>
			</div>
			<div class="modal-body" style="padding-left: 30px;padding-right: 30px;">
				<form id='ticketgenmodalform' action="craeteTicketforsuportSupport">
					<div class="form-group">
						<h4 id='error' style="color: red"></h4>
					</div>
					<div class="form-group">
						<label>Select Clinic : </label>
						<s:select list="cliniclist" listKey="clinicID"
							listValue="ClinicName" headerKey="" headerValue="Select Clinic"
							cssClass="form-control chosen-select"
							onchange="getuserlistsupport(this.value)" name='clinicids'
							id='clinicids'></s:select>
					</div>
					<div class="form-group" id='userlist'></div>
					<div class="form-group">
						<label> Query Type : </label> <select id="query_type"
							name="query_type" class="form-control" required>
							<option value="0">Select Query Type</option>
							<option value="Improvement">Improvement</option>
							<option value="Compatibility Issue">Compatibility Issue</option>
							<option value="User Training">User Training</option>
							<option value="Billing Support">Billing Support</option>
							<option value="Data Correction">Data Correction</option>
							<option value="Report Requirement">Report Requirement</option>
							<option value="Performance Issue">Performance Issue</option>
							<option value="Feedback">Feedback</option>
						</select>
					</div>
					<div class="form-group">
						<label>Select Module : </label>
						<s:select list="moduleList" listKey="name"
							listValue="name" headerKey="" headerValue="Select module"
							cssClass="form-control chosen-select"
							 name='modulename'
							id='modulename'></s:select>
					</div>
					<div class="form-group">
						<label>Select Issue Type : </label>
							<s:select name="issuetype" id="issuetype"
						cssClass="form-control chosen-select"
						
						list="#{'':'All','Bug':'Bug','New Requirement':'New Requirement','Modification':'Modification','Other':'Other'}"></s:select>
					</div>
					<div class="form-group">
						<label> Query : </label>
						<textarea rows="6" class="form-control" name='query' id='query'></textarea>
					</div>
					<div class="form-group">
						<label>Mobile : </label> <input type="text" class='form-control'
							name='mbl' id='mbl'>
					</div>
					
					<div class="form-group"  >
					<br>
						<input type="button"  value="Create Ticket"
							class="btn btn-primary col-lg-3 col-md-3 " 
							onclick="submitticketgenform()">
							
							<div class='col-lg-2 col-md-2 '></div>
							
							<input type="button" value="" id='otpbtn'
							class="btn btn-default col-lg-3 col-md-3 " 
							onclick="()" style="font-weight: 900;">
							
							<div class='col-lg-1 col-md-1 '></div>
							
							<input type="button" value="Generate OTP"
							class="btn btn-warning col-lg-3 col-md-3 " 
							onclick="requestforotp(this)">
					</div>
					
					
				</form>
			</div>
			<div class="modal-footer"></div>
		</div>
	</div>
</div>
<script>
	function setdata(status, ticketid, clinic, priority, handledby, remark,ischargable,time_taken) {
		document.getElementById("newstatus").className = "";

		document.getElementById("newstatus").value = status;
		$("#newstatus").trigger("chosen:updated");
		$(".chosen-select").chosen({
			allow_single_deselect : true
		});

		document.getElementById("priority").value = priority;
		$("#priority").trigger("chosen:updated");
		$(".chosen-select").chosen({
			allow_single_deselect : true
		});

		document.getElementById("execut").value = handledby;
		$("#execut").trigger("chosen:updated");
		$(".chosen-select").chosen({
			allow_single_deselect : true
		});

		document.getElementById("ticketid").value = ticketid;
		var query = document.getElementById("query" + ticketid).value;
		document.getElementById("queryname").innerHTML = query;
		document.getElementById("clinicname").innerHTML = clinic;
		document.getElementById("is_charged").checked = ischargable;
		document.getElementById("time_taken").value = time_taken;
		document.getElementById("remark").value = remark;
		$("#changesta").modal('show');
	}
</script>
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
