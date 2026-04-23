<!doctype html>
<!--[if lt IE 7]>      <html class="no-js lt-ie9 lt-ie8 lt-ie7" lang=""> <![endif]-->
<!--[if IE 7]>         <html class="no-js lt-ie9 lt-ie8" lang=""> <![endif]-->
<!--[if IE 8]>         <html class="no-js lt-ie9" lang=""> <![endif]-->
<!--[if gt IE 8]><!-->
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@page import="java.util.ArrayList"%>
<%@page import="com.apm.Emr.eu.entity.Investigation"%>
<%@page import="java.sql.Connection"%>
<%@page import="com.apm.common.eu.blogic.jdbc.Connection_provider"%>
<%@page import="com.apm.Emr.eu.bi.InvestigationDAO"%>
<%@page import="com.apm.Emr.eu.blogic.jdbc.JDBCInvestigationDAO"%>
<html class="no-js" lang="">
<!--<![endif]-->



<head>

    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <title>YUVICARE</title>
    <link rel="icon" type="image/ico" href="assets/images/favicon.ico" />
    <meta name="description" content="">
    <meta name="viewport" content="width=device-width, initial-scale=1">




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

<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12">
	<center><h2>Investigation Chart</h2></center><br>
	<label><s:property value="clientname"/></label>
</div>

<%int i=0; %>
<%ArrayList<Investigation>graphInvstNameList = (ArrayList<Investigation>)session.getAttribute("graphInvstNameList"); %>
<%for(Investigation investigation : graphInvstNameList){ %>
<div class="container-fluid">
<div class="row">
<%i++; %>
<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12" style="margin-top:15px;">



<div class="col-lg-4 col-md-4 col-sm-4 col-xs-4">
	<div id="container<%=i %>" style="width:100%; height: 300px; margin: 0 auto"></div>
</div>
<%i++; %>
<div class="col-lg-4 col-md-4 col-sm-4 col-xs-4">
	<div id="container<%=i %>" style="width:100%; height: 300px; margin: 0 auto"></div>
</div>

<%i++; %>
<div class="col-lg-4 col-md-4 col-sm-4 col-xs-4">
	<div id="container<%=i %>" style="width:100%; height: 300px; margin: 0 auto"></div>
</div>

</div>

</div>
</div>
<% }%>

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
	<%int j=1;
	String invstgraphclientid = (String)session.getAttribute("invstgraphclientid");
	String fromdate = (String)session.getAttribute("invstgraphfromdate");
	String todate = (String)session.getAttribute("invstgraphtodate");
	%>
	<%for(Investigation investigation : graphInvstNameList){
		Connection connection = Connection_provider.getconnection();
		InvestigationDAO investigationDAO = new JDBCInvestigationDAO(connection);
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
                enableMouseTracking: false
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