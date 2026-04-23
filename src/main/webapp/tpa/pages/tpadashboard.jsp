<%@page import="com.apm.common.web.common.helper.LoginHelper"%>
<%@page import="com.apm.common.web.common.helper.LoginInfo"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags" %>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<!-- New theme 30 01 2018 -->
<link href="_assets/newtheme/css/mbile.css" rel="stylesheet" type="text/css" />
<script src="_assets/newtheme/js/ui.js" type="text/javascript"></script>
<%
LoginInfo loginInfo=LoginHelper.getLoginInfo(request);
%>
<!-- New theme 30 01 2018 -->
<style>
.table>thead>tr>th, .table>tbody>tr>th, .table>tfoot>tr>th, .table>thead>tr>td, .table>tbody>tr>td, .table>tfoot>tr>td {
    border-bottom: 1px solid rgba(243, 241, 241, 0.63) !important;
}
.martop10{
margin-top:10px;
}.invest7{
    width: 8%;
}
.invest5{
    width: 5%;
}
.invest8{
width: 8%;
}
.invest4{
width: 3%;
}
.invest3{
width: 3%;
}
.invest21{
 width: 18%;

}
.invest24{
    width: 7% !important;
}
.invest10{
    width: 10%;
}
.invest9{
width: 15%;
}
.mainheader {
    background-color: #339966 !important;
}
.attached{
    text-align: left;
    font-size: 12px;
    font-weight: bold;
    color: #0514DE;
}
.indopborder {
	border: 2px dotted #A5A5C7 !important;
    width: 420px !important;
    color: #9C9C9C !important;
    text-align: left;
    vertical-align: middle !important;
    padding: 5px 5px 5px 5px !important;
}

.tooltip {
    position: relative;
    display: inline-block;
    opacity: 1;
    z-index: 0;
}

.tooltip .tooltiptext {
    visibility: hidden;
    width: 180px;
    text-align: left;
    padding: 0px 0;
    background-color:#555;
    color:#fff;
    position: absolute;
    z-index: 1;
    bottom: 125%;
    left: 50%;
    margin-left: -60px;
    opacity: 0;
    transition: opacity 1s;
}
.imflag{
 display: inline-block;
  width: 13px;
}


.tooltip .tooltiptext::after {
    content: "";
    position: absolute;
    top: 100%;
    left: 10%;
    margin-left: -5px;
    border-width: 5px;
    border-style: solid;
    border-color: #555 transparent transparent transparent;
}

.tooltip:hover .tooltiptext {
    visibility: visible;
    opacity: 1 !important;
}
.newstate{
	background-color: #ff6767;
    color: #fff;
    padding: 1px 3px 2px 3px;
    text-align: center;
    text-transform: uppercase;
    border-radius: 6px;
        width: 100%;
}



.collectstate{
	background-color: #36b9a5;
	text-transform: uppercase;
    color: #fff;
    padding: 1px 3px 2px 3px;
    text-align: center;
    border-radius: 6px;
        width: 100%;
}


.completedstate{
	background-color: #af9f22;
    color: #fff;
    padding: 1px 3px 2px 3px;
    text-transform: uppercase;
    text-align: center;
    border-radius: 6px;
        width: 100%;
}

.approvedstate{
	background-color: #60b761;
    color: #fff;
    padding: 1px 3px 2px 3px;
    text-align: center;
    text-transform: uppercase;
    border-radius: 6px;
        width: 100%;
}

.fontset{
    font-size: 9px;
}

.blink_me {
  animation: blinker 1s linear infinite;
  color:#0066ff;
}

@keyframes blinker {  
  50% { opacity: 0; }
}
.fa-2x {
    font-size: 13px;
}
</style>

<script type="text/javascript" src="diarymanagement/js/sendApmtAttachnment.js"></script>
<script type="text/javascript" src="tpa/js/tpadashboard.js"></script>
<script type="text/javascript" src="thirdParties/js/nicEdit.js"></script>
<script type="text/javascript" src="treatmentEpisode/js/treatmentEpisodeList.js""></script>



<script type="text/javascript">

  var globalapptid=0;
  var editAppointId=0;

$(document).ready(function(){
	   
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
	
});



bkLib.onDomLoaded(function() {
    
	 //new nicEditor().panelInstance('declarationNotes');
	 //new nicEditor({maxHeight : 250}).panelInstance('admissiondetails');
	 new nicEditor({buttonList : ['fontSize','bold','italic','underline','strikeThrough','subscript','superscript','html','image'],maxHeight : 70}).panelInstance('emailBodyLetter');

	
	 $('.nicEdit-panelContain').parent().width('auto');
	 $('.nicEdit-panelContain').parent().next().width('auto');
	 
	 $('.nicEdit-main').width('100%');
	// $('.nicEdit-main').height('480px');
});


</script>


</head>
<body>
<div id="preloader" style="display: none;">
  <div id="status" style="display: none;">&nbsp;</div>
</div>
<div class="row details mainheader">
								<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12 manascommheader">
								<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12" style="display: -webkit-inline-box;padding: 0px;">
									<img src="manasmaindashboard/images/TPA_Icon.svg" style="filter: brightness(5);margin-left: 7px;">
									<!-- <img src="dashboardicon/tpa.png" class="img-responsive prescripiconcircle"> --><h4>TPA Dashboard </h4>
								</div>
								
								</div>
							</div>
							
							<div class="col-lg-12 col-md-12 paddingnil">
										<div class="col-lg-12 col-md-12 topback2">
										<form id="searchfrm" name="Tpa" action="Tpa" method="post" class="form-inline search">
										
										
										
										 
            
<!--   Mobile Menu       --> 

<p>
  <a onclick="showHideDiv('divMsg')" class="btn btn-primary displaynoneweb mismenubarmobile collapsed" data-toggle="collapse" href="#collapseExample" role="button" aria-expanded="false" aria-controls="collapseExample">
<span class="pull-left">Filter   </span>  <span class="pull-right"><i class="fa fa-bars"></i></span>
  </a>
 
</p>
<div class="collapse" id="collapseExample" style="height: 0px;">
  <div class="card card-body">
   



  <div class="card card-body displaynoneweb">
  
 
 <script type="text/javascript">
			function showHideDiv(ele) {
				var srcElement = document.getElementById(ele);
				if (srcElement != null) {
					if (srcElement.style.display == "block") {
						srcElement.style.display = 'none';
					}
					else {
						srcElement.style.display = 'block';
					}
					return false;
				}
			}
		</script>
 
   
            
 
</div>         
 
  </div>
</div>
 
 
 
<!--  web Menu -->
  <div id="divMsg"  class="card card-body displaynonemobile">
  
 
            
    
            
 
					
										
										
										
										
											<div class="form-group tpasearchuhid">
										    	<s:textfield  name="searchText" id="searchText" cssClass="form-control" cssStyle="width:100%;" placeholder="Search UHID / patient name / IP ID" />
										  	</div>
											
											
											<div class="form-group tpadatei">
												<s:textfield name="fromdate" id="fromdate" cssClass="form-control" cssStyle="width:100%;" placeholder="From Date" />
											</div>
											<div class="form-group tpadateib">
												<s:textfield name="todate"  id="todate" cssClass="form-control" cssStyle="width:100%;" placeholder="To Date" />
											</div>
											
											
										 
											
											
											
											<div class="form-group">
												<select name="filter_status" id="searchfrm_filter_status" class="form-control">
												    <option value="0" selected="selected">Select All</option>
												    <option value="1">New</option>
												    <option value="2">Submitted</option>
												    <option value="3">Rejected</option>
												    <option value="4">Approved</option>
												</select>
											</div>
											
											<div class="form-group">
												<button type="submit" class="btn btn-primary">Go</button>
										  	<!-- 	<a href="#" onclick="refreshhere()" class="btn btn-primary" title="Refresh"><i class="fa fa-refresh"></i></a> -->
										  		 <a href="#" onclick="goreferesh()" class="btn btn-primary" title="Refresh"><i class="fa fa-refresh"></i></a>
											</div>
											<div class="form-group hidden">
												<input type="text" name="client" value="" id="client" class="form-control" onclick="showPopUp()" style="width: 100%;" placeholder="Select Patient">
											</div>
										
											<div class="form-group tpacolorleft" >
												<div class="form-inline" style="float: right;margin-top: 5px;">
												
											  <div class="checkbox checkboxmtpa">
											    <label>
											      <i class="fa fa-square" aria-hidden="true" style="color:#ffa3a3"></i> New
											    </label>
											  </div>&nbsp;|
											  <div class="checkbox checkboxmtpa">
											    <label>
											      <i class="fa fa-square" aria-hidden="true" style="color:rgb(72, 204, 184);"></i> Submitted
											    </label>
											  </div>&nbsp;|
											  <div class="checkbox checkboxmtpa">
											    <label>
											      <i class="fa fa-square" aria-hidden="true" style="color:rgba(229, 217, 129, 0.46)"></i> Rejected
											    </label>
											  </div>&nbsp;|
											  <div class="checkbox checkboxmtpa">
											    <label>
											      <i class="fa fa-square" aria-hidden="true" style="color:#7fcc80"></i> Approved
											    </label>
											  </div>&nbsp;|
											  <div class="checkbox checkboxmtpa">
											    <label>
											      <i class="fa fa-square" aria-hidden="true" style="color:#7fcc80"></i> Re-Approved
											    </label>
											  </div>
											</div>
											</div>
											
										 <!-- <input type="button" class="btn btn-primary" data-toggle="modal" data-target="#addPatientDiv" value="Add New Patient"> -->
										   <!--  <button type="submit" class="btn btn-primary" data-toggle="modal" data-target="#investigationpopup">Create New Investigation</button> -->
										  
										</form>

										</div>
										</div>
										
										
										</div>
            
 
					
										
										<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12" style="padding: 0px;">
											<div class="table-responsive">
											<table class="table table-bordered table-striped table-responsive table-hover" style="text-transform: uppercase;">
											<thead>
												<tr class="headings">
													<th style="width: 9%;">Admission Date</th>
													<th style="width: 23%;">UHID | IP ID | Ref.ID | Ref.Name</th>
													<!-- <th style="width: 10%;">Requested <br>Collected</th>
													<th style="width: 10%;">Completed <br>Approved</th> -->
													<th style="width: 27%;">Patient Details</th>
													<th style="width: 28%;">Payee Details</th>
		                                          	<th style="text-align: center;width: 5%;">Status</th>
		                                          	<th style="width: 9%;">Ref.End Date</th>
		                                          	<th style="width:6%;" class="text-right">Limit</th>
		                                          	<!-- <th></th> -->
		                                          	<th></th>
		                                          	<th></th>
		                                          	<th></th>
												</tr>
											</thead>
											<tbody>
											      <s:iterator value="tpnameList">
													<tr style="cursor: pointer; background-color: " id="2541" class="even pointer" >
														<td style="color: green;"> <s:property value="commencing" /></td>
														<td class=" "><s:property value="abrivationid" /> | <s:property value="ipdopd" /> |
														<%if(loginInfo.getIpd_abbr_access()==1){ %>
															<s:property value="newipdabbr" />
														<%}else{ %> 
															<s:property value="id" />
														<%} %>
														 | <s:property value="name"/></td>
														<s:if test="ipdid>0">
															<td class=" "><img src="emr/img/mediflag_ipd_small.png" class="img-responsive imflag"> <s:property value="fullname"/> | <s:property value="agegender" /> | <s:property value="bedname" />  </td>
																
														</s:if>
														<s:else>
														
															  <td class=" "><img src="emr/img/medicineflag_opd_small.png" class="img-responsive imflag"></img> <s:property value="fullname"/> | <s:property value="agegender" /> </td>
																 
														</s:else>
														
														<td><s:property value="companyName" /></td>
                                                     	<td class="">
                                                     	   <s:if test="approved==1">
                                                     	   	<button class="fontset" style="background-color: #7fcc80" onclick="">Approved</button>
                                                     	   </s:if>
                                                     	   <s:elseif test="rstatus==1">
                                                     	   	<button style="background-color: rgba(229, 217, 129, 0.46)" class="fontset newstate"  onclick="openApprovedOrRejectPop(<s:property value="id"/>,<s:property value="clientid"/>)">Rejected</button>
                                                     	   </s:elseif>
                                                     	   <s:elseif test="submitted==1">
                                                     	   	<button style="background-color: rgb(72, 204, 184)" class="fontset newstate"  onclick="openApprovedOrRejectPop(<s:property value="id"/>,<s:property value="clientid"/>)">Submitted</button>
                                                     	   </s:elseif>
                                                     	   <s:else>
                                                     	   	 	
                                                     	   	 	<button class="fontset newstate" onclick="showtpEdit(<s:property value="id"/>,<s:property value="clientid"/>)" >New</button> 
                                                     	   	 	
                                                     	   </s:else>
												        	
												       	</td>
												       	<td><s:property value="refenddate"/></td>
														<td class="text-right"> <s:property value="spendlimit"/></td>
												        <td>
												        	<span class="" ><a href="#" onclick="openMailPopup(<s:property value="clientid" />,<s:property value="id"/>)" ><i class="fa fa-envelope-o fa-2x" style="color: #555;"></i></a></span>
												        </td>
												         <td>
															<span><i class="fa fa-cloud-upload fa-2x" style="color: #555;" aria-hidden="true"></i></span>
												        </td>
												        <td style="text-align: center;">
																	<a href="#" onclick="deletetpa(<s:property value="id"/>)"><i class="fa fa-times fa-2x text-danger" aria-hidden="true"></i></a>
																	<%-- <td class="text-center"><a href="#" onclick="deleteMrd(<s:property value="id"/>)" class="text-danger"> --%>
												        </td> 
													</tr>
												  </s:iterator>	
											</tbody>
										</table>
										</div>
										</div>
										<s:form action="Tpa" name="paginationForm" id="paginationForm" theme="simple">
<div class="col-lg-12 col-md-12" style="padding:0px;">
		<div class="col-lg-4 col-md-4 text-left" style="padding:0px;">
			Total:<label class="text-info"><s:property value="totalRecords" /></label>
		</div>
		<%@ include file="/common/pages/pagination.jsp"%>
	</div>
</s:form>

<!-- Modal -->
<div id="deletetpa" class="modal fade" role="dialog">
  <div class="modal-dialog modal-sm">

    <!-- Modal content-->
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal">&times;</button>
        <h4 class="modal-title">Are You Sure To Cancel?</h4>
      </div>
      <div class="modal-body">
      		<input type="hidden" id="canceltpaid">
        	<textarea rows="3"  class="form-control" id="delete_reason" placeholder="Cancel Reason" style="background-color: beige;"></textarea>
      </div>
      <div class="modal-footer">
        <input type="button" class="btn btn-danger" onclick="deletetpaDetails()" data-dismiss="modal" value="Ok">
      </div>
    </div>

  </div>
</div>


<!-- Send Letter Details Modal -->
	<div class="modal fade popoverpop " id="sendLetterPopup" tabindex="-1"
		role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
	   <s:form id="upload" method="post" action="sendLetterEmailTpInstantMsg"
					enctype="multipart/form-data" theme="simple">

       <s:hidden name="treatmentEpisodeId" id="treatmentEpisodeId"></s:hidden>
       <s:hidden name="editAppointId" value="0"></s:hidden>	
		
		<div class="modal-dialog modal-md">
			<div class="modal-content">
			
     
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">
						<span aria-hidden="true">&times;</span><span class="sr-only">Close</span>
					</button>
					<h4 class="modal-title" id="myModalLabel">Send Email/Letter</h4>
				</div>
				<div class="modal-body">
				<div class="emailbodas">
					<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12" style="padding:0px;">
					<div class="col-lg-4 col-md-4 col-xs-12">
						<div class="form-group">
					    <label for="exampleInputEmail1">Patient</label>
					    <s:textfield name="userName" id="userNameletter" readonly="true" cssClass="form-control"  value="Client"  />
						<label  id = "unameError" class="text-danger"></label>	
					  </div>
					</div>
					<div class="col-lg-4 col-md-4 col-xs-12">
						<div class="form-group">
					    <label for="exampleInputEmail1">Select Template</label>
					     <s:select theme="simple" listKey="id" listValue="templateName" list="templateNameList" headerKey="0" headerValue="Select Template" id = "templateName" name ="templateName" onchange="showTemplateDetails(this.id)" cssClass="form-control showToolTip chosen">
					     </s:select>
					  </div>
					</div>
					<s:hidden name="clientid" id="id"></s:hidden>
					<s:hidden name="user" id="userletter"></s:hidden>
					<div class="col-lg-4 col-md-4 col-xs-12">
						<div class="form-group">
					    <label for="exampleInputEmail1">Email</label>
					   <s:textfield name="to" id="gpLetterEmail" cssClass="form-control showToolTip" title="Enter EmailId"
								data-toggle="tooltip" placeholder="Enter EmailId"/>
						<label  id = "emailError" class="text-danger"></label>	
					  </div>
					</div>
				</div>
					<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12" style="padding:0px;">
				<div class="col-lg-12 col-md-12 col-xs-12">
						<div class="form-group">
					    <label for="exampleInputEmail1">Cc</label>
					    <s:textfield theme="simple" id = "gpLetterccEmail" name = "cc"	cssClass="form-control showToolTip" title="Enter Cc"
								data-toggle="tooltip" placeholder="Enter Cc" />
					  </div>
					</div>
					<div class="col-lg-12 col-md-12 col-xs-12">
						<div class="form-group">
					    <label for="exampleInputEmail1">Subject</label>
					     <s:textfield name= "subject" id = "gpLettersubject" cssClass="form-control showToolTip"
								data-toggle="tooltip" title="Enter Subject" placeholder="Enter Subject" />
					  </div>
					</div>
					
					
				</div>
					
					<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12">
					<div class="form-group">
						<label for="exampleInputEmail1">Attachments</label>
						<input type="hidden" id="fileUpload" name = "fileUploadd" />
		 				 <div id = "drop">
		      			  <!--FileUpload Controls will be added here -->
		      			  <div id="upload"></div>
		      			  <div id = "draftAttachments"></div>
		    			</div>
		    			<input id="Button1" class="btn btn-default"  type="button" value="Attach Files" onclick = "AddFileUpload()" />
					</div>
				</div>
				<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12">
					<div class="form-group">
					    <label for="exampleInputEmail1">Body</label>
				    <s:textarea cssStyle="height:2500px;" cssClass="form-control showToolTip textarea"  data-toggle="tooltip" title="Write content" placeholder="Write content" name="body" id="emailBodyLetter" />
				  </div>
				</div>
					
				</div>
      </div>
						
			<div class="modal-footer">
			<div class="row">
			<div class="col-lg-2 col-md-2 col-xs-2 col-sm-2"></div>
			<div class="col-lg-2 col-md-2 col-xs-2 col-sm-2">
			<div id="pdfFileIdLetter">
			</div></div>
			<div class="col-lg-2 col-md-2 col-xs-2 col-sm-2">
			<div id="sendmail">
			</div></div>
			<div class="col-lg-2 col-md-2 col-xs-2 col-sm-2">
			<div id="printId">
			</div></div>
		<div class="col-lg-4 col-md-4 col-xs-4 col-sm-4">
		<!-- <button type="button" id="saveId" class="btn btn-primary" onclick="return createPdf()">Save as pdf</button> -->
		<button type="submit" id="saveId" class="btn btn-primary" >Send Mail</button>
		<button type="button" class="btn btn-primary"  onclick="return createPdf()">Print</button>
		<button type="button" class="btn btn-primary hidden" data-dismiss="modal">Close</button>
      </div></div></div>
     
    </div>
  </div>
  </s:form>
</div> 





<!-- Reject or Accept -->
<div class="modal fade" id="rejaccept" tabindex="-1" role="dialog"
	aria-labelledby="lblsemdsmspopup" aria-hidden="false" data-keyboard="false" data-backdrop="static">
		<div class="modal-dialog modal-sm">
			<div class="modal-content">
				<div class="modal-header bg-primary">
					<button  type="button" class="close" data-dismiss="modal">
						<span aria-hidden="true">&times;</span><span class="sr-only">Close</span>
					</button>
					<h4 class="modal-title" id="">Third party approval</h4>
				</div>
				<s:form theme="simple" action="rejectTpa" id="tpaform">
				
				   <s:hidden name="treatmentepisodeid" id="tepid"></s:hidden>
				<div class="modal-body">
				<div class="row">
				<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12">
				 <p id="piddvadv">
				 
				 </p>
				 <br>
				 <div class="col-lg-6 col-md-6">
					   <input type="button" value="Accept" onclick="cnfirmPopup()" class="btn btn-primary" />
					    <input type="button" value="Reject" onclick="rejectCnfPopup()" class="btn btn-primary" />
				 </div>
				</div>
				</div>
					
				</div>
				</s:form>
				<div class="modal-footer">
					<button  type="button" class="btn btn-primary hidden" data-dismiss="modal">Close</button>
				</div>
			</div>
		</div>
	</div>



<!-- for New Model Treatment Episode -->
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
					<%@include file="/treatmentEpisode/pages/addTreatmentEpisode.jsp" %>
				</div>
				<div class="modal-footer">

					<button type="button" id="btnsave" class="btn btn-primary disblebtnone"
						onclick="editSaveTreatment();">Save</button>
					<button type="button" id="btnsubmit"  class="btn btn-primary disblebtnone hidden"
						onclick="submitandsavetp();">Submit</button>	
						
					<button type="button" id="btnapproved" class="btn btn-primary disblebtnone hidden"
						onclick="cnfirmPopup();">Approved</button>	
						 
					<button type="button" id="btnreject" class="btn btn-primary disblebtnone hidden"
						onclick="rejectCnfPopup();">Reject</button>			
					<button type="button" class="btn btn-primary" data-dismiss="modal">Close</button>
				</div>
			</div>
		</div>
	</div>






	
    


</body>



</html>