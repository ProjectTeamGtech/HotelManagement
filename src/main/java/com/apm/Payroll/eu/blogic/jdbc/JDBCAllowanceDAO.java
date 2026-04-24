package com.apm.Payroll.eu.blogic.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.apm.Payroll.eu.bi.AllowanceDAO;
import com.apm.Payroll.eu.entity.Allowance;

public class JDBCAllowanceDAO implements AllowanceDAO {

	Connection connection;
	
	public JDBCAllowanceDAO(Connection connection) {

		 this.connection=connection;	
	}

	public ArrayList<Allowance> getAllAllowancesList() {

		ArrayList<Allowance> list=new ArrayList<Allowance>();
		
		try {
			
			String sql="select id, name, allowance_type, value from apm_payroll_allowance";
			PreparedStatement ps=connection.prepareStatement(sql);
			ResultSet rs=ps.executeQuery();
			
			while(rs.next()) {
				
				 Allowance allowance=new Allowance();
				 allowance.setId(rs.getInt(1));
				 allowance.setAllowance_name(rs.getString(2));
				 allowance.setAllowanceType(rs.getString(3));
				 allowance.setValue(rs.getString(4));
				 list.add(allowance);
				
			}
		
		} catch (Exception e) {

		   e.printStackTrace();
		}
		
	
		return list;
	}

	public int addAllowance(Allowance allowance) {

		int result=0;
		
		try {
			  String sql="insert into apm_payroll_allowance (name,allowance_type,value) values (?,?,?)";
			  PreparedStatement ps=connection.prepareStatement(sql);
              ps.setString(1,allowance.getAllowance_name());
              ps.setString(2,allowance.getAllowanceType());
              ps.setString(3,allowance.getValue());   
		      
              result=ps.executeUpdate();
		} catch (Exception e) {

		   e.printStackTrace();
		}
		
		return result;
	}

	public Allowance getAllowance(String id) {

		Allowance allowance=new Allowance();
		
		try {
			
			String sql="select name,allowance_type,value from apm_payroll_allowance where id="+id+"";
			PreparedStatement ps=connection.prepareStatement(sql);
			ResultSet rs=ps.executeQuery();
			while(rs.next()) {
				
				allowance.setId(Integer.parseInt(id));
				allowance.setAllowance_name(rs.getString(1));
				allowance.setAllowanceType(rs.getString(2));
				allowance.setValue(rs.getString(3));
			}
			
		} catch (Exception e) {

		   e.printStackTrace();
		}
		
		return allowance;
	}

	public int updateAllowance(Allowance allowance) {

		int res=0;
		
		try {
			
			String sql="update apm_payroll_allowance set name=?, allowance_type=?, value=? where id="+allowance.getId()+"";
			PreparedStatement ps=connection.prepareStatement(sql);
			ps.setString(1, allowance.getAllowance_name());
			ps.setString(2, allowance.getAllowanceType());
			ps.setString(3, allowance.getValue());
			
			res=ps.executeUpdate();
			
		} catch (Exception e) {

		   e.printStackTrace();
		}
		
		return res;
	}
	
	
	
	
	
	
}
