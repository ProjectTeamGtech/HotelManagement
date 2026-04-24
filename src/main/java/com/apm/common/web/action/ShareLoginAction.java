package com.apm.common.web.action;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;

import com.apm.Appointment.web.form.BranchForm;
import com.apm.DiaryManagement.eu.bi.ClientDAO;
import com.apm.DiaryManagement.eu.bi.DiaryManagementDAO;
import com.apm.DiaryManagement.eu.blogic.jdbc.JDBCClientDAO;
import com.apm.DiaryManagement.eu.blogic.jdbc.JDBCDiaryManagentDAO;
import com.apm.DiaryManagement.eu.entity.Client;
import com.apm.Registration.eu.bi.ClinicDAO;
import com.apm.Registration.eu.blogic.jdbc.JDBCClinicDAO;
import com.apm.Registration.eu.entity.Clinic;
import com.apm.Tools.eu.bi.EmailTemplateDAO;
import com.apm.Tools.eu.blogic.jdbc.JDBCEmailTemplateDAO;
import com.apm.Tools.eu.entity.EmailTemplate;
import com.apm.common.web.common.helper.LoginHelper;
import com.apm.common.web.common.helper.LoginInfo;
import com.apm.main.common.constants.Constants;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ModelDriven;

public class ShareLoginAction extends BaseAction implements ModelDriven<BranchForm> {
	
	HttpServletRequest request = (HttpServletRequest) ActionContext.getContext().get(ServletActionContext.HTTP_REQUEST);
	HttpServletResponse response = (HttpServletResponse)ActionContext.getContext().get(ServletActionContext.HTTP_RESPONSE);
	HttpSession session = request.getSession(true);
	BranchForm branchForm = new BranchForm();
	LoginInfo loginInfo = new LoginInfo();
	private static final Logger log = Logger.getLogger(LoginAction.class);
	
	public String execute() throws SQLException{
		Connection connection = null;
		try{
			
			loginInfo.setLoginType("shareemr");
			
			Class.forName("com.mysql.jdbc.Driver");
			//String title = request.getParameter("title");
			//String firstname = request.getParameter("firstname");
			//String lastname = request.getParameter("lastname");
			String email = request.getParameter("email");
			String mob = request.getParameter("mob");
			String clinicid = request.getParameter("clinicid");
			String diaryuserid = request.getParameter("diaryuser");
			
			String clientid = request.getParameter("clientid");
			String diaryuser = request.getParameter("diaryuser");
			String condition = request.getParameter("condition");
			
			loginInfo.setDbName(clientid);
			
			session.setAttribute("sharedclientid", clientid);
			session.setAttribute("shareddiaryuser", diaryuser);
			session.setAttribute("sharedcondition", condition);
			session.setAttribute("sharedmob", mob);
			
			/*String date = request.getParameter("date");
			DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
			Calendar cal = Calendar.getInstance();
			date = dateFormat.format(cal.getTime());
			date = "";*/
			
			/*String diaryuserid = request.getParameter("diaryuserid");
			String dob = request.getParameter("dob");
			String gender = request.getParameter("gender");*/
			loginInfo.setClinicUserid(clinicid);
			
			connection = DriverManager.getConnection(""+Constants.DB_HOST+":3306/admin",""+Constants.DB_USER+"",""+Constants.DB_PWD+"");
			ClinicDAO clinicDAO = new JDBCClinicDAO(connection);
			Clinic clinics = clinicDAO.getuser(clinicid);
			loginInfo.setCountry(clinics.getCountry());
			
			//set time zone
			loginInfo.setTimeZone("Europe/London");
			if(loginInfo.getCountry()!=null){
				if(loginInfo.getCountry().equals("India")){
					loginInfo.setTimeZone("IST");
				}
				if(loginInfo.getCountry().equals("London")){
					loginInfo.setTimeZone("Europe/London");
				}
			}
		
	
	
		
		connection = DriverManager.getConnection(""+Constants.DB_HOST+":3306/"+clinicid+"",""+Constants.DB_USER+"",""+Constants.DB_PWD+"");
		clinicDAO = new JDBCClinicDAO(connection);
		
		diaryuserid = clinicDAO.getFirstDiaryUserid();
		
		ClientDAO clientDAO = new JDBCClientDAO(connection);
		
		
		
		Clinic clinic = clinicDAO.getuser(clinicid);
		
		HttpServletRequest request = (HttpServletRequest) ActionContext.getContext().get(ServletActionContext.HTTP_REQUEST);
		HttpSession session = request.getSession(true);
		
		loginInfo.setId(clinic.getId());
		loginInfo.setUserId(clinic.getUserId());
	
		loginInfo.setClinicOwner(clinic.getClinicOwner());
		loginInfo.setUserType(5);
		loginInfo.setEmail(email);
		loginInfo.setMob(mob);
		
		loginInfo.setDiaryUserid(diaryuserid);
		
		
		
		
		loginInfo.setDbName(clinic.getUserId());
		
		
		//EmailConfigure Setting
		EmailTemplateDAO emailTemplateDAO = new JDBCEmailTemplateDAO(connection);
    	EmailTemplate emailTemplate = new EmailTemplate();
    	emailTemplate = emailTemplateDAO.getEmailConfigureDetails(clinic.getId());
    	loginInfo.setEmailConfigureId(emailTemplate.getEmailConfigureId());
    	loginInfo.setEmailUserName(emailTemplate.getEmailUserName());
    	loginInfo.setEmailPassword(emailTemplate.getEmailPassword());
		loginInfo.setEmailHostName(emailTemplate.getEmailHostName());
		
		//Menu Setting
		Clinic clinic2 = new Clinic();
		
		
			clinic2 = clinicDAO.getCliniclistDetails(clinic.getId());
			loginInfo.setClinicid(clinic.getId());
			
			//set clinic start and end time
			
			DiaryManagementDAO diaryManagementDAO = new JDBCDiaryManagentDAO(connection);
			Clinic csetting = diaryManagementDAO.getClinicStartAndEndTime(loginInfo.getClinicid());
			String temp[] = csetting.getStarttime().split(":");
			loginInfo.setClinicStartTime(Integer.parseInt(temp[0]));
			String temp1[] = csetting.getEndtime().split(":");
			loginInfo.setClinicEndTime(Integer.parseInt(temp1[0]));
			loginInfo.setDbsize(csetting.getDbsize());
			
			//set clinic registerd address
			loginInfo.setRegAddress(clinic2.getAddress());
			loginInfo.setRegLocation(clinic2.getLocationname());
			loginInfo.setRegPinCode(clinic2.getPinCode());
			loginInfo.setRegContactNo(clinic2.getContactNo());
		
			loginInfo.setDiaryManagement(clinic2.isDiaryManagement());
			loginInfo.setAppointmentBooking(clinic2.isAppointmentBooking());
			loginInfo.setBasicFinance(clinic2.isBasicFinance());
			loginInfo.setFullFinance(clinic2.isFullFinance());
			loginInfo.setMedicalRecord(clinic2.isMedicalRecord());
			loginInfo.setClinicResourceMngment(clinic2.isClinicResourceMngment());
			loginInfo.setClinicPayrollMngment(clinic2.isClinicPayrollMngment());
			loginInfo.setCommunication(clinic2.isCommunication());
			loginInfo.setReport(clinic2.isReport());
			loginInfo.setAssessmentForms(clinic2.isAssessmentForms());
			loginInfo.setDesktop(clinic2.isDesktop());
			loginInfo.setiOS(clinic2.isIOS());
			loginInfo.setMobile(clinic2.isMobile());
			loginInfo.setTablet(clinic2.isTablet());
			loginInfo.setJobTitle("Admin");
		
			loginInfo.setClinicName(clinic2.getClinicName());
			loginInfo.setClinicName(clinic2.getClinicName());
			loginInfo.setClinicAddress(clinic2.getAddress());
			loginInfo.setRegional_lang("en");
		
		
		
		session.setAttribute("logininfo", loginInfo);
		LoginHelper.saveLoginInfo(request, loginInfo);		
		
		String checkValue = clinicDAO.IsMailSend(clinic.getId());
		branchForm.setCheckMailSend(checkValue);
		
	
		
		
		
		
		
		
		
		
		}catch (Exception e) {
 			e.printStackTrace();
 			log.debug("##########################"+e.getMessage());
		}
		
		finally{
			connection.close();
		}
		
		
		
		return SUCCESS;
	}
	
/*	
	public String lockScreenPage(){
		
		Connection connection = null;
		try{
			connection = Connection_provider.getconnection();
			ClinicDAO clinicDAO = new JDBCClinicDAO(connection);
			LoginInfo loginInfo = (LoginInfo) session.getAttribute("loginInfo");
			String userId = loginInfo.getUserId();
			String encPassword = Encryption.encryptSHA(branchForm.getPassword());
			Clinic clinic = clinicDAO.getuser(userId);
			
			if(!encPassword.equals(clinic.getPassword())){
				addActionError(getText("error.user.authfailed"));
				return "lockscreen";
			}
			
		}catch(Exception e){
			
		}
		
		return "gotodashboard";
	}
	*/

	public void validate() {
	    	
    	 /* Do not use 'else if' since it will cause to show only one error at a time */
    	 // If user is null or empty add error in field errors
	/*	 if ( branchForm.getUserId() == null || branchForm.getUserId().length() == 0) {
	            addFieldError("userId", getText("error.userid.required") );	// set error message form property file
		 }
		 // If password is null or empty add error to field errors
	     if ( branchForm.getPassword() == null ||  branchForm.getPassword().length() == 0) {
	            addFieldError("password", getText("error.password.required")); 	// set error message form property file
	     }*/
    }

	public BranchForm getModel() {
		// TODO Auto-generated method stub
		return branchForm;
	}

	


}
