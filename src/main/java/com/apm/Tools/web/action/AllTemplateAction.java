package com.apm.Tools.web.action;

import java.sql.Connection;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.apm.Accounts.eu.bi.AccountsDAO;
import com.apm.Accounts.eu.blogic.jdbc.JDBCAccountsDAO;
import com.apm.DiaryManagement.eu.bi.ClientDAO;
import com.apm.DiaryManagement.eu.bi.CompleteAptmDAO;
import com.apm.DiaryManagement.eu.bi.NotAvailableSlotDAO;
import com.apm.DiaryManagement.eu.blogic.jdbc.JDBCClientDAO;
import com.apm.DiaryManagement.eu.blogic.jdbc.JDBCCompleteAptmDAO;
import com.apm.DiaryManagement.eu.blogic.jdbc.JDBCNotAvailableSlotDAO;
import com.apm.DiaryManagement.eu.entity.Client;
import com.apm.DiaryManagement.eu.entity.CompleteAppointment;
import com.apm.DiaryManagement.eu.entity.NotAvailableSlot;
import com.apm.DiaryManagement.web.action.EmbeddedImageEmailUtil;
import com.apm.Emr.eu.bi.ConsultationNoteDAO;
import com.apm.Emr.eu.blogic.jdbc.JDBCConsultationNoteDAO;
import com.apm.InstantMessage.web.action.Email;
import com.apm.Log.eu.entity.EmailLetterLog;
import com.apm.Master.eu.bi.TreatmentTypeDAO;
import com.apm.Master.eu.blogic.jdbc.JDBCTreatmentTypeDAO;
import com.apm.Master.eu.entity.TreatmentType;
import com.apm.Registration.eu.bi.ClinicDAO;
import com.apm.Registration.eu.bi.UserProfileDAO;
import com.apm.Registration.eu.blogic.jdbc.JDBCClinicDAO;
import com.apm.Registration.eu.blogic.jdbc.JDBCUserProfileDAO;
import com.apm.Registration.eu.entity.Clinic;
import com.apm.Registration.eu.entity.UserProfile;
import com.apm.ThirdParties.eu.entity.ThirdParty;
import com.apm.Tools.web.form.EmailTemplateForm;
import com.apm.TreatmentEpisode.eu.bi.TreatmentEpisodeDAO;
import com.apm.TreatmentEpisode.eu.blogic.jdbc.JDBCTreatmentEpisode;
import com.apm.TreatmentEpisode.eu.entity.TreatmentEpisode;
import com.apm.common.eu.blogic.jdbc.Connection_provider;
import com.apm.common.utils.DateTimeUtils;
import com.apm.common.web.action.BaseAction;
import com.apm.common.web.common.helper.LoginHelper;
import com.apm.common.web.common.helper.LoginInfo;
import com.apm.main.common.constants.Constants;
import com.apm.sendemail.java4s.Testing;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.Preparable;

public class AllTemplateAction {

	/*HttpServletRequest request = ServletActionContext.getRequest();
	//HttpServletRequest request = (HttpServletRequest) ActionContext.getContext().get(ServletActionContext.HTTP_REQUEST);
	HttpServletResponse response = (HttpServletResponse)ActionContext.getContext().get(ServletActionContext.HTTP_RESPONSE);
	HttpSession session = request.getSession(true);
	LoginInfo loginInfo = LoginHelper.getLoginInfo(request);*/
	//EmailTemplateForm emailTemplateForm = new EmailTemplateForm();
	private String result="";
	
	/*public String execute(){
		if(!verifyLogin(request)){
			return "login";
		}
		return SUCCESS;
	}
	*/
	
	
	
	
	
	
	
	public String header(String clinicName, String PracticeOwnerName, String qualification, String clinicAddress, 
							String clinicEmail, String clinicMobile, String clinicWebsite,int clinicid,Connection connection)throws Exception{
		
		try{	
			
			
			
			String value = null;
			if(clinicName == value){
				clinicName = "";
			}
			if(PracticeOwnerName == value){
				PracticeOwnerName = "";
			}
			if(qualification == value){
				qualification = "";
			}
			if(clinicEmail == value){
				clinicEmail = "";
			}
			if(clinicMobile == value){
				clinicMobile = "";
			}
			if(clinicWebsite == value){
				clinicWebsite = "";
			}
			
			int locationId = 0;
			String clinicAddress1 = "",pincode = "",location = "";
			
			AccountsDAO accountsDAO = new JDBCAccountsDAO(connection);
			ArrayList<Clinic> locationList = accountsDAO.getLocationAddress(clinicid);
			
			//String result = "";
			StringBuffer str = new StringBuffer();
			str.append("<div  align='center'><b>");
			str.append("<font size='4'>"+clinicName+"</font><br>");
			if(!PracticeOwnerName.equals("")){
				str.append("<font size='2'>"+PracticeOwnerName+", "+qualification+"</font></b><br>");
			}else{
				str.append("</b>");
			}
			
			for(Clinic clinic2 : locationList){
				locationId = clinic2.getLocationid();
				clinicAddress = clinic2.getAddress();	
				clinicAddress1 = clinicAddress.replace(","," | ");
				pincode = clinic2.getPinCode();
				location = clinic2.getLocationname();
				
				if(clinicAddress1 == value){
					clinicAddress1 = "";
				}
				if(pincode == value){
					pincode = "";
				}
				
						
			str.append("<font size='1'>"+clinicAddress1+" | "+location+" | "+pincode+"</font><br>");
			}
			
			if(!clinicWebsite.equals("")){
				str.append("<font size='1'>TEL/FAX : "+clinicMobile+" E : "+clinicEmail+" W : "+clinicWebsite+"</font><br></div><br>");
			}else{
				str.append("<font size='1'>TEL/FAX : "+clinicMobile+" E : "+clinicEmail+"</font><br></div><br>");
			}
			
			
//			System.out.println(str.toString());
			result = str.toString();
			
			/*String logo = "<img src=\"cid:image1\" width=\"20%\" height=\"10%\" />";
			result = logo + result;*/
			
			//response.setContentType("text/html");
			//response.setHeader("Cache-Control", "no-cache");			
			//response.getWriter().write(""+str.toString()+""); 
			
		}catch(Exception e){
			e.printStackTrace();
		}		
		finally {
			
		}
		return result;
	}
	
	
public String commonGPBody1(String nowDate, String Name, String address, String address1, String town, String postcode, String county,ThirdParty thirdParty)throws Exception {
		
		try{
			String value = null;
			if(nowDate == value){
				nowDate = "";
			}
			if(Name == value){
				Name = "";
			}
			if(address == value){
				address = "";
			}
			if(address1 == value){
				address1 = "";
			}
			if(postcode == value){
				postcode = "";
			}
			if(town == value){
				town = "";
			}
			if(county == value){
				county = "";
			}
			StringBuffer str = new StringBuffer();			
			//str.append("<div style ='font-size: 12px;'>");
			str.append(""+Name+"<br>");
			str.append(""+thirdParty.getCompanyName()+"<br>");
			str.append(""+address+"");
			if(!address1.equals(""))
			{
				str.append(","+address1+"<br>");
			}else{
				str.append("<br>");
			}
			
			str.append(""+town+"<br>");
			str.append(""+postcode+"");
			str.append("<div style='text-align:right;'>Date : "+nowDate+"</div>");
			//str.append(""+county+"<br>");
			str.append("<br>Dear "+Name+", <br>");
			//str.append("</div>");
//			System.out.println(str.toString());
			result = str.toString();
			
		}catch(Exception e){
			e.printStackTrace();
		}
				
		return result;
	}

	
	public String commonBody1(String nowDate, String Name, String address, String address1, String town, String postcode, String county)throws Exception {
		
		try{
			
			
			
			String value = null;
			if(nowDate == value){
				nowDate = "";
			}
			if(Name == value){
				Name = "";
			}
			if(address == value){
				address = "";
			}
			if(address1 == value){
				address1 = "";
			}
			if(postcode == value){
				postcode = "";
			}
			if(town == value){
				town = "";
			}
			if(county == value){
				county = "";
			}
			StringBuffer str = new StringBuffer();			
			//str.append("<div style ='font-size: 12px;'>");
			str.append(""+Name+"<br>");
			str.append(""+address+"");
			if(!address1.equals(""))
			{
				str.append(","+address1+"<br>");
			}else{
				str.append("<br>");
			}
			
			str.append(""+town+"<br>");
			str.append(""+postcode+"");
			str.append("<div style='text-align:right;'>Date : "+nowDate+"</div>");
			//str.append(""+county+"<br>");
			str.append("<br>Dear "+Name+", <br>");
			//str.append("</div>");
//			System.out.println(str.toString());
			result = str.toString();
			
		}catch(Exception e){
			e.printStackTrace();
		}	
		return result;
	}
	

	public String commonBody2(String practiceOwnerName, String qualification,String discilipine) throws Exception{
		
		
		try{
			String value = null;
			if(practiceOwnerName == value){
				practiceOwnerName = "";
			}
			if(qualification == value){
				qualification = "";
			}
			
			StringBuffer str = new StringBuffer();
			//str.append("<div style ='font-size: 12px;'>");
			str.append("<br>Best Regards, <br><br><br>"+practiceOwnerName+", "+qualification+"<br>"+discilipine+"<br>");
			//str.append("</div>");
			
			result = str.toString();
			
		}catch(Exception e){
			e.printStackTrace();
		}	
		return result;
	}
	
	public String commonFooter(String clinicName)throws Exception {
		
		
		try{
			String value = null;
			if(clinicName == value){
				clinicName = "";
			}
			
			StringBuffer str = new StringBuffer();
			str.append("<div  style = 'text-align: justify;text-justify: inter-word;'>");
			str.append("<br><br><br><p class='' style ='font-size: 12px;'>");
			str.append("The information contained in this message is privileged and confidential and is intended only for the use of the individual or entity named.If the reader of this message is not the intended recipient, you are hereby notified that any dissemination, distribution or copying of this communication and any attachments is strictly prohibited.<br>"+clinicName+" may monitor email traffic data and also the content of email for the purposes of security and staff training.If you have received this communication in error, please inform us and return the message and attachments to us at the above address.");
			str.append("</p>");
			str.append("</div>");
			
			str.append("<br><br>");
			/*str.append("<br><br><a href='www.twitter.com'><img src='img/Entypo_f309(0)_32.png'></a>&nbsp;&nbsp;<a href='www.facebook.com'><img src='img/Entypo_f30c(0)_32.png'></a>&nbsp;&nbsp;<a href='www.gmail.com'><img src='img/Entypo_f30f(0)_32.png'></a>&nbsp;<br>");
		*/	
//			System.out.println(str.toString());
			result = str.toString();
			
		}catch(Exception e){
			e.printStackTrace();
		}	
		return result;
	}

	public String tpReferalBody(String clientName, String address, String dob, String reference)throws Exception {
		
		
		try{
			String value = null;
			if(clientName == value){
				clientName = "Not Available";
			}
			if(address == value){
				address = "";
			}
			if(dob == value){
				dob = "Not Available";
			}
			if(reference == value){
				reference = "(Not Available)";
			}
			
			StringBuffer str = new StringBuffer();
			str.append("<div style = 'text-align: justify;text-justify: inter-word;'>");		
			str.append("<br>Re : "+clientName+", "+address+", DOB: "+dob+".<br>");
			str.append("<br>The above patient has been referred to us by "+reference+" for assessment and treatment. <br>");
			str.append("<br>We will proceed with suitable treatment and keep you informed of their progress.<br>");
			str.append("</div>");
			
//			System.out.println(str.toString());
			result = str.toString();
			
		}catch(Exception e){
			e.printStackTrace();
		}	
		return result;
	}
	
	public String gpReferalBody(String clientName, String address, String dob)throws Exception {
		try{
			String value = null;
			String value1 = "null null";
			if(clientName == value || clientName == value1){
				clientName = "";
			}
			if(address == value){
				address = "";
			}
			if(dob == value){
				dob = "Not Available";
			}
			
			StringBuffer str = new StringBuffer();
			str.append("<div style = 'text-align: justify;text-justify: inter-word;'>");		
			str.append("<br>Re : "+clientName+", "+address+", DOB: "+dob+".<br>");
			str.append("<br>Thank you for referring the above patient for treatment.");
			str.append("<br>We will keep you informed of their progress.<br>");
			str.append("</div>");
			
//			System.out.println(str.toString());
			result = str.toString();
			
		}catch(Exception e){
			e.printStackTrace();
		}	
		return result;
	}
	
	public String OnAppointBookingBody(String practiceOwnerName, String clinicWebsite, String practitionerName,String clientName,String startTime,String duration,String practitionerEmailId,String clientEmailId,String date,int locationId,Connection connection ) throws Exception{
		
	
				
		try{
			
			ClinicDAO clinicDAO = new JDBCClinicDAO(connection);
			Clinic clinic = clinicDAO.getLocationDetails(locationId);
			String address = clinic.getAddress();
			String address1="",address2="";
			if(address!=null){
			//	int temp = address.length();
			String[] temp = address.split(",",2);
			 address1 = temp[0];
			 if(address != address1){
				 address2 = temp[1];
			 }else{
				 address2 = "";
			 }
			 
			}
			else{
				address1 = "Not Available";
			}
			String location = clinic.getLocationname();
			String postCode = clinic.getPinCode();
			String contactNo = clinic.getContactNo();
			String locDirection = clinic.getLoc_direction();
			
			String value = null;
			
			if(startTime == value){
				startTime = "";
			}
			
			String startTime3 = "";
			if(startTime!=""){
			String temp1[] = startTime.split(":");
			String startTime1 = temp1[0];
			String startTime2 = temp1[1];
			int st = Integer.parseInt(startTime1);
			int st1 = 0;
			
			if(st>12){
				st1 = st-12;
				startTime3 = st1+":"+startTime2+" pm";
			}
			else if(st==12){
				startTime3 = startTime+" pm";
			}
			else{
				startTime3 = startTime+" am";
			}
			}
			
			if(address1 == value){
				address1 = "";
			}
			if(address2 == value){
				address2 = "";
			}
			if(location == value){
				location = "";
			}
			if(postCode == value){
				postCode = "";
			}
			if(locDirection == value){
				locDirection = "";
			}
			if(clinicWebsite == value){
				clinicWebsite = "";
			}
			if(practiceOwnerName == value){
				practiceOwnerName = "";
			}
			if(date == value){
				date = "";
			}
			if(date == ""){
				date = "(Not Available)";
			}
			
			
			StringBuffer str = new StringBuffer();
			str.append("<div style = 'text-align: justify;text-justify: inter-word;'>");	
			str.append("<br>Just a note to confirm the appointment you have booked with us on "+date+" at "+startTime3+".<br>");
			str.append("<br>The address of our clinic is:<br>"+address1+",<br>"+address2+"<br>"+location+"<br>"+postCode+".<br>");
			str.append("<br>"+locDirection);
			str.append("<br>Directions can also be found on our website "+clinicWebsite+". ");
			str.append("There are many short stay car parks within 2 minutes walking distance from the clinic.<br>");
			str.append("<br>If you require a chaperon you are welcome to bring someone to accompany you on your visit. ");
			str.append("If you need to change this appointment for any reason, we ask for 24 hours notice or a cancellation fee will be charged. ");
			str.append("Please confirm you have received this email. <br>");
			str.append("<br>Please arrive 10 minutes early in order for us to do the paperwork.");
			str.append("<br>We look forward to seeing you.<br><br>");
			str.append("</div>");
			//str.append("<br><br>Your Sincerely <br>"+practiceOwnerName+",<br><br>");

//			System.out.println(str.toString());
			result = str.toString();
			
		}catch(Exception e){
			e.printStackTrace();
		}
		return result;
	}
	
	public String ApptConfirmationReminder1Body(String practiceOwnerName,String date, String startTime)throws Exception {
		try{
			String value = null;
			if(date == value){
				date = "";
			}
			if(date == ""){
				date = "(Not Available)";
			}
			if(startTime == value){
				startTime = "";
			}
			if(practiceOwnerName == value){
				practiceOwnerName = "";
			}
			
			String startTime3 = "";
			if(startTime!=""){
			String temp1[] = startTime.split(":");
			String startTime1 = temp1[0];
			String startTime2 = temp1[1];
			int st = Integer.parseInt(startTime1);
			int st1 = 0;
			
			if(st>12){
				st1 = st-12;
				startTime3 = st1+":"+startTime2+" pm";
			}
			else if(st==12){
				startTime3 = startTime+" pm";
			}
			else{
				startTime3 = startTime+" am";
			}
			}
			
			StringBuffer str = new StringBuffer();

//			System.out.println(str.toString());
			str.append("<div style = 'text-align: justify;text-justify: inter-word;'>");
			str.append("<br>Just a reminder about your forthcoming appointment with us on "+date+" at "+startTime3+".<br>");
			str.append("<br>If you do need to change this appointment for any reason we ask for 24 hours notice or a cancellation fee will be charged.<br>");
			str.append("<br>We look forward to seeing you.<br>");
			str.append("</div>");
			//str.append("<br><br><br>Your Sincerely <br>"+practiceOwnerName+",<br><br><br>");
			result = str.toString();
			
		}catch(Exception e){
			e.printStackTrace();
		}	
		return result;

	}
	
	public String ApptConfirmationReminder2Body(String practiceOwnerName,String date, String startTime) throws Exception{
		try{
			String value = null;
			if(date == value){
				date = "";
			}
			if(date == ""){
				date = "(Not Available)";
			}
			if(startTime == value){
				startTime = "";
			}
			if(practiceOwnerName == value){
				practiceOwnerName = "";
			}
			
			String startTime3 = "";
			if(startTime!=""){
			String temp1[] = startTime.split(":");
			String startTime1 = temp1[0];
			String startTime2 = temp1[1];
			int st = Integer.parseInt(startTime1);
			int st1 = 0;
		
			
			if(st>12){
				st1 = st-12;
				startTime3 = st1+":"+startTime2+" pm";
			}
			else if(st==12){
				startTime3 = startTime+" pm";
			}
			else{
				startTime3 = startTime+" am";
			}
			}
			
			StringBuffer str = new StringBuffer();

//			System.out.println(str.toString());
			str.append("<div style = 'text-align: justify;text-justify: inter-word;'>");
			str.append("<br>Just a reminder about your forthcoming appointment with us on "+date+" at "+startTime3+".<br>");
			str.append("<br>If you do need to change this appointment for any reason we ask for 24 hours notice or a cancellation fee will be charged.<br>");
			str.append("<br>We look forward to seeing you.<br><br>");
			str.append("</div>");
			//str.append("<br><br><br>Your Sincerely <br>"+practiceOwnerName+",<br><br><br>");
			result = str.toString();
			
		}catch(Exception e){
			e.printStackTrace();
		}	
		return result;

	}
	
	public String FurtherTreatmentRequestBody(String practiceOwnerName,String clinicName,String clinicMobile) throws Exception{
		try{
			String value = null;
			if(clinicName == value){
				clinicName = "";
			}
			if(clinicMobile == value){
				clinicMobile = "";
			}
			if(practiceOwnerName == value){
				practiceOwnerName = "";
			}
			StringBuffer str = new StringBuffer();

//			System.out.println(str.toString());
			str.append("<div style = 'text-align: justify;text-justify: inter-word;'>");
			str.append("<br><br>Further Treatment has been authorised for you by your insurance company.<br>");
			str.append("<br>To Book your appointment please call <"+clinicName+" and "+clinicMobile+"> during office hours as soon as possible. <br><br>");
			str.append("</div>");
			//str.append("<br><br><br>Your Sincerely <br>"+practiceOwnerName+",<br><br><br>");
			result = str.toString();
			
		}catch(Exception e){
			e.printStackTrace();
		}	
		return result;

	}
	
	public String FinalReminderBody(String practiceOwnerName,String clinicName,String clinicMobile)throws Exception {
		try{
			String value = null;
			if(clinicName == value){
				clinicName = "";
			}
			if(clinicMobile == value){
				clinicMobile = "";
			}
			if(practiceOwnerName == value){
				practiceOwnerName = "";
			}
			
			StringBuffer str = new StringBuffer();

//			System.out.println(str.toString());
			str.append("<div style = 'text-align: justify;text-justify: inter-word;'>");
			str.append("<br>You need to complete your treatment with us. We have tried to contact you several times and left messages.<br>");
			str.append("<br>To Book your appointment please call <"+clinicName+" and "+clinicMobile+"> during office hours as soon as possible.<br>");
			str.append("<br>If we do not hear from you within next 48 hrs, ");
			str.append("we are required to inform your insurance company and they may close your case.<br><br>");
			str.append("</div>");
			//str.append("<br><br><br>Your Sincerely <br>"+practiceOwnerName+",<br><br><br>");
			result = str.toString();
			
		}catch(Exception e){
			e.printStackTrace();
		}	
		return result;

	}
	
/*	public EmailTemplateForm getModel() {
		// TODO Auto-generated method stub
		return emailTemplateForm;
	}

	public void prepare() throws Exception {
		// TODO Auto-generated method stub
		
	}*/

	public String tpCommonBody1(String nowDate, String tpName,String tpaddress, 
			String tpaddress1, String tptown,String tppostcode, String tpcounty)throws Exception {
		try{
			String value = null;
			if(nowDate == value){
				nowDate = "";
			}
			if(tpName == value){
				tpName = "";
			}
			if(tpaddress == value){
				tpaddress = "";
			}
			if(tpaddress1 == value){
				tpaddress1 = "";
			}
			if(tppostcode == value){
				tppostcode = "";
			}
			if(tptown == value){
				tptown = "";
			}
			if(tpcounty == value){
				tpcounty = "";
			}
			StringBuffer str = new StringBuffer();
			str.append("Date : "+nowDate+"");
			str.append("<br><br>"+tpName+"<br>");
			str.append(""+tpaddress+"<br>");
			if(tpaddress1!="")
			{
				str.append(""+tpaddress1+"<br>");
			}
			
			str.append(""+tptown+"<br>");
			str.append(""+tppostcode+"<br>");
			str.append(""+tpcounty+"<br>");
			str.append("<br>Dear Sir/Madam, <br>");
			
//			System.out.println(str.toString());
			result = str.toString();
			
		}catch(Exception e){
			e.printStackTrace();
		}	
		return result;
	}

	
	
	
public void emailSend(String practitionerName,String clientName,String startTime,String duration,String practitionerEmailId,String clientEmailId,String date,String location,String contactNo,String address,String locationID,String clientId,String userQualification,int appointmentid,String discipline,Connection connection,LoginInfo loginInfo) throws SQLException{
		
		
		
		try{
			
			
			
		
		int activity = 1;
		/*LoginInfo loginInfo = (LoginInfo)session.getAttribute("logininfo");
		
		connection = getconnection(loginInfo.getDbName());*/
		
	 	ClinicDAO clinicDAO = new JDBCClinicDAO(connection);
	 	
	 	Clinic locationdirection = new Clinic();
	 	locationdirection = clinicDAO.getLocationDetails(Integer.parseInt(locationID));
	 	Clinic clinic = new Clinic();
	 	int clinicID = loginInfo.getId();
	 	clinic = clinicDAO.getCliniclistDetails(loginInfo.getClinicid());
	 	String qualification = clinic.getOwner_qualification();
		
		String subject = "Appointment Details";

		StringBuffer str = new StringBuffer();

	   /* String filePath = (String)session.getAttribute("logopath");	    
	    File file = new File(filePath);
	    
	    String twitter = (String)session.getAttribute("twitter");	    
	    file = new File(twitter);
	    String imgt = "<a href='www.twitter.com'><img src=\"cid:imaget\" /></a>";
	    
	    String fb = (String)session.getAttribute("fb");	    
	    file = new File(fb);
	    String imgf = "<a href='www.facebook.com'><img src=\"cid:imagef\" /></a>";
	    
	    String gml = (String)session.getAttribute("gml");	    
	    file = new File(gml);
	    String imgg = "<a href='accounts.google.com'><img src=\"cid:imageg\" /></a>";*/
	    
	    ClientDAO clientDAO = new JDBCClientDAO(connection);
	 	Client client2 = clientDAO.getClientDetails(clientId);
	 	String addr = "";

	 	if(!discipline.equals("")){
	 		discipline = "Chartered" + " " +discipline;
			discipline = "("+discipline+")";
	 	}
	   
	    
		
		//Client Mail
	    
	 	AllTemplateAction allTemplateAction = new AllTemplateAction();
	     
	     int locationId = Integer.parseInt(locationID);
			
		String headerData = allTemplateAction.header(clinic.getClinicName(), clinic.getClinicOwner(), clinic.getOwner_qualification(), locationdirection.getAddress(), 
					clinic.getEmail(),locationdirection.getContactNo(), clinic.getWebsiteUrl(),loginInfo.getClinicid(),connection);
		
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");	       
	    Date date1 = new Date();
	    String nowDate = dateFormat.format(date1);
			
		String body1Data = allTemplateAction.commonBody1(nowDate,clientName,address,addr,client2.getTown(),client2.getPostCode(),client2.getCounty());
		
		date = DateTimeUtils.changeDateFormatToddmmyyyy(date);
		date = DateTimeUtils.changeDateFormatTemplate(date);

		String onAppointBookingBody = allTemplateAction.OnAppointBookingBody(clinic.getClinicOwner(), clinic.getWebsiteUrl(), practitionerName, clientName, startTime, duration, practitionerEmailId, clientEmailId, date, locationId,connection);
			
		String body2Data = allTemplateAction.commonBody2(practitionerName, userQualification,discipline);
			
		String footerData = allTemplateAction.commonFooter(clinic.getClinicName());
		
	
		//String img = "<img src=\"cid:image1\" width=\"20%\" height=\"10%\" />";
		//String data = img+headerData+body1Data+onAppointBookingBody+body2Data+footerData+"  "+imgt+" "+imgf+" "+imgg;
		
		String data = headerData+body1Data+onAppointBookingBody+body2Data+footerData+" ";
		
//		System.out.println(data);
		
			//EmbeddedImageEmailUtil.sendMail(clientEmailId, "", subject, str1.toString());
		
	/*	String twitter = request.getRealPath("/img/Entypo_f309(0)_32.png/");      	    
		    File file1 = new File(twitter);
		String imgt = "<a href='www.twitter.com'><img src=\"cid:imaget\" /></a>";
		String fb = request.getRealPath("/img/Entypo_f30c(0)_32.png/");      	    
		    file1 = new File(fb);
		String imgf = "<a href='www.facebook.com'><img src=\"cid:imagef\" /></a>";
		String gml = request.getRealPath("/img/Entypo_f30f(0)_32.png/");      	    
		    file1 = new File(gml);
		String imgg = "<a href='www.gmail.com'><img src=\"cid:imageg\" /></a>";
		data = data+" "+imgt+" "+imgf+" "+imgg;*/
		
		int id = loginInfo.getClinicid();
		/*if(file.exists()){			
			
	    	Map<String, String> inlineImages = new HashMap<String, String>();
		    inlineImages.put("imaget", twitter);
		    inlineImages.put("imagef", fb);
		    inlineImages.put("imageg", gml);
	    	
	    	// Map<String, String> inlineImages = new HashMap<String, String>();
		     inlineImages.put("image1", filePath);
		     EmbeddedImageEmailUtil.send(connection,id,clientEmailId, "", subject, data, inlineImages,loginInfo);

	    }
		else{
			EmbeddedImageEmailUtil.sendMail(connection,id,clientEmailId, "", subject, data,loginInfo);

		}*/
		//EmailLetterLogDAO emailLetterLogDAO = new JDBCEmailLetterLogDAO(connection);
		//String from = clinic.getEmail();
		String type = "Email";
		int invoiceid = 0;
		//int result = emailLetterLogDAO.saveEmailLetterLogDetails(from, clientEmailId, subject, type, invoiceid,appointmentid);		
		EmailLetterLog emailLetterLog = new EmailLetterLog();
		emailLetterLog.setAppointmentid(appointmentid);
		emailLetterLog.setInvoiceid(invoiceid);
		emailLetterLog.setType(type);
		
		emailLetterLog.setClientId(clientId);
		
		//EmbeddedImageEmailUtil.sendMailForForgotPassword(clientEmailId, "", subject, "hello world");
		
		EmbeddedImageEmailUtil.sendMailAdvance(connection,id,clientEmailId, "", subject, data,loginInfo,emailLetterLog);
		if(loginInfo.getUserType()==5){
			EmbeddedImageEmailUtil.sendMailAdvance(connection,id,clinic.getEmail(), "", subject, data,loginInfo,emailLetterLog);
		}
		
		//Activity Set
				NotAvailableSlotDAO notAvailableSlotDAO = new JDBCNotAvailableSlotDAO(connection);
				String CheckMailToSend = clinicDAO.IsMailSend(loginInfo.getId());
		        if(CheckMailToSend.equals("true")){
				int result1 = notAvailableSlotDAO.saveActivity(Integer.parseInt(clientId),activity);
		        }
		
//		mailTLS.sendMail(clientEmailId,subject, str1.toString());
		}catch (Exception e) {
			e.printStackTrace();
			
		}
		


			
			
		}


public void sendDischargeEmail(String patientid, String condition,
		String practionerId, String apmtId, LoginInfo loginInfo,
		Connection connection,String treatmentEpisodeid) throws Exception{
	
	try {
		
		ClinicDAO clinicDAO = new JDBCClinicDAO(connection);
		Clinic cliniclist = clinicDAO.getCliniclistDetails(loginInfo.getClinicid());
		
		String to = cliniclist.getEmail();
		//String to = loginInfo.getEmailUserName();
		String cc = "";
		String subject = "";
		String bodyText = "";
		EmailLetterLog emailLetterLog = new EmailLetterLog();
		
		ClientDAO clientDAO = new JDBCClientDAO(connection);
		Client client = clientDAO.getClientDetails(patientid);
		
		String clientName = client.getTitle() + " " + client.getFirstName() + " " + client.getLastName();
		
		subject = clientName + "," + " " + client.getDob() + "," + " " + client.getPostCode();
		
		emailLetterLog.setLastModified(DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone()));
		emailLetterLog.setType("Discharged");
		emailLetterLog.setInvoiceid(0);
		emailLetterLog.setAppointmentid(Integer.parseInt(apmtId));
		emailLetterLog.setClientId(patientid);
		
		StringBuffer str = new StringBuffer();
		
		str.append("Dear Admin, This is a auto generated email by system to inform you that following client has been discharged, please initiate any outstanding activities to close the account.<br><br> ");
		str.append("<table width='100%'>");
		str.append("<cols widt='10%'>");
		str.append("<cols widt='2%'>");
		str.append("<cols widt='50%'>");
		str.append("<tr>");
			str.append("<td>Client Name<td>");
			str.append("<td>:</td>");
			str.append("<td>"+clientName+"</td>>");
			str.append("</tr>");
			
			str.append("<tr>");
			str.append("<td>DoB<td>");
			str.append("<td>:</td>");
			str.append("<td>"+client.getDob()+"</td>>");
			str.append("</tr>");
			
			String address = client.getAddress() + "," + " " + client.getTown() + "," + " " + client.getPostCode(); 
			
			str.append("<tr>");
			str.append("<td>Address<td>");
			str.append("<td>:</td>");
			str.append("<td>"+client.getAddress()+"</td>>");
			str.append("</tr>");
			
			TreatmentEpisodeDAO treatmentEpisodeDAO = new JDBCTreatmentEpisode(connection);
			TreatmentEpisode treatmentEpisode = treatmentEpisodeDAO.getTreatmentEpisodeData(treatmentEpisodeid);
			
			CompleteAptmDAO completeAptmDAO = new JDBCCompleteAptmDAO(connection);
			CompleteAppointment completeAppointment =  completeAptmDAO.getInsuranceCompanyName(patientid);
			String tpName = completeAppointment.getInsuranceCompanyName();
			
			String trtmentEpisode = treatmentEpisode.getSessions() + " " + "Session " + tpName + " " + treatmentEpisode.getTreatmentEpisodeName();
			str.append("<tr>");
			str.append("<td>Treatment Episode<td>");
			str.append("<td>:</td>");
			str.append("<td>"+trtmentEpisode+"</td>>");
			str.append("</tr>");
			
			TreatmentType treatmentType1 = new TreatmentType();
			TreatmentTypeDAO treatmentTypeDAO = new JDBCTreatmentTypeDAO(connection);
			TreatmentType treatmentType = treatmentTypeDAO.getTreatmentType(Integer.parseInt(condition), treatmentType1);
			
			
			
			str.append("<tr>");
			str.append("<td>Condition<td>");
			str.append("<td>:</td>");
			str.append("<td>"+treatmentType.getTreatmentName()+"</td>>");
			str.append("</tr>");
			NotAvailableSlotDAO notAvailableSlotDAO = new JDBCNotAvailableSlotDAO(connection);
			NotAvailableSlot notAvailableSlot = notAvailableSlotDAO.getApmtConsNoteData(apmtId);
			
			String payee = "";
			if(client.getWhopay().equals(Constants.PAY_BY_CLIENT)){
				payee = "Self ("+clientName+")";
			}else{
				payee = "TP ("+tpName+")";
			}
			
			str.append("<tr>");
			str.append("<td>Payee<td>");
			str.append("<td>:</td>");
			str.append("<td>"+payee+"</td>>");
			str.append("</tr>");
			
			ClinicDAO clinicListDAO = new JDBCClinicDAO(connection);
			Clinic clinic = new Clinic();
			clinic = clinicListDAO.getCliniclistDetails(loginInfo.getClinicid());
			String location = clinic.getCity() + " ("+clinic.getLocationname()+")";
			
			str.append("<tr>");
			str.append("<td>Location<td>");
			str.append("<td>:</td>");
			str.append("<td>"+location+"</td>>");
			str.append("</tr>");
			
			UserProfileDAO userProfileDAO = new JDBCUserProfileDAO(connection);
			UserProfile userprofile = userProfileDAO.getUserprofileDetails(notAvailableSlot.getDiaryUserId());
			String practitonerName = userprofile.getInitial() + " " + userprofile.getFirstname() + " " + userprofile.getLastname();
			
			str.append("<tr>");
			str.append("<td>Practitioner<td>");
			str.append("<td>:</td>");
			str.append("<td>"+practitonerName+"</td>>");
			str.append("</tr>");
			
			String tpid = client.getTypeName();
			ConsultationNoteDAO consultationNoteDAO = new JDBCConsultationNoteDAO(connection);
			double debit = consultationNoteDAO.getInvoiceDebitTotal(notAvailableSlot.getWhopay(),tpid,patientid);
			double payment = consultationNoteDAO.getTotalPaymentDone(notAvailableSlot.getWhopay(),tpid,patientid);
			
			double balance = debit - payment;
			String balancex = DateTimeUtils.changeFormat(balance);
			balancex = Constants.getCurrency(loginInfo) + balancex;
			
			str.append("<tr>");
			str.append("<td>Balance<td>");
			str.append("<td>:</td>");
			str.append("<td>"+balancex+"</td>>");
			str.append("</tr>");
			

		str.append("</table>");
		
		bodyText = str.toString();
		String[] attachedfile = {};
		Email.sendPrintLetterMail(connection, to, cc, subject, bodyText, loginInfo, emailLetterLog,attachedfile);
		
	} catch (Exception e) {

		e.printStackTrace();
	}
	
}
	

public static String getAppointmentSMSText(String clientId,int appointmentid,Connection connection,LoginInfo loginInfo) {
	   	//ClientDAO clientDAO = new JDBCClientDAO(connection);
	    NotAvailableSlotDAO notAvailableSlotDAO = new JDBCNotAvailableSlotDAO(connection);
	    NotAvailableSlot notAvailableSlot = notAvailableSlotDAO.getApmtDetailsForLog(appointmentid);
	    //UserProfileDAO userProfileDAO = new JDBCUserProfileDAO(connection);
	    //Client client = clientDAO.getClientDetails(clientId);
	    //UserProfile userProfile  = userProfileDAO.getUserprofileDetails(notAvailableSlot.getDiaryUserId());
	   
	    String startime = notAvailableSlot.getSTime();
	    startime = DateTimeUtils.getAmPmTime(startime);
	    String commencing = DateTimeUtils.getCommencingDate1(notAvailableSlot.getCommencing());
	    /*String message = "You have Booked appointment on "+commencing+" at "+startime+" with "+loginInfo.getClinicName()+" "+loginInfo.getRegAddress()+" "+loginInfo.getRegLocation()+" "+loginInfo.getRegContactNo()+" ";*/
	    String message = "You have booked an appointment on "+commencing+" at "+startime+" with "+loginInfo.getClinicName()+"";
	    return message;
}
public static String getAppointmentSMSTextNew(String clientId,int appointmentid,Connection connection,LoginInfo loginInfo,int seqNO){
	 String seqNumber=""+seqNO;
	     ClientDAO clientDAO = new JDBCClientDAO(connection);
	     NotAvailableSlotDAO notAvailableSlotDAO = new JDBCNotAvailableSlotDAO(connection);
	     NotAvailableSlot notAvailableSlot = notAvailableSlotDAO.getApmtDetailsForLog(appointmentid);
	     //UserProfileDAO userProfileDAO = new JDBCUserProfileDAO(connection);
	     Client client = clientDAO.getClientDetails(clientId);
	     //UserProfile userProfile  = userProfileDAO.getUserprofileDetails(notAvailableSlot.getDiaryUserId());
	    
	     String startime = notAvailableSlot.getSTime();
	     startime = DateTimeUtils.getAmPmTime(startime);
	     String commencing = DateTimeUtils.getCommencingDate1(notAvailableSlot.getCommencing());
	     String message="";
	     if(loginInfo.isParihar()) {
	    	message=client.getFullname()+" टोकन नम्बर "+seqNumber+" आपने दिनांक "+commencing+" को डाॅ. "+notAvailableSlot.getDiaryUser()+", परिहार हास्पिटल कवर्धा में अपाइंटमेंट बुक किया है।";
	    			
	     }else if(loginInfo.isAyushman()){
	    	 
	    	 message=client.getFullname()+" टोकन नम्बर "+seqNumber+" आपने दिनांक "+commencing+" को डाॅ. "+notAvailableSlot.getDiaryUser()+", Ayushman Hospital में अपाइंटमेंट बुक किया है।";
	     }else {
	     
	     /*String message = "Token no: "+seqNumber+" You have Booked appointment on "+commencing+" at "+startime+" with "+loginInfo.getClinicName()+" "+loginInfo.getRegAddress()+" "+loginInfo.getRegLocation()+" "+loginInfo.getRegContactNo()+" ";*/
	     message = "Dear Parents\nToken no: "+seqNumber+" You have booked appointment on "+commencing+" at "+startime+" with "+loginInfo.getClinicName()+" ";
	     //String message = ""+client.getFullname()+" your appointment has been confirm with "+loginInfo.getClinicName()+" on date "+commencing+" in between "+startime+" and "+startime+". Yuvicr";
	     }
	     return message;
	}

public static String getAppointmentSMSTextToapmUser(String clientId,int appointmentid,Connection connection,LoginInfo loginInfo,int seqNO){
		String message="";
		try {
	 		 String seqNumber=""+seqNO;
		     ClientDAO clientDAO = new JDBCClientDAO(connection);
		     NotAvailableSlotDAO notAvailableSlotDAO = new JDBCNotAvailableSlotDAO(connection);
		     NotAvailableSlot notAvailableSlot = notAvailableSlotDAO.getApmtDetailsForLog(appointmentid);
		     //UserProfileDAO userProfileDAO = new JDBCUserProfileDAO(connection);
		     Client client = clientDAO.getClientDetails(clientId);
		    // UserProfile userProfile  = userProfileDAO.getUserprofileDetails(notAvailableSlot.getDiaryUserId());
		    
		     String startime = notAvailableSlot.getSTime();
		     startime = DateTimeUtils.getAmPmTime(startime);
		     String commencing = DateTimeUtils.getCommencingDate1(notAvailableSlot.getCommencing());
		     //message = "You have new appointment on "+commencing+" at "+startime+" with "+client.getFirstName()+" "+client.getLastName()+" patient token no: "+seqNumber+" ";
		     message= loginInfo.getClinicName() +"\n"+"Dear Doctor\nAppointment No: "+seqNumber+"\n"+"PT Name: "+client.getFirstName()+" "+client.getLastName()+"\n"+"Date/Time: "+commencing+"/"+startime;
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	     return message;
	}


public static String getDeletedAppointmentSMSText(String clientId,int appointmentid,Connection connection,LoginInfo loginInfo){
   	//ClientDAO clientDAO = new JDBCClientDAO(connection);
    NotAvailableSlotDAO notAvailableSlotDAO = new JDBCNotAvailableSlotDAO(connection);
    NotAvailableSlot notAvailableSlot = notAvailableSlotDAO.getApmtDetailsForLog(appointmentid);
    //UserProfileDAO userProfileDAO = new JDBCUserProfileDAO(connection);
    //Client client = clientDAO.getClientDetails(clientId);
    //UserProfile userProfile  = userProfileDAO.getUserprofileDetails(notAvailableSlot.getDiaryUserId());
   
    String startime = notAvailableSlot.getSTime();
    startime = DateTimeUtils.getAmPmTime(startime);
    String commencing = DateTimeUtils.getCommencingDate1(notAvailableSlot.getCommencing());
    /*String message = "Appointment Cancelled on "+commencing+" at "+startime+" with "+loginInfo.getClinicName()+" "+loginInfo.getRegAddress()+" "+loginInfo.getRegLocation()+" "+loginInfo.getRegContactNo()+" ";*/
    String message = "Appointment Cancelled on "+commencing+" at "+startime+" with "+loginInfo.getClinicName()+"";
    return message;
}


public static String getAppointmentModifiedSMSText(String clientId,
		int appointmentid, Connection connection, LoginInfo loginInfo) {
 	//ClientDAO clientDAO = new JDBCClientDAO(connection);
    NotAvailableSlotDAO notAvailableSlotDAO = new JDBCNotAvailableSlotDAO(connection);
    NotAvailableSlot notAvailableSlot = notAvailableSlotDAO.getApmtDetailsForLog(appointmentid);
    //UserProfileDAO userProfileDAO = new JDBCUserProfileDAO(connection);
    //Client client = clientDAO.getClientDetails(clientId);
    //UserProfile userProfile  = userProfileDAO.getUserprofileDetails(notAvailableSlot.getDiaryUserId());
   
    String startime = notAvailableSlot.getSTime();
    startime = DateTimeUtils.getAmPmTime(startime);
    String commencing = DateTimeUtils.getCommencingDate1(notAvailableSlot.getCommencing());
    /*String message = "You have appointment Modified on "+commencing+" at "+startime+" with "+loginInfo.getClinicName()+" "+loginInfo.getRegAddress()+" "+loginInfo.getRegLocation()+" "+loginInfo.getRegContactNo()+" ";*/
    String message = "You have appointment Modified on "+commencing+" at "+startime+" with "+loginInfo.getClinicName()+"";
    return message;
    
	
}

//ruchi  sms to patient or relative on discharge process start.

public static String getDischargeSMSTextPatient(String clientId,Connection connection){
	 
    ClientDAO clientDAO = new JDBCClientDAO(connection);
       Client client = clientDAO.getClientDetails(clientId);
  
  
    String message = "Patient "+client.getFirstName()+" "+client.getLastName()+" will be Discharged today, please clear any remaining dues. ";
    
    return message;
}


//ruchi  sms to patient or relative on bed shift.

public static String getBedChangeSMSTextPatient(String clientId,Connection connection,String wardid,String bedid){

  ClientDAO clientDAO = new JDBCClientDAO(connection);
     Client client = clientDAO.getClientDetails(clientId);

String wardname=clientDAO.getwardnamebyid(wardid);
  String message = "Patient "+client.getFirstName()+" "+client.getLastName()+" : Bed shifted to "+wardname+" / "+bedid+".";
  
  return message;
}
//ruchi  sms to practioner on bed shift.
public static String getPrimaryDoctorChangeSMSText(String clientId,Connection connection,String wardid,String bedid,int Practitionerid){

  ClientDAO clientDAO = new JDBCClientDAO(connection);
     Client client = clientDAO.getClientDetails(clientId);
   
            

  String message = "New Patient Admitted: "+client.getFirstName()+" "+client.getLastName()+" to "+wardid+" / "+bedid+".";
  
  return message;
}


}
