<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<SCRIPT type="text/javascript">
	function printReport() {
		$("#tablesort").table2excel({
			exclude : ".noExl",
			name : "Staff Shedule Report",
			filename : "Staff_Shedule_Report",
			fileext : ".xls",
			exclude_img : true,
			exclude_links : true,
			exclude_inputs : true
		});
	}
</SCRIPT>
<style>
.loc {
	background-color: #6699cc;
	color: white;
}

@media print {
	.loc {
		background-color: #6699cc !important;
		color: white;
	}
}
</style>

<style>
b, strong {
	font-weight: 900;
}

.topback2 {
	background-color: #f5f5f5;
	padding: 10px 0px 10px 15px;
}
</style>
<script type="text/javascript" src="common/js/pagination.js"></script>
<script type="text/javascript"
	src="assesmentForms/js/jquery.table2excel.js">
	
</script>

<script>
window.onload = function () {
 
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

};
</script>
</head>
<body>

	<%-- <% double[] usedead= (double[]) session.getAttribute("usedead"); %> --%>


	<div class="row">
		<div class="col-lg-12 col-md-12 col-xs-12">

			<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12"
				style="padding: 0px;">

				<div class="col-lg-12 col-md-12 paddingnil">
					<div class="col-lg-12 col-md-12 topback2">
						<s:form cssClass="form-inline search" theme="simple"
							action="getstaffsheduleprintdataNotAvailableSlot" method="post">
							<div class="form-group">
								<span class="text-uppercase"><b>Staff Shedule Report</b>
									&nbsp; - &nbsp;</span>
							</div>
							<div class="form-group hidden-print" style="width: 12%;">
								 <s:textfield id="fromdate" readonly="true" name="fromDate" cssClass="form-control" placeholder="From Date" />
							</div>
							
							<div class="form-group hidden-print" style="width: 12%;">
								 <s:textfield id="todate" readonly="true" name="toDate" cssClass="form-control" placeholder="To Date" />
							</div>
							
							<div class="form-group hidden-print" style="width: 12%;">
								<button type="submit" class="btn btn-primary">Go</button>
								<a type="button" id="btnxls" title="Save As XLS"
									onclick="printReport()" class="btn btn-primary btnxls"><i
									class="fa fa-file-excel-o"></i></a>
									 <a href="#"
									class="btn btn-warning" onclick="printpage()">Print</a>
							</div>

							<div class="form-group hidden-print">
								
							</div>
						</s:form>
					</div>
				</div>
			</div>

			<div class="col-lg-12 col-md-12 col-xs-12">
				<table class="table table-bordered tablesort" id="tablesort"
					cellspacing="0" width="100%">
					<thead>
						<tr class="loc">
							<td style="width: 3%;">Sr.no</td>
							<td style="width: 15%;">Staff Name</td>
							<td style="width: 15%;">Date</td>
							<td style="width: 5%;">Start Time</td>
							<td style="width: 5%;">End Time</td>
							<td style="width: 10%;">Duration</td>
							<td style="width: 30%;">Task</td>
						</tr>
					</thead>
					<tbody>
						<%
							int i = 0;
						%>
						<s:iterator value="shedulestaffreportlist">
							<tr>
								<td><%=i + 1%></td>
								<td><s:property value="diaryUser" /></td>
								<td><s:property value="commencing" />[<s:property value="weekday" />]</td>
								<td><s:property value="sTime" /></td>
								<td><s:property value="endTime" /></td>
								<td><s:property value="duration" /></td>
								<td><s:property value="notes" /></td>
							</tr>
							<%
								i++;
							%>
						</s:iterator>

					</tbody>

				</table>


			</div>
			
		</div>
	</div>



</body>
</html>