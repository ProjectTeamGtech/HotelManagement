package com.apm.Pharmacy.web.action;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.poi.hssf.record.EmbeddedObjectRefSubRecord;
import org.apache.struts2.ServletActionContext;

import com.apm.Accounts.eu.bi.AccountsDAO;
import com.apm.Accounts.eu.bi.ChargesAccountProcessingDAO;
import com.apm.Accounts.eu.blogic.jdbc.JDBCAccountsDAO;
import com.apm.Accounts.eu.blogic.jdbc.JDBCChargeAccountProcesDAO;
import com.apm.Accounts.eu.entity.Accounts;
import com.apm.DiaryManagement.eu.bi.ClientDAO;
import com.apm.DiaryManagement.eu.bi.NotAvailableSlotDAO;
import com.apm.DiaryManagement.eu.blogic.jdbc.JDBCClientDAO;
import com.apm.DiaryManagement.eu.blogic.jdbc.JDBCNotAvailableSlotDAO;
import com.apm.DiaryManagement.eu.entity.Breadcrumbs;
import com.apm.DiaryManagement.eu.entity.Client;
import com.apm.DiaryManagement.eu.entity.CompleteAppointment;
import com.apm.DiaryManagement.eu.entity.DiaryManagement;
import com.apm.DiaryManagement.eu.entity.Priscription;
import com.apm.DiaryManagement.web.action.EmbeddedImageEmailUtil;
import com.apm.DiaryManagement.web.action.SmsService;
import com.apm.Emr.eu.bi.EmrDAO;
import com.apm.Emr.eu.bi.PrescriptionDAO;
import com.apm.Emr.eu.blogic.jdbc.JDBCEmrDAO;
import com.apm.Emr.eu.blogic.jdbc.JDBCPrescriptionDAO;
import com.apm.Emr.web.form.EmrForm;
import com.apm.Inventory.eu.bi.IndentDAO;
import com.apm.Inventory.eu.bi.InventoryProductDAO;
import com.apm.Inventory.eu.bi.InventoryVendorDAO;
import com.apm.Inventory.eu.bi.ProcurementDAO;
import com.apm.Inventory.eu.blogic.jdbc.JDBCIndentDAO;
import com.apm.Inventory.eu.blogic.jdbc.JDBCInventoryProductDAO;
import com.apm.Inventory.eu.blogic.jdbc.JDBCInventoryVendorDAO;
import com.apm.Inventory.eu.blogic.jdbc.JDBCProcurementDAO;
import com.apm.Inventory.eu.entity.Product;
import com.apm.Inventory.eu.entity.Vendor;
import com.apm.Ipd.eu.bi.BedDao;
import com.apm.Ipd.eu.bi.IpdDAO;
import com.apm.Ipd.eu.blogic.jdbc.JDBCBedDao;
import com.apm.Ipd.eu.blogic.jdbc.JDBCIpdDAO;
import com.apm.Ipd.eu.entity.Bed;
import com.apm.Log.eu.entity.EmailLetterLog;
import com.apm.Master.eu.bi.DischargeOutcomeDAO;
import com.apm.Master.eu.bi.MasterDAO;
import com.apm.Master.eu.bi.PrescriptionMasterDAO;
import com.apm.Master.eu.blogic.jdbc.JDBCDischargeOutcomeDAO;
import com.apm.Master.eu.blogic.jdbc.JDBCMasterDAO;
import com.apm.Master.eu.blogic.jdbc.JDBCPrescriptionMasterDAO;
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
import com.apm.Report.eu.bi.ChargesReportDAO;
import com.apm.Report.eu.bi.SummaryReportDAO;
import com.apm.Report.eu.blogic.jdbc.JDBCChargesReportDAO;
import com.apm.Report.eu.blogic.jdbc.JDBCSummaryReportDAO;
import com.apm.ThirdParties.eu.bi.ThirdPartyDAO;
import com.apm.ThirdParties.eu.blogic.jdbc.JDBCThirdPartyDAO;
import com.apm.ThirdParties.eu.entity.ThirdParty;
import com.apm.common.eu.blogic.jdbc.Connection_provider;
import com.apm.common.utils.DateTimeUtils;
import com.apm.common.utils.Pagination;
import com.apm.common.web.action.BaseAction;
import com.apm.common.web.common.helper.LoginHelper;
import com.apm.common.web.common.helper.LoginInfo;
import com.apm.common.web.utils.PopulateList;
import com.apm.main.common.constants.Constants;
import com.onbarcode.barcode.Code128;
import com.onbarcode.barcode.Code39;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.Preparable;

public class PharmacyAction extends BaseAction implements ModelDriven<EmrForm>,Preparable{
	private static final Logger log = Logger.getLogger(PharmacyAction.class);
	EmrForm emrForm=new EmrForm();
    // Jitu Class
	
	// New Class
	HttpServletRequest request = ServletActionContext.getRequest();
	HttpServletResponse response = ServletActionContext.getResponse();
	HttpSession session = request.getSession(false);
	LoginInfo loginInfo = LoginHelper.getLoginInfo(request);
	
	private Pagination pagination = new Pagination(20, 1);
	public Pagination getPagination() {
		return pagination;
	}
	

	public void setPagination(Pagination pagination) {
		this.pagination = pagination;
	}
	
	public EmrForm getModel() {
		// TODO Auto-generated method stub
		return emrForm;
	}
 
	public String execute() throws Exception {

		if (!verifyLogin(request)) {

			return "login";
		}
		Connection connection = null;
		try {
			
			/*ArrayList<Breadcrumbs> indentflowlist = new ArrayList<Breadcrumbs>();
			ArrayList<Breadcrumbs> indentflowlisttemp = new ArrayList<Breadcrumbs>();
			if (session.getAttribute("indentflowlist") != null) {
				indentflowlisttemp = (ArrayList<Breadcrumbs>) session.getAttribute("indentflowlist");
			}
			boolean isavilablemodule= false;
			int modulecount =0;
			for (Breadcrumbs breadcrumbs : indentflowlisttemp) {
				breadcrumbs.setIscurrent(false);
				breadcrumbs.setSqno(modulecount);
				modulecount++;
				if(breadcrumbs.getName().equals("Pharmacy Dashboard")){
					isavilablemodule =true;
					breadcrumbs.setIscurrent(true);
					indentflowlist.add(breadcrumbs);
					break;
				}else{
					indentflowlist.add(breadcrumbs);
				}
			}
			if(!isavilablemodule){
				Breadcrumbs breadcrumbs = new Breadcrumbs();
				breadcrumbs.setName("Pharmacy Dashboard");
				breadcrumbs.setOn(true);
				breadcrumbs.setSqno(modulecount);
				breadcrumbs.setUrllink("Pharmacy");
				breadcrumbs.setIscurrent(true);
				breadcrumbs.setShowingname("Pharmacy Dashboard");
				indentflowlist.add(breadcrumbs);
			}
			session.setAttribute("indentflowlist",indentflowlist);*/
			
			String location="0";
			session.removeAttribute("sessionadmissionid");

			connection = Connection_provider.getconnection();
			UserProfileDAO userProfileDAO= new JDBCUserProfileDAO(connection);
			PharmacyDAO pharmacyDAO=new JDBCPharmacyDAO(connection);
			ThirdPartyDAO thirdPartyDAO=new JDBCThirdPartyDAO(connection);
			ArrayList<ThirdParty> thirdPartyList=new ArrayList<>();
			String payee=emrForm.getPayee();
			String tpid=emrForm.getTpid();	
			if(loginInfo.isParihar()) {
				thirdPartyList=thirdPartyDAO.getMainTPListNew(payee);
				emrForm.setThirdPartyTypeList(thirdPartyList);
			}else {
				thirdPartyList=thirdPartyDAO.getMainTPListNew("0");
				emrForm.setThirdPartyTypeList(thirdPartyList);
			}
			
			
			
			UserProfile profile2 = userProfileDAO.getPharmacyUserDetails(loginInfo.getClinicid());
			String userid = loginInfo.getUserId();
			Priscription priscription = pharmacyDAO.getPharacyUsrLInfo(userid);
			String ipdlocation="0";
			String opdlocation="0";
			String ipdloc="0";
			String opdloc="0";
			if(loginInfo.getUserType()==2) {
				 
				 location = (String) session.getAttribute("location");
				 if(location==null){
					 location= "0";
					 ipdloc=null;
					 opdloc=null;
				 }
				 if(location.equals("All")){
			    	 location="0";
			     }
			     
				 session.setAttribute("location", location);
			} else {
				 
				String loc="";
			     if(loginInfo.isParihar()) {
			    	 loc = (String) session.getAttribute("location");
			     }else {
			    	 loc=  priscription.getLocation();
			     }
			     
			     if(loc!=null){
			      	  session.setAttribute("location", loc);
			    	  location= loc;
			     }  else {
			    	 location= "0";
			     }
			     if(loc.equals("All")){
			    	 loc="0";
			     }
			     
			     UserProfile profile = userProfileDAO.getPharmacyUserDetails(loginInfo.getClinicid());
			     
			     if(profile.getProcurementType()!=null){
			    	 if(profile.getProcurementType().equals("1")){
			    		 loginInfo.setProcurementType(true);
			    	 } else {
			    		 loginInfo.setProcurementType(false);
			    	 }
			     } else {
			    	 loginInfo.setProcurementType(false);
			     }
			     
			     
			     ipdlocation= profile.getIpdlocation();
			     opdlocation= profile.getOpdlocation();
				 
				 for(String s: ipdlocation.split(",")){
					 if(s.equals("0")){
						 continue;
					 }
					 if(location.equals(s)){
						 ipdloc=location;
						 break;
					 }
					 
				 }
				 
				 for(String s:opdlocation.split(",")){
					  
					 if(s.equals("0")){
						 continue;
					 }
					 if(location.equals(s)){
						 opdloc=location;
						 break;
					 }
				 }
				 
			}
			
			
			emrForm.setLocation(location);
			
			if (priscription.getSale_bill()!=null)
				if(priscription.getSale_bill().equals("0"))
					loginInfo.setSale_bill(false);
				else
					loginInfo.setSale_bill(true);
			
			if (priscription.getDisscount()!=null)
				if (priscription.getDisscount().equals("0"))
					loginInfo.setDisscount(false);
				else
					loginInfo.setDisscount(true);
			
			if (priscription.getAccount()!=null)
				if (priscription.getAccount().equals("0"))
					loginInfo.setAccount(false);
				else
					loginInfo.setAccount(true);
			
			if (priscription.getLedger()!=null)
				if (priscription.getLedger().equals("0"))
					loginInfo.setLedger(false);
				else
					loginInfo.setLedger(true);
			
			if (priscription.getIslogin()!=null)
				if (priscription.getIslogin().equals("0"))
					loginInfo.setIslogin(false);
				else
					loginInfo.setIslogin(true);
			
			if (priscription.getPurchase_order()!=null)
				if (priscription.getPurchase_order().equals("0"))
					loginInfo.setPurchase_order(false);
				else
					loginInfo.setPurchase_order(true);
			
			if (priscription.getEdit_bill()!=null)
				if (priscription.getEdit_bill().equals("0"))
					loginInfo.setEdit_bill(false);
				else
					loginInfo.setEdit_bill(true);
			
			if (priscription.getDelete_bill()!=null)
				if (priscription.getDelete_bill().equals("0"))
					loginInfo.setDelete_bill(false);
				else
					loginInfo.setDelete_bill(true);
			if(priscription.getInhousepatient()!=null)
			if (priscription.getInhousepatient().equals("0"))
				loginInfo.setInhousepatient(false);
			else
				loginInfo.setInhousepatient(true);
			
			if (priscription.getReturn_medicine()!=null)
				if (priscription.getReturn_medicine().equals("0"))
					loginInfo.setReturn_medicine(false);
				else
					loginInfo.setReturn_medicine(true);
			
			
			if (!loginInfo.isIslogin()) {
				if(loginInfo.getUserType()==2 || loginInfo.getJobTitle().equals("Pharmacist")){
					
				}
				else{
					addActionError("Inactive pharmacy user");
					return "loginerror";
				}
			}
			/*String return_medicine =  priscription.getReturn_medicine();
			emrForm.setReturn_medicine(priscription.getReturn_medicine());*/
			String fromdate=emrForm.getFromdate();
			String todate=emrForm.getTodate();
			String searchtext= emrForm.getSearchText();
			String filter_status = emrForm.getFilter_status();
			String isfromreturndashboard = emrForm.getIsfromreturndashboard();
			String fakclientid=request.getParameter("fake_clientid");
			String fuhid=request.getParameter("uhid");
			String actiontype=request.getParameter("IpdpatientType");
			if(searchtext!=null){
				if(searchtext.equals("")){
					searchtext=null;
				}
			}
			
			if(filter_status==null){
				filter_status = "0";
			}else if(filter_status!=null){
				if(filter_status.equals("")){
					filter_status="0";
				}
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
			String isreturn = emrForm.getReturnbill();
			String paymode= emrForm.getPaymode();	
			int dontshowreqbill =0;
			//  07 aug 2018 when search for return bill or paymode then dont show requested list from opd and ipd
			if(isreturn==null){
				isreturn="0";
				dontshowreqbill =1;
			}
			if(paymode==null){
				paymode="0";
				dontshowreqbill =1;
			}
			if(payee==null){
				payee="0";
				dontshowreqbill =1;
			}
			if(actiontype!=null) {
				if(!actiontype.equals("")) {	
					String fdate=pharmacyDAO.getfakedatebyClientid(fakclientid);
					if(fdate!=null) {
						fdate=fdate.split(" ")[0];
					}
					
					if(fdate!=null) {
					 if(actiontype.equals("1")){
						fromdate=fdate;
						searchtext=fuhid;
					}
				  }
				}
				
			  }
			/*int count=prescriptionDAO.getAllPriscriptionCount(searchtext,fromdate,todate,location);
			pagination.setPreperties(count);*/
			
			//  11 AUg 2018
			//if its from return medicine (return with bill) 
			int isfromreturndash =0;
			if(isfromreturndashboard!=null){
				if(isfromreturndashboard.equals("1")){
					isfromreturndash =1;
				}
			}
			ArrayList<Priscription> priscriptionlist = new ArrayList<Priscription>();
			if(isfromreturndash>0){
				String hdnispharmacy=  request.getParameter("hdnispharmacy");
				String hdnphclientid = request.getParameter("hdnphclientid");
				String medids="";
				int ii=0;
				for(Priscription prisc:emrForm.getMedicines()){
			    	if(prisc==null){
			    		continue;
			    	}
			    	if(prisc.getId()==0){
			    		continue;
			    	}
			    	if(prisc.getReturnqty()==0){
			    		continue;
			    	}
			    	if(ii==0){
			    		medids = prisc.getProduct_id();
			    	}else{
			    		medids = medids+","+prisc.getProduct_id();
			    	}
			    	ii++;
				}
				
				int count =0;
				pagination.setPreperties(count);
				priscriptionlist = pharmacyDAO.getAllPharmacySaleListRefund(location,medids,hdnispharmacy,hdnphclientid,loginInfo);
				pagination.setTotal_records(priscriptionlist.size());
				emrForm.setTotalRecords(count);
				emrForm.setPagerecords(String.valueOf(pagination.getTotal_records()));
				emrForm.setPriscriptionlist(priscriptionlist);
				ArrayList<Product> requestedMedicineList=pharmacyDAO.getAllRequestedMedicine(fromdate, todate);
				emrForm.setRequestedMedicineList(requestedMedicineList); 
			}else{
				int count =pharmacyDAO.getAllPharmacySaleCount(searchtext,fromdate,todate,location,isreturn,paymode);
				pagination.setPreperties(count);
				//ArrayList<Priscription> priscriptionlist = prescriptionDAO.getAllPharmacyList(pagination,searchtext,fromdate,todate,ipdloc,opdloc,location);
				priscriptionlist = pharmacyDAO.getAllPharmacySaleList(pagination,searchtext,fromdate,todate,location,isreturn,paymode,dontshowreqbill,loginInfo,payee,tpid);
				pagination.setTotal_records(priscriptionlist.size());
				emrForm.setTotalRecords(count);
				emrForm.setPagerecords(String.valueOf(pagination.getTotal_records()));
				emrForm.setPriscriptionlist(priscriptionlist);
				ArrayList<Product> requestedMedicineList=pharmacyDAO.getAllRequestedMedicine(fromdate, todate);
				emrForm.setRequestedMedicineList(requestedMedicineList); 
			}
			
			
			/*double totalReceived=pharmacyDAO.getTotalRecived(fromdate,todate,location);
			double totalRefund=pharmacyDAO.getTotalRefund(fromdate,todate,location);
			double totalBalance=pharmacyDAO.getTotalBalance(fromdate,todate,location);
			emrForm.setTotalReceived(DateTimeUtils.changeFormat(totalReceived));
			emrForm.setTotalRefund(DateTimeUtils.changeFormat(totalRefund));
			emrForm.setTotalBalance(DateTimeUtils.changeFormat(totalBalance));*/
			
			double totalReceived=0;
 			double totalRefund=0;
 			double totalBalance=0;
 			int size = priscriptionlist.size();
			if (size > 0) {
				totalReceived = priscriptionlist.get(size - 1).getTotalReceived();
				totalRefund = priscriptionlist.get(size - 1).getTotalrefund();
				totalBalance = priscriptionlist.get(size - 1).getTotalBalance();
				
			}
 			emrForm.setTotalReceived(DateTimeUtils.changeFormat(totalReceived));
 			emrForm.setTotalRefund(DateTimeUtils.changeFormat(totalRefund));
 			emrForm.setTotalBalance(DateTimeUtils.changeFormat(totalBalance));
			
			
			fromdate=DateTimeUtils.getCommencingDate1(fromdate);
			todate=DateTimeUtils.getCommencingDate1(todate);
			
			emrForm.setFromdate(fromdate);
			emrForm.setTodate(todate);
			String date=DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone()).split(" ")[0];
			emrForm.setCommencing(DateTimeUtils.getCommencingDate1(date));
			
			String subcategoname = priscriptionlist.get(size - 1).getSubcategoryname();
			emrForm.setSubcategoryname(subcategoname);
			
			if(searchtext!=null){
				emrForm.setSearchText(searchtext);
			}
			emrForm.setReturnbill(isreturn);
			emrForm.setPaymode(paymode);
			
			/*if(loginInfo.getJobTitle().equals("Medical Store")){
				
				return "medicalstore";
			}*/
			//lokesh for showing letterhd in pharmacy
			boolean showletterhd=pharmacyDAO.isShowletterhd();
			 if(showletterhd){
				   session.setAttribute("showletterhd","true");
				   }else{
					   session.setAttribute("showletterhd","false"); 
				   }
			session.setAttribute("pagelimit", profile2.getPagelimit());
			
			
			UserProfile userProfile= userProfileDAO.getPharmacyUserDetails(loginInfo.getClinicid());
			emrForm.setClinicName(userProfile.getClinicname());
			emrForm.setClinicaddress(userProfile.getAddress());
			emrForm.setWebsiteUrl(userProfile.getWebsite());
			emrForm.setClinicemail(userProfile.getEmail());
			emrForm.setClinicphoneno(userProfile.getPhone());
		} catch (Exception e) {

			e.printStackTrace();
		} finally {
			connection.close();
		}

		return SUCCESS;
	}
	
	
	public String printprisc() throws Exception {
		if (!verifyLogin(request)) {

			return "login";
		}
		Connection connection = null;
		try{
			
			connection = Connection_provider.getconnection();
			EmrDAO emrDAO = new JDBCEmrDAO(connection);
			ClientDAO clientDAO = new JDBCClientDAO(connection);
			
			int selectedid = Integer.parseInt(request.getParameter("selectedid"));
			
			if(selectedid==0){
				selectedid = (Integer)session.getAttribute("saveparent");
			}
			
			Priscription priscription = emrDAO.getPriscriptionParentData(selectedid);
			session.setAttribute("parentpriscdata", priscription);
//			System.out.println(priscription.getClientId());
			String lastmodified="";
			if(priscription.getLastmodified()!=null){
				
				lastmodified=priscription.getLastmodified().split(" ")[0];
				priscription.setLastmodified(lastmodified);
			}
			
			
			Client client = clientDAO.getPatient(Integer.parseInt(priscription.getClientId()));
			String name = client.getTitle()+" "+client.getFirstName()+" "+client.getLastName();
			String date  = DateTimeUtils.getDashboardTodayDate(loginInfo.getCountry());
			
			String dobyear[] = client.getDob().split("/");
			String curryear[] = date.split("/");
			
			int  age = Integer.parseInt(curryear[2]) - Integer.parseInt(dobyear[2]);
			client.setAge(age);
			session.setAttribute("clientinfo", client);
			
			// get clinic info
			
			Clinic clinic = new Clinic();
			ClinicDAO clinicDAO  = new JDBCClinicDAO(connection);
			clinic = clinicDAO.getCliniclistDetails(loginInfo.getClinicid());
			
			Location location = clinicDAO.getRegisterdLication();
			clinic.setLocationname(location.getAddress());
			
			/*String curdatetime = DateTimeUtils.getPriscDatetime(loginInfo.getTimeZone());
			clinic.setCurDateTime(curdatetime);*/
			clinic.setCurDateTime(priscription.getLastmodified());
			
			session.setAttribute("clinicinfo", clinic);
			
			ArrayList<Priscription>priscList = emrDAO.getSelectedPriscList(Integer.toString(selectedid));
			session.setAttribute("authorisedPrisc", priscList);
			
			
			
			String ipdid = emrDAO.getpriscIpdId(selectedid);
			BedDao bedDao = new JDBCBedDao(connection);
			IpdDAO  ipdDAO = new JDBCIpdDAO(connection);
			Bed bed = bedDao.getEditIpdData(ipdid);
			
			emrForm.setIpdid(ipdid);
			if(ipdid==null || ipdid.equals("0")){
				priscription.setIpdid("0");
			}else{
				priscription.setIpdid(ipdid);
			}
			
			String wardname=ipdDAO.getIpdWardName(bed.getWardid());
			priscription.setWardname(wardname);
			String bedname = ipdDAO.getIpdBedName(bed.getBedid());
			priscription.setBedname(bedname);
			
			emrForm.setWardname(wardname);
			emrForm.setBedname(bedname);
		    emrForm.setId(selectedid);
			
		}catch(Exception e){
			e.printStackTrace();
		}finally {
			connection.close();
		}
		
		
		return "printprisc";
	}
	
	
	public String checkavailability() throws Exception{
		if (!verifyLogin(request)) {

			return "login";
		}
		Connection connection=null;
		try {
			connection=Connection_provider.getconnection();
			connection = Connection_provider.getconnection();
			EmrDAO emrDAO = new JDBCEmrDAO(connection);
			PharmacyDAO pharmacyDAO=new JDBCPharmacyDAO(connection);
			UserProfileDAO userProfileDAO=new JDBCUserProfileDAO(connection);
			ClientDAO clientDAO = new JDBCClientDAO(connection);
			
			String loc= (String) session.getAttribute("location");
			if(loc==null){
				loc="0";
			}
			emrForm.setLocation(loc);
			
			int selectedid=Integer.parseInt(request.getParameter("selectedid"));
			Priscription priscription = emrDAO.getPriscriptionParentData(selectedid);
			session.setAttribute("parentpriscdata", priscription);
//			System.out.println(priscription.getClientId());
			String lastmodified="";
			String time="";
			if(priscription.getLastmodified()!=null){
				String dateTime= priscription.getLastmodified();
				lastmodified=dateTime.split(" ")[0];
				time=dateTime.split(" ")[1];
				priscription.setLastmodified(lastmodified);
			}
			
			Client client = clientDAO.getPatient(Integer.parseInt(priscription.getClientId()));
			String name = client.getTitle()+" "+client.getFirstName()+" "+client.getLastName();
			emrForm.setFullname(name);
			String date  = DateTimeUtils.getDashboardTodayDate(loginInfo.getCountry());
			String todaydate=DateTimeUtils.getCommencingDatePicker(date);
			emrForm.setDateTime(todaydate);
			emrForm.setSelectedid(selectedid);
			
			try {

				String dobyear[] = client.getDob().split("/");
				String curryear[] = date.split("/");
				
				int  age = Integer.parseInt(curryear[2]) - Integer.parseInt(dobyear[2]);
				client.setAge(age);
				String agedate=client.getGender()+"/"+age;
				emrForm.setAgeandgender(agedate);
			} catch (Exception e) {

			}
			session.setAttribute("clientinfo", client);
			
			// get clinic info
			
			Clinic clinic = new Clinic();
			ClinicDAO clinicDAO  = new JDBCClinicDAO(connection);
			clinic = clinicDAO.getCliniclistDetails(loginInfo.getClinicid());
			
			Location location = clinicDAO.getRegisterdLication();
			clinic.setLocationname(location.getAddress());
			
			if(priscription.getPrectionerid()!=null){
				
				if(!priscription.getPrectionerid().equals("")){
					 
					UserProfile userProfile=userProfileDAO.getUserprofileDetails(Integer.parseInt(priscription.getPrectionerid()));
					String fullname= userProfile.getInitial()+" "+userProfile.getFirstname()+" "+userProfile.getLastname();
					emrForm.setPractitionerName(fullname);
				}
			}
			
			clinic.setCurDateTime(priscription.getLastmodified());
			ArrayList<Priscription> priscList = pharmacyDAO.getPriscPharmacyAvailablity(Integer.toString(selectedid));
			
			/*ArrayList<Priscription> prisMoleculessList = pharmacyDAO.getPriscAvailableByMolecules(Integer.toString(selectedid));
			emrForm.setPrisMoleculessList(prisMoleculessList);*/
			emrForm.setPriscriptionlist(priscList);
			session.setAttribute("listAvailable", priscList);
			
			double subtotal=0;
			for(Priscription priscription2:priscList){
				
				 subtotal=priscription2.getSubtotal();
			}
			double vat =subtotal*6/100;
			emrForm.setVat(DateTimeUtils.changeFormat(vat));
			emrForm.setDiscount("0.0");
			String ipdid = emrDAO.getpriscIpdId(selectedid);
			BedDao bedDao = new JDBCBedDao(connection);
			IpdDAO  ipdDAO = new JDBCIpdDAO(connection);
			Bed bed = bedDao.getEditIpdData(ipdid);
			
			emrForm.setIpdid(ipdid);
			if(ipdid==null || ipdid.equals("0")){
				priscription.setIpdid("0");
			}else{
				priscription.setIpdid(ipdid);
			}
			
			String wardname=ipdDAO.getIpdWardName(bed.getWardid());
			priscription.setWardname(wardname);
			String bedname = ipdDAO.getIpdBedName(bed.getBedid());
			priscription.setBedname(bedname);
			
			String payee="Self";
			if(client.getWhopay()!=null){
				if(client.getWhopay().equals("Third Party")){
					 payee= client.getTpDetails().getCompanyName();
				}
			}
			
			emrForm.setWhopay(payee);
			emrForm.setAbrivationid(client.getAbrivationid());
			emrForm.setInvoiceTime(time);
			String prebal = pharmacyDAO.getbalofpatient(priscription.getClientId());
			emrForm.setPrebal(prebal);
			emrForm.setWardname(wardname);
			emrForm.setBedname(bedname);
			String wardbed=wardname+"/"+bedname;
			emrForm.setWardname(wardbed);
			emrForm.setIpdidcheck(priscription.getIpdid());
			emrForm.setIpdid(priscription.getIpdid());
		    emrForm.setOpdid(priscription.getClientId());
		    emrForm.setHdnphclientid(priscription.getClientId());
		    emrForm.setHdnispharmacy("0");
		    emrForm.setMobno(client.getMobNo());
		    emrForm.setPriscid(String.valueOf(selectedid));
			//String prebalance = pharmacyDAO.getbalancepri();
		    
		    InventoryProductDAO inventoryProductDAO=new JDBCInventoryProductDAO(connection);
			ArrayList<Product> inventoryPriscList= inventoryProductDAO.geProductList("2",loc);
			emrForm.setInventoryPriscList(inventoryPriscList);
			
			Priscription master = pharmacyDAO.getPharacyUsrLInfo(loginInfo.getUserId());
			
			if(master.getAccess_back_date()==1){
				 emrForm.setBack_date_access("1");
			}
			if(master.getTpbill()==1){
				emrForm.setTp_bill_access("1");
			}
		    
			
		} catch (Exception e) {

			e.printStackTrace();
		}
		finally {
			connection.close();
		}
		
		
		return "checkavailability";
	}



	public String credit() throws Exception {
		
		if (!verifyLogin(request)) {

			return "login";
		}
		Connection connection=null;
		try {
			
			connection=Connection_provider.getconnection();
			EmrDAO emrDAO=new JDBCEmrDAO(connection);
			ClientDAO clientDAO=new JDBCClientDAO(connection);
			PharmacyDAO pharmacyDAO=new JDBCPharmacyDAO(connection);
			UserProfileDAO profileDAO=new JDBCUserProfileDAO(connection);
			InventoryProductDAO productDAO=new JDBCInventoryProductDAO(connection);
		    int selectedid=emrForm.getSelectedid();
		    String vat=emrForm.getVat();
		    String paymode=emrForm.getPaymode();
		    
		    String location =(String) session.getAttribute("location");
		    if(location==null){
		    	location="0";
		    }
		    
		    if(emrForm.getDiscount()!=null){
		    	 
		    	if(emrForm.getDiscount().equals("")){
		    		
		    		emrForm.setDiscount("0");
		    	}
		    }
		    else {
		    	emrForm.setDiscount("0");
		    }
		    
		    double discount=Double.parseDouble(emrForm.getDiscount());
		    String notes=emrForm.getNotes();
		    String total=emrForm.getTotal();
		    
		    
		    Priscription priscription = emrDAO.getPriscriptionParentData(selectedid);
		    Client client=clientDAO.getClientDetails(priscription.getClientId());
		    String clientname=client.getTitle()+" "+client.getFirstName()+" "+client.getLastName();
		    CompleteAppointment appointment=new CompleteAppointment();
		    appointment.setClientId(priscription.getClientId());
		    appointment.setPayBuy(client.getWhopay());
		    appointment.setCharges(""+total+"");
		    appointment.setChargeType("");
		    appointment.setVat(Double.parseDouble(vat));
		    appointment.setDiscount(discount);
		    appointment.setTotal(Double.parseDouble(total));
		    appointment.setNotes(notes);
		    appointment.setPriscid(selectedid);
		    
		    String date=DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone()).split(" ")[0];
		    appointment.setInvoiceDate(date);
		    appointment.setUserid(loginInfo.getUserId());
		    
		    int billno=pharmacyDAO.addMedicineBill(appointment);
		    
		    //update in priscription 
		    int result=pharmacyDAO.updatePriscEmrBill(billno,String.valueOf(selectedid));
		    
		    //update status
		    //int result=pharmacyDAO.updateDeliverStatus(, String.valueOf(selectedid));
		    String tpid="";
		    String payby="0";
		    if(client.getWhopay()!=null){
		    	
		    	if(client.getWhopay().equals("Client")){
		    		   tpid="0";
		    		   payby="0";
		    	}else {
		    		payby="1";
		    		tpid=client.getTypeName();
		    	}
		    }else {
		    	tpid="0";
		    	payby="0";
		    }
		    
		    
		    for(Priscription prisc:emrForm.getMedicines()){
		    	
		    	priscription.setMdicinenameid(prisc.getMdicinenameid());
		    	
		    	Product product=productDAO.getMedicineProductDetails(priscription.getMdicinenameid());
		    	priscription.setSaleqty(prisc.getSaleqty());
		    	priscription.setReqqty(prisc.getReqqty());
		    	priscription.setMrp(product.getSale_price());
		    	priscription.setWhopay(payby);
		    	priscription.setTpid(tpid);
		    	UserProfile userProfile= profileDAO.getUserprofileDetails(Integer.parseInt(priscription.getPrectionerid()));
		    	priscription.setFullname(userProfile.getFullname());
		    	priscription.setClientname(clientname);
		    	priscription.setDate(date);
		    	result=pharmacyDAO.addMedicineCharges(priscription,billno);
		    	
		    	//update inventory
		    	result=productDAO.updateMedicineQty(priscription.getSaleqty(),priscription.getMdicinenameid(),0);
		    	
		     }
		    double tot=Double.parseDouble(total);
		   
		  //  19 Sep 2018 generate pharmacy payment Seq No according location
		   int paymentseqno = pharmacyDAO.getPharmacyPaymentSeqNo(location);
		   paymentseqno = paymentseqno+1;
		   result=pharmacyDAO.recordPaymentMedicine(String.valueOf(billno),tot,paymode,date,appointment.getClientId(),"0",discount,notes,appointment.getPclientid(),loginInfo.getUserId(),location,0,paymentseqno);
		   int paymentid = result; 
		  CompleteAppointment completeAppointment=pharmacyDAO.getBillDetails(billno);
		  paymode=pharmacyDAO.getBillPaymode(billno);
		  ArrayList<Priscription> medicineChargeList=pharmacyDAO.getMedicineChargesList(billno,loginInfo);
		  double subtotal=0;
		  for(Priscription p:medicineChargeList){
			  
			  subtotal=subtotal+(Double.parseDouble(p.getMrp())*p.getSaleqty());
		  }
		  
		  String temp=String.valueOf(subtotal);
		  
		  emrForm.setSubtotal(DateTimeUtils.changeStringFormat(temp));
		  emrForm.setMedicineChargeList(medicineChargeList);
		  emrForm.setClientId(completeAppointment.getClientId());
		  String tt=String.valueOf(completeAppointment.getTotal());
		  emrForm.setTotal(DateTimeUtils.changeStringFormat(tt));
		  String disc=String.valueOf(completeAppointment.getDiscount());
		  emrForm.setDiscount(DateTimeUtils.changeStringFormat(disc));
		  emrForm.setVat(DateTimeUtils.changeFormat(completeAppointment.getVat()));
		  emrForm.setBillno(billno);
		  emrForm.setPaymode(paymode);
		    
		    
		} catch (Exception e) {

			e.printStackTrace();
			
		}finally{
			connection.close();
		}
		
		
		return "creditbill";
		
	}

	
public String viewbill() throws Exception{
	if (!verifyLogin(request)) {

		return "login";
	}
		Connection connection=null;
		try {
			
			connection=Connection_provider.getconnection();
			PharmacyDAO pharmacyDAO=new JDBCPharmacyDAO(connection);
			EmrDAO emrDAO=new JDBCEmrDAO(connection);
			ClientDAO clientDAO=new JDBCClientDAO(connection);
			UserProfileDAO userProfileDAO=new JDBCUserProfileDAO(connection);
			BedDao bedDao = new JDBCBedDao(connection);
			String userid = loginInfo.getUserId();
			Priscription priscription1 = pharmacyDAO.getPharacyUsrLInfo(userid);
			String oldbal="0";
			
			if (priscription1.getSMS()!=null)
				if (priscription1.getSMS().equals("0"))
					loginInfo.setSMS_authority(false);
				else
					loginInfo.setSMS_authority(true);
			
			if (!loginInfo.isIslogin()) {
				if(loginInfo.getUserType()==2){
					
				}
				else{
					addActionError("Inactive pharmacy user");
					return "loginerror";
				}
			}
			
			
			String bill=request.getParameter("billno");
			String selected=request.getParameter("selectedid");
			
			int billno=0;
			int selectedid =0;
			int shownavigation=1;
			
			if(bill!=null){
				
				if(bill.equals("")){
					billno=(Integer)session.getAttribute("billno");
				}else {
					billno=Integer.parseInt(bill);
					shownavigation=0;
				}
				
			} else {
				billno=(Integer)session.getAttribute("billno");
					
			}
			if(selected!=null){
				
				if(selected.equals("")){
					selectedid=(Integer)session.getAttribute("selectedid");
				}else {
					selectedid=Integer.parseInt(selected);
				}
			} else {
				selectedid=(Integer)session.getAttribute("selectedid");
			}
			emrForm.setShownavigation(shownavigation);
			/*if(shownavigation==1){
				ArrayList<Breadcrumbs> indentflowlist = new ArrayList<Breadcrumbs>();
				ArrayList<Breadcrumbs> indentflowlisttemp = new ArrayList<Breadcrumbs>();
				if (session.getAttribute("indentflowlist") != null) {
					indentflowlisttemp = (ArrayList<Breadcrumbs>) session.getAttribute("indentflowlist");
				}
				boolean isavilablemodule= false;
				int modulecount =0;
				for (Breadcrumbs breadcrumbs : indentflowlisttemp) {
					breadcrumbs.setIscurrent(false);
					breadcrumbs.setSqno(modulecount);
					modulecount++;
					if(breadcrumbs.getName().equals("Pharmacy Print")){
						isavilablemodule =true;
						breadcrumbs.setIscurrent(true);
						indentflowlist.add(breadcrumbs);
						break;
					}else{
						indentflowlist.add(breadcrumbs);
					}
				}
				if(!isavilablemodule){
					Breadcrumbs breadcrumbs = new Breadcrumbs();
					breadcrumbs.setName("Pharmacy Print");
					breadcrumbs.setOn(false);
					breadcrumbs.setSqno(modulecount);
					breadcrumbs.setUrllink("viewbillPharmacy");
					breadcrumbs.setIscurrent(true);
					breadcrumbs.setShowingname("Pharmacy Bill Print");
					indentflowlist.add(breadcrumbs);
				}
				session.setAttribute("indentflowlist",indentflowlist);
	 			
			}*/
			
			
			CompleteAppointment completeAppointment=pharmacyDAO.getBillDetails(billno);
			String reqfromlocation ="";
			double balance=0;
			if(selectedid>0){ //from HIS
					
						Priscription priscription = emrDAO.getPriscriptionParentData(selectedid);
						session.setAttribute("parentpriscdata", priscription);
//						System.out.println(priscription.getClientId());
						String lastmodified="";
						if(priscription.getLastmodified()!=null){
							
							lastmodified=priscription.getLastmodified().split(" ")[0];
							priscription.setLastmodified(lastmodified);
						}
						
						
						Client client = clientDAO.getPatient(Integer.parseInt(priscription.getClientId()));
						balance = Double.parseDouble(client.getBalance());
						oldbal= client.getBalance();
						try {
							String name = client.getTitle()+" "+client.getFirstName()+" "+client.getMiddlename()+" "+client.getLastName();
							String date  = DateTimeUtils.getDashboardTodayDate(loginInfo.getCountry());
							String dobyear[] = client.getDob().split("/");
							String curryear[] = date.split("/");
							
							int  age = Integer.parseInt(curryear[2]) - Integer.parseInt(dobyear[2]);
							client.setAge(age);
						} catch (Exception e) {
						    e.printStackTrace();
						}
						
						session.setAttribute("clientinfo", client);
						emrForm.setAbrivationid(client.getAbrivationid());
						// get clinic info
						Clinic clinic = new Clinic();
						ClinicDAO clinicDAO  = new JDBCClinicDAO(connection);
						clinic = clinicDAO.getCliniclistDetails(loginInfo.getClinicid());
						
						Location location = clinicDAO.getRegisterdLication();
						clinic.setLocationname(location.getAddress());
						
						/*String curdatetime = DateTimeUtils.getPriscDatetime(loginInfo.getTimeZone());
						clinic.setCurDateTime(curdatetime);*/
						clinic.setCurDateTime(priscription.getLastmodified());
						
						emrForm.setCommencing(DateTimeUtils.getCommencingDate1(priscription.getLastmodified()));
						session.setAttribute("clinicinfo", clinic);
						
						String ipdid = emrDAO.getpriscIpdId(selectedid);
						//BedDao bedDao = new JDBCBedDao(connection);
						IpdDAO  ipdDAO = new JDBCIpdDAO(connection);
						Bed bed = bedDao.getEditIpdData(ipdid);
						
						emrForm.setIpdid(ipdid);
						emrForm.setAbrivationid(client.getAbrivationid());
						if(ipdid==null || ipdid.equals("0")){
							priscription.setIpdid("0");
							String loc=client.getTown();
							emrForm.setWardname(loc);
							emrForm.setAddr(loc);
							
						}else{
							priscription.setIpdid(ipdid);
							String wardname=ipdDAO.getIpdWardName(bed.getWardid());
							priscription.setWardname(wardname);
							String bedname = ipdDAO.getIpdBedName(bed.getBedid());
							priscription.setBedname(bedname);
							
							emrForm.setWardname(wardname);
							emrForm.setBedname(bedname);
							String wardbed=wardname+"/"+bedname;
							emrForm.setWardname(wardbed);
							emrForm.setAddr(wardname);
						}
						
						
						if(priscription.getPrectionerid()!=null){
							
							if(!priscription.getPrectionerid().equals("")){
								 
								UserProfile userProfile=userProfileDAO.getUserprofileDetails(Integer.parseInt(priscription.getPrectionerid()));
								emrForm.setPractitionerName(userProfile.getFullname());
								emrForm.setDoctor_address(userProfile.getAddress());
								emrForm.setDoctor_qualification(userProfile.getQualification());
							}
						}
						
						
			}else { //from Pharmacy Patient
				
				String pclientid=pharmacyDAO.getPharmacyClientidFromBill(billno);
				
				if(!pclientid.equals("0")){		
					reqfromlocation ="0";
					Priscription priscription=pharmacyDAO.getPharmacyPatient(pclientid);
					
					if(!DateTimeUtils.numberCheck(priscription.getAbrivationid()).equals("0")){
						emrForm.setAbrivationid(priscription.getAbrivationid());
					}else{
						emrForm.setAbrivationid(pclientid);
					}
					
					oldbal= priscription.getBalance();
					Clinic clinic = new Clinic();
					ClinicDAO clinicDAO  = new JDBCClinicDAO(connection);
					clinic = clinicDAO.getCliniclistDetails(loginInfo.getClinicid());
					Client client=new Client();
					client.setFirstName(priscription.getFullname());
					try {
						
						String str[]=priscription.getAgeandgender().split("/");
						client.setGender(priscription.getGender());
						client.setAge(Integer.parseInt(priscription.getAge()));
						
						
					} catch (Exception e) {

						e.printStackTrace();
					}
					Location location = clinicDAO.getRegisterdLication();
					client.setMobNo(priscription.getMobile());
					client.setGstin(DateTimeUtils.isNull(priscription.getGstin()));
					client.setDruglic(DateTimeUtils.isNull(priscription.getDlno()));
					clinic.setLocationname(location.getAddress());
					emrForm.setWardname(priscription.getAddress());
					emrForm.setAddr(priscription.getAddress());
					emrForm.setAgeandgender(priscription.getAgeandgender());
					emrForm.setClinicLogo(clinic.getUserImageFileName());
					emrForm.setPractitionerName(priscription.getPractitionername());
				
					clinic.setCurDateTime(DateTimeUtils.getCommencingDate1(priscription.getLastmodified()));
					emrForm.setCommencing(DateTimeUtils.getCommencingDate1(priscription.getLastmodified()));
					session.setAttribute("clientinfo", client);
					session.setAttribute("clinicinfo", clinic);
				}else{
					String ipdopd=pharmacyDAO.getClientIDFromBillNo(billno);
					Client client = clientDAO.getClientDetails(ipdopd);
					try {
	
						String name = client.getTitle()+" "+client.getFirstName()+" "+client.getMiddlename()+" "+client.getLastName();
						String date  = DateTimeUtils.getDashboardTodayDate(loginInfo.getCountry());
						String dobyear[] = client.getDob().split("/");
						String curryear[] = date.split("/");
						int  age = Integer.parseInt(curryear[2]) - Integer.parseInt(dobyear[2]);
						client.setAge(age);
					} catch (Exception e) {
					
					}
					session.setAttribute("clientinfo", client);
					// get clinic info
					Clinic clinic = new Clinic();
					ClinicDAO clinicDAO  = new JDBCClinicDAO(connection);
					clinic = clinicDAO.getCliniclistDetails(loginInfo.getClinicid());
					Location location = clinicDAO.getRegisterdLication();
					clinic.setLocationname(location.getAddress());
					/*String curdatetime = DateTimeUtils.getPriscDatetime(loginInfo.getTimeZone());
					clinic.setCurDateTime(curdatetime);*/
					//clinic.setCurDateTime(priscription.getLastmodified());
				
					//emrForm.setCommencing(DateTimeUtils.getCommencingDate1(priscription.getLastmodified()));
					session.setAttribute("clinicinfo", clinic);
					emrForm.setAbrivationid(client.getAbrivationid());
					emrForm.setWardname(client.getAddress());
					emrForm.setAddr(client.getAddress());
					//emrForm.setAgeandgender();
					emrForm.setClinicLogo(clinic.getUserImageFileName());
					String ipdid = "0";
					String bedid="0";
					String wardid="0";
					if(completeAppointment.getPhar_ipdid()!=null){
						if(!completeAppointment.getPhar_ipdid().equals("0") || !completeAppointment.getPhar_ipdid().equals("")){
							ipdid = completeAppointment.getPhar_ipdid();
							bedid = completeAppointment.getBedid();
							wardid = completeAppointment.getWardid();
						}else{
							ipdid = pharmacyDAO.getIpdIdFromClientID(Integer.parseInt(ipdopd));
						}
					}else{
						ipdid = pharmacyDAO.getIpdIdFromClientID(Integer.parseInt(ipdopd));
						
					}
					
					
					String pract_name ="";
					String ipdoropd = "0";
					if(ipdid.equals("0")){
						reqfromlocation ="1";
						ipdoropd ="0";
						pract_name = pharmacyDAO.getappointmentinfo(Integer.parseInt(ipdopd));
						if(!completeAppointment.equals("0")){
							String reqfromlocationnew = pharmacyDAO.getRequestFromPriscriptionBill(""+billno);
							if(reqfromlocationnew.equals("2")){
								reqfromlocation ="3";
							}
						}
						
					}else{
						reqfromlocation ="2";
						Bed  bed = bedDao.getEditIpdData(ipdid);
						UserProfileDAO profileDAO = new JDBCUserProfileDAO(connection);
						IpdDAO ipdDAO = new JDBCIpdDAO(connection);
						pract_name = profileDAO.getName(bed.getPractitionerid());
						String wardname="";
						String bedname="";
						if(bedid.equals("0")){
							bedname = ipdDAO.getIpdBedName(bed.getBedid());
						}else{
							bedname = ipdDAO.getIpdBedName(bedid);
						}
						
						if(wardid.equals("0")){
							wardname = ipdDAO.getIpdWardName(bed.getWardid());
						}else{
							wardname = ipdDAO.getIpdWardName(wardid);
						}
						
						ipdoropd = ipdid;
						String wardbed = wardname +"/"+ bedname;
						emrForm.setWardbed(wardbed);
					}
					emrForm.setPractitionerName(pract_name);
					
					/*String ipdoropd = "0";
					if(ipdid.equals("0")){
						ipdoropd ="0";
					}else{
						IpdDAO ipdDAO = new JDBCIpdDAO(connection);
						Bed  bed = bedDao.getEditIpdData(ipdid);
						String wardname = ipdDAO.getIpdWardName(bed.getWardid());
						String bedname = ipdDAO.getIpdBedName(bed.getBedid());
						ipdoropd = ipdid;
						String wardbed = wardname +"/"+ bedname;
						emrForm.setWardbed(wardbed);
					}*/
					emrForm.setIpdoropd(ipdoropd);
					ThirdPartyDAO thirdPartyDAO = new JDBCThirdPartyDAO(connection);
					String thirdPartyCompanyName="0";
					if(client.getTypeName()==null){
						client.setTypeName("0");
					}
					if(!client.getTypeName().equals("0")){
						ThirdParty thirdParty =thirdPartyDAO.getThirdPartyDetails(client.getTypeName());
						thirdPartyCompanyName = thirdParty.getCompanyName();
					}else{
						thirdPartyCompanyName ="0";
					}
					emrForm.setThirdPartyCompanyName(thirdPartyCompanyName);
					
					emrForm.setReqfromlocation(reqfromlocation);
					
					
				}
				
				
			}
			if(!DateTimeUtils.isNull(completeAppointment.getPractitionerId()).equals("")){
				UserProfile userProfile=userProfileDAO.getUserprofileDetails(Integer.parseInt(completeAppointment.getPractitionerId()));
				emrForm.setPractitionerName(userProfile.getFullname());
				emrForm.setDoctor_address(DateTimeUtils.isNull(userProfile.getAddress()));
				emrForm.setDoctor_qualification(DateTimeUtils.isNull(userProfile.getQualification()));
			}
			
			
			//String paymode=pharmacyDAO.getBillPaymode(billno);
			String paymode=pharmacyDAO.getBillPaymodeNew(billno);
			String paynote= pharmacyDAO.getBilPayNote(billno);
			double payamt= pharmacyDAO.getPayAmount(billno);
			String cardBill="";
			if(paymode!=null){
				
				if(paymode.equals("Card")){
					
					cardBill = pharmacyDAO.getCardBillInvoice(billno);
				}
			}
			emrForm.setCardBill(cardBill);
			emrForm.setPayamt(DateTimeUtils.changeFormat(payamt));
			double refund=pharmacyDAO.getRefundAmt(billno);
			 
			 
			 balance =Double.parseDouble(completeAppointment.getBalance());
			  ArrayList<Priscription> medicineChargeList=pharmacyDAO.getMedicineChargesList(billno,loginInfo);
			  
			  double subtotal=0;
			  for(Priscription p:medicineChargeList){
				  if(loginInfo.isInfinyte() || loginInfo.isSaimed()) {
					  subtotal=p.getSubtotal();  
					  //emrForm.setTotalstr(p.getAv());
				  }else {
					  subtotal=subtotal+(Double.parseDouble(p.getMrp())*p.getSaleqty());
				  }
				  session.setAttribute("discountper", p.getDiscount_perproduct());
				  //subtotal=subtotal+(Double.parseDouble(p.getMrp())*p.getSaleqty());
				  
			  }
			 
			 /* if(loginInfo.getIskunal()==1){
				  int pagelimit= loginInfo.getPagelimitpharmacy();
				  ArrayList<Priscription>[] l= new ArrayList[((medicineChargeList.size()/pagelimit)+1)];
				
				  int newpagelimit=0;
				  int j=0;
				  for(int i=0;i<=medicineChargeList.size();i=(i+pagelimit)){
					  
					
						  newpagelimit=(pagelimit+newpagelimit)-1;
					 
					  ArrayList<Priscription> list= new ArrayList<Priscription>(medicineChargeList.subList(i, newpagelimit));
					  l[j]=list;
					  j=j+1;
					  
				  }
				  for(ArrayList<Priscription> list:l){
					  
				  }
			  }
			*/ 
			  emrForm.setDummybillno(completeAppointment.getDummybillno());
			  String str=DateTimeUtils.changeFormat(subtotal);
			  emrForm.setSubtotal(str);
			  emrForm.setNotes(completeAppointment.getNotes()); 
			  String lo= pharmacyDAO.getLocationName(completeAppointment.getLocation());
			  emrForm.setLocation(lo);
			  emrForm.setCommencing(DateTimeUtils.getCommencingDate1(completeAppointment.getInvoiceDate()));
			  emrForm.setInvoiceTime(completeAppointment.getInvoiceTime());
			  emrForm.setMedicineChargeList(medicineChargeList);
			  emrForm.setClientId(completeAppointment.getClientId());
			  String t=DateTimeUtils.changeFormat(completeAppointment.getTotal());
			  emrForm.setTotal(t);
			  
			  if(completeAppointment.getIsreturn()>0){
				    emrForm.setPayamt(DateTimeUtils.changeFormat(completeAppointment.getTotal()));
				    emrForm.setIsreturn(1);
				    if(completeAppointment.getTotal()==0){
				      emrForm.setPayamt(DateTimeUtils.changeFormat(completeAppointment.getRefundAmt()));
				    }
				    emrForm.setRefundid(completeAppointment.getRefundid());
				    
			  } else {
				   emrForm.setIsreturn(0);
				   emrForm.setPayamt(DateTimeUtils.changeFormat(payamt));
				   emrForm.setRefundid(0);
			  }
			  
			  
			  emrForm.setCgst(completeAppointment.getCgst());
			  emrForm.setSgst(completeAppointment.getSgst());
			  emrForm.setUserid(loginInfo.getUserId());
			  emrForm.setEdited(completeAppointment.getEdited());
			  emrForm.setEdit_note(completeAppointment.getEdit_note());
			  emrForm.setCreatedby(completeAppointment.getUserid());
			  String ddmm[]=DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone()).split(" "); 
			  
			  emrForm.setBill_edituserid(completeAppointment.getBill_edituserid());
			  emrForm.setBill_editdatetime(completeAppointment.getBill_editdatetime());
			  
			  emrForm.setDateTime(DateTimeUtils.getCommencingDate1(ddmm[0])+" "+ddmm[1]);
			  
			  double gst= Double.parseDouble(completeAppointment.getCgst());
			  
			  emrForm.setGst(DateTimeUtils.changeFormat(gst*2));
			  emrForm.setDiscount(DateTimeUtils.changeStringFormat(String.valueOf(completeAppointment.getDiscount())));
			  emrForm.setVat(String.valueOf(completeAppointment.getVat()));
			  emrForm.setBillno(billno);
			  emrForm.setSelectedid(selectedid);
			  if(paynote!=null){
				  if(paynote.equals("")){
					  emrForm.setPaymode(paymode);
					  emrForm.setMemo(paymode);
				  } else {
					  paymode=paymode+" ("+paynote+")";
					  emrForm.setPaymode(paymode);
					  emrForm.setMemo(paymode);
				  }
			  } else {
				  emrForm.setPaymode(paymode);
				  emrForm.setMemo(paymode);
			  }
			  
			  emrForm.setRefundamt(DateTimeUtils.changeFormat(refund));
			  emrForm.setBalance(DateTimeUtils.changeFormat(balance));
			  emrForm.setTpid(completeAppointment.getTpid());
			  if(completeAppointment.getTpid()!=null){
				  
				  if(completeAppointment.getTpid().equals("1")){
					  emrForm.setThirdPartyCompanyName("Third Party");
				  }
			  }
			  
			  
			  if(completeAppointment.getIsreturn()>0){
				  //emrForm.setBalance(oldbal);
				  String oldbillid = String.valueOf(completeAppointment.getReturnbillid());
				  emrForm.setReference(oldbillid);
				  double total=0;
				  total = Double.parseDouble(t);
				 /* if(!t.equals("0")){
					  emrForm.setTotal(t);
				  } else {
					  emrForm.setTotal(DateTimeUtils.changeFormat(completeAppointment.getRefundAmt()));
				  }*/
				  if(total>0){
					  emrForm.setTotal(t);
				  }else{
					  emrForm.setTotal(DateTimeUtils.changeFormat(completeAppointment.getRefundAmt()));
				  }
				  //emrForm.setDiscount("0.00");
			  }
			  
			  // new Header and Footer
			  
			  UserProfile userProfile= userProfileDAO.getPharmacyUserDetails(loginInfo.getClinicid());
			  emrForm.setClinicName(userProfile.getClinicname());
			  emrForm.setClinicaddress(userProfile.getAddress());
			  emrForm.setLandLine(userProfile.getPhone());
			  emrForm.setFullname(userProfile.getFullname());
			  emrForm.setEmail(userProfile.getEmail());
			  emrForm.setPlace(userProfile.getCity());
			  emrForm.setWebsiteUrl(userProfile.getWebsite());
			  emrForm.setInstruction1(userProfile.getInstruction1());
			  emrForm.setInstruction2(userProfile.getInstruction2());
			  emrForm.setInstruction3(userProfile.getInstruction3());
			  
			  emrForm.setInstruction4(userProfile.getInstruction4());
			  emrForm.setDlno(userProfile.getDlno());
			  emrForm.setDeleted(completeAppointment.getDeleted());
			  emrForm.setTinno(userProfile.getTinno());
			  
			  //casper10-12-2018
			  Priscription gstdetails= pharmacyDAO.getBifurcationOfPharmacyBill(String.valueOf(billno));
			  emrForm.setZerogst(gstdetails.getZerocgst());
			  emrForm.setFivegst(gstdetails.getFivecgst());
			  emrForm.setTwelvegst(gstdetails.getTweelvecgst());
			  emrForm.setEighteen(gstdetails.getEighteencgst());
			  emrForm.setTwentyeight(gstdetails.getEighttwocgst());
			  emrForm.setTotalgst(gstdetails.getZerocgst()+gstdetails.getFivecgst()+gstdetails.getTweelvecgst()+gstdetails.getEighteencgst()+gstdetails.getEighttwocgst());
			  emrForm.setZeroamt(gstdetails.getZeropricemed());
			  emrForm.setFiveamt(gstdetails.getFivepricemed());
			  emrForm.setTwelveamt(gstdetails.getTweelvepricemed());
			  emrForm.setEighteenamt(gstdetails.getEighteenpricemed());
			  emrForm.setTwentyeightamt(gstdetails.getEighttwopricemed()); 
			  emrForm.setTotalnetgstamt(gstdetails.getTotalamt());
			  emrForm.setTotalgst(Math.round(emrForm.getTotalgst()*100.0)/100.0);
			  emrForm.setZeromrp(Math.round((emrForm.getZeroamt()-emrForm.getZerogst())*100.0)/100.0);
			  emrForm.setFivemrp(Math.round((emrForm.getFiveamt()-emrForm.getFivegst())*100.0)/100.0);
			  emrForm.setTwelvemrp(Math.round((emrForm.getTwelveamt()-emrForm.getTwelvegst())*100.0)/100.0);
			  emrForm.setEightteenmrp(Math.round((emrForm.getEighteenamt()-emrForm.getEighteen())*100.0)/100.0);
			  emrForm.setTwentyeightmrp(Math.round((emrForm.getTwentyeightamt()-emrForm.getTwentyeight())*100.0)/100.0);
			  emrForm.setTotalnmrp(Math.round((emrForm.getTotalnetgstamt()-emrForm.getTotalgst())*100.0)/100.0);
			  
			  
			  if(!DateTimeUtils.isNull(completeAppointment.getPractitionerName()).equals("")){
				  emrForm.setPractitionerName(completeAppointment.getPractitionerName());
			  }
			 
			  
			  if(loginInfo.getIskunal()==1){
					SimpleDateFormat dateFormat=new SimpleDateFormat("dd-MM-yyyy");
					Date date=dateFormat.parse(emrForm.getCommencing());
					
					SimpleDateFormat format=new SimpleDateFormat("dd-MMM-yyyy");
					String nowdate=format.format(date);
					emrForm.setCommencing(nowdate);
				}
			  
			  boolean showletterhd=pharmacyDAO.isShowletterhd();
				 if(showletterhd){
					   session.setAttribute("showletterhd","true");
					   }else{
						   session.setAttribute("showletterhd","false"); 
					   }
				 
			if(loginInfo.getIskunal()==1){
				return "shortpharmacybill";
			}else{
				 if(userProfile.getPrintType()!=null){
					  
					    if(userProfile.getPrintType().equals("1")){
					    	if(loginInfo.isIsdotmatrix()){
					    		return "bigBill";
					    	}else{
					    		return "bigbilllazer";
					    	}
					    	 
					    }
					  
				  } 
			}
			   
			  
		} catch (Exception e) {

			e.printStackTrace();
		}
		finally{
			connection.close();
		}
		
		//return "creditbill";
		return "bigbilllazer";
	}
	

  public String recordcash() throws Exception {
	  if (!verifyLogin(request)) {

			return "login";
		}
	Connection connection=null;  
	try {
		connection=Connection_provider.getconnection();
		EmrDAO emrDAO=new JDBCEmrDAO(connection);
		ClientDAO clientDAO=new JDBCClientDAO(connection);
		PharmacyDAO pharmacyDAO=new JDBCPharmacyDAO(connection);
		UserProfileDAO profileDAO=new JDBCUserProfileDAO(connection);
		InventoryProductDAO productDAO=new JDBCInventoryProductDAO(connection);
	    int selectedid=emrForm.getSelectedid();
	    String paymode=emrForm.getPaymode();
	    String bill=request.getParameter("billno"); 
	    String balance = emrForm.getBalance();
	    String tbalance=balance;
	    String previous_balance = emrForm.getPrevious_balance();
	    String notes="";
	    String location =(String) session.getAttribute("location");
	    if(location==null){
	    	location="0";
	    }
	    if(bill!=null){
	    	 
	    	if(bill.equals("")){
	    		 bill="0";
	    	}
	    }
	    else {
	    	bill="0";
	    }
	    
		
		if(paymode!=null){
			 
			if(paymode.equals("Card")){
				
				notes= request.getParameter("card");
			}
		}
	    
	    int billno=Integer.parseInt(bill);
	    String vat=emrForm.getVat();
	    
	    if(emrForm.getDiscount()!=null){
	    	 
	    	if(emrForm.getDiscount().equals("")){
	    		
	    		emrForm.setDiscount("0");
	    	}
	    }
	    else {
	    	emrForm.setDiscount("0");
	    }
	    
	    double discount=Double.parseDouble(emrForm.getDiscount());
	    //notes=emrForm.getNotes();
	    String total=emrForm.getTotal();
	    
	    Priscription priscription=null;
	    CompleteAppointment appointment=new CompleteAppointment();
	    String date=DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone()).split(" ")[0];
	    String clientname="";
	    if(selectedid!=0){
	    	
	    	priscription = emrDAO.getPriscriptionParentData(selectedid);
	    	Client client=clientDAO.getClientDetails(priscription.getClientId());
	 	    clientname=client.getTitle()+" "+client.getFirstName()+" "+client.getLastName();
	 	    
	 	    String tpid="";
		    String payby="0";
		    if(client.getWhopay()!=null){
		    	
		    	if(client.getWhopay().equals("Client")){
		    		   tpid="0";
		    		   payby="0";
		    	}else {
		    		payby="1";
		    		tpid=client.getTypeName();
		    	}
		    }else {
		    	tpid="0";
		    	payby="0";
		    }
		    priscription.setWhopay(payby);
		    priscription.setTpid(tpid);
		    
	    	appointment.setClientId(priscription.getClientId());
		    appointment.setPayBuy(client.getWhopay());
		    appointment.setCharges(""+total+"");
		    appointment.setChargeType("");
		    appointment.setVat(Double.parseDouble(vat));
		    appointment.setDiscount(discount);
		    appointment.setTotal(Double.parseDouble(total));
		    appointment.setNotes(notes);
		    appointment.setPriscid(selectedid);
		    appointment.setInvoiceDate(date);
		    String loc =(String) session.getAttribute("location");
		    appointment.setLocation(loc);
		    
		    if(previous_balance!=null){
				if(previous_balance!="0"){
					String dbbalance = pharmacyDAO.getbalofpatient(priscription.getClientId());
					int bal = Integer.parseInt(dbbalance) - Integer.parseInt(previous_balance);
					int res = pharmacyDAO.updatebalpatient(""+bal,priscription.getClientId());
				}
			}
		    
		    if(balance!="0" || balance!=""){
		    	String dbbalance = pharmacyDAO.getbalofpatient(priscription.getClientId());
				int bal = Integer.parseInt(dbbalance) + Integer.parseInt(balance);
				balance = ""+bal;
				int res = pharmacyDAO.updatebalpatient(balance,priscription.getClientId());
		    }
			
	    } else {
	    	    priscription=pharmacyDAO.getPharmacyPatientDetails(billno);
	    	    clientname=priscription.getFullname();
	    	    appointment.setClientId("0");
	    	    appointment.setPclientid(priscription.getPclientid());
			    appointment.setPayBuy("Client");
			    appointment.setCharges(""+total+"");
			    appointment.setChargeType("");
			    appointment.setVat(Double.parseDouble(vat));
			    appointment.setDiscount(discount);
			    appointment.setTotal(Double.parseDouble(total));
			    appointment.setNotes(notes);
			    appointment.setInvoiceDate(date);
			    priscription.setWhopay("0");
			    priscription.setTpid("0");
		}
	    appointment.setBalance(tbalance); 
	    appointment.setUserid(loginInfo.getUserId());
	    billno=pharmacyDAO.addMedicineBill(appointment);
	    
	    //update in priscription 
	    int result=pharmacyDAO.updatePriscEmrBill(billno,String.valueOf(selectedid));
	    //update status
	    result=pharmacyDAO.updateDeliverStatus("1", String.valueOf(selectedid));
	    UserProfile userProfile=null;
	    
	    for(Priscription prisc:emrForm.getMedicines()){
	    	if(prisc==null){
	    		continue;
	    	}
	    	if(prisc.getId()==0){
	    		continue;
	    	}
	    	
	    	priscription.setMdicinenameid(prisc.getMdicinenameid());
	    	priscription.setProduct_id(prisc.getProduct_id());
	    	Product product=productDAO.getProduct(prisc.getProduct_id());
	    	priscription.setSaleqty(prisc.getSaleqty());
	    	priscription.setReqqty(prisc.getReqqty());
	    	priscription.setMrp(product.getSale_price());
	    	if(selectedid!=0){
	    		userProfile= profileDAO.getUserprofileDetails(Integer.parseInt(priscription.getPrectionerid()));
		    	priscription.setFullname(userProfile.getFullname());
	    	} else {
	    		priscription.setFullname(priscription.getPractitionername());
	    	}
	    	priscription.setClientname(clientname);
	    	priscription.setDate(date);
	    	
	    	if(product.getStock()!="0"){
	    		result=pharmacyDAO.addMedicineCharges(priscription,billno);
	    		result=productDAO.updateMedicineQty(priscription.getSaleqty(),priscription.getProduct_id(),0);
	    	}
	    	
	    	//update inventory
	    	
	    	
	    	
	     }
	    
	    //Direct Record Payment
	    double tot=Double.parseDouble(total);
	    
	    //  19 Sep 2018 generate pharmacy payment Seq No according location
	    int paymentseqno = pharmacyDAO.getPharmacyPaymentSeqNo(location);
		paymentseqno = paymentseqno+1;
	    
	    result=pharmacyDAO.recordPaymentMedicine(String.valueOf(billno),tot,paymode,date,appointment.getClientId(),"0",discount,notes,appointment.getPclientid(),loginInfo.getUserId(),location,0,paymentseqno);
		session.setAttribute("billno", billno);
	    
		session.setAttribute("clientid", priscription.getClientId());
	    session.setAttribute("selectedid",selectedid);
		
		
	} catch (Exception e) {
		e.printStackTrace();
	}
	 finally {
		 
		 connection.close();
	 }
	  
	  return "redirectbill";
  }
	
  
  public String recordpayment() throws Exception{
	  if (!verifyLogin(request)) {

			return "login";
		}
	  Connection connection=null;
	try {
		
		connection=Connection_provider.getconnection();
		PharmacyDAO pharmacyDAO=new JDBCPharmacyDAO(connection);
		
		String clientid=(String)session.getAttribute("clientid");
		
		AccountsDAO accountsDAO = new JDBCAccountsDAO(connection);
		
		String transactionType=emrForm.getTransactionType();
		String fromDate=emrForm.getFromDate();
		String toDate=emrForm.getToDate();

		if(fromDate!=null){
			if(fromDate.equals("")){
				
				fromDate=DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone()).split(" ")[0];	
				emrForm.setFromDate(DateTimeUtils.changeDateFormattoPicker(fromDate));
			 } else {
				    String temp[]= fromDate.split("/");
				    emrForm.setFromDate(fromDate);
					fromDate = temp[2]+"-"+temp[1]+"-"+temp[0];
			 }
			 
		}else {
			
			fromDate=DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone()).split(" ")[0];
			emrForm.setFromDate(DateTimeUtils.changeDateFormattoPicker(fromDate));
		}
		
		
		if(toDate!=null){
			if(toDate.equals("")){
				
				toDate=DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone()).split(" ")[0];	
				emrForm.setToDate(DateTimeUtils.changeDateFormattoPicker(toDate));
			} else {
				String temp1[]= toDate.split("/");	
				emrForm.setToDate(toDate);
				toDate = temp1[2]+"-"+temp1[1]+"-"+temp1[0];
			}
		}else {
			
			toDate=DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone()).split(" ")[0];
			emrForm.setToDate(DateTimeUtils.changeDateFormattoPicker(toDate));
		}
		
		
		if(transactionType!=null){
			
			if(transactionType.equals("")){
				transactionType="All";
			}
		}else {
			
			transactionType="All";
		}
		
		
		ArrayList<Accounts> clientDetailsList = accountsDAO.getClientDetails(clientid);
			for(Accounts a1:clientDetailsList){
				emrForm.setInitial(a1.getInitial());
				emrForm.setFirstname(a1.getFirstname());
				emrForm.setLastname(a1.getLastname());
				emrForm.setAddress(a1.getAddress());
				emrForm.setCity(a1.getCity());
				emrForm.setClientId(clientid);
				emrForm.setPostcode(a1.getPostcode());
				emrForm.setEmail(a1.getEmail());
				emrForm.setMobno(a1.getMobno());
				
				emrForm.setClient(a1.getInitial()+" "+a1.getFirstname()+" "+a1.getLastname());
				
		}
		
			
			
			
	   int count=pharmacyDAO.getTotalProcessingChargesCount(clientid,transactionType,fromDate,toDate);
	   pagination.setPreperties(count);
	   emrForm.setTotalRecords(count);
	   ArrayList<Accounts> chargeProcessingList=pharmacyDAO.getAllProcessingCharges(pagination,clientid,transactionType,fromDate,toDate);
	   pagination.setPage_records(chargeProcessingList.size());
	   emrForm.setPagerecords(String.valueOf(pagination.getPage_records()));
	   emrForm.setChargeProcessingList(chargeProcessingList);
	   
	   
	} catch (Exception e) {

		e.printStackTrace();
	} 
	finally {
		connection.close();
	}
	  
	  return "record";
  }
	
  

  public String paycash() throws Exception{
	  if (!verifyLogin(request)) {

			return "login";
		}
	Connection connection=null;  
	try {
		connection=Connection_provider.getconnection();
		PharmacyDAO pharmacyDAO=new JDBCPharmacyDAO(connection);
		
		String billno = request.getParameter("invoiceid");
		String payAmount = request.getParameter("amount");
		String howpaid = request.getParameter("howpaid");
		String invoiceDate = request.getParameter("invoiceDate");
		String clientId = request.getParameter("clientId");
		String ccdamt = request.getParameter("ccdamt");
		double payAmount1 = Double.parseDouble(payAmount);	
		double discount=Double.parseDouble(request.getParameter("discount"));
		String notes=request.getParameter("creditNotes");
		
		
		if(clientId.equals("")){
			clientId = (String)session.getAttribute("clientId");
		}
		 String location =(String) session.getAttribute("location");
		    if(location==null){
		    	location="0";
		    }
		//  19 Sep 2018 generate pharmacy payment Seq No according location
		int paymentseqno = pharmacyDAO.getPharmacyPaymentSeqNo(location);
		paymentseqno = paymentseqno+1;
		int result=pharmacyDAO.recordPaymentMedicine(billno,payAmount1,howpaid,invoiceDate,clientId,ccdamt,discount,notes,"0",loginInfo.getUserId(),location,0,paymentseqno);
		int paymentid = result;
		//23 OCT 2018   ledger work
		// ledger for credit invoice
		ChargesAccountProcessingDAO chargesAccountProcessingDAO = new JDBCChargeAccountProcesDAO(connection);
		CompleteAppointment completeAppointment1=pharmacyDAO.getBillDetails(Integer.parseInt(billno));
		
		if(howpaid!=null){
	  		if(!howpaid.equals("") || !howpaid.equals("Credit")){
		  		String locationname = pharmacyDAO.getLocationName(completeAppointment1.getLocation());
				String serviceid = chargesAccountProcessingDAO.getLedgrServiceIds(locationname);
				String ledgerid = chargesAccountProcessingDAO.getledgerIDNew(serviceid, howpaid, "0","2",0);
	
				double lbal = chargesAccountProcessingDAO.getLedgerBalance(ledgerid);
				lbal = lbal + payAmount1;
				String credit = "" + payAmount1 + "";
				String ldebit = "0";
				String product = "xxxxx";
				String partyid = completeAppointment1.getClientId();
				String otherclientid =completeAppointment1.getPclientid();
				  
				String lcommencing = DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone());
				int saveledger = chargesAccountProcessingDAO.saveLedger(partyid, product, ldebit, credit, lbal,
							ledgerid, lcommencing, "0", 0,otherclientid,"0","0","0",""+paymentid+"",0,0,completeAppointment1.getLocation());
					
				//second effect
				lbal = 0;
				credit = "0";
				ldebit = "" + payAmount1 + "";
				product = "xxxxx";
				partyid =completeAppointment1.getClientId();
				otherclientid =completeAppointment1.getPclientid();
				lcommencing = DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone());
				saveledger = chargesAccountProcessingDAO.saveLedger(partyid, product, ldebit, credit, lbal,
							ledgerid, lcommencing, "0", 0,otherclientid,"0","0","0",""+paymentid+"",0,0,completeAppointment1.getLocation());
		  	}
	  	}
		
		session.setAttribute("billno", Integer.parseInt(billno));
		
		
	} catch (Exception e) {

		e.printStackTrace();
	}
	finally {
		connection.close();
	}
	  
	  
	  return "redirectbill";
  }
  
  
  
  
/*  public String adnewmedicine() throws Exception{
	   String location=(String) session.getAttribute("location");
	   if(location==null)
	   {
		   location="0";
	   }
	   Connection connection=null;
	   try {
	    connection=Connection_provider.getconnection();
	    InventoryProductDAO inventoryProductDAO=new JDBCInventoryProductDAO(connection);
	    PharmacyDAO pharmacyDAO = new JDBCPharmacyDAO(connection);
	    String pid=request.getParameter("pid");
	    String tcount=request.getParameter("count");
	    String qty=request.getParameter("qty");
	    Product product=inventoryProductDAO.getProduct(pid);
	    String medicine_shedule= inventoryProductDAO.getMedicineShedule(product.getCatalogueid());
	    
	    String hdnphclientid = request.getParameter("hdnphclientid");
	    String hdnispharmacy = request.getParameter("hdnispharmacy");
	    
	    if(product.getId()>0){
	    	
	    	DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			Calendar cal = Calendar.getInstance();
			String fromDate = dateFormat.format(cal.getTime());
	    	String todate = DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone());
	    	int tempclientid = Integer.parseInt(hdnphclientid);
		    int ispharmacypatient =0;
			if(hdnispharmacy.equals("0")){
		    	//HIS USER
				ispharmacypatient =0;
		    }else{
		    	//Pharmacy User
		    	ispharmacypatient =1;
		    }
			
			//Check already patient and user session id available or not
			boolean flag = pharmacyDAO.checkPatientChargeAvailableInLog(tempclientid,ispharmacypatient,loginInfo.getLoginsessionid(),product.getId(),qty,fromDate,todate);
			int chargesessionid=0;
			if(!flag){
				//if entry not present in temp table so insert into temp table
				chargesessionid = pharmacyDAO.insertMedicineChargeLog(tempclientid,ispharmacypatient,loginInfo.getLoginsessionid(),product.getId(),qty,fromDate,todate);
			}
			
		    String ex=product.getExpiry_date();
		    String expiry=DateTimeUtils.converToMMMYYYYforExp(ex);
		    int count=Integer.parseInt(tcount);
		    String color="#777";
		    if(medicine_shedule.equals("Regular")) {
		     color="#777";
		    } else if(medicine_shedule.equals("Narcotics")){
		     color="#e05d6f";
		    } else if(medicine_shedule.equals("H1")){
		     color="#e69522";
		    }
		    int sr=count+1;
		    StringBuffer buffer=new StringBuffer();
		    buffer.append("<td class='text-center'>"+sr+"</td>");
		    buffer.append("<td style='color:"+color+" '>"+product.getProduct_name()+" <input type='hidden' class='pclass' value='"+count+"' /> <input type='hidden' name='medicines["+count+"].id' value='"+product.getId()+"' /> <input type='hidden' name='medicines["+count+"].product_id' id='product_id"+count+"' value='"+pid+"' /> </td>");
		    buffer.append("<td class='text-center' id='labelreq"+count+"'>"+qty+"</td> <input type='hidden' name='medicines["+count+"].reqqty' value='"+qty+"' id='req"+count+"' />");
		    
		    buffer.append("<td>"+product.getStock()+"/"+product.getBatch_no()+"/"+expiry+" <input type='hidden' id='pur_price"+count+"' value='"+product.getPurchase_price()+"' /> <input type='hidden' value='"+product.getPack()+"' id='pack"+count+"'/> </td>");
		    buffer.append("<input type='hidden' id='pur_price"+count+"' value='"+product.getPurchase_price()+"' /> <input type='hidden' value='"+product.getPack()+"' id='pack"+count+"'/> ");
		    buffer.append("<td class='text-center'>"+product.getStock()+" </td>");
		    buffer.append("<td class='text-center'>"+product.getBatch_no()+" </td>");
		    buffer.append("<td class='text-center'>"+expiry+" </td>");
		    buffer.append("<td class='text-center'><input   type='number' name='medicines["+count+"].saleqty' onchange='calsubTotal()' id='sale"+count+"' value='"+qty+"' class='form-control text-center' style='height: 20px !important;background-color: rgba(255, 225, 225, 0.59);'></td>");
		    buffer.append("<td class='text-center'><input   type='number' name='medicines["+count+"].saleqty' onchange='changeQtyFromSale("+count+","+chargesessionid+")' id='sale"+count+"' value='"+qty+"' class='form-control text-center' style='height: 20px !important;background-color: rgba(255, 225, 225, 0.59);'></td>");
		    if(loginInfo.isPurchase_edit_pharmacy()){
		    buffer.append("<td class='text-center'><input type='number' value='"+product.getSale_price()+"' name='medicines["+count+"].sale_price' onchange='calsubTotal()' id='mrp"+count+"' class='form-control' style='height: 20px !important;text-align: right;' /> <input type='hidden' value='"+product.getSale_price()+"' id='sale_price"+count+"' /> </td>");
		    }else{
		    	buffer.append("<td class='text-center'><input type='number' value='"+product.getSale_price()+"' name='medicines["+count+"].sale_price' onchange='calsubTotal()' id='mrp"+count+"' class='form-control' style='height: 20px !important;text-align: right;' readonly/> <input type='hidden' value='"+product.getSale_price()+"' id='sale_price"+count+"' /> </td>");
		    }
		    buffer.append("<td style='text-align: center;text-transform: uppercase;'>"+product.getShelf()+" <input type='hidden' name='medicines["+count+"].vat' id='vat"+count+"' value='"+product.getVat()+"'> </td>");
		    buffer.append("<td class='text-right'>Rs.<label id='totalmrp"+count+"'>00.00</label></td>");
		    buffer.append("<input type hidden id='prodd"+count+"' value='"+product.getStock()+"'/>");
		    
		    //min check with stock @jitu
		    Product pmaster= inventoryProductDAO.getProductCatalogueDetailsforsale(product.getCatalogueid(),location);
		    int allstock= inventoryProductDAO.getTotalStockProductforsale(product.getCatalogueid(),location); 
		    int minorder= Integer.parseInt(pmaster.getMinorder());
		    int pack= Integer.parseInt(pmaster.getPack());
		    if(pack==0){
		    	pack=1;
		    }
		    int nowstock=allstock/pack ;
		    UserProfileDAO userProfileDAO= new JDBCUserProfileDAO(connection);
		    UserProfile userProfile= userProfileDAO.getPharmacyUserDetails(loginInfo.getClinicid());
		    int medlimit=inventoryProductDAO.getMedLimit(userProfile.getUserid());
		    if(nowstock<medlimit){
		    	buffer.append("<td class='text-center'><a href='#' onclick=deleteRow(this)><i class='fa fa-minus-circle' style='color:#c50404;font-size: 17px;padding-top: 2px;' aria-hidden='true'></i></a></td><td class='text-center'><a href='#' onclick='requestStock("+pid+","+count+")' title='Request Stock'><i class='fa fa-cart-plus' aria-hidden='true' style='font-size: 17px;padding-top: 2px;'></i></a></td>");
		    	buffer.append("<td class='text-center'><a href='#' onclick=deleteRowFromSession(this,"+chargesessionid+","+count+")><i class='fa fa-minus-circle' style='color:#c50404;font-size: 17px;padding-top: 2px;' aria-hidden='true'></i></a></td><td class='text-center'><a href='#' onclick='requestStock("+pid+","+count+")' title='Request Stock'><i class='fa fa-cart-plus' aria-hidden='true' style='font-size: 17px;padding-top: 2px;'></i></a></td>");
		    } else {
		    	buffer.append("<td class='text-center' id='tdbutton"+count+"'><a href='#' onclick=deleteRow(this)><i class='fa fa-minus-circle' style='color:#c50404;font-size: 17px;padding-top: 2px;' aria-hidden='true'></i></a></td>");
		    	buffer.append("<td class='text-center' id='tdbutton"+count+"'><a href='#' onclick=deleteRowFromSession(this,"+chargesessionid+","+count+")><i class='fa fa-minus-circle' style='color:#c50404;font-size: 17px;padding-top: 2px;' aria-hidden='true'></i></a></td>");
		    }
		    buffer.append("<input type='hidden' id='tmedicineid"+count+"' name='medicines["+count+"].mdicinenameid' value='"+product.getMedicinenameid()+"' />");
		    
	    response.setContentType("text/html");
	    response.setHeader("Cache-Control", "no-cache");
	    response.getWriter().write(""+buffer.toString()+""); 
	    
	    } else {
	     response.setContentType("text/html");
	     response.setHeader("Cache-Control", "no-cache");
	     response.getWriter().write("0"); 
	    }
	    
	   } catch (Exception e) {

	    e.printStackTrace();
	   }finally{
			
			connection.close();
		}
	   
	   return null;
	  }*/
  
  

  
  
  public String adnewmedicineipd() throws Exception{
	  if (!verifyLogin(request)) {

			return "login";
		}
	   Connection connection=null;
	   try {
	    connection=Connection_provider.getconnection();
	    InventoryProductDAO inventoryProductDAO=new JDBCInventoryProductDAO(connection);
	    String pid=request.getParameter("pid");
	    String tcount=request.getParameter("count");
	    String qty=request.getParameter("qty");
	    Product product=inventoryProductDAO.getProduct(pid);
	    String medicine_shedule= inventoryProductDAO.getMedicineShedule(product.getCatalogueid());
	    
	    if(product.getId()>0){
	     
	    String ex=product.getExpiry_date();
	    String expiry=DateTimeUtils.converToMMMYYYYforExp(ex);
	    int count=Integer.parseInt(tcount);
	    String color="#777";
	    if(medicine_shedule.equals("Regular")) {
	         color="#777";
	    } else if(medicine_shedule.equals("Narcotics")){
	         color="#e05d6f";
	    } else if(medicine_shedule.equals("H1")){
	         color="#e69522";
	    }else if(medicine_shedule.equals("High Risk Medicine")){
	    	 color="#9381cc";
	    }else if(medicine_shedule.equals("Vaccination")){
	    	 color="#e0acdafc";
	    }
	    int sr=count+1;
	    StringBuffer buffer=new StringBuffer();
	    buffer.append("<td>"+sr+"</td> ");
	    buffer.append("<td style='border-bottom: none !important;color:"+color+" '>"+product.getProduct_name()+" <input type='hidden' class='pclass' value='"+count+"' /> <input type='hidden' name='medicines["+count+"].id' value='"+product.getId()+"' /> <input type='hidden' name='medicines["+count+"].product_id' id='product_id"+count+"' value='"+pid+"' /> </td>");
	    buffer.append("<td class='text-center' id='labelreq"+count+"' >"+qty+"</td> <input type='hidden' name='medicines["+count+"].reqqty' value='"+qty+"' id='req"+count+"' />");
	    buffer.append("<td class='text-center'>"+product.getStock()+"<input type='hidden' id='pur_price"+count+"' value='"+product.getPurchase_price()+"' /> <input type='hidden' value='"+product.getPack()+"' id='pack"+count+"'/> </td>");
	    buffer.append("<td class='text-center'> "+product.getBatch_no()+" </td>");
	    buffer.append("<td class='text-center'> "+expiry+" </td>");
	    buffer.append("<td ><input type='number' name='medicines["+count+"].saleqty' onchange='calsubTotal()' id='sale"+count+"' value='"+qty+"' class='form-control' style='height: 20px !important;background-color: rgba(255, 225, 225, 0.59);'></td>");
	    if(loginInfo.isPurchase_edit_pharmacy()){
	    buffer.append("<td ><input type='number' value='"+product.getSale_price()+"' name='medicines["+count+"].sale_price' onchange='calsubTotal()' id='mrp"+count+"' class='form-control' style='height: 20px !important;text-align: right;background-color: rgba(255, 225, 225, 0.59);'/> <input type='hidden' value='"+product.getSale_price()+"' id='sale_price"+count+"' /> </td>");
	    }else{
	    	 buffer.append("<td ><input type='number' value='"+product.getSale_price()+"' name='medicines["+count+"].sale_price' onchange='calsubTotal()' id='mrp"+count+"' class='form-control' style='height: 20px !important;text-align: right;background-color: rgba(255, 225, 225, 0.59);' readonly/> <input type='hidden' value='"+product.getSale_price()+"' id='sale_price"+count+"' /> </td>");	
	    }
	    buffer.append("<td style='text-align: center;text-transform: uppercase;'>"+product.getShelf()+" <input type='hidden' name='medicines["+count+"].vat' id='vat"+count+"' value='"+product.getVat()+"'> </td>");
	    buffer.append("<td  class='text-right'>Rs.<label id='totalmrp"+count+"'>00.00</label></td>");
	    buffer.append("<input type hidden id='prodd"+count+"' value='"+product.getStock()+"'/>");
	    
	    //min check with stock @jitu
	    Product pmaster= inventoryProductDAO.getProductCatalogueDetails(product.getCatalogueid());
	    double allstock= inventoryProductDAO.getTotalStockProduct(product.getCatalogueid()); 
	    int minorder= Integer.parseInt(pmaster.getMinorder());
	    int pack= Integer.parseInt(pmaster.getPack());
	    double nowstock=allstock/pack ;
	    
	    if(nowstock<minorder){
	     buffer.append("<td class='text-center'><a href='#' onclick=deleteRow(this)><i class='fa fa-minus-circle' style='color:#c50404;font-size: 17px;padding-top: 2px;' aria-hidden='true'></i></a></td><td class='text-center'><a href='#' onclick='requestStock("+pid+","+count+")' title='Request Stock'><i class='fa fa-cart-plus' aria-hidden='true' style='font-size: 17px;padding-top: 2px;'></i></a></td>");
	    } else {
	     buffer.append("<td class='text-center' id='tdbutton"+count+"'><a href='#' onclick=deleteRow(this)><i class='fa fa-minus-circle' style='color:#c50404;font-size: 17px;padding-top: 2px;' aria-hidden='true'></i></a></td>");
	    }
	    
	    buffer.append("<input type='hidden' id='tmedicineid"+count+"' name='medicines["+count+"].mdicinenameid' value='"+product.getMedicinenameid()+"' />");
	    
	    response.setContentType("text/html");
	    response.setHeader("Cache-Control", "no-cache");
	    response.getWriter().write(""+buffer.toString()+""); 
	    
	    } else {
	     response.setContentType("text/html");
	     response.setHeader("Cache-Control", "no-cache");
	     response.getWriter().write("0"); 
	    }
	    
	   } catch (Exception e) {

	    e.printStackTrace();
	   }finally{
			
			connection.close();
		}
	   
	   return null;
	  }
  
		

     public String getallprisc(){
    	 if (!verifyLogin(request)) {

 			return "login";
 		}
    	 try {
			
		} catch (Exception e) {

			e.printStackTrace();
		}
    	 
    	 return null;
     }
    
  
  
	
	public String saleprisc() throws Exception{
		if (!verifyLogin(request)) {

			return "login";
		}
		Connection connection=null;
		try {
			/*ArrayList<Breadcrumbs> indentflowlist = new ArrayList<Breadcrumbs>();
			ArrayList<Breadcrumbs> indentflowlisttemp = new ArrayList<Breadcrumbs>();
			if (session.getAttribute("indentflowlist") != null) {
				indentflowlisttemp = (ArrayList<Breadcrumbs>) session.getAttribute("indentflowlist");
			}
			boolean isavilablemodule= false;
			int modulecount =0;
			for (Breadcrumbs breadcrumbs : indentflowlisttemp) {
				breadcrumbs.setIscurrent(false);
				breadcrumbs.setSqno(modulecount);
				modulecount++;
				if(breadcrumbs.getName().equals("Pharmacy Sale")){
					isavilablemodule =true;
					breadcrumbs.setIscurrent(true);
					indentflowlist.add(breadcrumbs);
					break;
				}else{
					indentflowlist.add(breadcrumbs);
				}
			}
			if(!isavilablemodule){
				Breadcrumbs breadcrumbs = new Breadcrumbs();
				breadcrumbs.setName("Pharmacy Sale");
				breadcrumbs.setOn(true);
				breadcrumbs.setSqno(modulecount);
				breadcrumbs.setUrllink("salepriscPharmacy");
				breadcrumbs.setIscurrent(true);
				breadcrumbs.setShowingname("Pharmacy Sale");
				indentflowlist.add(breadcrumbs);
			}
			session.setAttribute("indentflowlist",indentflowlist);*/
			
			connection=Connection_provider.getconnection();
			UserProfileDAO userProfileDAO = new JDBCUserProfileDAO(connection);
			PharmacyDAO pharmacyDAO = new JDBCPharmacyDAO(connection);
			
			
			String location="0";
			session.removeAttribute("sessionadmissionid");
			UserProfile profile2 = userProfileDAO.getPharmacyUserDetails(loginInfo.getClinicid());
			String userid = loginInfo.getUserId();
			Priscription priscription = pharmacyDAO.getPharacyUsrLInfo(userid);
			String ipdlocation="0";
			String opdlocation="0";
			String ipdloc="0";
			String opdloc="0";
			if(loginInfo.getUserType()==2) {
				 
				 location = (String) session.getAttribute("location");
				 if(location==null){
					 location= "0";
					 ipdloc=null;
					 opdloc=null;
				 }
				 if(location.equals("All")){
			    	 location="0";
			     }
			     
				 session.setAttribute("location", location);
			} else {
				 
			     String loc=  priscription.getLocation();
			     
			     if(loc!=null){
			      	  session.setAttribute("location", loc);
			    	  location= loc;
			     }  else {
			    	 location= "0";
			     }
			     if(loc.equals("All")){
			    	 loc="0";
			     }
			     
			     //UserProfile profile = userProfileDAO.getPharmacyUserDetails(loginInfo.getClinicid());
			     
			     if(profile2.getProcurementType()!=null){
			    	 if(profile2.getProcurementType().equals("1")){
			    		 loginInfo.setProcurementType(true);
			    	 } else {
			    		 loginInfo.setProcurementType(false);
			    	 }
			     } else {
			    	 loginInfo.setProcurementType(false);
			     }
			     
			     
			     ipdlocation= profile2.getIpdlocation();
			     opdlocation= profile2.getOpdlocation();
				 
				 for(String s: ipdlocation.split(",")){
					 if(s.equals("0")){
						 continue;
					 }
					 if(location.equals(s)){
						 ipdloc=location;
						 break;
					 }
					 
				 }
				 
				 for(String s:opdlocation.split(",")){
					  
					 if(s.equals("0")){
						 continue;
					 }
					 if(location.equals(s)){
						 opdloc=location;
						 break;
					 }
				 }
				 
			}
			
			
			emrForm.setLocation(location);
			
			if (priscription.getSale_bill()!=null)
				if(priscription.getSale_bill().equals("0"))
					loginInfo.setSale_bill(false);
				else
					loginInfo.setSale_bill(true);
			
			if (priscription.getDisscount()!=null)
				if (priscription.getDisscount().equals("0"))
					loginInfo.setDisscount(false);
				else
					loginInfo.setDisscount(true);
			
			if (priscription.getAccount()!=null)
				if (priscription.getAccount().equals("0"))
					loginInfo.setAccount(false);
				else
					loginInfo.setAccount(true);
			
			if (priscription.getLedger()!=null)
				if (priscription.getLedger().equals("0"))
					loginInfo.setLedger(false);
				else
					loginInfo.setLedger(true);
			
			if (priscription.getIslogin()!=null)
				if (priscription.getIslogin().equals("0"))
					loginInfo.setIslogin(false);
				else
					loginInfo.setIslogin(true);
			
			if (priscription.getPurchase_order()!=null)
				if (priscription.getPurchase_order().equals("0"))
					loginInfo.setPurchase_order(false);
				else
					loginInfo.setPurchase_order(true);
			
			if (priscription.getEdit_bill()!=null)
				if (priscription.getEdit_bill().equals("0"))
					loginInfo.setEdit_bill(false);
				else
					loginInfo.setEdit_bill(true);
			
			if (priscription.getDelete_bill()!=null)
				if (priscription.getDelete_bill().equals("0"))
					loginInfo.setDelete_bill(false);
				else
					loginInfo.setDelete_bill(true);
			if(priscription.getInhousepatient()!=null)
			if (priscription.getInhousepatient().equals("0"))
				loginInfo.setInhousepatient(false);
			else
				loginInfo.setInhousepatient(true);
			
			if (priscription.getReturn_medicine()!=null)
				if (priscription.getReturn_medicine().equals("0"))
					loginInfo.setReturn_medicine(false);
				else
					loginInfo.setReturn_medicine(true);
			
			
			if (!loginInfo.isIslogin()) {
				if(loginInfo.getUserType()==2 || loginInfo.getJobTitle().equals("Pharmacist")){
					
				}
				else{
					addActionError("Inactive pharmacy user");
					return "loginerror";
				}
			}
			
			
			/*String location =(String) session.getAttribute("location");
			if(location==null){
				location="0";
			}*/
			//ArrayList<Product> inventoryPriscList = inventoryProductDAO.geProductList("2",location);
			ArrayList<Product> inventoryPriscList= new ArrayList<Product>();
			emrForm.setInventoryPriscList(inventoryPriscList);
			
			//ArrayList<Product> pharmapatientlist = inventoryProductDAO.getpatientlist();
			ArrayList<Product> pharmapatientlist = new ArrayList<Product>();
			emrForm.setPharmapatientlist(pharmapatientlist);
			emrForm.setSelectedclientid("0");
			String dateTime=DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone()).split(" ")[0];
			emrForm.setDateTime(DateTimeUtils.getCommencingDate1(dateTime));
			//Priscription priscription = pharmacyDAO.getPharacyUsrLInfo(loginInfo.getUserId());
			
			if(priscription.getAccess_back_date()==1){
				 emrForm.setBack_date_access("1");
			}
			if(priscription.getTpbill()==1){
				emrForm.setTp_bill_access("1");
			}
			
			//akash 08-07-2020 to set for store list
			ArrayList<UserProfile> pharmacydrlist = userProfileDAO.getPharmacyDoctorList();
			emrForm.setPharmacydrlist(pharmacydrlist);
			
			//  16 Nov 2018 
			//delete previous data of user using login session
			int res = pharmacyDAO.deleteTempPharmacySession(loginInfo.getLoginsessionid());
			
			//int res1 = pharmacyDAO.deleteTempPharmacyData(loginInfo.getLoginsessionid());
			
			//delete from sessionid 
			//int res2 = pharmacyDAO.deleteFromSessionLog(loginInfo.getLoginsessionid()); 
			
			boolean showletterhd=pharmacyDAO.isShowletterhd();
			if(showletterhd){
				session.setAttribute("showletterhd","true");
			}else{
				session.setAttribute("showletterhd","false"); 
			}
			session.setAttribute("pagelimit", profile2.getPagelimit());
			
			emrForm.setNonsystembarcode(profile2.isNonsystembarcode());
			
			//  07-03-2019 dont know why below code used so done comment
			/*UserProfile userProfile = userProfileDAO.getPharmacyUserDetails(1);
			String inhousepatient = userProfile.getInhousepatient();
			if (inhousepatient!=null) {
				String[] data = inhousepatient.split(",");
				for (int i = 0; i < data.length; i++) {
					String string = data[i];
					if(string.equals("0")){
						continue;
					}
					String loc = priscription.getLocation();
					if(loc!=null){
						if (loc.equals(string)) {
							return "opdipdsaleprisc";
						}
					}
				}
			}*/
			
		} catch (Exception e) {
		
			e.printStackTrace();
		}
		finally {
			connection.close();
		}
		
		return "newsale";
	}
	
	
	/*public String newpatientsale() throws Exception{
		
		Connection connection=null;
		String sessinbillno="0";
		try {
			connection=Connection_provider.getconnection();
			PharmacyDAO pharmacyDAO=new JDBCPharmacyDAO(connection);
			InventoryProductDAO productDAO=new JDBCInventoryProductDAO(connection);
			String notes="";
			String fullname=emrForm.getFullname();
			String agegender=emrForm.getAgeandgender();
			String paymode=emrForm.getPaymode();
			String address=emrForm.getWardname();
			String practitioner=emrForm.getPractitionerName();
			String dateTime=emrForm.getDateTime();
			String mobile= emrForm.getMobno();
			String extpid = request.getParameter("extpid");
			String tpid= request.getParameter("tpid");
			String priscid= emrForm.getPriscid();
			String previous_balance = emrForm.getPrevious_balance();
			String payamt= emrForm.getPayamt();
			String balance = emrForm.getBalance();
			String cgst= emrForm.getCgst();
			String sgst= emrForm.getSgst();
			String date= DateTimeUtils.getCommencingDate1(dateTime); 
			notes =emrForm.getNotes();
			String oldparentid = emrForm.getOldparentid();
			int newparentid = emrForm.getNewparentid();
			priscid = oldparentid;
			//  16 Nov 2018 For Pharmacy Repeat bill issue
			int tempclientid = Integer.parseInt(emrForm.getHdnphclientid());
		    int ispharmacypatient =0;
			if(emrForm.getHdnispharmacy().equals("0")){
		    	//HIS USER
				ispharmacypatient =0;
		    }else{
		    	//Pharmacy User
		    	ispharmacypatient =1;
		    }
			
			int updatechargesession = pharmacyDAO.updateChargeSessionStatus(tempclientid,ispharmacypatient,loginInfo.getLoginsessionid(),1);
			
			//Check already patient and user session id available or not
			boolean flag = pharmacyDAO.checkPatientAvailableInLog(tempclientid,ispharmacypatient,loginInfo.getLoginsessionid(),0);
			if(!flag){
				//if entry not present in temp table so insert into temp table
				int tempsessionid = pharmacyDAO.insertTempPharSaleSession(tempclientid,ispharmacypatient,loginInfo.getLoginsessionid(),0,"0","0","0");
				
				//Second filter check already tempid in sale bill
				boolean flag2 = pharmacyDAO.checkTempIdInPharmacyBill(tempsessionid);
				if(!flag2){
					//It means its 1st bill not repeat bill
					if(balance==null){
						balance="0";
					}
					if(tpid==null){
						tpid="0";
					}
					if(tpid.equals("")){
						tpid="0";
					}
					if(balance.equals("")){
						balance="0";
					}
					
					String paynote="";
					String loc= (String) session.getAttribute("location");
					
					Client client=new Client();
					client.setClientName(fullname);
					if(agegender!=null){
						 if(!agegender.equals("")){
							 try {
								 String data[]=agegender.split("/");
								 client.setGender(data[0]);
								 client.setAge(Integer.parseInt(data[1]));
							 } catch (Exception e) {
								 e.printStackTrace();
							 }
						 }
					}
					
					if(paymode!=null){
						 
						if(paymode.equals("Card")){
							
							paynote= request.getParameter("card");
						}
						if(paymode.equals("Cheque")){
							paynote= request.getParameter("chequeno");
						}
						if(paymode.equals("NEFT")){
							paynote= request.getParameter("neftid");
						}
						
					}else{
						paymode="";
					}
					
					client.setAddress(address);
					client.setReference(practitioner);
					client.setLastModified(dateTime);
					client.setFirstName(fullname);
					client.setMobNo(mobile);
					client.setBalance(balance);
					int pharmacyclientid= Integer.parseInt(emrForm.getHdnphclientid());
					
						if(extpid=="" || extpid.equals("") || extpid.equals("0")){
							
							ClientDAO clientDAO=new JDBCClientDAO(connection);
							//save abrivation seq no
							String cdate = DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone()).split(" ")[0];
							boolean checkifseq = clientDAO.checkifSequenceExist(cdate);
							String abrivationid = "";
							String clinicabrivation = clientDAO.getClinicAbrivation(loginInfo.getClinicid());
							String tempd[] = cdate.split("-");
							String y = tempd[0];
							String m = tempd[1];
							String d = tempd[2];
							if(checkifseq){
								int seqno = clientDAO.getSqeunceNumber(cdate);
								seqno++;
								int r = clientDAO.InserCdateSeq(cdate,seqno);
								//SNH170609001
								int yr = Integer.parseInt(y)%1000;
								abrivationid = clinicabrivation + yr + m +d + "00" + seqno;
							}else{
								
								int seqno = 1;
								int r = clientDAO.InserCdateSeq(cdate,seqno);
								//String seqno = clientDAO.getSqeunceNumber(cdate);
								int yr = Integer.parseInt(y)%1000;
								abrivationid = clinicabrivation + yr + m +d + "00" + seqno;
							}
							client.setAbrivationid(abrivationid);
							//pharmacyclientid=pharmacyDAO.addNewPharmacyPatient(client);
							int result = pharmacyDAO.updatebalance(pharmacyclientid,balance);
						}
						else
						{
							   pharmacyclientid= Integer.parseInt(extpid);
							   String bal= pharmacyDAO.getpreviousbalance(pharmacyclientid);
							   if(bal==null){
								   bal="0";
							   }else if(bal.equals("")){
								   bal="0";
							   }
							   double temp =Double.parseDouble(bal) + Double.parseDouble(balance);
						       int result = pharmacyDAO.updatebalance(pharmacyclientid,String.valueOf(temp));
						       int res = pharmacyDAO.updateExPatient(pharmacyclientid,client);
							
						}
						String vat=emrForm.getVat();
					    
						if(vat!=null){
							if(vat.equals("")){
								vat ="0";
							}
						}else{
							vat ="0";
						}
						
					    if(emrForm.getDiscount()!=null){
					    	 
					    	if(emrForm.getDiscount().equals("")){
					    		
					    		emrForm.setDiscount("0");
					    	}
					    }
					    else {
					    	emrForm.setDiscount("0");
					    }
						String discounttype ="";
					    String salediscount = "";
					    String discinper = "";
						if(paymode.equals("Card")){
							boolean fla4 = pharmacyDAO.checkPatientAvailableInLog(tempclientid,ispharmacypatient,loginInfo.getLoginsessionid(),1);
							if(fla4){
								CompleteAppointment completeAppointment = pharmacyDAO.getTempPharDiscountData(tempclientid,ispharmacypatient,loginInfo.getLoginsessionid(),1);
								discounttype = completeAppointment.getDisc_type();
							    salediscount = completeAppointment.getActualdiscount();
							    discinper = completeAppointment.getDiscperc();
							}else{
								discounttype = emrForm.getDiscounttype();
							    salediscount = emrForm.getSalediscount();
							    discinper = emrForm.getDiscinper();
							}
						}else{
							discounttype = emrForm.getDiscounttype();
						    salediscount = emrForm.getSalediscount();
						    discinper = emrForm.getDiscinper();
						}
					    
						
					    if(discounttype!=null){
					    	if(discounttype.equals("")){
					    		discounttype="1";
					    	}
					    }else{
					    	discounttype="1";
					    }
					    
					    if(salediscount!=null){
					    	if(salediscount.equals("")){
					    		salediscount="0";
					    	}
					    }else{
					    	salediscount="0";
					    }
					    
					    if(discinper!=null){
					    	if(discinper.equals("")){
					    		discinper="0";
					    	}
					    }else{
					    	discinper="0";
					    }
					    
					    double discount=Double.parseDouble(emrForm.getDiscount());
					    double discount=0;
					    String total=emrForm.getTotal();
					    String subbtotal = emrForm.getSubbtotal();
					    if(subbtotal!=null){
					    	if(subbtotal.equals("")){
					    		subbtotal="0";
					    	}
					    }else{
					    	subbtotal="0";
					    }
					    
					    if(discounttype.equals("1")){
					    	discount = Double.parseDouble(salediscount);
					    }else{
					    	discount= Double.parseDouble(subbtotal) * Double.parseDouble(salediscount)/100;
					    }
					    discount = Math.round(discount * 100.0) / 100.0;
					    
					    //  17-12-2018
					    String grosstotal = emrForm.getGrosstotal();
					    String grosssubtotal = emrForm.getGrosssubtotal();
					    
					    String clientname=fullname;
					    CompleteAppointment appointment=new CompleteAppointment();
					    appointment.setGrosssubtotal(grosssubtotal);
					    appointment.setGrosstotal(grosstotal);
					    appointment.setClientId("0");
					    appointment.setPclientid(""+pharmacyclientid+"");
					    if(emrForm.getHdnispharmacy().equals("0")){
					    	appointment.setClientId(Integer.toString(pharmacyclientid));
					    	appointment.setPclientid("0");
					    }
					    
					    appointment.setPayBuy("Client");
					    appointment.setCharges(""+total+"");
					    appointment.setChargeType("");
					    appointment.setVat(Double.parseDouble(vat));
					    appointment.setDiscount(discount);
					    appointment.setTotal(Double.parseDouble(total));
					    appointment.setNotes(notes);
					    
					    appointment.setPriscid(0);
					    String time=DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone()).split(" ")[1];
					    appointment.setInvoiceDate(date);
					    appointment.setInvoiceTime(time);
					    appointment.setBalance(balance);
					    appointment.setLocation(loc);
					    appointment.setCgst(cgst);
					    appointment.setTpid(tpid);
					    appointment.setSgst(sgst);
					    appointment.setPayamt(payamt);
					    appointment.setUserid(loginInfo.getUserId());
					    appointment.setOldparentid(oldparentid);
					    appointment.setNewparentid(newparentid);
					    
					    //  12-nov-2018 actual amount store, discttype and actual discount amount in table 
					    appointment.setDisc_type(discounttype);
					    appointment.setActualdiscount(salediscount);
					    appointment.setActualtotal(subbtotal);
					    appointment.setTempsessionid(tempsessionid);
					    appointment.setInitalpaymode(paymode);
					    appointment.setFinalpaymode(paymode);
					    int billno=pharmacyDAO.addMedicineBill(appointment);
					    
					    int updatesessiondata = pharmacyDAO.updateSessionChargeBillno(tempclientid,ispharmacypatient,loginInfo.getLoginsessionid(),billno);
					    
					    sessinbillno = ""+billno;
					    //  19 Sep 2018 generate pharmacy bill Seq No according location
					    int billseqno = pharmacyDAO.getPharmacyBillSeqNo(appointment.getLocation());
					    billseqno = billseqno+1;
					    int savebillseqresult = pharmacyDAO.updatePharmacySeqNo(billno,billseqno);
					    //update in new patient recors
					    int result=pharmacyDAO.updateBillandStatus(pharmacyclientid,billno,"1");
					    if(priscid!=null){
					    	if(!priscid.equals("") || !priscid.equals("0")){
					    		
					    		 result=pharmacyDAO.updatePriscEmrBill(billno,priscid);
					    		 result = pharmacyDAO.updatePriscEmrBillnew(billno,newparentid);
					    	}
					    }
					    
					    
					    //delete if estimate bill
					    String estimatebill= emrForm.getEstimatebill();
					    result =pharmacyDAO.deleteEstimateBill(estimatebill);
					    
					    Priscription priscription=new Priscription();
					    priscription.setPclientid(appointment.getPclientid());
					    priscription.setClientId(appointment.getClientId());;
					    String nowdate=DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone()).split(" ")[0];
					    double totaldebit =0;
					    for(Priscription prisc:emrForm.getMedicines()){
					    	if(prisc==null){
					    		continue;
					    	}
					    	if(prisc.getId()==0){
					    		continue;
					    	}
					    	
					    	priscription.setProduct_id(prisc.getProduct_id());
					    	priscription.setMdicinenameid(prisc.getMdicinenameid());
					    	Product product=productDAO.getProduct(prisc.getProduct_id());
					    	priscription.setSaleqty(prisc.getSaleqty());
					    	priscription.setReqqty(prisc.getReqqty());
					    	priscription.setMrp(prisc.getSale_price());
					    	priscription.setFullname(practitioner);
					    	priscription.setClientname(clientname);
					    	priscription.setWhopay("0");
					    	priscription.setDate(date);
					    
					    	if(priscription.getMrp()!=null){
					    		if(priscription.getMrp().equals("")){
					    			priscription.setMrp("0");
					    		}
					    	}else{
					    		priscription.setMrp("0");
					    	}
					    	
					    	double tvat= Double.parseDouble(product.getVat());
					    	double tot= 0;
					    	try{
					    		tot= priscription.getSaleqty() * Double.parseDouble(priscription.getMrp());
					    	}catch (Exception e) {
					    		log.debug("@@@@@@@@@@@@@@@@@@@@@@@"+e.getMessage());
								SmsService s = new SmsService();
							    s.sendSms(e.getMessage()+"bill no:"+sessinbillno+" "+loginInfo.getClinicUserid(), "9764434837", loginInfo, new EmailLetterLog());
							    s.sendSms(e.getMessage()+"bill no:"+sessinbillno+" "+loginInfo.getClinicUserid(), "8055056015", loginInfo, new EmailLetterLog());
					 			int loginId = loginInfo.getId();
					 			String to = "akashjamgade5594@gmail.com";
								String cc = "akashjamgade02@gmail.com";
								String subject = "Pharmacy Exception bill no:"+sessinbillno;
								String status = "Email";
								String notes1 =e.getMessage();
								EmailLetterLog emailLetterLog = new EmailLetterLog();
								emailLetterLog.setType(status);
					 			EmbeddedImageEmailUtil.sendMailAttachment(connection,loginId,to, cc, subject, notes1, "",loginInfo,emailLetterLog,"");
							}
					    	double temptot=0;
					    	double tempvat =0;
					    	if(discounttype.equals("0")){
					    		tempvat=tot*Double.parseDouble(discinper)/100;
					    	}else{
					    		tempvat=tot*Double.parseDouble(discinper)/100;
					    	}
					    	temptot = tot - tempvat;
					    	double dividevat= 100+tvat;
					    	double gst= tot*tvat/dividevat;
					    	double gst= temptot*tvat/dividevat;
					    	double half= gst/2;
					    	half = Math.round(half*100.0)/100.0;
					    	gst = Math.round(gst*100.0)/100.0;
					    	totaldebit = totaldebit + temptot;
					    	priscription.setTgstamt(DateTimeUtils.changeFormat(gst));
					    	priscription.setGstper(product.getVat());
					    	priscription.setSharediscount(DateTimeUtils.changeFormat(tempvat));
					    	
					    	priscription.setCgst(DateTimeUtils.changeFormat(half));
					    	priscription.setSgst(DateTimeUtils.changeFormat(half));
					    	try {
							if(!product.getStock().equals("0")){
					    		result=pharmacyDAO.addMedicineCharges(priscription,billno);
					    		result=productDAO.updateMedicineQty(priscription.getSaleqty(),priscription.getProduct_id(),0);
					    		
					    		// reorder level @jitu
					    		//min /max reorder level code by jitu
					        	String catalogueid= product.getCatalogueid(); 
					        	Product pmaster= productDAO.getProductCatalogueDetails(catalogueid);
					        	
					        	if(pmaster.getMinorder()!=null){
						    		if(pmaster.getMinorder().equals("")){
						    			pmaster.setMinorder("0");
						    		}
						    	}else{
						    		pmaster.setMinorder("0");
						    	}
					        	
					        	int minorder =Integer.parseInt(pmaster.getMinorder());
					        	int allstock= productDAO.getTotalStockProduct(catalogueid);
					        	if(pmaster.getPack()!=null){
					        		if(pmaster.getPack().equals("")){
					        			pmaster.setPack("0");
					        		}
					        	}else{
					        		pmaster.setPack("0");
					        	}
					        	int pack= Integer.parseInt(pmaster.getPack());
					        	if(pack==0){
					        		if(product.getPack()!=null){
					        			if(!product.getPack().equals("")){
					        				pack = Integer.parseInt(product.getPack());
					        			}else {
					        				pack=1;
					        			}
					        		}else{
					        			pack=1;
					        		}
					        	}
					        	int nowstock= allstock/pack;
					        	
					        	if(nowstock<=minorder){
					        		
					        		ProcurementDAO procurementDAO= new JDBCProcurementDAO(connection);
					        		 //add to po que list
					        		product.setDate(date);
					        		//  26-06-2018 error while billing
					        		if(pmaster.getMaxorder()!=null){
					        			if(pmaster.getMaxorder().equals("")){
					        				pmaster.setMaxorder("0");
					        			}
					        		}else{
					        			pmaster.setMaxorder("0");
					        		}
					        		int maxorder=Integer.parseInt(pmaster.getMaxorder());
					        		int orderqty=maxorder- allstock;   
					        		product.setQty(String.valueOf(orderqty));
					        		//add to po que
					    			int res = procurementDAO.saveNewTempPo(product);
					        	}
					    		
					    	}
					    	} catch (Exception e) {
					    		log.debug("@@@@@@@@@@@@@@@@@@@@@@@"+e.getMessage());
					    		SmsService s = new SmsService();
							    s.sendSms(e.getMessage()+"bill no:"+sessinbillno+" "+loginInfo.getClinicUserid(), "9764434837", loginInfo, new EmailLetterLog());
							    s.sendSms(e.getMessage()+"bill no:"+sessinbillno+" "+loginInfo.getClinicUserid(), "8055056015", loginInfo, new EmailLetterLog());
					 			int loginId = loginInfo.getId();
					 			String to = "akashjamgade5594@gmail.com";
								String cc = "akashjamgade02@gmail.com";
								String subject = "Pharmacy Exception bill no:"+sessinbillno;
								String status = "Email";
								String notes1 =e.getMessage();
								EmailLetterLog emailLetterLog = new EmailLetterLog();
								emailLetterLog.setType(status);
					 			EmbeddedImageEmailUtil.sendMailAttachment(connection,loginId,to, cc, subject, notes1, "",loginInfo,emailLetterLog,"");
							}
					    	
					    	//update inventory
					    	//result=productDAO.updateMedicineQty(priscription.getSaleqty(),priscription.getMdicinenameid(),0);
					     }
					    
					    int updatedebit = pharmacyDAO.updateBillDebit(Math.round(totaldebit),billno);
					    
					    int updatechargesession2 = pharmacyDAO.updateChargeSessionStatus(tempclientid,ispharmacypatient,loginInfo.getLoginsessionid(),2);
					    
					   double tot=Double.parseDouble(payamt);
					  //record payment
					   String datetime=DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone());
					 //  19 Sep 2018 generate pharmacy payment Seq No according location
					   int paymentseqno = pharmacyDAO.getPharmacyPaymentSeqNo(loc);
					   paymentseqno = paymentseqno+1;
					   result=pharmacyDAO.recordPaymentMedicine(String.valueOf(billno),tot,paymode,nowdate,appointment.getClientId(),"0",discount,notes,appointment.getPclientid(),loginInfo.getUserId(),loc,0,paymentseqno);
					   int paymentid =result;
					   int d= pharmacyDAO.updatePaymentNote(result,paynote);
					   result =pharmacyDAO.updatePaymentDateTime(datetime,result);
					   //int paymentseqnores = pharmacyDAO.updatePharmacyPaymentSeqNo(result, paymentseqno);
					   if(!tpid.equals("0")){
					    	d=pharmacyDAO.updateTpidtoPayment(result,tpid);
					   }
					  
					    
					  CompleteAppointment completeAppointment=pharmacyDAO.getBillDetails(billno);
					  ArrayList<Priscription> medicineChargeList=pharmacyDAO.getMedicineChargesList(billno);
					  double subtotal=0;
					  for(Priscription p:medicineChargeList){
						  
						  subtotal=subtotal+(Double.parseDouble(p.getMrp())*p.getSaleqty());
					  }
					  
					  if(previous_balance==null){
						  previous_balance="0";
					  }
					  
					  emrForm.setPrevious_balance(previous_balance);
					  emrForm.setSubtotal(DateTimeUtils.changeFormat(subtotal));
					  emrForm.setMedicineChargeList(medicineChargeList);
					  emrForm.setClientId(completeAppointment.getClientId());
					  emrForm.setTotal(DateTimeUtils.changeFormat(completeAppointment.getTotal()));
					  String disc=String.valueOf(completeAppointment.getDiscount());
					  emrForm.setDiscount(DateTimeUtils.changeStringFormat(disc));
					  emrForm.setVat(String.valueOf(vat));
					  emrForm.setCgst(completeAppointment.getCgst());
					  emrForm.setSgst(completeAppointment.getSgst());
					  emrForm.setBillno(billno);
					
					  //20 OCT 2018   ledger work
					  // ledger for credit invoice
					  ChargesAccountProcessingDAO chargesAccountProcessingDAO = new JDBCChargeAccountProcesDAO(connection);
					  double debit = completeAppointment.getTotal();
					  String clientid ="";
					  String pclientid="";
					  if(emrForm.getHdnispharmacy().equals("0")){
						  	clientid = ""+pharmacyclientid;
						  	pclientid ="0";
					  }else{
						  	clientid = "0";
						  	pclientid =""+pharmacyclientid;
					  }
					  
					  if (billno > 0) {
						  String locationname = pharmacyDAO.getLocationName(loc);
						  String serviceid = chargesAccountProcessingDAO.getLedgrServiceIds(locationname);
						  String ledgerid = chargesAccountProcessingDAO.getledgerIDNew(serviceid, "0", "0","2",0);
			
						  double lbal = chargesAccountProcessingDAO.getLedgerBalance(ledgerid);
						  lbal = lbal + debit;
						  String credit = "" + debit + "";
						  String ldebit = "0";
						  String product = "xxxxx";
						  String partyid = clientid;
						  String otherclientid =pclientid;
						  
						  String lcommencing = DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone());
						  int saveledger = chargesAccountProcessingDAO.saveLedger(partyid, product, ldebit, credit, lbal,
									ledgerid, lcommencing, "0", 0,otherclientid,"0","0",""+billno+"","0",0,0,loc);
							
						  //second effect
						  lbal = 0;
						  credit = "0";
						  ldebit = "" + debit + "";
						  product = "xxxxx";
						  partyid = clientid;
						  otherclientid =pclientid;
						  lcommencing = DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone());
						  saveledger = chargesAccountProcessingDAO.saveLedger(partyid, product, ldebit, credit, lbal,
									ledgerid, lcommencing, "0", 0,otherclientid,"0","0",""+billno+"","0",0,0,loc);
						}
					  	
					  	if (discount > 0) {
							String serviceid = chargesAccountProcessingDAO.getLedgrServiceIds("Discount");
							String ledgerid = chargesAccountProcessingDAO.getledgerIDNew(serviceid, "0", "0","2",0);
		
							double lbal = chargesAccountProcessingDAO.getLedgerBalance(ledgerid);
							lbal = lbal + discount;
							String credit = "" + discount + "";
							String ldebit = "0";
							String product = "" + billno + "";
							String partyid = clientid;
							String otherclientid =pclientid;
							String lcommencing = DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone());
							int saveledger = chargesAccountProcessingDAO.saveLedger(partyid, product, ldebit, credit, lbal,
									ledgerid, lcommencing, "0", 0,otherclientid,"0","0",""+billno+"","0",0,0,loc);
							
							//second effect
							 lbal = 0;
							 credit = "0";
							 ldebit = "" + discount + "";
							 product = "xxxxx";
							 partyid = clientid;
							 otherclientid =pclientid;
							 lcommencing = DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone());
							 saveledger = chargesAccountProcessingDAO.saveLedger(partyid, product, ldebit, credit, lbal,
									ledgerid, lcommencing, "0", 0,otherclientid,"0","0",""+billno+"","0",0,0,loc);
						}
					  	
					  	if(Double.parseDouble(vat)>0){
					  		String serviceid = chargesAccountProcessingDAO.getLedgrServiceIds("GST");
							String ledgerid = chargesAccountProcessingDAO.getledgerIDNew(serviceid, "0", "0","2",0);
		
							double lbal = chargesAccountProcessingDAO.getLedgerBalance(ledgerid);
							lbal = lbal + Double.parseDouble(vat);
							String credit = "" + Double.parseDouble(vat) + "";
							String ldebit = "0";
							String product = "" + billno + "";
							String partyid = clientid;
							String otherclientid =pclientid;
							
							String lcommencing = DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone());
							int saveledger = chargesAccountProcessingDAO.saveLedger(partyid, product, ldebit, credit, lbal,
									ledgerid, lcommencing, "0", 0,otherclientid,"0","0",""+billno+"","0",0,0,loc);
							
							//second effect
							 lbal = 0;
							 credit = "0";
							 ldebit = "" + Double.parseDouble(vat) + "";
							 product = "xxxxx";
							 partyid = clientid;
							 otherclientid =pclientid;
							 lcommencing = DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone());
							 saveledger = chargesAccountProcessingDAO.saveLedger(partyid, product, ldebit, credit, lbal,
									ledgerid, lcommencing, "0", 0,otherclientid,"0","0",""+billno+"","0",0,0,loc);
					  	}
					  	
					  	if(!paymode.equals("") || !paymode.equals("Credit")){
					  		String locationname = pharmacyDAO.getLocationName(loc);
							String serviceid = chargesAccountProcessingDAO.getLedgrServiceIds(locationname);
							String ledgerid = chargesAccountProcessingDAO.getledgerIDNew(serviceid, paymode, "0","2",0);
				
							double lbal = chargesAccountProcessingDAO.getLedgerBalance(ledgerid);
							lbal = lbal + tot;
							String credit = "" + tot + "";
							String ldebit = "0";
							String product = "xxxxx";
							String partyid = clientid;
							String otherclientid =pclientid;
							  
							String lcommencing = DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone());
							int saveledger = chargesAccountProcessingDAO.saveLedger(partyid, product, ldebit, credit, lbal,
										ledgerid, lcommencing, "0", 0,otherclientid,"0","0","0",""+paymentid+"",0,0,loc);
								
							//second effect
							lbal = 0;
							credit = "0";
							ldebit = "" + tot + "";
							product = "xxxxx";
							partyid = clientid;
							otherclientid =pclientid;
							lcommencing = DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone());
							saveledger = chargesAccountProcessingDAO.saveLedger(partyid, product, ldebit, credit, lbal,
										ledgerid, lcommencing, "0", 0,otherclientid,"0","0","0",""+paymentid+"",0,0,loc);
					  	}
					  	
					    Clinic clinic = new Clinic();
						ClinicDAO clinicDAO  = new JDBCClinicDAO(connection);
						clinic = clinicDAO.getCliniclistDetails(loginInfo.getClinicid());
						
						Location location = clinicDAO.getRegisterdLication();
						clinic.setLocationname(location.getAddress());
						
						String curdatetime = DateTimeUtils.getPriscDatetime(loginInfo.getTimeZone());
						clinic.setCurDateTime(curdatetime);
						clinic.setCurDateTime(DateTimeUtils.getCommencingDate1(date));
						session.setAttribute("billno", billno);
						session.setAttribute("selectedid", 0);
						
						session.setAttribute("clinicinfo", clinic);
						session.setAttribute("clientinfo", client);
				}
			}
			  
			  
		} catch (Exception e) {
			e.printStackTrace();
 			log.debug("@@@@@@@@@@@@@@@@@@@@@@@"+e.getMessage());
 			int loginId = loginInfo.getId();
 			String to = "akashjamgade5594@gmail.com";
			String cc = "akashjamgade02@gmail.com";
			String subject = "Pharmacy Exception bill no:"+sessinbillno;
			String status = "Email";
			String notes =e.getMessage();
			EmailLetterLog emailLetterLog = new EmailLetterLog();
			emailLetterLog.setType(status);
 			EmbeddedImageEmailUtil.sendMailAttachment(connection,loginId,to, cc, subject, notes, "",loginInfo,emailLetterLog,"");
			if(connection==null){
				connection=Connection_provider.getconnection();
			}
			SmsService s = new SmsService();
		    s.sendSms(e.getMessage()+"bill no:"+sessinbillno+" "+loginInfo.getClinicUserid(), "9764434837", loginInfo, new EmailLetterLog());
		    s.sendSms(e.getMessage()+"bill no:"+sessinbillno+" "+loginInfo.getClinicUserid(), "8055056015", loginInfo, new EmailLetterLog());
		    PharmacyDAO pharmacyDAO = new JDBCPharmacyDAO(connection);
		    int deleteresul = pharmacyDAO.deletePharmacyBill(sessinbillno);
 			return "error";
		}
		finally {
			if(connection==null){
				
				int loginId = loginInfo.getId();
	 			String to = "akashjamgade5594@gmail.com";
				String cc = "akashjamgade02@gmail.com";
				String subject = "Pharmacy Connection close bill no:"+sessinbillno;
				String status = "Email";
				String notes ="";
				EmailLetterLog emailLetterLog = new EmailLetterLog();
				emailLetterLog.setType(status);
				connection=Connection_provider.getconnection();
	 			EmbeddedImageEmailUtil.sendMailAttachment(connection,loginId,to, cc, subject, "", "",loginInfo,emailLetterLog,"");
				connection=Connection_provider.getconnection();
				SmsService s = new SmsService();
			    s.sendSms("connection close. bill no:"+sessinbillno+" "+loginInfo.getClinicUserid(), "9764434837", loginInfo, new EmailLetterLog());
			    s.sendSms("connection close bill no:"+sessinbillno+" "+loginInfo.getClinicUserid(), "8055056015", loginInfo, new EmailLetterLog());
			    PharmacyDAO pharmacyDAO = new JDBCPharmacyDAO(connection);
			    int deleteresul = pharmacyDAO.deletePharmacyBill(sessinbillno);
				return "error";
			}
			connection.close();
		}
		
		
		return "redirectbill";
	}*/
	
	
	public String deletebill() throws Exception {
		
		Connection connection=null;
		try {
			connection=Connection_provider.getconnection();
			PharmacyDAO pharmacyDAO=new JDBCPharmacyDAO(connection);
			String bill= request.getParameter("billno");
			//int r=pharmacyDAO.deleteMedicalBill(bill);
			int r=pharmacyDAO.cancelMedicineBill(bill);
			
			
			
		} catch (Exception e) {

			e.printStackTrace();
		}
		finally {
			connection.close();
		}
		
		return "save";
		
	}
	
	
	
	
	
	
	public String reportprisc() throws Exception{
		if (!verifyLogin(request)) {

			return "login";
		}
	     Connection connection=null;
	     try {
		 	/*ArrayList<Breadcrumbs> indentflowlist = new ArrayList<Breadcrumbs>();
			ArrayList<Breadcrumbs> indentflowlisttemp = new ArrayList<Breadcrumbs>();
			if (session.getAttribute("indentflowlist") != null) {
				indentflowlisttemp = (ArrayList<Breadcrumbs>) session.getAttribute("indentflowlist");
			}
			boolean isavilablemodule= false;
			int modulecount =0;
			for (Breadcrumbs breadcrumbs : indentflowlisttemp) {
				breadcrumbs.setIscurrent(false);
				breadcrumbs.setSqno(modulecount);
				modulecount++;
				if(breadcrumbs.getName().equals("Daily Sale Report")){
					isavilablemodule =true;
					breadcrumbs.setIscurrent(true);
					indentflowlist.add(breadcrumbs);
					break;
				}else{
					indentflowlist.add(breadcrumbs);
				}
			}
			if(!isavilablemodule){
				Breadcrumbs breadcrumbs = new Breadcrumbs();
				breadcrumbs.setName("Daily Sale Report");
				breadcrumbs.setOn(true);
				breadcrumbs.setSqno(modulecount);
				breadcrumbs.setUrllink("reportpriscPharmacy");
				breadcrumbs.setIscurrent(true);
				breadcrumbs.setShowingname("Daily Sale Report");
				indentflowlist.add(breadcrumbs);
			}
			session.setAttribute("indentflowlist",indentflowlist);
	    	 */
	    
	      connection=Connection_provider.getconnection();
	      PharmacyDAO pharmacyDAO=new JDBCPharmacyDAO(connection);
	      UserProfileDAO userProfileDAO= new JDBCUserProfileDAO(connection); 
	      String searchtext=emrForm.getSearchText();
	      String fromdate=emrForm.getFromdate();
	      String todate=emrForm.getTodate();
	      String from=emrForm.getFrom();
	      String paymode= emrForm.getPaymode();
	      String report=emrForm.getReport();
	      //String paymentmode = emrForm.getPaymentmode();
	      String isreturn= request.getParameter("isreturn");
	      if(searchtext!=null){
	       if(searchtext.equals("")){
	        searchtext=null;
	       }
	      }
	      
	      String location =(String) session.getAttribute("location");
	      if(location==null){
	       location="0";
	      }
	      if(searchtext!=null){
	       if(searchtext.equals("")){
	        searchtext=null;
	       }
	      }
	      
	      if(isreturn==null){
	       isreturn="0";
	      }
	      if(fromdate!=null){
	       if(fromdate.equals("")){
	         Calendar calendar=Calendar.getInstance();
	         SimpleDateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd");
	         fromdate=dateFormat.format(calendar.getTime());
	       } else {
	        
	        fromdate=DateTimeUtils.getCommencingDate1(fromdate);
	        
	       }
	      }else {
	        Calendar calendar=Calendar.getInstance();
	        SimpleDateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd");
	        fromdate=dateFormat.format(calendar.getTime());
	      }
	      
	      if(todate!=null){
	       if(todate.equals("")){
	         Calendar calendar=Calendar.getInstance();
	         SimpleDateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd");
	         todate=dateFormat.format(calendar.getTime());
	       }
	       else {
	        todate=DateTimeUtils.getCommencingDate1(todate);
	       }
	      }else {
	        Calendar calendar=Calendar.getInstance();
	        SimpleDateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd");
	        todate=dateFormat.format(calendar.getTime());
	      }
	      
	      if(report!=null){
	       
	       if(report.equals("Daily")){
	         Calendar calendar=Calendar.getInstance();
	         SimpleDateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd");
	         fromdate=dateFormat.format(calendar.getTime());
	         todate=dateFormat.format(calendar.getTime());
	       }
	      }
	      
	      
	      if(from!=null){
	       if(from.equals("0")) {
	        from=null;
	       }
	      }
	      if(paymode!=null){
	       if(paymode.equals("0")){
	        paymode=null;
	       }
	      }
	      if(report!=null){
	       if(report.equals("0")){
	        report=null;
	       }
	      }
	      
	      int ipdpatients= pharmacyDAO.getIpdPatientSaleCount(fromdate,todate);
	      int opdpatients= pharmacyDAO.getOpdPatientSaleCount(fromdate,todate);
	      int newpatients= pharmacyDAO.getNewpatientSaleCount(fromdate,todate);
	      
	      Priscription priscription=new Priscription();
	      priscription.setIpdpatients(ipdpatients);
	      priscription.setOpdpatients(opdpatients);
	      priscription.setNewpatients(newpatients);
	      
	      double totalTemp=ipdpatients+opdpatients+newpatients;
	      double ipdper=(ipdpatients*100)/totalTemp;
	      double opdper=(opdpatients*100)/totalTemp;
	      double newper=(newpatients*100)/totalTemp;
	      priscription.setIpdper(ipdper);
	      priscription.setOpdper(opdper);
	      priscription.setNewper(newper);
	      
	      session.setAttribute("report", priscription);
	      ChargesReportDAO chargesReportDAO = new JDBCChargesReportDAO(connection);
			DateFormat dateFormat1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		      Calendar cal = Calendar.getInstance();
		      String date1 = dateFormat1.format(cal.getTime());  
		     
			 int result = chargesReportDAO.saveMisReportLog("Daily Sale Report",loginInfo.getUserId(),fromdate,todate,date1,"reportprisc");
	      
	      ArrayList<Priscription> salesreportList= pharmacyDAO.getReportPharmacyList(searchtext,fromdate,todate,from,paymode,report,isreturn,location);
	      emrForm.setSalesreportList(salesreportList);
	      
	      double totalBalance= pharmacyDAO.getTotalBalanceofUsers(location,fromdate,todate);
	      double totalCredit= pharmacyDAO.getTotalCredit(location,fromdate,todate);
	      double todayagainstcredit = pharmacyDAO.getTotalAgainstCredit(location,fromdate,todate);
	      double finalcredit = totalCredit-todayagainstcredit;
	  //    double totalCollection =pharmacyDAO.getTotalCollectionToday(location,fromdate,todate);
	      double todaycash= pharmacyDAO.getCashToday(location,fromdate,todate);
	      double todaycard= pharmacyDAO.getCardToday(location,fromdate,todate);
	      double todaydisc =pharmacyDAO.getTodayDisc(location,fromdate,todate);
	      double chequepayment= pharmacyDAO.getChequePayment(location,fromdate,todate);
	      double neftpayment= pharmacyDAO.getNeftPayment(location,fromdate,todate);
	      double onlinepayment= pharmacyDAO.getOnlinePayment(location,fromdate,todate);
	      
	      double cashReturn= pharmacyDAO.getReturnToday(location,fromdate,todate);
	      double creditReturn= pharmacyDAO.getCreditReturn(location,fromdate,todate);
	      double hospitalReturn= pharmacyDAO.getHospitalReturn(location,fromdate,todate);
	      double totalhospital= pharmacyDAO.getTotalHospital(location,fromdate,todate);
	      double onlinepaymentreturn = pharmacyDAO.getOnlineReturn(location,fromdate,todate);
	      double cardreturn = pharmacyDAO.getCardReturn(location,fromdate,todate);
	    		  
	      
	      double totalreturn=cashReturn;
	      double plus=todaycash+todaycard+chequepayment+neftpayment+onlinepayment;
	      /*double minus= todaydisc+totalreturn;*/
	      double minus= totalreturn + onlinepaymentreturn + cardreturn;
	      double totalClosing = plus - minus;
	      emrForm.setCardreturn(DateTimeUtils.changeFormat(cardreturn));
	      emrForm.setOnlinepayment(DateTimeUtils.changeFormat(onlinepayment));
	      emrForm.setOnlinepaymentreturn(DateTimeUtils.changeFormat(onlinepaymentreturn));
	      emrForm.setCreditReturn(DateTimeUtils.changeFormat(creditReturn));
	      emrForm.setTotalcredit(DateTimeUtils.changeFormat(totalCredit));
	      emrForm.setHospitalReturn(DateTimeUtils.changeFormat(hospitalReturn));
	      emrForm.setTodaycard(DateTimeUtils.changeFormat(todaycard));
	      emrForm.setTodaycash(DateTimeUtils.changeFormat(todaycash));
	      emrForm.setTodaydisc(DateTimeUtils.changeFormat(todaydisc));
	      emrForm.setTodayReturn(DateTimeUtils.changeFormat(cashReturn));
	      emrForm.setChequepayment(DateTimeUtils.changeFormat(chequepayment));
	      emrForm.setNeftpayment(DateTimeUtils.changeFormat(neftpayment));
	      emrForm.setTotalpayment(DateTimeUtils.changeFormat(plus));
	      emrForm.setBalance(DateTimeUtils.changeFormat(totalBalance));
	      emrForm.setTotal(DateTimeUtils.changeFormat(totalClosing));
	      emrForm.setHospital(DateTimeUtils.changeFormat(totalhospital));
	      emrForm.setTodayagainstcredit(DateTimeUtils.changeFormat(todayagainstcredit));
	      emrForm.setFinalcredit(DateTimeUtils.changeFormat(finalcredit));
	       Calendar calendar=Calendar.getInstance();
	       SimpleDateFormat dateFormat=new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
	       String date=dateFormat.format(calendar.getTime());
	       emrForm.setDateTime(date);
	       
	       fromdate =DateTimeUtils.getCommencingDate1(fromdate);
	       todate =DateTimeUtils.getCommencingDate1(todate);
	      emrForm.setFromdate(fromdate);
	      emrForm.setTodate(todate);
	      
	         UserProfile userProfile= userProfileDAO.getPharmacyUserDetails(loginInfo.getClinicid());
	      emrForm.setClinicName(userProfile.getClinicname());
	      emrForm.setClinicaddress(userProfile.getAddress());
	      emrForm.setLandLine(userProfile.getPhone());
	      emrForm.setFullname(userProfile.getFullname());
	      emrForm.setEmail(userProfile.getEmail());
	      emrForm.setPlace(userProfile.getCity());
	      emrForm.setWebsiteUrl(userProfile.getWebsite());
	      emrForm.setLocation(pharmacyDAO.getLocationName(location));
	      emrForm.setUserid(loginInfo.getUserId());
	   
	     } catch (Exception e) {

	      e.printStackTrace();
	     }
	     finally {
	      connection.close();   
	     }
	     
	   return "reportprisc";
	 }
	
   public String changestatus() throws Exception{
	   if (!verifyLogin(request)) {

			return "login";
		}
	Connection connection=null;  
	try {
		
		connection=Connection_provider.getconnection();
		PharmacyDAO pharmacyDAO=new JDBCPharmacyDAO(connection);
		String status=request.getParameter("status");
		String priscid=request.getParameter("priscid");
		
		int result=pharmacyDAO.updateDeliverStatus(status,priscid);
		
		
	} catch (Exception e) {

		e.printStackTrace();
	} finally {
		connection.close();
	}
	   
	 return "save";
   }
   
   
/*public String requeststock(){
	   
	   
	   Connection connection=null;
	   try {
		   
		   connection=Connection_provider.getconnection();
		   PharmacyDAO pharmacyDAO=new JDBCPharmacyDAO(connection);
		   String pid=request.getParameter("pid");
		   String qty=request.getParameter("quantity");
		   String date=DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone());
		   
		   String loc= (String) session.getAttribute("location");
			if(loc==null){
				loc="0";
			}
			
		   int result=pharmacyDAO.addnewRequestStock(pid,qty,date,loc);
		   
		   response.setContentType("text/html");
		   response.setHeader("Cache-Control", "no-cache");
		   response.getWriter().write(""); 
	   } catch (Exception e) {

		   e.printStackTrace();
	   }
	   
	   return null;
   }*/
   
   
   public String reorder() throws Exception {
	   if (!verifyLogin(request)) {

			return "login";
		}
	   Connection connection=null;  
		 try {
	 		 connection=Connection_provider.getconnection();
			 PharmacyDAO pharmacyDAO=new JDBCPharmacyDAO(connection);
			 BedDao bedDao = new JDBCBedDao(connection);
			 IpdDAO  ipdDAO = new JDBCIpdDAO(connection);
			 EmrDAO emrDAO=new JDBCEmrDAO(connection);
			 ClientDAO clientDAO=new JDBCClientDAO(connection);
			 UserProfileDAO profileDAO=new JDBCUserProfileDAO(connection);
				
			 int billno=Integer.parseInt(request.getParameter("billno"));
			 int selectedid=Integer.parseInt(request.getParameter("selectedid"));
			 
			  CompleteAppointment completeAppointment=pharmacyDAO.getBillDetails(billno);
			  ArrayList<Priscription> priscriptionlist=pharmacyDAO.getMedicineChargesList(billno,loginInfo);
			  
			  emrForm.setPriscriptionlist(priscriptionlist);
			  double subtotal=0;
			  for(Priscription p:priscriptionlist){
				  
				  subtotal=subtotal+(Double.parseDouble(p.getMrp())*p.getSaleqty());
			  }

			  if(selectedid>0){
				    
				  	Priscription priscription=emrDAO.getPriscriptionParentData(selectedid);
				  	String practitionerName=profileDAO.getUserprofileDetails(Integer.parseInt(priscription.getPrectionerid())).getFullname();
				    String ipdid = emrDAO.getpriscIpdId(selectedid);
					Bed bed = bedDao.getEditIpdData(ipdid);
					emrForm.setIpdid(ipdid);
					String wardname=ipdDAO.getIpdWardName(bed.getWardid());
					String bedname = ipdDAO.getIpdBedName(bed.getBedid());
					emrForm.setWardname(wardname);
					emrForm.setBedname(bedname);
					String wardbed=wardname+"/"+bedname;
					emrForm.setWardname(wardbed);
					Client client=clientDAO.getClientDetails(completeAppointment.getClientId());
					String name = client.getTitle()+" "+client.getFirstName()+" "+client.getLastName();
					emrForm.setFullname(name);
					String date  = DateTimeUtils.getDashboardTodayDate(loginInfo.getCountry());
					emrForm.setDateTime(completeAppointment.getInvoiceDate());
					emrForm.setSelectedid(selectedid);
					String dobyear[] = client.getDob().split("/");
					String curryear[] = date.split("/");
					
					int  age = Integer.parseInt(curryear[2]) - Integer.parseInt(dobyear[2]);
					client.setAge(age);
					String agedate=client.getGender()+"/"+age;
					emrForm.setAgeandgender(agedate);
					emrForm.setPractitionerName(practitionerName);
					
			  }else {
				  
				   String pclientid=pharmacyDAO.getPharmacyClientidFromBill(billno);
				   Priscription priscription=pharmacyDAO.getPharmacyPatient(pclientid);
				   emrForm.setWardname(priscription.getAddress());
				   emrForm.setFullname(priscription.getFullname());
				   emrForm.setSelectedid(selectedid);
				   emrForm.setAgeandgender(priscription.getAgeandgender());
				   emrForm.setPractitionerName(priscription.getPractitionername());
			  }
			  
			  emrForm.setSubtotal(DateTimeUtils.changeFormat(subtotal));
			  emrForm.setMedicineChargeList(priscriptionlist);
			  emrForm.setClientId(completeAppointment.getClientId());
			  emrForm.setTotal(DateTimeUtils.changeFormat(completeAppointment.getTotal()));
			  emrForm.setDiscount(String.valueOf(completeAppointment.getDiscount()));
			  emrForm.setVat(String.valueOf(completeAppointment.getVat()));
			  emrForm.setBillno(billno);
			 
			
		 } catch (Exception e) {

			e.printStackTrace();
		 }
		 finally {
				connection.close();
		 }
	   
	   return "reorder";
   }
   
   
   
   
   
   
   
   
   public String editmedicine() throws Exception {
	   if (!verifyLogin(request)) {

			return "login";
		}
	 Connection connection=null;  
	 try {
 		 connection=Connection_provider.getconnection();
		 PharmacyDAO pharmacyDAO=new JDBCPharmacyDAO(connection);
		 BedDao bedDao = new JDBCBedDao(connection);
		 IpdDAO  ipdDAO = new JDBCIpdDAO(connection);
		 EmrDAO emrDAO=new JDBCEmrDAO(connection);
		 ClientDAO clientDAO=new JDBCClientDAO(connection);
			
		 int billno=Integer.parseInt(request.getParameter("billno"));
		 int selectedid=Integer.parseInt(request.getParameter("selectedid"));
		 
		  CompleteAppointment completeAppointment=pharmacyDAO.getBillDetails(billno);
		  ArrayList<Priscription> priscriptionlist=pharmacyDAO.getMedicineChargesList(billno,loginInfo);
		  
		  emrForm.setPriscriptionlist(priscriptionlist);
		  double subtotal=0;
		  for(Priscription p:priscriptionlist){
			  
			  subtotal=subtotal+(Double.parseDouble(p.getMrp())*p.getSaleqty());
		  }

		  if(selectedid>0){
			    
			    String ipdid = emrDAO.getpriscIpdId(selectedid);
				Bed bed = bedDao.getEditIpdData(ipdid);
				emrForm.setIpdid(ipdid);
				String wardname=ipdDAO.getIpdWardName(bed.getWardid());
				String bedname = ipdDAO.getIpdBedName(bed.getBedid());
				emrForm.setWardname(wardname);
				emrForm.setBedname(bedname);
				String wardbed=wardname+"/"+bedname;
				emrForm.setWardname(wardbed);
				Client client=clientDAO.getClientDetails(completeAppointment.getClientId());
				String name = client.getTitle()+" "+client.getFirstName()+" "+client.getLastName();
				emrForm.setFullname(name);
				String date  = DateTimeUtils.getDashboardTodayDate(loginInfo.getCountry());
				emrForm.setDateTime(completeAppointment.getInvoiceDate());
				emrForm.setSelectedid(selectedid);
				String dobyear[] = client.getDob().split("/");
				String curryear[] = date.split("/");
				
				int  age = Integer.parseInt(curryear[2]) - Integer.parseInt(dobyear[2]);
				client.setAge(age);
				String agedate=client.getGender()+"/"+age;
				emrForm.setAgeandgender(agedate);
				
				
		  }else {
			  
			   String pclientid=pharmacyDAO.getPharmacyClientidFromBill(billno);
			   Priscription priscription=pharmacyDAO.getPharmacyPatient(pclientid);
			   emrForm.setWardname(priscription.getAddress());
			   emrForm.setFullname(priscription.getFullname());
			   emrForm.setSelectedid(selectedid);
			   emrForm.setAgeandgender(priscription.getAgeandgender());
			   
		  }
		  
		  emrForm.setSubtotal(DateTimeUtils.changeFormat(subtotal));
		  emrForm.setMedicineChargeList(priscriptionlist);
		  emrForm.setClientId(completeAppointment.getClientId());
		  emrForm.setTotal(DateTimeUtils.changeFormat(completeAppointment.getTotal()));
		  emrForm.setDiscount(String.valueOf(completeAppointment.getDiscount()));
		  emrForm.setVat(String.valueOf(completeAppointment.getVat()));
		  emrForm.setBillno(billno);
		 
		
	 } catch (Exception e) {

		e.printStackTrace();
	 }
	 finally {
			connection.close();
	 }
	   
	   return "editmedicine";
   }
   
   
   /*public String update() throws Exception {
	   
	   Connection connection=null;
		try {
			
			connection=Connection_provider.getconnection();
			PharmacyDAO pharmacyDAO=new JDBCPharmacyDAO(connection);
			ClientDAO clientDAO=new JDBCClientDAO(connection);
		    String vat=emrForm.getVat();
		    int billno=emrForm.getBillno();
		    if(emrForm.getDiscount()!=null){
		    	 
		    	if(emrForm.getDiscount().equals("")){
		    		
		    		emrForm.setDiscount("0");
		    	}
		    }
		    else {
		    	emrForm.setDiscount("0");
		    }
		    
		    double discount=Double.parseDouble(emrForm.getDiscount());
		    String notes=emrForm.getNotes();
		    double total=emrForm.getTotal();
		    CompleteAppointment appointment=new CompleteAppointment();
		    appointment.setCharges(""+total+"");
		    appointment.setChargeType("");
		    appointment.setVat(Double.parseDouble(vat));
		    appointment.setDiscount(discount);
		    appointment.setTotal(total);
		    appointment.setNotes(notes);
		    
		    
		    String date=DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone()).split(" ")[0];
		    appointment.setInvoiceDate(date);
		    
		    int result=pharmacyDAO.updateMedicineBill(appointment,billno);
		    
		    Priscription priscription=new Priscription();
		    
		    for(Priscription prisc:emrForm.getMedicines()){
		    	
		    	priscription.setMdicinenameid(prisc.getMdicinenameid());
		    	priscription.setId(prisc.getId());
		    	priscription.setSaleqty(prisc.getSaleqty());
		    	priscription.setDate(date);
		    	result=pharmacyDAO.updateMedicineCharges(priscription);
		    	
		    	//update inventory
		    	result=productDAO.updateMedicineQty(priscription.getSaleqty(),priscription.getMdicinenameid());
		    	
		     }
		     
		  CompleteAppointment completeAppointment=pharmacyDAO.getBillDetails(billno);
		  ArrayList<Priscription> medicineChargeList=pharmacyDAO.getMedicineChargesList(billno);
		  double subtotal=0;
		  for(Priscription p:medicineChargeList){
			  
			  subtotal=subtotal+(Double.parseDouble(p.getMrp())*p.getSaleqty());
		  }
		  
		  emrForm.setSubtotal(subtotal);
		  emrForm.setMedicineChargeList(medicineChargeList);
		  emrForm.setClientId(completeAppointment.getClientId());
		  emrForm.setTotal(completeAppointment.getTotal());
		  emrForm.setDiscount(String.valueOf(completeAppointment.getDiscount()));
		  emrForm.setVat(completeAppointment.getVat());
		  emrForm.setBillno(billno);
		  
		  	Clinic clinic = new Clinic();
			ClinicDAO clinicDAO  = new JDBCClinicDAO(connection);
			clinic = clinicDAO.getCliniclistDetails(loginInfo.getClinicid());
			Location location = clinicDAO.getRegisterdLication();
			clinic.setLocationname(location.getAddress());
			clinic.setCurDateTime(date);
			session.setAttribute("clinicinfo", clinic);
			
			Client client=clientDAO.getClientDetails(completeAppointment.getClientId());
			session.setAttribute("clientinfo", client);
		    
		    
		} catch (Exception e) {

			e.printStackTrace();
			
		}finally{
			connection.close();
		}
		
		
		return "creditbill";
   }
   
   */
   
   public String returnmedicine() throws Exception{ 
	   if (!verifyLogin(request)) {

			return "login";
		}
	   Connection connection=null;  
		 try {
			 int billno=Integer.parseInt(request.getParameter("billno"));
			 int selectedid=Integer.parseInt(request.getParameter("selectedid"));
			 
		 	/*ArrayList<Breadcrumbs> indentflowlist = new ArrayList<Breadcrumbs>();
			ArrayList<Breadcrumbs> indentflowlisttemp = new ArrayList<Breadcrumbs>();
			if (session.getAttribute("indentflowlist") != null) {
				indentflowlisttemp = (ArrayList<Breadcrumbs>) session.getAttribute("indentflowlist");
			}
			boolean isavilablemodule= false;
			int modulecount =0;
			for (Breadcrumbs breadcrumbs : indentflowlisttemp) {
				breadcrumbs.setIscurrent(false);
				breadcrumbs.setSqno(modulecount);
				modulecount++;
				if(breadcrumbs.getName().equals("Direct Pharmacy Return")){
					isavilablemodule =true;
					breadcrumbs.setIscurrent(true);
					indentflowlist.add(breadcrumbs);
					break;
				}else{
					indentflowlist.add(breadcrumbs);
				}
			}
			if(!isavilablemodule){
				Breadcrumbs breadcrumbs = new Breadcrumbs();
				breadcrumbs.setName("Direct Pharmacy Return");
				breadcrumbs.setOn(true);
				breadcrumbs.setSqno(modulecount);
				breadcrumbs.setUrllink("returnmedicinePharmacy?selectedid=0&billno="+billno+"");
				breadcrumbs.setIscurrent(true);
				breadcrumbs.setShowingname("Direct Pharmacy Return");
				indentflowlist.add(breadcrumbs);
			}
			session.setAttribute("indentflowlist",indentflowlist);*/
			 
			 
	 		 connection=Connection_provider.getconnection();
			 PharmacyDAO pharmacyDAO=new JDBCPharmacyDAO(connection);
			 BedDao bedDao = new JDBCBedDao(connection);
			 IpdDAO  ipdDAO = new JDBCIpdDAO(connection);
			 EmrDAO emrDAO=new JDBCEmrDAO(connection);
			 ClientDAO clientDAO=new JDBCClientDAO(connection);
			 UserProfileDAO profileDAO=new JDBCUserProfileDAO(connection);
			 
			//  16 Nov 2018 
			//delete previous data of user using login session
			int res = pharmacyDAO.deleteTempPharmacySession(loginInfo.getLoginsessionid());

			 
			
			 
			  CompleteAppointment completeAppointment=pharmacyDAO.getBillDetails(billno);
			  String paymode =pharmacyDAO.getBillPaymodeNew(billno);
			  //  31 July 2018
			  //double discper = (completeAppointment.getDiscount()/(completeAppointment.getTotal()+completeAppointment.getDiscount()))*100;
			  //discper = Math.round(discper);
			  double discPerGivenEach= pharmacyDAO.getDiscountPerReturnMedicine(""+billno,completeAppointment.getTotal());
			  ArrayList<Priscription> priscriptionlist=pharmacyDAO.getMedicineReturnCharges(billno, completeAppointment.getReturnbillid(),discPerGivenEach,1);
			 
			  int size = priscriptionlist.size();
				if (size > 0) {
					int isinadjustment = priscriptionlist.get(size - 1).getIsinadjustment();
					if(isinadjustment==1){
						return "isinadjusterrorpage";
					}
				}
			  
			  ArrayList<Priscription> priscriptionlistnew = pharmacyDAO.getReturnMedicineCharges(billno);
			  emrForm.setPriscriptionlistnew(priscriptionlistnew);
			  //discPerGivenEach = Math.round(discPerGivenEach);
			  emrForm.setDiscper(DateTimeUtils.changeFormat(discPerGivenEach));
			  String dateTime=DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone()).split(" ")[0];
			  emrForm.setPriscriptionlist(priscriptionlist);
			  double subtotal=0;
			  for(Priscription p:priscriptionlist){
				  
				  subtotal=subtotal+(Double.parseDouble(p.getMrp())*p.getSaleqty());
			  }

			  if(selectedid>0){
				    
				  	Priscription priscription=emrDAO.getPriscriptionParentData(selectedid);
				  	String practitionerName=profileDAO.getUserprofileDetails(Integer.parseInt(priscription.getPrectionerid())).getFullname();
				    String ipdid = emrDAO.getpriscIpdId(selectedid);
					Bed bed = bedDao.getEditIpdData(ipdid);
					emrForm.setIpdid(ipdid);
					String wardname=ipdDAO.getIpdWardName(bed.getWardid());
					String bedname = ipdDAO.getIpdBedName(bed.getBedid());
					emrForm.setWardname(wardname);
					emrForm.setBedname(bedname);
					String wardbed=wardname+"/"+bedname;
					emrForm.setWardname(wardbed);
					Client client=clientDAO.getClientDetails(completeAppointment.getClientId());
					String name = client.getTitle()+" "+client.getFirstName()+" "+client.getMiddlename()+" "+client.getLastName();
					emrForm.setFullname(name);
					String date  = DateTimeUtils.getDashboardTodayDate(loginInfo.getCountry());
					emrForm.setDateTime(completeAppointment.getInvoiceDate());
					emrForm.setSelectedid(selectedid);
					String dobyear[] = client.getDob().split("/");
					String curryear[] = date.split("/");
					
					int  age = Integer.parseInt(curryear[2]) - Integer.parseInt(dobyear[2]);
					client.setAge(age);
					String agedate=client.getGender()+"/"+age;
					emrForm.setAgeandgender(agedate);
					emrForm.setPractitionerName(practitionerName);
					//String balance= pharmacyDAO.getbalofpatient(completeAppointment.getClientId());
					double creditbalance= pharmacyDAO.getPharClientBalance("0",completeAppointment.getClientId());
					emrForm.setBalance(""+Math.round(creditbalance*100.0)/100.0);
					emrForm.setOpdid(completeAppointment.getClientId());
					emrForm.setMobno(client.getMobNo());
			  }else {
				   String pclientid=pharmacyDAO.getPharmacyClientidFromBill(billno);
				   if(!pclientid.equals("0")){						
					   Priscription priscription=pharmacyDAO.getPharmacyPatient(pclientid);
					   emrForm.setWardname(priscription.getAddress());
					   emrForm.setFullname(priscription.getFullname());
					   emrForm.setSelectedid(selectedid);
					   emrForm.setAgeandgender(priscription.getAgeandgender());
					   emrForm.setPractitionerName(priscription.getPractitionername());
					  /* emrForm.setBalance(priscription.getBalance());*/
					   emrForm.setOpdid(pclientid);
					   emrForm.setMobno(priscription.getMobile());
					   double creditbalance= pharmacyDAO.getPharClientBalance("1",pclientid);
					   emrForm.setBalance(""+Math.round(creditbalance*100.0)/100.0);
				   }else{
						String ipdopd=pharmacyDAO.getClientIDFromBillNo(billno);
						Client client = clientDAO.getPatient(Integer.parseInt(ipdopd));
						Double pre_balance = Double.parseDouble(client.getBalance());
						String name = client.getTitle()+" "+client.getFirstName()+" "+client.getMiddlename()+" "+client.getLastName();
						String date  = DateTimeUtils.getDashboardTodayDate(loginInfo.getCountry());
						String dobyear[] = client.getDob().split("/");
						String curryear[] = date.split("/");
						int  age = Integer.parseInt(curryear[2]) - Integer.parseInt(dobyear[2]);
						client.setAge(age);
						String ipdid = pharmacyDAO.getIpdIdFromClientID(Integer.parseInt(ipdopd));
						String pract_name ="";
					if(ipdid.equals("0")){
						pract_name = pharmacyDAO.getappointmentinfo(Integer.parseInt(ipdopd));
					}else{
						Bed  bed = bedDao.getEditIpdData(ipdid);
						profileDAO = new JDBCUserProfileDAO(connection);
						pract_name = profileDAO.getName(bed.getPractitionerid());
					}
						emrForm.setPractitionerName(pract_name);
				
						String ipdoropd = "0";
						if(ipdid.equals("0")){
							ipdoropd ="0";
						}else{
							ipdDAO = new JDBCIpdDAO(connection);
							Bed  bed = bedDao.getEditIpdData(ipdid);
							String wardname = ipdDAO.getIpdWardName(bed.getBedid());
							String bedname = ipdDAO.getIpdBedName(bed.getWardid());
							ipdoropd = ipdid;
							String wardbed = wardname +"/"+ bedname;
							emrForm.setWardbed(wardbed);
						}
							emrForm.setIpdoropd(ipdoropd);
					
						emrForm.setWardname(client.getAddress());
						emrForm.setFullname(name);
						emrForm.setSelectedid(selectedid);
						//emrForm.setAgeandgender(priscription.getAgeandgender());
						emrForm.setPractitionerName(pract_name);
						emrForm.setBalance(""+pre_balance);
						emrForm.setOpdid(pclientid);
						emrForm.setMobno(client.getMobNo());
						double creditbalance= pharmacyDAO.getPharClientBalance("0",ipdopd);
						emrForm.setBalance(""+Math.round(creditbalance*100.0)/100.0);
				   }
			  }
			  emrForm.setPractitionerName(completeAppointment.getPractitionerName());
			  emrForm.setPhar_practid(completeAppointment.getPractitionerId());
			  
			  emrForm.setDateTime(DateTimeUtils.getCommencingDate1(dateTime));
			  emrForm.setSubtotal(DateTimeUtils.changeFormat(subtotal));
			  emrForm.setMedicineChargeList(priscriptionlist);
			  emrForm.setClientId(completeAppointment.getClientId());
			  emrForm.setTotal(DateTimeUtils.changeFormat(completeAppointment.getTotal()));
			  emrForm.setDiscount(String.valueOf(completeAppointment.getDiscount()));
			  emrForm.setVat(String.valueOf(completeAppointment.getVat()));
			  emrForm.setBillno(billno);
			  
			 
			  
			  //discper= Double.parseDouble(String.format("%.2f", discper));
			  emrForm.setReturnmode(paymode);
			  //emrForm.setReturnmode("Cash");
			
		 } catch (Exception e) {

			e.printStackTrace();
		 }
		 finally {
				connection.close();
		 }
	  return "returnmedicine"; 
   }
   
   public String editbill() throws Exception{ 
	   if (!verifyLogin(request)) {

			return "login";
		}
	   Connection connection=null;  
		 try {
	 		 connection=Connection_provider.getconnection();
			 PharmacyDAO pharmacyDAO=new JDBCPharmacyDAO(connection);
			 BedDao bedDao = new JDBCBedDao(connection);
			 IpdDAO  ipdDAO = new JDBCIpdDAO(connection);
			 EmrDAO emrDAO=new JDBCEmrDAO(connection);
			 ClientDAO clientDAO=new JDBCClientDAO(connection);
			 UserProfileDAO profileDAO=new JDBCUserProfileDAO(connection);
			 InventoryProductDAO inventoryProductDAO= new JDBCInventoryProductDAO(connection);
				
			 int billno=Integer.parseInt(request.getParameter("billno"));
			 int selectedid=Integer.parseInt(request.getParameter("selectedid"));
			 String location= (String)session.getAttribute("location");
			 if(location==null){
				 location="0";
			 }
			 
			 
			  CompleteAppointment completeAppointment=pharmacyDAO.getBillDetails(billno);
			  ArrayList<Priscription> priscriptionlist=pharmacyDAO.getMedicineChargesList(billno,loginInfo);
			  String dateTime=DateTimeUtils.getUKCurrentDataTime(loginInfo.getCountry()).split(" ")[0];
			  emrForm.setPriscriptionlist(priscriptionlist);
			  double subtotal=0;
			  for(Priscription p:priscriptionlist){
				  
				  subtotal=subtotal+(Double.parseDouble(p.getMrp())*p.getSaleqty());
			  }

			  if(selectedid>0){
				    
				  	Priscription priscription=emrDAO.getPriscriptionParentData(selectedid);
				  	String practitionerName=profileDAO.getUserprofileDetails(Integer.parseInt(priscription.getPrectionerid())).getFullname();
				    String ipdid = emrDAO.getpriscIpdId(selectedid);
					Bed bed = bedDao.getEditIpdData(ipdid);
					emrForm.setIpdid(ipdid);
					String wardname=ipdDAO.getIpdWardName(bed.getWardid());
					String bedname = ipdDAO.getIpdBedName(bed.getBedid());
					emrForm.setWardname(wardname);
					emrForm.setBedname(bedname);
					String wardbed=wardname+"/"+bedname;
					emrForm.setWardname(wardbed);
					Client client=clientDAO.getClientDetails(completeAppointment.getClientId());
					String name = client.getTitle()+" "+client.getFirstName()+" "+client.getLastName();
					emrForm.setFullname(name);
					String date  = DateTimeUtils.getDashboardTodayDate(loginInfo.getCountry());
					emrForm.setDateTime(completeAppointment.getInvoiceDate());
					emrForm.setSelectedid(selectedid);
					String dobyear[] = client.getDob().split("/");
					String curryear[] = date.split("/");
					
					int  age = Integer.parseInt(curryear[2]) - Integer.parseInt(dobyear[2]);
					client.setAge(age);
					String agedate=client.getGender()+"/"+age;
					emrForm.setAgeandgender(agedate);
					emrForm.setPractitionerName(practitionerName);
					String balance= pharmacyDAO.getbalofpatient(completeAppointment.getClientId());
					emrForm.setBalance(balance);
					emrForm.setOpdid(completeAppointment.getClientId());
					emrForm.setMobno(client.getMobNo());
			  }else {
				  
				   String pclientid=pharmacyDAO.getPharmacyClientidFromBill(billno);
				   if(!pclientid.equals("0")){						
					   Priscription priscription=pharmacyDAO.getPharmacyPatient(pclientid);
					   emrForm.setWardname(priscription.getAddress());
					   emrForm.setFullname(priscription.getFullname());
					   emrForm.setSelectedid(selectedid);
					   emrForm.setAgeandgender(priscription.getAgeandgender());
					   emrForm.setPractitionerName(priscription.getPractitionername());
					   emrForm.setBalance(priscription.getBalance());
					   emrForm.setOpdid(pclientid);
					   emrForm.setMobno(priscription.getMobile());
				   }else{
						String ipdopd=pharmacyDAO.getClientIDFromBillNo(billno);
						Client client = clientDAO.getPatient(Integer.parseInt(ipdopd));
						Double pre_balance = Double.parseDouble(client.getBalance());
						String name = client.getTitle()+" "+client.getFirstName()+" "+client.getLastName();
						String date  = DateTimeUtils.getDashboardTodayDate(loginInfo.getCountry());
						String dobyear[] = client.getDob().split("/");
						String curryear[] = date.split("/");
						int  age = Integer.parseInt(curryear[2]) - Integer.parseInt(dobyear[2]);
						client.setAge(age);
						String ipdid = pharmacyDAO.getIpdIdFromClientID(Integer.parseInt(ipdopd));
						String pract_name ="";
					if(ipdid.equals("0")){
						pract_name = pharmacyDAO.getappointmentinfo(Integer.parseInt(ipdopd));
					}else{
						Bed  bed = bedDao.getEditIpdData(ipdid);
						profileDAO = new JDBCUserProfileDAO(connection);
						pract_name = profileDAO.getName(bed.getPractitionerid());
					}
						emrForm.setPractitionerName(pract_name);
				
						String ipdoropd = "0";
						if(ipdid.equals("0")){
							ipdoropd ="0";
						}else{
							ipdDAO = new JDBCIpdDAO(connection);
							Bed  bed = bedDao.getEditIpdData(ipdid);
							String wardname = ipdDAO.getIpdWardName(bed.getBedid());
							String bedname = ipdDAO.getIpdBedName(bed.getWardid());
							ipdoropd = ipdid;
							String wardbed = wardname +"/"+ bedname;
							emrForm.setWardbed(wardbed);
						}
							emrForm.setIpdoropd(ipdoropd);
					
						emrForm.setWardname(client.getAddress());
						emrForm.setFullname(name);
						emrForm.setSelectedid(selectedid);
						//emrForm.setAgeandgender(priscription.getAgeandgender());
						emrForm.setPractitionerName(pract_name);
						emrForm.setBalance(""+pre_balance);
						emrForm.setOpdid(pclientid);
						emrForm.setMobno(client.getMobNo());
					}
			  }
			  
				ArrayList<Product> inventoryPriscList= inventoryProductDAO.geProductList("2",location);
				emrForm.setInventoryPriscList(inventoryPriscList);
				
				ArrayList<Product> pharmapatientlist = inventoryProductDAO.getpatientlist();
				emrForm.setPharmapatientlist(pharmapatientlist);
			  
			  emrForm.setDateTime(dateTime);
			  emrForm.setSubtotal(DateTimeUtils.changeFormat(subtotal));
			  emrForm.setMedicineChargeList(priscriptionlist);
			  emrForm.setClientId(completeAppointment.getClientId());
			  emrForm.setTotal(DateTimeUtils.changeFormat(completeAppointment.getTotal()));
			  emrForm.setDiscount(String.valueOf(completeAppointment.getDiscount()));
			  emrForm.setVat(String.valueOf(completeAppointment.getVat()));
			  emrForm.setBillno(billno);
			  emrForm.setSelectedid(selectedid);
			 session.setAttribute("editmeddiscount", completeAppointment.getDiscount());
			
		 } catch (Exception e) {

			e.printStackTrace();
		 }
		 finally {
				connection.close();
		 }
	  return "editbill"; 
   }
   
   
   
   public String refundmedicine() throws Exception {
	   if (!verifyLogin(request)) {

			return "login";
		}
	   Connection connection=null;
		try {
			connection=Connection_provider.getconnection();
			PharmacyDAO pharmacyDAO=new JDBCPharmacyDAO(connection);
			InventoryProductDAO inventoryProductDAO=new JDBCInventoryProductDAO(connection);
			EmrDAO emrDAO=new JDBCEmrDAO(connection);
			UserProfileDAO userProfileDAO=new JDBCUserProfileDAO(connection);
			ClientDAO clientDAO=new JDBCClientDAO(connection);
			BedDao bedDao = new JDBCBedDao(connection);
			IpdDAO  ipdDAO = new JDBCIpdDAO(connection);
			UserProfileDAO profileDAO=new JDBCUserProfileDAO(connection);
			String paynote="";
			int selectedid=emrForm.getSelectedid();
		    String paymode=emrForm.getPaymode();
		    String bill=request.getParameter("billno"); 
		    String discper = request.getParameter("discper");
		    double discamt =0;
		    if(discper!=null){
		    	if(!discper.equals("")){
		    		discamt= Math.round(Double.parseDouble(discper) * 100.0) / 100.0;
		    	}
		    }
		    String oldbill=bill;
		    String refund = emrForm.getRefundamt();
		    if(bill!=null){
		    	if(bill.equals("")){
		    		 bill="0";
		    	}
		    }
		    else {
		    	bill="0";
		    }
		    String loc =(String) session.getAttribute("location");
		    
		    int billno=Integer.parseInt(bill);
		    
             if(loginInfo.isParihar()) {
            	 loc=pharmacyDAO.getLocationfromBillNo(billno);
		    }else {
		      if(loc==null){
		    	  loc="0";
		      }
		    }
		    
		    int oldbillid = billno;
		    CompleteAppointment completeAppointment1=pharmacyDAO.getBillDetails(oldbillid);
		    //  16 Nov 2018 For Pharmacy Repeat bill issue
			int tempclientid = 0;
		    int ispharmacypatient =0;
		    String pclientid1 = completeAppointment1.getPclientid();
		    if(!pclientid1.equals("0")){
		    	//Pharmacy User
		    	tempclientid=Integer.parseInt(pclientid1);
		    	ispharmacypatient=1;
		    }else{
		    	//HIS USER
		    	ispharmacypatient=0;
		    	tempclientid = Integer.parseInt(completeAppointment1.getClientId());
		    }
			//Check already patient and user session id available or not
			boolean flag = pharmacyDAO.checkPatientAvailableInLog(tempclientid,ispharmacypatient,loginInfo.getLoginsessionid(),0);
			if(!flag){
				//if entry not present in temp table so insert into temp table
				int tempsessionid = pharmacyDAO.insertTempPharSaleSession(tempclientid,ispharmacypatient,loginInfo.getLoginsessionid(),0,"0","0","0");
				
				//Second filter check already tempid in sale bill
				/*boolean flag2 = pharmacyDAO.checkTempIdInPharmacyBill(tempsessionid);
				if(!flag2){*/
					//It means its 1st bill not repeat bill
				    
				    
				    String vat=emrForm.getVat();
				    
				    if(emrForm.getDiscount()!=null){
				    	 
				    	if(emrForm.getDiscount().equals("")){
				    		
				    		emrForm.setDiscount("0");
				    	}
				    }
				    else {
				    	emrForm.setDiscount("0");
				    }
				    
				    double discount=Double.parseDouble(emrForm.getDiscount());
				    
				    //  10 Aug 2018
				    discount = discamt;
				    String notes=emrForm.getNotes();
				    String total=emrForm.getTotal();
				    String cgst= emrForm.getCgst();
				    String sgst= emrForm.getSgst();
				    
				    //update if balance to this
				    if(paymode!=null){
						 
						if(paymode.equals("Card")){
							
							paynote= request.getParameter("card");
						}
						if(paymode.equals("Cheque")){
							paynote= request.getParameter("chequeno");
						}
						if(paymode.equals("NEFT")){
							paynote= request.getParameter("neftid");
						}
						
					}
				    
				    
				    Priscription priscription=null;
				    CompleteAppointment appointment= new CompleteAppointment();
				    String date=DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone()).split(" ")[0];
				    String newdate = date;
				    appointment.setInvoiceTime(DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone()).split(" ")[1]);
				    String clientname="";
				    if(selectedid!=0){
				    	priscription = emrDAO.getPriscriptionParentData(selectedid);
				    	String clientid= pharmacyDAO.getBillDetails(billno).getClientId();
				    	Client client = clientDAO.getClientDetails(clientid);
				 	    clientname=client.getTitle()+" "+client.getFirstName()+" "+client.getLastName();
				 	    
				 	    String tpid="";
					    String payby="0";
					    if(client.getWhopay()!=null){
					    	
					    	if(client.getWhopay().equals("Client")){
					    		   tpid="0";
					    		   payby="0";
					    	}else {
					    		payby="1";
					    		tpid=client.getTypeName();
					    	}
					    }else {
					    	tpid="0";
					    	payby="0";
					    }
					    
					    priscription.setWhopay(payby);
					    priscription.setTpid(tpid);
					    
				    	appointment.setClientId(priscription.getClientId());
				    	appointment.setPclientid("0");
					    appointment.setPayBuy(client.getWhopay());
					    appointment.setCharges(""+total+"");
					    appointment.setChargeType("");
					    appointment.setVat(Double.parseDouble(vat));
					    appointment.setDiscount(discount);
					    appointment.setTotal(Double.parseDouble(total));
					    appointment.setNotes(notes);
					    appointment.setPriscid(selectedid);
					    appointment.setInvoiceDate(date);
					    
				    } else {
				    		Client client = new Client();
				    		String pclientid= pharmacyDAO.getBillDetails(billno).getPclientid();
				    		if(!pclientid.equals("0")){
				    			priscription = pharmacyDAO.getPharmacyPatient(pclientid);
				    			clientname=priscription.getFullname();
				    	    	appointment.setClientId("0");
						    	appointment.setPclientid(pclientid);
						    	priscription.setWhopay("0");
								priscription.setTpid("0");
								priscription.setClientId("0");
						    }else{
						    	priscription = new Priscription();
						    	String ipdopd=pharmacyDAO.getClientIDFromBillNo(Integer.parseInt(oldbill));
				    	    	client = clientDAO.getPatient(Integer.parseInt(ipdopd));
				    	    	priscription.setClientId(ipdopd);
				    	    	clientname=client.getTitle()+" "+client.getFirstName()+" "+client.getLastName();
				    	    	appointment.setClientId(""+client.getId());
						    	appointment.setPclientid("0");
						    	String tpid="";
							    String payby="0";
							    if(client.getWhopay()!=null){
							    	
							    	if(client.getWhopay().equals("Client")){
							    		   tpid="0";
							    		   payby="0";
							    	}else {
							    		payby="1";
							    		tpid=client.getTypeName();
							    	}
							    }else {
							    	tpid="0";
							    	payby="0";
							    }
							    
							    priscription.setWhopay(payby);
							    priscription.setTpid(tpid);
						    }
				    	    appointment.setPayBuy("Client");
				    	    appointment.setCharges(""+total+"");
						    appointment.setChargeType("");
						    appointment.setVat(Double.parseDouble(vat));
						    appointment.setDiscount(discount);
						    appointment.setTotal(Double.parseDouble(total));
						    appointment.setNotes(notes);
						    appointment.setInvoiceDate(date);
					}
				    	appointment.setLocation(loc);
				    	appointment.setCgst(cgst);
				    	appointment.setSgst(sgst);
				    	appointment.setRefundAmt(0);
				    	appointment.setUserid(loginInfo.getUserId());
				    	appointment.setNotes(emrForm.getNotes());
				    
				   double nowbal= Double.parseDouble(refund);
				    if(paymode.equals("Credit")){
				    	double prebalance =  pharmacyDAO.getBillBalance(oldbill);
				    	String bal=  String.valueOf(prebalance);
				 	    nowbal =Double.parseDouble(refund) - Math.abs(Double.parseDouble(bal));
				 		if(nowbal>=0 && Double.parseDouble(bal)!=0){
				 			int f=pharmacyDAO.updateRefundBillBalance(oldbill,String.valueOf(refund));
					 	} else {
				 			 double t =Double.parseDouble(bal) - Double.parseDouble(refund);
				 			 int f=pharmacyDAO.updateRefundBillBalance(oldbill,String.valueOf(refund));
					 	}
				 		if( Double.parseDouble(bal)>= Double.parseDouble(refund)){
				 			 nowbal =0;
				 			 appointment.setRefundAmt(Double.parseDouble(refund));
				 		}
				 		prebalance =  pharmacyDAO.getBillBalance(oldbill);
				 		bal= String.valueOf(prebalance);
				 		emrForm.setBalance(bal);
				    } 
				    
				    
		
			    	String subbtotal = emrForm.getSubbtotal();
				    if(subbtotal!=null){
				    	if(subbtotal.equals("")){
				    		subbtotal="0";
				    	}
				    }else{
				    	subbtotal="0";
				    }
				    
				    //  12-nov-2018 actual amount store, discttype and actual discount amount in table 
				    appointment.setDisc_type("1");
				    appointment.setActualdiscount(discper);
				    appointment.setActualtotal(subbtotal);
				    appointment.setTempsessionid(tempsessionid);
				    
				    //  17-12-2018
				    String grosstotal = emrForm.getGrosstotal();
				    String grosssubtotal = emrForm.getGrosssubtotal();
				    appointment.setGrosstotal(grosstotal);
				    appointment.setGrosssubtotal(grosssubtotal);
				    appointment.setInitalpaymode(paymode);
				    appointment.setFinalpaymode(paymode);
				    
				    appointment.setBedid(completeAppointment1.getBedid());
		    		appointment.setWardid(completeAppointment1.getWardid());
		    		appointment.setPhar_ipdid(completeAppointment1.getPhar_ipdid());
		    		
		    		String phar_practid = emrForm.getPhar_practid();
		    		String practitionerName = emrForm.getPractitionerName();
		    		appointment.setPractitionerId(phar_practid);
		    		appointment.setPractitionerName(practitionerName);
				    billno=pharmacyDAO.addMedicineBill(appointment);
				    int returnid= pharmacyDAO.getMaxLastReturnBillId();
				    int res = pharmacyDAO.setIsReturn(billno,returnid+1);
				    res=pharmacyDAO.setReturnBillNumber(oldbill,billno);
				    res=pharmacyDAO.updateBillCreditReturn(billno,refund);
				    //  29-11-2019
			       /* if(paymode.equals("Credit")){
			        	double balance= pharmacyDAO.getBillBalance(oldbill);
	        			if(balance>0){
	        				double totalminus =  balance-Double.parseDouble(refund);
	        				int resss = pharmacyDAO.updateBalanceByBill(oldbill, DateTimeUtils.changeFormat(totalminus));
	        			}
			        }*/
				    //update in priscription 
				    //int result=pharmacyDAO.updatePriscEmrBill(billno,selectedid);
				    //update status
				    //result=pharmacyDAO.updateDeliverStatus("1", String.valueOf(selectedid));
				    UserProfile userProfile=null;
				    
				    for(Priscription prisc:emrForm.getMedicines()){
				    	
				    	if(prisc==null){
				    		continue;
				    	}
				    	if(prisc.getId()==0){
				    		continue;
				    	}
				    	if(prisc.getReturnqty()==0){
				    		continue;
				    	}
				    	
				    	priscription.setMdicinenameid(prisc.getMdicinenameid());
				    	
				    //	int t=pharmacyDAO.updateMedicineReturnQty(prisc.getId(), prisc.getReturnqty());
				    	priscription.setProduct_id(prisc.getProduct_id());
				    	Product product=inventoryProductDAO.getProduct(prisc.getProduct_id());
				    	priscription.setSaleqty(prisc.getReturnqty());
				    	priscription.setReqqty(prisc.getReqqty());
				    	priscription.setMrp(product.getSale_price());
				    	/*if(selectedid!=0){
				    		userProfile= profileDAO.getUserprofileDetails(Integer.parseInt(priscription.getPrectionerid()));
					    	priscription.setFullname(userProfile.getFullname());
				    	} else {
				    		priscription.setFullname(priscription.getPractitionername());
				    	}*/
				    	priscription.setFullname(practitionerName);
				    	priscription.setClientname(clientname);
				    	priscription.setDate(date);
				    	
				    	String indidiscount = prisc.getIndidiscount(); 
				    	if(indidiscount!=null){
					    	if(indidiscount.equals("")){
					    		indidiscount="0";
					    	}
					    }else{
					    	indidiscount="0";
					    }
				    	
				    	//  26 March 2018
				    	double tvat= Double.parseDouble(product.getVat());
				    	double tot=0;
				    	try {
				    		tot= priscription.getSaleqty() * Double.parseDouble(priscription.getMrp());
						} catch (Exception e) {
							// TODO: handle exception
						}
				    	double temptot=0;
				    	double tempvat =0;
				    	tempvat=tot*Double.parseDouble(indidiscount)/100;
				    	temptot = tot - tempvat;
				    	double dividevat= 100+tvat;
				    	/*double gst= tot*tvat/dividevat;*/
				    	double gst= temptot*tvat/dividevat;
				    	double half= gst/2;
				    	
				    	priscription.setCgst(DateTimeUtils.changeFormat(Math.round(half * 100.0) / 100.0));
				    	priscription.setSgst(DateTimeUtils.changeFormat(Math.round(half * 100.0) / 100.0));
				    	priscription.setTgstamt(DateTimeUtils.changeFormat(Math.round(gst * 100.0) / 100.0));
				    	priscription.setGstper(product.getVat());
				    	priscription.setSharediscount(DateTimeUtils.changeFormat(tempvat));
				    	
				    	//akash 30-07-2020
		    			double bill_purpice=product.getIndividual_pur_price()*priscription.getSaleqty();
		    			double bill_pur_gst=product.getIndi_purchase_gst()*priscription.getSaleqty();
		    			priscription.setBill_purpice(bill_purpice);
		    			priscription.setBill_pur_gst(bill_pur_gst);
				    	
				    	int previousstock = inventoryProductDAO.getPreviousStock(priscription.getProduct_id());
				    	int result=pharmacyDAO.addMedicineCharges(priscription,billno);
				    	result=inventoryProductDAO.updateMedicineQty(priscription.getSaleqty(),priscription.getProduct_id(),1);
				    	
				    	//stock log
						String datetime = DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone());
						int qtyinout=0;
						String source ="Pharmacy Return";
						int currentstock=inventoryProductDAO.getPreviousStock(priscription.getProduct_id());
						int changeqty=priscription.getSaleqty();
						int reslog = inventoryProductDAO.insertIntoProductLog(loginInfo.getUserId(),datetime,product.getLocation(),qtyinout,priscription.getProduct_id(),product.getCatalogueid(),source,currentstock,previousstock,changeqty,"0","0",billno,0,0,"0");
		    			
						String date2 =  DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone()).split(" ")[0];
						boolean checkopningstockexist = pharmacyDAO.checkopeningstockexist(priscription.getProduct_id(),date2);
						if(!checkopningstockexist){
							int r = pharmacyDAO.saveOpeningStock(priscription.getProduct_id(),date2,previousstock,"0");
						}
				    	
				    	int tem= pharmacyDAO.updateReturnQty(priscription.getSaleqty(),prisc.getId());
				    	//update inventory
				    	
				     }
				    
				    //Direct Record Payment
				    //double tot=Double.parseDouble(total);
				    //  19 Sep 2018 generate pharmacy payment Seq No according location
				    int paymentseqno = pharmacyDAO.getPharmacyPaymentSeqNo(loc);
					paymentseqno = paymentseqno+1;
				    int result=pharmacyDAO.recordPaymentMedicine(String.valueOf(billno),0,paymode,newdate,appointment.getClientId(),"0",discount,notes,appointment.getPclientid(),loginInfo.getUserId(),loc,0,paymentseqno);
				    int paymentid = result;
				    int d=pharmacyDAO.recordRefundPayment(result,refund);
				    d=pharmacyDAO.updatePaymentNote(result, paynote);
				    //for parihar update datetime
				    String time=DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone()).split(" ")[1];
				    String datetime=  date+" "+time;
				    result =pharmacyDAO.updatePaymentDateTime(datetime,result);
				    //end
				    String paymode1=pharmacyDAO.getBillPaymode(billno);
					
					CompleteAppointment completeAppointment=pharmacyDAO.getBillDetails(billno);
					emrForm.setCommencing(completeAppointment.getInvoiceDate());
					
					emrForm.setInvoiceTime(completeAppointment.getInvoiceTime());
				    if(appointment.getPclientid().equals("0")){ //from HIS
						Client client= new Client();
						String wardname="";
						String bedname="";
						if(selectedid>0){
							priscription = emrDAO.getPriscriptionParentData(selectedid);
							session.setAttribute("parentpriscdata", priscription);
//							System.out.println(priscription.getClientId());
							String lastmodified="";
							if(priscription.getLastmodified()!=null){
								lastmodified=priscription.getLastmodified().split(" ")[0];
								priscription.setLastmodified(lastmodified);
							}
							client = clientDAO.getPatient(Integer.parseInt(priscription.getClientId()));
						 	String ipdid = emrDAO.getpriscIpdId(selectedid);
							Bed bed = bedDao.getEditIpdData(ipdid);
							
							emrForm.setIpdid(ipdid);
							if(ipdid==null || ipdid.equals("0")){
								priscription.setIpdid("0");
							}else{
								priscription.setIpdid(ipdid);
							}
							wardname=ipdDAO.getIpdWardName(bed.getWardid());
							priscription.setWardname(wardname);
							bedname = ipdDAO.getIpdBedName(bed.getBedid());
							priscription.setBedname(bedname);
						}else{
							 client =  clientDAO.getPatient(Integer.parseInt(appointment.getClientId()));
							 String ipdid = pharmacyDAO.getIpdIdFromClientID(client.getId());
								String ipdoropd = "0";
								if(ipdid.equals("0")){
									priscription.setIpdid("0");
								}else{
									priscription.setIpdid(ipdid);
									Bed  bed = bedDao.getEditIpdData(ipdid);
									wardname = ipdDAO.getIpdWardName(bed.getBedid());
									bedname = ipdDAO.getIpdBedName(bed.getWardid());
									ipdoropd = ipdid;
								}
								emrForm.setIpdid(ipdid);
							}
						    int age=0;
						    String name = client.getTitle()+" "+client.getFirstName()+" "+client.getLastName();
							try {
							
								date  = DateTimeUtils.getDashboardTodayDate(loginInfo.getCountry());
								String dobyear[] = client.getDob().split("/");
								String curryear[] = date.split("/");
								  age = Integer.parseInt(curryear[2]) - Integer.parseInt(dobyear[2]);
							} catch (Exception e) {
								e.printStackTrace();
							}
							
							client.setAge(age);
							emrForm.setAbrivationid(client.getAbrivationid());
							session.setAttribute("clientinfo", client);
							// get clinic info
							Clinic clinic = new Clinic();
							ClinicDAO clinicDAO  = new JDBCClinicDAO(connection);
							clinic = clinicDAO.getCliniclistDetails(loginInfo.getClinicid());
							Location location = clinicDAO.getRegisterdLication();
							clinic.setLocationname(location.getAddress());
							String curdatetime = DateTimeUtils.getPriscDatetime(loginInfo.getTimeZone());
							clinic.setCurDateTime(curdatetime);
							clinic.setCurDateTime(appointment.getCommencing());
							session.setAttribute("clinicinfo", clinic);
						    emrForm.setName(name);
							emrForm.setWardname(wardname);
							emrForm.setBedname(bedname);
							String wardbed=wardname+"/"+bedname;
							emrForm.setWardname(wardbed);
							if(priscription.getPrectionerid()!=null){
								if(!priscription.getPrectionerid().equals("")){
								UserProfile userProfile1=userProfileDAO.getUserprofileDetails(Integer.parseInt(priscription.getPrectionerid()));
								emrForm.setPractitionerName(userProfile1.getFullname());
								}
							}
				    }else{//for pharmacy user
					    String pclientid=pharmacyDAO.getPharmacyClientidFromBill(billno);
						priscription=pharmacyDAO.getPharmacyPatient(pclientid);
						
						Clinic clinic = new Clinic();
						ClinicDAO clinicDAO  = new JDBCClinicDAO(connection);
						clinic = clinicDAO.getCliniclistDetails(loginInfo.getClinicid());
						Client client=new Client();
						client.setFirstName(priscription.getFullname());
						try {
							if(priscription.getAgeandgender()!=null){
								if(priscription.getAgeandgender()!="0"){
								String str[]=priscription.getAgeandgender().split("/");
								client.setGender(str[0]);
								client.setAge(Integer.parseInt(str[1]));
								}
							}
							
						} catch (Exception e) {
				
							e.printStackTrace();
						}
						Location location = clinicDAO.getRegisterdLication();
						client.setMobNo(priscription.getMobile());
						clinic.setLocationname(location.getAddress());
						emrForm.setWardname(priscription.getAddress());
						emrForm.setAgeandgender(priscription.getAgeandgender());
						emrForm.setPractitionerName(priscription.getPractitionername());
						emrForm.setAbrivationid(pclientid);
						
						UserProfile profile= userProfileDAO.getPharmacyUserDetails(loginInfo.getClinicid());
						String lo= pharmacyDAO.getLocationName(profile.getLocation());
						emrForm.setLocation(lo);
						String curdatetime = DateTimeUtils.getPriscDatetime(loginInfo.getTimeZone());
						clinic.setCurDateTime(curdatetime);
						clinic.setCurDateTime(priscription.getLastmodified());
						session.setAttribute("clientinfo", client);
						session.setAttribute("clinicinfo", clinic);
				    
					}
		
				    
					 
					ArrayList<Priscription> medicineChargeList = new ArrayList<Priscription>();
					if(selectedid==0 && billno==0){
						 for(Priscription prisc:emrForm.getMedicines()){
							 Priscription priscription2 = pharmacyDAO.getMedicineChargesbyid(prisc.getId(),0);
							 medicineChargeList.add(priscription2);
						 } 
					}else{
						 medicineChargeList=pharmacyDAO.getMedicineChargesList(billno,loginInfo);
					}
					  double subtotal=0;
					  double nettotal = 0;
					  for(Priscription p:medicineChargeList){
		
						  subtotal=subtotal+(Double.parseDouble(p.getMrp())*p.getSaleqty());
						  nettotal = Math.round(subtotal);
					  }
					  
					  emrForm.setSubtotal(DateTimeUtils.changeFormat(subtotal));
					  emrForm.setMedicineChargeList(medicineChargeList);
					  emrForm.setClientId(completeAppointment.getClientId());
					  
					  if(selectedid==0 && billno==0){
						  emrForm.setTotal(DateTimeUtils.changeFormat(nowbal));
					  }
					  else {
						  emrForm.setTotal(DateTimeUtils.changeFormat(completeAppointment.getTotal()));
					  }
					  emrForm.setDiscount(String.valueOf(completeAppointment.getDiscount()));
					  emrForm.setVat(String.valueOf(completeAppointment.getVat()));
					  emrForm.setBillno(billno);
					  emrForm.setRefundamt(refund);
					  emrForm.setIsreturn(1);
					  emrForm.setSelectedid(selectedid);
					  if(paynote!=null){
						  if(paynote.equals("")){
							  emrForm.setPaymode(paymode);
						  } else {
							  paymode=paymode+" ("+paynote+")";
							  emrForm.setPaymode(paymode);
						  }
					  } else {
						  emrForm.setPaymode(paymode);
					  }
					  
					  double temp=Double.parseDouble(refund);
					  emrForm.setPayamt(DateTimeUtils.changeFormat(nowbal));
					  if(nowbal==0){
						  emrForm.setPayamt(DateTimeUtils.changeFormat(completeAppointment.getRefundAmt()));
						  emrForm.setTotal(DateTimeUtils.changeFormat(completeAppointment.getRefundAmt()));
					  }
					  
					  userProfile= userProfileDAO.getPharmacyUserDetails(loginInfo.getClinicid());
					  emrForm.setClinicName(userProfile.getClinicname());
					  emrForm.setClinicaddress(userProfile.getAddress());
					  emrForm.setLandLine(userProfile.getPhone());
					  emrForm.setFullname(userProfile.getFullname());
					  emrForm.setEmail(userProfile.getEmail());
					  emrForm.setNotes(completeAppointment.getNotes());
					  emrForm.setPlace(userProfile.getCity());
					  emrForm.setWebsiteUrl(userProfile.getWebsite());
					  emrForm.setInstruction1(userProfile.getInstruction1());
					  emrForm.setInstruction2(userProfile.getInstruction2());
					  emrForm.setInstruction3(userProfile.getInstruction3());
					  emrForm.setInstruction4(userProfile.getInstruction4());
					  emrForm.setDlno(userProfile.getDlno());
					  emrForm.setTinno(userProfile.getTinno());
					  emrForm.setUserid(loginInfo.getUserId());
					  emrForm.setReference(oldbill);
					  String ddmm[]=DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone()).split(" "); 
					  
					  emrForm.setDateTime(DateTimeUtils.getCommencingDate1(ddmm[0])+" "+ddmm[1]);
					  
					  emrForm.setPlace(userProfile.getCity());
					  
					  //23 OCT 2018   ledger work
					  // ledger for credit invoice
					  ChargesAccountProcessingDAO chargesAccountProcessingDAO = new JDBCChargeAccountProcesDAO(connection);
					  double debit = completeAppointment.getTotal();
					  String clientid =completeAppointment.getClientId();
					  String pclientid=completeAppointment.getPclientid();
					  
					  if (billno > 0) {
						  String locationname = pharmacyDAO.getLocationName(completeAppointment.getLocation());
						  String serviceid = chargesAccountProcessingDAO.getLedgrServiceIds(locationname);
						  String ledgerid = chargesAccountProcessingDAO.getledgerIDNew(serviceid, "0", "0","3",0);
			
						  double lbal = chargesAccountProcessingDAO.getLedgerBalance(ledgerid);
						  lbal = lbal + debit;
						  String credit = "" + debit + "";
						  String ldebit = "0";
						  String product = "xxxxx";
						  String partyid = clientid;
						  String otherclientid =pclientid;
						  
						  String lcommencing = DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone());
						  int saveledger = chargesAccountProcessingDAO.saveLedger(partyid, product, ldebit, credit, lbal,
									ledgerid, lcommencing, "0", 0,otherclientid,"0","0",""+billno+"","0",0,0,completeAppointment.getLocation());
							
						  //second effect
						  lbal = 0;
						  credit = "0";
						  ldebit = "" + debit + "";
						  product = "xxxxx";
						  partyid = clientid;
						  otherclientid =pclientid;
						  lcommencing = DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone());
						  saveledger = chargesAccountProcessingDAO.saveLedger(partyid, product, ldebit, credit, lbal,
									ledgerid, lcommencing, "0", 0,otherclientid,"0","0",""+billno+"","0",0,0,completeAppointment.getLocation());
						}
					  	
					  	if (discount > 0) {
							String serviceid = chargesAccountProcessingDAO.getLedgrServiceIds("Discount");
							String ledgerid = chargesAccountProcessingDAO.getledgerIDNew(serviceid, "0", "0","3",0);
		
							double lbal = chargesAccountProcessingDAO.getLedgerBalance(ledgerid);
							lbal = lbal + discount;
							String credit = "" + discount + "";
							String ldebit = "0";
							String product = "" + billno + "";
							String partyid = clientid;
							String otherclientid =pclientid;
							String lcommencing = DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone());
							int saveledger = chargesAccountProcessingDAO.saveLedger(partyid, product, ldebit, credit, lbal,
									ledgerid, lcommencing, "0", 0,otherclientid,"0","0",""+billno+"","0",0,0,completeAppointment.getLocation());
							
							//second effect
							 lbal = 0;
							 credit = "0";
							 ldebit = "" + discount + "";
							 product = "xxxxx";
							 partyid = clientid;
							 otherclientid =pclientid;
							 lcommencing = DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone());
							 saveledger = chargesAccountProcessingDAO.saveLedger(partyid, product, ldebit, credit, lbal,
									ledgerid, lcommencing, "0", 0,otherclientid,"0","0",""+billno+"","0",0,0,completeAppointment.getLocation());
						}
					  	
					  	if(Double.parseDouble(vat)>0){
					  		String serviceid = chargesAccountProcessingDAO.getLedgrServiceIds("GST");
							String ledgerid = chargesAccountProcessingDAO.getledgerIDNew(serviceid, "0", "0","3",0);
		
							double lbal = chargesAccountProcessingDAO.getLedgerBalance(ledgerid);
							lbal = lbal + Double.parseDouble(vat);
							String credit = "" + Double.parseDouble(vat) + "";
							String ldebit = "0";
							String product = "" + billno + "";
							String partyid = clientid;
							String otherclientid =pclientid;
							
							String lcommencing = DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone());
							int saveledger = chargesAccountProcessingDAO.saveLedger(partyid, product, ldebit, credit, lbal,
									ledgerid, lcommencing, "0", 0,otherclientid,"0","0",""+billno+"","0",0,0,completeAppointment.getLocation());
							
							//second effect
							 lbal = 0;
							 credit = "0";
							 ldebit = "" + Double.parseDouble(vat) + "";
							 product = "xxxxx";
							 partyid = clientid;
							 otherclientid =pclientid;
							 lcommencing = DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone());
							 saveledger = chargesAccountProcessingDAO.saveLedger(partyid, product, ldebit, credit, lbal,
									ledgerid, lcommencing, "0", 0,otherclientid,"0","0",""+billno+"","0",0,0,completeAppointment.getLocation());
					  	}
					  	
					  	/*if(!paymode.equals("") || !paymode.equals("Credit")){
					  		String locationname = pharmacyDAO.getLocationName(loc);
							String serviceid = chargesAccountProcessingDAO.getLedgrServiceIds(locationname);
							String ledgerid = chargesAccountProcessingDAO.getledgerIDNew(serviceid, paymode, "0","3");
				
							double lbal = chargesAccountProcessingDAO.getLedgerBalance(ledgerid);
							lbal = lbal + Double.parseDouble(refund);
							String credit = "" + Double.parseDouble(refund) + "";
							String ldebit = "0";
							String product = "xxxxx";
							String partyid = clientid;
							String otherclientid =pclientid;
							  
							String lcommencing = DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone());
							int saveledger = chargesAccountProcessingDAO.saveLedger(partyid, product, ldebit, credit, lbal,
										ledgerid, lcommencing, "0", 0,otherclientid,"0","0","0",""+paymentid+"");
								
							//second effect
							lbal = 0;
							credit = "0";
							ldebit = "" + Double.parseDouble(refund) + "";
							product = "xxxxx";
							partyid = clientid;
							otherclientid =pclientid;
							lcommencing = DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone());
							saveledger = chargesAccountProcessingDAO.saveLedger(partyid, product, ldebit, credit, lbal,
										ledgerid, lcommencing, "0", 0,otherclientid,"0","0","0",""+paymentid+"");
					  	}*/
					  
					  session.setAttribute("billno", billno);
					  session.setAttribute("selectedid", 0);
				/*}*/
			}
		} catch (Exception e) {

			e.printStackTrace();
			
		}finally{
			connection.close();
		}
		
	    return "redirectbill";
   }

   
   
   
   
   public String refundmedicinenew() throws Exception {
	   if (!verifyLogin(request)) {

			return "login";
		}
	   int chargetempcount=0;
	   int sessinbillno =0;
	   int chargeaddedcount =0;
	   int tempclientid = 0;
	   int ispharmacypatient =0;
	   Connection connection=null;
		try {
			
			connection=Connection_provider.getconnection();
			PharmacyDAO pharmacyDAO=new JDBCPharmacyDAO(connection);
			InventoryProductDAO inventoryProductDAO=new JDBCInventoryProductDAO(connection);
			ClientDAO clientDAO=new JDBCClientDAO(connection);
			String paynote="";
		    String paymode=emrForm.getPaymode();
		    String refund = emrForm.getRefundamt();
		    String ispharmacy= request.getParameter("ispharmacy");
		    String clientid= request.getParameter("clientid");
		    String practitioner=emrForm.getPractitionerName();
		    String phar_practid = emrForm.getPhar_practid();
		    String loc =(String) session.getAttribute("location");
		    if(loc==null){
		    	loc="0";
		    }
		    
		    //  16 Nov 2018 For Pharmacy Repeat bill issue
			
		   
		    tempclientid = Integer.parseInt(clientid);
			if(ispharmacy.equals("0")){
		    	//HIS USER
				ispharmacypatient =0;
		    }else{
		    	//Pharmacy User
		    	ispharmacypatient =1;
		    }
		    
			//Check already patient and user session id available or not
			boolean flag = pharmacyDAO.checkPatientAvailableInLog(tempclientid,ispharmacypatient,loginInfo.getLoginsessionid(),0);
			if(!flag){
				//if entry not present in temp table so insert into temp table
				int tempsessionid = pharmacyDAO.insertTempPharSaleSession(tempclientid,ispharmacypatient,loginInfo.getLoginsessionid(),0,"0","0","0");
				
				//Second filter check already tempid in sale bill
				/*boolean flag2 = pharmacyDAO.checkTempIdInPharmacyBill(tempsessionid);
				if(!flag2){*/
					//It means its 1st bill not repeat bill
		    
				    String vat=emrForm.getVat();
				    
				    if(emrForm.getDiscount()!=null){
				    	 
				    	if(emrForm.getDiscount().equals("")){
				    		
				    		emrForm.setDiscount("0");
				    	}
				    }
				    else {
				    	emrForm.setDiscount("0");
				    }
				    if(vat!=null){
				    	if(vat.equals("")){
				    		vat="0";
				    	}
				    } else {
				    	vat="0";
				    }
				    
				    double discount=Double.parseDouble(emrForm.getDiscount());
				    
				    //  16 Aug 2018
				    String discamt = request.getParameter("discperamt");
				    if(discamt!=null){
				    	if(discamt.equals("")){
				    		discount =0; 
				    	}else{
				    		discount = Double.parseDouble(discamt);
				    	}
				    }else{
				    	discount = 0;
				    }
				   
				    String notes=emrForm.getNotes();
				    String total=emrForm.getTotal();
				    String cgst= emrForm.getCgst();
				    String sgst= emrForm.getSgst();
				    
				    //update if balance to this
				    if(paymode!=null){
						 
						if(paymode.equals("Card")){
							
							paynote= request.getParameter("card");
						}
						if(paymode.equals("Cheque")){
							paynote= request.getParameter("chequeno");
						}
						if(paymode.equals("NEFT")){
							paynote= request.getParameter("neftid");
						}
						
					}
				    
				    Vector<Priscription> tempmedicinelist = pharmacyDAO.getReturnTempMedicineList(tempclientid,ispharmacypatient,loginInfo.getLoginsessionid());
				    chargetempcount =  tempmedicinelist.size();
				    
				    if(chargetempcount==0){
				    	return "errorpage";
				    }
				    
				    
				    Priscription priscription=null;
				    CompleteAppointment appointment= new CompleteAppointment();
				    String date=DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone()).split(" ")[0];
				    appointment.setInvoiceTime(DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone()).split(" ")[1]);
				    String clientname="";
				            String pclientid =clientid;
				    		Client client = new Client();
				    		if(ispharmacy.equals("1")){
				    			priscription = pharmacyDAO.getPharmacyPatient(pclientid);
				    			clientname=priscription.getFullname();
				    	    	appointment.setClientId("0");
						    	appointment.setPclientid(pclientid);
						    	priscription.setWhopay("0");
								priscription.setTpid("0");
								priscription.setClientId("0");
								priscription.setDate(date);
								priscription.setUserid(loginInfo.getUserId()); 
						    }else{
						    	priscription = new Priscription();
				    	    	client = clientDAO.getPatient(Integer.parseInt(clientid));
				    	    	priscription.setClientId(clientid);
				    	    	clientname=client.getTitle()+" "+client.getFirstName()+" "+client.getLastName();
				    	    	appointment.setClientId(""+client.getId());
						    	appointment.setPclientid("0");
						    	priscription.setUserid(loginInfo.getUserId());
						    	priscription.setDate(date);
						    	String tpid="";
							    String payby="0";
							    if(client.getWhopay()!=null){
							    	
							    	if(client.getWhopay().equals("Client")){
							    		   tpid="0";
							    		   payby="0";
							    	}else {
							    		payby="1";
							    		tpid=client.getTypeName();
							    	}
							    }else {
							    	tpid="0";
							    	payby="0";
							    }
							    
							    priscription.setWhopay(payby);
							    priscription.setTpid(tpid);
						    }
				    	    appointment.setPayBuy("Client");
				    	    appointment.setCharges(""+total+"");
						    appointment.setChargeType("");
						    appointment.setVat(Double.parseDouble(vat));
						    appointment.setDiscount(discount);
						    appointment.setTotal(Double.parseDouble(total));
						    appointment.setNotes(notes);
						    appointment.setInvoiceDate(date);
				    	    appointment.setLocation(loc);
				    	    appointment.setCgst(cgst);
				    	    appointment.setSgst(sgst);
				    	    appointment.setRefundAmt(0);
				    	    appointment.setUserid(loginInfo.getUserId());
				    	    appointment.setNotes(emrForm.getNotes());
				    	    
					    	
					    	 double nowbal= Double.parseDouble(refund);
				    if(paymode.equals("Credit")){
				    	//  26-09-2019 commented because use of this code
//				    	if(!appointment.getPclientid().equals("0")){  //new patient
//				    		//  26-09-2019 balance issue
//				    		double prebalance = pharmacyDAO.getPharClientBalance("1",appointment.getPclientid());
//					 		String bal= String.valueOf(prebalance);
//					 		nowbal =Double.parseDouble(refund) - Math.abs(Double.parseDouble(bal));
//				    		
//				    		/*String bal= pharmacyDAO.getpreviousbalance(Integer.parseInt(appointment.getPclientid()));
//					 	    nowbal =Double.parseDouble(refund) - Double.parseDouble(bal);*/
//					 		 if(nowbal>=0 && Double.parseDouble(bal)!=0){
//					 			//int f=pharmacyDAO.updateRefundAllBalance(appointment.getPclientid(),String.valueOf(refund), "1");
//						 		int result= pharmacyDAO.updatebalance(Integer.parseInt(appointment.getPclientid()), String.valueOf(nowbal));
//					 		 } 
//					 		 else {
//					 			 double t =Double.parseDouble(bal) - Double.parseDouble(refund);
//					 			 //int f=pharmacyDAO.updateRefundAllBalance(appointment.getClientId(),String.valueOf(refund),"0");
//						 		 int result= pharmacyDAO.updatebalance(Integer.parseInt(appointment.getPclientid()), String.valueOf(t));
//					 			 
//					 		 }
//					 		 
//					 		 if( Double.parseDouble(bal)>= Double.parseDouble(refund)){
//					 			 nowbal =0;
//					 			 appointment.setRefundAmt(Double.parseDouble(refund));
//					 			 
//					 		 }
//					 		//bal=pharmacyDAO.getpreviousbalance(Integer.parseInt(appointment.getPclientid()));
//					 		//emrForm.setBalance(bal);
//					 		prebalance = pharmacyDAO.getPharClientBalance("1",appointment.getPclientid());
//					 		bal= String.valueOf(prebalance);
//					 		emrForm.setBalance(bal);
//					 	} else { //opd/ipd
//					 		/*String bal= pharmacyDAO.getbalofpatient(appointment.getClientId());
//					 		nowbal =Double.parseDouble(refund) - Double.parseDouble(bal);*/
//					 		double prebalance = pharmacyDAO.getPharClientBalance("0",appointment.getClientId());
//					 		String bal= String.valueOf(prebalance);
//					 		nowbal =Double.parseDouble(refund) - Math.abs(Double.parseDouble(bal));
//					 		 if(nowbal>=0 && Double.parseDouble(bal)!=0){
//					 			//int f=pharmacyDAO.updateRefundAllBalance(appointment.getClientId(),String.valueOf(refund), "0");
//						 		 int result= pharmacyDAO.updatebalpatient(String.valueOf(nowbal), appointment.getClientId());
//					 		 }
//					 		else {
//					 			 double t =Double.parseDouble(bal) - Double.parseDouble(refund);
//					 			//int f=pharmacyDAO.updateRefundAllBalance(appointment.getClientId(),String.valueOf(refund), "0");
//						 		 /*int result= pharmacyDAO.updatebalance(Integer.parseInt(appointment.getPclientid()), String.valueOf(t));*/
//					 			 int result= pharmacyDAO.updatebalpatient(String.valueOf(t), appointment.getClientId());
//					 		 }
//					 		if( Double.parseDouble(bal)>= Double.parseDouble(refund)){
//					 			 nowbal =0;
//					 			appointment.setRefundAmt(Double.parseDouble(refund));
//					 		 }
//					 		/*bal= pharmacyDAO.getbalofpatient(appointment.getClientId());
//					 		emrForm.setBalance(bal);*/
//					 		prebalance = pharmacyDAO.getPharClientBalance("0",appointment.getClientId());
//					 		bal= String.valueOf(prebalance);
//					 		emrForm.setBalance(bal);
//					    }
				    	//appointment.setTotal(nowbal);
				    	
				    } 
					
				    String subbtotal = emrForm.getSubbtotal();
				    if(subbtotal!=null){
				    	if(subbtotal.equals("")){
				    		subbtotal="0";
				    	}
				    }else{
				    	subbtotal="0";
				    }
				    //  13-nov-2018 actual amount store, discttype and actual discount amount in table 
				    appointment.setDisc_type("1");
				    appointment.setActualdiscount(DateTimeUtils.changeFormat(discount));
				    appointment.setActualtotal(subbtotal);
				    appointment.setTempsessionid(tempsessionid);
				    
				    //  17-12-2018
				    String grosstotal = emrForm.getGrosstotal();
				    String grosssubtotal = emrForm.getGrosssubtotal();
				    appointment.setGrosstotal(grosstotal);
				    appointment.setGrosssubtotal(grosssubtotal);
				    appointment.setInitalpaymode(paymode);
				    appointment.setFinalpaymode(paymode);
				    
				    appointment.setPractitionerId(phar_practid);
				    appointment.setPractitionerName(practitioner);
				    
				    int billno=pharmacyDAO.addMedicineBill(appointment);
				    sessinbillno = billno;
				    int returnid= pharmacyDAO.getMaxLastReturnBillId();
				    int res = pharmacyDAO.setIsReturn(billno,returnid+1);
				   
				    res=pharmacyDAO.updateBillCreditReturn(billno,refund);
				    
				    String returnbillids="0";
				   /* for(Priscription prisc:emrForm.getMedicines()){*/
				    for(Priscription prisc1:tempmedicinelist){
				    	if(prisc1==null){
				    		int deletestatus = pharmacyDAO.deletePharmacyReturnBillData(""+sessinbillno);
				    		return "errorpage";
				    	}
				    	if(prisc1.getId()==0){
				    		int deletestatus = pharmacyDAO.deletePharmacyReturnBillData(""+sessinbillno);
				    		return "errorpage";
				    	}
				    	if(prisc1.getQty()==0){
				    		int deletestatus = pharmacyDAO.deletePharmacyReturnBillData(""+sessinbillno);
				    		return "errorpage";
				    	}
				    	String mdicinenameid =request.getParameter("mdicinenameid"+prisc1.getChargeid());
				    	String product_id =request.getParameter("product_id"+prisc1.getChargeid());
				    	String billno1 = request.getParameter("billno"+prisc1.getChargeid());
				    	String indidiscount = request.getParameter("indidiscount"+prisc1.getChargeid()); 
				    	String oldchargeid = request.getParameter("id"+prisc1.getChargeid());
				    	priscription.setMdicinenameid(mdicinenameid);
				    	priscription.setProduct_id(product_id);
				    	Product product=inventoryProductDAO.getProduct(product_id);
				    	priscription.setSaleqty(prisc1.getQty());
				    	priscription.setReqqty(prisc1.getQty());
				    	priscription.setMrp(product.getSale_price());
				    	priscription.setClientname(clientname);
				    	priscription.setBillno(billno1);
				    	priscription.setDate(date);
				    	priscription.setFullname(practitioner);
				    	priscription.setChargeid(oldchargeid);
				    	if(indidiscount!=null){
					    	if(indidiscount.equals("")){
					    		indidiscount="0";
					    	}
					    }else{
					    	indidiscount="0";
					    }
				    	
				    	//  26 March 2018
				    	double tvat= Double.parseDouble(product.getVat());
				    	double tot=0;
				    	try {
				    		tot= priscription.getSaleqty() * Double.parseDouble(priscription.getMrp());
						} catch (Exception e) {
							// TODO: handle exception
						}
				    	double temptot=0;
				    	double tempvat =0;
				    	tempvat=tot*Double.parseDouble(indidiscount)/100;
				    	temptot = tot - tempvat;
				    	double dividevat= 100+tvat;
				    	/*double gst= tot*tvat/dividevat;*/
				    	double gst= temptot*tvat/dividevat;
				    	double half= gst/2;
				    	
				    	priscription.setCgst(DateTimeUtils.changeFormat(Math.round(half*100.0)/100.0));
				    	priscription.setSgst(DateTimeUtils.changeFormat(Math.round(half*100.0)/100.0));
				    	priscription.setTgstamt(DateTimeUtils.changeFormat(Math.round(gst*100.0)/100.0));
				    	priscription.setGstper(product.getVat());
				    	priscription.setSharediscount(DateTimeUtils.changeFormat(tempvat));
				    	int previousstock = inventoryProductDAO.getPreviousStock(priscription.getProduct_id());
				    	//akash 30-07-2020
		    			double bill_purpice=product.getIndividual_pur_price()*priscription.getSaleqty();
		    			double bill_pur_gst=product.getIndi_purchase_gst()*priscription.getSaleqty();
		    			priscription.setBill_purpice(bill_purpice);
		    			priscription.setBill_pur_gst(bill_pur_gst);
		    			
				    	int chargeid=pharmacyDAO.addMedicineCharges(priscription,billno);
				    	
				    	if(chargeid==0){
		    				int deletebill =pharmacyDAO.deletePharmacyReturnBillData(""+sessinbillno);
		    				return "errorpage";
		    			}
				    	
				    	int result=inventoryProductDAO.updateMedicineQty(priscription.getSaleqty(),priscription.getProduct_id(),1);
				    	
				    	//stock log
						String datetime = DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone());
						int qtyinout=0;
						String source ="Pharmacy Return";
						int currentstock=inventoryProductDAO.getPreviousStock(priscription.getProduct_id());
						int changeqty=priscription.getSaleqty();
						int reslog = inventoryProductDAO.insertIntoProductLog(loginInfo.getUserId(),datetime,product.getLocation(),qtyinout,priscription.getProduct_id(),product.getCatalogueid(),source,currentstock,previousstock,changeqty,"0","0",billno,0,0,"0");
		    			
						String date2 =  DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone()).split(" ")[0];
						boolean checkopningstockexist = pharmacyDAO.checkopeningstockexist(priscription.getProduct_id(),date2);
						if(!checkopningstockexist){
							int r = pharmacyDAO.saveOpeningStock(priscription.getProduct_id(),date2,previousstock,"0");
						}
						String id = request.getParameter("id"+prisc1.getChargeid());
				        result =pharmacyDAO.updateReturnQty(priscription.getSaleqty(),Integer.parseInt(id));
				        returnbillids =returnbillids+","+billno1;
				        
				        //int tem= pharmacyDAO.updateReturnQty(priscription.getSaleqty(),prisc.getId());
				        
				    	//  16 Aug 2018 
				        if(paymode.equals("Credit")){
				        	String totalrefund = request.getParameter("totalrefundrs"+prisc1.getChargeid());
				        	double totalminus=0;
				        	double balance= pharmacyDAO.getBillBalance(billno1);
				        	/*String totalrefund = prisc.getTotalrefundrs();*/
				        	if(totalrefund!=null){
				        		if(!totalrefund.equals("")){
				        			if(balance>0){
				        				totalminus =  balance-Double.parseDouble(totalrefund);
				        				int resss = pharmacyDAO.updateBalanceByBill(billno1, DateTimeUtils.changeFormat(totalminus));
				        			}
				        		}
				        	}
				        }
				    	
				     }
				    
				    chargeaddedcount = pharmacyDAO.getAllPharmacyChargeCount(billno);
					   
				    if(chargeaddedcount!=chargetempcount){
				    	int deletebill =pharmacyDAO.deletePharmacyReturnBillData(""+sessinbillno);
	    				return "errorpage";
				    }
				   
				    //Direct Record Payment
				    //double tot=Double.parseDouble(total);
				    //  19 Sep 2018 generate pharmacy payment Seq No according location
				    int paymentseqno = pharmacyDAO.getPharmacyPaymentSeqNo(loc);
					paymentseqno = paymentseqno+1;
				    int result=pharmacyDAO.recordPaymentMedicine(String.valueOf(billno),0,paymode,date,appointment.getClientId(),"0",discount,notes,appointment.getPclientid(),loginInfo.getUserId(),loc,0,paymentseqno);
				    int paymentid=result;
				    int d=pharmacyDAO.recordRefundPayment(result,refund);
				    d=pharmacyDAO.updatePaymentNote(result, paynote);
				    
				    int xx = pharmacyDAO.updateBillRefundBillids(billno,returnbillids); 
				    
				    CompleteAppointment completeAppointment2 = pharmacyDAO.getBillIpdIdfromMultiId(returnbillids);
					if(completeAppointment2.getStatus().equals("1")){
						int xxx = pharmacyDAO.updatePharmacyRefundIpdId(completeAppointment2.getBedid(),completeAppointment2.getWardid(),completeAppointment2.getPhar_ipdid(),billno);
					}
					session.setAttribute("billno", billno);
					session.setAttribute("selectedid", 0);
					
					String returnrequestid = emrForm.getReturnrequestid();
					String returnchargeid = emrForm.getReturnchargeid();
					
					if(returnrequestid!=null){
						if(!(returnrequestid.equals("") || returnrequestid.equals("0"))){
							int res11 = pharmacyDAO.updateReturnRequestStatus(returnrequestid,returnchargeid,billno);
							Priscription priscription2 = pharmacyDAO.getNursePrescriptionParent(returnrequestid);
							int res111 = pharmacyDAO.updateBillIpdId(priscription2.getIpdid(),billno);
						}
					}
					
					 //23 OCT 2018   ledger work
					  // ledger for credit invoice
					  ChargesAccountProcessingDAO chargesAccountProcessingDAO = new JDBCChargeAccountProcesDAO(connection);
					  CompleteAppointment completeAppointment=pharmacyDAO.getBillDetails(billno);
					  double debit = completeAppointment.getTotal();
					  clientid =completeAppointment.getClientId();
					  pclientid=completeAppointment.getPclientid();
					  
					  if (billno > 0) {
						  String locationname = pharmacyDAO.getLocationName(completeAppointment.getLocation());
						  String serviceid = chargesAccountProcessingDAO.getLedgrServiceIds(locationname);
						  String ledgerid = chargesAccountProcessingDAO.getledgerIDNew(serviceid, "0", "0","3",0);
			
						  double lbal = chargesAccountProcessingDAO.getLedgerBalance(ledgerid);
						  lbal = lbal + debit;
						  String credit = "" + debit + "";
						  String ldebit = "0";
						  String product = "xxxxx";
						  String partyid = clientid;
						  String otherclientid =pclientid;
						  
						  String lcommencing = DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone());
						  int saveledger = chargesAccountProcessingDAO.saveLedger(partyid, product, ldebit, credit, lbal,
									ledgerid, lcommencing, "0", 0,otherclientid,"0","0",""+billno+"","0",0,0,completeAppointment.getLocation());
							
						  //second effect
						  lbal = 0;
						  credit = "0";
						  ldebit = "" + debit + "";
						  product = "xxxxx";
						  partyid = clientid;
						  otherclientid =pclientid;
						  lcommencing = DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone());
						  saveledger = chargesAccountProcessingDAO.saveLedger(partyid, product, ldebit, credit, lbal,
									ledgerid, lcommencing, "0", 0,otherclientid,"0","0",""+billno+"","0",0,0,completeAppointment.getLocation());
						}
					  	
					  	if (discount > 0) {
							String serviceid = chargesAccountProcessingDAO.getLedgrServiceIds("Discount");
							String ledgerid = chargesAccountProcessingDAO.getledgerIDNew(serviceid, "0", "0","3",0);
		
							double lbal = chargesAccountProcessingDAO.getLedgerBalance(ledgerid);
							lbal = lbal + discount;
							String credit = "" + discount + "";
							String ldebit = "0";
							String product = "" + billno + "";
							String partyid = clientid;
							String otherclientid =pclientid;
							String lcommencing = DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone());
							int saveledger = chargesAccountProcessingDAO.saveLedger(partyid, product, ldebit, credit, lbal,
									ledgerid, lcommencing, "0", 0,otherclientid,"0","0",""+billno+"","0",0,0,completeAppointment.getLocation());
							
							//second effect
							 lbal = 0;
							 credit = "0";
							 ldebit = "" + discount + "";
							 product = "xxxxx";
							 partyid = clientid;
							 otherclientid =pclientid;
							 lcommencing = DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone());
							 saveledger = chargesAccountProcessingDAO.saveLedger(partyid, product, ldebit, credit, lbal,
									ledgerid, lcommencing, "0", 0,otherclientid,"0","0",""+billno+"","0",0,0,completeAppointment.getLocation());
						}
					  	
					  	if(Double.parseDouble(vat)>0){
					  		String serviceid = chargesAccountProcessingDAO.getLedgrServiceIds("GST");
							String ledgerid = chargesAccountProcessingDAO.getledgerIDNew(serviceid, "0", "0","3",0);
		
							double lbal = chargesAccountProcessingDAO.getLedgerBalance(ledgerid);
							lbal = lbal + Double.parseDouble(vat);
							String credit = "" + Double.parseDouble(vat) + "";
							String ldebit = "0";
							String product = "" + billno + "";
							String partyid = clientid;
							String otherclientid =pclientid;
							
							String lcommencing = DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone());
							int saveledger = chargesAccountProcessingDAO.saveLedger(partyid, product, ldebit, credit, lbal,
									ledgerid, lcommencing, "0", 0,otherclientid,"0","0",""+billno+"","0",0,0,completeAppointment.getLocation());
							
							//second effect
							 lbal = 0;
							 credit = "0";
							 ldebit = "" + Double.parseDouble(vat) + "";
							 product = "xxxxx";
							 partyid = clientid;
							 otherclientid =pclientid;
							 lcommencing = DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone());
							 saveledger = chargesAccountProcessingDAO.saveLedger(partyid, product, ldebit, credit, lbal,
									ledgerid, lcommencing, "0", 0,otherclientid,"0","0",""+billno+"","0",0,0,completeAppointment.getLocation());
					  	}
					  	
					  	/*if(!paymode.equals("") || !paymode.equals("Credit")){
					  		String locationname = pharmacyDAO.getLocationName(loc);
							String serviceid = chargesAccountProcessingDAO.getLedgrServiceIds(locationname);
							String ledgerid = chargesAccountProcessingDAO.getledgerIDNew(serviceid, paymode, "0","3");
				
							double lbal = chargesAccountProcessingDAO.getLedgerBalance(ledgerid);
							lbal = lbal + Double.parseDouble(refund);
							String credit = "" + Double.parseDouble(refund) + "";
							String ldebit = "0";
							String product = "xxxxx";
							String partyid = clientid;
							String otherclientid =pclientid;
							  
							String lcommencing = DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone());
							int saveledger = chargesAccountProcessingDAO.saveLedger(partyid, product, ldebit, credit, lbal,
										ledgerid, lcommencing, "0", 0,otherclientid,"0","0","0",""+paymentid+"");
								
							//second effect
							lbal = 0;
							credit = "0";
							ldebit = "" + Double.parseDouble(refund) + "";
							product = "xxxxx";
							partyid = clientid;
							otherclientid =pclientid;
							lcommencing = DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone());
							saveledger = chargesAccountProcessingDAO.saveLedger(partyid, product, ldebit, credit, lbal,
										ledgerid, lcommencing, "0", 0,otherclientid,"0","0","0",""+paymentid+"");
					  	}*/
				}
			/*}*/
			int res = pharmacyDAO.deleteTempReturnPharmacyData(""+tempclientid,""+ispharmacypatient,loginInfo.getLoginsessionid());
		} catch (Exception e) {
			e.printStackTrace();
			log.debug("@@@@@@@@@@@@@@@@@@@@@@@"+e.getMessage());
 			if(connection==null){
				connection=Connection_provider.getconnection();
			}
			PharmacyDAO pharmacyDAO = new JDBCPharmacyDAO(connection);
			int deletestatus = pharmacyDAO.deletePharmacyReturnBillData(""+sessinbillno);
			int res = pharmacyDAO.deleteTempReturnPharmacyData(""+tempclientid,""+ispharmacypatient,loginInfo.getLoginsessionid());
			SmsService s = new SmsService();
		    //s.sendSms(e.getMessage()+"bill no:"+sessinbillno+" "+loginInfo.getClinicUserid(), "9764434837", loginInfo, new EmailLetterLog());
		    //s.sendSms(e.getMessage()+"bill no:"+sessinbillno+" "+loginInfo.getClinicUserid(), "8055056015", loginInfo, new EmailLetterLog());
		    return "errorpage";
		}finally{
			if(connection==null){
				connection=Connection_provider.getconnection();
				PharmacyDAO pharmacyDAO = new JDBCPharmacyDAO(connection);
				int deletestatus = pharmacyDAO.deletePharmacyReturnBillData(""+sessinbillno);
				int res = pharmacyDAO.deleteTempReturnPharmacyData(""+tempclientid,""+ispharmacypatient,loginInfo.getLoginsessionid());
				return "errorpage";
			}
			connection.close();
		}
		
	    return "redirectbill";
   }

   
   
   
   
   public String reorderbill() throws Exception {
	   if (!verifyLogin(request)) {

			return "login";
		}
	   Connection connection=null;  
		try {
			connection=Connection_provider.getconnection();
			EmrDAO emrDAO=new JDBCEmrDAO(connection);
			ClientDAO clientDAO=new JDBCClientDAO(connection);
			PharmacyDAO pharmacyDAO=new JDBCPharmacyDAO(connection);
			UserProfileDAO profileDAO=new JDBCUserProfileDAO(connection);
			InventoryProductDAO productDAO=new JDBCInventoryProductDAO(connection);
		    int selectedid=emrForm.getSelectedid();
		    String paymode=emrForm.getPaymode();
		    String bill=request.getParameter("billno"); 
		    if(bill!=null) {
		    	 
		    	if(bill.equals("")){
		    		 bill="0";
		    	}
		    }
		    int billno=Integer.parseInt(bill);
		    String vat=emrForm.getVat();
		    
		    if(emrForm.getDiscount()!=null){
		    	 
		    	if(emrForm.getDiscount().equals("")){
		    		
		    		emrForm.setDiscount("0");
		    	}
		    }
		    else {
		    	emrForm.setDiscount("0");
		    }
		    
		    double discount=Double.parseDouble(emrForm.getDiscount());
		    String notes=emrForm.getNotes();
		    String total=emrForm.getTotal();
		    
		    Priscription priscription=null;
		    CompleteAppointment appointment=new CompleteAppointment();
		    String date=DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone()).split(" ")[0];
		    String lastmodified=DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone());
		    String clientname="";
		    if(selectedid!=0){
		    	priscription = emrDAO.getPriscriptionParentData(selectedid);
		    	priscription.setLastmodified(lastmodified);
		    	selectedid=pharmacyDAO.addPriscriptionForBill(priscription);
		    	Client client=clientDAO.getClientDetails(priscription.getClientId());
		 	    clientname=client.getTitle()+" "+client.getFirstName()+" "+client.getLastName();
		 	    String tpid="";
			    String payby="0";
			    if(client.getWhopay()!=null){
			    	
			    	if(client.getWhopay().equals("Client")){
			    		   tpid="0";
			    		   payby="0";
			    	}else {
			    		payby="1";
			    		tpid=client.getTypeName();
			    	}
			    }else {
			    	tpid="0";
			    	payby="0";
			    }
			    priscription.setWhopay(payby);
			    priscription.setTpid(tpid);
		 	    
		    	appointment.setClientId(priscription.getClientId());
			    appointment.setPayBuy(client.getWhopay());
			    appointment.setCharges(""+total+"");
			    appointment.setChargeType("");
			    appointment.setVat(Double.parseDouble(vat));
			    appointment.setDiscount(discount);
			    double tot= Double.parseDouble(total);
			    appointment.setTotal(tot);
			    appointment.setNotes(notes);
			    appointment.setPriscid(selectedid);
			    appointment.setInvoiceDate(date);
		    } else {
		    	    String pclientid=pharmacyDAO.getPharmacyClientidFromBill(billno);
				    priscription=pharmacyDAO.getPharmacyPatient(pclientid);
		    	    clientname=priscription.getFullname();
		    	    appointment.setClientId("0");
		    	    appointment.setPclientid(priscription.getPclientid());
				    appointment.setPayBuy("Client");
				    appointment.setCharges(""+total+"");
				    appointment.setChargeType("");
				    appointment.setVat(Double.parseDouble(vat));
				    appointment.setDiscount(discount);
				    double tot= Double.parseDouble(total);
				    appointment.setTotal(tot);
				    appointment.setNotes(notes);
				    appointment.setInvoiceDate(date);
				    
				    priscription.setWhopay("0");
				    priscription.setTpid("0");
				    
		    }
		    appointment.setUserid(loginInfo.getUserId());
		   
		    billno=pharmacyDAO.addMedicineBill(appointment);
		    
		    //update in priscription 
		    int result=pharmacyDAO.updatePriscEmrBill(billno,String.valueOf(selectedid));
		    //update status
		    result=pharmacyDAO.updateDeliverStatus("1", String.valueOf(selectedid));
		    UserProfile userProfile=null;
		    
		    for(Priscription prisc:emrForm.getMedicines()){
		    	
		    	priscription.setMdicinenameid(prisc.getMdicinenameid());
		    	
		    	Product product=productDAO.getMedicineProductDetails(priscription.getMdicinenameid());
		    	priscription.setSaleqty(prisc.getSaleqty());
		    	priscription.setReqqty(prisc.getReqqty());
		    	priscription.setMrp(product.getSale_price());
		    	if(selectedid!=0){
		    		userProfile= profileDAO.getUserprofileDetails(Integer.parseInt(priscription.getPrectionerid()));
			    	priscription.setFullname(userProfile.getFullname());
		    	} else {
		    		priscription.setFullname(priscription.getPractitionername());
		    	}
		    	priscription.setClientname(clientname);
		    	priscription.setDate(date);
		    	result=pharmacyDAO.addMedicineCharges(priscription,billno);
		    	
		    	//update inventory
		    	
		    	result=productDAO.updateMedicineQty(priscription.getSaleqty(),priscription.getMdicinenameid(),0);
		    	
		     }
		    
		    //Direct Record Payment
		    String loc=(String) session.getAttribute("location");
		    if(loc==null){
		    	loc="0";
		    }
		    //  19 Sep 2018 generate pharmacy payment Seq No according location
		    int paymentseqno = pharmacyDAO.getPharmacyPaymentSeqNo(loc);
			paymentseqno = paymentseqno+1;
		    result=pharmacyDAO.recordPaymentMedicine(String.valueOf(billno),Double.parseDouble(total),paymode,date,appointment.getClientId(),"0",discount,notes,appointment.getPclientid(),loginInfo.getUserId(),loc,0,paymentseqno);
			session.setAttribute("billno", billno);
		    
			session.setAttribute("clientid", priscription.getClientId());
		    session.setAttribute("selectedid",selectedid);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		 finally {
			 connection.close();
		 }
		 return "redirectbill";
   }
   
   
   public String plist() throws Exception{
	   if (!verifyLogin(request)) {

			return "login";
		}
	   Connection connection=null;
	   try {
		   connection=Connection_provider.getconnection();
		   PharmacyDAO pharmacyDAO=new JDBCPharmacyDAO(connection);
		   
		   String fromdate= emrForm.getFromdate();
		   String todate= emrForm.getTodate();
		   if(fromdate!=null){
			  
			    if(fromdate.equals("")){
			    	
			    	SimpleDateFormat dateFormat= new SimpleDateFormat("yyyy-MM-dd");
			    	Calendar calendar=Calendar.getInstance();
			    	fromdate=dateFormat.format(calendar.getTime());
			    } else {
			    	
			    	fromdate=DateTimeUtils.getCommencingDate1(fromdate);
			    }
		   } else {
			    SimpleDateFormat dateFormat= new SimpleDateFormat("yyyy-MM-dd");
		    	Calendar calendar=Calendar.getInstance();
		    	fromdate=dateFormat.format(calendar.getTime());
		   }
		   if(todate!=null){
				  
			    if(todate.equals("")){
			    	
			    	SimpleDateFormat dateFormat= new SimpleDateFormat("yyyy-MM-dd");
			    	Calendar calendar=Calendar.getInstance();
			    	todate=dateFormat.format(calendar.getTime());
			    } else {
			    	todate=DateTimeUtils.getCommencingDate1(todate);
			    }
		   } else {
			    SimpleDateFormat dateFormat= new SimpleDateFormat("yyyy-MM-dd");
		    	Calendar calendar=Calendar.getInstance();
		    	todate=dateFormat.format(calendar.getTime());
		   }
		   
		   
		   ArrayList<Product> soldProductList=pharmacyDAO.getSaleProdutList(fromdate, todate);
		   emrForm.setSoldProductList(soldProductList);
		   
		   fromdate=DateTimeUtils.getCommencingDate1(fromdate);
		   todate=DateTimeUtils.getCommencingDate1(todate);
		   emrForm.setFromdate(fromdate);
		   emrForm.setTodate(todate);
		   
	   } catch (Exception e) {

		   e.printStackTrace();
	   }
	   finally {
		   connection.close();
	   }
	   
	   return "plist";
   }
   
   public String addtostock() throws Exception {
	   if (!verifyLogin(request)) {

			return "login";
		}
	   Connection connection=null;
	   try {

		   connection=Connection_provider.getconnection();
		   InventoryProductDAO inventoryProductDAO=new JDBCInventoryProductDAO(connection);
		   String total=request.getParameter("total");
		   StringBuffer buffer=new StringBuffer();
		   int i=0,j=0;
		   for(String str:total.split(",")){
			   
			   if(str.equals("0")){
				    continue;
			   }
			   i++;
			   Product product= inventoryProductDAO.getMedicineProductDetails(str);
			   buffer.append("<tr>");
			   buffer.append("<td>"+i+"<input type='hidden' name='allproduct["+j+"].product_id' value='"+product.getId()+"' /></td>");
			   buffer.append("<td>"+str+"</td>");
			   buffer.append("<td>"+product.getProduct_name()+"</td>");
			   buffer.append("<td>"+product.getGenericname()+"</td>");
			   buffer.append("<td class='text-right hidden'>"+product.getMrp()+ "");
			   buffer.append("<td class='text-center hidden'></td>");
			   buffer.append("<td><input type='text' placeholder='Qty' id='qty"+i+"' onchange='calAll(this.value)' name='allproduct["+j+"].qty' class='form-control'  /></td>");
			   buffer.append("<td class='hidden text-right'>Rs.<label id='subtot"+i+"'>00.00</label></td>");
			   buffer.append("<td class='hidden text-center'><a herf='#'><i class='fa fa-trash-o'></i></a></td>");
			   buffer.append("</tr>");
			   j++;
		   }
		   
		   buffer.append("<input type='hidden' value='"+i+"' id='tcount' />");
		   
		   
		   
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
   
   
   
   public String history() throws Exception{
	   if (!verifyLogin(request)) {

			return "login";
		}
	 Connection connection=null;  
	 try {
		 connection=Connection_provider.getconnection();
		 PharmacyDAO pharmacyDAO=new JDBCPharmacyDAO(connection);
		 ClientDAO clientDAO=new JDBCClientDAO(connection);
		 String clientid=request.getParameter("clientid");
		 String flag=request.getParameter("flag");
		 String fromdate=request.getParameter("fromdate");
		 String todate=request.getParameter("todate");
		 
		 fromdate=DateTimeUtils.getCommencingDate1(fromdate);
		 todate=DateTimeUtils.getCommencingDate1(todate);
		 StringBuffer buffer=new StringBuffer();
		 String ipdopdnew="-";
		 //Mrs. Khushi Gupta | Female/20 | GENRAL/18 | 9568245625
		 String data="";
		 boolean isIpd=false;
		 double balance=0;
		 String fullname="";
		 String address="";
		 String mobile="";
		 String admissionDate="";
		 double cardAmount=0;
		 double chequeAmount=0;
		 double neftAmount=0;
		 double hospital=0;
		 if(flag.equals("1")){
			 
			 Priscription client=pharmacyDAO.getPharmacyPatient(clientid);
			 data=client.getFullname()+" | "+client.getAgeandgender()+" | "+client.getAddress()+" | "+client.getMobile();
			 balance= Double.parseDouble(client.getBalance());
			 fullname= client.getFullname();
			 address= client.getAddress();
			 mobile= client.getMobile();
			 admissionDate=DateTimeUtils.getCommencingDate1(client.getDate());
			 cardAmount= pharmacyDAO.getCardPaymentAmt(fromdate, todate, clientid, 1);
			 chequeAmount= pharmacyDAO.getChequePaymentAmt(fromdate, todate, clientid, 1);
			 neftAmount= pharmacyDAO.getNeftPaymentAmt(fromdate, todate, clientid, 1);
			 hospital= pharmacyDAO.getHospitalPaymentAmt(fromdate, todate, clientid, 1);
		 } else {
			 Client client=clientDAO.getClientDetails(clientid);
			 String warlocation=pharmacyDAO.getwardlocationofClient(clientid);
			 fullname=client.getTitle()+" "+client.getFirstName()+" "+client.getLastName();
			 isIpd= warlocation.contains("/");
			 data=fullname+" | "+client.getGender()+"/"+client.getAge()+" | "+warlocation+" | "+client.getMobNo();
			 address= client.getAddress();
			 mobile= client.getMobNo();
			 balance= Double.parseDouble(client.getBalance());
			 if(isIpd){
				 String ipdid = pharmacyDAO.getIpdIdOfClient(clientid);
				 BedDao bedDao= new JDBCBedDao(connection);
				 Bed bed=bedDao.getEditIpdData(ipdid);
				 admissionDate= bed.getAdmissiondate();
				 ipdopdnew="IPD- "+ipdid;
			 }else {
				 ipdopdnew="OPD";
			 }
			 cardAmount= pharmacyDAO.getCardPaymentAmt(fromdate, todate, clientid, 0);
			 chequeAmount= pharmacyDAO.getChequePaymentAmt(fromdate, todate, clientid, 0);
			 neftAmount= pharmacyDAO.getNeftPaymentAmt(fromdate, todate, clientid, 0);
			 hospital= pharmacyDAO.getHospitalPaymentAmt(fromdate, todate, clientid, 0);
		 }
		 buffer.append(data+"~");
		 
		 double totalbill=0;
		 double totaldisc=0;
		 double totalreturn=0;
		 double totalcash=0;
		 ArrayList<Priscription> allBillList= pharmacyDAO.getAllBillListofClient(clientid,flag,fromdate,todate); 
		 for(Priscription priscription:allBillList) {
			 
			    CompleteAppointment completeAppointment=pharmacyDAO.getBillDetails(priscription.getId());
			    double payAmt= pharmacyDAO.getCahPayAmount(priscription.getId());
			    String paymode= pharmacyDAO.getBillPaymode(priscription.getId());
			    totalcash=totalcash+payAmt;
			 	buffer.append("<b style='color: #e69e18;font-weight: normal;'>Prescription Date: "+DateTimeUtils.getCommencingDate1(priscription.getDate())+" </b> ");
			 	
			 	if(completeAppointment.getIsreturn()==1){
			 		buffer.append("<b> Bill no: "+priscription.getId()+" ("+paymode+") (Sales Return) </b>");
			 		totalreturn= totalreturn+ completeAppointment.getTotal();
			 	} else {
			 		buffer.append("<b> Bill no: "+priscription.getId()+" ("+paymode+") </b>");
			 	}
			 	
			    buffer.append("<div class=''>");
			    buffer.append("<table class='table table-bordered' cellspacing='0' width='100%' style='margin-bottom: 0px;'>");
			    buffer.append("<thead>");
			    buffer.append("<tr class='tableback'>");
			    buffer.append("<th style='width: 4%;'>SR.No</th>");
			    buffer.append("<th style='width: 38%;'>Name of Drug</th>");
			    buffer.append("<th style='width: 4%;'>Pkg</th>");
			    buffer.append("<th style='width: 4%;'>Mfg</th>");
			    buffer.append("<th style='width: 10%;'>HSN No</th>");
			    buffer.append("<th style='width: 6%;'>Batch No</th>");
			    buffer.append("<th style='width: 8%;'>Exp. Dt</th>");
			    buffer.append("<th style='width: 4%;'>GST</th>");
			    buffer.append("<th style='width: 4%;'>Sale Price</th>");
			    buffer.append("<th style='width: 4%;'>Qty</th>");
			    buffer.append("<th style='width: 6%;' class='text-right'>Amount</th>");
			   buffer.append("</tr></thead>");
			   
			   ArrayList<Priscription> priscriptionlist=pharmacyDAO.getMedicineChargesList(priscription.getId(),loginInfo);
			   buffer.append(" <tbody>");
			   if(ipdopdnew.equals("OPD") || ipdopdnew.equals("-")){
				   
				    admissionDate= completeAppointment.getInvoiceDate();
			   }
			   
			   double subtotal=0;
			   int i=0;
			   
			   for(Priscription medicine:priscriptionlist){
				   
				    subtotal=subtotal+medicine.getTotal();
				    buffer.append("<tr>");
				    buffer.append("<td>"+(++i)+"</td>");
				    buffer.append("<td>"+medicine.getMdicinenametxt()+"</td>");
				    buffer.append("<td>"+medicine.getPkg()+"</td>");
				    buffer.append("<td>"+medicine.getMfg()+"</td>");
				    buffer.append("<td>"+medicine.getHsnno()+"</td>");
				    buffer.append("<td>"+medicine.getBatch_no()+"</td>");
				    buffer.append("<td>"+medicine.getExpiry_date()+"</td>");
				    buffer.append("<td>"+medicine.getVat()+"%</td>");
				    buffer.append("<td>"+medicine.getSale_price()+"</td>");
				    buffer.append("<td>"+medicine.getSaleqty()+"</td>");
				    buffer.append("<td class='text-right'>Rs."+medicine.getTotal()+"</td>");
				    buffer.append("</tr>"); 
			   }
			   
			   buffer.append("</tbody>");
			   buffer.append("</table>");
			  
			   buffer.append("<div class='' style='border-top: 1px solid #000;border-bottom: 1px solid #000;padding-left: 0px;'>");
			   buffer.append("<div class='text-right'>");
			   buffer.append("<div class='' style=''>");
			   buffer.append("<h4 style='font-size: 13px;'>Sub Total : Rs."+DateTimeUtils.changeFormat(subtotal)+"</h4>");
			   totalbill=totalbill+subtotal;
			   totaldisc=totaldisc+completeAppointment.getDiscount();
			   double billpayamt= pharmacyDAO.getPayAmount(priscription.getId());
			   buffer.append("<h4 style='color: #868686;font-size: 13px;'>Discount(%) : Rs."+completeAppointment.getDiscount()+"</h4>");
			   buffer.append("<h4 style='font-size: 13px;'>CGST : Rs."+completeAppointment.getCgst()+"</h4>");
			   buffer.append("<h4 style='font-size: 13px;'>SGST : Rs."+completeAppointment.getSgst()+"</h4>");
			   buffer.append("<h4 style='font-weight: bold;font-size: 13px;color:green;'>Balace : Rs."+completeAppointment.getBalance()+"</h4>");
			   buffer.append("<h4 style='font-weight: bold;font-size: 13px;color:green;'>Total : Rs."+completeAppointment.getTotal()+"</h4>");
			   buffer.append("<h4 style='font-weight: bold;font-size: 13px;color:green;'>Received : Rs."+DateTimeUtils.changeFormat(billpayamt)+"</h4>");
			   buffer.append("</div></div></div></div></div>");
			   
		 }
		 
		 buffer.append("~");
		 buffer.append("<div class='col-lg-8 col-md-8 col-xs-8 col-sm-8'>");
		 buffer.append("<p class='marboset'><b>Registration No :</b><span>"+clientid+"</span></p>");
		 buffer.append("<p class='marboset'><b>Patient Name :</b><span>"+fullname+"</span></p>");
		 buffer.append("<p class='marboset'><b>Address :</b><span>"+address+"</span></p>");
		 buffer.append("<p class='marboset'><b>Contact No :</b><span>"+mobile+"</span></p>");
		 if(isIpd){
			 buffer.append("<p class='marboset'><b>Admission Date :</b><span>"+admissionDate+"</span></p>");
		 }
		 buffer.append("</div>");
		 
		 buffer.append("<div class='col-lg-4 col-md-4 col-xs-4 col-sm-4'>");
		 buffer.append("<p class='marboset'><b>Bill Amount :</b><span class='pull-right'>Rs."+DateTimeUtils.changeFormat(totalbill)+"</span></p>");
		 
		 buffer.append("<p class='marboset'><b>Cash :</b><span class='pull-right'>Rs."+DateTimeUtils.changeFormat(totalcash)+"</span></p>");
		 buffer.append("<p class='marboset'><b>Card :</b><span class='pull-right'>Rs."+DateTimeUtils.changeFormat(cardAmount)+"</span></p>");
		 buffer.append("<p class='marboset'><b>Cheque :</b><span class='pull-right'>Rs."+DateTimeUtils.changeFormat(chequeAmount)+"</span></p>");
		 buffer.append("<p class='marboset'><b>NEFT/RTGS :</b><span class='pull-right'>Rs."+DateTimeUtils.changeFormat(neftAmount)+"</span></p>");
		 buffer.append("<p class='bonetamt'><b>Hospital :</b><span class='pull-right'>Rs."+DateTimeUtils.changeFormat(hospital)+"</span></p>");
		 
		 String billamt= DateTimeUtils.changeFormat(totalbill);
		 String str[]= billamt.split("\\.");
		 String roundOf="00.00";
		 if(str.length>1){
			 roundOf=str[1];
			 roundOf="00."+roundOf;
		 }
		 
		 double netamt=totalbill-totaldisc-totalreturn;
		 netamt =netamt- Double.parseDouble(roundOf);
		 
		 
		 buffer.append("<p class='marboset'><b>Return :</b><span class='pull-right'>Rs."+totalreturn+"</span></p>");
		 buffer.append("<p class='marboset'><b>Discount :</b><span class='pull-right' style='color: #d9534f'>Rs."+totaldisc+"</span></p>");
		 
		 buffer.append("<p class='marboset'><b>Balance :</b><span class='pull-right' style='color: #d9534f;'> Rs."+balance+"</span></p>");
		 buffer.append("<p class='bonetamt'><b>Round Off :</b><span class='pull-right' style='color: #d9534f;'>- Rs."+roundOf+"</span></p>");
		 buffer.append("<p><b>Net Amount :</b><span class='pull-right'>Rs."+Math.round(netamt)+"</span></p>");
		 buffer.append("</div>");
		 
		 buffer.append("~");
		 UserProfileDAO userProfileDAO= new JDBCUserProfileDAO(connection);
		 UserProfile userProfile= userProfileDAO.getPharmacyUserDetails(loginInfo.getClinicid());
		 buffer.append("<h4>"+userProfile.getClinicname()+"</h4>");
		 buffer.append("<h5>"+userProfile.getAddress()+"</h5>");
		 buffer.append("<h5>Website:"+userProfile.getWebsite()+", Email:"+userProfile.getEmail()+", Contact : "+userProfile.getPhone()+"</h5>");
		 
		 
         		 
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
   
   public String vatreport(){
	   if (!verifyLogin(request)) {

			return "login";
		}
	   return "vatreport";
   }
   public String doctorreport() throws Exception {
	   if (!verifyLogin(request)) {

			return "login";
		}
	   Connection connection=null;
	   try {
		   
		   /*ArrayList<Breadcrumbs> indentflowlist = new ArrayList<Breadcrumbs>();
			ArrayList<Breadcrumbs> indentflowlisttemp = new ArrayList<Breadcrumbs>();
			if (session.getAttribute("indentflowlist") != null) {
				indentflowlisttemp = (ArrayList<Breadcrumbs>) session.getAttribute("indentflowlist");
			}
			boolean isavilablemodule= false;
			int modulecount =0;
			for (Breadcrumbs breadcrumbs : indentflowlisttemp) {
				breadcrumbs.setIscurrent(false);
				breadcrumbs.setSqno(modulecount);
				modulecount++;
				if(breadcrumbs.getName().equals("Pharmacy Doctor Report")){
					isavilablemodule =true;
					breadcrumbs.setIscurrent(true);
					indentflowlist.add(breadcrumbs);
					break;
				}else{
					indentflowlist.add(breadcrumbs);
				}
			}
			if(!isavilablemodule){
				Breadcrumbs breadcrumbs = new Breadcrumbs();
				breadcrumbs.setName("Pharmacy Doctor Report");
				breadcrumbs.setOn(true);
				breadcrumbs.setSqno(modulecount);
				breadcrumbs.setUrllink("doctorreportPharmacy");
				breadcrumbs.setIscurrent(true);
				breadcrumbs.setShowingname("Doctor Report");
				indentflowlist.add(breadcrumbs);
			}
			session.setAttribute("indentflowlist",indentflowlist);*/
		   
		   connection=Connection_provider.getconnection();
		   PharmacyDAO pharmacyDAO=new JDBCPharmacyDAO(connection);
		   String fromdate=emrForm.getFromdate();
		   String todate=emrForm.getTodate();
		   
		   if(fromdate==null){
			   
			   SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd");
			   Calendar calendar=Calendar.getInstance();
			  /* calendar.add(Calendar.DATE, -30);*/
			   fromdate=format.format(calendar.getTime());
			   
		   } else {
			   
			   if(fromdate.equals("")){
				    
				   SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd");
				   Calendar calendar=Calendar.getInstance();
				  /* calendar.add(Calendar.DATE, -30);*/
				   fromdate=format.format(calendar.getTime());
			   } else {
				   fromdate=DateTimeUtils.getCommencingDate1(fromdate);	
			   }
		   }
		   
		   if(todate==null){
			   
			   SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd");
			   Calendar calendar=Calendar.getInstance();
			   todate=format.format(calendar.getTime());
			   
		   } else {
			   
			   if(todate.equals("")){
				    
				   SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd");
				   Calendar calendar=Calendar.getInstance();
				   todate=format.format(calendar.getTime());
			   } else {
				   todate=DateTimeUtils.getCommencingDate1(todate);	
			   }
		   }
		   
		   ChargesReportDAO chargesReportDAO = new JDBCChargesReportDAO(connection);
			DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		      Calendar cal = Calendar.getInstance();
		      String date = dateFormat.format(cal.getTime());  
		     
			 int result = chargesReportDAO.saveMisReportLog("Doctor Report",loginInfo.getUserId(),fromdate,todate,date,"doctorreport");
		   ArrayList<Priscription> doctorreportList=pharmacyDAO.getAllDoctorReportList(fromdate,todate,"0"); 
		   emrForm.setDoctorreportList(doctorreportList);
		   
		   session.setAttribute("doctorreport", doctorreportList);
		   
		   fromdate=DateTimeUtils.getCommencingDate1(fromdate);
		   todate=DateTimeUtils.getCommencingDate1(todate);
		   
		   emrForm.setFromdate(fromdate);
		   emrForm.setTodate(todate);
		   
	   } catch (Exception e) {

		   e.printStackTrace();
	   }
	   finally {
		    connection.close();
	   }
	   
	   return "doctorreport";
   }
   public String discpatientreport(){
	   if (!verifyLogin(request)) {
			return "login";
		}
	   return "discpatientreport";
   }
   public String supplierledgerreport(){
	   	if (!verifyLogin(request)) {
			return "login";
		}
	   return "supplierledgerreport";
   }
 public String pharmacysetting() throws Exception{
	 	if (!verifyLogin(request)) {
			return "login";
		}
	   Connection connection=null;
	   try {
		   
		   /*ArrayList<Breadcrumbs> indentflowlist = new ArrayList<Breadcrumbs>();
			ArrayList<Breadcrumbs> indentflowlisttemp = new ArrayList<Breadcrumbs>();
			if (session.getAttribute("indentflowlist") != null) {
				indentflowlisttemp = (ArrayList<Breadcrumbs>) session.getAttribute("indentflowlist");
			}
			boolean isavilablemodule= false;
			int modulecount =0;
			for (Breadcrumbs breadcrumbs : indentflowlisttemp) {
				breadcrumbs.setIscurrent(false);
				breadcrumbs.setSqno(modulecount);
				modulecount++;
				if(breadcrumbs.getName().equals("Pharmacy Setting")){
					isavilablemodule =true;
					breadcrumbs.setIscurrent(true);
					indentflowlist.add(breadcrumbs);
					break;
				}else{
					indentflowlist.add(breadcrumbs);
				}
			}
			if(!isavilablemodule){
				Breadcrumbs breadcrumbs = new Breadcrumbs();
				breadcrumbs.setName("Pharmacy Setting");
				breadcrumbs.setOn(true);
				breadcrumbs.setSqno(modulecount);
				breadcrumbs.setUrllink("pharmacysettingPharmacy");
				breadcrumbs.setIscurrent(true);
				breadcrumbs.setShowingname("Setting");
				indentflowlist.add(breadcrumbs);
			}
			session.setAttribute("indentflowlist",indentflowlist);*/
		   
		   connection=Connection_provider.getconnection();
		   UserProfileDAO userProfileDAO= new JDBCUserProfileDAO(connection);
		   PharmacyDAO pharmacyDAO= new JDBCPharmacyDAO(connection);
		   UserProfile userProfile= userProfileDAO.getPharmacyUserDetails(loginInfo.getClinicid());
		   emrForm.setOpddiscount(DateTimeUtils.changeFormat(userProfile.getOpddiscount()));
		   emrForm.setIpddiscount(DateTimeUtils.changeFormat(userProfile.getIpddiscount()));
		   emrForm.setPharpatientdiscount(DateTimeUtils.changeFormat(userProfile.getPharpatientdiscount()));
		   emrForm.setClinicName(userProfile.getClinicname());
		   emrForm.setDlno(userProfile.getDlno());
		   emrForm.setTinno(userProfile.getTinno());
		   emrForm.setAddress(userProfile.getAddress());
		   emrForm.setCity(userProfile.getCity());
		   emrForm.setWebsiteUrl(userProfile.getWebsite());
		   emrForm.setEmail(userProfile.getEmail());
		   emrForm.setLandLine(userProfile.getPhone());
		   emrForm.setUserid(userProfile.getUserid());
		   emrForm.setPassword(userProfile.getPassword());
		   emrForm.setInstruction1(userProfile.getInstruction1());
		   emrForm.setInstruction2(userProfile.getInstruction2());
		   emrForm.setInstruction3(userProfile.getInstruction3());
		   emrForm.setInstruction4(userProfile.getInstruction4());
		   emrForm.setProcurementType(userProfile.getProcurementType());
		   emrForm.setPrintType(userProfile.getPrintType());
		   emrForm.setIsdotmatrix(userProfile.isIsdotmatrix());
		   emrForm.setMedlimit(userProfile.getMedlimit());
		   emrForm.setNonsystembarcode(userProfile.isNonsystembarcode());
		   String ipdloc= userProfile.getIpdlocation();
		   String opdloc= userProfile.getOpdlocation();
		   String ihpatient = userProfile.getInhousepatient();
		   ArrayList<UserProfile> pharmacyUserList= userProfileDAO.getAllPharmacyUserList();
		   emrForm.setPharmacyUserList(pharmacyUserList);
		   if(userProfile.isShowletterhd()){
		   session.setAttribute("showletterhd","true");
		   }else{
			   session.setAttribute("showletterhd","false"); 
		   }
		   emrForm.setPagelimit(Integer.parseInt(userProfile.getPagelimit()));
		   ArrayList<Master> locationListPharmacy= pharmacyDAO.getAllLocation();
		   
		   ArrayList<Master> locList =  new ArrayList<Master>();
		   
		   for(Master master: locationListPharmacy){
			    
			   for(String i: ipdloc.split(",")){
				     if(i.equals("0")){
				    	 continue;
				     }
				     int r=Integer.parseInt(i);
				     if(r==master.getId()){
				    	 master.setIpd(1);
				     }
			   }
			   for(String i: opdloc.split(",")){
				     if(i.equals("0")){
				    	 continue;
				     }
				     int r=Integer.parseInt(i);
				     if(r==master.getId()){
				    	 master.setOpd(1);
				     }
			   }
			   for(String i: ihpatient.split(",")){
				     if(i.equals("0")){
				    	 continue;
				     }
				     int r=Integer.parseInt(i);
				     if(r==master.getId()){
				    	 master.setIhpatient(1);
				     }
			   }
			   
			   locList.add(master);
		   }
		   emrForm.setLocationListPharmacy(locList);
		   
		   	//String inhousepatient =  pharmacyDAO.getinhousepatientstatus();
		   
		  // emrForm.setInhousepatient(inhousepatient);
		   
		  String isfrompharmacy = request.getParameter("isfrompharmacy"); 
		  isfrompharmacy = DateTimeUtils.isNull(isfrompharmacy);
		  emrForm.setIsfrompharmacy(isfrompharmacy);
		  
		  if(isfrompharmacy.equals("1")){
			  return "pharmacysetting";
		  }else{
			  return "pharmacySettingInventory";
		  }
	   } catch (Exception e) {

		   e.printStackTrace();
	   }
	   finally {
		   
		   connection.close();
	   }
	   return "pharmacysetting";
   }
   
   public String loginaccess() throws Exception{ 
	   if (!verifyLogin(request)) {

			return "login";
		}
	   Connection connection=null;
	   try {
		   
		   String id = request.getParameter("id");
		   String status = request.getParameter("status");
		   Calendar cal = Calendar.getInstance();
	        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss.SS");
	        String dateString = sdf.format(cal.getTime());
	        
			String Accessdatetime=dateString;
			String CurrentUser=loginInfo.getUserId();
			
		   
		   connection=Connection_provider.getconnection();
		   PharmacyDAO pharmacyDAO = new JDBCPharmacyDAO(connection);
		   
		   int upd = pharmacyDAO.updateLoginaccess(id,status,Accessdatetime,CurrentUser);
		   
	   }catch (Exception e) {
		e.printStackTrace();
	}finally{
		
		connection.close();
	}
	   
	   
	   return null;
   }
   
 public String updatesetting() throws Exception {
	   
	 if (!verifyLogin(request)) {

			return "login";
		}
	   Connection connection=null;
	   try {
		   
		   connection=Connection_provider.getconnection();
		   UserProfileDAO userProfileDAO= new JDBCUserProfileDAO(connection);
		   UserProfile userProfile= new UserProfile();
		   userProfile.setClinicname(emrForm.getClinicName());
		   userProfile.setDlno(emrForm.getDlno());
		   userProfile.setTinno(emrForm.getTinno());
		   userProfile.setAddress(emrForm.getAddress());
		   userProfile.setCity(emrForm.getCity());
		   userProfile.setWebsite(emrForm.getWebsiteUrl());
		   userProfile.setEmail(emrForm.getEmail());
		   userProfile.setPhone(emrForm.getLandLine());
		   userProfile.setUserid(emrForm.getUserid());
		   userProfile.setPassword(emrForm.getPassword());
		   userProfile.setInstruction1(emrForm.getInstruction1());
		   userProfile.setInstruction2(emrForm.getInstruction2());
		   userProfile.setInstruction3(emrForm.getInstruction3());
		   userProfile.setInstruction4(emrForm.getInstruction4());
		   userProfile.setIpdlocation(emrForm.getIpdlocation());
		   userProfile.setOpdlocation(emrForm.getOpdlocation());
		   userProfile.setProcurementType(emrForm.getProcurementType());
		   userProfile.setPrintType(emrForm.getPrintType());
		   userProfile.setInhousepatient(emrForm.getInhousepatient());
		   userProfile.setNonsystembarcode(emrForm.isNonsystembarcode());
		   //lokesh
		   String printlttr= request.getParameter("letterhd");
		   String pagelimit= request.getParameter("pagelimit");
		   String isdotmatrix= request.getParameter("isdotmatrix");
		   // 
		   String medlimit=request.getParameter("medlimit");
		   
		   if(isdotmatrix!=null){
			   if(isdotmatrix.equals("1")){
				   userProfile.setIsdotmatrix(true);
			   }else{
				   userProfile.setIsdotmatrix(false);
			   }
		   }else{
			   userProfile.setIsdotmatrix(false);
		   }
		  
		   if(pagelimit==null){
			   pagelimit="6";
		   } if(pagelimit.equals("")){
			   pagelimit="6";
		   }
		   userProfile.setPagelimit(pagelimit);
		   if(printlttr==null){
			   printlttr="";
		   }
		   if(printlttr.equals("1")){
		   userProfile.setShowletterhd(true);
		   }else{
			   userProfile.setShowletterhd(false);
		   }
		   if(medlimit==null){
			   medlimit="0";
			   
		   } if (medlimit.equals("")) {
			medlimit="0";
		}
		   userProfile.setMedlimit(emrForm.getMedlimit());
		   userProfile.setOpddiscount(DateTimeUtils.convertToDouble(emrForm.getOpddiscount()));
		   userProfile.setIpddiscount(DateTimeUtils.convertToDouble(emrForm.getIpddiscount()));
		   userProfile.setPharpatientdiscount(DateTimeUtils.convertToDouble(emrForm.getPharpatientdiscount()));
		   int result= userProfileDAO.updatePharmacyProfile(loginInfo.getClinicid(),userProfile);
		   
	   } catch (Exception e) {

		   e.printStackTrace();
	   }
	   finally {
		   
		   connection.close();
	   }
	   return "setting";
	   
   }
   
   
public String newuser() throws Exception {
	if (!verifyLogin(request)) {

		return "login";
	}
	   Connection connection=null;
	  try {
		
		  connection=Connection_provider.getconnection();
		  PharmacyDAO pharmacyDAO= new JDBCPharmacyDAO(connection);
		  InventoryVendorDAO vendorDAO= new JDBCInventoryVendorDAO(connection);
		  ArrayList<Master> locationList= pharmacyDAO.getAllLocation();
		  ArrayList<Master> stateList= vendorDAO.getAllStateList();
		  StringBuffer buffer= new StringBuffer();
		  
		  String count= request.getParameter("count");
		  int index=Integer.parseInt(count);
		  buffer.append("<td>"+(index+1)+"</td>");
		  buffer.append("<td><input type='text' class='form-control' id='fullname"+index+"'  /></td>");
		  
		 
		  buffer.append("<td>");
		  
		     buffer.append("<select class='form-control' id='location"+index+"'  >");
          buffer.append("<option value='0'>Select Location</option>");		     
          
          for(Master master:locationList){
         	 
         	 buffer.append("<option value='"+master.getId()+"'>"+master.getName()+"</option>");
          }
		     buffer.append("</select>");
		  
		  buffer.append("<input type='hidden' value='0' class='form-control' id='phone"+index+"'  /></td>");
		  
		  buffer.append("<td>");
		  buffer.append("<select class='form-control' id='state"+index+"'  >");
          buffer.append("<option value='0'>Select State</option>");		     
          
          for(Master master:stateList){
         	 
         	 buffer.append("<option value='"+master.getId()+"'>"+master.getName()+"</option>");
          }
		  buffer.append("</select>");
		  buffer.append("</td>");
		  buffer.append("<td><input type='text' class='form-control' onchange='UserIdExist(this.value)' id='userid"+index+"'  /></td>");
		  
		  buffer.append("<td><input type='text' class='form-control' id='password"+index+"'  /></td>");
		  
		  buffer.append("<td>");
		  buffer.append("<div class='onoffswitch greensea'>");
		  
		  buffer.append("<label class='onoffswitch-label' for='show-offline'>");
		  buffer.append("<span class='onoffswitch-inner'></span>");
		  buffer.append(" <span class='onoffswitch-switch'></span>");
		  buffer.append("</label>");
		  buffer.append("</div>");
		  buffer.append("</td>");
		  
		  buffer.append("<td><a href='#' data-toggle='modal' data-target='#accessset'>Setting</a></td>");
		  buffer.append("<td class='text-center'><a herf='#' onclick='validatepharmacyuser("+index+")' ><i class='fa fa-save'></i></a>&nbsp;&nbsp;<a herf='#'><i class='' style='color:red;'></i></a></td>");
		  
		  
		  response.setContentType("text/html");
		  response.setHeader("Cache-Control", "no-cache");
		  response.getWriter().write(""+buffer.toString()+"");
		  
		  
	  } catch (Exception e) {

		  e.printStackTrace();
	  }  finally{
			
			connection.close();
		}
	 return null;   
   }
   
   public String saveuser() throws Exception  {
	   if (!verifyLogin(request)) {

			return "login";
		}
	   Connection connection=null;
	   try {
		   connection=Connection_provider.getconnection();
		   UserProfileDAO userProfileDAO= new JDBCUserProfileDAO(connection);
		   String fullname= request.getParameter("fullname");
		   String phone= request.getParameter("phone");
		   String userid= request.getParameter("userid");
		   String password= request.getParameter("password");
		   String location= request.getParameter("location");
		   String state =request.getParameter("state");
		   UserProfile userProfile= new UserProfile();
		   String str[]= fullname.split(" ");
		   if(str.length>1){
			   
			   userProfile.setFirstname(str[0]);
			   userProfile.setLastname(str[1]);
		   } else {
			   userProfile.setFirstname(str[0]);
			   userProfile.setLastname("");
		   }
		   
		   userProfile.setUserid(userid);
		   userProfile.setLocation(location);
		   userProfile.setPassword(password);
		   userProfile.setDiciplineName(location);
		   userProfile.setPhone(phone);
		   userProfile.setState(state);
		   userProfile.setUserType(4);
		   userProfile.setJobtitle("Pharmacist");
		   
		   
		   int j= userProfileDAO.saPharmacyUser(userProfile);
		   int selectedid = userProfileDAO.saveDetailsUserprofile(userProfile,loginInfo.getClinicid());
		   
		   //save userid and userkey in user access
		   int useraccess = userProfileDAO.saveInUserAccess(userid,""+selectedid);
		   
		   connection = DriverManager.getConnection(""+Constants.DB_HOST+":3306/admin",""+Constants.DB_USER+"",""+Constants.DB_PWD+"");
		   userProfileDAO = new JDBCUserProfileDAO(connection);
		   int result= userProfileDAO.savePractitionerToAdmin(userProfile, loginInfo.getClinicUserid());  
		   
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
   
   
   
   
   
   public String userreport() throws Exception{
	   if (!verifyLogin(request)) {

			return "login";
		}
	  	 Connection connection = null;
	  	 try {
	  		/*ArrayList<Breadcrumbs> indentflowlist = new ArrayList<Breadcrumbs>();
			ArrayList<Breadcrumbs> indentflowlisttemp = new ArrayList<Breadcrumbs>();
			if (session.getAttribute("indentflowlist") != null) {
				indentflowlisttemp = (ArrayList<Breadcrumbs>) session.getAttribute("indentflowlist");
			}
			boolean isavilablemodule= false;
			int modulecount =0;
			for (Breadcrumbs breadcrumbs : indentflowlisttemp) {
				breadcrumbs.setIscurrent(false);
				breadcrumbs.setSqno(modulecount);
				modulecount++;
				if(breadcrumbs.getName().equals("Pharmacy User Wise Report")){
					isavilablemodule =true;
					breadcrumbs.setIscurrent(true);
					indentflowlist.add(breadcrumbs);
					break;
				}else{
					indentflowlist.add(breadcrumbs);
				}
			}
			if(!isavilablemodule){
				Breadcrumbs breadcrumbs = new Breadcrumbs();
				breadcrumbs.setName("Pharmacy User Wise Report");
				breadcrumbs.setOn(true);
				breadcrumbs.setSqno(modulecount);
				breadcrumbs.setUrllink("userreportPharmacy");
				breadcrumbs.setIscurrent(true);
				breadcrumbs.setShowingname("User Wise Report");
				indentflowlist.add(breadcrumbs);
			}
			session.setAttribute("indentflowlist",indentflowlist);*/
			
				connection = Connection_provider.getconnection();
				PharmacyDAO pharmacyDAO = new JDBCPharmacyDAO(connection);
				UserProfileDAO userProfileDAO=new JDBCUserProfileDAO(connection); 
				ArrayList<UserProfile> pharmacyUserList = userProfileDAO.getAllPharmacyUserListNew();
	 			emrForm.setPharmacyUserList(pharmacyUserList);
				String fromdate=emrForm.getFromdate();
				String todate=emrForm.getTodate();
				String selectuserid=emrForm.getUserid();
				if(selectuserid==null){
					selectuserid="0";
				}
				String location=(String) session.getAttribute("location");
				if(location==null){
					location="0";
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
				String user;
				if(loginInfo.getUserType()==2||loginInfo.getJobTitle().equals("Admin")){
					user="";
				}else{
					user= loginInfo.getUserId();
				}
				
				int count = pharmacyDAO.getallusereportcount(fromdate,todate);
				pagination.setPreperties(count);
				ChargesReportDAO chargesReportDAO = new JDBCChargesReportDAO(connection);
				DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			      Calendar cal = Calendar.getInstance();
			      String date = dateFormat.format(cal.getTime());  
			     
				 int result = chargesReportDAO.saveMisReportLog("User Report",loginInfo.getUserId(),fromdate,todate,date,"userreport");
				ArrayList<Priscription> locationWiseReport= pharmacyDAO.getLocationWiseReport(fromdate,todate,location);
				emrForm.setLocationWiseReport(locationWiseReport);
				ArrayList<Priscription> dailyuserreport = pharmacyDAO.getuserreportinfo(pagination,fromdate,todate,location,user,selectuserid);
				
				double totalCard=0;
				double totalCash=0;
				double totalRefund=0;
				double totalDiscount=0;
				double totalCardRefund=0;
				double netCard=0;
				double netCash=0;
				double netReceivedTotal=0;
				double nettotal=0;
				double creditToal=0;
				double totalcheque=0;
				double totalneft=0;
				double totalcreditbyuser=0;
				double totalcreditReturn=0;
				double todayagainstcredit=0;
				double newcredit =0;
				double totalneftamtref=0;
				 double grandneftnetco=0;
				double todayonline=0;
				for(Priscription priscription:dailyuserreport){
						todayonline = todayonline + priscription.getTotalonline();
					   totalCash= totalCash+ priscription.getTotalcash();
					   totalCard= totalCard+ priscription.getTotalcard();
					   totalRefund= totalRefund+ priscription.getTotalReturn();
					   totalDiscount= totalDiscount+ priscription.getTotaldisc();
					   netCard= netCard+priscription.getNetcard();
					   netCash= netCash +priscription.getNetcash();
					   nettotal= nettotal+priscription.getNettotal();
					   creditToal=creditToal+priscription.getCreditBalance();
					   totalcheque= totalcheque +priscription.getTotalcheque();
					   totalneft = totalneft +priscription.getTotalneft();
					   totalcreditbyuser = totalcreditbyuser+Double.parseDouble(priscription.getTotalCreditbyuser());
					   totalcreditReturn = totalcreditReturn+priscription.getTotalcreditReturn();
					   todayagainstcredit = todayagainstcredit +priscription.getTodayagainstcredit();
					   newcredit = newcredit + priscription.getNewcredit();
					   totalneftamtref=totalneftamtref+priscription.getTotalreturnneft();
					   grandneftnetco=totalneft-totalneftamtref;
					
				}
				emrForm.setNewcredit(DateTimeUtils.changeFormat(newcredit));
				netReceivedTotal=totalCash+totalCard+totalneft+totalcheque+todayonline;
				emrForm.setTotalcreditReturn(totalcreditReturn);
				emrForm.setCreditTotal(DateTimeUtils.changeFormat(creditToal));
				emrForm.setNettotal(DateTimeUtils.changeFormat(nettotal));
				emrForm.setNetReceivedTotal(DateTimeUtils.changeFormat(netReceivedTotal));
				
				emrForm.setTodayonline(DateTimeUtils.changeFormat(todayonline));
				emrForm.setTodaycash(DateTimeUtils.changeFormat(totalCash));
				emrForm.setTodaycard(DateTimeUtils.changeFormat(totalCard));
				emrForm.setTodayReturn(DateTimeUtils.changeFormat(totalRefund));
				emrForm.setTodaydisc(DateTimeUtils.changeFormat(totalDiscount));
				emrForm.setNetcash(DateTimeUtils.changeFormat(netCash));
				emrForm.setNetcard(DateTimeUtils.changeFormat(netCard));
				emrForm.setChequepayment(DateTimeUtils.changeFormat(totalcheque));
				emrForm.setNeftpayment(DateTimeUtils.changeFormat(grandneftnetco));
				emrForm.setDailyuserreport(dailyuserreport);
				emrForm.setTotalCardRefund(DateTimeUtils.changeFormat(totalCardRefund));
				emrForm.setTotalcreditbyuser(DateTimeUtils.changeFormat(totalcreditbyuser));
				emrForm.setTodayagainstcredit(DateTimeUtils.changeFormat(todayagainstcredit));
				emrForm.setGrandneftnetco(totalneft);
				emrForm.setFinalrettotal(totalRefund+totalneftamtref);
				session.setAttribute("dailyuserreport", dailyuserreport);
				emrForm.setTotalneftamtref(totalneftamtref);
				pagination.setTotal_records(dailyuserreport.size());
				emrForm.setTotalRecords(count);
				emrForm.setPagerecords(String.valueOf(pagination.getTotal_records()));
				
				fromdate=DateTimeUtils.getCommencingDate1(fromdate);
				todate=DateTimeUtils.getCommencingDate1(todate);
				
				emrForm.setFromdate(fromdate);
				emrForm.setTodate(todate);
				emrForm.setLocationid(location);
				
				Clinic clinic = new Clinic();
			      ClinicDAO clinicDAO = new JDBCClinicDAO(connection);
			      clinic = clinicDAO.getCliniclistDetails(loginInfo.getClinicid());
			      AccountsDAO accountsDAO = new JDBCAccountsDAO(connection);
			      ArrayList<Clinic> locationAdressList = accountsDAO.getLocationAddress(loginInfo.getClinicid());
			      
			      emrForm.setClinicName(clinic.getClinicName());
			      emrForm.setClinicOwner(clinic.getClinicOwner());
			      emrForm.setOwner_qualification(clinic.getOwner_qualification());
			      emrForm.setLocationAdressList(locationAdressList);
			      emrForm.setAddress(clinic.getAddress());
			      emrForm.setLandLine(clinic.getLandLine());
			      emrForm.setClinicemail(clinic.getEmail());
			      emrForm.setWebsiteUrl(clinic.getWebsiteUrl());
			      emrForm.setClinicLogo(clinic.getUserImageFileName());
				
			} catch (Exception e) {
				e.printStackTrace();
			}finally{
				connection.close();
			}
		    return "userpharreport";
		   }
	   
	   public String expiryreport() {
		   if (!verifyLogin(request)) {

				return "login";
			}
	    return "expiryreport";
	   }
   
   public String viewdoctorbill() throws Exception {
	   if (!verifyLogin(request)) {

			return "login";
		}
	   Connection connection=null;
	   ArrayList<String> allBilllist=null;
	   try {
		   connection=Connection_provider.getconnection();
		   UserProfileDAO userProfileDAO=new JDBCUserProfileDAO(connection);
		   PharmacyDAO pharmacyDAO=new JDBCPharmacyDAO(connection);
		   String fromdate=request.getParameter("fromdate");
		   String todate=request.getParameter("todate");
		   String doctorid= request.getParameter("doctorid");
		   String fullname =request.getParameter("fullname");
		   fromdate=DateTimeUtils.getCommencingDate1(fromdate);
		   todate=DateTimeUtils.getCommencingDate1(todate);
		   String data="";
		   
		   if(!doctorid.equals("0")){
		     UserProfile userProfile=userProfileDAO.getUserprofileDetails(Integer.parseInt(doctorid));
		     allBilllist= pharmacyDAO.getAllBillListByDoctor(fromdate,todate,doctorid); 
		     data=userProfile.getFullname()+" | "+userProfile.getDiscription()+" | "+userProfile.getMobile();
		   }  else {
			   
			   data=fullname+" | - | -";
			   allBilllist =pharmacyDAO.getAllBillListByExDoctor(fromdate,todate,fullname);
			   
		   }
		   StringBuffer buffer=new StringBuffer();
		   buffer.append(data+"~");
		   for(String billno:allBilllist){
			   
			   
			   buffer.append("<table class='table table-bordered' cellspacing='0' width='100%' style='margin-bottom: 0px;'>");
			   buffer.append(" <thead>");
			   buffer.append("<tr class='tableback'>");
			   buffer.append("<th style='width: 38%;'>Name of Drug</th>");
			   buffer.append("<th style='width: 4%;'>Mfg</th>");
			   buffer.append("<th style='width: 6%;'>Batch No</th>");
			   buffer.append("<th style='width: 6%;text-align:right'>Amount</th>");
			   buffer.append("</tr>");
			   buffer.append("</thead>");
			   
			   buffer.append("<tbody>");
			   
			    double sum=0; 
			    ArrayList<Priscription> medicineChargeList= pharmacyDAO.getMedicineChargesList(Integer.parseInt(billno),loginInfo);
			    for(Priscription priscription:medicineChargeList){
			    	
			    	sum=sum+priscription.getTotal();
			    	buffer.append("<tr>"); 
				    buffer.append("<td>"+priscription.getMdicinenametxt()+"</td>");
				    buffer.append("<td>"+priscription.getMfg()+"</td>");
				    buffer.append("<td>"+priscription.getBatch_no()+"</td>");
				    buffer.append("<td class='text-right'>Rs."+priscription.getTotal()+"</td>");
				    buffer.append("</tr>");
			    	
			    }
			   
			    buffer.append("</tbody>");
			    buffer.append("</table>");
			    buffer.append("<div class='' style='border-top: 1px solid #000;border-bottom: 1px solid #000;padding-left: 0px;'>");
			    buffer.append(" <div class='text-right'>");
			    buffer.append("<div class='' style=''>");
			    buffer.append("<h4 style='font-weight: bold;font-size: 13px;color:green;'>Total : Rs."+sum+"</h4>");
			    buffer.append("</div></div></div>");
			    
		   }

		    response.setContentType("text/html");
			response.setHeader("Cache-Control", "no-cache");
			response.getWriter().write(""+buffer.toString()+""); 
		   
	   } catch (Exception e) {

		   e.printStackTrace();
	   } finally {
		   connection.close();
	   }
	   
	   return null;
   }
   
   
   
   public String createpo() throws Exception {
	   if (!verifyLogin(request)) {

			return "login";
		}
	   Connection connection=null;
	   try {
		   connection=Connection_provider.getconnection();
		   ProcurementDAO procurementDAO=new JDBCProcurementDAO(connection);
		   String vendorid=request.getParameter("vendor");
		   String date = DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone());
		   int procurementid = procurementDAO.saveParengtPrecurementData(vendorid,date,0,"0",1,"","0");
		   String location ="";
		   for(Product product:emrForm.getAllproduct()){
			     String pid=product.getProduct_id();			   
			     Product product1 = procurementDAO.getProductDetails(pid);
			     double qty = product.getQty();
			     String brandid = product1.getBrand();
			     double total = Double.parseDouble(product1.getPurchase_price()) * (qty);
			     product.setGrnwithpo_child(1);
			     int result = procurementDAO.saveProcurementData(product1,""+qty,total,vendorid,brandid,procurementid,0,0,product1.getVat());
		   }
		   
		   String type="P";
		   String grnYearSeq= procurementDAO.generateYearPoSequence(location,loginInfo.isLmh(),type,null);
		   int updateResult = procurementDAO.updateYearPoSequence(procurementid,grnYearSeq);
	   } catch (Exception e) {

		   e.printStackTrace();
	   }
	   finally{ 
		   connection.close();
	   }
	   
	   return "polist";
   }
   
   public String medicinebarcode() throws Exception{
	   if (!verifyLogin(request)) {

			return "login";
		}
	   Connection connection=null;
		try {
			
			session.removeAttribute("prodList");
			
			connection=Connection_provider.getconnection();
			InventoryProductDAO inventoryProductDAO=new JDBCInventoryProductDAO(connection);
			
			String location =(String)session.getAttribute("location");
			if(location==null){
				location="0";
			}
			String isfromdashbaord = request.getParameter("isfromdashbaord");
			if(isfromdashbaord!=null){
				if(isfromdashbaord.equals("1")){
					LoginInfo loginInfo = new LoginInfo();
					String isbalgopal1 = request.getParameter("isbalgopal");
					String country = request.getParameter("country");
					boolean isbalgopal = Boolean.parseBoolean(isbalgopal1);
					loginInfo.setBalgopal(isbalgopal);
					loginInfo.setCountry(country);
					session.setAttribute("logininfo", loginInfo);
					LoginHelper.saveLoginInfo(request, loginInfo);	
				}
			}
			if(location.equals("")){
				location="0";
			}

			//truncate
			int res = inventoryProductDAO.deletemdbarcode();
			//ArrayList<Master> warehouseList= inventoryProductDAO.getWareHouseList();
			ArrayList<Master> warehouseList = inventoryProductDAO.getWareHouseList(location);
			emrForm.setWarehouseList(warehouseList);
			
			/*ArrayList<Product> inventoryPriscList= inventoryProductDAO.geProductList("2",location);
			emrForm.setInventoryPriscList(inventoryPriscList);*/
			
		}catch (Exception e) {
			e.printStackTrace();
		}finally{
			
			connection.close();
		}
	   
	   return "medicinebarcode";
   }
   
   public String showpack() throws Exception{
	   if (!verifyLogin(request)) {
			return "login";
		}
	   Connection connection=null;
		try {
			connection=Connection_provider.getconnection();
			InventoryProductDAO inventoryProductDAO=new JDBCInventoryProductDAO(connection);
			
			String prodid = request.getParameter("prodid");
			
			String pack = inventoryProductDAO.getProdPack(prodid);
			
		    response.setContentType("text/html");
			response.setHeader("Cache-Control", "no-cache");
			response.getWriter().write(pack); 
			   
			
		}catch (Exception e) {
			e.printStackTrace();
		}finally{
			
			connection.close();
		}
	   
	   return null;
   }
   
   public String addtempbarcode() throws Exception{
	   if (!verifyLogin(request)) {
			return "login";
		}
	   String prodid = request.getParameter("prodid");
	   String qty = request.getParameter("qty");
	   
	   Connection connection = null;
	   Product product = new Product();
	   try {
			connection=Connection_provider.getconnection();
			InventoryProductDAO inventoryProductDAO=new JDBCInventoryProductDAO(connection);
			Product p = inventoryProductDAO.getProduct(prodid);
			String pro_code = inventoryProductDAO.getCatlogueProductCode(p.getCatalogueid());
			product.setProduct_name(p.getProduct_name());
			product.setBatch_no(p.getBatch_no());
			product.setPro_code(pro_code);
		    ArrayList<Product>prodList = new ArrayList<Product>();
		   
		    if(session.getAttribute("prodList")!=null){
			   prodList = (ArrayList<Product>)session.getAttribute("prodList");
		    }
	  
		    product.setName(p.getProduct_name());
		    product.setProduct_id(prodid);
		    product.setQty(Double.parseDouble(qty));
		    product.setGlobal_prodid(p.getGlobal_prodid());
		   
		    if(p.getGlobal_prodid()>0){
			   prodList.add(product);
		    }
		    session.setAttribute("prodList", prodList);
		    setmedicinebarcodelist();
	   }catch (Exception e) {
			// TODO: handle exception
		}finally{
			
			connection.close();
		}
	   
	   return null;
   }
   
   public String delbarcode() throws Exception{
	   if (!verifyLogin(request)) {

			return "login";
		}
	   String id = request.getParameter("id");
	   ArrayList<Product>prodList = (ArrayList<Product>)session.getAttribute("prodList");
	   prodList.remove(Integer.parseInt(id));
	   
	   setmedicinebarcodelist();
	   
	   return null;
   }
   
   public void setmedicinebarcodelist() throws Exception{
	   
	   ArrayList<Product>prodList = (ArrayList<Product>)session.getAttribute("prodList");
	   int i = 0;
	   double total = 0.0;
	   try{
		   StringBuffer str = new StringBuffer();
		   for(Product product : prodList){
			  
			   
			   str.append("<tr>");
			   str.append("<td><input type='hidden' class='ajclass' value='" + i + "' class='form-control' /></td/>");
			   str.append("<td>"+product.getName()+"</td/>");
			   str.append("<td>"+product.getQty()+"</td/>");
			   
			   str.append("<td><i onclick='deletedata("+i+")' style='cursor: pointer;' class='fa fa-trash-o' ></i></td/>");
			   str.append("</tr>");
			   
			   total = total + (product.getQty());
			   
			   i++;
			}
		   
		   str.append("<tr style='background-color: #f5f5f5;'>");
		   str.append("<td><b>Total<b></td>");
		   str.append("<td><b>"+total+"</b></td>");
		   str.append("<td></td>");
		   str.append("<td></td>");
		   str.append("</tr>");
			
			response.setContentType("text/html");
			response.setHeader("Cache-Control", "no-cache");
			response.getWriter().write(""+str.toString()+""); 
		   
	   }catch (Exception e) {
		// TODO: handle exception
	}
	   
	   
   }
   
   public String barcode() throws Exception{
	   if (!verifyLogin(request)) {

			return "login";
		}
	   Connection connection = null;
	   
	   try{
		   connection=Connection_provider.getconnection();
		   InventoryProductDAO inventoryProductDAO = new JDBCInventoryProductDAO(connection);
		   String type=DateTimeUtils.isNull(request.getParameter("type"));
		   ArrayList<Product>prodList = (ArrayList<Product>)session.getAttribute("prodList");

		   int res = inventoryProductDAO.deletemdbarcode();
		   
		   for(Product product : prodList){
			   double qty =(product.getQty());
			   for(int i=1;i<=qty;i++){
				   /*String imgname = i + "p" + product.getProduct_id() + ".gif";*/
				   String imgname = i + "p" + product.getGlobal_prodid() + ".gif";
				   emrForm.setImagedata(""+product.getGlobal_prodid());
				   int result = inventoryProductDAO.savemdbarcodedata(imgname,product);
				/*   code128.setShowText(false);
				   code128.setData(""+product.getGlobal_prodid());
				  
				   //code128.setData(product.getProduct_id()+","+product.getGlobal_prodid());
				   String filePath = request.getRealPath("/mdbarcode/"+imgname);
				   code128.drawBarcode(filePath);*/
			   }
		   }
		  
		   int count = inventoryProductDAO.getmdBarcodeCount();
		   session.setAttribute("mdbarcodecount", count);
		   if(type.equals("1")){
			   return "singlebarcodeinv";
		   }
		   
		   
		   if(!loginInfo.getClinicUserid().equals("aureus")){
			   return "allmdbarcode";
		   }
		   
	   }catch (Exception e) {
		// TODO: handle exception
	}finally{
		
		connection.close();
	}
	   
	   return "mdbarcode";
   }
   
   
   public String setloc() {
	   if (!verifyLogin(request)) {

			return "login";
		}
	   try {
		  
		    String location =request.getParameter("location");
		    session.setAttribute("location", location); 
		    emrForm.setLocation(location); 
		   
		    response.setContentType("text/html");
			response.setHeader("Cache-Control", "no-cache");
			response.getWriter().write("");
		   
		   
	} catch (Exception e) {

		e.printStackTrace();
	}
	   return null;
   }
   
   
	public void prepare() throws Exception {

		Connection connection=null;
		try {
			
			connection=Connection_provider.getconnection();
			Clinic clinic = new Clinic();
			ClinicDAO clinicDAO  = new JDBCClinicDAO(connection);
			EmrDAO emrDAO=new JDBCEmrDAO(connection); 
			AccountsDAO accountsDAO = new JDBCAccountsDAO(connection);
		//	ClientDAO clientDAO=new JDBCClientDAO(connection);
			
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
			ArrayList<Master>mdicinecategoryList = emrDAO.getmedicineCategoryList();
			emrForm.setMdicinecategoryList(mdicinecategoryList);
			
		/*	ArrayList<Master>dosageList = emrDAO.getDosageList();
			emrForm.setDosageList(dosageList);
			
			ArrayList<Master>mdicneTypeList = emrDAO.getMedicineTypeList();
			emrForm.setMdicneTypeList(mdicneTypeList);
			
			ArrayList<Client> condtitionList = clientDAO.getTreatmentTypeList();
			emrForm.setTreatmentTypeList(condtitionList);
			
			ArrayList<Priscription>parentPriscList = new ArrayList<Priscription>();
			emrForm.setParentPriscList(parentPriscList);
			*/
			//MasterDAO masterDAO=new JDBCMasterDAO(connection);
		/*	ArrayList<Master>medicineList = masterDAO.getMedicineList();
			emrForm.setMedicineList(medicineList);*/
			
			/*InventoryProductDAO inventoryProductDAO=new JDBCInventoryProductDAO(connection);
			ArrayList<Product> inventoryPriscList= inventoryProductDAO.geProductList("2");
			emrForm.setInventoryPriscList(inventoryPriscList);*/
			
			PharmacyDAO pharmacyDAO=new JDBCPharmacyDAO(connection);
			int count= pharmacyDAO.getMedicineRequestedCount(); 
			emrForm.setRequestedCount(count);
			
			ArrayList<Master> locationListPharmacy= pharmacyDAO.getAllLocation();
			emrForm.setLocationListPharmacy(locationListPharmacy);
			 
			String location= (String) session.getAttribute("location");
			emrForm.setLocation(location);
			
			
			emrForm.setMedicineTypeList(null);  
			String loc= (String) session.getAttribute("location");
				if(loc==null){
					loc="0";
				}
			
			InventoryVendorDAO inventoryVendorDAO= new JDBCInventoryVendorDAO(connection);
			 ArrayList<Vendor> vendorList= inventoryVendorDAO.getAllVendorList(loc);
			 emrForm.setVendorList(vendorList);
			 
			 int counter = pharmacyDAO.getRequisitionCount(loc);
			 emrForm.setCounter(counter);
			 
			   String userid = loginInfo.getUserId();
				Priscription priscription = pharmacyDAO.getPharacyUsrLInfo(userid);
				emrForm.setRequisition_auth(priscription.getRequisition_auth());
			
			 
		} catch (Exception e) {

			e.printStackTrace();
		}
		finally{
			connection.close();
		}
		
	}
	
	public String userprofilebillhistory() throws Exception{
		Connection connection = null;
		try {
			connection = Connection_provider.getconnection();
			PharmacyDAO pharmacyDAO=new JDBCPharmacyDAO(connection);
			 String userid = request.getParameter("userid");
			 String fromdate = request.getParameter("fromdate");
			 String todate=request.getParameter("todate");
			 
			 String location=(String)session.getAttribute("location");
			 if(location==null){
				 location="0";
			 }
			 
			 fromdate=DateTimeUtils.getCommencingDate1(fromdate);
			 todate=DateTimeUtils.getCommencingDate1(todate);
			 StringBuffer buffer=new StringBuffer();
			 
			 String data= pharmacyDAO.getusernamefromusertbl(userid);
			 buffer.append(data+"~");
			 double disc=0;
			 ArrayList<Priscription> billnolist = pharmacyDAO.getallbillbyuser(userid,fromdate,todate,location);
			 double totalcredit =0;
			 for(Priscription priscription:billnolist) {
				    //  13 July 2018
				 	//String paymode= pharmacyDAO.getBillPaymode(priscription.getId());
				 	String paymode = priscription.getNewpaymentmode();
				 	
				 	buffer.append("<b style='color: #e69e18;font-weight: normal;'>Prescription Date: "+DateTimeUtils.getCommencingDate1(priscription.getDate())+"       </b>");
				 	buffer.append("<b>Bill no.: "+priscription.getBillno()+" ("+paymode+") </b>");
				 	buffer.append("&nbsp; | &nbsp; <b style='font-weight: normal;'>Patient Name: "+priscription.getFullname()+"</b>");
				 	buffer.append("<div class=''>");
				    buffer.append("<table class='table table-bordered' cellspacing='0' width='100%' style='margin-bottom: 0px;'>");
				    buffer.append("<thead>");
				    buffer.append("<tr class='tableback'>");
				    buffer.append("<th style='width: 38%;'>Name of Drug</th>");
				    buffer.append("<th style='width: 4%;'>Pkg</th>");
				    buffer.append("<th style='width: 4%;'>Mfg</th>");
				    buffer.append("<th style='width: 6%;'>Batch No</th>");
				    buffer.append("<th style='width: 8%;'>Exp. Dt</th>");
				    buffer.append("<th style='width: 4%;'>Qty</th>");
				    buffer.append("<th style='width: 6%;' class='text-right'>Amount</th>");
				   buffer.append("</tr></thead>");
				   CompleteAppointment completeAppointment=pharmacyDAO.getBillDetails(Integer.parseInt(priscription.getBillno()));
				   ArrayList<Priscription> priscriptionlist=pharmacyDAO.getMedicineChargesList(Integer.parseInt(priscription.getBillno()),loginInfo);
				   buffer.append(" <tbody>");
				   
				   double subtotal=0;
				   
				   for(Priscription medicine:priscriptionlist){
					   
					    subtotal=subtotal+medicine.getTotal();
					    buffer.append("<tr>");
					    buffer.append("<td>"+medicine.getMdicinenametxt()+"</td>");
					    buffer.append("<td>"+medicine.getPkg()+"</td>");
					    buffer.append("<td>"+medicine.getMfg()+"</td>");
					    buffer.append("<td>"+medicine.getBatch_no()+"</td>");
					    buffer.append("<td>"+medicine.getExpiry_date()+"</td>");
					    buffer.append("<td>"+medicine.getSaleqty()+"</td>");
					    buffer.append("<td class='text-right'>Rs."+medicine.getMrp()+"</td>");
					    buffer.append("</tr>"); 
				   }
				   
				   buffer.append("</tbody>");
				   buffer.append("</table>");
				   disc= disc+completeAppointment.getDiscount();
				   buffer.append("<div class='' style='border-top: 1px solid #000;border-bottom: 1px solid #000;padding-left: 0px;'>");
				   buffer.append("<div class='text-right'>");
				   buffer.append("<div class='' style=''>");
				   buffer.append("<h4 style='font-size: 13px;'>Sub Total : Rs."+DateTimeUtils.changeFormat(subtotal)+"</h4>");
				   buffer.append("<h4 style='color: #868686;font-size: 13px;'>Discount(%) : Rs."+completeAppointment.getDiscount()+"</h4>");
				   buffer.append("<h4 class='hidden' style='font-size: 13px;'>Vat : Rs."+completeAppointment.getVat()+"</h4>");
				   buffer.append("<h4 style='font-weight: bold;font-size: 13px;color:green;'>Total : Rs."+completeAppointment.getTotal()+"</h4>");
				   buffer.append("</div></div></div></div></div>");
				   
				   if(paymode!=null){
				 		if(paymode.equals("Credit")){
				 			totalcredit = totalcredit + completeAppointment.getTotal();
				 		}
				 	}
				   
			 }
			 buffer.append("~");
			 UserProfileDAO userProfileDAO= new JDBCUserProfileDAO(connection);
			 String mobile= pharmacyDAO.getmobfromuserid(userid);
			 double cardcolected= pharmacyDAO.getTotalCardCollectedByUser(userid, fromdate, todate,location);
		     double cashcollected= pharmacyDAO.getTotalCashCollectedByUser(userid, fromdate, todate,location);
		     double chequecollected= pharmacyDAO.getTotalChequeCollectedByUser(userid, fromdate, todate,location);
		     double neftcollected= pharmacyDAO.getTotalNeftCollectedByUser(userid, fromdate, todate,location);
		     double hospitalcollected= pharmacyDAO.getTotalHospitalCollectedByUser(userid, fromdate, todate,location);
		     double totalReturn= pharmacyDAO.getTotalReturnByUser(userid, fromdate, todate,location);
		     double totalcreditReturn = pharmacyDAO.getTotalcreditReturnByUser(userid,fromdate,todate,location);
			 UserProfile userProfile =userProfileDAO.getPharmacyUserDetails(loginInfo.getClinicid());
			 
			   buffer.append("<h4 class='modal-title visible-print hidden-md hidden-lg hidden-sm'>"+data+"("+userid+")</h4>");
		        
	             buffer.append("<div class='col-lg-12 col-md-12 col-sm-12 col-xs-12' style='padding:0px;'>");
	        	 buffer.append(" <div class='col-lg-12 col-md-12 col-xs-12 col-sm-12 text-center' style='border-bottom: 1px solid #ddd;'>");
	        	 buffer.append("<h4>"+userProfile.getClinicname()+"</h4>");
	        	 buffer.append("<h5>"+userProfile.getAddress()+"</h5>");
	        	 buffer.append("<h5>Websit:"+userProfile.getWebsite()+", Email:"+userProfile.getEmail()+", Contact : "+userProfile.getPhone()+"</h5>");
	        	 buffer.append("</div>");
	        	 double plus= cardcolected+cashcollected+chequecollected+neftcollected+hospitalcollected;
	        	 double minus=totalReturn + disc;
	        	 double netamt=plus -minus;
	        	 buffer.append("<div class='col-lg-12 col-md-12 col-xs-12 col-sm-12 text-center'>");
	        	buffer.append("<h4><b>User Wise Summary</b></h4>");
	        	 buffer.append("</div>");
	        	buffer.append("<div class='col-lg-12 col-md-12 col-xs-12 col-sm-12' style='border-bottom: 1px solid #ddd;padding:0px;margin-bottom: 15px;'>");
	        		buffer.append("<div class='col-lg-8 col-md-8 col-xs-8 col-sm-8'>");
		        	buffer.append("<p class='marboset'><b>User Name :</b><span>"+data+"</span></p>");
		        	buffer.append("<p class='marboset'><b>Contact No :</b><span>"+mobile+"</span></p>");
		        	buffer.append("</div>");
		        	buffer.append("<div class='col-lg-4 col-md-4 col-xs-4 col-sm-4'>");
		        		buffer.append("<p class='marboset'><b>Cash Collected :</b><span class='pull-right'>Rs."+DateTimeUtils.changeFormat(cashcollected)+"</span></p>");
		        		buffer.append("<p class='marboset'><b>Card Collected :</b><span class='pull-right'>Rs."+DateTimeUtils.changeFormat(cardcolected)+"</span></p>");
		        		buffer.append("<p class='marboset'><b>Cheque Collected :</b><span class='pull-right'>Rs."+DateTimeUtils.changeFormat(chequecollected)+"</span></p>");
		        		buffer.append("<p class='marboset'><b>NEFT Collected :</b><span class='pull-right'>Rs."+DateTimeUtils.changeFormat(neftcollected)+"</span></p>");
		        		buffer.append("<p class='marboset'><b>Hospital Collected :</b><span class='pull-right'>Rs."+DateTimeUtils.changeFormat(hospitalcollected)+"</span></p>");
		        		buffer.append("<p class='marboset'><b>Credit :</b><span class='pull-right'>Rs."+DateTimeUtils.changeFormat(totalcredit)+"</span></p>");
		        		buffer.append("<p class='marboset'><b>Cash Return :</b><span class='pull-right'>Rs."+DateTimeUtils.changeFormat(totalReturn)+"</span></p>");
		        		buffer.append("<p class='marboset'><b>Credit Return :</b><span class='pull-right'>Rs."+DateTimeUtils.changeFormat(totalcreditReturn)+"</span></p>");
		        		buffer.append("<p class='marboset bonetamt'><b>Discount :</b><span class='pull-right' style='color: #d9534f;'>- Rs."+DateTimeUtils.changeFormat(disc)+"</span></p>");
		        		buffer.append("<p><b>Net Amount :</b><span class='pull-right'>Rs."+DateTimeUtils.changeFormat(netamt)+"</span></p>");
		        	buffer.append("</div>");
		        buffer.append("</div>");
		        buffer.append("</div>");
		        
			 
	         		 
			    response.setContentType("text/html");
				response.setHeader("Cache-Control", "no-cache");
				response.getWriter().write(""+buffer.toString()+""); 
			 
		
		} catch (Exception e) {
			e.printStackTrace();
		}
		finally{
			connection.close();
		}
		   return null; 
	   }
	
	public String selectExternalPatient() throws Exception{
		 if (!verifyLogin(request)) {

				return "login";
			}
		Connection connection = null;
		try {
			connection = Connection_provider.getconnection();
			String id = request.getParameter("id");
			PharmacyDAO pharmacyDAO = new JDBCPharmacyDAO(connection);
			
			Product product = pharmacyDAO.getpatientinfo(id);
			String data = product.getId()+"~"+product.getFullname()+"~"+product.getAddress()+"~"+product.getReference()+"~"+product.getMobile()+"~"+product.getBalance();
			response.setContentType("text/html");
			response.setHeader("Cache-Control", "no-cache");
			response.getWriter().write(""+data+"");
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			connection.close();
		}
		return null;
	}
	public String clearbalfrmdb() throws Exception{
		 if (!verifyLogin(request)) {

				return "login";
			}
		Connection connection = null;
		try {
			connection = Connection_provider.getconnection();
			String extpid = request.getParameter("extpid");
			PharmacyDAO pharmacyDAO = new JDBCPharmacyDAO(connection);
			int result = pharmacyDAO.clearbalfromdb(extpid);
			
			String data = "<h3 style='color:#d9534f;'>Balance : <span style='float: right;' id='rebalance'>Rs. 00.00</span> </h3>";

			response.setContentType("text/html");
			response.setHeader("Cache-Control", "no-cache");
			response.getWriter().write(""+data+"");
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			connection.close();
		}
		return null;
	}
	public String clearpatientbaldb() throws Exception{
		 if (!verifyLogin(request)) {

				return "login";
			}
		Connection connection = null;
		try {
			connection = Connection_provider.getconnection();
			String opdid = request.getParameter("opdid");
			PharmacyDAO pharmacyDAO = new JDBCPharmacyDAO(connection);
			int result = pharmacyDAO.clearpatientbal(opdid);
			
			String data = "<h3 style='color:#d9534f;'>Balance : <span style='float: right;' id='rebalance'>Rs. 00.00</span> </h3>";

			response.setContentType("text/html");
			response.setHeader("Cache-Control", "no-cache");
			response.getWriter().write(""+data+"");
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			connection.close();
		}
		return null;
	}
	 public String deletepharmacyuser() throws Exception{
		 if (!verifyLogin(request)) {

				return "login";
			}
		   Connection connection = null;
		   try {
			connection = Connection_provider.getconnection();
			String id = request.getParameter("id");
			UserProfileDAO userProfileDAO = new JDBCUserProfileDAO(connection);
			int result = userProfileDAO.deletepharmacyuser(id);
			int res = result;
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			connection.close();
		}
		   
		   return null;
	   }
	 

	 public String getUsrSetting()throws Exception{
		 if (!verifyLogin(request)) {

				return "login";
			}
		 Connection connection = null;
		 try {
			connection = Connection_provider.getconnection();
			String userid = request.getParameter("userid");
			String status = request.getParameter("status");
			PharmacyDAO pharmacyDAO = new JDBCPharmacyDAO(connection);
			Priscription priscription = pharmacyDAO.getusersetting(userid,status);
			
			StringBuilder sb = new StringBuilder();
				
				if(priscription.getSale_bill()=="0" || priscription.getSale_bill().equals("0")){
					sb.append("<input type='checkbox' onclick='setSaleBillAccesss("+priscription.getId()+","+priscription.getSale_bill()+")' id='salebill"+priscription.getId()+"' class='onoffswitch-checkbox'>");
					sb.append("<label class='onoffswitch-label' for='salebill"+priscription.getId()+"'>");
					sb.append("<span class='onoffswitch-inner'></span>");
					sb.append("<span class='onoffswitch-switch'></span>");
					sb.append("</label>");
				}
				else
				{
					sb.append("<input type='checkbox' onclick='setSaleBillAccesss("+priscription.getId()+","+priscription.getSale_bill()+")' id='salebill"+priscription.getId()+"' class='onoffswitch-checkbox' checked='checked'>" );
					sb.append("<label class='onoffswitch-label' for='salebill"+priscription.getId()+"'>");
					sb.append("<span class='onoffswitch-inner'></span>");
					sb.append("<span class='onoffswitch-switch'></span>");
					sb.append("</label>");
				}
				sb.append("~");
				if(priscription.getDisscount()=="0" || priscription.getDisscount().equals("0")){
					sb.append("<input type='checkbox' onclick='setDisscountAccesss("+priscription.getId()+","+priscription.getDisscount()+")' id='discount"+priscription.getId()+"' class='onoffswitch-checkbox'>");
					sb.append("<label class='onoffswitch-label' for='discount"+priscription.getId()+"'>");
					sb.append("<span class='onoffswitch-inner'></span>");
					sb.append("<span class='onoffswitch-switch'></span>");
					sb.append("</label>");
				}
				else
				{
					sb.append("<input type='checkbox' onclick='setDisscountAccesss("+priscription.getId()+","+priscription.getDisscount()+")' id='discount"+priscription.getId()+"'  class='onoffswitch-checkbox'  checked='checked'>");
					sb.append("<label class='onoffswitch-label' for='discount"+priscription.getId()+"'>");
					sb.append("<span class='onoffswitch-inner'></span>");
					sb.append("<span class='onoffswitch-switch'></span>");
					sb.append("</label>");
				}
				sb.append("~");
				if(priscription.getLedger()=="0" || priscription.getLedger().equals("0")){
					sb.append("<input type='checkbox' onclick='setLedgerAccesss("+priscription.getId()+","+priscription.getLedger()+")' id='ledger"+priscription.getId()+"'  class='onoffswitch-checkbox'>");
					sb.append("<label class='onoffswitch-label' for='ledger"+priscription.getId()+"'>");
					sb.append("<span class='onoffswitch-inner'></span>");
					sb.append("<span class='onoffswitch-switch'></span>");
					sb.append("</label>");
				}
				else
				{
					sb.append("<input type='checkbox' onclick='setLedgerAccesss("+priscription.getId()+","+priscription.getLedger()+")' id='ledger"+priscription.getId()+"' class='onoffswitch-checkbox'  checked='checked'>");
					sb.append("<label class='onoffswitch-label' for='ledger"+priscription.getId()+"'>");
					sb.append("<span class='onoffswitch-inner'></span>");
					sb.append("<span class='onoffswitch-switch'></span>");
					sb.append("</label>");
				}
				sb.append("~");
				if(priscription.getAccount()=="0" || priscription.getAccount().equals("0")){
					sb.append("<input type='checkbox' onclick='setAccountAccesss("+priscription.getId()+","+priscription.getAccount()+")' id='account"+priscription.getId()+"' class='onoffswitch-checkbox'>");
					sb.append("<label class='onoffswitch-label' for='account"+priscription.getId()+"'>");
					sb.append("<span class='onoffswitch-inner'></span>");
					sb.append("<span class='onoffswitch-switch'></span>");
					sb.append("</label>");
				}
				else
				{
					sb.append("<input type='checkbox' onclick='setAccountAccesss("+priscription.getId()+","+priscription.getAccount()+")' id='account"+priscription.getId()+"' class='onoffswitch-checkbox'  checked='checked'>");
					sb.append("<label class='onoffswitch-label' for='account"+priscription.getId()+"'>");
					sb.append("<span class='onoffswitch-inner'></span>");
					sb.append("<span class='onoffswitch-switch'></span>");
					sb.append("</label>");
				}
				sb.append("~");
				if(priscription.getPurchase_order()=="0" || priscription.getPurchase_order().equals("0")){
					sb.append("<input type='checkbox' onclick='setPurchase_orderAccesss("+priscription.getId()+","+priscription.getPurchase_order()+")' id='purchaseorder"+priscription.getId()+"' class='onoffswitch-checkbox'>");
					sb.append("<label class='onoffswitch-label' for='purchaseorder"+priscription.getId()+"'>");
					sb.append("<span class='onoffswitch-inner'></span>");
					sb.append("<span class='onoffswitch-switch'></span>");
					sb.append("</label>");
				}
				else
				{
					sb.append("<input type='checkbox' onclick='setPurchase_orderAccesss("+priscription.getId()+","+priscription.getPurchase_order()+")' id='purchaseorder"+priscription.getId()+"' class='onoffswitch-checkbox'  checked='checked'>");
					sb.append("<label class='onoffswitch-label' for='purchaseorder"+priscription.getId()+"'>");
					sb.append("<span class='onoffswitch-inner'></span>");
					sb.append("<span class='onoffswitch-switch'></span>");
					sb.append("</label>");
				}
				
				sb.append("~");
				if(priscription.getSMS()=="0" || priscription.getSMS().equals("0")){
					sb.append("<input type='checkbox' onclick='setSMSAccesss("+priscription.getId()+","+priscription.getSMS()+")' id='SMS"+priscription.getId()+"' class='onoffswitch-checkbox'>");
					sb.append("<label class='onoffswitch-label' for='SMS"+priscription.getId()+"'>");
					sb.append("<span class='onoffswitch-inner'></span>");
					sb.append("<span class='onoffswitch-switch'></span>");
					sb.append("</label>");
				}
				else
				{
					sb.append("<input type='checkbox' onclick='setSMSAccesss("+priscription.getId()+","+priscription.getSMS()+")' id='SMS"+priscription.getId()+"' class='onoffswitch-checkbox'  checked='checked'>");
					sb.append("<label class='onoffswitch-label' for='SMS"+priscription.getId()+"'>");
					sb.append("<span class='onoffswitch-inner'></span>");
					sb.append("<span class='onoffswitch-switch'></span>");
					sb.append("</label>");
				}
				
				sb.append("~");
				sb.append(""+priscription.getId()+"");
				
				sb.append("~");
				if(priscription.getEdit_bill()=="0" || priscription.getEdit_bill().equals("0")){
					sb.append("<input type='checkbox' onclick='setEdit_billAccesss("+priscription.getId()+","+priscription.getEdit_bill()+")' id='edit_bill"+priscription.getId()+"' class='onoffswitch-checkbox'>");
					sb.append("<label class='onoffswitch-label' for='edit_bill"+priscription.getId()+"'>");
					sb.append("<span class='onoffswitch-inner'></span>");
					sb.append("<span class='onoffswitch-switch'></span>");
					sb.append("</label>");
				}
				else
				{
					sb.append("<input type='checkbox' onclick='setEdit_billAccesss("+priscription.getId()+","+priscription.getEdit_bill()+")' id='edit_bill"+priscription.getId()+"' class='onoffswitch-checkbox'  checked='checked'>");
					sb.append("<label class='onoffswitch-label' for='edit_bill"+priscription.getId()+"'>");
					sb.append("<span class='onoffswitch-inner'></span>");
					sb.append("<span class='onoffswitch-switch'></span>");
					sb.append("</label>");
				}
				sb.append("~");
				if(priscription.getDelete_bill()=="0" || priscription.getDelete_bill().equals("0")){
					sb.append("<input type='checkbox' onclick='setDelete_billAccesss("+priscription.getId()+","+priscription.getDelete_bill()+")' id='delete_bill"+priscription.getId()+"' class='onoffswitch-checkbox'>");
					sb.append("<label class='onoffswitch-label' for='delete_bill"+priscription.getId()+"'>");
					sb.append("<span class='onoffswitch-inner'></span>");
					sb.append("<span class='onoffswitch-switch'></span>");
					sb.append("</label>");
				}
				else
				{
					sb.append("<input type='checkbox' onclick='setDelete_billAccesss("+priscription.getId()+","+priscription.getDelete_bill()+")' id='delete_bill"+priscription.getId()+"' class='onoffswitch-checkbox'  checked='checked'>");
					sb.append("<label class='onoffswitch-label' for='delete_bill"+priscription.getId()+"'>");
					sb.append("<span class='onoffswitch-inner'></span>");
					sb.append("<span class='onoffswitch-switch'></span>");
					sb.append("</label>");
				}
				
				sb.append("~");
				if(priscription.getEdit_purchaseorder()=="0" || priscription.getEdit_purchaseorder().equals("0")){
					sb.append("<input type='checkbox' onclick='setEdit_PurchaseOrderAccesss("+priscription.getId()+","+priscription.getEdit_purchaseorder()+")' id='edit_purchaseorder"+priscription.getId()+"' class='onoffswitch-checkbox'>");
					sb.append("<label class='onoffswitch-label' for='edit_purchaseorder"+priscription.getId()+"'>");
					sb.append("<span class='onoffswitch-inner'></span>");
					sb.append("<span class='onoffswitch-switch'></span>");
					sb.append("</label>");
				}
				else
				{
					sb.append("<input type='checkbox' onclick='setEdit_PurchaseOrderAccesss("+priscription.getId()+","+priscription.getEdit_purchaseorder()+")' id='edit_purchaseorder"+priscription.getId()+"' class='onoffswitch-checkbox'  checked='checked'>");
					sb.append("<label class='onoffswitch-label' for='edit_purchaseorder"+priscription.getId()+"'>");
					sb.append("<span class='onoffswitch-inner'></span>");
					sb.append("<span class='onoffswitch-switch'></span>");
					sb.append("</label>");
				}
				
				sb.append("~");
				if(priscription.getDelete_purchaseorder()=="0" || priscription.getDelete_purchaseorder().equals("0")){
					sb.append("<input type='checkbox' onclick='setDelete_PurchaseOrderAccesss("+priscription.getId()+","+priscription.getDelete_purchaseorder()+")' id='delete_purchaseorder"+priscription.getId()+"' class='onoffswitch-checkbox'>");
					sb.append("<label class='onoffswitch-label' for='delete_purchaseorder"+priscription.getId()+"'>");
					sb.append("<span class='onoffswitch-inner'></span>");
					sb.append("<span class='onoffswitch-switch'></span>");
					sb.append("</label>");
				}
				else
				{
					sb.append("<input type='checkbox' onclick='setDelete_PurchaseOrderAccesss("+priscription.getId()+","+priscription.getDelete_purchaseorder()+")' id='delete_purchaseorder"+priscription.getId()+"' class='onoffswitch-checkbox'  checked='checked'>");
					sb.append("<label class='onoffswitch-label' for='delete_purchaseorder"+priscription.getId()+"'>");
					sb.append("<span class='onoffswitch-inner'></span>");
					sb.append("<span class='onoffswitch-switch'></span>");
					sb.append("</label>");
				}
				sb.append("~");
				if(priscription.getEdit_catalogue()==0){
					sb.append("<input type='checkbox' onclick='setEditCatalogue("+priscription.getId()+","+priscription.getEdit_catalogue()+")' id='edit_catalogue"+priscription.getId()+"' class='onoffswitch-checkbox'>");
					sb.append("<label class='onoffswitch-label' for='edit_catalogue"+priscription.getId()+"'>");
					sb.append("<span class='onoffswitch-inner'></span>");
					sb.append("<span class='onoffswitch-switch'></span>");
					sb.append("</label>");
				}
				else
				{
					sb.append("<input type='checkbox' onclick='setEditCatalogue("+priscription.getId()+","+priscription.getEdit_catalogue()+")' id='edit_catalogue"+priscription.getId()+"' class='onoffswitch-checkbox' checked='checked'>");
					sb.append("<label class='onoffswitch-label' for='edit_catalogue"+priscription.getId()+"'>");
					sb.append("<span class='onoffswitch-inner'></span>");
					sb.append("<span class='onoffswitch-switch'></span>");
					sb.append("</label>");
				}
				sb.append("~");
				if(priscription.getDelete_catalogue()==0){
					sb.append("<input type='checkbox' onclick='setDeleteCatalogue("+priscription.getId()+","+priscription.getEdit_catalogue()+")' id='delete_catalogue"+priscription.getId()+"' class='onoffswitch-checkbox'>");
					sb.append("<label class='onoffswitch-label' for='delete_catalogue"+priscription.getId()+"'>");
					sb.append("<span class='onoffswitch-inner'></span>");
					sb.append("<span class='onoffswitch-switch'></span>");
					sb.append("</label>");
				}
				else
				{
					sb.append("<input type='checkbox' onclick='setDeleteCatalogue("+priscription.getId()+","+priscription.getEdit_catalogue()+")' id='delete_catalogue"+priscription.getId()+"' class='onoffswitch-checkbox' checked='checked'>");
					sb.append("<label class='onoffswitch-label' for='delete_catalogue"+priscription.getId()+"'>");
					sb.append("<span class='onoffswitch-inner'></span>");
					sb.append("<span class='onoffswitch-switch'></span>");
					sb.append("</label>");
				}
				sb.append("~");
				if(priscription.getAccess_back_date()==0){
					sb.append("<input type='checkbox' onclick='setBackDateAccess("+priscription.getId()+","+priscription.getAccess_back_date()+")' id='backdate"+priscription.getId()+"' class='onoffswitch-checkbox'>");
					sb.append("<label class='onoffswitch-label' for='backdate"+priscription.getId()+"'>");
					sb.append("<span class='onoffswitch-inner'></span>");
					sb.append("<span class='onoffswitch-switch'></span>");
					sb.append("</label>");
				}
				else
				{
					sb.append("<input type='checkbox' onclick='setBackDateAccess("+priscription.getId()+","+priscription.getAccess_back_date()+")' id='backdate"+priscription.getId()+"' class='onoffswitch-checkbox' checked='checked'>");
					sb.append("<label class='onoffswitch-label' for='backdate"+priscription.getId()+"'>");
					sb.append("<span class='onoffswitch-inner'></span>");
					sb.append("<span class='onoffswitch-switch'></span>");
					sb.append("</label>");
				}
				sb.append("~");
				if(priscription.getTpbill()==0){
					sb.append("<input type='checkbox' onclick='setTpBillAccess("+priscription.getId()+","+priscription.getTpbill()+")' id='tpbill"+priscription.getId()+"' class='onoffswitch-checkbox'>");
					sb.append("<label class='onoffswitch-label' for='tpbill"+priscription.getId()+"'>");
					sb.append("<span class='onoffswitch-inner'></span>");
					sb.append("<span class='onoffswitch-switch'></span>");
					sb.append("</label>");
				}
				else
				{
					sb.append("<input type='checkbox' onclick='setTpBillAccess("+priscription.getId()+","+priscription.getTpbill()+")' id='tpbill"+priscription.getId()+"' class='onoffswitch-checkbox'  checked='checked'>");
					sb.append("<label class='onoffswitch-label' for='tpbill"+priscription.getId()+"'>");
					sb.append("<span class='onoffswitch-inner'></span>");
					sb.append("<span class='onoffswitch-switch'></span>");
					sb.append("</label>");
				}
				sb.append("~");
				if(priscription.getRequisition_auth().equals("0")){
					sb.append("<input type='checkbox' onclick='setRequisition_authAccess("+priscription.getId()+","+priscription.getRequisition_auth()+")' id='requisition_auth"+priscription.getId()+"' class='onoffswitch-checkbox'>");
					sb.append("<label class='onoffswitch-label' for='requisition_auth"+priscription.getId()+"'>");
					sb.append("<span class='onoffswitch-inner'></span>");
					sb.append("<span class='onoffswitch-switch'></span>");
					sb.append("</label>");
				}
				else
				{
					sb.append("<input type='checkbox' onclick='setRequisition_authAccess("+priscription.getId()+","+priscription.getRequisition_auth()+")' id='requisition_auth"+priscription.getId()+"' class='onoffswitch-checkbox'  checked='checked'>");
					sb.append("<label class='onoffswitch-label' for='requisition_auth"+priscription.getId()+"'>");
					sb.append("<span class='onoffswitch-inner'></span>");
					sb.append("<span class='onoffswitch-switch'></span>");
					sb.append("</label>");
				}
				//added by @k@sh
				sb.append("~");
				if(priscription.getCheck_stock_available().equals("0")){
					sb.append("<input type='checkbox' onclick='setCheck_stock_availableAccess("+priscription.getId()+","+priscription.getCheck_stock_available()+")' id='checkstockavail"+priscription.getId()+"' class='onoffswitch-checkbox'>");
					sb.append("<label class='onoffswitch-label' for='checkstockavail"+priscription.getId()+"'>");
					sb.append("<span class='onoffswitch-inner'></span>");
					sb.append("<span class='onoffswitch-switch'></span>");
					sb.append("</label>");
				}
				else
				{
					sb.append("<input type='checkbox' onclick='setCheck_stock_availableAccess("+priscription.getId()+","+priscription.getCheck_stock_available()+")' id='checkstockavail"+priscription.getId()+"' class='onoffswitch-checkbox'  checked='checked'>");
					sb.append("<label class='onoffswitch-label' for='checkstockavail"+priscription.getId()+"'>");
					sb.append("<span class='onoffswitch-inner'></span>");
					sb.append("<span class='onoffswitch-switch'></span>");
					sb.append("</label>");
				}
				
				//added by @k@sh
				sb.append("~");
				if(priscription.getDirect_transfer().equals("0")){
					sb.append("<input type='checkbox' onclick='setDirect_TransferAccess("+priscription.getId()+","+priscription.getDirect_transfer()+")' id='direct_transfer"+priscription.getId()+"' class='onoffswitch-checkbox'>");
					sb.append("<label class='onoffswitch-label' for='direct_transfer"+priscription.getId()+"'>");
					sb.append("<span class='onoffswitch-inner'></span>");
					sb.append("<span class='onoffswitch-switch'></span>");
					sb.append("</label>");
				}
				else
				{
					sb.append("<input type='checkbox' onclick='setDirect_TransferAccess("+priscription.getId()+","+priscription.getDirect_transfer()+")' id='direct_transfer"+priscription.getId()+"' class='onoffswitch-checkbox'  checked='checked'>");
					sb.append("<label class='onoffswitch-label' for='direct_transfer"+priscription.getId()+"'>");
					sb.append("<span class='onoffswitch-inner'></span>");
					sb.append("<span class='onoffswitch-switch'></span>");
					sb.append("</label>");
				}
				
				//added ny Rch1t@
			    
			    sb.append("~");
			    if(priscription.getInventory_transfer().equals("0")){
			     sb.append("<input type='checkbox' onclick='setInventory_ReportAccess("+priscription.getId()+","+priscription.getInventory_transfer()+")' id='inventory_report"+priscription.getId()+"' class='onoffswitch-checkbox'>");
			     sb.append("<label class='onoffswitch-label' for='inventory_report"+priscription.getId()+"'>");
			     sb.append("<span class='onoffswitch-inner'></span>");
			     sb.append("<span class='onoffswitch-switch'></span>");
			     sb.append("</label>");
			    }
			    else
			    {
			     sb.append("<input type='checkbox' onclick='setInventory_ReportAccess("+priscription.getId()+","+priscription.getInventory_transfer()+")' id='inventory_report"+priscription.getId()+"' class='onoffswitch-checkbox'  checked='checked'>");
			     sb.append("<label class='onoffswitch-label' for='inventory_report"+priscription.getId()+"'>");
			     sb.append("<span class='onoffswitch-inner'></span>");
			     sb.append("<span class='onoffswitch-switch'></span>");
			     sb.append("</label>");
			    }
			    
			  //added by @k@sh
				sb.append("~");
				if(priscription.getReturn_stock().equals("0")){
					sb.append("<input type='checkbox' onclick='setReturn_StockAccess("+priscription.getId()+","+priscription.getReturn_stock()+")' id='return_stock"+priscription.getId()+"' class='onoffswitch-checkbox'>");
					sb.append("<label class='onoffswitch-label' for='return_stock"+priscription.getId()+"'>");
					sb.append("<span class='onoffswitch-inner'></span>");
					sb.append("<span class='onoffswitch-switch'></span>");
					sb.append("</label>");
				}
				else
				{
					sb.append("<input type='checkbox' onclick='setReturn_StockAccess("+priscription.getId()+","+priscription.getReturn_stock()+")' id='return_stock"+priscription.getId()+"' class='onoffswitch-checkbox'  checked='checked'>");
					sb.append("<label class='onoffswitch-label' for='return_stock"+priscription.getId()+"'>");
					sb.append("<span class='onoffswitch-inner'></span>");
					sb.append("<span class='onoffswitch-switch'></span>");
					sb.append("</label>");
				}
				
				 //added by @k@sh, permission by abhi sir date 11 sep 3:22
				sb.append("~");
				if(priscription.getCancel_indent().equals("0")){
					sb.append("<input type='checkbox' onclick='setCancel_IndentAccess("+priscription.getId()+","+priscription.getCancel_indent()+")' id='cancel_indent"+priscription.getId()+"' class='onoffswitch-checkbox'>");
					sb.append("<label class='onoffswitch-label' for='cancel_indent"+priscription.getId()+"'>");
					sb.append("<span class='onoffswitch-inner'></span>");
					sb.append("<span class='onoffswitch-switch'></span>");
					sb.append("</label>");
				}
				else
				{
					sb.append("<input type='checkbox' onclick='setCancel_IndentAccess("+priscription.getId()+","+priscription.getCancel_indent()+")' id='cancel_indent"+priscription.getId()+"' class='onoffswitch-checkbox'  checked='checked'>");
					sb.append("<label class='onoffswitch-label' for='cancel_indent"+priscription.getId()+"'>");
					sb.append("<span class='onoffswitch-inner'></span>");
					sb.append("<span class='onoffswitch-switch'></span>");
					sb.append("</label>");
				}
				 //added by @k@sh, permission by abhi sir date 20 NOV 2017
				sb.append("~");
				if(priscription.getReturn_medicine().equals("0")){
					sb.append("<input type='checkbox' onclick='setReturn_MedicineAccess("+priscription.getId()+","+priscription.getReturn_medicine()+")' id='return_medicine"+priscription.getId()+"' class='onoffswitch-checkbox'>");
					sb.append("<label class='onoffswitch-label' for='return_medicine"+priscription.getId()+"'>");
					sb.append("<span class='onoffswitch-inner'></span>");
					sb.append("<span class='onoffswitch-switch'></span>");
					sb.append("</label>");
				}
				else
				{
					sb.append("<input type='checkbox' onclick='setReturn_MedicineAccess("+priscription.getId()+","+priscription.getReturn_medicine()+")' id='return_medicine"+priscription.getId()+"' class='onoffswitch-checkbox'  checked='checked'>");
					sb.append("<label class='onoffswitch-label' for='return_medicine"+priscription.getId()+"'>");
					sb.append("<span class='onoffswitch-inner'></span>");
					sb.append("<span class='onoffswitch-switch'></span>");
					sb.append("</label>");
				}
				
				 //added by @k@sh, permission by praful sir date 16 JAN 2018
				sb.append("~");
				if(priscription.getIndent_approve().equals("0")){
					sb.append("<input type='checkbox' onclick='setIndent_approveAccess("+priscription.getId()+","+priscription.getIndent_approve()+")' id='indent_approve"+priscription.getId()+"' class='onoffswitch-checkbox'>");
					sb.append("<label class='onoffswitch-label' for='indent_approve"+priscription.getId()+"'>");
					sb.append("<span class='onoffswitch-inner'></span>");
					sb.append("<span class='onoffswitch-switch'></span>");
					sb.append("</label>");
				}
				else
				{
					sb.append("<input type='checkbox' onclick='setIndent_approveAccess("+priscription.getId()+","+priscription.getIndent_approve()+")' id='indent_approve"+priscription.getId()+"' class='onoffswitch-checkbox'  checked='checked'>");
					sb.append("<label class='onoffswitch-label' for='indent_approve"+priscription.getId()+"'>");
					sb.append("<span class='onoffswitch-inner'></span>");
					sb.append("<span class='onoffswitch-switch'></span>");
					sb.append("</label>");
				}
				 //added by @k@sh date 26 june 2018
				sb.append("~");
				if(priscription.getApprove_ponew().equals("0")){
					sb.append("<input type='checkbox' onclick='setPO_approveAccess("+priscription.getId()+","+priscription.getApprove_ponew()+")' id='po_approve"+priscription.getId()+"' class='onoffswitch-checkbox'>");
					sb.append("<label class='onoffswitch-label' for='po_approve"+priscription.getId()+"'>");
					sb.append("<span class='onoffswitch-inner'></span>");
					sb.append("<span class='onoffswitch-switch'></span>");
					sb.append("</label>");
				}
				else
				{
					sb.append("<input type='checkbox' onclick='setPO_approveAccess("+priscription.getId()+","+priscription.getApprove_ponew()+")' id='po_approve"+priscription.getId()+"' class='onoffswitch-checkbox'  checked='checked'>");
					sb.append("<label class='onoffswitch-label' for='po_approve"+priscription.getId()+"'>");
					sb.append("<span class='onoffswitch-inner'></span>");
					sb.append("<span class='onoffswitch-switch'></span>");
					sb.append("</label>");
				}
				//lokesh
				sb.append("~");
				if(priscription.getCancel_po().equals("0")){
					sb.append("<input type='checkbox' onclick='setCancelPoAccess("+priscription.getId()+","+priscription.getCancel_po()+")' id='cancel_po"+priscription.getId()+"' class='onoffswitch-checkbox'>");
					sb.append("<label class='onoffswitch-label' for='cancel_po"+priscription.getId()+"'>");
					sb.append("<span class='onoffswitch-inner'></span>");
					sb.append("<span class='onoffswitch-switch'></span>");
					sb.append("</label>");
				}
				else
				{
					sb.append("<input type='checkbox' onclick='setCancelPoAccess("+priscription.getId()+","+priscription.getCancel_po()+")' id='cancel_po"+priscription.getId()+"' class='onoffswitch-checkbox'  checked='checked'>");
					sb.append("<label class='onoffswitch-label' for='cancel_po"+priscription.getId()+"'>");
					sb.append("<span class='onoffswitch-inner'></span>");
					sb.append("<span class='onoffswitch-switch'></span>");
					sb.append("</label>");
				}
				sb.append("~");
				if(priscription.getPurchaseedit().equals("0")){
					sb.append("<input type='checkbox' onclick='setPurchaseeditAccess("+priscription.getId()+","+priscription.getPurchaseedit()+")' id='purcahse_edit"+priscription.getId()+"' class='onoffswitch-checkbox'>");
					sb.append("<label class='onoffswitch-label' for='purcahse_edit"+priscription.getId()+"'>");
					sb.append("<span class='onoffswitch-inner'></span>");
					sb.append("<span class='onoffswitch-switch'></span>");
					sb.append("</label>");
				}
				else
				{
					sb.append("<input type='checkbox' onclick='setPurchaseeditAccess("+priscription.getId()+","+priscription.getPurchaseedit()+")' id='purcahse_edit"+priscription.getId()+"' class='onoffswitch-checkbox'  checked='checked'>");
					sb.append("<label class='onoffswitch-label' for='purcahse_edit"+priscription.getId()+"'>");
					sb.append("<span class='onoffswitch-inner'></span>");
					sb.append("<span class='onoffswitch-switch'></span>");
					sb.append("</label>");
				}
				sb.append("~");
				if(!priscription.isPharm_print_backdate()){
					sb.append("<input type='checkbox' onclick='setpharm_print_backdate("+priscription.getId()+","+priscription.isPharm_print_backdate()+")' id='pharm_print_backdate"+priscription.getId()+"' class='onoffswitch-checkbox'>");
					sb.append("<label class='onoffswitch-label' for='pharm_print_backdate"+priscription.getId()+"'>");
					sb.append("<span class='onoffswitch-inner'></span>");
					sb.append("<span class='onoffswitch-switch'></span>");
					sb.append("</label>");
				}
				else
				{
					sb.append("<input type='checkbox' onclick='setpharm_print_backdate("+priscription.getId()+","+priscription.isPharm_print_backdate()+")' id='pharm_print_backdate"+priscription.getId()+"' class='onoffswitch-checkbox'  checked='checked'>");
					sb.append("<label class='onoffswitch-label' for='pharm_print_backdate"+priscription.getId()+"'>");
					sb.append("<span class='onoffswitch-inner'></span>");
					sb.append("<span class='onoffswitch-switch'></span>");
					sb.append("</label>");
				}
				
				sb.append("~");
				if(!priscription.isSession_added_med()){
					sb.append("<input type='checkbox' onclick='setSession_added_med("+priscription.getId()+",this.checked)' id='session_added_med"+priscription.getId()+"' class='onoffswitch-checkbox'>");
					sb.append("<label class='onoffswitch-label' for='session_added_med"+priscription.getId()+"'>");
					sb.append("<span class='onoffswitch-inner'></span>");
					sb.append("<span class='onoffswitch-switch'></span>");
					sb.append("</label>");
				}
				else
				{
					sb.append("<input type='checkbox' onclick='setSession_added_med("+priscription.getId()+",this.checked)' id='session_added_med"+priscription.getId()+"' class='onoffswitch-checkbox'  checked='checked'>");
					sb.append("<label class='onoffswitch-label' for='session_added_med"+priscription.getId()+"'>");
					sb.append("<span class='onoffswitch-inner'></span>");
					sb.append("<span class='onoffswitch-switch'></span>");
					sb.append("</label>");
				}
				
				sb.append("~");
				if(!priscription.isPatient_edit()){
					sb.append("<input type='checkbox' onclick=updatepharmacyaccess("+priscription.getId()+",this.checked,'patient_edit') id='patientedit"+priscription.getId()+"' class='onoffswitch-checkbox'>");
					sb.append("<label class='onoffswitch-label' for='patientedit"+priscription.getId()+"'>");
					sb.append("<span class='onoffswitch-inner'></span>");
					sb.append("<span class='onoffswitch-switch'></span>");
					sb.append("</label>");
				}
				else
				{
					sb.append("<input type='checkbox' onclick=updatepharmacyaccess("+priscription.getId()+",this.checked,'patient_edit') id='patientedit"+priscription.getId()+"' class='onoffswitch-checkbox'  checked='checked'>");
					sb.append("<label class='onoffswitch-label' for='patientedit"+priscription.getId()+"'>");
					sb.append("<span class='onoffswitch-inner'></span>");
					sb.append("<span class='onoffswitch-switch'></span>");
					sb.append("</label>");
				}
				
			response.setContentType("text/html");
			response.setHeader("Cache-Control", "no-cache");
			response.getWriter().write(""+sb.toString()+"");
		
		 } catch (Exception e) {
			e.printStackTrace();
		}finally{
			connection.close();
		}
		 return null;
	 }
	 
	 public String setUserSaleSettting() throws Exception{
		 if (!verifyLogin(request)) {

				return "login";
			}
		 Connection connection = null;
		 try {
			connection = Connection_provider.getconnection();
			PharmacyDAO pharmacyDAO = new JDBCPharmacyDAO(connection);
			String userid = request.getParameter("userid");
			String status = request.getParameter("status");
			
			Calendar cal = Calendar.getInstance();
	        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss.SS");
	        String dateString = sdf.format(cal.getTime());
	        
			String Accessdatetime=dateString;
			String CurrentUser=loginInfo.getUserId();
			
			if(status.equals("0")){
				status = "1";
			}
			else {
				status = "0";
			}
			Priscription priscription = pharmacyDAO.setUserSaleSettting(userid,status,Accessdatetime,CurrentUser);
			StringBuilder sb = new StringBuilder();
			if(priscription.getSale_bill()=="0" || priscription.getSale_bill().equals("0")){
				sb.append("<input type='checkbox' onclick='setSaleBillAccesss("+priscription.getId()+","+priscription.getSale_bill()+")' id='salebill"+priscription.getId()+"' class='onoffswitch-checkbox'>");
				sb.append("<label class='onoffswitch-label' for='salebill"+priscription.getId()+"'>");
				sb.append("<span class='onoffswitch-inner'></span>");
				sb.append("<span class='onoffswitch-switch'></span>");
				sb.append("</label>");
			}
			else
			{
				sb.append("<input type='checkbox' onclick='setSaleBillAccesss("+priscription.getId()+","+priscription.getSale_bill()+")' id='salebill"+priscription.getId()+"' class='onoffswitch-checkbox' checked='checked'>");
				sb.append("<label class='onoffswitch-label' for='salebill"+priscription.getId()+"'>");
				sb.append("<span class='onoffswitch-inner'></span>");
				sb.append("<span class='onoffswitch-switch'></span>");
				sb.append("</label>");
			}
			
			response.setContentType("text/html");
			response.setHeader("Cache-Control", "no-cache");
			response.getWriter().write(""+sb.toString()+"");
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			connection.close();
		}
		 return null;
	 }
	 
	 public String setUserDiscountSettting() throws Exception{
		 if (!verifyLogin(request)) {

				return "login";
			}
		 Connection connection = null;
		 try {
			connection = Connection_provider.getconnection();
			PharmacyDAO pharmacyDAO = new JDBCPharmacyDAO(connection);
			String userid = request.getParameter("userid");
			String status = request.getParameter("status");
			
			Calendar cal = Calendar.getInstance();
	        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss.SS");
	        String dateString = sdf.format(cal.getTime());
	        
			String Accessdatetime=dateString;
			String CurrentUser=loginInfo.getUserId();
			
			if(status.equals("0") || status=="0"){
				status = "1";
			}
			else {
				status = "0";
			}
			Priscription priscription = pharmacyDAO.setUserDiscountSettting(userid,status,Accessdatetime,CurrentUser);
			StringBuilder sb = new StringBuilder();
			if(priscription.getDisscount()=="0" || priscription.getDisscount().equals("0")){
				sb.append("<input type='checkbox' onclick='setDisscountAccesss("+priscription.getId()+","+priscription.getDisscount()+")' id='discount"+priscription.getId()+"' class='onoffswitch-checkbox'>");
				sb.append("<label class='onoffswitch-label' for='discount"+priscription.getId()+"'>");
				sb.append("<span class='onoffswitch-inner'></span>");
				sb.append("<span class='onoffswitch-switch'></span>");
				sb.append("</label>");
			}
			else
			{
				sb.append("<input type='checkbox' onclick='setDisscountAccesss("+priscription.getId()+","+priscription.getDisscount()+")' id='discount"+priscription.getId()+"' class='onoffswitch-checkbox'  checked='checked'>");
				sb.append("<label class='onoffswitch-label' for='discount"+priscription.getId()+"'>");
				sb.append("<span class='onoffswitch-inner'></span>");
				sb.append("<span class='onoffswitch-switch'></span>");
				sb.append("</label>");
			}
			
			response.setContentType("text/html");
			response.setHeader("Cache-Control", "no-cache");
			response.getWriter().write(""+sb.toString()+"");
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			connection.close();
		}
		 return null;
	 }
	 
	 public String setUserLedgerSettting() throws Exception{
		 if (!verifyLogin(request)) {

				return "login";
			}
		 Connection connection = null;
		 try {
			connection = Connection_provider.getconnection();
			PharmacyDAO pharmacyDAO = new JDBCPharmacyDAO(connection);
			String userid = request.getParameter("userid");
			String status = request.getParameter("status");
			
			Calendar cal = Calendar.getInstance();
	        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss.SS");
	        String dateString = sdf.format(cal.getTime());
	        
			String Accessdatetime=dateString;
			String CurrentUser=loginInfo.getUserId();
			
			if(status.equals("0") || status=="0"){
				status = "1";
			}
			else {
				status = "0";
			}
			Priscription priscription = pharmacyDAO.setUserLedgerSettting(userid,status,Accessdatetime,CurrentUser);
			StringBuilder sb = new StringBuilder();
			if(priscription.getLedger()=="0" || priscription.getLedger().equals("0")){
				sb.append("<input type='checkbox' onclick='setLedgerAccesss("+priscription.getId()+","+priscription.getLedger()+")' id='ledger"+priscription.getId()+"' class='onoffswitch-checkbox'>");
				sb.append("<label class='onoffswitch-label' for='ledger"+priscription.getId()+"'>");
				sb.append("<span class='onoffswitch-inner'></span>");
				sb.append("<span class='onoffswitch-switch'></span>");
				sb.append("</label>");
			}
			else
			{
				sb.append("<input type='checkbox' onclick='setLedgerAccesss("+priscription.getId()+","+priscription.getLedger()+")' id='ledger"+priscription.getId()+"' class='onoffswitch-checkbox'  checked='checked'>");
				sb.append("<label class='onoffswitch-label' for='ledger"+priscription.getId()+"'>");
				sb.append("<span class='onoffswitch-inner'></span>");
				sb.append("<span class='onoffswitch-switch'></span>");
				sb.append("</label>");
			}
			
			response.setContentType("text/html");
			response.setHeader("Cache-Control", "no-cache");
			response.getWriter().write(""+sb.toString()+"");
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			connection.close();
		}
		 return null;
	 }
	 
	 public String setUserAccountSettting() throws Exception{
		 if (!verifyLogin(request)) {

				return "login";
			}
		 Connection connection = null;
		 try {
			connection = Connection_provider.getconnection();
			PharmacyDAO pharmacyDAO = new JDBCPharmacyDAO(connection);
			String userid = request.getParameter("userid");
			String status = request.getParameter("status");
			
			Calendar cal = Calendar.getInstance();
	        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss.SS");
	        String dateString = sdf.format(cal.getTime());
	        
			String Accessdatetime=dateString;
			String CurrentUser=loginInfo.getUserId();
			
			if(status.equals("0") || status=="0"){
				status = "1";
			}
			else {
				status = "0";
			}
			Priscription priscription = pharmacyDAO.setUserAccountSettting(userid,status,Accessdatetime,CurrentUser);
			StringBuilder sb = new StringBuilder();
			if(priscription.getAccount()=="0" || priscription.getAccount().equals("0")){
				sb.append("<input type='checkbox' onclick='setAccountAccesss("+priscription.getId()+","+priscription.getAccount()+")' id='account"+priscription.getId()+"' class='onoffswitch-checkbox'>");
				sb.append("<label class='onoffswitch-label' for='account"+priscription.getId()+"'>");
				sb.append("<span class='onoffswitch-inner'></span>");
				sb.append("<span class='onoffswitch-switch'></span>");
				sb.append("</label>");
			}
			else
			{
				sb.append("<input type='checkbox' onclick='setAccountAccesss("+priscription.getId()+","+priscription.getAccount()+")' id='account"+priscription.getId()+"' class='onoffswitch-checkbox'  checked='checked'>");
				sb.append("<label class='onoffswitch-label' for='account"+priscription.getId()+"'>");
				sb.append("<span class='onoffswitch-inner'></span>");
				sb.append("<span class='onoffswitch-switch'></span>");
				sb.append("</label>");
			}
			
			response.setContentType("text/html");
			response.setHeader("Cache-Control", "no-cache");
			response.getWriter().write(""+sb.toString()+"");
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			connection.close();
		}
		 return null;
	 }
	 
	 public String setUserPurchase_orderSettting() throws Exception{
		 if (!verifyLogin(request)) {

				return "login";
			}
		 Connection connection = null;
		 try {
			connection = Connection_provider.getconnection();
			PharmacyDAO pharmacyDAO = new JDBCPharmacyDAO(connection);
			String userid = request.getParameter("userid");
			String status = request.getParameter("status");
			
			Calendar cal = Calendar.getInstance();
	        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss.SS");
	        String dateString = sdf.format(cal.getTime());
	        
			String Accessdatetime=dateString;
			String CurrentUser=loginInfo.getUserId();
			
			if(status.equals("0") || status=="0"){
				status = "1";
			}
			else {
				status = "0";
			}
			Priscription priscription = pharmacyDAO.setUserPurchase_orderSettting(userid,status,Accessdatetime,CurrentUser);
			StringBuilder sb = new StringBuilder();
			if(priscription.getPurchase_order()=="0" || priscription.getPurchase_order().equals("0")){
				sb.append("<input type='checkbox' onclick='setPurchase_orderAccesss("+priscription.getId()+","+priscription.getPurchase_order()+")' id='purchaseorder"+priscription.getId()+"' class='onoffswitch-checkbox'>");
				sb.append("<label class='onoffswitch-label' for='purchaseorder"+priscription.getId()+"'>");
				sb.append("<span class='onoffswitch-inner'></span>");
				sb.append("<span class='onoffswitch-switch'></span>");
				sb.append("</label>");
			}
			else
			{
				sb.append("<input type='checkbox' onclick='setPurchase_orderAccesss("+priscription.getId()+","+priscription.getPurchase_order()+")' id='purchaseorder"+priscription.getId()+"' class='onoffswitch-checkbox'  checked='checked'>");
				sb.append("<label class='onoffswitch-label' for='purchaseorder"+priscription.getId()+"'>");
				sb.append("<span class='onoffswitch-inner'></span>");
				sb.append("<span class='onoffswitch-switch'></span>");
				sb.append("</label>");
			}
			
			response.setContentType("text/html");
			response.setHeader("Cache-Control", "no-cache");
			response.getWriter().write(""+sb.toString()+"");
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			connection.close();
		}
		 return null;
	 }
	 public String setUserSMSSend() throws Exception{
		 if (!verifyLogin(request)) {

				return "login";
			}
		 Connection connection = null;
		 try {
			connection = Connection_provider.getconnection();
			PharmacyDAO pharmacyDAO = new JDBCPharmacyDAO(connection);
			String userid = request.getParameter("userid");
			String status = request.getParameter("status");
			
			Calendar cal = Calendar.getInstance();
	        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss.SS");
	        String dateString = sdf.format(cal.getTime());
	        
			String Accessdatetime=dateString;
			String CurrentUser=loginInfo.getUserId();
			
			if(status.equals("0") || status=="0"){
				status = "1";
			}
			else {
				status = "0";
			}
			Priscription priscription = pharmacyDAO.setUserSMSSendAuthority(userid,status,Accessdatetime,CurrentUser);
			StringBuilder sb = new StringBuilder();
			if(priscription.getSMS()=="0" || priscription.getSMS().equals("0")){
				sb.append("<input type='checkbox' onclick='setSMSAccesss("+priscription.getId()+","+priscription.getSMS()+")' id='SMS"+priscription.getId()+"' class='onoffswitch-checkbox'>");
				sb.append("<label class='onoffswitch-label' for='SMS"+priscription.getId()+"'>");
				sb.append("<span class='onoffswitch-inner'></span>");
				sb.append("<span class='onoffswitch-switch'></span>");
				sb.append("</label>");
			}
			else
			{
				sb.append("<input type='checkbox' onclick='setSMSAccesss("+priscription.getId()+","+priscription.getSMS()+")' id='SMS"+priscription.getId()+"' class='onoffswitch-checkbox'  checked='checked'>");
				sb.append("<label class='onoffswitch-label' for='SMS"+priscription.getId()+"'>");
				sb.append("<span class='onoffswitch-inner'></span>");
				sb.append("<span class='onoffswitch-switch'></span>");
				sb.append("</label>");
			}
			
			response.setContentType("text/html");
			response.setHeader("Cache-Control", "no-cache");
			response.getWriter().write(""+sb.toString()+"");
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			connection.close();
		}
		 return null;
	 }
	 
	 
	 
	 
	 public String returnipdmedicine() throws Exception{
		 
		 if (!verifyLogin(request)) {

				return "login";
			}
		 Connection connection=null;  
		 try {
			 
		 	/*ArrayList<Breadcrumbs> indentflowlist = new ArrayList<Breadcrumbs>();
			ArrayList<Breadcrumbs> indentflowlisttemp = new ArrayList<Breadcrumbs>();
			if (session.getAttribute("indentflowlist") != null) {
				indentflowlisttemp = (ArrayList<Breadcrumbs>) session.getAttribute("indentflowlist");
			}
			boolean isavilablemodule= false;
			int modulecount =0;
			for (Breadcrumbs breadcrumbs : indentflowlisttemp) {
				breadcrumbs.setIscurrent(false);
				breadcrumbs.setSqno(modulecount);
				modulecount++;
				if(breadcrumbs.getName().equals("Sale Return")){
					isavilablemodule =true;
					breadcrumbs.setIscurrent(true);
					indentflowlist.add(breadcrumbs);
					break;
				}else{
					indentflowlist.add(breadcrumbs);
				}
			}
			if(!isavilablemodule){
				Breadcrumbs breadcrumbs = new Breadcrumbs();
				breadcrumbs.setName("Sale Return");
				breadcrumbs.setOn(true);
				breadcrumbs.setSqno(modulecount);
				breadcrumbs.setUrllink("returnipdmedicinePharmacy");
				breadcrumbs.setIscurrent(true);
				breadcrumbs.setShowingname("Sale Return");
				indentflowlist.add(breadcrumbs);
			}
			session.setAttribute("indentflowlist",indentflowlist);*/
			
		 
			 
	 		 connection=Connection_provider.getconnection();
			 PharmacyDAO pharmacyDAO=new JDBCPharmacyDAO(connection);
			 UserProfileDAO userProfileDAO = new JDBCUserProfileDAO(connection);
			 ArrayList<Priscription> priscriptionlist = new ArrayList<Priscription>();
			 String location= (String)session.getAttribute("location");
			 if(location==null){
				 location="0";
			 }
			/* ArrayList<Product> inventoryPriscList= inventoryProductDAO.geProductList("2",location);
				emrForm.setInventoryPriscList(inventoryPriscList);*/
			 
			 //ArrayList<Bed> bedlist = ipdDAO.getAllBedList(null,loginInfo.getClinicid());
			//  16 Nov 2018 
			//delete previous data of user using login session
			int res = pharmacyDAO.deleteTempPharmacySession(loginInfo.getLoginsessionid());
			 
			 //  16 aug 2018
			 String multireturndash = emrForm.getMultireturndash();
			 String clientid = emrForm.getHdnphclientid();
			 String ispharmacy = emrForm.getHdnispharmacy();
			 String hdnipdid = emrForm.getHdnipdid();
			 String paymodereturn = emrForm.getPaymodereturn();
			 //called from returnnursedashboard.jsp
			 String isfromreturnnurse = request.getParameter("isfromreturnnurse");
			 String id = request.getParameter("id");
			 String chargeid = request.getParameter("chargeid");
			 if(multireturndash!=null){
				 if(multireturndash.equals("1")){
					 //if its called from returnmultibill.jsp then only go through this
					 int ress = pharmacyDAO.deleteTempReturnPharmacyData(clientid, ispharmacy, loginInfo.getLoginsessionid());
					 String multireturnbillid = emrForm.getMultireturnbillid();
					 ArrayList<Product> allMedicieneList = pharmacyDAO.getAllBillWise(clientid,ispharmacy,multireturnbillid,0);
					 emrForm.setInventoryPriscList(allMedicieneList);
				 }else{
					 multireturndash="0";
				 }
			 }else{
				 multireturndash="0";
			 }
			 String returnrequestid ="0";
			 if(isfromreturnnurse!=null){
				 if(isfromreturnnurse.equals("1")){
					 ispharmacy="0";
					 Priscription priscription = pharmacyDAO.getReturnMedicineRequest(id);
					 clientid = priscription.getClientId();
					 int ress = pharmacyDAO.deleteTempReturnPharmacyData(clientid, ispharmacy, loginInfo.getLoginsessionid());
					 ArrayList<Product> allMedicieneList = new ArrayList<Product>();
					 //ArrayList<Product> allMedicieneList = pharmacyDAO.getAllBillWise(clientid,ispharmacy,chargeid,1);
					 //emrForm.setInventoryPriscList(allMedicieneList);
					 emrForm.setInventoryPriscList(allMedicieneList);
					 
					 hdnipdid = priscription.getIpdid();
					 paymodereturn = priscription.getPaymode();
					 emrForm.setReturnqty(priscription.getReturn_qty());
					 returnrequestid = ""+priscription.getId();
					 String datetime = DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone());
					 int ressss = pharmacyDAO.getCheckDiscountNotRequested(id);
					 if(ressss!=1){
						 priscriptionlist = pharmacyDAO.getReturnAllMedicineByNurceList(id,clientid,ispharmacy,loginInfo.getLoginsessionid(),datetime); 
					 }
					 emrForm.setPriscriptionlist(priscriptionlist);
				 }else{
					 isfromreturnnurse="0";
					 chargeid="0";
				 }
			 }else{
				 isfromreturnnurse="0";
				 chargeid="0";
			 }
			 
			 emrForm.setReturnrequestid(id);
			 emrForm.setReturnchargeid(chargeid);
			 
			 
			 //  16 aug 2018 not used in jsp thats why commented
			 /*ArrayList<Priscription> ipdpatlist = pharmacyDAO.getipdpatientlist();
			 emrForm.setIpdpatientlist(ipdpatlist);*/
			 emrForm.setIpdmedicineList(null);
			 emrForm.setMultireturndash(multireturndash);
			 emrForm.setPaymodereturn(paymodereturn);
			 session.setAttribute("retmultireturndash", multireturndash);
			 session.setAttribute("retmulticlientid", clientid);
			 session.setAttribute("retmultiispharmacy", ispharmacy);
			 session.setAttribute("retmultihdnipdid", hdnipdid);
			 session.setAttribute("isfromreturnnurse", isfromreturnnurse);
			 
			 //return sale barcode code start
			 UserProfile profile2 = userProfileDAO.getPharmacyUserDetails(loginInfo.getClinicid());
			 emrForm.setNonsystembarcode(profile2.isNonsystembarcode());
			 
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			connection.close();
		}
		 return "returnipdmedicine";
	 }
	 
	 
	 public String addreturnmedicine() throws Exception{
		 if (!verifyLogin(request)) {

				return "login";
			}
			
			Connection connection=null;
			try {
				connection=Connection_provider.getconnection();
				PharmacyDAO pharmacyDAO = new JDBCPharmacyDAO(connection);
				String pid=request.getParameter("pid");
				String tcount=request.getParameter("count");
				//String qty=request.getParameter("qty");
				//Product product=inventoryProductDAO.getProduct(pid);
				Priscription priscription = pharmacyDAO.getMedicineChargesbyid(Integer.parseInt(pid),0);
					
				//String ex=DateTimeUtils.getCommencingDate1(priscription.getExpiry_date());
				//String expiry=DateTimeUtils.converToMMMYYYYforExp(ex);
				int count=Integer.parseInt(tcount);
				count--;
				
				StringBuffer buffer=new StringBuffer();
				buffer.append("<input type='hidden' name='medicines["+count+"].id' value='"+priscription.getId()+"' />");
				buffer.append("<td style='border-bottom: none !important;'>"+priscription.getMdicinenametxt()+" <input type='hidden' name='medicines["+count+"].product_id' value='"+priscription.getProduct_id()+"' /> </td>");
				buffer.append("<td style='border-bottom: none !important;'>"+priscription.getReqqty()+"</td> <input type='hidden' id='req"+count+"' name='medicines["+count+"].reqqty' value='"+priscription.getReqqty()+"'/>");
				//buffer.append("<td style='border-bottom: none !important;'>");
				buffer.append("<td style='border-bottom: none !important;'>");
				buffer.append(""+priscription.getAvailable()+"");
				buffer.append("/");
				buffer.append(""+priscription.getBatch_no()+"");
				buffer.append("/");
				buffer.append(""+priscription.getExpiry_date()+"");
				buffer.append("<td>"+priscription.getSaleqty()+"</td>");
				buffer.append("<td style='border-bottom: none !important;'> <input type='text' name='medicines["+count+"].returnqty' class='form-control' id='returnqty"+count+"' onchange='calRefundTotaltemp()' style='height: 20px !important;'/> <input type='hidden' value='"+priscription.getSaleqty()+"'  name='medicines["+count+"].saleqty' id='sale"+count+"' class='form-control'><input type='hidden' id='tempsale"+count+"' value='"+priscription.getSaleqty()+"' /></td>");
				buffer.append("<td style='border-bottom: none !important;' class='text-right'>Rs."+priscription.getMrp()+"</td><input type='hidden' value='"+priscription.getMrp()+"' id='mrp"+count+"' name='medicines["+count+"].mrp'/> ");
				buffer.append("<td style='border-bottom: none !important;' class='text-right'>Rs.<label id='totalmrp"+count+"'>"+priscription.getTotal()+"</label></td>");
				buffer.append("<input type='hidden' id='tmedicineid'+"+count+"+'' name='medicines["+count+"].mdicinenameid' value='"+priscription.getMdicinenameid()+"' />    ");
				buffer.append("<td style='border-bottom: none !important;' class='text-center'><a href='#' onclick='deleteRowtemp(this)'><i class='fa fa-minus-circle' style='color:#c50404;font-size: 17px;padding-top: 2px;' aria-hidden='true'></i></a></td>");
				
				/*buffer.append("<td id='labelreq"+count+"' style='border-bottom: none !important;'>"+qty+"</td> <input type='hidden' name='medicines["+count+"].reqqty' value='"+qty+"' id='req"+count+"' />");
				buffer.append("<td>"+product.getStock()+"/"+product.getBatch_no()+"/"+expiry+" </td>");
				buffer.append("<td style='border-bottom: none !important;'><input  type='number' name='medicines["+count+"].saleqty' onchange='calsubTotal()' id='sale"+count+"' value='"+qty+"' class='form-control' style='height: 20px !important;'></td>");
				buffer.append("<td style='border-bottom: none !important;' class='text-right'>Rs."+product.getSale_price()+"</td><input style='height:20px !important;' type='hidden' value='"+product.getSale_price()+"' id='mrp"+count+"'/>");
				buffer.append("<td style='text-align: center;text-transform: uppercase;'>"+product.getShelf()+" <input type='hidden' name='medicines["+count+"].vat' id='vat"+count+"' value='"+product.getVat()+"'> </td>");
				buffer.append("<td style='border-bottom: none !important;' class='text-right'>Rs.<label id='totalmrp"+count+"'>00.00</label></td>");
				buffer.append("<input type hidden id='prodd"+count+"' value='"+product.getStock()+"'/>");
				
				int stock=Integer.parseInt(product.getStock());
				if(stock>0){
					buffer.append("<td class='text-center' id='tdbutton"+count+"'><a href='#' onclick='deleteRow(this)'><i class='fa fa-minus-circle' style='color:#c50404;font-size: 17px;padding-top: 2px;' aria-hidden='true'></i></a></td>");
				} else {
					buffer.append("<td class='text-center'><a href='#' onclick='deleteRow(this)'><i class='fa fa-minus-circle' style='color:#c50404;font-size: 17px;padding-top: 2px;' aria-hidden='true'></i></a></td><td class='text-center'><a href='#' onclick='requestStock("+pid+","+count+")' title='Request Stock'><i class='fa fa-cart-plus' aria-hidden='true' style='font-size: 17px;padding-top: 2px;'></i></a></td>");
				}
				
				buffer.append("<input type='hidden' id='tmedicineid"+count+"' name='medicines["+count+"].mdicinenameid' value='"+product.getMedicinenameid()+"' />");
*/				
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
	 
	 public String getIpdPatientbill() throws Exception{
		 if (!verifyLogin(request)) {

				return "login";
			}
		 Connection connection = null;
		 String ipdid = request.getParameter("ipdid");
		 try {
			 	connection = Connection_provider.getconnection();
	 			PharmacyDAO pharmacyDAO = new JDBCPharmacyDAO(connection);
	 			BedDao bedDao = new JDBCBedDao(connection);
				 IpdDAO  ipdDAO = new JDBCIpdDAO(connection);
				 ClientDAO clientDAO=new JDBCClientDAO(connection);
				 UserProfileDAO profileDAO=new JDBCUserProfileDAO(connection);
				
	 			ArrayList<Priscription> priscriptionlist = new ArrayList<Priscription>();
	 			ArrayList<Priscription> billnolist = pharmacyDAO.getbillno(ipdid);
	 			for (Priscription priscription : billnolist) {
	 				 ArrayList<Priscription> priscriptionlist1 = pharmacyDAO.getMedicineChargesList(Integer.parseInt(priscription.getBillno()),loginInfo);
	 				for (Priscription priscription3 : priscriptionlist1) {
	 			       String mdiname = priscription3.getMdicinenametxt();
	 			        if(priscription3.getSaleqty()>0){
	 			        if(mdiname==null)
	 			        {
	 			         
	 			        }
	 			        else 
	 			         priscriptionlist.add(priscription3);
	 			       }
	 			     }
	 				 
	 		
	 			}
	 			
	 				String clientid = pharmacyDAO.getclientid(ipdid);
	 				String selectedid = pharmacyDAO.getpricriptionid(ipdid);
	 				Bed bed = bedDao.getEditIpdData(ipdid);
					String practitionerName=profileDAO.getUserprofileDetails(Integer.parseInt(bed.getPractitionerid())).getFullname();
					String wardname=ipdDAO.getIpdWardName(bed.getWardid());
					String bedname = ipdDAO.getIpdBedName(bed.getBedid());
					String wardbed=wardname+"/"+bedname;
					Client client=clientDAO.getClientDetails(clientid);
					String name = client.getTitle()+" "+client.getFirstName()+" "+client.getLastName();
					String date  = DateTimeUtils.getDashboardTodayDate(loginInfo.getCountry());
					//String dobyear[] = client.getDob().split("/");
					//String curryear[] = date.split("/");
					String balance= pharmacyDAO.getbalofpatient(clientid);
					if(balance==null){
						 balance="0";
					}
					
					StringBuilder builder = new StringBuilder();
	 				builder.append(""+name+"~"+wardbed+"~"+practitionerName+"~"+date+"~");
	 			
					//String data = name+"~"+wardname+"~"+practitionerName+"~"+date;//+"~"+priscriptionlist;//+"~"+ipdid;
				  	builder.append("<select name='returnmedicine' id='returnmedicine' class='form-control chosen-select'>");
				  	builder.append("<option value='0'>Select Product</option>");
					for (Priscription priscription2 : priscriptionlist) {
						builder.append("<option value='"+priscription2.getId()+"'>"+priscription2.getMdicinenametxt()+"</option>");
							
					}
				  	builder.append("</select>~"+ipdid+"~"+selectedid+"~"+balance+"~"+clientid+"~"+client.getMobNo());
												
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

	 public String refundipdmedicine() throws Exception {
		 if (!verifyLogin(request)) {

				return "login";
			}
		   Connection connection=null;
			try {
				connection=Connection_provider.getconnection();
				PharmacyDAO pharmacyDAO=new JDBCPharmacyDAO(connection);
				InventoryProductDAO inventoryProductDAO=new JDBCInventoryProductDAO(connection);
				EmrDAO emrDAO=new JDBCEmrDAO(connection);
				UserProfileDAO userProfileDAO=new JDBCUserProfileDAO(connection);
				ClientDAO clientDAO=new JDBCClientDAO(connection);
				BedDao bedDao = new JDBCBedDao(connection);
				IpdDAO  ipdDAO = new JDBCIpdDAO(connection);
				UserProfileDAO profileDAO=new JDBCUserProfileDAO(connection);
			
				int selectedid=emrForm.getSelectedid();
			    String paymode=emrForm.getPaymode();
			    String bill=request.getParameter("billno"); 
			    String balance = emrForm.getBalance();
			    String ipdid = emrForm.getIpdid();
			    selectedid = pharmacyDAO.getSelectedIdFromIpdid(ipdid);
			    
			    String location =(String) session.getAttribute("location");
			    if(location==null){
			    	location="0";
			    }
			    if(bill!=null){
			    	 
			    	if(bill.equals("")){
			    		 bill="0";
			    	}
			    }
			    else {
			    	bill="0";
			    }
			    
			    int billno=Integer.parseInt(bill);
			    String vat=emrForm.getVat();
			    
			    if(emrForm.getDiscount()!=null){
			    	 
			    	if(emrForm.getDiscount().equals("")){
			    		
			    		emrForm.setDiscount("0");
			    	}
			    }
			    else {
			    	emrForm.setDiscount("0");
			    }
			    
			    double discount=Double.parseDouble(emrForm.getDiscount());
			    String notes=emrForm.getNotes();
			    String total=emrForm.getTotal();
			    
			    Priscription priscription=null;
			    CompleteAppointment appointment=new CompleteAppointment();
			    String date=DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone()).split(" ")[0];
			    String clientname="";
			    	priscription = emrDAO.getPriscriptionParentData(selectedid);
			    	Client client = clientDAO.getClientDetails(priscription.getClientId());
			 	    clientname=client.getTitle()+" "+client.getFirstName()+" "+client.getLastName();
			 	    
			 	    String tpid="";
				    String payby="0";
				    if(client.getWhopay()!=null){
				    	
				    	if(client.getWhopay().equals("Client")){
				    		   tpid="0";
				    		   payby="0";
				    	}else {
				    		payby="1";
				    		tpid=client.getTypeName();
				    	}
				    }else {
				    	tpid="0";
				    	payby="0";
				    }
				    if(vat!=null){
				    	if(vat.equals("")){
				    		vat = "0";
				    		appointment.setVat(Double.parseDouble(vat));
				    	}
				    	else
				    		appointment.setVat(Double.parseDouble(vat));
				    }
				    priscription.setWhopay(payby);
				    priscription.setTpid(tpid);
				    
			    	appointment.setClientId(priscription.getClientId());
				    appointment.setPayBuy(client.getWhopay());
				    appointment.setCharges(""+total+"");
				    appointment.setChargeType("");
				    appointment.setVat(Double.parseDouble(vat));
				    appointment.setDiscount(discount);
				    appointment.setTotal(Double.parseDouble(total));
				    appointment.setNotes(notes);
				    appointment.setPriscid(selectedid);
				    appointment.setInvoiceDate(date);
				    if(balance!=null){
				    if(balance!="0" || balance!=""){
				    	String dbbalance = pharmacyDAO.getbalofpatient(priscription.getClientId());
						int bal = Integer.parseInt(dbbalance) + Integer.parseInt(balance);
						balance = ""+bal;
						int res = pharmacyDAO.updatebalpatient(balance,priscription.getClientId());
				    }
				   }
			     
				    appointment.setUserid(loginInfo.getUserId());
			    billno=pharmacyDAO.addMedicineBill(appointment);
			    int refundid= pharmacyDAO.getMaxLastReturnBillId();
			    int res = pharmacyDAO.setIsReturn(billno,refundid+1);
			    
			    //update in priscription 
			    //int result=pharmacyDAO.updatePriscEmrBill(billno,selectedid);
			    //update status
			    //result=pharmacyDAO.updateDeliverStatus("1", String.valueOf(selectedid));
			    UserProfile userProfile=null;
			    
			    for(Priscription prisc:emrForm.getMedicines()){
			    	
			    	priscription.setMdicinenameid(prisc.getMdicinenameid());
			    	
			    	priscription.setProduct_id(prisc.getProduct_id());
			    	String priString = prisc.getProduct_id();
			    	Product product=inventoryProductDAO.getProduct(prisc.getProduct_id());
			    	priscription.setSaleqty(prisc.getReturnqty());
			    	int result =pharmacyDAO.updateMedicineReturnQty(prisc.getId(),prisc.getReturnqty()) ;
			    	priscription.setReqqty(prisc.getReqqty());
			    	priscription.setMrp(product.getSale_price());
			    	if(selectedid!=0){
			    		userProfile= profileDAO.getUserprofileDetails(Integer.parseInt(priscription.getPrectionerid()));
				    	priscription.setFullname(userProfile.getFullname());
			    	} else {
			    		priscription.setFullname(priscription.getPractitionername());
			    	}
			    	priscription.setClientname(clientname);
			    	priscription.setDate(date);
			    	result =0;
			    	String pdtock =(product.getStock());
			    	if(product.getStock()!="0"){
			    		result=pharmacyDAO.addMedicineCharges(priscription,billno);
			    		result=inventoryProductDAO.updateMedicineQty(priscription.getSaleqty(),priscription.getProduct_id(),1);
			    	}
			    	
			    	//update inventory
			     }
			    
			    //Direct Record Payment
			    double tot=Double.parseDouble(total);
			    //  19 Sep 2018 generate pharmacy payment Seq No according location
			    int paymentseqno = pharmacyDAO.getPharmacyPaymentSeqNo(location);
				paymentseqno = paymentseqno+1;
			    int result=pharmacyDAO.recordPaymentMedicine(String.valueOf(billno),0,paymode,date,appointment.getClientId(),"0",discount,notes,appointment.getPclientid(),loginInfo.getUserId(),location,0,paymentseqno);
				session.setAttribute("billno", billno);
				int paymentid =result;
				int d=pharmacyDAO.recordRefundPayment(result,total);
			    session.setAttribute("billno", billno);
			    session.setAttribute("clientid", priscription.getClientId());
			    session.setAttribute("selectedid",selectedid);
				
			    
			    priscription = emrDAO.getPriscriptionParentData(selectedid);
				session.setAttribute("parentpriscdata", priscription);
//				System.out.println(priscription.getClientId());
				String lastmodified="";
				if(priscription.getLastmodified()!=null){
					
					lastmodified=priscription.getLastmodified().split(" ")[0];
					priscription.setLastmodified(lastmodified);
				}
				
				
				Client client1 = clientDAO.getPatient(Integer.parseInt(priscription.getClientId()));
				String name = client1.getTitle()+" "+client1.getFirstName()+" "+client1.getLastName();
				date  = DateTimeUtils.getDashboardTodayDate(loginInfo.getCountry());
				String dobyear[] = client.getDob().split("/");
				String curryear[] = date.split("/");
				
				int  age = Integer.parseInt(curryear[2]) - Integer.parseInt(dobyear[2]);
				client1.setAge(age);
				session.setAttribute("clientinfo", client1);
				// get clinic info
				Clinic clinic = new Clinic();
				ClinicDAO clinicDAO  = new JDBCClinicDAO(connection);
				clinic = clinicDAO.getCliniclistDetails(loginInfo.getClinicid());
				
				Location loc = clinicDAO.getRegisterdLication();
				clinic.setLocationname(loc.getAddress());
				
				String curdatetime = DateTimeUtils.getPriscDatetime(loginInfo.getTimeZone());
				clinic.setCurDateTime(curdatetime);
				clinic.setCurDateTime(priscription.getLastmodified());
				
				session.setAttribute("clinicinfo", clinic);
				
				//String ipdid = emrDAO.getpriscIpdId(selectedid);
				Bed bed = bedDao.getEditIpdData(ipdid);
				
				emrForm.setIpdid(ipdid);
				if(ipdid==null || ipdid.equals("0")){
					priscription.setIpdid("0");
				}else{
					priscription.setIpdid(ipdid);
				}
				
				String wardname=ipdDAO.getIpdWardName(bed.getWardid());
				priscription.setWardname(wardname);
				String bedname = ipdDAO.getIpdBedName(bed.getBedid());
				priscription.setBedname(bedname);
				
				emrForm.setCommencing(date);
				emrForm.setWardname(wardname);
				emrForm.setBedname(bedname);
				String wardbed=wardname+"/"+bedname;
				emrForm.setWardname(wardbed);
				if(priscription.getPrectionerid()!=null){
					
				if(!priscription.getPrectionerid().equals("")){
						 
						UserProfile userProfile1=userProfileDAO.getUserprofileDetails(Integer.parseInt(priscription.getPrectionerid()));
						emrForm.setPractitionerName(userProfile1.getFullname());
					}
				}
		    
				String paymode1=pharmacyDAO.getBillPaymode(billno);
				
				 CompleteAppointment completeAppointment=pharmacyDAO.getBillDetails(billno);
				 ArrayList<Priscription> medicineChargeList = new ArrayList<Priscription>();
				 if(selectedid==0 && billno==0){
					 for(Priscription prisc:emrForm.getMedicines()){
						 Priscription priscription2 = pharmacyDAO.getMedicineChargesbyid(prisc.getId(),0);
						 medicineChargeList.add(priscription2);
					 } 
				 }else{
					 medicineChargeList=pharmacyDAO.getMedicineChargesList(billno,loginInfo);
				 }
				  double subtotal=0;
				  double nettotal = 0;
				  for(Priscription p:medicineChargeList){

					  subtotal=subtotal+(Double.parseDouble(p.getMrp())*p.getSaleqty());
					  nettotal = Math.round(subtotal);
				  }
				  
				  emrForm.setSubtotal(DateTimeUtils.changeFormat(subtotal));
				  emrForm.setMedicineChargeList(medicineChargeList);
				  emrForm.setClientId(completeAppointment.getClientId());
				  if(selectedid==0 && billno==0){
					  emrForm.setTotal(DateTimeUtils.changeFormat(nettotal));
				  }
				  else {
					  emrForm.setTotal(DateTimeUtils.changeFormat(completeAppointment.getTotal()));
				  }
				  emrForm.setDiscount(String.valueOf(completeAppointment.getDiscount()));
				  emrForm.setVat(String.valueOf(completeAppointment.getVat()));
				  emrForm.setBillno(billno);
				  emrForm.setRefundamt(total);
				  emrForm.setSelectedid(selectedid);
				  emrForm.setPaymode(paymode1);
			} catch (Exception e) {
				e.printStackTrace();
			}finally{
				connection.close();
			}
			
		    return "refundbill";
	   }

	 
	 public String clearbalance() throws Exception{
		 if (!verifyLogin(request)) {

				return "login";
			}
		 Connection connection=null;
		 try {
			 	connection=Connection_provider.getconnection();
			 	PharmacyDAO pharmacyDAO=new JDBCPharmacyDAO(connection);
			 	String flag=request.getParameter("flag");
			 	String billno= request.getParameter("billno");
			 	String clientid= request.getParameter("clientid");

			 	String balance="0";
			 	
			 	if(flag.equals("1")){  //new patient
			 		 balance= pharmacyDAO.getpreviousbalance(Integer.parseInt(clientid));
			 		int result= pharmacyDAO.updatebalance(Integer.parseInt(clientid), "0");
			 		
			 	} else { //opd/ipd
			 		 balance= pharmacyDAO.getbalofpatient(clientid); 
			 		 int result= pharmacyDAO.updatebalpatient("0", clientid);
			 	}
			 	
			 	int result=pharmacyDAO.updateBalanceofBill(billno,balance); 
			 	
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
	 
	
		  
	 
	  public String edituser() throws Exception {
		  if (!verifyLogin(request)) {

				return "login";
			}
		  Connection connection=null;
		  try {
			  connection=Connection_provider.getconnection();
			  UserProfileDAO userProfileDAO= new JDBCUserProfileDAO(connection);
			  InventoryVendorDAO inventoryVendorDAO= new JDBCInventoryVendorDAO(connection);
			  PharmacyDAO pharmacyDAO = new JDBCPharmacyDAO(connection);
			  ArrayList<Master> locationList= pharmacyDAO.getAllLocation();
			  ArrayList<Master> stateList= inventoryVendorDAO.getAllStateList();
			  String id= request.getParameter("id");
			  String status= request.getParameter("status");
			  UserProfile userProfile=userProfileDAO.getPharmacyUserbyId(id); 
			  int index=Integer.parseInt(request.getParameter("index"));
			  StringBuffer buffer= new StringBuffer();
			  buffer.append("<td>"+(index+1)+"</td>");
			  buffer.append("<td><input type='text' class='form-control' value='"+userProfile.getFullname()+"' id='fullname"+index+"'  /></td>");
			  
			  buffer.append("<td>");
			  
			  	     buffer.append("<select class='form-control' id='location"+index+"' >");
			  	     buffer.append("<option value='0'>Select Location</option>");
			        for(Master master: locationList){
			        	 
			        	   int loc= Integer.parseInt(userProfile.getLocation());
			        	   if(loc==master.getId()){
			        		   buffer.append("<option selected='selected' value='"+master.getId()+"'>"+master.getName()+"</option>");
			        	   } else {
			        		   buffer.append("<option value='"+master.getId()+"'>"+master.getName()+"</option>");
			        	   }
			        	   
			        }
			  
			  buffer.append("<input type='hidden' class='form-control' value='"+userProfile.getPhone()+"' id='phone"+index+"'  /></td>");
			  
			  buffer.append("<td>");
			  buffer.append("<select class='form-control' id='state"+index+"' >");
		  	     buffer.append("<option value='0'>Select State</option>");
		        for(Master master: stateList){
		        	 
		        	   int state= Integer.parseInt(userProfile.getState());
		        	   if(state==master.getId()){
		        		   buffer.append("<option selected='selected' value='"+master.getId()+"'>"+master.getName()+"</option>");
		        	   } else {
		        		   buffer.append("<option value='"+master.getId()+"'>"+master.getName()+"</option>");
		        	   }
		        }
			  
			  buffer.append("</td>");
			  
			  buffer.append("<td><input type='text' readonly='readonly' class='form-control' value='"+userProfile.getUserid()+"' onchange='UserIdExist(this.value)' id='userid"+index+"'  /></td>");
			  buffer.append("<td><input type='password' readonly='readonly' value='"+userProfile.getPassword()+"'  class='form-control' id='password"+index+"'  /></td>");
			  
			  buffer.append("<td>");
			  buffer.append("<div class='onoffswitch greensea'>");
			 
			  if(status.equals("0")){
				  buffer.append("<input onclick=setLoginAccesss("+id+","+status+") type='checkbox' name='onoffswitch' class='onoffswitch-checkbox' id='show-offline"+index+"' >");
			  } else {
				  
				  buffer.append("<input onclick=setLoginAccesss("+id+","+status+") type='checkbox' name='onoffswitch' class='onoffswitch-checkbox' id='show-offline"+index+"' checked='checked' > ");
			  }
			  buffer.append("<label class='onoffswitch-label' for='show-offline"+index+"'>");
			  buffer.append("<span class='onoffswitch-inner'></span>");
			  buffer.append(" <span class='onoffswitch-switch'></span>");
			  buffer.append("</label>");
			  buffer.append("</div>");
			  buffer.append("</td>");
			  buffer.append("<td><a href='#' onclick='openUserSetting("+id+","+status+")'>Setting</a></td>");
			  buffer.append("<td class='text-center'><a herf='#' onclick='updateuser("+index+","+id+")' >Update</a>&nbsp;&nbsp;<a herf='#'><i class='' style='color:red;'></i></a></td>");
			  
			  buffer.append("<td></td>");
			  
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
	  
	  
	  public String updateuser() throws Exception  {
		  if (!verifyLogin(request)) {

				return "login";
			}
		   Connection connection=null;
		   try {
			   connection=Connection_provider.getconnection();
			   UserProfileDAO userProfileDAO= new JDBCUserProfileDAO(connection);
			   String fullname= request.getParameter("fullname");
			   String phone= request.getParameter("phone");
			   String userid= request.getParameter("userid");
			   String password= request.getParameter("password");
			   String location= request.getParameter("location");
			   String id = request.getParameter("id");
			   String state= request.getParameter("state");
			   
			   UserProfile userProfile= new UserProfile();
			   String str[]= fullname.split(" ");
			   if(str.length>1){
				   
				   userProfile.setFirstname(str[0]);
				   userProfile.setLastname(str[1]);
			   } else {
				   userProfile.setFirstname(str[0]);
				   userProfile.setLastname("");
			   }
			   
			   userProfile.setUserid(userid);
			   userProfile.setId(Integer.parseInt(id));
			   userProfile.setPassword(password);
			   userProfile.setPhone(phone);
			   userProfile.setUserType(4);
			   userProfile.setLocation(location);
			   userProfile.setJobtitle("Pharmacist");
			   userProfile.setState(state);
			   
			   UserProfile master=userProfileDAO.getPharmacyUserbyId(id);
			   
			   int selectedid = userProfileDAO.updatePharmacyUserbyId(userProfile);
			   
			   connection = DriverManager.getConnection(""+Constants.DB_HOST+":3306/admin",""+Constants.DB_USER+"",""+Constants.DB_PWD+"");
			   userProfileDAO = new JDBCUserProfileDAO(connection);
			   int result= userProfileDAO.updatePractitionerToAdmin(userProfile, loginInfo.getClinicUserid(),master.getUserid());  
			   
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
	  
	  
	   public String sendsms() throws Exception{ //sending sms
		   if (!verifyLogin(request)) {

				return "login";
			}
		   Connection connection=null;
		   try {
			   
			   connection=Connection_provider.getconnection();
			   PharmacyDAO pharmacyDAO=new JDBCPharmacyDAO(connection);
			   ClientDAO clientDAO=new JDBCClientDAO(connection);
			   UserProfileDAO profileDAO=new JDBCUserProfileDAO(connection);
			   int billno =Integer.parseInt(request.getParameter("billno"));
			   CompleteAppointment appointment=pharmacyDAO.getBillDetails(billno);
			   UserProfile userProfile = profileDAO.getPharmacyUserDetails(loginInfo.getClinicid());
			   String clinicname=userProfile.getClinicname();
			   String mobileNo="";
               if(!appointment.getPclientid().equals("0")){
            	   mobileNo=pharmacyDAO.getPharmacyPatient(appointment.getPclientid()).getMobile();
               }else {
            	   mobileNo =clientDAO.getClientDetails(appointment.getClientId()).getMobNo();
               }
			   String msg="";
			   if(appointment.getBalance().equals("0")){
				   msg="Dear customer, thanks for the payment of Rs. "+appointment.getTotal()+" towards your bill no- "+billno+"  from- "+clinicname+"";   
			   } else {
				   msg="Dear customer, thanks for the payment of Rs. "+appointment.getTotal()+" towards your bill no- "+billno+" and outstanding Rs."+appointment.getBalance()+" from- "+clinicname+" ";
			   }
			   
			   SmsService service= new SmsService();
			   String templateid="";
			   service.sendSms(msg, mobileNo, loginInfo, new EmailLetterLog(),templateid);
			   
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
	   public String getBalanceFromDB() throws Exception{
		   if (!verifyLogin(request)) {

				return "login";
			}
			Connection connection = null;
			try {
				connection = Connection_provider.getconnection();
				//String extpid = request.getParameter("extpid");
				String rebalance = request.getParameter("rebalance");
				PharmacyDAO pharmacyDAO = new JDBCPharmacyDAO(connection);
				//int result = pharmacyDAO.clearbalfromdb(extpid);
				
				//String data = "<h3 style='color:#d9534f;'>Balance : <input type='text' id='rebalance' value='"+rebalance+"'> </h3>";
				String data = "<h3 style='color: #d9534f;'> Balance : <input type='text' id='rebalance' name='previous_balance' onkeyup='setReturnBalance(this.value)' value='"+rebalance+"' class='form-control balancetext'> </h3>";
				response.setContentType("text/html");
				response.setHeader("Cache-Control", "no-cache");
				response.getWriter().write(""+data+"");
			} catch (Exception e) {
				e.printStackTrace();
			}finally{
				connection.close();
			}
			return null;
		}
	 
	   
	  public String updatebill() throws Exception {
		  if (!verifyLogin(request)) {

				return "login";
			}
		Connection connection=null;  
		try {
			connection=Connection_provider.getconnection();
			PharmacyDAO pharmacyDAO= new JDBCPharmacyDAO(connection);
			InventoryProductDAO inventoryProductDAO= new JDBCInventoryProductDAO(connection);
			ClientDAO clientDAO =  new JDBCClientDAO(connection);
			EmrDAO emrDAO= new  JDBCEmrDAO(connection);
			String billno =request.getParameter("billno");
			String cgst= emrForm.getCgst();
			String sgst= emrForm.getSgst();
			String deletedcharges= request.getParameter("deletedcharges");
			String edit_note =request.getParameter("edit_note");
			int selectedid= Integer.parseInt(request.getParameter("selectedid"));
			Priscription priscription= new Priscription();
			CompleteAppointment appointment= pharmacyDAO.getBillDetails(Integer.parseInt(billno));
			String balance = emrForm.getBalance();
			String clientname="";
			String date=DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone()).split(" ")[0];
			String user="";
			
			
			if(selectedid!=0){
		    	priscription = emrDAO.getPriscriptionParentData(selectedid);
		    	selectedid=pharmacyDAO.addPriscriptionForBill(priscription);
		    	Client client=clientDAO.getClientDetails(priscription.getClientId());
		    	double tot= Double.parseDouble(client.getBalance()) + Double.parseDouble(balance);
		    	//int res=pharmacyDAO.updatebalpatient(balance, priscription.getClientId());
		 	    clientname=client.getTitle()+" "+client.getFirstName()+" "+client.getLastName();
		 	    priscription.setClientId(priscription.getClientId());
		 	    String tpid="";
			    String payby="0";
			    if(client.getWhopay()!=null){
			    	
			    	if(client.getWhopay().equals("Client")){
			    		   tpid="0";
			    		   payby="0";
			    	}else {
			    		payby="1";
			    		tpid=client.getTypeName();
			    	}
			    }else {
			    	tpid="0";
			    	payby="0";
			    }
			    priscription.setWhopay(payby);
			    priscription.setTpid(tpid);
			    priscription.setBalance(balance);
		    	
		    } else {
		    	    String pclientid=pharmacyDAO.getPharmacyClientidFromBill(Integer.parseInt(billno));
		    	    
				    if(!pclientid.equals("0")){
				    	int pharmacypatientid= Integer.parseInt(pclientid);
						String bal= pharmacyDAO.getpreviousbalance(pharmacypatientid);
						double temp =Double.parseDouble(bal) + Double.parseDouble(balance);
					   // int result = pharmacyDAO.updatebalance(pharmacypatientid,String.valueOf(temp));
					    priscription=pharmacyDAO.getPharmacyPatient(pclientid);
			    	    clientname=priscription.getFullname();
			    	    user = priscription.getPractitionername();
					    priscription.setWhopay("0");
					    priscription.setPclientid(pclientid);
					    priscription.setTpid("0");
					    priscription.setBalance(balance);
					    
					    String fullname =emrForm.getFullname();
					    String mobile= emrForm.getMobno();
					    String location= emrForm.getWardname();
					    String practitionerName= emrForm.getPractitionerName();
					    priscription.setFullname(fullname);
					    priscription.setMobile(mobile);
					    priscription.setLocation(location);
					    priscription.setPractitionername(practitionerName);
					    
					    int res= pharmacyDAO.updateClientData(priscription);  
					    
					    
				    }else{
				    	String ipdopd=pharmacyDAO.getClientIDFromBillNo(Integer.parseInt(billno));
				    	Client client=clientDAO.getClientDetails(ipdopd);
				    	double tot= Double.parseDouble(client.getBalance()) + Double.parseDouble(balance);
				    	//int res=pharmacyDAO.updatebalpatient(balance, priscription.getClientId());
				 	    clientname=client.getTitle()+" "+client.getFirstName()+" "+client.getLastName();
				 	    priscription.setClientId(priscription.getClientId());
				 	    String tpid="";
					    String payby="0";
					    if(client.getWhopay()!=null){
					    	
					    	if(client.getWhopay().equals("Client")){
					    		   tpid="0";
					    		   payby="0";
					    	}else {
					    		payby="1";
					    		tpid=client.getTypeName();
					    	}
					    }else {
					    	tpid="0";
					    	payby="0";
					    }
					    priscription.setWhopay(payby);
					    priscription.setTpid(tpid);
					    priscription.setBalance(balance);
				    }
				    
		    }
			
			
			 for(String str:deletedcharges.split(",")){
				 
				  int d=Integer.parseInt(str);
				  if(d==0){
					   continue;
				  }
				  
				  d= pharmacyDAO.deleteMedicineCharges(str);
				 
			 }
			
			
			 for(Priscription prisc:emrForm.getMedicines()){
			    	
				     if(prisc==null){
				    	 continue;
				     }
				     if(prisc.getId()==0){
				    	 continue;
				     }
			    	 int id= Integer.parseInt(prisc.getMedicineid());
			    	 int saleqty= prisc.getSaleqty();
			 		 double mrp =Double.parseDouble(prisc.getSale_price());
			 		 double subtotal= saleqty * mrp;
				 	 if(id>0){
				 		 
				 			int res= pharmacyDAO.updateBillCharges(saleqty,DateTimeUtils.changeFormat(mrp),id);
				 		 //update charges
				 	} else {
				 		//insert new 
				 		
				 		priscription.setProduct_id(prisc.getProduct_id());
				 		Product product=inventoryProductDAO.getProduct(prisc.getProduct_id());
				    	priscription.setMdicinenameid(prisc.getMdicinenameid());
				    	priscription.setSaleqty(prisc.getSaleqty());
				    	priscription.setPclientid(appointment.getPclientid());
				    	priscription.setReqqty(prisc.getReqqty());
				    	priscription.setMrp(prisc.getSale_price());
				    	priscription.setFullname(user);
				    	priscription.setClientname(clientname);
				    	priscription.setDate(date);
				 		
				    	if(product.getStock()!="0"){
				    		int result=pharmacyDAO.addMedicineCharges(priscription,Integer.parseInt(billno));
				    		result=inventoryProductDAO.updateMedicineQty(priscription.getSaleqty(),priscription.getProduct_id(),0);
				    	}
				 		
				 	}
			    }
			 
			  
			    if(emrForm.getDiscount()!=null){
			    	if(emrForm.getDiscount().equals("")){
			    		emrForm.setDiscount("0");
			    	}
			    }
			    else {
			    	emrForm.setDiscount("0");
			    }

			    String vat=emrForm.getVat();
			    double discount=Double.parseDouble(emrForm.getDiscount());
			    String total=emrForm.getTotal();
			    
			    int result=pharmacyDAO.updatePaymentMedicine(String.valueOf(billno),total,date,"0",discount,vat);
			    //update bill total amt
			    
			    result =pharmacyDAO.updateBillTotal(total,billno,edit_note);
			    result =pharmacyDAO.updateBalanceByBill(billno, balance);
			    result =pharmacyDAO.updateGstOfBill(vat,cgst,billno);
			    
			    
			    
			    Clinic clinic = new Clinic();
				ClinicDAO clinicDAO  = new JDBCClinicDAO(connection);
				clinic = clinicDAO.getCliniclistDetails(loginInfo.getClinicid());
				
				Location location = clinicDAO.getRegisterdLication();
				clinic.setLocationname(location.getAddress());
				int bill =Integer.parseInt(billno);
				int selected= Integer.parseInt(request.getParameter("selectedid"));
				clinic.setCurDateTime(DateTimeUtils.getCommencingDate1(date));
				session.setAttribute("billno", bill);
				session.setAttribute("selectedid",selected );
				
				
			
		} catch (Exception e) {

			e.printStackTrace();
		}
		  
		  return "redirectbill";
	  }
	  public String setUserEdit_bill() throws Exception{
		  if (!verifyLogin(request)) {

				return "login";
			}
			 Connection connection = null;
			 try {
				connection = Connection_provider.getconnection();
				PharmacyDAO pharmacyDAO = new JDBCPharmacyDAO(connection);
				String userid = request.getParameter("userid");
				String status = request.getParameter("status");
				
				 Calendar cal = Calendar.getInstance();
			        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss.SS");
			        String dateString = sdf.format(cal.getTime());
			        
					String Accessdatetime=dateString;
					String CurrentUser=loginInfo.getUserId();
					
				
				if(status.equals("0") || status=="0"){
					status = "1";
				}
				else {
					status = "0";
				}
				Priscription priscription = pharmacyDAO.setUserEditBillAuthority(userid,status,Accessdatetime,CurrentUser);
				StringBuilder sb = new StringBuilder();
				if(priscription.getEdit_bill()=="0" || priscription.getEdit_bill().equals("0")){
					sb.append("<input type='checkbox' onclick='setEdit_billAccesss("+priscription.getId()+","+priscription.getEdit_bill()+")' id='edit_bill"+priscription.getId()+"' class='onoffswitch-checkbox'>");
					sb.append("<label class='onoffswitch-label' for='edit_bill"+priscription.getId()+"'>");
					sb.append("<span class='onoffswitch-inner'></span>");
					sb.append("<span class='onoffswitch-switch'></span>");
					sb.append("</label>");
				}
				else
				{
					sb.append("<input type='checkbox' onclick='setEdit_billAccesss("+priscription.getId()+","+priscription.getEdit_bill()+")' id='edit_bill"+priscription.getId()+"' class='onoffswitch-checkbox'  checked='checked'>");
					sb.append("<label class='onoffswitch-label' for='edit_bill"+priscription.getId()+"'>");
					sb.append("<span class='onoffswitch-inner'></span>");
					sb.append("<span class='onoffswitch-switch'></span>");
					sb.append("</label>");
				}
				
				response.setContentType("text/html");
				response.setHeader("Cache-Control", "no-cache");
				response.getWriter().write(""+sb.toString()+"");
			} catch (Exception e) {
				e.printStackTrace();
			}finally{
				connection.close();
			}
			 return null;
		 }
	  
	  public String setUserDelete_bill() throws Exception{
		  if (!verifyLogin(request)) {

				return "login";
			}
			 Connection connection = null;
			 try {
				connection = Connection_provider.getconnection();
				PharmacyDAO pharmacyDAO = new JDBCPharmacyDAO(connection);
				String userid = request.getParameter("userid");
				String status = request.getParameter("status");
				 Calendar cal = Calendar.getInstance();
			        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss.SS");
			        String dateString = sdf.format(cal.getTime());
			        
					String Accessdatetime=dateString;
					String CurrentUser=loginInfo.getUserId();
					
				
				if(status.equals("0") || status=="0"){
					status = "1";
				}
				else {
					status = "0";
				}
				Priscription priscription = pharmacyDAO.setUserDeleteBillAuthority(userid,status,Accessdatetime,CurrentUser);
				StringBuilder sb = new StringBuilder();
				if(priscription.getDelete_bill()=="0" || priscription.getDelete_bill().equals("0")){
					sb.append("<input type='checkbox' onclick='setDelete_billAccesss("+priscription.getId()+","+priscription.getDelete_bill()+")' id='delete_bill"+priscription.getId()+"' class='onoffswitch-checkbox'>");
					sb.append("<label class='onoffswitch-label' for='delete_bill"+priscription.getId()+"'>");
					sb.append("<span class='onoffswitch-inner'></span>");
					sb.append("<span class='onoffswitch-switch'></span>");
					sb.append("</label>");
				}
				else
				{
					sb.append("<input type='checkbox' onclick='setDelete_billAccesss("+priscription.getId()+","+priscription.getDelete_bill()+")' id='delete_bill"+priscription.getId()+"' class='onoffswitch-checkbox'  checked='checked'>");
					sb.append("<label class='onoffswitch-label' for='delete_bill"+priscription.getId()+"'>");
					sb.append("<span class='onoffswitch-inner'></span>");
					sb.append("<span class='onoffswitch-switch'></span>");
					sb.append("</label>");
				}
				
				response.setContentType("text/html");
				response.setHeader("Cache-Control", "no-cache");
				response.getWriter().write(""+sb.toString()+"");
			} catch (Exception e) {
				e.printStackTrace();
			}finally{
				connection.close();
			}
			 return null;
		 }
	   
	  public String setUserEdit_PurchaseOrder() throws Exception{
		  if (!verifyLogin(request)) {

				return "login";
			}
			 Connection connection = null;
			 try {
				connection = Connection_provider.getconnection();
				PharmacyDAO pharmacyDAO = new JDBCPharmacyDAO(connection);
				String userid = request.getParameter("userid");
				String status = request.getParameter("status");
				
				Calendar cal = Calendar.getInstance();
		        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss.SS");
		        String dateString = sdf.format(cal.getTime());
		        
				String Accessdatetime=dateString;
				String CurrentUser=loginInfo.getUserId();
				
				if(status.equals("0") || status=="0"){
					status = "1";
				}
				else {
					status = "0";
				}
				Priscription priscription = pharmacyDAO.setUserEditPurchaseOrderAuthority(userid,status,Accessdatetime,CurrentUser);
				StringBuilder sb = new StringBuilder();
				if(priscription.getEdit_purchaseorder()=="0" || priscription.getEdit_purchaseorder().equals("0")){
					sb.append("<input type='checkbox' onclick='setEdit_PurchaseOrderAccesss("+priscription.getId()+","+priscription.getEdit_purchaseorder()+")' id='edit_purchaseorder"+priscription.getId()+"' class='onoffswitch-checkbox'>");
					sb.append("<label class='onoffswitch-label' for='edit_purchaseorder"+priscription.getId()+"'>");
					sb.append("<span class='onoffswitch-inner'></span>");
					sb.append("<span class='onoffswitch-switch'></span>");
					sb.append("</label>");
				}
				else
				{
					sb.append("<input type='checkbox' onclick='setEdit_PurchaseOrderAccesss("+priscription.getId()+","+priscription.getEdit_purchaseorder()+")' id='edit_purchaseorder"+priscription.getId()+"' class='onoffswitch-checkbox'  checked='checked'>");
					sb.append("<label class='onoffswitch-label' for='delete_bill"+priscription.getId()+"'>");
					sb.append("<span class='onoffswitch-inner'></span>");
					sb.append("<span class='onoffswitch-switch'></span>");
					sb.append("</label>");
				}
				
				response.setContentType("text/html");
				response.setHeader("Cache-Control", "no-cache");
				response.getWriter().write(""+sb.toString()+"");
			} catch (Exception e) {
				e.printStackTrace();
			}finally{
				connection.close();
			}
			 return null;
		 }
	  public String setUserDelete_PurchaseOrder() throws Exception{
		  if (!verifyLogin(request)) {

				return "login";
			}
			 Connection connection = null;
			 try {
				connection = Connection_provider.getconnection();
				PharmacyDAO pharmacyDAO = new JDBCPharmacyDAO(connection);
				String userid = request.getParameter("userid");
				String status = request.getParameter("status");
				
				Calendar cal = Calendar.getInstance();
		        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss.SS");
		        String dateString = sdf.format(cal.getTime());
		        
				String Accessdatetime=dateString;
				String CurrentUser=loginInfo.getUserId();
				
				if(status.equals("0") || status=="0"){
					status = "1";
				}
				else {
					status = "0";
				}
				Priscription priscription = pharmacyDAO.setUserDeletePurchaseOrderAuthority(userid,status,Accessdatetime,CurrentUser);
				StringBuilder sb = new StringBuilder();
				if(priscription.getDelete_purchaseorder()=="0" || priscription.getDelete_purchaseorder().equals("0")){
					sb.append("<input type='checkbox' onclick='setDelete_PurchaseOrderAccesss("+priscription.getId()+","+priscription.getDelete_purchaseorder()+")' id='delete_purchaseorder"+priscription.getId()+"' class='onoffswitch-checkbox'>");
					sb.append("<label class='onoffswitch-label' for='delete_purchaseorder"+priscription.getId()+"'>");
					sb.append("<span class='onoffswitch-inner'></span>");
					sb.append("<span class='onoffswitch-switch'></span>");
					sb.append("</label>");
				}
				else
				{
					sb.append("<input type='checkbox' onclick='setDelete_PurchaseOrderAccesss("+priscription.getId()+","+priscription.getDelete_purchaseorder()+")' id='delete_purchaseorder"+priscription.getId()+"' class='onoffswitch-checkbox'  checked='checked'>");
					sb.append("<label class='onoffswitch-label' for='delete_purchaseorder"+priscription.getId()+"'>");
					sb.append("<span class='onoffswitch-inner'></span>");
					sb.append("<span class='onoffswitch-switch'></span>");
					sb.append("</label>");
				}
				
				response.setContentType("text/html");
				response.setHeader("Cache-Control", "no-cache");
				response.getWriter().write(""+sb.toString()+"");
			} catch (Exception e) {
				e.printStackTrace();
			}finally{
				connection.close();
			}
			 return null;
		 }
	   
	  public String seteditcatalogue() throws Exception{
		  if (!verifyLogin(request)) {

				return "login";
			}
			 Connection connection = null;
			 try {
				connection = Connection_provider.getconnection();
				PharmacyDAO pharmacyDAO = new JDBCPharmacyDAO(connection);
				String userid = request.getParameter("userid");
				String status = request.getParameter("status");
				
				Calendar cal = Calendar.getInstance();
		        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss.SS");
		        String dateString = sdf.format(cal.getTime());
		        
				String Accessdatetime=dateString;
				String CurrentUser=loginInfo.getUserId();
				
				
				if(status.equals("0") || status=="0"){
					status = "1";
				}
				else {
					status = "0";
				}
				Priscription priscription = pharmacyDAO.setUserEditCatalogue(userid,status,Accessdatetime,CurrentUser);
				StringBuilder sb = new StringBuilder();
				if(priscription.getEdit_catalogue()==0){
					sb.append("<input type='checkbox' onclick='setEditCatalogue("+priscription.getId()+","+priscription.getEdit_catalogue()+")' id='edit_catalogue"+priscription.getId()+"' class='onoffswitch-checkbox'>");
					sb.append("<label class='onoffswitch-label' for='edit_catalogue"+priscription.getId()+"'>");
					sb.append("<span class='onoffswitch-inner'></span>");
					sb.append("<span class='onoffswitch-switch'></span>");
					sb.append("</label>");
				}
				else
				{
					sb.append("<input type='checkbox' onclick='setEditCatalogue("+priscription.getId()+","+priscription.getEdit_catalogue()+")' id='edit_catalogue"+priscription.getId()+"' class='onoffswitch-checkbox'  checked='checked'>");
					sb.append("<label class='onoffswitch-label' for='edit_catalogue"+priscription.getId()+"'>");
					sb.append("<span class='onoffswitch-inner'></span>");
					sb.append("<span class='onoffswitch-switch'></span>");
					sb.append("</label>");
				}
				
				response.setContentType("text/html");
				response.setHeader("Cache-Control", "no-cache");
				response.getWriter().write(""+sb.toString()+"");
			} catch (Exception e) {
				e.printStackTrace();
			}finally{
				connection.close();
			}
			 return null;
		 }
	  
	  public String setdeletecatalogue() throws Exception{
		  if (!verifyLogin(request)) {

				return "login";
			}
			 Connection connection = null;
			 try {
				connection = Connection_provider.getconnection();
				PharmacyDAO pharmacyDAO = new JDBCPharmacyDAO(connection);
				String userid = request.getParameter("userid");
				String status = request.getParameter("status");
				

				Calendar cal = Calendar.getInstance();
		        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss.SS");
		        String dateString = sdf.format(cal.getTime());
		        
				String Accessdatetime=dateString;
				String CurrentUser=loginInfo.getUserId();
				
				
				if(status.equals("0") || status=="0"){
					status = "1";
				}
				else {
					status = "0";
				}
				Priscription priscription = pharmacyDAO.setUserDeleteCatalogue(userid,status,Accessdatetime,CurrentUser);
				StringBuilder sb = new StringBuilder();
				if(priscription.getDelete_catalogue()==0){
					sb.append("<input type='checkbox' onclick='setDeleteCatalogue("+priscription.getId()+","+priscription.getDelete_catalogue()+")' id='delete_catalogue"+priscription.getId()+"' class='onoffswitch-checkbox'>");
					sb.append("<label class='onoffswitch-label' for='delete_catalogue"+priscription.getId()+"'>");
					sb.append("<span class='onoffswitch-inner'></span>");
					sb.append("<span class='onoffswitch-switch'></span>");
					sb.append("</label>");
				}
				else
				{
					sb.append("<input type='checkbox' onclick='setDeleteCatalogue("+priscription.getId()+","+priscription.getDelete_catalogue()+")' id='delete_catalogue"+priscription.getId()+"' class='onoffswitch-checkbox'  checked='checked'>");
					sb.append("<label class='onoffswitch-label' for='delete_catalogue"+priscription.getId()+"'>");
					sb.append("<span class='onoffswitch-inner'></span>");
					sb.append("<span class='onoffswitch-switch'></span>");
					sb.append("</label>");
				}
				
				response.setContentType("text/html");
				response.setHeader("Cache-Control", "no-cache");
				response.getWriter().write(""+sb.toString()+"");
			} catch (Exception e) {
				e.printStackTrace();
			}finally{
				connection.close();
			}
			 return null;
		 }
	  
	  public String setbackdate() throws Exception {
		  if (!verifyLogin(request)) {

				return "login";
			}
		  Connection connection = null;
			 try {
				connection = Connection_provider.getconnection();
				PharmacyDAO pharmacyDAO = new JDBCPharmacyDAO(connection);
				String userid = request.getParameter("userid");
				String status = request.getParameter("status");


				Calendar cal = Calendar.getInstance();
		        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss.SS");
		        String dateString = sdf.format(cal.getTime());
		        
				String Accessdatetime=dateString;
				String CurrentUser=loginInfo.getUserId();
				
				
				if(status.equals("0") || status=="0"){
					status = "1";
				}
				else {
					status = "0";
				}
				Priscription priscription = pharmacyDAO.setBackDateAccess(userid,status,Accessdatetime,CurrentUser);
				StringBuilder sb = new StringBuilder();
				if(priscription.getAccess_back_date()==0){
					sb.append("<input type='checkbox' onclick='setBackDateAccess("+priscription.getId()+","+priscription.getAccess_back_date()+")' id='backdate"+priscription.getId()+"' class='onoffswitch-checkbox'>");
					sb.append("<label class='onoffswitch-label' for='backdate"+priscription.getId()+"'>");
					sb.append("<span class='onoffswitch-inner'></span>");
					sb.append("<span class='onoffswitch-switch'></span>");
					sb.append("</label>");
				}
				else
				{
					sb.append("<input type='checkbox' onclick='setBackDateAccess("+priscription.getId()+","+priscription.getAccess_back_date()+")' id='backdate"+priscription.getId()+"' class='onoffswitch-checkbox'  checked='checked'>");
					sb.append("<label class='onoffswitch-label' for='backdate"+priscription.getId()+"'>");
					sb.append("<span class='onoffswitch-inner'></span>");
					sb.append("<span class='onoffswitch-switch'></span>");
					sb.append("</label>");
				}
				
				response.setContentType("text/html");
				response.setHeader("Cache-Control", "no-cache");
				response.getWriter().write(""+sb.toString()+"");
			} catch (Exception e) {
				e.printStackTrace();
			}finally{
				connection.close();
			}
			 return null;
	  }
	  public String settpbill() throws Exception {
		  if (!verifyLogin(request)) {

				return "login";
			}
		  Connection connection = null;
			 try {
				connection = Connection_provider.getconnection();
				PharmacyDAO pharmacyDAO = new JDBCPharmacyDAO(connection);
				String userid = request.getParameter("userid");
				String status = request.getParameter("status");


				Calendar cal = Calendar.getInstance();
		        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss.SS");
		        String dateString = sdf.format(cal.getTime());
		        
				String Accessdatetime=dateString;
				String CurrentUser=loginInfo.getUserId();
				
				
				if(status.equals("0") || status=="0"){
					status = "1";
				}
				else {
					status = "0";
				}
				Priscription priscription = pharmacyDAO.setTpBillAccess(userid,status,Accessdatetime,CurrentUser);
				StringBuilder sb = new StringBuilder();
				if(priscription.getTpbill()==0){
					sb.append("<input type='checkbox' onclick='setTpBillAccess("+priscription.getId()+","+priscription.getTpbill()+")' id='tpbill"+priscription.getId()+"' class='onoffswitch-checkbox'>");
					sb.append("<label class='onoffswitch-label' for='tpbill"+priscription.getId()+"'>");
					sb.append("<span class='onoffswitch-inner'></span>");
					sb.append("<span class='onoffswitch-switch'></span>");
					sb.append("</label>");
				}
				else
				{
					sb.append("<input type='checkbox' onclick='setTpBillAccess("+priscription.getId()+","+priscription.getTpbill()+")' id='tpbill"+priscription.getId()+"' class='onoffswitch-checkbox'  checked='checked'>");
					sb.append("<label class='onoffswitch-label' for='tpbill"+priscription.getId()+"'>");
					sb.append("<span class='onoffswitch-inner'></span>");
					sb.append("<span class='onoffswitch-switch'></span>");
					sb.append("</label>");
				}
				
				response.setContentType("text/html");
				response.setHeader("Cache-Control", "no-cache");
				response.getWriter().write(""+sb.toString()+"");
			} catch (Exception e) {
				e.printStackTrace();
			}finally{
				connection.close();
			}
			 return null;
	  }
	  
	  public String getbillbalance() throws Exception {
		  if (!verifyLogin(request)) {

				return "login";
			}
			Connection connection=null;  
			try {
				connection=Connection_provider.getconnection();
				PharmacyDAO pharmacyDAO=new JDBCPharmacyDAO(connection);
				InventoryProductDAO productDAO = new JDBCInventoryProductDAO(connection);
				String payamt =request.getParameter("payamt");
				String date= DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone()).split(" ")[0];
				String dateTime= DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone());
				String paymode= request.getParameter("paymode");
				String paynote ="";
				String paynotes= request.getParameter("paynotes");
				String billno= request.getParameter("billno");
				String discount= request.getParameter("discount1");
				String discinper = request.getParameter("discinper");
				if(discinper!=null){
					if(discinper.equals("")){
						discinper="0";
					}
				}else{
					discinper="0";
				}
				CompleteAppointment appointment=pharmacyDAO.getBillDetails(Integer.parseInt(billno));
				String location = (String) session.getAttribute("location"); 
				if(location==null){
					location="0";
				}
				Priscription priscription= new Priscription();
				priscription.setNotes(paynotes);
				priscription.setPaymode(paymode);
				priscription.setDate(date);
				priscription.setUserid(loginInfo.getUserId());
				priscription.setLocation(location);
				priscription.setDateTime(dateTime);
				priscription.setPclientid(appointment.getPclientid());
				priscription.setClientId(appointment.getClientId());
				
				priscription.setDisscount(discount);
				double disc=Double.parseDouble(discount);
				priscription.setDiscount(disc);
				
				if(paymode.equals("Card")){
					 paynote= request.getParameter("card");
				}
				if(paymode.equals("Cheque")){
					 paynote= request.getParameter("chequeno");
				}
				if(paymode.equals("NEFT") || paymode.equals("Online Payment")){
					 paynote= request.getParameter("neftid");
				}
				priscription.setPaynote(paynote);
				
				if(!appointment.getPclientid().equals("0")){  //new patient
			 		String balance= pharmacyDAO.getpreviousbalance(Integer.parseInt(appointment.getPclientid()));
			 		double nowbal =Double.parseDouble(balance) - Double.parseDouble(payamt);
			 		 if(nowbal<0){
			 			  nowbal=0;
			 		 }
			 		int result= pharmacyDAO.updatebalance(Integer.parseInt(appointment.getPclientid()), String.valueOf(nowbal));
			 	} else { //opd/ipd
			 		String balance= pharmacyDAO.getbalofpatient(appointment.getClientId());
			 		double nowbal =Double.parseDouble(balance) - Double.parseDouble(payamt);
			 		 if(nowbal<0){
			 			  nowbal=0;
			 		 }
			 		 int result= pharmacyDAO.updatebalpatient(String.valueOf(nowbal), appointment.getClientId());
			 	}
				double balance= pharmacyDAO.getBillBalance(billno);
				priscription.setBalance(DateTimeUtils.changeFormat(balance));
				priscription.setPayment(payamt);
				priscription.setBillno(billno);
				
				//int d=pharmacyDAO.updateBillBalance(billno);
				double payAmount1= Double.parseDouble(payamt);
				
				double temp=balance-payAmount1;
				int d=pharmacyDAO.updateBillBalance(billno,temp);
				int result = pharmacyDAO.updateBillFinalPaymode(billno,paymode);
				
				boolean flag = pharmacyDAO.checkBillCreditStatus(billno);
				if(flag){
					int res = pharmacyDAO.updateGstPaymentFlag(billno);
				}
				//  18 Jul 2018
				  int issame=0;
				  if(appointment.getInvoiceDate()!=null){
					  if(priscription.getDate()!=null){
						  if(priscription.getDate().equals(appointment.getInvoiceDate())){
							  issame =1;
						  }
					  }
				  }
				
				 //double disc1 = pharmacyDAO.getPreviousBillDiscount(Integer.parseInt(billno));
				  //double totaldisc = disc1 + priscription.getDiscount();
				  if(priscription.getDiscount()>0){
					  double totaldisc=priscription.getDiscount();
					  double totaldebit = appointment.getTotal()-totaldisc;
					  int res = pharmacyDAO.updateBillDiscount(totaldisc,Integer.parseInt(billno),totaldebit);
					  ArrayList<Priscription> arrayList = pharmacyDAO.getMedicineChargeList(Integer.parseInt(billno));
		    	  		for (Priscription priscription2 : arrayList) {
		    	  			
					    	double tvat= Double.parseDouble(priscription2.getVat());
					    	double tot= 0;
					    	try{
					    		tot= priscription2.getQty() * Double.parseDouble(priscription2.getMrp());
					    	}catch (Exception e) {
								// TODO: handle exception
							}
					    	double temptot=0;
					    	double tempvat =0;
					    	tempvat=tot*Double.parseDouble(discinper)/100;
					    	temptot = tot - tempvat;
					    	double dividevat= 100+tvat;
					    	/*double gst= tot*tvat/dividevat;*/
					    	double gst= temptot*tvat/dividevat;
					    	double half= gst/2;
					    	half = Math.round(half*100.0)/100.0;
					    	gst = Math.round(gst*100.0)/100.0;
					    	int es = pharmacyDAO.updateMedicineChargeGST(priscription2.getId(),gst,half,tempvat,tvat);
						}
				  }
				  
				  /*double discper = (priscription.getDiscount()/amt)*100;
				  discper = Math.round(discper);
				  
				  double discvaluetotal = appointment.getTotal()*discper/100;
				  discvaluetotal = Math.round(discvaluetotal);
				  if(discvaluetotal>0){
		    	  		double totaldebit = rs.getDouble(4)-discvaluetotal;
		    	  		int res = updateBillDiscount(discvaluetotal,billno,totaldebit);
				  }*/
			   /*double discper = (priscription.getDiscount()/appointment.getTotal())*100;
			   discper = Math.round(discper); 
			  
			   double discvalue = payAmount1*discper/100;
		       discvalue = Math.round(discvalue);*/
		       double totalbal = payAmount1-priscription.getDiscount();
		        //  19 Sep 2018 generate pharmacy payment Seq No according location
			    int paymentseqno = pharmacyDAO.getPharmacyPaymentSeqNo(location);
				paymentseqno = paymentseqno+1;
				int res= pharmacyDAO.recordPaymentMedicine(billno, totalbal, paymode, date, appointment.getClientId(), "", 0, paynotes, appointment.getPclientid(), loginInfo.getUserId(),location,issame,paymentseqno); 
				d=pharmacyDAO.updatePaymentNote(res, paynote);
				int paymentid= res;
				int parentid =pharmacyDAO.saveParentBillClearPayment(priscription);
				res= pharmacyDAO.saveChildBillClear(parentid,priscription);
				
				ChargesAccountProcessingDAO chargesAccountProcessingDAO = new JDBCChargeAccountProcesDAO(connection);
				
				if(!paymode.equals("") || !paymode.equals("Credit")){
			  		String locationname = pharmacyDAO.getLocationName(appointment.getLocation());
					String serviceid = chargesAccountProcessingDAO.getLedgrServiceIds(locationname);
					String ledgerid = chargesAccountProcessingDAO.getledgerIDNew(serviceid, paymode, "0","2",0);
		
					double lbal = chargesAccountProcessingDAO.getLedgerBalance(ledgerid);
					lbal = lbal + totalbal;
					String credit = "" + totalbal + "";
					String ldebit = "0";
					String product = "xxxxx";
					String partyid = appointment.getClientId();
					String otherclientid =appointment.getPclientid();
					  
					String lcommencing = DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone());
					int saveledger = chargesAccountProcessingDAO.saveLedger(partyid, product, ldebit, credit, lbal,
								ledgerid, lcommencing, "0", 0,otherclientid,"0","0","0",""+paymentid+"",0,0,appointment.getLocation());
						
					//second effect
					lbal = 0;
					credit = "0";
					ldebit = "" + totalbal + "";
					product = "xxxxx";
					partyid = appointment.getClientId();
					otherclientid =appointment.getPclientid();
					lcommencing = DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone());
					saveledger = chargesAccountProcessingDAO.saveLedger(partyid, product, ldebit, credit, lbal,
								ledgerid, lcommencing, "0", 0,otherclientid,"0","0","0",""+paymentid+"",0,0,appointment.getLocation());
			  	}
				
				
				 if (disc > 0) {
						String serviceid = chargesAccountProcessingDAO.getLedgrServiceIds("Discount");
						String ledgerid = chargesAccountProcessingDAO.getledgerIDNew(serviceid, "0", "0","2",0);

						double lbal = chargesAccountProcessingDAO.getLedgerBalance(ledgerid);
						lbal = lbal + disc;
						String credit = "" + disc + "";
						String ldebit = "0";
						String product = "" + billno + "";
						String partyid = priscription.getClientId();
						String otherclientid =priscription.getPclientid();
						String lcommencing = DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone());
						int saveledger = chargesAccountProcessingDAO.saveLedger(partyid, product, ldebit, credit, lbal,
								ledgerid, lcommencing, "0", 0,otherclientid,"0","0",""+billno+"","0",0,0,appointment.getLocation());
						
						//second effect
						 lbal = 0;
						 credit = "0";
						 ldebit = "" + disc + "";
						 product = "xxxxx";
						 partyid = priscription.getClientId();
						 otherclientid =priscription.getPclientid();
						 lcommencing = DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone());
						 saveledger = chargesAccountProcessingDAO.saveLedger(partyid, product, ldebit, credit, lbal,
								ledgerid, lcommencing, "0", 0,otherclientid,"0","0",""+billno+"","0",0,0,appointment.getLocation());
					}
				
				session.setAttribute("parentbillid", parentid);
				
				
			} catch (Exception e) {

				e.printStackTrace();
			}
			  
			finally {
				connection.close();
			}
			  
			 return "printbalreceipt"; 
			 
		  }
	  
	  public String getbalpayment() throws Exception {
		  if (!verifyLogin(request)) {

				return "login";
			}
			Connection connection=null;  
			try {
				connection=Connection_provider.getconnection();
				PharmacyDAO pharmacyDAO=new JDBCPharmacyDAO(connection);
				String flag= request.getParameter("flag");
				String clientid= request.getParameter("clientid");
				String payamt =request.getParameter("payamt");
				String date= DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone()).split(" ")[0];
				String paymode= request.getParameter("paymode");
				String cardno =request.getParameter("cardno");
				String paynotes= request.getParameter("paynotes");
				String discount= request.getParameter("discount");
				String discinper = request.getParameter("discinper");
				String paynote="";
				String fromdashboard=request.getParameter("fromdashboard");
				String clearbilltotalids = request.getParameter("clearbilltotalids");
				String balance="0";
				String pclientid="0";
			
				String location = (String) session.getAttribute("location"); 
				if(location==null){
					location="0";
				}
				if(paymode.equals("Card")){
					paynote= request.getParameter("card");
				}
				if(paymode.equals("Cheque")){
					paynote= request.getParameter("chequeno");
				}
				if(paymode.equals("NEFT") || paymode.equals("Online Payment")){
					paynote= request.getParameter("neftid");
				}
				
				Priscription priscription= new Priscription();
				priscription.setPaynote(paynote);
				priscription.setCardno(cardno);
				priscription.setNotes(paynotes);
				priscription.setPaymode(paymode);
				priscription.setDate(date);
				priscription.setUserid(loginInfo.getUserId());
				priscription.setLocation(location);
				priscription.setDisscount(discount);
				double disc=Double.parseDouble(discount);
				priscription.setDiscount(disc);
				String dateTime= DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone());
				priscription.setDateTime(dateTime);
				
				double nowbal=0;
				if(flag.equals("1")){  //new patient
			 		 balance= pharmacyDAO.getpreviousbalance(Integer.parseInt(clientid));
			 		 nowbal =Double.parseDouble(balance) - Double.parseDouble(payamt);
			 		 pclientid= clientid;
			 		 priscription.setPclientid(pclientid);
			 		 if(nowbal<0){
			 			  nowbal=0;
			 		 }
			 		int result= pharmacyDAO.updatebalance(Integer.parseInt(pclientid), String.valueOf(nowbal));
			 		clientid="0";	
			 		priscription.setClientId(clientid);
			 		int parentid=pharmacyDAO.updateBalanceByBillWise(pclientid,Double.parseDouble(payamt),0,priscription,paynote,location,loginInfo,clearbilltotalids);
			 		session.setAttribute("parentbillid", parentid);
			 	} else { //opd/ipd
			 		 balance= pharmacyDAO.getbalofpatient(clientid);
			 		 priscription.setClientId(clientid);
			 		 pclientid= "0";
			 		priscription.setPclientid(pclientid);
			 		 nowbal =Double.parseDouble(balance) - Double.parseDouble(payamt);
			 		  if(nowbal<0){
			 			  nowbal=0;
			 		  }
			 		  int result= pharmacyDAO.updatebalpatient(String.valueOf(nowbal), clientid);
			 		  int parentid=pharmacyDAO.updateBalanceByBillWise(clientid,Double.parseDouble(payamt),1,priscription,paynote,location,loginInfo,clearbilltotalids);
			 		 session.setAttribute("parentbillid", parentid);
			 	}
				
				/*double payAmount1= Double.parseDouble(payamt);
				int res= pharmacyDAO.recordPaymentMedicine("0", payAmount1, paymode, date, clientid, "", 0, paynotes, pclientid, loginInfo.getUserId());*/ 
				
			    if(fromdashboard!=null){
			    	
			    	 return "printbalreceipt";
			    }
				
			} catch (Exception e) {

				e.printStackTrace();
			}
			  
			finally {
				connection.close();
			}
			  
			return "printbalreceipt"; 
		  }
	  
	  
	  
	  
	  public String setInhousePatient() throws Exception{
		  if (!verifyLogin(request)) {

				return "login";
			}
			 Connection connection = null;
			 try {
				connection = Connection_provider.getconnection();
				PharmacyDAO pharmacyDAO = new JDBCPharmacyDAO(connection);
				String status = request.getParameter("status");

				if(status.equals("true")){
					status = "1";
				}
				else {
					status = "0";
				}
				Priscription priscription = pharmacyDAO.setinhousepatient(status);
				StringBuilder sb = new StringBuilder();
				if(priscription.getInhousepatient()=="0" || priscription.getInhousepatient().equals("0")){
					sb.append("<input type='checkbox' id='inhousepatient' name='inhousepatient' onclick='setinhousepatient()'  >");
				}
				else
				{
					sb.append("<input type='checkbox' id='inhousepatient' name='inhousepatient' onclick='setinhousepatient()'   checked='checked'>");
				}
				
				response.setContentType("text/html");
				response.setHeader("Cache-Control", "no-cache");
				response.getWriter().write(""+sb.toString()+"");
			} catch (Exception e) {
				e.printStackTrace();
			}finally{
				connection.close();
			}
			 return null;
		 }
	 
	 public String showAllPatientList() throws SQLException{
			if(!verifyLogin(request)){
				return "login";
			}
			
			Connection connection = null;
			Client client = new Client();
			ArrayList<Client> allPatientList = new ArrayList<Client>();
			try{
				connection = Connection_provider.getconnection();
				
				ClientDAO clientDAO = new JDBCClientDAO(connection);
				allPatientList = clientDAO.getAllPatient(loginInfo.getId(),"");
				PharmacyDAO pharmacyDAO = new JDBCPharmacyDAO(connection);
				
				//get pharmacy patient list
				ArrayList<Client>pharmacyClientList = pharmacyDAO.getPharmacyClientList();
				pharmacyClientList.addAll(allPatientList);
				BedDao bedDao = new JDBCBedDao(connection);
				//mrdForm.setAllPatientList(allPatientList);
				
				StringBuffer str = new StringBuffer();
				str.append("<table class='table table-bordered' > ");
				str.append("<thead>");
				str.append("<tr class='bg-info'>");
				str.append("<th>Patient ID</th> ");
				str.append("<th>Name</th> ");
				str.append("<th>Registered</th> ");
				str.append("<th>Mobile</th> ");
				str.append("<th>Address</th> ");
				str.append("<th class='hidden'>Email</th> ");
				str.append("<th class='hidden'>PostCode</th> ");
				str.append("<th class='hidden'>Dob</th> ");
				str.append("<th>LastModified</th> ");

				str.append("</tr>");
				str.append("</thead>");
				
				str.append("<tbody>");
				for(Client client1:pharmacyClientList){
					int id = client1.getId();
					String name = client1.getClientName();
					String ipdid = "0";
					if(client1.getIspharmacy().equals("0")){
						
					
					    ipdid = pharmacyDAO.getIpdIdFromClientID(client1.getId());
						String pract_name  = client1.getClientName();
						if(ipdid.equals("0")){
							pract_name = pharmacyDAO.getappointmentinfo(client1.getId());
						}else{
							Bed  bed = bedDao.getEditIpdData(ipdid);
							UserProfileDAO profileDAO = new JDBCUserProfileDAO(connection);
							pract_name = profileDAO.getName(bed.getPractitionerid());
							
						}
						
						 name = client1.getFirstName()+" "+client1.getMiddlename()+" "+client1.getLastName();
					}
					
					 	
					String color = "";
					if(!client1.getCasualtyid().equals("0")){
						color = "#f5a0b4";
					}
				str.append("<tr style='background-color:"+color+"' >");
				
				if(client1.getAbrivationid()!=null){
					if(client1.getAbrivationid().equals("0") || client1.getAbrivationid().equals("")){
						/*String clinicabrivation = clientDAO.getClinicAbrivation(loginInfo.getClinicid());*/
						/*str.append("<td>"+clinicabrivation+""+client1.getId()+"</td>");*/
						str.append("<td>"+client1.getId()+"</td>");
					}else{
						//str.append("<td>"+client1.getAbrivationid()+""+client1.getId()+"</td>");
						str.append("<td>"+client1.getAbrivationid()+"</td>");
					}
					
				}else{
					/*String clinicabrivation = clientDAO.getClinicAbrivation(loginInfo.getClinicid());*/
					//str.append("<td>"+clinicabrivation+""+client1.getId()+"</td>");
					str.append("<td>"+client1.getId()+"</td>");
				}
				
				
				String firstName= client1.getFirstName();
				
				int payee=0;
				if(client1.getWhopay()!=null){
					
					if(client1.getWhopay().equals("Client")){
						 payee=0;
					} else {
						payee=1;
					}
					
				}
				
			//	String data=client1.getAddress()+"~"+client1.getDob()+"~"+client1.getEmergencyContName()+"~"+client1.getEmergencyContNo()+"~"+client1.getRelation();
				String mobno = client1.getMobNo();
				String address = "-";
				if(client1.getAddress()!=null) {
					
					String[] address1 = client1.getAddress().split(" ");
					
					for (int i = 0; i < address1.length; i++) {
						address = address+"-"+address1[i];
						
					}
					
				}
				String balance =  client1.getBalance();
				
				if (mobno!=null) {
					if (mobno.equals("")) {
						mobno = "0";
					}
				}
				
				str.append("<td style='cursor: pointer;'; onclick= setInhousePatientInfo('"+client1.getId()+"','"+ipdid+"','"+client1.getIspharmacy()+"') >"+name+"</td>");
				/*if(client1.getOldclientId()==null){
					client1.setOldclientId("");
				}*/
				if(client1.getIspharmacy().equals("0")){
					str.append("<td>IPD / OPD</td>");
				}else{
					str.append("<td>Pharmacy</td>");
				}
				
				str.append("<td>"+client1.getMobNo()+"</td>");
				str.append("<td></td>");
				str.append("<td class='hidden'>"+client1.getEmail()+"</td>");
				str.append("<td class='hidden'>"+client1.getPostCode()+"</td>");
				str.append("<td class='hidden'>"+client1.getDob()+"</td>");
				if(client1.getLastModified()==null){
					client1.setLastModified("");
				}
				str.append("<td>"+client1.getLastModified()+"</td>");

				str.append("</tr>");
				}
				str.append("</tbody>");
				str.append("</table>");
				response.setContentType("text/html");
				response.setHeader("Cache-Control", "no-cache");
				
				response.getWriter().write(""+str.toString()+""); 
				
			}
			catch (Exception e) {
				e.printStackTrace();
			}
			finally{
				connection.close();
			}
			
			return null;
		}
	 public String searchParticularPatient() throws SQLException{
	 		if(!verifyLogin(request)){
				return "login";
			}
			String searchClient = request.getParameter("searchText");
			Connection connection = null;
			ArrayList<Client> allPatientList = new ArrayList<Client>();
			try{
				connection = Connection_provider.getconnection();
				PharmacyDAO pharmacyDAO = new JDBCPharmacyDAO(connection);
				BedDao bedDao = new JDBCBedDao(connection);
				ClientDAO clientDAO = new JDBCClientDAO(connection);
				allPatientList = clientDAO.getClient(searchClient,loginInfo.getId(),"");
				StringBuffer str = new StringBuffer();
								
				//get pharmacy patient list
				ArrayList<Client> pharmacySearchClientList = pharmacyDAO.getPharmacySearchClientList(searchClient);
				allPatientList.addAll(pharmacySearchClientList);
				
				//mrdForm.setAllPatientList(allPatientList);
				
				
				str.append("<table class='table table-bordered' > ");
				str.append("<thead>");
				str.append("<tr class='bg-info'>");
				str.append("<th>PATIENT ID</th> ");
				str.append("<th>Name</th> ");
				str.append("<th>REGISTERED</th> ");
				str.append("<th>Mobile</th> ");
				str.append("<th>Address</th> ");
				str.append("<th class='hidden'>Email</th> ");
				str.append("<th class='hidden'>PostCode</th> ");
				str.append("<th class='hidden'>Dob</th> ");
				str.append("<th>LastModified</th> ");

				str.append("</tr>");
				str.append("</thead>");
				
				str.append("<tbody>");
				for(Client client1:allPatientList){
					int id = client1.getId();
					String name = client1.getClientName();
					String ipdid = "0";
					if(client1.getIspharmacy().equals("0")){
						
					
					    ipdid = pharmacyDAO.getIpdIdFromClientID(client1.getId());
						String pract_name  = client1.getClientName();
						if(ipdid.equals("0")){
							pract_name = pharmacyDAO.getappointmentinfo(client1.getId());
						}else{
							Bed  bed = bedDao.getEditIpdData(ipdid);
							UserProfileDAO profileDAO = new JDBCUserProfileDAO(connection);
							pract_name = profileDAO.getName(bed.getPractitionerid());
							
						}
						
						 name = client1.getFirstName()+" "+client1.getMiddlename()+" "+client1.getLastName();
					}
					
					 	
					String color = "";
					/*if(!client1.getCasualtyid().equals("0")){
						color = "#f5a0b4";
					}*/
				str.append("<tr style='background-color:"+color+"' >");
				if(client1.getAbrivationid()!=null){
					if(client1.getAbrivationid().equals("0") || client1.getAbrivationid().equals("")){
						/*String clinicabrivation = clientDAO.getClinicAbrivation(loginInfo.getClinicid());*/
						/*str.append("<td>"+clinicabrivation+""+client1.getId()+"</td>");*/
						str.append("<td>"+client1.getId()+"</td>");
					}else{
						/*str.append("<td>"+client1.getAbrivationid()+""+client1.getId()+"</td>");*/
						str.append("<td>"+client1.getAbrivationid()+"</td>");
					}
					
				}else{
					/*String clinicabrivation = clientDAO.getClinicAbrivation(loginInfo.getClinicid());*/
					/*str.append("<td>"+clinicabrivation+""+client1.getId()+"</td>");*/
					str.append("<td>"+client1.getId()+"</td>");
				}
				//str.append("<td>0000"+client1.getId()+"</td>");
				String firstName= client1.getFirstName();
				
				int payee=0;
				if(client1.getWhopay()!=null){
					
					if(client1.getWhopay().equals("Client")){
						 payee=0;
					} else {
						payee=1;
					}
					
				}
				
			//	String data=client1.getAddress()+"~"+client1.getDob()+"~"+client1.getEmergencyContName()+"~"+client1.getEmergencyContNo()+"~"+client1.getRelation();
				String mobno = client1.getMobNo();
				String address = "-";
				if(client1.getAddress()!=null) {
					
					String[] address1 = client1.getAddress().split(" ");
					
					for (int i = 0; i < address1.length; i++) {
						address = address+"-"+address1[i];
						
					}
					
				}
				String balance =  client1.getBalance();
				
				if (mobno!=null) {
					if (mobno.equals("")) {
						mobno = "0";
					}
				}
				
				str.append("<td style='cursor: pointer;'; onclick= setInhousePatientInfo('"+client1.getId()+"','"+ipdid+"','"+client1.getIspharmacy()+"') >"+name+"</td>");
				/*if(client1.getOldclientId()==null){
					client1.setOldclientId("");
				}*/
				if(client1.getIspharmacy().equals("0")){
					str.append("<td>IPD / OPD</td>");
				}else{
					str.append("<td>Pharmacy</td>");
				}
				
				str.append("<td>"+client1.getMobNo()+"</td>");
				str.append("<td></td>");
				str.append("<td class='hidden'>"+client1.getEmail()+"</td>");
				str.append("<td class='hidden'>"+client1.getPostCode()+"</td>");
				str.append("<td class='hidden'>"+client1.getDob()+"</td>");
				if(client1.getLastModified()==null){
					client1.setLastModified("");
				}
				str.append("<td>"+client1.getLastModified()+"</td>");

				str.append("</tr>");
				}
				str.append("</tbody>");
				str.append("</table>");
				response.setContentType("text/html");
				response.setHeader("Cache-Control", "no-cache");
				
				response.getWriter().write(""+str.toString()+""); 
				
			}
			catch (Exception e) {
				e.printStackTrace();
			}
			finally{
				connection.close();
			}
			return null;
		}
	 
	
	 
	 public String updatepatient() throws Exception{
		 if (!verifyLogin(request)) {

				return "login";
			}
		 String val = request.getParameter("val");
		 String column = request.getParameter("column");
		 String clientid = request.getParameter("clientid");
		 
		 Connection connection = null;
		 try{
				connection = Connection_provider.getconnection();
				PharmacyDAO pharmacyDAO = new JDBCPharmacyDAO(connection);
				
				int upd = pharmacyDAO.updatePharmaClient(val,column,clientid);
		 }catch (Exception e) {
			// TODO: handle exception
		}finally{
			
			connection.close();
		}
		 return null;
	 }
	 
	 public String addnewpatient() throws Exception{
		 if (!verifyLogin(request)) {

				return "login";
			}
		 String adfullname = request.getParameter("adfullname");
		 String admobile = request.getParameter("admobile");
		 String adaddress = request.getParameter("adaddress");
		 String addoctor = request.getParameter("addoctor");
		 String gstin=request.getParameter("gstin");
		 String dlno=request.getParameter("dlno");
		 
		 Connection connection = null;
		 try{
				connection = Connection_provider.getconnection();
				PharmacyDAO pharmacyDAO = new JDBCPharmacyDAO(connection);
				UserProfileDAO userProfileDAO = new JDBCUserProfileDAO(connection);
				String fullname = userProfileDAO.getFullName(addoctor);
				String date = DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone()).split(" ")[0];
				
				//save abrivation seq no
				String cdate = DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone()).split(" ")[0];
				boolean checkifseq = pharmacyDAO.checkifSequenceExist(cdate);
				String abrivationid = "";
				String clinicabrivation = "PH";
				String tempd[] = cdate.split("-");
				String y = tempd[0];
				String m = tempd[1];
				String d = tempd[2];
				String newseq="";
				if(checkifseq){
					
					int seqno = pharmacyDAO.getSqeunceNumber(cdate);
					seqno++;
					int r = pharmacyDAO.InserCdateSeq(cdate,seqno);
					//SNH170609001
					int yr = Integer.parseInt(y)%1000;
					if(String.valueOf(seqno).length()==1)
					{
						 newseq="00"+seqno;
					}else if(String.valueOf(seqno).length()==2){
						 newseq="0"+seqno;
					}else{
						newseq=""+seqno;
					}
					abrivationid = clinicabrivation + yr + m +d + "" +newseq ;
				}else{
					int seqno = 1;
					int r = pharmacyDAO.InserCdateSeq(cdate,seqno);
					int yr = Integer.parseInt(y)%1000;
					if(String.valueOf(seqno).length()==1)
					{
						 newseq="00"+seqno;
					}else if(String.valueOf(seqno).length()==2){
						 newseq="0"+seqno;
					}else{
						newseq=""+seqno;
					}
					abrivationid = clinicabrivation + yr + m +d + "" + newseq;
				}
				int r = pharmacyDAO.saveNewPharmaPatient(date,adfullname,admobile,adaddress,addoctor,fullname,abrivationid,gstin,dlno);
			 
			 
		 }catch (Exception e) {
			e.printStackTrace();
		}finally{
			
			connection.close();
		}
		 
		 return null;
	 }
	 
	public String getAllPatientInformation() throws Exception{
		 if (!verifyLogin(request)) {

				return "login";
			}
		String clientid = request.getParameter("clientid");
		String ispharmacy = request.getParameter("ispharmacy");
		//String ipdid = request.getParameter("ipdid");
		Connection connection = null;
		try{
			String isfromsaledash = request.getParameter("isfromsaledash");
			String isfrompharmacymedreturn = request.getParameter("isfrompharmacymedreturn");
			connection = Connection_provider.getconnection();
			PharmacyDAO pharmacyDAO = new JDBCPharmacyDAO(connection);
			BedDao bedDao = new JDBCBedDao(connection);
			ClientDAO clientDAO = new JDBCClientDAO(connection);
			StringBuilder  str = new StringBuilder();
			
			//int res1 = pharmacyDAO.deleteTempPharmacyData(loginInfo.getLoginsessionid());
			
			//delete from sessionid 
			//int res2 = pharmacyDAO.deleteFromSessionLog(loginInfo.getLoginsessionid()); 
		/*	if(isfromsaledash.equals("1")){
				//its for double entry prevent
				int res = pharmacyDAO.deleteTempPharmacySession(loginInfo.getLoginsessionid());
				boolean checkpatientintemp = pharmacyDAO.checkPatientInTempSaleData(loginInfo.getLoginsessionid(),clientid,Integer.parseInt(ispharmacy));
				if(!checkpatientintemp){
					int res1 = pharmacyDAO.deleteTempPharmacySaleData(loginInfo.getLoginsessionid(),clientid,Integer.parseInt(ispharmacy));
					int resss = pharmacyDAO.deleteTempChargePharmacySessionLog(loginInfo.getLoginsessionid(),clientid,Integer.parseInt(ispharmacy));
				}
			}else{
				//its for double entry prevent
				int res = pharmacyDAO.deleteTempPharmacySession(loginInfo.getLoginsessionid());
			}*/
			int res = pharmacyDAO.deleteTempPharmacySession(loginInfo.getLoginsessionid());		
			String ipdid = pharmacyDAO.getIpdIdFromClientID(Integer.parseInt(clientid));
			String pract_name ="";
			String practid="0";
			if(ipdid.equals("0")){
				pract_name = pharmacyDAO.getappointmentinfo(Integer.parseInt(clientid));
				practid = pharmacyDAO.getappointmentinfopractid(Integer.parseInt(clientid));
			}else{
				Bed  bed = bedDao.getEditIpdData(ipdid);
				UserProfileDAO profileDAO = new JDBCUserProfileDAO(connection);
				pract_name = profileDAO.getName(bed.getPractitionerid());
				practid = bed.getPractitionerid();
			}
			Client client = clientDAO.getClientDetails(clientid);
			
			String clientname = client.getTitle()+" "+client.getFirstName()+" "+client.getMiddlename()+" "+client.getLastName();
			String payee="";
			double creditbalance = 0;
			double bydefaultdiscount =0;
			if(ispharmacy.equals("1")){
				bydefaultdiscount = loginInfo.getPharpatientdiscount();
				//client = pharmacyDAO.getPharmacyClientInfo(clientid);
				Product product = pharmacyDAO.getpatientinfo(clientid);
				String data = product.getId()+"~"+product.getFullname()+"~"+product.getAddress()+"~"+product.getReference()+"~"+product.getMobile()+"~"+product.getBalance();
				practid = product.getPhar_drid();
				pract_name = product.getReference();
				clientname = product.getFullname();
				client.setAddress(product.getAddress());
				client.setMobNo(product.getMobile());
				client.setBalance(product.getBalance());
				client.setAbrivationid("-");
				payee="Self";		
				creditbalance= pharmacyDAO.getPharClientBalance(ispharmacy,clientid);
			} else {
				Bed bed = pharmacyDAO.getIPDActiveDataFromClientid(""+clientid);
		    	if(Integer.parseInt(DateTimeUtils.numberCheck(bed.getIpdid()))>0){
		    		bydefaultdiscount = loginInfo.getIpddiscount();
		    	}else{
		    		bydefaultdiscount = loginInfo.getOpddiscount();
		    	}

				 if(client.getWhopay().equalsIgnoreCase("Third Party")){
					 payee = client.getTpDetails().getCompanyName();
				 } else {
					 payee="Self";
				 }
				 creditbalance= pharmacyDAO.getPharClientBalance("0",clientid);
			}
			
			if(DateTimeUtils.isNull(isfrompharmacymedreturn).equals("1")){
				if(ispharmacy.equals("1")){
					//pharmacy patient
					Priscription priscription = pharmacyDAO.getLastBillDrDetails(1,clientid);
					if(!DateTimeUtils.numberCheck(priscription.getPractitionerid()).equals("0")){
						practid = priscription.getPractitionerid();
						pract_name = priscription.getPractitionername();
					}
				}else{
					//manas patient
					Priscription priscription = pharmacyDAO.getLastBillDrDetails(0,clientid);
					if(!DateTimeUtils.numberCheck(priscription.getPractitionerid()).equals("0")){
						practid = priscription.getPractitionerid();
						pract_name = priscription.getPractitionername();
					}
				}
			}
			
			str.append(""+clientname+"");
			str.append("~");
			
			str.append(""+client.getMobNo()+"");
			str.append("~");
			
			str.append(""+client.getAddress()+"");
			str.append("~");
			
			str.append(""+pract_name+"");
			str.append("~");
			
			/*str.append(""+client.getBalance()+"");*/
			str.append(""+creditbalance+"");
			str.append("~");
			
			str.append(""+payee+"");
			str.append("~");
			
			str.append(""+client.getAbrivationid()+"");
			str.append("~");
			str.append(""+creditbalance+"");
			str.append("~");
			str.append(""+bydefaultdiscount+"");
			
			str.append("~");
			str.append(""+practid+"");
			response.setContentType("text/html");
			response.setHeader("Cache-Control", "no-cache");
			
			response.getWriter().write(""+str.toString()+""); 
		}catch (Exception e) {
			e.printStackTrace();
		}finally{
			connection.close();
		}
		return null;
	}
	
	
	public String opdipdsaleprisc() throws Exception{
		 if (!verifyLogin(request)) {

				return "login";
			}
		Connection connection=null;
		try {
			connection=Connection_provider.getconnection();
			InventoryProductDAO inventoryProductDAO=new JDBCInventoryProductDAO(connection);
			String location =(String) session.getAttribute("location");
			if(location==null){
				location="0";
			}
			ArrayList<Product> inventoryPriscList= inventoryProductDAO.geProductList("2",location);
			emrForm.setInventoryPriscList(inventoryPriscList);
			
			ArrayList<Product> pharmapatientlist = inventoryProductDAO.getpatientlist();
			emrForm.setPharmapatientlist(pharmapatientlist);
			emrForm.setSelectedclientid("0");
			String dateTime=DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone()).split(" ")[0];
			emrForm.setDateTime(DateTimeUtils.getCommencingDate1(dateTime));
			PharmacyDAO pharmacyDAO = new JDBCPharmacyDAO(connection);
			String userid = loginInfo.getUserId();
			Priscription priscription = pharmacyDAO.getPharacyUsrLInfo(userid);
			
			String inhouse = priscription.getInhousepatient();
			if (priscription.getInhousepatient()!=null)
				if (priscription.getInhousepatient().equals("0"))
					loginInfo.setInhousepatient(false);
				else
					loginInfo.setInhousepatient(true);
			
		} catch (Exception e) {
		
			e.printStackTrace();
		}
		finally {
			connection.close();
		}
		return "opdipdsaleprisc";
	}

	public String opdipdsale()throws Exception{
		 if (!verifyLogin(request)) {

				return "login";
			}
		Connection connection=null;
		try {
			connection=Connection_provider.getconnection();
			PharmacyDAO pharmacyDAO=new JDBCPharmacyDAO(connection);
			InventoryProductDAO productDAO=new JDBCInventoryProductDAO(connection);
			String notes="";
			String fullname = emrForm.getFullname();
			String agegender = emrForm.getAgeandgender();
			String paymode = emrForm.getPaymode();
			String address = emrForm.getWardname();
			String practitioner = emrForm.getPractitionerName();
			String dateTime = emrForm.getDateTime();
			String mobile = emrForm.getMobno();
			String extpid = request.getParameter("extpid");
			String previous_balance = emrForm.getPrevious_balance();
			String balance = emrForm.getBalance();
			String cgst= emrForm.getCgst();
			String sgst= emrForm.getSgst();
			String ipdopd = request.getParameter("ipdopd");
			String tpnameid = request.getParameter("tpnameid");
			if(balance==null){
				balance="0";
			}
			if(balance.equals("")){
				balance="0";
			}
			String loc= (String) session.getAttribute("location");
			if(loc==null){
				loc="0";
			}
			
			Client client=new Client();
			client.setClientName(fullname);
			if(agegender!=null){
				 if(!agegender.equals("")){
					 try {
						 String data[]=agegender.split("/");
						 client.setGender(data[0]);
						 client.setAge(Integer.parseInt(data[1]));
					 } catch (Exception e) {
						 e.printStackTrace();
					 }
				 }
			}
			
			if(paymode!=null){
				 
				if(paymode.equals("Card")){
					
					notes= request.getParameter("card");
				}
			}
			
			
			client.setAddress(address);
			client.setReference(practitioner);
			client.setLastModified(dateTime);
			client.setFirstName(fullname);
			client.setMobNo(mobile);
			client.setBalance(balance);
			int pharmacyclientid=0;
			
			
			/*if(extpid=="" || extpid.equals("")){
				pharmacyclientid=pharmacyDAO.addNewPharmacyPatient(client);
				int result = pharmacyDAO.updatebalance(pharmacyclientid,balance);
			}
			else
			{
				   pharmacyclientid= Integer.parseInt(extpid);
			         int result = pharmacyDAO.updatebalance(pharmacyclientid,balance);
				
			}*/
			int res = pharmacyDAO.updatebalpatient(balance,ipdopd);
			
			String vat=emrForm.getVat();
			    
			    if(emrForm.getDiscount()!=null){
			    	 
			    	if(emrForm.getDiscount().equals("")){
			    		
			    		emrForm.setDiscount("0");
			    	}
			    }
			    else {
			    	emrForm.setDiscount("0");
			    }
			    
			    double discount=Double.parseDouble(emrForm.getDiscount());
			    String total=emrForm.getTotal();
			    
			    
			    String clientname=fullname;
			    CompleteAppointment appointment=new CompleteAppointment();
			    appointment.setClientId(ipdopd);
			    appointment.setPayBuy("Client");
			    appointment.setCharges(""+total+"");
			    appointment.setChargeType("");
			    appointment.setVat(Double.parseDouble(vat));
			    appointment.setDiscount(discount);
			    appointment.setTotal(Double.parseDouble(total));
			    appointment.setNotes(notes);
			    appointment.setPclientid("0");
			    appointment.setPriscid(0);
			    String date=DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone()).split(" ")[0];
			    appointment.setInvoiceDate(date);
			    appointment.setBalance(balance);
			    appointment.setLocation(loc);
			    appointment.setCgst(cgst);
			    appointment.setSgst(sgst);
			    
			    int billno=pharmacyDAO.addMedicineBill(appointment);
			    
			    //update in new patient recors
			    //int result=pharmacyDAO.updateBillandStatus(pharmacyclientid,billno,"1");
			  
			    
			    Priscription priscription=new Priscription();
			    
			    for(Priscription prisc:emrForm.getMedicines()){
			    	
			    	if(prisc==null){
			    		continue;
			    	}
			    	if(prisc.getId()==0){
			    		continue;
			    	}
			    	
			    	priscription.setProduct_id(prisc.getProduct_id());
			    	priscription.setMdicinenameid(prisc.getMdicinenameid());
			    	Product product=productDAO.getProduct(prisc.getProduct_id());
			    	priscription.setSaleqty(prisc.getSaleqty());
			    	priscription.setPclientid(""+pharmacyclientid+"");
			    	priscription.setReqqty(prisc.getReqqty());
			    	priscription.setMrp(prisc.getSale_price());
			    	priscription.setFullname(practitioner);
			    	priscription.setClientname(clientname);
			    	priscription.setWhopay("0");
			    	priscription.setDate(date);
			    
			    	int result = 0;
			    	if((product.getStock()!="0")){
			    		result=pharmacyDAO.addMedicineCharges(priscription,billno);
			    		result=productDAO.updateMedicineQty(priscription.getSaleqty(),priscription.getProduct_id(),0);
			    	}
			    	
			    	//update inventory
			    	//result=productDAO.updateMedicineQty(priscription.getSaleqty(),priscription.getMdicinenameid(),0);
			    	
			     }
			    
			   double tot=Double.parseDouble(total);
			  //record payment
			 //  19 Sep 2018 generate pharmacy payment Seq No according location
			    int paymentseqno = pharmacyDAO.getPharmacyPaymentSeqNo(loc);
				paymentseqno = paymentseqno+1;
			   int result=pharmacyDAO.recordPaymentMedicine(String.valueOf(billno),tot,paymode,date,appointment.getClientId(),"0",discount,notes,appointment.getPclientid(),loginInfo.getUserId(),loc,0,paymentseqno);
			    
			    
			  CompleteAppointment completeAppointment=pharmacyDAO.getBillDetails(billno);
			  ArrayList<Priscription> medicineChargeList=pharmacyDAO.getMedicineChargesList(billno,loginInfo);
			  double subtotal=0;
			  for(Priscription p:medicineChargeList){
				  
				  subtotal=subtotal+(Double.parseDouble(p.getMrp())*p.getSaleqty());
			  }
			  
			  if(previous_balance==null){
				  previous_balance="0";
			  }
			  
			  emrForm.setPrevious_balance(previous_balance);
			  emrForm.setSubtotal(DateTimeUtils.changeFormat(subtotal));
			  emrForm.setMedicineChargeList(medicineChargeList);
			  emrForm.setClientId(completeAppointment.getClientId());
			  emrForm.setTotal(DateTimeUtils.changeFormat(completeAppointment.getTotal()));
			  String disc=String.valueOf(completeAppointment.getDiscount());
			  emrForm.setDiscount(DateTimeUtils.changeStringFormat(disc));
			  emrForm.setVat(String.valueOf(vat));
			  emrForm.setCgst(completeAppointment.getCgst());
			  emrForm.setSgst(completeAppointment.getSgst());
			  emrForm.setBillno(billno);
			  
			  String lo= pharmacyDAO.getLocationName(loc);
			  emrForm.setLocation(lo);
			
			
			    Clinic clinic = new Clinic();
				ClinicDAO clinicDAO  = new JDBCClinicDAO(connection);
				clinic = clinicDAO.getCliniclistDetails(loginInfo.getClinicid());
				
				Location location = clinicDAO.getRegisterdLication();
				clinic.setLocationname(location.getAddress());
				
				/*String curdatetime = DateTimeUtils.getPriscDatetime(loginInfo.getTimeZone());
				clinic.setCurDateTime(curdatetime);*/
				clinic.setCurDateTime(DateTimeUtils.getCommencingDate1(date));
				session.setAttribute("billno", billno);
				session.setAttribute("selectedid", 0);
				
				session.setAttribute("clinicinfo", clinic);
				session.setAttribute("clientinfo", client);
				
			
				String paymode1=pharmacyDAO.getBillPaymode(billno);
				String cardBill="";
				if(paymode!=null){
					
					if(paymode1.equals("Card")){
						
						cardBill = pharmacyDAO.getCardBillInvoice(billno);
					}
				}
				emrForm.setCardBill(cardBill);
				double refund=pharmacyDAO.getRefundAmt(billno);
				 CompleteAppointment completeAppointment1=pharmacyDAO.getBillDetails(billno);
				 Double balance1 =Double.parseDouble(completeAppointment1.getBalance());
				  ArrayList<Priscription> medicineChargeList1=pharmacyDAO.getMedicineChargesList(billno,loginInfo);
				  double subtotal1=0;
				  for(Priscription p:medicineChargeList1){

					  subtotal1=subtotal1+(Double.parseDouble(p.getMrp())*p.getSaleqty());
					  
				  }
				  String str=DateTimeUtils.changeFormat(subtotal1);
				  emrForm.setSubtotal(str);
				  emrForm.setCommencing(DateTimeUtils.getCommencingDate1(completeAppointment.getInvoiceDate()));
				  emrForm.setMedicineChargeList(medicineChargeList);
				  emrForm.setClientId(completeAppointment.getClientId());
				  String t=DateTimeUtils.changeFormat(completeAppointment.getTotal());
				  emrForm.setTotal(t);
				  emrForm.setCgst(completeAppointment.getCgst());
				  emrForm.setSgst(completeAppointment.getSgst());
				  
				  double gst= Double.parseDouble(completeAppointment.getCgst());
				  
				  emrForm.setGst(DateTimeUtils.changeFormat(gst*2));
				  emrForm.setDiscount(DateTimeUtils.changeStringFormat(String.valueOf(completeAppointment.getDiscount())));
				  emrForm.setVat(String.valueOf(completeAppointment.getVat()));
				  emrForm.setBillno(billno);
				  //emrForm.setSelectedid(selectedid);
				  emrForm.setPaymode(paymode1);
				  emrForm.setRefundamt(DateTimeUtils.changeFormat(refund));
				  emrForm.setBalance(DateTimeUtils.changeFormat(balance1));
				  
				  // new Header and Footer
				  UserProfileDAO userProfileDAO = new JDBCUserProfileDAO(connection);
				  UserProfile userProfile= userProfileDAO.getPharmacyUserDetails(loginInfo.getClinicid());
				  emrForm.setClinicName(userProfile.getClinicname());
				  emrForm.setClinicaddress(userProfile.getAddress());
				  emrForm.setLandLine(userProfile.getPhone());
				  emrForm.setFullname(userProfile.getFullname());
				  emrForm.setEmail(userProfile.getEmail());
				  emrForm.setPlace(userProfile.getCity());
				  emrForm.setWebsiteUrl(userProfile.getWebsite());
				  emrForm.setInstruction1(userProfile.getInstruction1());
				  emrForm.setInstruction2(userProfile.getInstruction2());
				  emrForm.setInstruction3(userProfile.getInstruction3());
				  emrForm.setInstruction4(userProfile.getInstruction4());
				  emrForm.setDlno(userProfile.getDlno());
				  emrForm.setTinno(userProfile.getTinno());
				  emrForm.setPagelimit(Integer.parseInt(userProfile.getPagelimit()));
				 session.setAttribute("pagelimit", userProfile.getPagelimit());
				  String ipdid = pharmacyDAO.getIpdIdFromClientID(Integer.parseInt(ipdopd));
					String pract_name ="";
					String ipdoropd = "0";
					if(ipdid.equals("0")){
						ipdoropd ="0";
					}else{
						BedDao bedDao = new JDBCBedDao(connection);
						IpdDAO ipdDAO = new JDBCIpdDAO(connection);
						Bed  bed = bedDao.getEditIpdData(ipdid);
						String wardname = ipdDAO.getIpdWardName(bed.getBedid());
						String bedname = ipdDAO.getIpdBedName(bed.getWardid());
						ipdoropd = ipdid;
						String wardbed = wardname +"/"+ bedname;
						emrForm.setWardbed(wardbed);
					}
					emrForm.setIpdoropd(ipdoropd);
					ThirdPartyDAO thirdPartyDAO = new JDBCThirdPartyDAO(connection);
					String thirdPartyCompanyName="0";
					if(!tpnameid.equals("0")){
						ThirdParty thirdParty =thirdPartyDAO.getThirdPartyDetails(tpnameid);
						thirdPartyCompanyName = thirdParty.getCompanyName();
					}else{
						thirdPartyCompanyName ="0";
					}
					emrForm.setThirdPartyCompanyName(thirdPartyCompanyName);
					
					 
					  if(userProfile.getPrintType()!=null){
						  
						    if(userProfile.getPrintType().equals("1")){
						    	 if(userProfile.isIsdotmatrix()){
						    		 return "bigBill";
						    	 }else{
						    		 return "bigbilllazer"; 
						    	 }
						    }
						  
					  } 
					
		} catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			
			connection.close();
		}
		/*return "creditbill";*/
		return "bigbilllazer";
 }
	  
	 public String validatepharmacyuser() throws Exception{
		 if (!verifyLogin(request)) {

				return "login";
			}
		 Connection connection = null;
		 try {
			connection = Connection_provider.getconnection();
			Connection connection1=DriverManager.getConnection(""+Constants.DB_HOST+":3306/admin",""+Constants.DB_USER+"",""+Constants.DB_PWD+"");
			PharmacyDAO pharmacyDAO = new JDBCPharmacyDAO(connection1);
			
			String userid = request.getParameter("userid");
		
			int result = pharmacyDAO.validatepharmacyuser(userid);
			
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
	 
	 public String estimate() throws Exception  {
		 if (!verifyLogin(request)) {

				return "login";
			}
		 Connection connection=null;
			try {
				connection=Connection_provider.getconnection();
				PharmacyDAO pharmacyDAO=new JDBCPharmacyDAO(connection);
				InventoryProductDAO productDAO=new JDBCInventoryProductDAO(connection);
				String notes="";
				String fullname=emrForm.getFullname();
				String agegender=emrForm.getAgeandgender();
				String paymode=emrForm.getPaymode();
				String address=emrForm.getWardname();
				String practitioner=emrForm.getPractitionerName();
				String dateTime=emrForm.getDateTime();
				String mobile= emrForm.getMobno();
				String extpid = request.getParameter("extpid");
				String tpid= request.getParameter("tpid");
				String previous_balance = emrForm.getPrevious_balance();
				String payamt= emrForm.getPayamt();
				String balance = emrForm.getBalance();
				String cgst= emrForm.getCgst();
				String sgst= emrForm.getSgst();
				String date= DateTimeUtils.getCommencingDate1(dateTime); 
				notes =emrForm.getNotes();
				if(balance==null){
					balance="0";
				}
				if(tpid==null){
					tpid="0";
				}
				if(tpid.equals("")){
					tpid="0";
				}
				if(balance.equals("")){
					balance="0";
				}
				
				String paynote="";
				String loc= (String) session.getAttribute("location");
				
				Client client=new Client();
				client.setClientName(fullname);
				if(agegender!=null){
					 if(!agegender.equals("")){
						 try {
							 String data[]=agegender.split("/");
							 client.setGender(data[0]);
							 client.setAge(Integer.parseInt(data[1]));
						 } catch (Exception e) {
							 e.printStackTrace();
						 }
					 }
				}
				
				if(paymode!=null){
					 
					if(paymode.equals("Card")){
						
						paynote= request.getParameter("card");
					}
					if(paymode.equals("Cheque")){
						paynote= request.getParameter("chequeno");
					}
					if(paymode.equals("NEFT")){
						paynote= request.getParameter("neftid");
					}
					
				}
				
				
				client.setAddress(address);
				client.setReference(practitioner);
				client.setLastModified(dateTime);
				client.setFirstName(fullname);
				client.setMobNo(mobile);
				client.setBalance(balance);
				int pharmacyclientid= Integer.parseInt(emrForm.getHdnphclientid());
				
				if(extpid=="" || extpid.equals("")){
					
					ClientDAO clientDAO=new JDBCClientDAO(connection);
					//save abrivation seq no
					String cdate = DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone()).split(" ")[0];
					boolean checkifseq = clientDAO.checkifSequenceExist(cdate);
					String abrivationid = "";
					String clinicabrivation = clientDAO.getClinicAbrivation(loginInfo.getClinicid());
					String tempd[] = cdate.split("-");
					String y = tempd[0];
					String m = tempd[1];
					String d = tempd[2];
					if(checkifseq){
						int seqno = clientDAO.getSqeunceNumber(cdate);
						seqno++;
						int r = clientDAO.InserCdateSeq(cdate,seqno);
						//SNH170609001
						int yr = Integer.parseInt(y)%1000;
						abrivationid = clinicabrivation + yr + m +d + "00" + seqno;
					}else{
						
						int seqno = 1;
						int r = clientDAO.InserCdateSeq(cdate,seqno);
						//String seqno = clientDAO.getSqeunceNumber(cdate);
						int yr = Integer.parseInt(y)%1000;
						abrivationid = clinicabrivation + yr + m +d + "00" + seqno;
					}
					client.setAbrivationid(abrivationid);
					//pharmacyclientid=pharmacyDAO.addNewPharmacyPatient(client);
					int result = pharmacyDAO.updatebalance(pharmacyclientid,balance);
				}
				else
				{
					   pharmacyclientid= Integer.parseInt(extpid);
					   String bal= pharmacyDAO.getpreviousbalance(pharmacyclientid);
					   double temp =Double.parseDouble(bal) + Double.parseDouble(balance);
				       int result = pharmacyDAO.updatebalance(pharmacyclientid,String.valueOf(temp));
				       int res = pharmacyDAO.updateExPatient(pharmacyclientid,client);
					
				}
				String vat=emrForm.getVat();
				    
				    if(emrForm.getDiscount()!=null){
				    	 
				    	if(emrForm.getDiscount().equals("")){
				    		
				    		emrForm.setDiscount("0");
				    	}
				    }
				    else {
				    	emrForm.setDiscount("0");
				    }
				    
				    double discount=Double.parseDouble(emrForm.getDiscount());
				    String total=emrForm.getTotal();
				    
				    
				    String clientname=fullname;
				    CompleteAppointment appointment=new CompleteAppointment();
				    appointment.setClientId("0");
				    appointment.setPclientid(""+pharmacyclientid+"");
				    if(emrForm.getHdnispharmacy().equals("0")){
				    	appointment.setClientId(Integer.toString(pharmacyclientid));
				    	appointment.setPclientid("0");
				    	
				    }
				    
				    appointment.setPayBuy("Client");
				    appointment.setCharges(""+total+"");
				    appointment.setChargeType("");
				    appointment.setVat(Double.parseDouble(vat));
				    appointment.setDiscount(discount);
				    appointment.setTotal(Double.parseDouble(total));
				    appointment.setNotes(notes);
				    
				    appointment.setPriscid(0);
				    String time=DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone()).split(" ")[1];
				    appointment.setInvoiceDate(date);
				    appointment.setInvoiceTime(time);
				    appointment.setBalance(balance);
				    appointment.setLocation(loc);
				    appointment.setCgst(cgst);
				    appointment.setTpid(tpid);
				    appointment.setSgst(sgst);
				    appointment.setPayamt(payamt);
				    appointment.setUserid(loginInfo.getUserId());
				    
				    int billno=pharmacyDAO.addEstimateMedicineBill(appointment);
				    int result=0;
				    
				    Priscription priscription=new Priscription();
				    
				    for(Priscription prisc:emrForm.getMedicines()){
				    	
				    	if(prisc==null){
				    		continue;
				    	}
				    	if(prisc.getId()==0){
				    		continue;
				    	}
				    	
				    	priscription.setProduct_id(prisc.getProduct_id());
				    	priscription.setMdicinenameid(prisc.getMdicinenameid());
				    	Product product=productDAO.getProduct(prisc.getProduct_id());
				    	priscription.setSaleqty(prisc.getSaleqty());
				    	priscription.setPclientid(""+pharmacyclientid+"");
				    	priscription.setReqqty(prisc.getReqqty());
				    	priscription.setMrp(prisc.getSale_price());
				    	priscription.setFullname(practitioner);
				    	priscription.setClientname(clientname);
				    	priscription.setWhopay("0");
				    	priscription.setDate(date);
				    
				    	
				    	if(product.getStock()!="0"){
				    		result=pharmacyDAO.addEstimateMedicineCharges(priscription,billno);
				    	}
				    	
				    	//update inventory
				    	//result=productDAO.updateMedicineQty(priscription.getSaleqty(),priscription.getMdicinenameid(),0);
				    	
				     }
				    
				   double tot=Double.parseDouble(payamt);
				  //record payment
				   String nowdate=DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone()).split(" ")[0];
				   result=pharmacyDAO.recordEstimatePaymentMedicine(String.valueOf(billno),tot,paymode,nowdate,appointment.getClientId(),"0",discount,notes,appointment.getPclientid(),loginInfo.getUserId(),loc);
				    					
				   if(!appointment.getPclientid().equals("0")){
					   emrForm.setHdnphclientid(appointment.getPclientid());
					   emrForm.setHdnispharmacy("1");
				   } else {
					   emrForm.setHdnphclientid(appointment.getClientId());
					   emrForm.setHdnispharmacy("0");
				   }
				   
			} catch (Exception e) {

				e.printStackTrace();
			}
			finally {
				
				connection.close();
			}
			
			
			return "tempbill";
		}
	 
       
	 public String saleestimate() throws Exception{
		 if (!verifyLogin(request)) {

				return "login";
			}
			Connection connection=null;
			try {
				connection=Connection_provider.getconnection();
				InventoryProductDAO inventoryProductDAO=new JDBCInventoryProductDAO(connection);
				UserProfileDAO userProfileDAO = new JDBCUserProfileDAO(connection);
				PharmacyDAO pharmacyDAO = new JDBCPharmacyDAO(connection);
				String location =(String) session.getAttribute("location");
				if(location==null){
					location="0";
				}
				String estimatebill= request.getParameter("estimatebill");
				
				CompleteAppointment completeAppointment =pharmacyDAO.getEstimateBillDetails(estimatebill);
				
				if(!completeAppointment.getPclientid().equals("0")){
					Priscription priscription=pharmacyDAO.getPharmacyPatient(completeAppointment.getPclientid());
					emrForm.setFullname(priscription.getFullname());
					emrForm.setPractitionerName(priscription.getPractitionername());
					emrForm.setWardname(priscription.getAddress());
					emrForm.setMobno(priscription.getMobile());
					emrForm.setExtpid(completeAppointment.getPclientid());
				} else {
	    			BedDao bedDao= new JDBCBedDao(connection);
	    			ClientDAO clientDAO =new JDBCClientDAO(connection);
	 				Client client = clientDAO.getClientDetails(completeAppointment.getClientId());
	 				String name = client.getTitle()+" "+client.getFirstName()+" "+client.getLastName();
	 				emrForm.setAbrivationid(client.getAbrivationid());
	 				emrForm.setWardname(client.getAddress());
	 				emrForm.setExtpid("");
	 				emrForm.setFullname(name);
	 				String ipdid = pharmacyDAO.getIpdIdFromClientID(Integer.parseInt(completeAppointment.getClientId()));
	 				String pract_name ="";
	 				if(ipdid.equals("0")){
	 					pract_name = pharmacyDAO.getappointmentinfo(Integer.parseInt(completeAppointment.getClientId()));
	 				}else{
	 					Bed  bed = bedDao.getEditIpdData(ipdid);
	 					UserProfileDAO profileDAO = new JDBCUserProfileDAO(connection);
	 					pract_name = profileDAO.getName(bed.getPractitionerid());
	 				}
	 				emrForm.setPractitionerName(pract_name);
	 				String ipdoropd = "0";
	 				if(ipdid.equals("0")){
	 					ipdoropd ="0";
	 				}else{
	 					IpdDAO ipdDAO = new JDBCIpdDAO(connection);
	 					Bed  bed = bedDao.getEditIpdData(ipdid);
	 					String wardname = ipdDAO.getIpdWardName(bed.getBedid());
	 					String bedname = ipdDAO.getIpdBedName(bed.getWardid());
	 					ipdoropd = ipdid;
	 					String wardbed = wardname +"/"+ bedname;
	 					emrForm.setWardbed(wardbed);
	 				}
	 				emrForm.setIpdoropd(ipdoropd);
	 				ThirdPartyDAO thirdPartyDAO = new JDBCThirdPartyDAO(connection);
	 				String thirdPartyCompanyName="0";
	 				if(client.getTypeName()==null){
	 					client.setTypeName("0");
	 				}
	 				if(!client.getTypeName().equals("0")){
	 					ThirdParty thirdParty =thirdPartyDAO.getThirdPartyDetails(client.getTypeName());
	 					thirdPartyCompanyName = thirdParty.getCompanyName();
	 				}else{
	 					thirdPartyCompanyName ="0";
	 				}
	 				emrForm.setThirdPartyCompanyName(thirdPartyCompanyName);
	    			emrForm.setAddress(client.getAddress()); 
	    		 
					
				}
				
				
				ArrayList<Priscription> priscriptionlist= pharmacyDAO.getEstimateMedicineChargesList(estimatebill); 
				emrForm.setPriscriptionlist(priscriptionlist);
				ArrayList<Product> inventoryPriscList= inventoryProductDAO.geProductList("2",location);
				emrForm.setInventoryPriscList(inventoryPriscList);
				ArrayList<Product> pharmapatientlist = inventoryProductDAO.getpatientlist();
				emrForm.setPharmapatientlist(pharmapatientlist);
				emrForm.setSelectedclientid("0");
				emrForm.setExtpid(completeAppointment.getPclientid());
				String dateTime=DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone()).split(" ")[0];
				emrForm.setDateTime(DateTimeUtils.getCommencingDate1(dateTime));
				emrForm.setEstimatebill(estimatebill);
				
				  if(!completeAppointment.getPclientid().equals("0")){
					   emrForm.setHdnphclientid(completeAppointment.getPclientid());
					   emrForm.setHdnispharmacy("1");
				   } else {
					   emrForm.setHdnphclientid(completeAppointment.getClientId());
					   emrForm.setHdnispharmacy("0");
				   }
				
				UserProfile userProfile = userProfileDAO.getPharmacyUserDetails(1);
				//String inhousepatient = userProfile.getInhousepatient();
				
				Priscription priscri = pharmacyDAO.getPharacyUsrLInfo(loginInfo.getUserId());
				
				if(priscri.getAccess_back_date()==1){
					 emrForm.setBack_date_access("1");
				}
				if(priscri.getTpbill()==1){
					emrForm.setTp_bill_access("1");
				}
				
			} catch (Exception e) {
			
				e.printStackTrace();
			}
			finally {
				connection.close();
			}
			
			return "estimatesale";
		}
	 
	 public String requestTransfer() throws Exception{
		 if (!verifyLogin(request)) {

				return "login";
			}
		 Connection connection = null;
		 try {
			
		 	ArrayList<Breadcrumbs> indentflowlist = new ArrayList<Breadcrumbs>();
			ArrayList<Breadcrumbs> indentflowlisttemp = new ArrayList<Breadcrumbs>();
			if (session.getAttribute("indentflowlist") != null) {
				indentflowlisttemp = (ArrayList<Breadcrumbs>) session.getAttribute("indentflowlist");
			}
			boolean isavilablemodule= false;
			int modulecount =0;
			for (Breadcrumbs breadcrumbs : indentflowlisttemp) {
				breadcrumbs.setIscurrent(false);
				breadcrumbs.setSqno(modulecount);
				modulecount++;
				if(breadcrumbs.getName().equals("Requisition")){
					isavilablemodule =true;
					breadcrumbs.setIscurrent(true);
					indentflowlist.add(breadcrumbs);
					break;
				}else{
					indentflowlist.add(breadcrumbs);
				}
			}
			if(!isavilablemodule){
				Breadcrumbs breadcrumbs = new Breadcrumbs();
				breadcrumbs.setName("Requisition");
				breadcrumbs.setOn(true);
				breadcrumbs.setSqno(modulecount);
				breadcrumbs.setUrllink("requestTransferPharmacy");
				breadcrumbs.setIscurrent(true);
				breadcrumbs.setShowingname("Requisition");
				indentflowlist.add(breadcrumbs);
			}
			session.setAttribute("indentflowlist",indentflowlist);
			
			 
			connection = Connection_provider.getconnection();
			PharmacyDAO pharmacyDAO = new JDBCPharmacyDAO(connection);
			InventoryProductDAO inventoryProductDAO = new JDBCInventoryProductDAO(connection);
			String fromdate= emrForm.getFromdate();
			String todate= emrForm.getTodate();
			
			String loc= (String) session.getAttribute("location");
			if(loc==null){
				loc="0";
			}
			
			if(fromdate!=null){
				if(fromdate.equals("")){
				    SimpleDateFormat dateFormat= new SimpleDateFormat("yyyy-MM-dd");
				    Calendar calendar=Calendar.getInstance();
				    fromdate=dateFormat.format(calendar.getTime());
				} else {
				    fromdate=DateTimeUtils.getCommencingDate1(fromdate);
				}
			} else {
				SimpleDateFormat dateFormat= new SimpleDateFormat("yyyy-MM-dd");
			    Calendar calendar=Calendar.getInstance();
			    fromdate=dateFormat.format(calendar.getTime());
			}
			if(todate!=null){
				if(todate.equals("")){
				    SimpleDateFormat dateFormat= new SimpleDateFormat("yyyy-MM-dd");
				    Calendar calendar=Calendar.getInstance();
				    todate=dateFormat.format(calendar.getTime());
				 } else {
				    todate=DateTimeUtils.getCommencingDate1(todate);
				 }
			} else {
				SimpleDateFormat dateFormat= new SimpleDateFormat("yyyy-MM-dd");
			    Calendar calendar=Calendar.getInstance();
			    todate=dateFormat.format(calendar.getTime());
			}
			ArrayList<Product> arrayList = pharmacyDAO.getAllRequestedMedFrTransfer(fromdate, todate,loc);
			emrForm.setRequestedMedicineList(arrayList);
			ArrayList<Product> parenttransferlist =  pharmacyDAO.getParentProductTransferData(fromdate,todate,loc);
			emrForm.setParenttransferlist(parenttransferlist);
			
			fromdate=DateTimeUtils.getCommencingDate1(fromdate);
			todate=DateTimeUtils.getCommencingDate1(todate);
			emrForm.setFromdate(fromdate);
			emrForm.setTodate(todate);
			
			ArrayList<Master> warehouseList= inventoryProductDAO.getWareHouseList();
			emrForm.setWarehouseList(warehouseList);
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			
			connection.close();
		}
		return "requestTransfer";
	 }
	 /*public String requestmedicine() throws Exception{
			Connection connection = null;
			 try {
				connection = Connection_provider.getconnection();
				PharmacyDAO pharmacyDAO = new JDBCPharmacyDAO(connection);
				String id= request.getParameter("allrequestlist");
				String prodid ="0";
				String m_id = "0";
				for(String s:id.split(",")) {
					 if(s.equals("0")){
						   continue;
					  }
					m_id=s;
				}
				Product product1 = pharmacyDAO.getRequestedMedicineid(m_id);
				int result = pharmacyDAO.saveNewMedicineRequestParent(product1);
				
				for(String s:id.split(",")) {
					 if(s.equals("0")){
						   continue;
					  }
					String req_qty = request.getParameter("qty"+s+"");
					Product product = pharmacyDAO.getRequestedMedicineid(s);
					int result2 = pharmacyDAO.saveNewMedicineRequestChild(product,req_qty,result);
					int result3 = pharmacyDAO.updateRequestedStatusById(s);
				}
				
				
			 } catch (Exception e) {
				e.printStackTrace();
			}finally{
				connection.close();
			}
			 return "requestMedicine";
		 }*/
		 
		 
		 public String checkmedicineavability() throws Exception{
			 if (!verifyLogin(request)) {

					return "login";
				}
			String parentid = request.getParameter("parentid2");
			Connection connection = null;
			try {
				session.setAttribute("reqparentid", parentid);
				connection= Connection_provider.getconnection();
				InventoryProductDAO inventoryProductDAO = new JDBCInventoryProductDAO(connection);
				PharmacyDAO pharmacyDAO = new JDBCPharmacyDAO(connection);
				ArrayList<Product> arrayList = inventoryProductDAO.getChildTranfserData(parentid);
				ArrayList<String> arrayList2 = new ArrayList<String>();
				for (Product product : arrayList) {
					String pname = product.getProduct_name();
					arrayList2.add(pname);
				}
				//pharmacyDAO.checkMedicineAvability(arrayList2);
				Product product2 = inventoryProductDAO.getParentTransferData(parentid);
				String fromlocation = product2.getLocation();
				int count=inventoryProductDAO.geReqTranTotalProductCountfrCatalogue(arrayList2,fromlocation);
				pagination.setPreperties(count);
				emrForm.setTotalRecords(count);
				ArrayList<Product> productList=inventoryProductDAO.getReqTransAllProdfrCatalogue(pagination,arrayList2,fromlocation);
				pagination.setPage_records(productList.size());
				emrForm.setPagerecords(String.valueOf(pagination.getPage_records()));
				emrForm.setProductList(productList);

				/*ArrayList<Master> medicineTypeList=inventoryProductDAO.getMedicineTypeList(); 
				emrForm.setBrandnameList(brandnameList);*/
				
				emrForm.setBrandnameList(null);
				
				emrForm.setMedicineTypeList(null);  
				 String loc= (String) session.getAttribute("location");
					if(loc==null){
						loc="0";
					}
				
				 InventoryVendorDAO inventoryVendorDAO= new JDBCInventoryVendorDAO(connection);
				 ArrayList<Vendor> vendorList= inventoryVendorDAO.getAllVendorList(loc);
				 emrForm.setVendorList(vendorList);
				 
				 ArrayList<Master> cellList = inventoryProductDAO.getcellList("32,33,34,35,36,105");
				 emrForm.setCellList(cellList);
				 ArrayList<Master> locationListPharmacy= pharmacyDAO.getAllLocation();
				 emrForm.setLocationListPharmacy(locationListPharmacy);
				 emrForm.setLocation(loc);
				
			} catch (Exception e) {
				e.printStackTrace();
			}finally{
				
				connection.close();
			}
			
			return "transfertocatalogue";
		 }
		 
		 
		 
		 public String showrequestedmedicinefraprove() throws Exception{
			 if (!verifyLogin(request)) {

					return "login";
				}
				Connection connection = null;
				  try {
					connection = Connection_provider.getconnection();
					InventoryProductDAO inventoryProductDAO = new JDBCInventoryProductDAO(connection);
					PharmacyDAO pharmacyDAO = new JDBCPharmacyDAO(connection);
					String parentid = request.getParameter("parentid");
					String val = request.getParameter("val");
					Product product = inventoryProductDAO.getParentTransferData(parentid);
					ArrayList<Product> childtransferlist = inventoryProductDAO.getChildRequestedTempData(parentid);
					Product product3 = inventoryProductDAO.getChildRequestTempCal(parentid);
					StringBuilder builder = new StringBuilder();
					
					builder.append(""+product.getFrom_location()+"");
					builder.append("~");
					
					builder.append(""+product.getRequest_date()+"");
					builder.append("~");
					
					builder.append(""+product.getTo_location()+"");
					builder.append("~");
					int i=1;
					for (Product product2 : childtransferlist) {
						builder.append("<tr>");
			            builder.append("<td>"+i+"</td>");
			            String hsnno="";
			            if(product2.getHsnno()!=null){
			            	hsnno = product2.getHsnno();
			            }
			            builder.append("<td>"+hsnno+"</td>");
			            builder.append("<td>"+product2.getProduct_name()+"</td>");
			            builder.append("<td>"+product2.getBatch_no()+"</td>");
			            builder.append("<td>"+product2.getExpiry_date()+"</td>");
			            builder.append("<td>"+product2.getStock()+"</td>");
			            builder.append("<td style='text-align: right;'>"+product2.getSale_price()+"</td>");
			            builder.append("<td style='text-align: right;'>"+product2.getAmountno()+"</td>");
			            builder.append("<td style='text-align: right;'>"+product2.getMrp()+"</td>");
			            builder.append("<td style='text-align: right;'>"+product2.getAmountmrp()+"</td>");
			            builder.append("<td style='text-align: right;'>"+pharmacyDAO.getPharmacyLocation(product2.getOld_location())+"</td>");
			            builder.append("</tr>");
					}
					builder.append("<tr>");
		            builder.append("<td></td>");
		            builder.append("<td></td>");
		            builder.append("<td></td>");
		            builder.append("<td></td>");
		            builder.append("<td></td>");
		            builder.append("<td></td>");
		            builder.append("<td style='text-align: right;font-weight: bold;color: green;'>GRAND TOTAL</td>");
		            builder.append("<td style='text-align: right;font-weight: bold;color: green;'>"+product3.getTotal_amount()+"</td>");
		            builder.append("<td></td>");
		            builder.append("<td style='text-align: right;font-weight: bold;color: green;'>"+product3.getTotolmrp()+"</td>");
		            builder.append("<td></td>");
		            builder.append("</tr>");
		            
		            builder.append("~");
		            builder.append(""+parentid+"");
		            
		            builder.append("~");
		   		 	UserProfileDAO userProfileDAO= new JDBCUserProfileDAO(connection);
		   		 	UserProfile userProfile= userProfileDAO.getPharmacyUserDetails(loginInfo.getClinicid());
		   		 	builder.append("<h4>"+userProfile.getClinicname()+"</h4>");
		   		 	builder.append("<h5>"+userProfile.getAddress()+"</h5>");
		   		 	builder.append("<h5>Website:"+userProfile.getWebsite()+", Email:"+userProfile.getEmail()+", Contact : "+userProfile.getPhone()+"</h5>");
		   		 	
		   		 	
		   		 	String userid = loginInfo.getUserId();
		   		 	int selectedid = loginInfo.getId();
		   		 	//UserProfile userProfile2 =userProfileDAO.getUserprofileDetails(selectedid);
		   		 	//String username = userProfile2.getInitial()+" "+ userProfile2.getFirstname()+" "+ userProfile2.getLastname();
		   		 	String datetime ="";
		   		 	DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy hh:mm a");
		   		 	Calendar cal = Calendar.getInstance();
		   		 	datetime = dateFormat.format(cal.getTime());
		   		 	builder.append("~");
		   		 	builder.append(""+userid+"");
		   		 	builder.append("~");
		   		 	builder.append(""+datetime+"");
		   		 	
		   		 	builder.append("~");
		   		 	if(val.equals("2")){
		   		 		builder.append("<input type='button'  class='btn btn-primary' value='Admin Aprove' onclick='aproveTransferLog()'>");
		   		 		builder.append("<input type='button'  class='btn btn-primary' value='Admin Reject' onclick='rejectTransferLog()'>");
		   		 	}else if(val.equals("4")){
		   		 		builder.append("Admin Rejected");
		   		 	}else if(val.equals("5")){
		   		 		builder.append("Head Rejected");
		   		 	}else if(val.equals("3")){
		   		 		builder.append("<input type='button'  class='btn btn-primary' value='Head Aprove' onclick='finalaproveTransferLog()'>");
		   		 		builder.append("<input type='button'  class='btn btn-primary' value='Head Reject' onclick='finalrejectTransferLog()'>");
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
		 
		/* public String updateAproveTransferLog()throws Exception{
			 Connection connection = null;
			  try {
				connection = Connection_provider.getconnection();
				PharmacyDAO pharmacyDAO = new JDBCPharmacyDAO(connection);
				String parentid = request.getParameter("parentid");
				String aprove = request.getParameter("aprove");
				int result = pharmacyDAO.updateAproveTransferLog(parentid,"2",aprove);
				response.setContentType("text/html");
				response.setHeader("Cache-Control", "no-cache");
				response.getWriter().write(""+result+""); 
			 } catch (Exception e) {
				e.printStackTrace();
			}
			  return null;
		 }
		 
		 public String updaterejectTransferLog()throws Exception{
			 Connection connection = null;
			  try {
				connection = Connection_provider.getconnection();
				PharmacyDAO pharmacyDAO = new JDBCPharmacyDAO(connection);
				String parentid = request.getParameter("parentid");
				String notes = request.getParameter("notes");
				String reject = request.getParameter("reject");
				int result = pharmacyDAO.updateRejectTransferLog(parentid,"4",notes,reject);
				response.setContentType("text/html");
				response.setHeader("Cache-Control", "no-cache");
				response.getWriter().write(""+result+""); 
				
			 } catch (Exception e) {
				e.printStackTrace();
			}
			  return null;
		 }*/
		 
		 
		 public String updatefinalrejectTransferLog()throws Exception{
			 if (!verifyLogin(request)) {

					return "login";
				}
			 Connection connection = null;
			  try {
				connection = Connection_provider.getconnection();
				PharmacyDAO pharmacyDAO = new JDBCPharmacyDAO(connection);
				String parentid = request.getParameter("parentid");
				String notes = request.getParameter("notes");
				String reject = request.getParameter("reject");
				int result = pharmacyDAO.updateRejectTransferLog(parentid,"5",notes,reject);
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
		 
		 
		 
		 public String updatefinalAproveTransferLog()throws Exception{
			 if (!verifyLogin(request)) {

					return "login";
				}
			 Connection connection = null;
			  try {
				connection = Connection_provider.getconnection();
				PharmacyDAO pharmacyDAO = new JDBCPharmacyDAO(connection);
				InventoryProductDAO inventoryProductDAO = new JDBCInventoryProductDAO(connection);
				String parentid = request.getParameter("parentid");
				ArrayList<Product> arrayList = pharmacyDAO.getChildTempReqData(parentid);
				int res = pharmacyDAO.deleteTempRequestedData(parentid);
				for (Product product : arrayList) {
					int result = pharmacyDAO.transferRequestedMedicine(parentid,product);
				}
				
				int result1 = pharmacyDAO.updateAproveTransferLog(parentid,"3","0");
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
		 
		 public String setRequisition_authAccess() throws Exception {
			 if (!verifyLogin(request)) {

					return "login";
				}
			  Connection connection = null;
				 try {
					connection = Connection_provider.getconnection();
					PharmacyDAO pharmacyDAO = new JDBCPharmacyDAO(connection);
					String userid = request.getParameter("userid");
					String status = request.getParameter("status");



					Calendar cal = Calendar.getInstance();
			        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss.SS");
			        String dateString = sdf.format(cal.getTime());
			        
					String Accessdatetime=dateString;
					String CurrentUser=loginInfo.getUserId();
					
					
					if(status.equals("0") || status=="0"){
						status = "1";
					}
					else {
						status = "0";
					}
					Priscription priscription = pharmacyDAO.setRequisition_AuthAccess(userid,status,Accessdatetime,CurrentUser);
					StringBuilder sb = new StringBuilder();
					if(priscription.getRequisition_auth().equals("0")){
						sb.append("<input type='checkbox' onclick='setRequisition_authAccess("+priscription.getId()+","+priscription.getRequisition_auth()+")' id='requisition_auth"+priscription.getId()+"' class='onoffswitch-checkbox'>");
						sb.append("<label class='onoffswitch-label' for='requisition_auth"+priscription.getId()+"'>");
						sb.append("<span class='onoffswitch-inner'></span>");
						sb.append("<span class='onoffswitch-switch'></span>");
						sb.append("</label>");
					}
					else
					{
						sb.append("<input type='checkbox' onclick='setRequisition_authAccess("+priscription.getId()+","+priscription.getRequisition_auth()+")' id='requisition_auth"+priscription.getId()+"' class='onoffswitch-checkbox'  checked='checked'>");
						sb.append("<label class='onoffswitch-label' for='requisition_auth"+priscription.getId()+"'>");
						sb.append("<span class='onoffswitch-inner'></span>");
						sb.append("<span class='onoffswitch-switch'></span>");
						sb.append("</label>");
					}
					
					response.setContentType("text/html");
					response.setHeader("Cache-Control", "no-cache");
					response.getWriter().write(""+sb.toString()+"");
				} catch (Exception e) {
					e.printStackTrace();
				}finally{
					connection.close();
				}
				 return null;
		  }
		 
		 public String requestmedicine() throws Exception{
			 if (!verifyLogin(request)) {

					return "login";
				}
			    Connection connection = null;
			     try {
			     connection = Connection_provider.getconnection();
			     PharmacyDAO pharmacyDAO = new JDBCPharmacyDAO(connection);
			     InventoryProductDAO inventoryProductDAO =  new JDBCInventoryProductDAO(connection);
			     ProcurementDAO procurementDAO= new JDBCProcurementDAO(connection);
			     IndentDAO indentDAO = new JDBCIndentDAO(connection);
			     String id= request.getParameter("allrequestlist");
			     String warehouse_id = request.getParameter("warehouse_id");
			     if(warehouse_id==null){
			    	 warehouse_id="1";
			     }else if(warehouse_id.equals("")){
			    	 warehouse_id="1";
			     }
			     String prodid ="0";
			     String m_id = "0";
			     for(String s:id.split(",")) {
			       if(s.equals("0")){
			          continue;
			        }
			      m_id=s;
			     }
			     Product product1 = pharmacyDAO.getRequestedMedicineid(m_id);
			     String userid = loginInfo.getUserId();
			     String exdate = emrForm.getExpectedDate();
			     product1.setExpectedDate(emrForm.getExpectedDate());
			     //
			     int count=inventoryProductDAO.getTotalIdentCount();
			     product1.setIndent(count+1);
			     product1.setWarehouse_id(warehouse_id);
			     
			     
			     int result = pharmacyDAO.saveNewMedicineRequestParent(product1,userid);
			     
			     
			     
			     for(String s:id.split(",")) {
			       if(s.equals("0")){
			          continue;
			        }
			      String req_qty = request.getParameter("qty"+s+"");
			      String comment = request.getParameter("comment"+s+"");
			      Product product = pharmacyDAO.getRequestedMedicineid(s);
			      Product product2 = inventoryProductDAO.getProduct(product.getProduct_id());
			      String catalogueid = indentDAO.getCatIdProdId(s);
			      int avail_qty = inventoryProductDAO.getAllAvailableStock(product2.getProduct_name(), product2.getLocation());
			      product.setCatalogueid(catalogueid);
			      int result2 = pharmacyDAO.saveNewMedicineRequestChild(product,req_qty,result,product2,avail_qty,comment);
			      int result3 = pharmacyDAO.updateRequestedStatusById(s);
			      double req_qty1=Double.parseDouble(req_qty);
			    
			      //@ jitu for 7star add to po directly not indent said by dip
			      int r = procurementDAO.addTempPo(product.getProduct_id(), product2.getVendor_id(), req_qty, 0,"0","0",0,product2.getVat(),loginInfo.getLoginsessionid(),"","","0");
			     }
			     } catch (Exception e) {
			     e.printStackTrace();
			    }finally{
			     connection.close();
			    }
			     return "requestMedicine";
			    }
		 public String updateAproveTransferLog()throws Exception{
			 if (!verifyLogin(request)) {

					return "login";
				}
			 Connection connection = null;
			  try {
				connection = Connection_provider.getconnection();
				PharmacyDAO pharmacyDAO = new JDBCPharmacyDAO(connection);
				String parentid = request.getParameter("parentid");
				String aprove = request.getParameter("aprove");
				String userid = loginInfo.getUserId();
				int result = pharmacyDAO.updateAproveTransferLog(parentid,"1",aprove,userid);
				
				
			 } catch (Exception e) {
				e.printStackTrace();
			 }
			 finally {
				 connection.close();
			 }
			 
			 return null;
		 }


 public String updaterejectTransferLog()throws Exception{
	 if (!verifyLogin(request)) {

			return "login";
		}
			 Connection connection = null;
			  try {
				connection = Connection_provider.getconnection();
				PharmacyDAO pharmacyDAO = new JDBCPharmacyDAO(connection);
				String parentid = request.getParameter("parentid");
				String notes = request.getParameter("notes");
				String reject = request.getParameter("reject");
				String userid = loginInfo.getUserId();
				int result = pharmacyDAO.updateRejectTransferLog(parentid,"2",notes,reject,userid);
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
 public String requeststock() throws Exception{
	 if (!verifyLogin(request)) {

			return "login";
		}
		Connection connection=null;
		try {
			connection=Connection_provider.getconnection();
			PharmacyDAO pharmacyDAO=new JDBCPharmacyDAO(connection);
			String pid=request.getParameter("pid");
			String qty=request.getParameter("quantity");
			String date=DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone());
			
			String loc= (String) session.getAttribute("location");
			if(loc==null){
				loc="0";
			}
			
			String userid = loginInfo.getUserId();
			int result=pharmacyDAO.addnewRequestStock(pid,qty,date,loc,userid);
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
    
 
   public String getbilldisc() throws Exception {
	   if (!verifyLogin(request)) {

			return "login";
		}
	   Connection connection=null; 
	   try {
		
		    connection=Connection_provider.getconnection();
		    PharmacyDAO pharmacyDAO=new JDBCPharmacyDAO(connection);
		    String billno=request.getParameter("billno");
		    CompleteAppointment appointment=pharmacyDAO.getBillDetails(Integer.parseInt(billno));
		    String name="";
		    if(!appointment.getPclientid().equals("0")){
		    	
		    	Priscription priscription=pharmacyDAO.getPharmacyPatient(appointment.getPclientid());
		    	name=priscription.getFullname();
		    } else {
		    	ClientDAO clientDAO=new JDBCClientDAO(connection);
		    	Client client=clientDAO.getClientDetails(appointment.getClientId());
		    	name=client.getTitle()+" "+client.getFirstName()+" "+client.getLastName();
		    }
		    int discoutstatus=0;
		    if(appointment.getDiscount()>0){
		    	discoutstatus=1;
		    }
		    int res = pharmacyDAO.checkpaymentdoneagainstbill(billno);
		    String data=name+"~"+appointment.getBalance()+"~"+billno+"~"+discoutstatus+"~"+appointment.getTotal()+"~"+res;
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
   
/*   public String getpatientbal() throws Exception {
	    
	    Connection connection=null; 
	    try {
	  
	      connection=Connection_provider.getconnection();
	      PharmacyDAO pharmacyDAO=new JDBCPharmacyDAO(connection);
	      String clientid=request.getParameter("clientid");
	      String flag= request.getParameter("flag");
	      String discountbalstatus = request.getParameter("discountbalstatus");
	      String name="";
	    
	      String data="";
	      double totalbal=0;
	      if(flag.equals("1")){
	       
	       Priscription priscription=pharmacyDAO.getPharmacyPatient(clientid);
	       name=priscription.getFullname();
	       totalbal =pharmacyDAO.getTotalBalance(clientid,flag);
	      } else {
	       ClientDAO clientDAO=new JDBCClientDAO(connection);
	       Client client=clientDAO.getClientDetails(clientid);
	       name=client.getTitle()+" "+client.getFirstName()+" "+client.getLastName();
	       totalbal =pharmacyDAO.getTotalBalance(clientid,flag);
	      }
	      data=flag+"~"+clientid+"~"+name+"~"+DateTimeUtils.changeFormat(totalbal)+"~"+discountbalstatus;
	      response.setContentType("text/html");
	   response.setHeader("Cache-Control", "no-cache");
	   response.getWriter().write(data); 
	      
	 } catch (Exception e) {

	  e.printStackTrace();
	 }finally{
			
			connection.close();
		}
	    
	    return null;
	   }*/
   
   public String setCheck_stock_availableAccess() throws Exception {
	   if (!verifyLogin(request)) {

			return "login";
		}
		//@k@sh
	   Connection connection = null;
			 try {
				connection = Connection_provider.getconnection();
				PharmacyDAO pharmacyDAO = new JDBCPharmacyDAO(connection);
				String userid = request.getParameter("userid");
				String status = request.getParameter("status");

				Calendar cal = Calendar.getInstance();
		        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss.SS");
		        String dateString = sdf.format(cal.getTime());
		        
				String Accessdatetime=dateString;
				String CurrentUser=loginInfo.getUserId();
				
				if(status.equals("0") || status=="0"){
					status = "1";
				}
				else {
					status = "0";
				}
				Priscription priscription = pharmacyDAO.setCheck_stock_availableAccess(userid,status,Accessdatetime,CurrentUser);
				StringBuilder sb = new StringBuilder();
				if(priscription.getCheck_stock_available().equals("0")){
					sb.append("<input type='checkbox' onclick='setCheck_stock_availableAccess("+priscription.getId()+","+priscription.getCheck_stock_available()+")' id='checkstockavail"+priscription.getId()+"' class='onoffswitch-checkbox'>");
					sb.append("<label class='onoffswitch-label' for='checkstockavail"+priscription.getId()+"'>");
					sb.append("<span class='onoffswitch-inner'></span>");
					sb.append("<span class='onoffswitch-switch'></span>");
					sb.append("</label>");
				}
				else
				{
					sb.append("<input type='checkbox' onclick='setCheck_stock_availableAccess("+priscription.getId()+","+priscription.getCheck_stock_available()+")' id='checkstockavail"+priscription.getId()+"' class='onoffswitch-checkbox'  checked='checked'>");
					sb.append("<label class='onoffswitch-label' for='checkstockavail"+priscription.getId()+"'>");
					sb.append("<span class='onoffswitch-inner'></span>");
					sb.append("<span class='onoffswitch-switch'></span>");
					sb.append("</label>");
				}
				response.setContentType("text/html");
				response.setHeader("Cache-Control", "no-cache");
				response.getWriter().write(""+sb.toString()+"");
			} catch (Exception e) {
				e.printStackTrace();
			}finally{
				connection.close();
			}
			 return null;
	  }
   
   
   public String setDirect_TransferAccess() throws Exception {
	   if (!verifyLogin(request)) {

			return "login";
		}
		//@k@sh
	   Connection connection = null;
			 try {
				connection = Connection_provider.getconnection();
				PharmacyDAO pharmacyDAO = new JDBCPharmacyDAO(connection);
				String userid = request.getParameter("userid");
				String status = request.getParameter("status");

				Calendar cal = Calendar.getInstance();
		        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss.SS");
		        String dateString = sdf.format(cal.getTime());
		        
				String Accessdatetime=dateString;
				String CurrentUser=loginInfo.getUserId();
				
				if(status.equals("0") || status=="0"){
					status = "1";
				}
				else {
					status = "0";
				}
				Priscription priscription = pharmacyDAO.setDirect_TransferAccess(userid,status,Accessdatetime,CurrentUser);
				StringBuilder sb = new StringBuilder();
				if(priscription.getDirect_transfer().equals("0")){
					sb.append("<input type='checkbox' onclick='setDirect_TransferAccess("+priscription.getId()+","+priscription.getDirect_transfer()+")' id='direct_transfer"+priscription.getId()+"' class='onoffswitch-checkbox'>");
					sb.append("<label class='onoffswitch-label' for='direct_transfer"+priscription.getId()+"'>");
					sb.append("<span class='onoffswitch-inner'></span>");
					sb.append("<span class='onoffswitch-switch'></span>");
					sb.append("</label>");
				}
				else
				{
					sb.append("<input type='checkbox' onclick='setDirect_TransferAccess("+priscription.getId()+","+priscription.getDirect_transfer()+")' id='direct_transfer"+priscription.getId()+"' class='onoffswitch-checkbox'  checked='checked'>");
					sb.append("<label class='onoffswitch-label' for='direct_transfer"+priscription.getId()+"'>");
					sb.append("<span class='onoffswitch-inner'></span>");
					sb.append("<span class='onoffswitch-switch'></span>");
					sb.append("</label>");
				}
				response.setContentType("text/html");
				response.setHeader("Cache-Control", "no-cache");
				response.getWriter().write(""+sb.toString()+"");
			} catch (Exception e) {
				e.printStackTrace();
			}finally{
				connection.close();
			}
			 return null;
	  }
   
   
   
   public String setInventory_ReportAccess() throws Exception {
	   if (!verifyLogin(request)) {

			return "login";
		}
	   //ruchi
	     Connection connection = null;
	     try {
	     connection = Connection_provider.getconnection();
	     PharmacyDAO pharmacyDAO = new JDBCPharmacyDAO(connection);
	     String userid = request.getParameter("userid");
	     String status = request.getParameter("status");
	     
	     Calendar cal = Calendar.getInstance();
	        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss.SS");
	        String dateString = sdf.format(cal.getTime());
	        
			String Accessdatetime=dateString;
			String CurrentUser=loginInfo.getUserId();
			
	     if(status.equals("0") || status=="0"){
	      status = "1";
	     }
	     else {
	      status = "0";
	     }
	     Priscription priscription = pharmacyDAO.setInventory_ReportAccess(userid,status,Accessdatetime,CurrentUser);
	     StringBuilder sb = new StringBuilder();
	     if(priscription.getInventory_transfer().equals("0")){
	      sb.append("<input type='checkbox' onclick='setInventory_ReportAccess("+priscription.getId()+","+priscription.getInventory_transfer()+")' id='inventory_report"+priscription.getId()+"' class='onoffswitch-checkbox'>");
	      sb.append("<label class='onoffswitch-label' for='inventory_report"+priscription.getId()+"'>");
	      sb.append("<span class='onoffswitch-inner'></span>");
	      sb.append("<span class='onoffswitch-switch'></span>");
	      sb.append("</label>");
	     }
	     else
	     {
	      sb.append("<input type='checkbox' onclick='setInventory_ReportAccess("+priscription.getId()+","+priscription.getInventory_transfer()+")' id='inventory_report"+priscription.getId()+"' class='onoffswitch-checkbox'  checked='checked'>");
	      sb.append("<label class='onoffswitch-label' for='inventory_report"+priscription.getId()+"'>");
	      sb.append("<span class='onoffswitch-inner'></span>");
	      sb.append("<span class='onoffswitch-switch'></span>");
	      sb.append("</label>");
	     }
	     response.setContentType("text/html");
	     response.setHeader("Cache-Control", "no-cache");
	     response.getWriter().write(""+sb.toString()+"");
	    } catch (Exception e) {
	     e.printStackTrace();
	    }finally{
	     connection.close();
	    }
	     return null;
	    }
   
   public String deletepharmacyBill() throws Exception {
	   if (!verifyLogin(request)) {

			return "login";
		}
		Connection connection=null;
		try {
			connection=Connection_provider.getconnection();
			PharmacyDAO pharmacyDAO=new JDBCPharmacyDAO(connection);
			InventoryProductDAO inventoryProductDAO = new JDBCInventoryProductDAO(connection);
			
			String bill= request.getParameter("bill_no");
			String delete_reason = request.getParameter("delete_reason");
			String isbillorestimate = request.getParameter("isbillorestimate");
			//int r=pharmacyDAO.deleteMedicalBill(bill);
			
			if(isbillorestimate.equals("0")){
				int res = pharmacyDAO.cancelEstimate(bill);
			}else{
				int r=pharmacyDAO.cancelMedicineBill(bill);
				
				String userid = loginInfo.getUserId();
				String loc= (String) session.getAttribute("location");
				if(loc==null){
					loc="0";
				}
				
				ArrayList<Priscription> chargeList= pharmacyDAO.getMedicineChargesList(Integer.parseInt(bill),loginInfo);
				for(Priscription priscription: chargeList){
					int previousstock = inventoryProductDAO.getPreviousStock(priscription.getProduct_id());
					//update qty
					Product product=inventoryProductDAO.getProduct(priscription.getProduct_id());
					int result=inventoryProductDAO.updateMedicineQty(priscription.getSaleqty(),priscription.getProduct_id(),1);
					
					//stock log
					String datetime = DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone());
					int qtyinout=0;
					String source ="Pharmacy Sale Cancel";
					int currentstock=inventoryProductDAO.getPreviousStock(priscription.getProduct_id());
					int changeqty=priscription.getSaleqty();
					int reslog = inventoryProductDAO.insertIntoProductLog(loginInfo.getUserId(),datetime,product.getLocation(),qtyinout,priscription.getProduct_id(),product.getCatalogueid(),source,currentstock,previousstock,changeqty,"0","0",Integer.parseInt(bill),0,0,"0");
					
					String datetime1[]=DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone()).split(" ");
					String date = datetime1[0];
					int openingstock = previousstock;
					boolean checkopningstockexist = pharmacyDAO.checkopeningstockexist(priscription.getProduct_id(),date);
					if(!checkopningstockexist){
						int r1 = pharmacyDAO.saveOpeningStock(priscription.getProduct_id(),date,openingstock,"0");
					}
				}
				
				
				
				int res = inventoryProductDAO.saveUpDeletePharmacyBill(userid,loc,delete_reason,bill);
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
   
   
    public String printbalreceipt() throws Exception {
    	 if (!verifyLogin(request)) {

 			return "login";
 		}
    	int res=0;
    	Connection connection=null;
    	try {
    		 int shownavigation=1;
    		 int parentid=0;
			 String id= request.getParameter("parentid");
			 if(id==null){
				 parentid = (Integer) session.getAttribute("parentbillid");
			 }
			 else if(id.equals("")){
				 parentid = (Integer) session.getAttribute("parentbillid");
			 } else {
				 parentid = Integer.parseInt(id);
				 shownavigation =0;
			 }
    		emrForm.setShownavigation(shownavigation);
    		if(shownavigation==1){
    			/*ArrayList<Breadcrumbs> indentflowlist = new ArrayList<Breadcrumbs>();
    			ArrayList<Breadcrumbs> indentflowlisttemp = new ArrayList<Breadcrumbs>();
    			if (session.getAttribute("indentflowlist") != null) {
    				indentflowlisttemp = (ArrayList<Breadcrumbs>) session.getAttribute("indentflowlist");
    			}
    			boolean isavilablemodule= false;
    			int modulecount =0;
    			for (Breadcrumbs breadcrumbs : indentflowlisttemp) {
    				breadcrumbs.setIscurrent(false);
    				breadcrumbs.setSqno(modulecount);
    				modulecount++;
    				if(breadcrumbs.getName().equals("Balance Clear Reciept")){
    					isavilablemodule =true;
    					breadcrumbs.setIscurrent(true);
    					indentflowlist.add(breadcrumbs);
    					break;
    				}else{
    					indentflowlist.add(breadcrumbs);
    				}
    			}
    			if(!isavilablemodule){
    				Breadcrumbs breadcrumbs = new Breadcrumbs();
    				breadcrumbs.setName("Balance Clear Reciept");
    				breadcrumbs.setOn(false);
    				breadcrumbs.setSqno(modulecount);
    				breadcrumbs.setUrllink("printbalreceiptPharmacy?parentid="+parentid+"");
    				breadcrumbs.setIscurrent(true);
    				breadcrumbs.setShowingname("Balance Clear Reciept");
    				indentflowlist.add(breadcrumbs);
    			}
    			session.setAttribute("indentflowlist",indentflowlist);*/
    		}
    		
			 connection=Connection_provider.getconnection();
			 UserProfileDAO userProfileDAO= new JDBCUserProfileDAO(connection);
			 Clinic clinic = new Clinic();
			 ClientDAO clientDAO= new JDBCClientDAO(connection);
				ClinicDAO clinicDAO  = new JDBCClinicDAO(connection);
				clinic = clinicDAO.getCliniclistDetails(loginInfo.getClinicid());
			 PharmacyDAO pharmacyDAO= new JDBCPharmacyDAO(connection);
			 
    		 
    		 String dateTime =DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone());
    		 dateTime =DateTimeUtils.getDBDate(dateTime);
    		 
    		 Priscription master = pharmacyDAO.getClearBillReceptInfo(parentid); 
    		 ArrayList<Priscription> clearBillList= pharmacyDAO.getClearBillList(parentid);
    		 double tot=0;
    		 for(Priscription priscription :clearBillList){
    			 
    			 tot=tot+ Double.parseDouble(priscription.getPayment());
    		 }
    		 emrForm.setTotal(DateTimeUtils.changeFormat(tot));
    		 
    		 double disctot = tot - master.getDiscount();
    		 
    		 emrForm.setPayamt(DateTimeUtils.changeFormat(disctot));
    		 emrForm.setClearBillList(clearBillList);
    		 emrForm.setDiscount(DateTimeUtils.changeFormat(master.getDiscount()));
    		 emrForm.setDateTime(master.getDateTime());
    		 emrForm.setCreatedby(master.getUserid()+" "+master.getDateTime());
    		 emrForm.setPrintedby(loginInfo.getUserId()+" "+dateTime);
    		 emrForm.setLocationName(pharmacyDAO.getLocationName(master.getLocation()));
    		 emrForm.setNotes(master.getNotes());
    		 emrForm.setRegno(String.valueOf(parentid));
    		 emrForm.setClinicLogo(clinic.getUserImageFileName());
    		 if(!master.getPclientid().equals("0")){
    			 
    			 Priscription priscription = pharmacyDAO.getPharmacyPatient(master.getPclientid());
    			 emrForm.setWardname(priscription.getAddress());
 				 emrForm.setAgeandgender(priscription.getAgeandgender());
 				 emrForm.setPractitionerName(priscription.getPractitionername());
 				 emrForm.setFullname(priscription.getFullname());
 				 emrForm.setMobno(priscription.getMobile());
 				 emrForm.setAddress(priscription.getAddress());
 				 String clinicabrivation = clientDAO.getClinicAbrivation(loginInfo.getClinicid());
 				emrForm.setAbrivationid(clinicabrivation+"/"+master.getPclientid());
    		 } else {
    			 BedDao bedDao= new JDBCBedDao(connection);
 				Client client = clientDAO.getClientDetails(master.getClientId());
 				String name = client.getTitle()+" "+client.getFirstName()+" "+client.getLastName();
 				emrForm.setAbrivationid(client.getAbrivationid());
 				emrForm.setWardname(client.getAddress());
 				emrForm.setFullname(name);
 				String ipdid = pharmacyDAO.getIpdIdFromClientID(Integer.parseInt(master.getClientId()));
 				String pract_name ="";
 				if(ipdid.equals("0")){
 					pract_name = pharmacyDAO.getappointmentinfo(Integer.parseInt(master.getClientId()));
 				}else{
 					Bed  bed = bedDao.getEditIpdData(ipdid);
 					UserProfileDAO profileDAO = new JDBCUserProfileDAO(connection);
 					pract_name = profileDAO.getName(bed.getPractitionerid());
 				}
 				emrForm.setPractitionerName(pract_name);
 				String ipdoropd = "0";
 				if(ipdid.equals("0")){
 					ipdoropd ="0";
 				}else{
 					IpdDAO ipdDAO = new JDBCIpdDAO(connection);
 					Bed  bed = bedDao.getEditIpdData(ipdid);
 					String wardname = ipdDAO.getIpdWardName(bed.getBedid());
 					String bedname = ipdDAO.getIpdBedName(bed.getWardid());
 					ipdoropd = ipdid;
 					String wardbed = wardname +"/"+ bedname;
 					emrForm.setWardbed(wardbed);
 				}
 				emrForm.setIpdoropd(ipdoropd);
 				ThirdPartyDAO thirdPartyDAO = new JDBCThirdPartyDAO(connection);
 				String thirdPartyCompanyName="0";
 				if(client.getTypeName()==null){
 					client.setTypeName("0");
 				}
 				if(!client.getTypeName().equals("0")){
 					ThirdParty thirdParty =thirdPartyDAO.getThirdPartyDetails(client.getTypeName());
 					thirdPartyCompanyName = thirdParty.getCompanyName();
 				}else{
 					thirdPartyCompanyName ="0";
 				}
 				emrForm.setThirdPartyCompanyName(thirdPartyCompanyName);
    			emrForm.setAddress(client.getAddress());
    			emrForm.setMobno(client.getMobNo());
    		 }
    		 String paymode=master.getPaymode();
    		 String paynote =master.getPaynote();
    		 
    		 
    		 if(paynote!=null){
				  if(paynote.equals("")){
					  emrForm.setPaymode(paymode);
				  } else {
					  paymode=paymode+" ("+paynote+")";
					  emrForm.setPaymode(paymode);
				  }
			  } else {
				  emrForm.setPaymode(paymode);
			  }
				
				Location location = clinicDAO.getRegisterdLication();
				clinic.setLocationname(location.getAddress());
				emrForm.setClinicLogo(clinic.getUserImageFileName());
    		  UserProfile userProfile= userProfileDAO.getPharmacyUserDetails(loginInfo.getClinicid());
    		  emrForm.setCity(userProfile.getCity());
    		  emrForm.setPlace(userProfile.getCity());
			  emrForm.setClinicName(userProfile.getClinicname());
			  emrForm.setClinicaddress(userProfile.getAddress());
			  emrForm.setLandLine(userProfile.getPhone());
			  emrForm.setEmail(userProfile.getEmail());
			  emrForm.setPlace(userProfile.getCity());
			  emrForm.setClinicLogo(clinic.getUserImageFileName());
			  emrForm.setWebsiteUrl(userProfile.getWebsite());
			  emrForm.setInstruction1(userProfile.getInstruction1());
			  emrForm.setInstruction2(userProfile.getInstruction2());
			  emrForm.setInstruction3(userProfile.getInstruction3());
			  
			  emrForm.setInstruction4(userProfile.getInstruction4());
			  emrForm.setDlno(userProfile.getDlno());
			  emrForm.setTinno(userProfile.getTinno());
		} catch (Exception e) {

			e.printStackTrace();
		}
    	finally {
    		connection.close();
    	}
    	
    	return "printslip";
    }
    public String setReturn_StockAccess() throws Exception {
    	 if (!verifyLogin(request)) {

 			return "login";
 		}
		//@k@sh
	   Connection connection = null;
			 try {
				connection = Connection_provider.getconnection();
				PharmacyDAO pharmacyDAO = new JDBCPharmacyDAO(connection);
				String userid = request.getParameter("userid");
				String status = request.getParameter("status");
				
				 Calendar cal = Calendar.getInstance();
			        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss.SS");
			        String dateString = sdf.format(cal.getTime());
			        
					String Accessdatetime=dateString;
					String CurrentUser=loginInfo.getUserId();
					
				
				if(status.equals("0") || status=="0"){
					status = "1";
				}
				else {
					status = "0";
				}
				Priscription priscription = pharmacyDAO.setReturn_StockAccess(userid,status,Accessdatetime,CurrentUser);
				StringBuilder sb = new StringBuilder();
				if(priscription.getReturn_stock().equals("0")){
					sb.append("<input type='checkbox' onclick='setReturn_StockAccess("+priscription.getId()+","+priscription.getReturn_stock()+")' id='return_stock"+priscription.getId()+"' class='onoffswitch-checkbox'>");
					sb.append("<label class='onoffswitch-label' for='return_stock"+priscription.getId()+"'>");
					sb.append("<span class='onoffswitch-inner'></span>");
					sb.append("<span class='onoffswitch-switch'></span>");
					sb.append("</label>");
				}
				else
				{
					sb.append("<input type='checkbox' onclick='setReturn_StockAccess("+priscription.getId()+","+priscription.getReturn_stock()+")' id='return_stock"+priscription.getId()+"' class='onoffswitch-checkbox'  checked='checked'>");
					sb.append("<label class='onoffswitch-label' for='return_stock"+priscription.getId()+"'>");
					sb.append("<span class='onoffswitch-inner'></span>");
					sb.append("<span class='onoffswitch-switch'></span>");
					sb.append("</label>");
				}
				response.setContentType("text/html");
				response.setHeader("Cache-Control", "no-cache");
				response.getWriter().write(""+sb.toString()+"");
			} catch (Exception e) {
				e.printStackTrace();
			}finally{
				connection.close();
			}
			 return null;
	  }
    
    
    public String setcancel_indentaccess() throws Exception {
    	 if (!verifyLogin(request)) {

 			return "login";
 		}
		//@k@sh
	   Connection connection = null;
			 try {
				connection = Connection_provider.getconnection();
				PharmacyDAO pharmacyDAO = new JDBCPharmacyDAO(connection);
				String userid = request.getParameter("userid");
				String status = request.getParameter("status");
				
				Calendar cal = Calendar.getInstance();
			    SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
			    String dateString = sdf.format(cal.getTime());
			        
				String Accessdatetime=dateString;
				String CurrentUser=loginInfo.getUserId();
				
				if(status.equals("0") || status=="0"){
					status = "1";
				}
				else {
					status = "0";
				}
				Priscription priscription = pharmacyDAO.setCancel_IndentAccess(userid,status,Accessdatetime,CurrentUser);
				StringBuilder sb = new StringBuilder();
				if(priscription.getCancel_indent().equals("0")){
					sb.append("<input type='checkbox' onclick='setCancel_IndentAccess("+priscription.getId()+","+priscription.getCancel_indent()+")' id='cancel_indent"+priscription.getId()+"' class='onoffswitch-checkbox'>");
					sb.append("<label class='onoffswitch-label' for='cancel_indent"+priscription.getId()+"'>");
					sb.append("<span class='onoffswitch-inner'></span>");
					sb.append("<span class='onoffswitch-switch'></span>");
					sb.append("</label>");
				}
				else
				{
					sb.append("<input type='checkbox' onclick='setCancel_IndentAccess("+priscription.getId()+","+priscription.getCancel_indent()+")' id='cancel_indent"+priscription.getId()+"' class='onoffswitch-checkbox'  checked='checked'>");
					sb.append("<label class='onoffswitch-label' for='cancel_indent"+priscription.getId()+"'>");
					sb.append("<span class='onoffswitch-inner'></span>");
					sb.append("<span class='onoffswitch-switch'></span>");
					sb.append("</label>");
				}
				response.setContentType("text/html");
				response.setHeader("Cache-Control", "no-cache");
				response.getWriter().write(""+sb.toString()+"");
			} catch (Exception e) {
				e.printStackTrace();
			}finally{
				connection.close();
			}
			 return null;
	  }
    
    public String setreturn_medicineaccess() throws Exception {
    	 if (!verifyLogin(request)) {

 			return "login";
 		}
		//@k@sh
	   Connection connection = null;
			 try {
				connection = Connection_provider.getconnection();
				PharmacyDAO pharmacyDAO = new JDBCPharmacyDAO(connection);
				String userid = request.getParameter("userid");
				String status = request.getParameter("status");
				
				Calendar cal = Calendar.getInstance();
			    SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
			    String dateString = sdf.format(cal.getTime());
			        
				String Accessdatetime=dateString;
				String CurrentUser=loginInfo.getUserId();
				
				if(status.equals("0") || status=="0"){
					status = "1";
				}
				else {
					status = "0";
				}
				Priscription priscription = pharmacyDAO.setReturn_MedicineAccess(userid,status,Accessdatetime,CurrentUser);
				StringBuilder sb = new StringBuilder();
				if(priscription.getReturn_medicine().equals("0")){
					sb.append("<input type='checkbox' onclick='setReturn_MedicineAccess("+priscription.getId()+","+priscription.getReturn_medicine()+")' id='return_medicine"+priscription.getId()+"' class='onoffswitch-checkbox'>");
					sb.append("<label class='onoffswitch-label' for='return_medicine"+priscription.getId()+"'>");
					sb.append("<span class='onoffswitch-inner'></span>");
					sb.append("<span class='onoffswitch-switch'></span>");
					sb.append("</label>");
				}
				else
				{
					sb.append("<input type='checkbox' onclick='setReturn_MedicineAccess("+priscription.getId()+","+priscription.getReturn_medicine()+")' id='return_medicine"+priscription.getId()+"' class='onoffswitch-checkbox'  checked='checked'>");
					sb.append("<label class='onoffswitch-label' for='return_medicine"+priscription.getId()+"'>");
					sb.append("<span class='onoffswitch-inner'></span>");
					sb.append("<span class='onoffswitch-switch'></span>");
					sb.append("</label>");
				}
				response.setContentType("text/html");
				response.setHeader("Cache-Control", "no-cache");
				response.getWriter().write(""+sb.toString()+"");
			} catch (Exception e) {
				e.printStackTrace();
			}finally{
				connection.close();
			}
			 return null;
	  }
    
     public String getbincardreport(){
    	 return null;
     }
     
     
     
     public String setallmedicines() throws Exception {
    	 if (!verifyLogin(request)) {
 			return "login";
 		 }
    	Connection connection=null; 
    	try {
    		connection=Connection_provider.getconnection();
    		String clientid= request.getParameter("clientid");
    		String ispharmacy= request.getParameter("ispharmacy");
    		PharmacyDAO pharmacyDAO= new JDBCPharmacyDAO(connection);
    		
    		String location =(String) session.getAttribute("location");
			if(location==null){
				location="0";
			}
    		
    		ArrayList<Product> allMedicieneList = pharmacyDAO.getAllMedicinesofClient(clientid,ispharmacy,location,0);
    		
    		StringBuffer buffer= new StringBuffer();
    		buffer.append("<select class='form-control chosen' id='newmedicine' name='mdicinename'  >");
    		buffer.append("<option value='0'>Select Product To Return</option>");
    		for(Product product:allMedicieneList){
    			
    			  buffer.append("<option value='"+product.getId()+"'>"+product.getGenericname()+"</option>");
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
     
     public String addtoreturn() throws Exception{
    	 if (!verifyLogin(request)) {
  			return "login";
  		 }
         Connection connection=null;
         try {
          connection=Connection_provider.getconnection();
          InventoryProductDAO inventoryProductDAO=new JDBCInventoryProductDAO(connection);
          PharmacyDAO pharmacyDAO= new JDBCPharmacyDAO(connection);
          String chargeid=request.getParameter("chargeid");
          String tcount=request.getParameter("count");
          String qty=request.getParameter("qty");
          Priscription priscription= pharmacyDAO.getMedicineChargesbyid(Integer.parseInt(chargeid),0);
          Product product=inventoryProductDAO.getProduct(priscription.getProduct_id());
          String medicine_shedule= inventoryProductDAO.getMedicineShedule(product.getCatalogueid());
          
          int returnqty= pharmacyDAO.getReturnQtyofCharge(chargeid); 
          
          
          if(product.getId()>0){
           
          String ex=product.getExpiry_date();
          String expiry=DateTimeUtils.converToMMMYYYYforExp(ex);
          int count=Integer.parseInt(tcount);
          String color="#777";
          if(medicine_shedule.equals("Regular")) {
            color="#777";
          } else if(medicine_shedule.equals("Narcotics")){
            color="#e05d6f";
          } else if(medicine_shedule.equals("H1")){
            color="#e69522";
          }else if(medicine_shedule.equals("High Risk Medicine")){
         	 color="#9381cc";
          }else if(medicine_shedule.equals("Vaccination")){
         	 color="#e0acdafc";
          }
          returnqty =priscription.getSaleqty()- returnqty; 
          int sr=count+1;
          StringBuffer buffer=new StringBuffer();
          buffer.append("<td>"+sr+"</td>");
          buffer.append("<td style=color:"+color+" '>"+product.getProduct_name()+" <input type='hidden' class='pclass' value='"+count+"' /> <input type='hidden' name='medicines["+count+"].id' id='id"+count+"'  value='"+chargeid+"' /> <input type='hidden' name='medicines["+count+"].product_id' id='product_id"+count+"' value='"+product.getId()+"' /> </td>");
          buffer.append("<td id='labelreq"+count+"'>"+priscription.getSaleqty()+"</td> <input type='hidden' name='medicines["+count+"].tempsale' value='"+returnqty+"' id='tempsale"+count+"' />");
         /* buffer.append("<td> "+product.getHsnno()+" </td>");*/
          /*buffer.append("<td>"+returnqty+"/"+product.getBatch_no()+"/"+expiry+" <input type='hidden' id='pur_price"+count+"' value='"+product.getPurchase_price()+"' /> <input type='hidden' value='"+product.getPack()+"' id='pack"+count+"'/> <input type='hidden' name='medicines["+count+"].billno' id='billno"+count+"' value='"+priscription.getBillno()+"' /> </td>");*/
          buffer.append("<input type='hidden' id='pur_price"+count+"' value='"+product.getPurchase_price()+"' /> <input type='hidden' value='"+product.getPack()+"' id='pack"+count+"'/> <input type='hidden' name='medicines["+count+"].billno' id='billno"+count+"' value='"+priscription.getBillno()+"' /> ");
          
          buffer.append("<td>"+returnqty+"</td>");
          buffer.append("<td>"+product.getBatch_no()+"</td>");
          buffer.append("<td>"+expiry+"</td>");
          buffer.append("<td><input type='number' name='medicines["+count+"].returnqty' onchange='calRefundTotaltemp()' id='returnqty"+count+"' value='"+qty+"' class='form-control' style='height: 20px !important;background-color: rgba(255, 225, 225, 0.59);'></td>");
          if(loginInfo.isPurchase_edit_pharmacy()){
        	  buffer.append("<td><input type='number' value='"+priscription.getSale_price()+"' name='medicines["+count+"].sale_price' onchange='calRefundTotaltemp()' id='mrp"+count+"' class='form-control' style='height: 20px !important;text-align: right;background-color: rgba(255, 225, 225, 0.59);' /> <input type='hidden' value='"+priscription.getSale_price()+"' id='sale_price"+count+"' /> </td>");
          }else{
        	  buffer.append("<td><input type='number' value='"+priscription.getSale_price()+"' name='medicines["+count+"].sale_price' onchange='calRefundTotaltemp()' id='mrp"+count+"' class='form-control' style='height: 20px !important;text-align: right;background-color: rgba(255, 225, 225, 0.59);' readonly/> <input type='hidden' value='"+priscription.getSale_price()+"' id='sale_price"+count+"' /> </td>");  
          }
          buffer.append("<td style='text-align: center;text-transform: uppercase;'>"+product.getShelf()+" <input type='hidden' name='medicines["+count+"].vat' id='vat"+count+"' value='"+product.getVat()+"'> </td>");
          buffer.append("<td  class='text-right'>Rs.<label id='totalmrp"+count+"'>00.00</label> ");
          buffer.append("<input type='hidden' id='prodd"+count+"' value='"+product.getStock()+"'/>");
          buffer.append("<input type='hidden' id='discper"+count+"' name='medicines["+count+"].indidiscount' value='"+priscription.getDiscount()+"'/>");
          buffer.append("</td>");
          double stock=Double.parseDouble(product.getStock());
          if(stock>100.0){
        	  buffer.append("<td class='text-center' id='tdbutton"+count+"'><a href='#' onclick=deleteRowtemp(this)><i class='fa fa-minus-circle' style='color:#c50404;font-size: 17px;padding-top: 2px;' aria-hidden='true'></i></a></td>");
          } else {
        	  buffer.append("<td class='text-center'><a href='#' onclick=deleteRowtemp(this)><i class='fa fa-minus-circle' style='color:#c50404;font-size: 17px;padding-top: 2px;' aria-hidden='true'></i></a></td><td class='text-center'><a href='#' onclick='requestStock("+product.getId()+","+count+")' title='Request Stock'><i class='fa fa-cart-plus' aria-hidden='true' style='font-size: 17px;padding-top: 2px;'></i></a></td>");
          }
          buffer.append("<input type='hidden' id='totalrefundrs"+count+"' name='medicines["+count+"].totalrefundrs'  />");
          buffer.append("<input type='hidden' id='tmedicineid"+count+"' name='medicines["+count+"].mdicinenameid' value='"+product.getMedicinenameid()+"' />");
          
          response.setContentType("text/html");
          response.setHeader("Cache-Control", "no-cache");
          response.getWriter().write(""+buffer.toString()+""); 
          
          } else {
           response.setContentType("text/html");
           response.setHeader("Cache-Control", "no-cache");
           response.getWriter().write("0"); 
          }
          
         } catch (Exception e) {

          e.printStackTrace();
         }
         finally {
          connection.close();
         }
         
         return null;
        }
     
     public String getallbillforprint() throws Exception{
    	 if (!verifyLogin(request)) {
  			return "login";
  		 }
    	Connection connection = null; 
    	try {
			connection = Connection_provider.getconnection();
			 PharmacyDAO pharmacyDAO=new JDBCPharmacyDAO(connection);
			 ClientDAO clientDAO=new JDBCClientDAO(connection);
			 String clientid=request.getParameter("clientid");
			 String flag=request.getParameter("flag");
			 String fromdate=request.getParameter("fromdate");
			 String todate=request.getParameter("todate");
			 if(fromdate!=null){
				 if(fromdate.equals("")){
					 fromdate=null;
				 }else{
					 fromdate=DateTimeUtils.getCommencingDate1(fromdate);
				 }
			 }
			 
			 if(todate!=null){
				 if(todate.equals("")){
					 todate=null;
				 }else{
					 todate=DateTimeUtils.getCommencingDate1(todate);
				 }
			 }
			
			
			 StringBuffer buffer=new StringBuffer();
			 String ipdopdnew="-";
			 //Mrs. Khushi Gupta | Female/20 | GENRAL/18 | 9568245625
			 String data="";
			 boolean isIpd=false;
			 double balance=0;
			 String fullname="";
			 String address="";
			 String mobile="";
			 String admissionDate="";
			 double cardAmount=0;
			 double chequeAmount=0;
			 double neftAmount=0;
			 double hospital=0;
			 if(flag.equals("1")){
				 
				 Priscription client=pharmacyDAO.getPharmacyPatient(clientid);
				 data=client.getFullname()+" | "+client.getAgeandgender()+" | "+client.getAddress()+" | "+client.getMobile();
				 balance= Double.parseDouble(client.getBalance());
				 fullname= client.getFullname();
				 address= client.getAddress();
				 mobile= client.getMobile();
				 admissionDate=DateTimeUtils.getCommencingDate1(client.getDate());
				/* cardAmount= pharmacyDAO.getCardPaymentAmt(fromdate, todate, clientid, 1);
				 chequeAmount= pharmacyDAO.getChequePaymentAmt(fromdate, todate, clientid, 1);
				 neftAmount= pharmacyDAO.getNeftPaymentAmt(fromdate, todate, clientid, 1);
				 hospital= pharmacyDAO.getHospitalPaymentAmt(fromdate, todate, clientid, 1);*/
			 } else {
				 Client client=clientDAO.getClientDetails(clientid);
				 String warlocation=pharmacyDAO.getwardlocationofClient(clientid);
				 fullname=client.getTitle()+" "+client.getFirstName()+" "+client.getLastName();
				 isIpd= warlocation.contains("/");
				 data=fullname+" | "+client.getGender()+"/"+client.getAge()+" | "+warlocation+" | "+client.getMobNo();
				 address= client.getAddress();
				 mobile= client.getMobNo();
				 balance= Double.parseDouble(client.getBalance());
				 if(isIpd){
					 String ipdid = pharmacyDAO.getIpdIdOfClient(clientid);
					 BedDao bedDao= new JDBCBedDao(connection);
					 Bed bed=bedDao.getEditIpdData(ipdid);
					 admissionDate= bed.getAdmissiondate();
					 ipdopdnew="IPD- "+ipdid;
				 }else {
					 ipdopdnew="OPD";
				 }
				/* cardAmount= pharmacyDAO.getCardPaymentAmt(fromdate, todate, clientid, 0);
				 chequeAmount= pharmacyDAO.getChequePaymentAmt(fromdate, todate, clientid, 0);
				 neftAmount= pharmacyDAO.getNeftPaymentAmt(fromdate, todate, clientid, 0);
				 hospital= pharmacyDAO.getHospitalPaymentAmt(fromdate, todate, clientid, 0);*/
			 }
			 buffer.append(data+"~");
			 
			 double totalbill=0;
			 double totaldisc=0;
			 double totalreturn=0;
			 double totalcash=0;
			 double totalbalance =0;
			 
			 int orderby =0;
	    		if(loginInfo.isPhar_print_seq()){
	    			orderby =1;
	    		}
			 ArrayList<Priscription> allBillList= pharmacyDAO.getAllBillListofClientNew(clientid,flag,fromdate,todate,orderby); 
		
			 int size = allBillList.size();
				if (size > 0) {
					totalbill = allBillList.get(size - 1).getTotalamt();
					totalreturn = allBillList.get(size - 1).getTotalReturn();
					totaldisc = allBillList.get(size - 1).getTotaldisc();
					totalbalance = allBillList.get(size - 1).getTotalBalance();
				}
			 
				String billamt= DateTimeUtils.changeFormat(totalbill);
				 String str[]= billamt.split("\\.");
				 String roundOf="00.00";
				 if(str.length>1){
					 roundOf=str[1];
					 roundOf="00."+roundOf;
				 }
				 
				 double netamt=totalbill-totaldisc-totalreturn;
				 netamt =netamt- Double.parseDouble(roundOf);
				 netamt =Math.round(netamt*100.0)/100.0;
			emrForm.setTotalbill(String.valueOf(Math.round(totalbill*100.0)/100.0));
			emrForm.setTotalreturn(String.valueOf(totalreturn));
			emrForm.setTotaldisc(String.valueOf(totaldisc));
			/*emrForm.setBalance(String.valueOf(balance));*/
			emrForm.setBalance(String.valueOf(totalbalance));
			emrForm.setRoundOf(roundOf);
			emrForm.setNetamt(String.valueOf(netamt));
			 emrForm.setFullinfo(data);
			 emrForm.setAllBillList(allBillList);
			 UserProfileDAO userProfileDAO= new JDBCUserProfileDAO(connection);
			 UserProfile userProfile= userProfileDAO.getPharmacyUserDetails(loginInfo.getClinicid());
			 emrForm.setClinicName(userProfile.getClinicname());
			 emrForm.setClinicaddress(userProfile.getAddress());
			 emrForm.setWebsiteUrl(userProfile.getWebsite());
			 emrForm.setClinicemail(userProfile.getEmail());
			 emrForm.setClinicphoneno(userProfile.getPhone());
			 
			 emrForm.setClientId(clientid);
			 emrForm.setFullname(fullname);
			 emrForm.setAddress(address);
			 emrForm.setMobno(mobile);
			 emrForm.setIsIpd(String.valueOf(isIpd));
			 emrForm.setAdmissionDate(admissionDate);
    	} catch (Exception e) {
			e.printStackTrace();
		}finally {
			connection.close();
		}
    	 return "getallbillforprint";
     }
     
     public String setindent_approveaccess() throws Exception {
    	 if (!verifyLogin(request)) {
  			return "login";
  		 }
 		//@k@sh
 	   Connection connection = null;
 			 try {
 				connection = Connection_provider.getconnection();
 				PharmacyDAO pharmacyDAO = new JDBCPharmacyDAO(connection);
 				String userid = request.getParameter("userid");
 				String status = request.getParameter("status");
 				
 				Calendar cal = Calendar.getInstance();
 			    SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
 			    String dateString = sdf.format(cal.getTime());
 			        
 				String Accessdatetime=dateString;
 				String CurrentUser=loginInfo.getUserId();
 				
 				if(status.equals("0")){
 					status = "1";
 				}
 				else {
 					status = "0";
 				}
 				Priscription priscription = pharmacyDAO.setindent_approveAccess(userid,status,Accessdatetime,CurrentUser);
 				StringBuilder sb = new StringBuilder();
 				if(priscription.getIndent_approve().equals("0")){
					sb.append("<input type='checkbox' onclick='setIndent_approveAccess("+priscription.getId()+","+priscription.getIndent_approve()+")' id='indent_approve"+priscription.getId()+"' class='onoffswitch-checkbox'>");
					sb.append("<label class='onoffswitch-label' for='indent_approve"+priscription.getId()+"'>");
					sb.append("<span class='onoffswitch-inner'></span>");
					sb.append("<span class='onoffswitch-switch'></span>");
					sb.append("</label>");
				}
				else
				{
					sb.append("<input type='checkbox' onclick='setIndent_approveAccess("+priscription.getId()+","+priscription.getIndent_approve()+")' id='indent_approve"+priscription.getId()+"' class='onoffswitch-checkbox'  checked='checked'>");
					sb.append("<label class='onoffswitch-label' for='indent_approve"+priscription.getId()+"'>");
					sb.append("<span class='onoffswitch-inner'></span>");
					sb.append("<span class='onoffswitch-switch'></span>");
					sb.append("</label>");
				}
 				response.setContentType("text/html");
 				response.setHeader("Cache-Control", "no-cache");
 				response.getWriter().write(""+sb.toString()+"");
 			} catch (Exception e) {
 				e.printStackTrace();
 			}finally{
 				connection.close();
 			}
 			 return null;
 	  }
     
     public String productwisereturnreport() throws Exception{
    	 if (!verifyLogin(request)) {
  			return "login";
  		 }
    	 Connection connection = null;
 		try {
 			/*ArrayList<Breadcrumbs> indentflowlist = new ArrayList<Breadcrumbs>();
			ArrayList<Breadcrumbs> indentflowlisttemp = new ArrayList<Breadcrumbs>();
			if (session.getAttribute("indentflowlist") != null) {
				indentflowlisttemp = (ArrayList<Breadcrumbs>) session.getAttribute("indentflowlist");
			}
			boolean isavilablemodule= false;
			int modulecount =0;
			for (Breadcrumbs breadcrumbs : indentflowlisttemp) {
				breadcrumbs.setIscurrent(false);
				breadcrumbs.setSqno(modulecount);
				modulecount++;
				if(breadcrumbs.getName().equals("Product Wise Return Report")){
					isavilablemodule =true;
					breadcrumbs.setIscurrent(true);
					indentflowlist.add(breadcrumbs);
					break;
				}else{
					indentflowlist.add(breadcrumbs);
				}
			}
			if(!isavilablemodule){
				Breadcrumbs breadcrumbs = new Breadcrumbs();
				breadcrumbs.setName("Product Wise Return Report");
				breadcrumbs.setOn(true);
				breadcrumbs.setSqno(modulecount);
				breadcrumbs.setUrllink("productwisereturnreportPharmacy");
				breadcrumbs.setIscurrent(true);
				breadcrumbs.setShowingname("Product Wise Return Report");
				indentflowlist.add(breadcrumbs);
			}
			session.setAttribute("indentflowlist",indentflowlist);*/
 			
 			
 			//  16 feb 2018 Prepare Billing Report
 			connection = Connection_provider.getconnection();
 			PharmacyDAO pharmacyDAO = new JDBCPharmacyDAO(connection);
 			String fromdate = emrForm.getFromDate();
 			String todate = emrForm.getToDate();
 			
 			if(fromdate == null){
 				DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
 				Calendar cal = Calendar.getInstance();
 				fromdate = dateFormat.format(cal.getTime());   
 				fromdate = DateTimeUtils.getCommencingDate1(fromdate);
 			}else{
 				if(fromdate.equals("")) {
 					DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
 				    Calendar cal = Calendar.getInstance();
 				    fromdate = dateFormat.format(cal.getTime());   
 				    fromdate = DateTimeUtils.getCommencingDate1(fromdate);
 				} else {
 				    fromdate = DateTimeUtils.getCommencingDate1(fromdate);   
 				}  
 			}
 			if(todate== null){
 				DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
 				Calendar cal = Calendar.getInstance(); 
 				todate = dateFormat.format(cal.getTime());
 				todate = DateTimeUtils.getCommencingDate1(todate);
 			}else{
 				if(todate.equals("")){
 				    DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
 				    Calendar cal = Calendar.getInstance(); 
 				    todate = dateFormat.format(cal.getTime());
 				    todate = DateTimeUtils.getCommencingDate1(todate);
 				} else {
 					todate = DateTimeUtils.getCommencingDate1(todate);
 				}
 			}
 			
 			ChargesReportDAO chargesReportDAO = new JDBCChargesReportDAO(connection);
			DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		    Calendar cal = Calendar.getInstance();
		    String date = dateFormat.format(cal.getTime());  
		     
			int result = chargesReportDAO.saveMisReportLog("Product wise Return Report",loginInfo.getUserId(),fromdate,todate,date,"productwisereturnreport");
 			ArrayList<Priscription> productwisereturnreport = pharmacyDAO.getProductwiseReportList(fromdate,todate);
 			
 			int size = productwisereturnreport.size();
			if (size > 0) {
				int totalqty = productwisereturnreport.get(size - 1).getTotalqty();
				double actualtemptot = productwisereturnreport.get(size-1).getActualtemptot();
				emrForm.setTotalqty(totalqty);
				emrForm.setActualtemptot(actualtemptot);
			} else {
				emrForm.setActualtemptot(0.0);
				emrForm.setTotalqty(0);
			}
 			
 			
 			emrForm.setProductwisereturnreport(productwisereturnreport);
 			emrForm.setFromDate(DateTimeUtils.getCommencingDate1(fromdate));
 			emrForm.setToDate(DateTimeUtils.getCommencingDate1(todate));
 			
 		} catch (Exception e) {
 			e.printStackTrace();
 		}finally {
 			connection.close();
 		}
 		return "productwisereturnreport";
     }
     
     public String openmultiplerequest() throws Exception{
    	 if (!verifyLogin(request)) {
  			return "login";
  		 }
    	//@k@sh 20 June 2018
   	   	Connection connection = null;
		 try {
			connection = Connection_provider.getconnection();
			PharmacyDAO pharmacyDAO = new JDBCPharmacyDAO(connection);
			PrescriptionDAO prescriptionDAO = new JDBCPrescriptionDAO(connection);
			String id = request.getParameter("id");
			String location =(String)session.getAttribute("location");
			if(location==null){
				location= "0";
			}
			
			ArrayList<Priscription> arrayList = prescriptionDAO.getMultiplePriscRequest(id,location);
			StringBuffer buffer = new StringBuffer();
			int srno =0;
			for (Priscription priscription : arrayList) {
				buffer.append("<tr>");
				buffer.append("<td>"+(++srno)+"</td>");
				buffer.append("<td>"+priscription.getDate()+"</td>");
				if(priscription.getStatus().equals("0")){
					buffer.append("<td>"+priscription.getId()+"</td>");
				}else{
					buffer.append("<td>"+priscription.getId()+"</td>");
				}
				buffer.append("<td>"+priscription.getBillno()+"</td>");
				if(DateTimeUtils.isNull(priscription.getTokenno()).equals("")){
					buffer.append("<td></td>");
				}else{
					buffer.append("<td>TN-"+priscription.getTokenno()+"</td>");
				}
				buffer.append("<td><a href='#' onclick=openPopup('printpriscEmr?selectedid="+id+"&newparentid="+priscription.getId()+"')><i class='fa fa-print'></i></a></td>");
				buffer.append("<td><a title='Save As CSV' onclick='printPriscReqCSV("+id+","+priscription.getId()+")' ><i class='fa fa-file-excel-o'></i></a></td>");
				if(priscription.getStatus().equals("0")){
					buffer.append("<td style='text-align:center;background-color: rgba(255, 163, 163, 0.93);'><a href='#' style='color:#fff;' onclick=openSamePopup('checkavailabilitynewPharmacy?selectedid="+priscription.getId()+"&oldparentid="+id+"')>Ready To Sale </a></td>");
				}else{
					buffer.append("<td style='text-align:center;background-color: rgba(229, 217, 129, 0.92);'>Delivered</td>");
				}
				buffer.append("</tr>");
				
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
     
     public String checkavailabilitynew() throws Exception{
    	 if (!verifyLogin(request)) {
  			return "login";
  		 }
 		Connection connection=null;
 		try {
 			int selectedid=Integer.parseInt(request.getParameter("selectedid"));
 			int oldparentid = Integer.parseInt(request.getParameter("oldparentid"));
 			
 			/*ArrayList<Breadcrumbs> indentflowlist = new ArrayList<Breadcrumbs>();
			ArrayList<Breadcrumbs> indentflowlisttemp = new ArrayList<Breadcrumbs>();
			if (session.getAttribute("indentflowlist") != null) {
				indentflowlisttemp = (ArrayList<Breadcrumbs>) session.getAttribute("indentflowlist");
			}
			boolean isavilablemodule= false;
			int modulecount =0;
			for (Breadcrumbs breadcrumbs : indentflowlisttemp) {
				breadcrumbs.setIscurrent(false);
				breadcrumbs.setSqno(modulecount);
				modulecount++;
				if(breadcrumbs.getName().equals("Online Pharmacy Sale")){
					isavilablemodule =true;
					breadcrumbs.setIscurrent(true);
					indentflowlist.add(breadcrumbs);
					break;
				}else{
					indentflowlist.add(breadcrumbs);
				}
			}
			if(!isavilablemodule){
				Breadcrumbs breadcrumbs = new Breadcrumbs();
				breadcrumbs.setName("Online Pharmacy Sale");
				breadcrumbs.setOn(false);
				breadcrumbs.setSqno(modulecount);
				breadcrumbs.setUrllink("checkavailabilitynewPharmacy?selectedid="+selectedid+"&oldparentid="+oldparentid+"");
				breadcrumbs.setIscurrent(true);
				breadcrumbs.setShowingname("Online Pharmacy Sale");
				indentflowlist.add(breadcrumbs);
			}
			session.setAttribute("indentflowlist",indentflowlist);*/
 			
 			
 			connection=Connection_provider.getconnection();
 			connection = Connection_provider.getconnection();
 			EmrDAO emrDAO = new JDBCEmrDAO(connection);
 			PharmacyDAO pharmacyDAO=new JDBCPharmacyDAO(connection);
 			UserProfileDAO userProfileDAO=new JDBCUserProfileDAO(connection);
 			ClientDAO clientDAO = new JDBCClientDAO(connection);
 			PrescriptionDAO prescriptionDAO=new JDBCPrescriptionDAO(connection);
 			
 			String loc= (String) session.getAttribute("location");
 			if(loc==null){
 				loc="0";
 			}
 			emrForm.setLocation(loc);
 			
 			//  16 Nov 2018 
			//delete previous data of user using login session
			int res = pharmacyDAO.deleteTempPharmacySession(loginInfo.getLoginsessionid());
 			
 			
 			Priscription priscription = emrDAO.getPriscriptionParentData(oldparentid);
 			session.setAttribute("parentpriscdata", priscription);
// 			System.out.println(priscription.getClientId());
 			String lastmodified="";
 			String time="";
 			if(priscription.getLastmodified()!=null){
 				String dateTime= priscription.getLastmodified();
 				lastmodified=dateTime.split(" ")[0];
 				time=dateTime.split(" ")[1];
 				priscription.setLastmodified(lastmodified);
 			}
 			
 			Client client = clientDAO.getPatient(Integer.parseInt(priscription.getClientId()));
 			String name = client.getTitle()+" "+client.getFirstName()+" "+client.getMiddlename()+" "+client.getLastName();
 			emrForm.setFullname(name);
 			String date  = DateTimeUtils.getDashboardTodayDate(loginInfo.getCountry());
 			/*String todaydate=DateTimeUtils.getCommencingDatePicker(date);
 			emrForm.setDateTime(todaydate);*/
 			String todaydate=DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone()).split(" ")[0];
 			emrForm.setDateTime(DateTimeUtils.getCommencingDate1(todaydate));
 			emrForm.setSelectedid(selectedid);
 			emrForm.setOldparentid(""+oldparentid);
 			emrForm.setNewparentid(selectedid);
 			try {

 				String dobyear[] = client.getDob().split("/");
 				String curryear[] = date.split("/");
 				
 				int  age = Integer.parseInt(curryear[2]) - Integer.parseInt(dobyear[2]);
 				client.setAge(age);
 				String agedate=client.getGender()+"/"+age;
 				emrForm.setAgeandgender(agedate);
 			} catch (Exception e) {

 			}
 			session.setAttribute("clientinfo", client);
 			
 			// get clinic info
 			
 			Clinic clinic = new Clinic();
 			ClinicDAO clinicDAO  = new JDBCClinicDAO(connection);
 			clinic = clinicDAO.getCliniclistDetails(loginInfo.getClinicid());
 			
 			Location location = clinicDAO.getRegisterdLication();
 			clinic.setLocationname(location.getAddress());
 			
 			if(!DateTimeUtils.isNull(priscription.getPrectionerid()).equals("")){
				UserProfile userProfile=userProfileDAO.getUserprofileDetails(Integer.parseInt(priscription.getPrectionerid()));
				String fullname= userProfile.getInitial()+" "+userProfile.getFirstname()+" "+userProfile.getLastname();
				emrForm.setPractitionerName(fullname);
				emrForm.setPhar_practid(priscription.getPrectionerid());
 			}else{
 				emrForm.setPhar_practid("0");
 			}
 			
 			clinic.setCurDateTime(priscription.getLastmodified());
 			
 			//int resss = pharmacyDAO.deleteTempChargePharmacySession(loginInfo.getLoginsessionid(),priscription.getClientId(),0);
 			/*int res2 = pharmacyDAO.deleteFromSessionLog(loginInfo.getLoginsessionid()); 
 			
 			int res1 = pharmacyDAO.deleteTempPharmacyData(loginInfo.getLoginsessionid());*/
 			
 			int res1 = pharmacyDAO.deleteTempPharmacySaleData(loginInfo.getLoginsessionid(),priscription.getClientId(),0);
			int resss = pharmacyDAO.deleteTempChargePharmacySessionLog(loginInfo.getLoginsessionid(),priscription.getClientId(),0);
 			
 			String todate = DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone());
 			ArrayList<Priscription>priscList = pharmacyDAO.getPriscPharmacyAvailablityNew(Integer.toString(selectedid),loginInfo.getLoginsessionid(),todate,loc);
 			
 			/*ArrayList<Priscription> prisMoleculessList = pharmacyDAO.getPriscAvailableByMolecules(Integer.toString(selectedid));
 			emrForm.setPrisMoleculessList(prisMoleculessList);*/
 			emrForm.setPriscriptionlist(priscList);
 			session.setAttribute("listAvailable", priscList);
 			
 			double subtotal=0;
 			for(Priscription priscription2:priscList){
 				
 				 subtotal=priscription2.getSubtotal();
 			}
 			double vat =subtotal*6/100;
 			emrForm.setVat(DateTimeUtils.changeFormat(vat));
 			emrForm.setDiscount("0.0");
 			String ipdid = emrDAO.getpriscIpdId(selectedid);
 			BedDao bedDao = new JDBCBedDao(connection);
 			IpdDAO  ipdDAO = new JDBCIpdDAO(connection);
 			
 			String wardname ="";
 			String bedname="";
 			String bedid ="0";
 			String wardid="0";
 			emrForm.setIpdid(ipdid);
 			double bydefaultdiscount =0;
 			int discountpatienttype =0;
 			if(ipdid==null || ipdid.equals("0")){
 				priscription.setIpdid("0");
 				bydefaultdiscount = loginInfo.getOpddiscount();
 			}else{
 				priscription.setIpdid(ipdid);
 				Bed bed = bedDao.getEditIpdData(ipdid);
 				wardname=ipdDAO.getIpdWardName(bed.getWardid());
 	 			bedname = ipdDAO.getIpdBedName(bed.getBedid());
 	 			bedid = bed.getBedid();
 	 			wardid = bed.getWardid();
 	 			bydefaultdiscount = loginInfo.getIpddiscount();
 	 			
 			}
 			if(DateTimeUtils.isNull(priscription.getLocation()).equals("IPD")){
 				discountpatienttype=1;
 			}
 			emrForm.setDiscountpatienttype(discountpatienttype);
 			priscription.setWardname(wardname);
 			priscription.setBedname(bedname);
 			emrForm.setPhar_ipdid(priscription.getIpdid());
 			emrForm.setPhar_bedid(wardid);
 			emrForm.setPhar_bedid(bedid);
 			String payee="Self";
 			if(client.getWhopay()!=null){
 				if(client.getWhopay().equals("Third Party")){
 					 /*payee= client.getTpDetails().getCompanyName();*/
 					payee= "Third Party";
 				}
 			}
 			
 			emrForm.setWhopay(payee);
 			emrForm.setAbrivationid(client.getAbrivationid());
 			emrForm.setInvoiceTime(time);
 			String prebal = pharmacyDAO.getbalofpatient(priscription.getClientId());
 			emrForm.setPrebal(prebal);
 			emrForm.setWardname(wardname);
 			emrForm.setBedname(bedname);
 			String wardbed=wardname+"/"+bedname;
 			emrForm.setWardname(wardbed);
 			emrForm.setIpdidcheck(priscription.getIpdid());
 			emrForm.setIpdid(priscription.getIpdid());
 		    emrForm.setOpdid(priscription.getClientId());
 		    emrForm.setHdnphclientid(priscription.getClientId());
 		    emrForm.setHdnispharmacy("0");
 		    emrForm.setMobno(client.getMobNo());
 		    emrForm.setPriscid(String.valueOf(selectedid));
 			//String prebalance = pharmacyDAO.getbalancepri();
 		    
 		    InventoryProductDAO inventoryProductDAO=new JDBCInventoryProductDAO(connection);
 			//ArrayList<Product> inventoryPriscList= inventoryProductDAO.geProductList("2",loc);
 			ArrayList<Product> inventoryPriscList =new ArrayList<Product>();
 			if(!loc.equals("0")){
 				if(loginInfo.isPhar_batchwise_sale()){
 					inventoryPriscList = inventoryProductDAO.getCatalogueListForSale("2",loc);
 				}else{
 					inventoryPriscList= inventoryProductDAO.geProductList("2",loc);
 				}
 			}
 			emrForm.setInventoryPriscList(inventoryPriscList);
 			
 			Priscription master = pharmacyDAO.getPharacyUsrLInfo(loginInfo.getUserId());
 			
 			if(master.getAccess_back_date()==1){
 				 emrForm.setBack_date_access("1");
 			}
 			if(master.getTpbill()==1){
 				emrForm.setTp_bill_access("1");
 			}
 		    emrForm.setIsnewcheckavailability("1");
 		    double balance = pharmacyDAO.getPharClientBalance("0", priscription.getClientId());
 		    emrForm.setPhar_pre_balance(DateTimeUtils.changeFormat(balance));
 		   
 		   UserProfile profile2 = userProfileDAO.getPharmacyUserDetails(loginInfo.getClinicid());
 		   emrForm.setNonsystembarcode(profile2.isNonsystembarcode());
 		   
 		   String tokenno = prescriptionDAO.getPriscTokenNo(selectedid);
 		   emrForm.setTokenno(tokenno);
 		   
 		   int ressul1 = prescriptionDAO.updatePriscTokenStatus(selectedid,1);
 		   
 		} catch (Exception e) {

 			e.printStackTrace();
 		}
 		finally {
 			connection.close();
 		}
 		
 		
 		return "checkavailability";
 	}
     
     
     public String setpo_approveaccess() throws Exception {
    	 if (!verifyLogin(request)) {
  			return "login";
  		 }
  		//@k@sh 26 June 2018
  	   Connection connection = null;
  			 try {
  				connection = Connection_provider.getconnection();
  				PharmacyDAO pharmacyDAO = new JDBCPharmacyDAO(connection);
  				String userid = request.getParameter("userid");
  				String status = request.getParameter("status");
  				
  				Calendar cal = Calendar.getInstance();
  			    SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
  			    String dateString = sdf.format(cal.getTime());
  			        
  				String Accessdatetime=dateString;
  				String CurrentUser=loginInfo.getUserId();
  				
  				if(status.equals("0")){
  					status = "1";
  				}
  				else {
  					status = "0";
  				}
  				Priscription priscription = pharmacyDAO.setpo_approveAccess(userid,status,Accessdatetime,CurrentUser);
  				StringBuilder sb = new StringBuilder();
  				if(priscription.getApprove_ponew().equals("0")){
					sb.append("<input type='checkbox' onclick='setPO_approveAccess("+priscription.getId()+","+priscription.getApprove_ponew()+")' id='po_approve"+priscription.getId()+"' class='onoffswitch-checkbox'>");
					sb.append("<label class='onoffswitch-label' for='po_approve"+priscription.getId()+"'>");
					sb.append("<span class='onoffswitch-inner'></span>");
					sb.append("<span class='onoffswitch-switch'></span>");
					sb.append("</label>");
				}
				else
				{
					sb.append("<input type='checkbox' onclick='setPO_approveAccess("+priscription.getId()+","+priscription.getApprove_ponew()+")' id='po_approve"+priscription.getId()+"' class='onoffswitch-checkbox'  checked='checked'>");
					sb.append("<label class='onoffswitch-label' for='po_approve"+priscription.getId()+"'>");
					sb.append("<span class='onoffswitch-inner'></span>");
					sb.append("<span class='onoffswitch-switch'></span>");
					sb.append("</label>");
				}
  				response.setContentType("text/html");
  				response.setHeader("Cache-Control", "no-cache");
  				response.getWriter().write(""+sb.toString()+"");
  			} catch (Exception e) {
  				e.printStackTrace();
  			}finally{
  				connection.close();
  			}
  			 return null;
  	  }
     
     public String setpurchaseeditaccess() throws Exception {
    	 if (!verifyLogin(request)) {
  			return "login";
  		 }
   		//lokesh 13-7-2018
   	   Connection connection = null;
   			 try {
   				connection = Connection_provider.getconnection();
   				PharmacyDAO pharmacyDAO = new JDBCPharmacyDAO(connection);
   				String userid = request.getParameter("userid");
   				String status = request.getParameter("status");
   				
   				Calendar cal = Calendar.getInstance();
   			    SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
   			    String dateString = sdf.format(cal.getTime());
   			        
   				String Accessdatetime=dateString;
   				String CurrentUser=loginInfo.getUserId();
   				
   				if(status.equals("0")){
   					status = "1";
   				}
   				else {
   					status = "0";
   				}
   				Priscription priscription = pharmacyDAO.setPurchaseEditAccess(userid,status,Accessdatetime,CurrentUser);
   				StringBuilder sb = new StringBuilder();
   				if(priscription.getPurchaseedit().equals("0")){
					sb.append("<input type='checkbox' onclick='setPurchaseeditAccess("+priscription.getId()+","+priscription.getPurchaseedit()+")' id='purcahse_edit"+priscription.getId()+"' class='onoffswitch-checkbox'>");
					sb.append("<label class='onoffswitch-label' for='purcahse_edit"+priscription.getId()+"'>");
					sb.append("<span class='onoffswitch-inner'></span>");
					sb.append("<span class='onoffswitch-switch'></span>");
					sb.append("</label>");
				}
				else
				{
					sb.append("<input type='checkbox' onclick='setPurchaseeditAccess("+priscription.getId()+","+priscription.getPurchaseedit()+")' id='purcahse_edit"+priscription.getId()+"' class='onoffswitch-checkbox'  checked='checked'>");
					sb.append("<label class='onoffswitch-label' for='purcahse_edit"+priscription.getId()+"'>");
					sb.append("<span class='onoffswitch-inner'></span>");
					sb.append("<span class='onoffswitch-switch'></span>");
					sb.append("</label>");
				}
   				
   				////lokesh
				
				
				
				
   				response.setContentType("text/html");
   				response.setHeader("Cache-Control", "no-cache");
   				response.getWriter().write(""+sb.toString()+"");
   			} catch (Exception e) {
   				e.printStackTrace();
   			}finally{
   				connection.close();
   			}
   			 return null;
   	  }
     public String setcancelpoaccess() throws Exception {
    	 if (!verifyLogin(request)) {
  			return "login";
  		 }
   		//lokesh 13-7-2018
   	   Connection connection = null;
   			 try {
   				connection = Connection_provider.getconnection();
   				PharmacyDAO pharmacyDAO = new JDBCPharmacyDAO(connection);
   				String userid = request.getParameter("userid");
   				String status = request.getParameter("status");
   				
   				Calendar cal = Calendar.getInstance();
   			    SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
   			    String dateString = sdf.format(cal.getTime());
   			        
   				String Accessdatetime=dateString;
   				String CurrentUser=loginInfo.getUserId();
   				
   				if(status.equals("0")){
   					status = "1";
   				}
   				else {
   					status = "0";
   				}
   				Priscription priscription = pharmacyDAO.setCancelPoAcces(userid,status,Accessdatetime,CurrentUser);
   				StringBuilder sb = new StringBuilder();
   				if(priscription.getCancel_po().equals("0")){
					sb.append("<input type='checkbox' onclick='setCancelPoAccess("+priscription.getId()+","+priscription.getCancel_po()+")' id='cancel_po"+priscription.getId()+"' class='onoffswitch-checkbox'>");
					sb.append("<label class='onoffswitch-label' for='cancel_po"+priscription.getId()+"'>");
					sb.append("<span class='onoffswitch-inner'></span>");
					sb.append("<span class='onoffswitch-switch'></span>");
					sb.append("</label>");
				}
				else
				{
					sb.append("<input type='checkbox' onclick='setCancelPoAccess("+priscription.getId()+","+priscription.getCancel_po()+")' id='cancel_po"+priscription.getId()+"' class='onoffswitch-checkbox'  checked='checked'>");
					sb.append("<label class='onoffswitch-label' for='cancel_po"+priscription.getId()+"'>");
					sb.append("<span class='onoffswitch-inner'></span>");
					sb.append("<span class='onoffswitch-switch'></span>");
					sb.append("</label>");
				}
   				response.setContentType("text/html");
   				response.setHeader("Cache-Control", "no-cache");
   				response.getWriter().write(""+sb.toString()+"");
   			} catch (Exception e) {
   				e.printStackTrace();
   			}finally{
   				connection.close();
   			}
   			 return null;
   	  }
     
     //  sale report
     public String pharmacysalereport() throws Exception{
    	 if (!verifyLogin(request)) {

 			return "login";
 		}
    	String actiontype="";
 		Connection connection = null;
 		try {
 			
 			/*ArrayList<Breadcrumbs> indentflowlist = new ArrayList<Breadcrumbs>();
			ArrayList<Breadcrumbs> indentflowlisttemp = new ArrayList<Breadcrumbs>();
			if (session.getAttribute("indentflowlist") != null) {
				indentflowlisttemp = (ArrayList<Breadcrumbs>) session.getAttribute("indentflowlist");
			}
			boolean isavilablemodule= false;
			int modulecount =0;
			for (Breadcrumbs breadcrumbs : indentflowlisttemp) {
				breadcrumbs.setIscurrent(false);
				breadcrumbs.setSqno(modulecount);
				modulecount++;
				if(breadcrumbs.getName().equals("Pharmacy Payment Report")){
					isavilablemodule =true;
					breadcrumbs.setIscurrent(true);
					indentflowlist.add(breadcrumbs);
					break;
				}else{
					indentflowlist.add(breadcrumbs);
				}
			}
			if(!isavilablemodule){
				Breadcrumbs breadcrumbs = new Breadcrumbs();
				breadcrumbs.setName("Pharmacy Payment Report");
				breadcrumbs.setOn(true);
				breadcrumbs.setSqno(modulecount);
				breadcrumbs.setUrllink("pharmacysalereportPharmacy");
				breadcrumbs.setIscurrent(true);
				breadcrumbs.setShowingname("Pharmacy Payment Report");
				indentflowlist.add(breadcrumbs);
			}
			session.setAttribute("indentflowlist",indentflowlist);*/
 			
 			String location="0";
 			connection = Connection_provider.getconnection();
 			UserProfileDAO userProfileDAO= new JDBCUserProfileDAO(connection);
 			PharmacyDAO pharmacyDAO=new JDBCPharmacyDAO(connection);
 			
 			String fromdate=emrForm.getFromdate();
 			String todate=emrForm.getTodate();
 			String searchtext= emrForm.getSearchText();
 			String filter_status = emrForm.getFilter_status();
 			location = emrForm.getLocation();
 			String userid = emrForm.getUserid();
 			actiontype=request.getParameter("action");
 			if(location==null){
 				location = "0";
 			}else if(location!=null){
 				if(location.equals("")){
 					location="0";
 				}
 			}
 			
 			if(userid==null){
 				userid = "0";
 			}else if(userid!=null){
 				if(userid.equals("")){
 					userid="0";
 				}
 			} 
 			
 			if(searchtext!=null){
 				if(searchtext.equals("")){
 					searchtext=null;
 				}
 			}
 			
 			if(filter_status==null){
 				filter_status = "0";
 			}else if(filter_status!=null){
 				if(filter_status.equals("")){
 					filter_status="0";
 				}
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
 			fromdate=DateTimeUtils.getCommencingDate1(fromdate);
 			todate=DateTimeUtils.getCommencingDate1(todate);
 			
 			emrForm.setFromdate(fromdate);
 			emrForm.setTodate(todate);
 			
 			fromdate=DateTimeUtils.getCommencingDate1(fromdate);
 			todate=DateTimeUtils.getCommencingDate1(todate);
 			String isreturn = emrForm.getReturnbill();
 			String paymode= emrForm.getPaymode();
 			if(isreturn==null){
 				isreturn="0";
 			}
 			if(paymode==null){
 				paymode="0";
 			}
 			//for parihar
 			emrForm.setHourList(PopulateList.hourListNew());
 			emrForm.setMinuteList(PopulateList.getMinuteList());
 			String tohour=emrForm.getHour();
 			String tominute=emrForm.getMinute();
 			String fromhour=emrForm.getFromhour();
 			String fromminute=emrForm.getFromminute();
 			String datetime=DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone());
 			String temp[] = datetime.split(" ");
 			
 			//for ayushman
 	        if(tohour==null) {
 	        	String time[] = temp[1].split(":");
 	    		String hh = time[0];
 	    		tohour=hh;
 	    		emrForm.setHour(hh);
 			}
 			if(tominute==null) {
 				String time[] = temp[1].split(":");
 				String mm = time[1];
 				tominute=mm;
 				emrForm.setMinute(mm);
 			}
 			if(fromhour==null) {
 				String time[] = temp[1].split(":");
 				String hh = time[0];
 				fromhour=hh;
 				emrForm.setFromhour(hh);
 			}
 			if(fromminute==null) {
 				String time[] = temp[1].split(":");
 				String mm = time[1];
 				fromminute=mm;
 				emrForm.setFromminute(mm);
 			}
 			//for ayushman 
 			if(loginInfo.isAyushman() || loginInfo.isParihar()) {
 				fromdate=fromdate+" " +fromhour+":"+fromminute+":"+"00";
 				todate = todate + " " +tohour+":"+tominute+":"+"59";
 				emrForm.setFromdate_time(fromdate);
 				emrForm.setTodate_time(todate);
 			}
 			
 			/*int count=prescriptionDAO.getAllPriscriptionCount(searchtext,fromdate,todate,location);
 			pagination.setPreperties(count);*/
 			int count=0;
 			if(loginInfo.isTime_utility_report()) {
 	 			count =pharmacyDAO.getAllPharmacySaleCountNew1(searchtext,fromdate,todate,location,isreturn,paymode,userid,loginInfo);

 			}else {
 	 			count =pharmacyDAO.getAllPharmacySaleCountNew(searchtext,fromdate,todate,location,isreturn,paymode,userid);

 			}
 			//int count =pharmacyDAO.getAllPharmacySaleCountNew(searchtext,fromdate,todate,location,isreturn,paymode,userid);
 			pagination.setPreperties(count);
 			//ArrayList<Priscription> priscriptionlist = prescriptionDAO.getAllPharmacyList(pagination,searchtext,fromdate,todate,ipdloc,opdloc,location);
 			ArrayList<Priscription> priscriptionlist=new ArrayList<Priscription>();
 			if(loginInfo.isTime_utility_report()) {
 				//for parihar
 	 			priscriptionlist = pharmacyDAO.getAllPharmacySaleListNew1(pagination,searchtext,fromdate,todate,location,isreturn,paymode,userid,loginInfo);

 			}else {
 	 			priscriptionlist = pharmacyDAO.getAllPharmacySaleListNew(pagination,searchtext,fromdate,todate,location,isreturn,paymode,userid);

 			}
 			//ArrayList<Priscription> priscriptionlist = pharmacyDAO.getAllPharmacySaleListNew(pagination,searchtext,fromdate,todate,location,isreturn,paymode,userid);
 			
 			pagination.setTotal_records(priscriptionlist.size());
 			emrForm.setTotalRecords(count);
 			emrForm.setPagerecords(String.valueOf(pagination.getTotal_records()));
 			emrForm.setPriscriptionlist(priscriptionlist);
 			ArrayList<Product> requestedMedicineList=pharmacyDAO.getAllRequestedMedicine(fromdate, todate);
 			emrForm.setRequestedMedicineList(requestedMedicineList); 
 			
 			Double totalamt = pharmacyDAO.getTotalRecivedAmt(searchtext,fromdate,todate,location,isreturn,paymode,userid);
 			
 			emrForm.setTotalamt(DateTimeUtils.changeFormat(totalamt));
 			
 			/*double totalReceived=pharmacyDAO.getTotalRecived(fromdate,todate,location);
 			double totalRefund=pharmacyDAO.getTotalRefund(fromdate,todate,location);
 			double totalBalance=pharmacyDAO.getTotalBalance(fromdate,todate,location);*/
 			double totalReceived=0;
 			double totalRefund=0;
 			double totalBalance=0;
 			double totaldiscount=0;
 			double totalRefundonline=0;
 			String totalcredit="0";
 			int size = priscriptionlist.size();
			if (size > 0) {
				totalReceived = priscriptionlist.get(size - 1).getTotalReceived();
				totalRefund = priscriptionlist.get(size - 1).getTotalrefund();
				totalBalance = priscriptionlist.get(size - 1).getTotalBalance();
				totalcredit = priscriptionlist.get(size-1).getTotalcredit();
				totaldiscount = priscriptionlist.get(size-1).getTotaldisc();
				totalRefundonline = priscriptionlist.get(size-1).getTotalrefund_online();
				
			}
			if(loginInfo.isParihar()) {
				double totalamtxx=totalReceived-(totalRefund+totalRefundonline);
				emrForm.setTotalamt(Double.toString(totalamtxx));
				emrForm.setTotalReceived(DateTimeUtils.changeFormat(totalReceived));
			}else {
				emrForm.setTotalReceived(DateTimeUtils.changeFormat(totalReceived));
			}
 			//emrForm.setTotalReceived(DateTimeUtils.changeFormat(totalReceived));
 			emrForm.setTotalRefund(DateTimeUtils.changeFormat(totalRefund+totalRefundonline));
 			emrForm.setTotalBalance(DateTimeUtils.changeFormat(totalBalance));
 			emrForm.setTotalcredit(totalcredit);
 			emrForm.setTotaldisc(DateTimeUtils.changeFormat(totaldiscount));
			/*
			 * fromdate=DateTimeUtils.getCommencingDate1(fromdate);
			 * todate=DateTimeUtils.getCommencingDate1(todate);
			 * 
			 * emrForm.setFromdate(fromdate); emrForm.setTodate(todate);
			 */
 			String date=DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone()).split(" ")[0];
 			emrForm.setCommencing(DateTimeUtils.getCommencingDate1(date));
 			emrForm.setUserid(userid);
 			
 			if(searchtext!=null){
 				emrForm.setSearchText(searchtext);
 			}
 			
 			ArrayList<UserProfile> pharmacyUserList = userProfileDAO.getAllPharmacyUserListNew();
 			emrForm.setPharmacyUserList(pharmacyUserList);
 			
 			ArrayList<Priscription> paymodewisepaymentlist=new ArrayList<Priscription>();
 			//for parihar
 			paymodewisepaymentlist=pharmacyDAO.getAllPaymentList(fromdate,todate,location,totalRefund,totalRefundonline);
 			emrForm.setPaymodewisepaymentlist(paymodewisepaymentlist);
 			
 			double discount=pharmacyDAO.getTotaldiscount(fromdate,todate,location);
 			emrForm.setTotaldiscount(totaldiscount);
 			
 			double refundamount=pharmacyDAO.getTotalRefundAmount(fromdate, todate, location);
 			emrForm.setRefundamt(Double.toString(refundamount));
 			//Parihar end
 			
 			//lokesh for showing letterhd in pharmacy
 			boolean showletterhd=pharmacyDAO.isShowletterhd();
 			 if(showletterhd){
 				   session.setAttribute("showletterhd","true");
 				   }else{
 					   session.setAttribute("showletterhd","false"); 
 				   }
 			emrForm.setReturnbill(isreturn);
			emrForm.setPaymode(paymode);
			String subcatename="";
		if(priscriptionlist.size()>0) {	
 			if(location.equals("157")) {
 				subcatename="Medical";
 			}else if(location.equals("158")) {
 				subcatename="Surgical";
 			}else if(location.equals("159")) {
 				subcatename="Vaccine";
 			}else {
 				subcatename="";
 			}
		}else {
			
			subcatename="";
		}	
			emrForm.setSubcategoryname(subcatename);
 			
 		} catch (Exception e) {

 			e.printStackTrace();
 		} finally {
 			connection.close();
 		}
        if(actiontype.equals("1")) {
        	return "pharmacysummaryreport";
        }else {
        	return "pharmacysalereport";
        }
 		//return "pharmacysalereport";
     }
     
     
     public String showpartpaymentrecipt() throws Exception {
    	 if (!verifyLogin(request)) {
  			return "login";
  		 }
  	   Connection connection=null; 
  	   try {
  		    connection=Connection_provider.getconnection();
  		    PharmacyDAO pharmacyDAO=new JDBCPharmacyDAO(connection);
  		    String billno=request.getParameter("billno");
  		    ArrayList<Priscription> priscclearbill = pharmacyDAO.getClearBalBillList(billno);
  		    StringBuffer buffer = new StringBuffer();
  		    int i=1;
  		    for (Priscription priscription : priscclearbill) {
				buffer.append("<tr>");
				buffer.append("<td>"+i+"</td>");
				buffer.append("<td>"+priscription.getDateTime()+"</td>");
				buffer.append("<td>"+priscription.getDebit()+"</td>");
				buffer.append("<td>"+priscription.getPaymode()+"</td>");
				buffer.append("<td><a href='#' onclick=openBlankPopup('printbalreceiptPharmacy?parentid="+priscription.getParentid()+"') style='color: #d9534f;'> Receipt</a></td>");
				buffer.append("</tr>");
				i++;
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
     
     //  14 Aug 2018 new multi return structure
     public String returnmultimedicine() throws Exception{
    	 if (!verifyLogin(request)) {
  			return "login";
  		 }
		 Connection connection=null;  
		 try {
	 		 connection=Connection_provider.getconnection();
			 PharmacyDAO pharmacyDAO=new JDBCPharmacyDAO(connection);
			 InventoryProductDAO inventoryProductDAO= new JDBCInventoryProductDAO(connection);
			 String location= (String)session.getAttribute("location");
			 if(location==null){
				 location="0";
			 }
			/* ArrayList<Product> inventoryPriscList= inventoryProductDAO.geProductList("2",location);
				emrForm.setInventoryPriscList(inventoryPriscList);*/
			 
			 //ArrayList<Bed> bedlist = ipdDAO.getAllBedList(null,loginInfo.getClinicid());
			 ArrayList<Priscription> ipdpatlist = pharmacyDAO.getipdpatientlist();
			 
			 emrForm.setIpdpatientlist(ipdpatlist);
			 emrForm.setIpdmedicineList(null);
			 
				
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			connection.close();
		}
		 return "returnmultimedicine";
	 }
     
     public String setmultireport() throws Exception {
    	 if (!verifyLogin(request)) {
  			return "login";
  		 }
     	Connection connection=null; 
     	try {
     		connection=Connection_provider.getconnection();
     		String clientid= request.getParameter("clientid");
     		String ispharmacy= request.getParameter("ispharmacy");
     		String paymodereturn = request.getParameter("paymodereturn");
     		PharmacyDAO pharmacyDAO= new JDBCPharmacyDAO(connection);
     		StringBuffer buffer= new StringBuffer();
     		String location= (String)session.getAttribute("location");
			if(location==null){
				location="0";
			}
			ArrayList<Product> allMedicieneList = pharmacyDAO.getAllBillsofClient(clientid,ispharmacy,paymodereturn,location);
			for (Product product : allMedicieneList) {
				buffer.append("<tr>");
				buffer.append("<td><input class='returnbillclass' type='checkbox'  value='"+product.getId()+"' /></td> ");
				buffer.append("<td>"+product.getId()+"</td>");
				buffer.append("<td>"+product.getDate()+"</td>");
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
     
     public String tempmedicinelist(){
    	 if (!verifyLogin(request)) {
  			return "login";
  		 }
    	 Connection connection= null;
    	 try {
    		 
    		/* ArrayList<Breadcrumbs> indentflowlist = new ArrayList<Breadcrumbs>();
 			ArrayList<Breadcrumbs> indentflowlisttemp = new ArrayList<Breadcrumbs>();
 			if (session.getAttribute("indentflowlist") != null) {
 				indentflowlisttemp = (ArrayList<Breadcrumbs>) session.getAttribute("indentflowlist");
 			}
 			boolean isavilablemodule= false;
 			int modulecount =0;
 			for (Breadcrumbs breadcrumbs : indentflowlisttemp) {
 				breadcrumbs.setIscurrent(false);
 				breadcrumbs.setSqno(modulecount);
 				modulecount++;
 				if(breadcrumbs.getName().equals("Temp. Product")){
 					isavilablemodule =true;
 					breadcrumbs.setIscurrent(true);
 					indentflowlist.add(breadcrumbs);
 					break;
 				}else{
 					indentflowlist.add(breadcrumbs);
 				}
 			}
 			if(!isavilablemodule){
 				Breadcrumbs breadcrumbs = new Breadcrumbs();
 				breadcrumbs.setName("Temp. Product");
 				breadcrumbs.setOn(true);
 				breadcrumbs.setSqno(modulecount);
 				breadcrumbs.setUrllink("tempmedicinelistPharmacy");
 				breadcrumbs.setIscurrent(true);
 				breadcrumbs.setShowingname("Temp. Product");
 				indentflowlist.add(breadcrumbs);
 			}
 			session.setAttribute("indentflowlist",indentflowlist);*/
    		 
    		 connection = Connection_provider.getconnection();
    		 ArrayList<Priscription> tempmedlist= new ArrayList<Priscription>();
    		 PharmacyDAO pharmacyDAO= new JDBCPharmacyDAO(connection);
    		 tempmedlist=pharmacyDAO.getallTempMedList();
    		 emrForm.setPriscriptionlist(tempmedlist);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
    	 return "tempmedicinelist";
     }
     
     public String settopermanat(){
    	 if (!verifyLogin(request)) {
  			return "login";
  		 }
    	 Connection connection= null;
    	 try {
			connection= Connection_provider.getconnection();
			String id= request.getParameter("id");
			PharmacyDAO pharmacyDAO= new JDBCPharmacyDAO(connection);
			DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		      Calendar cal = Calendar.getInstance();
		      String date = dateFormat.format(cal.getTime());  
			pharmacyDAO.settemptoPermanat(id, date, loginInfo.getUserId());
			
			response.setContentType("text/html");
			response.setHeader("Cache-Control", "no-cache");
			response.getWriter().write("");
			
		} catch (Exception e) {
			
			e.printStackTrace();
		}
    	 
			
    	 return "settopermanat";
     }
     
     
     //  22 Aug 2018
     public String refundmedicinebynurse() throws Exception {
    	 if (!verifyLogin(request)) {
  			return "login";
  		 }
  	   Connection connection=null;
  		try {
  			connection=Connection_provider.getconnection();
  			PharmacyDAO pharmacyDAO=new JDBCPharmacyDAO(connection);
  			InventoryProductDAO inventoryProductDAO=new JDBCInventoryProductDAO(connection);
  			ClientDAO clientDAO=new JDBCClientDAO(connection);
  		    String ispharmacy= request.getParameter("ispharmacy");
  		    String clientid= request.getParameter("clientid");
  		    String ipdid= request.getParameter("ipdid");
  		    String clientname = request.getParameter("fullname");
  		    String practitioner = request.getParameter("practitionerName");
  		    String datetime = DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone());
  		    String returnbillids="0";
  		    Priscription priscription = new Priscription();
  		    
  		    /*int parentid = pharmacyDAO.saveReturnMedicineByNurse(clientid,loginInfo.getUserId(),datetime,ipdid);
  		    for(Priscription prisc:emrForm.getMedicines()){
  		    	
  		    	if(prisc==null){
  		    		continue;
  		    	}
  		    	if(prisc.getId()==0){
  		    		continue;
  		    	}
  		    	if(prisc.getReturnqty()==0){
  		    		continue;
  		    	}
  		    	
  		    	priscription.setMdicinenameid(prisc.getMdicinenameid());
  		    	priscription.setProductname(prisc.getProductname());
  		    	priscription.setProduct_id(prisc.getProduct_id());
  		    	priscription.setId(prisc.getId());
  		    	priscription.setSaleqty(prisc.getReturnqty());
  		    	priscription.setReqqty(prisc.getReqqty());
  		    	int ress = pharmacyDAO.returnByNurseChild(priscription);
  		     }*/
  		   String sessionid = loginInfo.getLoginsessionid();
  		    for(Priscription prisc:emrForm.getMedicines()){
		   		if(prisc==null){
		    		continue;
		    	}
		    	if(prisc.getId()==0){
		    		continue;
		    	}
		    	if(prisc.getReturnqty()==0){
		    		continue;
		    	}
		    	priscription.setMdicinenameid(prisc.getMdicinenameid());
		    	priscription.setProductname(prisc.getProductname());
		    	priscription.setProduct_id(prisc.getProduct_id());
		    	priscription.setId(prisc.getId());
		    	priscription.setSaleqty(prisc.getReturnqty());
		    	priscription.setReqqty(prisc.getReqqty());
		    	
		    	int ress = pharmacyDAO.returnByNurseChildTemp(priscription,sessionid);
		    }
  		    
  		    ArrayList<Priscription> arrayList = pharmacyDAO.getTempMedicineCharge(sessionid);
  		    for (Priscription priscription2 : arrayList) {
  		    	 int parentid = pharmacyDAO.saveReturnMedicineByNurse(clientid,loginInfo.getUserId(),datetime,ipdid);
  		    	 ArrayList<Priscription> arrayList2 = pharmacyDAO.getReturnMedicineTempList(sessionid,priscription2);
  		    	 for(Priscription prisc: arrayList2){
  			    	if(prisc==null){
  			    		continue;
  			    	}
  			    	if(prisc.getId()==0){
  			    		continue;
  			    	}
  			    	
  			    	priscription.setMdicinenameid(prisc.getMdicinenameid());
  			    	priscription.setProductname(prisc.getProductname());
  			    	priscription.setProduct_id(prisc.getProduct_id());
  			    	priscription.setId(prisc.getId());
  			    	priscription.setSaleqty(prisc.getSaleqty());
  			    	priscription.setReqqty(prisc.getReqqty());
  			    	priscription.setParentid(""+parentid);
  			    	int ress = pharmacyDAO.returnByNurseChild(priscription);
  			     }
  		    }
  		   
  		    int res = pharmacyDAO.deleteTempNurcingMedicine(sessionid);
  		    session.setAttribute("nursebyreturnclientid", clientid);
  		    session.setAttribute("nursebyreturnipd", ipdid);
  		} catch (Exception e) {

  			e.printStackTrace();
  			
  		}finally{
  			connection.close();
  		}
  		
  	    return "returnmedicinenursemethod";
     }
     
     public String returnmedicinedash() throws Exception{
    	 if (!verifyLogin(request)) {
  			return "login";
  		 }
    	Connection connection= null;
    	try {
    		
    		/*ArrayList<Breadcrumbs> indentflowlist = new ArrayList<Breadcrumbs>();
			ArrayList<Breadcrumbs> indentflowlisttemp = new ArrayList<Breadcrumbs>();
			if (session.getAttribute("indentflowlist") != null) {
				indentflowlisttemp = (ArrayList<Breadcrumbs>) session.getAttribute("indentflowlist");
			}
			boolean isavilablemodule= false;
			int modulecount =0;
			for (Breadcrumbs breadcrumbs : indentflowlisttemp) {
				breadcrumbs.setIscurrent(false);
				breadcrumbs.setSqno(modulecount);
				modulecount++;
				if(breadcrumbs.getName().equals("Nurse Return")){
					isavilablemodule =true;
					breadcrumbs.setIscurrent(true);
					indentflowlist.add(breadcrumbs);
					break;
				}else{
					indentflowlist.add(breadcrumbs);
				}
			}
			if(!isavilablemodule){
				Breadcrumbs breadcrumbs = new Breadcrumbs();
				breadcrumbs.setName("Nurse Return");
				breadcrumbs.setOn(true);
				breadcrumbs.setSqno(modulecount);
				breadcrumbs.setUrllink("returnmedicinedashPharmacy");
				breadcrumbs.setIscurrent(true);
				breadcrumbs.setShowingname("Nurse Return");
				indentflowlist.add(breadcrumbs);
			}
			session.setAttribute("indentflowlist",indentflowlist);*/
    		
			connection = Connection_provider.getconnection();
			PharmacyDAO pharmacyDAO = new JDBCPharmacyDAO(connection);
			String fromdate = emrForm.getFromdate();
			String todate = emrForm.getTodate();
			if(fromdate == null){
				DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
				Calendar cal = Calendar.getInstance();
				fromdate = dateFormat.format(cal.getTime());   
				fromdate = DateTimeUtils.getCommencingDate1(fromdate);
			}else{
				if(fromdate.equals("")) {
					DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
				    Calendar cal = Calendar.getInstance();
				    fromdate = dateFormat.format(cal.getTime());   
				    fromdate = DateTimeUtils.getCommencingDate1(fromdate);
				}else{
					fromdate= DateTimeUtils.getCommencingDate1(fromdate);
				}
			}
			if(todate== null){
				DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
				Calendar cal = Calendar.getInstance(); 
				todate = dateFormat.format(cal.getTime());
				 todate = DateTimeUtils.getCommencingDate1(todate);
			}else{
				if(todate.equals("")){
				    DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
				    Calendar cal = Calendar.getInstance(); 
				    todate = dateFormat.format(cal.getTime());
				    todate = DateTimeUtils.getCommencingDate1(todate);
				}
				else{
					todate= DateTimeUtils.getCommencingDate1(todate);
				}
			}
			
			//  16 Nov 2018 
			//delete previous data of user using login session
			int res = pharmacyDAO.deleteTempPharmacySession(loginInfo.getLoginsessionid());

			
			ArrayList<Priscription> returnmedlist = pharmacyDAO.getReturnMedicineDash(fromdate,todate);
			emrForm.setReturnmedlist(returnmedlist);
			emrForm.setFromdate(DateTimeUtils.getCommencingDate1(fromdate));
			emrForm.setTodate(DateTimeUtils.getCommencingDate1(todate));
		} catch (Exception e) {
			e.printStackTrace();
		}
    	 return "retnursedashboard";
     }
     
     
   public String newdrreport(){
	   if (!verifyLogin(request)) {
			return "login";
		 }
	   Connection connection= null;
	   String fromdate="";
		String todate="";
		String drname= emrForm.getPractitionerName();
		emrForm.setPractitionerName(emrForm.getPractitionerName());
		fromdate= emrForm.getFromdate();
		todate= emrForm.getTodate();
		if(fromdate==null){
			fromdate="";
		}
		if(todate==null){
			todate="";
		}
		if(fromdate.equals("")){
			DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
		    Calendar cal = Calendar.getInstance();
		    fromdate = dateFormat.format(cal.getTime());   
		 	}
		if(todate.equals("")){
		    DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
		    Calendar cal = Calendar.getInstance(); 
		    todate = dateFormat.format(cal.getTime());
		    
		}
		if(drname==null){
			drname="";
		}
		if(drname.contains("Dr.")){
			drname= drname.replaceAll("Dr. ", "");
		}
	   try {
		  /* ArrayList<Breadcrumbs> indentflowlist = new ArrayList<Breadcrumbs>();
			ArrayList<Breadcrumbs> indentflowlisttemp = new ArrayList<Breadcrumbs>();
			if (session.getAttribute("indentflowlist") != null) {
				indentflowlisttemp = (ArrayList<Breadcrumbs>) session.getAttribute("indentflowlist");
			}
			boolean isavilablemodule= false;
			int modulecount =0;
			for (Breadcrumbs breadcrumbs : indentflowlisttemp) {
				breadcrumbs.setIscurrent(false);
				breadcrumbs.setSqno(modulecount);
				modulecount++;
				if(breadcrumbs.getName().equals("Doctor Product Report")){
					isavilablemodule =true;
					breadcrumbs.setIscurrent(true);
					indentflowlist.add(breadcrumbs);
					break;
				}else{
					indentflowlist.add(breadcrumbs);
				}
			}
			if(!isavilablemodule){
				Breadcrumbs breadcrumbs = new Breadcrumbs();
				breadcrumbs.setName("Doctor Product Report");
				breadcrumbs.setOn(true);
				breadcrumbs.setSqno(modulecount);
				breadcrumbs.setUrllink("newdrreportPharmacy");
				breadcrumbs.setIscurrent(true);
				breadcrumbs.setShowingname("Doctor Product Report");
				indentflowlist.add(breadcrumbs);
			}
			session.setAttribute("indentflowlist",indentflowlist);  */
		 
		ArrayList<Priscription> drreportlist= new ArrayList<Priscription>();   
		connection =Connection_provider.getconnection();
		PharmacyDAO pharmacyDAO= new JDBCPharmacyDAO(connection);
		drreportlist=pharmacyDAO.getnewDrReport(fromdate, todate, drname);
		session.setAttribute("drreportlist", drreportlist);
		emrForm.setDoctorreportList(drreportlist);
		emrForm.setFromdate(fromdate);
		emrForm.setTodate(todate);
		NotAvailableSlotDAO notAvailableSlotDAO= new JDBCNotAvailableSlotDAO(connection);
		ArrayList<DiaryManagement> alldoctorlist= notAvailableSlotDAO.getUserList(0);
		emrForm.setUserList(alldoctorlist);
	} catch (Exception e) {
		e.printStackTrace();
	}
	   return "newdrreport";
   }   
   
   
  public String newmedicinedailycount(){
	  if (!verifyLogin(request)) {
			return "login";
		 }
	  Connection connection= null;
	  String fromdate="";
	  String todate="";
	  fromdate= emrForm.getFromdate();
	  todate=emrForm.getTodate();
		if(fromdate==null){
			fromdate="";
		}
		if(todate==null){
			todate="";
		}
		if(fromdate.equals("")){
			DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
		    Calendar cal = Calendar.getInstance();
		    fromdate = dateFormat.format(cal.getTime());   
		 	}
		if(todate.equals("")){
		    DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
		    Calendar cal = Calendar.getInstance(); 
		    todate = dateFormat.format(cal.getTime());
		    
		}
		String location = emrForm.getLocation();
		if(location==null){
			location="";
		}
		String prodname=emrForm.getName();
		if(prodname==null){
			prodname="";
		}
	  try {
		  	/*ArrayList<Breadcrumbs> indentflowlist = new ArrayList<Breadcrumbs>();
			ArrayList<Breadcrumbs> indentflowlisttemp = new ArrayList<Breadcrumbs>();
			if (session.getAttribute("indentflowlist") != null) {
				indentflowlisttemp = (ArrayList<Breadcrumbs>) session.getAttribute("indentflowlist");
			}
			boolean isavilablemodule= false;
			int modulecount =0;
			for (Breadcrumbs breadcrumbs : indentflowlisttemp) {
				breadcrumbs.setIscurrent(false);
				breadcrumbs.setSqno(modulecount);
				modulecount++;
				if(breadcrumbs.getName().equals("Daily Product Count Report")){
					isavilablemodule =true;
					breadcrumbs.setIscurrent(true);
					indentflowlist.add(breadcrumbs);
					break;
				}else{
					indentflowlist.add(breadcrumbs);
				}
			}
			if(!isavilablemodule){
				Breadcrumbs breadcrumbs = new Breadcrumbs();
				breadcrumbs.setName("Daily Product Count Report");
				breadcrumbs.setOn(true);
				breadcrumbs.setSqno(modulecount);
				breadcrumbs.setUrllink("newmedicinedailycountPharmacy");
				breadcrumbs.setIscurrent(true);
				breadcrumbs.setShowingname("Daily Product Count Report");
				indentflowlist.add(breadcrumbs);
			}
			session.setAttribute("indentflowlist",indentflowlist);  */
		 
		  
		connection= Connection_provider.getconnection();
		PharmacyDAO pharmacyDAO= new JDBCPharmacyDAO(connection);
		ArrayList<Priscription> list=new ArrayList<Priscription>();
		list= pharmacyDAO.getnewMedicineDialyCountReport(fromdate, todate,location,prodname);

		ArrayList<Master> locationListPharmacy= pharmacyDAO.getAllLocation();
		emrForm.setLocationListPharmacy(locationListPharmacy);
		emrForm.setDoctorreportList(list);	
		emrForm.setFromdate(fromdate);
		emrForm.setTodate(todate);
	} catch (Exception e) {
		e.printStackTrace();
	}
	  
	  return "newmedicinedailycount";
  } 
  
  
  public String newgstReport(){
	  if (!verifyLogin(request)) {
			return "login";
		 }
	  String fromdate="";
	  String todate="";
	  fromdate= emrForm.getFromdate();
	  todate=emrForm.getTodate();
		if(fromdate==null){
			fromdate="";
		}
		if(todate==null){
			todate="";
		}
		if(fromdate.equals("")){
			DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
		    Calendar cal = Calendar.getInstance();
		    fromdate = dateFormat.format(cal.getTime());   
		 	}
		if(todate.equals("")){
		    DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
		    Calendar cal = Calendar.getInstance(); 
		    todate = dateFormat.format(cal.getTime());
		    
		}
		String paymode="";
		paymode= emrForm.getPaymode();
		if(paymode==null){
			paymode="";
		}
		int istreurn=0;
		istreurn= emrForm.getIsreturn();
		emrForm.setPaymode(paymode);
		
		String reportby=emrForm.getReportby();
		if(reportby==null){
			reportby="";
		}
		
	  Connection connection= null;
	  try {
		  	/*ArrayList<Breadcrumbs> indentflowlist = new ArrayList<Breadcrumbs>();
			ArrayList<Breadcrumbs> indentflowlisttemp = new ArrayList<Breadcrumbs>();
			if (session.getAttribute("indentflowlist") != null) {
				indentflowlisttemp = (ArrayList<Breadcrumbs>) session.getAttribute("indentflowlist");
			}
			boolean isavilablemodule= false;
			int modulecount =0;
			for (Breadcrumbs breadcrumbs : indentflowlisttemp) {
				breadcrumbs.setIscurrent(false);
				breadcrumbs.setSqno(modulecount);
				modulecount++;
				if(breadcrumbs.getName().equals("Pharmacy GST Sale Report")){
					isavilablemodule =true;
					breadcrumbs.setIscurrent(true);
					indentflowlist.add(breadcrumbs);
					break;
				}else{
					indentflowlist.add(breadcrumbs);
				}
			}
			if(!isavilablemodule){
				Breadcrumbs breadcrumbs = new Breadcrumbs();
				breadcrumbs.setName("Pharmacy GST Sale Report");
				breadcrumbs.setOn(true);
				breadcrumbs.setSqno(modulecount);
				breadcrumbs.setUrllink("newgstReportPharmacy");
				breadcrumbs.setIscurrent(true);
				breadcrumbs.setShowingname("GST Sale Report");
				indentflowlist.add(breadcrumbs);
			}
			session.setAttribute("indentflowlist",indentflowlist);   */
		
		connection= Connection_provider.getconnection();
		PharmacyDAO pharmacyDAO= new JDBCPharmacyDAO(connection);
		ArrayList<Priscription> list = new ArrayList<Priscription>();
		list= pharmacyDAO.getnewGSTReport(fromdate, todate,paymode,istreurn,loginInfo.getClinicUserid(),reportby);
		
		emrForm.setDoctorreportList(list);	
		emrForm.setFromdate(fromdate);
		emrForm.setTodate(todate);
		Clinic clinic= new Clinic();
		ClinicDAO clinicDAO= new JDBCClinicDAO(connection);
		AccountsDAO accountsDAO= new JDBCAccountsDAO(connection);
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
	} catch (Exception e) {
		e.printStackTrace();
	}
	  return "newgstReport";
  }
  
  public String showpatientpaymentmode() throws Exception {
	  if (!verifyLogin(request)) {
			return "login";
		 }
 	   Connection connection=null; 
 	   try {
 		    connection=Connection_provider.getconnection();
 		    PharmacyDAO pharmacyDAO=new JDBCPharmacyDAO(connection);
 		    String billno=request.getParameter("billno");
 		    //ArrayList<Priscription> priscclearbill = pharmacyDAO.getClearBalBillList(billno);
 		    ArrayList<Priscription> priscclearbill = pharmacyDAO.getBillPayments(billno);
 		    StringBuffer buffer = new StringBuffer();
 		    int i=1;
 		    for (Priscription priscription : priscclearbill) {
				buffer.append("<tr>");
				buffer.append("<td>"+i+"</td>");
				buffer.append("<td>"+priscription.getDate()+"</td>");
				buffer.append("<td>"+priscription.getPayment()+"</td>");
				buffer.append("<td class='notranslate'>"+priscription.getPaymode()+"</td>");
				if(priscription.getPaymode()!=null){
					if(priscription.getPaymode().equals("Credit")){
						buffer.append("<td></td>");
					}else{
						buffer.append("<td><a href='#' onclick=changePaymentMode("+priscription.getId()+",'"+priscription.getPayment()+"','"+priscription.getPaymode()+"') style='color: #d9534f;'> Change Pay Mode</a></td>");
					}
				}else{
					buffer.append("<td><a href='#' onclick=changePaymentMode("+priscription.getId()+",'"+priscription.getPayment()+"','"+priscription.getPaymode()+"') style='color: #d9534f;'> Change</a></td>");
				}
				
				buffer.append("</tr>");
				i++;
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
  
  
  
  public String savenewpaymode() throws Exception {
	  if (!verifyLogin(request)) {
			return "login";
		 }
		Connection connection=null;  
		try {
			connection=Connection_provider.getconnection();
			PharmacyDAO pharmacyDAO=new JDBCPharmacyDAO(connection);
			String change_paymode_id =request.getParameter("change_paymode_id");
			String paymode= request.getParameter("paymode");
			String paynote="";
			if(paymode.equals("Card")){
				paynote= request.getParameter("card");
			}
			if(paymode.equals("Cheque")){
				paynote= request.getParameter("chequeno");
			}
			if(paymode.equals("NEFT")){
				paynote= request.getParameter("neftid");
			}
			Priscription priscription= new Priscription();
			priscription.setId(Integer.parseInt(change_paymode_id));
			priscription.setPaymode(paymode);
			//priscription.setUserid(loginInfo.getUserId());
			//String dateTime= DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone());
			//priscription.setDateTime(dateTime);
			priscription.setPaynote(paynote);
			int res = pharmacyDAO.updatePaymode(priscription);
			int billno = pharmacyDAO.getBillNoFromPaymentid(change_paymode_id);
			int result = pharmacyDAO.updateBillInitialPaymode(""+billno,paymode);
		} catch (Exception e) {

			e.printStackTrace();
		}
		  
		finally {
			connection.close();
		}
		  
		return "save"; 
	  }
  
  public String setpharm_print_backdate(){
	  if (!verifyLogin(request)) {
			return "login";
		 }
	  Connection connection= null;
	  try {
		connection = Connection_provider.getconnection();
		
		PharmacyDAO pharmacyDAO = new JDBCPharmacyDAO(connection);
			String userid = request.getParameter("userid");
			String status = request.getParameter("status");
			
			Calendar cal = Calendar.getInstance();
		    SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
		    String dateString = sdf.format(cal.getTime());
		        
			String Accessdatetime=dateString;
			String CurrentUser=loginInfo.getUserId();
			
			if(status.equals("true")){
				status = "0";
			}
			else {
				status = "1";
			}
			Priscription priscription = pharmacyDAO.setpharmacy_backdate_print_access(status, userid);
			StringBuilder sb = new StringBuilder();
			if(!priscription.isPharm_print_backdate()){
				sb.append("<input type='checkbox' onclick='setpharm_print_backdate("+priscription.getId()+","+priscription.isPharm_print_backdate()+")' id='pharm_print_backdate"+priscription.getId()+"' class='onoffswitch-checkbox'>");
				sb.append("<label class='onoffswitch-label' for='pharm_print_backdate"+priscription.getId()+"'>");
				sb.append("<span class='onoffswitch-inner'></span>");
				sb.append("<span class='onoffswitch-switch'></span>");
				sb.append("</label>");
			}
			else
			{
				sb.append("<input type='checkbox' onclick='setpharm_print_backdate("+priscription.getId()+","+priscription.isPharm_print_backdate()+")' id='pharm_print_backdate"+priscription.getId()+"' class='onoffswitch-checkbox'  checked='checked'>");
				sb.append("<label class='onoffswitch-label' for='pharm_print_backdate"+priscription.getId()+"'>");
				sb.append("<span class='onoffswitch-inner'></span>");
				sb.append("<span class='onoffswitch-switch'></span>");
				sb.append("</label>");
			}
		
		
		
		
			response.setContentType("text/html");
			response.setHeader("Cache-Control", "no-cache");
			response.getWriter().write(""+sb.toString()+"");

	} catch (Exception e) {
		e.printStackTrace();
	}
	  return null;
  }
  
  public String hsnwisegst(){
	  if (!verifyLogin(request)) {
			return "login";
		 }
	  Connection connection= null;
	  String fromdate="";
	  String todate="";
	  fromdate= emrForm.getFromdate();
	  todate=emrForm.getTodate();
		if(fromdate==null){
			fromdate="";
		}
		 if(todate==null){
			todate="";
		}
		if(fromdate.equals("")){
			DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
		    Calendar cal = Calendar.getInstance();
		    fromdate = dateFormat.format(cal.getTime());   
		 	}
		if(todate.equals("")){
		    DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
		    Calendar cal = Calendar.getInstance(); 
		    todate = dateFormat.format(cal.getTime());
		    
		}
	
	  
	  try {
		  /*ArrayList<Breadcrumbs> indentflowlist = new ArrayList<Breadcrumbs>();
			ArrayList<Breadcrumbs> indentflowlisttemp = new ArrayList<Breadcrumbs>();
			if (session.getAttribute("indentflowlist") != null) {
				indentflowlisttemp = (ArrayList<Breadcrumbs>) session.getAttribute("indentflowlist");
			}
			boolean isavilablemodule= false;
			int modulecount =0;
			for (Breadcrumbs breadcrumbs : indentflowlisttemp) {
				breadcrumbs.setIscurrent(false);
				breadcrumbs.setSqno(modulecount);
				modulecount++;
				if(breadcrumbs.getName().equals("HSN Wise GST Report")){
					isavilablemodule =true;
					breadcrumbs.setIscurrent(true);
					indentflowlist.add(breadcrumbs);
					break;
				}else{
					indentflowlist.add(breadcrumbs);
				}
			}
			if(!isavilablemodule){
				Breadcrumbs breadcrumbs = new Breadcrumbs();
				breadcrumbs.setName("HSN Wise GST Report");
				breadcrumbs.setOn(true);
				breadcrumbs.setSqno(modulecount);
				breadcrumbs.setUrllink("hsnwisegstPharmacy");
				breadcrumbs.setIscurrent(true);
				breadcrumbs.setShowingname("HSN Wise GST Report");
				indentflowlist.add(breadcrumbs);
			}
			session.setAttribute("indentflowlist",indentflowlist); */
	    	 
		  
		connection= Connection_provider.getconnection();
		ArrayList<Priscription> list = new ArrayList<Priscription>();
		PharmacyDAO pharmacyDAO= new JDBCPharmacyDAO(connection);
		list=pharmacyDAO.gethsnwiseGSTReport(fromdate, todate);
		
		emrForm.setDoctorreportList(list);	
		emrForm.setFromdate(fromdate);
		emrForm.setTodate(todate);
		Clinic clinic= new Clinic();
		ClinicDAO clinicDAO= new JDBCClinicDAO(connection);
		AccountsDAO accountsDAO= new JDBCAccountsDAO(connection);
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
		
	} catch (Exception e) {
		e.printStackTrace();
	}
	  return "hsnwisegst";
  }
  
  public String savecarddiscountamount(){
	  if (!verifyLogin(request)) {
			return "login";
		 }
	  Connection connection= null;
	  try {
		connection = Connection_provider.getconnection();
		
		PharmacyDAO pharmacyDAO = new JDBCPharmacyDAO(connection);
			String discsmt = request.getParameter("discsmt");
			String distype = request.getParameter("distype");
			String discinper = request.getParameter("discinper");
			String hdnphclientid = request.getParameter("hdnphclientid");
			String hdnispharmacy = request.getParameter("hdnispharmacy");
			int isfordiscount=1;
			/*boolean flag = pharmacyDAO.checkPatientAvailableInLog(Integer.parseInt(hdnphclientid),Integer.parseInt(hdnispharmacy),loginInfo.getLoginsessionid(),isfordiscount);
			if(!flag){
			*/	//if entry not present in temp table so insert into temp table
				int tempsessionid = pharmacyDAO.insertTempPharSaleSession(Integer.parseInt(hdnphclientid),Integer.parseInt(hdnispharmacy),loginInfo.getLoginsessionid(),isfordiscount,discsmt,distype,discinper);
			/*}
		*/
		
		
			response.setContentType("text/html");
			response.setHeader("Cache-Control", "no-cache");
			response.getWriter().write("");

	} catch (Exception e) {
		e.printStackTrace();
	}
	  return null;
  }
  
  public String setallmedicinesmodewise() throws Exception {
	  if (!verifyLogin(request)) {
			return "login";
		 }
  	Connection connection=null; 
  	try {
  		connection=Connection_provider.getconnection();
  		String clientid= request.getParameter("clientid");
  		String ispharmacy= request.getParameter("ispharmacy");
  		String paymode= request.getParameter("paymode");
  		PharmacyDAO pharmacyDAO= new JDBCPharmacyDAO(connection);
  		
  		String location =(String) session.getAttribute("location");
		if(location==null){
			location="0";
		}
  		int res = pharmacyDAO.deleteTempReturnPharmacyData(clientid,ispharmacy,loginInfo.getLoginsessionid());
		
  		ArrayList<Product> allMedicieneList1 = pharmacyDAO.getAllBillsofClient(clientid,ispharmacy,paymode,location);
		int size= allMedicieneList1.size();
        String ids="0";
		if(size>0){
     	   ids = allMedicieneList1.get(size-1).getIds();
        }
		
		ArrayList<Product> allMedicieneList = pharmacyDAO.getAllBillWise(clientid,ispharmacy,ids,0);
  		StringBuffer buffer= new StringBuffer();
  		buffer.append("<select class='form-control chosen' id='newmedicine' name='mdicinename'  >");
  		buffer.append("<option value='0'>Select Product To Return</option>");
  		for(Product product:allMedicieneList){
  			buffer.append("<option value='"+product.getId()+"'>"+product.getGenericname()+"</option>");
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
  
  public String setallmedicineslistdaata() throws Exception {
	  if (!verifyLogin(request)) {
			return "login";
		 }
  	Connection connection=null; 
  	try {
  		connection=Connection_provider.getconnection();
  		InventoryProductDAO inventoryProductDAO = new JDBCInventoryProductDAO(connection);
  		String location =(String) session.getAttribute("location");
			if(location==null){
				location="0";
			}
  		
  		ArrayList<Product> allMedicieneList = inventoryProductDAO.geProductList("2",location);
  		StringBuffer buffer= new StringBuffer();
  		buffer.append("<select class='form-control chosen' id='newmedicine' name='mdicinename' style='width:50px' >");
  		buffer.append("<option value='0'>Select Product</option>");
  		for(Product product:allMedicieneList){
  			
  			  buffer.append("<option value='"+product.getId()+"'>"+product.getGenericname()+"</option>");
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
  public String onlinerequestphar() throws Exception{
	  if (!verifyLogin(request)) {

			return "login";
		}
		Connection connection = null;
		try {
			
			/*ArrayList<Breadcrumbs> indentflowlist = new ArrayList<Breadcrumbs>();
			ArrayList<Breadcrumbs> indentflowlisttemp = new ArrayList<Breadcrumbs>();
			if (session.getAttribute("indentflowlist") != null) {
				indentflowlisttemp = (ArrayList<Breadcrumbs>) session.getAttribute("indentflowlist");
			}
			boolean isavilablemodule= false;
			int modulecount =0;
			for (Breadcrumbs breadcrumbs : indentflowlisttemp) {
				breadcrumbs.setIscurrent(false);
				breadcrumbs.setSqno(modulecount);
				modulecount++;
				if(breadcrumbs.getName().equals("Online Dashboard")){
					isavilablemodule =true;
					breadcrumbs.setIscurrent(true);
					indentflowlist.add(breadcrumbs);
					break;
				}else{
					indentflowlist.add(breadcrumbs);
				}
			}
			if(!isavilablemodule){
				Breadcrumbs breadcrumbs = new Breadcrumbs();
				breadcrumbs.setName("Online Dashboard");
				breadcrumbs.setOn(true);
				breadcrumbs.setSqno(modulecount);
				breadcrumbs.setUrllink("onlinerequestpharPharmacy");
				breadcrumbs.setIscurrent(true);
				breadcrumbs.setShowingname("Online Dashboard");
				indentflowlist.add(breadcrumbs);
			}
			session.setAttribute("indentflowlist",indentflowlist);*/
			
			
			String location="0";
			session.removeAttribute("sessionadmissionid");

			connection = Connection_provider.getconnection();
			UserProfileDAO userProfileDAO= new JDBCUserProfileDAO(connection);
			PharmacyDAO pharmacyDAO=new JDBCPharmacyDAO(connection);
			
			
			 UserProfile profile2 = userProfileDAO.getPharmacyUserDetails(loginInfo.getClinicid());
			
			String userid = loginInfo.getUserId();
			Priscription priscription = pharmacyDAO.getPharacyUsrLInfo(userid);
			String ipdlocation="0";
			String opdlocation="0";
			String ipdloc="0";
			String opdloc="0";
			if(loginInfo.getUserType()==2) {
				 
				 location = (String) session.getAttribute("location");
				 if(location==null){
					 location= "0";
					 ipdloc=null;
					 opdloc=null;
				 }
				 if(location.equals("All")){
			    	 location="0";
			     }
			     
				 session.setAttribute("location", location);
			} else {
				 
			     //String loc=  priscription.getLocation();
				 String loc="";
			     if(loginInfo.isParihar()) {
			    	 loc = (String) session.getAttribute("location");
			     }else {
			    	 loc=  priscription.getLocation();
			     }
			     
			     if(loc!=null){
			      	  session.setAttribute("location", loc);
			    	  location= loc;
			     }  else {
			    	 location= "0";
			     }
			     if(loc.equals("All")){
			    	 loc="0";
			     }
			     
			     UserProfile profile = userProfileDAO.getPharmacyUserDetails(loginInfo.getClinicid());
			     
			     if(profile.getProcurementType()!=null){
			    	 if(profile.getProcurementType().equals("1")){
			    		 loginInfo.setProcurementType(true);
			    	 } else {
			    		 loginInfo.setProcurementType(false);
			    	 }
			     } else {
			    	 loginInfo.setProcurementType(false);
			     }
			     
			     
			     ipdlocation= profile.getIpdlocation();
			     opdlocation= profile.getOpdlocation();
				 
				 for(String s: ipdlocation.split(",")){
					 if(s.equals("0")){
						 continue;
					 }
					 if(location.equals(s)){
						 ipdloc=location;
						 break;
					 }
					 
				 }
				 
				 for(String s:opdlocation.split(",")){
					  
					 if(s.equals("0")){
						 continue;
					 }
					 if(location.equals(s)){
						 opdloc=location;
						 break;
					 }
				 }
				 
			}
			
			
			emrForm.setLocation(location);
			
			if (priscription.getSale_bill()!=null)
				if(priscription.getSale_bill().equals("0"))
					loginInfo.setSale_bill(false);
				else
					loginInfo.setSale_bill(true);
			
			if (priscription.getDisscount()!=null)
				if (priscription.getDisscount().equals("0"))
					loginInfo.setDisscount(false);
				else
					loginInfo.setDisscount(true);
			
			if (priscription.getAccount()!=null)
				if (priscription.getAccount().equals("0"))
					loginInfo.setAccount(false);
				else
					loginInfo.setAccount(true);
			
			if (priscription.getLedger()!=null)
				if (priscription.getLedger().equals("0"))
					loginInfo.setLedger(false);
				else
					loginInfo.setLedger(true);
			
			if (priscription.getIslogin()!=null)
				if (priscription.getIslogin().equals("0"))
					loginInfo.setIslogin(false);
				else
					loginInfo.setIslogin(true);
			
			if (priscription.getPurchase_order()!=null)
				if (priscription.getPurchase_order().equals("0"))
					loginInfo.setPurchase_order(false);
				else
					loginInfo.setPurchase_order(true);
			
			if (priscription.getEdit_bill()!=null)
				if (priscription.getEdit_bill().equals("0"))
					loginInfo.setEdit_bill(false);
				else
					loginInfo.setEdit_bill(true);
			
			if (priscription.getDelete_bill()!=null)
				if (priscription.getDelete_bill().equals("0"))
					loginInfo.setDelete_bill(false);
				else
					loginInfo.setDelete_bill(true);
			if(priscription.getInhousepatient()!=null)
			if (priscription.getInhousepatient().equals("0"))
				loginInfo.setInhousepatient(false);
			else
				loginInfo.setInhousepatient(true);
			
			if (priscription.getReturn_medicine()!=null)
				if (priscription.getReturn_medicine().equals("0"))
					loginInfo.setReturn_medicine(false);
				else
					loginInfo.setReturn_medicine(true);
			
			
			if (!loginInfo.isIslogin()) {
				if(loginInfo.getUserType()==2 || loginInfo.getJobTitle().equals("Pharmacist")){
					
				}
				else{
					addActionError("Inactive pharmacy user");
					return "loginerror";
				}
			}
			/*String return_medicine =  priscription.getReturn_medicine();
			emrForm.setReturn_medicine(priscription.getReturn_medicine());*/
			String fromdate=emrForm.getFromdate();
			String todate=emrForm.getTodate();
			String searchtext= emrForm.getSearchText();
			String filter_status = emrForm.getFilter_status();
			String isfromreturndashboard = emrForm.getIsfromreturndashboard();
			String actiontype=request.getParameter("IpdpatientType");
			String fakclientid=request.getParameter("fake_clientid");
			String fuhid=request.getParameter("uhid");
			if(searchtext!=null){
				if(searchtext.equals("")){
					searchtext=null;
				}
			}
			
			if(filter_status==null){
				filter_status = "0";
			}else if(filter_status!=null){
				if(filter_status.equals("")){
					filter_status="0";
				}
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
			String isreturn = emrForm.getReturnbill();
			String paymode= emrForm.getPaymode();
			int dontshowreqbill =0;
			//  07 aug 2018 when search for return bill or paymode then dont show requested list from opd and ipd
			if(isreturn==null){
				isreturn="0";
				dontshowreqbill =1;
			}
			if(paymode==null){
				paymode="0";
				dontshowreqbill =1;
			}
			
			/*int count=prescriptionDAO.getAllPriscriptionCount(searchtext,fromdate,todate,location);
			pagination.setPreperties(count);*/
			
			//  11 AUg 2018
			//if its from return medicine (return with bill) 
			int isfromreturndash =0;
			if(isfromreturndashboard!=null){
				if(isfromreturndashboard.equals("1")){
					isfromreturndash =1;
				}
			}
			if(loginInfo.getClinicid1().equals("bams") || loginInfo.getClinicid1().equals("chraibgh")) {
				String fdate=pharmacyDAO.getdatebyClientid(fakclientid);
				 fdate=fdate.split(" ")[0];
				 fromdate=fdate;
		         searchtext=fakclientid; 
				
			}else if(actiontype!=null) {
				if(actiontype.equals("1")) {
			       String fdate=pharmacyDAO.getfakedatebyClientid(fakclientid); 
			        if(fdate!=null){ 
			        fdate=fdate.split(" ")[0]; 
			   }
			  
			  if(fdate!=null) { 
				  if(actiontype.equals("1")){ 
			         fromdate=fdate;
			         searchtext=fakclientid; 
			     } 
			   }
			}
		}
			ArrayList<Priscription> priscriptionlist = new ArrayList<Priscription>();
			int count =pharmacyDAO.getAllOnlineSaleCountNew(searchtext,fromdate,todate,location,isreturn,paymode,loginInfo);
			pagination.setPreperties(count);
			//ArrayList<Priscription> priscriptionlist = prescriptionDAO.getAllPharmacyList(pagination,searchtext,fromdate,todate,ipdloc,opdloc,location);
			priscriptionlist = pharmacyDAO.getAllPharmacySaleListNew(pagination,searchtext,fromdate,todate,location,isreturn,paymode,dontshowreqbill,loginInfo);
			pagination.setTotal_records(priscriptionlist.size());
			emrForm.setTotalRecords(count);
			emrForm.setPagerecords(String.valueOf(pagination.getTotal_records()));
			emrForm.setPriscriptionlist(priscriptionlist);
			ArrayList<Product> requestedMedicineList=pharmacyDAO.getAllRequestedMedicine(fromdate, todate);
			emrForm.setRequestedMedicineList(requestedMedicineList); 
			
			
			
			/*double totalReceived=pharmacyDAO.getTotalRecived(fromdate,todate,location);
			double totalRefund=pharmacyDAO.getTotalRefund(fromdate,todate,location);
			double totalBalance=pharmacyDAO.getTotalBalance(fromdate,todate,location);
			emrForm.setTotalReceived(DateTimeUtils.changeFormat(totalReceived));
			emrForm.setTotalRefund(DateTimeUtils.changeFormat(totalRefund));
			emrForm.setTotalBalance(DateTimeUtils.changeFormat(totalBalance));*/
			
			double totalReceived=0;
			double totalRefund=0;
			double totalBalance=0;
			int size = priscriptionlist.size();
			if (size > 0) {
				totalReceived = priscriptionlist.get(size - 1).getTotalReceived();
				totalRefund = priscriptionlist.get(size - 1).getTotalrefund();
				totalBalance = priscriptionlist.get(size - 1).getTotalBalance();
				
			}
			emrForm.setTotalReceived(DateTimeUtils.changeFormat(totalReceived));
			emrForm.setTotalRefund(DateTimeUtils.changeFormat(totalRefund));
			emrForm.setTotalBalance(DateTimeUtils.changeFormat(totalBalance));
			
			
			fromdate=DateTimeUtils.getCommencingDate1(fromdate);
			todate=DateTimeUtils.getCommencingDate1(todate);
			
			emrForm.setFromdate(fromdate);
			emrForm.setTodate(todate);
			String date=DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone()).split(" ")[0];
			emrForm.setCommencing(DateTimeUtils.getCommencingDate1(date));
			
			
			if(searchtext!=null){
				emrForm.setSearchText(searchtext);
			}
			emrForm.setReturnbill(isreturn);
			emrForm.setPaymode(paymode);
			
			/*if(loginInfo.getJobTitle().equals("Medical Store")){
				
				return "medicalstore";
			}*/
			//lokesh for showing letterhd in pharmacy
			boolean showletterhd=pharmacyDAO.isShowletterhd();
			 if(showletterhd){
				   session.setAttribute("showletterhd","true");
				   }else{
					   session.setAttribute("showletterhd","false"); 
				   }
			session.setAttribute("pagelimit", profile2.getPagelimit());
		} catch (Exception e) {

			e.printStackTrace();
		} finally {
			connection.close();
		}

		return "onlinereqdashboard";
  }
  
  public String recivedcredit(){
	  if (!verifyLogin(request)) {
			return "login";
		 }
	  try {
		Connection connection= Connection_provider.getconnection();
		String fromdate= emrForm.getFromdate();
		String todate=emrForm.getTodate();
		String paymode=emrForm.getPaymode();
		if(paymode==null){
			paymode="";
		}
		if(fromdate==null){
			fromdate="";
		}
		if(todate==null){
			todate="";
		}
		if(fromdate.equals("")){
			DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
		    Calendar cal = Calendar.getInstance();
		    fromdate = dateFormat.format(cal.getTime());  
		}
		if(todate.equals("")){
			DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
		    Calendar cal = Calendar.getInstance();
		    todate = dateFormat.format(cal.getTime());  
		}
		
		PharmacyDAO pharmacyDAO= new JDBCPharmacyDAO(connection);
		ArrayList<Priscription> crditpaymentlist=new ArrayList<Priscription>();
		crditpaymentlist= pharmacyDAO.getpaymentAgainstCredit(fromdate, todate, "",paymode);
		if(crditpaymentlist.size()!=0){
			Priscription p= crditpaymentlist.get(crditpaymentlist.size()-1);
			emrForm.setTotalqty(p.getTotaldays());
		}
	
		
		emrForm.setAllBillList(crditpaymentlist);
		emrForm.setTodate(todate);
		emrForm.setFromdate(fromdate);
	} catch (Exception e) {
		e.printStackTrace();
	}
	  return "recivedcredit";
  }
  
 /* public String deleteproductfromsession() throws Exception {
	  	
	  	Connection connection=null; 
	  	try {
	  		connection=Connection_provider.getconnection();
	  		PharmacyDAO pharmacyDAO = new JDBCPharmacyDAO(connection);
	  		String chargesessionid = request.getParameter("chargesessionid");
	  		String chargetempid = request.getParameter("chargetempid");
	  		int res = pharmacyDAO.deleteProductFromSession(chargesessionid);
	  		int res1 = pharmacyDAO.deleteProductFromTemp(chargetempid);
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
	   }*/
  
/*  public String changeproductfromsession() throws Exception {
	  	
	  	Connection connection=null; 
	  	try {
	  		connection=Connection_provider.getconnection();
	  		PharmacyDAO pharmacyDAO = new JDBCPharmacyDAO(connection);
	  		String chargesessionid = request.getParameter("chargesessionid");
	  		String saleqty = request.getParameter("saleqty");
	  		String chargetempid= request.getParameter("chargetempid");
	  		int res = pharmacyDAO.updateProductFromSession(chargesessionid,saleqty);
	  		int res1 = pharmacyDAO.updateProductFromTemp(chargetempid,saleqty);
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
	   }*/
  
  public String phardiscount() throws Exception {
	  if (!verifyLogin(request)) {
			return "login";
		 }
		Connection connection = null;
		try {
			
			/*ArrayList<Breadcrumbs> indentflowlist = new ArrayList<Breadcrumbs>();
			ArrayList<Breadcrumbs> indentflowlisttemp = new ArrayList<Breadcrumbs>();
			if (session.getAttribute("indentflowlist") != null) {
				indentflowlisttemp = (ArrayList<Breadcrumbs>) session.getAttribute("indentflowlist");
			}
			boolean isavilablemodule= false;
			int modulecount =0;
			for (Breadcrumbs breadcrumbs : indentflowlisttemp) {
				breadcrumbs.setIscurrent(false);
				breadcrumbs.setSqno(modulecount);
				modulecount++;
				if(breadcrumbs.getName().equals("Pharmacy Discount Dashboard")){
					isavilablemodule =true;
					breadcrumbs.setIscurrent(true);
					indentflowlist.add(breadcrumbs);
					break;
				}else{
					indentflowlist.add(breadcrumbs);
				}
			}
			if(!isavilablemodule){
				Breadcrumbs breadcrumbs = new Breadcrumbs();
				breadcrumbs.setName("Pharmacy Discount Dashboard");
				breadcrumbs.setOn(true);
				breadcrumbs.setSqno(modulecount);
				breadcrumbs.setUrllink("phardiscountPharmacy");
				breadcrumbs.setIscurrent(true);
				breadcrumbs.setShowingname("Pharmacy Discount Dashboard");
				indentflowlist.add(breadcrumbs);
			}
			session.setAttribute("indentflowlist",indentflowlist);*/
			
			
			connection = Connection_provider.getconnection();
			PharmacyDAO pharmacyDAO = new JDBCPharmacyDAO(connection);
			
			String searchText = (String)session.getAttribute("pharaprov_searchtext");
			String fromdate = (String)session.getAttribute("pharaprov_fromdate");
			String todate = (String)session.getAttribute("pharaprov_todate");
			
			if(fromdate==null){
				fromdate=emrForm.getFromdate();
			}else{
				session.removeAttribute("pharaprov_fromdate");
			}
			if(todate==null){
				todate=emrForm.getTodate();
			}else{
				session.removeAttribute("pharaprov_todate");
			}
			if(searchText==null){
				searchText=emrForm.getSearchText();
			}else{
				session.removeAttribute("pharaprov_searchtext");
			}
			
			
			if (fromdate == null) {
				DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
				Calendar cal = Calendar.getInstance();
				cal.add(Calendar.DATE, -2);
				fromdate = dateFormat.format(cal.getTime());
				fromdate = DateTimeUtils.getCommencingDate1(fromdate);
			} else {
				if (fromdate.equals("")) {
					DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
					Calendar cal = Calendar.getInstance();
					cal.add(Calendar.DATE, -2);
					fromdate = dateFormat.format(cal.getTime());
					fromdate = DateTimeUtils.getCommencingDate1(fromdate);
					// fromdate = null;
				} else {
					fromdate = DateTimeUtils.getCommencingDate1(fromdate);
				}
			}

			if (todate == null) {
				DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
				Calendar cal = Calendar.getInstance();
				todate = dateFormat.format(cal.getTime());
				todate = DateTimeUtils.getCommencingDate1(todate);
			} else {
				if (todate.equals("")) {
					DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
					Calendar cal = Calendar.getInstance();
					todate = dateFormat.format(cal.getTime());
					todate = DateTimeUtils.getCommencingDate1(todate);
					// todate = null;
				} else {
					todate = DateTimeUtils.getCommencingDate1(todate);
				}
			}
			if(searchText==null){
				searchText="";
			}
			ArrayList<Priscription> discountList = pharmacyDAO.getDiscountList(fromdate,todate,searchText);
			emrForm.setDiscountList(discountList);
			emrForm.setFromdate(DateTimeUtils.getCommencingDate1(fromdate));
			emrForm.setTodate(DateTimeUtils.getCommencingDate1(todate));
			emrForm.setSearchText(searchText);

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			connection.close();
		}
		return "phardiscount";
	}

	public String aprovedbilldiscount() throws Exception {
		if (!verifyLogin(request)) {
 			return "login";
 		 }
		Connection connection = null;
		try {
			connection = Connection_provider.getconnection();
			PharmacyDAO pharmacyDAO = new JDBCPharmacyDAO(connection);
			int billno = emrForm.getBillno();
			int id = emrForm.getId();
			String approve_notes = emrForm.getApprove_notes();
			String fromdate = emrForm.getFromdate();
			String todate = emrForm.getTodate();
			String searchText = emrForm.getSearchText();
			
			String isgroupdiscount = request.getParameter("isgroupdiscount");
			if(isgroupdiscount.equals("0")){
				Priscription discountdata = pharmacyDAO.getDiscountData(id);
				//Priscription priscription = pharmacyDAO.getPharmacyBillDetails(discountdata.getBillno());
				String userid = loginInfo.getUserId();
				String datetime = DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone());
				
				double discvaluetotal = 0;
				if(discountdata.getDisc_type()==0){
					discvaluetotal = discountdata.getTotalamt()*discountdata.getDiscinper()/100;
				}else{
					discvaluetotal = discountdata.getDiscount();
				}
				
				discvaluetotal = Math.round(discvaluetotal);
				if(discvaluetotal>0){
			  		double totaldebit = discountdata.getTotalamt()-discvaluetotal;
			  		int res = pharmacyDAO.updateBillDiscount(discvaluetotal,billno,totaldebit);
			  		ArrayList<Priscription> arrayList = pharmacyDAO.getMedicineChargeList(billno);
			  		double totalgst=0;
			  		for (Priscription priscription2 : arrayList) {
						double tvat= Double.parseDouble(priscription2.getVat());
				    	double tot= 0;
				    	try{
				    		tot= priscription2.getQty() * Double.parseDouble(priscription2.getMrp());
				    	}catch (Exception e) {
							// TODO: handle exception
						}
				    	double temptot=0;
				    	double tempvat =0;
				    	tempvat=tot*discountdata.getDiscinper()/100;
				    	temptot = tot - tempvat;
				    	double dividevat= 100+tvat;
				    	/*double gst= tot*tvat/dividevat;*/
				    	double gst= temptot*tvat/dividevat;
				    	double half= gst/2;
				    	half = Math.round(half*100.0)/100.0;
				    	gst = Math.round(gst*100.0)/100.0;
				    	int es = pharmacyDAO.updateMedicineChargeGST(priscription2.getId(),gst,half,tempvat,tvat);
				    	totalgst = totalgst+gst;
					}
					res =pharmacyDAO.updateDiscountBillStatus(discountdata.getBillno(),2);
			  		int resss = pharmacyDAO.updateDiscountApproveNotes(discountdata.getId(),approve_notes,1,userid,datetime);
			  		int ress = pharmacyDAO.updateBalanceByBill(discountdata.getBillno(), DateTimeUtils.changeFormat(totaldebit));
			  		double cgst = totalgst/2;
			  		ress = pharmacyDAO.updateBillVatNew(Math.round(cgst*100.0)/100.0,Math.round(totalgst*100.0)/100.0,discountdata.getBillno(),discountdata.getDisc_type(),discountdata.getDiscount());
				}	
			}else{
				String aprovediscids = emrForm.getAprovediscids();
				String userid = loginInfo.getUserId();
				String datetime = DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone());
				 for (String newid : aprovediscids.split(",")) {
					   if(newid.equals("0")){
						   continue;
					   }
					   Priscription discountdata = pharmacyDAO.getDiscountData(Integer.parseInt(newid));
					   double discvaluetotal = 0;
					   if(discountdata.getDisc_type()==0){
							discvaluetotal = discountdata.getTotalamt()*discountdata.getDiscinper()/100;
					   }else{
							discvaluetotal = discountdata.getDiscount();
					   }
						
					   discvaluetotal = Math.round(discvaluetotal);
					   if(discvaluetotal>0){
						   double totaldebit = discountdata.getTotalamt()-discvaluetotal;
						   int res = pharmacyDAO.updateBillDiscount(discvaluetotal,Integer.parseInt(discountdata.getBillno()),totaldebit);
						   ArrayList<Priscription> arrayList = pharmacyDAO.getMedicineChargeList(Integer.parseInt(discountdata.getBillno()));
						   double totalgst=0;
						   for (Priscription priscription2 : arrayList) {
							   double tvat= Double.parseDouble(priscription2.getVat());
							   double tot= 0;
							   try{
						    		tot= priscription2.getQty() * Double.parseDouble(priscription2.getMrp());
							   }catch (Exception e) {
									// TODO: handle exception
							   }
							   double temptot=0;
							   double tempvat =0;
							   tempvat=tot*discountdata.getDiscinper()/100;
							   temptot = tot - tempvat;
							   double dividevat= 100+tvat;
							   double gst= temptot*tvat/dividevat;
							   double half= gst/2;
							   half = Math.round(half*100.0)/100.0;
							   gst = Math.round(gst*100.0)/100.0;
							   int es = pharmacyDAO.updateMedicineChargeGST(priscription2.getId(),gst,half,tempvat,tvat);
							   totalgst = totalgst+gst;
							}
							res =pharmacyDAO.updateDiscountBillStatus(discountdata.getBillno(),2);
					  		int resss = pharmacyDAO.updateDiscountApproveNotes(discountdata.getId(),approve_notes,1,userid,datetime);
					  		int ress = pharmacyDAO.updateBalanceByBill(discountdata.getBillno(), DateTimeUtils.changeFormat(totaldebit));
					  		double cgst = totalgst/2;
					  		ress = pharmacyDAO.updateBillVatNew(Math.round(cgst*100.0)/100.0,Math.round(totalgst*100.0)/100.0,discountdata.getBillno(),discountdata.getDisc_type(),discountdata.getDiscount());
					   }
				 }
			}
			
			  
			  
			  session.setAttribute("pharaprov_searchtext",searchText);
			  session.setAttribute("pharaprov_fromdate",fromdate);
			  session.setAttribute("pharaprov_todate",todate);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			connection.close();
		}
		return "phardiscountsave";
	}
	
	public String setsession_added_med(){
		  if (!verifyLogin(request)) {
				return "login";
			 }
		  Connection connection= null;
		  try {
			connection = Connection_provider.getconnection();
			
			PharmacyDAO pharmacyDAO = new JDBCPharmacyDAO(connection);
				String userid = request.getParameter("userid");
				String status = request.getParameter("status");
				
				Calendar cal = Calendar.getInstance();
			    SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
			    String dateString = sdf.format(cal.getTime());
			        
				String Accessdatetime=dateString;
				String CurrentUser=loginInfo.getUserId();
				
				if(status.equals("true")){
					status = "1";
				}
				else {
					status = "0";
				}
				Priscription priscription = pharmacyDAO.setSession_Added_Med(status, userid);
				
				response.setContentType("text/html");
				response.setHeader("Cache-Control", "no-cache");
				response.getWriter().write("");

		} catch (Exception e) {
			e.printStackTrace();
		}
		  return null;
	  }
	
	
	public String tokenrequestdashboard() throws Exception{
		  if (!verifyLogin(request)) {

				return "login";
			}
			Connection connection = null;
			try {
				
				connection = Connection_provider.getconnection();
				PharmacyDAO pharmacyDAO=new JDBCPharmacyDAO(connection);
				
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
				
				ArrayList<Priscription> priscriptionlist = new ArrayList<Priscription>();
				priscriptionlist = pharmacyDAO.getPriscInSaleList(fromdate,todate);
				emrForm.setPriscriptionlist(priscriptionlist);
				
				fromdate=DateTimeUtils.getCommencingDate1(fromdate);
				todate=DateTimeUtils.getCommencingDate1(todate);
				emrForm.setFromdate(fromdate);
				emrForm.setTodate(todate);
				
				emrForm.setIsfromtokendashbaord(1);
				
			} catch (Exception e) {

				e.printStackTrace();
			} finally {
				connection.close();
			}

			return "tokenrequestdashboard";
	  }
	
	public String doctorreportdatewise() throws Exception {
		   if (!verifyLogin(request)) {

				return "login";
			}
		   Connection connection=null;
		   try {
			   
			   /*ArrayList<Breadcrumbs> indentflowlist = new ArrayList<Breadcrumbs>();
				ArrayList<Breadcrumbs> indentflowlisttemp = new ArrayList<Breadcrumbs>();
				if (session.getAttribute("indentflowlist") != null) {
					indentflowlisttemp = (ArrayList<Breadcrumbs>) session.getAttribute("indentflowlist");
				}
				boolean isavilablemodule= false;
				int modulecount =0;
				for (Breadcrumbs breadcrumbs : indentflowlisttemp) {
					breadcrumbs.setIscurrent(false);
					breadcrumbs.setSqno(modulecount);
					modulecount++;
					if(breadcrumbs.getName().equals("Pharmacy Doctor Report")){
						isavilablemodule =true;
						breadcrumbs.setIscurrent(true);
						indentflowlist.add(breadcrumbs);
						break;
					}else{
						indentflowlist.add(breadcrumbs);
					}
				}
				if(!isavilablemodule){
					Breadcrumbs breadcrumbs = new Breadcrumbs();
					breadcrumbs.setName("Pharmacy Doctor Report");
					breadcrumbs.setOn(true);
					breadcrumbs.setSqno(modulecount);
					breadcrumbs.setUrllink("doctorreportPharmacy");
					breadcrumbs.setIscurrent(true);
					breadcrumbs.setShowingname("Doctor Report");
					indentflowlist.add(breadcrumbs);
				}
				session.setAttribute("indentflowlist",indentflowlist);*/
			   
			   connection=Connection_provider.getconnection();
			   PharmacyDAO pharmacyDAO=new JDBCPharmacyDAO(connection);
			   String fromdate=emrForm.getFromdate();
			   String todate=emrForm.getTodate();
			   String phar_practid = emrForm.getPhar_practid();
			   if(fromdate==null){
				   
				   SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd");
				   Calendar calendar=Calendar.getInstance();
				  /* calendar.add(Calendar.DATE, -30);*/
				   fromdate=format.format(calendar.getTime());
				   
			   } else {
				   
				   if(fromdate.equals("")){
					    
					   SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd");
					   Calendar calendar=Calendar.getInstance();
					  /* calendar.add(Calendar.DATE, -30);*/
					   fromdate=format.format(calendar.getTime());
				   } else {
					   fromdate=DateTimeUtils.getCommencingDate1(fromdate);	
				   }
			   }
			   
			   if(todate==null){
				   
				   SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd");
				   Calendar calendar=Calendar.getInstance();
				   todate=format.format(calendar.getTime());
				   
			   } else {
				   
				   if(todate.equals("")){
					    
					   SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd");
					   Calendar calendar=Calendar.getInstance();
					   todate=format.format(calendar.getTime());
				   } else {
					   todate=DateTimeUtils.getCommencingDate1(todate);	
				   }
			   }
			   
			   if(DateTimeUtils.isNull(phar_practid).equals("")){
				   phar_practid="0";
			   }
			   
			   ChargesReportDAO chargesReportDAO = new JDBCChargesReportDAO(connection);
			   DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			   Calendar cal1 = Calendar.getInstance();
			   String date = dateFormat.format(cal1.getTime());  
			     
			   int result = chargesReportDAO.saveMisReportLog("Date Wise Doctor Report",loginInfo.getUserId(),fromdate,todate,date,"doctorreportdatewisePharmacy");
			   ArrayList<Priscription> doctorreportList = new ArrayList<Priscription>();
			   
			   long differ = DateTimeUtils.getDifferenceOfTwoDateDBFormat(fromdate, todate);
			   int k = (int)differ;
			   for (int i=0; i <= k; i++) {
				   SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
				   Date d=sdf1.parse(fromdate);
				   Calendar cal = Calendar.getInstance();
				   cal.setTime(d);
				   cal.add(Calendar.DATE, i);
				   String dt=sdf1.format(cal.getTime());
				   ArrayList<Priscription> userlist=pharmacyDAO.getAllDoctorReportList(dt,dt,phar_practid); 
				   doctorreportList.addAll(userlist);
			   }
			   
			  
			   emrForm.setDoctorreportList(doctorreportList);
			   
			   session.setAttribute("doctorreport", doctorreportList);
			   
			   fromdate=DateTimeUtils.getCommencingDate1(fromdate);
			   todate=DateTimeUtils.getCommencingDate1(todate);
			   
			   emrForm.setFromdate(fromdate);
			   emrForm.setTodate(todate);
			   emrForm.setPhar_practid(phar_practid);
			   
			   String location =(String) session.getAttribute("location");
			   if(location==null){
			       location="0";
			   }
			   UserProfileDAO userProfileDAO = new JDBCUserProfileDAO(connection);
			   UserProfile userProfile= userProfileDAO.getPharmacyUserDetails(loginInfo.getClinicid());
		       emrForm.setClinicName(userProfile.getClinicname());
		       emrForm.setClinicaddress(userProfile.getAddress());
		       emrForm.setLandLine(userProfile.getPhone());
		       emrForm.setFullname(userProfile.getFullname());
		       emrForm.setEmail(userProfile.getEmail());
		       emrForm.setPlace(userProfile.getCity());
		       emrForm.setWebsiteUrl(userProfile.getWebsite());
		       emrForm.setLocation(pharmacyDAO.getLocationName(location));
		       emrForm.setUserid(loginInfo.getUserId());
		       
		       ArrayList<UserProfile> pharmacydrlist = userProfileDAO.getPharmacyDoctorList();
		       emrForm.setPharmacydrlist(pharmacydrlist);
			   
		   } catch (Exception e) {

			   e.printStackTrace();
		   }
		   finally {
			    connection.close();
		   }
		   
		   return "doctorreportdatewise";
	   }
	
	
	public String updatepharmacyaccess(){
		  if (!verifyLogin(request)) {
				return "login";
			 }
		  Connection connection= null;
		  try {
			connection = Connection_provider.getconnection();
			
			PharmacyDAO pharmacyDAO = new JDBCPharmacyDAO(connection);
				String userid = request.getParameter("userid");
				String status = request.getParameter("status");
				String colname = request.getParameter("colname");
				Calendar cal = Calendar.getInstance();
			    SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
			    String dateString = sdf.format(cal.getTime());
			        
				String Accessdatetime=dateString;
				String CurrentUser=loginInfo.getUserId();
				
				if(status.equals("true")){
					status = "1";
				}
				else {
					status = "0";
				}
				int res = pharmacyDAO.updatepharmacyaccess(status, userid,colname);
				
				response.setContentType("text/html");
				response.setHeader("Cache-Control", "no-cache");
				response.getWriter().write("");

		} catch (Exception e) {
			e.printStackTrace();
		}
		  return null;
	  }
	
	
	public String editpharmacybill() throws Exception{ 
		   if (!verifyLogin(request)) {

				return "login";
			}
		   Connection connection=null;  
			 try {
				 int billno=Integer.parseInt(request.getParameter("billno"));
				 int selectedid=Integer.parseInt(request.getParameter("selectedid"));
				
		 		 connection=Connection_provider.getconnection();
				 PharmacyDAO pharmacyDAO=new JDBCPharmacyDAO(connection);
				 BedDao bedDao = new JDBCBedDao(connection);
				 IpdDAO  ipdDAO = new JDBCIpdDAO(connection);
				 EmrDAO emrDAO=new JDBCEmrDAO(connection);
				 ClientDAO clientDAO=new JDBCClientDAO(connection);
				 UserProfileDAO profileDAO=new JDBCUserProfileDAO(connection);
				 
				CompleteAppointment completeAppointment=pharmacyDAO.getBillDetails(billno);
				String paymode =pharmacyDAO.getBillPaymodeNew(billno);
				//  31 July 2018
				double discPerGivenEach= pharmacyDAO.getDiscountPerReturnMedicine(""+billno,completeAppointment.getTotal());
				ArrayList<Priscription> priscriptionlist=pharmacyDAO.getMedicineReturnCharges(billno, completeAppointment.getReturnbillid(),discPerGivenEach,1);
				 
				int size = priscriptionlist.size();
				if (size > 0) {
					int isinadjustment = priscriptionlist.get(size - 1).getIsinadjustment();
					if(isinadjustment==1){
						return "isinadjusterrorpage";
					}
				}
				emrForm.setDiscper(DateTimeUtils.changeFormat(discPerGivenEach));
				String dateTime=DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone()).split(" ")[0];
				emrForm.setPriscriptionlist(priscriptionlist);
				double subtotal=0;
				for(Priscription p:priscriptionlist){
					subtotal=subtotal+(Double.parseDouble(p.getMrp())*p.getSaleqty());
				}
				if(selectedid>0){
					Priscription priscription=emrDAO.getPriscriptionParentData(selectedid);
				  	String practitionerName=profileDAO.getUserprofileDetails(Integer.parseInt(priscription.getPrectionerid())).getFullname();
				    String ipdid = emrDAO.getpriscIpdId(selectedid);
					Bed bed = bedDao.getEditIpdData(ipdid);
					emrForm.setIpdid(ipdid);
					String wardname=ipdDAO.getIpdWardName(bed.getWardid());
					String bedname = ipdDAO.getIpdBedName(bed.getBedid());
					emrForm.setWardname(wardname);
					emrForm.setBedname(bedname);
					String wardbed=wardname+"/"+bedname;
					emrForm.setWardname(wardbed);
					Client client=clientDAO.getClientDetails(completeAppointment.getClientId());
					String name = client.getTitle()+" "+client.getFirstName()+" "+client.getMiddlename()+" "+client.getLastName();
					emrForm.setFullname(name);
					String date  = DateTimeUtils.getDashboardTodayDate(loginInfo.getCountry());
					emrForm.setDateTime(completeAppointment.getInvoiceDate());
					emrForm.setSelectedid(selectedid);
					String dobyear[] = client.getDob().split("/");
					String curryear[] = date.split("/");
					
					int  age = Integer.parseInt(curryear[2]) - Integer.parseInt(dobyear[2]);
					client.setAge(age);
					String agedate=client.getGender()+"/"+age;
					emrForm.setAgeandgender(agedate);
					emrForm.setPractitionerName(practitionerName);
					//String balance= pharmacyDAO.getbalofpatient(completeAppointment.getClientId());
					double creditbalance= pharmacyDAO.getPharClientBalance("0",completeAppointment.getClientId());
					emrForm.setBalance(""+Math.round(creditbalance*100.0)/100.0);
					emrForm.setOpdid(completeAppointment.getClientId());
					emrForm.setMobno(client.getMobNo());
				}else {
				   String pclientid=pharmacyDAO.getPharmacyClientidFromBill(billno);
				   if(!pclientid.equals("0")){						
					   Priscription priscription=pharmacyDAO.getPharmacyPatient(pclientid);
					   emrForm.setWardname(priscription.getAddress());
					   emrForm.setFullname(priscription.getFullname());
					   emrForm.setSelectedid(selectedid);
					   emrForm.setAgeandgender(priscription.getAgeandgender());
					   emrForm.setPractitionerName(priscription.getPractitionername());
					  /* emrForm.setBalance(priscription.getBalance());*/
					   emrForm.setOpdid(pclientid);
					   emrForm.setMobno(priscription.getMobile());
					   double creditbalance= pharmacyDAO.getPharClientBalance("1",pclientid);
					   emrForm.setBalance(""+Math.round(creditbalance*100.0)/100.0);
				   }else{
						String ipdopd=pharmacyDAO.getClientIDFromBillNo(billno);
						Client client = clientDAO.getPatient(Integer.parseInt(ipdopd));
						Double pre_balance = Double.parseDouble(client.getBalance());
						String name = client.getTitle()+" "+client.getFirstName()+" "+client.getMiddlename()+" "+client.getLastName();
						String date  = DateTimeUtils.getDashboardTodayDate(loginInfo.getCountry());
						String dobyear[] = client.getDob().split("/");
						String curryear[] = date.split("/");
						int  age = Integer.parseInt(curryear[2]) - Integer.parseInt(dobyear[2]);
						client.setAge(age);
						String ipdid = pharmacyDAO.getIpdIdFromClientID(Integer.parseInt(ipdopd));
						String pract_name ="";
					if(ipdid.equals("0")){
						pract_name = pharmacyDAO.getappointmentinfo(Integer.parseInt(ipdopd));
					}else{
						Bed  bed = bedDao.getEditIpdData(ipdid);
						profileDAO = new JDBCUserProfileDAO(connection);
						pract_name = profileDAO.getName(bed.getPractitionerid());
					}
						emrForm.setPractitionerName(pract_name);
				
						String ipdoropd = "0";
						if(ipdid.equals("0")){
							ipdoropd ="0";
						}else{
							ipdDAO = new JDBCIpdDAO(connection);
							Bed  bed = bedDao.getEditIpdData(ipdid);
							String wardname = ipdDAO.getIpdWardName(bed.getBedid());
							String bedname = ipdDAO.getIpdBedName(bed.getWardid());
							ipdoropd = ipdid;
							String wardbed = wardname +"/"+ bedname;
							emrForm.setWardbed(wardbed);
						}
							emrForm.setIpdoropd(ipdoropd);
					
						emrForm.setWardname(client.getAddress());
						emrForm.setFullname(name);
						emrForm.setSelectedid(selectedid);
						//emrForm.setAgeandgender(priscription.getAgeandgender());
						emrForm.setPractitionerName(pract_name);
						emrForm.setBalance(""+pre_balance);
						emrForm.setOpdid(pclientid);
						emrForm.setMobno(client.getMobNo());
						double creditbalance= pharmacyDAO.getPharClientBalance("0",ipdopd);
						emrForm.setBalance(""+Math.round(creditbalance*100.0)/100.0);
				   }
				}
				emrForm.setPractitionerName(completeAppointment.getPractitionerName());
				emrForm.setPhar_practid(completeAppointment.getPractitionerId());
				emrForm.setDateTime(DateTimeUtils.getCommencingDate1(dateTime));
				emrForm.setSubtotal(DateTimeUtils.changeFormat(subtotal));
				emrForm.setMedicineChargeList(priscriptionlist);
				emrForm.setClientId(completeAppointment.getClientId());
				emrForm.setTotal(DateTimeUtils.changeFormat(completeAppointment.getTotal()));
				emrForm.setDiscount(String.valueOf(completeAppointment.getDiscount()));
				emrForm.setVat(String.valueOf(completeAppointment.getVat()));
				emrForm.setBillno(billno);
				emrForm.setReturnmode(paymode);
				  
			 } catch (Exception e) {

				e.printStackTrace();
			 }
			 finally {
					connection.close();
			 }
		  return "editpharmacybill"; 
	   }
	
	 public String updatepharmacybill() throws Exception {
		   if (!verifyLogin(request)) {

				return "login";
			}
		   Connection connection=null;
			try {
				connection=Connection_provider.getconnection();
				PharmacyDAO pharmacyDAO=new JDBCPharmacyDAO(connection);
				InventoryProductDAO inventoryProductDAO=new JDBCInventoryProductDAO(connection);
				EmrDAO emrDAO=new JDBCEmrDAO(connection);
				UserProfileDAO userProfileDAO=new JDBCUserProfileDAO(connection);
				ClientDAO clientDAO=new JDBCClientDAO(connection);
				BedDao bedDao = new JDBCBedDao(connection);
				IpdDAO  ipdDAO = new JDBCIpdDAO(connection);
				UserProfileDAO profileDAO=new JDBCUserProfileDAO(connection);
				String paynote="";
				int selectedid=emrForm.getSelectedid();
			    String paymode=emrForm.getPaymode();
			    String bill=request.getParameter("billno"); 
			    
			    String loc =(String) session.getAttribute("location");
			    if(loc==null){
			    	loc="0";
			    }
			    int billno=Integer.parseInt(bill);
			    
			    CompleteAppointment completeAppointment=pharmacyDAO.getBillDetails(billno);
			    double discPerGivenEach= pharmacyDAO.getDiscountPerReturnMedicine(bill,completeAppointment.getTotal());
			    
			    	    
			    Priscription priscription=new Priscription();
			    String date=DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone()).split(" ")[0];
			   
			    UserProfile userProfile=null;
			    int updatedinlocal=0;
			    for(Priscription prisc:emrForm.getMedicines()){
			    	
			    	if(prisc==null){
			    		continue;
			    	}
			    	if(prisc.getId()==0){
			    		continue;
			    	}
			    	if(prisc.getReturnqty()==0){
			    		continue;
			    	}
			    	priscription.setProduct_id(prisc.getProduct_id());
			    	Product product=inventoryProductDAO.getProduct(prisc.getProduct_id());
			    	priscription.setSaleqty(prisc.getReturnqty());
			    	priscription.setReqqty(prisc.getReqqty());
			    	priscription.setMrp(product.getSale_price());
			    	priscription.setDate(date);
			    	int previousstock = inventoryProductDAO.getPreviousStock(priscription.getProduct_id());
			    	int previousqty = pharmacyDAO.getPreviousSaleQty(prisc.getId());
			    	updatedinlocal=1;
			    	int newqty = previousqty - prisc.getReturnqty();
			    	int result=pharmacyDAO.updateMedicineChargesQty(newqty,prisc.getId());
			    	result=inventoryProductDAO.updateMedicineQty(priscription.getSaleqty(),priscription.getProduct_id(),1);
			    	
			    	//stock log
					String datetime = DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone());
					int qtyinout=0;
					String source ="Pharmacy Update";
					int currentstock=inventoryProductDAO.getPreviousStock(priscription.getProduct_id());
					int changeqty=priscription.getSaleqty();
					int reslog = inventoryProductDAO.insertIntoProductLog(loginInfo.getUserId(),datetime,product.getLocation(),qtyinout,priscription.getProduct_id(),product.getCatalogueid(),source,currentstock,previousstock,changeqty,"0","0",billno,0,0,"0");
	    			
					String date2 =  DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone()).split(" ")[0];
					boolean checkopningstockexist = pharmacyDAO.checkopeningstockexist(priscription.getProduct_id(),date2);
					if(!checkopningstockexist){
						int r = pharmacyDAO.saveOpeningStock(priscription.getProduct_id(),date2,previousstock,"0");
					}
			    }
			    
			    if(updatedinlocal==1){
			    	//  31 July 2018
					ArrayList<Priscription> priscriptionlist=pharmacyDAO.getMedicineReturnChargesList(billno);
					
					double totaldebit=0;
					double grosssubtotal =0;
					double totalvats=0;
					for (Priscription prisc1 : priscriptionlist) {
						priscription.setProduct_id(prisc1.getProduct_id());
				    	Product product=inventoryProductDAO.getProduct(prisc1.getProduct_id());
				    	priscription.setSaleqty(prisc1.getQty());
				    	priscription.setReqqty(prisc1.getQty());
				    	priscription.setMrp(DateTimeUtils.isNull(""+prisc1.getCharges()));
				    	priscription.setDate(date);
				    					    	
				    	double tvat= Double.parseDouble(prisc1.getGstper());
				    	double tot= 0;
				    	tot= priscription.getSaleqty() * Double.parseDouble(priscription.getMrp());
				    	grosssubtotal = grosssubtotal + tot;
				    	double temptot=0;
				    	double tempvat =0;
				    	
				    	tempvat=tot*discPerGivenEach/100;
				    	temptot = tot - tempvat;
				    	double dividevat= 100+tvat;
				    	double gst= temptot*tvat/dividevat;
				    	totalvats = totalvats +gst;
				    	double half= gst/2;
				    	half = Math.round(half*100.0)/100.0;
				    	gst = Math.round(gst*100.0)/100.0;
				    	totaldebit = totaldebit + temptot;
				    	priscription.setTgstamt(DateTimeUtils.changeFormat(gst));
				    	priscription.setGstper(prisc1.getGstper());
				    	priscription.setSharediscount(DateTimeUtils.changeFormat(tempvat));
				    	priscription.setCgst(DateTimeUtils.changeFormat(half));
				    	priscription.setSgst(DateTimeUtils.changeFormat(half));
				    	priscription.setId(prisc1.getId());
				    	double bill_purpice=product.getIndividual_pur_price()*priscription.getSaleqty();
		    			double bill_pur_gst=product.getIndi_purchase_gst()*priscription.getSaleqty();
		    			priscription.setBill_purpice(bill_purpice);
		    			priscription.setBill_pur_gst(bill_pur_gst);
				    	int res = pharmacyDAO.updateEditPharmacyCharges(priscription);
					}
					
					double grosstotal=0;
					double discount1= (discPerGivenEach*grosssubtotal)/100;
					double balance = grosssubtotal - discount1;
					grosstotal = balance;
	    			balance = Math.round(balance);
	    			String notes = DateTimeUtils.isNull(emrForm.getNotes());
	    			String datetime = DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone());
	    			String userid = loginInfo.getUserId();
	    			int updatebill = pharmacyDAO.updatePharmacyEditBill(billno,discount1,grosssubtotal,grosstotal,balance,totalvats,""+discPerGivenEach,notes,datetime,userid);
	    			
			    }
			    
			    session.setAttribute("billno", billno);
				session.setAttribute("selectedid", 0);
					
				
			} catch (Exception e) {

				e.printStackTrace();
				
			}finally{
				connection.close();
			}
			
		    return "redirectbill";
	   }
	
	 public String patientdashboard() throws Exception{

			
			Connection connection=null;
			try {
				
	            connection=Connection_provider.getconnection();
	            PharmacyDAO pharmacyDAO = new JDBCPharmacyDAO(connection);
	            String location=(String) session.getAttribute("location");
	            if(location==null){
	            	location="0";
	            }
	            String searchtext = emrForm.getSearchText();
	            searchtext = DateTimeUtils.isNull(searchtext);
	            
				int totalcount=pharmacyDAO.getPharmacyPatientCount(searchtext);
				pagination.setPreperties(totalcount);
				ArrayList<Client> patientlist=pharmacyDAO.getPharmacyPatientList(pagination,searchtext);
				pagination.setPage_records(patientlist.size());
				emrForm.setTotalRecords(totalcount);
				emrForm.setPagerecords(String.valueOf(pagination.getPage_records()));
				emrForm.setPatientlist(patientlist);
				emrForm.setSearchText(searchtext);
				UserProfileDAO userProfileDAO = new JDBCUserProfileDAO(connection);
				ArrayList<UserProfile> pharmacydrlist = userProfileDAO.getPharmacyDoctorList();
				emrForm.setPharmacydrlist(pharmacydrlist);
			} catch (Exception e) {

			   e.printStackTrace();
			}
		    finally {
		    	connection.close();
		    }
		return "patientdashboard";
	 }
	 public String medicinesale() throws Exception{
			

			if (!verifyLogin(request)) {
				return "login";
			}
			Connection connection = null;
			try {
				connection = Connection_provider.getconnection();
				

				InventoryProductDAO inventoryProductDAO = new JDBCInventoryProductDAO(connection);
				PharmacyDAO pharmacyDAO = new JDBCPharmacyDAO(connection);

				// ArrayList<Product> productList=
				// catalogueDAO.getAllProductListItemWiseSale(location);
				
				String fromdate = emrForm.getFromdate();
				String todate = emrForm.getTodate();
				

				String isfromoepning = request.getParameter("isfromoepning");
				int isfromoepningclosing = 0;
				String catalogueid = "0";
				
				if (fromdate != null) {

					if (!fromdate.equals("")) {
							fromdate = DateTimeUtils.getCommencingDate1(fromdate);
					} else {

						Calendar calendar = Calendar.getInstance();
						SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
						// calendar.add(Calendar.DATE, -7);
						fromdate = dateFormat.format(calendar.getTime());
					}
				} else {
					Calendar calendar = Calendar.getInstance();
					SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
					// calendar.add(Calendar.DATE, -7);
					fromdate = dateFormat.format(calendar.getTime());

				}
				if (todate != null) {

					if (!todate.equals("")) {
							todate = DateTimeUtils.getCommencingDate1(todate);
					} else {

						Calendar calendar = Calendar.getInstance();
						SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
						todate = dateFormat.format(calendar.getTime());
					}
				} else {
					Calendar calendar = Calendar.getInstance();
					SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
					todate = dateFormat.format(calendar.getTime());

				}

				ChargesReportDAO chargesReportDAO = new JDBCChargesReportDAO(connection);
				DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				Calendar cal = Calendar.getInstance();
				String date = dateFormat.format(cal.getTime());
				ThirdPartyDAO thirdPartyDAO=new JDBCThirdPartyDAO(connection);
				ArrayList<ThirdParty> thirdPartyList=new ArrayList<>();
				String payee=emrForm.getPayee();
				String tpid=emrForm.getTpid();
				
				if(payee==null){
					payee="0";
				}
				if(loginInfo.isParihar()) {
					thirdPartyList=thirdPartyDAO.getMainTPListNew(payee);
					emrForm.setThirdPartyTypeList(thirdPartyList);
				}else {
					thirdPartyList=thirdPartyDAO.getMainTPListNew("0");
					emrForm.setThirdPartyTypeList(thirdPartyList);
				}
				/*
				 * int count = inventoryProductDAO.getAllItemWiseReportListCount(fromdate,
				 * todate, product_id, location, product_type, isfromoepningclosing,
				 * catalogueid);
				 */
				//pagination.setPreperties(count);

				ArrayList<Product> medicinewisesalereport = new ArrayList<Product>();
				
				medicinewisesalereport = inventoryProductDAO.getMedicineWiseSaleReportList(fromdate,todate,pagination,payee,tpid);
				emrForm.setMedicinesaleList(medicinewisesalereport);
				//productForm.setItemWiseListReport(itemWiseListReport);
				// productForm.setProductList(productList);

				//pagination.setTotal_records(itemWiseListReport.size());
				//productForm.setTotalRecords(count);
				//productForm.setPagerecords(String.valueOf(pagination.getTotal_records()));

				fromdate = DateTimeUtils.getCommencingDate1(fromdate);
				todate = DateTimeUtils.getCommencingDate1(todate);
				emrForm.setFromdate(fromdate);
				emrForm.setTodate(todate);


			} catch (Exception e) {

				e.printStackTrace();
			} finally {
				connection.close();
			}

			return "medicinesalereport";
  
}
	 
}
