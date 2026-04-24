package com.apm.Appointment.web.action;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.apm.Registration.eu.entity.Location;
import com.apm.common.utils.DateTimeUtils;
import com.apm.main.common.constants.Constants;
import com.apm.Appointment.eu.bi.AppointmentDAO;
import com.apm.common.eu.blogic.jdbc.Connection_provider;
import com.apm.Appointment.eu.blogic.jdbc.JDBCAppointmentDAO;
import com.apm.Appointment.eu.entity.Appointment;
import com.apm.Appointment.eu.entity.AppointmentType;
import com.apm.Appointment.web.form.AppointmentForm;
import com.apm.common.web.action.BaseAction;
import com.apm.common.web.common.helper.LoginHelper;
import com.apm.common.web.common.helper.LoginInfo;
import com.apm.common.web.utils.PopulateData;
import com.apm.common.web.utils.PopulateList;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.Preparable;


public class AppointmentAction extends BaseAction implements Preparable, ModelDriven<AppointmentForm>{
	
	
	AppointmentForm appointmentForm = new AppointmentForm();
	HttpServletRequest request = (HttpServletRequest) ActionContext.getContext().get(ServletActionContext.HTTP_REQUEST);
	HttpServletResponse response = (HttpServletResponse)ActionContext.getContext().get(ServletActionContext.HTTP_RESPONSE);
	HttpSession session = request.getSession(true);
	LoginInfo loginInfo = LoginHelper.getLoginInfo(request);
	
	public String execute() throws Exception {
		
		if(!verifyLogin(request)){
			return "login";
		}
		
		ArrayList<String>list = new ArrayList<String>();
		list.add("08");
		list.add("09");
		list.add("10");
		list.add("11");
		list.add("12");
		list.add("13");
		list.add("14");
		list.add("15");
		list.add("16");
		list.add("17");
		list.add("18");
		list.add("19");
		list.add("20");
		list.add("21");
		list.add("22");
		list.add("23");
		list.add("24");
		
		ArrayList<Appointment>appointmentList = new ArrayList<Appointment>();
		
		
		
		for(String str : list){
			Appointment appointment = new Appointment();
			appointment.setAptTime(str);
			appointmentList.add(appointment);
		}
		
		
		//set practitioner list
		Connection connection = null;
		try{
			connection = Connection_provider.getconnection();
			AppointmentDAO appointmentnDAO = new JDBCAppointmentDAO(connection);
			ArrayList<Appointment>practitionerList = appointmentnDAO.getPractitionerList(loginInfo.getClinicid());
			appointmentForm.setPractitionerList(practitionerList);
			
			
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		finally{
			connection.close();
		}
		
		String currentDate = DateTimeUtils.getDateinSimpleStringFormate(new Date());
		String tempCurrentDate[] = currentDate.split(" ");
		
		String temp[] = tempCurrentDate[0].split("-");
		String dayString = "";
		
		
		int year = Integer.parseInt(temp[2]);
		int month = Integer.parseInt(temp[1]);
		int day = Integer.parseInt(temp[0]);
		
		month = month-1;
		
		Date date1 = (new GregorianCalendar(year, month, day)).getTime();
//	    System.out.println(new SimpleDateFormat("EEEE").format(date1));
	    String selectedDay = new SimpleDateFormat("EEEE").format(date1); 
		
		String monthName[] = new String[12]; 
		monthName[0] = "Jan";
		monthName[1] = "Feb";
		monthName[2] = "Mar";
		monthName[3] = "Apr";
		monthName[4] = "May";
		monthName[5] = "Jun";
		monthName[6] = "Jul";
		monthName[7] = "Aug";
		monthName[8] = "Sep";
		monthName[9] = "Oct";
		monthName[10] = "Nov";
		monthName[11] = "Dec";
		
		  int tempDay = 0;
		    
		    if(selectedDay.equals(Constants.SATURDAY)){
		    	tempDay = 5;
		    	dayString = getDayValue(selectedDay,tempDay,day);
		    }
		    if(selectedDay.equals(Constants.FRIDAY)){
		    	tempDay = 4;
		    	dayString = getDayValue(selectedDay,tempDay,day);
		    }
		    if(selectedDay.equals(Constants.THUSRDAY)){
		    	tempDay = 3;
		    	dayString = getDayValue(selectedDay,tempDay,day);
		    }
		    if(selectedDay.equals(Constants.WEDNEDAY)){
		    	tempDay = 2;
		    	dayString = getDayValue(selectedDay,tempDay,day);
		    }
		    if(selectedDay.equals(Constants.TUESDAY)){
		    	tempDay = 1;
		    	dayString = getDayValue(selectedDay,tempDay,day);
		    }
		    if(selectedDay.equals(Constants.MONDAY)){
		    	tempDay = 1;
		    	dayString = getDayValue(selectedDay,tempDay,day);
		    }
		    if(selectedDay.equals(Constants.SUNDAY)){
		    	tempDay = 5;
		    	dayString = getDayValue(selectedDay,tempDay,day);
		    }
		    
		    
		    String setDay[] = dayString.split(",");
		
		
		
		appointmentForm.setMonday(setDay[0]);
		appointmentForm.setTuesday(setDay[1]);
		appointmentForm.setWednesday(setDay[2]);
		appointmentForm.setThursday(setDay[3]);
		appointmentForm.setFriday(setDay[4]);
		appointmentForm.setSaturday(setDay[5]);
		appointmentForm.setSunday(setDay[6]);
		
		appointmentForm.setMonth(monthName[month]);
		appointmentForm.setYear(Integer.toString(year));
		
		appointmentForm.setAppointmentList(appointmentList);
		
		return SUCCESS;
	}
	
	
	public String save() throws SQLException{
		
		if(!verifyLogin(request)){
			return "login";
		}
		
		Connection connection = null;
		
		
		try{
				connection = Connection_provider.getconnection();
				AppointmentDAO appointmentDAO = new JDBCAppointmentDAO(connection);
				
			Appointment appointment = new Appointment();
			appointment.setPractitioner(appointmentForm.getPractitioner());
			appointment.setAppointmentType(appointmentForm.getAppointmentType());
			appointment.setPatientType(appointmentForm.getAppointmentType());
			appointment.setTitle(appointmentForm.getTitle());
			appointment.setFirstName(appointmentForm.getFirstName());
			appointment.setLastName(appointmentForm.getLastName());
			appointment.setContactNo(appointmentForm.getContactNo());
			appointment.setComDevice(appointmentForm.getComDevice());
			appointment.setDob(appointmentForm.getDob());
			appointment.setEmail(appointmentForm.getEmail());
			appointment.setAutoremaindertype(appointmentForm.getAutoremaindertype());
			appointment.setWhen(appointmentForm.getWhen());
			appointment.setHour(appointmentForm.getHour());
			appointment.setMinute(appointmentForm.getMinute());
			appointment.setTohour(appointmentForm.getTohour());
			appointment.setTominute(appointmentForm.getTominute());
			appointment.setRepeat(appointmentForm.getRepeat());
			appointment.setRevery(appointmentForm.getRevery());
			appointment.setHappointment(appointmentForm.getHappointment());
			appointment.setNotes(appointmentForm.getNotes());
			
			
			
			//int result = appointmentDAO.saveAppointment(appointment);
			
			addActionMessage("You Appointment Saved Successfully!!");
			
			
		}catch (Exception e) {
			e.printStackTrace();
		}finally{
			connection.close();
		}
		
		return "save";
	}
	
	
	public String calander(){
		if(!verifyLogin(request)){
			return "login";
		}
		try{
			String selectedDate = request.getParameter("selectedDate");
			String temp[] = selectedDate.split("/");
			String dayString = "";
			
			
			int year = Integer.parseInt(temp[2]);
			int month = Integer.parseInt(temp[1]);
			int day = Integer.parseInt(temp[0]);
			
			month = month-1;
			
			Date date1 = (new GregorianCalendar(year, month, day)).getTime();
//		    System.out.println(new SimpleDateFormat("EEEE").format(date1));
		    String selectedDay = new SimpleDateFormat("EEEE").format(date1); 
		    
		    int tempDay = 0;
		    
		    if(selectedDay.equals(Constants.SATURDAY)){
		    	tempDay = 5;
		    	dayString = getDayValue(selectedDay,tempDay,day);
		    }
		    if(selectedDay.equals(Constants.FRIDAY)){
		    	tempDay = 4;
		    	dayString = getDayValue(selectedDay,tempDay,day);
		    }
		    if(selectedDay.equals(Constants.THUSRDAY)){
		    	tempDay = 3;
		    	dayString = getDayValue(selectedDay,tempDay,day);
		    }
		    if(selectedDay.equals(Constants.WEDNEDAY)){
		    	tempDay = 2;
		    	dayString = getDayValue(selectedDay,tempDay,day);
		    }
		    if(selectedDay.equals(Constants.TUESDAY)){
		    	tempDay = 1;
		    	dayString = getDayValue(selectedDay,tempDay,day);
		    }
		    if(selectedDay.equals(Constants.MONDAY)){
		    	tempDay = 1;
		    	dayString = getDayValue(selectedDay,tempDay,day);
		    }
		    if(selectedDay.equals(Constants.SUNDAY)){
		    	tempDay = 5;
		    	dayString = getDayValue(selectedDay,tempDay,day);
		    }
		    
		    
		    response.setContentType("text/html");
			response.setHeader("Cache-Control", "no-cache");
			
			response.getWriter().write(""+dayString+""); 
		    
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	
	

	
	private String getDayValue(String selectedDay, int tempDay, int day) {
		StringBuffer str = new StringBuffer();
		int mon=0,tue=0,wed=0,thu=0,fri=0,sat=0,sun = 0;
		
		if(selectedDay.equals(Constants.SUNDAY)){
			mon = day - 6;
	 		str.append(mon+",");
	    
	    	tue = day - 5;
	    	str.append(tue+",");	
	   
	    	wed = day - 4;
	    	str.append(wed+",");
	   
	    	thu = day - 3;
	    	str.append(thu+",");
	   
	    	fri = day - 2;
	    	str.append(fri+",");
	    	
	    	sat = day - 1;
	    	str.append(sat + ",");
	    	
	    	sun = day;
	    	str.append(sun);
		}
		 	
		if(selectedDay.equals(Constants.SATURDAY)){
			mon = day - 5;
	 		str.append(mon+",");
	    
	    	tue = day - 4;
	    	str.append(tue+",");	
	   
	    	wed = day - 3;
	    	str.append(wed+",");
	   
	    	thu = day - 2;
	    	str.append(thu+",");
	   
	    	fri = day - 1;
	    	str.append(fri+",");
	    	
	    	sat = day;
	    	str.append(sat + ",");
	    	
	    	sun = day + 1;
	    	str.append(sun);
		}
		
		if(selectedDay.equals(Constants.FRIDAY)){
			mon = day - 4;
	 		str.append(mon+",");
	    
	    	tue = day - 3;
	    	str.append(tue+",");	
	   
	    	wed = day - 2;
	    	str.append(wed+",");
	   
	    	thu = day - 1;
	    	str.append(thu+",");
	   
	    	fri = day;
	    	str.append(fri+",");
	    	
	    	
	    	sat = day + 1;
	    	str.append(sat+",");
	    	
	    	sun = day + 2;
	    	str.append(sun);
		}
		
		if(selectedDay.equals(Constants.THUSRDAY)){
			mon = day - 3;
	 		str.append(mon+",");
	    
	    	tue = day - 2;
	    	str.append(tue+",");	
	   
	    	wed = day - 1;
	    	str.append(wed+",");
	   
	    	thu = day ;
	    	str.append(thu+",");
	   
	    	fri = day + 1;
	    	str.append(fri+",");
	    	
	    	
	    	sat = day + 2;
	    	str.append(sat+",");
	    	
	    	sun = day + 3;
	    	str.append(sun);
	    	
		}
		
		if(selectedDay.equals(Constants.WEDNEDAY)){
			mon = day - 2;
	 		str.append(mon+",");
	    
	    	tue = day - 1;
	    	str.append(tue+",");	
	   
	    	wed = day;
	    	str.append(wed+",");
	   
	    	thu = day + 1 ;
	    	str.append(thu+",");
	   
	    	fri = day + 2;
	    	str.append(fri+",");
	    	
	    	
	    	sat = day + 3;
	    	str.append(sat+",");
	    	
	    	sun = day + 4;
	    	str.append(sun);
		}
		
		if(selectedDay.equals(Constants.TUESDAY)){
			mon = day - 1;
	 		str.append(mon+",");
	    
	    	tue = day;
	    	str.append(tue+",");	
	   
	    	wed = day + 1;
	    	str.append(wed+",");
	   
	    	thu = day + 2 ;
	    	str.append(thu+",");
	   
	    	fri = day + 3;
	    	str.append(fri+",");
	    	
	    	
	    	sat = day + 4;
	    	str.append(sat+",");
	    	
	    	sun = day + 5;
	    	str.append(sun);
		}
		
		if(selectedDay.equals(Constants.MONDAY)){
			mon = day;
	 		str.append(mon+",");
	    
	    	tue = day + 1;
	    	str.append(tue+",");	
	   
	    	wed = day + 2;
	    	str.append(wed+",");
	   
	    	thu = day + 3 ;
	    	str.append(thu+",");
	   
	    	fri = day + 4;
	    	str.append(fri+",");
	    	
	    	
	    	sat = day + 5;
	    	str.append(sat+",");
	    	
	    	sun = day + 6;
	    	str.append(sun);
		}
		 		
		 		
		
		    
		return str.toString();
	}
	
	
	public String location() throws SQLException{
		if(!verifyLogin(request)){
			return "login";
		}
		int practitionerid = Integer.parseInt(request.getParameter("practitionerid"));
		Connection connection = null;
		try{
			connection = Connection_provider.getconnection();
			AppointmentDAO appointmentDAO = new JDBCAppointmentDAO(connection);
			ArrayList<Location>locationList = appointmentDAO.getLocationList(practitionerid);
			
			StringBuffer str = new StringBuffer();
			str.append("<select name='location' id='location'>");
			for(Location location : locationList){
				str.append("<option value='"+location.getLocation()+"'>"+location.getLocation()+"</option>");
			}
			str.append("</select>");
			
			response.setContentType("text/html");
			response.setHeader("Cache-Control", "no-cache");
			response.getWriter().write(""+str.toString()+""); 
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		finally{
			connection.close();
		}
		
		return null;
	}
	
	
	//set appointment type
	public String settype() throws SQLException{
		if(!verifyLogin(request)){
			return "login";
		}
		int practitionerid = Integer.parseInt(request.getParameter("practitionerid"));
		Connection connection = null;
		try{
			connection = Connection_provider.getconnection();
			AppointmentDAO appointmentDAO = new JDBCAppointmentDAO(connection);
			ArrayList<AppointmentType>appointmentTypeList = appointmentDAO.getAppointmentTypeList(practitionerid);
			
			StringBuffer str = new StringBuffer();
			str.append("<select name='appointmentType' id='appointmentType'>");
			for(AppointmentType appointmentType : appointmentTypeList){
				str.append("<option value='"+appointmentType.getId()+"_"+appointmentType.getDuration()+"'>"+appointmentType.getName()+"</option>");
			}
			str.append("</select>");
			
			response.setContentType("text/html");
			response.setHeader("Cache-Control", "no-cache");
			response.getWriter().write(""+str.toString()+""); 
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		finally{
			connection.close();
		}
		return null;
	}
	
	public String check() throws IOException, SQLException{
		if(!verifyLogin(request)){
			return "login";
		}
		int practitionerid = Integer.parseInt(request.getParameter("practitionerid"));
		String when = request.getParameter("when");
		
		String temp[] = when.split(",");
		String tempDate[] = temp[1].split(" ");
		String date = tempDate[3] + "-" + DateTimeUtils.getYearNumber(tempDate[2]) + "-" + tempDate[1];
		
		Connection connection = null;
		try{
			connection = Connection_provider.getconnection();
			AppointmentDAO appointmentDAO = new JDBCAppointmentDAO(connection);
			
			ArrayList<Appointment>apptAvailibilityList  = appointmentDAO.getApptAvailiability(practitionerid,date);
			
//			System.out.println(when);
			
			StringBuffer str = new StringBuffer();
			str.append("<ul>");
				if(apptAvailibilityList.size()!=0){
					for(Appointment appointment : apptAvailibilityList){
						str.append("<li>Available from=>"+appointment.getStarttime()+" to "+appointment.getEdndtime()+" at "+appointment.getLocation()+" Location </li>");
					}
				}else{
					str.append("<li>Not Avaibable</li>");
				}
				
			str.append("</ul>");
			
			response.setContentType("text/html");
			response.setHeader("Cache-Control", "no-cache");
			response.getWriter().write(str.toString()); 
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		finally{
			connection.close();
		}
		
		return null;
	}

	
	public String hour() throws SQLException{
		if(!verifyLogin(request)){
			return "login";
		}
		int practitionerid = Integer.parseInt(request.getParameter("practitionerid"));
		String location = request.getParameter("location");
		
		String when = request.getParameter("when");
		
		String temp[] = when.split(",");
		String tempDate[] = temp[1].split(" ");
		String date = tempDate[3] + "-" + DateTimeUtils.getYearNumber(tempDate[2]) + "-" + tempDate[1];
		
		Connection connection = null;
		try{
			connection = Connection_provider.getconnection();
			AppointmentDAO appointmentDAO = new JDBCAppointmentDAO(connection);
			
			String startTime = appointmentDAO.getStartTime(practitionerid,location,date);
			
			response.setContentType("text/html");
			response.setHeader("Cache-Control", "no-cache");
			response.getWriter().write(startTime); 
			
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		finally{
			connection.close();
		}
		
		return null;
	}


	public AppointmentForm getModel() {
		
		return appointmentForm;
	}



	
	public void prepare() throws Exception {
		
		appointmentForm.setMonthList(PopulateList.monthList());
		appointmentForm.setYearList(PopulateList.yearList());
		appointmentForm.setHourList(PopulateList.hourList());
		appointmentForm.setMinuteList(PopulateList.minuteList());
		
	}

}
