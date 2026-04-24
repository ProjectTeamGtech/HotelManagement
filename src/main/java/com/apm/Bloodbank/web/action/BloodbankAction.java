package com.apm.Bloodbank.web.action;

import java.io.BufferedReader;
import java.sql.Connection;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.a.a.a.a.a.b;
import com.a.a.a.g.m.r;
import com.apm.Appointment.eu.bi.AppointmentDAO;
import com.apm.Appointment.eu.blogic.jdbc.JDBCAppointmentDAO;
import com.apm.Appointment.eu.entity.AppointmentType;
import com.apm.Bloodbank.eu.bi.BloodbankDAO;
import com.apm.Bloodbank.eu.blogic.jdbc.JDBCBloodBankDAO;
import com.apm.Bloodbank.eu.entity.Bloodbank;
import com.apm.Bloodbank.web.form.BloodbankForm;
import com.apm.DiaryManagement.eu.bi.ClientDAO;
import com.apm.DiaryManagement.eu.bi.CompleteAptmDAO;
import com.apm.DiaryManagement.eu.bi.DiaryManagementDAO;
import com.apm.DiaryManagement.eu.bi.NotAvailableSlotDAO;
import com.apm.DiaryManagement.eu.blogic.jdbc.JDBCClientDAO;
import com.apm.DiaryManagement.eu.blogic.jdbc.JDBCCompleteAptmDAO;
import com.apm.DiaryManagement.eu.blogic.jdbc.JDBCDiaryManagentDAO;
import com.apm.DiaryManagement.eu.blogic.jdbc.JDBCNotAvailableSlotDAO;
import com.apm.DiaryManagement.eu.entity.Breadcrumbs;
import com.apm.DiaryManagement.eu.entity.Client;
import com.apm.DiaryManagement.eu.entity.CompleteAppointment;
import com.apm.DiaryManagement.eu.entity.DiaryManagement;
import com.apm.DiaryManagement.web.action.SmsService;
import com.apm.Inventory.eu.bi.IndentDAO;
import com.apm.Inventory.eu.bi.InventoryCatalogueDAO;
import com.apm.Inventory.eu.bi.InventoryProductDAO;
import com.apm.Inventory.eu.blogic.jdbc.JDBCIndentDAO;
import com.apm.Inventory.eu.blogic.jdbc.JDBCInventoryCatalogueDAO;
import com.apm.Inventory.eu.blogic.jdbc.JDBCInventoryProductDAO;
import com.apm.Inventory.eu.entity.Product;
import com.apm.Ipd.eu.bi.BedDao;
import com.apm.Ipd.eu.bi.IpdDAO;
import com.apm.Ipd.eu.blogic.jdbc.JDBCBedDao;
import com.apm.Ipd.eu.blogic.jdbc.JDBCIpdDAO;
import com.apm.Ipd.eu.entity.Bed;
import com.apm.Ipd.eu.entity.Ipd;
import com.apm.Log.eu.entity.EmailLetterLog;
import com.apm.Master.eu.entity.Master;
import com.apm.Registration.eu.bi.ClinicDAO;
import com.apm.Registration.eu.bi.UserProfileDAO;
import com.apm.Registration.eu.blogic.jdbc.JDBCClinicDAO;
import com.apm.Registration.eu.blogic.jdbc.JDBCUserProfileDAO;
import com.apm.Registration.eu.entity.Clinic;
import com.apm.Registration.eu.entity.Location;
import com.apm.Registration.eu.entity.UserProfile;
import com.apm.common.eu.blogic.jdbc.Connection_provider;
import com.apm.common.utils.DateTimeUtils;
import com.apm.common.utils.Pagination;
import com.apm.common.web.action.BaseAction;
import com.apm.common.web.common.helper.LoginHelper;
import com.apm.common.web.common.helper.LoginInfo;
import com.apm.main.common.constants.Constants;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.Preparable;

import atg.taglib.json.util.JSONObject;

public class BloodbankAction extends BaseAction implements Preparable,ModelDriven<BloodbankForm> {

    BloodbankForm bankForm=new BloodbankForm();
    
    HttpServletRequest request=ServletActionContext.getRequest();
    HttpServletResponse response=ServletActionContext.getResponse();
    HttpSession session=request.getSession(false); 
    LoginInfo loginInfo=LoginHelper.getLoginInfo(request);
    
    Pagination pagination=new Pagination(20,1);
    
	public Pagination getPagination() {
		return pagination;
	}

	public void setPagination(Pagination pagination) {
		this.pagination = pagination;
	}

	public void prepare() throws Exception {
	
		Connection connection=null;
		
		try {
			connection=Connection_provider.getconnection();
			BloodbankDAO bloodbankDAO=new JDBCBloodBankDAO(connection);
			ArrayList<Bloodbank> bloodgroupList=bloodbankDAO.getBloodgroupList();
			ArrayList<Bloodbank> blooddonorsList=bloodbankDAO.getBloodonorList();
			
			bankForm.setBlooddonorsList(blooddonorsList);
			bankForm.setBloodgroupList(bloodgroupList); 
			
		} catch (Exception e) {

			e.printStackTrace();
		}
		finally {
			connection.close();
		}
		
		
	}

	public BloodbankForm getModel() {
		return bankForm;
	}
	
	@Override
	public String execute() throws Exception {

		if(!verifyLogin(request)){
			return "login";
		}
		Connection connection=null;
		
		try {
			
			connection=Connection_provider.getconnection();
			BloodbankDAO bloodbankDAO=new JDBCBloodBankDAO(connection);
			ArrayList<Bloodbank> bloodgroupList=bloodbankDAO.getBloodgroupList();
			ArrayList<Bloodbank> blooddonorsList=bloodbankDAO.getBloodonorList();
			
			DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
			Calendar cal = Calendar.getInstance();
			//cal.add(Calendar.DATE, -7); 
			String currdate = dateFormat.format(cal.getTime());
			
			String wcdate[] = currdate.split("/");
			
			int wyear = Integer.parseInt(wcdate[2]);
			int month = Integer.parseInt(wcdate[1]);
			int day = Integer.parseInt(wcdate[0]);
			
			String cweekName = DateTimeUtils.getWeekName(wyear,month,day);
			
			bankForm.setDate(Integer.toString(day));
			bankForm.setMonth(cweekName);
			
			bankForm.setBloodgroupList(bloodgroupList);
			bankForm.setBlooddonorsList(blooddonorsList);
			DiaryManagementDAO diaryManagementDAO=new JDBCDiaryManagentDAO(connection);
			ArrayList<DiaryManagement> eventList=diaryManagementDAO.getAllEventListExist();
			
			bankForm.setEventList(eventList);
			
			
			
		 } catch (Exception e) {

		   e.printStackTrace();
		}
		finally {
			connection.close();
		}
		

		return super.execute();
	}
	
	
	public String instock() throws Exception {
		
		Connection connection=null;
		try {
			connection=Connection_provider.getconnection();
			
			BloodbankDAO bloodbankDAO=new JDBCBloodBankDAO(connection);
			ArrayList<Bloodbank> bloodgroupList=bloodbankDAO.getBloodgroupList();
			ArrayList<Bloodbank> blooddonorsList=bloodbankDAO.getBloodonorList();
			
			bankForm.setBloodgroupList(bloodgroupList);
			bankForm.setBlooddonorsList(blooddonorsList);
		} catch (Exception e) {

			e.printStackTrace();
		}
		finally {
			connection.close();
		}
		
		return "instock";
	}
	
	
	
	public String savegroup() throws Exception{

		Connection connection=null;
		try {
			connection=Connection_provider.getconnection();
			BloodbankDAO bloodbankDAO=new JDBCBloodBankDAO(connection);
			String blood_group=request.getParameter("blood_group");
			String no_bags=request.getParameter("no_bags");
			String expiry_date=request.getParameter("expiry_date");
			
			Bloodbank bloodbank=new Bloodbank();
			bloodbank.setBlood_group(blood_group);
			bloodbank.setNo_bags(no_bags);
			bloodbank.setExpiry_date(expiry_date);
			
			int result=bloodbankDAO.addBloodGroup(bloodbank);
			
			response.setContentType("text/html");
			response.setHeader("Cache-Control", "no-cache");
			response.getWriter().write("");
			
		} catch (Exception e) {

		   e.printStackTrace();
		}finally{
			
			connection.close();
		}
		
		return null;
	}
	
	
	public String  addgroup() throws Exception{
		
		if(!verifyLogin(request)){
			return "login";
		}
		
		Connection connection=null;
		
		
		try {
			connection=Connection_provider.getconnection();
			BloodbankDAO bloodbankDAO=new JDBCBloodBankDAO(connection);
			Bloodbank bloodbank=new Bloodbank();
			bloodbank.setBlood_group(bankForm.getBlood_group());
			bloodbank.setNo_bags(bankForm.getNo_bags());
			bloodbank.setExpiry_date(bankForm.getExpiry_date());
			
		
			int result=bloodbankDAO.addBloodGroup(bloodbank);
			
			
		} catch (Exception e) {

		   e.printStackTrace();
		}
		finally {
			 connection.close();
		}
		 
		return "save";
	}
	
	
	
	public String addajaxgroup(){

        StringBuffer buffer=new StringBuffer();
		
		try {
			int rowcount = Integer.parseInt(request.getParameter("rowcount"));
			int index = rowcount;
			index--;	
			buffer.append("<tr>");
			buffer.append("<td>");
			buffer.append("<select id='blood_group"+index+"' class='form-control validate[required]' name='blood_group'>");
			buffer.append("<option value='0'>Select Blood Group</option>");
			buffer.append("<option value='O+'>O+</option>");
			buffer.append("<option value='O-'>O-</option>");
			buffer.append("<option value='A+'>A+</option>");
			buffer.append("<option value='A-'>A-</option>");
			buffer.append("<option value='B+'>B+</option>");
			buffer.append("<option value='B-'>B-</option>");
			buffer.append("<option value='AB+'>AB+</option>");
			buffer.append("<option value='AB-'>AB-</option>");
			buffer.append("</select>");
			buffer.append("</td>");
			buffer.append("<td><input id='no_bags"+index+"' class='form-control validate[required] text-input' placeholder='enter no of bags' name='no_bags' type='text'></td>");
			buffer.append("<td><input id='expiry_date"+index+"' class='form-control validate[required] text-input' placeholder='enter expiry date'  name='expiry_date' type='text'></td>");
			buffer.append("<td><button type='button' class='btn btn-primary' onclick='addBloodDonor("+index+")' >Add</button></td>");
			
			buffer.append("</tr>");
			response.setContentType("text/html");
			response.setHeader("Cache-Control", "no-cache");
			response.getWriter().write(buffer.toString());
			
		} catch (Exception e) {

		   e.printStackTrace();
		}
		
		return null;
	}
	
	
	
   public String  updategroup() throws Exception{
		
		if(!verifyLogin(request)){
			return "login";
		}
		
		Connection connection=null;
		
		
		try {
			connection=Connection_provider.getconnection();
			BloodbankDAO bloodbankDAO=new JDBCBloodBankDAO(connection);
			Bloodbank bloodbank=new Bloodbank();
			bloodbank.setBlood_group(bankForm.getBlood_group());
			bloodbank.setNo_bags(bankForm.getNo_bags());
			bloodbank.setExpiry_date(bankForm.getExpiry_date());
			bloodbank.setId(bankForm.getId());
			int result=bloodbankDAO.updateBloodGroup(bloodbank);
			
		} catch (Exception e) {

		   e.printStackTrace();
		}
		finally {
			 connection.close();
		}
		 
		return "save";
	}
   
   public String adddonor() throws Exception{
	   
	   if(!verifyLogin(request)){
		   return "login";
	   }
	   
	   Connection connection=null;
	   
	   try {
		   connection=Connection_provider.getconnection();
		   BloodbankDAO bloodbankDAO=new JDBCBloodBankDAO(connection); 
		   Bloodbank bloodbank=new Bloodbank();
		   bloodbank.setName(bankForm.getName());
		   bloodbank.setGender(bankForm.getGender());
		   bloodbank.setAge(bankForm.getAge());
		   bloodbank.setPhone(bankForm.getPhone());
		   bloodbank.setEmail(bankForm.getEmail());
		   bloodbank.setBlood_group(bankForm.getBlood_group());
		   bloodbank.setDob(bankForm.getDob());
		   bloodbank.setDonor_state(bankForm.getDonor_state());
		   bloodbank.setAddress(bankForm.getAddress());
		   bloodbank.setCity(bankForm.getCity());
		   bloodbank.setWeight(bankForm.getWeight());
		   bloodbank.setLast_donation_date("0 days");
		   
		   String datetime=DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone());
		   bloodbank.setLastmodified(datetime);
		   int result=bloodbankDAO.addBloodDonor(bloodbank);
		   
		    ArrayList<Bloodbank> bloodgroupList=bloodbankDAO.getBloodgroupList();
			ArrayList<Bloodbank> blooddonorsList=bloodbankDAO.getBloodonorList();
			
			bankForm.setBloodgroupList(bloodgroupList);
			bankForm.setBlooddonorsList(blooddonorsList);
		   
		   
		
	} catch (Exception e) {

	   e.printStackTrace();
	}
	finally {
		connection.close();
	}
	   
	   
	   
	   return "donorlist";
   }
   
   
   
   
   public String editgroupajax() throws Exception{
	   
	   Connection connection=null;
	 try {
		   connection=Connection_provider.getconnection();
		   BloodbankDAO bloodbankDAO=new JDBCBloodBankDAO(connection);
		   String id=request.getParameter("id");
		   Bloodbank bloodbank=bloodbankDAO.getBloodbankGroup(id);
	      
		   StringBuffer buffer=new StringBuffer();
		   buffer.append("<td>");
		   buffer.append("<select id='blood_group"+id+"' class='form-control validate[required]' name='blood_group'>");
		   if(bloodbank.getBlood_group().equals("A+")){
			    
			    buffer.append("<option value='0'>Select Blood Group</option>");
				buffer.append("<option value='O+'>O+</option>");
				buffer.append("<option value='O-'>O-</option>");
				buffer.append("<option value='A+' selected='selected'>A+</option>");
				buffer.append("<option value='A-'>A-</option>");
				buffer.append("<option value='B+'>B+</option>");
				buffer.append("<option value='B-'>B-</option>");
				buffer.append("<option value='AB+'>AB+</option>");
				buffer.append("<option value='AB-'>AB-</option>");
		   }else if(bloodbank.getBlood_group().equals("A-")){
			   
			    buffer.append("<option value='0'>Select Blood Group</option>");
				buffer.append("<option value='O+'>O+</option>");
				buffer.append("<option value='O-'>O-</option>");
				buffer.append("<option value='A+'>A+</option>");
				buffer.append("<option value='A-' selected='selected'>A-</option>");
				buffer.append("<option value='B+'>B+</option>");
				buffer.append("<option value='B-'>B-</option>");
				buffer.append("<option value='AB+'>AB+</option>");
				buffer.append("<option value='AB-'>AB-</option>");
		   } else if(bloodbank.getBlood_group().equals("O-")){
			   
			    buffer.append("<option value='0'>Select Blood Group</option>");
				buffer.append("<option value='O+'>O+</option>");
				buffer.append("<option value='O-' selected='selected'>O-</option>");
				buffer.append("<option value='A+'>A+</option>");
				buffer.append("<option value='A-'>A-</option>");
				buffer.append("<option value='B+'>B+</option>");
				buffer.append("<option value='B-'>B-</option>");
				buffer.append("<option value='AB+'>AB+</option>");
				buffer.append("<option value='AB-'>AB-</option>");
		   } else if(bloodbank.getBlood_group().equals("O+")){
			   
			    buffer.append("<option value='0'>Select Blood Group</option>");
				buffer.append("<option value='O+' selected='selected'>O+</option>");
				buffer.append("<option value='O-'>O-</option>");
				buffer.append("<option value='A+'>A+</option>");
				buffer.append("<option value='A-'>A-</option>");
				buffer.append("<option value='B+'>B+</option>");
				buffer.append("<option value='B-'>B-</option>");
				buffer.append("<option value='AB+'>AB+</option>");
				buffer.append("<option value='AB-'>AB-</option>");
		   } else if(bloodbank.getBlood_group().equals("B+")){
			   
			    buffer.append("<option value='0'>Select Blood Group</option>");
				buffer.append("<option value='O+'>O+</option>");
				buffer.append("<option value='O-'>O-</option>");
				buffer.append("<option value='A+'>A+</option>");
				buffer.append("<option value='A-'>A-</option>");
				buffer.append("<option value='B+' selected='selected'>B+</option>");
				buffer.append("<option value='B-'>B-</option>");
				buffer.append("<option value='AB+'>AB+</option>");
				buffer.append("<option value='AB-'>AB-</option>");
		   } else if(bloodbank.getBlood_group().equals("B-")){
			   
			    buffer.append("<option value='0'>Select Blood Group</option>");
				buffer.append("<option value='O+'>O+</option>");
				buffer.append("<option value='O-'>O-</option>");
				buffer.append("<option value='A+'>A+</option>");
				buffer.append("<option value='A-'>A-</option>");
				buffer.append("<option value='B+'>B+</option>");
				buffer.append("<option value='B-' selected='selected'>B-</option>");
				buffer.append("<option value='AB+'>AB+</option>");
				buffer.append("<option value='AB-'>AB-</option>");
		   } else if(bloodbank.getBlood_group().equals("AB-")){
			   
			    buffer.append("<option value='0'>Select Blood Group</option>");
				buffer.append("<option value='O+'>O+</option>");
				buffer.append("<option value='O-'>O-</option>");
				buffer.append("<option value='A+'>A+</option>");
				buffer.append("<option value='A-'>A-</option>");
				buffer.append("<option value='B+'>B+</option>");
				buffer.append("<option value='B-'>B-</option>");
				buffer.append("<option value='AB+'>AB+</option>");
				buffer.append("<option value='AB-' selected='selected'>AB-</option>");
		   } else if(bloodbank.getBlood_group().equals("AB+")){
			   
			    buffer.append("<option value='0'>Select Blood Group</option>");
				buffer.append("<option value='O+'>O+</option>");
				buffer.append("<option value='O-'>O-</option>");
				buffer.append("<option value='A+'>A+</option>");
				buffer.append("<option value='A-'>A-</option>");
				buffer.append("<option value='B+'>B+</option>");
				buffer.append("<option value='B-'>B-</option>");
				buffer.append("<option value='AB+' selected='selected'>AB+</option>");
				buffer.append("<option value='AB-'>AB-</option>");
		   }
		   buffer.append("</select>");
		   buffer.append("</td>");
		   buffer.append("<td><input id='no_bags"+id+"' value='"+bloodbank.getNo_bags()+"' class='form-control validate[required] text-input' placeholder='enter no of bags' name='no_bags' type='text'></td>");
		   buffer.append("<td><input id='expiry_date"+id+"' value='"+bloodbank.getExpiry_date()+"' class='form-control validate[required] text-input' placeholder='enter expiry date'  name='expiry_date' type='text'></td>");
		   buffer.append("<td><button type='button' class='btn btn-primary' onclick='updategroup("+id+")' >Update</button></td>");
		  
		   response.setContentType("text/html");
		   response.setHeader("Cache-Control", "no-cache");
		   response.getWriter().write(buffer.toString()); 
	} catch (Exception e) {

	   e.printStackTrace();
	   
	}finally{
		
		connection.close();
	}
	                   
	   return null;
   }
   
   
   
public String editgroup()throws Exception {
	   
	   Connection connection=null;
	   
	   try {
		
		   connection=Connection_provider.getconnection();
		   BloodbankDAO bloodbankDAO=new JDBCBloodBankDAO(connection);
		   String id=request.getParameter("selectedid");
		   Bloodbank bloodbank=bloodbankDAO.getBloodbankGroup(id);
		   
    	   String data=bloodbank.getId()+"$"+bloodbank.getBlood_group()+"$"+bloodbank.getNo_bags();
		   
		   response.setContentType("text/html");
		   response.setHeader("Cache-Control", "no-cache");
		   response.getWriter().write(data);
				   
		  
	} catch (Exception e) {

	  e.printStackTrace();
	}
	finally {
		connection.close();
	}
	  
	   return null;
   }
   
   
   
   
   public String editdonor()throws Exception {
	   
	   Connection connection=null;
	   
	   try {
		
		   connection=Connection_provider.getconnection();
		   BloodbankDAO bloodbankDAO=new JDBCBloodBankDAO(connection);
		   String id=request.getParameter("selectedid");
		   Bloodbank bloodbank=bloodbankDAO.getBloodbankDonor(id);
		   
		   String data=bloodbank.getName()+"$"+bloodbank.getGender()+"$"+bloodbank.getAge()+"$"+bloodbank.getPhone()+"$"+bloodbank.getEmail()+"$"+bloodbank.getBlood_group()+"$"+bloodbank.getLast_donation_date()+"$"+bloodbank.getId();
			   
		   response.setContentType("text/html");
		   response.setHeader("Cache-Control", "no-cache");
		   response.getWriter().write(data);
				   
		  
	} catch (Exception e) {

	  e.printStackTrace();
	}finally{
		
		connection.close();
	}
	   
	   
	   return null;
   }
	
	
   
   public String deletedonor() throws Exception {
	   
	   Connection connection=null;
	   
	   try {
		   connection=Connection_provider.getconnection();
		   BloodbankDAO bloodbankDAO=new JDBCBloodBankDAO(connection);
		   String id=request.getParameter("id");
		   int result=bloodbankDAO.deleteDonor(id);
		   
		   ArrayList<Bloodbank> bloodgroupList=bloodbankDAO.getBloodgroupList();
			ArrayList<Bloodbank> blooddonorsList=bloodbankDAO.getBloodonorList();
			
			bankForm.setBloodgroupList(bloodgroupList);
			bankForm.setBlooddonorsList(blooddonorsList);
		   
		
	} catch (Exception e) {

	  e.printStackTrace();
	}
	finally {
		connection.close();
		
	}
	   return "donorlist";
   }
   
   
   

public String updatedonor() throws Exception {
	

	Connection connection=null;
	try {
		connection=Connection_provider.getconnection();
		BloodbankDAO bloodbankDAO=new JDBCBloodBankDAO(connection);
		Bloodbank bloodbank=new Bloodbank();
		
		   bloodbank.setName(bankForm.getName());
		   bloodbank.setGender(bankForm.getGender());
		   bloodbank.setAge(bankForm.getAge());
		   bloodbank.setPhone(bankForm.getPhone());
		   bloodbank.setEmail(bankForm.getEmail());
		   bloodbank.setBlood_group(bankForm.getBlood_group());
		   bloodbank.setDob(bankForm.getDob());
		   bloodbank.setDonor_state(bankForm.getDonor_state());
		   bloodbank.setAddress(bankForm.getAddress());
		   bloodbank.setCity(bankForm.getCity());
		   bloodbank.setWeight(bankForm.getWeight());
		   
		   bloodbank.setId(bankForm.getId());
		   String datetime=DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone());
		   bloodbank.setLastmodified(datetime);
		   int result=bloodbankDAO.updateBloodDonor(bloodbank);
		
		  ArrayList<Bloodbank> bloodgroupList=bloodbankDAO.getBloodgroupList();
		  ArrayList<Bloodbank> blooddonorsList=bloodbankDAO.getBloodonorList();
		
		bankForm.setBloodgroupList(bloodgroupList);
		bankForm.setBlooddonorsList(blooddonorsList);
		
	} catch (Exception e) {

	   e.printStackTrace();
	   
	}finally{
		
		connection.close();
	}
	
   return "donorlist";	 
}


public String donorlist() throws Exception {

	if(!verifyLogin(request)){
		return "login";
	}
	Connection connection=null;
	
	try {
		
		connection=Connection_provider.getconnection();
		BloodbankDAO bloodbankDAO=new JDBCBloodBankDAO(connection);
		ArrayList<Bloodbank> bloodgroupList=bloodbankDAO.getBloodgroupList();
		ArrayList<Bloodbank> blooddonorsList=bloodbankDAO.getBloodonorList();
		
		bankForm.setBloodgroupList(bloodgroupList);
		bankForm.setBlooddonorsList(blooddonorsList);
		
		
	 } catch (Exception e) {

	   e.printStackTrace();
	}
	finally {
		connection.close();
	}
	

	return "donorlist";
}


public String searchdonor()throws Exception  {

	Connection connection=null;
try {
		
		connection=Connection_provider.getconnection();
		BloodbankDAO bloodbankDAO=new JDBCBloodBankDAO(connection);
		
		String searchText=bankForm.getSearchText();
		
		ArrayList<Bloodbank> bloodgroupList=bloodbankDAO.getBloodgroupList();
		ArrayList<Bloodbank> blooddonorsList=bloodbankDAO.getBloodonorList(searchText);
		
		bankForm.setBloodgroupList(bloodgroupList);
		bankForm.setBlooddonorsList(blooddonorsList);
		
		
		
	 } catch (Exception e) {

	   e.printStackTrace();
	}
	finally {
		connection.close();
	}
	
	
	return "donorlist";
}
  


public String addajaxdonor(){
	
	
	try {
		int rowcount = Integer.parseInt(request.getParameter("rowcount"));
		int index = rowcount;
		index--; 
		StringBuffer buffer=new StringBuffer();
		
		buffer.append("<tr>");
		buffer.append("<td><input id='name"+index+"' class='form-control validate[required] text-input' placeholder='enter donor name' name='name' type='text'></td>");
		buffer.append("<td><input id='dob"+index+"' class='form-control validate[required] text-input' placeholder='enter dob' name='dob' type='text'></td>");
		buffer.append("<td>");
		buffer.append("<select id='gender"+index+"' class='form-control validate[required]' name='gender'>");
		buffer.append("<option value='0'>Select Gender</option>");
		buffer.append("<option value='Male'>Male</option>");
		buffer.append("<option value='Female'>Female</option>");
		buffer.append("<option value='Other'>Other</option>");
		buffer.append("</select>");
		buffer.append("</td>");
		buffer.append("<td>");
		buffer.append("<select id='blood_group"+index+"' class='form-control validate[required]' name='blood_group'>");
		buffer.append("<option value='0'>Select Blood Group</option>");
		buffer.append("<option value='O+'>O+</option>");
		buffer.append("<option value='O-'>O-</option>");
		buffer.append("<option value='A+'>A+</option>");
		buffer.append("<option value='A-'>A-</option>");
		buffer.append("<option value='B+'>B+</option>");
		buffer.append("<option value='B-'>B-</option>");
		buffer.append("<option value='AB+'>AB+</option>");
		buffer.append("<option value='AB-'>AB-</option>");
		buffer.append("</select>");
		buffer.append("</td>");
		buffer.append("<td>0 days</td>");
		buffer.append("<td><input id='weight"+index+"' name='weight' class='form-control validate[required] text-input' placeholder='enter weight' type='text'></td>");
		buffer.append("<td>");
		buffer.append("<select id='donor_state"+index+"' class='form-control validate[required]' name='donor_state'>");
		buffer.append("<option value='0'>Select</option>");
		buffer.append("<option value='Yet to Donate'>Yet to Donate</option>");
		buffer.append("<option value='Regular Donor'>Regular Donor</option>");
		buffer.append("<option value='On Need Basis'>On Need Basis</option>");
		buffer.append("</select>");
		buffer.append("</td>");
		buffer.append("<td><input id='phone"+index+"' name='phone' class='form-control validate[required] text-input' placeholder='enter contact' type='text'></td>");
		buffer.append("<td><input id='email"+index+"' name='email' class='form-control validate[required] text-input' placeholder='enter email' type='text'></td>");
		buffer.append("<td><input id='address"+index+"' name='address' class='form-control validate[required] text-input' placeholder='enter address' type='text'></td>");
		buffer.append("<td><input id='city"+index+"' name='city' class='form-control validate[required] text-input' placeholder='enter city' type='text'></td>");
		buffer.append("<td><button type='button' class='btn btn-primary' onclick='saveDonor("+index+")' >Save</button></td>");
		buffer.append("</tr>");
		
		response.setContentType("text/html");
		response.setHeader("Cache-Control", "no-cache");
		response.getWriter().write(buffer.toString());
	} catch (Exception e) {

	   e.printStackTrace();
	}
	
	
	return null;
}


public String editdonorajax() throws Exception{
	

	Connection connection=null;
	try {
		String index = request.getParameter("id");
		StringBuffer buffer=new StringBuffer();
		connection=Connection_provider.getconnection();
		BloodbankDAO bloodbankDAO=new JDBCBloodBankDAO(connection);
		Bloodbank bloodbank=bloodbankDAO.getBloodbankDonor(index);
		buffer.append("<td><input id='name"+index+"' value='"+bloodbank.getName()+"' class='form-control validate[required] text-input' placeholder='enter donor name' name='name' type='text'></td>");
		buffer.append("<td><input id='dob"+index+"' value='"+bloodbank.getDob()+"' class='form-control validate[required] text-input' placeholder='enter dob' name='dob' type='text'></td>");
		buffer.append("<td>");
		buffer.append("<select id='gender"+index+"' class='form-control validate[required]' name='gender'>");
		
		if(bloodbank.getGender().equals("Male")){
			buffer.append("<option value='0'>Select Gender</option>");
			buffer.append("<option value='Male' selected='selected'>Male</option>");
			buffer.append("<option value='Female'>Female</option>");
			buffer.append("<option value='Other'>Other</option>");
		} else if(bloodbank.getGender().equals("Feale")){
			buffer.append("<option value='0'>Select Gender</option>");
			buffer.append("<option value='Male' >Male</option>");
			buffer.append("<option value='Female' selected='selected'>Female</option>");
			buffer.append("<option value='Other'>Other</option>");
		} else {
			buffer.append("<option value='0'>Select Gender</option>");
			buffer.append("<option value='Male'>Male</option>");
			buffer.append("<option value='Female'>Female</option>");
			buffer.append("<option value='Other' selected='selected'>Other</option>");
		}
		
		buffer.append("</select>");
		buffer.append("<td>");
		   buffer.append("<select id='blood_group"+index+"' class='form-control validate[required]' name='blood_group'>");
		   if(bloodbank.getBlood_group().equals("A+")){
			    
			    buffer.append("<option value='0'>Select Blood Group</option>");
				buffer.append("<option value='O+'>O+</option>");
				buffer.append("<option value='O-'>O-</option>");
				buffer.append("<option value='A+' selected='selected'>A+</option>");
				buffer.append("<option value='A-'>A-</option>");
				buffer.append("<option value='B+'>B+</option>");
				buffer.append("<option value='B-'>B-</option>");
				buffer.append("<option value='AB+'>AB+</option>");
				buffer.append("<option value='AB-'>AB-</option>");
		   }else if(bloodbank.getBlood_group().equals("A-")){
			   
			    buffer.append("<option value='0'>Select Blood Group</option>");
				buffer.append("<option value='O+'>O+</option>");
				buffer.append("<option value='O-'>O-</option>");
				buffer.append("<option value='A+'>A+</option>");
				buffer.append("<option value='A-' selected='selected'>A-</option>");
				buffer.append("<option value='B+'>B+</option>");
				buffer.append("<option value='B-'>B-</option>");
				buffer.append("<option value='AB+'>AB+</option>");
				buffer.append("<option value='AB-'>AB-</option>");
		   } else if(bloodbank.getBlood_group().equals("O-")){
			   
			    buffer.append("<option value='0'>Select Blood Group</option>");
				buffer.append("<option value='O+'>O+</option>");
				buffer.append("<option value='O-' selected='selected'>O-</option>");
				buffer.append("<option value='A+'>A+</option>");
				buffer.append("<option value='A-'>A-</option>");
				buffer.append("<option value='B+'>B+</option>");
				buffer.append("<option value='B-'>B-</option>");
				buffer.append("<option value='AB+'>AB+</option>");
				buffer.append("<option value='AB-'>AB-</option>");
		   } else if(bloodbank.getBlood_group().equals("O+")){
			   
			    buffer.append("<option value='0'>Select Blood Group</option>");
				buffer.append("<option value='O+' selected='selected'>O+</option>");
				buffer.append("<option value='O-'>O-</option>");
				buffer.append("<option value='A+'>A+</option>");
				buffer.append("<option value='A-'>A-</option>");
				buffer.append("<option value='B+'>B+</option>");
				buffer.append("<option value='B-'>B-</option>");
				buffer.append("<option value='AB+'>AB+</option>");
				buffer.append("<option value='AB-'>AB-</option>");
		   } else if(bloodbank.getBlood_group().equals("B+")){
			   
			    buffer.append("<option value='0'>Select Blood Group</option>");
				buffer.append("<option value='O+'>O+</option>");
				buffer.append("<option value='O-'>O-</option>");
				buffer.append("<option value='A+'>A+</option>");
				buffer.append("<option value='A-'>A-</option>");
				buffer.append("<option value='B+' selected='selected'>B+</option>");
				buffer.append("<option value='B-'>B-</option>");
				buffer.append("<option value='AB+'>AB+</option>");
				buffer.append("<option value='AB-'>AB-</option>");
		   } else if(bloodbank.getBlood_group().equals("B-")){
			   
			    buffer.append("<option value='0'>Select Blood Group</option>");
				buffer.append("<option value='O+'>O+</option>");
				buffer.append("<option value='O-'>O-</option>");
				buffer.append("<option value='A+'>A+</option>");
				buffer.append("<option value='A-'>A-</option>");
				buffer.append("<option value='B+'>B+</option>");
				buffer.append("<option value='B-' selected='selected'>B-</option>");
				buffer.append("<option value='AB+'>AB+</option>");
				buffer.append("<option value='AB-'>AB-</option>");
		   } else if(bloodbank.getBlood_group().equals("AB-")){
			   
			    buffer.append("<option value='0'>Select Blood Group</option>");
				buffer.append("<option value='O+'>O+</option>");
				buffer.append("<option value='O-'>O-</option>");
				buffer.append("<option value='A+'>A+</option>");
				buffer.append("<option value='A-'>A-</option>");
				buffer.append("<option value='B+'>B+</option>");
				buffer.append("<option value='B-'>B-</option>");
				buffer.append("<option value='AB+'>AB+</option>");
				buffer.append("<option value='AB-' selected='selected'>AB-</option>");
		   } else if(bloodbank.getBlood_group().equals("AB+")){
			   
			    buffer.append("<option value='0'>Select Blood Group</option>");
				buffer.append("<option value='O+'>O+</option>");
				buffer.append("<option value='O-'>O-</option>");
				buffer.append("<option value='A+'>A+</option>");
				buffer.append("<option value='A-'>A-</option>");
				buffer.append("<option value='B+'>B+</option>");
				buffer.append("<option value='B-'>B-</option>");
				buffer.append("<option value='AB+' selected='selected'>AB+</option>");
				buffer.append("<option value='AB-'>AB-</option>");
		   }
		   buffer.append("</select>");
		   buffer.append("</td>");
		buffer.append("<td>0 days</td>");
		buffer.append("<td><input id='weight"+index+"' value='"+bloodbank.getWeight()+"' name='weight' class='form-control validate[required] text-input' placeholder='enter weight' type='text'></td>");
		buffer.append("<td>");
		buffer.append("<select id='donor_state"+index+"' class='form-control validate[required]' name='donor_state'>");
		
		if(bloodbank.getDonor_state().equals("Yet to Donate")){
			buffer.append("<option value='0'>Select</option>");
			buffer.append("<option value='Yet to Donate' selected='selected'>Yet to Donate</option>");
			buffer.append("<option value='Regular Donor'>Regular Donor</option>");
			buffer.append("<option value='On Need Basis'>On Need Basis</option>");
		} else if(bloodbank.getDonor_state().equals("Regular Donor")){
			buffer.append("<option value='0'>Select</option>");
			buffer.append("<option value='Yet to Donate' >Yet to Donate</option>");
			buffer.append("<option value='Regular Donor' selected='selected'>Regular Donor</option>");
			buffer.append("<option value='On Need Basis'>On Need Basis</option>");
		} else if(bloodbank.getDonor_state().equals("On Need Basis")){
			buffer.append("<option value='0'>Select</option>");
			buffer.append("<option value='Yet to Donate' >Yet to Donate</option>");
			buffer.append("<option value='Regular Donor' >Regular Donor</option>");
			buffer.append("<option value='On Need Basis' selected='selected'>On Need Basis</option>");
		}
		buffer.append("</select>");
		buffer.append("</td>");
		buffer.append("<td><input id='phone"+index+"' value='"+bloodbank.getPhone()+"' name='phone' class='form-control validate[required] text-input' placeholder='enter contact' type='text'></td>");
		buffer.append("<td><input id='email"+index+"' value='"+bloodbank.getEmail()+"' name='email' class='form-control validate[required] text-input' placeholder='enter email' type='text'></td>");
		buffer.append("<td><input id='address"+index+"' value='"+bloodbank.getAddress()+"' name='address' class='form-control validate[required] text-input' placeholder='enter address' type='text'></td>");
		buffer.append("<td><input id='city"+index+"' value='"+bloodbank.getCity()+"' name='city' class='form-control validate[required] text-input' placeholder='enter city' type='text'></td>");
		buffer.append("<td><button type='button' class='btn btn-primary' onclick='updateDonor("+index+")' >Update</button></td>");
		
		response.setContentType("text/html");
		response.setHeader("Cache-Control", "no-cache");
		response.getWriter().write(buffer.toString());
	} catch (Exception e) {

	   e.printStackTrace();
	}finally{
		
		connection.close();
	}
	
	return null;
}





  public String bloodcamp(){
	  
	  
	  return "bloodcamp";
  }

  
  public String donatepatient() throws Exception{
	  
	  Connection connection=null;
	  try {
		  connection=Connection_provider.getconnection();
		  BloodbankDAO bloodbankDAO=new JDBCBloodBankDAO(connection);
		  ArrayList<Bloodbank> donorPatientList=bloodbankDAO.getAllDonortoPatient();
		  bankForm.setDonorPatientList(donorPatientList);
		  
		   
	} catch (Exception e) {

	   e.printStackTrace();
	}
	finally{
		connection.close();
	}
	  
	  return "donatepatient";
  }
  
  
  public String addpatientblood() throws Exception{
	  
	  
	  Connection connection=null;
	  StringBuffer buffer=new StringBuffer();
		
		try {
			connection=Connection_provider.getconnection();
			IpdDAO ipdDAO=new JDBCIpdDAO(connection);
			BloodbankDAO bloodbankDAO=new JDBCBloodBankDAO(connection);
			ArrayList<Bed> ipdlist=ipdDAO.getAllActiveIpdPatients();
			ArrayList<Bloodbank> donorList=bloodbankDAO.getBloodonorList();
			
			int rowcount = Integer.parseInt(request.getParameter("rowcount"));
			int index = rowcount;
			index--;	
			buffer.append("<tr>");
			buffer.append("<td>");
			buffer.append("<select id='donor"+index+"' onchange='setbloodGroup(this.value,"+index+")' class='form-control validate[required]' name='donor'>");
			buffer.append("<option value='0'>Select Donor Name</option>");
			for(Bloodbank bloodbank:donorList){
				 
				buffer.append("<option value='"+bloodbank.getId()+"'>"+bloodbank.getName()+"</option>");
			}
			buffer.append("</select>");
			buffer.append("</td>");
			buffer.append("<td>");
			buffer.append("<select id='blood_group"+index+"' class='form-control validate[required]' name='blood_group'>");
			buffer.append("<option value='0'>Select Blood Group</option>");
			buffer.append("<option value='O+'>O+</option>");
			buffer.append("<option value='O-'>O-</option>");
			buffer.append("<option value='A+'>A+</option>");
			buffer.append("<option value='A-'>A-</option>");
			buffer.append("<option value='B+'>B+</option>");
			buffer.append("<option value='B-'>B-</option>");
			buffer.append("<option value='AB+'>AB+</option>");
			buffer.append("<option value='AB-'>AB-</option>");
			buffer.append("</select>");
			buffer.append("</td>");
			buffer.append("<td>");
			buffer.append("<select id='patient"+index+"' onchange='getipddetails(this.value,"+index+")' class='form-control validate[required]' name='patient'>");
			buffer.append("<option value='0'>Select Patient</option>");
		    for(Bed bed:ipdlist){
		    	   buffer.append("<option value='"+bed.getIpdid()+"'>"+bed.getClientname()+"</option>");
		    }
			buffer.append("</option>");  
			buffer.append("</select>");
			buffer.append("</td>");
            buffer.append("<td id='wardbed"+index+"'></td>");			
            buffer.append("<td></td>");
            buffer.append("<td><button type='button' class='btn btn-primary' onclick='saveDonorpatient("+index+")' >Save</button></td>");
            buffer.append("</tr>");
            
            response.setContentType("text/html");
    		response.setHeader("Cache-Control", "no-cache");
    		response.getWriter().write(buffer.toString());
            
			} catch (Exception e) {
		
			   e.printStackTrace();
			}finally{
				
				connection.close();
			}
	  
	  return null;
  }
  
  
  public String getgroup() throws Exception{
	  
	  
	  Connection connection=null;
	  try {
		  connection=Connection_provider.getconnection();
		  BloodbankDAO bloodbankDAO=new JDBCBloodBankDAO(connection);
		  String id=request.getParameter("id");
		  Bloodbank bloodbank=bloodbankDAO.getBloodbankDonor(id);
		   response.setContentType("text/html");
   		response.setHeader("Cache-Control", "no-cache");
   		response.getWriter().write(bloodbank.getBlood_group());
		
	} catch (Exception e) {

	  e.printStackTrace();
	}finally{
		
		connection.close();
	}
	return null;
	  
  }
  
  
  public String getipddata() throws Exception{
	  
	  Connection connection=null;
	  try {
		connection =Connection_provider.getconnection();
		BedDao bedDao=new JDBCBedDao(connection);
		String ipdid=request.getParameter("id");
		
		Bed bed=bedDao.getEditIpdData(ipdid);
		
		Bed ward=bedDao.getWard(bed.getWardid());
		Bed bed3=bedDao.getBed(Integer.parseInt(bed.getBedid()));
		
		String data=ward.getWardname()+"/"+bed3.getBedname();
		
		 response.setContentType("text/html");
	   	 response.setHeader("Cache-Control", "no-cache");
	   	 response.getWriter().write(data+"~"+bed.getClientid());
			
		
	  } catch (Exception e) {

	 	  e.printStackTrace();
	  }finally{
			
			connection.close();
		}
     return null;	  
  }
  


public String donatetopatient() throws Exception{

	Connection connection=null;
	try {
		connection=Connection_provider.getconnection();
		BloodbankDAO bloodbankDAO=new JDBCBloodBankDAO(connection);
		//donorid="+donorid+"&blood_group="+blood_group+"&ipdid="+ipdid+"&clientid="+clientid+"";
		
		String donorid=request.getParameter("donorid");
		Bloodbank bloodbank3=bloodbankDAO.getBloodbankDonor(donorid);
		String blood_group=bloodbank3.getBlood_group();
		String ipdid=request.getParameter("ipdid");
		String clientid=request.getParameter("clientid");
		Bloodbank bloodbank=new Bloodbank();
		bloodbank.setDonorid(donorid);
		bloodbank.setBlood_group(blood_group);
		bloodbank.setIpdid(ipdid);
		bloodbank.setClientid(clientid);
		
		String lastmodified=DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone());
		bloodbank.setLastmodified(lastmodified);
		
		int result=bloodbankDAO.saveBloodtoPatient(bloodbank);
    		
		 response.setContentType("text/html");
	   	 response.setHeader("Cache-Control", "no-cache");
	   	 response.getWriter().write("");
			 	
		
	} catch (Exception e) {

	    e.printStackTrace();
	}finally{
		
		connection.close();
	}
	
	return null;
}


public String editdonatepatient() throws Exception{
	
	Connection connection=null;
	try {
		StringBuffer buffer=new StringBuffer();
		connection=Connection_provider.getconnection();
		IpdDAO ipdDAO=new JDBCIpdDAO(connection);
		BloodbankDAO bloodbankDAO=new JDBCBloodBankDAO(connection);
		ArrayList<Bed> ipdlist=ipdDAO.getAllActiveIpdPatients();
		ArrayList<Bloodbank> donorList=bloodbankDAO.getBloodonorList();
		
		String id=request.getParameter("id");
		Bloodbank bloodbankDB=bloodbankDAO.getDonortoPatient(id);
		buffer.append("<td>");
		buffer.append("<select id='donor"+id+"' onchange='setbloodGroup(this.value,"+id+")' class='form-control validate[required]' name='donor'>");
		buffer.append("<option value='0'>Select Donor Name</option>");
		
		int donorid=Integer.parseInt(bloodbankDB.getDonorid());
		for(Bloodbank bloodbank:donorList){
			 
			if(donorid==bloodbank.getId()){
				buffer.append("<option value='"+bloodbank.getId()+"' selected='selected'>"+bloodbank.getName()+"</option>");
			} else {	
			
			     buffer.append("<option value='"+bloodbank.getId()+"'>"+bloodbank.getName()+"</option>");
			}
		}
		buffer.append("</select>");
		buffer.append("</td>");
		buffer.append("<td>");
		buffer.append("<select id='blood_group"+id+"' class='form-control validate[required]' name='blood_group'>");
		if(bloodbankDB.getBlood_group().equals("A+")){
		    
		    buffer.append("<option value='0'>Select Blood Group</option>");
			buffer.append("<option value='O+'>O+</option>");
			buffer.append("<option value='O-'>O-</option>");
			buffer.append("<option value='A+' selected='selected'>A+</option>");
			buffer.append("<option value='A-'>A-</option>");
			buffer.append("<option value='B+'>B+</option>");
			buffer.append("<option value='B-'>B-</option>");
			buffer.append("<option value='AB+'>AB+</option>");
			buffer.append("<option value='AB-'>AB-</option>");
	   }else if(bloodbankDB.getBlood_group().equals("A-")){
		   
		    buffer.append("<option value='0'>Select Blood Group</option>");
			buffer.append("<option value='O+'>O+</option>");
			buffer.append("<option value='O-'>O-</option>");
			buffer.append("<option value='A+'>A+</option>");
			buffer.append("<option value='A-' selected='selected'>A-</option>");
			buffer.append("<option value='B+'>B+</option>");
			buffer.append("<option value='B-'>B-</option>");
			buffer.append("<option value='AB+'>AB+</option>");
			buffer.append("<option value='AB-'>AB-</option>");
	   } else if(bloodbankDB.getBlood_group().equals("O-")){
		   
		    buffer.append("<option value='0'>Select Blood Group</option>");
			buffer.append("<option value='O+'>O+</option>");
			buffer.append("<option value='O-' selected='selected'>O-</option>");
			buffer.append("<option value='A+'>A+</option>");
			buffer.append("<option value='A-'>A-</option>");
			buffer.append("<option value='B+'>B+</option>");
			buffer.append("<option value='B-'>B-</option>");
			buffer.append("<option value='AB+'>AB+</option>");
			buffer.append("<option value='AB-'>AB-</option>");
	   } else if(bloodbankDB.getBlood_group().equals("O+")){
		   
		    buffer.append("<option value='0'>Select Blood Group</option>");
			buffer.append("<option value='O+' selected='selected'>O+</option>");
			buffer.append("<option value='O-'>O-</option>");
			buffer.append("<option value='A+'>A+</option>");
			buffer.append("<option value='A-'>A-</option>");
			buffer.append("<option value='B+'>B+</option>");
			buffer.append("<option value='B-'>B-</option>");
			buffer.append("<option value='AB+'>AB+</option>");
			buffer.append("<option value='AB-'>AB-</option>");
	   } else if(bloodbankDB.getBlood_group().equals("B+")){
		   
		    buffer.append("<option value='0'>Select Blood Group</option>");
			buffer.append("<option value='O+'>O+</option>");
			buffer.append("<option value='O-'>O-</option>");
			buffer.append("<option value='A+'>A+</option>");
			buffer.append("<option value='A-'>A-</option>");
			buffer.append("<option value='B+' selected='selected'>B+</option>");
			buffer.append("<option value='B-'>B-</option>");
			buffer.append("<option value='AB+'>AB+</option>");
			buffer.append("<option value='AB-'>AB-</option>");
	   } else if(bloodbankDB.getBlood_group().equals("B-")){
		   
		    buffer.append("<option value='0'>Select Blood Group</option>");
			buffer.append("<option value='O+'>O+</option>");
			buffer.append("<option value='O-'>O-</option>");
			buffer.append("<option value='A+'>A+</option>");
			buffer.append("<option value='A-'>A-</option>");
			buffer.append("<option value='B+'>B+</option>");
			buffer.append("<option value='B-' selected='selected'>B-</option>");
			buffer.append("<option value='AB+'>AB+</option>");
			buffer.append("<option value='AB-'>AB-</option>");
	   } else if(bloodbankDB.getBlood_group().equals("AB-")){
		   
		    buffer.append("<option value='0'>Select Blood Group</option>");
			buffer.append("<option value='O+'>O+</option>");
			buffer.append("<option value='O-'>O-</option>");
			buffer.append("<option value='A+'>A+</option>");
			buffer.append("<option value='A-'>A-</option>");
			buffer.append("<option value='B+'>B+</option>");
			buffer.append("<option value='B-'>B-</option>");
			buffer.append("<option value='AB+'>AB+</option>");
			buffer.append("<option value='AB-' selected='selected'>AB-</option>");
	   } else if(bloodbankDB.getBlood_group().equals("AB+")){
		   
		    buffer.append("<option value='0'>Select Blood Group</option>");
			buffer.append("<option value='O+'>O+</option>");
			buffer.append("<option value='O-'>O-</option>");
			buffer.append("<option value='A+'>A+</option>");
			buffer.append("<option value='A-'>A-</option>");
			buffer.append("<option value='B+'>B+</option>");
			buffer.append("<option value='B-'>B-</option>");
			buffer.append("<option value='AB+' selected='selected'>AB+</option>");
			buffer.append("<option value='AB-'>AB-</option>");
	   }
	   buffer.append("</select>");
		buffer.append("</td>");
		buffer.append("<td>");
		buffer.append("<select id='patient"+id+"' onchange='getipddetails(this.value,"+id+")' class='form-control validate[required]' name='patient'>");
		buffer.append("<option value='0'>Select Patient</option>");
	    for(Bed bed:ipdlist){
	    	   if(bed.getIpdid().equals(bloodbankDB.getIpdid())){
	    		   buffer.append("<option value='"+bed.getIpdid()+"' selected='selected'>"+bed.getClientname()+"</option>");
	    	   }
	    	   else {
	    	      buffer.append("<option value='"+bed.getIpdid()+"'>"+bed.getClientname()+"</option>");
	    	   }
	    }
		buffer.append("</option>");  
		buffer.append("</select>");
		buffer.append("</td>");
        buffer.append("<td id='wardbed"+id+"'>"+bloodbankDB.getWard()+"</td>");			
        buffer.append("<td></td>");
        buffer.append("<td><button type='button' class='btn btn-primary' onclick='updateDonorpatient("+id+")' >Update</button></td>");
        
        response.setContentType("text/html");
		response.setHeader("Cache-Control", "no-cache");
		response.getWriter().write(buffer.toString());
		
		
	} catch (Exception e) {

	   e.printStackTrace();
	}finally{
		
		connection.close();
	}
	
	
	return null;
}

public String requestblood() throws Exception{
	
	
	if(!verifyLogin(request)){
		return "login";
	}
	Connection connection=null;
	
	try {
		
		ArrayList<Breadcrumbs> indentflowlist = new ArrayList<Breadcrumbs>();
		ArrayList<Breadcrumbs> indentflowlisttemp = new ArrayList<Breadcrumbs>();
		if (session.getAttribute("indentflowlist") != null) {
			indentflowlisttemp = (ArrayList<Breadcrumbs>) session.getAttribute("indentflowlist");
		}
		boolean isavilablemodule= false;
		int modulecount =0;
		for (Breadcrumbs breadcrumbs : indentflowlisttemp) {
			breadcrumbs.setIscurrent(false);
			breadcrumbs.setSqno(modulecount);
			modulecount++;
			if(breadcrumbs.getName().equals("Blood Bank Dashboard")){
				isavilablemodule =true;
				breadcrumbs.setIscurrent(true);
				indentflowlist.add(breadcrumbs);
				break;
			}else{
				indentflowlist.add(breadcrumbs);
			}
		}
		if(!isavilablemodule){
			Breadcrumbs breadcrumbs = new Breadcrumbs();
			breadcrumbs.setName("Blood Bank Dashboard");
			breadcrumbs.setOn(true);
			breadcrumbs.setSqno(modulecount);
			breadcrumbs.setUrllink("requestbloodBloodbank");
			breadcrumbs.setIscurrent(true);
			breadcrumbs.setShowingname("Blood Bank Dashboard");
			indentflowlist.add(breadcrumbs);
		}
		session.setAttribute("indentflowlist",indentflowlist);
		
		connection=Connection_provider.getconnection();
		BloodbankDAO bloodbankDAO=new JDBCBloodBankDAO(connection);
		NotAvailableSlotDAO notAvailableSlotDAO = new JDBCNotAvailableSlotDAO(connection);
		String name=bankForm.getName();
		String fromdate=bankForm.getFromdate();
		String todate= bankForm.getTodate();
		
		String from=bankForm.getFrom();
		String bloodgroup=bankForm.getBlood_group();
		String status=bankForm.getStatus();
		
		if(name!=null){
			
			if(name.equals("")){
				 name=null;
			}
		}
		
		if(fromdate!=null){
			
			 if(fromdate.equals("")){
				 Calendar calendar=Calendar.getInstance();
				 SimpleDateFormat dateFormat= new SimpleDateFormat("yyyy-MM-dd");
				 //calendar.add(Calendar.DATE, -30);
				 fromdate= dateFormat.format(calendar.getTime());
			 } else {
				 fromdate= DateTimeUtils.getCommencingDate1(fromdate);
			 }
			
		}  else {
			 Calendar calendar=Calendar.getInstance();
			 SimpleDateFormat dateFormat= new SimpleDateFormat("yyyy-MM-dd");
			 //calendar.add(Calendar.DATE, -30);
			 fromdate= dateFormat.format(calendar.getTime());
		}
		if(todate!=null){
			
			if(todate.equals("")){
				 Calendar calendar=Calendar.getInstance();
				 SimpleDateFormat dateFormat= new SimpleDateFormat("yyyy-MM-dd");
				 todate= dateFormat.format(calendar.getTime());
			} else {
				 todate= DateTimeUtils.getCommencingDate1(todate);
			}
			
		} else {
			 Calendar calendar=Calendar.getInstance();
			 SimpleDateFormat dateFormat= new SimpleDateFormat("yyyy-MM-dd");
			 todate= dateFormat.format(calendar.getTime());
		}
			
		
		
		if(from!=null){
			if(from.equals("0")){
				 from=null;
			}
		}
		if(bloodgroup!=null){
			if(bloodgroup.equals("0")){
				bloodgroup=null;
			}
		}
		if(status!=null){
			if(status.equals("")){
				status=null;
			}
		}
		
		int count= bloodbankDAO.getAllReuestedBloodListCount(name,fromdate,todate,from,bloodgroup,status);
		pagination.setPreperties(count);
		bankForm.setTotalRecords(count);
		ArrayList<Bloodbank> requestedPatientList=bloodbankDAO.getAllRequestedBloodList(name,fromdate,todate,from,bloodgroup,status,pagination);
		pagination.setTotal_records(requestedPatientList.size());
		bankForm.setPagerecords(String.valueOf(pagination.getTotal_records()));
		bankForm.setRequestedPatientList(requestedPatientList);
		
		fromdate= DateTimeUtils.getCommencingDate1(fromdate);
		todate= DateTimeUtils.getCommencingDate1(todate);
		bankForm.setFromdate(fromdate);
		bankForm.setTodate(todate);
		
		ArrayList<Location>locationList = notAvailableSlotDAO.getLocationList(loginInfo.getId());
		bankForm.setLocationList(locationList);
		
		AppointmentDAO appointmentDAO = new JDBCAppointmentDAO(connection);
		ArrayList<AppointmentType>additionalChargeList = appointmentDAO.getAdditionalChaergeTypeList("");
		bankForm.setAdditionalChargeList(additionalChargeList);
		
		ArrayList<Master>masterChageTypeList = appointmentDAO.getmasterChageTypeList(loginInfo);
		bankForm.setMasterChageTypeList(masterChageTypeList);
		
		
	} catch (Exception e) {

		e.printStackTrace();
	}
	finally {
		connection.close();
	}
	
	return "requestblood";
}


public String updatedonatetopatient() throws Exception{
	
	Connection connection=null;
	try {
		connection=Connection_provider.getconnection();
		BloodbankDAO bloodbankDAO=new JDBCBloodBankDAO(connection);
		
		String id=request.getParameter("id");
		String donorid=request.getParameter("donorid");
		Bloodbank bloodbank3=bloodbankDAO.getBloodbankDonor(donorid);
		String blood_group=bloodbank3.getBlood_group();
		String ipdid=request.getParameter("ipdid");
		String clientid=request.getParameter("clientid");
		Bloodbank bloodbank=new Bloodbank();
		bloodbank.setDonorid(donorid);
		bloodbank.setBlood_group(blood_group);
		bloodbank.setIpdid(ipdid);
		bloodbank.setClientid(clientid);
		bloodbank.setId(Integer.parseInt(id));
		
		String datetime=DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone());
		bloodbank.setLastmodified(datetime);
		
		int result=bloodbankDAO.updateDonateToPatient(bloodbank);
		response.setContentType("text/html");
		response.setHeader("Cache-Control", "no-cache");
		response.getWriter().write("");
		
		
	} catch (Exception e) {

	   e.printStackTrace();
	}finally{
		
		connection.close();
	}
	
   return null;	
}


 	public String addrequest() throws Exception{

 		Connection connection=null;
 		try {
 			
 			connection=Connection_provider.getconnection();
 			BloodbankDAO bloodbankDAO=new JDBCBloodBankDAO(connection);
 			NotAvailableSlotDAO notAvailableSlotDAO=new JDBCNotAvailableSlotDAO(connection);
 			IpdDAO ipdDAO = new JDBCIpdDAO(connection);
 			String ipdid = bankForm.getIpdid();
 			String clientid = bankForm.getClientid();
 			String blood_group = bankForm.getBlood_group();
 			String bloodbank_component = bankForm.getBloodbank_component();
 			String bloodbank_idnattested = bankForm.getBloodbank_idnattested();
 			String bloodbank_leuco_depleted = bankForm.getBloodbank_leuco_depleted();
 			String qty =bankForm.getQty();
 			String requestfrom = bankForm.getRequestfrom();
 			String reqid =  bankForm.getReqid();
 			
 			if(requestfrom!=null){
 				if(requestfrom.equals("")){
 					requestfrom ="IPD";
 				}
 			}else{
 				requestfrom ="IPD";
 			}
 			
 			Bloodbank bloodbank=new Bloodbank();
 			bloodbank.setIpdid(ipdid);
 			bloodbank.setClientid(clientid);
 			bloodbank.setQty(qty);
 			//bloodbank.setBlood_group(bankForm.getBlood_group());
 			bloodbank.setRequestfrom(requestfrom);
 			//String bloodgrpid=bloodbankDAO.getGroupidfromGroup(bankForm.getBlood_group());
 			bloodbank.setBlood_group_id(blood_group);
 			bloodbank.setReqid(reqid);
 			bloodbank.setBloodbank_component(bloodbank_component);
 			bloodbank.setBloodbank_idnattested(bloodbank_idnattested);
 			bloodbank.setBloodbank_leuco_depleted(bloodbank_leuco_depleted);
 			if(bloodbank.getReqid()==null){
 				   bloodbank.setReqid("0");
 			}else {
 				if(bloodbank.getReqid().equals("")){
 					bloodbank.setReqid("0");
 				}else{
 					String appointemntid=bloodbank.getReqid();
 	 				  String result=notAvailableSlotDAO.getOtProcedure(appointemntid);
 	 				  if(!result.equals("0")){
 	 					    bloodbank.setRequestfrom("OT"); 
 	 				  }
 				}
 			}
 			
 			if(bloodbank.getIpdid()==null){
 				 bloodbank.setIpdid("0");
 			}else if(bloodbank.getIpdid().equals("")){
 				bloodbank.setIpdid("0");
 			}
 			bloodbank.setBedid("0");
 			bloodbank.setWardid("0");
 			bloodbank.setPractid("0");
 			if(!bloodbank.getIpdid().equals("0")){
 				Bed bed = ipdDAO.getIpdDetails(bloodbank.getIpdid());
 				bloodbank.setBedid(bed.getBedid());
 	 			bloodbank.setWardid(bed.getWardid());
 	 			bloodbank.setPractid(bed.getPractitionerid());
 			}
 			
 			SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Calendar calendar=Calendar.getInstance();
			String datetime=format.format(calendar.getTime());
			String str[]=datetime.split(" ");
			bloodbank.setCommencing(datetime);
		//	bloodbank.setTime(str[1]);
			bloodbank.setDate(str[0]);
			bloodbank.setTime(str[1]);
			bloodbank.setUserid(loginInfo.getUserId());
 			
 			int result=bloodbankDAO.addBloodRequest(bloodbank);
 			
 			if(bloodbank.getRequestfrom().equals("OPD")){
 				 
 				 return "opd";
 			}
 			
 			if(bloodbank.getRequestfrom().equals("OT")){
 				
 				 return "ot";
 			}
			
		} catch (Exception e) {
		
			e.printStackTrace();
		}
		finally {
			connection.close();
		}
 		return "ipd";
 	}


 	public String allocateblood() throws Exception {
 		
 		Connection connection=null;
 		try {
 			connection=Connection_provider.getconnection();
 			BloodbankDAO bloodbankDAO=new JDBCBloodBankDAO(connection);
 			String id=request.getParameter("id");
 			String alloc=request.getParameter("alloc");
 			
 			int r=bloodbankDAO.updateBloodAllocate(id,alloc);
 			
 			
			
		} catch (Exception e) {

			e.printStackTrace();
			
		}finally{
			
			connection.close();
		}
 		
 		return null;
 	}
 	
 	public String banklist() throws Exception {
 		
 		Connection connection=null;
 		try {
 			connection=Connection_provider.getconnection();
			BloodbankDAO bloodbankDAO=new JDBCBloodBankDAO(connection);
			ArrayList<Bloodbank> bloodBankList=bloodbankDAO.getAllBankList();
			bankForm.setBloodBankList(bloodBankList);
		}
 		catch(Exception e){
 			e.printStackTrace();
 		}
		finally {
			connection.close();
		}
 		
 		
 		return "banklist";
 	}
 	
 	public String addbank() throws Exception {

 		Connection connection=null;
 		try {
 			connection=Connection_provider.getconnection();
 			BloodbankDAO bloodbankDAO=new JDBCBloodBankDAO(connection);
 			String name=bankForm.getName();
 			String address=bankForm.getAddress();
 			String mobile=bankForm.getMobile();
 			
 			Bloodbank bloodbank=new Bloodbank();
 			bloodbank.setName(name);
 			bloodbank.setAddress(address);
 			bloodbank.setMobile(mobile);
 			
 			int result=bloodbankDAO.saveBloodBank(bloodbank);
			
		} catch (Exception e) {

			e.printStackTrace();
		}
		finally {
			connection.close();
			
		}
 		
 		return "savebank";
 	}
 	
 	public String editbank() throws Exception {
 		
 		Connection connection=null;
 		try {
 			connection=Connection_provider.getconnection();
 			BloodbankDAO bloodbankDAO=new JDBCBloodBankDAO(connection);
			String id=request.getParameter("id");
			
			Bloodbank bloodbank=bloodbankDAO.getBloodBankDetails(id);
			StringBuffer buffer=new StringBuffer();
			buffer.append("<td><input type='text' value='"+bloodbank.getName()+"' id='name"+id+"' class='form-control' ></td>");
			buffer.append("<td><input type='text' value='"+bloodbank.getAddress()+"' id='address"+id+"' class='form-control' ></td>");
			buffer.append("<td><input type='text' value='"+bloodbank.getMobile()+"' id='mobile"+id+"' class='form-control' ></td>");
			buffer.append("<td><input type='button' class='btn btn-primary' value='UPDATE' onclick='updateBankDetails("+id+")' ></td>");
			response.setContentType("text/html");
			response.setHeader("Cache-Control", "no-cache");
			response.getWriter().write(buffer.toString());
			
			
		} catch (Exception e) {

			e.printStackTrace();
		}finally{
			
			connection.close();
		}
 		
 		return null;
 	}
 	
 	
 	public String updatebank() throws Exception {
 		
 		Connection connection=null;
 		try {
 			connection=Connection_provider.getconnection();
 			BloodbankDAO bloodbankDAO=new JDBCBloodBankDAO(connection);
 			String name=bankForm.getName();
 			String address=bankForm.getAddress();
 			String mobile=bankForm.getMobile();
 			
 			Bloodbank bloodbank=new Bloodbank();
 			bloodbank.setId(bankForm.getId());
 			bloodbank.setName(name);
 			bloodbank.setAddress(address);
 			bloodbank.setMobile(mobile);
 			
 			int result=bloodbankDAO.updateBloodBank(bloodbank);
			
		} catch (Exception e) {

			e.printStackTrace();
		}
		finally {
			connection.close();
			
		}
 		
 		return "savebank";
 	}
 	
 	
 	public String deletebank() throws Exception {
 		
 		Connection connection=null;
 		try {
 			connection=Connection_provider.getconnection();
 			BloodbankDAO bloodbankDAO=new JDBCBloodBankDAO(connection);
 			String id=request.getParameter("id"); 
 			int result=bloodbankDAO.deleteBloodBank(id);
			
		} catch (Exception e) {

			e.printStackTrace();
		}
		finally {
			connection.close();
			
		}
 		
 		return "savebank";
 	}
 	
 	
 	public String smstodonor() throws Exception {
 		
 		Connection connection=null;
 		try {
			connection=Connection_provider.getconnection();
			BloodbankDAO bloodbankDAO=new JDBCBloodBankDAO(connection);
			ClinicDAO clinicDAO=new JDBCClinicDAO(connection);
 			String blood_group_id= request.getParameter("bloodgroup");
 			Bloodbank bankblood=bloodbankDAO.getBloodbankGroup(blood_group_id);
 			String blood_group=bankblood.getBlood_group();
 			Clinic clinic=clinicDAO.getCliniclistDetails(loginInfo.getClinicid());
 			
 			String msg="Dear Blood Donor, We urgently need your blood ("+blood_group+") contact us if it's possible.- "+clinic.getClinicName()+" -"+clinic.getLandLine()+"";
 			
 			
 			ArrayList<Bloodbank> userList=bloodbankDAO.getBloodonorListByGroup(blood_group); 
 			
 			ArrayList<Bloodbank> bankList=bloodbankDAO.getAllBankList(); 
 			
 			StringBuffer buffer=new StringBuffer();
 			buffer.append(blood_group+"~");
 			
 			buffer.append("<tr>");
 				buffer.append("<th><label class='checkbox checkbox-custom-alt m-0'><input type='checkbox' onclick=selectAllUser(this,'ucase') id='select-all'><i></i> Select All</label></th>");
 			buffer.append("</tr>");
 			for(Bloodbank bloodbank:userList){
 				 
 				buffer.append("<tr>");
  			   		buffer.append("<th><label class='checkbox checkbox-custom-alt checkbox-custom-sm m-0 mail-select'><input type='checkbox' value='"+bloodbank.getId()+"' class='ucase'><i></i> "+bloodbank.getName()+"</label></th>");
  			   	buffer.append("</tr>");
 			}
 			buffer.append("~");
 			
 			buffer.append("<tr>");
				buffer.append("<th><label class='checkbox checkbox-custom-alt m-0'><input type='checkbox' onclick=selectAllUser(this,'bcase') id='select-all'><i></i> Select All</label></th>");
			buffer.append("</tr>");
			for(Bloodbank bloodbank:bankList){
				 
				buffer.append("<tr>");
			   		buffer.append("<th><label class='checkbox checkbox-custom-alt checkbox-custom-sm m-0 mail-select'><input type='checkbox' value='"+bloodbank.getId()+"' class='bcase' ><i></i> "+bloodbank.getName()+"</label></th>");
			   	buffer.append("</tr>");
			}
			buffer.append("~"+msg);
 			
			response.setContentType("text/html");
			response.setHeader("Cache-Control", "no-cache");
			response.getWriter().write(buffer.toString());
			
			
		} catch (Exception e) {

			e.printStackTrace();
		}
		finally {
			connection.close();
		}
 		
 		return null;
 	}
 	
 	
 	public String sendsms() throws Exception {
 		
 		Connection connection=null;
 		try {
 			connection=Connection_provider.getconnection();
 			BloodbankDAO bloodbankDAO=new JDBCBloodBankDAO(connection);
 			String message=bankForm.getMessage();
 			String users=bankForm.getUsers();
 			String banks=bankForm.getBanks();
 			
 			SmsService smsService=new SmsService();
 			
 			for(String user:users.split(",")) {
 				
 				if(user.equals("0")){
 					 continue;
 				}  
 				Bloodbank bloodbank=bloodbankDAO.getBloodbankDonor(user);
 				String templateid="";
 				smsService.sendSms(message,bloodbank.getPhone(), loginInfo, new EmailLetterLog(),templateid);
 				
 			}
 			
 			for(String bank:banks.split(",")){
 				
 				if(bank.equals("0")){
 					continue;
 				}
 				
 				Bloodbank bloodbank=bloodbankDAO.getBloodBankDetails(bank);
 				String templateid="";
 				smsService.sendSms(message,bloodbank.getMobile(), loginInfo, new EmailLetterLog(),templateid);
 			}
			
		} catch (Exception e) {

			e.printStackTrace();
		}
		finally {
			connection.close();
		}
 		return "saverequest";
 	}
 	public String donormaster() {
 		return "donormaster";
 	}
 	public String campmaster() {
 		return "campmaster";
 	}
	public String employeemaster() {
 		return "employeemaster";
 	}

	public String hospitalmaster() {
 		return "hospitalmaster";
 	}

	public String doctormaster() {
 		return "doctormaster";
 	}

	public String suppliermaster() {
 		return "suppliermaster";
 	}
	public String accounting() {
 		return "accounting";
 	}
	public String bloodstock() {
 		return "bloodstock";
 	}
	
	public String setting() {
 		return "settingbloodbank";
 	}
	
	public String procurementblood() {
 		return "procurementblood";
 	}
	
	public String testcomponent() {
 		return "testcomponent";
 	}

	public String crossmatching() {
 		return "crossmatching";
 	}
	public String bloodinvoice() {
 		return "bloodinvoice";
 	}
	public String stockcheck() {
 		return "stockcheck";
 	}
	
	public String requestbloodforclient() throws Exception {
		Connection connection = null;
		try {
			connection = Connection_provider.getconnection();
			InventoryCatalogueDAO catalogueDAO = new JDBCInventoryCatalogueDAO(connection);
			StringBuilder buffer = new StringBuilder();
			BufferedReader reader = request.getReader();
			String line;
			while ((line = reader.readLine()) != null) {
				buffer.append(line);
			}
			String data = buffer.toString();
			
			ArrayList<Product> list = catalogueDAO.getAllProductList("85");
			StringBuffer buffer1= new StringBuffer();
			buffer1.append("<label for='exampleInputEmail1'>Select Blood Group</label>");     
			buffer1.append("<select name='blood_group' id='blood_group' class='form-control chosen' >"); 
			buffer1.append("<option value='0'>Select Blood Group</option>");
			for(Product product1:list){
				buffer1.append("<option value='"+product1.getId()+"'>"+product1.getData()+"</option>");
			}
			buffer1.append("</select>");
			
			
			JSONObject jsonobj = new JSONObject();
			jsonobj.put("cataloguelist", buffer1.toString());
			String response1 = jsonobj.toString();
			response.setContentType("application/json");
			response.setHeader("Cache-Control", "no-cache");
			response.getWriter().write(response1);
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			connection.close();
		}
		return null;
	}
	
	public String getcrossmatchdata() throws Exception {
		Connection connection = null;
		try {
			connection = Connection_provider.getconnection();
			InventoryProductDAO inventoryProductDAO = new JDBCInventoryProductDAO(connection);
			
			StringBuilder buffer = new StringBuilder();
			BufferedReader reader = request.getReader();
			String line;
			while ((line = reader.readLine()) != null) {
				buffer.append(line);
			}
			String data = buffer.toString();
			JSONObject jsonObject = new JSONObject(data);
			
			String id = jsonObject.getString("id");
			String catalogueid = jsonObject.getString("blood_group_id");
			String qty = jsonObject.getString("qty");
			String location ="85";
			ArrayList<Product> productlist = inventoryProductDAO.getProductListByCatalogueId(catalogueid,location,qty);
			StringBuffer buffer1 = new StringBuffer();
			buffer1.append("<select class='form-control chosen' id='crossmatchproductid' name='crossmatchproductid' >");
			buffer1.append("<option value='0'>Select Blood Bag</option>");
	  		for(Product product:productlist){
	  			
	  			buffer1.append("<option value='"+product.getId()+"'>"+product.getGenericname()+"</option>");
	  		}
			
			
			JSONObject jsonobj = new JSONObject();
			jsonobj.put("crossProductList", buffer1.toString());
			String response1 = jsonobj.toString();
			response.setContentType("application/json");
			response.setHeader("Cache-Control", "no-cache");
			response.getWriter().write(response1);
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			connection.close();
		}
		return null;
	}
	
	
	public String savecrossmatchdata() throws Exception {
		Connection connection = null;
		try {
			connection = Connection_provider.getconnection();
			BloodbankDAO bloodbankDAO = new JDBCBloodBankDAO(connection);
			StringBuilder buffer = new StringBuilder();
			BufferedReader reader = request.getReader();
			String line;
			while ((line = reader.readLine()) != null) {
				buffer.append(line);
			}
			String data = buffer.toString();
			JSONObject jsonObject = new JSONObject(data);
			
			String id = jsonObject.getString("crossmatchid");
			String productid = jsonObject.getString("productid");
			String userid= loginInfo.getUserId();
			String dateTime= DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone());   
			
			int res = bloodbankDAO.saveCrossMatchData(id,productid,userid,dateTime);
			
			JSONObject jsonobj = new JSONObject();
			jsonobj.put("crossProductList", "1");
			String response1 = jsonobj.toString();
			response.setContentType("application/json");
			response.setHeader("Cache-Control", "no-cache");
			response.getWriter().write(response1);
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			connection.close();
		}
		return null;
	}
	
	public String getissueproductdata() throws Exception {
		Connection connection = null;
		try {
			connection = Connection_provider.getconnection();
			InventoryProductDAO inventoryProductDAO = new JDBCInventoryProductDAO(connection);
			
			StringBuilder buffer = new StringBuilder();
			BufferedReader reader = request.getReader();
			String line;
			while ((line = reader.readLine()) != null) {
				buffer.append(line);
			}
			String data = buffer.toString();
			JSONObject jsonObject = new JSONObject(data);
		
			String productid = jsonObject.getString("productid");
			
			Product product = inventoryProductDAO.getProduct(productid);
			String data1 = product.getProduct_name()+" "+product.getBatch_no();
			StringBuffer buffer1 = new StringBuffer();
			buffer1.append("<label>");
			buffer1.append(""+data1+"");
			buffer1.append("</label>");
			
			JSONObject jsonobj = new JSONObject();
			jsonobj.put("productdata", buffer1.toString());
			jsonobj.put("stock", product.getStock());
			String response1 = jsonobj.toString();
			response.setContentType("application/json");
			response.setHeader("Cache-Control", "no-cache");
			response.getWriter().write(response1);
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			connection.close();
		}
		return null;
	}
	
	public String saveissuebloodbankdata() throws Exception {
		Connection connection = null;
		try {
			connection = Connection_provider.getconnection();
			BloodbankDAO bloodbankDAO = new JDBCBloodBankDAO(connection);
			InventoryProductDAO inventoryProductDAO = new JDBCInventoryProductDAO(connection);
			StringBuilder buffer = new StringBuilder();
			BufferedReader reader = request.getReader();
			String line;
			while ((line = reader.readLine()) != null) {
				buffer.append(line);
			}
			String data = buffer.toString();
			JSONObject jsonObject = new JSONObject(data);
			
			String productissueid= jsonObject.getString("productissueid");
			String reqqtyissueqty= jsonObject.getString("reqqtyissueqty");
			String id = jsonObject.getString("issuebloodid");
			String handoverto = jsonObject.getString("issuehandoverid");
			String userid= loginInfo.getUserId();
			String dateTime= DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone());   
			
			Product product = inventoryProductDAO.getProduct(reqqtyissueqty);
			int previousstock = inventoryProductDAO.getPreviousStock(productissueid);
			int result=inventoryProductDAO.updateMedicineQty(Integer.parseInt(reqqtyissueqty),productissueid,0);
			
			//stock log
			String datetime = DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone());
			int qtyinout=1;
			String source ="Blood Bank Issue";
			int currentstock=inventoryProductDAO.getPreviousStock(productissueid);
			int changeqty=Integer.parseInt(reqqtyissueqty);
			int reslog = inventoryProductDAO.insertIntoProductLog(loginInfo.getUserId(),datetime,product.getLocation(),qtyinout,productissueid,product.getCatalogueid(),source,currentstock,previousstock,changeqty,"0","0",0,0,0,"0");
			
			int res = bloodbankDAO.saveIssueBloodData(id,handoverto,userid,dateTime);
			
			JSONObject jsonobj = new JSONObject();
			jsonobj.put("crossProductList", "1");
			String response1 = jsonobj.toString();
			response.setContentType("application/json");
			response.setHeader("Cache-Control", "no-cache");
			response.getWriter().write(response1);
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			connection.close();
		}
		return null;
	}
	
	
	public String setchargesofblood(){
		Connection connection = null;
		try{
			connection = Connection_provider.getconnection();
			InventoryProductDAO inventoryProductDAO = new JDBCInventoryProductDAO(connection);
			BloodbankDAO bloodbankDAO = new JDBCBloodBankDAO(connection);
			String ipdclientid = request.getParameter("ipdclientid");
			String bloodbankid = request.getParameter("bloodbankid");
			String cdate = DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone());
			String temp[] = cdate.split(" ");
			String curdate = temp[0];
			//ArrayList<Product> childlist = inventoryProductDAO.getCathDataChildList(bomkitid,"1");
			Product product1 = bloodbankDAO.getBloodBankRequestDetails(bloodbankid);
			Product product = inventoryProductDAO.getProduct(product1.getProductid());
			/*for(Product product : childlist){*/
				ClientDAO clientDAO = new JDBCClientDAO(connection);
				Client client = clientDAO.getClientDetails(ipdclientid);
				String clientname = client.getTitle() + " " + client.getFirstName() + " " + client.getLastName();
			
				String payBuy = "0";
				if(client.getWhopay().equals(Constants.PAY_BY_THIRD_PARTY)){
					payBuy = "1";
				}
			
				CompleteAppointment completeAppointment = new CompleteAppointment();
			
				completeAppointment.setUser(clientname);
				completeAppointment.setApmtType("0");
				completeAppointment.setManualcharge(product.getProduct_name());
				completeAppointment.setCharges(product.getMrp());
				completeAppointment.setClientId(ipdclientid);
			
				DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
				Calendar cal = Calendar.getInstance();
				String date = dateFormat.format(cal.getTime());
				completeAppointment.setCommencing(date);
				completeAppointment.setPayBuy(payBuy);
				completeAppointment.setMarkAppointment("1");
				completeAppointment.setQuantity((product1.getQty()));
				completeAppointment.setMasterchargetype("BLOOD BANK CHARGES");
				completeAppointment.setProdid(0);
				completeAppointment.setAppointmentid("0");
				completeAppointment.setPractitionerId("0");
				completeAppointment.setPractitionerName("");
				completeAppointment.setGinvstid("0");
			
				CompleteAptmDAO completeAptmDAO = new JDBCCompleteAptmDAO(connection);
				int result = completeAptmDAO.saveCharge(completeAppointment,"0",loginInfo.getId());
				
	/*	}
			*/
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		
		return null;
	}
	
	public String updatebloodbankstatus() throws Exception{
		Connection connection = null;
		try{
			connection = Connection_provider.getconnection();
			BloodbankDAO bloodbankDAO = new JDBCBloodBankDAO(connection);
			String bloodbankid = request.getParameter("bloodbankid");
			String userid= loginInfo.getUserId();
			String dateTime= DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone());   
			String status="3";
			int res55 = bloodbankDAO.updateBloodBankStatus(bloodbankid,userid,dateTime,status);
			response.setContentType("text/html");
			response.setHeader("Cache-Control", "no-cache");
			response.getWriter().write("");
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		
		return null;
	}
	
	public String bloodbankprint() throws Exception{
		try {
			String reqid= request.getParameter("reqid");
			Connection connection= Connection_provider.getconnection();
			BloodbankDAO bloodbankDAO= new JDBCBloodBankDAO(connection);
			Product bloodBankDetials= new Product();
			bloodBankDetials=bloodbankDAO.getBloodBankRequestDetails(reqid);
			ClientDAO clientDAO= new JDBCClientDAO(connection);
			BedDao bedDao= new JDBCBedDao(connection);
			IpdDAO ipdDAO = new JDBCIpdDAO(connection);
			Client client= new  Client();
			client= clientDAO.getClientDetails(bloodBankDetials.getClientid());
			bankForm.setClientid(bloodBankDetials.getClientid());
			bankForm.setClientname(client.getTitle()+" "+client.getFullname() );
			Bed ipd= new Bed();
			ipd= bedDao.getEditIpdData(bloodBankDetials.getIpdid());
			bankForm.setWardbed(ipdDAO.getIpdWardName(ipd.getWardid())+"/"+ipdDAO.getIpdBedName(ipd.getBedid()));
			
			UserProfileDAO userProfileDAO = new JDBCUserProfileDAO(connection);
			UserProfile userProfile = userProfileDAO.getUserprofileDetails(Integer.parseInt(ipd.getPractitionerid()));
			String practitionername = userProfile.getInitial() + " " + userProfile.getFirstname() + " " + userProfile.getLastname();
			bankForm.setPractitionername(practitionername);
			
			bankForm.setAgegender(client.getAge1()+"/"+client.getGender());
			bankForm.setAddress(client.getAddress());
			
			InventoryProductDAO inventoryProductDAO= new JDBCInventoryProductDAO(connection);
			Product product= inventoryProductDAO.getProductCatalogueDetails(bloodBankDetials.getCatalogueid());
			bankForm.setBlood_group(product.getProd_name());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "bloodbankprint";
	}
	
	
	
}


