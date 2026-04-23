<%@taglib uri="/struts-tags" prefix="s"%>
<%@page import="java.util.Date"%>
<%@page import="com.apm.common.web.common.helper.LoginInfo"%>
<%@page import="com.apm.common.web.common.helper.LoginHelper"%>

<style>
.colo{
background-color: #3c6ea0 !important;
text-align: center;
color: white;
}

.upper{
padding: 20px;
}
.my-table td {
	border: 3px solid #c7bdb7 !important;
	padding: 5px !important;
	font-size: 11px !important;
}
i{
padding-left: 12px;
}
</style>
<%LoginInfo loginInfo= LoginHelper.getLoginInfo(request); %>
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
					filename: "newgst",
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
<div class="row details colo">
				<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12">
				<h4>Released Notes </h4>
				</div>
</div>

<div class="col-lg-6 col-md-6 col-xs-6 col-sm-6 upper">
<s:form action="all_relesed_notesCommonnew" theme="simple" id = "all_relesed_notesCommonnew">
		<div class="form-inline">
			<div class="form-group" >
			<label>From Date :</label>
				<s:textfield readonly="true" name="fromdate" id="fromDate"
					cssClass="form-control" theme="simple" style="width:40%;" placeholder="from date" cssStyle="border:2px solid #3c6ea0 "></s:textfield>
			</div>
			
			<div class="form-group" >
			<label>To Date :</label>
				<s:textfield readonly="true" name="todate" id="toDate" cssStyle="border:2px solid #3c6ea0 "
					cssClass="form-control" theme="simple" style="width:40%;" placeholder="to date" ></s:textfield>
			</div>
			<s:submit value="Go" theme="simple" cssClass="btn btn-primary"></s:submit>
		</div>	
</s:form>
</div>
<%if(loginInfo.isRelease_notes_upload()){ %>
<div class="col-lg-6 col-md-6 col-xs-6 col-sm-6 upper">
<form method="post" action="saverelesed_notesCommonnew" id='form<s:property value='id' />'  enctype='multipart/form-data'>
								<s:hidden name="subuploadfilesContentType"></s:hidden>
								<s:hidden name="subuploadfilesFileName"></s:hidden>
								<p class='hidden' id='ppp<s:property value='id' />'></p>
								<div class="form-inline">
								<div class="form-group" >
								<s:file accept=".pdf"  cssStyle="border:2px solid #3c6ea0 " cssClass="form-control   " theme="simple" name="subuploadfiles" id="filesub_uploadfiles" style='padding-right:20px'></s:file>
								</div>
								<div class="form-group" >
								<s:textfield name="warname" id="" cssStyle="border:2px solid #3c6ea0 " cssClass="form-control" theme="simple"  placeholder="WAR Release" required="true"></s:textfield>
								</div>
								<div class="form-group" >
								<input type="submit" class='btn btn-primary ' value="Upload Document" >
								</div>
								</div>
</form>
</div>

<%} %>


<s:if test="releaseList.size!=0">
<table class="my-table xlstable" style="width:100%">
	<tr class='colo'>
	<td>Sr.No   				<a > <i class="fa fa-angle-double-down fa-2x"></i> </a></td>
	<td>Date Time				 <a ><i class="fa fa-calendar fa-2x"></i></a></td>
	<td>Document Download/View   <a ><i class="fa fa-download fa-2x"></i></a></td>
	<td>War  						<a ><i class="fa fa-folder fa-2x"></i></a></td>
	</tr>
	<%int i=1; %>
	<s:iterator value="releaseList">
	<tr style="text-align: center;">
	<td><%=i%><%i++; %></td>
	<td><s:property value="datetime"/><i class="fa fa-calendar fa-1x"></i></td>
	<td><%-- <s:property value="filename"/> --%><a  onclick="opencPopup('liveData/release_notes/<s:property value="filename"/>')"><i class="fa fa-file fa-2x"></i></a></td>
	<td><s:property value="warname"/></td>
	</tr>
	</s:iterator>
</table>
</s:if>
<s:else><div class='colo' style="margin-top: 90px;"><h3>No Results Found!</h3></div></s:else>
</div>