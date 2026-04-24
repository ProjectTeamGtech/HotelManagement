package com.apm.Emr.web.action;

import java.sql.Connection;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.apm.Accounts.eu.bi.AccountsDAO;
import com.apm.Accounts.eu.blogic.jdbc.JDBCAccountsDAO;
import com.apm.Appointment.eu.bi.AppointmentDAO;
import com.apm.Appointment.eu.blogic.jdbc.JDBCAppointmentDAO;
import com.apm.Appointment.eu.entity.AppointmentType;
import com.apm.Diagnosis.eu.entity.Diagnosis;
import com.apm.DiaryManagement.eu.bi.ClientDAO;
import com.apm.DiaryManagement.eu.bi.CompleteAptmDAO;
import com.apm.DiaryManagement.eu.bi.NotAvailableSlotDAO;
import com.apm.DiaryManagement.eu.blogic.jdbc.JDBCClientDAO;
import com.apm.DiaryManagement.eu.blogic.jdbc.JDBCCompleteAptmDAO;
import com.apm.DiaryManagement.eu.blogic.jdbc.JDBCNotAvailableSlotDAO;
import com.apm.DiaryManagement.eu.entity.Client;
import com.apm.DiaryManagement.eu.entity.CompleteAppointment;
import com.apm.DiaryManagement.eu.entity.Priscription;
import com.apm.Emr.eu.bi.EmrDAO;
import com.apm.Emr.eu.bi.InvestigationDAO;
import com.apm.Emr.eu.bi.PrescriptionDAO;
import com.apm.Emr.eu.blogic.jdbc.JDBCEmrDAO;
import com.apm.Emr.eu.blogic.jdbc.JDBCInvestigationDAO;
import com.apm.Emr.eu.blogic.jdbc.JDBCPrescriptionDAO;
import com.apm.Emr.web.form.EmrForm;
import com.apm.Inventory.eu.bi.InventoryProductDAO;
import com.apm.Inventory.eu.blogic.jdbc.JDBCInventoryProductDAO;
import com.apm.Inventory.eu.entity.Product;
import com.apm.Ipd.eu.bi.BedDao;
import com.apm.Ipd.eu.blogic.jdbc.JDBCBedDao;
import com.apm.Ipd.eu.entity.Bed;
import com.apm.Master.eu.bi.MasterDAO;
import com.apm.Master.eu.bi.TreatmentTypeDAO;
import com.apm.Master.eu.blogic.jdbc.JDBCMasterDAO;
import com.apm.Master.eu.blogic.jdbc.JDBCTreatmentTypeDAO;
import com.apm.Master.eu.entity.Master;
import com.apm.Master.eu.entity.TreatmentType;
import com.apm.Pharmacy.eu.bi.PharmacyDAO;
import com.apm.Pharmacy.eu.blogic.jdbc.JDBCPharmacyDAO;
import com.apm.Registration.eu.bi.ClinicDAO;
import com.apm.Registration.eu.bi.UserProfileDAO;
import com.apm.Registration.eu.blogic.jdbc.JDBCClinicDAO;
import com.apm.Registration.eu.blogic.jdbc.JDBCUserProfileDAO;
import com.apm.Registration.eu.entity.Clinic;
import com.apm.Registration.eu.entity.Location;
import com.apm.Registration.eu.entity.UserProfile;
import com.apm.common.eu.blogic.jdbc.Connection_provider;
import com.apm.common.utils.DateTimeUtils;
import com.apm.common.utils.Pagination;
import com.apm.common.web.action.BaseAction;
import com.apm.common.web.common.helper.LoginHelper;
import com.apm.common.web.common.helper.LoginInfo;
import com.apm.main.common.constants.Constants;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.Preparable;

public class PrescriptionAction extends BaseAction implements
		ModelDriven<EmrForm>, Preparable {

	EmrForm emrForm = new EmrForm();
	HttpServletRequest request = ServletActionContext.getRequest();
	HttpServletResponse response = ServletActionContext.getResponse();
	HttpSession session = request.getSession(false);
	LoginInfo loginInfo = LoginHelper.getLoginInfo(request);

Pagination pagination=new Pagination(25, 1);
    
    
    

	public Pagination getPagination() {
		return pagination;
	}

	public void setPagination(Pagination pagination) {
		this.pagination = pagination;
	}

	public String execute() throws Exception {

		if (!verifyLogin(request)) {

			return "login";
		}

		Connection connection = null;
		try {
			
			session.removeAttribute("sessionadmissionid");

			connection = Connection_provider.getconnection();
			PrescriptionDAO prescriptionDAO = new JDBCPrescriptionDAO(
					connection);
			InvestigationDAO investigationDAO = new JDBCInvestigationDAO(connection);
			PharmacyDAO pharmacyDAO = new JDBCPharmacyDAO(connection);
			BedDao bedDao = new JDBCBedDao(connection);
			
			String serachtext = (String)session.getAttribute("priscdash_searchText");
			String fromdate = (String)session.getAttribute("priscdash_fromdate");
			String todate = (String)session.getAttribute("priscdash_todate");
			String phar_wardid = (String)session.getAttribute("priscdash_phar_wardid");
			String filter_location = (String)session.getAttribute("priscdash_filter_location");
			String filter_phar_location =(String) session.getAttribute("priscdash_filter_phar_location");
			String actiontype=request.getParameter("IpdpatientType");
			String fakclientid=request.getParameter("fake_clientid");
			String fuhid=request.getParameter("uhid");
			
			if(filter_phar_location==null){
				filter_phar_location=emrForm.getFilter_phar_location();
			}else{
				session.removeAttribute("priscdash_filter_phar_location");
			}
			
			if(filter_location==null){
				filter_location=emrForm.getFilter_location();
			}else{
				session.removeAttribute("priscdash_filter_location");
			}
			
			if(fromdate==null){
				fromdate=emrForm.getFromdate();
			}else{
				session.removeAttribute("priscdash_fromdate");
			}
			
			if(todate==null){
				todate=emrForm.getTodate();
			}else{
				session.removeAttribute("priscdash_todate");
			}
			
			if(phar_wardid==null){
				phar_wardid = emrForm.getPhar_wardid();
			}else{
				session.removeAttribute("priscdash_phar_wardid");
			}
			
			if(serachtext==null){
				serachtext = emrForm.getSearchText();
			}else{
				session.removeAttribute("priscdash_searchText");
			}
			
			if(serachtext==null){
				serachtext ="";
			}
			if(fromdate!=null){
				
				if(!fromdate.equals("")){
				  fromdate=DateTimeUtils.getCommencingDate1(fromdate);
				}else {
					fromdate=null;
				}
			} else {
				fromdate=DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone());
				String str[]=fromdate.split(" ");
				fromdate=str[0];	
			}
			
			
			if(todate!=null){
				if(!todate.equals("")){
				  todate=DateTimeUtils.getCommencingDate1(todate);
				}else {
					todate=null;
				}
			} else {
				
				todate=DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone());
				String str[]=todate.split(" ");
				todate=str[0];
				
			}
			
			if(phar_wardid!=null){
				if(phar_wardid.equals("")){
					phar_wardid = "0";
				}
			}else{
				phar_wardid = "0";
			}
			emrForm.setPhar_wardid(phar_wardid);

			if(filter_location!=null){
				if(filter_location.equals("")){
					filter_location="10";
				}
			}else{
				filter_location="10";
			}
			String fdate=prescriptionDAO.getfakedatebyClientid(fakclientid);
			if(fdate!=null) {
				fdate=fdate.split(" ")[0];
			}
			
			if(fdate!=null) {
			 if(actiontype.equals("1")){
				fromdate=fdate;
				serachtext=fuhid;
			}
			}
			
			filter_phar_location = DateTimeUtils.numberCheck(filter_phar_location);
			int count=prescriptionDAO.getAllPriscriptionCountNew(serachtext,fromdate,todate,"0",phar_wardid,filter_location,filter_phar_location);
			pagination.setPreperties(count);
			
			ArrayList<Priscription> priscriptionlist = prescriptionDAO.getAllPriscriptionList(pagination,serachtext,fromdate,todate,loginInfo,phar_wardid,filter_location,filter_phar_location);
			
			pagination.setPage_records(priscriptionlist.size());
			emrForm.setTotalRecords(count);
			emrForm.setPagerecords(String.valueOf(pagination.getPage_records()));
			
			fromdate=DateTimeUtils.getCommencingDate1(fromdate);
			todate=DateTimeUtils.getCommencingDate1(todate);
			emrForm.setFilter_location(filter_location);
			emrForm.setFromdate(fromdate);
			emrForm.setTodate(todate);
			
			emrForm.setPriscriptionlist(priscriptionlist);
			
			ArrayList<Master> locationListPharmacy= pharmacyDAO.getAllLocationNew();
			emrForm.setLocationListPharmacy(locationListPharmacy);
			int default_location = pharmacyDAO.getByDefaultPharmacyLocation();
			emrForm.setDefault_phar_location(""+default_location);
			
			ArrayList<Bed> wardlist=bedDao.getAllWardList("0");
			emrForm.setWardlist(wardlist);
			if(loginInfo.getJobTitle().equals("Medical Store")){
				return "medicalstore";
			}

		} catch (Exception e) {

			e.printStackTrace();
		} finally {
			connection.close();
		}
		

		return SUCCESS;
	}
	
	
	
	public String outocharge(){
		Connection connection = null;
		try{
			String selectedid = request.getParameter("selectedid");
			connection = Connection_provider.getconnection();
			PrescriptionDAO prescriptionDAO = new JDBCPrescriptionDAO(connection);
			CompleteAptmDAO completeAptmDAO = new JDBCCompleteAptmDAO(connection);
			
			ArrayList<Priscription>chargedMdicineList = prescriptionDAO.getchargedMdicineList(selectedid);
			CompleteAppointment completeAppointment = new CompleteAppointment();
			
			for(Priscription priscription : chargedMdicineList){
				Product product = prescriptionDAO.getProductDetails(priscription.getMdicinenameid()); 
				
				String temp[] = priscription.getDosage().split("-");
				int days = priscription.getDays();
				int qty = temp.length * days;
				
				ClientDAO clientDAO = new JDBCClientDAO(connection);
				Client client = clientDAO.getClientDetails(priscription.getClientId());
				String clientname = client.getTitle() + " " + client.getFirstName() + " " + client.getLastName();
				

				String payBuy = "0";
				if(client.getWhopay().equals(Constants.PAY_BY_THIRD_PARTY)){
					payBuy = "1";
				}
				
				completeAppointment.setUser(clientname);
				completeAppointment.setApmtType("0");
				completeAppointment.setManualcharge(product.getProduct_name());
				completeAppointment.setCharges(product.getSale_price());
				completeAppointment.setClientId(priscription.getClientId());
				
				DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
				Calendar cal = Calendar.getInstance();
				String date = dateFormat.format(cal.getTime());
				completeAppointment.setCommencing(date);
				completeAppointment.setPayBuy(payBuy);
				completeAppointment.setMarkAppointment("1");
				completeAppointment.setQuantity(qty);
				completeAppointment.setMasterchargetype("MEDICINE");
				completeAppointment.setProdid(product.getId());
				completeAppointment.setAppointmentid("0");
				completeAppointment.setPractitionerId("0");
				completeAppointment.setPractitionerName("");
				
				int result = completeAptmDAO.saveCharge(completeAppointment,"0",loginInfo.getId());
			}
			
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	
	
	public String dstatus() throws Exception{
		
		Connection connection = null;
		try {
			
			
			String id = request.getParameter("id");
			String ipdid=request.getParameter("ipdid");
			String mstatus=request.getParameter("mstatus");
			String fromdate=request.getParameter("fromdate");
			String todate=request.getParameter("todate");
			connection = Connection_provider.getconnection();
			PrescriptionDAO prescriptionDAO = new JDBCPrescriptionDAO(connection);
			BedDao bedDao=new JDBCBedDao(connection);
	        Bed bed=bedDao.getEditIpdData(ipdid); 		
	        
	       
			int status = prescriptionDAO.getDstatus(id);
			
			SimpleDateFormat sdfDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//dd/MM/yyyy
			Date now = new Date();
			String ddate = sdfDate.format(now);
			
			int update = prescriptionDAO.updateDstatus(id,status,ddate);
			String datetime=DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone());
			int result= prescriptionDAO.updateMedicineStatus(bed.getTreatmentepisodeid(),datetime,mstatus);
			
			emrForm.setFromdate(fromdate);
			emrForm.setTodate(todate);
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		return execute();
	}
	
	public String rpeat(){
		
		Connection connection = null;
		//var url = "rpeatPrescription?clientId="+cid+"&prectionerid="+pid+"&conditionid="+conid+" ";
		String clientId = request.getParameter("clientId");
		String prectionerid = request.getParameter("prectionerid");
		String conditionid = request.getParameter("conditionid");
		
		try{
			connection = Connection_provider.getconnection();
			EmrDAO emrDAO = new JDBCEmrDAO(connection);
			UserProfileDAO userProfileDAO=new JDBCUserProfileDAO(connection);
			UserProfile userProfile=userProfileDAO.getUserprofileDetails(Integer.parseInt(prectionerid));

			if(userProfile.getJobgroup().equals("4")){
				prectionerid = userProfile.getDoctor();
				//common condition
				userProfile=userProfileDAO.getUserprofileDetails(Integer.parseInt(prectionerid));
				conditionid = userProfile.getDiciplineName();
			}
			
			
			ArrayList<Priscription>parentPriscList = emrDAO.getRptParentPriscList(clientId,prectionerid,conditionid);
			
			StringBuffer str = new StringBuffer();
			
			str.append("<select onchange='editparentprisc(this.value)' name='repeatdate' id='repeatdate' class='form-control'>");
			str.append("<option value='0'>Repeat Prescription </option>");
			
			for(Priscription priscription : parentPriscList){
				str.append("<option value='"+priscription.getId()+"'>"+priscription.getDate()+"</option>");
			}
			
			str.append("</select>");
			
			
			response.setContentType("text/html");
			response.setHeader("Cache-Control", "no-cache");
			response.getWriter().write(str.toString());
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
		
		return null;
	}
	
	//  19 JUne 2018 8:45 PM
	public String savemedreqforphar() throws Exception{
		Connection connection = null;
		String action=emrForm.getHiddenval();
		try {
			connection = Connection_provider.getconnection();
			EmrDAO emrDAO = new JDBCEmrDAO(connection);
			String parentid = emrForm.getParentid();
			String remark =  emrForm.getRemark();
			String priscreqids = emrForm.getPriscreqids();
			String default_phar_location = emrForm.getDefault_phar_location();
			Priscription priscription = emrDAO.getPriscriptionParentData(Integer.parseInt(parentid));
			String date = "";
			DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Calendar cal = Calendar.getInstance();
			date = dateFormat.format(cal.getTime());
			priscription.setDate(date);
			priscription.setUserid(loginInfo.getUserId());
		
			int newparentid = emrDAO.saveParentPriscNew(priscription,remark,parentid,default_phar_location,1);
			String[] ids = priscreqids.split(","); 
			for (String string : ids) {
				if(string.equals("0")){
					continue;
				}
				String mdrequestqty = request.getParameter("mdrequestqty"+string);
				String mdname = request.getParameter("mdname"+string);
				String mdid = request.getParameter("mdid"+string);
				String childid = request.getParameter("childid"+string);
				String newchild = childid;
				priscription.setMdicinenameid(mdid);
				priscription.setMdicinenametxt(mdname);
				int res = emrDAO.saveChildPriscNew(priscription,mdrequestqty,parentid,childid,newparentid);
			}
			//token id generation
			String cdate = DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone()).split(" ")[0];
			int tokenid = emrDAO.getPriscTokenId(cdate);
			int res = emrDAO.updateTokenInPric(newparentid,tokenid);
			
			session.setAttribute("saveparent", Integer.parseInt(parentid));
			session.setAttribute("prisc_newparentid", ""+newparentid);
			if(!action.equals("1")){
				session.setAttribute("priscdash_searchText", emrForm.getSearchText());
				session.setAttribute("priscdash_fromdate", emrForm.getFromdate());
				session.setAttribute("priscdash_todate", emrForm.getTodate());
				session.setAttribute("priscdash_phar_wardid", emrForm.getPhar_wardid());
				session.setAttribute("priscdash_filter_location", emrForm.getFilter_location());
				session.setAttribute("priscdash_filter_phar_location", emrForm.getFilter_phar_location());
				
			}
			/*if(action.equals("1")){
			session.setAttribute("saveparent", parentid);
			EmrAction emrAction=new EmrAction();
			emrAction.printprisc();
			}*/
		} catch (Exception e) {
			e.printStackTrace();
		}
		if(action.equals("1")){
			return "savenprint";
		}else{
			return "save";
		}
		
	}
	
	
	//  19 JUne 2018 8:45 PM
		public String saverequestprisc() throws Exception{
			Connection connection = null;
			try {
				connection = Connection_provider.getconnection();
				PrescriptionDAO prescriptionDAO = new JDBCPrescriptionDAO(connection);
				String parentid = emrForm.getParentid();
				String priscreqids = emrForm.getPriscreqids();
				String date = "";
				DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				Calendar cal = Calendar.getInstance();
				date = dateFormat.format(cal.getTime());
				
				String[] ids = priscreqids.split(","); 
				int res =0;
				for (String string : ids) {
					if(string.equals("0")){
						continue;
					}
					String childid = request.getParameter("childid"+string);
					res = prescriptionDAO.updatePriscChildDeliverStatus(childid,date,loginInfo.getUserId());
				}
				//res = prescriptionDAO.updateNotDeilverStatusChild(priscreqids,parentid);
				int totalrequest = prescriptionDAO.getAllChildPriscCount(parentid,0);
				int requestcmplt = prescriptionDAO.getAllChildPriscCount(parentid,1);
				if(requestcmplt==0){
					res = prescriptionDAO.updateParentPriscDeliverStatus(parentid,0,loginInfo.getUserId(),date); 
				}else if(requestcmplt==totalrequest){
					res = prescriptionDAO.updateParentPriscDeliverStatus(parentid,1,loginInfo.getUserId(),date); 
				}else{
					res = prescriptionDAO.updateParentPriscDeliverStatus(parentid,2,loginInfo.getUserId(),date); 
				}
				
			} catch (Exception e) {
				e.printStackTrace();
			}
			return "save";
		}
	
	public String followup(){
		
		String remingmeduration = request.getParameter("remingmeduration");
		
		if(remingmeduration.equals("0")){
			remingmeduration = "30000";
		}
		session.setAttribute("remingmeduration", remingmeduration);
		
		return null;
	}

	
	//  19 JUne 2018 8:45 PM
		public String savereturnprisc() throws Exception{
			Connection connection = null;
			try {
				connection = Connection_provider.getconnection();
				PrescriptionDAO prescriptionDAO = new JDBCPrescriptionDAO(connection);
				EmrDAO emrDAO = new JDBCEmrDAO(connection);
				String parentid = emrForm.getParentid();
				String remark =  emrForm.getRemark();
				String priscreqids = emrForm.getPriscreqids();
				
				Priscription priscription = emrDAO.getPriscriptionParentData(Integer.parseInt(parentid));
				String date = "";
				DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				Calendar cal = Calendar.getInstance();
				date = dateFormat.format(cal.getTime());
				priscription.setDate(date);
				priscription.setUserid(loginInfo.getUserId());
			
				int newparentid = prescriptionDAO.saveParentReturnPrisc(priscription,remark,parentid);
				String[] ids = priscreqids.split(","); 
				for (String string : ids) {
					if(string.equals("0")){
						continue;
					}
					String mdrequestqty = request.getParameter("mdrequestqty"+string);
					String mdname = request.getParameter("mdname"+string);
					String mdid = request.getParameter("mdid"+string);
					String childid = request.getParameter("childid"+string);
					priscription.setMdicinenameid(mdid);
					priscription.setMdicinenametxt(mdname);
					int res = prescriptionDAO.saveChildReturnPrisc(priscription,mdrequestqty,parentid,childid,newparentid);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			return "save";
		}
	public void prepare() throws Exception {
		Connection connection = null;
		try{
			connection = Connection_provider.getconnection();
			EmrDAO emrDAO = new  JDBCEmrDAO(connection);
			MasterDAO masterDAO = new JDBCMasterDAO(connection);
			ClientDAO clientDAO = new JDBCClientDAO(connection);
			NotAvailableSlotDAO notAvailableSlotDAO = new JDBCNotAvailableSlotDAO(connection);
			
			String date = DateTimeUtils.getDashboardTodayDate(loginInfo.getTimeZone());
			emrForm.setPriscdate(date);
			emrForm.setPriscdateandtime(DateTimeUtils.getPriscDatetime(loginInfo.getTimeZone()));
			
			ArrayList<Master>mdicinecategoryList = emrDAO.getmedicineCategoryList();
			emrForm.setMdicinecategoryList(mdicinecategoryList);
			
			ArrayList<Master>dosageList = emrDAO.getDosageList();
			emrForm.setDosageList(dosageList);
			
			ArrayList<Master>mdicneTypeList = emrDAO.getMedicineTypeList();
			emrForm.setMdicneTypeList(mdicneTypeList);
			
			ArrayList<Client> condtitionList = clientDAO.getTreatmentTypeList();
			emrForm.setTreatmentTypeList(condtitionList);
			
			ArrayList<Master>invsTypeList = emrDAO.getInvesigationTypeList();
			emrForm.setInvsTypeList(invsTypeList);
			
			ArrayList<Master>invstReportTypeList = emrDAO.getInvstReportTypeList();
			emrForm.setInvstReportTypeList(invstReportTypeList);
			
			ArrayList<Master>invstUnitList = emrDAO.getInvstUnitList();
			emrForm.setInvstUnitList(invstUnitList);
			
			Clinic clinic = new Clinic();
			ClinicDAO clinicDAO  = new JDBCClinicDAO(connection);
			AccountsDAO accountsDAO = new JDBCAccountsDAO(connection);
			
			ArrayList<Clinic> locationAdressList = accountsDAO.getLetterHeadDetails(loginInfo.getClinicid());
			clinic = clinicDAO.getCliniclistDetails(loginInfo.getClinicid());
			emrForm.setClinicName(clinic.getClinicName());
			emrForm.setClinicOwner(clinic.getClinicOwner());
			emrForm.setOwner_qualification(clinic.getOwner_qualification());
		//	emrForm.setLocationAdressList(locationAdressList);
			//accountsForm.setAddress(clinic.getAddress());
			emrForm.setLandLine(clinic.getLandLine());
			emrForm.setClinicemail(clinic.getEmail());
			emrForm.setWebsiteUrl(clinic.getWebsiteUrl());
			
			emrForm.setLocationAdressList(locationAdressList);
			//emrForm.setClinicLogo(clinic.getUserImageFileName());
			
			ArrayList<Master>medicineList = masterDAO.getMedicineList();
			emrForm.setMedicineList(medicineList);
			
			ArrayList<Diagnosis>diagnosisList = masterDAO.getDiagnosisList();
			emrForm.setDiagnosislist(diagnosisList);
			
			ArrayList<Priscription>parentPriscList = new ArrayList<Priscription>();
			emrForm.setParentPriscList(parentPriscList);
			

			ArrayList<Master>dosagenoteList = masterDAO.getDosageNoteList();
			emrForm.setDosagenoteList(dosagenoteList);
			
			AppointmentDAO appointmentDAO = new JDBCAppointmentDAO(connection);
			ArrayList<AppointmentType>additionalChargeList = appointmentDAO.getAdditionalChaergeTypeList("");
			emrForm.setAdditionalChargeList(additionalChargeList);
			
			ArrayList<Master>masterChageTypeList = appointmentDAO.getmasterChageTypeList(loginInfo);
			emrForm.setMasterChageTypeList(masterChageTypeList);
			emrForm.setMasterchargetype("Additional Charge");
			
			ArrayList<Location>locationList = notAvailableSlotDAO.getLocationList(loginInfo.getId());
			emrForm.setLocationList(locationList);
			String regLocationID = emrDAO.getRegisteredLocationId();
			emrForm.setLocationid(regLocationID);
			
			//set template name list
			
			ArrayList<Priscription>templateNameList = emrDAO.getTemplateNameList(loginInfo);
			emrForm.setTemplateNameList(templateNameList);
			
			ArrayList<Master> priscUnitList= masterDAO.getPriscUnitList();
			emrForm.setPriscUnitList(priscUnitList);
			
		}catch(Exception e){
			e.printStackTrace();
		}
		finally{ 
			connection.close();
		}

	}
	
	public String openmultiplerequestnew() throws Exception{
		//@k@sh 20 June 2018
   	   	Connection connection = null;
		 try {
			connection = Connection_provider.getconnection();
			PrescriptionDAO prescriptionDAO = new JDBCPrescriptionDAO(connection);
			String id = request.getParameter("id");
			
			ArrayList<Priscription> arrayList = prescriptionDAO.getMultiplePriscRequestNew(id);
			StringBuffer buffer = new StringBuffer();
			int srno =0;
			for (Priscription priscription : arrayList) {
				buffer.append("<tr>");
				buffer.append("<td>"+(++srno)+"</td>");
				buffer.append("<td>"+priscription.getDate()+"</td>");
				if(DateTimeUtils.isNull(priscription.getTokenno()).equals("")){
					buffer.append("<td></td>");
				}else{
					buffer.append("<td>TN-"+priscription.getTokenno()+"</td>");
				}
				if(priscription.getStatus().equals("0")){
					buffer.append("<td>"+priscription.getId()+"</td>");
				}else{
					buffer.append("<td>"+priscription.getId()+"</td>");
				}
				buffer.append("<td>"+priscription.getBillno()+"</td>");
				if(priscription.getStatus().equals("0")){
					buffer.append("<td>Requested</td>");
				}else{
					buffer.append("<td>Delivered</td>");
				}
				buffer.append("<td>"+priscription.getReq_location()+"</td>");
				buffer.append("<td>"+priscription.getUserid()+"</td>");
				buffer.append("<td><a href='#' onclick=openPopup('printpriscEmr?selectedid="+id+"&newparentid="+priscription.getId()+"')><i class='fa fa-print'></i></a></td>");
				buffer.append("</tr>");
				for (Priscription priscription1 : priscription.getChildprisclist()) {
					buffer.append("<tr>");
					buffer.append("<td></td>");
					buffer.append("<td></td>");
					buffer.append("<td></td>");
					buffer.append("<td>"+priscription1.getMdicinenametxt()+"</td>");
					buffer.append("<td>"+priscription1.getPriscqty()+"</td>");
					buffer.append("<td></td>");
					buffer.append("<td></td>");
					buffer.append("<td></td>");
					buffer.append("<td></td>");
					buffer.append("</tr>");
				}
				
			}
			
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

	public EmrForm getModel() {
		// TODO Auto-generated method stub
		return emrForm;
	}
	
	public String returnpriscdashboard() throws Exception {

		if (!verifyLogin(request)) {

			return "login";
		}

		Connection connection = null;
		try {
			
			connection = Connection_provider.getconnection();
			PrescriptionDAO prescriptionDAO = new JDBCPrescriptionDAO(connection);
			
			String fromdate=emrForm.getFromdate();
			String todate=emrForm.getTodate();
			
			if(fromdate!=null){
				if(!fromdate.equals("")){
				  fromdate=DateTimeUtils.getCommencingDate1(fromdate);
				}else {
					fromdate=null;
				}
			} else {
				fromdate=DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone());
				String str[]=fromdate.split(" ");
				fromdate=str[0];	
			}
			if(todate!=null){
				if(!todate.equals("")){
				  todate=DateTimeUtils.getCommencingDate1(todate);
				}else {
					todate=null;
				}
			} else {
				todate=DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone());
				String str[]=todate.split(" ");
				todate=str[0];
			}
			
			String searchtext = emrForm.getSearchText();
			if(searchtext!=null){
				if(searchtext.equals("")){
					searchtext = null;
				}
			}
			int count=prescriptionDAO.getReturnPriscDashboardCount(searchtext,fromdate,todate);
			pagination.setPreperties(count);
			
			ArrayList<Priscription> priscriptionlist = prescriptionDAO.getReturnPriscDashboard(pagination,searchtext,fromdate,todate);
			pagination.setPage_records(priscriptionlist.size());
			emrForm.setTotalRecords(count);
			emrForm.setPagerecords(String.valueOf(pagination.getPage_records()));
			
			fromdate=DateTimeUtils.getCommencingDate1(fromdate);
			todate=DateTimeUtils.getCommencingDate1(todate);
			
			emrForm.setFromdate(fromdate);
			emrForm.setTodate(todate);
			
			emrForm.setPriscriptionlist(priscriptionlist);
			

		} catch (Exception e) {

			e.printStackTrace();
		} finally {
			connection.close();
		}

		return "returnpriscdashboard";
	}
	
	public String deletepriscription() throws Exception {
		
		Connection connection=null;
		try {
			connection=Connection_provider.getconnection();
			PrescriptionDAO prescriptionDAO = new JDBCPrescriptionDAO(connection);
			String parentid= request.getParameter("parentid");
			String delete_reason= request.getParameter("delete_reason");
			String userid = loginInfo.getUserId();
			String date = DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone());
			int del=prescriptionDAO.cancelPrescription(parentid,delete_reason,userid,date);
			
			response.setContentType("text/html");
			response.setHeader("Cache-Control", "no-cache");
			response.getWriter().write(""); 
		} catch (Exception e) {
			e.printStackTrace();
		}
		finally{
			connection.close();
		}
		
		return null;
	}

public String saveprinttakenstatus() throws Exception {
		
		Connection connection=null;
		try {
			connection=Connection_provider.getconnection();
			PrescriptionDAO prescriptionDAO = new JDBCPrescriptionDAO(connection);
			String id= request.getParameter("id");
			String val1= request.getParameter("val1");
			int res=prescriptionDAO.saveprinttakenstatus(id,val1);
			response.setContentType("text/html");
			response.setHeader("Cache-Control", "no-cache");
			response.getWriter().write(""); 
		} catch (Exception e) {
			e.printStackTrace();
		}
		finally{
			connection.close();
		}
		
		return null;
	}

}
