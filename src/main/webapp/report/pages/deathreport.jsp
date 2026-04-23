<%@taglib uri="/struts-tags" prefix="s"%>
<%@page import="java.util.Date"%>
<script type="text/javascript" src="report/js/report.js"></script>
<script type="text/javascript" src="assesmentForms/js/jquery.table2excel.js"></script>



<script>

 function printExcel() {

       $(".xlstable").table2excel({
					exclude: ".noExl",
					name: "Death Report List",
					filename: "deathReport",
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


function setSorting(column,order){
	if(order=='asc'){
		order = 'desc';
	}else{
		order = 'asc';
	}
	document.getElementById('orderby').value = column;
	document.getElementById('order').value = order;
	document.getElementById('invoicerportfrm').submit();
}


</script>

<div class="">

							<div class="">
							
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

										<h4>Death Report</h4>

									</div>
								</div>
								<div class="row ">
									<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12">
										
<s:form action="deathreportSummary" theme="simple" id = "invoicerportfrm">
<s:hidden name="order" id="order"/>
<s:hidden name="orderby" id="orderby"/>
	<div class="col-lg-12 col-md-12 topback2 hidden-print">
		
		<div class="form-inline">
			<div class="form-group" >
				<s:textfield readonly="true" name="fromDate" id="fromDate"
					cssClass="form-control" theme="simple" cssStyle="width: 80px;"  placeholder="from date"></s:textfield>
			</div>
			<div class="form-group" >
				<s:textfield readonly="true" name="toDate" id="toDate"
					cssClass="form-control" theme="simple" cssStyle="width: 80px;"  placeholder="to date"></s:textfield>
			</div>
			<div class="form-group" >
				<s:select name="payby" id="payby" 
				list="#{'0':'Pay By','Client':'Self','Third Party':'Third Party'}"
				cssClass="form-control chosen-select" onchange="setActionForAll()"></s:select>
			</div>
			<div class="form-group" >
				<s:select name="diaryUser" list="userList" listKey="id" listValue="diaryUser" cssClass="form-control chosen-select" headerKey="0" headerValue="All Practitioner" theme="simple" onchange = "setActionForAll()" ></s:select>
			</div>
			
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
				<tr><th>Sr. No.</th>
				<th>IPD No.</th>
				<th>UHID</th>
					<th>Patient Name</th>
					<th>Gender</th>
					<th>Age</th>
					<th>Mother Name</th>
					<th>Place of Birth</th>
					<th>Weight on birth</th>
					<th>Payed By</th>
					<th>Practitioner</th>
					<th>Department</th>
					
					<th>Discharge Status</th>
					<th>Diagnosis</th>
					<th>Admission Date</th>
					
					<th>Death Date</th>
				
				</tr>
				
				
				<s:if test="deathReportList.size!=0">
							<s:iterator value="deathReportList" status="rowstatus">
								<tr>
								<td><%=i++ %></td>
								<%-- <td><s:property value="ipdseqno"/></td> --%>
								 <td><s:property value="ipdseqno"/></td>
								 <td><s:property value="abrivationid"/></td>
									<td><s:property value="clientname"/></td>
									<td><s:property value="gender"/></td>
									<td><s:property value="Clientage"/></td>
									<td><s:property value="Mothername"/></td>
									<td><s:property value="Birthplace"/></td>
									<td><s:property value="Wtonbirth"/></td>
									
									
								<s:if test="payby=='Client'">
									<td>Self </td>
								</s:if>
								<s:else>
									<td>Third Party (<s:property value="tpName"/>)</td>
								</s:else>
								
								<td><s:property value="practitionerName"/></td>
								<td><s:property value="department"/></td>
								
							
								<td><s:property value="dschargeStatus"/></td>
								<td><s:property value="condition"/></td>
								
								<s:if test="discharge==0">
									<td> <s:property value="admissiondate"/></td>
								</s:if>
								<s:else>
									<td> <s:property value="admissiondate"/></td>
								</s:else>
								<td><s:property value="dischargedate"/></td>
								
								</tr>
							</s:iterator>
						</s:if>
						
						<s:else>
					No Record Found!!
				</s:else>
				
</table><br>
	<label>Gross Death Rate(Total Deaths*100/Total Patients Discharged): <s:property value="grossDeathRate"></s:property>%</label>		<br>								
<label>Net Death Rate (Total Deaths after 48 hrs/(Total Patients Discharged - (Total deaths-Total Deaths after 48 hrs)))*100 : <s:property value="netdeathrate"></s:property>%</label>
											
										
									</div>
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


