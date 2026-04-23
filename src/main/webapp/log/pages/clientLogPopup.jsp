<%@page import="com.apm.main.common.constants.Constants"%>

<%@taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sx" uri="/struts-dojo-tags"%>

 <script type="text/javascript"
	src="diarymanagement/js/nonavailableslot.js"></script> 
	
<script type="text/javascript" src="common/js/pagination.js"></script>
<%-- <script type="text/javascript" src="accounts/js/accounts.js"></script> --%>
<link href="diarymanagement/css/popupstyle.css" rel="stylesheet"
	type="text/css" />


 <script type="text/javascript" src="log/js/clientLogPopup.js"></script>
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


	
	<%-- $(document).ready(function() {
		<%
		String clientLogId = (String)session.getAttribute("clientLogId");
		String fullname = "";
		if(session.getAttribute("sessionselectedclientid")!=null){
			
			clientLogId = (String)session.getAttribute("sessionselectedclientid");
		}
		%>
		
		
		

		<%if(session.getAttribute("sessionselectedclientid")!=null){%>
			 fullname = (String)session.getAttribute("sessionselectedclientName");
			
	
		<%}%>
		
		<%if(session.getAttribute("sessionselectedclientid")!=null){%>
		document.getElementById('client').value = <%=fullname%>;
	
		<%}%>
		
	
		var logid = <%=clientLogId%>;
		
		document.getElementById("clientId").value = logid;
		
		showAppointments();
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
	}); --%>
</script>


<h4 id = "headingTitle">Log </h4>
<div class="row">
	<div class="col-lg-3 col-md-3">
		<div class="panel panel-default">
			<div class="panel-body">
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
						<li><img src="log/images/clientlog.Jpeg" />Client Log</span>
							<ul>
								<li style="cursor: pointer;" onclick="showTreatmentEpisode()"><img
									src="log/images/folder.gif" />Treatment Episode</li>
								<li><img src="log/images/folder.gif" />Consultation</li>
								<li><img src="log/images/folder.gif" />Custom Forms</li>
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
										<li style="cursor: pointer;" onclick="showAppointments()" id = "allApmts"><img src="log/images/folder.gif" />All Appointments</li>
										<li style="cursor: pointer;" onclick="showPastAppointments()" id = "pastApmts"><img src="log/images/folder.gif" />Past Appointments</li>
										<li style="cursor: pointer;" onclick="showFutureAppointments()" id = "futureApmts"><img src="log/images/folder.gif" />Future Appointments</li>
										<li style="cursor: pointer;" onclick="showDnaAppointments()" id = "dnaApmts"><img src="log/images/folder.gif" />DNA</li>
									</ul></li>
								<li><img src="log/images/folder.gif" />Activities
									<ul>
										<li><img src="log/images/folder.gif" />Letters</li>
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

							</ul></li>

					</ul>
				</div>
			</div>
		</div>

	</div>




	<div class="col-lg-9 col-md-9">
		<div class="row">
			<div class="col-lg-12">
				
				<div class="col-lg-2 col-md-2">
					<s:textfield name="clientSearchLog"  id = "client3"
				theme="simple" placeholder = "Click to Select Client" cssClass="form-control" cssStyle="width:92%" />
				</div>
				<!-- <div class="col-lg-3 col-md-3">
				<input type="text" name="searchText" id="searchText"
			class="form-control" 
			placeholder="Enter text to Search" />
				</div>
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
			onclick="showEmailHistory()" />
				</div> -->
				<div class="col-lg-3 col-md-3">
				<s:select list="{'Booked','Modified','DNA','Completed','Completed Modified'}"  multiple="true" cssClass="form-control" name = "apmtFilter[]" id = "apmtFilter" ></s:select>
				</div>
				<div class="col-lg-1 col-md-1">
				<input type="button" value="Filter" class="btn btn-primary"
					onclick = "filterList(this)" />
				</div>
				<div class="col-lg-4 col-md-4" style="display: none" id = "infoclient">
				<label>Name:</label><span id = "infoName"></span>
				<label>Address:</label><span id = "infoAddress"></span>
				<label> </label><span id = "infoSecondAdress"></span>
				<label>&nbsp;&nbsp;&nbsp;</label><span id = "infoTown"></span>,<span id = "infoPostcode"></span><br>
				<label>DOB:</label> <span id = "infoDob"></span><br>
				<label>Third Party:</label> <span id = "infotp"></span>
				</div>
			</div>
			
			<div class="col-lg-12">
			</br>
				<div class="table-responsive">
					<table id="dataList"
						class="table table-bordered table-hover table-striped table-condensed">

					</table>
				</div>
			</div>
		</div>
	</div>
</div>





