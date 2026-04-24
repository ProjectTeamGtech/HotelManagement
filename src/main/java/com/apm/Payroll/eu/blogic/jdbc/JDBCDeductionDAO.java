package com.apm.Payroll.eu.blogic.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.apm.Payroll.eu.bi.DeductionDAO;
import com.apm.Payroll.eu.entity.Payroll;

public class JDBCDeductionDAO implements DeductionDAO {

	Connection connection;
	
	public JDBCDeductionDAO(Connection connection) {
	
	    this.connection=connection;
	}

	public ArrayList<Payroll> getDeductionList() {

		ArrayList<Payroll> list=new ArrayList<Payroll>();
		
		try {
			
			String sql="select id, deduction, deduction_type, emp_contribution, comp_contribution from apm_payroll_deduction";
			PreparedStatement ps=connection.prepareStatement(sql);
			ResultSet rs=ps.executeQuery();
			
			while(rs.next()) {
				
				  Payroll payroll=new Payroll();
				  payroll.setId(rs.getInt(1));
				  payroll.setDeduction(rs.getString(2));
				  payroll.setDeduction_type(rs.getString(3));
				  payroll.setEmp_contribution(rs.getString(4));
				  payroll.setComp_contribution(rs.getString(5));
				  list.add(payroll);
			}
			
			
		} catch (Exception e) {

		  e.printStackTrace();
		}
		
		return list;
	}

	public int addDeduction(Payroll payroll) {

		int res=0;
		
		try {
			
			String sql="insert into apm_payroll_deduction (deduction, deduction_type, emp_contribution, comp_contribution) values (?,?,?,?)";
			PreparedStatement ps=connection.prepareStatement(sql);
			ps.setString(1,payroll.getDeduction());
			ps.setString(2,payroll.getDeduction_type());
			ps.setString(3,payroll.getEmp_contribution());
			ps.setString(4,payroll.getComp_contribution());
			
			res=ps.executeUpdate();
			
		} catch (Exception e) {

		   e.printStackTrace();
		}
		
		
		return res;
	}

	public Payroll getDeduction(String id) {

		Payroll payroll=new Payroll();
		
		try {
      
		    String sql="select deduction, deduction_type, emp_contribution, comp_contribution from apm_payroll_deduction where id="+id+"";	 
			PreparedStatement ps=connection.prepareStatement(sql);
			ResultSet rs=ps.executeQuery();
			while(rs.next()) {
				
			     payroll.setId(Integer.parseInt(id));
			     payroll.setDeduction(rs.getString(1));
			     payroll.setDeduction_type(rs.getString(2));
				 payroll.setEmp_contribution(rs.getString(3));
				 
			}
			
		} catch (Exception e) {

		   e.printStackTrace();
		}
		
		
		return payroll;
	}

	public int updateDeduction(Payroll payroll) {

		int result=0;
		
		try {
		
			 String sql="update apm_payroll_deduction set deduction=?, deduction_type=?, emp_contribution=?, comp_contribution=? where id="+payroll.getId()+"";
			 PreparedStatement ps=connection.prepareStatement(sql);
			 ps.setString(1,payroll.getDeduction());
			 ps.setString(2,payroll.getDeduction_type());
		 	 ps.setString(3,payroll.getEmp_contribution());
			 ps.setString(4,payroll.getComp_contribution());
			
			 result=ps.executeUpdate();
		} catch (Exception e) {

		   e.printStackTrace();
		}
		
		return result;
	}
	
}
