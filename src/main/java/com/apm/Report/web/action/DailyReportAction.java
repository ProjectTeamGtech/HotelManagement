package com.apm.Report.web.action;

import java.sql.Connection;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.apm.Accounts.eu.bi.AccountsDAO;
import com.apm.Accounts.eu.bi.AdditionalDAO;
import com.apm.Accounts.eu.blogic.jdbc.JDBCAccountsDAO;
import com.apm.Accounts.eu.blogic.jdbc.JDBCAdditionalDAO;
import com.apm.Accounts.eu.entity.Accounts;
import com.apm.Appointment.eu.entity.AppointmentType;
import com.apm.DiaryManagement.eu.bi.ClientDAO;
import com.apm.DiaryManagement.eu.bi.NotAvailableSlotDAO;
import com.apm.DiaryManagement.eu.blogic.jdbc.JDBCClientDAO;
import com.apm.DiaryManagement.eu.blogic.jdbc.JDBCNotAvailableSlotDAO;
import com.apm.DiaryManagement.eu.entity.Client;
import com.apm.DiaryManagement.eu.entity.DiaryManagement;
import com.apm.Master.eu.bi.TreatmentTypeDAO;
import com.apm.Master.eu.blogic.jdbc.JDBCTreatmentTypeDAO;
import com.apm.Master.eu.entity.TreatmentType;
import com.apm.Report.eu.bi.ChargesReportDAO;
import com.apm.Report.eu.bi.SummaryReportDAO;
import com.apm.Report.eu.blogic.jdbc.JDBCChargesReportDAO;
import com.apm.Report.eu.blogic.jdbc.JDBCSummaryReportDAO;
import com.apm.Report.eu.entity.SummaryReport;
import com.apm.Report.web.form.ChargesReportForm;
import com.apm.common.eu.blogic.jdbc.Connection_provider;
import com.apm.common.web.action.BaseAction;
import com.apm.common.web.common.helper.LoginHelper;
import com.apm.common.web.common.helper.LoginInfo;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.Preparable;

public class DailyReportAction extends BaseAction implements Preparable, ModelDriven<ChargesReportForm>{

	ChargesReportForm chargesReportForm = new ChargesReportForm();
	HttpServletRequest request = (HttpServletRequest) ActionContext.getContext().get(ServletActionContext.HTTP_REQUEST);
	HttpServletResponse response = (HttpServletResponse)ActionContext.getContext().get(ServletActionContext.HTTP_RESPONSE);
	HttpSession session = request.getSession(true);
	LoginInfo loginInfo = LoginHelper.getLoginInfo(request);
	
	@Override
	public String execute() throws Exception {
		
		return super.execute();
	}
	
	public String rptoutstanding() throws SQLException{
		
		if(!verifyLogin(request)){
			return "login";
		}
		String fromDate = chargesReportForm.getFromDate();
		String toDate = chargesReportForm.getToDate();
		
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		Calendar cal = Calendar.getInstance();
		//cal.add(Calendar.DATE, -365); 
		String checkDate = dateFormat.format(cal.getTime());
		if(fromDate.equals("")){
			chargesReportForm.setFromDate(checkDate);
			fromDate = chargesReportForm.getFromDate();
		}
		if(toDate.equals("")){
			chargesReportForm.setToDate(checkDate);
			toDate = chargesReportForm.getToDate();
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
		SummaryReportDAO summaryReportDAO = new JDBCSummaryReportDAO(connection);
		ChargesReportDAO chargesReportDAO = new JDBCChargesReportDAO(connection);
		DateFormat dateFormat1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	      Calendar cal1 = Calendar.getInstance();
	      String date = dateFormat.format(cal.getTime());  
	     
		 int result = chargesReportDAO.saveMisReportLog("Report Outstanding",loginInfo.getUserId(),fromDate,toDate,date,"rptoutstanding");
		ArrayList<SummaryReport>rptouststatndingList = summaryReportDAO.getReptOutStatndingList(fromDate,toDate);
		chargesReportForm.setRptouststatndingList(rptouststatndingList);
		
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			connection.close();
		}
		return "rptoutstanding";
	}
	
	
	public String newpts() throws SQLException{
		if(!verifyLogin(request)){
			return "login";
		}
		String fromDate = chargesReportForm.getFromDate();
		String toDate = chargesReportForm.getToDate();
	
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		Calendar cal = Calendar.getInstance();
		//cal.add(Calendar.DATE, -365); 
		String checkDate = dateFormat.format(cal.getTime());
		if(fromDate.equals("")){
			chargesReportForm.setFromDate(checkDate);
			fromDate = chargesReportForm.getFromDate();
		}
		if(toDate.equals("")){
			chargesReportForm.setToDate(checkDate);
			toDate = chargesReportForm.getToDate();
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
		SummaryReportDAO summaryReportDAO = new JDBCSummaryReportDAO(connection);
		ChargesReportDAO chargesReportDAO = new JDBCChargesReportDAO(connection);
		DateFormat dateFormat1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	      Calendar cal1 = Calendar.getInstance();
	      String date = dateFormat.format(cal.getTime());   
	     
		 int result = chargesReportDAO.saveMisReportLog("Report Outstanding",loginInfo.getUserId(),fromDate,toDate,date,"rptoutstanding");
		ArrayList<SummaryReport>newptsList = summaryReportDAO.getNewPtsList(fromDate,toDate,loginInfo.getClinicName());
		chargesReportForm.setNewptsList(newptsList);
		
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			connection.close();
		}
		return "newpts";
	}
	
	
	
	public String dnaotsreport() throws SQLException{
		if(!verifyLogin(request)){
			return "login";
		}
		String fromDate = chargesReportForm.getFromDate();
		String toDate = chargesReportForm.getToDate();
		
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		Calendar cal = Calendar.getInstance();
		//cal.add(Calendar.DATE, -365); 
		String checkDate = dateFormat.format(cal.getTime());
		if(fromDate.equals("")){
			chargesReportForm.setFromDate(checkDate);
			fromDate = chargesReportForm.getFromDate();
		}
		if(toDate.equals("")){
			chargesReportForm.setToDate(checkDate);
			toDate = chargesReportForm.getToDate();
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
		SummaryReportDAO summaryReportDAO = new JDBCSummaryReportDAO(connection);
		ChargesReportDAO chargesReportDAO = new JDBCChargesReportDAO(connection);
		DateFormat dateFormat1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	      Calendar cal1 = Calendar.getInstance();
	      String date = dateFormat.format(cal.getTime());  
	     
		 int result = chargesReportDAO.saveMisReportLog("DNA Outstanding Action",loginInfo.getUserId(),fromDate,toDate,date,"dnaotsreport");
		ArrayList<SummaryReport>dnaotsList = summaryReportDAO.getDnaOtsList(fromDate,toDate);
		chargesReportForm.setDnaotsList(dnaotsList);
		
		
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			connection.close();
		}
		return "dnaotsreport";
	}
	
	public String totalptsseen() throws SQLException{
		
		
		if(!verifyLogin(request)){
			return "login";
		}
		String fromDate = chargesReportForm.getFromDate();
		String toDate = chargesReportForm.getToDate();
		
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		Calendar cal = Calendar.getInstance();
		//cal.add(Calendar.DATE, -365); 
		String checkDate = dateFormat.format(cal.getTime());
		if(fromDate.equals("")){
			chargesReportForm.setFromDate(checkDate);
			fromDate = chargesReportForm.getFromDate();
		}
		if(toDate.equals("")){
			chargesReportForm.setToDate(checkDate);
			toDate = chargesReportForm.getToDate();
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
		SummaryReportDAO summaryReportDAO = new JDBCSummaryReportDAO(connection);
		ChargesReportDAO chargesReportDAO = new JDBCChargesReportDAO(connection);
		DateFormat dateFormat1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	      Calendar cal1= Calendar.getInstance();
	      String date = dateFormat.format(cal.getTime());  
	     
		 int result = chargesReportDAO.saveMisReportLog("Total Patients Seen",loginInfo.getUserId(),fromDate,toDate,date,"totalptsseen");
		ArrayList<SummaryReport>totalPtsList = summaryReportDAO.getTotalPtsList(fromDate,toDate,loginInfo.getClinicName(),chargesReportForm.getPayby(),chargesReportForm.getDiaryUser(),chargesReportForm.getLocation(),chargesReportForm.getThirdParty(),chargesReportForm.getCondition(),chargesReportForm.getApmtType());
		
		if(totalPtsList.size()>0){
			SummaryReport s = (SummaryReport)totalPtsList.get(totalPtsList.size()-1);
			chargesReportForm.setTotalPts(s.getTotalPts());
		}else{
			chargesReportForm.setTotalPts("0");
		}
		
		
		chargesReportForm.setTotalPtsList(totalPtsList);
		
		
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			connection.close();
		}
		
		return "totalptsseen";
	}
	
	public ChargesReportForm getModel() {
		// TODO Auto-generated method stub
		return chargesReportForm;
	}

	
	public void prepare() throws Exception {
		Connection connection = null;
		
		try{
			connection = Connection_provider.getconnection();
			NotAvailableSlotDAO notAvailableSlotDAO = new JDBCNotAvailableSlotDAO(connection);	
			AccountsDAO accountsDAO = new JDBCAccountsDAO(connection);
			ClientDAO clientDAO = new JDBCClientDAO(connection);
			
			ArrayList<DiaryManagement>userList = notAvailableSlotDAO.getUserList(loginInfo.getId());		
			chargesReportForm.setUserList(userList);
			
		
			ArrayList<Accounts>thirdPartyList = accountsDAO.getThirdPartyList(loginInfo.getId());
			chargesReportForm.setThirdPartyList(thirdPartyList);
			
			ArrayList<Accounts>locationList = accountsDAO.getLocationList(loginInfo.getClinicid());
			chargesReportForm.setLocationList(locationList);
		
			
			
			ArrayList<Client> condtitionList = clientDAO.getTreatmentTypeList();
			chargesReportForm.setTreatmentTypeList(condtitionList);
			
			AdditionalDAO additionalDAO = new JDBCAdditionalDAO(connection);
			ArrayList<AppointmentType>additionalChargesList = new ArrayList<AppointmentType>();
			additionalChargesList = additionalDAO.getAdditionalChargesList();
			chargesReportForm.setAdditionalChargesList(additionalChargesList);
			
			
		}catch(Exception e){
			
		}finally{
			connection.close();
		}
		
		
	}
	
	
	
	

}
