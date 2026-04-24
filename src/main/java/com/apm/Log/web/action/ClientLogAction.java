package com.apm.Log.web.action;

import java.sql.Connection;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;
import org.aspectj.apache.bcel.classfile.Constant;

import com.apm.DiaryManagement.eu.bi.ClientDAO;
import com.apm.DiaryManagement.eu.bi.NotAvailableSlotDAO;
import com.apm.DiaryManagement.eu.blogic.jdbc.JDBCClientDAO;
import com.apm.DiaryManagement.eu.blogic.jdbc.JDBCNotAvailableSlotDAO;
import com.apm.DiaryManagement.eu.entity.NotAvailableSlot;
import com.apm.Log.eu.bi.LogDAO;
import com.apm.Log.eu.blogic.jdbc.JDBCLogDAO;
import com.apm.Log.eu.entity.Cancelled;
import com.apm.Log.eu.entity.DNA;
import com.apm.Log.eu.entity.LogDetail;
import com.apm.Log.web.form.LogForm;
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


public class ClientLogAction extends BaseAction implements Preparable, ModelDriven<LogForm> {
	
	HttpServletRequest request = (HttpServletRequest) ActionContext.getContext().get(ServletActionContext.HTTP_REQUEST);
	HttpServletResponse response = (HttpServletResponse)ActionContext.getContext().get(ServletActionContext.HTTP_RESPONSE);
	HttpSession session = request.getSession(true);
	LogForm logForm = new LogForm();
	private Pagination pagination = new Pagination(7, 1);
	LoginInfo loginInfo = LoginHelper.getLoginInfo(request);
	DateTimeUtils dateTimeUtils = new DateTimeUtils();
public String execute() throws Exception {
		
		if(!verifyLogin(request)){
			return "login";
		}
		Connection connection = null;
		try{
			connection = Connection_provider.getconnection();
		String id = request.getParameter("id");
		session.setAttribute("clientLogId", id);
		ClientDAO clientDAO = new JDBCClientDAO(connection);
		String fullname = clientDAO.getClientFullName(id);
		logForm.setClient(fullname);
		logForm.setClientSearchLog(fullname);
		
		//session.setAttribute("clientLogName", fullname);
		
			
		
		}catch (Exception e) {
			e.printStackTrace();
		}finally{
			
			connection.close();
		}
	 
		return "clientLogPageSucess";
	}


public String tmentepisodecount(){
	
	if(!verifyLogin(request)){
		return "login";
	}
	Connection connection = null;
	try{
		
		//var url = "tmentepisodecountClientLog?whopay="+whopay+"&treatmentEpisodeid="+treatmentEpisodeid+"&clientId="+clientId+" ";
		
		String clientId = request.getParameter("clientId");
		String treatmentEpisodeid = request.getParameter("treatmentEpisodeid");
		String whopay = request.getParameter("whopay");
		
		int apptid = Integer.parseInt(request.getParameter("apptid"));
		
//		System.out.println(apptid);
		
		connection = Connection_provider.getconnection();
		LogDAO logDAO = new JDBCLogDAO(connection);
		NotAvailableSlotDAO notAvailableSlotDAO = new JDBCNotAvailableSlotDAO(connection);
		
		if(apptid!=0){
			NotAvailableSlot notAvailableSlot = notAvailableSlotDAO.getTreatmentDetails(apptid);
			clientId = notAvailableSlot.getClientId();
			whopay = notAvailableSlot.getWhopay();
			treatmentEpisodeid = notAvailableSlot.getTreatmentEpisodeId();
		}
		
		ArrayList<NotAvailableSlot>treatmentEpisodeCountList = logDAO.getTreatmentEpisodeCountList(clientId,treatmentEpisodeid,whopay);
		
		StringBuffer str = new StringBuffer();
		
		str.append("<table class='table table-hove table-bordered table-striped table-condensed'> ");
		str.append("<thead>");
		str.append("<tr>");
		str.append("<th>Appointment Date</th> ");
		str.append("<th>Appt ID</th> ");
		str.append("<th>Appt Status</th> ");
		str.append("<th>Session Count </th> ");
		
		str.append("</tr>");
		str.append("</thead>");
		
		String tpepisodeName = "";
		String condition = "";
		String totalSession = "";
		
		str.append("<tbody>");
		for(NotAvailableSlot notAvailableSlot : treatmentEpisodeCountList){
		str.append("<tr>");
		str.append("<td>"+notAvailableSlot.getCommencing()+"</td>");
		str.append("<td>"+notAvailableSlot.getId()+"</td>");
		
		
		if(notAvailableSlot.isDnaOffset()){
			str.append("<td style='color:red'>DNA(O)</td>");
		}else{
			if(notAvailableSlot.isDna()){
				str.append("<td style='color:red'>DNA</td>");
			}else{
				str.append("<td>COMPLETED</td>");
			}
			
			str.append("<td style='text-align:center;'>"+notAvailableSlot.getUsedsession()+"</td>");
			
			tpepisodeName = notAvailableSlot.getTreatmentEpisodeName();
			condition  = notAvailableSlot.getCondition();
			totalSession = notAvailableSlot.getSessions();
		}
		
		str.append("</tr>");
		}
		str.append("</tbody>");
		str.append("</table>");
		
		String titlename = "Treatment Name" + " " + tpepisodeName + " " + "For Condition" + " " + condition + "  " +  "| No. of Consultation Limit " + " " + totalSession + " " + "Paid by" + " " + whopay;
		str.append( "<input type='hidden' name='ttlname' id='ttlname' value='"+titlename+"'>" );
		
		response.setContentType("text/html");
		response.setHeader("Cache-Control", "no-cache");
		
		response.getWriter().write(""+str.toString()+""); 
	
		
		
		
	}catch(Exception e){
		e.printStackTrace();
	}
	
	return null;
}

public String showTreatmentEpisode() throws SQLException{
	if(!verifyLogin(request)){
		return "login";
	}
	Connection connection = null;
	try{
		
		String clientId = request.getParameter("clientId");
		connection = Connection_provider.getconnection();
		LogDAO logDAO = new JDBCLogDAO(connection);
	
		ArrayList<LogDetail> treatEpisodeList = logDAO.getTreatmentEpisodeList(clientId);
		StringBuffer str = new StringBuffer();
		
		str.append("<table class='table table-hove table-bordered table-striped table-condensed'> ");
		str.append("<thead>");
		str.append("<tr>");
		str.append("<th><i class='fa fa-calendar'></i>Date</th> ");
		str.append("<th>Type</th> ");
		str.append("<th>TreatmentEpisode Name</th> ");
		str.append("<th><i class='fa fa-user'></i>Practitioner</th> ");
		str.append("<th>Report Sent date</th> ");
		str.append("<th>Report Status</th> ");
		str.append("</tr>");
		str.append("</thead>");
		
		str.append("<tbody>");
		for(LogDetail log:treatEpisodeList){
		str.append("<tr>");
		str.append("<td>"+log.getDate()+"</td>");
		str.append("<td>"+"Treatment"+"</td>");
		str.append("<td>"+log.getHeading()+"</td>");
		str.append("<td>"+log.getPractitioner()+"</td>");
		str.append("<td>"+log.getSentDate()+"</td>");
		str.append("<td>"+log.getReposrtStatus()+"</td>");
		str.append("</tr>");
		}
		str.append("</tbody>");
		str.append("</table>");
		response.setContentType("text/html");
		response.setHeader("Cache-Control", "no-cache");
		
		response.getWriter().write(""+str.toString()+""); 
			
		
	}catch (Exception e) {
		e.printStackTrace();
	}
	finally{
		connection.close();
	}
	return null;
}

/*public String showAccountCharges () throws SQLException{
	if(!verifyLogin(request)){
		return "login";
	}
	Connection connection = null;
	try{
		
		String clientId = request.getParameter("clientId");
		connection = Connection_provider.getconnection();
		LogDAO logDAO = new JDBCLogDAO(connection);
	
		ArrayList<LogDetail> accChargeList = logDAO.getAccChargeList(clientId);
		StringBuffer str = new StringBuffer();
		
		str.append("<table class='table table-hove table-bordered table-striped table-condensed' > ");
		str.append("<thead>");
		str.append("<tr>");
		str.append("<th>Invoice No.</th> ");
		//str.append("<th>Charge</th> ");
		str.append("<th>Practitioner</th> ");
		str.append("<th>Invoice Date</th> ");
		str.append("<th>Charge Type</th> ");
		str.append("<th>Status</th> ");
		str.append("<th>Date & Time</th> ");
		str.append("</tr>");
		str.append("</thead>");
		
		str.append("<tbody>");
		for(LogDetail log:accChargeList){
		str.append("<tr>");
		str.append("<td>0000"+log.getInvoiceNo()+"</td>");
		//str.append("<td>"+"Charges"+"</td>");
		str.append("<td>"+log.getPractitioner()+"</td>");
		str.append("<td>"+log.getDate()+"</td>");
		str.append("<td>"+log.getChargeType()+"</td>");
		str.append("<td>"+log.getStatus()+"</td>");
		str.append("<td>"+log.getInvoiceDate()+"</td>");
		str.append("</tr>");
		}
		str.append("</tbody>");
		str.append("</table>");
		response.setContentType("text/html");
		response.setHeader("Cache-Control", "no-cache");
		
		response.getWriter().write(""+str.toString()+""); 
			
		
	}catch (Exception e) {
		e.printStackTrace();
	}
	finally{
		connection.close();
	}
	return null;
}*/

/*public String showAccountInvoice() throws SQLException{
	if(!verifyLogin(request)){
		return "login";
	}
	Connection connection = null;
	try{
		
		String clientId = request.getParameter("clientId");
		connection = Connection_provider.getconnection();
		LogDAO logDAO = new JDBCLogDAO(connection);
	
		ArrayList<LogDetail> accChargeList = logDAO.getAccInvoiceList(clientId);
		StringBuffer str = new StringBuffer();
		
		str.append("<table class='table table-hove table-bordered table-striped table-condensed' > ");
		str.append("<thead>");
		str.append("<tr>");
		str.append("<th>Date</th> ");
		str.append("<th>Invoice</th> ");
		str.append("<th>Payee</th> ");
		str.append("<th>Charge</th> ");
		str.append("<th>Deliver Status</th> ");
		str.append("<th>PDF</th> ");
		str.append("</tr>");
		str.append("</thead>");
		
		str.append("<tbody>");
		for(LogDetail log:accChargeList){
		str.append("<tr>");
		str.append("<td>"+log.getDate()+"</td>");
		str.append("<td>0000"+log.getInvoiceNo()+"</td>");
		str.append("<td>"+log.getPayby()+" ("+log.getPayeename()+") </td>");
		str.append("<td>"+log.getTotal()+"</td>");
		str.append("<td>"+log.getDeliverstatus()+"</td>");
		String filename = log.getInvoiceNo()+"_"+log.getClientname()+".pdf";
		str.append("<td class = 'text-center'><a class = 'text-danger' href='liveData/invoiceData/"+filename+"' target='blank'><i class='fa fa-file-pdf-o'></i></a></td>");

		str.append("</tr>");
		}
		str.append("</tbody>");
		str.append("</table>");
		response.setContentType("text/html");
		response.setHeader("Cache-Control", "no-cache");
		
		response.getWriter().write(""+str.toString()+""); 
			
		
	}catch (Exception e) {
		e.printStackTrace();
	}
	finally{
		connection.close();
	}
	return null;
}
*/
public String showAppointment() throws Exception{
	if(!verifyLogin(request)){
		return "login";
	}
	Connection connection = null;
	try{
		
		String clientId = request.getParameter("clientId");
		String text = request.getParameter("txt");
		String action = request.getParameter("action");
		String order = request.getParameter("order");
		String orderby  = request.getParameter("orderby");
		String fromdate=request.getParameter("fromdate");
		String todate=request.getParameter("todate");
		if(fromdate==null){
			fromdate="";
		}
		if(todate==null){
			todate="";
		}
		if(fromdate.equals("")||todate.equals("")){
			Calendar calendar=Calendar.getInstance();
			SimpleDateFormat dateFormat=new SimpleDateFormat("dd-MM-yyyy");
			 todate=dateFormat.format(calendar.getTime());
			
			
			 fromdate=dateFormat.format(calendar.getTime());
			}
		fromdate=fromdate.replaceAll("/", "-");
		todate=todate.replaceAll("/", "-");
		fromdate=DateTimeUtils.getCommencingDate1(fromdate);
		todate=DateTimeUtils.getCommencingDate1(todate);
		connection = Connection_provider.getconnection();
		LogDAO logDAO = new JDBCLogDAO(connection);
	
		ArrayList<LogDetail> accChargeList = logDAO.getAppointmentList(clientId,text,action,orderby,order,fromdate,todate);
		StringBuffer str = new StringBuffer();
		
		str.append("<table class='table table-bordered table-hover table-condensed' > ");
		str.append("<thead>");
		
		str.append("<tr>");
		str.append("<th> Apmt Id</th> ");
		str.append("<th><a href='#' title='sort by appointment date' onclick='showApmtOrder()'>Apmt Date</a></th> ");
		str.append("<th>Apmt Time</th> ");
		/*str.append("<th style = 'width:7%'><i class='fa fa-check-square-o'></i>Action</th> ");*/
		str.append("<th>Apmt Name</th> ");
		str.append("<th>Practitioner</th> ");
		str.append("<th>Department</th> ");
		if(loginInfo.getUserType() == 2){

		str.append("<th>Charges</th> ");
		}
		str.append("<th>Modified By</th> ");
		str.append("<th>Modified Date/Time</th> ");
		str.append("<th>Apmt Status</th> ");
		
		
		str.append("</tr>");
		str.append("</thead>");
		
		str.append("<tbody>");
		for(LogDetail log:accChargeList){
			
		str.append("<tr>");
		String heading = " ";
		String practitioner = " ";
		String comencing = "";
		heading = log.getHeading();
		practitioner = log.getPractitioner();
		if(heading == null){
			heading = "";
		}
		if(practitioner == null){
			practitioner = "";
		}
		
		
		if(log.getApmtDate()!=null){
			if(!log.getApmtDate().equals("")){
				comencing = dateTimeUtils.changeDateFormatToddmmyyyy(log.getApmtDate());
			}
		}
		
		str.append("<td>"+log.getApmtId()+"</td>");
		str.append("<td>"+comencing+"</td>");
		str.append("<td>"+log.getApmtStartTime()+"</td>");
		
		int apmtId = log.getApmtId();
		boolean isCompleted = logDAO.checkAppointmentCompleted(apmtId);
		boolean isDNA = logDAO.checkApmtIsDNA(apmtId);
		String status = null;
		 status = log.getDeleteStatus();
		 if(status == null){
			 status = " ";
		 }
		String del = "Cancelled";
		/*if(status.equalsIgnoreCase(del))
		{
			str.append("<td>"+"(Cancelled)"+log.getCancelApmtsNotes()+"</td>");

		}
		else{
			if(isCompleted){
				str.append("<td>"+"<a href = 'Accounts?clientId="+clientId+"&clientName="+log.getClientname()+"&payby="+log.getPayby()+"&location="+log.getApmtLocation()+"' target = 'blank' title = 'Create Invoice/Process Charge'> <img src='popicons/complete.ico' alt='...' style = 'height: 20px;width: 20px;'> </a> | <a href = 'Statement?clientId="+clientId+"&clientName="+log.getClientname()+"&payby="+log.getPayby()+"&location="+log.getApmtLocation()+"' target = 'blank' title = 'View Accounts'> <img src='popicons/view_acount.ico' alt='...' style = 'height: 20px;width: 20px;'> </a></td>");

			}
			else{
				str.append("<td>"+"<a href = 'redirectToPendingCompleteApmt?practitionerId="+log.getPractiitonerId()+"&location="+log.getApmtLocation()+"&apmtDate="+log.getApmtDate()+"' target = 'blank' title = 'Complete Appointmnet'> <img src='popicons/cbs.ico' alt='...' style = 'height: 20px;width: 20px;'> </a> | <a href = 'Statement?clientId="+clientId+"&clientName="+log.getClientname()+"&payby="+log.getPayby()+"&location="+log.getApmtLocation()+"' target = 'blank' title = 'View Accounts'> <img src='popicons/view_acount.ico' alt='...' style = 'height: 20px;width: 20px;'> </a></td>");

			}*/
		//}
		str.append("<td>"+heading+"</td>");
		str.append("<td>"+practitioner+"</td>");
		if(log.getApmtLocation()!=null){
			str.append("<td>"+log.getApmtLocation()+"</td>");
		}else{
			str.append("<td></td>");
		}
		
		if(loginInfo.getUserType() == 2){
		str.append("<td>"+Constants.getCurrency(loginInfo)+log.getTotal()+"</td>");
		}
		str.append("<td>"+log.getAddedBy()+"</td>");
		str.append("<td>"+DateTimeUtils.getIndianDateTimeFormat(log.getDate())+ "</td>");
		
		/*if(log.getStatus().equalsIgnoreCase("Completed") || log.getStatus().equalsIgnoreCase("Completed Modified")){
			str.append("<td style = 'color: Green;'>"+log.getStatus()+"</td>");

		}
		else if(log.getStatus().equalsIgnoreCase("Cancelled")){
			str.append("<td style = 'color: Red;'>"+log.getStatus()+" ("+log.getCancelApmtsNotes()+")</td>");

		}
		else if(log.getStatus().equalsIgnoreCase("DNA")){
			str.append("<td style = 'color: Red;'>"+log.getStatus()+"</td>");
		}
		else{
			str.append("<td style = 'color : #FFC200'>"+log.getStatus()+"</td>");

		}
		*/
		
		//str.append("<td style = 'color : #FFC200'>"+log.getStatus()+" <a href='javascript: void(0);' onclick='showAllStatus("+log.getApmtId()+","+log.getModiAppintmentList().size()+","+log.getDnaAppointmentList().size()+","+log.getCompletedAppointmentList().size()+","+log.getCmAppointmentList().size()+","+log.getCanceledAppointmentList().size()+")'><i class='fa fa-arrow-down'></i></a></td>");
		String title = "View History";
		if(log.getDnaAppointmentList().size()==0){
			title = "No History";
		}
		String color = "#FFC200";
		if(log.getStatus().equals("DNA") || log.getStatus().equals("Cancelled")){
			color = "Red";
		}
		if(log.getStatus().equals("Completed") || log.getStatus().equals("Completed Modified")){
			color = "green";
		}
		
		String logstatus = log.getStatus();
		if(logstatus.equals("Cancelled")){
			if(!log.getCancelApmtsNotes().equals("")){
				logstatus = logstatus +" <i class='fa fa-file-text-o' style='color:#000;cursor:pointer;' title='View Cancel Note' onclick='showcancelnote("+log.getId()+")'></i>";
			}
		}
			
		str.append("<td style = 'color : "+color+"'>"+logstatus+" <a href='javascript: void(0);' onclick='showAllStatus("+log.getApmtId()+","+log.getDnaAppointmentList().size()+")'><i class='fa fa-arrow-down' title='"+title+"'></i></a></td>");
		
		/*if(log.getStatus().equals("Modified")){
			str.append("<td style = 'color : #FFC200'>"+log.getStatus()+"</td>");
		}
		
		else if(log.getStatus().equals("DNA")){
			str.append("<td style = 'color : Red'>"+log.getStatus()+"</td>");
		}
		
		else if(log.getStatus().equals("Completed")){
			str.append("<td style = 'color : green'>"+log.getStatus()+"</td>");
		}
		
		else if(log.getStatus().equals("Completed Modified")){
			str.append("<td style = 'color : green'>"+log.getStatus()+"</td>");
		}
		
		else if(log.getStatus().equals("Cancelled")){
			str.append("<td style = 'color : Red'>"+log.getStatus()+"</td>");
		}
		else{
			str.append("<td style = 'color : #FFC200'>"+log.getStatus()+"</td>");
		}
		*/
		
		
		
		str.append("</tr>");
		
		
		
		
		//DNA list
		int d = 0;
		

		if(log.getDnaAppointmentList().size() > 0){
			for(DNA data : log.getDnaAppointmentList()){
				
				str.append("<tr id='"+d+"d"+log.getApmtId()+"' style='display:none;  background-color: rgb(249, 249, 16)'>");
				
				heading = log.getHeading();
				practitioner = log.getPractitioner();
				if(heading == null){
					heading = "";
				}
				if(practitioner == null){
					practitioner = "";
				}
				str.append("<td>"+data.getApmtId()+"</td>");
				str.append("<td>"+dateTimeUtils.changeDateFormatToddmmyyyy(data.getApmtDate())+"</td>");
				str.append("<td>"+data.getApmtStartTime()+"</td>");
				
				
				 status = log.getDeleteStatus();
				 if(status == null){
					 status = " ";
				 }
				
			/*	if(status.equalsIgnoreCase(del))
				{
					str.append("<td>"+"(Cancelled)"+data.getCancelApmtsNotes()+"</td>");

				}
				else{
					if(isCompleted){
						str.append("<td>"+"<a href = 'Accounts?clientId="+clientId+"&clientName="+log.getClientname()+"&payby="+log.getPayby()+"&location="+log.getApmtLocation()+"' target = 'blank' title = 'Create Invoice/Process Charge'> <img src='popicons/complete.ico' alt='...' style = 'height: 20px;width: 20px;'> </a> | <a href = 'Statement?clientId="+clientId+"&clientName="+log.getClientname()+"&payby="+log.getPayby()+"&location="+log.getApmtLocation()+"' target = 'blank' title = 'View Accounts'> <img src='popicons/view_acount.ico' alt='...' style = 'height: 20px;width: 20px;'> </a></td>");

					}
					else{
						str.append("<td>"+"<a href = 'redirectToPendingCompleteApmt?practitionerId="+log.getPractiitonerId()+"&location="+log.getApmtLocation()+"&apmtDate="+log.getApmtDate()+"' target = 'blank' title = 'Complete Appointmnet'> <img src='popicons/cbs.ico' alt='...' style = 'height: 20px;width: 20px;'> </a> | <a href = 'Statement?clientId="+clientId+"&clientName="+log.getClientname()+"&payby="+log.getPayby()+"&location="+log.getApmtLocation()+"' target = 'blank' title = 'View Accounts'> <img src='popicons/view_acount.ico' alt='...' style = 'height: 20px;width: 20px;'> </a></td>");

					}*/
				//}
				str.append("<td>"+heading+"</td>");
				str.append("<td>"+practitioner+"</td>");
				if(log.getApmtLocation()!=null){
					str.append("<td>"+log.getApmtLocation()+"</td>");
				}else{
					str.append("<td></td>");
				}
				if(loginInfo.getUserType() == 2){
				str.append("<td>"+Constants.getCurrency(loginInfo)+log.getTotal()+"</td>");
				}
				str.append("<td>"+data.getAddedBy()+"</td>");
				str.append("<td>"+DateTimeUtils.getIndianDateTimeFormat(data.getDate())+ "</td>");
				
				color = "#FFC200";
				if(data.getStatus().equals("DNA") || data.getStatus().equals("Cancelled")){
					color = "Red";
				}
				if(data.getStatus().equals("Completed") || data.getStatus().equals("Completed Modified")){
					color = "green";
				}
				
				logstatus = data.getStatus();
				if(logstatus.equals("Cancelled")){
					if(log.getCancelApmtsNotes()!=null){
						logstatus = logstatus +" "+  "("+log.getCancelApmtsNotes()+")";
					}
				}
				
				str.append("<td style = 'color : "+color+"'>"+data.getStatus()+"</td>");
				str.append("</tr>");
				
				d++;
				
			}
		}

		
		
	
		
		}
		
		fromdate=DateTimeUtils.getCommencingDate2(fromdate);
		todate=DateTimeUtils.getCommencingDate2(todate);
		str.append("</tbody>");
		str.append("</table>");
		str.append("~@"+fromdate+"~@"+todate);
		response.setContentType("text/html");
		response.setHeader("Cache-Control", "no-cache");
		
		response.getWriter().write(""+str.toString()+""); 
			
		
	}catch (Exception e) {
		e.printStackTrace();
	}
	finally{
		connection.close();
	}
	return null;
}

public String showPastAppointment() throws SQLException{
	if(!verifyLogin(request)){
		return "login";
	}
	Connection connection = null;
	try{
		
		String clientId = request.getParameter("clientId");
		String text = request.getParameter("txt");
		String action = request.getParameter("action");
		String orderby = request.getParameter("orderby");
		String order = request.getParameter("order");
		
		connection = Connection_provider.getconnection();
		LogDAO logDAO = new JDBCLogDAO(connection);
		String currentDate = DateTimeUtils.getDateinSimpleFormate(new Date());
		String datetemp[] = currentDate.split(" ");
		String temp1[] = datetemp[0].split("-");
		String date = temp1[2] + "-" + temp1[1] + "-" + temp1[0];
		
		ArrayList<LogDetail> accChargeList = logDAO.getPastAppointmentList(clientId,date,text,action,orderby,order);
		StringBuffer str = new StringBuffer();
		
		str.append("<table class='table table-hove table-bordered table-striped table-condensed' > ");
		str.append("<thead>");
		str.append("<tr>");
		str.append("<th> Apmt Id</th> ");
		str.append("<th><a href='#' title='sort by appointment date' onclick='showPastApmtOrder()'>Apmt Date</a></th> ");
		str.append("<th>Apmt Time</th> ");
		/*str.append("<th style = 'width:7%'><i class='fa fa-check-square-o'></i>Action</th> ");*/
		str.append("<th>Apmt Name</th> ");
		str.append("<th>Practitioner</th> ");
		str.append("<th>Department</th> ");
		if(loginInfo.getUserType() == 2){

		str.append("<th>Charges</th> ");
		}
		str.append("<th>Modified By</th> ");
		str.append("<th>Modified Date/Time</th> ");
		str.append("<th>Apmt Status</th> ");
		
		
		str.append("</tr>");
		str.append("</thead>");
		
		str.append("<tbody>");
		for(LogDetail log:accChargeList){
			
		str.append("<tr>");
		String heading = " ";
		String practitioner = " ";
		heading = log.getHeading();
		practitioner = log.getPractitioner();
		if(heading == null){
			heading = "";
		}
		if(practitioner == null){
			practitioner = "";
		}
		str.append("<td>"+log.getApmtId()+"</td>");
		str.append("<td>"+dateTimeUtils.changeDateFormatToddmmyyyy(log.getApmtDate())+"</td>");
		str.append("<td>"+log.getApmtStartTime()+"</td>");
		
		int apmtId = log.getApmtId();
		boolean isCompleted = logDAO.checkAppointmentCompleted(apmtId);
		boolean isDNA = logDAO.checkApmtIsDNA(apmtId);
		String status = null;
		 status = log.getDeleteStatus();
		 if(status == null){
			 status = " ";
		 }
		String del = "Cancelled";
		/*if(status.equalsIgnoreCase(del))
		{
			str.append("<td>"+"(Cancelled)"+log.getCancelApmtsNotes()+"</td>");
		}*/
		/*else{
			if(isCompleted){
				str.append("<td>"+"<a href = 'Accounts?clientId="+clientId+"&clientName="+log.getClientname()+"&payby="+log.getPayby()+"&location="+log.getApmtLocation()+"' target = 'blank' title = 'Create Invoice/Process Charge'> <img src='popicons/complete.ico' alt='...' style = 'height: 20px;width: 20px;'> </a> | <a href = 'Statement?clientId="+clientId+"&clientName="+log.getClientname()+"&payby="+log.getPayby()+"&location="+log.getApmtLocation()+"' target = 'blank' title = 'View Accounts'> <img src='popicons/view_acount.ico' alt='...' style = 'height: 20px;width: 20px;'> </a></td>");

			}
			else{
				str.append("<td>"+"<a href = 'redirectToPendingCompleteApmt?practitionerId="+log.getPractiitonerId()+"&location="+log.getApmtLocation()+"&apmtDate="+log.getApmtDate()+"' target = 'blank' title = 'Complete Appointmnet'> <img src='popicons/cbs.ico' alt='...' style = 'height: 20px;width: 20px;'> </a> | <a href = 'Statement?clientId="+clientId+"&clientName="+log.getClientname()+"&payby="+log.getPayby()+"&location="+log.getApmtLocation()+"' target = 'blank' title = 'View Accounts'> <img src='popicons/view_acount.ico' alt='...' style = 'height: 20px;width: 20px;'> </a></td>");

			}
		}*/
		str.append("<td>"+heading+"</td>");
		str.append("<td>"+practitioner+"</td>");
		if(log.getApmtLocation()!=null){
			str.append("<td>"+log.getApmtLocation()+"</td>");
		}else{
			str.append("<td></td>");
		}
		if(loginInfo.getUserType() == 2){

		str.append("<td>"+Constants.getCurrency(loginInfo)+log.getTotal()+"</td>");
		}
		str.append("<td>"+log.getAddedBy()+"</td>");
		str.append("<td>"+DateTimeUtils.getIndianDateTimeFormat(log.getDate())+ "</td>");
		
		
		
		

		
		String color = "#FFC200";
		if(log.getStatus().equals("DNA") || log.getStatus().equals("Cancelled")){
			color = "Red";
		}
		if(log.getStatus().equals("Completed") || log.getStatus().equals("Completed Modified")){
			color = "green";
		}
		
		String logstatus = log.getStatus();
		if(logstatus.equals("Cancelled")){
			if(!log.getCancelApmtsNotes().equals("")){
				logstatus = logstatus +" "+  "("+log.getCancelApmtsNotes()+")";
				
			}
		}
		
		str.append("<td style = 'color: "+color+";'>"+logstatus+"</td>");
	
		
		
		str.append("</tr>");
		}
		str.append("</tbody>");
		str.append("</table>");
		response.setContentType("text/html");
		response.setHeader("Cache-Control", "no-cache");
		
		response.getWriter().write(""+str.toString()+"");  
			
		
	}catch (Exception e) {
		e.printStackTrace();
	}
	finally{
		connection.close();
	}
	return null;
}
public String showFutureAppointment() throws SQLException{
	if(!verifyLogin(request)){
		return "login";
	}
	Connection connection = null;
	try{
		
		String clientId = request.getParameter("clientId");
		String text = request.getParameter("txt");
		connection = Connection_provider.getconnection();
		LogDAO logDAO = new JDBCLogDAO(connection);
		String currentDate = DateTimeUtils.getDateinSimpleFormate(new Date());
		String datetemp[] = currentDate.split(" ");
		String temp1[] = datetemp[0].split("-");
		String date = temp1[2] + "-" + temp1[1] + "-" + temp1[0];
		ArrayList<LogDetail> accChargeList = logDAO.getFutureAppointmentList(clientId,date,text);
		StringBuffer str = new StringBuffer();
		
		str.append("<table class='table table-hove table-bordered table-striped table-condensed' > ");
		str.append("<thead>");
		str.append("<tr>");
		str.append("<th>Apmt Date</th> ");
		str.append("<th>Apmt Time</th> ");
		str.append("<th style = 'width:7%'>Action</th> ");
		str.append("<th>Apmt Type</th> ");
		str.append("<th>Practitioner</th> ");
		str.append("<th>Department</th> ");
		if(loginInfo.getUserType() == 2){

		str.append("<th>Charges</th> ");
		}
		str.append("<th>Modified By</th> ");
		str.append("<th>Modified Date/Time</th> ");
		str.append("<th>Apmt Status</th> ");
		
		
		str.append("</tr>");
		str.append("</thead>");
		
		str.append("<tbody>");
		for(LogDetail log:accChargeList){
			
		str.append("<tr>");
		String heading = " ";
		String practitioner = " ";
		heading = log.getHeading();
		practitioner = log.getPractitioner();
		if(heading == null){
			heading = "";
		}
		if(practitioner == null){
			practitioner = "";
		}
		str.append("<td>"+dateTimeUtils.changeDateFormatToddmmyyyy(log.getApmtDate())+"</td>");
		str.append("<td>"+log.getApmtStartTime()+"</td>");
		
		int apmtId = log.getApmtId();
		boolean isCompleted = logDAO.checkAppointmentCompleted(apmtId);
		boolean isDNA = logDAO.checkApmtIsDNA(apmtId);
		String status = null;
		 status = log.getDeleteStatus();
		 if(status == null){
			 status = " ";
		 }
		String del = "Cancelled";
		if(status.equalsIgnoreCase(del))
		{
			str.append("<td>"+"(Cancelled)"+log.getCancelApmtsNotes()+"</td>");
		}
		else{
			if(isCompleted){
				str.append("<td>"+"<a href = 'Accounts?clientId="+clientId+"&clientName="+log.getClientname()+"&payby="+log.getPayby()+"&location="+log.getApmtLocation()+"' target = 'blank' title = 'Create Invoice/Process Charge'> <img src='popicons/complete.ico' alt='...' style = 'height: 20px;width: 20px;'> </a> | <a href = 'Statement?clientId="+clientId+"&clientName="+log.getClientname()+"&payby="+log.getPayby()+"&location="+log.getApmtLocation()+"' target = 'blank' title = 'View Accounts'> <img src='popicons/view_acount.ico' alt='...' style = 'height: 20px;width: 20px;'> </a></td>");

			}
			else{
				str.append("<td>"+"<a href = 'redirectToPendingCompleteApmt?practitionerId="+log.getPractiitonerId()+"&location="+log.getApmtLocation()+"&apmtDate="+log.getApmtDate()+"' target = 'blank' title = 'Complete Appointmnet'> <img src='popicons/cbs.ico' alt='...' style = 'height: 20px;width: 20px;'> </a> | <a href = 'Statement?clientId="+clientId+"&clientName="+log.getClientname()+"&payby="+log.getPayby()+"&location="+log.getApmtLocation()+"' target = 'blank' title = 'View Accounts'> <img src='popicons/view_acount.ico' alt='...' style = 'height: 20px;width: 20px;'> </a></td>");

			}
		}
		str.append("<td>"+heading+"</td>");
		str.append("<td>"+practitioner+"</td>");
		str.append("<td>"+log.getApmtLocation()+"</td>");
		if(loginInfo.getUserType() == 2){

		str.append("<td>"+Constants.getCurrency(loginInfo)+log.getTotal()+"</td>");
		}
		str.append("<td>"+log.getAddedBy()+"</td>");
		str.append("<td>"+DateTimeUtils.getIndianDateTimeFormat(log.getDate())+ "</td>");
		
		
		
		
		
		
		if(log.getStatus().equalsIgnoreCase("Completed") || log.getStatus().equalsIgnoreCase("Completed Modified")){
			str.append("<td style = 'color: Green;'>"+log.getStatus()+"</td>");

		}
		else if(log.getStatus().equalsIgnoreCase("Cancelled")){
			str.append("<td style = 'color: Red;'>"+log.getStatus()+" ("+log.getCancelApmtsNotes()+")</td>");

		}
		else if(log.getStatus().equalsIgnoreCase("DNA")){
			str.append("<td style = 'color: Red;'>"+log.getStatus()+"</td>");
		}
		else{
			str.append("<td style = 'color : #FFC200'>"+log.getStatus()+"</td>");

		}
		
		
		str.append("</tr>");
		}
		str.append("</tbody>");
		str.append("</table>");
		response.setContentType("text/html");
		response.setHeader("Cache-Control", "no-cache");
		
		response.getWriter().write(""+str.toString()+""); 
			
		
	}catch (Exception e) {
		e.printStackTrace();
	}
	finally{
		connection.close();
	}
	return null;
}
public String showDnaAppointment() throws Exception{
	if(!verifyLogin(request)){
		return "login";
	}
	Connection connection = null;
	try{
		
		String clientId = request.getParameter("clientId");
		String text = request.getParameter("txt");
		connection = Connection_provider.getconnection();
		LogDAO logDAO = new JDBCLogDAO(connection);
	
		ArrayList<LogDetail> accChargeList = logDAO.getDNAAppointmentList(clientId,text);
		StringBuffer str = new StringBuffer();
		
		str.append("<table class='table table-hove table-bordered table-striped table-condensed' > ");
		str.append("<thead>");
		str.append("<tr>");
		str.append("<th>Apmt Date</th> ");
		str.append("<th>Apmt Time</th> ");
		str.append("<th style = 'width:7%'>Action</th> ");
		str.append("<th>Apmt Type</th> ");
		str.append("<th>Practitioner</th> ");
		str.append("<th>Department</th> ");
		if(loginInfo.getUserType() == 2){

		str.append("<th>Charges</th> ");
		}
		str.append("<th>Modified By</th> ");
		str.append("<th>Modified Date/Time</th> ");
		str.append("<th>Apmt Status</th> ");
		
		
		str.append("</tr>");
		str.append("</thead>");
		
		str.append("<tbody>");
		for(LogDetail log:accChargeList){
			
		str.append("<tr>");
		String heading = " ";
		String practitioner = " ";
		heading = log.getHeading();
		practitioner = log.getPractitioner();
		if(heading == null){
			heading = "";
		}
		if(practitioner == null){
			practitioner = "";
		}
		str.append("<td>"+dateTimeUtils.changeDateFormatToddmmyyyy(log.getApmtDate())+"</td>");
		str.append("<td>"+log.getApmtStartTime()+"</td>");
		
		int apmtId = log.getApmtId();
		boolean isCompleted = logDAO.checkAppointmentCompleted(apmtId);
		boolean isDNA = logDAO.checkApmtIsDNA(apmtId);
		String status = null;
		 status = log.getDeleteStatus();
		 if(status == null){
			 status = " ";
		 }
		String del = "Cancelled";
		if(status.equalsIgnoreCase(del))
		{
			str.append("<td>"+"(Cancelled)"+log.getCancelApmtsNotes()+"</td>");
		}
		else{
			if(isCompleted){
				str.append("<td>"+"<a href = 'Accounts?clientId="+clientId+"&clientName="+log.getClientname()+"&payby="+log.getPayby()+"&location="+log.getApmtLocation()+"' target = 'blank' title = 'Create Invoice/Process Charge'> <img src='popicons/complete.ico' alt='...' style = 'height: 20px;width: 20px;'> </a> | <a href = 'Statement?clientId="+clientId+"&clientName="+log.getClientname()+"&payby="+log.getPayby()+"&location="+log.getApmtLocation()+"' target = 'blank' title = 'View Accounts'> <img src='popicons/view_acount.ico' alt='...' style = 'height: 20px;width: 20px;'> </a></td>");

			}
			else{
				str.append("<td>"+"<a href = 'redirectToPendingCompleteApmt?practitionerId="+log.getPractiitonerId()+"&location="+log.getApmtLocation()+"&apmtDate="+log.getApmtDate()+"' target = 'blank' title = 'Complete Appointmnet'> <img src='popicons/cbs.ico' alt='...' style = 'height: 20px;width: 20px;'> </a> | <a href = 'Statement?clientId="+clientId+"&clientName="+log.getClientname()+"&payby="+log.getPayby()+"&location="+log.getApmtLocation()+"' target = 'blank' title = 'View Accounts'> <img src='popicons/view_acount.ico' alt='...' style = 'height: 20px;width: 20px;'> </a></td>");

			}
		}
		str.append("<td>"+heading+"</td>");
		str.append("<td>"+practitioner+"</td>");
		str.append("<td>"+log.getApmtLocation()+"</td>");
		if(loginInfo.getUserType() == 2){

		str.append("<td>"+Constants.getCurrency(loginInfo)+log.getTotal()+"</td>");
		}
		str.append("<td>"+log.getAddedBy()+"</td>");
		str.append("<td>"+DateTimeUtils.getIndianDateTimeFormat(log.getDate())+ "</td>");
		
		
		
		
		
		
		if(log.getStatus().equalsIgnoreCase("Completed") || log.getStatus().equalsIgnoreCase("Completed Modified")){
			str.append("<td style = 'color: Green;'>"+log.getStatus()+"</td>");

		}
		else if(log.getStatus().equalsIgnoreCase("Cancelled")){
			str.append("<td style = 'color: Red;'>"+log.getStatus()+" ("+log.getCancelApmtsNotes()+")</td>");

		}
		else if(log.getStatus().equalsIgnoreCase("DNA")){
			str.append("<td style = 'color: Red;'>"+log.getStatus()+"</td>");
		}
		else{
			str.append("<td style = 'color : #FFC200'>"+log.getStatus()+"</td>");

		}
		
		
		str.append("</tr>");
		}
		str.append("</tbody>");
		str.append("</table>");
		response.setContentType("text/html");
		response.setHeader("Cache-Control", "no-cache");
		
		response.getWriter().write(""+str.toString()+""); 
			
		
	}catch (Exception e) {
		e.printStackTrace();
	}
	finally{
		connection.close();
	}
	return null;
}
/*public String showAccountPayments() throws SQLException{
	if(!verifyLogin(request)){
		return "login";
	}
	Connection connection = null;
	try{
		
		String clientId = request.getParameter("clientId");
		connection = Connection_provider.getconnection();
		LogDAO logDAO = new JDBCLogDAO(connection);
	
		ArrayList<LogDetail> accChargeList = logDAO.getPaymentList(clientId);
		StringBuffer str = new StringBuffer();
		
		str.append("<table class='table table-hove table-bordered table-striped table-condensed' > ");
		str.append("<thead>");
		str.append("<tr>");
		str.append("<th>Date</th> ");
		str.append("<th>Invoice No.</th> ");
		str.append("<th>Amount</th> ");
		str.append("<th>Mode of Paymnet</th> ");
		str.append("<th>Status</th> ");
		str.append("</tr>");
		str.append("</thead>");
		
		str.append("<tbody>");
		for(LogDetail log:accChargeList){
		str.append("<tr>");
		str.append("<td>"+log.getDate()+"</td>");
		str.append("<td>0000"+log.getInvoiceNo()+"</td>");
		str.append("<td>"+log.getTotal()+"</td>");
		str.append("<td>"+log.getPaymentMode()+"</td>");
		str.append("<td>"+log.getDeliverstatus()+"</td>");
		str.append("</tr>");
		}
		str.append("</tbody>");
		str.append("<table>");
		
		response.setContentType("text/html");
		response.setHeader("Cache-Control", "no-cache");
		
		response.getWriter().write(""+str.toString()+""); 
			
		
	}catch (Exception e) {
		e.printStackTrace();
	}
	finally{
		connection.close();
	}
	return null;
}*/

public String cancelnote(){
	if(!verifyLogin(request)){
		return "login";
	}
	
	String id = request.getParameter("id");
	try{
		Connection connection = null;
		connection = Connection_provider.getconnection();
		LogDAO logDAO = new JDBCLogDAO(connection);
		
		String note = logDAO.getCancelNote(id);
		
		response.setContentType("text/html");
		response.setHeader("Cache-Control", "no-cache");
		response.getWriter().write(""+note+""); 
		
	}catch(Exception e){
		e.printStackTrace();
	}

	
	
	return null;
}

public String showEmailHistory(){
	if(!verifyLogin(request)){
		return "login";
	}
	String searchText = request.getParameter("searchText");
	String fromDate = request.getParameter("fromDate");
	String toDate = request.getParameter("toDate");
	
	String clientId = request.getParameter("clientId");
	try{
	Connection connection = null;
	connection = Connection_provider.getconnection();
	LogDAO logDAO = new JDBCLogDAO(connection);

	ArrayList<LogDetail> emailHistoryList = logDAO.getEmailHistoryList(clientId);
	StringBuffer str = new StringBuffer();
	
	str.append("<table class='table table-hove table-bordered table-striped table-condensed' > ");
	str.append("<thead>");
	str.append("<tr>");
	str.append("<th>Invoice</th> ");
	str.append("<th>To</th> ");
	str.append("<th>Cc</th> ");
	str.append("<th>Subject</th> ");
	str.append("<th>Body</th> ");
	str.append("<th>Date</th> ");
	str.append("<th>time</th> ");
	str.append("<th>Pdf</th> ");
	str.append("</tr>");
	str.append("</thead>");
	
	str.append("<tbody>");
	for(LogDetail log:emailHistoryList){
		str.append("<tr>");
		str.append("<td>"+log.getInvoice_id()+"</td>");
		str.append("<td>"+log.getTo()+"</td>");
		str.append("<td>"+log.getCc()+"</td>");
		str.append("<td>"+log.getSubject()+"</td>");
		str.append("<td>"+log.getBody_text()+"</td>");
		str.append("<td>"+log.getDate()+"</td>");
		str.append("<td>"+log.getTime()+"</td>");
		str.append("<td class = 'text-center'><a class = 'text-danger' href='liveData/invoiceData/"+log.getFilename()+"' target='blank'><i class='fa fa-file-pdf-o'></i></a></td>");
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
	}
	return null;
}

public LogForm getModel() {
	// TODO Auto-generated method stub
	return logForm;
}

public void prepare() throws Exception {
	// TODO Auto-generated method stub
	
}
}