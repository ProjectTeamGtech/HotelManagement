

<%@page import="com.apm.Accounts.eu.blogic.jdbc.JDBCAccountsDAO"%>
<%@page import="com.apm.Accounts.eu.bi.AccountsDAO"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.apm.common.web.common.helper.LoginHelper"%>
<%@page import="com.apm.common.web.common.helper.LoginInfo"%>
<%@page import="com.apm.Registration.eu.blogic.jdbc.JDBCClinicDAO"%>
<%@page import="com.apm.Registration.eu.bi.ClinicDAO"%>
<%@page import="com.apm.Registration.eu.entity.Clinic"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="com.apm.common.utils.DateTimeUtils"%>
<%@page import="com.apm.common.eu.blogic.jdbc.Connection_provider"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.Connection"%>
<%@page import="java.util.Calendar"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.text.DateFormat"%>
<%@page import="java.util.Date"%>
 <%	
				LoginInfo loginInfo = LoginHelper.getLoginInfo(request);
		   %>



<link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
  <link rel="stylesheet" href="/resources/demos/style.css">
    <!-- Latest compiled and minified CSS -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script type="text/javascript" src="report/js/report.js"></script>
<script src="https://cdn.rawgit.com/rainabba/jquery-table2excel/1.1.0/dist/jquery.table2excel.min.js"></script>



  <script src="https://code.jquery.com/jquery-1.12.4.js"></script>
  <script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
  
<!-- jQuery library -->
<!-- <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script> -->

<!-- Latest compiled JavaScript --><!-- 
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
--><script>

 /* function printExcel() {

       $(".xlstable").table2excel({
					exclude: ".noExl",
					name: "report",
					filename: "hsnwisegst",
					fileext: ".xls",
					exclude_img: true,
					exclude_links: true,
					exclude_inputs: true
				});
   } */
  

$(document).ready(function() {

	

 $(function() {
	$( "#toDate" ).datepicker({ dateFormat: "dd-mm-yy" });
	
	});

 $(function() {
	 $( "#fromDate" ).datepicker({ dateFormat: "dd-mm-yy" });
	});

});	  
</script>


<%
String fromdate= request.getParameter("fromdate");
String todate=request.getParameter("todate");
if(fromdate==null){
	fromdate="";
}
if(todate==null){
	todate="";
}
if(fromdate.equals("")){
	 DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
	 Calendar cal = Calendar.getInstance();
	 cal.add(Calendar.DATE, -30);
	 fromdate = dateFormat.format(cal.getTime());
}
if(todate.equals("")){
	 DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
	 Calendar cal = Calendar.getInstance();
	 todate = dateFormat.format(cal.getTime());
	 System.out.print(""+todate);
}


%>
<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12">

<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12 print-visible hidden-md hidden-lg">
<%
Connection connection= null;
connection= Connection_provider.getconnection();
ClinicDAO clinicDAO= new JDBCClinicDAO(connection);
Clinic clinic = clinicDAO.getCliniclistDetails(loginInfo.getClinicid());
AccountsDAO accountsDAO= new JDBCAccountsDAO(connection);
ArrayList<Clinic> locationAdressList = accountsDAO.getLocationAddress(loginInfo.getClinicid());

%>
<div class="col-lg-2 col-md-2 col-xs-2 col-sm-2">
<img class="img-responsive logoste1" src="liveData/clinicLogo/<%=clinic.getUserImageFileName() %>" />
</div>
<div class="col-lg-9 col-md-9 col-xs-9 col-sm-9">
<b><span style="font-size: 20px"><%=clinic.getClinicName() %></span></b><br>
<%for(Clinic clinic2:locationAdressList) {
%>
<span><%=clinic2.getAddress() %></span>
<%
}
int total=0;
%>
<b>URL:</b> <span><%=clinic.getWebsiteUrl() %></span>&nbsp;&nbsp; 
 <b>Phone :</b><span><%=clinic.getMobileNo() %></span><br>
<b>Reg No:</b><span><%=clinic.getOwner_qualification() %></span>

</div>
</div>
<div class="row details" style="height: 40px;background-color: #6699CC;color: white">
									<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12">

										<h4> Investigation Revenue Report </h4>

									</div>
</div>
<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12 hidden-print" style="margin-top: 30px ">
<div >
<form action="investigationrevenuereport.jsp" method="post">
<label>From Date</label>
<input type="text" name="fromdate" id='fromDate' placeholder="From Date" class="from-control" value="<%=fromdate %>" style="width: 7%;text-align: center;border-color: #204d74; " >
<label>To Date</label>
<input type="text" name="todate" id='toDate' placeholder="To Date" class="from-control" value="<%=todate%>" style="width: 7%;text-align: center;border-color: #204d74; ">
<input type="submit" value ="Go" class="btn btn-primary">
<input type="button" value ="Print" class="btn btn-primary" onclick="window.print()">
<!-- <input type="button" value ="Excel" class="btn btn-primary" onclick="printExcel()"> -->
</form>
</div>
</div>




<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12" style="margin-top: 10px; ">
<table class=" xlstable" style="width:100%;text-align: center;border: 2px thick #6699CC ;">
<tr style="background-color: #6699CC !important;color: white;border: 2px solid #6699CC;font-size: 12px;height: 22px">
<td>Sr. No.</td>
<td>Department</td>
<td>Amount</td>
</tr>
<%

PreparedStatement ps=null;
try{
	connection= Connection_provider.getconnection();
	String sql=" select sum(apm_invoice_assesments.charge* quantity) ,job_title.jobtitle,count(*) from  apm_investigation_type ";
	sql= sql+"   inner join apm_invoice_assesments  on apm_invoice_assesments.apmtType=apm_investigation_type.name ";
	sql=sql+" inner join job_title on job_title.id=apm_investigation_type.department  ";
	sql=sql+"  where commencing  between '"+DateTimeUtils.getCommencingDate1(fromdate)+"' and '"+DateTimeUtils.getCommencingDate1(todate)+"' group by apm_investigation_type.department ";
	sql=sql+"   ";
	int i=1;
	ps= connection.prepareStatement(""+sql);
	ResultSet rs= ps.executeQuery();
	while(rs.next()){
		%>
		<tr style="border: 2px solid #6699CC;font-size: 11px;color:#373c40;height: 22px;">
		<td><%=i %></td>
		<td><%if((rs.getString(2)!=null)&&rs.getString(2).equals("Radiologist")){} %>
		<%=rs.getString(2) %></td>
		<td><%=rs.getString(1) %></td>
		</tr>
		<%
		total=rs.getInt(1)+total;
		i=i+1;
	}
}catch(Exception e){
	e.printStackTrace();
}
%>		
<tr  style="border: 2px solid #6699CC;font-size: 11px;color:#373c40;height: 22px;">
<td></td>
<td>Total :</td>
<td><b><%=total %></b></td>
</tr>
</table>
</div>





	
</div>