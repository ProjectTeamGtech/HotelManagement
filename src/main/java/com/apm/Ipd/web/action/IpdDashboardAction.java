package com.apm.Ipd.web.action;

import java.sql.Connection;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.apm.Accounts.eu.bi.AccountsDAO;
import com.apm.Accounts.eu.bi.ChargesAccountProcessingDAO;
import com.apm.Accounts.eu.blogic.jdbc.JDBCAccountsDAO;
import com.apm.Accounts.eu.blogic.jdbc.JDBCChargeAccountProcesDAO;
import com.apm.Accounts.eu.entity.Accounts;
import com.apm.Appointment.eu.bi.AppointmentDAO;
import com.apm.Appointment.eu.blogic.jdbc.JDBCAppointmentDAO;
import com.apm.Appointment.eu.entity.AppointmentType;
import com.apm.Bloodbank.eu.bi.BloodbankDAO;
import com.apm.Bloodbank.eu.blogic.jdbc.JDBCBloodBankDAO;
import com.apm.Bloodbank.eu.entity.Bloodbank;
import com.apm.DiaryManagement.eu.bi.ClientDAO;
import com.apm.DiaryManagement.eu.bi.CompleteAptmDAO;
import com.apm.DiaryManagement.eu.bi.DiaryManagementDAO;
import com.apm.DiaryManagement.eu.bi.NotAvailableSlotDAO;
import com.apm.DiaryManagement.eu.blogic.jdbc.JDBCClientDAO;
import com.apm.DiaryManagement.eu.blogic.jdbc.JDBCCompleteAptmDAO;
import com.apm.DiaryManagement.eu.blogic.jdbc.JDBCDiaryManagentDAO;
import com.apm.DiaryManagement.eu.blogic.jdbc.JDBCNotAvailableSlotDAO;
import com.apm.DiaryManagement.eu.entity.Breadcrumbs;
import com.apm.DiaryManagement.eu.entity.Client;
import com.apm.DiaryManagement.eu.entity.CompleteAppointment;
import com.apm.DiaryManagement.eu.entity.Priscription;
import com.apm.DiaryManagement.web.action.SmsService;
import com.apm.Dietary.eu.bi.DietaryDetailsDAO;
import com.apm.Dietary.eu.blogic.jdbc.JDBCDietaryDetailsDAO;
import com.apm.Dietary.eu.entity.DietaryDetails;
import com.apm.Emr.eu.bi.EmrDAO;
import com.apm.Emr.eu.bi.InvestigationDAO;
import com.apm.Emr.eu.blogic.jdbc.JDBCEmrDAO;
import com.apm.Emr.eu.blogic.jdbc.JDBCInvestigationDAO;
import com.apm.Emr.eu.entity.Investigation;
import com.apm.Inventory.eu.bi.InventoryVendorDAO;
import com.apm.Inventory.eu.blogic.jdbc.JDBCInventoryVendorDAO;
import com.apm.Ipd.eu.bi.BedDao;
import com.apm.Ipd.eu.bi.IpdDAO;
import com.apm.Ipd.eu.blogic.jdbc.JDBCBedDao;
import com.apm.Ipd.eu.blogic.jdbc.JDBCIpdDAO;
import com.apm.Ipd.eu.entity.Bed;
import com.apm.Ipd.eu.entity.Ipd;
import com.apm.Ipd.web.form.IpdForm;
import com.apm.Log.eu.bi.LogDAO;
import com.apm.Log.eu.blogic.jdbc.JDBCLogDAO;
import com.apm.Log.eu.entity.EmailLetterLog;
import com.apm.Master.eu.bi.MasterDAO;
import com.apm.Master.eu.bi.PackageMasterDAO;
import com.apm.Master.eu.blogic.jdbc.JDBCMasterDAO;
import com.apm.Master.eu.blogic.jdbc.JDBCPackageMasterDAO;
import com.apm.Master.eu.entity.Master;
import com.apm.Master.eu.entity.PackageMaster;
import com.apm.Mis.eu.bi.MisChartDAO;
import com.apm.Mis.eu.blogic.jdbc.JDBCMisChartDAO;
import com.apm.Pharmacy.eu.bi.PharmacyDAO;
import com.apm.Pharmacy.eu.blogic.jdbc.JDBCPharmacyDAO;
import com.apm.Registration.eu.bi.ClinicDAO;
import com.apm.Registration.eu.bi.UserProfileDAO;
import com.apm.Registration.eu.blogic.jdbc.JDBCClinicDAO;
import com.apm.Registration.eu.blogic.jdbc.JDBCUserProfileDAO;
import com.apm.Registration.eu.entity.Clinic;
import com.apm.Registration.eu.entity.Location;
import com.apm.Registration.eu.entity.UserProfile;
import com.apm.ThirdParties.eu.bi.ThirdPartyDAO;
import com.apm.ThirdParties.eu.blogic.jdbc.JDBCThirdPartyDAO;
import com.apm.ThirdParties.eu.entity.ThirdParty;
import com.apm.Tools.eu.bi.SMSTemplateDAO;
import com.apm.Tools.eu.blogic.jdbc.JDBCSMSTemplateDao;
import com.apm.Tools.eu.entity.EmailTemplate;
import com.apm.TreatmentEpisode.eu.bi.TreatmentEpisodeDAO;
import com.apm.TreatmentEpisode.eu.blogic.jdbc.JDBCTreatmentEpisode;
import com.apm.TreatmentEpisode.eu.entity.TreatmentEpisode;
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

public class IpdDashboardAction extends BaseAction implements Preparable, ModelDriven<IpdForm>{

	HttpServletRequest request = (HttpServletRequest) ActionContext.getContext().get(ServletActionContext.HTTP_REQUEST);
	HttpServletResponse response = (HttpServletResponse)ActionContext.getContext().get(ServletActionContext.HTTP_RESPONSE);
	HttpSession session = request.getSession(true);
	LoginInfo loginInfo = LoginHelper.getLoginInfo(request);
	
	Pagination pagination=new Pagination(25,1);
	
	public Pagination getPagination() {
		return pagination;
	}


	public void setPagination(Pagination pagination) {
		this.pagination = pagination;
	}

	IpdForm ipdForm = new IpdForm();
	
	public String execute() throws Exception {
		if(!verifyLogin(request)){
			return "login";
		}
		Connection connection = null;
		int requestresult=0;
		try {
			
			String action = request.getParameter("action");
			if(action==null){
				action = "0";
			}
			

			connection = Connection_provider.getconnection();
			IpdDAO ipdDAO = new JDBCIpdDAO(connection);
			UserProfileDAO userProfileDAO = new JDBCUserProfileDAO(connection);
			
			//Save request Time of Module
			LogDAO logDAO=new JDBCLogDAO(connection);
			SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");  
		    Date requestdate = new Date();
		    String req_datetime=formatter.format(requestdate);
		    requestresult=logDAO.saveresponsetime("IPD",req_datetime);
			//End Save request Time of Module
			
			
			UserProfile userProfile = userProfileDAO.getUserprofileDetails(loginInfo.getId());
			if(loginInfo.getUserType()!=2){
				if(userProfile.getWardid()==null){
					return "nipdaccess";
				}
			}
			
			
			BedDao bedDao = new JDBCBedDao(connection);
			
			session.setAttribute("ipdlocation", action);
			if(session.getAttribute("openedb")==null){
				session.setAttribute("openedb", "ipd");
			}
			boolean isreport = loginInfo.isReport();
			
			String filter_status= ipdForm.getFilter_status();
			/*saveprescrminder();
			savenursingrminder();*/
			
			String activefilter = ipdForm.getActivefilter();
			
			if(activefilter!=null){
				if(activefilter.equals("")){
					activefilter="1";
				}
			}else{
				activefilter="1";
			}
			String isfromandroid = request.getParameter("isfromandroid");
			String androidpractuserid = request.getParameter("androidpractuserid");
			String androidpractid="0";
			if(isfromandroid!=null){
				if(isfromandroid.equals("")){
					isfromandroid="0";
				}
			}else{
				isfromandroid = ipdForm.getAndroidpractid();
				if(isfromandroid!=null){
					if(isfromandroid.equals("")){
						isfromandroid="0";
					}
				}else{
					isfromandroid="0";
				}
			}
			
			if(isfromandroid.equals("1")){
				if(androidpractuserid!=null){
					if(androidpractuserid.equals("")){
						androidpractuserid = ipdForm.getAndroidpractuserid();
						if(androidpractuserid!=null){
							if(androidpractuserid.equals("")){
								androidpractuserid="0";
							}
						}else{
							androidpractuserid="0";
						}
					}
				}else{
					androidpractuserid = ipdForm.getAndroidpractuserid();
					if(androidpractuserid!=null){
						if(androidpractuserid.equals("")){
							androidpractuserid="0";
						}
					}else{
						androidpractuserid="0";
					}
				}
				int practid = userProfileDAO.getIdFromUserId(androidpractuserid);
				androidpractid = ""+practid;
			}
			
			
			if(loginInfo.isToDoctorIpd()){
				isfromandroid="1";
				androidpractid=""+loginInfo.getId();
			}
			ipdForm.setAndroidpractid(androidpractid);
			ipdForm.setIsfromandroid(isfromandroid);
			ipdForm.setAndroidpractuserid(androidpractuserid);
			String wardid = ipdForm.getWardid();
			wardid = DateTimeUtils.numberCheck(wardid);
			/*if(wardid!=null){
				if(wardid.equals("")){
					wardid ="0";
				}
			}else{
				wardid ="0";
			}*/
			if(wardid.equals("0")){
				wardid = DateTimeUtils.numberCheck(userProfile.getWardid());
			}else if(wardid.equals("0,")){
				wardid = DateTimeUtils.numberCheck(userProfile.getWardid());
				if(wardid.equals("0,")){
					wardid ="0";
				}
			}
			//ArrayList<Bed> wardlist = bedDao.getAllWardList(action,wardid,loginInfo.getUserType());
			//ipdForm.setWardlist(wardlist);
			
			ArrayList<Master> wardList = bedDao.getAllWardListNew(action,wardid,loginInfo.getUserType());
			ipdForm.setWardList(wardList);
			String excessamtbt = ipdForm.getExcessamtbt();
			if(excessamtbt!=null){
				if(excessamtbt.equals("")){
					excessamtbt="0";
				}
			}else{
				excessamtbt="0";
			}
			String newwardid=(String) session.getAttribute("dirctward");
			
			String directwardid= request.getParameter("directwardid");
			String bedsts=request.getParameter("bedstaus");
			if(directwardid==null){
				directwardid="";
			}
			if(directwardid.equals("")){
				if(newwardid!=null){
					if(!newwardid.equals("")){
						directwardid=newwardid;
					}
				}
			}
			session.setAttribute("dirctward", directwardid);
			if(directwardid.equals("0")){
				directwardid="";
			}
			if(!directwardid.equals("")){
				wardid="0,"+directwardid;
			}
			
			ipdForm.setWardid(directwardid);
			ipdForm.setExcessamtbt(excessamtbt);
			if(bedsts==null){
				bedsts="1";
			}
			if(bedsts.equals("")){
				bedsts="1";
			}
			activefilter=bedsts;
			
			/*//  08-06-2020
			int totalbookedbedcount = ipdDAO.getTotalBookedBedCount(wardid,loginInfo.getClinicid(),loginInfo, filter_status,action,activefilter,isfromandroid,androidpractid,excessamtbt); 
			
			String ipddash_firsttime = (String) session.getAttribute("ipddash_firsttime");
			boolean flaggetlistfromsession = false;
			if(DateTimeUtils.isNull(ipddash_firsttime).equals("1")){
				//if data is present in session
				flaggetlistfromsession =true;
				
				int totalsessionadmited = (Integer) session.getAttribute("ipddash_totalbookedbed");
				if(totalbookedbedcount==totalsessionadmited){
					flaggetlistfromsession = true;
				}else{
					flaggetlistfromsession = false;
				}
			}
			if(flaggetlistfromsession){
				if(excessamtbt.equals("1")){
					flaggetlistfromsession =false;
				}
			}
			String ipddash_forreload = (String) session.getAttribute("ipddash_forreload");
			if(DateTimeUtils.isNull(ipddash_forreload).equals("1")){
				flaggetlistfromsession =false;
				session.removeAttribute("ipddash_forreload");
			}*/
			
			ArrayList<Bed> bedlist = new ArrayList<Bed>();
			/*if(!flaggetlistfromsession){
				bedlist = ipdDAO.getAllBedList(wardid,loginInfo.getClinicid(),loginInfo, filter_status,action,activefilter,isfromandroid,androidpractid,excessamtbt);
				if(excessamtbt.equals("0")){
					//session remove 
					session.removeAttribute("ipddash_sessionbedlist");
					//session add
					session.setAttribute("ipddash_sessionbedlist",bedlist);
				}
			}else{
				bedlist = (ArrayList<Bed>) session.getAttribute("ipddash_sessionbedlist");
			}*/
			
			//24-08-2020 set view of admission form and discharge form
			/*boolean checkadmissionview = ipdDAO.checkAdmissionView(); 
			if(!checkadmissionview){
				ipdDAO.createAdmissionView();
			}
			
			boolean checkdischargeview = ipdDAO.checkDischargeView();
			if(!checkdischargeview){
				int createdischargeview = ipdDAO.createDischargeView();
			}*/
			
			bedlist = ipdDAO.getAllBedList(wardid,loginInfo.getClinicid(),loginInfo, filter_status,action,activefilter,isfromandroid,androidpractid,excessamtbt);
			ipdForm.setBedlist(bedlist);
			ipdForm.setMnsWardList(wardList);
			/*ArrayList<Master> mnsWardList= new ArrayList<Master>();
			for(Master ward:wardList){
				ward.setBedList(ipdDAO.getAllBedList(""+ward.getId(),loginInfo.getClinicid(),loginInfo, filter_status,action,activefilter,isfromandroid,androidpractid,excessamtbt));
				if(ward.getBedList().size()>0){
					ward.setCount(ward.getBedList().size());
					ward.setBookedBedCount(ward.getBedList().get(ward.getBedList().size()-1).getTotalbookedbed());
				}
				mnsWardList.add(ward);
			}
			ipdForm.setMnsWardList(mnsWardList);*/
			
			
			//@  for count intial discharge count, total bed, total active bed 
			int size= bedlist.size();
			int totalbed=0;
			int totalbookedbed=0;
	        if(size>0){
	        	 totalbookedbed = bedlist.get(size-1).getTotalbookedbed();
	        	 int totolintitaldischarge = bedlist.get(size-1).getTotolintitaldischarge();
	        	 totalbed = bedlist.get(size-1).getTotalbed();
	        	 ipdForm.setTotalbookedbed(totalbookedbed);
	        	 ipdForm.setTotolintitaldischarge(totolintitaldischarge);
	        	 ipdForm.setTotalbed(totalbed);
	        	 
	        	 int totalvacantbed = totalbed- totalbookedbed;
	        	 ipdForm.setTotalvacantbed(totalvacantbed);
	        	 
	      	 } else {
	      		 ipdForm.setTotalbookedbed(0);
	        	 ipdForm.setTotolintitaldischarge(0);
	        	 ipdForm.setTotalbed(0);
	        	 ipdForm.setTotalvacantbed(0);
	      	 }
			
	        session.setAttribute("ipddash_firsttime", "1");
			session.setAttribute("ipddash_ward"+session.getId(), wardid);
			session.setAttribute("ipddash_filter_status"+session.getId(), filter_status);
			session.setAttribute("ipddash_action"+session.getId(), action);
			session.setAttribute("ipddash_activefilter"+session.getId(), activefilter);
			session.setAttribute("ipddash_isfromandroid"+session.getId(), isfromandroid);
			session.setAttribute("ipddash_androidpractid"+session.getId(), androidpractid);
			session.setAttribute("ipddash_totalbookedbed", totalbookedbed);
	        
	        //int totalbed = ipdDAO.getAllTotalBed(ipdForm.getWardid(),loginInfo.getClinicid(),loginInfo);
			//ipdForm.setTotalbed(totalbed);
			String date= DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone()).split(" ")[0];
			ipdForm.setStddate(DateTimeUtils.getCommencingDate1(date));
			ipdForm.setEnddate(DateTimeUtils.getCommencingDate1(date));
			ipdForm.setDate(DateTimeUtils.getCommencingDate1(date));
			ipdForm.setFilter_status(filter_status);
			ipdForm.setCasualtyipd(action);
			
			
			//  10 jan 2018
			MisChartDAO misChartDAO = new JDBCMisChartDAO(connection);
			int dischargepatients = misChartDAO.getDischargePatientsWardWise(date, date, wardid);
			ipdForm.setTotaldischarge(String.valueOf(dischargepatients));
		
			
			//master
			MasterDAO masterDAO = new JDBCMasterDAO(connection);
			AppointmentDAO appointmentDAO = new JDBCAppointmentDAO(connection);
			InventoryVendorDAO vendorDAO=new JDBCInventoryVendorDAO(connection);
			NotAvailableSlotDAO notAvailableSlotDAO=new JDBCNotAvailableSlotDAO(connection);
			/*ArrayList<AppointmentType>additionalChargeList = appointmentDAO.getAdditionalChaergeTypeList("");
			ipdForm.setAdditionalChargeList(additionalChargeList);*/
			
			ArrayList<AppointmentType>additionalChargeList =new ArrayList<>();
			ipdForm.setAdditionalChargeList(additionalChargeList);
			
			ArrayList<Master>masterChageTypeList = appointmentDAO.getmasterChageTypeList(loginInfo);
			ipdForm.setMasterChageTypeList(masterChageTypeList);
			ipdForm.setMasterchargetype("Additional Charge");
			
			ArrayList<Master>medicineList = masterDAO.getMedicineList();
			//ArrayList<Master>medicineList = new ArrayList<Master>();
			ipdForm.setMedicineList(medicineList);
			
			ArrayList<Master> cityList= vendorDAO.getAllCityList();
			ipdForm.setCitylist(cityList);
			
			ArrayList<Master> procedureList = notAvailableSlotDAO.getProcedureList(""+4);
			ipdForm.setProcedureList(procedureList);
			
			
			
			DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
			Calendar cal = Calendar.getInstance();
			//cal.add(Calendar.DATE, -1);
			String todate = dateFormat.format(cal.getTime());
			todate = DateTimeUtils.getCommencingDate1(todate);
			
			 ClinicDAO clinicDAO = new JDBCClinicDAO(connection);
		     AccountsDAO accountsDAO = new JDBCAccountsDAO(connection);
		     ArrayList<Clinic> locationAdressList = accountsDAO.getLocationAddress(loginInfo.getClinicid());
		     Clinic clinic = clinicDAO.getCliniclistDetails(loginInfo.getClinicid());
		     ipdForm.setClinicName(clinic.getClinicName());
		     ipdForm.setClinicOwner(clinic.getClinicOwner());
		     ipdForm.setOwner_qualification(clinic.getOwner_qualification());
		     ipdForm.setLocationAdressList(locationAdressList);
		     ipdForm.setAddress(clinic.getAddress());
		     ipdForm.setLandLine(clinic.getLandLine());
		     ipdForm.setClinicemail(clinic.getEmail());
		     ipdForm.setWebsiteUrl(clinic.getWebsiteUrl());
		     ipdForm.setClinicLogo(clinic.getUserImageFileName());
			
			//now called from advanceEmailSendAction.java 
			/*Calendar cal1 = Calendar.getInstance();
			cal1.add(Calendar.DATE, -1);
			String newdate = dateFormat.format(cal1.getTime());
			newdate = DateTimeUtils.getCommencingDate1(newdate);
			Boolean flag = ipdDAO.checkInhousePatientExists(newdate);
			if(!flag){
				int ipdOldPatient = misChartDAO.getInHousePatients(todate, todate);
				int ipdnewadmission = misChartDAO.getIpdNewAdmission(todate, todate);
				int dischargepatient = misChartDAO.getDischargePatients(newdate, newdate);
				int total = dischargepatient+ipdOldPatient-ipdnewadmission;
				int totalbed1 = misChartDAO.getAvailableBed(date,date);
				
				ArrayList<String> ipdOldPatientlist =  misChartDAO.getInHousePatientList(todate, todate);
				ArrayList<String> ipdnewadmissionlist = misChartDAO.getIpdNewAdmissionList(todate, todate);
				String ipdids =misChartDAO.getDischargePatientList(newdate, newdate);
				
				for (String string : ipdOldPatientlist) {
					boolean flag1 = false;
					for (String string1 : ipdnewadmissionlist) {
						if(string.equals(string1)){
							flag1=true;
							break;
						}
					}
					if(!flag1){
						if(ipdids!=null){
							if(ipdids.equals("")){
								ipdids = string;
							}else{
								ipdids = ipdids+","+string;
							}
						}else{
							ipdids = string;
						}
					}
				}
				int res = ipdDAO.saveInhousePatient(total,newdate,totalbed1,ipdids);
			}*/
			
			/*Boolean flag1 = ipdDAO.checkPatientAdmorDis(date);
			int ipdnewadmission = misChartDAO.getIpdNewAdmission(date, date);
			Ipd ippd = ipdDAO.getTotalAdmDisCount();
			int totaladmision = ipdnewadmission+ippd.getTotaladmission();
			int totaldischarge  = dischargepatients + ippd.getTotaldischarge();
			if(flag1){
				//update
				int res = ipdDAO.updatePatientAdmandDis(date,ipdnewadmission,totaladmision,dischargepatients,totaldischarge);
			}else{
				//insert
				int res = ipdDAO.savePatientAdmandDis(date,ipdnewadmission,totaladmision,dischargepatients,totaldischarge);
			}
			*/
			ipdForm.setActivefilter(activefilter);
			//navigation 
//			ArrayList<Breadcrumbs> indentflowlist = new ArrayList<Breadcrumbs>();
//			ArrayList<Breadcrumbs> indentflowlisttemp = new ArrayList<Breadcrumbs>();
//			if (session.getAttribute("indentflowlist") != null) {
//				indentflowlisttemp = (ArrayList<Breadcrumbs>) session.getAttribute("indentflowlist");
//			}
//			boolean isavilablemodule= false;
//			int modulecount =0;
//			for (Breadcrumbs breadcrumbs : indentflowlisttemp) {
//				breadcrumbs.setIscurrent(false);
//				breadcrumbs.setSqno(modulecount);
//				modulecount++;
//				if(breadcrumbs.getName().equals("IPD Dashboard")){
//					isavilablemodule =true;
//					breadcrumbs.setIscurrent(true);
//					indentflowlist.add(breadcrumbs);
//					break;
//				}else{
//					indentflowlist.add(breadcrumbs);
//				}
//			}
//			if(!isavilablemodule){
//				Breadcrumbs breadcrumbs = new Breadcrumbs();
//				breadcrumbs.setName("IPD Dashboard");
//				breadcrumbs.setOn(true);
//				breadcrumbs.setSqno(modulecount);
//				breadcrumbs.setUrllink("IpdDashboard?action=0");
//				breadcrumbs.setIscurrent(true);
//				indentflowlist.add(breadcrumbs);
//			}
//			session.setAttribute("indentflowlist",indentflowlist);
			String freez_status = ipdForm.getFreez_status();
			if(DateTimeUtils.isNull(freez_status).equals("")){
				if(loginInfo.isFreeze_unfreeze()){
					freez_status ="1";
				}else{
					freez_status ="0";
				}
			}else{
				if(freez_status.equals("0")){
					freez_status ="1";
				}else{
					freez_status ="0";
				}
			}
			
			ipdForm.setFreez_status(freez_status);
			
			//Update response Time of Module
			String	requestid=""+requestresult;
			SimpleDateFormat formatter1 = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");  
		    Date responsedate = new Date();
		    String response_datetime=formatter1.format(responsedate);
		    String duration_of_time=DateTimeUtils.findTimeBTDateTime(req_datetime, response_datetime);
		    int upres=logDAO.updateresponsetime("IPD",response_datetime,requestid,duration_of_time);
			//End Update response Time of Module
			
		    String fromDate=ipdForm.getFromDate();
	    	
	    	if (fromDate == null) {
				 dateFormat = new SimpleDateFormat("dd-MM-yyyy");
				 cal = Calendar.getInstance();
				// cal.add(Calendar.DATE, -7);
				fromDate = dateFormat.format(cal.getTime());
				fromDate = DateTimeUtils.getCommencingDate1(fromDate);
			} else {
				if (fromDate.equals("")) {
					 dateFormat = new SimpleDateFormat("dd-MM-yyyy");
					 cal = Calendar.getInstance();
					// cal.add(Calendar.DATE, -7);
					fromDate = dateFormat.format(cal.getTime());
					fromDate = DateTimeUtils.getCommencingDate1(fromDate);
				} else {
					fromDate = DateTimeUtils.getCommencingDate1(fromDate);
				}
			}
	    	ipdForm.setFromDate(DateTimeUtils.getCommencingDate1(fromDate));
			if(true){
				return "mnsbookbed";
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			connection.close();
		}
		return super.execute();
	}
	
	
	public String pkglist() throws Exception{
		//url = "pkglistIpdDashboard?admissionid="+admissionid+"&ipdclientid="+ipdclientid+" ";
		String admissionid = request.getParameter("admissionid");
		String ipdclientid = request.getParameter("ipdclientid");
		
		Connection connection=null;
		   try {
				 
			   connection=Connection_provider.getconnection();
			   IpdDAO ipdDAO = new JDBCIpdDAO(connection);
			   
			   ArrayList<Master>patientPackageList = ipdDAO.getPatientPackageList(admissionid,ipdclientid);
			   
			   StringBuffer str  = new StringBuffer();
			   
			   str.append("<select onchange='addpackageoncreatecharge(this.value)' class='form-control' name='ipdpackage' id='ipdpackage'>");
			   str.append("<option value='0' >Select Package</option>");
			   for(Master m : patientPackageList){
				   str.append("<option value='"+m.getId()+"' >"+m.getName()+"</option>");
			   }
			   str.append("");
			   str.append("</select>");
			   
			   response.setContentType("text/html");
				response.setHeader("Cache-Control", "no-cache");
				response.getWriter().write(""+str.toString()+""); 
			   
		   }catch (Exception e) {
			// TODO: handle exception
		}finally{
			
			connection.close();
		}
		
		
		
		return null;
	}
	
	
	public String pkglistedit() throws Exception{
		//url = "pkglistIpdDashboard?admissionid="+admissionid+"&ipdclientid="+ipdclientid+" ";
		String admissionid = request.getParameter("admissionid");
		String ipdclientid = request.getParameter("ipdclientid");
		
		Connection connection=null;
		   try {
				 
			   connection=Connection_provider.getconnection();
			   IpdDAO ipdDAO = new JDBCIpdDAO(connection);
			   
			   ArrayList<Master>patientPackageList = ipdDAO.getPatientPackageList(admissionid,ipdclientid);
			   
			   StringBuffer str  = new StringBuffer();
			   
			   str.append("<select onchange='getapplpkglist(this.value)' class='form-control' name='appliedpkgname' id='appliedpkgname'>");
			   str.append("<option value='0' >Select Package</option>");
			   for(Master m : patientPackageList){
				   str.append("<option value='"+m.getMasterid()+"' >"+m.getName()+"</option>");
			   }
			   str.append("");
			   str.append("</select>");
			   
			   response.setContentType("text/html");
				response.setHeader("Cache-Control", "no-cache");
				response.getWriter().write(""+str.toString()+""); 
			   
		   }catch (Exception e) {
			// TODO: handle exception
		}finally{
			
			connection.close();
		}
		
		
		
		return null;
	}
	
	public String pkg() throws Exception{
		String id = request.getParameter("id");
		  Connection connection=null;
		   try { 
			   connection=Connection_provider.getconnection();
			   IpdDAO ipdDAO = new JDBCIpdDAO(connection);
			   
			   Master master = ipdDAO.getParentPackageData(id);
			   
			   ArrayList<Accounts>packagedtailList = ipdDAO.getPackageDetailList(id);
			   
			   StringBuffer str = new StringBuffer();
			   double chtoptal = 0;
			   str.append("<input type='hidden' name='hdnpkgamount' id='hdnpkgamount' value='"+Constants.getCurrency(loginInfo) + master.getCharge()+"'>");
			   str.append("<input type='hidden' name='gpgamt' id='gpgamt' value='"+master.getCharge()+"'>");
			   str.append("<input type='hidden' name='hdnpkgsize' id='hdnpkgsize' value='"+packagedtailList.size()+"'>");
			   int ct = 1;
			   for(Accounts accounts : packagedtailList){
				   str.append("<tr>");
				   str.append("<input type='hidden' class='akash' value='"+ ct +"' />");
				   str.append("<td id='atype"+ct+"' name='atype"+accounts.getId()+"' style='font-size: 14px;'>"+accounts.getAppointmentType()+"</td>");
				   if(master.isThird_party()){
					   str.append("<td style='font-size: 14px;'>"+accounts.getTpcode()+"<input type='hidden' name='tpcode"+ct+"'></td>");
				   }else{
					   str.append("<td style='font-size: 14px;'><input onchange='showPertotal("+ct+",this.value)'  id='pkgper"+ct+"' name='pkgper"+accounts.getId()+"'  type='text' class='form-control' value='"+accounts.getPercentage()+"' style='width: 35%;'>%</td>");
				   }
				 
				  /* str.append("<td  style='text-align: right;font-size: 14px;'><input  id='pkgamt"+ct+"' name='pkgamt"+accounts.getId()+"' style='text-align: right;' type='text' class='form-control' value='"+Constants.getCurrency(loginInfo)+""+accounts.getChargeTotal()+"'></td>");*/
				   str.append("<td  style='text-align: right;font-size: 14px;'><input  id='pkgamt"+ct+"'  name='pkgamt"+accounts.getId()+"' style='text-align: right;' type='text' class='form-control' value='"+accounts.getChargeTotal()+"'></td>");
				   ct++;
				   str.append("</tr>");
				   chtoptal = chtoptal + Double.parseDouble(accounts.getChargeTotal());
			   }
			   
			   str.append("<tr style='border-top: none !important;background-color: cornsilk;'>");
			   str.append("<td style='font-size: 14px;'><b>Total</b></td>");
			   if(master.isThird_party()){
				   str.append("<td style='font-size: 14px;'><input type='text' class='form-control' value='Code' style='width: 35%;'></td>");
					  
			   }else{
				   str.append("<td style='font-size: 14px;'><input type='text' class='form-control' value='100%' style='width: 35%;'></td>");
					  
			   }
			   str.append("<td style='text-align: right;font-size: 14px;'><b><input style='text-align: right;' type='text' id='chtotal' class='form-control' value='"+Constants.getCurrency(loginInfo)+""+DateTimeUtils.changeFormat(chtoptal)+"'></b></td>");
			   str.append("</tr>");
			   
			   str.append("#");
			   
			   int alltotal =(int) Double.parseDouble(master.getCharge());
			   str.append(""+alltotal+"");
			   str.append("#");
			   if(master.isThird_party()){
				   str.append("1");
			   }else{
				   str.append("0");
			   }
			   DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
				Calendar cal = Calendar.getInstance();
				 cal.add(Calendar.DATE, 0);
				String fromDate = dateFormat.format(cal.getTime());
				DateFormat dateFormat1 = new SimpleDateFormat("dd-MM-yyyy");
				Calendar cal1 = Calendar.getInstance();
				 cal1.add(Calendar.DATE, +master.getDays());
				String todate=dateFormat1.format(cal1.getTime());
			   str.append("#");
			   str.append(""+fromDate+"");
			   str.append("#");
			   str.append(""+todate+"");
			   str.append("#");
			   str.append(""+master.getDays()+"");
			   response.setContentType("text/html");
				response.setHeader("Cache-Control", "no-cache");
				response.getWriter().write(""+str.toString()+""); 
			
			
		}catch (Exception e) {
			// TODO: handle exception
		}finally{
			
			connection.close();
		}
		
		
		return null;
	}
	public String pkgdtails() throws Exception{
		String id = request.getParameter("id");
		  Connection connection=null;
		   try {
				 
			   connection=Connection_provider.getconnection();
			   IpdDAO ipdDAO = new JDBCIpdDAO(connection);
			   
			   Master master = ipdDAO.getParentPackageData(id);
			   
			   StringBuffer str = new StringBuffer();
			   str.append(master.getCharge());
			   str.append("~");
			   str.append(master.getId());
			   str.append("~");
			   str.append(master.getName());
			   response.setContentType("text/html");
				response.setHeader("Cache-Control", "no-cache");
				response.getWriter().write(""+str.toString()+""); 
			   
		   }catch (Exception e) {
			// TODO: handle exception
		}finally{
			
			connection.close();
		}
		
		return null;
	}
	
	public String getajaxnursingdata() throws Exception{
		
		  Connection connection=null;
		   
		  
		try {
			 connection=Connection_provider.getconnection();
			 DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
				Calendar cal = Calendar.getInstance();
				//cal.add(Calendar.DATE, -7); 
				String currDate = dateFormat.format(cal.getTime());
				

				connection = Connection_provider.getconnection();
				IpdDAO ipdDAO = new JDBCIpdDAO(connection); 
				String ipdid=request.getParameter("ipdid");
				
				//for priscription
				
				ArrayList<Priscription>parentPriscList = ipdDAO.getParentPriscList(ipdid);
				
				for(Priscription priscription : parentPriscList){
					String temp[] = priscription.getDate().split(" ");
					String mdicinestartdate = temp[0];
					
					long mdicinedays = DateTimeUtils.getDiffofTwoDates(mdicinestartdate, currDate);
					mdicinedays++;
					
					
					ArrayList<Priscription>clientPriscList = ipdDAO.getClientPriscList(priscription.getParentid());
					for(Priscription pr : clientPriscList){
						boolean checkpriscexist = ipdDAO.checkPrescExist(mdicinedays,pr.getId());
						String dosecolumn = "";
						String doseqmark = "";

						String dosage=pr.getDosage();
						if(!checkpriscexist){
							if(dosage!=null){
								String dosetemp[] = dosage.split("-");
								if(dosetemp.length<3){
									 dosage= ipdDAO.getAlterNateDose(dosage);
									 dosetemp =dosage.split("-");
								}
								int c = 0;
								for(int i=1;i<=dosetemp.length;i++){
									
									doseqmark = doseqmark + 0 +  ",";
									dosecolumn = dosecolumn +  "dos" + i + ",";
									c++;
								}
								dosecolumn = dosecolumn.substring(0,dosecolumn.length()-1);
								doseqmark = doseqmark.substring(0,doseqmark.length()-1);
								
								if(mdicinedays<=pr.getDays()){
									int result = ipdDAO.savePrescReminder(dosecolumn,doseqmark,mdicinedays,pr.getId(),ipdid);
								}
								
							}
							
						}
					}
				}
                
				
				//for Nursing
				
                ArrayList<Master> nursingParentList = ipdDAO.getParentNursingList(ipdid);
				
				for(Master master : nursingParentList){
					String temp[] = master.getDate().split(" ");
					String mdicinestartdate = temp[0];
					
					long mdicinedays = DateTimeUtils.getDiffofTwoDates(mdicinestartdate, currDate);
					mdicinedays++;
					
					
					ArrayList<Master> clientNursingList = ipdDAO.getClientNursingList(master.getParentid());
					for(Master pr : clientNursingList){
						boolean checkpriscexist = ipdDAO.checkNursingExist(mdicinedays,pr.getId());
						String dosecolumn = "";
						String doseqmark = "";
						
						if(!checkpriscexist){
							if(pr.getFrequency()!=null){
								String dosetemp[] = pr.getFrequency().split("-");
								int c = 0;
								for(int i=1;i<=dosetemp.length;i++){
									
									doseqmark = doseqmark + 0 +  ",";
									dosecolumn = dosecolumn +  "dos" + i + ",";
									c++;
								}
								dosecolumn = dosecolumn.substring(0,dosecolumn.length()-1);
								doseqmark = doseqmark.substring(0,doseqmark.length()-1);
								
								if(mdicinedays<=pr.getDays()){
									int result = ipdDAO.saveNursingReminder(dosecolumn,doseqmark,mdicinedays,pr.getId(),ipdid);
								}
								
							}
						}
					}
			    
		     }
				
		} 
		catch (Exception e) {

		    e.printStackTrace();
		}
	 	finally {
	 		connection.close();
	 	}
		return null;
	}  
	
	
	
	
public String getcommonbellajax() throws Exception{
		
		Connection connection=null;
		
		try {
			
			saveprescrminder();
			savenursingrminder();
			connection=Connection_provider.getconnection();
			IpdDAO ipdDAO=new JDBCIpdDAO(connection);
			ArrayList<Bed> ipdpricsdoselist=ipdDAO.getAllPricsandDoseTodayOfPatient(ipdForm.getWardid(),loginInfo);
			ipdForm.setIpdpricsdoselist(ipdpricsdoselist);
			   
			ArrayList<Bed> nursingdoseList=ipdDAO.getAllNursingDoseTodayofPatient(ipdForm.getWardid(),loginInfo);
			ipdForm.setNursingdoseList(nursingdoseList);
			
			
			StringBuffer buffer=new StringBuffer();
			for(Bed bed:ipdpricsdoselist){
				 
				buffer.append("<tr>");
				 buffer.append("<td><span id='c"+bed.getId()+"'><i class='fa fa-bell'></i></span> / "+bed.getWardname()+" <br> "+bed.getClientname()+" <br>"+bed.getAge()+"</td>");
				 buffer.append("<td>");  
				 buffer.append("<table class='table table-bordered' width=100%>");
				 buffer.append("<thead>");
				 buffer.append("<tr>");
				 buffer.append("<th class='mednawidth dosatable'>Task Name</th>");
				 buffer.append("<th class='text-center dosatable'>08:00</th>");
				 buffer.append("<th class='text-center dosatable'>09:00</th>");
				 buffer.append("<th class='text-center dosatable'>10:00</th>");
				 buffer.append("<th class='text-center dosatable'>12:00</th>");
				 buffer.append("<th class='text-center dosatable'>14:00</th>");
				 buffer.append("<th class='text-center dosatable'>16:00</th>");
				 buffer.append("<th class='text-center dosatable'>18:00</th>");
				 buffer.append("<th class='text-center dosatable'>20:00</th>");
				 buffer.append("<th class='text-center dosatable'>21:00</th>");
				 buffer.append("<th class='text-center dosatable'>22:00</th>");
				 buffer.append("<th class='bednote dosatable'> Notes</th>");				 
				 buffer.append("</tr>");
				 buffer.append("</thead>");
				 
				 buffer.append("<tbody>");
				   
				   if(bed.getIpdPriscList().size()!=0){
				       int i=0;
					   for(Priscription priscription:bed.getIpdPriscList()){
						   buffer.append("<tr>");
						   buffer.append("<td>"+priscription.getMdicinenametxt()+"</td>");
						   if(priscription.getDosesize()==3){
						   
							   buffer.append("<td></td>");
							   if(!priscription.getDosevalue1().equals("0")){
								   if(priscription.isDos1()){
									   
									   buffer.append("<td id='d"+(++i)+""+priscription.getIpdid()+"'><center><input type='checkbox'  name='dos1' id='dos1' checked='checked' onclick=toggleConfirmation("+priscription.getId()+",'dos1',"+priscription.isDos1()+") /></center></td>");
								   } else {
									   buffer.append("<td id='d"+(++i)+""+priscription.getIpdid()+"'><center><input type='checkbox'  name='dos1' id='dos1' onclick=toggleConfirmation("+priscription.getId()+",'dos1',"+priscription.isDos1()+") /></center></td>");
								   }
								   
							   }else {
								   buffer.append("<td></td>");
							   }
							   buffer.append("<td></td><td></td><td></td>");
							   if(!priscription.getDosevalue2().equals("0")){
								   
								      if(priscription.isDos2()){
								    	  buffer.append("<td id='d"+(++i)+""+priscription.getIpdid()+"'><center><input type='checkbox'  name='dos2' id='dos2' checked='checked' onclick=toggleConfirmation("+priscription.getId()+",'dos2',"+priscription.isDos2()+") /></center></td>");
								      } else {
								    	  buffer.append("<td id='d"+(++i)+""+priscription.getIpdid()+"'><center><input type='checkbox'  name='dos2' id='dos2' onclick=toggleConfirmation("+priscription.getId()+",'dos2',"+priscription.isDos2()+") /></center></td>");
								      }
									   
						       }else {
									   buffer.append("<td></td>");
						       }
							   buffer.append("<td></td><td></td>");
							   if(!priscription.getDosevalue3().equals("0")){
									  
								   if(priscription.isDos3()){
									   
									   buffer.append("<td id='d"+(++i)+""+priscription.getIpdid()+"'><center><input type='checkbox'  name='dos3' id='dos3' checked='checked'  onclick=toggleConfirmation("+priscription.getId()+",'dos3',"+priscription.isDos3()+") /></center></td>");
								   } else {
									   buffer.append("<td id='d"+(++i)+""+priscription.getIpdid()+"'><center><input type='checkbox'  name='dos3' id='dos3' onclick=toggleConfirmation("+priscription.getId()+",'dos3',"+priscription.isDos3()+") /></center></td>");
								   }
								   
						       }else {
									   buffer.append("<td></td>");
						       }
							   buffer.append("<td></td>");   
							   buffer.append("<td>"+priscription.getDosenotes()+"</td>");
						   }
						   if(priscription.getDosesize()==4){
							       if(!priscription.getDosevalue1().equals("0")){
									  
							    	   if(priscription.isDos1()){
							    		   buffer.append("<td id='d"+(++i)+""+priscription.getIpdid()+"'><center><input type='checkbox'  name='dos1' id='dos1' checked='checked' onclick=toggleConfirmation("+priscription.getId()+",'dos1',"+priscription.isDos1()+") /></center></td>");
							    	   } else {
							    		   buffer.append("<td id='d"+(++i)+""+priscription.getIpdid()+"'><center><input type='checkbox'  name='dos1' id='dos1' onclick=toggleConfirmation("+priscription.getId()+",'dos1',"+priscription.isDos1()+") /></center></td>");
							    	   }
							    	   
								   }else {
									   buffer.append("<td></td>");
								   }
							       buffer.append("<td></td><td></td><td></td>");
							       if(!priscription.getDosevalue2().equals("0")){
							    	   
							    	   if(priscription.isDos2()){
							    		   buffer.append("<td id='d"+(++i)+""+priscription.getIpdid()+"'><center><input type='checkbox'  name='dos2' id='dos2' checked='checked' onclick=toggleConfirmation("+priscription.getId()+",'dos2',"+priscription.isDos2()+") /></center></td>");
							    	   } else {
							    		   buffer.append("<td id='d"+(++i)+""+priscription.getIpdid()+"'><center><input type='checkbox'  name='dos2' id='dos2' onclick=toggleConfirmation("+priscription.getId()+",'dos2',"+priscription.isDos2()+") /></center></td>");
							    	   }
							    	   
							       }else {
										   buffer.append("<td></td>");
							       }
								   buffer.append("<td></td>");
								   if(!priscription.getDosevalue3().equals("0")){
										 if(priscription.isDos3()){
											 buffer.append("<td id='d"+(++i)+""+priscription.getIpdid()+"'><center><input type='checkbox'  name='dos3' id='dos3' checked='checked' onclick=toggleConfirmation("+priscription.getId()+",'dos3',"+priscription.isDos3()+") /></center></td>");
										 } else {
											 buffer.append("<td id='d"+(++i)+""+priscription.getIpdid()+"'><center><input type='checkbox'  name='dos3' id='dos3' onclick=toggleConfirmation("+priscription.getId()+",'dos3',"+priscription.isDos3()+") /></center></td>");
										 }
									    
							       }else {
										   buffer.append("<td></td>");
							       }
								   buffer.append("<td></td>");
								   if(!priscription.getDosevalue4().equals("0")){
									      
									      if(priscription.isDos4()){
									    	  buffer.append("<td id='d"+(++i)+""+priscription.getIpdid()+"'><center><input type='checkbox'  name='dos4' id='dos4' checked='checked' onclick=toggleConfirmation("+priscription.getId()+",'dos4',"+priscription.isDos4()+") /></center></td>");  
									      } else {
									    	  buffer.append("<td id='d"+(++i)+""+priscription.getIpdid()+"'><center><input type='checkbox'  name='dos4' id='dos4' onclick=toggleConfirmation("+priscription.getId()+",'dos4',"+priscription.isDos4()+") /></center></td>");
									      }
										   
							       }else {
										   buffer.append("<td></td>");
							       }
								   buffer.append("<td>"+priscription.getDosenotes()+"</td>");
						   }
						   
						   buffer.append("</tr>");
					   }
					   
				   }
				  
	     		  buffer.append("</tbody>");
				  buffer.append("</table>"); 
				  buffer.append("</td>");
				  
			}
			buffer.append("~");
			
			
			for(Bed bed:nursingdoseList){
				
				
				buffer.append("<tr>");
				 buffer.append("<td><span id='c"+bed.getId()+"'><i class='fa fa-bell'></i></span> / "+bed.getWardname()+" <br> "+bed.getClientname()+" <br>"+bed.getAge()+"</td>");
				 buffer.append("<td>");  
				 buffer.append("<table class='table table-bordered' width=100%>");
				 buffer.append("<thead>");
				 buffer.append("<tr>");
				 buffer.append("<th class='mednawidth dosatable'>Task Name</th>");
				 buffer.append("<th class='text-center dosatable'>08:00</th>");
				 buffer.append("<th class='text-center dosatable'>09:00</th>");
				 buffer.append("<th class='text-center dosatable'>10:00</th>");
				 buffer.append("<th class='text-center dosatable'>12:00</th>");
				 buffer.append("<th class='text-center dosatable'>14:00</th>");
				 buffer.append("<th class='text-center dosatable'>16:00</th>");
				 buffer.append("<th class='text-center dosatable'>18:00</th>");
				 buffer.append("<th class='text-center dosatable'>20:00</th>");
				 buffer.append("<th class='text-center dosatable'>21:00</th>");
				 buffer.append("<th class='text-center dosatable'>22:00</th>");
				 buffer.append("</tr>");
				 buffer.append("</thead>");
				 
				 buffer.append("<tbody>");
				   if(bed.getIpdNursingList()!=null){
				   if(bed.getIpdNursingList().size()!=0){
				       int i=0;
					   for(Master priscription:bed.getIpdNursingList()){
						   buffer.append("<tr>");
						   buffer.append("<td>"+priscription.getTaskname()+"</td>");
						   if(priscription.getDosesize()==3){
						   
							   buffer.append("<td></td>");
							   if(!priscription.getDosevalue1().equals("0")){
								  
								    if(priscription.isDos1()){
								    	buffer.append("<td id='d"+(++i)+""+priscription.getIpdid()+"'><center><input type='checkbox'  name='dos1' id='dos1' checked='checked' onclick=togglenursingConfirm("+priscription.getId()+",'dos1',"+priscription.isDos1()+") /></center></td>");
								    } else {
								    	buffer.append("<td id='d"+(++i)+""+priscription.getIpdid()+"'><center><input type='checkbox'  name='dos1' id='dos1' onclick=togglenursingConfirm("+priscription.getId()+",'dos1',"+priscription.isDos1()+") /></center></td>");
								    }
								   
							   }else {
								   buffer.append("<td></td>");
							   }
							   buffer.append("<td></td><td></td><td></td>");
							   if(!priscription.getDosevalue2().equals("0")){
								     if(priscription.isDos2()){
								    	 buffer.append("<td id='d"+(++i)+""+priscription.getIpdid()+"'><center><input type='checkbox'  name='dos2' id='dos2' checked='checked' onclick=togglenursingConfirm("+priscription.getId()+",'dos2',"+priscription.isDos2()+") /></center></td>");
								     } else {
								    	 buffer.append("<td id='d"+(++i)+""+priscription.getIpdid()+"'><center><input type='checkbox'  name='dos2' id='dos2' onclick=togglenursingConfirm("+priscription.getId()+",'dos2',"+priscription.isDos2()+") /></center></td>");
								     }
								   
									   
						       }else {
									   buffer.append("<td></td>");
						       }
							   buffer.append("<td></td><td></td>");
							   if(!priscription.getDosevalue3().equals("0")){
									  
								   if(priscription.isDos3()){
									   buffer.append("<td id='d"+(++i)+""+priscription.getIpdid()+"'><center><input type='checkbox'  name='dos3' id='dos3' checked='checked' onclick=togglenursingConfirm("+priscription.getId()+",'dos3',"+priscription.isDos3()+") /></center></td>");
								   } else {
									   buffer.append("<td id='d"+(++i)+""+priscription.getIpdid()+"'><center><input type='checkbox'  name='dos3' id='dos3' onclick=togglenursingConfirm("+priscription.getId()+",'dos3',"+priscription.isDos3()+") /></center></td>");
								   }
						       }else {
									   buffer.append("<td></td>");
						       }
							   buffer.append("<td></td>");   
						   }
						   if(priscription.getDosesize()==4){
							       if(!priscription.getDosevalue1().equals("0")){
									   if(priscription.isDos1()){
										   buffer.append("<td id='d"+(++i)+""+priscription.getIpdid()+"'><center><input type='checkbox'  name='dos1' id='dos1' checked='checked' onclick=togglenursingConfirm("+priscription.getId()+",'dos1',"+priscription.isDos1()+") /></center></td>");
									   } else {
										   buffer.append("<td id='d"+(++i)+""+priscription.getIpdid()+"'><center><input type='checkbox'  name='dos1' id='dos1' onclick=togglenursingConfirm("+priscription.getId()+",'dos1',"+priscription.isDos1()+") /></center></td>");
									   }
								   }else {
									   buffer.append("<td></td>");
								   }
							       buffer.append("<td></td><td></td><td></td>");
							       if(!priscription.getDosevalue2().equals("0")){
										  if(priscription.isDos2()){
											  buffer.append("<td id='d"+(++i)+""+priscription.getIpdid()+"'><center><input type='checkbox'  name='dos2' id='dos2' checked='checked' onclick=togglenursingConfirm("+priscription.getId()+",'dos2',"+priscription.isDos2()+") /></center></td>");
										  } else {
											  buffer.append("<td id='d"+(++i)+""+priscription.getIpdid()+"'><center><input type='checkbox'  name='dos2' id='dos2' onclick=togglenursingConfirm("+priscription.getId()+",'dos2',"+priscription.isDos2()+") /></center></td>");
										  }
							       }else {
										   buffer.append("<td></td>");
							       }
								   buffer.append("<td></td>");
								   if(!priscription.getDosevalue3().equals("0")){
										  if(priscription.isDos3()){
											  buffer.append("<td id='d"+(++i)+""+priscription.getIpdid()+"'><center><input type='checkbox'  name='dos3' id='dos3' checked='checked' onclick=togglenursingConfirm("+priscription.getId()+",'dos3',"+priscription.isDos3()+") /></center></td>");
										  } else {
											  buffer.append("<td id='d"+(++i)+""+priscription.getIpdid()+"'><center><input type='checkbox'  name='dos3' id='dos3' onclick=togglenursingConfirm("+priscription.getId()+",'dos3',"+priscription.isDos3()+") /></center></td>");
										  }
							       }else {
										   buffer.append("<td></td>");
							       }
								   buffer.append("<td></td>");
								   if(!priscription.getDosevalue4().equals("0")){
										  if(priscription.isDos4()){
											  buffer.append("<td id='d"+(++i)+""+priscription.getIpdid()+"'><center><input type='checkbox'  name='dos4' id='dos4' checked='checked' onclick=togglenursingConfirm("+priscription.getId()+",'dos4',"+priscription.isDos4()+") /></center></td>");
										  } else {
											  buffer.append("<td id='d"+(++i)+""+priscription.getIpdid()+"'><center><input type='checkbox'  name='dos4' id='dos4' onclick=togglenursingConfirm("+priscription.getId()+",'dos4',"+priscription.isDos4()+") /></center></td>");
										  }
							       }else {
										   buffer.append("<td></td>");
							       }
						   }
						   
						   buffer.append("</tr>");
					   }
					   
				   }
				    
				  
	     		  buffer.append("</tbody>");
				  buffer.append("</table>"); 
				  buffer.append("</td>");
				  
			   }
		}
			
			//Dietary Code
			
			DietaryDetailsDAO detailsDAO = new JDBCDietaryDetailsDAO(connection);
			ArrayList<DietaryDetails> dietarylist = detailsDAO.getAllIpdDietplan();
			 
			buffer.append("~");
			for(DietaryDetails dietaryDetails : dietarylist){
				 buffer.append("<tr>");
				 buffer.append("<td><span id='c"+dietaryDetails.getBedid()+"'><i class='fa fa-bell'></i></span> / "+dietaryDetails.getWardname()+" <br> "+dietaryDetails.getClientname()+" <br>"+dietaryDetails.getAge()+"</td>");
				 for(int i=1;i<=9;i++){
					 ArrayList<DietaryDetails> arrayList = detailsDAO.getSingleDietplanList(dietaryDetails.getParentid(),""+i);
					 buffer.append("<td>");
					 int x=0;
					 	for (DietaryDetails dietaryDetails2 : arrayList) {
					 		if(x==0){
					 			if(dietaryDetails2.getExecuted().equals("0")){
					 				buffer.append("<input type='checkbox' onclick=updateDietaryGivenStatus('"+dietaryDetails.getParentid()+"','"+i+"',this.checked)>"+dietaryDetails2.getSubcategory()+"<br>");
					 			}else{
					 				buffer.append("<input type='checkbox' checked='checked' onclick=updateDietaryGivenStatus('"+dietaryDetails.getParentid()+"','"+i+"',this.checked)>"+dietaryDetails2.getSubcategory()+"<br>");	
					 			}
					 			
					 		}else{
					 			buffer.append(""+dietaryDetails2.getSubcategory()+"<br>");
					 		}
					 		x++;
					 	}
					 buffer.append("</td>");
					 /*if(i==0){
						 DietaryDetails details = detailsDAO.getSingleDietplan(dietaryDetails.getParentid(),"Breakfast");
						 if(details.getSubcategory()!=null){
							 buffer.append("<td><center>"+details.getSubcategory()+"</center></td>");
						 }else{
							 buffer.append("<td></td>");
						 }
					 }else if(i==1){
						 DietaryDetails details = detailsDAO.getSingleDietplan(dietaryDetails.getParentid(),"Midmorning Snack");
						 if(details.getSubcategory()!=null){
							 buffer.append("<td><center>"+details.getSubcategory()+"</center></td>");
						 }else{
							 buffer.append("<td></td>");
						 }
					 }else if(i==2){
						 DietaryDetails details = detailsDAO.getSingleDietplan(dietaryDetails.getParentid(),"Lunch");
						 if(details.getSubcategory()!=null){
							 buffer.append("<td><center>"+details.getSubcategory()+"</center></td>");
						 }else{
							 buffer.append("<td></td>");
						 }
					}else if(i==3){
						 DietaryDetails details = detailsDAO.getSingleDietplan(dietaryDetails.getParentid(),"Midafternoon Snack");
						 if(details.getSubcategory()!=null){
							 buffer.append("<td><center>"+details.getSubcategory()+"</center></td>");
						 }else{
							 buffer.append("<td></td>");
						 }
					 }else if(i==4){
						 DietaryDetails details = detailsDAO.getSingleDietplan(dietaryDetails.getParentid(),"Midevening Snack");
						 if(details.getSubcategory()!=null){
							 buffer.append("<td><center>"+details.getSubcategory()+"</center></td>");
						 }else{
							 buffer.append("<td></td>");
						 }
					 }else if(i==5){
						 DietaryDetails details = detailsDAO.getSingleDietplan(dietaryDetails.getParentid(),"Dinner");
						 if(details.getSubcategory()!=null){
							 buffer.append("<td><center>"+details.getSubcategory()+"</center></td>");
						 }else{
							 buffer.append("<td></td>");
						 }
					 }*/
					 
				 }
				 buffer.append("</tr>");
			}
		
			response.setContentType("text/html");
			response.setHeader("Cache-Control", "no-cache");
			response.getWriter().write(""+buffer.toString()+""); 
			
		} catch (Exception e) {

		   e.printStackTrace();
		}
		finally {
			connection.close();
		}
		return null;
	}
	
	
	public String truncate() throws Exception{
		
		Connection connection = null;
		try {

			connection = Connection_provider.getconnection();
			IpdDAO ipdDAO = new JDBCIpdDAO(connection);
			
			CompleteAptmDAO completeAptmDAO = new JDBCCompleteAptmDAO(connection);

			int result = completeAptmDAO.deleteComplteApmt(loginInfo.getId());
		}
		catch(Exception e){
			e.printStackTrace();
		}finally{
			
			connection.close();
		}
		return null;
	}
	 //This method now called from BookappointmentAjaxAction 
	public String savecharge() throws Exception{
		
		Double total = 0.00;
		String totalx = "";
		
		String user = request.getParameter("ipdclientname");
		String apmtType = request.getParameter("chargetype");
		String quantity = request.getParameter("quantity");
		
		String charge = request.getParameter("charge");
		
		String practitionerId = request.getParameter("ipdpractitionerid");
		String clientId = request.getParameter("ipdclientid");
		String practitionerName = request.getParameter("ipdpractitionername");
		String date = request.getParameter("date");
		
		String payBuy = request.getParameter("payby");
		String markAppointment = request.getParameter("markappointment");
		String apppointmentid = "0";
		
		String masterchargetype = request.getParameter("masterchargetype");
		String mannualcharge = request.getParameter("mannualcharge");
		String packageid = request.getParameter("packageid");
		
		String visitingconsulatntdr = request.getParameter("visitingconsulatntdr");
		String isindisharecharge = request.getParameter("isindisharecharge");
		Connection connection = null;
		try{
			if(date==null){
				date = DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone()).split(" ")[0];
			} else {
				date = DateTimeUtils.getCommencingDate1(date);
			}
			connection = Connection_provider.getconnection();
			UserProfileDAO userProfileDAO = new JDBCUserProfileDAO(connection);
			ChargesAccountProcessingDAO chargesAccountProcessingDAO= new  JDBCChargeAccountProcesDAO(connection);
			int compulsary_consultant=chargesAccountProcessingDAO.isCompulasryConsultant(masterchargetype);
			if(masterchargetype!=null){
				if(masterchargetype.equals("IPD Visiting Charge") || masterchargetype.equals("Consultation Charge")||compulsary_consultant==1){
					practitionerId = visitingconsulatntdr;
					//practitionerName = userProfileDAO.getReferalDrName(visitingconsulatntdr);
					//  16 May 2018
					practitionerName = userProfileDAO.getFullName(practitionerId);
				}
			}
			
			CompleteAppointment completeAppointment = new CompleteAppointment();
			completeAppointment.setIsindisharecharge(isindisharecharge);
			completeAppointment.setVisitingconsulatntdrid(visitingconsulatntdr);
			
			PackageMasterDAO packageMasterDAO = new JDBCPackageMasterDAO(connection);
			
			CompleteAptmDAO completeAptmDAO = new JDBCCompleteAptmDAO(connection);
			NotAvailableSlotDAO notAvailableSlotDAO = new JDBCNotAvailableSlotDAO(connection);
			
			completeAppointment.setUser(user);
			
			
			String appointmentTypeName = completeAptmDAO.getAppointmentTypeName(apmtType);
			completeAppointment.setApmtType(apmtType);
			completeAppointment.setCharges(charge);
			//
			completeAppointment.setStartTime("");
			completeAppointment.setDuration("");
			completeAppointment.setPractitionerId(practitionerId);
			completeAppointment.setPractitionerName(practitionerName);
			completeAppointment.setClientId(clientId);
			completeAppointment.setCommencing(date);
			completeAppointment.setPayBuy(payBuy);
			completeAppointment.setMarkAppointment(markAppointment);
			completeAppointment.setAppointmentid(apppointmentid);
			completeAppointment.setQuantity(Integer.parseInt(quantity));
			completeAppointment.setMasterchargetype(masterchargetype);
			completeAppointment.setManualcharge(mannualcharge);
			//completeAppointment.setManuallyadded("1");
			//
			completeAppointment.setAdditionalcharge_id(apmtType);
			
				String appointmentTYpeText = notAvailableSlotDAO.getAppointmentTypeText(apmtType);
			
			
				String ipdid = (String) session.getAttribute("sessionadmissionid");
				if(ipdid!=null){
					completeAppointment.setIpdid(Integer.parseInt(ipdid));
				}
				
				if(packageid==null){
					int result = completeAptmDAO.saveCharge(completeAppointment,apppointmentid,loginInfo.getId());
				}else if(packageid.equals("")){
					int result = completeAptmDAO.saveCharge(completeAppointment,apppointmentid,loginInfo.getId());
				}else if(packageid.equals("0")){
					int result = completeAptmDAO.saveCharge(completeAppointment,apppointmentid,loginInfo.getId());
				}else{
						ArrayList<PackageMaster> arrayList = packageMasterDAO.getPackageFromChild(Integer.parseInt(packageid));
						for (PackageMaster packageMaster : arrayList) {
							completeAppointment.setManualcharge(packageMaster.getChargename());
							completeAppointment.setCharges(packageMaster.getCal_amount());
							completeAppointment.setMasterchargetype(mannualcharge);
							int result = completeAptmDAO.saveCharge(completeAppointment,apppointmentid,loginInfo.getId());
				}
			}
				
				
				
				
				ArrayList<CompleteAppointment> clientChargeListDetail = new ArrayList<CompleteAppointment>();

				clientChargeListDetail = completeAptmDAO.getPatientChrageDetails(clientId,date,apppointmentid,loginInfo.getId());
				
				for(CompleteAppointment completeAppointment2:clientChargeListDetail){
					total = completeAppointment2.getChargeTotal();
					totalx = completeAppointment2.getChargeTotalx();
				}
			//	completeAppointmentForm.setChargeTotal(total);
				
				 String textAjax = new String();
				 
				 textAjax = ("<input class = 'form-control' type = 'text' id = 'chargeTotal' name = 'chargeTotal' disabled = 'disabled' value = '"+Constants.getCurrency(loginInfo)+totalx+" '>");
				
				 
					response.setContentType("text/html");
					response.setHeader("Cache-Control", "no-cache");
					response.getWriter().write(""+textAjax.toString()+""); 
			
			
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			
			connection.close();
		}
		
		return null;
	}
	
	
	public String updatedose() throws Exception{ 
		
		Connection connection=null;
		
		try {
			connection=Connection_provider.getconnection();
			IpdDAO ipdDAO=new JDBCIpdDAO(connection);
			String id=request.getParameter("selectedid");
			String colname=request.getParameter("colname");
			String doseval=request.getParameter("savetogle");
			
			String datetime=DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone());
			
			String priscid=ipdDAO.getPriscId(id);
			
			int result=ipdDAO.updateorInsert(id,colname,doseval,datetime,priscid);
			
			
			   response.setContentType("text/html");
		       response.setHeader("Cache-Control", "no-cache");
		       response.getWriter().write("");    
			
			
		} catch (Exception e) {

		   e.printStackTrace();
		}
		finally {
			connection.close();
			
		}
		
	
		return null;
	}
	
public String updatenursingdose() throws Exception{ 
		
		Connection connection=null;
		
		try {
			connection=Connection_provider.getconnection();
			IpdDAO ipdDAO=new JDBCIpdDAO(connection);
			String id=request.getParameter("selectedid");
			String colname=request.getParameter("colname");
			String doseval=request.getParameter("savetogle");
			
			String datetime=DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone());
			
			String nursingid=ipdDAO.getNursingId(id);
			
			int result=ipdDAO.updateorInsertNursingToggle(id,colname,doseval,datetime,nursingid);
			
			
			response.setContentType("text/html");
		       response.setHeader("Cache-Control", "no-cache");
		       response.getWriter().write("");    
			
			
		} catch (Exception e) {

		   e.printStackTrace();
		}
		finally {
			connection.close();
			
		}

		return null;
	}
	
	
	
	
	
	public String admission(){
		
		try{
			String admissionid = request.getParameter("admissionid");
			session.setAttribute("sessionadmissionid", admissionid);
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return null;
	}
	
	
	
	public IpdForm getModel() {
	
		return ipdForm;
	}


	public void saveprescrminder() throws Exception{
		Connection connection = null;
		try {
			
			DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			Calendar cal = Calendar.getInstance();
			//cal.add(Calendar.DATE, -7); 
			String currDate = dateFormat.format(cal.getTime());
			

			connection = Connection_provider.getconnection();
			IpdDAO ipdDAO = new JDBCIpdDAO(connection);
			
			ArrayList<Bed>bookedbedlist = ipdDAO.getBookedBedList();
			for(Bed bed : bookedbedlist){
				ArrayList<Priscription>parentPriscList = ipdDAO.getParentPriscList(bed.getAddmissionid());
				
				for(Priscription priscription : parentPriscList){
					String temp[] = priscription.getDate().split(" ");
					String mdicinestartdate = temp[0];
					
					long mdicinedays = DateTimeUtils.getDiffofTwoDates(mdicinestartdate, currDate);
					mdicinedays++;
					
					
					ArrayList<Priscription>clientPriscList = ipdDAO.getClientPriscList(priscription.getParentid());
					for(Priscription pr : clientPriscList){
						boolean checkpriscexist = ipdDAO.checkPrescExist(mdicinedays,pr.getId());
						String dosecolumn = "";
						String doseqmark = "";
						
						if(!checkpriscexist){
							if(pr.getDosage()!=null){
								String dosetemp[] = pr.getDosage().split("-");
								int c = 0;
								for(int i=1;i<=dosetemp.length;i++){
									
									doseqmark = doseqmark + 0 +  ",";
									dosecolumn = dosecolumn +  "dos" + i + ",";
									c++;
								}
								dosecolumn = dosecolumn.substring(0,dosecolumn.length()-1);
								doseqmark = doseqmark.substring(0,doseqmark.length()-1);
								
								if(mdicinedays<=pr.getDays()){
									int result = ipdDAO.savePrescReminder(dosecolumn,doseqmark,mdicinedays,pr.getId(),bed.getAddmissionid());
								}
								
							}
							
							
							
						}
					}
				}
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
		finally {
			connection.close();
		}
		
			
		
	}
	
	
	public void savenursingrminder() throws Exception{
		Connection connection = null;
		try {
			
			DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			Calendar cal = Calendar.getInstance();
			//cal.add(Calendar.DATE, -7); 
			String currDate = dateFormat.format(cal.getTime());
			

			connection = Connection_provider.getconnection();
			IpdDAO ipdDAO = new JDBCIpdDAO(connection);
			
			ArrayList<Bed>bookedbedlist = ipdDAO.getBookedBedList();
			for(Bed bed : bookedbedlist){
				ArrayList<Master> nursingParentList = ipdDAO.getParentNursingList(bed.getAddmissionid());
				
				for(Master master : nursingParentList){
					String temp[] = master.getDate().split(" ");
					String mdicinestartdate = temp[0];
					
					long mdicinedays = DateTimeUtils.getDiffofTwoDates(mdicinestartdate, currDate);
					mdicinedays++;
					
					
					ArrayList<Master> clientNursingList = ipdDAO.getClientNursingList(master.getParentid());
					for(Master pr : clientNursingList){
						boolean checkpriscexist = ipdDAO.checkNursingExist(mdicinedays,pr.getId());
						String dosecolumn = "";
						String doseqmark = "";
						
						if(!checkpriscexist){
							if(pr.getFrequency()!=null){
								String dosetemp[] = pr.getFrequency().split("-");
								int c = 0;
								for(int i=1;i<=dosetemp.length;i++){
									
									doseqmark = doseqmark + 0 +  ",";
									dosecolumn = dosecolumn +  "dos" + i + ",";
									c++;
								}
								dosecolumn = dosecolumn.substring(0,dosecolumn.length()-1);
								doseqmark = doseqmark.substring(0,doseqmark.length()-1);
								
								if(mdicinedays<=pr.getDays()){
									int result = ipdDAO.saveNursingReminder(dosecolumn,doseqmark,mdicinedays,pr.getId(),bed.getAddmissionid());
								}
								
							}
						}
					}
				}
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
		finally {
			connection.close();
		}
			
		
	}
	
	
	public String prisctogle() throws Exception{
		// var url = "prisctogleIpdDashboard?selectedid="+id+"&colname="+colname+"&savetogle="+savetogle+" ";
		
		Connection connection = null;
		
		try{
			connection = Connection_provider.getconnection();
			String selectedid = request.getParameter("selectedid");
			String colname = request.getParameter("colname");
			boolean savetogle = Boolean.parseBoolean(request.getParameter("savetogle"));
			IpdDAO ipdDAO = new JDBCIpdDAO(connection);
			
			int upd = ipdDAO.updatePriscReminder(selectedid,colname,savetogle,loginInfo.getUserId());
			
			  response.setContentType("text/html");
		       response.setHeader("Cache-Control", "no-cache");
		       response.getWriter().write("");    
			
		}catch(Exception e){
			e.printStackTrace();
		}
		finally {
			connection.close();
		}
		
		
		return null;
	}
	
	
	public String nursingtogle() throws Exception{
		
		Connection connection = null;
		String selectedid = request.getParameter("selectedid");
		String colname = request.getParameter("colname");
		boolean savetogle = Boolean.parseBoolean(request.getParameter("savetogle"));
		
		try{
			connection = Connection_provider.getconnection();
			IpdDAO ipdDAO = new JDBCIpdDAO(connection);
			
			int upd = ipdDAO.updateNursingReminder(selectedid,colname,savetogle,loginInfo.getUserId());
			
			response.setContentType("text/html");
		       response.setHeader("Cache-Control", "no-cache");
		       response.getWriter().write("");    
			
		}catch(Exception e){
			e.printStackTrace();
		} finally {
			connection.close();
		}
	
		return null;
	}


	public String treatmentsheet() throws Exception {
		  Connection connection=null;
		  try {
		   connection=Connection_provider.getconnection();
		   IpdDAO ipdDAO=new JDBCIpdDAO(connection);
		   InvestigationDAO investigationDAO = new JDBCInvestigationDAO(connection);
		   ClientDAO clientDAO=new JDBCClientDAO(connection);
		   BedDao bedDao=new JDBCBedDao(connection);
		   String clientid=request.getParameter("clientid");
		   String ipdid=request.getParameter("ipdid");  
		   if(ipdid==null){
			   ipdid =   String.valueOf(ipdDAO.getLastIpdId(clientid)); 
		   }
		   if(ipdid.equals("0")){
			   ipdid =   String.valueOf(ipdDAO.getLastIpdId(clientid));
		   }
		   
		   String filter=ipdForm.getFilter(); 
		               
		   String date= ipdForm.getDate();
		   if(date==null){
			   Calendar calendar=Calendar.getInstance();
			   SimpleDateFormat dateFormat= new SimpleDateFormat("yyyy-MM-dd");
			   date= dateFormat.format(calendar.getTime());
		   } else if(!date.equals("")){
			   
			   date= DateTimeUtils.getCommencingDate1(date);
		   } else {
			   Calendar calendar=Calendar.getInstance();
			   SimpleDateFormat dateFormat= new SimpleDateFormat("yyyy-MM-dd");
			   date= dateFormat.format(calendar.getTime());
		   }
		  
		   
		   if(filter==null){
			   filter="All";
		   }
		   if(filter.equals("")){
			   filter="All";
		   }
		   
		   String days[]=getThreeDays();
		   ipdForm.setToday(days[0]);
		   ipdForm.setTomorow(days[1]);
		   ipdForm.setDayAfterTomorow(days[2]);
		   ipdForm.setClientid(clientid);
		   ipdForm.setIpdid(ipdid);
		   ipdForm.setFilter(filter);
		   
		   ArrayList<Priscription> listMedicineLog= new ArrayList<Priscription>();
		   ArrayList<Master> listNursingLog= new ArrayList<Master>();
		   ArrayList<String> timeList= new ArrayList<String>();
		   ArrayList<String> iotimeList= new ArrayList<String>();
		   ArrayList<String> ivtimeList= new ArrayList<String>();
		   ArrayList<String> eqtimeList= new ArrayList<String>();
		   ArrayList<Master> vitalMasterandValueList= new ArrayList<Master>();
		   ArrayList<Master> investigationList =new ArrayList<Master>();
		   ArrayList<Bed> visitingConsultList =new ArrayList<Bed>(); 
		   ArrayList<Bloodbank> bloodRequestedList= new ArrayList<Bloodbank>();
		   ArrayList<Master> vitalMasterIOList= new ArrayList<Master>();
		   ArrayList<Ipd> nursingcarelist= new ArrayList<Ipd>();
		   ArrayList<Master> vitalMasterIVList= new ArrayList<Master>();
		   ArrayList<Master> vitalMasterEquipmentList=new ArrayList<Master>();
		   ArrayList<DietaryDetails> dietarydatalist = new ArrayList<DietaryDetails>(); 
		   if(filter.equals("Medicine")){
			     //for Medicine List
			     listMedicineLog=ipdDAO.getTreatmentSheetPriscription(ipdid,clientid,date); 
			    
		   }
		   else if(filter.equals("Nursing")){
			   
			   // for Nursing Care
			   listNursingLog=ipdDAO.getTreatmentSheetNursing(ipdid,clientid,date); 
			  
		   } else if(filter.equals("Vitals")){
			   //for Vitals 
			   vitalMasterandValueList= ipdDAO.getDailyCareDataListandValues(clientid,ipdid,date,"1");
			   timeList= ipdDAO.getDailyCareTimeList(ipdid, clientid, date, "1");
			  
		   } else if(filter.equals("I/O")){
			   vitalMasterIOList =ipdDAO.getDailyCareDataListandValues(clientid,ipdid,date,"2");
			   iotimeList= ipdDAO.getDailyCareTimeList(ipdid, clientid, date, "2");
			   
			   
		   }
		   else if(filter.equals("IV")){
			   vitalMasterIVList =ipdDAO.getDailyCareDataListandValues(clientid,ipdid,date,"3");
			   ivtimeList= ipdDAO.getDailyCareTimeList(ipdid, clientid, date, "3");
			   
			   
		   } else if(filter.equals("Ventilator")){
			   vitalMasterEquipmentList =ipdDAO.getDailyCareDataListandValues(clientid,ipdid,date,"4");
			   eqtimeList= ipdDAO.getDailyCareTimeList(ipdid, clientid, date, "4");
			   
		   }
		   else if(filter.equals("Investigation")){
			   //for investigation
			   investigationList= investigationDAO.getInvestigationList(clientid, date, date, loginInfo);
			   
		   }
		   else if(filter.equals("Consultant")){
			   
			   visitingConsultList= ipdDAO.getClientVisitingConsultList(ipdid, date);
			   
		   }
		   else if(filter.equals("Blood")){
			   BloodbankDAO bloodbankDAO= new JDBCBloodBankDAO(connection);
			   bloodRequestedList=  bloodbankDAO.getBloodRequestList(clientid,ipdid,date);
		   }else if(filter.equals("Dietary")){
			   //  10 nov 2017
			   DietaryDetailsDAO dietaryDetailsDAO = new JDBCDietaryDetailsDAO(connection);
			   dietarydatalist = dietaryDetailsDAO.getIpdDietPlan(ipdid,date);
		   }
		  else if(filter.equals("Nursing Care Plan")){
			   //Lokesh 8-5-18
			   IpdDAO ipddao= new JDBCIpdDAO(connection);
			nursingcarelist= ipddao.getallNursingplans(ipdid, date);
			
		   }
		   else if(filter.equals("All")){
				   //for Medicine List
				    listMedicineLog=ipdDAO.getTreatmentSheetPriscription(ipdid,clientid,date); 
				   // for Nursing Care
				   listNursingLog=ipdDAO.getTreatmentSheetNursing(ipdid,clientid,date);
				   
				   //for Vitals 
				   vitalMasterandValueList= ipdDAO.getDailyCareDataListandValues(clientid,ipdid,date,"1");
				   timeList= ipdDAO.getDailyCareTimeList(ipdid, clientid, date, "1");
				    
				   //for I/O
				   vitalMasterIOList =ipdDAO.getDailyCareDataListandValues(clientid,ipdid,date,"2");
				   iotimeList= ipdDAO.getDailyCareTimeList(ipdid, clientid, date, "2");
				   
				   //for IV
				   vitalMasterIVList =ipdDAO.getDailyCareDataListandValues(clientid,ipdid,date,"3");
				   ivtimeList= ipdDAO.getDailyCareTimeList(ipdid, clientid, date, "3");
				   
				   //for ventilator
				   vitalMasterEquipmentList =ipdDAO.getDailyCareDataListandValues(clientid,ipdid,date,"4");
				   eqtimeList= ipdDAO.getDailyCareTimeList(ipdid, clientid, date, "4");
				   
				   //for investigation
				   investigationList= investigationDAO.getInvestigationList(clientid, date, date, loginInfo);
				   visitingConsultList= ipdDAO.getClientVisitingConsultList(ipdid, date);
				   
				   //for blood
				   BloodbankDAO bloodbankDAO= new JDBCBloodBankDAO(connection);
				   bloodRequestedList=  bloodbankDAO.getBloodRequestList(clientid,ipdid,date);
				   // for dietary
				 //  10 nov 2017
				   DietaryDetailsDAO dietaryDetailsDAO = new JDBCDietaryDetailsDAO(connection);
				   dietarydatalist = dietaryDetailsDAO.getIpdDietPlan(ipdid,date);
				 //lokesh 8-518  
				   IpdDAO ipddao= new JDBCIpdDAO(connection);
					nursingcarelist= ipddao.getallNursingplans(ipdid, date);
		   }
		   ipdForm.setDietarydatalist(dietarydatalist);
		   ipdForm.setListMedicineLog(listMedicineLog);
		   ipdForm.setListNursingLog(listNursingLog);
		   ipdForm.setInvestigationList(investigationList);
		   ipdForm.setTimeList(timeList);
		   ipdForm.setVitalMasterList(vitalMasterandValueList);
		   ipdForm.setVisitingConsultList(visitingConsultList);
		   ipdForm.setBloodRequestedList(bloodRequestedList);
		   ipdForm.setVitalMasterIOList(vitalMasterIOList);
		   ipdForm.setIotimeList(iotimeList);
		   ipdForm.setVitalMasterIVList(vitalMasterIVList);
		   ipdForm.setIvtimeList(ivtimeList);
		   ipdForm.setVitalMasterEquipmentList(vitalMasterEquipmentList);
		   ipdForm.setEqtimeList(eqtimeList);
		   ipdForm.setNursingcarelist(nursingcarelist);
		   
		   
		   
		 //  String calldays[]=getcurrentandDayafterTomorow();
		   ArrayList<Bed> treatmentlist= new ArrayList<Bed>();//ipdDAO.getTreatmentSheetList(clientid,calldays);
		   ipdForm.setTreatmentlist(treatmentlist);
		   
		   Client client=clientDAO.getClientDetails(clientid);
		            String format=client.getFirstName()+" "+client.getLastName();    
		   
		            String agegender= client.getAge()+"/"+client.getGender();
		            Bed bed=bedDao.getEditIpdData(ipdid);
		            
		            String wardname=ipdDAO.getIpdWardName(bed.getWardid());
		            String bedname=ipdDAO.getIpdBedName(bed.getBedid());
		            String beddetails=wardname+"/"+bedname;            
		            ipdForm.setBedid(beddetails);
		            ipdForm.setAgegender(agegender);
		            ipdForm.setAbrivationid(client.getAbrivationid()+"/ "+clientid);
		            ipdForm.setAddress(client.getAddress()); 
		            ipdForm.setContact(client.getMobNo());
		            ipdForm.setClient(format);
		            
		            //Height 
		            String height= ipdDAO.getClientVitalData("Height", clientid);
		            //Weight
		            String weight= ipdDAO.getClientVitalData("Weight", clientid);
		            /*//Blood Group
		            String blood_group= ipdDAO.getClientVitalData("Blood Group", clientid);*/
		            ipdForm.setHeight(height);
		            ipdForm.setWeight(weight);
		            
		            
		            
		            
		      date= DateTimeUtils.getCommencingDate1(date);
		      ipdForm.setDate(date);
		      ipdForm.setFilter(filter);      
		            
		            
		  } catch (Exception e) {

		     e.printStackTrace();
		  }
		        finally {
		         connection.close();
		        }
		   
		  return "treatmentsheet";
		 }
	
	  
	public String getfromdayssheet()throws Exception {
		  
	      
		  Connection connection=null;
		 
		  try {
		    
		   String clientid=ipdForm.getClientid();
		   String ipdid=ipdForm.getIpdid();  
		   String date=ipdForm.getDatetime();
		   connection=Connection_provider.getconnection();
		   IpdDAO ipdDAO=new JDBCIpdDAO(connection);
		   ClientDAO clientDAO=new JDBCClientDAO(connection);
		   BedDao bedDao=new JDBCBedDao(connection);
		   
		   String newdate=DateTimeUtils.changeDateFormat(date);
		   
		   String threedays[]=get3daysfromdateinddmmmyyyy(date);
		   ipdForm.setToday(threedays[2]);
		   ipdForm.setTomorow(threedays[1]);
		   ipdForm.setDayAfterTomorow(threedays[0]);
		   ipdForm.setIpdid(ipdid);
		   ipdForm.setClientid(clientid);
		   
		   
		   String days[]=get2daysfromdate(newdate);
		   
		   
		   ArrayList<Bed> treatmentlist=ipdDAO.getTreatmentSheetList(clientid, days);
		   
		            ipdForm.setTreatmentlist(treatmentlist);
		   
		   Client client=clientDAO.getClientDetails(clientid);
		            String format=""+clientid+"/"+client.getFirstName()+" "+client.getLastName()+"/"+client.getGender()+"/"+client.getAge()+"";      
		   
		            Bed bed=bedDao.getEditIpdData(ipdid);
		            
		            String wardname=ipdDAO.getIpdWardName(bed.getWardid());
		            String bedname=ipdDAO.getIpdBedName(bed.getBedid());
		            String beddetails=wardname+"/"+bedname;            
		            ipdForm.setBedid(beddetails);
		            
		            ipdForm.setClient(format);
		   
		   
		  } catch (Exception e) {

		     e.printStackTrace();
		  }
		  finally {
		   connection.close();
		  }
		  
		 
		  return "treatmentsheet";
		 }
	
	
	
	
	
	public void prepare() throws Exception {
		
		Connection connection = null;
		
		try{
			connection = Connection_provider.getconnection();
			NotAvailableSlotDAO notAvailableSlotDAO = new JDBCNotAvailableSlotDAO(connection);
			MasterDAO masterDAO = new JDBCMasterDAO(connection);
			EmrDAO emrDAO = new JDBCEmrDAO(connection);
			IpdDAO ipdDAO=new JDBCIpdDAO(connection);
			PharmacyDAO pharmacyDAO = new JDBCPharmacyDAO(connection);
			
			
			InvestigationDAO investigationDAO = new JDBCInvestigationDAO(connection);
			UserProfileDAO userProfileDAO = new JDBCUserProfileDAO(connection);
			
			String date = DateTimeUtils.getDashboardTodayDate(loginInfo.getTimeZone());
			ipdForm.setPriscdate(date);
			
			String curdate = DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone()).split(" ")[0];
			ipdForm.setPkgfromdate(DateTimeUtils.getCommencingDate1(curdate));
			ipdForm.setPkgtodate(DateTimeUtils.getCommencingDate1(curdate));
			ipdForm.setPriscdateandtime(DateTimeUtils.getPriscDatetime(loginInfo.getTimeZone()));
			
			ArrayList<Location>locationList = notAvailableSlotDAO.getLocationList(loginInfo.getId());
			ipdForm.setLocationList(locationList);
			String regLocationID = emrDAO.getRegisteredLocationId();
			ipdForm.setLocationid(regLocationID);
			
			
			//AppointmentDAO appointmentDAO = new JDBCAppointmentDAO(connection);
			/*ArrayList<AppointmentType>additionalChargeList = appointmentDAO.getAdditionalChaergeTypeList("");
			ipdForm.setAdditionalChargeList(additionalChargeList);*/
			
			
			
		/*	ArrayList<Master>medicineList = masterDAO.getMedicineList();
			ipdForm.setMedicineList(medicineList);*/
			
			
			ArrayList<Master>wardList = masterDAO.getWardList();
			ipdForm.setWardList(wardList);
			UserProfile userProfile = userProfileDAO.getUserprofileDetails(loginInfo.getId());
			ipdForm.setWardid(userProfile.getWardid());
			
			SMSTemplateDAO smsTemplateDAO = new JDBCSMSTemplateDao(connection);
			ArrayList<EmailTemplate>smsTemplateList = smsTemplateDAO.getAllSMSTemplates();
			ipdForm.setSmsTemplateList(smsTemplateList);
			
			ArrayList<Priscription>parentPriscList = new ArrayList<Priscription>();
			ipdForm.setParentPriscList(parentPriscList);
			
			//vital Master List
			 ArrayList<Master> vitalMasterList=ipdDAO.getVitalMasterByCategory("1");
			 ipdForm.setVitalMasterList(vitalMasterList);
			 
			 // vital intake/ output
			 ArrayList<Master> vitalMasterIOList=ipdDAO.getVitalMasterByCategory("2");
			 ipdForm.setVitalMasterIOList(vitalMasterIOList);
			 
			 //vital IV list
			 ArrayList<Master> vitalMasterIVList=ipdDAO.getVitalMasterByCategory("3");
			 ipdForm.setVitalMasterIVList(vitalMasterIVList);
			 
			 //vital Equipment List
			 ArrayList<Master> vitalMasterEquipmentList=ipdDAO.getVitalMasterByCategory("4");
			 ipdForm.setVitalMasterEquipmentList(vitalMasterEquipmentList);
			
			//investigation 

			//investigation section master
			ArrayList<Master>invSectionList = investigationDAO.getInvestigationSectionList();
			ipdForm.setInvSectionList(invSectionList);
			
			ArrayList<Master>invsTypeList = emrDAO.getInvesigationTypeList();
			ipdForm.setInvsTypeList(invsTypeList);
			
			ArrayList<Master>invstReportTypeList = emrDAO.getInvstReportTypeList();
			ipdForm.setInvstReportTypeList(invstReportTypeList);
			
			ArrayList<Master>invstUnitList = emrDAO.getInvstUnitList();
			ipdForm.setInvstUnitList(invstUnitList);
			
			ArrayList<Master>cbcIdList = investigationDAO.getCbcIdList();
			ipdForm.setCbcIdList(cbcIdList);
			
			ArrayList<String> jobTitleList = userProfileDAO.getJobTitleList(); 
			ipdForm.setJobTitleList(jobTitleList);
			ipdForm.setJobtitle(userProfile.getJobtitle());
			
			
			//standard charge list
			
			ArrayList<Master> standardChargesList=masterDAO.getStandardChargesList(null);
			ipdForm.setStandardChargesList(standardChargesList);

			//Visiting Consultant
			ArrayList<Bed> visitingConsultList=ipdDAO.getVisitingConsultList(null,null,null,null,null,null);
			ipdForm.setVisitingConsultList(visitingConsultList); 
			
			//Practitioner list only Visiting Consult
			ArrayList<UserProfile> visitingConsultDoctors=userProfileDAO.getVisitingPractitiner();
			ipdForm.setVisitingConsultDoctors(visitingConsultDoctors);
			ipdForm.setVisitingtimeList(PopulateList.startTimeList());
			
			//Nursing Care
			ArrayList<Master> nursingdetails=masterDAO.getAllNursingDetails(null,null);
			ipdForm.setNursingdetails(nursingdetails);
          
			ArrayList<Master> nursingcategorylist=masterDAO.getAllNursingCategory(null);
			ipdForm.setNursingcategorylist(nursingcategorylist);
			
			//jitu
			ArrayList<Master> specializationTemplateList= masterDAO.getMasterSpecializationList();
			ipdForm.setSpecializationTemplateList(specializationTemplateList);
			
			//set template name list
			
			ArrayList<Priscription>templateNameList = emrDAO.getTemplateNameList(loginInfo);
			ipdForm.setTemplateNameList(templateNameList);
			
			//set state and city list
			InventoryVendorDAO vendorDAO=new JDBCInventoryVendorDAO(connection);
			ArrayList<Master> stateList= vendorDAO.getAllStateList();
			ipdForm.setStatelist(stateList);
			/*ArrayList<Master> cityList= vendorDAO.getAllCityList();
			ipdForm.setCitylist(cityList);*/
			
			//package list
			ArrayList<Master>packageList = ipdDAO.getPackageList();
			ipdForm.setPackageList(packageList);
			
			//investigation pkg list
			ArrayList<Master>pkgsList = investigationDAO.getInvPaksLists();
			ipdForm.setPkgsList(pkgsList);
			
			ipdForm.setHourList(PopulateList.hourList());
			ipdForm.setMinuteList(PopulateList.getMinuteList());
			ipdForm.setTimeList(PopulateList.getSImpleTimeList());
			
			String datetime=DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone());
			String temp[] = datetime.split(" ");
			String time[] = temp[1].split(":");
			String hh = time[0];
			String mm = time[1];
			ipdForm.setHour(hh);
			ipdForm.setMinute(mm);
			
			//  15-07-2020 for speed up -start
			//ArrayList<Master>mdicneTypeList = emrDAO.getMedicineTypeList();
			ArrayList<Master>mdicneTypeList = new ArrayList<Master>();
			ipdForm.setMdicneTypeList(mdicneTypeList);
			
			//ArrayList<Master>mdicinecategoryList = emrDAO.getmedicineCategoryList();
			ArrayList<Master>mdicinecategoryList = new ArrayList<Master>();
			ipdForm.setMdicinecategoryList(mdicinecategoryList);
			
			//ArrayList<Master>dosageList = emrDAO.getDosageList();
			ArrayList<Master>dosageList = new ArrayList<Master>();
			ipdForm.setDosageList(dosageList);
			
			//ArrayList<Priscription> medicinetimelist = emrDAO.getMedicineTimeList(); 
			ArrayList<Priscription> medicinetimelist = new ArrayList<Priscription>();
			ipdForm.setMedicinetimelist(medicinetimelist);
			
			//ArrayList<Master>dosagenoteList = masterDAO.getDosageNoteList();
			ArrayList<Master>dosagenoteList = new ArrayList<Master>();
			ipdForm.setDosagenoteList(dosagenoteList);
			
			ArrayList<Master> priscUnitList= masterDAO.getPriscUnitList();
			ipdForm.setPriscUnitList(priscUnitList);
			
			ArrayList<Master>nimaidoselist = new ArrayList<Master>();
			ArrayList<Master>nimaiqtylist = new ArrayList<Master>();
			ArrayList<Master>nimairemarklist = new ArrayList<Master>();
			
			if(loginInfo.getOutoprisc()==1){
				nimaidoselist = masterDAO.getnimaidoselistt();
				nimaiqtylist = masterDAO.getnimaiqtylist();
				nimairemarklist = masterDAO.getnimairemarlist();
			}
			//End akash 15-07-2020
			ipdForm.setNimaidoselist(nimaidoselist);
			ipdForm.setNimaiqtylist(nimaiqtylist);
			ipdForm.setNimairemarklist(nimairemarklist);
			
			ArrayList<Master> requestlocationlist= pharmacyDAO.getAllLocationNew();
			ipdForm.setRequestlocationlist(requestlocationlist);
			if(loginInfo.isPrisc_location_list()){
				int default_location = pharmacyDAO.getByDefaultPharmacyLocation();
				ipdForm.setRequestlocationid(""+default_location);
			}
			
			ArrayList<Master> vitalList=masterDAO.getallVitalMasterdata("1");
			ipdForm.setVitalList(vitalList);
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			connection.close();
		}
		
		
	}
	
	
	public String showvisitingconsult() throws Exception {
        Connection connection=null;   
		try {
			
			connection=Connection_provider.getconnection();
			IpdDAO ipdDAO=new JDBCIpdDAO(connection);
			ClientDAO clientDAO=new JDBCClientDAO(connection);
			String ipdid=request.getParameter("ipdid");
			String clientid=request.getParameter("clientid");
			StringBuffer buffer=new StringBuffer();
			
			Client client=clientDAO.getClientDetails(clientid);
			
			String fullname=client.getTitle()+" "+client.getFirstName()+" "+client.getLastName();
			
			ArrayList<Bed> visitingConsultList= ipdDAO.getVisitingConsult(clientid,ipdid);
			
			int i=0;
			for(Bed bed:visitingConsultList){
				
				 buffer.append("<tr>");
				 buffer.append("<td>"+(++i)+"</td>");
				 buffer.append("<td>"+bed.getPractitionername()+"</td>");
				 buffer.append("<td>"+bed.getDate()+"</td>");
				 buffer.append("<td>"+bed.getTime()+"</td>");
				 buffer.append("<td>"+bed.getFees()+"</td>");
				 
				 if(bed.getStatus().equals("1")){
					 buffer.append("<td id='btnisvisited"+bed.getId()+"'> <a class='visited'  onclick='updateVisitedorNot("+bed.getId()+",0)' href='#'>Visited</a></td>");
				 } else {
					 buffer.append("<td id='btnisvisited"+bed.getId()+"'> <a class='notvisited'  onclick='updateVisitedorNot("+bed.getId()+",1)' href='#'>Not Visited</a></td>");
				 }
				 if(bed.getPayment().equals("1")){
					 buffer.append("<td id='btnpvisit"+bed.getId()+"' style='font-weight: bold;'> <a class='btn btn-primary' onclick='addchargornot("+bed.getId()+","+bed.getIpdid()+","+bed.getClientid()+",0)' href='#' style='color: green;'>Paid</a></td>");
				 } else {
					 buffer.append("<td id='btnpvisit"+bed.getId()+"' style='font-weight: bold;'> <a class='btn btn-primary' onclick='addchargornot("+bed.getId()+","+bed.getIpdid()+","+bed.getClientid()+",1)' href='#' style='color: red;'>Not Paid</a></td>");
				 }
				 buffer.append("<td><a onclick='editIpdVisit("+bed.getId()+")'><i class='fa fa-edit'></i></a></td>");
				 buffer.append("<td><a onclick='deleteIpdVisit("+bed.getId()+")'><i class='fa fa-trash'></i></a></td>");
				 buffer.append("</tr>");
			
			}
			
			 String data=fullname+"~"+buffer.toString();
			 response.setContentType("text/html");
		     response.setHeader("Cache-Control", "no-cache");
			 response.getWriter().write(data); 
			
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			
			connection.close();
		}
		
		
		return null;
	}
	
	
	
	public String addchargeornot() throws Exception {
		
		Connection connection=null;
		try {
			
			connection=Connection_provider.getconnection();
			ClientDAO clientDao=new JDBCClientDAO(connection);
			IpdDAO ipdDAO=new JDBCIpdDAO(connection);
			CompleteAptmDAO completeAptmDAO=new JDBCCompleteAptmDAO(connection);
			UserProfileDAO userProfileDAO=new JDBCUserProfileDAO(connection);
			MasterDAO masterDAO=new JDBCMasterDAO(connection);
			
			String ipdid=request.getParameter("ipdid");
			String clientid=request.getParameter("clientid");
			String visitid=request.getParameter("visitid");
			String status=request.getParameter("status");
			
			if(status.equals("1")) {
			
			Bed visitcharge=ipdDAO.getIpdVisitingConsult(visitid);
			Client client=clientDao.getClientDetails(clientid);
			
			int invoiceid=0;
		
			
			String date1 = DateTimeUtils.getDateinSimpleFormate(new Date());
			String stemp[] = date1.split(" ");
			
			String temp[] = stemp[0].split("-");
			date1 = temp[2] + "-" + temp[1] + "-" + temp[0];
			
				CompleteAppointment appointment=new CompleteAppointment();
				appointment.setClientId(clientid);
				appointment.setPractitionerId(visitcharge.getPractitionerid());
				appointment.setChargeType("IPD Visiting Charge");
				appointment.setLocation("1");
			    appointment.setAdditionalcharge_id(visitid);
			    appointment.setIpdid(Integer.parseInt(ipdid));
			    appointment.setInvoiceDate(date1);
			    appointment.setIpd("1");
			    appointment.setAppointmentid("0");
			    appointment.setWardid("0");
			    if(client.getWhopay()!=null){
			    	
			    	if(client.getWhopay().equals("Self") || client.getWhopay().equals("Client")){
			    	       
			    		appointment.setPolicyExcess("0");
			    		appointment.setPayBuy("0");
			    	} else {
			    		appointment.setPolicyExcess("1");
			    		appointment.setPayBuy("1");
			    	}
			    }
			    		    
			    UserProfile userProfile=userProfileDAO.getUserprofileDetails(Integer.parseInt(visitcharge.getPractitionerid()));
			    String userfullname=userProfile.getFullname();
			    String discid= userProfile.getDiciplineName();
			    Master master = masterDAO.getDisciplineData(discid);
			    
			    invoiceid=completeAptmDAO.saveAmpmInvoice(appointment,loginInfo.getId(),loginInfo.getUserId());
			    
			    String fullname=client.getTitle()+" "+client.getFirstName()+" "+client.getLastName();
			    appointment.setUser(fullname);
			    appointment.setCommencing(date1);     
			    
			    String apmtype="Consultation With "+userfullname+", "+master.getDiscipline()+"";
			    
			    appointment.setApmtType(apmtype);
			    appointment.setCharges(visitcharge.getFees());
			    appointment.setAdditionalcharge_id(visitid);
			    appointment.setMasterchargetype("IPD Visiting Charge");
			    appointment.setQuantity(1);
			    int res=completeAptmDAO.saveInvoiceAssesment(appointment, invoiceid);
			     
			    int result=ipdDAO.updateVisitingPaymentStatus(status,visitid);
			    
			}
			else {
				
				int result=completeAptmDAO.deletIpdVisitingConsultCharge(visitid,ipdid,clientid);
				int res=ipdDAO.updateVisitingPaymentStatus(status,visitid);
				
			}
			
			response.setContentType("text/html");
		    response.setHeader("Cache-Control", "no-cache");
			response.getWriter().write(""); 
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			connection.close();
		}
		return null;
	}
	
	
	
	
	private String[] getThreeDays() {
		  
		  String days[]=new String[3];
		  
		  try {
		   
		   Calendar calendar=Calendar.getInstance(Locale.getDefault());
		   Date today = calendar.getTime();

		   calendar.add(Calendar.DAY_OF_MONTH, -1);
		   Date previous = calendar.getTime();
		   
		   calendar.add(Calendar.DAY_OF_MONTH, -1);
		   Date afterprevious=calendar.getTime();
		   
		   DateFormat dateFormat = new SimpleDateFormat("dd-MMM-yyyy");

		   String todayAsString = dateFormat.format(today);
		   days[0]=todayAsString;
		   String tomorrowAsString = dateFormat.format(previous);
		   days[1]=tomorrowAsString;
		            String dayAfter2morow=dateFormat.format(afterprevious); 
		   days[2]=dayAfter2morow;
		                   
		   
		  } catch (Exception e) {

		    e.printStackTrace();
		   }
		  
		  return days;
		 }
		 
		 public String[] getcurrentandDayafterTomorow() {
		  
		  
		  SimpleDateFormat sdfDate = new SimpleDateFormat("yyyy-MM-dd");//dd/MM/yyyy
		     Date now = new Date();
		     String strDate = sdfDate.format(now);
		       
		     Calendar calendar=Calendar.getInstance(Locale.getDefault());
		     calendar.add(Calendar.DAY_OF_MONTH, -1);
		     Date previousday=calendar.getTime();
		     String dayp1=sdfDate.format(previousday);
		     
		     calendar.add(Calendar.DAY_OF_MONTH, -1);
		     Date previousd3=calendar.getTime();
		     
		     Calendar calendar2=Calendar.getInstance(Locale.getDefault());
		     calendar2.add(Calendar.DAY_OF_MONTH, 1);
		     
		     Date nextday=calendar2.getTime();
		     String nextD=sdfDate.format(nextday);
		     
		     String dayp2=sdfDate.format(previousd3);
		     
		    return new String[]{dayp2,dayp1,strDate,nextD};
		 }

		 private String[] get2daysfromdate(String date) {
			  
			  String str[]=null;
			  
			  try {
			   SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			   Calendar c = Calendar.getInstance(Locale.getDefault());
			   c.setTime(sdf.parse(date));
			           
			   Date curDate=c.getTime();
			   String currentday=sdf.format(curDate);
			      
			   c.add(Calendar.DAY_OF_MONTH, -1);    
			   Date previous1=c.getTime();  
			   String previous1Day=sdf.format(previous1);
			   
			   c.add(Calendar.DAY_OF_MONTH, -1);
			   Date previous2=c.getTime();
			   String previous2Day=sdf.format(previous2);
			   
			   c.add(Calendar.DAY_OF_MONTH, 3);
			         Date nextDay=c.getTime();
			         String dayNext=sdf.format(nextDay);
			   
			         str=new String[]{previous2Day,previous1Day,currentday,dayNext};
			         
			   
			  } catch (Exception e) {

			    e.printStackTrace();
			  }
			  return str;
			 }
	
	private String[] get3daysfromdateinddmmmyyyy(String date) {
		  
		  String str[]={"","",""};
		  
		 
		  try {
		   SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		   Calendar c = Calendar.getInstance(Locale.getDefault());
		   c.setTime(sdf.parse(date));
		   
		   SimpleDateFormat sdf1 = new SimpleDateFormat("dd-MMM-yyyy");
		   
		   Date curDate=c.getTime();
		   String curDay=sdf1.format(curDate);
		   
		   c.add(Calendar.DAY_OF_MONTH, -1);
		            
		   Date previous1Date=c.getTime();
		   String daybefore1=sdf1.format(previous1Date);
		   
		   c.add(Calendar.DAY_OF_MONTH, -1);
		   Date previous2Date=c.getTime();
		   String daybefore2=sdf1.format(previous2Date);
		   
		      str[0]=daybefore2;
		      str[1]=daybefore1;
		      str[2]=curDay;
		   
		  } catch (Exception e) {

		    e.printStackTrace();
		  }
		 
		  return str;
		 }
	
   
public String getbellcolor() throws Exception{
    
	   if(!verifyLogin(request)) {
		     return "login";
		    }
		            
		      Connection connection=null;
		      try {
		        
		        StringBuffer buffer=new StringBuffer();
		        connection=Connection_provider.getconnection();
		        IpdDAO ipdDAO=new JDBCIpdDAO(connection);
		        
		        String date=DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone());
		        date=date.substring(0,10);
		        String nextday=date+" 23:59:59";
		        ArrayList<Bed> doseremainders=ipdDAO.getAllDosageRemainders(date,nextday,loginInfo);
		        ipdForm.setDoseremainders(doseremainders);
		        
		        HashSet<String> colorcount=new HashSet<String>();
		        for(Bed bed:doseremainders) {
		        
		         String rowid="d"+""+bed.getConditionname()+""+bed.getId(); 
		         buffer.append(bed.getId()+"-"+bed.getColor()+"-"+rowid+"/");
		            if(bed.getColor().equals("Red")) {
		             
		              
		             String data=bed.getId()+"/"+bed.getColor();
		             colorcount.add(data);             
		            }
		             
		        }
		        
		        buffer.append(colorcount.size());
		        
		       response.setContentType("text/html");
		       response.setHeader("Cache-Control", "no-cache");
		       response.getWriter().write(buffer.toString());      
		  
		 } catch (Exception e) {

		    e.printStackTrace();
		 }   
		 finally {
		        connection.close();
		 }
     
    return null;
   }
 

   public String getclientdata() throws Exception {
	
	   Connection connection=null;
	   try {
		   
		   connection=Connection_provider.getconnection();
		   ClientDAO clientDAO=new JDBCClientDAO(connection);
		   String ipdid=request.getParameter("ipdid");
		   String clientid=request.getParameter("clientid");
		
		   Client client=clientDAO.getClientDetails(clientid);
		   
		   String fullname=client.getTitle()+" "+client.getFirstName()+" "+client.getLastName();
		   
		   String data=fullname+"~"+clientid+"~"+ipdid;
		   
		   response.setContentType("text/html");
	       response.setHeader("Cache-Control", "no-cache");
	       response.getWriter().write(data);   
		   
	   } catch (Exception e) {
		   e.printStackTrace();
	   }
	   	   
	   return null;
   }
   
   public String getclientstdchargedata() throws Exception {
	   
	  Connection connection=null;
	 
	  
	  try {
		  connection=Connection_provider.getconnection();
		  CompleteAptmDAO aptmDAO=new JDBCCompleteAptmDAO(connection);
		  ClientDAO clientDAO=new JDBCClientDAO(connection);
		  String wardid=request.getParameter("wardid"); 
		  String clientid=request.getParameter("clientid");
		  
		  Client client=clientDAO.getClientDetails(clientid);
		  
		  ArrayList<Master> standardChargesList=aptmDAO.getStandardCharges(wardid,client.getWhopay());
		  
		  StringBuffer buffer=new StringBuffer(); 
		  
		  for(Master master:standardChargesList){
			  
			  buffer.append("<input type='checkbox' class='scase' id='ch"+master.getId()+"' name='ch"+master.getId()+"' value='"+master.getId()+"'  />");
			  buffer.append(master.getName()+"<br>");
			 
		  }
		  
 		   response.setContentType("text/html");
	       response.setHeader("Cache-Control", "no-cache");
	       response.getWriter().write(buffer.toString());   
		  
		  
	  } catch (Exception e) {
		  e.printStackTrace();
	  }
	   
	   
	 return null;   
   }
  
   public String deleteipdvisit()throws Exception {
	   
	   Connection connection=null;
	   try {
		   connection=Connection_provider.getconnection();
		   IpdDAO ipdDAO=new JDBCIpdDAO(connection); 
		   String visitid=request.getParameter("visitid");
		   
		   int result=ipdDAO.deleteIpdVisitConsult(visitid);
		   

		   response.setContentType("text/html");
	       response.setHeader("Cache-Control", "no-cache");
	       response.getWriter().write("");  
		   
	} catch (Exception e) {

		e.printStackTrace();
	}
	finally {
		connection.close();
	}
	   
	   return null;
   }
   
   public String getallpriscajax() throws Exception {
	   
	   Connection connection=null;
	   
	   try {
		    String ipdid=request.getParameter("ipdid");
		    connection=Connection_provider.getconnection(); 
		    IpdDAO  ipdDAO=new JDBCIpdDAO(connection);    
		    ArrayList<Priscription> ipdPriscList=ipdDAO.getAJaxPriscription(ipdid);
		    
		    	StringBuffer buffer=new StringBuffer();
		        for(Priscription priscription:ipdPriscList){
		        	
		        	  if(priscription.getMdicinenametxt()!=null){
		        	
		        	  buffer.append("<tr class='prstotip'>");
		        	  buffer.append("<td>"+priscription.getMdicinenametxt()+"</td>");
		        	  buffer.append("<td>"+priscription.getDosage()+"</td>");
		        	  buffer.append("<td>");
		        	  
		        	   if(priscription.getDosesize()==3){
		        		   
		        		   if(!priscription.getDosevalue1().equals("0")){
		        			    if(priscription.isDos1()) {
		        			    	buffer.append("<input type='checkbox' name='dos1' id='dos1' checked='checked' onclick=toggleConfirmation("+priscription.getId()+",'dos1',"+priscription.isDos1()+")>");
		        			    }else {
		        			        buffer.append("<input type='checkbox' name='dos1' id='dos1' onclick=toggleConfirmation("+priscription.getId()+",'dos1',"+priscription.isDos1()+")>");
		        			    }
		        		   }else {
		        			   buffer.append("<input type='checkbox' name='dos1' id='dos1' onclick=toggleConfirmation("+priscription.getId()+",'dos1',"+priscription.isDos1()+") disabled='true'>");
		        		   }
		        		   if(!priscription.getDosevalue2().equals("0")){
		        			    if(priscription.isDos2()) {
		        			    	buffer.append("<input type='checkbox' name='dos2' id='dos2' checked='checked' onclick=toggleConfirmation("+priscription.getId()+",'dos2',"+priscription.isDos2()+")>");
		        			    }else {
		        			        buffer.append("<input type='checkbox' name='dos2' id='dos2' onclick=toggleConfirmation("+priscription.getId()+",'dos2',"+priscription.isDos2()+")>");
		        			    }
		        		   }else {
		        			   buffer.append("<input type='checkbox' name='dos2' id='dos2' onclick=toggleConfirmation("+priscription.getId()+",'dos2',"+priscription.isDos2()+") disabled='true'>");
		        		   }
		        		   if(!priscription.getDosevalue3().equals("0")){
		        			    if(priscription.isDos3()) {
		        			    	buffer.append("<input type='checkbox' name='dos3' id='dos3' checked='checked' onclick=toggleConfirmation("+priscription.getId()+",'dos3',"+priscription.isDos3()+")>");
		        			    }else {
		        			        buffer.append("<input type='checkbox' name='dos3' id='dos3' onclick=toggleConfirmation("+priscription.getId()+",'dos3',"+priscription.isDos3()+")>");
		        			    }
		        		   }else {
		        			   buffer.append("<input type='checkbox' name='dos3' id='dos3' onclick=toggleConfirmation("+priscription.getId()+",'dos3',"+priscription.isDos3()+") disabled='true'>");
		        		   }
		        		   
		     
		        	   }
		        	   if(priscription.getDosesize()==4){
		        		   
		        		   if(!priscription.getDosevalue1().equals("0")){
		        			    if(priscription.isDos1()) {
		        			    	buffer.append("<input type='checkbox' name='dos1' id='dos1' checked='checked' onclick=toggleConfirmation("+priscription.getId()+",'dos1',"+priscription.isDos1()+")>");
		        			    }else {
		        			        buffer.append("<input type='checkbox' name='dos1' id='dos1' onclick='toggleConfirmation("+priscription.getId()+",'dos1',"+priscription.isDos1()+")'>");
		        			    }
		        		   }else {
		        			   buffer.append("<input type='checkbox' name='dos1' id='dos1' onclick=toggleConfirmation("+priscription.getId()+",'dos1',"+priscription.isDos1()+") disabled='true'>");
		        		   }
		        		   if(!priscription.getDosevalue2().equals("0")){
		        			    if(priscription.isDos2()) {
		        			    	buffer.append("<input type='checkbox' name='dos2' id='dos2' checked='checked' onclick=toggleConfirmation("+priscription.getId()+",'dos2',"+priscription.isDos2()+")>");
		        			    }else {
		        			        buffer.append("<input type='checkbox' name='dos2' id='dos2' onclick=toggleConfirmation("+priscription.getId()+",'dos2',"+priscription.isDos2()+")>");
		        			    }
		        		   }else {
		        			   buffer.append("<input type='checkbox' name='dos2' id='dos2' onclick=toggleConfirmation("+priscription.getId()+",'dos2',"+priscription.isDos2()+") disabled='true'>");
		        		   }
		        		   if(!priscription.getDosevalue3().equals("0")){
		        			    if(priscription.isDos3()) {
		        			    	buffer.append("<input type='checkbox' name='dos3' id='dos3' checked='checked' onclick=toggleConfirmation("+priscription.getId()+",'dos3',"+priscription.isDos3()+")>");
		        			    }else {
		        			        buffer.append("<input type='checkbox' name='dos3' id='dos3' onclick=toggleConfirmation("+priscription.getId()+",'dos3',"+priscription.isDos3()+")>");
		        			    }
		        		   }else {
		        			   buffer.append("<input type='checkbox' name='dos3' id='dos3' onclick=toggleConfirmation("+priscription.getId()+",'dos3',"+priscription.isDos3()+") disabled='true'>");
		        		   }
		        		   if(!priscription.getDosevalue4().equals("0")){
		        			    if(priscription.isDos4()) {
		        			    	buffer.append("<input type='checkbox' name='dos4' id='dos4' checked='checked' onclick=toggleConfirmation("+priscription.getId()+",'dos4',"+priscription.isDos4()+")>");
		        			    }else {
		        			        buffer.append("<input type='checkbox' name='dos4' id='dos4' onclick=toggleConfirmation("+priscription.getId()+",'dos4',"+priscription.isDos4()+")>");
		        			    }
		        		   }else {
		        			   buffer.append("<input type='checkbox' name='dos4' id='dos4' onclick=toggleConfirmation("+priscription.getId()+",'dos4',"+priscription.isDos4()+") disabled='true'>");
		        		   }
		        	   } 
		        	  buffer.append("</td>");
		        	  buffer.append("<td>"+priscription.getDosenotes()+"</td>");
		        	  buffer.append("<td>"+priscription.getPrisctimename()+"</td>");
		        	  buffer.append("<td>"+priscription.getPriscindivisualremark()+"</td>");
		              buffer.append("</tr>"); 
		        	}   
		        }
		        		    
		       response.setContentType("text/html");
		       response.setHeader("Cache-Control", "no-cache");
		       response.getWriter().write(buffer.toString());  
		    
	   } catch (Exception e) {

		   e.printStackTrace();
	   }
	   finally{
		   connection.close();
	   }
	   
	   
	 
	   return null;
   }
   
   
   
public String getallnursingajax() throws Exception {
	   
	   Connection connection=null;
	   
	   try {
		    String ipdid=request.getParameter("ipdid");
		    connection=Connection_provider.getconnection(); 
		    IpdDAO  ipdDAO=new JDBCIpdDAO(connection);    
		    ArrayList<Master> ipdNursingList=ipdDAO.getAJaxNursing(ipdid);
		    
		    	StringBuffer buffer=new StringBuffer();
		        for(Master master:ipdNursingList){
		        	
		        	  if(master.getTaskname()!=null){
		        	
		        	  buffer.append("<tr class='prstotip'>");
		        	  buffer.append("<td>"+master.getTaskname()+"</td>");
		        	  buffer.append("<td>"+master.getFrequency()+"</td>");
		        	  buffer.append("<td>");
		        	   if(master.getDosesize()==3){
		        		   
		        		   if(!master.getDosevalue1().equals("0")){
		        			    if(master.isDos1()) {
		        			    	buffer.append("<input type='checkbox' name='dos1' id='dos1' checked='checked' onclick=togglenursingConfirm("+master.getId()+",'dos1',"+master.isDos1()+")>");
		        			    }else {
		        			        buffer.append("<input type='checkbox' name='dos1' id='dos1' onclick=togglenursingConfirm("+master.getId()+",'dos1',"+master.isDos1()+")>");
		        			    }
		        		   }else {
		        			   buffer.append("<input type='checkbox' name='dos1' id='dos1' onclick=togglenursingConfirm("+master.getId()+",'dos1',"+master.isDos1()+") disabled='true'>");
		        		   }
		        		   if(!master.getDosevalue2().equals("0")){
		        			    if(master.isDos2()) {
		        			    	buffer.append("<input type='checkbox' name='dos2' id='dos2' checked='checked' onclick=togglenursingConfirm("+master.getId()+",'dos2',"+master.isDos2()+")>");
		        			    }else {
		        			        buffer.append("<input type='checkbox' name='dos2' id='dos2' onclick=togglenursingConfirm("+master.getId()+",'dos2',"+master.isDos2()+")>");
		        			    }
		        		   }else {
		        			   buffer.append("<input type='checkbox' name='dos2' id='dos2' onclick=togglenursingConfirm("+master.getId()+",'dos2',"+master.isDos2()+") disabled='true'>");
		        		   }
		        		   if(!master.getDosevalue3().equals("0")){
		        			    if(master.isDos3()) {
		        			    	buffer.append("<input type='checkbox' name='dos3' id='dos3' checked='checked' onclick=togglenursingConfirm("+master.getId()+",'dos3',"+master.isDos3()+")>");
		        			    }else {
		        			        buffer.append("<input type='checkbox' name='dos3' id='dos3' onclick=togglenursingConfirm("+master.getId()+",'dos3',"+master.isDos3()+")>");
		        			    }
		        		   }else {
		        			   buffer.append("<input type='checkbox' name='dos3' id='dos3' onclick=togglenursingConfirm("+master.getId()+",'dos3',"+master.isDos3()+") disabled='true'>");
		        		   }
		        		   
		     
		        	   }
		        	   if(master.getDosesize()==4){
		        		   
		        		   if(!master.getDosevalue1().equals("0")){
		        			    if(master.isDos1()) {
		        			    	buffer.append("<input type='checkbox' name='dos1' id='dos1' checked='checked' onclick=togglenursingConfirm("+master.getId()+",'dos1',"+master.isDos1()+")>");
		        			    }else {
		        			        buffer.append("<input type='checkbox' name='dos1' id='dos1' onclick='togglenursingConfirm("+master.getId()+",'dos1',"+master.isDos1()+")'>");
		        			    }
		        		   }else {
		        			   buffer.append("<input type='checkbox' name='dos1' id='dos1' onclick=togglenursingConfirm("+master.getId()+",'dos1',"+master.isDos1()+") disabled='true'>");
		        		   }
		        		   if(!master.getDosevalue2().equals("0")){
		        			    if(master.isDos2()) {
		        			    	buffer.append("<input type='checkbox' name='dos2' id='dos2' checked='checked' onclick=togglenursingConfirm("+master.getId()+",'dos2',"+master.isDos2()+")>");
		        			    }else {
		        			        buffer.append("<input type='checkbox' name='dos2' id='dos2' onclick=togglenursingConfirm("+master.getId()+",'dos2',"+master.isDos2()+")>");
		        			    }
		        		   }else {
		        			   buffer.append("<input type='checkbox' name='dos2' id='dos2' onclick=togglenursingConfirm("+master.getId()+",'dos2',"+master.isDos2()+") disabled='true'>");
		        		   }
		        		   if(!master.getDosevalue3().equals("0")){
		        			    if(master.isDos3()) {
		        			    	buffer.append("<input type='checkbox' name='dos3' id='dos3' checked='checked' onclick=togglenursingConfirm("+master.getId()+",'dos3',"+master.isDos3()+")>");
		        			    }else {
		        			        buffer.append("<input type='checkbox' name='dos3' id='dos3' onclick=togglenursingConfirm("+master.getId()+",'dos3',"+master.isDos3()+")>");
		        			    }
		        		   }else {
		        			   buffer.append("<input type='checkbox' name='dos3' id='dos3' onclick=togglenursingConfirm("+master.getId()+",'dos3',"+master.isDos3()+") disabled='true'>");
		        		   }
		        		   if(!master.getDosevalue4().equals("0")){
		        			    if(master.isDos4()) {
		        			    	buffer.append("<input type='checkbox' name='dos4' id='dos4' checked='checked' onclick=togglenursingConfirm("+master.getId()+",'dos4',"+master.isDos4()+")>");
		        			    }else {
		        			        buffer.append("<input type='checkbox' name='dos4' id='dos4' onclick=togglenursingConfirm("+master.getId()+",'dos4',"+master.isDos4()+")>");
		        			    }
		        		   }else {
		        			   buffer.append("<input type='checkbox' name='dos4' id='dos4' onclick=togglenursingConfirm("+master.getId()+",'dos4',"+master.isDos4()+") disabled='true'>");
		        		   }
		        	   } 
		        	  buffer.append("</td>");
		        	 // buffer.append("<td>"+master.getNotes()+"</td>");
		        	  
		              buffer.append("</tr>"); 
		        	}   
		        }
		        		    
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


   public String getAjaxPerpatientdietary() throws Exception {
	   
	   Connection connection=null;
	   
	   try {
		    String ipdid=request.getParameter("ipdid");
		    connection=Connection_provider.getconnection(); 
		    StringBuffer buffer=new StringBuffer();
		    
		    DietaryDetailsDAO detailsDAO = new JDBCDietaryDetailsDAO(connection);
			//ArrayList<DietaryDetails> dietarylist = detailsDAO.getAllIpdDietplan();
			DietaryDetails dietaryDetails = detailsDAO.getIpdPerPatientDiet(ipdid);
				 //  10 nov 2017 dietary plan 
				 buffer.append("<tr>");
				 buffer.append("<td><span id='c"+dietaryDetails.getBedid()+"'><i class='fa fa-bell'></i></span> / "+dietaryDetails.getWardname()+" <br> "+dietaryDetails.getClientname()+" <br>"+dietaryDetails.getAge()+"</td>");
				 for(int i=1;i<=9;i++){
					 ArrayList<DietaryDetails> arrayList = detailsDAO.getSingleDietplanList(dietaryDetails.getParentid(),""+i);
					 buffer.append("<td>");
					 	int x=0;
					 	for (DietaryDetails dietaryDetails2 : arrayList) {
					 		if(x==0){
					 			if(dietaryDetails2.getExecuted().equals("0")){
					 				buffer.append("<input type='checkbox' onclick=updateDietaryGivenStatus('"+dietaryDetails.getParentid()+"','"+i+"',this.checked)>"+dietaryDetails2.getSubcategory()+"<br>");
					 			}else{
					 				buffer.append("<input type='checkbox' checked='checked' onclick=updateDietaryGivenStatus('"+dietaryDetails.getParentid()+"','"+i+"',this.checked)>"+dietaryDetails2.getSubcategory()+"<br>");	
					 			}
					 			
					 		}else{
					 			buffer.append(""+dietaryDetails2.getSubcategory()+"<br>");
					 		}
					 		x++;
					 		
					 	}
					 buffer.append("</td>");
					 /*if(i==0){
						 DietaryDetails details = detailsDAO.getSingleDietplan(dietaryDetails.getParentid(),"Breakfast");
						 if(details.getSubcategory()!=null){
							 buffer.append("<td><center>"+details.getSubcategory()+"</center></td>");
						 }else{
							 buffer.append("<td></td>");
						 }
					 }else if(i==1){
						 DietaryDetails details = detailsDAO.getSingleDietplan(dietaryDetails.getParentid(),"Midmorning Snack");
						 if(details.getSubcategory()!=null){
							 buffer.append("<td><center>"+details.getSubcategory()+"</center></td>");
						 }else{
							 buffer.append("<td></td>");
						 }
					 }else if(i==2){
						 DietaryDetails details = detailsDAO.getSingleDietplan(dietaryDetails.getParentid(),"Lunch");
						 if(details.getSubcategory()!=null){
							 buffer.append("<td><center>"+details.getSubcategory()+"</center></td>");
						 }else{
							 buffer.append("<td></td>");
						 }
					}else if(i==3){
						 DietaryDetails details = detailsDAO.getSingleDietplan(dietaryDetails.getParentid(),"Midafternoon Snack");
						 if(details.getSubcategory()!=null){
							 buffer.append("<td><center>"+details.getSubcategory()+"</center></td>");
						 }else{
							 buffer.append("<td></td>");
						 }
					 }else if(i==4){
						 DietaryDetails details = detailsDAO.getSingleDietplan(dietaryDetails.getParentid(),"Midevening Snack");
						 if(details.getSubcategory()!=null){
							 buffer.append("<td><center>"+details.getSubcategory()+"</center></td>");
						 }else{
							 buffer.append("<td></td>");
						 }
					 }else if(i==5){
						 DietaryDetails details = detailsDAO.getSingleDietplan(dietaryDetails.getParentid(),"Dinner");
						 if(details.getSubcategory()!=null){
							 buffer.append("<td><center>"+details.getSubcategory()+"</center></td>");
						 }else{
							 buffer.append("<td></td>");
						 }
					 }*/
					 
				 }
				 buffer.append("</tr>");
			
		        		    
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
   
   public String savePkgData() throws Exception{
		Connection connection= null;
		try{
			String fromdate = request.getParameter("pkgfromdate");
			String todate = request.getParameter("pkgtodate");
			String pkgid = request.getParameter("packagename");
			String newpackagename = request.getParameter("newpackagename");
			String newpackageamt = request.getParameter("newpackageamt");
			connection = Connection_provider.getconnection();
			PackageMasterDAO masterDAO = new JDBCPackageMasterDAO(connection);
		
			String hrs= request.getParameter("hour");
			String min= request.getParameter("minute");
			if(hrs==null){
				hrs="00";
			}else if(hrs.equals("")){
				hrs="00";
			}
			if(min==null){
				min="00";
			}else if(min.equals("")){
				min="00";
			}
			
			DateFormat dateFormat = new SimpleDateFormat("HH:mm");
		      Calendar cal = Calendar.getInstance();
		      String time = dateFormat.format(cal.getTime());  
			time=time+":00";
			todate= todate +" "+hrs+":"+min+":00";
			fromdate= fromdate+" "+hrs+":"+min+":00";
			IpdDAO ipdDAO = new JDBCIpdDAO(connection);
			boolean flag =false;
			if(newpackagename==null){
				flag =false;
			}else if(newpackagename.equals("")){
				flag =false;
			}else{
				flag =true;
			}
			if(!flag){
				String  ipdid = ipdForm.getIpdid();
				String  clientid = ipdForm.getClientid();
				if(ipdid==null){
					ipdid="0";
				}
				if(ipdid.equals("")||ipdid.equals("0")){
					ipdid=ipdDAO.getLAstIpdIdByClient(clientid);
				}
				String fromdate1[]= fromdate.split(" ");
				String todate1[]= todate.split(" ");
				
				fromdate1[0] = DateTimeUtils.getCommencingDate1(fromdate1[0]);
				todate1[0] = DateTimeUtils.getCommencingDate1(todate1[0]);
				fromdate= fromdate1[0]+" "+fromdate1[1];
				todate= todate1[0]+" "+todate1[1];
				PackageMaster master = masterDAO.getPerticularPackage(pkgid);
				ArrayList<PackageMaster> arrayList = masterDAO.getPackageFromChild(Integer.parseInt(pkgid));
				int result = ipdDAO.saveparentpackagedata(fromdate,todate,master,clientid,ipdid,newpackageamt);
				for (PackageMaster packageMaster : arrayList) {
					String pkgper="", tpcode="";
					if(master.isTp()){
						tpcode=packageMaster.getCode();
								/*request.getParameter("tpcode"+packageMaster.getChild_id()+"");*/
					}else{
						 pkgper = request.getParameter("pkgper"+packageMaster.getChild_id()+"");
					}
					String pkgamt = request.getParameter("pkgamt"+packageMaster.getChild_id()+"");
					
					packageMaster.setPercentage(pkgper);
					packageMaster.setCal_amount(pkgamt);
					packageMaster.setCode(tpcode);
					int result1 = ipdDAO.savechildpackagedata(packageMaster,result);
				}
			}else{
				String  ipdid = ipdForm.getIpdid();
				String  clientid = ipdForm.getClientid();
				fromdate = DateTimeUtils.getCommencingDate1(fromdate);
				todate = DateTimeUtils.getCommencingDate1(todate);
				//PackageMaster master = masterDAO.getPerticularPackage(pkgid);
				ArrayList<PackageMaster> arrayList = masterDAO.getPackageFromChild(Integer.parseInt(pkgid));
				
				int res = masterDAO.storePackageParentData(newpackagename, newpackageamt,"0","0","0","","");
				for (PackageMaster packageMaster : arrayList) {
					String pkgper = request.getParameter("pkgper"+packageMaster.getChild_id()+"");
					String pkgamt = request.getParameter("pkgamt"+packageMaster.getChild_id()+"");
					packageMaster.setPercentage(pkgper);
					packageMaster.setCal_amount(pkgamt);
					int res2 = masterDAO.storePackageChildData(packageMaster, res);
				}
				PackageMaster master = masterDAO.getPerticularPackage(""+res);
				int result = ipdDAO.saveparentpackagedata(fromdate,todate,master,clientid,ipdid,newpackageamt);
				for (PackageMaster packageMaster : arrayList) {
					String pkgper = request.getParameter("pkgper"+packageMaster.getChild_id()+"");
					String pkgamt = request.getParameter("pkgamt"+packageMaster.getChild_id()+"");
					packageMaster.setPercentage(pkgper);
					packageMaster.setCal_amount(pkgamt);
					int result1 = ipdDAO.savechildpackagedata(packageMaster,result);
				}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			connection.close();
		}
		   return "pkgsaved";
	   }
   public String getaprovedinvestigation() throws Exception{
	   Connection connection = null;
	   try {
		   connection = Connection_provider.getconnection();
		   String ipdid = request.getParameter("id");
		   InvestigationDAO investigationDAO = new JDBCInvestigationDAO(connection);
		   ArrayList<Investigation> investigationlist = investigationDAO.getAprovedInvestigation(ipdid);
		   StringBuilder  builder = new StringBuilder();
		   for (Investigation investigation : investigationlist) {
			   builder.append("<tr>");
			   builder.append("<td>"+investigation.getUserid()+"/"+investigation.getDate()+"</td>");
			   builder.append("<td>"+investigation.getInvsttype()+"</td>");
			   builder.append("<td>"+investigation.getComplete_date()+"</td>");
			   builder.append("</tr>"); 
		   }
		   int res = investigationDAO.updateInvestiSeenStatus(ipdid);
		  	response.setContentType("text/html");
		    response.setHeader("Cache-Control", "no-cache");
		    response.getWriter().write(builder.toString());
	   } catch (Exception e) {
		   e.printStackTrace();
	   }finally {
		connection.close();
	}
	   return null;
   }
   
   
   
   public String getajaxperpatientinvestigation() throws Exception{
	   String ipdid = request.getParameter("ipdid");
	
	   //  03 oct 2017 investigation details in ipd dashborad
	   Connection connection = null;
	   try {
		   connection = Connection_provider.getconnection();
			InvestigationDAO investigationDAO = new JDBCInvestigationDAO(connection);
			
			ArrayList<Investigation>invstigationList = investigationDAO.getIPDInvestList(ipdid);
			
			StringBuffer str = new StringBuffer();
			int i=0;
			
			for(Investigation investigation : invstigationList){
				
				str.append("<tr>");
				str.append("<td>"+(++i)+"</td>");	
				str.append("<td>"+investigation.getInvsttype()+"</td>");	
				str.append("<td>"+investigation.getDate()+"</td>");
				if(investigation.getInvstreporttype().equals("Numerical")){
					str.append("<td><a href='#' onclick='printinvstigationrecord("+investigation.getId()+",0)' title='Print Investigation' class='editpricon'><i class='fa fa-print'></i></a></td>");
					
				}else{
					str.append("<td><a href='#' onclick='printinvstigationrecord("+investigation.getId()+",1)' title='Print Investigation' class='editpricon'><i class='fa fa-print'></i></a></td>");
				}
				
				if(investigation.getPacs()!=0){
					str.append("<td><a href='#' onclick='viewpacsreport("+investigation.getId()+")' title='Pacs Report' class='editpricon'><i class='fa fa-object-ungroup aria-hidden='true''></i></a></td>");
				}else{
					str.append("<td></td>");
				}
				
				str.append("</tr>");
				
			}
			
			response.setContentType("text/html");
			response.setHeader("Cache-Control", "no-cache");
			response.getWriter().write(""+str.toString()+""); 
	   } catch (Exception e) {
		   e.printStackTrace();
	   }finally {
		connection.close();
	}
	   return null;
   }
   
   
   public String getvitalmasteranddata() throws Exception  {
	   
	    Connection connection=null;
		try {
			connection=Connection_provider.getconnection();
			ClientDAO clientDAO= new JDBCClientDAO(connection);
			String ipdid =request.getParameter("ipdid");
			String clientid= request.getParameter("clientid");
			Client client=clientDAO.getClientDetails(clientid);
			String fullname= client.getTitle()+" "+client.getFirstName()+" "+client.getLastName();
			SimpleDateFormat dateFormat= new SimpleDateFormat("HH:mm");
			Calendar calendar=Calendar.getInstance();
			String time=dateFormat.format(calendar.getTime());
			
			response.setContentType("text/html");
			response.setHeader("Cache-Control", "no-cache");
			response.getWriter().write(fullname+"~"+time); 
			
		} catch (Exception e) {
	
			e.printStackTrace();
		}finally{
			
			connection.close();
		}
		   
		   
	    return null;
   }
   
   public String updatedietarygivenstatus() throws Exception  {
	   
	    Connection connection=null;
		try {
			connection=Connection_provider.getconnection();
			DietaryDetailsDAO dietaryDetailsDAO = new JDBCDietaryDetailsDAO(connection);
			String parentid =request.getParameter("parentid");
			String dietplan= request.getParameter("dietplan");
			String val= request.getParameter("val");
			SimpleDateFormat dateFormat= new SimpleDateFormat("yyyy-MM-dd HH:mm");
			Calendar calendar=Calendar.getInstance();
			String time=dateFormat.format(calendar.getTime());
			String userid = loginInfo.getUserId();
			if(val.equals("true")){
				val="1";
			}else{
				val="0";
			}
			int result = dietaryDetailsDAO.updatedietarygivenstatus(parentid,dietplan,val,userid,time);
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
	
   public String saveclientfinding() throws Exception{
	   
	   Connection connection=null;
	   try {
		   connection=Connection_provider.getconnection();
		   IpdDAO ipdDAO= new JDBCIpdDAO(connection);
		   String finding= request.getParameter("finding");
		   String ipdid= request.getParameter("ipdid");
		   String clientid= request.getParameter("clientid");
		   String vitalid= request.getParameter("vitalid");
		   String time=request.getParameter("time");
		   String date= DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone()).split(" ")[0];
		   String dateTime= DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone());
		   Master master =new Master();
		   master.setFinding(finding);
		   master.setIpdid(ipdid);
		   master.setClientid(clientid);
		   master.setVital_master_id(vitalid);
		   master.setDate(date);
		   master.setUserid(loginInfo.getUserId());
		   master.setDatetime(dateTime);
		  String isfromclinical=DateTimeUtils.isNull(request.getParameter("isfromclinical"));
		  
		   
		   int res= ipdDAO.saveClientVitalData(master, finding, time);
		   
			
			response.setContentType("text/html");
			response.setHeader("Cache-Control", "no-cache");
			response.getWriter().write(""); 
		   		   
		
	   } catch (Exception e) {
		
		   e.printStackTrace();
	   }
	   finally {
		   connection.close();
	   }
	   return null;
   }
   public String callsendsms() throws Exception{
	    
	   if(!verifyLogin(request)) {
		     return "login";
		    }
		            
		      Connection connection=null;
		      try {
		        
		        //StringBuffer buffer=new StringBuffer();
		        connection=Connection_provider.getconnection();
		       // IpdDAO ipdDAO=new JDBCIpdDAO(connection);
		        BedDao bedDao = new JDBCBedDao(connection);
		        ClientDAO clientDAO = new JDBCClientDAO(connection);
		        DiaryManagementDAO diaryManagementDAO = new JDBCDiaryManagentDAO(connection);
		        String date=DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone());
		        String[] data = date.split(" ");
		        /*if(data[1].equals("09:00:00")){*/
		        String[] data2 = data[1].split(":");
		        String time = data2[0]+":"+data2[1];
		        if(time.equals("09:00")){
		        	//For some time comment 24 NOV 2017
		        	/*ArrayList<Bed> arrayList = bedDao.getAllIpdActivePatients();
		        	for (Bed bed : arrayList) {
		        		double balance = 0;
		        		Client client = clientDAO.getClientDetails(bed.getClientid());
						if(client.getWhopay().equals(Constants.PAY_BY_CLIENT)){
							double debit = diaryManagementDAO.getClientDebitTotal(bed.getClientid());
							double payment = diaryManagementDAO.getClientPayment(bed.getClientid());
							balance = debit - payment;
							if(balance>0){
								if(client.getMobNo()!=null){
									if(!client.getMobNo().equals("")){
										String name = client.getTitle()+" "+client.getFirstName()+" "+client.getLastName();
										SmsService s = new SmsService();
										String message = ""+name+", your remaining balance is "+balance+" from "+loginInfo.getClinicName()+" Billing Department";
										s.sendSms(message, client.getMobNo(), loginInfo, new EmailLetterLog());
									}
								}
							}
						}
					}*/
		        }
		       response.setContentType("text/html");
		       response.setHeader("Cache-Control", "no-cache");
		       response.getWriter().write("");      
		  
		 } catch (Exception e) {

		    e.printStackTrace();
		 }   
		 finally {
		        connection.close();
		 }
     
    return null;
   }
   
   public String savenursenoteajax() throws Exception{
	    
	   if(!verifyLogin(request)) {
		     return "login";
		    }
	   Connection connection=null;
	   try {
			StringBuffer buffer = new StringBuffer();
			connection = Connection_provider.getconnection();
			IpdDAO ipdDAO = new JDBCIpdDAO(connection);
			String nursemsg = request.getParameter("nursemsg");
			String ipdclientid = request.getParameter("ipdclientid");
			String ipdaddmissionid = request.getParameter("ipdaddmissionid");
			String userid = loginInfo.getUserId();
			String todate = DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone());
			int res = ipdDAO.savenursenoteajax(nursemsg,ipdclientid,ipdaddmissionid,userid,todate);
			ArrayList<Bed> arrayList = ipdDAO.getAllNurseNotes(ipdaddmissionid);
			buffer.append("<ul class='chats'>");
			for (Bed bed : arrayList) {
				buffer.append("<li class=''>");
				buffer.append("<div class='media'>");
				buffer.append("<p class='media-heading'><a role='button' tabindex='0' class='name' style='color: #16a085;'>"+bed.getClientname()+"</a><br><span class='datetime'>"+bed.getDatetime()+"</span></p>");
				buffer.append("<span class='body' style='font-size: 10px;text-align: justify;'>"+bed.getNursenotes()+"</span>");
				buffer.append("</div>");
				buffer.append("</li>");
			}
			buffer.append("</ul>");
			response.setContentType("text/html");
			response.setHeader("Cache-Control", "no-cache");
			response.getWriter().write(""+buffer.toString()+"");
		 } catch (Exception e) {

		    e.printStackTrace();
		 }   
		 finally {
		        connection.close();
		 }
     
    return null;
   }
   public String getnursenotesajax() throws Exception{
	    
	   if(!verifyLogin(request)) {
		     return "login";
		    }
	   Connection connection=null;
	   try {
			StringBuffer buffer = new StringBuffer();
			connection = Connection_provider.getconnection();
			IpdDAO ipdDAO = new JDBCIpdDAO(connection);
			String ipdaddmissionid = request.getParameter("admissionid");
			ArrayList<Bed> arrayList = ipdDAO.getAllNurseNotes(ipdaddmissionid);
			  
			buffer.append("<ul class='chats'>");
			for (Bed bed : arrayList) {
				buffer.append("<li class=''>");
				buffer.append("<div class='media'>");
				buffer.append("<p class='media-heading'><a role='button' tabindex='0' class='name' style='color: #16a085;'>"+bed.getClientname()+"</a><br><span class='datetime'>"+bed.getDatetime()+"</span></p>");
				buffer.append("<span class='body' style='font-size: 10px;text-align: justify;'>"+bed.getNursenotes()+"</span>");
				buffer.append("</div>");
				buffer.append("</li>");
			}
			buffer.append("</ul>");
			response.setContentType("text/html");
			response.setHeader("Cache-Control", "no-cache");
			response.getWriter().write(""+buffer.toString()+"");
		 } catch (Exception e) {

		    e.printStackTrace();
		 }   
		 finally {
		        connection.close();
		 }
     
    return null;
   }
   
   
 //Adarsh (Nursing Care)
   public String nursingcare() throws Exception{
	   if(!verifyLogin(request)){
			return "login";
		}
    Connection connection = null;
    try {
      connection = Connection_provider.getconnection();
      IpdDAO ipdDAO = new JDBCIpdDAO(connection);
  String ipdclientid= request.getParameter("ipdclientid");
  ipdForm.setClientid(ipdclientid);
  String ipdid = request.getParameter("ipdid");
  ipdForm.setIpdid(ipdid);
  ArrayList<Ipd>nursingdiagnosislist = ipdDAO.getallnursingdiagnosislist();
  ipdForm.setNursingdiagnosislist(nursingdiagnosislist);
  ArrayList<Ipd>rationalelist = ipdDAO.getallrationalelist();
  ipdForm.setRationalelist(rationalelist);
  
 } catch (Exception e) {
  e.printStackTrace();
 }finally{
		
		connection.close();
	}
 return "nursingcare";
    
   }
   public String savenursingplan() throws Exception{
	    
	    Connection connection = null;
	    try {
	    	String Date="";
	    	   DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
			   Calendar cal = Calendar.getInstance();
			 
			   Date = dateFormat.format(cal.getTime());
	     connection = Connection_provider.getconnection();
	     ClientDAO clientDAO = new JDBCClientDAO(connection);
	     BedDao bedDao=new JDBCBedDao(connection);
	     IpdDAO ipdDAO = new JDBCIpdDAO(connection);
	     Ipd ipd = new Ipd();
	     ipd.setId(ipdForm.getId());
	     ipd.setSubjectivecare(ipdForm.getSubjectivecare());
	     ipd.setObjectivecare(ipdForm.getObjectivecare());
	     ipd.setDiagnosiscare(ipdForm.getDiagnosiscare());
	     ipd.setInferencecare(ipdForm.getInferencecare());
	     ipd.setPlanningcare(ipdForm.getPlanningcare());
	     ipd.setInterventioncare(ipdForm.getInterventioncare());
	     ipd.setRationalecare(ipdForm.getRationalecare());
	     ipd.setEvaluationcare(ipdForm.getEvaluationcare());
	     ipd.setClientid(ipdForm.getClientid());
	     ipd.setIpdid(ipdForm.getIpdid());
	     ipd.setNursing_date(Date);
	     int result = ipdDAO.addnursingcareplan(ipd);
	     
	     Ipd ipd2 = ipdDAO.printnursingcareplan(result);
	     ipdForm.setSubjectivecare(ipd2.getSubjectivecare());
	     ipdForm.setObjectivecare(ipd2.getObjectivecare());
	     ipdForm.setDiagnosiscare(ipd2.getDiagnosiscare());
	     ipdForm.setInferencecare(ipd2.getInferencecare());
	     ipdForm.setPlanningcare(ipd2.getPlanningcare());
	     ipdForm.setInterventioncare(ipd2.getInterventioncare());
	     ipdForm.setRationalecare(ipd2.getRationalecare());
	     ipdForm.setEvaluationcare(ipd2.getEvaluationcare());
	     ipdForm.setClientid(ipd2.getClientid());
	     ipdForm.setIpdid(ipd2.getIpdid());
	     
	     ClinicDAO clinicDAO = new JDBCClinicDAO(connection);
	     AccountsDAO accountsDAO = new JDBCAccountsDAO(connection);
	     ArrayList<Clinic> locationAdressList = accountsDAO.getLocationAddress(loginInfo.getClinicid());
	     Clinic clinic = clinicDAO.getCliniclistDetails(loginInfo.getClinicid());
	     ipdForm.setClinicName(clinic.getClinicName());
	     ipdForm.setClinicOwner(clinic.getClinicOwner());
	     ipdForm.setOwner_qualification(clinic.getOwner_qualification());
	     ipdForm.setLocationAdressList(locationAdressList);
	     ipdForm.setAddress(clinic.getAddress());
	     ipdForm.setLandLine(clinic.getLandLine());
	     ipdForm.setClinicemail(clinic.getEmail());
	     ipdForm.setWebsiteUrl(clinic.getWebsiteUrl());
	     ipdForm.setClinicLogo(clinic.getUserImageFileName());
	     ipdForm.setDate(Date);
	     
	     Bed bed = bedDao.getEditIpdData(ipdForm.getIpdid());
	     Client client = clientDAO.getClientDetails(ipdForm.getClientid());
	     String fullname = client.getTitle() + " " + client.getFirstName() + " "+client.getMiddlename()+" " + client.getLastName();
	     ipdForm.setClient(fullname);
	     ipdForm.setRegno(client.getAbrivationid());
	     String whopay=client.getWhopay();
	     ipdForm.setAbrivationid(client.getAbrivationid());
	     ipdForm.setPatientIdAbrivation(client.getPatientIdAbrivation());
	      if(Integer.parseInt(bed.getIpdseqno())>0){
	       ipdForm.setIpdseqno(bed.getIpdseqno());
	      }else{
	       ipdForm.setIpdseqno(ipdForm.getIpdid());
	      }

	     if(whopay==null){
	      whopay="";
	     }
	     if(!whopay.equals("Client")){
	      ThirdPartyDAO thirdPartyDAO = new JDBCThirdPartyDAO(connection);
	      ThirdParty thirdParty = thirdPartyDAO.getThirdPartyDetails(bed.getTpid());
	      ipdForm.setThirdParty(thirdParty.getCompanyName());
	     }else{
	      ipdForm.setThirdParty("Self");
	     }
	     
	     /*String numcount=ipdDAO.getNumofAdmissionCount(ipdForm.getClientid());
	        ipdForm.setNum_admission(numcount);*/
	     String agegender="";
	     String dob = client.getDob();
	     String age = DateTimeUtils.getAge(client.getDob());
	     if(Integer.parseInt(age)<2){
	      if(Integer.parseInt(age)<1){
	       String monthdays= DateTimeUtils.getMonthandDays(client.getDob());
	       agegender=monthdays+" / "+client.getGender();
	      }else{
	       String monthdays= DateTimeUtils.getMonthandDays(client.getDob());
	       agegender= age + " Years" + " "+monthdays+" / "+client.getGender();
	      }
	     } else {
	      agegender = age + "Years" + " / " + client.getGender(); 
	     }
	     
	     ipdForm.setAgegender(agegender);
	     String payby = client.getWhopay();
	     if(client.getWhopay().equals("Self")){
	      payby = "Client";
	     }
	     TreatmentEpisodeDAO treatmentEpisodeDAO = new JDBCTreatmentEpisode(connection);
	     /*ClinicDAO clinicDAO=new JDBCClinicDAO(connection);*/
	     ArrayList<TreatmentEpisode> treatmentEpisodeList = treatmentEpisodeDAO.getIpdTreatmentEpisodeList(ipdForm.getClientid(), payby);
	     ipdForm.setTreatmentEpisodeList(treatmentEpisodeList);
	     
	     ipdForm.setTreatmentEpisode(bed.getTreatmentepisodeid());
	     String admissiondate = bed.getAdmissiondate();
	     String[] data = admissiondate.split(" ");
	     String data2 = DateTimeUtils.getCommencingDate1(data[0]);
	     String data3 = data2 +" "+ data[1];
	     //ipdForm.setAdmissiondate(bed.getAdmissiondate());
	     ipdForm.setAdmissiondate(data3);
	     
	    
	     String wardname=ipdDAO.getIpdWardName(bed.getWardid());
	     String bedname = ipdDAO.getIpdBedName(bed.getBedid());
	     
	     

	     ipdForm.setWardid(wardname);
	     ipdForm.setBedid(bedname);
	    
	     
	     
	       ipdForm.setMlccase(bed.getMlccase());
	       if(bed.getMlccase().equals("1")){
	        
	       }
	 } catch (Exception e) {
	  e.printStackTrace();
	 }finally{
	  connection.close();
	 }
	 return "printnursingplan";
	 
	   }
   public String getplanningdetails() throws Exception{
	    Connection connection = null;
	    try {
	     
	     String id = request.getParameter("planning");
	     
	     
	  connection = Connection_provider.getconnection();
	  IpdDAO ipdDAO = new JDBCIpdDAO(connection);
	  ArrayList<Ipd>list = ipdDAO.getplanninglist(id);
	  
	   StringBuffer buffer=new StringBuffer();
	   buffer.append("<select class='form-control showToolTip chosen' onchange =' selectIntervention(this.value)' name='planning' id='planning'>");
	      buffer.append("<option value='0'>Select Planning</option>");
	     for(Ipd ipd: list){
	         buffer.append("<option value ='"+ipd.getId()+"'>"+ipd.getPlanningname()+"</option>");
	        }
	        buffer.append("</select>");
	        response.setContentType("text/html");
	        response.setHeader("Cache-Control","no-cache");
	        response.getWriter().write(buffer.toString());
	        
	 } catch (Exception e) {
	  e.printStackTrace();
	 }finally{
			
			connection.close();
		}
	 return null;
	    
	   }
   public String getdiagnosisdetails() throws Exception{
	    Connection connection = null;
	    try {
	  String id = request.getParameter("diagnosiscare");
	  connection = Connection_provider.getconnection();
	  IpdDAO ipdDAO = new JDBCIpdDAO(connection);
	  Ipd ipd = new Ipd();
	  ipd.setId(Integer.parseInt(id));
	  String diagnosiscare= ipdDAO.diagnosisnoteslist(ipd);
	   response.setContentType("text/html");
	      response.setHeader("Cache-Control", "no-cache");
	      response.getWriter().write(""+diagnosiscare+"");
	 } catch (Exception e) {
	  e.printStackTrace();
	 }finally{
			
			connection.close();
		}
	 return null;
	   }
   public String getinterventiondetails() throws Exception{
	    Connection connection = null;
	    try {
	  String id =request.getParameter("intervention");
	  connection = Connection_provider.getconnection();
	  IpdDAO ipdDAO = new JDBCIpdDAO(connection);
	  ArrayList<Ipd>list = ipdDAO.getAllinterventionlist(id);
	   StringBuffer buffer=new StringBuffer();
	  
	   /*buffer.append("<label>Select Intervention</label>"); */    
	      buffer.append("<select id='interventionid' class='form-control chosen' onchange =' selectinterventionnotes(this.value)' name='interventionname' >"); 
	      buffer.append("<option value='0'>Select Intervention</option>");
	      for(Ipd ipd: list){
	         buffer.append("<option value ='"+ipd.getId()+"'>"+ipd.getIntervention_name()+"</option>");
	        }
	      buffer.append("</select>");
	        response.setContentType("text/html");
	        response.setHeader("Cache-Control","no-cache");
	        response.getWriter().write(buffer.toString());
	 } catch (Exception e) {
	  e.printStackTrace();
	 }finally{
			
			connection.close();
		}
	 return null;
	 
	   }
   public String getplanningnotesdetails() throws Exception{
	    Connection connection = null;
	    try {
	  String id = request.getParameter("planningcare");
	  connection = Connection_provider.getconnection();
	  IpdDAO ipdDAO = new JDBCIpdDAO(connection);
	  Ipd ipd = new Ipd();
	  ipd.setId(Integer.parseInt(id));
	  String planningcare = ipdDAO.getplanningnoteslist(ipd);
	   response.setContentType("text/html");
	      response.setHeader("Cache-Control", "no-cache");
	      response.getWriter().write(""+planningcare+"");
	 } catch (Exception e) {
	  e.printStackTrace();
	 }finally{
			
			connection.close();
		}
	 return null;
	    
	   }
   public String getinterventionnotesdetails() throws Exception{
	    Connection connection = null;
	    try {
	  String id = request.getParameter("interventioncare");
	  connection = Connection_provider.getconnection();
	  IpdDAO ipdDAO = new JDBCIpdDAO(connection);
	  Ipd ipd = new Ipd();
	  ipd.setId(Integer.parseInt(id));
	  String interventioncare = ipdDAO.getinterventionnoteslist(ipd);
	   response.setContentType("text/html");
	      response.setHeader("Cache-Control", "no-cache");
	      response.getWriter().write(""+interventioncare+"");
	 } catch (Exception e) {
	  e.printStackTrace();
	 }finally{
			
			connection.close();
		}
	 return null;
	    
	   }
	   public String getrationalenotesdetails() throws Exception{
	    Connection connection = null;
	    try {
	     String id = request.getParameter("rationalecare");
	   connection = Connection_provider.getconnection();
	   IpdDAO ipdDAO = new JDBCIpdDAO(connection);
	   Ipd ipd = new Ipd();
	   ipd.setId(Integer.parseInt(id));
	   String rationalecare = ipdDAO.getrationalecarenoteslist(ipd);
	   response.setContentType("text/html");
	       response.setHeader("Cache-Control", "no-cache");
	       response.getWriter().write(""+rationalecare+"");
	 } catch (Exception e) {
	  e.printStackTrace();
	 }finally{
			
			connection.close();
		}
	 return null;
	    
	   }
	
	
public String pkgdtailsapplied(){
	String parentid= request.getParameter("id");
	try {
		Connection connection= Connection_provider.getconnection();
		IpdDAO ipdDAO= new JDBCIpdDAO(connection);
		ArrayList<PackageMaster> list= ipdDAO.getappliedpakgeList(parentid);
		StringBuffer buffer= new StringBuffer();
		int i=0,amt=0;
		Master master1= ipdDAO.getParentPackageAppliedData(parentid);
		for(PackageMaster master:list){
			buffer.append(" <tr>  ");
			buffer.append("<input type='hidden' name='pkglist["+i+"].id' value='"+master.getId()+"'>");
			buffer.append("<td>"+(i+1)+"</td>");
			buffer.append(" <td><input type='text' name='pkglist["+i+"].name' value='"+master.getName()+"' class='form-control'> </td> ");
			buffer.append(" <td><input type='number' name='pkglist["+i+"].amount' value='"+master.getAmount()+"' class='form-control lock' onkeyup='addTotalPkgamt()'> </td> ");
			buffer.append(" </tr>  ");
			if(master.getAmount()==null){
				master.setAmount("0");
			}else if(master.getAmount().equals("")){
				master.setAmount("0");
			}
			
			amt=amt+Integer.parseInt(master.getAmount());
			i=i+1;
		}
		buffer.append("!@#");
		buffer.append(""+amt);
		buffer.append("!@#");
		buffer.append(master1.getFdate());
		buffer.append("!@#");
		buffer.append(master1.getTdate());
		
		response.setContentType("text/html");
	    response.setHeader("Cache-Control", "no-cache");
		response.getWriter().write(""+buffer.toString()); 
	} catch (Exception e) {
		e.printStackTrace();
	}
	
	return null;
}	

public String savepkgDataedit(){
	
	String statementred="";
	try {
		Connection connection= Connection_provider.getconnection();
		IpdDAO ipdDAO= new JDBCIpdDAO(connection);
		String parentid= request.getParameter("appliedpkgname");
		String amount=request.getParameter("amtpkgedit");
		String fromdate= request.getParameter("fdt1");
		String todate= request.getParameter("tdt1");
		String fromhr= request.getParameter("hrpkg1");
		String frommin= request.getParameter("mntpkg1");
		String tohr= request.getParameter("hrpkg");
		String tomin= request.getParameter("mntpkg");
		statementred=request.getParameter("statementred");
		if(statementred==null){
			statementred="";
		}
		CompleteAptmDAO completeAptmDAO=new JDBCCompleteAptmDAO(connection);
		fromdate= DateTimeUtils.getCommencingDate1(fromdate)+" "+fromhr+":"+frommin+":00";
		todate= DateTimeUtils.getCommencingDate1(todate)+" "+tohr+":"+tomin+":00";
		
		
		for(PackageMaster packageMaster: ipdForm.getPkglist()){
			int i= packageMaster.getId();
			int x=ipdDAO.updatePatientpkg(packageMaster.getId(), packageMaster.getAmount(), packageMaster.getName());
//			System.out.println(""+i+""+packageMaster.getName());
		}
		if(ipdForm.getPkglist().size()!=0){
			String ipdid= request.getParameter("hidenipdid");
			CompleteAppointment dates = completeAptmDAO.getPackageDtaes(parentid);
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String mastercharge= ipdDAO.getMasterchargeAss(fromdate,todate,ipdid,parentid);
			int res=ipdDAO.updatePkgInAssessment(fromdate, todate, ipdid, parentid,mastercharge);
	        Date orignalfdate = sdf.parse(dates.getPkgfdate());
	        Date orignaltodate = sdf.parse(dates.getPkgtdate());
	        String clientid=ipdDAO.getClientIDFromIPDID(ipdid);
	        Date modifyfdate=sdf1.parse(fromdate);
	        Date modifytodate=sdf1.parse(todate);
	        int z=ipdDAO.updateParentPkgAmt(parentid, amount,fromdate,todate);
	        
	        if(orignalfdate.before(modifyfdate)){
	        	completeAptmDAO.swapPkgChargeTptoinvoiceList(dates.getPkgfdate(), fromdate, ipdid);
				completeAptmDAO.deletefromtpinvoiceassessment(dates.getPkgfdate(), fromdate, ipdid);
	        }else if(orignalfdate.after(modifyfdate)){
			completeAptmDAO.swapPkgChargeList(fromdate, dates.getPkgfdate(), ipdid, Integer.parseInt(parentid),clientid);
			completeAptmDAO.deletefromapmminvoiceassessment(fromdate, dates.getPkgfdate(), ipdid,clientid);
	        }
	        
	        
	        if(orignaltodate.before(modifytodate)){
	        	completeAptmDAO.swapPkgChargeList(dates.getPkgtdate(), todate, ipdid,Integer.parseInt(parentid),clientid);
				completeAptmDAO.deletefromapmminvoiceassessment(dates.getPkgtdate(), todate, ipdid,clientid);
				
				
	        }else if(orignaltodate.after(modifytodate)) {
	        	completeAptmDAO.swapPkgChargeTptoinvoiceList(todate, dates.getPkgtdate(), ipdid);
	        	completeAptmDAO.deletefromtpinvoiceassessment(todate, dates.getPkgtdate(), ipdid);
		        }
		}
	} catch (Exception e) {
		e.printStackTrace();
	}
	if(!statementred.equals("5")){
	return "savepkgDataedit";
	}else{
		return "savepkgDataeditviewacc";	
	}
	
}
public String savePkgDataFromAcc() throws Exception{
	Connection connection= null;
	try{
		String fromdate = request.getParameter("pkgfromdate");
		String todate = request.getParameter("pkgtodate");
		String pkgid = request.getParameter("packagename");
		String newpackagename = request.getParameter("newpackagename");
		String newpackageamt = request.getParameter("newpackageamt");
		connection = Connection_provider.getconnection();
		PackageMasterDAO masterDAO = new JDBCPackageMasterDAO(connection);
	
		String hrs= request.getParameter("hour");
		String min= request.getParameter("minute");
		if(hrs==null){
			hrs="00";
		}else if(hrs.equals("")){
			hrs="00";
		}
		if(min==null){
			min="00";
		}else if(min.equals("")){
			min="00";
		}
		
		DateFormat dateFormat = new SimpleDateFormat("HH:mm");
	      Calendar cal = Calendar.getInstance();
	      String time = dateFormat.format(cal.getTime());  
		time=time+":00";
		todate= todate +" "+hrs+":"+min+":00";
		fromdate= fromdate+" "+hrs+":"+min+":00";
		IpdDAO ipdDAO = new JDBCIpdDAO(connection);
		boolean flag =false;
		if(newpackagename==null){
			flag =false;
		}else if(newpackagename.equals("")){
			flag =false;
		}else{
			flag =true;
		}
		if(!flag){
			String  ipdid = request.getParameter("ipdid");
			String  clientid = request.getParameter("clientid");
			if(ipdid==null){
				ipdid="0";
			}
			if(ipdid.equals("")||ipdid.equals("0")){
				ipdid=ipdDAO.getLAstIpdIdByClient(clientid);
			}
			String fromdate1[]= fromdate.split(" ");
			String todate1[]= todate.split(" ");
			
			fromdate1[0] = DateTimeUtils.getCommencingDate1(fromdate1[0]);
			todate1[0] = DateTimeUtils.getCommencingDate1(todate1[0]);
			fromdate= fromdate1[0]+" "+fromdate1[1];
			todate= todate1[0]+" "+todate1[1];
			PackageMaster master = masterDAO.getPerticularPackage(pkgid);
			ArrayList<PackageMaster> arrayList = masterDAO.getPackageFromChild(Integer.parseInt(pkgid));
			int result = ipdDAO.saveparentpackagedata(fromdate,todate,master,clientid,ipdid,newpackageamt);
			
			for (PackageMaster packageMaster : arrayList) {
				String pkgper="", tpcode="";
				if(master.isTp()){
					tpcode=packageMaster.getCode();
							/*request.getParameter("tpcode"+packageMaster.getChild_id()+"");*/
				}else{
					 pkgper = request.getParameter("pkgper"+packageMaster.getChild_id()+"");
				}
				String pkgamt = request.getParameter("pkgamt"+packageMaster.getChild_id()+"");
				
				packageMaster.setPercentage(pkgper);
				packageMaster.setCal_amount(pkgamt);
				packageMaster.setCode(tpcode);
				int result1 = ipdDAO.savechildpackagedata(packageMaster,result);
			}
		}else{
			String  ipdid = request.getParameter("ipdid");
			String  clientid = request.getParameter("clientid");
			fromdate = DateTimeUtils.getCommencingDate1(fromdate);
			todate = DateTimeUtils.getCommencingDate1(todate);
			//PackageMaster master = masterDAO.getPerticularPackage(pkgid);
			ArrayList<PackageMaster> arrayList = masterDAO.getPackageFromChild(Integer.parseInt(pkgid));
			
			int res = masterDAO.storePackageParentData(newpackagename, newpackageamt,"0","0","0","","");
			for (PackageMaster packageMaster : arrayList) {
				String pkgper = request.getParameter("pkgper"+packageMaster.getChild_id()+"");
				String pkgamt = request.getParameter("pkgamt"+packageMaster.getChild_id()+"");
				packageMaster.setPercentage(pkgper);
				packageMaster.setCal_amount(pkgamt);
				int res2 = masterDAO.storePackageChildData(packageMaster, res);
			}
			PackageMaster master = masterDAO.getPerticularPackage(""+res);
			int result = ipdDAO.saveparentpackagedata(fromdate,todate,master,clientid,ipdid,newpackageamt);
			for (PackageMaster packageMaster : arrayList) {
				String pkgper = request.getParameter("pkgper"+packageMaster.getChild_id()+"");
				String pkgamt = request.getParameter("pkgamt"+packageMaster.getChild_id()+"");
				packageMaster.setPercentage(pkgper);
				packageMaster.setCal_amount(pkgamt);
				int result1 = ipdDAO.savechildpackagedata(packageMaster,result);
			}
		}
		
	} catch (Exception e) {
		e.printStackTrace();
	}
	   return "pkgsavedpreview";
   }


public String patienttransactionrpt() {
	Connection connection=null;
	try {
	 connection= Connection_provider.getconnection();
	IpdDAO ipdDAO= new JDBCIpdDAO(connection);
	String fromdate = ipdForm.getFromdate();
	String todate = ipdForm.getTodate();
	String searchtext=ipdForm.getSearchtext();
	String paymentid=request.getParameter("paymentid");
	if(searchtext!=null){
		if(searchtext.equals("")){
			searchtext=null;
		}
	}
	
	if(DateTimeUtils.isNull(fromdate).equals("")){
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		fromdate = dateFormat.format(new Date());
	}else{
		fromdate = DateTimeUtils.getCommencingDate1(fromdate);
	}
	if(DateTimeUtils.isNull(todate).equals("")){
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		todate = dateFormat.format(new Date());
	}else{
		todate = DateTimeUtils.getCommencingDate1(todate);
	}
	ipdForm.setFromdate(DateTimeUtils.getCommencingDate1(fromdate));
	ipdForm.setTodate(DateTimeUtils.getCommencingDate1(todate));
	ArrayList<Ipd> patienttransactionlist=ipdDAO.getAllpatienttransactionList(fromdate,todate,searchtext,paymentid);
	ipdForm.setPatienttransactionlist(patienttransactionlist);
	}catch (Exception e) {
		e.printStackTrace();
	}
	return "patienttransact";
}
}
