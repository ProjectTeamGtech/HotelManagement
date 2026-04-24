package com.apm.DiaryManagement.eu.blogic.jdbc;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import com.apm.Appointment.eu.bi.AppointmentTypeDAO;
import com.apm.DiaryManagement.eu.bi.ClientDAO;
import com.apm.DiaryManagement.eu.bi.DiaryManagementDAO;
import com.apm.DiaryManagement.eu.entity.Client;
import com.apm.DiaryManagement.eu.entity.DiaryManagement;
import com.apm.DiaryManagement.eu.entity.NotAvailableSlot;
import com.apm.DiaryManagement.eu.entity.Tdcode;
import com.apm.Master.eu.bi.MasterDAO;
import com.apm.Master.eu.blogic.jdbc.JDBCMasterDAO;
import com.apm.Master.eu.entity.Master;
import com.apm.Pharmacy.web.action.Tra;
import com.apm.Registration.eu.bi.UserProfileDAO;
import com.apm.Registration.eu.blogic.jdbc.JDBCUserProfileDAO;
import com.apm.Registration.eu.entity.Clinic;
import com.apm.Registration.eu.entity.Location;
import com.apm.Registration.eu.entity.UserProfile;
import com.apm.ThirdParties.eu.entity.ThirdParty;
import com.apm.common.eu.blogic.jdbc.JDBCBaseDAO;
import com.apm.common.utils.DateTimeUtils;
import com.apm.common.utils.Pagination;
import com.apm.common.web.common.helper.LoginInfo;
import com.apm.main.common.constants.Constants;

public class JDBCDiaryManagentDAO extends JDBCBaseDAO implements DiaryManagementDAO {
	
	public JDBCDiaryManagentDAO(Connection connection){
		this.connection = connection;
		
	}

	public int saveAppointmentSlot(DiaryManagement diaryManagement) {
		
		PreparedStatement preparedStatement = null;
		int result = 0;
		String sql = "insert into apm_apmt_slot(commencing,diaryuser,diaryuserid,location,room,description,starttime,endtime,apmtduration,onlinebooking,tdcode,weekname,year,weekfullname) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		
		try{
			
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, diaryManagement.getCommencing());
			preparedStatement.setString(2, diaryManagement.getSelectedDiaryUser());
			preparedStatement.setInt(3, diaryManagement.getDiarUserid());
			preparedStatement.setString(4, diaryManagement.getLocation());;
			preparedStatement.setString(5, diaryManagement.getRoom());
			preparedStatement.setString(6, diaryManagement.getDescription());
			preparedStatement.setString(7, diaryManagement.getSTime());
			preparedStatement.setString(8, diaryManagement.getEndTime());
			preparedStatement.setString(9, diaryManagement.getApmtDuration());
			preparedStatement.setBoolean(10, diaryManagement.isOnlineBooking());
			preparedStatement.setString(11, diaryManagement.getTdCode());
			preparedStatement.setString(12, diaryManagement.getWeekName());
			preparedStatement.setString(13, diaryManagement.getYear());
			preparedStatement.setString(14, diaryManagement.getWeekFullName());
			
			result = preparedStatement.executeUpdate();
			
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}

	public ArrayList<DiaryManagement> getPractionerList(int year,int clinicid,Pagination pagination,String searchText,LoginInfo loginInfo) {
		PreparedStatement preparedStatement = null;
		ArrayList<DiaryManagement>list = new ArrayList<DiaryManagement>();
		String sql = "";
		if(!searchText.equals("")){
			sql = "select id,firstname,lastname,diarycolor,usrposition from apm_user where usertype = 4 and firstname like('%"+searchText+"%') and hasdiary=1  order by usrposition " ;
		}else{
			sql = "select id,firstname,lastname,diarycolor,usrposition from apm_user where usertype = 4 and hasdiary=1 order by usrposition" ;
		}
		
		if(loginInfo.getUserType()==4){
			sql = "select id,firstname,lastname,diarycolor,usrposition from apm_user where usertype = 4 and id = "+loginInfo.getId()+" and and hasdiary=1 " ;
		}
		
		 
		sql = pagination.getSQLQuery(sql);
		try{
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs  = preparedStatement.executeQuery();
			while(rs.next()){
				
				DiaryManagement diaryManagement = new DiaryManagement();
				diaryManagement.setDiarUserid(rs.getInt(1));
				diaryManagement.setFirstName(rs.getString(2));
				diaryManagement.setLastName(rs.getString(3));
				diaryManagement.setDiaryColor(rs.getString(4));
				diaryManagement.setUsrPosition(rs.getString(5));
				
				ArrayList<DiaryManagement>tdcodeList = getTdCodeList(year,diaryManagement.getDiarUserid());
				ArrayList<Tdcode>tdDataList = new ArrayList<Tdcode>();
				
				
				
				
				
				for(DiaryManagement tds : tdcodeList){
					 String weeklistname = getWeekList(tds.getTdCode(),year);
					 String temp[] = weeklistname.split(",");
					 weeklistname = getWeekShortName(temp);
					 String location = getAppoinmentLocation(tds.getTdCode(),year);
					 String colorname = getLocationColor(location,clinicid);
					 
					 Tdcode tdcode = new Tdcode();
					 tdcode.setWeekListName(weeklistname);
					 tdcode.setColorName(colorname);
					 tdcode.setTdCode(tds.getTdCode());
					
					 tdDataList.add(tdcode);
				}
				
				diaryManagement.setTdDataList(tdDataList);
				
				list.add(diaryManagement);
			}
			
		}catch (Exception e) {
			// TODO: handle exception
		}
		return list;
	}
	
	
	public String getWeekShortName(String weekFullName[]){
		String week[] = new String[7];
		StringBuffer str = new StringBuffer();
		for(String s : weekFullName){
			if(s.equals("Monday")){
				week[0] = "M";
			}
			if(s.equals("Tuesday")){
				week[1] = "T";
			}
			if(s.equals("Wednesday")){
				week[2] = "W";
			}
			if(s.equals("Thursday")){
				week[3] = "T";
			}
			if(s.equals("Friday")){
				week[4] = "F";
			}
			if(s.equals("Saturday")){
				week[5] = "S";
			}
			if(s.equals("Sunday")){
				week[6] = "S";
			}
		}
		
		for(String s : week){
			if(s == null){
				s = "";
			}else{
				str.append(s);
			}
			
		}
		
		return str.toString();
	}
	

	public ArrayList<Location> getLocationList(int id) {
		PreparedStatement preparedStatement = null;
		ArrayList<Location>list = new ArrayList<Location>();
		String sql = "select country,city,address,postcode,location,color,id from apm_clinic_location  ";
		
		try{
			
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()){
				Location location = new Location();
				location.setCountry(rs.getString(1));
				location.setCity(rs.getString(2));
				location.setAddress(rs.getString(3));
				location.setPinCode(rs.getString(4));
				//location.setLocation(rs.getString(5) + " " +  "("+location.getPinCode()+")");
				
				location.setColorName(rs.getString(6));
				location.setLocationid(rs.getString(7));
				location.setLocation(rs.getString(5));
				list.add(location);
			}
			
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	public ArrayList<DiaryManagement> getTdCodeList(int year,int diaryuserid) {
		PreparedStatement preparedStatement = null;
		ArrayList<DiaryManagement> list = new ArrayList<DiaryManagement>();
		String sql = "SELECT tdcode FROM apm_apmt_slot where diaryuserid = "+diaryuserid+" and year="+year+" group by tdcode ";
		
		try{
			
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs  = preparedStatement.executeQuery();
			while(rs.next()){
				DiaryManagement diaryManagement = new DiaryManagement();
				diaryManagement.setTdCode(rs.getString(1));
				list.add(diaryManagement);
			}
			
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	public String getWeekList(String tdCode, int year) {
		PreparedStatement preparedStatement = null;
		String result = "";
		//String sql = "SELECT weekfullname FROM apm_apmt_slot where tdcode = ? and year = ?  group by weekfullname ";
		String sql = "SELECT weekfullname FROM apm_apmt_slot where tdcode = ? group by weekfullname ";
		StringBuffer str = new StringBuffer();
		try{
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, tdCode);
			//preparedStatement.setInt(2, year);
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()){
				str.append(rs.getString(1) + ",");
			}
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		return str.toString();
	}

	public String getAppoinmentLocation(String tdCode, int year) {
		PreparedStatement  preparedStatement = null;
		String result = "";
		String sql = "SELECT location  FROM apm_apmt_slot where tdcode = ? and year = ? group by tdcode";
		
		try{
			
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, tdCode);
			preparedStatement.setInt(2, year);
			ResultSet rs = preparedStatement.executeQuery();
			
			while(rs.next()){
				result = rs.getString(1);
			}
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public String getLocationColor(String location, int userId) {
		PreparedStatement preparedStatement = null;
		String result = "";
		String sql = "SELECT color FROM apm_clinic_location where id = ? ";
		
		try{
			preparedStatement = connection.prepareStatement(sql);
			//preparedStatement.setInt(1, userId);
			preparedStatement.setString(1, location);
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()){
				result = rs.getString(1);
			}
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public ArrayList<DiaryManagement> getAppointmentSlotData(String diaryuserid,int clinicUserid,String tdcode,String year) {
		PreparedStatement preparedStatement = null;
		ArrayList<DiaryManagement>list = new ArrayList<DiaryManagement>();
		String sql = "SELECT id,starttime,endtime,apmtduration,location,tdcode,weekname,commencing,weekfullname,onlinebooking FROM apm_apmt_slot where diaryuserid= "+diaryuserid+" and tdcode = ? and year = ? ";
		
		try{
			
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, tdcode);
			preparedStatement.setString(2, year);
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()){
				DiaryManagement diaryManagement = new DiaryManagement();
				diaryManagement.setId(rs.getInt(1));
				diaryManagement.setSTime(rs.getString(2));
				diaryManagement.setEndTime(rs.getString(3));
				diaryManagement.setApmtDuration(rs.getString(4));
				diaryManagement.setLocation(rs.getString(5));
				String locationColor = getLocationColor(diaryManagement.getLocation(), clinicUserid);
				diaryManagement.setLocationColor(locationColor);
				diaryManagement.setTdCode(rs.getString(6));
				diaryManagement.setWeekName(rs.getString(7));
				diaryManagement.setCommencing(rs.getString(8));
				diaryManagement.setWeekFullName(rs.getString(9));
				diaryManagement.setOnlineBooking(rs.getBoolean(10));
				
				
				
				list.add(diaryManagement);
			}
			
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	
	
	


	public int updateAppointment(int selectedid, DiaryManagement diaryManagement) {
		PreparedStatement preparedStatement = null;
		int result = 0;
		String sql = "update apm_apmt_slot set starttime=?,endtime=?,apmtduration=?,location=?,onlinebooking=? where id = "+selectedid+" ";
		
		try{
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, diaryManagement.getSTime());
			preparedStatement.setString(2, diaryManagement.getEndTime());
			preparedStatement.setString(3, diaryManagement.getApmtDuration());
			preparedStatement.setString(4, diaryManagement.getLocation());
			preparedStatement.setBoolean(5, diaryManagement.isOnlineBooking());
			
			result = preparedStatement.executeUpdate();
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public int deleteAppointmentSlot(int selectedid) {
		PreparedStatement preparedStatement = null;
		int result = 0;
		String sql = "delete from apm_apmt_slot where id = "+selectedid+" ";
		
		try{
			preparedStatement = connection.prepareStatement(sql);
			result = preparedStatement.executeUpdate();
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public boolean checkUsertdCodeExist(String usertdcode,String year) {
		PreparedStatement preparedStatement = null;
		boolean result = false;
		String sql = "SELECT * FROM apm_apmt_slot where tdcode = ?  and year = ? ";
		
		try{
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, usertdcode);
			preparedStatement.setString(2, year);
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()){
				result = true;
			}
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public int deleteAppointSlotByTdcode(String usertdcode,String year) {
		PreparedStatement preparedStatement = null;
		int result = 0;
		String sql = "delete from apm_apmt_slot where tdcode = ? and year = ? ";
		
		try{
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, usertdcode);
			preparedStatement.setString(2, year);
			result = preparedStatement.executeUpdate();
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	public int deleteAppointSlotByTdcode(String usertdcode,String year,String location) {
		PreparedStatement preparedStatement = null;
		int result = 0;
		String sql = "delete from apm_apmt_slot where tdcode = ? and year = ? and location =? ";
		
		try{
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, usertdcode);
			preparedStatement.setString(2, year);
			preparedStatement.setString(3, location);
			result = preparedStatement.executeUpdate();
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	
	public ArrayList<DiaryManagement> getAppointmentSlotData(int id,
			String date, String year) {
		PreparedStatement preparedStatement = null;
		ArrayList<DiaryManagement>list = new ArrayList<DiaryManagement>();
		String sql = "SELECT id,starttime,endtime,apmtduration,location,tdcode,weekname,commencing,weekfullname,onlinebooking FROM apm_apmt_slot where commencing = '"+date+"' and year = '"+year+"' ";
		
		try{
			
			preparedStatement = connection.prepareStatement(sql);
			
			
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()){
				DiaryManagement diaryManagement = new DiaryManagement();
				diaryManagement.setId(rs.getInt(1));
				diaryManagement.setSTime(rs.getString(2));
				diaryManagement.setEndTime(rs.getString(3));
				diaryManagement.setApmtDuration(rs.getString(4));
				diaryManagement.setLocation(rs.getString(5));
				String locationColor = getLocationColor(diaryManagement.getLocation(), id);
				diaryManagement.setLocationColor(locationColor);
				diaryManagement.setTdCode(rs.getString(6));
				diaryManagement.setWeekName(rs.getString(7));
				diaryManagement.setCommencing(rs.getString(8));
				diaryManagement.setWeekFullName(rs.getString(9));
				diaryManagement.setOnlineBooking(rs.getBoolean(10));
				
				list.add(diaryManagement);
			}
			
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	
	public ArrayList<DiaryManagement> getAppointmentSlotData2(
			String diaryuserid, int id, String date, String year,String locationid) {
		PreparedStatement preparedStatement = null;
		ArrayList<DiaryManagement>list = new ArrayList<DiaryManagement>();
		String sql = "";
		if(!locationid.equals("0")){
			 sql = "SELECT id,starttime,endtime,apmtduration,location,tdcode,weekname,commencing,weekfullname,onlinebooking,diaryuser,diaryuserid FROM apm_apmt_slot where diaryuserid= "+diaryuserid+" and commencing = '"+date+"' and location="+locationid+"  ";
		}else{
			 sql = "SELECT id,starttime,endtime,apmtduration,location,tdcode,weekname,commencing,weekfullname,onlinebooking,diaryuser,diaryuserid FROM apm_apmt_slot where diaryuserid= "+diaryuserid+" and commencing = '"+date+"' ";
		}
			
		
		try{
			
			preparedStatement = connection.prepareStatement(sql);
			
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()){
				DiaryManagement diaryManagement = new DiaryManagement();
				diaryManagement.setId(rs.getInt(1));
				diaryManagement.setSTime(rs.getString(2));
				diaryManagement.setEndTime(rs.getString(3));
				diaryManagement.setApmtDuration(rs.getString(4));
				diaryManagement.setLocation(rs.getString(5));
				String location= getLocationName(rs.getString(5));
				diaryManagement.setLocationName(location);
				
				String locationColor = getLocationColor(diaryManagement.getLocation(), id);
				
				
				diaryManagement.setLocationColor(locationColor);
				diaryManagement.setTdCode(rs.getString(6));
				diaryManagement.setWeekName(rs.getString(7));
				diaryManagement.setCommencing(rs.getString(8));
				diaryManagement.setWeekFullName(rs.getString(9));
				diaryManagement.setOnlineBooking(rs.getBoolean(10));
				diaryManagement.setDiaryUser(rs.getString(11));
				diaryManagement.setDiarUserid(rs.getInt(12));
				
				UserProfileDAO userProfileDAO = new JDBCUserProfileDAO(connection);
				UserProfile userProfile = userProfileDAO.getUserprofileDetails(rs.getInt(12));
				diaryManagement.setDisciplineid(userProfile.getDiciplineName());
				
				list.add(diaryManagement);
			}

			
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	
	public ArrayList<DiaryManagement> getAppointmentSlotData3(int id,
			String date, String year1) {
	
			PreparedStatement preparedStatement = null;
			ArrayList<DiaryManagement>list = new ArrayList<DiaryManagement>();
			String sql = "SELECT id,starttime,endtime,apmtduration,location,tdcode,weekname,commencing,weekfullname,onlinebooking,diaryuserid FROM apm_apmt_slot where commencing = '"+date+"' and year = '"+year1+"' ";
			
			try{
				
				preparedStatement = connection.prepareStatement(sql);
				
				ResultSet rs = preparedStatement.executeQuery();
				while(rs.next()){
					DiaryManagement diaryManagement = new DiaryManagement();
					diaryManagement.setId(rs.getInt(1));
					diaryManagement.setSTime(rs.getString(2));
					diaryManagement.setEndTime(rs.getString(3));
					diaryManagement.setApmtDuration(rs.getString(4));
					diaryManagement.setLocation(rs.getString(5));
					String locationColor = getLocationColor(diaryManagement.getLocation(), id);
					diaryManagement.setLocationColor(locationColor);
					diaryManagement.setTdCode(rs.getString(6));
					diaryManagement.setWeekName(rs.getString(7));
					diaryManagement.setCommencing(rs.getString(8));
					diaryManagement.setWeekFullName(rs.getString(9));
					diaryManagement.setOnlineBooking(rs.getBoolean(10));
					diaryManagement.setDiarUserid(rs.getInt(11));
					
					list.add(diaryManagement);
				}
				
				
			}catch (Exception e) {
				e.printStackTrace();
			}
			return list;
	}
	
	public ArrayList<DiaryManagement> getAllDisplayDiaryUserAppointmentSlotData(
			String diaryuserid, int clinicUserid, String commencing,String locationid,LoginInfo loginInfo) {
		PreparedStatement preparedStatement = null;
		ArrayList<DiaryManagement>list = new ArrayList<DiaryManagement>();
		
		String sql = "";
		
		if(Integer.parseInt(locationid)!=0){
			sql = "SELECT id,starttime,endtime,apmtduration,location,tdcode,weekname,commencing,weekfullname,onlinebooking,diaryuser,diaryuserid FROM apm_apmt_slot where diaryuserid= "+diaryuserid+" and commencing = ? and location="+locationid+" ";
		}else{
			sql = "SELECT id,starttime,endtime,apmtduration,location,tdcode,weekname,commencing,weekfullname,onlinebooking,diaryuser,diaryuserid FROM apm_apmt_slot where diaryuserid= "+diaryuserid+" and commencing = ?  ";
		}
		
		
		
		try{
			
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, commencing);
			
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()){
				DiaryManagement diaryManagement = new DiaryManagement();
				diaryManagement.setId(rs.getInt(1));
				diaryManagement.setSTime(rs.getString(2));
				//diaryManagement.setEndTime(rs.getString(3));
				
				diaryManagement.setEndTime("10:00");
				String ctime = DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone()).split(" ")[1];
				String hh = ctime.split(":")[0];
				if(Integer.parseInt(hh)>=11){
					diaryManagement.setSTime("10:00");
					diaryManagement.setEndTime("13:00");
				}
				
				if(Integer.parseInt(hh)>=13){
					diaryManagement.setSTime("12:00");
					diaryManagement.setEndTime("15:00");
				}
				
				if(Integer.parseInt(hh)>=14){
					diaryManagement.setSTime("14:00");
					diaryManagement.setEndTime("17:00");
				}
				
				if(Integer.parseInt(hh)>=16){
					diaryManagement.setSTime("16:00");
					diaryManagement.setEndTime("19:00");
				}
				
				if(Integer.parseInt(hh)>=18){
					diaryManagement.setSTime("18:00");
					diaryManagement.setEndTime("21:00");
				}
				
				if(Integer.parseInt(hh)>=20){
					diaryManagement.setSTime("20:00");
					diaryManagement.setEndTime("23:00");
				}
				
				diaryManagement.setApmtDuration(rs.getString(4));
				
				diaryManagement.setLocation(rs.getString(5));
				
				String locationColor = getLocationColor(diaryManagement.getLocation(), clinicUserid);
				diaryManagement.setLocationColor(locationColor);
				diaryManagement.setTdCode(rs.getString(6));
				diaryManagement.setWeekName(rs.getString(7));
				diaryManagement.setCommencing(rs.getString(8));
				diaryManagement.setWeekFullName(rs.getString(9));
				diaryManagement.setOnlineBooking(rs.getBoolean(10));
				diaryManagement.setDiaryUser(rs.getString(11));
				diaryManagement.setDiarUserid(rs.getInt(12));
				String location= getLocationName(rs.getString(5));
				diaryManagement.setLocationName(location);
				String fullName = getFullNameOfPractioner(rs.getInt(12));
				
				diaryManagement.setDiaryUser(fullName);
				UserProfileDAO userProfileDAO = new JDBCUserProfileDAO(connection);
				UserProfile userProfile = userProfileDAO.getUserprofileDetails(rs.getInt(12));
				diaryManagement.setDisciplineid(userProfile.getDiciplineName());
				
				list.add(diaryManagement);
			}
			
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}


	
	public ArrayList<DiaryManagement> getAllDiaryUserAppointmentSlotData(
			String diaryuserid, int clinicUserid, String commencing,String locationid) {
		PreparedStatement preparedStatement = null;
		ArrayList<DiaryManagement>list = new ArrayList<DiaryManagement>();
		
		String sql = "";
		
		if(Integer.parseInt(locationid)!=0){
			sql = "SELECT id,starttime,endtime,apmtduration,location,tdcode,weekname,commencing,weekfullname,onlinebooking,diaryuser,diaryuserid FROM apm_apmt_slot where diaryuserid= "+diaryuserid+" and commencing = ? and location="+locationid+" ";
		}else{
			sql = "SELECT id,starttime,endtime,apmtduration,location,tdcode,weekname,commencing,weekfullname,onlinebooking,diaryuser,diaryuserid FROM apm_apmt_slot where diaryuserid= "+diaryuserid+" and commencing = ?  ";
		}
		
		
		
		try{
			
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, commencing);
			
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()){
				DiaryManagement diaryManagement = new DiaryManagement();
				diaryManagement.setId(rs.getInt(1));
				diaryManagement.setSTime(rs.getString(2));
				diaryManagement.setEndTime(rs.getString(3));
				
				diaryManagement.setApmtDuration(rs.getString(4));
				
				diaryManagement.setLocation(rs.getString(5));
				
				String locationColor = getLocationColor(diaryManagement.getLocation(), clinicUserid);
				diaryManagement.setLocationColor(locationColor);
				diaryManagement.setTdCode(rs.getString(6));
				diaryManagement.setWeekName(rs.getString(7));
				diaryManagement.setCommencing(rs.getString(8));
				diaryManagement.setWeekFullName(rs.getString(9));
				diaryManagement.setOnlineBooking(rs.getBoolean(10));
				diaryManagement.setDiaryUser(rs.getString(11));
				diaryManagement.setDiarUserid(rs.getInt(12));
				String location= getLocationName(rs.getString(5));
				diaryManagement.setLocationName(location);
				String fullName = getFullNameOfPractioner(rs.getInt(12));
				
				diaryManagement.setDiaryUser(fullName);
				UserProfileDAO userProfileDAO = new JDBCUserProfileDAO(connection);
				UserProfile userProfile = userProfileDAO.getUserprofileDetails(rs.getInt(12));
				diaryManagement.setDisciplineid(userProfile.getDiciplineName());
				
				list.add(diaryManagement);
			}
			
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	
	

	private String getFullNameOfPractioner(int id) {
		PreparedStatement preparedStatement = null;
		String result = "";
		String sql = "SELECT initial,firstname,lastname FROM apm_user where id= "+id+" ";
		
		try{
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			if(rs.next()){
				result = rs.getString(1) + " " + rs.getString(2) + " " +rs.getString(3);
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
		return result;
	}
	
	
	public ArrayList<NotAvailableSlot> getWrapedEventData(String eventid,String opendb){
		PreparedStatement preparedStatement = null;
		ArrayList<NotAvailableSlot>list = new ArrayList<NotAvailableSlot>();
		
		ClientDAO clientDAO=new JDBCClientDAO(connection);
		UserProfileDAO  userProfileDAO = new JDBCUserProfileDAO(connection);
		
	/*	if(!mobile.equals("")){
			clientid = getClientID(mobile);
		}*/
		
		//String sql = "SELECT id,clientname,aptmtype,starttime,endtime,duration,notes,status,arrivedstatus,dna,clientId,commencing,diaryuserid,charge,reasonforblock,treatmentEpisodeId,apmttypetext,usedsession from apm_available_slot where apmslotid="+diaryuserid+" and diaryuserid="+practitionerid+" and clientid like('%"+clientid+"%') ";
		String sql = "SELECT id,clientname,aptmtype,starttime,endtime,duration,notes,status,arrivedstatus,dna,clientId,commencing,diaryuserid,charge,reasonforblock,treatmentEpisodeId,apmttypetext,usedsession,location,condition_id,whopay,apmslotid,otid,category, procedures, surgeon, anesthesia, ipdno, wardid,otmsg,otaccid from apm_available_slot where id="+eventid+"  ";
		
		if(opendb.equals("staff")){
			sql = "SELECT id,clientname,aptmtype,starttime,endtime,duration,notes,status,arrivedstatus,dna,clientId,commencing,diaryuserid,charge,reasonforblock,treatmentEpisodeId,apmttypetext,usedsession,location,condition_id,whopay,apmslotid,otid,category, procedures, surgeon, anesthesia, ipdno, wardid,otmsg,otaccid from his_staff_slot where id="+eventid+"  ";
		}
		
		//for display code
		String ctime = DateTimeUtils.getUKCurrentDataTime("IST").split(" ")[1];
		String hh = ctime.split(":")[0];
		
		if(opendb.equals("dsplay")){
			if(Integer.parseInt(hh)>=11){
				sql = sql + " and starttime>='10:00' ";
			}
			
			if(Integer.parseInt(hh)>=13){
				sql = sql + " and starttime>='12:00' ";
			}
			
			if(Integer.parseInt(hh)>=14){
				sql = sql + " and starttime>='14:00' ";
			}
			
			if(Integer.parseInt(hh)>=16){
				sql = sql + " and starttime>='16:00' ";
			}
			
			if(Integer.parseInt(hh)>=18){
				sql = sql + " and starttime>='18:00' ";
			}
			
			if(Integer.parseInt(hh)>=20){
				sql = sql + " and starttime>='20:00' ";
			}
		}

		try{
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			
			while(rs.next()){
				NotAvailableSlot notAvailableSlot = new NotAvailableSlot();
				
				String token = getAppointmentToken(notAvailableSlot.getId());
				notAvailableSlot.setToken(token);
				
				notAvailableSlot.setId(rs.getInt(1));
				notAvailableSlot.setClientName(rs.getString(2));
				notAvailableSlot.setApmtType(rs.getString(3));
				notAvailableSlot.setSTime(rs.getString(4));
				notAvailableSlot.setEndTime(rs.getString(5));
				notAvailableSlot.setDuration(rs.getString(6));
				notAvailableSlot.setNotes(rs.getString(7));
				notAvailableSlot.setStatus(rs.getString(8));
				notAvailableSlot.setArrivedStatus(rs.getInt(9));
				notAvailableSlot.setDna(rs.getBoolean(10));
				notAvailableSlot.setClientId(rs.getString(11));
				
				Client client=clientDAO.getClientDetails(notAvailableSlot.getClientId());
				
				notAvailableSlot.setUhid(client.getAbrivationid());
	            notAvailableSlot.setImagename(client.getImageName());
				notAvailableSlot.setCommencing(rs.getString(12));
				notAvailableSlot.setDiaryUserId(rs.getInt(13));
				notAvailableSlot.setCharge(rs.getDouble(14));
				notAvailableSlot.setReasonforblock(rs.getString(15));
				notAvailableSlot.setTreatmentEpisodeId(rs.getString(16));
				notAvailableSlot.setApmttypetext(rs.getString(17));
				
				String name = getClientFullName(rs.getString(11));
				notAvailableSlot.setClientName(name);
				if(Integer.parseInt(notAvailableSlot.getStatus())==0){
					String usedsession = getUsedSession(notAvailableSlot.getTreatmentEpisodeId(),rs.getString(18));
					notAvailableSlot.setUsedsession(usedsession);
				}
				
				int practitionerId = rs.getInt(13);
				int clientId = rs.getInt(11);
				
				//set thirdparty details
				ThirdParty thirdParty = getClientThirdPartyDetails(clientId);
				notAvailableSlot.setTptypeid(thirdParty.getType());
				notAvailableSlot.setTpnameid(thirdParty.getTpnameid());
				notAvailableSlot.setTpName(thirdParty.getThirdPartyName());
				
				
				String practitionerEmailId = getpractitionerEmailId(practitionerId);
				String clientEmailId = getclientEmailId(clientId);
				
				notAvailableSlot.setPractitionerEmail(practitionerEmailId);
				notAvailableSlot.setClientEmail(clientEmailId);
				notAvailableSlot.setLocid(rs.getString(19));
				String location= getLocationName(rs.getString(19));
				notAvailableSlot.setLocation(location);
				notAvailableSlot.setCondition(rs.getString(20));
				notAvailableSlot.setWhopay(rs.getString(21));
				notAvailableSlot.setApmtSlotId(rs.getInt(22));
				
				notAvailableSlot.setOtid(rs.getInt(23));
				String otname = getOtname(notAvailableSlot.getOtid());
				notAvailableSlot.setOtname(otname);
				
				//ot values
				notAvailableSlot.setOtplan(rs.getString(24));
				notAvailableSlot.setProcedure(rs.getString(25));
				notAvailableSlot.setSurgeon(rs.getString(26));
				notAvailableSlot.setAnesthesia(rs.getString(27));
				notAvailableSlot.setIpdno(rs.getString(28));
				notAvailableSlot.setWardid(rs.getString(29));
				notAvailableSlot.setOtmsg(rs.getString(30));
				
				UserProfile u = userProfileDAO.getUserprofileDetails(rs.getInt(31));
				String otaccname = u.getInitial() + " " + u.getFirstname() + " " + u.getLastname();
				notAvailableSlot.setOtaccname(otaccname);
				
				String asistantdoclist = getAsistantDoctorList(notAvailableSlot.getId());
				notAvailableSlot.setAsistantdoclist(asistantdoclist);
				
				//check appointment completed
				boolean isCompleted = checkAppointmentCompleted(notAvailableSlot.getId());
				if(isCompleted && !notAvailableSlot.isDna()){
					notAvailableSlot.setAppointmentCompleted(isCompleted);
					
					boolean chargeCompleted = checkAppointmentChargeCompleted(notAvailableSlot.getId());
					notAvailableSlot.setChargeCompleted(chargeCompleted);
				}
				
				
				
				if(Integer.parseInt(notAvailableSlot.getStatus())==0){
					if(Integer.parseInt(rs.getString(18))==1){
						notAvailableSlot.setFirstApmt(1);
					}
				}
				
				if(Integer.parseInt(notAvailableSlot.getStatus())==0){
					int lastApmt = getLastApmt(notAvailableSlot.getTreatmentEpisodeId(),rs.getString(18));
					notAvailableSlot.setLastApmt(lastApmt);
				}
				
				String fullName = getFullNameOfPractioner(rs.getInt(13));
				notAvailableSlot.setDiaryUser(fullName);
				
				
				list.add(notAvailableSlot);
			}
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	

	public String getAsistantDoctorList(int id) {
		PreparedStatement preparedStatement = null;
		String result = "";
		StringBuffer str = new StringBuffer();
		String sql = "SELECT asistantdoctor FROM apm_ot_parent where apmtid = "+id+" ";
		
		try{
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()){
				str.append(rs.getString(1) + ",");
			}
			if(str.length()!=0){
				result = str.substring(0,str.length()-1);
			}
		}catch (Exception e) {
			// TODO: handle exception
		}
		return result;
	}

	public ArrayList<NotAvailableSlot> getApmtList(String diaryuserid,String practitionerid,String opendb,String rdddval) {
		if(rdddval==null){
			rdddval = "0";
		}
		
		rdddval = "0";
		PreparedStatement preparedStatement = null;
		ArrayList<NotAvailableSlot>list = new ArrayList<NotAvailableSlot>();
		UserProfileDAO userProfileDAO = new JDBCUserProfileDAO(connection);
		
	/*	if(!mobile.equals("")){
			clientid = getClientID(mobile);
		}*/
		
		//String sql = "SELECT id,clientname,aptmtype,starttime,endtime,duration,notes,status,arrivedstatus,dna,clientId,commencing,diaryuserid,charge,reasonforblock,treatmentEpisodeId,apmttypetext,usedsession from apm_available_slot where apmslotid="+diaryuserid+" and diaryuserid="+practitionerid+" and clientid like('%"+clientid+"%') ";
		String sql = "SELECT id,clientname,aptmtype,starttime,endtime,duration,notes,status,arrivedstatus,dna,clientId,commencing,diaryuserid,charge,reasonforblock,treatmentEpisodeId,apmttypetext,usedsession,location,condition_id,whopay,dnanotes,sent,otid,work,category, procedures, surgeon, anesthesia, ipdno, wardid,otmsg,otaccid from apm_available_slot where apmslotid="+diaryuserid+" and diaryuserid="+practitionerid+" " ;
		if(Integer.parseInt(rdddval)!=0){
			int sc = Integer.parseInt(rdddval);
			int sd = sc + 1;
			
			String sc1 = ""+sc+"";
			String sd1 = ""+sd+"";
			if(sc<=9){
				sc1 = "0" + sc1;
			}
			if(sd<=9){
				sd1 = "0" + sd1;
			}
			//sql = sql + " and starttime between '"+sc1+":00' and '"+sd1+":00'";
			sql = sql + " and starttime >= "+sc1+" ";
		}
		if(opendb.equals("staff")){
			sql = "SELECT id,clientname,aptmtype,starttime,endtime,duration,notes,status,arrivedstatus,dna,clientId,commencing,diaryuserid,charge,reasonforblock,treatmentEpisodeId,apmttypetext,usedsession,location,condition_id,whopay,dnanotes,sent,otid,work,category, procedures, surgeon, anesthesia, ipdno, wardid,otmsg,otaccid from his_staff_slot where apmslotid="+diaryuserid+" and diaryuserid="+practitionerid+"  ";
			
		}
		
		//for display code
		String ctime = DateTimeUtils.getUKCurrentDataTime("IST").split(" ")[1];
		String hh = ctime.split(":")[0];
		
		if(opendb.equals("dsplay")){
			if(Integer.parseInt(hh)>=11){
				sql = sql + " and starttime>='10:00' ";
			}
			
			if(Integer.parseInt(hh)>=13){
				sql = sql + " and starttime>='12:00' ";
			}
			
			if(Integer.parseInt(hh)>=14){
				sql = sql + " and starttime>='14:00' ";
			}
			
			if(Integer.parseInt(hh)>=16){
				sql = sql + " and starttime>='16:00' ";
			}
			
			if(Integer.parseInt(hh)>=18){
				sql = sql + " and starttime>='18:00' ";
			}
			
			if(Integer.parseInt(hh)>=20){
				sql = sql + " and starttime>='20:00' ";
			}
			
		}

		try{
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()){
				NotAvailableSlot notAvailableSlot = new NotAvailableSlot();
				
				notAvailableSlot.setId(rs.getInt(1));
				
				String token = getAppointmentToken(notAvailableSlot.getId());
				notAvailableSlot.setToken(token);
				
				notAvailableSlot.setClientName(rs.getString(2));
				notAvailableSlot.setApmtType(rs.getString(3));
				notAvailableSlot.setSTime(rs.getString(4));
				notAvailableSlot.setEndTime(rs.getString(5));
				notAvailableSlot.setDuration(rs.getString(6));
				notAvailableSlot.setNotes(rs.getString(7));
				notAvailableSlot.setStatus(rs.getString(8));
				notAvailableSlot.setArrivedStatus(rs.getInt(9));
				notAvailableSlot.setDna(rs.getBoolean(10));
				notAvailableSlot.setClientId(rs.getString(11));
				notAvailableSlot.setCommencing(rs.getString(12));
				notAvailableSlot.setDiaryUserId(rs.getInt(13));
				notAvailableSlot.setCharge(rs.getDouble(14));
				notAvailableSlot.setReasonforblock(rs.getString(15));
				notAvailableSlot.setTreatmentEpisodeId(rs.getString(16));
				notAvailableSlot.setApmttypetext(rs.getString(17));
				
				String name = getClientFullName(rs.getString(11));
				notAvailableSlot.setClientName(name);
				if(Integer.parseInt(notAvailableSlot.getStatus())==0){
					String usedsession = getUsedSession(notAvailableSlot.getTreatmentEpisodeId(),rs.getString(18));
					notAvailableSlot.setUsedsession(usedsession);
				}
				
				int practitionerId = rs.getInt(13);
				int clientId = rs.getInt(11);
				
				ClientDAO clientDAO = new JDBCClientDAO(connection);
				Client client = clientDAO.getClientDetails(Integer.toString(clientId));
				String profileimage = client.getImageName();
				
				//set thirdparty details
				ThirdParty thirdParty = getClientThirdPartyDetails(clientId);
				notAvailableSlot.setTptypeid(thirdParty.getType());
				notAvailableSlot.setTpnameid(thirdParty.getTpnameid());
				notAvailableSlot.setTpName(thirdParty.getThirdPartyName());
				notAvailableSlot.setUhid(client.getAbrivationid());
				
				
				String practitionerEmailId = getpractitionerEmailId(practitionerId);
				String clientEmailId = getclientEmailId(clientId);
				
				notAvailableSlot.setPractitionerEmail(practitionerEmailId);
				notAvailableSlot.setClientEmail(clientEmailId);
				notAvailableSlot.setLocid(rs.getString(19));
				String location= getLocationName(rs.getString(19));
				notAvailableSlot.setLocation(location);
				notAvailableSlot.setCondition(rs.getString(20));
				notAvailableSlot.setWhopay(rs.getString(21));
				notAvailableSlot.setDnaNotes(rs.getString(22));
				notAvailableSlot.setIsReportsent(rs.getString(23));
				notAvailableSlot.setOtid(rs.getInt(24));
				String otname = getOtname(notAvailableSlot.getOtid());
				notAvailableSlot.setOtname(otname);
				notAvailableSlot.setImagename(profileimage);
				notAvailableSlot.setWork(rs.getString(25));
				
				//ot values
				notAvailableSlot.setOtplan(rs.getString(26));
				notAvailableSlot.setProcedure(rs.getString(27));
				notAvailableSlot.setSurgeon(rs.getString(28));
				notAvailableSlot.setAnesthesia(rs.getString(29));
				notAvailableSlot.setIpdno(rs.getString(30));
				notAvailableSlot.setWardid(rs.getString(31));
				notAvailableSlot.setOtmsg(rs.getString(32));
				
				UserProfile u = userProfileDAO.getUserprofileDetails(rs.getInt(33));
				String otaccname = u.getInitial() + " " + u.getFirstname() + " " + u.getLastname();
				notAvailableSlot.setOtaccname(otaccname);
				
				String asistantdoclist = getAsistantDoctorList(notAvailableSlot.getId());
				notAvailableSlot.setAsistantdoclist(asistantdoclist);
				
				//check appointment completed
				boolean isCompleted = checkAppointmentCompleted(notAvailableSlot.getId());
				if(isCompleted && !notAvailableSlot.isDna()){
					notAvailableSlot.setAppointmentCompleted(isCompleted);
					
					boolean chargeCompleted = checkAppointmentChargeCompleted(notAvailableSlot.getId());
					notAvailableSlot.setChargeCompleted(chargeCompleted);
				}
				
				
				
				if(Integer.parseInt(notAvailableSlot.getStatus())==0){
					if(Integer.parseInt(rs.getString(18))==1){
						notAvailableSlot.setFirstApmt(1);
					}
				}
				
				if(Integer.parseInt(notAvailableSlot.getStatus())==0){
					int lastApmt = getLastApmt(notAvailableSlot.getTreatmentEpisodeId(),rs.getString(18));
					notAvailableSlot.setLastApmt(lastApmt);
				}
				
				
				
				
				list.add(notAvailableSlot);
			}
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	
	


	private String getAppointmentToken(int id) {
		PreparedStatement preparedStatement = null;
		String result = "";
		String sql = "select seqno from apm_available_slot_seqno where apmtid = "+id+" ";
		
		try{
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			if(rs.next()){
				result = rs.getString(1);
			}
			
		}catch (Exception e) {
			// TODO: handle exception
		}
		return result;
	}

	private String getOtname(int otid) {
		PreparedStatement preparedStatement = null;
		String result = "";
		StringBuffer sql = new StringBuffer();
		
		sql.append("select firstname from apm_user inner join apm_ot_parent on ");
		sql.append("apm_ot_parent.otroomid = apm_user.id ");
		sql.append("where apm_ot_parent.id = "+otid+" ");
		
		try{
			preparedStatement = connection.prepareStatement(sql.toString());
			ResultSet rs = preparedStatement.executeQuery();
			if(rs.next()){
				result = rs.getString(1);			}
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		
		return result;
	}

	private ThirdParty getClientThirdPartyDetails(int clientId) {
		PreparedStatement preparedStatement = null;
		ThirdParty thirdParty = new ThirdParty();
		//String sql = "SELECT third_party_id,third_party_name_id FROM apm_patient where id = "+clientId+"";
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT apm_patient.third_party_id,apm_patient.third_party_name_id,company_name FROM apm_patient ");
		sql.append("inner join apm_third_party_details on apm_patient.third_party_name_id = apm_third_party_details.id and apm_patient.id = "+clientId+" ");
		
		
		try{
			
			preparedStatement = connection.prepareStatement(sql.toString());
			ResultSet rs = preparedStatement.executeQuery();
			if(rs.next()){
				thirdParty.setType(rs.getString(1));
				thirdParty.setTpnameid(rs.getString(2));
				thirdParty.setThirdPartyName(rs.getString(3));
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
	
		return thirdParty;
	}

	private boolean checkAppointmentChargeCompleted(int id) {
		PreparedStatement preparedStatement = null;
		boolean result = false;
		//String sql = "SELECT appointmentid,chargetype FROM apm_charges_assesment inner join apm_invoice on apm_invoice.id =  apm_charges_assesment.invoiceid where appointmentid="+id+" ";
		String sql = "SELECT opdpmnt FROM apm_available_slot where id = "+id+" ";
		
		try{
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			if(rs.next()){
				if(rs.getInt(1)>0){
					result = true;
				}
				
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
		return result;
	}

	private String getLocationName(String id) {
		PreparedStatement preparedStatement = null;
		String result = "";
		String sql = "SELECT location FROM apm_clinic_location where id = "+id+" ";
		
		try{
			
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			if(rs.next()){
				result = rs.getString(1);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return result;
	}

	private String getClientFullName(String id) {
		PreparedStatement preparedStatement = null;
	String result = "";
	String sql = "SELECT title,firstname,surname FROM apm_patient where id = '"+id+"' ";
	
	try{
		
		preparedStatement = connection.prepareStatement(sql);
		ResultSet rs = preparedStatement.executeQuery();
		if(rs.next()){
			result = rs.getString(1)+" "+rs.getString(2)+" "+rs.getString(3);
		}
	}catch(Exception e){
		e.printStackTrace();
	}
	return result;
	}

	private String getClientID(String mobile) {
		PreparedStatement preparedStatement = null;
		String result = "";
		String sql = "SELECT id FROM apm_patient where mobno = '"+mobile+"' ";
		
		try{
			
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			if(rs.next()){
				result = rs.getString(1);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return result;
	}

	private String getPayBy(String treatmentEpisodeId) {
		PreparedStatement preparedStatement = null;
		String result = "";
		String sql = "SELECT payby FROM apm_treatment_episode where id= "+treatmentEpisodeId+" ";
		
		try{
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			result = rs.getString(1);
			
		}catch(Exception e){
			e.printStackTrace();
		}
		return result;
	}

	private int getLastApmt(String treatmentEpisodeId,String usedsession) {
		PreparedStatement preparedStatement = null;
		int result = 0;
		String sql = "SELECT sessions FROM apm_treatment_episode where  id= "+treatmentEpisodeId+" ";
		
		try{
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			if(rs.next()){
				result = rs.getInt(1);
			}
			
			if(result == Integer.parseInt(usedsession)){
				result = 1;
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
		return result;
	}

	private int getFirstApmt(String treatmentEpisodeId) {
		PreparedStatement preparedStatement = null;
		int result = 0;
		String sql = "SELECT usedsession FROM apm_treatment_episode where id= "+treatmentEpisodeId+" ";
		
		try{
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			if(rs.next()){
				result = rs.getInt(1);
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
		return result;
	}

	public int getTempUsedSession(String treatmentEpisodeId) {
		
		PreparedStatement preparedStatement = null;
		int result = 0;
		String sql = "SELECT usedsession FROM apm_treatment_episode where id="+treatmentEpisodeId+" ";
		
		try{
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			if(rs.next()){
				result = rs.getInt(1);
			}
			
		}catch(Exception e){
			e.printStackTrace();
			
		}
		
		return result;
	}

	private String getUsedSession(String treatmentEpisodeId,String usedsession) {
		PreparedStatement preparedStatement = null;
		String result = "";
		String sql = "SELECT sessions,usedsession,payby FROM apm_treatment_episode where id="+treatmentEpisodeId+" ";
		
		try{
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			if(rs.next()){
				int session = rs.getInt(1);
				//int usedsession = rs.getInt(2);
				//int resultSession = session - usedsession;
				
				int usedsession1 = Integer.parseInt(usedsession);
				
				String payby = rs.getString(3);
				String str = "";
				if(payby.equals(Constants.PAY_BY_CLIENT)){
					str = "Self";
				}else{
					str = "TP";
				}
				
				if(usedsession1!=0){
					result =  "(" +usedsession1+ "-" +session+")";
				}else{
					//result = "("+0+" of "+session+")";
					result = "";
				}
				
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
		return result;
	}

	public int updateTreatmentSession(int resultSession,
			String treatmentEpisodeId) {
		PreparedStatement preparedStatement = null;
		int result = 0;
		String sql = "update apm_treatment_episode set usedsession ="+resultSession+" where id = "+treatmentEpisodeId+" ";
		
		try{
			preparedStatement = connection.prepareStatement(sql);
			result = preparedStatement.executeUpdate();
			
		}catch(Exception e){
			e.printStackTrace();
//			StringWriter sw = new StringWriter();
//	         PrintWriter pw = new PrintWriter(sw);
//	         e.printStackTrace(pw);
//			Tra.exceptionMail(sw.toString());
		}
		return result;
	}

	public boolean checkAppointmentCompleted(int id) {
		PreparedStatement preparedStatement = null;
		boolean result = false;
		String sql = "SELECT * FROM apm_invoice where appointmentid = "+id+" ";
		
		try{
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			if(rs.next()){
				result = true;
			}
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	private String getclientEmailId(int clientId) {
		PreparedStatement preparedStatement = null;
		
		String practitionerEmailId = "";
		String sql = "select email from apm_patient where id = "+clientId+"";
		
		try{
			preparedStatement = connection.prepareStatement(sql);
			
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()){
				practitionerEmailId = rs.getString(1);
			}
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		return practitionerEmailId;
	}

	private String getpractitionerEmailId(int practitionerId) {
		PreparedStatement preparedStatement = null;
		String clientEmailId = " ";
		String sql = "select email from apm_user where id = '"+practitionerId+"'";
		
		try{
			preparedStatement = connection.prepareStatement(sql);
			
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()){
				clientEmailId = rs.getString(1);
			}
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		return clientEmailId;
	}

	public boolean checkPractitionerAvailibility(String commencing,
			String diaryuserid) {
		
		PreparedStatement preparedStatement = null;
		boolean result = false;
		String sql = "SELECT * FROM apm_apmt_slot where diaryuserid="+diaryuserid+" and commencing=? ";
		
		try{
			
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, commencing);
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()){
				result = true;
			}
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}

	public int getDiarymanagementTotalUser(int year, int clinicid,
			String searchText,LoginInfo loginInfo) {
		PreparedStatement preparedStatement = null;
		int result = 0;
		
		String sql = "";
		
		if(!searchText.equals("")){
			sql = "select count(*) from apm_user where usertype = 4  and firstname like('%"+searchText+"%') and hasdiary=1";
		}else{
			sql = "select count(*) from apm_user where usertype = 4  and firstname like('%"+searchText+"%') and hasdiary=1";
		}
		
		if(loginInfo.getUserType()==4){
			sql = "select count(*) from apm_user where usertype = 4 and id = "+loginInfo.getId()+" and hasdiary=1" ;
		}
	 
		
		try{
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			if(rs.next()){
				result = rs.getInt(1);
			}
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public ArrayList<NotAvailableSlot> getAppintmentSendMailDetails(
			int appointmentid) {
		
		PreparedStatement preparedStatement = null;
		ArrayList<NotAvailableSlot>list = new ArrayList<NotAvailableSlot>();
		String sql = "SELECT clientid,diaryuserid,starttime,duration,location,commencing,ad FROM apm_available_slot where id="+appointmentid+" ";
		
		try{
			
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()){
				NotAvailableSlot notAvailableSlot = new NotAvailableSlot();
				Client clientDetails = getclientDetails(rs.getInt(1));
				notAvailableSlot.setClientId(rs.getString(1));
				notAvailableSlot.setClientDetails(clientDetails);
				UserProfile userProfile = getUserProfile(rs.getInt(2));
				
				notAvailableSlot.setUserDEtails(userProfile);
				notAvailableSlot.setSTime(rs.getString(3));
				notAvailableSlot.setDuration(rs.getString(4));
				
				Location location = getLocDetails(rs.getString(5)); 
				notAvailableSlot.setLocationDetails(location);
				notAvailableSlot.setCommencing(rs.getString(6));
				notAvailableSlot.setAd(rs.getInt(7));
				list.add(notAvailableSlot);
			}
			
		}catch(Exception e){
			
			e.printStackTrace();
		}
		return list;
	}

	
	private Location getLocDetails(String id) {
		PreparedStatement preparedStatement = null;
		Location location = new Location();
		String sql = "SELECT id,address,location,contact_no FROM apm_clinic_location where id= "+id+" ";
		
		try{
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			if(rs.next()){
				location.setLocationid(rs.getString(1));
				location.setAddress(rs.getString(2));
				location.setLocationname(rs.getString(3));
				location.setContactNo(rs.getString(4));
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return location;
	}

	public UserProfile getUserProfile(int practitionerId) {
		PreparedStatement preparedStatement = null;
		UserProfile userProfile = new UserProfile();
		String sql = "SELECT concat(initial, ' ', firstname,' ',lastname),email,owner_qualification,discription FROM apm_user where id= "+practitionerId+" ";
		
		try{
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			if(rs.next()){
				userProfile.setFullname(rs.getString(1));
				userProfile.setEmail(rs.getString(2));
				userProfile.setQualification(rs.getString(3));
				
				MasterDAO masterDAO = new JDBCMasterDAO(connection);
				Master master = masterDAO.getDisciplineData(rs.getString(4));
				
				userProfile.setDiciplineName(master.getDiscipline());
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return userProfile;
	
	}

	private Client getclientDetails(int clientid) {
		PreparedStatement preparedStatement = null;
		Client client = new Client();
		String sql = "SELECT title,firstname,surname,email FROM apm_patient where id= "+clientid+" ";
		
		try{
			
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			if(rs.next()){
				client.setTitle(rs.getString(1));
				client.setFirstName(rs.getString(1) + " " + rs.getString(2) + " " + rs.getString(3));
				client.setEmail(rs.getString(4));
				
			}
		}catch(Exception e){
			e.printStackTrace();
		}
			
		return client;
	}

	public int deleteDiarySlot(String selectedid) {
		PreparedStatement preparedStatement = null;
		int result = 0;
		String sql = "delete FROM apm_apmt_slot where tdcode='"+selectedid+"'";
		
		try{
			preparedStatement = connection.prepareStatement(sql);
			result = preparedStatement.executeUpdate();
			
			
		}catch(Exception e){
			e.printStackTrace();
		}
		return result;
	}

	
	public int updateUserPosition(int position, int id) {
		PreparedStatement preparedStatement = null;
		int result = 0;
		String sql = "update apm_user set usrposition="+position+" where id="+id+" ";
		
		try{
			preparedStatement = connection.prepareStatement(sql);
			result = preparedStatement.executeUpdate();
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return result;
	}

	public int getUserIdToSetPosition(int prevPosition) {
		PreparedStatement preparedStatement = null;
		int result = 0;
		String sql = "select id from apm_user where usrposition = "+prevPosition+" ";
		
		try{
			
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			
			if(rs.next()){
				result = rs.getInt(1);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return result;
	}

	public int updateCurrentUserPosition(int prevPosition, int curPosition) {
		// TODO Auto-generated method stub
		return 0;
	}
	
	
	

	public boolean checkEventAllreadyExist(String commencing, String location,
			String diaryuserId, String starttime, String endtime) {
		starttime = starttime + ":" + "00";
		endtime = endtime + ":" + "00";
		PreparedStatement preparedStatement = null;
		boolean result = false;
		StringBuffer sql = new StringBuffer();
		
		sql.append("SELECT * FROM apm_apmt_slot  where ");
		sql.append("commencing='"+commencing+"' and diaryuserid="+diaryuserId+" ");
		sql.append("and NOT ('"+starttime+"' > endtime OR '"+endtime+"' < starttime) ");
		
		try{
			
			preparedStatement = connection.prepareStatement(sql.toString());
			ResultSet rs = preparedStatement.executeQuery();
			if(rs.next()){
				result = true;
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
		return result;
	}

		
	public boolean checkEventAllreadyExist(String commencing, String location,
			String diaryuserId, String starttime, String endtime,
			int editAppointId) {
		starttime = starttime + ":" + "00";
		endtime = endtime + ":" + "00";
		PreparedStatement preparedStatement = null;
		boolean result = false;
		StringBuffer sql = new StringBuffer();
		
		sql.append("SELECT * FROM apm_apmt_slot  where ");
		sql.append("commencing='"+commencing+"' and diaryuserid="+diaryuserId+" ");
		sql.append("and NOT ('"+starttime+"' > endtime OR '"+endtime+"' < starttime) and id!="+editAppointId+" ");
		
		try{
			
			preparedStatement = connection.prepareStatement(sql.toString());
			ResultSet rs = preparedStatement.executeQuery();
			if(rs.next()){
				result = true;
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
		return result;
		
	}


	public DiaryManagement getAvailableSlotdata(int editAppointId) {
		PreparedStatement preparedStatement = null;
		DiaryManagement diaryManagement = new DiaryManagement();
		//String sql = "SELECT starttime,endtime FROM apm_available_slot where id="+editAppointId+" ";
		
		String sql = "SELECT starttime,endtime,commencing,diaryuserid FROM apm_apmt_slot where id = "+editAppointId+" ";
		
		try{
			
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			if(rs.next()){
				diaryManagement.setSTime(rs.getString(1));
				diaryManagement.setEndTime(rs.getString(2));
				diaryManagement.setCommencing(rs.getString(3));
				diaryManagement.setDiarUserid(rs.getInt(4));
				diaryManagement.setId(editAppointId);
			}
			
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return diaryManagement;
	}

	

	public 	int coutnEsistingSlot(String commencing, String location,
			String diaryuserId, String starttime, String endtime,
			int editAppointId){
		
		starttime = starttime + ":" + "00";
		endtime = endtime + ":" + "00";
		PreparedStatement preparedStatement = null;
		int result = 0;
		StringBuffer sql = new StringBuffer();
		
		sql.append("SELECT count(*) FROM apm_apmt_slot  where ");
		sql.append("commencing='"+commencing+"' and diaryuserid="+diaryuserId+" ");
		sql.append("and NOT ('"+starttime+"' > endtime OR '"+endtime+"' < starttime) ");
		
		try{
			
			preparedStatement = connection.prepareStatement(sql.toString());
			ResultSet rs = preparedStatement.executeQuery();
			if(rs.next()){
				result = rs.getInt(1);
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
		return result;
	}


	public String getExistStartTime(String commencing, String location,
			String diaryuserId, String starttime, String endtime,
			int editAppointId){
		
		starttime = starttime + ":" + "00";
		endtime = endtime + ":" + "00";
		PreparedStatement preparedStatement = null;
		String result = "";
		StringBuffer sql = new StringBuffer();
		
		sql.append("SELECT starttime FROM apm_apmt_slot  where ");
		sql.append("commencing='"+commencing+"' and diaryuserid="+diaryuserId+"  ");
		sql.append("and NOT ('"+starttime+"' > endtime OR '"+endtime+"' < starttime) limit 1 ");
		
		try{
			
			preparedStatement = connection.prepareStatement(sql.toString());
			ResultSet rs = preparedStatement.executeQuery();
			if(rs.next()){
				result = rs.getString(1);
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
		return result;
	}
	
	
	
	public String getEditExistStartTime(String commencing, String location,
			String diaryuserId, String starttime, String endtime,
			int editAppointId) {
		starttime = starttime + ":" + "00";
		endtime = endtime + ":" + "00";
		PreparedStatement preparedStatement = null;
		String result = "";
		StringBuffer sql = new StringBuffer();
		
		sql.append("SELECT starttime FROM apm_apmt_slot  where ");
		sql.append("commencing='"+commencing+"' and diaryuserid="+diaryuserId+"  ");
		sql.append("and NOT ('"+starttime+"' > endtime OR '"+endtime+"' < starttime)  limit 1 ");
		
		try{
			
			preparedStatement = connection.prepareStatement(sql.toString());
			ResultSet rs = preparedStatement.executeQuery();
			if(rs.next()){
				result = rs.getString(1);
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
		return result;
	}

	public boolean checkSlotExist(String dt, DiaryManagement diaryManagement) {
		String  starttime = diaryManagement.getSTime();
		String endtime = diaryManagement.getEndTime();
		starttime = starttime + ":" + "00";
		endtime = endtime + ":" + "00";
		PreparedStatement preparedStatement = null;
		boolean result = false;
		StringBuffer sql = new StringBuffer();
		
		sql.append("SELECT * FROM apm_apmt_slot  where ");
		sql.append("commencing='"+dt+"' and diaryuserid="+diaryManagement.getDiarUserid()+"  ");
		sql.append("and NOT ('"+starttime+"' > endtime OR '"+endtime+"' < starttime) ");
		
		try{
			
			preparedStatement = connection.prepareStatement(sql.toString());
			ResultSet rs = preparedStatement.executeQuery();
			if(rs.next()){
				result = true;
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
		return result;
	}

	public boolean checkApmtExist(String selectedid) {
		PreparedStatement preparedStatement = null;
		boolean result = false;
		String sql = "SELECT * FROM apm_available_slot where apmslotid = "+selectedid+" ";
		
		try{
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			if(rs.next()){
				result = true;
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
		return result;
	}

	public double getClientDebitTotal(String clientId) {
		PreparedStatement preparedStatement = null;
		double result = 0;
		String sql = "select debit,discount from apm_charges_invoice where clientid = "+clientId+" ";
		
		try{
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()){
				double debit = rs.getDouble(1);
				double disc = rs.getDouble(2);
				double discamt = 0;
				
				if(disc>0){
					discamt = (debit*disc)/100;
					debit = debit - discamt;
				}
				result = result +  debit;
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
		return result;
	}

	public double getClientPayment(String clientId) {
		PreparedStatement preparedStatement = null;
		double result  = 0;
		String sql = "select sum(payment) from apm_charges_payment where clientid = "+clientId+" ";
		
		try{
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			if(rs.next()){
				result = rs.getDouble(1);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return result;
	}

	public Clinic getClinicStartAndEndTime(int clinicid) {
		PreparedStatement preparedStatement = null;
		Clinic clinic = new Clinic();
		String sql = "SELECT starttime,endtime FROM apm_user where id = "+clinicid+" ";
		
		try{
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			if(rs.next()){
				clinic.setStarttime(rs.getString(1));
				clinic.setEndtime(rs.getString(2));
			}
			
		}catch(Exception e){
			e.printStackTrace();
			
		}
		return clinic;
	}

	public int getTempSessions(String treatmentEpisodeId) {
		PreparedStatement preparedStatement = null;
		int result = 0;
		String sql = "SELECT sessions FROM apm_treatment_episode where id = "+treatmentEpisodeId+" ";
		
		try{
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			if(rs.next()){
				result = rs.getInt(1);
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
		return result;
	}


	
	public int updateRoleAccess(DiaryManagement diaryManagement) {
		
        int result=0;
        try {
        	StringBuffer buffer=new StringBuffer();
        	buffer.append("update apm_role_access set diarymanagement=?, appointmentbooking=?,");
        	buffer.append("basicfinance=?, fullfinance=?, medicalrecord=?, communication=?, report=?,");
        	buffer.append("assessmentForm=?, manageclient=?, manageclinic=?, managemaster=?, manageprisc=?, manageinvst=?,");
        	buffer.append("manageipd=?, manageopd=?, apmtfinder=?, manageemr=?, expences=?, managemis=?,");
        	buffer.append("payroll=?, bloodbak=?, inventory=?, discharge=?,packs=?, investigation_chart=?, sheduler=?");
        	buffer.append(",housekeeping=?, dietery=?, cafeteria=?, packages=?, ambulance=?, bank_deposite=?, account_reconcilation=?");
        	buffer.append(",ot=?, casualty=?, pharmacy=?, mrd=?, marketing=?, voice_recorder=?,indent_access=?, tpa=?, nabh_quality=?,doctor_opd=? ,cathlab=?");
        	buffer.append(",myhr=?, daycare=?, emergency_lbl=?,medicine_barcode=?,refund=?,discount=?,vaccination=?,video_training=?,setup_master=?,sale_pharmacy=? ");
        	buffer.append(",nabh_quality=?,token_display=?, delete_invoice=?, registrationdash =? ");
        	buffer.append(" where usertype='"+diaryManagement.getJobtitle()+"'");    
        	PreparedStatement ps=connection.prepareStatement(buffer.toString());
        	ps.setBoolean(1, diaryManagement.isSetting());
        	ps.setBoolean(2, diaryManagement.isOpd());
        	ps.setBoolean(3, diaryManagement.isAccounts());
        	ps.setBoolean(4, diaryManagement.isAccounts());
        	ps.setBoolean(5, diaryManagement.isEmr());
        	ps.setBoolean(6, diaryManagement.isConsultants());
        	ps.setBoolean(7, diaryManagement.isMis());
        	ps.setBoolean(8, diaryManagement.isConsultants());
        	ps.setBoolean(9, diaryManagement.isPatient());
        	ps.setBoolean(10, diaryManagement.isSetting());
        	ps.setBoolean(11, diaryManagement.isSetting());
        	ps.setBoolean(12, diaryManagement.isMedicine());
        	ps.setBoolean(13, diaryManagement.isInvestigation());
        	ps.setBoolean(14, diaryManagement.isIpd());
        	ps.setBoolean(15, diaryManagement.isOpd());
        	ps.setBoolean(16, diaryManagement.isAppointmentfinder());
        	ps.setBoolean(17, diaryManagement.isEmr());
        	ps.setBoolean(18, diaryManagement.isExpenses());
        	ps.setBoolean(19, diaryManagement.isMis());
        	ps.setBoolean(20, diaryManagement.isPayroll());
        	ps.setBoolean(21, diaryManagement.isBloodbank());
        	ps.setBoolean(22, diaryManagement.isInventory());
        	ps.setBoolean(23, diaryManagement.isDischarge());
        	ps.setBoolean(24, diaryManagement.isPacks());
        	ps.setBoolean(25, diaryManagement.isInvestigation_chart());
        	ps.setBoolean(26, diaryManagement.isSheduler());
        	ps.setBoolean(27, diaryManagement.isHousekeeping());
        	ps.setBoolean(28, diaryManagement.isDietery());
        	ps.setBoolean(29, diaryManagement.isCafeteria());
        	ps.setBoolean(30, diaryManagement.isPackages());
        	ps.setBoolean(31, diaryManagement.isAmbulance());
        	ps.setBoolean(32, diaryManagement.isBank_deposite());
        	ps.setBoolean(33, diaryManagement.isAccount_reconcilation());
        	ps.setBoolean(34, diaryManagement.isOt());
        	ps.setBoolean(35, diaryManagement.isCasualty());
        	ps.setBoolean(36, diaryManagement.isPharmacy());
        	ps.setBoolean(37, diaryManagement.isMrd());
        	ps.setBoolean(38, diaryManagement.isMarketing());
        	ps.setBoolean(39, diaryManagement.isVoice_recording());
        	
        	//  06 feb 2018 
        	ps.setBoolean(40, diaryManagement.isIndent());
        	ps.setBoolean(41, diaryManagement.isTpa());
        	ps.setBoolean(42, diaryManagement.isNabh_quality());
        	ps.setBoolean(44, diaryManagement.isCathlab());
        	//  08 feb 2018
        	ps.setBoolean(43, diaryManagement.isDoctor_opd());
        	ps.setBoolean(45, diaryManagement.isMyhr());
        	ps.setBoolean(46, diaryManagement.isDaycare());
        	ps.setBoolean(47, diaryManagement.isEmergency_lbl());
        	ps.setBoolean(48, diaryManagement.isMedicine_barcode());
        	
        	ps.setBoolean(49, diaryManagement.isRefund());
        	ps.setBoolean(50, diaryManagement.isDiscount());
        	ps.setBoolean(51, diaryManagement.isVaccination());
        	ps.setBoolean(52, diaryManagement.isVideo_training());
        	ps.setBoolean(53, diaryManagement.isSetup_master());
        	ps.setBoolean(54, diaryManagement.isSale_pharmacy());
        	ps.setBoolean(55, diaryManagement.isNabh_quality());
        	ps.setBoolean(56, diaryManagement.isToken_display());
        	ps.setBoolean(57, diaryManagement.isDelete_invoice());
        	ps.setBoolean(58, diaryManagement.isRegistrationdash());
        	result=ps.executeUpdate();
		} catch (Exception e) {
		   e.printStackTrace();
		}
		return result;
	}


public DiaryManagement getRoleAccessofUser(String jobtitle) {
		
		DiaryManagement diaryManagement=new DiaryManagement();
		
		try {
			
			String sql="select id,diarymanagement, appointmentbooking, basicfinance, fullfinance, medicalrecord, "
					+ "communication, report, assessmentForm, manageclient, manageclinic, managemaster, manageprisc,"
					+ " manageinvst, manageipd, manageopd, apmtfinder, manageemr, expences, managemis, payroll, "
					+ "bloodbak, inventory, discharge,packs, investigation_chart, sheduler, housekeeping, dietery, "
					+ "cafeteria, packages, ambulance, bank_deposite, account_reconcilation, ot, casualty, pharmacy, "
					+ "mrd, marketing, voice_recorder,indent_access,daily_opd,indent_approve, cash_desk,tpa,nabh_quality,"
					+ "doctor_opd,show_master,token_display,add_medicine,pharm_print_backdate,cathlab,myhr,daycare,"
					+ "emergency_lbl,medicine_barcode,refund,discount,vaccination,video_training,setup_master,"
					+ "sale_pharmacy,delete_invoice,registrationdash "
					+ "from apm_role_access where usertype='"+jobtitle+"'";
			PreparedStatement ps=connection.prepareStatement(sql);
			ResultSet rs=ps.executeQuery();
			
			while(rs.next()){
				 
				   diaryManagement.setId(rs.getInt(1));
				   diaryManagement.setSetting(rs.getBoolean(2));
				   diaryManagement.setOpd(rs.getBoolean(3));
				   diaryManagement.setAccounts(rs.getBoolean(4));
				   diaryManagement.setAccounts(rs.getBoolean(5));
				   diaryManagement.setMedicine(rs.getBoolean(6));
				   diaryManagement.setConsultants(rs.getBoolean(7));
				   diaryManagement.setMis(rs.getBoolean(8));
				   diaryManagement.setIpd(rs.getBoolean(9));
				   diaryManagement.setPatient(rs.getBoolean(10));
				   diaryManagement.setSetting(rs.getBoolean(11));
				   diaryManagement.setSetting(rs.getBoolean(12));
				   diaryManagement.setMedicine(rs.getBoolean(13));
				   diaryManagement.setInvestigation(rs.getBoolean(14));
				   diaryManagement.setIpd(rs.getBoolean(15));
				   diaryManagement.setOpd(rs.getBoolean(16));
				   diaryManagement.setAppointmentfinder(rs.getBoolean(17));
				   diaryManagement.setEmr(rs.getBoolean(18));
				   diaryManagement.setExpenses(rs.getBoolean(19));
				   diaryManagement.setMis(rs.getBoolean(20));
				   diaryManagement.setPayroll(rs.getBoolean(21));
				   diaryManagement.setBloodbank(rs.getBoolean(22));
				   diaryManagement.setInventory(rs.getBoolean(23));
				   diaryManagement.setDischarge(rs.getBoolean(24));
				   
				   diaryManagement.setPacks(rs.getBoolean(25));
				   diaryManagement.setInvestigation_chart(rs.getBoolean(26));
				   diaryManagement.setSheduler(rs.getBoolean(27));
				   diaryManagement.setHousekeeping(rs.getBoolean(28));
				   diaryManagement.setDietery(rs.getBoolean(29));
				   diaryManagement.setCafeteria(rs.getBoolean(30));
				   diaryManagement.setPackages(rs.getBoolean(31));
				   diaryManagement.setAmbulance(rs.getBoolean(32));
				   diaryManagement.setBank_deposite(rs.getBoolean(33));
				   diaryManagement.setAccount_reconcilation(rs.getBoolean(34));
					
				   // get new terms ot,casualty,pharmacy,mrd,marketing,voice recorder
				   diaryManagement.setOt(rs.getBoolean(35));
				   diaryManagement.setCasualty(rs.getBoolean(36));
				   diaryManagement.setPharmacy(rs.getBoolean(37));
				   diaryManagement.setMrd(rs.getBoolean(38));
				   diaryManagement.setMarketing(rs.getBoolean(39));
				   diaryManagement.setVoice_recording(rs.getBoolean(40));
				   diaryManagement.setIndent(rs.getBoolean(41));
				   diaryManagement.setDaily_opd(rs.getBoolean(42));
				   diaryManagement.setIndent_approve(rs.getBoolean(43));
				   diaryManagement.setCash_desk(rs.getBoolean(44));
				   
				   //06 feb 2018 akash
				   diaryManagement.setTpa(rs.getBoolean(45));
				   diaryManagement.setNabh_quality(rs.getBoolean(46));
				   diaryManagement.setDoctor_opd(rs.getBoolean(47));
				   //lokesh
				   diaryManagement.setShow_master(rs.getBoolean(48));
				   diaryManagement.setToken_display(rs.getBoolean(49));
				   diaryManagement.setAdd_medicine(rs.getBoolean(50)); 
				  
				   diaryManagement.setPharm_print_backdate(rs.getBoolean(51));
				   diaryManagement.setCathlab(rs.getBoolean(52));
				   diaryManagement.setMyhr(rs.getBoolean(53));
				   diaryManagement.setDaycare(rs.getBoolean(54));
				   diaryManagement.setEmergency_lbl(rs.getBoolean(55));
				   diaryManagement.setMedicine_barcode(rs.getBoolean(56));
				   //refund,discount,vaccination,video_training,setup_master,sale_pharmacy
				   diaryManagement.setRefund(rs.getBoolean(57));
				   diaryManagement.setDiscount(rs.getBoolean(58));
				   diaryManagement.setVaccination(rs.getBoolean(59));
				   diaryManagement.setVideo_training(rs.getBoolean(60));
				   diaryManagement.setSetup_master(rs.getBoolean(61));
				   diaryManagement.setSale_pharmacy(rs.getBoolean(62));
				   diaryManagement.setDelete_invoice(rs.getBoolean(63));
				   diaryManagement.setRegistrationdash(rs.getBoolean(64));
			}
			
		} catch (Exception e) {

		   e.printStackTrace();
		}
	
		return diaryManagement;
	}

	public ArrayList<DiaryManagement> getAllEventList(Pagination pagination) {

		ArrayList<DiaryManagement> list=new ArrayList<DiaryManagement>();
		
		String sql="select id, name, description, time, fromdate, todate, place, jobtitle from apm_events";
		try {
			
			if(pagination!=null){
				sql=pagination.getSQLQuery(sql);
			}
			
			PreparedStatement ps=connection.prepareStatement(sql);
			ResultSet rs=ps.executeQuery();
			while(rs.next()){
				
				DiaryManagement diaryManagement=new DiaryManagement();
				diaryManagement.setId(rs.getInt(1));
				diaryManagement.setEventname(rs.getString(2));
				diaryManagement.setDescription(rs.getString(3));
				diaryManagement.setTime(rs.getString(4));
				diaryManagement.setFromdate(rs.getString(5));
				diaryManagement.setTodate(rs.getString(6));
				diaryManagement.setPlace(rs.getString(7));
				//diaryManagement.setDiarUserid(rs.getInt(8));
				diaryManagement.setJobtitle(rs.getString(8));
				list.add(diaryManagement);				
			}
			
		} catch (Exception e) {

		   e.printStackTrace();
		}
		
		return list;
	}

	public int getTotalEventsCount() {

		int result=0;
		try {
			String sql="select count(*) from apm_events";
			PreparedStatement ps=connection.prepareStatement(sql);
			ResultSet rs=ps.executeQuery();
			while(rs.next()){
				result=rs.getInt(1);
			}
		} catch (Exception e) {

		   e.printStackTrace();
		}
		return result;
	}

	public ArrayList<DiaryManagement> getAllJobTitleList() {

		ArrayList<DiaryManagement> list=new ArrayList<DiaryManagement>();
		
		try {
		
			String sql="select id,jobtitle from job_title";
			PreparedStatement ps=connection.prepareStatement(sql);
			ResultSet rs=ps.executeQuery();
			while(rs.next()){
				
				DiaryManagement diaryManagement=new DiaryManagement();
				diaryManagement.setId(rs.getInt(1));
				diaryManagement.setJobtitle(rs.getString(2));
				list.add(diaryManagement);
			}
		} catch (Exception e) {

		   e.printStackTrace();
		}
		
		return list;
	}

	public String getJobTitleNamefromID(int selectedid) {

		String name="";
		try {
			String sql="select jobtitle from job_title where id="+selectedid+"";
			PreparedStatement ps=connection.prepareStatement(sql);
			ResultSet rs=ps.executeQuery();
			while(rs.next()){
				
				name=rs.getString(1);
			}
		} catch (Exception e) {

		   e.printStackTrace();
		}
		
		return name;
	}

	public int saveEvent(DiaryManagement diaryManagement) {

		int result=0;
		try {
			
			String sql="insert into apm_events (name, description, time, fromdate, todate, place, jobtitle) values (?,?,?,?,?,?,?)";
			PreparedStatement ps=connection.prepareStatement(sql);
			ps.setString(1, diaryManagement.getEventname());
			ps.setString(2, diaryManagement.getDescription());
		    ps.setString(3, diaryManagement.getTime());
		    ps.setString(4, diaryManagement.getFromdate());
		    ps.setString(5, diaryManagement.getTodate());
		    ps.setString(6, diaryManagement.getPlace());
		    ps.setString(7, diaryManagement.getJobtitle());
		    
		    result=ps.executeUpdate();
		} catch (Exception e) {

		   e.printStackTrace();
		}
		
		return result;
	}

	public int deleteEvent(String id) {

		int result=0;
		
		try {
			String sql="delete from apm_events where id="+id+"";
			PreparedStatement ps=connection.prepareStatement(sql);
			result=ps.executeUpdate();
           			
		} catch (Exception e) {

		   e.printStackTrace();
		}
		return result;
	}

	public DiaryManagement getEvent(String selectedid) {

		DiaryManagement diaryManagement=new DiaryManagement(); 
		
		try {
			String sql="SELECT name, description, time, fromdate, todate, place, jobtitle from apm_events where id="+selectedid+"";
			PreparedStatement ps=connection.prepareStatement(sql);
			ResultSet rs=ps.executeQuery();
			while(rs.next()){
				
				diaryManagement.setEventname(rs.getString(1));
				diaryManagement.setDescription(rs.getString(2));
				diaryManagement.setTime(rs.getString(3));
				diaryManagement.setFromdate(rs.getString(4));
				diaryManagement.setTodate(rs.getString(5));
				diaryManagement.setPlace(rs.getString(6));
				diaryManagement.setJobtitle(rs.getString(7));
		        diaryManagement.setId(Integer.parseInt(selectedid));
				
			}
			
		} catch (Exception e) {

		   e.printStackTrace();
		}
		
		return diaryManagement;
	}

	public int updateEvent(DiaryManagement diaryManagement) {

		int result=0;
		try {
			
			String sql="update apm_events set name=?, description=?, time=?, fromdate=?, todate=?, place=?, jobtitle=? where id="+diaryManagement.getId()+"";
			PreparedStatement ps=connection.prepareStatement(sql);
			ps.setString(1, diaryManagement.getEventname());
			ps.setString(2, diaryManagement.getDescription());
		    ps.setString(3, diaryManagement.getTime());
		    ps.setString(4, diaryManagement.getFromdate());
		    ps.setString(5, diaryManagement.getTodate());
		    ps.setString(6, diaryManagement.getPlace());
		    ps.setString(7, diaryManagement.getJobtitle());
		    
		    result=ps.executeUpdate();
		} catch (Exception e) {

			e.printStackTrace();
		}
		
		return result;
	}

	public ArrayList<Master> getWardList() {
		PreparedStatement preparedStatement = null;
		ArrayList<Master>list = new ArrayList<Master>();
		String sql = "SELECT wardname FROM apm_ipd_ward ";
		
		try{
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()){
				Master master = new Master();
				master.setName(rs.getString(1));
				
				list.add(master);
			}
			
		}catch (Exception e) {
			// TODO: handle exception
		}
		return list;
	}

	public ArrayList<UserProfile> getRotaList(String fromDate, String toDate,String strdate,String jobtitle) {
		PreparedStatement preparedStatement = null;
		ArrayList<UserProfile>list = new ArrayList<UserProfile>();
		StringBuffer sql = new StringBuffer();
		
		
		sql.append("SELECT diaryuserid, concat(initial,' ',firstname,' ',lastname)," +
				"commencing,apm_apmt_slot.starttime,apm_apmt_slot.endtime,weekfullname ");
		sql.append("FROM apm_apmt_slot ");
		sql.append("inner join apm_user on apm_user.id = apm_apmt_slot.diaryuserid ");
		sql.append("where jobgroup!=4 and commencing between '"+fromDate+"' and '"+toDate+"'  ");
		
		if(!jobtitle.equals("")){
			sql.append("and jobtitle='"+jobtitle+"' ");
		}
		sql.append("group by diaryuserid ");
		
		try{
			preparedStatement = connection.prepareStatement(sql.toString());
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()){
				UserProfile userProfile = new UserProfile();
				userProfile.setId(rs.getInt(1));
				userProfile.setFullname(rs.getString(2));
				
				ArrayList<DiaryManagement>rotadataList = getUserRotaDataList(userProfile.getId(),fromDate,toDate,strdate);
				userProfile.setRotadataList(rotadataList);
				
				list.add(userProfile);
			}
			
			
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		return list;
	}

	private ArrayList<DiaryManagement> getUserRotaDataList(int id,String fromDate,String toDate,String strdate) {
		PreparedStatement preparedStatement = null;
		ArrayList<DiaryManagement>list = new ArrayList<DiaryManagement>();
		
		
		String temp[] = strdate.split(",");
		
		for(int i=0;i<temp.length;i++){
			
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT  commencing,apm_apmt_slot.starttime,apm_apmt_slot.endtime,weekfullname ");
		sql.append("FROM apm_apmt_slot ");
		//sql.append("inner join apm_user on apm_user.id = apm_apmt_slot.diaryuserid ");
		sql.append("where commencing ="+temp[i]+"  and diaryuserid = "+id+" ");
		
		try{
			
			preparedStatement = connection.prepareStatement(sql.toString());
			ResultSet rs = preparedStatement.executeQuery();
			DiaryManagement diaryManagement = new DiaryManagement();
			if(rs.next()){
				
				
				diaryManagement.setCommencing(rs.getString(1));
				
				/*String temp[] = diaryManagement.getCommencing().split("-");
				String month = temp[1];
				String date = temp[2];
				String commencing = date + "-" + DateTimeUtils.getMonthName(month);
				diaryManagement.setCommencing(commencing);*/
				
				diaryManagement.setSTime(rs.getString(2));
				diaryManagement.setEndTime(rs.getString(3));
				diaryManagement.setWeekFullName(rs.getString(4));
				
				
				
			}
			
			list.add(diaryManagement);
		}catch (Exception e) {
			// TODO: handle exception
		}
		}
		return list;
	}

	public ArrayList<DiaryManagement> getWeekNameList(String fromDate,
			String toDate) {
		PreparedStatement preparedStatement = null;
		ArrayList<DiaryManagement>list = new ArrayList<DiaryManagement>();
		StringBuffer sql = new StringBuffer();
		
		try{
			
		
			
			fromDate = DateTimeUtils.getCommencingDate2(fromDate);
			toDate = DateTimeUtils.getCommencingDate2(toDate);
		
			long dif = DateTimeUtils.getDifferenceOfTwoDate(fromDate, toDate);
			
			String temp[] = fromDate.split("/");
			fromDate = temp[2] + "/" + temp[1] + "/" + temp[0];
			
		
			
			for(int i=0;i<dif;i++){
				
				DiaryManagement diaryManagement = new DiaryManagement();
				
				DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
				Calendar cal = Calendar.getInstance();
				cal.setTime(new Date(fromDate));
				cal.add(Calendar.DATE, 1); 
				fromDate = dateFormat.format(cal.getTime());
				
//				System.out.println(fromDate); 
				
				String temps[] = fromDate.split("/");
				String month = DateTimeUtils.getMonthName(temps[1]);
				String date = temps[2];
				
				String commencing = date + "-" + month;
				diaryManagement.setCommencing(commencing);
				
				
				int yy = Integer.parseInt(temps[0]);
				int mm = Integer.parseInt(temps[1]);
				int dd = Integer.parseInt(temps[2]);
				
				String weekFullName = DateTimeUtils.getWeekName(yy,mm,dd);
				diaryManagement.setWeekFullName(weekFullName);
				
				
				String rotadate = temps[0] + "-" + temps[1] + "-" + temps[2];
				diaryManagement.setRotadate(rotadate);
				/*for(UserProfile userProfile : rotaUserList){
					
					diaryManagement.setDiaryUser(userProfile.getFullname());
					ArrayList<DiaryManagement>rotadataList = getUserRotaDataList(userProfile.getId(),rotadate,toDate);
					diaryManagement.setRotadataList(rotadataList);
				}
				*/
				list.add(diaryManagement);
				
			}
			
//			System.out.println(dif);
				
		}catch (Exception e) {
			e.printStackTrace();
		}
	
		return list;
	}

	public ArrayList<DiaryManagement> getAllEventListExist() {

		ArrayList<DiaryManagement> list=new ArrayList<DiaryManagement>();
		PreparedStatement ps = null;
		 
		  try {
		   
		   String sql="select id, name, description, time, fromdate, todate, place, jobtitle from apm_events";
		    ps=connection.prepareStatement(sql);
		   ResultSet rs=ps.executeQuery();
		   while(rs.next()){
		    
		    DiaryManagement diaryManagement=new DiaryManagement();
		    diaryManagement.setId(rs.getInt(1));
		    diaryManagement.setEventname(rs.getString(2));
		    diaryManagement.setDescription(rs.getString(3));
		    diaryManagement.setTime(rs.getString(4));
		    diaryManagement.setFromdate(rs.getString(5));
		    diaryManagement.setTodate(rs.getString(6));
		    
		    diaryManagement.setPlace(rs.getString(7));
		    //diaryManagement.setDiarUserid(rs.getInt(8));
		    diaryManagement.setJobtitle(rs.getString(8));
		    
		    String eventdate=diaryManagement.getTodate();
		    Calendar eventcalendar=Calendar.getInstance();
		    SimpleDateFormat dateFormat=new SimpleDateFormat("dd-MM-yyyy");
		    Date date=dateFormat.parse(eventdate);
		    eventcalendar.setTime(date);
		    
		    Calendar calendartoday=Calendar.getInstance();
		    String datenow=dateFormat.format(calendartoday.getTime());
		    
		    if(datenow.equals(eventdate)){
		     list.add(diaryManagement);
		    }
		    else if(calendartoday.compareTo(eventcalendar)>0){
		      
		    }
		    else {
		      list.add(diaryManagement);
		    }
		   }
		   
		  } catch (Exception e) {

		     e.printStackTrace();
		  }finally{
			  try {
				ps.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		  }
		  
		  return list;
		  
		 }
	public String getcityfrmaindashboard(int selectedid) {
		String city = "";
		try {
		String sql = "SELECT city from apm_clinic_location where userid="+selectedid+"";
		PreparedStatement preparedStatement = connection.prepareStatement(sql);
		ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()){
			city = rs.getString(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return city;
	}

	public ArrayList<String> getDepartmentList() {

		ArrayList<String> list= new ArrayList<String>();
		
		try {
		
			String sql="select discipline from apm_discipline";
			PreparedStatement ps=connection.prepareStatement(sql);
			ResultSet rs =ps.executeQuery();
			while(rs.next()){
				
				   list.add(rs.getString(1));
			}
			
		} catch (Exception e) {

			e.printStackTrace();
		}
		
		return list;
	}
    
	public ArrayList<Master> getSpecializationList() {

		ArrayList<Master> list= new ArrayList<Master>();
		
		try {
		
			String sql="select id,discipline from apm_discipline";
			PreparedStatement ps=connection.prepareStatement(sql);
			ResultSet rs =ps.executeQuery();
			while(rs.next()){
				
				   Master master=new Master();
				   master.setId(rs.getInt(1));
				   
				   ArrayList<Master> listSelected= getAllSelectedIpdFormDepartment();
				   for(Master demo: listSelected) {
					   
					    if(demo.getId()==master.getId()) {
					    	master.setStatus(1);
					    	break;
					    }
				   }
				   
				   master.setName(rs.getString(2));
				   list.add(master);
			}
			
		} catch (Exception e) {

			e.printStackTrace();
		}
		
		return list;
	}
	
	public ArrayList<Master> getAllSelectedIpdFormDepartment() {
		
		ArrayList<Master> list= new ArrayList<Master>();
		try {
			
			String sql="SELECT dept_id from apm_ipd_form_setting";
			PreparedStatement ps=connection.prepareStatement(sql);
			ResultSet rs=ps.executeQuery();
			while(rs.next()) {
				   
				  Master master=new Master();
				  master.setId(rs.getInt(1));
				  list.add(master);
			}
				
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
		
	}

	public DiaryManagement getEmergencyDetails() {
		DiaryManagement diaryManagement = new DiaryManagement();
		try {
			String sql ="select emrgency_title,emrgency_data from apm_user where id=1 ";
			PreparedStatement  preparedStatement = connection.prepareStatement(sql);
			ResultSet  resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				diaryManagement.setEmrgency_title(resultSet.getString(1));
				diaryManagement.setEmrgency_data(resultSet.getString(2));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return diaryManagement;
	}

	public int updateEmergencyDetails(DiaryManagement diaryManagement) {
		int res = 0;
		try {
			String sql ="update apm_user set emrgency_title='"+diaryManagement.getEmrgency_title()+"',emrgency_data='"+diaryManagement.getEmrgency_data()+"' where id=1";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			res = preparedStatement.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return res;
	}

	@Override
	public DiaryManagement getdoctorDiarydata(String docid) {
		
		DiaryManagement diaryManagement=new DiaryManagement(); 
		
		try {
			
			String sql ="select id,diaryuser,diaryuserid,location,starttime,endtime,apmtduration,onlinebooking,tdcode from apm_apmt_slot where diaryuserid= '"+docid+"' limit 0,1 ";
			PreparedStatement  preparedStatement = connection.prepareStatement(sql);
			ResultSet  rs = preparedStatement.executeQuery();
			while (rs.next()) {
				diaryManagement.setSelectedDiaryUser(rs.getString(2));
				diaryManagement.setDiarUserid(Integer.parseInt(docid));
				diaryManagement.setLocation(rs.getString(3));
				diaryManagement.setsTime(rs.getString(4));
				diaryManagement.setEndTime(rs.getString(5));
				diaryManagement.setApmtDuration(rs.getString(6));
				diaryManagement.setOnlineBooking(rs.getBoolean(7));
				diaryManagement.setTdCode(rs.getString(8));
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return diaryManagement;
	}

	@Override
	public int saveDiaryforfakept(DiaryManagement diaryManagement) {
		
		PreparedStatement preparedStatement = null;
		int result = 0;
		String sql = "insert into apm_apmt_slot(commencing,diaryuser,diaryuserid,location,room,description,starttime,endtime,apmtduration,onlinebooking,tdcode,weekname,year,weekfullname) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		
		try{
			
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, diaryManagement.getCommencing());
			preparedStatement.setString(2, diaryManagement.getSelectedDiaryUser());
			preparedStatement.setInt(3, diaryManagement.getDiarUserid());
			preparedStatement.setString(4, diaryManagement.getLocation());;
			preparedStatement.setString(5, diaryManagement.getRoom());
			preparedStatement.setString(6, diaryManagement.getDescription());
			preparedStatement.setString(7, "07:00");
			preparedStatement.setString(8, "11:00");
			preparedStatement.setString(9, "00:05");
			preparedStatement.setBoolean(10, diaryManagement.isOnlineBooking());
			preparedStatement.setString(11, diaryManagement.getTdCode());
			preparedStatement.setString(12, diaryManagement.getWeekName());
			preparedStatement.setString(13, diaryManagement.getYear());
			preparedStatement.setString(14, diaryManagement.getWeekFullName());
			
			result = preparedStatement.executeUpdate();
			
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;

		
	}

	@Override
	public int getupdatehasDiary(String docid) {
		
		PreparedStatement preparedStatement = null;
		int result = 0;
		String sql = "update apm_user set hasdiary=1 where id = "+docid+" ";
		
		try{
			preparedStatement = connection.prepareStatement(sql);
			result = preparedStatement.executeUpdate();
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		return result;
		
	}

	@Override
	public DiaryManagement getdoctorDetails(int selectedid) {
		PreparedStatement pst=null;
		DiaryManagement diaryManagement=new DiaryManagement();
		try {
			String sql="select concat(firstname,' ',lastname),discription from apm_user where id="+selectedid+"";
			pst=connection.prepareStatement(sql);
			ResultSet rs=pst.executeQuery();
			while(rs.next()) {
				diaryManagement.setSelectedDiaryUser(rs.getString(1));
				diaryManagement.setLocation(rs.getString(2));
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return diaryManagement;
	}

	@Override
	public ArrayList<DiaryManagement> getManasPractionerList(int year, int clinicid, Pagination pagination, String searchText,
			LoginInfo loginInfo,String clinistaff) {
		PreparedStatement preparedStatement = null;
		ArrayList<DiaryManagement>list = new ArrayList<DiaryManagement>();
		if(clinistaff.equals("0")) {
			clinistaff=Integer.toString(loginInfo.getId());
		}
		String sql = "";
		if(!searchText.equals("")){
			sql = "select id,firstname,lastname,diarycolor,usrposition from apm_user where clinic_staff="+clinistaff+" and jobtitle='Practitioner' and usertype = 4 and firstname like('%"+searchText+"%') and hasdiary=1  order by usrposition " ;
		}else{
			sql = "select id,firstname,lastname,diarycolor,usrposition from apm_user where clinic_staff="+clinistaff+" and jobtitle='Practitioner' and usertype = 4 and hasdiary=1 order by usrposition" ;
		}
		
		if(loginInfo.getUserType()==4){
			sql = "select id,firstname,lastname,diarycolor,usrposition from apm_user where usertype = 4 and id = "+loginInfo.getId()+" and and hasdiary=1 " ;
		}
		
		 
		sql = pagination.getSQLQuery(sql);
		try{
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs  = preparedStatement.executeQuery();
			while(rs.next()){
				
				DiaryManagement diaryManagement = new DiaryManagement();
				diaryManagement.setDiarUserid(rs.getInt(1));
				diaryManagement.setFirstName(rs.getString(2));
				diaryManagement.setLastName(rs.getString(3));
				diaryManagement.setDiaryColor(rs.getString(4));
				diaryManagement.setUsrPosition(rs.getString(5));
				
				ArrayList<DiaryManagement>tdcodeList = getTdCodeList(year,diaryManagement.getDiarUserid());
				ArrayList<Tdcode>tdDataList = new ArrayList<Tdcode>();
				
				
				
				
				
				for(DiaryManagement tds : tdcodeList){
					 String weeklistname = getWeekList(tds.getTdCode(),year);
					 String temp[] = weeklistname.split(",");
					 weeklistname = getWeekShortName(temp);
					 String location = getAppoinmentLocation(tds.getTdCode(),year);
					 String colorname = getLocationColor(location,clinicid);
					 
					 Tdcode tdcode = new Tdcode();
					 tdcode.setWeekListName(weeklistname);
					 tdcode.setColorName(colorname);
					 tdcode.setTdCode(tds.getTdCode());
					
					 tdDataList.add(tdcode);
				}
				
				diaryManagement.setTdDataList(tdDataList);
				
				list.add(diaryManagement);
			}
			
		}catch (Exception e) {
			// TODO: handle exception
		}
		return list;
		
	}

	@Override
	public int updateData(String smtp, String password, String email) {
		int res = 0;
		try {
			String sql ="update apm_clinic_location set SMTP='"+smtp+"',password='"+password+"',emailId='"+email+"' where id=1";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			res = preparedStatement.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return res;
		
	}
	

}
