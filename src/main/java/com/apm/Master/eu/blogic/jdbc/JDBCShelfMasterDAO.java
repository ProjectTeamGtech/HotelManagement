package com.apm.Master.eu.blogic.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import com.apm.Master.eu.bi.MasterDAO;
import com.apm.Master.eu.bi.ShelfMasterDAO;
import com.apm.Master.eu.entity.Master;
import com.apm.Master.eu.entity.ShelfMaster;
import com.apm.Master.eu.entity.State;
import com.apm.common.utils.DateTimeUtils;

public class JDBCShelfMasterDAO implements ShelfMasterDAO{
	Connection connection;
	public JDBCShelfMasterDAO(Connection connection) {
		this.connection = connection;
	}
	
	public ArrayList<ShelfMaster> getallShelf() {
		MasterDAO masterDAO =  new JDBCMasterDAO(connection);
		ArrayList<ShelfMaster> users = new ArrayList<ShelfMaster>();
		try {
			Statement stmt = connection.createStatement();
			String sql = "select id,name,departmentid from apm_medicine_cell where departmentid is not null";
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				ShelfMaster shelfMaster = new ShelfMaster();
				shelfMaster.setId(rs.getInt(1));
				shelfMaster.setName(rs.getString(2));
				Master master = new Master();
				master.setId(rs.getInt(3));
				Master master2 = masterDAO.getlocationinfo(master);
				String departname ="";
				if(master2.getName()!=null){
					departname = master2.getName();
				}
				shelfMaster.setDepartmentname(departname);
				users.add(shelfMaster);
			}
			stmt.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return users;
	}

	public int addshelfDB(ShelfMaster shelfMaster) {
		int result = 0;
		try {
			String query = "insert into apm_medicine_cell (name,departmentid) values(?,?)";
			PreparedStatement stmt = connection.prepareStatement(query); 
			stmt.setString(1, shelfMaster.getName());
			stmt.setString(2, shelfMaster.getDepartmentid());
			result = stmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	public int deleteshelfDB(ShelfMaster shelfMaster) {
		int result = 0;
		try {
			
			int j =shelfMaster.getId();
			String query = "delete from apm_medicine_cell where id="+shelfMaster.getId();
			PreparedStatement stmt = connection.prepareStatement(query);
			result = stmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public ShelfMaster getshelfinfo(ShelfMaster shelfMaster) {
		ShelfMaster shelfMaster2 = new ShelfMaster();
		try {
			String query= "select id,name,departmentid from apm_medicine_cell where id='"+shelfMaster.getId()+"'";
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			ResultSet rs = preparedStatement.executeQuery();
			if(rs.next()){
				
				shelfMaster2.setId(rs.getInt(1));
				shelfMaster2.setName(rs.getString(2));
				shelfMaster2.setDepartmentid(rs.getString(3));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return shelfMaster2;
	}


	public int updateshelfDB(ShelfMaster shelfMaster) {
		int result = 0;
		try {
			String query = "update apm_medicine_cell set name=?,departmentid=? where id="+shelfMaster.getId();
			PreparedStatement stmt = connection.prepareStatement(query); 
			stmt.setString(1, shelfMaster.getName());
			stmt.setString(2, shelfMaster.getDepartmentid());
			result = stmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public int checkAlreadyShelfPresent(String name, String departmentid, String id, String val) {
		int res =0;
		try {
			String sql ="select * from apm_medicine_cell where name=? and departmentid=? ";
			if(DateTimeUtils.convertToInteger(val)>0){
				sql = sql +" "+ "and id!='"+id+"' ";
			}
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, name);
			preparedStatement.setString(2, departmentid);
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()){
				res=1;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return res;
	}

}
