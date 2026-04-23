<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="com.apm.common.utils.DateTimeUtils"%>
<%@page import="com.apm.Accounts.eu.blogic.jdbc.JDBCAccountsDAO"%>
<%@page import="com.apm.Accounts.eu.bi.AccountsDAO"%>
<%@page import="com.apm.Registration.eu.blogic.jdbc.JDBCClinicDAO"%>
<%@page import="com.apm.Registration.eu.bi.ClinicDAO"%>
<%@page import="com.apm.Registration.eu.entity.Clinic"%>
<%@page import="com.apm.Emr.web.form.EmrForm"%>
<%@page import="com.apm.Emr.eu.entity.Investigation"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.apm.Emr.eu.blogic.jdbc.JDBCInvestigationDAO"%>
<%@page import="com.apm.Emr.eu.bi.InvestigationDAO"%>
<%@page import="com.apm.common.eu.blogic.jdbc.Connection_provider"%>
<%@page import="java.util.Calendar"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.text.DateFormat"%>
<%@page import="java.sql.Connection"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<%@page import="java.util.Date"%>
<%@ page contentType="text/html; charset=utf-8" pageEncoding="UTF-8"%>

<%
	request.setCharacterEncoding("UTF-8");
	response.setCharacterEncoding("UTF-8");
%>
<script type="text/javascript" src="report/js/report.js"></script>
<script type="text/javascript" src="assesmentForms/js/jquery.table2excel.js"></script>

<script type="text/javascript" src="accounts/js/commonaddcharge.js"></script> 
<script type="text/javascript" src="diarymanagement/js/addpriscription.js"></script>
<script type="text/javascript" src="emr/js/addInvestigation.js"></script>  
<script type="text/javascript" src="common/js/pagination.js"></script>
<script type="text/javascript" src="pharmacy/js/pharmacy.js"></script>  
<script type="text/javascript" src="assesmentForms/js/jquery.table2excel.js"> </script>
<script type="text/javascript" src="ipd/js/addcharges.js"> </script>
<script type="text/javascript" src="emr/js/emr.js"></script>
<script>

 function printExcel() {

       $(".xlstable").table2excel({
					exclude: ".noExl",
					name: "invstcountrpt",
					filename: "invstcountrpt",
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

<%
Connection connection= null;
EmrForm emrForm = new EmrForm();
LoginInfo loginInfo = LoginHelper.getLoginInfo(request);
String fromdate="";
String todate="";
String test=null;
if("".equals(test)){
	test="";
}
fromdate= request.getParameter("fromdate");
todate= request.getParameter("todate");
if(fromdate==null){
	fromdate="";
}
if(todate==null){
	todate="";
}
if(fromdate.equals("")){
	 DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
	    Calendar cal = Calendar.getInstance(); 
	    fromdate = dateFormat.format(cal.getTime());
}
if(todate.equals("")){
	 DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
	    Calendar cal = Calendar.getInstance(); 
	    todate = dateFormat.format(cal.getTime());
}
String fromdate1= DateTimeUtils.getCommencingDate1(fromdate);
String todate1= DateTimeUtils.getCommencingDate1(todate);
try{
	connection= Connection_provider.getconnection();
	InvestigationDAO investigationDAO= new JDBCInvestigationDAO(connection);
	
	String sql="";
	 sql= "select invsttypeid,apm_investigation_type.name,count(*),sum(compstatus),sum(upstatus),sum(status) from apm_client_parent_investigation ";
	 sql=sql+"inner join apm_investigation_type on apm_investigation_type.id= apm_client_parent_investigation.invsttypeid ";
	 sql=sql+"where apm_client_parent_investigation.lastmodified between '"+ fromdate1+"' and '"+todate1+"' ";
	 sql=sql+"group by invsttypeid order by (invsttypeid+0)";
	 System.out.println(sql);
	 System.out.println(fromdate1);
	 System.out.println(todate1);
	 int i = 0;
	 
	 String investtypeid="";
	 String investname="";
	 PreparedStatement ps=null;
	 ps= connection.prepareStatement(sql);
	 ResultSet rs=ps.executeQuery();
			 
%>


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

										<h4>Investigation Wise Count Report</h4>

									</div>

</div>	
<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12">
<s:form action="investigationtypewisereportnewInvestigation" theme="simple" id = "invoicerportfrm">

	<div class="col-lg-12 col-md-12 topback2 hidden-print">
		
		<div class="form-inline">
			<div class="form-group">
				
			<%-- <div class="form-group">
				<s:select name="practitionerName" list="userList" listKey="diaryUser" listValue="diaryUser" cssClass="form-control chosen-select" headerKey="" headerValue="All Practitioner" theme="simple" onchange = "setActionForAll()" ></s:select>
			</div> --%>
			
			
			
			<div class="form-group" style="width:27%;">
					<input type="text" class="form-control" readonly="readonly"   id="fromDate" name="fromdate" value="<%=fromdate %>" style="width: 95%;text-align: center;">
			</div>
			<div class="form-group" style="width:24%;">
				<%-- <s:textfield readonly="true" name="todate" id="toDate"
					cssClass="form-control" theme="simple" style="width:100%;" placeholder=" Select To Date"  value="<%=todate %>"></s:textfield> --%>
					<input type="text" class="form-control"  id="toDate" name="todate" value="<%=todate %>"  style="width:95%; text-align: center;">
			</div>
			<div class="form-group">
				<s:submit value="Go" theme="simple" cssClass="btn btn-primary"></s:submit>
			<a type="button" class="btn btn-primary"  title="Print" onclick="printpage()"><i class="fa fa-print"></i></a>
			<a type="button" id="btnxls" title="Save As XLS" onclick="printExcel()" class="btn btn-primary"><i class="fa fa-file-excel-o"></i></a>
			</div>
			
		</div>
	</div>
	</div>
	</s:form>
</div>							
<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12">
								<table class="my-table xlstable" style="width:100%">
								<thead>
								<tr style="background-color: darkgray;color: black;">
								<td>Sr.</td>
								<td>Investigation Type</td>
								<td style="text-align: center;">Total Investigation</td>
								<td style="text-align: center;">New</td>
								<td style="text-align: center;">Deleted</td>
								<td style="text-align: center;">Approved</td>
								<td style="text-align: center;">Collected</td>
								<td style="text-align: center;">Completed</td>
								</tr>
								</thead>
								
							
								<%
								while(rs.next()){
									 investtypeid=rs.getString(1);
									 PreparedStatement ps1= null;
									 int deleted=0, newinvst=0,collected=0,completed=0,approved=0;
									 investname=rs.getString(2);
									 
										String sql1=" select deleted,upstatus,compstatus,status  from apm_client_parent_investigation where lastmodified between '"+fromdate1+"' and '"+todate1+" 23:59:59' and invsttypeid='"+investtypeid+"' ";
										System.out.println(sql1);
										ps1= connection.prepareStatement(sql1);
										
										ResultSet rs1= ps1.executeQuery();
										while(rs1.next()){
											if(rs1.getInt(1)==1){
												deleted++;
											} if(rs1.getInt(2)==0 && rs1.getInt(3)==0){
												newinvst++;
											} if(rs1.getInt(2)==0 && rs1.getInt(3)==1){
												collected++;
											} if(rs1.getInt(2)==1 && rs1.getInt(3)==1 && rs1.getInt(4)==0){
												completed++;
											} if(rs1.getInt(4)==1){
												approved++;
											}			
										}
										
										
								%>
								<tr>
								<td><%=++i %></td>
								
								<td><%=rs.getString(2) %></td>
								<td style="text-align: center;"><%=rs.getInt(3) %></td>
							
								<td style="text-align: center;"><%=newinvst%></td>
								<td style="text-align: center;"><%=deleted %></td>
								<td style="text-align: center;"><%=approved%></td>
								<td style="text-align: center;"><%=collected %></td>
								<td style="text-align: center;"><%=completed%></td>
									<%System.out.println(""+newinvst+""+deleted+""+approved+""+collected+""+completed+"");
									}			%>
								</tr>
								</tbody>
								</table>
</div>				
				
<%
																										
}catch (Exception e) {
	e.printStackTrace();
}

%>
</div>
