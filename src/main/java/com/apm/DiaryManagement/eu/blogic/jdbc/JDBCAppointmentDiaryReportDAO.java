package com.apm.DiaryManagement.eu.blogic.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.apm.DiaryManagement.eu.bi.AppointmentDiaryReportDAO;
import com.apm.DiaryManagement.eu.entity.AppointmentDiaryReport;
import com.apm.DiaryManagement.eu.entity.DiaryManagement;
import com.apm.common.eu.blogic.jdbc.JDBCBaseDAO;
import com.apm.common.utils.Pagination;

public class JDBCAppointmentDiaryReportDAO extends JDBCBaseDAO implements AppointmentDiaryReportDAO {
	
public JDBCAppointmentDiaryReportDAO(Connection connection){
		
		this.connection = connection;
	}


public ArrayList<AppointmentDiaryReport> getDiaryUserList(int id) {
	PreparedStatement preparedStatement = null;
	ArrayList<AppointmentDiaryReport>list = new ArrayList<AppointmentDiaryReport>();
	String sql = "select id,firstname,lastname from apm_user";
	
	try{
		
		preparedStatement = connection.prepareStatement(sql);
		ResultSet rs = preparedStatement.executeQuery();
		while(rs.next()){
			AppointmentDiaryReport appointmentDiaryReport = new AppointmentDiaryReport();
			appointmentDiaryReport.setId(rs.getInt(1));
			appointmentDiaryReport.setDiaryUser(rs.getString(2) + " " + rs.getString(3));
			list.add(appointmentDiaryReport);
			
		}
		
		
	}catch (Exception e) {
		e.printStackTrace();
	}
	
	return list;
}


public ArrayList<AppointmentDiaryReport> getDiaryReportList(String diaryUserId,
		String fromDate, String toDate, Pagination pagination) {
	PreparedStatement preparedStatement = null;
	ArrayList<AppointmentDiaryReport>list = new ArrayList<AppointmentDiaryReport>();
	String sql = "select id,commencing,starttime,duration,diaryusername,clientname,newClient,arrivedstatus,dna,notes from apm_available_slot where diaryuserid = "+diaryUserId+" and commencing between '"+fromDate+"' AND '"+toDate+"'" ;
	sql = pagination.getSQLQuery(sql);
	try{
		
		preparedStatement = connection.prepareStatement(sql);
		ResultSet rs = preparedStatement.executeQuery();
		while(rs.next()){
			AppointmentDiaryReport appointmentDiaryReport = new AppointmentDiaryReport();
			appointmentDiaryReport.setId(rs.getInt(1));
			appointmentDiaryReport.setDate(rs.getString(2));
			appointmentDiaryReport.setStartTime(rs.getString(3));
			appointmentDiaryReport.setDuration(rs.getString(4));
			appointmentDiaryReport.setDiaryUser(rs.getString(5));
			appointmentDiaryReport.setClientName(rs.getString(6));
			appointmentDiaryReport.setNewClient(rs.getString(7));
			appointmentDiaryReport.setAttend(rs.getString(8));
			appointmentDiaryReport.setDna(rs.getString(9));
			appointmentDiaryReport.setNotes(rs.getString(10));
			
			list.add(appointmentDiaryReport);
			
		}
		
		
	}catch (Exception e) {
		e.printStackTrace();
	}
	
	return list;
}


public ArrayList<AppointmentDiaryReport> getDepartmentList() {
	
		PreparedStatement preparedStatement = null;
		ArrayList<AppointmentDiaryReport>list = new ArrayList<AppointmentDiaryReport>();
		String sql = "select id,dept from apm_department ";
		
		try{
			
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()){
				AppointmentDiaryReport appointmentDiaryReport = new AppointmentDiaryReport();
				appointmentDiaryReport.setId(rs.getInt(1));
				appointmentDiaryReport.setDept(rs.getString(2));
				list.add(appointmentDiaryReport);
				
			}
			
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		return list;
}


public ArrayList<AppointmentDiaryReport> getAllWalkInPreBookedList() {
	PreparedStatement preparedStatement = null;
	ArrayList<AppointmentDiaryReport>list = new ArrayList<AppointmentDiaryReport>();
	
	String sql = "select id,commencing,starttime,endtime,duration,diaryusername,aptmtype,room,clientname,dept from apm_available_slot" ;
	
	try{
		
		preparedStatement = connection.prepareStatement(sql);
		ResultSet rs = preparedStatement.executeQuery();
		while(rs.next()){
			AppointmentDiaryReport appointmentDiaryReport = new AppointmentDiaryReport();
			appointmentDiaryReport.setId(rs.getInt(1));
			appointmentDiaryReport.setDate(rs.getString(2));
			appointmentDiaryReport.setStartTime(rs.getString(3));
			appointmentDiaryReport.setEndTime(rs.getString(4));
			appointmentDiaryReport.setDuration(rs.getString(5));
			appointmentDiaryReport.setDiaryUser(rs.getString(6));
			appointmentDiaryReport.setAptmtype(rs.getString(7));
			appointmentDiaryReport.setRoom(rs.getString(8));
			appointmentDiaryReport.setClientName(rs.getString(9));
			appointmentDiaryReport.setDept(rs.getString(10));
			
			
			list.add(appointmentDiaryReport);
			
		}
		
		
	}catch (Exception e) {
		e.printStackTrace();
	}
	
	return list;
}


public ArrayList<AppointmentDiaryReport> getAllBookedList(String date,
		String diaryUserId, String dept,boolean arrived, boolean beingSeen, boolean completed, boolean dna) {
	PreparedStatement preparedStatement = null;
	AppointmentDiaryReport appointmentDiaryReport = new AppointmentDiaryReport();
	ArrayList<AppointmentDiaryReport>list = new ArrayList<AppointmentDiaryReport>();
	String sql = "";
	
	boolean a1 = beingSeen;
	
	if(arrived==true && beingSeen==true && completed==true && dna ==true){
		  sql = "select id,commencing,starttime,endtime,duration,diaryusername,aptmtype,room,clientname,dept from apm_available_slot where commencing = '"+date+"' and diaryuserid = "+diaryUserId+" and dept = '"+dept+"' and (arrivedstatus = 1 or arrivedstatus = 2) and dna = 1" ;
	}
	
	else if(arrived == true && beingSeen == true && completed == true && dna == false){
		  sql = "select id,commencing,starttime,endtime,duration,diaryusername,aptmtype,room,clientname,dept from apm_available_slot where commencing = '"+date+"' and diaryuserid = "+diaryUserId+" and dept = '"+dept+"' and (arrivedstatus = 1 or arrivedstatus = 2) and dna = 0" ;
	}
	
	else if(arrived == true && beingSeen == true && completed == false && dna == true){
		 sql = "select id,commencing,starttime,endtime,duration,diaryusername,aptmtype,room,clientname,dept from apm_available_slot where commencing = '"+date+"' and diaryuserid = "+diaryUserId+" and dept = '"+dept+"' and (arrivedstatus = 1 or arrivedstatus = 2) and dna = 1" ;
	}
	else if(arrived == true && beingSeen == true && completed == false && dna == false){
		 sql = "select id,commencing,starttime,endtime,duration,diaryusername,aptmtype,room,clientname,dept from apm_available_slot where commencing = '"+date+"' and diaryuserid = "+diaryUserId+" and dept = '"+dept+"' and (arrivedstatus = 1 or arrivedstatus = 2) and dna = 0" ;
	}
	else if(arrived == true && beingSeen == false && completed == true && dna == true){
		 sql = "select id,commencing,starttime,endtime,duration,diaryusername,aptmtype,room,clientname,dept from apm_available_slot where commencing = '"+date+"' and diaryuserid = "+diaryUserId+" and dept = '"+dept+"' and arrivedstatus = 1  and dna = 1" ;
	}
	else if(arrived == true && beingSeen == false && completed == true && dna == false){
		  sql = "select id,commencing,starttime,endtime,duration,diaryusername,aptmtype,room,clientname,dept from apm_available_slot where commencing = '"+date+"' and diaryuserid = "+diaryUserId+" and dept = '"+dept+"' and arrivedstatus = 1  and dna = 0" ;
	}
	else if(arrived == true && beingSeen == false && completed == false && dna == true){
		 sql = "select id,commencing,starttime,endtime,duration,diaryusername,aptmtype,room,clientname,dept from apm_available_slot where commencing = '"+date+"' and diaryuserid = "+diaryUserId+" and dept = '"+dept+"' and arrivedstatus = 1  and dna = 1" ;
	}
	else if(arrived == true && beingSeen == false && completed == false && dna == false){
		 sql = "select id,commencing,starttime,endtime,duration,diaryusername,aptmtype,room,clientname,dept from apm_available_slot where commencing = '"+date+"' and diaryuserid = "+diaryUserId+" and dept = '"+dept+"' and arrivedstatus = 1  and dna = 0" ;
	}
	else if(arrived == false && beingSeen == true && completed == true && dna == true){
		 sql = "select id,commencing,starttime,endtime,duration,diaryusername,aptmtype,room,clientname,dept from apm_available_slot where commencing = '"+date+"' and diaryuserid = "+diaryUserId+" and dept = '"+dept+"' and arrivedstatus = 2  and dna = 1" ;
	}
	else if(arrived == false && beingSeen == true && completed == true && dna == false){
		 sql = "select id,commencing,starttime,endtime,duration,diaryusername,aptmtype,room,clientname,dept from apm_available_slot where commencing = '"+date+"' and diaryuserid = "+diaryUserId+" and dept = '"+dept+"' and arrivedstatus = 2  and (dna = 0 or dna = 1)" ;
	}
	else if(arrived == false && beingSeen == true && completed == false && dna == true){
		 sql = "select id,commencing,starttime,endtime,duration,diaryusername,aptmtype,room,clientname,dept from apm_available_slot where commencing = '"+date+"' and diaryuserid = "+diaryUserId+" and dept = '"+dept+"' and arrivedstatus = 2  and dna = 1" ;
	}
	else if(arrived == false && beingSeen == true && completed == false && dna == false){
		 sql = "select id,commencing,starttime,endtime,duration,diaryusername,aptmtype,room,clientname,dept from apm_available_slot where commencing = '"+date+"' and diaryuserid = "+diaryUserId+" and dept = '"+dept+"' and arrivedstatus = 2  and (dna = 0 or dna = 1)  " ;
	}
	else if(arrived == false && beingSeen == false && completed == true && dna == true){
		 sql = "select id,commencing,starttime,endtime,duration,diaryusername,aptmtype,room,clientname,dept from apm_available_slot where commencing = '"+date+"' and diaryuserid = "+diaryUserId+" and dept = '"+dept+"' and arrivedstatus = 0  and dna = 1" ;
	}
	else if(arrived == false && beingSeen == false && completed == true && dna == false){
		 sql = "select id,commencing,starttime,endtime,duration,diaryusername,aptmtype,room,clientname,dept from apm_available_slot where commencing = '"+date+"' and diaryuserid = "+diaryUserId+" and dept = '"+dept+"' and arrivedstatus = 0  and (dna = 0 or dna = 1)" ;
	}
	else if(arrived == false && beingSeen == false && completed == false && dna == true){
		 sql = "select id,commencing,starttime,endtime,duration,diaryusername,aptmtype,room,clientname,dept from apm_available_slot where commencing = '"+date+"' and diaryuserid = "+diaryUserId+" and dept = '"+dept+"' and (arrivedstatus = 1 or arrivedstatus = 2)  and dna = 1" ;
	}
	else if(arrived == false && beingSeen == false && completed == false && dna == false){
		 sql = "select id,commencing,starttime,endtime,duration,diaryusername,aptmtype,room,clientname,dept from apm_available_slot where commencing = '"+date+"' and diaryuserid = "+diaryUserId+" and dept = '"+dept+"' and arrivedstatus = 0  and (dna = 0 or dna = 1)" ;
	}
	
	try{
		
		preparedStatement = connection.prepareStatement(sql);
		ResultSet rs = preparedStatement.executeQuery();		
		while(rs.next()){
			 appointmentDiaryReport = new AppointmentDiaryReport();
			appointmentDiaryReport.setId(rs.getInt(1));
			appointmentDiaryReport.setDate(rs.getString(2));
			appointmentDiaryReport.setStartTime(rs.getString(3));
			appointmentDiaryReport.setEndTime(rs.getString(4));
			appointmentDiaryReport.setDuration(rs.getString(5));
			appointmentDiaryReport.setDiaryUser(rs.getString(6));
			appointmentDiaryReport.setAptmtype(rs.getString(7));
			appointmentDiaryReport.setRoom(rs.getString(8));
			appointmentDiaryReport.setClientName(rs.getString(9));
			appointmentDiaryReport.setDept(rs.getString(10));
			
			
			list.add(appointmentDiaryReport);
			
		}
		
		
	}catch (Exception e) {
		e.printStackTrace();
	}
	
	return list;
}


public int getTotalApmtReportCount(String diaryUserId, String fromDate,
		String toDate) {
	PreparedStatement preparedStatement = null;
	int result = 0;
	
	String sql = "select count(*) from apm_available_slot where diaryuserid = "+diaryUserId+" and commencing between '"+fromDate+"' AND '"+toDate+"';" ;

	try{
		preparedStatement = connection.prepareStatement(sql);
		
		ResultSet rs = preparedStatement.executeQuery();
		while(rs.next()){
			result = rs.getInt(1);
		}
		
	}catch (Exception e) {
		e.printStackTrace();
	}
	return result;
}
}