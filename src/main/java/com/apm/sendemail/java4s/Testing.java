package com.apm.sendemail.java4s;
import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;

import com.apm.DiaryManagement.eu.bi.ClientDAO;
import com.apm.DiaryManagement.eu.bi.DiaryManagementDAO;
import com.apm.DiaryManagement.eu.bi.NotAvailableSlotDAO;
import com.apm.DiaryManagement.eu.blogic.jdbc.JDBCClientDAO;
import com.apm.DiaryManagement.eu.blogic.jdbc.JDBCDiaryManagentDAO;
import com.apm.DiaryManagement.eu.blogic.jdbc.JDBCNotAvailableSlotDAO;
import com.apm.DiaryManagement.eu.entity.Client;
import com.apm.DiaryManagement.eu.entity.NotAvailableSlot;
import com.apm.DiaryManagement.web.action.EmbeddedImageEmailUtil;
import com.apm.DiaryManagement.web.action.SendMailTLS;
import com.apm.Log.eu.bi.EmailLetterLogDAO;
import com.apm.Log.eu.blogic.jdbc.JDBCEmailLetterLogDAO;
import com.apm.Log.eu.entity.EmailLetterLog;
import com.apm.Registration.eu.bi.ClinicDAO;
import com.apm.Registration.eu.blogic.jdbc.JDBCClinicDAO;
import com.apm.Registration.eu.entity.Clinic;
import com.apm.Registration.eu.entity.Location;
import com.apm.Registration.eu.entity.UserProfile;
import com.apm.Registration.web.action.ClinicRegistrationAction;
import com.apm.Tools.web.action.AllTemplateAction;
import com.apm.common.eu.blogic.jdbc.Connection_provider;
import com.apm.common.web.action.LoginAction;
import com.apm.common.web.common.helper.LoginHelper;
import com.apm.common.web.common.helper.LoginInfo;
import com.apm.sendemail.javaConstants.Constants;
import com.opensymphony.xwork2.ActionContext;
public class Testing extends TimerTask
{
	int flag =1;
	String folderName = "";
	TimerTask task;
	
	static HttpServletRequest request = (HttpServletRequest) ActionContext.getContext().get(ServletActionContext.HTTP_REQUEST);
	HttpServletResponse response = (HttpServletResponse)ActionContext.getContext().get(ServletActionContext.HTTP_RESPONSE);
	HttpSession session = request.getSession(true);
	LoginInfo loginInfo = LoginHelper.getLoginInfo(request);
	AllTemplateAction allTemplateAction = new AllTemplateAction();
	Connection connection = null;
	int appointmentid = 0;
	
	private static final Logger log = Logger.getLogger(LoginAction.class);
	
	public Testing(Connection connection,LoginInfo loginInfo,HttpServletRequest request,int appointmentid){
		this.request = request;
		this.loginInfo = loginInfo;
		this.connection = connection;
		this.appointmentid = appointmentid;
	}
	
	
	
	public void run()
	{
		log.debug("*****************appointment5");
		
                //GMailServer sender = new GMailServer(Constants.setFrom, Constants.setPassword);
                
               

	            try {
	            	
	            	for(int i=0;i<50000000;i++)

	        		{

//	        		System.out.println(i);

	        		}
	            	
	            	
			    //sender.sendMail("Subject","This is Java4s",Constants.setFrom,Constants.emailTO);
	            	
	            	
	            //int appointmentid = (Integer)session.getAttribute("appointmentid");
	          // if(session.getAttribute("appointmentid")!=null){
	            	
	            	
	            	log.debug("********************hello world1");
	            	
	            	/* if(appointmentid > 0){
	            		 log.debug("********************hello world2");
	 	            	System.out.println(appointmentid);
	 	            	
	 	            	
	 	            	
	 	            			
	 	            	DiaryManagementDAO diaryManagementDAO = new JDBCDiaryManagentDAO(connection);
	 	            	ArrayList<NotAvailableSlot>appointmentList = diaryManagementDAO.getAppintmentSendMailDetails(appointmentid);
	 	            	
	 	            	DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	 	    	        Calendar cal = Calendar.getInstance();
	 	    	        //cal.add(Calendar.DATE, -1); 
	 	    	        
	 	    	        String date = dateFormat.format(cal.getTime());
	 	    	        
	 	    	      
	 	            	
	 	            	for(NotAvailableSlot notAvailableSlot : appointmentList){
	 	    				UserProfile userProfile = notAvailableSlot.getUserDEtails();
	 	    				Client client = notAvailableSlot.getClientDetails();
	 	    				Location location = notAvailableSlot.getLocationDetails();
	 	    				log.debug("********************hello world3");
	    					 emailSend(userProfile.getFullname(), client.getFirstName(), notAvailableSlot.getSTime(), notAvailableSlot.getDuration(), userProfile.getEmail(), client.getEmail(), notAvailableSlot.getCommencing(), location.getLocationname(),location.getContactNo(),location.getAddress(),location.getLocationid(),notAvailableSlot.getClientId(),userProfile.getQualification(),appointmentid,userProfile.getDiciplineName());
	 	    				
	 	    				
	 	    				 
	 	    				
	 	    			}
	 	            	
	 	            	appointmentid = 0;
	 	            	
	 	            	
	 	            	
	 	            }else{
	 	            	Timer timer = new Timer();
	 	            	timer.cancel();
	 	            }*/
	           // }
	            
	           
	            
	          
	            	
	        		
	        		
			}
                   catch (Exception e) {
			     e.printStackTrace();
			     log.debug("********************hello world4"+e.getMessage());
			}  finally{
				try {
					if(connection!=null){
						connection.close();
					}
					
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

				//System.out.println("Email Sent Succesfully...");

	        }
	
	
	
	public  Connection getconnection() throws SQLException
	{
		String dbname = "";
		if(loginInfo!=null){
			dbname = loginInfo.getUserId();
		}
		
		
		Connection con=null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			
			//con=DriverManager.getConnection("jdbc:mysql://localhost:3306/apm","root","mysql");
			//con=DriverManager.getConnection("jdbc:mysql://localhost:3306/apmCbstec","Balvinder001","Deepak001");
			con=DriverManager.getConnection(""+com.apm.main.common.constants.Constants.DB_HOST+":3306/"+dbname+"",""+com.apm.main.common.constants.Constants.DB_USER+"",""+com.apm.main.common.constants.Constants.DB_PWD+"");
			//con=DriverManager.getConnection("jdbc:mysql://localhost:3306/"+dbname+"","root","mysql");
//			System.out.println("done");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return con;
	}

	
	public String emailSend(String practitionerName,String clientName,String startTime,String duration,String practitionerEmailId,String clientEmailId,String date,String location,String contactNo,String address,String locationID,String clientId,String userQualification,int appointmentid,String discipline) throws SQLException{
		
		try{
			log.debug("********************hello world5");
		
		int activity = 1;
		
		
     	ClinicDAO clinicDAO = new JDBCClinicDAO(connection);
     	
     	Clinic locationdirection = new Clinic();
     	locationdirection = clinicDAO.getLocationDetails(Integer.parseInt(locationID));
     	Clinic clinic = new Clinic();
     	int clinicID = loginInfo.getId();
     	clinic = clinicDAO.getCliniclistDetails(loginInfo.getId());
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

	   
	    
		
		//Client Mail
	    
	     
	     int locationId = Integer.parseInt(locationID);
			
		String headerData = allTemplateAction.header(clinic.getClinicName(), clinic.getClinicOwner(), clinic.getOwner_qualification(), locationdirection.getAddress(), 
					clinic.getEmail(),locationdirection.getContactNo(), clinic.getWebsiteUrl(),loginInfo.getClinicid(),connection);
			
		String body1Data = allTemplateAction.commonBody1(date,clientName,address,addr,client2.getTown(),client2.getPostCode(),client2.getCounty());
	
		String onAppointBookingBody = allTemplateAction.OnAppointBookingBody(clinic.getClinicOwner(), clinic.getWebsiteUrl(), practitionerName, clientName, startTime, duration, practitionerEmailId, clientEmailId, date, locationId,connection);
			
		String body2Data = allTemplateAction.commonBody2(practitionerName, userQualification,discipline);
			
		String footerData = allTemplateAction.commonFooter(clinic.getClinicName());
		
		log.debug("********************hello world6");
		
		String img = "<img src=\"cid:image1\" width=\"20%\" height=\"10%\" />";
		//String data = img+headerData+body1Data+onAppointBookingBody+body2Data+footerData+"  "+imgt+" "+imgf+" "+imgg;
		
		String data = headerData+body1Data+onAppointBookingBody+body2Data+footerData+" ";
		
		System.out.println(data);
		
		//	EmbeddedImageEmailUtil.sendMail(clientEmailId, "", subject, str1.toString());
		
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
    	
		int id = loginInfo.getId();
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
		
		EmbeddedImageEmailUtil.sendMail(connection,id,clientEmailId, "", subject, data,loginInfo,emailLetterLog);
		
		//Activity Set
				NotAvailableSlotDAO notAvailableSlotDAO = new JDBCNotAvailableSlotDAO(connection);
				String CheckMailToSend = clinicDAO.IsMailSend(loginInfo.getId());
		        if(CheckMailToSend.equals("true")){
				int result1 = notAvailableSlotDAO.saveActivity(Integer.parseInt(clientId),activity);
		        }
		
//		mailTLS.sendMail(clientEmailId,subject, str1.toString());
		}catch (Exception e) {
			e.printStackTrace();
			log.debug("********************hello world9"+e.getMessage());
		}
		


			
			return null;
		}

	
	
}