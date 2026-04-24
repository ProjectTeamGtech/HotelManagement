package com.apm.Report.web.action;

import java.sql.Connection;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.apm.Accounts.eu.bi.AccountsDAO;
import com.apm.Accounts.eu.blogic.jdbc.JDBCAccountsDAO;
import com.apm.Accounts.eu.entity.Accounts;
import com.apm.Registration.eu.bi.UserProfileDAO;
import com.apm.Registration.eu.blogic.jdbc.JDBCUserProfileDAO;
import com.apm.Registration.eu.entity.UserProfile;
import com.apm.Report.eu.bi.ChargesReportDAO;
import com.apm.Report.eu.blogic.jdbc.JDBCChargesReportDAO;
import com.apm.Report.web.form.ReportForm;
import com.apm.common.eu.blogic.jdbc.Connection_provider;
import com.apm.common.utils.DateTimeUtils;
import com.apm.common.web.action.BaseAction;
import com.apm.common.web.common.helper.LoginHelper;
import com.apm.common.web.common.helper.LoginInfo;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.Preparable;

public class DoctorShareReportAction extends BaseAction implements ModelDriven<ReportForm> ,Preparable {
	HttpServletRequest request = (HttpServletRequest) ActionContext.getContext().get(ServletActionContext.HTTP_REQUEST);
	HttpServletResponse response = (HttpServletResponse)ActionContext.getContext().get(ServletActionContext.HTTP_RESPONSE);
	ReportForm reportForm=new ReportForm();
	LoginInfo loginInfo = LoginHelper.getLoginInfo(request);
	public ReportForm getModel() {
		// TODO Auto-generated method stub
		return reportForm;
	}

	@Override
	public String execute() throws Exception {

		Connection connection=null;
		try {
			connection=Connection_provider.getconnection();
			ChargesReportDAO reportDAO=new JDBCChargesReportDAO(connection);
			UserProfileDAO userProfileDAO=new JDBCUserProfileDAO(connection);
			ChargesReportDAO chargesReportDAO = new JDBCChargesReportDAO(connection);
			String fromdate=reportForm.getFromDate();
			String todate=reportForm.getToDate();
			String practitionerId=reportForm.getPractitionerId();
			String jobtitle=reportForm.getJobtitle();
			
			/*String fromDate = reportForm.getFromDate();
			String toDate = reportForm.getToDate();	*/
			if(jobtitle!=null){
				
				if(jobtitle.equals("")){
					 jobtitle="0";
				}
			} else {
				jobtitle="0";
			}
			
			if(practitionerId==null){
				practitionerId="0";
			}
			if(practitionerId.equals("")){
				practitionerId="0";
			}
			
			
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			if(fromdate==null){
				
				Calendar calendar=Calendar.getInstance();
				calendar.add(Calendar.DATE, -7);
				fromdate=format.format(calendar.getTime());
				reportForm.setFromDate(DateTimeUtils.getCommencingDate1(fromdate));
				
			} else {
				
				fromdate=DateTimeUtils.getCommencingDate1(fromdate);
			}
			
			if(todate==null){
				
				Calendar calendar=Calendar.getInstance();
				todate=format.format(calendar.getTime());
				reportForm.setToDate(DateTimeUtils.getCommencingDate1(todate));
			} else {
				
				todate=DateTimeUtils.getCommencingDate1(todate);
			}
			DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		      Calendar cal = Calendar.getInstance();
		      String date = dateFormat.format(cal.getTime());  
			
			int result = chargesReportDAO.saveMisReportLog("Doctor Share Report",loginInfo.getUserId(),fromdate,todate,date,"execute");
			ArrayList<Accounts> doctorShareList=reportDAO.getDoctorShareReport(fromdate,todate,practitionerId,jobtitle); 
			reportForm.setDoctorShareList(doctorShareList);
			
			ArrayList<UserProfile> userProfileList=userProfileDAO.getAllUserProfileList();
			reportForm.setUserProfileList(userProfileList);
			
			
		} catch (Exception e) {

			e.printStackTrace();
		}
		finally {
			connection.close();
		}
		
		return super.execute();
	}
	
	
	
	
	public void prepare() throws Exception {
		
	}

}
