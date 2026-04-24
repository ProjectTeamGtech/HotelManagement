package com.apm.Log.eu.blogic.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.TimeZone;

import javax.swing.text.html.HTMLDocument.HTMLReader.PreAction;

import com.apm.Log.eu.bi.EmailLetterLogDAO;
import com.apm.Log.eu.entity.EmailLetterLog;
import com.apm.Log.eu.entity.LogDetail;
import com.apm.common.eu.blogic.jdbc.JDBCBaseDAO;
import com.apm.common.utils.DateTimeUtils;


public class JDBCEmailLetterLogDAO extends JDBCBaseDAO implements EmailLetterLogDAO{
	
	public JDBCEmailLetterLogDAO(Connection connection){
		this.connection = connection;
		
	}

	public int saveEmailLetterLogDetails(String sender, String receiver,String heading, EmailLetterLog emailLetterLog,String status,String procurementid) {
		PreparedStatement preparedStatement = null;
		int result = 0;
		if(procurementid==null){
			procurementid="0";
		}
		String sql = "insert into apm_email_letter_log (datetime,type,heading,sender,receiver,invoiceid,appointmentid,status,clientId,po_id) values(?,?,?,?,?,?,?,?,?,?)";
		
		/*SimpleDateFormat sdf = new SimpleDateFormat("d-MM-yyyy hh:mm:ss");
		Calendar now = Calendar.getInstance();
		now.add(Calendar.HOUR, 5);
		now.add(Calendar.MINUTE, 30);
		String date = sdf.format(now.getTime());
		System.out.println(date);*/
		
		
	
		
		try{
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, emailLetterLog.getLastModified());
			preparedStatement.setString(2, emailLetterLog.getType());
			preparedStatement.setString(3, heading);
			preparedStatement.setString(4, sender);
			preparedStatement.setString(5, receiver);
			preparedStatement.setInt(6, emailLetterLog.getInvoiceid());
			preparedStatement.setInt(7, emailLetterLog.getAppointmentid());
			preparedStatement.setString(8, status);
			preparedStatement.setString(9, emailLetterLog.getClientId());
			preparedStatement.setString(10, procurementid);
			result = preparedStatement.executeUpdate();
			
			
		}catch(Exception e){
			e.printStackTrace();
		}
		return result;
	}

	public ArrayList<EmailLetterLog> getEmailHistoryList(String clientid,String type) {
		PreparedStatement preparedStatement = null;		
		ArrayList<EmailLetterLog>list = new ArrayList<EmailLetterLog>();
		String sql = "SELECT id,datetime,type,heading,sender,receiver,status FROM apm_email_letter_log where clientid = "+clientid+" and type = 'Email' order by id desc ";
		/*if(!client.equals("")){
		  sql = "select id,datetime,type,heading,sender,receiver,invoiceid,appointmentid,status,clientId from apm_email_letter_log where type = 'Email' and clientId = "+client+"";
		}else{
			  sql = "select id,datetime,type,heading,sender,receiver,invoiceid,appointmentid,status,clientId from apm_email_letter_log where type = 'Email'";
		}*/
		 
		try{
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()){
				EmailLetterLog emailLetterLog = new EmailLetterLog();
				emailLetterLog.setId(rs.getInt(1));
				emailLetterLog.setDatetime(rs.getString(2));
				emailLetterLog.setType(rs.getString(3));
				emailLetterLog.setHeading(rs.getString(4));
				emailLetterLog.setSender(rs.getString(5));
				emailLetterLog.setReceiver(rs.getString(6));
				/*emailLetterLog.setInvoiceid(rs.getInt(7));*/
				/*emailLetterLog.setAppointmentid(rs.getInt(8));*/
				emailLetterLog.setStatus(rs.getString(7));
				/*emailLetterLog.setClientId(rs.getString(10));*/
				list.add(emailLetterLog);
			}
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	public String getclientIdByInvoiceId(int invoiceid) {
		PreparedStatement preparedStatement = null;
		String result = "";
		String sql = "select clientid from apm_charges_invoice where id = "+invoiceid+"";
		try{
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()){
				result = rs.getString(1);
			}
			
		}catch(Exception e){
			
		}
		return result;
	}

	public String getClientIdByAptmentId(int appointmentid) {
		PreparedStatement preparedStatement = null;
		String result = "";
		String sql = "select clientId from apm_available_slot where id = "+appointmentid+"";
		try{
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()){
				result = rs.getString(1);
			}
			
		}catch(Exception e){
			
		}
		return result;
	}

	public ArrayList<EmailLetterLog> getLetterHistoryList(String clientid) {

		PreparedStatement preparedStatement = null;		
		ArrayList<EmailLetterLog>list = new ArrayList<EmailLetterLog>();
		String sql = "SELECT id,datetime,type,heading,sender,receiver,status FROM apm_email_letter_log where clientid = "+clientid+" and type = 'Letter' order by id desc ";
		/*if(!client.equals("")){
			sql = "select id,datetime,type,heading,sender,receiver,invoiceid,appointmentid,status,clientId from apm_email_letter_log where type = 'Letter' and clientId = "+client+"";
		}else{
			sql = "select id,datetime,type,heading,sender,receiver,invoiceid,appointmentid,status,clientId from apm_email_letter_log where type = 'Letter'";
		}*/
		
		try{
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()){
				EmailLetterLog emailLetterLog = new EmailLetterLog();
				emailLetterLog.setId(rs.getInt(1));
				emailLetterLog.setDatetime(rs.getString(2));
				emailLetterLog.setType(rs.getString(3));
				emailLetterLog.setHeading(rs.getString(4));
				emailLetterLog.setSender(rs.getString(5));
				emailLetterLog.setReceiver(rs.getString(6));
			/*	emailLetterLog.setInvoiceid(rs.getInt(7));*/
			/*	emailLetterLog.setAppointmentid(rs.getInt(8));*/
				emailLetterLog.setStatus(rs.getString(7));
				/*emailLetterLog.setClientId(rs.getString(10));*/
				list.add(emailLetterLog);
			}
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	public int updateAdEmailStatus(EmailLetterLog emailLetterLog) {
		PreparedStatement preparedStatement = null;
		int result = 0;
		String sql = "update apm_available_slot set ad=? where id=? ";
		
		try{
			
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, 1);
			preparedStatement.setInt(2, emailLetterLog.getAppointmentid());
			
			result = preparedStatement.executeUpdate();
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return result;
	}

	
	

}
