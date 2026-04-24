package com.apm.Medical.Reports.web.action;

import java.sql.Connection;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.apm.Accounts.eu.bi.AccountsDAO;
import com.apm.Accounts.eu.blogic.jdbc.JDBCAccountsDAO;
import com.apm.Accounts.eu.entity.Accounts;
import com.apm.DiaryManagement.eu.bi.NotAvailableSlotDAO;
import com.apm.DiaryManagement.eu.blogic.jdbc.JDBCNotAvailableSlotDAO;
import com.apm.DiaryManagement.eu.entity.DiaryManagement;
import com.apm.Medical.Reports.eu.bi.MedicalReportsDAO;
import com.apm.Medical.Reports.eu.blogic.jdbc.JdbcMedicalReportsDAO;
import com.apm.Medical.Reports.eu.entity.MedicalReports;
import com.apm.Medical.Reports.web.form.MedicalReportForm;
import com.apm.Registration.eu.entity.Location;
import com.apm.Report.eu.bi.ChargesReportDAO;
import com.apm.Report.eu.blogic.jdbc.JDBCChargesReportDAO;
import com.apm.Report.web.form.ChargesReportForm;
import com.apm.common.eu.blogic.jdbc.Connection_provider;
import com.apm.common.utils.DateTimeUtils;
import com.apm.common.web.action.BaseAction;
import com.apm.common.web.common.helper.LoginHelper;
import com.apm.common.web.common.helper.LoginInfo;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.Preparable;

public class PendingPaymentReportsAction extends BaseAction implements Preparable, ModelDriven<MedicalReportForm>{
	
	
	HttpServletRequest request = (HttpServletRequest) ActionContext.getContext().get(ServletActionContext.HTTP_REQUEST);
	HttpServletResponse response = (HttpServletResponse)ActionContext.getContext().get(ServletActionContext.HTTP_RESPONSE);
	HttpSession session = request.getSession(true);
	LoginInfo loginInfo = LoginHelper.getLoginInfo(request);
	
	MedicalReportForm medicalReportForm = new MedicalReportForm();
	ChargesReportForm chargesReportForm = new ChargesReportForm();
	public String execute() throws Exception {
		
		if(!verifyLogin(request)){
			return "login";
		}
		
		
		Connection connection = null;
		
		try{
		
			if(medicalReportForm.getFromDate().equals("")){
				DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
				Calendar cal = Calendar.getInstance();
				/*cal.add(Calendar.DATE, -365); */
				cal.add(Calendar.DATE, -15); 
				String checkDate = dateFormat.format(cal.getTime());
				medicalReportForm.setFromDate(checkDate);
			}
			
			if(medicalReportForm.getToDate().equals("")){
				DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
				Calendar cal = Calendar.getInstance();
				//cal.add(Calendar.DATE, -7); 
				String checkDate = dateFormat.format(cal.getTime());
				medicalReportForm.setToDate(checkDate);
			}
		
			
			connection = Connection_provider.getconnection();
			MedicalReportsDAO medicalReportsDAO = new JdbcMedicalReportsDAO(connection);
			ChargesReportDAO chargesReportDAO = new JDBCChargesReportDAO(connection);
			String fromDate = chargesReportForm.getFromDate();
			String toDate = chargesReportForm.getToDate();	
			DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		      Calendar cal = Calendar.getInstance();
		      String date = dateFormat.format(cal.getTime());  
			
			int result = chargesReportDAO.saveMisReportLog("Add Debtors Report",loginInfo.getUserId(),fromDate,toDate,date,"null");
			
			ArrayList<MedicalReports>pendingPaymentList = medicalReportsDAO.getPendingPaymentList(medicalReportForm.getPayby(),medicalReportForm.getLocation(),medicalReportForm.getDiaryUser(),medicalReportForm.getFromDate(),medicalReportForm.getToDate(),medicalReportForm.getOrder(),medicalReportForm.getOrderField(),medicalReportForm.getThirdParty());
			medicalReportForm.setPendingPaymentList(pendingPaymentList);
			
			 double amountBeforeThirtyTotal=0;
			
			 double amountAfterThirtyTotal=0;
			
			 double amountAfterSixtyTotal=0;
			
			 double amountAfterHundredTotal=0;
			
			for(MedicalReports medicalReports : pendingPaymentList){
				amountBeforeThirtyTotal = amountBeforeThirtyTotal + medicalReports.getAmountBeforeThirty();
				amountAfterThirtyTotal = amountAfterThirtyTotal + medicalReports.getAmountAfterThirty();
				amountAfterSixtyTotal = amountAfterSixtyTotal + medicalReports.getAmountAfterSixty();
				amountAfterHundredTotal = amountAfterHundredTotal + medicalReports.getAmountAfterHundred();
			}
			
			medicalReportForm.setAmountBeforeThirtyTotal(DateTimeUtils.changeFormat(amountBeforeThirtyTotal));
			medicalReportForm.setAmountAfterThirtyTotal(DateTimeUtils.changeFormat(amountAfterThirtyTotal));
			medicalReportForm.setAmountAfterSixtyTotal(DateTimeUtils.changeFormat(amountAfterSixtyTotal));
			medicalReportForm.setAmountAfterHundredTotal(DateTimeUtils.changeFormat(amountAfterHundredTotal));
			
			if(medicalReportForm.getOrder().equals("asc")){
				medicalReportForm.setOrder("desc");
				return SUCCESS;
			}
			if(medicalReportForm.getOrder().equals("desc")){
				medicalReportForm.setOrder("asc");
				return SUCCESS;
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			connection.close();
		}
		
		return SUCCESS;
	}

	public MedicalReportForm getModel() {
		
		return medicalReportForm;
	}

	public void prepare() throws Exception {
	Connection connection  = null;
		
		try{
			
			connection  = Connection_provider.getconnection();
			AccountsDAO accountsDAO = new JDBCAccountsDAO(connection);
			NotAvailableSlotDAO notAvailableSlotDAO = new JDBCNotAvailableSlotDAO(connection);
			
			ArrayList<Accounts>locationList = accountsDAO.getLocationList(loginInfo.getClinicid());
			ArrayList<DiaryManagement>userList = notAvailableSlotDAO.getUserList(loginInfo.getId());
			medicalReportForm.setUserList(userList);
			medicalReportForm.setLocationList(locationList);
			
			ArrayList<Accounts>thirdPartyList = accountsDAO.getThirdPartyList(loginInfo.getId());
			medicalReportForm.setThirdPartyList(thirdPartyList);
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}

}
