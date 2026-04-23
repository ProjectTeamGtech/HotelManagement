
<%@page import="com.apm.common.web.common.helper.LoginHelper"%>
<%@page import="com.apm.common.web.common.helper.LoginInfo"%>
<%@page import="com.apm.main.common.constants.Constants"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<script type="text/javascript" src="common/js/pagination.js"></script>
<script type="text/javascript" src="report/js/report.js"></script>
<script type="text/javascript" src="assesmentForms/js/jquery.table2excel.js"></script>
<script type="text/javascript" src="common/js/pagination.js"></script>
<script type="text/javascript"
	src="diarymanagement/js/commonAppointmentView.js"></script>
	


 <script type="text/javascript" src="pharmacy/searchexport/jquery.dataTables.js"></script>
    <script type="text/javascript" src="pharmacy/searchexport/dataTables.bootstrap.js"></script>
<style>
.loktbl{
width: 100%;
padding: 4px;
border: 1px solid #266d71;
}

.loktbl th{
background-color: #a5dbde;
 padding: 4px;
 border: 1px solid #266d71;
 color: #114f52;
}
.loktbl td{
border: 1px solid #266d71;
    padding: 4px;
    color:#333;
    font-family:  'Open Sans', sans-serif !important;
    background: #ecfcff;
}
.xx{
margin-right: 10px;
}
</style>
<script src="popupdialog/dialog/js/jquery.ui.datepicker.js"></script>
<script>

function printExcel() {

    $(".xlstable").table2excel({
					exclude: ".noExl",
					name: "Birth Places Report",
					filename: "Birth Place Report",
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

	
	 var table = $('#sss').DataTable( {
	        lengthChange: false,
	        buttons: [ ]
	    } );
	 
	    table.buttons().container()
	        .appendTo( '#example_wrapper .col-sm-6:eq(0)' );
});
</script>


<div class='row' style="padding: 1px;">
<div class="manascommheader ">
	<h4>BirhPlace Report</h4>
</div>

<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12 " style="padding-top: 20px;">
<s:form action="birthplacereportSummary" theme="simple" id = "invoicerportfrm">
<div class="st hidden-print" style=" float: left;display: flex;width: 70% ">
	<s:textfield  name="search" cssClass="form-control xx" theme="simple" style="width:100%;background:#ecfcff" placeholder="Search By Name or UHID "></s:textfield>
	&nbsp;
	<s:select list="wardlist" listKey="wardid" listValue="wardname" headerKey="wardid" headerValue="wardname" cssClass="form-control chosen-select"></s:select>
	&nbsp;
	<s:select cssClass="form-control" list="#{'0':'Birth Wise Date', '1':'Admission Wise Date'}" name="orderby" />
	&nbsp;
	<s:textfield id="fromdate" name="fromDate" readonly="true" cssClass="form-control" placeholder="From Date" style="width:50%;"/>
	&nbsp;
	<s:textfield  name="toDate" id="todate" readonly="true"  cssClass="form-control" placeholder="To Date" style="width:50%;"/>	
	&nbsp;
	<s:submit value="Go" theme="simple" cssClass="btn btn-primary"></s:submit>
	
</div>

</s:form>
<div class="form-group hidden-print" style=" float: right;">
				
				<a type="button" class="btn btn-primary"  title="Print" onclick="printpage()"><i class="fa fa-print"></i></a>
			<a type="button" id="btnxls" title="Save As XLS" onclick="printExcel()" class="btn btn-primary"><i class="fa fa-file-excel-o"></i></a>
			<a href="#"  class="btn btn-primary" title="Refresh" onclick="setrefresh()"><i class="fa fa-refresh"></i></a>	
				</div>

<table class='loktbl xlstable' id='sss'>


	<tr>
	<th>Sr No</th>
	<th>Name</th>
	<th>Age/Gender</th>
	<th>DOB</th>
	<th>Mobile/Email</th>
	<th>Emergency Contact number</th>
	<th>Address</th>
	<th>UHID</th>
	<th>Father Name</th>
	<th>Birthplace</th>
	<th>IPD ID/ OPD Id</th>
	<th>Payee</th>
	<%int i=1; %>
	</tr>
	<s:iterator value="clientlist">
	<tr>
	<td><%=i++ %></td>
		<td><s:property value="fullname" /></td>
		<td><s:property value="age1" />/<s:property value="gender" /></td>
		<td><s:property value="dob" /></td>
		<td><s:property value="mobNo" />/<s:property value="email" /></td>
		<td><s:property value="emergencyContName" /></td>
		<td><s:property value="address" /></td>
		<td><s:property value="abrivationid" /></td>
		<td><s:property value="fathername" /></td>
		<td><s:property value="birthplace" /></td>
		<td><s:property value="ipdid" /></td>
		<td><s:property value="whopay" />  --<s:property value="tpName" /></td>
	</tr>
	</s:iterator>
</table>	

<div style="margin-top: 20px !important">
			<!-- pagination div -->
			<s:form action="birthplacereportSummary" name="paginationForm"
				id="paginationForm" theme="simple">
				<s:hidden name="search"></s:hidden>
				<s:hidden name="totalcount"></s:hidden>
				<s:hidden name="fromDate"></s:hidden>
				<s:hidden name="toDate"></s:hidden>
				<s:hidden name="orderby"></s:hidden>
				
				<div class="col-lg-12 col-md-12" style="padding: 0px;">
					<div class="col-lg-4 col-md-4 text-left" style="padding: 0px;">
						Total:<label class="text-info"><s:property
								value="totalComplete" /></label>
					</div>
					<%@ include file="/common/pages/pagination.jsp"%>


				</div>
			</s:form>

		</div>
</div>

</div>
