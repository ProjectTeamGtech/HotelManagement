<%@page import="com.apm.common.utils.DateTimeUtils"%>
<%@page import="com.apm.DiaryManagement.eu.entity.Client"%>
<%@page import="java.util.ArrayList"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<%@page import="java.util.Date"%>
<script type="text/javascript" src="diarymanagement/js/feedback.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-select/1.13.1/js/bootstrap-select.min.js"></script>
<script type="text/javascript" src="assesmentForms/js/jquery.table2excel.js"> </script>
<script>


 function printExcel() {

       $(".xlstable").table2excel({
					exclude: ".noExl",
					name: "report",
					filename: "Feedback_Report",
					fileext: ".xls",
					exclude_img: true,
					exclude_links: true,
					exclude_inputs: true
				});
   }
  

$(document).ready(function() {

	$("#fromDate").datepicker({

		dateFormat : 'dd-mm-yy',
		yearRange: yearrange,
		minDate : '30-12-1880',
		changeMonth : true,
		changeYear : true

	});

	$("#toDate").datepicker({

		dateFormat : 'dd-mm-yy',
		yearRange: yearrange,
		minDate : '30-12-1880',
		changeMonth : true,
		changeYear : true
	});
	 document.addEventListener("contextmenu", function(e){
			e.preventDefault();
			}, false); 
});

</script>
<%-- <style>
@media print {
.my-table th{
display: table-header-group;
}
}

</style> --%>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-select/1.13.1/css/bootstrap-select.min.css">
<div class="row details row details mainheader">
<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12">
</div><center><h4><i class="fa fa-comments"></i> &nbsp;All FeedBacks</h4></center>
</div>

<div class="row ">
<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12">
<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12 ">
		<br>	
		<s:form action="seeallFeedbacksClient" theme="simple"     id = "feedbackform">
		
		<div class="form-inline hidden-print">
		
			<div class="form-group" style="width:8%;">
			<label>From Date :</label>
					<s:textfield readonly="true" name="fromdate" id="fromDate"
					cssClass="form-control" theme="simple" style="width:90%;" placeholder="From Date"></s:textfield>
			</div>
			
			<div class="form-group" style="width:8%;">
			<label>To Date :</label>
					<s:textfield readonly="true" name="todate" id="toDate"
					cssClass="form-control" theme="simple" style="width:90%;" placeholder="To Date"></s:textfield>
			</div>
			
			<div class="form-group" style="width:5%;">
			<label>Rating :</label>
				<s:select name="ratingper" id="ratingper" 
				list="#{'0':'All','20':'1','40':'2','60':'3','80':'4','100':'5'}"
				cssClass="form-control chosen-select"  theme="simple"></s:select>
			</div>
			
			<div class="form-group" style="width:15%;">
			<label>Patients :</label>
				<s:select name="patient" id="patientlist" list="patientlist" listKey="id" listValue="%{fullname+ '  ( '+abrivationid+' )'}" 
				style="width:80%;" data-live-search="true" cssClass="form-control chosen-select" headerKey="0" 
				headerValue="All Patients" theme="simple" ></s:select>
			</div>
			
				
			<div class="form-group" style="width:5%;">
			<label>Type :</label>
				<s:select name="treatmenttype" id="treatmenttype" 
				list="#{'opd':'OPD','ipd':'IPD','hd':'HD'}"
				cssClass="form-control chosen-select" onchange="getlist(this.value)" theme="simple"></s:select>
			</div>
			
			<div class="form-group" >
			<br>
			<s:submit value="Go" theme="simple" cssClass="btn btn-primary"></s:submit>
			<a type="button" class="btn btn-primary"  title="Print" onclick="printpage()"><i class="fa fa-print"></i></a>
			<a type="button" id="btnxls" title="Save As XLS" onclick="printExcel()" class="btn btn-primary"><i class="fa fa-file-excel-o"></i></a>
			</div>
			</div>
			</s:form>
		
				<%ArrayList<Client> list=(ArrayList<Client>) request.getAttribute("feedbackbypatient");%>
					<br><%int i=0; %>
						<table class="my-table xlstable " style="width: 100%">
						<tr><th>sr no</th>
						<th> Date</th>
						<th> Patient name</th>
						
						<th style="width: 50%"> Remarks</th>
						<th> Over All Rating</th>
						<th>print</th>
						</tr>
						<s:iterator value="feedbackbypatient">
						<%String remark=DateTimeUtils.isNull(list.get(i).getFeedbackname()).toString(); %>
						<tr>
						<td><%=++i %></td>
						<td><s:property value="date"/></td>
						<td><s:property value="clientName"/></td>
						
						<td><%=remark %></td>
						<td><meter value="<s:property value='rating'/>" min="0" max="<s:property value='total'/>" low="<s:property value="total/2"/>"></meter>  <s:property value="ratingper"/> % </td>
						<td><a href="#" onclick="opencPopup('printfeedbackOFPatientClient?id=<s:property value="feedbackparentid"/>&clientname=<s:property value="clientName"/>&date=<s:property value="date"/>&feedback=<s:property value="feedbackname"/>')"><i class="fa fa-print" aria-hidden="true"></i></a></td>
						</tr>
						</s:iterator>
						</table>
			
			</div>
			</div>								
</div>
<script src="common/chosen_v1.1.0/chosen.jquery.js" type="text/javascript"></script>
  <script type="text/javascript">
    var config = {
      '.chosen-select'           : {},
      '.chosen-select-deselect'  : {allow_single_deselect:true},
      '.chosen-select-no-single' : {disable_search_threshold:10},
      '.chosen-select-no-results': {no_results_text:'Oops, nothing found!'},
      '.chosen-select-width'     : {width:"95%"}
    }
    for (var selector in config) {
      $(selector).chosen(config[selector]);
    }
  </script>

