package com.apm.ThirdParties.web.action;

import java.sql.Connection;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.print.attribute.standard.MediaSize.Other;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.a.a.a.a.a.d;
import com.apm.Accounts.eu.bi.AccountsDAO;
import com.apm.Accounts.eu.blogic.jdbc.JDBCAccountsDAO;
import com.apm.Accounts.eu.entity.Accounts;
import com.apm.DiaryManagement.eu.bi.ClientDAO;
import com.apm.DiaryManagement.eu.blogic.jdbc.JDBCClientDAO;
import com.apm.DiaryManagement.eu.entity.Client;
import com.apm.DiaryManagement.web.action.EmbeddedImageEmailUtil;
import com.apm.DiaryManagement.web.action.SmsService;
import com.apm.Log.eu.entity.EmailLetterLog;
import com.apm.Master.eu.entity.Master;
import com.apm.Registration.eu.bi.ClinicDAO;
import com.apm.Registration.eu.blogic.jdbc.JDBCClinicDAO;
import com.apm.Registration.eu.entity.Clinic;
import com.apm.ThirdParties.eu.bi.OutstandingReportDAO;
import com.apm.ThirdParties.eu.bi.ThirdPartyDAO;
import com.apm.ThirdParties.eu.blogic.jdbc.JDBCOutstandingReportDAO;
import com.apm.ThirdParties.eu.blogic.jdbc.JDBCThirdPartyDAO;
import com.apm.ThirdParties.eu.entity.OutstandingReport;
import com.apm.ThirdParties.eu.entity.ThirdParty;
import com.apm.ThirdParties.web.form.OutstandingReportForm;
import com.apm.ThirdParties.web.form.ThirdPartyForm;
import com.apm.common.eu.blogic.jdbc.Connection_provider;
import com.apm.common.utils.DateTimeUtils;
import com.apm.common.utils.Pagination;
import com.apm.common.web.action.BaseAction;
import com.apm.common.web.common.helper.LoginHelper;
import com.apm.common.web.common.helper.LoginInfo;
import com.apm.main.common.constants.Constants;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.Preparable;

public class OutstandingReportAction extends BaseAction implements Preparable, ModelDriven<OutstandingReportForm> {
	HttpServletRequest request = (HttpServletRequest) ActionContext.getContext().get(ServletActionContext.HTTP_REQUEST);
	HttpServletResponse response = (HttpServletResponse)ActionContext.getContext().get(ServletActionContext.HTTP_RESPONSE);
	HttpSession session = request.getSession(true);
	
	OutstandingReportForm outstandingReportForm = new OutstandingReportForm();
	private Pagination pagination = new Pagination(10, 1);
	public Pagination getPagination() {
		return pagination;
	}


	public void setPagination(Pagination pagination) {
		this.pagination = pagination;
	}
	LoginInfo loginInfo = LoginHelper.getLoginInfo(request);
	
public String execute() throws Exception {
		
	if(!verifyLogin(request)){
		return "login";
	}
	
	session.removeAttribute("collectInvoiceList");
		
		Connection connection = null;
		try{
			connection = Connection_provider.getconnection();
			OutstandingReportDAO outstandingReportDAO = new JDBCOutstandingReportDAO(connection);
			//int totalCount = outstandingReportDAO.getTotalOutStandingCount(loginInfo.getId());
			/*pagination.setPreperties(totalCount);
			ArrayList<OutstandingReport>outstandingReportList = outstandingReportDAO.getOutStandingReportList(loginInfo.getId(),pagination);
			pagination.setPage_records(outstandingReportList.size());
			outstandingReportForm.setTotalRecords(totalCount);
			outstandingReportForm.setPagerecords(Integer.toString(pagination.getPage_records()));
			outstandingReportForm.setOutstandingReportList(outstandingReportList);*/
			
			ArrayList<OutstandingReport>outstandingReportListClient = new ArrayList<OutstandingReport>();
			ArrayList<OutstandingReport>outstandingReportListTP = new ArrayList<OutstandingReport>();
			
			String fromDate = outstandingReportForm.getFromDate();
			String toDate = outstandingReportForm.getToDate();	
			
			if(fromDate.equals("")){
				DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
				Calendar cal = Calendar.getInstance();
				cal.add(Calendar.DATE, -7); 
				fromDate = dateFormat.format(cal.getTime());
				outstandingReportForm.setFromDate(fromDate);
			}
			if(toDate.equals("")){
				DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
				Calendar cal = Calendar.getInstance();
				//cal.add(Calendar.DATE, -7); 
				toDate = dateFormat.format(cal.getTime());
				outstandingReportForm.setToDate(toDate);
			}
			
			
			if(!fromDate.equals("")){
				String temp[]= fromDate.split("/");
				fromDate = temp[2]+"-"+temp[1]+"-"+temp[0];
			}
			if(!toDate.equals("")){
				String temp1[]= toDate.split("/");
				toDate = temp1[2]+"-"+temp1[1]+"-"+temp1[0];
			}
			
			
			outstandingReportListClient = outstandingReportDAO.getOutStandingReportOfClientList(loginInfo.getId(),pagination,"Client",outstandingReportForm.getClientId(),fromDate,toDate);
			outstandingReportListTP = outstandingReportDAO.getOutstandingReportOfTPList(loginInfo.getId(),pagination,"Third Party",outstandingReportForm.getThirdParty(),fromDate,toDate);
			ArrayList<OutstandingReport>outstandingReportListAll = new ArrayList<OutstandingReport>(outstandingReportListClient);
	       
			outstandingReportListAll.addAll(outstandingReportListTP);
			outstandingReportForm.setOutstandingReportList(outstandingReportListAll);
			
			ArrayList<OutstandingReport>outstandingLimitExceedList = new ArrayList<OutstandingReport>();

			/*for(OutstandingReport outstandingReport:outstandingReportList){
				
				double creditLmt = outstandingReport.getCreditWarningLimit();
				double unpaidamout = outstandingReport.getUnpaidAmout();
				if(unpaidamout>=creditLmt){
					outstandingLimitExceedList.add(outstandingReport);
				}
				
			}
			session.setAttribute("outstandingLimitExceedList", outstandingLimitExceedList);*/
			
			
			
		}catch (Exception e) {
			e.printStackTrace();
		}finally{
			
			connection.close();
		}
	 
		return SUCCESS;
	}


public String updatealt() throws Exception{
	if(!verifyLogin(request)){
		return "login";
	}
	
	
		
		Connection connection = null;
		try{
			connection = Connection_provider.getconnection();
			OutstandingReportDAO outstandingReportDAO = new JDBCOutstandingReportDAO(connection);
			ArrayList<Accounts>altinvoicelist = (ArrayList<Accounts>)session.getAttribute("altinvoicelist");
			
			for(Accounts accounts : altinvoicelist){
				String invoiceno = accounts.getInvoiceNo();
				String dedtn = request.getParameter("dedtn"+invoiceno);
				String tds = request.getParameter("tds"+invoiceno);
				String stmdcine = request.getParameter("stmdcine"+invoiceno);
				String recdamt = request.getParameter("recdamt"+invoiceno);
				String runbal = request.getParameter("runbal"+invoiceno);
				
				String recno = outstandingReportForm.getRecno();
				String date = DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone());
				
				double recpayment = Double.parseDouble(dedtn) + Double.parseDouble(tds) + Double.parseDouble(stmdcine) + Double.parseDouble(recdamt);
				
				if(!recdamt.equals("0.00")){
					int result = outstandingReportDAO.updateInvoiceAllocation(invoiceno,dedtn,tds,stmdcine,recdamt,runbal,recno,date);
					
					
					//record payment
					boolean isinvoicexeist = outstandingReportDAO.checkInvoiceExist(invoiceno);
					if(!isinvoicexeist){
						int recpmnt = outstandingReportDAO.savePayment(outstandingReportForm.getTpid(), Integer.toString(accounts.getClientid()),
								"Cash", invoiceno, Double.toString(recpayment), date);
					}else{
						
						int lastpamntid = outstandingReportDAO.getLastPaymentId(invoiceno);
						int upd = outstandingReportDAO.updatePayment(lastpamntid,date,Double.toString(recpayment));
					}
					
				}
				
			}
			
			
		}catch (Exception e) {
			e.printStackTrace();
		}finally{
			connection.close();
		}
	
	return "updatealt";
}

public String showalt() throws Exception{
	String altid = request.getParameter("id");
	String tpid = request.getParameter("tpid");
	String ra = request.getParameter("ra");
	String action = request.getParameter("action");
	
	Connection connection = null;
	try{
		connection = Connection_provider.getconnection();
		OutstandingReportDAO outstandingReportDAO = new JDBCOutstandingReportDAO(connection);
		ThirdPartyDAO thirdPartyDAO = new JDBCThirdPartyDAO(connection);
		
		ThirdParty thirdParty = thirdPartyDAO.getThirdPartyDetails(tpid);
		
		outstandingReportForm.setThirdParty(thirdParty.getCompanyName());
		
		outstandingReportForm.setRecno(altid);
		outstandingReportForm.setTpid(tpid);
		
		/*if(action.equals("ad")){
			altid = "0";
		}*/
		ArrayList<Accounts>invoiceList = outstandingReportDAO.getAltInvoiceList(tpid,altid);
		
		Accounts accounts = invoiceList.get(invoiceList.size()-1);
		double altsum = accounts.getAltsum();
//		System.out.println(accounts.getAltsum());
		
		double alresult = Double.parseDouble(ra) - altsum;
		outstandingReportForm.setRecamt(DateTimeUtils.changeFormat(alresult));
		
		outstandingReportForm.setInvoiceList(invoiceList);
		session.setAttribute("altinvoicelist", invoiceList);
		
	}catch (Exception e) {
		e.printStackTrace();
	}finally{
		connection.close();
	}
	
	return "showaltlist";
}

public String allocation() throws Exception{
	if(!verifyLogin(request)){
		return "login";
	}
	
	String fromDate = outstandingReportForm.getFromDate();
	String toDate = outstandingReportForm.getToDate();	
	
	
	if(fromDate.equals("")){
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DATE, -7); 
		fromDate = dateFormat.format(cal.getTime());
		outstandingReportForm.setFromDate(fromDate);
	}
	if(toDate.equals("")){
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		Calendar cal = Calendar.getInstance();
		//cal.add(Calendar.DATE, -7); 
		toDate = dateFormat.format(cal.getTime());
		outstandingReportForm.setToDate(toDate);
	}
	
	
	if(!fromDate.equals("")){
		String temp[]= fromDate.split("/");
		fromDate = temp[2]+"-"+temp[1]+"-"+temp[0];
	}
	if(!toDate.equals("")){
		String temp1[]= toDate.split("/");
		toDate = temp1[2]+"-"+temp1[1]+"-"+temp1[0];
	}
	
	Connection connection = null;
	try{
		connection = Connection_provider.getconnection();
		OutstandingReportDAO outstandingReportDAO = new JDBCOutstandingReportDAO(connection);
		
		ArrayList<OutstandingReport>allotList = outstandingReportDAO.getAllotList(fromDate,toDate);
		outstandingReportForm.setAllotList(allotList);
		
	}catch (Exception e) {
		e.printStackTrace();
	}finally{
		connection.close();
	}
	
	return "allocationlist";
	
}

public String savealt() throws Exception{
	
	if(!verifyLogin(request)){
		return "login";
	}
	
	Connection connection = null;
	try{
		connection = Connection_provider.getconnection();
		OutstandingReportDAO outstandingReportDAO = new JDBCOutstandingReportDAO(connection);
		
		OutstandingReport outstandingReport = new OutstandingReport();
		outstandingReport.setThirdPartyId(Integer.parseInt(outstandingReportForm.getThirdParty()));
		outstandingReport.setAllotamount(outstandingReportForm.getAllotamount());
		outstandingReport.setDate(DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone()));
		
		outstandingReport.setPaymentType(outstandingReportForm.getPaymentType());
		outstandingReport.setCheqType(outstandingReportForm.getCheqType());
		outstandingReport.setCheqNo(outstandingReportForm.getCheqNo());
		outstandingReport.setBankname(outstandingReportForm.getBankname());
		outstandingReport.setHandoverTo(outstandingReportForm.getHandoverTo());
		
		
		
		
		
		int save = outstandingReportDAO.saveAltAmount(outstandingReport);
		
	}catch (Exception e) {
		e.printStackTrace();
	}finally{
		connection.close();
	}
	
	return "savealt";
}

public String getActionList() throws SQLException{
	if(!verifyLogin(request)){
		return "login";
	}
	String id = request.getParameter("id");
	String name = request.getParameter("name");
	String email = request.getParameter("email");
	String contactno = request.getParameter("contactno");
	if(id!=null && name!= null && email!=null && contactno!=null){
	session.setAttribute("id", id);
	session.setAttribute("name", name);
	session.setAttribute("email", email);
	session.setAttribute("contactno", contactno);
	}
	if(id==null || id ==""){
		id = Integer.toString(outstandingReportForm.getThirdPartyId1());
	}
	if(name==null || name ==""){
		name = outstandingReportForm.getThirdPartyName1();
	}
	if(email==null || email ==""){
		email = outstandingReportForm.getThirdPartEmail1();
	}
	if(contactno==null || contactno ==""){
		contactno = outstandingReportForm.getThirdPartyContactno1();
	}
	if(id==null || id =="" || id.equalsIgnoreCase("0")){
		id = (String) session.getAttribute("id");
	}
	if(name==null || name ==""){
		name = (String) session.getAttribute("name");
	}
	if(email==null || email ==""){
		email = (String) session.getAttribute("email");
	}
	if(contactno==null || contactno ==""){
		contactno = (String) session.getAttribute("contactno");
	}
	Connection connection = null;
	try{
		connection = Connection_provider.getconnection();
		OutstandingReportDAO outstandingReportDAO = new JDBCOutstandingReportDAO(connection);
		int totalCount = outstandingReportDAO.getTotalOutStandingActionCount(loginInfo.getId(),id);
		pagination.setPreperties(totalCount);
		ArrayList<OutstandingReport>outstandingActionList = outstandingReportDAO.getOutStandingReportActionList(loginInfo.getId(),id,pagination);
		pagination.setPage_records(outstandingActionList.size());
		outstandingReportForm.setTotalRecords(totalCount);
		outstandingReportForm.setPagerecords(Integer.toString(pagination.getPage_records()));
		outstandingReportForm.setOutstandingActionList(outstandingActionList);
		
		outstandingReportForm.setThirdPartEmail(email);
		outstandingReportForm.setThirdPartyContactno(contactno);
		outstandingReportForm.setThirdPartyId(Integer.parseInt(id));
		outstandingReportForm.setThirdPartyName(name);
		
		outstandingReportForm.setThirdPartEmail1(email);
		outstandingReportForm.setThirdPartyContactno1(contactno);
		outstandingReportForm.setThirdPartyId1(Integer.parseInt(id));
		outstandingReportForm.setThirdPartyName1(name);
		
		outstandingReportForm.setThirdPartEmail2(email);
		outstandingReportForm.setThirdPartyContactno2(contactno);
		outstandingReportForm.setThirdPartyId2(Integer.parseInt(id));
		outstandingReportForm.setThirdPartyName2(name);
		
	}catch (Exception e) {
		e.printStackTrace();
	}finally{
		
		connection.close();
	}
	
	return "actionListPage";
}

public String sendSaveEmail() throws Exception{
	if(!verifyLogin(request)){
		return "login";
	}
	String id = request.getParameter("id");
	String name = request.getParameter("name");
	String email = request.getParameter("email");
	String notes = request.getParameter("notes");
	String date = request.getParameter("date");
	String time = request.getParameter("time");
	String subject = request.getParameter("subject");
	String ccEmail = request.getParameter("ccEmail");
	Connection connection = null;
	try{
		
		connection = Connection_provider.getconnection();
		OutstandingReportDAO outstandingReportDAO = new JDBCOutstandingReportDAO(connection);
		
		OutstandingReport outstandingReport = new OutstandingReport();
		outstandingReport.setThirdPartyId(Integer.parseInt(id));
		outstandingReport.setThirdPartyName(name);
		outstandingReport.setThirdPartEmail(email);
		outstandingReport.setDate(date);
		outstandingReport.setTime(time);
		outstandingReport.setNotes(notes);
		outstandingReport.setCcEmail(ccEmail);
		outstandingReport.setClinicId(loginInfo.getId());
		
		int result = outstandingReportDAO.saveEmailSendDetails(outstandingReport);
		
		StringBuffer str = new StringBuffer();

	    String filePath = request.getRealPath("/Design/images/logo.png/");
		//Practitioner Mail
		str.append("<table width = '50%'> ");
		str.append("<tr>");
		str.append("<td colspan = '2'><img src=\"cid:image1\" width=\"90%\" height=\"50%\" /></td>");
		str.append("</tr>");
		str.append("<tr>");
		str.append("<td><b>"+"Hi,"+name+"</b></td> ");
		str.append("</tr>");
		str.append("<b>Account Credit Limit Exceed:</b>");

		str.append("<tr>");
		str.append("<td>Date:"+date+"</td>");
		str.append("<td>"+notes+"</td> ");
		str.append("</tr>");

		

		str.append("</table>");
		Map<String, String> inlineImages = new HashMap<String, String>();
	    inlineImages.put("image1", filePath);
		
		/*EmbeddedImageEmailUtil.send(email,subject, str.toString(), inlineImages);	
		EmbeddedImageEmailUtil.send(ccEmail,subject, str.toString(), inlineImages);	*/
		

		
	}
	catch(Exception e){
		e.printStackTrace();
	}finally{
		
		connection.close();
	}
	
	return null;
}

public String sendSaveMessage() throws SQLException{
	if(!verifyLogin(request)){
		return "login";
	}
	String id = request.getParameter("id");
	String name = request.getParameter("name");
	String contactno = request.getParameter("contactno");
	String notes = request.getParameter("notes");
	String date = request.getParameter("date");
	String time = request.getParameter("time");
	Connection connection = null;
	try{
		
		connection = Connection_provider.getconnection();
		OutstandingReportDAO outstandingReportDAO = new JDBCOutstandingReportDAO(connection);
		
		OutstandingReport outstandingReport = new OutstandingReport();
		outstandingReport.setThirdPartyId(Integer.parseInt(id));
		outstandingReport.setThirdPartyName(name);
		outstandingReport.setThirdPartyContactno(contactno);
		outstandingReport.setDate(date);
		outstandingReport.setTime(time);
		outstandingReport.setNotes(notes);
		outstandingReport.setClinicId(loginInfo.getId());
		
		int result = outstandingReportDAO.saveSmsSendDetails(outstandingReport);
		
		SmsService s = new SmsService();
		String templateid="";
        s.sendSms(notes, contactno, loginInfo, new EmailLetterLog(),templateid);
		
	}
	catch(Exception e){
		e.printStackTrace();
	}finally{
		
		connection.close();
	}
	return null;
}

public String callDataSave() throws Exception{
	if(!verifyLogin(request)){
		return "login";
	}
	String id = request.getParameter("id");
	String name = request.getParameter("name");
	String contactno = request.getParameter("contactno");
	String notes = request.getParameter("notes");
	String date = request.getParameter("date");
	String time = request.getParameter("time");
	Connection connection = null;
	try{
		
		connection = Connection_provider.getconnection();
		OutstandingReportDAO outstandingReportDAO = new JDBCOutstandingReportDAO(connection);
		
		OutstandingReport outstandingReport = new OutstandingReport();
		outstandingReport.setThirdPartyId(Integer.parseInt(id));
		outstandingReport.setThirdPartyName(name);
		outstandingReport.setThirdPartyContactno(contactno);
		outstandingReport.setDate(date);
		outstandingReport.setTime(time);
		outstandingReport.setNotes(notes);
		outstandingReport.setClinicId(loginInfo.getId());
		
		int result = outstandingReportDAO.saveCallRecordDetails(outstandingReport);
		
		
		
	}
	catch(Exception e){
		e.printStackTrace();
	}finally{
		
		connection.close();
	}
	return null;
}

public String getAllClientOfThirdParty() throws SQLException{
	if(!verifyLogin(request)){
		return "login";
	}
	String id = request.getParameter("id");
	Connection connection = null;
	Client client = new Client();
	ArrayList<Client> allPatientList = new ArrayList<Client>();
	try{
		connection = Connection_provider.getconnection();
		
		ClientDAO clientDAO = new JDBCClientDAO(connection);
		allPatientList = clientDAO.getAllClientOfThirdParty(id);
		
		StringBuffer str = new StringBuffer();
		str.append("<table class='table table-hover table-condensed table-bordered table-striped ' > ");
		str.append("<thead>");
		str.append("<tr class='bg-info'>");
		str.append("<th>Name</th> ");
		str.append("<th>Mobile</th> ");
		str.append("<th>Email</th> ");
		
		str.append("</tr>");
		str.append("</thead>");
		
		str.append("<tbody>");
		for(Client client1:allPatientList){
		String name = client1.getTitle()+" "+client1.getFirstName()+" "+client1.getLastName(); 		
		str.append("<tr>");
		String toDate = "";
		String fromDate="";
		str.append("<td><a href='Statement?clientId="+client1.getId()+"&payby=Third Party&client="+name+"&transactionType=All&location=All&thirdParty="+client1.getThirdPartyTypeName()+"&toDate="+toDate+"&fromDate="+fromDate+"'>"+name+"</td>");

		str.append("<td>"+client1.getMobNo()+"</td>");
		str.append("<td>"+client1.getEmail()+"</td>");
		str.append("</tr>");
		}
		str.append("</tbody>");
		str.append("</table>");
		response.setContentType("text/html");
		response.setHeader("Cache-Control", "no-cache");
		
		response.getWriter().write(""+str.toString()+""); 
		
	}
	catch (Exception e) {
		// TODO: handle exception
	}
	finally{
		connection.close();
	}
	
	return null;
	
}

public String showReport() throws Exception{
	if(!verifyLogin(request)){
		return "login";
	}
	
	String fromDate = outstandingReportForm.getFromDate();
	String toDate = outstandingReportForm.getToDate();	
	
	if(fromDate.equals("")){
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DATE, -7); 
		fromDate = dateFormat.format(cal.getTime());
		outstandingReportForm.setFromDate(fromDate);
	}
	if(toDate.equals("")){
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		Calendar cal = Calendar.getInstance();
		//cal.add(Calendar.DATE, -7); 
		toDate = dateFormat.format(cal.getTime());
		outstandingReportForm.setToDate(toDate);
	}
	
	
	if(!fromDate.equals("")){
		String temp[]= fromDate.split("/");
		fromDate = temp[2]+"-"+temp[1]+"-"+temp[0];
	}
	if(!toDate.equals("")){
		String temp1[]= toDate.split("/");
		toDate = temp1[2]+"-"+temp1[1]+"-"+temp1[0];
	}
	
	session.removeAttribute("collectInvoiceList");
	
	Connection connection = null;
	String payby = outstandingReportForm.getPayby();
	try{
		connection = Connection_provider.getconnection();
		ArrayList<OutstandingReport>outstandingReportListClient = new ArrayList<OutstandingReport>();
		ArrayList<OutstandingReport>outstandingReportListTP = new ArrayList<OutstandingReport>();
		


		OutstandingReportDAO outstandingReportDAO = new JDBCOutstandingReportDAO(connection);
		int totalCount = outstandingReportDAO.getTotalOutStandingCount(loginInfo.getId());
		pagination.setPreperties(totalCount);
		
		if(payby.equals(Constants.PAY_BY_CLIENT)){
			outstandingReportListClient = outstandingReportDAO.getOutStandingReportOfClientList(loginInfo.getId(),pagination,payby,outstandingReportForm.getClientId(),fromDate,toDate);
			outstandingReportForm.setOutstandingReportList(outstandingReportListClient);
		}
		else if(payby.equals(Constants.PAY_BY_THIRD_PARTY)){
			outstandingReportListTP = outstandingReportDAO.getOutstandingReportOfTPList(loginInfo.getId(),pagination,payby,outstandingReportForm.getThirdParty(),fromDate,toDate);
			outstandingReportForm.setOutstandingReportList(outstandingReportListTP);
		}
		else{
			
			outstandingReportForm.setClientId(null);
			outstandingReportForm.setThirdParty(null);
			outstandingReportForm.setClient("");
			
			outstandingReportListClient = outstandingReportDAO.getOutStandingReportOfClientList(loginInfo.getId(),pagination,"Client",outstandingReportForm.getClientId(),fromDate,toDate);
			outstandingReportListTP = outstandingReportDAO.getOutstandingReportOfTPList(loginInfo.getId(),pagination,"Third Party",outstandingReportForm.getThirdParty(),fromDate,toDate);
			ArrayList<OutstandingReport>outstandingReportListAll = new ArrayList<OutstandingReport>(outstandingReportListClient);
	       
			outstandingReportListAll.addAll(outstandingReportListTP);
			outstandingReportForm.setOutstandingReportList(outstandingReportListAll);
			
			
			
		}
		/*//pagination.setPage_records(outstandingReportList.size());
		//outstandingReportForm.setTotalRecords(totalCount);
		outstandingReportForm.setPagerecords(Integer.toString(pagination.getPage_records()));
		outstandingReportForm.setOutstandingReportList(outstandingReportList);*/
		
		ArrayList<OutstandingReport>outstandingLimitExceedList = new ArrayList<OutstandingReport>();

		/*for(OutstandingReport outstandingReport:outstandingReportList){
			
			double creditLmt = outstandingReport.getCreditWarningLimit();
			double unpaidamout = outstandingReport.getUnpaidAmout();
			if(unpaidamout>=creditLmt){
				outstandingLimitExceedList.add(outstandingReport);
			}
			
		}*/
		session.setAttribute("outstandingLimitExceedList", outstandingLimitExceedList);
	}
	catch(Exception e){
		e.printStackTrace();
	}finally{
		connection.close();
	}
	return "showList";
}

public String getClientList() throws Exception{
	if(!verifyLogin(request)){
		return "login";
	}
	String id = request.getParameter("id");
	Connection connection  = null;
	
	try{
		
		connection  = Connection_provider.getconnection();
		ArrayList<Client> allPatientList = new ArrayList<Client>();
		
		
		ClientDAO clientDAO = new JDBCClientDAO(connection);
		allPatientList = clientDAO.getAllClientOfThirdParty(id);
		
		StringBuffer str = new StringBuffer();
		str.append("<table class='table table-hover table-condensed table-bordered table-striped ' > ");
		str.append("<thead>");
		str.append("<tr class='bg-info'>");
		str.append("<th>Name</th> ");
		str.append("<th>Mobile</th> ");
		str.append("<th>Email</th> ");
		
		str.append("</tr>");
		str.append("</thead>");
		
		str.append("<tbody>");
		for(Client client1:allPatientList){
	
		String name = client1.getTitle()+" "+client1.getFirstName()+" "+client1.getLastName(); 		
		str.append("<tr>");

		str.append("<td>"+name+"<a href = 'javascript: void(0);' onclick = showInnerChildInvoiceDiv('divid_"+client1.getId()+"_"+id+"','"+client1.getId()+"','"+id+"');><i class='fa fa-arrow-down'></i></a></td>");

		str.append("<td>"+client1.getMobNo()+"</td>");
		str.append("<td>"+client1.getEmail()+"</td>");
		str.append("</tr>");
		
		str.append("<tr id = 'divid_"+client1.getId()+"_"+id+"' style='display: none'>");
		str.append("<td colspan='7' id = 'divid1_"+client1.getId()+"_"+id+"'></td>");
		str.append("</tr>");
		}
		str.append("</tbody>");
		str.append("</table>");
		response.setContentType("text/html");
		response.setHeader("Cache-Control", "no-cache");
		
		response.getWriter().write(""+str.toString()+""); 
	}
	catch(Exception e){
		e.printStackTrace();
	}finally{
		connection.close();
	}
	return null;
}

public String showChildInvoice() throws Exception{
	if(!verifyLogin(request)){
		return "login";
	}
	DateTimeUtils dateTimeUtils = new DateTimeUtils();
	String clientId = request.getParameter("clientId");
	String tpId = request.getParameter("tpId");
	Connection connection  = null;
	String payamtTrID = "paymentAmount_"+clientId+"_"+tpId;
	try{
		
		connection  = Connection_provider.getconnection();
		ArrayList<OutstandingReport> invoiceList = new ArrayList<OutstandingReport>();
		
		
		OutstandingReportDAO outstandingReportDAO = new JDBCOutstandingReportDAO(connection);
		
		int creditDuration = outstandingReportDAO.getCreditDurationDate(tpId);
		invoiceList = outstandingReportDAO.getPendingInvoiceList(clientId,tpId,creditDuration);
		int c1=1;
		double balanceTotal = 0;
		for(OutstandingReport outstandingReport:invoiceList){
			balanceTotal = outstandingReport.getBalance() + balanceTotal;
			//balanceTotal = Double.parseDouble(new DecimalFormat("##.##").format(balanceTotal));

		}
		StringBuffer str = new StringBuffer();
		String tableId = "tableId_"+tpId+"_"+clientId+"";
		str.append("<table class='table table-hover table-condensed table-bordered table-striped 'id = '"+tableId+"' > ");
		if(invoiceList.size()==0){
			str.append("<thead>");
			str.append("<tr class='bg-info'>");
			str.append("<th>No Pending Invoice</th> ");
			str.append("</tr>");
			str.append("</thead>");
		}
		else{
		str.append("<thead>");
		str.append("<tr class='bg-info'>");
		str.append("<th>Sr. No.<input type='checkbox' name='selectAlls_"+tpId+"_"+clientId+"' id='selectAlls_"+tpId+"_"+clientId+"' onclick=setSelectAll('"+tpId+"','"+clientId+"','"+dateTimeUtils.changeFormat(balanceTotal)+"','"+payamtTrID+"','"+tableId+"')></th> ");
		str.append("<th>Invoice No.</th> ");
		str.append("<th>Debit</th> ");
		str.append("<th>Credit</th> ");
		str.append("<th>Balance</th> ");
		
		str.append("</tr>");
		str.append("</thead>");
		
		str.append("<tbody>");
		String selectAllId = "selectAlls_"+tpId+"_"+clientId+"";
		for(OutstandingReport outstandingReport:invoiceList){
			String count = "";
			if(c1<=9){
			count = "0"+c1;
			}
			else{
				count =  ""+c1+"";
			}	
			Date currentDate = outstandingReport.getCurrentdate();
			Date invoiceDate = outstandingReport.getInvoiceDate();
			
			if(currentDate.compareTo(invoiceDate)>=0){
				str.append("<tr class='bg-danger'>");
				str.append("<td>"+count+" <input type='checkbox' value = "+outstandingReport.getInvoiceNo()+"/"+outstandingReport.getBalancex()+" id='ch-"+outstandingReport.getInvoiceNo()+"' name='ch-"+outstandingReport.getInvoiceNo()+"' onclick = addCredit(this.id,'"+outstandingReport.getInvoiceNo()+"','"+outstandingReport.getBalancex()+"','"+payamtTrID+"','"+dateTimeUtils.changeFormat(balanceTotal)+"','"+selectAllId+"')></td>");
				str.append("<td>0000"+outstandingReport.getInvoiceNo()+"</td>");
				str.append("<td>£ "+outstandingReport.getDebitx()+"</td>");
				if(outstandingReport.getDiscount()!=0){
					str.append("<td>£ "+outstandingReport.getCreditx()+" (Discount:"+outstandingReport.getDiscountx()+"%)</td>");
					}
					else{
						str.append("<td>£ "+outstandingReport.getCreditx()+"</td>");

					}				str.append("<td>£ "+outstandingReport.getBalancex()+"</td>");
				str.append("</tr>");
			}else{
				str.append("<tr>");
				str.append("<td>"+count+" <input type='checkbox' value = "+outstandingReport.getInvoiceNo()+"/"+outstandingReport.getBalancex()+" id='ch-"+outstandingReport.getInvoiceNo()+"' name='ch-"+outstandingReport.getInvoiceNo()+"' onclick = addCredit(this.id,'"+outstandingReport.getInvoiceNo()+"','"+outstandingReport.getBalancex()+"','"+payamtTrID+"','"+dateTimeUtils.changeFormat(balanceTotal)+"','"+selectAllId+"')></td>");
				str.append("<td>0000"+outstandingReport.getInvoiceNo()+"</td>");
				str.append("<td>£ "+outstandingReport.getDebitx()+"</td>");
				if(outstandingReport.getDiscount()!=0){
				str.append("<td>£ "+outstandingReport.getCreditx()+" (Discount:"+outstandingReport.getDiscountx()+"%)</td>");
				}
				else{
					str.append("<td>£ "+outstandingReport.getCreditx()+"</td>");

				}
				str.append("<td>£ "+outstandingReport.getBalancex()+"</td>");
				str.append("</tr>");
			}
		c1 = c1+1;
		}
		str.append("<tr>");
		str.append("<td></td>");
		str.append("<td></td>");
		str.append("<td></td>");
		str.append("<td></td>");
		str.append("<td><b>Total : £ "+dateTimeUtils.changeFormat(balanceTotal)+"</b></td>");
		str.append("</tr>");
		
		str.append("<tr>");
		str.append("<td></td>");
		str.append("<td></td>");
		str.append("<td></td>");
		str.append("<td></td>");
		str.append("<td><b id = '"+payamtTrID+"'>Payment Amount:£0.0</b>&nbsp;&nbsp;<select name='howpaid_"+tpId+"_"+clientId+"' id='howpaid_"+tpId+"_"+clientId+"' class='form-control' style = 'width:24%;display: inline;'>"
				+"<option value='0'>Select Payment Mode</option>"
				+"<option value='BACS'>BACS</option>"
				+"<option value='Cheque'>Cheque</option>"
				+"<option value='C/Card'>C/Card</option>"
				+"<option value='Cash'>Cash</option>"
				+"<option value='D/Card'>D/Card</option>"
				+"<option value='D/D'>D/D</option>"
				+"<option value='Other'>Other</option>"
				+"<option value='S/O'>S/O</option>"
				+ "</select>"
				+"&nbsp;&nbsp;<input type = 'button' value = 'Record Payment' id = 'btn_"+tpId+"_"+clientId+"' class = 'btn btn-primary' onclick = recordPayment('"+tpId+"','"+clientId+"','"+tableId+"')>"
				+"</td>");
		str.append("</tr>");
		
		}
		str.append("</tbody>");
		str.append("</table>");
		response.setContentType("text/html");
		response.setHeader("Cache-Control", "no-cache");
		
		response.getWriter().write(""+str.toString()+""); 

	}
	catch(Exception e){
		e.printStackTrace();
	}finally{
		connection.close();
	}
	return null;
	
}
public String showChildInvoiceInSession() throws Exception{
	if(!verifyLogin(request)){
		return "login";
	}
	String clientId = request.getParameter("clientId");
	String tpId = request.getParameter("tpId");
	Connection connection  = null;
	
	try{
		
		connection  = Connection_provider.getconnection();
		ArrayList<OutstandingReport> invoiceList = new ArrayList<OutstandingReport>();
		
		
		OutstandingReportDAO outstandingReportDAO = new JDBCOutstandingReportDAO(connection);
		
		int creditDuration = outstandingReportDAO.getCreditDurationDate(tpId);
		invoiceList = outstandingReportDAO.getPendingInvoiceList(clientId,tpId,creditDuration);
		String str = "";
		for(OutstandingReport o : invoiceList){
		str = str+"/" + o.getInvoiceNo();
		}
		
		response.setContentType("text/html");
		response.setHeader("Cache-Control", "no-cache");
		
		response.getWriter().write(""+str.toString()+""); 
		
	}
	catch(Exception e){
		e.printStackTrace();
	}finally{
		connection.close();
	}
		return null;
}
public String savePayment() throws Exception{
	if(!verifyLogin(request)){
		return "login";
	}
	String tpId = request.getParameter("tpId");
	String clientId = request.getParameter("clientId");
	String allCheck = request.getParameter("allCheck");
	String modeofPayment = request.getParameter("modeofPayment");
	String paymentTotal = request.getParameter("balanceTotal");
	String data = request.getParameter("data");
	String yes = "Yes";
	Connection connection  = null;
	
	try{
		
		connection  = Connection_provider.getconnection();
		OutstandingReportDAO outstandingReportDAO = new JDBCOutstandingReportDAO(connection);
		String temp[] = data.split(",");
		if(allCheck.equals(yes)){
			for(int i=2;i<temp.length-1;i++){
//				System.out.println(temp[i]);
				String temp1[] = temp[i].split("/");
				String invoiceNo = temp1[0];
				String payamount = temp1[1];
				
			//	int savePayment = outstandingReportDAO.savePayment(tpId,clientId,modeofPayment,invoiceNo,payamount);
				
				
			}
		}
		else{
			for(int i=1;i<temp.length-1;i++){
//				System.out.println(temp[i]);
				String temp1[] = temp[i].split("/");
				String invoiceNo = temp1[0];
				String payamount = temp1[1];
				//int savePayment = outstandingReportDAO.savePayment(tpId,clientId,modeofPayment,invoiceNo,payamount);
			}
		}
		int save = outstandingReportDAO.saveTPPayment(tpId,clientId,modeofPayment,paymentTotal);
		
		
	}
	catch(Exception e){
		e.printStackTrace();
	}finally{
		connection.close();
	}
	
	String payamtTrID = "paymentAmount_"+clientId+"_"+tpId;
	DateTimeUtils dateTimeUtils = new DateTimeUtils();
	try{
		
		connection  = Connection_provider.getconnection();
		ArrayList<OutstandingReport> invoiceList = new ArrayList<OutstandingReport>();
		
		
		OutstandingReportDAO outstandingReportDAO = new JDBCOutstandingReportDAO(connection);
		int creditDuration = outstandingReportDAO.getCreditDurationDate(tpId);
		invoiceList = outstandingReportDAO.getPendingInvoiceList(clientId,tpId,creditDuration);
		int c1=1;
		double balanceTotal = 0;
		for(OutstandingReport outstandingReport:invoiceList){

			balanceTotal = outstandingReport.getBalance() + balanceTotal;
			//balanceTotal = Double.parseDouble(new DecimalFormat("##.##").format(balanceTotal));

		}
		StringBuffer str = new StringBuffer();
		String tableId = "tableId_"+tpId+"_"+clientId+"";
		str.append("<table class='table table-hover table-condensed table-bordered table-striped 'id = '"+tableId+"' > ");
		if(invoiceList.size()==0){
			str.append("<thead>");
			str.append("<tr class='bg-info'>");
			str.append("<th>No Pending Invoice</th> ");
			str.append("</tr>");
			str.append("</thead>");
		}
		else{
		str.append("<thead>");
		str.append("<tr class='bg-info'>");
		str.append("<th>Sr. No.<input type='checkbox' name='selectAlls_"+tpId+"_"+clientId+"' id='selectAlls_"+tpId+"_"+clientId+"' onclick=setSelectAll('"+tpId+"','"+clientId+"','"+dateTimeUtils.changeFormat(balanceTotal)+"','"+payamtTrID+"','"+tableId+"')></th> ");
		str.append("<th>Invoice No.</th> ");
		str.append("<th>Debit</th> ");
		str.append("<th>Credit</th> ");
		str.append("<th>Balance</th> ");
		
		str.append("</tr>");
		str.append("</thead>");
		
		str.append("<tbody>");
		String selectAllId = "selectAlls_"+tpId+"_"+clientId+"";
		for(OutstandingReport outstandingReport:invoiceList){
			String count = "";
			if(c1<=9){
			count = "0"+c1;
			}
			else{
				count =  ""+c1+"";
			}	
		if(outstandingReport.getCurrentdate().compareTo(outstandingReport.getInvoiceDate())>=0){
			str.append("<tr class= 'bg-danger'>");
			str.append("<td>"+count+" <input type='checkbox' value = "+outstandingReport.getInvoiceNo()+"/"+outstandingReport.getBalancex()+" id='ch-"+outstandingReport.getInvoiceNo()+"' name='ch-"+outstandingReport.getInvoiceNo()+"' onclick = addCredit(this.id,'"+outstandingReport.getInvoiceNo()+"','"+outstandingReport.getBalancex()+"','"+payamtTrID+"','"+dateTimeUtils.changeFormat(balanceTotal)+"','"+selectAllId+"')></td>");
			str.append("<td>0000"+outstandingReport.getInvoiceNo()+"</td>");
			str.append("<td>£ "+outstandingReport.getDebitx()+"</td>");
			str.append("<td>£ "+outstandingReport.getCreditx()+"</td>");
			str.append("<td>£ "+outstandingReport.getBalancex()+"</td>");
			str.append("</tr>");
		}else{
			str.append("<tr>");
			str.append("<td>"+count+" <input type='checkbox' value = "+outstandingReport.getInvoiceNo()+"/"+outstandingReport.getBalancex()+" id='ch-"+outstandingReport.getInvoiceNo()+"' name='ch-"+outstandingReport.getInvoiceNo()+"' onclick = addCredit(this.id,'"+outstandingReport.getInvoiceNo()+"','"+outstandingReport.getBalancex()+"','"+payamtTrID+"','"+dateTimeUtils.changeFormat(balanceTotal)+"','"+selectAllId+"')></td>");
			str.append("<td>0000"+outstandingReport.getInvoiceNo()+"</td>");
			str.append("<td>£ "+outstandingReport.getDebitx()+"</td>");
			str.append("<td>£ "+outstandingReport.getCreditx()+"</td>");
			str.append("<td>£ "+outstandingReport.getBalancex()+"</td>");
			str.append("</tr>");
		}
		
		
		c1 = c1+1;
		}
		str.append("<tr>");
		str.append("<td></td>");
		str.append("<td></td>");
		str.append("<td></td>");
		str.append("<td></td>");
		str.append("<td><b>Total : £"+dateTimeUtils.changeFormat(balanceTotal)+"</b></td>");
		str.append("</tr>");
		
		str.append("<tr>");
		str.append("<td></td>");
		str.append("<td></td>");
		str.append("<td></td>");
		str.append("<td></td>");
		str.append("<td><b id = '"+payamtTrID+"'>Payment Amount:£ 0.0</b>&nbsp;&nbsp;<select name='howpaid_"+tpId+"_"+clientId+"' id='howpaid_"+tpId+"_"+clientId+"' class='form-control' style = 'width:24%;display: inline;'>"
				+"<option value='0'>Select Payment Mode</option>"
				+"<option value='BACS'>BACS</option>"
				+"<option value='Cheque'>Cheque</option>"
				+"<option value='C/Card'>C/Card</option>"
				+"<option value='Cash'>Cash</option>"
				+"<option value='D/Card'>D/Card</option>"
				+"<option value='D/D'>D/D</option>"
				+"<option value='Other'>Other</option>"
				+"<option value='S/O'>S/O</option>"
				+ "</select>"
				+"&nbsp;&nbsp;<input type = 'button' value = 'Record Payment' id = 'btn_"+tpId+"_"+clientId+"' class = 'btn btn-primary' onclick = recordPayment('"+tpId+"','"+clientId+"','"+tableId+"')>"
				+"</td>");
		str.append("</tr>");
		
		}
		str.append("</tbody>");
		str.append("</table>");
		response.setContentType("text/html");
		response.setHeader("Cache-Control", "no-cache");
		
		response.getWriter().write(""+str.toString()+""); 

	}
	catch(Exception e){
		e.printStackTrace();
	}finally{
		connection.close();
	}
	return null;
}

public String showClientSeflInvoice() throws Exception{
	if(!verifyLogin(request)){
		return "login";
	}
	String clientId = request.getParameter("clientId");
	Connection connection  = null;
	DateTimeUtils dateTimeUtils = new DateTimeUtils();
	try{
		
		connection  = Connection_provider.getconnection();
		ArrayList<OutstandingReport> invoiceList = new ArrayList<OutstandingReport>();
		
		
		OutstandingReportDAO outstandingReportDAO = new JDBCOutstandingReportDAO(connection);
		
		invoiceList = outstandingReportDAO.getPendingClientSelfInvoiceList(clientId);
		int c1=1;
		double balanceTotal = 0;
		for(OutstandingReport outstandingReport:invoiceList){
			balanceTotal = outstandingReport.getBalance() + balanceTotal;
			//balanceTotal = Double.parseDouble(new DecimalFormat("##.##").format(balanceTotal));
			

		}
		
		StringBuffer str = new StringBuffer();
		str.append("<table class='table table-hover table-condensed table-bordered table-striped '> ");
		if(invoiceList.size()==0){
			str.append("<thead>");
			str.append("<tr class='bg-info'>");
			str.append("<th>No Pending Invoice</th> ");
			str.append("</tr>");
			str.append("</thead>");
		}
		else{
		str.append("<thead>");
		str.append("<tr class='bg-info'>");
		str.append("<th>Invoice No.</th> ");
		str.append("<th>Type</th> ");
		str.append("<th>Debit</th> ");
		str.append("<th>Credit</th> ");
		str.append("<th>Balance</th> ");
		
		str.append("</tr>");
		str.append("</thead>");
		
		str.append("<tbody>");
		for(OutstandingReport outstandingReport:invoiceList){
			
		str.append("<tr>");
		str.append("<td>0000"+outstandingReport.getInvoiceNo()+"<a href = paymentProcessingAccount?payby=Client&numberOfChages="+outstandingReport.getNoOfCharges()+"&creditCharge="+outstandingReport.getCredit()+"&invoiceid="+outstandingReport.getInvoiceNo()+"&debitamt="+outstandingReport.getDebit()+"&balance="+outstandingReport.getBalance()+"&clientId="+clientId+">    Record Payment</a></td>");
		str.append("<td>"+outstandingReport.getChargeType()+"</td>");
		str.append("<td>Rs. "+outstandingReport.getDebitx()+"</td>");
		str.append("<td>Rs. "+outstandingReport.getCreditx()+" (Discount:"+outstandingReport.getDiscountx()+"%)</td>");
		str.append("<td>Rs. "+outstandingReport.getBalancex()+"</td>");
		str.append("</tr>");
		c1 = c1+1;
		}
		str.append("<tr>");
		
		str.append("<td></td>");
		str.append("<td></td>");
		str.append("<td></td>");
		str.append("<td></td>");
		str.append("<td><b>Total : Rs."+dateTimeUtils.changeFormat(balanceTotal)+"</b></td>");
		str.append("</tr>");
		
		
		
		}
		str.append("</tbody>");
		str.append("</table>");
		response.setContentType("text/html");
		response.setHeader("Cache-Control", "no-cache");
		
		response.getWriter().write(""+str.toString()+""); 

	}
	catch(Exception e){
		e.printStackTrace();
	}finally{
		connection.close();
	}
	return null;
}


public String collect() throws Exception{
	
	//var url = "collectOutstandingReport?invoicelist="+invoicelist+"";
	String invoicelist = request.getParameter("invoicelist");
	boolean status = Boolean.parseBoolean(request.getParameter("status"));
	
//	System.out.println(invoicelist);
	
	ArrayList<String>collectInvoiceList = new ArrayList<String>();
	
	if(session.getAttribute("collectInvoiceList")!=null){
		collectInvoiceList =  (ArrayList<String>) session.getAttribute("collectInvoiceList");
	}
	
	String temp[] = invoicelist.split(",");
	
	if(status==true){
		for(int i=1;i<temp.length;i++){
			if(!collectInvoiceList.contains(temp[i])){
				collectInvoiceList.add(temp[i]);
			}
			
		}
	}else{
		for(int i=1;i<temp.length;i++){
			if(collectInvoiceList.contains(temp[i])){
				collectInvoiceList.remove(temp[i]);
			}
			
		}
	}
	
	
	session.setAttribute("collectInvoiceList", collectInvoiceList);
	
//	System.out.println(collectInvoiceList);
	
	return null;
}



public String red() throws Exception{
	
	if(!verifyLogin(request)){
		return "login";
	}
	
	Connection connection = null;
	
	
	try{
		
		connection = Connection_provider.getconnection();
		AccountsDAO accountsDAO = new JDBCAccountsDAO(connection);
		OutstandingReportDAO outstandingReportDAO = new JDBCOutstandingReportDAO(connection);
		ClientDAO clientDAO = new JDBCClientDAO(connection);
		
		String payby =  (String)session.getAttribute("topPayby");
		String clientid = (String)session.getAttribute("topClientid");
		String tpid = (String)session.getAttribute("topTpid");
		
		String fromdate = (String)session.getAttribute("topfromdate");
		String todate = (String)session.getAttribute("toptodate");
		
		
		outstandingReportForm.setClientId(clientid);
		outstandingReportForm.setThirdParty(tpid);
		outstandingReportForm.setPayby(payby);
		
		ArrayList<OutstandingReport>outstandingReportListClient = new ArrayList<OutstandingReport>();
		ArrayList<OutstandingReport>outstandingReportListTP = new ArrayList<OutstandingReport>();
		
		if(payby.equals(Constants.PAY_BY_CLIENT)){
			outstandingReportListClient = outstandingReportDAO.getOutStandingReportOfClientList(loginInfo.getId(),pagination,payby,outstandingReportForm.getClientId(),fromdate,todate);
			outstandingReportForm.setOutstandingReportList(outstandingReportListClient);
			Client client = clientDAO.getSelectedSessionClientDetails(outstandingReportForm.getClientId());
			outstandingReportForm.setClient(client.getClientName());
		}
		 if(payby.equals(Constants.PAY_BY_THIRD_PARTY)){
			outstandingReportListTP = outstandingReportDAO.getOutstandingReportOfTPList(loginInfo.getId(),pagination,payby,outstandingReportForm.getThirdParty(),fromdate,todate);
			outstandingReportForm.setOutstandingReportList(outstandingReportListTP);
		}
		
		 outstandingReportForm.setMessage("Payment Done Sucessfully!!");
		 addActionMessage("Payment Done Sucessfully!!");
		 
	}catch(Exception e){
		e.printStackTrace();
	}finally{
		connection.close();
	}
	
	
	return "showList";
	
}

public String payment() throws Exception{
	
	if(!verifyLogin(request)){
		return "login";
	}
	
	Connection connection = null;
	
	
	try{
		
		connection = Connection_provider.getconnection();
		AccountsDAO accountsDAO = new JDBCAccountsDAO(connection);
		OutstandingReportDAO outstandingReportDAO = new JDBCOutstandingReportDAO(connection);
		ClientDAO clientDAO = new JDBCClientDAO(connection);
		
		ArrayList<String>collectInvoiceList = (ArrayList<String>)session.getAttribute("collectInvoiceList");
		
		Object[] ob = collectInvoiceList.toArray();
		
		StringBuffer str = new StringBuffer();
		
		String  tpId = "0";
		if(!outstandingReportForm.getThirdParty().equals("")){
			tpId = outstandingReportForm.getThirdParty();
		}
		
		
		for(int i=0;i<ob.length;i++){
			//str.append(ob[i].toString() + ",");
			String invoiceid = ob[i].toString();
			String payamount = request.getParameter(ob[i].toString());
			String clientId = outstandingReportDAO.getClientId(invoiceid);
			
			
			
			int savePayment = outstandingReportDAO.savePayment(tpId,clientId,outstandingReportForm.getHowpaid(),invoiceid,payamount,DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone()));
		}
		
		
		
		
		String fromDate = outstandingReportForm.getFromDate();
		String toDate = outstandingReportForm.getToDate();	
		
		if(fromDate.equals("")){
			DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
			Calendar cal = Calendar.getInstance();
			cal.add(Calendar.DATE, -7); 
			fromDate = dateFormat.format(cal.getTime());
			outstandingReportForm.setFromDate(fromDate);
		}
		if(toDate.equals("")){
			DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
			Calendar cal = Calendar.getInstance();
			//cal.add(Calendar.DATE, -7); 
			toDate = dateFormat.format(cal.getTime());
			outstandingReportForm.setToDate(toDate);
		}
		
		
		if(!fromDate.equals("")){
			String temp[]= fromDate.split("/");
			fromDate = temp[2]+"-"+temp[1]+"-"+temp[0];
		}
		if(!toDate.equals("")){
			String temp1[]= toDate.split("/");
			toDate = temp1[2]+"-"+temp1[1]+"-"+temp1[0];
		}


	
		String payby = outstandingReportForm.getPayby();
		session.setAttribute("topPayby", payby);
		session.setAttribute("topClientid", outstandingReportForm.getClientId());
		session.setAttribute("topTpid", outstandingReportForm.getThirdParty());
		
		session.setAttribute("topfromdate", fromDate);
		session.setAttribute("toptodate", toDate);
		
	
		
	}catch(Exception e){
		e.printStackTrace();
	}finally{
		connection.close();
	}
	
	return "gored";
}

public String createlist() throws SQLException{
	
	if(!verifyLogin(request)){
		return "login";
	}
	
	Connection connection = null;
	
	
	try{
		
		connection = Connection_provider.getconnection();
		AccountsDAO accountsDAO = new JDBCAccountsDAO(connection);
		OutstandingReportDAO outstandingReportDAO = new JDBCOutstandingReportDAO(connection);
		
		ArrayList<Clinic> locationAdressList = accountsDAO.getLocationAddress(loginInfo.getClinicid());

		Clinic clinic = new Clinic();
		ClinicDAO clinicDAO  = new JDBCClinicDAO(connection);
		clinic = clinicDAO.getCliniclistDetails(loginInfo.getClinicid());
		outstandingReportForm.setClinicName(clinic.getClinicName());
		outstandingReportForm.setClinicOwner(clinic.getClinicOwner());
		outstandingReportForm.setOwner_qualification(clinic.getOwner_qualification());
		outstandingReportForm.setLandLine(clinic.getLandLine());
		outstandingReportForm.setWebsiteUrl(clinic.getWebsiteUrl());
		outstandingReportForm.setLocationAdressList(locationAdressList);
		
		ArrayList<String>collectInvoiceList = (ArrayList<String>)session.getAttribute("collectInvoiceList");
		
		Object[] ob = collectInvoiceList.toArray();
		
		StringBuffer str = new StringBuffer();
		
		for(int i=0;i<ob.length;i++){
			str.append(ob[i].toString() + ",");
		}
		
		String invoiceList = "";
		
		if(str.length()>0){
			invoiceList = str.substring(0,str.length()-1);
		}
		
		ArrayList<Client>selectedClientList = outstandingReportDAO.getInvoicedClientidList(invoiceList);
		
		outstandingReportForm.setSelectedClientList(selectedClientList);
		
		//ArrayList<Accounts>selecttedInvoiceList = outstandingReportDAO.getSelectedInvoiceList(invoiceList);
		
		//outstandingReportForm.setSelecttedInvoiceList(selecttedInvoiceList);
		
		
		if(outstandingReportForm.getPayby().equalsIgnoreCase("Third Party")){
			int tpid = Integer.parseInt(outstandingReportForm.getThirdParty());
			ThirdParty thirdParty = new ThirdParty();
			thirdParty = accountsDAO.getTpDetails(tpid);
			
			outstandingReportForm.setPayeename(thirdParty.getCompanyName());
			//String address = thirdParty.getAddress()+"," +thirdParty.getTown() +" " +thirdParty.getPostcode();
			outstandingReportForm.setPayeeadress(thirdParty.getAddress());
			outstandingReportForm.setPayeeTown(thirdParty.getTown());
			outstandingReportForm.setPayeePostcode(thirdParty.getPostcode());
			outstandingReportForm.setPayeeEmail(thirdParty.getEmail());
			outstandingReportForm.setPayeeConatctNo(thirdParty.getTelephoneLine());
			
			
			
		}
		else{
			Client client = accountsDAO.getClientData(outstandingReportForm.getClientId());
			outstandingReportForm.setPayeename(client.getTitle() + " " + client.getFirstName() + " " + client.getLastName());
			outstandingReportForm.setPayeeadress(client.getAddress());
			outstandingReportForm.setPayeeTown(client.getTown());
			outstandingReportForm.setPayeePostcode(client.getPostCode());
			outstandingReportForm.setPayeeEmail(client.getEmail());
			outstandingReportForm.setPayeeConatctNo(client.getMobNo());
			
		}
		
		
		
	}catch(Exception e){
		e.printStackTrace();
	}finally{
		connection.close();
	}
	
	

	
	return "createlist";
}


public OutstandingReportForm getModel() {
	// TODO Auto-generated method stub
	return outstandingReportForm;
}


public void prepare() throws Exception {
	// TODO Auto-generated method stub
	
	Connection connection = null;
	
	try{
		
		connection = Connection_provider.getconnection();
		
			AccountsDAO accountsDAO = new JDBCAccountsDAO(connection);
		
			ArrayList<Accounts>thirdPartyList = accountsDAO.getThirdPartyList(loginInfo.getId());
			//	accountsForm.setLocationList(locationList);
			outstandingReportForm.setThirdPartyList(thirdPartyList);
			
			ArrayList<Master>banknamelist = accountsDAO.getBankNameList();
			outstandingReportForm.setBanknamelist(banknamelist);
		
		
	}catch(Exception e){
		e.printStackTrace();
	}finally{
		connection.close();
	}
	
	
}

//  31 jan 2018

public String getclientmoboremail() throws Exception{
	Connection connection = null;
	try {
		connection = Connection_provider.getconnection();
		String clientid = request.getParameter("clientid");
		String val = request.getParameter("val");
		ClientDAO clientDAO = new JDBCClientDAO(connection);
		Client client = clientDAO.getPatient(Integer.parseInt(clientid));
		
		String name = "";
		
		if(val.equals("0")){
			if(client.getMobNo()!=null){
				name= client.getMobNo(); 
			}
		}else{
			if(client.getEmail()!=null){
				name = client.getEmail();
			}
		}
		
		response.setContentType("text/html");
		response.setHeader("Cache-Control", "no-cache");
		response.getWriter().write(""+name+""); 
		
	} catch (Exception e) {
		e.printStackTrace();
	}finally {
		connection.close();
	}
	return null;
}




}
