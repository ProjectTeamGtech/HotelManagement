package com.apm.Payroll.eu.blogic.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.apm.Payroll.eu.bi.BankDAO;
import com.apm.Payroll.eu.bi.PayrollEmployeeDAO;
import com.apm.Payroll.eu.entity.Employee;
import com.apm.Payroll.eu.entity.Payroll;

public class JDBCBankDAO implements BankDAO  {

	  Connection connection;
	  
	  public JDBCBankDAO(Connection connection) {
           this.connection=connection;
           
	  }

	public ArrayList<Payroll> getAllBankList() {

		ArrayList<Payroll> list=new ArrayList<Payroll>();
		PayrollEmployeeDAO payrollEmployeeDAO=new JDBCPayrollEmployeeDAO(connection);
		
		try {
			
			String sql="select id,bank_name, account_no, ifsc, emp_id,bankaddress from apm_payroll_bank";
			PreparedStatement ps=connection.prepareStatement(sql);
			ResultSet rs=ps.executeQuery();
			while(rs.next()){
				
				  Payroll payroll=new Payroll();
				  payroll.setId(rs.getInt(1));
				  payroll.setBank(rs.getString(2));
				  payroll.setBank_account(rs.getString(3));
				  payroll.setIfsccode(rs.getString(4));
				  payroll.setEmp_id(rs.getString(5));
		          payroll.setBankaddress(rs.getString(6));  
			      Employee employee=payrollEmployeeDAO.getEmployee(payroll.getEmp_id());	  
				  payroll.setName(employee.getName());
			      
				  //hiii
				  
				  list.add(payroll);
			}
			
		} catch (Exception e) {

		  e.printStackTrace();
		}
		
		return list;
	}

	public int addBank(Payroll payroll) {

		int result=0;
		
		try {
			String sql="insert into apm_payroll_bank (bank_name, account_no, ifsc, emp_id,bankaddress) values (?,?,?,?,?)";
			PreparedStatement ps=connection.prepareStatement(sql);
			ps.setString(1, payroll.getBank());
			ps.setString(2, payroll.getBank_account());
			ps.setString(3, payroll.getIfsccode());
			ps.setString(4, payroll.getEmp_id());
			ps.setString(5, payroll.getBankaddress());
			result=ps.executeUpdate();
			
		} catch (Exception e) {

		   e.printStackTrace();
		}
		
		
		return result;
	}

	public Payroll getBankData(String id) {

		Payroll payroll=new Payroll();
		
		try {
			
			String sql="select bank_name, account_no, ifsc, emp_id,bankaddress from apm_payroll_bank where id="+id+"";
			PreparedStatement ps=connection.prepareStatement(sql);
			ResultSet rs=ps.executeQuery();
			while(rs.next()){
				
				  payroll.setBank(rs.getString(1));
				  payroll.setBank_account(rs.getString(2));
				  payroll.setIfsccode(rs.getString(3));
				  payroll.setEmp_id(rs.getString(4));
				  payroll.setId(Integer.parseInt(id));
				  payroll.setBankaddress(rs.getString(5));
			}
			
			
		} catch (Exception e) {

		   e.printStackTrace();
		}
		return payroll;
	}

	public int updateBank(Payroll payroll) {

		int result=0;
		
		try {
			
			String sql="update apm_payroll_bank set bank_name=?, account_no=?, ifsc=?,emp_id=?,bankaddress=? where id="+payroll.getId()+"";
			PreparedStatement ps=connection.prepareStatement(sql);
			ps.setString(1, payroll.getBank());
			ps.setString(2, payroll.getBank_account());
			ps.setString(3, payroll.getIfsccode());
			ps.setString(4, payroll.getEmp_id());
			ps.setString(5, payroll.getBankaddress());
		   result=ps.executeUpdate();	
		} catch (Exception e) {

		  e.printStackTrace();
		}
		
		return result;
	}
	  
	  
	  
	  
	  
	  
	
}
