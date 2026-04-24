 package com.apm.common.web.action;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.validation.SkipValidation;

import com.apm.Accounts.eu.bi.AccountsDAO;
import com.apm.Accounts.eu.blogic.jdbc.JDBCAccountsDAO;
import com.apm.common.eu.blogic.jdbc.Connection_provider;
import com.apm.common.web.common.helper.Access;
import com.apm.common.web.common.helper.LoginHelper;
import com.apm.common.web.common.helper.LoginInfo;
import com.apm.main.common.constants.Constants;
import com.apm.common.utils.DateTimeUtils;
import com.apm.common.utils.Encryption;
import com.apm.Appointment.web.form.BranchForm;
import com.apm.DiaryManagement.eu.bi.ClientDAO;
import com.apm.DiaryManagement.eu.bi.DiaryManagementDAO;
import com.apm.DiaryManagement.eu.bi.FinderDAO;
import com.apm.DiaryManagement.eu.bi.NotAvailableSlotDAO;
import com.apm.DiaryManagement.eu.blogic.jdbc.JDBCClientDAO;
import com.apm.DiaryManagement.eu.blogic.jdbc.JDBCDiaryManagentDAO;
import com.apm.DiaryManagement.eu.blogic.jdbc.JDBCFinderDAO;
import com.apm.DiaryManagement.eu.blogic.jdbc.JDBCNotAvailableSlotDAO;
import com.apm.DiaryManagement.eu.entity.Client;
import com.apm.DiaryManagement.eu.entity.NotAvailableSlot;
import com.apm.DiaryManagement.eu.entity.Priscription;
import com.apm.DiaryManagement.web.action.SmsService;
import com.apm.Inventory.eu.bi.InventoryProductDAO;
import com.apm.Inventory.eu.blogic.jdbc.JDBCInventoryProductDAO;
import com.apm.Log.eu.entity.EmailLetterLog;
import com.apm.Master.eu.bi.MasterDAO;
import com.apm.Master.eu.blogic.jdbc.JDBCMasterDAO;
import com.apm.Pharmacy.eu.bi.PharmacyDAO;
import com.apm.Pharmacy.eu.blogic.jdbc.JDBCPharmacyDAO;
import com.apm.Registration.eu.bi.ClinicDAO;
import com.apm.Registration.eu.bi.UserProfileDAO;
import com.apm.Registration.eu.blogic.jdbc.JDBCClinicDAO;
import com.apm.Registration.eu.blogic.jdbc.JDBCUserProfileDAO;
import com.apm.Registration.eu.entity.Clinic;
import com.apm.Registration.eu.entity.UserProfile;
import com.apm.Registration.web.form.ClinicRegistrationForm;
import com.apm.Tools.eu.bi.EmailTemplateDAO;
import com.apm.Tools.eu.blogic.jdbc.JDBCEmailTemplateDAO;
import com.apm.Tools.eu.entity.EmailTemplate;
import com.apm.Tools.web.action.AllTemplateAction;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ModelDriven;



public class LoginAction extends BaseAction implements ModelDriven<BranchForm> {
	
	HttpServletRequest request = (HttpServletRequest) ActionContext.getContext().get(ServletActionContext.HTTP_REQUEST);
	HttpServletResponse response = (HttpServletResponse)ActionContext.getContext().get(ServletActionContext.HTTP_RESPONSE);
	HttpSession session = request.getSession(true);
	BranchForm branchForm = new BranchForm();
	
	private static final Logger log = Logger.getLogger(LoginAction.class);
	String actionType="",appclinicid="",vidappoin="",globactiontype="";
	@SkipValidation
	public String doctopd() throws SQLException{
		
		actionType = request.getParameter("actionType");
		if(actionType.equals("doctoropd")){
			String uid = request.getParameter("uid");
			String pass = request.getParameter("pass");
			appclinicid=request.getParameter("clinicid");
			branchForm.setUserId(uid);
			branchForm.setPassword(pass);
			branchForm.setActiontype(actionType);
			branchForm.setDbType("0");
			
			//session.removeAttribute("dbTypes");
			LoginHelper.removeLoginInfo(request);
		}
		
		
		return execute();
	}
	@SkipValidation
public String recepopd() throws SQLException{
		
		actionType = request.getParameter("actionType");
		if(actionType.equals("recepopd")){
			String uid = request.getParameter("uid");
			String pass = request.getParameter("pass");
			appclinicid=request.getParameter("clinicid");
			branchForm.setUserId(uid);
			branchForm.setPassword(pass);
			branchForm.setActiontype(actionType);
			branchForm.setDbType("0");
			
			//session.removeAttribute("dbTypes");
			LoginHelper.removeLoginInfo(request);
		}
		
		
		return execute();
	}
	
	@SkipValidation
public String mobemr() throws SQLException{
	
	 actionType = request.getParameter("actionType");
	if(actionType.equals("mobemr")){
		String uid = request.getParameter("uid");
		String pass = request.getParameter("pass");
		String appointmentid=request.getParameter("appintmntid");
		branchForm.setUserId(uid);
//		branchForm.setUserId("chraibgh");
		branchForm.setPassword(pass);
		branchForm.setActiontype(actionType);
		branchForm.setDbType("0");
		//session.removeAttribute("dbTypes");
		LoginHelper.removeLoginInfo(request);
		vidappoin=appointmentid;
	}
	
	
	return execute();
}
	@SkipValidation
	public String mobemrdoc() throws SQLException{
		
		globactiontype = request.getParameter("actionType");
		if(globactiontype.equals("mobemrdoc")){
			String uid = request.getParameter("uid");
			String pass = request.getParameter("pass");
			String appointmentid=request.getParameter("appintmntid");
			branchForm.setUserId(uid);
//			branchForm.setUserId("chraibgh");
			branchForm.setPassword(pass);
			branchForm.setActiontype(actionType);
			branchForm.setDbType("0");
			//session.removeAttribute("dbTypes");
			LoginHelper.removeLoginInfo(request);
			vidappoin=appointmentid;
			actionType="mobemr";
		}
		
		
		return execute();
	}
	
	
	@SkipValidation
	public String todoctoripd() throws SQLException{
		 actionType = request.getParameter("actionType");
			if(actionType.equals("todoctoripd")){
				String uid = request.getParameter("uid");
				
				branchForm.setUserId(uid);
//				branchForm.setUserId("chraibgh");
				branchForm.setPassword("");
				branchForm.setActiontype(request.getParameter("actionType"));
				branchForm.setDbType("0");
				//session.removeAttribute("dbTypes");
				LoginHelper.removeLoginInfo(request);
				
			}
			
		return execute();
	}
	@SkipValidation
public String doctoremr() throws SQLException{
		
		actionType = request.getParameter("actionType");
		if(actionType.equals("doctoremr")){
			String uid = request.getParameter("uid");
			String pass = request.getParameter("pass");
			appclinicid=request.getParameter("clinicid");
			branchForm.setUserId(uid);
			branchForm.setPassword(pass);
			branchForm.setActiontype(actionType);
			branchForm.setDbType("0");
			
			//session.removeAttribute("dbTypes");
			LoginHelper.removeLoginInfo(request);
		}
		
		
		return execute();
	}
	
	public String execute() throws SQLException{
		Connection connection = null;
		String url = "";
		try{
			
			String opendb=(String)session.getAttribute("openedb");
			
			if(opendb==null){
				
				session.setAttribute("openedb", "opd");
			}
			
			String sessionid = session.getId();
//			System.out.println(sessionid);
			
			
			session.setAttribute("dbTypes", Integer.parseInt(branchForm.getDbType()));
			
			connection = Connection_provider.getconnection();
			
			DatabaseMetaData dmd = connection.getMetaData();
			 url = dmd.getURL() + " username = " + dmd.getUserName();
//			System.out.println(url);
			log.debug("@@@@@@@@@@"+ url );
		
		
			LoginInfo loginInfo = new LoginInfo();
			loginInfo.setLoginType("pc");
//			connection = Connection_provider.getconnection();
			
			ClinicDAO clinicDAO=new JDBCClinicDAO(connection);
			
			
		String existUserid = "";
		boolean checkuserexist = clinicDAO.checkLoginUserExist(branchForm.getUserId());
		if(checkuserexist){
			 existUserid = clinicDAO.getExistUserId(branchForm.getUserId());
		}else{
			existUserid = "";
		}
		branchForm.setUserId(existUserid);
		
		
		boolean loginstatus = clinicDAO.getLoginStatus(branchForm.getUserId());
		if(loginstatus){
			addActionError(getText("error.userid.loggedin"));
			return INPUT;
		}
		
		Clinic clinic = clinicDAO.getuser(branchForm.getUserId());
		loginInfo.setCountry(clinic.getCountry());
		loginInfo.setIslogo(clinic.getIslogo());
		loginInfo.setClinicid1(clinic.getClinicID());
		//loginInfo.setAcaccess(clinic.getAcaccess());
		
	
		
		if(!branchForm.getUserId().equals(clinic.getUserId()) ){
			addActionError(getText("error.userid.usernotexist"));
			return INPUT;
		}
		if(!(actionType.equals("doctoropd")||actionType.equals("recepopd")||actionType.equals("mobemr")||actionType.equals("todoctoripd")||actionType.equals("doctoremr"))){
		String encPassword = Encryption.encryptSHA(branchForm.getPassword());
		if(!encPassword.equals(clinic.getPassword())){
			addActionError(getText("error.user.authfailed"));
			return INPUT;
		}
		}
		
		/*String userId=request.getParameter("userId");
		String password=request.getParameter("password");
		
		if(branchForm.getUserId().equals(userId) && branchForm.getPassword().endsWith(password)){
			int res = clinicDAO.updateLoginStatus(branchForm.getUserId());
		}
		*/
		
		if(!branchForm.getUserId().equals("admin")){
			loginInfo.setActionType("");
			
			if(clinic.getUserType()==4){
				//get country for practitoner
				String country = clinicDAO.getCountryForPractitoner(clinic.getClinicID());
				loginInfo.setCountry(country);
				connection = DriverManager.getConnection(""+Constants.DB_HOST+":3306/"+clinic.getClinicID()+"",""+Constants.DB_USER+"",""+Constants.DB_PWD+"");
				loginInfo.setClinicUserid(clinic.getClinicID());
				
			}else{
				connection = DriverManager.getConnection(""+Constants.DB_HOST+":3306/"+branchForm.getUserId()+"",""+Constants.DB_USER+"",""+Constants.DB_PWD+"");
				//connection = DriverManager.getConnection(":3306/"+branchForm.getUserId()+"","root","mysql");
				loginInfo.setClinicUserid(branchForm.getUserId());
			}
			if(actionType.equals("mobemr")){
				loginInfo.setClinicUserid(branchForm.getUserId());
				NotAvailableSlotDAO availableSlotDAO=new JDBCNotAvailableSlotDAO(connection);
				NotAvailableSlot availableSlot=availableSlotDAO.getAvailableSlotdata(DateTimeUtils.convertToInteger(vidappoin));
				session.setAttribute("sessionselectedclientid", DateTimeUtils.isNull(availableSlot.getClientId()));

				session.setAttribute("clientId", availableSlot.getClientId());
				session.setAttribute("diaryUserId", ""+availableSlot.getDiaryUserId());
				session.setAttribute("condition", availableSlot.getCondition());
				session.setAttribute("conditionId", availableSlot.getCondition());
				session.setAttribute("videoconfappoitmentid", vidappoin);
				
				String dusr=availableSlotDAO.getDiaryUserIdName(availableSlot.getDiaryUserId());
				branchForm.setUserId(dusr);
			}
			clinicDAO = new JDBCClinicDAO(connection);
			clinic = clinicDAO.getuser(branchForm.getUserId());
			loginInfo.setIslogo(clinic.getIslogo());
			loginInfo.setJobTitle(clinic.getJobtitle());
			loginInfo.setMobileno(clinic.getMobileNo());
			
			
		}
		Clinic clinic5 = new Clinic();
		if(loginInfo.getWarningmsg()==null){
			loginInfo.setWarningmsg("");
		}
		clinic5=clinicDAO.getLoginExpiry();
		if(clinic5.getLe_date()!=null){
			if(!clinic5.getLe_date().equals("")){
				DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
				Calendar cal = Calendar.getInstance();
	 			   String today = dateFormat.format(cal.getTime()); 
	 			  long diff=DateTimeUtils.getDifferenceOfTwoDateDBFormat(today, clinic5.getLe_date());
	 			 
	 			   if(diff<20 && diff>0){
	 				   if(clinic5.getLe_msg()!=null){
	 					   if(!clinic5.getLe_msg().equals("")){
	 						   loginInfo.setWarningmsg(clinic5.getLe_msg());
	 					   }
	 					  else{
		 					   loginInfo.setWarningmsg(" "+diff+""+" Day Remains To Expire Your Software");
		 				   }
	 				   }else{
	 					   loginInfo.setWarningmsg(" "+diff+""+" Day Remains To Expire Your Software");
	 				   }
	 				 
	 			   }else if(diff<=0 ){
	 				   return "expiryerror";
	 			   }
				
			}
		}
		
		//set time zone
		loginInfo.setTimeZone("Europe/London");
		if(loginInfo.getCountry()!=null){
			if(loginInfo.getCountry().equals("India")){
				loginInfo.setTimeZone("IST");
			}
			if(loginInfo.getCountry().equals("London")){
				loginInfo.setTimeZone("Europe/London");
			}
		}
		
		
		HttpServletRequest request = (HttpServletRequest) ActionContext.getContext().get(ServletActionContext.HTTP_REQUEST);
		HttpSession session = request.getSession(true);
		
		loginInfo.setId(clinic.getId());
		if(actionType.equals("doctoropd")){
			session.setAttribute("logintimedrid", ""+loginInfo.getId());
		}
		loginInfo.setUserId(clinic.getUserId());
		loginInfo.setFirstName(clinic.getFirstName());
		loginInfo.setLastName(clinic.getLastName());
		loginInfo.setClinicOwner(clinic.getClinicOwner());
		loginInfo.setUserType(clinic.getUserType());
		
		
		loginInfo.setDbName(clinic.getUserId());
		if(loginInfo.getUserType()==4){
			String userid = clinicDAO.getOtherUserID(clinic.getClinicID());
			loginInfo.setDbName(userid);
		}
		
//		String abc=(String)session.getAttribute(loginInfo.getUserId());
//		System.out.println(abc);
		//EmailConfigure Setting
		EmailTemplateDAO emailTemplateDAO = new JDBCEmailTemplateDAO(connection);
    	EmailTemplate emailTemplate = new EmailTemplate();
    	emailTemplate = emailTemplateDAO.getEmailConfigureDetails(clinic.getId());
    	loginInfo.setEmailConfigureId(emailTemplate.getEmailConfigureId());
    	loginInfo.setEmailUserName(emailTemplate.getEmailUserName());
    	loginInfo.setEmailPassword(emailTemplate.getEmailPassword());
		loginInfo.setEmailHostName(emailTemplate.getEmailHostName());
		loginInfo.setTelemedPharmacyMail(emailTemplate.getTelemedPharmacy());
		
		//Menu Setting
		Clinic clinic2 = new Clinic();
		if(clinic.getUserType()==4){
			int clinicId = clinicDAO.getClinicId(clinic.getId());
			clinic2 = clinicDAO.getCliniclistDetails(clinicId);	
			loginInfo.setClinicid(clinicId);
			
		}
		else if(clinic.getUserType()==2){
			clinic2 = clinicDAO.getCliniclistDetails(clinic.getId());
			loginInfo.setClinicid(clinic.getId());
		}
		//Hospital wise access
		Clinic clinic3= new Clinic();
		clinic3= clinicDAO.getclinicNewHospitalAccess();
		if(!clinic3.isActive_clinic()){
			return "expiryerror";
			
		}
		UserProfileDAO userProfileDAOxml= new JDBCUserProfileDAO(connection);
		NotAvailableSlotDAO notAvailableSlotDAO= new JDBCNotAvailableSlotDAO(connection);
		PharmacyDAO pharmacyDAO= new JDBCPharmacyDAO(connection);
		  UserProfileDAO userProfileDAO = new JDBCUserProfileDAO(connection);
		
		  AccountsDAO accountsDAO = new JDBCAccountsDAO(connection);
			ArrayList<Clinic> locationAdressList = accountsDAO.getLocationAddress(loginInfo.getClinicid());
			if(locationAdressList.size()>0){
				Clinic ccbg =  locationAdressList.get(0);
				session.setAttribute("balgopaladdress", ccbg.getAddress());
				session.setAttribute("balgopalcname", ccbg.getClinicName());
			}
			InventoryProductDAO inventoryProductDAO = new JDBCInventoryProductDAO(connection);
			boolean ispharmacist = inventoryProductDAO.isPharamcistOrNot(loginInfo.getUserId());
			String location="0";
			Priscription priscription =pharmacyDAO.getPharacyUsrLInfo(loginInfo.getUserId());
		    if(ispharmacist){
			    location = priscription.getLocation();
		    }else{
			    location = inventoryProductDAO.getHISUserLocation(loginInfo.getUserId());
		    }
		    if(DateTimeUtils.isNull(location).equals("")){
		    	location="0";
			}
			session.setAttribute("location", location);
		  
		  boolean activeprevusr=false;
		String lang ="";
		if(session.getAttribute(loginInfo.getUserId())!=null) {
		activeprevusr=true;
		loginInfo=(LoginInfo) session.getAttribute(loginInfo.getUserId());
		}
		session.setAttribute("logininfo", loginInfo);
		LoginHelper.saveLoginInfo(request, loginInfo);	
		
		if(!activeprevusr) {
			loginInfo.setIsclinic_use_yuvi(clinic3.isIsclinic_use_yuvi());
		loginInfo.setSmallClinic(clinic3.isSmallClinic());
		loginInfo.setLmh_po_discount(clinic3.isLmh_po_discount());	
		loginInfo.setVaci_sms_7day(clinic3.isVaci_sms_7day());
		loginInfo.setMaindash_graph(clinic3.isMaindash_graph());
		loginInfo.setSms_access(clinic3.isSms_access());
		loginInfo.setPhar_batchwise_sale(clinic3.isPhar_batchwise_sale());
		loginInfo.setDisc_approve_sms(clinic3.isDisc_approve_sms());
		loginInfo.setHidecalinpoprint(clinic3.isHidecalinpoprint());
		
		int vacinator=notAvailableSlotDAO.getVaccinator();
		loginInfo.setPcsAdmin(clinic3.isPcsadmin());
		loginInfo.setInvoice_default_note(clinic3.getInvoice_default_note());
		loginInfo.setDeleted_invst_charge(clinic3.isDeleted_invst_charge());
		loginInfo.setOpd_video_icon_show(clinic3.isOpd_video_icon_show());
		loginInfo.setEmr_vitals_show(clinic3.isEmr_vitals_show());
		loginInfo.setGrn_to_prisc_location(clinic3.getGrn_to_prisc_location());
		loginInfo.setNew_aureus_discard(clinic3.isNew_aureus_discard());
		loginInfo.setSms_senderid(clinic3.getSms_senderid());
		loginInfo.setRelease_notes_upload(clinic3.isRelease_notes_upload());
		loginInfo.setBarcode_productname_show(clinic3.isBarcode_productname_show());
		loginInfo.setDischarge_msg_hs(clinic3.isDischarge_msg_hs());
		loginInfo.setPhar_print_seq(clinic3.isPhar_print_seq());
		loginInfo.setOpening_closeing_on(clinic3.isOpening_closeing_on());
		loginInfo.setOpening_locations(clinic3.getOpening_locations());
		loginInfo.setPrachi_clinic(clinic3.isPrachi_clinic());
		loginInfo.setVacinator(vacinator);
		loginInfo.setOpd_telemed(clinic3.isOpd_telemed());
		loginInfo.setDirect_prisc(clinic3.isDirect_prisc());
		loginInfo.setPrisc_location_list(clinic3.isPrisc_location_list());
		loginInfo.setPrisc_print(clinic3.isPrisc_print());
		loginInfo.setAuto_generic_name(clinic3.isAuto_generic_name());
		loginInfo.setPrisc_deliver_return(clinic3.isPrisc_deliver_return());
		loginInfo.setJson_diagnosis(clinic3.isJson_diagnosis());
		loginInfo.setAddress_manual(clinic3.getAddress_manual());
		loginInfo.setPatientname_field(clinic3.getPatientname_field());
		loginInfo.setPractitonername_field(clinic3.getPractitonername_field());
		/*loginInfo.setDirect_refund_disc(clinic3.isDirect_refund_disc());*/
		loginInfo.setIsledgerhosp(clinic3.isIsledgerhosp());
		loginInfo.setInvest_order(clinic3.isInvest_order());
		loginInfo.setDemo_access(clinic3.isDemo_access());
		loginInfo.setDischarge_validation(clinic3.getDischarge_validation());
		loginInfo.setOpd_payamnt_sms(clinic3.isOpd_payamnt_sms());
		loginInfo.setIpd_payamnt_sms(clinic3.isIpd_payamnt_sms());
		loginInfo.setRefund_payamnt_sms(clinic3.isRefund_payamnt_sms());
		loginInfo.setAdv_payamnt_sms(clinic3.isAdv_payamnt_sms());
		loginInfo.setOther_payamnt_sms(clinic3.isOther_payamnt_sms());
		loginInfo.setNewdischargecard(clinic3.isNewdischargecard());
		loginInfo.setInvoice_date_modify(clinic3.isInvoice_date_modify());
		loginInfo.setOt_patient_sms(clinic3.isOt_patient_sms());
		loginInfo.setOt_surgeon_sms(clinic3.isOt_surgeon_sms());
		loginInfo.setOpdfollowup(clinic3.getOpdfollowup());
		loginInfo.setCreate_invoice_selected_charges(clinic3.isCreate_invoice_selected_charges());
		loginInfo.setVermanh(clinic3.isVermanh());
		loginInfo.setWardhazp(clinic3.isWardhazp());
		loginInfo.setOpd_recep_sp_list(clinic3.isOpd_recep_sp_list());
		loginInfo.setDel_ipd_data_cancel_inv(clinic3.isDel_ipd_data_cancel_inv());
		
		loginInfo.setSupport_Access(userProfileDAOxml.supportAccess(loginInfo.getUserId()));
		loginInfo.setAnalytics_template(clinic3.isAnalytics_template());
		
		//xml access
		String x=request.getRealPath("/");
//		System.out.println(x);
		Access xmlaccess=userProfileDAOxml.getXMLAccess(loginInfo.getClinicUserid(),x);
		loginInfo.setInvestigation_newaccess(xmlaccess.getInvestigation_newaccess());
		//Indivisual access
		UserProfile individualaccess=userProfileDAOxml.getIndivdualAccess(loginInfo.getUserId());
		loginInfo.setInventory_report_access(individualaccess.isInventory_report_access());
		loginInfo.setIndent_self_approve(individualaccess.isIndent_self_approve());
		loginInfo.setIndent_all_approve(individualaccess.isIndent_all_approve());
		loginInfo.setAdmsn_date_edit(individualaccess.isAdmsn_date_edit());
		loginInfo.setEditpharmacy_bill(individualaccess.isEditpharmacy_bill());
		loginInfo.setManual_sms_vaccine(individualaccess.isManual_sms_vaccine());
		loginInfo.setFreeze_unfreeze(individualaccess.isFreeze_unfreeze());
		loginInfo.setPrisc_temp_showother(individualaccess.isPrisc_temp_showother());
		loginInfo.setPprice_sale(individualaccess.isPprice_sale());
		loginInfo.setMulti_adjust_approve(individualaccess.isMulti_adjust_approve());
		loginInfo.setEdit_product(individualaccess.isEdit_product());
		loginInfo.setAdjustment_approve(individualaccess.isAdjustment_approve());
		loginInfo.setEdit_approve(individualaccess.isEdit_approve());
		loginInfo.setMulti_edit_product(individualaccess.isMulti_edit_product());
		loginInfo.setEdit_catalogue_name(individualaccess.isEdit_catalogue_name());
		loginInfo.setMax_phar_discount(individualaccess.isMax_phar_discount());
		loginInfo.setChange_indent_product(individualaccess.isChange_indent_product());
		loginInfo.setSuperadminid(individualaccess.getId());
		loginInfo.setProc_after_stock(individualaccess.isProc_after_stock());
		loginInfo.setCancel_invoice_new(individualaccess.isCancel_invoice_new());
		loginInfo.setUpdate_investigation_charge(individualaccess.isUpdate_investigation_charge());
		loginInfo.setPaymentReport(individualaccess.isPaymentReport());
		loginInfo.setRef_dis_pay(individualaccess.isRef_dis_pay());
		loginInfo.setAdd_manual_charge(individualaccess.isAdd_manual_charge());
		loginInfo.setInvoicemodify(individualaccess.isInvoicemodify());
		loginInfo.setAdditional_disc(individualaccess.isAdditional_disc());
		loginInfo.setIndv_discount(individualaccess.isIndv_discount());
		loginInfo.setPayrollaccess(individualaccess.isPayrollaccess());
		loginInfo.setShow_dept_opd_list(individualaccess.getShow_dept_opd_list());
		loginInfo.setShow_hospital_admin(clinic3.isShow_hospital_admin());
		loginInfo.setDirect_ipd(clinic3.isDirect_ipd());
		loginInfo.setDrwise_ipd(clinic3.isDrwise_ipd());
		loginInfo.setJobtitlewise_investigation(clinic3.isJobtitlewise_investigation());
		loginInfo.setAdd_charge_amt_edit(individualaccess.isAdd_charge_amt_edit());
		loginInfo.setOpd_user_vid_access(individualaccess.isOpd_user_vid_access());
		loginInfo.setDr_opd_vid(individualaccess.isDr_opd_vid());
		loginInfo.setDelete_invoice_history(individualaccess.isDelete_invoice_history());
		loginInfo.setUserwiseaceess(individualaccess.getUserwiseaceess());
		loginInfo.setAdd_medicine(individualaccess.isAdd_medicine());
		loginInfo.setLmh_consultation_charge(individualaccess.isLmh_consultation_charge());
		loginInfo.setLmh_other_charge(individualaccess.isLmh_other_charge());
		loginInfo.setLmh_discount(individualaccess.isLmh_discount());
		loginInfo.setDeptOpdReport(individualaccess.isDeptOpdReport());
		loginInfo.setSupplier_access(individualaccess.isSupplier_access());
		loginInfo.setMrp_access(individualaccess.isMrp_access());
		loginInfo.setSale_price_access(individualaccess.isSale_price_access());
		loginInfo.setGst_access(individualaccess.isGst_access());
		loginInfo.setPurchase_price_access(individualaccess.isPurchase_price_access());
		loginInfo.setSale_value_access(individualaccess.isSale_value_access());
		loginInfo.setNet_rate_access(individualaccess.isNet_rate_access());
		loginInfo.setOpd_discount_access(individualaccess.isOpd_discount_access());
		lang = individualaccess.getUser_language_access();
		
		
//		loginInfo.setShow_wardname(clinic3.isShow_wardname());
		loginInfo.setCriticalvaluesms(clinic2.isCriticalvaluesms());
		loginInfo.setMedtreatgiven(clinic2.isMedtreatgiven());
		//for new req of discharge from in kunal Hospital
		loginInfo.setDischarge_new(clinic3.isDischarge_new());
		
	//set clinic start and end time
		if(clinic.getUserType()!=1){
			DiaryManagementDAO diaryManagementDAO = new JDBCDiaryManagentDAO(connection);
			Clinic csetting = diaryManagementDAO.getClinicStartAndEndTime(loginInfo.getClinicid());
			String temp[] = csetting.getStarttime().split(":");
			loginInfo.setClinicStartTime(Integer.parseInt(temp[0]));
			String temp1[] = csetting.getEndtime().split(":");
			loginInfo.setClinicEndTime(Integer.parseInt(temp1[0]));
			
			loginInfo.setDbsize(csetting.getDbsize());
		}
	//access for invoice group by and invoice seq no
		loginInfo.setInvoice_charge_seqno(clinic3.isInvoice_charge_seqno());
		loginInfo.setInvoice_groupby(clinic3.isInvoice_groupby());
		//access while take payment from opd of tp patient then received amount should 0
		loginInfo.setOpd_tp_zero_invoice(clinic3.isOpd_tp_zero_invoice());
		
			//set clinic registerd address
			loginInfo.setRegAddress(clinic2.getAddress());
			loginInfo.setRegLocation(clinic2.getLocationname());
			loginInfo.setRegPinCode(clinic2.getPinCode());
			loginInfo.setRegContactNo(clinic2.getContactNo());
		//  14/12/2018 show option for posted invoice
			loginInfo.setShow_unpost(clinic3.isShow_unpost());
			//  20/12/2018 sms on bed change and new admission for dr
			loginInfo.setSms_on_bedchange(clinic3.isSms_on_bedchange());
			loginInfo.setSms_on_newadm(clinic3.isSms_on_newadm());
			loginInfo.setIpd_abbr_access(clinic3.getIpd_abbr_access());
			loginInfo.setHidelogoinvst(clinic3.isHidelettinvst());
			loginInfo.setHidelogoemr(clinic3.isHidelettemr());
			loginInfo.setHidelogobillinv(clinic3.isHidelettbillinv());
			loginInfo.setInvst_inv_apr(clinic3.isInvst_inv_apr());
			loginInfo.setBalgopal(clinic3.isBalgopal());
			loginInfo.setPrisc_savenprint(clinic3.isPrisc_savenprint());
			loginInfo.setInvest_savenprint(clinic3.isInvest_savenprint());
			loginInfo.setPackage_access(clinic3.isPackage_access());
			loginInfo.setSms_reg_patient(clinic3.isSms_reg_patient());
			loginInfo.setSms_cancel_appointment(clinic3.isSms_cancel_appointment());
			//set balgopal address
			
		
		/*	loginInfo.setDiaryManagement(clinic2.isDiaryManagement());
			loginInfo.setAppointmentBooking(clinic2.isAppointmentBooking());
			loginInfo.setBasicFinance(clinic2.isBasicFinance());
			loginInfo.setFullFinance(clinic2.isFullFinance());
			loginInfo.setMedicalRecord(clinic2.isMedicalRecord());
			loginInfo.setClinicResourceMngment(clinic2.isClinicResourceMngment());
			loginInfo.setClinicPayrollMngment(clinic2.isClinicPayrollMngment());
			loginInfo.setCommunication(clinic2.isCommunication());
			loginInfo.setReport(clinic2.isReport());
			loginInfo.setAssessmentForms(clinic2.isAssessmentForms());
			loginInfo.setDesktop(clinic2.isDesktop());
			loginInfo.setiOS(clinic2.isIOS());
			loginInfo.setMobile(clinic2.isMobile());
			loginInfo.setTablet(clinic2.isTablet());*/
			if(loginInfo.getClinicid1().equals("manasclinic")) {
				//for mamas clinic
				String clinicstaff_id=notAvailableSlotDAO.getclinicstaff(loginInfo.getId());
				String manas_clinicname=notAvailableSlotDAO.getManasClinicName(clinicstaff_id);
				loginInfo.setClinicName(manas_clinicname);
			}else {
				//origanal
				loginInfo.setClinicName(clinic3.getClinicName());
			}

			//loginInfo.setClinicName(clinic3.getClinicName());
			loginInfo.setClinicAddress(clinic2.getAddress());
			loginInfo.setClinicLogo(clinic2.getUserImageFileName());
		
		
		
		
		
		    loginInfo.setPharmacyUserType(priscription.getPharmacyUserType());
		    loginInfo.setPurchase_edit_pharmacy(priscription.isPurchase_edit());
		    loginInfo.setCancel_po(priscription.getCancel_po());
		    loginInfo.setSession_added_med(priscription.isSession_added_med());
		    loginInfo.setPhar_patient_edit(priscription.isPatient_edit());
		    loginInfo.setPharm_print_backdate(priscription.isPharm_print_backdate());
			loginInfo.setCreditlimit(priscription.getCreditlimit());
			loginInfo.setCreditlimitaccess(priscription.isCreditlimitaccess());
			
		
		  
		    //MIS access
			  Access accessmis=userProfileDAO.getMisRoleaccess(loginInfo.getUserId());
			  
			  loginInfo.setPractioner_share(accessmis.isPractioner_share());
			  loginInfo.setOpd_practioner_share(accessmis.isOpd_practioner_share());
			  loginInfo.setCharges(accessmis.isCharges());
			  loginInfo.setInvoice(accessmis.isInvoice());
			  loginInfo.setPayment_report_detailed(accessmis.isPayment_report_detailed());
			  loginInfo.setPayment_report_small(accessmis.isPayment_report_small());
			  loginInfo.setAdd_debtors(accessmis.isAdd_debtors());
			  loginInfo.setCa(accessmis.isCa());
			  loginInfo.setAuditors(accessmis.isAuditors());
			  loginInfo.setUserwise_payment(accessmis.isUserwise_payment());
			  loginInfo.setDeptwise_analysis(accessmis.isDeptwise_analysis());
			  loginInfo.setCharges_share(accessmis.isCharges_share());
			  loginInfo.setBilling(accessmis.isBilling());
			  loginInfo.setDiscount(accessmis.isDiscount());
			  loginInfo.setCancel_invoice(accessmis.isCancel_invoice());
			  loginInfo.setPayment(accessmis.isPayment());
			  loginInfo.setKpi_dashboard(accessmis.isKpi_dashboard());
			  loginInfo.setTreatment_episode_list(accessmis.isTreatment_episode_list());
			  loginInfo.setPatient_condition_list(accessmis.isPatient_condition_list());
			  loginInfo.setDtr_report(accessmis.isDtr_report());
			  loginInfo.setPatientlist(accessmis.isPatientlist());
			  loginInfo.setCurrent_track_with_no_future_ampts(accessmis.isCurrent_track_with_no_future_ampts());
			  loginInfo.setNo_appointment_activity_record(accessmis.isNo_appointment_activity_record());
			  loginInfo.setDna_with_no_future_appointment(accessmis.isDna_with_no_future_appointment());
			  loginInfo.setNo_activity_record(accessmis.isNo_activity_record());
			  loginInfo.setDna_analysiis(accessmis.isDna_analysiis());
			  loginInfo.setAppointment_kept_vs_dna(accessmis.isAppointment_kept_vs_dna());
			  loginInfo.setTreatment_state_by_refferal(accessmis.isTreatment_state_by_refferal());
			  loginInfo.setReturning_patients(accessmis.isReturning_patients());
			  loginInfo.setOutcome_discharge(accessmis.isOutcome_discharge());
			  loginInfo.setDeathreport(accessmis.isDeathreport());
			  loginInfo.setCurrent_patient_report(accessmis.isCurrent_patient_report());
			  loginInfo.setIpd_daily_report(accessmis.isIpd_daily_report());
			  loginInfo.setIpd_monthly_report(accessmis.isIpd_monthly_report());
			  loginInfo.setBed_occupancy_report(accessmis.isBed_occupancy_report());
			  loginInfo.setReffered_by(accessmis.isReffered_by());
			  loginInfo.setMlc(accessmis.isMlc());
			  loginInfo.setReport_outstandng(accessmis.isReport_outstandng());
			  loginInfo.setNow_patients(accessmis.isNow_patients());
			  loginInfo.setTotal_patients_seen(accessmis.isTotal_patients_seen());
			  loginInfo.setDna_outstanding_action(accessmis.isDna_outstanding_action());
			  loginInfo.setSales_report(accessmis.isSales_report());
			  loginInfo.setPayment_recive(accessmis.isPayment_recive());
			  loginInfo.setInventory_opening(accessmis.isInventory_opening());
			  loginInfo.setItemwise_stock(accessmis.isItemwise_stock());
			  loginInfo.setPurchase_report(accessmis.isPurchase_report());
			  loginInfo.setExpiry_medicine_report(accessmis.isExpiry_medicine_report());
			  loginInfo.setGrn(accessmis.isGrn());
			  loginInfo.setIndent_statement(accessmis.isIndent_statement());
			  loginInfo.setIpd_daily_discharge(accessmis.isIpd_daily_discharge());
			  loginInfo.setOpd_ipd_cancel_refund(accessmis.isOpd_ipd_cancel_refund());
			  loginInfo.setIpd_bill_register(accessmis.isIpd_bill_register());
			  loginInfo.setService_register_details(accessmis.isService_register_details());
			  loginInfo.setCancel_invoice_report(accessmis.isCancel_invoice_report());
			  loginInfo.setKPI_report(accessmis.isKPI_report());
			  loginInfo.setRefund_report(accessmis.isRefund_report());
			  loginInfo.setPayment_combined_report(accessmis.isPayment_combined_report());
			  loginInfo.setPayment_receipt_report(accessmis.isPayment_receipt_report());
			  loginInfo.setDeptwise_payment_report(accessmis.isDeptwise_payment_report());
			  loginInfo.setDoctor_share_report(accessmis.isDoctor_share_report());
			  loginInfo.setOpd_ipd_report(accessmis.isOpd_ipd_report());
			  loginInfo.setVisiting_consultation_report(accessmis.isVisiting_consultation_report());
			  loginInfo.setCharges_detaile_report(accessmis.isCharges_detaile_report());
			  loginInfo.setPractitioner_share_report(accessmis.isPractitioner_share_report());
			  loginInfo.setOut_source_report(accessmis.isOut_source_report());
			  loginInfo.setLab_report(accessmis.isLab_report());
			  loginInfo.setInvestigation_count_report(accessmis.isInvestigation_count_report());
			  loginInfo.setInvestigation_package_report(accessmis.isInvestigation_package_report());
			  loginInfo.setInvstwise_count_report(accessmis.isInvstwise_count_report());
			  loginInfo.setInvestigation_revenue_report(accessmis.isInvestigation_revenue_report());
			  loginInfo.setInvst_revcount_namewise(accessmis.isInvst_revcount_namewise());
			  loginInfo.setInvst_opdipd_report(accessmis.isInvst_opdipd_report());
			  loginInfo.setInvst_tat_report(accessmis.isInvst_tat_report());
			  loginInfo.setInvst_criticalval_report(accessmis.isInvst_criticalval_report());
			  loginInfo.setItem_wise_sale_report(accessmis.isItem_wise_sale_report());
			  loginInfo.setPatient_consumption_report(accessmis.isPatient_consumption_report());
			  loginInfo.setPayable_aging_report(accessmis.isPayable_aging_report());
			  loginInfo.setStock_report(accessmis.isStock_report());
			  loginInfo.setConsumption_report(accessmis.isConsumption_report());
			  loginInfo.setCathlab_stock_report(accessmis.isCathlab_stock_report());
			  loginInfo.setDetail_grn_report(accessmis.isDetail_grn_report());
			  loginInfo.setCathlab_opening_closing(accessmis.isCathlab_opening_closing());
			  loginInfo.setOpd_tat_report(accessmis.isOpd_tat_report());
			  loginInfo.setBirthplace_report(accessmis.isBirthplace_report());
			  loginInfo.setTotal_revenue_report(accessmis.isTotal_revenue_report());
			  loginInfo.setWard_wise_rev_report(accessmis.isWard_wise_rev_report());
			  loginInfo.setRevenue_matrix(accessmis.isRevenue_matrix());
			  loginInfo.setAdmission_discharge(accessmis.isAdmission_discharge());
			  loginInfo.setDeptwise_revenue_report(accessmis.isDeptwise_revenue_report());
			  loginInfo.setConsultation_report(accessmis.isConsultation_report());
			  loginInfo.setBed_occupancy_per_day(accessmis.isBed_occupancy_per_day());
			  loginInfo.setRender_charges_report(accessmis.isRender_charges_report());
			  loginInfo.setInventory_openclose_report(accessmis.isInventory_openclose_report());
			  loginInfo.setDetailed_inventory_openclose_report(accessmis.isDetailed_inventory_openclose_report());
			  loginInfo.setInventory_openclose_catalogue(accessmis.isInventory_openclose_catalogue());
			  loginInfo.setInventory_stock_report(accessmis.isInventory_stock_report());
			  loginInfo.setInventory_sale_report(accessmis.isInventory_sale_report());
			  loginInfo.setPayment_receive_report(accessmis.isPayment_receive_report());
			  loginInfo.setInventory_itemwise_sale(accessmis.isInventory_itemwise_sale());
			  loginInfo.setProductwise_sale_report(accessmis.isProductwise_sale_report());
			  loginInfo.setCataloguewise_sale_report(accessmis.isCataloguewise_sale_report());
			  loginInfo.setGst_report(accessmis.isGst_report());
			  loginInfo.setGrn_gst_report(accessmis.isGrn_gst_report());
			  loginInfo.setSupplier_payment_report(accessmis.isSupplier_payment_report());
			  loginInfo.setInventory_report(accessmis.isInventory_report());
			  priscription=userProfileDAO.getpharmaAdminaccess();
			  loginInfo.setIsdotmatrix(priscription.isIsdotmatrix());
		    
		
		    
		   
		
			
		
		String checkValue = clinicDAO.IsMailSend(clinic.getId());
		branchForm.setCheckMailSend(checkValue);
		
		
		//show admin for all user other than practitioner
		
		int duserid  = userProfileDAO.getDiaryUserId(loginInfo.getUserId());
		UserProfile userProfile = userProfileDAO.getUserprofileDetails(duserid);
		loginInfo.setDicipline(userProfile.getDiciplineName());
		loginInfo.setPrisc_new_req_access(userProfile.isPrisc_new_req_access());
		loginInfo.setDirect_refund_disc(userProfile.isDirect_refund_disc());
		loginInfo.setTreatment_episode_acc(userProfile.isTreatmentacc());
		loginInfo.setSupplier_add(userProfile.isSupplier_add());
		loginInfo.setAdjustmentaccess(userProfile.isAdjustmentaccess());
		loginInfo.setAcaccess(userProfile.getAcaccess());
		loginInfo.setEdit_paypo(userProfile.isEdit_paypo());
		loginInfo.setEdit_invst_charge(userProfile.isEdit_invst_charge());
		loginInfo.setStock_log(clinic2.isStock_log());
		//lokesh
		loginInfo.setFullname(userProfile.getFullname());
		loginInfo.setBdaysms(clinic2.isBdaysms());
		loginInfo.setImmusms(clinic2.isImmusms());
		loginInfo.setF_diagnosis_discharge(clinic2.isF_diagnosis_discharge());
		loginInfo.setSeq_no_gen(clinic2.isSeq_no_gen());
		loginInfo.setRemoveprocurement(clinic2.isRemoveprocurement());
		loginInfo.setModify_disc(clinic2.isModify_disc());
		loginInfo.setUserMobileNo(userProfile.getMobile());
		// 
		loginInfo.setSmsVisitingConslt(clinic2.isSmsVisitingConslt());
		loginInfo.setShow_wardname(clinic2.isShow_wardname());
		
		//connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/admin",""+Constants.DB_USER+"",""+Constants.DB_PWD+"");
		userProfileDAO = new JDBCUserProfileDAO(connection);
		
		//Role wise access
		String tempjobtitle =userProfile.getJobtitle();
		if(loginInfo.getUserwiseaceess()==1){
			tempjobtitle = loginInfo.getUserId();
		}
		Access access = userProfileDAO.getRoleAccess(tempjobtitle);
		
		//Access access = userProfileDAO.getUserRoleAccess(loginInfo.getUserId());
		loginInfo.setMedicine_barcode(access.isMedicine_barcode());
		loginInfo.setPharm_print_backdate(access.isPharm_print_backdate());
		loginInfo.setDiaryManagement(access.isDiarymanagement());
		loginInfo.setAppointmentBooking(access.isAppointmentbooking());
		loginInfo.setBasicFinance(access.isBasicfinance());
		loginInfo.setFullFinance(access.isFullfinance());
		loginInfo.setMedicalRecord(access.isMedicalrecord());
		loginInfo.setCommunication(access.isCommunication());
		loginInfo.setReport(access.isReport());
		loginInfo.setAssessmentForms(access.isAssessmentForm());
		loginInfo.setManageclient(access.isManageclient());
		loginInfo.setManageclinic(access.isManageclinic());
		loginInfo.setManagemaster(access.isManagemaster());
		loginInfo.setManageprisc(access.isManageprisc());
		loginInfo.setManageinvst(access.isManageinvst());
		loginInfo.setManageipd(access.isManageipd());
		loginInfo.setJobTitle(userProfile.getJobtitle());
		loginInfo.setManageopd(access.isManageopd());
		loginInfo.setManageemr(access.isManageemr());
		loginInfo.setExpences(access.isExpences());
		loginInfo.setPayroll(access.isPayroll());
		loginInfo.setBloodbak(access.isBloodbak());
		loginInfo.setInventory(access.isInventory());
		loginInfo.setDischarge(access.isDischarge());
		loginInfo.setManagemis(access.isManagemis());
		loginInfo.setApmtfinder(access.isApmtfinder());
		loginInfo.setPacks(access.isPacks());
		loginInfo.setInvestigation_chart(access.isInvestigation_chart());
		loginInfo.setSheduler(access.isSheduler());
		loginInfo.setHousekeeping(access.isHousekeeping());
		loginInfo.setDietery(access.isDietery());
		loginInfo.setCafeteria(access.isCafeteria());
		loginInfo.setPackages(access.isPackages());
		loginInfo.setAmbulance(access.isAmbulance());
		loginInfo.setBank_deposite(access.isBank_deposite());
		loginInfo.setAccount_reconcilation(access.isAccount_reconcilation());
		loginInfo.setCathlab(access.isCathlab());
		//set  ot, casualty, pharmacy, mrd, marketing, voice_recorder in main dashboard
		loginInfo.setOt(access.isOt());
		loginInfo.setCasualty(access.isCasualty());
		loginInfo.setMrd(access.isMrd());
		loginInfo.setMarketing(access.isMarketing());
		loginInfo.setVoice_recording(access.isVoice_recording());
		loginInfo.setIndent(access.isIndent());
		loginInfo.setPharmacy(access.isPharmacy());
		loginInfo.setInvestigation_approve(access.isInvestigation_approve());
		loginInfo.setDaily_opd(access.isDaily_opd());
		loginInfo.setIndent_approve(access.isIndent_approve());
		loginInfo.setTpa(access.isTpa());
		loginInfo.setNabh_quality(access.isNabh_quality());
		loginInfo.setDoctor_opd(access.isDoctor_opd());
		loginInfo.setInvst_collect(access.isInvst_collect());
		loginInfo.setToken_display(access.isToken_display());
		loginInfo.setRegistrationdash(access.isRegistrationdash());
		loginInfo.setMyhr(access.isMyhr());
		loginInfo.setDaycare(access.isDaycare());
		loginInfo.setEmergency_lbl(access.isEmergency_lbl());
		loginInfo.setRefund(access.isRefund());
		loginInfo.setDiscount(access.isDiscount());
		loginInfo.setVaccination(access.isVaccination());
		loginInfo.setSetup_master(access.isSetup_master());
		loginInfo.setVideo_training(access.isVideo_training());
		loginInfo.setSale_pharmacy(access.isSale_pharmacy());
		loginInfo.setDelete_invoice(access.isDelete_invoice());
		
		//refund and discount dashboard
		loginInfo.setRefund_dashboard(userProfile.isRefund_dashboard());
		loginInfo.setShowinvestigation(userProfile.isShowinvestigation());
		
		if(userProfile.getJobtitle().equals("Admin")){
			clinic.setUserType(2);
			loginInfo.setUserType(2);
		}
		
		
		//get pacs ip address
		String pacsip = clinicDAO.getPacsIpAddress(loginInfo.getClinicUserid());
		loginInfo.setPacsip(pacsip);
		
		
		
		//get outo prisc
		int outoprisc = clinicDAO.getoutoprisc(loginInfo.getClinicUserid());
		loginInfo.setOutoprisc(outoprisc);
		
		int wardforcharge = clinicDAO.getwardforcharge(loginInfo.getClinicUserid());
		loginInfo.setWardforcharge(wardforcharge);
		
		int iskunal = clinicDAO.getIsKunal(loginInfo.getClinicUserid());
		int pharmacypagelimit=clinicDAO.getPharmacyPagelimit();
		loginInfo.setPagelimitpharmacy(pharmacypagelimit);
		loginInfo.setIskunal(iskunal);
		//Server DATA
		
		Clinic server=clinicDAO.getServerData();
		loginInfo.setSshhostname(DateTimeUtils.isNull(server.getSshhostname()));
		loginInfo.setSshpassword(DateTimeUtils.isNull(server.getSshpassword()));
		loginInfo.setPort(server.getPort());
		loginInfo.setSshuser(server.getSshuser());
		loginInfo.setDbpassword(server.getDbpassword());
		//get clinic ip address
		String ipadd = clinicDAO.getClinicIpAddress(loginInfo.getClinicUserid());
		Access globalaccess = clinicDAO.getGlobalAccessAccess(userProfile.getJobtitle());
		
		if(ipadd!=null){
			String remoteaddress = request.getRemoteAddr();
			if(!globalaccess.isGlobalaccess()){
				if(!remoteaddress.equals(ipadd)){
					LoginHelper.removeLoginInfo(request);
					return "noglobalaccess";
				}
			}
		}
		
		boolean isshowdiscount = userProfileDAO.checkisshowdiscount(loginInfo.getClinicid());
		
		loginInfo.setShowdiscount(isshowdiscount);
		
		
		//set indivusial access
		
		
		
		//set opdrollaccess
		Access opdaccess = userProfileDAO.getopdRollAccess(tempjobtitle);
		loginInfo.setOpd_modify(opdaccess.isOpd_modify());
		loginInfo.setOpd_cancel(opdaccess.isOpd_cancel());
		loginInfo.setOpd_prescription(opdaccess.isOpd_prescription());
		loginInfo.setOpd_investigation(opdaccess.isOpd_investigation());
		loginInfo.setOpd_ot(opdaccess.isOpd_ot());
		loginInfo.setOpd_viewaccount(opdaccess.isOpd_viewaccount());
		loginInfo.setOpd_apmtfinder(opdaccess.isOpd_apmtfinder());
		loginInfo.setOpd_advandref(opdaccess.isOpd_advandref());
		loginInfo.setOpd_modifydiagnosis(opdaccess.isOpd_modifydiagnosis());
		loginInfo.setOpd_editpatient(opdaccess.isOpd_editpatient());
		loginInfo.setOpd_log(opdaccess.isOpd_log());
		loginInfo.setOpd_emr(opdaccess.isOpd_emr());
		loginInfo.setOpd_assessmentform(opdaccess.isOpd_assessmentform());
		loginInfo.setOpd_treatment(opdaccess.isOpd_treatment());
		loginInfo.setOpd_addcharges(opdaccess.isOpd_addcharges());
		loginInfo.setOpd_createinvoice(opdaccess.isOpd_createinvoice());
		loginInfo.setOpd_recordpayment(opdaccess.isOpd_recordpayment());
		
		
		
		Access ipdaccess  = userProfileDAO.getipdaccesss(tempjobtitle);
		
		loginInfo.setIpd_admission(ipdaccess.isIpd_admission());
		loginInfo.setIpd_declaration(ipdaccess.isIpd_declaration());
		loginInfo.setIpd_log(ipdaccess.isIpd_log());
		loginInfo.setIpd_forms(ipdaccess.isIpd_forms());
		loginInfo.setIpd_discharge(ipdaccess.isIpd_discharge());
		loginInfo.setIpd_emr(ipdaccess.isIpd_emr());
		loginInfo.setIpd_prescription(ipdaccess.isIpd_prescription());
		loginInfo.setIpd_investigation(ipdaccess.isIpd_investigation());
		loginInfo.setIpd_reqconsultant(ipdaccess.isIpd_reqconsultant());
		loginInfo.setIpd_nursingcare(ipdaccess.isIpd_nursingcare());
		loginInfo.setIpd_reqblood(ipdaccess.isIpd_reqblood());
		loginInfo.setIpd_autocare(ipdaccess.isIpd_autocare());
		loginInfo.setIpd_treatmentlog(ipdaccess.isIpd_treatmentlog());
		loginInfo.setIpd_addcharges(ipdaccess.isIpd_addcharges());
		loginInfo.setIpd_createinvoice(ipdaccess.isIpd_createinvoice());
		loginInfo.setIpd_recordpayment(ipdaccess.isIpd_recordpayment());
		loginInfo.setIpd_advandref(ipdaccess.isIpd_advandref());
		loginInfo.setIpd_viewaccount(ipdaccess.isIpd_viewaccount());
		loginInfo.setIpd_packages(ipdaccess.isIpd_packages());
		loginInfo.setCancel_admsn(ipdaccess.isCancel_admsn());
		
		//account access
		Access accaess  = userProfileDAO.getAccountaccesss(tempjobtitle);
		
		loginInfo.setAcc_createinvoice(accaess.isAcc_createinvoice());
		loginInfo.setAcc_recordpayment(accaess.isAcc_recordpayment());
		loginInfo.setAcc_viewcreditaccount(accaess.isAcc_viewcreditaccount());
		loginInfo.setAcc_advandref(accaess.isAcc_advandref());
		loginInfo.setAcc_chargeinvoice(accaess.isAcc_chargeinvoice());
		loginInfo.setAcc_addcharges(accaess.isAcc_addcharges());
		loginInfo.setAcc_chargedetails(accaess.isAcc_chargedetails());
		loginInfo.setCash_desk(accaess.isCash_desk());
		loginInfo.setRefund(accaess.isRefund());
		loginInfo.setUnit_price(accaess.isUnit_price());
		
		Access emraccess  = userProfileDAO.getEmraccesss(tempjobtitle);
		loginInfo.setEmr_docs(emraccess.isEmr_docs());
		loginInfo.setEmr_history(emraccess.isEmr_history());
		loginInfo.setEmr_medicine(emraccess.isEmr_medicine());
		loginInfo.setEmr_investigation(emraccess.isEmr_investigation());
		loginInfo.setEmr_pacs(emraccess.isEmr_pacs());
		loginInfo.setEmr_media(emraccess.isEmr_media());
		loginInfo.setEmr_update(emraccess.isEmr_update());
		loginInfo.setEmr_print(emraccess.isEmr_print());
		loginInfo.setEmr_edit(emraccess.isEmr_edit());
		loginInfo.setEmr_delete(emraccess.isEmr_delete());
		
		//client access
		Access clientaccaess  = userProfileDAO.getClientAccesss(tempjobtitle);
		
		loginInfo.setClient_add(clientaccaess.isClient_add());
		loginInfo.setClient_edit(clientaccaess.isClient_edit());
		loginInfo.setClient_delete(clientaccaess.isClient_delete());
		loginInfo.setClient_forms(clientaccaess.isClient_forms());
		loginInfo.setClient_discharge(clientaccaess.isClient_discharge());
		loginInfo.setClient_emai(clientaccaess.isClient_emai());
		loginInfo.setClient_print(clientaccaess.isClient_print());
		loginInfo.setClient_treatment(clientaccaess.isClient_treatment());
		loginInfo.setClient_log(clientaccaess.isClient_log());
		loginInfo.setClient_recordpayment(clientaccaess.isClient_recordpayment());
		loginInfo.setClient_cashdesk(clientaccaess.isClient_cashdesk());
		loginInfo.setClientadvandref(clientaccaess.isClientadvandref());
		loginInfo.setClient_addcharge(clientaccaess.isClient_addcharge());
		loginInfo.setClient_viewaccount(clientaccaess.isClient_viewaccount());
		loginInfo.setClient_emr(clientaccaess.isClient_emr());
		String loginsessionid = loginInfo.getUserId()+session.getId();
		loginInfo.setLoginsessionid(loginsessionid);
		loginInfo.setShow_master(clientaccaess.isShow_master());
		
		//pharmacy hospital access
		UserProfile pharmacymain= userProfileDAO.getPharmacyUserDetails(loginInfo.getClinicid());
		loginInfo.setOpddiscount(pharmacymain.getOpddiscount());
		loginInfo.setIpddiscount(pharmacymain.getIpddiscount());
		loginInfo.setPharpatientdiscount(pharmacymain.getPharpatientdiscount());
		
		/*if(loginInfo.getJobTitle().equals("Pathlab")){
			
			return "pathlab";
		}*/
		
	/*	if(loginInfo.getJobTitle().equals("Medical Store")){
			
			return "medicalstore";
		}
		*/
		
		/*if(clinic.getUserType()==2){
			if(clinic2.isAppointmentBooking() == true){
			//return "gotodashboard";
			return "maindashboard";
		}
		}
		
		if(clinic.getUserType()==4){
			if(clinic2.isAppointmentBooking() == true){
			return "gotoweekdashboard";
				return "maindashboard";
		}
		}*/
		
		/*if(loginInfo.getJobTitle().equals("Medical Store")){
			return "phstaff";
		}*/
		
 
		//vaccination sms
		/*
		 * if(loginInfo.isImmusms()){ DateFormat dateFormat3 = new
		 * SimpleDateFormat("yyyy-MM-dd"); Calendar cal3 = Calendar.getInstance();
		 * cal3.add(Calendar.DATE, 1); String Date1= dateFormat3.format(cal3.getTime());
		 * boolean is1daysms = true; sms_type="Vaccination";
		 * loginInfo.setSms_type(sms_type);
		 * //notAvailableSlotDAO.getAllClientVaccinations(Date1, loginInfo,is1daysms); }
		 * 
		 * //vaccination sms 7 days ago if(loginInfo.isVaci_sms_7day()){ DateFormat
		 * dateFormat3 = new SimpleDateFormat("yyyy-MM-dd"); Calendar cal3 =
		 * Calendar.getInstance(); cal3.add(Calendar.DATE, 7); String Date1=
		 * dateFormat3.format(cal3.getTime()); boolean is1daysms = false;
		 * sms_type="Vaccination"; loginInfo.setSms_type(sms_type);
		 * //notAvailableSlotDAO.getAllClientVaccinations(Date1, loginInfo,is1daysms); }
		 */
		/*
		 * if(true){ DateFormat dateFormat3 = new SimpleDateFormat("dd-MM-yyyy");
		 * Calendar cal3 = Calendar.getInstance(); cal3.add(Calendar.DATE, 1);
		 * SmsService s= new SmsService(); ClientDAO clientDAO= new
		 * JDBCClientDAO(connection); String Date1= dateFormat3.format(cal3.getTime());
		 * ArrayList<Client> followuplist=clientDAO.getallfollowupsToDash("", "", Date1,
		 * Date1); for(Client client:followuplist){ if(client.getState().equals("0")){
		 * //String
		 * message="Your Follow up is Scheduled on "+Date1+" by "+client.getDiaryUser()
		 * +" -"+loginInfo.getClinicName()+""; String message =
		 * "Dear Parents\nFollow up of your child is scheduled on "+Date1+" by "+client.
		 * getDiaryUser()+" - "+loginInfo.getClinicName()+""; String templateid =
		 * userProfileDAO.getSMSTemplateID("FollowUp"); s.sendvaccineSms(message,
		 * client.getMobNo(), loginInfo, new EmailLetterLog(),connection,templateid);
		 * clientDAO.setfollowupsmsflag(""+client.getId()); } } }
		 */
		/*
		 * if(loginInfo.isBdaysms()){ //lokesh bdaysms DateFormat dateFormat2 = new
		 * SimpleDateFormat("dd/MM/yyyy"); Calendar cal2 = Calendar.getInstance();
		 * 
		 * String Date1 = dateFormat2.format(cal2.getTime()); String dob1[]=
		 * Date1.split("/"); String dob= dob1[0]+"/"+dob1[1]+"/"; ClientDAO clientDAO=
		 * new JDBCClientDAO(connection); sms_type="Birthday";
		 * loginInfo.setSms_type(sms_type); if(isTriggerTime("09:00")){
		 * //ArrayList<Client> bdaylist= clientDAO.getbdaylistPatients(dob, loginInfo);
		 * }
		 * 
		 * }
		 */
		
		//loginInfo.setLandLine(clinic3.getLandLine());
		 MasterDAO masterDAO=new JDBCMasterDAO(connection);
	     int smscount=masterDAO.getSMSCount();	
		 loginInfo.setSmscount(smscount);
		 int wsmscount=masterDAO.getWSMSCount();
		 loginInfo.setWsmscount(wsmscount);
		
		/*SmsService s= new SmsService();
		String message = "Dear Parents\nFollow up of your child is scheduled on 09-10-2022 by Dr. Prashant Kedia - "+loginInfo.getClinicName()+"";
		String templateid = userProfileDAO.getSMSTemplateID("FollowUp");
		s.sendvaccineSms(message, "8999673551", loginInfo, new EmailLetterLog(),connection,templateid);
		
		message = AllTemplateAction.getAppointmentSMSTextNew(""+23632,
				Integer.parseInt(""+93081), connection, loginInfo, 77);
		if (loginInfo.getCountry().equals("India")) {
			templateid = userProfileDAO.getSMSTemplateID("Tokan SMS");
			s.sendSms(message, "8999673551", loginInfo, new EmailLetterLog(),templateid);
		}
		message ="Dear Parents\nToken no: 103 You have Booked appointment on 07-10-2022 at 18:30 PM with Dr. Ashok Bhattar Clinic";
		templateid = "1507166521650876547";
		s.sendSms(message, "8999673551", loginInfo, new EmailLetterLog(),templateid);*/
		
		/*SmsService s = new SmsService();
		String templateid="1507166480962871374";
		String message =loginInfo.getClinicName() +" : Dear Parents\nB/O TEST Patient à¤†à¤ªà¤•à¤¾ Hep-B 2 à¤•à¤¾ à¤Ÿà¥€à¤•à¤¾à¤•à¤°à¤£ 26-04-2021 à¤ªà¤° à¤¨à¤¿à¤°à¥�à¤§à¤¾à¤°à¤¿à¤¤ à¤¹à¥ˆ| Phone: "+loginInfo.getLandLine()+" ";
		s.sendvaccineSms(message, "8999673551", loginInfo, new EmailLetterLog(),connection,templateid);
		
		String messageapmuser = AllTemplateAction.getAppointmentSMSTextToapmUser(""+68641,
				140025, connection, loginInfo, 1);
		templateid = "1507166480955640452";
		s.sendSms(messageapmuser, "8999673551", loginInfo, new EmailLetterLog(),templateid);*/
		
		/*SmsService s = new SmsService();
		//SMS TEST Success
		String message="";
		String templateid="";
		//done
		message ="Mr. TEST Patient à¤•à¥‹ à¤¬à¤¾à¤² à¤—à¥‹à¤ªà¤¾à¤² à¤¹à¥‰à¤¸à¥�à¤ªà¤¿à¤Ÿà¤² à¤ªà¤°à¤¿à¤µà¤¾à¤° à¤•à¥€ à¤“à¤° à¤¸à¥‡ à¤œà¤¨à¥�à¤®à¤¦à¤¿à¤¨ à¤•à¥€ à¤¹à¤¾à¤°à¥�à¤¦à¤¿à¤• à¤¶à¥�à¤­à¤•à¤¾à¤®à¤¨à¤¾à¤�à¤�. 'à¤¸à¥�à¤µà¤¸à¥�à¤¥ à¤°à¤¹à¥‡à¤‚, à¤¸à¥�à¤°à¤•à¥�à¤·à¤¿à¤¤ à¤°à¤¹à¥‡à¤‚' 0771-4225600, 7869920001 www.balgopalhospital.com";
		templateid = userProfileDAO.getSMSTemplateID("Birthday");
		s.sendvaccineSms(message, "9730706757,8999673551,8788583834", loginInfo, new EmailLetterLog(),connection,templateid);*/
		
		/*//done
		message="Mr. TEST User, your user ID: test and password: Test@123 from BALGOPAL CHILDREN HOSPITAL (Yuvi Care)";
		templateid = userProfileDAO.getSMSTemplateID("User Registration");
		s.sendSms(message, "9730706757,8999673551,8788583834", loginInfo, new EmailLetterLog(),templateid);
		
		//Not Working
		//done
		message="BALGOPAL CHILDREN HOSPITAL : B/O TEST Patient à¤†à¤ªà¤•à¤¾  Hep-B 2 à¤•à¤¾ à¤Ÿà¥€à¤•à¤¾à¤•à¤°à¤£ 26-04-2021 à¤ªà¤° à¤¨à¤¿à¤°à¥�à¤§à¤¾à¤°à¤¿à¤¤ à¤¹à¥ˆ| Phone: (0771) 4225600, 4225610";
		templateid = userProfileDAO.getSMSTemplateID("Vaccination");
		s.sendvaccineSms(message, "9730706757,8999673551,8788583834", loginInfo, new EmailLetterLog(),connection,templateid);
		
		//done
		message="Thanks Mr. TEST Patient for payment of Rupees 1000.00 from- BALGOPAL CHILDREN HOSPITAL";
		templateid = userProfileDAO.getSMSTemplateID("Payment");
		s.sendSms(message, "9730706757,8999673551,8788583834", loginInfo, new EmailLetterLog(),templateid);
		
		//done
		message="BALGOPAL CHILDREN HOSPITAL Appointment No: 101 PT Name: Mr. TEST Patient Date/Time: 26-04-2021/12:10 AM";
		templateid = userProfileDAO.getSMSTemplateID("Appointment SMS DR");
		s.sendSms(message, "9730706757,8999673551,8788583834", loginInfo, new EmailLetterLog(),templateid);
		
		//done
		message="Token no: 10 You have booked an appointment on 26-04-2021 at 12:10 AM with BALGOPAL CHILDREN HOSPITAL";
		templateid = userProfileDAO.getSMSTemplateID("Tokan SMS");
		s.sendSms(message, "9730706757,8999673551,8788583834", loginInfo, new EmailLetterLog(),templateid);
		
		//done
		message="You have booked an appointment on 26-04-2021 at 12:10 AM with BALGOPAL CHILDREN HOSPITAL";
		templateid = userProfileDAO.getSMSTemplateID("Appointment SMS");
		s.sendSms(message, "9730706757,8999673551,8788583834", loginInfo, new EmailLetterLog(),templateid);
		
		//done
		message="Your Follow up is Scheduled on 26-04-2021 by Mr. TEST User -BALGOPAL CHILDREN HOSPITAL";
		templateid = userProfileDAO.getSMSTemplateID("FollowUp");
		s.sendSms(message, "9730706757,8999673551,8788583834", loginInfo, new EmailLetterLog(),templateid);*/
		/*SmsService s = new SmsService();
		String msg=loginInfo.getRegAddress();
		s.sendSms(msg, "8999673551", loginInfo, new EmailLetterLog());
		msg =loginInfo.getRegAddress();
		s.sendvaccineSms(msg, "8999673551", loginInfo, new EmailLetterLog(), connection);*/
		/*SmsService s = new SmsService();
		String msg="testing by akash";
		s.sendSms(msg, "8999673551", loginInfo, new EmailLetterLog());
		msg ="à¤†à¤ªà¤•à¤¾  à¤•à¤¾ à¤Ÿà¥€à¤•à¤¾à¤•à¤°à¤£ à¤ªà¤° à¤¨à¤¿à¤°à¥�à¤§à¤¾à¤°à¤¿à¤¤ à¤¹à¥ˆ|";
		s.sendvaccineSms(msg, "9764434837", loginInfo, new EmailLetterLog(), connection);*/
		  //SmsService s = new SmsService();
		  //String msg="&#2357;&#2352;&#2381;&#2350;&#2366; &#2344;&#2352;&#2381;&#2360;&#2367;&#2306;&#2327; &#2361;&#2379;&#2350; &#2350;&#2343;&#2381;&#2351;&#2375; &#2358;&#2369;&#2327;&#2352; &#2310;&#2339;&#2367; &#2348;&#2368;&#2346;&#2368; &#2330;&#2381;&#2351;&#2366; &#2324;&#2359;&#2343;&#2367;&#2357;&#2352; &#2407;&#2406;% &#2360;&#2369;&#2335; &#2310;&#2361;&#2375;.";
		  //s.sendSms(msg, "8999673551,7378744328", loginInfo, new EmailLetterLog());
		  //String msg="Ã Â¤Â¶Ã Â¥ï¿½Ã Â¤â€”Ã Â¤Â° Ã Â¤â€ Ã Â¤Â£Ã Â¤Â¿ Ã Â¤Â¬Ã Â¥â‚¬Ã Â¤ÂªÃ Â¥â‚¬ Ã Â¤Å¡Ã Â¥ï¿½Ã Â¤Â¯Ã Â¤Â¾ Ã Â¤â€�Ã Â¤Â·Ã Â¤Â¾Ã Â¤Â§Ã Â¤Â¿Ã Â¤ÂµÃ Â¤Â° Ã Â¥Â§Ã Â¥Â¦% Ã Â¤Â¸Ã Â¥ï¿½Ã Â¤Å¸ Ã Â¤â€ Ã Â¤Â¹Ã Â¥â€¡.";
		  //String msg ="Ã Â¤ÂµÃ Â¤Â°Ã Â¥ï¿½Ã Â¤Â®Ã Â¤Â¾";
		  //String msg ="&#2357;&#2352;&#2381;&#2350;&#2366; &#2344;&#2352;&#2381;&#2360;&#2367;&#2306;&#2327; &#2361;&#2379;&#2350; &#2350;&#2343;&#2381;&#2351;&#2375; &#2358;&#2369;&#2327;&#2352; &#2310;&#2339;&#2367; &#2348;&#2368;&#2346;&#2368; &#2330;&#2381;&#2351;&#2366; &#2324;&#2359;&#2343;&#2367;&#2357;&#2352; &#2407;&#2406;% &#2360;&#2369;&#2335; &#2310;&#2361;&#2375;.";
		  //String msg ="#2357;#2352;#2381;#2350;#2366; #2344;#2352;#2381;#2360;#2367;#2306;#2327;";
		  //String msg="testing by akash";
		  //s.sendSms(msg, "8999673551", loginInfo, new EmailLetterLog());
		  //s.sendvaccineSms(msg, "9764434837", loginInfo, new EmailLetterLog(), connection);
		  //s.sendSms("Thanks Master Test Yuvicare for payment of Rupees 1500.00 from- BALGOPAL CHILDREN HOSPITAL", "9764434837", loginInfo, new EmailLetterLog());
		  
		/*SmsService s = new SmsService();
		String message ="";
		
		message="BALGOPAL CHILDREN HOSPITAL : 123 is OTP for password change from YUVICARE.";
		String templateid="1507163101549461938";
		s.sendSms(message, "9764434837", loginInfo, new EmailLetterLog(),templateid);
		
        message="Dr. Ashok Bhattar Clinic : 123 is OTP for password change from BGCLINIC.";
        loginInfo.setClinicUserid("raibhattar");
        templateid="1507163101559389511";
        s.sendSms(message, "9764434837", loginInfo, new EmailLetterLog(),templateid);
		
		message="Dr. Prachi Bhattar's Skin, Hair Clinic & Cosmetology Centre : 123 is OTP for password change from YUVICARE.";
		loginInfo.setClinicUserid("raiprachi");
		templateid="1507163101554691948";
        s.sendSms(message, "9764434837", loginInfo, new EmailLetterLog(),templateid);
		
		*/
		
		  //edit charges access
		  UserProfile access2=userProfileDAO.getMainAccessByUserid(loginInfo.getUserId());
		  String editcharge=access2.getEditcharges();
		  boolean editchargesacs=false;
		  if(DateTimeUtils.isNull(editcharge).equals("1")){
			  editchargesacs=true;
		  }
		  loginInfo.setEditchargesacs(editchargesacs);
		  
		  String fromtime=clinic2.getFromtime();
		  String totime=clinic2.getTotime();
		  
		  boolean misaccess=false;
		  if(fromtime!=null){
		  if(fromtime.equals(""))
		  {
			  misaccess=true;
		  }
		  }else {
			  misaccess=true;
		}
		  if (totime!=null) {
			if (totime.equals("")) {
				misaccess=true;
			}
		}else {
			misaccess=true;	
		}
		  if(!misaccess){
		  int ft=0,tt=0,ct=0;
		  ft=Integer.parseInt(fromtime);
		  tt=Integer.parseInt(totime);
		  DateFormat d = new SimpleDateFormat("HH");
		    Calendar cal2 = Calendar.getInstance();
		    cal2.add(Calendar.DATE, 1);
		    String currenttm = d.format(cal2.getTime());
		    ct=Integer.parseInt(currenttm);
		    if(ft>=ct && ct<=tt){
		    	misaccess=true;
		    }
		  }
		  
		  
		  
		  
		  //New Clinic Access   03/11/2020
		  Clinic clinicaccess=clinicDAO.getNewClinicAccess();
		  loginInfo.setSaimed(clinicaccess.isSaimed());
		  loginInfo.setKalmegha(clinicaccess.isKalmegha());
		  loginInfo.setAustralia(clinicaccess.isAustralia());
		  //loginInfo.setBalgopal(clinicaccess.isBalgopal());
		  loginInfo.setLmh(clinicaccess.isLmh());
		  loginInfo.setOpd_chat(clinicaccess.isOpd_chat());
		  loginInfo.setMatrusevasang(clinicaccess.isMatrusevasang());
		  loginInfo.setPhysio(clinicaccess.isPhysio());
		  loginInfo.setAmravati(clinicaccess.isAmravati());
		  loginInfo.setSimpliclinic(clinicaccess.isSimpliclinic());
		  loginInfo.setInfinyte(clinicaccess.isInfinyte());
		  loginInfo.setVrundawan(clinicaccess.isVrundawan());
		  loginInfo.setBams1(clinicaccess.isBams1());
		  loginInfo.setMbbs(clinicaccess.isMbbs());
		  loginInfo.setUhiddatewise(clinicaccess.isUhiddatewise());
		  loginInfo.setAyushman(clinicaccess.isAyushman());
		  loginInfo.setTime_utility_report(clinicaccess.isTime_utility_report());
		  loginInfo.setParihar(clinicaccess.isParihar());
		  loginInfo.setPhysio_ipd(clinicaccess.isPhysio_ipd());
		  loginInfo.setSjivh(clinicaccess.isSjivh());
		  loginInfo.setTotehosp(clinicaccess.isTotehosp());
		  loginInfo.setSramhosp(clinicaccess.isSramhosp());
		  loginInfo.setMarkhosp(clinicaccess.isMarkhosp());
		  loginInfo.setAfitech(clinicaccess.isAfitech());
		  loginInfo.setNabh(clinicaccess.isNabh());
		  loginInfo.setClinic_small(clinicaccess.isClinic_small());
		  if(DateTimeUtils.isNull(clinicaccess.getRegional_lang()).equals("")){
			  clinicaccess.setRegional_lang("en");
		  }
		  loginInfo.setHospital_language(clinicaccess.getRegional_lang());
		 
		  if(DateTimeUtils.isNull(lang).equals("")){
			  lang="en";
		  }
		  clinicaccess.setRegional_lang(lang);
		  loginInfo.setRegional_lang(clinicaccess.getRegional_lang());
		  loginInfo.setPackage_investigation(clinicaccess.isPackage_investigation());
		  loginInfo.setPatient_category(clinicaccess.getPatient_category());
		  loginInfo.setCommon_clinic_db(clinicaccess.isCommon_clinic_db());
		  
		    loginInfo.setMisaccess(misaccess);
		}
		    if(actionType.equals("doctoropd")){
				loginInfo.setOpd_user_vid_access(true);
				loginInfo.setDr_opd_vid(true);	
			}
			if(actionType.equals("recepopd")){
				loginInfo.setOpd_user_vid_access(true);
				loginInfo.setDr_opd_vid(false);	
				loginInfo.setAppreception(true);
			}
			
			if(actionType.equals("todoctoripd")){
				loginInfo.setToDoctorIpd(true);
			}
			 session.setAttribute(loginInfo.getUserId(), loginInfo);
			 if(loginInfo.isSmallClinic() && loginInfo.isCommon_clinic_db()){
				 loginInfo.setCommon_db_clinic(true);
			 }
			 if(loginInfo.isSmallClinic() && loginInfo.isCommon_clinic_db() && loginInfo.getSuperadminid()!=1){
				 //If small clinic true and common clinic db true, 
				 //it means clinic or ambulance user which having common database share
				 if(loginInfo.isOpd_video_icon_show() && loginInfo.getJobTitle().equals("Practitioner")){
					 session.setAttribute("logintimedrid", ""+loginInfo.getId());
				 }
				 return "referPatientDashboard";
			 }
			 if(loginInfo.isSmallClinic() && loginInfo.getSuperadminid()!=1 && !loginInfo.isIsclinic_use_yuvi()){
				 //if small clinic true and isclinic_use_yuvi false 
				 //then its means it have separate database but don't have all yuvicare access only to PRO module
				 return "referPatientDashboard";
				 //if small clinic true and isclinic_use_yuvi "true" 
				 //then its means it have separate database but have all yuvicare access so user will access all yuvicare and PRO module 
			 }
			 
			 
			 if(loginInfo.isSjivh()) {
				 String sms_type="";
				  if(loginInfo.isBdaysms()){ 
					  //lokesh bdaysms 
					  DateFormat dateFormat2 = new
				      SimpleDateFormat("dd/MM/yyyy"); 
					  Calendar cal2 = Calendar.getInstance();
				      String Date1 = dateFormat2.format(cal2.getTime()); 
				      String dob1[]= Date1.split("/"); 
				      String dob= dob1[0]+"/"+dob1[1]+"/"; 
				      ClientDAO clientDAO=new JDBCClientDAO(connection);
				      sms_type="Birthday";
				      loginInfo.setSms_type(sms_type); 
				      if(isTriggerTime("09:00")){
				      ArrayList<Client> bdaylist= clientDAO.getbdaylistPatients(dob, loginInfo);
				  }
				  
				  }
				  
				//vaccination sms
					if(loginInfo.isImmusms()){
						DateFormat dateFormat3 = new SimpleDateFormat("yyyy-MM-dd");
						Calendar cal3 = Calendar.getInstance();
						cal3.add(Calendar.DATE, 1); 
						String Date1= dateFormat3.format(cal3.getTime());
						boolean is1daysms = true;
						sms_type="Vaccination";
						loginInfo.setSms_type(sms_type);
						notAvailableSlotDAO.getAllClientVaccinations(Date1, loginInfo,is1daysms);
					}
					
					//vaccination sms 7 days ago
					if(loginInfo.isVaci_sms_7day()){
						DateFormat dateFormat3 = new SimpleDateFormat("yyyy-MM-dd");
						Calendar cal3 = Calendar.getInstance();
						cal3.add(Calendar.DATE, 7); 
						String Date1= dateFormat3.format(cal3.getTime());
						boolean is1daysms = false;
						sms_type="Vaccination";
						loginInfo.setSms_type(sms_type);
						notAvailableSlotDAO.getAllClientVaccinations(Date1, loginInfo,is1daysms);
					}
				  
				//deworming sms
					if(loginInfo.isImmusms()){
						DateFormat dateFormat3 = new SimpleDateFormat("yyyy-MM-dd");
						Calendar cal3 = Calendar.getInstance();
						cal3.add(Calendar.DATE, 1); 
						String Date1= dateFormat3.format(cal3.getTime());
						boolean is1daysms = true;
						sms_type="deworming";
						loginInfo.setSms_type(sms_type);
						notAvailableSlotDAO.getAllClientdeworming(Date1, loginInfo,is1daysms);
					}
					//deworming sms 7 days ago
					if(loginInfo.isVaci_sms_7day()){
						DateFormat dateFormat3 = new SimpleDateFormat("yyyy-MM-dd");
						Calendar cal3 = Calendar.getInstance();
						cal3.add(Calendar.DATE, 1); 
						String Date1= dateFormat3.format(cal3.getTime());
						boolean is1daysms = true;
						sms_type="deworming";
						loginInfo.setSms_type(sms_type);
						notAvailableSlotDAO.getAllClientdeworming(Date1, loginInfo,is1daysms);
					}
					
				
			}
			 
			 
			 FinderDAO finderDAO = new JDBCFinderDAO(connection);
			 boolean smallClinic = loginInfo.isSmallClinic();
			 boolean isHospital = true;
			 if(!smallClinic){
				isHospital = finderDAO.checkLoginByUserOrHosp(loginInfo.getUserId());
				if(!isHospital && loginInfo.getSuperadminid()!=1){
					return "referPatientDashboard";
				}
			 }
			 loginInfo.setReferHospital(isHospital);
			 
			 int ipdphysio=finderDAO.getIpdphysioaccess(loginInfo.getId());
			 loginInfo.setIpdphysio(""+ipdphysio);
				/*
				 * if(true){ DateFormat dateFormat3 = new SimpleDateFormat("dd-MM-yyyy");
				 * Calendar cal3 = Calendar.getInstance(); cal3.add(Calendar.DATE, 1);
				 * SmsService s= new SmsService(); ClientDAO clientDAO= new
				 * JDBCClientDAO(connection); String Date1= dateFormat3.format(cal3.getTime());
				 * ArrayList<Client> followuplist=clientDAO.getallfollowupsToDash("", "", Date1,
				 * Date1); for(Client client:followuplist){ String message ="";
				 * if(client.getState().equals("0")){ //String
				 * message="Your Follow up is Scheduled on "+Date1+" by "+client.getDiaryUser()
				 * +" -"+loginInfo.getClinicName()+""; if(loginInfo.isParihar()) {
				 * message="प्रिय "+client.getClientName()+" आपका फालोअप अपाइंटमेंट दिनांक "
				 * +Date1+" को डाॅ. "+client.getDiaryUser()
				 * +", परिहार हास्पिटल कवर्धा के साथ निर्धारित है।"; }else { message =
				 * "Dear Parents\nFollow up of your child is scheduled on "+Date1+" by "+client.
				 * getDiaryUser()+" - "+loginInfo.getClinicName()+""; } String templateid =
				 * userProfileDAO.getSMSTemplateID("FollowUp"); s.sendvaccineSms(message,
				 * client.getMobNo(), loginInfo, new EmailLetterLog(),connection,templateid);
				 * clientDAO.setfollowupsmsflag(""+client.getId()); } } }
				 */
		  if(actionType.equals("todoctoripd")){
			  return "todoctoripd";
		  }
		  if(branchForm.getActiontype().equals("doctoropd")||actionType.equals("recepopd")){
			  
			  return "doctormob";
		  }
		 if(globactiontype.equals("mobemrdoc")){
			 return "mobemrdoc";
		 }
		  if(actionType.equals("mobemr")){
			  return "mobemr";
		  }
		  if(actionType.equals("doctoremr")){
			  return "doctoremr";
		  }
		  if(loginInfo.getIpdphysio().equals("1")) {
			  return "physioipd";
		  }
		  if(loginInfo.isOpd_video_icon_show() && loginInfo.getJobTitle().equals("Practitioner") && loginInfo.getIpdphysio().equals("0")){
			  session.setAttribute("logintimedrid", ""+loginInfo.getId());
			  return "myopddashboard";
		  }
		  if(loginInfo.isAfitech()) {
			  return "marketing";
		  }
		// update login status
					/*if((clinic.getUserType()!=2 || !clinic.getJobtitle().equals("Admin")) && loginInfo.isLmh()){
						int res = clinicDAO.updateLoginStatus(branchForm.getUserId());
					}*/
	
		}catch (Exception e) {
 			e.printStackTrace();
 			log.debug("##########################"+e.getMessage() + "-" + url);
 			/*DatabaseMetaData dmd = connection.getMetaData();
 			 url = dmd.getURL() + " username = " + dmd.getUserName();
 			log.debug("##########################"+url);
 			log.debug("@@@@@@@@@"+Constants.DB_HOST + "/" + e.getMessage());
 			 ln(url);*/
 			return "error";
		}
		
		finally{
			if(connection==null){
				return "error";
			}
			connection.close();
			
		}
		
		
		
		return "maindashboard";
	}
	
	
	public String input() {
		
		
		  if(session.getAttribute("logininfo")!=null){
			
			  return SUCCESS;
		  } else {
			  return INPUT;
		  }
	}
	
	
/*	
	public String lockScreenPage(){
		
		Connection connection = null;
		try{
			connection = Connection_provider.getconnection();
			ClinicDAO clinicDAO = new JDBCClinicDAO(connection);
			LoginInfo loginInfo = (LoginInfo) session.getAttribute("loginInfo");
			String userId = loginInfo.getUserId();
			String encPassword = Encryption.encryptSHA(branchForm.getPassword());
			Clinic clinic = clinicDAO.getuser(userId);
			
			if(!encPassword.equals(clinic.getPassword())){
				addActionError(getText("error.user.authfailed"));
				return "lockscreen";
			}
			
		}catch(Exception e){
			
		}
		
		return "gotodashboard";
	}
	*/

	public void validate() {
	    	
    	 /* Do not use 'else if' since it will cause to show only one error at a time */
    	 // If user is null or empty add error in field errors
		 if ( branchForm.getUserId() == null || branchForm.getUserId().length() == 0) {
	            addFieldError("userId", getText("error.userid.required") );	// set error message form property file
		 }
		 // If password is null or empty add error to field errors
	     if ( branchForm.getPassword() == null ||  branchForm.getPassword().length() == 0) {
	            addFieldError("password", getText("error.password.required")); 	// set error message form property file
	     }
    }

	public BranchForm getModel() {
		// TODO Auto-generated method stub
		return branchForm;
	}

	

	public boolean isTriggerTime(String triggerTime){
		
		boolean flag=false;
		try {
			DateFormat ss= new SimpleDateFormat("dd:MM:yyyy HH:mm");
			Date checkTime= ss.parse( new SimpleDateFormat("dd:MM:yyyy").format(new Date())+" "+triggerTime);
			Date nowTime= new Date();
			if(nowTime.after(checkTime)){
				flag= true;
			}else{
				flag= false;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}
	public String show() throws Exception{
		
		try {
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		return "showdashboard";
	}
	
	

}
