package com.apm.Payroll.eu.blogic.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.apm.Payroll.eu.bi.PayrollTaxDAO;
import com.apm.Payroll.eu.entity.Payroll;

public class JDBCPayrollTaxDAO implements PayrollTaxDAO{

	Connection connection;
	
	public JDBCPayrollTaxDAO(Connection connection) {
	
	    this.connection=connection;
	}

	public ArrayList<Payroll> getAllTaxlist() {

		ArrayList<Payroll> list=new ArrayList<Payroll>();
	
		try {
			
			String sql="select id, fromamount, toamount, gender, tax from apm_payroll_tax";
			PreparedStatement ps=connection.prepareStatement(sql);
			ResultSet rs=ps.executeQuery();
			
			while(rs.next()) {
				Payroll payroll=new Payroll();
				payroll.setId(rs.getInt(1));
				payroll.setFromamount(rs.getString(2));
				payroll.setToamount(rs.getString(3));
				payroll.setGender(rs.getString(4));
				payroll.setTax(rs.getString(5));
				list.add(payroll);
			
			}
			
		} catch (Exception e) {

		   e.printStackTrace();
		}
		
		return list;
	}

	public int addTax(Payroll payroll) {

		int result=0;
		
		try {
			
			String sql="insert into apm_payroll_tax (fromamount, toamount, gender, tax) values (?,?,?,?)";
			PreparedStatement ps=connection.prepareStatement(sql);
			ps.setString(1, payroll.getFromamount());
			ps.setString(2, payroll.getToamount());
			ps.setString(3, payroll.getGender());
			ps.setString(4, payroll.getTax());
			
			result=ps.executeUpdate();
			
		} catch (Exception e) {

		   e.printStackTrace();
		}
	
		return result;
	}

	public Payroll getTax(String id) {

		Payroll payroll=new Payroll();
		
		try {
			
		     String sql="select fromamount, toamount, gender, tax from apm_payroll_tax where id="+id+"";
		     PreparedStatement ps=connection.prepareStatement(sql);
		     
		     ResultSet rs=ps.executeQuery();
		     while(rs.next()) {
		    	 
		    	  payroll.setFromamount(rs.getString(1));
		    	  payroll.setToamount(rs.getString(2));
		    	  payroll.setGender(rs.getString(3));
		    	  payroll.setTax(rs.getString(4));
		    	  payroll.setId(Integer.parseInt(id));
		     }
		     
		     
		} catch (Exception e) {

		   e.printStackTrace();
		}
		
		return payroll;
	}

	public int updateTax(Payroll payroll) {

		
		 int result=0;
		try {
			
			String sql="update apm_payroll_tax set fromamount=?, toamount=?, gender=?, tax=? where id="+payroll.getId()+"";
		    PreparedStatement ps=connection.prepareStatement(sql);
		    ps.setString(1, payroll.getFromamount());
		    ps.setString(2, payroll.getToamount());
		    ps.setString(3, payroll.getGender());
		    ps.setString(4, payroll.getTax());
		    
		    result=ps.executeUpdate();
		    
		} catch (Exception e) {

		   e.printStackTrace();
		}
				
		return result;
	}
	
	
	
	
	
	
}
