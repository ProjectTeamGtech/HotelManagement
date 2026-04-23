<%@page import="com.apm.Report.eu.entity.MisReport"%>
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
					filename: "nelsonevenue",
					fileext: ".xls",
					exclude_img: true,
					exclude_links: true,
					exclude_inputs: true
				});
}


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

										<h4>Nelson Revenue Report</h4>

									</div>

</div>
<form action="totalrevenueSummary" method="post">
<input type="hidden" name='isnelson' value='1'>
<input type="hidden" name='yearly' value='1'>
	<div class="form-inline hidden-print" style="height: 50px;padding: 10px;">
					<%-- <div class="form-group" style="width:10%;">		
						 <s:select style="width:100%;" cssClass="form-control chosen-select" list="#{'01':'January', '02':'February', '03':'March', '04':'April', '05':'May','06':'June', '07':'July', '08':'August', '09':'September', '10':'October', '11':'November', '12':'December'}" name="fromDate" />
					</div>	 --%>			
	
					<div class="form-group" style="width:10%;">
						<s:select style="width:100%;" cssClass="form-control chosen-select" list="#{'2014':'2014','2015':'2015','2016':'2016','2017':'2017', '2018':'2018', '2019':'2019', '2020':'2020', '2021':'2021','2022':'2022', '2023':'2023', '2024':'2024', '2025':'2025', '2026':'2026', '2027':'2027', '2028':'2028'}" name="toDate" />
					</div>
					<div class="form-group" style="width:20%;">		
						<s:submit value="Go" theme="simple" cssClass="btn btn-primary"></s:submit>
						<a type="button" class="btn btn-primary"  title="Print" onclick="printpage()"><i class="fa fa-print"></i></a>
						<a type="button" id="btnxls" title="Save As XLS" onclick="printExcel()" class="btn btn-primary"><i class="fa fa-file-excel-o"></i></a>
					</div>
	</div>
</form>

<div class="col-lg-6 col-md-6 col-xs-6 col-sm-6">
<table class="my-table xlstable" style="width:100%;border: 1px solid #DFD8D4 !important;" >
	<tr bgcolor="#3c6ea0" style="color:white;">
		<td >Sr. No.</td>
		<td style="width: ">Month</td>
		<td  style="width: ">IPD Revenue</td>
		<td  style="width: ">OPD Revenue</td>
		<td  style="width: ">Investigation Revenue</td>
		<td  style="width: ">Other Revenue</td>
		<td  style="width: ">Total Revenue</td>
	</tr>
	<%int i=1; %>
	<s:iterator value="totalrevenuelist">
		<tr>
			<td><%=i %><%i++; %></td>
			<td><s:if test="mnth=='01'">Jan</s:if>
			<s:elseif test="mnth=='02'">Feb</s:elseif>
			<s:elseif test="mnth=='03'">Mar</s:elseif>
			<s:elseif test="mnth=='04'">Apr</s:elseif>
			<s:elseif test="mnth=='05'">May</s:elseif>
			<s:elseif test="mnth=='06'">Jun</s:elseif>
			<s:elseif test="mnth=='07'">Jul</s:elseif>
			<s:elseif test="mnth=='08'">Aug</s:elseif>
			<s:elseif test="mnth=='09'">Sep</s:elseif>
			<s:elseif test="mnth=='10'">Oct</s:elseif>
			<s:elseif test="mnth=='11'">Nov</s:elseif>
			<s:elseif test="mnth=='12'">Dec</s:elseif>
			'<s:property value="yr"/>
			</td>
			<td><s:property value="ipddebitcount"/></td>
			<td><s:property value="opddebitcount"/></td>
			<td><s:property value="Investigationdebitcount"/></td>
			<td><s:property value="Medicinedebitcount"/></td>
			<td><s:property value="allttl"/></td>
		
		</tr>
	</s:iterator>
</table>
</div>

<div class="col-lg-6 col-md-6 col-xs-6 col-sm-6">
	<div id="indicator"></div>
</div>
</div>
<script src="_assets/newtheme/js/vendor/hichart/highcharts.js"></script>
		<script src="_assets/newtheme/js/vendor/hichart/exporting.js"></script>

		<script src="mis/kpiplugin/js/jquery.scrollto.js"></script>   

<%
	MisReport misReport = (MisReport)session.getAttribute("graphicalopdrevenuereportt");
%>
<script id="kipqiscript">
	Highcharts.chart('indicator', {
	chart: {
			type: 'line'
			},
    title: {
	        text: 'OPD Revenue Report'
	    },
	    xAxis: {
	        categories: ['Apr', 'May', 'Jun', 'Jul', 'Aug', 'Sep', 'Oct', 'Nov', 'Dec', 'Jan', 'Feb', 'Mar', ]
	    },
	    yAxis: {
	        title: {
	            text: ''
	        }
	    },
	    plotOptions: {
	        line: {
	            dataLabels: {
	                enabled: true
	            },
	            enableMouseTracking: true
	        }
	    },
	    series: [<%-- {
	        name: 'Result',
	        data: [<%=misReport.getMonth4()%>,<%=misReport.getMonth5()%>, <%=misReport.getMonth6()%>, <%=misReport.getMonth7()%>, <%=misReport.getMonth8()%>, <%=misReport.getMonth9()%>, <%=misReport.getMonth10()%>, <%=misReport.getMonth11()%>, <%=misReport.getMonth12()%>, <%=misReport.getMonth1()%>, <%=misReport.getMonth2()%>, <%=misReport.getMonth3()%>]
	    }, --%>
	    {
	        name: 'Target',
	        data: [<%=misReport.getMonth4_target()%>,<%=misReport.getMonth5_target()%>, <%=misReport.getMonth6_target()%>, <%=misReport.getMonth7_target()%>, <%=misReport.getMonth8_target()%>, <%=misReport.getMonth9_target()%>, <%=misReport.getMonth10_target()%>, <%=misReport.getMonth11_target()%>, <%=misReport.getMonth12_target()%>, <%=misReport.getMonth1_target()%>, <%=misReport.getMonth2_target()%>, <%=misReport.getMonth3_target()%>]
	    }]
	});
</script>
