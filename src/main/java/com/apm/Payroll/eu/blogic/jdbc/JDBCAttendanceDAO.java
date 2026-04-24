package com.apm.Payroll.eu.blogic.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.Locale;

import com.apm.Accounts.eu.entity.Charges;
import com.apm.Payroll.eu.bi.AttendanceDAO;
import com.apm.Payroll.eu.bi.PayrollEmployeeDAO;
import com.apm.Payroll.eu.bi.PayrollMasterDAO;
import com.apm.Payroll.eu.entity.Attendance;
import com.apm.Payroll.eu.entity.Employee;
import com.apm.Payroll.eu.entity.Payroll;
import com.apm.Payroll.eu.entity.Salary;
import com.apm.common.utils.DateTimeUtils;
import com.apm.common.utils.Pagination;
import com.apm.common.web.common.helper.LoginInfo;

public class JDBCAttendanceDAO implements AttendanceDAO{

	Connection connection;
	
	public JDBCAttendanceDAO(Connection connection) {
          
		 this.connection=connection;
	}

	public int addOrUpdateWorkSheet(Attendance attendance) {
		PayrollEmployeeDAO employeeDAO=new JDBCPayrollEmployeeDAO(connection);
		PayrollMasterDAO payrollMasterDAO=new JDBCPayrollMasterDAO(connection);
		int result=0;
		int id=0;
		boolean flag=false;
		String userid="";
	     try {
	    	 String[] temp = attendance.getMonth().split("-");
	    	 int iyear = Integer.parseInt(temp[1]);
				int imonth = Integer.parseInt(attendance.getNummonth());
				int idays = 1;
				String res=payrollMasterDAO.getweekdays();
				int sunday=0;
				if(res!=null){
				String temp1[]=res.split(",");
				sunday=getSundays(imonth, iyear,temp1);
				}
	    	 String sql="select id,emp_id,month,weekno from apm_payroll_weeksheet";
	    	 PreparedStatement ps=connection.prepareStatement(sql);
	    	 
	    	 ResultSet  rs=ps.executeQuery();
	    	 while(rs.next()){
	    		 
	    		 id=rs.getInt(1);
	    		 String emp_id=rs.getString(2);
	    		 
	    		 String month=rs.getString(3);
	    		 String weekno=rs.getString(4);
	    		 
	    		 if(emp_id!=null && month!=null){
	    			 
	    			  if(emp_id.equals(attendance.getEmp_id()) && month.equals(attendance.getMonth()) ) {
	    				  
	    				   flag=true;
	    				   break;
	    			  }
	    		
	    		 }
	    	 }
	    	
	    	
	    	 /*if(flag){
	    		 
	    		 //update
	    		 
	    		 String query1="update apm_payroll_weeksheet set emp_id=?, branch_id=?, reason=?, month=?, weekno=?, monday=?, tuesday=?, wednesday=?, thursday=?, friday=?, saturday=?, sunday=?, total_hrs=?, notes=?, date=? where id="+id+"";
	    		 PreparedStatement ps1=connection.prepareStatement(query1);
	    		 ps1.setString(1, attendance.getEmp_id());
	    		 ps1.setString(2, attendance.getBranch_id());
	    		 ps1.setString(3, attendance.getReason());
	    		 ps1.setString(4, attendance.getMonth());
	    		 ps1.setString(5, attendance.getWeekno());
	    		 ps1.setString(6, attendance.getMonday());
	    		 ps1.setString(7, attendance.getTuesday());
	    		 ps1.setString(8, attendance.getWednesday());
	    		 ps1.setString(9, attendance.getThursday()); 
	    		 ps1.setString(10, attendance.getFriday());
	    		 ps1.setString(11, attendance.getSaturday());
	    		 ps1.setString(12, attendance.getSunday());
	    		 ps1.setString(13, attendance.getTotal_hour());
	    		 ps1.setString(14, attendance.getNotes());
	    		 ps1.setString(15, attendance.getDate());
	    		
	    		 result=ps1.executeUpdate();
	    	 }*/
if(flag){
	    		 
	    		 //update
	    		 
	    		 String query1="update apm_payroll_weeksheet set emp_id=?, branch_id=?, reason=?, month=?, notes=?, date=?, days=?, totalsalary=?,userid=?,leaves=?,total_hrs=? where id="+id+"";
	    		 PreparedStatement ps1=connection.prepareStatement(query1);
	    		 ps1.setString(1, attendance.getEmp_id());
	    		 ps1.setString(2, attendance.getBranch_id());
	    		 ps1.setString(3, attendance.getReason());
	    		  ps1.setString(4, attendance.getMonth());
	    		  /*ps1.setString(5, attendance.getWeekno());
	    		 ps1.setString(6, attendance.getMonday());
	    		 ps1.setString(7, attendance.getTuesday());
	    		 ps1.setString(8, attendance.getWednesday());
	    		 ps1.setString(9, attendance.getThursday()); 
	    		 ps1.setString(10, attendance.getFriday());
	    		 ps1.setString(11, attendance.getSaturday());
	    		 ps1.setString(12, attendance.getSunday());
	    		 ps1.setString(13, attendance.getTotal_hour());*/
//	    		  int days=0;
//	    		  if(attendance.getDays()!=null){
//			    		 if(!attendance.getDays().equals("")){
//			    			  days=Integer.parseInt(attendance.getDays())+sunday;
//			    	    	 attendance.setDays(String.valueOf(days));
//			    		 }
//			    	 }
	    		 ps1.setString(5, attendance.getNotes());
	    		 ps1.setString(6, attendance.getDate());
	    		 ps1.setString(7, attendance.getDays());
	    		 ps1.setString(8,attendance.getSalaryTotal());
	    		 ps1.setString(9,employeeDAO.getEmployeeUserId(attendance.getEmp_id()));
	    		 ps1.setString(10,attendance.getLeaveday());
	    		ps1.setString(11, attendance.getTotal_hour());
	    		 result=ps1.executeUpdate();
	    	 }
	    	 /*else {
	    		 //add
	    		 String query2="insert into apm_payroll_weeksheet (emp_id, branch_id, reason, month, weekno, monday, tuesday, wednesday, thursday, friday, saturday, sunday, total_hrs, notes, date) values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
	    		 PreparedStatement ps1=connection.prepareStatement(query2);
	    		 ps1.setString(1, attendance.getEmp_id());
	    		 ps1.setString(2, attendance.getBranch_id());
	    		 ps1.setString(3, attendance.getReason());
	    		 ps1.setString(4, attendance.getMonth());
	    		 ps1.setString(5, attendance.getWeekno());
	    		 ps1.setString(6, attendance.getMonday());
	    		 ps1.setString(7, attendance.getTuesday());
	    		 ps1.setString(8, attendance.getWednesday());
	    		 ps1.setString(9, attendance.getThursday()); 
	    		 ps1.setString(10, attendance.getFriday());
	    		 ps1.setString(11, attendance.getSaturday());
	    		 ps1.setString(12, attendance.getSunday());
	    		 ps1.setString(13, attendance.getTotal_hour());
	    		 ps1.setString(14, attendance.getNotes());
	    		 ps1.setString(15, attendance.getDate());
	    		 
	    		 result=ps1.executeUpdate();
	    	 }*/
	    	 
else {
	 //add
	 String query2="insert into apm_payroll_weeksheet (emp_id, branch_id, reason, month, notes, date, days, totalsalary,userid,leaves,total_hrs) values (?,?,?,?,?,?,?,?,?,?,?)";
	 PreparedStatement ps1=connection.prepareStatement(query2);
	 ps1.setString(1, attendance.getEmp_id());
	 ps1.setString(2, attendance.getBranch_id());
	 ps1.setString(3, attendance.getReason());
	 ps1.setString(4, attendance.getMonth());
	 /*ps1.setString(5, attendance.getWeekno());
	 ps1.setString(6, attendance.getMonday());
	 ps1.setString(7, attendance.getTuesday());
	 ps1.setString(8, attendance.getWednesday());
	 ps1.setString(9, attendance.getThursday()); 
	 ps1.setString(10, attendance.getFriday());
	 ps1.setString(11, attendance.getSaturday());
	 ps1.setString(12, attendance.getSunday());
	 ps1.setString(13, attendance.getTotal_hour());*/
//	 int days=0;
//	  if(attendance.getDays()!=null){
//   		 if(!attendance.getDays().equals("")){
//   			  days=Integer.parseInt(attendance.getDays())+sunday;
//   	    	 attendance.setDays(String.valueOf(days));
//   		 }
//   	 }
	 ps1.setString(5, attendance.getNotes());
	 ps1.setString(6, attendance.getDate());
	 ps1.setString(7, attendance.getDays());
	 ps1.setString(8, attendance.getSalaryTotal());
	 ps1.setString(9, employeeDAO.getEmployeeUserId(attendance.getEmp_id()));
	 ps1.setString(10,attendance.getLeaveday());
	 ps1.setString(11, attendance.getTotal_hour());
	 result=ps1.executeUpdate();
}
	    	 
		} catch (Exception e) {

		   e.printStackTrace();
		}	 
		
		
		return result;
	}

	public ArrayList<Attendance> getAllAttendanceList(String month, int weekno,String branchid,Pagination pagination, String searchemp,LoginInfo loginInfo,String numdate,int daysInMonth) {

		ArrayList<Attendance> list=new ArrayList<Attendance>();
		PayrollEmployeeDAO employeeDAO=new JDBCPayrollEmployeeDAO(connection);
        String sql="";		
		try {
			
			if(branchid!="" && branchid!=null){
				sql="select empid,name,branchid from apm_payroll_employee where branchid="+branchid+" ";
			}
			else {
			   sql="select empid,name,branchid from apm_payroll_employee ";
		    }
			sql=sql+" "+"where name like('%"+searchemp+"%')";
			if(!loginInfo.isPayrollaccess()){
				sql=sql+" "+" and userid='"+loginInfo.getUserId()+"'"; 
			}
			 if(pagination!=null)
				{
					sql=pagination.getSQLQuery(sql);
				}
			PreparedStatement ps=connection.prepareStatement(sql);
	        ResultSet rs=ps.executeQuery();
	        Employee employee=new Employee();
	        while(rs.next()){
	        	
	            Attendance attendance=new Attendance();      	
	        	attendance.setEmp_id(rs.getString(1));
	        	attendance.setEmp_name(rs.getString(2));
	        	attendance.setBranch_id(rs.getString(3));
	        	String userid=employeeDAO.getEmployeeUserId(rs.getString(1));
	        	int leavecount=getleavecount(userid,numdate);
	        	attendance=getAttendanceOfMonth(attendance, month, weekno,numdate);
	        	attendance.setMonth(month);
	        	attendance.setWeekno(String.valueOf(weekno));
	        	attendance.setLeaveday(String.valueOf(leavecount));
	        	attendance.setWorkwithleave(daysInMonth);
	        	employee=employeeDAO.getAllDetailsEmployee(rs.getString(1));
	        	attendance.setWorkingdayhour(""+(DateTimeUtils.convertToInteger(employee.getWorkinghourday())*attendance.getWorkwithleave()));
	        	String basicsalary= getSalaryForAttendence(attendance.getEmp_id());
	        	attendance.setBasicsalary(basicsalary);
	        	String salaryTotal =totalSalaryForAttendence(attendance.getEmp_id());
	        	attendance.setSalaryTotal(salaryTotal);
	            list.add(attendance);	 
	        		
	        }
			
		
		} catch (Exception e) {

		   e.printStackTrace();
		}
		
		
		
		return list;
	}
	
	private int getleavecount(String userid, String month) throws ParseException {
		int result=0;
		String startdate=month+"/01";
		String enddate=month+"/31";
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd");
		Date startDate = formatter.parse(startdate);
		Date endDate = formatter.parse(enddate);
		Calendar start = Calendar.getInstance();
		start.setTime(startDate);
		Calendar end = Calendar.getInstance();
		end.setTime(endDate);
		try {
			for (Date date = start.getTime(); start.before(end); start.add(Calendar.DATE,1), date = start.getTime()) {
				 String strDate = formatter.format(date);
			
			
			String sql="select days from payroll_leave where userid='"+userid+"' and  fromdate<='"+strDate+"' AND todate>='"+strDate+"' and status=1 and (leave_type=2 or leave_type=1) ";
			PreparedStatement ps=connection.prepareStatement(sql);
			ResultSet rs=ps.executeQuery();
			
			while(rs.next()){
				
				 result=result+1;   
				
			}
			}
			
		} catch (Exception e) {

		   e.printStackTrace();
		}
	
		return result;

	}

	public Attendance getAttendanceOfMonth(Attendance attendance,String month,int weekno, String numdate) {
		
		try {
			
	         boolean flag=false;
			 /*String sql="select id, branch_id, reason, month, weekno, monday, tuesday, wednesday, thursday, friday, saturday, sunday, total_hrs, notes, date from apm_payroll_weeksheet where month='"+month+"' and weekno="+weekno+" and emp_id="+attendance.getEmp_id()+"";*/	
			 StringBuffer buffer = new StringBuffer();
			
			/* String sql="select id, branch_id, reason, month, notes, date, days, totalsalary from apm_payroll_weeksheet where  emp_id="+attendance.getEmp_id()+"";*/
			 buffer.append("select id, branch_id, reason, month, notes, date, days, totalsalary from apm_payroll_weeksheet where  emp_id="+attendance.getEmp_id()+" ");
			 buffer.append("and month='"+month+"' ");
			 PreparedStatement ps=connection.prepareStatement(buffer.toString());
	         ResultSet set=ps.executeQuery();
	         
			 while(set.next()){
				 flag=true;
				  attendance.setReason(set.getString(3));
				 /* attendance.setMonth(month);*/
				  attendance.setNotes(set.getString(5));
				  attendance.setDate(set.getString(6));
				  attendance.setDays(set.getString(7));
				  attendance.setSalaryTotal(set.getString(8));
				 if(attendance.getMonth()=="" && attendance.getMonth()==null){
					   
					  attendance.setMonth(month);
				  }
				  
				 // attendance.setDate(set.getString(15));
				  
			 }
	          if(!flag){
	        	  String startdate=numdate+"/01";
	      		String enddate=numdate+"/31";
	      		int count=gettotalapprovehour(startdate,enddate,attendance.getEmp_id());
	      		attendance.setDays(""+count);
	          }
			
		} catch (Exception e) {

		   e.printStackTrace();
		} 
		
	
		return attendance;
	}

	private int gettotalapprovehour(String startdate, String enddate, String emp_id) {
int result=0;
		
		try {
			
			String sql="select sum(hours) from payroll_request_timesheet where empid="+emp_id+" and date between '"+startdate+"' and '"+enddate+"' and status=3";
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

	public int getTotalHours(String month, String emp_id) {

		int result=0;
		
		try {
			
			String sql="select sum(total_hrs) from apm_payroll_weeksheet where emp_id="+emp_id+" and month='"+month+"'";
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
	
	public String getSalaryForAttendence(String emp_id) {

		String result="";

		try {
			String sql = "select basic from apm_payroll_salary_master where emp_id='" + emp_id + "'";
			PreparedStatement ps = connection.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {

			result = rs.getString(1);
				

			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return result;
	}
	public String totalSalaryForAttendence(String emp_id){
		
		String result="";
		try {
			String sql="select totalsalary from apm_payroll_weeksheet where emp_id='" + emp_id + "'";
			PreparedStatement ps = connection.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {

			result = rs.getString(1);
				

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
		
	}

	public int getAllAttendanceListcount(String month, int weekno, String branchid,String searchemp, LoginInfo loginInfo, String numdate, int daysInMonth) {
		  String sql="";		
		  int res=0;
			try {
				
				if(branchid!="" && branchid!=null){
					sql="select empid,name,branchid from apm_payroll_employee where branchid="+branchid+" ";
				}
				else {
				   sql="select empid,name,branchid from apm_payroll_employee ";
			    }
				sql=sql+" "+"where name like('%"+searchemp+"%')";
				if(!loginInfo.isPayrollaccess()){
					sql=sql+" "+" and userid='"+loginInfo.getUserId()+"'"; 
				}
				PreparedStatement ps=connection.prepareStatement(sql);
		        ResultSet rs=ps.executeQuery();
		        
		        while(rs.next()){
		        	res=rs.getInt(1);
	}
	
}catch (Exception e) {
	e.printStackTrace();
}
			return res;
	}
	public int getSundays(int month,int year, String[] temp1) 
	{ 
	int count=0; 
	Calendar calendar=Calendar.getInstance(); 
	calendar.set(year, month,1); 
	int days = calendar.getActualMaximum(Calendar.DAY_OF_MONTH); 

	for(int i=1;i<=days;i++) 
	{ 

	calendar.set(year, month, i); 
	int day=calendar.get(Calendar.DAY_OF_WEEK); 
	for (int j = 0; j < temp1.length; j++) {
		int  string = Integer.parseInt(temp1[j]);
		if(day==string) {
			count++ ; 
		}
	}
	

	} 
	return count; 
	}

	public Attendance getListOfAttendance(String today,String userid, String empid) {
		Attendance attendance=new Attendance();
		try {
			
			String sql="select emp_id, userid, date, indatetime, outdatetime, totalhour, status from apm_payroll_daily_attendance where date='"+today+"' and userid='"+userid+"' and emp_id='"+empid+"'";
			PreparedStatement ps=connection.prepareStatement(sql);
			ResultSet rs=ps.executeQuery();
			while (rs.next()) {
				attendance.setEmp_id(rs.getString(1));
				attendance.setUserid(rs.getString(2));
				attendance.setDate(rs.getString(3));
				if(rs.getString(4)!=null){
					if(!rs.getString(4).equals("")){
						String temp[]=rs.getString(4).split(" ");
						attendance.setIndatetime(DateTimeUtils.getCommencingDatePayroll(temp[0])+" "+temp[1]);
					}
				}
				if(rs.getString(5)!=null){
					if(!rs.getString(5).equals("")){
						String temp[]=rs.getString(5).split(" ");
						attendance.setOutdatetime(DateTimeUtils.getCommencingDatePayroll(temp[0])+" "+temp[1]);
					}
				}
				attendance.setTotalhour(rs.getString(6));
				attendance.setStatus(rs.getString(7));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return attendance;
	}

	public int insertPunchIn(String empid, String userid, String intime,String date,int status,String remark, String month_yr) {
		int res=0;
		try {
			String query="insert into apm_payroll_daily_attendance (emp_id, userid,date, indatetime,status,remarkintime,month_year) values (?,?,?,?,?,?)";
			 PreparedStatement ps1=connection.prepareStatement(query);
			 ps1.setString(1, empid);
			 ps1.setString(2, userid);
			 ps1.setString(3, date);
			 ps1.setString(4, intime);
			 ps1.setInt(5, status);
			 ps1.setString(6, remark);
			 ps1.setString(7, month_yr);
			 res=ps1.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return res;
	}

	public boolean checktodaysentry(String today,LoginInfo loginInfo) {
		boolean flag = false;
		try {

			String sql = "select * from apm_payroll_daily_attendance where date='" + today + "' and userid='"+loginInfo.getUserId()+"'";
			PreparedStatement ps = connection.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				flag = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return flag;
	}

	public int updatePunching(String empid, String userid, String intime, String date1, int status,String columnname,String remarkcol, String remark,String difftime,int timin, String month_yr) {
int result=0;
		
		try {
			
			String sql="update apm_payroll_daily_attendance set status=?,"+columnname+"=?,"+remarkcol+"=?,totalhour=?, totalmin='"+timin+"' where date='"+date1+"' and emp_id='"+empid+"' and userid='"+userid+"'";
			PreparedStatement ps=connection.prepareStatement(sql);
			ps.setInt(1, status);
			ps.setString(2, intime);
			ps.setString(3, remark);
			ps.setString(4, difftime);
			result=ps.executeUpdate();
			if(columnname.equals("outdatetime"))
			{
			insertintodaytoday(empid,userid,difftime,date1, timin);
			}
		} catch (Exception e) {

		   e.printStackTrace();
		}
	
		return result;
	}
	private void insertintodaytoday(String empid, String userid, String difftime, String date1, int timin) {
		int res=0;
		try {
			String query="insert into payroll_daytoday_attendance (empid, userid,date,totalhour,totalmin) values (?,?,?,?,?)";
			 PreparedStatement ps1=connection.prepareStatement(query);
			 ps1.setString(1, empid);
			 ps1.setString(2, userid);
			 ps1.setString(3, date1);
			 ps1.setString(4, difftime);
			 ps1.setInt(5, timin);
			 res=ps1.executeUpdate();
			 
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	public String differenceOftwoTimes(String dateStart,String dateStop) {
		String result="";
		//HH converts hour in 24 hours format (0-23), day calculation
		SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");

		Date d1 = null;
		Date d2 = null;

		try {
			d1 = format.parse(dateStart);
			d2 = format.parse(dateStop);

			//in milliseconds
			long diff = d2.getTime() - d1.getTime();

			long diffSeconds = diff / 1000 % 60;
			long diffMinutes = diff / (60 * 1000) % 60;
			long diffHours = diff / (60 * 60 * 1000) % 24;
			long diffDays = diff / (24 * 60 * 60 * 1000);
			String diffhh="";
			String diffmm="";
			if(diffHours<10){
				diffhh="0"+diffHours;
			}else{
				diffhh=""+diffHours;
			}
			if(diffMinutes<10){
				diffmm="0"+diffMinutes;
			}else{
				diffmm=""+diffMinutes;
			}
			result=diffhh+":"+diffmm;
//			System.out.print(diffDays + " days, ");
//			System.out.print(diffHours + " hours, ");
//			System.out.print(diffMinutes + " minutes, ");
//			System.out.print(diffSeconds + " seconds.");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
		
	}

	public ArrayList<Attendance> getAllAttendanceListOfSelf(String attdate, String newmonth, String year,LoginInfo loginInfo,String empname) {
		ArrayList<Attendance> list=new ArrayList<Attendance>();
		newmonth=String.valueOf((Integer.parseInt(newmonth)+1));
		if(Integer.parseInt(newmonth)<10){
			newmonth="0"+newmonth;
		}
		String fromdate=year+"/"+newmonth+"/"+"01";
		String todate=year+"/"+newmonth+"/"+"31";
        StringBuffer buffer  = new StringBuffer();		
		try {
			buffer.append("select apm_payroll_daily_attendance.date, indatetime, outdatetime,remarkintime,remarkouttime,apm_payroll_daily_attendance.id,apm_payroll_employee.name from apm_payroll_daily_attendance"
					+ " left join apm_payroll_employee on apm_payroll_employee.empid=apm_payroll_daily_attendance.emp_id where date between '"+fromdate+"' and '"+todate+"' ");
			if(!loginInfo.isPayrollaccess()){
				buffer.append(" and apm_payroll_daily_attendance.userid='"+loginInfo.getUserId()+"'");
			}
			
				if(!attdate.equals("")){
					buffer.append("and apm_payroll_daily_attendance.date='"+DateTimeUtils.getCommencingDatePayroll(attdate)+"' ");
				}
				if(!empname.equals("")){
					buffer.append(" and apm_payroll_employee.name like '%"+empname+"%'");
				}
			PreparedStatement ps = connection.prepareStatement(buffer.toString());
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				  Attendance attendance=new Attendance();
				  attendance.setDate(DateTimeUtils.getCommencingDatePayroll(rs.getString(1)));
				  if(rs.getString(2)!=null){
				  String tmp[]=rs.getString(2).split(" ");
				  attendance.setIndatetime(tmp[1]);
				  }
				  if(rs.getString(3)!=null){
				  String tmp1[]=rs.getString(3).split(" ");
				 
				  attendance.setOutdatetime(tmp1[1]);
				  }
				  attendance.setRemarkintime(rs.getString(4));
				  attendance.setRemarkouttime(rs.getString(5));
				  attendance.setId(rs.getInt(6));
				  attendance.setEmp_name(rs.getString(7));
				  list.add(attendance);	 
	        		
	        }
		}catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	public ArrayList<Attendance> getMonthWiseReport(String empid, String empname, String month, String year,int daysinmonth,int daysofMonth,Pagination pagination) {
		ArrayList<Attendance> list=new ArrayList<Attendance>();
		PayrollEmployeeDAO employeeDAO=new JDBCPayrollEmployeeDAO(connection);
		String fromdate=year+"/"+month+"/"+"01";
		String todate=year+"/"+month+"/"+"31";
		String sql1="";
		GregorianCalendar date = new GregorianCalendar();      
        int currentmonth = date.get(Calendar.MONTH);
        currentmonth = currentmonth+1;
        int currentyear = Calendar.getInstance().get(Calendar.YEAR);
        StringBuffer sql=new StringBuffer();	
		try {
			
			   sql.append("select empid,name from apm_payroll_employee ");
			   sql.append("inner join apm_payroll_daily_attendance on apm_payroll_daily_attendance.emp_id=apm_payroll_employee.empid ");
			   sql.append("where apm_payroll_daily_attendance.date between '"+fromdate+"' and '"+todate+"' ");
			   if(empid!=null){
				   if(!empid.equals("")){
					   sql.append(" and apm_payroll_employee.empid='"+empid+"'");
				   }
			   }
			   if(empname!=null){
				   if(!empname.equals("")){
					   sql.append(" and apm_payroll_employee.name like '%"+empname+"%'" );
				   }
			   }
			   sql.append("group by empid ");
			   sql1=sql.toString();
			   if(pagination!=null)
				{
					sql1=pagination.getSQLQuery(sql1);
				}
			   PreparedStatement ps = connection.prepareStatement(sql1);

				ResultSet rs = ps.executeQuery();
				while (rs.next()) {
					Attendance attendance=new Attendance();
					
					ArrayList<Attendance> subattendancelist=new ArrayList<Attendance>();
					attendance.setEmp_name(rs.getString(2));
					attendance.setEmp_id(rs.getString(1));
					for (int i = 1; i <= daysinmonth; i++) {
						Attendance attendance2=new Attendance();
						int res=0;
						if(month.equals(String.valueOf(currentmonth)) && year.equals(String.valueOf(currentyear))){
						if(i<=daysofMonth){
							res=getSubAttendanceList(i,month,year,rs.getString(1));
							attendance2.setStatus(String.valueOf(res));
							
						}
						}else{
							res=getSubAttendanceList(i,month,year,rs.getString(1));
							attendance2.setStatus(String.valueOf(res));
						}
						subattendancelist.add(attendance2);
					}
					attendance.setSubattendancelist(subattendancelist);
					list.add(attendance);
				}
		}catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	private int getSubAttendanceList(int i, String month, String year, String empid) {
		ArrayList<Attendance> list=new ArrayList<Attendance>();
		int res=1;
		String temp="";
		if(i<10){
			temp="0"+i;
		}else{
			temp=String.valueOf(i);
		}
		String date=year+"/"+month+"/"+temp;
		try{
			String sql="select * from apm_payroll_daily_attendance where date='"+date+"' and emp_id="+empid+"";
			PreparedStatement ps = connection.prepareStatement(sql);

			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				res=2;
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		return res;
	}

	public int getMonthWiseReportCount(String empid, String empname, String tmp, String year, int daysInMonth,
			int dayOfMonth) {
		String fromdate=year+"/"+tmp+"/"+"01";
		String todate=year+"/"+tmp+"/"+"31";
        StringBuffer sql=new StringBuffer();
        int res=0;
		try {
			
			   sql.append("select empid from apm_payroll_employee ");
			   sql.append("inner join apm_payroll_daily_attendance on apm_payroll_daily_attendance.emp_id=apm_payroll_employee.empid ");
			   sql.append("where apm_payroll_daily_attendance.date between '"+fromdate+"' and '"+todate+"' ");
			   if(empid!=null){
				   if(!empid.equals("")){
					   sql.append(" and apm_payroll_employee.empid='"+empid+"'");
				   }
			   }
			   if(empname!=null){
				   if(!empname.equals("")){
					   sql.append(" and apm_payroll_employee.name like '%"+empname+"%'" );
				   }
			   }
			   sql.append("group by empid ");
			   
			   PreparedStatement ps = connection.prepareStatement(sql.toString());

				ResultSet rs = ps.executeQuery();
				if (rs.last()) {
					res = rs.getRow();
				}
		}catch (Exception e) {
			e.printStackTrace();
		}
		return res;
	}

	public int changepunchtime(String id, String val,String colname,Attendance attendance) {
		int res=0;
		PreparedStatement ps=null;
		try {
			val=attendance.getDate()+" "+val;
			String sql="update apm_payroll_daily_attendance set "+colname+"='"+val+"' where id="+id+"";
			ps=connection.prepareStatement(sql);
			res=ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return res;
	}

	public Attendance getDayDetails(String id) {
		Attendance attendance=new Attendance();
		try{
			String sql="select date from apm_payroll_daily_attendance where id="+id+"";
			PreparedStatement ps = connection.prepareStatement(sql);

			ResultSet rs = ps.executeQuery();
			
			while (rs.next()) {
				
				attendance.setDate(rs.getString(1));
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		return attendance;
	}

	public boolean checktodayChecksentry(String date1, LoginInfo loginInfo) {
		boolean flag = false;
		try {

			String sql = "select * from parent_mobile_attendance where date='" + date1 + "' and userid='"+loginInfo.getUserId()+"'";
			PreparedStatement ps = connection.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				flag = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return flag;
	}

	public int inserCheckIn(String empid, String userid, String intime, String date1, int i, String remark) {
		int res=0;
		try {
			String query="insert into parent_mobile_attendance (empid, userid,date, indatetime,status,totalhour) values (?,?,?,?,?,?)";
			 PreparedStatement ps1=connection.prepareStatement(query);
			 ps1.setString(1, empid);
			 ps1.setString(2, userid);
			 ps1.setString(3, date1);
			 ps1.setString(4, intime);
			 ps1.setInt(5, i);
			 ps1.setString(6, remark);
			 res=ps1.executeUpdate();
			 if(res>0){
					
				   ResultSet rs1= ps1.getGeneratedKeys(); 
				   
				   while(rs1.next()) {
					   res=rs1.getInt(1);
				   }
				   
			}
			insertIntoChild(empid, userid, intime, date1 ,remark,res,i) ;
			 
		} catch (Exception e) {
			e.printStackTrace();
		}
		return res;
	}

	private void insertIntoChild(String empid, String userid, String intime, String date1, String remark, int res, int i) {
		int res1=0;
		try {
			String query="insert into child_mobile_attendance (empid, userid,date, indatetime,inremark,totalhour,parentid,status) values (?,?,?,?,?,?,?,?)";
			 PreparedStatement ps1=connection.prepareStatement(query);
			 ps1.setString(1, empid);
			 ps1.setString(2, userid);
			 ps1.setString(3, date1);
			 ps1.setString(4, intime);
			 ps1.setString(5, remark);
			 ps1.setString(6, "0");
			 ps1.setInt(7, res);
			 ps1.setInt(8, i);
			 res1=ps1.executeUpdate();
		}catch (Exception e) {
		e.printStackTrace();
		}
		
	}

	public int getChildAttendanceid(String date1, String empid, String userid) {
		int res = 0;
		try {

			String sql = "select id from child_mobile_attendance where date='" + date1 + "' and userid='"+userid+"' and empid="+empid+"  order by id desc limit 1 ";
			PreparedStatement ps = connection.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				res = rs.getInt(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return res;
	}

	public int updateCheckin(String empid, String userid, String intime, String date1, String status, String columnname,
			String remarkcol, String remark, int childid,int parentid,String longlat) {
		int result=0;
		
		try {
			//send parameter parent id
			String sql="update child_mobile_attendance set status=?,"+columnname+"=?,"+remarkcol+"=?,inlonglat=? where date='"+date1+"' and empid='"+empid+"' and userid='"+userid+"' and id="+childid+"";
			PreparedStatement ps=connection.prepareStatement(sql);
			ps.setString(1, status);
			ps.setString(2, intime);
			ps.setString(3, remark);
			ps.setString(4, longlat);
			result=ps.executeUpdate();
			updateParentAttendance(empid,userid,intime,childid,date1,status,parentid);
		} catch (Exception e) {

		   e.printStackTrace();
		}
	
		return result;
	}

	private void updateParentAttendance(String empid, String userid, String outtime, int res, String date1, String status, int parentid) {
		int res1=0;
		PreparedStatement ps=null;
		try {
			 String indatetime= getParentindate(parentid);
			 StringBuffer   buffer=new StringBuffer();
			buffer.append("update parent_mobile_attendance set ");
			if(indatetime==null || indatetime.equals("")){
				buffer.append("indatetime='"+outtime+"', ");
			}
			buffer.append("status="+status+" where id="+parentid+" and date='"+date1+"' ");
			ps=connection.prepareStatement(buffer.toString());
			res1=ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	private String getParentindate(int parentid) {
		String res = "";
		try {

			String sql = "select indatetimw from parent_mobile_attendance where id="+parentid+" ";
			PreparedStatement ps = connection.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				res = rs.getString(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return res;
	}

	public int insertChildCheckIn(String empid, String userid, String intime, String date1, int i, String remark,int parentid,String longlat) {
		int res1=0;
		try {
			String query="insert into child_mobile_attendance (empid, userid,date, indatetime,inremark,totalhour,parentid,status,inlonglat) values (?,?,?,?,?,?,?,?,?)";
			 PreparedStatement ps1=connection.prepareStatement(query);
			 ps1.setString(1, empid);
			 ps1.setString(2, userid);
			 ps1.setString(3, date1);
			 ps1.setString(4, intime);
			 ps1.setString(5, remark);
			 ps1.setString(6, "0");
			 ps1.setInt(7, parentid);
			 ps1.setInt(8, i);
			 ps1.setString(9, longlat);
			 res1=ps1.executeUpdate();
		}catch (Exception e) {
		e.printStackTrace();
		}
		return res1;
	}

	public int getParentId(String date1, LoginInfo loginInfo, String empid) {
		int res = 0;
		try {

			String sql = "select id from parent_mobile_attendance where date='" + date1 + "' and userid='"+loginInfo.getUserId()+"' and empid="+empid+" ";
			PreparedStatement ps = connection.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				res = rs.getInt(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return res;
	}

	public ArrayList<Attendance> getAllAttendanceListOfemp(String attdate, String newmonth, String year,
			LoginInfo loginInfo, String empname,String inpdate) {
		ArrayList<Attendance> list=new ArrayList<Attendance>();
		
	/*	String fromdate="";
		String todate="";*/
		/*if(DateTimeUtils.isNull(inpdate).equals("")){
			
			newmonth=String.valueOf((Integer.parseInt(newmonth)+1));
			if(Integer.parseInt(newmonth)<10){
				newmonth="0"+newmonth;
			}
			fromdate=year+"/"+newmonth+"/"+"01";
			todate=year+"/"+newmonth+"/"+"31";
		}
		else{
			fromdate=inpdate;
			todate=fromdate;
		}*/
		
        StringBuffer buffer  = new StringBuffer();		
		try {
			buffer.append("select child_mobile_attendance.date, indatetime, outdatetime,inremark,outremark,child_mobile_attendance.id,apm_payroll_employee.name,inlonglat, outlonglat,child_mobile_attendance.totalhour from child_mobile_attendance"
					+ " left join apm_payroll_employee on apm_payroll_employee.empid=child_mobile_attendance.empid where date = '"+attdate+"' ");
			if(!loginInfo.isPayrollaccess()){
				buffer.append(" and child_mobile_attendance.userid='"+loginInfo.getUserId()+"'");
			}
			
				/*if(!attdate.equals("")){
					buffer.append("and child_mobile_attendance.date='"+attdate+"' ");
				}*/
				if(!empname.equals("")){
					buffer.append(" and apm_payroll_employee.name like '%"+empname+"%'");
				}
			PreparedStatement ps = connection.prepareStatement(buffer.toString());
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				  Attendance attendance=new Attendance();
				  attendance.setDate(DateTimeUtils.getCommencingDatePayroll(rs.getString(1)));
				  if(rs.getString(2)!=null){
				  String tmp[]=rs.getString(2).split(" ");
				  attendance.setIndatetime(tmp[1]);
				  }
				  if(rs.getString(3)!=null){
				  String tmp1[]=rs.getString(3).split(" ");
				 
				  attendance.setOutdatetime(tmp1[1]);
				  }
				  attendance.setRemarkintime(rs.getString(4));
				  attendance.setRemarkouttime(rs.getString(5));
				  attendance.setId(rs.getInt(6));
				  attendance.setEmp_name(rs.getString(7));
				  attendance.setInlonglat(rs.getString(8));
				  attendance.setOutlonglat(rs.getString(9));
				  attendance.setTotal_hour(rs.getString(10));
				  list.add(attendance);	 
	        		
	        }
		}catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	public Attendance getListOfMobileAttendance(String today, String userid, String empid) {
		Attendance attendance=new Attendance();
		try {
			
			String sql="select child_mobile_attendance.empid, child_mobile_attendance.userid, child_mobile_attendance.date, child_mobile_attendance.indatetime, "
					+ "child_mobile_attendance.outdatetime, child_mobile_attendance.totalhour, child_mobile_attendance.status, parent_mobile_attendance.status"
					+ " from child_mobile_attendance "
					+ "left join parent_mobile_attendance on parent_mobile_attendance.id=child_mobile_attendance.parentid "
					+ " where child_mobile_attendance.date='"+today+"' and child_mobile_attendance.userid='"+userid+"' and child_mobile_attendance.empid='"+empid+"' "
							+ "order by child_mobile_attendance.id desc limit 0,1";
			PreparedStatement ps=connection.prepareStatement(sql);
			ResultSet rs=ps.executeQuery();
			while (rs.next()) {
				attendance.setEmp_id(rs.getString(1));
				attendance.setUserid(rs.getString(2));
				attendance.setDate(rs.getString(3));
				if(rs.getString(4)!=null){
					if(!rs.getString(4).equals("")){
						String temp[]=rs.getString(4).split(" ");
						attendance.setIndatetime(DateTimeUtils.getCommencingDatePayroll(temp[0])+" "+temp[1]);
					}
				}
				if(rs.getString(5)!=null){
					if(!rs.getString(5).equals("")){
						String temp[]=rs.getString(5).split(" ");
						attendance.setOutdatetime(DateTimeUtils.getCommencingDatePayroll(temp[0])+" "+temp[1]);
					}
				}
				attendance.setTotalhour(rs.getString(6));
				attendance.setStatus(rs.getString(8));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return attendance;
	}

	public int updateCheckInStatus(int parentid,  int sts) {
		int res1=0;
		PreparedStatement ps=null;
		try {
			String sql="update parent_mobile_attendance set status="+sts+" where id="+parentid+"";
			ps=connection.prepareStatement(sql);
			res1=ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return res1;
	}

	public String getoutdatetime(int childid) {
		String res = "";
		try {

			String sql = "select status from child_mobile_attendance where id="+childid+" ";
			PreparedStatement ps = connection.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				res = DateTimeUtils.isNull(rs.getString(1));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return res;
	}

	public int updateCheckout(String empid, String userid, String intime, String date1, String status, String string,
			String string2, String remark, int childid, int parentid,String longlat,String totalhour) {
int result=0;
		
		try {
			//send parameter parent id
			String sql="update child_mobile_attendance set status=?,"+string+"=?,"+string2+"=?,outlonglat=?,totalhour=? where date='"+date1+"' and empid='"+empid+"' and userid='"+userid+"' and id="+childid+"";
			PreparedStatement ps=connection.prepareStatement(sql);
			ps.setString(1, status);
			ps.setString(2, intime);
			ps.setString(3, remark);
			ps.setString(4, longlat);
			ps.setString(5, totalhour);
			result=ps.executeUpdate();
			String prevparenttotalhour=getChildTotalHour(parentid,date1);
			String calctotalhour="";
			if(!prevparenttotalhour.equals("")){
				calctotalhour=getTwoTimeTotal(totalhour,prevparenttotalhour);
			}else{
				calctotalhour=totalhour;
			}
			
			updateParentAttendanceout(empid,userid,intime,childid,date1,status,parentid,calctotalhour);
		} catch (Exception e) {

		   e.printStackTrace();
		}
	
		return result;
	}

	private String getTwoTimeTotal(String time1, String time2) {
		 int index1 = time1.indexOf(":");
		 int time1hrs = Integer.parseInt(time1.substring(0,index1));
		 int time1min = Integer.parseInt(time1.substring(index1+1));
		 int index2 = time2.indexOf(":");
		 int time2hrs = Integer.parseInt(time2.substring(0,index2));
		 int time2min = Integer.parseInt(time2.substring(index2+1));
		 int time3min = time1min + time2min;
		 int time3hrs = time1hrs + time2hrs;
		 time3hrs = time3hrs + (time3min/60);
		 time3min = time3min%60;
		 String time3 = time3hrs+":"+time3min;
		 System.out.println(time3);
		return time3;
	}

	private String getChildTotalHour(int parentid, String date1) {
		String res = "";
		try {

			String sql = "select totalhour from parent_mobile_attendance where id="+parentid+" and date='"+date1+"' ";
			PreparedStatement ps = connection.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				res=rs.getString(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return res;
	}

	private void updateParentAttendanceout(String empid, String userid, String intime, int childid, String date1,
			String status, int parentid, String parenttotalhour) {
		int res1=0;
		PreparedStatement ps=null;
		try {
			String sql="update parent_mobile_attendance set outdatetime='"+intime+"',status="+status+", totalhour='"+parenttotalhour+"' where id="+parentid+" and date='"+date1+"'";
			ps=connection.prepareStatement(sql);
			res1=ps.executeUpdate();
			boolean flag= checkentry(empid,userid,date1);
			if(!flag){
				insertintodaytoday(empid, userid, parenttotalhour, date1,0);
			}else{
				updatedaytoday(empid,userid,parenttotalhour,date1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
	}

	private void updatedaytoday(String empid, String userid, String parenttotalhour, String date1) {
		PreparedStatement ps=null;
		try {
			String sql="update payroll_daytoday_attendance set totalhour='"+parenttotalhour+"' where  empid="+empid+" and userid='"+userid+"' and date='"+date1+"'";
			ps=connection.prepareStatement(sql);
			int res=ps.executeUpdate();
		}catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	private boolean checkentry(String empid, String userid, String date1) {
		boolean flag=false;
		try {
			String sql="select * from payroll_daytoday_attendance where empid="+empid+" and userid='"+userid+"' and date='"+date1+"'";
			PreparedStatement ps=connection.prepareStatement(sql);
			ResultSet rs=ps.executeQuery();
			if(rs.next()){
				flag=true;
			}
		} catch (Exception e) {
		}
	return  flag;	
	}

	public ArrayList<Attendance> getmobileattendance(String empsearch, String fromDate, String toDate,
			Pagination pagination) {
		ArrayList<Attendance> list=new ArrayList<Attendance>();
        StringBuffer buffer  = new StringBuffer();		
		try {
			buffer.append("select id, parent_mobile_attendance.empid, parent_mobile_attendance.userid, indatetime, outdatetime, totalhour, parent_mobile_attendance.status, date,apm_payroll_employee.name,parent_mobile_attendance.totalhour from parent_mobile_attendance ");
			buffer.append("inner join apm_payroll_employee on apm_payroll_employee.empid =parent_mobile_attendance.empid ")	;	
			buffer.append("where parent_mobile_attendance.date between '"+fromDate+"' and '"+toDate+"' ");
			if(empsearch!=null){
				if(!empsearch.equals("")){
					buffer.append("and apm_payroll_employee.name like ('%"+empsearch+"%')");
				}
			}
			PreparedStatement ps = connection.prepareStatement(buffer.toString());
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				  Attendance attendance=new Attendance();
				  attendance.setId(rs.getInt(1));
				  attendance.setEmp_id(rs.getString(2)); 
				  attendance.setUserid(rs.getString(3));
				  attendance.setIndatetime(DateTimeUtils.isNull(rs.getString(4)));
				  attendance.setOutdatetime(DateTimeUtils.isNull(rs.getString(5)));
				  attendance.setTotalhour(DateTimeUtils.isNull(rs.getString(6)));
				  attendance.setDate(rs.getString(8));
				  attendance.setEmp_name(rs.getString(9));
				  attendance.setTotalhour(rs.getString(10));
				  ArrayList<Attendance> allcheckinlist=getAllcheckIn(rs.getString(1),fromDate,toDate);
				  attendance.setAllcheckinlist(allcheckinlist);
				  list.add(attendance);	 
	        		
	        }
		}catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	private ArrayList<Attendance> getAllcheckIn(String parentid, String fromDate, String toDate) {
		PreparedStatement preparedStatement = null;
		ArrayList<Attendance>list = new ArrayList<Attendance>();
		String sql = "SELECT indatetime, outdatetime, totalhour, inlonglat, outlonglat, inremark, outremark, date, totalhour FROM child_mobile_attendance "
				+ "where parentid = "+parentid+" and date between '"+fromDate+"' and '"+toDate+"' ";
		
		try{
			
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()){
				Attendance attendance=new Attendance();
				attendance.setIndatetime(rs.getString(1));
				attendance.setOutdatetime(rs.getString(2));
				attendance.setTotalhour(rs.getString(3));
				attendance.setInlonglat(rs.getString(4));
				attendance.setOutlonglat(rs.getString(5));
				attendance.setRemarkintime(rs.getString(6));
				attendance.setRemarkouttime(rs.getString(7));
				attendance.setDate(rs.getString(8));
				attendance.setTotalhour(rs.getString(9));
				list.add(attendance);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return list;
	}

	public Attendance getcheckInDetails(int childid,int parentid,String date1) {
		PreparedStatement preparedStatement = null;
		Attendance attendance=new Attendance();
		String sql = "SELECT indatetime, outdatetime, totalhour, inlonglat, outlonglat, inremark, outremark, date FROM child_mobile_attendance "
				+ "where parentid = "+parentid+" and id="+childid+" and date='"+date1+"' ";
		
		try{
			
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
		
			while(rs.next()){
				
				attendance.setIndatetime(rs.getString(1));
				attendance.setOutdatetime(rs.getString(2));
				attendance.setTotalhour(rs.getString(3));
				attendance.setInlonglat(rs.getString(4));
				attendance.setOutlonglat(rs.getString(5));
				attendance.setRemarkintime(rs.getString(6));
				attendance.setRemarkouttime(rs.getString(7));
				attendance.setDate(rs.getString(8));
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return attendance;
	}

	public String getintimefortotal(String empid, String userid, String date1) {
		String indatetime="";
		try {
			String sql="select indatetime from apm_payroll_daily_attendance where date='"+date1+"' and emp_id='"+empid+"' and userid='"+userid+"'";
			PreparedStatement ps= connection.prepareStatement(sql);
			ResultSet rs=ps.executeQuery();
			while (rs.next()) {
				indatetime=rs.getString(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return indatetime;
	}

	public Attendance getpayroll_daytoday_attendance(String empid, String strDate) {
		PreparedStatement preparedStatement = null;
		Attendance attendance=new Attendance();
		String sql = "SELECT id, empid, userid, date, totalhour FROM payroll_daytoday_attendance where empid='"+empid+"' and date='"+strDate+"'";
		
		try{
			
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
		
			while(rs.next()){
				
				attendance.setId(rs.getInt(1));
				attendance.setEmp_id(rs.getString(2));
				attendance.setUserid(rs.getString(3));
				attendance.setDate(rs.getString(4));
				attendance.setTotalhour(rs.getString(5));
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return attendance;
	}

	public int insertIntopayroll_parent_request_timesheet(Attendance attendance1) {
		int res1=0;
		try {
			String query="insert into payroll_parent_request_timesheet (empid, userid, date, fromdate, todate, status,totalhour) values (?,?,?,?,?,?,?)";
			 PreparedStatement ps1=connection.prepareStatement(query);
			 ps1.setString(1, attendance1.getEmp_id());
			 ps1.setString(2, attendance1.getUserid());
			 ps1.setString(3, attendance1.getDate());
			 ps1.setString(4, attendance1.getFromdate());
			 ps1.setString(5, attendance1.getTodate());
			 ps1.setString(6, attendance1.getStatus());
			 ps1.setString(7, attendance1.getTotal_hour());
			 res1=ps1.executeUpdate();
			 if(res1>0){
					
				   ResultSet rs1= ps1.getGeneratedKeys(); 
				   
				   while(rs1.next()) {
					   res1=rs1.getInt(1);
				   }
				   
			}
		}catch (Exception e) {
		e.printStackTrace();
		}
		return res1;
	}

	public int insertintopayroll_request_timesheet(Attendance attendance) {
		int res1=0;
		try {
			String query="insert into payroll_request_timesheet (parentid, empid, userid, date, hours, commencing) values (?,?,?,?,?,?)";
			 PreparedStatement ps1=connection.prepareStatement(query);
			 ps1.setString(1, attendance.getParentid());
			 ps1.setString(2, attendance.getEmp_id());
			 ps1.setString(3, attendance.getUserid());
			 ps1.setString(4, attendance.getDate());
			 ps1.setString(5, attendance.getValues());
			 ps1.setString(6, attendance.getCommencing());
			 res1=ps1.executeUpdate();
			 
		}catch (Exception e) {
		e.printStackTrace();
		}
		return res1;
	}

	public ArrayList<Attendance> getrequesttimesheetdashboard(String fromDate, String toDate, String userid,LoginInfo loginInfo) {
		PreparedStatement preparedStatement = null;
		fromDate=fromDate+" "+"00:00:00";
		toDate=toDate+" "+"23:59:59";
		ArrayList<Attendance>list = new ArrayList<Attendance>();
		PayrollEmployeeDAO employeeDAO=new JDBCPayrollEmployeeDAO(connection);
		String sql="";
		
		sql = "SELECT id , empid, userid, date, fromdate, todate, status FROM payroll_parent_request_timesheet "
				+ "where date between '"+fromDate+"' and '"+toDate+"' and userid='"+userid+"' ";
		if(loginInfo.isPayrollaccess()){
			sql = "SELECT id , empid, userid, date, fromdate, todate, status FROM payroll_parent_request_timesheet "
					+ "where date between '"+fromDate+"' and '"+toDate+"' and status>=2";
		}
		try{
			
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()){
				Attendance attendance=new Attendance();
				attendance.setId(rs.getInt(1));
				attendance.setEmp_id(rs.getString(2));
				attendance.setUserid(rs.getString(3));
				attendance.setDate(rs.getString(4));
				attendance.setFromdate(rs.getString(5));
				attendance.setTodate(rs.getString(6));
				attendance.setStatus(rs.getString(7));
				attendance.setEmp_name(employeeDAO.getEmployeeName(rs.getString(2)));
				list.add(attendance);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return list;
	}

	public Attendance getpayroll_child_request_attendance(String empid, String strDate,String parentid) {
		PreparedStatement preparedStatement = null;
		Attendance attendance=new Attendance();
		String sql = "SELECT id, parentid, empid, userid, date, hours, commencing FROM payroll_request_timesheet "
				+ "where empid='"+empid+"' and date='"+strDate+"' and parentid='"+parentid+"'";
		
		try{
			
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
		
			while(rs.next()){
				
				attendance.setId(rs.getInt(1));
				attendance.setParentid(rs.getString(2));
				attendance.setEmp_id(rs.getString(3));
				attendance.setUserid(rs.getString(4));
				attendance.setDate(rs.getString(5));
				attendance.setTotalhour(rs.getString(6));
				attendance.setCommencing(rs.getString(7));
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return attendance;
	}

	public Attendance getparentRequestTimesheet(String parentid) {
		PreparedStatement preparedStatement = null;
		Attendance attendance=new Attendance();
		String sql = "SELECT id, empid, userid, date, fromdate, todate, status , approvedby, approveremark, approvedate, userremark,totalhour FROM payroll_parent_request_timesheet "
				+ "where  id='"+parentid+"'";
		
		try{
			
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
		
			while(rs.next()){
				
				attendance.setId(rs.getInt(1));
				attendance.setEmp_id(rs.getString(2));
				attendance.setUserid(rs.getString(3));
				attendance.setDate(rs.getString(4));
				attendance.setFromdate(rs.getString(5));
				attendance.setTodate(rs.getString(6));
				attendance.setStatus(rs.getString(7));
				attendance.setApprovedby(rs.getString(8));
				attendance.setApproveremark(rs.getString(9));
				attendance.setApprovedate(rs.getString(10));
				attendance.setUserremark(rs.getString(11));
				attendance.setTotal_hour(rs.getString(12));
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return attendance;
	}

	public int updatepayroll_request_timesheet(Attendance attendance) {
		PreparedStatement ps=null;
		int res=0;
		try {
			String sql="update payroll_request_timesheet set hours='"+attendance.getValues()+"',status="+attendance.getStatus()+" where  id="+attendance.getId()+" and date='"+attendance.getDate()+"'";
			ps=connection.prepareStatement(sql);
			res=ps.executeUpdate();
		}catch (Exception e) {
			e.printStackTrace();
		}
		return res;
	}

	public int updatepayrollparentstatus(String parentid,String sts) {
		PreparedStatement ps=null;
		int res=0;
		try {
			String sql="update payroll_parent_request_timesheet set status='"+sts+"' where  id='"+parentid+"'";
			ps=connection.prepareStatement(sql);
			res=ps.executeUpdate();
		}catch (Exception e) {
			e.printStackTrace();
		}
		return res;
	}

	public int updremarknduserid(String parentid, String notes, String colname,String totalhour) {
		PreparedStatement ps=null;
		int res=0;
		try {
			String sql="update payroll_parent_request_timesheet set "+colname+"='"+notes+"',totalhour='"+totalhour+"' where  id='"+parentid+"'";
			ps=connection.prepareStatement(sql);
			res=ps.executeUpdate();
		}catch (Exception e) {
			e.printStackTrace();
		}
		return res;
	}

	public int updremarknduseriddate(String parentid, String notes, String colname, String userId, String date,String totalhour) {
		PreparedStatement ps=null;
		int res=0;
		try {
			String sql="update payroll_parent_request_timesheet set "+colname+"='"+notes+"',approvedby='"+userId+"', "
					+ "approveremark='"+notes+"', approvedate='"+date+"', totalhour='"+totalhour+"' where  id='"+parentid+"'";
			ps=connection.prepareStatement(sql);
			res=ps.executeUpdate();
		}catch (Exception e) {
			e.printStackTrace();
		}
		return res;
	}

	public int getleavestatus(String fromDate, String toDate, String empid) {
		int flag=0;
		
		try {
			String sql="SELECT id FROM payroll_leave WHERE fromdate<='"+fromDate+"' AND todate>='"+fromDate+"'  and empid='"+empid+"' and status=1";
			PreparedStatement ps= connection.prepareStatement(sql);
			ResultSet rs=ps.executeQuery();
			while (rs.next()) {
				flag=rs.getInt(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}

	public boolean getholidaystatus(String strDate) {
boolean flag=false;
		
		try {
			String sql="SELECT * FROM apm_payroll_holiday WHERE date='"+strDate+"'";
			PreparedStatement ps= connection.prepareStatement(sql);
			ResultSet rs=ps.executeQuery();
			while (rs.next()) {
				flag=true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}

	@Override
	public int insertBackdatepunch(String empid, String userid, String punchdate, int status,
			String indatetime, String outdatetime, String remark, String difftime, int timin,String month_yr) {
		
		int res=0;
		try {
			String query="insert into apm_payroll_daily_attendance (emp_id, userid,date, indatetime,outdatetime,status,totalhour,totalmin,month_year) values (?,?,?,?,?,?,?,?,?)";
			 PreparedStatement ps1=connection.prepareStatement(query);
			 ps1.setString(1, empid);
			 ps1.setString(2, userid);
			 ps1.setString(3, punchdate);
			 ps1.setString(4, indatetime);
			 ps1.setString(5, outdatetime);
			 ps1.setInt(6, status);
			 ps1.setString(7, difftime);
			 ps1.setInt(8, timin);
			 ps1.setString(9, month_yr);
			 res=ps1.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return res;
	}

	@Override
	public String getSalaryDatebyid() {
		String date="";
		try {
			String sql="SELECT salary_date FROM apm_user WHERE id='1'";
			PreparedStatement ps= connection.prepareStatement(sql);
			ResultSet rs=ps.executeQuery();
			while (rs.next()) {
				date=rs.getString(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return date;
	}

	@Override
	public double getSumoftime(String sal_date, String new_sal_date, String empid) {
		double total=0.0;
		sal_date=DateTimeUtils.getCommencingDate3(sal_date);
		new_sal_date=DateTimeUtils.getCommencingDate3(new_sal_date);
		try {
			String sql="select sum(totalmin) from apm_payroll_daily_attendance where date between '"+sal_date+"' and '"+new_sal_date+"'  and emp_id='"+empid+"'";
			PreparedStatement ps= connection.prepareStatement(sql);
			ResultSet rs=ps.executeQuery();
			while (rs.next()) {
				total=rs.getDouble(1);
			}
					
		} catch (Exception e) {
			e.printStackTrace();
		}
		return total;
	}

	@Override
	public int insertDaysinworksheet(String new_sal_date, double totaldays, String userid, String empid, String month,double totalmin) {
		int res=0;
		try {
			String sql="insert into apm_payroll_weeksheet(emp_id,  month, date, days, userid,totalmin) values(?,?,?,?,?,?)";
			PreparedStatement ps= connection.prepareStatement(sql);
			ps.setString(1, empid);
			ps.setString(2, month);
			ps.setString(3, new_sal_date);
			ps.setDouble(4, totaldays);
			ps.setString(5, userid);
			ps.setDouble(6, totalmin);
			res=ps.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return res;
	}

}	
