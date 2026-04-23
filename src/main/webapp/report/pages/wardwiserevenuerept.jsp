<%@taglib uri="/struts-tags" prefix="s"%>
<%@page import="java.util.Date"%>
<%@page import="com.apm.common.web.common.helper.LoginInfo"%>
<%@page import="com.apm.common.web.common.helper.LoginHelper"%>

<script type="text/javascript" src="report/js/report.js"></script>
<script type="text/javascript" src="assesmentForms/js/jquery.table2excel.js"></script>

<script type="text/javascript" src="accounts/js/commonaddcharge.js"></script> 
<script type="text/javascript" src="diarymanagement/js/addpriscription.js"></script>
<script type="text/javascript" src="emr/js/addInvestigation.js"></script>  
<script type="text/javascript" src="common/js/pagination.js"></script>
<script type="text/javascript" src="pharmacy/js/pharmacy.js"></script>  
<script type="text/javascript" src="assesmentForms/js/jquery.table2excel.js"> </script>

<script>
function printExcel() {

    $(".xlstable").table2excel({
					exclude: ".noExl",
					name: "report",
					filename: "wardwiserevenue",
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
});
</script>
<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12">

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

										<h4>Ward Wise Revenue Report</h4>

									</div>

</div>
<form action="wardwiserevenuereptSummary" method="post">
	<div class="form-inline hidden-print" style="height: 50px;padding: 10px;">
					<%-- <div class="form-group" style="width:10%;">		
						 <s:select style="width:100%;" cssClass="form-control chosen-select" list="#{'01':'January', '02':'February', '03':'March', '04':'April', '05':'May','06':'June', '07':'July', '08':'August', '09':'September', '10':'October', '11':'November', '12':'December'}" name="fromDate" />
					</div>				
	
					<div class="form-group" style="width:10%;">
						<s:select style="width:100%;" cssClass="form-control chosen-select" list="#{'2014':'2014','2015':'2015','2016':'2016','2017':'2017', '2018':'2018', '2019':'2019', '2020':'2020', '2021':'2021','2022':'2022', '2023':'2023', '2024':'2024', '2025':'2025', '2026':'2026', '2027':'2027', '2028':'2028'}" name="toDate" />
					</div> --%>
					<div class="form-group" style="width:7%;">
				                <s:textfield readonly="true" name="fromDate" id="fromDate"
				                     cssClass="form-control" theme="simple" placeholder="from date" style="width:100%;"></s:textfield>
			         </div>
			          <div class="form-group" style="width:7%;">
			                       	<s:textfield readonly="true" name="toDate" id="toDate"
				                       cssClass="form-control" theme="simple" palceholder="to date" style="width:100%;"></s:textfield>
			          </div>
					<div class="form-group" style="width:20%;">		
						<s:submit value="Go" theme="simple" cssClass="btn btn-primary"></s:submit>
						<a type="button" class="btn btn-primary"  title="Print" onclick="printpage()"><i class="fa fa-print"></i></a>
						<a type="button" id="btnxls" title="Save As XLS" onclick="printExcel()" class="btn btn-primary"><i class="fa fa-file-excel-o"></i></a>
					</div>
	</div>
</form>


<table class="my-table xlstable" style="width:100%">
<tr bgcolor="#3c6ea0" style="color:white;">
<td >Sr. No.</td>
<td style="width: 30%">Ward Name</td>
<td  style="width: 30%">Ward Revenue</td>
<td  style="width: 30%">Ward Patients (As per charges applied)</td>
</tr>
<%int i=1; %>
<s:iterator value="reportlist">
<tr>
<td><%=i %><%i++; %></td>
<td><s:property value="wardname"/></td>
<td><s:property value="ipdamount"/></td>
<td><s:property value="count"/></td>
</tr>
</s:iterator>
</table>
</div>