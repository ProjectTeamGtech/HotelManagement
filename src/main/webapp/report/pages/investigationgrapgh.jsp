<%@page import="com.apm.Emr.eu.entity.Investigation"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.apm.Emr.eu.blogic.jdbc.JDBCInvestigationDAO"%>
<%@page import="com.apm.Emr.eu.bi.InvestigationDAO"%>
<%@page import="com.apm.common.eu.blogic.jdbc.Connection_provider"%>
<%@page import="java.sql.Connection"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib uri="/struts-tags" prefix="s"%>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
    <!-- ============================================
    ================= Stylesheets ===================
    ============================================= -->
    <!-- vendor css files -->
   <link rel="stylesheet" href="common/BootstrapNew/bootstrap/css/bootstrap.min.css">
        <link rel="stylesheet" href="mis/assets/css/vendor/animate.css">
        <link rel="stylesheet" href="mis/assets/css/vendor/font-awesome.min.css">
        <link rel="stylesheet" href="mis/assets/js/vendor/animsition/css/animsition.min.css">
        
   


    <!-- project main css files -->
      <link rel="stylesheet" href="mis/assets/css/main.css">
    <!--/ stylesheets -->
    <!-- ==========================================
    ================= Modernizr ===================
    =========================================== -->
    <script src="mis/assets/js/vendor/modernizr/modernizr-2.8.3-respond-1.4.2.min.js"></script>
    <!--/ modernizr -->

</head>
<body>
<%String investigationType=""; %>
<h2 style="text-align: center;"><s:property value="invstgiven"/></h2>
<div>

<div style="padding: 15px">
<span>Patient Name : <s:property value="patient.fullname"/></span><br>
<span>UHID : <s:property value="patient.abrivationid"/></span>
</div>
<div  align="right" >
<input type="button" value="Print" onclick="printpage()" class='btn btn-primary hidden-print'>
</div>

</div>
<%int i=0; %>
<div class="container-fluid">
<div class="row">


<%ArrayList<Investigation> graphInvstNameList=(ArrayList<Investigation>)request.getAttribute("investlist");  %>
<%for(Investigation investigation : graphInvstNameList){ %>

<%i++;
investigationType=investigation.getInvsttype();

%>




<div class="col-lg-4 col-md-4 col-sm-4 col-xs-4">
	<div id="container<%=i %>" style="width:100%; height: 300px; margin: 0 auto"></div>
</div>


<% }%>

</div>

</div>
</body>



    <!--/ Application Content -->
    <!-- ============================================
    ============== Vendor JavaScripts ===============
    ============================================= -->
   
     <script src="mis/assets/js/vendor/jRespond/jRespond.min.js"></script>

        <script src="mis/assets/js/vendor/sparkline/jquery.sparkline.min.js"></script>

        <script src="mis/assets/js/vendor/slimscroll/jquery.slimscroll.min.js"></script>

        <script src="mis/assets/js/vendor/animsition/js/jquery.animsition.min.js"></script>

        <script src="mis/assets/js/vendor/screenfull/screenfull.min.js"></script>
       

        <script src="mis/assets/js/vendor/raphael/raphael-min.js"></script>
        <script src="mis/assets/js/vendor/d3/d3.v2.js"></script>
        <script src="mis/assets/js/vendor/d3/d3.min.js"></script>
        <script src="mis/assets/js/vendor/d3/d3.layout.min.js"></script>
        <script src="mis/assets/js/vendor/rickshaw/rickshaw.min.js"></script>

    	<%-- <script src="mis/assets/js/vendor/daterangepicker/moment.min.js"></script>
        <script src="mis/assets/js/vendor/daterangepicker/daterangepicker.js"></script>
        <script src="mis/assets/js/vendor/datetimepicker/js/bootstrap-datetimepicker.min.js"></script> --%>
        <script src="mis/assets/js/vendor/morris/morris.min.js"></script>

		 <script src="mis/assets/js/vendor/flot/jquery.flot.min.js"></script>
        <script src="mis/assets/js/vendor/flot-tooltip/jquery.flot.tooltip.min.js"></script>
        <script src="mis/assets/js/vendor/flot-spline/jquery.flot.spline.min.js"></script>

        <script src="mis/assets/js/vendor/easypiechart/jquery.easypiechart.min.js"></script>



        <script src="mis/assets/js/vendor/countTo/jquery.countTo.js"></script>
        <!--/ vendor javascripts -->





        <!-- ============================================
        ============== Custom JavaScripts ===============
        ============================================= -->
        <script src="mis/assets/js/main.js"></script>
        <!--/ custom javascripts -->



<script src="_assets/newtheme/js/vendor/hichart/highcharts.js"></script>
<script src="_assets/newtheme/js/vendor/hichart/exporting.js"></script>


    <!--/ vendor javascripts -->
    <!-- ============================================
    ============== Custom JavaScripts ===============
    ============================================= -->
    <script src="assets/js/main.js"></script>
    <script src="assets/js/vendor/hichart/highcharts.js"></script>
    <script src="assets/js/vendor/hichart/exporting.js"></script>
    <!--/ custom javascripts -->





<script>
	$(function () {
<%
int j=1;

Connection connection = Connection_provider.getconnection();
InvestigationDAO investigationDAO = new JDBCInvestigationDAO(connection);
String invstgraphclientid="0";
String fromdate="2010-01-22 23:59:59";
String todate=((String)request.getAttribute("todate"));
for(Investigation investigation : graphInvstNameList){
	invstgraphclientid=investigation.getClientId();
	
		String graphdatelist = investigationDAO.getGraphDateList(invstgraphclientid,investigation.getInvstname(),fromdate,todate);
		String graphValueList = investigationDAO.getGraphValueList(invstgraphclientid,investigation.getInvstname(),fromdate,todate);
		String invstTypeName = investigationDAO.getGraphinvstTypeName(invstgraphclientid,investigation.getInvstname(),fromdate,todate);

		%>
		var jj = <%=j%>
	    Highcharts.chart('container'+jj+'', {
	        chart: {
	            type: 'line'
	        },
	        title: {
	            text: ''
	        },
	        xAxis: {
	            categories: [<%=graphdatelist%>]
	        },
	        yAxis: {
	            title: {
	                text: 'Range'
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
	        series: [{
	            name: '<%=investigation.getInvstname()%>',
	            data: [<%=graphValueList%>]
	            
	        }]
	    });
	    <%j++;%>
	   <%}%>
	    
	});
	</script>	

</html>