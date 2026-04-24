package com.apm.Eq.web.action;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;

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
import com.apm.DiaryManagement.web.action.EmbeddedImageEmailUtil;
import com.apm.DiaryManagement.web.action.SmsService;
import com.apm.Log.eu.entity.EmailLetterLog;
import com.apm.Registration.eu.bi.ClinicDAO;
import com.apm.Registration.eu.bi.UserProfileDAO;
import com.apm.Registration.eu.blogic.jdbc.JDBCClinicDAO;
import com.apm.Registration.eu.blogic.jdbc.JDBCUserProfileDAO;
import com.apm.Registration.eu.entity.Clinic;
import com.apm.Registration.eu.entity.UserProfile;
import com.apm.Tools.eu.bi.EmailTemplateDAO;
import com.apm.Tools.eu.blogic.jdbc.JDBCEmailTemplateDAO;
import com.apm.Tools.eu.entity.EmailTemplate;
import com.apm.common.eu.blogic.jdbc.Connection_provider;
import com.apm.common.utils.DateTimeUtils;
import com.apm.common.utils.Encryption;
import com.apm.common.web.action.BaseAction;
import com.apm.common.web.action.LoginAction;
import com.apm.common.web.common.helper.Access;
import com.apm.common.web.common.helper.LoginHelper;
import com.apm.common.web.common.helper.LoginInfo;
import com.apm.main.common.constants.Constants;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.Preparable;

public class EQAction extends BaseAction implements ModelDriven<BranchForm>,Preparable{

	BranchForm branchForm=new BranchForm();
	HttpServletRequest request = (HttpServletRequest) ActionContext.getContext().get(ServletActionContext.HTTP_REQUEST);
	HttpServletResponse response = (HttpServletResponse)ActionContext.getContext().get(ServletActionContext.HTTP_RESPONSE);
	HttpSession session = request.getSession(true);
	LoginInfo loginInfo = new LoginInfo();
	
	private static final Logger log = Logger.getLogger(LoginAction.class);
	
	
	public BranchForm getModel() {
		// TODO Auto-generated method stub
		return branchForm;
	}

	public String execute() throws SQLException{
		Connection connection = null;
		try{
			
			loginInfo.setLoginType("emr");
			
		//	session.setAttribute("dbTypes", Integer.parseInt(branchForm.getDbType()));
			
		//	connection = Connection_provider.getconnection();
			
			Class.forName("com.mysql.jdbc.Driver");
			connection=DriverManager.getConnection(""+Constants.DB_HOST+":3306/admin",""+Constants.DB_USER+"",""+Constants.DB_PWD+"");
			
			
			
			/*String user = branchForm.getUserId();
			String password = branchForm.getPassword();*/
	    String clinicid=request.getParameter("clinicid");	
		String email=request.getParameter("email");		
		
		
		
	    String outdata=	decrypt(email,clinicid);
		String[] data=outdata.split(",");
		
		clinicid=data[1];
		email=data[0];
	    
		ClinicDAO clinicDAO = new JDBCClinicDAO(connection);
		
		branchForm.setUserId(clinicid);
	
		boolean loginstatus = clinicDAO.getLoginStatus(branchForm.getUserId());
		if(loginstatus){
			addActionError(getText("error.userid.loggedin"));
			return INPUT;
		}
		
		Clinic clinic = clinicDAO.getuser(branchForm.getUserId());
		
		branchForm.setPassword(clinic.getPassword());
		
		loginInfo.setCountry(clinic.getCountry());
		
		if(!branchForm.getUserId().equals(clinic.getUserId()) ){
			addActionError(getText("error.userid.notexist"));
			return INPUT;
		}
		//String encPassword = Encryption.encryptSHA(branchForm.getPassword());
		if(!branchForm.getPassword().equals(clinic.getPassword())){
			addActionError(getText("error.user.authfailed"));
			return INPUT;
		}
		
				
		if(!branchForm.getUserId().equals("admin")){
			
			// update login status
			//int res = clinicDAO.updateLoginStatus(branchForm.getUserId());
			
			if(clinic.getUserType()==4){
				//connection = DriverManager.getConnection(""+Constants.DB_HOST+":3306/"+clinic.getClinicID()+"","root","mysql");
				String country = clinicDAO.getCountryForPractitoner(clinic.getClinicID());
				loginInfo.setCountry(country);
				connection = DriverManager.getConnection(""+Constants.DB_HOST+":3306/"+clinic.getClinicID()+"",""+Constants.DB_USER+"",""+Constants.DB_PWD+"");
				loginInfo.setClinicUserid(clinic.getClinicID());
			}else{
				connection = DriverManager.getConnection(""+Constants.DB_HOST+":3306/"+branchForm.getUserId()+"",""+Constants.DB_USER+"",""+Constants.DB_PWD+"");
				//connection = DriverManager.getConnection(""+Constants.DB_HOST+":3306/"+branchForm.getUserId()+"","root","mysql");
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
		
		connection = DriverManager.getConnection(""+Constants.DB_HOST+":3306/admin",""+Constants.DB_USER+"",""+Constants.DB_PWD+"");
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
		
		connection = DriverManager.getConnection(""+Constants.DB_HOST+":3306/"+clinic.getUserId()+"",""+Constants.DB_USER+"",""+Constants.DB_PWD+"");
		ClientDAO clientDAO=new JDBCClientDAO(connection);
		
		boolean checkClientExist=clientDAO.checkEmailidExist(email);
		
		
		if(clinic.getUserId().equals("eq")){
		
		 if(checkClientExist){
		
			int clientid=clientDAO.getPureSevaClientid(email);
			Client client=clientDAO.getClientDetails(String.valueOf(clientid));
			branchForm.setClientid(clientid);
			
			session.setAttribute("clientId", String.valueOf(clientid));
			session.setAttribute("diaryUserId", "1");
			session.setAttribute("conditionId", client.getConditionid());
			
			return "emrlink";
			
		   }else {
				ArrayList<String> initialList = clientDAO.getInitialList();
				branchForm.setInitialList(initialList);
				ArrayList<Client>condtitionList = clientDAO.getTreatmentTypeList();
				branchForm.setCondtitionList(condtitionList);
				return "register";
				
			}
		} else {
			
			 if(checkClientExist){
				   
				    int clientid=clientDAO.getPureSevaClientid(email);
					Client client=clientDAO.getClientDetails(String.valueOf(clientid));
					
					session.setAttribute("clientId", String.valueOf(clientid));
					session.setAttribute("diaryUserId", "1");
					session.setAttribute("conditionId", client.getConditionid());
					
					String otp=otp(client.getMobNo(), email);
					session.setAttribute("otp", otp);
					
				    return "otppage"; 
			 } else {
				 
				 branchForm.setAddress("You Are Not Registered in HIS!");
				 
				 return "errorotp";
				 
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
	
	
	
	public void prepare() throws Exception {
		// TODO Auto-generated method stub
		
	}
	
	public String getotp(){
		
		try {
			
			String otptext=branchForm.getOtptext();
			if(otptext!=null){
				
				if(!otptext.equals("")){
					
					String sessionotp=(String)session.getAttribute("otp");
					
					if(otptext.equals(sessionotp)){
						
						return "emrlink";
					} else {
						branchForm.setAddress("Error you enter wrong OTP!");
						return "errorotp";
					}
				}else {
					branchForm.setAddress("Error you enter wrong OTP!");
					return "errorotp";
				}
			}else {
				branchForm.setAddress("Error you enter wrong OTP!");
				return "errorotp";
			}
			
		} catch (Exception e) {

		   e.printStackTrace();
		}
		
		return "";
	}
	
	
	public String otp(String mob,String email){
	//	String email = request.getParameter("email");
	//	String mob = request.getParameter("mob");
		String otp = DateTimeUtils.getOTP();
		try{
			
		    String to = email;
			String cc = "";
			String subject = "One Time Password";
			String notes = "One Time Password for appointment booking is "+otp+". Please use this OTP to open EMR.";
			
			EmbeddedImageEmailUtil.sendOtpMail(to, cc, subject, notes);
			
			if(loginInfo.getCountry().equals("India")){
				SmsService s = new SmsService();
				String templateid="";
				s.sendSms(notes, mob, loginInfo, new EmailLetterLog(),templateid);
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return otp;
	}
	
	
	public String decrypt(String email, String clinicid)
    {
        String cipherText2 = "";
        String cipherText1 = "";
        String cipherText = "";

        for(char s:email.toCharArray()){
        	  
        	cipherText1 += decryptcharacter(s);
        }
        
        for (char s:clinicid.toCharArray())
        {
            cipherText2 += decryptcharacter(s);
        }
        cipherText = cipherText1 + "," + cipherText2;

        return cipherText;

    }
    public char decryptcharacter(char character)
    {
        char charreturn = '`';

        switch (character)
        {
            case '0':
                charreturn = '7';
                break;
            case '1':
                charreturn = '2';
                break;
            case '2':
                charreturn = '5';
                break;
            case '3':
                charreturn = '0';
                break;
            case '4':
                charreturn = '6';
                break;
            case '5':
                charreturn = '8';
                break;
            case '6':
                charreturn = '9';
                break;
            case '7':
                charreturn = '3';
                break;
            case '8':
                charreturn = '4';
                break;
            case '9':
                charreturn = '1';
                break;
          
            case 'a':
                charreturn = 'n';
                break;
      
            case 'b':
                charreturn = 'o';
                break;
       
            case 'c':
                charreturn = 'p';
                break;
            case 'd':
                charreturn = 'q';
                break;
   
            case 'e':
                charreturn = 'r';
                break;
   
            case 'f':
                charreturn = 's';
                break;
   
            case 'g':
                charreturn = 't';
                break;
       
            case 'h':
                charreturn = 'u';
                break;
    
            case 'i':
                charreturn = 'v';
                break;
      
            case 'j':
                charreturn = 'w';
                break;
     
            case 'k':
                charreturn = 'x';
                break;
       
            case 'l':
                charreturn = 'y';
                break;
    
            case 'm':
                charreturn = 'z';
                break;
     
            case 'n':
                charreturn = 'a';
                break;
       
            case 'o':
                charreturn = 'b';
                break;
      
            case 'p':
                charreturn = 'c';
                break;
  
            case 'q':
                charreturn = 'd';
                break;

            case 'r':
                charreturn = 'e';
                break;

            case 's':
                charreturn = 'f';
                break;
            case 't':
                charreturn = 'g';
                break;
      
            case 'u':
                charreturn = 'h';
                break;
         
            case 'v':
                charreturn = 'i';
                break;
        
            case 'w':
                charreturn = 'j';
                break;
    
            case 'x':
                charreturn = 'k';
                break;
     
            case 'y':
                charreturn = 'l';
                break;
         
            case 'z':
                charreturn = 'm';
                break;
            default:
                charreturn = character;
                break;

        }

        return charreturn;
    }
	
	
	
}
