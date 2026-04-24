package com.apm.DiaryManagement.web.action;



import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.mail.Message;
import javax.mail.internet.InternetAddress;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;

import com.apm.Accounts.eu.bi.AccountsDAO;
import com.apm.Accounts.eu.blogic.jdbc.JDBCAccountsDAO;
import com.apm.Accounts.eu.entity.Accounts;
import com.apm.Appointment.eu.entity.Appointment;
import com.apm.DiaryManagement.eu.bi.ClientDAO;
import com.apm.DiaryManagement.eu.bi.DiaryManagementDAO;
import com.apm.DiaryManagement.eu.bi.NotAvailableSlotDAO;
import com.apm.DiaryManagement.eu.blogic.jdbc.JDBCClientDAO;
import com.apm.DiaryManagement.eu.blogic.jdbc.JDBCDiaryManagentDAO;
import com.apm.DiaryManagement.eu.blogic.jdbc.JDBCNotAvailableSlotDAO;
import com.apm.DiaryManagement.eu.entity.Client;
import com.apm.DiaryManagement.eu.entity.DiaryManagement;
import com.apm.DiaryManagement.eu.entity.NotAvailableSlot;
import com.apm.DiaryManagement.eu.entity.Tdcode;
import com.apm.DiaryManagement.web.common.ApmDate;
import com.apm.DiaryManagement.web.common.DateOfWeek;
import com.apm.DiaryManagement.web.common.Month;
import com.apm.DiaryManagement.web.form.DiaryManagementForm;
import com.apm.Emr.eu.bi.EmrDAO;
import com.apm.Emr.eu.blogic.jdbc.JDBCEmrDAO;
import com.apm.Ipd.eu.bi.IpdDAO;
import com.apm.Log.eu.entity.EmailLetterLog;
import com.apm.Master.eu.bi.MasterDAO;
import com.apm.Master.eu.blogic.jdbc.JDBCMasterDAO;
import com.apm.Master.eu.entity.Master;
import com.apm.Mis.eu.bi.MisChartDAO;
import com.apm.Mis.eu.blogic.jdbc.JDBCMisChartDAO;
import com.apm.Registration.eu.bi.ClinicDAO;
import com.apm.Registration.eu.bi.UserProfileDAO;
import com.apm.Registration.eu.blogic.jdbc.JDBCClinicDAO;
import com.apm.Registration.eu.blogic.jdbc.JDBCUserProfileDAO;
import com.apm.Registration.eu.entity.Location;
import com.apm.Registration.eu.entity.UserProfile;
import com.apm.Report.eu.bi.ChargesReportDAO;
import com.apm.Report.eu.bi.ClientReportDAO;
import com.apm.Report.eu.blogic.jdbc.JDBCChargesReportDAO;
import com.apm.Report.eu.blogic.jdbc.JDBCClientReportDAO;
import com.apm.Report.eu.entity.MisReport;
import com.apm.Report.eu.entity.Report;
import com.apm.Support.eu.bi.UserAdministartionDAO;
import com.apm.Support.eu.blogic.jdbc.JDBCUserAdministration;
import com.apm.Tools.eu.bi.EmailTemplateDAO;
import com.apm.Tools.eu.blogic.jdbc.JDBCEmailTemplateDAO;
import com.apm.Tools.eu.entity.EmailTemplate;
import com.apm.common.eu.blogic.jdbc.Connection_provider;
import com.apm.common.utils.DateTimeUtils;
import com.apm.common.utils.Pagination;
import com.apm.common.web.action.BaseAction;
import com.apm.common.web.common.helper.LoginHelper;
import com.apm.common.web.common.helper.LoginInfo;
import com.apm.common.web.utils.PopulateData;
import com.apm.common.web.utils.PopulateList;
import com.apm.main.common.constants.Constants;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.Preparable;

import atg.taglib.json.util.JSONObject;





public class DiaryManagementAction extends BaseAction implements Preparable, ModelDriven<DiaryManagementForm> {

	DiaryManagementForm diaryManagementForm = new DiaryManagementForm();
	HttpServletRequest request = (HttpServletRequest) ActionContext.getContext().get(ServletActionContext.HTTP_REQUEST);
	HttpServletResponse response = (HttpServletResponse)ActionContext.getContext().get(ServletActionContext.HTTP_RESPONSE);
	HttpSession session = request.getSession(true);
	LoginInfo loginInfo = LoginHelper.getLoginInfo(request);
	
	private Pagination pagination = new Pagination(20, 1);
	
	public String execute() throws Exception {
		
		if(!verifyLogin(request)){
			return "login";
		}
		
		Connection  connection = null;
		try{
			connection = Connection_provider.getconnection();
			DiaryManagementDAO diaryManagementDAO = new JDBCDiaryManagentDAO(connection);
			//int selectedid = Integer.parseInt(request.getParameter("selectedid"));
			//String actiontype=request.getParameter("actiontype");
			String commencing = request.getParameter("commencing");
			//DiaryManagement diary=diaryManagementDAO.getdoctorDetails(selectedid);
			//String docname=diary.getSelectedDiaryUser().split(" ")[0];
			if(diaryManagementForm.getAction().equals("position")){
				
				int prevUserid = diaryManagementDAO.getUserIdToSetPosition(diaryManagementForm.getPrevPosition());
				int curUserid =  diaryManagementDAO.getUserIdToSetPosition(diaryManagementForm.getCurPosition());
				
				int editCurPosition = diaryManagementDAO.updateUserPosition(diaryManagementForm.getPrevPosition(),curUserid);
				int editPrevPosition = diaryManagementDAO.updateUserPosition(diaryManagementForm.getCurPosition(),prevUserid);
			}
			
			if(session.getAttribute("diaryYear")!=null){
				int diaryYear = (Integer)session.getAttribute("diaryYear");
//				System.out.println(diaryYear);
				diaryManagementForm.setDiaryYear(Integer.toString(diaryYear));
				
			}
			
			diaryManagementForm.setAction("");
			/*
			 * if(actiontype.equals("1")) { //diaryManagementForm.setSearchText(docname); }
			 */

		}catch(Exception e){
			e.printStackTrace();
		}
			
		setDiary(pagination,diaryManagementForm.getSearchText());
		/*Calendar now = Calendar.getInstance(); 
		int currentYear = now.get(Calendar.YEAR);
		diaryManagementForm.setDiaryYear(Integer.toString(currentYear));*/
		//set current year
		/*Calendar now = Calendar.getInstance(); 
		int diaryYear = now.get(Calendar.YEAR);
		
		diaryManagementForm.setDiaryYear(Integer.toString(diaryYear));*/
		
		return SUCCESS;
	}
	
	public String rota() throws Exception{
		
		if(!verifyLogin(request)){
			return "login";
		}
		String fromDate = diaryManagementForm.getFromDate();
		String toDate = diaryManagementForm.getToDate();	
		if(diaryManagementForm.getOrderby().equals("")){
			diaryManagementForm.setOrderby("commencing");
		}
		
		if(fromDate.equals("")){
			DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
			Calendar cal = Calendar.getInstance();
			cal.add(Calendar.DATE, -7); 
			fromDate = dateFormat.format(cal.getTime());
			diaryManagementForm.setFromDate(fromDate);
		}
		if(toDate.equals("")){
			DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
			Calendar cal = Calendar.getInstance();
			//cal.add(Calendar.DATE, -7); 
			toDate = dateFormat.format(cal.getTime());
			diaryManagementForm.setToDate(toDate);
		}
		
		
		if(!fromDate.equals("")){
			String temp[]= fromDate.split("/");
			fromDate = temp[2]+"-"+temp[1]+"-"+temp[0];
		}
		if(!toDate.equals("")){
			String temp1[]= toDate.split("/");
			toDate = temp1[2]+"-"+temp1[1]+"-"+temp1[0];
		}
		Connection connection = null;
		try{
		//Connection connection = null;
		connection = Connection_provider.getconnection();
		DiaryManagementDAO diaryManagementDAO = new JDBCDiaryManagentDAO(connection);
		
		
		
		ArrayList<DiaryManagement>rotaCommencingList = diaryManagementDAO.getWeekNameList(fromDate,toDate);
		diaryManagementForm.setRotaCommencingList(rotaCommencingList);
		
		StringBuffer strdate = new StringBuffer();
		for(DiaryManagement diaryManagement : rotaCommencingList){
			strdate.append("'"+diaryManagement.getRotadate()+"'" + ",");
		}
		
		ArrayList<UserProfile>rotaList = diaryManagementDAO.getRotaList(fromDate,toDate,strdate.toString(),diaryManagementForm.getJobtitle());
		diaryManagementForm.setRotaList(rotaList);
		
	
		
		}catch (Exception e) {
			e.printStackTrace();
		}finally{
			
			connection.close();
		}
		
		return "rota";
	}
	
	
	public Pagination getPagination() {
		return pagination;
	}


	public void setPagination(Pagination pagination) {
		this.pagination = pagination;
	}


	public void setDiary(Pagination pagination,String searchText) throws SQLException{
		ArrayList<Month>monthList = new ArrayList<Month>();
		ArrayList<Month>monthtdList = new ArrayList<Month>();
		int year = Integer.parseInt(diaryManagementForm.getDiaryYear());
		
		ArrayList<DiaryManagement>tdUserList = new ArrayList<DiaryManagement>();
		
		Connection connection = null;
		try{
			connection = Connection_provider.getconnection();
			DiaryManagementDAO diaryManagementDAO = new JDBCDiaryManagentDAO(connection);
			
			/*int totalCount = categoryDAO.getCategoryCount(categoryForm.getSearchText(),loginInfo.getUserId());
			pagination.setPreperties(totalCount);
			ArrayList<Category>categoryList = categoryDAO.getCategoryList(pagination,categoryForm.getSearchText(),loginInfo.getUserId());
			categoryForm.setCategoryList(categoryList);
			pagination.setPage_records(categoryList.size());*/
			
			int totalCount = diaryManagementDAO.getDiarymanagementTotalUser(year,loginInfo.getId(),searchText,loginInfo);
			pagination.setPreperties(totalCount);
			ArrayList<DiaryManagement>userList=new ArrayList<DiaryManagement>();
			if(loginInfo.getClinicid1().equals("manasclinic") && loginInfo.getId()!=1) {
				NotAvailableSlotDAO notAvailableSlotDAO=new JDBCNotAvailableSlotDAO(connection);
				String  clinistaff=notAvailableSlotDAO.getclinicstaff(loginInfo.getId());
				userList = diaryManagementDAO.getManasPractionerList(year,loginInfo.getId(),pagination,searchText,loginInfo,clinistaff);

			}else {
				userList = diaryManagementDAO.getPractionerList(year,loginInfo.getId(),pagination,searchText,loginInfo);

			}
			//ArrayList<DiaryManagement>userList = diaryManagementDAO.getPractionerList(year,loginInfo.getId(),pagination,searchText,loginInfo);
			pagination.setPage_records(userList.size());
			
			diaryManagementForm.setTotalRecords(totalCount);
			diaryManagementForm.setPagerecords(Integer.toString(pagination.getPage_records()));
			
			Calendar now = Calendar.getInstance();
			
			
			
			int currentMonth = (now.get(Calendar.MONTH));
			int nextMonth = (now.get(Calendar.MONTH)+2);
			
			if(currentMonth == 11){
				currentMonth = 9;
				nextMonth = 11;
			}
			
			if(diaryManagementForm.isFullCalander()){
				currentMonth = 0;
				nextMonth = 11;
			}
			
			for(int i=currentMonth;i<=nextMonth;i++){
				Month month = new Month();
				
				DiaryManagement diaryManagement = new DiaryManagement();
				
					ArrayList<DateOfWeek>dateStringList = getMonthWiseDateList(year,i);
					month.setDateStringList(dateStringList);
					monthList.add(month);
			}
			
			
			//design td
			for(DiaryManagement diaryManagement : userList){
				monthtdList = new ArrayList<Month>();
				for(int i=currentMonth;i<=nextMonth;i++){
					Month month = new Month();
					ArrayList<DateOfWeek>dateStringList = getMonthWiseDateList(year,i,diaryManagement);
					month.setDateStringList(dateStringList);
					monthtdList.add(month);
					
				}
				diaryManagement.setMonthtdList(monthtdList);
				tdUserList.add(diaryManagement);
			}
			
			/*for(DiaryManagement dm : tdUserList){
				System.out.println(dm.getFirstName());
				for(Month month : dm.getMonthtdList()){
					System.out.println(month.getMonthName());
					for(DateOfWeek dateOfWeek : month.getDateStringList()){
						
						System.out.println(dateOfWeek.getDateName());
						for(Tdcode tdcode : dateOfWeek.getTdcodelist()){
							System.out.println(tdcode.getWeekListName());
						}
					}
				}
			}*/
			
			
			
			diaryManagementForm.setUserList(userList);
			session.setAttribute("userList", userList);
			diaryManagementForm.setTdUserList(tdUserList);
			session.setAttribute("tdUserList", tdUserList);
			
		
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		finally{
			connection.close();
		}
		
		diaryManagementForm.setMonthList(monthList);
		session.setAttribute("monthList", monthList);
		session.setAttribute("year", year);
		diaryManagementForm.setMonthtdList(monthtdList);
		
		int diaryLine = 0;
		for(Month month : monthList){
			for(DateOfWeek dateOfWeek : month.getDateStringList()){
				diaryLine++;
			}
			
		}
		session.setAttribute("diaryLine", diaryLine);
		
	}
	
	
	public String nextprev() throws Exception{
		
		if(!verifyLogin(request)){
			return "login";
		}
		
		try{
			int currentYear = Integer.parseInt(diaryManagementForm.getDiaryYear());
			
			if(diaryManagementForm.getActionType().equals("<< Prev")){
				currentYear = currentYear -1;
				diaryManagementForm.setDiaryYear(Integer.toString(currentYear));
				setDiary(pagination,diaryManagementForm.getSearchText());
			}
			if(diaryManagementForm.getActionType().equals("Next >>")){
				currentYear = currentYear + 1;
				diaryManagementForm.setDiaryYear(Integer.toString(currentYear));
				setDiary(pagination,diaryManagementForm.getSearchText());
			}
			
			session.setAttribute("diaryYear", currentYear);
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		return "nextprev";
	}
	
	
	//repeat slot
	public String repeatslot() throws SQLException{
		if(!verifyLogin(request)){
			return "login";
		}
		String tdcode = request.getParameter("tdcode");
		int weekNumber = Integer.parseInt(request.getParameter("weekNumber")); 
		String diaryYear = request.getParameter("diaryYear");
		int diaryUserid = Integer.parseInt(request.getParameter("diaryUserid"));
		String diaryUser = request.getParameter("diaryUser");
		
		Connection connection = null;
		try{
			connection = Connection_provider.getconnection();
			DiaryManagementDAO diaryManagementDAO = new JDBCDiaryManagentDAO(connection);
			
			ArrayList<DiaryManagement>diaryUserList = diaryManagementDAO.getAppointmentSlotData(Integer.toString(diaryUserid),loginInfo.getId(),tdcode,diaryYear);
			
			ArrayList<Month>montList = (ArrayList<Month>)session.getAttribute("monthList");
			
				int weekcount = 0;
				int oldweekcount = 0;
				
			
				
		
				
				
				
			//delete old data
				/*olb:
				for(Month months : montList){
					for(DateOfWeek dateOfWeek : months.getDateStringList()){
						System.out.println(dateOfWeek.getDateName());
						String usertdcode = dateOfWeek.getDateName() + "-" + diaryUserid;
						
						if(!tdcode.equals(usertdcode)){
							boolean isUsertdCodeExist = diaryManagementDAO.checkUsertdCodeExist(usertdcode,diaryYear);
							if(isUsertdCodeExist){
								int delete = diaryManagementDAO.deleteAppointSlotByTdcode(usertdcode,diaryYear);
							}
							
							
						}
						oldweekcount++;
						if(oldweekcount == weekNumber){
							System.out.println("hello");
							break olb;
						}
					}
				}*/
				
				
		/*	ol:
			for(Month month : montList){
				for(DateOfWeek dateOfWeek : month.getDateStringList()){
					System.out.println(dateOfWeek.getDateName());
					String usertdcode = dateOfWeek.getDateName() + "-" + diaryUserid;
					
					if(!tdcode.equals(usertdcode)){
						boolean isUsertdCodeExist = diaryManagementDAO.checkUsertdCodeExist(usertdcode,diaryYear);
						if (isUsertdCodeExist){
							int delete = diaryManagementDAO.deleteAppointSlotByTdcode(usertdcode,diaryYear);
						}
						
						for(DiaryManagement diaryManagement : diaryUserList){
							diaryManagement.setCommencing("");
							diaryManagement.setTdCode(usertdcode);
							diaryManagement.setDiarUserid(diaryUserid);
							diaryManagement.setYear(diaryYear);
							diaryManagement.setSelectedDiaryUser(diaryUser);
							int result = diaryManagementDAO.saveAppointmentSlot(diaryManagement);
							
						}
					}
					weekcount++;
					if(weekcount == weekNumber){
						System.out.println("hello");
						break ol;
					}
						
				}
				
				
			}*/
			
			
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		finally{
			connection.close();
		}
		
		
		System.out.println("repeat");
		
		return null;
	}
	
	
	//setslot
	
	public String saveslot() throws SQLException{
		if(!verifyLogin(request)){
			return "login";
		}
		DiaryManagement diaryManagement = new DiaryManagement();
		
		int selectedid = Integer.parseInt(request.getParameter("selectedid"));
		
		 String commencing = request.getParameter("commencing");
		/* String temp[] = commencing.split("/");
		 
		 if(temp.length > 0){
			 int dd = Integer.parseInt(temp[0]);
			 int mm = Integer.parseInt(temp[1]);
			 
			 if(dd < 10){
				 temp[0] = "0" + temp[0];
			 }
			 if(mm < 10){
				 temp[1] = "0" + temp[1];
			 }
		 }
		 
		 commencing = temp[0] + "/" + temp[1] + "/" + temp[2] ;*/
		 
	    String temp[] = commencing.split("/");
	  /*String monthNumber = DateTimeUtils.getYearNumber(temp[1]);
		 
		 
	 	int date = Integer.parseInt(temp[0]);
		String tsmpdate = temp[0];
		if(date < 10){
			 tsmpdate = "0"+date+"";
		}
		commencing = tsmpdate + "/" + monthNumber + "/" + temp[2] ;*/
			
		 String selectedDiaryUser = request.getParameter("selectedDiaryUser");;
		 String location = request.getParameter("location");
		 String room = request.getParameter("room");
		 String description = request.getParameter("description");
		 boolean onlineBooking = Boolean.parseBoolean(request.getParameter("onlineBooking"));
		 String sTime = request.getParameter("sTime");;
		 String endTime = request.getParameter("endTime");
		 String apmtDuration = request.getParameter("apmtDuration");
		 String selecteddiaryUserid = request.getParameter("selecteddiaryUserid");
		 String tdCode = request.getParameter("tdcode");
		 String weekname = request.getParameter("weekname");
		 
		 diaryManagement.setWeekFullName(weekname);
		 
		 weekname = DateTimeUtils.getWeekShortName(weekname);
		 
		 Connection connection = null;
		 
		 
		 diaryManagement.setCommencing(DateTimeUtils.getCommencingDate(commencing));
		 diaryManagement.setSelectedDiaryUser(selectedDiaryUser);
		 diaryManagement.setLocation(location);
		 diaryManagement.setRoom(room);
		 diaryManagement.setDescription(description);
		 diaryManagement.setOnlineBooking(onlineBooking);
		 diaryManagement.setSTime(sTime);
		 diaryManagement.setEndTime(endTime);
		 diaryManagement.setApmtDuration(apmtDuration);
		 diaryManagement.setDiarUserid(Integer.parseInt(selecteddiaryUserid));
		 diaryManagement.setTdCode(tdCode);
		 diaryManagement.setWeekName(weekname);
		 diaryManagement.setYear(temp[2]);
		 
		 
		 
		 
		 try{
			 connection = Connection_provider.getconnection();
			 DiaryManagementDAO diaryManagementDAO = new JDBCDiaryManagentDAO(connection);
			
			
			 if(selectedid == 0){
				 int weekNumber = Integer.parseInt(request.getParameter("weekNumber"));
				 boolean wholeweek = Boolean.parseBoolean(request.getParameter("wholeweek"));
				 
				 
				 if(weekNumber > 0){
					 
					boolean monday = Boolean.parseBoolean(request.getParameter("monday"));
					boolean tuesday = Boolean.parseBoolean(request.getParameter("tuesday"));
					boolean wednesday = Boolean.parseBoolean(request.getParameter("wednesday"));
					boolean thursday = Boolean.parseBoolean(request.getParameter("thursday"));
					boolean friday = Boolean.parseBoolean(request.getParameter("friday"));
					boolean saturday = Boolean.parseBoolean(request.getParameter("saturday"));
					boolean sunday = Boolean.parseBoolean(request.getParameter("sunday"));
					 
					 
					
						 
						 int year = (Integer) session.getAttribute("year");
							
						 //split tdcoe
						 String tempTdCode[] = tdCode.split("-");
						 String mondayDate[] = tempTdCode[0].split(" ");
						 
						 commencing = request.getParameter("commencing");
						 String tempCommencing[] = commencing.split("/");
						 
						 
						 int size = 0;
						 if(Integer.parseInt(tempCommencing[0]) >= Integer.parseInt(mondayDate[1])){
							 size = Integer.parseInt(tempCommencing[0]) - Integer.parseInt(mondayDate[1]);
						 }else{
							size =  Integer.parseInt(mondayDate[1]) + Integer.parseInt(tempCommencing[0]);
							size = size - Integer.parseInt(mondayDate[1]);
						 }
						  
						 int count = 0;
						 
						 for(int i=size;i<=6;i++){
							 ApmDate apmDate = DateTimeUtils.getApmDate(commencing,count);
//							 System.out.println(apmDate.getDate() +"/"+ apmDate.getMonth()+"/"+apmDate.getYear());
							 String dd = Integer.toString(apmDate.getDate());
							 String mm = Integer.toString(apmDate.getMonth());
							 if(apmDate.getDate() < 10){
								 dd = "0" + apmDate.getDate();
							 }
							 if(apmDate.getMonth() < 10){
								 mm = "0" + apmDate.getMonth();
							 }
							 
							String sqldate = apmDate.getYear()+"-"+mm+"-"+dd;
							 
							 diaryManagement.setCommencing(sqldate);
					
							 diaryManagement.setWeekFullName(Constants.WEEK_NAME[i]);
							 //diaryManagement.setCommencing("");
							 weekname = DateTimeUtils.getWeekShortName(Constants.WEEK_NAME[i]);
							 diaryManagement.setWeekName(weekname);
							
							 if(monday && Constants.WEEK_NAME[i].equals(Constants.MONDAY)){
								 if(DateTimeUtils.checkispm(sTime) && DateTimeUtils.checkispm(endTime)==false){
									 //first day schedule
									 if(count==0){
										 String fendTime = "24:00";
										 diaryManagement.setEndTime(fendTime);
										 int result = diaryManagementDAO.saveAppointmentSlot(diaryManagement);
										 
										 saveNextDayRecord(commencing, endTime, diaryManagement, connection);
										 
										 fendTime = "24:00";
										 diaryManagement.setEndTime(fendTime);
										 diaryManagement.setSTime(sTime);
										 result = diaryManagementDAO.saveAppointmentSlot(diaryManagement);
										 tuesday = false;
									 }else{
										 //next day schedule
										 String fstime = "01:00";
										 diaryManagement.setEndTime(endTime);
										 diaryManagement.setSTime(fstime);
										 int results = diaryManagementDAO.saveAppointmentSlot(diaryManagement);
										 
										 //if(count!=i){
											 String fendTime = "24:00";
											 diaryManagement.setEndTime(fendTime);
											 diaryManagement.setSTime(sTime);
											 int result = diaryManagementDAO.saveAppointmentSlot(diaryManagement);
										// }
									 }
									 
									
									 
//									 System.out.println("next day");
								 }else{
									 int result = diaryManagementDAO.saveAppointmentSlot(diaryManagement);
								 }
								 
							 }else if(tuesday && Constants.WEEK_NAME[i].equals(Constants.TUESDAY)){
								 if(DateTimeUtils.checkispm(sTime) && DateTimeUtils.checkispm(endTime)==false){
									 //first day schedule
									 if(count==0){
										 String fendTime = "24:00";
										 diaryManagement.setEndTime(fendTime);
										 int result = diaryManagementDAO.saveAppointmentSlot(diaryManagement);
										 
										 saveNextDayRecord(commencing, endTime, diaryManagement, connection);
										 
										 fendTime = "24:00";
										 diaryManagement.setEndTime(fendTime);
										 diaryManagement.setSTime(sTime);
										 result = diaryManagementDAO.saveAppointmentSlot(diaryManagement);
										 wednesday = false;
									 }else{
										 //next day schedule
										 String fstime = "01:00";
										 diaryManagement.setEndTime(endTime);
										 diaryManagement.setSTime(fstime);
										 int results = diaryManagementDAO.saveAppointmentSlot(diaryManagement);
										 
										// if(count!=i){
											 String fendTime = "24:00";
											 diaryManagement.setEndTime(fendTime);
											 diaryManagement.setSTime(sTime);
											 int result = diaryManagementDAO.saveAppointmentSlot(diaryManagement);
										// }
									 }
									 
									 
//									 System.out.println("next day");
								 }else{
									 int result = diaryManagementDAO.saveAppointmentSlot(diaryManagement);
								 }
							 }else if(wednesday && Constants.WEEK_NAME[i].equals(Constants.WEDNEDAY)){
								 if(DateTimeUtils.checkispm(sTime) && DateTimeUtils.checkispm(endTime)==false){
									 //first day schedule
									 if(count==0){
										 String fendTime = "24:00";
										 diaryManagement.setEndTime(fendTime);
										 int result = diaryManagementDAO.saveAppointmentSlot(diaryManagement);
										 
										 saveNextDayRecord(commencing, endTime, diaryManagement, connection);
										 
										 fendTime = "24:00";
										 diaryManagement.setEndTime(fendTime);
										 diaryManagement.setSTime(sTime);
										 result = diaryManagementDAO.saveAppointmentSlot(diaryManagement);
										 thursday = false;
									 }else{
										 //next day schedule
										 String fstime = "01:00";
										 diaryManagement.setEndTime(endTime);
										 diaryManagement.setSTime(fstime);
										 int results = diaryManagementDAO.saveAppointmentSlot(diaryManagement);
										 
										// if(count!=i){
											 String fendTime = "24:00";
											 diaryManagement.setEndTime(fendTime);
											 diaryManagement.setSTime(sTime);
											 int result = diaryManagementDAO.saveAppointmentSlot(diaryManagement);
										// }
									 }
									 
									
//									 System.out.println("next day");
								 }else{
									 int result = diaryManagementDAO.saveAppointmentSlot(diaryManagement);
								 }
							 }else if(thursday && Constants.WEEK_NAME[i].equals(Constants.THUSRDAY)){
								 if(DateTimeUtils.checkispm(sTime) && DateTimeUtils.checkispm(endTime)==false){
									 //first day schedule
									 if(count==0){
										 String fendTime = "24:00";
										 diaryManagement.setEndTime(fendTime);
										 int result = diaryManagementDAO.saveAppointmentSlot(diaryManagement);
										 
										 saveNextDayRecord(commencing, endTime, diaryManagement, connection);
										 
										 fendTime = "24:00";
										 diaryManagement.setEndTime(fendTime);
										 diaryManagement.setSTime(sTime);
										 result = diaryManagementDAO.saveAppointmentSlot(diaryManagement);
										 friday = false;
									 }else{
										 //next day schedule
										 String fstime = "01:00";
										 diaryManagement.setEndTime(endTime);
										 diaryManagement.setSTime(fstime);
										 int results = diaryManagementDAO.saveAppointmentSlot(diaryManagement);
										 
										// if(count!=i){
											 String fendTime = "24:00";
											 diaryManagement.setEndTime(fendTime);
											 diaryManagement.setSTime(sTime);
											 int result = diaryManagementDAO.saveAppointmentSlot(diaryManagement);
										// }
									 }
									 
									 
//									 System.out.println("next day");
								 }else{
									 int result = diaryManagementDAO.saveAppointmentSlot(diaryManagement);
								 }
							 }else if(friday && Constants.WEEK_NAME[i].equals(Constants.FRIDAY)){
								 if(DateTimeUtils.checkispm(sTime) && DateTimeUtils.checkispm(endTime)==false){
									 //first day schedule
									 if(count==0){
										 String fendTime = "24:00";
										 diaryManagement.setEndTime(fendTime);
										 int result = diaryManagementDAO.saveAppointmentSlot(diaryManagement);
										 
										 saveNextDayRecord(commencing, endTime, diaryManagement, connection);
										 
										 fendTime = "24:00";
										 diaryManagement.setEndTime(fendTime);
										 diaryManagement.setSTime(sTime);
										 result = diaryManagementDAO.saveAppointmentSlot(diaryManagement);
										 saturday = false;
									 }else{
										 //next day schedule
										 String fstime = "01:00";
										 diaryManagement.setEndTime(endTime);
										 diaryManagement.setSTime(fstime);
										 int results = diaryManagementDAO.saveAppointmentSlot(diaryManagement);
										 
										// if(count!=i){
											 String fendTime = "24:00";
											 diaryManagement.setEndTime(fendTime);
											 diaryManagement.setSTime(sTime);
											 int result = diaryManagementDAO.saveAppointmentSlot(diaryManagement);
										// }
									 }
									 
									 
//									 System.out.println("next day");
								 }else{
									 int result = diaryManagementDAO.saveAppointmentSlot(diaryManagement);
								 }
							 }else if(saturday && Constants.WEEK_NAME[i].equals(Constants.SATURDAY)){
								 if(DateTimeUtils.checkispm(sTime) && DateTimeUtils.checkispm(endTime)==false){
									 //first day schedule
									 if(count==0){
										 String fendTime = "24:00";
										 diaryManagement.setEndTime(fendTime);
										 int result = diaryManagementDAO.saveAppointmentSlot(diaryManagement);
										 
										 saveNextDayRecord(commencing, endTime, diaryManagement, connection);
										 
										 fendTime = "24:00";
										 diaryManagement.setEndTime(fendTime);
										 diaryManagement.setSTime(sTime);
										 result = diaryManagementDAO.saveAppointmentSlot(diaryManagement);
										 sunday = false;
									 }else{
										 //next day schedule
										 String fstime = "01:00";
										 diaryManagement.setEndTime(endTime);
										 diaryManagement.setSTime(fstime);
										 int results = diaryManagementDAO.saveAppointmentSlot(diaryManagement);
										 
										// if(count!=i){
											 String fendTime = "24:00";
											 diaryManagement.setEndTime(fendTime);
											 diaryManagement.setSTime(sTime);
											 int result = diaryManagementDAO.saveAppointmentSlot(diaryManagement);
										// }
									 }
									 
									 
//									 System.out.println("next day");
								 }else{
									 int result = diaryManagementDAO.saveAppointmentSlot(diaryManagement);
								 }
							 }else if(sunday && Constants.WEEK_NAME[i].equals(Constants.SUNDAY)){
								 if(DateTimeUtils.checkispm(sTime) && DateTimeUtils.checkispm(endTime)==false){
									 //first day schedule
									 if(count==0){
										 String fendTime = "24:00";
										 diaryManagement.setEndTime(fendTime);
										 int result = diaryManagementDAO.saveAppointmentSlot(diaryManagement);
										 
										 saveNextDayRecord(commencing, endTime, diaryManagement, connection);
										 
										 fendTime = "24:00";
										 diaryManagement.setEndTime(fendTime);
										 diaryManagement.setSTime(sTime);
										 result = diaryManagementDAO.saveAppointmentSlot(diaryManagement);
										 monday = false;
									 }else{
										 //next day schedule
										 String fstime = "01:00";
										 diaryManagement.setEndTime(endTime);
										 diaryManagement.setSTime(fstime);
										 int results = diaryManagementDAO.saveAppointmentSlot(diaryManagement);
										 
										// if(count!=i){
											 String fendTime = "24:00";
											 diaryManagement.setEndTime(fendTime);
											 diaryManagement.setSTime(sTime);
											 int result = diaryManagementDAO.saveAppointmentSlot(diaryManagement);
										// }
									 }
									 
									 
//									 System.out.println("next day");
								 }else{
									 int result = diaryManagementDAO.saveAppointmentSlot(diaryManagement);
								 }
							 }else if(!monday && !tuesday && !wednesday && !thursday && !friday && !saturday && !sunday){
								 if(DateTimeUtils.checkispm(sTime) && DateTimeUtils.checkispm(endTime)==false){
									 //first day schedule
									 if(count==0){
										 String fendTime = "24:00";
										 diaryManagement.setEndTime(fendTime);
										 int result = diaryManagementDAO.saveAppointmentSlot(diaryManagement);
									 }
									 
									 //next day schedule
									 saveNextDayRecord(commencing, endTime, diaryManagement, connection);
									 
									 String fendTime = "24:00";
									 diaryManagement.setEndTime(fendTime);
									 diaryManagement.setSTime(sTime);
									 int result = diaryManagementDAO.saveAppointmentSlot(diaryManagement);
									 
//									 System.out.println("next day");
								 }else{
									 int result = diaryManagementDAO.saveAppointmentSlot(diaryManagement);
								 }
							 }
							 
							 
							 count++;
						 }
						 
						 
//						 System.out.println("repeat");

						 if(weekNumber > 1){
							 
							 	String dt = diaryManagement.getCommencing();
								SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
								Calendar c = Calendar.getInstance();
								c.setTime(sdf.parse(dt));
								
								weekNumber = weekNumber-1;
								
								for(int i=0;i<weekNumber;i++){
									
									
									
									for(int j=0;j<=6;j++){
										
										c.add(Calendar.DATE, 1);  // number of days to add
										dt = sdf.format(c.getTime());  // dt is now the new date
										c.setTime(sdf.parse(dt));
										
										if(j==0){
											String date[] = dt.split("-");
											
											String monthName = DateTimeUtils.getMonthName(date[1]);
											
											String tdcode = monthName + " " + date[2] + "-" +diaryManagement.getDiarUserid();
											
											diaryManagement.setTdCode(tdcode);
											
											
										}
										
										diaryManagement.setCommencing(dt);
										 commencing = DateTimeUtils.getCommencingDate2(dt);
										
										String wcdate[] = dt.split("-");
										
										
										
										//set weekname
										int wyear = Integer.parseInt(wcdate[0]);
										int month = Integer.parseInt(wcdate[1]);
										int day = Integer.parseInt(wcdate[2]);
										
										String cweekName = DateTimeUtils.getWeekName(wyear,month,day);
										
//										System.out.println(dt);
//										System.out.println(cweekName);
										
										diaryManagement.setWeekFullName(cweekName);
										
										String weekShortName = DateTimeUtils.getWeekShortName(cweekName);
										diaryManagement.setWeekName(weekShortName);
										
										if(monday && cweekName.equals(Constants.MONDAY)){
											
											 if(DateTimeUtils.checkispm(sTime) && DateTimeUtils.checkispm(endTime)==false){
												 //first day schedule
												 if(j==0){
													 String fendTime = "24:00";
													 diaryManagement.setEndTime(fendTime);
													 int result = diaryManagementDAO.saveAppointmentSlot(diaryManagement);
													 
													
													 saveNextDayRecord(commencing, endTime, diaryManagement, connection);
													 
													 fendTime = "24:00";
													 diaryManagement.setEndTime(fendTime);
													 diaryManagement.setSTime(sTime);
													 result = diaryManagementDAO.saveAppointmentSlot(diaryManagement);
													 tuesday = false;
												 }
													 //next day schedule
													 String fstime = "01:00";
													 diaryManagement.setEndTime(endTime);
													 diaryManagement.setSTime(fstime);
													 int results = diaryManagementDAO.saveAppointmentSlot(diaryManagement);
													 
													// if(count!=i){
														 String fendTime = "24:00";
														 diaryManagement.setEndTime(fendTime);
														 diaryManagement.setSTime(sTime);
														 int result = diaryManagementDAO.saveAppointmentSlot(diaryManagement);
													// }
												 
												 
												 
//												 System.out.println("next day");
											 }else{
												 boolean checkSlotExist = diaryManagementDAO.checkSlotExist(dt,diaryManagement);
													if(!checkSlotExist){
														int result = diaryManagementDAO.saveAppointmentSlot(diaryManagement);
													}
											 }
											
											
											
										}else if(tuesday && cweekName.equals(Constants.TUESDAY)){
											 if(DateTimeUtils.checkispm(sTime) && DateTimeUtils.checkispm(endTime)==false){
												 //first day schedule
												 if(j==0){
													 String fendTime = "24:00";
													 diaryManagement.setEndTime(fendTime);
													 int result = diaryManagementDAO.saveAppointmentSlot(diaryManagement);
													 
													 saveNextDayRecord(commencing, endTime, diaryManagement, connection);
													 
													 fendTime = "24:00";
													 diaryManagement.setEndTime(fendTime);
													 diaryManagement.setSTime(sTime);
													 result = diaryManagementDAO.saveAppointmentSlot(diaryManagement);
													 wednesday = false;
												 }
													 //next day schedule
													 String fstime = "01:00";
													 diaryManagement.setEndTime(endTime);
													 diaryManagement.setSTime(fstime);
													 int results = diaryManagementDAO.saveAppointmentSlot(diaryManagement);
													 
													// if(count!=i){
														 String fendTime = "24:00";
														 diaryManagement.setEndTime(fendTime);
														 diaryManagement.setSTime(sTime);
														 int result = diaryManagementDAO.saveAppointmentSlot(diaryManagement);
													// }
												 
												 
												 
//												 System.out.println("next day");
											 }else{
												 boolean checkSlotExist = diaryManagementDAO.checkSlotExist(dt,diaryManagement);
													if(!checkSlotExist){
														int result = diaryManagementDAO.saveAppointmentSlot(diaryManagement);
													}
											 }
											
										}else if(wednesday && cweekName.equals(Constants.WEDNEDAY)){
											 if(DateTimeUtils.checkispm(sTime) && DateTimeUtils.checkispm(endTime)==false){
												 //first day schedule
												 if(j==0){
													 String fendTime = "24:00";
													 diaryManagement.setEndTime(fendTime);
													 int result = diaryManagementDAO.saveAppointmentSlot(diaryManagement);
													 
													 saveNextDayRecord(commencing, endTime, diaryManagement, connection);
													 
													 fendTime = "24:00";
													 diaryManagement.setEndTime(fendTime);
													 diaryManagement.setSTime(sTime);
													 result = diaryManagementDAO.saveAppointmentSlot(diaryManagement);
													 thursday = false;
												 }
													 //next day schedule
													 String fstime = "01:00";
													 diaryManagement.setEndTime(endTime);
													 diaryManagement.setSTime(fstime);
													 int results = diaryManagementDAO.saveAppointmentSlot(diaryManagement);
													 
													// if(count!=i){
														 String fendTime = "24:00";
														 diaryManagement.setEndTime(fendTime);
														 diaryManagement.setSTime(sTime);
														 int result = diaryManagementDAO.saveAppointmentSlot(diaryManagement);
													// }
												 
												 
												 
//												 System.out.println("next day");
											 }else{
												 boolean checkSlotExist = diaryManagementDAO.checkSlotExist(dt,diaryManagement);
													if(!checkSlotExist){
														int result = diaryManagementDAO.saveAppointmentSlot(diaryManagement);
													}
											 }
											
										}else if(thursday && cweekName.equals(Constants.THUSRDAY)){
											 if(DateTimeUtils.checkispm(sTime) && DateTimeUtils.checkispm(endTime)==false){
												 //first day schedule
												 if(j==0){
													 String fendTime = "24:00";
													 diaryManagement.setEndTime(fendTime);
													 int result = diaryManagementDAO.saveAppointmentSlot(diaryManagement);
													 
													 saveNextDayRecord(commencing, endTime, diaryManagement, connection);
													 
													 fendTime = "24:00";
													 diaryManagement.setEndTime(fendTime);
													 diaryManagement.setSTime(sTime);
													 result = diaryManagementDAO.saveAppointmentSlot(diaryManagement);
													 friday = false;
												 }
													 //next day schedule
													 String fstime = "01:00";
													 diaryManagement.setEndTime(endTime);
													 diaryManagement.setSTime(fstime);
													 int results = diaryManagementDAO.saveAppointmentSlot(diaryManagement);
													 
													// if(count!=i){
														 String fendTime = "24:00";
														 diaryManagement.setEndTime(fendTime);
														 diaryManagement.setSTime(sTime);
														 int result = diaryManagementDAO.saveAppointmentSlot(diaryManagement);
													// }
												 
												 
												 
//												 System.out.println("next day");
											 }else{
												 boolean checkSlotExist = diaryManagementDAO.checkSlotExist(dt,diaryManagement);
													if(!checkSlotExist){
														int result = diaryManagementDAO.saveAppointmentSlot(diaryManagement);
													}
											 }
											
										}else if(friday && cweekName.equals(Constants.FRIDAY)){
											 if(DateTimeUtils.checkispm(sTime) && DateTimeUtils.checkispm(endTime)==false){
												 //first day schedule
												 if(j==0){
													 String fendTime = "24:00";
													 diaryManagement.setEndTime(fendTime);
													 int result = diaryManagementDAO.saveAppointmentSlot(diaryManagement);
													 
													 saveNextDayRecord(commencing, endTime, diaryManagement, connection);
													 
													 fendTime = "24:00";
													 diaryManagement.setEndTime(fendTime);
													 diaryManagement.setSTime(sTime);
													 result = diaryManagementDAO.saveAppointmentSlot(diaryManagement);
													 saturday = false;
												 }
													 //next day schedule
													 String fstime = "01:00";
													 diaryManagement.setEndTime(endTime);
													 diaryManagement.setSTime(fstime);
													 int results = diaryManagementDAO.saveAppointmentSlot(diaryManagement);
													 
													// if(count!=i){
														 String fendTime = "24:00";
														 diaryManagement.setEndTime(fendTime);
														 diaryManagement.setSTime(sTime);
														 int result = diaryManagementDAO.saveAppointmentSlot(diaryManagement);
													// }
												 
												 
												 
//												 System.out.println("next day");
											 }else{
												 boolean checkSlotExist = diaryManagementDAO.checkSlotExist(dt,diaryManagement);
													if(!checkSlotExist){
														int result = diaryManagementDAO.saveAppointmentSlot(diaryManagement);
													}
											 }
											
										}else if(saturday && cweekName.equals(Constants.SATURDAY)){
											 if(DateTimeUtils.checkispm(sTime) && DateTimeUtils.checkispm(endTime)==false){
												 //first day schedule
												 if(j==0){
													 String fendTime = "24:00";
													 diaryManagement.setEndTime(fendTime);
													 int result = diaryManagementDAO.saveAppointmentSlot(diaryManagement);
													 
													 saveNextDayRecord(commencing, endTime, diaryManagement, connection);
													 
													 fendTime = "24:00";
													 diaryManagement.setEndTime(fendTime);
													 diaryManagement.setSTime(sTime);
													 result = diaryManagementDAO.saveAppointmentSlot(diaryManagement);
													 sunday = false;
												 }
													 //next day schedule
													 String fstime = "01:00";
													 diaryManagement.setEndTime(endTime);
													 diaryManagement.setSTime(fstime);
													 int results = diaryManagementDAO.saveAppointmentSlot(diaryManagement);
													 
													// if(count!=i){
														 String fendTime = "24:00";
														 diaryManagement.setEndTime(fendTime);
														 diaryManagement.setSTime(sTime);
														 int result = diaryManagementDAO.saveAppointmentSlot(diaryManagement);
													// }
												 
												 
												 
//												 System.out.println("next day");
											 }else{
												 boolean checkSlotExist = diaryManagementDAO.checkSlotExist(dt,diaryManagement);
													if(!checkSlotExist){
														int result = diaryManagementDAO.saveAppointmentSlot(diaryManagement);
													}
											 }
											
										}else if(sunday && cweekName.equals(Constants.SUNDAY)){
											 if(DateTimeUtils.checkispm(sTime) && DateTimeUtils.checkispm(endTime)==false){
												 //first day schedule
												 if(j==0){
													 String fendTime = "24:00";
													 diaryManagement.setEndTime(fendTime);
													 int result = diaryManagementDAO.saveAppointmentSlot(diaryManagement);
													 
													 saveNextDayRecord(commencing, endTime, diaryManagement, connection);
													 
													 fendTime = "24:00";
													 diaryManagement.setEndTime(fendTime);
													 diaryManagement.setSTime(sTime);
													 result = diaryManagementDAO.saveAppointmentSlot(diaryManagement);
													 monday = false;
												 }
													 //next day schedule
													 String fstime = "01:00";
													 diaryManagement.setEndTime(endTime);
													 diaryManagement.setSTime(fstime);
													 int results = diaryManagementDAO.saveAppointmentSlot(diaryManagement);
													 
													// if(count!=i){
														 String fendTime = "24:00";
														 diaryManagement.setEndTime(fendTime);
														 diaryManagement.setSTime(sTime);
														 int result = diaryManagementDAO.saveAppointmentSlot(diaryManagement);
													// }
												
												 
												 
//												 System.out.println("next day");
											 }else{
												 boolean checkSlotExist = diaryManagementDAO.checkSlotExist(dt,diaryManagement);
													if(!checkSlotExist){
														int result = diaryManagementDAO.saveAppointmentSlot(diaryManagement);
													}
											 }
										}else if(!monday && !tuesday && !wednesday && !thursday && !friday && !saturday && !sunday){
											int result = diaryManagementDAO.saveAppointmentSlot(diaryManagement);
										}
										
										
									}
									
									
									//c.add(Calendar.DATE, 7);  // number of days to add
								}
								
								
							
						 }	
						 
					 
					 
					 
					 
					 	
						
						
					
					 
					 
					 
					 
										 
					 
			 }else{
//					 System.out.println("non repeat");
					 if(DateTimeUtils.checkispm(sTime) && DateTimeUtils.checkispm(endTime)==false){
						 //first day schedule
						 String fendTime = "24:00";
						 diaryManagement.setEndTime(fendTime);
						 int result = diaryManagementDAO.saveAppointmentSlot(diaryManagement);
						 
						 //next day schedule
						 saveNextDayRecord(commencing, endTime, diaryManagement, connection);
						 
//						 System.out.println("next day");
					 }else{
						 int result = diaryManagementDAO.saveAppointmentSlot(diaryManagement);
					 }
					
				 }
				 
			 }else{
				 if(DateTimeUtils.checkispm(sTime) && DateTimeUtils.checkispm(endTime)==false){
					 //first day schedule
					 String fendTime = "24:00";
					 diaryManagement.setEndTime(fendTime);
					 int result = diaryManagementDAO.updateAppointment(selectedid,diaryManagement);
					 
					 //save next day schedule
					 saveNextDayRecord(commencing, endTime, diaryManagement, connection);
				 }else{
					 int result = diaryManagementDAO.updateAppointment(selectedid,diaryManagement);
				 }
				 
			 }
			
			 
		 }catch (Exception e) {
			e.printStackTrace();
		}
		 finally{
				connection.close();
			}
		return null;
	}
	
	
	public void saveNextDayRecord(String commencing,String endTime, DiaryManagement diaryManagement,Connection connection  ) throws ParseException{
		 String fsTime = "01:00";
		 diaryManagement.setSTime(fsTime);
		 
		 String templ[] = commencing.split("/");
		 commencing = templ[2] + "/" + templ[1] + "/" + templ[0];
		 
		 DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
		Calendar cal = Calendar.getInstance();
		cal.setTime(new Date(commencing));
		cal.add(Calendar.DATE, 1); 
		commencing = dateFormat.format(cal.getTime());
		
		String tems[] = commencing.split("/");
		commencing = tems[0] + "-" + tems[1] + "-" + tems[2];
		diaryManagement.setEndTime(endTime);
		diaryManagement.setCommencing(commencing);
		
		String wcdate[] = commencing.split("-");
		
		//set weekname
		int wyear = Integer.parseInt(wcdate[0]);
		int month = Integer.parseInt(wcdate[1]);
		int day = Integer.parseInt(wcdate[2]);
		
		String cweekName = DateTimeUtils.getWeekName(wyear,month,day);
		diaryManagement.setWeekFullName(cweekName);
		String weekShortName = DateTimeUtils.getWeekShortName(cweekName);
		diaryManagement.setWeekName(weekShortName);
		
		DiaryManagementDAO diaryManagementDAO = new JDBCDiaryManagentDAO(connection);
		boolean checkSlotExist = diaryManagementDAO.checkSlotExist(commencing,diaryManagement);
		if(!checkSlotExist){
			int result = diaryManagementDAO.saveAppointmentSlot(diaryManagement);
		}
	}
	
	
	public String del() throws Exception{
		
		
		
		if(!verifyLogin(request)){
			return "login";
		}
		
		String selectedid = request.getParameter("selectedid");
//		System.out.println(selectedid);
		
		Connection connection = null;
		try{
			
			 connection = Connection_provider.getconnection();
			 DiaryManagementDAO diaryManagementDAO = new JDBCDiaryManagentDAO(connection);
			 
			 int del = diaryManagementDAO.deleteDiarySlot(selectedid);
			 
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			
			connection.close();
		}
		
		
		return "deld";
	}

	//delete slot
	
	public String deleteslot() throws SQLException{
		if(!verifyLogin(request)){
			return "login";
		}
		Connection connection = null;
		try{
			
			 connection = Connection_provider.getconnection();
			 DiaryManagementDAO diaryManagementDAO = new JDBCDiaryManagentDAO(connection);
			 
			 int selectedid = Integer.parseInt(request.getParameter("selectedid"));
//			 System.out.println(selectedid);
			 
			 int delete = diaryManagementDAO.deleteAppointmentSlot(selectedid);
			
			
		}catch (Exception e) {
			e.printStackTrace();
		}finally{
			connection.close();
		}
		
		return null;
	}
	
	
	public String start() throws SQLException{
		if(!verifyLogin(request)){
			return "login";
		}
		Connection connection = null;
		
		try{
			connection = Connection_provider.getconnection();
			DiaryManagementDAO diaryManagementDAO = new JDBCDiaryManagentDAO(connection);
			
			 int selectedid = Integer.parseInt(request.getParameter("selectedid"));
//			 System.out.println(selectedid);
			 
			 	response.setContentType("text/html");
				response.setHeader("Cache-Control", "no-cache");
				response.getWriter().write(""+selectedid+""); 
			 
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		finally{
			connection.close();
		}
		return null;
	}
	


	public DiaryManagementForm getModel() {
		
		return diaryManagementForm;
	}
	
	
	private ArrayList<DateOfWeek> getMonthWiseDateList(int year, int month) throws Exception {
		ArrayList<DateOfWeek>list = new ArrayList<DateOfWeek>();
		
		try{
			
			
			Calendar c = Calendar.getInstance();
		    c.set( Calendar.YEAR, year );
		    c.set( Calendar.MONTH , month);
		    c.set( Calendar.DAY_OF_MONTH, 0 );
		    c.add( Calendar.DAY_OF_MONTH, -1 );

		    //System.out.println( c.getTime() );

		    int mondaysCount = 0;

		    while ( mondaysCount != 5 ) {
		    	DateOfWeek dateOfWeek = new DateOfWeek();
		        c.add( Calendar.DAY_OF_MONTH, 1 );
		        if ( c.get( Calendar.DAY_OF_WEEK ) == Calendar.MONDAY ) {
		            mondaysCount++; 
		            
		            String temp[] = c.getTime().toString().split(" ");
		            String dateStr = temp[2] + " " + temp[1];
		            dateOfWeek.setDateName(dateStr);
		            
		             
		             if(c.get(Calendar.MONTH) == month){
		            	 list.add(dateOfWeek);
		             }
		            //System.out.println(c.getTime() );
		            
		        }       
		    }
			
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		
		return list;
	}
	
	
	private ArrayList<DateOfWeek> getMonthWiseDateList(int year, int month,DiaryManagement diaryManagement) throws Exception {
		ArrayList<DateOfWeek>list = new ArrayList<DateOfWeek>();
		
		String weekListName = "";
		String colorName = "";
		
		
		try{
			
			
			Calendar c = Calendar.getInstance();
		    c.set( Calendar.YEAR, year );
		    c.set( Calendar.MONTH , month);
		    c.set( Calendar.DAY_OF_MONTH, 0 );
		    c.add( Calendar.DAY_OF_MONTH, -1 );

		    //System.out.println( c.getTime() );

		    int mondaysCount = 0;

		    while ( mondaysCount != 5 ) {
		    	DateOfWeek dateOfWeek = new DateOfWeek();
		        c.add( Calendar.DAY_OF_MONTH, 1 );
		        if ( c.get( Calendar.DAY_OF_WEEK ) == Calendar.MONDAY ) {
		            mondaysCount++; 
		            
		            String temp[] = c.getTime().toString().split(" ");
		            String dateStr = temp[1] + " " + temp[2];
		            dateOfWeek.setDateName(dateStr);
		            dateOfWeek.setTdDateName(dateStr+"-"+diaryManagement.getDiarUserid());
		            
		            
		            ArrayList<Tdcode>tdcodelist = diaryManagement.getTdDataList();
		            for(Tdcode tdcode : tdcodelist){
		            	if(tdcode.getTdCode().equals(dateOfWeek.getTdDateName())){
		            		weekListName = tdcode.getWeekListName();
		            		colorName = diaryManagement.getDiaryColor();
		            		
		            		 dateOfWeek.setWeekListname(weekListName);
		 		            dateOfWeek.setColorName(colorName);
		            	}
		            }
		            
		           

		            if(c.get(Calendar.MONTH) == month){
		            	 list.add(dateOfWeek);
		             }
		            //System.out.println(c.getTime() );
		            
		        }       
		    }
			
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		
		return list;
	}
	
	
	public String eventExist() throws Exception{
		if(!verifyLogin(request)){
			return "login";
		}
		Connection connection = null;
		try{
			connection = Connection_provider.getconnection();
			DiaryManagementDAO diaryManagementDAO = new JDBCDiaryManagentDAO(connection);
			
			//var url = "eventExistDiaryMangent?selectedid="+editappointmentid+"&commencing="+commencing+"&selectedDiaryUser="+selectedDiaryUser+"&location="+location+"&sTime="+sTime+"&endTime="+endTime+" ";
			
			String commencing = request.getParameter("commencing");
			String location = request.getParameter("location");
			String diaryuserId = request.getParameter("selectedDiaryUser");
			String starttime = request.getParameter("sTime");
			String endtime = request.getParameter("endTime");
			int editAppointId = Integer.parseInt(request.getParameter("selectedid"));
			
			String temp[] = commencing.split("/");
			commencing = temp[2] + "-" + temp[1] + "-" + temp[0];
			
			boolean checkEventExist = diaryManagementDAO.checkEventAllreadyExist(commencing,location, diaryuserId,starttime,endtime);
			if(editAppointId!=0){
				DiaryManagement diaryManagement = diaryManagementDAO.getAvailableSlotdata(editAppointId);
				
				if(starttime.equals(diaryManagement.getSTime()) && endtime.equals(diaryManagement.getEndTime())){
					checkEventExist = false;
				}else{
					
					 checkEventExist = diaryManagementDAO.checkEventAllreadyExist(commencing,location, diaryuserId,starttime,endtime,editAppointId);
				}
			}
			
			
			if(checkEventExist){
				int coutnEsistingSlot = diaryManagementDAO.coutnEsistingSlot(commencing,location, diaryuserId,starttime,endtime,editAppointId);
				
				if(coutnEsistingSlot==1){
					String existStartTime = diaryManagementDAO.getExistStartTime(commencing,location, diaryuserId,starttime,endtime,editAppointId);
					
					
				/*	String duration = DateTimeUtils.getDuration(starttime, endtime);
					
					System.out.println(duration);
					
					String sumoftime = DateTimeUtils.getSumofTime(starttime,duration);*/
					
					if(existStartTime.equals(endtime)){
						checkEventExist = false;
					}
					
					
				}
				if(editAppointId!=0){
					if(coutnEsistingSlot==1 || coutnEsistingSlot==2){
						String duration = DateTimeUtils.getDuration(starttime, endtime);
						
						String existStartTime = diaryManagementDAO.getEditExistStartTime(commencing,location, diaryuserId,starttime,endtime,editAppointId);
						
						
						/*System.out.println(duration);
						
						String sumoftime = DateTimeUtils.getSumofTime(starttime,duration);*/
						
						if(existStartTime.equals(endtime)){
							checkEventExist = false;
						}
						
					}
				}
				
				
			}
			
			response.setContentType("text/html");
			response.setHeader("Cache-Control", "no-cache");
			response.getWriter().write(""+checkEventExist+""); 
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			
			connection.close();
		}
		
		return null;
	}
	
	public String check() throws Exception{
		
		if(!verifyLogin(request)){
			return "login";
		}
		
		String selectedid = request.getParameter("selectedid");
		
		Connection connection = null;
		try{
			connection = Connection_provider.getconnection();
			DiaryManagementDAO diaryManagementDAO = new JDBCDiaryManagentDAO(connection);
			
			boolean checkAppmtExist = diaryManagementDAO.checkApmtExist(selectedid);
			
			response.setContentType("text/html");
			response.setHeader("Cache-Control", "no-cache");
			response.getWriter().write(""+checkAppmtExist+""); 
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			
			connection.close();
		}
		
		return null;
	}
	
	


	public void prepare() throws Exception {
		
		diaryManagementForm.setStartTimeList(PopulateList.startTimeList());
		diaryManagementForm.setEndTimeList(PopulateList.endTimeList());
		diaryManagementForm.setApmtDurationList(PopulateList.apmBlocktDurationList());
		
		Connection connection = null;
		
		try{
			connection = Connection_provider.getconnection();
			DiaryManagementDAO diaryManagementDAO = new JDBCDiaryManagentDAO(connection);
			
			ArrayList<Location>locationList = diaryManagementDAO.getLocationList(loginInfo.getId());
			diaryManagementForm.setLocationList(locationList);
			
			ArrayList<String>weekNameList = new ArrayList<String>();
			weekNameList.add("Monday");
			weekNameList.add("Tuesday");
			weekNameList.add("Wednesday");
			weekNameList.add("Thursday");
			weekNameList.add("Friday");
			weekNameList.add("Saturday");
			weekNameList.add("Sunday");
			
			diaryManagementForm.setWeekNameList(weekNameList);
			
			
			UserProfileDAO userProfileDAO = new JDBCUserProfileDAO(connection);
			ArrayList<String> jobTitleList = userProfileDAO.getJobTitleList();
			diaryManagementForm.setJobTitleList(jobTitleList);
			
			ArrayList<Master>wardList = diaryManagementDAO.getWardList();
			diaryManagementForm.setWardList(wardList);
			
			jobTitleList = userProfileDAO.getJobTitleList();
			diaryManagementForm.setJobTitleList(jobTitleList);
		
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		finally{
			connection.close();
		}
	}
	

	public String widgets() throws Exception{
		
	   Connection connection=null;
		try {
			connection=Connection_provider.getconnection();
			UserProfileDAO userProfileDAO = new JDBCUserProfileDAO(connection);
			ArrayList<String> jobTitleList = userProfileDAO.getJobTitleList();
			diaryManagementForm.setJobTitleList(jobTitleList);
			
		} catch (Exception e) {

		   e.printStackTrace();
		}
		finally {
			connection.close();
		}
	
		return "widgets";
	}
	
	public String mainsetting() {
		
	   return "mainsetting";	
	}
	
	public String miswidget(){
		return "miswidget";
	}

public String update() throws Exception  {
		
		Connection connection=null;
		try {
			connection=Connection_provider.getconnection();
			DiaryManagementDAO diaryManagementDAO=new JDBCDiaryManagentDAO(connection);
			DiaryManagement diaryManagement=new DiaryManagement();
			diaryManagement.setRegistrationdash(diaryManagementForm.isRegistrationdash());
			diaryManagement.setOpd(diaryManagementForm.isOpd());
			diaryManagement.setIpd(diaryManagementForm.isIpd());
			diaryManagement.setEmr(diaryManagementForm.isEmr());
//			diaryManagement.setPacs(diaryManagementForm.isPacs());
			diaryManagement.setDischarge(diaryManagementForm.isDischarge());
			diaryManagement.setMedicine(diaryManagementForm.isMedicine());
			diaryManagement.setInvestigation(diaryManagementForm.isInvestigation());
			diaryManagement.setBloodbank(diaryManagementForm.isBloodbank());
			diaryManagement.setAccounts(diaryManagementForm.isAccounts());
			diaryManagement.setPayroll(diaryManagementForm.isPayroll());
			diaryManagement.setExpenses(diaryManagementForm.isExpenses());
			diaryManagement.setInventory(diaryManagementForm.isInventory());
			diaryManagement.setMis(diaryManagementForm.isMis());
			diaryManagement.setConsultants(diaryManagementForm.isConsultants());
			diaryManagement.setPatient(diaryManagementForm.isPatient());
			diaryManagement.setAppointmentfinder(diaryManagementForm.isAppointmentfinder());
			diaryManagement.setSetting(diaryManagementForm.isSetting());
			diaryManagement.setJobtitle(diaryManagementForm.getJobtitle());
			
			/* private boolean packs;
			 private boolean investigation_chart;
			 private boolean sheduler;
			 private boolean housekeeping;
			 private boolean dietery;
			 private boolean cafeteria;
			 private boolean packages;
			 private boolean ambulance;
			 private boolean bank_deposite;
			 private boolean account_reconcilation; 	*/		
			//boolean x = diaryManagementForm.isPacks();
			diaryManagement.setPacks(diaryManagementForm.isPacks());
			diaryManagement.setInvestigation_chart(diaryManagementForm.isInvestigation_chart());
			diaryManagement.setSheduler(diaryManagementForm.isSheduler());
			diaryManagement.setHousekeeping(diaryManagementForm.isHousekeeping());
			diaryManagement.setDietery(diaryManagementForm.isDietery());
			diaryManagement.setCafeteria(diaryManagementForm.isCafeteria());
			diaryManagement.setPackages(diaryManagementForm.isPackages());
			diaryManagement.setAmbulance(diaryManagementForm.isAmbulance());
			diaryManagement.setBank_deposite(diaryManagementForm.isBank_deposite());
			diaryManagement.setAccount_reconcilation(diaryManagementForm.isAccount_reconcilation());
			
			//new terms added for personal widget
			diaryManagement.setOt(diaryManagementForm.isOt());
			diaryManagement.setCasualty(diaryManagementForm.isCasualty());
			diaryManagement.setPharmacy(diaryManagementForm.isPharmacy());
			diaryManagement.setMrd(diaryManagementForm.isMrd());
			diaryManagement.setMarketing(diaryManagementForm.isMarketing());
			diaryManagement.setVoice_recording(diaryManagementForm.isVoice_recording());
			
			//  06 feb 2018
			diaryManagement.setIndent(diaryManagementForm.isIndent());
			diaryManagement.setTpa(diaryManagementForm.isTpa());
			diaryManagement.setNabh_quality(diaryManagementForm.isNabh_quality());
			
			//  08 feb 2018
			diaryManagement.setDoctor_opd(diaryManagementForm.isDoctor_opd());
			diaryManagement.setCathlab(diaryManagementForm.isCathlab());
			diaryManagement.setMyhr(diaryManagementForm.isMyhr());
			diaryManagement.setDaycare(diaryManagementForm.isDaycare());
			diaryManagement.setEmergency_lbl(diaryManagementForm.isEmergency_lbl());
			diaryManagement.setMedicine_barcode(diaryManagementForm.isMedicine_barcode());
			diaryManagement.setRefund(diaryManagementForm.isRefund());
			diaryManagement.setDiscount(diaryManagementForm.isDiscount());
			diaryManagement.setVaccination(diaryManagementForm.isVaccination());
			diaryManagement.setVideo_training(diaryManagementForm.isVideo_training());
			diaryManagement.setSetup_master(diaryManagementForm.isSetup_master());
			diaryManagement.setSale_pharmacy(diaryManagementForm.isSale_pharmacy());
			diaryManagement.setNabh_quality(diaryManagementForm.isNabh_quality());
			diaryManagement.setToken_display(diaryManagementForm.isToken_display());
			diaryManagement.setDelete_invoice(diaryManagementForm.isDelete_invoice());
			int result=diaryManagementDAO.updateRoleAccess(diaryManagement);		
		} catch (Exception e) {

		  e.printStackTrace();
		}
		finally {
			connection.close();
		}
		return "widgets";
	}
	
public String getwidget()throws Exception {
		
	   Connection connection=null;
	   
	   try {
		   connection=Connection_provider.getconnection();
		   
		   String jobtitle=diaryManagementForm.getJobtitle();
		   if(jobtitle!=null){
			    
			    if(jobtitle.equals("")){
			    	jobtitle=loginInfo.getJobTitle();
			    }
		   } else {
			   jobtitle=loginInfo.getJobTitle();
		   }
		   
		   
		   DiaryManagementDAO diaryManagementDAO=new JDBCDiaryManagentDAO(connection); 
		   DiaryManagement diaryManagement=diaryManagementDAO.getRoleAccessofUser(jobtitle);  
		   diaryManagementForm.setSetting(diaryManagement.isSetting());
		   diaryManagementForm.setOpd(diaryManagement.isOpd());
		   diaryManagementForm.setIpd(diaryManagement.isIpd());
		   diaryManagementForm.setEmr(diaryManagement.isEmr());
		   diaryManagementForm.setPacs(diaryManagement.isPacs());
		   diaryManagementForm.setDischarge(diaryManagement.isDischarge());
		   diaryManagementForm.setMedicine(diaryManagement.isMedicine());
		   diaryManagementForm.setInvestigation(diaryManagement.isInvestigation());
		   diaryManagementForm.setBloodbank(diaryManagement.isBloodbank());
		   diaryManagementForm.setAccounts(diaryManagement.isAccounts());
		   diaryManagementForm.setPayroll(diaryManagement.isPayroll());
		   diaryManagementForm.setExpenses(diaryManagement.isExpenses());
		   diaryManagementForm.setInventory(diaryManagement.isInventory());
		   diaryManagementForm.setMis(diaryManagement.isMis());
		   diaryManagementForm.setConsultants(diaryManagement.isConsultants());
		   diaryManagementForm.setPatient(diaryManagement.isPatient());
		   diaryManagementForm.setAppointmentfinder(diaryManagement.isAppointmentfinder());
		   
		   diaryManagementForm.setPacks(diaryManagement.isPacks());
		   diaryManagementForm.setInvestigation_chart(diaryManagement.isInvestigation_chart());
		   diaryManagementForm.setSheduler(diaryManagement.isSheduler());
		   diaryManagementForm.setHousekeeping(diaryManagement.isHousekeeping());
		   diaryManagementForm.setDietery(diaryManagement.isDietery());
		   diaryManagementForm.setCafeteria(diaryManagement.isCafeteria());
		   diaryManagementForm.setPackages(diaryManagement.isPackages());
		   diaryManagementForm.setAmbulance(diaryManagement.isAmbulance());
		   diaryManagementForm.setBank_deposite(diaryManagement.isBank_deposite());
		   diaryManagementForm.setAccount_reconcilation(diaryManagement.isAccount_reconcilation());
		   //ot,casualty,pharmacy,mrd,marketing,voice recorder
		   diaryManagementForm.setJobtitle(jobtitle);
		   
		// get new terms ot,casualty,pharmacy,mrd,marketing,voice recorder
		   diaryManagementForm.setOt(diaryManagement.isOt());
		   diaryManagementForm.setCasualty(diaryManagement.isCasualty());
		   diaryManagementForm.setPharmacy(diaryManagement.isPharmacy());
		   diaryManagementForm.setMrd(diaryManagement.isMrd());
		   diaryManagementForm.setMarketing(diaryManagement.isMarketing());
		   diaryManagementForm.setVoice_recording(diaryManagement.isVoice_recording());
		   
		   //06 feb 18   add widgets 
		   
		   diaryManagementForm.setIndent(diaryManagement.isIndent());
		   diaryManagementForm.setTpa(diaryManagement.isTpa());
		   diaryManagementForm.setNabh_quality(diaryManagement.isNabh_quality());
		  
		   //08 feb 18   add widgets 
		   diaryManagementForm.setDoctor_opd(diaryManagement.isDoctor_opd());
		   diaryManagementForm.setCathlab(diaryManagement.isCathlab());
		   
		   diaryManagementForm.setMyhr(diaryManagement.isMyhr());
		   diaryManagementForm.setDaycare(diaryManagement.isDaycare());
		   diaryManagementForm.setEmergency_lbl(diaryManagement.isEmergency_lbl());
		   diaryManagementForm.setMedicine_barcode(diaryManagement.isMedicine_barcode());
		 //refund,discount,vaccination,video_training,setup_master,sale_pharmacy
		   diaryManagementForm.setRefund(diaryManagement.isRefund());
		   diaryManagementForm.setDiscount(diaryManagement.isDiscount());
		   diaryManagementForm.setVaccination(diaryManagement.isVaccination());
		   diaryManagementForm.setVideo_training(diaryManagement.isVideo_training());
		   diaryManagementForm.setSetup_master(diaryManagement.isSetup_master());
		   diaryManagementForm.setSale_pharmacy(diaryManagement.isSale_pharmacy());
		   diaryManagementForm.setToken_display(diaryManagement.isToken_display());
		   diaryManagementForm.setDelete_invoice(diaryManagement.isDelete_invoice());
		   diaryManagementForm.setRegistrationdash(diaryManagement.isRegistrationdash());
		   
	   } catch (Exception e) {

		   e.printStackTrace();
	   }finally {
		   connection.close();
	   }
	
		return "widgets";
	}
	
	
	
	public String dietary() throws Exception {
		
	    if(!verifyLogin(request)){
	    	return "login";
	    }
		
		return "dietary";
	}
	
	public String packages() throws Exception {
		
		 if(!verifyLogin(request)){
		    	return "login";
		    }
			
			return "packages";
	}
	
	public String marketing()throws Exception {
		 if(!verifyLogin(request)){
		    	return "login";
		    }
		 Connection connection = null;
			try {
				connection = Connection_provider.getconnection();
				ClientReportDAO clientReportDAO = new JDBCClientReportDAO(connection);
				MisChartDAO misChartDAO = new JDBCMisChartDAO(connection);
				AccountsDAO accountsDAO = new JDBCAccountsDAO(connection);
				ClientDAO clientDAO = new JDBCClientDAO(connection);
				MasterDAO masterDAO = new JDBCMasterDAO(connection);
				ArrayList<MisReport> smstemlatelist = clientReportDAO.getSms_Template();
				diaryManagementForm.setSmstemlatelist(smstemlatelist);
				String fromdate = diaryManagementForm.getFromDate();
				String todate = diaryManagementForm.getToDate();
				
				if (fromdate.equals("")) {
					DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
					Calendar cal = Calendar.getInstance();
					// cal.add(Calendar.DATE, -30);
					fromdate = dateFormat.format(cal.getTime());
					

				}
				if (todate.equals("")) {
					DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
					Calendar cal = Calendar.getInstance();
					// cal.add(Calendar.DATE, -7);
					todate = dateFormat.format(cal.getTime());
					

				}
				diaryManagementForm.setToDate(todate);
				diaryManagementForm.setFromDate(fromdate);
				
				fromdate = DateTimeUtils.getCommencingDate1(fromdate);
				todate = DateTimeUtils.getCommencingDate1(todate);
				String action = diaryManagementForm.getAction();
				String mainaction = (String) session.getAttribute("marketingmainaction");
				if(mainaction==null){
					mainaction = diaryManagementForm.getMainaction();
				}else{
					session.removeAttribute("marketingmainaction");
				}
				
				mainaction = DateTimeUtils.isNull(mainaction);
				if(mainaction.equals("")){
					mainaction="marketingdashboard";
				}
				diaryManagementForm.setMainaction(mainaction);
				
				String history_typefilter = diaryManagementForm.getHistory_typefilter();
				history_typefilter = DateTimeUtils.isNull(history_typefilter);
				
				if(history_typefilter.equals("")){
					history_typefilter ="sms";
				}
				diaryManagementForm.setHistory_typefilter(history_typefilter);
				if (action != null) {
					if(action.equals("")){
						action = "paymentmode";
					}
				}else{
					action = "paymentmode";
				}
				session.setAttribute("misfromdate", diaryManagementForm.getFromDate());
				session.setAttribute("mistodate", diaryManagementForm.getToDate());
				
				ArrayList<Accounts> patientViewByPackage = new ArrayList<Accounts>();
				ArrayList<Accounts> accountinfo = new ArrayList<Accounts>();
				ArrayList<Accounts> accountinfo1 = new ArrayList<Accounts>();
				ArrayList<Client> patientbylocation = new ArrayList<Client>();
				
				ArrayList<Master> clienttypelist= masterDAO.getAllClientTypeList();
				diaryManagementForm.setClientTypeList(clienttypelist);
				
				if (action.equals("paymentmode")) {
					String invoicecat = diaryManagementForm.getInvoicecategory();
					if(diaryManagementForm.getInvoicecategory()==null){
						invoicecat = "2";
					}else if(diaryManagementForm.getInvoicecategory().equals("")){
						invoicecat = "2";
					}
					int totalPayment = misChartDAO.getTotalPayment(fromdate, todate, invoicecat, loginInfo.getJobTitle());
					int cashpayment = misChartDAO.getCashPayment(fromdate, todate, invoicecat, loginInfo.getJobTitle());
					int chequepayment = misChartDAO.getChequepaymenyt(fromdate, todate, invoicecat,loginInfo.getJobTitle());
					int cardPayment = misChartDAO.getCardpayment(fromdate, todate, invoicecat,loginInfo.getJobTitle());
					long otherPayment = misChartDAO.getAllOtherPayment(fromdate, todate);
					diaryManagementForm.setOtherPayment(otherPayment);
					diaryManagementForm.setTotalPayment(totalPayment);
					diaryManagementForm.setCashpayment(cashpayment);
					diaryManagementForm.setChequepayment(chequepayment);
					diaryManagementForm.setCardPayment(cardPayment);
					diaryManagementForm.setOtherPayment(otherPayment);
				}
				
				if (action.equals("patientview")) {
					patientbylocation = misChartDAO.getpatientbyLocation(fromdate, todate);
					int index = patientbylocation.size() - 1;
					if (index > 0) {
						diaryManagementForm.setTotalPatient(patientbylocation.get(index).getTotal());
					} else {
						diaryManagementForm.setTotalPatient(0);
					}
					
					int totalOpdPatient = misChartDAO.getTotalOpdPatient(fromdate, todate);
					diaryManagementForm.setTotalOpdPatient(totalOpdPatient);

				}
				if (action.equals("patientviewpackage")) {

					patientViewByPackage = misChartDAO.getPatientViewByPackage(fromdate, todate);
					Accounts act = misChartDAO.getCashCheque(fromdate, todate);

					diaryManagementForm.setPaymodecash(act.getPaymodecash());
					diaryManagementForm.setPaymodecheque(act.getPaymodecheque());

				}
				
				if (action.equals("accountinfo")) {
					String invoicecat = diaryManagementForm.getInvoicecategory();
					if(diaryManagementForm.getInvoicecategory()==null){
						invoicecat = "2";
					}else if(diaryManagementForm.getInvoicecategory().equals("")){
						invoicecat = "2";
					}
					// Account Summary
					long expenseTotal = misChartDAO.getExpenseTotal(fromdate, todate);
					diaryManagementForm.setExpenseTotal(expenseTotal);

					int totalPayment = misChartDAO.getTotalPayment(fromdate, todate, invoicecat,loginInfo.getJobTitle());
					int cashpayment = misChartDAO.getCashPayment(fromdate, todate, invoicecat,loginInfo.getJobTitle());
					int chequepayment = misChartDAO.getChequepaymenyt(fromdate, todate, invoicecat,loginInfo.getJobTitle());
					int cardPayment = misChartDAO.getCardpayment(fromdate, todate, invoicecat,loginInfo.getJobTitle());

					diaryManagementForm.setTotalPayment(totalPayment);
					diaryManagementForm.setCashpayment(cashpayment);
					diaryManagementForm.setChequepayment(chequepayment);
					diaryManagementForm.setCardPayment(cardPayment);

					// Account info
					accountinfo = misChartDAO.getAccountInfoList(fromdate, todate);
					accountinfo1 = misChartDAO.getAccountInfoList1(fromdate, todate);

				}
				
				diaryManagementForm.setPatientbylocation(patientbylocation);
				diaryManagementForm.setAccountinfo1(accountinfo1);
				diaryManagementForm.setAccountinfo(accountinfo);
				diaryManagementForm.setAction(action);
				diaryManagementForm.setPatientviewbypackage(patientViewByPackage);
				session.setAttribute("misChartForm", diaryManagementForm);
				session.setAttribute("graphaction", action);
				
				//Connecting
				ArrayList<Accounts>thirdPartyList = accountsDAO.getThirdPartyList(loginInfo.getId());
				diaryManagementForm.setThirdPartyList(thirdPartyList);
				
				ArrayList<Client> refrenceList = clientDAO.getReferenceList();
				diaryManagementForm.setRefrenceList(refrenceList);

				ArrayList<Master> jobGroupList = masterDAO.getJobTitleList();
				diaryManagementForm.setJobGroupList(jobGroupList);

				ArrayList<Master> disciplineList =  masterDAO.getDisciplineDataList();
				diaryManagementForm.setDisciplineList(disciplineList);
				
				ArrayList<Master> investigationamelist = masterDAO.getInvestigationNameList();
				diaryManagementForm.setInvestigationamelist(investigationamelist);
				
				//History
				ArrayList<Report> marketinghistorylist = clientReportDAO.getMarketingSendList(fromdate,todate,history_typefilter);//fromdate,todate
				diaryManagementForm.setMarketinghistorylist(marketinghistorylist);
				
				ArrayList<Master>medicineList = masterDAO.getMedicineList();
				diaryManagementForm.setMedicineList(medicineList);
			} catch (Exception e) {
				e.printStackTrace();
			}finally{
				connection.close();
			}
		return "marketing";
	}
	
	
	
	
	
	public String letsTalk() throws Exception {
		Connection connection = null;
		if(!verifyLogin(request)){
	    	return "login";
	    }
		try {
			connection = Connection_provider.getconnection();
			int selectedid = loginInfo.getId();
			
			ClinicDAO clinicListDAO = new JDBCClinicDAO(connection);
			com.apm.Registration.eu.entity.Clinic cliniclist = clinicListDAO.getCliniclistDetails(selectedid);
			diaryManagementForm.setClinicname(cliniclist.getClinicName());
			diaryManagementForm.setUserid(cliniclist.getUserId());
			UserAdministartionDAO userAdministartionDAO= new JDBCUserAdministration(connection);
			diaryManagementForm.setModuleList(userAdministartionDAO.getModuleMasterList());
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			connection.close();
		}
		
		return "letsTalk";
	}
	public String submitSupportMail() throws Exception{
		Connection connection=null;
		try {
			connection = Connection_provider.getconnection();
			/*String query = request.getParameter("query");
			String message = request.getParameter("message");*/
			StringBuilder buffer1= new StringBuilder();
			BufferedReader reader = request.getReader();
			String line;
			while ((line = reader.readLine()) != null) {
				buffer1.append(line);
			}
			String data = buffer1.toString();
			JSONObject jsonObject = new JSONObject(data);
			
			String query= jsonObject.getString("query");
			
			String message= jsonObject.getString("message");
			String module=jsonObject.getString("module");
			String altmobno=jsonObject.getString("altmobno");
			
			
			String to ="yuvicare.hims@manasindustry.com";
			String cc="";
			String subject="Query From Support";
			String notes="";
			
			String timeformat = "hh:mm:ss a";
	        SimpleDateFormat obDateFormat = new SimpleDateFormat(timeformat);
	        Calendar time = Calendar.getInstance();
	        String time1 = obDateFormat.format(time.getTime());
			
			DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
			Calendar cal = Calendar.getInstance();
			String date = dateFormat.format(cal.getTime());
			
			int selectedid = loginInfo.getId();
			ClinicDAO clinicListDAO = new JDBCClinicDAO(connection);
			com.apm.Registration.eu.entity.Clinic cliniclist = clinicListDAO.getCliniclistDetails(selectedid);
			
			StringBuffer buffer = new StringBuffer();
			buffer.append("<b>Hospital Name: "+cliniclist.getClinicName()+"</b><br>");
			buffer.append("User Id: "+cliniclist.getUserId()+"<br><br>");
			buffer.append("Query Type: "+query+"<br>");
			buffer.append("Comment: "+message+".<br>");
			buffer.append("Date and Time:"+date+" "+time1+"<br>");
			
			notes = buffer.toString();
			//EmbeddedImageEmailUtil.sendMailFromSupport(to, cc, subject, notes);
			
			DateFormat dateFormat1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		    Calendar cal1 = Calendar.getInstance();
		    String datenew = dateFormat1.format(cal1.getTime());  
		    String jobtile= clinicListDAO.getJobtile(loginInfo.getUserId()); 
			UserAdministartionDAO userAdministartionDAO= new JDBCUserAdministration(connection);
			// will be save to 139 demo
			int x=userAdministartionDAO.sendToMainSupport(loginInfo.getClinicUserid(), loginInfo.getUserId(), query, message, datenew, jobtile,loginInfo.getFullname(),loginInfo.getUserMobileNo(),module,"",altmobno);
			if(x>0){
				String dd[]=datenew.split(" ");
				datenew=DateTimeUtils.getCommencingDate1(dd[0])+" "+dd[1];
				SmsService s = new SmsService();
				String msg="Yuvicare support ticket no: SC"+x+" Created on "+datenew+".You can check status online from Help Center>View Tickets ";
				String templateid="";
				s.sendSms(msg, loginInfo.getUserMobileNo(), loginInfo, new EmailLetterLog(),templateid);	
			}
			
			JSONObject jsonobj = new JSONObject();
			jsonobj.put("responsej", "1");
			
			String response1 = jsonobj.toString();
			response.setContentType("application/json");
			response.setHeader("Cache-Control", "no-cache");
			response.getWriter().write(response1);
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			connection.close();
		}
		return null;
	}
	
	public String casuality() throws Exception {
		
		if(!verifyLogin(request)){
	    	return "login";
	    }
		
		return "casuality";
	}
	
	
	public String newpatient(){
		
		return "newpatient";
	}
	public String recording(){
		
		return "recording";
	}
	
	public String getEmployeeList() throws Exception{
		
		return null;
	}
	
	public String getPatientList() throws Exception{
		return null;
	}
	public String openpharmacyuserprofile() throws Exception{
		Connection connection = null;
		try {
			connection = Connection_provider.getconnection();
			UserProfileDAO userProfileDAO = new JDBCUserProfileDAO(connection);
			int id = userProfileDAO.getIdFromUserId(loginInfo.getUserId());
			//UserProfile userProfile = userProfileDAO.getPharmacyUserbyId(""+id);
			UserProfile userProfile= userProfileDAO.getUserprofileDetails(id);
			StringBuffer buffer = new  StringBuffer();
			
			buffer.append(""+userProfile.getFullname()+"");
			buffer.append("~");
			buffer.append(""+userProfile.getUserid()+"");
			buffer.append("~");
			buffer.append(""+userProfile.getStatename()+"");
			buffer.append("~");
			buffer.append(""+userProfile.getLocationname()+"");
			buffer.append("~");
			if(userProfile.getPhone()==null){
				buffer.append("<label for='fullname'>Mobile No</label>");
				buffer.append("<input type='text' class='form-control' id='user_mobile' maxlength='10' placeholder='Mobile No'>");
			}else if(userProfile.getPhone().equals("")){
				buffer.append("<label for='fullname'>Mobile No</label>");
				buffer.append("<input type='text' class='form-control' id='user_mobile' maxlength='10' placeholder='Mobile No'>");
			}else if (!userProfile.getPhone().equals("0")){
				buffer.append("<label for='fullname'>Mobile No</label>");
				buffer.append("<input type='text' class='form-control' id='user_mobile' maxlength='10' readonly='readonly' value='"+userProfile.getPhone()+"' placeholder='Mobile No'>");
			}else {
				buffer.append("<label for='fullname'>Mobile No</label>");
				buffer.append("<input type='text' class='form-control' id='user_mobile' maxlength='10' placeholder='Mobile No'>");
			}
			//buffer.append(""+userProfile.getPhone()+"");
			buffer.append("~");
			
			buffer.append(""+userProfile.getId()+"");
			//buffer.append("~");
			//buffer.append(""+userProfile.getPassword()+"");
			
			response.setContentType("text/html");
			response.setHeader("Cache-Control", "no-cache");
			response.getWriter().write(""+buffer.toString()+"");
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			connection.close();
		}
		
		return null;
		}
	
	public String validateoldpharmacypwd() throws Exception{
		Connection connection = null;
		try {
			connection = Connection_provider.getconnection();
			UserProfileDAO userProfileDAO = new JDBCUserProfileDAO(connection);
			int id = userProfileDAO.getIdFromUserId(loginInfo.getUserId());
			UserProfile userProfile = userProfileDAO.getPharmacyUserbyId(""+id);
			String pwd = request.getParameter("oldpwd");
			connection = DriverManager.getConnection(""+Constants.DB_HOST+":3306/admin",""+Constants.DB_USER+"",""+Constants.DB_PWD+"");
			userProfileDAO = new JDBCUserProfileDAO(connection);
			
			int result= userProfileDAO.checkOldPharmacyPwd(userProfile, loginInfo.getClinicUserid(),userProfile.getUserid(),pwd);  
			
			response.setContentType("text/html");
			response.setHeader("Cache-Control", "no-cache");
			response.getWriter().write(""+result+"");
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			connection.close();
		}
		
		return null;
	}
	
	
	public String sendotpforchangepassword() throws Exception{
		Connection connection = null;
		try {
			connection = Connection_provider.getconnection();
			UserProfileDAO userProfileDAO = new JDBCUserProfileDAO(connection);
			String mobile = request.getParameter("mobile");
			String otp = DateTimeUtils.getOTP();
			session.setAttribute("otp", otp);
			//String message = "OTP from Yuvicare for password change. Your OTP is:"+otp+"-"+loginInfo.getClinicName()+"";
			String message ="";
			if(DateTimeUtils.isNull(loginInfo.getClinicUserid()).equals("raibhattar")){
				message="Dr. Ashok Bhattar Clinic : "+otp+" is OTP for password change from BGCLINIC.";
			}else if(DateTimeUtils.isNull(loginInfo.getClinicUserid()).equals("raiprachi")){
				message="Dr. Prachi Bhattar's Skin, Hair Clinic & Cosmetology Centre : "+otp+" is OTP for password change from YUVICARE.";
			}else if(loginInfo.isBalgopal()){
				message="BALGOPAL CHILDREN HOSPITAL : "+otp+" is OTP for password change from YUVICARE.";
			}else{
				message = ""+otp+" is OTP for password change from YUVICARE.";
			}
			
			SmsService s = new SmsService();
			String templateid=userProfileDAO.getSMSTemplateID("Forgot Password");
	        s.sendSms(message, mobile, loginInfo, new EmailLetterLog(),templateid);
			response.setContentType("text/html");
			response.setHeader("Cache-Control", "no-cache");
			response.getWriter().write(""+otp+"");
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			connection.close();
		}
		
		return null;
	}
	
	public String updatepharmacyuserpwd() throws Exception{
		Connection connection = null;
		try {
			connection = Connection_provider.getconnection();
			UserProfileDAO userProfileDAO = new JDBCUserProfileDAO(connection);
			String mobile = request.getParameter("mobile");
			String userid = request.getParameter("user_userid");
			String confirm_pwd = request.getParameter("confirm_pwd");
			int id = userProfileDAO.getIdFromUserId(loginInfo.getUserId());
			
			UserProfile userProfile = new UserProfile();
			userProfile.setPassword(confirm_pwd);
			userProfile.setId(id);
			userProfile.setPhone(mobile);
			userProfile.setMobile(mobile);
			
			int res = userProfileDAO.updateLocalUserAdmin(loginInfo.getUserId(),confirm_pwd);
			
			int selectedid = userProfileDAO.updatePharmacyUserPwdbyId(userProfile);
			
			connection = DriverManager.getConnection(""+Constants.DB_HOST+":3306/admin",""+Constants.DB_USER+"",""+Constants.DB_PWD+"");
			userProfileDAO = new JDBCUserProfileDAO(connection);
			int result= userProfileDAO.updateAdminPharmacyUsrwd(userProfile, loginInfo.getClinicUserid(),loginInfo.getUserId()); 
			
			response.setContentType("text/html");
			response.setHeader("Cache-Control", "no-cache");
			response.getWriter().write("");
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			connection.close();
		}
		
		return null;
	}
	
	public String getAllEmployeeList() throws Exception{
		Connection connection = null;
		try {
			connection = Connection_provider.getconnection();
			UserProfileDAO userProfileDAO = new JDBCUserProfileDAO(connection);
			ClientDAO clientDAO = new JDBCClientDAO(connection);
			//ArrayList<Client> arrayList = clientDAO.getAllPatient();
			ArrayList<UserProfile> arrayList = userProfileDAO.getAllUserProfileList();
			
			StringBuilder builder = new StringBuilder();
			builder.append("<tr>");
			builder.append("<td>");
			builder.append("<label class='checkbox checkbox-custom-alt m-0 mt-5'><input type='checkbox' onclick='selectAllChecked(this)' id='select-all3'><i></i> All</label>");
			builder.append("</td>");
			builder.append("<td></td>");
			builder.append("<td></td>");
			builder.append("<td></td>");
			builder.append("<td></td>");
			builder.append("</tr>");
			for (UserProfile userProfile : arrayList) {
				if(userProfile.getMobile()==null){
					continue;
				}else if(userProfile.getMobile().equals("")){
					continue;
				}else{
					builder.append("<tr>");
					builder.append("<td>");
					builder.append("<input type='hidden' id='clientid"+userProfile.getId()+"' value='"+userProfile.getId()+"'>");
					builder.append("<input type='hidden' id='clientmobno"+userProfile.getId()+"' value='"+userProfile.getMobile()+"'>");
					builder.append("<label class='checkbox checkbox-custom-alt checkbox-custom-sm m-0 mail-select3'><input class='akash' value='"+userProfile.getId()+"' type='checkbox'><i></i>"+userProfile.getFullname()+"</label>");
					builder.append("</td>");
					builder.append("<td></td>");
					builder.append("<td></td>");
					builder.append("<td></td>");
					builder.append("<td></td>");
					builder.append("</tr>");
				}
				
			}
				
			
			
			//ArrayList<Client> arrayList = userProfileDAO.get
			response.setContentType("text/html");
			response.setHeader("Cache-Control", "no-cache");
			response.getWriter().write(""+builder.toString()+"");
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			connection.close();
		}
		
		return null;
	}
	
	public String getAllPatientList() throws Exception{
		Connection connection = null;
		try {
			connection = Connection_provider.getconnection();
			UserProfileDAO userProfileDAO = new JDBCUserProfileDAO(connection);
			ClientDAO clientDAO = new JDBCClientDAO(connection);
			ArrayList<Client> arrayList = clientDAO.getAllPatient();
			StringBuilder builder = new StringBuilder();
			builder.append("<tr>");
			builder.append("<td>");
			builder.append("<label class='checkbox checkbox-custom-alt m-0 mt-5'><input type='checkbox' onclick='selectAllChecked(this)' id='select-all3'><i></i> All</label>");
			builder.append("</td>");
			builder.append("<td></td>");
			builder.append("<td></td>");
			builder.append("<td></td>");
			builder.append("<td></td>");
			builder.append("</tr>");
			for (Client client : arrayList) {
				if(client.getMobNo()==null){
					continue;
				}else if(client.getMobNo().equals("")){
					continue;
				}else{
					String fullname = client.getTitle()+" "+client.getFirstName()+" "+client.getLastName();
					builder.append("<tr>");
					builder.append("<td>");
					builder.append("<input type='hidden' id='clientid"+client.getId()+"' value='"+client.getId()+"'>");
					builder.append("<input type='hidden' id='clientmobno"+client.getId()+"' value='"+client.getMobNo()+"'>");
					builder.append("<label class='checkbox checkbox-custom-alt checkbox-custom-sm m-0 mail-select3'><input class='akash' value='"+client.getId()+"' type='checkbox'><i></i>"+fullname+"</label>");
					builder.append("</td>");
					builder.append("<td></td>");
					builder.append("<td></td>");
					builder.append("<td></td>");
					builder.append("<td></td>");
					builder.append("</tr>");
				}
				
			}
				
			
			
			//ArrayList<Client> arrayList = userProfileDAO.get
			response.setContentType("text/html");
			response.setHeader("Cache-Control", "no-cache");
			response.getWriter().write(""+builder.toString()+"");
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			connection.close();
		}
		
		return null;
	}
	public String sendsmsOrmail()throws Exception{
		Connection connection = null;
		try {
			connection = Connection_provider.getconnection();
			ClientReportDAO clientReportDAO = new JDBCClientReportDAO(connection);
			String subject = request.getParameter("subject");
			String message = request.getParameter("smstxt");
			String allmobileno = request.getParameter("allmobileno");
			String sendtype = request.getParameter("sendtype");
			String allemailid= request.getParameter("allemailid");
			String userid = loginInfo.getUserId();
			DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Calendar cal = Calendar.getInstance();
			String todate = dateFormat.format(cal.getTime());
			String allmno="";
			String alleid =""
					;
			String allmobilenos="";
			String allemailds="";
			if(sendtype.equals("sms")){
				for (String t : allmobileno.split(",")) {
					if (t.equals("0")) {
						continue;
					}
					if(allmno.equals("")){
						allmno = t;
						allmobilenos = t;
					}else{
						allmno = allmno+","+t;
						allmobilenos = allmobilenos+", "+t+" ";
					}
					 
				}
				SmsService s = new SmsService();
				for (String t : allmobileno.split(",")) {
					if (t.equals("0")) {
						continue;
					}
					String templateid="";
					s.sendSms(message, t, loginInfo, new EmailLetterLog(),templateid);
				}
				int result = clientReportDAO.saveMarketingSendHistory(subject,message,allmobilenos,sendtype,"",todate,userid);
			}else{
				for (String t : allemailid.split(",")) {
					if (t.equals("0")) {
						continue;
					}
					if(alleid.equals("")){
						alleid = t;
						allemailds = t;
					}else{
						alleid = alleid+","+t;
						allemailds = allemailds+", "+t+" ";
					}
					 
				}
				String cc="";
				String notes="";
				DateFormat dateFormat2 = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
				Calendar cal1 = Calendar.getInstance();
				String todate1 = dateFormat2.format(cal1.getTime()); 
				
				int selectedid = loginInfo.getId();
				ClinicDAO clinicListDAO = new JDBCClinicDAO(connection);
				//com.apm.Registration.eu.entity.Clinic cliniclist = clinicListDAO.getCliniclistDetails(selectedid);
				
				StringBuffer buffer = new StringBuffer();
				//buffer.append("");
				//buffer.append("<b>Hospital Name: "+cliniclist.getClinicName()+"</b><br>");
				//buffer.append("Date and Time:"+todate1+"<br>");
				//notes = buffer.toString();
				DiaryManagement diaryManagement=clientReportDAO.getsmtpData();
				String[] emailArray = alleid.split(",");
	            for(String string : emailArray) {
	            	System.out.println(string);
					EmbeddedImageEmailUtil.sendMailFromMarketing(string, cc, subject, message,loginInfo,allemailds,diaryManagement);

		            //Transport.send(message);
	            }
				//EmbeddedImageEmailUtil.sendMailFromMarketing(alleid, cc, subject, message,loginInfo,allemailds);
				//int result = clientReportDAO.saveMarketingSendHistory(subject,message,"",sendtype,allemailds,todate,userid);
			}
			
			session.setAttribute("marketingmainaction", "marketing_history");
			
	        response.setContentType("text/html");
			response.setHeader("Cache-Control", "no-cache");
			response.getWriter().write("");
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			connection.close();
		}
		
		return null;
	}
	
	public String sendmail() throws Exception{
		
		Connection connection = null;
		try {
			connection = Connection_provider.getconnection();
			request.setCharacterEncoding("UTF-8");
			response.setCharacterEncoding("UTF-8");
			connection = DriverManager.getConnection(
					"" + Constants.DB_HOST + ":3306/" + loginInfo.getClinicUserid()
							+ "?useUnicode=true&characterEncoding=UTF-8",
					"" + Constants.DB_USER + "", "" + Constants.DB_PWD + "");
			ClientReportDAO clientReportDAO = new JDBCClientReportDAO(connection);
			EmailTemplateDAO emailTemplateDAO = new JDBCEmailTemplateDAO(connection);
			String subject = diaryManagementForm.getSubject();
			String message = diaryManagementForm.getDescription();
			String allmobileno = request.getParameter("allmobileno");
			String sendtype = request.getParameter("sendtype");
			String allemailid= diaryManagementForm.getEmail();
			String templid=diaryManagementForm.getTemplateName();
			String userid = loginInfo.getUserId();
			DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Calendar cal = Calendar.getInstance();
			String todate = dateFormat.format(cal.getTime());
			String allmno="";
			String alleid ="";
			String allmobilenos="";
			String allemailds="";
	       
	        String emailorwhatsup = request.getParameter("allmobileno");
				
				String cc="";
				String notes="";
				cc=diaryManagementForm.getCcEmail();
				DateFormat dateFormat2 = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
				Calendar cal1 = Calendar.getInstance();
				String todate1 = dateFormat2.format(cal1.getTime()); 
				
				int selectedid = loginInfo.getId();
				ClinicDAO clinicListDAO = new JDBCClinicDAO(connection);
				EmrDAO emrDAO = new JDBCEmrDAO(connection);
				
				StringBuffer buffer = new StringBuffer();
				
				DiaryManagement diaryManagement=clientReportDAO.getsmtpData();
	      
	            
	            ArrayList<String> attachfile = new ArrayList<>();
				
				 // copy the uploaded files into pre-configured location
	       if(diaryManagementForm.getFileUpload()!=null) {     
		        for (int i = 0; i < diaryManagementForm.getFileUpload().length; i++) {
		            File uploadedFile = diaryManagementForm.getFileUpload()[i];
		            String fileName = diaryManagementForm.getFileUploadFileName()[i];
		            fileName = loginInfo.getClinicUserid() + "_" + fileName;
		            
		            //fileName = invsttype + "_" + invstid + "_" + fileName;
		            String filePath = request.getRealPath("/liveData/document/");
		            
		            try {
		            	File fileToCreate = new File(filePath, fileName);
		            	FileUtils.copyFile(uploadedFile, fileToCreate);
		            	
		            	diaryManagement.setFileName(fileName);
		            	String attachFileName="";
		            	attachFileName = filePath + "/" + fileName;
		            	attachfile.add(attachFileName);
		            	diaryManagement.setClientid(diaryManagementForm.getClientid());
		            	diaryManagement.setTemplateid(templid);
		            	//int result = emrDAO.savePatientDocument(emr,DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone()),invstid,loginInfo.getUserId()); 
		            	int result = emrDAO.saveAfitechClientDocument(diaryManagement,DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone()),loginInfo.getUserId());
		            	
		            } catch (IOException ex) {
//		                System.out.println("Could not copy file " + fileName);
		                ex.printStackTrace();
		            }
		        }
	       }   
	       
	            ArrayList<EmailTemplate> fileList = emailTemplateDAO.getFileNameBytemplate(templid);
	            
	            for (EmailTemplate emailTemplate : fileList) {
	            	String filePath = request.getRealPath("/liveData/document/");
	            	String fileName = emailTemplate.getUserImageFileName();
	            	String attachFileName="";
	            	attachFileName = filePath+""+ fileName;
	            	diaryManagement.setClientid(diaryManagementForm.getClientid());
	            	diaryManagement.setTemplateid(templid);
	            	attachfile.add(attachFileName);
				}
	            
	            
		        EmbeddedImageEmailUtil.sendMailFromPanel(allemailid, cc, subject, message,loginInfo,allemailds,diaryManagement,attachfile);
	            
	
				//EmbeddedImageEmailUtil.sendMailFromMarketing(alleid, cc, subject, message,loginInfo,allemailds);
				//int result = clientReportDAO.saveMarketingSendHistory(subject,message,"",sendtype,allemailds,todate,userid);
			
			
			session.setAttribute("marketingmainaction", "marketing_history");
			
	        
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			connection.close();
		}
		
		return "manageclientaffitech";
	}
	
public String sendwhatsup() throws Exception{
		
		Connection connection = null;
		try {
			connection = Connection_provider.getconnection();
			ClientReportDAO clientReportDAO = new JDBCClientReportDAO(connection);
			String clientid=diaryManagementForm.getClientid();
			String subject = diaryManagementForm.getSubject();
			String message = diaryManagementForm.getDescription();
			//String allmobileno = request.getParameter("allmobileno");
			String sendtype = request.getParameter("sendtype");
			String allmobileno= diaryManagementForm.getEmail();
			String userid = loginInfo.getUserId();
			DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Calendar cal = Calendar.getInstance();
			String todate = dateFormat.format(cal.getTime());
			String cc="";
			String notes="";
			cc=diaryManagementForm.getCcEmail();
			DateFormat dateFormat2 = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
			Calendar cal1 = Calendar.getInstance();
			String todate1 = dateFormat2.format(cal1.getTime()); 
			String allemailds="";	
			int selectedid = loginInfo.getId();
			ClinicDAO clinicListDAO = new JDBCClinicDAO(connection);
		    WhatsAppService ws=new WhatsAppService();
	        ws.sendMsg(loginInfo, allmobileno, message);
	        DiaryManagement diaryManagement=clientReportDAO.getsmtpData();
	        diaryManagement.setMobNo(allmobileno);
	        diaryManagement.setClientid(clientid);        
	        String mailorwhatsup="whatsup";
	        int result = clientReportDAO.saveMarketingSendHistory1(subject,notes,"",mailorwhatsup,allemailds,todate,loginInfo.getUserId(),diaryManagement);
			session.setAttribute("marketingmainaction", "marketing_history");
			
	        
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			connection.close();
		}
		
		return "manageclientaffitech";
		
	}
	
	
	public String getSmsTemplateData()throws Exception{
		Connection connection = null;
		try {
			connection = Connection_provider.getconnection();
			ClientReportDAO clientReportDAO = new JDBCClientReportDAO(connection);
			String tempid = request.getParameter("tempid");
			MisReport misReport = clientReportDAO.getSmsTemplate(tempid);
			StringBuilder builder = new StringBuilder();
			builder.append(""+DateTimeUtils.isNull(misReport.getName())+"");
			builder.append("~");
			builder.append(""+DateTimeUtils.isNull(misReport.getMessage())+"");
			builder.append("~");
			builder.append(""+DateTimeUtils.isNull(misReport.getSubject())+"");
			response.setContentType("text/html");
			response.setHeader("Cache-Control", "no-cache");
			response.getWriter().write(""+builder.toString()+"");
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			connection.close();
		}
		
		return null;
	}
	
	public String sendsmsOrmailandsave()throws Exception{
		Connection connection = null;
		try {
			connection = Connection_provider.getconnection();
			ClientReportDAO clientReportDAO= new JDBCClientReportDAO(connection);
			String subject = request.getParameter("subject");
			String message = request.getParameter("smstxt");
			String allmobileno = request.getParameter("allmobileno");
			String smtemplatename = request.getParameter("smtemplatename");
			String sendtype = request.getParameter("sendtype");
			String allemailid= request.getParameter("allemailid");
			
			boolean flag = clientReportDAO.istemplateExist(smtemplatename);
			if(!flag){
				int res = clientReportDAO.saveSMSTemplate(smtemplatename,message,subject);
			}
			
			String userid = loginInfo.getUserId();
			DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Calendar cal = Calendar.getInstance();
			String todate = dateFormat.format(cal.getTime());
			String allmno="";
			String alleid ="";
			String allmobilenos="";
			String allemailids ="";
			
			
			if(sendtype.equals("sms")){
				for (String t : allmobileno.split(",")) {
					if (t.equals("0")) {
						continue;
					}
					if(allmno.equals("")){
						allmno = t;
						allmobilenos=t;
					}else{
						allmno = allmno+","+t;
						allmobilenos = allmobilenos+", "+t+"";
					}
					 
				}
				SmsService s = new SmsService();
				for (String t : allmobileno.split(",")) {
					if (t.equals("0")) {
						continue;
					}
					String templateid="";
					s.sendSms(message, t, loginInfo, new EmailLetterLog(),templateid);
				}
				int result = clientReportDAO.saveMarketingSendHistory(subject,message,allmobilenos,sendtype,"",todate,userid);
				//int result = clientReportDAO.saveMarketingSendHistory(subject,message,allmobilenos,sendtype,allemailids,todate,userid);
			}else{
				for (String t : allemailid.split(",")) {
					if (t.equals("0")) {
						continue;
					}
					if(alleid.equals("")){
						alleid = t;
						allemailids = t;
					}else{
						alleid = alleid+","+t;
						allemailids =alleid+", "+t+"";
					}
					 
				}
				String cc="";
				String notes="";
				DateFormat dateFormat2 = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
				Calendar cal1 = Calendar.getInstance();
				String todate1 = dateFormat2.format(cal1.getTime()); 
				
				int selectedid = loginInfo.getId();
				ClinicDAO clinicListDAO = new JDBCClinicDAO(connection);
				//com.apm.Registration.eu.entity.Clinic cliniclist = clinicListDAO.getCliniclistDetails(selectedid);
				
				StringBuffer buffer = new StringBuffer();
				//buffer.append("");
				//buffer.append("<b>Hospital Name: "+cliniclist.getClinicName()+"</b><br>");
				//buffer.append("Date and Time:"+todate1+"<br>");
				//notes = buffer.toString();
				DiaryManagement diaryManagement=new DiaryManagement();
				EmbeddedImageEmailUtil.sendMailFromMarketing(alleid, cc, subject, message,loginInfo,allemailids,diaryManagement);
			}
			
			session.setAttribute("marketingmainaction", "marketing_history");
			/*SmsService s = new SmsService();
			for (String t : allmobileno.split(",")) {
				if (t.equals("0")) {
					continue;
				}
				s.sendSms(message, t, loginInfo, new EmailLetterLog());
			}*/
	        response.setContentType("text/html");
			response.setHeader("Cache-Control", "no-cache");
			response.getWriter().write("");
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			connection.close();
		}
		return null;
	}
	public String showallcontactData()throws Exception{
		Connection connection = null;
		try {
			connection = Connection_provider.getconnection();
			ClientReportDAO clientReportDAO= new JDBCClientReportDAO(connection);
			ClientDAO clientDAO = new JDBCClientDAO(connection);
			String val = request.getParameter("val");
			String dobdate = request.getParameter("dobdate");
			String thirdpartynameid = request.getParameter("thirdpartynameid");
			String refferedby= request.getParameter("refferedby");
			String mobileoremail = request.getParameter("mobileoremail");
			String jobtitle = request.getParameter("jobtitleid");
			String specialityid = request.getParameter("specialityid");
			String invtname = request.getParameter("invtname");
			String greaterthanid = request.getParameter("greaterthanid");
			String lessthanid = request.getParameter("lessthanid");
			String mdicinename = request.getParameter("mdicinename");
			String medafterdate = request.getParameter("medafterdate");
			String sendtype = request.getParameter("sendtype");
			String clientType = request.getParameter("clientType");
			ArrayList<Report> arrayallList = new ArrayList<Report>();
			
			if(val.equals("1")){
				ArrayList<Report> arrayList = clientReportDAO.getPatientTodayBList(dobdate,sendtype);
				ArrayList<Report> arrayList2 = clientReportDAO.getEmployeeTodayBList(dobdate,sendtype);
				arrayallList.addAll(arrayList);
				arrayallList.addAll(arrayList2);
			}else if(val.equals("5")){
				ArrayList<Report> arrayList = clientReportDAO.getPatientThirtPartyList(thirdpartynameid,sendtype);
				arrayallList.addAll(arrayList);
			}else if(val.equals("11")){
				ArrayList<Report> arrayList = clientReportDAO.getPatientDRReferedByList(refferedby,sendtype);
				arrayallList.addAll(arrayList);
			}else if(val.equals("10")){
				ArrayList<Report> arrayList = clientReportDAO.getPatientNotEorMList(mobileoremail,sendtype);
				arrayallList.addAll(arrayList);
			}else if(val.equals("14")){
				ArrayList<Report> arrayList = clientReportDAO.getUserRoleorDepartList(jobtitle,specialityid,sendtype);
				arrayallList.addAll(arrayList);
			}else if(val.equals("4")){
				ArrayList<Report> arrayList = clientReportDAO.getPatientInvtList(invtname,greaterthanid,lessthanid,sendtype);
				arrayallList.addAll(arrayList);
			}else if(val.equals("3")){
				medafterdate = DateTimeUtils.getCommencingDate1(medafterdate);
				ArrayList<Report> arrayList = clientReportDAO.getPatientMedGivenList(mdicinename,medafterdate);
				arrayallList.addAll(arrayList);
			}else if(val.equals("15")){
				ArrayList<Report> arrayList = clientReportDAO.getListOfEmployeeForSMS(sendtype);
				arrayallList.addAll(arrayList);
			}else if(val.equals("16")){
				ArrayList<Report> arrayList = clientReportDAO.getListOfPatientForSMS(sendtype,clientType);
				arrayallList.addAll(arrayList);
			}
			StringBuilder builder = new StringBuilder();
			/*builder.append("<tr>");
			builder.append("<td>");
			builder.append("<label class='checkbox checkbox-custom-alt m-0 mt-5'><input type='checkbox' onclick='selectAllChecked(this)' id='select-all3'><i></i> All</label>");
			builder.append("</td>");
			builder.append("<td></td>");
			builder.append("<td></td>");
			builder.append("<td></td>");
			builder.append("<td></td>");
			builder.append("</tr>");*/
			int i=1;
			for (Report report : arrayallList) {
				builder.append("<tr>");
				if(report.getMobno()==null){
					report.setMobno("");
				}
				if(report.getEmail()==null){
					report.setEmail("");
				}
				
				builder.append("<input hidden id='mobnno"+i+"' value='"+report.getMobno()+"'>");
				builder.append("<input hidden id='emmailid"+i+"' value='"+report.getEmail()+"'>");
				builder.append("<input hidden class='alllclass' value='"+i+"'>");
				/*if(report.getMobno()==null){
					builder.append("<td><label class='checkbox checkbox-custom-alt checkbox-custom-sm m-0 mail-select3'><input class='dclass' value='' type='checkbox'><i></i>"+report.getFullname()+"</label></td>");
				}else if(report.getMobno().equals("")){
					builder.append("<td><label class='checkbox checkbox-custom-alt checkbox-custom-sm m-0 mail-select3'><input class='dclass' value='' type='checkbox'><i></i>"+report.getFullname()+"</label></td>");
				}else{
					builder.append("<td><label class='checkbox checkbox-custom-alt checkbox-custom-sm m-0 mail-select3'><input class='dclass' onclick='selectIndicheck(this.value)' value='"+i+"' type='checkbox'><i></i>"+report.getFullname()+"</label></td>");
				}*/
				builder.append("<td><label class='checkbox checkbox-custom-alt checkbox-custom-sm m-0 mail-select3'><input class='dclass' onclick='selectIndicheck(this.value)' value='"+i+"' type='checkbox'><i></i>"+report.getFullname()+"</label></td>");
				/*if(report.getEmail()==null){
					builder.append("<input class='eclass' value='' type='hidden'>");
				}else if(report.getEmail().equals("")){
					builder.append("<input class='eclass' value='' type='hidden'>");
				}else{
					builder.append("<input class='eclass' value='"+i+"' type='hidden'>");
				}*/
				builder.append("<input class='eclass' value='"+i+"' type='hidden'>");
				
				//builder.append("<td style='width:25%;'>"+report.getFullname()+"</td>");
				builder.append("<td style='width:25%;'>"+report.getMobno()+"</td>");
				builder.append("<td style='width:25%;'>"+report.getEmail()+"</td>");
				builder.append("<td style='width:25%;'>"+DateTimeUtils.isNull(report.getAddress())+"</td>");
				if(DateTimeUtils.isNull(report.getClienttype()).equals("STUDENT")) {
					builder.append("<td style='width:25%;color:green;'>"+DateTimeUtils.isNull(report.getClienttype())+"</td>");
				}else if(DateTimeUtils.isNull(report.getClienttype()).equals("VENDOR")){
					builder.append("<td style='width:25%;color:red;'>"+DateTimeUtils.isNull(report.getClienttype())+"</td>");
				}else {
					builder.append("<td style='width:25%;Black;'>"+DateTimeUtils.isNull(report.getClienttype())+"</td>");
				}
				//builder.append("<td style='width:25%;'>"+DateTimeUtils.isNull(report.getClienttype())+"</td>");
				
				builder.append("</tr>");
				i++;
			}
	        response.setContentType("text/html");
			response.setHeader("Cache-Control", "no-cache");
			response.getWriter().write(""+builder.toString()+"");
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			connection.close();
		}
		return null;
	}
	public String tpadashboard()throws Exception{
		return "tpadashboard";
	}
	public String emergencylabel() throws Exception{
		Connection connection = null;
		try {
			connection = Connection_provider.getconnection();
			DiaryManagementDAO diaryManagementDAO = new JDBCDiaryManagentDAO(connection);
			DiaryManagement diaryManagement = diaryManagementDAO.getEmergencyDetails();
			diaryManagementForm.setEmrgency_data(diaryManagement.getEmrgency_data());
			diaryManagementForm.setEmrgency_title(diaryManagement.getEmrgency_title());
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			connection.close();
		}
		return "emergencylabel";
	}
	public String saveemrgencydata() throws Exception{
		Connection connection = null;
		try {
			connection = Connection_provider.getconnection();
			DiaryManagement diaryManagement = new DiaryManagement();
			DiaryManagementDAO diaryManagementDAO = new JDBCDiaryManagentDAO(connection);
			diaryManagement.setEmrgency_data(diaryManagementForm.getEmrgency_data());
			diaryManagement.setEmrgency_title(diaryManagementForm.getEmrgency_title());
			int res = diaryManagementDAO.updateEmergencyDetails(diaryManagement);
			
			diaryManagementForm.setEmrgency_data(diaryManagement.getEmrgency_data());
			diaryManagementForm.setEmrgency_title(diaryManagement.getEmrgency_title());
			session.setAttribute("emergencydata", diaryManagement.getEmrgency_data());
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			connection.close();
		}
		return "printemergencylabel";
	}
	
	public String savedoctorDiary() throws Exception {
		Connection connection = null;
		if(!verifyLogin(request)){
			return "login";
		}
		DiaryManagement diaryManagement = new DiaryManagement();
		connection = Connection_provider.getconnection();
		DiaryManagementDAO diaryManagementDAO = new JDBCDiaryManagentDAO(connection);
		int selectedid = Integer.parseInt(request.getParameter("selectedid"));
		String actiontype=request.getParameter("actiontype");
		String commencing = request.getParameter("commencing");
		
		try {	
			 DiaryManagement diary=diaryManagementDAO.getdoctorDetails(selectedid);
			 session.setAttribute("actiontype", actiontype);
			 session.setAttribute("docid", selectedid);
			 session.setAttribute("docname", diary.getSelectedDiaryUser());
		 }catch (Exception e) {
			e.printStackTrace();
		}finally{
				connection.close();
			}
		return execute();
	}
	
	public String updatesmtp() throws Exception{
		
		Connection connection = null;
		try {
			connection = Connection_provider.getconnection();
			DiaryManagement diaryManagement = new DiaryManagement();
			DiaryManagementDAO diaryManagementDAO = new JDBCDiaryManagentDAO(connection);
			String smtp=request.getParameter("smtp");
			String password = request.getParameter("password");
			String email = request.getParameter("email");
			

			
			int res = diaryManagementDAO.updateData(smtp,password,email);
			
			response.setContentType("text/html");
			response.setHeader("Cache-Control", "no-cache");
			response.getWriter().write(""+res+"");
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			connection.close();
		}
		
		return null;
		
		
		
	}
	public String target()
	{
		System.out.println("hello");	return "target";
	}
	
	public String test()
	{
		System.out.println("hello");	return "test";
	}
	
}

