package com.apm.common.web.action;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.validation.SkipValidation;

import com.apm.Appointment.web.form.BranchForm;
import com.apm.DiaryManagement.eu.bi.ClientDAO;
import com.apm.DiaryManagement.eu.bi.DiaryManagementDAO;
import com.apm.DiaryManagement.eu.bi.NotAvailableSlotDAO;
import com.apm.DiaryManagement.eu.blogic.jdbc.JDBCClientDAO;
import com.apm.DiaryManagement.eu.blogic.jdbc.JDBCDiaryManagentDAO;
import com.apm.DiaryManagement.eu.blogic.jdbc.JDBCNotAvailableSlotDAO;
import com.apm.DiaryManagement.eu.entity.NotAvailableSlot;
import com.apm.Registration.eu.bi.ClinicDAO;
import com.apm.Registration.eu.bi.UserProfileDAO;
import com.apm.Registration.eu.blogic.jdbc.JDBCClinicDAO;
import com.apm.Registration.eu.blogic.jdbc.JDBCUserProfileDAO;
import com.apm.Registration.eu.entity.Clinic;
import com.apm.Registration.eu.entity.UserProfile;
import com.apm.Tools.eu.bi.EmailTemplateDAO;
import com.apm.Tools.eu.blogic.jdbc.JDBCEmailTemplateDAO;
import com.apm.Tools.eu.entity.EmailTemplate;
import com.apm.common.utils.DateTimeUtils;
import com.apm.common.utils.Encryption;
import com.apm.common.web.common.helper.Access;
import com.apm.common.web.common.helper.LoginHelper;
import com.apm.common.web.common.helper.LoginInfo;
import com.apm.main.common.constants.Constants;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ModelDriven;


public class MobLoginAction extends BaseAction implements ModelDriven<BranchForm> {
	
	HttpServletRequest request = (HttpServletRequest) ActionContext.getContext().get(ServletActionContext.HTTP_REQUEST);
	HttpServletResponse response = (HttpServletResponse)ActionContext.getContext().get(ServletActionContext.HTTP_RESPONSE);
	HttpSession session = request.getSession(true);
	BranchForm branchForm = new BranchForm();
	LoginInfo loginInfo = new LoginInfo();
	
	private static final Logger log = Logger.getLogger(LoginAction.class);
	String actionType="";
	String appcclinicid="";
	String vidappoin="";
public String patientprofile() throws SQLException{
		
		 actionType = request.getParameter("actionType");
		if(actionType.equals("clientprof")){
			String uid = request.getParameter("uid");
			String pass = request.getParameter("pass");
			String uhid=request.getParameter("uhid");
//			branchForm.setUserId("chraibgh");
			branchForm.setPassword(pass);
			branchForm.setActiontype(actionType);
			branchForm.setDbType("0");
			appcclinicid=request.getParameter("clinicid");
			//session.removeAttribute("dbTypes");
			LoginHelper.removeLoginInfo(request);
			session.setAttribute("appclientid", uhid);
			loginInfo.setClientprof(true);
		}
		
		
		return execute();
	}

public String userprofile() throws SQLException{
	 actionType = request.getParameter("actionType");
		if(actionType.equals("userprof")){
			branchForm.setActiontype(actionType);
			branchForm.setPassword("");
			branchForm.setDbType("0");
			String userid=request.getParameter("userid");
			appcclinicid=request.getParameter("clinicid");
			//session.removeAttribute("dbTypes");
			LoginHelper.removeLoginInfo(request);
			session.setAttribute("appuserid", userid);
		}
	return execute();
}

@SkipValidation
public String patientemr() throws SQLException{
	 actionType = request.getParameter("actionType");
		if(actionType.equals("patientemr")){
			branchForm.setActiontype(actionType);
			branchForm.setPassword("");
			branchForm.setDbType("0");
			String uhid=request.getParameter("uhid");
			appcclinicid=request.getParameter("clinicid");
			//session.removeAttribute("dbTypes");
			LoginHelper.removeLoginInfo(request);
			session.setAttribute("appclientid", uhid);
		}
	
	
	return execute();
}


	public String execute() throws SQLException{
		Connection connection = null;
		try{
			
			loginInfo.setLoginType("mob");
			
		//	session.setAttribute("dbTypes", Integer.parseInt(branchForm.getDbType()));
			
		//	connection = Connection_provider.getconnection();
			
			Class.forName("com.mysql.jdbc.Driver");
			connection=DriverManager.getConnection(""+Constants.DB_HOST+":3306/admin",""+Constants.DB_USER+"",""+Constants.DB_PWD+"");
			
		
			/*String user = branchForm.getUserId();
			String password = branchForm.getPassword();*/
			
			//if(user==null){
				String user = request.getParameter("userid");
				String password = request.getParameter("password");
				branchForm.setUserId(user);
				branchForm.setPassword(password);
			//}
			if(actionType.equals("clientprof")||actionType.equals("mobemr")||actionType.equals("userprof")||actionType.equals("patientemr")){
				branchForm.setUserId(appcclinicid);
			}
		
		ClinicDAO clinicDAO = new JDBCClinicDAO(connection);
		
		String existUserid = "";
		boolean checkuserexist = clinicDAO.checkLoginUserExist(branchForm.getUserId());
		if(checkuserexist){
			 existUserid = clinicDAO.getExistUserId(branchForm.getUserId());
		}else{
			existUserid = "";
		}
		
		branchForm.setUserId(existUserid);
		
		boolean loginstatus = clinicDAO.getLoginStatus(branchForm.getUserId());
		if(loginstatus){
			addActionError(getText("error.userid.loggedin"));
			return INPUT;
		}
		
		Clinic clinic = clinicDAO.getuser(branchForm.getUserId());
		loginInfo.setCountry(clinic.getCountry());
		
	
		
		if(!branchForm.getUserId().equals(clinic.getUserId()) ){
			addActionError(getText("error.userid.notexist"));
			return INPUT;
		}
		if(!(actionType.equals("clientprof")||actionType.equals("mobemr")||actionType.equals("userprof")||actionType.equals("patientemr"))){
		String encPassword = Encryption.encryptSHA(branchForm.getPassword());
		if(!encPassword.equals(clinic.getPassword())){
			addActionError(getText("error.user.authfailed"));
			return INPUT;
		}
		}
		
		
		if(!branchForm.getUserId().equals("admin")){
			
			// update login status
			//int res = clinicDAO.updateLoginStatus(branchForm.getUserId());
			

			
			
			if(clinic.getUserType()==4){
				//connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/"+clinic.getClinicID()+"","root","mysql");
				String country = clinicDAO.getCountryForPractitoner(clinic.getClinicID());
				loginInfo.setCountry(country);
				connection = DriverManager.getConnection(""+Constants.DB_HOST+":3306/"+clinic.getClinicID()+"",""+Constants.DB_USER+"",""+Constants.DB_PWD+"");
				loginInfo.setClinicUserid(clinic.getClinicID());
			}else{
				connection = DriverManager.getConnection(""+Constants.DB_HOST+":3306/"+branchForm.getUserId()+"",""+Constants.DB_USER+"",""+Constants.DB_PWD+"");
				//connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/"+branchForm.getUserId()+"","root","mysql");
				loginInfo.setClinicUserid(branchForm.getUserId());
			}
			clinicDAO = new JDBCClinicDAO(connection);
			clinic = clinicDAO.getuser(branchForm.getUserId());
		}
		
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
		
		loginInfo.setIslogo("1");
		HttpServletRequest request = (HttpServletRequest) ActionContext.getContext().get(ServletActionContext.HTTP_REQUEST);
		HttpSession session = request.getSession(true);
		
		loginInfo.setId(clinic.getId());
		loginInfo.setUserId(clinic.getUserId());
		loginInfo.setFirstName(clinic.getFirstName());
		loginInfo.setLastName(clinic.getLastName());
		loginInfo.setClinicOwner(clinic.getClinicOwner());
		loginInfo.setUserType(clinic.getUserType());
		
		
		loginInfo.setDbName(clinic.getUserId());
		if(loginInfo.getUserType()==4){
			String userid = clinicDAO.getOtherUserID(clinic.getClinicID());
			loginInfo.setDbName(userid);
		}
		
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
		if(clinic.getUserType()==4){
			int clinicId = clinicDAO.getClinicId(clinic.getId());
			clinic2 = clinicDAO.getCliniclistDetails(clinicId);	
			loginInfo.setClinicid(clinicId);
		}
		else if(clinic.getUserType()==2){
			clinic2 = clinicDAO.getCliniclistDetails(clinic.getId());
			loginInfo.setClinicid(clinic.getId());
		}
		
		
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
			
		/*	loginInfo.setDiaryManagement(clinic2.isDiaryManagement());
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
			loginInfo.setTablet(clinic2.isTablet());*/
		
			loginInfo.setClinicName(clinic2.getClinicName());
		
			loginInfo.setClinicName(clinic2.getClinicName());
			loginInfo.setClinicAddress(clinic2.getAddress());
		
		
		
		
		session.setAttribute("logininfo", loginInfo);
		session.setAttribute("openedb", "opd");
		LoginHelper.saveLoginInfo(request, loginInfo);		
		
		String checkValue = clinicDAO.IsMailSend(clinic.getId());
		branchForm.setCheckMailSend(checkValue);
		
		/*AssessmentFormDAO assessmentFormDAO = new JDBCAssessmentFormDAO(connection);
		ArrayList<Assessment> templateNameList = new ArrayList<Assessment>();
		templateNameList = assessmentFormDAO.getTemplateList();
		//branchForm.setTemplateNameList(templateNameList);
		session.setAttribute("templateNameList", templateNameList);*/
		
	
		
		//show admin for all user other than practitioner
		UserProfileDAO userProfileDAO = new JDBCUserProfileDAO(connection);
		int duserid  = userProfileDAO.getDiaryUserId(loginInfo.getUserId());
		UserProfile userProfile = userProfileDAO.getUserprofileDetails(duserid);
		
		//connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/admin",""+Constants.DB_USER+"",""+Constants.DB_PWD+"");
		userProfileDAO = new JDBCUserProfileDAO(connection);
		Access access = userProfileDAO.getRoleAccess(userProfile.getJobtitle());
		
		//Access access = userProfileDAO.getUserRoleAccess(loginInfo.getUserId());
		loginInfo.setDiaryManagement(access.isDiarymanagement());
		loginInfo.setAppointmentBooking(access.isAppointmentbooking());
		loginInfo.setBasicFinance(access.isBasicfinance());
		loginInfo.setFullFinance(access.isFullfinance());
		loginInfo.setMedicalRecord(access.isMedicalrecord());
		loginInfo.setCommunication(access.isCommunication());
		loginInfo.setReport(access.isReport());
		loginInfo.setAssessmentForms(access.isAssessmentForm());
		loginInfo.setManageclient(access.isManageclient());
		loginInfo.setManageclinic(access.isManageclinic());
		loginInfo.setManagemaster(access.isManagemaster());
		loginInfo.setManageprisc(access.isManageprisc());
		loginInfo.setManageinvst(access.isManageinvst());
		loginInfo.setManageipd(access.isManageipd());
		loginInfo.setJobTitle(userProfile.getJobtitle());
		
		if(actionType.equals("clientprof")){
			return "clientprof";
		}
		
		if(actionType.equals("userprof")){
			return "userprof";
		}
		if(actionType.equals("mobemr")){
			NotAvailableSlotDAO availableSlotDAO=new JDBCNotAvailableSlotDAO(connection);
			NotAvailableSlot availableSlot=availableSlotDAO.getAvailableSlotdata(DateTimeUtils.convertToInteger(vidappoin));
			session.setAttribute("sessionselectedclientid", DateTimeUtils.isNull(availableSlot.getClientId()));

			session.setAttribute("clientId", availableSlot.getClientId());
			session.setAttribute("diaryUserId", ""+availableSlot.getDiaryUserId());
			session.setAttribute("condition", availableSlot.getCondition());
			session.setAttribute("conditionId", availableSlot.getCondition());
			session.setAttribute("videoconfappoitmentid", vidappoin);
			
			return "mobemr";
		}
		if(actionType.equals("patientemr")){
			loginInfo.setPatientemr(true);
			ClientDAO clientDAO=new JDBCClientDAO(connection);
			String uhid=(String)session.getAttribute("appclientid");
			int cid=clientDAO.getPureSevaClientid(uhid);
			session.setAttribute("sessionselectedclientid", ""+cid);
			return "patientemr";
		}
		if(clinic.getUserType()==2){
			if(clinic2.isAppointmentBooking() == true){
			return SUCCESS;
		}
		}
		
		if(clinic.getUserType()==4){
			if(clinic2.isAppointmentBooking() == true){
			return  SUCCESS;
		}
		}
		
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
