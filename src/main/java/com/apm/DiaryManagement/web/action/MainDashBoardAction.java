package com.apm.DiaryManagement.web.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;

import com.apm.Accounts.eu.bi.AdditionalDAO;
import com.apm.Accounts.eu.blogic.jdbc.JDBCAdditionalDAO;
import com.apm.Appointment.eu.bi.AppointmentDAO;
import com.apm.Appointment.eu.blogic.jdbc.JDBCAppointmentDAO;
import com.apm.DiaryManagement.eu.bi.ClientDAO;
import com.apm.DiaryManagement.eu.bi.DiaryManagementDAO;
import com.apm.DiaryManagement.eu.bi.NotAvailableSlotDAO;
import com.apm.DiaryManagement.eu.blogic.jdbc.JDBCClientDAO;
import com.apm.DiaryManagement.eu.blogic.jdbc.JDBCDiaryManagentDAO;
import com.apm.DiaryManagement.eu.blogic.jdbc.JDBCNotAvailableSlotDAO;
import com.apm.DiaryManagement.eu.entity.Breadcrumbs;
import com.apm.DiaryManagement.eu.entity.DiaryManagement;
import com.apm.DiaryManagement.eu.entity.Priscription;
import com.apm.DiaryManagement.web.form.MisDashboardForm;
import com.apm.Inventory.eu.bi.IndentDAO;
import com.apm.Inventory.eu.bi.InventoryProductDAO;
import com.apm.Inventory.eu.blogic.jdbc.JDBCIndentDAO;
import com.apm.Inventory.eu.blogic.jdbc.JDBCInventoryProductDAO;
import com.apm.Ipd.eu.bi.BedDao;
import com.apm.Ipd.eu.blogic.jdbc.JDBCBedDao;
import com.apm.Ipd.eu.entity.Bed;
import com.apm.Mis.eu.bi.MisChartDAO;
import com.apm.Mis.eu.blogic.jdbc.JDBCMisChartDAO;
import com.apm.Pharmacy.eu.bi.PharmacyDAO;
import com.apm.Pharmacy.eu.blogic.jdbc.JDBCPharmacyDAO;
import com.apm.Registration.eu.bi.ClinicDAO;
import com.apm.Registration.eu.bi.UserProfileDAO;
import com.apm.Registration.eu.blogic.jdbc.JDBCClinicDAO;
import com.apm.Registration.eu.blogic.jdbc.JDBCUserProfileDAO;
import com.apm.Registration.eu.entity.Clinic;
import com.apm.Registration.eu.entity.UserProfile;
import com.apm.Report.eu.entity.MisReport;
import com.apm.SSH.Basic;
import com.apm.common.eu.blogic.jdbc.Connection_provider;
import com.apm.common.utils.DateTimeUtils;
import com.apm.common.utils.Pagination;
import com.apm.common.web.action.BaseAction;
import com.apm.common.web.common.helper.LoginHelper;
import com.apm.common.web.common.helper.LoginInfo;
import com.apm.main.common.constants.Constants;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ModelDriven;

public class MainDashBoardAction extends BaseAction implements ModelDriven<MisDashboardForm>{
	
	HttpServletRequest request = (HttpServletRequest) ActionContext.getContext().get(ServletActionContext.HTTP_REQUEST);
	HttpServletResponse response = (HttpServletResponse)ActionContext.getContext().get(ServletActionContext.HTTP_RESPONSE);
	HttpSession session = request.getSession(true);
	LoginInfo loginInfo = LoginHelper.getLoginInfo(request);
	private Pagination pg= null;
	MisDashboardForm misDashboardForm = new MisDashboardForm();
	
	public String execute() throws Exception {
		if(!verifyLogin(request)){
			return "login";
		}
		
		Connection connection=null;
		try{
			connection=Connection_provider.getconnection();
			ClinicDAO clinicListDAO = new JDBCClinicDAO(connection);
			UserProfileDAO userProfileDAO = new JDBCUserProfileDAO(connection);
			MisChartDAO misChartDAO = new JDBCMisChartDAO(connection);
			String userid = loginInfo.getUserId();
			boolean flag = userProfileDAO.checkUserActive(userid);
			String jobtitle = loginInfo.getJobTitle();
			//  17 nov 2017 for Pharmacy user active work from pharmacy setting and its control from there. not from his
			if(!jobtitle.equals("Pharmacist")){
				if (!flag) {
					if(loginInfo.getUserType()==2){
						
					}
					else{
						addActionError("Inactive HIS user");
						return "loginerror";
					}
				}
			}
			ArrayList<Breadcrumbs> indentflowlist = new ArrayList<Breadcrumbs>();
			/*ArrayList<Breadcrumbs> indentflowlisttemp = new ArrayList<Breadcrumbs>();
			if (session.getAttribute("indentflowlist") != null) {
				indentflowlisttemp = (ArrayList<Breadcrumbs>) session.getAttribute("indentflowlist");
			}*/
			Breadcrumbs breadcrumbs = new Breadcrumbs();
			breadcrumbs.setName("HOME");
			breadcrumbs.setOn(true);
			breadcrumbs.setSqno(0);
			breadcrumbs.setUrllink("MainDashBoard");
			breadcrumbs.setIscurrent(true);
			breadcrumbs.setShowingname("HOME");
			indentflowlist.add(breadcrumbs);
			session.setAttribute("indentflowlist",indentflowlist);
			
			DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
			Calendar cal = Calendar.getInstance();
			//cal.add(Calendar.DATE, -7); 
			String currdate = dateFormat.format(cal.getTime());
			
			String wcdate[] = currdate.split("/");
			
			int wyear = Integer.parseInt(wcdate[2]);
			int month = Integer.parseInt(wcdate[1]);
			int day = Integer.parseInt(wcdate[0]);
			
			
			String newdate= wcdate[0]+"/"+wcdate[1];
			String cweekName = DateTimeUtils.getWeekName(wyear,month,day);
			
			misDashboardForm.setDate(Integer.toString(day));
			misDashboardForm.setMonth(cweekName);
			DiaryManagementDAO diaryManagementDAO=new JDBCDiaryManagentDAO(connection);
			//UserProfileDAO userProfileDAO= new JDBCUserProfileDAO(connection);
			ArrayList<DiaryManagement> eventList=diaryManagementDAO.getAllEventListExist();
			
			misDashboardForm.setEventList(eventList);
			
			int selectedid = loginInfo.getId();
			String city = diaryManagementDAO.getcityfrmaindashboard(selectedid);
			misDashboardForm.setCity(city);
			
			
			// /ruchi coding for add logo link
			Clinic cliniclist=new Clinic();
			if(loginInfo.getClinicid1().equals("manasclinic")) {
				NotAvailableSlotDAO notAvailableSlotDAO=new JDBCNotAvailableSlotDAO(connection);
				String clinicstaff_id=notAvailableSlotDAO.getclinicstaff(loginInfo.getId());
				cliniclist = clinicListDAO.getCliniclistDetails(Integer.parseInt(clinicstaff_id));
			}else {
				cliniclist = clinicListDAO.getCliniclistDetails(loginInfo.getClinicid());
			}
			//Clinic cliniclist = clinicListDAO.getCliniclistDetails(loginInfo.getClinicid());
			misDashboardForm.setUserImageFileName(cliniclist.getUserImageFileName());
			
			{
				try {
					//Setting im gae 1st time
					String dest_path = request.getRealPath("/liveData/clinicLogo/");
					DateTimeUtils.saveFileToLocalDirectory(dest_path, misDashboardForm.getUserImageFileName());
				/*	File destFolder = new File(dest_path);
					if (!destFolder.exists()) {
						destFolder.mkdirs();
					}
					//Source Dir
					String source_path=dest_path;
					source_path=source_path.replace("HISTEST", "HISDATA");
					source_path=source_path.replace("HISLIVE", "HISDATA");
					File sourceFolder = new File(source_path);
					if (!sourceFolder.exists()) {
						sourceFolder.mkdirs();
					}
					 File file=new File(source_path+"/"+misDashboardForm.getUserImageFileName());
					 File destfile = new File(dest_path+"/"+misDashboardForm.getUserImageFileName());*/
					/* FileUtils.copyFile(file, destfile);*/
					
				} catch (Exception e) {
					e.printStackTrace();
				}
				
				
				
			}
			
			if(loginInfo.getClinicid1().equals("manasclinic")) {
				misDashboardForm.setClinicname(cliniclist.getManas_clinic_name());
				
			}else {
				misDashboardForm.setClinicname(cliniclist.getClinicName());
			}
			//misDashboardForm.setClinicname(cliniclist.getClinicName());
			ArrayList<UserProfile> bdaylist= userProfileDAO.getallbirthdays(cliniclist.getClinicName(), newdate);
			
			int id = userProfileDAO.getIdFromUserId(loginInfo.getUserId());
			//UserProfile userProfile = userProfileDAO.getPharmacyUserbyId(""+id);
			UserProfile userProfile= userProfileDAO.getUserprofileDetails(id);
			misDashboardForm.setFullname(userProfile.getFullname());
			misDashboardForm.setUserid(loginInfo.getUserId());
			misDashboardForm.setMobile(userProfile.getMobile());
			misDashboardForm.setJobtitle(userProfile.getJobtitle());
			misDashboardForm.setState(userProfile.getState());
			misDashboardForm.setBdaylist(bdaylist);
			
			InventoryProductDAO inventoryProductDAO = new JDBCInventoryProductDAO(connection);
			PharmacyDAO pharmacyDAO = new JDBCPharmacyDAO(connection);
			IndentDAO indentDAO = new JDBCIndentDAO(connection);
			boolean ispharmacist = inventoryProductDAO.isPharamcistOrNot(userid);
			String location1="";
			Priscription priscription = pharmacyDAO.getPharacyUsrLInfo(loginInfo.getUserId());
			if(ispharmacist){
				location1 = priscription.getLocation();
				if(priscription.getIslogin().equals("0")){
					addActionError("Inactive Pharmacy user");
					return "loginerror";
				}
			}else{
				if(loginInfo.getJobTitle().equals("Admin")){
					location1 = "0";
				}else{
					location1 = inventoryProductDAO.getHISUserLocation(userid);
				}
			}
			if(location1==null){
				location1="0";
			}
			
			int refundstatus=0;
			int discountstatus=0;
			
			UserProfile userentity = userProfileDAO.getUserNotificationCount(loginInfo.getUserId());
			
			if(userentity.getNoti_dis_apprd_count()>0 || userentity.getNoti_dis_reqtd_count()>0){
				discountstatus=1;
			}
			misDashboardForm.setNoti_dis_apprd_count(userentity.getNoti_dis_apprd_count());
			misDashboardForm.setNoti_dis_reqtd_count(userentity.getNoti_dis_reqtd_count());
			misDashboardForm.setDiscountstatus(discountstatus);
			
			if(userentity.getNoti_ref_appr_count()>0 || userentity.getNoti_ref_reqtd_count()>0){
				refundstatus = 1;
			}
			misDashboardForm.setNoti_ref_appr_count(userentity.getNoti_ref_appr_count());
			misDashboardForm.setNoti_ref_reqtd_count(userentity.getNoti_ref_reqtd_count());
			misDashboardForm.setRefundstatus(refundstatus);
			
			/*// 
			AdditionalDAO additionalDAO = new JDBCAdditionalDAO(connection);
			boolean accessofapprove= false;
			if(loginInfo.isRefund_dashboard() || loginInfo.getUserType()==2 || loginInfo.isRef_dis_pay()){
				accessofapprove =true;
			}
			String currentdate = DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone()).split(" ")[0];
			int refundstatus = additionalDAO.checkNewRefund(accessofapprove,loginInfo.getId(),currentdate,loginInfo.getUserId());
			int discountstatus = additionalDAO.checkNewDiscount(accessofapprove,loginInfo.getId(),currentdate,loginInfo.getUserId());
			misDashboardForm.setRefundstatus(refundstatus);
			misDashboardForm.setDiscountstatus(discountstatus);
			
			DateFormat dateFormat1 = new SimpleDateFormat("yyyy-MM-dd");
			Calendar cal1 = Calendar.getInstance();
			cal1.add(Calendar.DATE, -7);
			String fromdate1 = dateFormat1.format(cal1.getTime());  
			   
			DateFormat dateFormat2 = new SimpleDateFormat("yyyy-MM-dd");
		    Calendar cal2 = Calendar.getInstance(); 
		    String todate = dateFormat2.format(cal2.getTime());
		    
			boolean flags = indentDAO.checkIndentDelivered(location1,todate,todate);
			if(!flags){
				boolean flag1 = false;
				flag1 = indentDAO.checkLocationInWarehouseid(location1);
				if(flag1){
					boolean flag2 = indentDAO.checkIndentApproved(location1,todate,todate);
					if(!flag2){
						String indentapprove = "0";
					 	if(priscription.getIndent_approve()!=null){
			 				if(priscription.getIndent_approve().equals("1")){
			 					indentapprove="1";
			 				}
			 			}
					 	if(loginInfo.getUserType()==2 || loginInfo.isIndent_approve() || indentapprove.equals("1")){
					 		boolean flag3 = indentDAO.checkIndentRequested(location1,fromdate1,todate);
					 		if(!flag3){
					 			misDashboardForm.setIndentstaus(0);
					 		}else{
					 			misDashboardForm.setIndentstaus(1);
					 		}
					 	}
					}else{
						misDashboardForm.setIndentstaus(1);
					}
				}else{
					String indentapprove = "0";
				 	if(priscription.getIndent_approve()!=null){
		 				if(priscription.getIndent_approve().equals("1")){
		 					indentapprove="1";
		 				}
		 			}
				 	if(loginInfo.getUserType()==2 || loginInfo.isIndent_approve() || indentapprove.equals("1")){
				 		boolean flag3 = indentDAO.checkIndentRequested(location1,fromdate1,todate);
				 		if(!flag3){
				 			misDashboardForm.setIndentstaus(0);
				 		}else{
				 			misDashboardForm.setIndentstaus(1);
				 		}
				 	}
				}
			}else{
				misDashboardForm.setIndentstaus(1);
			}
			*/
			
			
			if(loginInfo.isMaindash_graph()){
				DateFormat dateFormatnew = new SimpleDateFormat("dd-MM-yyyy");
				Calendar calender = Calendar.getInstance();
				calender.add(Calendar.DATE, -1);
				String newdate1 = dateFormatnew.format(calender.getTime());
				newdate1 = DateTimeUtils.getCommencingDate1(newdate1);
				MisReport misReport = misChartDAO.getMainDashboardData(newdate1);
				misDashboardForm.setIpd_admission(DateTimeUtils.numberCheck(misReport.getIpd_admission()));
				misDashboardForm.setIpd_discharge(DateTimeUtils.numberCheck(misReport.getIpd_discharge()));
				misDashboardForm.setIpd_inhouse(DateTimeUtils.numberCheck(misReport.getIpd_inhouse()));
				misDashboardForm.setIpd_vacantbed(DateTimeUtils.numberCheck(misReport.getIpd_vacantbed()));
				misDashboardForm.setOpd_booked(DateTimeUtils.numberCheck(misReport.getOpd_booked()));
				misDashboardForm.setOpd_completed(DateTimeUtils.numberCheck(misReport.getOpd_completed()));
				misDashboardForm.setOpd_dna(DateTimeUtils.numberCheck(misReport.getOpd_dna()));
				misDashboardForm.setTotal_male(DateTimeUtils.numberCheck(misReport.getTotal_male()));
				misDashboardForm.setTotal_female(DateTimeUtils.numberCheck(misReport.getTotal_female()));
				misDashboardForm.setUpto20(DateTimeUtils.numberCheck(misReport.getUpto20()));
				misDashboardForm.setUpto40(DateTimeUtils.numberCheck(misReport.getUpto40()));
				misDashboardForm.setUpto60(DateTimeUtils.numberCheck(misReport.getUpto60()));
				misDashboardForm.setAbove60(DateTimeUtils.numberCheck(misReport.getAbove60()));
			}
			
			
			//lokesh 26-10-18
			ArrayList<Bed> wardlist=new ArrayList<Bed>();
			connection = Connection_provider.getconnection();
			//ClientDAO clientDAO = new JDBCClientDAO(connection);
			BedDao bedDao = new  JDBCBedDao(connection);
			wardlist=bedDao.getAllWardList( pg );
			misDashboardForm.setWardlist(wardlist);
			
			Connection connection2= null;
			Class.forName("com.mysql.jdbc.Driver");
		//	connection2=DriverManager.getConnection("jdbc:mysql://139.162.51.34:3306/demo",""+Constants.DB_USER+"",""+Constants.DB_PWD+"");
			ClinicDAO clinicDAO = new JDBCClinicDAO(connection);
			/*int remainingdays=clinicDAO.getClinicDeactiveDays(connection2, loginInfo);
			if(remainingdays<=0){
//				System.out.println("Zero");
			}
			
			misDashboardForm.setExpirytime(remainingdays);*/
			
			
			
			
			
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		finally{
			if(connection==null){
				return "error";
			}
			connection.close();
		}
		return super.execute();
	}

	public String setting() throws Exception{
		
		if(!verifyLogin(request)){
			
			return "login";
		}
		
		Connection connection=null;
		try {
			connection=Connection_provider.getconnection();
			UserProfileDAO userProfileDAO= new JDBCUserProfileDAO(connection); 
			int id = userProfileDAO.getIdFromUserId(loginInfo.getUserId());
			//UserProfile userProfile = userProfileDAO.getPharmacyUserbyId(""+id);
			UserProfile userProfile= userProfileDAO.getUserprofileDetails(id);
			misDashboardForm.setFullname(userProfile.getFullname());
			misDashboardForm.setUserid(loginInfo.getUserId());
			misDashboardForm.setMobile(userProfile.getMobile());
			misDashboardForm.setJobtitle(userProfile.getJobtitle());
			misDashboardForm.setState(userProfile.getCounty());
			misDashboardForm.setLocation(userProfile.getSpecialization());
			
		} catch (Exception e) {

			e.printStackTrace();
		}
		finally {
			if(connection==null){
				return "error";
			}
			connection.close();
		}
		
		
		return "setting";
	}
	
	
	public MisDashboardForm getModel() {
		// TODO Auto-generated method stub
		return misDashboardForm;
	}
	
	public String nabhdashboard(){
		return "nabhdashboard";
	}
	
	public String discountapprove() throws Exception{
		Connection connection =null;
		try {
			String userid = request.getParameter("userid");
			String clinicuserid = request.getParameter("clinicuserid");
			String discid = request.getParameter("discid");
			String invoiceid = request.getParameter("invoiceid");
			connection = DriverManager.getConnection(""+Constants.DB_HOST+":3306/"+clinicuserid+"",""+Constants.DB_USER+"",""+Constants.DB_PWD+"");
			
		   AppointmentDAO appointmentDAO = new JDBCAppointmentDAO(connection);
		   boolean flag = appointmentDAO.checkDiscRequestDeleted(discid);
		   int res =0;
		   if(!flag){
			   flag = appointmentDAO.checkInvoiceCreatedAgainstDiscReq(invoiceid);
			   if(flag){
				   res =2;
				   //approved
			   }
		   }else{
			   res=1;
			 //deleted
		   }
		   if(res==0){
			   DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			   Calendar cal = Calendar.getInstance();
			   String date = dateFormat.format(cal.getTime());  
			   int res2 = appointmentDAO.updateApproveInvoiceDiscout(invoiceid,userid,date,"Approved");
			   int res1 = appointmentDAO.updateInvoiceDiscout(discid,userid,date,"Approved");
			   res=3;
		   }
		   misDashboardForm.setState(""+res);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "discountapprove";
	}
public String takehospitalbk() {
	Connection connection=null;
	try{
		connection = Connection_provider.getconnection();
		Basic basic=new Basic();
		int r=basic.sshExecute();
		response.setContentType("text/html");
		response.setHeader("Cache-Control", "no-cache");
		response.getWriter().write("0");
	}catch (Exception e) {
		// TODO: handle exception
	}
	
	
	
	return null;
	}

public String downloaddatabase(){
	try {
		String filePath = request.getRealPath("");
		
		filePath=filePath.replace("YUVICARE", "YUVICAREDATA/DBbackup");
		filePath=filePath.replace("YUVICARE1", "YUVICAREDATA/DBbackup");
		filePath=filePath.replace("YUVICARETEST", "YUVICAREDATA/DBbackup");
		File destFolder = new File(filePath);
		if (!destFolder.exists()) {
			destFolder.mkdirs();
		}
		
		FileInputStream in = null;
		ServletOutputStream out = null;
		String fileName = loginInfo.getClinicUserid()+".sql";
		filePath=filePath+"/"+fileName;
		//String workingDirectory = System.getProperty("user.dir");

		File file = new File(filePath);

			if (file.exists()) {
				response.setHeader("Content-Disposition", "attachment;filename=" + fileName + "");
				try {
					in = new FileInputStream(file);
					out = response.getOutputStream();
					byte[] outputByte = new byte[4096];
					while (in.read(outputByte, 0, 4096) != -1) {
						out.write(outputByte, 0, 4096);
					}
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				}
			}

			in.close();
			out.flush();
			out.close();
//		response.setContentType("text/html");
//		response.setHeader("Cache-Control", "no-cache");
//		response.getWriter().write(filePath+"/"+fileName);
	} catch (Exception e) {
		// TODO: handle exception
	}
	return null;
}
public String downloadjsp(){
	try {
	String path=""+loginInfo.getSshhostname()+":8080/YUVICAREDATA/DBbackup/"+loginInfo.getClinicUserid()+".sql";
	response.setContentType("text/html");
	response.setHeader("Cache-Control", "no-cache");
	response.getWriter().write(path);
	} catch (IOException e) {
		e.printStackTrace();
	}
	return null;
}

public String missinguseridsave() throws Exception{
	Connection connection=null;
	try {
		connection = Connection_provider.getconnection();
		UserProfileDAO userProfileDAO = new JDBCUserProfileDAO(connection);
		int save = userProfileDAO.saveUseridInAccess();
	} catch (Exception e) {
		e.printStackTrace();
	}
	return "maindashboard";
}
}