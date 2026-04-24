package com.apm.ThirdParties.web.action;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.a.a.a.a.a.b;
import com.apm.Appointment.eu.bi.AppointmentTypeDAO;
import com.apm.Appointment.eu.blogic.jdbc.JDBCAppointmentTypeDAO;
import com.apm.Appointment.eu.entity.AppointmentType;
import com.apm.DiaryManagement.eu.bi.AppointmentDiaryReportDAO;
import com.apm.DiaryManagement.eu.bi.ClientDAO;
import com.apm.DiaryManagement.eu.blogic.jdbc.JDBCAppointmentDiaryReportDAO;
import com.apm.DiaryManagement.eu.blogic.jdbc.JDBCClientDAO;
import com.apm.DiaryManagement.eu.entity.AppointmentDiaryReport;
import com.apm.DiaryManagement.eu.entity.Client;
import com.apm.DiaryManagement.web.form.AppointmentDiaryReportForm;
import com.apm.Emr.eu.bi.EmrDAO;
import com.apm.Emr.eu.bi.InvestigationDAO;
import com.apm.Emr.eu.blogic.jdbc.JDBCEmrDAO;
import com.apm.Emr.eu.blogic.jdbc.JDBCInvestigationDAO;
import com.apm.Inventory.eu.bi.InventoryCatalogueDAO;
import com.apm.Inventory.eu.bi.InventoryVendorDAO;
import com.apm.Inventory.eu.blogic.jdbc.JDBCInventoryVendorDAO;
import com.apm.Ipd.eu.bi.BedDao;
import com.apm.Ipd.eu.blogic.jdbc.JDBCBedDao;
import com.apm.Ipd.eu.entity.Bed;
import com.apm.Master.eu.bi.DischargeOutcomeDAO;
import com.apm.Master.eu.bi.MasterDAO;
import com.apm.Master.eu.blogic.jdbc.JDBCDischargeOutcomeDAO;
import com.apm.Master.eu.blogic.jdbc.JDBCMasterDAO;
import com.apm.Master.eu.entity.Master;
import com.apm.Registration.eu.entity.Clinic;
import com.apm.Registration.eu.entity.Location;
import com.apm.ThirdParties.eu.bi.ThirdPartyDAO;
import com.apm.ThirdParties.eu.blogic.jdbc.JDBCThirdPartyDAO;
import com.apm.ThirdParties.eu.entity.GP;
import com.apm.ThirdParties.eu.entity.ThirdParty;
import com.apm.ThirdParties.web.form.DynamicAppointment;
import com.apm.ThirdParties.web.form.ThirdPartyForm;
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

public class ThirdPartyAction extends BaseAction implements Preparable, ModelDriven<ThirdPartyForm> {

	HttpServletRequest request = (HttpServletRequest) ActionContext.getContext().get(ServletActionContext.HTTP_REQUEST);
	HttpServletResponse response = (HttpServletResponse)ActionContext.getContext().get(ServletActionContext.HTTP_RESPONSE);
	HttpSession session = request.getSession(true);
	
	ThirdPartyForm thirdPartyForm = new ThirdPartyForm();
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
	
		
		Connection connection = null;
		try{
			connection = Connection_provider.getconnection();
			ThirdPartyDAO  thirdPartyDAO = new JDBCThirdPartyDAO(connection);
			ArrayList<ThirdParty>thirdPartyTypeList = thirdPartyDAO.getThirdPartyList();
			
			thirdPartyForm.setThirdPartyTypeList(thirdPartyTypeList);
			
			thirdPartyForm.setThirdPartyName(thirdPartyForm.getThirdPartyName());
			int totalCount = thirdPartyDAO.getTotalThirdPartiesCount(loginInfo.getId());
			pagination.setPreperties(totalCount);
			ArrayList<ThirdParty>thirdPartyDetailList = thirdPartyDAO.getThirdPartyDetailsList(loginInfo.getId(),pagination);
			pagination.setPage_records(thirdPartyDetailList.size());
			thirdPartyForm.setTotalRecords(totalCount);
			thirdPartyForm.setPagerecords(Integer.toString(pagination.getPage_records()));
			thirdPartyForm.setThirdPartyDetailList(thirdPartyDetailList);
			
		}catch (Exception e) {
			e.printStackTrace();
		}finally{
			
			connection.close();
		}
	 
		return SUCCESS;
	}



public String add(){
	if(!verifyLogin(request)){
		return "login";
	}
	try {
		session.setAttribute("thirdpartyeditappointmenttypeid", "0");
		String type = request.getParameter("type");
		thirdPartyForm.setType(type);
		
		String isFromPG = request.getParameter("isFromPG");
		if(DateTimeUtils.isNull(isFromPG).equals("")){
			isFromPG = "0";
		}
		thirdPartyForm.setIsFromPG(isFromPG);
		if(DateTimeUtils.isNull(isFromPG).equals("1")){
			String mastername= (String)session.getAttribute("mastername");;
			thirdPartyForm.setMastername(mastername);
		}
		thirdPartyForm.setThirdPartyTypeList(new ArrayList<>());
		thirdPartyForm.setTpnameList(new ArrayList<>());
		
		String isfromadd = request.getParameter("isfromadd");
		/*if(DateTimeUtils.isNull(isfromadd).equals("1")){*/
			Connection connection = Connection_provider.getconnection();
			String patientType = request.getParameter("patientType");
			patientType = DateTimeUtils.isNull(patientType);
			ClientDAO clientDAO = new JDBCClientDAO(connection);
			ThirdPartyDAO thirdPartyDAO = new JDBCThirdPartyDAO(connection);
			ArrayList<ThirdParty> thirdPartyTypeList = new ArrayList<>();
			
			if(!patientType.equals("")){
				thirdPartyTypeList = clientDAO.getPayerTypeList(patientType);
			}
			thirdPartyForm.setThirdPartyTypeList(thirdPartyTypeList);
			thirdPartyForm.setPatientType(patientType);
			
			String payerType = request.getParameter("payerType");
			payerType = DateTimeUtils.isNull(payerType);
			ArrayList<ThirdParty> tpnameList = new ArrayList<>();
			if(!payerType.equals("")){
				tpnameList = thirdPartyDAO.getTPNameListNew(payerType);
			}
			thirdPartyForm.setTpnameList(tpnameList);
		/*}*/
			
	} catch (Exception e) {
		e.printStackTrace();
	}
	
	return "addThirdPartyDetailData";
}


public String save()throws SQLException{
	if(!verifyLogin(request)){
		return "login";
	}
	String type = thirdPartyForm.getType();
	ThirdParty thirdParty = new ThirdParty();
	
	//Personal Details field
	thirdParty.setType(type);
	thirdParty.setName(thirdPartyForm.getName());
	thirdParty.setTelephoneLine(thirdPartyForm.getTelephoneLine());
	thirdParty.setEmail(thirdPartyForm.getEmail());
	thirdParty.setGptown(thirdPartyForm.getGptown());
	thirdParty.setGpaddress(thirdPartyForm.getGpaddress());
	
	//Company Details field	
	thirdParty.setCompanyName(thirdPartyForm.getCompanyName());
	thirdParty.setAddress(thirdPartyForm.getAddress());
	thirdParty.setTown(thirdPartyForm.getTown());
	thirdParty.setCountry(thirdPartyForm.getCountry());
	thirdParty.setCompnyTelephone(thirdPartyForm.getCompnyTelephone());
	thirdParty.setCompnyEmail(thirdPartyForm.getCompnyEmail());
	
	//Account Setting
	
	thirdParty.setOutInvoiceLimit(thirdPartyForm.getOutInvoiceLimit());
	thirdParty.setCreditDuration(thirdPartyForm.getCreditDuration());
	thirdParty.setAccountWarningLimit(thirdPartyForm.getAccountWarningLimit());
	thirdParty.setCreditReminderDuration(thirdPartyForm.getCreditReminderDuration());
	thirdParty.setDnaLimit(thirdPartyForm.getDnaLimit());
	
	//Contact Preference
	//thirdParty.setConfContactEmail(thirdPartyForm.getConfContactEmail());
	//thirdParty.setDnaContactEmail(thirdPartyForm.getDnaContactEmail());
	thirdParty.setClinicId(loginInfo.getId());
	
	Connection connection = null;
	try{
		connection = Connection_provider.getconnection();
		ThirdPartyDAO  thirdPartyDAO = new JDBCThirdPartyDAO(connection);
		
		int result = thirdPartyDAO.saveData(type,thirdParty);
		
		thirdPartyForm.setMessage("Third Party Type Added Sucessfully!!");
		addActionMessage("Third Party Details Added Sucessfully!!");
		setFormData();
		
	}catch (Exception e) {
	e.printStackTrace();
	}finally{
	
	connection.close();
	}

	return SUCCESS;
	
}


public String addDetail(){
	if(!verifyLogin(request)){
		return "login";
	}
	String type = request.getParameter("type");
	thirdPartyForm.setType(type);
	return "addThirdPartyDetail";
}

public String saveDetails() throws SQLException{
	if(!verifyLogin(request)){
		return "login";
	}
	String type = thirdPartyForm.getType();
	ThirdParty thirdParty = new ThirdParty();
	//Personal Details field
	
	thirdParty.setType(type);
	thirdParty.setName(thirdPartyForm.getName());
	thirdParty.setSalutation(thirdPartyForm.getSalutation());
	thirdParty.setTitle(thirdPartyForm.getTitle());
	thirdParty.setDepartment(thirdPartyForm.getDepartment());
	thirdParty.setSearchName(thirdPartyForm.getSearchName());
	thirdParty.setTelephoneLine(thirdPartyForm.getTelephoneLine());
	thirdParty.setEmail(thirdPartyForm.getEmail());
	thirdParty.setEmailCc(thirdPartyForm.getEmailCc());
	thirdParty.setNotes(thirdPartyForm.getNotes());
	thirdParty.setCompanyDetails(thirdPartyForm.getCompanyDetails());
	
	//Company Details field
	
	thirdParty.setCompanyName(thirdPartyForm.getCompanyName());
	thirdParty.setAddress(thirdPartyForm.getAddress());
	thirdParty.setTown(thirdPartyForm.getTown());
	thirdParty.setCountry(thirdPartyForm.getCountry());
	thirdParty.setPostcode(thirdPartyForm.getPostcode());
	thirdParty.setCompnyTelephone(thirdPartyForm.getCompnyTelephone());
	thirdParty.setFax(thirdPartyForm.getFax());
	thirdParty.setWeb(thirdPartyForm.getWeb());
	thirdParty.setReferenceNo(thirdPartyForm.getReferenceNo());
	thirdParty.setCompnyEmail(thirdPartyForm.getCompnyEmail());
	
	//Account Setting
	
	thirdParty.setWarningMsg(thirdPartyForm.getWarningMsg());
	thirdParty.setAccountsNotes(thirdPartyForm.getAccountsNotes());
	thirdParty.setAccountMustBeInCredit(thirdPartyForm.isAccountMustBeInCredit());
	//thirdParty.setAccountCreditLimit(thirdPartyForm.getAccountCreditLimit());
	thirdParty.setAccountWarningLimit(thirdPartyForm.getAccountWarningLimit());
	thirdParty.setDnaLimit(thirdPartyForm.getDnaLimit());
	thirdParty.setOutInvoiceLimit(thirdPartyForm.getOutInvoiceLimit());
	thirdParty.setCreditDuration(thirdPartyForm.getCreditDuration());
	thirdParty.setCreditReminderDuration(thirdPartyForm.getCreditReminderDuration());
	//Contact Preference
	
	thirdParty.setDefaultApmtBookingConfmTemp(thirdPartyForm.getDefaultApmtBookingConfmTemp());
	thirdParty.setConfContactEmail(thirdPartyForm.getConfContactEmail());
	thirdParty.setDefaultApmtDnaTemp(thirdPartyForm.getDefaultApmtDnaTemp());
	thirdParty.setDnaContactEmail(thirdPartyForm.getDnaContactEmail());
	thirdParty.setClinicId(loginInfo.getId());
	
	Connection connection = null;
	try{
		connection = Connection_provider.getconnection();
	ThirdPartyDAO  thirdPartyDAO = new JDBCThirdPartyDAO(connection);
	
	int result = thirdPartyDAO.saveDetails(type,thirdParty);
	
	thirdPartyForm.setMessage("Third Party Type Added Sucessfully!!");
	addActionMessage("Third Party Details Added Sucessfully!!");
	setFormData();
	
	}catch (Exception e) {
	e.printStackTrace();
	}finally{
	
	connection.close();
	}

	return SUCCESS;
}

public String saveTPData() throws SQLException{
	if(!verifyLogin(request)){
		return "login";
	}
	String type = thirdPartyForm.getType();
	ThirdParty thirdParty = new ThirdParty();
	//TP Details field
	String typename = thirdPartyForm.getTypeName();
	String patienttype=request.getParameter("patientType");
	
	
	thirdParty.setPatientType(patienttype);
	thirdParty.setCity(thirdPartyForm.getCity());
	thirdParty.setState(thirdPartyForm.getState());
	
	thirdParty.setType(type);
	String companyname = thirdPartyForm.getCompanyName();
	thirdParty.setShortname(thirdPartyForm.getShortname());
	thirdParty.setTypeName(thirdPartyForm.getTypeName());
	thirdParty.setCompanyName(thirdPartyForm.getCompanyName());
	thirdParty.setTelephoneLine(thirdPartyForm.getTelephoneLine());
	thirdParty.setCompnyEmail(thirdPartyForm.getCompnyEmail());
	thirdParty.setAddress(thirdPartyForm.getAddress());
	thirdParty.setTown(thirdPartyForm.getTown());
	thirdParty.setPostcode(thirdPartyForm.getPostcode());
	thirdParty.setCountry(thirdPartyForm.getCountry());
	thirdParty.setEmailCc(thirdPartyForm.getEmailCc());
	thirdParty.setSecondLineAddress(thirdPartyForm.getSecondLineAddress());
	thirdParty.setFax(thirdPartyForm.getFax());
	thirdParty.setWeb(thirdPartyForm.getWeb());
	thirdParty.setTypeName(typename);

	//Gp Details
	thirdParty.setGpname(thirdPartyForm.getGpname());
	thirdParty.setWorkphno(thirdPartyForm.getWorkphno());
	thirdParty.setGpemailid(thirdPartyForm.getGpemailid());
	thirdParty.setGpfax(thirdPartyForm.getGpfax());
	thirdParty.setNotes(thirdPartyForm.getNotes());
	
	thirdParty.setTpAccountSettingNotes(thirdPartyForm.getTpAccountSettingNotes());
	thirdParty.setClinicId(loginInfo.getId());
	thirdParty.setDnaForAll(thirdPartyForm.isDnaForAll());
	
	thirdParty.setValidityFrom(thirdPartyForm.getValidityFrom());
	thirdParty.setValidityTo(thirdPartyForm.getValidityTo());
	thirdParty.setValidityStatus(thirdPartyForm.getValidityStatus());
	thirdParty.setPayementType(thirdPartyForm.getPayementType());
	//Account Setting
	
	thirdParty.setAccountWarningLimit(thirdPartyForm.getAccountWarningLimit());
	thirdParty.setDnaLimit(thirdPartyForm.getDnaLimit());
	thirdParty.setOutInvoiceLimit(thirdPartyForm.getOutInvoiceLimit());
	thirdParty.setCreditDuration(thirdPartyForm.getCreditDuration());
	thirdParty.setCreditReminderDuration(thirdPartyForm.getCreditReminderDuration());
	
	//dna appointment name
	thirdParty.setDnaInitialApmt(thirdPartyForm.getDnaInitialApmt());
	thirdParty.setDnafollowupApmt(thirdPartyForm.getDnafollowupApmt());
	thirdParty.setDnafinalApmt(thirdPartyForm.getDnafinalApmt());
	thirdParty.setDnamaintenanceApmt(thirdPartyForm.getDnamaintenanceApmt());
	
	//dna charges
	/*thirdParty.setCompltInitialApmt(thirdPartyForm.getDnaInitialApmt());
	thirdParty.setCompltfollowupApmt(thirdPartyForm.getDnafollowupApmt());
	thirdParty.setCompltfinalApmt(thirdPartyForm.getDnafinalApmt());
	thirdParty.setCompltmaintenanceApmt(thirdPartyForm.getDnamaintenanceApmt());
	*/
	
	thirdParty.setDnaInitialCharge(thirdPartyForm.getDnaInitialCharge());
	thirdParty.setDnaFollowupCharge(thirdPartyForm.getDnaFollowupCharge());
	thirdParty.setDnaFinalCharge(thirdPartyForm.getDnaFinalCharge());
	thirdParty.setDnaMaintnanceCharge(thirdPartyForm.getDnaMaintnanceCharge());
	
	
	//completed charges
	thirdParty.setCompletedInitialCharge(thirdPartyForm.getCompletedInitialCharge());
	thirdParty.setCompletedFollowupCharge(thirdPartyForm.getCompletedFollowupCharge());
	thirdParty.setCompletedFinalCharge(thirdPartyForm.getCompletedFinalCharge());
	thirdParty.setCompletedMaintnanceCharge(thirdPartyForm.getCompletedMaintnanceCharge());
	
	
	//Share
	thirdParty.setIpdShare(thirdPartyForm.getIpdShare());
	thirdParty.setSurgeonShare(thirdPartyForm.getSurgeonShare());
	thirdParty.setOpdShare(thirdPartyForm.getOpdShare());
	
	
	//duration
	thirdParty.setCompltInitialApmtDuration(thirdPartyForm.getCompltInitialApmtDuration());
	thirdParty.setCompltfollowupApmtDuration(thirdPartyForm.getCompltfollowupApmtDuration());
	thirdParty.setCompltfinalApmtDuration(thirdPartyForm.getCompltfinalApmtDuration());
	thirdParty.setCompltmaintenanceApmtDuration(thirdPartyForm.getCompltmaintenanceApmtDuration());
	
	//dna offset
	thirdParty.setInitialOffsetdna(thirdPartyForm.isInitialOffsetdna());
	thirdParty.setFollowupOffsetdna(thirdPartyForm.isFollowupOffsetdna());
	thirdParty.setFinalOffsetdna(thirdPartyForm.isFinalOffsetdna());
	thirdParty.setMaintnanceOffsetdna(thirdPartyForm.isMaintnanceOffsetdna());
	
	//unit and area
	thirdParty.setArea(thirdPartyForm.getArea());
	thirdParty.setUnit(thirdPartyForm.getUnit());
	
	//main tp
	thirdParty.setMaintp(thirdPartyForm.isMaintp());
	thirdParty.setEnrollcode(thirdPartyForm.isEnrollcode());
	thirdParty.setCampArea(thirdPartyForm.isCampArea());
	
	String modifyid = (String)session.getAttribute("thirdpartyeditappointmenttypeid");
	
	Connection connection = null;
	try{
		connection = Connection_provider.getconnection();
	ThirdPartyDAO  thirdPartyDAO = new JDBCThirdPartyDAO(connection);
	ThirdParty thirdParty2 = new ThirdParty();
	//for getting previous registerpayer data before update
	ThirdParty old_registerpayer=thirdPartyDAO.getRegisterPayer(typename);
	
	if(type.equals("1") && !typename.equals("0")){
		int result = thirdPartyDAO.saveGp(type,thirdParty,typename);
		int rr1 = thirdPartyDAO.updateTp(type,thirdParty,loginInfo.getId(),typename);
	}
	
	/*//added 27/01/20222
	 if( !typename.equals("0") && !companyname.equals("")){
		 int r2 = thirdPartyDAO.updateCompanyName(typename , thirdParty);
		
	}*/
	
	
	else{
		int r1 = 0;
		if(!companyname.equals("") && typename.equals("0")){
			 r1 = thirdPartyDAO.saveTp(type,thirdParty,loginInfo.getId());
			 if(thirdPartyForm.getDynamicApmt()!=null){
			 for(DynamicAppointment dynamicAppointment : thirdPartyForm.getDynamicApmt()){
					
					ThirdParty thirdParty1 = new ThirdParty();
					thirdParty1.setDnaName(dynamicAppointment.getDnaName());
					thirdParty1.setDnaCharge(dynamicAppointment.getDnaCharge());
					thirdParty1.setApmtName(dynamicAppointment.getApmtName());
					thirdParty1.setApmtCharge(dynamicAppointment.getApmtCharge());
					thirdParty1.setApmtDuaration(dynamicAppointment.getApmtDuaration());
					thirdParty1.setOffset(dynamicAppointment.isOffset());
					
					//int result = thirdPartyDAO.saveDynamicApmts(r1,thirdParty1);
					int result  = thirdPartyDAO.saveDynamicAppointmentTypeData(r1,thirdParty1);
				}
			 }
			 
		}/*else if(!typename.equals("0") && !companyname.equals("")){
			 int r2 = thirdPartyDAO.updateCompanyName(typename , thirdParty);
		 }*/
		else {
			   int pre_patientype=thirdPartyDAO.getPatientType(typename);
			r1 = thirdPartyDAO.updateTp(type,thirdParty,loginInfo.getId(),typename);
			old_registerpayer.setPatientType(Integer.toString(pre_patientype));
			if(thirdPartyForm.getDynamicApmt()!=null){
				 for(DynamicAppointment dynamicAppointment : thirdPartyForm.getDynamicApmt()){
						
						ThirdParty thirdParty1 = new ThirdParty();
						int iddnymic = dynamicAppointment.getId();
						thirdParty1.setDnaName(dynamicAppointment.getDnaName());
						thirdParty1.setDnaCharge(dynamicAppointment.getDnaCharge());
						thirdParty1.setApmtName(dynamicAppointment.getApmtName());
						thirdParty1.setApmtCharge(dynamicAppointment.getApmtCharge());
						thirdParty1.setApmtDuaration(dynamicAppointment.getApmtDuaration());
						thirdParty1.setOffset(dynamicAppointment.isOffset());
						if(iddnymic > 0){
							//int result = thirdPartyDAO.updateDynamicApmts(r1,thirdParty1,iddnymic);
							int result = thirdPartyDAO.updateDynamicAppointmentTypeData(iddnymic,thirdParty1,modifyid);

						}
						else{
							//int update1 = thirdPartyDAO.saveDynamicApmts(Integer.parseInt(typename),thirdParty1);
							int result  = thirdPartyDAO.saveDynamicAppointmentTypeData(Integer.parseInt(modifyid),thirdParty1);
						}
					}
				 }
			//to store log of register payer update
			if(r1>0){
				DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			    Calendar cal = Calendar.getInstance();
			    String datetime = dateFormat.format(cal.getTime());  
				int res = thirdPartyDAO.saveRegisterPayerLog(loginInfo.getUserId(),old_registerpayer,thirdParty,datetime);
			}
			
		}
		
		
		// save appointment type
		
		//int res = thirdPartyDAO.saveAppointmentTYpe(thirdParty);
		
		
		if(r1==0){
			r1 = Integer.parseInt(modifyid);
		}
		
		String companyNameForChargeType = "";
		
		if(thirdPartyForm.getCompanyName().equals("")){
			companyNameForChargeType = thirdPartyDAO.getCompanyNameForChargeType(typename);
		}else{
			companyNameForChargeType = thirdPartyForm.getCompanyName();
		}
		 
		
		
		if(!thirdParty.getDnaInitialCharge().equals("") || !thirdParty.getCompletedInitialCharge().equals("")){
			thirdParty.setInitialAppointmentName(companyNameForChargeType + " " + Constants.INITIAL_APPOINTMENT);
			//short name
			if(!thirdPartyForm.getDnaInitialApmt().equals("")){
				thirdParty.setInitialAppointmentName(thirdPartyForm.getDnaInitialApmt());
			}
			
			thirdParty.setCompltInitialApmtDuration(thirdPartyForm.getCompltInitialApmtDuration());
			
			boolean isAppointmentTypeExist = thirdPartyDAO.checkAppointmentTypeExist(typename,Constants.INITIAL_APPOINTMENT_TYPE);
			if(!isAppointmentTypeExist){
				int res = thirdPartyDAO.saveInitialAppointmentTYpe(thirdParty,r1);
				//int update = thirdPartyDAO.updateAppointmentTypeName(thirdPartyForm.getTypeName(),thirdParty.getInitialAppointmentName(),Constants.INITIAL_APPOINTMENT);
			}else{
				int apmttypeid = thirdPartyDAO.getExistingAppointmentTypeid(typename,Constants.INITIAL_APPOINTMENT_TYPE);
				int update = thirdPartyDAO.updateThirpartyAppointmentType(thirdParty.getInitialAppointmentName(),thirdParty.getCompletedInitialCharge(),thirdParty.getCompltInitialApmtDuration(),thirdParty.getDnaInitialCharge(),thirdParty.isInitialOffsetdna(),apmttypeid);
				//int updat = thirdPartyDAO.updateAppointmentTypeName(thirdPartyForm.getTypeName(),thirdParty.getInitialAppointmentName(),Constants.INITIAL_APPOINTMENT);
			}
			
		}
		if(!thirdParty.getDnaFollowupCharge().equals("") || !thirdParty.getCompletedFollowupCharge().equals("")){
			thirdParty.setFollowupAppointmentName(companyNameForChargeType + " " + Constants.FOLLOWUP_APPOINTMENT);
			//short name
			if(!thirdPartyForm.getDnafollowupApmt().equals("")){
				thirdParty.setFollowupAppointmentName(thirdPartyForm.getDnafollowupApmt());
			}
			thirdParty.setCompltfollowupApmtDuration(thirdPartyForm.getCompltfollowupApmtDuration());
			
			boolean isAppointmentTypeExist = thirdPartyDAO.checkAppointmentTypeExist(typename,Constants.FOLLOWUP_APPOINTMENT_TYPE);
			if(!isAppointmentTypeExist){
				int res = thirdPartyDAO.saveFollowupAppointmentTYpe(thirdParty,r1);
				//int update = thirdPartyDAO.updateAppointmentTypeName(thirdPartyForm.getTypeName(),thirdParty.getFollowupAppointmentName(),Constants.FOLLOWUP_APPOINTMENT);
			}else{
				int apmttypeid = thirdPartyDAO.getExistingAppointmentTypeid(typename,Constants.FOLLOWUP_APPOINTMENT_TYPE);
				int update = thirdPartyDAO.updateThirpartyAppointmentType(thirdParty.getFollowupAppointmentName(),thirdParty.getCompletedFollowupCharge(),thirdParty.getCompltfollowupApmtDuration(),thirdParty.getDnaFollowupCharge(),thirdParty.isFollowupOffsetdna(),apmttypeid);
				//int updat = thirdPartyDAO.updateAppointmentTypeName(thirdPartyForm.getTypeName(),thirdParty.getFollowupAppointmentName(),Constants.FOLLOWUP_APPOINTMENT);
			}
			
		}
		if(!thirdParty.getDnaFinalCharge().equals("") || !thirdParty.getCompletedFinalCharge().equals("")){
			thirdParty.setFinalAppointmentName(companyNameForChargeType + " " + Constants.FINAL_APPOINTMENT);
			//short name
			if(!thirdPartyForm.getDnafinalApmt().equals("")){
				thirdParty.setFinalAppointmentName(thirdPartyForm.getDnafinalApmt());
			}
			thirdParty.setCompltfollowupApmtDuration(thirdPartyForm.getCompltfinalApmtDuration());
			
			boolean isAppointmentTypeExist = thirdPartyDAO.checkAppointmentTypeExist(typename,Constants.FINAL_APPOINTMENT_TYPE);
			if(!isAppointmentTypeExist){
				int res = thirdPartyDAO.saveFinalAppointmentTYpe(thirdParty,r1);
				//int update = thirdPartyDAO.updateAppointmentTypeName(thirdPartyForm.getTypeName(),thirdParty.getFinalAppointmentName(),Constants.FINAL_APPOINTMENT);
			}else{
				int apmttypeid = thirdPartyDAO.getExistingAppointmentTypeid(typename,Constants.FINAL_APPOINTMENT_TYPE);
				int update = thirdPartyDAO.updateThirpartyAppointmentType(thirdParty.getFinalAppointmentName(),thirdParty.getCompletedFinalCharge(),thirdParty.getCompltfinalApmtDuration(),thirdParty.getDnaFinalCharge(),thirdParty.isFinalOffsetdna(),apmttypeid);
				//int updat = thirdPartyDAO.updateAppointmentTypeName(thirdPartyForm.getTypeName(),thirdParty.getFinalAppointmentName(),Constants.FINAL_APPOINTMENT);
			}
			
		}
		if(!thirdParty.getDnaMaintnanceCharge().equals("") || !thirdParty.getCompletedMaintnanceCharge().equals("") ){
			thirdParty.setMaintnanceAppointmentName(companyNameForChargeType + " " + Constants.MAINTNANCE_APPOINTMENT);
			//short name
			if(!thirdPartyForm.getDnamaintenanceApmt().equals("")){
				thirdParty.setMaintnanceAppointmentName(thirdPartyForm.getDnamaintenanceApmt());
			}
			thirdParty.setCompltmaintenanceApmtDuration(thirdPartyForm.getCompltmaintenanceApmtDuration());
			
			boolean isAppointmentTypeExist = thirdPartyDAO.checkAppointmentTypeExist(typename,Constants.MAINTNANCE_APPOINTMENT_TYPE);
			if(!isAppointmentTypeExist){
				int res = thirdPartyDAO.saveMaintnanceAppointmentTYpe(thirdParty,r1);
				//int updat = thirdPartyDAO.updateAppointmentTypeName(thirdPartyForm.getTypeName(),thirdParty.getMaintnanceAppointmentName(),Constants.MAINTNANCE_APPOINTMENT);
			}else{
				int apmttypeid = thirdPartyDAO.getExistingAppointmentTypeid(typename,Constants.MAINTNANCE_APPOINTMENT_TYPE);
				int update = thirdPartyDAO.updateThirpartyAppointmentType(thirdParty.getMaintnanceAppointmentName(),thirdParty.getCompletedMaintnanceCharge(),thirdParty.getCompltmaintenanceApmtDuration(),thirdParty.getDnaMaintnanceCharge(),thirdParty.isMaintnanceOffsetdna(),apmttypeid);
				//int updat = thirdPartyDAO.updateAppointmentTypeName(thirdPartyForm.getTypeName(),thirdParty.getMaintnanceAppointmentName(),Constants.MAINTNANCE_APPOINTMENT);
			}
			
		}
		
		
		
	/*	if(type.equals("1")){
			int result = thirdPartyDAO.saveGp(Integer.toString(r1),thirdParty);
		}*/
		
		
		

	}
	
	
	String isFromPG = request.getParameter("isFromPG");
	if(DateTimeUtils.isNull(isFromPG).equals("1")){
		return "payerCreationMaster";
	}
	
	thirdPartyForm.setMessage("Third Party Type Added Sucessfully!!");
	addActionMessage("Third Party Details Added Sucessfully!!");
	setFormData();
	
	
	}catch (Exception e) {
		e.printStackTrace();
	}finally{
	connection.close();
}
	
	return SUCCESS;
}

public String saveNewTpAjax() throws SQLException{
	String type = request.getParameter("type");
	String typename = request.getParameter("typeName");
	String newtpname = request.getParameter("newtpname");
	String telephoneLine = request.getParameter("telephoneLine");
	String compnyEmail = request.getParameter("compnyEmail");
	String address = request.getParameter("address");
	String town = request.getParameter("town");
	String postcode = request.getParameter("postcode");
	String country = request.getParameter("country");
	String gpname = request.getParameter("gpname");
	String workphno = request.getParameter("workphno");
	String gpemailid = request.getParameter("gpemailid");
	String gpfax = request.getParameter("gpfax");
	String notes = request.getParameter("notes");
	String outInvoiceLimit = request.getParameter("outInvoiceLimit");
	String accountWarningLimit = request.getParameter("accountWarningLimit");
	String creditDuration = request.getParameter("creditDuration");
	String creditReminderDuration = request.getParameter("creditReminderDuration");
	String dnaLimit = request.getParameter("dnaLimit");
	String dnaInitialApmt = request.getParameter("dnaInitialApmt");
	String compltInitialApmt = request.getParameter("compltInitialApmt");
	String dnafollowupApmt = request.getParameter("dnafollowupApmt");
	String compltfollowupApmt = request.getParameter("compltfollowupApmt");
	String dnafinalApmt = request.getParameter("dnafinalApmt");
	String compltfinalApmt = request.getParameter("compltfinalApmt");
	String dnamaintenanceApmt = request.getParameter("dnamaintenanceApmt");
	String compltmaintenanceApmt = request.getParameter("compltmaintenanceApmt");
	String compltInitialApmtDuration = request.getParameter("compltInitialApmtDuration");
	String compltfollowupApmtDuration = request.getParameter("compltfollowupApmtDuration");
	String compltfinalApmtDuration = request.getParameter("compltfinalApmtDuration");
	String compltmaintenanceApmtDuration = request.getParameter("compltmaintenanceApmtDuration");
	

	ThirdParty thirdParty = new ThirdParty();
	//TP Details field
	thirdParty.setType(type);
	thirdParty.setTypeName(typename);
	thirdParty.setCompanyName(newtpname);
	thirdParty.setTelephoneLine(telephoneLine);
	thirdParty.setCompnyEmail(compnyEmail);
	thirdParty.setAddress(address);
	thirdParty.setTown(town);
	thirdParty.setPostcode(postcode);
	thirdParty.setCountry(country);

	//Gp Details
	thirdParty.setGpname(gpname);
	thirdParty.setWorkphno(workphno);
	thirdParty.setGpemailid(gpemailid);
	thirdParty.setGpfax(gpfax);
	thirdParty.setNotes(notes);

	
	//Account Setting
	
	thirdParty.setAccountWarningLimit(accountWarningLimit);
	thirdParty.setDnaLimit(dnaLimit);
	thirdParty.setOutInvoiceLimit(outInvoiceLimit);
	thirdParty.setCreditDuration(creditDuration);
	thirdParty.setCreditReminderDuration(creditReminderDuration);
	thirdParty.setDnaInitialApmt(dnaInitialApmt);
	thirdParty.setDnafollowupApmt(dnafollowupApmt);
	thirdParty.setDnafinalApmt(dnafinalApmt);
	thirdParty.setDnamaintenanceApmt(dnamaintenanceApmt);
	thirdParty.setCompltInitialApmt(compltInitialApmt);
	thirdParty.setCompltfollowupApmt(compltfollowupApmt);
	thirdParty.setCompltfinalApmt(compltfinalApmt);
	thirdParty.setCompltmaintenanceApmt(compltmaintenanceApmt);
	thirdParty.setClinicId(loginInfo.getId());
	
	thirdParty.setCompltInitialApmtDuration(compltInitialApmtDuration);
	thirdParty.setCompltfollowupApmtDuration(compltfollowupApmtDuration);
	thirdParty.setCompltfinalApmtDuration(compltfinalApmtDuration);
	thirdParty.setCompltmaintenanceApmtDuration(compltmaintenanceApmtDuration);
	
	
	Connection connection = null;
	try{
		connection = Connection_provider.getconnection();
	ThirdPartyDAO  thirdPartyDAO = new JDBCThirdPartyDAO(connection);
	if(type.equals("1")&& !typename.equals("0")){
		int result = thirdPartyDAO.saveGp(type,thirdParty,typename);

	}
	else{

		int r1 = thirdPartyDAO.saveTp(type,thirdParty,loginInfo.getId());
		if(type.equals("1")){
		int result = thirdPartyDAO.saveGp(Integer.toString(r1),thirdParty,Integer.toString(r1));
		}
		ArrayList<ThirdParty>ajaxTypeNameList = thirdPartyDAO.getThirdPartyDetailsList(loginInfo.getId(),type);
		 
		 StringBuffer dropDownAjax = new StringBuffer();
			dropDownAjax.append("<select id='popuptypeName' name = 'typeName' class = 'form-control chosen'>");
				dropDownAjax.append("<option value = '0'>Select Company</option>");
				if(ajaxTypeNameList.size()!=0){
					for(ThirdParty tParty : ajaxTypeNameList){
						dropDownAjax.append("<option value = '"+tParty.getId()+"'>"+tParty.getCompanyName()+"</option>");
					}
					
				}
			dropDownAjax.append("</select>");
			response.setContentType("text/html");
			response.setHeader("Cache-Control", "no-cache");
			
			response.getWriter().write(""+dropDownAjax.toString()+""); 
	}
	
	}catch (Exception e) {
	e.printStackTrace();
	}finally{
		connection.close();
	}
	
	return null;
}
public String saveDoctorSurgeryAjax() throws Exception{
	String type = request.getParameter("type");
	String dsname = request.getParameter("dsname");
	String telephoneLine = request.getParameter("telephoneLine");
	String compnyEmail = request.getParameter("compnyEmail");
	String address = request.getParameter("address");
	String town = request.getParameter("town");
	String postcode = request.getParameter("postcode");
	String country = request.getParameter("country");
	String gpname = request.getParameter("gpname");
	String workphno = request.getParameter("workphno");
	String gpemailid = request.getParameter("gpemailid");
	String gpfax = request.getParameter("gpfax");
	String notes = request.getParameter("notes");
	
	ThirdParty thirdParty = new ThirdParty();
	//TP Details field
	thirdParty.setType(type);
	thirdParty.setCompanyName(dsname);
	thirdParty.setTelephoneLine(telephoneLine);
	thirdParty.setCompnyEmail(compnyEmail);
	thirdParty.setAddress(address);
	thirdParty.setTown(town);
	thirdParty.setPostcode(postcode);
	thirdParty.setCountry(country);

	//Gp Details
	thirdParty.setGpname(gpname);
	thirdParty.setWorkphno(workphno);
	thirdParty.setGpemailid(gpemailid);
	thirdParty.setGpfax(gpfax);
	thirdParty.setNotes(notes);
	
	Connection connection = null;
	try{
		connection = Connection_provider.getconnection();
	ThirdPartyDAO  thirdPartyDAO = new JDBCThirdPartyDAO(connection);
	int r1 = thirdPartyDAO.saveTp(type,thirdParty,loginInfo.getId());
	
	int result = thirdPartyDAO.saveGp(Integer.toString(r1),thirdParty,Integer.toString(r1));
	
	ArrayList<ThirdParty>doctorSurgeryList = thirdPartyDAO.getDoctorSurgeryList(type);
	 
	 StringBuffer dropDownAjax = new StringBuffer();
		dropDownAjax.append("<select id='gptypeName' name = 'gptypeName' class = 'form-control chosen'>");
			//dropDownAjax.append("<option value = '0'>Select Company</option>");
			if(doctorSurgeryList.size()!=0){
				for(ThirdParty tParty : doctorSurgeryList){
					dropDownAjax.append("<option value = '"+tParty.getId()+"'>"+tParty.getCompanyName()+"</option>");
				}
				
			}
		dropDownAjax.append("</select>");
		ArrayList<ThirdParty>gpList = thirdPartyDAO.getGPList(r1);
		 StringBuffer dropDownAjax1 = new StringBuffer();
			dropDownAjax1.append("<select id='gpname' name = 'gpname' class = 'form-control chosen'>");
				//dropDownAjax1.append("<option value = '0'>Select Company</option>");
				if(gpList.size()!=0){
					for(ThirdParty tParty : gpList){
						dropDownAjax1.append("<option value = '"+tParty.getId()+"'>"+tParty.getGpname()+"</option>");
					}
					
				}
			dropDownAjax1.append("</select>");
		
		response.setContentType("text/html");
		response.setHeader("Cache-Control", "no-cache");
		
		response.getWriter().write(""+dropDownAjax.toString()+"*"+dropDownAjax1.toString()+""); 
	
	}
	catch(Exception e){
		e.printStackTrace();
	}finally{
		connection.close();
	}
	return null;
}
public void setFormData() {

	
	
		String type= (String) session.getAttribute("type");
		String searchText = (String) session.getAttribute("searchText");
		if(type=="0" || type == null){
			type = "";
		}
		if(searchText == "" || searchText== null){
			searchText = "";
		}
	Connection connection = null;
	try{
		connection = Connection_provider.getconnection();
		ThirdPartyDAO  thirdPartyDAO = new JDBCThirdPartyDAO(connection);
		ArrayList<ThirdParty>thirdPartyTypeList = thirdPartyDAO.getThirdPartyList();
		thirdPartyForm.setThirdPartyTypeList(thirdPartyTypeList);
		thirdPartyForm.setName(type);
		thirdPartyForm.setName1(type);
		pagination = (Pagination) session.getAttribute("pagination");
		int totalCount = thirdPartyDAO.getTotalThirdPartiesCount(loginInfo.getId(),type,searchText);
		pagination.setPreperties(totalCount);
		ArrayList<ThirdParty>thirdPartyDetailList = thirdPartyDAO.getThirdPartyDetailsList(type,loginInfo.getId(),pagination,searchText);
		
		pagination.setPage_records(thirdPartyDetailList.size());
		thirdPartyForm.setTotalRecords(totalCount);
		thirdPartyForm.setPagerecords(Integer.toString(pagination.getPage_records()));
		
		thirdPartyForm.setThirdPartyDetailList(thirdPartyDetailList);
		session.setAttribute("type", type);
		session.setAttribute("searchText", searchText);
		
		
	}catch (Exception e) {
		e.printStackTrace();
	}
	
	
	
}

public String back(){
	setFormData();
	return SUCCESS;
	
}
public String move(){
	setFormData();
	return SUCCESS;
}
public String showList() throws SQLException{
	if(!verifyLogin(request)){
		return "login";
	}
	String type = thirdPartyForm.getName();
	String searchText = thirdPartyForm.getSearchText();
	
	if(type == ""){
		type= (String) session.getAttribute("type");
		
	}
	if(searchText == ""){
		searchText = (String) session.getAttribute("searchText");
	}
	
	if(type == null){
		type = "";
	}
	if(searchText == null){
		searchText = "";
	}
	
	Connection connection = null;
	try{
		connection = Connection_provider.getconnection();
		ThirdPartyDAO  thirdPartyDAO = new JDBCThirdPartyDAO(connection);
		ArrayList<ThirdParty>thirdPartyTypeList = thirdPartyDAO.getThirdPartyList();
		thirdPartyForm.setThirdPartyTypeList(thirdPartyTypeList);
		thirdPartyForm.setName(type);
		
		thirdPartyForm.setSearchText(searchText);
		
		int totalCount = thirdPartyDAO.getTotalThirdPartiesCount(loginInfo.getId(),type,searchText);
		pagination.setPreperties(totalCount);
		ArrayList<ThirdParty>thirdPartyDetailList = thirdPartyDAO.getThirdPartyDetailsList(type,loginInfo.getId(),pagination,searchText);
		
		pagination.setPage_records(thirdPartyDetailList.size());
		thirdPartyForm.setTotalRecords(totalCount);
		thirdPartyForm.setPagerecords(Integer.toString(pagination.getPage_records()));
		
		thirdPartyForm.setThirdPartyDetailList(thirdPartyDetailList);
		session.setAttribute("type", type);
		session.setAttribute("searchText", searchText);
		session.setAttribute("pagination", pagination);
		
	}catch (Exception e) {
		e.printStackTrace();
	}
	finally{
		
		connection.close();
	}
	return SUCCESS;
}

public String editDetail() throws SQLException{
	if(!verifyLogin(request)){
		return "login";
	}
	String id = request.getParameter("selectedid");
	
	Connection connection = null;
	try{
		connection = Connection_provider.getconnection();
		ThirdPartyDAO  thirdPartyDAO = new JDBCThirdPartyDAO(connection);
		ThirdParty thirdParty = new ThirdParty();
		thirdParty= thirdPartyDAO.getThirdPartyDetails(id);
		
		String type =  Integer.toString(thirdParty.getThirdPartyId()); 
		thirdPartyForm.setId(thirdParty.getId());
		thirdPartyForm.setType(type);
		thirdPartyForm.setName(thirdParty.getName());
		thirdPartyForm.setSalutation(thirdParty.getSalutation());
		thirdPartyForm.setTitle(thirdParty.getTitle());
		thirdPartyForm.setDepartment(thirdParty.getDepartment());
		thirdPartyForm.setSearchName(thirdParty.getSearchName());
		thirdPartyForm.setTelephoneLine(thirdParty.getTelephoneLine());
		thirdPartyForm.setEmail(thirdParty.getEmail());
		thirdPartyForm.setEmailCc(thirdParty.getEmailCc());
		thirdPartyForm.setNotes(thirdParty.getNotes());
		thirdPartyForm.setCompanyDetails(thirdParty.getCompanyDetails());
		thirdPartyForm.setGptown(thirdParty.getGptown());
		thirdPartyForm.setGpaddress(thirdParty.getGpaddress());
		//Company Details field
		
		thirdPartyForm.setCompanyName(thirdParty.getCompanyName());
		thirdPartyForm.setAddress(thirdParty.getAddress());
		thirdPartyForm.setTown(thirdParty.getTown());
		thirdPartyForm.setCountry(thirdParty.getCountry());
		thirdPartyForm.setPostcode(thirdParty.getPostcode());
		thirdPartyForm.setCompnyTelephone(thirdParty.getCompnyTelephone());
		thirdPartyForm.setFax(thirdParty.getFax());
		thirdPartyForm.setWeb(thirdParty.getWeb());
		thirdPartyForm.setReferenceNo(thirdParty.getReferenceNo());
		thirdPartyForm.setCompnyEmail(thirdParty.getCompnyEmail());
		
		//Account Setting
		
		thirdPartyForm.setWarningMsg(thirdParty.getWarningMsg());
		thirdPartyForm.setAccountsNotes(thirdParty.getAccountsNotes());
		thirdPartyForm.setAccountMustBeInCredit(thirdParty.isAccountMustBeInCredit());
		
		thirdPartyForm.setAccountWarningLimit(thirdParty.getAccountWarningLimit());
		thirdPartyForm.setDnaLimit(thirdParty.getDnaLimit());
		thirdPartyForm.setOutInvoiceLimit(thirdParty.getOutInvoiceLimit());
		thirdPartyForm.setCreditDuration(thirdParty.getCreditDuration());
		thirdPartyForm.setCreditReminderDuration(thirdParty.getCreditReminderDuration());
		//Contact Preference
		
		thirdPartyForm.setDefaultApmtBookingConfmTemp(thirdParty.getDefaultApmtBookingConfmTemp());
		thirdPartyForm.setConfContactEmail(thirdParty.getConfContactEmail());
		thirdPartyForm.setDefaultApmtDnaTemp(thirdParty.getDefaultApmtDnaTemp());
		thirdPartyForm.setDnaContactEmail(thirdParty.getDnaContactEmail());
		
		thirdPartyForm.setUnit(thirdParty.getUnit());
		thirdPartyForm.setArea(thirdParty.getArea());
		
		thirdPartyForm.setShortname(thirdParty.getShortname());
		
		
		
	}catch (Exception e) {
		e.printStackTrace();
	}
	finally{
		
		connection.close();
	}
	
	return "editThirdPartyDetail";
}

public String updateDetails() throws SQLException{
	if(!verifyLogin(request)){
		return "login";
	}
	String type = thirdPartyForm.getType();
	ThirdParty thirdParty = new ThirdParty();
	
	int id = thirdPartyForm.getId();
	//Personal Details field
	
	thirdParty.setType(type);
	thirdParty.setName(thirdPartyForm.getName());
	thirdParty.setSalutation(thirdPartyForm.getSalutation());
	thirdParty.setTitle(thirdPartyForm.getTitle());
	thirdParty.setDepartment(thirdPartyForm.getDepartment());
	thirdParty.setSearchName(thirdPartyForm.getSearchName());
	thirdParty.setTelephoneLine(thirdPartyForm.getTelephoneLine());
	thirdParty.setEmail(thirdPartyForm.getEmail());
	thirdParty.setEmailCc(thirdPartyForm.getEmailCc());
	thirdParty.setNotes(thirdPartyForm.getNotes());
	thirdParty.setCompanyDetails(thirdPartyForm.getCompanyDetails());
	thirdParty.setGptown(thirdPartyForm.getGptown());
	thirdParty.setGpaddress(thirdPartyForm.getGpaddress());
	//Company Details field
	
	thirdParty.setCompanyName(thirdPartyForm.getCompanyName());
	thirdParty.setAddress(thirdPartyForm.getAddress());
	thirdParty.setTown(thirdPartyForm.getTown());
	thirdParty.setCountry(thirdPartyForm.getCountry());
	thirdParty.setPostcode(thirdPartyForm.getPostcode());
	thirdParty.setCompnyTelephone(thirdPartyForm.getCompnyTelephone());
	thirdParty.setFax(thirdPartyForm.getFax());
	thirdParty.setWeb(thirdPartyForm.getWeb());
	thirdParty.setReferenceNo(thirdPartyForm.getReferenceNo());
	thirdParty.setCompnyEmail(thirdPartyForm.getCompnyEmail());
	
	//Account Setting
	
	thirdParty.setWarningMsg(thirdPartyForm.getWarningMsg());
	thirdParty.setAccountsNotes(thirdPartyForm.getAccountsNotes());
	thirdParty.setAccountMustBeInCredit(thirdPartyForm.isAccountMustBeInCredit());
	//thirdParty.setAccountCreditLimit(thirdPartyForm.getAccountCreditLimit());
	thirdParty.setAccountWarningLimit(thirdPartyForm.getAccountWarningLimit());
	thirdParty.setDnaLimit(thirdPartyForm.getDnaLimit());
	thirdParty.setOutInvoiceLimit(thirdPartyForm.getOutInvoiceLimit());
	thirdParty.setCreditDuration(thirdPartyForm.getCreditDuration());
	thirdParty.setCreditReminderDuration(thirdPartyForm.getCreditReminderDuration());
	//Contact Preference
	
	thirdParty.setDefaultApmtBookingConfmTemp(thirdPartyForm.getDefaultApmtBookingConfmTemp());
	thirdParty.setConfContactEmail(thirdPartyForm.getConfContactEmail());
	thirdParty.setDefaultApmtDnaTemp(thirdPartyForm.getDefaultApmtDnaTemp());
	thirdParty.setDnaContactEmail(thirdPartyForm.getDnaContactEmail());
	
	Connection connection = null;
	try{
		connection = Connection_provider.getconnection();
		ThirdPartyDAO  thirdPartyDAO = new JDBCThirdPartyDAO(connection);
	
	int result = thirdPartyDAO.editDetails(type,id,thirdParty);
	thirdPartyForm.setMessage("Third Party Type Updated Sucessfully!!");
	addActionMessage("Third Party Details Updated Sucessfully!!");
	setFormData();
	
	}catch (Exception e) {
	e.printStackTrace();
	}finally{
	
	connection.close();
	}

	return SUCCESS;
}

public String deleteDetails() throws SQLException{
	if(!verifyLogin(request)){
		return "login";
	}
	int id = Integer.parseInt(request.getParameter("selectedid"));
	Connection connection = null;
	try{
		connection = Connection_provider.getconnection();
		ThirdPartyDAO  thirdPartyDAO = new JDBCThirdPartyDAO(connection);
	
	int result = thirdPartyDAO.deleteDetail(id);
	thirdPartyForm.setMessage("Third Party Details Deleted Sucessfully!!");
	addActionMessage("Third Party Details Deleted Sucessfully!!");
	setFormData();
	
	}catch (Exception e) {
	e.printStackTrace();
	}finally{
	
	connection.close();
	}

	return SUCCESS;
}



public String saveNew() throws Exception{
	if(!verifyLogin(request)){
		return "login";
	}
	String thirdPartyType = request.getParameter("thirdPartyType");
	String thirdPartyCompanyName = request.getParameter("thirdPartyCompanyName");
	String compnyEmail = request.getParameter("compnyEmail");
	String compnyTelephone = request.getParameter("compnyTelephone");
	String outInvoiceLimit = request.getParameter("outInvoiceLimit");
	String accountWarningLimit = request.getParameter("accountWarningLimit");
	String dnaLimit = request.getParameter("dnaLimit");
	ThirdParty thirdParty = new ThirdParty();
	
	
	thirdParty.setType(thirdPartyType);
	thirdParty.setCompanyName(thirdPartyCompanyName);
	thirdParty.setCompnyTelephone(compnyTelephone);
	thirdParty.setCompnyEmail(compnyEmail);
	
	//Account Setting
	

	thirdParty.setAccountWarningLimit(accountWarningLimit);
	thirdParty.setDnaLimit(dnaLimit);
	thirdParty.setOutInvoiceLimit(outInvoiceLimit);
	

	thirdParty.setClinicId(loginInfo.getId());
	
	Connection connection = null;
	try{
		connection = Connection_provider.getconnection();
	ThirdPartyDAO  thirdPartyDAO = new JDBCThirdPartyDAO(connection);
	
	int result = thirdPartyDAO.saveNewDetails(thirdPartyType,thirdParty);
	
	
	 ArrayList<ThirdParty>ajaxTypeNameList = thirdPartyDAO.getThirdPartyDetailsList(loginInfo.getId());
	 
	 StringBuffer dropDownAjax = new StringBuffer();
		dropDownAjax.append("<select id='companyName' name = 'companyName' class = 'form-control chosen'>");
			dropDownAjax.append("<option value = '0'>Select Company</option>");
			if(ajaxTypeNameList.size()!=0){
				for(ThirdParty tParty : ajaxTypeNameList){
					dropDownAjax.append("<option value = '"+tParty.getId()+"'>"+tParty.getCompanyName()+"</option>");
				}
				
			}
		dropDownAjax.append("</select>");
		response.setContentType("text/html");
		response.setHeader("Cache-Control", "no-cache");
		
		response.getWriter().write(""+dropDownAjax.toString()+""); 
	
	
	}
	catch(Exception e){
		e.printStackTrace();
	}finally{
		connection.close();
	}
	return null;
}
public String saveNew1() throws Exception{
	if(!verifyLogin(request)){
		return "login";
	}
	String thirdPartyType = request.getParameter("thirdPartyType");
	String thirdPartyCompanyName = request.getParameter("thirdPartyCompanyName");
	String compnyEmail = request.getParameter("compnyEmail");
	String compnyTelephone = request.getParameter("compnyTelephone");
	String outInvoiceLimit = request.getParameter("outInvoiceLimit");
	String accountWarningLimit = request.getParameter("accountWarningLimit");
	String dnaLimit = request.getParameter("dnaLimit");
	ThirdParty thirdParty = new ThirdParty();
	
	
	thirdParty.setType(thirdPartyType);
	thirdParty.setCompanyName(thirdPartyCompanyName);
	thirdParty.setCompnyTelephone(compnyTelephone);
	thirdParty.setCompnyEmail(compnyEmail);
	
	//Account Setting
	

	thirdParty.setAccountWarningLimit(accountWarningLimit);
	thirdParty.setDnaLimit(dnaLimit);
	thirdParty.setOutInvoiceLimit(outInvoiceLimit);
	

	thirdParty.setClinicId(loginInfo.getId());
	
	Connection connection = null;
	try{
		connection = Connection_provider.getconnection();
	ThirdPartyDAO  thirdPartyDAO = new JDBCThirdPartyDAO(connection);
	
	int result = thirdPartyDAO.saveNewDetails(thirdPartyType,thirdParty);
	
	
	 ArrayList<ThirdParty>ajaxTypeNameList = thirdPartyDAO.getThirdPartyDetailsList(loginInfo.getId());
	 
	 StringBuffer dropDownAjax = new StringBuffer();
		dropDownAjax.append("<select id='typeName' name = 'typeName' class = 'form-control chosen'>");
			dropDownAjax.append("<option value = '0'>Select Company</option>");
			if(ajaxTypeNameList.size()!=0){
				for(ThirdParty tParty : ajaxTypeNameList){
					dropDownAjax.append("<option value = '"+tParty.getId()+"'>"+tParty.getCompanyName()+"</option>");
				}
				
			}
		dropDownAjax.append("</select>");
		response.setContentType("text/html");
		response.setHeader("Cache-Control", "no-cache");
		
		response.getWriter().write(""+dropDownAjax.toString()+""); 
	
	
	}
	catch(Exception e){
		e.printStackTrace();
	}finally{
		connection.close();
	}
	return null;
}

public String checkEmailExist() throws IOException, SQLException{
	if(!verifyLogin(request)){
		return "login";
	}
	Connection connection = null;
	String email = (String)request.getParameter("email").trim();
	
	connection = Connection_provider.getconnection();
	ThirdPartyDAO  thirdPartyDAO = new JDBCThirdPartyDAO(connection);
	// check if user with given user id already exist
	boolean emailId = thirdPartyDAO.isEmailIdExist(email);

	// if user id already exist then set response 'false'
	if(emailId){
		response.getWriter().write("false");
	}else{	// else set response 'true'
		response.getWriter().write("true");
	}
	return null;
	
}

public String checkMobileExist () throws IOException, SQLException{
	if(!verifyLogin(request)){
		return "login";
	}
	Connection connection = null;
	String telephone = (String)request.getParameter("mob").trim();
	
	connection = Connection_provider.getconnection();
	ThirdPartyDAO  thirdPartyDAO = new JDBCThirdPartyDAO(connection);
	// check if user with given user id already exist
	boolean telephoneline = thirdPartyDAO.isTelePhoneExist(telephone);

	// if user id already exist then set response 'false'
	if(telephoneline){
		response.getWriter().write("false");
	}else{	// else set response 'true'
		response.getWriter().write("true");
	}
	return null;
}
public String getTPDetails() throws Exception{
	if(!verifyLogin(request)){
		return "login";
	}
	String type = request.getParameter("type");
	String tpname = request.getParameter("typename");
	ThirdParty thirdParty = new ThirdParty();
	Connection connection = null;
	try{
		connection = Connection_provider.getconnection();
		ThirdPartyDAO  thirdPartyDAO = new JDBCThirdPartyDAO(connection);
		thirdParty = thirdPartyDAO.getTPDetails(tpname);
		StringBuffer data = new StringBuffer();
		
		data.append(""+thirdParty.getType()+"/"+thirdParty.getTypeName()+"/"+thirdParty.getTelephoneLine()+"/"+thirdParty.getCompanyName()
				+"/"+thirdParty.getAddress()+"/"+thirdParty.getTown()+"/"+thirdParty.getCountry()+"/"+thirdParty.getPostcode()+"/"
				+thirdParty.getCompnyEmail()+"/"+thirdParty.getAccountWarningLimit()+"/"+thirdParty.getDnaLimit()+"/"+thirdParty.getOutInvoiceLimit()
				+"/"+thirdParty.getCreditDuration()+"/"+thirdParty.getCreditReminderDuration()+"/"+thirdParty.getDnaInitialApmt()+"/"+thirdParty.getDnafollowupApmt()
				+"/"+thirdParty.getDnafinalApmt()+"/"+thirdParty.getDnamaintenanceApmt()+"/"+thirdParty.getCompltInitialApmt()+"/"+thirdParty.getCompltfollowupApmt()
				+"/"+thirdParty.getCompltfinalApmt()+"/"+thirdParty.getCompltmaintenanceApmt()+"/"+thirdParty.getCompltInitialApmtDuration()
				+"/"+thirdParty.getCompltfollowupApmtDuration()+"/"+thirdParty.getCompltfinalApmtDuration()+"/"+thirdParty.getCompltmaintenanceApmtDuration());
		response.setContentType("text/html");
		response.setHeader("Cache-Control", "no-cache");
		
		response.getWriter().write(""+data.toString()+""); 
	}
	catch(Exception e){
		e.printStackTrace();
	}finally{
		connection.close();
	}
	return null;
}
public String editTPDetailsNew() throws Exception{
	if(!verifyLogin(request)){
		return "login";
	}
	String id = request.getParameter("selectedid");
	
	session.setAttribute("thirdpartyeditappointmenttypeid", id);
	
	Connection connection = null;
	try{
		connection = Connection_provider.getconnection();
		ThirdPartyDAO  thirdPartyDAO = new JDBCThirdPartyDAO(connection);
		ThirdParty thirdParty = new ThirdParty();
		
		
		thirdParty = thirdPartyDAO.getTPDetails(id);
		ArrayList<Client> tpnameList = new ArrayList<Client>();
		tpnameList = thirdPartyDAO.getTPNameList(thirdParty.getType());
		thirdPartyForm.setThirdPartyTypeNameList(tpnameList);
		
		ArrayList<ThirdParty> tpnameListNew = new ArrayList<>();
		tpnameListNew = thirdPartyDAO.getTPNameListNew(thirdParty.getType());
		thirdPartyForm.setTpnameList(tpnameListNew);
		thirdPartyForm.setCampArea(thirdParty.isCampArea());
		thirdPartyForm.setEnrollcode(thirdParty.isEnrollcode());
		thirdPartyForm.setCity(thirdParty.getCity());
		thirdPartyForm.setState(thirdParty.getState());
		
		thirdPartyForm.setType(thirdParty.getType());
		thirdPartyForm.setTypeName(id);
		thirdPartyForm.setTelephoneLine(thirdParty.getTelephoneLine());
		thirdPartyForm.setCompnyEmail(thirdParty.getCompnyEmail());
		thirdPartyForm.setAddress(thirdParty.getAddress());
		thirdPartyForm.setTown(thirdParty.getTown());
		thirdPartyForm.setPostcode(thirdParty.getPostcode());
		thirdPartyForm.setCountry(thirdParty.getCountry());
		thirdPartyForm.setSecondLineAddress(thirdParty.getSecondLineAddress());
		thirdPartyForm.setFax(thirdParty.getFax());
		thirdPartyForm.setWeb(thirdParty.getWeb());
		thirdPartyForm.setEmailCc(thirdParty.getEmailCc());
		thirdPartyForm.setTpAccountSettingNotes(thirdParty.getTpAccountSettingNotes());
		thirdPartyForm.setDnaForAll(thirdParty.isDnaForAll());

		

		
		//Account Setting
		
		thirdPartyForm.setAccountWarningLimit(thirdParty.getAccountWarningLimit());
		thirdPartyForm.setDnaLimit(thirdParty.getDnaLimit());
		thirdPartyForm.setOutInvoiceLimit(thirdParty.getOutInvoiceLimit());
		thirdPartyForm.setCreditDuration(thirdParty.getCreditDuration());
		thirdPartyForm.setCreditReminderDuration(thirdParty.getCreditReminderDuration());
		
		//set initialAppointment Type Data
		ThirdParty appointmentChargesData = thirdPartyDAO.getAppointmentChargesData(id,Constants.INITIAL_APPOINTMENT_TYPE);
		thirdPartyForm.setDnaInitialCharge(appointmentChargesData.getDnaCharge());
		thirdPartyForm.setCompletedInitialCharge(appointmentChargesData.getApmtCharge());
		thirdPartyForm.setInitialOffsetdna(appointmentChargesData.isOffset());
		thirdPartyForm.setCompltInitialApmtDuration(appointmentChargesData.getApmtDuaration());
		thirdPartyForm.setDnaInitialApmt(appointmentChargesData.getDnaName());
		
		//set followup Type Data
		appointmentChargesData = thirdPartyDAO.getAppointmentChargesData(id,Constants.FOLLOWUP_APPOINTMENT_TYPE);
		thirdPartyForm.setDnaFollowupCharge(appointmentChargesData.getDnaCharge());
		thirdPartyForm.setCompletedFollowupCharge(appointmentChargesData.getApmtCharge());
		thirdPartyForm.setFollowupOffsetdna(appointmentChargesData.isOffset());
		thirdPartyForm.setCompltfollowupApmtDuration(appointmentChargesData.getApmtDuaration());
		thirdPartyForm.setDnafollowupApmt(appointmentChargesData.getDnaName());
		
		
		//set Final Type Data
		appointmentChargesData = thirdPartyDAO.getAppointmentChargesData(id,Constants.FINAL_APPOINTMENT_TYPE);
		thirdPartyForm.setDnaFinalCharge(appointmentChargesData.getDnaCharge());
		thirdPartyForm.setCompletedFinalCharge(appointmentChargesData.getApmtCharge());
		thirdPartyForm.setFinalOffsetdna(appointmentChargesData.isOffset());
		thirdPartyForm.setCompltfinalApmtDuration(appointmentChargesData.getApmtDuaration());
		thirdPartyForm.setDnafinalApmt(appointmentChargesData.getDnaName());
		
		//set Maintnance Type Data
		appointmentChargesData = thirdPartyDAO.getAppointmentChargesData(id,Constants.MAINTNANCE_APPOINTMENT_TYPE);
		thirdPartyForm.setDnaMaintnanceCharge(appointmentChargesData.getDnaCharge());
		thirdPartyForm.setCompletedMaintnanceCharge(appointmentChargesData.getApmtCharge());
		thirdPartyForm.setMaintnanceOffsetdna(appointmentChargesData.isOffset());
		thirdPartyForm.setCompltmaintenanceApmtDuration(appointmentChargesData.getApmtDuaration());
		thirdPartyForm.setDnamaintenanceApmt(appointmentChargesData.getDnaName());
		
		thirdPartyForm.setIpdShare(thirdParty.getIpdShare());
		thirdPartyForm.setSurgeonShare(thirdParty.getSurgeonShare());
		thirdPartyForm.setOpdShare(thirdParty.getOpdShare());
		
		thirdPartyForm.setUnit(thirdParty.getUnit());
		thirdPartyForm.setArea(thirdParty.getArea());
		thirdPartyForm.setShortname(thirdParty.getShortname());
		thirdPartyForm.setMaintp(thirdParty.isMaintp());
		
		thirdPartyForm.setValidityFrom(thirdParty.getValidityFrom());
		thirdPartyForm.setValidityTo(thirdParty.getValidityTo());
		thirdPartyForm.setValidityStatus(thirdParty.getValidityStatus());
		thirdPartyForm.setPayementType(thirdParty.getPayementType());
		
	/*	//name
		thirdPartyForm.setDnaInitialApmt(thirdParty.getDnaInitialApmt());
		thirdPartyForm.setDnafollowupApmt(thirdParty.getDnafollowupApmt());
		thirdPartyForm.setDnafinalApmt(thirdParty.getDnafinalApmt());
		thirdPartyForm.setDnamaintenanceApmt(thirdParty.getDnamaintenanceApmt());
		
		
		//charges
		thirdPartyForm.setCompltInitialApmt(thirdParty.getCompltInitialApmt());
		thirdPartyForm.setCompltfollowupApmt(thirdParty.getCompltfollowupApmt());
		thirdPartyForm.setCompltfinalApmt(thirdParty.getCompltfinalApmt());
		thirdPartyForm.setCompltmaintenanceApmt(thirdParty.getCompltmaintenanceApmt());
		
		//duration
		thirdPartyForm.setCompltInitialApmtDuration(thirdParty.getCompltInitialApmtDuration());
		thirdPartyForm.setCompltfollowupApmtDuration(thirdParty.getCompltfollowupApmtDuration());
		thirdPartyForm.setCompltfinalApmtDuration(thirdParty.getCompltfinalApmtDuration());
		thirdPartyForm.setCompltmaintenanceApmtDuration(thirdParty.getCompltmaintenanceApmtDuration());
		thirdPartyForm.setClinicId(loginInfo.getId());
		
		
		thirdPartyForm.setCompltInitialApmtName(thirdParty.getInitialAppointmentName());
		thirdPartyForm.setCompltfollowupApmtName(thirdParty.getFollowupAppointmentName());
		thirdPartyForm.setCompltfinalApmtName(thirdParty.getFinalAppointmentName());
		thirdPartyForm.setCompltmaintenanceApmt(thirdParty.getMaintnanceAppointmentName());
		thirdPartyForm.setTpAccountSettingNotes(thirdParty.getTpAccountSettingNotes());*/
		
		ArrayList<DynamicAppointment> dynamicApmtTypeList = new ArrayList<DynamicAppointment>();
		dynamicApmtTypeList = thirdPartyDAO.getDynmicApmtList(id);
		if(dynamicApmtTypeList.size() > 0){
			thirdPartyForm.setDanyamiclistpresent(1);
		}
		else{
			thirdPartyForm.setDanyamiclistpresent(0);
		}
		thirdPartyForm.setDynamicApmtTypeList(dynamicApmtTypeList);
		session.setAttribute("dynamicApmtTypeList", dynamicApmtTypeList);
		
		
		//set gpdetails
		
		if(Integer.parseInt(DateTimeUtils.numberCheck(thirdParty.getType()))==1){
			GP gp = thirdPartyDAO.getGPDetails(id);
		}
		
		String patientType = request.getParameter("patientType");
		ClientDAO clientDAO = new JDBCClientDAO(connection);
		ArrayList<ThirdParty> thirdPartyTypeList = clientDAO.getPayerTypeList(patientType);
		thirdPartyForm.setThirdPartyTypeList(thirdPartyTypeList);
		
		thirdPartyForm.setPatientType(patientType);
		
		thirdPartyForm.setCompanyName(thirdParty.getCompanyName());
		
	}
	catch(Exception e){
		e.printStackTrace();
	}finally{
		connection.close();
	}
	return "addThirdPartyDetailData";

}

public String editDoctorSurgery() throws Exception{
	if(!verifyLogin(request)){
		return "login";
	}
	String id = request.getParameter("selectedid");
	
	session.setAttribute("thirdpartyeditappointmenttypeid", id);
	
	Connection connection = null;
	try{
		connection = Connection_provider.getconnection();
		ThirdPartyDAO  thirdPartyDAO = new JDBCThirdPartyDAO(connection);
		ThirdParty thirdParty = new ThirdParty();
		
		
		thirdParty = thirdPartyDAO.getTPDetails(id);
		ArrayList<Client> tpnameList = new ArrayList<Client>();
		tpnameList = thirdPartyDAO.getTPNameList(thirdParty.getType());
		thirdPartyForm.setThirdPartyTypeNameList(tpnameList);
		thirdPartyForm.setType(thirdParty.getType());
		thirdPartyForm.setTypeName(id);
		thirdPartyForm.setTelephoneLine(thirdParty.getTelephoneLine());
		thirdPartyForm.setCompnyEmail(thirdParty.getCompnyEmail());
		thirdPartyForm.setAddress(thirdParty.getAddress());
		thirdPartyForm.setTown(thirdParty.getTown());
		thirdPartyForm.setPostcode(thirdParty.getPostcode());
		thirdPartyForm.setCountry(thirdParty.getCountry());
		thirdPartyForm.setCompanyName(thirdParty.getCompanyName());
		thirdPartyForm.setCompnyFax(thirdParty.getFax());
		thirdPartyForm.setTpAccountSettingNotes(thirdParty.getTpAccountSettingNotes());
		

		
	
		

	}
	catch(Exception e){
		e.printStackTrace();
	}finally{
		connection.close();
	}
	return "showDoctorSurgeryData";
}

public String updateDoctorSurgery() throws SQLException{
	if(!verifyLogin(request)){
		return "login";
	}
	String type = thirdPartyForm.getType();
	ThirdParty thirdParty = new ThirdParty();
	//TP Details field
	String typename = thirdPartyForm.getTypeName();
	
	
	
	thirdParty.setType(type);
	String companyname = thirdPartyForm.getCompanyName();
	thirdParty.setTypeName(thirdPartyForm.getTypeName());
	thirdParty.setCompanyName(thirdPartyForm.getCompanyName());
	thirdParty.setTelephoneLine(thirdPartyForm.getTelephoneLine());
	thirdParty.setCompnyEmail(thirdPartyForm.getCompnyEmail());
	thirdParty.setAddress(thirdPartyForm.getAddress());
	thirdParty.setTown(thirdPartyForm.getTown());
	thirdParty.setPostcode(thirdPartyForm.getPostcode());
	thirdParty.setCountry(thirdPartyForm.getCountry());
	thirdParty.setFax(thirdPartyForm.getCompnyFax());
	thirdParty.setTpAccountSettingNotes(thirdPartyForm.getTpAccountSettingNotes());
	
	
	Connection connection = null;
		try{
	connection = Connection_provider.getconnection();
	ThirdPartyDAO  thirdPartyDAO = new JDBCThirdPartyDAO(connection);
	int update  = thirdPartyDAO.updateDoctorSurgery(typename,thirdParty);
	thirdPartyForm.setMessage("Doctor Surgery Sucessfully!!");
	addActionMessage("Doctor Surgery Added Sucessfully!!");
	setFormData();
	
	}catch (Exception e) {
	e.printStackTrace();
	}finally{
	
	connection.close();
	}
	
	return SUCCESS;
}



public String addcharges() throws Exception{
	
	Connection connection=null;
	try {
		connection=Connection_provider.getconnection();
		ThirdPartyDAO thirdPartyDAO=new JDBCThirdPartyDAO(connection);
		DischargeOutcomeDAO outcomeDAO=new JDBCDischargeOutcomeDAO(connection);
		EmrDAO emrDAO=new JDBCEmrDAO(connection);
		BedDao bedDao=new JDBCBedDao(connection);
		ArrayList<ThirdParty> thirdPartyList=thirdPartyDAO.getMainTPListNew("0");
		thirdPartyForm.setThirdPartyList(thirdPartyList);
		
		ArrayList<Master>newChargeTypeList = outcomeDAO.getMasterChargeTypeList();
		thirdPartyForm.setNewChargeTypeList(newChargeTypeList);
		
	/*	ArrayList<AppointmentType> appointmentTypeList=appointmentTypeDAO.getSelfChargesList();
		thirdPartyForm.setAppointmentTypeList(appointmentTypeList);*/
		
		ArrayList<Bed> wardlist=bedDao.getAllWardList("0");
		thirdPartyForm.setWardlist(wardlist);
		
		ArrayList<Master> invsTypeList = emrDAO.getInvesigationTypeList();
		thirdPartyForm.setInvsTypeList(invsTypeList);
		
		String frompayercreation = DateTimeUtils.numberCheck(request.getParameter("frompayercreation"));
		thirdPartyForm.setFrompayercreation(frompayercreation);
		
	} catch (Exception e) {

		e.printStackTrace();
	}
	finally {
		
		connection.close();
	}
	
	return "addcharges";
}



public String getcharges() throws Exception {
	
	Connection connection=null;
	try {	
		
		connection=Connection_provider.getconnection();
		AppointmentTypeDAO appointmentTypeDAO=new JDBCAppointmentTypeDAO(connection);
		String chargeType=request.getParameter("chargetype");
		String tpid=request.getParameter("tpid");
		String wardid=request.getParameter("wardid");
		
		
		StringBuffer buffer=new StringBuffer();
		if(tpid==null){
			tpid="0";
		}
		if(tpid.equals("")){
			tpid="0";
		}
		ThirdParty thirdParty=new ThirdParty();
		if(!tpid.equals("0")){
			ThirdPartyDAO thirdPartyDAO=new JDBCThirdPartyDAO(connection);
			thirdParty=thirdPartyDAO.getThirdPartyDetails(tpid);
		}
		ArrayList<AppointmentType> chargeList=appointmentTypeDAO.getChargesList(chargeType,thirdParty.getChargecolumnname());
		ArrayList<AppointmentType> chargeExistList=appointmentTypeDAO.getNewChargeList(tpid,wardid,chargeType,"0");
		ArrayList<String> stdhourlist= appointmentTypeDAO.getStdChargeHourList();
		
		String hidden="hidden";
		
		if(loginInfo.isLmh()){
			hidden ="";
		}	
		
		int i=0;
		for(AppointmentType appointmentType:chargeList){
			
			String mrp="";
			String code="";
			boolean ischecked=false;
			String stdcharge="0";
			String onoff="0";
			String hours="24";
			String ratio="0";
			String discount ="";
			String ndsId = ""+appointmentType.getId();
			boolean nds_flag = false;
			double chargeAmount =0;
			String actualamount ="";
			double discountPer=0;
			double discountamt=0;
			double mrp1=0;
			for(AppointmentType type:chargeExistList){
				
					int chargeid=Integer.parseInt(type.getChargeid());
					
				     if(chargeid==appointmentType.getId()){
				    	 ischecked=true;
				    	 mrp=type.getCharges();
				    	 code=type.getCode();
				    	 stdcharge=type.getStdcharge();
						 onoff= type.getOnoff();
						 hours= type.getHours();
						 ratio= type.getRatio();
						 discount= type.getDiscountShare(); 
						 chargeAmount = type.getOriginalAmt();
						 actualamount = type.getActualAmount();
							/*
							 * if(loginInfo.isLmh()) { ///for vspm discountamt =
							 * Double.parseDouble(actualamount) * (Double.parseDouble(discount)/100);
							 * mrp1=Double.parseDouble(actualamount)-discountamt; }// end vspm
							 */						 
						 discountPer = Double.parseDouble(DateTimeUtils.numberCheck(type.getDiscountShare()));
						 break;
				     }
				
			}
			
			if(loginInfo.isLmh()){
				if(!ischecked){
					mrp =appointmentType.getCharges();
					code = appointmentType.getCode();
				}
			}	
			
			
			
			nds_flag = appointmentTypeDAO.checkChargeInNDS(ndsId,tpid);
			
			buffer.append("<tr>");
			buffer.append("<td class='text-align-center'>");
			if(ischecked){
				buffer.append("<input onclick='calculateChargeDiscount()' class='case' type='checkbox' checked='checked'  id='ch"+appointmentType.getId()+"' name='ch"+appointmentType.getId()+"' value='"+appointmentType.getId()+"' /> ");
			} else {
				buffer.append("<input onclick='calculateChargeDiscount()' class='case' type='checkbox'  id='ch"+appointmentType.getId()+"' name='ch"+appointmentType.getId()+"' value='"+appointmentType.getId()+"' /> ");
			}
			buffer.append("</td>");
			
			buffer.append("<td>");
			buffer.append(""+appointmentType.getName()+""); 
			buffer.append("</td>");
			
			buffer.append("<td>");
			if(discountPer>0){
				buffer.append("<input  type='text' title='enter Amount' onchange='calculateChargeDiscount()' value='"+actualamount+"' id='mrp"+appointmentType.getId()+"' name='mrp"+appointmentType.getId()+"' class='form-control text-align-right' placeholder='Enter Amount' style='width:100%;'/>");
			}else{
				buffer.append("<input  type='text' title='enter Amount' onchange='calculateChargeDiscount()' value='"+mrp+"' id='mrp"+appointmentType.getId()+"' name='mrp"+appointmentType.getId()+"' class='form-control text-align-right' placeholder='Enter Amount' style='width:100%;'/>");
			}
			buffer.append("</td>");
			
			buffer.append("<td class='"+hidden+"'>");
			if(nds_flag){
				buffer.append("<input type='hidden' id='isndscharge"+appointmentType.getId()+"' value='1'>");
				buffer.append("<input type='hidden' readonly='readonly' title='Discount' onchange='calculateChargeDiscount()' value='"+discount+"' id='discount"+appointmentType.getId()+"' name='discount"+appointmentType.getId()+"' class='form-control text-align-right' placeholder='Discount %' style='width:100%;'/>");
				buffer.append("<label style='color: #b70606;' class='text-align-right'>Non-Discount Charge</label>");
			}else{
				buffer.append("<input type='hidden' id='isndscharge"+appointmentType.getId()+"' value='0'>");
				buffer.append("<input type='text' title='Discount' onchange='calculateChargeDiscount()' value='"+discount+"' id='discount"+appointmentType.getId()+"' name='discount"+appointmentType.getId()+"' class='form-control text-align-right' placeholder='Discount %' style='width:100%;'/>");
			}
			buffer.append("<input type='hidden' id='discountAmt"+appointmentType.getId()+"' name='discountAmt"+appointmentType.getId()+"' value='0'>");
			buffer.append("</td>");
			
			buffer.append("<td class='"+hidden+"'>");
			
			buffer.append("<input readonly='readonly' type='text' title='Charge Amount' value='"+mrp+"' id='chargeAmount"+appointmentType.getId()+"' name='chargeAmount"+appointmentType.getId()+"' class='form-control text-align-right' placeholder='Charge Amount' style='width:100%;'/>");

		
			//buffer.append("<input readonly='readonly' type='text' title='Charge Amount' value='"+mrp+"' id='chargeAmount"+appointmentType.getId()+"' name='chargeAmount"+appointmentType.getId()+"' class='form-control text-align-right' placeholder='Charge Amount' style='width:100%;'/>");
			buffer.append("</td>");
			
			buffer.append("<td class=''>");
			buffer.append("<input type='text' class='form-control text-align-right' value='"+code+"' id='code"+appointmentType.getId()+"' title='enter code' name='code"+appointmentType.getId()+"'  placeholder='Enter Code' style='width:100%;'/>");
			buffer.append("</td>");
			
			buffer.append("<td class='text-align-center'>");
			if(stdcharge.equals("1")){
				buffer.append("<input  type='checkbox' checked='checked' onclick='setOnOff(this,"+appointmentType.getId()+")' id='ch1"+appointmentType.getId()+"' /> <input type='hidden' id='standardcharge"+appointmentType.getId()+"' name='standardcharge"+appointmentType.getId()+"'  value='1'   /> ");
			}else{
				buffer.append("<input  type='checkbox' onclick='setOnOff(this,"+appointmentType.getId()+")' id='ch1"+appointmentType.getId()+"' /> <input type='hidden' id='standardcharge"+appointmentType.getId()+"' name='standardcharge"+appointmentType.getId()+"' value='0'   /> ");
			}
			buffer.append("</td>");
			
			buffer.append("<td class='text-align-center'>");
			if(onoff.equals("1")){
				buffer.append("<input type='checkbox' checked='checked'  onclick='setOnVal(this,"+appointmentType.getId()+")'   id='ch2"+appointmentType.getId()+"' /> <input type='hidden' id='onoff"+appointmentType.getId()+"' name='onoff"+appointmentType.getId()+"' value='1'   />");
			} else if(stdcharge.equals("1")) {
				buffer.append("<input type='checkbox' onclick='setOnVal(this,"+appointmentType.getId()+")'   id='ch2"+appointmentType.getId()+"' /> <input type='hidden' id='onoff"+appointmentType.getId()+"' name='onoff"+appointmentType.getId()+"' value='0'  />");
			} else {
				buffer.append("<input type='checkbox' disabled='disabled' onclick='setOnVal(this,"+appointmentType.getId()+")'   id='ch2"+appointmentType.getId()+"' /> <input type='hidden' id='onoff"+appointmentType.getId()+"' name='onoff"+appointmentType.getId()+"' value='0'  />");
			}
			buffer.append("</td>");
			
			buffer.append("<td>");
			buffer.append("<select class='form-control' id='hour"+appointmentType.getId()+"' name='hour"+appointmentType.getId()+"'  > ");
			buffer.append("<option value='0'>Select Hours</option>");
			for(String hour: stdhourlist){
				if(hour!=null){
					 if(hour.equals(hours)){
						 buffer.append("<option selected='selected' value='"+hour+"'> "+hour+" hour</option>");
					 } else {
						 buffer.append("<option value='"+hour+"'> "+hour+" hour</option>");
					 }
				} else {
						buffer.append("<option value='"+hour+"'> "+hour+" hour</option>");
				}
			}
			buffer.append("</select>");
			buffer.append("</td>");
			
			buffer.append("<td>");
			buffer.append("<select  class='form-control' id='ratio"+appointmentType.getId()+"' name='ratio"+appointmentType.getId()+"' > ");
			if(ratio!=null){
				 if(ratio.equals("0")){
					 	buffer.append("<option selected='selected' value='0'>Full</option>");
						buffer.append("<option value='1'>Half</option>");
				 } else {
					 	buffer.append("<option value='0'>Full</option>");
						buffer.append("<option selected='selected' value='1'>Half</option>");
				 }
			}else {
				buffer.append("<option selected='selected' value='0'>Full</option>");
				buffer.append("<option value='1'>Half</option>");
			}
			buffer.append("</select>");
			buffer.append("</td>");
			
			buffer.append("</tr>");
			i++;
		}
		
		response.setContentType("text/html");
		response.setHeader("Cache-Control", "no-cache");
		response.getWriter().write("" + buffer.toString() + "");
		
		
	} catch (Exception e) {

		e.printStackTrace();
	}finally{
		connection.close();
	}
	
	return null;
}



public String savecharges() throws Exception {
	
	Connection connection=null; 
	try {
		connection=Connection_provider.getconnection();
		AppointmentTypeDAO appointmentTypeDAO=new JDBCAppointmentTypeDAO(connection);
		
		String notes=thirdPartyForm.getNotes();
		String tpid=thirdPartyForm.getTpid();
		String chargeType=thirdPartyForm.getChargeType();
		String payee=thirdPartyForm.getPayee();
		String wardid=thirdPartyForm.getWardid();
		String invstype=thirdPartyForm.getInvstype();
		
		if(invstype==null){
			invstype="0";
		}
		if(invstype.equals("")){
			invstype="0";
		}
		if (tpid==null) {
			tpid="0";
		}
		if(tpid.equals("")){
			tpid="0";
		}
		for(String tid:notes.split(",")){
			
			if(tid.equals("0")){
				continue;
			}
			String mrp=request.getParameter("mrp"+tid);
			String code=request.getParameter("code"+tid);
			String standardcharge= request.getParameter("standardcharge"+tid);
			String onoff= request.getParameter("onoff"+tid);
			String hour= request.getParameter("hour"+tid);
			String ratio= request.getParameter("ratio"+tid);
			String discount= request.getParameter("discount"+tid);
			String discountAmt = request.getParameter("discountAmt"+tid);
			String chargeAmount = request.getParameter("chargeAmount"+tid);
			
			discount = DateTimeUtils.numberCheck(discount);
			discountAmt = DateTimeUtils.numberCheck(discountAmt);
			
			int chargeid=0;
			if(DateTimeUtils.numberCheck(tpid).equals("0")){
				chargeid = DateTimeUtils.convertToInteger(tid);
				if(DateTimeUtils.isNull(chargeType).equals("INVESTIGATION")){
					chargeid = appointmentTypeDAO.getChargeIdIfExists(tpid,wardid,chargeType,tid,invstype);
				}else if(DateTimeUtils.convertToInteger(wardid)!=0){
					chargeid = appointmentTypeDAO.getChargeIdIfExists(tpid,wardid,chargeType,tid,invstype);
				}
			}else{
				chargeid = appointmentTypeDAO.getChargeIdIfExists(tpid,wardid,chargeType,tid,invstype);
			}
			
					
			if(chargeid>0){
				//old data
				   AppointmentType old_thirdparty_charges=appointmentTypeDAO.getoldThirdPartyCharges(chargeid);
				//update
				int result=appointmentTypeDAO.updateThirdPartyCharges(chargeid,mrp,code,standardcharge,onoff,hour,ratio,tpid,discount,discountAmt,chargeAmount);
				//to store log of payer update
				if(result>0){
					DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				    Calendar cal = Calendar.getInstance();
				    String datetime = dateFormat.format(cal.getTime());  
					int res = appointmentTypeDAO.savePayerCharges(old_thirdparty_charges,loginInfo.getUserId(),tid,mrp,code,chargeType,tpid,payee,wardid,invstype,standardcharge,onoff,hour,ratio,discount,discountAmt,chargeAmount,datetime);
				}
				if(chargeType.equals("INVESTIGATION")){
					int upd = appointmentTypeDAO.updateinvstgationChargeName(chargeid,chargeType,invstype,tid,mrp,code,tpid,discount,discountAmt,chargeAmount);
				}
				
			} else {
				//insert 
				int result=appointmentTypeDAO.saveThirdPartyCharges(tid,mrp,code,chargeType,tpid,payee,wardid,invstype,standardcharge,onoff,hour,ratio,discount,discountAmt,chargeAmount);
			}
			
			
		}
		if(DateTimeUtils.numberCheck(thirdPartyForm.getFrompayercreation()).equals("1")){
			return "addchargemaster";
		}
		
	} catch (Exception e) {

		e.printStackTrace();
	}
	finally {
		connection.close();
	}
	
	return "showList";
}


public String getinvnames() throws Exception {
	
	Connection connection=null;
	try {
		
		connection=Connection_provider.getconnection();
		InvestigationDAO investigationDAO=new JDBCInvestigationDAO(connection);
		AppointmentTypeDAO appointmentTypeDAO=new JDBCAppointmentTypeDAO(connection); 
		String invstype=request.getParameter("invstype");
		String tpid=request.getParameter("tpid");
		String wardid=request.getParameter("wardid");
		
		String chargeType="INVESTIGATION";
		
		if(invstype==null){
			invstype="0";
		}
		if(invstype.equals("")){
			invstype="0";
		}
		StringBuffer buffer=new StringBuffer();
		
		ArrayList<Master> invnamList=investigationDAO.getNameList(invstype);
		int roundcharge = investigationDAO.checkRoundCharge(invstype);
		//if(roundcharge==1){
			invnamList = new ArrayList<Master>();
			String chargename = investigationDAO.getMasterChargeName(invstype);
			Master m = new Master();
			m.setId(Integer.parseInt(invstype));
			m.setName(chargename);
			
			invnamList.add(m);
			
		//}
		
		String hidden="hidden";
		
		if(loginInfo.isLmh()){
			hidden ="";
		}	
		
		ArrayList<AppointmentType> chargeExistList=appointmentTypeDAO.getInvestigationChargeList(tpid,wardid,chargeType,invstype,m.getName());
		
		int i=0;
		for(Master master:invnamList){
			
			String mrp="";
			String code="";
			boolean ischecked=false;
			
			for(AppointmentType type:chargeExistList){
				     
				     if(master.getName().equals(type.getName())){
				    	 ischecked=true;
				    	 mrp=type.getCharges();
				    	 code=type.getCode();
				    	 break;
				     }
				
			}
			/*buffer.append("<div class='col-lg-12 col-md-12 col-sm-12' style='padding:0px;border-top: 1px solid #ddd;padding-top: 2px;'>");
			buffer.append("<div class='col-lg-8 col-md-8 col-sm-8' style='padding:0px;'>");
			buffer.append("<div class='form-group'>");

			if(ischecked){
				buffer.append("<input class='case' type='checkbox' checked='checked'  id='ch"+master.getId()+"' name='ch"+master.getId()+"' value='"+master.getId()+"' /> ");
			} else {
				buffer.append("<input class='case' type='checkbox'  id='ch"+master.getId()+"' name='ch"+master.getId()+"' value='"+master.getId()+"' /> ");
			}
			
			buffer.append(""+master.getName()+""); 
			buffer.append("</div> </div>");
			
			buffer.append("<div class='col-lg-4 col-md-4 col-sm-4' style='padding:0px;'>");
			buffer.append("<div class='form-inline'>");
			
			buffer.append("<div class='form-group' style='width:49%'>");
			buffer.append("<input type='text' title='enter mrp' value='"+mrp+"' id='mrp"+master.getId()+"' name='mrp"+master.getId()+"' class='form-control' placeholder='Enter MRP' style='width:100%;'/>");
			buffer.append("</div>");
			
			buffer.append("<div class='form-group' style='width:49%'>");
			buffer.append("<input type='text' class='form-control' value='"+code+"' id='code"+master.getId()+"' title='enter code' name='code"+master.getId()+"'  placeholder='Enter Code' style='width:100%;'/>");
			buffer.append("</div>");
			
			buffer.append("<input type='hidden' name='standardcharge"+master.getId()+"'  id='standardcharge"+master.getId()+"' value='0' />");
			buffer.append("<input type='hidden' name='hour"+master.getId()+"' id='hour"+master.getId()+"' value='0' />");
			buffer.append("<input type='hidden' name='ratio"+master.getId()+"' id='ratio"+master.getId()+"' value='0' />");
			
			buffer.append("</div>");
			buffer.append("</div>");
			buffer.append("</div>");*/
			
			
			buffer.append("<tr>");
			buffer.append("<td class='text-align-center'>");
			if(ischecked){
				buffer.append("<input onclick='calculateChargeDiscount()' class='case' type='checkbox' checked='checked'  id='ch"+master.getId()+"' name='ch"+master.getId()+"' value='"+master.getId()+"' /> ");
			} else {
				buffer.append("<input onclick='calculateChargeDiscount()' class='case' type='checkbox'  id='ch"+master.getId()+"' name='ch"+master.getId()+"' value='"+master.getId()+"' /> ");
			}
			buffer.append("</td>");
			buffer.append("<td>");
			buffer.append(""+master.getName()+""); 
			buffer.append("</td>");
			
			
			buffer.append("<td>");
			buffer.append("<input type='text' title='enter mrp' value='"+mrp+"' id='mrp"+master.getId()+"' name='mrp"+master.getId()+"' class='form-control' placeholder='Enter MRP' style='width:100%;'/>");
			buffer.append("</td>");
			
			buffer.append("<td class='"+hidden+"'>");
			buffer.append("<input type='hidden' id='isndscharge"+master.getId()+"' value='1'>");
			buffer.append("<input type='hidden' readonly='readonly' title='Discount' onchange='calculateChargeDiscount()' value='"+0+"' id='discount"+master.getId()+"' name='discount"+master.getId()+"' class='form-control text-align-right' placeholder='Discount %' style='width:100%;'/>");
			buffer.append("<label style='color: #b70606;' class='text-align-right'>Non-Discount Charge</label>");
			buffer.append("<input type='hidden' id='discountAmt"+master.getId()+"' name='discountAmt"+master.getId()+"' value='0'>");
			buffer.append("</td>");
			
			buffer.append("<td class='"+hidden+"'>");
			buffer.append("<input readonly='readonly' type='text' title='Charge Amount' value='"+mrp+"' id='chargeAmount"+master.getId()+"' name='chargeAmount"+master.getId()+"' class='form-control text-align-right' placeholder='Charge Amount' style='width:100%;'/>");
			buffer.append("</td>");
			
			buffer.append("<td class=''>");
			buffer.append("<input type='text' class='form-control text-align-right' value='"+code+"' id='code"+master.getId()+"' title='enter code' name='code"+master.getId()+"'  placeholder='Enter Code' style='width:100%;'/>");
			buffer.append("</td>");
			
			buffer.append("<td class='text-align-center'>");
			buffer.append("<input type='hidden' name='standardcharge"+master.getId()+"'  id='standardcharge"+master.getId()+"' value='0' />");
			buffer.append("<input type='hidden' name='hour"+master.getId()+"' id='hour"+master.getId()+"' value='0' />");
			buffer.append("<input type='hidden' name='ratio"+master.getId()+"' id='ratio"+master.getId()+"' value='0' />");
			buffer.append("</td>");
			buffer.append("<td class='text-align-center'>");
			buffer.append("</td>");
			buffer.append("<td>");
			buffer.append("</td>");
			buffer.append("<td>");
			buffer.append("</td>");
			
			buffer.append("</tr>");
			
			
			i++;
		}
		
		response.setContentType("text/html");
		response.setHeader("Cache-Control", "no-cache");
		response.getWriter().write("" + buffer.toString() + "");
		
		
		
	} catch (Exception e) {

		e.printStackTrace();
	}finally{
		connection.close();
	}
	
	
	return null;
}


public String getPayerTypeListData() throws Exception{
	Connection connection = null;
	try{
		connection = Connection_provider.getconnection();
		String id = request.getParameter("id");
		ClientDAO clientDAO = new JDBCClientDAO(connection);
		ArrayList<ThirdParty>ajaxTypeNameList = clientDAO.getPayerTypeList(id);
		StringBuffer dropDownAjax = new StringBuffer();
		dropDownAjax.append("<label>Payer Type<span class='text-danger'>*</span></label><br>");
		dropDownAjax.append("<select id='type1' name ='type'  onchange='setTypeName(this.value)' class='form-control showToolTip chosen-select' data-toggle='tooltip'>");
		dropDownAjax.append("<option value = '0'>Select Payer Type</option>");
		if(ajaxTypeNameList.size()!=0){
			for(ThirdParty client : ajaxTypeNameList){
				dropDownAjax.append("<option value = '"+client.getId()+"'>"+client.getTypeName()+"</option>");
			}
					
		}
		dropDownAjax.append("</select>");
		dropDownAjax.append("<label  id = 'tpTypeError' class='text-danger'></label>	");
		response.setContentType("text/html");
		response.setHeader("Cache-Control", "no-cache");
		response.getWriter().write(""+dropDownAjax.toString()+""); 
		
	}catch (Exception e) {
		e.printStackTrace();
	}finally{
		try {
			connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	return null;
	
	
}




public void prepare() throws Exception {
	Connection connection = null;
	try{
		connection = Connection_provider.getconnection();
		ThirdPartyDAO  thirdPartyDAO = new JDBCThirdPartyDAO(connection);

		ArrayList<ThirdParty>thirdPartyTypeList = thirdPartyDAO.getThirdPartyList();
		thirdPartyForm.setThirdPartyTypeList(thirdPartyTypeList);
		thirdPartyForm.setCountryList(PopulateList.countryList());

		thirdPartyForm.setCountry("India");
	
		//ArrayList<Client> thirdPartyTypeNameList = new ArrayList<Client>();
		//thirdPartyTypeNameList = clientDAO.getThirdPartyTypeName();
		//thirdPartyForm.setThirdPartyTypeNameList(thirdPartyTypeNameList);
		
		thirdPartyForm.setApmtDurationList(PopulateList.apmtDurationList());
		ArrayList<ThirdParty> tpnameList = new ArrayList<ThirdParty>();
		tpnameList = thirdPartyDAO.getTPNameList();
		thirdPartyForm.setTpnameList(tpnameList);
		MasterDAO masterDAO = new JDBCMasterDAO(connection);
		ArrayList<Master> masterlist = masterDAO.getMasterList(loginInfo);
		thirdPartyForm.setMasterlist(masterlist);
		String selectedid = request.getParameter("selectedid");
		thirdPartyForm.setMastername(selectedid);
		
		InventoryVendorDAO inventoryVendorDAO = new JDBCInventoryVendorDAO(connection);
		ArrayList<Master> stateList= inventoryVendorDAO.getAllStateList();
		ArrayList<Master> cityList= inventoryVendorDAO.getAllCityList();
		thirdPartyForm.setStateList(stateList);
		thirdPartyForm.setCityList(cityList);
	}catch (Exception e) {
		e.printStackTrace();
	}finally{
		
		connection.close();
	}
 
	
}

public ThirdPartyForm getModel() {
	// TODO Auto-generated method stub
	return thirdPartyForm;
}
	


public String newThirdpartyCharge()throws Exception{
	Connection connection=null;
	try {
		connection=Connection_provider.getconnection();
		ThirdPartyDAO thirdPartyDAO=new JDBCThirdPartyDAO(connection);
		ArrayList<ThirdParty> chargeslist=new ArrayList<ThirdParty>();
		String action =request.getParameter("action");
		String tpidnew=request.getParameter("tpidnew");
		String tpidold=request.getParameter("tpidold");
		ArrayList<ThirdParty> thirdPartyList=thirdPartyDAO.getMainTPListNew("1");
		thirdPartyForm.setThirdPartyList(thirdPartyList);
		if(action.equals("1")) {
			chargeslist=thirdPartyDAO.getChargeslistbytpid(tpidold,tpidnew);
			
			return "createcharge";
		}
		
	} catch (Exception e) {
		e.printStackTrace();
	}
	return "newcharge";
}
}
