package com.apm.Registration.web.action;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.xml.bind.DatatypeConverter;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;
import org.json.JSONObject;

import com.apm.Accounts.eu.bi.AccountsDAO;
import com.apm.Accounts.eu.blogic.jdbc.JDBCAccountsDAO;
import com.apm.DiaryManagement.eu.bi.ClientDAO;
import com.apm.DiaryManagement.eu.bi.DiaryManagementDAO;
import com.apm.DiaryManagement.eu.blogic.jdbc.JDBCClientDAO;
import com.apm.DiaryManagement.eu.blogic.jdbc.JDBCDiaryManagentDAO;
import com.apm.DiaryManagement.eu.entity.Client;
import com.apm.DiaryManagement.eu.entity.DiaryManagement;
import com.apm.DiaryManagement.web.action.SmsService;
import com.apm.Inventory.eu.bi.IndentDAO;
import com.apm.Inventory.eu.bi.InventoryProductDAO;
import com.apm.Inventory.eu.blogic.jdbc.JDBCIndentDAO;
import com.apm.Inventory.eu.blogic.jdbc.JDBCInventoryProductDAO;
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
import com.apm.Registration.eu.entity.Clinic;
import com.apm.Registration.eu.entity.Currency;
import com.apm.Registration.eu.entity.UserProfile;
import com.apm.Registration.web.form.UserProfileForm;
import com.apm.Report.eu.blogic.jdbc.JDBCClientReportDAO;
import com.apm.Report.eu.entity.MisReport;
import com.apm.ThirdParties.eu.bi.GPDAO;
import com.apm.ThirdParties.eu.blogic.jdbc.JDBCGPDAO;
import com.apm.ThirdParties.eu.entity.GP;
import com.apm.common.eu.blogic.jdbc.Connection_provider;
import com.apm.common.utils.DateTimeUtils;
import com.apm.common.utils.Encryption;
import com.apm.common.utils.Pagination;
import com.apm.common.web.action.BaseAction;
import com.apm.common.web.common.helper.Access;
import com.apm.common.web.common.helper.LoginHelper;
import com.apm.common.web.common.helper.LoginInfo;
import com.apm.main.common.constants.Constants;
import com.onbarcode.barcode.QRCode;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.Preparable;
import com.razorpay.Order;
import com.razorpay.RazorpayClient;

public class UserProfileAction extends BaseAction implements Preparable, ModelDriven<UserProfileForm>  {
	
	HttpServletRequest request = (HttpServletRequest) ActionContext.getContext().get(ServletActionContext.HTTP_REQUEST);
	HttpServletResponse response = (HttpServletResponse)ActionContext.getContext().get(ServletActionContext.HTTP_RESPONSE);
	HttpSession session = request.getSession(true);
	UserProfileForm userProfileForm = new UserProfileForm();
	private Pagination pagination = new Pagination(10, 1);
		public Pagination getPagination() {
			return pagination;
		}


		public void setPagination(Pagination pagination) {
			this.pagination = pagination;
		}
	
	LoginInfo loginInfo = LoginHelper.getLoginInfo(request);
	
	
	public String execute() throws Exception {
		
		if(!verifyLogin(request)){
			return "login";
		}
		
		//show practice manager
		Connection connection = null;
		try{
			connection = Connection_provider.getconnection();
			UserProfileDAO userProfileDAO = new JDBCUserProfileDAO(connection);
			LoginInfo loginInfo = LoginHelper.getLoginInfo(request);
			ArrayList<String> jobTitleList = userProfileDAO.getJobTitleList();
			userProfileForm.setJobTitleList(jobTitleList);
			
			String jobtitle=DateTimeUtils.isNull(userProfileForm.getJobtitless());
			int totalCount = userProfileDAO.getUserprofileCount(userProfileForm.getSearchText(),loginInfo.getId(),loginInfo.getUserType(),jobtitle);
			pagination.setPreperties(totalCount);
			ArrayList<UserProfile>userProfile = userProfileDAO.getUserProfileList(pagination,userProfileForm.getSearchText(),loginInfo.getId(),loginInfo.getUserType(),jobtitle);
			userProfileForm.setUserProfileList(userProfile);
			pagination.setPage_records(userProfile.size());
			userProfileForm.setTotalRecords(totalCount);
			userProfileForm.setPagerecords(Integer.toString(pagination.getPage_records()));
			session.setAttribute("pagination", pagination);
			String searchName = userProfileForm.getSearchText();
			session.setAttribute("searchName", searchName);
			
			
			
		}catch (Exception e) {
			e.printStackTrace();
		}finally{
			
			connection.close();
		}
	 
		return SUCCESS;
	}
	
	public String back1() throws Exception{
		if(!verifyLogin(request)){
			return "login";
		}
		setFormData();
	return "updatesave";
		
	}
	
	public String jobtitle() throws Exception{
		
		if(!verifyLogin(request)){
			return "login";
		}
		Connection connection = null;
		try{
			connection = Connection_provider.getconnection();
			UserProfileDAO userProfileDAO = new JDBCUserProfileDAO(connection);
			String selectedid = request.getParameter("selectedid");
			
			ArrayList<Master>jobtitleList = userProfileDAO.getUserJobTitleList(selectedid);
			StringBuffer str = new StringBuffer();
			str.append("<select onchange='checkDoctorSelect()' name='jobtitles' id='jobtitle' class='form-control showToolTip' data-toggle='tooltip'>");
			str.append("<option value='0'>Select  JobTitle</option>");
			
			for(Master master : jobtitleList){
				str.append("<option value='"+master.getName()+"'>"+master.getName()+"</option>");
			}
			str.append("</select>");
			
			
			response.setContentType("text/html");
			response.setHeader("Cache-Control", "no-cache");
			response.getWriter().write(""+str.toString()+""); 
			
		}catch (Exception e) {
			// TODO: handle exception
		}
		
		return null;
	}
	
	public String input() throws SQLException{
		if(!verifyLogin(request)){
			return "login";
		}
		Connection connection = null;
		Client client = new Client();
		
		ArrayList<String> initialList = new ArrayList<String>();
		ArrayList<String> jobTitleList = new ArrayList<String>();
		ArrayList<Master> clinicUseridlist = new ArrayList<Master>();
		ArrayList<Master> clinicStafflist = new ArrayList<Master>();
		try{
			
			connection = Connection_provider.getconnection();
			ClientDAO clientDAO = new JDBCClientDAO(connection);
			
			
			initialList = clientDAO.getInitialList();
			userProfileForm.setInitialList(initialList);
			
			UserProfileDAO userProfileDAO = new JDBCUserProfileDAO(connection);
			jobTitleList = userProfileDAO.getJobTitleList();
			jobTitleList.add("Other");
			userProfileForm.setJobTitleList(jobTitleList);
			
			clinicUseridlist = userProfileDAO.getClinicUseridlist();
			userProfileForm.setClinicuseridlist(clinicUseridlist);
			
			clinicStafflist = userProfileDAO.getClinicStafflist();
			userProfileForm.setClinicstafflist(clinicStafflist);
			
			ArrayList<Master>jobGroupList = userProfileDAO.getJobGroupList("1");
			userProfileForm.setJobGroupList(jobGroupList);
			
			ArrayList<Master>doctorList = userProfileDAO.getDoctorList();
			userProfileForm.setDoctorList(doctorList);
			
			
		}
		catch (Exception e) {
			// TODO: handle exception
		}
finally{
			
			connection.close();
		}
		return "input";
	}
	
	public String save() throws SQLException{
		if(!verifyLogin(request)){
			return "login";
		}
		//save practice manager
		Connection connection = null;
		try{
			connection = Connection_provider.getconnection();
			UserProfileDAO userProfileDAO = new JDBCUserProfileDAO(connection);
			
			//start coding
			
			//connection = Connection_provider.getconnection();
			//practiceManagerDAO practitionerDAO = new JDBCParcticeManagerDAO(connection);
			 
			UserProfile userprofile = new UserProfile();
			
			userprofile.setFirstname(userProfileForm.getFirstname());
			userprofile.setLastname(userProfileForm.getLastname());
			userprofile.setInitial(userProfileForm.getInitial());
			
			String other = "Other";
			if(userProfileForm.getJobtitle().equalsIgnoreCase(other))
			{
				userProfileForm.setJobtitle(userProfileForm.getOtherJobTitle());
			}
			
			userprofile.setJobtitle(userProfileForm.getJobtitle());
			/*userprofile.setDiscription(userProfileForm.getDiscription());*/
			userprofile.setDiciplineName(userProfileForm.getDiciplineName());
			userprofile.setRegisterno(userProfileForm.getRegisterno());
			userprofile.setUsergroup(userProfileForm.getUsergroup());
			userprofile.setUsercheck(userProfileForm.isUsercheck());
			userprofile.setAppointmentdiary(userProfileForm.isAppointmentdiary());
			
			String colorWhite = "FFFFFF"; String color = "";
			if(userProfileForm.getDiarycolor().equals(colorWhite))
			{
				userProfileForm.setDiarycolor(color);
			}
			userprofile.setDiarycolor(userProfileForm.getDiarycolor());
			userprofile.setDiarycolumnposition(userProfileForm.getDiarycolumnposition());
			userprofile.setCompressionrate(userProfileForm.getCompressionrate());
			userprofile.setOnlinename(userProfileForm.getOnlinename());
			userprofile.setIsavailableonline(userProfileForm.isIsavailableonline());
			userprofile.setLoginsystem(userProfileForm.isLoginsystem());
			userprofile.setUserid(userProfileForm.getUserId());
			userprofile.setPassword(userProfileForm.getPassword());
			userprofile.setLastchanged(userProfileForm.getLastchanged());
			userprofile.setChangefre(userProfileForm.getChangefre());
			userprofile.setAppointmentbookingcontem(userProfileForm.getAppointmentbookingcontem());
			userprofile.setAppointmentbookingdnatem(userProfileForm.getAppointmentbookingdnatem());
			userprofile.setEmail(userProfileForm.getEmail());
			userprofile.setMobile(userProfileForm.getMobile());
			userprofile.setApmremote(userProfileForm.isApmremote());
			userprofile.setOnlinebooking(userProfileForm.isOnlinebooking());
			userprofile.setUserType(4);
			userprofile.setDnaCharge(userProfileForm.getDnaCharge());
			userprofile.setCompAppCharge(userProfileForm.getCompAppCharge());
			userprofile.setIpdCharge(userProfileForm.getIpdCharge());
			userprofile.setSurgeonCharge(userProfileForm.getSurgeonCharge());
			userprofile.setVisitingdoctor(userProfileForm.isVisitingdoctor());
			
			
	
			int reult = userProfileDAO.saveUserprofile(userprofile,loginInfo.getId());
			userProfileForm.setMessage("Registred Sucessfully!!");
			addActionMessage("Registred Sucessfully!!");
			
			setFormData();
			
			
			
		}catch (Exception e) {
			e.printStackTrace();
		}finally{
			
			connection.close();
		}
		return SUCCESS;
	}
	
	public String move() throws Exception{
		if(!verifyLogin(request)){
			return "login";
		}
		setFormDataSort();
		return SUCCESS;
	}
	
	public void setFormDataSort() throws Exception{
		Connection connection = null;
		int totalCount = 0;
		try{
			String searchName = userProfileForm.getSearchText();	
			if(searchName == null){
				searchName = "";
			}
			if(searchName.equals(""))
			{
				searchName = (String) session.getAttribute("searchName");
				
				if(searchName == null){
					searchName = "";
				}
			}
		connection = Connection_provider.getconnection();
		UserProfileDAO userProfileDAO = new JDBCUserProfileDAO(connection);
		ArrayList<UserProfile> userProfile = new ArrayList<UserProfile>();

		//pagination = (Pagination) session.getAttribute("pagination");		
		
		//if(!searchName.equals("")){
			 totalCount = userProfileDAO.getUserprofileCount(searchName,loginInfo.getId(),loginInfo.getUserType(),"");
			 pagination.setPreperties(totalCount);
			 userProfile = userProfileDAO.getUserProfileList(pagination,searchName,loginInfo.getId(),loginInfo.getUserType(),"");
		/*}else{
			 totalCount = userProfileDAO.getUserprofileCount(loginInfo.getId());
			 pagination.setPreperties(totalCount);
			 userProfile = userProfileDAO.getUserProfileList(pagination,loginInfo.getId());

		}*/		
		
		userProfileForm.setSearchText(searchName);
		
		pagination.setPage_records(userProfile.size());
		pagination.setPage_records(userProfile.size());
		userProfileForm.setTotalRecords(totalCount);
		userProfileForm.setPagerecords(Integer.toString(pagination.getPage_records()));
		userProfileForm.setUserProfileList(userProfile);
		}
		catch(Exception e){
			e.printStackTrace();
			
		}finally{
			
			connection.close();
		}
	}


	public void setFormData() throws Exception{
		Connection connection = null;
		try{
			connection = Connection_provider.getconnection();
			UserProfileDAO userProfileDAO = new JDBCUserProfileDAO(connection);
			
		pagination = (Pagination) session.getAttribute("pagination");
		int totalCount = userProfileDAO.getUserprofileCount(userProfileForm.getSearchText(),loginInfo.getId(),loginInfo.getUserType(),"");
		pagination.setPreperties(totalCount);
		ArrayList<UserProfile>userProfile = userProfileDAO.getUserProfileList(pagination,userProfileForm.getSearchText(),loginInfo.getId(),loginInfo.getUserType(),"");
		userProfileForm.setUserProfileList(userProfile);
		pagination.setPage_records(userProfile.size());
		userProfileForm.setTotalRecords(totalCount);
		userProfileForm.setPagerecords(Integer.toString(pagination.getPage_records()));
		}
		catch(Exception e){
			
		}finally{
			
			connection.close();
		}
	}
	
	public String updatesave () throws SQLException
	{
		if(!verifyLogin(request)){
			return "login";
		}
		
		//int selectedid = Integer.parseInt(request.getParameter("id"));
		int selectedid=userProfileForm.getId();
		Connection connection = null;
		
		try{
			HttpServletRequest request = (HttpServletRequest)ActionContext.getContext().get(org.apache.struts2.ServletActionContext.HTTP_REQUEST);
			UserProfile UserProfileForm = new UserProfile();
			/*int selectedid = practicemanagerForm.getId();*/
			
			 connection = Connection_provider.getconnection();
			 
				if(loginInfo.getUserType()==1){
					String userid = userProfileForm.getSelectedClinicid();
					//connection = DriverManager.getConnection(""+Constants.DB_HOST+":3306/"+userid+"","root","mysql");
					connection = DriverManager.getConnection(""+Constants.DB_HOST+":3306/"+userid+"",""+Constants.DB_USER+"",""+Constants.DB_PWD+"");
				}

			UserProfileDAO userProfileDAO = new JDBCUserProfileDAO(connection);
			ClientDAO clientDAO=new JDBCClientDAO(connection);
			UserProfileForm.setPractitonerType(userProfileForm.getPractitonerType());
			UserProfileForm.setFirstname(userProfileForm.getFirstname());
			UserProfileForm.setLastname(userProfileForm.getLastname());
			UserProfileForm.setInitial(userProfileForm.getInitial());
			String other = "Other";
			if(userProfileForm.getJobtitle().equalsIgnoreCase(other))
			{
				userProfileForm.setJobtitle(userProfileForm.getOtherJobTitle());
			}
			UserProfileForm.setJobtitle(userProfileForm.getJobtitle());
			/*UserProfileForm.setDiscription(userProfileForm.getDiscription());*/
			UserProfileForm.setDiciplineName(userProfileForm.getDiciplineName());
			UserProfileForm.setRegisterno(userProfileForm.getRegisterno());
			UserProfileForm.setUsergroup(userProfileForm.getUsergroup());
			UserProfileForm.setUsercheck(userProfileForm.isUsercheck());
			UserProfileForm.setAppointmentdiary(userProfileForm.isAppointmentdiary());
			String colorWhite = "FFFFFF"; String color = "";
			if(userProfileForm.getDiarycolor().equals(colorWhite))
			{
				userProfileForm.setDiarycolor(color);
			}
			UserProfileForm.setDiarycolor(userProfileForm.getDiarycolor());
			UserProfileForm.setDiarycolumnposition(userProfileForm.getDiarycolumnposition());
			UserProfileForm.setCompressionrate(userProfileForm.getCompressionrate());
			UserProfileForm.setOnlinename(userProfileForm.getOnlinename());
			UserProfileForm.setIsavailableonline(userProfileForm.isIsavailableonline());
			UserProfileForm.setLoginsystem(userProfileForm.isLoginsystem());
			UserProfileForm.setUserid(userProfileForm.getUserId());
			UserProfileForm.setPassword(userProfileForm.getPassword());
			UserProfileForm.setLastchanged(userProfileForm.getLastchanged());
			UserProfileForm.setChangefre(userProfileForm.getChangefre());
			UserProfileForm.setAppointmentbookingcontem(userProfileForm.getAppointmentbookingcontem());
			UserProfileForm.setAppointmentbookingdnatem(userProfileForm.getAppointmentbookingdnatem());
			UserProfileForm.setEmail(userProfileForm.getEmail());
			UserProfileForm.setMobile(userProfileForm.getMobile());
			UserProfileForm.setApmremote(userProfileForm.isApmremote());
			UserProfileForm.setOnlinebooking(userProfileForm.isOnlinebooking());
			UserProfileForm.setDnaCharge(userProfileForm.getDnaCharge());
			UserProfileForm.setCompAppCharge(userProfileForm.getCompAppCharge());	
			UserProfileForm.setQualification(userProfileForm.getQualification());
			UserProfileForm.setHasDiary(userProfileForm.isHasDiary());
			UserProfileForm.setSecondary_doc(userProfileForm.isSecondary_doc());
			UserProfileForm.setChargeType(userProfileForm.getChargeType());
			UserProfileForm.setDoctor(userProfileForm.getDoctor());
			UserProfileForm.setJobgroup(userProfileForm.getJobgroup());
			UserProfileForm.setIpdCharge(userProfileForm.getIpdCharge());
			UserProfileForm.setSurgeonCharge(userProfileForm.getSurgeonCharge());
			UserProfileForm.setVisitingdoctor(userProfileForm.isVisitingdoctor());
			UserProfileForm.setWardid(userProfileForm.getWardid());
			UserProfileForm.setLabname(userProfileForm.getLabname());
			UserProfileForm.setDob(userProfileForm.getDob());
			UserProfileForm.setId(selectedid);
			UserProfileForm.setLicenceId(userProfileForm.getLicenceId());
			UserProfileForm.setEmpid(userProfileForm.getEmpid());
			String indentocations = userProfileDAO.getLocationFromDiciplineID(userProfileForm.getDiciplineName());
			UserProfileForm.setLocation(indentocations);
			UserProfileForm.setIpdconsultper(""+userProfileForm.getIpdCharge());
			int result=userProfileDAO.Updateempcode(UserProfileForm);
			//get access
			/*connection = DriverManager.getConnection(""+Constants.DB_HOST+":3306/admin",""+Constants.DB_USER+"",""+Constants.DB_PWD+"");
			userProfileDAO = new JDBCUserProfileDAO(connection);
			Access access = userProfileDAO.getRoleAccess(userProfileForm.getJobtitle());*/
			
			
			userProfileDAO = new JDBCUserProfileDAO(connection);
			String existingUserid = userProfileDAO.getexistingUserid(selectedid);
			
			String userImageFileName = userProfileForm.getUserImageFileName();
			File userImage = userProfileForm.getUserImage();
			if(userImageFileName!=null){			
				userImageFileName =loginInfo.getClinicUserid()+"_"+userImageFileName;
				//update image name
				int res=clientDAO.updateUserImageUserphoto(userImageFileName,selectedid);
			}else{
				userImageFileName = loginInfo.getClinicUserid()+"_"+userImageFileName;
				
				String filepath = request.getRealPath("/liveData/"+userImageFileName);
				String clientImageData = userProfileForm.getProfileimg();
				byte[] imagedata = DatatypeConverter.parseBase64Binary(clientImageData.substring(clientImageData.indexOf(",") + 1));
				BufferedImage bufferedImage = ImageIO.read(new ByteArrayInputStream(imagedata));
				if(!userProfileForm.getProfileimg().equals("")){
					ImageIO.write(bufferedImage, "png", new File(filepath));
				}
			}
			//String userImageContentType = clientForm.getUserImageContentType();
			
			if(userProfileForm.getUserImageContentType()!=null){
			String filePath = request.getRealPath("/liveData/clinicLogo/");			
//			System.out.println("Server path:" + filePath);
			File fileToCreate = new File(filePath, userImageFileName);
			FileUtils.copyFile(userImage, fileToCreate);
			
			UserProfileForm.setUserImageFileName(userImageFileName);
			
			
			
			{
				DateTimeUtils.saveFileToGlobelDirectory(filePath, userImageFileName, userImage);
			}
			}
			
			int userType = userProfileDAO.updateUserprofile(UserProfileForm);
			//int updateaccess = userProfileDAO.updateUserAccess(access,selectedid);
			
			//update admin database
			connection = DriverManager.getConnection(""+Constants.DB_HOST+":3306/admin",""+Constants.DB_USER+"",""+Constants.DB_PWD+"");
			userProfileDAO = new JDBCUserProfileDAO(connection);
			int updte =  userProfileDAO.updatePractitionerAdminData(UserProfileForm.getUserid(),UserProfileForm.getEmail(),userProfileForm.getMobile(),existingUserid);
			
			
			userProfileForm.setMessage("Updated Sucessfully!!");
			addActionMessage("Updated Sucessfully!!");
			
			if(loginInfo.getUserType() == 4){
				return "profile";
			}
			/*else{			
				setFormData();
			}*/
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		finally{
			
			connection.close();
		}
		
		if(loginInfo.getUserType()==1){
			return "adminuserprofile";
		}
		
		if(loginInfo.getUserType()==2){
			return "updatesave";
		}
		//return "updatesave";
		return SUCCESS;
	}
	
	public String edit() throws SQLException{
		if(!verifyLogin(request)){
			return "login";
		}
		
		int selectedid = 0;
		
		
		Connection connection = null;
		try{
			connection = Connection_provider.getconnection();
			UserProfileDAO userProfileDAO = new JDBCUserProfileDAO(connection);
			
			if(loginInfo.getUserType()==1){
				String userid = request.getParameter("idd");
				String clinicid = request.getParameter("clinicid");
				
				userProfileForm.setSelectedClinicid(clinicid);
				userProfileForm.setSelectedUserid(userid);
				
				//connection = DriverManager.getConnection(""+Constants.DB_HOST+":3306/"+clinicid+"","root","mysql");
				connection = DriverManager.getConnection(""+Constants.DB_HOST+":3306/"+clinicid+"",""+Constants.DB_USER+"",""+Constants.DB_PWD+"");
				
				ClinicDAO	clinicListDAO = new JDBCClinicDAO(connection);
				userProfileDAO = new JDBCUserProfileDAO(connection);
				selectedid = clinicListDAO.getAdminClinicId(userid);
				
			}else{
				 selectedid = Integer.parseInt(request.getParameter("id"));
			}
			
			ArrayList<String> initialList = new ArrayList<String>();
			ArrayList<Master> jobTitleList = new ArrayList<Master>();
			
			ArrayList<Master>jobGroupList = userProfileDAO.getJobGroupList("0");
			userProfileForm.setJobGroupList(jobGroupList);
			
			ArrayList<Master>doctorList = userProfileDAO.getDoctorList();
			userProfileForm.setDoctorList(doctorList);
			
			
				
				
				ClientDAO clientDAO = new JDBCClientDAO(connection);
				
				
				initialList = clientDAO.getInitialList();
				userProfileForm.setInitialList(initialList);
				
				
			
			//start coding
			UserProfile userprofile = userProfileDAO.getUserprofileDetails(selectedid);
			jobTitleList = userProfileDAO.getUserJobTitleList(userprofile.getJobgroup());
			userProfileForm.setJobTitleLists(jobTitleList);
			userProfileForm.setJobtitles(userprofile.getJobtitle());
			
			userProfileForm.setLicenceId(userprofile.getLicenceId());
			/*userprofile.setUserprofileId(userprofile.getUserprofileId());*/
			userProfileForm.setFirstname(userprofile.getFirstname());
			userProfileForm.setLastname(userprofile.getLastname());
			userProfileForm.setInitial(userprofile.getInitial());
			userProfileForm.setJobtitle(userprofile.getJobtitle());
			/*userProfileForm.setDiscription(userprofile.getDiscription());*/
			userProfileForm.setDiciplineName(userprofile.getDiciplineName());
			userProfileForm.setRegisterno(userprofile.getRegisterno());
			userProfileForm.setUsergroup(userprofile.getUsergroup());
			userProfileForm.setUsercheck(userprofile.isUsercheck());
			userProfileForm.setAppointmentdiary(userprofile.isAppointmentdiary());
			userProfileForm.setDiarycolor(userprofile.getDiarycolor());
			userProfileForm.setDiarycolumnposition(userprofile.getDiarycolumnposition());
			userProfileForm.setCompressionrate(userprofile.getCompressionrate());
			userProfileForm.setOnlinename(userprofile.getOnlinename());
			userProfileForm.setIsavailableonline(userprofile.isIsavailableonline());
			userProfileForm.setLoginsystem(userprofile.isLoginsystem());
			userProfileForm.setUserId(userprofile.getUserid());
			/*userProfileForm.setPassword(userprofile.getPassword());*/
			userProfileForm.setLastchanged(userprofile.getLastchanged());
			userProfileForm.setChangefre(userprofile.getChangefre());
			userProfileForm.setAppointmentbookingcontem(userprofile.getAppointmentbookingcontem());
			userProfileForm.setAppointmentbookingdnatem(userprofile.getAppointmentbookingdnatem());
			userProfileForm.setEmail(userprofile.getEmail());
			userProfileForm.setMobile(userprofile.getMobile());
			userProfileForm.setApmremote(userprofile.isApmremote());
			userProfileForm.setOnlinebooking(userprofile.isOnlinebooking());
			userProfileForm.setDnaCharge(userprofile.getDnaCharge());
			userProfileForm.setCompAppCharge(userprofile.getCompAppCharge());
			userProfileForm.setHasDiary(userprofile.isHasDiary());
			userProfileForm.setSecondary_doc(userprofile.isSecondary_doc());
			userProfileForm.setQualification(userprofile.getQualification());
			userProfileForm.setPractitonerType(userprofile.getPractitonerType());
			userProfileForm.setChargeType(userprofile.getChargeType());
			userProfileForm.setJobgroup(userprofile.getJobgroup());
			userProfileForm.setDoctor(userprofile.getDoctor());
			userProfileForm.setIpdCharge(userprofile.getIpdCharge());
			userProfileForm.setSurgeonCharge(userprofile.getSurgeonCharge());
			userProfileForm.setVisitingdoctor(userprofile.isVisitingdoctor());
			userProfileForm.setWardid(userprofile.getWardid());
			userProfileForm.setLabname(userprofile.getLabname());
			userProfileForm.setId(userprofile.getId());
			userProfileForm.setDob(userprofile.getDob());
			userProfileForm.setEmpid(userprofile.getEmpid());
			userProfileForm.setUserImageFileName(userprofile.getUserImageFileName());
			
			//userProfileForm.setDiciplineName("Dummy three");
			
		}catch (Exception e) {
			e.printStackTrace();
		}finally{
			
			connection.close();
		}
		
		return "edit";
	}
	
	public String delete() throws SQLException{
		if(!verifyLogin(request)){
			return "login";
		}
		Connection connection = null;
		try{
			connection = Connection_provider.getconnection();
			//UserProfileDAO userProfileDAO = new JDBCUserProfileDAO(connection);
			UserProfileDAO userProfileDAO = new JDBCUserProfileDAO(connection);
			
			//start coding
			
			HttpServletRequest request = (HttpServletRequest)ActionContext.getContext().get(org.apache.struts2.ServletActionContext.HTTP_REQUEST);
			int selectedid= Integer.parseInt(request.getParameter("id"));
			//UserProfileDAO userprofileDAO = new JDBCUserProfileDAO(connection);
			
			String userid = userProfileDAO.getAdminDbUserID(selectedid);
			
			/*int deleteUserProfile = userProfileDAO.deleteUserprofile(selectedid);*/
			int result = userProfileDAO.deleteUserprofile(selectedid);
			
			
			
			//delete from apmadmin
			//connection = DriverManager.getConnection(""+Constants.DB_HOST+":3306/admin","root","mysql");
			connection = DriverManager.getConnection(""+Constants.DB_HOST+":3306/admin",""+Constants.DB_USER+"",""+Constants.DB_PWD+"");
			
			userProfileDAO = new JDBCUserProfileDAO(connection);
			int del = userProfileDAO.deleteAdminUserprofile(userid);
			
		
			
		
			
			userProfileForm.setMessage("Deleted Sucessfully!!");
			addActionMessage("Deleted Sucessfully!!");
			setFormData();
			
			
		}catch (Exception e) {
			e.printStackTrace();
		}finally{
			
			connection.close();
		}
		
		return SUCCESS;
	}
	
	
	
	public UserProfileForm getModel() {
		
		return userProfileForm;
	}


	public void prepare() throws Exception {
		
		ArrayList<String>diciplineList = new ArrayList<String>();
		diciplineList.add("Dummy One");
		diciplineList.add("Dummy two");
		diciplineList.add("Dummy three");
		diciplineList.add("Dummy four");
		
		userProfileForm.setDiciplineList(diciplineList);
		
		ArrayList<String>diarycolorList = new ArrayList<String>();
		diarycolorList.add("Green");
		diarycolorList.add("Blue");
		diarycolorList.add("Red");
		diarycolorList.add("White");
		diarycolorList.add("Maroon");
		diarycolorList.add("Yellow");
		
		userProfileForm.setDiarycolorList(diarycolorList);
		
		ArrayList<String>abctList = new ArrayList<String>();
		abctList.add("Appointment Confirmation");
		abctList.add("Appointment Follow Up Reminder");
		abctList.add("Appiontment Reminder");
		abctList.add("Appt Confirmation - Plates");
		abctList.add("Diabetic Assessment Reminder");
		abctList.add("Patient Cancellation");
		
		userProfileForm.setAbctList(abctList);
		
		ArrayList<String>adtList = new ArrayList<String>();
		adtList.add("Appointment Confirmation");
		adtList.add("Appointment Follow Up Reminder");
		adtList.add("Appiontment Reminder");
		adtList.add("Appt Confirmation - Plates");
		adtList.add("Diabetic Assessment Reminder");
		adtList.add("Patient Cancellation");
		
		userProfileForm.setAdtList(adtList);
		
		userProfileForm.setJobtitle("Practitioner");
		
		Connection connection = null;
		try{
			
			connection = Connection_provider.getconnection();
			MasterDAO masterDAO = new JDBCMasterDAO(connection);
			UserProfileDAO userProfileDAO = new JDBCUserProfileDAO(connection);
			ArrayList<Master>disciplineList =  masterDAO.getDisciplineDataList();
			userProfileForm.setDisciplineList(disciplineList);
			
			ArrayList<Master>practitonerTypeList = masterDAO.getPractitonerTypeList();
			userProfileForm.setPractitonerTypeList(practitonerTypeList);
			
			ArrayList<Currency>chargeSignList = new ArrayList<Currency>();
			chargeSignList.add(new Currency(0, "%"));
			//chargeSignList.add(new Currency(1, Constants.getCurrency(loginInfo)));
			userProfileForm.setChargeSignList(chargeSignList);
			
			ArrayList<Master>countrycodeList = masterDAO.getCountryCodeList();
			userProfileForm.setCountrycodeList(countrycodeList);
			userProfileForm.setCountrycode(loginInfo.getCountry());
			
			ArrayList<Master>wardList = masterDAO.getWardList();
			userProfileForm.setWardList(wardList);
			   
		ArrayList<Master>labnameList = userProfileDAO.getLabnameList();  
		userProfileForm.setLabnameList(labnameList);
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			connection.close();
		}
		
	}
	
	public String addOtherJobTitle() throws SQLException{
		if(!verifyLogin(request)){
			return "login";
		}
		String jobTitle = request.getParameter("jobtitle");
		Connection connection = null;
		try{
			UserProfile userProfile = new UserProfile();
			connection = Connection_provider.getconnection();
			UserProfileDAO userProfileDAO = new JDBCUserProfileDAO(connection);
			
			
			int result = userProfileDAO.insertJobTitle(userProfile,jobTitle);
		}
		catch (Exception e) {
			// TODO: handle exception
		}
		finally{
			
			connection.close();
		}
		return null;
	}
	
	public String saveP(){
		
		
		
		return "SaveSucess";
	}
public String saveDetails()throws SQLException{
	if(!verifyLogin(request)){
		return "login";
	}
		//save practice manager
		Connection connection = null;
		HttpServletRequest request = (HttpServletRequest)ActionContext.getContext().get(org.apache.struts2.ServletActionContext.HTTP_REQUEST);
		try{
			connection = Connection_provider.getconnection();
			UserProfileDAO userProfileDAO = new JDBCUserProfileDAO(connection);
			ClientDAO clientDAO = new JDBCClientDAO(connection);
			UserProfile userprofile = new UserProfile();
			userprofile.setFirstname(userProfileForm.getFirstname());
			userprofile.setLastname(userProfileForm.getLastname());
			userprofile.setInitial(userProfileForm.getInitial());
			userprofile.setDiciplineName(userProfileForm.getDiciplineName());
			
			//akash 27 jan 2018 set indent location
			
			String indentocations = userProfileDAO.getLocationFromDiciplineID(userProfileForm.getDiciplineName());
			userprofile.setLocation(indentocations);
			String clinicname = loginInfo.getClinicName();
			
			
			String other = "Other";
			if(userProfileForm.getJobtitle().equalsIgnoreCase(other))
			{
				userProfileForm.setJobtitle(userProfileForm.getOtherJobTitle());
			}
			
			userprofile.setJobtitle(userProfileForm.getJobtitle());
			
			userprofile.setUserid(userProfileForm.getUserId());
			userprofile.setPassword(userProfileForm.getPassword());
			
			userprofile.setEmail(userProfileForm.getEmail());
			userprofile.setMobile(userProfileForm.getMobile());
			
			userprofile.setUserType(4);
			
			userprofile.setDnaCharge(userProfileForm.getDnaCharge());
			userprofile.setCompAppCharge(userProfileForm.getCompAppCharge());
			userprofile.setHasDiary(userProfileForm.isHasDiary());
			userprofile.setSecondary_doc(userProfileForm.isSecondary_doc());
			userprofile.setQualification(userProfileForm.getQualification());
			userprofile.setPractitonerType(userProfileForm.getPractitonerType());
			userprofile.setChargeType(userProfileForm.getChargeType());
			userprofile.setIpdCharge(userProfileForm.getIpdCharge());
			userprofile.setSurgeonCharge(userProfileForm.getSurgeonCharge());
			userprofile.setVisitingdoctor(userProfileForm.isVisitingdoctor());
			userprofile.setLicenceId(userProfileForm.getLicenceId());
			userprofile.setCommercial(userProfileForm.getCommercial());
			userprofile.setConsultatantshare(userProfileForm.getConsultatantshare());
			userprofile.setCity(userProfileForm.getCity());
			userprofile.setHospitalname(userProfileForm.getHospitalname());
			userprofile.setClinic_staff(userProfileForm.getClinic_name());
			
			if(userProfileForm.getDob()==null){
				userProfileForm.setDob("");
			}
			userprofile.setDob(userProfileForm.getDob());
			//for check userid is present in that clinic
			int checkuser= userProfileDAO.checkIsValidReg(userprofile,clinicname);
			
			if(checkuser==0){
				//save normal password
				int result = userProfileDAO.saveAdminPassword(userprofile);
				
				userprofile.setDoctor(userProfileForm.getDoctor());
				userprofile.setJobgroup(userProfileForm.getJobgroup());
				userprofile.setWardid(userProfileForm.getWardid());
				userprofile.setLabname(userProfileForm.getLabname());
				userprofile.setEmpid(userProfileForm.getEmpid());
				userprofile.setClinicname(clinicname);
				int id = loginInfo.getClinicid();
				String userid = loginInfo.getClinicUserid();
				//New code as per praful sir requirememt 21 nov 2017
				/*int selectedid = userProfileDAO.saveDetailsUserprofile(userprofile,loginInfo.getId());*/
				String userImageFileName = userProfileForm.getUserImageFileName();
				File userImage = userProfileForm.getUserImage();
				if(userImageFileName!=null){			
					userImageFileName =loginInfo.getClinicUserid()+"_"+userImageFileName;
					//update image name
					//int res=clientDAO.updateClientUserImage(userImageFileName,result);
				}else{
					userImageFileName = loginInfo.getClinicUserid()+"_"+result+"_profileimg.png";
					
					String filepath = request.getRealPath("/liveData/"+userImageFileName);
					String clientImageData = userProfileForm.getProfileimg();
					byte[] imagedata = DatatypeConverter.parseBase64Binary(clientImageData.substring(clientImageData.indexOf(",") + 1));
					BufferedImage bufferedImage = ImageIO.read(new ByteArrayInputStream(imagedata));
					if(!userProfileForm.getProfileimg().equals("")){
						ImageIO.write(bufferedImage, "png", new File(filepath));
					}
				}
				//String userImageContentType = clientForm.getUserImageContentType();
				
				if(userProfileForm.getUserImageContentType()!=null){
				String filePath = request.getRealPath("/liveData/clinicLogo/");			
//				System.out.println("Server path:" + filePath);
				File fileToCreate = new File(filePath, userImageFileName);
				FileUtils.copyFile(userImage, fileToCreate);
				userprofile.setUserImageFileName(userImageFileName);
				DateTimeUtils.saveFileToGlobelDirectory(filePath, userImageFileName, userImage);
				}
			
				//store data in apm_user in hospital schema
				int selectedid = userProfileDAO.saveDetailsUserprofile(userprofile,loginInfo.getClinicid());
				
				//  11 Nov 2017 save in job title
				userprofile.setSelectedid(""+selectedid);
				
				//save userid and userkey in user access
				int useraccess = userProfileDAO.saveInUserAccess(userprofile.getUserid(),userprofile.getSelectedid());
				
				//save in jobtitle for make job wise access using name
				int result1 = userProfileDAO.saveInJobTitle(userprofile);
				//userprofile.setEmpid(userProfileForm.getEmpid());
				
				// save data in payroll
				int res1=userProfileDAO.saveEmployeeDetails(userprofile);
				
				//Send userid and password   24 Nov
				String useridtest = userProfileForm.getUserId();
				String userpwd = userProfileForm.getPassword();
				String usermob = userProfileForm.getMobile();
				String name = userProfileForm.getFirstname()+" "+userProfileForm.getLastname();
				String testuserid=userProfileForm.getClinic_userid();
				
				SmsService s = new SmsService();
				String templateid = userProfileDAO.getSMSTemplateID("User Registration");
				String message = ""+name+", your user ID: "+useridtest+" and password: "+userpwd+" from "+loginInfo.getClinicName()+" (Yuvi Care)";
				s.sendSms(message, usermob, loginInfo, new EmailLetterLog(),templateid);
				
				
				//save physio to admin db
				//connection = DriverManager.getConnection(""+Constants.DB_HOST+":3306/admin","root","mysql");
				connection = DriverManager.getConnection(""+Constants.DB_HOST+":3306/admin",""+Constants.DB_USER+"",""+Constants.DB_PWD+"");
				
				userProfileDAO = new JDBCUserProfileDAO(connection);
				userprofile.setMobile(userProfileForm.getMobile());
				//New code as per praful sir requirememt 21 nov 2017
				/*int res = userProfileDAO.savePractitionerToAdmin(userprofile,loginInfo.getUserId());*/
				
				//save data in admin schema 
				int res = userProfileDAO.savePractitionerToAdmin(userprofile,loginInfo.getClinicUserid());
				
				//save ipd hospital data to admin schema
	            if(loginInfo.getClinicid1().equals("manaspro")) {
	            	res=userProfileDAO.saveIpdhospitaldataToAdmin(userprofile,loginInfo.getClinicUserid(),testuserid);
	            }
				//update access
				/*Access access = userProfileDAO.getRoleAccess(userProfileForm.getJobtitle());
				connection = Connection_provider.getconnection();
				userProfileDAO = new JDBCUserProfileDAO(connection);
				int updateaccess = userProfileDAO.updateUserAccess(access,selectedid);*/
				
				userProfileForm.setMessage("Registred Sucessfully!!");
				addActionMessage("Registred Sucessfully!!");
			}else{
				return "input";
			}
			//setFormData();			
			
		}catch (Exception e) {
			e.printStackTrace();
		}finally{
			
			connection.close();
		}
		return "save";
	}

	public String profilePractitioner() throws SQLException{
		if(!verifyLogin(request)){
			return "login";
		}
		Connection connection = null;
		try{
			connection = Connection_provider.getconnection();
			UserProfileDAO userProfileDAO = new JDBCUserProfileDAO(connection);
			int practitionerId = loginInfo.getId();
			
			UserProfile userprofile = userProfileDAO.getUserprofileDetails(practitionerId);
			
			//userProfileForm.setId(userprofile.getId());
			userProfileForm.setFirstname(userprofile.getFirstname());
			userProfileForm.setLastname(userprofile.getLastname());
			userProfileForm.setInitial(userprofile.getInitial());
			userProfileForm.setJobtitle(userprofile.getJobtitle());
			
			/*userProfileForm.setDiscription(userprofile.getDiscription());*/
			userProfileForm.setDiciplineName(userprofile.getDiciplineName());
			userProfileForm.setRegisterno(userprofile.getRegisterno());
			userProfileForm.setUsergroup(userprofile.getUsergroup());
			userProfileForm.setUsercheck(userprofile.isUsercheck());
			userProfileForm.setAppointmentdiary(userprofile.isAppointmentdiary());
			userProfileForm.setDiarycolor(userprofile.getDiarycolor());
			userProfileForm.setDiarycolumnposition(userprofile.getDiarycolumnposition());
			userProfileForm.setCompressionrate(userprofile.getCompressionrate());
			userProfileForm.setOnlinename(userprofile.getOnlinename());
			userProfileForm.setIsavailableonline(userprofile.isIsavailableonline());
			userProfileForm.setLoginsystem(userprofile.isLoginsystem());
			userProfileForm.setUserId(userprofile.getUserid());
			
			/*userProfileForm.setPassword(userprofile.getPassword());*/
			userProfileForm.setLastchanged(userprofile.getLastchanged());
			userProfileForm.setChangefre(userprofile.getChangefre());
			userProfileForm.setAppointmentbookingcontem(userprofile.getAppointmentbookingcontem());
			userProfileForm.setAppointmentbookingdnatem(userprofile.getAppointmentbookingdnatem());
			userProfileForm.setEmail(userprofile.getEmail());
			userProfileForm.setMobile(userprofile.getMobile());
			userProfileForm.setApmremote(userprofile.isApmremote());
			userProfileForm.setOnlinebooking(userprofile.isOnlinebooking());
			userProfileForm.setDnaCharge(userprofile.getDnaCharge());
			userProfileForm.setCompAppCharge(userprofile.getCompAppCharge());
			userProfileForm.setVisitingdoctor(userprofile.isVisitingdoctor());
			
			userProfileForm.setId(userprofile.getId());
			
			
		}catch (Exception e) {
			e.printStackTrace();
		}finally{
			
			connection.close();
		}
		
		return "practitionerProfile";
	}
	

	public String checkEmailIdExist() throws SQLException, IOException{
		Connection connection = null;
		String emailId = request.getParameter("email").trim();
		String selectedid = request.getParameter("selectedid");
		
		try{
			
			connection = Connection_provider.getconnection();
			UserProfileDAO userProfileDAO = new JDBCUserProfileDAO(connection);
			
			String existEmailid = "";
			
			if(!selectedid.equals("0")){
				 existEmailid = userProfileDAO.getExistingEmailID(selectedid);
			}
			
			connection = DriverManager.getConnection(""+Constants.DB_HOST+":3306/admin",""+Constants.DB_USER+"",""+Constants.DB_PWD+"");
			userProfileDAO = new JDBCUserProfileDAO(connection);
			
			boolean emailIdExist = userProfileDAO.isEmailIdExist(emailId,selectedid,existEmailid);
			
			if(emailIdExist){
				response.getWriter().write("false");
			}else{
				response.getWriter().write("true");
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			
			connection.close();
		}
		return null;
	}

	public String checkMobileNoExist()throws SQLException, IOException{
		Connection connection = null;
		String mobileNo = request.getParameter("mobile").trim();
		mobileNo = "0" + mobileNo;
		try{
			connection = Connection_provider.getconnection();
			
			connection = DriverManager.getConnection(""+Constants.DB_HOST+":3306/admin",""+Constants.DB_USER+"",""+Constants.DB_PWD+"");
			UserProfileDAO userProfileDAO = new JDBCUserProfileDAO(connection);
			
			boolean mobileNoExist = userProfileDAO.isMobileNoExist(mobileNo);
			
			if(mobileNoExist){
				response.getWriter().write("false");
				
			}else{
				response.getWriter().write("true");
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			
			connection.close();
		}
		return null;
	}
	
	public String updateglobalaccessss() throws Exception{
		Connection connection=null;
		try {
			connection=Connection_provider.getconnection();
			UserProfileDAO userProfileDAO = new JDBCUserProfileDAO(connection);
			String jobtitle = request.getParameter("jobtitle");
			String val = request.getParameter("val");
			if(val.equals("true")){
				val="1";
			}else{
				val="0";
			}
			int res = userProfileDAO.updateGlobalAccess(jobtitle,val);
			response.setContentType("text/html");
			response.setHeader("Cache-Control", "no-cache");
			response.getWriter().write(""); 	
		} catch (Exception e) {
			e.printStackTrace();
		} finally{
			
			connection.close();
		}
		return null;
	}
	public String roleaccess() throws Exception{
		Connection connection=null;
		try {
			connection=Connection_provider.getconnection();
			ClinicDAO clinicDAO = new JDBCClinicDAO(connection);
			UserProfileDAO userProfileDAO = new JDBCUserProfileDAO(connection);
			DiaryManagementDAO diaryManagementDAO=new JDBCDiaryManagentDAO(connection); 
			InventoryProductDAO inventoryProductDAO = new JDBCInventoryProductDAO(connection);
			IndentDAO indentDAO = new JDBCIndentDAO(connection);
			String jobtitle = request.getParameter("jobtitle");
			String fname = request.getParameter("fname");
			String lname = request.getParameter("lname");
			String userid = request.getParameter("userid");
			
			//new method to get accesss from apm_user by lokesh
			DiaryManagement diaryManagement2= userProfileDAO.getMainAccessofUser(userid);
			
			String newjobtitle=jobtitle;
			if(diaryManagement2.getUserwiseaceess()==1){
				newjobtitle = userid;
			}
			
			Clinic clinic = clinicDAO.checkroleAccessName(newjobtitle);
			int id = 0;
			if (clinic.getName()==null) {
				id = clinicDAO.setroleaccesssetiing(newjobtitle);
			}else {
				id = clinic.getId();
			}
			 //ArrayList<Clinic> arrayList = clinicDAO.getAllRoleAccessData(id,modulename);
			 String fullname = fname +" "+ lname;
			 DiaryManagement diaryManagement=diaryManagementDAO.getRoleAccessofUser(newjobtitle); 
			 
			 userProfileForm.setSetup_master(diaryManagement.isSetup_master());
			 
			 userProfileForm.setUserwiseaceess(diaryManagement2.getUserwiseaceess());
			 userProfileForm.setSetting(diaryManagement.isSetting());
			 userProfileForm.setOpdid(diaryManagement.isOpd());
			 userProfileForm.setIpdid(diaryManagement.isIpd());
			 userProfileForm.setEmr(diaryManagement.isEmr());
			 userProfileForm.setPacs(diaryManagement.isPacs());
			 userProfileForm.setDischarge(diaryManagement.isDischarge());
			 userProfileForm.setMedicine(diaryManagement.isMedicine());
			 userProfileForm.setInvestigation(diaryManagement.isInvestigation());
			 userProfileForm.setBloodbank(diaryManagement.isBloodbank());
			 userProfileForm.setAccounts(diaryManagement.isAccounts());
			 userProfileForm.setPayroll(diaryManagement.isPayroll());
			 userProfileForm.setExpenses(diaryManagement.isExpenses());
			 userProfileForm.setInventory(diaryManagement.isInventory());
			 userProfileForm.setMis(diaryManagement.isMis());
			 userProfileForm.setConsultants(diaryManagement.isConsultants());
			 userProfileForm.setPatient(diaryManagement.isPatient());
			 userProfileForm.setAppointmentfinder(diaryManagement.isAppointmentfinder());
			   
			 userProfileForm.setPacks(diaryManagement.isPacks());
			 userProfileForm.setInvestigation_chart(diaryManagement.isInvestigation_chart());
			 userProfileForm.setSheduler(diaryManagement.isSheduler());
			 userProfileForm.setHousekeeping(diaryManagement.isHousekeeping());
			 userProfileForm.setDietery(diaryManagement.isDietery());
			 userProfileForm.setCafeteria(diaryManagement.isCafeteria());
			 userProfileForm.setPackages(diaryManagement.isPackages());
			 userProfileForm.setAmbulance(diaryManagement.isAmbulance());
			 userProfileForm.setBank_deposite(diaryManagement.isBank_deposite());
			 userProfileForm.setAccount_reconcilation(diaryManagement.isAccount_reconcilation());
			   //ot,casualty,pharmacy,mrd,marketing,voice recorder
			 userProfileForm.setJobtitle(jobtitle);
			   
			// get new terms ot,casualty,pharmacy,mrd,marketing,voice recorder
			 userProfileForm.setOt(diaryManagement.isOt());
			 userProfileForm.setCasualty(diaryManagement.isCasualty());
			 userProfileForm.setPharmacy(diaryManagement.isPharmacy());
			 userProfileForm.setMrd(diaryManagement.isMrd());
			 userProfileForm.setMarketing(diaryManagement.isMarketing());
			 userProfileForm.setVoice_recording(diaryManagement.isVoice_recording());
			 userProfileForm.setIndent(diaryManagement.isIndent());
			 userProfileForm.setDaily_opd(diaryManagement.isDaily_opd());
			 
			 //  09 jan 2017
			 userProfileForm.setIndent_approve(diaryManagement.isIndent_approve());
			 userProfileForm.setCash_desk(diaryManagement.isCash_desk());
			 
			 //set Main module
			 userProfileForm.setJobtitle(jobtitle);
			 userProfileForm.setFullname(fullname);
			 
			 //lokesh 
			 userProfileForm.setShow_master(diaryManagement.isShow_master());
			 userProfileForm.setToken_display(diaryManagement.isToken_display());
			 userProfileForm.setAdd_medicine(diaryManagement2.isAdd_medicine());
	/**/
			 userProfileForm.setPharm_print_backdate(diaryManagement.isPharm_print_backdate());
			 userProfileForm.setModify_disc(diaryManagement2.isModify_disc());
			 userProfileForm.setEdit_paypo(diaryManagement2.isEdit_paypo());
			 userProfileForm.setAdjustmentaccess(diaryManagement2.isAdjustmentaccess());
			 userProfileForm.setSupplier_add(diaryManagement2.isSupplier_add());
			 userProfileForm.setDirect_refund_disc(diaryManagement2.isDirect_refund_disc());
			 userProfileForm.setRef_dis_pay(diaryManagement2.isRef_dis_pay());
			 userProfileForm.setPrisc_new_req_access(diaryManagement2.isPrisc_new_req_access());
			 userProfileForm.setAdditional_disc(diaryManagement2.isAdditional_disc());
			 userProfileForm.setCancel_invoice(diaryManagement2.isCancel_invoice());
			 UserProfile uf=new UserProfile();
			 uf=userProfileDAO.getIndivdualAccess(userid);
			 userProfileForm.setAdd_manual_charge(uf.isAdd_manual_charge());
			 userProfileForm.setUpdate_investigation_charge(uf.isUpdate_investigation_charge());
			 ArrayList<Clinic> opdlist = new ArrayList<Clinic>();
			 ArrayList<Clinic> ipdlist = new ArrayList<Clinic>();
			 ArrayList<Clinic> accountlist = new ArrayList<Clinic>();
			 ArrayList<Clinic> emrlist = new ArrayList<Clinic>();
			 ArrayList<Clinic> clientlist = new ArrayList<Clinic>();
			 ArrayList<Clinic> investigationrolelist = new ArrayList<Clinic>();
			 ArrayList<Clinic> indentrolelist = new ArrayList<Clinic>();
			 ArrayList<Master> indentloclist = new ArrayList<Master>();
			 ArrayList<UserProfile> useracceslist = new ArrayList<UserProfile>(); 
			 ArrayList<Clinic> mislist= new ArrayList<Clinic>();
			 MasterDAO masterDAO = new JDBCMasterDAO(connection);
			 
			 if(diaryManagement.isOpd()){
				 opdlist = clinicDAO.getAllRoleAccesssData(id,"1",newjobtitle);
			 }
			 if(diaryManagement.isMis()){
				 
				 mislist= clinicDAO.getAllMisRoleAccesssData(id, "1", userid);
			 }
			 if(diaryManagement.isIpd()){
				 ipdlist = clinicDAO.getAllRoleAccesssData(id,"2",newjobtitle);
				 ArrayList<Master>  arrayList = new ArrayList<Master>();
				 ArrayList<Master> wardList = masterDAO.getWardList();
				 String wardid = userProfileDAO.getWardIdsFromUserid(userid);
				 
				 for (Master master : wardList) {
					 if(wardid==null){
						 master.setStatus(0);
						 arrayList.add(master);
					 }else{
						 
					if(wardid.equals("0")){
						master.setStatus(0);
						
					}else{
						for (String s : wardid.split(",")) {
							if(s.equals("0")){
								continue;
							}
							
							if(s.equals(""+master.getId())){
								master.setStatus(1);
								break;
							}
						}
						
					}
					arrayList.add(master);
					 }
				}
				 userProfileForm.setWardList(arrayList);
			 }
			 
			 if(diaryManagement.isAccounts()){
				 accountlist = clinicDAO.getAllRoleAccesssData(id,"3",newjobtitle);
			 }
			 
			 if(diaryManagement.isEmr()){
				 emrlist = clinicDAO.getAllRoleAccesssData(id,"4",newjobtitle);
			 }
			 if(diaryManagement.isPatient()){
				 clientlist = clinicDAO.getAllRoleAccesssData(id,"5",newjobtitle);
				 
			 }
			 if(diaryManagement.isInvestigation()){
				 investigationrolelist = clinicDAO.getAllRoleAccesssData(id,"7",newjobtitle);
			 }
			 
			 if(diaryManagement.isIndent()){
				 indentrolelist = clinicDAO.getAllRoleAccesssData(id,"8",newjobtitle);
				 String location ="";
				 boolean ispharmacist = inventoryProductDAO.isPharamcistOrNot(userid);
				   if(ispharmacist){
					   location = inventoryProductDAO.getPharakmacyUserLocation(userid);
				   }else{
					   location = inventoryProductDAO.getHISUserLocation(userid);
				   }
				 ArrayList<Master> locationlist = masterDAO.getAllLocation(null);
				 String indentloc = userProfileDAO.getIndentApprovelocations(userid);
				String locationname = indentDAO.pharmacyLocationNameByID(location);
				userProfileForm.setLocationname(locationname);
				userProfileForm.setLocation(location);
				
				for (Master master : locationlist) {
					if(location.equals("" + master.getId())){
						continue;
					}else if (indentloc == null) {
						master.setStatus(0);
					} else if (indentloc.equals("")) {
						master.setStatus(0);
					} else {
						for (String s : indentloc.split(",")) {
							if (s.equals("" + master.getId())) {
								master.setStatus(1);
								break;
							}
						}
					}
					indentloclist.add(master);
				}
			 }
			
			 //21 March 2018
			 
			 UserProfile userProfile = userProfileDAO.getUserIndivisualAccess(userid);
			 userProfileForm.setRefund_dashboard(userProfile.isRefund_dashboard());
			 
			 userProfileForm.setIndentloclist(indentloclist);
			 userProfileForm.setUserId(userid);
			 userProfileForm.setOpdlist(opdlist);
			 userProfileForm.setIpdlist(ipdlist);
			 userProfileForm.setAccountlist(accountlist);
			 userProfileForm.setEmrlist(emrlist);
			 userProfileForm.setClientlist(clientlist);
			 userProfileForm.setInvestigationrolelist(investigationrolelist);
			 userProfileForm.setIndentrolelist(indentrolelist);
			 userProfileForm.setMislist(mislist);
			 
			 if(!DateTimeUtils.isNull(userid).equals("")){
				 if(!userProfileDAO.isExistInMisAccess(userid)){
					 userProfileDAO.saveUserForMisAccess(userid);
				 }
			 }
			 
			 
			 
		} catch (Exception e) {
			e.printStackTrace();
		} finally{
			
			connection.close();
		}
		return "roleacccess";
	}
	
	public String updatewidegetstatus() throws Exception{
		Connection connection=null;
		try {
			connection=Connection_provider.getconnection();
			UserProfileDAO userProfileDAO = new JDBCUserProfileDAO(connection);
			String jobtitle = request.getParameter("jobtitle");
			String val = request.getParameter("val");
			String cname = request.getParameter("cname"); 
			if(val.equals("true")){
				val="1";
			}else{
				val="0";
			}
			String[] data = cname.split(",");
			for (int i = 0; i < data.length; i++) {
				String string = data[i];
				//String chek = string;
				int res = userProfileDAO.updatewidegetstatus(jobtitle,val,string);
			}
			
			response.setContentType("text/html");
			response.setHeader("Cache-Control", "no-cache");
			response.getWriter().write(""); 	
		} catch (Exception e) {
			e.printStackTrace();
		} finally{
			
			connection.close();
		}
		return null;
	}
	public String updateactiveinactive() throws Exception{
		Connection connection=null;
		try {
			connection=Connection_provider.getconnection();
			UserProfileDAO userProfileDAO = new JDBCUserProfileDAO(connection);
			String id = request.getParameter("id");
			String val = request.getParameter("val");
			if(val.equals("true")){
				val="1";
			}else{
				val="0";
			}
			
			int res = userProfileDAO.updateactiveinactive(id,val);
			String userid=userProfileDAO.getAdminDbUserID(Integer.parseInt(id));
			int res1 = userProfileDAO.updatePayrollEmpaxtive(userid,val);
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
	
	public String updateindentlocations() throws Exception{
		Connection connection=null;
		try {
			connection=Connection_provider.getconnection();
			UserProfileDAO userProfileDAO = new JDBCUserProfileDAO(connection);
			String userid = request.getParameter("userid");
			String val = request.getParameter("val");
			
			
			int res = userProfileDAO.updateIndentLocations(userid,val);
			
			response.setContentType("text/html");
			response.setHeader("Cache-Control", "no-cache");
			response.getWriter().write(""); 	
		} catch (Exception e) {
			e.printStackTrace();
		} finally{
			
			connection.close();
		}
		return null;
	}
	public String updatewardid() throws Exception{
		Connection connection=null;
		try {
			connection=Connection_provider.getconnection();
			UserProfileDAO userProfileDAO = new JDBCUserProfileDAO(connection);
			String userid = request.getParameter("userid");
			String val = request.getParameter("val");
			
			
			int res = userProfileDAO.updatewardid(userid,val);
			
			response.setContentType("text/html");
			response.setHeader("Cache-Control", "no-cache");
			response.getWriter().write(""); 	
		} catch (Exception e) {
			e.printStackTrace();
		} finally{
			
			connection.close();
		}
		return null;
	}
	
	public String updateuserindivisualstatus() throws Exception{
		Connection connection=null;
		try {
			connection=Connection_provider.getconnection();
			UserProfileDAO userProfileDAO = new JDBCUserProfileDAO(connection);
			String userid = request.getParameter("userid");
			String val = request.getParameter("val");
			String cname = request.getParameter("cname"); 
			if(val.equals("true")){
				val="1";
			}else{
				val="0";
			}
			String[] data = cname.split(",");
			for (int i = 0; i < data.length; i++) {
				String string = data[i];
				//String chek = string;
				int res = userProfileDAO.updateuserindivisualstatus(userid,val,string);
			}
			
			response.setContentType("text/html");
			response.setHeader("Cache-Control", "no-cache");
			response.getWriter().write(""); 	
		} catch (Exception e) {
			e.printStackTrace();
		} finally{
			
			connection.close();
		}
		return null;
	}
	public String updatemiswidegetstatus() throws Exception{
		  Connection connection=null;
		  try {
		   connection=Connection_provider.getconnection();
		   UserProfileDAO userProfileDAO = new JDBCUserProfileDAO(connection);
		   String userid = request.getParameter("userid");
		   String val = request.getParameter("val");
		   String cname = request.getParameter("cname"); 
		   if(val.equals("true")){
		    val="1";
		   }else{
		    val="0";
		   }
		   String[] data = cname.split(",");
		   for (int i = 0; i < data.length; i++) {
		    String string = data[i];
		    //String chek = string;
		    int res = userProfileDAO.updatemiswidegetstatus(userid,val,string);
		   }
		   
		   response.setContentType("text/html");
		   response.setHeader("Cache-Control", "no-cache");
		   response.getWriter().write("");  
		  } catch (Exception e) {
		   e.printStackTrace();
		  } finally{
		   
		   connection.close();
		  }
		  return null;
		 }
	
	public String updateuserwiseaccess(){
		Connection connection=null;
		try {
			connection=Connection_provider.getconnection();
			UserProfileDAO userProfileDAO = new JDBCUserProfileDAO(connection);
			String userid = request.getParameter("userid");
			String val = request.getParameter("val");
			String cname = request.getParameter("cname");
			String showingcol_name=request.getParameter("showingcol_name");
			if(val.equals("true")){
				val="1";
			}else{
				val="0";
			}
			int res=0;
			String[] data = cname.split(",");
			for (int i = 0; i < data.length; i++) {
				String string = data[i];
				//String chek = string;
				res = userProfileDAO.updateUserWiseAccess(userid,val,string);
				//user Wise access log
				if(res>0){
					DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				    Calendar cal = Calendar.getInstance();
				    String datetime = dateFormat.format(cal.getTime());  
					 res = userProfileDAO.saveUserWiseAccessLog(loginInfo.getUserId(),datetime,userid,val,string,showingcol_name);
				}
			}
			
			response.setContentType("text/html");
			response.setHeader("Cache-Control", "no-cache");
			response.getWriter().write(""); 	

			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public String settypeofaccess(){
		Connection connection=null;
		try {
			connection=Connection_provider.getconnection();
			UserProfileDAO userProfileDAO = new JDBCUserProfileDAO(connection);
			String userid = request.getParameter("userid");
			String val = request.getParameter("val");
			String jobtitle = request.getParameter("jobtitle"); 
			int res = userProfileDAO.updateUserWiseAccess(userid,val,"userwiseaceess");
			
			int result = userProfileDAO.checkUseridInRoleAccess(userid);
			if(result==0){
				res = userProfileDAO.insertUserTypeInRoleAccess(userid,jobtitle);
			}
			response.setContentType("text/html");
			response.setHeader("Cache-Control", "no-cache");
			response.getWriter().write(""); 	

			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	
	
	public String practitioner(){
		try {
			
			
			Connection connection =Connection_provider.getconnection();
			UserProfileDAO userProfileDAO= new JDBCUserProfileDAO(connection);
			int id=DateTimeUtils.convertToInteger(request.getParameter("id"));
			if(id==0){
				String userid=(String) session.getAttribute("appuserid");
				
				if(!(userid==null)){
					id=userProfileDAO.getDiaryUserId(userid);
				}
			}
			
			UserProfile user=userProfileDAO.getUserprofileDetails(id);
			userProfileForm.setUserDetails(user);;
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "practitioner";
	}
	
	public String display() {
		
		Connection  connection=null;
		try {
			connection = Connection_provider.getconnection();
			String clinicid=loginInfo.getClinicid1();
			UserProfileDAO userProfileDAO = new JDBCUserProfileDAO(connection);
			ArrayList<UserProfile>loginlist=userProfileDAO.getloginlist(clinicid);
			userProfileForm.setLoginlist(loginlist);
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		return "showdashboard";
	}
	
	
	
	public String updateStatus() throws Exception{
		Connection con=null;
		String selectedid=request.getParameter("selectedid");
		try {
			con=Connection_provider.getconnection();
			UserProfileDAO userprofiledao=new JDBCUserProfileDAO(con);
			UserProfile user=new UserProfile();
			user.setId(userProfileForm.getId());
			user.setUserid(userProfileForm.getUserId());
			user.setLoginstatus(userProfileForm.getLoginstatus());
			int result=userprofiledao.getUpdateloginstatus(selectedid);
			response.setContentType("text/html");
			response.setHeader("Cache-Control", "no-cache");
			response.getWriter().write(""+result+"");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			con.close();
		}
		return "showdashboard";
	}
	
	public String checkEmpIdExist() throws Exception{
		
		Connection connection = null;
		String empId = (String)request.getParameter("empId");
		String selectedid = request.getParameter("selectedid");
		
		connection = Connection_provider.getconnection();
		UserProfileDAO userProfileDAO = new JDBCUserProfileDAO(connection);
		
		
		// check if empid with given empid already exist
		boolean empIdExist = userProfileDAO.isEmpidExist(empId,selectedid);

		// if user id already exist then set response 'false'
		if(empIdExist){
			response.getWriter().write("false");
		}else{	// else set response 'true'
			response.getWriter().write("true");
		}
		return null;
		
	}
	
	public String updatepassword() throws Exception{
		
		
		Connection con=null;
		String selectedid=request.getParameter("id");
		try {
			con=Connection_provider.getconnection();
			UserProfileDAO userprofiledao=new JDBCUserProfileDAO(con);
			UserProfile user=new UserProfile();
			user.setResetpassword(userProfileForm.getResetpassword());
			String userid=userprofiledao.getuseridById(selectedid);
			int result=userprofiledao.getUpdatepassword(user,selectedid);
			result=userprofiledao.updatepasswordtoAdmin(user,userid);
			response.setContentType("text/html");
			response.setHeader("Cache-Control", "no-cache");
			response.getWriter().write(""+result+"");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			con.close();
		}
		return null;
		
		
		
	}
	
	
	 public String paymenttransaction() throws Exception{

			Connection con=null;
			try {
				
				  String clientid=request.getParameter("clientid");
				  String apmttype=request.getParameter("apmttype");
				  con=Connection_provider.getconnection();
				  UserProfileDAO userprofiledao=new JDBCUserProfileDAO(con);
				  AccountsDAO accountsDAO=new JDBCAccountsDAO(con);
				  UserProfile user=new UserProfile();
				  double charge=userprofiledao.getAppointmentChargesbyid(apmttype);
					SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
				    Calendar cal = Calendar.getInstance();
				    String todaydate = dateFormat.format(cal.getTime());
				    String endfinancialyeardate="31-03-"+todaydate.split("-")[2];
				    SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		            Date today = sdf.parse(todaydate);
		            Date lastdayoffinyr = sdf.parse(endfinancialyeardate);
				    String yearrangenew="";
				    if(today.after(lastdayoffinyr)){
				    	int year=Integer.parseInt(todaydate.split("-")[2]);
				    	String yearrange=year+"-"+(year+1);
				    	yearrangenew=yearrange;
				    	
				    }else{
				    	int year=Integer.parseInt(todaydate.split("-")[2]);
				    	String yearrange=(year-1)+"-"+year; 
				    	yearrangenew=yearrange;
				    }
				    int maxSeqNo=accountsDAO.getMaxBillSeqNo(yearrangenew,"OB");
				    maxSeqNo=maxSeqNo+1;
				   int ins=accountsDAO.inserLatestSeqNoOfBill(yearrangenew, maxSeqNo, "OB");
				    String yearcomb[]=yearrangenew.split("-");
					//2019-2020 will be 1920
					String yr =yearcomb[0].substring(2,4)+"-"+yearcomb[1].substring(2, 4);
					String seqno="";
					seqno="OB"+yr+"/"+maxSeqNo;
					//saveSeqToBill(billid, seqno);
				    String str=""+charge+"~~"+maxSeqNo;
				    response.setContentType("text/html");
				    response.setHeader("Cache-Control", "no-cache");
				    response.getWriter().write(str.toString());
			} catch (Exception e) {
				e.printStackTrace();
			}finally {
				con.close();
			}
		 return null;
	 }
	 
		
		public String payment() throws Exception {
			
			return "responsehandler";
		}
		

		
		 public String createOrder()throws Exception {
		    
		        Connection con=null;
		        try {
		        	
		        	 String clientid=request.getParameter("clientid");
					  String apmttype=request.getParameter("apmtType");
					  con=Connection_provider.getconnection();
					  UserProfileDAO userprofiledao=new JDBCUserProfileDAO(con);
					  AccountsDAO accountsDAO=new JDBCAccountsDAO(con);
					  UserProfile user=new UserProfile();
					  double charge=userprofiledao.getAppointmentChargesbyid(apmttype);
		            RazorpayClient razorpay = new RazorpayClient("rzp_live_kw4aux9EmjvJIZ", "U4euV9rQ24RZanLU4wA67V1m");

		             JSONObject orderRequest = new JSONObject();
		           
		      
		            orderRequest.put("amount", (int)charge*100); // amount in paise
		            orderRequest.put("currency", "INR");
		            orderRequest.put("payment_capture", 1);
                
		          
		            
		            Order order=razorpay.orders.create(orderRequest);
		            String orderId = order.get("id").toString();
		            int amount = Integer.parseInt(order.get("amount").toString());
		            
		            String currency = order.get("currency").toString();
		            JSONObject responseData = new JSONObject();
		            responseData.put("id",orderId);
		            responseData.put("amount", amount);
		            responseData.put("currency", currency);

		            PrintWriter out = response.getWriter();
		            out.print(responseData.toString());
		            out.flush();
		        } catch (Exception e) {
		            e.printStackTrace();
		        }finally {
					con.close();
				}

		        return null;
		    }
		    
		  
}


