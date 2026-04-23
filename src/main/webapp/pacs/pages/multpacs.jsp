<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>

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
								<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12">
								<div class="col-lg-1 col-md-1 col-sm-1 col-xs-1 oneseticonleft">
									<img src="dashboardicon/pacs.png" class="img-responsive prescripiconcircle">
								</div>
								<div class="col-lg-11 col-md-11 col-sm-11 col-xs-11 titlestleftiocn">
								<h4>PACs Dashboard  | <span style="font-size:11px;"> <s:if test="clientid!=0"><s:property value="clientname"/></s:if></span></h4> 
								</div>
								</div>
								
								
								
							</div>
							<s:form action="Pacs?clientid=0" id="showpacsfrms" theme="simple">
							<s:hidden name="selectedmodality" id="selectedmodality"/>
							<div class="row ">
								<div class="col-lg-12 col-md-12">
										<div class="col-lg-12 col-md-12 topback2">
										<div class="form-inline search">
										    <div class="form-group" style="width: 7%;">
										  <s:textfield readonly="true" name="fromDate" id="fromDate"
											cssClass="form-control" theme="simple" cssStyle="width:100%;"></s:textfield>
											</div>
											<div class="form-group" style="width: 7%;">
										 <s:textfield readonly="true" name="toDate" id="toDate"
											cssClass="form-control" theme="simple" cssStyle="width:100%;"></s:textfield>
										 </div>
										 
										 
											 
										 
										  
										  <input type="button" class="btn btn-primary" value="Go" onclick="showPacsData()">
										  <a href="#" onclick="refreshData()" class="btn btn-primary" title="Refresh"><i class="fa fa-refresh"></i></a>
										  <a href="#" data-toggle="modal" data-target="#uploadfile" class="btn btn-primary pull-right hidden" title="Browse File">Browse File</a>
										  <a href="#" data-toggle="modal" data-target="#addfinding" class="btn btn-primary pull-right hidden" title="Add Findings" style="margin-right: 5px;">Add Findings</a>
										</div>
										</div>
										
										</s:form>
										
										
										
										<div class="row">
												<div class="col-lg-12 col-md-12 col-xs-12">
											<table class="table table-bordered" width="100%">
											<thead>
												<tr class="tableback">
													<th class="pdate">Sr.No</th>
													<th class="pnew"> Date</th>
													<th class="pnew">Folder</th>
													<th class="pnew">Upload Finding</th>
													<!--<th class="pview">Status</th>
													--><th class="pview">View</th>
												</tr>
											</thead>
											<tbody>
											<%int cnt = 1; %>
											<s:iterator value="folderList">
												<tr id="<s:property value="id"/>" >
													<td><%=cnt %></td>
													<td><s:property value="date"/></td>
													<!--<td><a href="multiPacs?id=<s:property value="id"/>"><s:property value="dir"/></a></td>
													-->
													<td><a id="myAnchor<s:property value="id"/>" href="#" onclick="setshowdicomid(<s:property value="id"/>,1)"><s:property value="dir"/></a></td>
													<td>
													<a href="#" data-toggle="modal" onclick="uploadfinding(<s:property value="id"/>)"  title="Upload File">Upload File</a>
													</td>
													<td>
														<a title="Download Finding" href="downloadPacs?fname=<s:property value="filename"/>"><s:property value="filename"/> </a>
													</td>
													<!--
													
													<td> 
														<s:if test="fstatus==1">
															Updated
														</s:if>
														<s:else>
															Not Updated
														</s:else>
													</td>
													-->
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
									<div id="uploadfileword" class="modal fade" role="dialog">
									  <div class="modal-dialog modal-sm">
									    <!-- Modal content-->
									    <div class="modal-content">
									      <div class="modal-header">
									        <button type="button" class="close" data-dismiss="modal">&times;</button>
									        <h4 class="modal-title">Upload File</h4>
									      </div>
									      <div class="modal-body">
									        <s:form id="upload"  action="wordfilePacs" theme="simple" enctype="multipart/form-data" >
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
	                      					 <input type="submit" class="btn btn-primary btncr " value="Save" onclick="saveFindings();">
											</s:form>
									      </div>
									      <div class="modal-footer">
									       <input type="submit"  class="btn btn-primary pull-right hidden" value="Save">
											
									      </div>
									    </div>
									
									  </div>
									</div>
							
									
									<script>
										$(".modal-draggable .modal-dialog").draggable({
									        handle: ".modal-header"
									    });
									</script>
									
