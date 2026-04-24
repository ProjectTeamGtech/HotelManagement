package com.apm.Tools.web.action;

import java.io.BufferedReader;
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
import com.apm.DiaryManagement.eu.bi.ClientDAO;
import com.apm.DiaryManagement.eu.bi.DiaryManagementDAO;
import com.apm.DiaryManagement.eu.blogic.jdbc.JDBCClientDAO;
import com.apm.DiaryManagement.eu.blogic.jdbc.JDBCDiaryManagentDAO;
import com.apm.DiaryManagement.eu.entity.Client;
import com.apm.DiaryManagement.eu.entity.NotAvailableSlot;
import com.apm.DiaryManagement.web.action.EmbeddedImageEmailUtil;
import com.apm.DiaryManagement.web.form.ClientForm;
import com.apm.Inventory.eu.bi.ProcurementDAO;
import com.apm.Inventory.eu.blogic.jdbc.JDBCProcurementDAO;
import com.apm.Log.eu.entity.EmailLetterLog;
import com.apm.Registration.eu.bi.ClinicDAO;
import com.apm.Registration.eu.bi.UserProfileDAO;
import com.apm.Registration.eu.blogic.jdbc.JDBCClinicDAO;
import com.apm.Registration.eu.blogic.jdbc.JDBCUserProfileDAO;
import com.apm.Registration.eu.entity.Clinic;
import com.apm.Registration.eu.entity.Location;
import com.apm.Registration.eu.entity.UserProfile;
import com.apm.ThirdParties.eu.bi.GPDAO;
import com.apm.ThirdParties.eu.bi.ThirdPartyDAO;
import com.apm.ThirdParties.eu.blogic.jdbc.JDBCGPDAO;
import com.apm.ThirdParties.eu.blogic.jdbc.JDBCThirdPartyDAO;
import com.apm.ThirdParties.eu.entity.GP;
import com.apm.ThirdParties.eu.entity.ThirdParty;
import com.apm.Tools.eu.bi.EmailTemplateDAO;
import com.apm.Tools.eu.blogic.jdbc.JDBCEmailTemplateDAO;
import com.apm.Tools.eu.entity.EmailTemplate;
import com.apm.Tools.web.form.EmailTemplateForm;
import com.apm.common.eu.blogic.jdbc.Connection_provider;
import com.apm.common.utils.DateTimeUtils;
import com.apm.common.web.action.BaseAction;
import com.apm.common.web.common.helper.LoginHelper;
import com.apm.common.web.common.helper.LoginInfo;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.Preparable;

import atg.taglib.json.util.JSONObject;

import com.apm.main.common.constants.Constants;

public class TPReferalAction extends BaseAction implements Preparable, ModelDriven<EmailTemplateForm>{

	HttpServletRequest request = (HttpServletRequest) ActionContext.getContext().get(ServletActionContext.HTTP_REQUEST);
	HttpServletResponse response = (HttpServletResponse)ActionContext.getContext().get(ServletActionContext.HTTP_RESPONSE);
	HttpSession session = request.getSession(true);
	LoginInfo loginInfo = LoginHelper.getLoginInfo(request);
	EmailTemplateForm emailTemplateForm = new EmailTemplateForm();
	ClientForm clientForm = new ClientForm();
	public String execute(){
		if(!verifyLogin(request)){
			return "login";
		}
		return SUCCESS;
	}
	
	public String getTemplateDetails() throws Exception{
		if(!verifyLogin(request)){
			return "login";
		}
		Connection connection = null;
		EmailTemplate emailTemplate = new EmailTemplate();
		AllTemplateAction allTemplateAction = new AllTemplateAction();
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");	       
	    Date date1 = new Date();
	    String date = "";
//	    System.out.println(dateFormat.format(date1));
		String nowDate = dateFormat.format(date1);
		String clinicAddress = "",gpName="",gpaddress="";
		String templateData = "",pname = "",pdob = "",paddress = "", tpName = "",tpaddress = "",tpdob = "";
		String practitionerName = "",startTime = "",duration="",practitionerEmailId="",clientEmailId="";
		int locationId = 0;
		String body2Data = "",userQualification = "";
		String discipline = "";  
		try{
			connection = Connection_provider.getconnection();
			EmailTemplateDAO emailTemplateDAO = new JDBCEmailTemplateDAO(connection);
			
			String tempId = request.getParameter("tempId");
			String templateName = request.getParameter("templateName");
			String userName = emailTemplateDAO.getUserNameOfTemplate(tempId);
			//String userName = request.getParameter("userName");
			//emailTemplate = emailTemplateDAO.getEmailTemplatePreview(selectedid);
			//String filename=emailTemplateDAO.getFileNameBytemplate(tempId);
			ClientDAO clientDAO = new JDBCClientDAO(connection);
			UserProfileDAO userProfileDAO = new JDBCUserProfileDAO(connection);
			ThirdPartyDAO thirdPartyDAO = new JDBCThirdPartyDAO(connection);
			
		//	String id = (String)session.getAttribute("id");
			String userid = request.getParameter("id1");
			int selectedid = Integer.parseInt(userid);
			
			//Practitioner
			/*UserProfile userProfile1 = new UserProfile();
			userProfile1 = userProfileDAO.getUserprofileDetails(selectedid);
			pname = userProfile1.getFirstname()+" "+userProfile1.getLastname();	
			paddress = userProfile1.getAddress();			
			String clientId = userProfileDAO.getClientID(selectedid);
			Client client1 = clientDAO.getClientDetails(clientId);		*/
			
			//ThirdParty			
			/*ThirdParty thirdParty = new ThirdParty();
			thirdParty = thirdPartyDAO.getThirdPartyDetails(userid);
			tpName  = thirdParty.getCompanyName();
			tpaddress = thirdParty.getAddress();			
			String clientId1 = clientDAO.getClientID(userid);
			Client client2 = clientDAO.getClientDetails(clientId1);	*/
			
			//GP			
		/*	int gpId = thirdPartyDAO.getThirdPartyId(selectedid);
			String gpId1 = Integer.toString(gpId);
			ThirdParty thirdParty1 = new ThirdParty();
			thirdParty1 = thirdPartyDAO.getThirdPartyDetails(gpId1);
			gpName  = thirdParty1.getCompanyName();
			gpaddress = thirdParty1.getAddress();		*/	
			//String gpclientId = clientDAO.getGPClientID(userid);
			//Client gpclient = clientDAO.getClientDetails(gpclientId);	
			
			
			//client
			Client client = clientDAO.getClientDetails(userid);			
			String clientName = client.getTitle()+" "+client.getFirstName()+" "+client.getLastName();
			String address1 = client.getAddress();
			String address2 = client.getSecondLineaddress();
			String town = client.getTown();
			String postcode = client.getPostCode();
			String county = client.getCounty();
			String country = client.getCountry();
			String address = address1+ "," + " " + town + "," + " " + postcode;
			String dob = client.getDob();
			
			//String tpName  = clientDAO.getThirdPartyCompanyName(id);
			
			ClinicDAO clinicDAO = new JDBCClinicDAO(connection);
			Clinic clinic = clinicDAO.getCliniclistDetails(loginInfo.getClinicid());
			String clinicName = clinic.getClinicName();
			String PracticeOwnerName = clinic.getClinicOwner();
			String qualification = clinic.getOwner_qualification();
			
			String clinicEmail = clinic.getEmail();
			String clinicPhone = clinic.getContactNo();
			String clinicMobile = clinic.getMobileNo();
			String clinicWebsite = clinic.getWebsiteUrl();			
			
			int appointmentid = 0;
			String aptmtId = request.getParameter("aptmtId");
			if(aptmtId == null){
				 appointmentid = emailTemplateDAO.getLatestAppointmentId(userid);
			}
			else{
				 appointmentid = Integer.parseInt(aptmtId);
			}
			//emailTemplate =  emailTemplateDAO.getEmailTemplateData(appointmentid);
			
			DiaryManagementDAO diaryManagementDAO = new JDBCDiaryManagentDAO(connection);
         	ArrayList<NotAvailableSlot>appointmentList = diaryManagementDAO.getAppintmentSendMailDetails(appointmentid);
         	
         	for(NotAvailableSlot notAvailableSlot : appointmentList){
 				UserProfile userProfile = notAvailableSlot.getUserDEtails();
 				client = notAvailableSlot.getClientDetails();
 				Location location = notAvailableSlot.getLocationDetails(); 				
				
 				practitionerName = userProfile.getFullname();
 				userQualification = userProfile.getQualification();
 				startTime =  notAvailableSlot.getSTime();
 				duration = notAvailableSlot.getDuration();
 				practitionerEmailId = userProfile.getEmail(); 				
 				clientEmailId =  client.getEmail();
 				String locId = location.getLocationid();
 				if(locId==null) {
 					locId="0";
 				}
 				locationId = Integer.parseInt(locId);
 				date = notAvailableSlot.getCommencing();
 				date = DateTimeUtils.changeDateFormatToddmmyyyy(date);
 				date = DateTimeUtils.changeDateFormatTemplate(date);
 				
 				if(userProfile.getDiciplineName()!=null){
 					discipline = "Chartered" + " " +userProfile.getDiciplineName();
 					discipline = "("+discipline+")";
 				}
 				
 			}
         	String body1Data ="";
         	String email="";
         	String subject="";
         	
         	email = client.getEmail();
			subject = templateName +" : "+ clientName;
			
			
			String headerData = allTemplateAction.header(clinicName, PracticeOwnerName, qualification, clinicAddress, 
					clinicEmail,clinicMobile, clinicWebsite,loginInfo.getClinicid(),connection);
			
			
			if(templateName.equals(Constants.GPTEMP) && userName.equals("Practitioner")){
				int pId = userProfileDAO.getPractitionerId(selectedid);
				UserProfile userProfile1 = new UserProfile();
				userProfile1 = userProfileDAO.getUserprofileDetails(pId);
				pname = userProfile1.getFirstname()+" "+userProfile1.getLastname();	
				paddress = userProfile1.getAddress();
				String paddress1 = "";
				String ptown = userProfile1.getTown();
				String ppostcode = userProfile1.getPostcode();
				String pcounty = userProfile1.getCounty();
				
				email = userProfile1.getEmail();
				if(email == null){
					email = "";
				}
				//subject = templateName +":"+ pname;
				
				//String clientId = userProfileDAO.getClientID(selectedid);
				Client client1 = clientDAO.getClientDetails(userid);	
				String pClientName = client1.getFirstName()+" "+client1.getLastName();
				String completeAddress = client1.getAddress()+" "+client1.getSecondLineaddress()+"";
				
				body1Data = allTemplateAction.commonBody1(nowDate,pname,paddress,paddress1,ptown,ppostcode,pcounty);
//				System.out.println(body1Data);
				templateData = allTemplateAction.gpReferalBody(pClientName, completeAddress, client1.getDob());
//				System.out.println(templateData);
			}
			else if(templateName.equals(Constants.GPTEMP)){
				int gpId = thirdPartyDAO.getThirdPartyId(selectedid);
				String gpId1 = Integer.toString(gpId);
				ThirdParty thirdParty1 = new ThirdParty();
				thirdParty1 = thirdPartyDAO.getThirdPartyDetails(gpId1);
				GPDAO gpdao = new JDBCGPDAO(connection);
				int gpId2 = gpdao.getGPId(selectedid);
				GP gp  = gpdao.getGPDetail(gpId2);
				gpName = gp.getName();
				gpaddress = thirdParty1.getAddress();	
				String gpaddress1 = "";
				String gptown = thirdParty1.getTown();
				String gppostcode = thirdParty1.getPostcode();
				String gpcounty = thirdParty1.getCounty();
				
				email = thirdParty1.getCompnyEmail();
				//subject = templateName +":"+ gpName;
				if(email == null){
					email = "";
				}
				
				body1Data = allTemplateAction.commonGPBody1(nowDate,gpName,gpaddress,gpaddress1,gptown,gppostcode,gpcounty,thirdParty1);
//				System.out.println(body1Data);
				templateData = allTemplateAction.gpReferalBody(clientName,address,dob);
//				System.out.println(templateData);
			}
			else if(templateName.equals(Constants.APPBOOKTEMP)){				
				
				body1Data = allTemplateAction.commonBody1(nowDate,clientName,address1,address2,town,postcode,county);
//				System.out.println(body1Data);
				templateData = allTemplateAction.OnAppointBookingBody(PracticeOwnerName, clinicWebsite, practitionerName, clientName, startTime, duration, practitionerEmailId, clientEmailId, date, locationId,connection);
//				System.out.println(templateData);
			}
			else if(templateName.equals(Constants.TPTEMP)){
				int tpId = thirdPartyDAO.getThirdPartyTempId(selectedid);
				String tpId1 = Integer.toString(tpId);				
				ThirdParty thirdParty = new ThirdParty();
				thirdParty = thirdPartyDAO.getThirdPartyDetails(tpId1);
				tpName  = thirdParty.getCompanyName();
				tpaddress = thirdParty.getAddress();	
				String tpaddress1 = "";
				String tptown = thirdParty.getTown();
				String tppostcode = thirdParty.getPostcode();
				String tpcounty = thirdParty.getCounty();
				
				email = thirdParty.getCompnyEmail();
				if(email == null){
					email = "";
				}
				//subject = templateName +":"+ tpName;
				
				//String clientId1 = clientDAO.getClientID(userid);
				Client client2 = clientDAO.getClientDetails(userid);	
				String tClientName = client2.getFirstName()+" "+client2.getLastName();
				String completeAddress = client2.getAddress()+" "+client2.getSecondLineaddress()+"";
				String reference = client2.getReference();
				if(reference.equals("GP")){
					int gpId = thirdPartyDAO.getThirdPartyId(selectedid);
					String gpId1 = Integer.toString(gpId);
					ThirdParty thirdParty1 = new ThirdParty();
					thirdParty1 = thirdPartyDAO.getThirdPartyDetails(gpId1);
					GPDAO gpdao = new JDBCGPDAO(connection);
					int gpId2 = gpdao.getGPId(selectedid);
					GP gp  = gpdao.getGPDetail(gpId2);
					gpName = gp.getName();
					gpaddress = thirdParty1.getAddress();	
					//String gpaddress1 = "";
					String gptown = thirdParty1.getTown();
					String gppostcode = thirdParty1.getPostcode();
					String gpcounty = thirdParty1.getCounty();
					
					reference = reference+" - "+gpName+", "+gpaddress+", "+gptown+", "+gppostcode+"";
				}
				
				body1Data = allTemplateAction.tpCommonBody1(nowDate,tpName,tpaddress,tpaddress1,tptown,tppostcode,tpcounty);
//				System.out.println(body1Data);
				templateData = allTemplateAction.tpReferalBody(tClientName,completeAddress,client2.getDob(),reference);
//				System.out.println(templateData);
			}
			else if(templateName.equals(Constants.REMINDER1TEMP)){
				body1Data = allTemplateAction.commonBody1(nowDate,clientName,address1,address2,town,postcode,county);
//				System.out.println(body1Data);
				templateData = allTemplateAction.ApptConfirmationReminder1Body(PracticeOwnerName, date, startTime);
//				System.out.println(templateData);
			}
			else if(templateName.equals(Constants.REMINDER2TEMP)){
				body1Data = allTemplateAction.commonBody1(nowDate,clientName,address1,address2,town,postcode,county);
//				System.out.println(body1Data);
				templateData = allTemplateAction.ApptConfirmationReminder2Body(PracticeOwnerName, date, startTime);
//				System.out.println(templateData);
			}
			else if(templateName.equals(Constants.FURTHERREQUESTTEMP)){
				body1Data = allTemplateAction.commonBody1(nowDate,clientName,address1,address2,town,postcode,county);
//				System.out.println(body1Data);
				templateData = allTemplateAction.FurtherTreatmentRequestBody(PracticeOwnerName, clinicName, clinicMobile);
//				System.out.println(templateData);
			}
			else if(templateName.equals(Constants.FINALREMINDERTEMP)){
				body1Data = allTemplateAction.commonBody1(nowDate,clientName,address1,address2,town,postcode,county);
//				System.out.println(body1Data);
				templateData = allTemplateAction.FinalReminderBody(PracticeOwnerName, clinicName, clinicMobile);
//				System.out.println(templateData);
			}
			
			else{
				if(userName.equals("Practitioner")){
					int pId = userProfileDAO.getPractitionerId(selectedid);
					UserProfile userProfile1 = new UserProfile();
					userProfile1 = userProfileDAO.getUserprofileDetails(pId);
					pname = userProfile1.getFirstname()+" "+userProfile1.getLastname();	
					paddress = userProfile1.getAddress();	
					String paddress1 = "";
					String ptown = userProfile1.getTown();
					String ppostcode = userProfile1.getPostcode();
					String pcounty = userProfile1.getCounty();
					email = userProfile1.getEmail();
					if(email == null){
						email = "";
					}
					
					body1Data = allTemplateAction.commonBody1(nowDate,pname,paddress,paddress1,ptown,ppostcode,pcounty);
				}
				else if(userName.equals("ThirdParty")){
					int tpId = thirdPartyDAO.getThirdPartyTempId(selectedid);
					String tpId1 = Integer.toString(tpId);		
					ThirdParty thirdParty = new ThirdParty();
					thirdParty = thirdPartyDAO.getThirdPartyDetails(tpId1);
					tpName  = thirdParty.getCompanyName();
					tpaddress = thirdParty.getAddress();
					String tpaddress1 = "";
					String tptown = thirdParty.getTown();
					String tppostcode = thirdParty.getPostcode();
					String tpcounty = thirdParty.getCounty();
					email = thirdParty.getCompnyEmail();
					if(email == null){
						email = "";
					}
					
					body1Data = allTemplateAction.tpCommonBody1(nowDate,tpName,tpaddress,tpaddress1,tptown,tppostcode,tpcounty);
				}
				else if(userName.equals("GP")){
					int gpId = thirdPartyDAO.getThirdPartyId(selectedid);
					String gpId1 = Integer.toString(gpId);
					//String gpId1 = Integer.toString(selectedid);
					ThirdParty thirdParty1 = new ThirdParty();
					thirdParty1 = thirdPartyDAO.getThirdPartyDetails(gpId1);
					GPDAO gpdao = new JDBCGPDAO(connection);
					//int gpId2 = emailTemplateDAO.getGPId(selectedid);
					int gpId2 = gpdao.getGPId(selectedid);
					GP gp  = gpdao.getGPDetail(gpId2);
					gpName = gp.getName();
					gpaddress = thirdParty1.getAddress();	
					String gpaddress1 = "";
					String gptown = thirdParty1.getTown();
					String gppostcode = thirdParty1.getPostcode();
					String gpcounty = thirdParty1.getCounty();				
					email = thirdParty1.getCompnyEmail();	
					if(email == null){
						email = "";
					}
					
					body1Data = allTemplateAction.commonGPBody1(nowDate,gpName,gpaddress,gpaddress1,gptown,gppostcode,gpcounty,thirdParty1);
				}
				else{
					body1Data = allTemplateAction.commonBody1(nowDate,clientName,address1,address2,town,postcode,county);
					}
				
				StringBuffer s = new StringBuffer();
				s.append("<div style = 'text-align: justify;text-justify: inter-word;'>");		
				s.append("<br>Re : "+clientName+", "+address+", DOB: "+dob+".<br><br>");
				
				templateData = emailTemplateDAO.getEmailTemplateData(tempId);
				
				s.append(templateData);
				
				templateData = s.toString();
				
				//templateData = allTemplateAction.gpReferalBody(clientName,address,dob);
				
			}
			
			if(userName.equals("Practitioner")){
				
				body2Data = allTemplateAction.commonBody2(PracticeOwnerName,qualification,discipline);
				
			}else{
				
				body2Data = allTemplateAction.commonBody2(practitionerName,userQualification,discipline);
			}
//			System.out.println(body2Data);
			String footerData = allTemplateAction.commonFooter(clinicName);
//			System.out.println(footerData);
			String data = ""+headerData+" "+body1Data+" "+templateData+" "+body2Data+" "+footerData+" "; 
//			System.out.println(data);		
			
			StringBuffer str = new StringBuffer();
			str.append("<div style ='height:auto;margin-left: 30px;margin-right: 30px;'>"+headerData+"</div>");
			str.append("<div class='emailhead' style ='height:auto;margin-left: 30px;margin-right: 30px;'>"+body1Data+" "+templateData+" "+body2Data+"</div>");
			str.append("<div style ='height: auto;margin-left: 30px;margin-right: 30px;'>"+footerData+"</div>");
			
			data = str.toString()+"#"+email+"#"+subject+"#"+tempId;
			
			response.setContentType("text/html");
			response.setHeader("Cache-Control", "no-cache");
			response.getWriter().write(""+data+""); 
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			connection.close();
		}
		
		return null;
	}
	
	public String getTemplateDetailsPrint() throws Exception{
		
		Connection connection = null;
		EmailTemplate emailTemplate = new EmailTemplate();
		AllTemplateAction allTemplateAction = new AllTemplateAction();
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");	       
	    Date date1 = new Date();
	    String date = "";
//	    System.out.println(dateFormat.format(date1));
		String nowDate = dateFormat.format(date1);
		String clinicAddress = "",gpName="",gpaddress="";
		String templateData = "",pname = "",pdob = "",paddress = "", tpName = "",tpaddress = "",tpdob = "";
		String practitionerName = "",startTime = "",duration="",practitionerEmailId="",clientEmailId="";
		int locationId = 0;
		String body2Data = "";
		  
		try{
			connection = Connection_provider.getconnection();
			EmailTemplateDAO emailTemplateDAO = new JDBCEmailTemplateDAO(connection);
			
			String tempId = request.getParameter("tempId");
			String templateName = request.getParameter("templateName");
			//String userName = request.getParameter("userName");
			String userid = request.getParameter("id1");
			int selectedid = Integer.parseInt(userid);
			
			String userName = emailTemplateDAO.getUserNameOfTemplate(tempId);
			
			ClientDAO clientDAO = new JDBCClientDAO(connection);
			UserProfileDAO userProfileDAO = new JDBCUserProfileDAO(connection);
			ThirdPartyDAO thirdPartyDAO = new JDBCThirdPartyDAO(connection);
			
			Client client = new Client();
			
			ClinicDAO clinicDAO = new JDBCClinicDAO(connection);
			Clinic clinic = clinicDAO.getCliniclistDetails(loginInfo.getClinicid());
			String clinicName = clinic.getClinicName();
			String PracticeOwnerName = clinic.getClinicOwner();
			String qualification = clinic.getOwner_qualification();
			
			String clinicEmail = clinic.getEmail();
			String clinicPhone = clinic.getContactNo();
			String clinicMobile = clinic.getMobileNo();
			String clinicWebsite = clinic.getWebsiteUrl();		
			int appointmentid = 0;
			String email= "", body1Data = "", subject="";
			String discipline = "";
         	
			
			String clientName = "",address1="",address2="",town="",postcode="",county="",country="",address="",dob="";
			address2 = new String("");
			//if(userName.equals("Client")){
				 appointmentid = emailTemplateDAO.getLatestAppointmentId(userid);
				 
					client = clientDAO.getClientDetails(userid);			
					clientName = client.getTitle()+" "+client.getFirstName()+" "+client.getLastName();
					address1 = client.getAddress();
					address2 = client.getSecondLineaddress();
					town = client.getTown();
					postcode = client.getPostCode();
					county = client.getCounty();
					country = client.getCountry();
					address = address1+ "," + " " + town + "," + " " + postcode;
					dob = client.getDob();
					
					subject = templateName +" : "+ clientName;
					email = client.getEmail();
					
			/*}
			else if(userName.equals("Practitioner")){
				 appointmentid = emailTemplateDAO.getLatestAppointmentIdByPract(userid);
			}
			else if(userName.equals("ThirdParty")){
				 String clientId = emailTemplateDAO.getClientId(userid);
				 appointmentid = emailTemplateDAO.getLatestAppointmentId(clientId);
			}
			else if(userName.equals("GP")){
				 String clientId = emailTemplateDAO.getClientId(userid);
				 appointmentid = emailTemplateDAO.getLatestAppointmentId(clientId);
			}*/
			
			DiaryManagementDAO diaryManagementDAO = new JDBCDiaryManagentDAO(connection);
         	ArrayList<NotAvailableSlot>appointmentList = diaryManagementDAO.getAppintmentSendMailDetails(appointmentid);
         	
         	for(NotAvailableSlot notAvailableSlot : appointmentList){
 				UserProfile userProfile = notAvailableSlot.getUserDEtails();
 				client = notAvailableSlot.getClientDetails();
 				Location location = notAvailableSlot.getLocationDetails(); 				
				
 				practitionerName = userProfile.getFullname();
 				startTime =  notAvailableSlot.getSTime();
 				duration = notAvailableSlot.getDuration();
 				practitionerEmailId = userProfile.getEmail(); 				
 				clientEmailId =  client.getEmail();
 				String locId = location.getLocationid();
 				locationId = Integer.parseInt(locId);
 				date = notAvailableSlot.getCommencing();
 				date = DateTimeUtils.changeDateFormatToddmmyyyy(date);
 				date = DateTimeUtils.changeDateFormatTemplate(date);
 				
 				if(userProfile.getDiciplineName()!=null){
 					discipline = "Chartered" + " " +userProfile.getDiciplineName();
 					discipline = "("+discipline+")";
 				}
 				
 			}
         	
         	String headerData = allTemplateAction.header(clinicName, PracticeOwnerName, qualification, clinicAddress, 
					clinicEmail,clinicMobile, clinicWebsite,loginInfo.getClinicid(),connection);
			
         	if(templateName.equals(Constants.GPTEMP) && userName.equals("Practitioner")){
         		int pId = userProfileDAO.getPractitionerId(selectedid);
				UserProfile userProfile1 = new UserProfile();
				userProfile1 = userProfileDAO.getUserprofileDetails(pId);
				pname = userProfile1.getFirstname()+" "+userProfile1.getLastname();	
				paddress = userProfile1.getAddress();
				String paddress1 = "";
				String ptown = userProfile1.getTown();
				String ppostcode = userProfile1.getPostcode();
				String pcounty = userProfile1.getCounty();				
				email = userProfile1.getEmail();
				if(email == null){
					email = "";
				}
				if(userProfile1.getFirstname() == null && userProfile1.getLastname() == null){
					pname = "Not Available";
				}
				
				//String clientId = userProfileDAO.getClientID(selectedid);
				Client client1 = clientDAO.getClientDetails(userid);	
				String pClientName = client1.getFirstName()+" "+client1.getLastName();
				String completeAddress = client1.getAddress()+" "+client1.getSecondLineaddress()+"";
				if((client1.getAddress() == null) && (client1.getSecondLineaddress()) == null){
					completeAddress = "";
				}
				if(pClientName.equals(" ")){
					pClientName = "Not Available";
				}
				subject = templateName +" : "+ pClientName;
				
				body1Data = allTemplateAction.commonBody1(nowDate,pname,paddress,paddress1,ptown,ppostcode,pcounty);
//				System.out.println(body1Data);
				templateData = allTemplateAction.gpReferalBody(pClientName, completeAddress, client1.getDob());
//				System.out.println(templateData);
			}
			else if(templateName.equals(Constants.GPTEMP)){
				int gpId = thirdPartyDAO.getThirdPartyId(selectedid);
				String gpId1 = Integer.toString(gpId);
				ThirdParty thirdParty1 = new ThirdParty();
				thirdParty1 = thirdPartyDAO.getThirdPartyDetails(gpId1);
				GPDAO gpdao = new JDBCGPDAO(connection);
				//int gpId2 = emailTemplateDAO.getGPId(selectedid);
				int gpId2 = gpdao.getGPId(selectedid);
				GP gp  = gpdao.getGPDetail(gpId2);
				gpName = gp.getName();
				gpaddress = thirdParty1.getAddress();	
				String gpaddress1 = "";
				String gptown = thirdParty1.getTown();
				String gppostcode = thirdParty1.getPostcode();
				String gpcounty = thirdParty1.getCounty();				
				email = thirdParty1.getCompnyEmail();
				if(email == null){
					email = "";
				}
				
				//String clientId = emailTemplateDAO.getClientId(userid);				
				Client client1 = clientDAO.getClientDetails(userid);	
				String tClientName = client1.getFirstName()+" "+client1.getLastName();
				String completeAddress = client1.getAddress()+" "+client1.getSecondLineaddress()+"";
			
				if((client1.getAddress() == null) && (client1.getSecondLineaddress()) == null){
					completeAddress = "";
				}
				if(tClientName.equals(" ")){
					tClientName = "Not Available";
				}
				
				subject = templateName +" : "+ tClientName;
				
				body1Data = allTemplateAction.commonGPBody1(nowDate,gpName,gpaddress,gpaddress1,gptown,gppostcode,gpcounty,thirdParty1);
//				System.out.println(body1Data);
				templateData = allTemplateAction.gpReferalBody(tClientName,address,client1.getDob());
//				System.out.println(templateData);
			}
			else if(templateName.equals(Constants.TPTEMP)){
				int tpId = thirdPartyDAO.getThirdPartyTempId(selectedid);
				String tpId1 = Integer.toString(tpId);				
				ThirdParty thirdParty = new ThirdParty();
				thirdParty = thirdPartyDAO.getThirdPartyDetails(tpId1);
				tpName  = thirdParty.getCompanyName();
				tpaddress = thirdParty.getAddress();	
				String tpaddress1 = "";
				String tptown = thirdParty.getTown();
				String tppostcode = thirdParty.getPostcode();
				String tpcounty = thirdParty.getCounty();				
				email = thirdParty.getCompnyEmail();
				if(email == null){
					email = "";
				}
				//String clientId1 = clientDAO.getClientID(userid);
				Client client2 = clientDAO.getClientDetails(userid);	
				String tClientName = client2.getFirstName()+" "+client2.getLastName();
				String completeAddress = client2.getAddress()+" "+client2.getSecondLineaddress()+"";
				
				if((client2.getAddress() == null) && (client2.getSecondLineaddress()) == null){
					completeAddress = "";
				}
				if(tClientName.equals(" ")){
					tClientName = "Not Available";
				}
				
				String reference = client2.getReference();
				if(reference == null){
					reference = "";
				}
				if(reference.equals("GP")){
					int gpId = thirdPartyDAO.getThirdPartyId(selectedid);
					String gpId1 = Integer.toString(gpId);
					ThirdParty thirdParty1 = new ThirdParty();
					thirdParty1 = thirdPartyDAO.getThirdPartyDetails(gpId1);
					GPDAO gpdao = new JDBCGPDAO(connection);
					int gpId2 = gpdao.getGPId(selectedid);
					GP gp  = gpdao.getGPDetail(gpId2);
					gpName = gp.getName();
					gpaddress = thirdParty1.getAddress();	
					//String gpaddress1 = "";
					String gptown = thirdParty1.getTown();
					String gppostcode = thirdParty1.getPostcode();
					String gpcounty = thirdParty1.getCounty();
					
					reference = reference+" - "+gpName+", "+gpaddress+", "+gptown+", "+gppostcode+"";
				}	
				
				subject = templateName +" : "+ tClientName;
				
				body1Data = allTemplateAction.tpCommonBody1(nowDate,tpName,tpaddress,tpaddress1,tptown,tppostcode,tpcounty);
//				System.out.println(body1Data);
				templateData = allTemplateAction.tpReferalBody(tClientName,completeAddress,client2.getDob(),reference);
//				System.out.println(templateData);
			}
			else if(templateName.equals(Constants.APPBOOKTEMP)){				
				
				body1Data = allTemplateAction.commonBody1(nowDate,clientName,address1,address2,town,postcode,county);
//				System.out.println(body1Data);
				templateData = allTemplateAction.OnAppointBookingBody(PracticeOwnerName, clinicWebsite, practitionerName, clientName, startTime, duration, practitionerEmailId, clientEmailId, date, locationId,connection);
				//System.out.println(templateData);
				
			}
			else if(templateName.equals(Constants.REMINDER1TEMP)){
				body1Data = allTemplateAction.commonBody1(nowDate,clientName,address1,address2,town,postcode,county);
				//System.out.println(body1Data);
				templateData = allTemplateAction.ApptConfirmationReminder1Body(PracticeOwnerName, date, startTime);
				//System.out.println(templateData);
			}
			else if(templateName.equals(Constants.REMINDER2TEMP)){
				body1Data = allTemplateAction.commonBody1(nowDate,clientName,address1,address2,town,postcode,county);
				//System.out.println(body1Data);
				templateData = allTemplateAction.ApptConfirmationReminder2Body(PracticeOwnerName, date, startTime);
				//System.out.println(templateData);
			}
			else if(templateName.equals(Constants.FURTHERREQUESTTEMP)){
				body1Data = allTemplateAction.commonBody1(nowDate,clientName,address1,address2,town,postcode,county);
				//System.out.println(body1Data);
				templateData = allTemplateAction.FurtherTreatmentRequestBody(PracticeOwnerName, clinicName, clinicMobile);
				//System.out.println(templateData);
			}
			else if(templateName.equals(Constants.FINALREMINDERTEMP)){
				body1Data = allTemplateAction.commonBody1(nowDate,clientName,address1,address2,town,postcode,county);
				//System.out.println(body1Data);
				templateData = allTemplateAction.FinalReminderBody(PracticeOwnerName, clinicName, clinicMobile);
				//System.out.println(templateData);
			}
			else{
				if(userName.equals("Practitioner")){
					int pId = userProfileDAO.getPractitionerId(selectedid);
					UserProfile userProfile1 = new UserProfile();
					userProfile1 = userProfileDAO.getUserprofileDetails(pId);
					pname = userProfile1.getFirstname()+" "+userProfile1.getLastname();	
					paddress = userProfile1.getAddress();	
					String paddress1 = "";
					String ptown = userProfile1.getTown();
					String ppostcode = userProfile1.getPostcode();
					String pcounty = userProfile1.getCounty();
					email = userProfile1.getEmail();
					if(email == null){
						email = "";
					}
					body1Data = allTemplateAction.commonBody1(nowDate,pname,paddress,paddress1,ptown,ppostcode,pcounty);
				}
				else if(userName.equals("ThirdParty")){
					int tpId = thirdPartyDAO.getThirdPartyTempId(selectedid);
					String tpId1 = Integer.toString(tpId);	
					//int gpId = thirdPartyDAO.getThirdPartyId(selectedid);
					//String gpId1 = Integer.toString(selectedid);
					//int tpId = thirdPartyDAO.getThirdPartyTempId(gpId);
				//	String tpId1 = Integer.toString(tpId);		
					ThirdParty thirdParty = new ThirdParty();
					thirdParty = thirdPartyDAO.getThirdPartyDetails(tpId1);
					tpName  = thirdParty.getCompanyName();
					tpaddress = thirdParty.getAddress();
					String tpaddress1 = "";
					String tptown = thirdParty.getTown();
					String tppostcode = thirdParty.getPostcode();
					String tpcounty = thirdParty.getCounty();
					email = thirdParty.getCompnyEmail();
					if(email == null){
						email = "";
					}
					body1Data = allTemplateAction.tpCommonBody1(nowDate,tpName,tpaddress,tpaddress1,tptown,tppostcode,tpcounty);
				}
				else if(userName.equals("GP")){
					int gpId = thirdPartyDAO.getThirdPartyId(selectedid);
					String gpId1 = Integer.toString(gpId);
					//String gpId1 = Integer.toString(selectedid);
					ThirdParty thirdParty1 = new ThirdParty();
					thirdParty1 = thirdPartyDAO.getThirdPartyDetails(gpId1);
					GPDAO gpdao = new JDBCGPDAO(connection);
					//int gpId2 = emailTemplateDAO.getGPId(selectedid);
					int gpId2 = gpdao.getGPId(selectedid);
					GP gp  = gpdao.getGPDetail(gpId2);
					gpName = gp.getName();
					gpaddress = thirdParty1.getAddress();	
					String gpaddress1 = "";
					String gptown = thirdParty1.getTown();
					String gppostcode = thirdParty1.getPostcode();
					String gpcounty = thirdParty1.getCounty();				
					email = thirdParty1.getCompnyEmail();					
					if(email == null){
						email = "";
					}
					body1Data = allTemplateAction.commonGPBody1(nowDate,gpName,gpaddress,gpaddress1,gptown,gppostcode,gpcounty,thirdParty1);
				}
				else{
					body1Data = allTemplateAction.commonBody1(nowDate,clientName,address1,address2,town,postcode,county);
					}
				
				
				//templateData = emailTemplateDAO.getEmailTemplateData(tempId);
				
				StringBuffer s = new StringBuffer();
				s.append("<div style = 'text-align: justify;text-justify: inter-word;'>");		
				s.append("<br>Re : "+clientName+", "+address+", DOB: "+dob+".<br><br>");
				
				templateData = emailTemplateDAO.getEmailTemplateData(tempId);
				
				s.append(templateData);
				
				templateData = s.toString();
				
				
				subject = templateName +" : "+ clientName;
				
			}
         	
         	if(userName.equals("Practitioner")){
				
				body2Data = allTemplateAction.commonBody2(PracticeOwnerName,qualification,discipline);
				
			}else{
				
				body2Data = allTemplateAction.commonBody2(practitionerName,qualification,discipline);
			}
	
		//	String body2Data = allTemplateAction.commonBody2(PracticeOwnerName,qualification);
			//System.out.println(body2Data);
			String footerData = allTemplateAction.commonFooter(clinicName);
			//System.out.println(footerData);
			String data = ""+headerData+" "+body1Data+" "+templateData+" "+body2Data+" "+footerData+" "; 
			//System.out.println(data);		
			
			StringBuffer str = new StringBuffer();
			str.append("<div style ='height:auto;margin-left: 30px;margin-right: 30px;'>"+headerData+"</div>");
			str.append("<div class='emailhead' style ='height:auto;margin-left: 30px;margin-right: 30px;'>"+body1Data+" "+templateData+" "+body2Data+"</div>");
			str.append("<div style ='height:auto;margin-left: 30px;margin-right: 30px;'>"+footerData+"</div>");
			
			data = str.toString()+"#"+email+"#"+subject;
			
			response.setContentType("text/html");
			response.setHeader("Cache-Control", "no-cache");
			response.getWriter().write(""+data+""); 
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			connection.close();
		}
		return null;
	}
	
/*	public String preview(){
		if(!verifyLogin(request)){
			return "login";
		}
		Connection connection = null;
		EmailTemplate emailTemplate = new EmailTemplate();
		AllTemplateAction allTemplateAction = new AllTemplateAction();
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");	       
	    Date date1 = new Date();
	    String date = date1.toString();
	    //System.out.println(dateFormat.format(date1));
		String nowDate = dateFormat.format(date1);
		try{
			connection = Connection_provider.getconnection();
			EmailTemplateDAO emailTemplateDAO = new JDBCEmailTemplateDAO(connection);
			ArrayList<EmailTemplate> templateNameList = new ArrayList<EmailTemplate>();
			
			String templateName = request.getParameter("templateName");
			//String templateName = request.getParameter("templateId");
			
			ClientDAO clientDAO = new JDBCClientDAO(connection);
			String id = (String)session.getAttribute("id");
			
			Client client = clientDAO.getClientDetails(id);
			
			String clientName = client.getTitle()+" "+client.getFirstName()+" "+client.getLastName();
			String address = client.getAddress();
			String dob = client.getDob();
			
			String tpName  = clientDAO.getThirdPartyCompanyName(id);
			
			ClinicDAO clinicDAO = new JDBCClinicDAO(connection);
			Clinic clinic = clinicDAO.getCliniclistDetails(loginInfo.getId());
			String clinicName = clinic.getClinicName();
			String PracticeOwnerName = clinic.getClinicOwner();
			String qualification = clinic.getOwner_qualification();
			
			String clinicEmail = clinic.getEmail();
			String clinicPhone = clinic.getContactNo();
			String clinicMobile = clinic.getMobileNo();
			String clinicWebsite = clinic.getWebsiteUrl();
			
			String clinicAddress = "";
			String templateData = "";
			String practitionerName = "",startTime = "",duration="",practitionerEmailId="",clientEmailId="";
			int locationId = 0;
			
			int appointmentid = emailTemplateDAO.getLatestAppointmentId(id);
			//emailTemplate =  emailTemplateDAO.getEmailTemplateData(appointmentid);
			
			DiaryManagementDAO diaryManagementDAO = new JDBCDiaryManagentDAO(connection);
         	ArrayList<NotAvailableSlot>appointmentList = diaryManagementDAO.getAppintmentSendMailDetails(appointmentid);
         	
         	for(NotAvailableSlot notAvailableSlot : appointmentList){
 				UserProfile userProfile = notAvailableSlot.getUserDEtails();
 				client = notAvailableSlot.getClientDetails();
 				Location location = notAvailableSlot.getLocationDetails();
 				
				// emailSend(userProfile.getFullname(), client.getFirstName(), notAvailableSlot.getSTime(), notAvailableSlot.getDuration(), userProfile.getEmail(), client.getEmail(), notAvailableSlot.getCommencing(), location.getLocationname(),location.getContactNo(),location.getAddress(),location.getLocationid());
 				
 				practitionerName = userProfile.getFullname();
 				startTime =  notAvailableSlot.getSTime();
 				duration = notAvailableSlot.getDuration();
 				practitionerEmailId = userProfile.getEmail();
 				clientEmailId =  client.getEmail();
 				String locId = location.getLocationid();
 				locationId = Integer.parseInt(locId);
 				
 			}
         	
			
			String headerData = allTemplateAction.header(clinicName, PracticeOwnerName, qualification, clinicAddress, 
					clinicEmail,clinicMobile, clinicWebsite);
			
			String body1Data = allTemplateAction.commonBody1(nowDate,tpName,address);
			System.out.println(body1Data);
			if(templateName.equalsIgnoreCase("GP Referal Template")){
				templateData = allTemplateAction.gpReferalBody(clientName, clinicAddress, dob);
				System.out.println(templateData);
			}
			else if(templateName.equalsIgnoreCase("On Appoint Booking Template")){
				templateData = allTemplateAction.OnAppointBookingBody(PracticeOwnerName, clinicWebsite, practitionerName, clientName, startTime, duration, practitionerEmailId, clientEmailId, date, locationId);
				System.out.println(templateData);
			}
			else if(templateName.equalsIgnoreCase("Tp Referal Template")){
				templateData = allTemplateAction.tpReferalBody(clientName,address,dob);
				System.out.println(templateData);
			}
			else if(templateName.equalsIgnoreCase("Appt. Confirmation Reminder 1 Template")){
				templateData = allTemplateAction.ApptConfirmationReminder1Body(PracticeOwnerName, date, startTime);
				System.out.println(templateData);
			}
			else if(templateName.equalsIgnoreCase("Appt. Confirmation Reminder 2 Template")){
				templateData = allTemplateAction.ApptConfirmationReminder2Body(PracticeOwnerName, date, startTime);
				System.out.println(templateData);
			}
			else if(templateName.equalsIgnoreCase("Further Treatment Request Template")){
				templateData = allTemplateAction.FurtherTreatmentRequestBody(PracticeOwnerName, clinicName, clinicMobile);
				System.out.println(templateData);
			}
			else if(templateName.equalsIgnoreCase("Final Reminder Template")){
				templateData = allTemplateAction.FinalReminderBody(PracticeOwnerName, clinicName, clinicMobile);
				System.out.println(templateData);
			}
			String body2Data = allTemplateAction.commonBody2(PracticeOwnerName);
			System.out.println(body2Data);
			String footerData = allTemplateAction.commonFooter(clinicName);
			System.out.println(footerData);
			String data = ""+headerData+" "+body1Data+" "+templateData+" "+body2Data+" "+footerData+" "; 
			System.out.println(data);	
			
			//int selectedid = Integer.parseInt(templateName1);
			
			//emailTemplate = emailTemplateDAO.getEmailTemplatePreview(selectedid);
			
			//emailTemplateForm.setId(emailTemplate.getId());
			emailTemplateForm.setTemplateName(templateName);
			emailTemplateForm.setHeaderNote(headerData);
			emailTemplateForm.setBody1Note(body1Data);
			emailTemplateForm.setTemplateData(templateData);
			emailTemplateForm.setBody2Note(body2Data);
			emailTemplateForm.setFooterNote(footerData);
			
			String templateName1 = emailTemplate.getTemplateName();
			String headerNote = emailTemplate.getHeaderNote();
			String body1Note = emailTemplate.getBody1Note();
			String body2Note = emailTemplate.getBody2Note();
			String footerNote = emailTemplate.getFooterNote();
			
			session.setAttribute("templateName",templateName);
			session.setAttribute("headerNote",headerData);
			session.setAttribute("body1Note",body1Data);
			session.setAttribute("templateData", templateData);
			session.setAttribute("body2Note",body2Data);
			session.setAttribute("footerNote",footerData);
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return "previewTPReferalPage";
	}*/
	
	public String back(){
		//setFormDataofEmailTemplate();
		return SUCCESS;
	}
	
	public String emailTemplate() throws Exception{
		if(!verifyLogin(request)){
			return "login";
		}
		Connection connection = null;
		
		try{
			connection = Connection_provider.getconnection();
			EmailTemplateDAO emailTemplateDAO = new JDBCEmailTemplateDAO(connection);			
			//String id = request.getParameter("id");
			String to = request.getParameter("email");
			String cc = request.getParameter("ccEmail");
			String subject = request.getParameter("subject");
			String notes = request.getParameter("body");
			System.out.println(notes);
			//String filename = (String)session.getAttribute("pdfFileName");
			//String[] temp1 = filename.split("/");
			//String filename1 = temp1[1];
			//int invoiceid = (Integer)session.getAttribute("chargesInvoiceid");
			DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
			Date date = new Date();
			String d1 = dateFormat.format(date);
			String[] temp = d1.split("\\s+");
			String date1 = temp[0];
			String time = temp[1];
			
			//int result = emailTemplateDAO.saveEmailLogDetails(to, cc, subject, notes, date1,time);
			
			String type = "Letter";
			int appointmentid = 0,invoiceid = 0;
			EmailLetterLog emailLetterLog = new EmailLetterLog();
			emailLetterLog.setAppointmentid(appointmentid);
			emailLetterLog.setInvoiceid(invoiceid);
			emailLetterLog.setType(type);
			
			EmbeddedImageEmailUtil.sendMail(connection, loginInfo.getId(), to, cc, subject, notes,loginInfo,emailLetterLog);
			
			emailTemplateForm.setMessage("Mail Sent Successfully!!");
			addActionMessage("Mail Sent Successfully!!");
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			connection.close();
		}
		return SUCCESS;
		
	}
	public String getattachmentfile() throws Exception{
		int i=1;
		Connection connection = null;
		try{
			connection = Connection_provider.getconnection();
			EmailTemplateDAO emailTemplateDAO = new JDBCEmailTemplateDAO(connection);
			String tempId = request.getParameter("tempId");
			//String filename=emailTemplateDAO.getFileNameBytemplate(tempId);
			
			ArrayList<EmailTemplate> fileList = emailTemplateDAO.getFileNameBytemplate(tempId);
	        StringBuffer buffer=new StringBuffer();
	    	
	    	for (EmailTemplate emailTemplate : fileList) {
	    		
	    		
				
	    		 buffer.append("<tr>");
	    		 buffer.append("<td>"+i+++"</td>");
	 		  
	 		     buffer.append("<td>"+emailTemplate.getUserImageFileName()+"</td>");
	 		     buffer.append("<td><a href='#' onclick='deleteattachment("+emailTemplate.getId()+","+tempId+")'><i class='fa fa-trash-o'></i></a></td>");
	 		    
	 		     buffer.append("</tr>");

	    		}
			
           
			
			response.setContentType("text/html");
			response.setHeader("Cache-Control", "no-cache");
			response.getWriter().write(buffer.toString());
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			connection.close();
		}
		return null;
	}
	
	public String deleteAttachment() {
		try {
			if (!verifyLogin(request)) {
				return "login";
			}

			Connection connection = Connection_provider.getconnection();
			StringBuilder buffer = new StringBuilder();
			BufferedReader reader = request.getReader();
			String line;
			while ((line = reader.readLine()) != null) {
				buffer.append(line);
			}
			EmailTemplateDAO emailTemplateDAO = new JDBCEmailTemplateDAO(connection);

			String data = buffer.toString();
			JSONObject jsonObject = new JSONObject(data);
			String id = jsonObject.getString("id");
		
			int res = emailTemplateDAO.deleteAttachment(id);

			JSONObject jsonobj = new JSONObject();

			String response1 = jsonobj.toString();
			response.setContentType("application/json");
			response.setHeader("Cache-Control", "no-cache");
			response.getWriter().write(response1);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	public EmailTemplateForm getModel() {
		// TODO Auto-generated method stub
		return emailTemplateForm;
	}

	public void prepare() throws Exception {
		Connection connection = null;
		try{
			connection = Connection_provider.getconnection();
			EmailTemplateDAO emailTemplateDAO = new JDBCEmailTemplateDAO(connection);
			ArrayList<EmailTemplate> templateNameList = emailTemplateDAO.getEmailTemplateNameList();
			emailTemplateForm.setTemplateNameList(templateNameList);
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			connection.close();
		}
		
	}

}
