package com.apm.Payroll.web.action;

import java.sql.Connection;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.DateFormatSymbols;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.a.a.a.g.m.m;
import com.apm.Accounts.eu.bi.AccountsDAO;
import com.apm.Accounts.eu.blogic.jdbc.JDBCAccountsDAO;
import com.apm.Accounts.eu.entity.Accounts;
import com.apm.DiaryManagement.eu.bi.CompleteAptmDAO;
import com.apm.DiaryManagement.eu.blogic.jdbc.JDBCCompleteAptmDAO;
import com.apm.DiaryManagement.eu.entity.CompleteAppointment;
import com.apm.DiaryManagement.web.action.EmbeddedImageEmailUtil;
import com.apm.Inventory.eu.bi.InventoryProductDAO;
import com.apm.Inventory.eu.blogic.jdbc.JDBCInventoryProductDAO;
import com.apm.Log.eu.entity.EmailLetterLog;
import com.apm.Payroll.eu.bi.AttendanceDAO;
import com.apm.Payroll.eu.bi.PayrollEmployeeDAO;
import com.apm.Payroll.eu.bi.PayrollMasterDAO;
import com.apm.Payroll.eu.blogic.jdbc.JDBCAttendanceDAO;
import com.apm.Payroll.eu.blogic.jdbc.JDBCPayrollEmployeeDAO;
import com.apm.Payroll.eu.blogic.jdbc.JDBCPayrollMasterDAO;
import com.apm.Payroll.eu.entity.Attendance;
import com.apm.Payroll.eu.entity.Employee;
import com.apm.Payroll.eu.entity.Payroll;
import com.apm.Payroll.web.form.AttendanceForm;
import com.apm.Registration.eu.entity.UserProfile;
import com.apm.common.eu.blogic.jdbc.Connection_provider;
import com.apm.common.utils.DateTimeUtils;
import com.apm.common.utils.Pagination;
import com.apm.common.web.action.BaseAction;
import com.apm.common.web.common.helper.LoginHelper;
import com.apm.common.web.common.helper.LoginInfo;
import com.apm.common.web.utils.PopulateList;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.Preparable;

public class AttendanceAction extends BaseAction implements ModelDriven<AttendanceForm>,Preparable {

	AttendanceForm attendanceForm=new AttendanceForm();
	
	HttpServletRequest request=ServletActionContext.getRequest();
	HttpServletResponse response=ServletActionContext.getResponse();
	HttpSession session=request.getSession(true);
	LoginInfo loginInfo=LoginHelper.getLoginInfo(request); 
	Pagination pagination = new Pagination(25, 1);

	
	
	public Pagination getPagination() {
		return pagination;
	}


	public void setPagination(Pagination pagination) {
		this.pagination = pagination;
	}


	public AttendanceForm getModel() {
		return attendanceForm;
	}

	
	@Override
	public String execute() throws Exception {

		if(!verifyLogin(request)){
	
			 return "login";
		}
		
		Connection connection=null;
		try {
			connection=Connection_provider.getconnection();
			AttendanceDAO attendanceDAO=new JDBCAttendanceDAO(connection);
			PayrollMasterDAO payrollMasterDAO = new JDBCPayrollMasterDAO(connection);
            String branchid=attendanceForm.getBranch();
            String searchemp=attendanceForm.getEmpnamesearch();
			Calendar calendar=Calendar.getInstance(Locale.getDefault());
			SimpleDateFormat sdf=new SimpleDateFormat("MMM-yyyy");
			Date date=calendar.getTime();
			String month="";
			
			Calendar cal=Calendar.getInstance(Locale.getDefault());
			SimpleDateFormat sdf1=new SimpleDateFormat("yyyy");
			SimpleDateFormat sdf2=new SimpleDateFormat("MM");
			Date date1=cal.getTime();
			String year=sdf1.format(date1);
			String todaymonth=sdf2.format(date1);
			String newmonth=request.getParameter("month");
			String year1=request.getParameter("year");
			if(year1==null){
				 year=sdf1.format(date1);
			}else{
				year=year1;
			}
			if(newmonth == null){
				month= DateTimeUtils.getMonthName(Integer.parseInt(todaymonth));
				month=month+"-"+year;
				newmonth=String.valueOf(Integer.parseInt(todaymonth)-1);
			}else{
				month=newmonth;
				
				if (newmonth.equals("0")) {
					month="January"+ "-" + year;
				} else if (newmonth.equals("1")) {
					month="February"+ "-" + year;
				} else if (newmonth.equals("2")) {
					month="March"+ "-" + year;
				} else if (newmonth.equals("3")) {
					month="April"+ "-" + year;
				} else if (newmonth.equals("4")) {
					month="May"+ "-" + year;
				} else if (newmonth.equals("5")) {
					month="June"+ "-" + year;
				} else if (newmonth.equals("6")) {
					month="July"+ "-" + year;
				} else if (newmonth.equals("7")) {
					month="August"+ "-" + year;
				} else if (newmonth.equals("8")) {
					month="September"+ "-" + year;
				} else if (newmonth.equals("9")) {
					month="October"+ "-" + year;
				} else if (newmonth.equals("10")) {
					month="November"+ "-" + year;
				} else if (newmonth.equals("11")) {
					month="December"+ "-" + year;
				}
			}
			int monthwithdigit=Integer.parseInt(newmonth)+1;
			String monthwithzero="";
			if(monthwithdigit<=9){
				monthwithzero="0"+monthwithdigit;
			}else{
				monthwithzero=""+monthwithdigit;
			}
			String numdate=year+"/"+monthwithzero;
			int weekno=calendar.get(Calendar.WEEK_OF_MONTH);
			SimpleDateFormat dateFormat=new SimpleDateFormat("dd-MM-yyyy");
			String fromdate=dateFormat.format(date);
			String emp_id=request.getParameter("selectedid");
		/*	String month = payrollMasterDAO.monthforSalarySlip(emp_id);*/
			String filter_status = request.getParameter("filter_status");
			
			if(searchemp==null){
				searchemp="";
			}
			String[] temp = month.split("-");

			int iyear = Integer.parseInt(temp[1]);
			int imonth = Integer.parseInt(newmonth);
			int idays = 1;
			if (temp[0].equals("January")) {
				filter_status="0";
			} else if (temp[0].equals("February")) {
				filter_status="1";
			} else if (temp[0].equals("March")) {
				filter_status="2";
			} else if (temp[0].equals("April")) {
				filter_status="3";
			} else if (temp[0].equals("May")) {
				filter_status="4";
			} else if (temp[0].equals("June")) {
				filter_status="5";
			} else if (temp[0].equals("July")) {
				filter_status="6";
			} else if (temp[0].equals("August")) {
				filter_status="7";
			} else if (temp[0].equals("September")) {
				filter_status="8";
			} else if (temp[0].equals("October")) {
				filter_status="9";
			} else if (temp[0].equals("November")) {
				filter_status="10";
			} else if (temp[0].equals("December")) {
				filter_status="11";
			}
			String result=payrollMasterDAO.getweekdays();
			int sunday=0;
			if(result==null){
				result="0";
			}
			if(result.equals("")){
				result="0";
			}
			if(result!=null){
			String temp1[]=result.split(",");
			sunday=getSundays(iyear,imonth ,temp1);
			}
			Calendar mycal = new GregorianCalendar(iyear, imonth, idays);
			// Get the number of days in that month
			int daysInMonth = mycal.getActualMaximum(Calendar.DAY_OF_MONTH);
			int count=attendanceDAO.getAllAttendanceListcount(month,weekno,branchid,searchemp,loginInfo,numdate,daysInMonth);
			pagination.setPreperties(count);
			
			attendanceForm.setTotalmonthday(String.valueOf(daysInMonth));
			daysInMonth=daysInMonth-sunday;
			ArrayList<Attendance> attendanceList=attendanceDAO.getAllAttendanceList(month,weekno,branchid,pagination,searchemp,loginInfo,numdate,daysInMonth); 
			
		    attendanceForm.setAttendanceList(attendanceList);
		    attendanceForm.setTotalRecords(count);
			pagination.setTotal_records(attendanceList.size());
			attendanceForm.setTotalRecords(count);
			attendanceForm.setPagerecords(String.valueOf(pagination.getTotal_records()));
		    

			

//			String[] temp = month.split("-");
//
//			int iyear = Integer.parseInt(temp[1]);
//			int imonth = Integer.parseInt(newmonth);
//			int idays = 1;
//			if (temp[0].equals("Jan")) {
//				filter_status="0";
//			} else if (temp[0].equals("Feb")) {
//				filter_status="1";
//			} else if (temp[0].equals("March")) {
//				filter_status="2";
//			} else if (temp[0].equals("April")) {
//				filter_status="3";
//			} else if (temp[0].equals("May")) {
//				filter_status="4";
//			} else if (temp[0].equals("June")) {
//				filter_status="5";
//			} else if (temp[0].equals("July")) {
//				filter_status="6";
//			} else if (temp[0].equals("August")) {
//				filter_status="7";
//			} else if (temp[0].equals("September")) {
//				filter_status="8";
//			} else if (temp[0].equals("October")) {
//				filter_status="9";
//			} else if (temp[0].equals("November")) {
//				filter_status="10";
//			} else if (temp[0].equals("December")) {
//				filter_status="11";
//			}
//			String result=payrollMasterDAO.getweekdays();
//			int sunday=0;
//			if(result!=null){
//			String temp1[]=result.split(",");
//			sunday=getSundays(imonth, iyear,temp1);
//			}
//			Calendar mycal = new GregorianCalendar(iyear, imonth, idays);
//			// Get the number of days in that month
//			int daysInMonth = mycal.getActualMaximum(Calendar.DAY_OF_MONTH);
//			attendanceForm.setTotalmonthday(String.valueOf(daysInMonth));
//			daysInMonth=daysInMonth-sunday;
			attendanceForm.setDaysmonth(daysInMonth);
			attendanceForm.setWeekoff(String.valueOf(sunday));
		    attendanceForm.setFilter_status(filter_status);
			calendar.add(Calendar.DAY_OF_MONTH, 6);
			
			date=calendar.getTime();
		
			String todate=dateFormat.format(date);
			
			attendanceForm.setFromdate(fromdate);
			attendanceForm.setTodate(todate);
			attendanceForm.setMonth(newmonth);
			attendanceForm.setWeekno(String.valueOf(weekno));
			attendanceForm.setYear(year);
		
			
			PayrollEmployeeDAO employeeDAO=new JDBCPayrollEmployeeDAO(connection);
			ArrayList<Employee> branchlist=employeeDAO.getAllBranches();
			attendanceForm.setBranchlist(branchlist);
			
			if(branchid!="" && branchid!=null){
				attendanceForm.setBranch(branchid);
			}
			
			
		} catch (Exception e) {
				e.printStackTrace();
		}
		finally{
			connection.close();
		}
	
		return super.execute();
	}
	
	
	public String sort() throws Exception{ 
		
		Connection connection=null;
		
		try {
			connection=Connection_provider.getconnection();
			AttendanceDAO attendanceDAO=new JDBCAttendanceDAO(connection);
			String fromdate=attendanceForm.getFromdate();
			String todate=attendanceForm.getTodate();
			
			SimpleDateFormat sdf=new SimpleDateFormat("dd-MM-yyyy");
			Date date=sdf.parse(fromdate);
			
			Calendar calendar=Calendar.getInstance();
			calendar.setTime(date);
			SimpleDateFormat format=new SimpleDateFormat("MMM-yyyy");
			Date nowdate=calendar.getTime();
		 //	int monthindex= Integer.parseInt(request.getParameter("month"));
		   // String monThArr[]={"Jan","Feb","March","April"};
			
			String month= format.format(nowdate); 
			int weekno=calendar.get(Calendar.WEEK_OF_MONTH);
			
            String branchid=attendanceForm.getBranch_id();
			ArrayList<Attendance> attendanceList=attendanceDAO.getAllAttendanceList(month,weekno,branchid,pagination,"",loginInfo,"",0); 
		    attendanceForm.setAttendanceList(attendanceList);
			
			PayrollEmployeeDAO employeeDAO=new JDBCPayrollEmployeeDAO(connection);
			ArrayList<Employee> branchlist=employeeDAO.getAllBranches();
			attendanceForm.setBranchlist(branchlist);
			
			attendanceForm.setFromdate(fromdate);
			attendanceForm.setTodate(todate);
			attendanceForm.setMonth(month);
			attendanceForm.setWeekno(String.valueOf(weekno));
			
			
			if(branchid!="" && branchid!=null){
				attendanceForm.setBranch(branchid);
			}
			
		} catch (Exception e) {

		   e.printStackTrace();
		}
		finally {
			connection.close();
		}
		
		return super.execute();
	}
	
	
	
	
	
	
	
	public String update() throws Exception{
    	
		if(!verifyLogin(request)){
			return "login";
		}
		int monthindex= 0;
		Connection connection=null;
		try {
			 connection=Connection_provider.getconnection();
			 AttendanceDAO attendanceDAO=new JDBCAttendanceDAO(connection);
			  monthindex= Integer.parseInt(request.getParameter("month"));
			    String monThArr[]={"Jan","Feb","March","April","May","June","July","August","September","October","November","December"};
			    
				String month= monThArr[monthindex]; //format.format(nowdate); 
				
				Calendar calendar=Calendar.getInstance(Locale.getDefault());
			     SimpleDateFormat format=new SimpleDateFormat("yyyy");
			     String year=format.format(calendar.getTime());
			 for(Attendance attendance:attendanceForm.getAttendance()){
				 attendance.setNummonth(String.valueOf(monthindex));
				    attendance.setMonth(month+"-"+year);
				    if(attendance.getDays()!=null){
				    	if(!attendance.getDays().equals("")){
				    		attendanceDAO.addOrUpdateWorkSheet(attendance); 	  
				    	}
				    }
                    
			 }
			 
			 
		} catch (Exception e) {

		   e.printStackTrace();
		   
		}
		
    	return "save";
    }
	
 	public String caldate() {
 		
  		
 	    try {
 	    	
 	    	String fromdate=request.getParameter("fromdate");
 	    	SimpleDateFormat dateFormat=new SimpleDateFormat("dd-MM-yyyy");
 	    	Date date=dateFormat.parse(fromdate);

 	    	Calendar calendar=Calendar.getInstance();
 	    	calendar.setTime(date);
 	    
 	    	calendar.add(Calendar.DAY_OF_MONTH, 6);
 	    	
 	    	String data=dateFormat.format(calendar.getTime());
 	   
 	    	response.setContentType("text/html");
		    response.setHeader("Cache-Control", "no-cache");
			response.getWriter().write(data);
			
		} catch (Exception e) {

		   e.printStackTrace();
		}
 	    finally {
 	    	
 	    }
 	
 		return null;
 	}
	
	
	
	
	public void prepare() throws Exception {
		Connection connection=null;
		try {
			 connection=Connection_provider.getconnection();
		PayrollEmployeeDAO employeeDAO=new JDBCPayrollEmployeeDAO(connection);
		ArrayList<Employee> branchlist=employeeDAO.getAllBranches();
		attendanceForm.setBranchlist(branchlist);
		}catch (Exception e) {
			// TODO: handle exception
		}
		
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
/*	public static int getNumberofSundays(String d1,String d2) throws Exception{ //object in Date form
		Date date1=getDate(d1);
		Date date2=getDate(d2);
		Date date3=

		Calendar c1=Calendar.getInstance();
		c1.setTime(date1);
		Calendar c2=Calendar.getInstance();
		c2.setTime(date2);
		int sundays=0;
		while(c1.after(c2)){
		if(c2.get(Calendar.DAY_OF_WEEK)==Calendar.SUNDAY){
		sundays++;
		c2.add(Calendar.DATE,1);
		}
		}

		System.out.println("number of days between 2 dates"+sundays);


		return sundays;
		}
*/
			public String dayemployee() {
			Connection connection=null;
			try {
			connection=Connection_provider.getconnection();
			Date date = new Date();  
			SimpleDateFormat formatter = new SimpleDateFormat("dd MMMM yyyy"); 
			DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
			SimpleDateFormat formatter1 = new SimpleDateFormat("dd-M-yyyy HH:mm:ss");  
			SimpleDateFormat formatter2 = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");  
		    Calendar cal = Calendar.getInstance();
		    String strDate = formatter.format(date);
		    String today=dateFormat.format(cal.getTime());
		    String datetime=formatter2.format(date);
		    AttendanceDAO attendanceDAO=new JDBCAttendanceDAO(connection);
		    PayrollEmployeeDAO employeeDAO=new JDBCPayrollEmployeeDAO(connection);
		    boolean chkentry=attendanceDAO.checktodaysentry(today,loginInfo);
		    String userid=loginInfo.getUserId();
		    String empid=employeeDAO.getEmployeeEmpid(userid);
		    Calendar cal1=Calendar.getInstance(Locale.getDefault());
			SimpleDateFormat sdf1=new SimpleDateFormat("yyyy");
			SimpleDateFormat sdf2=new SimpleDateFormat("MM");
			Date date1=cal1.getTime();
			String year=sdf1.format(date1);
			String todaymonth=sdf2.format(date1);
			String newmonth=request.getParameter("month");
			String year1=request.getParameter("year");
			String attdate=request.getParameter("date");
			String empname=attendanceForm.getEmpnamesearch();
			if(empname==null){
				empname="";
			}
			if(attdate==null){
				attdate="";
			}
			String month="";
			if(year1==null){
				 year=sdf1.format(date1);
			}else{
				year=year1;
			}
			if(newmonth == null){
				month= DateTimeUtils.getMonthName(Integer.parseInt(todaymonth));
				month=month+"-"+year;
				newmonth=String.valueOf(Integer.parseInt(todaymonth)-1);
			}else{
				month=newmonth;
			}
			
			ArrayList<Attendance> attendanceList=attendanceDAO.getAllAttendanceListOfSelf(attdate,newmonth,year,loginInfo,empname);
			attendanceForm.setAttendanceList(attendanceList);
			attendanceForm.setMonth(newmonth);
			attendanceForm.setYear(year);
			if(!chkentry){
	 	    	
			 	
			 	
			 	
			 	String intime = formatter1.format(date);
			 	int res=attendanceDAO.insertPunchIn(empid,userid,"",today,0,"","");
			}
			Attendance attendance=attendanceDAO.getListOfAttendance(today,userid,empid);
			attendanceForm.setDate(strDate);
			attendanceForm.setStatus(DateTimeUtils.isNull(attendance.getStatus()));
			attendanceForm.setIndatetime(attendance.getIndatetime());
			attendanceForm.setOutdatetime(attendance.getOutdatetime());
			  attendanceForm.setTotalhour(attendance.getTotalhour());
			   attendanceForm.setHourList(PopulateList.hourListNew());
			   attendanceForm.setMinuteList(PopulateList.getMinuteList());
			if(attendance.getStatus().equals("1")){
			   String tmp[]=attendance.getIndatetime().split(" ");
			   String onlydate=DateTimeUtils.getCommencingDatemmddyyy(tmp[0]);
			   String datewithtime=onlydate+" "+tmp[1];
			   String difftime=attendanceDAO.differenceOftwoTimes(datewithtime, datetime);
			   attendance.setTotalhour(difftime);
			}else if(attendance.getStatus().equals("2")){
			   String tmp[]=attendance.getIndatetime().split(" ");
			   String onlydate=DateTimeUtils.getCommencingDatemmddyyy(tmp[0]);
			   String datewithtime=onlydate+" "+tmp[1];
			   String tmp1[]=attendance.getOutdatetime().split(" ");
			   String onlydate1=DateTimeUtils.getCommencingDatemmddyyy(tmp1[0]);
			   String datewithtime1=onlydate1+" "+tmp1[1];
			   String difftime=attendanceDAO.differenceOftwoTimes(datewithtime, datewithtime1);
			   attendance.setTotalhour(difftime);
			}else{
			   attendance.setTotalhour("0");
		   }
		 
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return "dayemployee";
	}
	public String setpunchin(){
		Connection connection=null;
		try {
			 connection=Connection_provider.getconnection();
			 	Date date = new Date();  
	 	    	AttendanceDAO attendanceDAO=new JDBCAttendanceDAO(connection);
	 	    	PayrollEmployeeDAO employeeDAO=new JDBCPayrollEmployeeDAO(connection);
	 	    	DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
			 	String status=request.getParameter("status");
			 	String remark=request.getParameter("remark");
			 	String userid=loginInfo.getUserId();
			 	String empid=employeeDAO.getEmployeeEmpid(userid);
			    Calendar cal = Calendar.getInstance();
			    String date1 = dateFormat.format(cal.getTime());
			 	SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");  
			 	String intime = formatter.format(date);
			 	SimpleDateFormat sdf1=new SimpleDateFormat("yyyy");
				SimpleDateFormat sdf2=new SimpleDateFormat("MM");
				Date date2=cal.getTime();
			 	String year=sdf1.format(date2);
				String todaymonth=sdf2.format(date2);
				String month_yr=todaymonth+"-"+year;
			 	if(!status.equals("2")){
			 	boolean chkentry=attendanceDAO.checktodaysentry(date1,loginInfo);
				   if(!chkentry){
					   int res=attendanceDAO.insertPunchIn(empid,userid,intime,date1,1,remark,month_yr);
				   }else{
					   int res=attendanceDAO.updatePunching(empid,userid,intime,date1,1,"indatetime","remarkintime",remark,"",0,month_yr);
				   }
			 	}else{
			 		String intimefortotal=attendanceDAO.getintimefortotal(empid,userid,date1);
			 		 String tmp[]=intimefortotal.split(" ");
					   String onlydate=DateTimeUtils.getCommencingDatemmddyyy(tmp[0]);
					   String datewithtime=onlydate+" "+tmp[1];
					   String tmp1[]=intime.split(" ");
					   String onlydate1=DateTimeUtils.getCommencingDatemmddyyy(tmp1[0]);
					   String datewithtime1=onlydate1+" "+tmp1[1];
					   String difftime=attendanceDAO.differenceOftwoTimes(datewithtime, datewithtime1);
					   
					   String dif1=difftime.split(":")[1];
					   String dif2=difftime.split(":")[0];
					   int timin=Integer.parseInt(dif2)*60+Integer.parseInt(dif1);
			 		int res=attendanceDAO.updatePunching(empid,userid,intime,date1,2,"outdatetime","remarkouttime",remark,difftime,timin,month_yr);
			 	}
	 	    	response.setContentType("text/html");
			    response.setHeader("Cache-Control", "no-cache");
				response.getWriter().write("");
				
			} catch (Exception e) {

			   e.printStackTrace();
			}
		return null;
	}
	public String changepunchtime() throws Exception{
		Connection connection=Connection_provider.getconnection();;
		int res1=0;
		try {
			String id=request.getParameter("id");
			String val=request.getParameter("val");
			String colname=request.getParameter("colname");
			AttendanceDAO attendanceDAO=new JDBCAttendanceDAO(connection);
			Attendance attendance=attendanceDAO.getDayDetails(id);
			res1=attendanceDAO.changepunchtime(id, val,colname,attendance);
			response.setContentType("text/html");
			response.setHeader("Cache-Control", "no-cache");
			response.getWriter().write(""); 
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			connection.close();
		}
		
		return null;
		
	}
	public String mobileattendance(){
		Connection connection=null;
		try {
			 connection=Connection_provider.getconnection();
			 	Date date = new Date();  
	 	    	AttendanceDAO attendanceDAO=new JDBCAttendanceDAO(connection);
	 	    	PayrollEmployeeDAO employeeDAO=new JDBCPayrollEmployeeDAO(connection);
	 	    	DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
			 	String status=request.getParameter("status");
			 	String remark=request.getParameter("remark");
			 	String longlat=request.getParameter("longlat");
			 	SimpleDateFormat formatter2 = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");  
			 	 String datetime=formatter2.format(date);
			 	String userid=loginInfo.getUserId();
			 	String empid=employeeDAO.getEmployeeEmpid(userid);
			    Calendar cal = Calendar.getInstance();
			    String date1 = dateFormat.format(cal.getTime());
			 	SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");  
			 	String intime = formatter.format(date);
			 	if(!status.equals("2")){
			 	boolean chkentry=attendanceDAO.checktodayChecksentry(date1,loginInfo);
				   if(!chkentry){
					   int res=attendanceDAO.inserCheckIn(empid,userid,intime,date1,1,remark);
				   }else{
					   int parentid=attendanceDAO.getParentId(date1,loginInfo,empid);
					   int childid=attendanceDAO.getChildAttendanceid(date1,empid,userid);
					   String childsts=attendanceDAO.getoutdatetime(childid);
					   if(childsts.equals("0") && childid>0 ){
				 			int res=attendanceDAO.updateCheckin(empid,userid,intime,date1,status,"indatetime","inremark",remark,childid,parentid,longlat);
				 		}else{
				 			int res=attendanceDAO.insertChildCheckIn(empid,userid,intime,date1,1,remark,parentid,longlat);
				 			int res1=attendanceDAO.updateCheckInStatus(parentid, 1);
				 		}
				   }
			 	}else{
			 		int childid=attendanceDAO.getChildAttendanceid(date1,empid,userid);
			 		 int parentid=attendanceDAO.getParentId(date1,loginInfo,empid);
			 		if(childid>0){
			 			Attendance attendance=attendanceDAO.getcheckInDetails(childid,parentid,date1);
			 			String tmp[]=attendance.getIndatetime().split(" ");
			 		   String onlydate=DateTimeUtils.getCommencingDatemmddyyy(tmp[0]);
			 		   String datewithtime=onlydate+" "+tmp[1];
			 		   String difftime=attendanceDAO.differenceOftwoTimes(datewithtime, datetime);
			 		   attendance.setTotalhour(difftime);
			 			int res=attendanceDAO.updateCheckout(empid,userid,intime,date1,status,"outdatetime","outremark",remark,childid,parentid,longlat,attendance.getTotalhour());
			 			int res1=attendanceDAO.updateCheckInStatus(parentid, 2);
			 		}else{
			 			int res=attendanceDAO.insertChildCheckIn(empid,userid,intime,date1,1,remark,parentid,longlat);
			 		}
			 		
			 	}
			 	response.setContentType("text/html");
				response.setHeader("Cache-Control", "no-cache");
				response.getWriter().write("0");
				
			} catch (Exception e) {

			   e.printStackTrace();
			}
		return null;
	}
	public String mobileattdashboard() {
		Connection connection=null;
		try {
		connection=Connection_provider.getconnection();
		Date date = new Date();  
		SimpleDateFormat formatter = new SimpleDateFormat("dd MMMM yyyy"); 
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
		SimpleDateFormat formatter1 = new SimpleDateFormat("dd-M-yyyy HH:mm:ss");  
		SimpleDateFormat formatter2 = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");  
	    Calendar cal = Calendar.getInstance();
	    String strDate = formatter.format(date);
	    String today=dateFormat.format(cal.getTime());
	    String datetime=formatter2.format(date);
	    AttendanceDAO attendanceDAO=new JDBCAttendanceDAO(connection);
	    PayrollEmployeeDAO employeeDAO=new JDBCPayrollEmployeeDAO(connection);
	    boolean chkentry=attendanceDAO.checktodayChecksentry(today,loginInfo);
	    String userid=loginInfo.getUserId();
	    String empid=employeeDAO.getEmployeeEmpid(userid);
	    Calendar cal1=Calendar.getInstance(Locale.getDefault());
		SimpleDateFormat sdf1=new SimpleDateFormat("yyyy");
		SimpleDateFormat sdf2=new SimpleDateFormat("MM");
		Date date1=cal1.getTime();
		String year=sdf1.format(date1);
		String todaymonth=sdf2.format(date1);
		String newmonth=request.getParameter("month");
		String year1=request.getParameter("year");
		String attdate=request.getParameter("date");
		String empname=attendanceForm.getEmpnamesearch();
		String inpdate=attendanceForm.getDate();
		if(empname==null){
			empname="";
		}
		if (attdate == null){
			attdate=today;
		}else if (attdate.equals("")){
			attdate=today;
		}else{
			attdate=DateTimeUtils.getCommencingDatePayroll(attdate);
		}
		
			/*if (attdate == null) {
				DateFormat dateFormat4 = new SimpleDateFormat("dd/MM/yyyy");
				Calendar cal4 = Calendar.getInstance();
				attdate = dateFormat4.format(cal4.getTime());
			} else {

				if (attdate.equals("")) {
					DateFormat dateFormat4 = new SimpleDateFormat("dd/MM/yyyy");
					Calendar cal4 = Calendar.getInstance();
					attdate = dateFormat4.format(cal4.getTime());
				} 
			}*/
		String month="";
		if(year1==null){
			 year=sdf1.format(date1);
		}else{
			year=year1;
		}
		if(newmonth == null){
			month= DateTimeUtils.getMonthName(Integer.parseInt(todaymonth));
			month=month+"-"+year;
			newmonth=String.valueOf(Integer.parseInt(todaymonth)-1);
		}else{
			month=newmonth;
		}
		
		ArrayList<Attendance> attendanceList=attendanceDAO.getAllAttendanceListOfemp(attdate,newmonth,year,loginInfo,empname,inpdate);
		attendanceForm.setAttendanceList(attendanceList);
		attendanceForm.setMonth(newmonth);
		attendanceForm.setYear(year);
		if(!chkentry){
 	    	
		 	
		 	
		 	
		 	String intime = formatter1.format(date);
		 	  int res=attendanceDAO.inserCheckIn(empid,userid,"",today,0,"");
		}
		Attendance attendance=attendanceDAO.getListOfMobileAttendance(today,userid,empid);
		attendanceForm.setDate(DateTimeUtils.getCommencingDatePayroll(attdate));
		attendanceForm.setStatus(attendance.getStatus());
		attendanceForm.setIndatetime(attendance.getIndatetime());
		attendanceForm.setOutdatetime(attendance.getOutdatetime());
		if(attendance.getStatus().equals("1")){
		   String tmp[]=attendance.getIndatetime().split(" ");
		   String onlydate=DateTimeUtils.getCommencingDatemmddyyy(tmp[0]);
		   String datewithtime=onlydate+" "+tmp[1];
		   String difftime=attendanceDAO.differenceOftwoTimes(datewithtime, datetime);
		   attendance.setTotalhour(difftime);
		}else if(attendance.getStatus().equals("2")){
		   String tmp[]=attendance.getIndatetime().split(" ");
		   String onlydate=DateTimeUtils.getCommencingDatemmddyyy(tmp[0]);
		   String datewithtime=onlydate+" "+tmp[1];
		   String tmp1[]=attendance.getOutdatetime().split(" ");
		   String onlydate1=DateTimeUtils.getCommencingDatemmddyyy(tmp1[0]);
		   String datewithtime1=onlydate1+" "+tmp1[1];
		   String difftime=attendanceDAO.differenceOftwoTimes(datewithtime, datewithtime1);
		   attendance.setTotalhour(difftime);
		}else{
		   attendance.setTotalhour("0");
	   }
	   attendanceForm.setTotalhour(attendance.getTotalhour());
	} catch (Exception e) {
		e.printStackTrace();
	}
		return "mobileattendance";
	
	}
	
	public String addmorecheckin() throws SQLException {
		Connection connection=Connection_provider.getconnection();;
		int res1=0;
		try {
			DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
			PayrollEmployeeDAO employeeDAO=new JDBCPayrollEmployeeDAO(connection);
			String userid=loginInfo.getUserId();
		 	String empid=employeeDAO.getEmployeeEmpid(userid);
		    Calendar cal = Calendar.getInstance();
		    String date1 = dateFormat.format(cal.getTime());
			AttendanceDAO attendanceDAO=new JDBCAttendanceDAO(connection);
			int parentid=attendanceDAO.getParentId(date1,loginInfo,empid);
			int res=attendanceDAO.updateCheckInStatus(parentid,0);
			response.setContentType("text/html");
			response.setHeader("Cache-Control", "no-cache");
			response.getWriter().write(""); 
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			connection.close();
		}
		
		return null;
		
		
	}
	public String timesheetdashboard() throws SQLException {
		String fromDate="",toDate="";
		fromDate=attendanceForm.getFromdate();
		toDate=attendanceForm.getTodate();
		Connection connection=Connection_provider.getconnection();
		AttendanceDAO attendanceDAO=new JDBCAttendanceDAO(connection);
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd");
		if (fromDate == null) {
			DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
			Calendar cal = Calendar.getInstance();
			cal.add(Calendar.DATE, -7);
			fromDate = dateFormat.format(cal.getTime());
			fromDate = DateTimeUtils.getCommencingDatePayroll(fromDate);
		} else {

			if (fromDate.equals("")) {
				DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
				Calendar cal = Calendar.getInstance();
				cal.add(Calendar.DATE, -7);
				fromDate = dateFormat.format(cal.getTime());
				fromDate = DateTimeUtils.getCommencingDatePayroll(fromDate);
			} else {
				fromDate = DateTimeUtils.getCommencingDatePayroll(fromDate);
			}
		}

		if (toDate == null) {
			DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
			Calendar cal = Calendar.getInstance();
			toDate = dateFormat.format(cal.getTime());
			toDate = DateTimeUtils.getCommencingDatePayroll(toDate);
		} else {
			if (toDate.equals("")) {
				DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
				Calendar cal = Calendar.getInstance();
				toDate = dateFormat.format(cal.getTime());
				toDate = DateTimeUtils.getCommencingDatePayroll(toDate);
			} else {
				toDate = DateTimeUtils.getCommencingDatePayroll(toDate);
			}
		}
		
		ArrayList<Attendance> arrayList=attendanceDAO.getrequesttimesheetdashboard(fromDate,toDate,loginInfo.getUserId(),loginInfo);
		attendanceForm.setAttendanceList(arrayList);
		attendanceForm.setFromdate(DateTimeUtils.getCommencingDatePayroll(fromDate));
		attendanceForm.setTodate(DateTimeUtils.getCommencingDatePayroll(toDate));
		return "timesheetdashboard";
		
		
	}
	public String createtimesheet() throws ParseException, SQLException {
		String fromDate="",toDate="";
		fromDate=attendanceForm.getFromdate();
		toDate=attendanceForm.getTodate();
		Connection connection=Connection_provider.getconnection();
		AttendanceDAO attendanceDAO=new JDBCAttendanceDAO(connection);
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd");
		SimpleDateFormat formatter1 = new SimpleDateFormat("E dd MMM");
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		Calendar cal = Calendar.getInstance();
		if (fromDate == null) {
			
			while(cal.get(Calendar.DAY_OF_WEEK) != Calendar.MONDAY)
	        {
				cal.add(Calendar.DATE,-1);//go one day before
	        }
			fromDate = dateFormat.format(cal.getTime());
			fromDate = DateTimeUtils.getCommencingDatePayroll(fromDate);
		} else {

			if (fromDate.equals("")) {
				while(cal.get(Calendar.DAY_OF_WEEK) != Calendar.MONDAY)
		        {
					cal.add(Calendar.DATE,-1);//go one day before
		        }
				
				fromDate = dateFormat.format(cal.getTime());
				fromDate = DateTimeUtils.getCommencingDatePayroll(fromDate);
			} else {
				fromDate = DateTimeUtils.getCommencingDatePayroll(fromDate);
			}
		}

		if (toDate == null) {
			cal.add(Calendar.DATE, 7); //add 6 days after Monday
			toDate = dateFormat.format(cal.getTime());
			toDate = DateTimeUtils.getCommencingDatePayroll(toDate);
		} else {
			if (toDate.equals("")) {
				cal.add(Calendar.DATE, 7); //add 6 days after Monday
				toDate = dateFormat.format(cal.getTime());
				toDate = DateTimeUtils.getCommencingDatePayroll(toDate);
			} else {
				toDate = DateTimeUtils.getCommencingDatePayroll(toDate);
			}
		}
		ArrayList<Attendance> arrayList=new ArrayList<Attendance>();
		Date startDate = formatter.parse(fromDate);
		Date endDate = formatter.parse(toDate);
		Calendar start = Calendar.getInstance();
		start.setTime(startDate);
		Calendar end = Calendar.getInstance();
		end.setTime(endDate);
		SimpleDateFormat simpleDateformat = new SimpleDateFormat("E");
		PayrollEmployeeDAO employeeDAO=new JDBCPayrollEmployeeDAO(connection);
		String empid=employeeDAO.getEmployeeEmpid(loginInfo.getUserId());
		 Employee employee=employeeDAO.getAllDetailsEmployee(empid);
		 PayrollMasterDAO payrollMasterDAO=new JDBCPayrollMasterDAO(connection);
		 attendanceForm.setWorkhour(employee.getWorkhour());
		 attendanceForm.setOtallow(employee.getOtallow());
//		 boolean leave=attendanceDAO.getleavestatus(fromDate,toDate,empid);
		for (Date date = start.getTime(); start.before(end); start.add(Calendar.DATE,1), date = start.getTime()) {
		    Attendance  attendance = new Attendance();
			System.out.println(date);
			System.out.println(simpleDateformat.format(date));
		    String strDate = formatter.format(date);
		    attendance.setDate(strDate);
		    attendance.setShowdate(formatter1.format(date));
		    attendance.setDays(simpleDateformat.format(date));
		    Attendance attendance2=attendanceDAO.getpayroll_daytoday_attendance(empid,strDate);
		    int leaveid=attendanceDAO.getleavestatus(strDate,toDate,empid);
		    boolean holiday=attendanceDAO.getholidaystatus(strDate);
		    if(leaveid>0)
		    {
		    	Payroll payroll = payrollMasterDAO.getleaveDetails(""+leaveid);
		    	attendance.setTotalhour(DateTimeUtils.isNull(employee.getWorkinghourday()));
		    	attendance.setReadonly(true);
		    	if(payroll.getLeaveno().equals("1")){
		    		attendance.setLeaveday("C.L");
		    	}else if(payroll.getLeaveno().equals("2")){
		    		attendance.setLeaveday("M.L");
		    	}
		    }else if (holiday) {
		    	attendance.setLeaveday("N.H");
		    	attendance.setTotalhour(DateTimeUtils.isNull(employee.getWorkinghourday()));
		    	attendance.setReadonly(true);
			}else{
				attendance.setLeaveday(attendance2.getTotalhour());
		    	attendance.setReadonly(false);
//		    	attendance.setTotalhour(DateTimeUtils.isNull());
		    	}
		    attendance.setEmp_id(empid);
		    arrayList.add(attendance);
		}
		attendanceForm.setAttendanceList(arrayList);
//		long diff=DateTimeUtils.getDiffofTwoDates(DateTimeUtils.getCommencingDatePayroll(fromDate), DateTimeUtils.getCommencingDatePayroll(toDate));
//		System.out.println(diff);
			attendanceForm.setEmp_id(empid);
			attendanceForm.setFromdate(DateTimeUtils.getCommencingDatePayroll(fromDate));
			attendanceForm.setTodate(DateTimeUtils.getCommencingDatePayroll(toDate));
		return "createtimesheet";
		
	}
	public String timesheetsavedraft() {
		try {
			Connection connection=Connection_provider.getconnection();
			AttendanceDAO attendanceDAO=new JDBCAttendanceDAO(connection);
			String status=request.getParameter("status");
			Attendance attendance1=new Attendance();
			attendance1.setEmp_id(attendanceForm.getEmp_id());
			attendance1.setFromdate(DateTimeUtils.getCommencingDatePayroll(attendanceForm.getFromdate()));
			attendance1.setTodate(DateTimeUtils.getCommencingDatePayroll(attendanceForm.getTodate()));
			attendance1.setUserid(loginInfo.getUserId());
			attendance1.setNotes(attendanceForm.getNotes());
			attendance1.setTotal_hour(attendanceForm.getTotal_hour());
			DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
			Date date = new Date();
			attendance1.setDate(dateFormat.format(date));
			attendance1.setStatus(status);
			if(status.equals("1")){
			int res= attendanceDAO.insertIntopayroll_parent_request_timesheet(attendance1);
			for (Attendance attendance : attendanceForm.getCollectionsal()) {
				attendance.setParentid(""+res);
				attendance.setEmp_id(attendance1.getEmp_id());
				attendance.setUserid(attendance1.getUserid());
				attendance.setCommencing(dateFormat.format(date));
				int res1=attendanceDAO.insertintopayroll_request_timesheet(attendance);
				int updremarknduserd=attendanceDAO.updremarknduserid(""+res,attendance1.getNotes(),"userremark",attendance1.getTotal_hour());
			}
			}else if (status.equals("2")) {
				for (Attendance attendance : attendanceForm.getCollectionsal()) {
					attendance.setEmp_id(attendance1.getEmp_id());
					attendance.setUserid(attendance1.getUserid());
					attendance.setCommencing(dateFormat.format(date));
					int res1=attendanceDAO.updatepayroll_request_timesheet(attendance);
					int updremarknduserd=attendanceDAO.updremarknduserid(attendanceForm.getParentid(),attendance1.getNotes(),"userremark",attendance1.getTotal_hour());
				}
			}else if (status.equals("3")) {
				for (Attendance attendance : attendanceForm.getCollectionsal()) {
					attendance.setEmp_id(attendance1.getEmp_id());
					attendance.setUserid(attendance1.getUserid());
					attendance.setCommencing(dateFormat.format(date));
					int res1=attendanceDAO.updatepayroll_request_timesheet(attendance);
					int r=attendanceDAO.updatepayrollparentstatus(attendanceForm.getParentid(),"2");
					int updremarknduserd=attendanceDAO.updremarknduserid(attendanceForm.getParentid(),attendance1.getNotes(),"userremark",attendance1.getTotal_hour());
				}
				
			}else if (status.equals("4")) {
				attendance1.setStatus("2");
				int res= attendanceDAO.insertIntopayroll_parent_request_timesheet(attendance1);
				for (Attendance attendance : attendanceForm.getCollectionsal()) {
					attendance.setParentid(""+res);
					attendance.setEmp_id(attendance1.getEmp_id());
					attendance.setUserid(attendance1.getUserid());
					attendance.setCommencing(dateFormat.format(date));
					int res1=attendanceDAO.insertintopayroll_request_timesheet(attendance);
					//int updremarknduserd=attendanceDAO.updremarknduseriddate(""+res,attendance1.getNotes(),"userremark",loginInfo.getUserId(),dateFormat.format(date),attendance1.getTotal_hour());
				}
				
			}else if (status.equals("5")) {
				for (Attendance attendance : attendanceForm.getCollectionsal()) {
					attendance.setStatus("3");
					attendance.setEmp_id(attendance1.getEmp_id());
					attendance.setUserid(attendance1.getUserid());
					attendance.setCommencing(dateFormat.format(date));
					int res1=attendanceDAO.updatepayroll_request_timesheet(attendance);
					int r=attendanceDAO.updatepayrollparentstatus(attendanceForm.getParentid(),"3");
					int updremarknduserd=attendanceDAO.updremarknduseriddate(attendanceForm.getParentid(),attendance1.getNotes(),"approveremark",loginInfo.getUserId(),dateFormat.format(date),attendance1.getTotal_hour());
					sendapprovalmail(attendance.getEmp_id(),attendanceForm.getParentid());
				}
				
			}
			else if (status.equals("6")) {
				for (Attendance attendance : attendanceForm.getCollectionsal()) {
					attendance.setEmp_id(attendance1.getEmp_id());
					attendance.setUserid(attendance1.getUserid());
					attendance.setCommencing(dateFormat.format(date));
					int res1=attendanceDAO.updatepayroll_request_timesheet(attendance);
					int r=attendanceDAO.updatepayrollparentstatus(attendanceForm.getParentid(),"4");
					int updremarknduserd=attendanceDAO.updremarknduseriddate(attendanceForm.getParentid(),attendance1.getNotes(),"approveremark",loginInfo.getUserId(),dateFormat.format(date),attendance1.getTotal_hour());
				}
				
			}
			attendanceForm.setParentid(request.getParameter("parentid"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "redirecttimesheetdashboard";
		
	}
	
	private void sendapprovalmail(String emp_id, String parentid) throws Exception {
		StringBuffer buffer=new StringBuffer();
		Connection connection=Connection_provider.getconnection();
		AttendanceDAO attendanceDAO=new JDBCAttendanceDAO(connection);
		Attendance attendance=attendanceDAO.getparentRequestTimesheet(parentid);
		PayrollEmployeeDAO employeeDAO=new JDBCPayrollEmployeeDAO(connection);
		Employee employee=employeeDAO.getAllDetailsEmployee(emp_id);
		String to=employee.getEmail();
		String subject="Timesheet Approved for duration: "+DateTimeUtils.getCommencingDatePayroll(attendance.getFromdate())+" to "+DateTimeUtils.getCommencingDatePayroll(attendance.getFromdate())+"";
		buffer.append("Dear,<br> "+employee.getName().toUpperCase()+" <br><br>");
		buffer.append("Your Timesheet "+DateTimeUtils.getCommencingDatePayroll(attendance.getFromdate())+" to "+DateTimeUtils.getCommencingDatePayroll(attendance.getTodate())+" has been approved."); 
		String notes = buffer.toString();
		/*EmbeddedImageEmailUtil.sendMailFromSupport(to, cc, subject, notes);*/
		EmailLetterLog emailLetterLog=new EmailLetterLog();
		EmbeddedImageEmailUtil.sendMailPayroll(connection, to, "", subject, notes, loginInfo,emailLetterLog);
		
	}


	public String saveasdrafttimesheet() throws ParseException, SQLException {
		String fromDate="",toDate="";
		fromDate=DateTimeUtils.isNull(attendanceForm.getFromdate());
		toDate=DateTimeUtils.isNull(attendanceForm.getTodate());
		Connection connection=Connection_provider.getconnection();
		AttendanceDAO attendanceDAO=new JDBCAttendanceDAO(connection);
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd");
		SimpleDateFormat formatter1 = new SimpleDateFormat("E dd MMM");
		/*if (fromDate == null) {
			DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
			Calendar cal = Calendar.getInstance();
			cal.add(Calendar.DATE, -7);
			fromDate = dateFormat.format(cal.getTime());
			fromDate = DateTimeUtils.getCommencingDatePayroll(fromDate);
		} else {

			if (fromDate.equals("")) {
				DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
				Calendar cal = Calendar.getInstance();
				cal.add(Calendar.DATE, -7);
				
				fromDate = dateFormat.format(cal.getTime());
				fromDate = DateTimeUtils.getCommencingDatePayroll(fromDate);
			} else {
				fromDate = DateTimeUtils.getCommencingDatePayroll(fromDate);
			}
		}

		if (toDate == null) {
			DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
			Calendar cal = Calendar.getInstance();
			toDate = dateFormat.format(cal.getTime());
			toDate = DateTimeUtils.getCommencingDatePayroll(toDate);
		} else {
			if (toDate.equals("")) {
				DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
				Calendar cal = Calendar.getInstance();
				toDate = dateFormat.format(cal.getTime());
				toDate = DateTimeUtils.getCommencingDatePayroll(toDate);
			} else {
				toDate = DateTimeUtils.getCommencingDatePayroll(toDate);
			}
		}*/
		String parentid="";
		parentid=DateTimeUtils.isNull(attendanceForm.getParentid());
		if(parentid.equals("")){
		parentid=request.getParameter("id");
		}
		Attendance attendance3=attendanceDAO.getparentRequestTimesheet(parentid);
		attendanceForm.setTotal_hour(attendance3.getTotal_hour());
		if(fromDate.equals("") || toDate.equals("")){
			fromDate=attendance3.getFromdate();
			toDate=attendance3.getTodate();
		}else{
			fromDate=DateTimeUtils.getCommencingDatePayroll(fromDate);
			toDate=DateTimeUtils.getCommencingDatePayroll(toDate);
		}
		attendanceForm.setNotes(attendance3.getUserremark());
		ArrayList<Attendance> arrayList=new ArrayList<Attendance>();
		Date startDate = formatter.parse(fromDate);
		Date endDate = formatter.parse(toDate);
		Calendar start = Calendar.getInstance();
		start.setTime(startDate);
		Calendar end = Calendar.getInstance();
		end.setTime(endDate);
		PayrollMasterDAO payrollMasterDAO=new JDBCPayrollMasterDAO(connection);
		SimpleDateFormat simpleDateformat = new SimpleDateFormat("E");
		PayrollEmployeeDAO employeeDAO=new JDBCPayrollEmployeeDAO(connection);
		String empid=employeeDAO.getEmployeeEmpid(loginInfo.getUserId());
		Employee employee=employeeDAO.getAllDetailsEmployee(empid);
		 attendanceForm.setWorkhour(employee.getWorkhour());
		for (Date date = start.getTime(); start.before(end); start.add(Calendar.DATE, 1), date = start.getTime()) {
		    Attendance  attendance = new Attendance();
			System.out.println(date);
			System.out.println(simpleDateformat.format(date));
		    String strDate = formatter.format(date);
		    attendance.setDate(strDate);
		    attendance.setShowdate(formatter1.format(date));
		    attendance.setDays(simpleDateformat.format(date));
		    Attendance attendance2=attendanceDAO.getpayroll_child_request_attendance(empid,strDate,parentid);
		    int leaveid=attendanceDAO.getleavestatus(strDate,toDate,empid);
		    boolean holiday=attendanceDAO.getholidaystatus(strDate);
//		    if(leaveid>0)
//		    {
//		    	Payroll payroll = payrollMasterDAO.getleaveDetails(""+leaveid);
//		    	attendance.setTotalhour(DateTimeUtils.isNull(attendance2.getTotalhour()));
//		    	attendance.setReadonly(true);
//		    }else{
//		    	attendance.setReadonly(false);
//		    	attendance.setTotalhour(DateTimeUtils.isNull(attendance2.getTotalhour()));
//		    	}
		    Attendance attend=attendanceDAO.getpayroll_daytoday_attendance(empid,strDate);
		    if(leaveid>0)
		    {
		    	Payroll payroll = payrollMasterDAO.getleaveDetails(""+leaveid);
		    	attendance.setTotalhour(DateTimeUtils.isNull(attendance2.getTotalhour()));
		    	attendance.setReadonly(true);
		    	if(payroll.getLeaveno().equals("1")){
		    		attendance.setLeaveday("C.L");
		    	}else if(payroll.getLeaveno().equals("2")){
		    		attendance.setLeaveday("M.L");
		    	}
		    }else if (holiday) {
		    	attendance.setLeaveday("N.H");
		    	attendance.setTotalhour(DateTimeUtils.isNull(attendance2.getTotalhour()));
		    	attendance.setReadonly(true);
			}else{
				attendance.setLeaveday(DateTimeUtils.isNull(attend.getTotalhour()));
		    	attendance.setReadonly(false);
		    	attendance.setTotalhour(DateTimeUtils.isNull(attendance2.getTotalhour()));
		    	}
		    attendance.setId(attendance2.getId());
		    attendance.setEmp_id(empid);
		    
		    arrayList.add(attendance);
		}
		attendanceForm.setOtallow(employee.getOtallow());
		attendanceForm.setAttendanceList(arrayList);
		attendanceForm.setParentid(parentid);
//		long diff=DateTimeUtils.getDiffofTwoDates(DateTimeUtils.getCommencingDatePayroll(fromDate), DateTimeUtils.getCommencingDatePayroll(toDate));
//		System.out.println(diff);
			attendanceForm.setEmp_id(empid);
			attendanceForm.setFromdate(DateTimeUtils.getCommencingDatePayroll(fromDate));
			attendanceForm.setTodate(DateTimeUtils.getCommencingDatePayroll(toDate));
		return "saveasdrafttimesheet";
		
	}
	public String getsubmittedtimesheet() {
		try {
			Connection connection=Connection_provider.getconnection();
			AttendanceDAO attendanceDAO=new JDBCAttendanceDAO(connection);
			String parentid=request.getParameter("id");
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd");
			StringBuffer buffer=new StringBuffer();
			Attendance attendance3=attendanceDAO.getparentRequestTimesheet(parentid);
				String fromDate=attendance3.getFromdate();
				String toDate=attendance3.getTodate();
			
				buffer.append("<thead>");
				buffer.append("<tr>");
				buffer.append("<th style='text-align: center; width: 9%;'>Date</th>");
				Date startDate = formatter.parse(fromDate);
				Date endDate = formatter.parse(toDate);
				Calendar start = Calendar.getInstance();
				start.setTime(startDate);
				Calendar end = Calendar.getInstance();
				end.setTime(endDate);
				SimpleDateFormat simpleDateformat = new SimpleDateFormat("E dd MMM");
				PayrollEmployeeDAO employeeDAO=new JDBCPayrollEmployeeDAO(connection);
				String empid=employeeDAO.getEmployeeEmpid(attendance3.getUserid());
				Employee employee=employeeDAO.getAllDetailsEmployee(empid);
				String empname=employeeDAO.getEmployeeName(empid);
				String remark=attendance3.getUserremark();
				int i=0;
				for (Date date = start.getTime(); start.before(end); start.add(Calendar.DATE, 1), date = start.getTime()) {
				    Attendance  attendance = new Attendance();
					System.out.println(date);
					System.out.println(simpleDateformat.format(date));
				    String strDate = formatter.format(date);
				    attendance.setDate(strDate);
				    attendance.setDays(simpleDateformat.format(date));
				    Attendance attendance2=attendanceDAO.getpayroll_child_request_attendance(empid,strDate,parentid);
				    attendance.setTotalhour(DateTimeUtils.isNull(attendance2.getTotalhour()));
				    attendance.setId(attendance2.getId());
				    attendance.setEmp_id(empid);
				
				buffer.append("<td style='padding:0;text-align: center;'>"+simpleDateformat.format(date)+"<input type='hidden' name='collectionsal["+i+"].date' value="+strDate+"></td>");
				i++;
				}
				buffer.append("<td class='hidden'> <input type='hidden' name='fromdate' value="+fromDate+">");
				buffer.append("<input type='hidden' name='todate' value="+toDate+">");
				buffer.append("<input type='hidden' name='emp_id' value="+empid+">");
				buffer.append("<input type='hidden' name='parentid' value="+parentid+">");
				buffer.append("</td>");
				buffer.append("</tr>");
				buffer.append("</thead>");
				buffer.append("<tbody>");

				buffer.append("<tr>");
				buffer.append("<td>Hours/Day</td>");
				i=0;
				Attendance attendance4=attendanceDAO.getparentRequestTimesheet(parentid);
				PayrollMasterDAO payrollMasterDAO=new  JDBCPayrollMasterDAO(connection);
				 fromDate=attendance4.getFromdate();
				 toDate=attendance4.getTodate();
				  startDate = formatter.parse(fromDate);
					 endDate = formatter.parse(toDate);
					 start = Calendar.getInstance();
					start.setTime(startDate);
					 end = Calendar.getInstance();
					end.setTime(endDate);
					session.setAttribute("dataforts", attendance4.getStatus());
				for (Date date = start.getTime(); start.before(end); start.add(Calendar.DATE, 1), date = start.getTime()) {
				    Attendance  attendance = new Attendance();
					System.out.println(date);
					System.out.println(simpleDateformat.format(date));
				    String strDate = formatter.format(date);
				    attendance.setDate(strDate);
				    attendance.setDays(simpleDateformat.format(date));
				    Attendance attendance2=attendanceDAO.getpayroll_child_request_attendance(empid,strDate,parentid);
				    attendance.setTotalhour(DateTimeUtils.isNull(attendance2.getTotalhour()));
				    attendance.setId(attendance2.getId());
				    attendance.setEmp_id(empid);
				    int leaveid=attendanceDAO.getleavestatus(strDate,toDate,empid);
				    boolean holiday=attendanceDAO.getholidaystatus(strDate);
				    if(leaveid>0)
				    {
				    	Payroll payroll = payrollMasterDAO.getleaveDetails(""+leaveid);
				    	attendance.setTotalhour(DateTimeUtils.isNull("8"));
				    }else if (holiday) {
				    	attendance.setTotalhour(DateTimeUtils.isNull("8"));
					}else{
				    	attendance.setTotalhour(DateTimeUtils.isNull(attendance2.getTotalhour()));
				    	}
				    if(loginInfo.isPayrollaccess()){
				    	if (leaveid>0) {
					    	buffer.append("<td style='text-align: center;width: 8%'> <input type='Number' name='collectionsal["+i+"].values' placeholder='00:00' class='form-control shubha' readonly='readonly' style='text-align: center;' value="+attendance.getTotalhour()+"></td>");
						}else if (holiday){
							buffer.append("<td style='text-align: center;width: 8%'> <input type='Number' name='collectionsal["+i+"].values' placeholder='00:00' class='form-control shubha' readonly='readonly' style='text-align: center;' value="+attendance.getTotalhour()+"></td>");
						}else{
							buffer.append("<td style='text-align: center;width: 8%'> <input type='Number' name='collectionsal["+i+"].values' placeholder='00:00' class='form-control shubha' value='"+attendance.getTotalhour()+"' style='text-align: center;' onchange='recordedtime(this.value,"+i+")'></td>");
						}
						}else if(attendance4.getStatus().equals("3")){
				    	buffer.append("<td style='text-align: center;width: 8%'> <input type='Number' name='collectionsal["+i+"].values' placeholder='00:00' class='form-control shubha' readonly='readonly' style='text-align: center;' value="+attendance.getTotalhour()+"></td>");
				    }else if (leaveid>0) {
				    	buffer.append("<td style='text-align: center;width: 8%'> <input type='Number' name='collectionsal["+i+"].values' placeholder='00:00' class='form-control shubha' readonly='readonly' style='text-align: center;' value="+attendance.getTotalhour()+"></td>");
					}else if (holiday){
						buffer.append("<td style='text-align: center;width: 8%'> <input type='Number' name='collectionsal["+i+"].values' placeholder='00:00' class='form-control shubha' readonly='readonly' style='text-align: center;' value="+attendance.getTotalhour()+"></td>");
					}
				    else{
				    	buffer.append("<td style='text-align: center;width: 8%'> <input type='Number' name='collectionsal["+i+"].values' placeholder='00:00' class='form-control shubha' readonly='readonly' style='text-align: center;' value="+attendance.getTotalhour()+"></td>");
				    }
				buffer.append("<td class='hidden'> <input type='hidden' value="+attendance2.getId()+" name='collectionsal["+i+"].id'> </td>");
				i++;
				}
				
				buffer.append("</tr>");
				 fromDate=attendance4.getFromdate();
				 toDate=attendance4.getTodate();
				  startDate = formatter.parse(fromDate);
					 endDate = formatter.parse(toDate);
					 start = Calendar.getInstance();
					start.setTime(startDate);
					 end = Calendar.getInstance();
					end.setTime(endDate);
					  buffer.append("<tr>");
					  buffer.append("<td></td>");
				for (Date date = start.getTime(); start.before(end); start.add(Calendar.DATE, 1), date = start.getTime()) {
				    Attendance  attendance = new Attendance();
					System.out.println(date);
					System.out.println(simpleDateformat.format(date));
				    String strDate = formatter.format(date);
				    attendance.setDate(strDate);
				    attendance.setDays(simpleDateformat.format(date));
				    Attendance attendance2=attendanceDAO.getpayroll_child_request_attendance(empid,strDate,parentid);
				    attendance.setTotalhour(DateTimeUtils.isNull(attendance2.getTotalhour()));
				    attendance.setId(attendance2.getId());
				    attendance.setEmp_id(empid);
				    Attendance attend=attendanceDAO.getpayroll_daytoday_attendance(empid,strDate);
				    int leaveid=attendanceDAO.getleavestatus(strDate,toDate,empid);
				    boolean holiday=attendanceDAO.getholidaystatus(strDate);
				    if(leaveid>0)
				    {
				    	Payroll payroll = payrollMasterDAO.getleaveDetails(""+leaveid);
				    	if(payroll.getLeaveno().equals("1")){
				    		attendance.setLeaveday("C.L");
				    	}else if(payroll.getLeaveno().equals("2")){
				    		attendance.setLeaveday("M.L");
				    	}
				    }else if (holiday) {
				    	attendance.setLeaveday("N.H");
					}else{
						attendance.setLeaveday(DateTimeUtils.isNull(attend.getTotalhour()));
				    	}
				  
				   
				    buffer.append("<td style='padding: 0;text-align: center'>"); 
				    buffer.append("<span>"+attendance.getLeaveday()+"</span>");
				    buffer.append("</td>");
				    
				}
				buffer.append("</tr>");
				buffer.append("</tbody>");
				String sts="";
				if(attendance3.getStatus().equals("1")){
					sts="Save As Draft";
				}else if(attendance3.getStatus().equals("2")){
					sts="Submitted";
				}else if(attendance3.getStatus().equals("3")){
					sts="Approved";
				}else if(attendance3.getStatus().equals("4")){
					sts="Rejected";
				}
				String data=buffer.toString()+"##"+empname+"##"+remark+"##"+sts+"##"+DateTimeUtils.isNull(attendance3.getApproveremark())
				+"##"+employee.getWorkhour()+"##"+attendance4.getTotal_hour()+"##"+DateTimeUtils.isNull(attendance4.getApprovedby())+"##"+DateTimeUtils.isNull(attendance4.getApproveremark());
				response.setContentType("text/html");
			    response.setHeader("Cache-Control", "no-cache");
				response.getWriter().write(data);
				
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
		
	}
	
	
	public String setpunchin1(){
		Connection connection=null;
		try {
			 connection=Connection_provider.getconnection();
			 	Date date = new Date();  
	 	    	AttendanceDAO attendanceDAO=new JDBCAttendanceDAO(connection);
	 	    	PayrollEmployeeDAO employeeDAO=new JDBCPayrollEmployeeDAO(connection);
	 	    	DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
	 	    	String cdate=DateTimeUtils.getUKCurrentDataTime("India").split(" ")[0];
	 	    	cdate=DateTimeUtils.getCommencingDate2(cdate);
			 	String punchinhr=request.getParameter("punchinhr");
			 	String punchinmin=request.getParameter("punchinmin");
			 	String punchouthr=request.getParameter("punchouthr");
			 	String punchoutmin=request.getParameter("punchoutmin");
			 	String punchdate=request.getParameter("punchdate");
			 	String userid=loginInfo.getUserId();
			 	String empid=employeeDAO.getEmployeeEmpid(userid);
			    Calendar cal = Calendar.getInstance();
			    String date1 = dateFormat.format(cal.getTime());
			 	SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");  
			 	//String intime = formatter.format(date);
			 	String status="0",remark="";
			 	SimpleDateFormat sdf1=new SimpleDateFormat("yyyy");
				SimpleDateFormat sdf2=new SimpleDateFormat("MM");
				Date date2=cal.getTime();
			 	String year=sdf1.format(date2);
				String todaymonth=sdf2.format(date2);
				String month_yr=todaymonth+"-"+year;
			 	punchdate=DateTimeUtils.getCommencingDate3(punchdate);
			 	String intime=punchinhr+":"+punchinmin+":00";
			 	String outtime=punchouthr+":"+punchoutmin+":00";
			 	String indatetime=punchdate+" "+intime;
			 	String outdatetime=punchdate+" "+outtime;
			 	
			 	
		 		   String tmp[]=indatetime.split(" ");
				   String onlydate=DateTimeUtils.getCommencingDatemmddyyy(tmp[0]);
				   String datewithtime=onlydate+" "+tmp[1];
				   String tmp1[]=outdatetime.split(" ");
				   String onlydate1=DateTimeUtils.getCommencingDatemmddyyy(tmp1[0]);
				   String datewithtime1=onlydate1+" "+tmp1[1];
				   String difftime=attendanceDAO.differenceOftwoTimes(datewithtime, datewithtime1);
				   
				   String dif1=difftime.split(":")[1];
				   String dif2=difftime.split(":")[0];
				   int timin=Integer.parseInt(dif2)*60+Integer.parseInt(dif1);
			 	  String sal_month=punchdate.split("/")[1];
				   String sal_date=attendanceDAO.getSalaryDatebyid();
				   String  sal_date1=sal_date+"/"+sal_month+"/"+year;
				   sal_date=sal_date1+","+"00:00:00"+" "+"PM";
				   String new_sal_date=DateTimeUtils.calnewdate(sal_date, 30);
				
				  
				   
			 	if(!status.equals("2")){
			 	boolean chkentry=attendanceDAO.checktodaysentry(punchdate,loginInfo);
				   if(!chkentry){
						/*
						 * int res=attendanceDAO.insertPunchIn(empid,userid,intime,date1,1,remark);
						 * }else{
						 */
					 int res=attendanceDAO.insertBackdatepunch(empid,userid,punchdate,2,indatetime,outdatetime,remark,difftime,timin,month_yr);
				   }
			 	}else{
			 		System.out.println("checked");
			 	}
			 	 double totalmin=attendanceDAO.getSumoftime(sal_date1,new_sal_date,empid);
				 double   totaldays=Math.round(totalmin/60)/8;
					String month= DateTimeUtils.getMonthName(Integer.parseInt(sal_month));
					month=month+"-"+year;
					  // long diffofdates=DateTimeUtils.getDifferenceOfTwoDate(new_sal_date,"01/01/2025");
				int insert=attendanceDAO.insertDaysinworksheet(new_sal_date,totaldays,userid,empid,month,totalmin);
				   
	 	    	response.setContentType("text/html");
			    response.setHeader("Cache-Control", "no-cache");
				response.getWriter().write("");
				
			} catch (Exception e) {

			   e.printStackTrace();
			}
		return null;
	}
}


