package com.apm.Log.web.action;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.apm.Log.eu.bi.AccountLogDAO;
import com.apm.Log.eu.bi.LogDAO;
import com.apm.Log.eu.blogic.jdbc.JDBCAccountLogDAO;
import com.apm.Log.eu.blogic.jdbc.JDBCLogDAO;
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

public class AccountLogAction extends BaseAction implements Preparable, ModelDriven<LogForm> {
	
	HttpServletRequest request = (HttpServletRequest) ActionContext.getContext().get(ServletActionContext.HTTP_REQUEST);
	HttpServletResponse response = (HttpServletResponse)ActionContext.getContext().get(ServletActionContext.HTTP_RESPONSE);
	HttpSession session = request.getSession(true);
	LogForm logForm = new LogForm();
	private Pagination pagination = new Pagination(7, 1);
	LoginInfo loginInfo = LoginHelper.getLoginInfo(request);
	
	
	public String showAccountCharges () throws SQLException{
		if(!verifyLogin(request)){
			return "login";
		}
		Connection connection = null;
		boolean chkInvoiceId = false;
		boolean chkPaid = false;
		try{
			
			String clientId = request.getParameter("clientId");
			connection = Connection_provider.getconnection();
			AccountLogDAO accountLogDAO = new JDBCAccountLogDAO(connection);
		
			ArrayList<LogDetail> accChargeList = accountLogDAO.getAccChargeList(clientId);
			StringBuffer str = new StringBuffer();
			
			str.append("<table class='table table-hove table-bordered table-striped table-condensed' > ");
			str.append("<thead>");
			str.append("<tr>");
			str.append("<th>Charge No.</th> ");
			//str.append("<th>Charge</th> ");
			str.append("<th>Practitioner</th> ");
			str.append("<th>Invoice Date</th> ");
			str.append("<th>Charge Type</th> ");
			str.append("<th>Status</th> ");
			str.append("<th>Date & Time</th> ");
			str.append("<th>Total Charge</th> ");
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
			
			chkInvoiceId = accountLogDAO.getInvoiceIdForColor(log.getInvoiceNo());
			
			chkPaid = accountLogDAO.checkColorForPaid(log.getInvoiceNo());
			
			if(chkInvoiceId == false){
				str.append("<td style='color: RED;'>"+log.getStatus()+"</td>");
			}
			else if(chkPaid == true){
				str.append("<td style='color: GREEN;'>"+log.getStatus()+"</td>");
			}
			else if(chkPaid == false){
				str.append("<td style='color: #FFC200;'>"+log.getStatus()+"</td>");
			}
			else{
				str.append("<td>"+log.getStatus()+"</td>");				
			}
			str.append("<td>"+DateTimeUtils.getIndianDateTimeFormat(log.getInvoiceDate())+"</td>");
			str.append("<td> "+Constants.getCurrency(loginInfo)+DateTimeUtils.changeFormat(log.getTotalCharge()) +"</td>");
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
	
	
	public String showAccountInvoice() throws SQLException{
		if(!verifyLogin(request)){
			return "login";
		}
		Connection connection = null;
		boolean chkPaid = false;
		try{
			
			String clientId = request.getParameter("clientId");
			connection = Connection_provider.getconnection();
			AccountLogDAO accountLogDAO = new JDBCAccountLogDAO(connection);
		
			ArrayList<LogDetail> accChargeList = accountLogDAO.getAccInvoiceList(clientId);
			StringBuffer str = new StringBuffer();
			
			str.append("<table class='table table-hove table-bordered table-striped table-condensed' > ");
			str.append("<thead>");
			str.append("<tr>");
			str.append("<th>Date</th> ");
			str.append("<th>Invoice No</th> ");
			str.append("<th>Payee</th> ");
			str.append("<th>Charge</th> ");
			str.append("<th>Deliver Status</th> ");
			str.append("<th>Status</th> ");
			str.append("<th>Invoice Date</th> ");
			//str.append("<th>PDF</th> ");
			str.append("</tr>");
			str.append("</thead>");
			
			str.append("<tbody>");
			for(LogDetail log:accChargeList){
			str.append("<tr>");
			str.append("<td>"+log.getDate()+"</td>");
			str.append("<td>0000"+log.getInvoiceNo()+"</td>");
			str.append("<td>"+log.getPayby()+" ("+log.getPayeename()+") </td>");
			str.append("<td>"+Constants.getCurrency(loginInfo)+DateTimeUtils.changeFormat(Double.parseDouble(log.getTotal()))+"</td>");
			str.append("<td>"+log.getDeliverstatus()+"</td>");
			
			chkPaid = accountLogDAO.checkColorForPaid(log.getInvoiceNo());
			
			if(chkPaid == true){
				str.append("<td style='color: GREEN;'>"+log.getStatus()+"</td>");
			}
			else{
				str.append("<td style='color: #FFC200;'>"+log.getStatus()+"</td>");
			}
			
			str.append("<td>"+DateTimeUtils.getIndianDateTimeFormat(log.getInvoiceDate())+"</td>");
			String filename = log.getInvoiceNo()+"_"+log.getClientname()+".pdf";
			//str.append("<td class = 'text-center'><a class = 'text-danger' href='liveData/invoiceData/"+filename+"' target='blank'><i class='fa fa-file-pdf-o'></i></a></td>");

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

	
	public String showAccountPayments() throws SQLException{
		if(!verifyLogin(request)){
			return "login";
		}
		Connection connection = null;
		try{
			
			String clientId = request.getParameter("clientId");
			connection = Connection_provider.getconnection();
			AccountLogDAO accountLogDAO = new JDBCAccountLogDAO(connection);
		
			ArrayList<LogDetail> accChargeList = accountLogDAO.getPaymentList(clientId);
			StringBuffer str = new StringBuffer();
			
			str.append("<table class='table table-hove table-bordered table-striped table-condensed' > ");
			str.append("<thead>");
			str.append("<tr>");
			str.append("<th>Date</th> ");
			str.append("<th>Invoice No.</th> ");
			str.append("<th>Amount</th> ");
			str.append("<th>Mode of Paymnet</th> ");
			str.append("<th>Delivery Status</th> ");
			str.append("<th>Status</th> ");
			str.append("<th>Invoice Date</th> ");
			str.append("<th>Payment Note</th> ");
			str.append("</tr>");
			str.append("</thead>");
			
			str.append("<tbody>");
			for(LogDetail log:accChargeList){
			str.append("<tr>");
			str.append("<td>"+DateTimeUtils.getIndianDateTimeFormat(log.getDate())+"</td>");
			str.append("<td>0000"+log.getInvoiceNo()+"</td>");
			str.append("<td>"+Constants.getCurrency(loginInfo)+DateTimeUtils.changeFormat(Double.parseDouble(log.getTotal())) +"</td>");
			str.append("<td>"+log.getPaymentMode()+"</td>");
			str.append("<td>"+log.getDeliverstatus()+"</td>");
			str.append("<td>"+log.getStatus()+"</td>");
			str.append("<td>"+log.getInvoiceDate()+"</td>");
			str.append("<td>"+log.getPaymentNote()+"</td>");
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
	}
	
	
	
	public LogForm getModel() {
		// TODO Auto-generated method stub
		return logForm;
	}
	public void prepare() throws Exception {
		// TODO Auto-generated method stub
		
	}

}
