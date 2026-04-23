
<%@page import="com.apm.common.web.common.helper.LoginHelper"%>
<%@page import="com.apm.common.web.common.helper.LoginInfo"%>
<%@ taglib uri="/struts-tags" prefix="s" %>


<!DOCTYPE html>
<html lang="en">
<head>
    <title>Admission Summary Form</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
     <!-- New theme 30 01 2018 -->
<link href="_assets/newtheme/css/mbile.css" rel="stylesheet" type="text/css" />
<script src="_assets/newtheme/js/ui.js" type="text/javascript"></script>
<!-- New theme 30 01 2018 -->
    <style>

.mainheader {
    background-color: #339966 !important;
}
    </style>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    
   <script type="text/javascript" src="common/js/pagination.js"></script>
   <%LoginInfo loginInfo = LoginHelper.getLoginInfo(request); %>
 <SCRIPT type="text/javascript" >
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
		
		$("#admission_date").datepicker({

			dateFormat : 'dd-mm-yy',
			yearRange: yearrange,
			minDate : '30-12-1880',
			changeMonth : true,
			changeYear : true

		});
		
		$("#discharge_date").datepicker({

			dateFormat : 'dd-mm-yy',
			yearRange: yearrange,
			minDate : '30-12-1880',
			changeMonth : true,
			changeYear : true

		});
		$("#new_expiryDate").datepicker({

			dateFormat : 'dd-mm-yy',
			yearRange: yearrange,
			minDate : '30-12-1880',
			changeMonth : true,
			changeYear : true

		});
		$("#shiftmrdgivendatedetails").datepicker({

			dateFormat : 'dd-mm-yy',
			yearRange: yearrange,
			minDate : '30-12-1880',
			changeMonth : true,
			changeYear : true

		});
		$("#shiftmrdreceiveddatedetails").datepicker({

			dateFormat : 'dd-mm-yy',
			yearRange: yearrange,
			minDate : '30-12-1880',
			changeMonth : true,
			changeYear : true

		});
	});
    
    </SCRIPT>    
    <script type="text/javascript" src="mrd/js/mrd.js"></script>
<style type="text/css">
	.manascommheader{
	  	padding: 6px;
	    font-size: 20px;
	    text-transform: uppercase;
	    display: flex;
	    border-bottom: 2px solid #10606f;
	    background: #43b9be;
	}
	.manascommheader h4,h3{
		font-weight: bold;
		font-size: 17px;
		font-family: 'Open Sans', sans-serif !important;
		color: white;
	}
	.manascommheader img{
		margin-right: 6px !important;
	}
	
	.manastableheader {
	    background-color: #95d2de !important;
	    color: black !important;
	}

</style>
</head>



<body>
   <body>
<div class="">
							<div class="">
								<div class="row <!-- details mainheader -->">
									<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12 manascommheader">
										<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12" style="display: -webkit-inline-box;padding: 0px;">
										 	<img src="manasmaindashboard/images/MRD_Icon.svg" style="filter: brightness(5);margin-left: 7px;">
										 	<h4>MRD Dashboard</h4>
										</div>
										<!-- <div class="col-lg-1 col-md-1 col-sm-1 col-xs-1 oneseticonleft">
											<img src="dashboardicon/mrd.png" class="img-responsive prescripiconcircle">
											<img src="manasmaindashboard/images/MRD_Icon.svg" style="filter: brightness(5);width: 21%;margin-top: 5px;margin-left: 7px;">
										</div>
										<div class="col-lg-11 col-md-11 col-sm-11 col-xs-11 titlestleftiocn">
											<h4>MRD Dashboard</h4>
										</div> -->
									</div>
									<div class="col-lg-12 col-md-12 paddingnil">
										<div class="col-lg-12 col-md-12 topback2">
										<form id="mrdsearch" name="mrdsearch" action="Mrd" method="post" class="form-inline">
											  <div class="form-group" style="width: 20%">
											     <s:textfield  name="searchtext"  cssClass="form-control"  placeholder="Search Patient Name / Reg.no / IPD ID " size="36%" />
											  </div>
											  <div class="form-group">
											  	<%-- <select class="form-control" name="patient_department">
												  	<option value="0">Select Department</option>
												  	<option value="IPD">IPD</option>
												  	<option value="OPD">OPD</option>
												  	<option value="Casualty">Casualty</option>
												 </select> --%>
											  <s:select cssClass="form-control" list="#{'IPD':'IPD', 'OPD':'OPD','Casualty':'Casualty'}" name="patient_department" />
											  <!--<s:select list="wardlist" id="wardnameid" name="wardnameid" listKey="id" listValue="wardname" headerKey="0" headerValue="Select Ward" cssClass="form-control">
											  </s:select>
											  -->
											  </div>
											  <div class="form-group">
											    <s:textfield id="fromdate" name="fromdate"  cssClass="form-control" placeholder="From Date" />
											  </div>
											  <div class="form-group">
											    <s:textfield  name="todate" id="todate"  cssClass="form-control" placeholder="To Date" />
											  </div>
											   <div class="form-group">
											  	<%-- <select class="form-control" name="searchbydate">
												  	<option value="1">Admission Date</option>
												  	<option value="2">Saved date</option>
												 
												 </select> --%>
												 <s:select cssClass="form-control" list="#{'1':'Admission Date', '2':'Saved date'}" name="searchbydate" />		
											  </div>
											
											  <input type="submit" value="GO" class="btn btn-primary">
 
											  <a href="#" onclick="goreferesh()" class="btn btn-primary" title="Refresh"><i class="fa fa-refresh"></i></a>
										  	  <div class="form-group">
										  		<input type="button" class="btn btn-primary hidden" data-toggle="modal" data-target="#addPatientDiv"  value="Add New Patient">
										  	  	
										  	  	<!--<a class="btn btn-primary" href="#addPatientDiv" data-target="#myModal" style="margin-top:21px;"><i
													class="fa fa-plus"></i> Add</a>										  	  
											 --></div>
											 <div class="form-group">
										  		<!--<input type="button" class="btn btn-primary" data-toggle="modal"  value="Add To Mrd">
										  	 -->
										  	 <input type="button" name="client" id="client" value="Add To MRD" cssClass="form-control" onclick="showMrdPopUp()" placeholder="Search Patient" class="btn btn-primary" style="width: 100%;">
										  	 </div>
											 <!--<button type="button" class="btn btn-info btn-lg" data-toggle="modal" data-target="#newregister">Open Modal</button>
										  --></form>
										</div>
									</div>
								</div>
								
								<s:actionmessage/>
								<div class="row ">
									<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12">
										<div>
											<table class="my-table xlstable" style="width: 100%;font-size: 8px">
												<thead >
    
													<tr>
														<th style="width: 4%;color: black !important;" class="manastableheader">Sr No</th>
														<th style="width: 6%;color: black !important;" class="manastableheader">UID</th>
														<th style="width: 6%;color: black !important;" class="manastableheader">IpdId</th>
														<th style="width: 18%;color: black !important;" class="manastableheader">Patient Detail</th>
														<th style="width: 6%;color: black !important;" class="manastableheader">Date</th>
														<th style="width: 6%;color: black !important;" class="manastableheader">Adm.Date</th>
														<th style="width: 4%;color: black !important;" class="manastableheader">Dis.Date</th>
														<!-- <th style="width: 8%;">Ward / Bed</th> -->
														
														<th style="width: 4%;color: black !important;" class="manastableheader">Empanelment</th>
														<th style="width: 5%;color: black !important;" class="manastableheader">Check List</th>
														<th style="width: 7%;color: black !important;" class="manastableheader">Place</th>
														<th style="width: 10%;color: black !important;" class="manastableheader">Shelf No./ Rack</th>
														<!-- <th style="width: 6%">Delete</th> -->
														<th style="width: 10%;color: black !important;" class="manastableheader">Remark</th>
														<th style="width: 4%;text-align: center;color: black !important;" class="manastableheader">MLC</th>
														<th class="text-center manastableheader" style="color: black !important;" >Delete</th>
														<th class="manastableheader" style="color: black !important;"></th>
														<th class="manastableheader" style="color: black !important;"></th>
														<th class="manastableheader" style="color: black !important;"></th>
														<th class="manastableheader" style="color: black !important;"></th>
													</tr>
													</thead>
													<tbody>	
													<%int i=0; %>
													<s:hidden id="templateId" name="templateId"></s:hidden>
														<s:iterator value="mrdlist">
															<%++i;%>
															<!--<form action="updatemrdMrd" id="updatedata">
																--><s:hidden name="status" id="status"></s:hidden>
																	<tr>
																		<td><s:hidden name="ipdid"></s:hidden><%=i%></td>
																		<td><s:hidden name="clientid"></s:hidden><s:property value="abrivationid" /></td>
																		<td><%if(loginInfo.getIpd_abbr_access()==1){ %>
																		<s:property value="newipdabbr"/>
																		<%}else{ %>
																		<s:property value="ipdid"/>
																		<%} %>
																		</td>
																	
																		<td><s:property value="clientname"/> | <s:property value="wardname"/><s:if test="bedname!=null">/<s:property value="bedname"/></s:if></td>
																
																		<td><s:property value="lastmodfied"/></td>
																		<td><s:hidden name="id"></s:hidden><s:property value="admissiondsate"/></td>
																		<td><s:property value="dischargedate"/></td>
																		<%-- <td><s:property value="wardname"/>
																			<s:if test="bedname!=null">
																					/<s:property value="bedname"/>
																			</s:if>
																		</td> --%>	
																		
																		<td><s:property value="payby"/></td>
																			<s:if test="checklist==0 || checklist==null">
																				<td><a href="#" onclick="openPopup('addMrdTemplateDetailsAssesmentForms?clientid=<s:property value="clientid"/>&ipdid=<s:property value="ipdid"/>&mrdid=<s:property value="id"/>&templateId=<s:property value="templateID"/>')">CL</a></td>
																			</s:if>
																			<s:else>
																				<td><a href="#" onclick="openPopup('editListAssessmentForm?id=<s:property value="assessmenetid"/>&actionType=2&client_id=<s:property value="clientid"/>&mrdid=<s:property value="id"/>')">Update CL</a></td>
																			</s:else>
																		<td><s:property value="place"/></td>
																		<%-- <td><s:property value="shelf_no"/></td> --%>
																		<td><s:if test="shiftstatus==1">Shifted</s:if>
																		<s:else><s:property value="shelf_no"/></s:else>
																		</td>
																		<td><s:property value="remark"/></td>
																		<td>
																			<s:if test="mlc==1">
																				<input type="checkbox" name="mlc" id="mlc<s:property value="id"/>" checked="checked" class="checkbox"  style="margin-left: auto;margin-right: auto;" disabled="disabled">
																			</s:if>
																			<s:else>
																				<input type="checkbox" name="mlc" id="mlc<s:property value="id"/>" class="checkbox"  style="margin-left: auto;margin-right: auto;" disabled="disabled">
																			</s:else>
																		</td>
																		<td class="text-center"><a href="#" onclick="deleteMrd(<s:property value="id"/>)" class="text-danger">
										<i class="fa fa-trash-o"></i>
								</a></td>
																		<td>
																		
																			<!--<s:if test="status==1">
																				<input type="button" value="Edit" onclick="editstatus(<s:property value="ipdid"/>)" class="btn btn-sm btn-primary">							
																			</s:if>
																			<s:else>
																				<input type="submit" value="save" onclick="return saveinfo(<s:property value="ipdid"/>)" class="btn btn-sm btn-primary">
																			</s:else>
																		-->
																			<input type="button" value="Edit" onclick="editMrdDetails(<s:property value="id"/>)" class="btn btn-sm btn-primary">
																		</td>
																		 <td>
																		 <s:if test="shiftstatus==1"><input type="button" value="Issued" onclick="shiftMrdDetails1(<s:property value="id"/>)" class="btn btn-sm btn-primary"></s:if>
																		<s:else>
																		<input type="button" value="Shift" onclick="shiftMrdDetails1(<s:property value="id"/>)" class="btn btn-sm btn-primary">
																		</s:else>
													
																</td>
																<td><a href="#" onclick="openBlankPopup('Statement?clientId=<s:property value="clientid" />')" title="View Account" class="btn btn-sm btn-primary">View Acc</a></td>
																<td>
																<input type="button" onclick="redircttoNewEmr(<s:property value="clientid" />,<s:property value="practitionerid" />,'')" class="btn btn-sm btn-primary" value="EMR">
																</td>		
																</tr>
															<!--</form>
														--></s:iterator>
													</tbody>
												</table>
										
			
												
											</div>
										</div>
									</div>
								</div>
							</div>
							
<!-- Modal -->
<div id="deletemodel" class="modal fade" role="dialog">
  <div class="modal-dialog modal-sm">

    <!-- Modal content-->
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal">&times;</button>
        <h4 class="modal-title">Are You Sure To Cancel?</h4>
      </div>
      <div class="modal-body">
      		<input type="hidden" id="cancelmrdid">
        	<textarea rows="3"  class="form-control" id="delete_reason" placeholder="Cancel Reason" style="background-color: beige;"></textarea>
      </div>
      <div class="modal-footer">
        <input type="button" class="btn btn-danger" onclick="deleteMrdDetails()" data-dismiss="modal" value="Ok">
      </div>
    </div>

  </div>
</div>
							<s:form action="Mrd" name="paginationForm" id="paginationForm" theme="simple">
<div class="col-lg-12 col-md-12" style="padding:0px;margin-top:10px;">
		<div class="col-lg-4 col-md-4 text-left" style="padding:0px;">
			Total:<label class="text-info"><s:property value="totalRecords" /></label>
			<s:hidden name="searchtext"></s:hidden>
			<s:hidden name="fromdate"></s:hidden>
			<s:hidden name="todate"></s:hidden>
			<s:hidden name="searchbydate"></s:hidden>
		</div>
		<%@ include file="/common/pages/pagination.jsp"%>
	</div>
</s:form>
						</body>
						
			
        
       
				
					
						
						
						
					
					<!-- Modal -->
<div class="modal fade" id="clientSearchDiv" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
  <div class="modal-dialog modal-lg">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
        <h4 class="modal-title" id="myModalLabel">Patient Search</h4>
      </div>
      <div class="modal-body">
        <%@ include file="/ipd/pages/ipdPatientList.jsp"%>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
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
					<button type="button" class="btn btn-primary hidden" data-dismiss="modal" >Close</button>
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
		
		
<!-- New Patinet Register -->
<div id="newregister" class="modal fade" role="dialog">
  <div class="modal-dialog">

    <!-- Modal content-->
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal">&times;</button>
        <h4 class="modal-title">MRD Form</h4>
      </div>
      <div class="modal-body">
      	<div id="registerpop">
      		<div class="row">
      			<div class="col-lg-12 col-md-12 col-xs-12">
      				<s:hidden id="patient_id" name="patient_id"></s:hidden>
      				<s:hidden id="patient_ipdid" name="patient_ipdid"></s:hidden>
      				<div class="col-lg-3 col-md-3 col-xs-3 col-sm-3">
		         		<div class="form-group">
		          			<label>Patient Name</label>
		            		<p id="patient_name"></p>
		          		</div>
		        	</div>
				
					<div class="col-lg-2 col-md-2 col-xs-2 col-sm-2">
		         		<div class="form-group">
		            		<label>City</label>
		                		<p id="patient_city"></p>
		                </div>
		         	</div>
		
		        	<div class="col-lg-2 col-md-2 col-xs-2 col-sm-2">
		         		<div class="form-group">
		            		<label>Gender/Age</label>
		                	<p id="patient_gender"></p>
		            	</div>
		        	</div>
		        	<div class="col-lg-2 col-md-2 col-xs-2 col-sm-2">
		         		<div class="form-group">
		         			<label>Mobile</label>
		                	<p id="patient_phone"></p>
		            	</div>
		        	</div>
		        	<div class="col-lg-3 col-md-3 col-xs-3 col-sm-3">
		         		<div class="form-group">
		         			<label>Address</label>
		                	<p id="patient_address"></p>
		            	</div>
		        	</div>
		    	</div>
      		</div>
      		
      		<div class="row">
      			<div class="col-lg-12 col-md-12 col-xs-12">
      				<div class="col-lg-3 col-md-3 col-xs-3 col-sm-3">
		    	   		<label>UID</label>
		    	   		<p id="patient_uid"></p>
		    		</div>
		        	<div class="col-lg-3 col-md-3 col-xs-3 col-sm-3">
		        		<label>Patient From <span class="text-danger">*</span></label>
		            	<!--<s:select id="patientFrom" name="patientFrom" onchange="setPatientInforamtion(this.value)" list="{'IPD','OPD','Casualty'}"  headerKey="0" headerValue="Select" theme="simple" cssClass="form-control showToolTip chosen"/>
		            	--><s:select id="patientFrom" name="patientFrom" list="#{'IPD':'IPD','OPD':'OPD','Casualty':'Casualty'}"  headerKey="0" headerValue="Select" theme="simple" cssClass="form-control"/>
	    				<label id = "patientFromError" class="text-danger"></label>
		        	</div>
					<div class="col-lg-3 col-md-3 col-xs-3 col-sm-3">
		       		</div>
		        	<div class="col-lg-3 col-md-3 col-xs-3 col-sm-3">
		        	</div>
		      	</div>
      		</div>
      		<div class="row">
      			<div class="col-lg-12 col-md-12 col-xs-12">
      				<div class="col-lg-3 col-md-3 col-xs-3 col-sm-3">
		         		<div class="form-group" id="admissiondatediv">
		            		<label>Admission Date<span class="text-danger">*</span></label>
							<s:textfield id ="admission_date" name="admission_date" cssClass="form-control showToolTip" 
							data-toggle="tooltip" title="Enter Admission Date" theme="simple"  placeholder = "Enter Admission Date"></s:textfield>
		            		<label id = "admission_dateError" class="text-danger"></label>
		            	</div>
		         	</div>
		         	<div class="col-lg-3 col-md-3 col-xs-3 col-sm-3">
		         		<div class="form-group" id="dischargedatediv">
		            		<label>Discharge Date<span class="text-danger">*</span></label>
							<s:textfield id ="discharge_date" name="discharge_date" cssClass="form-control showToolTip" 
							data-toggle="tooltip" title="Enter Discharge Date" theme="simple"  placeholder = "Enter Discharge Date"></s:textfield>
		            		<label id = "discharge_dateError" class="text-danger"></label>
		            	</div>
		         	</div>
		         	<div class="col-lg-3 col-md-3 col-xs-3 col-sm-3">
		         		<div class="form-group" id="warddiv">
		            		<label>Ward</label>						
							<s:select id="wardid" name="wardid" onchange="selectBedFromWardid(this.value)" list="wardlist" listKey="id" listValue="wardname" headerKey="0" headerValue="Select Ward" theme="simple" cssClass="form-control showToolTip chosen"/>
		            	</div>
		         	</div>
		         	<div class="col-lg-3 col-md-3 col-xs-3 col-sm-3">
		         		<div class="form-group" id="beddiv">
		            		<label>Bed</label>						
							<!--<s:select id="bedid" name="bedid" list="{'X','Y','Z'}" headerKey="0" headerValue="Select" theme="simple" cssClass="form-control showToolTip"/>
		            		-->
		            		<SELECT id="bedid" name="bedid" class="form-control showToolTip chosen">
		            		<option value="0">Select Ward First</option>
		            		</SELECT>
		            	</div>
		         	</div>
      			</div>
     		</div>
      		<div class="row">
      			<div class="col-lg-12 col-md-12 col-xs-12">
      				<div class="col-lg-4 col-md-4 col-xs-4 col-sm-4">
		         		<div class="form-group" id="practinordiv">
		            		<label>Practinoner Name<span class="text-danger">*</span></label>						
							<s:select id="practitioner_name" name="practitioner_name" list="userList" onchange="getspeciality(this.value)" headerKey="0" headerValue="Select" listValue="diaryUser" listKey="id" theme="simple" cssClass="form-control showToolTip chosen"/>
		            		<label id = "practitioner_nameError" class="text-danger"></label>
		            	</div>
		         	</div>
		         	<div class="col-lg-4 col-md-4 col-xs-4 col-sm-4">
		         		<div class="form-group" id="specialitydiv">
		            		<label>Speciality</label>						
							<!--<s:select id="speciality" name="speciality" list="{'X','Y','Z'}" headerKey="0" headerValue="Select" theme="simple" cssClass="form-control showToolTip"/>
		            		-->
		            		<SELECT id="speciality" name="speciality" class="form-control showToolTip chosen">
		            		<option value="0">Select Doctor First</option>
		            		</SELECT>
		            	</div>
		         	</div>
		         	<div class="col-lg-4 col-md-4 col-xs-4 col-sm-4">
		         	</div>
      			</div>
      		</div>
     		<div class="row">
      			<div class="col-lg-12 col-md-12 col-xs-12">
      				<div class="col-lg-3 col-md-3 col-xs-3 col-sm-3">
		         		<div class="form-group" id="placediv">
		            		<label>Place</label>
							<s:textfield id ="new_place" name="place" cssClass="form-control showToolTip" 
							data-toggle="tooltip" title="Enter Place" theme="simple"  placeholder = "Enter PLace"></s:textfield>
		            	</div>
		         	</div>
      				<div class="col-lg-3 col-md-3 col-xs-3 col-sm-3">
		         		<div class="form-group" id="shelfdiv">
		            		<label>Shelf No/Rack</label>
		            		<s:select id="new_shelf" name="shelf" list="shelflist" headerKey="0" headerValue="Select shelf" listValue="name" listKey="id" theme="simple" cssClass="form-control showToolTip chosen"/>
							<%-- <s:textfield id ="new_shelf" name="shelf" cssClass="form-control showToolTip" 
							data-toggle="tooltip" title="Enter Shelf No" theme="simple"  placeholder = "Enter Shelf No"></s:textfield> --%>
		            	</div>
		        	</div>
		         	<div class="col-lg-3 col-md-3 col-xs-3 col-sm-3">
		         		<div class="form-group" id="remarkdiv">
		            		<label>Remark</label>
							<s:textfield id ="new_remark" name="remark" cssClass="form-control showToolTip" 
							data-toggle="tooltip" title="Enter Remark" theme="simple"  placeholder = "Enter Remark"></s:textfield>
		            	</div>
		         	</div>
		         	<div class="col-lg-3 col-md-3 col-xs-3 col-sm-3">
		         		<div class="form-group" id="mlcdiv">
		            		<label>MLC</label><br>
		            		<input type="checkbox" id="new_mlc"  class="checkbox" >
		            	</div>
		         	</div>
      			</div>
      		</div>
      		<div class="row">
      			<div class="col-lg-12 col-md-12 col-xs-12">
      				<div class="col-lg-3 col-md-3 col-xs-3 col-sm-3" id="patientypediv">
		         		<div class="form-group" id="empanelmentdiv">
		            		<label>Empanelment <span class="text-danger">*</span></label>						
							<s:select id = "new_whopay" name = "whopay" list="#{'Client':'Self','Third Party':'Third Party'}"  
							headerKey="0" onchange="showMrdHide()" cssClass="form-control showToolTip chosen"
							data-toggle="tooltip" theme="simple"></s:select>
							<label id = "new_whopayError" class="text-danger"></label>
		            	</div>
		         	</div>
		         	<div class="col-lg-3 col-md-3 col-xs-3 col-sm-3" id="smlcnodiv">
		         		<label>MLC No.</label>
							<s:textfield id ="new_mlcno" name="new_mlcno" cssClass="form-control showToolTip" 
							data-toggle="tooltip" title="Enter MLC NO" theme="simple"  placeholder = "Enter MLC NO"></s:textfield>
		         	</div>
		         	<div class="col-lg-3 col-md-3 col-xs-3 col-sm-3">
		         	</div>
		         	<div class="col-lg-3 col-md-3 col-xs-3 col-sm-3">
		         	</div>
      			</div>
      		</div>
      		
      		<div id="new_hidden_html" style="display:none;">					
 				<div class="row col-lg-12 col-md-12 col-xs-12" style="margin-top: -20px;">
					<div class="col-lg-3 col-md-3">
						<label>Type<span class="text-danger">*</span></label>
						<s:select id="new_type" name="type" list="thirdPartyTypeList" listKey="id" listValue="type" headerKey="0" cssClass="form-control showToolTip chosen"
						data-toggle="tooltip" title="Select Third Party Type"  headerValue="Select Type" onchange="setMrdTPName(this.value)" theme="simple"/>
						<label  id = "new_typeError" class="text-danger"></label>
					</div>
				<div class="col-lg-3 col-md-3" id="new_typeNamediv">
					<label>Third Party Name <span class="text-danger">*</span></label>		
					<select id = "new_typeName" name = "typeName"  class="form-control showToolTip chosen" data-toggle="tooltip" 
					theme="simple" >									
						<option value="0">Select Third Party</option>
					</select>
					<label  id ="new_typeNameError" class="text-danger"></label>	
				</div>
				<div class="col-lg-3 col-md-3 hidden">
				<br>
					<input type="button" onclick="setTypeFieldofTp()" class="btn btn-primary" value="Add Third Party">
				</div>
				<div class="col-lg-3 col-md-3">
				</div>
			</div>  
		
			<div class="row col-lg-12 col-md-12 col-xs-12">
				<div class="col-lg-3 col-md-3">
					<label>Policy No.</label>			
					<s:textfield name = "policyNo" id = "new_policyNo" cssClass="form-control showToolTip" disabled="true"
					data-toggle="tooltip" placeholder = "Enter Policy No." title="Enter Policy No." theme="simple"/>
				</div>
				<div class="col-lg-3 col-md-3">
					<label>Policy Expiry Date</label>
					<s:textfield name = "expiryDate" id = "new_expiryDate" cssClass="form-control showToolTip" disabled="true"
					data-toggle="tooltip" placeholder = "Enter Expiry Date." theme="simple" readonly = "true"/>
				</div>
				<div class="col-lg-3 col-md-3">
					<label>Policy Excess</label>
					<s:textfield name="policyExcess" id="new_policyExcess" disabled="true"
						cssClass="form-control showToolTip" data-toggle="tooltip"
						placeholder = "Enter Value"/>								
				</div>
				<div class="col-lg-3 col-md-3">
				</div>
			</div>						
	  	</div>		
    </div>
  </div>
      	<div class="modal-footer">
      	<input type="button" value="Save" class="btn btn-primary" id="saveMrd" onclick="saveToMrd()"/>
      </div>
    </div>
  </div>
</div>


<!-- Edit Patinet Register -->
<div id="editmrdregistered" class="modal fade" role="dialog">
  <div class="modal-dialog">

    <!-- Modal content-->
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal">&times;</button>
        <h4 class="modal-title">Mrd Edit Form</h4>
      </div>
      <div class="modal-body">
      	<div id="registerpop">
      		<div class="row">
      			<div class="col-lg-12 col-md-12 col-xs-12">
      				<s:hidden id="editpatient_id"></s:hidden>
      				<s:hidden id="editpatient_ipdid"></s:hidden>
      				<s:hidden id="editmrd_id"></s:hidden>
      	        	
		        		<div class="col-lg-3 col-md-3 col-xs-3 col-sm-3">
		         		<div class="form-group">
		          			<label>Patient Name</label>
		            		<p id="editpatient_name"></p>
		          		</div>
		        	</div>
				
					<div class="col-lg-2 col-md-2 col-xs-2 col-sm-2">
		         		<div class="form-group">
		            		<label>City</label>
		                		<p id="editpatient_city"></p>
		                </div>
		         	</div>
		
		        	<div class="col-lg-2 col-md-2 col-xs-2 col-sm-2">
		         		<div class="form-group">
		            		<label>Gender/Age</label>
		                	<p id="editpatient_gender"></p>
		            	</div>
		        	</div>
		        	<div class="col-lg-2 col-md-2 col-xs-2 col-sm-2">
		         		<div class="form-group">
		         			<label>Mobile</label>
		                	<p id="editpatient_phone"></p>
		            	</div>
		        	</div>
		        	<div class="col-lg-3 col-md-3 col-xs-3 col-sm-3">
		         		<div class="form-group">
		         			<label>Address</label>
		                	<p id="editpatient_address"></p>
		            	</div>
		        	</div>
		        	
		        	
		    	</div>
      		</div>
      		
      		<div class="row">
      			<div class="col-lg-12 col-md-12 col-xs-12">
      				<div class="col-lg-3 col-md-3 col-xs-3 col-sm-3">
		        		<label>UID</label>
		    	   		<p id="editpatient_uid"></p>
		        	</div>
      				<div class="col-lg-3 col-md-3 col-xs-3 col-sm-3" id="editpatientfrom">
		    	   		<label>Patient From <span class="text-danger">*</span></label>
		            	<s:select id="editpatientFrom" list="{'IPD','OPD','Casualty'}"  headerKey="0" headerValue="Select" theme="simple" cssClass="form-control showToolTip chosen"/>
	    				<label id = "editpatientFromError" class="text-danger"></label>
		    		</div>
		        	
					<div class="col-lg-3 col-md-3 col-xs-3 col-sm-3">
		       		</div>
		        	<div class="col-lg-3 col-md-3 col-xs-3 col-sm-3">
		        	</div>
		      	</div>
      		</div>
      		<div class="row">
      			<div class="col-lg-12 col-md-12 col-xs-12">
      				<div class="col-lg-3 col-md-3 col-xs-3 col-sm-3">
		         		<div class="form-group" id="editadmissiondatediv">
		            		<label>Admission Date<span class="text-danger">*</span></label>
							<s:textfield  id="editadmission_date" cssClass="form-control showToolTip" 
							data-toggle="tooltip" title="Enter Admission Date" theme="simple"  placeholder = "Enter Admission Date"></s:textfield>
		            		<label id = "editadmission_dateError" class="text-danger"></label>
		            	</div>
		         	</div>
		         	<div class="col-lg-3 col-md-3 col-xs-3 col-sm-3">
		         		<div class="form-group" id="editdischargedatediv">
		            		<label>Discharge Date<span class="text-danger">*</span></label>
							<s:textfield id ="editdischarge_date" cssClass="form-control showToolTip" 
							data-toggle="tooltip" title="Enter Discharge Date" theme="simple"  placeholder = "Enter Discharge Date"></s:textfield>
		            		<label id = "editdischarge_dateError" class="text-danger"></label>
		            	</div>
		         	</div>
		         	<div class="col-lg-3 col-md-3 col-xs-3 col-sm-3">
		         		<div class="form-group" id="editwarddiv">
		            		<label>Select Ward</label>						
							<s:select id="editwardid" onchange="selectBedFromWardid(this.value)" list="wardlist" listKey="id" listValue="wardname" headerKey="0" headerValue="Select Ward" theme="simple" cssClass="form-control showToolTip chosen"/>
		            	</div>
		         	</div>
		         	<div class="col-lg-3 col-md-3 col-xs-3 col-sm-3">
		         		<div class="form-group" id="editbeddiv">
		            		<label>Select Bed</label>						
							<SELECT id="editbedid" class="form-control showToolTip chosen">
		            		<option value="0">Select Ward First</option>
		            		</SELECT>
		            	</div>
		         	</div>
      			</div>
     		</div>
      		<div class="row">
      			<div class="col-lg-12 col-md-12 col-xs-12">
      				<div class="col-lg-4 col-md-4 col-xs-4 col-sm-4">
		         		<div class="form-group" id="editpractinordiv">
		            		<label>Practitioner Name<span class="text-danger">*</span></label>						
							<s:select id="editpractitioner_name"  list="userList" onchange="getspeciality(this.value)" headerKey="0" headerValue="Select" listValue="diaryUser" listKey="id" theme="simple" cssClass="form-control showToolTip chosen"/>
		            		<label id = "editpractitioner_nameError" class="text-danger"></label>
		            	</div>
		         	</div>
		         	<div class="col-lg-4 col-md-4 col-xs-4 col-sm-4">
		         		<div class="form-group" id="editspecialitydiv">
		            		<label>Speciality</label>						
							
							<SELECT id="editspeciality" class="form-control showToolTip chosen">
		            		<option value="0">Select Doctor First</option>
		            		</SELECT>
		            	</div>
		         	</div>
		         	<div class="col-lg-4 col-md-4 col-xs-4 col-sm-4">
		         	</div>
      			</div>
      		</div>
     		<div class="row">
      			<div class="col-lg-12 col-md-12 col-xs-12">
      				<div class="col-lg-3 col-md-3 col-xs-3 col-sm-3">
		         		<div class="form-group" id="editplacediv">
		            		<label>Place</label>
							<s:textfield id ="editnew_place" cssClass="form-control showToolTip" 
							data-toggle="tooltip" title="Enter Place" theme="simple"  placeholder = "Enter PLace"></s:textfield>
		            	</div>
		         	</div>
      				<div class="col-lg-3 col-md-3 col-xs-3 col-sm-3">
		         		<div class="form-group" id="editshelfdiv">
		            		<label>Shelf No / Rack</label>
		            		<s:textfield id ="editnew_shelf" cssClass="form-control showToolTip" 
							data-toggle="tooltip" title="Enter Shelf No" theme="simple"  placeholder = "Enter Shelf No"></s:textfield>
		            	</div>
		        	</div>
		         	
		         	<div class="col-lg-3 col-md-3 col-xs-3 col-sm-3">
		         		<div class="form-group" id="editremarkdiv">
		            		<label>Remark</label>
							<s:textfield id ="edit_new_remark" cssClass="form-control showToolTip" 
							data-toggle="tooltip" title="Enter Remark" theme="simple"  placeholder = "Enter Remark"></s:textfield>
		            	</div>
		         	</div>
		         	<div class="col-lg-3 col-md-3 col-xs-3 col-sm-3">
		         		<div class="form-group" id="editmlcdiv">
		            		<label>MLC</label><br>
		            		<input type="checkbox" name="editnew_mlc" id="editnew_mlc" >
		            	</div>
		         	</div>
      			</div>
      		</div>
      		<div class="row">
      			<div class="col-lg-12 col-md-12 col-xs-12">
      				<div class="col-lg-3 col-md-3 col-xs-3 col-sm-3" id="editmlcnodiv">
		         			<label>MLC No.</label>
							<s:textfield id ="editnew_mlcno" name="new_mlcno" cssClass="form-control" 
							data-toggle="tooltip" title="Enter MLC NO" theme="simple"  placeholder = "Enter MLC NO"></s:textfield>
		         	</div>
      				<div class="col-lg-3 col-md-3 col-xs-3 col-sm-3">
		         		<div class="form-group hidden" id="editempanelmentdiv">
		            		<label>Empanelment <span class="text-danger">*</span></label>						
							<s:select id = "editnew_whopay" list="#{'Client':'Self','Third Party':'Third Party'}"  
							headerKey="0" onchange="showMrdHide()" cssClass="form-control showToolTip chosen"
							data-toggle="tooltip" theme="simple"></s:select>
							<label id = "new_whopayError" class="text-danger"></label>
		            	</div>
		         	</div>
		         	
		         	<div class="col-lg-3 col-md-3 col-xs-3 col-sm-3">
		         	</div>
		         	<div class="col-lg-3 col-md-3 col-xs-3 col-sm-3">
		         	</div>
      			</div>
      		</div>
      		<div id="new_hidden_html" style="display:none;">					
 				<div class="row col-lg-12 col-md-12 col-xs-12" style="margin-top: -20px;">
					<div class="col-lg-3 col-md-3" id="edittpediv">
						<label>Type<span class="text-danger">*</span></label>
						<s:select id="editnew_type" list="thirdPartyTypeList" listKey="id" listValue="type" headerKey="0" cssClass="form-control showToolTip chosen"
						data-toggle="tooltip" title="Select Third Party Type"  headerValue="Select Type" onchange="setMrdTPName(this.value)" theme="simple"/>
						<label  id = "new_typeError" class="text-danger"></label>
					</div>
				<div class="col-lg-3 col-md-3" id="edittypeNamediv">
					<label>Third Party Name <span class="text-danger">*</span></label>		
					<select id = "editnew_typeName"  class="form-control showToolTip chosen" data-toggle="tooltip" 
					theme="simple" >									
						<option value="0">Select Third Party</option>
					</select>
					<label  id ="editnew_typeNameError" class="text-danger"></label>	
				</div>
				<div class="col-lg-3 col-md-3 hidden">
				<br>
					<input type="button" onclick="setTypeFieldofTp()" class="btn btn-primary" value="Add Third Party">
				</div>
				<div class="col-lg-3 col-md-3">
				</div>
			</div>  
		
			<div class="row col-lg-12 col-md-12 col-xs-12">
				<div class="col-lg-3 col-md-3" id="editnew_policyNo">
					<label>Policy No.</label>			
					<s:textfield  id = "editnew_policyNo" cssClass="form-control showToolTip" disabled="true"
					data-toggle="tooltip" placeholder = "Enter Policy No." title="Enter Policy No." theme="simple"/>
				</div>
				<div class="col-lg-3 col-md-3" id="editnew_expiryDatediv">
					<label>Policy Expiry Date</label>
					<s:textfield  id ="editnew_expiryDate" cssClass="form-control showToolTip" disabled="true"
					data-toggle="tooltip" placeholder = "Enter Expiry Date." theme="simple" readonly = "true"/>
				</div>
				<div class="col-lg-3 col-md-3" id="editnew_policyExcessdiv">
					<label>Policy Excess</label>
					<s:textfield  id="editnew_policyExcess" disabled="true"
						cssClass="form-control showToolTip" data-toggle="tooltip"
						placeholder = "Enter Value"/>								
				</div>
				<div class="col-lg-3 col-md-3">
				</div>
			</div>						
	  	</div>		
    </div>
  </div>
      	<div class="modal-footer" id="btnupdate">
      	<input type="button" value="Update" class="btn btn-primary" id="updatemrd" onclick="updateInformation()"/>
      </div>
    </div>
  </div>
</div>
	
	
	
<div id="saveShiftMrdDetails" class="modal fade" role="dialog">
  <div class="modal-dialog mrdshiftdetailspopup">

    Modal content
   <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal">&times;</button>
        <h4 class="modal-title">Mrd Shift Details</h4>
      </div>
      <div class="modal-body">
      <s:hidden id="shiftpatient_id"></s:hidden>
      				<s:hidden id="shiftpatient_ipdid"></s:hidden>
      				<s:hidden id="shiftpatienmrd_id"></s:hidden>
      				<s:hidden id="shiftstatus"></s:hidden>
      <div class="col-lg-2 col-md-2 col-xs-2 col-sm-2">
		         		<div class="form-group">
		            		<label><b>UHID</b></label>
		                	<p id="shiftpatientuhid"></p>
		            	</div>
		        	</div>
      	<div class="col-lg-3 col-md-3 col-xs-3 col-sm-3">
		         		<div class="form-group">
		          			<label><b>Patient Name</b></label>
		            		<p id="shiftpatientname"></p>
		          		</div>
		        	</div>
      	<div class="col-lg-2 col-md-2 col-xs-2 col-sm-2">
		         		<div class="form-group">
		            		<label><b>Gender/Age</b></label>
		                	<p id="shiftpatientgender"></p>
		            	</div>
		        	</div>
		        	
		        	<div class="col-lg-2 col-md-2 col-xs-2 col-sm-2">
		         		<div class="form-group">
		            		<label><b>City</b></label>
		                	<p id="shiftpatientcity"></p>
		            	</div>
		        	</div>
		        	
		        	<div class="form-group">
								<a onclick="addmrdshiftrow()"  class="btn btn-primary"><i class="fa fa-plus"></i> Shift</a>
      	<!-- <td>UHID No.</td>
      	<td>Gender/Age</td>
      	<td>City</td> -->
      	
  </div>
   </div>
  <div>
  
 <table class="table table-bordered" >
  <thead>
  <tr class="tableback">
             
              <!-- <th style="width: 12%;">UID</th> -->
              <th style="width: 16.6%;">Issued</th>
              <th style="width: 16.6%" >Issued Date</th>
              <th style="width: 16.6%;" >Received From</th>
              <th style="width: 16.6%;" >Received Date</th>
              <th style="width: 16.6%;">Remark</th>
              <th style="width: 16.6%;">Action</th>
              <!-- <th style="width: 15%;">Received</th> -->
              
              <!-- <th style="width: 8%;">Dis.Date</th>
              <th style="width: 8%;">Ward / Bed</th>
              
              <th style="width: 10%">Empanelment</th>
              <th style="width: 9%">Check List</th>
              <th style="width: 7%">Place</th>
              <th style="width: 10%">Shelf No./ Rack</th>
              <th style="width: 10%">Remark</th>
              <th style="width: 4%;text-align: center;">MLC</th> -->
              <th></th>
              <!-- <th style="width: 10%">Shift</th> -->
             
  </tr>
  </thead>
  <tbody id="shiftmrdtbody"></tbody>
  </table>
  
  </div>
  
  <!-- <div class="modal-footer">
      	<input type="button" value="Save" class="btn btn-primary" id="saveshiftToMrd" onclick="saveshiftToMrd()"/>
      </div> -->
  
  
      	<!-- <div class="modal-footer" id="btnupdate">
      	<input type="button" value="Update" class="btn btn-primary" id="updatemrd" onclick="updateInformation()"/>
      </div> -->
    </div>
      <div class="modal-body">
         </div>
  </div>
</div> 

<!-- </div> -->
	
	
	<s:form action="getPatientRecordEmr" id="getPatientRecord" method="post" onsubmit="return redircttoNewEmr()" target="formtarget">
        <s:hidden name="diaryUser" id="diaryUserApmt"/>
        <s:hidden id = "conditionsApmt"  name = "conditionsApmt"></s:hidden>
        <s:hidden id="clientnameApmt" name="clientname"/>
        <s:hidden name="action" id="hdnaction"/>
        <s:hidden name="caldate" id="caldate"/>
        <s:hidden name="apmtId" id="apmtId"/>
             
   </s:form>
	
	
	
	
	<script type="text/javascript" src="_assets/newtheme/js/vendor/slimscroll/jquery.slimscroll.min.js"></script>
	<script>
		$(function() {
 $('#registerpop').slimScroll({
		        height: '492px',
		        railVisible: true,
				alwaysVisible: true
		    });

 });
	</script>
	
					  <script>
   $(document).ready(function () {
	    $(".disblebtnone").on("click", function() {
	        $(this).attr("disabled", "disabled");
	        doWork();
	    });
	});
   </script>
					
 </html>


<%-- =======
<%@ taglib uri="/struts-tags" prefix="s" %>


<!DOCTYPE html>
<html lang="en">
<head>
    <title>Admission Summary Form</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <style>

.mainheader {
    background-color: #339966 !important;
}
    </style>
    
    
   <script type="text/javascript" src="common/js/pagination.js"></script>
 <SCRIPT type="text/javascript" >
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
		
		$("#admission_date").datepicker({

			dateFormat : 'dd-mm-yy',
			yearRange: yearrange,
			minDate : '30-12-1880',
			changeMonth : true,
			changeYear : true

		});
		
		$("#discharge_date").datepicker({

			dateFormat : 'dd-mm-yy',
			yearRange: yearrange,
			minDate : '30-12-1880',
			changeMonth : true,
			changeYear : true

		});
		$("#new_expiryDate").datepicker({

			dateFormat : 'dd-mm-yy',
			yearRange: yearrange,
			minDate : '30-12-1880',
			changeMonth : true,
			changeYear : true

		});
	});
    
    </SCRIPT>    
    <script type="text/javascript" src="mrd/js/mrd.js"></script>
 
</head>



<body>
   <body>
<div class="">
							<div class="">
								<div class="row details mainheader">
									<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12">
								<div class="col-lg-1 col-md-1 col-sm-1 col-xs-1 oneseticonleft">
									<img src="dashboardicon/mrd.png" class="img-responsive prescripiconcircle">
								</div>
								<div class="col-lg-11 col-md-11 col-sm-11 col-xs-11 titlestleftiocn">
								<h4>MRD Dashboard</h4>
								</div>
									</div>
									<div class="col-lg-12 col-md-12 paddingnil">
										<div class="col-lg-12 col-md-12 topback2">
										<form id="mrdsearch" name="mrdsearch" action="Mrd" method="post" class="form-inline">
											  <div class="form-group">
											     <s:textfield name="searchtext"  cssClass="form-control"  placeholder="Search Patient Name / Reg.no"/>
											  </div>
											  <div class="form-group">
											  	<select class="form-control" name="patient_department">
												  	<option value="0">Select Department</option>
												  	<option value="IPD">IPD</option>
												  	<option value="OPD">OPD</option>
												  	<option value="Casualty">Casualty</option>
												 </select>
											  
											  <!--<s:select list="wardlist" id="wardnameid" name="wardnameid" listKey="id" listValue="wardname" headerKey="0" headerValue="Select Ward" cssClass="form-control">
											  </s:select>
											  -->
											  </div>
											  <div class="form-group">
											    <s:textfield id="fromdate" name="fromdate"  cssClass="form-control" placeholder="From Date" />
											  </div>
											  <div class="form-group">
											    <s:textfield  name="todate" id="todate"  cssClass="form-control" placeholder="To Date" />
											  </div>
											  <input type="submit" value="GO" class="btn btn-primary">
 
											  <a href="#" onclick="goreferesh()" class="btn btn-primary" title="Refresh"><i class="fa fa-refresh"></i></a>
										  	  <div class="form-group">
										  		<input type="button" class="btn btn-primary" data-toggle="modal" data-target="#addPatientDiv"  value="Add New Patient">
										  	  	
										  	  	<!--<a class="btn btn-primary" href="#addPatientDiv" data-target="#myModal" style="margin-top:21px;"><i
													class="fa fa-plus"></i> Add</a>										  	  
											 --></div>
											 <div class="form-group">
										  		<!--<input type="button" class="btn btn-primary" data-toggle="modal"  value="Add To Mrd">
										  	 -->
										  	 <input type="button" name="client" id="client" value="Add To MRD" cssClass="form-control" onclick="showMrdPopUp()" placeholder="Search Patient" class="btn btn-primary" style="width: 100%;">
										  	 </div>
											 <!--<button type="button" class="btn btn-info btn-lg" data-toggle="modal" data-target="#newregister">Open Modal</button>
										  --></form>
										</div>
									</div>
								</div>
								
								<s:actionmessage/>
								<div class="row ">
									<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12">
										<div>
											<table class="my-table xlstable" style="width: 100%;font-size: 8px">
												<thead>
													<tr>
														<th style="width: 4%;">Sr No</th>
														<th style="width: 8%;">UID</th>
														<th style="width: 18%;">Patient Detail</th>
														<th style="width: 10%">Date</th>
														<th style="width: 8%;">Adm.Date</th>
														<th style="width: 8%;">Dis.Date</th>
														<!-- <th style="width: 8%;">Ward / Bed</th> -->
														
														<th style="width: 10%">Empanelment</th>
														<th style="width: 9%">Check List</th>
														<th style="width: 7%">Place</th>
														<th style="width: 10%">Shelf No./ Rack</th>
														<th style="width: 10%">Remark</th>
														<th style="width: 4%;text-align: center;">MLC</th>
														<th></th>
													</tr>
													</thead>
													<tbody>	
													<%int i=0; %>
												<%int k=0; %>
													<s:hidden id="templateId" name="templateId"></s:hidden>
														<s:iterator value="mrdlist">
															<%++k;%>
															<!--<form action="updatemrdMrd" id="updatedata">
																--><s:hidden name="status" id="status"></s:hidden>
																	<tr>
																		<td><s:hidden name="ipdid"></s:hidden><%=i%></td>
																		<td><s:hidden name="clientid"></s:hidden><s:property value="abrivationid" /></td>
																		<td><s:property value="clientname"/> | <s:property value="wardname"/><s:if test="bedname!=null">/<s:property value="bedname"/></s:if></td>
																		<td><s:property value="lastmodfied"/></td>
																		<td><s:hidden name="id"></s:hidden><s:property value="admissiondsate"/></td>
																		<td><s:property value="dischargedate"/></td>
																		<td><s:property value="wardname"/>
																			<s:if test="bedname!=null">
																					/<s:property value="bedname"/>
																			</s:if>
																		</td>	
																		
																		<td><s:property value="payby"/></td>
																			<s:if test="checklist==0 || checklist==null">
																				<td><a href="#" onclick="openPopup('addMrdTemplateDetailsAssesmentForms?clientid=<s:property value="clientid"/>&ipdid=<s:property value="ipdid"/>&mrdid=<s:property value="id"/>&templateId=<s:property value="templateID"/>')">CL</a></td>
																			</s:if>
																			<s:else>
																				<td><a href="#" onclick="openPopup('editListAssessmentForm?id=<s:property value="assessmenetid"/>&actionType=2&client_id=<s:property value="clientid"/>&mrdid=<s:property value="id"/>')">Update CL</a></td>
																			</s:else>
																		<td><s:property value="place"/></td>
																		<td><s:property value="shelf_no"/></td>
																		<td><s:property value="remark"/></td>
																		<td>
																			<s:if test="mlc==1">
																				<input type="checkbox" name="mlc" id="mlc<s:property value="id"/>" checked="checked" class="checkbox"  style="margin-left: auto;margin-right: auto;" disabled="disabled">
																			</s:if>
																			<s:else>
																				<input type="checkbox" name="mlc" id="mlc<s:property value="id"/>" class="checkbox"  style="margin-left: auto;margin-right: auto;" disabled="disabled">
																			</s:else>
																		</td>
																		<td>
																			<!--<s:if test="status==1">
																				<input type="button" value="Edit" onclick="editstatus(<s:property value="ipdid"/>)" class="btn btn-sm btn-primary">							
																			</s:if>
																			<s:else>
																				<input type="submit" value="save" onclick="return saveinfo(<s:property value="ipdid"/>)" class="btn btn-sm btn-primary">
																			</s:else>
																		-->
																			<input type="button" value="Edit" onclick="editMrdDetails(<s:property value="id"/>)" class="btn btn-sm btn-primary">
																		</td>
																</tr>
															<!--</form>
														--></s:iterator>
													</tbody>
												</table>
										
			
												
											</div>
										</div>
									</div>
								</div>
							</div>
							<s:form action="Mrd" name="paginationForm" id="paginationForm" theme="simple">
<div class="col-lg-12 col-md-12" style="padding:0px;margin-top:10px;">
		<div class="col-lg-4 col-md-4 text-left" style="padding:0px;">
			Total:<label class="text-info"><s:property value="totalRecords" /></label>
			<s:hidden name="searchtext"></s:hidden>
			<s:hidden name="fromdate"></s:hidden>
			<s:hidden name="todate"></s:hidden>
			
		</div>
		<%@ include file="/common/pages/pagination.jsp"%>
	</div>
</s:form>
						</body>
						
			
        
       
				
					
						
						
						
					
					<!-- Modal -->
<div class="modal fade" id="clientSearchDiv" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
  <div class="modal-dialog modal-lg">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
        <h4 class="modal-title" id="myModalLabel">Patient Search</h4>
      </div>
      <div class="modal-body">
        <%@ include file="/ipd/pages/ipdPatientList.jsp"%>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
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
					<button type="button" class="btn btn-primary hidden" data-dismiss="modal" >Close</button>
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
		
		
<!-- New Patinet Register -->
<div id="newregister" class="modal fade" role="dialog">
  <div class="modal-dialog">

    <!-- Modal content-->
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal">&times;</button>
        <h4 class="modal-title">MRD Form</h4>
      </div>
      <div class="modal-body">
      	<div id="registerpop">
      		<div class="row">
      			<div class="col-lg-12 col-md-12 col-xs-12">
      				<s:hidden id="patient_id" name="patient_id"></s:hidden>
      				<s:hidden id="patient_ipdid" name="patient_ipdid"></s:hidden>
      				<div class="col-lg-3 col-md-3 col-xs-3 col-sm-3">
		         		<div class="form-group">
		          			<label>Patient Name</label>
		            		<p id="patient_name"></p>
		          		</div>
		        	</div>
				
					<div class="col-lg-2 col-md-2 col-xs-2 col-sm-2">
		         		<div class="form-group">
		            		<label>City</label>
		                		<p id="patient_city"></p>
		                </div>
		         	</div>
		
		        	<div class="col-lg-2 col-md-2 col-xs-2 col-sm-2">
		         		<div class="form-group">
		            		<label>Gender/Age</label>
		                	<p id="patient_gender"></p>
		            	</div>
		        	</div>
		        	<div class="col-lg-2 col-md-2 col-xs-2 col-sm-2">
		         		<div class="form-group">
		         			<label>Mobile</label>
		                	<p id="patient_phone"></p>
		            	</div>
		        	</div>
		        	<div class="col-lg-3 col-md-3 col-xs-3 col-sm-3">
		         		<div class="form-group">
		         			<label>Address</label>
		                	<p id="patient_address"></p>
		            	</div>
		        	</div>
		    	</div>
      		</div>
      		
      		<div class="row">
      			<div class="col-lg-12 col-md-12 col-xs-12">
      				<div class="col-lg-3 col-md-3 col-xs-3 col-sm-3">
		    	   		<label>UID</label>
		    	   		<p id="patient_uid"></p>
		    		</div>
		        	<div class="col-lg-3 col-md-3 col-xs-3 col-sm-3">
		        		<label>Patient From <span class="text-danger">*</span></label>
		            	<!--<s:select id="patientFrom" name="patientFrom" onchange="setPatientInforamtion(this.value)" list="{'IPD','OPD','Casualty'}"  headerKey="0" headerValue="Select" theme="simple" cssClass="form-control showToolTip chosen"/>
		            	--><s:select id="patientFrom" name="patientFrom" list="#{'IPD':'IPD','OPD':'OPD','Casualty':'Casualty'}"  headerKey="0" headerValue="Select" theme="simple" cssClass="form-control"/>
	    				<label id = "patientFromError" class="text-danger"></label>
		        	</div>
					<div class="col-lg-3 col-md-3 col-xs-3 col-sm-3">
		       		</div>
		        	<div class="col-lg-3 col-md-3 col-xs-3 col-sm-3">
		        	</div>
		      	</div>
      		</div>
      		<div class="row">
      			<div class="col-lg-12 col-md-12 col-xs-12">
      				<div class="col-lg-3 col-md-3 col-xs-3 col-sm-3">
		         		<div class="form-group" id="admissiondatediv">
		            		<label>Admission Date<span class="text-danger">*</span></label>
							<s:textfield id ="admission_date" name="admission_date" cssClass="form-control showToolTip" 
							data-toggle="tooltip" title="Enter Admission Date" theme="simple"  placeholder = "Enter Admission Date"></s:textfield>
		            		<label id = "admission_dateError" class="text-danger"></label>
		            	</div>
		         	</div>
		         	<div class="col-lg-3 col-md-3 col-xs-3 col-sm-3">
		         		<div class="form-group" id="dischargedatediv">
		            		<label>Discharge Date<span class="text-danger">*</span></label>
							<s:textfield id ="discharge_date" name="discharge_date" cssClass="form-control showToolTip" 
							data-toggle="tooltip" title="Enter Discharge Date" theme="simple"  placeholder = "Enter Discharge Date"></s:textfield>
		            		<label id = "discharge_dateError" class="text-danger"></label>
		            	</div>
		         	</div>
		         	<div class="col-lg-3 col-md-3 col-xs-3 col-sm-3">
		         		<div class="form-group" id="warddiv">
		            		<label>Ward</label>						
							<s:select id="wardid" name="wardid" onchange="selectBedFromWardid(this.value)" list="wardlist" listKey="id" listValue="wardname" headerKey="0" headerValue="Select Ward" theme="simple" cssClass="form-control showToolTip chosen"/>
		            	</div>
		         	</div>
		         	<div class="col-lg-3 col-md-3 col-xs-3 col-sm-3">
		         		<div class="form-group" id="beddiv">
		            		<label>Bed</label>						
							<!--<s:select id="bedid" name="bedid" list="{'X','Y','Z'}" headerKey="0" headerValue="Select" theme="simple" cssClass="form-control showToolTip"/>
		            		-->
		            		<SELECT id="bedid" name="bedid" class="form-control showToolTip chosen">
		            		<option value="0">Select Ward First</option>
		            		</SELECT>
		            	</div>
		         	</div>
      			</div>
     		</div>
      		<div class="row">
      			<div class="col-lg-12 col-md-12 col-xs-12">
      				<div class="col-lg-4 col-md-4 col-xs-4 col-sm-4">
		         		<div class="form-group" id="practinordiv">
		            		<label>Practinoner Name<span class="text-danger">*</span></label>						
							<s:select id="practitioner_name" name="practitioner_name" list="userList" onchange="getspeciality(this.value)" headerKey="0" headerValue="Select" listValue="diaryUser" listKey="id" theme="simple" cssClass="form-control showToolTip chosen"/>
		            		<label id = "practitioner_nameError" class="text-danger"></label>
		            	</div>
		         	</div>
		         	<div class="col-lg-4 col-md-4 col-xs-4 col-sm-4">
		         		<div class="form-group" id="specialitydiv">
		            		<label>Speciality</label>						
							<!--<s:select id="speciality" name="speciality" list="{'X','Y','Z'}" headerKey="0" headerValue="Select" theme="simple" cssClass="form-control showToolTip"/>
		            		-->
		            		<SELECT id="speciality" name="speciality" class="form-control showToolTip chosen">
		            		<option value="0">Select Doctor First</option>
		            		</SELECT>
		            	</div>
		         	</div>
		         	<div class="col-lg-4 col-md-4 col-xs-4 col-sm-4">
		         	</div>
      			</div>
      		</div>
     		<div class="row">
      			<div class="col-lg-12 col-md-12 col-xs-12">
      				<div class="col-lg-3 col-md-3 col-xs-3 col-sm-3">
		         		<div class="form-group" id="placediv">
		            		<label>Place</label>
							<s:textfield id ="new_place" name="place" cssClass="form-control showToolTip" 
							data-toggle="tooltip" title="Enter Place" theme="simple"  placeholder = "Enter PLace"></s:textfield>
		            	</div>
		         	</div>
      				<div class="col-lg-3 col-md-3 col-xs-3 col-sm-3">
		         		<div class="form-group" id="shelfdiv">
		            		<label>Shelf No/Rack</label>
		            		<s:select id="new_shelf" name="shelf" list="shelflist" headerKey="0" headerValue="Select shelf" listValue="name" listKey="id" theme="simple" cssClass="form-control showToolTip chosen"/>
							<s:textfield id ="new_shelf" name="shelf" cssClass="form-control showToolTip" 
							data-toggle="tooltip" title="Enter Shelf No" theme="simple"  placeholder = "Enter Shelf No"></s:textfield>
		            	</div>
		        	</div>
		         	<div class="col-lg-3 col-md-3 col-xs-3 col-sm-3">
		         		<div class="form-group" id="remarkdiv">
		            		<label>Remark</label>
							<s:textfield id ="new_remark" name="remark" cssClass="form-control showToolTip" 
							data-toggle="tooltip" title="Enter Remark" theme="simple"  placeholder = "Enter Remark"></s:textfield>
		            	</div>
		         	</div>
		         	<div class="col-lg-3 col-md-3 col-xs-3 col-sm-3">
		         		<div class="form-group" id="mlcdiv">
		            		<label>MLC</label><br>
		            		<input type="checkbox" id="new_mlc"  class="checkbox" >
		            	</div>
		         	</div>
      			</div>
      		</div>
      		<div class="row">
      			<div class="col-lg-12 col-md-12 col-xs-12">
      				<div class="col-lg-3 col-md-3 col-xs-3 col-sm-3" id="patientypediv">
		         		<div class="form-group" id="empanelmentdiv">
		            		<label>Empanelment <span class="text-danger">*</span></label>						
							<s:select id = "new_whopay" name = "whopay" list="#{'Client':'Self','Third Party':'Third Party'}"  
							headerKey="0" onchange="showMrdHide()" cssClass="form-control showToolTip chosen"
							data-toggle="tooltip" theme="simple"></s:select>
							<label id = "new_whopayError" class="text-danger"></label>
		            	</div>
		         	</div>
		         	<div class="col-lg-3 col-md-3 col-xs-3 col-sm-3" id="smlcnodiv">
		         		<label>MLC No.</label>
							<s:textfield id ="new_mlcno" name="new_mlcno" cssClass="form-control showToolTip" 
							data-toggle="tooltip" title="Enter MLC NO" theme="simple"  placeholder = "Enter MLC NO"></s:textfield>
		         	</div>
		         	<div class="col-lg-3 col-md-3 col-xs-3 col-sm-3">
		         	</div>
		         	<div class="col-lg-3 col-md-3 col-xs-3 col-sm-3">
		         	</div>
      			</div>
      		</div>
      		
      		<div id="new_hidden_html" style="display:none;">					
 				<div class="row col-lg-12 col-md-12 col-xs-12" style="margin-top: -20px;">
					<div class="col-lg-3 col-md-3">
						<label>Type<span class="text-danger">*</span></label>
						<s:select id="new_type" name="type" list="thirdPartyTypeList" listKey="id" listValue="type" headerKey="0" cssClass="form-control showToolTip chosen"
						data-toggle="tooltip" title="Select Third Party Type"  headerValue="Select Type" onchange="setMrdTPName(this.value)" theme="simple"/>
						<label  id = "new_typeError" class="text-danger"></label>
					</div>
				<div class="col-lg-3 col-md-3" id="new_typeNamediv">
					<label>Third Party Name <span class="text-danger">*</span></label>		
					<select id = "new_typeName" name = "typeName"  class="form-control showToolTip chosen" data-toggle="tooltip" 
					theme="simple" >									
						<option value="0">Select Third Party</option>
					</select>
					<label  id ="new_typeNameError" class="text-danger"></label>	
				</div>
				<div class="col-lg-3 col-md-3 hidden">
				<br>
					<input type="button" onclick="setTypeFieldofTp()" class="btn btn-primary" value="Add Third Party">
				</div>
				<div class="col-lg-3 col-md-3">
				</div>
			</div>  
		
			<div class="row col-lg-12 col-md-12 col-xs-12">
				<div class="col-lg-3 col-md-3">
					<label>Policy No.</label>			
					<s:textfield name = "policyNo" id = "new_policyNo" cssClass="form-control showToolTip" disabled="true"
					data-toggle="tooltip" placeholder = "Enter Policy No." title="Enter Policy No." theme="simple"/>
				</div>
				<div class="col-lg-3 col-md-3">
					<label>Policy Expiry Date</label>
					<s:textfield name = "expiryDate" id = "new_expiryDate" cssClass="form-control showToolTip" disabled="true"
					data-toggle="tooltip" placeholder = "Enter Expiry Date." theme="simple" readonly = "true"/>
				</div>
				<div class="col-lg-3 col-md-3">
					<label>Policy Excess</label>
					<s:textfield name="policyExcess" id="new_policyExcess" disabled="true"
						cssClass="form-control showToolTip" data-toggle="tooltip"
						placeholder = "Enter Value"/>								
				</div>
				<div class="col-lg-3 col-md-3">
				</div>
			</div>						
	  	</div>		
    </div>
  </div>
      	<div class="modal-footer">
      	<input type="button" value="Save" class="btn btn-primary" id="saveMrd" onclick="saveToMrd()"/>
      </div>
    </div>
  </div>
</div>


<!-- Edit Patinet Register -->
<div id="editmrdregistered" class="modal fade" role="dialog">
  <div class="modal-dialog">

    <!-- Modal content-->
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal">&times;</button>
        <h4 class="modal-title">Mrd Edit Form</h4>
      </div>
      <div class="modal-body">
      	<div id="registerpop">
      		<div class="row">
      			<div class="col-lg-12 col-md-12 col-xs-12">
      				<s:hidden id="editpatient_id"></s:hidden>
      				<s:hidden id="editpatient_ipdid"></s:hidden>
      				<s:hidden id="editmrd_id"></s:hidden>
      	        	
		        		<div class="col-lg-3 col-md-3 col-xs-3 col-sm-3">
		         		<div class="form-group">
		          			<label>Patient Name</label>
		            		<p id="editpatient_name"></p>
		          		</div>
		        	</div>
				
					<div class="col-lg-2 col-md-2 col-xs-2 col-sm-2">
		         		<div class="form-group">
		            		<label>City</label>
		                		<p id="editpatient_city"></p>
		                </div>
		         	</div>
		
		        	<div class="col-lg-2 col-md-2 col-xs-2 col-sm-2">
		         		<div class="form-group">
		            		<label>Gender/Age</label>
		                	<p id="editpatient_gender"></p>
		            	</div>
		        	</div>
		        	<div class="col-lg-2 col-md-2 col-xs-2 col-sm-2">
		         		<div class="form-group">
		         			<label>Mobile</label>
		                	<p id="editpatient_phone"></p>
		            	</div>
		        	</div>
		        	<div class="col-lg-3 col-md-3 col-xs-3 col-sm-3">
		         		<div class="form-group">
		         			<label>Address</label>
		                	<p id="editpatient_address"></p>
		            	</div>
		        	</div>
		        	
		        	
		    	</div>
      		</div>
      		
      		<div class="row">
      			<div class="col-lg-12 col-md-12 col-xs-12">
      				<div class="col-lg-3 col-md-3 col-xs-3 col-sm-3">
		        		<label>UID</label>
		    	   		<p id="editpatient_uid"></p>
		        	</div>
      				<div class="col-lg-3 col-md-3 col-xs-3 col-sm-3" id="editpatientfrom">
		    	   		<label>Patient From <span class="text-danger">*</span></label>
		            	<s:select id="editpatientFrom" list="{'IPD','OPD','Casualty'}"  headerKey="0" headerValue="Select" theme="simple" cssClass="form-control showToolTip chosen"/>
	    				<label id = "editpatientFromError" class="text-danger"></label>
		    		</div>
		        	
					<div class="col-lg-3 col-md-3 col-xs-3 col-sm-3">
		       		</div>
		        	<div class="col-lg-3 col-md-3 col-xs-3 col-sm-3">
		        	</div>
		      	</div>
      		</div>
      		<div class="row">
      			<div class="col-lg-12 col-md-12 col-xs-12">
      				<div class="col-lg-3 col-md-3 col-xs-3 col-sm-3">
		         		<div class="form-group" id="editadmissiondatediv">
		            		<label>Admission Date<span class="text-danger">*</span></label>
							<s:textfield  id="editadmission_date" cssClass="form-control showToolTip" 
							data-toggle="tooltip" title="Enter Admission Date" theme="simple"  placeholder = "Enter Admission Date"></s:textfield>
		            		<label id = "editadmission_dateError" class="text-danger"></label>
		            	</div>
		         	</div>
		         	<div class="col-lg-3 col-md-3 col-xs-3 col-sm-3">
		         		<div class="form-group" id="editdischargedatediv">
		            		<label>Discharge Date<span class="text-danger">*</span></label>
							<s:textfield id ="editdischarge_date" cssClass="form-control showToolTip" 
							data-toggle="tooltip" title="Enter Discharge Date" theme="simple"  placeholder = "Enter Discharge Date"></s:textfield>
		            		<label id = "editdischarge_dateError" class="text-danger"></label>
		            	</div>
		         	</div>
		         	<div class="col-lg-3 col-md-3 col-xs-3 col-sm-3">
		         		<div class="form-group" id="editwarddiv">
		            		<label>Select Ward</label>						
							<s:select id="editwardid" onchange="selectBedFromWardid(this.value)" list="wardlist" listKey="id" listValue="wardname" headerKey="0" headerValue="Select Ward" theme="simple" cssClass="form-control showToolTip chosen"/>
		            	</div>
		         	</div>
		         	<div class="col-lg-3 col-md-3 col-xs-3 col-sm-3">
		         		<div class="form-group" id="editbeddiv">
		            		<label>Select Bed</label>						
							<SELECT id="editbedid" class="form-control showToolTip chosen">
		            		<option value="0">Select Ward First</option>
		            		</SELECT>
		            	</div>
		         	</div>
      			</div>
     		</div>
      		<div class="row">
      			<div class="col-lg-12 col-md-12 col-xs-12">
      				<div class="col-lg-4 col-md-4 col-xs-4 col-sm-4">
		         		<div class="form-group" id="editpractinordiv">
		            		<label>Practinoner Name<span class="text-danger">*</span></label>						
							<s:select id="editpractitioner_name"  list="userList" onchange="getspeciality(this.value)" headerKey="0" headerValue="Select" listValue="diaryUser" listKey="id" theme="simple" cssClass="form-control showToolTip chosen"/>
		            		<label id = "editpractitioner_nameError" class="text-danger"></label>
		            	</div>
		         	</div>
		         	<div class="col-lg-4 col-md-4 col-xs-4 col-sm-4">
		         		<div class="form-group" id="editspecialitydiv">
		            		<label>Speciality</label>						
							
							<SELECT id="editspeciality" class="form-control showToolTip chosen">
		            		<option value="0">Select Doctor First</option>
		            		</SELECT>
		            	</div>
		         	</div>
		         	<div class="col-lg-4 col-md-4 col-xs-4 col-sm-4">
		         	</div>
      			</div>
      		</div>
     		<div class="row">
      			<div class="col-lg-12 col-md-12 col-xs-12">
      				<div class="col-lg-3 col-md-3 col-xs-3 col-sm-3">
		         		<div class="form-group" id="editplacediv">
		            		<label>Place</label>
							<s:textfield id ="editnew_place" cssClass="form-control showToolTip" 
							data-toggle="tooltip" title="Enter Place" theme="simple"  placeholder = "Enter PLace"></s:textfield>
		            	</div>
		         	</div>
      				<div class="col-lg-3 col-md-3 col-xs-3 col-sm-3">
		         		<div class="form-group" id="editshelfdiv">
		            		<label>Shelf No / Rack</label>
		            		<s:textfield id ="editnew_shelf" cssClass="form-control showToolTip" 
							data-toggle="tooltip" title="Enter Shelf No" theme="simple"  placeholder = "Enter Shelf No"></s:textfield>
		            	</div>
		        	</div>
		         	
		         	<div class="col-lg-3 col-md-3 col-xs-3 col-sm-3">
		         		<div class="form-group" id="editremarkdiv">
		            		<label>Remark</label>
							<s:textfield id ="edit_new_remark" cssClass="form-control showToolTip" 
							data-toggle="tooltip" title="Enter Remark" theme="simple"  placeholder = "Enter Remark"></s:textfield>
		            	</div>
		         	</div>
		         	<div class="col-lg-3 col-md-3 col-xs-3 col-sm-3">
		         		<div class="form-group" id="editmlcdiv">
		            		<label>MLC</label><br>
		            		<input type="checkbox" name="editnew_mlc" id="editnew_mlc" >
		            	</div>
		         	</div>
      			</div>
      		</div>
      		<div class="row">
      			<div class="col-lg-12 col-md-12 col-xs-12">
      				<div class="col-lg-3 col-md-3 col-xs-3 col-sm-3" id="editmlcnodiv">
		         			<label>MLC No.</label>
							<s:textfield id ="editnew_mlcno" name="new_mlcno" cssClass="form-control" 
							data-toggle="tooltip" title="Enter MLC NO" theme="simple"  placeholder = "Enter MLC NO"></s:textfield>
		         	</div>
      				<div class="col-lg-3 col-md-3 col-xs-3 col-sm-3">
		         		<div class="form-group hidden" id="editempanelmentdiv">
		            		<label>Empanelment <span class="text-danger">*</span></label>						
							<s:select id = "editnew_whopay" list="#{'Client':'Self','Third Party':'Third Party'}"  
							headerKey="0" onchange="showMrdHide()" cssClass="form-control showToolTip chosen"
							data-toggle="tooltip" theme="simple"></s:select>
							<label id = "new_whopayError" class="text-danger"></label>
		            	</div>
		         	</div>
		         	
		         	<div class="col-lg-3 col-md-3 col-xs-3 col-sm-3">
		         	</div>
		         	<div class="col-lg-3 col-md-3 col-xs-3 col-sm-3">
		         	</div>
      			</div>
      		</div>
      		<div id="new_hidden_html" style="display:none;">					
 				<div class="row col-lg-12 col-md-12 col-xs-12" style="margin-top: -20px;">
					<div class="col-lg-3 col-md-3" id="edittpediv">
						<label>Type<span class="text-danger">*</span></label>
						<s:select id="editnew_type" list="thirdPartyTypeList" listKey="id" listValue="type" headerKey="0" cssClass="form-control showToolTip chosen"
						data-toggle="tooltip" title="Select Third Party Type"  headerValue="Select Type" onchange="setMrdTPName(this.value)" theme="simple"/>
						<label  id = "new_typeError" class="text-danger"></label>
					</div>
				<div class="col-lg-3 col-md-3" id="edittypeNamediv">
					<label>Third Party Name <span class="text-danger">*</span></label>		
					<select id = "editnew_typeName" class="form-control showToolTip chosen" data-toggle="tooltip" 
					theme="simple" >									
						<option value="0">Select Third Party</option>
					</select>
					<label  id ="editnew_typeNameError" class="text-danger"></label>	
				</div>
				<div class="col-lg-3 col-md-3 hidden">
				<br>
					<input type="button" onclick="setTypeFieldofTp()" class="btn btn-primary" value="Add Third Party">
				</div>
				<div class="col-lg-3 col-md-3">
				</div>
			</div>  
		
			<div class="row col-lg-12 col-md-12 col-xs-12">
				<div class="col-lg-3 col-md-3" id="editnew_policyNo">
					<label>Policy No.</label>			
					<s:textfield  id = "editnew_policyNo" cssClass="form-control showToolTip" disabled="true"
					data-toggle="tooltip" placeholder = "Enter Policy No." title="Enter Policy No." theme="simple"/>
				</div>
				<div class="col-lg-3 col-md-3" id="editnew_expiryDatediv">
					<label>Policy Expiry Date</label>
					<s:textfield id ="editnew_expiryDate" cssClass="form-control showToolTip" disabled="true"
					data-toggle="tooltip" placeholder = "Enter Expiry Date." theme="simple" readonly = "true"/>
				</div>
				<div class="col-lg-3 col-md-3" id="editnew_policyExcessdiv">
					<label>Policy Excess</label>
					<s:textfield  id="editnew_policyExcess" disabled="true"
						cssClass="form-control showToolTip" data-toggle="tooltip"
						placeholder = "Enter Value"/>								
				</div>
				<div class="col-lg-3 col-md-3">
				</div>
			</div>						
	  	</div>		
    </div>
  </div>
      	<div class="modal-footer" id="btnupdate">
      	<input type="button" value="Update" class="btn btn-primary" id="updatemrd" onclick="updateInformation()"/>
      </div>
    </div>
  </div>
</div>
	
	<script type="text/javascript" src="_assets/newtheme/js/vendor/slimscroll/jquery.slimscroll.min.js"></script>
	<script>
		$(function() {
 $('#registerpop').slimScroll({
		        height: '492px',
		        railVisible: true,
				alwaysVisible: true
		    });

 });
	</script>
	
					  <script>
   $(document).ready(function () {
	    $(".disblebtnone").on("click", function() {
	        $(this).attr("disabled", "disabled");
	        doWork();
	    });
	});
   </script>
					
 </html>


>>>>>>> refs/remotes/origin/master
 --%>	