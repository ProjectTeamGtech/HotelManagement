<%@taglib uri="/struts-tags" prefix="s"%>

<%@page import="java.util.ArrayList"%>
<%@page import="com.apm.Emr.eu.entity.Investigation"%>
<%@page import="java.sql.Connection"%>
<%@page import="com.apm.common.eu.blogic.jdbc.Connection_provider"%>
<%@page import="com.apm.Emr.eu.bi.InvestigationDAO"%>
<%@page import="com.apm.Emr.eu.blogic.jdbc.JDBCInvestigationDAO"%>
<%@page import="java.util.Date"%>
<script type="text/javascript" src="report/js/report.js"></script>
<script type="text/javascript" src="assesmentForms/js/jquery.table2excel.js"></script>
<script>
 function printExcel() {

       $(".xlstable").table2excel({
					exclude: ".noExl",
					name: "Analytical Report",
					filename: "analytical",
					fileext: ".xls",
					exclude_img: true,
					exclude_links: true,
					exclude_inputs: true
				});
   }
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
<h5><s:property value="investtemp1" />	</h5>
		<div class="row details">
									<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12">

										<h4>Analytics Report</h4>

									</div>

								</div>
<br>
								<s:hidden name="order" id="order"/>
<s:hidden name="orderby" id="orderby"/>

	<div class="col-lg-12 col-md-12 topback2 hidden-print">
		<label><s:property value="clientname"/></label>
		<div class="form-inline">
			<div class="form-group">
			<div class="form-group">
			<a type="button" class="btn btn-primary"  title="Print" onclick="printpage()"><i class="fa fa-print"></i></a>
			<a type="button" id="btnxls" title="Save As XLS" onclick="printExcel()" class="btn btn-primary"><i class="fa fa-file-excel-o"></i></a>
			
								</div>
								</div>
								</div></div>
								<br>
								<div>
								 
<%ArrayList<Investigation>graphInvstNameList1 = (ArrayList<Investigation>)session.getAttribute("graphInvstNameList"); %>
<table class="my-table xlstable" style="width: 100%">
<% 
String invstgraphclientid = (String)session.getAttribute("invstgraphclientid");
	String fromdate = (String)session.getAttribute("invstgraphfromdate");
	String todate = (String)session.getAttribute("invstgraphtodate");
	int i=1;
	Connection connection =null;
	for(Investigation investigation : graphInvstNameList1){
		String name= investigation.getInvstname();
		
		 connection = Connection_provider.getconnection();
		InvestigationDAO investigationDAO = new JDBCInvestigationDAO(connection);
		String graphdatelist = investigationDAO.getGraphDateList(invstgraphclientid,investigation.getInvstname(),fromdate,todate);
		String graphValueList = investigationDAO.getGraphValueList(invstgraphclientid,investigation.getInvstname(),fromdate,todate);
		String invstTypeName = investigationDAO.getGraphinvstTypeName(invstgraphclientid,investigation.getInvstname(),fromdate,todate);
		String date[]= graphdatelist.split(",");
		String values[]= graphValueList.split(",");
		int x= date.length;
		//out.print(x);
		if(i==1){
		
			out.print("<tr><div><h3>"+invstTypeName+"</h3><div></tr>");
		out.print("<tr bgcolor='#283747' style='color: white'><td ></td> ");
		
		for(int c=0;c<x;c++){
			date[c]= date[c].replace("'", "");
		out.print("<td>"+date[c]+"</td> ");
		}
		out.print("</tr>");
	}
		++i;
		out.print("<tr><td> "+name+"</td>");
		for(int c=0;c<x;c++){
			values[c]= values[c].replace("'", "");
		out.print("<td><strong>"+values[c]+"</strong></td> ");
		}
		//out.print("<td>"+invstTypeName+"</td></tr> ");
		
	}%>
	 </table></div> 
								
								
								
								</div></div>