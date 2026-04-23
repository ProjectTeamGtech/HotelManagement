<%@page import="com.apm.Registration.eu.entity.Currency"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<%@page import="java.util.Date"%>
<script type="text/javascript" src="report/js/report.js"></script>
<script type="text/javascript" src="assesmentForms/js/jquery.table2excel.js"></script>
<script type="text/javascript" src="common/js/pagination.js"></script>
<link href="https://fonts.googleapis.com/css2?family=Open+Sans:wght@300;400;600;700&display=swap" rel="stylesheet">
<script>

 function printExcel() {

       $(".xlstable").table2excel({
					exclude: ".noExl",
					name: "OPD TAT Report",
					filename: "consultationReport",
					fileext: ".xls",
					exclude_img: true,
					exclude_links: true,
					exclude_inputs: true
				});
   }
  

$(document).ready(function() {

/* 	$("#fromDate").datepicker({

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
	}); */

	var year = document.getElementById("selectyr").value;
	document.getElementById("year").className="";
	document.getElementById("year").value = year;
	$("#year").trigger("chosen:updated");
	$(".chosen-select").chosen({allow_single_deselect: true});
	var year1 = document.getElementById("selectyr1").value;
	document.getElementById("year2").className="";
	document.getElementById("year2").value = year1;
	$("#year2").trigger("chosen:updated");
	$(".chosen-select").chosen({allow_single_deselect: true});

});
</script>




<style>
.text-center{
text-align: center;
}
td{
color: black;
}
</style>
<div class="" style="font-family: 'Open Sans', sans-serif;">
	<div class="print-visible hidden-md hidden-lg letterheadhgt" style="height: 135px;">
		<div id="newpost" class="col-lg-12 col-md-12 col-xs-12 col-sm-12 borderbot">
			<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12" style="padding-left:0px;padding-right:0px;">
				 <link href="common/css/printpreview.css" rel="stylesheet" />
			<%@ include file="/accounts/pages/letterhead.jsp" %>
			</div>
		</div>
	</div>
	<div class="row details">
									<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12">

										<h4>Consultation Report</h4>

									</div>
								</div>

<div class="row ">

<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12">
<s:form action="consultationReport" theme="simple" id = "consultationfrm">
<div class="col-lg-12 col-md-12 topback2 hidden-print">
<div class="form-inline">
			<s:hidden name="year1" id="selectyr" />
			<label>From Date</label>
			<div class="form-group" style="width:7%;">
			<s:select cssClass="form-control chosen-select"
						list="#{'00':'January', '01':'February', '02':'March', '03':'April' , '04':'May', '05':'June', '06':'July','07':'August','08':'September','09':'October','10':'November','11':'December'}"
						theme="simple" id="month" name="month" style="width: 43%" />
			</div>
			
			<div class="form-group " style="width: 8%">
					<select name="year" id="year" class="form-control chosen-select " style="width: 65%;height: 48px;">
						<%
							for (int k = 1980; k <= 2030; k++) {
						%>
						<option value="<%=k%>"><%=k%></option>
						<%
							}
						%>
					</select> 
			<!-- 		<label class="focus-label">Select Year</label>  -->
				</div> &ensp;
			<s:hidden name="year3" id="selectyr1" />
			<label>To Date</label>
			<div class="form-group" style="width:7%;">
			<s:select cssClass="form-control chosen-select"
						list="#{'00':'January', '01':'February', '02':'March', '03':'April' , '04':'May', '05':'June', '06':'July','07':'August','08':'September','09':'October','10':'November','11':'December'}"
						theme="simple" id="month" name="month1" style="width: 43%" />
			</div>
			
			<div class="form-group " style="width: 8%">
					<select name="year2" id="year2" class="form-control chosen-select " style="width: 65%;height: 48px;">
						<%
							for (int k = 1980; k <= 2030; k++) {
						%>
						<option value="<%=k%>"><%=k%></option>
						<%
							}
						%>
					</select> 
			<!-- 		<label class="focus-label">Select Year</label>  -->
				</div> &ensp;
			<div class="form-group">
					<s:select name="diaryUser" list="userList" listKey="id" listValue="diaryUser" cssClass="form-control chosen-select" headerKey="0" headerValue="All Practitioner" theme="simple" onchange = "setActionForAll()" ></s:select>
				</div>
			&nbsp;&nbsp;&nbsp;
			
			
			<div class="form-group">
				<s:submit value="Go" theme="simple" cssClass="btn btn-primary"></s:submit>
				<a type="button" class="btn btn-primary"  title="Print" onclick="printpage()"><i class="fa fa-print"></i></a>
			<a type="button" id="btnxls" title="Save As XLS" onclick="printExcel()" class="btn btn-primary"><i class="fa fa-file-excel-o"></i></a>
				</div>

</div>
</div>
</s:form>
<%int i=1; %>
<table class="my-table xlstable" style="width: 100%">

				<tr bgcolor="#e0faff" >
					<td>Sr. No.</td>
					
					<td>UHID</td>
					<td>Type of Patient</td>
					<td>Patient Name</td>
					<td>Admission No.</td>
					<td> Admission Date</td>
					<!-- <td>Discharge No.</td> -->
					<td>Discharge Date</td>
					<td class="text-center">Shift/Charges Details</td>
					<!-- <td class="text-center">Amount</td>
					<td class="text-center">Total Amount</td>
					<td class="text-center">Discount Amount</td> -->
					</tr>
				 <s:if test="allChargeWardlist.size!=0">
				<s:iterator value="allChargeWardlist">
				<tr>
					<td><%=i++ %></td>
					<td ><s:property value="uhid"/></td> 
					<td ><s:property value="-"/></td> 
					<td><s:property value="clientname"/></td>
					<td><s:property value="ipdid"/></td>
					<td class="text-center"><s:property value="admissiondate"/></td>
					<td class="text-center"><s:property value="dischargedate"/></td>
					<td style="text-align: center;">
					<table>
					<%int j=1; %>
					<tr>
					<td>Ward Name/Bed Name</td>
					<td>From Date-To Date</td>
					<td>Duration</td>
					<td>Qty</td>
					<td>Charge</td>
					<td>Total Charge</td>
					</tr>
					<s:iterator value="shiftlist">
					<tr>
						<td style="font-weight: 800;"><%=j++ %>)&emsp;<s:property value="wardname"/>/<s:property value="bedname"/></td>
						<td><s:property value="fromdate"/></td>
						<td><s:property value="duration"/></td>
						<td></td>
						<td></td>
						<td></td>
						</tr>
					<s:iterator value="wardwiseChargelist">
						<tr>
						<td style="font-weight: 500;"><s:property value="apmttype"/></td>
						<td></td>
						<td></td>
						<td><s:property value="quantity"/></td>
						<td><s:property value="charge"/></td>
						<td><s:property value="chargetotal"/></td>
						
						</tr>
					</s:iterator>	
						</s:iterator>
						</table>
						</td>
				</tr>
				</s:iterator>
				</s:if> 
				</table>
</div>
</div>


<%-- <s:form action="opdtatreportReport" name="paginationForm" id="paginationForm" theme="simple">
							    
							     <s:hidden name="fromDate"></s:hidden>
							     <s:hidden name="toDate"></s:hidden>
							      <s:hidden name="diaryUser"></s:hidden>
								<div class="col-lg-12 col-md-12" style="margin-top:15px;">
									<div class="col-lg-4 col-md-4 text-left" style="padding:0px;">
										Total:<label class="text-info"><s:property value="totalRecords" /></label>
									</div>
									<jsp:include page="/common/pages/pagination.jsp"></jsp:include>
								</div>
							</s:form>  --%>
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