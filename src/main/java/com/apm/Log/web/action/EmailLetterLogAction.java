package com.apm.Log.web.action;

import java.sql.Connection;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.apm.Log.eu.bi.EmailLetterLogDAO;
import com.apm.Log.eu.bi.LogDAO;
import com.apm.Log.eu.blogic.jdbc.JDBCEmailLetterLogDAO;
import com.apm.Log.eu.blogic.jdbc.JDBCLogDAO;
import com.apm.Log.eu.entity.EmailLetterLog;
import com.apm.Log.eu.entity.LogDetail;
import com.apm.Log.web.form.LogForm;
import com.apm.common.eu.blogic.jdbc.Connection_provider;
import com.apm.common.utils.DateTimeUtils;
import com.apm.common.web.action.BaseAction;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ModelDriven;

public class EmailLetterLogAction extends BaseAction implements ModelDriven<LogForm>{
	HttpServletRequest request = (HttpServletRequest) ActionContext.getContext().get(ServletActionContext.HTTP_REQUEST);
	HttpServletResponse response = (HttpServletResponse)ActionContext.getContext().get(ServletActionContext.HTTP_RESPONSE);
	HttpSession session = request.getSession(true);
	LogForm logForm = new LogForm();
	
	
	public String showEmailHistory(){
		if(!verifyLogin(request)){
			return "login";
		}
		String client = request.getParameter("client");
		String type  = request.getParameter("type");
		
		try{
		Connection connection = null;
		connection = Connection_provider.getconnection();
		EmailLetterLogDAO emailLetterLogDAO = new JDBCEmailLetterLogDAO(connection);

		ArrayList<EmailLetterLog> emailHistoryList = emailLetterLogDAO.getEmailHistoryList(client,type);
		StringBuffer str = new StringBuffer();
			
		str.append("<table class='table table-hove table-bordered table-striped table-condensed' > ");
		str.append("<thead>");
		str.append("<tr>");
		str.append("<th>Date & Time</th> ");
		str.append("<th>Type</th> ");
		str.append("<th>Heading</th> ");
		str.append("<th>From</th> ");
		str.append("<th>To</th> ");
		str.append("<th>Status</th> ");
	/*	str.append("<th>Invoice Id</th> ");
		str.append("<th>Appointment Id</th> ");
		str.append("<th>Status</th> ");
		str.append("<th>Client Id</th> ");*/
		str.append("</tr>");
		str.append("</thead>");
		
		str.append("<tbody>");
		for(EmailLetterLog log:emailHistoryList){
			str.append("<tr>");
			str.append("<td>"+DateTimeUtils.getIndianDateTimeFormat(log.getDatetime())+"</td>");
			str.append("<td>"+log.getType()+"</td>");
			str.append("<td>"+log.getHeading()+"</td>");
			str.append("<td>"+log.getSender()+"</td>");
			str.append("<td>"+log.getReceiver()+"</td>");
			/*str.append("<td>"+log.getInvoiceid()+"</td>");
			str.append("<td>"+log.getAppointmentid()+"</td>");*/
			str.append("<td>"+log.getStatus()+"</td>");
			/*str.append("<td>"+log.getClientId()+"</td>");*/
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
	
	
	public String showLetterHistory(){
		if(!verifyLogin(request)){
			return "login";
		}
		String client = request.getParameter("client");
		
		try{
		Connection connection = null;
		connection = Connection_provider.getconnection();
		EmailLetterLogDAO emailLetterLogDAO = new JDBCEmailLetterLogDAO(connection);

		ArrayList<EmailLetterLog> letterHistoryList = emailLetterLogDAO.getLetterHistoryList(client);
		StringBuffer str = new StringBuffer();
			
		str.append("<table class='table table-hove table-bordered table-striped table-condensed' > ");
		str.append("<thead>");
		str.append("<tr>");
		str.append("<th>Date & Time</th> ");
		str.append("<th>Type</th> ");
		str.append("<th>Heading</th> ");
		str.append("<th>From</th> ");
		str.append("<th>To</th> ");
		/*str.append("<th>Invoice Id</th> ");*/
		/*str.append("<th>Appointment Id</th> ");*/
		/*str.append("<th>Status</th> ");*/
		/*str.append("<th>Client Id</th> ");*/
		str.append("</tr>");
		str.append("</thead>");
		
		str.append("<tbody>");
		for(EmailLetterLog log:letterHistoryList){
			str.append("<tr>");
			str.append("<td>"+DateTimeUtils.getIndianDateTimeFormat(log.getDatetime())+"</td>");
			str.append("<td>"+log.getType()+"</td>");
			str.append("<td>"+log.getHeading()+"</td>");
			str.append("<td>"+log.getSender()+"</td>");
			str.append("<td>"+log.getReceiver()+"</td>");
			//str.append("<td>"+log.getInvoiceid()+"</td>");
			/*str.append("<td>"+log.getAppointmentid()+"</td>");*/
			/*str.append("<td>"+log.getStatus()+"</td>");*/
			/*str.append("<td>"+log.getClientId()+"</td>");*/
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
		return null;
	}

}
