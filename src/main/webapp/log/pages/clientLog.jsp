<%@page import="com.apm.common.web.common.helper.LoginHelper"%>
<%@page import="com.apm.common.web.common.helper.LoginInfo"%>
<%@page import="com.apm.main.common.constants.Constants"%>

<%@taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sx" uri="/struts-dojo-tags"%>

<%-- <script type="text/javascript"
	src="diarymanagement/js/nonavailableslot.js"></script> --%>
	
<script type="text/javascript" src="common/js/pagination.js"></script>
<%-- <script type="text/javascript" src="accounts/js/accounts.js"></script>
 --%><link href="diarymanagement/css/popupstyle.css" rel="stylesheet"
	type="text/css" />


<script type="text/javascript" src="log/js/clientLog.js"></script>
<%-- <script type="text/javascript" src="log/js/clientLogPopup.js"></script> --%>
<link href="log/css/jquery.treeview.css" rel="stylesheet"
	type="text/css" />
<link href="log/css/screen.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="log/js/jquery.treeview.js"></script>




<script type="text/javascript">
	$(function() {
		$("#browser").treeview();
		
			
		
	});
</script>
<script>
	
	$(document).ready(function() {
		<%
		String clientLogId = (String)session.getAttribute("clientLogId");
		String fullname = "";
		
		if(session.getAttribute("sessionselectedclientid")!=null && clientLogId==null){
			
			clientLogId = (String)session.getAttribute("sessionselectedclientid");
			fullname = (String)session.getAttribute("sessionselectedclientName");
		}
		
		%>
		
		<%if(session.getAttribute("sessionselectedclientid")!=null && session.getAttribute("clientLogId")==null){%>
			document.getElementById('client').value = '<%=fullname%>';
		
		<%}%>
		
		
		
		
		
	
		var logid = <%=clientLogId%>;
		
		document.getElementById("clientId").value = logid;
		
		showAppointments('All','commencing','desc');
		
		getClientInfo(logid);
		
		$("#fromDate").datepicker({

			dateFormat : 'dd/mm/yy',
			yearRange: yearrange,
			minDate : '30/12/1880',
			changeMonth : true,
			changeYear : true

		});

		$("#toDate").datepicker({

			dateFormat : 'dd/mm/yy',
			yearRange: yearrange,
			minDate : '30/12/1880',
			changeMonth : true,
			changeYear : true
		});
	});
</script>

<div class="">
							<div class="">
								<div class="row details">
									<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12">

										<h4>Patient Log</h4>

									</div>
								</div>
								<div class="row ">
									<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12">
										<div>
											<div class="row">
	<div class="col-lg-3 col-md-3 col-sm-3 col-xs-12">
		<div class="panel panel-default">
			<div class="panel-body" id="client-height">
				<div class="navbar-header">
					<button type="button" class="navbar-toggle" data-toggle="collapse"
						data-target="#bs-example-navbar-collapse-1">
						<span class="sr-only"></span> <span class="icon-bar bg-primary"></span>
						<span class="icon-bar bg-primary"></span> <span
							class="icon-bar bg-primary"></span>
					</button>
					<span class="navbar-brand hidden-lg hidden-md hidden-sm"><i
						class="fa fa-folder-open"></i> Browse</span>
				</div>
				<div class="collapse navbar-collapse"
					id="bs-example-navbar-collapse-1">
					<ul id="browser" class="filetree">
						<li><img src="log/images/clientlog.Jpeg" />Patient Log</span>
							<ul>
								<li"><img
									src="log/images/folder.gif" />Treatment Episode</li>
								<li><img src="log/images/folder.gif" />Consultation</li>
								<li><img src="log/images/folder.gif" />Custom Forms</li>
								
								<li><img src="log/images/folder.gif" />IPD
									<ul>
										<li style="cursor: pointer;" onclick="showIpdAdmissionLog('All','commencing','desc')"><img
											src="log/images/folder.gif" />Admission</li>
									<!-- 	<li style="cursor: pointer;" onclick="showBedChangeLog()"><img
											src="log/images/folder.gif" />Bed Change</li>
										<li style="cursor: pointer;" onclick="showIpdDischargeLog()"><img
											src="log/images/folder.gif" />Discharge</li> -->
									
									</ul></li>
								
								<li><img src="log/images/folder.gif" />Accounts
									<ul>
										<li style="cursor: pointer;" onclick="showAccountCharges()"><img
											src="log/images/folder.gif" />Charges</li>
										<li style="cursor: pointer;" onclick="showAccountInvoice()"><img
											src="log/images/folder.gif" />Invoice</li>
										<li style="cursor: pointer;" onclick="showAccountPayments()"><img
											src="log/images/folder.gif" />Payments</li>
										<li><img src="log/images/folder.gif" />Estimates</li>
									</ul></li>
								<li><img
									src="log/images/folder.gif" />Appointments
									<ul>
										<li style="cursor: pointer;" onclick="showAppointments('All','commencing','desc')" id = "allApmts"><img src="log/images/folder.gif" />All Appointments</li>
										<li style="cursor: pointer;" onclick="showAppointments('Past','commencing','desc')" id = "pastApmts"><img src="log/images/folder.gif" />Past Appointments</li>
										<li style="cursor: pointer;" onclick="showAppointments('Future','commencing','desc')" id = "futureApmts"><img src="log/images/folder.gif" />Future Appointments</li>
										<li style="cursor: pointer;" onclick="showDnaAppointments()" id = "dnaApmts"><img src="log/images/folder.gif" />DNA</li>
									</ul></li>
								<li><img src="log/images/folder.gif" />Activities
									<ul>
										<li style="cursor: pointer;" onclick="showTreatmentEpisode()"><img
											src="log/images/folder.gif" />Report</li>
										<li style="cursor: pointer;" onclick="showLetterHistory()"><img
											src="log/images/folder.gif" />Letters</li>
										<li><img src="log/images/folder.gif" />Telephone Call</li>
										<li><img src="log/images/folder.gif" />Actions</li>
										<li><img src="log/images/folder.gif" />Contact Notes</li>
										<li style="cursor: pointer;" onclick="showEmailLogOnly()"><img
											src="log/images/folder.gif" />E-Mails</li>
										<li><img src="log/images/folder.gif" />Labels</li>
										<li><img src="log/images/folder.gif" />Messages</li>
										<li><img src="log/images/folder.gif" />SMS Messages</li>
										<li><img src="log/images/folder.gif" />Booking
											Confirmation</li>
										<li><img src="log/images/folder.gif" />Appointments
											Reminder</li>
										<li><img src="log/images/folder.gif" />Appointments
											Follow-Ups</li>
									</ul></li>

								<li><img src="log/images/folder.gif" />Medical History</li>
								<li><img src="log/images/folder.gif" />Documents</li>
								<li style="cursor: pointer;" onclick="showpatientdetailsupdatelog()"><img src="log/images/folder.gif" />Patient Details Update Log</li>

							</ul></li>

					</ul>
				</div>
			</div>
		</div>

	</div>




	<div class="col-lg-9 col-md-9 col-sm-9 col-xs-12">
		<div class="row">
			<div class="col-lg-12">
				<div class="">
					<div class="panel-body topback2">
					<div class="col-lg-3 col-md-3 col-sm-3 col-xs-12">
					<div class="input-group">
					 <span class="input-group-addon " id="basic-addon1"><i class="fa fa-user"></i></span>
						
						<%LoginInfo logf = LoginHelper.getLoginInfo(request); %>
						<%if(logf.getUserType()==5){ %>
								<s:textfield name="clientSearchLog"  id = "client" aria-describedby="basic-addon1" 
								theme="simple" readonly="true" data-toggle="modal" placeholder = "Click to Select Patient"
								data-target="clientSearch"
								cssClass="form-control"  />
						<% }else{%>
								<s:textfield name="clientSearchLog" readonly="true"  id = "client" aria-describedby="basic-addon1" 
								theme="simple" onclick="showPopUp()" data-toggle="modal" placeholder = "Click to Select Patient"
								data-target="#clientSearch"
								cssClass="form-control"  />
						
						<% }%>
						
						
					
					</div>
					
					</div>
				<!-- <div class="col-lg-1 col-md-1">
							<input type="button" class="btn btn-primary"  value="Go" onclick="showAppointments('All','appmt_id','desc')">
						</div> -->
					<s:hidden name="clientId" id="clientId"/>
					<!-- <div class="col-lg-3 col-md-3">
					<input type="text" name="searchText" id="searchText"
				class="form-control" 
				placeholder="Enter text to Search" />
					</div> -->
					<div class="col-lg-2 col-md-2">
					<input type="text" name="fromDate" id="fromDate" 
				class="form-control" placeholder="From Date" />
					</div>
					<div class="col-lg-2 col-md-2">
					<input type="text" name="toDate" id="toDate" 
				class="form-control" placeholder="To Date" />
					</div>
					<div class="col-lg-1 col-md-1">
					<input type="button" value="Go!" class="btn btn-primary"
				onclick="showAppointments('All','commencing','desc')" />
					</div> 
					
					<input type="hidden" id="filterdivid">
					<input type="hidden" id="filterbtnid">
					
					<%-- <div class="col-lg-3 col-md-3" id="filterdivid" style="display: none;">
					<s:select list="{'Booked','Modified','DNA','Completed','Completed Modified','Cancelled'}"  multiple="true" cssClass="form-control" name = "apmtFilter[]" id = "apmtFilter" ></s:select>
					</div>
					<div class="col-lg-1 col-md-1" id="filterbtnid" style="display: none;">
					
				<!-- 
						<a href="#" class="btn btn-primary"
						onclick = "filterList(this)"><i class="fa fa-filter"></i> Filter</a> -->
					</div> --%>
					<div class="col-lg-9 col-md-9 col-sm-9 col-xs-12" style="display: none" id = "infoclient">
					<label>Name: </label><span id = "infoName"></span><br>
					<label>Address: </label><span id = "infoAddress"></span>
					<label> </label><span id = "infoSecondAdress"></span>
					<label></label><span id = "infoTown"></span>,<span id = "infoPostcode"></span><br>
					<label>Mobile: </label><span id = "infomobile"></span><br>
					<label>Email: </label><span id = "infoemail"></span><br>
					<label>Age: </label> <span id = "infoAge"></span><br>
					<label>Third Party: </label> <span id = "infotp"></span>
					</div>
				</div>
			</div>
		</div>
			
			<div class="col-lg-12">
			 <h4 id = "headingTitle">Log</h4>
			 
				<div class="table-responsive" id="appoint-height">
					<table id="dataList" class="table table-bordered">

					</table>
				</div>
			</div>
		</div>
	</div>
</div>

											

											
										</div>
									</div>
								</div>
							</div>
						</div>






<!-- <h4 id = "headingTitle">Log </h4> -->




<!-- Modal -->
<div class="modal fade" id="clientSearch" tabindex="-1" role="dialog"
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
				<%@ include file="/diarymanagement/pages/allClientListLog.jsp"%>
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-primary hidden" data-dismiss="modal">Close</button>
			</div>
		</div>
	</div>
</div>



	
	<div class="modal fade" style="background: rgba(255, 255, 255, 0.93);" id="loaderPopup" tabindex="-1"
		role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" data-backdrop="static" data-keyboard="false">
		<div class="modal-dialog">
			<div class="">
				<div class="modal-body text-center">
					<img src="common/images/hourglass1.gif" class="img-responsive middlelogo" style="margin-left:auto;margin-right:auto;"></img>
					
				</div>
			</div>
		</div>
	</div>	
	
	

<script src="_assets/newtheme/js/vendor/slimscroll/jquery.slimscroll.min.js"></script>

<script>
$(function() {
  $('#client-height').slimScroll({
   		height : '584px',
   		railVisible: true,
		alwaysVisible: true
  });
  $('#appoint-height').slimScroll({
   		height : '400px',
   		railVisible: true,
		alwaysVisible: true
  });
  

 });


</script>