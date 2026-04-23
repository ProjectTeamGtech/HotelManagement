<%@page import="com.apm.common.web.common.helper.LoginHelper"%>
<%@page import="com.apm.common.web.common.helper.LoginInfo"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<%@page import="java.util.Date"%>
<script type="text/javascript" src="report/js/report.js"></script>
<script type="text/javascript" src="assesmentForms/js/jquery.table2excel.js"></script>
<%
LoginInfo loginInfo = LoginHelper.getLoginInfo(request); 

%>

<script>

 function printExcel() {

       $(".xlstable").table2excel({
					exclude: ".noExl",
					name: "Outcome Discharge Report List",
					filename: "outcomeDischargeReport",
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
								<div class="row details">
									<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12">

										<h4>Discharge Outcome Report</h4>

									</div>
								</div>
								<div class="row ">
									<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12">
										
<s:form action="odreportSummary" theme="simple" id = "invoicerportfrm">
<s:hidden name="order" id="order"/>
<s:hidden name="orderby" id="orderby"/>
	<div class="col-lg-12 col-md-12 topback2 hidden-print">
		
		<div class="form-inline">
			<div class="form-group">
				<s:select name="ipdopd" id="ipdopd" 
				list="#{'0':'All Location','IPD':'IPD','OPD':'OPD'}"
				cssClass="form-control chosen-select" onchange="setActionForAll()"></s:select>
			</div>
			<div class="form-group">
				<s:select name="payby" id="payby" 
				list="#{'0':'All Payed By','Client':'Self','Third Party':'Third Party'}"
				cssClass="form-control chosen-select" onchange="setActionForAll()"></s:select>
			</div>
			<div class="form-group">
				<s:select name="diaryUser" list="userList" listKey="id" listValue="diaryUser" cssClass="form-control chosen-select" headerKey="0" headerValue="All Practitioner" theme="simple" onchange = "setActionForAll()" ></s:select>
			</div>
			<div class="form-group">
				<s:select id="location" name="location" listKey="id"
				listValue="location" headerKey="0" headerValue="All Referral Location"
				list="locationList" value="location" cssClass="form-control chosen-select" onchange="setActionForAll()"></s:select>
			</div>
			<div class="form-group">
				<s:select cssClass="form-control showToolTip chosen-select" name="dischrgeOutcomes"
				id="dischrgeOutcomes" list="dischargeOutcomeList"
				listKey="id" listValue="name" headerKey="0"
				headerValue="All Outcomes" />
			</div>
			<div class="form-group">
				<s:select cssClass="form-control showToolTip chosen-select" name="dischargeStatus"
				id="dischargeStatus" list="dischargeStatusList"
				listKey="id" listValue="name" headerKey="0"
				headerValue="All Discharge Status" />
			</div>
			<div class="form-group">
				<s:select name="discharge" id="discharge" 
					list="#{'All':'Discharged All','0':'No','1':'Yes'}"
					cssClass="form-control chosen-select" onchange="setActionForAll()"></s:select>
			</div>
			<div class="form-inline">
			<div class="form-group">
			<label>Discharge From Date</label>
			</div>
			<div class="form-group" style="width:7%;">
				<s:textfield readonly="true" name="fromDate" id="fromDate"
					cssClass="form-control" theme="simple" style="width:100%;" placeholder="from date"></s:textfield>
			</div>
			
			
			<div class="form-group">
			<label> - To</label>
			</div>
			<div class="form-group" style="width:7%;">
				<s:textfield readonly="true" name="toDate" id="toDate"
					cssClass="form-control" theme="simple" style="width:100%;" placeholder="to date"></s:textfield>
			</div>
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
				<tr>
					<th>Sr No</th>	
					<th>IPD No.</th>
					<th>UHID</th>
					<th>Patient Name</th>
					<th>Payed By</th>
					<th>Practitioner</th>
					<th>Department</th>
					<th>Outcomes</th>
					<th>Discharge Status</th>
					<th>Discharged</th>
					<th style="text-align: center;">Admission Date</th>
				
				<th style="text-align: center;">Discharge Date</th>
				</tr>
				
				
				<s:if test="odReportList.size!=0">
							<s:iterator value="odReportList" status="rowstatus">
								<tr>
									<td><%=i++ %></td>
								<td><%if(loginInfo.getIpd_abbr_access()==1){ %>
								<s:property value="newipdabbr"/>
								<%}else{ %>
								<s:property value="ipdseqno"/>
								<%} %>
								</td>
								
								<td><s:property value="abrivationid"/></td>
									<td><s:property value="clientname"/></td>
									
								<s:if test="payby=='Client'">
									<td>Self (<s:property value="clientname"/>)</td>
								</s:if>
								<s:else>
									<td>Third Party (<s:property value="tpName"/>)</td>
								</s:else>
								
								<td><s:property value="practitionerName"/></td>
								<td><s:property value="location"/></td>
								
								<td><s:property value="outcomes"/></td>
								<td><s:property value="dschargeStatus"/></td>
								
								<s:if test="discharge==0">
									<td style="text-align: center;">No</td> 
									<td><s:property value="admissiondate"/> <s:property value="time"/></td>
								</s:if>
								<s:else>
									<td style="text-align: center;" >Yes</td> 
									<td><s:property value="admissiondate"/> <s:property value="time"/></td>
								</s:else>
								<td><s:property value="dischargedate"/></td>
								
								</tr>
							</s:iterator>
						</s:if>
						
						<s:else>
					No Record Found!!
				</s:else>
				
</table>
											

											
										
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



						
	
	