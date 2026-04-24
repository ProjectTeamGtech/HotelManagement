package com.apm.Log.eu.entity;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.print.attribute.standard.MediaSize.Other;

import com.apm.DiaryManagement.eu.entity.NotAvailableSlot;
import com.apm.common.eu.blogic.jdbc.Connection_provider;
import com.apm.main.common.constants.Constants;

public class Demo {
	
	public static void main(String[] args) {
		//System.out.println("hello world");
		Connection connection = null;
		try{
			Class.forName("com.mysql.jdbc.Driver");
			connection=DriverManager.getConnection("jdbc:mysql://localhost:3306/ankerside",""+com.apm.main.common.constants.Constants.DB_USER+"",""+Constants.DB_PWD+"");
			int clientid = 5300;
			ArrayList<NotAvailableSlot>bookedList = getBookedAppointmentList(clientid,connection);
			
			for(NotAvailableSlot availableSlot : bookedList){
//				System.out.println("booked Appointments:");
//				System.out.println(availableSlot.getId());
//				System.out.println(availableSlot.getCommencing());
//				System.out.println(availableSlot.getStatus());
				
				if(availableSlot.getModiAppintmentList().size() > 0){
					for(Modify modify : availableSlot.getModiAppintmentList()){
//						System.out.println("Modify Appointments:");
//						System.out.println(modify.getId());
//						System.out.println(modify.getCommencing());
//						System.out.println(modify.getStatus());
					}
				}else{
//					System.out.println("no modify appointments");
				}
			
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}

	private static ArrayList<NotAvailableSlot> getBookedAppointmentList(
			int clientid,Connection connection) {
		
		ArrayList<NotAvailableSlot>list = new ArrayList<NotAvailableSlot>();
		PreparedStatement preparedStatement = null;
		
		String sql = "SELECT appmt_id,status,commencing FROM apm_appointment_log where status = 'Booked'  ";
		
		try{
			
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			
			while(rs.next()){
				NotAvailableSlot availableSlot = new NotAvailableSlot();
				availableSlot.setId(rs.getInt(1));
				availableSlot.setStatus(rs.getString(2));
				availableSlot.setCommencing(rs.getString(3));
				ArrayList<Modify>modifyList = getModifyAppointmentList(availableSlot.getId(),connection);
				availableSlot.setModiAppintmentList(modifyList);
				
				list.add(availableSlot);
			}
			
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return list;
	}

	private static ArrayList<Modify> getModifyAppointmentList(int id,Connection connection) {
		
		PreparedStatement preparedStatement = null;
		ArrayList<Modify>list = new ArrayList<Modify>();
		String sql = "SELECT appmt_id,status,commencing FROM apm_appointment_log where status = 'Modified'  and appmt_id = "+id+" ";
		
		try{
			
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()){
				Modify modify = new Modify();
				modify.setId(rs.getInt(1));
				modify.setStatus(rs.getString(2));
				modify.setCommencing(rs.getString(3));
				
				list.add(modify);
			}
			
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return list;
	}
}
