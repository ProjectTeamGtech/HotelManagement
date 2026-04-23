<%@page import="com.apm.DiaryManagement.eu.entity.Priscription"%>
<%@page import="com.apm.DiaryManagement.eu.entity.Breadcrumbs"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.apm.Pharmacy.eu.blogic.jdbc.JDBCPharmacyDAO"%>
<%@page import="com.apm.Pharmacy.eu.bi.PharmacyDAO"%>
<%@page import="java.util.Calendar"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.text.DateFormat"%>
<%@page import="com.apm.common.utils.DateTimeUtils"%>
<%@page import="com.apm.Ipd.eu.blogic.jdbc.JDBCIpdDAO"%>
<%@page import="com.apm.Ipd.eu.bi.IpdDAO"%>
<%@page import="com.apm.Registration.eu.blogic.jdbc.JDBCUserProfileDAO"%>
<%@page import="com.apm.Registration.eu.bi.UserProfileDAO"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="com.apm.common.eu.blogic.jdbc.Connection_provider"%>
<%@page import="java.sql.Connection"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<%@page import="java.util.Date"%>
<script type="text/javascript" src="report/js/report.js"></script>
<script type="text/javascript"
	src="assesmentForms/js/jquery.table2excel.js"></script>

<script type="text/javascript" src="accounts/js/commonaddcharge.js"></script>
<script type="text/javascript" src="pharmacy/js/pharmacy.js"></script>
<script type="text/javascript"
	src="diarymanagement/js/addpriscription.js"></script>
<script type="text/javascript" src="emr/js/addInvestigation.js"></script>
<script type="text/javascript" src="common/js/pagination.js"></script>
<script type="text/javascript" src="pharmacy/js/pharmacy.js"></script>
<script type="text/javascript"
	src="assesmentForms/js/jquery.table2excel.js">
	
</script>

<script>
	function printExcel() {

		$(".xlstable").table2excel({
			exclude : ".noExl",
			name : "report",
			filename : "PharmacyCreditReport",
			fileext : ".xls",
			exclude_img : true,
			exclude_links : true,
			exclude_inputs : true
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
		
		 document.addEventListener("contextmenu", function(e){
				e.preventDefault();
				}, false); 
	});
</script>

<style>
ul.breadcrumb {
  list-style: none;
  background-color: #eee;
}
ul.breadcrumb li {
  display: inline;
  font-size: 15px;
}
ul.breadcrumb li+li:before {
  color: black;
  content: ">\00a0";
}
ul.breadcrumb li a {
  color: #0275d8;
  text-decoration: none;
}
ul.breadcrumb li a:hover {
  color: #01447e;
  text-decoration: underline;
}
ul, ol {
    margin-top: 0 !important;
    margin-bottom: 0px !important;
}
.breadcrumb {
     padding: 0px 0px !important; 
     margin-bottom: 0px !important;
}
</style>
<%
LoginInfo loginInfo=LoginHelper.getLoginInfo(request);
String fromdate="";
String todate="";
	try {
		Connection connection = Connection_provider.getconnection();
		PreparedStatement ps = null;
		double totalamount=0.0;
		double totaldisc=0.0;
		double totalbal=0.0;
		double totalpaid=0.0;
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
		
	%>
<div class="">


	<div class="print-visible hidden-md hidden-lg letterheadhgt" style="height: 135px;">
		<div id="newpost"
			class="col-lg-12 col-md-12 col-xs-12 col-sm-12 borderbot">
			<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12"
				style="padding-left: 0px; padding-right: 0px;">
				<link href="common/css/printpreview.css" rel="stylesheet" />
				<%@ include file="/accounts/pages/letterhead.jsp"%>
			</div>
		</div>
	</div>
	<div class="row details">
		<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12">

			<h4>Pharmacy Credit Report</h4>

		</div>

	</div>
<%-- <div class="hidden-print">
											<ul class="breadcrumb">
												&nbsp;
												<%ArrayList<Breadcrumbs> indentflowlist = (ArrayList<Breadcrumbs>) session.getAttribute("indentflowlist"); %>
												<%for (Breadcrumbs breadcrumbs : indentflowlist) { %>
													<%if(breadcrumbs.isIscurrent()){ %>
														<li><%=breadcrumbs.getShowingname()%></li>
													<%}else{ %>
														<%if(breadcrumbs.getOn()){ %>
															<li><a href="<%=breadcrumbs.getUrllink()%>"><%=breadcrumbs.getName()%></a></li>
														<%}else{ %>
															<li><%=breadcrumbs.getName()%></li>
														<%} %>
													<%} %>
													
												<%} %>
											</ul>
										</div> --%>

	<form action="pharmacycreditrptReport" method="post">
		<div class="col-lg-12 col-md-12 topback2 hidden-print">

					<input type="text" class="form-control" readonly="readonly"   id="fromDate" name="fromdate" value="<%=fromdate %>" style="width: 6%;text-align: center;">
			
	
				
					<input type="text" class="form-control"  id="toDate" name="todate" value="<%=todate %>"  style="width:6%; text-align: center;">
	
			<select name="test_bal" id="test_bal"
				class="form-control chosen-select" style="width:30%" >
				<option value="0" selected="selected">Select Balance</option>
				<option value="5000">Balance>5000</option>
				<option value="10000" >Balance>10000</option>
				<option value="15000" >Balance>15000</option>
				<option value="50000" >Balance>50000</option>
			</select> 
			
			<input type="submit" value="Go" class="btn btn-primary">


			
				<a type="button" class="btn btn-primary" title="Print"
					onclick="printpage()"><i class="fa fa-print"></i></a> <a
					type="button" id="btnxls" title="Save As XLS"
					onclick="printExcel()" class="btn btn-primary"><i
					class="fa fa-file-excel-o"></i></a>
		
		</div>
	</form>
</div>


<%
	String balance = request.getParameter("test_bal");
		if (balance == null) {
			balance = "0";
		}
%>


<%
		String sql = "select apm_medicine_bill.clientid,abrivationid,fullname,sum(debit),sum(discount),sum(balance) from apm_medicine_bill  ";
		sql = sql + "inner join apm_patient on apm_patient.id=apm_medicine_bill.clientid  ";
		sql = sql + "where apm_medicine_bill.date between '"+fromdate1+"' and '"+todate1+"' and isreturn=0  ";
		sql = sql + "group by clientid having  sum(balance)>" + balance + "";
		System.out.println(fromdate1);
		System.out.println(sql);
		ps = connection.prepareStatement(sql);
		ResultSet rs = ps.executeQuery();
		UserProfileDAO profileDAO = new JDBCUserProfileDAO(connection);
		IpdDAO ipdDAO = new JDBCIpdDAO(connection);
		PharmacyDAO pharmacyDAO=new JDBCPharmacyDAO(connection);
%>






<table class="my-table xlstable" style="width: 100%;">
	<tr bgcolor="#3c6ea0" style="color: white;">
		<td>Sr.</td>
		<td>UHID</td>
		<td>IPD ID</td>
		<td>Patient Name</td>
		<td>Date of Admission</td>
		<td>Consultant Name</td>
		<td style="text-align: right;" >Total Amount</td>
		<td style="text-align: right;" >Total Discount</td>
		<td style="text-align: center;" >Paid Amount</td>
		<td style="text-align: center;" >Balance Amount</td>
		
	</tr>
	<%
		int i = 0;
	%>
	<%
		while (rs.next()) {
				String clientid = rs.getString(1);

				String ipdid = ipdDAO.getIpdId(clientid);
				String practid = ipdDAO.getPractidbyipd(ipdid);
				String Pract_name = profileDAO.getFullName(practid);

				String doa = ipdDAO.getIPDAdmissionDate(ipdid);
				String temp[] = doa.split(" ");
				String addmissiondate = DateTimeUtils.getCommencingDate1(temp[0]);
				String ipdseqno = ipdDAO.getipdseqno(ipdid);
				
				
				if (ipdseqno == null) {
					ipdseqno = "0";
				}
				if (ipdseqno.equals("")) {
					ipdseqno = "0";
				}
				if(!ipdseqno.equals("0")){
					if(loginInfo.getIpd_abbr_access()==1){
						String newipdabbr=ipdDAO.getIpdAbrivationIds(Integer.parseInt(ipdseqno));
						ipdseqno=newipdabbr;
					}
				}
				String discount1 = DateTimeUtils.changeFormat(rs.getDouble(5));

				double discount = Math.round(Double.parseDouble(discount1) * 100) / 100;
				//total amount
				 totalamount=totalamount+Double.parseDouble(rs.getString(4));
				 System.out.println(totalamount);
				 totaldisc=totaldisc+discount;
				 System.out.println(totaldisc);
				 totalbal=totalbal+Double.parseDouble(rs.getString(6));
				 System.out.println(totalbal);
				 //totalpaid=totalpaid+Double.parseDouble(rs.getString(7));
				 double paid =  pharmacyDAO.getTotalPaidAmountBYclient(rs.getInt(1),fromdate1,todate1,balance,0);
				 totalpaid=totalpaid+paid;
				 System.out.println(totalpaid);
	%>

	<tr>
		<td style="width: 5%"><%=++i%></td>
		<td style="width: 15%"><%=rs.getString(2)%></td>
		<td style="width: 5%"><%=ipdseqno%></td>
		<td style="width: 15%"><a href="#" onclick="showbillspatient(<%=clientid %>,1)"><%=rs.getString(3)%></a> </td>
		<td style="width: 10%"><%=addmissiondate%></td>
		<td style="width: 15%"><%=Pract_name%></td>
		<td style="width: 10%; text-align: right;"><%=rs.getDouble(4)+discount%></td>
		<td style="width: 10%; text-align: right;"><%=discount%></td>
	
		<td style="width: 15%; text-align: center;"><%=paid %></td>
	<td style="width: 10%; text-align: center;"><%=rs.getString(6)%></td>

	</tr>
	<%
		}
			String sql1 = "select apm_medicine_bill.pclientid,apm_pharmacy_client.id,fullname,reference,sum(debit),sum(discount),sum(apm_medicine_bill.balance) from apm_medicine_bill  ";
			sql1 = sql1 + "inner join apm_pharmacy_client on apm_pharmacy_client.id=apm_medicine_bill.pclientid ";
			sql1 = sql1 + "where apm_medicine_bill.date between '"+fromdate1+"' and '"+todate1+"' and isreturn=0 " ;
			sql1 = sql1 +  "group by pclientid having sum(apm_medicine_bill.balance)>" + balance +" ";
			PreparedStatement ps1 = null;

			System.out.println(sql1);
			ps1 = connection.prepareStatement(sql1);
			ResultSet rs1 = ps1.executeQuery();
			while (rs1.next()) {
				String pclientid = rs1.getString(1);

				String ipdid = ipdDAO.getIpdId(pclientid);
				
				String Pract_name = rs1.getString(4);
				System.out.println(Pract_name);
				String doa = ipdDAO.getIPDAdmissionDate(ipdid);
				String temp[] = doa.split(" ");
				String addmissiondate = DateTimeUtils.getCommencingDate1(temp[0]);
				String ipdseqno = ipdDAO.getipdseqno(ipdid);
				
				if (ipdseqno == null) {
					ipdseqno = "0";
				}
				if (ipdseqno.equals("")) {
					ipdseqno = "0";
				}
				if(!ipdseqno.equals("0")){
					if(loginInfo.getIpd_abbr_access()==1){
						String newipdabbr=ipdDAO.getIpdAbrivationIds(Integer.parseInt(ipdseqno));
						ipdseqno=newipdabbr;
					}
				}
				String discount1 = DateTimeUtils.changeFormat(rs1.getDouble(6));

				double discount = Math.round(Double.parseDouble(discount1) * 100) / 100;
				
				 totalamount=totalamount+Math.round(Double.parseDouble(rs1.getString(5))*100)/100;
				
				 totaldisc=totaldisc+discount;
				 
				 totalbal=totalbal+Math.round(Double.parseDouble(rs1.getString(7))*100)/100;
				
				 //totalpaid=totalpaid+Math.round(Double.parseDouble(rs1.getString(8))*100)/100;
				  double paid =  pharmacyDAO.getTotalPaidAmountBYclient(rs1.getInt(1),fromdate1,todate1,balance,1);
				 totalpaid=totalpaid+paid;
				 String pharmacyabrivationid="";
				 Priscription masterClient = pharmacyDAO.getPharmacyPatient(pclientid);
				 if(!DateTimeUtils.numberCheck(masterClient.getAbrivationid()).equals("0")){
					 pharmacyabrivationid = masterClient.getAbrivationid();
				 }else{
					 pharmacyabrivationid = rs1.getString(2);
				}		
				
	%>
	<tr>
		<td style="width: 5%"><%=++i%></td>
		<td style="width: 15%">
		<%=pharmacyabrivationid%>
		</td>
		<td style="width: 5%"><%=ipdseqno%></td>
		<td style="width: 15%"><a href="#" onclick="showbillspatient(<%=pclientid %>,1)"><%=rs1.getString(3)%></a> </td>
		<td style="width: 10%"><%=addmissiondate%></td>
		<td style="width: 15%"><%=Pract_name%></td>
		<td style="width: 10%; text-align: right;"><%=rs1.getDouble(5)+discount%></td>
		<td style="width: 10%; text-align: right;"><%=discount%></td>
	
		<td style="width: 15%; text-align: center;"><%=paid %></td>
	<td style="width: 10%; text-align: center;"><%=rs1.getString(7)%></td>

	
	<%
		}%>
		</tr>
	<tr>
	<td>Total</td>
	<td></td>
	<td></td>
	<td></td>
	<td></td>
	<td></td>
	<td style="text-align: right;"><b><%=totalamount +totaldisc%></b></td>
	<td style="text-align: right;"><b><%=totaldisc %></b></td>
	
	<td style="text-align: center;"><%=totalpaid %></td>
	<td style="text-align: center;"><b><%=totalbal %></b></td>
	</tr>
		
<%
		}catch (Exception e) {
			e.printStackTrace();
		}
	
	%>
</table>





</div>

<!-- Patinet Details View Modal -->
<div id="detailsview" class="modal fade" role="dialog">
  <div class="modal-dialog modal-lg">
    <!-- Modal content-->
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal">&times;</button>
        <h4 class="modal-title">Bill Transaction Report</h4>
      </div>
      <div class="modal-body">
      <div id="page_printer" class="minhesigh">
       <h4 class="modal-title">Patient Name - <SPAN id='tempname'></SPAN></h4>
      	<div class="col-lg-12 col-md-12 col-xs-12" style="padding:0px;" id="newdiv">
         <table class="table table-responsive" >
          <thead>
           <tr>
            <th>Sr</th>
            <th>Bill</th>
            <th>Bill ID</th>
            <th>Bill Date</th>
            <th>Bill Type</th>
            <th style="text-align:right;">Total Disc</th>
            <th style="text-align:right;">Total Amt</th>
            <th style="text-align:right;">Paid Amt</th>
            <th style="text-align:right;">Balance</th>
            <th style="text-align:right;">Return Amt</th>
            <th class="hidden">Change Mode</th>
            <th class="hidden">Receipt</th>
            <th></th>
            <th></th>
           
           </tr>
          </thead>
          <tfoot style="background-color: rgba(239, 239, 239, 0.35);">
        <tr style="color: green;" id="tfootdata">
          <td></td>
          <td></td>
          <td></td>
          <td></td>
          <td>Total</td>
          <td style="text-align:right;">1731.70</td>
          <td style="text-align:right;">1700.70</td>
          <td style="text-align:right;"><a href="#" style="color: green;">31.00</a></td>
          <td style="text-align:right;">1700.70</td>
          <td class="hidden"></td>
          <td class="hidden"></td>
          <td></td>
          <td></td>
        </tr>
      </tfoot>
          <tbody id="tbodydata">
           
          </tbody>
         </table>
        </div>
      </div>
      </div>
      <div class="modal-footer" id="printbtndiv">
        <a href="#" class="btn btn-warning hidden-print" onclick="printDiv('newdiv')" style="margin-top: 15px;">Print</a>
      </div>
    </div>

  </div>
</div>

