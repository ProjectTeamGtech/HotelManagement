package com.apm.Ipd.web.action;

import java.sql.Connection;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.struts2.ServletActionContext;

import com.apm.Accounts.eu.bi.AccountsDAO;
import com.apm.Accounts.eu.blogic.jdbc.JDBCAccountsDAO;
import com.apm.Accounts.eu.entity.Accounts;
import com.apm.AssesmentForms.eu.bi.AssessmentFormDAO;
import com.apm.AssesmentForms.eu.blogic.jdbc.JDBCAssessmentFormDAO;
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
import com.apm.DiaryManagement.eu.entity.DiaryManagement;
import com.apm.DiaryManagement.eu.entity.NotAvailableSlot;
import com.apm.DiaryManagement.eu.entity.Priscription;
import com.apm.DiaryManagement.web.action.SmsService;
import com.apm.Dietary.eu.bi.DietaryDetailsDAO;
import com.apm.Dietary.eu.blogic.jdbc.JDBCDietaryDetailsDAO;
import com.apm.Dietary.eu.entity.DietaryDetails;
import com.apm.Inventory.eu.bi.InventoryProductDAO;
import com.apm.Inventory.eu.bi.InventoryVendorDAO;
import com.apm.Inventory.eu.blogic.jdbc.JDBCInventoryProductDAO;
import com.apm.Inventory.eu.blogic.jdbc.JDBCInventoryVendorDAO;
import com.apm.Inventory.eu.entity.Product;
import com.apm.Ipd.eu.bi.BedDao;
import com.apm.Ipd.eu.bi.IpdDAO;
import com.apm.Ipd.eu.blogic.jdbc.JDBCBedDao;
import com.apm.Ipd.eu.blogic.jdbc.JDBCIpdDAO;
import com.apm.Ipd.eu.entity.Bed;
import com.apm.Ipd.web.form.IpdForm;
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
import com.apm.Registration.eu.entity.Location;
import com.apm.Registration.eu.entity.UserProfile;
import com.apm.ThirdParties.eu.bi.ThirdPartyDAO;
import com.apm.ThirdParties.eu.blogic.jdbc.JDBCThirdPartyDAO;
import com.apm.ThirdParties.eu.entity.ThirdParty;
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
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ModelDriven;

public class IpdAjaxAction extends BaseAction implements ModelDriven<IpdForm> {
	HttpServletRequest request = (HttpServletRequest) ActionContext.getContext().get(ServletActionContext.HTTP_REQUEST);
	HttpServletResponse response = (HttpServletResponse) ActionContext.getContext()
			.get(ServletActionContext.HTTP_RESPONSE);
	HttpSession session = request.getSession(true);
	LoginInfo loginInfo = LoginHelper.getLoginInfo(request);

	Pagination pagination = new Pagination(25, 1);

	public Pagination getPagination() {
		return pagination;
	}

	public void setPagination(Pagination pagination) {
		this.pagination = pagination;
	}

	IpdForm ipdForm = new IpdForm();

	public IpdForm getModel() {
		return ipdForm;
	}

	public String ipdformdata() throws Exception {
		if (!verifyLogin(request)) {
			return "login";
		}
		String wardid = request.getParameter("wardid");
		String bedid = request.getParameter("bedid");
		String action = request.getParameter("action");
		Connection connection = null;
		try {
			connection = Connection_provider.getconnection();
			IpdDAO ipdDAO = new JDBCIpdDAO(connection);
			UserProfileDAO userProfileDAO = new JDBCUserProfileDAO(connection);
			MasterDAO masterDAO = new JDBCMasterDAO(connection);
			ClientDAO clientDAO = new JDBCClientDAO(connection);
			BedDao bedDao = new JDBCBedDao(connection);
			NotAvailableSlotDAO notAvailableSlotDAO = new JDBCNotAvailableSlotDAO(connection);
			AccountsDAO accountsDAO = new JDBCAccountsDAO(connection);
			TreatmentEpisodeDAO treatmentEpisodeDAO = new JDBCTreatmentEpisode(connection);

			ArrayList<Bed> wardlist = bedDao.getAllWardList(action);
			ipdForm.setWardlist(wardlist);
			ArrayList<Bed> bedList = ipdDAO.getWardWiseBedList(wardid);
			DiaryManagementDAO diaryManagementDAO = new JDBCDiaryManagentDAO(connection);
			String clientid = ipdForm.getClientid();
			Client client = clientDAO.getClientDetails(clientid);
			String practitionerid = ipdForm.getPractitionerid();
			ipdForm.setBedlist(bedList);
			ipdForm.setClientid(clientid);
			ipdForm.setWardid(wardid);
			ipdForm.setBedid(bedid);
			ipdForm.setTpid(client.getTypeName());
			ipdForm.setPractitionerid(practitionerid);
			String chief_comlint_id = masterDAO.getIpdTemplateId("Chief Complaints");
			String present_ill_id = masterDAO.getIpdTemplateId("Present Illness");
			String past_history_id = masterDAO.getIpdTemplateId("Past History");
			String family_hist_id = masterDAO.getIpdTemplateId("Family History");
			String personal_hist_id = masterDAO.getIpdTemplateId("Personal History");
			String onexami_id = masterDAO.getIpdTemplateId("On Examination");
			String tretment_given_id = masterDAO.getIpdTemplateId("Treatment Given");
			UserProfile userProfile = userProfileDAO.getUserprofileDetails(Integer.parseInt(practitionerid));
			String specializationId = userProfile.getDiciplineName();
			ipdForm.setDepartment(userProfile.getSpecialization());
			if (loginInfo.getClinicUserid().equals("iconhospital")) {
				if (specializationId.equals("20") || specializationId.equals("21")) {
					ipdForm.setWeightsts("true");
				} else {
					ipdForm.setWeightsts("false");
				}
			} else {
				ipdForm.setWeightsts("false");
			}
			boolean issystemicreview = masterDAO.isIpdFormFieldActive(specializationId, "Systemic Review");
			boolean history = masterDAO.isIpdFormFieldActive(specializationId, "History");
			boolean obstretic_history = masterDAO.isIpdFormFieldActive(specializationId, "OBSTRETIC HISTORY");
			boolean menstrual_history = masterDAO.isIpdFormFieldActive(specializationId, "MENSTRUAL HISTORY");
			boolean substance_history = masterDAO.isIpdFormFieldActive(specializationId, "SUBSTANCE HISTORY");
			boolean peditric = masterDAO.isIpdFormFieldActive(specializationId, "Paediatric History");
			ipdForm.setIssystemicreview(issystemicreview);
			ipdForm.setHistory(history);
			ipdForm.setObstretic_history(obstretic_history);
			ipdForm.setMenstrual_history(menstrual_history);
			ipdForm.setSubstance_history(substance_history);
			ipdForm.setPaediatrichist(peditric);
			ArrayList<Master> chief_complaints_list = masterDAO.getIpdTemplateListNames(chief_comlint_id);
			ArrayList<Master> present_illness_list = masterDAO.getIpdTemplateListNames(present_ill_id);
			ArrayList<Master> past_history_list = masterDAO.getIpdTemplateListNames(past_history_id);
			ArrayList<Master> family_history_list = masterDAO.getIpdTemplateListNames(family_hist_id);
			ArrayList<Master> personal_hist_list = masterDAO.getIpdTemplateListNames(personal_hist_id);
			ArrayList<Master> on_exam_list = masterDAO.getIpdTemplateListNames(onexami_id);
			ArrayList<Master> treatment_given_list = masterDAO.getIpdTemplateListNames(tretment_given_id);
			ipdForm.setChief_complaints_list(chief_complaints_list);
			ipdForm.setPresent_illness_list(present_illness_list);
			ipdForm.setPast_history_list(past_history_list);
			ipdForm.setFamily_history_list(family_history_list);
			ipdForm.setPersonal_hist_list(personal_hist_list);
			ipdForm.setOn_exam_list(on_exam_list);
			ipdForm.setTreatment_given_list(treatment_given_list);
			int selectedid = loginInfo.getId();
			ClinicDAO clinicListDAO = new JDBCClinicDAO(connection);
			com.apm.Registration.eu.entity.Clinic cliniclist = clinicListDAO.getCliniclistDetails(selectedid);
			ipdForm.setClinicName(cliniclist.getClinicName());

			// prepapre

			ArrayList<Client> clientOccupationList = clientDAO.getOccupationList();
			Client client1 = new Client();
			client1.getOther();
			clientOccupationList.add(client1);

			ipdForm.setClientOccupationList(clientOccupationList);
			ipdForm.setCountryList(PopulateList.countryList());

			// user define date time
			ipdForm.setHourList(PopulateList.hourList());
			ipdForm.setMinuteList(PopulateList.getMinuteList());

			ArrayList<Accounts> thirdPartyList = accountsDAO.getThirdPartyList(loginInfo.getId());
			ipdForm.setThirdPartyList(thirdPartyList);

			ArrayList<UserProfile> mlcdrlist = userProfileDAO.getAllPractitionerList(null, null, null, null, "1");
			ipdForm.setMlcdrlist(mlcdrlist);

			ArrayList<DiaryManagement> userList = notAvailableSlotDAO.getUserList(loginInfo.getId());
			ipdForm.setUserList(userList);

			//   25 dec 2017 list get from referal table
			ArrayList<Client> refrenceList = clientDAO.getReferenceList();
			ipdForm.setRefrenceList(refrenceList);

			ArrayList<Location> locationList = diaryManagementDAO.getLocationList(loginInfo.getId());
			ipdForm.setLocationList(locationList);

			ArrayList<String> departmentList = diaryManagementDAO.getDepartmentList();
			ipdForm.setDepartmentList(departmentList);

			ArrayList<String> initialList = clientDAO.getInitialList();
			ipdForm.setInitialList(initialList);

			// set state and city list
			InventoryVendorDAO vendorDAO = new JDBCInventoryVendorDAO(connection);
			ArrayList<Master> statelist = vendorDAO.getAllStateList();
			ipdForm.setStatelist(statelist);

			ArrayList<Master> citylist = vendorDAO.getAllCityList();
			ipdForm.setCitylist(citylist);

			ArrayList<Client> diagnosisList = clientDAO.getEmrTreatmentTypeList();
			ipdForm.setDiagnosisList(diagnosisList);

			ArrayList<Client> surgeryList = clientDAO.getSurgeryList();
			if (surgeryList.size() == 0) {
				surgeryList = new ArrayList<Client>();
			}
			Client client3 = new Client();
			client3.getOther();
			surgeryList.add(client3);
			ipdForm.setSurgeryList(surgeryList);

			ArrayList<Client> thirdPartyTypeList = new ArrayList<Client>();
			thirdPartyTypeList = clientDAO.getThirdPartyType();
			ipdForm.setThirdPartyTypeList(thirdPartyTypeList);

			ArrayList<Client> condtitionList = clientDAO.getEmrTreatmentTypeList();
			ipdForm.setCondtitionList(condtitionList);

			ArrayList<TreatmentEpisode> sourceOfReferralList = treatmentEpisodeDAO.getSourceOfReferralList();
			ipdForm.setSourceOfReferralList(sourceOfReferralList);
			ipdForm.setActiontype(action);
			if (action.equals("1") || action.equals("2")) {
				return "addipdcasualty";
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			connection.close();
		}

		return INPUT;

	}

	public String admitted() throws Exception {
		if (!verifyLogin(request)) {
			return "login";
		}

		Connection connection = null;
		String clientid = request.getParameter("clientid");
		try {
			connection = Connection_provider.getconnection();
			AssessmentFormDAO assessmentFormDAO = new JDBCAssessmentFormDAO(connection);
			int bedid = assessmentFormDAO.getIpdBedno(clientid);

			response.setContentType("text/html");
			response.setHeader("Cache-Control", "no-cache");
			response.getWriter().write("" + bedid + "");

		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			connection.close();
		}

		return null;
	}

	public String clientinfo() throws Exception {

		if (!verifyLogin(request)) {

			return "login";
		}

		Connection connection = null;

		String clientid = request.getParameter("clientid");

		try {

			connection = Connection_provider.getconnection();
			BedDao bedDao = new JDBCBedDao(connection);
			ThirdPartyDAO thirdPartyDAO = new JDBCThirdPartyDAO(connection);

			NotAvailableSlot notAvailableSlot = bedDao.getClientLastOpdRecord(clientid);
			IpdDAO ipdDAO = new JDBCIpdDAO(connection);

			ClientDAO clientDAO = new JDBCClientDAO(connection);
			Client client = clientDAO.getPatient(Integer.parseInt(clientid));

			String fullname = client.getTitle() + " " + client.getFirstName() + " " + client.getMiddlename() + " "
					+ client.getLastName();
			String tp = clientDAO.getTPCompanyName(client.getTypeName());

			ThirdParty thirdParty = thirdPartyDAO.getThirdPartyDetails(client.getTypeName());

			String age = DateTimeUtils.getAge(client.getDob());
			String agegender = "";
			if (Integer.parseInt(age) < 2) {

				String monthdays = DateTimeUtils.getMonthandDays(client.getDob());
				agegender = monthdays + " / " + client.getGender();
			} else {
				agegender = age + "Years" + " / " + client.getGender();
			}
			age = DateTimeUtils.getAge1(client.getDob());
			agegender = age + " / " + client.getGender();
			String num_admission = ipdDAO.getNumofAdmissionCount(clientid);
			String abrivationid = client.getAbrivationid();

			String reference = clientDAO.getReferenceName(client.getReference());
			if (client.getEmergencyContName() == null) {
				client.setEmergencyContName("");
			}

			if (client.getEmergencyContNo() == null) {
				client.setEmergencyContNo("");
			}
			if (client.getReference() == null) {
				client.setReference("");
			}
			if (client.getRelation() == null) {

				client.setRelation("");
			}
			if (client.getAbrivationid() == null) {
				client.setAbrivationid("");
			}
			int lastipdid = ipdDAO.getLastIpdId(clientid);
			Bed bed = bedDao.getEditIpdData("" + lastipdid);
			bed.setPractitionerid(DateTimeUtils.numberCheck(bed.getPractitionerid()));

			UserProfileDAO ueUserProfileDAO = new JDBCUserProfileDAO(connection);
			UserProfile userProfile = ueUserProfileDAO.getUserprofileDetails(Integer.parseInt(bed.getPractitionerid()));
			String dept = userProfile.getSpecialization();

			String clientpay = "0";
			if (client.getWhopay() == null || client.getWhopay().equals("") || client.getWhopay().equals("Client")
					|| client.getWhopay().equals("0")) {
				clientpay = "0";
			} else {
				clientpay = "1";
			}

			String clientInfo = fullname + "~" + notAvailableSlot.getDiaryUser() + "~" + notAvailableSlot.getCondition()
					+ "~" + notAvailableSlot.getTreatmentEpisodeId() + "~" + tp + "~" + client.getWhopay() + "~"
					+ clientid + "~" + client.getPolicyNo() + "~" + agegender + "~" + client.getAddress() + "~"
					+ client.getDob() + "~" + client.getEmergencyContName() + "~" + client.getEmergencyContNo() + "~"
					+ client.getRelation() + "~" + num_admission + "~" + reference + "~" + abrivationid + "~"
					+ bed.getPractitionerid() + "~" + dept + "~" + clientpay;

			response.setContentType("text/html");
			response.setHeader("Cache-Control", "no-cache");
			response.getWriter().write("" + clientInfo + "");

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			connection.close();
		}
		return null;
	}

	public String returnipdmedicine() throws Exception {
		Connection connection = null;
		try {
			connection = Connection_provider.getconnection();
			PharmacyDAO pharmacyDAO = new JDBCPharmacyDAO(connection);
			BedDao bedDao = new JDBCBedDao(connection);
			ClientDAO clientDAO = new JDBCClientDAO(connection);
			String clientid = request.getParameter("ipdclientid");
			String ispharmacy = "0";
			String ipdid = request.getParameter("ipdid");
			boolean flag = false;
			if (clientid == null) {
				clientid = (String) session.getAttribute("nursebyreturnclientid");
				ipdid = (String) session.getAttribute("nursebyreturnipd");
				flag = true;
			}

			if (ipdid != null) {
				if (ipdid.equals("")) {
					ipdid = "0";
				}
			} else {
				ipdid = "0";
			}

			String pract_name = "";
			if (ipdid.equals("0")) {
				pract_name = pharmacyDAO.getappointmentinfo(Integer.parseInt(clientid));
			} else {
				Bed bed = bedDao.getEditIpdData(ipdid);
				UserProfileDAO profileDAO = new JDBCUserProfileDAO(connection);
				pract_name = profileDAO.getName(bed.getPractitionerid());
			}
			Client client = clientDAO.getClientDetails(clientid);
			String mob = client.getMobNo();
			String address = client.getAddress();
			String clientname = client.getTitle() + " " + client.getFirstName() + " " + client.getLastName();

			ipdForm.setFullname(clientname);
			ipdForm.setMobile(mob);
			ipdForm.setAddress(address);
			ipdForm.setPractitionerName(pract_name);
			ArrayList<Product> allMedicieneList = pharmacyDAO.getAllMedicinesForIPDReturn(clientid, ispharmacy, "0", 1);
			ipdForm.setAllMedicieneList(allMedicieneList);
			ipdForm.setIpdid(ipdid);
			ipdForm.setClientid(clientid);
			ipdForm.setIspharmacy(ispharmacy);
			if (flag) {

			}

			if (flag) {
				ipdForm.setSucessmsg("Return request send sucessfully in pharmacy");
				addActionMessage("Return request send sucessfully in pharmacy");
			} else {
				ipdForm.setSucessmsg("0");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			connection.close();
		}
		return "returnmedicinenurse";
	}

	public String viewvisitingconsult() throws Exception {

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
				if(breadcrumbs.getName().equals("Visiting Consultant Dashboard")){
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
				breadcrumbs.setName("Visiting Consultant Dashboard");
				breadcrumbs.setOn(true);
				breadcrumbs.setSqno(modulecount);
				breadcrumbs.setUrllink("viewvisitingconsultIpdAjax");
				breadcrumbs.setIscurrent(true);
				breadcrumbs.setShowingname("Visiting Consultant Dashboard");
				indentflowlist.add(breadcrumbs);
			}
			session.setAttribute("indentflowlist",indentflowlist); 
			
			connection = Connection_provider.getconnection();
			MasterDAO masterDAO = new JDBCMasterDAO(connection);
			ClientDAO clientDAO = new JDBCClientDAO(connection);
			UserProfileDAO userProfileDAO = new JDBCUserProfileDAO(connection);
			String searchtext = ipdForm.getSearchtext();
			String fromdate = ipdForm.getFromdate();
			String todate = ipdForm.getTodate();

			// String wardnameid = mrdForm.getWardnameid();
			String searchdrname = ipdForm.getSearchdrname();
			if (searchtext != null) {
				if (searchtext.equals("")) {
					searchtext = null;
				}
			}
			if (searchdrname != null) {
				if (searchdrname.equals("0") || searchdrname.equals("")) {
					searchdrname = null;
				}
			}

			if (fromdate == null) {
				DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
				Calendar cal = Calendar.getInstance();
				// cal.add(Calendar.DATE, -7);
				fromdate = dateFormat.format(cal.getTime());
				fromdate = DateTimeUtils.getCommencingDate1(fromdate);
			} else {
				if (fromdate.equals("")) {
					DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
					Calendar cal = Calendar.getInstance();
					// cal.add(Calendar.DATE, -7);
					fromdate = dateFormat.format(cal.getTime());
					fromdate = DateTimeUtils.getCommencingDate1(fromdate);
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
				} else {
					todate = DateTimeUtils.getCommencingDate1(todate);
				}
			}
			IpdDAO ipdDAO = new JDBCIpdDAO(connection);
			String ipdid = request.getParameter("ipdid");
			int count = ipdDAO.getTotalVisitingConsultList(ipdid, searchtext, fromdate, todate, searchdrname);
			pagination.setPreperties(count);

			// Visiting Consultant
			if (ipdid != null) {
				if (ipdid.equals("")) {
					ipdid = null;
				}
			}
			ArrayList<Bed> visitingConsultList = ipdDAO.getVisitingConsultList(ipdid, searchtext, fromdate, todate,
					searchdrname, pagination);

			pagination.setPage_records(visitingConsultList.size());
			ipdForm.setPagerecords(String.valueOf(pagination.getPage_records()));
			ipdForm.setTotalRecords(count);

			fromdate = DateTimeUtils.getCommencingDate1(fromdate);
			todate = DateTimeUtils.getCommencingDate1(todate);
			ipdForm.setSearchtext(searchtext);
			ipdForm.setFromdate(fromdate);
			ipdForm.setTodate(todate);
			ipdForm.setSearchdrname(searchdrname);

			ipdForm.setVisitingConsultList(visitingConsultList);
			// Practitioner list only Visiting Consult
			// ArrayList<UserProfile>
			// visitingConsultDoctors=userProfileDAO.getVisitingPractitiner();
			// akash 24 dec 17 set visiting consultant and practitional list
			// instead of practitional
			ArrayList<UserProfile> visitingConsultDoctors = userProfileDAO.getVisitingPractitinerList();
			ipdForm.setVisitingConsultDoctors(visitingConsultDoctors);
			ipdForm.setVisitingtimeList(PopulateList.startTimeList());

			// this code take lot of tiem take patient details // 
			// ArrayList<Bed> activeIpdPatientList =
			// ipdDAO.getAllActiveIpdPatients();
			ArrayList<Bed> activeIpdPatientList = ipdDAO.getActiveIpdPatientList();
			ipdForm.setActiveIpdPatientList(activeIpdPatientList);
			//   03 oct 2017 set clientid and date
			String clientid = ipdDAO.getClientIDFromIPDID(ipdid);
			ipdForm.setClientid(clientid);
			String date = "";
			DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
			Calendar cal = Calendar.getInstance();
			date = dateFormat.format(cal.getTime());
			ipdForm.setDate(date);

			ArrayList<Master> disciplineList = masterDAO.getDisciplineDataList();
			ipdForm.setDisciplineList(disciplineList);
			ArrayList<String> initialList = new ArrayList<String>();
			initialList = clientDAO.getInitialList();
			ipdForm.setInitialList(initialList);

			//   25 dec 2017 practioner list
			ArrayList<UserProfile> practionerlist = userProfileDAO.getVisitingPractitiner();
			ipdForm.setPractionerlist(practionerlist);

		} catch (Exception e) {

			e.printStackTrace();
		} finally {
			connection.close();
		}

		return "visitconsult";
	}

	public String checkdralreadypresent() throws Exception {
		Connection connection = null;
		try {
			connection = Connection_provider.getconnection();
			UserProfileDAO profileDAO = new JDBCUserProfileDAO(connection);
			StringBuffer buffer = new StringBuffer();
			String id = request.getParameter("drid");
			int userid = profileDAO.checkdralreadypresent(id);
			if (userid == 0) {
				UserProfile userProfile = profileDAO.getUserprofileDetails(Integer.parseInt(id));

				buffer.append("0");
				buffer.append("~");
				buffer.append("" + DateTimeUtils.isNull(userProfile.getFullname()) + "");
				buffer.append("~");

				buffer.append("" + DateTimeUtils.isNull(userProfile.getDiciplineName()) + "");
				buffer.append("~");
				buffer.append("" + DateTimeUtils.isNull(userProfile.getMobile()) + "");
				buffer.append("~");
				buffer.append("" + DateTimeUtils.isNull(userProfile.getEmail()) + "");
				buffer.append("~");
				buffer.append("" + id + "");
			} else {
				buffer.append("1");
			}
			response.setContentType("text/html");
			response.setHeader("Cache-Control", "no-cache");
			response.getWriter().write("" + buffer.toString() + "");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			connection.close();
		}
		return null;
	}

	public String validateaddnewconsltalready() throws Exception {
		Connection connection = null;
		try {
			connection = Connection_provider.getconnection();
			UserProfileDAO profileDAO = new JDBCUserProfileDAO(connection);
			String consultantname = request.getParameter("consultantname");
			int i = profileDAO.checkExistConsultantName(consultantname);

			response.setContentType("text/html");
			response.setHeader("Cache-Control", "no-cache");
			response.getWriter().write("" + i + "");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			connection.close();
		}
		return null;
	}

	public String savenewconsultant() throws Exception {
		Connection connection = null;
		try {
			StringBuffer buffer = new StringBuffer();
			connection = Connection_provider.getconnection();

			UserProfileDAO userProfileDAO = new JDBCUserProfileDAO(connection);
			/*
			 * String initial = request.getParameter("initial"); String
			 * firstname = request.getParameter("firstname"); String lastname =
			 * request.getParameter("lastname");
			 */
			String diciplineName = request.getParameter("diciplineName");
			String mobileno = request.getParameter("mobileno");
			String visitingconfees = request.getParameter("visitingconfees");

			String consultantname = request.getParameter("consultantname");
			String isvisitingconsultant = request.getParameter("isvisitingcons");
			String issurgeon = request.getParameter("issurgeon");

			String isanesthesiologist = request.getParameter("isanesthesiologist");
			String isreferred = request.getParameter("isreferred");
			String existingdrid = request.getParameter("existingdr");
			String emailid = request.getParameter("emailid");
			String ismlc = request.getParameter("ismlc");
			String mlcqualification = request.getParameter("mlcqualification");
			String sharepercentage = request.getParameter("sharepercentage");
			String refshareammount = request.getParameter("refshareammount");
			String refsharetype = request.getParameter("refsharetype");

			UserProfile userProfile = new UserProfile();

			if (consultantname != null) {
				String[] name = consultantname.split(" ");
				if (name.length == 1) {
					String firstname = name[0];
					userProfile.setFirstname(firstname);
					userProfile.setLastname("");
					userProfile.setInitial("");
				} else if (name.length == 2) {
					String firstname = name[0];
					String lastname = name[1];
					userProfile.setFirstname(firstname);
					userProfile.setLastname(lastname);
					userProfile.setInitial("");
				} else {
					String initial = name[0];
					String firstname = name[1];
					String lastname = name[2];
					userProfile.setFirstname(firstname);
					userProfile.setLastname(lastname);
					userProfile.setInitial(initial);
				}
			}

			userProfile.setUserType(4);
			// userProfile.setVisitingdoctor(true);
			userProfile.setJobtitle("Visiting Consultant");
			userProfile.setFullname(consultantname);
			userProfile.setMobile(mobileno);
			userProfile.setEmail(emailid);
			userProfile.setDiciplineName(diciplineName);
			userProfile.setFees(visitingconfees);
			userProfile.setIsvisitingconsultant(Boolean.parseBoolean(isvisitingconsultant));
			userProfile.setIssurgeon(Boolean.parseBoolean(issurgeon));
			userProfile.setIsanesthesiologist(Boolean.parseBoolean(isanesthesiologist));
			userProfile.setIsreferred(Boolean.parseBoolean(isreferred));
			userProfile.setExistingdrid(existingdrid);
			userProfile.setIsmlc(Boolean.parseBoolean(ismlc));
			userProfile.setQualification(mlcqualification);
			userProfile.setSharepercentage(sharepercentage);

			if (existingdrid != null) {
				if (existingdrid.equals("") || existingdrid.equals("0")) {
					int res = userProfileDAO.saveVisitingCosultant(userProfile, loginInfo.getId(),
							loginInfo.getClinicName());
					userProfile.setExistingdrid(String.valueOf(res));
				}
			} else {
				int res = userProfileDAO.saveVisitingCosultant(userProfile, loginInfo.getId(),
						loginInfo.getClinicName());
				userProfile.setExistingdrid(String.valueOf(res));
			}
			userProfile.setIsfromvisitingdashboard(1);
			userProfile.setRefshareammount(refshareammount);
			userProfile.setRefsharetype(refsharetype);
			int res = userProfileDAO.saveReferralDoctor(userProfile);
			if(isvisitingconsultant.equals("true")){
				int rr=userProfileDAO.setisvisitingDoctor(userProfile);
			}
			// int res =
			// userProfileDAO.saveVisitingCosultant(userProfile,loginInfo.getId(),loginInfo.getClinicName());
			response.setContentType("text/html");
			response.setHeader("Cache-Control", "no-cache");
			response.getWriter().write("" + buffer.toString() + "");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			connection.close();
		}
		return null;
	}

	public String searchexistingconsultantdr() throws Exception {
		Connection connection = null;
		try {
			connection = Connection_provider.getconnection();
			UserProfileDAO profileDAO = new JDBCUserProfileDAO(connection);
			StringBuffer buffer = new StringBuffer();
			String val = request.getParameter("val");
			ArrayList<UserProfile> arrayList = profileDAO.checkExistConsultant(val);

			for (UserProfile profile : arrayList) {
				buffer.append("<tr>");
				buffer.append("<td><a href='#' style='color:#000;' onclick='setrefereddrinfo(" + profile.getId() + ")'>"
						+ profile.getName() + "</a></td>");
				buffer.append("</tr>");
			}

			response.setContentType("text/html");
			response.setHeader("Cache-Control", "no-cache");
			response.getWriter().write("" + buffer.toString() + "");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			connection.close();
		}
		return null;
	}

	public String setrefereddrinfo() throws Exception {
		Connection connection = null;
		try {
			connection = Connection_provider.getconnection();
			UserProfileDAO profileDAO = new JDBCUserProfileDAO(connection);
			StringBuffer buffer = new StringBuffer();
			String id = request.getParameter("id");
			UserProfile userProfile = profileDAO.getrefereddrinfo(id);

			buffer.append("" + userProfile.getId() + "");
			buffer.append("~");

			buffer.append("" + userProfile.getName() + "");
			buffer.append("~");

			buffer.append("" + userProfile.getSpecialization() + "");
			buffer.append("~");

			buffer.append("" + userProfile.getMobile() + "");
			buffer.append("~");

			buffer.append("" + userProfile.getEmail() + "");
			buffer.append("~");

			/*
			 * buffer.append(""+userProfile.getMobile()+""); buffer.append("~");
			 */

			buffer.append("" + userProfile.getFees() + "");
			buffer.append("~");

			buffer.append("" + userProfile.getUserid() + "");
			buffer.append("~");

			if (userProfile.isIsvisitingconsultant()) {
				buffer.append(
						"<label class='checkbox checkbox-custom-alt m-0 mt-5'><input type='checkbox' checked='checked' id='isvisitingcons' name='isvisitingcons'><i></i> Visiting Consultant</label>");
			} else {
				buffer.append(
						"<label class='checkbox checkbox-custom-alt m-0 mt-5'><input type='checkbox' id='isvisitingcons' name='isvisitingcons'><i></i> Visiting Consultant</label>");
			}

			buffer.append("~");

			// buffer.append("<label
			// for='exampleInputPassword1'>Surgeon</label>");
			if (userProfile.isIssurgeon()) {
				buffer.append(
						"<label class='checkbox checkbox-custom-alt m-0 mt-5'><input type='checkbox' checked='checked' id='issurgeon' name='issurgeon'><i></i> Surgeon</label>");
			} else {
				buffer.append(
						"<label class='checkbox checkbox-custom-alt m-0 mt-5'><input type='checkbox' id='issurgeon' name='issurgeon'><i></i> Surgeon</label>");
			}

			buffer.append("~");

			// buffer.append("<label
			// for='exampleInputPassword1'>Anesthesiologist</label>");
			if (userProfile.isIsanesthesiologist()) {
				buffer.append(
						"<label class='checkbox checkbox-custom-alt m-0 mt-5'><input type='checkbox' checked='checked' id='isanesthesiologist' name='isanesthesiologist'><i></i> Anesthesiologist</label>");
			} else {
				buffer.append(
						"<label class='checkbox checkbox-custom-alt m-0 mt-5'><input type='checkbox' id='isanesthesiologist' name='isanesthesiologist'><i></i> Anesthesiologist</label>");
			}

			buffer.append("~");

			// buffer.append("<label
			// for='exampleInputPassword1'>Referred</label>");
			if (userProfile.isIsreferred()) {
				buffer.append(
						"<label class='checkbox checkbox-custom-alt m-0 mt-5'><input type='checkbox' checked='checked' id='isreferred' name='isreferred'><i></i> Referred</label>");
			} else {
				buffer.append(
						"<label class='checkbox checkbox-custom-alt m-0 mt-5'><input type='checkbox' id='isreferred' name='isreferred'><i></i> Referred</label>");
			}

			buffer.append("~");

			// buffer.append("<label for='exampleInputPassword1'>MLC</label>");
			if (userProfile.isIsmlc()) {
				buffer.append(
						"<label class='checkbox checkbox-custom-alt m-0 mt-5'><input type='checkbox' checked='checked' id='ismlc' name='ismlc'><i></i> MLC</label>");
			} else {
				buffer.append(
						"<label class='checkbox checkbox-custom-alt m-0 mt-5'><input type='checkbox' id='ismlc' name='ismlc'><i></i> MLC</label>");
			}

			buffer.append("~");
			buffer.append("" + userProfile.getQualification() + "");

			buffer.append("~");
			buffer.append("" + userProfile.getReference_shareammount() + "");

			buffer.append("~");
			buffer.append("<label for='exampleInputPassword1'>Reference Share Type <span class='red'>*</span></label>");
			buffer.append("<select id='refsharetype' name='refsharetype' class='form-control chosen'>");
			if (userProfile.getReference_sharetype() != null) {
				buffer.append("<option value='0'>%</option>");
				if (userProfile.getReference_sharetype().equals("1")) {
					buffer.append("<option value='1' selected>Rs.</option>");
				} else {
					buffer.append("<option value='1'>Rs.</option>");
				}
			} else {
				buffer.append("<option value='0'>%</option>");
				buffer.append("<option value='1'>Rs.</option>");
			}
			buffer.append("</select>");

			buffer.append("~");
			buffer.append("" + userProfile.getSharepercentage() + "");
			response.setContentType("text/html");
			response.setHeader("Cache-Control", "no-cache");
			response.getWriter().write("" + buffer.toString() + "");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			connection.close();
		}
		return null;
	}

	public String updatenewconsultant() throws Exception {
		Connection connection = null;
		try {
			StringBuffer buffer = new StringBuffer();
			connection = Connection_provider.getconnection();
			UserProfileDAO userProfileDAO = new JDBCUserProfileDAO(connection);
			String diciplineName = request.getParameter("diciplineName");
			String mobileno = request.getParameter("mobileno");
			String visitingconfees = request.getParameter("visitingconfees");
			String consultantname = request.getParameter("consultantname");
			String isvisitingconsultant = request.getParameter("isvisitingcons");
			String issurgeon = request.getParameter("issurgeon");
			String isanesthesiologist = request.getParameter("isanesthesiologist");
			String isreferred = request.getParameter("isreferred");
			String existingdrid = request.getParameter("existingdr");
			String emailid = request.getParameter("emailid");
			String referid = request.getParameter("referid");
			String ismlc = request.getParameter("ismlc");
			String mlcqualification = request.getParameter("mlcqualification");
			String refsharetype = request.getParameter("refsharetype");
			String refshareammount = request.getParameter("refshareammount");
			String sharepercentage = request.getParameter("sharepercentage");
			String existingdr_update = request.getParameter("existingdr_update");
			UserProfile userProfile = new UserProfile();
			userProfile.setFullname(consultantname);
			userProfile.setMobile(mobileno);
			userProfile.setEmail(emailid);
			userProfile.setDiciplineName(diciplineName);
			userProfile.setFees(visitingconfees);
			userProfile.setIsvisitingconsultant(Boolean.parseBoolean(isvisitingconsultant));
			userProfile.setIssurgeon(Boolean.parseBoolean(issurgeon));
			userProfile.setIsanesthesiologist(Boolean.parseBoolean(isanesthesiologist));
			userProfile.setIsreferred(Boolean.parseBoolean(isreferred));
			userProfile.setExistingdrid(existingdr_update);
			userProfile.setReferid(referid);
			userProfile.setIsmlc(Boolean.parseBoolean(ismlc));
			userProfile.setQualification(mlcqualification);
			userProfile.setRefshareammount(refshareammount);
			userProfile.setRefsharetype(refsharetype);
			userProfile.setSharepercentage(sharepercentage);
			int res = userProfileDAO.updateReferralDoctor(userProfile);
			// int res =
			// userProfileDAO.saveVisitingCosultant(userProfile,loginInfo.getId(),loginInfo.getClinicName());
			response.setContentType("text/html");
			response.setHeader("Cache-Control", "no-cache");
			response.getWriter().write("" + buffer.toString() + "");
		} catch (Exception e) {

			e.printStackTrace();
		} finally {
			connection.close();
		}
		return null;
	}

	public String setvisitingconsultantfees() throws Exception {
		Connection connection = null;
		try {
			connection = Connection_provider.getconnection();
			UserProfileDAO profileDAO = new JDBCUserProfileDAO(connection);
			StringBuffer buffer = new StringBuffer();
			String id = request.getParameter("id");
			UserProfile userProfile = profileDAO.getrefereddrinfo(id);
			buffer.append("" + userProfile.getFees() + "");
			buffer.append("~");

			buffer.append("" + id + "");
			response.setContentType("text/html");
			response.setHeader("Cache-Control", "no-cache");
			response.getWriter().write("" + buffer.toString() + "");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			connection.close();
		}
		return null;
	}

	public String addvisiting() throws Exception {

		Connection connection = null;

		try {
			connection = Connection_provider.getconnection();
			IpdDAO ipdDAO = new JDBCIpdDAO(connection);
			UserProfileDAO profileDAO = new JDBCUserProfileDAO(connection);
			BedDao bedDao = new JDBCBedDao(connection);
			ClientDAO clientDAO = new JDBCClientDAO(connection);
			Bed bed = new Bed();
			String doctors = request.getParameter("doctors");
			for (String doctorId : doctors.split(",")) {
				if (doctorId.equals("0")) {
					continue;
				}
				bed.setPractitionerid(doctorId);
				bed.setDate(DateTimeUtils.getCommencingDate1(ipdForm.getDate()));
				bed.setTime(ipdForm.getTime());
				bed.setFees(ipdForm.getFees());
				String clientid=ipdForm.getClientid();
				bed.setClientid(ipdForm.getClientid());
				String ipdid = ipdDAO.getActiveIpdIdFromClientId(bed.getClientid());
				bed.setIpdid(ipdid);
				bed.setStatus("0");
				bed.setPayment("0");
				bed.setTds(ipdForm.getTds());
				Bed bed2 = new Bed();
				//Wrong data show
				//bed2 = bedDao.getIpdDetails(clientid);
				
				//New data called properly
				bed2 = bedDao.getEditIpdData(ipdid);
				bed.setBedid(bed2.getBedid());
				bed.setWardid(bed2.getWardid());
				
				UserProfile profile = new UserProfile();
				profile = profileDAO.getrefereddrinfo(doctorId);
				bed.setSharePercent(profile.getSharepercentage());
				int result = ipdDAO.addVisitingConsult(bed);
				if (result > 0) {
					String date = ipdForm.getDate();
					String time = ipdForm.getTime();
					
					// String drname=profile.getName();
					Client client = new Client();
					
					client = clientDAO.getSelectedSessionClientDetails(clientid);
					String patientname = client.getClientName();
					bed2 = bedDao.getIpdDetails(clientid);
					String wardname = bedDao.getWardName(bed2.getWardid());
					String bedname = bedDao.getBedName(bed2.getBedid());
					SmsService s = new SmsService();
					String message = " Your visit has been scheduled for Patient " + patientname + " on " + date
							+ " at " + time + ". Ward No. " + wardname + " and Bed No. " + bedname + "";
					if (loginInfo.isSmsVisitingConslt()) {
						//temporay
						String templateid="";
						s.sendSms(message, profile.getMobile(), loginInfo, new EmailLetterLog(),templateid);
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			connection.close();
		}
		return "savevisit";
	}
	public String editipdvisit() throws Exception {
		   
		   Connection connection=null;
		   try {
			   connection=Connection_provider.getconnection();
			   IpdDAO ipdDAO=new JDBCIpdDAO(connection); 
			   String visitid=request.getParameter("visitid");
			   Bed bed=ipdDAO.getIpdVisitingConsult(visitid);
			   String data=visitid+"~"+bed.getPractitionerid()+"~"+DateTimeUtils.getCommencingDate1(bed.getDate())+"~"+bed.getTime()+"~"+bed.getFees()+"~"+bed.getClientid()+"~"+bed.getTds();
			   
			   
			   response.setContentType("text/html");
		       response.setHeader("Cache-Control", "no-cache");
		       response.getWriter().write(data);  
			   
		   } catch (Exception e) {

			e.printStackTrace();
		   }
		   
		   return null;
	   }
	
	 public String updatevisiting()throws Exception {
		 
		   Connection connection=null;
		   
		 try {
			 connection=Connection_provider.getconnection();
			 IpdDAO ipdDAO=new JDBCIpdDAO(connection);
			 Bed bed=new Bed();
			 bed.setId(ipdForm.getId());
			 bed.setPractitionerid(ipdForm.getVisitconslt());
			 bed.setTds(ipdForm.getTds());	
		     bed.setDate(DateTimeUtils.getCommencingDate1(ipdForm.getDate()));
			 bed.setTime(ipdForm.getTime());
			 bed.setFees(ipdForm.getFees());
			 int result=ipdDAO.updateVisitingConsult(bed);
			 
			
		} catch (Exception e) {

			e.printStackTrace();
		}
		finally{
			connection.close();
		}
		   
		   return "savevisit";
	   }
	 
	 public String getconsulatntcharge() throws Exception {
			Connection connection = null;
			try {
				connection = Connection_provider.getconnection();
				IpdDAO ipdDAO = new JDBCIpdDAO(connection);
				StringBuffer buffer = new StringBuffer();
				String id = request.getParameter("id");
				UserProfile userProfile = ipdDAO.getVisitedDrInfor(id);
				String visitingper = userProfile.getSharepercentage();
				if(DateTimeUtils.isNull(visitingper).equals("")){
					visitingper = ipdDAO.getVistingDrPer(userProfile.getDoctor());
				}else if(DateTimeUtils.numberCheck(visitingper).equals("0")){
					visitingper = ipdDAO.getVistingDrPer(userProfile.getDoctor());
				}
				
				buffer.append("" + userProfile.getFees() + "");
				buffer.append("~");
				buffer.append("" + userProfile.getTds() + "");
				buffer.append("~");
				
				double x = (Integer.parseInt(userProfile.getFees())/100)*Integer.parseInt(visitingper);
				x = Math.round(x * 100.0) / 100.0;
				
				int total = 100-Integer.parseInt(userProfile.getTds());
				double aftertds = (x/100)*total;
				aftertds = Math.round(aftertds * 100.0) / 100.0;
				
				buffer.append(""+x+"");
				buffer.append("~");
				buffer.append(""+aftertds+"");
				buffer.append("~");
				buffer.append(""+id+"");
				buffer.append("~");
				buffer.append(""+visitingper+"");
				
				response.setContentType("text/html");
				response.setHeader("Cache-Control", "no-cache");
				response.getWriter().write("" + buffer.toString() + "");
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				connection.close();
			}
			return null;
		}
	 
	 public String updatevisitedornot() throws Exception {
			
			Connection connection=null;
			try {
				connection=Connection_provider.getconnection();
				CompleteAptmDAO completeAptmDAO=new JDBCCompleteAptmDAO(connection);
				UserProfileDAO userProfileDAO=new JDBCUserProfileDAO(connection);
				MasterDAO masterDAO=new JDBCMasterDAO(connection);
				ClientDAO clientDao=new JDBCClientDAO(connection);
				IpdDAO ipdDAO=new JDBCIpdDAO(connection);
				
				String visitid=request.getParameter("id");
				String status=request.getParameter("status");
				
				String ipdid=request.getParameter("ipdid");
				String clientid=request.getParameter("clientid");
				
				int result=ipdDAO.updatePractitionerVisitedorNot(visitid,status);
				
				Bed visitcharge=ipdDAO.getIpdVisitingConsult(visitid);
				int apm_userid = userProfileDAO.getPractIdFromReferenceId(visitcharge.getPractitionerid());
				Client client=clientDao.getClientDetails(clientid);
				
				int invoiceid=0;
				
				String date1 = DateTimeUtils.getDateinSimpleFormate(new Date());
				String stemp[] = date1.split(" ");
				
				String temp[] = stemp[0].split("-");
				date1 = temp[2] + "-" + temp[1] + "-" + temp[0];
				
				CompleteAppointment appointment=new CompleteAppointment();
				appointment.setClientId(clientid);
				//appointment.setPractitionerId(visitcharge.getPractitionerid());
				appointment.setPractitionerId(""+apm_userid);
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
			    		    
			    //UserProfile userProfile=userProfileDAO.getUserprofileDetails(Integer.parseInt(visitcharge.getPractitionerid()));
			    UserProfile userProfile=userProfileDAO.getUserprofileDetails(apm_userid);
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
			     
			    //int result0=ipdDAO.updateVisitingPaymentStatus(status,visitid);
				
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
	 
	 public String updateconsultantpaidstatus() throws Exception{

			Connection connection=null;
			try {
				
				connection=Connection_provider.getconnection();
				IpdDAO ipdDAO=new JDBCIpdDAO(connection);
				
				String visitid = request.getParameter("id");
				String total = request.getParameter("total");
				String userid = loginInfo.getUserId();
				String status = "1";
				String date = DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone());
				int res=ipdDAO.updateVisitingPaymentStatus(status,visitid,userid,date,total);
				
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
	 
	 public String deleteipdvisit()throws Exception {
		   
		   Connection connection=null;
		   try {
			   connection=Connection_provider.getconnection();
			   IpdDAO ipdDAO=new JDBCIpdDAO(connection); 
			   String visitid=request.getParameter("visitid");
			   
			   //int result=ipdDAO.deleteIpdVisitConsult(visitid);
			   String date = DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone());
			   int res = ipdDAO.cancelIpdVisitConsult(visitid,loginInfo.getUserId(),date);

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
							if(mdicinedays<=pr.getDays()){
								
							}else{
								continue;
							}
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
							if(mdicinedays<=pr.getDays()){
								
							}else{
								continue;
							}
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
		


}
