package com.apm.Payroll.eu.blogic.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.apm.Payroll.eu.bi.BranchDAO;
import com.apm.Payroll.eu.entity.Payroll;

public class JDBCBranchDAO implements BranchDAO {

	Connection connection;
	
	public JDBCBranchDAO(Connection connection) {
   
	    this.connection=connection;
	}
	

	public int addBranch(Payroll payroll) {

		int result=0;
	    try {
	    	
	      String sql="insert into apm_payroll_branch (name,address,city,state,country,phone1,phone2,email) values (?,?,?,?,?,?,?,?)";	
	      PreparedStatement ps=connection.prepareStatement(sql);       	
	      ps.setString(1, payroll.getBranch());
	      ps.setString(2, payroll.getAddress());
	      ps.setString(3, payroll.getCity());
		  ps.setString(4, payroll.getState());	
	      ps.setString(5, payroll.getCountry());
	      ps.setString(6, payroll.getPhone1());
	      ps.setString(7, payroll.getPhone2());
	      ps.setString(8, payroll.getEmail());
	      
	     result= ps.executeUpdate();
	      
		} catch (Exception e) {

		   e.printStackTrace();
		}
	   
	  return result;
	}


	public int updateBranch(Payroll payroll) {

		int result=0;
		
		try {
			String sql="update apm_payroll_branch set name=?,address=?,city=?,state=?,country=?,phone1=?,phone2=?,email=? where id="+payroll.getId()+"";
			PreparedStatement ps=connection.prepareStatement(sql);
            ps.setString(1, payroll.getBranch());
            ps.setString(2, payroll.getAddress());
            ps.setString(3, payroll.getCity());
            ps.setString(4, payroll.getState());
            ps.setString(5, payroll.getCountry());
            ps.setString(6, payroll.getPhone1());
            ps.setString(7, payroll.getPhone2());
            ps.setString(8, payroll.getEmail());
			
			result=ps.executeUpdate();
			
		} catch (Exception e) {

		   e.printStackTrace();
		    
		}
	
		return result;
	}


	public ArrayList<Payroll> getAllBranchList() {

		ArrayList<Payroll> list=new ArrayList<Payroll>();
		
		try {
	         String sql="select id,name,address,city,state,country,phone1,phone2,email from apm_payroll_branch";
	         PreparedStatement ps=connection.prepareStatement(sql);
	         ResultSet rs=ps.executeQuery();
	         while(rs.next()) {
	        	 
	        	  Payroll payroll=new Payroll();
	        	  payroll.setId(rs.getInt(1));
	        	  payroll.setBranch(rs.getString(2));
	        	  payroll.setAddress(rs.getString(3));
	        	  payroll.setCity(rs.getString(4));
	        	  payroll.setState(rs.getString(5));
	        	  payroll.setCountry(rs.getString(6));
	        	  payroll.setPhone1(rs.getString(7));
	        	  payroll.setPhone2(rs.getString(8));
	        	  payroll.setEmail(rs.getString(9));
	        	  list.add(payroll);
	        	  
	         }
	        	
		} catch (Exception e) {

		   e.printStackTrace();
		}
	
		return list;
	}


	public Payroll getBranch(String id) {

		Payroll payroll=new Payroll();
		
		
		try {
			
			String sql="select name,address,city,state,country,phone1,phone2,email from apm_payroll_branch where id="+id+"";
			PreparedStatement ps=connection.prepareStatement(sql);
			ResultSet rs=ps.executeQuery();
			while(rs.next()) {
				
				payroll.setBranch(rs.getString(1));
				payroll.setAddress(rs.getString(2));
				payroll.setCity(rs.getString(3));
				payroll.setState(rs.getString(4));
				payroll.setCountry(rs.getString(5));
				payroll.setPhone1(rs.getString(6));
				payroll.setPhone2(rs.getString(7));
				payroll.setEmail(rs.getString(8));
				payroll.setId(Integer.parseInt(id));
				
			}
			
		} catch (Exception e) {

		  e.printStackTrace();
		}
		
		return payroll;
	}
	
	
  
	
	
}
