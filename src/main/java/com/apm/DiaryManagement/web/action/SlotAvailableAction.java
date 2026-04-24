package com.apm.DiaryManagement.web.action;

import java.io.BufferedReader;
import java.sql.Connection;
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

import com.apm.Accounts.eu.bi.AccountsDAO;
import com.apm.Accounts.eu.blogic.jdbc.JDBCAccountsDAO;
import com.apm.Appointment.eu.bi.AppointmentDAO;
import com.apm.Appointment.eu.blogic.jdbc.JDBCAppointmentDAO;
import com.apm.Appointment.eu.entity.AppointmentType;
import com.apm.Diagnosis.eu.entity.Diagnosis;
import com.apm.DiaryManagement.eu.bi.ClientDAO;
import com.apm.DiaryManagement.eu.bi.NotAvailableSlotDAO;
import com.apm.DiaryManagement.eu.blogic.jdbc.JDBCClientDAO;
import com.apm.DiaryManagement.eu.blogic.jdbc.JDBCNotAvailableSlotDAO;
import com.apm.DiaryManagement.eu.entity.Client;
import com.apm.DiaryManagement.eu.entity.DiaryManagement;
import com.apm.DiaryManagement.eu.entity.Priscription;
import com.apm.DiaryManagement.web.form.NotAvailableSlotForm;
import com.apm.Emr.eu.bi.EmrDAO;
import com.apm.Emr.eu.bi.InvestigationDAO;
import com.apm.Emr.eu.blogic.jdbc.JDBCEmrDAO;
import com.apm.Emr.eu.blogic.jdbc.JDBCInvestigationDAO;
import com.apm.Inventory.eu.bi.InventoryVendorDAO;
import com.apm.Inventory.eu.blogic.jdbc.JDBCInventoryVendorDAO;
import com.apm.Ipd.eu.bi.BedDao;
import com.apm.Ipd.eu.blogic.jdbc.JDBCBedDao;
import com.apm.Ipd.eu.entity.Bed;
import com.apm.Log.eu.bi.LogDAO;
import com.apm.Log.eu.blogic.jdbc.JDBCLogDAO;
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
import com.apm.common.eu.blogic.jdbc.Connection_provider;
import com.apm.common.utils.DateTimeUtils;
import com.apm.common.utils.Pagination;
import com.apm.common.web.action.BaseAction;
import com.apm.common.web.action.LoginAction;
import com.apm.common.web.common.helper.LoginHelper;
import com.apm.common.web.common.helper.LoginInfo;
import com.apm.common.web.utils.PopulateList;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ModelDriven;

import atg.taglib.json.util.JSONObject;

public class SlotAvailableAction extends BaseAction implements ModelDriven<NotAvailableSlotForm> {

	private static final Logger log = Logger.getLogger(LoginAction.class);

	HttpServletRequest request = (HttpServletRequest) ActionContext.getContext().get(ServletActionContext.HTTP_REQUEST);
	HttpServletResponse response = (HttpServletResponse) ActionContext.getContext().get(ServletActionContext.HTTP_RESPONSE);
	HttpSession session = request.getSession(true);
	
	NotAvailableSlotForm notAvailableSlotForm = new NotAvailableSlotForm();
	String req_datetime="";
	
	private Pagination pagination = new Pagination(5, 1);
	public Pagination getPagination() {
		return pagination;
	}
	public void setPagination(Pagination pagination) {
		this.pagination = pagination;
	}

	LoginInfo loginInfo = LoginHelper.getLoginInfo(request);
	
	public NotAvailableSlotForm getModel() {
		return notAvailableSlotForm;
	}
	
	
	// get calander date
		public String cal() throws SQLException {

			if (!verifyLogin(request)) {
				return "login";
			}

			String opendb = (String) session.getAttribute("openedb");
			if (opendb.equals("staff")) {
				session.setAttribute("openedb", "staff");
			} else if (opendb.equals("otdb")) {
				session.setAttribute("openedb", "otdb");
			} else {
				session.setAttribute("openedb", "opd");
			}
			int reqres=0;
			String actionType = request.getParameter("actionType");
			notAvailableSlotForm.setCommencing(notAvailableSlotForm.getCaldate());
			if (notAvailableSlotForm.getCaldate().equals("")) {
				
				String date = DateTimeUtils.getDashboardTodayDate(loginInfo.getTimeZone());
				notAvailableSlotForm.setCommencing(date);
			}
			Connection connection = null;
			try {

				connection = Connection_provider.getconnection();
				
				//Save request Time of Module
				LogDAO logDAO=new JDBCLogDAO(connection);
				
				reqres=logDAO.saveresponsetime("OPD",req_datetime);
				//End Save request Time of Module
				
				
				BedDao bedDao = new JDBCBedDao(connection);
				NotAvailableSlotDAO notAvailableSlotDAO = new JDBCNotAvailableSlotDAO(connection);
				ArrayList<Bed> wardlist = bedDao.getAllWardList("0");
				notAvailableSlotForm.setWardlist(wardlist);

				if (actionType.equals("dashboard")) {

					session.setAttribute("pgview", "dashboard");

					pagination = (Pagination) session.getAttribute("pagination");
					int totalcount = notAvailableSlotDAO.getUserTotalCount(opendb,
							notAvailableSlotForm.getSelectedjobgroup());

					pagination.setPreperties(totalcount);

					ArrayList<DiaryManagement> userList = notAvailableSlotDAO.getDashBoardUserList(loginInfo.getId(),
							pagination, notAvailableSlotForm.getCommencing(), opendb,
							notAvailableSlotForm.getSelectedjobgroup());
					pagination.setPage_records(userList.size());

					notAvailableSlotForm.setUserList(userList);

					session.setAttribute("userListSize", userList.size());
					session.setAttribute("allDiaryUser", userList);
					session.setAttribute("sessioncommencing", notAvailableSlotForm.getCommencing());

					notAvailableSlotForm.setLocationid(notAvailableSlotForm.getSelectedLocation());

					notAvailableSlotForm.setTotalRecords(totalcount);
					notAvailableSlotForm.setPagerecords(Integer.toString(pagination.getPage_records()));

					notAvailableSlotForm.setJobgroup(notAvailableSlotForm.getSelectedjobgroup());

					if (opendb.equals("staff")) {
						return "staffdb";
					} else if (opendb.equals("otdb")) {
						return "otdb";
					} else {
						session.setAttribute("openedb", "opd");
					}

					return "allUser";
				}

				if (actionType.equals("display")) {
					
					ArrayList<DiaryManagement> userList = notAvailableSlotDAO.getUserListwithdept(loginInfo.getId(), "");
					notAvailableSlotForm.setUserList(userList);
					
					String drids = "";
					ArrayList<DiaryManagement> list = notAvailableSlotDAO.getIdDashBoardUserList(loginInfo.getId(),
							pagination, notAvailableSlotForm.getCommencing(), opendb,
							notAvailableSlotForm.getSelectedjobgroup());
					for (DiaryManagement diaryManagement : userList) {
						if (drids.equals("")) {
							drids = "" + diaryManagement.getId();
						} else {
							drids = drids + "," + String.valueOf(diaryManagement.getId());
						}
					}
					loginInfo.setTokenstatus(1);
					session.setAttribute("drids", drids);
					return "display";
				}

				if (actionType.equals("week")) {

					String selecteduserid = request.getParameter("selecteduserid");
					ArrayList<DiaryManagement> userList = notAvailableSlotDAO.getUserList(loginInfo.getId());
					notAvailableSlotForm.setUserList(userList);
					notAvailableSlotForm.setDiaryUser(selecteduserid);
					return SUCCESS;
				}

				if (actionType.equals("mob")) {
					String selecteduserid = request.getParameter("selecteduserid");
					String uhid = notAvailableSlotForm.getOriuhid();
					if (!DateTimeUtils.isNull(uhid).equals("")) {
						session.setAttribute("mobuseruid", DateTimeUtils.numberCheck(uhid));
					}
					String loc = request.getParameter("loc");
					String commencing = "";
					if (!loginInfo.getCommencing().equals("")) {
						commencing = loginInfo.getCommencing();
					} else {
						commencing = notAvailableSlotForm.getCommencing();
					}
					if (!loginInfo.getDiaryUserid().equals("")) {

						selecteduserid = loginInfo.getDiaryUserid();
						loc = loginInfo.getLoc();
					}
					if (notAvailableSlotForm.getDiaryUser() != null) {
						selecteduserid = notAvailableSlotForm.getDiaryUser();
						loc = notAvailableSlotForm.getLocation();
					}
					if (!notAvailableSlotForm.getCaldate().equals("")) {
						commencing = notAvailableSlotForm.getCaldate();

					}
					if (loginInfo.getUserType() == 5) {
						notAvailableSlotForm.setDuration("1");
					}
					notAvailableSlotForm.setCommencing(commencing);
					String dept = "";
					ArrayList<DiaryManagement> userList = notAvailableSlotDAO.getUserList(loginInfo.getId());
					if (loginInfo.getUserType() == 5) {
						dept = (String) session.getAttribute("selecteddept");
						
						if (!DateTimeUtils.isNull(selecteduserid).equals("")) {
							userList = notAvailableSlotDAO.getUserList(loginInfo.getId());
						} else {
							userList.removeAll(userList);
						}
					} else {
						userList = notAvailableSlotDAO.getUserList(loginInfo.getId());
					}
					notAvailableSlotForm.setUserList(userList);
					notAvailableSlotForm.setDiaryUser(selecteduserid);
					notAvailableSlotForm.setLocationid(loc);

					String temp[] = notAvailableSlotForm.getCommencing().split("/");

					int wyear = Integer.parseInt(temp[2]);
					int month = Integer.parseInt(temp[1]);
					int day = Integer.parseInt(temp[0]);

					String cweekName = DateTimeUtils.getWeekName(wyear, month, day);
					notAvailableSlotForm.setDayWeekName(cweekName);
					if(loginInfo.getUserType()==5){
						notAvailableSlotForm.setCommencing("");
					}
					return "mobweek";
				}

				boolean isnewopd = false;
				if (actionType.equals("newopdday")) {
					actionType = "day";
					isnewopd = true;
				}

				if (actionType.equals("day")) {
					String selecteduserid = request.getParameter("selecteduserid");
					ArrayList<DiaryManagement> userList = notAvailableSlotDAO.getUserList(loginInfo.getId());
					notAvailableSlotForm.setUserList(userList);
					notAvailableSlotForm.setDiaryUser(notAvailableSlotForm.getDiaryUser());

					String temp[] = notAvailableSlotForm.getCommencing().split("/");

					int wyear = Integer.parseInt(temp[2]);
					int month = Integer.parseInt(temp[1]);
					int day = Integer.parseInt(temp[0]);

					String cweekName = DateTimeUtils.getWeekName(wyear, month, day);
					notAvailableSlotForm.setDayWeekName(cweekName);

					if (isnewopd) {
						return "newopd";
					}

					return "day";
				}

				if (actionType.equals("doctormob")) {
					String selecteduserid = Integer.toString(loginInfo.getId());
					String commencing = DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone()).split(" ")[0];
					ArrayList<DiaryManagement> userList = notAvailableSlotDAO.getUserList(loginInfo.getId());
					notAvailableSlotForm.setUserList(userList);
					notAvailableSlotForm.setDiaryUser(selecteduserid);

					String temp[] = commencing.split("-");

					int wyear = Integer.parseInt(temp[0]);
					int month = Integer.parseInt(temp[1]);
					int day = Integer.parseInt(temp[2]);

					String cweekName = DateTimeUtils.getWeekName(wyear, month, day);
					notAvailableSlotForm.setDayWeekName(cweekName);

					return "day";
				}

				if (actionType.equals("newopd")) {
					actionType = "doctorday";
					isnewopd = true;
				}
				if (actionType.equals("doctorday")) {
					String selecteduserid = request.getParameter("doctor");
					String logintimedrid = (String) session.getAttribute("logintimedrid");
					if (!DateTimeUtils.isNull(logintimedrid).equals("")) {
						selecteduserid = logintimedrid;
						session.removeAttribute("logintimedrid");
					}
					ArrayList<DiaryManagement> userList=new ArrayList<>();
					if(loginInfo.getClinicid1().equals("manasclinic") && loginInfo.getId()!=1) {
						userList = notAvailableSlotDAO.getManasClinicUserList(loginInfo);
					}else {
						userList = notAvailableSlotDAO.getUserList(loginInfo.getId());
					}
					
					//ArrayList<DiaryManagement> userList = notAvailableSlotDAO.getUserList(loginInfo.getId());
					notAvailableSlotForm.setUserList(userList);
					notAvailableSlotForm.setDiaryUser(selecteduserid);

					session.setAttribute("openedb", "opd");

					String temp[] = notAvailableSlotForm.getCommencing().split("/");

					int wyear = Integer.parseInt(temp[2]);
					int month = Integer.parseInt(temp[1]);
					int day = Integer.parseInt(temp[0]);

					String cweekName = DateTimeUtils.getWeekName(wyear, month, day);
					notAvailableSlotForm.setDayWeekName(cweekName);
					
					//Update response Time of Module
					String	requestid=""+reqres;
					SimpleDateFormat formatter1 = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");  
				    Date date1 = new Date();
				    String response_datetime=formatter1.format(date1);
				    String duration_of_time=DateTimeUtils.findTimeBTDateTime(req_datetime, response_datetime);
					int upres=logDAO.updateresponsetime("OPD",response_datetime,requestid,duration_of_time );
					
					MasterDAO masterDAO = new JDBCMasterDAO(connection);
					ClientDAO clientDAO = new JDBCClientDAO(connection);
					InventoryVendorDAO vendorDAO = new JDBCInventoryVendorDAO(connection);
					AccountsDAO accountsDAO = new JDBCAccountsDAO(connection);
					AppointmentDAO appointmentDAO = new JDBCAppointmentDAO(connection);
					EmrDAO emrDAO = new JDBCEmrDAO(connection);
					InvestigationDAO investigationDAO = new JDBCInvestigationDAO(connection);
					PharmacyDAO pharmacyDAO = new JDBCPharmacyDAO(connection);
					//UserProfileDAO userProfileDAO = new JDBCUserProfileDAO(connection);
					
					if(loginInfo.getUserType()==5 || loginInfo.isOpd_recep_sp_list()){
//						ArrayList<Master> disciplineList = masterDAO.getDisciplineDataList();
						ArrayList<Master> disciplineList = masterDAO.getDisciplineDataListWithChecked();
						notAvailableSlotForm.setDisciplineList(disciplineList);
					}else{
						notAvailableSlotForm.setDisciplineList(new ArrayList<>());
					}
					ArrayList<Location> locationList = notAvailableSlotDAO.getLocationList(loginInfo.getId());
					notAvailableSlotForm.setLocationList(locationList);
					//notAvailableSlotForm.setLocationList(new ArrayList<>());
					
					ArrayList<String> initialList = new ArrayList<String>();
					initialList = clientDAO.getInitialList();
					notAvailableSlotForm.setInitialList(initialList);
					
					ArrayList<Master> stateList = vendorDAO.getAllStateList();
					notAvailableSlotForm.setStatelist(stateList);
					ArrayList<Master> cityList = vendorDAO.getAllCityList();
					notAvailableSlotForm.setCitylist(cityList);
					
					Master master=clientDAO.getClinicCity();
					if(loginInfo.isMarkhosp()) {
					notAvailableSlotForm.setTown("Bilaspur");
					notAvailableSlotForm.setState("Chhattisgarh");
					}else {
						notAvailableSlotForm.setTown(master.getCity());
						notAvailableSlotForm.setState(master.getState());
						
					}
					//bookAppointment1.jsp
					//notAvailableSlotForm.setWeekNameList(new ArrayList<>());
					//notAvailableSlotForm.setStartTimeList(new ArrayList<>());
					//notAvailableSlotForm.setEndTimeList(new ArrayList<>());
					//notAvailableSlotForm.setApmtBlockDurationList(new ArrayList<>());
					notAvailableSlotForm.setOtdepartmentList(new ArrayList<>());
					notAvailableSlotForm.setProcedureList(new ArrayList<>());
					notAvailableSlotForm.setAppointmentTypeList(new ArrayList<>());
					notAvailableSlotForm.setSurgeonlist(new ArrayList<>());
					notAvailableSlotForm.setAnesthesiaList(new ArrayList<>());
					//notAvailableSlotForm.setDisciplineList(new ArrayList<>());
					//notAvailableSlotForm.setLocationList(new ArrayList<>());
					//notAvailableSlotForm.setCondtitionList(new ArrayList<>());
					//notAvailableSlotForm.setDisciplineList(new ArrayList<>());
					//notAvailableSlotForm.setWeekNameList(new ArrayList<>());
					
					//takepayment.jsp
					//notAvailableSlotForm.setInvoiceTypeLis(new ArrayList<>());
					//notAvailableSlotForm.setBankNameList(new ArrayList<>());
					
					//addPatientPage.jsp
					//notAvailableSlotForm.setHourList(new ArrayList<>());
					//notAvailableSlotForm.setMinuteList(new ArrayList<>());
					//notAvailableSlotForm.setBirthPlaceList(new ArrayList<>());
					//notAvailableSlotForm.setCitylist(new ArrayList<>());
					//notAvailableSlotForm.setStatelist(new ArrayList<>());
					//notAvailableSlotForm.setCountryList(new ArrayList<>());
					//notAvailableSlotForm.setRefrenceList(new ArrayList<>());
					notAvailableSlotForm.setDiagnosisList(new ArrayList<>());
					notAvailableSlotForm.setClientOccupationList(new ArrayList<>());
					//notAvailableSlotForm.setSurgeryList(new ArrayList<>());
					//notAvailableSlotForm.setThirdPartyTypeList(new ArrayList<>());
					
					//addNewDoctSurgery.jsp
					//notAvailableSlotForm.setThirdPartyTypeList(new ArrayList<>());
					//notAvailableSlotForm.setCountryList(new ArrayList<>());
					
					//newcommonpopup.jsp
					notAvailableSlotForm.setSchedulerlist(new ArrayList<>());
					//notAvailableSlotForm.setLocationList(new ArrayList<>());
					//notAvailableSlotForm.setWardlist(new ArrayList<>());
					//notAvailableSlotForm.setMedicineList(new ArrayList<>());
					//notAvailableSlotForm.setDisciplineList(new ArrayList<>());
					//notAvailableSlotForm.setLocationList(new ArrayList<>());
					//notAvailableSlotForm.setApmtBlockDurationList(new ArrayList<>());
					//notAvailableSlotForm.setStartTimeList(new ArrayList<>());
					//notAvailableSlotForm.setEndTimeList(new ArrayList<>());
					
					//addpriscription.jsp
					//notAvailableSlotForm.setMedicineList(new ArrayList<>());
					notAvailableSlotForm.setSpecializationTemplateList(new ArrayList<>());
					notAvailableSlotForm.setMdicinecategoryList(new ArrayList<>());
					notAvailableSlotForm.setMdicneTypeList(new ArrayList<>());
					notAvailableSlotForm.setDosageList(new ArrayList<>());
					//notAvailableSlotForm.setRequestlocationlist(new ArrayList<>());
					notAvailableSlotForm.setNimairemarklist(new ArrayList<>());
					notAvailableSlotForm.setPriscUnitList(new ArrayList<>());
					notAvailableSlotForm.setNimaidoselist(new ArrayList<>());
					notAvailableSlotForm.setNimaiqtylist(new ArrayList<>());
					notAvailableSlotForm.setDosagenoteList(new ArrayList<>());
					notAvailableSlotForm.setMedicinetimelist(new ArrayList<>());
					notAvailableSlotForm.setDosageList(new ArrayList<>());
					notAvailableSlotForm.setParentPriscList(new ArrayList<>());
					//notAvailableSlotForm.setTemplateNameList(new ArrayList<>());
					notAvailableSlotForm.setMedicineList(new ArrayList<>());
					
					ArrayList<Diagnosis>diagnosisList = masterDAO.getDiagnosisList();
					notAvailableSlotForm.setDiagnosislist(diagnosisList);
					
					notAvailableSlotForm.setSpecializationTemplateList(new ArrayList<>());
					notAvailableSlotForm.setMdicinecategoryList(new ArrayList<>());
					
					//addInvestigation.jsp
					notAvailableSlotForm.setJobTitleList(new ArrayList<>());
					//notAvailableSlotForm.setPkgsList(new ArrayList<>());
					notAvailableSlotForm.setInvSectionList(new ArrayList<>());
					//notAvailableSlotForm.setInvsTypeList(new ArrayList<>());
					notAvailableSlotForm.setInvstUnitList(new ArrayList<>());
					
					//invaddcharge.jsp
					//notAvailableSlotForm.setLocationList(new ArrayList<>());
					//notAvailableSlotForm.setMasterChageTypeList(new ArrayList<>());
					
					//completeApmt.jsp
					//notAvailableSlotForm.setMasterChageTypeList(new ArrayList<>());
					//notAvailableSlotForm.setLocationList(new ArrayList<>());
					
					//addNewGp.jsp
					notAvailableSlotForm.setSurgeryList(new ArrayList<>());
					
					//addNewTp.jsp
					//notAvailableSlotForm.setThirdPartyTypeList(new ArrayList<>());
					notAvailableSlotForm.setTpnameList(new ArrayList<>());
					//notAvailableSlotForm.setCountryList(new ArrayList<>());
					//notAvailableSlotForm.setApmtDurationList(new ArrayList<>());
					
					//unknown
					notAvailableSlotForm.setAdditionalChargeList(new ArrayList<>());
					
					
//					/set important list
					notAvailableSlotForm.setCountryList(PopulateList.countryList());
					notAvailableSlotForm.setCountry("India");
					
					ArrayList<String> weekNameList = new ArrayList<String>();
					weekNameList.add("Monday");
					weekNameList.add("Tuesday");
					weekNameList.add("Wednesday");
					weekNameList.add("Thursday");
					weekNameList.add("Friday");
					weekNameList.add("Saturday");
					weekNameList.add("Sunday");
					notAvailableSlotForm.setWeekNameList(weekNameList);
					
					notAvailableSlotForm.setStartTimeList(PopulateList.startTimeList());
					notAvailableSlotForm.setEndTimeList(PopulateList.endTimeList());
					notAvailableSlotForm.setApmtDurationList(PopulateList.apmtDurationList());
					notAvailableSlotForm.setApmtBlockDurationList(PopulateList.apmBlocktDurationList());
				
					ArrayList<Master> invoiceTypeList = accountsDAO.getInvoiceTypeList();
					notAvailableSlotForm.setInvoiceTypeLis(invoiceTypeList);
					notAvailableSlotForm.setInvcetype("1");
					
					ArrayList<Master> bankNameList = masterDAO.getBankNameList();
					notAvailableSlotForm.setBankNameList(bankNameList);
					ArrayList<Client> thirdPartyTypeList = clientDAO.getThirdPartyType();
					notAvailableSlotForm.setThirdPartyTypeList(thirdPartyTypeList);
					
					ArrayList<Master> masterChageTypeList = appointmentDAO.getmasterChageTypeList(loginInfo);
					notAvailableSlotForm.setMasterChageTypeList(masterChageTypeList);
					notAvailableSlotForm.setMasterchargetype("Appointment Charge");
					
					notAvailableSlotForm.setHourList(PopulateList.hourList());
					notAvailableSlotForm.setMinuteList(PopulateList.getMinuteList());
					notAvailableSlotForm.setBirthPlaceList(masterDAO.getBirthPlaceList());
					
					ArrayList<Priscription> templateNameList = emrDAO.getTemplateNameList(loginInfo);
					notAvailableSlotForm.setTemplateNameList(templateNameList);
					
					/*ArrayList<String> jobTitleList = userProfileDAO.getJobTitleList();
					notAvailableSlotForm.setJobTitleList(jobTitleList);*/
					ArrayList<Priscription> oteqtemplateNameList = emrDAO.getoteqTemplateNameList();
					notAvailableSlotForm.setOteqtemplateNameList(oteqtemplateNameList);

					ArrayList<Master> assetList = emrDAO.getOtAssetList();
					notAvailableSlotForm.setAssetList(assetList);
					notAvailableSlotForm.setCondtitionList(clientDAO.getTreatmentTypeList());
					
					ArrayList<Master> pkgsList = investigationDAO.getInvPaksLists();
					notAvailableSlotForm.setPkgsList(pkgsList);
					
					ArrayList<Master> invsTypeList = emrDAO.getInvesigationTypeList();
					notAvailableSlotForm.setInvsTypeList(invsTypeList);
					
					ArrayList<Master> requestlocationlist = pharmacyDAO.getAllLocationNew();
					notAvailableSlotForm.setRequestlocationlist(requestlocationlist);
					
					if (loginInfo.isPrisc_location_list()) {
						int default_location = pharmacyDAO.getByDefaultPharmacyLocation();
						notAvailableSlotForm.setRequestlocationid("" + default_location);
					}
					
					ArrayList<Master> disciplineList = masterDAO.getDisciplineDataList();
					notAvailableSlotForm.setDisciplineList(disciplineList);
					
					ArrayList<Master> procedureList = notAvailableSlotDAO.getProcedureList(""+4);
					notAvailableSlotForm.setProcedure_List(procedureList);

					ArrayList<Client> refrenceList = clientDAO.getReferenceList();
					Client client2 = new Client();
					client2.getOther();
					refrenceList.add(client2);
					notAvailableSlotForm.setRefrenceList(refrenceList);
					
					ArrayList<Master> priscUnitList = masterDAO.getPriscUnitList();
					notAvailableSlotForm.setPriscUnitList(priscUnitList);
					
					
					String fromDate=notAvailableSlotForm.getFromDate();
			    	
			    	if (fromDate == null) {
						DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
						Calendar cal = Calendar.getInstance();
						// cal.add(Calendar.DATE, -7);
						fromDate = dateFormat.format(cal.getTime());
						fromDate = DateTimeUtils.getCommencingDate1(fromDate);
					} else {
						if (fromDate.equals("")) {
							DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
							Calendar cal = Calendar.getInstance();
							// cal.add(Calendar.DATE, -7);
							fromDate = dateFormat.format(cal.getTime());
							fromDate = DateTimeUtils.getCommencingDate1(fromDate);
						} else {
							fromDate = DateTimeUtils.getCommencingDate1(fromDate);
						}
					}
			    	notAvailableSlotForm.setFromDate(DateTimeUtils.getCommencingDate1(fromDate));
					
					
					
					
					
					
					
					//End Update response Time of Module
					if (isnewopd) {
						return "newopd";
					}
					return "day";
				}

			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				connection.close();
			}

			return null;
		}
		
		public String newimmunizationchart() {
			
			try {
				
				String clientid = request.getParameter("clientId");
				String type = DateTimeUtils.isNull(request.getParameter("type"));

				immunizationProcess(clientid, type,false);

			} catch (Exception e) {
				e.printStackTrace();
			}

			return "newimmunizationchart";
		}
		
		public void immunizationProcess(String clientid,String type,boolean fromVital){
			Connection connection = null;
			try {
				connection = Connection_provider.getconnection();
				ClientDAO clientDAO = new JDBCClientDAO(connection);
				NotAvailableSlotDAO notAvailableSlotDAO = new JDBCNotAvailableSlotDAO(connection);
				Client client = clientDAO.getPatient(Integer.parseInt(clientid));
				String changedob = notAvailableSlotDAO.getDOBChangeLogDate(clientid);
				boolean ischangedob = false;
				if (!DateTimeUtils.isNull(clientid).equals("")) {
					if (changedob.equals("")) {
						notAvailableSlotDAO.insertDobChangeLog(clientid, client.getDob());
					}
					changedob = notAvailableSlotDAO.getDOBChangeLogDate(clientid);
					if (!changedob.equals(client.getDob())) {
						ischangedob = true;
					}
				}
				if (ischangedob) {
					notAvailableSlotDAO.insertDobChangeLog(clientid, client.getDob());
				}
				if (type != null) {
					if (!type.equals("")) {
						client.setVacine_type(Integer.parseInt(type));
					}
				} else {
					client.setVacine_type(0);
				}
				notAvailableSlotForm.setVacine_type(client.getVacine_type());
				String lmpdate = "";
				ArrayList<Master> vacinlist = new ArrayList<Master>();
				vacinlist = notAvailableSlotDAO.getVaccinationdata(client);
				if (vacinlist.size() != 0) {

					for (Master master : vacinlist) {
						/*if (master == null) {
							continue;
						}*/

						master.getVacine_scheduledon();
						String dob = client.getDob();

						dob = DateTimeUtils.getCommencingDatePicker(dob);
						if (client.getVacine_type() == 1) {
							// dob is lmp date fr gynic
							dob = notAvailableSlotDAO.lmpDAte(clientid);
							lmpdate = dob;
							if(fromVital||DateTimeUtils.isNull(dob).equals("")){
								dob = notAvailableSlotDAO.lmpDateFromVital(clientid);
								lmpdate = dob;
							}
							
						} else if (client.getVacine_type() == 2) {
							DateFormat dateFormat2 = new SimpleDateFormat("dd-MM-yyyy");
							Calendar cal2 = Calendar.getInstance();
							dob = dateFormat2.format(cal2.getTime());
						}
						int schedule = Integer.parseInt(master.getVacine_scheduledon());
						SimpleDateFormat birthDate = new SimpleDateFormat("dd-MM-yyyy");
						Date date = birthDate.parse(dob);
						Calendar cal = Calendar.getInstance();
						cal.setTime(date);
						String scheduledate = "";
						cal.add(Calendar.DAY_OF_YEAR, schedule);
						scheduledate = birthDate.format(cal.getTime());
						/*
						 * if(cal.get(Calendar.DAY_OF_WEEK)==Calendar.SUNDAY){
						 * cal.add(Calendar.DAY_OF_YEAR,1);
						 * scheduledate=birthDate.format(cal.getTime()); }
						 */ master.setScheduledate(scheduledate);

						int depndvalue = 0;
						if (master.getVacine_dependson() != null) {
							if (!master.getVacine_dependson().equals("")) {
								depndvalue = notAvailableSlotDAO.getvacinedependacyvale(master.getVacine_dependson());
							}
						}

						/*
						 * if(master.getVacine_dependson()!=null){
						 * if(!master.getVacine_dependson().equals("")){ Master
						 * mstr=
						 * vacinlist.get(Integer.parseInt(master.getVacine_dependson
						 * ())-1); String newdate= mstr.getScheduledate();
						 * SimpleDateFormat birthDate2=new
						 * SimpleDateFormat("dd-MM-yyyy"); Date date2=
						 * birthDate2.parse(newdate); Calendar cal2=
						 * Calendar.getInstance(); cal2.setTime(date2); String
						 * scheduledate2=""; cal2.add(Calendar.DAY_OF_YEAR,
						 * schedule); scheduledate2=
						 * birthDate2.format(cal2.getTime());
						 * master.setScheduledate(scheduledate2);
						 * if(mstr.getVacine_givendate()==null){
						 * 
						 * } } }
						 */
						/*
						 * Calendar cal = Calendar.getInstance();
						 * cal.add(Calendar.DATE, schedule+depndvalue); String
						 * scheduledate=""; scheduledate =
						 * birthDate.format(cal.getTime());
						 * master.setScheduledate(scheduledate);
						 */
						SimpleDateFormat checkdate = new SimpleDateFormat("dd-MM-yyyy");

						String exclude = DateTimeUtils.isNull(master.getVacine_excludes());
						for (int i = 0; i < exclude.length(); i++) {
							Date chkdateobj = checkdate.parse(master.getScheduledate());
							Calendar chkdatecal = Calendar.getInstance();
							chkdatecal.setTime(chkdateobj);
							char day = (exclude.charAt(i));
							switch (day) {
							case '1':
								if (chkdatecal.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY) {
									chkdatecal.add(Calendar.DAY_OF_YEAR, 1);
									master.setScheduledate(checkdate.format(chkdatecal.getTime()));
								}
								break;
							case '2':
								if (chkdatecal.get(Calendar.DAY_OF_WEEK) == Calendar.MONDAY) {
									chkdatecal.add(Calendar.DAY_OF_YEAR, 1);
									master.setScheduledate(checkdate.format(chkdatecal.getTime()));
								}
								break;
							case '3':
								if (chkdatecal.get(Calendar.DAY_OF_WEEK) == Calendar.TUESDAY) {
									chkdatecal.add(Calendar.DAY_OF_YEAR, 1);
									master.setScheduledate(checkdate.format(chkdatecal.getTime()));
								}
								break;
							case '4':
								if (chkdatecal.get(Calendar.DAY_OF_WEEK) == Calendar.WEDNESDAY) {
									chkdatecal.add(Calendar.DAY_OF_YEAR, 1);
									master.setScheduledate(checkdate.format(chkdatecal.getTime()));
								}
								break;
							case '5':
								if (chkdatecal.get(Calendar.DAY_OF_WEEK) == Calendar.THURSDAY) {
									chkdatecal.add(Calendar.DAY_OF_YEAR, 1);
									master.setScheduledate(checkdate.format(chkdatecal.getTime()));
								}
								break;
							case '6':
								if (chkdatecal.get(Calendar.DAY_OF_WEEK) == Calendar.FRIDAY) {
									chkdatecal.add(Calendar.DAY_OF_YEAR, 1);
									master.setScheduledate(checkdate.format(chkdatecal.getTime()));
								}
								break;
							case '7':
								if (chkdatecal.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY) {
									chkdatecal.add(Calendar.DAY_OF_YEAR, 1);
									master.setScheduledate(checkdate.format(chkdatecal.getTime()));
								}
								break;

							default:
								break;
							}
						}

						int checkexist = notAvailableSlotDAO
								.checkvacinationimmunizationajaxData(String.valueOf(master.getId()), clientid);
						if (checkexist == 0) {
							// if in means record does not exist
							int x = notAvailableSlotDAO.setdatatoVacinationInfo(master, clientid);
							// inserts data
							DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
							Calendar cal3 = Calendar.getInstance();
							cal3.add(Calendar.DATE, 1);
							String tomarrow = dateFormat.format(cal3.getTime());
							if (master.getScheduledate().equals(tomarrow)) {
								// if tomarrow is vacinedate
								boolean smssent = notAvailableSlotDAO.checksmsflag(master.getId(), clientid);
								if (!smssent) {
									/*
									 * int w=
									 * notAvailableSlotDAO.setsmsflag(master.getId()
									 * , clientid);
									 */
								}
							}

						} else {
							if (master.getDependson() > 0) {
								String depenondate = DateTimeUtils
										.isNull(notAvailableSlotDAO.getGivenDate(master.getDependson(), clientid));
								int scheduledep = Integer.parseInt(master.getVacine_scheduledon());
								
								//Shouldnt change the date if given date s there 
								String currentsGivenDate = DateTimeUtils
										.isNull(notAvailableSlotDAO.getGivenDate(master.getId(), clientid));
								if(currentsGivenDate.equals("")){
									if (depenondate.contains("-")) {
										SimpleDateFormat depndongivendatedate = new SimpleDateFormat("yyyy-MM-dd");
										Date dependdate = depndongivendatedate.parse(depenondate);
										Calendar calnew = Calendar.getInstance();
										calnew.setTime(dependdate);
										String dependate = "";

										calnew.add(Calendar.DAY_OF_YEAR, master.getDeppendsonDays());
										dependate = depndongivendatedate.format(calnew.getTime());
										/*
										 * if(calnew.get(Calendar.DAY_OF_WEEK)==Calendar
										 * .SUNDAY){ calnew.add(Calendar.DAY_OF_YEAR,1);
										 * dependate=depndongivendatedate.format(calnew.
										 * getTime()); }
										 */
										master.setScheduledate(DateTimeUtils.getCommencingDate1(dependate));
										notAvailableSlotDAO.updatedueDateOfVaccine(clientid, "" + master.getId(), dependate);
									}
								}
							
							}

							if (ischangedob) {
								int dobchngsuccess = notAvailableSlotDAO.updatedueDateOfVaccine(clientid,
										"" + master.getId(), DateTimeUtils.getCommencingDate1(master.getScheduledate()));
							}
						}

						String savedauedate = notAvailableSlotDAO.getDueDate("" + master.getId(), clientid);
						if (!DateTimeUtils.isNull(savedauedate).equals("")) {
							master.setScheduledate(savedauedate);

						}
						master.setVacine_period(DateTimeUtils.getAge1onAddmissionimmu(dob, master.getScheduledate()));

					}
				}
				ArrayList<Master> list1 = new ArrayList<Master>();
				for (Master master : vacinlist) {
					if (master != null) {

						list1.add(master);
					}
				}
				type = DateTimeUtils.numberCheck(type);

				notAvailableSlotForm.setVacine_type(Integer.parseInt(type));
				notAvailableSlotForm.setClientId(clientid);
				notAvailableSlotForm.setAbrivationid(client.getAbrivationid());
				notAvailableSlotForm.setFullname(client.getFullname());
				notAvailableSlotForm.setVacinlist(list1);
				notAvailableSlotForm.setDob(client.getDob());
				notAvailableSlotForm.setLmpdate(lmpdate);
				// clinic header
				AccountsDAO accountsDAO = new JDBCAccountsDAO(connection);
				ArrayList<Clinic> locationAdressList = accountsDAO.getLocationAddress(loginInfo.getClinicid());
				ClinicDAO clinicDAO = new JDBCClinicDAO(connection);
				Clinic clinic = new Clinic();
				clinic = clinicDAO.getCliniclistDetails(loginInfo.getClinicid());
				notAvailableSlotForm.setClinicName(clinic.getClinicName());
				notAvailableSlotForm.setClinicOwner(clinic.getClinicOwner());
				notAvailableSlotForm.setOwner_qualification(clinic.getOwner_qualification());
				notAvailableSlotForm.setLocationAdressList(locationAdressList);
				notAvailableSlotForm.setAddress(clinic.getAddress());
				notAvailableSlotForm.setLandLine(clinic.getLandLine());
				notAvailableSlotForm.setClinicemail(clinic.getEmail());
				notAvailableSlotForm.setWebsiteUrl(clinic.getWebsiteUrl());
				notAvailableSlotForm.setClinicLogo(clinic.getUserImageFileName());
				notAvailableSlotForm.setBookedstatus((notAvailableSlotDAO.bookededStatsu(clientid)));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		
          public String newdewormingchart() {
			
			try {
				
				String clientid = request.getParameter("clientId");
				String type = DateTimeUtils.isNull(request.getParameter("type"));

				dewormingProcess(clientid, type,false);

			} catch (Exception e) {
				e.printStackTrace();
			}

			return "newdewormingchart";
		}
		
		private void dewormingProcess(String clientid, String type, boolean fromVital) {

			Connection connection = null;
			try {
				connection = Connection_provider.getconnection();
				ClientDAO clientDAO = new JDBCClientDAO(connection);
				NotAvailableSlotDAO notAvailableSlotDAO = new JDBCNotAvailableSlotDAO(connection);
				Client client = clientDAO.getPatient(Integer.parseInt(clientid));
				String changedob = notAvailableSlotDAO.getDOBChangeLogDate(clientid);
				boolean ischangedob = false;
				if (!DateTimeUtils.isNull(clientid).equals("")) {
					if (changedob.equals("")) {
						notAvailableSlotDAO.insertDobChangeLog(clientid, client.getDob());
					}
					changedob = notAvailableSlotDAO.getDOBChangeLogDate(clientid);
					if (!changedob.equals(client.getDob())) {
						ischangedob = true;
					}
				}
				if (ischangedob) {
					notAvailableSlotDAO.insertDobChangeLog(clientid, client.getDob());
				}
				if (type != null) {
					if (!type.equals("")) {
						client.setVacine_type(Integer.parseInt(type));
					}
				} else {
					client.setVacine_type(0);
				}
				notAvailableSlotForm.setVacine_type(client.getVacine_type());
				String lmpdate = "";
				ArrayList<Master> vacinlist = new ArrayList<Master>();
				vacinlist = notAvailableSlotDAO.getVaccinationdataveterinary(client);
				if (vacinlist.size() != 0) {

					for (Master master : vacinlist) {
						/*if (master == null) {
							continue;
						}*/

						master.getVacine_scheduledon();
						String dob = client.getDob();

						dob = DateTimeUtils.getCommencingDatePicker(dob);
						if (client.getVacine_type() == 1) {
							// dob is lmp date fr gynic
							dob = notAvailableSlotDAO.lmpDAte(clientid);
							lmpdate = dob;
							if(fromVital||DateTimeUtils.isNull(dob).equals("")){
								dob = notAvailableSlotDAO.lmpDateFromVital(clientid);
								lmpdate = dob;
							}
							
						} else if (client.getVacine_type() == 2) {
							DateFormat dateFormat2 = new SimpleDateFormat("dd-MM-yyyy");
							Calendar cal2 = Calendar.getInstance();
							dob = dateFormat2.format(cal2.getTime());
						}
						int schedule = Integer.parseInt(master.getVacine_scheduledon());
						SimpleDateFormat birthDate = new SimpleDateFormat("dd-MM-yyyy");
						Date date = birthDate.parse(dob);
						Calendar cal = Calendar.getInstance();
						cal.setTime(date);
						String scheduledate = "";
						cal.add(Calendar.DAY_OF_YEAR, schedule);
						scheduledate = birthDate.format(cal.getTime());
						/*
						 * if(cal.get(Calendar.DAY_OF_WEEK)==Calendar.SUNDAY){
						 * cal.add(Calendar.DAY_OF_YEAR,1);
						 * scheduledate=birthDate.format(cal.getTime()); }
						 */ master.setScheduledate(scheduledate);

						int depndvalue = 0;
						if (master.getVacine_dependson() != null) {
							if (!master.getVacine_dependson().equals("")) {
								depndvalue = notAvailableSlotDAO.getvacinedependacyvaleveterinary(master.getVacine_dependson());
							}
						}

						/*
						 * if(master.getVacine_dependson()!=null){
						 * if(!master.getVacine_dependson().equals("")){ Master
						 * mstr=
						 * vacinlist.get(Integer.parseInt(master.getVacine_dependson
						 * ())-1); String newdate= mstr.getScheduledate();
						 * SimpleDateFormat birthDate2=new
						 * SimpleDateFormat("dd-MM-yyyy"); Date date2=
						 * birthDate2.parse(newdate); Calendar cal2=
						 * Calendar.getInstance(); cal2.setTime(date2); String
						 * scheduledate2=""; cal2.add(Calendar.DAY_OF_YEAR,
						 * schedule); scheduledate2=
						 * birthDate2.format(cal2.getTime());
						 * master.setScheduledate(scheduledate2);
						 * if(mstr.getVacine_givendate()==null){
						 * 
						 * } } }
						 */
						/*
						 * Calendar cal = Calendar.getInstance();
						 * cal.add(Calendar.DATE, schedule+depndvalue); String
						 * scheduledate=""; scheduledate =
						 * birthDate.format(cal.getTime());
						 * master.setScheduledate(scheduledate);
						 */
						SimpleDateFormat checkdate = new SimpleDateFormat("dd-MM-yyyy");

						String exclude = DateTimeUtils.isNull(master.getVacine_excludes());
						for (int i = 0; i < exclude.length(); i++) {
							Date chkdateobj = checkdate.parse(master.getScheduledate());
							Calendar chkdatecal = Calendar.getInstance();
							chkdatecal.setTime(chkdateobj);
							char day = (exclude.charAt(i));
							switch (day) {
							case '1':
								if (chkdatecal.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY) {
									chkdatecal.add(Calendar.DAY_OF_YEAR, 1);
									master.setScheduledate(checkdate.format(chkdatecal.getTime()));
								}
								break;
							case '2':
								if (chkdatecal.get(Calendar.DAY_OF_WEEK) == Calendar.MONDAY) {
									chkdatecal.add(Calendar.DAY_OF_YEAR, 1);
									master.setScheduledate(checkdate.format(chkdatecal.getTime()));
								}
								break;
							case '3':
								if (chkdatecal.get(Calendar.DAY_OF_WEEK) == Calendar.TUESDAY) {
									chkdatecal.add(Calendar.DAY_OF_YEAR, 1);
									master.setScheduledate(checkdate.format(chkdatecal.getTime()));
								}
								break;
							case '4':
								if (chkdatecal.get(Calendar.DAY_OF_WEEK) == Calendar.WEDNESDAY) {
									chkdatecal.add(Calendar.DAY_OF_YEAR, 1);
									master.setScheduledate(checkdate.format(chkdatecal.getTime()));
								}
								break;
							case '5':
								if (chkdatecal.get(Calendar.DAY_OF_WEEK) == Calendar.THURSDAY) {
									chkdatecal.add(Calendar.DAY_OF_YEAR, 1);
									master.setScheduledate(checkdate.format(chkdatecal.getTime()));
								}
								break;
							case '6':
								if (chkdatecal.get(Calendar.DAY_OF_WEEK) == Calendar.FRIDAY) {
									chkdatecal.add(Calendar.DAY_OF_YEAR, 1);
									master.setScheduledate(checkdate.format(chkdatecal.getTime()));
								}
								break;
							case '7':
								if (chkdatecal.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY) {
									chkdatecal.add(Calendar.DAY_OF_YEAR, 1);
									master.setScheduledate(checkdate.format(chkdatecal.getTime()));
								}
								break;

							default:
								break;
							}
						}

						int checkexist = notAvailableSlotDAO
								.checkvacinationimmunizationajaxDataVet(String.valueOf(master.getId()), clientid);
						if (checkexist == 0) {
							// if in means record does not exist
							int x = notAvailableSlotDAO.setdatatoVacinationInfoVet(master, clientid);
							// inserts data
							DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
							Calendar cal3 = Calendar.getInstance();
							cal3.add(Calendar.DATE, 1);
							String tomarrow = dateFormat.format(cal3.getTime());
							if (master.getScheduledate().equals(tomarrow)) {
								// if tomarrow is vacinedate
								boolean smssent = notAvailableSlotDAO.checksmsflagvet(master.getId(), clientid);
								if (!smssent) {
									/*
									 * int w=
									 * notAvailableSlotDAO.setsmsflag(master.getId()
									 * , clientid);
									 */
								}
							}

						} else {
							if (master.getDependson() > 0) {
								String depenondate = DateTimeUtils
										.isNull(notAvailableSlotDAO.getGivenDateVet(master.getDependson(), clientid));
								int scheduledep = Integer.parseInt(master.getVacine_scheduledon());
								
								//Shouldnt change the date if given date s there 
								String currentsGivenDate = DateTimeUtils
										.isNull(notAvailableSlotDAO.getGivenDateVet(master.getId(), clientid));
								if(currentsGivenDate.equals("")){
									if (depenondate.contains("-")) {
										SimpleDateFormat depndongivendatedate = new SimpleDateFormat("yyyy-MM-dd");
										Date dependdate = depndongivendatedate.parse(depenondate);
										Calendar calnew = Calendar.getInstance();
										calnew.setTime(dependdate);
										String dependate = "";

										calnew.add(Calendar.DAY_OF_YEAR, master.getDeppendsonDays());
										dependate = depndongivendatedate.format(calnew.getTime());
										/*
										 * if(calnew.get(Calendar.DAY_OF_WEEK)==Calendar
										 * .SUNDAY){ calnew.add(Calendar.DAY_OF_YEAR,1);
										 * dependate=depndongivendatedate.format(calnew.
										 * getTime()); }
										 */
										master.setScheduledate(DateTimeUtils.getCommencingDate1(dependate));
										notAvailableSlotDAO.updatedueDateOfVaccineVet(clientid, "" + master.getId(), dependate);
									}
								}
							
							}

							if (ischangedob) {
								int dobchngsuccess = notAvailableSlotDAO.updatedueDateOfVaccineVet(clientid,
										"" + master.getId(), DateTimeUtils.getCommencingDate1(master.getScheduledate()));
							}
						}

						String savedauedate = notAvailableSlotDAO.getDueDateVet("" + master.getId(), clientid);
						if (!DateTimeUtils.isNull(savedauedate).equals("")) {
							master.setScheduledate(savedauedate);

						}
						master.setVacine_period(DateTimeUtils.getAge1onAddmissionimmu(dob, master.getScheduledate()));

					}
				}
				ArrayList<Master> list1 = new ArrayList<Master>();
				for (Master master : vacinlist) {
					if (master != null) {

						list1.add(master);
					}
				}
				type = DateTimeUtils.numberCheck(type);

				notAvailableSlotForm.setVacine_type(Integer.parseInt(type));
				notAvailableSlotForm.setClientId(clientid);
				notAvailableSlotForm.setAbrivationid(client.getAbrivationid());
				notAvailableSlotForm.setFullname(client.getFullname());
				notAvailableSlotForm.setVacinlist(list1);
				notAvailableSlotForm.setDob(client.getDob());
				notAvailableSlotForm.setLmpdate(lmpdate);
				// clinic header
				AccountsDAO accountsDAO = new JDBCAccountsDAO(connection);
				ArrayList<Clinic> locationAdressList = accountsDAO.getLocationAddress(loginInfo.getClinicid());
				ClinicDAO clinicDAO = new JDBCClinicDAO(connection);
				Clinic clinic = new Clinic();
				clinic = clinicDAO.getCliniclistDetails(loginInfo.getClinicid());
				notAvailableSlotForm.setClinicName(clinic.getClinicName());
				notAvailableSlotForm.setClinicOwner(clinic.getClinicOwner());
				notAvailableSlotForm.setOwner_qualification(clinic.getOwner_qualification());
				notAvailableSlotForm.setLocationAdressList(locationAdressList);
				notAvailableSlotForm.setAddress(clinic.getAddress());
				notAvailableSlotForm.setLandLine(clinic.getLandLine());
				notAvailableSlotForm.setClinicemail(clinic.getEmail());
				notAvailableSlotForm.setWebsiteUrl(clinic.getWebsiteUrl());
				notAvailableSlotForm.setClinicLogo(clinic.getUserImageFileName());
				notAvailableSlotForm.setBookedstatus((notAvailableSlotDAO.bookededStatsu(clientid)));
			} catch (Exception e) {
				e.printStackTrace();
			}
		
			
		}
		public String savevacinationimmunizationajax() {
			Connection connection = null;
			try {
				connection = Connection_provider.getconnection();
				StringBuilder buffer1 = new StringBuilder();
				BufferedReader reader = request.getReader();
				String line;
				while ((line = reader.readLine()) != null) {
					buffer1.append(line);
				}
				String data = buffer1.toString();
				JSONObject jsonObject = new JSONObject(data);

				String clientid = jsonObject.getString("clientid");
				String masterid = jsonObject.getString("masterid");
				String mastername = jsonObject.getString("mastername");
				String givendate = jsonObject.getString("givendate");
				String duedate = jsonObject.getString("duedate");
				NotAvailableSlotDAO notAvailableSlotDAO = new JDBCNotAvailableSlotDAO(connection);
				int x = 0, y = 0;
				x = notAvailableSlotDAO.checkvacinationimmunizationajaxData(masterid, clientid);
				if (x == 0) {
					y = notAvailableSlotDAO.savevacinationimmunizationajax(mastername, masterid, clientid, givendate,
							duedate);
				} else {
					y = notAvailableSlotDAO.updatevacinationimmunizationajaxdate(String.valueOf(x), givendate, duedate);
				}

				response.setContentType("application/json");
				response.setHeader("Cache-Control", "no-cache");
				response.getWriter().write("1");
				String date = "";
			} catch (Exception e) {
				e.printStackTrace();
			}

			return null;
		}

		public String updateremarkvacinationimmunizationajax() {
			Connection connection = null;
			try {
				connection = Connection_provider.getconnection();
				StringBuilder buffer1 = new StringBuilder();
				BufferedReader reader = request.getReader();
				String line;
				while ((line = reader.readLine()) != null) {
					buffer1.append(line);
				}
				String data = buffer1.toString();
				JSONObject jsonObject = new JSONObject(data);

				String clientid = jsonObject.getString("clientid");
				String masterid = jsonObject.getString("masterid");
				String remark = jsonObject.getString("remark");
				NotAvailableSlotDAO notAvailableSlotDAO = new JDBCNotAvailableSlotDAO(connection);
				int x = 0, y = 0;
				x = notAvailableSlotDAO.updatevacinationimmunizationajaxRemark(clientid, masterid, remark);

				response.setContentType("application/json");
				response.setHeader("Cache-Control", "no-cache");
				response.getWriter().write("1");
				String date = "";
			} catch (Exception e) {
				e.printStackTrace();
			}

			return null;
		}
		public void immunizationProcessVet(String clientid, String type, boolean fromVital) {
			Connection connection = null;
			try {
				connection = Connection_provider.getconnection();
				ClientDAO clientDAO = new JDBCClientDAO(connection);
				NotAvailableSlotDAO notAvailableSlotDAO = new JDBCNotAvailableSlotDAO(connection);
				Client client = clientDAO.getPatient(Integer.parseInt(clientid));
				String changedob = notAvailableSlotDAO.getDOBChangeLogDate(clientid);
				boolean ischangedob = false;
				if (!DateTimeUtils.isNull(clientid).equals("")) {
					if (changedob.equals("")) {
						notAvailableSlotDAO.insertDobChangeLog(clientid, client.getDob());
					}
					changedob = notAvailableSlotDAO.getDOBChangeLogDate(clientid);
					if (!changedob.equals(client.getDob())) {
						ischangedob = true;
					}
				}
				if (ischangedob) {
					notAvailableSlotDAO.insertDobChangeLog(clientid, client.getDob());
				}
				if (type != null) {
					if (!type.equals("")) {
						client.setVacine_type(Integer.parseInt(type));
					}
				} else {
					client.setVacine_type(0);
				}
				notAvailableSlotForm.setVacine_type(client.getVacine_type());
				String lmpdate = "";
				ArrayList<Master> vacinlist = new ArrayList<Master>();
				vacinlist = notAvailableSlotDAO.getVaccinationdataveterinary(client);
				if (vacinlist.size() != 0) {

					for (Master master : vacinlist) {
						/*if (master == null) {
							continue;
						}*/

						master.getVacine_scheduledon();
						String dob = client.getDob();

						dob = DateTimeUtils.getCommencingDatePicker(dob);
						if (client.getVacine_type() == 1) {
							// dob is lmp date fr gynic
							dob = notAvailableSlotDAO.lmpDAte(clientid);
							lmpdate = dob;
							if(fromVital||DateTimeUtils.isNull(dob).equals("")){
								dob = notAvailableSlotDAO.lmpDateFromVital(clientid);
								lmpdate = dob;
							}
							
						} else if (client.getVacine_type() == 2) {
							DateFormat dateFormat2 = new SimpleDateFormat("dd-MM-yyyy");
							Calendar cal2 = Calendar.getInstance();
							dob = dateFormat2.format(cal2.getTime());
						}
						int schedule = Integer.parseInt(master.getVacine_scheduledon());
						SimpleDateFormat birthDate = new SimpleDateFormat("dd-MM-yyyy");
						Date date = birthDate.parse(dob);
						Calendar cal = Calendar.getInstance();
						cal.setTime(date);
						String scheduledate = "";
						cal.add(Calendar.DAY_OF_YEAR, schedule);
						scheduledate = birthDate.format(cal.getTime());
						/*
						 * if(cal.get(Calendar.DAY_OF_WEEK)==Calendar.SUNDAY){
						 * cal.add(Calendar.DAY_OF_YEAR,1);
						 * scheduledate=birthDate.format(cal.getTime()); }
						 */ master.setScheduledate(scheduledate);

						int depndvalue = 0;
						if (master.getVacine_dependson() != null) {
							if (!master.getVacine_dependson().equals("")) {
								depndvalue = notAvailableSlotDAO.getvacinedependacyvaleveterinary(master.getVacine_dependson());
							}
						}

						/*
						 * if(master.getVacine_dependson()!=null){
						 * if(!master.getVacine_dependson().equals("")){ Master
						 * mstr=
						 * vacinlist.get(Integer.parseInt(master.getVacine_dependson
						 * ())-1); String newdate= mstr.getScheduledate();
						 * SimpleDateFormat birthDate2=new
						 * SimpleDateFormat("dd-MM-yyyy"); Date date2=
						 * birthDate2.parse(newdate); Calendar cal2=
						 * Calendar.getInstance(); cal2.setTime(date2); String
						 * scheduledate2=""; cal2.add(Calendar.DAY_OF_YEAR,
						 * schedule); scheduledate2=
						 * birthDate2.format(cal2.getTime());
						 * master.setScheduledate(scheduledate2);
						 * if(mstr.getVacine_givendate()==null){
						 * 
						 * } } }
						 */
						/*
						 * Calendar cal = Calendar.getInstance();
						 * cal.add(Calendar.DATE, schedule+depndvalue); String
						 * scheduledate=""; scheduledate =
						 * birthDate.format(cal.getTime());
						 * master.setScheduledate(scheduledate);
						 */
						SimpleDateFormat checkdate = new SimpleDateFormat("dd-MM-yyyy");

						String exclude = DateTimeUtils.isNull(master.getVacine_excludes());
						for (int i = 0; i < exclude.length(); i++) {
							Date chkdateobj = checkdate.parse(master.getScheduledate());
							Calendar chkdatecal = Calendar.getInstance();
							chkdatecal.setTime(chkdateobj);
							char day = (exclude.charAt(i));
							switch (day) {
							case '1':
								if (chkdatecal.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY) {
									chkdatecal.add(Calendar.DAY_OF_YEAR, 1);
									master.setScheduledate(checkdate.format(chkdatecal.getTime()));
								}
								break;
							case '2':
								if (chkdatecal.get(Calendar.DAY_OF_WEEK) == Calendar.MONDAY) {
									chkdatecal.add(Calendar.DAY_OF_YEAR, 1);
									master.setScheduledate(checkdate.format(chkdatecal.getTime()));
								}
								break;
							case '3':
								if (chkdatecal.get(Calendar.DAY_OF_WEEK) == Calendar.TUESDAY) {
									chkdatecal.add(Calendar.DAY_OF_YEAR, 1);
									master.setScheduledate(checkdate.format(chkdatecal.getTime()));
								}
								break;
							case '4':
								if (chkdatecal.get(Calendar.DAY_OF_WEEK) == Calendar.WEDNESDAY) {
									chkdatecal.add(Calendar.DAY_OF_YEAR, 1);
									master.setScheduledate(checkdate.format(chkdatecal.getTime()));
								}
								break;
							case '5':
								if (chkdatecal.get(Calendar.DAY_OF_WEEK) == Calendar.THURSDAY) {
									chkdatecal.add(Calendar.DAY_OF_YEAR, 1);
									master.setScheduledate(checkdate.format(chkdatecal.getTime()));
								}
								break;
							case '6':
								if (chkdatecal.get(Calendar.DAY_OF_WEEK) == Calendar.FRIDAY) {
									chkdatecal.add(Calendar.DAY_OF_YEAR, 1);
									master.setScheduledate(checkdate.format(chkdatecal.getTime()));
								}
								break;
							case '7':
								if (chkdatecal.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY) {
									chkdatecal.add(Calendar.DAY_OF_YEAR, 1);
									master.setScheduledate(checkdate.format(chkdatecal.getTime()));
								}
								break;

							default:
								break;
							}
						}

						int checkexist = notAvailableSlotDAO
								.checkvacinationimmunizationajaxDataVet(String.valueOf(master.getId()), clientid);
						if (checkexist == 0) {
							// if in means record does not exist
							int x = notAvailableSlotDAO.setdatatoVacinationInfoVet(master, clientid);
							// inserts data
							DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
							Calendar cal3 = Calendar.getInstance();
							cal3.add(Calendar.DATE, 1);
							String tomarrow = dateFormat.format(cal3.getTime());
							if (master.getScheduledate().equals(tomarrow)) {
								// if tomarrow is vacinedate
								boolean smssent = notAvailableSlotDAO.checksmsflagvet(master.getId(), clientid);
								if (!smssent) {
									/*
									 * int w=
									 * notAvailableSlotDAO.setsmsflag(master.getId()
									 * , clientid);
									 */
								}
							}

						} else {
							if (master.getDependson() > 0) {
								String depenondate = DateTimeUtils
										.isNull(notAvailableSlotDAO.getGivenDateVet(master.getDependson(), clientid));
								int scheduledep = Integer.parseInt(master.getVacine_scheduledon());
								
								//Shouldnt change the date if given date s there 
								String currentsGivenDate = DateTimeUtils
										.isNull(notAvailableSlotDAO.getGivenDateVet(master.getId(), clientid));
								if(currentsGivenDate.equals("")){
									if (depenondate.contains("-")) {
										SimpleDateFormat depndongivendatedate = new SimpleDateFormat("yyyy-MM-dd");
										Date dependdate = depndongivendatedate.parse(depenondate);
										Calendar calnew = Calendar.getInstance();
										calnew.setTime(dependdate);
										String dependate = "";

										calnew.add(Calendar.DAY_OF_YEAR, master.getDeppendsonDays());
										dependate = depndongivendatedate.format(calnew.getTime());
										/*
										 * if(calnew.get(Calendar.DAY_OF_WEEK)==Calendar
										 * .SUNDAY){ calnew.add(Calendar.DAY_OF_YEAR,1);
										 * dependate=depndongivendatedate.format(calnew.
										 * getTime()); }
										 */
										master.setScheduledate(DateTimeUtils.getCommencingDate1(dependate));
										notAvailableSlotDAO.updatedueDateOfVaccineVet(clientid, "" + master.getId(), dependate);
									}
								}
							
							}

							if (ischangedob) {
								int dobchngsuccess = notAvailableSlotDAO.updatedueDateOfVaccineVet(clientid,
										"" + master.getId(), DateTimeUtils.getCommencingDate1(master.getScheduledate()));
							}
						}

						String savedauedate = notAvailableSlotDAO.getDueDateVet("" + master.getId(), clientid);
						if (!DateTimeUtils.isNull(savedauedate).equals("")) {
							master.setScheduledate(savedauedate);

						}
						master.setVacine_period(DateTimeUtils.getAge1onAddmissionimmu(dob, master.getScheduledate()));

					}
				}
				ArrayList<Master> list1 = new ArrayList<Master>();
				for (Master master : vacinlist) {
					if (master != null) {

						list1.add(master);
					}
				}
				type = DateTimeUtils.numberCheck(type);

				notAvailableSlotForm.setVacine_type(Integer.parseInt(type));
				notAvailableSlotForm.setClientId(clientid);
				notAvailableSlotForm.setAbrivationid(client.getAbrivationid());
				notAvailableSlotForm.setFullname(client.getFullname());
				notAvailableSlotForm.setVacinlist(list1);
				notAvailableSlotForm.setDob(client.getDob());
				notAvailableSlotForm.setLmpdate(lmpdate);
				// clinic header
				AccountsDAO accountsDAO = new JDBCAccountsDAO(connection);
				ArrayList<Clinic> locationAdressList = accountsDAO.getLocationAddress(loginInfo.getClinicid());
				ClinicDAO clinicDAO = new JDBCClinicDAO(connection);
				Clinic clinic = new Clinic();
				clinic = clinicDAO.getCliniclistDetails(loginInfo.getClinicid());
				notAvailableSlotForm.setClinicName(clinic.getClinicName());
				notAvailableSlotForm.setClinicOwner(clinic.getClinicOwner());
				notAvailableSlotForm.setOwner_qualification(clinic.getOwner_qualification());
				notAvailableSlotForm.setLocationAdressList(locationAdressList);
				notAvailableSlotForm.setAddress(clinic.getAddress());
				notAvailableSlotForm.setLandLine(clinic.getLandLine());
				notAvailableSlotForm.setClinicemail(clinic.getEmail());
				notAvailableSlotForm.setWebsiteUrl(clinic.getWebsiteUrl());
				notAvailableSlotForm.setClinicLogo(clinic.getUserImageFileName());
				notAvailableSlotForm.setBookedstatus((notAvailableSlotDAO.bookededStatsu(clientid)));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

}
