package com.apm.Master.eu.blogic.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import com.apm.Master.eu.bi.CityMasterDAO;
import com.apm.Master.eu.bi.StateDAO;
import com.apm.Master.eu.entity.CityMaster;
import com.apm.Master.eu.entity.State;
import com.apm.common.utils.Pagination;
import com.apm.common.web.action.BaseAction;


public class JDBCCityMasterDAO extends BaseAction implements CityMasterDAO{
	Connection connection;
	public JDBCCityMasterDAO(Connection connection) {
	this.connection = connection;
	}
	
	
	public int getTotalCityCount() {

		int result=0;
		
		 try {
			
			 String sql="select count(*) from apm_city";
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
		
	
	
	public ArrayList<CityMaster> getallCity(Pagination pagination,String SearchText) {
		ArrayList<CityMaster> users = new ArrayList<CityMaster>();
		try {
			Statement stmt = connection.createStatement();
			
			String sql="";
			if(SearchText !=null)
			{
				sql="select id,stateid,city from apm_city where city like ('"+SearchText+"%')";
			}
			else
			{
				sql = "select id,stateid,city from apm_city";
			}
			
			if(pagination!=null) 
			{
				  sql=pagination.getSQLQuery(sql);
		    }
			
			PreparedStatement ps=connection.prepareStatement(sql);
			ResultSet rs=ps.executeQuery();
		
			//ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				CityMaster city = new CityMaster();
				city.setId(rs.getInt(1));
				city.setStateid(rs.getString(2));
				city.setCity(rs.getString(3));
				
				StateDAO stateDAO = new JDBCStateDAO(connection);
				State state1 = new State();
				state1.setId(Integer.parseInt(rs.getString(2)));
				State state = stateDAO.getstateinfo(state1);
				city.setStatename(state.getName());
				
				users.add(city);
			}
			stmt.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return users;
	}
	
	public int addcityDB(CityMaster cityMaster) {
		int result = 0;
		try {
			String query = "insert into apm_city(stateid,city) values(?,?)";
			PreparedStatement stmt = connection.prepareStatement(query); 
			stmt.setString(1, cityMaster.getStateid());
			stmt.setString(2, cityMaster.getCity());
			result = stmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public int deletecityDB(CityMaster cityMaster) {
		int result = 0;
		try {
			
			int j =cityMaster.getId();
			String query = "delete from apm_city where id="+cityMaster.getId();
			PreparedStatement stmt = connection.prepareStatement(query);
			result = stmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public CityMaster getcitydetails(CityMaster cityMaster) {
		CityMaster cityMaster2 =null;
		try {
			String query= "select id,stateid,city from apm_city where id="+cityMaster.getId();
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			ResultSet rs = preparedStatement.executeQuery();
			if(rs.next()){
				cityMaster2 = new CityMaster();
				cityMaster2.setId(rs.getInt(1));
				cityMaster2.setStateid(rs.getString(2));
				cityMaster2.setCity(rs.getString(3));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return cityMaster2;
	}

	public int updatecityDB(CityMaster cityMaster) {
		int result = 0;
		try {
			String query = "update apm_city set stateid=?, city=? where id="+cityMaster.getId();
			PreparedStatement stmt = connection.prepareStatement(query); 
			stmt.setString(1, cityMaster.getStateid());
			stmt.setString(2, cityMaster.getCity());
			result = stmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}


	public ArrayList<CityMaster> getallCity() {
		// TODO Auto-generated method stub
		return null;
	}


	

	




	
	
	
	
}
