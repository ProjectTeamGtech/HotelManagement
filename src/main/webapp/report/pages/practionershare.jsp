<%@page import="com.apm.common.web.common.helper.LoginHelper"%>
<%@page import="com.apm.common.web.common.helper.LoginInfo"%>
<%@page import="com.apm.main.common.constants.Constants"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<%@page import="java.util.Date"%>
<script type="text/javascript" src="report/js/commission.js"></script>
<script type="text/javascript" src="thirdParties/js/nicEdit.js"></script>
<script type="text/javascript" src="assesmentForms/js/jquery.table2excel.js"></script>

<%LoginInfo loginfo = LoginHelper.getLoginInfo(request);%>

<script>

 function printExcel() {

       $(".xlstable").table2excel({
					exclude: ".noExl",
					name: "Practitoner Commision Report",
					filename: "commisionReport",
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


bkLib.onDomLoaded(function() {
    
  	 new nicEditor().panelInstance('emailBody');
  	 $('.nicEdit-panelContain').parent().width('500px');
  	 $('.nicEdit-panelContain').parent().next().width('500px');
  	 
  	 $('.nicEdit-main').width('100%');
  	 $('.nicEdit-main').height('80px');
});

</script>

<html>
<body>

<div class="">
							<div class="">
								<div class="row details">
									<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12">

										<h4>Share Report</h4>

									</div>
								</div>
								<div class="row ">
									<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12">
										<div>


											
<s:form action="PractitionerListCommission" theme="simple" id = "userFrm">

<s:hidden name="action" value="1"/>
	<div class="col-lg-12 col-md-12 topback2 hidden-print">
		<div class="form-inline">
			<div class="form-group">
				<s:select name="diaryUser" list="userList" listKey="id" listValue="diaryUser" 
				cssClass="form-control chosen-select" headerKey="0" headerValue="Select All Practitioner" theme="simple" onchange = "setActionForAll()" ></s:select>
			</div>
			<div class="form-group">
				<s:select id="location" name="location" listKey="id"
				listValue="location" headerKey="0" headerValue="Referral All Location"
				list="locationList" value="location" cssClass="form-control chosen-select" onchange="setActionForAll()"></s:select>
			</div>
			<div class="form-group" style="width: 7%;">
				<s:textfield readonly="true" name="fromDate" id="fromDate"
				cssClass="form-control" theme="simple" placeholder="from date" style="width: 100%;"></s:textfield>
			</div>
			<div class="form-group" style="width: 7%;">
				<s:textfield readonly="true" name="toDate" id="toDate"
				cssClass="form-control" theme="simple" placeholder="to date" style="width: 100%;"></s:textfield>
			</div>
			<div class="form-group">
				<%-- <s:submit value="Go" theme="simple" cssClass="btn btn-primary"></s:submit> --%>
				<a href="#" class="btn btn-primary" onclick="submitPractitionershareReport()">Go</a>

				<a type="button" class="btn btn-primary" title="Print" onclick="printpage()"><i class="fa fa-print"></i></a>
				<a type="button" title="Save As PDF" class="btn btn-primary" onclick="return preview();"><i class="fa fa-file-pdf-o"></i></a>
				<a type="button" id="btnxls" title="Save As XLS" onclick="printExcel()" class="btn btn-primary btnxls"><i class="fa fa-file-excel-o"></i></a>
			</div>
		</div>
	
	
		<div id="sendMailCommissionId" ></div>
		
	</div>
	

<s:if test="practitionerList.size!=0">	
<div class="row">
		<div class="col-lg-12">
			<s:actionmessage cssClass="messageDiv" />			
				<table class="my-table xlstable">
					<thead>
						<tr>
							<th>No</th>
							<th>Consultant Name</th>
							<th class="hidden">Practitioner No.</th>
							<th>Department</th>
							<th>Booked</th>
							<th>DNA</th>
							<th>Completed</th>
							<th>Incomplete</th>
							<th>Invoiced</th>
							<th>Paid </th>
							<th>Revenue</th>
							<th class="hidden">Share % DNA</th>
							<th>Share % </th>
							<th>Share Amt</th>
						</tr>
					</thead>
					<tbody>										
						
						<% int srno = 1;%>
							<s:iterator value="practitionerList" status="rowstatus">
								<tr id="<s:property  value="practitionerId" />" >								
									<td><%=srno%></td>	
									<td><s:property  value="practitionerName" /></td>
									<td class="hidden"><s:property  value="practitionerId" /></td>
									<td><s:property  value="clinicLocation" /></td>
									<td><s:property  value="totalAppNo" /></td>
									<td><s:property  value="totalDNA" /></td>
									<td><s:property  value="totalCompleted" /></td>
									<td><s:property  value="totalIncompleted" /></td>
									<td><s:property  value="totalChargeInvoiced" /></td>
									<td><s:property  value="totalInvoicePaid" /></td>
									<td><%=Constants.getCurrency(loginfo) %><s:property  value="totalCharge" /></td>
									
									<s:if test="chtype==1"> 
										<td class="hidden"><%=Constants.getCurrency(loginfo) %><s:property  value="DNACharge" /></td>
										<td><%=Constants.getCurrency(loginfo) %><s:property  value="CACharge" /></td>
									</s:if>
									<s:else>
										<td class="hidden">% <s:property  value="DNACharge" /></td>
										<td>% <s:property  value="CACharge" /></td>
									</s:else>
									
									<td><%=Constants.getCurrency(loginfo) %><s:property  value="totalCommission" /></td>
									</tr>
									<%srno++;%>
									<tr id="hiddenDetailsDiv<s:property value="practitionerId"/>"
									style="display: none" aria-hidden="true">
									<td colspan="13" id="hiddenDetailsDiv1<s:property value="practitionerId"/>">
									</td>
								</tr>
							</s:iterator>
						
				</tbody>	
							
			</table>
		</div>
	</div>
	</s:if>
	<s:else>
	 <h3 class="text-center">
			<i class="fa fa-times text-danger"></i> No Practitioner Share /Fee Report found!!
		</h3>
	</s:else>	
	
	</s:form>
 
 	<!-- Modal Email-->
	
<div class="modal fade" id="sendEmailCommissionPopup" tabindex="-1" role="dialog"
	aria-labelledby="lblsendEmailPopUp" aria-hidden="true">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal">
					<span aria-hidden="true">&times;</span><span class="sr-only">Close</span>
				</button>
				<h4 class="modal-title">Send Email</h4>
			</div>
			<div class="modal-body">
				<div class="row">
					<div class="col-lg-12">
					<div class="row">
					<div class="col-lg-1 col-md-1">	
					</div>
				
					</div>
						<div class="form-group">
							<label>To:</label>
							<s:textfield theme="simple" id = "reportEmail" name = "email" cssClass="form-control showToolTip"
								placeholder="Enter email address of receiver" title="Enter Email Id" data-toggle="tooltip" />
						</div>
						<div class="form-group">
							<label>Cc:</label>
							<s:textfield theme="simple" id = "reportccEmail" name = "ccEmail"	cssClass="form-control showToolTip" title="Enter Cc"
								data-toggle="tooltip" placeholder="Enter Cc" />
						</div>
						<div class="form-group">
							<label>Subject:</label> <input type="text" name= "subject" id = "reportsubject" class="form-control showToolTip"
								data-toggle="tooltip" title="Enter Subject" placeholder="Enter Subject">
						</div>
						<div class="form-group">
							<label>Body:</label>
							<textarea class="form-control showToolTip" data-toggle="tooltip" rows="20" cols="60"
								title="Enter Body" name="emailBody"  id="emailBody" ></textarea>
						</div>
						<div class="form-group">
							<s:property value="filename"/><span style="margin-left:3px;"><a href="invoice/<s:property value="filename"/>" target="blank"><i
								class="fa fa-file-pdf-o fa-2x text-danger" title="Attached PDF"></i></a></span> 
						</div>
						<div class="form-group" id="pdfReportMailId" style="display: none;">
							
						</div>
						<div class="form-group">
						<button type="button" class="btn btn-primary" onclick="sendReportrMail();">Send</button>
						<button type="button" class="btn btn-primary" data-dismiss="modal">Close</button>
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
							</div>
						</div>




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

 

 </body>
 </html>
 