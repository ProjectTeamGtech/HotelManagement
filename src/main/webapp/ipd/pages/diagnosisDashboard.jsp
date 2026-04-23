
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
   <script type="text/javascript" src="assesmentForms/js/jquery.table2excel.js"></script>
   <%LoginInfo loginInfo = LoginHelper.getLoginInfo(request); %>
 <SCRIPT type="text/javascript" >
 function printExcel() {

	    $(".xlstable").table2excel({
						exclude: ".noExl",
						name: "Purchase GST report",
						filename: "DiagnosisReport",
						fileext: ".xls",
						exclude_img: true,
						exclude_links: true,
						exclude_inputs: true
					});
	}
 
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
       });
		
    </SCRIPT>    
    <script type="text/javascript" src="mrd/js/mrd.js"></script>
 <style type="text/css">
 select {
	color: black !important; 
	
}
option{
	color:black !important;
	}
	
 </style>
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
								<h4>Diagnosis Dashboard</h4>
								</div>
									</div>
									<div class="col-lg-12 col-md-12 paddingnil hidden-print">
										<div class="col-lg-12 col-md-12 topback2">
										<form id="diagnose" name="diagnose" action="diagnosisdashboardInitialDischarge" method="post" class="form-inline">
											  <div class="form-group">
											  <s:select cssClass="form-control" list="#{'IPD':'IPD', 'OPD':'OPD'}" name="patient_department" />
											  </div>
											  <div class="form-group">
											    <s:textfield id="fromdate" name="fromdate"  cssClass="form-control" placeholder="From Date" />
											  </div>
											  <div class="form-group">
											    <s:textfield  name="todate" id="todate"  cssClass="form-control" placeholder="To Date" />
											  </div>
											  <div class="form-group">
											  <s:select  cssClass="form-control" list="#{'0':'By admission Date', '1':'By discharge Date'}" name="filter_sortby" />
											</div>
											   <div class="form-group">
											<%-- <s:select name="diagnose" list="conditionlist" listKey="id" listValue="treatmentName" cssClass="form-control chosen-select" headerKey="0" headerValue="select diagnosis"   cssStyle="width:20%;color:black"></s:select>
											  --%>
											 <%-- <s:select name="diagnose" id="id" cssClass="form-control chosen-select" 
                                              list="conditionlist" listKey="id" listValue="treatmentName" 
                                              headerKey="0" headerValue="Select  Diagnosis" cssStyle="width:20%;"/> --%>
                                              <s:textfield theme="simple" name="diagnose" placeholder="Search By Diagnosis"  cssClass="form-control" />
											 </div>
											  <input type="submit" value="GO" class="btn btn-primary">
											  <!-- <div class="form-group">
										  	        <a type="button" id="btnxls" title="Save As XLS" onclick="printExcel()" class="btn btn-primary"><i class="fa fa-file-excel-o"></i></a>
										       </div> -->
 
										  	  <div class="form-group">
										       <a type="button" id="btnxls" title="Save As XLS" onclick="printExcel()" class="btn btn-primary"><i class="fa fa-file-excel-o"></i></a>
										  	    <a href="#" class="btn btn-warning" onclick="printpage()"><i class="fa fa-print"></i></a>
										  		<input type="button" class="btn btn-primary" data-toggle="modal" data-target="#groupsmsmodel"  value="Send SMS ">
										  	  </div>
											 </form>
										</div>
									</div>
								</div>
								
								<s:actionmessage/>
								<div class="row " >
									<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12">
										<div>
											<table class="table  table-striped xlstable" style="width: 100%;font-size: 8px">
												<thead>
												 
													<tr>
													<th >Select All <br>
                                						<input type="checkbox"  class="pacssw hidden-print" onclick="setchkForAll()" value="<s:property value="clientid"/>"/>
                                						</th>
														<th >Sr No</th>
														<th>UHID</th>
														<th >Invoice Type</th>
														<th >Ipd/Opd NO</th>
														<th >Patient Name</th>
														<th >Age on Admission/Gender</th>
														<!-- <th hidden="true">Last Date of Appointment/Discharge</th> -->
														<th >Admission Date</th>
														<th >Discharge Date</th>
														<th style="width: 30%;">Diagnosis</th>
														<%if(loginInfo.isSimpliclinic()){ %>
														<th>Consultation</th>
														<%} %>
													</tr>
													</thead>
													<tbody>	
													<%int i=0; %>
														<s:iterator value="ipddiagnosis">
															<%++i;%>
																	<tr>
																		<td><input type="checkbox" name='' class="pacss hidden-print" onclick="setActionForAll()" value="<s:property value="clientid"/>"></td>
																		<td><%=i%></td>
																		
																		<td><s:property value="abrivationid"/> </td>
																		<td><s:property value="invoicetype"/> </td>
																		<td><s:property value="ipdabrivationid"/> </td>
																		<td><s:property value="clientname"/> </td>
																		<td><s:property value="AgeonAdmn"/> </td>
																		<%-- <td hidden="true"><s:property value="discharge_end_date"/></td> --%>
																		<td><s:property value="admissiondate"/> </td>
																		<td><s:property value="Dischargedate"/></td>
																		<td><s:property value="diagnosis"/></td>
																		<%if(loginInfo.isSimpliclinic()){ %>
																		<td><a onclick="opencPopup('printConsulatationFormCommonnew?clientId=<s:property value="clientid"/>+&apmtId=<s:property value="appointmentid"/>')"> <i class='fa fa-print fa-2x' title='Print'></i></a></td>
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
	



<div id="groupsmsmodel" class="modal fade" role="dialog">
  <div class="modal-dialog modal-sm" style="width: 30%">

    <!-- Modal content-->
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal">&times;</button>
        <h4 class="modal-title">Group SMS</h4>
      </div>
      <input type="hidden" name="hideselect" id="hideselect" value=""/>
      <div class="modal-body">
     			 <div class="form-inline">
        					<div class="form-group" id="smstemplist">
     
    						<s:select list="smstemplate"
							onchange="getsmstemplate(this.value)" listKey="id"
							listValue="smstmp" cssClass="form-control" headerKey="0"
							headerValue="Select Template"></s:select> 
							</div>
							
							 <div class="form-group">
							&nbsp;&nbsp;&nbsp;&nbsp;<input type="text" placeholder="Add Template Name" class='form-control' id='smstemplate'>	
							</div>
							
							 <div class="form-group">
							<input type="button"  class='btn btn-primary' value ='save' onclick="saveSmsTemplates()">
							</div>
							
							</div>
							<br>
        	<textarea rows="5"  class="form-control" id="groupsmstosend" placeholder="160 Character Only" style="background-color: beige;" maxlength="160"></textarea>
      </div>
      <div class="modal-footer">
        <input type="button" class="btn btn-danger" onclick="groupsms()"  value="Send SMS" id="groupsmsid">
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
		 <script src="common/chosen_v1.1.0/chosen.jquery.js" type="text/javascript"></script>
  <script src="common/chosen_v1.1.0/docsupport/prism.js" type="text/javascript" charset="utf-8"></script>
  <script type="text/javascript">
    var config = {
      '.chosen-select'           : {},
      '.chosen-select-deselect'  : {allow_single_deselect:true},
      '.chosen-select-no-single' : {disable_search_threshold:10},
      '.chosen-select-no-results': {no_results_text:'Oops, nothing found!'},
      '.chosen-select-width'     : {width:"100%"}
    }
    for (var selector in config) {
      $(selector).chosen(config[selector]);
    }
  </script>
 <script  type="text/javascript">
	function setActionForAll(){
		var hideselect="";
	  $('.pacss').each(function() { 
			 if(this.checked==true) {
				if(hideselect==''){
					hideselect =this.value;
				} else{
					hideselect =hideselect + ',' + this.value;	
				}
			 }     
		});
		
	  document.getElementById('hideselect').value=hideselect;
	}
	function setchkForAll(){
	  $('.pacss').each(function() { 
				
				this.checked=true;
		});
		
	  setActionForAll();
	}
	  </script>
 </html>

