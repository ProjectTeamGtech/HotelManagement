<%@taglib uri="/struts-tags" prefix="s"%>
<%@page import="java.util.Date"%>
<script type="text/javascript" src="report/js/report.js"></script>
<script type="text/javascript" src="assesmentForms/js/jquery.table2excel.js"></script>


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
 
 

  function printExcel() {

       $(".xlstable").table2excel({
					exclude: ".noExl",
					name: "Returning Payment Report List",
					filename: "returnPaymentReport",
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

										<h4>Returning Patients</h4>

									</div>
								</div>
								<div class="row ">
									<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12">
										
<s:form action="returningptsSummary" theme="simple" id = "invoicerportfrm">
<s:hidden name="order" id="order"/>
<s:hidden name="orderby" id="orderby"/>
	<div class="col-lg-12 col-md-12 topback2 hidden-print">
	
	<div class="form-inline">
		<div class="form-group">
			<s:select name="diaryUser" list="userList" listKey="id" listValue="diaryUser" cssClass="form-control chosen-select" headerKey="0" headerValue="All Practitioner" theme="simple" onchange = "setActionForAll()" ></s:select>
		</div>
		<div class="form-group">
			<s:select id="location" name="location" listKey="id"
				listValue="location" headerKey="0" headerValue="All Referral Location"
				list="locationList" value="location" cssClass="form-control chosen-select" onchange="setActionForAll()"></s:select>
		</div>
		<div class="form-group">
			<s:select id="condition" name="condition" listKey="id" onchange="setActionForAll()"
				listValue="treatmentName"  headerKey="0" headerValue="All Condition"
				list="treatmentTypeList" cssClass="form-control showToolTip chosen-select"></s:select>
		</div>
		<div class="form-group">
			<s:select id="sourceOfIntro" name="sourceOfIntro"
			list="sourceOfIntroList" headerKey="0" listValue="sourceOfIntro" listKey="id"
			headerValue="All Introduction"
			title="Select Source Of Introduction" 
			cssClass="form-control showToolTip chosen-select" data-toggle="tooltip"/>
		</div>
		<div class="form-group">
			<s:if test="%{#referalList != 'null'}" >
			<s:select cssStyle="height: 31px;padding: 0px" cssClass="form-control chosen-select" 
						id="referal" name="referal" list="referalList" listKey="id" listValue="referal" 
						headerKey="0" theme="simple" headerValue="All Referred By"  />
						
			</s:if>
		</div>
		<div class="form-group" style="width:7%;">
			<s:textfield readonly="true" name="fromDate" id="fromDate"
					cssClass="form-control" theme="simple" style="width:100%;" placeholder="from date"></s:textfield>
		</div>
		<div class="form-group" style="width:7%;">
			<s:textfield readonly="true" name="toDate" id="toDate"
					cssClass="form-control" theme="simple" style="width:100%;" placeholder="to date"></s:textfield>
		</div>
		<div class="form-group">
			<s:submit value="Go" theme="simple" cssClass="btn btn-primary"></s:submit>
			<a type="button" class="btn btn-primary"  title="Print" onclick="printpage()"><i class="fa fa-print"></i></a>
			<a type="button" id="btnxls" title="Save As XLS" onclick="printExcel()" class="btn btn-primary"><i class="fa fa-file-excel-o"></i></a>
		</div>
		
	</div>
	</div>
	
	</s:form>
	
	
	<table class="my-table xlstable" style="width: 100%">
				<tr>
					
					<th>Practitioner <a href="#" onclick="setSorting('apm_user.firstname','<s:property value="order"/>')"><i class="fa fa-sort"></i></a></th>
					<th>Patient Name</th>
					<th>Treatment Episode Start Date</th>
					<th>Location</th>
					<th>Condition <a href="#" onclick="setSorting('apm_condition.name','<s:property value="order"/>')"><i class="fa fa-sort"></i></a></th>
					<th>Source of Intro <a href="#" onclick="setSorting('apm_sourceofintro.sourceofintro','<s:property value="order"/>')"><i class="fa fa-sort"></i></a></th> 
					<th>Reffered By <a href="#" onclick="setSorting('reference.name','<s:property value="order"/>')"><i class="fa fa-sort"></i></a></th>
					
				
				</tr>
				
				
				<s:if test="returningPtsList.size!=0">
							<s:iterator value="returningPtsList" status="rowstatus">
								<tr>
								<td><s:property value="practitionerName"/></td>
								<td><s:property value="clientname"/></td>
								<td><s:property value="tmentstartdate"/></td>
								<td><s:property value="location"/></td>
								<td><s:property value="condition"/></td>
								<td><s:property value="sourceofintro"/></td>
								<td><s:property value="referal"/></td>
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

		
				
				
				
				
				
				
				
				
						
	