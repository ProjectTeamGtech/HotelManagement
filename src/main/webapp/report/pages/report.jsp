<%@taglib uri="/struts-tags" prefix="s"%>
<script type="text/javascript" src="report/js/report.js"></script>
<script>
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


 <div class="row">
	<div class="col-lg-12">
		<ol class="breadcrumb">
			<li><a href="#">Repo</a></li>
			<li class="active">Report</li>
		</ol>
	</div>
</div>  
<s:form action="setColumnsReport" id = "report_frm" theme="simple">
<div class="row">
		
		<div class="col-lg-2 col-md-2">
			<label>Select From Date:</label>
			<s:textfield readonly="true" name="fromDate" id="fromDate"
				cssClass="form-control" theme="simple"></s:textfield>
		</div>
		<div class="col-lg-2 col-md-2">
			<label>Select To Date:</label>
			<s:textfield readonly="true" name="toDate" id="toDate"
				cssClass="form-control" theme="simple"></s:textfield>
		</div>
		<div class="col-lg-5 col-md-5">
			<label>Select Report:</label>
			<%-- <s:select name="report" headerKey="0" headerValue="Select Report"
				list="#{'1':'Practioner Commission','2':'AGID Invoice','3':'Appmt List'}"
				cssClass="form-control" onchange="showColumns()"> </s:select> --%>
				<s:if test="%{#reportList != 'null'}">
									<s:select id="report" name="report" list="reportList"
										headerKey="0" headerValue="Select Report"
										labelposition="left" title="Select Report"
										cssClass="form-control showToolTip" data-toggle="tooltip" onchange="showColumns()" />
								</s:if>	
		</div>
		
</div>
</s:form>	
<hr>
<div class="row">
<div class="col-lg-11 col-md-11">

<div class="col-lg-1 col-md-1">
</div>
<div class="col-lg-3 col-md-3">
<label>Add/Remove Field For Report</label>
</div>

<div class="col-lg-1 col-md-1">
</div>
<div class="col-lg-4 col-md-4">
<label>Field Available to be added to Report</label>
</div>
</div>
</div>
<div class="row">

<div class="col-lg-10 col-md-10">
<div class="col-lg-1 col-md-1" style="padding-left: 56px">
<br><br><br><br><br><br>

<a class="previous btn btn-primary" onclick="listbox_move('list1','up')"><i class="fa fa-level-up"></i></a> <br> <br>
<a class="previous btn btn-primary" onclick="listbox_move('list1','down')"><i class="fa fa-level-down"></i></a> <br> <br>
</div>
<div class="col-lg-3 col-md-3">
  <select size="20" style="width:150px;" multiple="multiple" class="form-control" id = "list1">
 <option value="Practioner Name">Practioner Name</option>
 <option value="Date">Date</option>
 <option value="Location">Location</option>
 
 
</select> 

<%-- <s:if test="%{#reportList != 'null'}">
	<s:select id="list1" name="columnOfList1" list="practionerCommList"
		headerKey="0" headerValue="Select Report"
		labelposition="left" title="Select Report" size="20" cssStyle="width:150px;" multiple="multiple"
		cssClass="form-control showToolTip" data-toggle="tooltip" onchange="showColumns()" />
</s:if> --%>	
</div>

<div class="col-lg-2 col-md-2">
<br><br><br><br><br><br>

<a class="previous btn btn-primary" style="width: 79px;" onclick="addItem()"><i class="fa fa-reply"></i> Add</a> <br> <br>
<a class="next btn btn-primary" onclick="removeItem()"><i class="fa fa-share"></i> Remove</a>
</div> 

<div class="col-lg-3 col-md-3">
 <select size="20" style="width:150px;" class="form-control" id = "list2" multiple="multiple">
 <option value="Commission Total">Commission Total</option>
 <option value="Total">Total</option>
 <option value="Rate">Rate</option>

</select> 
<%-- <s:select id="list2" size="20" style="width:150px;" multiple="multiple" name="list2" list="list2" title="Select" theme="simple" cssClass="form-control showToolTip" data-toggle="tooltip" listKey="columnname" listValue="columnname"/>
 --%>
</div>
</div>
</div>
<hr>
<div class="row">
<div class="col-lg-2 col-md-2">

	<input type="button" value="View Report" class="btn btn-primary">
	</div>
</div>






	