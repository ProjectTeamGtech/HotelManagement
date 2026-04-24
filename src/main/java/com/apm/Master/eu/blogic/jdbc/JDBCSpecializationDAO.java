package com.apm.Master.eu.blogic.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.apm.Master.eu.bi.SpecializationDAO;
import com.apm.Master.eu.entity.Master;
import com.apm.common.eu.blogic.jdbc.JDBCBaseDAO;
import com.apm.common.utils.DateTimeUtils;
import com.apm.common.utils.Pagination;
import com.opensymphony.xwork2.Preparable;

public class JDBCSpecializationDAO extends JDBCBaseDAO implements SpecializationDAO{
	
	public JDBCSpecializationDAO(Connection connection){
		this.connection = connection;
	}

	public int getTotalSpecializationCount() {
		PreparedStatement preparedStatement = null;
		int result = 0;
		String sql = "select count(*) from apm_specialization";
		try{
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()){
				result = rs.getInt(1);
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
		return result;
	}

	public ArrayList<Master> getSpecializationList(Pagination pagination) {
		PreparedStatement preparedStatement = null;
		ArrayList<Master> list = new ArrayList<Master>();
		String sql = "";
		if(pagination.sortColumn==null){
		 sql = "select id,specialization_text,description,datetime from apm_specialization order by id desc";
		}
		else{
			 sql = "select id,specialization_text,description,datetime from apm_specialization";

		}
		try{
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()){
				Master master = new Master();
				master.setId(rs.getInt(1));
				master.setSpecialization(rs.getString(2));
				master.setDescription(rs.getString(3));
				master.setDatetime(rs.getString(4));
				list.add(master);
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
		return list;
	}

	public int saveSpecialization(Master master) {
		PreparedStatement preparedStatement = null;
		int result = 0;
		String sql = "insert into apm_specialization(specialization_text,description,datetime) values(?,?,?)";
		try{
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, master.getSpecialization());
			preparedStatement.setString(2, master.getDescription());
			preparedStatement.setString(3, DateTimeUtils.getCurrentDateInDDMMYYYYCasting());
			
			result = preparedStatement.executeUpdate();
			
		}catch(Exception e){
			e.printStackTrace();
		}
		return result;
	}

	public Master getSpecializationData(int id, Master master) {
		PreparedStatement preparedStatement = null;
		String sql = "select id,specialization_text,description,datetime from apm_specialization where id = "+id+"";
		try{
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()){
				master = new Master();
				master.setId(rs.getInt(1));
				master.setSpecialization(rs.getString(2));
				master.setDescription(rs.getString(3));
				master.setDatetime(rs.getString(4));
			}
			
			
		}catch(Exception e){
			e.printStackTrace();
		}
		return master;
	}

	public int updateSpecialization(int id, Master master) {
		PreparedStatement preparedStatement = null;
		int result = 0;
		String sql = "update apm_specialization set specialization_text = ?,description = ? where id = "+id+" ";
		try{
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, master.getSpecialization());
			preparedStatement.setString(2, master.getDescription());
			//preparedStatement.setString(3, master.getDatetime());
			
			result = preparedStatement.executeUpdate();
			
		}catch(Exception e){
			e.printStackTrace();
		}
		return result;
	}

	public int deleteSpecialization(int id) {
		PreparedStatement preparedStatement = null;
		int result = 0;
		String sql = "delete from apm_specialization where id = "+id+" ";
		try{
			preparedStatement = connection.prepareStatement(sql);
			result = preparedStatement.executeUpdate();
			
		}catch(Exception e){
			e.printStackTrace();
		}
		return result;
	}
	
	
	public ArrayList<Master> getMasterList() {
		// TODO Auto-generated method stub
		ArrayList<Master> masters=new ArrayList<Master>();
		
		try {
			
			  PreparedStatement ps=connection.prepareStatement("select * from apm_select_master order by mastername");
			  ResultSet rs=ps.executeQuery();
			  while(rs.next())
			  {    
				    Master master=new Master();
				    int id=rs.getInt(1);
				    String mastername=rs.getString(2);
				    master.setId(id);
				    master.setName(mastername);
				    masters.add(master);
			  }
            			
		} catch (Exception e) {

		   e.printStackTrace();
		}
			
		return masters;
	}

}
