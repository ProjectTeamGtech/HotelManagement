package com.apm.Registration.web.action;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.validation.SkipValidation;

import com.apm.DiaryManagement.eu.bi.DiaryManagementDAO;
import com.apm.DiaryManagement.eu.blogic.jdbc.JDBCDiaryManagentDAO;
import com.apm.DiaryManagement.eu.entity.DiaryManagement;
import com.apm.Dietary.eu.bi.DietaryDetailsDAO;
import com.apm.Dietary.eu.blogic.jdbc.JDBCDietaryDetailsDAO;
import com.apm.Dietary.eu.entity.DietaryDetails;
import com.apm.Master.eu.bi.MasterDAO;
import com.apm.Master.eu.blogic.jdbc.JDBCMasterDAO;
import com.apm.Master.eu.entity.Master;
import com.apm.Pharmacy.eu.bi.PharmacyDAO;
import com.apm.Pharmacy.eu.blogic.jdbc.JDBCPharmacyDAO;
import com.apm.Registration.eu.bi.ClinicDAO;
import com.apm.Registration.eu.bi.UserProfileDAO;
import com.apm.Registration.eu.blogic.jdbc.JDBCClinicDAO;
import com.apm.Registration.eu.blogic.jdbc.JDBCUserProfileDAO;
import com.apm.Registration.eu.entity.Clinic;
import com.apm.Registration.eu.entity.Location;
import com.apm.Registration.eu.entity.UserProfile;
import com.apm.Registration.web.form.ClinicRegistrationForm;
import com.apm.common.eu.blogic.jdbc.Connection_provider;
import com.apm.common.utils.DateTimeUtils;
import com.apm.common.utils.Pagination;
import com.apm.common.web.action.BaseAction;
import com.apm.common.web.common.helper.LoginHelper;
import com.apm.common.web.common.helper.LoginInfo;
import com.apm.common.web.utils.PopulateList;
import com.apm.main.common.constants.Constants;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.Preparable;

public class ClinicRegistrationAction extends BaseAction implements Preparable, ModelDriven<ClinicRegistrationForm> {
	
	ClinicRegistrationForm clinicRegistrationForm = new ClinicRegistrationForm();
	HttpServletRequest request = (HttpServletRequest) ActionContext.getContext().get(ServletActionContext.HTTP_REQUEST);
	HttpServletResponse response = (HttpServletResponse)ActionContext.getContext().get(ServletActionContext.HTTP_RESPONSE);
	HttpSession session = request.getSession(true);
	LoginInfo loginInfo = LoginHelper.getLoginInfo(request);
	Pagination pagination = new Pagination(10,1);
	@SkipValidation
	public String execute() throws Exception {
		
		if(!verifyLogin(request)){
			return "login";
		}
		// TODO Auto-generated method stub
		Connection connection = Connection_provider.getconnection();
		
	    try {
	    	
	    	ClinicDAO clinicDAO = new JDBCClinicDAO(connection);
			
			
			//pagination
			int totalCount = clinicDAO.getCliniclistCount(clinicRegistrationForm.getSearchText());
			pagination.setPreperties(totalCount);
			
			ArrayList<Clinic>clinicList = clinicDAO.getClinicList(pagination,clinicRegistrationForm.getSearchText());
			clinicRegistrationForm.setClinicList(clinicList);
			pagination.setPage_records(clinicList.size());
			clinicRegistrationForm.setTotalRecords(totalCount);
			clinicRegistrationForm.setPagerecords(Integer.toString(pagination.getPage_records()));

			
		} catch (Exception e) {

		   e.printStackTrace();
		}
		finally {
			connection.close();
		}
	 	
		return SUCCESS;
	}
	
	
	public String status() throws SQLException{
		if(!verifyLogin(request)){
			return "login";
		}
		Connection connection = null;
		try{
			
			connection = Connection_provider.getconnection();
			
	
			ClinicDAO clinicDAO = new JDBCClinicDAO(connection);
			
			String selectedid = request.getParameter("selectedid");
			int  activestatus =  Integer.parseInt(request.getParameter("activestatus"));
			
			if(activestatus==0){
				activestatus = 1;
				
			
				Clinic cliniclist = clinicDAO.getCliniclistDetails(Integer.parseInt(selectedid));
				ArrayList<Clinic> clinicLocationlist = clinicDAO.getClinicLocationList(Integer.parseInt(selectedid));
				
				int result = clinicDAO.updateClinicStatus(selectedid,activestatus);
				
				//connection = DriverManager.getConnection(""+Constants.DB_HOST+":3306/"+cliniclist.getUserId()+"","root","mysql");
				connection = DriverManager.getConnection(""+Constants.DB_HOST+":3306/"+cliniclist.getUserId()+"",""+Constants.DB_USER+"",""+Constants.DB_PWD+"");
				
				clinicDAO = new JDBCClinicDAO(connection);
				
				int clinicID = clinicDAO.saveClinic(cliniclist);
				
			
				
				for(Clinic location : clinicLocationlist){
					int res = clinicDAO.saveLocation(clinicID, location);
				}
				
				
			}else{
				activestatus = 0;
				int result = clinicDAO.updateClinicStatus(selectedid,activestatus);
			}
			
			
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			connection.close();
		}
		
		
		
		return "status";
	}
	
	@SkipValidation
	public String save() throws SQLException{
		
		Connection connection = null;
		try{
			connection = Connection_provider.getconnection();
			
			
			
			ClinicDAO clinicDAO = new JDBCClinicDAO(connection);
			Clinic clinic = new Clinic();
			File userImage = clinicRegistrationForm.getUserImage();
			String userImageFileName = clinicRegistrationForm.getUserImageFileName();
			if(userImageFileName!=null){			
				userImageFileName = loginInfo.getUserId()+"_"+userImageFileName;
			}
			String userImageContentType = clinicRegistrationForm.getUserImageContentType();
			
			if(clinicRegistrationForm.getUserImageContentType()!=null){
				String filePath = request.getRealPath("/liveData/clinicLogo/");			
	//			System.out.println("Server path:" + filePath);
				File fileToCreate = new File(filePath, userImageFileName);
				FileUtils.copyFile(userImage, fileToCreate);
				DateTimeUtils.saveFileToGlobelDirectory(filePath, userImageFileName, userImage);
			}
			clinic.setClinicName(clinicRegistrationForm.getClinicName());
			clinic.setClinicOwner(clinicRegistrationForm.getClinicOwner());
			clinic.setEmail(clinicRegistrationForm.getEmail());
			clinic.setMobileNo(clinicRegistrationForm.getMobileNo());
			clinic.setLandLine(clinicRegistrationForm.getLandLine());
			
			clinic.setUserId(clinicRegistrationForm.getUserId());
			clinic.setPassword(clinicRegistrationForm.getPassword());
			clinic.setOwner_qualification(clinicRegistrationForm.getOwner_qualification());
			clinic.setUserImageFileName(userImageFileName);
						
			
			
			for(String str : clinicRegistrationForm.getPkgsubscription()){
				
				if(str.equals(Constants.STANDALONE)){
					clinic.setStandalone(true);
				}else if(str.equals(Constants.HOSTED_DB)){
					clinic.setHostedDB(true);
				}else if(str.equals(Constants.ONLINE_SINGLE_DEVICE)){
					clinic.setOnlineSingleDevice(true);
				}else if(str.equals(Constants.ONLINE_MULTI_DEVICE)){
					clinic.setOnlineMultiDevice(true);
				}
				
				
			}
			
			for(String str : clinicRegistrationForm.getFunSubscription()){
				
				if(str.equals(Constants.DIARY_MANAGEMENT)){
					clinic.setDiaryManagement(true);
				}else if(str.equals(Constants.APPOINTMENT_BOOKING)){
					clinic.setAppointmentBooking(true);
				}else if(str.equals(Constants.BASIC_FINANCE)){
					clinic.setBasicFinance(true);
				}else if(str.equals(Constants.FULL_FINANCE)){
					clinic.setFullFinance(true);
				}else if(str.equals(Constants.MEDICAL_RECORD)){
					clinic.setMedicalRecord(true);
				}else if(str.equals(Constants.CLINIC_RESOURCE_MANAGEMENT)){
					clinic.setClinicResourceMngment(true);
				}else if(str.equals(Constants.CLINIC_PAYROLL_MANAGEMENT)){
					clinic.setClinicPayrollMngment(true);
				}else if(str.equals(Constants.COMMUNICATION)){
					clinic.setCommunication(true);
				}else if(str.equals(Constants.REPORT)){
					clinic.setReport(true);
				}else if(str.equals(Constants.ASSESSMENT_FORM)){
					clinic.setAssessmentForms(true);
				}else if(str.equals(Constants.DESKTOP)){
					clinic.setDesktop(true);
				}else if(str.equals(Constants.MOBILE)){
					clinic.setMobile(true);
				}else if(str.equals(Constants.IOS)){
					clinic.setIOS(true);
				}else if(str.equals(Constants.TABLET)){
					clinic.setTablet(true);
				}
			}
			
			int userid = clinicDAO.saveClinic(clinic);
			
			//int result = clinicDAO.saveAdminAccessPassword(userid, clinic);
			
			for(Location location : clinicRegistrationForm.getLocation()){
				clinic = new Clinic();
				//clinic.setCountry(location.getCountry());
				//clinic.setCity(location.getCity());
				clinic.setAddress(location.getAddress());
				clinic.setPinCode(location.getPinCode());
				clinic.setLocationname(location.getLocationname());
				clinic.setContactNo(location.getContactNo());
				/*String colorWhite = "FFFFFF"; String color = "";
				if(location.getColorName().equals(colorWhite))
				{
					location.setColorName(color);
				}*/
				clinic.setAddressType(location.getAddressType());
				clinic.setEmailId(location.getEmailId());
				clinic.setColorName(location.getColorName());
				clinic.setCheckLocation(location.getCheckLocation());
				
				int result = clinicDAO.saveLocation(userid,clinic);
			}
		clinicRegistrationForm.setMessage("Clinic Registred Successfully!!");	
		addActionMessage("Clinic Registred Successfully!!");
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		finally{
			connection.close();
		}
		return "save";
	}
	
	@SkipValidation
	public String edit() throws SQLException{
		if(!verifyLogin(request)){
			return "login";
		}
		int selectedid = 0;
		Connection connection = null;
		try{
			connection = Connection_provider.getconnection();
			ClinicDAO clinicListDAO = new JDBCClinicDAO(connection);
			PharmacyDAO pharmacyDAO = new JDBCPharmacyDAO(connection);
			ArrayList<Clinic>colorList = new ArrayList<Clinic>();
			//start coding
			
			if(loginInfo.getUserType()==1){
				String userid = request.getParameter("idd");
				clinicRegistrationForm.setSelectedUserid(userid);
				//connection = DriverManager.getConnection(""+Constants.DB_HOST+":3306/"+userid+"","root","mysql");
				connection = DriverManager.getConnection(""+Constants.DB_HOST+":3306/"+userid+"",""+Constants.DB_USER+"",""+Constants.DB_PWD+"");
				
				clinicListDAO = new JDBCClinicDAO(connection);
				selectedid = clinicListDAO.getAdminClinicId(userid);
			}else{
				
				selectedid = Integer.parseInt(request.getParameter("id"));
			}
			
			
			Clinic cliniclist = clinicListDAO.getCliniclistDetails(selectedid);
			colorList = clinicListDAO.getColorList();
			
			
			
			
			clinicRegistrationForm.setClinicName(cliniclist.getClinicName());
			
			clinicRegistrationForm.setUserId(cliniclist.getUserId());
			clinicRegistrationForm.setPassword(cliniclist.getPassword());
			clinicRegistrationForm.setEmail(cliniclist.getEmail());
			/*clinicRegistrationForm.setMobileNo(cliniclist.getMobileNo().substring(1,cliniclist.getMobileNo().length()));*/
			clinicRegistrationForm.setMobileNo(cliniclist.getMobileNo());
			clinicRegistrationForm.setLandLine(cliniclist.getLandLine());
			clinicRegistrationForm.setClinicOwner(cliniclist.getClinicOwner());
			clinicRegistrationForm.setStandalone(cliniclist.isStandalone());
			clinicRegistrationForm.setHostedDB(cliniclist.isHostedDB());
			clinicRegistrationForm.setOnlineSingleDevice(cliniclist.isOnlineSingleDevice());
			clinicRegistrationForm.setOnlineMultiDevice(cliniclist.isOnlineMultiDevice());
			clinicRegistrationForm.setDiaryManagement(cliniclist.isDiaryManagement());
			clinicRegistrationForm.setAppointmentBooking(cliniclist.isAppointmentBooking());
			clinicRegistrationForm.setBasicFinance(cliniclist.isBasicFinance());
			clinicRegistrationForm.setFullFinance(cliniclist.isFullFinance());
			clinicRegistrationForm.setMedicalRecord(cliniclist.isMedicalRecord());
			clinicRegistrationForm.setClinicResourceMngment(cliniclist.isClinicResourceMngment());
			clinicRegistrationForm.setClinicPayrollMngment(cliniclist.isClinicPayrollMngment());
			clinicRegistrationForm.setOwner_qualification(cliniclist.getOwner_qualification());
			clinicRegistrationForm.setWebsiteUrl(cliniclist.getWebsiteUrl());
			clinicRegistrationForm.setUserImageFileName(cliniclist.getUserImageFileName());
			clinicRegistrationForm.setDesktop(cliniclist.isDesktop());
			clinicRegistrationForm.setMobile(cliniclist.isMobile());
			clinicRegistrationForm.setiOS(cliniclist.isIOS());
			clinicRegistrationForm.setTablet(cliniclist.isTablet());
			clinicRegistrationForm.setCommunication(cliniclist.isCommunication());
			clinicRegistrationForm.setReport(cliniclist.isReport());
			clinicRegistrationForm.setAssessmentForms(cliniclist.isAssessmentForms());
			clinicRegistrationForm.setsTime(cliniclist.getStarttime());
			clinicRegistrationForm.setEndTime(cliniclist.getEndtime());
			clinicRegistrationForm.setExcess(cliniclist.getExcess());
			clinicRegistrationForm.setAdvanceTime(cliniclist.getAdvanceTime());
			clinicRegistrationForm.setAdvancerenge(cliniclist.getAdvancerenge());
			clinicRegistrationForm.setIpdregcharge(cliniclist.getIpdregcharge());
			clinicRegistrationForm.setSetupstdcharge(cliniclist.getSetupstdcharge());
			String ctype=cliniclist.getIpdregtype();
			
			clinicRegistrationForm.setIpdregtype(ctype);
			clinicRegistrationForm.setSmscheck(cliniclist.isSmscheck());
			clinicRegistrationForm.setSmsPayment(cliniclist.isSmsPayment());
			clinicRegistrationForm.setSmscheckdoctor(cliniclist.isSmscheckdoctor());
			if(ctype!=null){
				
				  if(ctype.equals("All")){
					    
					    clinicRegistrationForm.setCtp(true);
					    clinicRegistrationForm.setCself(true);
				  }
				  else if(ctype.equals("Third Party")){
					  clinicRegistrationForm.setCtp(true);
				  }
				  else if(ctype.equals("Self")){
					  clinicRegistrationForm.setCself(true);
				  }
			
			}
			clinicRegistrationForm.setDiscount_show(cliniclist.getDiscount_show());
			clinicRegistrationForm.setWithpayment(cliniclist.getWithpayment());
			clinicRegistrationForm.setWithoutpayment(cliniclist.getWithoutpayment());
			clinicRegistrationForm.setInvoice_date(cliniclist.getInvoice_date());
			 clinicRegistrationForm.setInvestigation_show(cliniclist.getInvestigation_show());
			  clinicRegistrationForm.setDiscount_show(cliniclist.getDiscount_show());			
			clinicRegistrationForm.setId(cliniclist.getId());
			ArrayList<Clinic> clinicLocationlist = new ArrayList<Clinic>();
			clinicLocationlist = clinicListDAO.getClinicLocationList(selectedid);
			clinicRegistrationForm.setCliniclocationList(clinicLocationlist);
			clinicRegistrationForm.setBdaysms(cliniclist.isBdaysms());
			clinicRegistrationForm.setImmusms(cliniclist.isImmusms());
			clinicRegistrationForm.setSmsVisitingConslt(cliniclist.isSmsVisitingConslt());
			clinicRegistrationForm.setShow_wardname(cliniclist.isShow_wardname());
			clinicRegistrationForm.setShow_unpost(cliniclist.isShow_unpost());
			clinicRegistrationForm.setSms_on_bedchange(cliniclist.isSms_on_bedchange());
			clinicRegistrationForm.setSms_on_newadm(cliniclist.isSms_on_newadm());
			//  08/01/19 new ipd id access
			
			clinicRegistrationForm.setShow_new_ipd_id(cliniclist.isShow_new_ipd_id());
		    //  14/01/2019 hide letterhead access
			clinicRegistrationForm.setHidelettinvst(cliniclist.isHidelettinvst());
			clinicRegistrationForm.setHidelettemr(cliniclist.isHidelettemr());
			clinicRegistrationForm.setHidelettbillinv(cliniclist.isHidelettbillinv());
			clinicRegistrationForm.setLoginexmsg(cliniclist.getLe_msg());
			clinicRegistrationForm.setAuto_generic_name(cliniclist.isAuto_generic_name());
			
			ArrayList<Master> locationListPharmacy= pharmacyDAO.getAllLocationNew();
			clinicRegistrationForm.setLocationListPharmacy(locationListPharmacy);
			clinicRegistrationForm.setDefault_phar_location(cliniclist.getDefault_phar_location());
			clinicRegistrationForm.setDirect_prisc(cliniclist.isDirect_prisc());
			clinicRegistrationForm.setInvest_savenprint(cliniclist.isInvest_savenprint());
			clinicRegistrationForm.setPrisc_savenprint(cliniclist.isPrisc_savenprint());
			clinicRegistrationForm.setOpdfollowup(cliniclist.getOpdfollowup());
			clinicRegistrationForm.setOpd_payamnt_sms(cliniclist.isOpd_payamnt_sms());
			clinicRegistrationForm.setIpd_payment_sms(cliniclist.isIpd_payamnt_sms());
			clinicRegistrationForm.setSms_cancel_appointment(cliniclist.isSms_cancel_appointment());
			int locationcount = 0;
			locationcount = clinicLocationlist.size();
			
			session.setAttribute("colorList", colorList);
			session.setAttribute("clinicLocationlist", clinicLocationlist);
			session.setAttribute("locationcount", locationcount);
			Clinic clinic=clinicListDAO.getLocationDetails(1);
			clinicRegistrationForm.setAddress(clinic.getAddress());
			//userProfileForm.setDiciplineName("Dummy three");
			
		}catch (Exception e) {
			e.printStackTrace();
		}finally{
			
			connection.close();
		}
		if(loginInfo.getUserType()==1){
			return "editAdminClinic";
		}
		return "edit";
	}
	@SkipValidation
	public String updatesave() throws SQLException
	{
		if(!verifyLogin(request)){
			return "login";
		}
		
		int selectedid = 	clinicRegistrationForm.getId();
		Connection connection = null;
		
		
			HttpServletRequest request = (HttpServletRequest)ActionContext.getContext().get(org.apache.struts2.ServletActionContext.HTTP_REQUEST);
			
			try{
				connection = Connection_provider.getconnection();
				
				
				if(loginInfo.getUserType()==1){
					String userid = clinicRegistrationForm.getSelectedUserid();
					//connection = DriverManager.getConnection(""+Constants.DB_HOST+":3306/"+userid+"","root","mysql");
					connection = DriverManager.getConnection(""+Constants.DB_HOST+":3306/"+userid+"",""+Constants.DB_USER+"",""+Constants.DB_PWD+"");
				}
				
				ClinicDAO clinicDAO = new JDBCClinicDAO(connection);
				MasterDAO masterDAO= new JDBCMasterDAO(connection);
				
				Clinic clinic = new Clinic();
				
				String userImageFileName = clinicRegistrationForm.getUserImageFileName();
				if(clinicRegistrationForm.getUserImageContentType()!=null){			
					userImageFileName = loginInfo.getUserId()+"_"+userImageFileName;
				}
				if(clinicRegistrationForm.getUserImageContentType()!=null){
					String filePath = request.getRealPath("/liveData/clinicLogo/");	
				       
//					System.out.println("Server path:" + filePath);
					File fileToCreate = new File(filePath, userImageFileName);
					FileUtils.copyFile(clinicRegistrationForm.getUserImage(), fileToCreate);
					clinic.setUserImageFileName(userImageFileName);
					
					
					{
						/*//saving same file to globel repository
						String source_path=filePath;
						source_path=source_path.replace("HISTEST", "HISDATA");
						source_path=source_path.replace("HISLIVE", "HISDATA");
						File destFolder = new File(source_path);
						if (!destFolder.exists()) {
							destFolder.mkdirs();
						}
						File fileToCreateGlobel = new File(source_path, userImageFileName);
						FileUtils.copyFile(clinicRegistrationForm.getUserImage(), fileToCreateGlobel);*/
						DateTimeUtils.saveFileToGlobelDirectory(filePath, userImageFileName, clinicRegistrationForm.getUserImage());
					}
					
					}
					
 				clinic.setClinicName(clinicRegistrationForm.getClinicName());
				clinic.setClinicOwner(clinicRegistrationForm.getClinicOwner());
				clinic.setEmail(clinicRegistrationForm.getEmail());
				clinic.setMobileNo(clinicRegistrationForm.getMobileNo());
				clinic.setLandLine(clinicRegistrationForm.getLandLine());
				
				clinic.setUserId(clinicRegistrationForm.getUserId());
				clinic.setPassword(clinicRegistrationForm.getPassword());
				clinic.setWebsiteUrl(clinicRegistrationForm.getWebsiteUrl());
				clinic.setOwner_qualification(clinicRegistrationForm.getOwner_qualification());
				clinic.setUserImageFileName(userImageFileName);
				clinic.setStarttime(clinicRegistrationForm.getsTime());
				clinic.setEndtime(clinicRegistrationForm.getEndTime());
				clinic.setExcess(clinicRegistrationForm.getExcess());
				clinic.setAdvanceTime(clinicRegistrationForm.getAdvanceTime());
				clinic.setAdvancerenge(clinicRegistrationForm.getAdvancerenge());
				clinic.setIpdregcharge(clinicRegistrationForm.getIpdregcharge());
				clinic.setSmscheck(clinicRegistrationForm.isSmscheck());
				clinic.setSmsPayment(clinicRegistrationForm.isSmsPayment());
				clinic.setSetupstdcharge(clinicRegistrationForm.getSetupstdcharge());
				String withpayment = clinicRegistrationForm.getWithpayment();
				String withoutpayment = clinicRegistrationForm.getWithoutpayment();
				String invoice_date = clinicRegistrationForm.getInvoice_date();
				 
				String discount_show =clinicRegistrationForm.getDiscount_show();
				String investigation_show= clinicRegistrationForm.getInvestigation_show();
				
				//@ruchi for is check for ipd admition,discharge
				clinic.setSmscheckdoctor(clinicRegistrationForm.isSmscheckdoctor());
				clinic.setSmscheckrelativebedchange(clinicRegistrationForm.isSmscheckrelativebedchange());
//				clinic.setSmsdoctorBedchange(clinicRegistrationForm.isSmsdoctorBedchange());
				clinic.setSmsrelativeDischarge(clinicRegistrationForm.isSmsrelativeDischarge());
				clinic.setSmspatientApproved(clinicRegistrationForm.isSmspatientApproved());
				clinic.setSmspatientCompleted(clinicRegistrationForm.isSmspatientCompleted());
				clinic.setBdaysms(clinicRegistrationForm.isBdaysms());
				clinic.setImmusms(clinicRegistrationForm.isImmusms());
				clinic.setSmsVisitingConslt(clinicRegistrationForm.isSmsVisitingConslt());
				clinic.setShow_wardname(clinicRegistrationForm.isShow_wardname());
				clinic.setShow_unpost(clinicRegistrationForm.isShow_unpost());
				clinic.setSms_on_bedchange(clinicRegistrationForm.isSms_on_bedchange());
				clinic.setSms_on_newadm(clinicRegistrationForm.isSms_on_newadm());
				clinic.setShow_new_ipd_id(clinicRegistrationForm.isShow_new_ipd_id());
				//  14/01/2019
				
				clinic.setHidelettinvst(clinicRegistrationForm.isHidelettinvst());
				clinic.setHidelettemr(clinicRegistrationForm.isHidelettemr());
				clinic.setHidelettbillinv(clinicRegistrationForm.isHidelettbillinv());
				clinic.setLe_msg(clinicRegistrationForm.getLoginexmsg());
				
				clinic.setAuto_generic_name(clinicRegistrationForm.isAuto_generic_name());
				clinic.setDefault_phar_location(clinicRegistrationForm.getDefault_phar_location());
				clinic.setDirect_prisc(clinicRegistrationForm.isDirect_prisc());
				clinic.setPrisc_savenprint(clinicRegistrationForm.isPrisc_savenprint());
				clinic.setInvest_savenprint(clinicRegistrationForm.isInvest_savenprint());
				clinic.setAddress(clinicRegistrationForm.getAddress());
				if (withpayment.equals("true")) {
					withpayment = "1";
				} else {
					withpayment = "0";
				}
				
				if (withoutpayment.equals("true")) {
					withoutpayment = "1";
				} else {
					withoutpayment = "0";
				}
				
			if (invoice_date.equals("true")) {
					invoice_date = "1";
				} else {
					invoice_date = "0";
				}
				
				if(discount_show.equals("true")){
					discount_show="1";
				}else{
					discount_show="0";
				}  
				if(investigation_show.equals("true")){
				     investigation_show="1";
			    }else{
			     investigation_show="0";
			    }
				 
				 clinic.setInvestigation_show(investigation_show);
				clinic.setDiscount_show(discount_show);
				clinic.setInvoice_date(invoice_date);
				clinic.setWithpayment(withpayment);
				clinic.setWithoutpayment(withoutpayment);
				
				/*String specializations= request.getParameter("specializations");
				String fields= request.getParameter("fields");
*/				
				/*int d=clinicDAO.truncateTempIpdFormData();
				for(String speci: specializations.split(",")) {
				
					 if(speci.equals("0"))
					 {
						  continue;
					 }
					 
					 for(String f: fields.split(",")) {
						 
						 if(f.equals("0")) {
							 continue;
						 }
						 String name= masterDAO.getIpdFormFieldName(f);
						 d=clinicDAO.saveIpdFormData(speci,name);
					 }
					
				}*/
				
				int result2 = clinicDAO.saveLocation1(selectedid,clinic);
				String ctype="";
				boolean ctp=clinicRegistrationForm.isCtp();
				boolean cself=clinicRegistrationForm.isCself();
				
				if(ctp && cself){
					 ctype="All";
				}
				else if(ctp){
					ctype="Third Party";
				}
				else if(cself){
					ctype="Self";
				}
				else {
					ctype="None";
				}
				clinic.setIpdregtype(ctype);
				clinic.setOpdfollowup(clinicRegistrationForm.getOpdfollowup());
				
				
				
				if(loginInfo.getUserType()==1){
					
					for(String str : clinicRegistrationForm.getPkgsubscription()){
					
					if(str.equals(Constants.STANDALONE)){
						clinic.setStandalone(true);
					}else if(str.equals(Constants.HOSTED_DB)){
						clinic.setHostedDB(true);
					}else if(str.equals(Constants.ONLINE_SINGLE_DEVICE)){
						clinic.setOnlineSingleDevice(true);
					}else if(str.equals(Constants.ONLINE_MULTI_DEVICE)){
						clinic.setOnlineMultiDevice(true);
					}
					
					
				}
				
				for(String str : clinicRegistrationForm.getFunSubscription()){
					
					if(str.equals(Constants.DIARY_MANAGEMENT)){
						clinic.setDiaryManagement(true);
					}else if(str.equals(Constants.APPOINTMENT_BOOKING)){
						clinic.setAppointmentBooking(true);
					}else if(str.equals(Constants.BASIC_FINANCE)){
						clinic.setBasicFinance(true);
					}else if(str.equals(Constants.FULL_FINANCE)){
						clinic.setFullFinance(true);
					}else if(str.equals(Constants.MEDICAL_RECORD)){
						clinic.setMedicalRecord(true);
					}else if(str.equals(Constants.CLINIC_RESOURCE_MANAGEMENT)){
						clinic.setClinicResourceMngment(true);
					}else if(str.equals(Constants.CLINIC_PAYROLL_MANAGEMENT)){
						clinic.setClinicPayrollMngment(true);
					}else if(str.equals(Constants.COMMUNICATION)){
						clinic.setCommunication(true);
					}else if(str.equals(Constants.REPORT)){
						clinic.setReport(true);
					}else if(str.equals(Constants.ASSESSMENT_FORM)){
						clinic.setAssessmentForms(true);
					}else if(str.equals(Constants.DESKTOP)){
						clinic.setDesktop(true);
					}else if(str.equals(Constants.MOBILE)){
						clinic.setMobile(true);
					}else if(str.equals(Constants.IOS)){
						clinic.setIOS(true);
					}else if(str.equals(Constants.TABLET)){
						clinic.setTablet(true);
					}
				}
				
				int result1 = clinicDAO.updateClinicByAdmin(clinic,selectedid);
			}
				
				int result = clinicDAO.updateClinic(clinic,selectedid);
				
				if(loginInfo.getClinicid1().equals("manasclinic")) {
					result=clinicDAO.updateAddressForManasclinic(clinic,selectedid);
				}
				
				UserProfileDAO userProfileDAO = new JDBCUserProfileDAO(connection);
				String userid = userProfileDAO.getAdminDbUserID(selectedid);
				clinic.setUserId(userid);
				clinic.setMobileNo(clinicRegistrationForm.getMobileNo());
				
				
				//set clinic start and end time
				
				DiaryManagementDAO diaryManagementDAO = new JDBCDiaryManagentDAO(connection);
				Clinic	clinic1 = diaryManagementDAO.getClinicStartAndEndTime(loginInfo.getClinicid());
				String temp[] = clinic1.getStarttime().split(":");
				loginInfo.setClinicStartTime(Integer.parseInt(temp[0]));
				String temp1[] = clinic1.getEndtime().split(":");
				loginInfo.setClinicEndTime(Integer.parseInt(temp1[0]));
				
				//update admin database
				connection = DriverManager.getConnection(""+Constants.DB_HOST+":3306/admin",""+Constants.DB_USER+"",""+Constants.DB_PWD+"");
				clinicDAO = new JDBCClinicDAO(connection);
				int updateadmin = clinicDAO.updateAdminEmailAndMobile(clinic);
				
				
				
				
				
				
				
				//int result1 = clinicDAO.deleteClinicLocation(selectedid);
				/*for(Location location : clinicRegistrationForm.getLocation()){
					clinic = new Clinic();
					clinic.setCountry(location.getCountry());
					clinic.setCity(location.getCity());
					clinic.setAddress(location.getAddress());
					clinic.setPinCode(location.getPinCode());
					clinic.setLocationname(location.getLocationname());
					clinic.setContactNo(location.getContactNo());
					String colorWhite = "FFFFFF"; String color = "";
					if(location.getColorName().equals(colorWhite))
					{
						location.setColorName(color);
					}
					clinic.setColorName(location.getColorName());
					
					
					int result2 = clinicDAO.saveLocation(selectedid,clinic);
				}*/
				clinicRegistrationForm.setMessage("Clinic details Modified Successfully!!");	
				addActionMessage("Clinic details Modified Successfully!!");
				int totalCount = clinicDAO.getCliniclistCount(clinicRegistrationForm.getSearchText());
				pagination.setPreperties(totalCount);
				
				ArrayList<Clinic>clinicList = clinicDAO.getClinicList(pagination,clinicRegistrationForm.getSearchText());
				clinicRegistrationForm.setClinicList(clinicList);
				pagination.setPage_records(clinicList.size());
				clinicRegistrationForm.setTotalRecords(totalCount);
				clinicRegistrationForm.setPagerecords(Integer.toString(pagination.getPage_records()));
				
				
		
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		finally{
			connection.close();
		}
			if(loginInfo.getUserType()==1){
				return "admineditsave";
			}
			else{
				return profile();
			}
		
	}
	@SkipValidation
	public String delete() throws SQLException{
		if(!verifyLogin(request)){
			return "login";
		}
		Connection connection = null;
		try{
			connection = Connection_provider.getconnection();
			//UserProfileDAO userProfileDAO = new JDBCUserProfileDAO(connection);
			ClinicDAO clinicListDAO = new JDBCClinicDAO(connection);
			
			//start coding
			
			HttpServletRequest request = (HttpServletRequest)ActionContext.getContext().get(org.apache.struts2.ServletActionContext.HTTP_REQUEST);
			int selectedid= Integer.parseInt(request.getParameter("id"));
			
			
			int result = clinicListDAO.deleteClinicList(selectedid);
			int result1 = clinicListDAO.deleteClinicLocation(selectedid);
			clinicRegistrationForm.setMessage("Clinic deleted Successfully!!");	
			addActionMessage("Clinic deleted Successfully!!");
			
			int totalCount = clinicListDAO.getCliniclistCount(clinicRegistrationForm.getSearchText());
			pagination.setPreperties(totalCount);
			
			ArrayList<Clinic>clinicList = clinicListDAO.getClinicList(pagination,clinicRegistrationForm.getSearchText());
			clinicRegistrationForm.setClinicList(clinicList);
			pagination.setPage_records(clinicList.size());
			clinicRegistrationForm.setTotalRecords(totalCount);
			clinicRegistrationForm.setPagerecords(Integer.toString(pagination.getPage_records()));
			
		}catch (Exception e) {
			e.printStackTrace();
		}finally{
			
			connection.close();
		}
		
		return SUCCESS;
	}
	
	@SkipValidation
	public String checkColorExist() throws SQLException, IOException{
		//if(!verifyLogin(request)){
		//	return "login";
		//}
		Connection connection = null;
		String diarycolor = (String)request.getParameter("color");
		diarycolor = "#"+diarycolor;
		connection = Connection_provider.getconnection();
		UserProfileDAO userProfileDAO = new JDBCUserProfileDAO(connection);
		// check if user with given user id already exist
		boolean userIdExist = userProfileDAO.isColorExist(diarycolor);
		
		// if user id already exist then set response 'false'
		if(userIdExist){
			response.getWriter().write("false");
		}else{	// else set response 'true'
			response.getWriter().write("true");
		}
		return null;
	}
	
	
	@SkipValidation
	public String checkUserIdExist() throws SQLException, IOException{
		Connection connection = null;
		String userId = (String)request.getParameter("userId");
		String selectedid = request.getParameter("selectedid");
		
		connection = Connection_provider.getconnection();
		UserProfileDAO userProfileDAO = new JDBCUserProfileDAO(connection);
		
		String existUserid = "";
		
		if(!selectedid.equals("0")){
			existUserid = userProfileDAO.getexistingUserid(Integer.parseInt(selectedid));
		}
		
		connection=DriverManager.getConnection(""+Constants.DB_HOST+":3306/admin",""+Constants.DB_USER+"",""+Constants.DB_PWD+"");
		//connection = DriverManager.getConnection(""+Constants.DB_HOST+":3306/admin","root","mysql");

		
		
		
		ClinicDAO clinicDAO = new JDBCClinicDAO(connection);
		// check if user with given user id already exist
		boolean userIdExist = clinicDAO.isUserExist(userId,selectedid,existUserid);

		// if user id already exist then set response 'false'
		if(userIdExist){
			response.getWriter().write("false");
		}else{	// else set response 'true'
			response.getWriter().write("true");
		}
		return null;
	}
	
	
	
	@SkipValidation
	public String checkEmaildExist() throws SQLException{
		
		String emailid = request.getParameter("emailid");
		
		Connection connection = null;
		try{
			connection = Connection_provider.getconnection();
			
			
			
			ClinicDAO clinicDAO = new JDBCClinicDAO(connection);
			
			boolean isExist = clinicDAO.checkEmailidExist(emailid);
			
			if(isExist){
				response.getWriter().write("false");
			}else{	// else set response 'true'
				response.getWriter().write("true");
			}
			
			
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			connection.close();
		}
		
		return null;
	}
	
	public void validate() {
    	
		try{
		
		for(Location location : clinicRegistrationForm.getLocation()){
			Clinic clinic = new Clinic();
			clinic.setCountry(location.getCountry());
			clinic.setCity(location.getCity());
			clinic.setAddress(location.getAddress());
			clinic.setPinCode(location.getPinCode());
			clinic.setLocationname(location.getLocationname());
			clinic.setColorName(location.getColorName());
			String locationvalue = location.getLocationname();
			 if (locationvalue.equals("")) {
		            addActionError("Enter Location");
			 }
			 if(location.getCountry().equals("")){
				 addActionError("Enter Country");
			 }
			 if(location.getCity().equals("")){
				 addActionError("Enter City");
			 }
			 if(location.getAddress().equals("")){
				 addActionError("Enter Address");
			 }
			 
			 else{	// else set response 'true'
					response.getWriter().write("true");
				}
			
		}
		
		Connection connection = Connection_provider.getconnection();
		LoginInfo loginInfo = LoginHelper.getLoginInfo(request);
		UserProfileDAO userProfileDAO = new JDBCUserProfileDAO(connection);
		
		for(Location location : clinicRegistrationForm.getLocation()){
		
		String diarycolor = location.getColorName();
		
		
		boolean colorExist = userProfileDAO.isColorExist(diarycolor);
		
		
		if(colorExist){
			 addActionError("Try another color");
		}else{	// else set response 'true'
			response.getWriter().write("true");
		}
		}
		}
		catch (Exception e) {
			// TODO: handle exception
		}
		
		 
   }
	@SkipValidation
	public String input() throws Exception {
		
		return INPUT;
	}
	
	@SkipValidation
	public String profile() throws SQLException{
		if(!verifyLogin(request)){
			return "login";
		}
		int selectedid = loginInfo.getId();
		Connection connection = null;
		try{
			connection = Connection_provider.getconnection();
			ClinicDAO clinicListDAO = new JDBCClinicDAO(connection);
			PharmacyDAO pharmacyDAO = new JDBCPharmacyDAO(connection);
			//int id = loginInfo.getClinicid();
			
			//start coding
			Clinic cliniclist = clinicListDAO.getCliniclistDetails(selectedid);
			
			clinicRegistrationForm.setClinicName(cliniclist.getClinicName());
			clinicRegistrationForm.setUserId(cliniclist.getUserId());
			clinicRegistrationForm.setPassword(cliniclist.getPassword());
			clinicRegistrationForm.setEmail(cliniclist.getEmail());
			clinicRegistrationForm.setMobileNo(cliniclist.getMobileNo());
			clinicRegistrationForm.setLandLine(cliniclist.getLandLine());
			clinicRegistrationForm.setClinicOwner(cliniclist.getClinicOwner());
			clinicRegistrationForm.setStandalone(cliniclist.isStandalone());
			clinicRegistrationForm.setHostedDB(cliniclist.isHostedDB());
			clinicRegistrationForm.setOnlineSingleDevice(cliniclist.isOnlineSingleDevice());
			clinicRegistrationForm.setOnlineMultiDevice(cliniclist.isOnlineMultiDevice());
			clinicRegistrationForm.setDiaryManagement(cliniclist.isDiaryManagement());
			clinicRegistrationForm.setAppointmentBooking(cliniclist.isAppointmentBooking());
			clinicRegistrationForm.setBasicFinance(cliniclist.isBasicFinance());
			clinicRegistrationForm.setFullFinance(cliniclist.isFullFinance());
			clinicRegistrationForm.setMedicalRecord(cliniclist.isMedicalRecord());
			clinicRegistrationForm.setClinicResourceMngment(cliniclist.isClinicResourceMngment());
			clinicRegistrationForm.setClinicPayrollMngment(cliniclist.isClinicPayrollMngment());
			clinicRegistrationForm.setId(cliniclist.getId());
			clinicRegistrationForm.setOwner_qualification(cliniclist.getOwner_qualification());
			clinicRegistrationForm.setWebsiteUrl(cliniclist.getWebsiteUrl());
			clinicRegistrationForm.setUserImageFileName(cliniclist.getUserImageFileName());
			
			clinicRegistrationForm.setDesktop(cliniclist.isDesktop());
			clinicRegistrationForm.setMobile(cliniclist.isMobile());
			clinicRegistrationForm.setiOS(cliniclist.isIOS());
			clinicRegistrationForm.setTablet(cliniclist.isTablet());
			clinicRegistrationForm.setCommunication(cliniclist.isCommunication());
			clinicRegistrationForm.setReport(cliniclist.isReport());
			clinicRegistrationForm.setAssessmentForms(cliniclist.isAssessmentForms());
			clinicRegistrationForm.setsTime(cliniclist.getStarttime());
			clinicRegistrationForm.setEndTime(cliniclist.getEndtime());
			clinicRegistrationForm.setExcess(cliniclist.getExcess());
			clinicRegistrationForm.setAdvanceTime(cliniclist.getAdvanceTime());
			clinicRegistrationForm.setIpdregcharge(cliniclist.getIpdregcharge());
			clinicRegistrationForm.setIpdregtype(cliniclist.getIpdregtype());
			clinicRegistrationForm.setSmscount(cliniclist.getSmscount());
			clinicRegistrationForm.setSmscheck(cliniclist.isSmscheck());
			clinicRegistrationForm.setSmsPayment(cliniclist.isSmsPayment());
			String withpayment = cliniclist.getWithpayment();
			clinicRegistrationForm.setWithpayment(cliniclist.getWithpayment());
			clinicRegistrationForm.setWithoutpayment(cliniclist.getWithoutpayment());
			clinicRegistrationForm.setInvoice_date(cliniclist.getInvoice_date());
			
			clinicRegistrationForm.setDiscount_show(cliniclist.getDiscount_show());
			clinicRegistrationForm.setInvestigation_show(cliniclist.getInvestigation_show());
			clinicRegistrationForm.setImmusms(cliniclist.isImmusms());
			clinicRegistrationForm.setBdaysms(cliniclist.isBdaysms());
			clinicRegistrationForm.setSmsVisitingConslt(cliniclist.isSmsVisitingConslt());
			if(cliniclist.getSetupstdcharge().equals("0")){
				clinicRegistrationForm.setSetupstdcharge("On Admission Date");
			} else {
				clinicRegistrationForm.setSetupstdcharge("On Next Date Of Admission");
			}
			clinicRegistrationForm.setShow_wardname(cliniclist.isShow_wardname());
			clinicRegistrationForm.setShow_unpost(cliniclist.isShow_unpost());
			clinicRegistrationForm.setSms_on_bedchange(cliniclist.isSms_on_bedchange());
			clinicRegistrationForm.setSms_on_newadm(cliniclist.isSms_on_newadm());
	
			clinicRegistrationForm.setShow_new_ipd_id(cliniclist.isShow_new_ipd_id());
			clinicRegistrationForm.setHidelettinvst(cliniclist.isHidelettinvst());
			clinicRegistrationForm.setHidelettemr(cliniclist.isHidelettemr());
			clinicRegistrationForm.setHidelettbillinv(cliniclist.isHidelettbillinv());
			clinicRegistrationForm.setLoginexmsg(cliniclist.getLe_msg());
			clinicRegistrationForm.setSmscheckdoctor(cliniclist.isSmscheckdoctor());
			/*ArrayList<Clinic> clinicLocationlist = new ArrayList<Clinic>();
			clinicLocationlist = clinicListDAO.getClinicLocationList(selectedid);
			clinicRegistrationForm.setCliniclocationList(clinicLocationlist);
			
			int locationcount = 0;
			locationcount = clinicLocationlist.size();
			
			session.setAttribute("clinicLocationlist", clinicLocationlist);
			session.setAttribute("locationcount", locationcount);*/
			clinicRegistrationForm.setAuto_generic_name(cliniclist.isAuto_generic_name());
			/*ArrayList<Master> locationListPharmacy= pharmacyDAO.getAllLocation();
			clinicRegistrationForm.setLocationListPharmacy(locationListPharmacy);*/
			String default_phar_location_name = pharmacyDAO.getLocationName(cliniclist.getDefault_phar_location());
			clinicRegistrationForm.setDefault_phar_location_name(default_phar_location_name);
			clinicRegistrationForm.setDirect_prisc(cliniclist.isDirect_prisc());
			clinicRegistrationForm.setInvest_savenprint(cliniclist.isInvest_savenprint());
			clinicRegistrationForm.setPrisc_savenprint(cliniclist.isPrisc_savenprint());
			Clinic clinic=clinicListDAO.getLocationDetails(1);
			if(loginInfo.getClinicid1().equals("manasclinic")) {
				clinicRegistrationForm.setAddress(cliniclist.getAddress());
			}else {
				clinicRegistrationForm.setAddress(clinic.getAddress());
			}
			//clinicRegistrationForm.setAddress(clinic.getAddress());
			clinicRegistrationForm.setOpdfollowup(cliniclist.getOpdfollowup());
		}catch (Exception e) {
			e.printStackTrace();
		}finally{
			
			connection.close();
		}
		
		return "viewProfileOfClinic";
	}
	
	@SkipValidation
	public String location() throws SQLException{
		int selectedid = loginInfo.getId();
		Connection connection = null;
		try{
			connection = Connection_provider.getconnection();
			ClinicDAO clinicListDAO = new JDBCClinicDAO(connection);
			
			ArrayList<Clinic> clinicLocationlist = new ArrayList<Clinic>();
			 clinicLocationlist = clinicListDAO.getClinicLocationList(selectedid);
			clinicRegistrationForm.setCliniclocationList(clinicLocationlist);
			
			
			
		}catch (Exception e) {
			e.printStackTrace();
		}finally{
			
			connection.close();
		}
		return "locationList";
	}
	@SkipValidation
	public String viewLocation() throws Exception{
		int selectedid = Integer.parseInt(request.getParameter("selectedid"));
		Connection connection = null;
		try{
			connection = Connection_provider.getconnection();
			ClinicDAO clinicListDAO = new JDBCClinicDAO(connection);
			Clinic clinic = new Clinic();
			clinic = clinicListDAO.getLocationDetails(selectedid);
			clinicRegistrationForm.setLocationid(clinic.getLocationid());
			clinicRegistrationForm.setLocationname(clinic.getLocationname());
			clinicRegistrationForm.setPinCode(clinic.getPinCode());
			clinicRegistrationForm.setAddress(clinic.getAddress());
			clinicRegistrationForm.setContactNo(clinic.getContactNo());
			clinicRegistrationForm.setCountry(clinic.getCountry());
			clinicRegistrationForm.setCity(clinic.getCity());
			clinicRegistrationForm.setLoc_direction(clinic.getLoc_direction());
			clinicRegistrationForm.setUserImageFileName(clinic.getUserImageFileName());
			clinicRegistrationForm.setCheckLocation(clinic.getCheckLocation());
			
			session.setAttribute("locdirection", clinicRegistrationForm.getLoc_direction());
			
		}
		catch(Exception e){
			e.printStackTrace();
		}finally{
			
			connection.close();
		}
		return "viewLocation";
	}
	@SkipValidation
	public String editLocation() throws Exception{
		if(!verifyLogin(request)){
			return "login";
		}
		int selectedid = Integer.parseInt(request.getParameter("selectedid"));
		Connection connection = null;
		try{
			connection = Connection_provider.getconnection();
			ClinicDAO clinicListDAO = new JDBCClinicDAO(connection);
			Clinic clinic = new Clinic();
			clinic = clinicListDAO.getLocationDetails(selectedid);
			clinicRegistrationForm.setLocationid(clinic.getLocationid());
			clinicRegistrationForm.setLocationname(clinic.getLocationname());
			clinicRegistrationForm.setPinCode(clinic.getPinCode());
			clinicRegistrationForm.setAddress(clinic.getAddress());
			clinicRegistrationForm.setContactNo(clinic.getContactNo());
			//clinicRegistrationForm.setCountry(clinic.getCountry());
			//clinicRegistrationForm.setCity(clinic.getCity());
			clinicRegistrationForm.setEmailId(clinic.getEmailId());
			clinicRegistrationForm.setColorName(clinic.getColorName());
			clinicRegistrationForm.setLoc_direction(clinic.getLoc_direction());
			clinicRegistrationForm.setUserImageFileName(clinic.getUserImageFileName());
			clinicRegistrationForm.setCheckLocation(clinic.getCheckLocation());
			clinicRegistrationForm.setAddressType(clinic.getAddressType());
			
			clinicRegistrationForm.setCity(clinic.getCity());
			//clinicRegistrationForm.setRefLocation(clinic.getRefLocation());
			
			
		}
		catch(Exception e){
			e.printStackTrace();
		}finally{
			
			connection.close();
		}
		return "editClinicLocationPage";
	}
	
	@SkipValidation
	public String updateSaveLocation() throws SQLException{
		if(!verifyLogin(request)){
			return "login";
		}
		int selectedid = 	clinicRegistrationForm.getLocationid();
		Connection connection = null;
		
		
			
			try{
				connection = Connection_provider.getconnection();
				ClinicDAO clinicDAO = new JDBCClinicDAO(connection);
				Clinic clinic = new Clinic();
				
				String userImageFileName = clinicRegistrationForm.getUserImageFileName();
				if(userImageFileName == null){
					userImageFileName = "";
				}
				if(!userImageFileName.equals("")){
					String temp[] = userImageFileName.split("_");
					if(temp[0].equalsIgnoreCase(loginInfo.getUserId())){
						
					}
					else{
						userImageFileName = loginInfo.getUserId()+"_"+userImageFileName;
					}
				}
				
				String hospname = clinicRegistrationForm.getHospname();
				String address = clinicRegistrationForm.getAddress();
				
				clinic.setLocationname(clinicRegistrationForm.getLocationname());
				//clinic.setLocationname(clinicRegistrationForm.getLocationname());
				clinic.setPinCode(clinicRegistrationForm.getPinCode());
				clinic.setAddress(address);
				clinic.setClinicName(hospname);
				clinic.setContactNo(clinicRegistrationForm.getContactNo());
				clinic.setCountry(clinicRegistrationForm.getCountry());
				clinic.setCity(clinicRegistrationForm.getCity());
				clinic.setColorName(clinicRegistrationForm.getColorName());
				clinic.setLoc_direction(clinicRegistrationForm.getLoc_direction());
				clinic.setUserImageFileName(userImageFileName);
				clinic.setCheckLocation(clinicRegistrationForm.getCheckLocation());
				clinic.setAddressType(clinicRegistrationForm.getAddressType());
				clinic.setEmailId(clinicRegistrationForm.getEmailId());
				
				
				//update hosp name
				//int upd = clinicDAO.updateHospName(hospname);
				
				clinic.setClinicName(hospname);
				int result = clinicDAO.updateClinicLocation(clinic, selectedid);
				//Upload
				String userImagecontent = clinicRegistrationForm.getUserImageContentType();
				if(userImagecontent == null){
					userImagecontent = "";
				}
				if(!userImagecontent.equals("")){
				String filePath = request.getRealPath("/liveData/locationMap/");
			       
				//System.out.println("Server path:" + filePath);
				File fileToCreate = new File(filePath, clinicRegistrationForm.getUserImageFileName());
				FileUtils.copyFile(clinicRegistrationForm.getUserImage(), fileToCreate);
				clinic.setUserImageFileName(clinicRegistrationForm.getUserImageFileName());
				}
				else{
					
				}
				clinicRegistrationForm.setMessage("Clinic Updated Successfully!!");	
				addActionMessage("Clinic Updated Successfully!!");
				
			}
			catch(Exception e){
				e.printStackTrace();
			}
		
			return location();
	}
	@SkipValidation
	public String deleteLocation() throws Exception{
		if(!verifyLogin(request)){
			return "login";
		}
		int selectedid = Integer.parseInt(request.getParameter("selectedid"));
		Connection connection = null;
		
		
			
			try{
				connection = Connection_provider.getconnection();
				ClinicDAO clinicDAO = new JDBCClinicDAO(connection);
				Clinic clinic = new Clinic();
				
				
				
				int result = clinicDAO.deleteClinicLocation(clinic, selectedid);
				clinicRegistrationForm.setMessage("Clinic Deleted Successfully!!");	
				addActionMessage("Clinic Deleted Successfully!!");
			}
			catch(Exception e){
				e.printStackTrace();
			}finally{
				
				connection.close();
			}
		
			return location();
	}
	
	public String checkMailSend() throws Exception{
		
		if(!verifyLogin(request)){
			return "login";
		}
		String checkMailSend = request.getParameter("checkMailSend");
		int id = loginInfo.getId();
		Connection connection = null;
		try{
			connection = Connection_provider.getconnection();
			ClinicDAO clinicDAO = new JDBCClinicDAO(connection);
			
			int changeMailSendValue = clinicDAO.changeMailSend(checkMailSend,id);
			
			response.setContentType("text/html");
			response.setHeader("Cache-Control", "no-cache");
			response.getWriter().write(changeMailSendValue); 
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			
			connection.close();
		}
		
		
		return null;
	}
	
	public String checkEmailSetting() throws Exception{
		if(!verifyLogin(request)){
			return "login";
		}
		Connection connection = null;
		try{
			connection = Connection_provider.getconnection();
			ClinicDAO clinicDAO = new JDBCClinicDAO(connection);
			
			String CheckMailToSend = clinicDAO.IsMailSend(loginInfo.getId());
			
			response.setContentType("text/html");
			response.setHeader("Cache-Control", "no-cache");
			response.getWriter().write(CheckMailToSend); 
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			
			connection.close();
		}
		
		
		return null;
	}
	
	public String checkLocation() throws Exception{
		if(!verifyLogin(request)){
			return "login";
		}
		Connection connection = null;
		String checkLoc = request.getParameter("checkLoc");
		int locId = Integer.parseInt(request.getParameter("id"));
		try{
			connection = Connection_provider.getconnection();
			ClinicDAO clinicDAO = new JDBCClinicDAO(connection);
			
			int checkLocationToSet = clinicDAO.updateLocationCheck(checkLoc,locId);			
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			
			connection.close();
		}
		return null;
	}
	
	public String setDNACharge() throws Exception{
		if(!verifyLogin(request)){
			return "login";
		}
		Connection connection = null;
		try{
			connection = Connection_provider.getconnection();
			ClinicDAO clinicDAO = new JDBCClinicDAO(connection);
			ArrayList<Clinic> dnaChargeList = clinicDAO.getDNAChargeList();
			clinicRegistrationForm.setDnaChargeList(dnaChargeList);
			
		}catch(Exception e){
			
		}finally{
			
			connection.close();
		}
				
		return "dnaCharge";
	}
	
	public String addDNACharge(){
		if(!verifyLogin(request)){
			return "login";
		}
		
		return "addDnaCharge";
		
	}
	
	public String saveDNACharge() throws Exception{
		if(!verifyLogin(request)){
			return "login";
		}
		Connection connection = null;
		try{
			connection = Connection_provider.getconnection();
			ClinicDAO clinicDAO = new JDBCClinicDAO(connection);
			Clinic clinic = new Clinic();
			clinic.setId(clinicRegistrationForm.getId());
			clinic.setDnaCharges(clinicRegistrationForm.getDnaCharges());
			int result = clinicDAO.saveDnaCharge(clinic);
			
			ArrayList<Clinic> dnaChargeList = clinicDAO.getDNAChargeList();
			clinicRegistrationForm.setDnaChargeList(dnaChargeList);
			
			clinicRegistrationForm.setMessage("DNA Charge Added Successfully!!");	
			addActionMessage("DNA Charge Added Successfully!!");
			
			
		}catch(Exception e){
			
		}finally{
			
			connection.close();
		}
		return "dnaCharge";
	}
	
	public String editDnaCharge() throws Exception{
		if(!verifyLogin(request)){
			return "login";
		}
		Connection connection = null;
		try{
			connection = Connection_provider.getconnection();
			int selectedid = Integer.parseInt(request.getParameter("selectedid"));			
			ClinicDAO clinicDAO = new JDBCClinicDAO(connection);
			Clinic clinic = clinicDAO.getDnaChargeDetails(selectedid);
			clinicRegistrationForm.setId(clinic.getId());
			clinicRegistrationForm.setDnaCharges(clinic.getDnaCharges());
			
		}catch(Exception e){
			
		}finally{
			
			connection.close();
		}
		return "editDNA";
	}
	
	public String updateDNACharge() throws Exception{
		if(!verifyLogin(request)){
			return "login";
		}
		Connection connection = null;
		try{
			connection = Connection_provider.getconnection();
			ClinicDAO clinicDAO = new JDBCClinicDAO(connection);
			int dnaCharge = clinicRegistrationForm.getDnaCharges();
			int id = clinicRegistrationForm.getId();
			int result = clinicDAO.updateDNACharge(dnaCharge,id);
			
			ArrayList<Clinic> dnaChargeList = clinicDAO.getDNAChargeList();
			clinicRegistrationForm.setDnaChargeList(dnaChargeList);
			
			clinicRegistrationForm.setMessage("DNA Charge Modified Successfully!!");	
			addActionMessage("DNA Charge Modified Successfully!!");
			
		}catch(Exception e){
			
		}finally{
			
			connection.close();
		}
		return "dnaCharge";
		
	}
	public String deleteDnaCharge() throws Exception{
		if(!verifyLogin(request)){
			return "login";
		}
		Connection connection = null;
		try{
			connection = Connection_provider.getconnection();
			ClinicDAO clinicDAO = new JDBCClinicDAO(connection);
			int selectedid = Integer.parseInt(request.getParameter("selectedid"));	
			int result = clinicDAO.deleteDNACharge(selectedid);
			ArrayList<Clinic> dnaChargeList = clinicDAO.getDNAChargeList();
			clinicRegistrationForm.setDnaChargeList(dnaChargeList);
			
			clinicRegistrationForm.setMessage("DNA Charge Deleted Successfully!!");	
			addActionMessage("DNA Charge Deleted Successfully!!");
			
		}catch(Exception e){
			
		}finally{
			
			connection.close();
		}
		return "dnaCharge";
	}
	/*public String saveEmailConfigure(){
		if(!verifyLogin(request)){
			return "login";
		}
		Connection connection = null;
		
		try{
			connection = Connection_provider.getconnection();
			ClinicDAO clinicDAO = new JDBCClinicDAO(connection);
			Clinic clinic = new Clinic();
			clinic.setEmailUserName(clinicRegistrationForm.getEmailUserName());
			clinic.setEmailPassword(clinicRegistrationForm.getEmailPassword());
			clinic.setEmailConfirmPassword(clinicRegistrationForm.getEmailConfirmPassword());
			clinic.setEmailHostName(clinicRegistrationForm.getEmailHostName());
			
			int result = clinicDAO.SaveEmailConfugureInfo(clinic);
			
			
		}catch(Exception e){
			e.printStackTrace();
		}
		return "checkEmailSend";
	}*/
	
	
	public String semika() throws Exception{
		
		String usr = request.getParameter("usr");
		
		String dbName = loginInfo.getDbName();
		
		boolean result = false;
		
		if(loginInfo.getUserType()==2){
			if(usr.equals(dbName)){
				result = true;
			}
		}
		
		Connection connection = null;
		try{
			
			//connection = DriverManager.getConnection(""+Constants.DB_HOST+":3306/admin","root","mysql");
			connection = DriverManager.getConnection(""+Constants.DB_HOST+":3306/admin",""+Constants.DB_USER+"",""+Constants.DB_PWD+"");
			
			
			ClinicDAO clinicDAO = new JDBCClinicDAO(connection);
			
			String selectedDbName = clinicDAO.getSemikaUserID(usr);
			
			if(dbName.equals(selectedDbName)){
				result = true;
			}
			
			String sresult = Boolean.toString(result) + "/" + usr;
			
			response.setContentType("text/html");
			response.setHeader("Cache-Control", "no-cache");
			response.getWriter().write(""+sresult+""); 
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			
			connection.close();
		}
		
		
		return null;
	}
	
	
 public String log() throws Exception{
		
		Connection connection = null;
		
		try{
			
			connection = Connection_provider.getconnection();
			ClinicDAO clinicDAO = new JDBCClinicDAO(connection);
			
			boolean status = Boolean.parseBoolean(request.getParameter("status"));
			String userid = request.getParameter("userid");
			
			if(status){
				int res = clinicDAO.updateAdminLoginStatus(userid,false);
			}
			
			
		}catch(Exception e){
			e.printStackTrace();
		}
		finally {
			connection.close();
		}
		
		return "log";
	}
	
	public ClinicRegistrationForm getModel() {
		// TODO Auto-generated method stub
		return clinicRegistrationForm;
	}


	public Pagination getPagination() {
		return pagination;
	}


	public void setPagination(Pagination pagination) {
		this.pagination = pagination;
	}


	public void prepare() throws Exception {
		Connection connection = null;
		
		try{
			
			connection = Connection_provider.getconnection();
			MasterDAO masterDAO = new JDBCMasterDAO(connection);
			DiaryManagementDAO diaryManagementDAO=new JDBCDiaryManagentDAO(connection);
			
			clinicRegistrationForm.setStartTimeList(PopulateList.clinicstartTimeList());
			clinicRegistrationForm.setEndTimeList(PopulateList.clinicEndTimeList());
			clinicRegistrationForm.setAdvanceTimeList(PopulateList.clinicEndTimeList());
			
			ArrayList<Master> specilizationList= diaryManagementDAO.getSpecializationList();
			clinicRegistrationForm.setSpecilizationList(specilizationList);
			
			ArrayList<Master> ipdformfieldList= masterDAO.getIpdFormFiledList();
			clinicRegistrationForm.setIpdformfieldList(ipdformfieldList);
			ArrayList<Master>countrycodeList = masterDAO.getCountryCodeList();
			clinicRegistrationForm.setCountrycodeList(countrycodeList);
			clinicRegistrationForm.setCountrycode(loginInfo.getCountry());
			
		}catch(Exception e){
			e.printStackTrace();
		}
		finally {
			connection.close();
		}
	}
	
	/*public String roleAccessSetting() throws Exception{
		Connection connection = null;
		try {
			connection = Connection_provider.getconnection();
			ClinicDAO clinicDAO = new JDBCClinicDAO(connection);
			ArrayList<Clinic> jobTitleList = clinicDAO.getAllJobTitle();
			clinicRegistrationForm.setJobTitleList(jobTitleList);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "roleAccessSetting";
	}*/
	public String getJobTitleSetting() throws Exception{
		Connection connection = null;
		try {
			String id = request.getParameter("id");
			connection = Connection_provider.getconnection();
			ClinicDAO clinicDAO = new JDBCClinicDAO(connection);
			StringBuffer buffer=new StringBuffer();
			
			
			response.setContentType("text/html");
			response.setHeader("Cache-Control", "no-cache");
			response.getWriter().write(buffer.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			connection.close();
		}

	   return null;		
	}
	
	
	public String roleAccessSetting() throws Exception{
		Connection connection = null;
		try {
			connection = Connection_provider.getconnection();
			ClinicDAO clinicDAO = new JDBCClinicDAO(connection);
			UserProfileDAO userProfileDAO = new JDBCUserProfileDAO(connection);
			//ArrayList<Clinic> jobTitleList = clinicDAO.getAllJobTitle();
			//clinicRegistrationForm.setJobTitleList(jobTitleList);
			ArrayList<Clinic> accessmodulelist = clinicDAO.getAllAccessModule();
			clinicRegistrationForm.setAccessmodulelist(accessmodulelist);
			
			ArrayList<Master>jobGroupList = userProfileDAO.getJobGroupList("0");
			clinicRegistrationForm.setJobGroupList(jobGroupList);
			
			ArrayList<Clinic> jobTitleList = clinicDAO.getJobTitle();
			//clinicRegistrationForm.setJobTitleList(jobTitleList);
			clinicRegistrationForm.setJobtitlelist(jobTitleList);
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			
			connection.close();
		}
		return "roleAccessSetting";
	}
	
	public String getjobtitle() throws Exception{
		Connection connection = null;
		try {
			connection = Connection_provider.getconnection();
			ClinicDAO clinicDAO = new JDBCClinicDAO(connection);
			String id = request.getParameter("id");
			ArrayList<Clinic> arrayList = clinicDAO.getJobTitle();
			StringBuffer buffer = new StringBuffer();
			buffer.append("<select class='form-control showToolTip chosen-select' name='jobtitle' id='jobtitle''>");
			buffer.append("<option value='0'>Select Job Title</option>");
			
			for(Clinic clinic : arrayList){
				 buffer.append("<option value='"+clinic.getName()+"'>"+clinic.getName()+"</option>");
			}
			buffer.append("</select>");
			response.setContentType("text/html");
			response.setHeader("Cache-Control", "no-cache");
			response.getWriter().write(buffer.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			
			connection.close();
		}
		return null;
	}
	
	public String getAllAccessAuthority() throws Exception{
		Connection connection = null;
		try {
			connection = Connection_provider.getconnection();
			ClinicDAO clinicDAO = new JDBCClinicDAO(connection);
			String modulename = request.getParameter("modulename");
			String job_title = request.getParameter("job_title");
			StringBuffer buffer = new StringBuffer(); 
			
			Clinic clinic = clinicDAO.checkroleAccessName(job_title);
			int id = 0;
			if (clinic.getName()==null) {
				id = clinicDAO.setroleaccesssetiing(job_title);
			}else {
				id = clinic.getId();
			}
			
			ArrayList<Clinic> arrayList = clinicDAO.getAllRoleAccessData(id,modulename);
			//buffer.append("<tr>");
			//buffer.append("<td><label>Select All</label></td>");
			//buffer.append("<td><label class='checkbox checkbox-custom-alt m-0 mt-5'><input type='checkbox' id='select-all'><i></i></label></td>");
			//buffer.append("</tr>");
			
			for (Clinic clinic2 : arrayList) {
				buffer.append("<tr>");
				if(clinic2.isFlag()==true){
					buffer.append("<td>");
					buffer.append("<label>"+clinic2.getModulename()+"</label>");
					buffer.append("</td>");
					buffer.append("<td>");
					//buffer.append("<label><input type='checkbox' name='"+clinic2.getName()+"' id='"+clinic2.getName()+"' class='checkbox' checked='checked'>"+clinic2.getName()+"</label>");
					buffer.append("<div class='onoffswitch greensea'");
					buffer.append("<label class='checkbox checkbox-custom-alt checkbox-custom-sm m-0 mail-select'><input type='checkbox' name='"+clinic2.getName()+"' id='"+clinic2.getName()+"' class='onoffswitch-checkbox' checked='checked'></label>");
					buffer.append("<label class='onoffswitch-label' for='"+clinic2.getName()+"'>");
					buffer.append("<span class='onoffswitch-inner'></span>");
					buffer.append("<span class='onoffswitch-switch'></span>");
					buffer.append("</label>");
					buffer.append("</div>");
					buffer.append("</td>");
					
				}else{
					//buffer.append("<td><label><input type='checkbox' name='"+clinic2.getName()+"' id='"+clinic2.getName()+"' class='checkbox'>"+clinic2.getName()+"</label></td>");
					buffer.append("<td>");
					buffer.append("<label>"+clinic2.getModulename()+"</label>");
					buffer.append("</td>");
					buffer.append("<td>");
					buffer.append("<div class='onoffswitch greensea'");
					buffer.append("<label class='checkbox checkbox-custom-alt checkbox-custom-sm m-0 mail-select'><input type='checkbox' name='"+clinic2.getName()+"' id='"+clinic2.getName()+"' class='onoffswitch-checkbox'></label>");
					buffer.append("<label class='onoffswitch-label' for='"+clinic2.getName()+"'>");
					buffer.append("<span class='onoffswitch-inner'></span>");
					buffer.append("<span class='onoffswitch-switch'></span>");
					buffer.append("</label>");
					buffer.append("</div>");
					buffer.append("</td>");
				}
				buffer.append("</tr>");
			}
			
			buffer.append("~");
			buffer.append(""+modulename+"");
			
			buffer.append("~");
			buffer.append(""+id+"");
			
			response.setContentType("text/html");
			response.setHeader("Cache-Control", "no-cache");
			response.getWriter().write(buffer.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			
			connection.close();
		}
		return null;
	}
	
	public String updateRoleAccess() throws Exception{
		Connection connection = null;
		try {
			connection = Connection_provider.getconnection();
			ClinicDAO clinicDAO = new JDBCClinicDAO(connection);
			String moduleid = clinicRegistrationForm.getModuleid();
			String roleid = clinicRegistrationForm.getRoleid();
			
			ArrayList<Clinic> arrayList = clinicDAO.getSpecificAccessModule(moduleid);
			for (Clinic clinic : arrayList) {
				String value = request.getParameter(""+clinic.getName()+"");
				if (value!=null) {
					if(value.equals("on")){
						value = "1";
					}else{
						value="0";
					}	
				}else{
					value ="0";
				}
				
				int result = clinicDAO.updateRoleAccessSetting(roleid,clinic.getName(),value);
				int res = result;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			
			connection.close();
		}
		return "saverole";
	}
	
	public String smscountdashboard(){
		if(!verifyLogin(request)){
			return "login";
		}
		Connection connection =null;
		try {
			connection = Connection_provider.getconnection();
			ClinicDAO clinicDAO = new JDBCClinicDAO(connection);
			String month_filter = clinicRegistrationForm.getMonth_filter();
			String year_filter = clinicRegistrationForm.getYear_filter();
			    
			if(month_filter!=null){
				if(month_filter.equals("")){
					DateFormat dateFormat = new SimpleDateFormat("MM");
				    Calendar cal = Calendar.getInstance();
				    month_filter = dateFormat.format(cal.getTime());
				}
			}else{
				DateFormat dateFormat = new SimpleDateFormat("MM");
			    Calendar cal = Calendar.getInstance();
			    month_filter = dateFormat.format(cal.getTime());
			}
			if(year_filter!=null){
				if(year_filter.equals("")){
					DateFormat dateFormat1 = new SimpleDateFormat("yyyy");
				    Calendar cal1 = Calendar.getInstance();
				    year_filter = dateFormat1.format(cal1.getTime());
				}
			}else{
				DateFormat dateFormat1 = new SimpleDateFormat("yyyy");
			    Calendar cal1 = Calendar.getInstance();
			    year_filter = dateFormat1.format(cal1.getTime());
			}
			
			ArrayList<Clinic> smscountmonthlylist = clinicDAO.getSMSCountList(month_filter,year_filter);
			clinicRegistrationForm.setSmscountmonthlylist(smscountmonthlylist);
			clinicRegistrationForm.setMonth_filter(month_filter);
			clinicRegistrationForm.setYear_filter(year_filter);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "smscountdashboard";
	}
	
	public String hsaccess() throws SQLException{
		if(!verifyLogin(request)){
			return "login";
		}
		try {
			Connection connection= Connection_provider.getconnection();
			ClinicDAO clinicDAO= new JDBCClinicDAO(connection);
			MasterDAO masterDAO =new JDBCMasterDAO(connection);
			Clinic clinic3= new Clinic();
			clinic3= clinicDAO.getclinicNewHospitalAccess();
			request.setAttribute("clinicaccess",clinic3);
			ArrayList<Master> locationlist = masterDAO.getAllLocation(null);
			clinicRegistrationForm.setLocationListPharmacy(locationlist);
			clinicRegistrationForm.setGrn_to_prisc_location(clinic3.getGrn_to_prisc_location());
			
			Clinic cliniclist = clinicDAO.getCliniclistDetails(1);
			request.setAttribute("clinic_access",cliniclist);
			
			Clinic clinicaccess= new Clinic();
			clinicaccess= clinicDAO.getNewClinicAccess();
			clinicRegistrationForm.setRegional_lang(clinicaccess.getRegional_lang());
			request.setAttribute("newclinicaccess",clinicaccess);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return "hsaccess";
		}
	
	public String userwiseaccess() throws SQLException{
		if(!verifyLogin(request)){
			return "login";
		}
		try {
			Connection connection= Connection_provider.getconnection();
			UserProfileDAO userProfileDAO = new JDBCUserProfileDAO(connection);
			String jobtitle = request.getParameter("jobtitle");
			String fname = request.getParameter("fname");
			String lname = request.getParameter("lname");
			String userid = request.getParameter("userid");
			//DiaryManagement diaryManagement2= userProfileDAO.getMainAccessofUser(userid);
			UserProfile individualaccess=userProfileDAO.getIndivdualAccess(userid);
			clinicRegistrationForm.setUserId(userid);
			clinicRegistrationForm.setFullname(fname+" "+lname);
			clinicRegistrationForm.setJob_title(jobtitle);
			request.setAttribute("userwiseaccesssetting",individualaccess);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return "userwiseaccess";
	}
	
}
