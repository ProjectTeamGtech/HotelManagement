<%@page import="com.apm.common.web.common.helper.LoginHelper"%>
<%@page import="com.apm.common.web.common.helper.LoginInfo"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>

<%LoginInfo loginInfo = LoginHelper.getLoginInfo(request); %>

<link href="common/assets/css/style.css" rel="stylesheet" /> 
 <script type="text/javascript" src="diarymanagement/js/sendApmtAttachnment.js"></script>
<script type="text/javascript" src="pacs/js/pacs.js"></script>
<script type="text/javascript" src="thirdParties/js/nicEdit.js"></script>
<style>
.dropdown-menu {
    left: 484px !important;
    top: 42px !important;
}
.dropdown-menu>li>a {
    display: block;
    padding: 1px 10px;
}
.spandrop{
	margin-top: 4px;
    position: absolute;
}
.modal-draggable .modal-backdrop {
             position: fixed;
         }

         .modal.modal-draggable {
             overflow: overflow-y;
         }

         .modal-draggable .modal-header:hover {
             cursor: move;
         }
#drop a {
    background-color: transparent !important;
    padding: 0px 0px !important;
    color: #fff;
    font-size: 12px;
    border-radius: 2px;
    cursor: pointer;
    display: inline-block;
    margin-top: 12px;
    line-height: 1;
    float:right;
}
.padnil{
    padding: 0px !important;
    padding-bottom: 10px !important;
}
#drop a:hover {
    background-color: transparent;
    padding: 0px 0px !important;
}
#drop input {
    display: block !important;
}
#upload {
    background-color: #fff !important;
    padding: 0px;
    border-radius: 0px;
}
.foemse{
	border-left: none !important;
    border-right: none !important;
    border-top: none !important;
}


.setem{
	margin-left: -70px;
}
.micimg{
	float: left;
    width: 27%;
}

</style>

<script>

    function printExcel() {

       $(".tablexls").table2excel({
					exclude: ".noExl",
					name: "Charges Report List",
					filename: "chargesReport",
					fileext: ".xls",
					exclude_img: true,
					exclude_links: true,
					exclude_inputs: true
				});
}
 
 
 
	$(document).ready(function() {

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
<script>
 bkLib.onDomLoaded(function() {
		           
	        	 //new nicEditor().panelInstance('declarationNotes');
	        	 new nicEditor({maxHeight : 250}).panelInstance('otnotes');
	        	 $('.nicEdit-panelContain').parent().width('98%');
	        	 $('.nicEdit-panelContain').parent().next().width('98%');
	        	 
	        	 $('.nicEdit-main').width('100%');
	        	// $('.nicEdit-main').height('480px');
	      });

</script>

</head>
	<body>
							<div class="row details mainheader">
								<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12 manascommheader">
									<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12" style="display: -webkit-inline-box;padding: 0px;">
									 	<img src="manasmaindashboard/images/PAC's_Icon.svg" style="filter: brightness(5);margin-left: 7px;">
									 	<h4>PACs Dashboard  | <span style="font-size:11px;"> <s:if test="clientid!=0"><s:property value="clientname"/></s:if></span></h4>
									</div>
									
									<%-- <div class="col-lg-1 col-md-1 col-sm-1 col-xs-1 oneseticonleft">
										<img src="dashboardicon/pacs.png" class="img-responsive prescripiconcircle">
									</div>
									<div class="col-lg-11 col-md-11 col-sm-11 col-xs-11 titlestleftiocn">
										<h4>PACs Dashboard  | <span style="font-size:11px;"> <s:if test="clientid!=0"><s:property value="clientname"/></s:if></span></h4> 
									</div> --%>
								</div>
								
								
								
							</div>
							<s:form action="Pacs?clientid=0" id="showpacsfrms" theme="simple">
							<s:hidden name="selectedmodality" id="selectedmodality"/>
							<div class="row ">
								<div class="col-lg-12 col-md-12">
										<div class="col-lg-12 col-md-12 topback2">
										<div class="form-inline search">
										<div class="form-group">
										    <s:textfield  name="searchText"  value="" id="searchText" cssClass="form-control" placeholder="Patient ID/Name"/>
										  </div>
										  <div class="form-group">
										    <s:textfield  name="searchInvstid"   id="searchInvstid" cssClass="form-control" placeholder="Search Investigation"/>
										  </div>
										    <div class="form-group" style="width: 7%;">
										  <s:textfield readonly="true" name="fromDate" id="fromDate"
											cssClass="form-control" theme="simple" cssStyle="width:100%;"></s:textfield>
											</div>
											<div class="form-group" style="width: 7%;">
										 <s:textfield readonly="true" name="toDate" id="toDate"
											cssClass="form-control" theme="simple" cssStyle="width:100%;"></s:textfield>
										 </div>
										     <div class="form-group button-group">
										        <button type="button" class="btn btn-default btn-sm dropdown-toggle" data-toggle="dropdown">Modality Filter <span class="caret"></span></button>
												<ul class="dropdown-menu">
													<s:iterator value="modalityList">
												  <li><a href="#" class="small" data-value="option1" tabIndex="-1"><input type="checkbox" class="pacss" value="<s:property value="id"/>"/>&nbsp;<span class="spandrop"><s:property value="name"/></span></a></li>
												  </s:iterator>
												 
												</ul>
										  	</div>
											
											 
										 
										  
										  <input type="button" class="btn btn-primary" value="Go" onclick="showPacsData()">
										  <a href="#" onclick="refreshData()" class="btn btn-primary" title="Refresh"><i class="fa fa-refresh"></i></a>
										   <a href="folderPacs"  class="btn btn-primary" title="Refresh">Folder View</a>
										  <a href="#" data-toggle="modal" data-target="#uploadfile" class="btn btn-primary pull-right" title="Browse File">Browse File</a>
										  <a href="#" data-toggle="modal" data-target="#addfinding" class="btn btn-primary pull-right hidden" title="Add Findings" style="margin-right: 5px;">Add Findings</a>
										</div>
										</div>
										
										</s:form>
										<div class="row">
												<div class="col-lg-12 col-md-12 col-xs-12">
											<table class="table table-bordered" width="100%">
											<thead>
												<tr class="tableback">
													<th class="pdate manastableheader">Sr.No</th>
													<th class="pnew manastableheader">File</th>
													<!-- <th class="pnamewidth manastableheader">Modality</th>
													<th class="page manastableheader">Invest ID</th>
													<th class="pdate manastableheader">Patient Name</th>
													<th class="pnew manastableheader">Study Date</th> -->
													<th class="pnew manastableheader">Recieved On</th>
													<th class="pnew manastableheader">Patient Name</th>
													<th class="pnew manastableheader">UHID</th>
													<th class="pnew manastableheader">Update UHID</th>
											        <th class="pnew manastableheader">Update Report</th>
											        <th class="pnew manastableheader">Print Report</th>	
											        <th class="pnew manastableheader">Add Inv</th>		
													<!-- <th class="pview manastableheader" >Status</th>
													<th class="pview manastableheader">View</th> -->
												</tr>
											</thead>
											<tbody>
											<%int cnt = 1; %>
											<s:iterator value="pacsdataList">
												<tr id="<s:property value="id"/>">
													<td><%=cnt %></td>
													
													<td>
														<%--  <a href="#" id="myAnchor<s:property value="id"/>" onclick="openPacsPopup('http://<%=loginInfo.getPacsip() %>:8080/webpacs/viewPacs?mpid=<s:property value="multipacsid"/>')" >
														<a href="#" id="myAnchor<s:property value="id"/>" src="liveData/webpacs/pacsdata/37/P3.img" alt="Another alt text">" >
														<s:property value="filename"/>
														</a> --%>
														
														<a href="#" onclick="openPacsPopup('http://<%=loginInfo.getPacsip() %>:8080/webpacs/pacsdata/<s:property value = "multipacsid" />/<s:property value="filename" />')" id="myAnchor3"><s:property value="filename" /> </a>
													</td>
													
													<%-- <td><s:property value="modality"/></td>
													<td><s:property value="pid"/></td>
													<td><s:property value="pname"/></td> --%>
													<%-- <td><s:property value="studydate"/></td> --%>
													<td><s:property value="recievedon"/></td>
													<td><s:property value="fullname" /></td>
													<td><s:property value="abrivationid" /></td>
													<td><a href="#" onclick="updatepatientuhid(<s:property value="dicid"/>,'<s:property value="id"/>')"><i class="fa fa-edit"></i></a></td>													
													<%-- <td> 
														<s:if test="fstatus==1">
															Updated
														</s:if>
														<s:else>
															Not Updated
														</s:else>
													</td> --%>
													<%-- <td>
														<a href="#" onclick="openEmrPopup('pacsreportEmr?imgid=<s:property value="pid"/>&action=p')" class="font11" title="View Report" >
															<i class="fa fa-object-ungroup" aria-hidden="true"></i>
														</a>
													</td> --%>
													<%String userid=loginInfo.getUserId(); %>
													<td><a href="#" onclick="openPacsPopup('http://<%=loginInfo.getPacsip() %>:8080/webpacs/reportPacs?pacsid=<s:property value="dicid"/>&userid=<%=userid%>&uhid=<s:property value="abrivationid"/>','<s:property value="dicid"/>')"><i class="fa fa-edit"></i></a></td>
												   <td><a href="#" onclick="openPopup('printpagePacs?pacsid=<s:property value="dicid"/>')"><i class="fa fa-print"></i></a></td>
												   <td><a href="#" onclick="openPopup('finding1Pacs?multipacsid=<s:property value="multipacsid"/>')">Add Investigation</a></td>
												   
												</tr>
												<%cnt++; %>
												</s:iterator>
											</tbody>
										</table>
										</div>
										</div>
										</div>
									</div>
									
									<!-- Modal -->
									<div id="uploadfile" class="modal fade" role="dialog">
									  <div class="modal-dialog modal-sm">
									    <!-- Modal content-->
									    <div class="modal-content">
									      <div class="modal-header">
									        <button type="button" class="close" data-dismiss="modal">&times;</button>
									        <h4 class="modal-title">Upload File</h4>
									      </div>
									      <div class="modal-body">
									      <div class="form-group"> 
											 	<s:select onchange="setInvstId(this.value)" id="invstid" name="invstid" list="invstList"
											 	listKey="id" listValue="name" cssClass="form-control"
											 	headerKey="0" headerValue="Select Investigation"
											 	/> 
											 </div>
									        <s:form id="upload"  action="savedicomPacs" theme="simple" enctype="multipart/form-data" >
											<s:hidden name="selectedinvstid" id="selectedinvstid"/>
									        <div class="col-lg-12 col-md-12" style="background-color: #efefef;padding: 18px;margin-top: -13px;">
									        	<div class="col-lg-5 col-md-5 col-xs-12 col-sm-12" style="text-align:left;">
					                      			Attached File: <div id="attachdfilediv" class="attached"></div>
					                      		</div>
					                      		<div class="col-lg-7 col-md-7" style="float:right;margin-top: -4px;">
														 <button id="Button1" class="btn btn-warning" type="button" onclick = "AddFileUpload1()"><i class="fa fa-paperclip"></i> Attach Files</button>
													</div>
									        </div>
												<br>
												<br>
												<br>
											  <div class="">
												<input type="hidden" id="fileUpload" name = "fileUploadd" />
												  <div id = "drop" class="col-lg-12 col-md-12" style="padding-top: 5px;">
									      			  <!--FileUpload Controls will be added here -->
									      			  <div id="upload"></div>
									      			  <div id = "draftAttachments"></div>
					    						</div>
		   									</div>
	                      					 <input type="button" class="btn btn-primary btncr hidden" value="Save" onclick="saveFindings();">
											</s:form>
									      </div>
									      <div class="modal-footer">
									       <input type="button" onclick="savedcomfile()" class="btn btn-primary pull-right" value="Save">
											
									      </div>
									    </div>
									
									  </div>
									</div>
							
									
									<s:form action="findingPacs" theme="simple">
									<div id="addfinding" class="modal fade modal-draggable" role="dialog">
									  <div class="modal-dialog modal-sm">
									    <!-- Modal content-->
									    <div class="modal-content">
									      <div class="modal-header">
									        <button type="button" class="close" data-dismiss="modal">&times;</button>
									        <h4 class="modal-title">Add Findings</h4>
									      </div>
									      <div class="modal-body">
									        <div class="col-lg-12 col-md-12 col-xs-12 col-sm-12" style="padding: 0px;">
												<div class="">
												<div class="col-lg-7 col-md-7">
													<div class="form-group">
														<img src="cicon/mic_off.png" class="img-responsive micimg" onclick="startConverting1(this)" title="Microphone" id="changer"></img>
													
												  </div>
												</div>
												    <div class="col-lg-12 col-md-12 col-xs-12 col-sm-12" style="margin-top: 10px;"><!--
												     <textarea name="notes" cols="" rows="10" tabindex="119" class="form-control foemse" title=""></textarea>
												    -->
												    	<s:textarea onchange="saveautofinding(this.value)" style="height: 250px;" rows="10" cols="8" id="otnotes" name="otnotes"
														cssClass="showToolTip form-control" data-toggle="tooltip"
														title="Enter Other Template Text" placeholder="Enter finding" ></s:textarea>
												    </div>
												    
												   
												  </div>
											</div>
									      </div>
									      <div class="modal-footer">
									       <input type="submit"  class="btn btn-primary pull-right" value="Save">
											
									      </div>
									    </div>
									
									  </div>
									</div>
									</s:form>

 <!-- Update Patient UHID -->
<s:form action="edituhidPacs?clientid=0" theme="simple" id="updateuhidfrm">
<div id="updatepatientuhid" class="modal fade" role="dialog">

  <div class="modal-dialog modal-sm">
  
    <!-- Modal content-->
    <div class="modal-content">
       <div class="modal-header">
         	<button type="button" class="close" data-dismiss="modal">&times;</button>
          	<h4 class="modal-title">Update UHID</h4>
        </div>
  <div class="modal-body">
      	<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12">
      	 <%--  <s:hidden id="abriid"></s:hidden> --%>
      	<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12" style="padding-top: 10%;">
      			
                <div class="form-group">
	      			<label>UHID</label><label class="text-danger">*</label>
	      			<input type="text" class="form-control" id="uhid" name="uhid">
	      		</div>
	      		<div class="form-group" hidden="true">
	      			<label>UHID</label><label class="text-danger">*</label>
	      			<input type="hidden" class="form-control" id="abriid" name="abriid">
	      			<input type="hidden" class="form-control" id="pacsid" name="pacsid">
	      		</div>
      		</div>
      	</div>
     </div>
     <div class="modal-footer">
     	<button type="button" class="btn btn-primary" onclick="updateUHID()" style="margin-top: 60px;">Save</button>
       </div>
     </div>
   </div>
</div>  
</s:form>
									
									<script>
										$(".modal-draggable .modal-dialog").draggable({
									        handle: ".modal-header"
									    });
									</script>
									
								<script>
								
									var options = [];
									$( '.dropdown-menu a' ).on( 'click', function( event ) {
									   var $target = $( event.currentTarget ),
									       val = $target.attr( 'data-value' ),
									       $inp = $target.find( 'input' ),
									       idx;
									   if ( ( idx = options.indexOf( val ) ) > -1 ) {
									      options.splice( idx, 1 );
									      setTimeout( function() { $inp.prop( 'checked', false ) }, 0);
									   } else {
									      options.push( val );
									      setTimeout( function() { $inp.prop( 'checked', true ) }, 0);
									   }
									   $( event.target ).blur();
									   console.log( options );
									   return false;
									});
								</script>	
								
								<script>
									function startConverting1(element) {
									
									   var abc=element.src.split('/');
									   
									     var right = "cicon/mic_off.png";
									         var left = "cicon/mic.png";
									         element.src = element.bln ? right : left;
									         element.bln = !element.bln;
									         
									       //  document.getElementById("otnotes").value=localStorage.getItem("xx");
									   if(abc[5]=="mic_off.png")
									   {
									    startConverting();
									   }
									   else{
									   //var textvalue=document.getElementById("otnotes").value;
									  // localStorage.setItem("xx",textvalue);
									  // location.reload();
									   }
									   
									   
									
									       
									         
									     }
									</script>		
</body>
</html>