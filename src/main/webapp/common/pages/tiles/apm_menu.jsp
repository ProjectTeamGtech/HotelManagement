<%@ taglib uri="/struts-tags" prefix="s"%>
<%@page import="com.apm.AssesmentForms.eu.entity.Assessment"%>
<%@page import="java.util.ArrayList"%>

<%@page import="com.apm.common.web.common.helper.LoginInfo"%>
<%@page import="com.apm.common.web.common.helper.LoginHelper"%>
<link rel="icon" href="images/icon.ico">

<script type="text/javascript" src="registration/js/checkMailSend.js"></script>

<nav class="navbar navbar-default navbar-fixed-top" role="navigation">
				<div class="container-fluid">
					<!-- Brand and toggle get grouped for better mobile display -->
					<div class="navbar-header">
						<button type="button" class="navbar-toggle" data-toggle="collapse"
							data-target="#apm-primary-collapse-nav">
							<span class="sr-only">Toggle navigation</span> <span
								class="icon-bar"></span> <span class="icon-bar"></span> <span
								class="icon-bar"></span>
						</button>
<!-- 						<a class="navbar-brand" href="#"><i class="fa fa-user-md"></i> APM</a>
 -->						
					</div>

					<!-- Collect the nav links, forms, and other content for toggling -->
					<div class="collapse navbar-collapse"
						id="apm-primary-collapse-nav">
			<%LoginInfo loginInfo = LoginHelper.getLoginInfo(request); %>
			<s:if test="(#session.LOGIN_INFO != null)&&(#session.LOGGED_IN)">
			<%@ include file="/application.jsp" %>
						<ul class="nav navbar-nav">
						 <li class="dropdown"><a href="#" id="services" class="dropdown-toggle"
								data-toggle="dropdown"><i class="fa fa-user-md" style="font-size: 15px"></i> APM<span class="caret"></span></a>
							<ul class="dropdown-menu" role="menu">
								    <li><a href="#">About Us</a></li>
 								    <li><a href="#">Contact Us</a></li>
 								    <li><a href="#">Support</a></li>
 								    <li><a href="#">User Guide</a></li>
 								
 								
								
							</ul>
						</li>
						
						
						<%if(loginInfo.isAppointmentBooking() == true){ %>
						<li class="dropdown"><a href="#" id="services" class="dropdown-toggle" title="Appointments"
								data-toggle="dropdown"><i class="fa fa-calendar" style="font-size: 22px"></i><span class="caret"></span></a>
							<ul class="dropdown-menu" role="menu">							
								<li><a href="allUserNotAvailableSlot">MyDashboard</a></li>
								<li><a href="#" onclick="openPopup('inputFinder')">Finder</a></li>
							</ul>
						</li>
						<%}%>
						
						<li class="dropdown"><a href="#" id="services" class="dropdown-toggle" title="Clients"
								data-toggle="dropdown"><i class="fa fa-user" style="font-size: 22px"></i> <span class="caret"></span></a>
							
								
    								<ul class="dropdown-menu" role="menu">
    								
    									
										<li><a href="#" onclick="openPopup('manageClient')">View Clients</a></li>
										<!--  <li><a href="addManageClient">Add New Client(Basic Info)</a></li>
										--><li><a href="#" onclick="openPopup('addCompleteInfoClient')">Add New Client(Complete Info)</a></li>
									<li><a href="#" onclick="openPopup('viewProfileClient')">Print Client Record</a></li>
										<li><a href="#" onclick="openPopup('TreatmentEpisode')">View Treatment Episode</a></li>
										<li><a href="#" onclick="openPopup('ClientLog')">View Client Log</a></li>
									<%if(loginInfo.isMedicalRecord() == true){ %>
										<!-- <li><a href="inputEmr" id="services">View Medical Record(EMR)</a></li> -->
										<li><a href="#" onclick="openPopup('viewPageEmr')" id="services">View Medical Record(EMR)</a></li>
									<%}%>	
   									 </ul>
 								
								
 								
								
							
						</li>
						
							<%if(loginInfo.getUserType() == 1){ %>
							<li><a href="ClinicRegistration">Clinic</a></li>	
						
							<%}%>
							
						<%if(loginInfo.getUserType() == 2){ 
							
							if(loginInfo.isFullFinance() == true){
						%>
						
						<li class="dropdown"><a href="#" id="services" class="dropdown-toggle" title="Accounts"
								data-toggle="dropdown"><i class="fa fa-gbp" style="font-size: 22px;"></i> <span class="caret"></span></a>
							<ul class="dropdown-menu" role="menu">
								
								    <li><a href="#" onclick="openPopup('inputStatement')">View Account</a></li>								
 								    <li><a href="#" onclick="openPopup('pendingChargesCompleteApmt')">Pending Charges</a></li> 								
 								    <li><a href="#" onclick="openPopup('inputAccounts')">Create Invoice/Process Charges</a></li>
 									<li><a href="#" onclick="openPopup('inputProcessingAccount')">View Invoice/Record Payment </a></li> 								
 									<li><a href="#" onclick="openPopup('OutstandingReport')">Track Outstanding Invoices</a></li>
<!--  								<li><a href="CashDesk">Cash Desk</a></li>
 --> 								<li><a href="#" onclick="openPopup('inputAdditional')">Raise Credit/ Debit Note</a></li>
 									<li><a href="#" onclick="openPopup('creditListAdditional')">View Credit Account</a></li>
 							
								<!-- <li><a href="createChargeCompleteApmt">Create Invoice</a></li>
								<li><a href="createChargeCompleteApmt">Update Payment</a></li> -->
							</ul>
						</li>
						<%}else if(loginInfo.isBasicFinance() == true){%>
						
						<li class="dropdown"><a href="#" id="services" class="dropdown-toggle" title="Accounts"
								data-toggle="dropdown"><i class="fa fa-gbp" style="font-size: 22px;"></i> <span class="caret"></span></a>
							<ul class="dropdown-menu" role="menu">
								
								    <li><a href="#" onclick="openPopup('inputStatement')">View Account</a></li>
								<%if(loginInfo.isFullFinance() == true){ %>
 								    <li><a href="#" onclick="openPopup('pendingChargesCompleteApmt')">Pending Charges</a></li>
 								<%}%>
 								
 								    <li><a href="#" onclick="openPopup('inputAccounts')">Create Invoice/Process Charges</a></li>
 									<li><a href="#" onclick="openPopup('inputProcessingAccount')">View Invoice/Record Payment </a></li>
 								<%if(loginInfo.isFullFinance() == true){ %>	
 									<li><a href="#" onclick="openPopup('OutstandingReport')">Track Outstanding Invoices</a></li>
<!--  									<li><a href="CashDesk">Cash Desk</a></li>
 --> 									<li><a href="#" onclick="openPopup('inputAdditional')">Raise Credit/ Debit Note</a></li>
 								<%}%>
 									
								
								<!-- <li><a href="createChargeCompleteApmt">Create Invoice</a></li>
								<li><a href="createChargeCompleteApmt">Update Payment</a></li> -->
							</ul>
						</li>
						
						<%} else {}%>	
						
						<li class="dropdown"><a href="#" id="services" class="dropdown-toggle" title="Third-Parties"
								data-toggle="dropdown"><i class="fa fa-users" style="font-size: 22px"></i> <span class="caret"></span></a>
							<ul class="dropdown-menu" role="menu">
								<li><a href="#" onclick="openPopup('showListThirdParty')">View Third Party </a></li>	
								<li><a href="#" onclick="openPopup('addThirdParty')">Add New Third Party </a></li>
								<li><a href="#" onclick="openPopup('GP')"> View GP</a></li>	
							</ul>
						</li>
						<li class="dropdown"><a href="#" id="services" class="dropdown-toggle" title="Tools"
								data-toggle="dropdown"><i class="fa fa-cog" style="font-size: 22px"></i> <span class="caret"></span></a>
							<ul class="dropdown-menu" role="menu" aria-labelledby="dLabel">
							<%if(loginInfo.isDiaryManagement() == true){ %>
								<li><a href="#" onclick="openPopup('DiaryMangent')">Manage Diary </a></li>
							<%}%>
 								 <li class="dropdown-submenu"> 
    								<a tabindex="-1" href="#">Manage Clinic</a>
    								<ul class="dropdown-menu">
    									<li><a href="#" onclick="openPopup('profileClinicRegistration')">Clinic Profile</a></li>
										<li><a href="#" onclick="openPopup('locationClinicRegistration')">Clinic Location</a></li>
   									 </ul>
 								 </li>
 								 <li class="dropdown-submenu"> 
    								<a tabindex="-1" href="#">Users</a>
    								<ul class="dropdown-menu">
    									<li><a href="#" onclick="openPopup('UserProfile')">View User</a></li>    									
   									 </ul>
 								 </li>
 								  <li class="dropdown-submenu"> 
    								<a tabindex="-1" href="#">Setup Clinic Data</a>
    								<ul class="dropdown-menu">
    									<li><a href="#" onclick="openPopup('AppointmentType')">Set Appointment/Charges</a></li>
										<li><a href="#" onclick="openPopup('Occupation')">Set Occupation</a></li>
										<li><a href="#" onclick="openPopup('JobTitle')">Set Job Title</a></li>
										<li><a href="#" onclick="openPopup('Reference')">Set Referred By</a></li>
										<li><a href="#" onclick="openPopup('ThirdPartyType')">Set TP Type</a></li>
										<li><a href="#" onclick="openPopup('Declaration')">Set Client Declaration</a></li>
										<li><a href="#" onclick="openPopup('Specialize')">Set Specialization</a></li>
<!-- 									<li><a href="#">Set Notification & Reminders</a></li>
 -->									<li><a href="#" onclick="openPopup('TreatmentType')">Set Condition</a></li>
										<!--<li><a href="TreatmentType">Treatment Type</a></li> -->
										<li><a href="#" onclick="openPopup('SourceOfIntro')">SourceOfIntro</a></li>
										<!-- <li><a href="#" onclick="openPopup('setDNAChargeClinicRegistration')">Set DNA Charge</a></li> -->
   									 </ul>
 								 </li>
 								
 								 <!--  <li class="dropdown-submenu"> 
    								<a tabindex="-1" href="#">Practice Report</a>
    								<ul class="dropdown-menu">
    									<li><a href="PendingPaymentReports" id="services">Pending Payment (Aged)</a></li>
    									<li><a href="ApmtDiaryReport" id="services">Practitioners Appointment Status</a></li>
   									 </ul>
 								 </li> -->
 								 <%if(loginInfo.isCommunication() == true){ %>	
 								 <li class="dropdown-submenu"> 
    								<a tabindex="-1" href="#">Manage Email</a>
    								<ul class="dropdown-menu">
    									<%-- <!-- <li><a href="TPReferal">Configure Clinic email</a></li> -->
    									<li><s:checkbox name="checkMailSend" id="checkMailSend" onchange="setChangeValueCheckEmail()" /> Configure Clinic email</li>			
   									   
    									<!-- <li><a href="TPReferal">TP Referal</a></li>   
										<li><a href="#">GP Referal</a></li>  -->
 										 <li><a href="EmailTemplate">View email Template</a>
 										<!--   <li><a href="checkEmailSendEmailTemplate">Check Email Send</a>	-->	 --%>	
 										
 										 <li><a href="#" onclick="openPopup('EmailTemplate')">View email Template</a>
 										 <li><a href="#" onclick="openPopup('checkEmailSendEmailTemplate')"> Configure Clinic email</a>	
   									 </ul>
 								 </li>
								
								<%}%>					
								<li><a href="#" onclick="openPopup('changePasswordPageResetPassword')">Change Account Password</a></li>
							</ul>
						
															
						</li>
						
						
						 <%if(loginInfo.isCommunication() == true){ %>	
						<!-- <li class="dropdown" onclick="setCometList()"><a href="#" id="services" class="dropdown-toggle" title="Communication" style="font-size: 22px"
								data-toggle="dropdown"> <i class="fa fa-comments-o"></i></a> -->
							<li class="dropdown"><a href="#" id="services" class="dropdown-toggle" title="Communication" style="font-size: 22px"
								data-toggle="dropdown"> <i class="fa fa-comments-o"></i></a>
							<ul class="dropdown-menu" role="menu" aria-labelledby="dLabel">
								<li class="dropdown-submenu">
									<a href="#" id="services"><i style="font-size: 15px"></i> Email</a>
									<ul class="dropdown-menu">		
										 <li><a href="#" onclick="openPopup('InstantMsg')">Send Email</a></li>
										
   									</ul>
								</li>
								<li class="dropdown-submenu"> 
    								<a tabindex="-1" href="#"><i class="fa fa-weixin" style="font-size: 15px"></i> Live Chat</a>
    								<ul class="dropdown-menu">
    									<li> <div id="members"></div> </li>
			
   									 </ul>
 								 </li>
    								  <li><a href="#" onclick="openPopup('printLetterInstantMsg')">Print Letter</a></li>	
								
								
							</ul>							
						</li>
						<%}%>	
						 <%if(loginInfo.isReport() == true){ %>	
						<li class="dropdown"><a href="#" id="services" class="dropdown-toggle" title="Reports"
								data-toggle="dropdown"><i class="fa fa-file-text-o"  style="font-size: 22px"></i>   <span class="caret"></span></a>
							<ul class="dropdown-menu" role="menu" aria-labelledby="dLabel">
								
 								 <li class="dropdown-submenu"> 
    								<a tabindex="-1" href="#">Accounts</a>
    								<ul class="dropdown-menu">
    									<li><a href="#" onclick="openPopup('PractitionerListCommission')">Practitioners Commission Report</a></li>
										<li><a href="#" onclick="openPopup('ChargesRpt')">Charges Report</a></li>
										<li><a href="#" onclick="openPopup('invoiceReportChargesRpt')">Invoice Report</a></li>
										<li><a href="#" onclick="openPopup('paymentReportChargesRpt')">Payment Report</a></li>
										<li><a href="#" onclick="openPopup('PendingPaymentReports')">Aged Debtors Report</a></li>
										
										
										
   									 </ul>
 								 </li>
 								 <li class="dropdown-submenu"> 
    								<a tabindex="-1" href="#">Clinical</a>
    								<ul class="dropdown-menu">
    									<li><a href="#" onclick="openPopup('treatmentEpisodeListClinical')">Treatment Episode List</a></li>
    									<li><a href="#"></a></li>
   									 </ul>
 								 </li>
 								 <li class="dropdown-submenu"> 
    								<a tabindex="-1" href="#">Client</a>
    								<ul class="dropdown-menu">
    									<li><a href="#" onclick="openPopup('clientListClientRpt')">Client List</a></li>
    									<li><a href="#" onclick="openPopup('currentTreatmentNoFutureApmtsClientRpt')">Current Treatment with No Future Apmts</a></li> 
    									<li><a href="#" onclick="openPopup('noApptActivityRecordClientRpt')">No Appointment Activity Record</a></li>
    									<li><a href="#" onclick="openPopup('DNANoFutureAppClientRpt')">DNA with No Future Appointment</a></li>
    									<li><a href="#" onclick="openPopup('noActivityRecordClientRpt')">No Activity Record</a></li>
    									<li><a href="#"></a></li>
   									 </ul>
 								 </li>
 								 <li class="dropdown-submenu"> 
    								<a tabindex="-1" href="#">Summary</a>
    								<ul class="dropdown-menu">
    									<li><a href="#" onclick="openPopup('Summary')">DNA Analysis Report</a></li>
    									<li><a href="#" onclick="openPopup('appDNAKeptSummary')">Appointment kept vs DNA</a></li>
    									<li><a href="#" onclick="openPopup('treatmentReferralSummary')">Treatment States by Referral</a></li>
   									 </ul>
 								 </li>
								
													
								
							</ul>								
						</li>
						<%}%>	
						 <%if(loginInfo.isAssessmentForms() == true){ %>	
						<li class="dropdown"><a href="#" id="services" class="dropdown-toggle"  title="Assessment Forms" style="font-size: 22px"
								data-toggle="dropdown"><i class="fa fa-list-alt"></i> <span class="caret"></span></a>
							<ul class="dropdown-menu" role="menu" aria-labelledby="dLabel">
							 <li class="dropdown-submenu">  
							<a tabindex="-1" href="#">Form Builder</a>
    						<ul class="dropdown-menu">
							 <li><a href="#" onclick="openPopup('AssesmentMasterForms')">Add/Edit Field</a></li>
							 <li><a href="#" onclick="openPopup('inputAssesmentForms')">Create New Forms</a></li>	
							<!--  <li><a href="modifyAssesmentForms">Modify Assessment Forms</a></li>	 -->
							</ul>	
							</li>						
 								 <li class="dropdown-submenu">     								
    								<%ArrayList<Assessment>  templateNameList = (ArrayList<Assessment>)session.getAttribute("templateNameList");%>
										
										<a tabindex="-1" href="#">View Forms</a>
    									<ul class="dropdown-menu">
    									<%if(templateNameList!=null){ %>
											<%for(Assessment assessment:templateNameList){ %>
	    										<li><a href="#" onclick="openPopup('addTemplateDetailsAssesmentForms?templateId=<%=assessment.getId() %>')"><%=assessment.getTemplateName() %></a></li>
    										<%} %>
    									<%} %>
    									 </ul>
											   									
 								 </li>
 								  <li><a href="#" onclick="openPopup('inputListAssessmentForm')">View/Edit Forms</a></li> 								 
 								 <li><a href="#" onclick="openPopup('templateFormsAssessmentTemplate')">Physiotherpy Assesment form</a></li>
 								 <li><a href="#" onclick="openPopup('ImportImageForAssessment')">List/Add Images</a></li> 
 							<%if(loginInfo.isMedicalRecord() == true){ %>
 								 <!--  <li><a href="ConsultationNote">Consultation Note</a></li> --> 								 
 							<%} %>
 								 <!-- <li><a href="Editor">Upload/Edit Image</a></li> -->
 						</ul>
 						</li>		 
						<%} else if(loginInfo.isMedicalRecord() == true){ %>		
						
						<%-- <li class="dropdown"><a href="#" id="services" class="dropdown-toggle"  title="Consultation Note" style="font-size: 22px"
								data-toggle="dropdown"><i class="fa fa-list-alt"></i> <span class="caret"></span></a>
							<ul class="dropdown-menu" role="menu" aria-labelledby="dLabel">
								<li><a href="ConsultationNote">Consultation Note</a></li> 
							</ul>
 						</li> --%>
						<%} else{} %>			
					<%} %>
					
					<%if(loginInfo.getUserType() == 3){ %>
						<li><a href="PracticeManager" id="services">Practice Manager</a></li>		
					<%} %>
					<%if(loginInfo.getUserType() == 4){ %>
					<li class="dropdown"><a href="#" id="services" class="dropdown-toggle" title="Tools"
								data-toggle="dropdown"><i class="fa fa-cog" style="font-size: 22px"></i> <span class="caret"></span></a>
								<ul class="dropdown-menu" role="menu" aria-labelledby="dLabel">
								<%if(loginInfo.isDiaryManagement() == true){ %>
									<li> <a href="#" onclick="openPopup('DiaryMangent')" id="services">Manage Diary</a></li>
    							<%} %>		
   									<li class="dropdown-submenu"> 
	    								<a tabindex="-1" href="#">Practitioner</a>
	    								<ul class="dropdown-menu">
	    									<li><a href="#" onclick="openPopup('profilePractitionerUserProfile')" id="services">View Profile</a></li>    									
	   									 </ul>
	 								 </li>
	 							
								<li><a href="#" onclick="openPopup('changePasswordPageResetPassword')">Change Account Password</a></li>
							</ul>
						</li>
						
						<%if(loginInfo.isCommunication() == true){ %>	
						<li class="dropdown" onclick="setCometList()"><a href="#" id="services" class="dropdown-toggle" style="font-size: 22px" title="Communication"
								data-toggle="dropdown"><i class="fa fa-comments-o"></i><span class="caret"></span></a>
							<ul class="dropdown-menu" role="menu" aria-labelledby="dLabel">
								<li><a href="#" onclick="openPopup('InstantMsg')" id="services"><i class="fa fa-envelope" style="font-size: 15px"></i> Email</a></li>
    									<li class="dropdown-submenu"> 
    								<a tabindex="-1" href="#"><i class="fa fa-weixin" style="font-size: 15px"></i> Live Chat</a></a>
    								<ul class="dropdown-menu">
    									<li> <div id="members"></div> </li>
			
   									 </ul>
 								 </li>								
								
							</ul>								
						</li>
						<%} %>
						
						 <%if(loginInfo.isAssessmentForms() == true){ %>
						<li class="dropdown"><a href="#" id="services" class="dropdown-toggle"  title="Assessment Forms" style="font-size: 22px"
								data-toggle="dropdown"><i class="fa fa-list-alt"></i> <span class="caret"></span></a>
							<ul class="dropdown-menu" role="menu" aria-labelledby="dLabel">
							 <li class="dropdown-submenu">  
							<a tabindex="-1" href="#">Form Builder</a>
    						<ul class="dropdown-menu">
							 <li><a href="#" onclick="openPopup('AssesmentMasterForms')">Add/Edit Field</a></li>
							 <li><a href="#" onclick="openPopup('inputAssesmentForms')">Create New Forms</a></li>	
							<!--  <li><a href="modifyAssesmentForms">Modify Assessment Forms</a></li>	 -->
							</ul>	
							</li>						
 								 <li class="dropdown-submenu">     								
    								<%ArrayList<Assessment>  templateNameList = (ArrayList<Assessment>)session.getAttribute("templateNameList");%>
										
										<a tabindex="-1" href="#">View Forms</a>
    									<ul class="dropdown-menu">
    									<%if(templateNameList!=null){ %>
											<%for(Assessment assessment:templateNameList){ %>
	    										<li><a href="#" onclick="openPopup('addTemplateDetailsAssesmentForms?templateId=<%=assessment.getId() %>')"><%=assessment.getTemplateName() %></a></li>
    										<%} %>
    									<%} %>
    									 </ul>
											   									
 								 </li>
 								  <li><a href="#" onclick="openPopup('inputListAssessmentForm')">View/Edit Forms</a></li> 								 
 								 <li><a href="#" onclick="openPopup('templateFormsAssessmentTemplate')">Physiotherpy Assesment form1</a></li>
 								 <li><a href="#" onclick="openPopup('ImportImageForAssessment')">List/Add Images</a></li>
 							<%if(loginInfo.isMedicalRecord() == true){ %>
 								<!--   <li><a href="ConsultationNote">Consultation Note</a></li>  -->								 
 							<%} %>	 
 								 <!-- <li><a href="Editor">Upload/Edit Image</a></li> -->
 						</ul>
 						</li>
 						
 						<%} else if(loginInfo.isMedicalRecord() == true){ %>		
						
						<li class="dropdown"><a href="#" id="services" class="dropdown-toggle"  title="Consultation Note" style="font-size: 22px"
								data-toggle="dropdown"><i class="fa fa-list-alt"></i> <span class="caret"></span></a>
							<ul class="dropdown-menu" role="menu" aria-labelledby="dLabel">
								<li><a href="#" onclick="openPopup('ConsultationNote')">Consultation Note</a></li> 
							</ul>
 						</li>
						<%} else{} %>	
						
					<%} %>
					<%if(loginInfo.getUserType() == 5){ %>
						<%if(loginInfo.isAppointmentBooking() == true){ %>
							<li><a href="#" onclick="openPopup('allUserNotAvailableSlot')" id="services"><i class="fa fa-tags" style="font-size: 15px"></i>Appointment</a></li>	
						<%} %>	
					<%} %>
					</ul>
					</s:if>	
					
					<s:else>
				<ul class="nav navbar-nav">	
<!-- 				<li><a href="inputLogin" id="services"><i class="fa fa-unlock-alt"></i> Login</a></li>
 -->				<li><a href="inputClinicRegistration" id="services" title="Clinic Registration"><i class="fa fa-edit" style="font-size: 15px"></i> Clinic Registration</a></li>
				</ul>
				
				
				
			</s:else>
						
			<ul class="nav navbar-nav navbar-right">
			<s:if test="(#session.LOGIN_INFO != null)&&(#session.LOGGED_IN)">
				<%if(loginInfo.getUserType() == 4){ %>
						<!-- <li><a href="InstantMsg" id="services"><i class="fa fa-envelope"></i> IM</a></li>	 -->
						<!-- <li class="dropdown"><a href="#" id="services" class="dropdown-toggle"
								data-toggle="dropdown">IM</a>
							<ul class="dropdown-menu" role="menu">
								<li> <div id="members"></div> </li>
								
								
							</ul>
						</li> -->
					<%} %>
			</s:if>
				<s:if test="(#session.LOGIN_INFO != null)&&(#session.LOGGED_IN)">
				                      <li><a href="#" id="toggleFullScreen" data-toggle="tooltip" data-placement="bottom" data-original-title="Full Screen" onclick="toggleFullScreen();"><i class="glyphicon glyphicon-fullscreen"></i></a></li>
				
					<% if(loginInfo.getUserType()==2){%>
						<li><a href="#22" id="services">Hi Admin</a></li>
					<%}else{ %>
						<li><a href="#22" id="services">Hi <%=loginInfo.getFirstName()%></a></li>
					<% }%>
					
				</s:if>
				<s:else>
					<li><a href="#22" id="services"> Hi Guest</a></li>
				</s:else>
				<s:if test="(#session.LOGIN_INFO != null)&&(#session.LOGGED_IN)">
								<li><a href="Logout" id="services"><i class="fa fa-unlock-alt" style="font-size: 15px"></i> Logout</a></li>
				
				</s:if>
				<s:else>
								<li><a href="inputLogin" id="services"><i class="fa fa-lock" style="font-size: 15px"></i> Login</a></li>
				
				</s:else>

			</ul>
			
		</div>
		
		
					<!-- /.navbar-collapse -->
				</div>
				<!-- /.container-fluid -->
			</nav>


