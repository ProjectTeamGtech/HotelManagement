package com.apm.Api;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.apm.DiaryManagement.eu.bi.ClientDAO;
import com.apm.DiaryManagement.eu.blogic.jdbc.JDBCClientDAO;
import com.apm.DiaryManagement.eu.entity.Client;
import com.apm.DiaryManagement.eu.entity.Priscription;
import com.apm.DiaryManagement.web.action.SmsService;
import com.apm.Emr.eu.bi.EmrDAO;
import com.apm.Emr.eu.bi.InvestigationDAO;
import com.apm.Emr.eu.bi.PrescriptionDAO;
import com.apm.Emr.eu.blogic.jdbc.JDBCEmrDAO;
import com.apm.Emr.eu.blogic.jdbc.JDBCInvestigationDAO;
import com.apm.Emr.eu.blogic.jdbc.JDBCPrescriptionDAO;
import com.apm.Emr.eu.entity.Emr;
import com.apm.Emr.eu.entity.Investigation;
import com.apm.Log.eu.entity.EmailLetterLog;
import com.apm.Registration.eu.bi.UserProfileDAO;
import com.apm.Registration.eu.blogic.jdbc.JDBCUserProfileDAO;
import com.apm.common.utils.DateTimeUtils;
import com.apm.common.web.action.BaseAction;
import com.apm.common.web.common.helper.LoginInfo;
import com.apm.main.common.constants.Constants;
import com.google.android.gcm.server.Message;
import com.google.android.gcm.server.Sender;

//@jitu
public class MobileApiAction extends BaseAction  {
	
	
	HttpServletRequest request=ServletActionContext.getRequest();
	HttpServletResponse response=ServletActionContext.getResponse();
	HttpSession session=request.getSession();

	
	final static String GOOGLE_API_KEY = "AIzaSyAdE5zluOpzB-Sb3wjpYOdXyiUojj0UWuk";
	final static String MESSAGE_KEY = "his";
	
	
	private Connection getConnection(){
		
		Connection connection=null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			String databasename = (String) session.getAttribute("databasename");
			if(databasename==null){
				databasename="akdcnagpur";
			}
			connection=DriverManager.getConnection(""+Constants.DB_HOST+":3306/"+databasename+"",""+Constants.DB_USER+"",""+Constants.DB_PWD+"");
			//""+Constants.DB_USER+"",""+Constants.DB_PWD+""
		} catch (Exception e) {

			e.printStackTrace();
		}
		
		return connection;
	}
	
	
	
	public String execute() throws Exception {
		return super.execute();
	}
	
	//for his patient app check login uhid and mobile
	public String checkloginpatient() throws Exception {
		
		JSONObject jsonObject= new JSONObject();
		String result="0";
		int flag=0;
		String clientid="0";
		response.setContentType("application/json");
		Connection connection=null;
		try {
			String uhid= request.getParameter("uhid");
			String mobile= request.getParameter("mobile");
			String databasename  = request.getParameter("databasename");
			session.setAttribute("databasename", databasename);
			connection=getConnection();
			String sql1="select id,mobno from apm_patient where abrivationid='"+uhid+"' ";
			PreparedStatement ps=connection.prepareStatement(sql1);
			ResultSet rs =ps.executeQuery();
			while(rs.next()){
				 flag=1;
				 clientid= rs.getString(1);
				 String mob= rs.getString(2);
				 if(mob==null){
					 mob="";
				 }
				 if(mob.equals(mobile)){
					   flag=2;
					   break;
				 }
			}
			
			if(flag==0){
				  result ="uhid";
			} else if(flag==1){
				  result ="mobile";
			} else if(flag==2){
				String otp=sendOtp(mobile);
				result = otp; 
			}
			
			jsonObject.put("response", result);
			jsonObject.put("clientid", clientid);
			response.getWriter().write(jsonObject.toJSONString());
			
		} catch (Exception e) {
			 e.printStackTrace();
		}finally {
			connection.close();
		}
		
		return null;
}	
	
	
	private String sendOtp(String mobile) {
		String otp="";
		try {
			
			otp = DateTimeUtils.getOTP();
			session.setAttribute("otp", otp);
			String message = "OTP from YUVICARE for patient login! Your OTP is: "+otp+"";
			SmsService s = new SmsService();
			String templateid="";
	        s.sendSms(message, mobile, new LoginInfo(), new EmailLetterLog(),templateid);
			
		}catch (Exception e) {

			e.printStackTrace();
		}
		return otp;
		
	}
	
	public String UserRegistration() throws Exception {
		JSONObject jsonobject=new JSONObject();
		response.setContentType("application/json");
		Connection connection=null;
		try {
			String firstname=request.getParameter("firstname");
			String lastname=request.getParameter("lastname");
			String mobile_no=request.getParameter("mobile_no");
			String dob=request.getParameter("dob");
			String gender=request.getParameter("gender");
			String state=request.getParameter("state");
			String city=request.getParameter("city");
			String adhno=request.getParameter("adhno");
			String databasename  = request.getParameter("databasename");
			session.setAttribute("databasename", databasename);
			connection=getConnection();
			ClientDAO clientDAO = new JDBCClientDAO(connection);
			
			
			//save abrivation seq no
			//String cdate = DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone()).split(" ")[0];
			DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
			Calendar cal = Calendar.getInstance();
			String cdate = dateFormat.format(cal.getTime());			
			cdate = DateTimeUtils.getCommencingDate1(cdate);
			
			boolean checkifseq = clientDAO.checkifSequenceExist(cdate);
			String abrivationid = "";
			//String clinicabrivation = clientDAO.getClinicAbrivation(loginInfo.getClinicid());
			String clinicabrivation = clientDAO.getClinicAbrivationFromUserid(databasename);
			String tempd[] = cdate.split("-");
			String y = tempd[0];
			String m = tempd[1];
			String d = tempd[2];
			if(checkifseq){
				int seqno = clientDAO.getSqeunceNumber(cdate);
				seqno++;
				int r = clientDAO.InserCdateSeq(cdate,seqno);
				//SNH170609001
				int yr = Integer.parseInt(y)%1000;
				/*abrivationid = clinicabrivation + yr + m +d + "00" + seqno;*/
				abrivationid = clinicabrivation + yr + m +d + seqno;
			}else{
				
				int seqno = 1;
				int r = clientDAO.InserCdateSeq(cdate,seqno);
				//String seqno = clientDAO.getSqeunceNumber(cdate);
				int yr = Integer.parseInt(y)%1000;
				abrivationid = clinicabrivation + yr + m +d + seqno;
			}
			Client client = new Client();
			client.setFirstName(firstname);
			client.setLastName(lastname);
			client.setMobNo(mobile_no);
			client.setDob(dob);
			client.setGender(gender);
			client.setTown(city);
			client.setState(state);
			client.setAdhno(adhno);
			client.setWhopay("Client");
			if(gender.equals("Male")){
				client.setTitle("Mr.");
			}else{
				client.setTitle("Mrs.");
			}
			client.setAbrivationid(abrivationid);
			client.setAddress(city);
			client.setCountry("India");
			client.setThirdPartyType("0");
			client.setThirdPartyCompanyName("0");
			client.setExpiryDate("");
			client.setPolicyNo("");
			client.setMiddlename("");
			client.setGpname("0");
			int result = clientDAO.savePatientDetails(client,0);
			
			if(result>0){
				   
				jsonobject.put("response", result);
				sendSMS(mobile_no,abrivationid);
				
		   } else {
			   jsonobject.put("response", result);
		   }
		   
		   response.getWriter().write(jsonobject.toJSONString());
		}catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			// TODO: handle finally clause
			connection.close();
		}
		
		return null;
	}
	
	private void sendSMS(String mobile,String abrivationid) {
		
		try {
			
			String message ="For login in patient app use UHID:"+abrivationid+" and Mobile number:"+mobile;
			SmsService s = new SmsService();
			String templateid="";
	        s.sendSms(message, mobile, new LoginInfo(), new EmailLetterLog(),templateid);
			
		}catch (Exception e) {

			e.printStackTrace();
		}
		
		
	}
	
	public String emrdata() throws Exception {
		
		Connection connection=null;
		try {
			connection=getConnection();
			response.setContentType("application/json");
			JSONObject jsonObject= new JSONObject();
			JSONArray jsonArray= new JSONArray();
			String clientid= request.getParameter("clientid");
			EmrDAO emrDAO= new JDBCEmrDAO(connection);
			ArrayList<Emr> list=emrDAO.getConsultationListMobApi(clientid);  
			for(Emr emr: list){
				
				 JSONObject object= new JSONObject();
				 object.put("id", emr.getId());
				 String title=emr.getLastModified()+" / "+emr.getPractitionerName(); 
				 object.put("title", title);
				 object.put("content", emr.getDescription());
				 jsonArray.add(object);
				 
			}
			
			jsonObject.put("response", jsonArray);
			response.getWriter().write(jsonObject.toJSONString());
			
		} catch (Exception e) {

			e.printStackTrace();
		}
		
		return null;
	}
	
	
	
	public String priscription() throws Exception{
		
		Connection connection=null;
		try {
			response.setContentType("application/json");
			JSONObject jsonObject= new JSONObject();
			JSONArray jsonArray= new JSONArray();
			String databasename  = request.getParameter("databasename");
			session.setAttribute("databasename", databasename);
			connection=getConnection();
			String clientid= request.getParameter("clientid");
			PrescriptionDAO prescriptionDAO= new JDBCPrescriptionDAO(connection);
			ArrayList<Priscription> list= prescriptionDAO.getPriscclientforApiList(clientid);
			
			for(Priscription priscription: list){
				
				  JSONObject object= new JSONObject();
				  object.put("id", priscription.getId());
				  object.put("datetime", priscription.getDateTime());
				  jsonArray.add(object);
			}
			
			jsonObject.put("response", jsonArray);
			response.getWriter().write(jsonObject.toJSONString());
		} catch (Exception e) {

			e.printStackTrace();
		}finally {
			connection.close();
		}
		
		return null;
	}
	
	
	public String getprisclist() throws Exception {
		
		Connection connection=null;
		try {
			String databasename  = request.getParameter("databasename");
			session.setAttribute("databasename", databasename);
			connection=getConnection();
			response.setContentType("application/json");
			JSONObject jsonObject= new JSONObject();
			JSONArray jsonArray= new JSONArray();
			PrescriptionDAO prescriptionDAO= new JDBCPrescriptionDAO(connection);
			String parentid= request.getParameter("parentid");
			ArrayList<Priscription> list= prescriptionDAO.getchargedMdicineList(parentid);
			Priscription priscData= prescriptionDAO.getPriscriptionParentData(parentid);
			
			for(Priscription priscription: list){
				
				  JSONObject object= new JSONObject();
				  object.put("drug", priscription.getDrug());
				  object.put("days", priscription.getDays());
				  object.put("dosage", priscription.getDosage());
				  object.put("dosenote", priscription.getDosagenote());
				  jsonArray.add(object);
				
			}
			jsonObject.put("doctor", priscData.getPractitionername());
			jsonObject.put("dateTime", priscData.getDateTime());
			jsonObject.put("response", jsonArray);
			response.getWriter().write(jsonObject.toJSONString());
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			connection.close();
		}
		
		return null;
	}
	
	public String investigation() throws Exception{ 
		
		Connection connection=null;
		try {
			String databasename  = request.getParameter("databasename");
			session.setAttribute("databasename", databasename);
			connection=getConnection();
			response.setContentType("application/json");
			JSONObject jsonObject= new JSONObject();
			JSONArray jsonArray= new JSONArray();
			String clientid= request.getParameter("clientid");
			
			InvestigationDAO investigationDAO= new JDBCInvestigationDAO(connection);
			ArrayList<Investigation> list= investigationDAO.getMobApiInvsList(clientid);
			
			for(Investigation investigation: list){
				
				     JSONObject object =new JSONObject();
				     object.put("id", investigation.getId());
				     object.put("data", investigation.getDate());
				     jsonArray.add(object);
			}
			
			
			jsonObject.put("response", jsonArray);
			response.getWriter().write(jsonObject.toJSONString());
			
		} catch (Exception e) {

			e.printStackTrace();
		}
		finally {
			connection.close();
		}
		return null;
	}
	
	
	//
   public String getinvslist() throws Exception {
		
		Connection connection=null;
		try {
			String databasename  = request.getParameter("databasename");
			session.setAttribute("databasename", databasename);
			connection=getConnection();
			response.setContentType("application/json");
			JSONObject jsonObject= new JSONObject();
			JSONArray jsonArray= new JSONArray();
			InvestigationDAO investigationDAO= new JDBCInvestigationDAO(connection);
			UserProfileDAO userProfileDAO= new JDBCUserProfileDAO(connection);
			String parentid= request.getParameter("parentid");
			Investigation masterData= investigationDAO.getEditInvestigation(parentid);
			String fullname= userProfileDAO.getFullName(masterData.getPrectionerid());
			String dateTime= masterData.getDate();
			ArrayList<Investigation> list= investigationDAO.getPrintSelectedInvestigationData(parentid);
			
			
			for(Investigation investigation: list){
				
				  JSONObject object= new JSONObject();
				  object.put("name",investigation.getInvstname());
				  object.put("invstype", investigation.getInvsttype());
				  object.put("finding", investigation.getObsvalue());
				  object.put("reference", investigation.getNormvalue());
				  jsonArray.add(object);
			}
			
			jsonObject.put("dateTime", dateTime);
			jsonObject.put("doctor", fullname);
			jsonObject.put("response", jsonArray);
			response.getWriter().write(jsonObject.toJSONString());
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			connection.close();
		}
		
		return null;
	}
   
   
   //@ jitu
   // api for registering user for push notification
   
   public String registeruser() throws Exception{
	   
	   JSONObject jsonObject= new JSONObject();
	   response.setContentType("application/json");
	   Connection connection=null;
	   try {
		   int flag=0;
		   connection=DriverManager.getConnection(""+Constants.DB_HOST+":3306/admin",""+Constants.DB_USER+"",""+Constants.DB_PWD+"");
		   String uhid=request.getParameter("uhid");
		   String regId= request.getParameter("regId");
		   
		   String sql="select regId from gcm_user where uhid='"+uhid+"'";
		   PreparedStatement ps=connection.prepareStatement(sql);
		   ResultSet rs =ps.executeQuery();
		   while(rs.next()) {
			   uhid= rs.getString(1);   
			   flag=1;
		   }
		   int res=0;
		   
		   if(flag==1){
			   
			   sql="update gcm_user set regId=? where uhid='"+uhid+"'";
			   PreparedStatement ps1=connection.prepareStatement(sql);
			   ps1.setString(1, regId);
			   res= ps1.executeUpdate();
		   } else {
			   sql="insert into gcm_user (userid, regId, uhid) value (?,?,?)";
			   PreparedStatement ps1=connection.prepareStatement(sql);
			   ps1.setString(1, "");
			   ps1.setString(2, regId);
			   ps1.setString(3, uhid);
			   res= ps1.executeUpdate();
		   }
		   
		   if(res>0){
			   
			    jsonObject.put("response", "1");
		   } else {
			   jsonObject.put("response", "0");
		   }
		   
		   response.getWriter().write(jsonObject.toJSONString());
	   } catch (Exception e) {
		   	e.printStackTrace();
	   }
	   finally {
		   connection.close();
	   }
	   
	   return null;
   }
   
   
   
  // @jitu
  //api for sending the push notification to registered users   
    public static void sendpushnotification(String action,String data,String uhid) throws Exception {
    	
    	Connection connection=null;
    	try {
    		connection=DriverManager.getConnection(""+Constants.DB_HOST+":3306/admin",""+Constants.DB_USER+"",""+Constants.DB_PWD+"");
    		String regId="";
    		List<String> list = new ArrayList<String>();
    		
    		if(action.equals("single")) {
    			String sql="select regId from gcm_user where uhid='"+uhid+"'";
    			PreparedStatement ps=connection.prepareStatement(sql);
    			ResultSet rs = ps.executeQuery();
    			while (rs.next()) {
    				regId = rs.getString("regId");
    				list.add(regId);
    			}
    		
    		} else if(action.equals("many")) {
    			
    			String sql="select regId from gcm_user";
    			PreparedStatement ps=connection.prepareStatement(sql);
    			ResultSet rs = ps.executeQuery();
    			while (rs.next()) {
    				regId = rs.getString("regId");
    				list.add(regId);
    			}
    		}
    		
    		Sender sender = new Sender(GOOGLE_API_KEY);
			Message message = new Message.Builder().timeToLive(30)
					.delayWhileIdle(true).addData(MESSAGE_KEY, data)
					.build();
			sender.send(message, list, 1);
    		
		} catch (Exception e) {
			e.printStackTrace();
		}
    	finally {
    		connection.close();
    	}
    }
    public String userprofile() throws Exception{
    	  JSONObject jsonObject=new JSONObject();
    	  JSONArray jsonArray= new JSONArray();
    	  String Uhid="",fname="",lname="",dob="",mobno="",city="";
    	  response.setContentType("application/json");
    	  Connection connection=null;
    	  try {
    	   String uhid=request.getParameter("uhid");
    	   String databasename  = request.getParameter("databasename");
    	   session.setAttribute("databasename", databasename);
    	   connection=getConnection();
    	   String sql1="select abrivationid,firstname,surname,dob,mobno,town from apm_patient where abrivationid='"+uhid+"' ";
    	   PreparedStatement ps=connection.prepareStatement(sql1);
    	   ResultSet rs =ps.executeQuery();
    	   while(rs.next()){
    	  
    	    JSONObject jsonobject1=new JSONObject();
    	    Uhid=rs.getString(1);
    	       fname=rs.getString(2);
    	    lname=rs.getString(3);
    	       dob=rs.getString(4);
    	       mobno=rs.getString(5);
    	       city=rs.getString(6);
    	       jsonobject1.put("uhid",Uhid);
    	    jsonobject1.put("fname",fname);
    	    jsonobject1.put("lname",lname);
    	    jsonobject1.put("dob",dob);
    	    jsonobject1.put("mobno",mobno);
    	    jsonobject1.put("city",city);
    	    jsonArray.add(jsonobject1);
    	   }
    	   
    	   jsonObject.put("response", jsonArray);
    	  response.getWriter().write(jsonObject.toJSONString());
    	   
    	  } catch (Exception e) {
    	   e.printStackTrace();
    	  }finally {
    	   connection.close();
    	  }
    	  return null;
    	 }
}
