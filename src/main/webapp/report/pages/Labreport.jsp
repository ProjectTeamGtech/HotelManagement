<%@taglib uri="/struts-tags" prefix="s"%>
<%@page import="java.util.Date"%>
<%@ page contentType="text/html; charset=utf-8" pageEncoding="UTF-8"%>

<%
	request.setCharacterEncoding("UTF-8");
	response.setCharacterEncoding("UTF-8");
%>
<script type="text/javascript" src="report/js/report.js"></script>
<script type="text/javascript" src="assesmentForms/js/jquery.table2excel.js"></script>

<script>

 function printExcel() {

       $(".xlstable").table2excel({
					exclude: ".noExl",
					name: "Lab report",
					filename: "labreport",
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

										<h4>Lab Report</h4>

									</div>
								</div>

<div class="row ">

<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12">
<s:form action="LabreportSummary" theme="simple" id = "invoicerportfrm">
<div class="col-lg-12 col-md-12 topback2 hidden-print">
<div class="form-inline">
			
			<div class="form-group" style="width:7%;">
			FROM DATE
			</div>
			<div class="form-group" style="width:7%;">
				<s:textfield readonly="true" name="fromDate" id="fromDate"
					cssClass="form-control" theme="simple" style="width:100%;" placeholder="from date"></s:textfield>
			</div>
			<div class="form-group" style="width:5%;">
			TO DATE	
			</div>
			<div class="form-group" style="width:7%;">
			<s:textfield readonly="true" name="toDate" id="toDate"
					cssClass="form-control" theme="simple" style="width:100%;" placeholder="to date"></s:textfield>
			</div>
			&nbsp;&nbsp;&nbsp;
			<div class="form-group"  style="width: 15%">
				<s:select name="testname"  style="width: 100%" list="inestigationnamelist" listKey="name" listValue="name"  multiple="" cssClass="form-control showToolTip chosen-select" headerKey="" headerValue=""  ></s:select>
			</div>
			&nbsp;&nbsp;&nbsp;
			<div class="form-group"  style="width: 10%">
				<s:select name="abovebelow"
				list="#{'>':'above','<':'below'}"
				cssClass="form-control chosen-select" ></s:select>
			</div>
			&nbsp;&nbsp;&nbsp;
			<div class="form-group">
				<s:textfield name="obsvalue"   cssClass="form-control " placeholder="Absolute value" >
				</s:textfield>
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

				<tr bgcolor="#4E7894" >
				<td style="color:#eee;width: 5%">Sr. No.</td>
				<td style="width: 20% ;color:#eee">Date</td>
				<td style="width: 20% ;color:#eee">UHID</td>
				<td style="color:#eee;width: 20%">Patient Name</td>
				
				<td style="color:#eee;width: 20%">Investigation Name</td>
				<td style="color:#eee">Observation value</td>
				
				
					
				</tr>
				<s:if test="investigationlist.size!=0">
				<s:iterator value="investigationlist">
				<tr>
				<td><%=i++ %></td>
				<td><s:property value="date"/></td>
				<td><s:property value="abrivationid"/></td>
				
				<td><s:property value="clientname"/></td>
			
				<td><s:property value="name"/></td>
				<td><s:property value="obsvalue"/></td>
			
				</tr>
				</s:iterator>
				</s:if>
				</table>
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


