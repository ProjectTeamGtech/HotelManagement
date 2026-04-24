package com.apm.Report.web.action;

import java.io.File;
import java.io.FileOutputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.allcolor.yahp.converter.CYaHPConverter;
import org.allcolor.yahp.converter.IHtmlToPdfTransformer;
import org.apache.struts2.ServletActionContext;

import com.apm.Accounts.eu.bi.AccountsDAO;
import com.apm.Accounts.eu.blogic.jdbc.JDBCAccountsDAO;
import com.apm.Accounts.eu.entity.Accounts;
import com.apm.DiaryManagement.eu.bi.NotAvailableSlotDAO;
import com.apm.DiaryManagement.eu.blogic.jdbc.JDBCNotAvailableSlotDAO;
import com.apm.DiaryManagement.eu.entity.Client;
import com.apm.DiaryManagement.eu.entity.DiaryManagement;
import com.apm.DiaryManagement.web.action.EmbeddedImageEmailUtil;
import com.apm.Log.eu.entity.EmailLetterLog;
import com.apm.Report.eu.bi.ChargesReportDAO;
import com.apm.Report.eu.bi.ClientReportDAO;
import com.apm.Report.eu.blogic.jdbc.JDBCChargesReportDAO;
import com.apm.Report.eu.blogic.jdbc.JDBCClientReportDAO;
import com.apm.Report.web.form.ChargesReportForm;
import com.apm.Report.web.form.ClientReportForm;
import com.apm.Report.web.form.ClinicalReportForm;
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

public class ClientReportAction  extends BaseAction implements Preparable,ModelDriven<ClientReportForm> {
	ClientReportForm clientReportForm = new ClientReportForm();
	HttpServletRequest request = (HttpServletRequest) ActionContext.getContext().get(ServletActionContext.HTTP_REQUEST);
	HttpServletResponse response = (HttpServletResponse)ActionContext.getContext().get(ServletActionContext.HTTP_RESPONSE);
	HttpSession session = request.getSession(true);
	LoginInfo loginInfo = LoginHelper.getLoginInfo(request);
	ChargesReportForm chargesReportForm = new ChargesReportForm();
	private Pagination pagination = new Pagination(10, 1);
	public Pagination getPagination() {
		return pagination;
	}


	public void setPagination(Pagination pagination) {
		this.pagination = pagination;
	}
	 String fromDate = chargesReportForm.getFromDate();
		String toDate = chargesReportForm.getToDate();	
	public String clientList() throws Exception{
		if(!verifyLogin(request)){
			return "login";
		}
		
		if(clientReportForm.getOrderby().equals("")){
			clientReportForm.setOrderby("clientid");
		}
		
		if(clientReportForm.getFromDate().equals("")){
			DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
			Calendar cal = Calendar.getInstance();
			//cal.add(Calendar.DATE, -7); 
			String checkDate = dateFormat.format(cal.getTime());
			clientReportForm.setFromDate(checkDate);
		}
		
		if(clientReportForm.getToDate().equals("")){
			DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
			Calendar cal = Calendar.getInstance();
			//cal.add(Calendar.DATE, -7); 
			String checkDate = dateFormat.format(cal.getTime());
			clientReportForm.setToDate(checkDate);
		}
		
		Connection connection = null;
		ArrayList<Client> clientList1 = new ArrayList<Client>();
		ArrayList<Client> clientList2 = new ArrayList<Client>();
		ArrayList<Client> clientselfpaymentList = new ArrayList<Client>();
		ArrayList<Client> clientTPpaymentList = new ArrayList<Client>();
		try{
			connection = Connection_provider.getconnection();
			ClientReportDAO clientReportDAO = new JDBCClientReportDAO(connection);
			ChargesReportDAO chargesReportDAO = new JDBCChargesReportDAO(connection);
			DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		      Calendar cal = Calendar.getInstance();
		      String date = dateFormat.format(cal.getTime());  
		     
			 int result = chargesReportDAO.saveMisReportLog("Patient List",loginInfo.getUserId(),fromDate,toDate,date,"clientList");
			
				/*
				 * clientList =
				 * clientReportDAO.getClientReportList(clientReportForm.getThirdParty(),
				 * clientReportForm.getLocation(),clientReportForm.getFromDate(),
				 * clientReportForm.getToDate(),clientReportForm.getPayby(),clientReportForm.
				 * getDiaryUser(),clientReportForm.getOrderby(),clientReportForm.getOrder());
				 * session.setAttribute("clientListReport", clientList);
				 * clientReportForm.setClientList(clientList);
				 */
			clientList1=clientReportDAO.getClientReportList1(clientReportForm.getThirdParty(),clientReportForm.getLocation(),clientReportForm.getFromDate(),clientReportForm.getToDate(),clientReportForm.getPayby(),clientReportForm.getDiaryUser(),clientReportForm.getOrderby(),clientReportForm.getOrder());
			session.setAttribute("clientListReport", clientList1);
			clientReportForm.setClientList(clientList1);
			String clientid="";
			if(clientList1.size()!=0){
				Client clientids=clientList1.get(clientList1.size()-1);
				String ids=""+clientids.getClient_ids();
				
				String temp[]=ids.split(",");
				for (String string : temp) {
					if(!string.equals(" ")){
						String data=string;
						 if(clientid.equals("")){
							 clientid=data;
						 }else{
							 clientid=clientid+", "+data;
						 }
					}
				  }
				}
			
			clientselfpaymentList=clientReportDAO.getClientselfpaymentList(clientReportForm.getFromDate(),clientReportForm.getToDate(),clientid);
			ArrayList<Client> selfdebitList=clientReportDAO.getClientSelfdebitlist(clientid);
			
			clientList2=clientReportDAO.getClientReportTPList(clientReportForm.getThirdParty(),clientReportForm.getLocation(),clientReportForm.getFromDate(),clientReportForm.getToDate(),clientReportForm.getPayby(),clientReportForm.getDiaryUser(),clientReportForm.getOrderby(),clientReportForm.getOrder());
			session.setAttribute("clientListReport", clientList2);
			clientReportForm.setClientList(clientList2);
			String clientid1="";
			if(clientList2.size()!=0){
				Client clientids=clientList1.get(clientList2.size()-1);
				String ids1=""+clientids.getClient_ids();
				
				String temp1[]=ids1.split(",");
				for (String string : temp1) {
					if(!string.equals(" ")){
						String data=string;
						 if(clientid1.equals("")){
							 clientid1=data;
						 }else{
							 clientid1=clientid1+", "+data;
						 }
					}
				  }
				}
			clientTPpaymentList=clientReportDAO.getClientTPpaymentList(clientReportForm.getFromDate(),clientReportForm.getToDate(),clientid1);
			ArrayList<Client> TPdebitList=clientReportDAO.getClientTPdebitlist(clientid1);
			ArrayList<Client> selfpaymentList=new ArrayList<Client>();
			ArrayList<Client> TPpaymentList1=new ArrayList<Client>();
			int i=0;
			int j=0;
			for (Client c1 : clientList1) {
				Client c2=clientselfpaymentList.get(i);
				//Client c3=selfdebitList.get(i);
				double balance=(c1.getTotalSelfCharges())-c2.getTotalSelfPaidAmt();
				c1.setTotalSelfPaidAmt(c2.getTotalSelfPaidAmt());
				c1.setTotalSelfBalnace(balance);
				i++;
				selfpaymentList.add(c1);
				
				
			}
			for (Client c3 : clientList2) {
				Client c4=clientTPpaymentList.get(j);
				Client c5=TPdebitList.get(j);
				double balance=(c3.getTotalTPCharges())-c4.getTotalTPPaidAmt();
				c3.setTotalTPPaidAmt(c4.getTotalTPPaidAmt());
				c3.setTotalTPBalance(balance);
				j++;
				TPpaymentList1.add(c3);
			}
			 selfpaymentList.addAll(TPpaymentList1);
			 clientReportForm.setClientList(selfpaymentList);
		}
		catch(Exception e){
			e.printStackTrace();
		}finally {
			connection.close();
		}
		return "clientListSuccess";
	}
	
	public String currentTreatmentNoFutureApmts() throws Exception{
		if(!verifyLogin(request)){
			return "login";
		}
		
		Connection connection = null;
		ArrayList<Client> currentTreatmentNoFutureApmtsList = new ArrayList<Client>();
		try{
			connection = Connection_provider.getconnection();
			ClientReportDAO clientReportDAO = new JDBCClientReportDAO(connection);
			
			ChargesReportDAO chargesReportDAO = new JDBCChargesReportDAO(connection);
			DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		      Calendar cal = Calendar.getInstance();
		      String date = dateFormat.format(cal.getTime());  
		     
			 int result = chargesReportDAO.saveMisReportLog("Current Treat With No Future Apmts",loginInfo.getUserId(),fromDate,toDate,date,"currentTreatmentNoFutureApmts");
			currentTreatmentNoFutureApmtsList = clientReportDAO.getCurrentTreatmentNoFutureApmtsList();
			clientReportForm.setCurrentTreatmentNoFutureApmtsList(currentTreatmentNoFutureApmtsList);
			
		}
		catch(Exception e){
			e.printStackTrace();
		}finally {
			connection.close();
		}
		return "currentTreatmentNoFutureApmts";
	}
	

	public String noApptActivityRecord() throws Exception{
		
		if(!verifyLogin(request)){
			return "login";
		}
		Connection connection = null;
		try{
			connection = Connection_provider.getconnection();
			ClientReportDAO clientReportDAO = new JDBCClientReportDAO(connection);
			
			int totalCount = clientReportDAO.getTotalNoApptActivityCount();
			pagination.setPreperties(totalCount);
			
			ArrayList<Client> noActivityRecordList = clientReportDAO.getNoActivityRecordOfClientList(pagination);
			
			pagination.setPage_records(noActivityRecordList.size());
			clientReportForm.setTotalRecords(totalCount);
			clientReportForm.setPagerecords(Integer.toString(pagination.getPage_records()));
			
			clientReportForm.setNoActivityRecordList(noActivityRecordList);
			session.setAttribute("pagination", pagination);
			
		}catch(Exception e){
			e.printStackTrace();
		}finally {
			connection.close();
		}
		return "noActivityApptRecord";
	}
	
	public String DNANoFutureApp() throws Exception{
		if(!verifyLogin(request)){
			return "login";
		}
		Connection connection = null;
		String  practitionerId = request.getParameter("diaryUser");
		
		try{
			connection = Connection_provider.getconnection();
			ClientReportDAO clientReportDAO = new JDBCClientReportDAO(connection);
			ArrayList<Client> DNANoFutureAppReport = clientReportDAO.getDNANoFutureAppReport(practitionerId);
			clientReportForm.setDNANoFutureAppReport(DNANoFutureAppReport);
			
		}catch(Exception e){
			e.printStackTrace();
		}finally {
			connection.close();
		}
		return "dnaNoFurureApp";
	}
	
	public String noActivityRecord() throws Exception{
		if(!verifyLogin(request)){
			return "login";
		}
		Connection connection = null;
		String  practitionerId = request.getParameter("diaryUser");
		try{
			connection = Connection_provider.getconnection();
			ClientReportDAO clientReportDAO = new JDBCClientReportDAO(connection);
			
			int totalCount = clientReportDAO.getTotalNoActivityCount(practitionerId);
			pagination.setPreperties(totalCount);
			
			ArrayList<Client> noActivityClientRecordList = clientReportDAO.getNoActivityRecordList(practitionerId,pagination);
			
			pagination.setPage_records(noActivityClientRecordList.size());
			clientReportForm.setTotalRecords(totalCount);
			clientReportForm.setPagerecords(Integer.toString(pagination.getPage_records()));
			
			clientReportForm.setNoActivityClientRecordList(noActivityClientRecordList);
			session.setAttribute("pagination", pagination);
			
		}catch(Exception e){
			e.printStackTrace();
		}finally {
			connection.close();
		}
		return "noActivityClientRecord";
	}
	
	public String saveAsPdfClientList() throws Exception{
		if(!verifyLogin(request)){
			return "login";
		}
		
		Connection connection = null;
		ArrayList<Client> clientList = new ArrayList<Client>();
		try{
			connection = Connection_provider.getconnection();
			ClientReportDAO clientReportDAO = new JDBCClientReportDAO(connection);
			clientList = (ArrayList<Client>)session.getAttribute("clientListReport");
			clientReportForm.setClientList(clientList);
			
			String currentDate = DateTimeUtils.getDateinSimpleFormate(new Date());
			String temp[] = currentDate.split(" ");
			String temptime[] = currentDate.split("at");
			String temp1[] = temp[0].split("-");
			String date = temp1[0] + "/" + temp1[1] + "/" + temp1[2];
			String time = temptime[1];
			StringBuffer str = new StringBuffer();
			str.append("<div style='font-size: 20px; font-weight: bold;'> Client List </div>");
			str.append("<div style='font-size: 10px; font-weight: bold;'> Date: "+date+" </div>");
			str.append("<div style='font-size: 10px; font-weight: bold;'> Time: "+time+" </div>");
			str.append("<br>");
			str.append("<table cellpadding='0' cellspacing='0' style='width: 100%;font-size: 10px;'>");
			str.append("<tr>");
			str.append("<th>Client No.</th>");
			str.append("<th>Practitioner</th>");
			str.append("<th>Name</th>");
			str.append("<th>Address</th>");
			str.append("<th>Town</th>");
			str.append("<th>County</th>");
			str.append("<th>PostCode</th>");
			str.append("<th>Contact No.</th>");
			str.append("<th>Email</th>");
			str.append("<th>DOB</th>");
			str.append("<th>No. Of Apmts</th>");
			str.append("<th>TP Charges</th>");
			str.append("<th>Self Charges</th>");
			str.append("<th>TP Paid Amt</th>");
			str.append("<th>Self Paid Amt</th>");
			str.append("<th>TP Balance</th>");
			str.append("<th>Self Balance</th>");
			str.append("</tr>");
			
			
			for(Client client : clientList){
				str.append("<tr>");
				str.append("<td>0000"+client.getId()+"</td>");
				str.append("<td>"+client.getDiaryUser()+"</td>");
				str.append("<td>"+client.getTitle()+ client.getFirstName()+ client.getMiddlename() +client.getLastName()+"</td>");
				str.append("<td>"+client.getAddress()+"</td>");
				str.append("<td>"+client.getTown()+"</td>");
				str.append("<td>"+client.getCounty()+"</td>");
				str.append("<td>"+client.getPostCode()+"</td>");
				str.append("<td>"+client.getMobNo()+"</td>");
				str.append("<td>"+client.getEmail()+"</td>");
				str.append("<td>"+client.getDob()+"</td>");
				str.append("<td>"+client.getNoApmts()+"</td>");
				str.append("<td>"+Constants.getCurrency(loginInfo)+ client.getTotalTPCharges()+"</td>");
				str.append("<td>"+Constants.getCurrency(loginInfo)+ client.getTotalSelfCharges()+"</td>");
				str.append("<td>"+Constants.getCurrency(loginInfo)+ client.getTotalTPPaidAmt()+"</td>");
				str.append("<td>"+Constants.getCurrency(loginInfo)+ client.getTotalSelfPaidAmt()+"</td>");
				str.append("<td>"+Constants.getCurrency(loginInfo)+ client.getTotalTPBalance()+"</td>");
				str.append("<td>"+Constants.getCurrency(loginInfo)+ client.getTotalSelfBalnace()+"</td>");



				str.append("</tr>");
				
			}
				str.append("</table>");
				String filePath = request.getRealPath("//liveData//ClientReport//");
				String filename = "ClientList.pdf";

				htmlToPdfFile(str.toString(), filePath, filename);
				session.setAttribute("clientListFileName", filePath+"/"+filename);
//				System.out.println("pdf done");
				response.setContentType("text/html");
				response.setHeader("Cache-Control", "no-cache");
				response.getWriter().write(""+filename+"");	
			
		}
		catch(Exception e){
			e.printStackTrace();
		}finally {
			connection.close();
		}
		return null;
	}
	public String sendMailClientList() throws Exception{
		if(!verifyLogin(request)){
			return "login";
		}
		Connection connection = null;
		try{
			
		connection = Connection_provider.getconnection();
		
		String to = request.getParameter("to");
		String cc = request.getParameter("cc");
		String subject = request.getParameter("subject");
		String notes = request.getParameter("emailBody");
		String file = request.getParameter("file");	
		String filename = (String)session.getAttribute("clientListFileName");		
		
		//int result = emailTemplateDAO.saveEmailLogDetails(to, cc, subject, notes, date1,time);
		
		String type = "Email";
		int appointmentid = 0;
		int invoiceid = 0;
		EmailLetterLog emailLetterLog = new EmailLetterLog();
		emailLetterLog.setAppointmentid(appointmentid);
		emailLetterLog.setInvoiceid(invoiceid);
		emailLetterLog.setType(type);
		
		EmbeddedImageEmailUtil.sendMailAttachment(connection,loginInfo.getId(),to, cc, subject, notes,filename,loginInfo,emailLetterLog,"0");
		
		
		}catch(Exception e){
			e.printStackTrace();
		}finally {
			connection.close();
		}
		return null;
	}
	public String saveAsPdfCurrentTreatNofutureApmts() throws Exception{
		if(!verifyLogin(request)){
			return "login";
		}
		
		Connection connection = null;
		ArrayList<Client> currentTreatmentNoFutureApmtsList = new ArrayList<Client>();
		try{
			connection = Connection_provider.getconnection();
			ClientReportDAO clientReportDAO = new JDBCClientReportDAO(connection);
			currentTreatmentNoFutureApmtsList = clientReportDAO.getCurrentTreatmentNoFutureApmtsList();
			clientReportForm.setCurrentTreatmentNoFutureApmtsList(currentTreatmentNoFutureApmtsList);
			
			String currentDate = DateTimeUtils.getDateinSimpleFormate(new Date());
			String temp[] = currentDate.split(" ");
			String temptime[] = currentDate.split("at");
			String temp1[] = temp[0].split("-");
			String date = temp1[0] + "/" + temp1[1] + "/" + temp1[2];
			String time = temptime[1];
			StringBuffer str = new StringBuffer();
			str.append("<div style='font-size: 20px; font-weight: bold;'> Client In Current Treatment With No Future Appointments </div>");
			str.append("<div style='font-size: 10px; font-weight: bold;'> Date: "+date+" </div>");
			str.append("<div style='font-size: 10px; font-weight: bold;'> Time: "+time+" </div>");
			str.append("<br>");
			str.append("<table cellpadding='0' cellspacing='0' style='width: 100%;font-size: 10px;'>");
			str.append("<tr>");
			str.append("<th>Client No.</th>");
			str.append("<th>Name</th>");
			str.append("<th>Address</th>");
			str.append("<th>Town</th>");
			str.append("<th>County</th>");
			str.append("<th>PostCode</th>");
			str.append("<th>Contact No.</th>");
			str.append("<th>Email</th>");
			str.append("<th>DOB</th>");
			str.append("</tr>");
			
			
			for(Client client : currentTreatmentNoFutureApmtsList){
				str.append("<tr>");
				str.append("<td>0000"+client.getId()+"</td>");
				str.append("<td>"+client.getTitle()+ client.getFirstName()+ client.getMiddlename() +client.getLastName()+"</td>");
				str.append("<td>"+client.getAddress()+"</td>");
				str.append("<td>"+client.getTown()+"</td>");
				str.append("<td>"+client.getCounty()+"</td>");
				str.append("<td>"+client.getPostCode()+"</td>");
				str.append("<td>"+client.getMobNo()+"</td>");
				str.append("<td>"+client.getEmail()+"</td>");
				str.append("<td>"+client.getDob()+"</td>");
				
				str.append("</tr>");
				
			}
				str.append("</table>");
				String filePath = request.getRealPath("//liveData//ClientReport//");
				String filename = "CurrentTreatmentNoFutureApmts.pdf";

				htmlToPdfFile1(str.toString(), filePath, filename);
				
				
				session.setAttribute("currentTreatmentNoFutureApmtsFileName", filePath+"/"+filename);
//				System.out.println("pdf done");
				response.setContentType("text/html");
				response.setHeader("Cache-Control", "no-cache");
				response.getWriter().write(""+filename+"");	
		}
		catch(Exception e){
			e.printStackTrace();
		}finally {
			connection.close();
		}
		return null;
	}
	public String sendCurrentTreatNofutureApmtsMail() throws Exception{
		
		if(!verifyLogin(request)){
			return "login";
		}
		Connection connection = null;
		try{
			
		connection = Connection_provider.getconnection();
		
		String to = request.getParameter("to");
		String cc = request.getParameter("cc");
		String subject = request.getParameter("subject");
		String notes = request.getParameter("emailBody");
		String file = request.getParameter("file");	
		String filename = (String)session.getAttribute("currentTreatmentNoFutureApmtsFileName");		
		
		//int result = emailTemplateDAO.saveEmailLogDetails(to, cc, subject, notes, date1,time);
		
		String type = "Email";
		int appointmentid = 0;
		int invoiceid = 0;
		EmailLetterLog emailLetterLog = new EmailLetterLog();
		emailLetterLog.setAppointmentid(appointmentid);
		emailLetterLog.setInvoiceid(invoiceid);
		emailLetterLog.setType(type);
		
		EmbeddedImageEmailUtil.sendMailAttachment(connection,loginInfo.getId(),to, cc, subject, notes,filename,loginInfo,emailLetterLog,"0");
		
		
		}catch(Exception e){
			e.printStackTrace();
		}finally {
			connection.close();
		}
		return null;
	}
	public String previewNoApptActivity() throws Exception{
		
		if(!verifyLogin(request)){
			return "login";
		}
		Connection connection = null;
		try{
			connection = Connection_provider.getconnection();
			ClientReportDAO clientReportDAO = new JDBCClientReportDAO(connection);
			
			int totalCount = clientReportDAO.getTotalNoApptActivityCount();
			pagination.setPreperties(totalCount);
			
			ArrayList<Client> noActivityRecordList = clientReportDAO.getNoActivityRecordOfClientList(pagination);			
			
			String currentDate = DateTimeUtils.getDateinSimpleFormate(new Date());
			String temp[] = currentDate.split(" ");
			String temptime[] = currentDate.split("at");
			String temp1[] = temp[0].split("-");
			String date = temp1[0] + "-" + temp1[1] + "-" + temp1[2];
			String time = temptime[1];
			int clientId = 0;
			
			StringBuffer str = new StringBuffer();
			str.append("<div style='font-size: 20px; font-weight: bold;'> No Appointment Activity Record Of Client </div>");
			str.append("<div style='font-size: 10px; font-weight: bold;'> Date: "+date+" </div>");
			str.append("<div style='font-size: 10px; font-weight: bold;'> Time: "+time+" </div>");
			str.append("<br>");
			
			str.append("<table cellpadding='0' cellspacing='0' style='width: 100%;font-size: 10px;'>");
			str.append("<tr>");
			
			str.append("<th>Sr.No.</th> ");
			str.append("<th>Client No.</th>");
			str.append("<th>Name</th>");
			str.append("<th>Address</th>");
			str.append("<th>Town</th>");
			str.append("<th>Country</th>");
			str.append("<th>PostCode</th>");
			str.append("<th>Contact No.</th>");
			str.append("<th>Email</th>");
			str.append("<th>DOB</th>");
			
			str.append("</tr>");
			str.append("</thead>");
			str.append("<tbody>");
			int srno = 1;		
			
			for(Client client : noActivityRecordList){
				str.append("<tr class= 'bg-info'>");
				str.append("<td>"+srno+"</td>");				
				str.append("<td>0000"+client.getId()+"</td>");
				str.append("<td>"+client.getTitle()+ client.getFirstName()+ client.getMiddlename() +client.getLastName()+"</td>");
				str.append("<td>"+client.getAddress()+"</td>");
				str.append("<td>"+client.getTown()+"</td>");
				str.append("<td>"+client.getCountry()+"</td>");
				str.append("<td>"+client.getPostCode()+"</td>");
				str.append("<td>"+client.getMobNo()+"</td>");
				str.append("<td>"+client.getEmail()+"</td>");
				str.append("<td>"+client.getDob()+"</td>");
				
				str.append("</tr>");
				clientId = client.getId();
				
			}
				str.append("</table>");
				String filePath = request.getRealPath("/liveData/NoApptActivityReport/");
//				System.out.println(filePath);
				String filename = "NoApptActivity_"+date+".pdf";
				//String filename ="commission.pdf";
				
				htmlToPdfFile2(str.toString(), filePath, filename);		
				
				session.setAttribute("NoApptActivityFileName", filePath+"/"+filename);
				
//				System.out.println("PDF Created..");
				
				response.setContentType("text/html");
				response.setHeader("Cache-Control", "no-cache");
				response.getWriter().write(""+filename+""); 
				
		}
		catch(Exception e){
			e.printStackTrace();
		}finally {
			connection.close();
		}
		return null;
	}
	
	public String sendEmailNoApptActivity() throws Exception{
		if(!verifyLogin(request)){
			return "login";
		}
		Connection connection = null;
		try{
			
		connection = Connection_provider.getconnection();
		
		String to = request.getParameter("to");
		String cc = request.getParameter("cc");
		String subject = request.getParameter("subject");
		String notes = request.getParameter("emailBody");
		String file = request.getParameter("file");	
		
		String filename = (String)session.getAttribute("NoApptActivityFileName");		
		
		//int result = emailTemplateDAO.saveEmailLogDetails(to, cc, subject, notes, date1,time);
		
		String type = "Email";
		int appointmentid = 0;
		int invoiceid = 0;
		EmailLetterLog emailLetterLog = new EmailLetterLog();
		emailLetterLog.setAppointmentid(appointmentid);
		emailLetterLog.setInvoiceid(invoiceid);
		emailLetterLog.setType(type);
		
		EmbeddedImageEmailUtil.sendMailAttachment(connection,loginInfo.getId(),to, cc, subject, notes,filename,loginInfo,emailLetterLog,"0");
		
		
		}catch(Exception e){
			e.printStackTrace();
		}finally {
			connection.close();
		}
		return null;
	}
	
	public String dnaNoFutureApptPreview() throws Exception{
		if(!verifyLogin(request)){
			return "login";
		}
		Connection connection = null;
		String practitionerId = request.getParameter("diaryUser");
		
		try{
			connection = Connection_provider.getconnection();
			ClientReportDAO clientReportDAO = new JDBCClientReportDAO(connection);
			ArrayList<Client> DNANoFutureAppReport = clientReportDAO.getDNANoFutureAppReport(practitionerId);
			
			String currentDate = DateTimeUtils.getDateinSimpleFormate(new Date());
			String temp[] = currentDate.split(" ");
			String temptime[] = currentDate.split("at");
			String temp1[] = temp[0].split("-");
			String date = temp1[0] + "-" + temp1[1] + "-" + temp1[2];
			String time = temptime[1];
			int clientId = 0;
			
			StringBuffer str = new StringBuffer();
			str.append("<div style='font-size: 20px; font-weight: bold;'> DNA With No Future Appointment Of Client </div>");
			str.append("<div style='font-size: 10px; font-weight: bold;'> Date: "+date+" </div>");
			str.append("<div style='font-size: 10px; font-weight: bold;'> Time: "+time+" </div>");
			str.append("<br>");
			
			str.append("<table cellpadding='0' cellspacing='0' style='width: 100%;font-size: 10px;'>");
			str.append("<tr>");
			
			str.append("<th>Sr.No.</th> ");
			str.append("<th>Client No.</th>");
			str.append("<th>Name</th>");
			str.append("<th>Address</th>");
			str.append("<th>Town</th>");
			str.append("<th>Country</th>");
			str.append("<th>PostCode</th>");
			str.append("<th>Contact No.</th>");
			str.append("<th>Email</th>");
			str.append("<th>DOB</th>");
			
			str.append("</tr>");
			str.append("</thead>");
			str.append("<tbody>");
			int srno = 1;		
			
			for(Client client : DNANoFutureAppReport){
				str.append("<tr class= 'bg-info'>");
				str.append("<td>"+srno+"</td>");				
				str.append("<td>0000"+client.getId()+"</td>");
				str.append("<td>"+client.getTitle()+ client.getFirstName()+ client.getMiddlename() +client.getLastName()+"</td>");
				str.append("<td>"+client.getAddress()+"</td>");
				str.append("<td>"+client.getTown()+"</td>");
				str.append("<td>"+client.getCountry()+"</td>");
				str.append("<td>"+client.getPostCode()+"</td>");
				str.append("<td>"+client.getMobNo()+"</td>");
				str.append("<td>"+client.getEmail()+"</td>");
				str.append("<td>"+client.getDob()+"</td>");
				
				str.append("</tr>");
				clientId = client.getId();
				
			}
				str.append("</table>");
				String filePath = request.getRealPath("/liveData/dnaNoFutureApptReport/");
//				System.out.println(filePath);
				String filename = "DNANoFutureAppt_"+date+".pdf";  
				//String filename ="commission.pdf";
				
				htmlToPdfFile2(str.toString(), filePath, filename);				
				
				session.setAttribute("dnaNoFutureApptFileName", filePath+"/"+filename);
				
//				System.out.println("PDF Created..");
				
				response.setContentType("text/html");
				response.setHeader("Cache-Control", "no-cache");
				response.getWriter().write(""+filename+""); 
				
		}
		catch(Exception e){
			e.printStackTrace();
		}finally {
			connection.close();
		}
		return null;
	}
	
	public String sendEmaildnaNoFutureAppt(){
		if(!verifyLogin(request)){
			return "login";
		}
		Connection connection = null;
		try{
			
		connection = Connection_provider.getconnection();
		
		String to = request.getParameter("to");
		String cc = request.getParameter("cc");
		String subject = request.getParameter("subject");
		String notes = request.getParameter("emailBody");
		String file = request.getParameter("file");	
		
		String filename = (String)session.getAttribute("dnaNoFutureApptFileName");		
		
		//int result = emailTemplateDAO.saveEmailLogDetails(to, cc, subject, notes, date1,time);
		String type = "Email";
		int appointmentid = 0;
		int invoiceid = 0;
		EmailLetterLog emailLetterLog = new EmailLetterLog();
		emailLetterLog.setAppointmentid(appointmentid);
		emailLetterLog.setInvoiceid(invoiceid);
		emailLetterLog.setType(type);
		
		EmbeddedImageEmailUtil.sendMailAttachment(connection,loginInfo.getId(),to, cc, subject, notes,filename,loginInfo,emailLetterLog,"0");
		
		
		}catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}
	
	public String previewNoActivity(){

		if(!verifyLogin(request)){
			return "login";
		}
		Connection connection = null;
		String practitionerId = request.getParameter("diaryUser");
		try{
			connection = Connection_provider.getconnection();
			ClientReportDAO clientReportDAO = new JDBCClientReportDAO(connection);
			ArrayList<Client> noActivityClientRecordList = clientReportDAO.getNoActivityRecordList(practitionerId);			
			
			String currentDate = DateTimeUtils.getDateinSimpleFormate(new Date());
			String temp[] = currentDate.split(" ");
			String temptime[] = currentDate.split("at");
			String temp1[] = temp[0].split("-");
			String date = temp1[0] + "-" + temp1[1] + "-" + temp1[2];
			String time = temptime[1];
			int clientId = 0;
			
			StringBuffer str = new StringBuffer();
			str.append("<div style='font-size: 20px; font-weight: bold;'> No Activity Record Of Client </div>");
			str.append("<div style='font-size: 10px; font-weight: bold;'> Date: "+date+" </div>");
			str.append("<div style='font-size: 10px; font-weight: bold;'> Time: "+time+" </div>");
			str.append("<br>");
			
			str.append("<table cellpadding='0' cellspacing='0' style='width: 100%;font-size: 10px;'>");
			str.append("<tr>");
			
			str.append("<th>Sr.No.</th> ");
			str.append("<th>Client No.</th>");
			str.append("<th>Name</th>");
			str.append("<th>Address</th>");
			str.append("<th>Town</th>");
			str.append("<th>Country</th>");
			str.append("<th>PostCode</th>");
			str.append("<th>Contact No.</th>");
			str.append("<th>Email</th>");
			str.append("<th>DOB</th>");
			str.append("<th>Gender</th>");
			
			str.append("</tr>");
			str.append("</thead>");
			str.append("<tbody>");
			int srno = 1;		
			
			for(Client client : noActivityClientRecordList){
				str.append("<tr class= 'bg-info'>");
				str.append("<td>"+srno+"</td>");				
				str.append("<td>0000"+client.getId()+"</td>");
				str.append("<td>"+client.getTitle()+ client.getFirstName()+ client.getMiddlename() +client.getLastName()+"</td>");
				str.append("<td>"+client.getAddress()+"</td>");
				str.append("<td>"+client.getTown()+"</td>");
				str.append("<td>"+client.getCountry()+"</td>");
				str.append("<td>"+client.getPostCode()+"</td>");
				str.append("<td>"+client.getMobNo()+"</td>");
				str.append("<td>"+client.getEmail()+"</td>");
				str.append("<td>"+client.getDob()+"</td>");
				str.append("<td>"+client.getGender()+"</td>");
				
				str.append("</tr>");
				clientId = client.getId();
				
			}
				str.append("</table>");
				String filePath = request.getRealPath("/liveData/NoActivityReport/");
//				System.out.println(filePath);
				String filename = "NoActivity_"+date+".pdf";
				//String filename ="commission.pdf";
				
				htmlToPdfFile2(str.toString(), filePath, filename);		
				
				session.setAttribute("NoActivityFileName", filePath+"/"+filename);
				
//				System.out.println("PDF Created..");
				
				response.setContentType("text/html");
				response.setHeader("Cache-Control", "no-cache");
				response.getWriter().write(""+filename+""); 
				
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}
	
	public String sendEmailNoActivity() throws Exception{
		if(!verifyLogin(request)){
			return "login";
		}
		Connection connection = null;
		try{
			
		connection = Connection_provider.getconnection();
		
		String to = request.getParameter("to");
		String cc = request.getParameter("cc");
		String subject = request.getParameter("subject");
		String notes = request.getParameter("emailBody");
		String file = request.getParameter("file");	
		
		String filename = (String)session.getAttribute("NoActivityFileName");		
		
		//int result = emailTemplateDAO.saveEmailLogDetails(to, cc, subject, notes, date1,time);
		
		String type = "Email";
		int appointmentid = 0;
		int invoiceid = 0;
		EmailLetterLog emailLetterLog = new EmailLetterLog();
		emailLetterLog.setAppointmentid(appointmentid);
		emailLetterLog.setInvoiceid(invoiceid);
		emailLetterLog.setType(type);
		
		EmbeddedImageEmailUtil.sendMailAttachment(connection,loginInfo.getId(),to, cc, subject, notes,filename,loginInfo,emailLetterLog,"0");
		
		
		}catch(Exception e){
			e.printStackTrace();
		}finally {
			connection.close();
		}
		return null;
	}
	
	
	public void htmlToPdfFile2(String htmlString, String filepath,
			String fileaName) throws Exception {	

		CYaHPConverter converter = new CYaHPConverter();
		File fout = new File(filepath + "/" + fileaName);
		FileOutputStream out = new FileOutputStream(fout);
		Map properties = new HashMap();
		ArrayList headerFooterList = new ArrayList();

		properties.put(IHtmlToPdfTransformer.PDF_RENDERER_CLASS,IHtmlToPdfTransformer.FLYINGSAUCER_PDF_RENDERER);
		// properties.put(IHtmlToPdfTransformer.FOP_TTF_FONT_PATH, fontPath);
		converter.convertToPdf(htmlString, IHtmlToPdfTransformer.A4L,(java.util.List) headerFooterList, "file:///temp/", out, properties);
		out.flush();
		out.close();
	}
	
	
	public void htmlToPdfFile(String htmlString, String filepath,
			String fileaName) throws Exception {	
		
		

		CYaHPConverter converter = new CYaHPConverter();
		File fout = new File(filepath + "/" + fileaName);
		FileOutputStream out = new FileOutputStream(fout);
		Map properties = new HashMap();
		ArrayList headerFooterList = new ArrayList();

		properties.put(IHtmlToPdfTransformer.PDF_RENDERER_CLASS,IHtmlToPdfTransformer.FLYINGSAUCER_PDF_RENDERER);
		// properties.put(IHtmlToPdfTransformer.FOP_TTF_FONT_PATH, fontPath);
		converter.convertToPdf(htmlString, IHtmlToPdfTransformer.A3L,(java.util.List) headerFooterList, "file:///temp/", out, properties);
		out.flush();
		out.close();
	}
	public void htmlToPdfFile1(String htmlString, String filepath,
			String fileaName) throws Exception {	
		
		

		CYaHPConverter converter = new CYaHPConverter();
		File fout = new File(filepath + "/" + fileaName);
		FileOutputStream out = new FileOutputStream(fout);
		Map properties = new HashMap();
		ArrayList headerFooterList = new ArrayList();

		properties.put(IHtmlToPdfTransformer.PDF_RENDERER_CLASS,IHtmlToPdfTransformer.FLYINGSAUCER_PDF_RENDERER);
		// properties.put(IHtmlToPdfTransformer.FOP_TTF_FONT_PATH, fontPath);
		converter.convertToPdf(htmlString, IHtmlToPdfTransformer.A4P,(java.util.List) headerFooterList, "file:///temp/", out, properties);
		out.flush();
		out.close();
	}

	public ClientReportForm getModel() {
		// TODO Auto-generated method stub
		return clientReportForm;
	}

	public void prepare() throws Exception {
		
		
		
	Connection connection  = null;
		
		try{
			
			connection  = Connection_provider.getconnection();
			AccountsDAO accountsDAO = new JDBCAccountsDAO(connection);
			
			NotAvailableSlotDAO notAvailableSlotDAO = new JDBCNotAvailableSlotDAO(connection);		
			ArrayList<DiaryManagement>userList = notAvailableSlotDAO.getUserList(loginInfo.getId());		
			clientReportForm.setUserList(userList);
			
			//ArrayList<Accounts>locationList = accountsDAO.getLocationList(loginInfo.getClinicid());
			ArrayList<Accounts>thirdPartyList = accountsDAO.getThirdPartyList(loginInfo.getId());
			ArrayList<Accounts>locationList = accountsDAO.getLocationList(loginInfo.getClinicid());
			clientReportForm.setLocationList(locationList);
		//	accountsForm.setLocationList(locationList);
			clientReportForm.setThirdPartyList(thirdPartyList);
			
		}
		catch(Exception e){
			e.printStackTrace();
		}finally {
			connection.close();
		}
		
	}
	
	
	public String smslogReport() throws Exception {
		Connection connection=null;
		try {
			connection=Connection_provider.getconnection();
			ClientReportDAO clientReportDAO = new JDBCClientReportDAO(connection);
			String fromDate = clientReportForm.getFromDate();
			String toDate = clientReportForm.getToDate();
			String patientname=clientReportForm.getPatientname();
			String mobile=clientReportForm.getMobno();
			
			if (fromDate == null) {
				DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
				Calendar cal = Calendar.getInstance();
				// cal.add(Calendar.DATE, -7);
				fromDate = dateFormat.format(cal.getTime());
				fromDate = DateTimeUtils.getCommencingDate1(fromDate);
			} else {
				if (fromDate.equals("")) {
					DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
					Calendar cal = Calendar.getInstance();
					// cal.add(Calendar.DATE, -7);
					fromDate = dateFormat.format(cal.getTime());
					fromDate = DateTimeUtils.getCommencingDate1(fromDate);
					// fromdate = null;
				} else {
					fromDate = DateTimeUtils.getCommencingDate1(fromDate);
				}
			}

			if (toDate == null) {
				DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
				Calendar cal = Calendar.getInstance();
				toDate = dateFormat.format(cal.getTime());
				toDate = DateTimeUtils.getCommencingDate1(toDate);
			} else {
				if (toDate.equals("")) {
					DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
					Calendar cal = Calendar.getInstance();
					toDate = dateFormat.format(cal.getTime());
					toDate = DateTimeUtils.getCommencingDate1(toDate);
					// todate = null;
				} else {
					toDate = DateTimeUtils.getCommencingDate1(toDate);
				}
			}
			clientReportForm.setFromDate(DateTimeUtils.getCommencingDate1(fromDate));
			clientReportForm.setToDate(DateTimeUtils.getCommencingDate1(toDate));
			
			ArrayList<Client>smsloglist=clientReportDAO.getsmsLogReportlist(fromDate,toDate,patientname,mobile);
			clientReportForm.setSmsloglist(smsloglist);
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			connection.close();
		}
		return "smslogrpt";
	}

}
