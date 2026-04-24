package com.apm.DiaryManagement.web.action;

import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.nio.channels.ClosedByInterruptException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.xml.bind.DatatypeConverter;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;

import com.apm.Accounts.eu.bi.AccountsDAO;
import com.apm.Accounts.eu.bi.ChargesAccountProcessingDAO;
import com.apm.Accounts.eu.blogic.jdbc.JDBCAccountsDAO;
import com.apm.Accounts.eu.blogic.jdbc.JDBCChargeAccountProcesDAO;
import com.apm.Accounts.eu.entity.Accounts;
import com.apm.DiaryManagement.eu.bi.ClientDAO;
import com.apm.DiaryManagement.eu.bi.CompleteAptmDAO;
import com.apm.DiaryManagement.eu.bi.FinderDAO;
import com.apm.DiaryManagement.eu.bi.NotAvailableSlotDAO;
import com.apm.DiaryManagement.eu.blogic.jdbc.JDBCClientDAO;
import com.apm.DiaryManagement.eu.blogic.jdbc.JDBCCompleteAptmDAO;
import com.apm.DiaryManagement.eu.blogic.jdbc.JDBCFinderDAO;
import com.apm.DiaryManagement.eu.blogic.jdbc.JDBCNotAvailableSlotDAO;
import com.apm.DiaryManagement.eu.entity.Client;
import com.apm.DiaryManagement.eu.entity.CompleteAppointment;
import com.apm.DiaryManagement.eu.entity.GpData;
import com.apm.DiaryManagement.eu.entity.NotAvailableSlot;
import com.apm.DiaryManagement.eu.entity.Priscription;
import com.apm.DiaryManagement.web.form.ClientForm;
import com.apm.Emr.eu.bi.EmrDAO;
import com.apm.Emr.eu.blogic.jdbc.JDBCEmrDAO;
import com.apm.Inventory.eu.bi.InventoryVendorDAO;
import com.apm.Inventory.eu.blogic.jdbc.JDBCInventoryVendorDAO;
import com.apm.Ipd.eu.bi.IpdDAO;
import com.apm.Ipd.eu.blogic.jdbc.JDBCIpdDAO;
import com.apm.Log.eu.bi.AccountLogDAO;
import com.apm.Log.eu.blogic.jdbc.JDBCAccountLogDAO;
import com.apm.Log.eu.entity.EmailLetterLog;
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
import com.apm.Registration.eu.entity.UserProfile;
import com.apm.ThirdParties.eu.entity.ThirdParty;
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
import com.sun.mail.iap.Response;

import atg.taglib.json.util.JSONObject;

public class RegistrationAction extends BaseAction implements ModelDriven<ClientForm> {
	
	HttpServletRequest request = (HttpServletRequest) ActionContext.getContext().get(ServletActionContext.HTTP_REQUEST);
	HttpServletResponse Response = (HttpServletResponse) ActionContext.getContext().get(ServletActionContext.HTTP_RESPONSE);
	HttpSession session = request.getSession(true);
	
	LoginInfo loginInfo = LoginHelper.getLoginInfo(request);
	Pagination pagination = new Pagination(25, 1);
	public Pagination getPagination() {
		return pagination;
	}
	
	
	ClientForm clientForm = new ClientForm();
	@Override
	public ClientForm getModel() {
		return clientForm;
	}
	
	public String addCompleteInfo() throws SQLException{
		if(!verifyLogin(request)){
			return "login";
		}
		Connection connection = null;
		ArrayList<Client> thirdPartyTypeList = new ArrayList<Client>();
		ArrayList<Client> thirdPartyTypeNameList = new ArrayList<Client>();
		ArrayList<Client> clientOccupationList = new ArrayList<Client>();
		ArrayList<Client> refrenceList = new ArrayList<Client>();
		ArrayList<String> initialList = new ArrayList<String>();
		ArrayList<Client> sourceOfIntroList = new ArrayList<Client>();
		String date = DateTimeUtils.isNull(clientForm.getFromdate()) ;
		try{
			
			connection = Connection_provider.getconnection();
			ClientDAO clientDAO = new JDBCClientDAO(connection);

			if (date == null) {
				DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
				Calendar cal = Calendar.getInstance();
				date = dateFormat.format(cal.getTime());
				date = DateTimeUtils.getCommencingDate1(date);
			}else {

				if (date.equals("")) {
					DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
					Calendar cal = Calendar.getInstance();
					date = dateFormat.format(cal.getTime());
					date = DateTimeUtils.getCommencingDate1(date);
				} else {
					date = DateTimeUtils.getCommencingDate1(date);
				}
		   }
			
			//clientOccupationList = clientDAO.getOccupationList();
			
			
			//clientForm.setClientOccupationList(clientOccupationList);
			
			//refrenceList = clientDAO.getReferenceList();
			
			//clientForm.setRefrenceList(refrenceList);
			
			initialList = clientDAO.getInitialList();
			clientForm.setInitialList(initialList);
			
			//sourceOfIntroList = clientDAO.getSourceOfIntroList();
			//clientForm.setSourceOfIntroList(sourceOfIntroList);
			
			//ArrayList<Client>surgeryList = clientDAO.getSurgeryList();
			//clientForm.setSurgeryList(surgeryList);
			//clientForm.setHourList(PopulateList.hourList());
			//clientForm.setMinuteList(PopulateList.getMinuteList());
			
			Client client=new Client();
			ArrayList<Client>planlist=clientDAO.getAllPlanlist();
			clientForm.setPlanlist(planlist);
			
			ArrayList<Client>chargelist=clientDAO.getAllChargeslist();
			clientForm.setChargelist(chargelist);
			
			//set state and city list
			InventoryVendorDAO vendorDAO=new JDBCInventoryVendorDAO(connection);
			ArrayList<Master> stateList= vendorDAO.getAllStateList();
			ArrayList<Master> cityList= vendorDAO.getAllCityList();
			clientForm.setStatelist(stateList);
			clientForm.setCitylist(cityList);
			
			clientForm.setCountryList(PopulateList.countryList());
			clientForm.setBirthPlaceList(new ArrayList<>());
			clientForm.setHourList(new ArrayList<>());
			clientForm.setMinuteList(new ArrayList<>());
			clientForm.setRefrenceList(new ArrayList<>());
			clientForm.setDocuList(new ArrayList<>());
			clientForm.setSurgeryList(new ArrayList<>());
			clientForm.setSourceOfIntroList(new ArrayList<>());
			clientForm.setClientOccupationList(new ArrayList<>());
			clientForm.setCondtitionList(new ArrayList<>());
			clientForm.setCountry("India");
			
			if(loginInfo.isLmh()){
				clientForm.setWhopay("Client");
				MasterDAO masterDAO=new JDBCMasterDAO(connection);
				CompleteAptmDAO completeAptmDAO = new JDBCCompleteAptmDAO(connection);
				// department ot list
				ArrayList<Master> departmentList = masterDAO.getDisciplineDataListWithChecked();
				clientForm.setDepartmentList(departmentList);
//				ArrayList<DiaryManagement> userList = notAvailableSlotDAO.getUserList(loginInfo.getId());
//				clientForm.setUserList(userList);
				/*double registrationcharge = completeAptmDAO.getOpdRegCharge();
				clientForm.setRegistrationcharge(String.valueOf(registrationcharge));
				clientForm.setNetamount(String.valueOf(registrationcharge));*/
				ArrayList<Master> camploactionList= masterDAO.getAllCampLocation();
				clientForm.setCamplocationList(camploactionList);				
				double registrationcharge = 0.0;
				clientForm.setRegistrationcharge(String.valueOf(registrationcharge));
				clientForm.setNetamount(String.valueOf(registrationcharge));
				
				thirdPartyTypeList = clientDAO.getThirdPartyTypeNew("0");
				clientForm.setThirdPartyTypeList(thirdPartyTypeList);
				
				clientForm.setThirdPartyTypeNameList(thirdPartyTypeNameList);
				clientForm.setFromdate(DateTimeUtils.getCommencingDate1(date));
				
				return "addnewpatient";
			}else{
				thirdPartyTypeList = clientDAO.getThirdPartyType();
				clientForm.setThirdPartyTypeList(thirdPartyTypeList);
				
				thirdPartyTypeNameList = clientDAO.getThirdPartyTypeName();
				clientForm.setThirdPartyTypeNameList(thirdPartyTypeNameList);
			}
			
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		finally{
			connection.close();
		}
		return "addCompleteInfo";
	}
	
	public String edit() throws SQLException{
		if(!verifyLogin(request)){
			return "login";
		}
		ArrayList<Client> thirdPartyTypeList = new ArrayList<Client>();
		ArrayList<Client> thirdPartyTypeNameList = new ArrayList<Client>();
		ArrayList<Client> clientOccupationList = new ArrayList<Client>();
		ArrayList<Client> refrenceList = new ArrayList<Client>();
		ArrayList<String> initialList = new ArrayList<String>();
		Connection connection = null;
		try{
			int id = Integer.parseInt(request.getParameter("selectedid"));
			Client client = new Client();
			connection = Connection_provider.getconnection();
			ClientDAO clientDAO = new JDBCClientDAO(connection);
			clientOccupationList = clientDAO.getOccupationList();
			Client client1 = new Client();
			client1.getOther();
			clientOccupationList.add(client1);
			
			
			clientForm.setClientOccupationList(clientOccupationList);
			
			
			refrenceList = clientDAO.getReferenceList();
			Client client2 = new Client();
			client2.getOther();
			refrenceList.add(client2);
			clientForm.setRefrenceList(refrenceList);
			
			initialList = clientDAO.getInitialList();
			clientForm.setInitialList(initialList);
			
			ArrayList<Client>surgeryList = clientDAO.getSurgeryList();
			clientForm.setSurgeryList(surgeryList);
			
	
			
			client = clientDAO.getPatient(id);
			
			Client planClient=clientDAO.getPatientActiveplanDetails(""+clientDAO.getPatientActiveplan(id));
			clientForm.setRegularpatient(client.getRegularpatient());
			clientForm.setPlanid(planClient.getPlanid());
			clientForm.setTitle(client.getTitle());
			clientForm.setFirstName(client.getFirstName());
			clientForm.setLastName(client.getLastName());
			clientForm.setMiddleName(client.getMiddlename());
			clientForm.setAddress(client.getAddress());
			clientForm.setCountry(client.getCountry());
			clientForm.setDob(client.getDob());
			clientForm.setEmail(client.getEmail());
			clientForm.setGender(client.getGender());
			clientForm.setMobNo(client.getMobNo());
			clientForm.setPostCode(client.getPostCode());
			clientForm.setReference(client.getReference());
			clientForm.setSourceOfIntro(client.getSourceOfIntro());
			
			
			clientForm.setPatientoccc(client.getPatienthusocc());
			clientForm.setPatientincm(client.getPatientincm());
			clientForm.setPatienthusocc(client.getPatienthusocc());
			clientForm.setPatienthusincome(client.getPatienthusincome());
			clientForm.setPatientEductn(client.getPatientEductn());
			clientForm.setPathusbEductn(client.getPathusbEductn());
			clientForm.setReligion(client.getReligion());
			clientForm.setCast(client.getCast());
			clientForm.setClientType(client.getClientType());
			
			
			
			//String city = clientDAO.getcityfromid(client.getTown());
			//city selection
			clientForm.setTown(client.getTown());
			clientForm.setId(client.getId());
			clientForm.setType(client.getType());
			
			clientForm.setAdhno(client.getAdhno());
			clientForm.setTpmemb(client.getTpmemb());
			
			if(client.getHospitalborn().equals("1")){
				clientForm.setHospitalborn(true);
			}else{
				clientForm.setHospitalborn(false);
			}
			
			clientForm.setUserImage(new File(request.getRealPath("/liveData/")+"/"+client.getImageName()));
			clientForm.setClientimg(client.getImageName());
			
			String typeid = client.getType();
			String null1 = null;
			if(typeid!= null1){
				thirdPartyTypeNameList = clientDAO.getThirdTypeNameList(Integer.parseInt(typeid));
			}
			clientForm.setDocument_name(client.getDocumentID());
			clientForm.setDocumentValue(client.getDocumentValue());	
			
			clientForm.setThirdPartyTypeNameList(thirdPartyTypeNameList);
			clientForm.setTypeName(client.getTypeName());
			clientForm.setExpiryDate(client.getExpiryDate());
			clientForm.setOccupation(client.getOccupation());
			
			clientForm.setExpiryDate(client.getExpiryDate());
			clientForm.setWhopay(client.getWhopay());
			clientForm.setPolicyAuthorzCode(client.getPolicyAuthorzCode());
			clientForm.setPolicyNo(client.getPolicyNo());
			clientForm.setKnownAs(client.getKnownAs());
			//String county = clientDAO.getstatefromid(client.getCounty());
			clientForm.setCounty(client.getCounty());
			clientForm.setHomeNo(client.getHomeNo());
			clientForm.setWorkNo(client.getWorkNo());
			clientForm.setEmailCc(client.getEmailCc());
			clientForm.setPrefContactMode(client.getPrefContactMode());
			clientForm.setEmergencyContName(client.getEmergencyContName());
			clientForm.setEmergencyContNo(client.getEmergencyContNo());
			clientForm.setPatientType(client.getPatientType());
			
			clientForm.setPolicyExcess(client.getPolicyExcess());
			String doctorsurgery = client.getGptypeName();
			ArrayList<GpData>gpList = clientDAO.getGPDataList(doctorsurgery);
			clientForm.setGpDataList(gpList);
			clientForm.setGpname(client.getGpname());
			clientForm.setGptypeName(client.getGptypeName());
			/*String tpiddata = clientDAO.getTpIdDetails(client.getGpname());
			if(!tpiddata.equals("")){
				String temp[] = tpiddata.split(",");
				
				ArrayList<GpData>gpList = clientDAO.getGPDataList(tpiddata);
				clientForm.setGpDataList(gpList);
				//clientForm.setGpname(client.getGpname());
				
				clientForm.setSurgeryName(tpiddata);
			}*/
			
			
			clientForm.setReferedDate(client.getReferedDate());
			clientForm.setEmployerName(client.getEmployerName());
			clientForm.setTreatmentType(client.getTreatmentType());
			clientForm.setId(id);
			clientForm.setSourceOfIntroName(client.getSourceOfIntroName());
			clientForm.setSecondLineaddress(client.getSecondLineaddress());
			//clientForm.setThirdPartyCompanyName(client.getThirdPartyCompanyName());
			
			//note
			clientForm.setClientNote(client.getClientNote());
			clientForm.setAccountNote(client.getAccountNote());
			clientForm.setClinicalNote(client.getClinicalNote());
			clientForm.setNhsNumber(client.getNhsNumber());
					//  06/12/2018
			clientForm.setCompname(client.getCompname());
			clientForm.setNeisno(client.getNeisno());
			clientForm.setDesignationbytp(client.getDesignationbytp());
			clientForm.setRelationvbytpe(client.getRelationvbytpe());
			clientForm.setUnitstation(client.getUnitstation());
			clientForm.setClaimbytp(client.getClaimbytp());
			clientForm.setColliery(client.getColliery());
			clientForm.setAreabytp(client.getAreabytp());
			clientForm.setPhysioipd(client.getPhysioipd());
			//for physio ipd
			/*
			 * if(loginInfo.isPhysio_ipd()) { int
			 * result=clientDAO.updatePhysioIpd(id,clientForm.getPhysioipd()); }
			 * 
			 * String physioipd=clientDAO.getPhysioIpdorOpd(id);
			 */
				
			
			String companyname=clientDAO.gettptypenamebyid(client.getTypeName());
			
			if(companyname==null){
				companyname="";
			}
				
			if(companyname.equals("CGHS")){
				clientForm.setTptypenamestatus("CGHS");
			}else if(companyname.equals("WCL")){
				clientForm.setTptypenamestatus("WCL");
				
			}else if(companyname.equals("INSURANCE COMPANY")){
				clientForm.setTptypenamestatus("INSURANCE");
			}
			else{
				clientForm.setTptypenamestatus("");
			}
			clientForm.setPolicyholder(client.getPolicyholder());
			clientForm.setMaritalsts(client.getMaritalsts());
			
			clientForm.setMothername(client.getMothername());
			clientForm.setFathername(client.getFathername());
			clientForm.setBirthplace(client.getBirthplace());
			String btime=client.getBirthtime().replaceAll(" ", "");
			String time[] = btime.split(":");
			String hh = time[0];
			String mm = time[1];
			clientForm.setHourls(hh);
			clientForm.setMinutels(mm);
			clientForm.setAbrivationid(client.getAbrivationid());
			
			ArrayList<Client>planlist=clientDAO.getAllPlanlist();
			clientForm.setPlanlist(planlist);
			
//			clientForm.setPlanid(client.getPlanid());
			
			ArrayList<Client>chargelist=clientDAO.getAllChargeslist();
			clientForm.setChargelist(chargelist);
			//set state and city list
			InventoryVendorDAO vendorDAO=new JDBCInventoryVendorDAO(connection);
			ArrayList<Master> stateList= vendorDAO.getAllStateList();
			ArrayList<Master> cityList= vendorDAO.getAllCityList();
			clientForm.setStatelist(stateList);
			clientForm.setCitylist(cityList);
			
			clientForm.setCountryList(PopulateList.countryList());
			clientForm.setBirthPlaceList(new ArrayList<>());
			clientForm.setHourList(new ArrayList<>());
			clientForm.setMinuteList(new ArrayList<>());
			clientForm.setRefrenceList(new ArrayList<>());
			clientForm.setDocuList(new ArrayList<>());
			clientForm.setSurgeryList(new ArrayList<>());
			clientForm.setSourceOfIntroList(new ArrayList<>());
			clientForm.setClientOccupationList(new ArrayList<>());
			clientForm.setCondtitionList(new ArrayList<>());
			clientForm.setCountry("India");
			clientForm.setEnrollcode(client.getEnrollcode());
			clientForm.setClientName(client.getClientName());
			int enrollcode=clientDAO.checkEnrollCode(client.getTypeName());
			clientForm.setStatusenrollcode(enrollcode);
			int isCampArea=clientDAO.checkCampArea(client.getTypeName());
			clientForm.setIsCampArea(isCampArea);
			clientForm.setCampArea(client.getCampArea());
			if(loginInfo.isPhysio_ipd() && client.getLmh_department()==null) {//for balgopal physio ipdopd
				String physiodoctordept=clientDAO.getLastAppoitnmentDetails(""+id);
				client.setLmh_department(physiodoctordept);
			}
			String isfromaddpatient = request.getParameter("isfromaddpatient");
			if(DateTimeUtils.isNull(isfromaddpatient).equals("1")){
				clientForm.setIsfromaddpatientlmh(1);
				clientForm.setSearchText(client.getAbrivationid());
				clientForm.setIsfromaddpatientlmh(1);
				clientForm.setDiaryUser(client.getLmh_department());
				clientForm.setPatientcategory(client.getPatientcategory());
				
				//Registration charge
				MasterDAO masterDAO = new JDBCMasterDAO(connection);
				CompleteAptmDAO completeAptmDAO = new JDBCCompleteAptmDAO(connection);
//				ArrayList<DiaryManagement> userList = notAvailableSlotDAO.getUserList(loginInfo.getId());
//				clientForm.setUserList(userList);
				// department ot list
				ArrayList<Master> departmentList = masterDAO.getDisciplineDataListWithChecked();
				clientForm.setDepartmentList(departmentList);
				
				ArrayList<Master> camploactionList= masterDAO.getAllCampLocation();
				clientForm.setCamplocationList(camploactionList);
				//double registrationcharge = completeAptmDAO.getOpdRegCharge();
				
				//boolean regchargeapply = completeAptmDAO.testRegChargeApply(id);
				int year = Calendar.getInstance().get(Calendar.YEAR);
				/*if(client.getChargeYear()==year){
					clientForm.setRegchargeapplied(1);
					clientForm.setRegistrationcharge("0");
					clientForm.setNetamount("0");
				}else{
					clientForm.setRegchargeapplied(0);
					clientForm.setRegistrationcharge(String.valueOf(registrationcharge));
					clientForm.setNetamount(String.valueOf(registrationcharge));
				}*/
				
				String registrationcharge ="0";
				String masterCharge = "";
				String chargeName ="";
				if(loginInfo.isMatrusevasang()) {
					masterCharge ="Appointment Charge";
					chargeName ="Follow Up Charge";
					clientForm.setRegchargeapplied(1);
				}else {
				if(client.getChargeYear()!=year){
					clientForm.setRegchargeapplied(0);
					masterCharge ="Appointment Charge";
					chargeName ="Registration Charge";
				}else{
					masterCharge ="Appointment Charge";
					chargeName ="Follow Up Charge";
					clientForm.setRegchargeapplied(1);
				}
				}
				NotAvailableSlot availableslot =clientDAO.getRegistrationChargeAmount(client.getTypeName(),masterCharge,chargeName);
				client=clientDAO.getRegiDatebyId(id);
				//setting registration charge for chraibgh (09/03/2023)
				if(loginInfo.isMatrusevasang()){
				String regdate=client.getRegDate();
				Date date1 = new SimpleDateFormat("yyyy-MM-dd").parse(regdate);
				String cdate = DateTimeUtils.getUKCurrentDataTime("India").split(" ")[0];
				Date date2 = new SimpleDateFormat("yyyy-MM-dd").parse(cdate);
			    long duration=date2.getTime()-date1.getTime();
			    long daydiff = duration / (1000 * 60 * 60 * 24);
			   
			        if(daydiff<=27){
			    	   registrationcharge ="0";
			    	   clientForm.setRegistrationcharge(String.valueOf(registrationcharge));
					   clientForm.setNetamount(String.valueOf(registrationcharge));
			          }
			      else
			         {
			    	   registrationcharge = availableslot.getChargeamout();
			    	   clientForm.setRegistrationcharge(String.valueOf(registrationcharge));
					   clientForm.setNetamount(String.valueOf(registrationcharge));
			         }
				}
				else{
				registrationcharge = availableslot.getChargeamout();
				clientForm.setRegistrationcharge(String.valueOf(registrationcharge));
				clientForm.setNetamount(String.valueOf(registrationcharge));
				}
				String patientType="0";
				if(DateTimeUtils.isNull(client.getWhopay()).equals("Third Party")){
					patientType ="1";
				}
				thirdPartyTypeList = clientDAO.getThirdPartyTypeNew(patientType);
				clientForm.setThirdPartyTypeList(thirdPartyTypeList);
				String paymentType = clientDAO.getPayerPaymentType(client.getTypeName());
				clientForm.setPaymenttype(paymentType);
				ArrayList<Master> clienttypelist= masterDAO.getAllClientTypeList();
				clientForm.setClientTypeList(clienttypelist);
				
				return "addnewpatientforafitech";
				
				//return "addnewpatient";
			}else{
				thirdPartyTypeList = clientDAO.getThirdPartyType();
				clientForm.setThirdPartyTypeList(thirdPartyTypeList);
			}
			
			String isfornewadd = request.getParameter("isfornewadd");
			if(DateTimeUtils.isNull(isfornewadd).equals("1")){
				return "addCompleteInfo";
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		finally{
			connection.close();
		}
		return "editClientPage";
	}
	
	public String save() throws SQLException{
		if(!verifyLogin(request)){
			return "login";
		}
 		Connection connection = null;
		try{
			Client client = new Client();
			connection = Connection_provider.getconnection();
			String physioipd=request.getParameter("physioipd");
			String gp = clientForm.getGpid();
			ClientDAO clientDAO = new JDBCClientDAO(connection);
			
			//save abrivation seq no
			String cdate = DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone()).split(" ")[0];
			boolean checkifseq = clientDAO.checkifSequenceExist(cdate);
			String abrivationid = "";
			String clinicabrivation = clientDAO.getClinicAbrivation(loginInfo.getClinicid());
			String tempd[] = cdate.split("-");
			String y = tempd[0];
			String m = tempd[1];
			String d = tempd[2];
			String newseq="";
//			String mothername=DateTimeUtils.isNull(request.getParameter("mothername"));
//			String fathername=DateTimeUtils.isNull(request.getParameter("fathername"));
//			String birthplace=DateTimeUtils.isNull(request.getParameter("birthplace"));
			if(checkifseq){
				
				int seqno = clientDAO.getSqeunceNumber(cdate);
				seqno++;
				int r = clientDAO.InserCdateSeq(cdate,seqno);
				//SNH170609001
				int yr = Integer.parseInt(y)%1000;
				if(loginInfo.isLmh()) {
					yr=Integer.parseInt(y);
					if(String.valueOf(seqno).length()==1)
					{
						 newseq="000"+seqno;
					}else if(String.valueOf(seqno).length()==2){
						 newseq="00"+seqno;
					}else if(String.valueOf(seqno).length()==3){
						 newseq="0"+seqno;
					}else{
						newseq=""+seqno;
					}
				}else {
					
				
					if(String.valueOf(seqno).length()==1)
					{
						 newseq="00"+seqno;
					}else if(String.valueOf(seqno).length()==2){
						 newseq="0"+seqno;
					}else{
						newseq=""+seqno;
					}
				}
				abrivationid = clinicabrivation + yr + m +d + "" +newseq ;
			}else{
				int seqno = 1;
				int r = clientDAO.InserCdateSeq(cdate,seqno);
				//String seqno = clientDAO.getSqeunceNumber(cdate);
				int yr = Integer.parseInt(y)%1000;
				if(loginInfo.isLmh()) {
					yr=Integer.parseInt(y);
					if(String.valueOf(seqno).length()==1)
					{
						 newseq="000"+seqno;
					}else if(String.valueOf(seqno).length()==2){
						 newseq="00"+seqno;
					}else if(String.valueOf(seqno).length()==3){
						 newseq="0"+seqno;
					}else{
						newseq=""+seqno;
					}
				}else {
					if(String.valueOf(seqno).length()==1)
					{
						 newseq="00"+seqno;
					}else if(String.valueOf(seqno).length()==2){
						 newseq="0"+seqno;
					}else{
						newseq=""+seqno;
					}
				}
				abrivationid = clinicabrivation + yr + m +d + "" + newseq;
			}
			
			client.setTitle(clientForm.getTitle());
			client.setFirstName(clientForm.getFirstName());
			client.setLastName(clientForm.getLastName());
			client.setMiddlename(clientForm.getMiddleName());
			client.setAddress(clientForm.getAddress());
			client.setCountry(clientForm.getCountry());
			client.setDob(clientForm.getDob());
			client.setEmail(clientForm.getEmail());
			client.setGender(clientForm.getGender());
			client.setMobNo(clientForm.getMobNo().trim());
			client.setPostCode(clientForm.getPostCode().trim());
			
			//get adhar and third party member number 
			String adhno = clientForm.getAdhno();
			String tmemb = clientForm.getTpmemb();
			client.setAdhno(clientForm.getAdhno());
			client.setTpmemb(clientForm.getTpmemb());
			
			//ArrayList<Client> clientOccupationList = clientDAO.getOccupationList();
			//ArrayList<Client> refrenceList = clientDAO.getReferenceList();
			//ArrayList<Client> sourceOfIntroList = clientDAO.getSourceOfIntroList();
			
			
			client.setMothername(clientForm.getMothername());
			client.setFathername(clientForm.getFathername());
			client.setBirthplace(clientForm.getBirthplace());
			client.setTown(clientForm.getTown());
			client.setType(clientForm.getType());
			client.setTypeName(clientForm.getTypeName());
			if(DateTimeUtils.isNull(clientForm.getOtherRef()).equals(""))
			{
				client.setReference(clientForm.getReference());
				
			}else{
				String reference = clientForm.getOtherRef();
				int save = clientDAO.insertOtherReference(client, reference);
				int id = clientDAO.getMaxIdOfRefernce();
				client.setReference(Integer.toString(id));
			
			}
			if(DateTimeUtils.isNull(clientForm.getOtherSourceOfIntro()).equals(""))
			{
				client.setSourceOfIntro(clientForm.getSourceOfIntro());

			}
			else{
				String otherSourceOfIntro = clientForm.getOtherSourceOfIntro();
				int save = clientDAO.insertOtherSourceOfIntro(client, otherSourceOfIntro);
				int id = clientDAO.getMaxIdOfSourceOfIntro();
				client.setSourceOfIntro(Integer.toString(id));
			}
			if(DateTimeUtils.isNull(clientForm.getOtherOccupation()).equals(""))
			{
				client.setOccupation(clientForm.getOccupation());

			}
			else{
				String otherOccupation = clientForm.getOtherOccupation();
				int save = clientDAO.insertOtherOccupation(client, otherOccupation);
				int id = clientDAO.getMaxIdOfOccupation();
				client.setOccupation(Integer.toString(id));
			}
			
			if(clientForm.getOtherCondition().equals(null) || clientForm.getOtherCondition().equals(""))
			{
				client.setTreatmentType(clientForm.getTreatmentType());

			}
			else{
				String otherCondition = clientForm.getOtherCondition();
				int save = clientDAO.insertOtherCondition(client, otherCondition);
				int id = clientDAO.getMaxIdOfCondition();
				client.setTreatmentType(Integer.toString(id));
			}
			client.setExpiryDate(clientForm.getExpiryDate());
			client.setWhopay(clientForm.getWhopay());
			client.setPolicyAuthorzCode(clientForm.getPolicyAuthorzCode());
			client.setPolicyNo(clientForm.getPolicyNo());
			client.setPolicyExcess(clientForm.getPolicyExcess());
			
           //for chraibgh other details
			
			client.setPatientoccc(clientForm.getPatientoccc());
			client.setPatientincm(clientForm.getPatientincm());
			client.setPatienthusocc(clientForm.getPatienthusocc());
			client.setPatienthusincome(clientForm.getPatienthusincome());
			client.setPatientEductn(clientForm.getPatientEductn());
			client.setPathusbEductn(clientForm.getPathusbEductn());
			client.setReligion(clientForm.getReligion());
			client.setCast(clientForm.getCast());
			
			
			client.setKnownAs(clientForm.getKnownAs());
			String County = clientForm.getCounty();
			client.setCounty(clientForm.getCounty());
			client.setHomeNo(clientForm.getHomeNo().trim());
			client.setWorkNo(clientForm.getWorkNo().trim());
			client.setEmailCc(clientForm.getEmailCc());
			client.setPrefContactMode(clientForm.getPrefContactMode());
			if(!loginInfo.isMatrusevasang()){
			client.setEmergencyContName(clientForm.getEmergencyContName());
			client.setEmergencyContNo((clientForm.getEmergencyContNo().trim()));
			}
			client.setPatientType(clientForm.getPatientType());
			client.setReferedDate(clientForm.getReferedDate());
			client.setEmployerName(clientForm.getEmployerName());
			client.setGpname(clientForm.getGpid());
			String surgeryname = clientForm.getGptypeName();
			client.setDoctorsurgery(clientForm.getGptypeName());
			client.setSecondLineaddress(clientForm.getSecondLineaddress());
			client.setSourceOfIntroName(clientForm.getSourceOfIntroName());
			//ArrayList<Client>condtitionList = clientDAO.getTreatmentTypeList();
			
			
			
			//set note
			client.setClientNote(clientForm.getClientNote());
			client.setAccountNote(clientForm.getAccountNote());
			client.setClinicalNote(clientForm.getClinicalNote());
			client.setLastModifiedDate(DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone()));
			client.setNhsNumber(clientForm.getNhsNumber());
			client.setAbrivationid(abrivationid);
			//  06/12/2018
			client.setCompname(clientForm.getCompname());
			client.setNeisno(clientForm.getNeisno());
			client.setDesignationbytp(clientForm.getDesignationbytp());
			client.setUnitstation(clientForm.getUnitstation());
			client.setRelationvbytpe(clientForm.getRelationvbytpe());
			client.setClaimbytp(clientForm.getClaimbytp());
			client.setColliery(clientForm.getColliery());
			client.setAreabytp(clientForm.getAreabytp());
			client.setPolicyholder(clientForm.getPolicyholder());
			if(clientForm.isHospitalborn()){
				client.setHospitalborn("1");
			}else{
				client.setHospitalborn("0");
			}
			if(clientForm.getMaritalsts().equals("0")){
				client.setMaritalsts("");
			}else{
				client.setMaritalsts(clientForm.getMaritalsts());
			}
			
			String birthtime=clientForm.getHourls()+":"+clientForm.getMinutels()+":00";
			client.setBirthtime(birthtime.replaceAll(" ", ""));
			client.setDocumentValue(clientForm.getDocumentValue());
			client.setDocumentID(clientForm.getDocument_name());
			client.setEnrollcode(DateTimeUtils.isNull(clientForm.getEnrollcode()));
			client.setClientName(clientForm.getClientName());
			String patientCategory="";
			if(!DateTimeUtils.numberCheck(client.getTypeName()).equals("0")){
				patientCategory = clientDAO.getThirdPartyCompanyName(client.getTypeName());
			}
			client.setPatientcategory(patientCategory);
			client.setDiaryUser(clientForm.getDiaryUser());
			client.setCampArea(clientForm.getCampArea());
			client.setPlanid(clientForm.getPlanid());
			client.setPlan(clientForm.getPlan());
			client.setFromdate(DateTimeUtils.getCommencingDate1(clientForm.getFromdate()));
			if(physioipd==null) {
				physioipd="0";
			}
			client.setPhysioipd(physioipd);
			client.setClientType(DateTimeUtils.isNull(clientForm.getClientType()));
			
			int result = clientDAO.savePatient(client,loginInfo.getId(),loginInfo);
			//int result1=clientDAO.savePhysioPatient(client,loginInfo.getId());
			//sms while patient register
			
			// (09/03/2023) chraibgh Priyanka
			if(loginInfo.isMatrusevasang()){
				int CurrentYear = Calendar.getInstance().get(Calendar.YEAR);
				int CurrentMonth = (Calendar.getInstance().get(Calendar.MONTH)+1);
				if(CurrentMonth<4){
					CurrentYear=CurrentYear-1;
				}
				int register=clientDAO.saveRegnobyfinyear(result,CurrentYear,client);
				
			}
			//(09/03/23)chraibgh Priyanka
            if(loginInfo.isMatrusevasang()){
         	   Client client2=clientDAO.getPatientnofromregno(result);
         	   int CurrentYear = Calendar.getInstance().get(Calendar.YEAR)%100;
         	   int CurrentYear1=Calendar.getInstance().get(Calendar.YEAR);
         	   String financialyr="";
         	 
			       int CurrentMonth = (Calendar.getInstance().get(Calendar.MONTH)+1);
			       String financiyalYearFrom="";
			       String financiyalYearTo="";
			    if (CurrentMonth<4) {
			        financiyalYearFrom=""+(CurrentYear-1);
			        financiyalYearTo=""+(CurrentYear);
			        financialyr=(CurrentYear1-1)+"-"+CurrentYear1;
			        
			    } else {
			        financiyalYearFrom=""+(CurrentYear);
			        financiyalYearTo=""+(CurrentYear+1);
			        financialyr=CurrentYear1+"-"+(CurrentYear1+1);
			        
			    }
			       int maxseq=clientDAO.getMaxseqNo(financialyr);
			       maxseq=maxseq+1;
			       int insertseq=clientDAO.saveRegisstrationmaxseqno(financialyr,maxseq);
			       String finyear=financiyalYearFrom+"/"+financiyalYearTo+"/"+maxseq;
			       int updateregno=clientDAO.updateReginobyfinyear(finyear,client2.getId());
			       
            }
			 int planid1=0;
			 int activeplanid=0;
			 String actplan="";
			 Client client1=new Client();
			 int totalphysiodays=0;
		if(client.getPhysioipd().equals("0")) {	 
			if(loginInfo.isPhysio()){
				planid1=(int) session.getAttribute("planid");
				activeplanid=(int) session.getAttribute("activeplanid");
				actplan=Integer.toString(activeplanid);
				client1=clientDAO.getPatientActiveplanDetails(actplan);
			    totalphysiodays=client1.getDay();
	          if(planid1==1 || planid1==5){
		     	int physioresult=clientDAO.savePhysioPatient(result,client,planid1,activeplanid,totalphysiodays);
			  }
			
			}
		}
			if(loginInfo.isSms_reg_patient()){
				 SmsService s= new SmsService();
				 String message="";
				 String templateid="";
				 s.sendSms(message, client.getMobNo(), loginInfo, new EmailLetterLog(),templateid);
			}
			
			
			clientDAO.addToHISDocumentLog(clientForm.getDocument_name(), clientForm.getDocumentValue(), ""+result);
			
			File userImage = clientForm.getUserImage();
			String userImageFileName = clientForm.getUserImageFileName();
			if(userImageFileName!=null){			
				userImageFileName =loginInfo.getClinicUserid()+"_"+result+"_"+userImageFileName;
				//update image name
				int res=clientDAO.updateClientUserImage(userImageFileName,result);
			}else{
				userImageFileName = loginInfo.getClinicUserid()+"_"+result+"_profileimg.png";
				
				String filepath = request.getRealPath("/liveData/"+userImageFileName);
				String clientImageData = clientForm.getProfileimg();
				byte[] imagedata = DatatypeConverter.parseBase64Binary(clientImageData.substring(clientImageData.indexOf(",") + 1));
				BufferedImage bufferedImage = ImageIO.read(new ByteArrayInputStream(imagedata));
				if(!clientForm.getProfileimg().equals("")){
					ImageIO.write(bufferedImage, "png", new File(filepath));
				}
			}
			//String userImageContentType = clientForm.getUserImageContentType();
			
			if(clientForm.getUserImageContentType()!=null){
			String filePath = request.getRealPath("/liveData/");			
//			System.out.println("Server path:" + filePath);
			File fileToCreate = new File(filePath, userImageFileName);
			FileUtils.copyFile(userImage, fileToCreate);
			}
			
			
	
			session.setAttribute("clientid", Integer.toString(result));
			session.setAttribute("title", clientForm.getTitle());
			session.setAttribute("firstname", clientForm.getFirstName());
			session.setAttribute("lastname", clientForm.getLastName());
			session.setAttribute("treatmenttype", clientForm.getTreatmentType());
			session.setAttribute("newWhopay", clientForm.getWhopay());
			session.setAttribute("newPolicyNo", clientForm.getPolicyNo());
			
			CompleteAptmDAO completeAptmDAO = new JDBCCompleteAptmDAO(connection);
			CompleteAppointment completeAppointment =  completeAptmDAO.getInsuranceCompanyName(Integer.toString(result));
			String tpName = completeAppointment.getInsuranceCompanyName();
			session.setAttribute("newTpname", tpName);
			
			if(loginInfo.isLmh()){
				String opdoption=clientForm.getOpdoption();
				if(opdoption.equals("regwopd")) {
					String department="";
					if(client.getPhysioipd().equals("0")) {
						//for physio ipd
						department=clientForm.getDiaryUser();
					}else {
						if(!loginInfo.getJobTitle().equals("Practitioner")) {
							department=clientForm.getDiaryUser();	
						}else {
					        department=completeAptmDAO.getpractitonerdept(loginInfo.getId());
						}
					}
					//String department=clientForm.getDiaryUser();
					//String department=completeAptmDAO.getpractitonerdept(loginInfo.getId());
					String invoicetype="10";
					String paymode=clientForm.getPaymenttype();
					String paidamount=clientForm.getNetamount();
					String invoiceNote = request.getParameter("invoiceNote");
					
					int planid=0;
					int day=0;
					if(loginInfo.isPhysio()){
						totalphysiodays=client1.getDay();
					if(planid!=0){
					    day=1;
					   }
					}
//					String discountamt=clientForm.getDiscountvalue();
//					double grandtotal=DateTimeUtils.convertToDouble(paidamount)+DateTimeUtils.convertToDouble(discountamt);
//					String disctype=clientForm.getDiscounttype();
//					String opdregchargeamount=clientForm.getRegistrationcharge();
//					String consultationcharge=clientForm.getConsultationcharge();
//					String othercharge=clientForm.getOthercharge();
					String discountamt=DateTimeUtils.numberCheck(clientForm.getDiscountvalue());
					String disctype=DateTimeUtils.numberCheck(clientForm.getDiscounttype());
					String opdregchargeamount=DateTimeUtils.numberCheck(clientForm.getRegistrationcharge());
					String consultationcharge=DateTimeUtils.numberCheck(clientForm.getConsultationcharge());
					String othercharge=DateTimeUtils.numberCheck(clientForm.getOthercharge());
					double grandtotal=DateTimeUtils.convertToDouble(opdregchargeamount)+DateTimeUtils.convertToDouble(consultationcharge)+DateTimeUtils.convertToDouble(othercharge);
					if(paymode.equals("Credit")) {
						paidamount="0";
					}
					double opdregcharge = DateTimeUtils.convertToDouble(opdregchargeamount);
					/*if(opdregcharge>0){
						int year = Calendar.getInstance().get(Calendar.YEAR);
						int res = clientDAO.updateRegChargeApplied(year,result);
					}*/
					
	//				String appointment=savedirectappointment(department, ""+result);
					int newpatient =1;
					String appointment=saveappointmentwithDept(department,""+result,1,newpatient,opdregcharge,0,null,activeplanid,day);
					int appointmentid=DateTimeUtils.convertToInteger(appointment);
					cashdeskdepartmentdirect(appointmentid, invoicetype, paymode, ""+grandtotal, discountamt, paidamount, connection, disctype, opdregchargeamount, consultationcharge, othercharge,invoiceNote);
	//				cashdeskdirect(appointmentid, invoicetype, paymode, ""+grandtotal, discountamt, paidamount, connection, disctype, opdregchargeamount, consultationcharge, othercharge);
					session.setAttribute("lmh_register_apmtid", String.valueOf(appointmentid));
					
					
				}
				session.setAttribute("lmh_register_patientid", String.valueOf(result));
				return "sendtoblackletter";
			}else if( loginInfo.isPhysio_ipd()){
				String opdoption=clientForm.getOpdoption();
				if(opdoption.equals("regwopd")) {
					String department="";
					if(client.getPhysioipd().equals("0")) {
						//for physio ipd
						department=clientForm.getDiaryUser();
					}else {
						if(!loginInfo.getJobTitle().equals("Practitioner")) {
							department=clientForm.getDiaryUser();	
						}else {
					        department=completeAptmDAO.getpractitonerdept(loginInfo.getId());
						}
					}
					//String department=clientForm.getDiaryUser();
					//String department=completeAptmDAO.getpractitonerdept(loginInfo.getId());
					String invoicetype="17";
					String paymode=clientForm.getPaymenttype();
					String paidamount=clientForm.getNetamount();
					String invoiceNote = request.getParameter("invoiceNote");
					
					int planid=0;
					int day=0;
					if(loginInfo.isPhysio()){
						totalphysiodays=client1.getDay();
					if(planid!=0){
					    day=1;
					   }
					}
//					String discountamt=clientForm.getDiscountvalue();
//					double grandtotal=DateTimeUtils.convertToDouble(paidamount)+DateTimeUtils.convertToDouble(discountamt);
//					String disctype=clientForm.getDiscounttype();
//					String opdregchargeamount=clientForm.getRegistrationcharge();
//					String consultationcharge=clientForm.getConsultationcharge();
//					String othercharge=clientForm.getOthercharge();
					String discountamt=DateTimeUtils.numberCheck(clientForm.getDiscountvalue());
					String disctype=DateTimeUtils.numberCheck(clientForm.getDiscounttype());
					String opdregchargeamount=DateTimeUtils.numberCheck(clientForm.getRegistrationcharge());
					String consultationcharge=DateTimeUtils.numberCheck(clientForm.getConsultationcharge());
					String othercharge=DateTimeUtils.numberCheck(clientForm.getOthercharge());
					double grandtotal=DateTimeUtils.convertToDouble(opdregchargeamount)+DateTimeUtils.convertToDouble(consultationcharge)+DateTimeUtils.convertToDouble(othercharge);
					if(paymode.equals("Credit")) {
						paidamount="0";
					}
					double opdregcharge = DateTimeUtils.convertToDouble(opdregchargeamount);
					/*if(opdregcharge>0){
						int year = Calendar.getInstance().get(Calendar.YEAR);
						int res = clientDAO.updateRegChargeApplied(year,result);
					}*/
					
	//				String appointment=savedirectappointment(department, ""+result);
					int newpatient =1;
					String appointment=saveappointmentwithDept(department,""+result,1,newpatient,opdregcharge,0,null,activeplanid,day);
					int appointmentid=DateTimeUtils.convertToInteger(appointment);
					//cashdeskdepartmentdirect(appointmentid, invoicetype, paymode, ""+grandtotal, discountamt, paidamount, connection, disctype, opdregchargeamount, consultationcharge, othercharge,invoiceNote);
	//				cashdeskdirect(appointmentid, invoicetype, paymode, ""+grandtotal, discountamt, paidamount, connection, disctype, opdregchargeamount, consultationcharge, othercharge);
					session.setAttribute("lmh_register_apmtid", String.valueOf(appointmentid));
					
					
				}
				session.setAttribute("lmh_register_patientid", String.valueOf(result));
				if(client.getPhysioipd().equals("1")) {
					return "savecashphysioipd";
				}else {
					return "sendtoblackletter";
				}
				//return "sendtoblackletter";
			}
			clientForm.setMessage("Patient Registered Suceessfully");
			addActionMessage("Patient Registered Suceessfully");
			
			setFromData();
			
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		finally{
			connection.close();
		}
		return "save";
	}
	
	public String saveappointmentwithDept(String department,String clientid, int status, int newpatient, double opdregcharge, int previousDate, String currentDate, int activeplanid, int day) throws Exception {
		Connection connection=null;
		int appointmentid =0;
		try {
//			String diaryuserid=request.getParameter("diaryuser");
//			String clientid=request.getParameter("clientid");
			connection=Connection_provider.getconnection();
			NotAvailableSlot notAvailableSlot=new NotAvailableSlot();
			NotAvailableSlotDAO notAvailableSlotDAO=new JDBCNotAvailableSlotDAO(connection);
			UserProfileDAO userProfileDAO=new JDBCUserProfileDAO(connection);
			ClientDAO clientDAO=new JDBCClientDAO(connection);
			FinderDAO finderDAO=new JDBCFinderDAO(connection);
			Date date = new Date();
			String commencing= "";
			if(previousDate==1){
				commencing = currentDate;
			}else{
				commencing= new SimpleDateFormat("yyyy-MM-dd").format(date);
			}
			
//			NotAvailableSlot n = notAvailableSlotDAO.getNewOpdDiaryUserData(commencing,diaryuserid);
			String duration = "00:05";
			Date date1 = new Date();
			DateFormat format = new SimpleDateFormat("HH:mm");
			
			
			String currentTime=format.format(date1);
//			String currentTime="17:30";
			String mm=currentTime.split(":")[1];
			String strmm="";
			int rem=Integer.parseInt(mm)%5;
			if(rem==0){
				notAvailableSlot.setSTime(currentTime);
			}else{
				int mmm=Integer.parseInt(mm);
				while(mmm%5!=0){
					mmm--;
				}
				strmm=""+mmm;
				if(mmm<=9){
					String singlemm=String.valueOf(mmm);
					singlemm="0"+singlemm;
					strmm=singlemm;
				}
				String updatetime=currentTime.split(":")[0]+":"+strmm;
				notAvailableSlot.setSTime(updatetime);
			}
				notAvailableSlot.setApmtSlotId(0);
				
				String apmtduration=duration.split(":")[1];
				notAvailableSlot.setApmtDuration(duration);
				String endtime1=DateTimeUtils.getTotalofTwoTime(notAvailableSlot.getSTime(),duration);
				notAvailableSlot.setEndTime(endtime1);
				notAvailableSlot.setDiaryUserId(1);
				UserProfile userProfile = userProfileDAO.getUserprofileDetails(notAvailableSlot.getDiaryUserId());
				String dusername = userProfile.getInitial() + " " + userProfile.getFirstname() + " "
						+ userProfile.getLastname();
				notAvailableSlot.setDiaryUser(dusername);
				Client client=clientDAO.getClientDetails(clientid);
				notAvailableSlot.setDept(department);
				notAvailableSlot.setLocation("1");
				notAvailableSlot.setRoom("Room1");
				notAvailableSlot.setCommencing(commencing);
				notAvailableSlot.setClient(client.getTitle()+" "+client.getFullname());
				notAvailableSlot.setPhysio_ipd(client.getPhysioipd());
				String planid=""+activeplanid+"";
				Client client2=clientDAO.getPatientActiveplanDetails(planid);
				if(loginInfo.isLmh()) {
					//newpatient==1 means new patient
					//newpatient==2 means old patient
					int year = Calendar.getInstance().get(Calendar.YEAR);
					String masterCharge = "";
					String chargeName ="";
					/*if(opdregcharge==0 && newpatient!=1){*/
					if(client.getChargeYear()!=year){
						masterCharge ="Appointment Charge";
						chargeName ="Registration Charge";
						int res = clientDAO.updateRegChargeApplied(year,client.getId());
					}
					else if(loginInfo.isPhysio()){
						masterCharge="PHYSIOTHERAPY";
						chargeName=client2.getPlan();
						int res = clientDAO.updateRegChargeApplied(year,client.getId());
					}
					else{
						masterCharge ="Appointment Charge";
						chargeName ="Follow Up Charge";
					}
					NotAvailableSlot availableslot =clientDAO.getRegistrationChargeAmount(client.getTypeName(),masterCharge,chargeName);
					notAvailableSlot.setApmtType(""+availableslot.getId());	
					if(loginInfo.isPhysio())
					{
						notAvailableSlot.setApmtType(planid);
					}
				}else {
					notAvailableSlot.setApmtType("1");	
				}
				notAvailableSlot.setNotes("");
				notAvailableSlot.setClientId(clientid);
				notAvailableSlot.setTreatmentEpisodeId("0");
				notAvailableSlot.setAddedBy(loginInfo.getUserId());
				notAvailableSlot.setModifiedBy(loginInfo.getUserId());
//				notAvailableSlot.setCondition(condition);
				notAvailableSlot.setPayBy(client.getWhopay());
//				notAvailableSlot.setStafflistid(stafflistid);
				SimpleDateFormat sdfTime = new SimpleDateFormat("HH:mm:ss");
				Date now = new Date();
				String opdbooktime = sdfTime.format(now);
				if(status==1) {
					notAvailableSlot.setRequestDateTime(commencing+" "+opdbooktime);
				}else {
					String preBookDate=finderDAO.getBookedDateTime(commencing,clientid);
					notAvailableSlot.setRequestDateTime(DateTimeUtils.isNull(preBookDate));
				}
				notAvailableSlot.setOpdbooktime(opdbooktime);
				notAvailableSlot.setMobstatus("0");
				String speciality=userProfileDAO.getSpeciality("2");
				notAvailableSlot.setSpeciality(speciality);
				notAvailableSlot.setOtplan("0");
				notAvailableSlot.setNewpatient(newpatient);
				notAvailableSlot.setPreDate(previousDate);
				notAvailableSlot.setPlanid(activeplanid);
				notAvailableSlot.setDay(day);
				appointmentid = notAvailableSlotDAO.saveDepartmentAppointment(notAvailableSlot);
				
				int second_dr=finderDAO.saveDeptapptid(appointmentid,clientid);
				if(loginInfo.isPhysio()){
					
				Client client1=clientDAO.getNumberofPhysioDays(clientid);
				int countdays=client1.getDay();
				int totalphysiodays=client1.getTotaldays();
				client1.setDay(countdays);
				if(client.getRegularpatient()==0){
				if(countdays<totalphysiodays){
					countdays=countdays+1;
					int physiores=clientDAO.updatePhysiopatientdays(client,countdays,activeplanid);
				}
				if((countdays==totalphysiodays) && (activeplanid==2847)){
					int planid1=2;
					int activeplanid1=2848;
					int patientid=Integer.parseInt(clientid);
					int physioresult=clientDAO.savePhysioPatient(patientid,client,planid1,activeplanid1,totalphysiodays);
				}
				else if((countdays==totalphysiodays) && (activeplanid==2848)){
					int planid1=3;
					int activeplanid1=2849;
					int patientid=Integer.parseInt(clientid);
					int physioresult=clientDAO.savePhysioPatient(patientid,client,planid1,activeplanid1,totalphysiodays);
					
				}
				 else if((countdays==totalphysiodays) && (activeplanid==2849))
				{
					int patientid=Integer.parseInt(clientid);
					int res=clientDAO.updateRegularPatient(patientid);
				}
				else if((countdays==totalphysiodays) && (activeplanid==2850))
				{
						int patientid=Integer.parseInt(clientid);
						int res=clientDAO.updateRegularPatient(patientid);
				}
			}
				else{
				      
					if(countdays<totalphysiodays){
						countdays=countdays+1;
						int physiores=clientDAO.updatePhysiopatientdays(client,countdays,activeplanid);
					}
					
				}
	    }
//				saveOpdCharge(appointmentid, not, "1", "Cash", "20", "0", "20", connection, "0", "20", "0");
//				cashdeskdirect(appointmentid, "1", "Cash", "60", "0", "60", connection, "0", "20", "0","40");
				
		}catch (Exception e) {
			e.printStackTrace();
		}
		return ""+appointmentid;
	}
	
	public void cashdeskdepartmentdirect(int appointmentid,String invoiicetype,String paymode,String grandtotal,String discountamt,String paidamount,Connection connection,String disctype,String opdregchargeamount ,String consultationcharge,String othercharge, String invoiceNote ){
		try {
			connection=Connection_provider.getconnection();
			NotAvailableSlotDAO  notAvailableSlotDAO=new JDBCNotAvailableSlotDAO(connection);
			NotAvailableSlot notavailableslot=notAvailableSlotDAO.getDepartmentAvailableSlotdata(appointmentid);
			notavailableslot.setInvoiceNote(invoiceNote);
//			saveOpdCharge(appointmentid, obj of notavailableslot, Invoice Type, Payment Mode, Total Amount, Discount Amount, Remaoining Amount, connection obj, Discount Type, "0", "0");
//			saveOpdCharge(appointmentid, notAvailableSlot, invcetype, howpaid, totalamount, discount,payAmount, connection, disctype,opdotcharge,opdotregcharge);
			boolean flag=false;
//			if(!opdregchargeamount.equals("0")){
//				flag=true;
//			}else if(!consultationcharge.equals("0")){
//				flag=true;
//			}else if (!othercharge.equals("0")) {
//				flag=true;
//			}else{
//				flag=false;
//			}
//			if(flag){
			saveDirectOpdCharge(appointmentid, notavailableslot, invoiicetype, paymode, grandtotal, discountamt, paidamount, connection, disctype, opdregchargeamount, consultationcharge,othercharge);
//			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void saveDirectOpdCharge(int appointmentid, NotAvailableSlot notAvailableSlot, String invcetype, String howpaid,
			String totalamount, String discounts, String payAmount, Connection connection, String disctype, String opdregchargeamount, String consultationcharge, String othercharge)
			throws Exception {

		try {

			AccountsDAO accountsDAO = new JDBCAccountsDAO(connection);
			AccountLogDAO accountLogDAO = new JDBCAccountLogDAO(connection);
			CompleteAptmDAO completeAptmDAO = new JDBCCompleteAptmDAO(connection);
			NotAvailableSlotDAO notAvailableSlotDAO = new JDBCNotAvailableSlotDAO(connection);

			String location = notAvailableSlot.getLocation();
			String chargeType = "Submit";

			String temp[] = notAvailableSlot.getCommencing().split("-");
			String commencing = temp[2] + "-" + temp[1] + "-" + temp[0];
			double debit = Double.parseDouble(totalamount);
			if(discounts==null){
				discounts="0";
			}
			if(discounts.equals("")){
				discounts="0";
			}
			double discount = Double.parseDouble(discounts);

			ArrayList<Accounts> invoiceList = new ArrayList<Accounts>();
			int thirdPartyID = accountsDAO.getThirdPartyID(notAvailableSlot.getClientId());

			String payBuy = "0";
			if (notAvailableSlot.getPayBy().equals(Constants.PAY_BY_THIRD_PARTY)) {
				payBuy = "1";
			}

			String date = DateTimeUtils.getDateinSimpleFormate(new Date());
			String stemp[] = date.split(" ");

			String temps[] = stemp[0].split("-");
			date = temps[2] + "-" + temps[1] + "-" + temps[0];
			
			if(notAvailableSlot.getPreDate()==1){
				date = DateTimeUtils.getCommencingDate1(commencing);
			}

			ClientDAO clientDAO = new JDBCClientDAO(connection);
			Client client = clientDAO.getClientDetails(notAvailableSlot.getClientId());
			String clientname = client.getTitle() + " " + client.getFirstName() + " " + client.getLastName();

			CompleteAppointment completeAppointment = new CompleteAppointment();
			completeAppointment.setClientId(notAvailableSlot.getClientId());
			completeAppointment.setPractitionerId(Integer.toString(notAvailableSlot.getDiaryUserId()));
			completeAppointment.setUser(clientname);
			completeAppointment.setInvoiceDate(date);
			completeAppointment.setChargeId(notAvailableSlot.getApmtType());
			completeAppointment.setChargeType("Charge");
			completeAppointment.setAppointmentid(Integer.toString(appointmentid));
			completeAppointment.setPractitionerName(notAvailableSlot.getDiaryUser());
			completeAppointment.setLocation(location);
			completeAppointment.setIpd("0");
			completeAppointment.setGpriscid("0");
			completeAppointment.setGinvstid("0");
			completeAppointment.setAdditionalcharge_id(null);
			completeAppointment.setWardid(null);
			completeAppointment.setPayBuy(payBuy);
			completeAppointment.setQuantity(1);
			completeAppointment.setCommencing(notAvailableSlot.getCommencing());
			
			int result=0;
			// insert into apm_invoice
			int selfInvoice = completeAptmDAO.saveAmpmInvoice(completeAppointment, loginInfo.getId(),loginInfo.getUserId());
			if(!opdregchargeamount.equals("0") || loginInfo.isLmh()){
			// insert in apm_invoice_assessment
				
			//completeAppointment.setCharges(totalamount);
				completeAppointment.setCharges(opdregchargeamount);
//			NotAvailableSlot not = notAvailableSlotDAO.getOTData(Integer.toString(appointmentid));
//			if (!notAvailableSlot.getProcedure().equals("0")) {
//				completeAppointment.setCharges(not.getChargeamout());
//				completeAppointment.setMasterchargetype(notAvailableSlot.getProcedure());
//			}
				String apmtTYpeText = notAvailableSlotDAO.getAppointmentTypeText(notAvailableSlot.getApmtType());
				completeAppointment.setApmtType(apmtTYpeText);
				completeAppointment.setMasterchargetype("Appointment Charge");
				result = completeAptmDAO.saveInvoiceAssesment(completeAppointment, selfInvoice);
			}
			if(!consultationcharge.equals("0")){
				//  12 July 2018 /to set registration charge
				completeAppointment.setApmtType("Consultation Charge");
				completeAppointment.setCharges(consultationcharge);
				completeAppointment.setMasterchargetype("OPD Charge");
				result = completeAptmDAO.saveInvoiceAssesment(completeAppointment, selfInvoice);
			}
			if(!othercharge.equals("0")){
				//  12 July 2018 /to set registration charge
				completeAppointment.setApmtType("Other Charge");
				completeAppointment.setCharges(othercharge);
				completeAppointment.setMasterchargetype("OPD Charge");
				result = completeAptmDAO.saveInvoiceAssesment(completeAppointment, selfInvoice);
			}

			ChargesAccountProcessingDAO chargesAccountProcessingDAO = new JDBCChargeAccountProcesDAO(connection);
			// logdata
			String status = "Created";
			int log = accountLogDAO.saveAmpmInvoice(completeAppointment, selfInvoice, status,
					DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone()));

			// reset invoice
			int resetinv = accountsDAO.getMaxResetInv();
			int resetcreditinv = accountsDAO.getMaxResetCreditInv();
			int rinv = 0;
			if (resetinv > resetcreditinv) {
				rinv = resetinv + 1;
			} else {
				rinv = resetcreditinv + 1;
			}
			IpdDAO ipdDAO = new JDBCIpdDAO(connection);
			int ipdid = 0;
			if (invcetype != null) {
				if (invcetype.equals("2")) {
					ipdid = ipdDAO.getLastIpdId(notAvailableSlot.getClientId());
				} else {
					ipdid = 0;
				}
			} else {
				ipdid = 0;
			}
			int invoiceid = accountsDAO.saveChargesInvoice(notAvailableSlot.getPayBy(),
					DateTimeUtils.getCommencingDate1(commencing), Integer.parseInt(notAvailableSlot.getClientId()),
					debit, discount, "", thirdPartyID, location,
					DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone()), loginInfo.getId(),
					"0", invcetype, rinv, null, null, String.valueOf(notAvailableSlot.getDiaryUserId()), ipdid,notAvailableSlot.getPreDate(),"0");
			DateFormat dateFormat1 = new SimpleDateFormat("yyyy");
			Calendar cal1 = Calendar.getInstance();
			String a_year = dateFormat1.format(cal1.getTime());
			//lokeh for generating seqno
			int res1 = accountsDAO.getMaxOpdseqNo(a_year);
			res1 =  res1+1;
			int ress = accountsDAO.updateInvoiceSeqNo("1",res1,invoiceid,a_year);

		
			// posting opd invoice
			String podate = DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone());

			int res = chargesAccountProcessingDAO.updateIclosed(Integer.toString(invoiceid), podate);
			if(loginInfo.isShow_unpost()!=true){
				res = chargesAccountProcessingDAO.updateIpost(Integer.toString(invoiceid), podate);
			}
			
			String lcommencing=DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone());
			
			if(notAvailableSlot.getPreDate()==1){
				lcommencing = DateTimeUtils.getCommencingDate1(commencing)+" "+DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone()).split(" ")[1];
			}

			
			// ledger for credit invoice
			if (invoiceid > 0) {
				String itype = accountsDAO.getInvoiceTypeId(invoiceid);
				// String serviceid =
				// chargesAccountProcessingDAO.getLedgrServiceIds(itype);
				String serviceid = itype;
				String ledgerid = chargesAccountProcessingDAO.getledgerID(serviceid, "0", "0");

				double lbal = chargesAccountProcessingDAO.getLedgerBalance(ledgerid);
				lbal = lbal + debit;
				String credit = "" + debit + "";
				String ldebit = "0";
				String product = "xxxxx";
				String partyid = notAvailableSlot.getClientId();
				//String lcommencing = DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone());
				int saveledger = chargesAccountProcessingDAO.saveLedger(partyid, product, ldebit, credit, lbal,
						ledgerid, lcommencing, "" + invoiceid + "", 0,"0","0","0","0","0",0,0,"0");
				
				//second effect
				 lbal = 0;
				 credit = "0";
				 ldebit = "" + debit + "";
				 product = "xxxxx";
				 partyid = notAvailableSlot.getClientId();
				 //lcommencing = DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone());
				 saveledger = chargesAccountProcessingDAO.saveLedger(partyid, product, ldebit, credit, lbal,
						ledgerid, lcommencing, "" + invoiceid + "", 0,"0","0","0","0","0",0,0,"0");
			}
			// update discount
			String userid = loginInfo.getUserId();
			String datetime = DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone());
			if(notAvailableSlot.getPreDate()==1){
				datetime = DateTimeUtils.getCommencingDate1(commencing)+" "+DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone()).split(" ")[1];
			}
			
			int p = accountsDAO.updatePercentageAmount(Integer.toString(invoiceid), discounts, disctype, userid,
					datetime);

			double discdebit = Double.parseDouble(discounts);
			if (disctype.equals("0")) {
				discdebit = (debit * Double.parseDouble(discounts)) / 100;
			}

			// discount ledger
			if (discdebit > 0) {

				String serviceid = chargesAccountProcessingDAO.getLedgrServiceIds("Discount");
				String ledgerid = chargesAccountProcessingDAO.getledgerID(serviceid, "0", "0");

				double lbal = chargesAccountProcessingDAO.getLedgerBalance(ledgerid);
				lbal = lbal + discdebit;
				String credit = "" + discdebit + "";
				String ldebit = "0";
				String product = "" + invoiceid + "";
				String partyid = notAvailableSlot.getClientId();
				//String lcommencing = DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone());
				int saveledger = chargesAccountProcessingDAO.saveLedger(partyid, product, ldebit, credit, lbal,
						ledgerid, lcommencing, "" + invoiceid + "", 0,"0","0","0","0","0",0,0,"0");
				
				//second effect
				 lbal = 0;
				 credit = "0";
				 ldebit = "" + discdebit + "";
				 product = "xxxxx";
				 partyid = notAvailableSlot.getClientId();
				 //lcommencing = DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone());
				 saveledger = chargesAccountProcessingDAO.saveLedger(partyid, product, ldebit, credit, lbal,
						ledgerid, lcommencing, "" + invoiceid + "", 0,"0","0","0","0","0",0,0,"0");

			}

			// save log
			int invoiceid1 = accountLogDAO.saveChargesInvoice(notAvailableSlot.getPayBy(),
					DateTimeUtils.getCommencingDate1(commencing), Integer.parseInt(notAvailableSlot.getClientId()),
					debit, discount, "", thirdPartyID, location, invoiceid,
					DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone()));

			String chargeInvoiceid = accountsDAO.getAppointmentInvoiceid(Integer.toString(appointmentid));
			int save = accountsDAO.saveChargesAssesment(Integer.parseInt(chargeInvoiceid), invoiceid);
			int update = accountsDAO.updateChargeType(chargeInvoiceid, "Submit");

			String chequeno = request.getParameter("chequeno");
			String bankname = request.getParameter("bankname");

			//update charge invoiced
			ArrayList<Master>chargeidList = accountsDAO.getInvoicedChargeidList(invoiceid);
			for(Master m : chargeidList){
				int upc = accountsDAO.updateChargeInvoideid(m.getId(),invoiceid);
			}
			
			// save payment
			if(!payAmount.equals("0")|| loginInfo.isLmh()){
				int re = accountsDAO.saveChargesPayment(notAvailableSlot.getClientId(), invoiceid, payAmount, howpaid,
						thirdPartyID, notAvailableSlot.getInvoiceNote(), DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone()), 0,
						loginInfo.getUserId(), chequeno, bankname,notAvailableSlot.getPreDate(),DateTimeUtils.getCommencingDate1(commencing),invcetype,"","");
				
				int upstatus = accountsDAO.updateOpdPaymentStatus(appointmentid,invoiceid);
				
				//update invoice type payment autono
				int maxno = accountsDAO.getMaxInvoiceTypePaymentNo(re,invcetype);
				int u = accountsDAO.updateInvoicetypePaymentNo(re,maxno,invcetype);
				// Sms to Patient
				ClinicDAO clinicDAO = new JDBCClinicDAO(connection);
				Clinic clinic = clinicDAO.getCliniclistDetails(loginInfo.getClinicid());
				boolean isPaymentSms = clinicDAO.isPaymentSMSActive(loginInfo.getClinicid());
				if (loginInfo.isOpd_payamnt_sms()) {
					UserProfileDAO userProfileDAO = new JDBCUserProfileDAO(connection);
					String templateid = userProfileDAO.getSMSTemplateID("Payment");
					String msg = "Thanks " + clientname + " for payment of Rupees " + payAmount + " from- "
							+ clinic.getClinicName() + "";
					SmsService service = new SmsService();
					service.sendSms(msg, client.getMobNo(), loginInfo, new EmailLetterLog(),templateid);
	
				}
	
				int result1 = accountLogDAO.saveChargesPayment(notAvailableSlot.getClientId(), invoiceid, payAmount,
						howpaid, thirdPartyID, "", DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone()), "");
	
				// ledger services
				ArrayList<Master> ledgerservicesList = accountsDAO.getLedgerServicesList(invoiceid);
	
				int l = 1;
				// for(Master master : ledgerservicesList){
	
				String itype = accountsDAO.getInvoiceTypeId(invoiceid1);
				// String serviceid =
				// chargesAccountProcessingDAO.getLedgrServiceIds(itype);
				String serviceid = itype;
				String ledgerid = chargesAccountProcessingDAO.getledgerID(serviceid, howpaid,
						notAvailableSlot.getBnkname());
	
				double lbal = chargesAccountProcessingDAO.getLedgerBalance(ledgerid);
				lbal = lbal + Double.parseDouble(payAmount);
				String credit = "0";
				String ldebit = payAmount;
				String product = "xxxxx";
				String partyid = notAvailableSlot.getClientId();
				//String lcommencing = DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone());
				int saveledger = chargesAccountProcessingDAO.saveLedger(partyid, product, ldebit, credit, lbal, ledgerid,
						lcommencing, "" + invoiceid + "", re,"0","0","0","0","0",0,0,"0");
				
				//second effect
				 lbal = 0;
				 credit = payAmount;
				 ldebit = "0";
				 product = "xxxxx";
				 partyid = notAvailableSlot.getClientId();
				 lcommencing = DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone());
				 saveledger = chargesAccountProcessingDAO.saveLedger(partyid, product, ldebit, credit, lbal,
						ledgerid, lcommencing, "" + invoiceid + "", 0,"0","0","0","0","0",0,0,"0");
			 }else{
				 //if payamount not zero
				 int upstatus = accountsDAO.updateOpdPaymentStatus(appointmentid,invoiceid);
			 }

		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	public void setFromData() throws Exception{
		Connection connection = null;
		int totalCount =0;
		try{
			String searchClient = clientForm.getSearchText();
			String status = clientForm.getStatus();
			if(searchClient.equals(""))
			{
				searchClient = (String) session.getAttribute("searchClient");
				
				if(searchClient == null){
					searchClient = "";
				}
			}
		connection = Connection_provider.getconnection();
		ClientDAO clientDAO = new JDBCClientDAO(connection);
		ArrayList<Client> allPatientList = new ArrayList<Client>();

		/*session.setAttribute("csdiaryuser", clientForm.getDiaryUser());
		session.setAttribute("showall", clientForm.isShowAll());*/
		
		String diaryUser = "0";
		
		if((String)session.getAttribute("csdiaryuser")!=null){
			diaryUser = (String)session.getAttribute("csdiaryuser");
		}
		boolean showAll = false;
				
		if(session.getAttribute("showall")!=null){
			showAll = (Boolean)session.getAttribute("showall");
		}
				
		
		clientForm.setDiaryUser(diaryUser);
	//	clientForm.setShowAll(showAll);;
		
		
		
		/*if(!searchClient.equals("")){
			 totalCount = clientDAO.getTotalClientCountOfSearch(loginInfo.getId(),searchClient,status,clientForm.isShowAll(),clientForm.getDiaryUser());
			 pagination.setPreperties(totalCount);
			allPatientList = clientDAO.getClientofSearch(pagination,searchClient,loginInfo.getId(),status,clientForm.isShowAll(),clientForm.getDiaryUser());
		}else{
			 totalCount = clientDAO.getTotalClientCount(loginInfo.getId(),clientForm.isShowAll(),clientForm.getDiaryUser());
			 pagination.setPreperties(totalCount);
			allPatientList = clientDAO.getAllPatient(pagination,loginInfo.getId(),clientForm.isShowAll(),clientForm.getDiaryUser());

		}*/
		
		 totalCount = clientDAO.getTotalClientCountOfSearch(loginInfo.getId(),searchClient,status,clientForm.isShowAll(),clientForm.getDiaryUser(),"",pagination);
		 pagination.setPreperties(totalCount);
		allPatientList = clientDAO.getClientofSearch(pagination,searchClient,loginInfo.getId(),status,clientForm.isShowAll(),clientForm.getDiaryUser(),"","","",clientForm.getStatus());
		
		
		clientForm.setSearchText(searchClient);
		
		pagination.setPage_records(allPatientList.size());
		clientForm.setTotalRecords(totalCount);
		clientForm.setPagerecords(Integer.toString(pagination.getPage_records()));
		clientForm.setAllPatientList(allPatientList);
		}
		catch(Exception e){
			e.printStackTrace();
			
		}finally{
			
			connection.close();
		}
	}
	
	public String update() throws SQLException{
		if(!verifyLogin(request)){
			return "login";
		}
		Connection connection = null;
		try{
			int id = clientForm.getId();
			Client client = new Client();
			connection = Connection_provider.getconnection();
			ClientDAO clientDAO = new JDBCClientDAO(connection);
			
			//for getting previous client data before update
			Client oldclientdata = clientDAO.getPatient(id);
			
			if(DateTimeUtils.isNull(clientForm.getOtherRef()).equals(""))
			{
				client.setReference(clientForm.getReference());
				
			}else{
				String reference = clientForm.getOtherRef();
				int save = clientDAO.insertOtherReference(client, reference);
				int id1 = clientDAO.getMaxIdOfRefernce();
				client.setReference(Integer.toString(id1));
			
			}
			if(DateTimeUtils.isNull(clientForm.getOtherSourceOfIntro()).equals(""))
			{
				client.setSourceOfIntro(clientForm.getSourceOfIntro());

			}
			else{
				String otherSourceOfIntro = clientForm.getOtherSourceOfIntro();
				int save = clientDAO.insertOtherSourceOfIntro(client, otherSourceOfIntro);
				int id1 = clientDAO.getMaxIdOfSourceOfIntro();
				client.setSourceOfIntro(Integer.toString(id1));
			}
			if(DateTimeUtils.isNull(clientForm.getOtherOccupation()).equals(""))
			{
				client.setOccupation(clientForm.getOccupation());

			}
			else{
				String otherOccupation = clientForm.getOtherOccupation();
				int save = clientDAO.insertOtherOccupation(client, otherOccupation);
				int id1 = clientDAO.getMaxIdOfOccupation();
				client.setOccupation(Integer.toString(id1));
			}
			
			if(DateTimeUtils.isNull(clientForm.getOtherCondition()).equals(""))
			{
				client.setTreatmentType(clientForm.getTreatmentType());

			}
			else{
				String otherCondition = clientForm.getOtherCondition();
				int save = clientDAO.insertOtherCondition(client, otherCondition);
				int id1 = clientDAO.getMaxIdOfCondition();
				client.setTreatmentType(Integer.toString(id1));
			}
			client.setTitle(clientForm.getTitle());
			client.setFirstName(clientForm.getFirstName());
			client.setMiddlename(clientForm.getMiddleName());
			client.setLastName(clientForm.getLastName());
			client.setAddress(clientForm.getAddress());
			client.setCountry(clientForm.getCountry());
			client.setDob(clientForm.getDob());
			client.setEmail(clientForm.getEmail());
			client.setGender(clientForm.getGender());
			client.setMobNo(clientForm.getMobNo());
			client.setPostCode(clientForm.getPostCode());
			//client.setReference(clientForm.getReference());
			//client.setSourceOfIntro(clientForm.getSourceOfIntro());
			client.setTown(clientForm.getTown());
			client.setType(clientForm.getType());
			client.setTypeName(clientForm.getTypeName());
			//client.setOccupation(clientForm.getOccupation());
			client.setExpiryDate(clientForm.getExpiryDate());
			client.setWhopay(clientForm.getWhopay());
			client.setPolicyAuthorzCode(clientForm.getPolicyAuthorzCode());
			client.setPolicyNo(clientForm.getPolicyNo());
			client.setKnownAs(clientForm.getKnownAs());
			client.setCounty(clientForm.getCounty());
			client.setHomeNo(clientForm.getHomeNo());
			client.setWorkNo(clientForm.getWorkNo());
			client.setEmailCc(clientForm.getEmailCc());
			client.setPrefContactMode(clientForm.getPrefContactMode());
			client.setEmergencyContName(clientForm.getEmergencyContName());
			client.setEmergencyContNo(clientForm.getEmergencyContNo());
			client.setPatientType(clientForm.getPatientType());
			//client.setGpname(clientForm.getGpname());
			client.setEmployerName(clientForm.getEmployerName());
			client.setReferedDate(clientForm.getReferedDate());
			//client.setTreatmentType(clientForm.getTreatmentType());
			client.setPolicyExcess(clientForm.getPolicyExcess());
			client.setGpname(clientForm.getGpname());
			client.setDoctorsurgery(clientForm.getGptypeName());
			client.setSourceOfIntroName(clientForm.getSourceOfIntroName());
			client.setSecondLineaddress(clientForm.getSecondLineaddress());
			client.setDocumentID(clientForm.getDocument_name());
			client.setDocumentValue(clientForm.getDocumentValue());
			//update adhar no and tp membership no
			client.setAdhno(clientForm.getAdhno());
			client.setTpmemb(clientForm.getTpmemb());
			
			
			clientDAO.addToHISDocumentLog(clientForm.getDocument_name(), clientForm.getDocumentValue(), ""+id);
			
			//note
			client.setClientNote(clientForm.getClientNote());
			client.setAccountNote(clientForm.getAccountNote());
			client.setClinicalNote(clientForm.getClinicalNote());
			client.setLastModifiedDate(DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone()));
			client.setNhsNumber(clientForm.getNhsNumber());
			if(clientForm.isHospitalborn()){
				client.setHospitalborn("1");
			}else{
				client.setHospitalborn("0");
			}
			File userImage = clientForm.getUserImage();
			String userImageFileName = clientForm.getUserImageFileName();
			if(userImageFileName!=null){			
				userImageFileName = loginInfo.getClinicUserid()+"_"+id+"_"+userImageFileName;
			}else{
				
				userImageFileName = loginInfo.getClinicUserid()+"_"+id+"_profileimg.png";
				
				String filepath = request.getRealPath("/liveData/"+userImageFileName);
				String clientImageData = clientForm.getProfileimg();
				byte[] imagedata = DatatypeConverter.parseBase64Binary(clientImageData.substring(clientImageData.indexOf(",") + 1));
				BufferedImage bufferedImage = ImageIO.read(new ByteArrayInputStream(imagedata));
				if(!clientForm.getProfileimg().equals("")){
					ImageIO.write(bufferedImage, "png", new File(filepath));
				}
			}
			
            String userImageContentType = clientForm.getUserImageContentType();
			
			if(clientForm.getUserImageContentType()!=null){
			String filePath = request.getRealPath("/liveData/");			
//			System.out.println("Server path:" + filePath);
			File fileToCreate = new File(filePath, userImageFileName);
			FileUtils.copyFile(userImage, fileToCreate);
			

			//update image name
			int res=clientDAO.updateClientUserImage(userImageFileName,id);
			
			}
			
			//  06/12/2018
			client.setCompname(clientForm.getCompname());
			client.setNeisno(clientForm.getNeisno());
			client.setDesignationbytp(clientForm.getDesignationbytp());
			client.setUnitstation(clientForm.getUnitstation());
			client.setRelationvbytpe(clientForm.getRelationvbytpe());
			client.setClaimbytp(clientForm.getClaimbytp());
			client.setColliery(clientForm.getColliery());
			client.setAreabytp(clientForm.getAreabytp());
			client.setPolicyholder(clientForm.getPolicyholder());
			if(clientForm.getMaritalsts().equals("0")){
				client.setMaritalsts("");
			}else{
			client.setMaritalsts(clientForm.getMaritalsts());
		}
			
			client.setMothername(clientForm.getMothername());
			client.setFathername(clientForm.getFathername());
			client.setBirthplace(clientForm.getBirthplace());
			String birthtime=clientForm.getHourls()+":"+clientForm.getMinutels()+":00";
			client.setBirthtime(birthtime.replaceAll(" ", ""));
			client.setPatientcategory(clientForm.getPatientcategory());
			client.setDiaryUser(clientForm.getDiaryUser());
			client.setEnrollcode(clientForm.getEnrollcode());
			client.setClientName(clientForm.getClientName());
			if(!DateTimeUtils.isNull(clientForm.getDeptcampArea()).equals("")) {
				client.setCampArea(clientForm.getDeptcampArea());
				
			}else {
				client.setCampArea(clientForm.getCampArea());
			}
			//client.setCampArea(clientForm.getCampArea());
			String patientCategory="";
			if(!DateTimeUtils.numberCheck(client.getTypeName()).equals("0")){
				patientCategory = clientDAO.getThirdPartyCompanyName(client.getTypeName());
			}
			client.setPatientcategory(patientCategory);
			//update chraibgh
			client.setPatientoccc(clientForm.getPatientoccc());
			client.setPatientincm(clientForm.getPatientincm());
			client.setPatienthusocc(clientForm.getPatienthusocc());
			client.setPatienthusincome(clientForm.getPatienthusincome());
			client.setPatientEductn(clientForm.getPatientEductn());
			client.setPathusbEductn(clientForm.getPathusbEductn());
			client.setReligion(clientForm.getReligion());
			client.setCast(clientForm.getCast());
			client.setClientName(clientForm.getClientName());
			client.setPlan(clientForm.getPlan());
			client.setPlanid(clientForm.getPlanid());
			client.setId(clientForm.getId());
			client.setPhysioipd(clientForm.getPhysioipd());
			int result = clientDAO.updatePatient(client,id,loginInfo);
			
			/*// (10/03/23) Chraibgh Priyanka
			       Client client4=clientDAO.getCurrentyearbyid(id);
			       int CurrentYear = Calendar.getInstance().get(Calendar.YEAR)%100;
			       int CurrentMonth = (Calendar.getInstance().get(Calendar.MONTH)+1);
			       int CurrentYear1 = Calendar.getInstance().get(Calendar.YEAR);
			       int Currentyear2=client4.getCurrentyear();
			    if(loginInfo.isMatrusevasang()){
			    	
			    	  if((CurrentYear1!=client4.getCurrentyear() && CurrentMonth>=4)){
			    	  int register=clientDAO.saveRegnobyfinyear(id,CurrentYear1,client);
			    	 }
			    	  else if((CurrentYear1-Currentyear2)>=2 && CurrentMonth<4){
			    	  int register=clientDAO.saveRegnobyfinyear(id,CurrentYear1-1,client);
					}
			    	
			    }
			 // (10/03/23) Chraibgh Priyanka
				
                if(loginInfo.isMatrusevasang()){
             	    Client client3=clientDAO.getPatientnofromregno(id);
             	    String financiyalYearFrom="";
			        String financiyalYearTo="";
			        
				    if (CurrentMonth<4) {
    			        financiyalYearFrom=""+(CurrentYear-1);
    			        financiyalYearTo=""+(CurrentYear);
    			    } else {
    			        financiyalYearFrom=""+(CurrentYear);
    			        financiyalYearTo=""+(CurrentYear+1);
    			    }
             	   
    			       String finyear=financiyalYearFrom+"/"+financiyalYearTo+"/"+client3.getId();
    			       if(((CurrentYear1!=client4.getCurrentyear()) && CurrentMonth>=4)||((CurrentYear1-Currentyear2)>=2 && CurrentMonth<4)){
    			       int updateregno=clientDAO.updateReginobyfinyear(finyear,client3.getId());
    			       }
                }*/
			    
			
			//(13/04/2023) chraibgh priyanka
			/*if(loginInfo.isMatrusevasang()){
				client=clientDAO.getRegiDatebyId(id);
				String regdate=client.getRegDate();
				Date date1 = new SimpleDateFormat("yyyy-MM-dd").parse(regdate);
				String cdate = DateTimeUtils.getUKCurrentDataTime("India").split(" ")[0];
				Date date2 = new SimpleDateFormat("yyyy-MM-dd").parse(cdate);
			    long duration=date2.getTime()-date1.getTime();
			    long daydiff = duration / (1000 * 60 * 60 * 24);
			   
			        if(daydiff>27){
			         int res=clientDAO.updateregdatebyid(id);
			        }
			}*/
			int planid=0;
			int activeplanid=0;
			String actplan="";
			Client client1=new Client();
			Client client2=new Client();
			int totaldaysphysio=0;
			String patientid="";
			
			if(loginInfo.isPhysio() && clientForm.getPhysioipd().equals("0")){
			planid=(int) session.getAttribute("planid");
			activeplanid=(int) session.getAttribute("activeplanid");
			actplan=Integer.toString(activeplanid);
			client1=clientDAO.getPatientActiveplanDetails(actplan);
			client2=clientDAO.getNumberofPhysioDays(patientid);
			}
			int countdays=client2.getDay();
			int totalphysiodays=client2.getTotaldays();
			
			if(loginInfo.isPhysio() && (oldclientdata.getRegularpatient()==1 && clientForm.getPhysioipd().equals("0"))){
			planid=(int) session.getAttribute("planid");
			activeplanid=(int) session.getAttribute("activeplanid");
			totaldaysphysio=client1.getDay();	   
			int physioresult=clientDAO.savePhysioPatient(id,client,planid,activeplanid,totaldaysphysio);
			int res=clientDAO.updateRegularPatienttoZero(id);
			if(planid==5){
				int res1=clientDAO.updateRegularPatient(id);// again set value regularpatient to 1;
			 }
			}
			/*if(planid==1 && days<6){
				days=days+1;
				int physiores=clientDAO.updatePhysiopatientdays(client,days);
			}*/
			
			//oldclientdata
			//to store log of patient update
			if(result>0){
				DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			    Calendar cal = Calendar.getInstance();
			    String datetime = dateFormat.format(cal.getTime());  
				int res = clientDAO.savePatientLog(loginInfo.getUserId(),id,oldclientdata,client,datetime);
			}
			clientForm.setMessage("Patient Updated Suceessfully");
			addActionMessage("");
			String isfromaddpatientlmh123 = request.getParameter("isfromaddpatientlmh123");
			CompleteAptmDAO completeAptmDAO = new JDBCCompleteAptmDAO(connection);
			if(DateTimeUtils.isNull(isfromaddpatientlmh123).equals("1") && loginInfo.isLmh()){
				String opdoption=clientForm.getOpdoption();
				if(opdoption.equals("regwopd")) {
					String department="";
					if(client.getPhysioipd().equals("0")) {
						//for physio ipd
						department=clientForm.getDiaryUser();
					}else {
					    department=completeAptmDAO.getpractitonerdept(loginInfo.getId());
					}
					//String department=clientForm.getDiaryUser();
	//				String department="5";
					String invoicetype="10";
					String paymode=clientForm.getPaymenttype();
					String paidamount=clientForm.getNetamount();
					String discountamt=DateTimeUtils.numberCheck(clientForm.getDiscountvalue());
					String invoiceNote = request.getParameter("invoiceNote");
					
					//(13/04/2023) chraibgh priyanka
					if(loginInfo.isMatrusevasang()){
						client=clientDAO.getRegiDatebyId(id);
						String regdate=client.getRegDate();
						Date date1 = new SimpleDateFormat("yyyy-MM-dd").parse(regdate);
						String cdate = DateTimeUtils.getUKCurrentDataTime("India").split(" ")[0];
						Date date2 = new SimpleDateFormat("yyyy-MM-dd").parse(cdate);
					    long duration=date2.getTime()-date1.getTime();
					    long daydiff = duration / (1000 * 60 * 60 * 24);
					   
					        if(daydiff>27){
					         int res=clientDAO.updateregdatebyid(id);
					        }
					}
					
					int day=0;
					if(loginInfo.isPhysio()){
					if(planid!=0){
					    day=1;
					  }
					}
					String disctype=DateTimeUtils.numberCheck(clientForm.getDiscounttype());
					String opdregchargeamount=DateTimeUtils.numberCheck(clientForm.getRegistrationcharge());
					String consultationcharge=DateTimeUtils.numberCheck(clientForm.getConsultationcharge());
					String othercharge=DateTimeUtils.numberCheck(clientForm.getOthercharge());
					double grandtotal=DateTimeUtils.convertToDouble(opdregchargeamount)+DateTimeUtils.convertToDouble(consultationcharge)+DateTimeUtils.convertToDouble(othercharge);
					if(paymode.equals("Credit")) {
						paidamount="0";
					}
					double opdregcharge = DateTimeUtils.convertToDouble(opdregchargeamount);
					/*if(opdregcharge>0){
						int year = Calendar.getInstance().get(Calendar.YEAR);
						int res = clientDAO.updateRegChargeApplied(year,id);
					}*/
					//old patient
					int newpatient;
					String cdate = DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone()).split(" ")[0];
					String registerdate=oldclientdata.getRegisterdate();
					SimpleDateFormat dateFormat1=new SimpleDateFormat("yyyy-MM-dd");
					Date dt=dateFormat1.parse(registerdate);
					String date1=dateFormat1.format(dt);
					if(loginInfo.isLmh() && !loginInfo.isMatrusevasang() && !loginInfo.isPhysio() && !loginInfo.isAmravati()){
						if(cdate.equals(date1)){
							newpatient=1;
						}
						else{
							newpatient=2;
						}
					}else{
					 newpatient =2;
					}
					String appointment=saveappointmentwithDept(department, ""+id,1,newpatient,opdregcharge,0,null,activeplanid,day);
	//				String appointment=savedirectappointment(department, ""+id);
					int appointmentid=DateTimeUtils.convertToInteger(appointment);
					cashdeskdepartmentdirect(appointmentid, invoicetype, paymode, ""+grandtotal, discountamt, paidamount, connection, disctype, opdregchargeamount, consultationcharge, othercharge,invoiceNote);
	//				cashdeskdirect(appointmentid, invoicetype, paymode, ""+grandtotal, discountamt, paidamount, connection, disctype, opdregchargeamount, consultationcharge, othercharge);
					session.setAttribute("lmh_register_patientid", String.valueOf(id));
					session.setAttribute("lmh_register_apmtid", String.valueOf(appointmentid));
					
					
					return "sendtoblackletter";
				}
				return "patientRegistration";
			}else if(loginInfo.isPhysio_ipd()){
				//For balgopal physio ipd condition added
                String opdoption=clientForm.getOpdoption();
				if(opdoption.equals("regwopd")) {
					//String department="";
					/*
					 * if(client.getPhysioipd().equals("0")) { //for physio ipd
					 * department=clientForm.getDiaryUser(); }else {
					 * department=completeAptmDAO.getpractitonerdept(loginInfo.getId()); }
					 */
					String department=clientForm.getDiaryUser();
	//				String department="5";
					String invoicetype="10";
					String paymode=clientForm.getPaymenttype();
					String paidamount=clientForm.getNetamount();
					String discountamt=DateTimeUtils.numberCheck(clientForm.getDiscountvalue());
					String invoiceNote = request.getParameter("invoiceNote");
					
					//(13/04/2023) chraibgh priyanka
					if(loginInfo.isMatrusevasang()){
						client=clientDAO.getRegiDatebyId(id);
						String regdate=client.getRegDate();
						Date date1 = new SimpleDateFormat("yyyy-MM-dd").parse(regdate);
						String cdate = DateTimeUtils.getUKCurrentDataTime("India").split(" ")[0];
						Date date2 = new SimpleDateFormat("yyyy-MM-dd").parse(cdate);
					    long duration=date2.getTime()-date1.getTime();
					    long daydiff = duration / (1000 * 60 * 60 * 24);
					   
					        if(daydiff>27){
					         int res=clientDAO.updateregdatebyid(id);
					        }
					}
					
					int day=0;
					if(loginInfo.isPhysio()){
					if(planid!=0){
					    day=1;
					  }
					}
					String disctype=DateTimeUtils.numberCheck(clientForm.getDiscounttype());
					String opdregchargeamount=DateTimeUtils.numberCheck(clientForm.getRegistrationcharge());
					String consultationcharge=DateTimeUtils.numberCheck(clientForm.getConsultationcharge());
					String othercharge=DateTimeUtils.numberCheck(clientForm.getOthercharge());
					double grandtotal=DateTimeUtils.convertToDouble(opdregchargeamount)+DateTimeUtils.convertToDouble(consultationcharge)+DateTimeUtils.convertToDouble(othercharge);
					if(paymode.equals("Credit")) {
						paidamount="0";
					}
					double opdregcharge = DateTimeUtils.convertToDouble(opdregchargeamount);
					/*if(opdregcharge>0){
						int year = Calendar.getInstance().get(Calendar.YEAR);
						int res = clientDAO.updateRegChargeApplied(year,id);
					}*/
					//old patient
					int newpatient;
					String cdate = DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone()).split(" ")[0];
					String registerdate=oldclientdata.getRegisterdate();
					SimpleDateFormat dateFormat1=new SimpleDateFormat("yyyy-MM-dd");
					Date dt=dateFormat1.parse(registerdate);
					String date1=dateFormat1.format(dt);
					if(loginInfo.isLmh() && !loginInfo.isMatrusevasang() && !loginInfo.isPhysio() && !loginInfo.isAmravati()){
						if(cdate.equals(date1)){
							newpatient=1;
						}
						else{
							newpatient=2;
						}
					}else{
					 newpatient =2;
					}
					String appointment=saveappointmentwithDept(department, ""+id,1,newpatient,opdregcharge,0,null,activeplanid,day);
	//				String appointment=savedirectappointment(department, ""+id);
					int appointmentid=DateTimeUtils.convertToInteger(appointment);
					cashdeskdepartmentdirect(appointmentid, invoicetype, paymode, ""+grandtotal, discountamt, paidamount, connection, disctype, opdregchargeamount, consultationcharge, othercharge,invoiceNote);
	//				cashdeskdirect(appointmentid, invoicetype, paymode, ""+grandtotal, discountamt, paidamount, connection, disctype, opdregchargeamount, consultationcharge, othercharge);
					session.setAttribute("lmh_register_patientid", String.valueOf(id));
					session.setAttribute("lmh_register_apmtid", String.valueOf(appointmentid));
					
					if(client.getPhysioipd().equals("1")) {
						return "savecashphysioipd";
					}else {
						return "sendtoblackletter";
					}
					//return "sendtoblackletter";
				}
				return "patientRegistration";
			
			}
			
			setFromData();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		finally{
			connection.close();
		}
		return "save";
	}
	
	public String saveparentprisc(String locations,String prepay,String postpay,String otherpay,String priscdosenotes,
			String followupsqty,String followupstype,String priscadvoice,String english,String regional,String hindi,String discharge,
			String clientId,String prectionerid,String conditionid,String json_ipdid) {
		
		Priscription priscription = new Priscription();
		priscription.setPrepay(prepay);
		priscription.setPostpay(postpay);
		priscription.setOtherpay(otherpay);
		priscription.setPriscdose(priscdosenotes);
		priscription.setFollowupsqty(followupsqty);
		priscription.setFollowupstype(followupstype);
		priscription.setPriscadvoice(priscadvoice);
		priscription.setEnglish(english);
		priscription.setRegional(regional);
		priscription.setHindi(hindi);
		priscription.setPriscdosenotes(priscdosenotes);

		Connection connection = null;
		try {

			connection = Connection_provider.getconnection();
			EmrDAO emrDAO = new JDBCEmrDAO(connection);
			PharmacyDAO pharmacyDAO = new JDBCPharmacyDAO(connection);

			priscription.setClientId(clientId);
			priscription.setPrectionerid(prectionerid);
			locations = "opd";
			priscription.setConditionid(conditionid);

			String requestlocationid = request.getParameter("requestlocationid");
			int default_location = 0;
			if (requestlocationid != null) {
				if (requestlocationid.equals("") || requestlocationid.equals("0")) {
					default_location = pharmacyDAO.getByDefaultPharmacyLocation();
				} else {
					default_location = Integer.parseInt(requestlocationid);
				}
			} else {
				default_location = pharmacyDAO.getByDefaultPharmacyLocation();
			}
			/*int saveparent = emrDAO.saveParentPriscription(priscription,
					DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone()), sessionadmissionid, discharge, "0",
					loginInfo.getUserId(), locations, default_location, json_ipdid);*/
			
		} catch (Exception e) {
			// TODO: handle exception
		}

		return null;
	}
	 public String editemp() throws Exception {
		 
		 if(!verifyLogin(request)){
				return "login";
			}
			ArrayList<Client> thirdPartyTypeList = new ArrayList<Client>();
			ArrayList<Client> thirdPartyTypeNameList = new ArrayList<Client>();
			ArrayList<Client> clientOccupationList = new ArrayList<Client>();
			ArrayList<Client> refrenceList = new ArrayList<Client>();
			ArrayList<String> initialList = new ArrayList<String>();
			Connection connection = null;
			try{
				int id = Integer.parseInt(request.getParameter("selectedid1"));
				Client client = new Client();
				connection = Connection_provider.getconnection();
				ClientDAO clientDAO = new JDBCClientDAO(connection);
				refrenceList = clientDAO.getReferenceList();
				Client client2 = new Client();
				client2.getOther();
				refrenceList.add(client2);
				clientForm.setRefrenceList(refrenceList);
				
				initialList = clientDAO.getInitialList();
				clientForm.setInitialList(initialList);
				
				ArrayList<Client>surgeryList = clientDAO.getSurgeryList();
				clientForm.setSurgeryList(surgeryList);
				
				client = clientDAO.getUser(id);
				
				clientForm.setTitle(client.getTitle());
				clientForm.setFirstName(client.getFirstName());
				clientForm.setLastName(client.getLastName());
				clientForm.setMiddleName(client.getMiddlename());
				clientForm.setAddress(client.getAddress());
				clientForm.setCountry(client.getCountry());
				clientForm.setDob(client.getDob());
				clientForm.setEmail(client.getEmail());
				clientForm.setGender(client.getGender());
				clientForm.setMobNo(client.getMobNo());
				clientForm.setPostCode(client.getPostCode());
				clientForm.setReference(client.getReference());
				clientForm.setSourceOfIntro(client.getSourceOfIntro());
				clientForm.setPatientoccc(client.getPatienthusocc());
				clientForm.setPatientincm(client.getPatientincm());
				clientForm.setPatienthusocc(client.getPatienthusocc());
				clientForm.setPatienthusincome(client.getPatienthusincome());
				clientForm.setPatientEductn(client.getPatientEductn());
				clientForm.setPathusbEductn(client.getPathusbEductn());
				clientForm.setReligion(client.getReligion());
				clientForm.setCast(client.getCast());
				clientForm.setTown(client.getTown());
				clientForm.setId(client.getId());
				clientForm.setType(client.getType());
				clientForm.setAdhno(client.getAdhno());
				clientForm.setTpmemb(client.getTpmemb());
				clientForm.setClientName(client.getClientName());
				
				String typeid = client.getType();
				String null1 = null;
				if(typeid!= null1){
					thirdPartyTypeNameList = clientDAO.getThirdTypeNameList(Integer.parseInt(typeid));
				}
				
				
				clientForm.setThirdPartyTypeNameList(thirdPartyTypeNameList);
				clientForm.setTypeName(client.getTypeName());
				clientForm.setExpiryDate(client.getExpiryDate());
				clientForm.setWhopay(client.getWhopay());
				clientForm.setPolicyAuthorzCode(client.getPolicyAuthorzCode());
				clientForm.setPolicyNo(client.getPolicyNo());
				clientForm.setKnownAs(client.getKnownAs());
				//String county = clientDAO.getstatefromid(client.getCounty());
				clientForm.setCounty(client.getCounty());
				clientForm.setHomeNo(client.getHomeNo());
				clientForm.setWorkNo(client.getWorkNo());
				clientForm.setEmailCc(client.getEmailCc());
				clientForm.setPrefContactMode(client.getPrefContactMode());
				clientForm.setEmergencyContName(client.getEmergencyContName());
				clientForm.setEmergencyContNo(client.getEmergencyContNo());
				clientForm.setPatientType(client.getPatientType());
				
				clientForm.setPolicyExcess(client.getPolicyExcess());
				String doctorsurgery = client.getGptypeName();
				ArrayList<GpData>gpList = clientDAO.getGPDataList(doctorsurgery);
				clientForm.setGpDataList(gpList);
				clientForm.setGpname(client.getGpname());
				clientForm.setGptypeName(client.getGptypeName());
				clientForm.setReferedDate(client.getReferedDate());
				clientForm.setEmployerName(client.getEmployerName());
				clientForm.setTreatmentType(client.getTreatmentType());
				clientForm.setId(id);
				clientForm.setSourceOfIntroName(client.getSourceOfIntroName());
				clientForm.setSecondLineaddress(client.getSecondLineaddress());
			
				clientForm.setClientNote(client.getClientNote());
				clientForm.setAccountNote(client.getAccountNote());
				clientForm.setClinicalNote(client.getClinicalNote());
				clientForm.setNhsNumber(client.getNhsNumber());
						//  06/12/2018
				clientForm.setCompname(client.getCompname());
				clientForm.setNeisno(client.getNeisno());
				clientForm.setDesignationbytp(client.getDesignationbytp());
				clientForm.setRelationvbytpe(client.getRelationvbytpe());
				clientForm.setUnitstation(client.getUnitstation());
				clientForm.setClaimbytp(client.getClaimbytp());
				clientForm.setColliery(client.getColliery());
				clientForm.setAreabytp(client.getAreabytp());
				String companyname=clientDAO.gettptypenamebyid(client.getTypeName());
				
				if(companyname==null){
					companyname="";
				}
					
				if(companyname.equals("CGHS")){
					clientForm.setTptypenamestatus("CGHS");
				}else if(companyname.equals("WCL")){
					clientForm.setTptypenamestatus("WCL");
					
				}else if(companyname.equals("INSURANCE COMPANY")){
					clientForm.setTptypenamestatus("INSURANCE");
				}
				else{
					clientForm.setTptypenamestatus("");
				}
				clientForm.setPolicyholder(client.getPolicyholder());
				
				
				InventoryVendorDAO vendorDAO=new JDBCInventoryVendorDAO(connection);
				ArrayList<Master> stateList= vendorDAO.getAllStateList();
				ArrayList<Master> cityList= vendorDAO.getAllCityList();
				clientForm.setStatelist(stateList);
				clientForm.setCitylist(cityList);
				
				clientForm.setCountryList(PopulateList.countryList());
				clientForm.setBirthPlaceList(new ArrayList<>());
				clientForm.setHourList(new ArrayList<>());
				clientForm.setMinuteList(new ArrayList<>());
				clientForm.setRefrenceList(new ArrayList<>());
				clientForm.setDocuList(new ArrayList<>());
				clientForm.setSurgeryList(new ArrayList<>());
				clientForm.setSourceOfIntroList(new ArrayList<>());
				clientForm.setClientOccupationList(new ArrayList<>());
				clientForm.setCondtitionList(new ArrayList<>());
				clientForm.setCountry("India");
				clientForm.setEnrollcode(client.getEnrollcode());
				int enrollcode=clientDAO.checkEnrollCode(client.getTypeName());
				clientForm.setStatusenrollcode(enrollcode);
				int isCampArea=clientDAO.checkCampArea(client.getTypeName());
				clientForm.setIsCampArea(isCampArea);
				clientForm.setCampArea(client.getCampArea());
				
				String isfromadduser = request.getParameter("isfromadduser");
				if(DateTimeUtils.isNull(isfromadduser).equals("1")){
					//clientForm.setIsfromaddpatientlmh(1);
					clientForm.setSearchText(client.getAbrivationid());
					clientForm.setIsfromaddpatientlmh(0);
					clientForm.setDiaryUser(client.getLmh_department());
					clientForm.setPatientcategory(client.getPatientcategory());
					
					//Registration charge
					MasterDAO masterDAO = new JDBCMasterDAO(connection);
					CompleteAptmDAO completeAptmDAO = new JDBCCompleteAptmDAO(connection);
//					ArrayList<DiaryManagement> userList = notAvailableSlotDAO.getUserList(loginInfo.getId());
//					clientForm.setUserList(userList);
					// department ot list
					ArrayList<Master> departmentList = masterDAO.getDisciplineDataListWithChecked();
					clientForm.setDepartmentList(departmentList);
					//double registrationcharge = completeAptmDAO.getOpdRegCharge();
					
					//boolean regchargeapply = completeAptmDAO.testRegChargeApply(id);
					int year = Calendar.getInstance().get(Calendar.YEAR);
					/*if(client.getChargeYear()==year){
						clientForm.setRegchargeapplied(1);
						clientForm.setRegistrationcharge("0");
						clientForm.setNetamount("0");
					}else{
						clientForm.setRegchargeapplied(0);
						clientForm.setRegistrationcharge(String.valueOf(registrationcharge));
						clientForm.setNetamount(String.valueOf(registrationcharge));
					}*/
					
					
					String registrationcharge ="0";
					String masterCharge = "";
					String chargeName ="";
					if(client.getChargeYear()!=year){
						clientForm.setRegchargeapplied(0);
						masterCharge ="Appointment Charge";
						chargeName ="Registration Charge";
					}else{
						masterCharge ="Appointment Charge";
						chargeName ="Follow Up Charge";
						clientForm.setRegchargeapplied(1);
					}
					NotAvailableSlot availableslot =clientDAO.getRegistrationChargeAmount(client.getTypeName(),masterCharge,chargeName);
					registrationcharge = availableslot.getChargeamout();
					clientForm.setRegistrationcharge(String.valueOf(registrationcharge));
					clientForm.setNetamount(String.valueOf(registrationcharge));
					
					String patientType="0";
					if(DateTimeUtils.isNull(client.getWhopay()).equals("Third Party")){
						patientType ="1";
					}
					thirdPartyTypeList = clientDAO.getThirdPartyTypeNew(patientType);
					clientForm.setThirdPartyTypeList(thirdPartyTypeList);
					String paymentType = clientDAO.getPayerPaymentType(client.getTypeName());
					clientForm.setPaymenttype(paymentType);
					return "addnewpatient";
				}else{
					thirdPartyTypeList = clientDAO.getThirdPartyType();
					clientForm.setThirdPartyTypeList(thirdPartyTypeList);
				}
				
				String isfornewadd = request.getParameter("isfornewadd");
				if(DateTimeUtils.isNull(isfornewadd).equals("1")){
					return "addCompleteInfo";
				}
			}catch (Exception e) {
				e.printStackTrace();
			}
			finally{
				connection.close();
			}
			return "editClientPage";
	 }
	 
	/* public String physioRecord() throws Exception{
		 if(!verifyLogin(request)){
				return "login";
			}
		 Connection con=null;
		 try {
			 con=Connection_provider.getconnection();
			ClientDAO clientDAO=new JDBCClientDAO(con);
			Client client=clientDAO.getPhysiorecordfromdeptopd();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		 finally{
				con.close();
			}
		 return null;
	 }*/
	 
	/* public static int getYearFromDate(Date date) {
		    int result = -1;
		    if (date != null) {
		        Calendar cal = Calendar.getInstance();
		        cal.setTime(date);
		        result = cal.get(Calendar.YEAR)%100;
		    }
		    return result;
		}
*/
	 
	 public String addnewIpdPatient() throws Exception{
		 

			if(!verifyLogin(request)){
				return "login";
			}
			Connection connection = null;
			ArrayList<Client> thirdPartyTypeList = new ArrayList<Client>();
			ArrayList<Client> thirdPartyTypeNameList = new ArrayList<Client>();
			ArrayList<Client> clientOccupationList = new ArrayList<Client>();
			ArrayList<Client> refrenceList = new ArrayList<Client>();
			ArrayList<String> initialList = new ArrayList<String>();
			ArrayList<Client> sourceOfIntroList = new ArrayList<Client>();
			String date = DateTimeUtils.isNull(clientForm.getFromdate()) ;
			try{
				
				connection = Connection_provider.getconnection();
				ClientDAO clientDAO = new JDBCClientDAO(connection);

				if (date == null) {
					DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
					Calendar cal = Calendar.getInstance();
					date = dateFormat.format(cal.getTime());
					date = DateTimeUtils.getCommencingDate1(date);
				}else {

					if (date.equals("")) {
						DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
						Calendar cal = Calendar.getInstance();
						date = dateFormat.format(cal.getTime());
						date = DateTimeUtils.getCommencingDate1(date);
					} else {
						date = DateTimeUtils.getCommencingDate1(date);
					}
			   }
				
				//clientOccupationList = clientDAO.getOccupationList();
				
				
				//clientForm.setClientOccupationList(clientOccupationList);
				
				//refrenceList = clientDAO.getReferenceList();
				
				//clientForm.setRefrenceList(refrenceList);
				
				initialList = clientDAO.getInitialList();
				clientForm.setInitialList(initialList);
				
				//sourceOfIntroList = clientDAO.getSourceOfIntroList();
				//clientForm.setSourceOfIntroList(sourceOfIntroList);
				
				//ArrayList<Client>surgeryList = clientDAO.getSurgeryList();
				//clientForm.setSurgeryList(surgeryList);
				//clientForm.setHourList(PopulateList.hourList());
				//clientForm.setMinuteList(PopulateList.getMinuteList());
				
				Client client=new Client();
				ArrayList<Client>planlist=clientDAO.getAllPlanlist();
				clientForm.setPlanlist(planlist);
				
				ArrayList<Client>chargelist=clientDAO.getAllChargeslist();
				clientForm.setChargelist(chargelist);
				
				//set state and city list
				InventoryVendorDAO vendorDAO=new JDBCInventoryVendorDAO(connection);
				ArrayList<Master> stateList= vendorDAO.getAllStateList();
				ArrayList<Master> cityList= vendorDAO.getAllCityList();
				clientForm.setStatelist(stateList);
				clientForm.setCitylist(cityList);
				
				clientForm.setCountryList(PopulateList.countryList());
				clientForm.setBirthPlaceList(new ArrayList<>());
				clientForm.setHourList(new ArrayList<>());
				clientForm.setMinuteList(new ArrayList<>());
				clientForm.setRefrenceList(new ArrayList<>());
				clientForm.setDocuList(new ArrayList<>());
				clientForm.setSurgeryList(new ArrayList<>());
				clientForm.setSourceOfIntroList(new ArrayList<>());
				clientForm.setClientOccupationList(new ArrayList<>());
				clientForm.setCondtitionList(new ArrayList<>());
				clientForm.setCountry("India");
				
				if(loginInfo.isLmh() || loginInfo.isPhysio_ipd()){
					clientForm.setWhopay("Client");
					MasterDAO masterDAO=new JDBCMasterDAO(connection);
					CompleteAptmDAO completeAptmDAO = new JDBCCompleteAptmDAO(connection);
					// department ot list
					ArrayList<Master> departmentList = masterDAO.getDisciplineDataListWithChecked();
					clientForm.setDepartmentList(departmentList);
//					ArrayList<DiaryManagement> userList = notAvailableSlotDAO.getUserList(loginInfo.getId());
//					clientForm.setUserList(userList);
					/*double registrationcharge = completeAptmDAO.getOpdRegCharge();
					clientForm.setRegistrationcharge(String.valueOf(registrationcharge));
					clientForm.setNetamount(String.valueOf(registrationcharge));*/
					
					double registrationcharge = 0.0;
					clientForm.setRegistrationcharge(String.valueOf(registrationcharge));
					clientForm.setNetamount(String.valueOf(registrationcharge));
					
					thirdPartyTypeList = clientDAO.getThirdPartyTypeNew("0");
					clientForm.setThirdPartyTypeList(thirdPartyTypeList);
					
					clientForm.setThirdPartyTypeNameList(thirdPartyTypeNameList);
					clientForm.setFromdate(DateTimeUtils.getCommencingDate1(date));
					
					return "addipdpatient";
				}else{
					thirdPartyTypeList = clientDAO.getThirdPartyType();
					clientForm.setThirdPartyTypeList(thirdPartyTypeList);
					
					thirdPartyTypeNameList = clientDAO.getThirdPartyTypeName();
					clientForm.setThirdPartyTypeNameList(thirdPartyTypeNameList);
				}
				
			}
			catch (Exception e) {
				e.printStackTrace();
			}
			finally{
				connection.close();
			}
			return "addCompleteInfo";
		
		 }
	 public String addnewlocation() throws Exception {
			Connection connection = null;
			try {
				connection = Connection_provider.getconnection();
				MasterDAO masterDAO = new JDBCMasterDAO(connection);
				StringBuffer stringBuffer = new StringBuffer();
				// String addprocedurename =
				// request.getParameter("addprocedurename");
				// String addproceduredescription =
				// request.getParameter("addproceduredescription");

				StringBuilder buffer = new StringBuilder();
				BufferedReader reader = request.getReader();
				String line;
				while ((line = reader.readLine()) != null) {
					buffer.append(line);
				}
				String data = buffer.toString();
				JSONObject jsonObject = new JSONObject(data);
				String location = jsonObject.getString("location");
			
				Master master = new Master();

				master.setCamp_location(location);
				
				int res = masterDAO.addCampLocation(master);

				Response.setContentType("application/json");
				Response.setHeader("Cache-Control", "no-cache");
				Response.getWriter().write("1");
			} catch (Exception e) {
				e.printStackTrace();
			} finally {

				connection.close();
			}
			return null;
		}
	 public String locationlist() throws Exception {

			Connection connection = null;
			try {

				//connection = Connection_provider.getconnection();
				connection = DriverManager.getConnection(
						"" + Constants.DB_HOST + ":3306/" + loginInfo.getClinicUserid()
								+ "?useUnicode=true&characterEncoding=UTF-8",
						"" + Constants.DB_USER + "", "" + Constants.DB_PWD + "");
				request.setCharacterEncoding("UTF-8");
				Response.setCharacterEncoding("UTF-8");
				IpdDAO ipdDAO = new JDBCIpdDAO(connection);
				NotAvailableSlotDAO notAvailableSlotDAO = new JDBCNotAvailableSlotDAO(connection);
				MasterDAO masterDAO=new JDBCMasterDAO(connection);
				String id = request.getParameter("id");
				String action = request.getParameter("action");
				ArrayList<Master> camploactionList= masterDAO.getAllCampLocation();
						

				StringBuffer str = new StringBuffer();
	              
				str.append(
						"<select  name='camplocation' id='camplocation' class='form-control showToolTip chosen' >");
	              
				str.append("<option value='0'>Select Location</option>");
				for (Master master : camploactionList) {
					//   06-jan-2018
					//   25 jan 2018
					str.append("<option value='" + master.getId() + "'>" + master.getCamp_location() + "</option>");
					// str.append("<option
					// value='"+master.getName()+"'>"+master.getName()+"</option>");
				}
				str.append("</select>");

				/*
				 * str.
				 * append("<label id='apmtTypeError' class='text-danger'></label>");
				 * str.append("<div class='col-lg-12 col-md-12'>"); str.
				 * append("<label id='apmtTypeDuration' class='text-danger durane'></label>"
				 * ); str.append("</div>");
				 */

				Response.setContentType("text/html");
				Response.setHeader("Cache-Control", "no-cache");
				Response.getWriter().write("" + str.toString() + "");

			} catch (Exception e) {
				e.printStackTrace();
			}

			return null;
		}
	 
	 public String addCompleteInfoforAfitech() throws SQLException{
			if(!verifyLogin(request)){
				return "login";
			}
			Connection connection = null;
			ArrayList<Client> thirdPartyTypeList = new ArrayList<Client>();
			ArrayList<Client> thirdPartyTypeNameList = new ArrayList<Client>();
			ArrayList<Client> clientOccupationList = new ArrayList<Client>();
			ArrayList<Client> refrenceList = new ArrayList<Client>();
			ArrayList<String> initialList = new ArrayList<String>();
			ArrayList<Client> sourceOfIntroList = new ArrayList<Client>();
			String date = DateTimeUtils.isNull(clientForm.getFromdate()) ;
			try{
				
				connection = Connection_provider.getconnection();
				ClientDAO clientDAO = new JDBCClientDAO(connection);

				if (date == null) {
					DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
					Calendar cal = Calendar.getInstance();
					date = dateFormat.format(cal.getTime());
					date = DateTimeUtils.getCommencingDate1(date);
				}else {

					if (date.equals("")) {
						DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
						Calendar cal = Calendar.getInstance();
						date = dateFormat.format(cal.getTime());
						date = DateTimeUtils.getCommencingDate1(date);
					} else {
						date = DateTimeUtils.getCommencingDate1(date);
					}
			   }
				
				//clientOccupationList = clientDAO.getOccupationList();
				
				
				//clientForm.setClientOccupationList(clientOccupationList);
				
				//refrenceList = clientDAO.getReferenceList();
				
				//clientForm.setRefrenceList(refrenceList);
				
				initialList = clientDAO.getInitialList();
				clientForm.setInitialList(initialList);
				
				//sourceOfIntroList = clientDAO.getSourceOfIntroList();
				//clientForm.setSourceOfIntroList(sourceOfIntroList);
				
				//ArrayList<Client>surgeryList = clientDAO.getSurgeryList();
				//clientForm.setSurgeryList(surgeryList);
				//clientForm.setHourList(PopulateList.hourList());
				//clientForm.setMinuteList(PopulateList.getMinuteList());
				
				Client client=new Client();
				ArrayList<Client>planlist=clientDAO.getAllPlanlist();
				clientForm.setPlanlist(planlist);
				
				ArrayList<Client>chargelist=clientDAO.getAllChargeslist();
				clientForm.setChargelist(chargelist);
				
				//set state and city list
				InventoryVendorDAO vendorDAO=new JDBCInventoryVendorDAO(connection);
				ArrayList<Master> stateList= vendorDAO.getAllStateList();
				ArrayList<Master> cityList= vendorDAO.getAllCityList();
				clientForm.setStatelist(stateList);
				clientForm.setCitylist(cityList);
				
				clientForm.setCountryList(PopulateList.countryList());
				clientForm.setBirthPlaceList(new ArrayList<>());
				clientForm.setHourList(new ArrayList<>());
				clientForm.setMinuteList(new ArrayList<>());
				clientForm.setRefrenceList(new ArrayList<>());
				clientForm.setDocuList(new ArrayList<>());
				clientForm.setSurgeryList(new ArrayList<>());
				clientForm.setSourceOfIntroList(new ArrayList<>());
				clientForm.setClientOccupationList(new ArrayList<>());
				clientForm.setCondtitionList(new ArrayList<>());
				clientForm.setCountry("India");
				
				
					clientForm.setWhopay("Client");
					MasterDAO masterDAO=new JDBCMasterDAO(connection);
					CompleteAptmDAO completeAptmDAO = new JDBCCompleteAptmDAO(connection);
					// department ot list
					ArrayList<Master> departmentList = masterDAO.getDisciplineDataListWithChecked();
					clientForm.setDepartmentList(departmentList);
//					ArrayList<DiaryManagement> userList = notAvailableSlotDAO.getUserList(loginInfo.getId());
//					clientForm.setUserList(userList);
					/*double registrationcharge = completeAptmDAO.getOpdRegCharge();
					clientForm.setRegistrationcharge(String.valueOf(registrationcharge));
					clientForm.setNetamount(String.valueOf(registrationcharge));*/
					ArrayList<Master> camploactionList= masterDAO.getAllCampLocation();
					clientForm.setCamplocationList(camploactionList);				
					double registrationcharge = 0.0;
					clientForm.setRegistrationcharge(String.valueOf(registrationcharge));
					clientForm.setNetamount(String.valueOf(registrationcharge));
					
					thirdPartyTypeList = clientDAO.getThirdPartyTypeNew("0");
					clientForm.setThirdPartyTypeList(thirdPartyTypeList);
					
					clientForm.setThirdPartyTypeNameList(thirdPartyTypeNameList);
					clientForm.setFromdate(DateTimeUtils.getCommencingDate1(date));
					
					ArrayList<Master> clienttypelist= masterDAO.getAllClientTypeList();
					clientForm.setClientTypeList(clienttypelist);
					
					
				
				
			}
			catch (Exception e) {
				e.printStackTrace();
			}
			finally{
				connection.close();
			}
			return "addnewpatientforafitech";
		}
	 
	 public String addnewclienttype() throws Exception {
			Connection connection = null;
			try {
				connection = Connection_provider.getconnection();
				MasterDAO masterDAO = new JDBCMasterDAO(connection);
				StringBuffer stringBuffer = new StringBuffer();
				// String addprocedurename =
				// request.getParameter("addprocedurename");
				// String addproceduredescription =
				// request.getParameter("addproceduredescription");

				StringBuilder buffer = new StringBuilder();
				BufferedReader reader = request.getReader();
				String line;
				while ((line = reader.readLine()) != null) {
					buffer.append(line);
				}
				String data = buffer.toString();
				JSONObject jsonObject = new JSONObject(data);
				String clienttype = jsonObject.getString("clienttype");
			
				Master master = new Master();

				master.setClientType(clienttype);
				
				int res = masterDAO.saveclientType(master);

				Response.setContentType("application/json");
				Response.setHeader("Cache-Control", "no-cache");
				Response.getWriter().write("1");
			} catch (Exception e) {
				e.printStackTrace();
			} finally {

				connection.close();
			}
			return null;
		}
	 public String clientType() throws Exception {

			Connection connection = null;
			try {

				//connection = Connection_provider.getconnection();
				connection = DriverManager.getConnection(
						"" + Constants.DB_HOST + ":3306/" + loginInfo.getClinicUserid()
								+ "?useUnicode=true&characterEncoding=UTF-8",
						"" + Constants.DB_USER + "", "" + Constants.DB_PWD + "");
				request.setCharacterEncoding("UTF-8");
				Response.setCharacterEncoding("UTF-8");
				IpdDAO ipdDAO = new JDBCIpdDAO(connection);
				NotAvailableSlotDAO notAvailableSlotDAO = new JDBCNotAvailableSlotDAO(connection);
				MasterDAO masterDAO=new JDBCMasterDAO(connection);
				String id = request.getParameter("id");
				String action = request.getParameter("action");
				ArrayList<Master> clientTypeList= masterDAO.getAllClientTypeList();
						

				StringBuffer str = new StringBuffer();
	              
				str.append(
						"<select  name='clientType' id='clientType' class='form-control showToolTip chosen' >");
	              
				str.append("<option value='0'>Select Client Type</option>");
				for (Master master : clientTypeList) {
					//   06-jan-2018
					//   25 jan 2018
					str.append("<option value='" + master.getId() + "'>" + master.getClientType() + "</option>");
					// str.append("<option
					// value='"+master.getName()+"'>"+master.getName()+"</option>");
				}
				str.append("</select>");

				/*
				 * str.
				 * append("<label id='apmtTypeError' class='text-danger'></label>");
				 * str.append("<div class='col-lg-12 col-md-12'>"); str.
				 * append("<label id='apmtTypeDuration' class='text-danger durane'></label>"
				 * ); str.append("</div>");
				 */

				Response.setContentType("text/html");
				Response.setHeader("Cache-Control", "no-cache");
				Response.getWriter().write("" + str.toString() + "");

			} catch (Exception e) {
				e.printStackTrace();
			}

			return null;
		}
}
