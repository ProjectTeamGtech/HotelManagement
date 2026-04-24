package com.apm.Master.eu.blogic.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import com.apm.Master.eu.bi.StateDAO;
import com.apm.Master.eu.entity.State;
import com.apm.common.utils.Pagination;
import com.apm.common.web.action.BaseAction;

public class JDBCStateDAO extends BaseAction implements StateDAO {
	Connection connection;
	public JDBCStateDAO(Connection connection) {
		this.connection= connection;
	}
	
	public int getTotalStateCount(){
		
		int result=0;
		try {
     
			String sql="select count(*) from apm_state";
			PreparedStatement stmt=connection.prepareStatement(sql);
			ResultSet rs=stmt.executeQuery();
			while(rs.next())
			{
				result=rs.getInt(1);
				
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
	public ArrayList<State> getallState(String searchText,Pagination pagination) {
		ArrayList<State> users = new ArrayList<State>();
		try {
			Statement stmt = connection.createStatement();
			String sql ="";
			if (searchText!=null) {
				sql = "select id,name from apm_state where name like ('"+searchText+"%')";
			} else {
				sql = "select id,name from apm_state";
			}
			if(pagination!=null)
			{
				sql=pagination.getSQLQuery(sql);
			}
			
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				State state = new State();
				state.setId(rs.getInt(1));
				state.setName(rs.getString(2));
				users.add(state);
			}
			stmt.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return users;
	}
	
	
	
	
	public int addstateDB(State state) {
		int result = 0;
		try {
			String query = "insert into apm_state(name) values(?)";
			PreparedStatement stmt = connection.prepareStatement(query); 
			stmt.setString(1, state.getName());
			result = stmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	public int deleteStateDB(State state) {
		int result = 0;
		try {
			
			int j =state.getId();
			String query = "delete from apm_state where id="+state.getId();
			PreparedStatement stmt = connection.prepareStatement(query);
			result = stmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public State getstateinfo(State state) {
		State state2 =null;
		try {
			String query= "select id,name from apm_state where id="+state.getId();
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			ResultSet rs = preparedStatement.executeQuery();
			if(rs.next()){
				state2 = new State();
				state2.setId(rs.getInt(1));
				state2.setName(rs.getString(2));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return state2;
	}


	public int updateStateDB(State state) {
		int result = 0;
		try {
			String query = "update apm_state set name=? where id="+state.getId();
			PreparedStatement stmt = connection.prepareStatement(query); 
			stmt.setString(1, state.getName());
			result = stmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public ArrayList<State> getallState(String searchText) {
		// TODO Auto-generated method stub
		return null;
	}
}
