<%@taglib uri="/struts-tags" prefix="s"%>
<%@page import="java.util.Date"%>
<script type="text/javascript" src="report/js/report.js"></script>
<script type="text/javascript" src="assesmentForms/js/jquery.table2excel.js"></script>


<script>

 function printExcel() {

       $(".xlstable").table2excel({
					exclude: ".noExl",
					name: "showpatients",
					filename: "showpatients",
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
<script>

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

										<h4>Patients</h4>

									</div>
								</div>
								<div class="row ">
									<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12">
										
<s:form action="showpatientsreportSummary" theme="simple" id = "invoicerportfrm">
<s:hidden name="order" id="order"/>
<s:hidden name="orderby" id="orderby"/>
	<div class="col-lg-12 col-md-12 topback2 hidden-print">
		
		<div class="form-inline">
			<div class="form-group">
							<div class="form-group">
				<s:select name="type" id="type" 
				list="#{'IPD':'IPD','OPD':'OPD'}"
				cssClass="form-control chosen-select" ></s:select>
			</div>
			<div class="form-group">
				<s:select name="dept" list="deptlist" listKey="discipline_id" listValue="discipline_name" cssClass="form-control chosen-select" headerKey="0" headerValue="All Depts" theme="simple" ></s:select>
			</div>
			
			<div class="form-group">
				<s:select name="city" list="locationlist" listKey="city" listValue="city" cssClass="form-control chosen-select" headerKey="0" headerValue="All Location" theme="simple" ></s:select>
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
	<br>
			<P><b>Count :<s:property value="patientlist.size()"/></b></P>
	</div>
	
	</s:form>
	
	<%int i=1; %>
	<table class="my-table xlstable" style="width: 100%" onload="count()">
				<tr><th>Sr. No.</th>
					<th>Patient Name</th>
					<th>Age</th>
							<th>Department</th>
					<th>Address</th>
					<th>City</th>
					
					<th>State</th>
					
					
				
				</tr>
				
				
				<s:if test="patientlist.size!=0">
							<s:iterator value="patientlist" status="rowstatus">
								<tr>
								<td><%=i++ %></td>
									<td><s:property value="patientName"/></td>
								
								
								<td><s:property value="age1"/></td>
									<td><s:property value="department"/></td>
								<td><s:property value="address"/></td>
								
							
								<td><s:property value="city"/></td>
								<td><s:property value="state"/></td>
								
							
								</tr>
							</s:iterator>
							
						</s:if>
						
						<s:else>
					No Record Found!! 
				</s:else>
				
</table><br>
										
										
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


