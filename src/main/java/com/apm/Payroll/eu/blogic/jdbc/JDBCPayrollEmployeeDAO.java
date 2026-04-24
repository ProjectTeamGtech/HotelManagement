package com.apm.Payroll.eu.blogic.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import com.apm.Master.eu.entity.Master;
import com.apm.Payroll.eu.bi.PayrollEmployeeDAO;
import com.apm.Payroll.eu.entity.Employee;
import com.apm.Report.eu.entity.MisReport;
import com.apm.common.utils.DateTimeUtils;
import com.apm.common.utils.Pagination;
import com.apm.common.web.common.helper.LoginInfo;

public class JDBCPayrollEmployeeDAO implements PayrollEmployeeDAO {
  
	   Connection connection;
	   public JDBCPayrollEmployeeDAO(Connection connection) {
	
	       this.connection=connection;
	   }
	   
	public ArrayList<Employee> getAllEmployees(String searchText,Pagination pagination,String dept,String searchname,LoginInfo loginInfo) {
		if(dept==null){
			dept="0";
		}if(dept.equals("")){
			dept="0";
		}
		ArrayList<Employee> list=new ArrayList<Employee>();
		
		try {
		      String sql="";
		      StringBuffer buffer=new StringBuffer();
			   buffer.append("select empid,name, company_id, branchid, dept_id, designation, date_join, qualification, password, status,initial,empcode,email,mobno,workhour from apm_payroll_employee ");
				boolean flag=false;
			   if(!dept.equals("0")){
				   flag=true;
					   buffer.append("where dept_id='"+dept+"'");
				   }
		      if(searchText!=null){
		    	  if(!searchText.equals("")){
		    		  if(flag){
		    			  buffer.append(" and empcode='"+searchText+"' ");
		    		  }else{
		    			  flag=true;
		    		  buffer.append(" where empcode='"+searchText+"' ");
		    		  }  		
		    	  }
		      }
		      if(searchname!=null){
		    	  if(!searchname.equals("")){
		    		  if(flag){
		    			  buffer.append(" and name like '%"+searchname+"%' ");
		    		  }else {
		    			  buffer.append(" where name like '%"+searchname+"%' ");
					}
		    		  
		    	  }
		      }
		      if(!loginInfo.isPayrollaccess()){
		    	  
		    	  if(flag){
	    			  buffer.append(" and userid='"+loginInfo.getUserId()+"' ");
	    		  }else {
	    			  buffer.append(" where userid='"+loginInfo.getUserId()+"' ");
				}
		      }
		      buffer.append("group by empid desc");
			   sql=buffer.toString();			  
			   if(pagination!=null)
				{
					sql=pagination.getSQLQuery(sql);
				}
			   PreparedStatement ps=connection.prepareStatement(sql);
			  ResultSet rs=ps.executeQuery();
			  
			  while(rs.next()) {
				     Employee employee=new Employee();
				     employee.setEmp_id(rs.getString(1));
				     String name= rs.getString(2);
				     
				     employee.setName(name);
				     
				     String comp_id=rs.getString(3);
				     employee.setCompany(getCompanyName(comp_id));
				     String branchid=rs.getString(4);
				     employee.setBranch(getBranchName(branchid));
				     String dept_id=rs.getString(5);
				     employee.setDepartment(getDepartmentName(dept_id));
				     employee.setDesignation(rs.getString(6));
				     employee.setDate_join(rs.getString(7));
				     employee.setQualification(rs.getString(8));
				     employee.setPassword(rs.getString(9));
				    employee.setEmpcode(rs.getString(12));
				    employee.setEmail(rs.getString(13));
				    employee.setMobNo(rs.getString(14));
				    employee.setWorkhour(rs.getString(15));
				    /*employee.setInitial(rs.getString(10));*/
				     list.add(employee);  
			  }
			  			
		} catch (Exception e) {

		  e.printStackTrace();
		}
		
		return list;
	}

	/*
	public ArrayList<Employee> getAllEmployees(String branch_id) {

		ArrayList<Employee> list=new ArrayList<Employee>();
		String sql="";
		
		try {
			
		      if(branch_id!=null && branch_id!=""){
		    	  sql="select empid, name, company_id, branchid, dept_id, designation, date_join, qualification, password, status from apm_payroll_employee where branchid="+branch_id+"";
		      }
		      else {
			     sql="select empid, name, company_id, branchid, dept_id, designation, date_join, qualification, password, status from apm_payroll_employee";
		      }
			  PreparedStatement ps=connection.prepareStatement(sql);
			  ResultSet rs=ps.executeQuery();
			  
			  while(rs.next()) {
				     Employee employee=new Employee();
				     employee.setEmp_id(rs.getString(1));
				     employee.setName(rs.getString(2));
				     
				     String comp_id=rs.getString(3);
				     employee.setCompany(getCompanyName(comp_id));
				     String branchid=rs.getString(4);
				     employee.setBranch(getBranchName(branchid));
				     String dept_id=rs.getString(5);
				     employee.setDepartment(getDepartmentName(dept_id));
				     employee.setDesignation(rs.getString(6));
				     employee.setDate_join(rs.getString(7));
				     employee.setQualification(rs.getString(8));
				     employee.setPassword(rs.getString(9));
				     list.add(employee);  
			  }
			  			
		} catch (Exception e) {

		  e.printStackTrace();
		}
		
		return list;
	}
*/
	
	
	public String getDepartmentName(String dept_id) {

		String department="";
		try {
			
			String sql="select dept_name from apm_payroll_department where id="+dept_id+"";
			PreparedStatement ps=connection.prepareStatement(sql);
			ResultSet rs=ps.executeQuery();
			while(rs.next()) {
				
				 department=rs.getString(1);
			}
			
			
		} catch (Exception e) {

		   e.printStackTrace();
		}
	
		return department;
	}

	public String getBranchName(String branchid) {

		String branch="";
		
		try {

			String sql="select name from apm_payroll_branch where id="+branchid+"";
			PreparedStatement ps=connection.prepareStatement(sql);
			ResultSet rs=ps.executeQuery();
			
			while(rs.next()){
				
				branch=rs.getString(1);
			}
			
		} catch (Exception e) {

		  e.printStackTrace();
		}
	
		return branch;
	}

	public String getCompanyName(String comp_id) {

		String company="";
		try {
			
			String sql="select company_name from apm_payroll_company where id="+comp_id+"";
			PreparedStatement ps=connection.prepareStatement(sql);
			ResultSet rs=ps.executeQuery();
			while(rs.next()) {
				
				company=rs.getString(1);				
			}
			
			
		} catch (Exception e) {

		   e.printStackTrace();
		}
		
		return company;
	}

	public ArrayList<Employee> getAllCompanies() {

		ArrayList<Employee> list=new ArrayList<Employee>();
		
		try {
			
			String sql="select id,company_name from apm_payroll_company";
			PreparedStatement ps=connection.prepareStatement(sql);
			ResultSet rs=ps.executeQuery();
			while(rs.next()) {
				
				  Employee employee=new Employee();
				  employee.setComp_id(rs.getString(1));
				  employee.setCompany(rs.getString(2));
				  list.add(employee);
				
			}
			
		} catch (Exception e) {

		   e.printStackTrace();
		}
		
		return list;
	}

	public ArrayList<Employee> getAllBranches() {

		ArrayList<Employee> list=new ArrayList<Employee>();
		
		try {
			
			 String sql="select id,name from apm_payroll_branch";
			 PreparedStatement ps=connection.prepareStatement(sql);
			 ResultSet rs=ps.executeQuery();
			 while(rs.next()) {
				   
				  Employee employee=new Employee();
				  employee.setBranch_id(rs.getString(1));
				  employee.setBranch(rs.getString(2));
				  list.add(employee);
			 }
			 
		} catch (Exception e) {

		   e.printStackTrace();
		   
		}
		
		return list;
	}

	public ArrayList<Employee> getAllDepartments() {

		ArrayList<Employee> list=new ArrayList<Employee>();
		
		try {
			
			String sql="select id,dept_name from apm_payroll_department";
			PreparedStatement ps=connection.prepareStatement(sql);
			ResultSet rs=ps.executeQuery();
			while(rs.next()) {
				
				  Employee employee=new Employee();
				  employee.setDept_id(rs.getString(1));
				  employee.setDepartment(rs.getString(2));
				  list.add(employee);
			}
			
		} catch (Exception e) {

		   e.printStackTrace();
		}
		
		
		
		return list;
	}

	public int addEmployee(Employee employee) {

		int result=0;
		
		try {
			
			String sql="insert into apm_payroll_employee (name, company_id, branchid, dept_id, designation, date_join, qualification,dob, currentaddress, contact, panno, permanentadd, initial, empcode,adharno, gender, postcode, maritalsts, country, town, mobno, email) values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
			PreparedStatement ps=connection.prepareStatement(sql);
			ps.setString(1, employee.getName());
			ps.setString(2, employee.getCompany());
			ps.setString(3, employee.getBranch());
			ps.setString(4, employee.getDepartment());
			ps.setString(5, employee.getDesignation());
			ps.setString(6, employee.getDate_join());
			ps.setString(7, employee.getQualification());
			ps.setString(8, employee.getDob());
			ps.setString(9, employee.getCurrentaddress());
			ps.setString(10, employee.getContact());
			ps.setString(11, employee.getPanno());
			ps.setString(12, employee.getPermanentaddress());
			/*ps.setString(8, employee.getPassword());*/
			ps.setString(13, employee.getInitial());
			ps.setString(14, employee.getEmpcode());
			//adharno, gender, postcode, maritalsts, country, town, mobno, email
			ps.setString(15, employee.getAdhno());
			ps.setString(16, employee.getGender());
			ps.setString(17, employee.getPostcode());
			ps.setString(18, employee.getMaritalsts());
			ps.setString(19, employee.getCounty());
			ps.setString(20, employee.getTown());
			ps.setString(21, employee.getMobNo());
			ps.setString(22, employee.getEmail());
			result=ps.executeUpdate();
			
			if(result>0){
				
				   ResultSet rs1= ps.getGeneratedKeys(); 
				   
				   while(rs1.next()) {
					   result=rs1.getInt(1);
				   }
				   
			}
			
			
		} catch (Exception e) {

		   e.printStackTrace();
		}
	
		return result;
	}

	public Employee getEmployee(String id) {

		Employee employee=new Employee();
		
		try {
			StringBuffer buffer=new StringBuffer();
			buffer.append("select name, company_id, branchid, dept_id, designation, date_join, qualification,status,dob, currentaddress, contact, panno, permanentadd, ");
			buffer.append("empcode,adharno, gender, postcode, maritalsts, country, town, mobno, email,initial,apm_payroll_salary_master.basic ");
			buffer.append("from apm_payroll_employee ");
			buffer.append("inner join apm_payroll_salary_master on apm_payroll_salary_master.emp_id=apm_payroll_employee.empid where apm_payroll_employee.empid="+id+" ");
			PreparedStatement ps=connection.prepareStatement(buffer.toString());
			ResultSet rs=ps.executeQuery();
			while(rs.next()) {
				
				     employee.setName(rs.getString(1));
				     employee.setCompany(rs.getString(2));
				     employee.setBranch(rs.getString(3));
				     employee.setDepartment(rs.getString(4));
				     employee.setDesignation(rs.getString(5));
				     employee.setDate_join(rs.getString(6));
				     employee.setQualification(rs.getString(7));
				     /*employee.setPassword(rs.getString(8));*/
				     employee.setDob(rs.getString(9));
				     employee.setCurrentaddress(rs.getString(10));
				     employee.setContact(rs.getString(11));
				     employee.setPanno(rs.getString(12));
				     employee.setPermanentaddress(rs.getString(13));
				     employee.setEmpcode(rs.getString(14));
				     employee.setEmp_id(id);
//				     adharno, gender, postcode, maritalsts, country, town, mobno, email
				     employee.setAdhno(rs.getString(15));
				     employee.setGender(rs.getString(16));
				     employee.setPostcode(rs.getString(17));
				     employee.setMaritalsts(rs.getString(18));
				     employee.setCounty(rs.getString(19));
				     employee.setTown(rs.getString(20));
				     employee.setMobNo(rs.getString(21));
				     employee.setEmail(rs.getString(22));
				     employee.setInitial(rs.getString(23));
				     employee.setBasicsal(rs.getString(24));
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return employee;
	}

	public int updateEmployee(Employee employee) {

		int result=0;
		
		try {
			
//			String sql="update apm_payroll_employee set name=?, company_id=?, branchid=?, dept_id=?, designation=?, date_join=?, qualification=?, dob=?, currentaddress=?, contact=?, panno=?, permanentadd=?,initial=?,empcode=?, adharno=?, gender=?, postcode=?, maritalsts=?, country=?, town=?, mobno=?, email=? where empid="+employee.getEmp_id()+"";
			String sql="update apm_payroll_employee set name=?, company_id=?, branchid=?, dept_id=?, designation=?, dob=?, currentaddress=?,  gender=?, postcode=?,  country=?, town=?, mobno=?, nationality=?, workhour=?, empcode=?,date_join=?,agencyname=?,email=? where empid="+employee.getEmp_id()+"";
			PreparedStatement ps=connection.prepareStatement(sql);
			ps.setString(1, employee.getName());
			ps.setString(2, employee.getCompany());
			ps.setString(3, employee.getBranch());
			ps.setString(4, employee.getDepartment());
			ps.setString(5, employee.getDesignation());
//			ps.setString(6, employee.getDate_join());
//			ps.setString(7, employee.getQualification());
			ps.setString(6, employee.getDob());
			ps.setString(7, employee.getCurrentaddress());
//			ps.setString(10, employee.getContact());
//			ps.setString(11, employee.getPanno());
//			ps.setString(12, employee.getPermanentaddress());
//			ps.setString(13, employee.getInitial());
//			empcode, adharno, gender, postcode, maritalsts, country, town, mobno, email?
//			ps.setString(14, employee.getEmpcode());
//			ps.setString(15, employee.getAdhno());
			ps.setString(8, employee.getGender());
			ps.setString(9, employee.getPostcode());
//			ps.setString(18, employee.getMaritalsts());
			ps.setString(10, employee.getCounty());
			ps.setString(11, employee.getTown());
			ps.setString(12, employee.getMobNo());
			ps.setString(13, employee.getNationality());
			ps.setString(14, DateTimeUtils.isNull(employee.getWorkhour()));
			ps.setString(15, employee.getEmployid());
			ps.setString(16, employee.getDate_join());
			ps.setString(17, employee.getAgency());
			ps.setString(18, employee.getEmail());
			/*ps.setString(8, employee.getPassword());*/
			
			result=ps.executeUpdate();
		} catch (Exception e) {

		   e.printStackTrace();
		}
	
		return result;
	}

	public int addToSalary(int result) {

		int i=0;

		
		try {
			
		   Date date=Calendar.getInstance(Locale.getDefault()).getTime();	
           SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd");
           String time=format.format(date);
           
           String sql="insert into apm_payroll_salary_master (emp_id,basic,date) values ("+result+",'0','"+time+"')";
           PreparedStatement ps=connection.prepareStatement(sql);
           i=ps.executeUpdate();
           
           String sql1="insert into apm_payroll_salary (emp_id,basic,date) values ("+result+",'0','"+time+"')";
           PreparedStatement ps1=connection.prepareStatement(sql1);
           i=ps1.executeUpdate();
	 		
		} catch (Exception e) {
			
			e.printStackTrace();
		}
	
		return i;
	}
	   
      	public int gettotalempcount(String searchText, String dept, String searchname) {
    	  int res=0;
    	  try {
    		  if(dept==null){
    				dept="0";
    			}if(dept.equals("")){
    				dept="0";
    			}
    			ArrayList<Employee> list=new ArrayList<Employee>();
    			
    			      String sql="";
    			      StringBuffer buffer=new StringBuffer();
    				   buffer.append("select count(*) from apm_payroll_employee ");
    					boolean flag=false;
    				   if(!dept.equals("0")){
    					   flag=true;
    						   buffer.append("where dept_id='"+dept+"'");
    					   }
    			      if(searchText!=null){
    			    	  if(!searchText.equals("")){
    			    		  if(flag){
    			    			  buffer.append(" and empcode='"+searchText+"' ");
    			    		  }else{
    			    			  flag=true;
    			    		  buffer.append(" where empcode='"+searchText+"' ");
    			    		  }  		
    			    	  }
    			      }
    			      if(searchname!=null){
    			    	  if(!searchname.equals("")){
    			    		  if(flag){
    			    			  buffer.append(" and name like '%"+searchname+"%' ");
    			    		  }else {
    			    			  buffer.append(" where name like '%"+searchname+"%' ");
    						}
    			    		  
    			    	  }
    			      }
    				   sql=buffer.toString();			  
			PreparedStatement ps = connection.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()){
				res=rs.getInt(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
      		return res;
      	}	
      public ArrayList<Employee> getAllAttendence(String emp_id,String filter_status) {
    	  
    	  ArrayList<Employee>list = new ArrayList<Employee>();
    	  try { 
			String sql = "";
			StringBuffer buffer = new StringBuffer();
			buffer.append("select emp_id, month, days, totalsalary from apm_payroll_weeksheet ");
			if(!filter_status.equals("")){
				buffer.append("where month='"+filter_status+"'");
			}
			PreparedStatement ps = connection.prepareStatement(buffer.toString());
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()){
				String empname = getempname(rs.getString(1));
			Employee employee = new Employee();
			employee.setEmp_id(rs.getString(1));
			employee.setMonth(rs.getString(2));
			employee.setDays(rs.getString(3));
			employee.setTotalsalary(rs.getString(4));
			employee.setName(empname);
			list.add(employee);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
    	return list;
    }
      public String getempname(String emp_id){
    	  String name="";
    	  try {
			String sql = "select name from apm_payroll_employee where empid="+emp_id+"";
			PreparedStatement ps = connection.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				name = rs.getString(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return name;
    	  
      }

	public ArrayList<Employee> getseachedname(String searchtxt) {
		ArrayList<Employee> list=new ArrayList<Employee>();
		String sql="";
	    	  sql="select empid,name, company_id, branchid, dept_id, designation, date_join, qualification, password, status,initial,empcode from apm_payroll_employee where name like('%"+searchtxt+"%')";
	      
	    
		  try{
		   PreparedStatement ps=connection.prepareStatement(sql);
		  ResultSet rs=ps.executeQuery();
		  
		  while(rs.next()) {
			     Employee employee=new Employee();
			     employee.setEmp_id(rs.getString(1));
			     String name= rs.getString(11)+" "+rs.getString(2);
			     
			     employee.setName(name);
			     
			     String comp_id=rs.getString(3);
			     employee.setCompany(getCompanyName(comp_id));
			     String branchid=rs.getString(4);
			     employee.setBranch(getBranchName(branchid));
			     String dept_id=rs.getString(5);
			     employee.setDepartment(getDepartmentName(dept_id));
			     employee.setDesignation(rs.getString(6));
			     employee.setDate_join(rs.getString(7));
			     employee.setQualification(rs.getString(8));
			     employee.setPassword(rs.getString(9));
			    employee.setEmpcode(rs.getString(12));
			     list.add(employee);  
		  }
		  			
	} catch (Exception e) {

	  e.printStackTrace();
	}
	
	return list;
	}

	public int updatebasicsal(String empid, String basicsal) {
		int res=0;
		try {
			String sql="update apm_payroll_salary_master set basic='"+basicsal+"' where emp_id='"+empid+"' ";
			PreparedStatement ps=connection.prepareStatement(sql);
			
			res=ps.executeUpdate();
		}catch (Exception e) {
			e.printStackTrace();
		}
		return res;
	}

	public Employee getAllDetailsEmployee(String emp_id) {
Employee employee=new Employee();
		
		try {
			StringBuffer buffer=new StringBuffer();
			buffer.append("select name, company_id, branchid, dept_id, designation, date_join, qualification,status,dob, currentaddress, contact, panno, permanentadd, ");
			buffer.append("empcode,adharno, gender, postcode, maritalsts, country, town, mobno, email,initial,apm_payroll_salary_master.basic,apm_payroll_salary_master.da, ");
			buffer.append("apm_payroll_salary_master.hra,apm_payroll_salary_master.conveyance,apm_payroll_salary_master.perdevallow,medical_allowance, ");
			buffer.append("apm_payroll_salary_master.tds,apm_payroll_salary_master.emp_esi,apm_payroll_salary_master.emp_pf,apm_payroll_salary_master.professional_tax,apm_payroll_salary_master.net_pay,nationality, ");
			buffer.append("account_no, bank_name, ifsc_code, bank_branch,apm_payroll_salary_master.daperc,apm_payroll_salary_master.hraperc,apm_payroll_salary_master.tdsperc,apm_payroll_employee.workhour,apm_payroll_salary_master.fixedsalary,apm_payroll_salary_master.basicsalper,apm_payroll_employee.userid, ");
			buffer.append("uan, pfno, esicno, totalleaves, remain_leave, agencyname, workhourday,workdayweek,otallow ");
			buffer.append("from apm_payroll_employee ");
			buffer.append("left join apm_payroll_salary_master on apm_payroll_salary_master.emp_id=apm_payroll_employee.empid where apm_payroll_employee.empid="+emp_id+" ");
			PreparedStatement ps=connection.prepareStatement(buffer.toString());
			ResultSet rs=ps.executeQuery();
			while(rs.next()) {
				     employee.setName(rs.getString(1));
				     employee.setCompany(DateTimeUtils.isNull(rs.getString(2)));
				     employee.setBranch(DateTimeUtils.isNull(rs.getString(3)));
				     employee.setDepartment(DateTimeUtils.isNull(rs.getString(4)));
				     employee.setDesignation(DateTimeUtils.isNull(rs.getString(5)));
				     employee.setDate_join(DateTimeUtils.isNull(rs.getString(6)));
				     employee.setQualification(DateTimeUtils.isNull(rs.getString(7)));
				     /*employee.setPassword(rs.getString(8));*/
				     employee.setDob(DateTimeUtils.isNull(rs.getString(9)));
				     employee.setCurrentaddress(DateTimeUtils.isNull(rs.getString(10)));
				     employee.setContact(DateTimeUtils.isNull(rs.getString(11)));
				     employee.setPanno(DateTimeUtils.isNull(rs.getString(12)));
				     employee.setPermanentaddress(DateTimeUtils.isNull(rs.getString(13)));
				     employee.setEmpcode(DateTimeUtils.isNull(rs.getString(14)));
				     employee.setEmp_id(emp_id);
//				     adharno, gender, postcode, maritalsts, country, town, mobno, email
				     employee.setAdhno(DateTimeUtils.isNull(rs.getString(15)));
				     employee.setGender(DateTimeUtils.isNull(rs.getString(16)));
				     employee.setPostcode(DateTimeUtils.isNull(rs.getString(17)));
				     employee.setMaritalsts(DateTimeUtils.isNull(rs.getString(18)));
				     employee.setCounty(DateTimeUtils.isNull(rs.getString(19)));
				     employee.setTown(DateTimeUtils.isNull(rs.getString(20)));
				     employee.setMobNo(DateTimeUtils.isNull(rs.getString(21)));
				     employee.setEmail(DateTimeUtils.isNull(rs.getString(22)));
				     employee.setInitial(DateTimeUtils.isNull(rs.getString(23)));
				     employee.setBasicsal(DateTimeUtils.numberCheck(rs.getString(24)));
				     employee.setDa(DateTimeUtils.numberCheck(rs.getString(25)));
				     employee.setHra(DateTimeUtils.numberCheck(rs.getString(26)));
				     employee.setConveyance(DateTimeUtils.numberCheck(rs.getString(27)));
				     employee.setPerdevallow(DateTimeUtils.numberCheck(rs.getString(28)));
				     employee.setMedicalallow(DateTimeUtils.numberCheck(rs.getString(29)));
				     employee.setTds(DateTimeUtils.numberCheck(rs.getString(30)));
				     employee.setEmp_esi(DateTimeUtils.numberCheck(rs.getString(31)));
				     employee.setEmp_pf(DateTimeUtils.numberCheck(rs.getString(32)));
				     employee.setProftax(DateTimeUtils.numberCheck(rs.getString(33)));
				     employee.setNetsal(DateTimeUtils.numberCheck(rs.getString(34)));
				     employee.setNationality(rs.getString(35));
				     employee.setAccount_no(DateTimeUtils.numberCheck(rs.getString(36)));
				     employee.setBank_name(DateTimeUtils.isNull(rs.getString(37)));
				     employee.setIfsc_code(DateTimeUtils.isNull(rs.getString(38)));
				     employee.setBank_branch(DateTimeUtils.isNull(rs.getString(39)));
				     employee.setDaper(DateTimeUtils.numberCheck(rs.getString(40)));
				     employee.setHraper(DateTimeUtils.numberCheck(rs.getString(41)));
				     employee.setTdsper(DateTimeUtils.numberCheck(rs.getString(42)));
				     employee.setWorkhour(DateTimeUtils.numberCheck(rs.getString(43)));
				     employee.setFixedsal(DateTimeUtils.numberCheck(rs.getString(44)));
				     employee.setBasicsalper(DateTimeUtils.numberCheck(rs.getString(45)));
				     employee.setUserid(DateTimeUtils.isNull(rs.getString(46)));
				     employee.setUanno(DateTimeUtils.isNull(rs.getString(47)));
				     employee.setPfno(DateTimeUtils.isNull(rs.getString(48)));
				     employee.setEsicno(DateTimeUtils.isNull(rs.getString(49)));
				     employee.setCasualleave(DateTimeUtils.isNull(rs.getString(50)));
				     employee.setRemain_leave(DateTimeUtils.isNull(rs.getString(51)));
				     employee.setAgency(DateTimeUtils.isNull(rs.getString(52)));
				     employee.setWorkinghourday(DateTimeUtils.isNull(rs.getString(53)));
				     employee.setWorkingdayweek(DateTimeUtils.isNull(rs.getString(54)));
				     employee.setTotworkinghourweek(DateTimeUtils.isNull(rs.getString(43)));
				     employee.setOtallow(rs.getString(55));
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return employee;
	}

	public int updatebasicsal(Employee employee,String table) {
int result=0;
		try {
			
			String sql="update "+table+" set basic=?, da=?, hra=?, conveyance=?, perdevallow=?, medical_allowance=?, tds=?, emp_pf=?, emp_esi=?, professional_tax=?, net_pay=?, daperc=?, hraperc=?, tdsperc=?, userid=?, fixedsalary=?, basicsalper=?, gross_sal=?, salperhr=? where emp_id="+employee.getEmp_id()+"";
			PreparedStatement ps=connection.prepareStatement(sql);
			ps.setString(1, employee.getBasicsal());
			ps.setString(2, employee.getDa());
			ps.setString(3, employee.getHra());
			ps.setString(4, employee.getConveyance());
			ps.setString(5, employee.getPerdevallow());
			ps.setString(6, employee.getMedicalallow());
			ps.setString(7, employee.getTds());
			ps.setString(8, employee.getEmp_pf());
			ps.setString(9, employee.getEmp_esi());
			ps.setString(10, employee.getProftax());
			ps.setString(11, employee.getNetsal());
			ps.setString(12, employee.getDaper());
			ps.setString(13, employee.getHraper());
			ps.setString(14, employee.getTdsper());
			ps.setString(15, employee.getUserid());
			ps.setString(16, employee.getFixedsal());
			ps.setString(17, employee.getBasicsalper());
			ps.setString(18, employee.getGrossal());
			ps.setString(19, employee.getPerhr_sal());
			result=ps.executeUpdate();
		} catch (Exception e) {

		   e.printStackTrace();
		}
	
		return result;
	}

	public String getEmployeeName(String empid) {
		String empname="";
		try {
			String sql="select name from apm_payroll_employee where empid='"+empid+"'";
			PreparedStatement preparedStatement=connection.prepareStatement(sql);
			ResultSet rs=preparedStatement.executeQuery();
			while(rs.next()) {
				empname=rs.getString(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return empname;
	}

	public int updateBankEmployee(Employee employee) {

		int result=0;
		
		try {
			
//			String sql="update apm_payroll_employee set name=?, company_id=?, branchid=?, dept_id=?, designation=?, date_join=?, qualification=?, dob=?, currentaddress=?, contact=?, panno=?, permanentadd=?,initial=?,empcode=?, adharno=?, gender=?, postcode=?, maritalsts=?, country=?, town=?, mobno=?, email=? where empid="+employee.getEmp_id()+"";
			String sql="update apm_payroll_employee set account_no=?,bank_name=?, ifsc_code=?, bank_branch=? where empid="+employee.getEmp_id()+"";
			PreparedStatement ps=connection.prepareStatement(sql);
			ps.setString(1, employee.getAccount_no());
			ps.setString(2, employee.getBank_name());
			ps.setString(3, employee.getIfsc_code());
			ps.setString(4, employee.getBank_branch());
			
			result=ps.executeUpdate();
		} catch (Exception e) {

		   e.printStackTrace();
		}
	
		return result;
	}

	public ArrayList<Master> getAllDesignation(String deptid) {
		ArrayList<Master> list=new ArrayList<Master>();
		try {
			String sql="SELECT id,name from apm_payroll_designation where department='"+deptid+"' ";
			PreparedStatement ps=connection.prepareStatement(sql);
			ResultSet rs=ps.executeQuery();
			
			while(rs.next()){
				  
				Master master=new Master();
				master.setId(rs.getInt(1));
				master.setName(rs.getString(2));
				list.add(master);
			}
			
		} catch (Exception e) {

			e.printStackTrace();
		}
		
		return list;
	}
	public int updatePersonalEmployee(Employee employee) {

		int result=0;
	try {
			
					String sql="update apm_payroll_employee set panno=?, adharno=?, maritalsts=? where empid="+employee.getEmp_id()+"";
		 			PreparedStatement ps=connection.prepareStatement(sql);
					ps.setString(1, employee.getPanno());
					ps.setString(2, employee.getAdhno());
					ps.setString(3, employee.getMaritalsts());
		 			
		 			result=ps.executeUpdate();
		 		} catch (Exception e) {
		 			e.printStackTrace();
}
	return result;
	}

	public String getEmployeeUserId(String emp_id) {
		String result="";
	try {
		String sql="select userid from apm_payroll_employee where empid="+emp_id+" ";
		PreparedStatement preparedStatement=connection.prepareStatement(sql);
		ResultSet rs=preparedStatement.executeQuery();
		while(rs.next()){
			result=rs.getString(1);
		}
	} catch (Exception e) {
		e.printStackTrace();
	}
	return result;
	}

	public int updateUanPfEmployee(Employee employee) {

		int result=0;
		
		try {
			
//			String sql="update apm_payroll_employee set name=?, company_id=?, branchid=?, dept_id=?, designation=?, date_join=?, qualification=?, dob=?, currentaddress=?, contact=?, panno=?, permanentadd=?,initial=?,empcode=?, adharno=?, gender=?, postcode=?, maritalsts=?, country=?, town=?, mobno=?, email=? where empid="+employee.getEmp_id()+"";
			String sql="update apm_payroll_employee set uan=?, pfno=?, esicno=? where empid="+employee.getEmp_id()+"";
			PreparedStatement ps=connection.prepareStatement(sql);
			ps.setString(1, employee.getUanno());
			ps.setString(2, employee.getPfno());
			ps.setString(3, employee.getEsicno());
			
			result=ps.executeUpdate();
		} catch (Exception e) {

		   e.printStackTrace();
		}
	
		return result;
	}

	public int updateleaveEmployee(Employee employee) {
		int result=0;
		
		try {
			
//			String sql="update apm_payroll_employee set name=?, company_id=?, branchid=?, dept_id=?, designation=?, date_join=?, qualification=?, dob=?, currentaddress=?, contact=?, panno=?, permanentadd=?,initial=?,empcode=?, adharno=?, gender=?, postcode=?, maritalsts=?, country=?, town=?, mobno=?, email=? where empid="+employee.getEmp_id()+"";
			String sql="update apm_payroll_employee set totalleaves=?, remain_leave=? where empid="+employee.getEmp_id()+"";
			PreparedStatement ps=connection.prepareStatement(sql);
			ps.setString(1, employee.getCasualleave());
			ps.setString(2, employee.getCasualleave());
			result=ps.executeUpdate();
		} catch (Exception e) {

		   e.printStackTrace();
		}
	
		return result;
	}

	public Employee getallDetailsEmployeeByUserId(String userid) {

Employee employee=new Employee();
		
		try {
			StringBuffer buffer=new StringBuffer();
			buffer.append("select name, company_id, branchid, dept_id, designation, date_join, qualification,status,dob, currentaddress, contact, panno, permanentadd, ");
			buffer.append("empcode,adharno, gender, postcode, maritalsts, country, town, mobno, email,initial,apm_payroll_salary_master.basic,apm_payroll_salary_master.da, ");
			buffer.append("apm_payroll_salary_master.hra,apm_payroll_salary_master.conveyance,apm_payroll_salary_master.perdevallow,medical_allowance, ");
			buffer.append("apm_payroll_salary_master.tds,apm_payroll_salary_master.emp_esi,apm_payroll_salary_master.emp_pf,apm_payroll_salary_master.professional_tax,apm_payroll_salary_master.net_pay,nationality, ");
			buffer.append("account_no, bank_name, ifsc_code, bank_branch,apm_payroll_salary_master.daperc,apm_payroll_salary_master.hraperc,apm_payroll_salary_master.tdsperc,apm_payroll_employee.workhour,apm_payroll_salary_master.fixedsalary,apm_payroll_salary_master.basicsalper,apm_payroll_employee.userid, ");
			buffer.append("uan, pfno, esicno, totalleaves,remain_leave,empid ");
			buffer.append("from apm_payroll_employee ");
			buffer.append("left join apm_payroll_salary_master on apm_payroll_salary_master.emp_id=apm_payroll_employee.empid where apm_payroll_employee.userid='"+userid+"' ");
			PreparedStatement ps=connection.prepareStatement(buffer.toString());
			ResultSet rs=ps.executeQuery();
			while(rs.next()) {
				     employee.setName(rs.getString(1));
				     employee.setCompany(DateTimeUtils.isNull(rs.getString(2)));
				     employee.setBranch(DateTimeUtils.isNull(rs.getString(3)));
				     employee.setDepartment(DateTimeUtils.isNull(rs.getString(4)));
				     employee.setDesignation(DateTimeUtils.isNull(rs.getString(5)));
				     employee.setDate_join(DateTimeUtils.isNull(rs.getString(6)));
				     employee.setQualification(DateTimeUtils.isNull(rs.getString(7)));
				     /*employee.setPassword(rs.getString(8));*/
				     employee.setDob(DateTimeUtils.isNull(rs.getString(9)));
				     employee.setCurrentaddress(DateTimeUtils.isNull(rs.getString(10)));
				     employee.setContact(DateTimeUtils.isNull(rs.getString(11)));
				     employee.setPanno(DateTimeUtils.isNull(rs.getString(12)));
				     employee.setPermanentaddress(DateTimeUtils.isNull(rs.getString(13)));
				     employee.setEmpcode(DateTimeUtils.isNull(rs.getString(14)));
//				     adharno, gender, postcode, maritalsts, country, town, mobno, email
				     employee.setAdhno(DateTimeUtils.isNull(rs.getString(15)));
				     employee.setGender(DateTimeUtils.isNull(rs.getString(16)));
				     employee.setPostcode(DateTimeUtils.isNull(rs.getString(17)));
				     employee.setMaritalsts(DateTimeUtils.isNull(rs.getString(18)));
				     employee.setCounty(DateTimeUtils.isNull(rs.getString(19)));
				     employee.setTown(DateTimeUtils.isNull(rs.getString(20)));
				     employee.setMobNo(DateTimeUtils.isNull(rs.getString(21)));
				     employee.setEmail(DateTimeUtils.isNull(rs.getString(22)));
				     employee.setInitial(DateTimeUtils.isNull(rs.getString(23)));
				     employee.setBasicsal(DateTimeUtils.numberCheck(rs.getString(24)));
				     employee.setDa(DateTimeUtils.numberCheck(rs.getString(25)));
				     employee.setHra(DateTimeUtils.numberCheck(rs.getString(26)));
				     employee.setConveyance(DateTimeUtils.numberCheck(rs.getString(27)));
				     employee.setPerdevallow(DateTimeUtils.numberCheck(rs.getString(28)));
				     employee.setMedicalallow(DateTimeUtils.numberCheck(rs.getString(29)));
				     employee.setTds(DateTimeUtils.numberCheck(rs.getString(30)));
				     employee.setEmp_esi(DateTimeUtils.numberCheck(rs.getString(31)));
				     employee.setEmp_pf(DateTimeUtils.numberCheck(rs.getString(32)));
				     employee.setProftax(DateTimeUtils.numberCheck(rs.getString(33)));
				     employee.setNetsal(DateTimeUtils.numberCheck(rs.getString(34)));
				     employee.setNationality(rs.getString(35));
				     employee.setAccount_no(DateTimeUtils.numberCheck(rs.getString(36)));
				     employee.setBank_name(DateTimeUtils.numberCheck(rs.getString(37)));
				     employee.setIfsc_code(DateTimeUtils.numberCheck(rs.getString(38)));
				     employee.setBank_branch(DateTimeUtils.numberCheck(rs.getString(39)));
				     employee.setDaper(DateTimeUtils.numberCheck(rs.getString(40)));
				     employee.setHraper(DateTimeUtils.numberCheck(rs.getString(41)));
				     employee.setTdsper(DateTimeUtils.numberCheck(rs.getString(42)));
				     employee.setWorkhour(DateTimeUtils.numberCheck(rs.getString(43)));
				     employee.setFixedsal(DateTimeUtils.numberCheck(rs.getString(44)));
				     employee.setBasicsalper(DateTimeUtils.numberCheck(rs.getString(45)));
				     employee.setUserid(DateTimeUtils.isNull(rs.getString(46)));
				     employee.setUanno(DateTimeUtils.isNull(rs.getString(47)));
				     employee.setPfno(DateTimeUtils.isNull(rs.getString(48)));
				     employee.setEsicno(DateTimeUtils.isNull(rs.getString(49)));
				     employee.setCasualleave(DateTimeUtils.isNull(rs.getString(50)));
				     employee.setRemain_leave(DateTimeUtils.isNull(rs.getString(51)));
				     employee.setEmp_id(rs.getString(52));
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return employee;
	}
	public String getEmployeeEmpid(String userid) {
		String result="";
	try {
		String sql="select empid from apm_payroll_employee where userid='"+userid+"' ";
		PreparedStatement preparedStatement=connection.prepareStatement(sql);
		ResultSet rs=preparedStatement.executeQuery();
		while(rs.next()){
			result=rs.getString(1);
		}
	} catch (Exception e) {
		e.printStackTrace();
	}
	return result;
	}

	public int savePayrollFileSubmissionData(String datetime, String uploaded_userid, String filesubmission_category,
			String filesubremark, String fileName, String filecontenttype,String empid, String userid) {
		int result=0;
		try {
			String sql="insert into payroll_uploaded_document (emp_id, userid, discription, category, filename, upload_userid, datetime, filecontenttype) values (?,?,?,?,?,?,?,?) ";
			PreparedStatement ps=connection.prepareStatement(sql);
			ps.setString(1, empid);
			ps.setString(2, userid);
			ps.setString(3, filesubremark);
			ps.setString(4, filesubmission_category);
			ps.setString(5, fileName);
			ps.setString(6, uploaded_userid);
			ps.setString(7, datetime);
			ps.setString(8, filecontenttype);
			result =ps.executeUpdate();
		} catch (Exception e) {

			e.printStackTrace();
		}
		return result;
	}

	public ArrayList<Employee> getPayrollFileSubmissionList(String emp_id) {
		ArrayList<Employee> arrayList = new ArrayList<Employee>();
		try {
			StringBuffer buffer = new StringBuffer();
			buffer.append("select id,emp_id, userid, discription, category, filename, upload_userid, datetime, filecontenttype from payroll_uploaded_document where emp_id="+emp_id+" ");
			PreparedStatement preparedStatement = connection.prepareStatement(buffer.toString());
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				Employee employee = new Employee();
				employee.setId(rs.getInt(1));
				employee.setEmp_id(rs.getString(2));
				employee.setUserid(rs.getString(3));
				employee.setDiscription(rs.getString(4));
				employee.setCategory(rs.getString(5));
				employee.setDatetime(DateTimeUtils.getCommencingDate1(rs.getString(8).split(" ")[0]));
				String filePathnew = "liveData/document/"+rs.getString(6);
				employee.setFilePathnew(filePathnew);
				
				//String filePathlink = filePath +"/"+rs.getString(4);
				//misReport.setFilePathlink(filePathlink);
				arrayList.add(employee);
			}
	}catch (Exception e) {
		e.printStackTrace();
	}
		return arrayList;
	}

	public Employee getFileSubmissionData(String id) {
		Employee employee = new Employee();
		try {
			String sql="select id, discription, category, filename, upload_userid, datetime from payroll_uploaded_document where id='"+id+"'";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				employee.setId(rs.getInt(1));
				employee.setDiscription(rs.getString(2));
				employee.setCategory(rs.getString(3));
				employee.setFilename(rs.getString(4));
				employee.setUserid(rs.getString(5));
				employee.setDatetime(DateTimeUtils.getCommencingDate1(rs.getString(6).split(" ")[0]));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return employee;
	}

	public ArrayList<Employee> getAlldocumenttype() {
ArrayList<Employee> list=new ArrayList<Employee>();
		
		try {
			
			String sql="select id,name from payroll_document_list";
			PreparedStatement ps=connection.prepareStatement(sql);
			ResultSet rs=ps.executeQuery();
			while(rs.next()) {
				
				  Employee employee=new Employee();
				  employee.setId(rs.getInt(1));
				  employee.setName(rs.getString(2));
				  list.add(employee);
			}
			
		} catch (Exception e) {

		   e.printStackTrace();
		}
		
		
		
		return list;
	}

	public int updateWorkinghrEmployee(Employee employee) {
int result=0;
		if(employee.getOtallow().equals("on")){
			employee.setOtallow("1");
		}else{
			employee.setOtallow("0");
		}
		try {
			
			String sql="update apm_payroll_employee set workhour=?,workhourday=?,workdayweek=?,otallow=? where empid="+employee.getEmp_id()+"";
			PreparedStatement ps=connection.prepareStatement(sql);
			ps.setString(1, employee.getTotworkinghourweek());
			ps.setString(2, employee.getWorkinghourday());
			ps.setString(3, employee.getWorkingdayweek());
			ps.setString(4, employee.getOtallow());
			result=ps.executeUpdate();
		} catch (Exception e) {

		   e.printStackTrace();
		}
	
		return result;
	}

	public int updateactiveStatus(String emp_id, String flag) {
int result=0;
		
		try {
			
			String sql="update apm_payroll_employee set status='"+flag+"' where empid="+emp_id+"";
			PreparedStatement ps=connection.prepareStatement(sql);
			result=ps.executeUpdate();
		} catch (Exception e) {

		   e.printStackTrace();
		}
	
		return result;
	}

	@Override
	public ArrayList<Employee> getAlltemplateList() {
		PreparedStatement preparedStatement=null;
		ArrayList<Employee>list=new ArrayList<Employee>();
		try {
			
			String sql="select id,title from apm_other_template where payroll=1";
		    PreparedStatement ps=connection.prepareStatement(sql);
		    ResultSet rs=ps.executeQuery();
		    while(rs.next()) {
			
			  Employee employee=new Employee();
			  employee.setId(rs.getInt(1));
			  employee.setTemplateName(rs.getString(2));
			  list.add(employee);
		}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
				
		return list;
	}

	@Override
	public String getTemplatetextbyid(String tempId) {
		PreparedStatement preparedStatement=null;
	    String template="";
		try {
			
			String sql="select othertemplate_text from apm_other_template where id="+tempId+"";
		    PreparedStatement ps=connection.prepareStatement(sql);
		    ResultSet rs=ps.executeQuery();
		    while(rs.next()) {
			
			template=rs.getString(1);
		}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
				
		return template;
	}

	@Override
	public double getPayroll_leave(String emp_id,String mont_yr) {
		PreparedStatement preparedStatement=null;
	    double days=0.0;
		try {
			
			String sql="select sum(days) from payroll_leave where empid='"+emp_id+"' and month= '"+mont_yr+"' and status=1 order by id  ";
		    PreparedStatement ps=connection.prepareStatement(sql);
		    ResultSet rs=ps.executeQuery();
		    while(rs.next()) {
			
			days=rs.getDouble(1);
		}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
				
		return days;
	}
}
