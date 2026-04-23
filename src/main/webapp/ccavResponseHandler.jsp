<%-- <%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page import = "java.io.*,java.util.*,com.ccavenue.security.*" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Response Handler</title>
</head> --%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@page import="com.apm.Registration.eu.entity.UserProfile"%>
<%@page import="com.apm.common.utils.DateTimeUtils"%>
<%-- <%@page import="com.apm.BookDoctor.eu.entity.OrderResponse"%>
<%@page import="com.apm.BookDoctor.eu.entity.BookDoctor"%> --%>
<%@page import="java.sql.Connection"%>
<%@ page import = "java.io.*,java.util.*,com.ccavenue.security.*,java.sql.DriverManager,com.apm.main.common.constants.Constants" %>
<%@ page import="com.apm.common.eu.blogic.jdbc.Connection_provider,com.apm.Registration.eu.blogic.jdbc.JDBCUserProfileDAO,com.apm.Registration.eu.bi.UserProfileDAO" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet"  href= "https://unpkg.com/purecss@2.0.6/build/pure-min.css" integrity= "sha384-Uu6IeWbM+gzNVXJcM9XV3SohHtmWE+3VGi496jvgX1jyvDTXfdK+rfZc8C1Aehk5" crossorigin="anonymous"> 

<title>Insert title here</title>
	<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
<script type="text/javascript" src="registration/js/userprofile.js"></script>
<script src="https://code.jquery.com/jquery-3.1.1.min.js">
<%@ page contentType="text/html; charset=utf-8" pageEncoding="UTF-8"%>

<%
	request.setCharacterEncoding("UTF-8");
	response.setCharacterEncoding("UTF-8");
%>
<style>
	.font-inti
	{
		font-size: initial;
	}
	.font-inti a {
    font-size: initial;
    line-height: .9;
}
</style>
<script type="text/javascript">
	/* function submitForm(id) {
		//document.getElementById(id).submit();;
	} */
</script>
</head>
<body>
	<%
		String workingKey = "6CC663463EF06CD11A51CA0DC10D8678";		//32 Bit Alphanumeric Working Key should be entered here so that data can be decrypted.
		String encResp= request.getParameter("encResp");
		AesCryptUtil aesUtil=new AesCryptUtil(workingKey);
		String decResp = aesUtil.decrypt(encResp);
		StringTokenizer tokenizer = new StringTokenizer(decResp, "&");
		String error_msg="";
		 int insert=0;
		Hashtable hs=new Hashtable();
		Connection connection = DriverManager.getConnection(""+Constants.DB_HOST+":3306/grpmedicare","manasyuvi","M@n@S1928YUVI#$@%");	
		connection=Connection_provider.getconnection();
		UserProfileDAO userProfileDAO=new JDBCUserProfileDAO(connection);
		 
		String pair=null, pname=null, pvalue=null;
		while (tokenizer.hasMoreTokens()) {
			pair = (String)tokenizer.nextToken();
			if(pair!=null) {
				StringTokenizer strTok=new StringTokenizer(pair, "=");
				pname=""; pvalue="";
				if(strTok.hasMoreTokens()) {
					pname=(String)strTok.nextToken();
					if(strTok.hasMoreTokens())
						pvalue=(String)strTok.nextToken();
					hs.put(pname, pvalue);
					if(pname.equals("order_id")){
						
							  insert=userProfileDAO.saveChildData(pvalue);
							 session.setAttribute("orderid", pvalue);
						
					}
				}
			}
		}
	%>
	<center>
	
		<label style="margin-top: 5%;"><font size="4" color="blue" ><b>Response Page</b></font></label>
		<table border="1" class="pure-table pure-table-horizontal" style="margin-top: 4%;" >
		 <tr>
			<th>status_message</th>
			<th>amount</th>
			<th>order_status</th>
			<th>discount_value</th>
			<th>retry</th>
			<th>order_id</th>
			<th>bank_ref_no</th>
			<th>payment_mode</th>
			<th>vault</th>
			<th>bin_country</th>
			<th>tracking_id</th>
			<th>trans_date</th>
			<th>currency</th>
			<th>card_name</th>
			<th>mer_amount</th>
			<th>Receipt</th>
		</tr> 
		<tr>
			<%
			   UserProfile userProfile=userProfileDAO.getCustomerdatabyid(insert);
			   Enumeration enumeration = hs.keys();
			   String oderid=(String)session.getAttribute("orderid");
			   String mobileno=(String)session.getAttribute("mobileno");
			   String planid=(String)session.getAttribute("planid");
			   String maxorderno=(String)session.getAttribute("maxorderno");
			 
			  UserProfile user1=userProfileDAO.getPlanidandDatebyMob(mobileno);
			  double amount=userProfileDAO.getAmountbyplan(Integer.parseInt(planid));
			  double amount1=0.0;
				while(enumeration.hasMoreElements()) {
					
					pname=""+enumeration.nextElement();
					pvalue=""+ hs.get(pname);
					
					if(pname.equals("trans_date")){
						String date=pvalue.split(" ")[0];
						String time=pvalue.split(" ")[1];
						pvalue=DateTimeUtils.getCommencingDate(date);
						pvalue=pvalue+" "+time;
						
					}
					
			%>
			<% if(!pvalue.equals("null") && !pvalue.equals("")) {
				
				if(pname.equals("amount")){
					amount1=Double.parseDouble(pvalue);
					if(amount1 !=amount){
						int updtstatus=userProfileDAO.updateTransaction_status(oderid);
					}else{
						int update=userProfileDAO.updateChildData(pname,pvalue,oderid);
					}
				}
				/* if(pname.equals("order_status")){
					if(!oderid.equals(""+userProfile.getOrder_id()+"")){
						pvalue="Failed";
						oderid=userProfile.getOrder_id();
						int update=userProfileDAO.updateChildData(pname,pvalue,oderid);
					}
				
				     
				} */
				
				if(pname.equals("order_id")){
					
					if(!oderid.equals(maxorderno)){
						int updtstatus=userProfileDAO.updateTransaction_status(oderid);
						
					}
				}
				if(pname.equals("order_status")){
					if((amount1 !=amount) || !oderid.equals(maxorderno)){
						int updtstatus=userProfileDAO.updateTransaction_status(oderid);
					}else{
						int update=userProfileDAO.updateChildData(pname,pvalue,oderid);
					}
				}
				
				else{
			     int update=userProfileDAO.updateChildData(pname,pvalue,oderid);
			     
			     }%>
				 <td> <%= pvalue %> </td>
			<%}%>
			<%
				}
				
				String status_msg=userProfileDAO.getTransaction_statusbyid(oderid);
				
				if(status_msg.equals("Transaction is Successful")){
				     int updtplan=userProfileDAO.updateCustomerplan(planid,user1.getId());
				}
		%>
		<% if(status_msg.equals("Transaction is Successful")){ %>
		<td><a href="#" onclick="openPopup('invoicepageUserProfile?orderid=<%=oderid%>&mobileno=<%=mobileno%>')" style="cursor: pointer;"><i class="fa fa-print"></i></a></td>
		<%} %>
		</tr>
		</table>
		<%--  <div class="col-lg-12 col-md-12 col-xs-12 col-sm-12" >
		  		<div class="" style="height: 155px;">
					<div id="newpost" class="col-lg-12 col-md-12 col-xs-12 col-sm-12 borderbot">
							
							<%@ include file="/student/pages/invoiceprint.jsp" %>
						
							
					</div>
				</div>
		</div> --%>
	</center>
</body>
</html>