package com.apm.Payroll.eu.blogic.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.apm.Payroll.eu.bi.PayrollDepartmentDAO;
import com.apm.Payroll.eu.entity.Payroll;
import com.apm.common.utils.DateTimeUtils;
import com.opensymphony.xwork2.Preparable;

public class JDBCPayrollDepartment implements PayrollDepartmentDAO{

	Connection connection;
	
	public JDBCPayrollDepartment(Connection connection) {
	
	    this.connection=connection;
	}

	public ArrayList<Payroll> getAllDepartmentList() {

		ArrayList<Payroll> list=new ArrayList<Payroll>();
		
		
		try {
			String sql="select id,dept_name,overtime_status,rate,email from apm_payroll_department";
            PreparedStatement ps=connection.prepareStatement(sql);
            ResultSet rs=ps.executeQuery();
            while(rs.next()) {
            	
            	Payroll payroll=new Payroll();
            	payroll.setId(rs.getInt(1));
            	payroll.setDept_name(rs.getString(2));
            	payroll.setOvertime_status(rs.getString(3));
            	payroll.setRate(rs.getString(4));
            	payroll.setEmail(rs.getString(5));
            	list.add(payroll);
            	
            }
            
			
		} catch (Exception e) {

		   e.printStackTrace();
		}

		
		return list;
	}

	public int addDepartment(Payroll payroll) {

		int result=0;
		
		try {
		
			String sql="insert into apm_payroll_department(dept_name,overtime_status,rate,email) values (?,?,?,?)";
			PreparedStatement ps=connection.prepareStatement(sql);
			ps.setString(1, payroll.getDept_name());
			ps.setString(2, payroll.getOvertime_status());  
			ps.setString(3, payroll.getRate());
			ps.setString(4, payroll.getEmail());
			result=ps.executeUpdate();

		} catch (Exception e) {

		   e.printStackTrace();
		}
		return result;
	}

	public Payroll getDepartment(String id) {

		Payroll payroll=new Payroll();
		
		try {
			String sql="select dept_name,overtime_status,rate,email from apm_payroll_department where id="+id+"";
			PreparedStatement ps=connection.prepareStatement(sql);
			ResultSet rs=ps.executeQuery();
			
			while(rs.next()) {
				payroll.setDept_name(rs.getString(1));
				payroll.setOvertime_status(rs.getString(2));
				payroll.setRate(rs.getString(3));
				payroll.setEmail(rs.getString(4));
				payroll.setId(Integer.parseInt(id));
			}
			
		} catch (Exception e) {

		    e.printStackTrace();
		}
		
		return payroll;
	}

	public int updateDepartment(Payroll payroll) {

		int res=0;
		
		try {
			String sql="update apm_payroll_department set dept_name=?,overtime_status=?,rate=?,email=? where id="+payroll.getId()+""; 
			PreparedStatement ps=connection.prepareStatement(sql);
			ps.setString(1, payroll.getDept_name());
			ps.setString(2, payroll.getOvertime_status());
			ps.setString(3, payroll.getRate());
			ps.setString(4, payroll.getEmail());
			res=ps.executeUpdate();
		} catch (Exception e) {

		   e.printStackTrace();
		}
	
		return res;
	}

	public Payroll getCompanyDetails(String id) {

		Payroll payroll=new Payroll();
		
		try {
			
			String sql="select company_name, date_format, it_month, tin_no, panno, esino, pfno, hourly_type, fixed_hour, noofhours, ot_status, permissions, perm_penalty, no_permission from apm_payroll_company where id="+id+"";
			PreparedStatement ps=connection.prepareStatement(sql);
			ResultSet rs=ps.executeQuery();
			
			while(rs.next()) {
				   
				 payroll.setCompany_name(rs.getString(1));
				 payroll.setDate_format(rs.getString(2));
				 payroll.setItmonth(rs.getString(3));
				 payroll.setTinno(rs.getString(4));
				 payroll.setPan_no(rs.getString(5));
				 payroll.setEsi_no(rs.getString(6));
				 payroll.setPf_no(rs.getString(7));
				 payroll.setHourly_type(rs.getString(8));
				 payroll.setFixed_hour(rs.getString(9));
				 payroll.setNo_hours(rs.getString(10));
				 payroll.setOt_status(rs.getString(11));
				 payroll.setPermissions(rs.getString(12));
				 payroll.setPermi_penalty(rs.getString(13));
				 payroll.setNo_permission(rs.getString(14));
				 payroll.setId(Integer.parseInt(id));
				 
			}
			
			
		} catch (Exception e) {

		  e.printStackTrace();
		}
		
		return payroll;
	}

	public int updateCompany(Payroll payroll) {

		int result=0;
		
		try {
//		    String sql="update apm_payroll_company set company_name=?, date_format=?, it_month=?, tin_no=?, panno=?, esino=?, pfno=?, hourly_type=?, fixed_hour=?, noofhours=?, ot_status=?, permissions=?, perm_penalty=?, no_permission=? where id=1";
			String sql="update apm_payroll_company set noofhours=? where id=1";
			PreparedStatement ps=connection.prepareStatement(sql);
//			ps.setString(1,payroll.getCompany_name());
//			ps.setString(2,payroll.getDate_format());
//			ps.setString(3,payroll.getItmonth());
//			ps.setString(4,payroll.getTinno());
//			ps.setString(5,payroll.getPan_no());
//			ps.setString(6,payroll.getEsi_no());
//			ps.setString(7,payroll.getPf_no());
//			ps.setString(8,payroll.getHourly_type());
//			ps.setString(9,payroll.getFixed_hour());
			ps.setString(1, payroll.getNo_hours());
//			ps.setString(11, payroll.getOt_status());
//			ps.setString(12, payroll.getPermissions());
//			ps.setString(13, payroll.getPermi_penalty());
//			ps.setString(14, payroll.getNo_permission());
			
			result=ps.executeUpdate();    	    
		} catch (Exception e) {

		   e.printStackTrace();
		}
		
		return result;
	}

	public ArrayList<Payroll> getAllDesignation() {
ArrayList<Payroll> list=new ArrayList<Payroll>();
		
		
		try {
			String sql="select apm_payroll_designation.id,name,dept_name from apm_payroll_designation inner join apm_payroll_department on apm_payroll_department.id=apm_payroll_designation.department";
            PreparedStatement ps=connection.prepareStatement(sql);
            ResultSet rs=ps.executeQuery();
            while(rs.next()) {
            	
            	Payroll payroll=new Payroll();
            	payroll.setId(rs.getInt(1));
            	payroll.setName(rs.getString(2));
            	payroll.setDept_name(rs.getString(3));
            	list.add(payroll);
            	
            }
            
			
		} catch (Exception e) {

		   e.printStackTrace();
		}

		
		return list;
	}

	public int addDesignation(Payroll payroll) {
int result=0;
		
		try {
		
			String sql="insert into apm_payroll_designation(name,department) values (?,?)";
			PreparedStatement ps=connection.prepareStatement(sql);
			ps.setString(1, payroll.getName());
			ps.setString(2, payroll.getDept_name());
			result=ps.executeUpdate();

		} catch (Exception e) {

		   e.printStackTrace();
		}
		return result;
	}

	public Payroll getCompanyDetailsUser(int clinicid) {
Payroll payroll=new Payroll();
		
		try {
			
			String sql="select clinicname, country, state, city, address, pincode, mobile, landline, email, website from apm_user where id='"+clinicid+"'";
			PreparedStatement ps=connection.prepareStatement(sql);
			ResultSet rs=ps.executeQuery();
			
			while(rs.next()) {
				   
				 payroll.setCompany_name(rs.getString(1));
				 payroll.setCountry(rs.getString(2));
				 payroll.setState(rs.getString(3));
				 payroll.setCity(rs.getString(4));
				 payroll.setAddress(rs.getString(5));
				 payroll.setPincode(rs.getString(6));
				 payroll.setMobile(rs.getString(7));
				 payroll.setLandline(rs.getString(8));
				 payroll.setEmail(rs.getString(9));
				 payroll.setWebsite(rs.getString(10));
			}
			
			
		} catch (Exception e) {

		  e.printStackTrace();
		}
		
		return payroll;
	}

	public Payroll getDesignation(String id) {

		Payroll payroll=new Payroll();
		
		try {
			String sql="select name,department from apm_payroll_designation where id="+id+"";
			PreparedStatement ps=connection.prepareStatement(sql);
			ResultSet rs=ps.executeQuery();
			
			while(rs.next()) {
				payroll.setName(rs.getString(1));
				payroll.setDept_name(rs.getString(2));
				payroll.setId(Integer.parseInt(id));
			}
			
		} catch (Exception e) {

		    e.printStackTrace();
		}
		
		return payroll;
	}

	public int updateDesignation(Payroll payroll) {

		int res=0;
		
		try {
			String sql="update apm_payroll_designation set name=?,department=? where id="+payroll.getId()+""; 
			PreparedStatement ps=connection.prepareStatement(sql);
			ps.setString(1, payroll.getName());
			ps.setString(2, payroll.getDept_name());
			
			res=ps.executeUpdate();
		} catch (Exception e) {

		   e.printStackTrace();
		}
	
		return res;
	}

	public int deleteDepartment(String id) {
		int result=0;
		try {
			PreparedStatement ps=connection.prepareStatement("delete from apm_payroll_department where id='"+id+"'");
			result= ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public int deleteHoliday(String id) {
		int result=0;
		try {
			PreparedStatement ps=connection.prepareStatement("delete from apm_payroll_holiday where id='"+id+"'");
			result= ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public int deleteDesignation(String id) {
		int result=0;
		try {
			PreparedStatement ps=connection.prepareStatement("delete from apm_payroll_designation where id='"+id+"'");
			result= ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public int deleteLeave(String id) {
		int result=0;
		try {
			PreparedStatement ps=connection.prepareStatement("delete from payroll_leave where id='"+id+"'");
			result= ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public ArrayList<Payroll> getAllQualification() {
ArrayList<Payroll> list=new ArrayList<Payroll>();
		
		
		try {
			String sql="select id,name from apm_payroll_qualification";
            PreparedStatement ps=connection.prepareStatement(sql);
            ResultSet rs=ps.executeQuery();
            while(rs.next()) {
            	
            	Payroll payroll=new Payroll();
            	payroll.setId(rs.getInt(1));
            	payroll.setName(DateTimeUtils.isNull(rs.getString(2)));
            	list.add(payroll);
            	
            }
            
			
		} catch (Exception e) {

		   e.printStackTrace();
		}

		
		return list;
	}

	public int addQualification(Payroll payroll) {

		int result=0;
		
		try {
		
			String sql="insert into apm_payroll_qualification(name) values (?)";
			PreparedStatement ps=connection.prepareStatement(sql);
			ps.setString(1, payroll.getName());
			result=ps.executeUpdate();

		} catch (Exception e) {

		   e.printStackTrace();
		}
		return result;
	}

	public int updateQualification(Payroll payroll) {
	int res=0;
		
		try {
			String sql="update apm_payroll_qualification set name=? where id="+payroll.getId()+""; 
			PreparedStatement ps=connection.prepareStatement(sql);
			ps.setString(1, payroll.getName());
			
			res=ps.executeUpdate();
		} catch (Exception e) {

		   e.printStackTrace();
		}
	
		return res;
	}

	public Payroll getQualifications(String id) {
	Payroll payroll=new Payroll();
		
		try {
			String sql="select name from apm_payroll_qualification where id="+id+"";
			PreparedStatement ps=connection.prepareStatement(sql);
			ResultSet rs=ps.executeQuery();
			
			while(rs.next()) {
				payroll.setName(rs.getString(1));
			}
			
		} catch (Exception e) {

		    e.printStackTrace();
		}
		
		return payroll;
	}

	public int deleteQualification(String id) {
		int result=0;
		try {
			PreparedStatement ps=connection.prepareStatement("delete from apm_payroll_qualification where id='"+id+"'");
			result= ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public int deleteOT(String id) {
		int result=0;
		try {
			PreparedStatement ps=connection.prepareStatement("delete from payroll_ot_request where id='"+id+"'");
			result= ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	
	
	
}
