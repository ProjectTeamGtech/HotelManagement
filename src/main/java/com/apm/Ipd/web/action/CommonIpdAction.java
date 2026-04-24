package com.apm.Ipd.web.action;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.allcolor.yahp.converter.CYaHPConverter;
import org.allcolor.yahp.converter.IHtmlToPdfTransformer;
import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;

import com.apm.Accounts.eu.bi.AccountsDAO;
import com.apm.Accounts.eu.bi.AdditionalDAO;
import com.apm.Accounts.eu.blogic.jdbc.JDBCAccountsDAO;
import com.apm.Accounts.eu.blogic.jdbc.JDBCAdditionalDAO;
import com.apm.Accounts.eu.entity.Accounts;
import com.apm.Ambulance.eu.bi.DutyDAO;
import com.apm.Ambulance.eu.blogic.jdbc.JDBCDutyDAO;
import com.apm.Ambulance.eu.entity.Duty;
import com.apm.Appointment.eu.bi.AppointmentTypeDAO;
import com.apm.Appointment.eu.blogic.jdbc.JDBCAppointmentTypeDAO;
import com.apm.Appointment.eu.entity.AppointmentType;
import com.apm.Diagnosis.eu.bi.DiagnosisDAO;
import com.apm.Diagnosis.eu.blogic.jdbc.JDBCDiagnosisDAO;
import com.apm.Diagnosis.eu.entity.Diagnosis;
import com.apm.DiaryManagement.eu.bi.ClientDAO;
import com.apm.DiaryManagement.eu.bi.CompleteAptmDAO;
import com.apm.DiaryManagement.eu.bi.DiaryManagementDAO;
import com.apm.DiaryManagement.eu.bi.NotAvailableSlotDAO;
import com.apm.DiaryManagement.eu.blogic.jdbc.JDBCClientDAO;
import com.apm.DiaryManagement.eu.blogic.jdbc.JDBCCompleteAptmDAO;
import com.apm.DiaryManagement.eu.blogic.jdbc.JDBCDiaryManagentDAO;
import com.apm.DiaryManagement.eu.blogic.jdbc.JDBCNotAvailableSlotDAO;
import com.apm.DiaryManagement.eu.entity.Client;
import com.apm.DiaryManagement.eu.entity.CompleteAppointment;
import com.apm.DiaryManagement.eu.entity.DiaryManagement;
import com.apm.DiaryManagement.eu.entity.NotAvailableSlot;
import com.apm.DiaryManagement.eu.entity.Priscription;
import com.apm.DiaryManagement.web.action.EmbeddedImageEmailUtil;
import com.apm.DiaryManagement.web.action.SmsService;
import com.apm.Dietary.eu.entity.DietaryDetails;
import com.apm.Emr.eu.bi.EmrDAO;
import com.apm.Emr.eu.bi.InvestigationDAO;
import com.apm.Emr.eu.blogic.jdbc.JDBCEmrDAO;
import com.apm.Emr.eu.blogic.jdbc.JDBCInvestigationDAO;
import com.apm.Emr.eu.entity.Investigation;
import com.apm.Emr.eu.entity.InvstTemplate;
import com.apm.Expence.eu.bi.ExpenManagementDAO;
import com.apm.Expence.eu.bi.blogic.jdbc.JDBCExpenceManagementDAO;
import com.apm.Inventory.eu.bi.InventoryVendorDAO;
import com.apm.Inventory.eu.blogic.jdbc.JDBCInventoryVendorDAO;
import com.apm.Ipd.eu.bi.BedDao;
import com.apm.Ipd.eu.bi.IpdDAO;
import com.apm.Ipd.eu.bi.IpdLogDAO;
import com.apm.Ipd.eu.blogic.jdbc.JDBCBedDao;
import com.apm.Ipd.eu.blogic.jdbc.JDBCIpdDAO;
import com.apm.Ipd.eu.blogic.jdbc.JDBCIpdLogDAO;
import com.apm.Ipd.eu.entity.Bams;
import com.apm.Ipd.eu.entity.Bed;
import com.apm.Ipd.eu.entity.Discharge;
import com.apm.Ipd.eu.entity.Ipd;
import com.apm.Ipd.web.form.IpdForm;
import com.apm.Log.eu.entity.EmailLetterLog;
import com.apm.Master.eu.bi.DischargeOutcomeDAO;
import com.apm.Master.eu.bi.DischargeStatusDAO;
import com.apm.Master.eu.bi.MasterDAO;
import com.apm.Master.eu.bi.TreatmentTypeDAO;
import com.apm.Master.eu.blogic.jdbc.JDBCDischargeOutcomeDAO;
import com.apm.Master.eu.blogic.jdbc.JDBCDischargeStatus;
import com.apm.Master.eu.blogic.jdbc.JDBCMasterDAO;
import com.apm.Master.eu.blogic.jdbc.JDBCTreatmentTypeDAO;
import com.apm.Master.eu.entity.Master;
import com.apm.Master.eu.entity.TreatmentType;
import com.apm.Master.web.action.IpdFormSettingMasterAction;
import com.apm.Pharmacy.eu.bi.PharmacyDAO;
import com.apm.Pharmacy.eu.blogic.jdbc.JDBCPharmacyDAO;
import com.apm.Registration.eu.bi.ClinicDAO;
import com.apm.Registration.eu.bi.UserProfileDAO;
import com.apm.Registration.eu.blogic.jdbc.JDBCClinicDAO;
import com.apm.Registration.eu.blogic.jdbc.JDBCUserProfileDAO;
import com.apm.Registration.eu.entity.Clinic;
import com.apm.Registration.eu.entity.UserProfile;
import com.apm.Support.eu.bi.UserAdministartionDAO;
import com.apm.Support.eu.blogic.jdbc.JDBCUserAdministration;
import com.apm.ThirdParties.eu.bi.ThirdPartyDAO;
import com.apm.ThirdParties.eu.blogic.jdbc.JDBCThirdPartyDAO;
import com.apm.ThirdParties.eu.entity.ThirdParty;
import com.apm.Tools.web.action.AllTemplateAction;
import com.apm.TreatmentEpisode.eu.bi.TreatmentEpisodeDAO;
import com.apm.TreatmentEpisode.eu.blogic.jdbc.JDBCTreatmentEpisode;
import com.apm.TreatmentEpisode.eu.entity.TreatmentEpisode;
import com.apm.common.eu.blogic.jdbc.Connection_provider;
import com.apm.common.utils.CommonOpdIpdReport;
import com.apm.common.utils.DateTimeUtils;
import com.apm.common.web.action.BaseAction;
import com.apm.common.web.common.helper.LoginHelper;
import com.apm.common.web.common.helper.LoginInfo;
import com.apm.common.web.utils.PopulateList;
import com.apm.main.common.constants.Constants;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.Preparable;

import atg.taglib.json.util.JSONObject;


public class CommonIpdAction extends BaseAction implements ModelDriven<IpdForm>, Preparable {

	HttpServletRequest request = (HttpServletRequest) ActionContext.getContext().get(ServletActionContext.HTTP_REQUEST);
	HttpServletResponse response = (HttpServletResponse) ActionContext.getContext()
			.get(ServletActionContext.HTTP_RESPONSE);
	HttpSession session = request.getSession(true);
	LoginInfo loginInfo = LoginHelper.getLoginInfo(request);

	IpdForm ipdForm = new IpdForm();

	public void prepare() throws Exception {
		Connection connection = null;
		try {
			connection = Connection_provider.getconnection();
			PharmacyDAO pharmacyDAO = new JDBCPharmacyDAO(connection);
			NotAvailableSlotDAO notAvailableSlotDAO = new JDBCNotAvailableSlotDAO(connection);
			UserProfileDAO userProfileDAO = new JDBCUserProfileDAO(connection);
			MasterDAO masterDAO = new JDBCMasterDAO(connection);
			ArrayList<Master> requestlocationlist= pharmacyDAO.getAllLocationNew();
			ipdForm.setRequestlocationlist(requestlocationlist);
			if(loginInfo.isPrisc_location_list()){
				int default_location = pharmacyDAO.getByDefaultPharmacyLocation();
				ipdForm.setRequestlocationid(""+default_location);
			}
			
			ArrayList<Master> otdepartmentList = notAvailableSlotDAO.getotDepartmenrList();
			ipdForm.setOtdepartmentList(otdepartmentList);
			UserProfile userProfile = userProfileDAO.getUserprofileDetails(loginInfo.getId());
			ArrayList<Master> procedureList = notAvailableSlotDAO.getProcedureList(userProfile.getDiciplineName());
			ipdForm.setProcedureList(procedureList);
			ArrayList<Master> disciplineList = masterDAO.getDisciplineDataList();
			ipdForm.setDisciplineList(disciplineList);
			ipdForm.setCountry("India");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public IpdForm getModel() {
		return ipdForm;
	}

	public String status() throws Exception {
		String treatmentepisodeid = request.getParameter("ipdtreatmentepisodeid");
		String clientid = request.getParameter("clientid");
		Connection connection = null;
		try {
			connection = Connection_provider.getconnection();
			IpdDAO ipdDAO = new JDBCIpdDAO(connection);
			ClientDAO clientDAO = new JDBCClientDAO(connection);

			Discharge discharge = ipdDAO.getDischargeDetails(treatmentepisodeid);
			Client client = clientDAO.getClientDetails(clientid);
			String fullname = client.getTitle() + " " + client.getFirstName() + " " + client.getLastName();

			String list1 = ipdDAO.getDischargeChecklistDataText(treatmentepisodeid, clientid, "1", discharge);
			String list2 = ipdDAO.getDischargeChecklistDataText(treatmentepisodeid, clientid, "2", discharge);
			String list3 = ipdDAO.getDischargeChecklistDataText(treatmentepisodeid, clientid, "3", discharge);
			String list4 = ipdDAO.getDischargeChecklistDataText(treatmentepisodeid, clientid, "4", discharge);

			String str = discharge.getDis_form_status() + "~" + discharge.getDis_form_time() + "~"
					+ discharge.getDis_mdicine_status() + "~" + discharge.getDis_mdicine_time() + "~"
					+ discharge.getDis_bill_status() + "~" + discharge.getDis_bill_time() + "~"
					+ discharge.getDis_nursing_status() + "~" + discharge.getDis_nursing_time() + "~"
					+ discharge.getDis_initiate_status() + "~" + discharge.getDis_initiate_time() + "~" + fullname;

			StringBuilder builder = new StringBuilder();
			builder.append("" + discharge.getDis_form_status() + "");
			builder.append("~");
			builder.append("" + discharge.getDis_form_time() + "");
			builder.append("~");
			builder.append("" + discharge.getDis_mdicine_status() + "");
			builder.append("~");
			builder.append("" + discharge.getDis_mdicine_time() + "");
			builder.append("~");
			builder.append("" + discharge.getDis_bill_status() + "");
			builder.append("~");
			builder.append("" + discharge.getDis_bill_time() + "");
			builder.append("~");
			builder.append("" + discharge.getDis_nursing_status() + "");
			builder.append("~");
			builder.append("" + discharge.getDis_nursing_time() + "");
			builder.append("~");
			builder.append("" + discharge.getDis_initiate_status() + "");
			builder.append("~");
			builder.append("" + discharge.getDis_initiate_time() + "");
			builder.append("~");
			builder.append("" + fullname + "");
			builder.append("~");
			builder.append("<ul>");
			builder.append("  " + list1);
			builder.append("</ul>");

			builder.append("~");
			builder.append("<ul>");
			if (discharge.getDis_mdicine_status().equals("0")) {
				builder.append(
						"<li class='setlimet'><label class='checkbox checkbox-custom-alt'><input onclick='selectAllChecklist(this.value,this.checked,"
								+ treatmentepisodeid
								+ ")' value='akashbclass' type='checkbox'><i></i>Select All</label></li>");
			} else {
				builder.append(
						"<li class='setlimet'><label class='checkbox checkbox-custom-alt'><input type='checkbox'><i></i>Select All</label></li>");
			}
			builder.append(" " + list2);
			builder.append("</ul>");

			builder.append("~");
			builder.append("<ul>");
			if (discharge.getDis_bill_status().equals("0")) {
				builder.append(
						"<li class='setlimet'><label class='checkbox checkbox-custom-alt'><input onclick='selectAllChecklist(this.value,this.checked,"
								+ treatmentepisodeid
								+ ")' value='akashcclass' type='checkbox'><i></i>Select All</label></li>");
			} else {
				builder.append(
						"<li class='setlimet'><label class='checkbox checkbox-custom-alt'><input type='checkbox'><i></i>Select All</label></li>");
			}

			builder.append(" " + list3);
			builder.append("</ul>");

			builder.append("~");
			builder.append("<ul>");
			if (discharge.getDis_nursing_status().equals("0")) {
				builder.append(
						"<li class='setlimet'><label class='checkbox checkbox-custom-alt'><input onclick='selectAllChecklist(this.value,this.checked,"
								+ treatmentepisodeid
								+ ")' value='akashdclass' type='checkbox'><i></i>Select All</label></li>");
			} else {
				builder.append(
						"<li class='setlimet'><label class='checkbox checkbox-custom-alt'><input type='checkbox'><i></i>Select All</label></li>");
			}
			builder.append(" " + list4);
			builder.append("</ul>");

			response.setContentType("text/html");
			response.setHeader("Cache-Control", "no-cache");
			// response.getWriter().write(""+str.toString()+"");
			response.getWriter().write("" + builder.toString() + "");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			connection.close();
		}
		return null;
	}

	public String getipdtemplate() throws Exception {

		Connection connection = null;

		try {
			request.setCharacterEncoding("UTF-8");
			response.setCharacterEncoding("UTF-8");
			connection = DriverManager.getConnection(
					"" + Constants.DB_HOST + ":3306/" + loginInfo.getClinicUserid()
							+ "?useUnicode=true&characterEncoding=UTF-8",
					"" + Constants.DB_USER + "", "" + Constants.DB_PWD + "");
			MasterDAO masterDAO = new JDBCMasterDAO(connection);
			String id = request.getParameter("id");

			Master master = masterDAO.getIpdTemplate(id);

			response.setContentType("text/html");
			 response.setCharacterEncoding("UTF-8");
			response.setHeader("Cache-Control", "no-cache");
			response.getWriter().write("" + master.getText() + "");

		} catch (Exception e) {

			e.printStackTrace();

		} finally {
			connection.close();
		}

		return null;

	}

	public String getproceduretemplate()throws Exception{
		
		   Connection connection = null;
		try {
			connection = Connection_provider.getconnection();
			MasterDAO masterDAO = new JDBCMasterDAO(connection);
			String id = request.getParameter("id");

			Master master = masterDAO.getProcedureTemplate(id);

			response.setContentType("text/html");
			 response.setCharacterEncoding("UTF-8");
			response.setHeader("Cache-Control", "no-cache");
			response.getWriter().write("" + master.getTemplate_nameid() + "");

		} catch (Exception e) {

			e.printStackTrace();

		} finally {
			connection.close();
		}

		return null;
		
	}
	
	public String updatedischarge() throws Exception {

		if (!verifyLogin(request)) {
			return "login";
		}
		Connection connection = null;

		try {
			request.setCharacterEncoding("UTF-8");
			response.setCharacterEncoding("UTF-8");
			connection = DriverManager.getConnection(
					"" + Constants.DB_HOST + ":3306/" + loginInfo.getClinicUserid()
							+ "?useUnicode=true&characterEncoding=UTF-8",
					"" + Constants.DB_USER + "", "" + Constants.DB_PWD + "");
			//connection = Connection_provider.getconnection();
			IpdDAO ipdDAO = new JDBCIpdDAO(connection);
			EmrDAO emrDAO = new JDBCEmrDAO(connection);
			BedDao bedDao = new JDBCBedDao(connection);
			MasterDAO masterDAO = new JDBCMasterDAO(connection);
			UserProfileDAO userProfileDAO = new JDBCUserProfileDAO(connection);
			String sessionadmissionid = ipdForm.getClientip();

//			System.out.println(sessionadmissionid);

			session.setAttribute("qipd", sessionadmissionid);
			Bed bed = bedDao.getEditIpdData(sessionadmissionid);
			String patientid = bed.getClientid();
			String treatmentEpisodeid = bed.getTreatmentepisodeid();
			String condition = bed.getConditionid();
			String practionerId = bed.getPractitionerid();
			String otnotesid = ipdForm.getOtNotesids();
			String priscids = ipdForm.getPriscid();

			for (String notesid : otnotesid.split(",")) {
				if (notesid.equals("0")) {
					continue;
				}
				int otnote = ipdDAO.updateotnotesid(notesid);
			}
			TreatmentTypeDAO treatmentTypeDAO = new JDBCTreatmentTypeDAO(connection);
			ArrayList<TreatmentType> conditionlist = new ArrayList<TreatmentType>();

			ipdForm.setConditionlist(conditionlist);

			session.setAttribute("sessionselectedclientid", bed.getClientid());

			// update status

			String datetime = DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone());
			int valuetoupdate = 1;

			int update = ipdDAO.updateInitialDischargeStatus("dis_form_status", "dis_form_time", valuetoupdate,
					datetime, bed.getTreatmentepisodeid(), loginInfo.getUserId());

			/*
			 * Bed bed = new Bed();
			 * bed.setHospitalcourse(ipdForm.getHospitalcourse());
			 * 
			 * 
			 * bed.setDischargedate(dischargedate);
			 * bed.setHour(ipdForm.getHour());
			 * bed.setMinute(ipdForm.getMinute());
			 * bed.setDischargeStatus(ipdForm.getDischargeStatus());
			 * bed.setDischrgeOutcomes(ipdForm.getDischrgeOutcomes());
			 * bed.setDiscadvnotes(ipdForm.getDiscadvnotes());
			 * bed.setChkDischarge(ipdForm.isChkDischarge());
			 */

			Bed bed2 = new Bed();
			bed2.setDischrgeOutcomes(ipdForm.getDischrgeOutcomes());
			bed2.setDischargeStatus(ipdForm.getDischargeStatus());
			// bed2.setTreatmentepisodeid(ipdForm.getHospitalcourse());
			String hcourse = ipdForm.getHospitalcourse();
			bed2.setHospitalcourse(new String(DateTimeUtils.isNull(ipdForm.getHospitalcourse()).getBytes("iso-8859-1"), "UTF-8"));
			bed2.setDiscadvnotes(new String(DateTimeUtils.isNull(ipdForm.getDiscadvnotes()).getBytes("iso-8859-1"), "UTF-8"));
			bed2.setFindondischarge(new String(DateTimeUtils.isNull(ipdForm.getFindondischarge()).getBytes("iso-8859-1"), "UTF-8"));
			bed2.setTreatmentgiven(new String(DateTimeUtils.isNull(ipdForm.getTreatmentgiven()).getBytes("iso-8859-1"), "UTF-8"));
			bed2.setInvestigation(new String(DateTimeUtils.isNull(ipdForm.getInvestigation()).getBytes("iso-8859-1"), "UTF-8"));
			bed2.setOtNotes(new String(DateTimeUtils.isNull(ipdForm.getOtNotes()).getBytes("iso-8859-1"), "UTF-8"));
			String appt="";
			for(String s : ipdForm.getAppointmentText().split(",")){
				if(s.equals("0")){
					continue;
				}
				if(appt.equals("")){
					appt=s;
				}else{
					appt=appt+","+s;
				}
				
			}
			bed2.setAppointmentText(appt);
			bed2.setAnesthesia(ipdForm.getAnesthesia());
			bed2.setSurgeon(ipdForm.getSurgeon());
			bed2.setAnesthesiologist(ipdForm.getAnesthesiologist());
			bed2.setTreatmentepisodeid(treatmentEpisodeid);
			bed2.setExample(new String(DateTimeUtils.isNull(ipdForm.getExample()).getBytes("iso-8859-1"), "UTF-8"));
			bed2.setDischargebyid(loginInfo.getUserId());
			bed2.setDeathnote(new String(DateTimeUtils.isNull(ipdForm.getDeathnote()).getBytes("iso-8859-1"), "UTF-8"));
			bed2.setEmergencydetail(new String(DateTimeUtils.isNull(ipdForm.getEmergencydetail()).getBytes("iso-8859-1"), "UTF-8"));
			bed2.setOndiet(new String(DateTimeUtils.isNull(ipdForm.getOndiet()).getBytes("iso-8859-1"), "UTF-8"));
			if(loginInfo.isNew_aureus_discard()){
				String dietryDetails=ipdForm.getNewCardFields().getDietary_advice();
				int r=bedDao.saveNewFieldsData(sessionadmissionid, "dietary_advice", dietryDetails);
				 r=bedDao.saveNewFieldsData(sessionadmissionid, "local_relevant_area", ipdForm.getNewCardFields().getLocal_relevant_area());
				 r=bedDao.saveNewFieldsData(sessionadmissionid, "tubes_and_training", ipdForm.getNewCardFields().getTubes_and_training());
				 r=bedDao.saveNewFieldsData(sessionadmissionid, "line_tube_drains", ipdForm.getNewCardFields().getLine_tube_drains());
				 r=bedDao.saveNewFieldsData(sessionadmissionid, "when_to_get_help", ipdForm.getNewCardFields().getWhen_to_get_help());
				 r=bedDao.saveNewFieldsData(sessionadmissionid, "general_other", ipdForm.getNewCardFields().getGeneral_other());
				 r=bedDao.saveNewFieldsData(sessionadmissionid, "call_for_appointmant", ipdForm.getNewCardFields().getCall_for_appointmant());
				 r=bedDao.saveNewFieldsData(sessionadmissionid, "consent_sign", ipdForm.getNewCardFields().getConsent_sign());
			}
				
			 
			 
			int status = 0;
			String dischargedate = DateTimeUtils.getCommencingDate1(ipdForm.getDischargedate()) + " "
					+ ipdForm.getHour() + ":" + ipdForm.getMinute() + ":20";

			bed2.setDischargedate(dischargedate);
			int disstatus = ipdDAO.gettreatmentstatus(bed.getTreatmentepisodeid());
			if (disstatus == 1) {
				status = 1;
			}
			// if(ipdForm.isChkDischarge()){
			// status = 1;
			// }

			if (status == 1) {
				int res = bedDao.updateBedStatus(sessionadmissionid);
				int log = bedDao.saveDischargeLog(sessionadmissionid, patientid, dischargedate, "", loginInfo);
			}

			bed2.setStatus("" + status);

			if (bed2.getAppointmentText() != null) {
				if (bed2.getAppointmentText().equals("0,") || bed2.getAppointmentText().equals("0, ")) {
					bed2.setAppointmentText("0");
				}
			}
			String test = bed2.getAppointmentText();
			if (status == 1) {

				/*
				 * int upd =
				 * emrDAO.updateTreatmentEpisodeSischargeStatus(ipdForm.
				 * getDischrgeOutcomes(),
				 * ipdForm.getDischargeStatus(),status,treatmentEpisodeid,
				 * dischargedate,
				 * ipdForm.getHospitalcourse(),ipdForm.getDiscadvnotes(),ipdForm
				 * .getFindondischarge(),ipdForm.getTreatmentgiven(),
				 * ipdForm.getInvestigation(),ipdForm.getOtNotes());
				 */
				int upd = emrDAO.updateTreatmentEpisodeDischargeForm(bed2);

				AllTemplateAction allTemplateAction = new AllTemplateAction();
				allTemplateAction.sendDischargeEmail(patientid, condition, practionerId, "0", loginInfo, connection,
						treatmentEpisodeid);
			} else {
				/*
				 * emrDAO.updateTreatmentEpisodeSischargeStatus(ipdForm.
				 * getDischrgeOutcomes(),
				 * ipdForm.getDischargeStatus(),status,treatmentEpisodeid,
				 * dischargedate,
				 * ipdForm.getHospitalcourse(),ipdForm.getDiscadvnotes(),ipdForm
				 * .getExample(),ipdForm.getFindondischarge(),ipdForm.
				 * getTreatmentgiven(),ipdForm.getInvestigation(),ipdForm.
				 * getOtNotes());
				 */
				int upd = emrDAO.updateTreatmentEpisodeDischargeForm(bed2);
			}
			Bed bed1=new Bed();
			bed1.setFakebackdate(ipdForm.getFromDate());
			bed1.setIpdid(sessionadmissionid);
			bed1.setPhysio(new String(DateTimeUtils.isNull(ipdForm.getPhysio()).getBytes("iso-8859-1"), "UTF-8"));
    		bed1.setClientid(bed.getClientid());
    		//save ipd physio data for bams
    		int re=bedDao.saveIpdPhsioDetails(bed1);
			// update discharge ot notes and data
			NotAvailableSlotDAO notAvailableSlotDAO = new JDBCNotAvailableSlotDAO(connection);
			NotAvailableSlot notAvailableSlot = new NotAvailableSlot();
			notAvailableSlot.setProcedure(ipdForm.getProcedure());
			notAvailableSlot.setAnesthesia(ipdForm.getAnesthesia());
			notAvailableSlot.setSurgeon(ipdForm.getSurgeon());
			notAvailableSlot.setOtnotes(ipdForm.getOtNotes());
			notAvailableSlot.setApmttypetext(ipdForm.getAppointmentText());
			ipdForm.setAnsintime(notAvailableSlot.getAnsintime());

			int res = notAvailableSlotDAO.updateDischargeOtData(sessionadmissionid, notAvailableSlot);

			datetime = DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone());

			// for update delete previous conditions @jitu
			res = ipdDAO.deleteFinalConditions(sessionadmissionid, treatmentEpisodeid);
			res = ipdDAO.deleteConditionReport(sessionadmissionid, treatmentEpisodeid);

			String allconditions = "0";
			if (ipdForm.getConditions() != null) {
				for (Bed bedcondition : ipdForm.getConditions()) {

					if (bedcondition == null) {
						continue;
					}
					allconditions = allconditions + "," + bedcondition.getConditionid();
					bedcondition.setTreatmentepisodeid(treatmentEpisodeid);
					bedcondition.setLastmodified(datetime);

					boolean isexist = bedDao.isIpdExistCondition(sessionadmissionid, treatmentEpisodeid,
							bedcondition.getConditionid());
					if (!isexist) {
						int dd = bedDao.addIpdConditionReport(Integer.parseInt(sessionadmissionid), bedcondition);
					}
				}
			}
			int result = ipdDAO.savefinalConditionDischarge(sessionadmissionid, treatmentEpisodeid, datetime,
					allconditions);

			// 23 jan 18   sms comment
			// autosmsDischarge(patientid);

			//   06 nov 2017
			String userid = loginInfo.getUserId();
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Calendar calendar = Calendar.getInstance();
			String todate = dateFormat.format(calendar.getTime());
			boolean flag = ipdDAO.getIsDisCheckListStatus("1", bed.getTreatmentepisodeid());

			if (!flag) {
				int res99 = ipdDAO.updateCheckListStatus("1", "1", bed.getTreatmentepisodeid(), userid, todate);
				int res1 = ipdDAO.updateCheckListStatus("1", "2", bed.getTreatmentepisodeid(), userid, todate);
				int res2 = ipdDAO.updateCheckListStatus("1", "3", bed.getTreatmentepisodeid(), userid, todate);
				int res3 = ipdDAO.updateCheckListStatus("1", "4", bed.getTreatmentepisodeid(), userid, todate);
				int disdataid1 = ipdDAO.getDisDataId("1", bed.getTreatmentepisodeid());
				int disdataid2 = ipdDAO.getDisDataId("2", bed.getTreatmentepisodeid());
				int disdataid3 = ipdDAO.getDisDataId("3", bed.getTreatmentepisodeid());
				int disdataid4 = ipdDAO.getDisDataId("4", bed.getTreatmentepisodeid());
				int res6 = ipdDAO.updateDischargeCKLIndi(disdataid1);
				int res7 = ipdDAO.updateDischargeCKLIndi(disdataid2);
				int res8 = ipdDAO.updateDischargeCKLIndi(disdataid3);
				int res9 = ipdDAO.updateDischargeCKLIndi(disdataid4);
			}

			if (ipdForm.getHospitalcourse() != null) {
				if (!ipdForm.getHospitalcourse().equals("")) {
					if (!ipdForm.getHospitalcourse().equals("<br>")) {

						boolean flag2 = ipdDAO.getIsDisCheckListStatus("5", bed.getTreatmentepisodeid());
						if (!flag2) {
							int res4 = ipdDAO.updateCheckListStatus("1", "5", bed.getTreatmentepisodeid(), userid,
									todate);
							int disdataid4 = ipdDAO.getDisDataId("5", bed.getTreatmentepisodeid());
							int res8 = ipdDAO.updateDischargeCKLIndi(disdataid4);
						} else {
							int res9 = ipdDAO.updateCheckListStatusSystemModify("1", "5", bed.getTreatmentepisodeid(),
									userid, todate, "true");
						}
					}
				}
			}
			for (String priscid : priscids.split(",")) {
				if (priscid.equals("0")) {
					continue;
				}
				int prisc = ipdDAO.updateprisc(priscid);
			}
			String invstids = ipdForm.getInvstids();
			/*
			 * for(String invstid: invstids.split(",") ){
			 * if(invstid.equals("0")){ continue; } int invst1=
			 * ipdDAO.updateinvst(invstid); }
			 */

			String rmonotesids = ipdForm.getRmonotesids();
			for (String rmonotesid : rmonotesids.split(",")) {
				if (rmonotesid.equals("0")) {
					continue;
				}
				int invst1 = ipdDAO.updateRMONotesDisplayed(rmonotesid);
			}

			// 29 NOV 2017  

			if (ipdForm.getDisdefaulttempname() != null) {
				if (!ipdForm.getDisdefaulttempname().equals("")) {
					String discharge_default_id = masterDAO.getIpdTemplateId("Discharge Default");
					int res5 = ipdDAO.saveIPDTemplate(ipdForm.getDisdefaulttempname(), discharge_default_id,
							ipdForm.getDepartment(), new String(DateTimeUtils.isNull(ipdForm.getExample()).getBytes("iso-8859-1"), "UTF-8"));
				}
			}
			if (ipdForm.getHospitalcoursetempname() != null) {
				if (!ipdForm.getHospitalcoursetempname().equals("")) {
					String hospital_course_id = masterDAO.getIpdTemplateId("Hospital Course");
					int res5 = ipdDAO.saveIPDTemplate(ipdForm.getHospitalcoursetempname(), hospital_course_id,
							ipdForm.getDepartment(),
							new String(DateTimeUtils.isNull(ipdForm.getHospitalcourse()).getBytes("iso-8859-1"), "UTF-8"));
				}
			}
			if (ipdForm.getNursingadvicetempname() != null) {
				if (!ipdForm.getNursingadvicetempname().equals("")) {
					String nursing_advice_id = masterDAO.getIpdTemplateId("Nursing Advice");
					int res5 = ipdDAO.saveIPDTemplate(ipdForm.getNursingadvicetempname(), nursing_advice_id,
							ipdForm.getDepartment(),
							new String(DateTimeUtils.isNull(ipdForm.getDiscadvnotes()).getBytes("iso-8859-1"), "UTF-8"));
				}
			}
			if (ipdForm.getInvestigationtempname() != null) {
				if (!ipdForm.getInvestigationtempname().equals("")) {
					String invenstigations = masterDAO.getIpdTemplateId("Investigations");
					int res5 = ipdDAO.saveIPDTemplate(ipdForm.getInvestigationtempname(), invenstigations,
							ipdForm.getDepartment(), 
							new String(DateTimeUtils.isNull(ipdForm.getInvestigation()).getBytes("iso-8859-1"), "UTF-8"));
				}
			}
			if (ipdForm.getFindingondistempname() != null) {
				if (!ipdForm.getFindingondistempname().equals("")) {
					String finding_on_discharge = masterDAO.getIpdTemplateId("FINDING ON DISCHARGE");
					int res5 = ipdDAO.saveIPDTemplate(ipdForm.getFindingondistempname(), finding_on_discharge,
							ipdForm.getDepartment(),
							new String(DateTimeUtils.isNull(ipdForm.getFindondischarge()).getBytes("iso-8859-1"), "UTF-8"));
				}
			}

			if (ipdForm.getPerinataltemp() != null) {
				if (!ipdForm.getPerinataltemp().equals("")) {
					String getPerinataltemp = masterDAO.getIpdTemplateId("Perinatal History");
					int res5 = ipdDAO.saveIPDTemplate(ipdForm.getPerinataltemp(), getPerinataltemp,
							ipdForm.getDepartment(), 
							new String(DateTimeUtils.isNull(ipdForm.getPerinatal_history()).getBytes("iso-8859-1"), "UTF-8")
							);
				}
			}
			
			if (ipdForm.getMaternalhisttemp() != null) {
				if (!ipdForm.getMaternalhisttemp().equals("")) {
					String getMaternalhisttemp = masterDAO.getIpdTemplateId("Maternal History");
					int res5 = ipdDAO.saveIPDTemplate(ipdForm.getMaternalhisttemp(), getMaternalhisttemp,
							ipdForm.getDepartment(), 
							new String(DateTimeUtils.isNull(ipdForm.getMaternal_history()).getBytes("iso-8859-1"), "UTF-8")
							);
				}
			}
			
			//UserProfile userProfile = userProfileDAO.getUserprofileDetails(Integer.parseInt(ipdForm.getPractitionerid()));
			if (ipdForm.getOperativetempname() != null) {
				if (!ipdForm.getOperativetempname().equals("")) {
					/*
					 * Master master = new Master();
					 * master.setOther_template_text(ipdForm.getOtNotes());
					 * master.setTitle(ipdForm.getOperativetempname());
					 * master.setDiscipline_id(userProfile.getDiciplineName());
					 * int res5 = masterDAO.addOtherTemplate(master);
					 */
					String surgical_template = masterDAO.getIpdTemplateId("OT Template");
					int res5 = ipdDAO.saveIPDTemplate(ipdForm.getOperativetempname(), surgical_template,
							ipdForm.getDepartment(), 
							new String(DateTimeUtils.isNull(ipdForm.getOtNotes()).getBytes("iso-8859-1"), "UTF-8")
							);
				}
			}
			if (ipdForm.getOndiettempname() != null) {
				if (!ipdForm.getOndiettempname().equals("")) {
					String getondiet = masterDAO.getIpdTemplateId("On Diet");
					int res5 = ipdDAO.saveIPDTemplate(ipdForm.getPerinataltemp(), getondiet,
							ipdForm.getDepartment(), 
							new String(DateTimeUtils.isNull(ipdForm.getPerinatal_history()).getBytes("iso-8859-1"), "UTF-8")
							);
				}
			}
			if (ipdForm.getPhysiotempname() != null) {
				if (!ipdForm.getPhysiotempname().equals("")) {
					String getPhysio = masterDAO.getIpdTemplateId("Physio");
					int res5 = ipdDAO.saveIPDTemplate(ipdForm.getPerinataltemp(), getPhysio,
							ipdForm.getDepartment(), 
							new String(DateTimeUtils.isNull(ipdForm.getPerinatal_history()).getBytes("iso-8859-1"), "UTF-8")
							);
				}
			}
			if (ipdForm.getKarmatempname() != null) {
				if (!ipdForm.getKarmatempname().equals("")) {
					String getKarma = masterDAO.getIpdTemplateId("Karma");
					int res5 = ipdDAO.saveIPDTemplate(ipdForm.getPerinataltemp(), getKarma,
							ipdForm.getDepartment(), 
							new String(DateTimeUtils.isNull(ipdForm.getPerinatal_history()).getBytes("iso-8859-1"), "UTF-8")
							);
				}
			}
			
			if (ipdForm.getProceduretempname() != null) {
				if (!ipdForm.getProceduretempname().equals("")) {
					String getProcedure = masterDAO.getIpdTemplateId("Procedurebams");
					int res5 = ipdDAO.saveIPDTemplate(ipdForm.getPerinataltemp(), getProcedure,
							ipdForm.getDepartment(), 
							new String(DateTimeUtils.isNull(ipdForm.getPerinatal_history()).getBytes("iso-8859-1"), "UTF-8")
							);
				}
			}
			//   27 Nov 2017
			String admissionfr = ipdForm.getAddmissionfor();
			String alergies = ipdForm.getAlergies();
			String chiefcomplatetempname = ipdForm.getChiefcomplatetempname();
			String presentillnesstempname = ipdForm.getPresentillnesstempname();
			Bed bed3 = new Bed();
			
			bed3.setAddmissionfor(new String(DateTimeUtils.isNull(ipdForm.getAddmissionfor()).getBytes("iso-8859-1"), "UTF-8"));
			bed3.setAlergies(new String(DateTimeUtils.isNull(ipdForm.getAlergies()).getBytes("iso-8859-1"), "UTF-8"));
			String str_chiefcompl=ipdForm.getChiefcomplains().replace("<br>","");
			bed3.setChiefcomplains(new String(DateTimeUtils.isNull(str_chiefcompl).getBytes("iso-8859-1"), "UTF-8"));
			
			bed3.setPresentillness(new String(DateTimeUtils.isNull(ipdForm.getPresentillness()).getBytes("iso-8859-1"), "UTF-8"));
			bed3.setIpdid(sessionadmissionid);
			bed3.setSuggestedtrtment(new String(DateTimeUtils.isNull(ipdForm.getSuggestedtrtment()).getBytes("iso-8859-1"), "UTF-8"));
			// bed3.setSuggestedtrtment(ipdForm.getTreatmenthistory());
			bed3.setEarlierinvest(new String(DateTimeUtils.isNull(ipdForm.getEarlierinvest()).getBytes("iso-8859-1"), "UTF-8"));
			bed3.setSurgicalnotes(new String(DateTimeUtils.isNull(ipdForm.getSurgicalnotes()).getBytes("iso-8859-1"), "UTF-8"));
			bed3.setDiethist(new String(DateTimeUtils.isNull(ipdForm.getDiethist()).getBytes("iso-8859-1"), "UTF-8"));
			bed3.setEmmunizationhist(new String(DateTimeUtils.isNull(ipdForm.getEmmunizationhist()).getBytes("iso-8859-1"), "UTF-8"));
			bed3.setDevelopmenthist(new String(DateTimeUtils.isNull(ipdForm.getDevelopmenthist()).getBytes("iso-8859-1"), "UTF-8"));
			bed3.setBirthhist(new String(DateTimeUtils.isNull(ipdForm.getBirthhist()).getBytes("iso-8859-1"), "UTF-8"));
			bed3.setHeadcircumference(new String(DateTimeUtils.isNull(ipdForm.getHeadcircumference()).getBytes("iso-8859-1"), "UTF-8"));
			bed3.setMidarmcircumference(new String(DateTimeUtils.isNull(ipdForm.getMidarmcircumference()).getBytes("iso-8859-1"), "UTF-8"));
			bed3.setLength(new String(DateTimeUtils.isNull(ipdForm.getLength()).getBytes("iso-8859-1"), "UTF-8"));
			bed3.setWtaddmission(new String(DateTimeUtils.isNull(ipdForm.getWtaddmission()).getBytes("iso-8859-1"), "UTF-8"));
			bed3.setWtdischarge(new String(DateTimeUtils.isNull(ipdForm.getWtdischarge()).getBytes("iso-8859-1"), "UTF-8"));

			// new datra
			bed3.setPersonalhist(new String(DateTimeUtils.isNull(ipdForm.getPersonalhist()).getBytes("iso-8859-1"), "UTF-8"));
			bed3.setFamily_history(new String(DateTimeUtils.isNull(ipdForm.getFamilyhist()).getBytes("iso-8859-1"), "UTF-8"));
			bed3.setPasthistory(new String(DateTimeUtils.isNull(ipdForm.getPasthistory()).getBytes("iso-8859-1"), "UTF-8"));
			bed3.setSurgicalnotes(new String(DateTimeUtils.isNull(ipdForm.getSurgicalnotes()).getBytes("iso-8859-1"), "UTF-8"));
			bed3.setOnexamination(new String(DateTimeUtils.isNull(ipdForm.getOnexamination()).getBytes("iso-8859-1"), "UTF-8"));
			bed3.setSuggestedtrtment(new String(DateTimeUtils.isNull(ipdForm.getSuggestedtrtment()).getBytes("iso-8859-1"), "UTF-8"));
			bed3.setEarlierinvest(new String(DateTimeUtils.isNull(ipdForm.getEarlierinvest()).getBytes("iso-8859-1"), "UTF-8"));

			// kunal
			String str_finaldiagnosis=ipdForm.getKunal_final_diagnosis_text().replace("<br>","");
			bed3.setKunal_final_diagnosis_text(new String(DateTimeUtils.isNull(str_finaldiagnosis).getBytes("iso-8859-1"), "UTF-8"));
			bed3.setKunal_manual_medicine_text(new String(DateTimeUtils.isNull(ipdForm.getKunal_manual_medicine_text()).getBytes("iso-8859-1"), "UTF-8"));
			bed3.setGstureage(new String(DateTimeUtils.isNull(ipdForm.getGstureage()).getBytes("iso-8859-1"), "UTF-8"));
			bed3.setWtonbirth(new String(DateTimeUtils.isNull(ipdForm.getWtonbirth()).getBytes("iso-8859-1"), "UTF-8"));
			bed3.setMaternal_history(new String(DateTimeUtils.isNull(ipdForm.getMaternal_history()).getBytes("iso-8859-1"), "UTF-8"));
			bed3.setPerinatal_history(new String(DateTimeUtils.isNull(ipdForm.getPerinatal_history()).getBytes("iso-8859-1"), "UTF-8"));
			bed3.setReasonlamadama(new String(DateTimeUtils.isNull(ipdForm.getLamadamareason()).getBytes("iso-8859-1"), "UTF-8"));
			bed3.setSecndryconsult(ipdForm.getSecndryconsult());
			int resultt = ipdDAO.updateAdmDataFromDisc(bed3);

			if (chiefcomplatetempname != null) {
				if (!chiefcomplatetempname.equals("")) {
					int res5 = ipdDAO.saveIPDTemplate(chiefcomplatetempname, "1", ipdForm.getDepartment(),
							new String(DateTimeUtils.isNull(ipdForm.getChiefcomplains()).getBytes("iso-8859-1"), "UTF-8")
							);
				}
			}
			if (presentillnesstempname != null) {
				if (!presentillnesstempname.equals("")) {
					int res5 = ipdDAO.saveIPDTemplate(presentillnesstempname, "2", ipdForm.getDepartment(),
							new String(DateTimeUtils.isNull(ipdForm.getPresentillness()).getBytes("iso-8859-1"), "UTF-8")
							);
				}
			}

			//   01 feb 18
			// If discharge form is fillled then by default, patient to be mark
			// d as discharge Initiated.
			/*
			 * String column="dis_initiate_status"; String
			 * column2="dis_initiate_time"; String datetime1 =
			 * DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone()); int
			 * valuetoupdate1 =
			 * ipdDAO.getValueToUpdate(column,bed.getTreatmentepisodeid());
			 * //beacuse it return vice versa i.e i fit return 1 that means it
			 * status 0 if(valuetoupdate1==1){ int update1 =
			 * ipdDAO.updateInitialDischargeStatus(column,column2,valuetoupdate1
			 * ,datetime1,bed.getTreatmentepisodeid()); }
			 */

			//   03 April 2018
			String totalotids = ipdForm.getTotalotids();
			if (totalotids != null) {
				for (String id : totalotids.split(",")) {
					if (id.equals("0")) {
						continue;
					}
					String editotprocedure = request.getParameter("editotprocedure" + id);
					int res4 = ipdDAO.updateDischrgeOTProcedure(editotprocedure, id);
				}
			}

			//   04 April 2018 prisc sr wise
			String totalchildmedids = ipdForm.getTotalchildmedids();
			if (totalchildmedids != null) {
				for (String id : totalchildmedids.split(",")) {
					if (id.equals("0")) {
						continue;
					}
					String dicpriscmedsrno = request.getParameter("dicpriscmed" + id);
					String dicpriscdose = request.getParameter("discpriscdose" + id);
					String dicpriscdays = request.getParameter("dicpriscdays" + id);
					int res5 = ipdDAO.updateDischrgePriscSrNo(dicpriscmedsrno, id, dicpriscdose, dicpriscdays);
				}
			}

			String followupdate = request.getParameter("followupdate1");
			String giveFollowUp = DateTimeUtils.numberCheck(request.getParameter("giveFollowUp"));
			if (!DateTimeUtils.isNull(followupdate).equals("") && giveFollowUp.equals("on")) {
				followupdate = DateTimeUtils.getCommencingDate1(followupdate);
				DateFormat dateFormat1 = new SimpleDateFormat("yyyy-MM-dd");
				Calendar cal = Calendar.getInstance();
				String date = dateFormat1.format(cal.getTime());
				Client client = new Client();

				client.setIpdid(sessionadmissionid);
				client.setClientId(bed.getClientid());
				client.setType("1");
				client.setFollowupdate(followupdate);
				client.setDate(date);
				client.setPractid(bed.getPractitionerid());
				client.setLocation("IPD Discharge");
				ClientDAO clientDAO = new JDBCClientDAO(connection);
				int followup =  clientDAO.savefollowup(client);
				String bkapmtipd = ipdForm.getBkapmtipd();
				if(followup>0 && bkapmtipd.equals("on")){
					//followupdate = DateTimeUtils.getCommencingDate1(followupdate);
					AccountsDAO accountsDAO = new JDBCAccountsDAO(connection);
					String whopay = clientDAO.getWhoPayName(patientid);
					AppointmentType appointmentType = accountsDAO.getFollowUpIdCharge(whopay);
					if (appointmentType.getCharges() != null) {
						NotAvailableSlot n = notAvailableSlotDAO.getMveDiaryUserDetails("" + practionerId, followupdate);

						String stime = n.getSTime();
						int slotid = n.getId();
						String duration = n.getDuration();
						boolean chkapmtexsist = notAvailableSlotDAO.chkmveapmtaxsist("" + practionerId,followupdate);
						if (chkapmtexsist) {
							stime = notAvailableSlotDAO.getmveapmtendtime("" + practionerId, followupdate);
						}
						SimpleDateFormat df = new SimpleDateFormat("HH:mm");
						Date d = df.parse(stime);
						Calendar cal1 = Calendar.getInstance();
						cal1.setTime(d);
						cal1.add(Calendar.MINUTE, 5);
						String endtime = df.format(cal1.getTime());

						Client client2 = clientDAO.getClientDetails(patientid);

						UserProfile user = userProfileDAO.getUserprofileDetails(DateTimeUtils.convertToInteger(practionerId));
						NotAvailableSlot notAvailableSlot1 = new NotAvailableSlot();
						notAvailableSlot1.setApmtSlotId(slotid);
						notAvailableSlot1.setCommencing(followupdate);
						notAvailableSlot1.setSTime(stime);
						notAvailableSlot1.setEndTime(endtime);
						notAvailableSlot1.setDiaryUserId((DateTimeUtils.convertToInteger(practionerId)));
						notAvailableSlot1.setDiaryUser(user.getFullname());
						notAvailableSlot1.setLocation("1");
						notAvailableSlot1.setDept(user.getDiciplineName());
						notAvailableSlot1.setClient(client2.getFullname());
						notAvailableSlot1.setClientId(patientid);
						notAvailableSlot1.setApmtDuration("00:05");
						notAvailableSlot1.setApmtType("" + appointmentType.getId());
						notAvailableSlot1.setRoom("Room1");
						notAvailableSlot1.setPayBy("Client");
						notAvailableSlot1.setAddedBy("" + loginInfo.getUserId());
						notAvailableSlot1.setCondition(user.getDiciplineName());
						notAvailableSlot1.setVaccineApmt(true);
						notAvailableSlot1.setNotes("");
						notAvailableSlot1.setTreatmentEpisodeId("0");
						notAvailableSlot1.setUsedsession("0");
						notAvailableSlot1.setOtid(0);
						notAvailableSlot1.setOtplan("" + 0);
						notAvailableSlot1.setProcedure("" + 0);
						notAvailableSlot1.setSurgeon("" + 0);
						notAvailableSlot1.setAnesthesia("" + 0);
						notAvailableSlot1.setIpdno("" + 0);
						notAvailableSlot1.setWardid("" + 0);
						notAvailableSlot1.setAssistaffcharge("" + 0);
						notAvailableSlot1.setSic("" + 0);
						notAvailableSlot1.setPsurcharge("" + 0);
						notAvailableSlot1.setPanetcharge("" + 0);
						notAvailableSlot1.setMobstatus("0");
						DateFormat dateFormat11 = new SimpleDateFormat("HH:mm:ss");
						Calendar cal2 = Calendar.getInstance();
						notAvailableSlot1.setOpdbooktime(dateFormat11.format(cal2.getTime()));
						notAvailableSlot1.setOtplan("0");
						notAvailableSlot1.setSpeciality(user.getDiciplineName());
						int x = notAvailableSlotDAO.saveAppointment(notAvailableSlot1);
					}
				
				}
			}
			int rest=bedDao.updateUseridInTable(loginInfo.getUserId(), bed.getTreatmentepisodeid(), "lastformfilleruserid");
			 
			//ipdDAO.createAdmissionView();
			//ipdDAO.createDischargeView();
			 
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			connection.close();
		}
		return "dischargesaved";

	}

	public String dynamictemplatesave() {
		try {
			//Connection connection = Connection_provider.getconnection();
			
			request.setCharacterEncoding("UTF-8");
			response.setCharacterEncoding("UTF-8");
			Connection connection = DriverManager.getConnection(
					"" + Constants.DB_HOST + ":3306/" + loginInfo.getClinicUserid()
							+ "?useUnicode=true&characterEncoding=UTF-8",
					"" + Constants.DB_USER + "", "" + Constants.DB_PWD + "");
			
			StringBuilder buffer = new StringBuilder();
			BufferedReader reader = request.getReader();
			String line;
			while ((line = reader.readLine()) != null) {
				buffer.append(line);
			}
			String data = buffer.toString();
			JSONObject jsonObject = new JSONObject(data);

			String id = jsonObject.getString("id");
			String name = jsonObject.getString("name");
			//String datatext = new String(jsonObject.getString("dataa").getBytes("iso-8859-1"), "UTF-8");
			String datatext = jsonObject.getString("dataa");
			//datattxet in hindi
			

			IpdDAO ipdDAO = new JDBCIpdDAO(connection);
			int x = ipdDAO.saveIpdTemplates(id, datatext, name);

			JSONObject jsonobj = new JSONObject();
			jsonobj.put("noob", "" + x);
			String response1 = jsonobj.toString();
			response.setContentType("application/json");
			response.setHeader("Cache-Control", "no-cache");
			response.getWriter().write("" + response1);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public String printdischarge() throws SQLException {

		if (!verifyLogin(request)) {
			return "login";
		}
		Connection connection = null;

		String clientid = request.getParameter("clientid");

		if (clientid == null) {
			clientid = (String) session.getAttribute("sessionselectedclientid");
		}

		try {

			// connection=Connection_provider.getconnection();
			connection = DriverManager.getConnection("" + Constants.DB_HOST + ":3306/" + loginInfo.getClinicUserid()
					+ "?useUnicode=true&characterEncoding=UTF-8", ""+Constants.DB_USER+"", ""+Constants.DB_PWD+"");
			ClientDAO clientDAO = new JDBCClientDAO(connection);
			UserProfileDAO userProfileDAO = new JDBCUserProfileDAO(connection);
			BedDao bedDao = new JDBCBedDao(connection);
			String tempid = DateTimeUtils.numberCheck(request.getParameter("selectedid"));
			/*if(tempid.equals("0")){
				
			}else{
				Bed b = bedDao.getEditIpdData(tempid);
				clientid=DateTimeUtils.isNull(clientid);
				if(clientid.equals("")||clientid.equals("0")){
					clientid=b.getClientid();
				}
			}*/
			
			if (clientid != null || !tempid.equals("0")) {
				IpdDAO ipdDAO = new JDBCIpdDAO(connection);
				
				String selectedid = "0";
				String reqselectedid = request.getParameter("selectedid");
				if (reqselectedid != null) {
					selectedid = reqselectedid;
				}else{
					selectedid = ipdDAO.getDischargedIpdid(clientid);
				}

				Bed bed = bedDao.getEditIpdData(selectedid);
				
				if(DateTimeUtils.isNull(clientid).equals("")){
					clientid = bed.getClientid();;
				}
				
				ipdForm.setLamadamareason(bed.getReasonlamadama());
				
				/*if (loginInfo.getIpd_abbr_access() == 1) {
					String newipdabbr = ipdDAO.getIpdAbrivationIds(Integer.parseInt(selectedid));
					ipdForm.setNewipdabbr(newipdabbr);
					if (Integer.parseInt(bed.getIpdseqno()) > 0) {
						ipdForm.setIpdseqno(bed.getIpdseqno());
					} else {
						ipdForm.setIpdseqno(selectedid);
					}
				} else {
					if (Integer.parseInt(bed.getIpdseqno()) > 0) {
						ipdForm.setIpdseqno(bed.getIpdseqno());
						ipdForm.setNewipdabbr(bed.getIpdseqno());
					} else {
						ipdForm.setIpdseqno(selectedid);
						ipdForm.setNewipdabbr(selectedid);
					}

				}
				*/
				Client client = clientDAO.getClientDetails(clientid);
				ipdForm.setAddress(client.getAddress());
				ipdForm.setAddr(client.getAddress() + "," + client.getTown() + "-" + client.getPostCode()+", "+DateTimeUtils.isNull(client.getCounty()) );
				ipdForm.setContact(client.getMobNo());
				
				ipdForm.setClient(clientid);

				ipdForm.setAgeonadmn(bed.getAgeonAdmn());
				UserProfile userProfile = userProfileDAO.getUserprofileDetails(Integer.parseInt(bed.getPractitionerid()));
				//Master discipline = masterDAO.getDisciplineData(userProfile.getDiciplineName());
				//ipdForm.setDiscipline(discipline.getDiscipline());
				ipdForm.setDoctor_name(userProfile.getInitial() + " " + userProfile.getFirstname() + " " + userProfile.getLastname());

				ipdForm.setQualification(DateTimeUtils.isNull(userProfile.getQualification()));

				ArrayList<Master> qualificationList= new ArrayList<Master>();
				
				for(String str:ipdForm.getQualification().split(",")){
					Master master= new Master();
					master.setName(str);
					qualificationList.add(master);
				}
				if(qualificationList.size()==0){
					Master master= new Master();
					master.setName(ipdForm.getQualification());
					qualificationList.add(master);
				}
				ipdForm.setQualificationList(qualificationList);
				
				/*String numcount = ipdDAO.getNumofAdmissionCount(clientid);
				ipdForm.setNum_admission(numcount);*/
				String str_finaldiagnosis=bed.getKunal_final_diagnosis_text().replace("<br>","");
				ipdForm.setKunal_final_diagnosis_text(str_finaldiagnosis);
				String medicine_text=bed.getKunal_manual_medicine_text().replace("<br>", "");
				ipdForm.setKunal_manual_medicine_text(medicine_text);
				/*String discadvoice = ipdDAO.getDIscPrisc(selectedid);
				ipdForm.setAdvoice(discadvoice);*/

				
				Bed bed1 = ipdDAO.getDischargeData(bed.getTreatmentepisodeid());
				ipdForm.setTreatmentEpisodeData(bed1);
				ipdForm.setChkDischarge(bed1.isChkDischarge());

				ipdForm.setEmergencydetail(bed1.getEmergencydetail());
				ipdForm.setHospitalcourse(bed1.getHospitalcourse());
				ipdForm.setHospitalcourse(bed1.getHospitalcourse());
				ipdForm.setEnddischargename(bed1.getEnddischargename());
				if (ipdForm.getHospitalcourse() != null) {
					if (ipdForm.getHospitalcourse().equals("") || ipdForm.getHospitalcourse().equals("<br>")) {
						ipdForm.setHospitalcourse(null);
					}
				}

				ipdForm.setDiscadvnotes(bed1.getDiscadvnotes());
				if (ipdForm.getDiscadvnotes() != null) {
					if (ipdForm.getDiscadvnotes().equals("") || ipdForm.getDiscadvnotes().equals("<br>")) {
						ipdForm.setDiscadvnotes(null);
					}
				}

				ipdForm.setExample(bed1.getExample());

				DischargeOutcomeDAO dao = new JDBCDischargeOutcomeDAO(connection);

				String discdate = "";
				if (bed1.getDischargedate() != null) {
					if (!bed1.getDischargedate().equals("")) {

						String temp[] = bed1.getDischargedate().split(" ");
						discdate = DateTimeUtils.getCommencingDate1(temp[0]) + " " + temp[1];
						if (loginInfo.isBalgopal()) {
							String time[] = temp[1].split(":");
							int hourOfDay = (Integer.parseInt(time[0]));
							int minute = (Integer.parseInt(time[1]));
							String apmpm = ((hourOfDay > 12) ? hourOfDay % 12 : hourOfDay) + ":"
									+ (minute < 10 ? ("0" + minute) : minute) + " " + ((hourOfDay >= 12) ? "PM" : "AM");
							discdate = DateTimeUtils.getCommencingDate1(temp[0]) + " " + apmpm;
						}

					}
				}

				ipdForm.setDischargedate(discdate);

				session.setAttribute("dischargeddata", ipdForm);

				Bed bedconditions = ipdDAO.getAllFinalCondition(selectedid, bed.getTreatmentepisodeid());

				ArrayList<Bed> finalConditions = new ArrayList<Bed>();

				if (bedconditions.getConditionname() != null) {

					for (String str : bedconditions.getConditionname().split(",")) {

						if (str.equals("0")) {

							continue;
						}

						int id = Integer.parseInt(str);
						String conditionname = bedDao.getIpdConditionName(str);
						Bed bed2 = new Bed();
						bed2.setId(id);
						bed2.setConditionname(conditionname);
						finalConditions.add(bed2);

					}

				}

				ipdForm.setFinalConditions(finalConditions);

				printformdata(selectedid,bed,bed1,client,1,userProfile);
				
				// get prescription list
				ArrayList<Priscription> dischargeParentPriscIds = ipdDAO.getDischargeParentPriscIds(selectedid);
	    		ArrayList<Priscription> dischargePriscList = new ArrayList<>();
				ArrayList<Priscription> treatmentivendischargePriscList = new ArrayList<>();
				String parentpriscid ="";
	    		String advoice="";
				String treatmentIds="0,";
				if(dischargeParentPriscIds.size()>0){
	    			//parentpriscid = ipdDAO.getParentPriscId(selectedid);
					Priscription priscription1  = dischargeParentPriscIds.get(dischargeParentPriscIds.size()-1);
					parentpriscid = ""+priscription1.getId();
					advoice = priscription1.getAdvoice();
					for (Priscription priscription : dischargeParentPriscIds) {
						if(priscription.getFromtreatmentgiven()==1){
							treatmentIds = treatmentIds + priscription.getId()+",";
						}
					}
					ArrayList<Priscription> dischargePriscriptionList = ipdDAO.getDichargePriscriptionList(dischargeParentPriscIds.get(dischargeParentPriscIds.size()-1).getIds(),"0");
	    			for (Priscription priscription : dischargePriscriptionList) {
						if(treatmentIds.contains(","+priscription.getParentid()+",")){
							treatmentivendischargePriscList.add(priscription);
						}else{
							dischargePriscList.add(priscription);
						}
					}
	    		}
				ipdForm.setAdvoice(advoice);
	    		ipdForm.setParentpriscid(parentpriscid);
				ipdForm.setDischargePriscList(dischargePriscList);
				session.setAttribute("priscList", dischargePriscList);
				ipdForm.setTreatmentivendischargePriscListt(treatmentivendischargePriscList);
				session.setAttribute("treatmentivendischargePriscList", treatmentivendischargePriscList);
				session.setAttribute("dischargePriscList", dischargePriscList);
				if (dischargePriscList.size() > 0) {
					Priscription pr = dischargePriscList.get(dischargePriscList.size() - 1);
					ipdForm.setStrengthflag(pr.isStrengthflag());
					ipdForm.setRemarkflag(pr.isRemarkflag());
					ipdForm.setQuantityflag(pr.isStrengthflag());
				}
				
				int actionType= DateTimeUtils.convertToInteger(bed.getAction());
				boolean isdaycare=false;
				if(actionType==2){
					isdaycare = true;
				}
				ipdForm.setDaycare(isdaycare);
				//ipdForm.setDaycare(bedDao.isDayCare(selectedid));
				NotAvailableSlotDAO notAvailableSlotDAO = new JDBCNotAvailableSlotDAO(connection);
				// String anethesia_name =
				// notAvailableSlotDAO.getDiaryUserName(bed1.getAnesthesia());
				/*
				 * String surgeon_name =
				 * notAvailableSlotDAO.getDiaryUserName(bed1.getSurgeon());
				 * String anesthesiologist_name =
				 * notAvailableSlotDAO.getDiaryUserName(bed1.getAnesthesiologist
				 * ());
				 */
				// String surgeon_name =
				// userProfileDAO.getReferalDrName(bed1.getSurgeon());
				String surgeon_name = notAvailableSlotDAO.getDiaryUserName(bed1.getSurgeon());
				String anesthesiologist_name = userProfileDAO.getReferalDrName(bed1.getAnesthesiologist());
				String anethesia_name = null;
				if (bed1.getAnesthesia() != null) {
					if (!bed1.getAnesthesia().equals("")) {
						if (bed1.getAnesthesia().equals("1")) {
							anethesia_name = "Local";
						} else if (bed1.getAnesthesia().equals("2")) {
							anethesia_name = "General";
						} else if (bed1.getAnesthesia().equals("3")) {
							anethesia_name = "Spinal";
						}
					}
				}
				ipdForm.setAnesthesia(anethesia_name);
				ipdForm.setSurgeon(surgeon_name);
				ipdForm.setAnesthesiologist(anesthesiologist_name);

				if (bed1.getEmergencydetail() != null) {
					if (bed1.getEmergencydetail().equals("") || bed1.getEmergencydetail().equals("<br>")) {
						ipdForm.setEmergencydetail(null);
					} else {
						ipdForm.setEmergencydetail(bed1.getEmergencydetail());
					}
				} else {
					ipdForm.setEmergencydetail(bed1.getEmergencydetail());
				}

				if (ipdForm.getAppointmentText() != null) {

					if (ipdForm.getAppointmentText().equals("") || ipdForm.getAppointmentText().equals("<br>")
							|| ipdForm.getAppointmentText().equals("0")) {
						ipdForm.setAppointmentText(null);
						/*
						 * Ipd ipd = ipdDAO.getProcedureName(selectedid);
						 * 
						 * String procedureid =
						 * ipdDAO.getProcedureId(ipd.getProcedurename());
						 * ipdForm.setAppointmentText(ipd.getProcedurename());
						 */

					}

				} /*
					 * else{ Ipd ipd = ipdDAO.getProcedureName(selectedid);
					 * 
					 * String procedureid =
					 * ipdDAO.getProcedureId(ipd.getProcedurename());
					 * ipdForm.setAppointmentText(ipd.getProcedurename()); }
					 */
				if (ipdForm.getSurgeon() != null) {
					if (ipdForm.getSurgeon().equals("") || ipdForm.getSurgeon().equals("<br>")
							|| ipdForm.getSurgeon().equals("0")) {
						ipdForm.setSurgeon(null);
					}

				}
				if (ipdForm.getAnesthesia() != null) {
					if (ipdForm.getAnesthesia().equals("") || ipdForm.getAnesthesia().equals("<br>")
							|| ipdForm.getAnesthesia().equals("0")) {
						ipdForm.setAnesthesia(null);
					}
				}
				if (ipdForm.getAnesthesiologist() != null) {
					if (ipdForm.getAnesthesiologist().equals("") || ipdForm.getAnesthesiologist().equals("<br>")
							|| ipdForm.getAnesthesiologist().equals("0")) {
						ipdForm.setAnesthesiologist(null);
					}
				}
				if (userProfile.getMobile() == "0" || userProfile.getMobile().equals("0")) {
					ipdForm.setDoctor_phone(null);
				} else {
					ipdForm.setDoctor_phone(userProfile.getMobile());
				}

				if (bed1.getDischrgeOutcomes() != null) {
					Master master = dao.getMaster(Integer.parseInt(bed1.getDischrgeOutcomes()));
					if (master.getName() != null) {
						ipdForm.setDischrgeOutcomes(master.getName());
					} else {
						ipdForm.setDischrgeOutcomes("");
					}

					int selectedid1 = loginInfo.getId();

					ClinicDAO clinicListDAO = new JDBCClinicDAO(connection);
					com.apm.Registration.eu.entity.Clinic cliniclist = clinicListDAO.getCliniclistDetails(selectedid1);
					ipdForm.setClinicName(cliniclist.getClinicName());

					DischargeStatusDAO ddao = new JDBCDischargeStatus(connection);
					master = ddao.getMaster(Integer.parseInt(bed1.getDischargeStatus()));

					ipdForm.setDischargeStatusId(bed1.getDischargeStatus());

					if (master.getName() != null) {
						ipdForm.setDischargeStatus(master.getName());
					} else {
						ipdForm.setDischargeStatus("");
					}
					DischargeStatusDAO statusDAO = new JDBCDischargeStatus(connection);
					String dischargehead = statusDAO.getDischargeStatusById(Integer.parseInt(bed1.getDischargeStatus()));
					if (dischargehead == null) {
						dischargehead = "";
					}
					ipdForm.setDischargehead(dischargehead);
				}
//				ArrayList<String> otaptid = ipdDAO.getAllOTIds(selectedid, clientid);

			
				/*
				 * ArrayList<Master> otnoteslist =new ArrayList<Master>(); for
				 * (String string : otaptid) { ArrayList<Master> otnoteslist1 =
				 * ipdDAO.getAllOtNotes(string);
				 * otnoteslist.addAll(otnoteslist1); }
				 * ipdForm.setOtnoteslist(otnoteslist);
				 */
				EmrDAO emrDAO=new JDBCEmrDAO(connection);
				InvestigationDAO investigationDAO=new JDBCInvestigationDAO(connection);
				DutyDAO dutyDAO=new JDBCDutyDAO(connection);
				ArrayList<Bams> mainArrayList =new ArrayList<>();
			
				if(loginInfo.isBams1() || loginInfo.isMbbs()) {
					if(bed1.getDischargedate().equals("")) {
						String currdate= DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone());
						bed1.setDischargedate(currdate);
					}
					
					long diff=DateTimeUtils.getDifferenceOfTwoDateDBFormat(bed.getAdmissiondate().split(" ")[0], bed1.getDischargedate().split(" ")[0]);
					
					for (int i = 0; i <= diff; i++) {
						Bams bams = new Bams();
						Date sdf = new SimpleDateFormat("yyyy-MM-dd").parse(bed.getAdmissiondate().split(" ")[0]);
						SimpleDateFormat sdformat = new SimpleDateFormat("yyyy-MM-dd");
						Calendar c = Calendar.getInstance();
						c.setTime(sdf); // Using today's date
						c.add(Calendar.DATE, i); // Adding 5 days
						String mainDate = sdformat.format(c.getTime());
						String fromdate = mainDate + " 00:00:00";
						String todate = mainDate + " 23:59:59";
						ArrayList<Bams> vitals = dao.getVitals(fromdate, todate, ipdForm.getClientid());
						bams.setVitalList(vitals);
						ArrayList<Priscription> priscriptionList = emrDAO.getPrintPriscListWIthDate(parentpriscid,
								loginInfo, fromdate, todate,ipdForm.getClientid());
						bams.setPriscList(priscriptionList);
						ArrayList<Investigation> investList = investigationDAO.getAllInvestigation(fromdate, todate,
								ipdForm.getId(),"0");
						bams.setInvestList(investList);
						ArrayList<Duty> punchKarma = dutyDAO.getPunchkarmaList(mainDate, mainDate, ipdForm.getId(),"0");
						bams.setPunchList(punchKarma);
						ArrayList<Bed> physioList=bedDao.getPhysioList(mainDate,ipdForm.getId(),"0");
						bams.setPhysioList(physioList);
						
						
						bams.setShowDate(DateTimeUtils.getInvoiceCommencingDate(mainDate));
						if (!( priscriptionList.isEmpty() && investList.isEmpty()
								&& punchKarma.isEmpty()&& physioList.isEmpty()&& vitals.isEmpty())) {
							mainArrayList.add(bams);
						}
					}
					int treatmentepisode_id=ipdDAO.getTreatmentId(ipdForm.getId());
					int treatmentstatus=ipdDAO.gettreatmentStatus(ipdForm.getClientid());
					Ipd ipd=ipdDAO.getDietData(treatmentepisode_id,ipdForm.getApmtId(),treatmentstatus);
					ipdForm.setDiet(ipd);
					ipdForm.setMainArrayList(mainArrayList);
					session.setAttribute("mainArrayList", mainArrayList);
					if(loginInfo.isMbbs()) {
						 Client client3=clientDAO.getConsentform();	
						 ipdForm.setDecData(client3);
						}else {
					Client client2=clientDAO.getDeclarationData(ipdForm.getId());
					ipdForm.setDecData(client2);
						}
					ArrayList<Accounts> dailynoteslist=ipdDAO.getdailynoteslist(""+ipdForm.getId(),ipdForm.getClientid(),bed.getAdmissiondate(),bed1.getDischargedate());
					ipdForm.setDailynoteslist(dailynoteslist);
				}
				
				
				ArrayList<Master> otdatesandids = ipdDAO.getOtDatesAndIds(selectedid, clientid);
				ipdForm.setOtdatesandids(otdatesandids);
				ipdForm.setClinicName(userProfile.getClinicname());

				
			} else {
				addActionError("Please Select Client!!");
			}

			Clinic clinic = new Clinic();
			ClinicDAO clinicDAO = new JDBCClinicDAO(connection);
			AccountsDAO accountsDAO = new JDBCAccountsDAO(connection);

			ArrayList<Clinic> locationAdressList = accountsDAO.getLocationAddress(loginInfo.getClinicid());

			clinic = clinicDAO.getCliniclistDetails(loginInfo.getClinicid());
			ipdForm.setClinicName(clinic.getClinicName());
			ipdForm.setClinicOwner(clinic.getClinicOwner());
			ipdForm.setOwner_qualification(clinic.getOwner_qualification());
			ipdForm.setLocationAdressList(locationAdressList);
			ipdForm.setMobile(clinic.getMobileNo());
			ipdForm.setAddress(clinic.getAddress());
			ipdForm.setLandLine(clinic.getLandLine());
			ipdForm.setClinicemail(clinic.getEmail());
			ipdForm.setWebsiteUrl(clinic.getWebsiteUrl());
			ipdForm.setClinicLogo(clinic.getUserImageFileName());
			session.setAttribute("clinicowner", clinic.getClinicOwner());
			if(loginInfo.isNew_aureus_discard()){
				
						
				return "print_aureus_dcard";
			}
			if(loginInfo.isBams1() || loginInfo.isMbbs()) {
				return "bams_discharge";
			}if(loginInfo.isTotehosp()) {
				return "tote_discharge";
			}
		}

		
		catch (Exception e) {
			e.printStackTrace();
		}

		finally {
			connection.close();
		}
		return "printdischarge";
	}

	/*public void printformdata(String selectedid) throws SQLException {

		Connection connection = null;

		try {

			connection = Connection_provider.getconnection();
			// String selectedid = request.getParameter("selectedid");
			ipdForm.setClientip(selectedid);
			ipdForm.setJsonipdid(selectedid);
			BedDao bedDao = new JDBCBedDao(connection);
			IpdDAO ipdDAO = new JDBCIpdDAO(connection);
			Bed bed = bedDao.getEditIpdData(selectedid);
			MasterDAO masterDAO = new JDBCMasterDAO(connection);
			IpdLogDAO ipdLogDAO = new JDBCIpdLogDAO(connection);
			NotAvailableSlotDAO notAvailableSlotDAO = new JDBCNotAvailableSlotDAO(connection);

			ipdForm.setLamadamareason(bed.getReasonlamadama());
			ipdForm.setCasualtyipd(bed.getAction());
			ipdForm.setDaycare(bedDao.isDayCare(selectedid));
			String dd[] = DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone()).split(" ");
			String printedBy = loginInfo.getUserId() + " " + DateTimeUtils.getCommencingDate1(dd[0]) + " " + dd[1];
			ipdForm.setPrintedBy(printedBy);
			if (loginInfo.getIpd_abbr_access() == 1) {
				String newipdabbr = ipdDAO.getIpdAbrivationIds(Integer.parseInt(selectedid));
				ipdForm.setNewipdabbr(newipdabbr);
				if (Integer.parseInt(bed.getIpdseqno()) > 0) {
					ipdForm.setIpdseqno(bed.getIpdseqno());
				} else {
					ipdForm.setIpdseqno(selectedid);
				}
			} else {
				if (Integer.parseInt(bed.getIpdseqno()) > 0) {
					ipdForm.setIpdseqno(bed.getIpdseqno());
					ipdForm.setNewipdabbr(bed.getIpdseqno());
				} else {
					ipdForm.setIpdseqno(selectedid);
					ipdForm.setNewipdabbr(selectedid);
				}
			}
			UserProfileDAO userProfileDAO = new JDBCUserProfileDAO(connection);
			UserProfile userProfile = userProfileDAO.getUserprofileDetails(Integer.parseInt(bed.getPractitionerid()));
			String practitionername = userProfile.getInitial() + " " + userProfile.getFirstname() + " "
					+ userProfile.getLastname();
			String specializationId = userProfile.getDiciplineName();
			ipdForm.setDepartment(userProfile.getSpecialization());

			if (userProfile.getDoctor() != null) {

				if (!userProfile.getDoctor().equals("") && !userProfile.getDoctor().equals("0")) {

					UserProfile supportiveDoctorDetails = userProfileDAO
							.getUserprofileDetails(Integer.parseInt(userProfile.getDoctor()));
					String supportiveDoctorName = supportiveDoctorDetails.getInitial() + " "
							+ supportiveDoctorDetails.getFirstname() + " " + supportiveDoctorDetails.getLastname();
					String supportiveQualification = supportiveDoctorDetails.getQualification();
					ipdForm.setSupportiveDoctorName(supportiveDoctorName);
					ipdForm.setSupportiveQualification(supportiveQualification);
				}
			}

			boolean issystemicreview = masterDAO.isIpdFormFieldActive(specializationId, "Systemic Review");
			boolean history = masterDAO.isIpdFormFieldActive(specializationId, "History");
			boolean obstretic_history = masterDAO.isIpdFormFieldActive(specializationId, "OBSTRETIC HISTORY");
			boolean menstrual_history = masterDAO.isIpdFormFieldActive(specializationId, "MENSTRUAL HISTORY");
			boolean substance_history = masterDAO.isIpdFormFieldActive(specializationId, "SUBSTANCE HISTORY");
			boolean verification = masterDAO.isIpdFormFieldActive(specializationId, "Verification");
			boolean pediatric = masterDAO.isIpdFormFieldActive(specializationId, "Paediatric History");

			ipdForm.setNicuaccess(masterDAO.isIpdFormFieldActive(specializationId, "NICU Setting"));
			ipdForm.setNicuaccess(pediatric);
			ipdForm.setVerification(verification);
			ipdForm.setIssystemicreview(issystemicreview);
			ipdForm.setHistory(history);
			ipdForm.setObstretic_history(obstretic_history);
			ipdForm.setMenstrual_history(menstrual_history);
			ipdForm.setSubstance_history(substance_history);
			ipdForm.setPaediatrichist(pediatric);

			ipdForm.setEditclientid(bed.getClientid());
			ipdForm.setClientid(bed.getClientid());
			ipdForm.setPractitionerid(practitionername);
			ipdForm.setConditionid(bed.getConditionid());
			ipdForm.setDepartment(bed.getDepartment());
			ipdForm.setSecndryconsult(bed.getSecndryconsult());
			ipdForm.setAddmitedbyuserid(bed.getAddmitedbyuserid());

			NotAvailableSlot notAvailableSlot = notAvailableSlotDAO.getOTDataByIpd(selectedid);
			ipdForm.setProcedure(notAvailableSlot.getProcedure());
			// ipdForm.setAnesthesia(notAvailableSlot.getAnesthesia());
			// ipdForm.setSurgeon(notAvailableSlot.getSurgeon());
			ipdForm.setOtNotes(notAvailableSlot.getOtnotes());
			session.setAttribute("ipdotnotes", notAvailableSlot.getOtnotes());
			// ipdForm.setAppointmentText(notAvailableSlot.getApmttypetext());
			// ipdForm.setAnsintime(notAvailableSlot.getAnsintime());

			if (bed.getRefferedby() != null) {

				if (bed.getRefferedby().equals("") || bed.getRefferedby().equals("0")) {
					bed.setRefferedby(null);
				}
			}

			ipdForm.setRefferedby(bed.getRefferedby());

			String wardname = ipdDAO.getIpdWardName(bed.getWardid());
			String bedname = ipdDAO.getIpdBedName(bed.getBedid());

			ipdForm.setWardid(wardname);
			ipdForm.setBedid(bedname);

			ipdForm.setTpid(bed.getTpid());

			if (ipdForm.getTpid() == null) {

				ipdForm.setTpid("0");
			}

			ipdForm.setStatus(bed.getStatus());
			ipdForm.setAddmissionfor(bed.getAddmissionfor());
			ipdForm.setReimbursment(bed.getReimbursment());

			if (bed.getSecndryconsult() != null) {
				if (bed.getSecndryconsult().equals("0")) {
					bed.setSecndryconsult(null);
				}
			}

			if (bed.getSecndryconsult() != null) {
				if (!bed.getSecndryconsult().equals("")) {

					ArrayList<String> allConsultantList = ipdDAO.getAllSecondaryConsultList(selectedid);
					ArrayList<UserProfile> allconsultantlistwithdepart = ipdDAO.getSecConsWithDepartment(selectedid);
					ipdForm.setAllconsultantlistwithdepart(allconsultantlistwithdepart);
					ipdForm.setAllConsultantList(allConsultantList);
					bed.setAllConsultantList(allConsultantList);
				}
			} else {

				practitionername = "";
			}

			userProfile = userProfileDAO.getUserprofileDetails(Integer.parseInt(bed.getPractitionerid()));
			Master discipline = masterDAO.getDisciplineData(userProfile.getDiciplineName());
			ipdForm.setDiscipline(discipline.getDiscipline());
			ipdForm.setDoctor_name(
					userProfile.getInitial() + " " + userProfile.getFirstname() + " " + userProfile.getLastname());
			ipdForm.setDoctor_phone(userProfile.getMobile());
			ipdForm.setQualification(userProfile.getQualification());
			ipdForm.setUseregno(userProfile.getRegisterno());

			//   add mlc ref dr name
			String mlcdrname = userProfileDAO.getReferalDrName(bed.getMlcrefdoctor());
			ipdForm.setMlcrefdoctor(mlcdrname);

			ipdForm.setSecndryconsult(practitionername);
			ipdForm.setMlcno(bed.getMlcno());
			ipdForm.setAdmissiondetails(bed.getAdmissiondetails());
			String val = bed.getSuggestedtrtment();
			ipdForm.setSuggestedtrtment(bed.getSuggestedtrtment());
			ipdForm.setHosp(bed.getHosp());
			ipdForm.setPackagename(bed.getPackagename());
			// chiefcomplains, presentillness, pasthistory, personalhist,
			// familyhist, onexamination, treatmentepisodeid lokeshnew
			ipdForm.setChiefcomplains(bed.getChiefcomplains());
			ipdForm.setPresentillness(bed.getPresentillness());
			ipdForm.setPasthistory(bed.getPasthistory());
			ipdForm.setPersonalhist(bed.getPersonalhist());
			ipdForm.setFamilyhist(bed.getFamilyhist());
			ipdForm.setOnexamination(bed.getOnexamination());

			String treatmentname = bedDao.getTreatmentName(bed.getTreatmentepisodeid());

			ipdForm.setTreatmentepisodeid(treatmentname);

			ipdForm.setSuggestoper(bed.getSuggestoper());
			ipdForm.setSystdepartment(bed.getSystdepartment());
			ipdForm.setFpcondition(bed.getFpcondition());
			ipdForm.setFpnotest(bed.getFpnotest());
			ipdForm.setNauseacondition(bed.getNauseacondition());
			ipdForm.setNauseanotes(bed.getNauseanotes());
			ipdForm.setFnucondition(bed.getFnucondition());
			ipdForm.setFnunotes(bed.getFnunotes());
			ipdForm.setSmcondition(bed.getSmcondition());
			ipdForm.setSmnotes(bed.getSmnotes());
			ipdForm.setChestpaincond(bed.getChestpaincond());
			ipdForm.setChestpainnotes(bed.getChestpainnotes());
			ipdForm.setDimvisioncond(bed.getDimvisioncond());
			ipdForm.setDimvisionnotes(bed.getDimvisionnotes());

			// dimvisionnotes, hgucondition, hgunotes, hmcondition, hmnotes,
			// jointpaincond, jointpainnotes,
			ipdForm.setHgucondition(bed.getHgucondition());
			ipdForm.setHgunotes(bed.getHgunotes());
			ipdForm.setHmcondition(bed.getHmcondition());
			ipdForm.setHmnotes(bed.getHmnotes());
			ipdForm.setJointpaincond(bed.getJointpaincond());
			ipdForm.setJointpainnotes(bed.getJointpainnotes());

			// constipationcond, constpationnotes, specialnotes, edemafeetcondi,
			// edemafeetnotes, hematuriacondi,
			ipdForm.setConstipationcond(bed.getConstipationcond());
			ipdForm.setConstpationnotes(bed.getConstpationnotes());
			ipdForm.setSpecialnotes(bed.getSpecialnotes());
			ipdForm.setEdemafeetcondi(bed.getEdemafeetcondi());
			ipdForm.setEdemafeetnotes(bed.getEdemafeetnotes());
			ipdForm.setHematuriacondi(bed.getHematuriacondi());
			ipdForm.setHematurianotes(bed.getHematurianotes());

			// hematurianotes, bmcondition, bmnotes, oliguriacondi,
			// oligurianotes, pstgucondi, pstgunotes,
			ipdForm.setBmcondition(bed.getBmcondition());
			ipdForm.setBmnotes(bed.getBmnotes());
			ipdForm.setOliguriacondi(bed.getOliguriacondi());
			ipdForm.setOligurianotes(bed.getOligurianotes());
			ipdForm.setPstgucondi(bed.getPstgucondi());
			ipdForm.setPstgunotes(bed.getPstgunotes());

			// bmecondition, bmenotes, tnecondition, tnenotes, weaknesscondi,
			// weaknessnotes, ihcondition, ihnotes
			ipdForm.setBmecondition(bed.getBmecondition());
			ipdForm.setBmenotes(bed.getBmenotes());
			ipdForm.setTnecondition(bed.getTnecondition());
			ipdForm.setTnenotes(bed.getTnenotes());
			ipdForm.setWeaknesscondi(bed.getWeaknesscondi());
			ipdForm.setWeaknessnotes(bed.getWeaknessnotes());
			ipdForm.setIhcondition(bed.getIhcondition());
			ipdForm.setIhnotes(bed.getIhnotes());

			ipdForm.setAdmission_reason(bed.getAdmission_reason());
			ipdForm.setEarlierinvest(bed.getEarlierinvest());
			ipdForm.setAlergies(bed.getAlergies());

			ipdForm.setBirthhist(bed.getBirthhist());
			ipdForm.setDiethist(bed.getDiethist());
			ipdForm.setDevelopmenthist(bed.getDevelopmenthist());
			ipdForm.setEmmunizationhist(bed.getEmmunizationhist());
			// lokesh
			ipdForm.setHeadcircumference(bed.getHeadcircumference());
			ipdForm.setMidarmcircumference(bed.getMidarmcircumference());
			ipdForm.setLength(bed.getLength());
			ipdForm.setWtaddmission(bed.getWtaddmission());
			ipdForm.setWtdischarge(bed.getWtdischarge());

			// giddinesscondition,giddinessnotes,unconcondition,unconnotes
			ipdForm.setGiddinesscondition(bed.getGiddinesscondition());
			ipdForm.setGiddinessnotes(bed.getGiddinessnotes());
			ipdForm.setUnconcondition(bed.getUnconcondition());
			ipdForm.setUnconnotes(bed.getUnconnotes());

			
			 * if(!bed.getEmmunizationhist().equals("")||!bed.getBirthhist().
			 * equals("")||!bed.getDiethist().equals("")||!bed.
			 * getDevelopmenthist().equals("")){ bed.setPeditric(true); }
			 

			ipdForm.setId(Integer.parseInt(selectedid));

			Bed bed1 = ipdDAO.getDischargeData(bed.getTreatmentepisodeid());
			ipdForm.setChkDischarge(bed1.isChkDischarge());
			ipdForm.setDischrgeOutcomes(bed1.getDischrgeOutcomes());
			ipdForm.setDischargeStatus(bed1.getDischargeStatus());
			ipdForm.setHospitalcourse(bed1.getHospitalcourse());
			// lokesh
			
			 * if(bed1.getHospitalcourse()!=null){
			 * if(bed1.getHospitalcourse().contains("<div>")){ String hscourse=
			 * bed1.getHospitalcourse(); hscourse=hscourse.replaceAll("<div>",
			 * ""); hscourse=hscourse.replaceAll("</div>", "");
			 * bed1.setHospitalcourse(hscourse); } }
			 
			ipdForm.setDiscadvnotes(bed1.getDiscadvnotes());
			ipdForm.setTreatmentgiven(bed1.getTreatmentgiven());
			ipdForm.setFindondischarge(bed1.getFindondischarge());
			ipdForm.setInvestigation(bed1.getInvestigation());
			ipdForm.setOtNotes(bed1.getOtNotes());
			ipdForm.setAppointmentText(bed1.getAppointmentText());
			ipdForm.setAnesthesia(bed1.getAnesthesia());
			ipdForm.setSurgeon(bed1.getSurgeon());
			ipdForm.setAnesthesiologist(bed1.getAnesthesiologist());
			ipdForm.setDischargebyid(bed1.getDischargebyid());
			ipdForm.setSurgicalnotes(bed1.getSurgicalnotes());
			ipdForm.setMaternal_history(bed.getMaternal_history());
			ipdForm.setPerinatal_history(bed.getPerinatal_history());
			
			ipdForm.setDischargeEndedbyId(bedDao.getLastDischargeUserId(bed.getTreatmentepisodeid(), "endeduserid"));
			ipdForm.setDischargteLastUpdatedId(bedDao.getLastDischargeUserId(bed.getTreatmentepisodeid(), "lastformfilleruserid"));
			// peditric

			ipdForm.setEmergencydetail(bed1.getEmergencydetail());

			ipdForm.setDeathnote(bed1.getDeathnote());
			ipdForm.setTreatmenthistory(bed.getTreatmenthistory());

			if (ipdForm.getDeathnote() != null) {

				if (ipdForm.getDeathnote().equals("") || ipdForm.getDeathnote().equals("<br>")) {
					ipdForm.setDeathnote(null);
				}

			}

			if (ipdForm.getSurgicalnotes() != null) {

				if (ipdForm.getSurgicalnotes().equals("") || ipdForm.getSurgicalnotes().equals("<br>")) {
					ipdForm.setSurgicalnotes(null);
				}

			}

			if (ipdForm.getTreatmentgiven() != null) {

				if (ipdForm.getTreatmentgiven().equals("") || ipdForm.getTreatmentgiven().equals("<br>")) {
					ipdForm.setTreatmentgiven(null);
				}

			}
			if (ipdForm.getFindondischarge() != null) {

				if (ipdForm.getFindondischarge().equals("") || ipdForm.getFindondischarge().equals("<br>")) {
					ipdForm.setFindondischarge(null);
				}

			}
			if (ipdForm.getInvestigation() != null) {

				if (ipdForm.getInvestigation().equals("") || ipdForm.getInvestigation().equals("<br>")) {
					ipdForm.setInvestigation(null);
				}

			}
			if (ipdForm.getOtNotes() != null) {

				if (ipdForm.getOtNotes().equals("") || ipdForm.getOtNotes().equals("<br>")) {
					ipdForm.setOtNotes(null);
				}

			}
			//As per praful shinde requirement It has to bring complete text Date :29/11/2019 lokesh
			
			
			if (ipdForm.getAppointmentText() != null) {

				if (ipdForm.getAppointmentText().equals("") || ipdForm.getAppointmentText().equals("<br>")) {
					ipdForm.setAppointmentText(null);
				} else {
					String data1 = "";
					String[] data = ipdForm.getAppointmentText().split(",");
					for (int i = 0; i < data.length; i++) {
						if (i != 0) {
							if (data1.equals("")) {
								data1 = data[i];
							} else {
								data1 = data1 + data[i];
							}

						}
					}
					ipdForm.setAppointmentText(data1);
				}

			}
			if (ipdForm.getSurgeon() != null) {

				if (ipdForm.getSurgeon().equals("") || ipdForm.getSurgeon().equals("<br>")) {
					ipdForm.setSurgeon(null);
				}

			}
			if (ipdForm.getAnesthesia() != null) {

				if (ipdForm.getAnesthesia().equals("") || ipdForm.getAnesthesia().equals("<br>")) {
					ipdForm.setAnesthesia(null);
				}

			}
			if (ipdForm.getAnesthesiologist() != null) {

				if (ipdForm.getAnesthesiologist().equals("") || ipdForm.getAnesthesiologist().equals("<br>")) {
					ipdForm.setAnesthesiologist(null);
				}

			}

			ipdForm.setChkDischarge(bed1.isChkDischarge());
			ipdForm.setHospitalcourse(bed1.getHospitalcourse());
			ipdForm.setHospitalcourse(bed1.getHospitalcourse());
			if (ipdForm.getHospitalcourse() != null) {
				if (ipdForm.getHospitalcourse().equals("") || ipdForm.getHospitalcourse().equals("<br>")) {
					ipdForm.setHospitalcourse(null);
				}
			}

			ipdForm.setDiscadvnotes(bed1.getDiscadvnotes());
			if (ipdForm.getDiscadvnotes() != null) {
				if (ipdForm.getDiscadvnotes().equals("") || ipdForm.getDiscadvnotes().equals("<br>")) {
					ipdForm.setDiscadvnotes(null);
				}
			}
			ipdForm.setExample(bed1.getExample());
			if (ipdForm.getExample() != null) {

				if (ipdForm.getExample().equals("") || ipdForm.getExample().equals("<br>")) {
					ipdForm.setExample(null);
				}

			}

			// gynic details

			boolean issubstance = false;

			ipdForm.setAlcohal(bed.getAlcohal());
			ipdForm.setDrugs(bed.getDrugs());
			ipdForm.setOther_medication(bed.getOther_medication());
			ipdForm.setTobaco(bed.getTobaco());
			ipdForm.setTobaconotes(bed.getTobaconotes());
			ipdForm.setSmoking(bed.getSmoking());
			if (ipdForm.getAlcohal() != null) {

				if (ipdForm.getAlcohal().equals("") || ipdForm.getAlcohal().equals("No")) {
					ipdForm.setAlcohal(null);
				} else {
					issubstance = true;
				}
			}
			if (ipdForm.getDrugs() != null) {

				if (ipdForm.getDrugs().equals("") || ipdForm.getDrugs().equals("No")) {
					ipdForm.setDrugs(null);
				} else {
					issubstance = true;
				}
			}
			if (ipdForm.getOther_medication() != null) {

				if (ipdForm.getOther_medication().equals("") || ipdForm.getOther_medication().equals("No")) {
					ipdForm.setOther_medication(null);
				} else {
					issubstance = true;
				}
			}
			if (ipdForm.getTobaco() != null) {

				if (ipdForm.getTobaco().equals("") || ipdForm.getTobaco().equals("No")) {
					ipdForm.setTobaco(null);
				} else {
					issubstance = true;
				}
			}
			if (ipdForm.getTobaconotes() != null) {

				if (ipdForm.getTobaconotes().equals("") || ipdForm.getTobaconotes().equals("No")) {
					ipdForm.setTobaconotes(null);
				} else {
					issubstance = true;
				}
			}
			if (ipdForm.getSmoking() != null) {

				if (ipdForm.getSmoking().equals("") || ipdForm.getSmoking().equals("No")) {
					ipdForm.setSmoking(null);
				} else {
					issubstance = true;
				}
			}

			if (issubstance) {
				ipdForm.setSubstancehistory("");
			} else {
				ipdForm.setSubstancehistory(null);
			}

			boolean ismenstrual = false;

			ipdForm.setAge_menarche(bed.getAge_menarche());
			ipdForm.setLmp(bed.getLmp());
			ipdForm.setLlmp(bed.getLlmp());
			ipdForm.setPmc(bed.getPmc());

			if (ipdForm.getAge_menarche() != null) {
				if (ipdForm.getAge_menarche().equals("0") || ipdForm.getAge_menarche().equals("")) {
					ipdForm.setAge_menarche(null);
				} else {
					ismenstrual = true;
				}
			}
			if (ipdForm.getLmp() != null) {
				if (ipdForm.getLmp().equals("0") || ipdForm.getLmp().equals("")) {
					ipdForm.setLmp(null);
				} else {
					ismenstrual = true;
				}
			}
			if (ipdForm.getLlmp() != null) {
				if (ipdForm.getLlmp().equals("0") || ipdForm.getLlmp().equals("")) {
					ipdForm.setLlmp(null);
				} else {
					ismenstrual = true;
				}
			}
			if (ipdForm.getPmc() != null) {
				if (ipdForm.getPmc().equals("0") || ipdForm.getPmc().equals("")) {
					ipdForm.setPmc(null);
				} else {
					ismenstrual = true;
				}
			}

			ipdForm.setCycle_length(bed.getCycle_length());
			ipdForm.setDuration_flow(bed.getDuration_flow());
			ipdForm.setAmount_flow(bed.getAmount_flow());
			ipdForm.setDysmenorrhea(bed.getDysmenorrhea());
			ipdForm.setDyspareunia(bed.getDyspareunia());
			ipdForm.setHopassing_clots(bed.getHopassing_clots());
			if (ipdForm.getCycle_length() != null) {
				if (ipdForm.getCycle_length().equals("0") || ipdForm.getCycle_length().equals("")) {
					ipdForm.setCycle_length(null);
				} else {
					ismenstrual = true;
				}
			}
			if (ipdForm.getDuration_flow() != null) {
				if (ipdForm.getDuration_flow().equals("0") || ipdForm.getDuration_flow().equals("")) {
					ipdForm.setDuration_flow(null);
				} else {
					ismenstrual = true;
				}
			}
			if (ipdForm.getAmount_flow() != null) {
				if (ipdForm.getAmount_flow().equals("0") || ipdForm.getAmount_flow().equals("")) {
					ipdForm.setAmount_flow(null);
				} else {
					ismenstrual = true;
				}
			}
			if (ipdForm.getDysmenorrhea() != null) {
				if (ipdForm.getDysmenorrhea().equals("0") || ipdForm.getDysmenorrhea().equals("")) {
					ipdForm.setDysmenorrhea(null);
				} else {
					ismenstrual = true;
				}
			}
			if (ipdForm.getDyspareunia() != null) {
				if (ipdForm.getDyspareunia().equals("0") || ipdForm.getDyspareunia().equals("")) {
					ipdForm.setDyspareunia(null);
				} else {
					ismenstrual = true;
				}
			}
			if (ipdForm.getHopassing_clots() != null) {
				if (ipdForm.getHopassing_clots().equals("0") || ipdForm.getHopassing_clots().equals("")) {
					ipdForm.setHopassing_clots(null);
				} else {
					ismenstrual = true;
				}
			}

			ipdForm.setWhite_disc_itching(bed.getWhite_disc_itching());
			ipdForm.setIntercourse_freq(bed.getIntercourse_freq());
			ipdForm.setGalactorrea(bed.getGalactorrea());
			if (ipdForm.getWhite_disc_itching() != null) {
				if (ipdForm.getWhite_disc_itching().equals("0") || ipdForm.getWhite_disc_itching().equals("")) {
					ipdForm.setWhite_disc_itching(null);
				} else {
					ismenstrual = true;
				}
			}
			if (ipdForm.getIntercourse_freq() != null) {
				if (ipdForm.getIntercourse_freq().equals("0") || ipdForm.getIntercourse_freq().equals("")) {
					ipdForm.setIntercourse_freq(null);
				} else {
					ismenstrual = true;
				}
			}
			if (ipdForm.getGalactorrea() != null) {
				if (ipdForm.getGalactorrea().equals("0") || ipdForm.getGalactorrea().equals("")) {
					ipdForm.setGalactorrea(null);
				} else {
					ismenstrual = true;
				}
			}

			ipdForm.setHo_contraception(bed.getHo_contraception());
			ipdForm.setRubella_vaccine(bed.getRubella_vaccine());
			ipdForm.setMenopause(bed.getMenopause());
			if (ipdForm.getHo_contraception() != null) {
				if (ipdForm.getHo_contraception().equals("0") || ipdForm.getHo_contraception().equals("")) {
					ipdForm.setHo_contraception(null);
				} else {
					ismenstrual = true;
				}
			}
			if (ipdForm.getRubella_vaccine() != null) {
				if (ipdForm.getRubella_vaccine().equals("0") || ipdForm.getRubella_vaccine().equals("")) {
					ipdForm.setRubella_vaccine(null);
				} else {
					ismenstrual = true;
				}
			}
			if (ipdForm.getMenopause() != null) {
				if (ipdForm.getMenopause().equals("0") || ipdForm.getMenopause().equals("")) {
					ipdForm.setMenopause(null);
				} else {
					ismenstrual = true;
				}
			}

			if (ismenstrual) {
				ipdForm.setMenstraulhistory("");
			} else {
				ipdForm.setMenstraulhistory(null);
			}

			ipdForm.setLive_boys(bed.getLive_boys());
			ipdForm.setLive_girls(bed.getLive_girls());
			ipdForm.setStillbirths(bed.getStillbirths());
			ipdForm.setAbortions(bed.getAbortions());
			ipdForm.setDeath_children(bed.getDeath_children());

			ArrayList<Bed> gynicobsList = bedDao.getGynicObsList(selectedid);
			ipdForm.setGynicobsList(gynicobsList);

			// parity_aboration_notes,p,l,a,d
			ipdForm.setParity_abortion_notes(bed.getParity_abortion_notes());
			if (ipdForm.getParity_abortion_notes() != null) {

				if (ipdForm.getParity_abortion_notes().equals("") || ipdForm.getParity_abortion_notes().equals("0")) {
					ipdForm.setParity_abortion_notes("");
				}
			}
			ipdForm.setP(bed.getP());
			ipdForm.setL(bed.getL());
			ipdForm.setA(bed.getA());
			ipdForm.setD(bed.getD());

			boolean ishistory = false;

			if (bed.getAddmissionfor() != null) {

				if (bed.getAddmissionfor().equals("") || bed.getAddmissionfor().equals("<br>")) {
					bed.setAddmissionfor(null);
				}
			}
			if (bed.getAlergies() != null) {

				if (bed.getAlergies().equals("") || bed.getAlergies().equals("<br>")) {
					bed.setAlergies(null);
				}
			}
			if (bed.getPackagename() != null) {

				if (bed.getPackagename().equals("") || bed.getPackagename().equals("<br>")) {
					bed.setPackagename(null);
				}

			}
			if (bed.getAdmission_reason() != null) {

				if (bed.getAdmission_reason().equals("") || bed.getAdmission_reason().equals("<br>")) {
					bed.setAdmission_reason(null);
				}
			}
			if (bed.getChiefcomplains() != null) {

				if (bed.getChiefcomplains().equals("") || bed.getChiefcomplains().equals("<br>")) {
					bed.setChiefcomplains(null);
				}
			}
			if (bed.getPasthistory() != null) {

				if (bed.getPasthistory().equals("") || bed.getPasthistory().equals("<br>")) {
					bed.setPasthistory(null);
				} else {
					ishistory = true;
				}
			}
			if (bed.getFamilyhist() != null) {

				if (bed.getFamilyhist().equals("") || bed.getFamilyhist().equals("<br>")) {
					bed.setFamilyhist(null);
				} else {
					ishistory = true;
				}
			}
			if (bed.getPersonalhist() != null) {

				if (bed.getPersonalhist().equals("") || bed.getPersonalhist().equals("<br>")) {
					bed.setPersonalhist(null);
				} else {
					ishistory = true;
				}
			}
			if (bed.getOnexamination() != null) {

				if (bed.getOnexamination().equals("") || bed.getOnexamination().equals("<br>")) {
					bed.setOnexamination(null);
				} else {
					ishistory = true;
				}

			}
			if (bed.getSurgicalnotes() != null) {

				if (bed.getSurgicalnotes().equals("") || bed.getSurgicalnotes().equals("<br>")) {
					bed.setSurgicalnotes(null);
				} else {
					ishistory = true;
				}

			}

			//   05 June 2018 to set content of History textbox data
			if (ipdForm.getExample() != null) {
				bed.setExample(ipdForm.getExample());
				ishistory = true;
			}

			if (bed.getSuggestedtrtment() != null) {

				if (bed.getSuggestedtrtment().equals("") || bed.getSuggestedtrtment().equals("<br>")) {
					bed.setSuggestedtrtment(null);
				} else {
				}
			}
			if (bed.getSpecialnotes() != null) {

				if (bed.getSpecialnotes().equals("") || bed.getSpecialnotes().equals("<br>")) {
					bed.setSpecialnotes(null);
				}

			}
			if (bed.getEarlierinvest() != null) {

				if (bed.getEarlierinvest().equals("") || bed.getEarlierinvest().equals("<br>")) {
					bed.setEarlierinvest(null);
				}
			}
			if (bed.getPresentillness() != null) {

				if (bed.getPresentillness().equals("") || bed.getPresentillness().equals("<br>")) {
					bed.setPresentillness(null);
				}
			}

			if (bed.getPresentillness() == null && bed.getChiefcomplains() == null
					&& bed.getAdmission_reason() == null) {

				bed.setSummary(null);
			} else {
				bed.setSummary("");
			}

			if (!ishistory) {
				bed.setHistory(null);
			} else {
				bed.setHistory("");
			}

			ipdForm.setSurgicalnotes(bed.getSurgicalnotes());
			// set treatment episode
			ClientDAO clientDAO = new JDBCClientDAO(connection);
			Client client = clientDAO.getClientDetails(ipdForm.getClientid());
			String fullname = client.getTitle() + " " + client.getFirstName() + " " + client.getMiddlename() + " "
					+ client.getLastName();
			ipdForm.setClient(fullname);
			ipdForm.setRegno(client.getAbrivationid());
			String whopay = client.getWhopay();
			ipdForm.setAbrivationid(client.getAbrivationid());
			ipdForm.setPatientIdAbrivation(client.getPatientIdAbrivation());
			if (whopay == null) {
				whopay = "";
			}
			if (!whopay.equals("Client")) {
				ThirdPartyDAO thirdPartyDAO = new JDBCThirdPartyDAO(connection);
				ThirdParty thirdParty = thirdPartyDAO.getThirdPartyDetails(bed.getTpid());
				ipdForm.setThirdParty(thirdParty.getCompanyName());
			} else {
				ipdForm.setThirdParty("Self");
			}
			String birthtime=client.getBirthtime().replaceAll(" ", "");
			 String hh1="";
			 String mm1="";
			 String apmpm1="";
			 if(!birthtime.equals("00:00:00")){
				 String time[] = birthtime.split(":");
				 hh1 = time[0];
				 mm1 = time[1];
				 int hourOfDay1=Integer.parseInt(hh1);
				 int minute1=Integer.parseInt(mm1);
				 apmpm1 =  ((hourOfDay1 > 12) ? hourOfDay1 % 12 : hourOfDay1) + ":" + (minute1 < 10 ? ("0" + minute1) : minute1) + " " + ((hourOfDay1 >= 12) ? "PM" : "AM");	 
			 }
			// lokesh
			String agegender = "";
			String dob = client.getDob();
			String age = DateTimeUtils.getAge1(client.getDob());
			
			 * String age1[]= age.split("(?<=\\D)(?=\\d)|(?<=\\d)(?=\\D)"); age=
			 * age1[0]; if(Integer.parseInt(age)<2){
			 * if(Integer.parseInt(age)<1){ String monthdays=
			 * DateTimeUtils.getMonthandDays(client.getDob());
			 * agegender=monthdays+" / "+client.getGender(); }else{ String
			 * monthdays= DateTimeUtils.getMonthandDays(client.getDob());
			 * agegender= age + " Years" +
			 * " "+monthdays+" / "+client.getGender(); } } else { agegender =
			 * age + "Years" + " / " + client.getGender(); }
			 

			if(!birthtime.equals("00:00:00")){
				agegender = age +"("+apmpm1+")"+ " / " + client.getGender();
			}else{
			agegender = age + " / " + client.getGender();	
			}
			ipdForm.setAge(age);
			ipdForm.setAgegender(agegender);
			ipdForm.setRelativename(client.getEmergencyContName());
			ipdForm.setRelationcont(client.getEmergencyContNo());
			ipdForm.setRelation(client.getRelation());

			boolean isfamilyd = false;

			if (ipdForm.getRelativename() != null) {

				if (ipdForm.getRelativename().equals("")) {

					ipdForm.setRelativename(null);
				}
			}

			if (ipdForm.getRelationcont() != null) {

				if (ipdForm.getRelationcont().equals("")) {

					ipdForm.setRelationcont(null);
				}
			}

			if (ipdForm.getRelation() != null) {

				if (ipdForm.getRelation().equals("")) {

					ipdForm.setRelation(null);
				}
			}

			if (ipdForm.getRelativename() == null) {
				isfamilyd = true;
			}
			if (ipdForm.getRelationcont() == null) {
				isfamilyd = true;
			}
			if (ipdForm.getRelation() == null) {
				isfamilyd = true;
			}

			if (isfamilyd) {
				ipdForm.setFamilyDetails("");
			} else {
				ipdForm.setFamilyDetails("ee");
			}

			String numcount = ipdDAO.getNumofAdmissionCount(ipdForm.getClientid());
			ipdForm.setNum_admission(numcount);
			ipdForm.setDob(client.getDob());
			ipdForm.setAddress(client.getAddress() + ", " + client.getTown() + "-" + client.getPostCode());
			ipdForm.setContact(client.getMobNo());
			
			//  24-07-2020
			//ArrayList<Bed> bedLogList = ipdLogDAO.getBedChangeLogList(bed.getClientid(), selectedid);
			ArrayList<Bed> bedLogList = ipdLogDAO.getBedChangeLogListNew(bed.getClientid(), selectedid);
			ipdForm.setBedLogList(bedLogList);

			if (bedLogList.size() > 0) {
				bedLogList.get(0).setStatus("1");
			}

			String payby = client.getWhopay();
			if (client.getWhopay().equals("Self")) {
				payby = "Client";
			}
			TreatmentEpisodeDAO treatmentEpisodeDAO = new JDBCTreatmentEpisode(connection);
			ClinicDAO clinicDAO = new JDBCClinicDAO(connection);
			ArrayList<TreatmentEpisode> treatmentEpisodeList = treatmentEpisodeDAO
					.getIpdTreatmentEpisodeList(ipdForm.getClientid(), payby);
			ipdForm.setTreatmentEpisodeList(treatmentEpisodeList);

			ipdForm.setTreatmentEpisode(bed.getTreatmentepisodeid());
			String admissiondate = bed.getAdmissiondate();
			String[] data = admissiondate.split(" ");
			String data2 = DateTimeUtils.getCommencingDate1(data[0]);
			String data3 = data2 + " " + data[1];
			// ipdForm.setAdmissiondate(bed.getAdmissiondate());
			if (loginInfo.isBalgopal()) {
				String time[] = data[1].split(":");
				int hourOfDay = (Integer.parseInt(time[0]));
				int minute = (Integer.parseInt(time[1]));
				String apmpm = ((hourOfDay > 12) ? hourOfDay % 12 : hourOfDay) + ":"
						+ (minute < 10 ? ("0" + minute) : minute) + " " + ((hourOfDay >= 12) ? "PM" : "AM");
				data3 = DateTimeUtils.getCommencingDate1(data[0]) + " " + apmpm;
			}
			ipdForm.setAdmissiondate(data3);

			
			 * Collection<Bed>
			 * conditions=bedDao.getConditionList(bed.getConditionid());
			 * ipdForm.setConditions(conditions);
			 

			Clinic clinic = clinicDAO.getCliniclistDetails(loginInfo.getClinicid());
			ipdForm.setClinicLogo(clinic.getUserImageFileName());

			String treatmentName = bedDao.getTreatmentName(bed.getTreatmentepisodeid());
			ipdForm.setTreatmentEpisode(treatmentName);
			ArrayList<Bed> ipdConditionids = bedDao.getIpdConditionList(selectedid);

			ArrayList<String> ipdconditionnames = bedDao.getConditionNameList(ipdConditionids);

			ArrayList<Client> ipdCondtitionList = clientDAO.getTreatmentTypeList();
			session.setAttribute("ipdConditionids", ipdConditionids);
			session.setAttribute("ipdCondtitionList", ipdconditionnames);
			session.setAttribute("bed", bed);

			ipdForm.setMlccase(bed.getMlccase());
			
			 * if(bed.getMlccase().equals("1")){
			 * 
			 * }
			 
			
			ipdForm.setFathername(client.getFathername());
			ipdForm.setMothername(client.getMothername());
			ipdForm.setBirthplace(client.getBirthplace());
			
			if (ipdForm.getDischargedate() != null) {
				if (!ipdForm.getDischargedate().equals("")) {
					String c = ipdForm.getDischargedate();
					String h[] = c.split(" ");
					c = DateTimeUtils.getCommencingDate1(h[0]);
					String d = DateTimeUtils.getAge1onAddmission(ipdForm.getDob(), c);
					String w[] = ipdForm.getAgegender().split("/");

					ipdForm.setAgegender(d + "/" + w[1]);
					ipdForm.setAge(d);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}*/

	public String discharge() throws SQLException {
		if (!verifyLogin(request)) {
			return "login";
		}

		try {
			session.removeAttribute("finalConditions");
			if (session.getAttribute("openedb") == null) {
				session.setAttribute("openedb", "ipd");
			}
			String fromDate=ipdForm.getFromDate();
			
			if(DateTimeUtils.isNull(fromDate).equals("")){
				DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
				fromDate = dateFormat.format(new Date());
			}else{
				fromDate = DateTimeUtils.getCommencingDate1(fromDate);
			}

			ipdForm.setFromDate(DateTimeUtils.getCommencingDate1(fromDate));

			String selectedid = request.getParameter("selectedid");
			getipddatadischarge(selectedid);
			
			if(loginInfo.isNew_aureus_discard()){
				return "new_aureus_discard";
			}
			

		}
		
		catch (Exception e) {
			e.printStackTrace();
		}

		return "discharge";
	}

	public void getipddatadischarge(String selectedid) {

		Connection connection = null;

		try {
			session.removeAttribute("finalConditions");
			if (session.getAttribute("openedb") == null) {
				session.setAttribute("openedb", "ipd");
			}
			request.setCharacterEncoding("UTF-8");
			response.setCharacterEncoding("UTF-8");
	/*		pointer*/
			connection = Connection_provider.getconnection();
			IpdDAO ipdDAO = new JDBCIpdDAO(connection);
			BedDao bedDao = new JDBCBedDao(connection);
			IpdLogDAO ipdLogDAO = new JDBCIpdLogDAO(connection);
			ClientDAO clientDAO = new JDBCClientDAO(connection);
			MasterDAO masterDAO = new JDBCMasterDAO(connection);
			UserProfileDAO userProfileDAO = new JDBCUserProfileDAO(connection);
			NotAvailableSlotDAO notAvailableSlotDAO = new JDBCNotAvailableSlotDAO(connection);
			EmrDAO emrDAO = new JDBCEmrDAO(connection);
			ipdForm.setClientip(selectedid);
			/*
			 * UserProfileDAO userProfileDAO = new
			 * JDBCUserProfileDAO(connection);
			 */

			// prepare data of IPDACTION LOKESH
			listOfExtraData();

			// for procedure
			Ipd ipd = ipdDAO.getProcedureName(selectedid);

			Bed bed = bedDao.getEditIpdData(selectedid);
			int actionType= DateTimeUtils.convertToInteger(bed.getAction());
			
			boolean isdaycare=false;
			if(actionType==2){
				isdaycare = true;
			}
			ipdForm.setDaycare(isdaycare);
			ipdForm.setOndiet(bed.getOndiet());
			/*ipdForm.setDaycare(bedDao.isDayCare(selectedid));*/
			
			//for new Aureus Discharge Card
			
			if(!bedDao.entryExistsInNewDischargeFields(selectedid)){
				bedDao.makeEntryToNewDischargeFields(bed.getClientid(), selectedid, bed.getTreatmentepisodeid());
			}
			
			ipdForm.setLamadamareason(bed.getReasonlamadama());
			ipdForm.setGstureage(bed.getGstureage());
			ipdForm.setWtonbirth(bed.getWtonbirth());
			ipdForm.setSecndryconsult(bed.getSecndryconsult());
			session.setAttribute("sessionadmissionid", selectedid);
			//for parihar
			ArrayList<DiaryManagement> userList=notAvailableSlotDAO.getEditUserList(loginInfo.getId(),selectedid); 
    		ipdForm.setUserList(userList);
			ipdForm.setStatus(bed.getStatus());
			String procedureid = ipdDAO.getProcedureId(ipd.getProcedurename());
			ipdForm.setAppointmentText(procedureid);

			ipdForm.setNewadmndate(bed.getNewadmndate());
			ArrayList<Master> procedureList = notAvailableSlotDAO.getProcedureList(bed.getSpeciality());
			/*if (procedureList.size() == 0) {
				procedureList = new ArrayList<Master>();
			}*/

			ipdForm.setProcedureList(procedureList);

			if (loginInfo.getIpd_abbr_access() == 1) {
				//String newipdabbr = ipdDAO.getIpdAbrivationIds(Integer.parseInt(selectedid));
				ipdForm.setNewipdabbr(bed.getIpdabrivationid());
				if (Integer.parseInt(bed.getIpdseqno()) > 0) {
					ipdForm.setIpdseqno(bed.getIpdseqno());
				} else {
					ipdForm.setIpdseqno(selectedid);
				}
			} else {
				if (Integer.parseInt(bed.getIpdseqno()) > 0) {
					ipdForm.setIpdseqno(bed.getIpdseqno());
					ipdForm.setNewipdabbr(bed.getIpdseqno());
				} else {
					ipdForm.setIpdseqno(selectedid);
					ipdForm.setNewipdabbr(selectedid);
				}
			}

			Client client = clientDAO.getClientDetails(bed.getClientid());

			UserProfile userProfile = userProfileDAO.getUserprofileDetails(Integer.parseInt(bed.getPractitionerid()));
			Master discipline = masterDAO.getDisciplineData(userProfile.getDiciplineName());
			ipdForm.setDiscipline(discipline.getDiscipline());
			ipdForm.setDoctor_name(
					userProfile.getInitial() + " " + userProfile.getFirstname() + " " + userProfile.getLastname());
			ipdForm.setDoctor_phone(userProfile.getMobile());

			/*String discharge_default_id = masterDAO.getIpdTemplateId("Discharge Default");
			String hospital_course_id = masterDAO.getIpdTemplateId("Hospital Course");
			String nursing_advice_id = masterDAO.getIpdTemplateId("Nursing Advice");
			String operative_notes_id = masterDAO.getIpdTemplateId("Operative Notes");
			String invenstigations = masterDAO.getIpdTemplateId("Investigations");
			String finding_on_discharge = masterDAO.getIpdTemplateId("FINDING ON DISCHARGE");
			String treatment_given_id = masterDAO.getIpdTemplateId("Discharge Treatment Given");
			String emergencydetailsid = masterDAO.getIpdTemplateId("Emergency Details");
			String treatmentgiventemplateid = masterDAO.getIpdTemplateId("Treatment Given");
			String onexami_id = masterDAO.getIpdTemplateId("On Examination");*/
			//  24-07-2020 showing repeated log of same bed
			/*ArrayList<Bed> bedLogList = ipdLogDAO.getBedChangeLogList(bed.getClientid(), selectedid);*/
			ArrayList<Bed> bedLogList = ipdLogDAO.getBedChangeLogListNew(bed.getClientid(), selectedid);
			ipdForm.setBedLogList(bedLogList);

			ipdForm.setCommonTemplateList(ipdDAO.commonTemplateList());
			
			
			/*String specializationId=  userProfile.getDiciplineName();
			ipdForm.setNicuaccess(masterDAO.isIpdFormFieldActive(specializationId, "NICU Setting"));
			boolean peditric = masterDAO.isIpdFormFieldActive(specializationId, "Paediatric History");
			ipdForm.setNicuaccess(peditric);*/
			// int size=bedLogList.size()-1;
			if (bedLogList.size() > 0) {
				bedLogList.get(0).setStatus("1");
			}

			ArrayList<Master> discharge_default_list = masterDAO.getIpdTemplateListNamesList("Discharge Default");
			ArrayList<Master> hospital_course_list = masterDAO.getIpdTemplateListNamesList("Hospital Course");
			ArrayList<Master> nursing_advice_list = masterDAO.getIpdTemplateListNamesList("Nursing Advice");
			ArrayList<Master> operativeList = masterDAO.getIpdTemplateListNamesList("Operative Notes");
			ArrayList<Master> investigationList = masterDAO.getIpdTemplateListNamesList("Investigations");
			ArrayList<Master> finding_on_dischargeList = masterDAO.getIpdTemplateListNamesList("FINDING ON DISCHARGE");
			ArrayList<Master> treatment_given_list = masterDAO.getIpdTemplateListNamesList("Discharge Treatment Given");
			ArrayList<Master> emergencydetailslist = masterDAO.getIpdTemplateListNamesList("Emergency Details");
			ArrayList<Master> treatmentgiventemplist = masterDAO.getIpdTemplateListNamesList("Treatment Given");
			/*ArrayList<Master> on_exam_list = masterDAO.getIpdTemplateListNames("On Examination");*/
			ArrayList<Master> on_exam_list = masterDAO.getIpdTemplateListNamesList("On Examination");
			
			
			//String maternalHisttempid=masterDAO.getIpdTemplateId("Maternal History");
			//String perinatalHistroy=masterDAO.getIpdTemplateId("Perinatal History");
			ArrayList<Master> perintal_hisry_list=masterDAO.getIpdTemplateListNamesList("Perinatal History");
			ArrayList<Master> maternal_histry_list=masterDAO.getIpdTemplateListNamesList("Maternal History");
			ArrayList<Master>on_diet_list=masterDAO.getIpdTemplateListNamesList("On Diet");
			ArrayList<Master>physio_list=masterDAO.getIpdTemplateListNamesList("Physio");
			ArrayList<Master>karma_list=masterDAO.getIpdTemplateListNamesList("Karma");
			ArrayList<Master>procedure_list=masterDAO.getIpdTemplateListNamesList("Procedurebams");
			ipdForm.setPerintal_hisry_list(perintal_hisry_list);
			ipdForm.setMaternal_histry_list(maternal_histry_list);
			
			
			ipdForm.setOperativeList(operativeList);
			ipdForm.setDischarge_default_list(discharge_default_list);
			ipdForm.setHospital_course_list(hospital_course_list);
			ipdForm.setNursing_advice_list(nursing_advice_list);
			ipdForm.setInvestigationList(investigationList);
			ipdForm.setFinding_on_dischargeList(finding_on_dischargeList);
			ipdForm.setTreatment_given_list(treatment_given_list);
			ipdForm.setEmergencydetailslist(emergencydetailslist);
			ipdForm.setTreatmentgiventemplatelist(treatmentgiventemplist);
			ipdForm.setOn_exam_list(on_exam_list);
			ipdForm.setOn_diet_list(on_diet_list);
			ipdForm.setPhysio_list(physio_list);
			ipdForm.setKarma_list(karma_list);
			ipdForm.setProcedure_list(procedure_list);
			// Old surgical list from apm_other_template table //  11 dec
			// 2017
			/*
			 * UserProfile userProfile2 =
			 * userProfileDAO.getUserprofileDetails(loginInfo.getId());
			 * ArrayList<Master> otherTemplateList =
			 * masterDAO.getEmrTemplateList(userProfile2.getDiciplineName());
			 * ipdForm.setOtherTemplateList(otherTemplateList);
			 */

			InvestigationDAO investigationDAO = new JDBCInvestigationDAO(connection);
			ArrayList<InvstTemplate> templateList = investigationDAO.getTemplateList();
			ipdForm.setInvestigationtemplatelist(templateList);

			// New from ipd_template table //the surgical notes
			//String surgical_template = masterDAO.getIpdTemplateId("OT Template");
			ArrayList<Master> otherTemplateList = masterDAO.getIpdTemplateListNamesList("OT Template");
			ipdForm.setOtherTemplateList(otherTemplateList);

			double balance = 0;
			
			//Client clientData = clientDAO.getClientDetails(bed.getClientid());
			ipdForm.setContact(client.getMobNo());
			ipdForm.setAddress(client.getAddress());
			String numcount = ipdDAO.getNumofAdmissionCount(bed.getClientid());
			ipdForm.setNum_admission(numcount);

			if (client.getWhopay().equals(Constants.PAY_BY_CLIENT)) {
				/*double debit = diaryManagementDAO.getClientDebitTotal(bed.getClientid());
				double payment = diaryManagementDAO.getClientPayment(bed.getClientid());

				balance = debit - payment;*/

//				System.out.println(balance);

				ipdForm.setWhopay("Self");
			} else {

				ThirdParty thirdParty = client.getTpDetails();
				ipdForm.setWhopay(thirdParty.getCompanyName());

			}

			ipdForm.setBalance(balance);
			
			//set treatment episode
			String fullname = client.getTitle() + " " + client.getFirstName() + " "+client.getMiddlename()+" " + client.getLastName();
			ipdForm.setClient(fullname);
			ipdForm.setRegno(client.getAbrivationid());
			String whopay=client.getWhopay();
			ipdForm.setAbrivationid(client.getAbrivationid());
			ipdForm.setPatientIdAbrivation(client.getPatientIdAbrivation());
			if(whopay==null){
				whopay="";
			}
			if(!whopay.equals("Client")){
				ThirdPartyDAO thirdPartyDAO = new JDBCThirdPartyDAO(connection);
				if(DateTimeUtils.numberCheck(bed.getTpid()).equals("0") && (!DateTimeUtils.numberCheck(bed.getBedid()).equals("0"))){
					String typeName = client.getTypeName();
					if(!DateTimeUtils.numberCheck(typeName).equals("0")){
						bed.setTpid(typeName);
						ipdForm.setTpid(bed.getTpid());
						int result = ipdDAO.updateAdm_Tp(selectedid,typeName);
						result = ipdDAO.updateAdmNew_Tp(selectedid,typeName);
					}
					
				}
				ThirdParty thirdParty = thirdPartyDAO.getThirdPartyDetails(bed.getTpid());
				ipdForm.setThirdParty(thirdParty.getCompanyName());
			}else{
				ipdForm.setThirdParty("Self");
			}
			String birthtime=client.getBirthtime().replaceAll(" ", "");
			 String hh1="";
			 String mm1="";
			 String apmpm1="";
			 if(!birthtime.equals("00:00:00")){
				 String time[] = birthtime.split(":");
				 hh1 = time[0];
				 hh1=hh1.replaceAll(" ", "");
				 mm1 = time[1];
				 mm1=mm1.replaceAll(" ", "");
				 	int hourOfDay1=Integer.parseInt(hh1);
				   int minute1=Integer.parseInt(mm1);
				    apmpm1 =  ((hourOfDay1 > 12) ? hourOfDay1 % 12 : hourOfDay1) + ":" + (minute1 < 10 ? ("0" + minute1) : minute1) + " " + ((hourOfDay1 >= 12) ? "PM" : "AM");	 
			 }
			//lokesh
			String agegender="";
			String dob = client.getDob();
			String age = DateTimeUtils.getAge1(client.getDob());
			if(!birthtime.equals("00:00:00")){
				agegender = age +"("+apmpm1+")"+ " / " + client.getGender();
			}else{
			agegender = age + " / " + client.getGender();	
			}
			ipdForm.setAge(age);
			ipdForm.setAgegender(agegender);
			ipdForm.setRelativename(client.getEmergencyContName());
    		ipdForm.setRelationcont(client.getEmergencyContNo());
    		ipdForm.setRelation(client.getRelation());
			//parentpriscid
			
			// get prescription list
    		
    		ArrayList<Priscription> dischargeParentPriscIds = ipdDAO.getDischargeParentPriscIds(selectedid);
    		ArrayList<Priscription> dischargePriscList = new ArrayList<>();
			ArrayList<Priscription> treatmentivendischargePriscList = new ArrayList<>();
			String parentpriscid ="";
    		
			String treatmentIds="0,";
			if(dischargeParentPriscIds.size()>0){
    			//parentpriscid = ipdDAO.getParentPriscId(selectedid);
				parentpriscid = ""+dischargeParentPriscIds.get(dischargeParentPriscIds.size()-1).getId();
				
				for (Priscription priscription : dischargeParentPriscIds) {
					if(priscription.getFromtreatmentgiven()==1){
						treatmentIds = treatmentIds + priscription.getId()+",";
					}
				}
				ArrayList<Priscription> dischargePriscriptionList = ipdDAO.getDichargePriscriptionList(dischargeParentPriscIds.get(dischargeParentPriscIds.size()-1).getIds(),"0");
    			for (Priscription priscription : dischargePriscriptionList) {
					if(treatmentIds.contains(","+priscription.getParentid()+",")){
						treatmentivendischargePriscList.add(priscription);
					}else{
						dischargePriscList.add(priscription);
					}
				}
    		}
    		ipdForm.setParentpriscid(parentpriscid);
    		
			
			/*if(DateTimeUtils.convertToInteger(parentpriscid)>0){
				dischargePriscList = ipdDAO.getDischargePrescList(selectedid);
				treatmentivendischargePriscList = ipdDAO.getTreatmentGivenDischargePrescList(selectedid);
			}*/
			ipdForm.setDischargePriscList(dischargePriscList);
			session.setAttribute("priscList", dischargePriscList);
			ipdForm.setTreatmentivendischargePriscListt(treatmentivendischargePriscList);
			int size = dischargePriscList.size();
			if (size > 0) {
				String totalchildmedids = dischargePriscList.get(size - 1).getTotalchildmedids();
				ipdForm.setTotalchildmedids(totalchildmedids);
			} else {
				ipdForm.setTotalchildmedids("0");
			}

			Bed bed1 = ipdDAO.getDischargeData(bed.getTreatmentepisodeid());
			/*
			 * String findings=""; if(loginInfo.isParihar()) {
			 * findings=ipdDAO.getInvestigationNameFindings(bed.getClientid()); }
			 */

			ipdForm.setTreatmentEpisodeData(bed1);
			ipdForm.setChkDischarge(bed1.isChkDischarge());
			ipdForm.setDischrgeOutcomes(bed1.getDischrgeOutcomes());
			ipdForm.setInvestigation(bed1.getInvestigation());
			ipdForm.setDischargeStatus(bed1.getDischargeStatus());
			ipdForm.setHospitalcourse(bed1.getHospitalcourse());
			ipdForm.setDiscadvnotes(bed1.getDiscadvnotes());
			ipdForm.setExample(bed1.getExample());
			ipdForm.setFindondischarge(bed1.getFindondischarge());
			ipdForm.setTreatmentgiven(bed1.getTreatmentgiven());
			/*
			 * //for parihar if(loginInfo.isParihar()) { ipdForm.setInvestigation(findings);
			 * }else { ipdForm.setInvestigation(bed1.getInvestigation()); }
			 */
			//ipdForm.setInvestigation(bed1.getInvestigation());
			ipdForm.setOtNotes(bed1.getOtNotes());

			ipdForm.setDeathnote(bed1.getDeathnote());

			ipdForm.setAppointmentText(bed1.getAppointmentText());
			ipdForm.setAnesthesia(bed1.getAnesthesia());
			ipdForm.setSurgeon(bed1.getSurgeon());
			ipdForm.setAnesthesiologist(bed1.getAnesthesiologist());
			
			ipdForm.setEmergencydetail(bed1.getEmergencydetail());

			if (bed1.getDischargedate() != null) {
				if (!bed1.getDischargedate().equals("")) {
					String temp[] = bed1.getDischargedate().split(" ");
					String date = DateTimeUtils.getCommencingDate1(temp[0]);
					ipdForm.setDischargedate(date);
					String time[] = temp[1].split(":");
					String hh = time[0];
					String mm = time[1];
					ipdForm.setHour(hh);
					ipdForm.setMinute(mm);
				}
			}

			//printformdata(selectedid);
			
			ipdForm.setChiefcomplains(bed.getChiefcomplains());
			ipdForm.setPresentillness(bed.getPresentillness());
			ipdForm.setPasthistory(bed.getPasthistory());
			ipdForm.setPersonalhist(bed.getPersonalhist());
			ipdForm.setFamilyhist(bed.getFamilyhist());
			ipdForm.setOnexamination(bed.getOnexamination());
			ipdForm.setSuggestedtrtment(bed.getSuggestedtrtment());
			ipdForm.setAdmission_reason(bed.getAdmission_reason());
    		ipdForm.setEarlierinvest(bed.getEarlierinvest());
    		ipdForm.setAlergies(bed.getAlergies());
    		

			ipdForm.setClientid(bed.getClientid());
			ipdForm.setPractitionerid(bed.getPractitionerid());
			ipdForm.setConditionid(bed.getConditionid());
			
			Bed bedconditions = ipdDAO.getAllFinalCondition(selectedid, bed.getTreatmentepisodeid());
			

			ArrayList<Bed> finalConditions = new ArrayList<Bed>();

			if (bedconditions.getConditionname() != null) {

				for (String str : bedconditions.getConditionname().split(",")) {

					if (str == null) {
						continue;
					}
					if (str.equals("0")) {

						continue;
					}
					int id = Integer.parseInt(str);
					String conditionname = bedDao.getIpdConditionName(str);
					Bed bed2 = new Bed();
					bed2.setId(id);
					bed2.setConditionname(conditionname);
					bed2.setConditionid(str);
					finalConditions.add(bed2);

				}
			}
			ipdForm.setAbrivationid(client.getAbrivationid());

			ipdForm.setFinalConditions(finalConditions);

			ArrayList<Client> ipdCondtitionList = new ArrayList<Client>(); // clientDAO.getEmrTreatmentTypeList();
			session.setAttribute("finalConditions", finalConditions);
			session.setAttribute("ipdCondtitionmaster", ipdCondtitionList);

			ArrayList<Bed> ipdconditionlist = bedDao.getIpdConditionList(selectedid);
			ipdForm.setIpdconditionlist(ipdconditionlist);
			
			ArrayList<Bed> ipdConditionids = bedDao.getIpdConditionList(selectedid);
			ArrayList<String> ipdconditionnames=bedDao.getConditionNameList(ipdConditionids);
			session.setAttribute("ipdConditionids", ipdConditionids);
			session.setAttribute("ipdCondtitionList", ipdconditionnames);
			session.setAttribute("bed", bed);

			int selectedid1 = loginInfo.getId();

			ClinicDAO clinicListDAO = new JDBCClinicDAO(connection);
			com.apm.Registration.eu.entity.Clinic cliniclist = clinicListDAO.getCliniclistDetails(selectedid1);
			ipdForm.setClinicName(cliniclist.getClinicName());

			// 29 NOV 2017
			ipdForm.setAddmissionfor(bed.getAddmissionfor());
			ipdForm.setAlergies(bed.getAlergies());
			ipdForm.setChiefcomplains(bed.getChiefcomplains());
			ipdForm.setPresentillness(bed.getPresentillness());

			// peditric
			ipdForm.setBirthhist(bed.getBirthhist());
			ipdForm.setDiethist(bed.getDiethist());
			ipdForm.setDevelopmenthist(bed.getDevelopmenthist());
			ipdForm.setEmmunizationhist(bed.getEmmunizationhist());
			ipdForm.setHeadcircumference(bed.getHeadcircumference());
			ipdForm.setMidarmcircumference(bed.getMidarmcircumference());
			ipdForm.setLength(bed.getLength());
			ipdForm.setWtaddmission(bed.getWtaddmission());
			ipdForm.setWtdischarge(bed.getWtdischarge());
			ipdForm.setMaternal_history(bed.getMaternal_history());
			ipdForm.setPerinatal_history(bed.getPerinatal_history());

			// kunal
			ipdForm.setKunal_final_diagnosis_text(bed.getKunal_final_diagnosis_text());
			ipdForm.setKunal_manual_medicine_text(bed.getKunal_manual_medicine_text());

			//String chief_comlint_id = masterDAO.getIpdTemplateId("Chief Complaints");
			//String present_ill_id = masterDAO.getIpdTemplateId("Present Illness");
   
			ArrayList<Master> chief_complaints_list = masterDAO.getIpdTemplateListNamesList("Chief Complaints");
			ArrayList<Master> present_illness_list = masterDAO.getIpdTemplateListNamesList("Present Illness");
			ArrayList<Master> examination_finding_list=masterDAO.getIpdTemplateListNamesList("Examination Findings");
			ipdForm.setChief_complaints_list(chief_complaints_list);
			ipdForm.setPresent_illness_list(present_illness_list);
            ipdForm.setExamination_finding_list(examination_finding_list);
			ArrayList<String> otaptid = ipdDAO.getAllOTIds(selectedid, bed.getClientid());

			ArrayList<Master> otnoteslist = new ArrayList<Master>();
			for (String string : otaptid) {
				ArrayList<Master> otnoteslist1 = ipdDAO.getAllOtNotes(string);
				otnoteslist.addAll(otnoteslist1);
			}
			String otNotes = bed1.getOtNotes();
			if (otNotes == null) {
				otNotes = "";
			}
			String otNotesids = "0";
			for (Master master : otnoteslist) {
				otNotesids = otNotesids + "," + master.getId();
				otNotes = otNotes + master.getOtnotes();
			}
			ipdForm.setOtnoteslist(otnoteslist);
			ipdForm.setOtNotes(otNotes);
			ipdForm.setOtNotesids(otNotesids);

			ArrayList<Master> otdatesandids = ipdDAO.getOtDatesAndIdsFromdischarge(selectedid);
			ipdForm.setOtdatesandids(otdatesandids);
			int size1 = otdatesandids.size();
			if (size1 > 0) {
				String totalotids = otdatesandids.get(size1 - 1).getTotalids();
				ipdForm.setTotalotids(totalotids);
			} else {
				ipdForm.setTotalotids("0");
			}

			session.setAttribute("otdatesandids", otdatesandids);

			ArrayList<Priscription> treatmentlist = new ArrayList<Priscription>();

			//  
			if (loginInfo.isMedtreatgiven()) {
				treatmentlist = bedDao.gettreatmentlist(selectedid);
				ipdForm.setTreatmentlist1(treatmentlist);
			} else {
				ipdForm.setTreatmentlist1(treatmentlist);
			}

			ArrayList<Investigation> investlist = new ArrayList<Investigation>();
			/* investlist = bedDao.getinvestigationlist(selectedid); */
			ipdForm.setInvestlist(investlist);
			String treatmentgiven = "";
			String invstl = "";
			StringBuffer buffer = new StringBuffer();
			StringBuffer buffer1 = new StringBuffer();
			String priscid = "0";
			for (Priscription prisc : treatmentlist) {

				/*
				 * buffer.append(prisc.getMdicinenametxt()+" - "+prisc.getDosage
				 * ()+" - "+String.valueOf(prisc.getDays())+" <br>");
				 */
				buffer.append(prisc.getMdicinenametxt() + " <br>");
				priscid = priscid + "," + prisc.getPrischildid();
			}
			if (bed1.getTreatmentgiven() != null) {
				if (loginInfo.isMedtreatgiven()) {
					treatmentgiven = bed1.getTreatmentgiven() + "<br>" + buffer.toString();
				} else {
					treatmentgiven = bed1.getTreatmentgiven();
				}

			} else {
				if (loginInfo.isMedtreatgiven()) {
					treatmentgiven = buffer.toString();
				}

			}
			ipdForm.setPriscid(priscid);

			String invstids = "0";
			/*
			 * for(Investigation invst : investlist){ invstids =
			 * invstids+","+invst.getParentid();
			 * buffer1.append(invst.getInvsttype()+"--"+invst.getDate()+"<br>");
			 * } if(bed1.getInvestigation()!=null){ invstl=
			 * bed1.getInvestigation() +"<br>"+buffer1.toString(); }else{
			 * invstl= buffer1.toString(); }
			 */

			ipdForm.setInvstids(invstids);
			ipdForm.setTreatmentgiven(treatmentgiven);
			if(!loginInfo.isParihar()) {
				ipdForm.setInvestigation(bed1.getInvestigation());
			}
			//ipdForm.setInvestigation(bed1.getInvestigation());

			if (loginInfo.getInvestigation_newaccess().equals("1")) {
				ipdForm.setDischrgeOutcomes("9");
			}
			//   24 May 2018
			ArrayList<Ipd> rmonoteslist = new ArrayList<Ipd>();
			rmonoteslist = ipdDAO.getRMONotesList(selectedid);
			StringBuffer stringBuffer = new StringBuffer();
			String rmonotesids = "0";
			String hospitalcorc = "";
			for (Ipd ipd2 : rmonoteslist) {
				rmonotesids = rmonotesids + "," + ipd2.getId();
				stringBuffer.append("Day:" + ipd2.getDay() + "<br>");
				stringBuffer.append("" + ipd2.getNotes() + "<br>");
			}

			if (bed1.getHospitalcourse() != null) {
				if (bed1.getHospitalcourse().equals("<br>")) {
					hospitalcorc = stringBuffer.toString();
				} else {
					hospitalcorc = bed1.getHospitalcourse() + "<br>" + stringBuffer.toString();
				}

			} else {
				hospitalcorc = stringBuffer.toString();
			}

			ipdForm.setHospitalcourse(hospitalcorc);
			ipdForm.setRmonotesids(rmonotesids);

			if (ipdForm.getSurgeon() == null) {
				if (ipd.getSurgeon() != null) {
					if (!ipd.getSurgeon().equals("")) {
						ipdForm.setSurgeon(ipd.getSurgeon());
					}
				}
			} else if (ipdForm.getSurgeon().equals("0")) {
				if (ipd.getSurgeon() != null) {
					if (!ipd.getSurgeon().equals("")) {
						ipdForm.setSurgeon(ipd.getSurgeon());
					}
				}
			}

			if (ipdForm.getAnesthesiologist() == null) {
				if (ipd.getAnesthesia() != null) {
					if (!ipd.getAnesthesia().equals("")) {
						ipdForm.setAnesthesiologist(ipd.getAnesthesia());
					}
				}
			} else if (ipdForm.getAnesthesiologist().equals("0")) {
				if (ipd.getAnesthesia() != null) {
					if (!ipd.getAnesthesia().equals("")) {
						ipdForm.setAnesthesiologist(ipd.getAnesthesia());
					}
				}
			}
			
			String admissiondate = bed.getAdmissiondate();
			String[] data = admissiondate.split(" ");
			String data2 = DateTimeUtils.getCommencingDate1(data[0]);
			String data3 = data2 +" "+ data[1];
			//ipdForm.setAdmissiondate(bed.getAdmissiondate());
			if(loginInfo.isBalgopal()){
				String time[]=data[1].split(":"); 
				   int hourOfDay=(Integer.parseInt(time[0]));
				   int minute=(Integer.parseInt(time[1]));
				   String apmpm =  ((hourOfDay > 12) ? hourOfDay % 12 : hourOfDay) + ":" + (minute < 10 ? ("0" + minute) : minute) + " " + ((hourOfDay >= 12) ? "PM" : "AM");
				   data3= DateTimeUtils.getCommencingDate1(data[0])+" "+apmpm;
			}
			ipdForm.setAdmissiondate(data3);
			
			String specializationId=  userProfile.getDiciplineName();
			ipdForm.setDepartment(userProfile.getSpecialization());
			
			if(userProfile.getDoctor()!=null){
				
				 if(!userProfile.getDoctor().equals("") && !userProfile.getDoctor().equals("0")){
					 
					 UserProfile supportiveDoctorDetails= userProfileDAO.getUserprofileDetails(Integer.parseInt(userProfile.getDoctor()));  
					 String supportiveDoctorName= supportiveDoctorDetails.getInitial() + " " + supportiveDoctorDetails.getFirstname() + " " + supportiveDoctorDetails.getLastname();
					 String supportiveQualification=supportiveDoctorDetails.getQualification();
					 ipdForm.setSupportiveDoctorName(supportiveDoctorName);
					 ipdForm.setSupportiveQualification(supportiveQualification);
				 } 
			}
			
			
			
			boolean issystemicreview= masterDAO.isIpdFormFieldActive(specializationId,"Systemic Review");
			boolean history= masterDAO.isIpdFormFieldActive(specializationId,"History");
			boolean obstretic_history = masterDAO.isIpdFormFieldActive(specializationId,"OBSTRETIC HISTORY");
			boolean menstrual_history = masterDAO.isIpdFormFieldActive(specializationId, "MENSTRUAL HISTORY"); 
			boolean substance_history = masterDAO.isIpdFormFieldActive(specializationId, "SUBSTANCE HISTORY");
			boolean verification = masterDAO.isIpdFormFieldActive(specializationId, "Verification");
			boolean pediatric = masterDAO.isIpdFormFieldActive(specializationId, "Paediatric History");
			ipdForm.setNicuaccess(masterDAO.isIpdFormFieldActive(specializationId, "NICU Setting"));
			ArrayList<Master> pkgsList = investigationDAO.getInvPaksLists();
			ipdForm.setPkgsList(pkgsList);
			ArrayList<Master> invSectionList = investigationDAO.getInvestigationSectionList();
			ipdForm.setInvSectionList(invSectionList);
			ArrayList<Master> invsTypeList = emrDAO.getInvesigationTypeList();
			ipdForm.setInvsTypeList(invsTypeList);
			ArrayList<Master> invstUnitList = emrDAO.getInvstUnitList();
			ipdForm.setInvstUnitList(invstUnitList);
			ipdForm.setNicuaccess(pediatric);
			ipdForm.setVerification(verification);
			ipdForm.setIssystemicreview(issystemicreview);
			ipdForm.setHistory(history);
			ipdForm.setObstretic_history(obstretic_history);
			ipdForm.setMenstrual_history(menstrual_history);
			ipdForm.setSubstance_history(substance_history);
			ipdForm.setPaediatrichist(pediatric);

			/*
			 * //get repeat prescription list EmrDAO emrDAO = new
			 * JDBCEmrDAO(connection); ArrayList<Priscription>parentPriscList =
			 * emrDAO.getParentPriscList(bed.getClientid(),bed.getPractitionerid
			 * (),bed.getConditionid());
			 * ipdForm.setParentPriscList(parentPriscList);
			 */
			

		}

		catch (Exception e) {
			e.printStackTrace();
		}

	}

	private void listOfExtraData() {
		try {
			Connection connection = Connection_provider.getconnection();
			NotAvailableSlotDAO notAvailableSlotDAO = new JDBCNotAvailableSlotDAO(connection);
			EmrDAO emrDAO = new JDBCEmrDAO(connection);
			MasterDAO masterDAO = new JDBCMasterDAO(connection);
			UserProfileDAO userProfileDAO = new JDBCUserProfileDAO(connection);
			ClientDAO clientDAO = new JDBCClientDAO(connection);
			ArrayList<DiaryManagement> userList = notAvailableSlotDAO.getUserList(loginInfo.getId());
			ipdForm.setUserList(userList);

			// user define date time
			ipdForm.setHourList(PopulateList.hourListNew());
			ipdForm.setMinuteList(PopulateList.getMinuteList());
			String datetime = DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone());
			String temp[] = datetime.split(" ");
			String date = DateTimeUtils.getCommencingDate1(temp[0]);
			ipdForm.setAdmissiondate(date);
			ipdForm.setDischargedate(date);
			String time[] = temp[1].split(":");
			String hh = time[0];
			String mm = time[1];
			ipdForm.setHour(hh);
			ipdForm.setMinute(mm);

			ArrayList<com.apm.Master.eu.entity.Discharge> dischargeOutcomeList = emrDAO.getDischrageOutcomeList();
			ipdForm.setDischargeOutcomeList(dischargeOutcomeList);

			ArrayList<com.apm.Master.eu.entity.Discharge> dischargeStatusList = emrDAO.getDischrageStatusList();
			ipdForm.setDischargeStatusList(dischargeStatusList);

			String dates = DateTimeUtils.getDashboardTodayDate(loginInfo.getTimeZone());
			ipdForm.setPriscdate(dates);
			ipdForm.setPriscdateandtime(DateTimeUtils.getPriscDatetime(loginInfo.getTimeZone()));

			ArrayList<Master> dosageList = emrDAO.getDosageList();
			ipdForm.setDosageList(dosageList);

			ArrayList<Master> mdicneTypeList = emrDAO.getMedicineTypeList();
			ipdForm.setMdicneTypeList(mdicneTypeList);

			ArrayList<Master> mdicinecategoryList = emrDAO.getmedicineCategoryList();
			ipdForm.setMdicinecategoryList(mdicinecategoryList);

			ArrayList<Priscription> templateNameList = emrDAO.getTemplateNameList(loginInfo);
			if (templateNameList.size() == 0) {
				templateNameList = new ArrayList<Priscription>();
			}
			ipdForm.setTemplateNameList(templateNameList);
			ipdForm.setCountry("India");

			// set staff name list of OPERATING SURGEON and OPERATIVE PROCEDURE
			UserProfile userProfile = userProfileDAO.getUserprofileDetails(loginInfo.getId());
			ArrayList<Master> procedureList = notAvailableSlotDAO.getProcedureList(userProfile.getDiciplineName());
			if (procedureList.size() == 0) {
				procedureList = new ArrayList<Master>();
			}
			ipdForm.setProcedureList(procedureList);

			/*
			 * ArrayList<UserProfile> staffList =
			 * notAvailableSlotDAO.getOTstaffList(); if(staffList.size()==0){
			 * staffList = new ArrayList<UserProfile>(); }
			 * ipdForm.setStaffList(staffList);
			 */

			ArrayList<UserProfile> staffList = userProfileDAO.getAllPractitionerList("1", null, null, null, null);
			ipdForm.setStaffList(staffList);

			if (staffList.size() == 0) {
				staffList = new ArrayList<UserProfile>();
			}
			ArrayList<Client> anesthesiaList = userProfileDAO.getAllAnethesiaList();
			ipdForm.setAnesthesiaList(anesthesiaList);

			ArrayList<UserProfile> mlcdrlist = userProfileDAO.getAllPractitionerList(null, null, null, null, "1");
			ipdForm.setMlcdrlist(mlcdrlist);

			ArrayList<Master> declerationTitleList = clientDAO.getDeclerationTitleList();
			ipdForm.setDeclerationTitleList(declerationTitleList);

			// jitu
			ArrayList<Master> specializationTemplateList = masterDAO.getMasterSpecializationList();
			ipdForm.setSpecializationTemplateList(specializationTemplateList);
			// set template name list

			ArrayList<Master> priscUnitList = masterDAO.getPriscUnitList();
			ipdForm.setPriscUnitList(priscUnitList);
			//   11 April 2018
			ArrayList<Priscription> medicinetimelist = emrDAO.getMedicineTimeList();
			ipdForm.setMedicinetimelist(medicinetimelist);

			ArrayList<Master> medicineList = new ArrayList<Master>();
			ipdForm.setMedicineList(medicineList);

			ArrayList<Priscription> parentPriscList = new ArrayList<Priscription>();
			ipdForm.setParentPriscList(parentPriscList);

			ArrayList<Master> dosagenoteList = masterDAO.getDosageNoteList();
			ipdForm.setDosagenoteList(dosagenoteList);
			ArrayList<Client> thirdPartyTypeList = new ArrayList<Client>();
			thirdPartyTypeList = clientDAO.getThirdPartyType();
			ipdForm.setThirdPartyTypeList(thirdPartyTypeList);

			/*ArrayList<Master> nimaidoselist = masterDAO.getnimaidoselistt();
			ArrayList<Master> nimaiqtylist = masterDAO.getnimaiqtylist();
			ArrayList<Master> nimairemarklist = masterDAO.getnimairemarlist();*/
			
			ArrayList<Master> nimaidoselist = new ArrayList<>();
			ArrayList<Master> nimaiqtylist = new ArrayList<>();
			ArrayList<Master> nimairemarklist = new ArrayList<>();
			
			ipdForm.setNimaidoselist(nimaidoselist);
			ipdForm.setNimaiqtylist(nimaiqtylist);
			ipdForm.setNimairemarklist(nimairemarklist);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public String getinvestigationlistforipd() {
		
		try {
			BufferedReader br=request.getReader();
			String line="";
			String inputjson="";
			if((line=br.readLine())!=null){
				inputjson=inputjson+line;
			}
			JSONObject jsonObject= new JSONObject(inputjson);
			String ipdid=jsonObject.getString("ipdid");
			Connection connection = Connection_provider.getconnection();
			String list = "";
			InvestigationDAO investigationDAO = new JDBCInvestigationDAO(connection);
			list = investigationDAO.getListOFIpdInvestigation(ipdid);
			JSONObject jsonobj = new JSONObject();
			jsonobj.put("textdata", list);
			
			String responsetext=jsonobj.toString();
			response.setContentType("application/json");
			response.setHeader("Cache-Control", "no-cache");
			response.getWriter().write(responsetext);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public String savewardchargechangerate() {
		String clientid = request.getParameter("clientid");
		String wardid = request.getParameter("wardid");
		try {
			/*
			 * Connection connection= Connection_provider.getconnection();
			 * IpdDAO ipdDAO= new JDBCIpdDAO(connection); BedDao bedDao= new
			 * JDBCBedDao(connection); String ipdid=""; ipdid=
			 * ipdDAO.getLAstIpdIdByClient(clientid);
			 * ipdDAO.rateChangeFlagWard(ipdid,"1"); Bed bed=
			 * bedDao.getEditIpdData(ipdid);
			 * ipdDAO.rateChangeWardFromMaster(wardid, bed.getBedid());
			 */
			response.setContentType("text/html");
			response.setHeader("Cache-Control", "no-cache");
			response.getWriter().write("");

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public String newdischargeform() {
		try {
			if (!verifyLogin(request)) {
				return "login";
			}
			if (session.getAttribute("openedb") == null) {
				session.setAttribute("openedb", "ipd");
			}

			String selectedid = request.getParameter("selectedid");
			Connection connection= Connection_provider.getconnection();
			IpdDAO ipdDAO= new JDBCIpdDAO(connection);
			BedDao bedDao= new JDBCBedDao(connection);
			
			MasterDAO masterDAO= new JDBCMasterDAO(connection);
			NotAvailableSlotDAO notAvailableSlotDAO= new JDBCNotAvailableSlotDAO(connection);
			UserProfileDAO userProfileDAO= new JDBCUserProfileDAO(connection);
			ClientDAO clientDAO= new JDBCClientDAO(connection);
			IpdLogDAO ipdLogDAO= new JDBCIpdLogDAO(connection);
			DiaryManagementDAO diaryManagementDAO= new JDBCDiaryManagentDAO(connection);
			
			
			session.setAttribute("sessionadmissionid", selectedid);
			session.removeAttribute("finalConditions");
			if (session.getAttribute("openedb") == null) {
				session.setAttribute("openedb", "ipd");
			}
			
			// prepare data of IPDACTION LOKESH
						listOfExtraData();
						Ipd ipd = ipdDAO.getProcedureName(selectedid);

						Bed bed = bedDao.getEditIpdData(selectedid);
						session.setAttribute("sessionadmissionid", selectedid);
						String parentpriscid = ipdDAO.getParentPriscId(selectedid);
						ipdForm.setParentpriscid(parentpriscid);
						session.setAttribute("bed", bed);

						String procedureid = ipdDAO.getProcedureId(ipd.getProcedurename());
						ipdForm.setAppointmentText(procedureid);

						ipdForm.setNewadmndate(bed.getNewadmndate());
						ArrayList<Master> procedureList = notAvailableSlotDAO.getProcedureList(bed.getSpeciality());
						if (procedureList.size() == 0) {
							procedureList = new ArrayList<Master>();
						}

						ipdForm.setProcedureList(procedureList);

						if (loginInfo.getIpd_abbr_access() == 1) {
							String newipdabbr = ipdDAO.getIpdAbrivationIds(Integer.parseInt(selectedid));
							ipdForm.setNewipdabbr(newipdabbr);
							if (Integer.parseInt(bed.getIpdseqno()) > 0) {
								ipdForm.setIpdseqno(bed.getIpdseqno());
							} else {
								ipdForm.setIpdseqno(selectedid);
							}
						} else {
							if (Integer.parseInt(bed.getIpdseqno()) > 0) {
								ipdForm.setIpdseqno(bed.getIpdseqno());
								ipdForm.setNewipdabbr(bed.getIpdseqno());
							} else {
								ipdForm.setIpdseqno(selectedid);
								ipdForm.setNewipdabbr(selectedid);
							}
						}

						Client client = clientDAO.getClientDetails(bed.getClientid());

						UserProfile userProfile = userProfileDAO.getUserprofileDetails(Integer.parseInt(bed.getPractitionerid()));
						Master discipline = masterDAO.getDisciplineData(userProfile.getDiciplineName());
						ipdForm.setDiscipline(discipline.getDiscipline());
						ipdForm.setDoctor_name(
								userProfile.getInitial() + " " + userProfile.getFirstname() + " " + userProfile.getLastname());
						ipdForm.setDoctor_phone(userProfile.getMobile());
						ipdForm.setFullname(client.getFullname());
						ipdForm.setClient(ipdForm.getFullname());
						ipdForm.setAddress(client.getAddress());
						ipdForm.setContact(client.getMobNo());
						
						ipdForm.setAddr(client.getAddress() + "," + client.getTown() + "-" + client.getPostCode());
						ipdForm.setContact(client.getMobNo());
						
						
						ipdForm.setAgegender(client.getAge1()+"/"+client.getGender());
					
						
						String clientid= bed.getClientid();
						ipdForm.setPractitionerid(bed.getPractitionerid());
						ipdForm.setClientid(bed.getClientid());
						ipdForm.setAgeonadmn(bed.getAgeonAdmn());
						
						ipdForm.setQualification(userProfile.getQualification());

						String numcount = ipdDAO.getNumofAdmissionCount(clientid);
						ipdForm.setNum_admission(numcount);

						ipdForm.setKunal_final_diagnosis_text(bed.getKunal_final_diagnosis_text());
						ipdForm.setKunal_manual_medicine_text(bed.getKunal_manual_medicine_text());
						
						ArrayList<Bed> ipdconditionlist = bedDao.getIpdConditionList(selectedid);
						ipdForm.setIpdconditionlist(ipdconditionlist);
						
						Bed bedconditions = ipdDAO.getAllFinalCondition(selectedid, bed.getTreatmentepisodeid());

						ArrayList<Bed> finalConditions = new ArrayList<Bed>();

						if (bedconditions.getConditionname() != null) {

							for (String str : bedconditions.getConditionname().split(",")) {

								if (str == null) {
									continue;
								}
								if (str.equals("0")) {

									continue;
								}
								int id = Integer.parseInt(str);
								String conditionname = bedDao.getIpdConditionName(str);
								Bed bed2 = new Bed();
								bed2.setId(id);
								bed2.setConditionname(conditionname);
								bed2.setConditionid(str);
								finalConditions.add(bed2);

							}
						}
						ipdForm.setAbrivationid(client.getAbrivationid());

						ipdForm.setFinalConditions(finalConditions);

						ArrayList<Client> ipdCondtitionList = new ArrayList<Client>(); // clientDAO.getEmrTreatmentTypeList();
						session.setAttribute("finalConditions", finalConditions);
						session.setAttribute("ipdCondtitionmaster", ipdCondtitionList);
						
						String discharge_default_id = masterDAO.getIpdTemplateId("Discharge Default");
						String hospital_course_id = masterDAO.getIpdTemplateId("Hospital Course");
						String nursing_advice_id = masterDAO.getIpdTemplateId("Nursing Advice");
						String operative_notes_id = masterDAO.getIpdTemplateId("Operative Notes");
						String invenstigations = masterDAO.getIpdTemplateId("Investigations");
						String finding_on_discharge = masterDAO.getIpdTemplateId("FINDING ON DISCHARGE");
						String treatment_given_id = masterDAO.getIpdTemplateId("Discharge Treatment Given");
						String emergencydetailsid = masterDAO.getIpdTemplateId("Emergency Details");
						String treatmentgiventemplateid = masterDAO.getIpdTemplateId("Treatment Given");
						String onexami_id = masterDAO.getIpdTemplateId("On Examination");

						
						ArrayList<Master> discharge_default_list = masterDAO.getIpdTemplateListNames(discharge_default_id);
						ArrayList<Master> hospital_course_list = masterDAO.getIpdTemplateListNames(hospital_course_id);
						ArrayList<Master> nursing_advice_list = masterDAO.getIpdTemplateListNames(nursing_advice_id);
						ArrayList<Master> operativeList = masterDAO.getIpdTemplateListNames(operative_notes_id);
						ArrayList<Master> investigationList = masterDAO.getIpdTemplateListNames(invenstigations);
						ArrayList<Master> finding_on_dischargeList = masterDAO.getIpdTemplateListNames(finding_on_discharge);
						ArrayList<Master> treatment_given_list = masterDAO.getIpdTemplateListNames(treatment_given_id);
						ArrayList<Master> emergencydetailslist = masterDAO.getIpdTemplateListNames(emergencydetailsid);
						ArrayList<Master> treatmentgiventemplist = masterDAO.getIpdTemplateListNames(treatmentgiventemplateid);
						ArrayList<Master> on_exam_list = masterDAO.getIpdTemplateListNames(onexami_id);
						ipdForm.setOperativeList(operativeList);
						ipdForm.setDischarge_default_list(discharge_default_list);
						ipdForm.setHospital_course_list(hospital_course_list);
						ipdForm.setNursing_advice_list(nursing_advice_list);
						ipdForm.setInvestigationList(investigationList);
						ipdForm.setFinding_on_dischargeList(finding_on_dischargeList);
						ipdForm.setTreatment_given_list(treatment_given_list);
						ipdForm.setEmergencydetailslist(emergencydetailslist);
						ipdForm.setTreatmentgiventemplatelist(treatmentgiventemplist);
						ipdForm.setOn_exam_list(on_exam_list);
						ipdForm.setLamadamareason(bed.getReasonlamadama());
						
						ipdForm.setCommonTemplateList(ipdDAO.commonTemplateList());
						
						String chief_comlint_id = masterDAO.getIpdTemplateId("Chief Complaints");
						String present_ill_id = masterDAO.getIpdTemplateId("Present Illness");
                        String examination_id=masterDAO.getIpdTemplateId("Examination Findings");
                        
						ArrayList<Master> chief_complaints_list = masterDAO.getIpdTemplateListNames(chief_comlint_id);
						ArrayList<Master> present_illness_list = masterDAO.getIpdTemplateListNames(present_ill_id);
						ArrayList<Master> examination_finding_list=masterDAO.getIpdTemplateListNames(examination_id);
						ipdForm.setChief_complaints_list(chief_complaints_list);
						ipdForm.setPresent_illness_list(present_illness_list);
						ipdForm.setExamination_finding_list(examination_finding_list);
						String surgical_template = masterDAO.getIpdTemplateId("OT Template");
						ArrayList<Master> otherTemplateList = masterDAO.getIpdTemplateListNames(surgical_template);
						ipdForm.setOtherTemplateList(otherTemplateList);
						
						InvestigationDAO investigationDAO = new JDBCInvestigationDAO(connection);
						ArrayList<InvstTemplate> templateList = investigationDAO.getTemplateList();
						ipdForm.setInvestigationtemplatelist(templateList);
						
						String specializationId = userProfile.getDiciplineName();
						
						boolean issystemicreview = masterDAO.isIpdFormFieldActive(specializationId, "Systemic Review");
						boolean history = masterDAO.isIpdFormFieldActive(specializationId, "History");
						boolean obstretic_history = masterDAO.isIpdFormFieldActive(specializationId, "OBSTRETIC HISTORY");
						boolean menstrual_history = masterDAO.isIpdFormFieldActive(specializationId, "MENSTRUAL HISTORY");
						boolean substance_history = masterDAO.isIpdFormFieldActive(specializationId, "SUBSTANCE HISTORY");
						
						boolean verification = masterDAO.isIpdFormFieldActive(specializationId, "Verification");
						boolean pediatric = masterDAO.isIpdFormFieldActive(specializationId, "Paediatric History");
						
						ipdForm.setConditionid(bed.getConditionid());
						ipdForm.setVerification(verification);
						ipdForm.setIssystemicreview(issystemicreview);
						ipdForm.setHistory(history);
						ipdForm.setObstretic_history(obstretic_history);
						ipdForm.setMenstrual_history(menstrual_history);
						ipdForm.setSubstance_history(substance_history);
						ipdForm.setPaediatrichist(pediatric);
						
						
						
						String admissiondate = bed.getAdmissiondate();
						String[] data = admissiondate.split(" ");
						String data2 = DateTimeUtils.getCommencingDate1(data[0]);
						String data3 = data2 + " " + data[1];
						// ipdForm.setAdmissiondate(bed.getAdmissiondate());
						if (loginInfo.isBalgopal()) {
							String time[] = data[1].split(":");
							int hourOfDay = (Integer.parseInt(time[0]));
							int minute = (Integer.parseInt(time[1]));
							String apmpm = ((hourOfDay > 12) ? hourOfDay % 12 : hourOfDay) + ":"
									+ (minute < 10 ? ("0" + minute) : minute) + " " + ((hourOfDay >= 12) ? "PM" : "AM");
							data3 = DateTimeUtils.getCommencingDate1(data[0]) + " " + apmpm;
						}
						ipdForm.setAdmissiondate(data3);

						
						Bed bed1 = ipdDAO.getDischargeData(bed.getTreatmentepisodeid());
						ipdForm.setChkDischarge(bed1.isChkDischarge());
						ipdForm.setDischrgeOutcomes(bed1.getDischrgeOutcomes());
						ipdForm.setDischargeStatus(bed1.getDischargeStatus());
						ipdForm.setHospitalcourse(bed1.getHospitalcourse());
						
						if (bed1.getDischargedate() != null) {
							if (!bed1.getDischargedate().equals("")) {
								String temp[] = bed1.getDischargedate().split(" ");
								String date = DateTimeUtils.getCommencingDate1(temp[0]);
								ipdForm.setDischargedate(date);
								String time[] = temp[1].split(":");
								String hh = time[0];
								String mm = time[1];
								ipdForm.setHour(hh);
								ipdForm.setMinute(mm);
							}
						}
						ArrayList<Master> otdatesandids = ipdDAO.getOtDatesAndIdsFromdischarge(selectedid);
						ipdForm.setOtdatesandids(otdatesandids);
						int size1 = otdatesandids.size();
						if (size1 > 0) {
							String totalotids = otdatesandids.get(size1 - 1).getTotalids();
							ipdForm.setTotalotids(totalotids);
						} else {
							ipdForm.setTotalotids("0");
						}

						session.setAttribute("otdatesandids", otdatesandids);
						
						
						boolean isentryExist=ipdDAO.checkIfJsonNewDischargeFormStatusExist(selectedid);
						if(!isentryExist){
							ipdDAO.insereJsonNewDischargeFormStatus(selectedid);
						}
						ipdForm.setJsonipdid(selectedid);
						ipdForm.setSurgeon(bed1.getSurgeon());
						ipdForm.setAnesthesiologist(bed1.getAnesthesiologist());
						ipdForm.setAnesthesia(bed1.getAnesthesia());
						ipdForm.setAppointmentText(bed1.getAppointmentText());
						
						ipdForm.setAdmn_summdiv(ipdDAO.chkStatusOfJsonNewDischargeForm("admn_summ",selectedid));
						ipdForm.setHistrydiv(ipdDAO.chkStatusOfJsonNewDischargeForm("histry",selectedid));
						ipdForm.setSurgical_notesdiv(ipdDAO.chkStatusOfJsonNewDischargeForm("surgical_notes",selectedid));
						ipdForm.setHospital_coursediv(ipdDAO.chkStatusOfJsonNewDischargeForm("hospital_course",selectedid));
						ipdForm.setTreatmnt_givendiv(ipdDAO.chkStatusOfJsonNewDischargeForm("treatmnt_given",selectedid));
						ipdForm.setInvst_div(ipdDAO.chkStatusOfJsonNewDischargeForm("invst",selectedid));
						ipdForm.setOtherdiv(ipdDAO.chkStatusOfJsonNewDischargeForm("other",selectedid));
						ipdForm.setEmergency_detdiv(ipdDAO.chkStatusOfJsonNewDischargeForm("emergency_det",selectedid));
						
						// get prescription list
						ArrayList<Priscription> dischargePriscList = ipdDAO.getDischargePrescList(selectedid);
						if (dischargePriscList.size() > 0) {
							Priscription pr = dischargePriscList.get(dischargePriscList.size() - 1);
							ipdForm.setStrengthflag(pr.isStrengthflag());
							ipdForm.setRemarkflag(pr.isRemarkflag());
							ipdForm.setQuantityflag(pr.isStrengthflag());
						}
						String discadvoice = ipdDAO.getDIscPrisc(selectedid);

						ipdForm.setAdvoice(discadvoice);

						ipdForm.setDischargePriscList(dischargePriscList);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "newdischargeform";
	}

	
	public String getAdmissionDivDataOfDischargeForm(){
		try {
			Connection connection= Connection_provider.getconnection();
			BufferedReader br=request.getReader();
			String line="";
			String inputjson="";
			if((line=br.readLine())!=null){
				inputjson=inputjson+line;
			}
			JSONObject jsonObject= new JSONObject(inputjson);
			String ipdid=jsonObject.getString("ipdid");
			if(ipdid==null){
				ipdid="";
			}
			BedDao bedDao= new JDBCBedDao(connection);
			Bed bed = bedDao.getEditIpdData(ipdid);
			JSONObject jsonobj = new JSONObject();
			/*chiefcomplains,alergies,presentillness,headcircumference.midarmcircumference,length,wtaddmission,wtdischarge, on Next Hist   div familyhist,personalhist,earlierinvest,suggestedtrtment,suggestedtrtment,surgicalnotes,birthhist,diethist,developmenthist,emmunizationhist,example*/
			jsonobj.put("chiefcomplains", bed.getChiefcomplains());
			jsonobj.put("alergies", bed.getAlergies());
			jsonobj.put("presentillness", bed.getPresentillness());
			jsonobj.put("headcircumference", bed.getHeadcircumference());
			jsonobj.put("midarmcircumference", bed.getMidarmcircumference());
			jsonobj.put("length", bed.getLength());
			jsonobj.put("wtaddmission", bed.getWtaddmission());
			jsonobj.put("wtdischarge", bed.getWtdischarge());
			
			String responsetext=jsonobj.toString();
			response.setContentType("application/json");
			response.setHeader("Cache-Control", "no-cache");
			response.getWriter().write(responsetext);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	
public String getHistoryDivDataOfDischargeForm(){
	try {
		Connection connection= Connection_provider.getconnection();
		BufferedReader br=request.getReader();
		String line="";
		String inputjson="";
		if((line=br.readLine())!=null){
			inputjson=inputjson+line;
		}
		JSONObject jsonObject= new JSONObject(inputjson);
		String ipdid=jsonObject.getString("ipdid");
		if(ipdid==null){
			ipdid="";
		}
		IpdDAO ipdDAO= new JDBCIpdDAO(connection);
		BedDao bedDao= new JDBCBedDao(connection);
		Bed bed = bedDao.getEditIpdData(ipdid);
		Bed bed1 = ipdDAO.getDischargeData(bed.getTreatmentepisodeid());
		JSONObject jsonobj = new JSONObject();
		/* Hist   div familyhist,personalhist,earlierinvest,suggestedtrtment,suggestedtrtment,surgicalnotes,birthhist,diethist,developmenthist,emmunizationhist,example,onexamination,otNotes*/
		jsonobj.put("familyhist", bed.getFamilyhist());
		jsonobj.put("personalhist", bed.getPersonalhist());
		jsonobj.put("earlierinvest", bed.getEarlierinvest());
		jsonobj.put("suggestedtrtment", bed.getSuggestedtrtment());
		jsonobj.put("surgicalnotes", bed.getSurgicalnotes());
		jsonobj.put("birthhist", bed.getBirthhist());
		jsonobj.put("diethist", bed.getDiethist());
		jsonobj.put("developmenthist", bed.getDevelopmenthist());
		jsonobj.put("emmunizationhist", bed.getEmmunizationhist());
		
		jsonobj.put("onexamination", bed.getOnexamination());
		jsonobj.put("otNotes", bed1.getOtNotes());
		jsonobj.put("pasthistory", bed.getPasthistory());
		jsonobj.put("example", bed1.getExample());
		
		String responsetext=jsonobj.toString();
		response.setContentType("application/json");
		response.setHeader("Cache-Control", "no-cache");
		response.getWriter().write(responsetext);
		
	} catch (Exception e) {
		e.printStackTrace();
	}
	return null;
}	
	

public String getHospitalCourseDivDataOfDischargeForm(){
	try {
		Connection connection= Connection_provider.getconnection();
		BufferedReader br=request.getReader();
		String line="";
		String inputjson="";
		if((line=br.readLine())!=null){
			inputjson=inputjson+line;
		}
		JSONObject jsonObject= new JSONObject(inputjson);
		String ipdid=jsonObject.getString("ipdid");
		if(ipdid==null){
			ipdid="";
		}
		IpdDAO ipdDAO= new JDBCIpdDAO(connection);
		BedDao bedDao= new JDBCBedDao(connection);
		Bed bed = bedDao.getEditIpdData(ipdid);
		Bed bed1 = ipdDAO.getDischargeData(bed.getTreatmentepisodeid());
		JSONObject jsonobj = new JSONObject();
		/* Hist   div familyhist,personalhist,earlierinvest,suggestedtrtment,suggestedtrtment,surgicalnotes,birthhist,diethist,developmenthist,emmunizationhist,example,onexamination,otNotes*/
		jsonobj.put("hospitalcourse", bed1.getHospitalcourse());
		
		
		
		String responsetext=jsonobj.toString();
		response.setContentType("application/json");
		response.setHeader("Cache-Control", "no-cache");
		response.getWriter().write(responsetext);
		
	} catch (Exception e) {
		e.printStackTrace();
	}
	return null;
}

public String getTearmentGivenDivDataOfDischargeForm(){
	try {
		Connection connection= Connection_provider.getconnection();
		BufferedReader br=request.getReader();
		String line="";
		String inputjson="";
		if((line=br.readLine())!=null){
			inputjson=inputjson+line;
		}
		JSONObject jsonObject= new JSONObject(inputjson);
		String ipdid=jsonObject.getString("ipdid");
		if(ipdid==null){
			ipdid="";
		}
		IpdDAO ipdDAO= new JDBCIpdDAO(connection);
		BedDao bedDao= new JDBCBedDao(connection);
		Bed bed = bedDao.getEditIpdData(ipdid);
		Bed bed1 = ipdDAO.getDischargeData(bed.getTreatmentepisodeid());
		JSONObject jsonobj = new JSONObject();
		/* Hist   div familyhist,personalhist,earlierinvest,suggestedtrtment,suggestedtrtment,surgicalnotes,birthhist,diethist,developmenthist,emmunizationhist,example,onexamination,otNotes*/
		jsonobj.put("treatmentgiven", bed1.getTreatmentgiven());
		
		
		
		String responsetext=jsonobj.toString();
		response.setContentType("application/json");
		response.setHeader("Cache-Control", "no-cache");
		response.getWriter().write(responsetext);
		
	} catch (Exception e) {
		e.printStackTrace();
	}
	return null;
}


public String getOtherDivDataOfDischargeForm(){
	try {
		Connection connection= Connection_provider.getconnection();
		BufferedReader br=request.getReader();
		String line="";
		String inputjson="";
		if((line=br.readLine())!=null){
			inputjson=inputjson+line;
		}
		JSONObject jsonObject= new JSONObject(inputjson);
		String ipdid=jsonObject.getString("ipdid");
		if(ipdid==null){
			ipdid="";
		}
		IpdDAO ipdDAO= new JDBCIpdDAO(connection);
		BedDao bedDao= new JDBCBedDao(connection);
		Bed bed = bedDao.getEditIpdData(ipdid);
		Bed bed1 = ipdDAO.getDischargeData(bed.getTreatmentepisodeid());
		JSONObject jsonobj = new JSONObject();
		/*findondischarge,discadvnotes,kunal_manual_medicine_text*/
		if(bed1.getFindondischarge()==null){
			bed1.setFinaldiagnosis("");
		}
		if(bed1.getDiscadvnotes()==null){
			bed1.setDiscadvnotes("");
		}
		if(bed.getKunal_manual_medicine_text()==null){
			bed.setKunal_manual_medicine_text("");
		}
		
		jsonobj.put("findondischarge", bed1.getFindondischarge());
		jsonobj.put("discadvnotes", bed1.getDiscadvnotes());
		jsonobj.put("kunal_manual_medicine_text", bed.getKunal_manual_medicine_text());
		
		String responsetext=jsonobj.toString();
		response.setContentType("application/json");
		response.setHeader("Cache-Control", "no-cache");
		response.getWriter().write(responsetext);
		
	} catch (Exception e) {
		e.printStackTrace();
	}
	
	
	
	
	return  null;
}

public String getEmergencyDetailsDivDataOfDischargeForm(){
	try {
		Connection connection= Connection_provider.getconnection();
		BufferedReader br=request.getReader();
		String line="";
		String inputjson="";
		/*response.setHeader("Access-Control-Allow-Origin", "/*");
		response.setHeader("Access-Control-Allow-Methods", "GET");*/
		
		if((line=br.readLine())!=null){
			inputjson=inputjson+line;
		}
		JSONObject jsonObject= new JSONObject(inputjson);
		String ipdid=jsonObject.getString("ipdid");
		if(ipdid==null){
			ipdid="";
		}
		IpdDAO ipdDAO= new JDBCIpdDAO(connection);
		BedDao bedDao= new JDBCBedDao(connection);
		Bed bed = bedDao.getEditIpdData(ipdid);
		Bed bed1 = ipdDAO.getDischargeData(bed.getTreatmentepisodeid());
		JSONObject jsonobj = new JSONObject();
		/*findondischarge,discadvnotes,kunal_manual_medicine_text*/
		jsonobj.put("emergencydetail", bed1.getEmergencydetail());
		
		
		String responsetext=jsonobj.toString();
		response.setContentType("application/json");
		response.setHeader("Cache-Control", "no-cache");
		response.getWriter().write(responsetext);
		
	} catch (Exception e) {
		e.printStackTrace();
	}

	
	return null;
}


public String getInvstigationDivDataOfDischargeForm(){
	try {
		Connection connection= Connection_provider.getconnection();
		BufferedReader br=request.getReader();
		String line="";
		String inputjson="";
		if((line=br.readLine())!=null){
			inputjson=inputjson+line;
		}
		JSONObject jsonObject= new JSONObject(inputjson);
		String ipdid=jsonObject.getString("ipdid");
		if(ipdid==null){
			ipdid="";
		}
		IpdDAO ipdDAO= new JDBCIpdDAO(connection);
		BedDao bedDao= new JDBCBedDao(connection);
		Bed bed = bedDao.getEditIpdData(ipdid);
		Bed bed1 = ipdDAO.getDischargeData(bed.getTreatmentepisodeid());
		JSONObject jsonobj = new JSONObject();
		/*findondischarge,discadvnotes,kunal_manual_medicine_text*/
		jsonobj.put("investigation", bed1.getInvestigation());
		
		
		String responsetext=jsonobj.toString();
		response.setContentType("application/json");
		response.setHeader("Cache-Control", "no-cache");
		response.getWriter().write(responsetext);
		
	} catch (Exception e) {
		e.printStackTrace();
	}

	
	return null;
}


public String saveInvstigationDivDataOfDischargeForm(){
	try {
		Connection connection= Connection_provider.getconnection();
		BufferedReader br=request.getReader();
		String line="";
		String inputjson="";
		if((line=br.readLine())!=null){
			inputjson=inputjson+line;
		}
		JSONObject jsonObject= new JSONObject(inputjson);
		String ipdid=jsonObject.getString("ipdid");
		String investigation=jsonObject.getString("investigation");
		if(ipdid==null){
			ipdid="";
		}
		IpdDAO ipdDAO= new JDBCIpdDAO(connection);
		BedDao bedDao= new JDBCBedDao(connection);
		Bed bed = bedDao.getEditIpdData(ipdid);
		bed.setInvestigation(investigation);
	
		JSONObject jsonobj = new JSONObject();
		ipdDAO.saveInvestigationOfDischargeForm(bed);
		/*findondischarge,discadvnotes,kunal_manual_medicine_text*/
		jsonobj.put("investigation", "1");
		ipdDAO.updateJsonNewDischargeFormStatus(ipdid, "invst", "1");
		
		
		String responsetext=jsonobj.toString();
		response.setContentType("application/json");
		response.setHeader("Cache-Control", "no-cache");
		response.getWriter().write(responsetext);
		
	} catch (Exception e) {
		e.printStackTrace();
	}

	
	return null;
}


public String saveEmergencyDetailsDivDataOfDischargeForm(){
	try {
		Connection connection= Connection_provider.getconnection();
		BufferedReader br=request.getReader();
		String line="";
		String inputjson="";
		if((line=br.readLine())!=null){
			inputjson=inputjson+line;
		}
		JSONObject jsonObject= new JSONObject(inputjson);
		String ipdid=jsonObject.getString("ipdid");
		String emergencydetail=jsonObject.getString("emergencydet");
		if(ipdid==null){
			ipdid="";
		}
		IpdDAO ipdDAO= new JDBCIpdDAO(connection);
		BedDao bedDao= new JDBCBedDao(connection);
		Bed bed = bedDao.getEditIpdData(ipdid);
		bed.setEmergencydetail(emergencydetail);
	
		JSONObject jsonobj = new JSONObject();
		ipdDAO.saveEmergencyDetailsOfDischargeForm(bed);
		/*findondischarge,discadvnotes,kunal_manual_medicine_text*/
		jsonobj.put("emergencydet", "1");
		ipdDAO.updateJsonNewDischargeFormStatus(ipdid, "emergency_det", "1");
		
		String responsetext=jsonobj.toString();
		response.setContentType("application/json");
		response.setHeader("Cache-Control", "no-cache");
		response.getWriter().write(responsetext);
		
	} catch (Exception e) {
		e.printStackTrace();
	}

	
	return null;
}


public String saveTreatmentGivenDivDataOfDischargeForm(){
	try {
		Connection connection= Connection_provider.getconnection();
		BufferedReader br=request.getReader();
		String line="";
		String inputjson="";
		if((line=br.readLine())!=null){
			inputjson=inputjson+line;
		}
		JSONObject jsonObject= new JSONObject(inputjson);
		String ipdid=jsonObject.getString("ipdid");
		String treatmentgiven=jsonObject.getString("treatmentgiven");
		if(ipdid==null){
			ipdid="";
		}
		IpdDAO ipdDAO= new JDBCIpdDAO(connection);
		BedDao bedDao= new JDBCBedDao(connection);
		Bed bed = bedDao.getEditIpdData(ipdid);
		bed.setTreatmentgiven(treatmentgiven);
	
		JSONObject jsonobj = new JSONObject();
		ipdDAO.saveTearmentGivenDivDataOfDischargeForm(bed);
		ipdDAO.updateJsonNewDischargeFormStatus(ipdid, "treatmnt_given", "1");
		/*findondischarge,discadvnotes,kunal_manual_medicine_text*/
		jsonobj.put("treatmentgiven", "1");
		
		
		String responsetext=jsonobj.toString();
		response.setContentType("application/json");
		response.setHeader("Cache-Control", "no-cache");
		response.getWriter().write(responsetext);
		
	} catch (Exception e) {
		e.printStackTrace();
	}

	
	return null;
}


public String saveHospitalCourseDivDataOfDischargeForm(){
	try {
		Connection connection= Connection_provider.getconnection();
		BufferedReader br=request.getReader();
		String line="";
		String inputjson="";
		if((line=br.readLine())!=null){
			inputjson=inputjson+line;
		}
		JSONObject jsonObject= new JSONObject(inputjson);
		String ipdid=jsonObject.getString("ipdid");
		String hospitalcourse=jsonObject.getString("hospitalcourse");
		if(ipdid==null){
			ipdid="";
		}
		IpdDAO ipdDAO= new JDBCIpdDAO(connection);
		BedDao bedDao= new JDBCBedDao(connection);
		Bed bed = bedDao.getEditIpdData(ipdid);
		bed.setHospitalcourse(hospitalcourse);
	
		JSONObject jsonobj = new JSONObject();
		ipdDAO.saveHospitalCourseDivDataOfDischargeForm(bed);
		/*findondischarge,discadvnotes,kunal_manual_medicine_text*/
		jsonobj.put("hospitalcourse", "1");
		ipdDAO.updateJsonNewDischargeFormStatus(ipdid, "hospital_course", "1");
		
		String responsetext=jsonobj.toString();
		response.setContentType("application/json");
		response.setHeader("Cache-Control", "no-cache");
		response.getWriter().write(responsetext);
		
	} catch (Exception e) {
		e.printStackTrace();
	}

	
	return null;
}

public String saveHistoryDivDataOfDischargeForm(){
	try {
		Connection connection= Connection_provider.getconnection();
		BufferedReader br=request.getReader();
		String line="";
		String inputjson="";
		if((line=br.readLine())!=null){
			inputjson=inputjson+line;
		}
		JSONObject jsonObject= new JSONObject(inputjson);
		String ipdid=jsonObject.getString("ipdid");
		
		
		if(ipdid==null){
			ipdid="";
		}
		IpdDAO ipdDAO= new JDBCIpdDAO(connection);
		BedDao bedDao= new JDBCBedDao(connection);
		Bed bed = bedDao.getEditIpdData(ipdid);
		
		bed.setFamilyhist(jsonObject.getString("familyhist"));
		bed.setPersonalhist(jsonObject.getString("personalhist"));
		bed.setEarlierinvest(jsonObject.getString("earlierinvest"));
		bed.setSuggestedtrtment(jsonObject.getString("suggestedtrtment"));
		bed.setSurgicalnotes(jsonObject.getString("surgicalnotes"));
		bed.setBirthhist(jsonObject.getString("birthhist"));
		bed.setDiethist(jsonObject.getString("diethist"));
		bed.setDevelopmenthist(jsonObject.getString("developmenthist"));
		bed.setEmmunizationhist(jsonObject.getString("emmunizationhist"));
		bed.setOnexamination(jsonObject.getString("onexaminationlok"));
		bed.setPasthistory(jsonObject.getString("pasthistory"));
//		familyhist,personalhist,earlierinvest,suggestedtrtment,suggestedtrtment,surgicalnotes,birthhist,diethist,developmenthist,emmunizationhist
		JSONObject jsonobj = new JSONObject();
		Bed bed1 = ipdDAO.getDischargeData(bed.getTreatmentepisodeid());
		bed.setExample(jsonObject.getString("discharge_default"));
		bed.setOtNotes(jsonObject.getString("operation_notes"));
		ipdDAO.saveHistoryDivDataOfDischargeForm(bed);
		
		ipdDAO.updateJsonNewDischargeFormStatus(ipdid, "histry", "1");
		
		/*findondischarge,discadvnotes,kunal_manual_medicine_text*/
		jsonobj.put("hospitalcourse", "1");
		
		
		String responsetext=jsonobj.toString();
		response.setContentType("application/json");
		response.setHeader("Cache-Control", "no-cache");
		response.getWriter().write(responsetext);
		
	} catch (Exception e) {
		e.printStackTrace();
	}

	
	return null;
}



public String saveAdmissionDivDataOfDischargeForm(){
	try {
		Connection connection= Connection_provider.getconnection();
		BufferedReader br=request.getReader();
		String line="";
		String inputjson="";
		if((line=br.readLine())!=null){
			inputjson=inputjson+line;
		}
		JSONObject jsonObject= new JSONObject(inputjson);
		String ipdid=jsonObject.getString("ipdid");
		
		
		if(ipdid==null){
			ipdid="";
		}
		IpdDAO ipdDAO= new JDBCIpdDAO(connection);
		BedDao bedDao= new JDBCBedDao(connection);
		Bed bed = bedDao.getEditIpdData(ipdid);
		
		bed.setChiefcomplains(jsonObject.getString("chiefcomplains"));
		bed.setAlergies(jsonObject.getString("alergies"));
		bed.setPresentillness(jsonObject.getString("presentillness"));
		bed.setHeadcircumference(jsonObject.getString("headcircumference"));
		bed.setMidarmcircumference(jsonObject.getString("midarmcircumference"));
		bed.setLength(jsonObject.getString("length"));
		bed.setWtaddmission(jsonObject.getString("wtaddmission"));
		bed.setWtdischarge(jsonObject.getString("wtdischarge"));
		
		//chiefcomplains,alergies,presentillness,headcircumference.midarmcircumference,length,wtaddmission,wtdischarge	
		Bed bed1 = ipdDAO.getDischargeData(bed.getTreatmentepisodeid());
		bed.setExample(bed1.getExample());
		ipdDAO.saveAdmissionDataOfDischForm(bed);
		JSONObject jsonobj= new JSONObject();
		/*findondischarge,discadvnotes,kunal_manual_medicine_text*/
		jsonobj.put("hospitalcourse", "1");
		ipdDAO.updateJsonNewDischargeFormStatus(ipdid, "admn_summ", "1");
		
		String responsetext=jsonobj.toString();
		response.setContentType("application/json");
		response.setHeader("Cache-Control", "no-cache");
		response.getWriter().write(responsetext);
		
	} catch (Exception e) {
		e.printStackTrace();
	}

	
	return null;
}

public String saveOtherDivDataOfDischargeForm(){
	try {
		Connection connection= Connection_provider.getconnection();
		BufferedReader br=request.getReader();
		String line="";
		String inputjson="";
		if((line=br.readLine())!=null){
			inputjson=inputjson+line;
		}
		JSONObject jsonObject= new JSONObject(inputjson);
		String ipdid=jsonObject.getString("ipdid");
		
		
		if(ipdid==null){
			ipdid="";
		}
		IpdDAO ipdDAO= new JDBCIpdDAO(connection);
		BedDao bedDao= new JDBCBedDao(connection);
		Bed bed = bedDao.getEditIpdData(ipdid);
		
		String dayta=jsonObject.getString("discadvnotes");
		
//		System.out.println(dayta);
		bed.setFindondischarge(jsonObject.getString("finddis"));
		bed.setDiscadvnotes(jsonObject.getString("discadvnotes"));
		bed.setKunal_manual_medicine_text(jsonObject.getString("kunal_manual_medicine_text"));
		
		//chiefcomplains,alergies,presentillness,headcircumference.midarmcircumference,length,wtaddmission,wtdischarge	
		
		ipdDAO.saveOTHERDivDataOfDischargeForm(bed);
		JSONObject jsonobj= new JSONObject();
		/*findondischarge,discadvnotes,kunal_manual_medicine_text*/
		jsonobj.put("hospitalcourse", "1");
		ipdDAO.updateJsonNewDischargeFormStatus(ipdid, "other", "1");
		
		String responsetext=jsonobj.toString();
		response.setContentType("application/json");
		response.setHeader("Cache-Control", "no-cache");
		response.getWriter().write(responsetext);
		
	} catch (Exception e) {
		e.printStackTrace();
	}

	
	return null;
}


public String saveDiagnosisOfDischargeForm(){
	try {
		Connection connection= Connection_provider.getconnection();
		BufferedReader br=request.getReader();
		String line="";
		String inputjson="";
		if((line=br.readLine())!=null){
			inputjson=inputjson+line;
		}
		JSONObject jsonObject= new JSONObject(inputjson);
		String ipdid=jsonObject.getString("ipdid");
		
		
		if(ipdid==null){
			ipdid="";
		}
		IpdDAO ipdDAO= new JDBCIpdDAO(connection);
		BedDao bedDao= new JDBCBedDao(connection);
		Bed bed = bedDao.getEditIpdData(ipdid);
		
	
		
		String diagosis=(jsonObject.getString("diagosis"));
		String disstatusjson=(jsonObject.getString("disstatus"));
		String disOutcome=(jsonObject.getString("disOutcome"));
		String dischargedate=(jsonObject.getString("dischargedate"));
		String dishour=(jsonObject.getString("dishour"));
		String dismin=(jsonObject.getString("dismin"));
		String reasonformlama=(jsonObject.getString("reasonlamdama"));
		
		
		Bed bed1 = ipdDAO.getDischargeData(bed.getTreatmentepisodeid());

		String datetime = DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone());
		String allconditions = "0";
		
		if(diagosis!=null){
			for(String bedcondition:diagosis.split(",")){
				allconditions = allconditions + "," + bedcondition;
				boolean isexist = bedDao.isIpdExistCondition(ipdid, bed.getTreatmentepisodeid(),
						bedcondition);
				
				bed.setLastmodified(datetime);
				bed.setConditionid(bedcondition);
				if (!isexist) {
					int dd = bedDao.addIpdConditionReport(Integer.parseInt(ipdid), bed);
				}
			}
		}
		

		int result = ipdDAO.savefinalConditionDischarge(ipdid, bed.getTreatmentepisodeid(), datetime,
				allconditions);

		
		//chiefcomplains,alergies,presentillness,headcircumference.midarmcircumference,length,wtaddmission,wtdischarge	
		bed.setReasonlamadama(reasonformlama);
		DateFormat dateFormat1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	      Calendar cal1 = Calendar.getInstance();
	      String date = dateFormat1.format(cal1.getTime());  
	      bed.setDischargeDate(date);
		ipdDAO.saveOTHERDivDataOfDischargeForm(bed);
		
		 dischargedate = DateTimeUtils.getCommencingDate1(dischargedate) + " "
					+ dishour + ":" + dismin + ":20";

		 
		Bed bed2=bed1;
		bed2.setDischrgeOutcomes(disOutcome);
		bed2.setDischargeStatus(disstatusjson);
		 bed2.setDischargedate(dischargedate);
		 int status=0;
			int disstatus = ipdDAO.gettreatmentstatus(bed.getTreatmentepisodeid());
			if (disstatus == 1) {
				status = 1;
			}
			// if(ipdForm.isChkDischarge()){
			// status = 1;
			// }

			if (status == 1) {
				int res = bedDao.updateBedStatus(ipdid);
				int log = bedDao.saveDischargeLog(ipdid, bed.getClientid(), dischargedate, "", loginInfo);
			}

			bed2.setStatus("" + status);

			if (bed2.getAppointmentText() != null) {
				if (bed2.getAppointmentText().equals("0,") || bed2.getAppointmentText().equals("0, ")) {
					bed2.setAppointmentText("0");
				}
			}
			EmrDAO emrDAO=new JDBCEmrDAO(connection);
			String test = bed2.getAppointmentText();
			bed2.setTreatmentepisodeid(bed.getTreatmentepisodeid());
			if (status == 1) {

				
				
				int upd = emrDAO.updateTreatmentEpisodeDischargeForm(bed2);

				AllTemplateAction allTemplateAction = new AllTemplateAction();
				allTemplateAction.sendDischargeEmail(bed.getClientid(), bed.getConditionid(), bed.getPractitionerid(), "0", loginInfo, connection,
						bed.getTreatmentepisodeid());
			}else{
				int upd = emrDAO.updateTreatmentEpisodeDischargeForm(bed2);
			}

			String userid = loginInfo.getUserId();
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Calendar calendar = Calendar.getInstance();
			String todate = dateFormat.format(calendar.getTime());
			boolean flag = ipdDAO.getIsDisCheckListStatus("1", bed.getTreatmentepisodeid());

			if (!flag) {
				int res99 = ipdDAO.updateCheckListStatus("1", "1", bed.getTreatmentepisodeid(), userid, todate);
				int res1 = ipdDAO.updateCheckListStatus("1", "2", bed.getTreatmentepisodeid(), userid, todate);
				int res2 = ipdDAO.updateCheckListStatus("1", "3", bed.getTreatmentepisodeid(), userid, todate);
				int res3 = ipdDAO.updateCheckListStatus("1", "4", bed.getTreatmentepisodeid(), userid, todate);
				int disdataid1 = ipdDAO.getDisDataId("1", bed.getTreatmentepisodeid());
				int disdataid2 = ipdDAO.getDisDataId("2", bed.getTreatmentepisodeid());
				int disdataid3 = ipdDAO.getDisDataId("3", bed.getTreatmentepisodeid());
				int disdataid4 = ipdDAO.getDisDataId("4", bed.getTreatmentepisodeid());
				int res6 = ipdDAO.updateDischargeCKLIndi(disdataid1);
				int res7 = ipdDAO.updateDischargeCKLIndi(disdataid2);
				int res8 = ipdDAO.updateDischargeCKLIndi(disdataid3);
				int res9 = ipdDAO.updateDischargeCKLIndi(disdataid4);
			}
			



		 
		 
		 
		 
		 
		 
			 int rest=bedDao.updateUseridInTable(loginInfo.getUserId(), bed.getTreatmentepisodeid(), "lastformfilleruserid");	
		
		JSONObject jsonobj= new JSONObject();
		/*findondischarge,discadvnotes,kunal_manual_medicine_text*/
		jsonobj.put("hospitalcourse", "1");
		
		
		String responsetext=jsonobj.toString();
		response.setContentType("application/json");
		response.setHeader("Cache-Control", "no-cache");
		response.getWriter().write(responsetext);
		
	} catch (Exception e) {
		e.printStackTrace();
	}

	
	return null;
}

public String getSurgicalNotewsDivData(){
	try {
		Connection connection= Connection_provider.getconnection();
		BufferedReader reader=request.getReader();
		String ipline="";
		String line="";
		if((line=reader.readLine())!=null){
			ipline=ipline+line;
					
		}
		JSONObject jsonObject= new JSONObject(ipline);
		String ipdid=jsonObject.getString("ipdid");
		IpdDAO ipdDAO= new JDBCIpdDAO(connection);
		BedDao bedDao= new JDBCBedDao(connection);
		Bed bed = bedDao.getEditIpdData(ipdid);
		Bed bed1 = ipdDAO.getDischargeData(bed.getTreatmentepisodeid());
		JSONObject jsonobj = new JSONObject();
		/*findondischarge,discadvnotes,kunal_manual_medicine_text*/
		jsonobj.put("operation_notes", bed1.getOtNotes());
		
		
		String responsetext=jsonobj.toString();
		response.setContentType("application/json");
		response.setHeader("Cache-Control", "no-cache");
		response.getWriter().write(responsetext);
		
		
	} catch (Exception e) {
		e.printStackTrace();
	}
	return null;
}


public String saveSurgicalNotewsDivData(){
	try {
		Connection connection= Connection_provider.getconnection();
		BufferedReader br=request.getReader();
		String line="";
		String inputjson="";
		if((line=br.readLine())!=null){
			inputjson=inputjson+line;
		}
		JSONObject jsonObject= new JSONObject(inputjson);
		String ipdid=jsonObject.getString("ipdid");
		String otnotes=jsonObject.getString("otnotes");
	
		if(ipdid==null){
			ipdid="";
		}
		IpdDAO ipdDAO= new JDBCIpdDAO(connection);
		BedDao bedDao= new JDBCBedDao(connection);
		Bed bed = bedDao.getEditIpdData(ipdid);
		
		bed.setOtNotes(otnotes);
		bed.setSurgeon(jsonObject.getString("surgeon"));
		bed.setAnesthesiologist(jsonObject.getString("anesthesiologist"));
		bed.setAnesthesia(jsonObject.getString("anesthesia"));
		bed.setAppointmentText(jsonObject.getString("procedure"));
		//chiefcomplains,alergies,presentillness,headcircumference.midarmcircumference,length,wtaddmission,wtdischarge	
		
		ipdDAO.saveSurgicalNotesOfDiv(bed);
		JSONObject jsonobj= new JSONObject();
		/*findondischarge,discadvnotes,kunal_manual_medicine_text*/
		jsonobj.put("hospitalcourse", "1");
		ipdDAO.updateJsonNewDischargeFormStatus(ipdid, "surgical_notes", "1");
		
		String responsetext=jsonobj.toString();
		response.setContentType("application/json");
		response.setHeader("Cache-Control", "no-cache");
		response.getWriter().write(responsetext);
		
	} catch (Exception e) {
		e.printStackTrace();
	}

	
	return null;
}


public String refreshAjaxipTemplateList(){
	try {
		String tempid=request.getParameter("reqtempid");
		Connection connection= Connection_provider.getconnection();
		IpdDAO ipdDAO = new JDBCIpdDAO(connection);
		MasterDAO masterDAO= new JDBCMasterDAO(connection);
		ArrayList<Master> treatmentgiventemplist = masterDAO.getIpdTemplateListNames(tempid);
		StringBuffer buffer= new StringBuffer();
		String function="gettreattemplate(this.value)";
		if(tempid!=null){
			if(tempid.equals("7")){
				function="gettreatmentttemplate(this.value)";
			}else if(tempid.equals("2")){
				function="getpresentIllness(this.value)";
			}else if(tempid.equals("11")){
				function="getOTSurgicaltemplate(this.value)";
			}else if(tempid.equals("9")){
				function="gethosptemplate(this.value)";
			}else if(tempid.equals("")){
				function="getInvstemplate(this.value)";
			}else if(tempid.equals("12")){
				function="getemergencytemplate(this.value)";
			}else if(tempid.equals("16")){
				function="getFindOnDischtemplate(this.value)";
			}else if(tempid.equals("10")){
				function="getnursingtemplate(this.value)";
			}else if(tempid.equals("1")){
				function="setChiefComplaints(this.value)";
			}else if(tempid.equals("8")){
				function="getdisctemplate(this.value)";
			}else if(tempid.equals("6")){
				function="getonexamtemp123(this.value)";
			}
		}
		buffer.append("<select onchange='"+function+"' class='form-control'  id=''>");
		buffer.append("<option value='0'>Select Template</option>");
		for(Master master:treatmentgiventemplist){
			buffer.append("<option value='"+master.getId()+"'>"+master.getName()+"</option>");
		}
		buffer.append("</select>");
		
		response.setContentType("text/html");
		response.setHeader("Cache-Control", "no-cache");
		response.getWriter().write(buffer.toString());
	} catch (Exception e) {
		e.printStackTrace();
	}
	return null;
}


public String loadolddataofdiagnosis(){
	try {
		String diagnosisid=request.getParameter("diagid");
		String clientId=request.getParameter("clientid");
		Connection connection= Connection_provider.getconnection();
		IpdDAO ipdDAO= new JDBCIpdDAO(connection);
		
		ArrayList<Bed> ipdList=ipdDAO.getAllIpdidsOfDiagnosis(diagnosisid);
		
		StringBuffer buffer= new StringBuffer();
		buffer.append("<table id='diag' class='my-table xlstable' style='width:100%;border-bottom:0px solid gray !important'>");
		buffer.append("<tr><th><b>Discharge Card Id</b></th><th><b>Name</b> </th><th><b>Sex/Age</b> </th><th><b>Discharge Status</b></th></tr>");
		for(Bed bed:ipdList){
			if(bed!=null){
				buffer.append("<tr>");
				buffer.append("<td><input type='radio' class='chk' name='diagipdid'  value='"+bed.getIpdnewid()+"'>   <b><a onclick=opencPopup('printdischargeCommonnew?selectedid="+bed.getIpdnewid()+"&clientid=')>"+bed.getIpdseqno()+"</a></b></td>");
				buffer.append("<td><b>"+bed.getClientname()+"</b></td>");
				buffer.append("<td><b>"+bed.getGender()+" / "+bed.getAge()+"</b></td>");
				buffer.append("<td><b>"+bed.getDis_status_name()+"</b></td>");
				buffer.append("</tr>");
			}
		}
		buffer.append("</table>");
		
		
		ArrayList<Bed> previousipdlist=ipdDAO.getAllIpdOfClient(clientId);
		if(previousipdlist.size()>0){
			buffer.append("<br> <h4 align='center'><b> Also Your Previous Data</b></h4>");
			buffer.append("<table id='diag2' class='my-table xlstable' style='width:100%;border-bottom:0px solid gray !important'>");
			buffer.append("<tr><th><b>Discharge Card Id</b></th><th><b>Name</b> </th><th><b>Sex/Age</b> </th><th><b>Discharge Status</b></th></tr>");
			for(Bed bed:previousipdlist){
				if(bed!=null){
					buffer.append("<tr>");
					buffer.append("<td><input type='radio' class='chk' name='diagipdid'  value='"+bed.getIpdnewid()+"'>   <b><a onclick=opencPopup('printdischargeCommonnew?selectedid="+bed.getIpdnewid()+"&clientid=')>"+bed.getIpdseqno()+"</a></b></td>");
					buffer.append("<td><b>"+bed.getClientname()+"</b></td>");
					buffer.append("<td><b>"+bed.getGender()+" / "+bed.getAge()+"</b></td>");
					buffer.append("<td><b>"+bed.getDis_status_name()+"</b></td>");
					buffer.append("</tr>");
				}
			}
			buffer.append("</table>");
	
		}
				
		
		
		
		
		response.setContentType("text/html");
		response.setHeader("Cache-Control", "no-cache");
		response.getWriter().write(buffer.toString());
		
	} catch (Exception e) {
		e.printStackTrace();
	}
	return null;
}



public String givefollowup(){
	try {
		Connection connection=Connection_provider.getconnection();
		String ipdid=DateTimeUtils.isNull(request.getParameter("ipdid"));
		String date=DateTimeUtils.isNull(request.getParameter("date"));
		String isbookapmt=DateTimeUtils.isNull(request.getParameter("bookapmt"));
		BedDao bedDao= new JDBCBedDao(connection);
		Bed bed=bedDao.getEditIpdData(ipdid);
		ClientDAO clientDAO= new JDBCClientDAO(connection);
		Client client= new Client();
		client.setClientId(bed.getClientid());
		client.setIpdid(ipdid);
		client.setIpdid(ipdid);
		client.setType("1");
		client.setPractid(bed.getPractitionerid());
		client.setLocation("IPD");
		client.setFollowupdate(DateTimeUtils.getCommencingDate1(date));
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	     Calendar cal = Calendar.getInstance();
	     String toDaysDate = dateFormat.format(cal.getTime());
	     client.setDate(toDaysDate);
	    String practionerId=bed.getPractitionerid(); 
		
		if(isbookapmt.equals("true")){
			int followup=clientDAO.savefollowup(client);
			String newfollwupdate=date;
			newfollwupdate=DateTimeUtils.getCommencingDate1(newfollwupdate);
			AccountsDAO accountsDAO= new JDBCAccountsDAO(connection);
			String whopay=clientDAO.getWhoPayName(client.getClientId());
			AppointmentType appointmentType= accountsDAO.getFollowUpIdCharge(whopay);
			if(appointmentType.getCharges()!=null){
				NotAvailableSlotDAO notAvailableSlotDAO =  new JDBCNotAvailableSlotDAO(connection);	
				NotAvailableSlot n =notAvailableSlotDAO.getMveDiaryUserDetails(""+practionerId,newfollwupdate);
				
				
				String stime = n.getSTime();
				int slotid = n.getId();
				String duration = n.getDuration();
				boolean chkapmtexsist = notAvailableSlotDAO.chkmveapmtaxsist(""+practionerId,newfollwupdate);
				if(chkapmtexsist){
					stime = notAvailableSlotDAO.getmveapmtendtime(""+practionerId,newfollwupdate);
				}
				SimpleDateFormat df = new SimpleDateFormat("HH:mm");
				 Date d = df.parse(stime); 
				 Calendar cal1 = Calendar.getInstance();
				 cal1.setTime(d);
				 cal1.add(Calendar.MINUTE, 5);
				 String endtime = df.format(cal1.getTime());
				 
				 Client client2= clientDAO.getClientDetails(bed.getClientid());
				 
				 UserProfileDAO userProfileDAO= new JDBCUserProfileDAO(connection);
				 UserProfile user= new UserProfile();
					user=userProfileDAO.getUserprofileDetails(DateTimeUtils.convertToInteger(practionerId));
				 NotAvailableSlot notAvailableSlot= new NotAvailableSlot();
				
				 notAvailableSlot.setApmtSlotId(slotid);
				 notAvailableSlot.setCommencing(newfollwupdate);
				 notAvailableSlot.setSTime(stime);
				 notAvailableSlot.setEndTime(endtime);
				 notAvailableSlot.setDiaryUserId((DateTimeUtils.convertToInteger(practionerId)));
				 notAvailableSlot.setDiaryUser(user.getFullname());
				 notAvailableSlot.setLocation("1");
				 notAvailableSlot.setDept(user.getDiciplineName());
				 notAvailableSlot.setClient(client2.getFullname());
				 notAvailableSlot.setClientId(bed.getClientid());
				 notAvailableSlot.setApmtDuration("00:05");
				 notAvailableSlot.setApmtType(""+appointmentType.getId());
				 notAvailableSlot.setRoom("Room1");
				 notAvailableSlot.setPayBy("Client");
				 notAvailableSlot.setAddedBy(""+loginInfo.getUserId());
				 notAvailableSlot.setCondition(user.getDiciplineName());
				 notAvailableSlot.setVaccineApmt(true);
				 notAvailableSlot.setNotes("");
				 notAvailableSlot.setTreatmentEpisodeId("0");
				 notAvailableSlot.setUsedsession("0");
				 notAvailableSlot.setOtid(0);
				 notAvailableSlot.setOtplan(""+0);
				 notAvailableSlot.setProcedure(""+0);
				 notAvailableSlot.setSurgeon(""+0);
				 notAvailableSlot.setAnesthesia(""+0);
				 notAvailableSlot.setIpdno(""+0);
				 notAvailableSlot.setWardid(""+0);
				 notAvailableSlot.setAssistaffcharge(""+0);
				 notAvailableSlot.setSic(""+0);
				 notAvailableSlot.setPsurcharge(""+0);
				 notAvailableSlot.setPanetcharge(""+0);
					
				 
				 
				int x= notAvailableSlotDAO.saveAppointment(notAvailableSlot);
				 
				String message="Your Follow up is Scheduled on "+DateTimeUtils.getCommencingDate1(client.getDate())+" by "+user.getFullname()+" -"+loginInfo.getClinicName()+"";
				
				SmsService s= new SmsService();
				/*s.sendSms(message, client2.getMobNo(), loginInfo, new EmailLetterLog());*/
			}
		
		}
		response.setContentType("text/html");
		response.setHeader("Cache-Control", "no-cache");
		response.getWriter().write("");
	} catch (Exception e) {
		// TODO: handle exception
	}
	
	return null;
}
public String patientbarcode(){
	String clientId= DateTimeUtils.isNull(request.getParameter("id"));
	String uhid=DateTimeUtils.isNull(request.getParameter("abrivation"));
	String namefull=DateTimeUtils.isNull(request.getParameter("patientNameOrig"));
	try {
	/*	Connection connection= Connection_provider.getconnection();
		ClientDAO clientDAO= new JDBCClientDAO(connection);
		Client  client= clientDAO.getClientDetails(clientId);*/
		 /*  Code128 code128 = new Code128();
		   code128.setBarcodeWidth(3);
		   String imgname = clientId + "" + uhid + ".gif";
		   code128.setShowText(false);
		   code128.setData(""+uhid);
		  
		  
		   String filePath = request.getRealPath("/mdbarcode/"+imgname);
		   code128.drawBarcode(filePath);*/
			Client client= new Client();
		ClientDAO clientDAO= new JDBCClientDAO(Connection_provider.getconnection());
		client=clientDAO.getPatient(DateTimeUtils.convertToInteger(clientId));
		   ipdForm.setClientid(clientId);
		   ipdForm.setAbrivationid(client.getAbrivationid());
		   /*ipdForm.setFullname(client.getTitle()+" "+client.getFullname());*/
		   ipdForm.setFullname(""+client.getFullname());
	} catch (Exception e) {
	e.printStackTrace();
	}
	return "patientbarcode";
}

public String savepaymentreportnotes(){
	try {
		BufferedReader br= request.getReader();
		String line="",jsonString="";
		if((line=br.readLine())!=null){
			jsonString=jsonString+line;
		}
		
		JSONObject jsonObject= new JSONObject(jsonString);
		String note=jsonObject.getString("data");
		Connection connection= Connection_provider.getconnection();
		UserProfileDAO userProfileDAO= new JDBCUserProfileDAO(connection);
			DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Calendar cal = Calendar.getInstance();
			String date = dateFormat.format(cal.getTime());
		
		if(!(userProfileDAO.ifEntryExistUserPaymentReportNotes(loginInfo.getUserId(), date))){
			int res=userProfileDAO.insertToUserPaymentReportNotes(loginInfo, note, date);
		}else{
			int id=userProfileDAO.getIdOfPaymentReportNotes(loginInfo.getUserId(), date);
			int res=userProfileDAO.updateStatusUserPaymentReportNotes(loginInfo, "0", date, ""+id, note);
		}
		response.setContentType("application/json");
		response.setHeader("Cache-Control", "no-cache");
		response.getWriter().write("");
	} catch (Exception e) {
		e.printStackTrace();
	}
	return null;
} 


public String saveDeathNoteDivDataOfDischargeForm(){
	try {
		Connection connection= Connection_provider.getconnection();
		BufferedReader br=request.getReader();
		String line="";
		String inputjson="";
		if((line=br.readLine())!=null){
			inputjson=inputjson+line;
		}
		JSONObject jsonObject= new JSONObject(inputjson);
		String ipdid=jsonObject.getString("ipdid");
		String death_note=jsonObject.getString("death_note");
	
		if(ipdid==null){
			ipdid="";
		}
		IpdDAO ipdDAO= new JDBCIpdDAO(connection);
		BedDao bedDao= new JDBCBedDao(connection);
		Bed bed = bedDao.getEditIpdData(ipdid);
		
		bed.setDeathnote(death_note);
		
		//chiefcomplains,alergies,presentillness,headcircumference.midarmcircumference,length,wtaddmission,wtdischarge	
		
		ipdDAO.saveSurgicalNotesOfDiv(bed);
		JSONObject jsonobj= new JSONObject();
		/*findondischarge,discadvnotes,kunal_manual_medicine_text*/
		jsonobj.put("hospitalcourse", "1");
		
		ipdDAO.saveDeathNotesDivData(bed);
		
		response.setContentType("application/json");
		response.setHeader("Cache-Control", "no-cache");
		response.getWriter().write("");
	} catch (Exception e) {
		e.printStackTrace();
	}
	return null;
}

public String getDeathNoteDivDataOfDischargeForm(){
	try {
		Connection connection= Connection_provider.getconnection();
		BufferedReader br=request.getReader();
		String line="";
		String inputjson="";
		if((line=br.readLine())!=null){
			inputjson=inputjson+line;
		}
		JSONObject jsonObject= new JSONObject(inputjson);
		String ipdid=jsonObject.getString("ipdid");
		BedDao bedDao= new JDBCBedDao(connection);
		IpdDAO ipdDAO= new JDBCIpdDAO(connection);
		Bed bed = bedDao.getEditIpdData(ipdid);
		bed=ipdDAO.getDischargeData(bed.getTreatmentepisodeid());
		jsonObject.put("death_note", bed.getDeathnote());
		
		
		String responsetext=jsonObject.toString();
		response.setContentType("application/json");
		response.setHeader("Cache-Control", "no-cache");
		response.getWriter().write(""+responsetext);
	} catch (Exception e) {
		e.printStackTrace();
	}
	return null;
}

public String setaccessToThis(){
	try {
		Connection connection= Connection_provider.getconnection();
		ClinicDAO clinicDAO= new JDBCClinicDAO(connection);
		String colname=request.getParameter("col");
		String value=request.getParameter("value");
		int x=clinicDAO.switchAccessOfClinic(value, colname);
		response.setContentType("text/html");
		response.setHeader("Cache-Control", "no-cache");
		response.getWriter().write("" +x+ "");
	} catch (Exception e) {
		e.printStackTrace();
	}
	return null;
}
public String all_relesed_notes(){
	try {
		String fromDate=DateTimeUtils.isNull(ipdForm.getFromdate()),toDate=DateTimeUtils.isNull(ipdForm.getTodate());
		if(fromDate.equals("")){
			DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
			Calendar cal = Calendar.getInstance();
			fromDate = dateFormat.format(cal.getTime());
			toDate = dateFormat.format(cal.getTime());
		}
		Connection connection= Connection_provider.getconnection();
		UserAdministartionDAO userAdministartionDAO= new JDBCUserAdministration(connection);
		ArrayList<UserProfile> releaseList=new ArrayList<UserProfile>();
		releaseList=userAdministartionDAO.relesedNotesList(fromDate, toDate);
		ipdForm.setReleaseList(releaseList);
		ipdForm.setFromdate(fromDate);
		ipdForm.setTodate(toDate);
	} catch (Exception e) {
		e.printStackTrace();
	}
	return "all_relesed_notes";
}

public String saverelesed_notes(){
	try {
		Connection connection=Connection_provider.getconnection();
		File uploadedFile = ipdForm.getSubuploadfiles();
		String filecontenttype = ipdForm.getSubuploadfilesContentType();
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Calendar cal = Calendar.getInstance();
		String date = dateFormat.format(cal.getTime());
		
		String fileName= ipdForm.getSubuploadfilesFileName();
 		//String path =ServletActionContext.getre("/liveData/release_notes/");
		
		
		String path =request.getRealPath(("/liveData/release_notes/"));
		File uploadFolder = new File(path);
		if (!uploadFolder.exists()) {
			uploadFolder.mkdirs();
		}
		File fileToCreate = new File(path, fileName);
        FileUtils.copyFile(uploadedFile, fileToCreate);
        UserAdministartionDAO userAdministartionDAO= new JDBCUserAdministration(connection);
        UserProfile userProfile= new UserProfile();
        userProfile.setDatetime(date);
        userProfile.setFilename(fileName);
        userProfile.setWarname(DateTimeUtils.isNull(ipdForm.getWarname()));
        int uploaded=userAdministartionDAO.saveReleasedNotes(userProfile);
	} catch (Exception e) {
		e.printStackTrace();
	}
	return "saverelesed_notes";
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
		   master.setIsfromdcard(DateTimeUtils.isNull(request.getParameter("isfromdcard")));
		  String isfromclinical=DateTimeUtils.isNull(request.getParameter("isfromclinical"));
		  if(isfromclinical.equals("1")){
			  master.setDate(DateTimeUtils.getCommencingDate1(time.split(" ")[0]));
			  time=time.split(" ")[1];
			  master.setDatetime(master.getDate()+" "+time+":00");
		  }
		   
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



public String getnursingtasknamelist() throws Exception{
	Connection connection=null;
	try {
		connection=Connection_provider.getconnection();
		MasterDAO masterDAO = new JDBCMasterDAO(connection);
		String id = request.getParameter("id");
		ArrayList<Master>  arrayList = masterDAO.getNursingDetailsByNursingTypeid(id);
		StringBuilder str = new StringBuilder();
		str.append("<select class='form-control showToolTip chosen' name='taskname' id='taskname'>");
		str.append("<option value='0'>Select Taskname</option>");	
		for (Master master : arrayList) {
				str.append("<option value='"+master.getTaskname()+"'>"+master.getTaskname()+"</option>");
			}
		str.append("</select>");	  
		response.setContentType("text/html");
		response.setHeader("Cache-Control", "no-cache");
		response.getWriter().write(""+str.toString()+"");
	} catch (Exception e) {
		e.printStackTrace();
	}finally{
		connection.close();
	}
	return null;
}

public String getclinical_notes_new_list_to_emr(){
	StringBuffer buffer= new StringBuffer();
	try {
		String clientId=DateTimeUtils.isNull(request.getParameter("cid"));
		String practioner=DateTimeUtils.isNull(request.getParameter("pid"));
		Connection connection= Connection_provider.getconnection();
		EmrDAO emrDAO= new JDBCEmrDAO(connection);
		ArrayList<DietaryDetails> dietList=emrDAO.dietryList(clientId, practioner);
		ArrayList<Master> vitaList= emrDAO.vitalList(clientId, practioner);
		ArrayList<Master> nursingList=emrDAO.nursingcareList(clientId, practioner);
		
		if(dietList.size()>0){
			int i=1;
			buffer.append("<label>Diet List</label><br>");
			buffer.append("<table class='my-table'><tr><th>Sr.</th><th>Date / Time</th><th>Print</th></tr>");
			for(DietaryDetails diet:dietList){
				buffer.append("<tr><td>"+i+"</td> <td>"+diet.getLastmodified()+"</td>  <td><a href='#'><i class='fa fa-print' onclick=openPopup('printdiethistoryDietarydetails?parentid="+diet.getId()+"')></i></a></td></tr>");
				i++;
			}
			buffer.append("</table><br>");
		}
		
		if(vitaList.size()>0){
			int i=1;
			buffer.append("<label>Vital List</label><br>");
			buffer.append("<table class='my-table'><tr><th>Sr.</th><th>Ipd Number</th><th>Print</th></tr>");
			for(Master vital:vitaList){
				buffer.append("<td>"+i+"</td> <td>"+vital.getIpdid()+"</td>  <td><a href='#'><i class='fa fa-print' onclick=openPopup('vitalstatisticsIpd?clientid="+vital.getClientid()+"&ipdid="+vital.getIpdid()+"')></i></a></td>");
				i++;
			}
			buffer.append("</table><br>");
		}
		
		if(nursingList.size()>0){
			int i=1;
			buffer.append("<label>Nursing List</label><br>");
			buffer.append("<table class='my-table'><tr><th>Sr.</th><th>Date / Time</th><th>Print</th></tr>");
			for(Master nursing:nursingList){
				buffer.append("<td>"+i+"</td> <td>"+nursing.getDatetime()+"</td>  <td><a href='#'><i class='fa fa-print' ></i></a></td>");
				i++;
			}
			buffer.append("</table><br>");
		}
		
		
	
		
		response.setContentType("text/html");
		response.setHeader("Cache-Control", "no-cache");
		response.getWriter().write(""+buffer.toString()+"");
	} catch (Exception e) {
		e.printStackTrace();
	}
	
	return null;
}

public String setdichargecheckers(){
	try {
		Connection connection= Connection_provider.getconnection();
		BedDao bedDao = new JDBCBedDao(connection);
		BufferedReader br=request.getReader();
		String line="";
		String inputjson="";
		if((line=br.readLine())!=null){
			inputjson=inputjson+line;
		}
		JSONObject jsonObject= new JSONObject(inputjson);
		String ipdid=jsonObject.getString("ipdid");
		String val=jsonObject.getString("val");
		String column=jsonObject.getString("column");
		
		int res=bedDao.saveNewFieldsData(ipdid, column, val);
		
		String responsetext=jsonObject.toString();
		response.setContentType("application/json");
		response.setHeader("Cache-Control", "no-cache");
		response.getWriter().write(responsetext);
		
	} catch (Exception e) {
		e.printStackTrace();
	}
	return null;
}



public String savedischargefields(){
	try {
		Connection connection= Connection_provider.getconnection();
		BedDao bedDao = new JDBCBedDao(connection);
		BufferedReader br=request.getReader();
		String line="";
		String inputjson="";
		if((line=br.readLine())!=null){
			inputjson=inputjson+line;
		}
		JSONObject jsonObject= new JSONObject(inputjson);
		String ipdid=jsonObject.getString("ipdid");
		String val=jsonObject.getString("val");
		String column=jsonObject.getString("column");
		Bed bed= bedDao.getEditIpdData(ipdid);
		
		int res=bedDao.saveTreatmentEpisodeFieldsData(bed.getTreatmentepisodeid(), column, val);
		
		String responsetext=jsonObject.toString();
		response.setContentType("application/json");
		response.setHeader("Cache-Control", "no-cache");
		response.getWriter().write(responsetext);
		
	} catch (Exception e) {
		e.printStackTrace();
	}
	return null;
}


public String save_template_ajax(){
	try {
		Connection connection= Connection_provider.getconnection();
		IpdDAO ipdDAO= new JDBCIpdDAO(connection);
		BufferedReader br=request.getReader();
		String line="";
		String inputjson="";
		if((line=br.readLine())!=null){
			inputjson=inputjson+line;
		}
		JSONObject jsonObject= new JSONObject(inputjson);
		Master master= new Master();
		master.setName(jsonObject.getString("name"));
		master.setText(jsonObject.getString("text"));
		String boxId=jsonObject.getString("boxId");
		int res=ipdDAO.saveCommonTeplate(master);
		StringBuffer buffer= new StringBuffer();
		ArrayList<Master> list=ipdDAO.commonTemplateList();
		buffer.append("<select style='width:20%' onchange=getTemplateDataByAjax(this.value,'"+boxId+"')  class='form-control'>");
		buffer.append("<option value='0'> Select Template </option>");
		for(Master master2:list){
			buffer.append("<option value='"+master2.getId()+"'> "+master2.getName()+" </option>");
		}
		buffer.append("</select >");
		
		jsonObject.put("list", buffer.toString());
		
		response.setContentType("application/json");
		response.setHeader("Cache-Control", "no-cache");
		response.getWriter().write(jsonObject.toString());
	} catch (Exception e) {
		e.printStackTrace();
	}
	return null;
}


public String end()throws Exception{
	 if(!verifyLogin(request)) {
	 		return "login";
	 	}
	     Connection connection=null;
		try {
			connection=Connection_provider.getconnection();
			IpdDAO ipdDAO = new JDBCIpdDAO(connection);
			BedDao bedDao = new JDBCBedDao(connection);
			ClientDAO clientDAO= new JDBCClientDAO(connection);
			
			String sessionadmissionid = ipdForm.getClientip();
			String clietid = ipdForm.getClientid();
			String treatmentepisode = ipdForm.getTreatmentEpisode();
			String isfromdischargedashbaord = request.getParameter("isfromdischargedashbaord");
			Client client= clientDAO.getClientDetails(clietid);
			Bed bed = bedDao.getEditIpdData(sessionadmissionid);
			
			/*String dischargedate = ipdDAO.getIpdDischargeDate(treatmentepisode);
			*/
			String dischargedate= DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone());
			
			 int res = bedDao.updateBedStatus(sessionadmissionid);
			 int log = bedDao.saveDischargeLog(sessionadmissionid,clietid,dischargedate,bed.getBedid(),loginInfo);
			 
			 //set treatment sttaus
			 int update = ipdDAO.updateDischaregeStatus(treatmentepisode,dischargedate);
			 
			 //update in process charges
			 update = ipdDAO.updateInProcessCharges(sessionadmissionid);
			  int rest=bedDao.updateUseridInTable(loginInfo.getUserId(), bed.getTreatmentepisodeid(), "endeduserid");
			
			if(loginInfo.isKalmegha()){
				String originalDischargeDate = ipdDAO.getIpdDischargeDate(treatmentepisode);
				String diagnosis ="";
				
				Bed bedconditions = ipdDAO.getAllFinalCondition(sessionadmissionid, treatmentepisode);
				
				if (bedconditions.getConditionname() != null) {
					for (String str : bedconditions.getConditionname().split(",")) {
						if (str.equals("0")) {
							continue;
						}
						String conditionname = bedDao.getIpdConditionName(str);
						if(diagnosis.equals("")){
							diagnosis = conditionname;
						}else{
							diagnosis = diagnosis +","+conditionname;
						}
					}

				}
				int ressss = bedDao.updateKalmeghIpdData(diagnosis,originalDischargeDate,sessionadmissionid);
			}
			  
			  DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
			    Calendar cal = Calendar.getInstance();
			    
			    String dtt = dateFormat.format(cal.getTime());
			  SmsService s = new SmsService();
			  String msg=client.getFullname()+" : You have successfully completed discharge process on :"+dtt;
			  if(loginInfo.isDischarge_msg_hs()){
				  String templateid="";
				  s.sendSms(msg, client.getMobNo(), loginInfo, new EmailLetterLog(),templateid);
			  }
			session.setAttribute("ipddash_forreload", "1");
			
			//ipdDAO.createAdmissionView();
			//ipdDAO.createDischargeView();
			//for updating value of third party and who pay  manoj sir
			
			//commenting below line code as asked by Manoj sir
			
			//int result= clientDAO.updateThirdpartyandwhopay(clietid);
			
			 if(DateTimeUtils.isNull(isfromdischargedashbaord).equals("1")){
				 return "dishchargedashboard";
			 }
			  
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		finally{
			connection.close();
		}
		 return "ipddashboard";
}



public String email() throws Exception{
	if(!verifyLogin(request)){
		return "login";
	}
	Connection connection = null;
	
	try{
		int loginId = loginInfo.getId();
		connection = Connection_provider.getconnection();
		AccountsDAO accountsDAO = new JDBCAccountsDAO(connection);
		UserProfileDAO userProfileDAO = new JDBCUserProfileDAO(connection);
		BufferedReader br=request.getReader();
		String line="";
		String inputjson="";
		if((line=br.readLine())!=null){
			inputjson=inputjson+line;
		}
		JSONObject jsonObject= new JSONObject(inputjson);
		
		String id = request.getParameter("id");
		String to = request.getParameter("to");
		String cc = request.getParameter("cc");
		String subject = request.getParameter("subject");
		String notes = request.getParameter("notes");
		
		
		id=jsonObject.getString("id");
		to=jsonObject.getString("to");
		cc=jsonObject.getString("cc");
		subject=jsonObject.getString("subject");
		notes=jsonObject.getString("notes");
		String filename1="";
		String filename = "";
		if((filename).contains("/")){
			filename = (String)session.getAttribute("pdfFileName");
			String[] temp1 = filename.split("/");
			 filename1 = temp1[1];
		}
		
		
		int invoiceid = 0;
		if(session.getAttribute("chargesInvoiceid")!=null){
			invoiceid=(Integer)session.getAttribute("chargesInvoiceid");
		}
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		Date date = new Date();
		String d1 = dateFormat.format(date);
		String[] temp = d1.split("\\s+");
		String date1 = temp[0];
		String time = temp[1];
		String clientid = request.getParameter("clientid");
		String type = request.getParameter("type");
		UserProfile user1=userProfileDAO.getUserprofileDetails(1);
		 String pdffilename="";
		clientid=jsonObject.getString("clientid");
		type=jsonObject.getString("type");
		String filePath="";
		{
			  filePath = request.getRealPath("/liveData/document/");
				//String filename = "Invoice_"+clientid+"_"+invoiceid+".pdf";
			pdffilename = "Invoice_"+invoiceid+".pdf";
			 String filedata=""+jsonObject.getString("htmlContent");
			 filedata=filedata.replaceAll("[\\r\\n\\t]+", "");
			 StringBuffer buffer= new StringBuffer();
			// buffer.append("<html><body><h2>Hello</h2></body></html>");
			//s filedata=buffer.toString();
			// htmlToPdfFile(filedata, filePath, pdffilename);
			 buffer.append("<html><body>"
						+ "<div class='col-lg-12 col-xs-12 col-sm-12'>"
						+ "<label style='font-size: 25px;text-align:center;'><b>"+user1.getClinicname()+"</b></label></div>"
						+ "<div class='col-lg-12 col-xs-12 col-sm-12'>"
						+ "<label style='font-size: 15px;text-align:center;'><b>"+user1.getClinicowner()+"</b></label></div>"
						+ "<div class='col-lg-12 col-xs-12 col-sm-12'>"
						+ "<label style='font-size: 15px;text-align:center;'><b>"+user1.getAddress()+"</b></label></div>"
						+ "<div class='col-lg-12 col-xs-12 col-sm-12'>"
					    + "<label style='font-size: 15px;text-align:center;'><b>"+user1.getMobile()+"</b></label></div>"
					    + "<div class='col-lg-12 col-xs-12 col-sm-12'>"
					    + "<label style='font-size: 15px;text-align:center;'><b>"+user1.getEmail()+"</b></label>"
						+ "</div><br><br>"
					    + "<hr>");
			 buffer.append(filedata);
			 createPdf2(buffer.toString(),to);
			 filename=pdffilename;	
			  session.setAttribute("pdfFileName", filePath+"/"+pdffilename);
				
		}
		
		
		
		int result = accountsDAO.saveEmailLogDetails(to, cc, subject, notes, filePath+"/"+pdffilename,invoiceid,DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone()),time,type);
		
		//set deliver status
		int update = accountsDAO.updateDeliverStatus(invoiceid,"2");
		String status = "Email";
		//int upPaymentStatus = accountsDAO.updatePaymentDeliverStatus(id,status);
		
		EmailLetterLog emailLetterLog = new EmailLetterLog();
		emailLetterLog.setClientId(clientid);
		emailLetterLog.setType(status);
		
		EmbeddedImageEmailUtil.sendMailAttachment(connection,loginId,to, cc, subject, notes, filePath+"/"+pdffilename,loginInfo,emailLetterLog,"0");
		response.setContentType("application/json");
		response.setHeader("Cache-Control", "no-cache");
		response.getWriter().write(jsonObject.toString());
		
		
	}catch(Exception e){
		e.printStackTrace();
	}finally{
		
		connection.close();
	}
	
return null;
}



private void createPdf2(String filedata, String to) {
	//String smsUrl = "http://157.119.43.54:8081/PdfProject1/generate-invoicepdf/"+filedata+"/"+to;
	
		//String smsUrl = "http://localhost:8080/PdfProject1/generate-invoicepdf/"+filedata+"/"+to;
	
	try{
		
		
		
		 String jsonInput = "{\"html\": \"" + filedata.replace("\"", "\\\"") + "\", \"to\": \"" + to + "\"}";

		    // Connect to Spring Boot
		    URL url = new URL("http://localhost:8080/generate-invoicepdf");
		    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		
			/*
			 * URL url = new URL(smsUrl); // get url from url string
			 * 
			 * URLConnection urlConnection = url.openConnection(); // open url connection
			 * urlConnection.connect(); // connect to url
			 * 
			 * HttpURLConnection conn = (HttpURLConnection) urlConnection; // get http url
			 * connection object from url connection
			 */		

	    conn.setRequestMethod("POST");
	    conn.setRequestProperty("Content-Type", "application/json");
	    conn.setDoOutput(true);

	    // Send data
	    try (OutputStream os = conn.getOutputStream()) {
	        byte[] input = jsonInput.getBytes("utf-8");
	        os.write(input, 0, input.length);
	    }

	    // Read response
	    StringBuilder responseBuilder = new StringBuilder();
	    try (BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream(), "utf-8"))) {
	        String responseLine;
	        while ((responseLine = br.readLine()) != null) {
	            responseBuilder.append(responseLine.trim());
	        }
	    }

	    String result = responseBuilder.toString();
	    System.out.println("Spring response: " + result);
	    
		
	} catch (Exception e) {
		e.printStackTrace();
	}
	
}



public void htmlToPdfFile(String htmlString, String filepath,
		String fileaName) throws Exception {
	
	
		try {
			

			//CYaHPConverter converter = new CYaHPConverter();
			File fout = new File(filepath + "/" + fileaName);
			FileOutputStream out = new FileOutputStream(fout);
		//	Map properties = new HashMap();
			ArrayList headerFooterList = new ArrayList();

			/*
			 * properties.put(IHtmlToPdfTransformer.PDF_RENDERER_CLASS,
			 * IHtmlToPdfTransformer.FLYINGSAUCER_PDF_RENDERER);
			 */
			// properties.put(IHtmlToPdfTransformer.FOP_TTF_FONT_PATH, fontPath);
			/*
			 * converter.convertToPdf(htmlString, IHtmlToPdfTransformer.A4P,
			 * (java.util.List) headerFooterList, "file:///temp/", // root for // relative
			 * // external // CSS and // IMAGE out, properties);
			 */
			
			

CYaHPConverter converter = new CYaHPConverter();

Map<String, Object> properties = new HashMap<>();
properties.put(IHtmlToPdfTransformer.PDF_RENDERER_CLASS,
        "org.allcolor.yahp.converter.CFlyingSaucerRenderer");

converter.convertToPdf(
    htmlString,
    IHtmlToPdfTransformer.A4P,
    (List) headerFooterList,
    "file:///temp/", // base URL for relative CSS/image paths
    out,
    properties
);
			
			out.flush();
			out.close();
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
}


public String saveToPdf(){
	try{
		String inputfile="3212_IPD.pdf";
		String  uploadDirName = request.getRealPath("/liveData/document/");
		File f=new File(inputfile);
		FileInputStream fis=null;
		FileOutputStream fo=null;
		File f1=new File(uploadDirName+"/"+f.getName());
		
		fis=new FileInputStream(f);
		fo=new FileOutputStream(f1);
		
		
		try
		{
		byte buf[] = new byte[1024*8]; /* declare a 8kB buffer */
		int len = -1;
		while((len = fis.read(buf)) != -1)
		{
		fo.write(buf, 0, len);
		}
		}
		catch(Exception e)
		{
		e.printStackTrace();
		}
		
		
		
		
		
		
	}catch (Exception e) {
		e.printStackTrace();
	}
	return null;
}
public String deletedoc(){
	try {
		String id=request.getParameter("docid");
		Connection connection= Connection_provider.getconnection();
		EmrDAO emrDAO = new JDBCEmrDAO(connection);
		int result = emrDAO.deleteDocuments(id);
		response.setContentType("text/html");
		response.setHeader("Cache-Control", "no-cache");
		response.getWriter().write("");
	} catch (Exception e) {
		e.printStackTrace();
	}
	return null;
} 


public String dischargeformtemplatelist(){
	try {
		
		Connection connection=Connection_provider.getconnection();
		
		
		response.setContentType("application/json");
		response.setHeader("Cache-Control", "no-cache");
		response.getWriter().write("");
	} catch (Exception e) {
		e.printStackTrace();
	}
	
	return null;
}

public String updateusersign(){
	try {
		String invstid=DateTimeUtils.isNull(request.getParameter("invstid"));
		String userid=DateTimeUtils.isNull(request.getParameter("userid"));
		Connection connection= Connection_provider.getconnection();
		InvestigationDAO investigationDAO= new JDBCInvestigationDAO(connection);
		
		investigationDAO.updateSingUser(invstid, userid);
		response.setContentType("text/html");
		response.setHeader("Cache-Control", "no-cache");
		response.getWriter().write("");
	} catch (Exception e) {
		e.printStackTrace();
	}
	return null;
} 

public String gynicassesmentform() throws Exception{
	
	if(!verifyLogin(request)) {
		
		return "login";
	}
	
	//String wardid = request.getParameter("wardid");
	
	String bedid = request.getParameter("bedid");
	String action = "0";
	String clientid =request.getParameter("clientid");
	String practid = request.getParameter("practid");
			
	Connection connection = null;
	try{
		connection = Connection_provider.getconnection();
		IpdDAO ipdDAO = new JDBCIpdDAO(connection);
		BedDao bedDao = new JDBCBedDao(connection);
		MasterDAO masterDAO=new JDBCMasterDAO(connection);
		ClientDAO clientDAO= new JDBCClientDAO(connection);
		Client client=clientDAO.getClientDetails(clientid);
		String fullname= client.getTitle()+" "+client.getFirstName()+" "+client.getLastName();
		ipdForm.setClient(fullname);
		ipdForm.setAbrivationid(client.getAbrivationid());
		ipdForm.setClientid(clientid);
		ipdForm.setAddress(client.getAddress());
		ipdForm.setContact(client.getMobNo());
		
		ipdForm.setPractitionerid(practid);
		
		String formtype= ipdForm.getFormtype();
		if(formtype==null){
			formtype="1";
		}
		if(loginInfo.isVermanh()){
			formtype="4";
		}
		ArrayList<String> reasonVisitList = new ArrayList<String>();
		
		if(loginInfo.isVermanh()){
			reasonVisitList.add("Amenorrhea ");
			reasonVisitList.add("Leaking PV");
			reasonVisitList.add("Pain in lower abdomen");
			reasonVisitList.add("Nausea");
			reasonVisitList.add("Vomiting");
			reasonVisitList.add("Bleeding PV");
			reasonVisitList.add("Burning Micturition");
			reasonVisitList.add("White discharge PV");
			reasonVisitList.add("Backache");
			reasonVisitList.add("Others");
			reasonVisitList.add("Spotting PV");
			reasonVisitList.add("Fetal movements");
			reasonVisitList.add("Epigastric pain");
			reasonVisitList.add("Oedema");
		}else{
			reasonVisitList= getReasonVisitData(formtype);
		}
		
		
		String chief_comlint_id=masterDAO.getIpdTemplateId("Chief Complaints");
		String present_ill_id=masterDAO.getIpdTemplateId("Present Illness");
		String past_history_id=masterDAO.getIpdTemplateId("Past History");
		String family_hist_id=masterDAO.getIpdTemplateId("Family History");
		String personal_hist_id=masterDAO.getIpdTemplateId("Personal History");
		String onexami_id=masterDAO.getIpdTemplateId("On Examination");
		String tretment_given_id=masterDAO.getIpdTemplateId("Treatment Given");
		String examination_id=masterDAO.getIpdTemplateId("Examination Findings");
		
		ArrayList<Master> chief_complaints_list=masterDAO.getIpdTemplateListNames(chief_comlint_id);
		ArrayList<Master> present_illness_list=masterDAO.getIpdTemplateListNames(present_ill_id);
		ArrayList<Master> past_history_list=masterDAO.getIpdTemplateListNames(past_history_id);
		ArrayList<Master> family_history_list=masterDAO.getIpdTemplateListNames(family_hist_id);
		ArrayList<Master> personal_hist_list=masterDAO.getIpdTemplateListNames(personal_hist_id);
		ArrayList<Master> on_exam_list=masterDAO.getIpdTemplateListNames(onexami_id);
		ArrayList<Master> treatment_given_list=masterDAO.getIpdTemplateListNames(tretment_given_id);
		ArrayList<Master> examination_finding_list=masterDAO.getIpdTemplateListNames(examination_id);
		
		ipdForm.setReasonVisitList(reasonVisitList);
		ipdForm.setChief_complaints_list(chief_complaints_list);
		ipdForm.setPresent_illness_list(present_illness_list);
		ipdForm.setPast_history_list(past_history_list);
		ipdForm.setFamily_history_list(family_history_list);
		ipdForm.setPersonal_hist_list(personal_hist_list);
		ipdForm.setOn_exam_list(on_exam_list);
		ipdForm.setTreatment_given_list(treatment_given_list);
		ipdForm.setExamination_finding_list(examination_finding_list);
		
		ArrayList<Bed> wardlist=bedDao.getAllWardList(action);
		ipdForm.setWardlist(wardlist);
		ipdForm.setFormtype(formtype);
		
		ArrayList<Bed> gynicobsList= new ArrayList<Bed>();
		ipdForm.setGynicobsList(gynicobsList);
		
		//priscription 
		ArrayList<Master>medicineList = new ArrayList<Master>();
		ipdForm.setMedicineList(medicineList);
		EmrDAO emrDAO = new JDBCEmrDAO(connection);
		ArrayList<Priscription>templateNameList = emrDAO.getTemplateNameList(loginInfo);
		if(templateNameList.size()==0){
			templateNameList = new ArrayList<Priscription>();
		}
		ipdForm.setTemplateNameList(templateNameList);
		ArrayList<Priscription>parentPriscList = new ArrayList<Priscription>();
		ipdForm.setParentPriscList(parentPriscList);
		ArrayList<Master>dosageList = emrDAO.getDosageList();
		ipdForm.setDosageList(dosageList);
		ArrayList<Priscription> medicinetimelist = emrDAO.getMedicineTimeList(); 
		ipdForm.setMedicinetimelist(medicinetimelist);
		ArrayList<Master>dosagenoteList = masterDAO.getDosageNoteList();
		ipdForm.setDosagenoteList(dosagenoteList);
		ArrayList<Master>nimaidoselist = masterDAO.getnimaidoselistt();
		ArrayList<Master>nimaiqtylist = masterDAO.getnimaiqtylist();
		ArrayList<Master>nimairemarklist = masterDAO.getnimairemarlist();
		ipdForm.setNimaidoselist(nimaidoselist);
		ipdForm.setNimaiqtylist(nimaiqtylist);
		ipdForm.setNimairemarklist(nimairemarklist);
		ArrayList<Master> priscUnitList= masterDAO.getPriscUnitList();
		ipdForm.setPriscUnitList(priscUnitList);
		ArrayList<Master>mdicneTypeList = emrDAO.getMedicineTypeList();
		ipdForm.setMdicneTypeList(mdicneTypeList);
		ArrayList<Master>mdicinecategoryList = emrDAO.getmedicineCategoryList();
		ipdForm.setMdicinecategoryList(mdicinecategoryList);
		ArrayList<Master> specializationTemplateList= masterDAO.getMasterSpecializationList();
		ipdForm.setSpecializationTemplateList(specializationTemplateList);
		
		//investigation
		UserProfileDAO userProfileDAO = new JDBCUserProfileDAO(connection);
		InvestigationDAO investigationDAO = new JDBCInvestigationDAO(connection);
		ArrayList<String> jobTitleList = userProfileDAO.getJobTitleList();
		ipdForm.setJobTitleList(jobTitleList);
		UserProfile userProfile = userProfileDAO.getUserprofileDetails(loginInfo.getId());
		ipdForm.setJobtitle("Pathlab");
		ArrayList<Master> pkgsList = investigationDAO.getInvPaksLists();
		ipdForm.setPkgsList(pkgsList);
		ArrayList<Master> invsTypeList = emrDAO.getInvesigationTypeList();
		ipdForm.setInvsTypeList(invsTypeList);
		ArrayList<Master> invstReportTypeList = emrDAO.getInvstReportTypeList();
		ipdForm.setInvstReportTypeList(invstReportTypeList);
		ArrayList<Master> invstUnitList = emrDAO.getInvstUnitList();
		ipdForm.setInvstUnitList(invstUnitList);
		ArrayList<Master> invSectionList = investigationDAO.getInvestigationSectionList();
		ipdForm.setInvSectionList(invSectionList);
		
		if(loginInfo.isVermanh()){
			
			NotAvailableSlotDAO availableSlotDAO = new JDBCNotAvailableSlotDAO(connection);
			int lastappointmentid = availableSlotDAO.getLastAppointmentId(clientid);
			ipdForm.setLastappointmentid(lastappointmentid);
			
			ArrayList<Priscription> gynicPriscList = new ArrayList<Priscription>();
			gynicPriscList=ipdDAO.getGynicPrescList(""+lastappointmentid);
			ipdForm.setGynicPriscList(gynicPriscList);
			
			int id = ipdDAO.getLastGynicformId(clientid);
			Bed bed= ipdDAO.getEditNewGynicFormData(""+id);
			ArrayList<Bed> gynicobsList1 = new ArrayList<Bed>();
			if(id>0){
				gynicobsList1= bedDao.getNewGynicObsListByGynicId(""+id);
			}
			ipdForm.setGynicobsList(gynicobsList1);
			
			ipdForm.setHrf_gdm(DateTimeUtils.booleanCheck(bed.getHrf_gdm()));
		    ipdForm.setHrf_pih(DateTimeUtils.booleanCheck(bed.getHrf_pih()));
		    ipdForm.setHrf_boh(DateTimeUtils.booleanCheck(bed.getHrf_boh()));
		    ipdForm.setHrf_hypothyroid(DateTimeUtils.booleanCheck(bed.getHrf_hypothyroid()));
		    ipdForm.setHrf_hyperthyroid(DateTimeUtils.booleanCheck(bed.getHrf_hyperthyroid()));
		    ipdForm.setHrf_prev_lscs(DateTimeUtils.booleanCheck(bed.getHrf_prev_lscs()));
		    ipdForm.setHrf_rh_negative(DateTimeUtils.booleanCheck(bed.getHrf_rh_negative()));
		    ipdForm.setHrf_other(DateTimeUtils.booleanCheck(bed.getHrf_other()));
		    
		    ipdForm.setNulligravida(DateTimeUtils.booleanCheck(bed.getNulligravida()));
		    ipdForm.setCurrent_pregnent(DateTimeUtils.booleanCheck(bed.getCurrent_pregnent()));
		    ipdForm.setIud(DateTimeUtils.booleanCheck(bed.getIud()));
		    
		    ipdForm.setLive_boys(bed.getLive_boys());
			ipdForm.setLive_girls(bed.getLive_girls());
			ipdForm.setStillbirths(bed.getStillbirths());
			ipdForm.setAbortions(bed.getAbortions());
			ipdForm.setDeath_children(bed.getDeath_children());
			
			ipdForm.setG(DateTimeUtils.isNull(bed.getG()));
			ipdForm.setP(bed.getP());
			ipdForm.setL(bed.getL());
			ipdForm.setA(bed.getA());
			ipdForm.setD(bed.getD());
			ipdForm.setParity_abortion_notes(bed.getParity_abortion_notes());
			String vitallmpdate ="";
			if(DateTimeUtils.isNull(bed.getLmp()).equals("")){
				vitallmpdate= bedDao.getLastVitalLmpDate(lastappointmentid);
			}else{
				vitallmpdate = bed.getLmp();
			}
			ipdForm.setLmp(vitallmpdate);
			
			//ipdForm.setLmp(bed.getLmp());
			ipdForm.setDating_usg_date(bed.getDating_usg_date());
			ipdForm.setEdd(bed.getEdd());
			ipdForm.setWeeks_dating_scan(bed.getWeeks_dating_scan());
			ipdForm.setPog(bed.getPog());
			ipdForm.setPog_dating_scan(bed.getPog_dating_scan());
			ipdForm.setPmc(bed.getPmc());
			ipdForm.setUsg_report(bed.getUsg_report());
			
			ipdForm.setSurgical_ho(bed.getSurgical_ho());
			
			ipdForm.setDate1(bed.getDate1());
			ipdForm.setHb1(bed.getHb1());
			ipdForm.setFbs1(bed.getFbs1());
			ipdForm.setDpbs1(bed.getDpbs1());
			ipdForm.setUrm1(bed.getUrm1());
			ipdForm.setTsh1(bed.getTsh1());
			ipdForm.setIct1(bed.getIct1());
			ipdForm.setGtt1(bed.getGtt1());
			
			ipdForm.setDate2(bed.getDate2());
			ipdForm.setHb2(bed.getHb2());
			ipdForm.setFbs2(bed.getFbs2());
			ipdForm.setDpbs2(bed.getDpbs2());
			ipdForm.setUrm2(bed.getUrm2());
			ipdForm.setTsh2(bed.getTsh2());
			ipdForm.setIct2(bed.getIct2());
			ipdForm.setGtt2(bed.getGtt2());
			
			ipdForm.setDate3(bed.getDate3());
			ipdForm.setHb3(bed.getHb3());
			ipdForm.setFbs3(bed.getFbs3());
			ipdForm.setDpbs3(bed.getDpbs3());
			ipdForm.setUrm3(bed.getUrm3());
			ipdForm.setTsh3(bed.getTsh3());
			ipdForm.setIct3(bed.getIct3());
			ipdForm.setGtt3(bed.getGtt3());
			
			ipdForm.setDate4(bed.getDate4());
			ipdForm.setHb4(bed.getHb4());
			ipdForm.setFbs4(bed.getFbs4());
			ipdForm.setDpbs4(bed.getDpbs4());
			ipdForm.setUrm4(bed.getUrm4());
			ipdForm.setTsh4(bed.getTsh4());
			ipdForm.setIct4(bed.getIct4());
			ipdForm.setGtt4(bed.getGtt4());
			
			ipdForm.setHv_1m(bed.getHv_1m());
			ipdForm.setHbs_ag(bed.getHbs_ag());
			ipdForm.setVdrl(bed.getVdrl());
			ipdForm.setHb_srecta(bed.getHb_srecta());
			ipdForm.setHb_ac(bed.getHb_ac());
			ipdForm.setDuet_markess(bed.getDuet_markess());
			ipdForm.setTriple(bed.getTriple());
			ipdForm.setQuadrple_maicers(bed.getQuadrple_maicers());
			ipdForm.setDays_dating_scan(bed.getDays_dating_scan());
			ipdForm.setHrf_otherval(bed.getHrf_otherval());
			
			return "vermanhgynicform";
		}
		
	
	}catch(Exception e){
		e.printStackTrace();
	}
	finally {
		connection.close();
	}
	
	return "gynicform";
	
}


public String savenewgynicform() throws Exception{
	
	Connection connection=null;
	
	try {
		connection=Connection_provider.getconnection();
		IpdDAO ipdDAO= new JDBCIpdDAO(connection);
		MasterDAO masterDAO= new JDBCMasterDAO(connection);
		BedDao bedDao =new JDBCBedDao(connection);
		String clientid= ipdForm.getClientid();
		Bed bed =new Bed();
		bed.setClientid(clientid);
		
		SimpleDateFormat dateFormat= new SimpleDateFormat("yyyy-MM-dd");
		Calendar calendar=Calendar.getInstance();
		String commencing = dateFormat.format(calendar.getTime());
		String lastmodified= DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone());
		
		bed.setCommencing(commencing);
		bed.setLastmodified(lastmodified);
		bed.setUserid(loginInfo.getUserId());
		bed.setFormtype("4");
		
		bed.setHrf_gdm(DateTimeUtils.booleanCheck(ipdForm.getHrf_gdm()));
	    bed.setHrf_pih(DateTimeUtils.booleanCheck(ipdForm.getHrf_pih()));
	    bed.setHrf_boh(DateTimeUtils.booleanCheck(ipdForm.getHrf_boh()));
	    bed.setHrf_hypothyroid(DateTimeUtils.booleanCheck(ipdForm.getHrf_hypothyroid()));
	    bed.setHrf_hyperthyroid(DateTimeUtils.booleanCheck(ipdForm.getHrf_hyperthyroid()));
	    bed.setHrf_prev_lscs(DateTimeUtils.booleanCheck(ipdForm.getHrf_prev_lscs()));
	    bed.setHrf_rh_negative(DateTimeUtils.booleanCheck(ipdForm.getHrf_rh_negative()));
	    bed.setHrf_other(DateTimeUtils.booleanCheck(ipdForm.getHrf_other()));
	    
	    bed.setNulligravida(DateTimeUtils.booleanCheck(ipdForm.getNulligravida()));
	    bed.setCurrent_pregnent(DateTimeUtils.booleanCheck(ipdForm.getCurrent_pregnent()));
	    bed.setIud(DateTimeUtils.booleanCheck(ipdForm.getIud()));
	    
	    bed.setLive_boys(ipdForm.getLive_boys());
		bed.setLive_girls(ipdForm.getLive_girls());
		bed.setStillbirths(ipdForm.getStillbirths());
		bed.setAbortions(ipdForm.getAbortions());
		bed.setDeath_children(ipdForm.getDeath_children());
		
		bed.setG(DateTimeUtils.isNull(ipdForm.getG()));
		bed.setP(ipdForm.getP());
		bed.setL(ipdForm.getL());
		bed.setA(ipdForm.getA());
		bed.setD(ipdForm.getD());
		bed.setParity_abortion_notes(ipdForm.getParity_abortion_notes());
		
		bed.setLmp(ipdForm.getLmp());
		bed.setDating_usg_date(ipdForm.getDating_usg_date());
		bed.setEdd(ipdForm.getEdd());
		bed.setWeeks_dating_scan(ipdForm.getWeeks_dating_scan());
		bed.setPog(ipdForm.getPog());
		bed.setPog_dating_scan(ipdForm.getPog_dating_scan());
		bed.setPmc(ipdForm.getPmc());
		bed.setUsg_report(ipdForm.getUsg_report());
		
		bed.setSurgical_ho(ipdForm.getSurgical_ho());
		
		bed.setDate1(ipdForm.getDate1());
		bed.setHb1(ipdForm.getHb1());
		bed.setFbs1(ipdForm.getFbs1());
		bed.setDpbs1(ipdForm.getDpbs1());
		bed.setUrm1(ipdForm.getUrm1());
		bed.setTsh1(ipdForm.getTsh1());
		bed.setIct1(ipdForm.getIct1());
		bed.setGtt1(ipdForm.getGtt1());
		
		bed.setDate2(ipdForm.getDate2());
		bed.setHb2(ipdForm.getHb2());
		bed.setFbs2(ipdForm.getFbs2());
		bed.setDpbs2(ipdForm.getDpbs2());
		bed.setUrm2(ipdForm.getUrm2());
		bed.setTsh2(ipdForm.getTsh2());
		bed.setIct2(ipdForm.getIct2());
		bed.setGtt2(ipdForm.getGtt2());
		
		bed.setDate3(ipdForm.getDate3());
		bed.setHb3(ipdForm.getHb3());
		bed.setFbs3(ipdForm.getFbs3());
		bed.setDpbs3(ipdForm.getDpbs3());
		bed.setUrm3(ipdForm.getUrm3());
		bed.setTsh3(ipdForm.getTsh3());
		bed.setIct3(ipdForm.getIct3());
		bed.setGtt3(ipdForm.getGtt3());
		
		bed.setDate4(ipdForm.getDate4());
		bed.setHb4(ipdForm.getHb4());
		bed.setFbs4(ipdForm.getFbs4());
		bed.setDpbs4(ipdForm.getDpbs4());
		bed.setUrm4(ipdForm.getUrm4());
		bed.setTsh4(ipdForm.getTsh4());
		bed.setIct4(ipdForm.getIct4());
		bed.setGtt4(ipdForm.getGtt4());
		
		bed.setHv_1m(ipdForm.getHv_1m());
		bed.setHbs_ag(ipdForm.getHbs_ag());
		bed.setVdrl(ipdForm.getVdrl());
		bed.setHb_srecta(ipdForm.getHb_srecta());
		bed.setHb_ac(ipdForm.getHb_ac());
		bed.setDuet_markess(ipdForm.getDuet_markess());
		bed.setTriple(ipdForm.getTriple());
		bed.setQuadrple_maicers(ipdForm.getQuadrple_maicers());
		
		bed.setVisit_reason_ids(ipdForm.getVisit_reason_ids());
		
		bed.setGen_condition(ipdForm.getGen_condition());
	    bed.setTemp(ipdForm.getTemp());
	    bed.setPallor(ipdForm.getPallor());
	    bed.setPedal_edema(ipdForm.getPedal_edema());
	    bed.setPulse(ipdForm.getPulse());
	    bed.setBmi(ipdForm.getBmi());
	    bed.setHeight(ipdForm.getHeight());
	    bed.setWeight(ipdForm.getWeight() );
	    bed.setSys_bp(ipdForm.getSys_bp());
	    bed.setDia_bp(ipdForm.getDia_bp());
	    
	    bed.setWall_edema(request.getParameter("wall_edema"));
	    bed.setPa_soft(DateTimeUtils.booleanCheck(ipdForm.getPa_soft()));
	    bed.setFundal_height(DateTimeUtils.isNull(ipdForm.getFundal_height()));
		
	    bed.setCephalic(DateTimeUtils.booleanCheck(ipdForm.getCephalic()));
	    bed.setCephalicVal(DateTimeUtils.isNull(ipdForm.getCephalicVal()));
	    bed.setBreech(DateTimeUtils.booleanCheck(ipdForm.getBreech()));
	    bed.setBaley_size(DateTimeUtils.booleanCheck(ipdForm.getBaley_size()));
	    bed.setBaley_sizeVal(DateTimeUtils.isNull(ipdForm.getBaley_sizeVal()));
	    bed.setTransverse_fhs(DateTimeUtils.booleanCheck(ipdForm.getTransverse_fhs()));
	    bed.setExternal_ballotment(DateTimeUtils.booleanCheck(ipdForm.getExternal_ballotment()));
	    bed.setPs_fhs(DateTimeUtils.isNull(ipdForm.getPs_fhs()));
	    bed.setBeats_min(ipdForm.getBeats_min());
	    bed.setLiquor(ipdForm.getLiquor());
	    bed.setUterine_contractions(ipdForm.getUterine_contractions());
	    
	    bed.setPs_cervix(DateTimeUtils.booleanCheck(ipdForm.getPs_cervix()));
	    bed.setPs_cervixVal(DateTimeUtils.booleanCheck(ipdForm.getPs_cervixVal()));
	    bed.setPs_vagine(DateTimeUtils.booleanCheck(ipdForm.getPs_vagine()));
	    bed.setPs_vagineVal(ipdForm.getPs_vagineVal());
	    bed.setPs_vault(DateTimeUtils.booleanCheck(ipdForm.getPs_vault()));
	    bed.setPs_vaultVal(ipdForm.getPs_vaultVal());
	    bed.setPv_trim(DateTimeUtils.booleanCheck(ipdForm.getPv_trim()));
	    bed.setPv_unettacced(DateTimeUtils.booleanCheck(ipdForm.getPv_unettacced()));
	    bed.setPv_ant(DateTimeUtils.booleanCheck(ipdForm.getPv_ant()));
	    bed.setPv_tubular(DateTimeUtils.booleanCheck(ipdForm.getPv_tubular()));
	    bed.setPv_just_ettacced(DateTimeUtils.booleanCheck(ipdForm.getPv_just_ettacced()));
	    bed.setPv_mid_posits(DateTimeUtils.booleanCheck(ipdForm.getPv_mid_posits()));
	    bed.setPv_soft(DateTimeUtils.booleanCheck(ipdForm.getPv_soft()));
	    bed.setPv_ettacced(DateTimeUtils.booleanCheck(ipdForm.getPv_ettacced()));
	    bed.setEffaced_text(DateTimeUtils.isNull(ipdForm.getEffaced_text()));
	    bed.setPv_post(DateTimeUtils.booleanCheck(ipdForm.getPv_post()));
	    bed.setPv_station(ipdForm.getPv_station());
	    bed.setPv_liquor(ipdForm.getPv_liquor());
	    bed.setPv_pelvis(ipdForm.getPv_pelvis());
	    bed.setPv_position(ipdForm.getPv_position());
	    bed.setPv_os(DateTimeUtils.booleanCheck(ipdForm.getPv_os()));
	    bed.setPv_osVal(ipdForm.getPv_osVal());
	    bed.setPv_membrane(DateTimeUtils.booleanCheck(ipdForm.getPv_membrane()));
	    bed.setPv_MVal(ipdForm.getPv_MVal());
	    bed.setPv_dialated(DateTimeUtils.booleanCheck(ipdForm.getPv_dialated()));
	    bed.setPv_dialatedcm(DateTimeUtils.isNull(ipdForm.getPv_dialatedcm()));
	    bed.setFormtype(ipdForm.getFormtype());
	    bed.setTt_dose1(ipdForm.getTt_dose1());
	    bed.setTt_dose2(ipdForm.getTt_dose2());
	    
	    bed.setAdditonal_notes(ipdForm.getAdditonal_notes());
	    bed.setDiagonosis(ipdForm.getDiagonosis());
	    bed.setTreatment_advice(ipdForm.getTreatment_advice());
	    bed.setInvestigation_advice(ipdForm.getInvestigation_advice());
		
	    bed.setDays_dating_scan(ipdForm.getDays_dating_scan());
	    bed.setHrf_otherval(ipdForm.getHrf_otherval());
	    bed.setPractitionerid(ipdForm.getPractitionerid());
	    
	    bed.setInfluenza_vaccine(DateTimeUtils.isNull(ipdForm.getInfluenza_vaccine()));
	    bed.setLastappointmentid(ipdForm.getLastappointmentid());
	    
	    if(ipdForm.getId()==0){
	    	bed.setCreateddate(lastmodified);
	    	int res= ipdDAO.savenewIpdGynicDetails(bed);
			
			 //save gynic obs history
	        if(ipdForm.getObslist()!=null){
	        	 for(Bed bed2 :ipdForm.getObslist()){
	        		 if(bed2==null){
	        			 continue;
	        		 }
	            	  bed2.setGynicid("0");
	            	  bed2.setNewgynicid(res);
	        	      int r=bedDao.saveGynicObsData(0,bed2);
	        	 }
	        }
	        session.setAttribute("gynicformid", String.valueOf(res));
	    }else{
	    	bed.setId(ipdForm.getId());
	    	bed.setCommencing(ipdForm.getCommencing());
	    	int res= ipdDAO.updatenewIpdGynicDetails(bed);
	    	res = ipdForm.getId();
	    	
			 //save gynic obs history
	        if(ipdForm.getObslist()!=null){
	        	 int rr = bedDao.deletePreviousGynicObsData(res);
	        	 for(Bed bed2 :ipdForm.getObslist()){
	        		 if(bed2==null){
	        			 continue;
	        		 }
	            	  bed2.setGynicid("0");
	            	  bed2.setNewgynicid(res);
	            	  int r=bedDao.saveGynicObsData(0,bed2);
	        	 }
	        }
	        session.setAttribute("gynicformid", String.valueOf(ipdForm.getId()));
	    }
	    
		
		String chief_comlint_id=masterDAO.getIpdTemplateId("Chief Complaints");
		String present_ill_id=masterDAO.getIpdTemplateId("Present Illness");
		String past_history_id=masterDAO.getIpdTemplateId("Past History");
		String family_hist_id=masterDAO.getIpdTemplateId("Family History");
		String personal_hist_id=masterDAO.getIpdTemplateId("Personal History");
		String onexami_id=masterDAO.getIpdTemplateId("On Examination");
		String tretment_given_id=masterDAO.getIpdTemplateId("Treatment Given");
		String examination_id=masterDAO.getIpdTemplateId("Examination Findings");
		
		ArrayList<Master> chief_complaints_list=masterDAO.getIpdTemplateListNames(chief_comlint_id);
		ArrayList<Master> present_illness_list=masterDAO.getIpdTemplateListNames(present_ill_id);
		ArrayList<Master> past_history_list=masterDAO.getIpdTemplateListNames(past_history_id);
		ArrayList<Master> family_history_list=masterDAO.getIpdTemplateListNames(family_hist_id);
		ArrayList<Master> personal_hist_list=masterDAO.getIpdTemplateListNames(personal_hist_id);
		ArrayList<Master> on_exam_list=masterDAO.getIpdTemplateListNames(onexami_id);
		ArrayList<Master> treatment_given_list=masterDAO.getIpdTemplateListNames(tretment_given_id);
		ArrayList<Master> examination_finding_list=masterDAO.getIpdTemplateListNames(examination_id);
		ipdForm.setChief_complaints_list(chief_complaints_list);
		ipdForm.setPresent_illness_list(present_illness_list);
		ipdForm.setPast_history_list(past_history_list);
		ipdForm.setFamily_history_list(family_history_list);
		ipdForm.setPersonal_hist_list(personal_hist_list);
		ipdForm.setOn_exam_list(on_exam_list);
		ipdForm.setTreatment_given_list(treatment_given_list);
		ipdForm.setExamination_finding_list(examination_finding_list);
		
		ArrayList<Bed> wardlist=bedDao.getAllWardList("0");
		ipdForm.setWardlist(wardlist);
		
		
		
		
	} catch (Exception e) {

		e.printStackTrace();
	}
	finally {
		connection.close();
	}
	
	
	return "redirectprintgynic";
}

public String editnewgynicform() throws Exception{
	
	Connection connection=null;
	
	try {
		connection=Connection_provider.getconnection();
		String id= request.getParameter("selectedid"); 
		if(id==null) {
			id=(String) session.getAttribute("gynicformid");
		}
		
		IpdDAO ipdDAO= new JDBCIpdDAO(connection);
		MasterDAO masterDAO= new JDBCMasterDAO(connection);
		BedDao bedDao= new JDBCBedDao(connection);
		
		Bed bed= ipdDAO.getEditNewGynicFormData(id);
		
		ipdForm.setLastappointmentid(bed.getLastappointmentid());
		
		ArrayList<Priscription> gynicPriscList = new ArrayList<Priscription>();
		gynicPriscList=ipdDAO.getGynicPrescList(""+bed.getLastappointmentid());
		ipdForm.setGynicPriscList(gynicPriscList);
		
		ipdForm.setInfluenza_vaccine(bed.getInfluenza_vaccine());
		
		ClientDAO clientDAO =new JDBCClientDAO(connection);
		Client client=clientDAO.getClientDetails(bed.getClientid());
		
		String fullname = client.getTitle() + " " + client.getFirstName() + " " + client.getLastName();
		ipdForm.setClient(fullname);
		ipdForm.setPractitionerid(bed.getPractitionerid());
		ipdForm.setRelativename(client.getEmergencyContName());
		ipdForm.setRelationcont(client.getEmergencyContNo());
		ipdForm.setRelation(client.getRelation());
		ipdForm.setClientid(bed.getClientid());
		ipdForm.setEditclientid(bed.getClientid());
		
		ipdForm.setClient(fullname);
		ipdForm.setRelativename(client.getEmergencyContName());
		ipdForm.setRelationcont(client.getEmergencyContNo());
		ipdForm.setRelation(client.getRelation());
		//String numcount=ipdDAO.getNumofAdmissionCount(ipdForm.getClientid());
		ipdForm.setContact(client.getMobNo());
		//ipdForm.setNum_admission(numcount);
		ipdForm.setDob(client.getDob());
		ipdForm.setTpid(client.getTypeName());
		ipdForm.setAddress(client.getAddress());
		ipdForm.setAbrivationid(client.getAbrivationid());
		
		String age = DateTimeUtils.getAge(client.getDob());
		String agegender = age + "Years" + " / " + client.getGender();
		ipdForm.setAgegender(agegender);
		
		//String numcount=ipdDAO.getNumofAdmissionCount(ipdForm.getClientid());
		String formtype= bed.getFormtype();
		
		ArrayList<String> reasonVisitList = new ArrayList<String>();
		reasonVisitList.add("Amenorrhea ");
		reasonVisitList.add("Leaking PV");
		reasonVisitList.add("Pain in lower abdomen");
		reasonVisitList.add("Nausea");
		reasonVisitList.add("Vomiting");
		reasonVisitList.add("Bleeding PV");
		reasonVisitList.add("Burning Micturition");
		reasonVisitList.add("White discharge PV");
		reasonVisitList.add("Backache");
		reasonVisitList.add("Others");
		reasonVisitList.add("Spotting PV");
		reasonVisitList.add("Fetal movements");
		reasonVisitList.add("Epigastric pain");
		reasonVisitList.add("Oedema");
	
		ipdForm.setReasonVisitList(reasonVisitList);
		
		ipdForm.setVisit_reason_ids(bed.getVisit_reason_ids());
		ipdForm.setId(bed.getId());
		ipdForm.setLastmodified(bed.getCommencing());
		
		ArrayList<Bed> allVisitReasonList = ipdDAO.getGynicVisitReasonList(ipdForm.getVisit_reason_ids());
		ipdForm.setAllVisitReasonList(allVisitReasonList);
		ipdForm.setFormtype(formtype);
		ArrayList<Bed> gynicobsList= bedDao.getNewGynicObsListByGynicId(id);
		ipdForm.setGynicobsList(gynicobsList);
		
		ipdForm.setId(bed.getId());
		
		ipdForm.setHrf_gdm(DateTimeUtils.booleanCheck(bed.getHrf_gdm()));
	    ipdForm.setHrf_pih(DateTimeUtils.booleanCheck(bed.getHrf_pih()));
	    ipdForm.setHrf_boh(DateTimeUtils.booleanCheck(bed.getHrf_boh()));
	    ipdForm.setHrf_hypothyroid(DateTimeUtils.booleanCheck(bed.getHrf_hypothyroid()));
	    ipdForm.setHrf_hyperthyroid(DateTimeUtils.booleanCheck(bed.getHrf_hyperthyroid()));
	    ipdForm.setHrf_prev_lscs(DateTimeUtils.booleanCheck(bed.getHrf_prev_lscs()));
	    ipdForm.setHrf_rh_negative(DateTimeUtils.booleanCheck(bed.getHrf_rh_negative()));
	    ipdForm.setHrf_other(DateTimeUtils.booleanCheck(bed.getHrf_other()));
	    
	    ipdForm.setNulligravida(DateTimeUtils.booleanCheck(bed.getNulligravida()));
	    ipdForm.setCurrent_pregnent(DateTimeUtils.booleanCheck(bed.getCurrent_pregnent()));
	    ipdForm.setIud(DateTimeUtils.booleanCheck(bed.getIud()));
	    
	    ipdForm.setLive_boys(bed.getLive_boys());
		ipdForm.setLive_girls(bed.getLive_girls());
		ipdForm.setStillbirths(bed.getStillbirths());
		ipdForm.setAbortions(bed.getAbortions());
		ipdForm.setDeath_children(bed.getDeath_children());
		
		ipdForm.setG(DateTimeUtils.isNull(bed.getG()));
		ipdForm.setP(bed.getP());
		ipdForm.setL(bed.getL());
		ipdForm.setA(bed.getA());
		ipdForm.setD(bed.getD());
		ipdForm.setParity_abortion_notes(bed.getParity_abortion_notes());
		
		ipdForm.setLmp(bed.getLmp());
		ipdForm.setDating_usg_date(bed.getDating_usg_date());
		ipdForm.setEdd(bed.getEdd());
		ipdForm.setWeeks_dating_scan(bed.getWeeks_dating_scan());
		ipdForm.setPog(bed.getPog());
		ipdForm.setPog_dating_scan(bed.getPog_dating_scan());
		ipdForm.setPmc(bed.getPmc());
		ipdForm.setUsg_report(bed.getUsg_report());
		
		ipdForm.setSurgical_ho(bed.getSurgical_ho());
		
		ipdForm.setDate1(bed.getDate1());
		ipdForm.setHb1(bed.getHb1());
		ipdForm.setFbs1(bed.getFbs1());
		ipdForm.setDpbs1(bed.getDpbs1());
		ipdForm.setUrm1(bed.getUrm1());
		ipdForm.setTsh1(bed.getTsh1());
		ipdForm.setIct1(bed.getIct1());
		ipdForm.setGtt1(bed.getGtt1());
		
		ipdForm.setDate2(bed.getDate2());
		ipdForm.setHb2(bed.getHb2());
		ipdForm.setFbs2(bed.getFbs2());
		ipdForm.setDpbs2(bed.getDpbs2());
		ipdForm.setUrm2(bed.getUrm2());
		ipdForm.setTsh2(bed.getTsh2());
		ipdForm.setIct2(bed.getIct2());
		ipdForm.setGtt2(bed.getGtt2());
		
		ipdForm.setDate3(bed.getDate3());
		ipdForm.setHb3(bed.getHb3());
		ipdForm.setFbs3(bed.getFbs3());
		ipdForm.setDpbs3(bed.getDpbs3());
		ipdForm.setUrm3(bed.getUrm3());
		ipdForm.setTsh3(bed.getTsh3());
		ipdForm.setIct3(bed.getIct3());
		ipdForm.setGtt3(bed.getGtt3());
		
		ipdForm.setDate4(bed.getDate4());
		ipdForm.setHb4(bed.getHb4());
		ipdForm.setFbs4(bed.getFbs4());
		ipdForm.setDpbs4(bed.getDpbs4());
		ipdForm.setUrm4(bed.getUrm4());
		ipdForm.setTsh4(bed.getTsh4());
		ipdForm.setIct4(bed.getIct4());
		ipdForm.setGtt4(bed.getGtt4());
		
		ipdForm.setHv_1m(bed.getHv_1m());
		ipdForm.setHbs_ag(bed.getHbs_ag());
		ipdForm.setVdrl(bed.getVdrl());
		ipdForm.setHb_srecta(bed.getHb_srecta());
		ipdForm.setHb_ac(bed.getHb_ac());
		ipdForm.setDuet_markess(bed.getDuet_markess());
		ipdForm.setTriple(bed.getTriple());
		ipdForm.setQuadrple_maicers(bed.getQuadrple_maicers());
		
		ipdForm.setVisit_reason_ids(bed.getVisit_reason_ids());
		
		ipdForm.setGen_condition(bed.getGen_condition());
	    ipdForm.setTemp(bed.getTemp());
	    ipdForm.setPallor(bed.getPallor());
	    ipdForm.setPedal_edema(bed.getPedal_edema());
	    ipdForm.setPulse(bed.getPulse());
	    ipdForm.setBmi(bed.getBmi());
	    ipdForm.setHeight(bed.getHeight());
	    ipdForm.setWeight(bed.getWeight() );
	    ipdForm.setSys_bp(bed.getSys_bp());
	    ipdForm.setDia_bp(bed.getDia_bp());
	    
	    ipdForm.setWall_edema(bed.getWall_edema());
	    ipdForm.setPa_soft(DateTimeUtils.booleanCheck(bed.getPa_soft()));
	    ipdForm.setFundal_height(DateTimeUtils.isNull(bed.getFundal_height()));
		
	    ipdForm.setCephalic(DateTimeUtils.booleanCheck(bed.getCephalic()));
	    ipdForm.setCephalicVal(DateTimeUtils.isNull(bed.getCephalicVal()));
	    ipdForm.setBreech(DateTimeUtils.booleanCheck(bed.getBreech()));
	    ipdForm.setBaley_size(DateTimeUtils.booleanCheck(bed.getBaley_size()));
	    ipdForm.setBaley_sizeVal(DateTimeUtils.isNull(bed.getBaley_sizeVal()));
	    ipdForm.setTransverse_fhs(DateTimeUtils.booleanCheck(bed.getTransverse_fhs()));
	    ipdForm.setExternal_ballotment(DateTimeUtils.booleanCheck(bed.getExternal_ballotment()));
	    ipdForm.setPs_fhs(DateTimeUtils.isNull(bed.getPs_fhs()));
	    ipdForm.setBeats_min(bed.getBeats_min());
	    ipdForm.setLiquor(bed.getLiquor());
	    ipdForm.setUterine_contractions(bed.getUterine_contractions());
	    
	    ipdForm.setPs_cervix(DateTimeUtils.booleanCheck(bed.getPs_cervix()));
	    ipdForm.setPs_cervixVal(DateTimeUtils.booleanCheck(bed.getPs_cervixVal()));
	    ipdForm.setPs_vagine(DateTimeUtils.booleanCheck(bed.getPs_vagine()));
	    ipdForm.setPs_vagineVal(bed.getPs_vagineVal());
	    ipdForm.setPs_vault(DateTimeUtils.booleanCheck(bed.getPs_vault()));
	    ipdForm.setPs_vaultVal(bed.getPs_vaultVal());
	    ipdForm.setPv_trim(DateTimeUtils.booleanCheck(bed.getPv_trim()));
	    ipdForm.setPv_unettacced(DateTimeUtils.booleanCheck(bed.getPv_unettacced()));
	    ipdForm.setPv_ant(DateTimeUtils.booleanCheck(bed.getPv_ant()));
	    ipdForm.setPv_tubular(DateTimeUtils.booleanCheck(bed.getPv_tubular()));
	    ipdForm.setPv_just_ettacced(DateTimeUtils.booleanCheck(bed.getPv_just_ettacced()));
	    ipdForm.setPv_mid_posits(DateTimeUtils.booleanCheck(bed.getPv_mid_posits()));
	    ipdForm.setPv_soft(DateTimeUtils.booleanCheck(bed.getPv_soft()));
	    ipdForm.setPv_ettacced(DateTimeUtils.booleanCheck(bed.getPv_ettacced()));
	    ipdForm.setEffaced_text(DateTimeUtils.isNull(bed.getEffaced_text()));
	    ipdForm.setPv_post(DateTimeUtils.booleanCheck(bed.getPv_post()));
	    ipdForm.setPv_station(bed.getPv_station());
	    ipdForm.setPv_liquor(bed.getPv_liquor());
	    ipdForm.setPv_pelvis(bed.getPv_pelvis());
	    ipdForm.setPv_position(bed.getPv_position());
	    ipdForm.setPv_os(DateTimeUtils.booleanCheck(bed.getPv_os()));
	    ipdForm.setPv_osVal(bed.getPv_osVal());
	    ipdForm.setPv_membrane(DateTimeUtils.booleanCheck(bed.getPv_membrane()));
	    ipdForm.setPv_MVal(bed.getPv_MVal());
	    ipdForm.setPv_dialated(DateTimeUtils.booleanCheck(bed.getPv_dialated()));
	    ipdForm.setPv_dialatedcm(DateTimeUtils.isNull(bed.getPv_dialatedcm()));
	    ipdForm.setFormtype(bed.getFormtype());
	    ipdForm.setTt_dose1(bed.getTt_dose1());
	    ipdForm.setTt_dose2(bed.getTt_dose2());
	    
	    ipdForm.setAdditonal_notes(bed.getAdditonal_notes());
	    ipdForm.setDiagonosis(bed.getDiagonosis());
	    ipdForm.setTreatment_advice(bed.getTreatment_advice());
	    ipdForm.setInvestigation_advice(bed.getInvestigation_advice());
		ipdForm.setCommencing(bed.getCommencing());
		ipdForm.setDays_dating_scan(bed.getDays_dating_scan());
		ipdForm.setHrf_otherval(bed.getHrf_otherval());
		
		String chief_comlint_id=masterDAO.getIpdTemplateId("Chief Complaints");
		String present_ill_id=masterDAO.getIpdTemplateId("Present Illness");
		String past_history_id=masterDAO.getIpdTemplateId("Past History");
		String family_hist_id=masterDAO.getIpdTemplateId("Family History");
		String personal_hist_id=masterDAO.getIpdTemplateId("Personal History");
		String onexami_id=masterDAO.getIpdTemplateId("On Examination");
		String tretment_given_id=masterDAO.getIpdTemplateId("Treatment Given");
		String examination_id=masterDAO.getIpdTemplateId("Examination Findings");
		
		ArrayList<Master> chief_complaints_list=masterDAO.getIpdTemplateListNames(chief_comlint_id);
		ArrayList<Master> present_illness_list=masterDAO.getIpdTemplateListNames(present_ill_id);
		ArrayList<Master> past_history_list=masterDAO.getIpdTemplateListNames(past_history_id);
		ArrayList<Master> family_history_list=masterDAO.getIpdTemplateListNames(family_hist_id);
		ArrayList<Master> personal_hist_list=masterDAO.getIpdTemplateListNames(personal_hist_id);
		ArrayList<Master> on_exam_list=masterDAO.getIpdTemplateListNames(onexami_id);
		ArrayList<Master> treatment_given_list=masterDAO.getIpdTemplateListNames(tretment_given_id);
		ArrayList<Master> examination_finding_list=masterDAO.getIpdTemplateListNames(examination_id);
		
		ipdForm.setChief_complaints_list(chief_complaints_list);
		ipdForm.setPresent_illness_list(present_illness_list);
		ipdForm.setPast_history_list(past_history_list);
		ipdForm.setFamily_history_list(family_history_list);
		ipdForm.setPersonal_hist_list(personal_hist_list);
		ipdForm.setOn_exam_list(on_exam_list);
		ipdForm.setTreatment_given_list(treatment_given_list);
		ipdForm.setExamination_finding_list(examination_finding_list);
		
		//priscription 
		ArrayList<Master>medicineList = new ArrayList<Master>();
		ipdForm.setMedicineList(medicineList);
		EmrDAO emrDAO = new JDBCEmrDAO(connection);
		ArrayList<Priscription>templateNameList = emrDAO.getTemplateNameList(loginInfo);
		if(templateNameList.size()==0){
			templateNameList = new ArrayList<Priscription>();
		}
		ipdForm.setTemplateNameList(templateNameList);
		ArrayList<Priscription>parentPriscList = new ArrayList<Priscription>();
		ipdForm.setParentPriscList(parentPriscList);
		ArrayList<Master>dosageList = emrDAO.getDosageList();
		ipdForm.setDosageList(dosageList);
		ArrayList<Priscription> medicinetimelist = emrDAO.getMedicineTimeList(); 
		ipdForm.setMedicinetimelist(medicinetimelist);
		ArrayList<Master>dosagenoteList = masterDAO.getDosageNoteList();
		ipdForm.setDosagenoteList(dosagenoteList);
		ArrayList<Master>nimaidoselist = masterDAO.getnimaidoselistt();
		ArrayList<Master>nimaiqtylist = masterDAO.getnimaiqtylist();
		ArrayList<Master>nimairemarklist = masterDAO.getnimairemarlist();
		ipdForm.setNimaidoselist(nimaidoselist);
		ipdForm.setNimaiqtylist(nimaiqtylist);
		ipdForm.setNimairemarklist(nimairemarklist);
		ArrayList<Master> priscUnitList= masterDAO.getPriscUnitList();
		ipdForm.setPriscUnitList(priscUnitList);
		ArrayList<Master>mdicneTypeList = emrDAO.getMedicineTypeList();
		ipdForm.setMdicneTypeList(mdicneTypeList);
		ArrayList<Master>mdicinecategoryList = emrDAO.getmedicineCategoryList();
		ipdForm.setMdicinecategoryList(mdicinecategoryList);
		ArrayList<Master> specializationTemplateList= masterDAO.getMasterSpecializationList();
		ipdForm.setSpecializationTemplateList(specializationTemplateList);
		
		//investigation
		UserProfileDAO userProfileDAO = new JDBCUserProfileDAO(connection);
		InvestigationDAO investigationDAO = new JDBCInvestigationDAO(connection);
		ArrayList<String> jobTitleList = userProfileDAO.getJobTitleList();
		ipdForm.setJobTitleList(jobTitleList);
		UserProfile userProfile = userProfileDAO.getUserprofileDetails(loginInfo.getId());
		ipdForm.setJobtitle("Pathlab");
		ArrayList<Master> pkgsList = investigationDAO.getInvPaksLists();
		ipdForm.setPkgsList(pkgsList);
		ArrayList<Master> invsTypeList = emrDAO.getInvesigationTypeList();
		ipdForm.setInvsTypeList(invsTypeList);
		ArrayList<Master> invstReportTypeList = emrDAO.getInvstReportTypeList();
		ipdForm.setInvstReportTypeList(invstReportTypeList);
		ArrayList<Master> invstUnitList = emrDAO.getInvstUnitList();
		ipdForm.setInvstUnitList(invstUnitList);
		ArrayList<Master> invSectionList = investigationDAO.getInvestigationSectionList();
		ipdForm.setInvSectionList(invSectionList);
		
		
		ArrayList<Bed> wardlist=bedDao.getAllWardList("0");
		ipdForm.setWardlist(wardlist);
		
	} catch (Exception e) {

		e.printStackTrace();
	}
	finally {
		connection.close();
	}
	
	return "vermanhgynicform";
}

public String savereasonforvisitobj() throws Exception{
		
		Connection  connection=null;
		try {
			connection=Connection_provider.getconnection();
			IpdDAO ipdDAO= new JDBCIpdDAO(connection);
			String clientid= request.getParameter("clientid");
			String reason= request.getParameter("reason");
			String quality=request.getParameter("quality");
			String periodicity=request.getParameter("periodicity");
			String influence= request.getParameter("influence");
			String since= request.getParameter("since");
			String notes= request.getParameter("notes");
			String reasonvisit= request.getParameter("reasonvisit");
			String decreased = request.getParameter("decreased");
			String perceives = request.getParameter("perceives");
			String notperceives = request.getParameter("notperceives");
			String datetime= DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone()); 
			
			Bed bed=new Bed();
			bed.setClientid(clientid);
			bed.setReason(reason);
			bed.setQuality(quality);
			bed.setPeriodicity(periodicity);
			bed.setInfluence(influence);
			bed.setSince(since);
			bed.setNotes(notes);
			bed.setDatetime(datetime);
			bed.setReasonvisit(reasonvisit);
			bed.setDecreased(decreased);
			bed.setPerceives(perceives);
			bed.setNotperceives(notperceives);
			
			int res= ipdDAO.saveReasonGynicVisit(bed);
			String commencing =datetime.split(" ")[0];
			
			ArrayList<Bed> allVisitReason = ipdDAO.getGynicVisitReasonList(clientid,commencing);
			StringBuffer buffer= new StringBuffer(); 
			for(Bed bed2 : allVisitReason){
				buffer.append("<h5 style='margin-bottom: 5px;margin-top: 5px;'><b>"+bed2.getReasonvisit()+"</b>&nbsp;&nbsp;<a href='#' data-toggle='modal' data-target=''><i class='fa fa-pencil'></i></a></h5>");
				if(loginInfo.isVermanh()){
					buffer.append("<p style='margin: 0px;'>Since : "+bed2.getSince()+"</p>");
					buffer.append("<p style='margin: 0px;'>Note : "+bed2.getNotes()+"</p>");
					
					if(DateTimeUtils.numberCheck(bed.getPerceives()).equals("1")){
						buffer.append("<p style='margin: 0px;'>Perceives</p>");
					}
					if(DateTimeUtils.numberCheck(bed.getNotperceives()).equals("1")){
						buffer.append("<p style='margin: 0px;'>Not Perceives</p>");
					}
					if(DateTimeUtils.numberCheck(bed.getDecreased()).equals("1")){
						buffer.append("<p style='margin: 0px;'>Decreased</p>");
					}
					buffer.append("<hr>");
				}else{
					buffer.append("<p style='margin: 0px;'>Quality : "+bed2.getQuality()+"</p>");
					buffer.append("<p style='margin: 0px;'>Periodicity : "+bed2.getPeriodicity()+"</p>");
					buffer.append("<p style='margin: 0px;'>Influencing Factor : "+bed2.getInfluence()+"</p>");
					buffer.append("<p style='margin: 0px;'>Since : "+bed2.getSince()+"</p>");
					buffer.append("<p style='margin: 0px;'>Note : "+bed2.getNotes()+"</p>");
					buffer.append("<hr>");
				}
				
			}
			
			buffer.append("~"+res);
			
			response.setContentType("text/html");
		response.setHeader("Cache-Control", "no-cache");
		response.getWriter().write(""+buffer.toString()+"");
		
	} catch (Exception e) {

		e.printStackTrace();
	}
		
		return null;
		
	}
private ArrayList<String> getReasonVisitData(String formtype) {
	  
	  
	  ArrayList<String> list= new ArrayList<String>();
	  if(formtype.equals("1")){
		  list.add("Routine Follow up");
		  list.add("Amenorrhoea");
		  list.add("UPT +ve");
		  list.add("Pain in lower abdomen");
		  list.add("Nausea");
		  list.add("Vomiting");
		  list.add("Nausea + Vomiting");
		  list.add("Bleeding PV");
		  list.add("Burning Micturition");
		  list.add("White discharge PV");
		  list.add("Bad Obstetric History");
		  list.add("Others");
		  
	  } else if(formtype.equals("2")){
		  
		  list.add("Irregular Menses");
		  list.add("Excessive Menstual Bleeding");
		  list.add("Secondary Ammenorrhea");
		  list.add("Dysmenorrhea");
		  list.add("Pain in Abdomen ");
		  list.add("Upt ï¿½ Negative");
		  list.add("Burning Micturation");
		  list.add("Comes For Followup ");
		  list.add("White Discharge");
		  list.add("Scanty Menses ");
		  list.add("Post Menopausal Bleeding");
		  list.add("Climacteric Symtoms");
		  list.add("Fue Of Endometnosis- Comes For Check Up");
		  list.add("Postmenopauasal Spotting");
		  list.add("Postmenopauasal Spotting ï¿½ For Routine Check Up ");
		  list.add("Frequent Menses");
		  list.add("Itching Around Vulva");
		  list.add("Primary Ammenorrhea");
		  list.add("Pain in Left Lumbar Region");
		  list.add("Pain in Lower Abdomen");
		  
		  
	  } else if(formtype.equals("3")) {
		  list.add("Married");
		  list.add("Primary Infertility");
		  list.add("Eager To Conceive");
		  list.add("Secondary Infertility");
		  list.add("Irregular Menses");
		  list.add("Boh");
		  list.add("Comes For D-2/3 Investigation");
		  list.add("Secondary Ammenorrhea");
		  list.add("Comes For Fertility Assessment");
		  list.add("Low Amh");
		  list.add("Poor Ovarian reserve");
		  list.add("Failed Ivf ï¿½ Once");
		  list.add("Excessive Menstrual Bleeding ");
		  list.add("Dysmenorrhea");
		  list.add("Scanty Menses");
		  list.add("Secondary Ovarian Failure");
		  list.add("Comes For Follow Up");
		  list.add("Low Fertility Potential");
		  list.add("Menses Only With Medicines");
		  list.add("Ammenorrhea");
		  
	  }
	  return list;
}

public String printnewgynicform() throws Exception{
	
	Connection connection=null;
	
	try {
		connection=Connection_provider.getconnection();
		String id= request.getParameter("selectedid"); 
		
		if(id==null) {
			id=(String) session.getAttribute("gynicformid");
		}
		
		IpdDAO ipdDAO= new JDBCIpdDAO(connection);
		MasterDAO masterDAO= new JDBCMasterDAO(connection);
		BedDao bedDao= new JDBCBedDao(connection);
		
		Bed bed= ipdDAO.getEditNewGynicFormData(id);
		
		ipdForm.setLastappointmentid(bed.getLastappointmentid());
		
		ArrayList<Priscription> gynicPriscList = new ArrayList<Priscription>();
		gynicPriscList=ipdDAO.getGynicPrescList(""+bed.getLastappointmentid());
		ipdForm.setGynicPriscList(gynicPriscList);
		
		ipdForm.setInfluenza_vaccine(bed.getInfluenza_vaccine());
		
		ClientDAO clientDAO =new JDBCClientDAO(connection);
		Client client=clientDAO.getClientDetails(bed.getClientid());
		
		String fullname = client.getTitle() + " " + client.getFirstName() + " " + client.getLastName();
		ipdForm.setClient(fullname);
		ipdForm.setRelativename(client.getEmergencyContName());
		ipdForm.setRelationcont(client.getEmergencyContNo());
		ipdForm.setRelation(client.getRelation());
		ipdForm.setClientid(bed.getClientid());
		ipdForm.setEditclientid(bed.getClientid());
		
		ipdForm.setClient(fullname);
		ipdForm.setRelativename(client.getEmergencyContName());
		ipdForm.setRelationcont(client.getEmergencyContNo());
		ipdForm.setRelation(client.getRelation());
		//String numcount=ipdDAO.getNumofAdmissionCount(ipdForm.getClientid());
		ipdForm.setContact(client.getMobNo());
		//ipdForm.setNum_admission(numcount);
		ipdForm.setDob(client.getDob());
		ipdForm.setTpid(client.getTypeName());
		ipdForm.setPatientaddress(client.getAddress());
		ipdForm.setAbrivationid(client.getAbrivationid());
		
		String age = DateTimeUtils.getAge(client.getDob());
		String agegender = age + "Years" + " / " + client.getGender();
		ipdForm.setAgegender(agegender);
		
		//String numcount=ipdDAO.getNumofAdmissionCount(ipdForm.getClientid());
		String formtype= bed.getFormtype();
		
		ArrayList<String> reasonVisitList = new ArrayList<String>();
		reasonVisitList.add("Amenorrhea ");
		reasonVisitList.add("Leaking PV");
		reasonVisitList.add("Pain in lower abdomen");
		reasonVisitList.add("Nausea");
		reasonVisitList.add("Vomiting");
		reasonVisitList.add("Bleeding PV");
		reasonVisitList.add("Burning Micturition");
		reasonVisitList.add("White discharge PV");
		reasonVisitList.add("Backache");
		reasonVisitList.add("Others");
		reasonVisitList.add("Spotting PV");
		reasonVisitList.add("Fetal movements");
		reasonVisitList.add("Epigastric pain");
		reasonVisitList.add("Oedema");
	
		ipdForm.setReasonVisitList(reasonVisitList);
		
		ipdForm.setVisit_reason_ids(bed.getVisit_reason_ids());
		ipdForm.setId(bed.getId());
		ipdForm.setLastmodified(bed.getCommencing());
		
		ArrayList<Bed> allVisitReasonList = ipdDAO.getGynicVisitReasonList(ipdForm.getVisit_reason_ids());
		ipdForm.setAllVisitReasonList(allVisitReasonList);
		ipdForm.setFormtype(formtype);
		ArrayList<Bed> gynicobsList= bedDao.getNewGynicObsListByGynicId(id);
		ipdForm.setGynicobsList(gynicobsList);
		
		int size = gynicobsList.size();
		if (size > 0) {
			int check_indications = gynicobsList.get(size - 1).getCheck_indications();
			int check_coamplications = gynicobsList.get(size - 1).getCheck_coamplications();
			int check_institution = gynicobsList.get(size - 1).getCheck_institution();
			ipdForm.setCheck_indications(check_indications);
			ipdForm.setCheck_coamplications(check_coamplications);
			ipdForm.setCheck_institution(check_institution);
			session.setAttribute("check_indications", ""+check_indications);
			session.setAttribute("check_coamplications", ""+check_coamplications);
			session.setAttribute("check_institution", ""+check_institution);
		} else {
			ipdForm.setCheck_indications(0);
			ipdForm.setCheck_coamplications(0);
			ipdForm.setCheck_institution(0);
			session.setAttribute("check_indications", "1");
			session.setAttribute("check_coamplications", "1");
			session.setAttribute("check_institution", "1");
		}
		
		ipdForm.setId(bed.getId());
		
		ipdForm.setHrf_gdm(DateTimeUtils.booleanCheck(bed.getHrf_gdm()));
	    ipdForm.setHrf_pih(DateTimeUtils.booleanCheck(bed.getHrf_pih()));
	    ipdForm.setHrf_boh(DateTimeUtils.booleanCheck(bed.getHrf_boh()));
	    ipdForm.setHrf_hypothyroid(DateTimeUtils.booleanCheck(bed.getHrf_hypothyroid()));
	    ipdForm.setHrf_hyperthyroid(DateTimeUtils.booleanCheck(bed.getHrf_hyperthyroid()));
	    ipdForm.setHrf_prev_lscs(DateTimeUtils.booleanCheck(bed.getHrf_prev_lscs()));
	    ipdForm.setHrf_rh_negative(DateTimeUtils.booleanCheck(bed.getHrf_rh_negative()));
	    ipdForm.setHrf_other(DateTimeUtils.booleanCheck(bed.getHrf_other()));
	    
	    ipdForm.setNulligravida(DateTimeUtils.booleanCheck(bed.getNulligravida()));
	    ipdForm.setCurrent_pregnent(DateTimeUtils.booleanCheck(bed.getCurrent_pregnent()));
	    ipdForm.setIud(DateTimeUtils.booleanCheck(bed.getIud()));
	    
	    ipdForm.setLive_boys(bed.getLive_boys());
		ipdForm.setLive_girls(bed.getLive_girls());
		ipdForm.setStillbirths(bed.getStillbirths());
		ipdForm.setAbortions(bed.getAbortions());
		ipdForm.setDeath_children(bed.getDeath_children());
		
		ipdForm.setG(DateTimeUtils.isNull(bed.getG()));
		ipdForm.setP(bed.getP());
		ipdForm.setL(bed.getL());
		ipdForm.setA(bed.getA());
		ipdForm.setD(bed.getD());
		ipdForm.setParity_abortion_notes(bed.getParity_abortion_notes());
		
		ipdForm.setLmp(bed.getLmp());
		ipdForm.setDating_usg_date(bed.getDating_usg_date());
		ipdForm.setEdd(bed.getEdd());
		ipdForm.setWeeks_dating_scan(bed.getWeeks_dating_scan());
		ipdForm.setPog(bed.getPog());
		ipdForm.setPog_dating_scan(bed.getPog_dating_scan());
		ipdForm.setPmc(bed.getPmc());
		ipdForm.setUsg_report(bed.getUsg_report());
		
		ipdForm.setSurgical_ho(bed.getSurgical_ho());
		
		ipdForm.setDate1(bed.getDate1());
		ipdForm.setHb1(bed.getHb1());
		ipdForm.setFbs1(bed.getFbs1());
		ipdForm.setDpbs1(bed.getDpbs1());
		ipdForm.setUrm1(bed.getUrm1());
		ipdForm.setTsh1(bed.getTsh1());
		ipdForm.setIct1(bed.getIct1());
		ipdForm.setGtt1(bed.getGtt1());
		
		ipdForm.setDate2(bed.getDate2());
		ipdForm.setHb2(bed.getHb2());
		ipdForm.setFbs2(bed.getFbs2());
		ipdForm.setDpbs2(bed.getDpbs2());
		ipdForm.setUrm2(bed.getUrm2());
		ipdForm.setTsh2(bed.getTsh2());
		ipdForm.setIct2(bed.getIct2());
		ipdForm.setGtt2(bed.getGtt2());
		
		ipdForm.setDate3(bed.getDate3());
		ipdForm.setHb3(bed.getHb3());
		ipdForm.setFbs3(bed.getFbs3());
		ipdForm.setDpbs3(bed.getDpbs3());
		ipdForm.setUrm3(bed.getUrm3());
		ipdForm.setTsh3(bed.getTsh3());
		ipdForm.setIct3(bed.getIct3());
		ipdForm.setGtt3(bed.getGtt3());
		
		ipdForm.setDate4(bed.getDate4());
		ipdForm.setHb4(bed.getHb4());
		ipdForm.setFbs4(bed.getFbs4());
		ipdForm.setDpbs4(bed.getDpbs4());
		ipdForm.setUrm4(bed.getUrm4());
		ipdForm.setTsh4(bed.getTsh4());
		ipdForm.setIct4(bed.getIct4());
		ipdForm.setGtt4(bed.getGtt4());
		
		ipdForm.setHv_1m(bed.getHv_1m());
		ipdForm.setHbs_ag(bed.getHbs_ag());
		ipdForm.setVdrl(bed.getVdrl());
		ipdForm.setHb_srecta(bed.getHb_srecta());
		ipdForm.setHb_ac(bed.getHb_ac());
		ipdForm.setDuet_markess(bed.getDuet_markess());
		ipdForm.setTriple(bed.getTriple());
		ipdForm.setQuadrple_maicers(bed.getQuadrple_maicers());
		
		ipdForm.setVisit_reason_ids(bed.getVisit_reason_ids());
		
		ipdForm.setGen_condition(bed.getGen_condition());
	    ipdForm.setTemp(bed.getTemp());
	    ipdForm.setPallor(bed.getPallor());
	    ipdForm.setPedal_edema(bed.getPedal_edema());
	    ipdForm.setPulse(bed.getPulse());
	    ipdForm.setBmi(bed.getBmi());
	    ipdForm.setHeight(bed.getHeight());
	    ipdForm.setWeight(bed.getWeight() );
	    ipdForm.setSys_bp(bed.getSys_bp());
	    ipdForm.setDia_bp(bed.getDia_bp());
	    
	    ipdForm.setWall_edema(request.getParameter("wall_edema"));
	    ipdForm.setPa_soft(DateTimeUtils.booleanCheck(bed.getPa_soft()));
	    ipdForm.setFundal_height(DateTimeUtils.isNull(bed.getFundal_height()));
		
	    ipdForm.setCephalic(DateTimeUtils.booleanCheck(bed.getCephalic()));
	    ipdForm.setCephalicVal(DateTimeUtils.isNull(bed.getCephalicVal()));
	    ipdForm.setBreech(DateTimeUtils.booleanCheck(bed.getBreech()));
	    ipdForm.setBaley_size(DateTimeUtils.booleanCheck(bed.getBaley_size()));
	    ipdForm.setBaley_sizeVal(DateTimeUtils.isNull(bed.getBaley_sizeVal()));
	    ipdForm.setTransverse_fhs(DateTimeUtils.booleanCheck(bed.getTransverse_fhs()));
	    ipdForm.setExternal_ballotment(DateTimeUtils.booleanCheck(bed.getExternal_ballotment()));
	    ipdForm.setPs_fhs(DateTimeUtils.isNull(bed.getPs_fhs()));
	    ipdForm.setBeats_min(bed.getBeats_min());
	    ipdForm.setLiquor(bed.getLiquor());
	    ipdForm.setUterine_contractions(bed.getUterine_contractions());
	    
	    ipdForm.setPs_cervix(DateTimeUtils.booleanCheck(bed.getPs_cervix()));
	    ipdForm.setPs_cervixVal(DateTimeUtils.booleanCheck(bed.getPs_cervixVal()));
	    ipdForm.setPs_vagine(DateTimeUtils.booleanCheck(bed.getPs_vagine()));
	    ipdForm.setPs_vagineVal(bed.getPs_vagineVal());
	    ipdForm.setPs_vault(DateTimeUtils.booleanCheck(bed.getPs_vault()));
	    ipdForm.setPs_vaultVal(bed.getPs_vaultVal());
	    ipdForm.setPv_trim(DateTimeUtils.booleanCheck(bed.getPv_trim()));
	    ipdForm.setPv_unettacced(DateTimeUtils.booleanCheck(bed.getPv_unettacced()));
	    ipdForm.setPv_ant(DateTimeUtils.booleanCheck(bed.getPv_ant()));
	    ipdForm.setPv_tubular(DateTimeUtils.booleanCheck(bed.getPv_tubular()));
	    ipdForm.setPv_just_ettacced(DateTimeUtils.booleanCheck(bed.getPv_just_ettacced()));
	    ipdForm.setPv_mid_posits(DateTimeUtils.booleanCheck(bed.getPv_mid_posits()));
	    ipdForm.setPv_soft(DateTimeUtils.booleanCheck(bed.getPv_soft()));
	    ipdForm.setPv_ettacced(DateTimeUtils.booleanCheck(bed.getPv_ettacced()));
	    ipdForm.setEffaced_text(DateTimeUtils.isNull(bed.getEffaced_text()));
	    ipdForm.setPv_post(DateTimeUtils.booleanCheck(bed.getPv_post()));
	    ipdForm.setPv_station(bed.getPv_station());
	    ipdForm.setPv_liquor(bed.getPv_liquor());
	    ipdForm.setPv_pelvis(bed.getPv_pelvis());
	    ipdForm.setPv_position(bed.getPv_position());
	    ipdForm.setPv_os(DateTimeUtils.booleanCheck(bed.getPv_os()));
	    ipdForm.setPv_osVal(bed.getPv_osVal());
	    ipdForm.setPv_membrane(DateTimeUtils.booleanCheck(bed.getPv_membrane()));
	    ipdForm.setPv_MVal(bed.getPv_MVal());
	    ipdForm.setPv_dialated(DateTimeUtils.booleanCheck(bed.getPv_dialated()));
	    ipdForm.setPv_dialatedcm(DateTimeUtils.isNull(bed.getPv_dialatedcm()));
	    ipdForm.setFormtype(bed.getFormtype());
	    ipdForm.setTt_dose1(bed.getTt_dose1());
	    ipdForm.setTt_dose2(bed.getTt_dose2());
	    
	    ipdForm.setAdditonal_notes(bed.getAdditonal_notes());
	    ipdForm.setDiagonosis(bed.getDiagonosis());
	    ipdForm.setTreatment_advice(bed.getTreatment_advice());
	    ipdForm.setInvestigation_advice(bed.getInvestigation_advice());
		ipdForm.setCommencing(bed.getCommencing());
		ipdForm.setDays_dating_scan(bed.getDays_dating_scan());
		ipdForm.setHrf_otherval(bed.getHrf_otherval());
		
		if(!DateTimeUtils.isNull(bed.getCreateddate()).equals("")){
			ipdForm.setDate(DateTimeUtils.getCommencingDate1(bed.getCreateddate().split(" ")[0])+" "+bed.getCreateddate().split(" ")[1]);
		}
		
		ArrayList<Bed> wardlist=bedDao.getAllWardList("0");
		ipdForm.setWardlist(wardlist);
		
		Clinic clinic = new Clinic();
		ClinicDAO clinicDAO  = new JDBCClinicDAO(connection);
		AccountsDAO accountsDAO = new JDBCAccountsDAO(connection);
		ArrayList<Clinic> locationAdressList = accountsDAO.getLocationAddress(loginInfo.getClinicid());
		clinic = clinicDAO.getCliniclistDetails(loginInfo.getClinicid());
		ipdForm.setClinicName(clinic.getClinicName());
		ipdForm.setClinicOwner(clinic.getClinicOwner());
		ipdForm.setOwner_qualification(clinic.getOwner_qualification());
		ipdForm.setLocationAdressList(locationAdressList);
		ipdForm.setAddress(clinic.getAddress());
		ipdForm.setLandLine(clinic.getLandLine());
		ipdForm.setClinicemail(clinic.getEmail());
		ipdForm.setWebsiteUrl(clinic.getWebsiteUrl());
		ipdForm.setClinicLogo(clinic.getUserImageFileName());
		
		session.setAttribute("gynicnewbed", bed);
		
		UserProfileDAO userProfileDAO = new JDBCUserProfileDAO(connection);
		UserProfile userProfile=userProfileDAO.getUserprofileDetails(Integer.parseInt(bed.getPractitionerid()));
		Master discipline=masterDAO.getDisciplineData(userProfile.getDiciplineName());
		ipdForm.setDiscipline(discipline.getDiscipline());
		ipdForm.setDoctor_name(userProfile.getInitial()+" "+userProfile.getFirstname()+" "+userProfile.getLastname());
		ipdForm.setQualification(userProfile.getQualification());
		
		ipdForm.setPrintedBy(loginInfo.getUserId());
		
	} catch (Exception e) {

		e.printStackTrace();
	}
	finally {
		connection.close();
	}
	
	return "printnewgynicform";
}



public String getBirthPlaceList(){
	try {
		Connection connection= Connection_provider.getconnection();
		MasterDAO masterDAO= new JDBCMasterDAO(connection);
		ArrayList<Master> list=masterDAO.getBirthPlaceList();
		
		StringBuffer buffer= new StringBuffer();
		buffer.append("<select id='birthplace' class='form-control chosen-select' name='birthplace'>");
		for(Master master:list){
			buffer.append("<option value='"+master.getName()+"'>"+master.getName()+"</option>");
		}
		buffer.append("</select>");
		
		
		response.setContentType("text/html");
		response.setHeader("Cache-Control", "no-cache");
		response.getWriter().write(""+buffer.toString()+"");
	} catch (Exception e) {
		e.printStackTrace();
	}
	return null;
}

public String addBirthPlaceList(){
	try {
		String name=DateTimeUtils.isNull(request.getParameter("name"));
		Connection connection= Connection_provider.getconnection();
		MasterDAO masterDAO= new JDBCMasterDAO(connection);
		int x=0;
		if(!name.equals("")){
			 x=masterDAO.addBirthPlcae(name);
		}
		
		
		
		response.setContentType("text/html");
		response.setHeader("Cache-Control", "no-cache");
		response.getWriter().write(x);
	} catch (Exception e) {
		e.printStackTrace();
	}
	return null;
}

public String input() throws Exception{
	
	if(!verifyLogin(request)) {
		
		return "login";
	}
	
	String wardid = request.getParameter("wardid");
	String bedid = request.getParameter("bedid");
	String action = request.getParameter("action");
	String casualtyShiftClientId=request.getParameter("clId");
	
	Connection connection = null;
	try{
		if(casualtyShiftClientId!=null){
			action="0";
		}
		connection = Connection_provider.getconnection();
		IpdDAO ipdDAO = new JDBCIpdDAO(connection);
		BedDao bedDao = new JDBCBedDao(connection);
		MasterDAO masterDAO=new JDBCMasterDAO(connection);
		ArrayList<Bed>bedList = ipdDAO.getWardWiseBedList(wardid);
		
		ipdForm.setBedlist(bedList);
		
		ipdForm.setWardid(wardid);
		ipdForm.setBedid(bedid);
		if(action.equals("1") || action.equals("2")){
			String chief_comlint_id=masterDAO.getIpdTemplateId("Chief Complaints");
			String present_ill_id=masterDAO.getIpdTemplateId("Present Illness");
			String past_history_id=masterDAO.getIpdTemplateId("Past History");
			String family_hist_id=masterDAO.getIpdTemplateId("Family History");
			String personal_hist_id=masterDAO.getIpdTemplateId("Personal History");
			String onexami_id=masterDAO.getIpdTemplateId("On Examination");
			String tretment_given_id=masterDAO.getIpdTemplateId("Treatment Given");
			String examination_id=masterDAO.getIpdTemplateId("Examination Findings");
			
			ArrayList<Master> chief_complaints_list=masterDAO.getIpdTemplateListNames(chief_comlint_id);
			ArrayList<Master> present_illness_list=masterDAO.getIpdTemplateListNames(present_ill_id);
			ArrayList<Master> past_history_list=masterDAO.getIpdTemplateListNames(past_history_id);
			ArrayList<Master> family_history_list=masterDAO.getIpdTemplateListNames(family_hist_id);
			ArrayList<Master> personal_hist_list=masterDAO.getIpdTemplateListNames(personal_hist_id);
			ArrayList<Master> on_exam_list=masterDAO.getIpdTemplateListNames(onexami_id);
			ArrayList<Master> treatment_given_list=masterDAO.getIpdTemplateListNames(tretment_given_id);
			ArrayList<Master> examination_finding_list=masterDAO.getIpdTemplateListNames(examination_id);
			
			ipdForm.setChief_complaints_list(chief_complaints_list);
			ipdForm.setPresent_illness_list(present_illness_list);
			ipdForm.setPast_history_list(past_history_list);
			ipdForm.setFamily_history_list(family_history_list);
			ipdForm.setPersonal_hist_list(personal_hist_list);
			ipdForm.setOn_exam_list(on_exam_list);
			ipdForm.setTreatment_given_list(treatment_given_list);
			ipdForm.setExamination_finding_list(examination_finding_list);
		}
		
		ipdForm.setRatewardid(ipdForm.getWardid());
		request.setAttribute("casualtyShiftClientId", casualtyShiftClientId);
		ArrayList<Bed> wardlist=bedDao.getAllWardList(action);
		ipdForm.setLocationshift(DateTimeUtils.isNull(request.getParameter("actionfrom")));
		ipdForm.setWardlist(wardlist);
		
		ipdForm.setActiontype(action);
		
		//AKash 06-08-2020
		//user define date time
		UserProfileDAO userProfileDAO = new JDBCUserProfileDAO(connection);
		AccountsDAO accountsDAO = new JDBCAccountsDAO(connection);
		NotAvailableSlotDAO notAvailableSlotDAO=new JDBCNotAvailableSlotDAO(connection);
		ClientDAO clientDAO = new JDBCClientDAO(connection);
		DiaryManagementDAO diaryManagementDAO=new JDBCDiaryManagentDAO(connection);
		InventoryVendorDAO vendorDAO=new JDBCInventoryVendorDAO(connection);
		TreatmentEpisodeDAO treatmentEpisodeDAO = new JDBCTreatmentEpisode(connection);
		
		//  admisson form filed
		ipdForm.setHourList(PopulateList.hourListNew());
		ipdForm.setMinuteList(PopulateList.getMinuteList());
		String datetime=DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone());
		String temp[] = datetime.split(" ");
		String date = DateTimeUtils.getCommencingDate1(temp[0]);
		ipdForm.setAdmissiondate(date);
		ipdForm.setDischargedate(date);
		String time[] = temp[1].split(":");
		String hh = time[0];
		String mm = time[1];
		ipdForm.setHour(hh);
		ipdForm.setMinute(mm);
		ArrayList<Accounts>thirdPartyList = accountsDAO.getThirdPartyList(loginInfo.getId());
		ipdForm.setThirdPartyList(thirdPartyList);
		ArrayList<UserProfile> mlcdrlist = userProfileDAO.getAllPractitionerList(null,null,null,null,"1");
		ipdForm.setMlcdrlist(mlcdrlist);
		ArrayList<DiaryManagement> userList = notAvailableSlotDAO.getUserList(loginInfo.getId());
		ipdForm.setUserList(userList);
		ArrayList<Client> refrenceList = clientDAO.getReferenceList();
		ipdForm.setRefrenceList(refrenceList);
		ArrayList<Client> refrencedoctorList = clientDAO.getReferenceDoctorList("");
		ipdForm.setRefrencedoctorList(refrencedoctorList);
		ArrayList<String> departmentList= diaryManagementDAO.getDepartmentList();
		ipdForm.setDepartmentList(departmentList);
		
		//add patient filed
		ArrayList<String> initialList = clientDAO.getInitialList();
		ipdForm.setInitialList(initialList);
		//set state and city list
		ArrayList<Master> statelist= vendorDAO.getAllStateList();
		ipdForm.setStatelist(statelist);
		ArrayList<Master> citylist= vendorDAO.getAllCityList();
		ipdForm.setCitylist(citylist);
		ipdForm.setCountryList(PopulateList.countryList());
		ArrayList<Client> clientOccupationList = clientDAO.getOccupationList();
		Client client1 = new Client();
		client1.getOther();
		clientOccupationList.add(client1);
		ipdForm.setClientOccupationList(clientOccupationList);
		ArrayList<Client> surgeryList = clientDAO.getSurgeryList();
		if(surgeryList.size()==0){
			surgeryList = new ArrayList<Client>();
		}
		Client client3 = new Client();
		client3.getOther();
		surgeryList.add(client3);
		ipdForm.setSurgeryList(surgeryList);
		ArrayList<Client> diagnosisList=clientDAO.getEmrTreatmentTypeList();
		ipdForm.setDiagnosisList(diagnosisList);
		ArrayList<Client> thirdPartyTypeList = new ArrayList<Client>();
		thirdPartyTypeList = clientDAO.getThirdPartyType();
		ipdForm.setThirdPartyTypeList(thirdPartyTypeList);
		ArrayList<Client> condtitionList = clientDAO.getEmrTreatmentTypeList();
		ipdForm.setCondtitionList(condtitionList);
		ArrayList<TreatmentEpisode> sourceOfReferralList = treatmentEpisodeDAO.getSourceOfReferralList();
		ipdForm.setSourceOfReferralList(sourceOfReferralList);
		ipdForm.setBirthPlaceList(masterDAO.getBirthPlaceList());
		ArrayList<UserProfile> practionerlist = userProfileDAO.getVisitingPractitiner();
		ipdForm.setPractionerlist(practionerlist);
		
		ArrayList<Master> disciplineList = masterDAO.getDisciplineDataList();
		ipdForm.setDisciplineList(disciplineList);
		
		
		
		if(action.equals("1")||action.equals("2")){
			return "addipdcasualty";
		}
		
		
		
	}catch(Exception e){
		e.printStackTrace();
	}
	finally {
		connection.close();
	}
	
	return INPUT;
}

public String saveform() throws Exception
{
	
	if(!verifyLogin(request)) {
		
		return "login";
	}
	
	Connection connection=null;
	String action = request.getParameter("action");
	try {

		connection=Connection_provider.getconnection();
		BedDao bedDao=new JDBCBedDao(connection);
		UserProfileDAO profileDAO=new JDBCUserProfileDAO(connection);
		ClinicDAO clinicDAO= new JDBCClinicDAO(connection);
		TreatmentEpisodeDAO treatmentEpisodeDAO=new JDBCTreatmentEpisode(connection);
		ClientDAO clientDAO=new JDBCClientDAO(connection);
		AccountsDAO accountsDAO = new JDBCAccountsDAO(connection);
		IpdDAO ipdDAO = new JDBCIpdDAO(connection);
		Bed bed=new Bed();
		
		/*for(Bed tbed:ipdForm.getConditions()) {
			
			ipdForm.setConditionid(tbed.getConditionid());
			break;
			
		}*/
		
		Client client=clientDAO.getClientDetails(ipdForm.getClientid()); 
		CompleteAptmDAO completeAptmDAO=new JDBCCompleteAptmDAO(connection);
		String payee=ipdForm.getPayee();
       
		if(payee.equals("0")){
        	payee="Client";
        	String dateTime=DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone()).split(" ")[0];
        	DateFormat df = new SimpleDateFormat("dd/MM/yyyy"); 
        	Date date=Calendar.getInstance().getTime();
        	String referalDate=df.format(date);
        	String name="IPD"+client.getFirstName()+""+client.getLastName()+dateTime;
        	TreatmentEpisode treatmentEpisode=new TreatmentEpisode();
        	treatmentEpisode.setClientId(Integer.parseInt(ipdForm.getClientid()));
        	treatmentEpisode.setPayby("Client");
        	treatmentEpisode.setConsultationLimit("3");
        	treatmentEpisode.setSessions("1");
        	treatmentEpisode.setDiaryUser(ipdForm.getPractitionerid());
        	treatmentEpisode.setTreatmentEpisodeName(name);
        	treatmentEpisode.setReferalDate(referalDate);
        	
        	Calendar calendar=Calendar.getInstance();
        	calendar.add(Calendar.DATE, 30);
        	String referalEndDate=df.format(calendar.getTime());
        	treatmentEpisode.setReferalendDate(referalEndDate);
        	treatmentEpisode.setSpendLimit("3");
        	dateTime=DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone());
        	String clientName = client.getTitle()+" "+client.getFirstName()+" "+ client.getLastName();
        	treatmentEpisode.setClientName(clientName);
        	int tretmentEpisodeId=treatmentEpisodeDAO.saveTreatmentEpisode(treatmentEpisode, dateTime);
        	//28 oct 2017   
        	int res = ipdDAO.saveDischargeCheckList(tretmentEpisodeId,ipdForm.getClientid());
        	ipdForm.setTreatmentepisodeid(""+tretmentEpisodeId+"");
        	bed.setTreatmentepisodeid(ipdForm.getTreatmentepisodeid());
        	bed.setTpid("0");
        } else {
        	
        	 //@ jitu from auto 
        	String dateTime=DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone()).split(" ")[0];
        	DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        	
        	Date date=Calendar.getInstance().getTime();
        	String referalDate=df.format(date);
        	String name="IPD"+client.getFirstName()+""+client.getLastName()+dateTime;
        	TreatmentEpisode treatmentEpisode=new TreatmentEpisode();
        	treatmentEpisode.setClientId(Integer.parseInt(ipdForm.getClientid()));
        	treatmentEpisode.setPayby("Third Party");
        	treatmentEpisode.setConsultationLimit("3");
        	treatmentEpisode.setSessions("1");
        	treatmentEpisode.setDiaryUser(ipdForm.getPractitionerid());
        	treatmentEpisode.setTreatmentEpisodeName(name);
        	treatmentEpisode.setReferalDate(referalDate);
        	
        	
        	Calendar calendar=Calendar.getInstance();
        	calendar.add(Calendar.DATE, 30);
        	String referalEndDate=df.format(calendar.getTime());
        	treatmentEpisode.setReferalendDate(referalEndDate);
        	treatmentEpisode.setSpendLimit("50000");
        	dateTime=DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone());
        	String clientName = client.getTitle()+" "+client.getFirstName()+" "+ client.getLastName();
        	treatmentEpisode.setClientName(clientName);
        	
        	ThirdParty thirdParty= client.getTpDetails();
        	treatmentEpisode.setInvoicee(thirdParty.getCompanyName());
        	int tretmentEpisodeId=treatmentEpisodeDAO.saveTreatmentEpisode(treatmentEpisode, dateTime);
        	
        	int res = ipdDAO.saveDischargeCheckList(tretmentEpisodeId,ipdForm.getClientid());
        	ipdForm.setTreatmentepisodeid(""+tretmentEpisodeId+"");
        	bed.setTreatmentepisodeid(ipdForm.getTreatmentepisodeid());
        	
        	payee="Third Party";
        	bed.setTreatmentepisodeid(ipdForm.getTreatmentepisodeid());
        	//28 oct 2017   save discharge checklist
        	if(ipdForm.getTreatmentepisodeid()==null){
        		
        	}else if(!ipdForm.getTreatmentepisodeid().equals("0")){
        		 res = ipdDAO.saveDischargeCheckList(Integer.parseInt(ipdForm.getTreatmentepisodeid()),ipdForm.getClientid());
            	bed.setTpid(ipdForm.getTpid());
        	}
        	
        }
        String tpid=ipdForm.getTpid();
        int r=clientDAO.updatePayeeofPatient(ipdForm.getClientid(),payee,tpid);
        
        //update emergency contact details
        Client clientcont=new Client();
        clientcont.setEmergencyContName(ipdForm.getRelativename());
        clientcont.setEmergencyContNo(ipdForm.getRelationcont());
        clientcont.setRelation(ipdForm.getRelation());
        clientcont.setId(Integer.parseInt(ipdForm.getClientid()));
        clientcont.setReference(ipdForm.getRefferedby());
        // and reference
        r=clientDAO.updateEmergencyDetails(clientcont);
        
		bed.setClientid(ipdForm.getClientid());
		bed.setPractitionerid(ipdForm.getPractitionerid());
		bed.setConditionid(ipdForm.getConditionid());
		 // 07-08-2020 for speed up
		 /*if(ipdForm.getPractitionerid()!=null){
		       if(!ipdForm.getPractitionerid().equals("")){
		         
		           int practitionerid=Integer.parseInt(ipdForm.getPractitionerid());
		           UserProfile userProfile=profileDAO.getUserprofileDetails(practitionerid);
		           bed.setSpeciality(userProfile.getDiciplineName());
		       }
		 }*/
		UserProfile profile=profileDAO.getUserprofileDetails(Integer.parseInt(DateTimeUtils.numberCheck(bed.getPractitionerid())));
		bed.setSpeciality(profile.getDiciplineName());
		bed.setDepartment(ipdForm.getDepartment());
		 
		String refenceid=ipdForm.getRefferedby(); 
		String name= clientDAO.getReferenceName(refenceid);
		bed.setReferenceid(refenceid);
		
		bed.setRefferedby(name);
		bed.setWardid(ipdForm.getWardid());
		bed.setBedid(ipdForm.getBedid());
		bed.setStatus(ipdForm.getStatus());
		bed.setAddmissionfor(ipdForm.getAddmissionfor());
		bed.setReimbursment(ipdForm.getReimbursment());
		bed.setSecndryconsult(ipdForm.getSecndryconsult());
		bed.setMlccase(ipdForm.getMlccase());
		bed.setMlcno(ipdForm.getMlcno());
		bed.setAdmissiondetails(ipdForm.getAdmissiondetails());
		bed.setSuggestedtrtment(ipdForm.getSuggestedtrtment());
		bed.setHosp(ipdForm.getHosp());
		bed.setPackagename(ipdForm.getPackagename());
		bed.setRefdocotor(ipdForm.getRefdocotor());
		bed.setReferenceid(ipdForm.getRefdocotor());
		String referedby=request.getParameter("referedby");
		referedby=referedby.replace("<br>", ",");
		bed.setRefferedby(referedby);
		 // 07-08-2020 for speed up
		//chiefcomplains, presentillness, pasthistory, personalhist, familyhist, onexamination, treatmentepisodeid
		/*bed.setChiefcomplains(ipdForm.getChiefcomplains());
		bed.setPresentillness(ipdForm.getPresentillness());
		bed.setPasthistory(ipdForm.getPasthistory());
		bed.setPersonalhist(ipdForm.getPersonalhist());
		bed.setFamilyhist(ipdForm.getFamilyhist());
		bed.setSurgicalnotes(ipdForm.getSurgicalnotes());
		bed.setOnexamination(ipdForm.getOnexamination());
	    bed.setEarlierinvest(ipdForm.getEarlierinvest());
	    bed.setAdmission_reason(ipdForm.getAdmission_reason());*/
		// 07-08-2020 for speed up
		//peditric
	    /*bed.setBirthhist(ipdForm.getBirthhist());
	    bed.setDiethist(ipdForm.getDiethist());
		bed.setDevelopmenthist(ipdForm.getDevelopmenthist());
		bed.setEmmunizationhist(ipdForm.getEmmunizationhist());
		bed.setHeadcircumference(ipdForm.getHeadcircumference());
		bed.setMidarmcircumference(ipdForm.getMidarmcircumference());
		bed.setLength(ipdForm.getLength());
		bed.setWtaddmission(ipdForm.getWtaddmission());
		bed.setWtdischarge(ipdForm.getWtdischarge());
		
		bed.setSuggestoper(ipdForm.getSuggestoper());
		bed.setSystdepartment(bed.getSystdepartment());
		bed.setFpcondition(ipdForm.getFpcondition());
		bed.setFpnotest(ipdForm.getFpnotest());
		bed.setNauseacondition(ipdForm.getNauseacondition());
		bed.setNauseanotes(ipdForm.getNauseanotes());
		bed.setFnucondition(ipdForm.getFnucondition());
		bed.setFnunotes(ipdForm.getFnunotes());
		bed.setSmcondition(ipdForm.getSmcondition());
		bed.setSmnotes(ipdForm.getSmnotes());
		bed.setChestpaincond(ipdForm.getChestpaincond());
		bed.setChestpainnotes(ipdForm.getChestpainnotes());
		bed.setDimvisioncond(ipdForm.getDimvisioncond());
		bed.setDimvisionnotes(ipdForm.getDimvisionnotes());
		
		//dimvisionnotes, hgucondition, hgunotes, hmcondition, hmnotes, jointpaincond, jointpainnotes, 
		bed.setHgucondition(ipdForm.getHgucondition());
		bed.setHgunotes(ipdForm.getHgunotes());
		bed.setHmcondition(ipdForm.getHmcondition());
		bed.setHmnotes(ipdForm.getHmnotes());
		bed.setJointpaincond(ipdForm.getJointpaincond());
		bed.setJointpainnotes(ipdForm.getJointpainnotes());
		
		//constipationcond, constpationnotes, specialnotes, edemafeetcondi, edemafeetnotes, hematuriacondi, 
		bed.setConstipationcond(ipdForm.getConstipationcond());
		bed.setConstpationnotes(ipdForm.getConstpationnotes());
		bed.setSpecialnotes(ipdForm.getSpecialnotes());
		bed.setEdemafeetcondi(ipdForm.getEdemafeetcondi());
		bed.setEdemafeetnotes(ipdForm.getEdemafeetnotes());
		bed.setHematuriacondi(ipdForm.getHematuriacondi());
		bed.setHematurianotes(ipdForm.getHematurianotes());
		
		//hematurianotes, bmcondition, bmnotes, oliguriacondi, oligurianotes, pstgucondi, pstgunotes, 
		bed.setBmcondition(ipdForm.getBmcondition());
		bed.setBmnotes(ipdForm.getBmnotes());
		bed.setOliguriacondi(ipdForm.getOliguriacondi());
		bed.setOligurianotes(ipdForm.getOligurianotes());
		bed.setPstgucondi(ipdForm.getPstgucondi());
		bed.setPstgunotes(ipdForm.getPstgunotes());
		
		//bmecondition, bmenotes, tnecondition, tnenotes, weaknesscondi, weaknessnotes, ihcondition, ihnotes
		bed.setBmecondition(ipdForm.getBmecondition());
		bed.setBmenotes(ipdForm.getBmenotes());
		bed.setTnecondition(ipdForm.getTnecondition());
		bed.setTnenotes(ipdForm.getTnenotes());
		bed.setWeaknesscondi(ipdForm.getWeaknesscondi());
		bed.setWeaknessnotes(ipdForm.getWeaknessnotes());
		bed.setIhcondition(ipdForm.getIhcondition());
		bed.setIhnotes(ipdForm.getIhnotes());
		bed.setAlergies(ipdForm.getAlergies());
		
		//gynic details
		bed.setAlcohal(ipdForm.getAlcohal());
		bed.setDrugs(ipdForm.getDrugs());
		bed.setOther_medication(ipdForm.getOther_medication());
		bed.setTobaco(ipdForm.getTobaco());
		bed.setTobaconotes(ipdForm.getTobaconotes());
		bed.setSmoking(ipdForm.getSmoking());
		
		bed.setAge_menarche(ipdForm.getAge_menarche());
		bed.setLmp(ipdForm.getLmp());
		bed.setLlmp(ipdForm.getLlmp());
		bed.setPmc(ipdForm.getPmc());
		
		
		bed.setCycle_length(ipdForm.getCycle_length());
		bed.setDuration_flow(ipdForm.getDuration_flow());
		bed.setAmount_flow(ipdForm.getAmount_flow());
		bed.setDysmenorrhea(ipdForm.getDysmenorrhea());
		bed.setDyspareunia(ipdForm.getDyspareunia());
		bed.setHopassing_clots(ipdForm.getHopassing_clots());
		
		bed.setWhite_disc_itching(ipdForm.getWhite_disc_itching());
		bed.setIntercourse_freq(ipdForm.getIntercourse_freq());
		bed.setGalactorrea(ipdForm.getGalactorrea());
		
		bed.setHo_contraception(ipdForm.getHo_contraception());
		bed.setRubella_vaccine(ipdForm.getRubella_vaccine());
		bed.setMenopause(ipdForm.getMenopause());
		bed.setNulligravida(ipdForm.getNulligravida());
		bed.setCurrent_pregnent(ipdForm.getCurrent_pregnent());
		bed.setIud(ipdForm.getIud());
		
		bed.setLive_boys(ipdForm.getLive_boys());
		bed.setLive_girls(ipdForm.getLive_girls());
		bed.setStillbirths(ipdForm.getStillbirths());
		bed.setAbortions(ipdForm.getAbortions());
		bed.setDeath_children(ipdForm.getDeath_children());
		
		//parity_aboration_notes,p,l,a,d
		bed.setParity_abortion_notes(ipdForm.getParity_abortion_notes());
		bed.setP(ipdForm.getP());
		bed.setL(ipdForm.getL());
		bed.setA(ipdForm.getA());
		bed.setD(ipdForm.getD());*/
		
		bed.setMlcrefdoctor(ipdForm.getMlcrefdoctor());
		bed.setMlccase(ipdForm.getMlccase());
		
		
		//  10 July 2018 MLC No generated
		if(bed.getMlccase()!=null){
			if(bed.getMlccase().equals("1")){
				//save MLC abrivation seq no
				String cdate = DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone()).split(" ")[0];
				String tempd[] = cdate.split("-");
				String y = tempd[0];
				boolean checkifseq = ipdDAO.checkifSequenceExist(y);
				String mlcabrivationid = "";
				
				String clinicabrivation = clientDAO.getClinicAbrivation(loginInfo.getClinicid());
				
				
				if(checkifseq){
					int seqno = ipdDAO.getSqeunceNumber(cdate);
					seqno++;
					int k = ipdDAO.InserCdateSeq(cdate,seqno);
					//SNH170609001
					int yr = Integer.parseInt(y)%1000;
					mlcabrivationid = clinicabrivation+"MLC"+ yr + seqno;
				}else{
					
					int seqno = 1;
					int k = ipdDAO.InserCdateSeq(cdate,seqno);
					//String seqno = clientDAO.getSqeunceNumber(cdate);
					int yr = Integer.parseInt(y)%1000;
					mlcabrivationid = clinicabrivation+"MLC"+ yr + seqno;
				}
				bed.setMlcabrivationid(mlcabrivationid);
			}else{
				bed.setMlcrefdoctor("");
			}
		}else{
			bed.setMlcrefdoctor("");
		}
		
		String stdchargesetup= clinicDAO.getStdChargeSetup(loginInfo.getClinicid());
		bed.setStdchargesetup(stdchargesetup);
		String date = DateTimeUtils.getCommencingDate1(ipdForm.getAdmissiondate()) + " " + ipdForm.getHour() + ":" + ipdForm.getMinute() + ":20" ;
		bed.setAddmitedbyuserid(loginInfo.getUserId());
		
		boolean checkbedidexsist = bedDao.checkBedidExixtst(ipdForm.getBedid());
		int newadmisisonid = 0;
		if(!checkbedidexsist){
			 String action1 = "0";
			 int maxid = bedDao.getMaxIpdId();
			 maxid = maxid + 1;
			 if(loginInfo.isKalmegha()){
	    		bed.setIpdabrivationid(bedDao.generateIPDKalmeghAbrivation(DateTimeUtils.getCommencingDate1(ipdForm.getAdmissiondate().split(" ")[0]),action));
	    	 }else{
	    		bed.setIpdabrivationid(ipdDAO.generateIPDSequenceNewFormat(action));
	    	 }
			 //bed.setIpdabrivationid(ipdDAO.generateIPDSequenceNewFormat(action));
			 int admissionid=bedDao.addIpdAdmissionForm(bed,date,action1,maxid);
			 String lastopdno=bedDao.getlastOpdNo(bed.getClientid());
			 if(lastopdno!=null) {
				 int re=bedDao.updateOpdNo(admissionid,lastopdno);
			 }
			
			 Client client2 = new Client();
			 client2.setDiagnosis("");
			 client2.setDrnameId(bed.getPractitionerid());
			 client2.setAdmissiondate(DateTimeUtils.getCommencingDate1(ipdForm.getAdmissiondate()));
			 client2.setDischargedate("");
			 client2.setAbrivationid(bed.getIpdabrivationid());
			 client2.setIpdid(""+admissionid);
			 bedDao.saveFakeIPDPatient(client2,client.getId());
			 
			 newadmisisonid = admissionid;
       //ward charges change
			 if(!ipdForm.getWardid().equals(ipdForm.getRatewardid())){
				/* ipdDAO.rateChangeFlagWard(""+admissionid,"1");
				 Bed bed1= bedDao.getEditIpdData(""+admissionid);
				 ipdDAO.rateChangeWardFromMaster(ipdForm.getRatewardid(), bed1.getBedid());*/
			 }
        
        if(action.equals("1")){
        	int up = bedDao.updateCasualtybedEmpty(ipdForm.getId());
        }
        String shiftedfromcasualty=request.getParameter("ttyy");
        if(shiftedfromcasualty!=null){
        	int casual=bedDao.updateCasualtybedEmptyByClient(ipdForm.getClientid());
        }
        
        //save gynic obs history
        //akash 07-08-2020 for speed up
       /* if(ipdForm.getObslist()!=null){
        	 for(Bed bed2 :ipdForm.getObslist()){
            	 
        	     int res=bedDao.saveGynicObsData(admissionid,bed2);
          }
        }*/
       
        //On auto charge
        int res11111=accountsDAO.updateAutochargeFlagClient(ipdForm.getClientid(),"1");
        
        //save log
        String logcommencing = DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone()).split(" ")[0];
        int log = bedDao.saveAdmissionLog(bed,date,admissionid);
        
        String cutofftime = bedDao.getHospitalCutoffTime(loginInfo.getClinicid());
        String logdate = DateTimeUtils.getCommencingDate1(ipdForm.getAdmissiondate()) + " " + cutofftime + ":20" ;
        if(!cutofftime.equals("0")){
        	int log1 = bedDao.saveBedChangeLog(bed,logdate,admissionid,logcommencing,date,0);
        }else{
        	int log1 = bedDao.saveBedChangeLog(bed,date,admissionid,logcommencing,date,0);
        }
        
        
        if(!client.getCasualtyid().equals("0")){
        	
        	int updc = bedDao.updateCasualtyid(client.getId());
        }
        
        //akash 07-08-2020
        //saveConditions(admissionid,ipdForm.getTreatmentepisodeid());
        
		//getting registration charge
		Clinic registrationcharge=completeAptmDAO.getIpdRegistrationCharge(loginInfo.getClinicid()); 
		
		if(registrationcharge.getIpdregcharge()!=null && registrationcharge.getIpdregtype()!=null && !registrationcharge.getIpdregcharge().equals("0")) {
			
			  int amt=Integer.parseInt(registrationcharge.getIpdregcharge());  
			  String ctype=registrationcharge.getIpdregtype();
			  
			  if(!ctype.equals("None")){
				  
				   if(ctype.equals("All")){
					   
					   saveCharges(String.valueOf(amt), bed, client, admissionid, completeAptmDAO,ipdForm.getAdmissiondate());
					   
				   }
				   else if(ctype.equals("Third Party")){
					   
					   if(client.getWhopay().equalsIgnoreCase("Third Party")) {
						     
						      saveCharges(String.valueOf(amt), bed, client, admissionid, completeAptmDAO,ipdForm.getAdmissiondate());
						   
					   }
				   }
				   else if(ctype.equals("Self")){
					   
					   if(client.getWhopay().equalsIgnoreCase("Self") || client.getWhopay().equalsIgnoreCase("Client")){
						   
			                 			    
						   saveCharges(String.valueOf(amt), bed, client, admissionid, completeAptmDAO,ipdForm.getAdmissiondate());
					   }
				   }
				  
			  }
			  
		}
		if(bed.getMlccase()!=null){
			if(bed.getMlccase().equals("1")){
				//lokesh mlc charge reg 2/11/18
	      		if(registrationcharge.getMlc_charge()!=0){
	      			saveChargesMLC(String.valueOf(registrationcharge.getMlc_charge()),bed, client, admissionid, completeAptmDAO,ipdForm.getAdmissiondate());
	      		}
			}
		}
		
		
	   if(tpid==null){
       	  tpid="0";
       }
       if(tpid.equals("")){
        	tpid="0";
       }
       
       
		//adding charges
		//old code
		//ArrayList<Master> chargeList=completeAptmDAO.getStandardCharges(bed.getWardid(), client.getWhopay());
       AppointmentTypeDAO appointmentTypeDAO= new JDBCAppointmentTypeDAO(connection);
		   	
       
		//new code
       ArrayList<Master> chargeList=appointmentTypeDAO.getStandardChargeList(ipdForm.getWardid(), tpid, payee,loginInfo);
		
		String stdcharges="0";
		int invoiceid=0;
		
		
		/*String date1 = DateTimeUtils.getDateinSimpleFormate(new Date());
		String stemp[] = date1.split(" ");
		
		String temp[] = stemp[0].split("-");
		date1 = temp[2] + "-" + temp[1] + "-" + temp[0];*/
		
		String date1 = DateTimeUtils.getCommencingDate1(ipdForm.getAdmissiondate());
		
		
		if(chargeList.size()!=0){ 
			
			CompleteAppointment appointment=new CompleteAppointment();
			appointment.setClientId(bed.getClientid());
			appointment.setPractitionerId(bed.getPractitionerid());
			appointment.setChargeType("Charge");
			appointment.setLocation("1");
		    appointment.setAdditionalcharge_id("001");
		    appointment.setIpdid(admissionid);
		    appointment.setInvoiceDate(date1);
		    appointment.setIpd("1");
		    appointment.setAppointmentid("0");
		    appointment.setGinvstid("0");
		    appointment.setWardid(bed.getWardid());
		    if(client.getWhopay()!=null){
		    	
		    	if(client.getWhopay().equals("Self") || client.getWhopay().equals("Client")){
		    	       
		    		appointment.setPolicyExcess("0");
		    		appointment.setPayBuy("0");
		    	} else {
		    		appointment.setPolicyExcess("1");
		    		appointment.setPayBuy("1");
		    	}
		    }
		    
		    if(stdchargesetup.equals("0")){
		    	invoiceid=completeAptmDAO.saveStndCharge(appointment.getClientId(), String.valueOf(admissionid), stdcharges);
    		    
    		    invoiceid=completeAptmDAO.saveAmpmInvoice(appointment,loginInfo.getId(),loginInfo.getUserId());
		    }
		        		    
		    String nowDate=DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone()).split(" ")[0];
		    String n1 = DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone()).split(" ")[1];
		    String datetime=DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone());
			String stddate= DateTimeUtils.getCommencingDate1(ipdForm.getAdmissiondate());
			String sdate = ipdForm.getAdmissiondate() + " " + ipdForm.getHour() + ":" + ipdForm.getMinute() + ":00";
			  
            if(!cutofftime.equals("0")){
            	sdate = ipdForm.getAdmissiondate() + " " + cutofftime + ":00";
            }
			String edate = DateTimeUtils.getCommencingDate1(nowDate) + " "+ n1;
			long diff= DateTimeUtils.getDifferenceOfTwoDateDBFormat(stddate, nowDate);
			int qty =(int) diff;
			if(qty<0){
				 qty=0;
			}
			
			if(qty==0){
				qty=1;
			} else {
				qty++;
			}
			
		    
		    String fullname=client.getTitle()+" "+client.getFirstName()+" "+client.getLastName();
		    appointment.setUser(fullname);
		    appointment.setCommencing(date1);   
		    String dt2=date1;
		    for(Master master:chargeList){
		    	  
		    	   String chargeId=String.valueOf(master.getId());
		    	   appointment.setApmtType(master.getName());
		    	   appointment.setCharges(master.getCharge());
		    	   appointment.setAdditionalcharge_id(chargeId);
		    	   if(loginInfo.getIskunal()!=1){
		    		   appointment.setMasterchargetype("Bed Charge");
				   }else{
					   appointment.setMasterchargetype("Bed Charges");
				   }
		    	   //appointment.setMasterchargetype("Accommodation Charges");
		    	   appointment.setIpd(Integer.toString(admissionid));
		    	   appointment.setWardid(ipdForm.getWardid());
		    	  
		    	   qty = DateTimeUtils.getDifferanceofDateWithTime(sdate, edate, master.getChargehours());
		    	   int newqty=qty;
		    	   if(qty==0){
		    		   qty++;
		    	   }
		    	  
		    	   appointment.setQuantity(qty);
		    	   appointment.setStdflag("1");
		    	   int res=0;
		    	   if(stdchargesetup.equals("0")){
//		    		   if(loginInfo.getIskunal()==1){
		    			   //appointment.setMasterchargetype(master.getMasterchargetype());
		    		   if(qty>0){
		    			   for(int i=0;i<=newqty;i++){
		    				   int w=1;
		    				    	if(i==0){
		    				    		w=0;
		    				    		appointment.setCommencing(dt2);
		    				    	}
		    				   appointment.setQuantity(1);
		    				   SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
		    				   Date d=sdf1.parse(appointment.getCommencing());
		    				   Calendar cal = Calendar.getInstance();
		    				    
		    				    cal.setTime(d);
		    				    cal.add(Calendar.DATE, w);
		    				    String dt=sdf1.format(cal.getTime());
		    				    appointment.setCommencing(dt);    
		    				    res=completeAptmDAO.saveInvoiceAssesment(appointment, invoiceid); 
		    			   }
		    			   
		    			   }
		    			   
//		    		   }else{
//		    			   res=completeAptmDAO.saveInvoiceAssesment(appointment, invoiceid); 
//		    		   }
//		    		   int res=completeAptmDAO.saveInvoiceAssesment(appointment, invoiceid);
    		    	   
    		    	   int result= appointmentTypeDAO.saveStdCharge(String.valueOf(admissionid),chargeId,res,"1",datetime,"");
		    	   }
		    	   
		    }
		    
		 //   int upd = appointmentTypeDAO.setInprocessforNewShiftCharges(invoiceid,log1);
		    
		}
        
        //sending sms to practitioner
        
        
        DiagnosisDAO diagnosisDAO=new JDBCDiagnosisDAO(connection);
        
        String fullname=client.getTitle()+" "+client.getFirstName()+" "+client.getLastName();
        UserProfileDAO userProfileDAO=new JDBCUserProfileDAO(connection);
        String wardname=ipdDAO.getIpdWardName(bed.getWardid());
        String bedname=ipdDAO.getIpdBedName(bed.getBedid());
        String condition=diagnosisDAO.getDiagnosisName(bed.getConditionid()).getName();
        
        //UserProfile profile=userProfileDAO.getUserprofileDetails(Integer.parseInt(bed.getPractitionerid()));
        String datetime=DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone());
        String time[]=datetime.split(" ");
        
//        boolean smsisActive=clinicDAO.isSmsActive(loginInfo.getId());
        
//        if(smsisActive)
        if(loginInfo.isSms_on_newadm()==true){
        	String message="New patient "+fullname+" admitted to "+wardname+" ward bed "+bedname+", condition "+condition+" at "+time[1]+"";
        	SmsService smsService=new SmsService();
        	String templateid="";
        	smsService.sendSms(message, profile.getMobile(), loginInfo, new EmailLetterLog(),templateid);
        	//sms sent
        }
		
        /*//  27 Nov 2017 
		
		String chiefcomplatetempname = ipdForm.getChiefcomplatetempname();
		String presentillnesstempname = ipdForm.getPresentillnesstempname();
		String pasthistorytempname = ipdForm.getPasthistorytempname();
		String personalhistorytempname = ipdForm.getPersonalhistorytempname();
		String familyhistorytempname = ipdForm.getFamilyhistorytempname();
		String onexaminationtempname = ipdForm.getOnexaminationtempname();
		String treatmentgiventempname = ipdForm.getTreatmentgiventempname();
		
		if(chiefcomplatetempname!=null){
			if(!chiefcomplatetempname.equals("")){
				int res = ipdDAO.saveIPDTemplate(chiefcomplatetempname,"1",ipdForm.getDepartment(),ipdForm.getChiefcomplains());
			}
		}
		if(presentillnesstempname!=null){
			if(!presentillnesstempname.equals("")){
				int res = ipdDAO.saveIPDTemplate(presentillnesstempname,"2",ipdForm.getDepartment(),ipdForm.getPresentillness());
			}
		}
		if(pasthistorytempname!=null){
			if(!pasthistorytempname.equals("")){
				int res = ipdDAO.saveIPDTemplate(pasthistorytempname,"3",ipdForm.getDepartment(),ipdForm.getPasthistory());
			}
			
		}
		if(personalhistorytempname!=null){
			if(!personalhistorytempname.equals("")){
				int res = ipdDAO.saveIPDTemplate(personalhistorytempname,"5",ipdForm.getDepartment(),ipdForm.getPersonalhist());
			}
			
		}
		if(familyhistorytempname!=null){
			if(!familyhistorytempname.equals("")){
				int res = ipdDAO.saveIPDTemplate(familyhistorytempname,"4",ipdForm.getDepartment(),ipdForm.getFamilyhist());
			}
			
		}
		if(onexaminationtempname!=null){
			if(!onexaminationtempname.equals("")){
				int res = ipdDAO.saveIPDTemplate(onexaminationtempname,"6",ipdForm.getDepartment(),ipdForm.getOnexamination());
			}
		}
		if(treatmentgiventempname!=null){
			if(!treatmentgiventempname.equals("")){
				int res = ipdDAO.saveIPDTemplate(treatmentgiventempname,"7",ipdForm.getDepartment(),ipdForm.getTreatmentepisodeid());
			}
		}*/
		
	}
	//ipdDAO.createAdmissionView();
	//ipdDAO.createDischargeView();
	session.setAttribute("ipddash_forreload", "1");
	if(ipdForm.getSaveandprintadmission()==1){
		session.setAttribute("saveandprintadmission", ""+newadmisisonid);
		return "ipdadmissionformprint";
	}
		
	if(ipdForm.getLocationshift().equals("2")){
		return "daycare";
	}	
	} catch (Exception e) {
        e.printStackTrace();	
	}
	finally{
		connection.close();
	}
	return "save";
}

public void saveConditions(int admissionid,String treatmentepisodeid) throws Exception{
	
	Connection connection=null;
	
	try {
		connection=Connection_provider.getconnection();
        BedDao bedDao=new JDBCBedDao(connection);
        //for report
        int d=bedDao.deleteIpdConditionifExist(admissionid,treatmentepisodeid);
        
        String lastmodified=DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone());
        int i = 0;
		for(Bed bed:ipdForm.getConditions()) {
			
			if(bed==null){
				continue;
			}
			bed.setLastmodified(lastmodified);
			bed.setTreatmentepisodeid(treatmentepisodeid);
			if(i==0){
				int upd = bedDao.updateIpdCondition(admissionid,bed.getConditionid());
			}
		     int result=bedDao.addCondition(admissionid,bed);
		     result=bedDao.addIpdConditionReport(admissionid,bed); 
		    i++;
		}
	
	} catch (Exception e) {

	    e.printStackTrace();
	}
	finally {
		connection.close();
	}
}

private void saveCharges(String amount,Bed bed,Client client,int ipdid,CompleteAptmDAO completeAptmDAO,String admissiondate)throws Exception {
	
	try {
		
		int invoiceid=0;
		
		
		/*String date1 = DateTimeUtils.getDateinSimpleFormate(new Date());
		String stemp[] = date1.split(" ");
		
		String temp[] = stemp[0].split("-");
		date1 = temp[2] + "-" + temp[1] + "-" + temp[0];*/
		
		String date1 = DateTimeUtils.getCommencingDate1(admissiondate);
		
			CompleteAppointment appointment=new CompleteAppointment();
			appointment.setClientId(bed.getClientid());
			appointment.setPractitionerId(bed.getPractitionerid());
			appointment.setChargeType("Charge");
			appointment.setLocation("1");
		    appointment.setAdditionalcharge_id("0");
		    appointment.setIpdid(ipdid);
		    appointment.setInvoiceDate(date1);
		    appointment.setIpd("1");
		    appointment.setAppointmentid("0");
		    appointment.setWardid(bed.getWardid());
		    if(client.getWhopay()!=null){
		    	
		    	if(client.getWhopay().equals("Self") || client.getWhopay().equals("Client")){
		    	       
		    		appointment.setPolicyExcess("0");
		    		appointment.setPayBuy("0");
		    	} else {
		    		appointment.setPolicyExcess("1");
		    		appointment.setPayBuy("1");
		    	}
		    }
		    		    
		    invoiceid=completeAptmDAO.saveAmpmInvoice(appointment,loginInfo.getId(),loginInfo.getUserId());
		    
		    String fullname=client.getTitle()+" "+client.getFirstName()+" "+client.getLastName();
		    appointment.setUser(fullname);
		    appointment.setCommencing(date1);     
		    	  
		   appointment.setApmtType("Ipd Registration Charge");
		   appointment.setCharges(amount);
		   appointment.setAdditionalcharge_id("0");
		   appointment.setMasterchargetype("Ipd Registration Charge");
		   appointment.setQuantity(1);
		   int res=completeAptmDAO.saveInvoiceAssesment(appointment, invoiceid);
		
	} catch (Exception e) {

		e.printStackTrace();
	}
	
}

private void saveChargesMLC(String amount,Bed bed,Client client,int ipdid,CompleteAptmDAO completeAptmDAO,String admissiondate)throws Exception {
	
	try {
		
		int invoiceid=0;
		
		
		/*String date1 = DateTimeUtils.getDateinSimpleFormate(new Date());
		String stemp[] = date1.split(" ");
		
		String temp[] = stemp[0].split("-");
		date1 = temp[2] + "-" + temp[1] + "-" + temp[0];*/
		
		String date1 = DateTimeUtils.getCommencingDate1(admissiondate);
		
			CompleteAppointment appointment=new CompleteAppointment();
			appointment.setClientId(bed.getClientid());
			appointment.setPractitionerId(bed.getPractitionerid());
			appointment.setChargeType("Charge");
			appointment.setLocation("1");
		    appointment.setAdditionalcharge_id("0");
		    appointment.setIpdid(ipdid);
		    appointment.setInvoiceDate(date1);
		    appointment.setIpd("1");
		    appointment.setAppointmentid("0");
		    appointment.setWardid(bed.getWardid());
		    if(client.getWhopay()!=null){
		    	
		    	if(client.getWhopay().equals("Self") || client.getWhopay().equals("Client")){
		    	       
		    		appointment.setPolicyExcess("0");
		    		appointment.setPayBuy("0");
		    	} else {
		    		appointment.setPolicyExcess("1");
		    		appointment.setPayBuy("1");
		    	}
		    }
		    Connection connection= Connection_provider.getconnection();
		    String mlcdr=bed.getMlcrefdoctor();
		    UserProfileDAO userProfileDAO= new JDBCUserProfileDAO(connection);
		    mlcdr= userProfileDAO.getReferalDrName(bed.getMlcrefdoctor()); 
		    if(mlcdr==null){
		    	mlcdr="";
		    } 
		    if(!mlcdr.equals("")){
		    	 appointment.setApmtType(" MLC Charge -"+mlcdr);
		    }else{
		    	 appointment.setApmtType(" MLC Charge");
		    }
		    invoiceid=completeAptmDAO.saveAmpmInvoice(appointment,loginInfo.getId(),loginInfo.getUserId());
		     
		    String fullname=client.getTitle()+" "+client.getFirstName()+" "+client.getLastName();
		    appointment.setUser(fullname);
		    appointment.setCommencing(date1);     
		    	  
		  
		   appointment.setCharges(amount);
		   appointment.setAdditionalcharge_id("0");
		   appointment.setMasterchargetype("MLC Charge");
		   appointment.setQuantity(1);
		   int res=completeAptmDAO.saveInvoiceAssesment(appointment, invoiceid);
		
	} catch (Exception e) {

		e.printStackTrace();
	}
}

public String update()throws Exception{
	
	if(!verifyLogin(request)) {
    		
    		return "login";
    	}
    	
    	Connection connection=null;
    	
    	try {
	
    		/*connection=Connection_provider.getconnection();*/
    		
    		request.setCharacterEncoding("UTF-8");
			response.setCharacterEncoding("UTF-8");
			connection = DriverManager.getConnection(
					"" + Constants.DB_HOST + ":3306/" + loginInfo.getClinicUserid()
							+ "?useUnicode=true&characterEncoding=UTF-8",
					"" + Constants.DB_USER + "", "" + Constants.DB_PWD + "");

    		
    		BedDao bedDao=new JDBCBedDao(connection);
    		UserProfileDAO profileDAO = new JDBCUserProfileDAO(connection);
    		ClientDAO clientDAO=new JDBCClientDAO(connection);
    		CompleteAptmDAO completeAptmDAO=new JDBCCompleteAptmDAO(connection);
    		AppointmentTypeDAO appointmentTypeDAO = new JDBCAppointmentTypeDAO(connection);
    		ClinicDAO clinicDAO = new JDBCClinicDAO(connection);
    		IpdDAO ipdDAO = new JDBCIpdDAO(connection);
    		
    		Bed bed=new Bed();
    		
    		int practitionerid=0;
    		
    		String payee=ipdForm.getPayee();
            if(payee.equals("0")){
            	payee="Client";
            } else {
            	payee="Third Party";
            }
            String tpid=ipdForm.getTpid();
            int r=clientDAO.updatePayeeofPatient(ipdForm.getClientid(),payee,tpid);
    		
    		
            //update emergency contact details
            Client clientcont=new Client();
            clientcont.setEmergencyContName(ipdForm.getRelativename());
            clientcont.setEmergencyContNo(ipdForm.getRelationcont());
            clientcont.setRelation(ipdForm.getRelation());
            clientcont.setId(Integer.parseInt(ipdForm.getClientid()));
            clientcont.setReference(ipdForm.getRefferedby());
            
            r=clientDAO.updateEmergencyDetails(clientcont);
    		
    		bed.setClientid(ipdForm.getClientid());
    		bed.setPractitionerid(ipdForm.getPractitionerid());
    		bed.setConditionid(ipdForm.getConditionid());
    		
    		 if(ipdForm.getPractitionerid()!=null){
  		       if(!ipdForm.getPractitionerid().equals("")){
  		         
  		            practitionerid=Integer.parseInt(ipdForm.getPractitionerid());
  		           UserProfile userProfile=profileDAO.getUserprofileDetails(practitionerid);
  		           bed.setSpeciality(userProfile.getDiciplineName());
  		           ipdForm.setDepartment(userProfile.getSpecialization());
  		       }
  		 }
    		
    		bed.setDepartment(ipdForm.getDepartment());
    		String id= ipdForm.getRefferedby();
    		String name= clientDAO.getReferenceName(id);
    		bed.setRefferedby(name);
    		bed.setReferenceid(id);
    		
    		
    		bed.setWardid(ipdForm.getWardid());
    		
    		
    		bed.setTpid(ipdForm.getTpid());
    		bed.setStatus(ipdForm.getStatus());
    		
    		bed.setAddmissionfor(new String(DateTimeUtils.isNull(ipdForm.getAddmissionfor()).getBytes("iso-8859-1"), "UTF-8"));
    		bed.setReimbursment(ipdForm.getReimbursment());
    		bed.setSecndryconsult(ipdForm.getSecndryconsult());
    		bed.setMlcno(ipdForm.getMlcno());
    		bed.setAdmissiondetails(ipdForm.getAdmissiondetails());
    		bed.setRefdocotor(ipdForm.getRefdocotor());
    		bed.setReferenceid(ipdForm.getRefdocotor());//for ayushman refered doc multiple select
    		String referedby=request.getParameter("referedby");
    		referedby=referedby.replace("<br>", ",");
    		bed.setRefferedby(referedby);
    		bed.setSuggestedtrtment(new String(DateTimeUtils.isNull(ipdForm.getSuggestedtrtment()).getBytes("iso-8859-1"), "UTF-8"));
    		bed.setHosp(ipdForm.getHosp());
    		bed.setPackagename(ipdForm.getPackagename());
    		//chiefcomplains, presentillness, pasthistory, personalhist, familyhist, onexamination, treatmentepisodeid
    		bed.setChiefcomplains(new String(DateTimeUtils.isNull(ipdForm.getChiefcomplains()).getBytes("iso-8859-1"), "UTF-8"));
    		bed.setPresentillness(new String(DateTimeUtils.isNull(ipdForm.getPresentillness()).getBytes("iso-8859-1"), "UTF-8"));
    		bed.setPasthistory(new String(DateTimeUtils.isNull(ipdForm.getPasthistory()).getBytes("iso-8859-1"), "UTF-8"));
    		bed.setPersonalhist(new String(DateTimeUtils.isNull(ipdForm.getPersonalhist()).getBytes("iso-8859-1"), "UTF-8"));
    		bed.setFamilyhist(new String(DateTimeUtils.isNull(ipdForm.getFamilyhist()).getBytes("iso-8859-1"), "UTF-8"));
    		bed.setOnexamination(new String(DateTimeUtils.isNull(ipdForm.getOnexamination()).getBytes("iso-8859-1"), "UTF-8"));
    		bed.setOndiet(new String(DateTimeUtils.isNull(ipdForm.getOndiet()).getBytes("iso-8859-1"), "UTF-8"));
    		bed.setPhysio(new String(DateTimeUtils.isNull(ipdForm.getPhysio()).getBytes("iso-8859-1"), "UTF-8"));

    		bed.setTreatmentepisodeid(ipdForm.getTreatmentepisodeid());
    		
    		
    		bed.setSuggestoper(ipdForm.getSuggestoper());
    		bed.setSystdepartment(bed.getSystdepartment());
    		bed.setFpcondition(ipdForm.getFpcondition());
    		bed.setFpnotest(ipdForm.getFpnotest());
    		bed.setNauseacondition(ipdForm.getNauseacondition());
    		bed.setNauseanotes(ipdForm.getNauseanotes());
    		bed.setFnucondition(ipdForm.getFnucondition());
    		bed.setFnunotes(ipdForm.getFnunotes());
    		bed.setSmcondition(ipdForm.getSmcondition());
    		bed.setSmnotes(ipdForm.getSmnotes());
    		bed.setChestpaincond(ipdForm.getChestpaincond());
    		bed.setChestpainnotes(ipdForm.getChestpainnotes());
    		bed.setDimvisioncond(ipdForm.getDimvisioncond());
    		bed.setDimvisionnotes(ipdForm.getDimvisionnotes());
    		
    		//dimvisionnotes, hgucondition, hgunotes, hmcondition, hmnotes, jointpaincond, jointpainnotes, 
    		bed.setHgucondition(ipdForm.getHgucondition());
    		bed.setHgunotes(ipdForm.getHgunotes());
    		bed.setHmcondition(ipdForm.getHmcondition());
    		bed.setHmnotes(ipdForm.getHmnotes());
    		bed.setJointpaincond(ipdForm.getJointpaincond());
    		bed.setJointpainnotes(ipdForm.getJointpainnotes());
    		
    		//constipationcond, constpationnotes, specialnotes, edemafeetcondi, edemafeetnotes, hematuriacondi, 
    		bed.setConstipationcond(ipdForm.getConstipationcond());
    		bed.setConstpationnotes(ipdForm.getConstpationnotes());
    		bed.setSpecialnotes(ipdForm.getSpecialnotes());
    		bed.setEdemafeetcondi(ipdForm.getEdemafeetcondi());
    		bed.setEdemafeetnotes(ipdForm.getEdemafeetnotes());
    		bed.setHematuriacondi(ipdForm.getHematuriacondi());
    		bed.setHematurianotes(ipdForm.getHematurianotes());
    		
    		//hematurianotes, bmcondition, bmnotes, oliguriacondi, oligurianotes, pstgucondi, pstgunotes, 
    		bed.setBmcondition(ipdForm.getBmcondition());
    		bed.setBmnotes(ipdForm.getBmnotes());
    		bed.setOliguriacondi(ipdForm.getOliguriacondi());
    		bed.setOligurianotes(ipdForm.getOligurianotes());
    		bed.setPstgucondi(ipdForm.getPstgucondi());
    		bed.setPstgunotes(ipdForm.getPstgunotes());
    		
    		//bmecondition, bmenotes, tnecondition, tnenotes, weaknesscondi, weaknessnotes, ihcondition, ihnotes
    		bed.setBmecondition(ipdForm.getBmecondition());
    		bed.setBmenotes(ipdForm.getBmenotes());
    		bed.setTnecondition(ipdForm.getTnecondition());
    		bed.setTnenotes(ipdForm.getTnenotes());
    		bed.setWeaknesscondi(ipdForm.getWeaknesscondi());
    		bed.setWeaknessnotes(ipdForm.getWeaknessnotes());
    		bed.setIhcondition(ipdForm.getIhcondition());
    		bed.setIhnotes(ipdForm.getIhnotes());
    		
    		bed.setEarlierinvest(new String(DateTimeUtils.isNull(ipdForm.getEarlierinvest()).getBytes("iso-8859-1"), "UTF-8"));
    		bed.setAdmission_reason(new String(DateTimeUtils.isNull(ipdForm.getAdmission_reason()).getBytes("iso-8859-1"), "UTF-8"));
       	    bed.setAlergies(new String(DateTimeUtils.isNull(ipdForm.getAlergies()).getBytes("iso-8859-1"), "UTF-8"));
    		
       	    //peditric 
       	  bed.setBirthhist(new String(DateTimeUtils.isNull(ipdForm.getBirthhist()).getBytes("iso-8859-1"), "UTF-8"));
       	
       	  bed.setDevelopmenthist(new String(DateTimeUtils.isNull(ipdForm.getDevelopmenthist()).getBytes("iso-8859-1"), "UTF-8"));
       	  bed.setEmmunizationhist(new String(DateTimeUtils.isNull(ipdForm.getEmmunizationhist()).getBytes("iso-8859-1"), "UTF-8"));
          
          bed.setDiethist(new String(DateTimeUtils.isNull(ipdForm.getDiethist()).getBytes("iso-8859-1"), "UTF-8"));
       	  bed.setMidarmcircumference(ipdForm.getMidarmcircumference());  
       	  bed.setHeadcircumference(ipdForm.getHeadcircumference());
       	  bed.setLength(ipdForm.getLength());
       	  bed.setWtaddmission(ipdForm.getWtaddmission());
       	  bed.setWtdischarge(ipdForm.getWtdischarge());
       	  bed.setGstureage(ipdForm.getGstureage());
       	  bed.setWtonbirth(ipdForm.getWtonbirth());
       	  
       	//gynic details
    		bed.setAlcohal(ipdForm.getAlcohal());
    		bed.setDrugs(ipdForm.getDrugs());
    		bed.setOther_medication(ipdForm.getOther_medication());
    		bed.setTobaco(ipdForm.getTobaco());
    		bed.setTobaconotes(ipdForm.getTobaconotes());
    		bed.setSmoking(ipdForm.getSmoking());
    		
    		bed.setAge_menarche(ipdForm.getAge_menarche());
    		bed.setLmp(ipdForm.getLmp());
    		bed.setLlmp(ipdForm.getLlmp());
    		bed.setPmc(ipdForm.getPmc());
    		
    		
    		bed.setCycle_length(ipdForm.getCycle_length());
    		bed.setDuration_flow(ipdForm.getDuration_flow());
    		bed.setAmount_flow(ipdForm.getAmount_flow());
    		bed.setDysmenorrhea(ipdForm.getDysmenorrhea());
    		bed.setDyspareunia(ipdForm.getDyspareunia());
    		bed.setHopassing_clots(ipdForm.getHopassing_clots());
    		
    		bed.setWhite_disc_itching(ipdForm.getWhite_disc_itching());
    		bed.setIntercourse_freq(ipdForm.getIntercourse_freq());
    		bed.setGalactorrea(ipdForm.getGalactorrea());
    		
    		bed.setHo_contraception(ipdForm.getHo_contraception());
    		bed.setRubella_vaccine(ipdForm.getRubella_vaccine());
    		bed.setMenopause(ipdForm.getMenopause());
    		bed.setNulligravida(ipdForm.getNulligravida());
    		bed.setCurrent_pregnent(ipdForm.getCurrent_pregnent());
    		bed.setIud(ipdForm.getIud());
    		
    		bed.setLive_boys(ipdForm.getLive_boys());
    		bed.setLive_girls(ipdForm.getLive_girls());
    		bed.setStillbirths(ipdForm.getStillbirths());
    		bed.setAbortions(ipdForm.getAbortions());
    		bed.setDeath_children(ipdForm.getDeath_children());
    		bed.setMlcrefdoctor(ipdForm.getMlcrefdoctor());
    		
    		bed.setSurgicalnotes(new String(DateTimeUtils.isNull(ipdForm.getSurgicalnotes()).getBytes("iso-8859-1"), "UTF-8"));
    		
    		
    		bed.setMaternal_history(new String(DateTimeUtils.isNull(ipdForm.getMaternal_history()).getBytes("iso-8859-1"), "UTF-8"));
    		bed.setPerinatal_history(new String(DateTimeUtils.isNull(ipdForm.getPerinatal_history()).getBytes("iso-8859-1"), "UTF-8"));
    		
    		bed.setMlccase(ipdForm.getMlccase());
    		 bed.setMlcabrivationid(ipdForm.getMlcabrivationid());
            //save gynic obs history
            if(ipdForm.getObslist()!=null){
            	 for(Bed bed2 :ipdForm.getObslist()){
                	 
            		 if(bed2.getId()==0){
            			 int res=bedDao.saveGynicObsData(ipdForm.getId(),bed2);
            		 } else {
            			 int res=bedDao.updateGynicObsData(bed2);
            		 }
            	     
              }
            }
    		
    		
    		//parity_aboration_notes,p,l,a,d	
    		bed.setParity_abortion_notes(ipdForm.getParity_abortion_notes());
    		bed.setP(ipdForm.getP());
    		bed.setL(ipdForm.getL());
    		bed.setA(ipdForm.getA());
    		bed.setD(ipdForm.getD());
    		
    		//  27 July 2018
    		bed.setGiddinesscondition(ipdForm.getGiddinesscondition());
    		bed.setGiddinessnotes(ipdForm.getGiddinessnotes());
    		bed.setUnconcondition(ipdForm.getUnconcondition());
    		bed.setUnconnotes(ipdForm.getUnconnotes());
    		String da=ipdForm.getAdmissiondate();
    		String addmissiondate = DateTimeUtils.getCommencingDate1(ipdForm.getAdmissiondate()) + " " + ipdForm.getHour() + ":" + ipdForm.getMinute() + ":20" ;
    		bed.setAdmissiondate(addmissiondate);
    		
    		String date = DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone());
    		
    		//log f
    		Bed bedchange = bedDao.getEditIpdData(Integer.toString(ipdForm.getId()));
    		
    		String templ[] = bedchange.getAdmissiondate().split(" ");
			String ldate = DateTimeUtils.getCommencingDate1(templ[0]);
			String time[] = templ[1].split(":");
			String hh = time[0];
			String mm = time[1];
			
			String loglastmodified = DateTimeUtils.getCommencingDate1(ipdForm.getAdmissiondate()) + " " + hh + ":" + mm + ":20" ;
			  String cutofftime = bedDao.getHospitalCutoffTime(loginInfo.getClinicid());
	            if(!cutofftime.equals("0")){
	            	loglastmodified = DateTimeUtils.getCommencingDate1(ipdForm.getAdmissiondate()) + " " + cutofftime + ":20" ;
	            }
    		int log = 0;
    		bed.setBedid(ipdForm.getBedid());
    		String logcommencing = DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone()).split(" ")[0];
    		boolean bedchangestatus=false;
    		if(!bedchange.getWardid().equals(ipdForm.getWardid()) && !bedchange.getBedid().equals(ipdForm.getBedid())){
    			log = bedDao.saveBedChangeLog(bed, loglastmodified, ipdForm.getId(),logcommencing,date,0);
    			session.setAttribute("ipddash_forreload", "1");
    			bedchangestatus = true;
    		}
    		else if(!bedchange.getWardid().equals(ipdForm.getWardid())){
    			log = bedDao.saveBedChangeLog(bed, loglastmodified, ipdForm.getId(),logcommencing,date,0);
    			session.setAttribute("ipddash_forreload", "1");
    			bedchangestatus = true;
    		}
    		else if(!bedchange.getBedid().equals(ipdForm.getBedid())){
    			log = bedDao.saveBedChangeLog(bed, loglastmodified, ipdForm.getId(),logcommencing,date,0);
    			session.setAttribute("ipddash_forreload", "1");
    			bedchangestatus = true;
    		}
    		/*if(bedchangestatus){
    			ipdDAO.createAdmissionView();
    			ipdDAO.createDischargeView();
    		}*/
    		
    		String admissiondate = DateTimeUtils.getCommencingDate1(ipdForm.getAdmissiondate()) + " " + ipdForm.getHour() + ":" + ipdForm.getMinute() + ":20" ;
    		bed.setAdmissiondate(admissiondate);
    		
    		boolean flag=bedDao.getIsWardChange(ipdForm.getId(),ipdForm.getWardid());
    		Bed bed2=new Bed();
    		bed2=bedDao.getRecentWardid(ipdForm.getId(),ipdForm.getWardid());
    		
    		if(ipdForm.getDisbedid().equals("0")){
    			bed.setBedid("0");
    		} else {

    			bed.setBedid(ipdForm.getBedid());
    		}
    		
//Lokesh code to get The current treatmentEpisodseid;    	
    		Bed bed4= bedDao.getEditIpdData(""+ipdForm.getId());
    		bed.setTreatmentepisodeid(bed4.getTreatmentepisodeid());
    		int update = bedDao.updateIpdDetails(bed,ipdForm.getId(),date);
    		bed4.setFakebackdate(ipdForm.getFromDate());
    		bed4.setIpdid(""+ipdForm.getId());
    		bed4.setPhysio(bed.getPhysio());
    		//save ipd physio data for bams
    		int result=bedDao.saveIpdPhsioDetails(bed4);
    		/*update = bedDao.updateAdmissionDate(ipdForm.getId(),bed.getAdmissiondate());*/
    		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			Calendar cal = Calendar.getInstance();
 			String today = dateFormat.format(cal.getTime()); 
 			boolean datestatus=today.equals(bed2.getCommencing());
    		
 			//  22-06-2019 new code of auto charge
 			if(flag){
 				Client client=clientDAO.getClientDetails(bed.getClientid()); 
 	        	int res=bedDao.saveShiftBedLog(admissiondate,ipdForm.getId(),bedchange.getWardid(),ipdForm.getWardid());
 	        	
 	        	int wardids = completeAptmDAO.getMaxWardChargeID(ipdForm.getId(),today);
 	        	String wardid = ""+wardids;
 	        	if(wardid.equals("0")){
 	        		wardid = ipdForm.getWardid();
 	        	}
 	        	ArrayList<Master> chargeList=appointmentTypeDAO.getStandardChargeList(""+wardid, tpid, payee,loginInfo);
 	        	String stdcharges="0";
        		for(Master master:chargeList){
        			stdcharges=stdcharges+","+master.getId();
        		}
    			result=completeAptmDAO.updateWardCharges(ipdForm.getId(),stdcharges);
    			int invoiceid=0;
    			String date1 = DateTimeUtils.getCommencingDate1(ipdForm.getAdmissiondate());
    			if(chargeList.size()!=0){
    				CompleteAppointment appointment=new CompleteAppointment();
        			appointment.setClientId(bed.getClientid());
        			appointment.setPractitionerId(bed.getPractitionerid());
        			appointment.setChargeType("Charge");
        			appointment.setLocation("1");
        		    appointment.setAdditionalcharge_id(stdcharges);
        		    appointment.setIpdid(ipdForm.getId());
        		    appointment.setInvoiceDate(date1);
        		    appointment.setAppointmentid("0");
        		    appointment.setIpd("1");
        		    appointment.setWardid(""+wardid);
        		    if(client.getWhopay()!=null){
        		    	if(client.getWhopay().equals("Self") || client.getWhopay().equals("Client")){
        		    		appointment.setPolicyExcess("0");
        		    		appointment.setPayBuy("0");
        		    	} else {
        		    		appointment.setPolicyExcess("1");
        		    		appointment.setPayBuy("1");
        		    	}
        		    }
        		    String fullname=client.getTitle()+" "+client.getFirstName()+" "+client.getLastName();
        		    appointment.setUser(fullname);
        		    appointment.setInvoiceDate(today);
         			appointment.setCommencing(today); 
          		    invoiceid=completeAptmDAO.saveStndCharge(appointment.getClientId(), String.valueOf(ipdForm.getId()), stdcharges);
          		    invoiceid=completeAptmDAO.saveAmpmInvoice(appointment,loginInfo.getId(),loginInfo.getUserId());
          		    int del = appointmentTypeDAO.deletetodayBedShiftingcharge(ipdForm.getId(),today,loginInfo);
       				appointment.setInvoiceid(String.valueOf(invoiceid));
          		    for(Master master:chargeList){
	      		       appointment.setCommencing(today); 
      		    	   appointment.setApmtType(master.getName());
      		    	   appointment.setCharges(master.getCharge());
      		    	   appointment.setAdditionalcharge_id(String.valueOf(master.getId()));
      		    	   //appointment.setMasterchargetype("Bed Charge");
      		    	   //  30-11-2019 kunal auto charges not applied
    		    	   if(loginInfo.getIskunal()!=1){
    		    		   appointment.setMasterchargetype("Bed Charge");
    				   }else{
    					   appointment.setMasterchargetype("Bed Charges");
    				   }
      		    	   appointment.setQuantity(1);
      		    	   appointment.setStdflag("1");
      		    	   res=completeAptmDAO.saveInvoiceAssesment(appointment, invoiceid);
      		    	}
      		    	
	            }
    			int autosetchargelogid = appointmentTypeDAO.getautosetchargelogid(ipdForm.getId(),""+wardid,ipdForm.getBedid());
      		    int uw = appointmentTypeDAO.updateAutosetWardID(autosetchargelogid,""+wardid,ipdForm.getBedid());

      		    
      		  
      		    //Lokesh For OnOff charges in auto charge
      	 		if(loginInfo.isBalgopal()){
      	 			ipdDAO.startOnOffChargesWardChangingProcess(""+ipdForm.getId(), ipdForm.getWardid(), loginInfo);
      	 		}
      	 			
      	 		
      	 		
      	 			

      		    
 			}
 			
 			//update auto charges..
//    		if(datestatus==true && flag==true){
////    		if(flag){
//    		
//    			Client client=clientDAO.getClientDetails(bed.getClientid()); 
//        	
//    			int res=bedDao.saveShiftBedLog(admissiondate,ipdForm.getId(),bedchange.getWardid(),ipdForm.getWardid());
//    			
//        	//	ArrayList<Master> chargeList=completeAptmDAO.getStandardCharges(ipdForm.getWardid(), client.getWhopay());
//    			ArrayList<Master> chargeList=appointmentTypeDAO.getStandardChargeList(ipdForm.getWardid(), tpid, payee,loginInfo);
//        		String stdcharges="0";
//        		for(Master master:chargeList){
//        			
//        			stdcharges=stdcharges+","+master.getId();
//        		}
//    			int result=completeAptmDAO.updateWardCharges(ipdForm.getId(),stdcharges);
//    			
//    			int invoiceid=0;
//        		
//        		
//    			/*String date1 = DateTimeUtils.getDateinSimpleFormate(new Date());
//    			String stemp[] = date1.split(" ");
//    			
//    			String temp[] = stemp[0].split("-");
//    			date1 = temp[2] + "-" + temp[1] + "-" + temp[0];*/
//    			
//    			String date1 = DateTimeUtils.getCommencingDate1(ipdForm.getAdmissiondate());
//    			
//        		
//        		if(chargeList.size()!=0){ 
//        			
//        			CompleteAppointment appointment=new CompleteAppointment();
//        			appointment.setClientId(bed.getClientid());
//        			appointment.setPractitionerId(bed.getPractitionerid());
//        			appointment.setChargeType("Charge");
//        			appointment.setLocation("1");
//        		    appointment.setAdditionalcharge_id(stdcharges);
//        		    appointment.setIpdid(ipdForm.getId());
//        		    appointment.setInvoiceDate(date1);
//        		    appointment.setAppointmentid("0");
//        		    appointment.setIpd("1");
//        		    appointment.setWardid(ipdForm.getWardid());
//        		    
//        		    if(client.getWhopay()!=null){
//        		    	
//        		    	if(client.getWhopay().equals("Self") || client.getWhopay().equals("Client")){
//        		    	       
//        		    		appointment.setPolicyExcess("0");
//        		    		appointment.setPayBuy("0");
//        		    	} else {
//        		    		appointment.setPolicyExcess("1");
//        		    		appointment.setPayBuy("1");
//        		    	}
//        		    }
//        		    
//        		    invoiceid=completeAptmDAO.saveStndCharge(appointment.getClientId(), String.valueOf(ipdForm.getId()), stdcharges);
//        		    
//        		   // invoiceid=completeAptmDAO.saveAmpmInvoice(appointment,loginInfo.getId());
//        		    
//        		    String fullname=client.getTitle()+" "+client.getFirstName()+" "+client.getLastName();
//        		    appointment.setUser(fullname);
//        		      
//        		    
//        		    //getting shifting qty
//        		  /*  String curshiftingdate = DateTimeUtils.getCommencingDate1(ipdForm.getAdmissiondate());
//        		    String edate = ipdForm.getAdmissiondate() + " " + ipdForm.getHour() + ":" + ipdForm.getMinute() + ":00";
//        		    String lastshiftingdate = appointmentTypeDAO.getLastShiftingDate(ipdForm.getId());
//        		    String lastshiftedlogid = appointmentTypeDAO.getLastShiftedLogid(ipdForm.getId());
//        		    
//        		    ArrayList<Master>lastShiftedChargeList = appointmentTypeDAO.getLastShiftedChargeList(lastshiftedlogid);
//        		    for(Master m : lastShiftedChargeList){
//        		    	
//        		    	 int  qty = DateTimeUtils.getDifferanceofDateWithTime(lastshiftingdate, edate, m.getChargehours());
//       		    	   if(qty==0){
//       		    		   qty = 1;
//       		    	   }
//       		    	   
//        		    	int assesmentid = m.getId();
//     		    	   int upd = appointmentTypeDAO.updateLasteShiftedChargeQty(lastshiftingdate,curshiftingdate,qty,ipdForm.getId(),m.getId(),assesmentid);
//        		    }
//        		    */
//        		    
//        		    
//        		/*	int qty = 0;
//        			appointment.setCommencing(curshiftingdate); 
//        		    
//        		    for(Master master:chargeList){
//        		    	  
//        		    	  appointment.setCommencing(curshiftingdate); 
//        		    	   appointment.setApmtType(master.getName());
//        		    	   appointment.setCharges(master.getCharge());
//        		    	   appointment.setAdditionalcharge_id(String.valueOf(master.getId()));
//        		    	   appointment.setMasterchargetype("Bed Charge");
//        		    	   
//        		    	    qty = DateTimeUtils.getDifferanceofDateWithTime(lastshiftingdate, edate, master.getChargehours());
//        		    	   if(qty==0){
//        		    		   qty = 1;
//        		    	   }
//        		    	   appointment.setQuantity(qty);
//        		    	   res=completeAptmDAO.saveInvoiceAssesment(appointment, invoiceid);
//        		    	   
//        		    }*/
//        		    
//        		  //reset previous in process charges
//        		    
//        		/*  int  upd = appointmentTypeDAO.resetAllInprocessCharge(ipdForm.getId());
//        			upd = appointmentTypeDAO.setInprocessforNewShiftCharges(invoiceid,log);
//        			
//        			
//        			String curdate = DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone());
//        			String tempa[] = curdate.split(" ");
//        			String dateStop = DateTimeUtils.getCommencingDate1(tempa[0]) + " " + tempa[1];
//        			
//        			ArrayList<String>chargeIdList = appointmentTypeDAO.getChargeIdList(invoiceid);
//        			for(String str : chargeIdList){
//        				int chargehour = appointmentTypeDAO.getChargeHour(str);
//        				qty = DateTimeUtils.getDifferanceofDateWithTime(edate, dateStop, chargehour);
//        				 if(qty==0){
//      		    		   qty = 1;
//      		    	   }
//        				 upd = appointmentTypeDAO.updateInprocessQty(ipdForm.getId(),qty,str);
//        			}*/
//        		    
//        		    
//        		    //bed shifting charge once i a day
//        		    /*String logidList = appointmentTypeDAO.getLogIDList(ipdForm.getId());
//        		    String apminvoiceidlist = appointmentTypeDAO.getapminvoiceidlist(logidList);*/
//        		    int del = appointmentTypeDAO.deleteallBedShiftingcharge(ipdForm.getId());
//        		    ArrayList<Master>logcommencingList = appointmentTypeDAO.getLogCommencingList(ipdForm.getId());
//        		    
//        		    //set quantity
//        		    String sdate = appointmentTypeDAO.getIpdAdmissionDate(ipdForm.getId());
//        		    String edate = DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone());
//
//        			
//        		    //applyShiftingCharges(logcommencingList,edate,connection,tpid,payee,appointment);
//        			
//        			
//        		    
//        		    //update auto set charge wardid and bedid
//        		    int autosetchargelogid = appointmentTypeDAO.getautosetchargelogid(ipdForm.getId(),ipdForm.getWardid(),ipdForm.getBedid());
//        		    int uw = appointmentTypeDAO.updateAutosetWardID(autosetchargelogid,ipdForm.getWardid(),ipdForm.getBedid());
//        		    
//        			 ArrayList<Master>sepetatrLogcommencingList = appointmentTypeDAO.getsepetatrLogcommencingList(ipdForm.getId());
//        			 applyShiftingCharges(sepetatrLogcommencingList,edate,connection,tpid,payee,appointment);
//        			 
//        			 
//        			//apply charges for individual date shifting
//         		   
//        			 
//         		  
//        			 BedDao bedDao1=new JDBCBedDao(connection);
//        				Bed b = bedDao1.getEditIpdData(Integer.toString(ipdForm.getId()));
//        				
//        				Master master = appointmentTypeDAO.getLastIpdLogData(ipdForm.getId());
//              		    String curshifydate = DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone()).split(" ")[0];
//        			/* if(!master.getDate().equals(curshifydate)){
//        				 sdate = master.getLastmodified();
//        				String stemp[] = sdate.split(" ");
//        				sdate = DateTimeUtils.getCommencingDate1(stemp[0]) + " " + stemp[1];
//        				
//        				 invoiceid=completeAptmDAO.saveAmpmInvoice(appointment,loginInfo.getId());
//        				 
//        				 String temp[] = edate.split(" ");
//        					edate = DateTimeUtils.getCommencingDate1(temp[0]) + " " + temp[1];
//        				
//        				int qty1 =(int)DateTimeUtils.getDifferanceofDateWithTime(sdate, edate, master.getChargehours());
//        				String wardidList = appointmentTypeDAO.getBedShiftigWardIdLIst(ipdForm.getId(),master.getDate());
//        				 chargeList=appointmentTypeDAO.getBedShiftingStandardChargeList(wardidList, tpid, payee);
//        				 for(Master m1:chargeList){
//        			    	   bed = appointmentTypeDAO.getLogwardId(master.getDate(),ipdForm.getId());
//        			    	  appointment.setCommencing(master.getDate()); 
//        			    	   appointment.setApmtType(m1.getName());
//        			    	   appointment.setCharges(m1.getCharge());
//        			    	   appointment.setAdditionalcharge_id(String.valueOf(m1.getId()));
//        			    	   appointment.setMasterchargetype("Bed Charge");
//        			    	   appointment.setWardid(bed.getWardid());
//        			    	   appointment.setBedid(bed.getBedid());
//        			    	   appointment.setIpdid(ipdForm.getId());
//        			    	   appointment.setLogid(bed.getLogid());
//        			    	   
//        			    	 
//        			    	
//        		   	   if(qty1==0){
//        		   		   qty1++;
//        		   	   }
//
//        			    	   
//        		   	   		appointment.setQuantity(qty1);
//
//        			    	    res=completeAptmDAO.saveInvoiceAssesment(appointment, invoiceid);
//        			    	   
//        			    }
//         		    }*/
//        			 
//        			
//        			/*int  upd = appointmentTypeDAO.resetAllInprocessCharge(ipdForm.getId());
//        	           upd = appointmentTypeDAO.setInprocessforNewShiftCharges(invoiceid,log);*/
//        			
//        		    //ruchi code for send msg for bed change to patient relative or patient and other one for practioner on patient admit or update
//        			//sms akash 23 jan 2018 comment
//              		    //  20/12/2018 sms on bed change and new admission access based
//              		 
//              		    if(ipdForm.getPractitionerid()!=null){
//             		       if(!ipdForm.getPractitionerid().equals("")){
//             		         
//             		            practitionerid=Integer.parseInt(ipdForm.getPractitionerid());
//             		          
//             		       }
//              		  }
//              		  connection=Connection_provider.getconnection();
//              		    UserProfileDAO userProfileDAO = new JDBCUserProfileDAO(connection);
//              		  UserProfile userProfile  = userProfileDAO.getUserprofileDetails(practitionerid);
//              		
//              		    if(loginInfo.isSms_on_bedchange()==true){
//              		    	
//              		    
//        			autosmsBedChange(bed.getClientid(),ipdForm.getWardid(),ipdForm.getBedid(),userProfile.getMobile());
//              		    }
////              		    if(loginInfo.isSms_on_newadm()==true){
////        	           autosmsPrimaryDoctorChange(bed.getClientid(),ipdForm.getWardid(),ipdForm.getBedid(),practitionerid);
////              		    }
//              		    }
//  	
//    		}else{
//    		
//
//        		
//        			Client client=clientDAO.getClientDetails(bed.getClientid()); 
//            	
//        			int res=bedDao.saveShiftBedLog(admissiondate,ipdForm.getId(),bedchange.getWardid(),ipdForm.getWardid());
//        			
//            	//	ArrayList<Master> chargeList=completeAptmDAO.getStandardCharges(ipdForm.getWardid(), client.getWhopay());
//        			ArrayList<Master> chargeList=appointmentTypeDAO.getStandardChargeList(ipdForm.getWardid(), tpid, payee,loginInfo);
//            		String stdcharges="0";
//            		for(Master master:chargeList){
//            			
//            			stdcharges=stdcharges+","+master.getId();
//            		}
//        			int result=completeAptmDAO.updateWardCharges(ipdForm.getId(),stdcharges);
//        			
//        			int invoiceid=0;
//            		
//            		
//        			/*String date1 = DateTimeUtils.getDateinSimpleFormate(new Date());
//        			String stemp[] = date1.split(" ");
//        			
//        			String temp[] = stemp[0].split("-");
//        			date1 = temp[2] + "-" + temp[1] + "-" + temp[0];*/
//        			
//        			String date1 = DateTimeUtils.getCommencingDate1(ipdForm.getAdmissiondate());
//        			
//        			String stdchargesetup = clinicDAO.getStdChargeSetup(loginInfo.getClinicid());
//            		
//            		if(chargeList.size()!=0){ 
//            			
//            			CompleteAppointment appointment=new CompleteAppointment();
//            			appointment.setClientId(bed.getClientid());
//            			appointment.setPractitionerId(bed.getPractitionerid());
//            			appointment.setChargeType("Charge");
//            			appointment.setLocation("1");
//            		    appointment.setAdditionalcharge_id(stdcharges);
//            		    appointment.setIpdid(ipdForm.getId());
//            		    appointment.setInvoiceDate(date1);
//            		    appointment.setAppointmentid("0");
//            		    appointment.setIpd("1");
//            		    appointment.setWardid(ipdForm.getWardid());
//            		    
//            		    if(client.getWhopay()!=null){
//            		    	
//            		    	if(client.getWhopay().equals("Self") || client.getWhopay().equals("Client")){
//            		    	       
//            		    		appointment.setPolicyExcess("0");
//            		    		appointment.setPayBuy("0");
//            		    	} else {
//            		    		appointment.setPolicyExcess("1");
//            		    		appointment.setPayBuy("1");
//            		    	}
//            		    }
//            		    
////            		    invoiceid=completeAptmDAO.saveStndCharge(appointment.getClientId(), String.valueOf(ipdForm.getId()), stdcharges);
//            		    
//            		   // invoiceid=completeAptmDAO.saveAmpmInvoice(appointment,loginInfo.getId());
//            		    
//            		    String fullname=client.getTitle()+" "+client.getFirstName()+" "+client.getLastName();
//            		    appointment.setUser(fullname);
//            		      
//            		    
//            		    //getting shifting qty
//            		    String curshiftingdate = DateTimeUtils.getCommencingDate1(ipdForm.getAdmissiondate());
//            		    String edate = ipdForm.getAdmissiondate() + " " + ipdForm.getHour() + ":" + ipdForm.getMinute() + ":00";
//            		    String lastshiftingdate = appointmentTypeDAO.getLastShiftingDate(ipdForm.getId());
//            		    String lastshiftedlogid = appointmentTypeDAO.getLastShiftedLogid(ipdForm.getId());
//            		    
//            		    ArrayList<Master>lastShiftedChargeList = appointmentTypeDAO.getLastShiftedChargeList(lastshiftedlogid);
//            		    for(Master m : lastShiftedChargeList){
//            		    	
//            		    	 int  qty = DateTimeUtils.getDifferanceofDateWithTime(lastshiftingdate, edate, m.getChargehours());
//           		    	   if(qty==0){
//           		    		   qty = 1;
//           		    	   }
//           		    	   if(qty<0){
//         		    		   qty = 1;
//         		    	   }
//           		    	   
//            		    	int assesmentid = m.getId();
//         		    	   int upd = appointmentTypeDAO.updateLasteShiftedChargeQty(lastshiftingdate,curshiftingdate,qty,ipdForm.getId(),m.getId(),assesmentid);
//            		    }
//            		    
//            		    appointment.setInvoiceDate(today);
//           			  invoiceid=completeAptmDAO.saveAmpmInvoice(appointment,loginInfo.getId(),loginInfo.getUserId());
//           			 int del = appointmentTypeDAO.deletetodayBedShiftingcharge(ipdForm.getId(),today);
//            			int qty = 0;
//            			appointment.setCommencing(today); 
//            		    appointment.setInvoiceid(String.valueOf(invoiceid));
//            		    for(Master master:chargeList){
//
//            		    	appointment.setCommencing(today); 
//            		    	appointment.setApmtType(master.getName());
//            		    	appointment.setCharges(master.getCharge());
//            		    	appointment.setAdditionalcharge_id(String.valueOf(master.getId()));
//            		    	appointment.setMasterchargetype("Bed Charge");
//
//            		    	qty = DateTimeUtils.getDifferanceofDateWithTime(lastshiftingdate, edate, master.getChargehours());
//            		    	if(qty==0){
//            		    	qty = 1;
//            		    	}else if(qty<0){
//            		    	qty=1;
//            		    	}
//            		    	appointment.setQuantity(qty);
//            		    	res=completeAptmDAO.saveInvoiceAssesment(appointment, invoiceid);
//
//            		    }
//            		    /*String currentdate = DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone()).split(" ")[0];
//            		    for(Master master:chargeList){
//            		    	  
//            		    	  appointment.setCommencing(today); 
//            		    	   appointment.setApmtType(master.getName());
//            		    	   appointment.setCharges(master.getCharge());
//            		    	   appointment.setAdditionalcharge_id(String.valueOf(master.getId()));
//            		    	   appointment.setMasterchargetype("Bed Charge");
//            		    	   
//            		    	    qty = DateTimeUtils.getDifferanceofDateWithTime(lastshiftingdate, edate, master.getChargehours());
//            		    	    qty = Math.abs(qty);
//            		    	    int newqty = qty;
//            		    	
//            		    	    if(qty==0){
//            		    	    	qty++;
//            		    	    }
//            		    	   appointment.setQuantity(1);
//            		    	   appointment.setStdflag("1");
//            		    	   int res1 = 0;
//            		    	   if(stdchargesetup.equals("0")){
//            		    		   if(qty>0){
//            		    			   appointment.setCommencing(curshiftingdate);
//            		    			   for(int i =0; i<=newqty;i++){
//            		    				   SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
//            		    				   Date d=sdf1.parse(curshiftingdate);
//            		    				   Calendar cal1 = Calendar.getInstance();
//            		    				   cal1.setTime(d);
//            		    				   cal1.add(Calendar.DATE, i);
//            		    				   String dt=sdf1.format(cal1.getTime());
//            		    				   appointment.setCommencing(dt);  
//            		    				   
//            		    				   String checkingdate = dt;
//            		    				   String string =dt;
//	   				 			  			if(!cutofftime.equals("0")){
//	   				 			            	string = string + " " + cutofftime + ":20" ;
//	   				 			            }else{
//	   				 			            	string = string + " " +  DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone()).split(" ")[1];
//	   				 			            }
//	   				 			  			boolean yesflag=false;
//	   				 			            if(checkingdate.equals(currentdate)){
//	   				 			            	String currentdate1 = DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone());
//	   				 			            	if(!cutofftime.equals("0")){
//	   				 			            		int differ = currentdate1.compareTo(string);
//	   				 			            		if(differ<0){
//	   				 			            			yesflag= true;
//	   				 			            		}
//	   				 			            	}
//	   				 			            	
//	   				 			            }
//	   				 			            if(!yesflag){
//	   				 			        		boolean alreadysetautocharge = bedDao.checkAlreadyAutoChargeApplied(dt,bed.getClientid(),ipdForm.getId(),master.getName());
//	   				 			        		if(!alreadysetautocharge){
//	   				 			        			res=completeAptmDAO.saveInvoiceAssesment(appointment, invoiceid);
//	   				 			        		}
//	   				 			        	}
//            		    			   }
//            		    		   }
//            		    	   }
//            		    }*/
//            		    
//            		  //reset previous in process charges
//            		    
//            		  int  upd = appointmentTypeDAO.resetAllInprocessCharge(ipdForm.getId());
//            			upd = appointmentTypeDAO.setInprocessforNewShiftCharges(invoiceid,log);
//            			
//            			
//            			String curdate = DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone());
//            			String tempa[] = curdate.split(" ");
//            			String dateStop = DateTimeUtils.getCommencingDate1(tempa[0]) + " " + tempa[1];
//            			
////            			ArrayList<String>chargeIdList = appointmentTypeDAO.getChargeIdList(invoiceid);
////            			for(String str : chargeIdList){
////            				int chargehour = appointmentTypeDAO.getChargeHour(str);
////            				qty = DateTimeUtils.getDifferanceofDateWithTime(edate, dateStop, chargehour);
////            				 if(qty==0){
////          		    		   qty = 1;
////          		    	   }
////            				 upd = appointmentTypeDAO.updateInprocessQty(ipdForm.getId(),qty,str);
////            			}
//            		    
//            		    
//            		    //bed shifting charge once i a day
//            		    /*String logidList = appointmentTypeDAO.getLogIDList(ipdForm.getId());
//            		    String apminvoiceidlist = appointmentTypeDAO.getapminvoiceidlist(logidList);*/
////            		    int del = appointmentTypeDAO.deleteallBedShiftingcharge(ipdForm.getId());
////            		    ArrayList<Master>logcommencingList = appointmentTypeDAO.getLogCommencingList(ipdForm.getId());
////            		    
//            		    //set quantity
////            		    String sdate = appointmentTypeDAO.getIpdAdmissionDate(ipdForm.getId());
////            		    String edate = DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone());
//
//            			
////            		    applyShiftingCharges(logcommencingList,edate,connection,tpid,payee,appointment);
//            			
//            			
//            		    
//            		    //update auto set charge wardid and bedid
//            		    int autosetchargelogid = appointmentTypeDAO.getautosetchargelogid(ipdForm.getId(),ipdForm.getWardid(),ipdForm.getBedid());
//            		    int uw = appointmentTypeDAO.updateAutosetWardID(autosetchargelogid,ipdForm.getWardid(),ipdForm.getBedid());
//            		    
////            			 ArrayList<Master>sepetatrLogcommencingList = appointmentTypeDAO.getsepetatrLogcommencingList(ipdForm.getId());
////            			 applyShiftingCharges(sepetatrLogcommencingList,edate,connection,tpid,payee,appointment);
//            			 
//            			 
//            			//apply charges for individual date shifting
//             		   
//            			 
//             		  
//            			 BedDao bedDao1=new JDBCBedDao(connection);
//            				Bed b = bedDao1.getEditIpdData(Integer.toString(ipdForm.getId()));
//            				
//            				Master master = appointmentTypeDAO.getLastIpdLogData(ipdForm.getId());
//                  		    String curshifydate = DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone()).split(" ")[0];
//            			/* if(!master.getDate().equals(curshifydate)){
//            				 sdate = master.getLastmodified();
//            				String stemp[] = sdate.split(" ");
//            				sdate = DateTimeUtils.getCommencingDate1(stemp[0]) + " " + stemp[1];
//            				
//            				 invoiceid=completeAptmDAO.saveAmpmInvoice(appointment,loginInfo.getId());
//            				 
//            				 String temp[] = edate.split(" ");
//            					edate = DateTimeUtils.getCommencingDate1(temp[0]) + " " + temp[1];
//            				
//            				int qty1 =(int)DateTimeUtils.getDifferanceofDateWithTime(sdate, edate, master.getChargehours());
//            				String wardidList = appointmentTypeDAO.getBedShiftigWardIdLIst(ipdForm.getId(),master.getDate());
//            				 chargeList=appointmentTypeDAO.getBedShiftingStandardChargeList(wardidList, tpid, payee);
//            				 for(Master m1:chargeList){
//            			    	   bed = appointmentTypeDAO.getLogwardId(master.getDate(),ipdForm.getId());
//            			    	  appointment.setCommencing(master.getDate()); 
//            			    	   appointment.setApmtType(m1.getName());
//            			    	   appointment.setCharges(m1.getCharge());
//            			    	   appointment.setAdditionalcharge_id(String.valueOf(m1.getId()));
//            			    	   appointment.setMasterchargetype("Bed Charge");
//            			    	   appointment.setWardid(bed.getWardid());
//            			    	   appointment.setBedid(bed.getBedid());
//            			    	   appointment.setIpdid(ipdForm.getId());
//            			    	   appointment.setLogid(bed.getLogid());
//            			    	   
//            			    	 
//            			    	
//            		   	   if(qty1==0){
//            		   		   qty1++;
//            		   	   }
//
//            			    	   
//            		   	   		appointment.setQuantity(qty1);
//
//            			    	    res=completeAptmDAO.saveInvoiceAssesment(appointment, invoiceid);
//            			    	   
//            			    }
//             		    }*/
//            			 
//            			
//            			/*int  upd = appointmentTypeDAO.resetAllInprocessCharge(ipdForm.getId());
//            	           upd = appointmentTypeDAO.setInprocessforNewShiftCharges(invoiceid,log);*/
//            			
//            		    //ruchi code for send msg for bed change to patient relative or patient and other one for practioner on patient admit or update
//            			//sms akash 23 jan 2018 comment
//                  		    //  20/12/2018 sms on bed change and new admission access based
//                  		 
//                  		    if(ipdForm.getPractitionerid()!=null){
//                 		       if(!ipdForm.getPractitionerid().equals("")){
//                 		         
//                 		            practitionerid=Integer.parseInt(ipdForm.getPractitionerid());
//                 		          
//                 		       }
//                  		  }
//                  		  connection=Connection_provider.getconnection();
//                  		    UserProfileDAO userProfileDAO = new JDBCUserProfileDAO(connection);
//                  		  UserProfile userProfile  = userProfileDAO.getUserprofileDetails(practitionerid);
//                  		
//                  		    if(loginInfo.isSms_on_bedchange()==true){
//                  		    	
//                  		    
//            			autosmsBedChange(bed.getClientid(),ipdForm.getWardid(),ipdForm.getBedid(),userProfile.getMobile());
//                  		    }
////                  		    if(loginInfo.isSms_on_newadm()==true){
////            	           autosmsPrimaryDoctorChange(bed.getClientid(),ipdForm.getWardid(),ipdForm.getBedid(),practitionerid);
////                  		    }
//                  		    }
//      	
//        		
//    		}
    		
    		
    		int del = bedDao.deleteIpdCondition(ipdForm.getId());
    		saveConditions(ipdForm.getId(),ipdForm.getTreatmentepisodeid());
    		boolean flag2 = ipdDAO.checkCheckListAvailable(ipdForm.getTreatmentepisodeid());
    		if(!flag2){
    			ipdDAO.saveDischargeCheckList(Integer.parseInt(ipdForm.getTreatmentepisodeid()),ipdForm.getClientid());
            }
    		//  27 Nov 2017 
    		
    		String chiefcomplatetempname = ipdForm.getChiefcomplatetempname();
    		String presentillnesstempname = ipdForm.getPresentillnesstempname();
    		String pasthistorytempname = ipdForm.getPasthistorytempname();
    		String personalhistorytempname = ipdForm.getPersonalhistorytempname();
    		String familyhistorytempname = ipdForm.getFamilyhistorytempname();
    		String onexaminationtempname = ipdForm.getOnexaminationtempname();
    		String treatmentgiventempname = ipdForm.getTreatmentgiventempname();
    		String getmaternaltempname=ipdForm.getMaternalhisttemp();
    		String getperinataltempname=ipdForm.getPerinataltemp();
    		String ondiettempname=ipdForm.getOndiettempname();
    		String physiotempname=ipdForm.getPhysiotempname();
    		if(chiefcomplatetempname!=null){
    			if(!chiefcomplatetempname.equals("")){
    				int res = ipdDAO.saveIPDTemplate(chiefcomplatetempname,"1",ipdForm.getDepartment(),
    						new String(DateTimeUtils.isNull(ipdForm.getChiefcomplains()).getBytes("iso-8859-1"), "UTF-8")
    						);
    			}
    		}
    		if(presentillnesstempname!=null){
    			if(!presentillnesstempname.equals("")){
    				int res = ipdDAO.saveIPDTemplate(presentillnesstempname,"2",ipdForm.getDepartment(),
    						new String(DateTimeUtils.isNull(ipdForm.getPresentillness()).getBytes("iso-8859-1"), "UTF-8")
    						);
    			}
    		}
    		if(pasthistorytempname!=null){
    			if(!pasthistorytempname.equals("")){
    				int res = ipdDAO.saveIPDTemplate(pasthistorytempname,"3",ipdForm.getDepartment(),
    						new String(DateTimeUtils.isNull(ipdForm.getPasthistory()).getBytes("iso-8859-1"), "UTF-8")
    						);
    			}
    			
    		}
    		if(personalhistorytempname!=null){
    			if(!personalhistorytempname.equals("")){
    				int res = ipdDAO.saveIPDTemplate(personalhistorytempname,"5",ipdForm.getDepartment(),
    						new String(DateTimeUtils.isNull(ipdForm.getPersonalhist()).getBytes("iso-8859-1"), "UTF-8")
    						);
    			}
    			
    		}
    		if(familyhistorytempname!=null){
    			if(!familyhistorytempname.equals("")){
    				int res = ipdDAO.saveIPDTemplate(familyhistorytempname,"4",ipdForm.getDepartment(),
    						new String(DateTimeUtils.isNull(ipdForm.getFamilyhist()).getBytes("iso-8859-1"), "UTF-8")
    						);
    			}
    			
    		}
    		if(onexaminationtempname!=null){
    			if(!onexaminationtempname.equals("")){
    				int res = ipdDAO.saveIPDTemplate(onexaminationtempname,"6",ipdForm.getDepartment(),
    						new String(DateTimeUtils.isNull(ipdForm.getOnexamination()).getBytes("iso-8859-1"), "UTF-8")
    						);
    			}
    		}
    		if(treatmentgiventempname!=null){
    			if(!treatmentgiventempname.equals("")){
    				int res = ipdDAO.saveIPDTemplate(treatmentgiventempname,"7",ipdForm.getDepartment(),
    						new String(DateTimeUtils.isNull(ipdForm.getTreatmentepisodeid()).getBytes("iso-8859-1"), "UTF-8")
    						);
    			}
    		}
    		
    		if(getmaternaltempname!=null){
    			if(!getmaternaltempname.equals("")){
    				int res = ipdDAO.saveIPDTemplate(getmaternaltempname,"13",ipdForm.getDepartment(),
    						new String(DateTimeUtils.isNull(ipdForm.getMaternal_history()).getBytes("iso-8859-1"), "UTF-8")
    						);
    			}
    		}
    		if(getperinataltempname!=null){
    			if(!getperinataltempname.equals("")){
    				int res = ipdDAO.saveIPDTemplate(getperinataltempname,"14",ipdForm.getDepartment(),
    						new String(DateTimeUtils.isNull(ipdForm.getPerinatal_history()).getBytes("iso-8859-1"), "UTF-8")
    						);
    			}
    		}if(ondiettempname!=null){
    			if(!ondiettempname.equals("")){
    				int res = ipdDAO.saveIPDTemplate(ondiettempname,"17",ipdForm.getDepartment(),
    						new String(DateTimeUtils.isNull(ipdForm.getOndiet()).getBytes("iso-8859-1"), "UTF-8")
    						);
    			}
    		}
    		if(physiotempname!=null){
    			if(!physiotempname.equals("")){
    				int res = ipdDAO.saveIPDTemplate(physiotempname,"18",ipdForm.getDepartment(),
    						new String(DateTimeUtils.isNull(ipdForm.getPhysio()).getBytes("iso-8859-1"), "UTF-8")
    						);
    			}
    		}
    		
    		if(ipdForm.getSaveandprintadmission()==1){
    			session.setAttribute("saveandprintadmission", ""+ipdForm.getId());
    			return "ipdadmissionformprint";
    		}
    		
    		if(bedDao.isDayCare(""+ipdForm.getId())){
    			return "daycare";
    		}
    	}catch(Exception e){
    		e.printStackTrace();
    	}
    	finally{
			connection.close();
		}
		return "save";
	}


		public String sendvaccinesmsmanual(){
			try {
				Connection connection=Connection_provider.getconnection();
				/*BufferedReader br=request.getReader();
				String str="";
				while (br.readLine()!=null) {
					System.out.println(br.readLine());
				}*/
				StringBuilder buffer = new StringBuilder();
				BufferedReader reader = request.getReader();
				String line;
				while ((line = reader.readLine()) != null) {
					buffer.append(line);
				}
				JSONObject jsonObject=new JSONObject(buffer.toString());
				String vaccineId=jsonObject.getString("vaccineid");
				NotAvailableSlotDAO notAvailableSlotDAO= new JDBCNotAvailableSlotDAO(connection);
				boolean b=notAvailableSlotDAO.sendVaccineSMSManual(vaccineId,loginInfo);
				
				
				response.setContentType("application/json");
				response.setHeader("Cache-Control", "no-cache");
				response.getWriter().write("" + jsonObject.toString());
			} catch (Exception e) {
				e.printStackTrace();
			}
			return null;
		}

		public String listOfLedgers(){
			try {
				Connection connection=Connection_provider.getconnection();
				ExpenManagementDAO expenManagementDAO = new JDBCExpenceManagementDAO(connection);
				AppointmentTypeDAO appointmentTypeDAO = new JDBCAppointmentTypeDAO(connection);
				ArrayList<Master>ledgerList = appointmentTypeDAO.getExpenceLedgerAmountList(loginInfo);
				String type=request.getParameter("type");
				String cd="";
				if(type.equals("Payment")){
					cd="1";
				}else if(type.equals("Receipt")){
					cd="2";
				}
				StringBuffer buffer= new StringBuffer();
				buffer.append("<select name='ledgername' id='ledgername' onchange='showSelectedServices()' class='form-control chosen-select'>");
				buffer.append("<option value='0'>Select Ledger</option>");
				for(Master m:ledgerList){
					if(m.getCreditDebit().equals(cd)){
						buffer.append("<option value='"+m.getLedgerid()+"'>"+m.getName()+"</option>");
					}	
				}
				buffer.append("</select>");
				
				response.setContentType("text/html");
				response.setHeader("Cache-Control", "no-cache");
				response.getWriter().write(buffer.toString());
			} catch (Exception e) {
				e.printStackTrace();
			}
			return null;
		}
		
		
		public String updateledgername(){
			try {
				String ledgerId=DateTimeUtils.isNull(request.getParameter("ledgerid"));
				String ledgername=DateTimeUtils.isNull(request.getParameter("ledgername"));
				Connection connection= Connection_provider.getconnection();
				ExpenManagementDAO expenManagementDAO= new JDBCExpenceManagementDAO(connection);
				Master master= new Master();
				master.setId(DateTimeUtils.convertToInteger(ledgerId));
				master.setName(ledgername);
				expenManagementDAO.updateLedgerName(master);
				response.setContentType("text/html");
				response.setHeader("Cache-Control", "no-cache");
				response.getWriter().write("");
			} catch (Exception e) {
				e.printStackTrace();
			}
			return null;
		}
		
		public String delledger(){
			try {
				int ledgerId=DateTimeUtils.convertToInteger(request.getParameter("ledgerid"));
				
				Connection connection= Connection_provider.getconnection();
				ExpenManagementDAO expenManagementDAO= new JDBCExpenceManagementDAO(connection);
				
				int status=expenManagementDAO.delLedgerWithStatus(ledgerId);
				response.setContentType("text/html");
				response.setHeader("Cache-Control", "no-cache");
				response.getWriter().write(""+status);
			} catch (Exception e) {
				e.printStackTrace();
			}
			return null;
		}
		
		public String setaccessToNewTable(){
			try {
				Connection connection= Connection_provider.getconnection();
				ClinicDAO clinicDAO= new JDBCClinicDAO(connection);
				String colname=request.getParameter("col");
				String value=request.getParameter("value");
				int x=clinicDAO.switchAccessOfClinicNew(value, colname);
				
				if(DateTimeUtils.isNull(colname).equals("regional_lang")){
					int res = clinicDAO.updateUserLanguageAccess(value);
				}
				response.setContentType("text/html");
				response.setHeader("Cache-Control", "no-cache");
				response.getWriter().write("" +x+ "");
			} catch (Exception e) {
				e.printStackTrace();
			}
			return null;
		}
		
		
		
		public String savebmidata(){
			

			
			Connection connection=null; 
			try {
				connection=Connection_provider.getconnection();
//				/ var url="savebmiNotAvailableSlot?height="+height+"&weight="+weight+"&bmi="+bmi+"&pulse="+pulse+"&sysbp="+sysbp+"&diabp="+diabp+"";
				ClientDAO clientDAO=new JDBCClientDAO(connection);
				String height=request.getParameter("height");
				String weight=request.getParameter("weight");
				String bmi=request.getParameter("bmi");
				String pulse=request.getParameter("pulse");
				String sysbp=request.getParameter("sysbp");
				String diabp=request.getParameter("diabp");
				String patientId=request.getParameter("patientId");		
				String sugarfasting=request.getParameter("sugarfasting");
				String postmeal=request.getParameter("postmeal");	
				String temprature=request.getParameter("temprature");
				String spo=request.getParameter("spo");
				String head_cir=request.getParameter("bsa");
				String appointmentid=request.getParameter("apmtid");
				String lmpd=request.getParameter("lmpd");
				Client client=new Client();
				
				
				StringBuilder buffer = new StringBuilder();
				BufferedReader reader = request.getReader();
				String line;
				while ((line = reader.readLine()) != null) {
					buffer.append(line);
				}
				String data = buffer.toString();
				JSONObject jsonObject = new JSONObject(data);

				
				String name = jsonObject.getString("name");
				String datatext = jsonObject.getString("dataa");

				

				JSONObject jsonobj = new JSONObject();
			
				String response1 = jsonobj.toString();
				
				
				
				
				client.setHeight(height);
				client.setWeight(weight);
				client.setBmi(bmi);
				client.setPulse(pulse);
				client.setSysbp(sysbp);
				client.setDiabp(diabp);
				client.setClientid(patientId);
				client.setSugarfasting(sugarfasting);
				client.setPostmeal(postmeal);
				client.setTemprature(temprature);
				client.setSpo(spo);
				client.setHead_cir(head_cir);
				client.setAppointmentid(appointmentid);
				client.setLmpd(lmpd);
				int result=clientDAO.saveBMIPatient(client);
				
				Client client2 = clientDAO.getClientDetails(patientId);
				int ageinmonth = DateTimeUtils.getmonthsfromdob(client2.getDob());
				if(ageinmonth<=60 && ageinmonth>=0){
					if(height!=null){
						if(!height.equals("") && !height.equals("0")){
							Client client4 = new Client();
							client4.setHeightdata(height);
							client4.setWeightdata(weight);
							client4.setBmidata(bmi);
							client4.setHeadcircumferncedata(head_cir);
							client4.setClientid(patientId);
							client4.setMonth(""+ageinmonth);
							client4.setUserid(loginInfo.getUserId());
							String datetime = DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone());
							client4.setDate(datetime);
							int id = clientDAO.check_child_growth_data(patientId, ""+ageinmonth);
							int res1 = 0;
							if (id > 0) {
								client4.setId(id);
								// update
								res1 = clientDAO.updateChildGrowthData(client4, "height");
							} else {
								// insert
								res1 = clientDAO.saveChildGrowthData(client4, "height");
							}
						}
					}
					if(weight!=null){
						if(!weight.equals("") && !weight.equals("0")){
							Client client4 = new Client();
							client4.setHeightdata(height);
							client4.setWeightdata(weight);
							client4.setBmidata(bmi);
							client4.setHeadcircumferncedata(head_cir);
							client4.setClientid(patientId);
							client4.setMonth(""+ageinmonth);
							client4.setUserid(loginInfo.getUserId());
							String datetime = DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone());
							client4.setDate(datetime);
							int id = clientDAO.check_child_growth_data(patientId, ""+ageinmonth);
							int res1 = 0;
							if (id > 0) {
								client4.setId(id);
								// update
								res1 = clientDAO.updateChildGrowthData(client4, "weight");
							} else {
								// insert
								res1 = clientDAO.saveChildGrowthData(client4, "weight");
							}
						}
					}
					if(bmi!=null){
						if(!bmi.equals("") && !bmi.equals("0")){
							Client client4 = new Client();
							client4.setHeightdata(height);
							client4.setWeightdata(weight);
							client4.setBmidata(bmi);
							client4.setHeadcircumferncedata(head_cir);
							client4.setClientid(patientId);
							client4.setMonth(""+ageinmonth);
							client4.setUserid(loginInfo.getUserId());
							String datetime = DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone());
							client4.setDate(datetime);
							int id = clientDAO.check_child_growth_data(patientId, ""+ageinmonth);
							int res1 = 0;
							if (id > 0) {
								client4.setId(id);
								// update
								res1 = clientDAO.updateChildGrowthData(client4, "bmi");
							} else {
								// insert
								res1 = clientDAO.saveChildGrowthData(client4, "bmi");
							}
						}
					}
					if(head_cir!=null){
						if(!head_cir.equals("") && !head_cir.equals("0")){
							Client client4 = new Client();
							client4.setHeightdata(height);
							client4.setWeightdata(weight);
							client4.setBmidata(bmi);
							client4.setHeadcircumferncedata(head_cir);
							client4.setClientid(patientId);
							client4.setMonth(""+ageinmonth);
							client4.setUserid(loginInfo.getUserId());
							String datetime = DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone());
							client4.setDate(datetime);
							int id = clientDAO.check_child_growth_data(patientId, ""+ageinmonth);
							int res1 = 0;
							if (id > 0) {
								client4.setId(id);
								// update
								res1 = clientDAO.updateChildGrowthData(client4, "headcircumfernce");
							} else {
								// insert
								res1 = clientDAO.saveChildGrowthData(client4, "headcircumfernce");
							}
						}
					}
				}
				
				response.setContentType("application/json");
				response.setHeader("Cache-Control", "no-cache");
				response.getWriter().write("" + response1);
				
			} catch (Exception e) {

				e.printStackTrace();
			}
			 
			
		 
			return null;
		}
		
		
		
		
		
		public String deleteinvoicerelateddata(){
			try {
				Connection connection= Connection_provider.getconnection();
				String invoiceId=DateTimeUtils.isNull(request.getParameter("invId"));  
				AccountsDAO accountsDAO= new JDBCAccountsDAO(connection);
				Accounts invoiceData= accountsDAO.getInvoiceDetails(invoiceId);
				if(invoiceData.getItype().equals("2")){
					IpdDAO ipdDAO= new JDBCIpdDAO(connection);
					ipdDAO.clearIpdDetails(invoiceData.getIpdid());
					ipdDAO.saveIpdInvoiceDataClearLog(loginInfo, invoiceData);
				}
				response.setContentType("text/html");
				response.setHeader("Cache-Control", "no-cache");
				response.getWriter().write("");
			} catch (Exception e) {
				e.printStackTrace();
			}
			return null;
		}
		
		
		
		public String investigationgraph(){
			try {
				Connection connection= Connection_provider.getconnection();
				InvestigationDAO investigationDAO= new JDBCInvestigationDAO(connection);
				ClientDAO clientDAO = new JDBCClientDAO(connection);
				String id=DateTimeUtils.isNull(request.getParameter("invstId"));
				Investigation investigation=investigationDAO.getEditInvestigation(id);
				ipdForm.setTodate(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
				ipdForm.setInvstgiven(investigationDAO.getInvestType(DateTimeUtils.convertToInteger(id)));
				ipdForm.setPatient(clientDAO.getPatient(DateTimeUtils.convertToInteger(investigation.getClientId())));
				ipdForm.setInvestlist(investigationDAO.getSelectedInvestigationData(id));
			} catch (Exception e) {
				e.printStackTrace();
			}
			return "investigationgraph";
		}
		
		public String edit()throws Exception{
	    	
	    	if(!verifyLogin(request)) {
	    		return "login";
	    	}
	    	
	    	Connection connection=null;
			String action = request.getParameter("action");
			try {
				
				connection=Connection_provider.getconnection();
				UserProfileDAO userProfileDAO= new JDBCUserProfileDAO(connection);
				String selectedid = request.getParameter("selectedid");
				String practitionerid= request.getParameter("practitionerid");
				MasterDAO masterDAO=new JDBCMasterDAO(connection);
				NotAvailableSlotDAO notAvailableSlotDAO= new JDBCNotAvailableSlotDAO(connection);
				
				ipdForm.setClientip(selectedid);
				
				IpdDAO ipdDAO = new JDBCIpdDAO(connection);
				BedDao bedDao=new JDBCBedDao(connection);
				Bed bed = bedDao.getEditIpdData(selectedid);
				if(loginInfo.getIpd_abbr_access()==1){
					 String newipdabbr=ipdDAO.getIpdAbrivationIds(Integer.parseInt(selectedid));
					 ipdForm.setNewipdabbr(newipdabbr);
					 if(Integer.parseInt(bed.getIpdseqno())>0){
						 ipdForm.setIpdseqno(bed.getIpdseqno());
					 }else{
						 ipdForm.setIpdseqno(selectedid);
					 }
				}else{
					if(Integer.parseInt(bed.getIpdseqno())>0){
						ipdForm.setIpdseqno(bed.getIpdseqno());
						ipdForm.setNewipdabbr(bed.getIpdseqno());
					}else{
						ipdForm.setIpdseqno(selectedid);
						ipdForm.setNewipdabbr(selectedid);
					}
				}
				if(practitionerid!=null){
					ipdForm.setPractitionerid(practitionerid);
				} else {
					ipdForm.setPractitionerid(bed.getPractitionerid());
				}
				
				ipdForm.setClientid(bed.getClientid());
				ipdForm.setEditclientid(bed.getClientid());
	    		
	    		ipdForm.setConditionid(bed.getConditionid());
	    		ipdForm.setDepartment(bed.getDepartment());
	    		ipdForm.setRefferedby(bed.getRefferedby());
	    		ipdForm.setMlcabrivationid(bed.getMlcabrivationid());
	    		ipdForm.setRefferedby(bed.getReferenceid());
	    		
	    		if(bed.getPractitionerid()!=null){
	    			UserProfile userProfile=  userProfileDAO.getUserprofileDetails(Integer.parseInt(ipdForm.getPractitionerid()));
	    			String specializationId=  userProfile.getDiciplineName();
	    			ipdForm.setDepartment(userProfile.getSpecialization());
	    			if(loginInfo.getClinicUserid().equals("iconhospital")){
	    				if(specializationId.equals("20")||specializationId.equals("21")){
	    					ipdForm.setWeightsts("true");
	    				}else{
	    					ipdForm.setWeightsts("false");
	    				}
	    			}else{
	    				ipdForm.setWeightsts("false");
	    			}
	    			boolean issystemicreview= masterDAO.isIpdFormFieldActive(specializationId,"Systemic Review");
	    			boolean history= masterDAO.isIpdFormFieldActive(specializationId,"History");
	    			boolean obstretic_history = masterDAO.isIpdFormFieldActive(specializationId,"OBSTRETIC HISTORY");
	    			boolean menstrual_history = masterDAO.isIpdFormFieldActive(specializationId, "MENSTRUAL HISTORY"); 
	    			boolean substance_history = masterDAO.isIpdFormFieldActive(specializationId, "SUBSTANCE HISTORY");
	    			boolean peditric = masterDAO.isIpdFormFieldActive(specializationId, "Paediatric History");
	    			ipdForm.setNicuaccess(masterDAO.isIpdFormFieldActive(specializationId, "NICU Setting"));
	    			ipdForm.setNicuaccess(peditric);
	    			ipdForm.setIssystemicreview(issystemicreview);
	    			ipdForm.setHistory(history);
	    			ipdForm.setObstretic_history(obstretic_history);
	    			ipdForm.setMenstrual_history(menstrual_history);
	    			ipdForm.setSubstance_history(substance_history);
	        		ipdForm.setPaediatrichist(peditric);
	    		}
	    		
	    		ArrayList<DiaryManagement> userList=notAvailableSlotDAO.getEditUserList(loginInfo.getId(),selectedid); 
	    		ipdForm.setUserList(userList);
	    		ArrayList<Bed>bedlist = ipdDAO.geteditWardWiseBedList(bed.getWardid(),bed.getBedid());
	    		ipdForm.setBedlist(bedlist);
	    		
	    		ipdForm.setWardid(bed.getWardid());

	    		// 
	    		if(bed.getBedid().equals("0")){
	    			// get from log means patient discharge
	    			String bedid= ipdDAO.getBedIdFromLog(selectedid);
	    			bed.setBedid(bedid);
	    			ipdForm.setDisbedid("0");
	    			//after discharge bed is zero so it not allow to update from emr so add bed from log akash 23 march 2018 
	    			ArrayList<Bed> bedlist1 = ipdDAO.geteditBedListAfterDischarge(bed.getBedid());
	        		ipdForm.setBedlist(bedlist1);
	    		}  else {
	    			ipdForm.setDisbedid(bed.getBedid());
	    		}
	    			
	    		ipdForm.setBedid(bed.getBedid());
	    		ipdForm.setOrigbedid(bed.getBedid());
	    		
	    		//ipdForm.setTpid(bed.getTpid());
	    		ipdForm.setStatus(bed.getStatus());
	    		ipdForm.setAddmissionfor(bed.getAddmissionfor());
	    		ipdForm.setReimbursment(bed.getReimbursment());
	    		ipdForm.setSecndryconsult(bed.getSecndryconsult());
	    		ipdForm.setMlcno(bed.getMlcno());
	    		ipdForm.setMlccase(bed.getMlccase());
	    		ipdForm.setAdmissiondetails(bed.getAdmissiondetails());
	    		ipdForm.setSuggestedtrtment(bed.getSuggestedtrtment());
	    		ipdForm.setHosp(bed.getHosp());
	    		ipdForm.setPackagename(bed.getPackagename());
	    		//chiefcomplains, presentillness, pasthistory, personalhist, familyhist, onexamination, treatmentepisodeid
	    		ipdForm.setChiefcomplains(bed.getChiefcomplains());
	    		ipdForm.setPresentillness(bed.getPresentillness());
	    		ipdForm.setPasthistory(bed.getPasthistory());
	    		ipdForm.setPersonalhist(bed.getPersonalhist());
	    		ipdForm.setFamilyhist(bed.getFamilyhist());
	    		ipdForm.setOnexamination(bed.getOnexamination());
	    		ipdForm.setTreatmentepisodeid(bed.getTreatmentepisodeid());
	    		
	    		ipdForm.setMaternal_history(bed.getMaternal_history());
	    		ipdForm.setPerinatal_history(bed.getPerinatal_history());
	    		
	    		ipdForm.setSuggestoper(bed.getSuggestoper());
	    		ipdForm.setSystdepartment(ipdForm.getSystdepartment());
	    		ipdForm.setFpcondition(bed.getFpcondition());
	    		ipdForm.setFpnotest(bed.getFpnotest());
	    		ipdForm.setNauseacondition(bed.getNauseacondition());
	    		ipdForm.setNauseanotes(bed.getNauseanotes());
	    		ipdForm.setFnucondition(bed.getFnucondition());
	    		ipdForm.setFnunotes(bed.getFnunotes());
	    		ipdForm.setSmcondition(bed.getSmcondition());
	    		ipdForm.setSmnotes(bed.getSmnotes());
	    		ipdForm.setChestpaincond(bed.getChestpaincond());
	    		ipdForm.setChestpainnotes(bed.getChestpainnotes());
	    		ipdForm.setDimvisioncond(bed.getDimvisioncond());
	    		ipdForm.setDimvisionnotes(bed.getDimvisionnotes());
	    		ipdForm.setOndiet(bed.getOndiet());
	    		
	    		//dimvisionnotes, hgucondition, hgunotes, hmcondition, hmnotes, jointpaincond, jointpainnotes, 
	    		ipdForm.setHgucondition(bed.getHgucondition());
	    		ipdForm.setHgunotes(bed.getHgunotes());
	    		ipdForm.setHmcondition(bed.getHmcondition());
	    		ipdForm.setHmnotes(bed.getHmnotes());
	    		ipdForm.setJointpaincond(bed.getJointpaincond());
	    		ipdForm.setJointpainnotes(bed.getJointpainnotes());
	    		
	    		//constipationcond, constpationnotes, specialnotes, edemafeetcondi, edemafeetnotes, hematuriacondi, 
	    		ipdForm.setConstipationcond(bed.getConstipationcond());
	    		ipdForm.setConstpationnotes(bed.getConstpationnotes());
	    		ipdForm.setSpecialnotes(bed.getSpecialnotes());
	    		ipdForm.setEdemafeetcondi(bed.getEdemafeetcondi());
	    		ipdForm.setEdemafeetnotes(bed.getEdemafeetnotes());
	    		ipdForm.setHematuriacondi(bed.getHematuriacondi());
	    		ipdForm.setHematurianotes(bed.getHematurianotes());
	    		
	    		//hematurianotes, bmcondition, bmnotes, oliguriacondi, oligurianotes, pstgucondi, pstgunotes, 
	    		ipdForm.setBmcondition(bed.getBmcondition());
	    		ipdForm.setBmnotes(bed.getBmnotes());
	    		ipdForm.setOliguriacondi(bed.getOliguriacondi());
	    		ipdForm.setOligurianotes(bed.getOligurianotes());
	    		ipdForm.setPstgucondi(bed.getPstgucondi());
	    		ipdForm.setPstgunotes(bed.getPstgunotes());
	    		
	    		//bmecondition, bmenotes, tnecondition, tnenotes, weaknesscondi, weaknessnotes, ihcondition, ihnotes
	    		ipdForm.setBmecondition(bed.getBmecondition());
	    		ipdForm.setBmenotes(bed.getBmenotes());
	    		ipdForm.setTnecondition(bed.getTnecondition());
	    		ipdForm.setTnenotes(bed.getTnenotes());
	    		ipdForm.setWeaknesscondi(bed.getWeaknesscondi());
	    		ipdForm.setWeaknessnotes(bed.getWeaknessnotes());
	    		ipdForm.setIhcondition(bed.getIhcondition());
	    		ipdForm.setIhnotes(bed.getIhnotes());
	    		ipdForm.setAdmission_reason(bed.getAdmission_reason());
	    		ipdForm.setEarlierinvest(bed.getEarlierinvest());
	    		ipdForm.setAlergies(bed.getAlergies());
	    		
	    		//gynic details
	    		ipdForm.setAlcohal(bed.getAlcohal());
	    		ipdForm.setDrugs(bed.getDrugs());
	    		ipdForm.setOther_medication(bed.getOther_medication());
	    		ipdForm.setTobaco(bed.getTobaco());
	    		ipdForm.setTobaconotes(bed.getTobaconotes());
	    		ipdForm.setSmoking(bed.getSmoking());
	    		
	    		ipdForm.setAge_menarche(bed.getAge_menarche());
	    		ipdForm.setLmp(bed.getLmp());
	    		ipdForm.setLlmp(bed.getLlmp());
	    		ipdForm.setPmc(bed.getPmc());
	    		//peditric 
	    		ipdForm.setBirthhist(bed.getBirthhist());
	    		ipdForm.setDiethist(bed.getDiethist());
	    		ipdForm.setDevelopmenthist(bed.getDevelopmenthist());
	    		ipdForm.setEmmunizationhist(bed.getEmmunizationhist());
	    		ipdForm.setHeadcircumference(bed.getHeadcircumference());
	    		ipdForm.setMidarmcircumference(bed.getMidarmcircumference());
	    		ipdForm.setLength(bed.getLength());
	    		ipdForm.setWtaddmission(bed.getWtaddmission());
	    		ipdForm.setWtdischarge(bed.getWtdischarge());
	    		
	    		
	    		ipdForm.setCycle_length(bed.getCycle_length());
	    		ipdForm.setDuration_flow(bed.getDuration_flow());
	    		ipdForm.setAmount_flow(bed.getAmount_flow());
	    		ipdForm.setDysmenorrhea(bed.getDysmenorrhea());
	    		ipdForm.setDyspareunia(bed.getDyspareunia());
	    		ipdForm.setHopassing_clots(bed.getHopassing_clots());
	    		
	    		ipdForm.setWhite_disc_itching(bed.getWhite_disc_itching());
	    		ipdForm.setIntercourse_freq(bed.getIntercourse_freq());
	    		ipdForm.setGalactorrea(bed.getGalactorrea());
	    		
	    		ipdForm.setHo_contraception(bed.getHo_contraception());
	    		ipdForm.setRubella_vaccine(bed.getRubella_vaccine());
	    		ipdForm.setMenopause(bed.getMenopause());
	    		ipdForm.setNulligravida(bed.getNulligravida());
	    		ipdForm.setCurrent_pregnent(bed.getCurrent_pregnent());
	    		ipdForm.setIud(bed.getIud());
	    		
	    		ipdForm.setLive_boys(bed.getLive_boys());
	    		ipdForm.setLive_girls(bed.getLive_girls());
	    		ipdForm.setStillbirths(bed.getStillbirths());
	    		ipdForm.setAbortions(bed.getAbortions());
	    		ipdForm.setDeath_children(bed.getDeath_children());
	  		
	    		//parity_aboration_notes,p,l,a,d
	    		ipdForm.setParity_abortion_notes(bed.getParity_abortion_notes());
	    		ipdForm.setP(bed.getP());
	    		ipdForm.setL(bed.getL());
	    		ipdForm.setA(bed.getA());
	    		ipdForm.setD(bed.getD());
	    		ipdForm.setMlcrefdoctor(bed.getMlcrefdoctor());
	    		ipdForm.setSurgicalnotes(bed.getSurgicalnotes());
	    		ipdForm.setMlccase(bed.getMlccase());
	    		
	    		//giddinesscondition,giddinessnotes,unconcondition,unconnotes
	    		ipdForm.setGiddinesscondition(bed.getGiddinesscondition());
	    		ipdForm.setGiddinessnotes(bed.getGiddinessnotes());
	    		ipdForm.setUnconcondition(bed.getUnconcondition());
	    		ipdForm.setUnconnotes(bed.getUnconnotes());
	    		
	    		ipdForm.setGstureage(bed.getGstureage());
	    		ipdForm.setWtonbirth(bed.getWtonbirth());
	    		ipdForm.setRefdocotor(bed.getRefdocotor());
	    		/*ArrayList<Bed> gynicobsList= bedDao.getGynicObsList(selectedid);*/
	    		ArrayList<Bed> gynicobsList= new ArrayList<>();
	    		ipdForm.setGynicobsList(gynicobsList);
	    		
	    		String temp[] = bed.getAdmissiondate().split(" ");
				String date = DateTimeUtils.getCommencingDate1(temp[0]);
				ipdForm.setAdmissiondate(date);
				String time[] = temp[1].split(":");
				String hh = time[0];
				String mm = time[1];
				ipdForm.setHour(hh);
				ipdForm.setMinute(mm);
	    		
	    		//ipdForm.setAdmissiondate(bed.getAdmissiondate());
	    		
	    		ipdForm.setId(Integer.parseInt(selectedid));
	    		
	    		//set treatment episode
	    		
	    		ClientDAO clientDAO = new JDBCClientDAO(connection);
	    		Client client = clientDAO.getClientDetails(ipdForm.getClientid());
	    		String fullname = client.getTitle() + " " + client.getFirstName() + " " + client.getLastName();
	    		ipdForm.setFname(client.getFirstName());
	    		ipdForm.setLname(client.getLastName());
	    		ipdForm.setTitle(client.getTitle());
	    		ipdForm.setAge(client.getAge1());
	    		ipdForm.setGender(client.getGender());
	    		ipdForm.setClient(fullname);
	    		ipdForm.setRelativename(client.getEmergencyContName());
	    		ipdForm.setRelationcont(client.getEmergencyContNo());
	    		ipdForm.setRelation(client.getRelation());
	    		String numcount=ipdDAO.getNumofAdmissionCount(ipdForm.getClientid());
	    		ipdForm.setNum_admission(numcount);
	    		ipdForm.setDob(client.getDob());
	    		ipdForm.setTpid(client.getTypeName());
	    		ipdForm.setAddress(client.getAddress());
	    		ipdForm.setAbrivationid(client.getAbrivationid());
	    		String age = DateTimeUtils.getAge1(client.getDob());
	    		String agegender = age + "Years" + " / " + client.getGender();
	    		ipdForm.setAgegender(age+"/"+client.getGender());
	    		ipdForm.setMob(client.getMobNo());
	    		
	    		String payby = client.getWhopay();
	    		if(client.getWhopay().equals("Self")){
	    			payby = "Client";
	    		}
	    		if(payby.equalsIgnoreCase("client")){
	    			ipdForm.setPayee("0");
	    		} else {
	    			ipdForm.setPayee("1");
	    		}
	    		
	    		TreatmentEpisodeDAO treatmentEpisodeDAO = new JDBCTreatmentEpisode(connection);
	    		ArrayList<TreatmentEpisode> treatmentEpisodeList = treatmentEpisodeDAO.getIpdTreatmentEpisodeList(ipdForm.getClientid(), payby);
	    		
	    		if(treatmentEpisodeList.size()<1){
	    			   
	    			treatmentEpisodeList=treatmentEpisodeDAO.getSelectedTreatmentEpisode(bed.getTreatmentepisodeid());
	    		}
	    		ipdForm.setTreatmentEpisodeList(treatmentEpisodeList);
	    		ipdForm.setTreatmentEpisode(bed.getTreatmentepisodeid());
	    		
	    		int actionType= DateTimeUtils.convertToInteger(bed.getAction());
				
				boolean isdaycare=false;
				if(actionType==2){
					isdaycare = true;
				}
				ipdForm.setDaycare(isdaycare);
	    		
	    		//ipdForm.setDaycare(bedDao.isDayCare(selectedid));
	    		
	    		ArrayList<Bed>ipdConditionids = bedDao.getIpdConditionList(selectedid);
	    		ArrayList<Client> ipdCondtitionList = new ArrayList<Client>(); // clientDAO.getEmrTreatmentTypeList();
	    		session.setAttribute("ipdConditionids", ipdConditionids);
	    		session.setAttribute("ipdCondtitionList", ipdCondtitionList);
	    		
	    		
	    		/*String chief_comlint_id=masterDAO.getIpdTemplateId("Chief Complaints");
				String present_ill_id=masterDAO.getIpdTemplateId("Present Illness");
				String past_history_id=masterDAO.getIpdTemplateId("Past History");
				String family_hist_id=masterDAO.getIpdTemplateId("Family History");
				String personal_hist_id=masterDAO.getIpdTemplateId("Personal History");
				String onexami_id=masterDAO.getIpdTemplateId("On Examination");
				String tretment_given_id=masterDAO.getIpdTemplateId("Treatment Given");
				String maternalHisttempid=masterDAO.getIpdTemplateId("Maternal History");
				String perinatalHistroy=masterDAO.getIpdTemplateId("Perinatal History");*/
				
				ArrayList<Master> chief_complaints_list=masterDAO.getIpdTemplateListNamesList("Chief Complaints");
				ArrayList<Master> present_illness_list=masterDAO.getIpdTemplateListNamesList("Present Illness");
				ArrayList<Master> past_history_list=masterDAO.getIpdTemplateListNamesList("Past History");
				ArrayList<Master> family_history_list=masterDAO.getIpdTemplateListNamesList("Family History");
				ArrayList<Master> personal_hist_list=masterDAO.getIpdTemplateListNamesList("Personal History");
				ArrayList<Master> on_exam_list=masterDAO.getIpdTemplateListNamesList("On Examination");
				ArrayList<Master> treatment_given_list=masterDAO.getIpdTemplateListNamesList("Treatment Given");
				ArrayList<Master> perintal_hisry_list=masterDAO.getIpdTemplateListNamesList("Perinatal History");
				ArrayList<Master> maternal_histry_list=masterDAO.getIpdTemplateListNamesList("Maternal History");
				ArrayList<Master> examination_finding_list=masterDAO.getIpdTemplateListNames("Examination Findings");
				ArrayList<Master>on_diet_list=masterDAO.getIpdTemplateListNamesList("On Diet");
				ArrayList<Master>physio_list=masterDAO.getIpdTemplateListNamesList("Physio");
				ArrayList<Master>karma_list=masterDAO.getIpdTemplateListNamesList("Karma");
			    ArrayList<Master>procedure_list=masterDAO.getIpdTemplateListNamesList("Procedurebams");
				ipdForm.setChief_complaints_list(chief_complaints_list);
				ipdForm.setPresent_illness_list(present_illness_list);
				ipdForm.setPast_history_list(past_history_list);
				ipdForm.setFamily_history_list(family_history_list);
				ipdForm.setPersonal_hist_list(personal_hist_list);
				ipdForm.setOn_exam_list(on_exam_list);
				ipdForm.setTreatment_given_list(treatment_given_list);
				ipdForm.setPerintal_hisry_list(perintal_hisry_list);
				ipdForm.setMaternal_histry_list(maternal_histry_list);
				ipdForm.setExamination_finding_list(examination_finding_list);
				ipdForm.setOn_diet_list(on_diet_list);
				ipdForm.setPhysio_list(physio_list);
				ipdForm.setKarma_list(karma_list);
				ipdForm.setProcedure_list(procedure_list);
				int selectedid1 = loginInfo.getId();
				
				ClinicDAO clinicListDAO = new JDBCClinicDAO(connection);
				com.apm.Registration.eu.entity.Clinic cliniclist = clinicListDAO.getCliniclistDetails(selectedid1);
				ipdForm.setClinicName(cliniclist.getClinicName());
				
				
				ArrayList<Bed> wardlist=bedDao.getAllWardList(bed.getAction());
				ipdForm.setWardlist(wardlist);
				
				//get prescription list
				//ArrayList<Priscription>admissionPriscList = ipdDAO.getAdmissionPrescList(selectedid);
				String ids =ipdDAO.getAdmissionParentPriscIds(selectedid);
				
				ArrayList<Priscription> admissionPriscList = ipdDAO.getAdmissionPriscList(ids);
				
				if(admissionPriscList.size()>0){
					Priscription pr= admissionPriscList.get(admissionPriscList.size()-1);
					ipdForm.setStrengthflag(pr.isStrengthflag());
					ipdForm.setRemarkflag(pr.isRemarkflag());
					ipdForm.setQuantityflag(pr.isStrengthflag());
				}
				//String discadvoice=ipdDAO.getDIscPrisc(selectedid);
				//ipdForm.setAdvoice(discadvoice);
				
				ipdForm.setAdmissionPriscList(admissionPriscList);
				session.setAttribute("dischargePriscList",admissionPriscList);
				ipdForm.setActiontype(bed.getAction());
				
				ipdForm.setHourList(PopulateList.hourListNew());
				ipdForm.setMinuteList(PopulateList.getMinuteList());
				AccountsDAO accountsDAO = new JDBCAccountsDAO(connection);
				ArrayList<Accounts>thirdPartyList = accountsDAO.getThirdPartyList(loginInfo.getId());
				ipdForm.setThirdPartyList(thirdPartyList);
				
				ArrayList<UserProfile> mlcdrlist = userProfileDAO.getAllPractitionerList(null,null,null,null,"1");
				ipdForm.setMlcdrlist(mlcdrlist);
				
				ArrayList<Client> refrenceList = clientDAO.getReferenceList();
				ipdForm.setRefrenceList(refrenceList);
				
				ArrayList<Client> refrencedoctorList = clientDAO.getReferenceDoctorList(selectedid);
				ipdForm.setRefrencedoctorList(refrencedoctorList);
				
				DiaryManagementDAO diaryManagementDAO=new JDBCDiaryManagentDAO(connection);
				ArrayList<String> departmentList= diaryManagementDAO.getDepartmentList();
				ipdForm.setDepartmentList(departmentList);
				
				ArrayList<Client> condtitionList = clientDAO.getEmrTreatmentTypeList();
				ipdForm.setCondtitionList(condtitionList);
				
				ArrayList<TreatmentEpisode> sourceOfReferralList = treatmentEpisodeDAO.getSourceOfReferralList();
				ipdForm.setSourceOfReferralList(sourceOfReferralList);
				
				ArrayList<Master> medicineList = new ArrayList<Master>();
				ipdForm.setMedicineList(medicineList);
				
				EmrDAO emrDAO = new JDBCEmrDAO(connection);
				ArrayList<Priscription>templateNameList = emrDAO.getTemplateNameList(loginInfo);
				if(templateNameList.size()==0){
					templateNameList = new ArrayList<Priscription>();
				}
				ipdForm.setTemplateNameList(templateNameList);
				ipdForm.setCountry("India");
				
				ArrayList<Priscription>parentPriscList = new ArrayList<Priscription>();
				ipdForm.setParentPriscList(parentPriscList);
				
				//From Ajax
//				ArrayList<Master>dosageList = emrDAO.getDosageList();
				ArrayList<Master>dosageList = new ArrayList<>();
				ipdForm.setDosageList(dosageList);
				
//				ArrayList<Priscription> medicinetimelist = emrDAO.getMedicineTimeList(); 
				ArrayList<Priscription> medicinetimelist = new ArrayList<>();
				ipdForm.setMedicinetimelist(medicinetimelist);
				
//				ArrayList<Master>dosagenoteList = masterDAO.getDosageNoteList();
				ArrayList<Master>dosagenoteList = new ArrayList<>();
				ipdForm.setDosagenoteList(dosagenoteList);
				
				/*ArrayList<Master>nimaidoselist = masterDAO.getnimaidoselistt();
				ArrayList<Master>nimaiqtylist = masterDAO.getnimaiqtylist();
				ArrayList<Master>nimairemarklist = masterDAO.getnimairemarlist();*/
				ArrayList<Master>nimaidoselist = new ArrayList<>();
				ArrayList<Master>nimaiqtylist = new ArrayList<>();
				ArrayList<Master>nimairemarklist = new ArrayList<>();
				
				ipdForm.setNimaidoselist(nimaidoselist);
				ipdForm.setNimaiqtylist(nimaiqtylist);
				ipdForm.setNimairemarklist(nimairemarklist);
				
				
				ArrayList<Master> priscUnitList= masterDAO.getPriscUnitList();
				ipdForm.setPriscUnitList(priscUnitList);
				
				ArrayList<Master>mdicneTypeList = emrDAO.getMedicineTypeList();
				ipdForm.setMdicneTypeList(mdicneTypeList);
				
				ArrayList<Master>mdicinecategoryList = emrDAO.getmedicineCategoryList();
				ipdForm.setMdicinecategoryList(mdicinecategoryList);
				
				ArrayList<Master> specializationTemplateList= masterDAO.getMasterSpecializationList();
				ipdForm.setSpecializationTemplateList(specializationTemplateList);
				
				ArrayList<UserProfile> practionerlist = userProfileDAO.getVisitingPractitiner();
				ipdForm.setPractionerlist(practionerlist);
				
				ArrayList<Master> disciplineList = masterDAO.getDisciplineDataList();
				ipdForm.setDisciplineList(disciplineList);
				ArrayList<String> initialList = new ArrayList<String>();
				initialList = clientDAO.getInitialList();
				ipdForm.setInitialList(initialList);
				
				String fromDate=ipdForm.getFromDate();
				
				if(DateTimeUtils.isNull(fromDate).equals("")){
					DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
					fromDate = dateFormat.format(new Date());
				}else{
					fromDate = DateTimeUtils.getCommencingDate1(fromDate);
				}

				ipdForm.setFromDate(DateTimeUtils.getCommencingDate1(fromDate));
				
				if(bed.getAction().equals("1")){
					if(action.equals("1")){
						 wardlist=bedDao.getAllWardList("1");
						ipdForm.setWardlist(wardlist);
						return "editcasualty";
					}
					return "editcasualty";
				}
				
			}catch(Exception e){
				e.printStackTrace();
			}
			finally{
				connection.close();
			}
	    	return "edit";
	    }
		
		 public String print() throws Exception {
			 
			 if(!verifyLogin(request)) {
		 		return "login";
		 	}
		     Connection connection=null;
			
			try {
				connection=Connection_provider.getconnection();
				ClientDAO clientDAO = new JDBCClientDAO(connection);
				String selectedid = request.getParameter("selectedid");
				ClinicDAO clinicDAO=new JDBCClinicDAO(connection);
				AccountsDAO accountsDAO=new JDBCAccountsDAO(connection);
				String saveandprintadmission = (String) session.getAttribute("saveandprintadmission");
				if(!DateTimeUtils.isNull(saveandprintadmission).equals("")){
					selectedid = saveandprintadmission;
					session.removeAttribute("saveandprintadmission");
				}
				ArrayList<Master>declerationTitleList = clientDAO.getDeclerationTitleList();
				ipdForm.setDeclerationTitleList(declerationTitleList);
				
				printformdata(selectedid,new Bed(),new Bed(),new Client(),0,new UserProfile());
				session.setAttribute("declarationNotes", null);
				ArrayList<Clinic> locationAdressList = accountsDAO.getLocationAddress(loginInfo.getClinicid());
			    Clinic	clinic = clinicDAO.getCliniclistDetails(loginInfo.getClinicid());
				ipdForm.setClinicName(clinic.getClinicName());
				ipdForm.setClinicOwner(clinic.getClinicOwner());
				ipdForm.setOwner_qualification(clinic.getOwner_qualification());
				ipdForm.setLocationAdressList(locationAdressList);
				ipdForm.setMobile(clinic.getMobileNo());
				//ipdForm.setAddress(clinic.getAddress());
				ipdForm.setLandLine(clinic.getLandLine());
				ipdForm.setClinicemail(clinic.getEmail());
				ipdForm.setWebsiteUrl(clinic.getWebsiteUrl());
				ipdForm.setClinicLogo(clinic.getUserImageFileName());
			}
			catch(Exception e) {
				e.printStackTrace();
			}
			
			finally {
				connection.close();
			}
			
			return "printForm";
		}
		 
		 public void printformdata(String selectedid, Bed bed, Bed bed1, Client client, int intitalizedObject, UserProfile userProfile) throws SQLException{
			 Connection connection=null;
				
				try {
					request.setCharacterEncoding("UTF-8");
					response.setCharacterEncoding("UTF-8");
					connection=Connection_provider.getconnection();
					//String selectedid = request.getParameter("selectedid");
					ipdForm.setClientip(selectedid);
					
					BedDao bedDao=new JDBCBedDao(connection);
					IpdDAO ipdDAO = new JDBCIpdDAO(connection);
					ClientDAO clientDA=new JDBCClientDAO(connection);
					if(intitalizedObject==0){
						bed = bedDao.getEditIpdData(selectedid);
					}
					MasterDAO masterDAO=new JDBCMasterDAO(connection);
					IpdLogDAO ipdLogDAO=new JDBCIpdLogDAO(connection);
					NotAvailableSlotDAO notAvailableSlotDAO = new JDBCNotAvailableSlotDAO(connection);
					
					ipdForm.setCasualtyipd(bed.getAction());
					ipdForm.setMbbsseqno(bed.getMbbsipdsequence());
					//FOR BAMS Opd Vitals
					ipdForm.setOpd_abbrivation_id(bed.getOpdabbrivationid());
					String apmtId=bedDao.getAppointmentId(ipdForm.getOpd_abbrivation_id());
					Client bmidata = clientDA.getBMIData(bed.getClientid(), apmtId);
					ipdForm.setPulse(bmidata.getPulse());
					ipdForm.setSys_bp(bmidata.getSysbp());
					ipdForm.setSpo(bmidata.getSpo());
					ipdForm.setTemp(bmidata.getTemprature());
					ipdForm.setSugarFasting(bmidata.getSugarfasting());
					ipdForm.setHeight(bmidata.getHeight());
					ipdForm.setWeight(bmidata.getWeight());
					ipdForm.setBmi(bmidata.getBmi());
					ipdForm.setAllergynotes(bmidata.getAllergynotes());
					ipdForm.setRespiratory_rate(bmidata.getRespiratory_rate());
					ipdForm.setDia_bp(bmidata.getDiabp());
					ipdForm.setPostmeal(bmidata.getPostmeal());
					
					
					int actionType= DateTimeUtils.convertToInteger(bed.getAction());
					boolean isdaycare=false;
					if(actionType==2){
						isdaycare = true;
					}
					ipdForm.setDaycare(isdaycare);
					//ipdForm.setDaycare(bedDao.isDayCare(selectedid));
					String dd[]=DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone()).split(" ");
					String printedBy = loginInfo.getUserId()+" "+DateTimeUtils.getCommencingDate1(dd[0])+" "+dd[1];  
					ipdForm.setPrintedBy(printedBy);
					if(loginInfo.getIpd_abbr_access()==1){
						  String newipdabbr=ipdDAO.getIpdAbrivationIds(Integer.parseInt(selectedid));
						  ipdForm.setNewipdabbr(newipdabbr);
						  if(Integer.parseInt(bed.getIpdseqno())>0){
								ipdForm.setIpdseqno(bed.getIpdseqno());
							}else{
								ipdForm.setIpdseqno(selectedid);
							}
					  }else{
					if(Integer.parseInt(bed.getIpdseqno())>0){
						ipdForm.setIpdseqno(bed.getIpdseqno());
						ipdForm.setNewipdabbr(bed.getIpdseqno());
					}else{
						ipdForm.setIpdseqno(selectedid);
						ipdForm.setNewipdabbr(selectedid);
					}
					  }
					UserProfileDAO userProfileDAO = new JDBCUserProfileDAO(connection);
					if(intitalizedObject==0){
						userProfile = userProfileDAO.getUserprofileDetails(Integer.parseInt(bed.getPractitionerid()));
					}
					String practitionername = userProfile.getInitial() + " " + userProfile.getFirstname() + " " + userProfile.getLastname();
					String specializationId=  userProfile.getDiciplineName();
					ipdForm.setDepartment(userProfile.getSpecialization());
					
					if(userProfile.getDoctor()!=null){
						
						 if(!userProfile.getDoctor().equals("") && !userProfile.getDoctor().equals("0")){
							 
							 UserProfile supportiveDoctorDetails= userProfileDAO.getUserprofileDetails(Integer.parseInt(userProfile.getDoctor()));  
							 String supportiveDoctorName= supportiveDoctorDetails.getInitial() + " " + supportiveDoctorDetails.getFirstname() + " " + supportiveDoctorDetails.getLastname();
							 String supportiveQualification=supportiveDoctorDetails.getQualification();
							 ipdForm.setSupportiveDoctorName(supportiveDoctorName);
							 ipdForm.setSupportiveQualification(supportiveQualification);
						 } 
					}
					
					
					
					boolean issystemicreview= masterDAO.isIpdFormFieldActive(specializationId,"Systemic Review");
					boolean history= masterDAO.isIpdFormFieldActive(specializationId,"History");
					boolean obstretic_history = masterDAO.isIpdFormFieldActive(specializationId,"OBSTRETIC HISTORY");
					boolean menstrual_history = masterDAO.isIpdFormFieldActive(specializationId, "MENSTRUAL HISTORY"); 
					boolean substance_history = masterDAO.isIpdFormFieldActive(specializationId, "SUBSTANCE HISTORY");
					boolean verification = masterDAO.isIpdFormFieldActive(specializationId, "Verification");
					boolean pediatric = masterDAO.isIpdFormFieldActive(specializationId, "Paediatric History");
					ipdForm.setNicuaccess(masterDAO.isIpdFormFieldActive(specializationId, "NICU Setting"));
					ipdForm.setNicuaccess(pediatric);
					ipdForm.setVerification(verification);
					ipdForm.setIssystemicreview(issystemicreview);
					ipdForm.setHistory(history);
					ipdForm.setObstretic_history(obstretic_history);
					ipdForm.setMenstrual_history(menstrual_history);
					ipdForm.setSubstance_history(substance_history);
					ipdForm.setPaediatrichist(pediatric);
					
					ipdForm.setEditclientid(bed.getClientid());
					ipdForm.setClientid(bed.getClientid());
					ipdForm.setPractitionerid(practitionername);
					ipdForm.setConditionid(bed.getConditionid());
					ipdForm.setDepartment(bed.getDepartment());
					ipdForm.setSecndryconsult(bed.getSecndryconsult());
					ipdForm.setAddmitedbyuserid(bed.getAddmitedbyuserid());
					
					
					ipdForm.setMaternal_history(bed.getMaternal_history());
					ipdForm.setPerinatal_history(bed.getPerinatal_history());
					
		      		NotAvailableSlot notAvailableSlot= notAvailableSlotDAO.getOTDataByIpd(selectedid);
		      	    ipdForm.setProcedure(notAvailableSlot.getProcedure());
		      	    //ipdForm.setAnesthesia(notAvailableSlot.getAnesthesia());
		      	    //ipdForm.setSurgeon(notAvailableSlot.getSurgeon());
		      		ipdForm.setOtNotes(notAvailableSlot.getOtnotes());
		      		session.setAttribute("ipdotnotes", notAvailableSlot.getOtnotes());
		      		//ipdForm.setAppointmentText(notAvailableSlot.getApmttypetext());
		      		//ipdForm.setAnsintime(notAvailableSlot.getAnsintime());
					
					if(bed.getRefferedby()!=null){
						
						if(bed.getRefferedby().equals("") || bed.getRefferedby().equals("0") ){
							bed.setRefferedby(null);
						}
					}
					
					ipdForm.setRefferedby(bed.getRefferedby());
					
					String wardname=ipdDAO.getIpdWardName(bed.getWardid());
					String bedname = ipdDAO.getIpdBedName(bed.getBedid());
					
					

					ipdForm.setWardid(wardname);
					ipdForm.setBedid(bedname);
					

					ipdForm.setTpid(bed.getTpid());
					
					
					if(ipdForm.getTpid()==null){
						
						ipdForm.setTpid("0");
					}
					
					
					ipdForm.setStatus(bed.getStatus());
					ipdForm.setAddmissionfor(bed.getAddmissionfor());
					ipdForm.setReimbursment(bed.getReimbursment());
					
					if(bed.getSecndryconsult()!=null){
						if(bed.getSecndryconsult().equals("0")){
							bed.setSecndryconsult(null);
						}
					}
					
					
					if(bed.getSecndryconsult()!=null){
						if(!bed.getSecndryconsult().equals("")){
							
							 ArrayList<String> allConsultantList= ipdDAO.getAllSecondaryConsultList(selectedid);  
							 ArrayList<UserProfile> allconsultantlistwithdepart = ipdDAO.getSecConsWithDepartment(selectedid);
							 ipdForm.setAllconsultantlistwithdepart(allconsultantlistwithdepart);
							 ipdForm.setAllConsultantList(allConsultantList);
							 bed.setAllConsultantList(allConsultantList);
						}
					} else {
						
						practitionername="";
					}
					
					
					//userProfile = userProfileDAO.getUserprofileDetails(Integer.parseInt(bed.getPractitionerid()));
					Master discipline=masterDAO.getDisciplineData(userProfile.getDiciplineName());
					ipdForm.setDiscipline(discipline.getDiscipline());
					ipdForm.setDoctor_name(userProfile.getInitial()+" "+userProfile.getFirstname()+" "+userProfile.getLastname());
					ipdForm.setDoctor_phone(userProfile.getMobile());
					ipdForm.setQualification(DateTimeUtils.isNull(userProfile.getQualification()));
					ipdForm.setUseregno(userProfile.getRegisterno());
					ArrayList<Master> qualificationList= new ArrayList<Master>();
					
					for(String str:ipdForm.getQualification().split(",")){
						Master master= new Master();
						master.setName(str);
						qualificationList.add(master);
					}
					if(qualificationList.size()==0){
						Master master= new Master();
						master.setName(ipdForm.getQualification());
						qualificationList.add(master);
					}
					ipdForm.setQualificationList(qualificationList);
					//  add mlc ref dr name
					String mlcdrname = userProfileDAO.getReferalDrName(bed.getMlcrefdoctor());
					ipdForm.setMlcrefdoctor(mlcdrname);
					
					ipdForm.setSecndryconsult(practitionername);
					ipdForm.setMlcno(bed.getMlcno());
					ipdForm.setAdmissiondetails(bed.getAdmissiondetails());
					String val = bed.getSuggestedtrtment();
					ipdForm.setSuggestedtrtment(bed.getSuggestedtrtment());
					ipdForm.setHosp(bed.getHosp());
					ipdForm.setPackagename(bed.getPackagename());
					//chiefcomplains, presentillness, pasthistory, personalhist, familyhist, onexamination, treatmentepisodeid lokeshnew
					ipdForm.setChiefcomplains(bed.getChiefcomplains());
					ipdForm.setPresentillness(bed.getPresentillness());
					ipdForm.setPasthistory(bed.getPasthistory());
					ipdForm.setPersonalhist(bed.getPersonalhist());
					ipdForm.setFamilyhist(bed.getFamilyhist());
					ipdForm.setOnexamination(bed.getOnexamination());
					
					String treatmentname = bedDao.getTreatmentName(bed.getTreatmentepisodeid());
					
					ipdForm.setTreatmentepisodeid(treatmentname);

					ipdForm.setSuggestoper(bed.getSuggestoper());
					ipdForm.setSystdepartment(bed.getSystdepartment());
					ipdForm.setFpcondition(bed.getFpcondition());
					ipdForm.setFpnotest(bed.getFpnotest());
					ipdForm.setNauseacondition(bed.getNauseacondition());
					ipdForm.setNauseanotes(bed.getNauseanotes());
					ipdForm.setFnucondition(bed.getFnucondition());
					ipdForm.setFnunotes(bed.getFnunotes());
					ipdForm.setSmcondition(bed.getSmcondition());
					ipdForm.setSmnotes(bed.getSmnotes());
					ipdForm.setChestpaincond(bed.getChestpaincond());
					ipdForm.setChestpainnotes(bed.getChestpainnotes());
					ipdForm.setDimvisioncond(bed.getDimvisioncond());
					ipdForm.setDimvisionnotes(bed.getDimvisionnotes());
					
					//dimvisionnotes, hgucondition, hgunotes, hmcondition, hmnotes, jointpaincond, jointpainnotes, 
					ipdForm.setHgucondition(bed.getHgucondition());
					ipdForm.setHgunotes(bed.getHgunotes());
					ipdForm.setHmcondition(bed.getHmcondition());
					ipdForm.setHmnotes(bed.getHmnotes());
					ipdForm.setJointpaincond(bed.getJointpaincond());
					ipdForm.setJointpainnotes(bed.getJointpainnotes());
					
					//constipationcond, constpationnotes, specialnotes, edemafeetcondi, edemafeetnotes, hematuriacondi, 
					ipdForm.setConstipationcond(bed.getConstipationcond());
					ipdForm.setConstpationnotes(bed.getConstpationnotes());
					ipdForm.setSpecialnotes(bed.getSpecialnotes());
					ipdForm.setEdemafeetcondi(bed.getEdemafeetcondi());
					ipdForm.setEdemafeetnotes(bed.getEdemafeetnotes());
					ipdForm.setHematuriacondi(bed.getHematuriacondi());
					ipdForm.setHematurianotes(bed.getHematurianotes());
					
					//hematurianotes, bmcondition, bmnotes, oliguriacondi, oligurianotes, pstgucondi, pstgunotes, 
					ipdForm.setBmcondition(bed.getBmcondition());
					ipdForm.setBmnotes(bed.getBmnotes());
					ipdForm.setOliguriacondi(bed.getOliguriacondi());
					ipdForm.setOligurianotes(bed.getOligurianotes());
					ipdForm.setPstgucondi(bed.getPstgucondi());
					ipdForm.setPstgunotes(bed.getPstgunotes());
					
					//bmecondition, bmenotes, tnecondition, tnenotes, weaknesscondi, weaknessnotes, ihcondition, ihnotes
					ipdForm.setBmecondition(bed.getBmecondition());
					ipdForm.setBmenotes(bed.getBmenotes());
					ipdForm.setTnecondition(bed.getTnecondition());
					ipdForm.setTnenotes(bed.getTnenotes());
					ipdForm.setWeaknesscondi(bed.getWeaknesscondi());
					ipdForm.setWeaknessnotes(bed.getWeaknessnotes());
					ipdForm.setIhcondition(bed.getIhcondition());
					ipdForm.setIhnotes(bed.getIhnotes());
					
					ipdForm.setAdmission_reason(bed.getAdmission_reason());
		    		ipdForm.setEarlierinvest(bed.getEarlierinvest());
		    		ipdForm.setAlergies(bed.getAlergies());
		    		
		    		
		    		ipdForm.setBirthhist(bed.getBirthhist());
		    		ipdForm.setDiethist(bed.getDiethist());
		    		ipdForm.setDevelopmenthist(bed.getDevelopmenthist());
		    		ipdForm.setEmmunizationhist(bed.getEmmunizationhist());
		    		//lokesh
		    		ipdForm.setHeadcircumference(bed.getHeadcircumference());
		    		ipdForm.setMidarmcircumference(bed.getMidarmcircumference());
		    		ipdForm.setLength(bed.getLength());
		    		ipdForm.setWtaddmission(bed.getWtaddmission());
		    		ipdForm.setWtdischarge(bed.getWtdischarge());
					
		    		//giddinesscondition,giddinessnotes,unconcondition,unconnotes
		    		ipdForm.setGiddinesscondition(bed.getGiddinesscondition());
		    		ipdForm.setGiddinessnotes(bed.getGiddinessnotes());
		    		ipdForm.setUnconcondition(bed.getUnconcondition());
		    		ipdForm.setUnconnotes(bed.getUnconnotes());
		    		 
		    		
		    		ipdForm.setWtonbirth(bed.getWtonbirth());
		    		ipdForm.setGstureage(bed.getGstureage());
		    		
		  /* if(!bed.getEmmunizationhist().equals("")||!bed.getBirthhist().equals("")||!bed.getDiethist().equals("")||!bed.getDevelopmenthist().equals("")){
			   bed.setPeditric(true);
		   }*/
		    		
		    		
					ipdForm.setId(Integer.parseInt(selectedid));
					if(intitalizedObject==0){
						bed1 = ipdDAO.getDischargeData(bed.getTreatmentepisodeid());
					}
					ipdForm.setChkDischarge(bed1.isChkDischarge());
					ipdForm.setDischrgeOutcomes(bed1.getDischrgeOutcomes());
					ipdForm.setDischargeStatus(bed1.getDischargeStatus());
					ipdForm.setHospitalcourse(bed1.getHospitalcourse());
					//lokesh
					/*if(bed1.getHospitalcourse()!=null){
					if(bed1.getHospitalcourse().contains("<div>")){
						String hscourse= bed1.getHospitalcourse();
						hscourse=hscourse.replaceAll("<div>", "");
						hscourse=hscourse.replaceAll("</div>", "");
						bed1.setHospitalcourse(hscourse);
					}
					}*/
					ipdForm.setDiscadvnotes(bed1.getDiscadvnotes());
					String treatmentgiven1=bed1.getTreatmentgiven().replace("<br>", "");
					ipdForm.setTreatmentgiven(treatmentgiven1);
					ipdForm.setFindondischarge(bed1.getFindondischarge());
					if(loginInfo.isParihar()) {
						ArrayList<Bed>invtypelist=new ArrayList<>();
						//String findings=ipdDAO.getInvestigationNameFindings(bed.getClientid(),bed.getAdmissiondate(),bed1.getDischargedate());
						if(!bed1.getDischargedate().equals("")) {
						invtypelist=ipdDAO.getInvTypelist(bed.getClientid(),bed.getAdmissiondate(),bed1.getDischargedate());
						ipdForm.setInvtypelist(invtypelist);
					  }
						Bed bed11=new Bed();
						if(invtypelist.size()>0) {
							bed11=invtypelist.get(invtypelist.size()-1);
						}
						//Bed bed11=invtypelist.get(invtypelist.size()-1);
						ipdForm.setInvestigation(bed11.getInvestigation_name());
						ipdForm.setDis_investigation(bed1.getInvestigation());
					}else {
						ipdForm.setInvestigation(bed1.getInvestigation());
					}
					//ipdForm.setInvestigation(bed1.getInvestigation());
					ipdForm.setOtNotes(bed1.getOtNotes());
					ipdForm.setAppointmentText(bed1.getAppointmentText());
					ipdForm.setAnesthesia(bed1.getAnesthesia());
					ipdForm.setSurgeon(bed1.getSurgeon());
					ipdForm.setAnesthesiologist(bed1.getAnesthesiologist());
					ipdForm.setDischargebyid(bed1.getDischargebyid());
					ipdForm.setSurgicalnotes(bed.getSurgicalnotes());
					//peditric 
					
					ipdForm.setEmergencydetail(bed1.getEmergencydetail());
					
					ipdForm.setDeathnote(bed1.getDeathnote());
					ipdForm.setTreatmenthistory(bed.getTreatmenthistory());
					
					if(ipdForm.getDeathnote()!=null){
						   
						   if(ipdForm.getDeathnote().equals("") || ipdForm.getDeathnote().equals("<br>")){
							     ipdForm.setDeathnote(null);
							 } 
						   
					   }
					
					if(ipdForm.getSurgicalnotes()!=null){
						   
						   if(ipdForm.getSurgicalnotes().equals("") || ipdForm.getSurgicalnotes().equals("<br>")){
							     ipdForm.setSurgicalnotes(null);
							 } 
						   
					   }
					
					if(ipdForm.getTreatmentgiven()!=null){
						   
						   if(ipdForm.getTreatmentgiven().equals("") || ipdForm.getTreatmentgiven().equals("<br>")){
							     ipdForm.setTreatmentgiven(null);
							 } 
						   
					   }
					if(ipdForm.getFindondischarge()!=null){
						   
						   if(ipdForm.getFindondischarge().equals("") || ipdForm.getFindondischarge().equals("<br>")){
							     ipdForm.setFindondischarge(null);
							 } 
						   
					   }
					if(ipdForm.getInvestigation()!=null){
						   
						   if(ipdForm.getInvestigation().equals("") || ipdForm.getInvestigation().equals("<br>")){
							     ipdForm.setInvestigation(null);
							 } 
						   
					   }
					if(ipdForm.getOtNotes()!=null){
						   
						   if(ipdForm.getOtNotes().equals("") || ipdForm.getOtNotes().equals("<br>")){
							     ipdForm.setOtNotes(null);
							 } 
						   
					}
					if(ipdForm.getAppointmentText()!=null){
						   
						   if(ipdForm.getAppointmentText().equals("") || ipdForm.getAppointmentText().equals("<br>")){
							     ipdForm.setAppointmentText(null);
							 } else{
								 String data1="";
								String[] data = ipdForm.getAppointmentText().split(",");
								for (int i = 0; i < data.length; i++) {
									if(i!=0){
										if(data1.equals("")){
											data1 = data[i];
										}else{
											data1 = data1+data[i];
										}
										
									}
								}
								ipdForm.setAppointmentText(data1);
							 }
						   
					}
					if(ipdForm.getSurgeon()!=null){
						   
						   if(ipdForm.getSurgeon().equals("") || ipdForm.getSurgeon().equals("<br>")){
							     ipdForm.setSurgeon(null);
							 } 
						   
					}
					if(ipdForm.getAnesthesia()!=null){
						   
						   if(ipdForm.getAnesthesia().equals("") || ipdForm.getAnesthesia().equals("<br>")){
							     ipdForm.setAnesthesia(null);
							 } 
						   
					}
					if(ipdForm.getAnesthesiologist()!=null){
						   
						   if(ipdForm.getAnesthesiologist().equals("") || ipdForm.getAnesthesiologist().equals("<br>")){
							     ipdForm.setAnesthesiologist(null);
							 } 
						   
					}
					
					ipdForm.setChkDischarge(bed1.isChkDischarge());
					ipdForm.setHospitalcourse(bed1.getHospitalcourse());
					ipdForm.setHospitalcourse(bed1.getHospitalcourse());
					if(ipdForm.getHospitalcourse()!=null){
					  if(ipdForm.getHospitalcourse().equals("") || ipdForm.getHospitalcourse().equals("<br>")){
					     ipdForm.setHospitalcourse(null);
					  } 
					}
					   
					   ipdForm.setDiscadvnotes(bed1.getDiscadvnotes());
					   if(ipdForm.getDiscadvnotes()!=null){
					    if(ipdForm.getDiscadvnotes().equals("") || ipdForm.getDiscadvnotes().equals("<br>")){
					     ipdForm.setDiscadvnotes(null);
					    } 
					   }
					   ipdForm.setExample(bed1.getExample());
					   if(ipdForm.getExample()!=null){
						   
						   if(ipdForm.getExample().equals("") || ipdForm.getExample().equals("<br>")){
							     ipdForm.setExample(null);
							 } 
						   
					   }
					   
					  
					 //gynic details
					   
					    boolean issubstance=false;
					   	
			    		ipdForm.setAlcohal(bed.getAlcohal());
			    		ipdForm.setDrugs(bed.getDrugs());
			    		ipdForm.setOther_medication(bed.getOther_medication());
			    		ipdForm.setTobaco(bed.getTobaco());
			    		ipdForm.setTobaconotes(bed.getTobaconotes());
			    		ipdForm.setSmoking(bed.getSmoking());
			    		if(ipdForm.getAlcohal()!=null){
			    			
			    			if(ipdForm.getAlcohal().equals("") || ipdForm.getAlcohal().equals("No") ){
			    				 ipdForm.setAlcohal(null);
			    			} else {
			    				issubstance= true;
			    			}
			    		}
			    		if(ipdForm.getDrugs()!=null){
			    			
			    			if(ipdForm.getDrugs().equals("") || ipdForm.getDrugs().equals("No") ){
			    				 ipdForm.setDrugs(null);
			    			}else {
			    				issubstance= true;
			    			}
			    		}
			    		if(ipdForm.getOther_medication()!=null){
			    			
			    			if(ipdForm.getOther_medication().equals("") || ipdForm.getOther_medication().equals("No") ){
			    				 ipdForm.setOther_medication(null);
			    			}else {
			    				issubstance= true;
			    			}
			    		}
			    		if(ipdForm.getTobaco()!=null){
			    			
			    			if(ipdForm.getTobaco().equals("") || ipdForm.getTobaco().equals("No") ){
			    				 ipdForm.setTobaco(null);
			    			}else {
			    				issubstance= true;
			    			}
			    		}
			    		if(ipdForm.getTobaconotes()!=null){
			    			
			    			if(ipdForm.getTobaconotes().equals("") || ipdForm.getTobaconotes().equals("No") ){
			    				 ipdForm.setTobaconotes(null);
			    			}else {
			    				issubstance= true;
			    			}
			    		}
			    		if(ipdForm.getSmoking()!=null){
			    			
			    			if(ipdForm.getSmoking().equals("") || ipdForm.getSmoking().equals("No") ){
			    				 ipdForm.setSmoking(null);
			    			}else {
			    				issubstance= true;
			    			}
			    		}
			    		
			    		if(issubstance){
			    			ipdForm.setSubstancehistory("");
			    		} else {
			    			ipdForm.setSubstancehistory(null);
			    		}
			    		
			    		boolean ismenstrual=false;
			    		
			    		ipdForm.setAge_menarche(bed.getAge_menarche());
			    		ipdForm.setLmp(bed.getLmp());
			    		ipdForm.setLlmp(bed.getLlmp());
			    		ipdForm.setPmc(bed.getPmc());
			    		
			    		if(ipdForm.getAge_menarche()!=null){
			    			if(ipdForm.getAge_menarche().equals("0") || ipdForm.getAge_menarche().equals("")){
			    				ipdForm.setAge_menarche(null);
			    			} else {
			    				ismenstrual=true;
			    			}
			    		} 
			    		if(ipdForm.getLmp()!=null){
			    			if(ipdForm.getLmp().equals("0") || ipdForm.getLmp().equals("")){
			    				ipdForm.setLmp(null);
			    			} else {
			    				ismenstrual=true;
			    			}
			    		} 
			    		if(ipdForm.getLlmp()!=null){
			    			if(ipdForm.getLlmp().equals("0") || ipdForm.getLlmp().equals("")){
			    				ipdForm.setLlmp(null);
			    			} else {
			    				ismenstrual=true;
			    			}
			    		} 
			    		if(ipdForm.getPmc()!=null){
			    			if(ipdForm.getPmc().equals("0") || ipdForm.getPmc().equals("")){
			    				ipdForm.setPmc(null);
			    			} else {
			    				ismenstrual=true;
			    			}
			    		}
			    		
			    		
			    		ipdForm.setCycle_length(bed.getCycle_length());
			    		ipdForm.setDuration_flow(bed.getDuration_flow());
			    		ipdForm.setAmount_flow(bed.getAmount_flow());
			    		ipdForm.setDysmenorrhea(bed.getDysmenorrhea());
			    		ipdForm.setDyspareunia(bed.getDyspareunia());
			    		ipdForm.setHopassing_clots(bed.getHopassing_clots());
			    		if(ipdForm.getCycle_length()!=null){
			    			if(ipdForm.getCycle_length().equals("0") || ipdForm.getCycle_length().equals("")){
			    				ipdForm.setCycle_length(null);
			    			} else {
			    				ismenstrual=true;
			    			}
			    		}
			    		if(ipdForm.getDuration_flow()!=null){
			    			if(ipdForm.getDuration_flow().equals("0") || ipdForm.getDuration_flow().equals("")){
			    				ipdForm.setDuration_flow(null);
			    			} else {
			    				ismenstrual=true;
			    			}
			    		}
			    		if(ipdForm.getAmount_flow()!=null){
			    			if(ipdForm.getAmount_flow().equals("0") || ipdForm.getAmount_flow().equals("")){
			    				ipdForm.setAmount_flow(null);
			    			} else {
			    				ismenstrual=true;
			    			}
			    		}
			    		if(ipdForm.getDysmenorrhea()!=null){
			    			if(ipdForm.getDysmenorrhea().equals("0") || ipdForm.getDysmenorrhea().equals("")){
			    				ipdForm.setDysmenorrhea(null);
			    			} else {
			    				ismenstrual=true;
			    			}
			    		}
			    		if(ipdForm.getDyspareunia()!=null){
			    			if(ipdForm.getDyspareunia().equals("0") || ipdForm.getDyspareunia().equals("")){
			    				ipdForm.setDyspareunia(null);
			    			} else {
			    				ismenstrual=true;
			    			}
			    		}
			    		if(ipdForm.getHopassing_clots()!=null){
			    			if(ipdForm.getHopassing_clots().equals("0") || ipdForm.getHopassing_clots().equals("")){
			    				ipdForm.setHopassing_clots(null);
			    			} else {
			    				ismenstrual=true;
			    			}
			    		}
			    		
			    		
			    		ipdForm.setWhite_disc_itching(bed.getWhite_disc_itching());
			    		ipdForm.setIntercourse_freq(bed.getIntercourse_freq());
			    		ipdForm.setGalactorrea(bed.getGalactorrea());
			    		if(ipdForm.getWhite_disc_itching()!=null){
			    			if(ipdForm.getWhite_disc_itching().equals("0") || ipdForm.getWhite_disc_itching().equals("")){
			    				ipdForm.setWhite_disc_itching(null);
			    			} else {
			    				ismenstrual=true;
			    			}
			    		}
			    		if(ipdForm.getIntercourse_freq()!=null){
			    			if(ipdForm.getIntercourse_freq().equals("0") || ipdForm.getIntercourse_freq().equals("")){
			    				ipdForm.setIntercourse_freq(null);
			    			} else {
			    				ismenstrual=true;
			    			}
			    		}
			    		if(ipdForm.getGalactorrea()!=null){
			    			if(ipdForm.getGalactorrea().equals("0") || ipdForm.getGalactorrea().equals("")){
			    				ipdForm.setGalactorrea(null);
			    			} else {
			    				ismenstrual=true;
			    			}
			    		}
			    		
			    		
			    		
			    		ipdForm.setHo_contraception(bed.getHo_contraception());
			    		ipdForm.setRubella_vaccine(bed.getRubella_vaccine());
			    		ipdForm.setMenopause(bed.getMenopause());
			    		if(ipdForm.getHo_contraception()!=null){
			    			if(ipdForm.getHo_contraception().equals("0") || ipdForm.getHo_contraception().equals("")){
			    				ipdForm.setHo_contraception(null);
			    			} else {
			    				ismenstrual=true;
			    			}
			    		}
			    		if(ipdForm.getRubella_vaccine()!=null){
			    			if(ipdForm.getRubella_vaccine().equals("0") || ipdForm.getRubella_vaccine().equals("")){
			    				ipdForm.setRubella_vaccine(null);
			    			} else {
			    				ismenstrual=true;
			    			}
			    		}
			    		if(ipdForm.getMenopause()!=null){
			    			if(ipdForm.getMenopause().equals("0") || ipdForm.getMenopause().equals("")){
			    				ipdForm.setMenopause(null);
			    			} else {
			    				ismenstrual=true;
			    			}
			    		}
			    		
			    		if(ismenstrual){
			    			ipdForm.setMenstraulhistory("");
			    		} else {
			    			ipdForm.setMenstraulhistory(null);
			    		}
			    		
			    		
			    		ipdForm.setLive_boys(bed.getLive_boys());
			    		ipdForm.setLive_girls(bed.getLive_girls());
			    		ipdForm.setStillbirths(bed.getStillbirths());
			    		ipdForm.setAbortions(bed.getAbortions());
			    		ipdForm.setDeath_children(bed.getDeath_children());
			  		   
			    		ArrayList<Bed> gynicobsList= bedDao.getGynicObsList(selectedid);
			    		ipdForm.setGynicobsList(gynicobsList);
					   
			    		
			    		//parity_aboration_notes,p,l,a,d
			    		ipdForm.setParity_abortion_notes(bed.getParity_abortion_notes());
			    		if(ipdForm.getParity_abortion_notes()!=null){
			    			
			    			if(ipdForm.getParity_abortion_notes().equals("") || ipdForm.getParity_abortion_notes().equals("0") ){
			    				ipdForm.setParity_abortion_notes("");
			    			}
			    		}
			    		ipdForm.setP(bed.getP());
			    		ipdForm.setL(bed.getL());
			    		ipdForm.setA(bed.getA());
			    		ipdForm.setD(bed.getD());
			    		
			    		
					boolean ishistory=false;
					
					
					if(bed.getAddmissionfor()!=null  ){
						
						if(bed.getAddmissionfor().equals("") || bed.getAddmissionfor().equals("<br>")){
							bed.setAddmissionfor(null);
						}
					}
					if(bed.getAlergies()!=null){
						
						if(bed.getAlergies().equals("") || bed.getAlergies().equals("<br>")){
							bed.setAlergies(null);
						}
					}
					if(bed.getPackagename()!=null){
						
						if(bed.getPackagename().equals("") || bed.getPackagename().equals("<br>")){
							bed.setPackagename(null);
						}
						
					}
					if(bed.getAdmission_reason()!=null){
						
						if(bed.getAdmission_reason().equals("") || bed.getAdmission_reason().equals("<br>")){
							bed.setAdmission_reason(null);
						}
					}
					if(bed.getChiefcomplains()!=null){
						
						if(bed.getChiefcomplains().equals("") || bed.getChiefcomplains().equals("<br>")){
							bed.setChiefcomplains(null);
						}
					}
					if(bed.getPasthistory()!=null){
						
						if(bed.getPasthistory().equals("") || bed.getPasthistory().equals("<br>")){
							bed.setPasthistory(null);
						} else {
							ishistory=true;
						}
					}
					if(bed.getFamilyhist()!=null){
						
						if(bed.getFamilyhist().equals("") || bed.getFamilyhist().equals("<br>")){
							bed.setFamilyhist(null);
						} else {
							ishistory=true;
						}
					}
					if(bed.getPersonalhist()!=null){
						
						if(bed.getPersonalhist().equals("") || bed.getPersonalhist().equals("<br>")){
							bed.setPersonalhist(null);
						} else {
							ishistory=true;
						}
					}
					if(bed.getOnexamination()!=null){
						
						if(bed.getOnexamination().equals("") || bed.getOnexamination().equals("<br>")){
							bed.setOnexamination(null);
						} else {
							ishistory=true;
						}
						
					}
					if(bed.getSurgicalnotes()!=null){
						   
						   if(bed.getSurgicalnotes().equals("") || bed.getSurgicalnotes().equals("<br>")){
							   bed.setSurgicalnotes(null);
							 } else {
								 ishistory=true;
							 }
						   
					   }
					
					//  05 June 2018 to set content of History textbox data
					if(ipdForm.getExample()!=null){
						bed.setExample(ipdForm.getExample());
						ishistory=true;
					}
					
					
					if(bed.getSuggestedtrtment()!=null){
						
						if(bed.getSuggestedtrtment().equals("") || bed.getSuggestedtrtment().equals("<br>")){
							bed.setSuggestedtrtment(null);
						}else {
						}
					}
					if(bed.getSpecialnotes()!=null){
						
						if(bed.getSpecialnotes().equals("") || bed.getSpecialnotes().equals("<br>")){
							bed.setSpecialnotes(null);
						}
						
					}
					if(bed.getEarlierinvest()!=null){
						
						if(bed.getEarlierinvest().equals("") || bed.getEarlierinvest().equals("<br>")){
							bed.setEarlierinvest(null);
						}
					}	
					if(bed.getPresentillness()!=null){
						
						if(bed.getPresentillness().equals("") || bed.getPresentillness().equals("<br>")){
							bed.setPresentillness(null);
						}
					}	
					
					
					if(bed.getPresentillness()==null && bed.getChiefcomplains()==null && bed.getAdmission_reason()==null){
						
						 bed.setSummary(null); 
					} else {
						bed.setSummary("");
					}
					
					if(!ishistory){
						bed.setHistory(null);
					} else {
						bed.setHistory("");
					}
					
					
					
					//set treatment episode
					ClientDAO clientDAO = new JDBCClientDAO(connection);
					if(intitalizedObject==0){
						client = clientDAO.getClientDetails(ipdForm.getClientid());
					}
					String fullname = client.getTitle() + " " + client.getFirstName() + " "+client.getMiddlename()+" " + client.getLastName();
					ipdForm.setClient(fullname);
					ipdForm.setRegno(client.getAbrivationid());
					String whopay=client.getWhopay();
					ipdForm.setAbrivationid(client.getAbrivationid());
					ipdForm.setPatientIdAbrivation(client.getPatientIdAbrivation());
					if(whopay==null){
						whopay="";
					}
					if(!whopay.equals("Client")){
						ThirdPartyDAO thirdPartyDAO = new JDBCThirdPartyDAO(connection);
						if(DateTimeUtils.numberCheck(bed.getTpid()).equals("0") && (!DateTimeUtils.numberCheck(bed.getBedid()).equals("0"))){
							String typeName = client.getTypeName();
							if(!DateTimeUtils.numberCheck(typeName).equals("0")){
								bed.setTpid(typeName);
								ipdForm.setTpid(bed.getTpid());
								int result = ipdDAO.updateAdm_Tp(selectedid,typeName);
								result = ipdDAO.updateAdmNew_Tp(selectedid,typeName);
							}
							
						}
						ThirdParty thirdParty = thirdPartyDAO.getThirdPartyDetails(bed.getTpid());
						ipdForm.setThirdParty(thirdParty.getCompanyName());
					}else{
						ipdForm.setThirdParty("Self");
					}
					String birthtime= "00:00:00";
					if(!DateTimeUtils.isNull(client.getBirthtime()).equals("")){
						birthtime=client.getBirthtime().replaceAll(" ", "");
					}
							
					 String hh1="";
					 String mm1="";
					 String apmpm1="";
					 if(!birthtime.equals("00:00:00")){
						 String time[] = birthtime.split(":");
						 hh1 = time[0];
						 hh1=hh1.replaceAll(" ", "");
						 mm1 = time[1];
						 mm1=mm1.replaceAll(" ", "");
						 	int hourOfDay1=Integer.parseInt(hh1);
						   int minute1=Integer.parseInt(mm1);
						    apmpm1 =  ((hourOfDay1 > 12) ? hourOfDay1 % 12 : hourOfDay1) + ":" + (minute1 < 10 ? ("0" + minute1) : minute1) + " " + ((hourOfDay1 >= 12) ? "PM" : "AM");	 
					 }
					//lokesh
					String agegender="";
					String dob = client.getDob();
					String age = DateTimeUtils.getAge1(client.getDob());
				/*	String age1[]= age.split("(?<=\\D)(?=\\d)|(?<=\\d)(?=\\D)");
					age= age1[0];
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
					}*/
					if(!birthtime.equals("00:00:00") && !loginInfo.isBalgopal()){
						agegender = age +"("+apmpm1+")"+ " / " + client.getGender();
					}else{
					agegender = age + " / " + client.getGender();	
					}
					ipdForm.setAge(age);
					ipdForm.setAgegender(agegender);
					ipdForm.setRelativename(client.getEmergencyContName());
		    		ipdForm.setRelationcont(client.getEmergencyContNo());
		    		ipdForm.setRelation(client.getRelation());
		    		ipdForm.setGender(client.getGender());
		    		
		    		boolean isfamilyd=false;
		    		
		    		if(ipdForm.getRelativename()!=null){
		    			 
		    			if(ipdForm.getRelativename().equals("")){
		    				 
		    				ipdForm.setRelativename(null);
		    			}
		    		}

		    		if(ipdForm.getRelationcont()!=null){
		    			 
		    			if(ipdForm.getRelationcont().equals("")){
		    				 
		    				ipdForm.setRelationcont(null);
		    			}
		    		}

		    		if(ipdForm.getRelation()!=null){
		    			 
		    			if(ipdForm.getRelation().equals("")){
		    				 
		    				ipdForm.setRelation(null);
		    			}
		    		}
		    		
		    		if(ipdForm.getRelativename()==null&&ipdForm.getRelationcont()==null&&ipdForm.getRelation()==null){
		    			isfamilyd=true;
		    		}
		    		/*if(ipdForm.getRelationcont()==null){
		    			isfamilyd=true;
		    		}
		    		if(ipdForm.getRelation()==null){
		    			isfamilyd=true;
		    		}
		    		*/
		    		if(isfamilyd){
		    			ipdForm.setFamilyDetails("");
		    		} else {
		    			ipdForm.setFamilyDetails("ee");
		    		}
		    		
		    		String numcount=ipdDAO.getNumofAdmissionCount(ipdForm.getClientid());
		    		ipdForm.setNum_admission(numcount);
		    		ipdForm.setDob(client.getDob());
		    		ipdForm.setAddress(client.getAddress()+", "+client.getTown()+"-"+client.getPostCode()+", "+DateTimeUtils.isNull(client.getCounty())  );
					if(client.getAlternate_mobno()!=null){
						ipdForm.setContact(client.getMobNo()+","+client.getAlternate_mobno());
					}else {
						ipdForm.setContact(client.getMobNo());
					}
		    		
					
		    		ArrayList<Bed> bedLogList=ipdLogDAO.getBedChangeLogListNew(bed.getClientid(),selectedid);
					ipdForm.setBedLogList(bedLogList);
					
					if(bedLogList.size()>0){
						bedLogList.get(0).setStatus("1");
					}
		    		
		    		String payby = client.getWhopay();
					if(client.getWhopay().equals("Self")){
						payby = "Client";
					}
					TreatmentEpisodeDAO treatmentEpisodeDAO = new JDBCTreatmentEpisode(connection);
					ClinicDAO clinicDAO=new JDBCClinicDAO(connection);
					ArrayList<TreatmentEpisode> treatmentEpisodeList = treatmentEpisodeDAO.getIpdTreatmentEpisodeList(ipdForm.getClientid(), payby);
					ipdForm.setTreatmentEpisodeList(treatmentEpisodeList);
					
					ipdForm.setTreatmentEpisode(bed.getTreatmentepisodeid());
					String admissiondate = bed.getAdmissiondate();
					String[] data = admissiondate.split(" ");
					String data2 = DateTimeUtils.getCommencingDate1(data[0]);
					String data3 = data2 +" "+ data[1];
					//ipdForm.setAdmissiondate(bed.getAdmissiondate());
					if(loginInfo.isBalgopal()){
						String time[]=data[1].split(":"); 
						   int hourOfDay=(Integer.parseInt(time[0]));
						   int minute=(Integer.parseInt(time[1]));
						   String apmpm =  ((hourOfDay > 12) ? hourOfDay % 12 : hourOfDay) + ":" + (minute < 10 ? ("0" + minute) : minute) + " " + ((hourOfDay >= 12) ? "PM" : "AM");
						   data3= DateTimeUtils.getCommencingDate1(data[0])+" "+apmpm;
					}
					if(loginInfo.isMbbs()) {
						ipdForm.setAdmissiondate(data2);
					}else {
						ipdForm.setAdmissiondate(data3);
					}
					//ipdForm.setAdmissiondate(data3);
				
				/*	Collection<Bed> conditions=bedDao.getConditionList(bed.getConditionid());
					ipdForm.setConditions(conditions);*/
					
					Clinic clinic = clinicDAO.getCliniclistDetails(loginInfo.getClinicid());
					ipdForm.setClinicLogo(clinic.getUserImageFileName());
					
					
					String treatmentName=bedDao.getTreatmentName(bed.getTreatmentepisodeid());
					  ipdForm.setTreatmentEpisode(treatmentName);
					  ArrayList<Bed>ipdConditionids = bedDao.getIpdConditionList(selectedid);
					  
					  ArrayList<String> ipdconditionnames=bedDao.getConditionNameList(ipdConditionids);
					  
					  ArrayList<Client> ipdCondtitionList = clientDAO.getTreatmentTypeList();
					  session.setAttribute("ipdConditionids", ipdConditionids);
					  session.setAttribute("ipdCondtitionList", ipdconditionnames);
					  session.setAttribute("bed", bed);
					
					  
//					  ArrayList<Priscription>admissionPriscList = ipdDAO.getAdmissionPrescList(selectedid);
//					   if(admissionPriscList.size()>0){
//						   Priscription pr= admissionPriscList.get(admissionPriscList.size()-1);
//						   ipdForm.setStrengthflag(pr.isStrengthflag());
//						   ipdForm.setRemarkflag(pr.isRemarkflag());
//						   ipdForm.setQuantityflag(pr.isStrengthflag());
//					   }
					  
//					   ipdForm.setAdmissionPriscList(admissionPriscList);
//					   session.setAttribute("dischargePriscList",admissionPriscList);
					   
					   String ids =ipdDAO.getAdmissionParentPriscIds(selectedid);
						
						ArrayList<Priscription> admissionPriscList = ipdDAO.getAdmissionPriscList(ids);
						
						if(admissionPriscList.size()>0){
							Priscription pr= admissionPriscList.get(admissionPriscList.size()-1);
							ipdForm.setStrengthflag(pr.isStrengthflag());
							ipdForm.setRemarkflag(pr.isRemarkflag());
							ipdForm.setQuantityflag(pr.isStrengthflag());
						}
						//String discadvoice=ipdDAO.getDIscPrisc(selectedid);
						//ipdForm.setAdvoice(discadvoice);
						
						ipdForm.setAdmissionPriscList(admissionPriscList);
						session.setAttribute("dischargePriscList",admissionPriscList);
					   
					   
					  ipdForm.setMothername(client.getMothername());
					  ipdForm.setFathername(client.getFathername());
					  ipdForm.setBirthplace(client.getBirthplace());
					  ipdForm.setMlccase(bed.getMlccase());
					  ipdForm.setAgeonadmn(bed.getAgeonAdmn());
					 /* if(bed.getMlccase().equals("1")){
						  
					  }*/
					 if(ipdForm.getDischargedate()!=null){
						 if(!ipdForm.getDischargedate().equals("")){
							 String c=ipdForm.getDischargedate();
							 String h[]= c.split(" ");
							 c= DateTimeUtils.getCommencingDate1(h[0]);
							 String d=DateTimeUtils.getAge1onAddmission(ipdForm.getDob(), c);
							 ipdForm.setAgeondischarge(d);
						 }
					 }
					 
				}
				catch(Exception e) {
					e.printStackTrace();
				}
			
		}	
		 
		public String ipdreport()throws Exception{
			Connection connection = null;
			try {
				connection = Connection_provider.getconnection();
				IpdDAO ipdDAO = new JDBCIpdDAO(connection);
				String fromdate = ipdForm.getFromdate();
				String todate = ipdForm.getTodate();
				String doctorid=ipdForm.getPractitionerid();
				NotAvailableSlotDAO notAvailableSlotDAO = new JDBCNotAvailableSlotDAO(connection);
				
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
				ArrayList<DiaryManagement> userList = notAvailableSlotDAO.getUserList(loginInfo.getId());
				ipdForm.setUserList(userList);
				
				int count = ipdDAO.getTotalIPDcount(fromdate,todate,doctorid);
				
				ipdForm.setTotalRecords(count);
				ArrayList<Bed> ipdReport = ipdDAO.getIpdReportKalmegh(fromdate,todate,doctorid);
				ipdForm.setIpdReport(ipdReport);
				
				ArrayList<DiaryManagement> admitteduserlist = notAvailableSlotDAO.getadmittedbyUserList();
				ipdForm.setAdmittedbyuserList(admitteduserlist);
				
				String datetime=DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone());
				String temp[] = datetime.split(" ");
				String date = DateTimeUtils.getCommencingDate1(temp[0]);
				ipdForm.setAdmissiondate(date);
				ipdForm.setDischargedate(date);
				String time[] = temp[1].split(":");
				String hh = time[0];
				String mm = time[1];
				ipdForm.setHour(hh);
				ipdForm.setMinute(mm);


                ipdForm.setHourList(PopulateList.hourListNew());
                ipdForm.setMinuteList(PopulateList.getMinuteList());  
				
				ipdForm.setFromdate(DateTimeUtils.getCommencingDate1(fromdate));
				ipdForm.setTodate(DateTimeUtils.getCommencingDate1(todate));
				
				Clinic clinic = new Clinic();
				ClinicDAO clinicDAO = new JDBCClinicDAO(connection);
				AccountsDAO accountsDAO = new JDBCAccountsDAO(connection);
				ArrayList<Clinic> locationAdressList = accountsDAO.getLocationAddress(loginInfo.getClinicid());
				clinic = clinicDAO.getCliniclistDetails(loginInfo.getClinicid());
				ipdForm.setClinicName(clinic.getClinicName());
				ipdForm.setClinicOwner(clinic.getClinicOwner());
				ipdForm.setOwner_qualification(clinic.getOwner_qualification());
				ipdForm.setLocationAdressList(locationAdressList);
				ipdForm.setAddress(clinic.getAddress());
				ipdForm.setLandLine(clinic.getLandLine());
				ipdForm.setClinicemail(clinic.getEmail());
				ipdForm.setWebsiteUrl(clinic.getWebsiteUrl());
				ipdForm.setClinicLogo(clinic.getUserImageFileName());
				
			} catch (Exception e) {
				e.printStackTrace();
			}
			return "ipdreport";
		}
		
		public String consultationNotes()throws Exception{
			Connection connection = null;
			String descriptionname="";
			try {
				connection = Connection_provider.getconnection();
				String clientId = request.getParameter("clientId");
				String apmtId = request.getParameter("apmtId");
				IpdDAO ipdDAO = new JDBCIpdDAO(connection);
				String fromDate=ipdForm.getFromDate();
				MasterDAO masterDAO = new JDBCMasterDAO(connection);
				 descriptionname=(String) session.getAttribute("descriptionname");
				ArrayList<Bed> consultationhistoryList = ipdDAO.getConsultantNoteHistory(clientId);
				ipdForm.setConsulthistorylist(consultationhistoryList);
				if(DateTimeUtils.isNull(fromDate).equals("")){
					DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
					fromDate = dateFormat.format(new Date());
				}else{
					fromDate = DateTimeUtils.getCommencingDate1(fromDate);
				}
				ArrayList<Diagnosis>diagnosisList = masterDAO.getDiagnosisList();
				ipdForm.setDiagnosislist(diagnosisList);
				ipdForm.setFromDate(DateTimeUtils.getCommencingDate1(fromDate));
				getConsultationNote(clientId,apmtId,"");
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			if(descriptionname.equalsIgnoreCase("Ophthalmology")) {
			  return "consultationOphthamo";	
			}else {
			  // return "consultationNotes";
			   return "consultationNotes1";
			}
			//return "discharge";
		}
		public String historyConsulatationForm() throws Exception{
			Connection connection = null;
			
			try {
				 connection = Connection_provider.getconnection();
				 String clientid=request.getParameter("clientid");
				 String apmtid=request.getParameter("apmtid");
				 
				 IpdDAO ipdDAO = new JDBCIpdDAO(connection);
				 
				ArrayList<Bed> consultationhistoryList = ipdDAO.getConsultantNoteHistory(clientid);

				StringBuffer str = new StringBuffer();
				str.append("<table class='table'>");
				str.append("<thead class='thead-dark'>");
				str.append("<tr>");
				str.append("<th class='emrinvestigationfont'>Sr.No</th><th class='emrinvestigationfont'>Date</th><th class='emrinvestigationfont'>Print</th><th class='emrinvestigationfont'>Edit</th>");
				str.append("</tr>");
				str.append("</thead>");
				str.append("<tbody>");
				int i=0;
				for (Bed bed : consultationhistoryList) {
					
					str.append("<tr>");
					str.append("<td>"+(++i)+"</td>");			
					str.append("<td>"+bed.getDatetime()+"</td>");
					//str.append("<td><a href='#' onclick='printParentPrisc("+clientid+")' title='Print Prescription'><i class='fa fa-print emrinvestigationfontprint'></i></a></td>");
			    	str.append("<td><a onclick=openPopup('printConsulatationFormCommonnew?clientId="+clientid+"+&apmtId="+bed.getAppointmentid()+"') <i class='fa fa-print fa-2x' title='Print'></i></a></td>");
                    str.append("<td><a onclick=openPopup('consultationNotesCommonnew?clientId="+clientid+"&apmtId="+bed.getAppointmentid()+"') <i class='fa fa-edit fa-2x' title='Edit'></i></a></td>");
					str.append("</tr>");
					
				}
				
				str.append("</tbody>");
				str.append("</table>");
				response.setContentType("text/html");
				response.setHeader("Cache-Control", "no-cache");
				response.getWriter().write(""+str.toString()+""); 

            } catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
			
			return null;
		}
		
		public void getConsultationNote(String clientId,String apmtId, String id2) {

			Connection connection = null;

			try {
				
				request.setCharacterEncoding("UTF-8");
				response.setCharacterEncoding("UTF-8");
				connection = DriverManager.getConnection(
						"" + Constants.DB_HOST + ":3306/" + loginInfo.getClinicUserid()
						+ "?useUnicode=true&characterEncoding=UTF-8",
				"" + Constants.DB_USER + "", "" + Constants.DB_PWD + "");
				IpdDAO ipdDAO = new JDBCIpdDAO(connection);
				BedDao bedDao = new JDBCBedDao(connection);
				IpdLogDAO ipdLogDAO = new JDBCIpdLogDAO(connection);
				ClientDAO clientDAO = new JDBCClientDAO(connection);
				MasterDAO masterDAO = new JDBCMasterDAO(connection);
				UserProfileDAO userProfileDAO = new JDBCUserProfileDAO(connection);
				NotAvailableSlotDAO notAvailableSlotDAO = new JDBCNotAvailableSlotDAO(connection);
				listOfExtraData();
				
				Client client = clientDAO.getClientDetails(clientId);
				String datetime =ipdDAO.getDateTime(clientId,apmtId);
				ipdForm.setContact(client.getMobNo());
				ipdForm.setAddress(client.getAddress());
				ipdForm.setMiddlename(client.getMiddlename());
				if(loginInfo.isBams1()) {
					datetime=datetime.split(" ")[0];
					ipdForm.setDatetime(datetime);
				}else {
					ipdForm.setDatetime(datetime);
				}
				//ipdForm.setDatetime(datetime);
				if (client.getWhopay().equals(Constants.PAY_BY_CLIENT)) {
					ipdForm.setWhopay("Self");
				} else {
					ThirdParty thirdParty = client.getTpDetails();
					ipdForm.setWhopay(thirdParty.getCompanyName());
				}
				
				//set treatment episode
				String fullname = client.getTitle() + " " + client.getFirstName() + " "+client.getMiddlename()+" " + client.getLastName();
				ipdForm.setClient(fullname);
				ipdForm.setRegno(client.getAbrivationid());
				String whopay=client.getWhopay();
				ipdForm.setAbrivationid(client.getAbrivationid());
				ipdForm.setPatientIdAbrivation(client.getPatientIdAbrivation());
				if(whopay==null){
					whopay="";
				}
				if(!whopay.equals("Client")){
					ThirdPartyDAO thirdPartyDAO = new JDBCThirdPartyDAO(connection);
					ThirdParty thirdParty = thirdPartyDAO.getThirdPartyDetails(client.getTypeName());
					ipdForm.setThirdParty(thirdParty.getCompanyName());
				}else{
					ipdForm.setThirdParty("Self");
				}
				String birthtime=client.getBirthtime().replaceAll(" ", "");
				String hh1="";
				String mm1="";
				String apmpm1="";
				if(!birthtime.equals("00:00:00")){
					String time[] = birthtime.split(":");
					hh1 = time[0];
					hh1=hh1.replaceAll(" ", "");
					mm1 = time[1];
					mm1=mm1.replaceAll(" ", "");
					int hourOfDay1=Integer.parseInt(hh1);
					int minute1=Integer.parseInt(mm1);
					apmpm1 =  ((hourOfDay1 > 12) ? hourOfDay1 % 12 : hourOfDay1) + ":" + (minute1 < 10 ? ("0" + minute1) : minute1) + " " + ((hourOfDay1 >= 12) ? "PM" : "AM");	 
				}
				String agegender="";
				String dob = client.getDob();
				String age = DateTimeUtils.getAge1(client.getDob());
				if(!birthtime.equals("00:00:00")){
					agegender = age +"("+apmpm1+")"+ " / " + client.getGender();
				}else{
					agegender = age + " / " + client.getGender();	
				}
				ipdForm.setAge(age);
				ipdForm.setAgegender(agegender);
				ipdForm.setRelativename(client.getEmergencyContName());
	    		ipdForm.setRelationcont(client.getEmergencyContNo());
	    		ipdForm.setRelation(client.getRelation());
	    		NotAvailableSlot notAvailableSlot=new NotAvailableSlot();
	    		//rahul chnge below condition add id2.equals("")
	    		if(id2==null || id2.equals("")) {
	    	    notAvailableSlot=notAvailableSlotDAO.getAvailableSlotdata(Integer.parseInt(apmtId));
	    		}
	    		UserProfile userProfile = userProfileDAO.getUserprofileDetails(notAvailableSlot.getDiaryUserId());
				Master discipline = masterDAO.getDisciplineData(userProfile.getDiciplineName());
				ipdForm.setDiscipline(discipline.getDiscipline());
				ipdForm.setDoctor_name(userProfile.getInitial() + " " + userProfile.getFirstname() + " " + userProfile.getLastname());
				ipdForm.setDoctor_phone(userProfile.getMobile());
				
				ipdForm.setNewipdabbr(notAvailableSlot.getOpdAbbrId());
				ipdForm.setIpdseqno(""+notAvailableSlot.getId());
				
				Bed bed = ipdDAO.getConsultationFormDetails(apmtId,id2);
				if(bed.getId()>0){
					ipdForm.setChiefcomplains(bed.getChiefcomplains());
					ipdForm.setExample(bed.getNotes());
					ipdForm.setInvestigation(bed.getInvestigation());
					ipdForm.setDiscadvnotes(bed.getDiscadvnotes());
					ipdForm.setKunal_manual_medicine_text(bed.getKunal_manual_medicine_text());
					ipdForm.setExamination(bed.getExamination());
					ipdForm.setOndiet(bed.getOndiet());
					ipdForm.setKarma(bed.getKarma());
					ipdForm.setProcedurebams(bed.getProcesurebams());
					ipdForm.setLSplRFI(bed.getLSplRFI());
					ipdForm.setLCylRFI(bed.getLCylRFI());
					ipdForm.setLAxisRFI(bed.getLAxisRFI());
					ipdForm.setRSplRFI(bed.getRSplRFI());
					ipdForm.setRCylRFI(bed.getRCylRFI());
					ipdForm.setRAxisRFI(bed.getRAxisRFI());
					ipdForm.setEyereading(bed.getEyereading());
					ipdForm.setConsNote(bed.getConsNote());
					ipdForm.setExamplehist(bed.getHistory());
					ipdForm.setRemark(bed.getRemark());	
					ipdForm.setNotes_text(bed.getKunal_manual_medicine_text());
				}else{
					ipdForm.setChiefcomplains("");
					ipdForm.setKunal_manual_medicine_text("");
					ipdForm.setDiscadvnotes("");
					ipdForm.setExample("");
					ipdForm.setInvestigation("");
					ipdForm.setKunal_manual_medicine_text("");
					ipdForm.setOndiet("");
					ipdForm.setKarma("");
					ipdForm.setProcedurebams("");
					ipdForm.setLSplRFI("");
					ipdForm.setLCylRFI("");
					ipdForm.setLAxisRFI("");
					ipdForm.setRSplRFI("");
					ipdForm.setRCylRFI("");
					ipdForm.setRAxisRFI("");
					ipdForm.setEyereading("");
					ipdForm.setExamplehist("");
					ipdForm.setRemark("");	
				}
				
				ArrayList<Master> chief_complaints_list = masterDAO.getConsultantNotesTemplateList("Chief Complaints");
				ipdForm.setChief_complaints_list(chief_complaints_list);
				
				//Diagnosis code starts
				Bed bedconditions = ipdDAO.getConsultationNotesDiagnosis(apmtId);

				ArrayList<Bed> finalConditions = new ArrayList<Bed>();

				if (bedconditions.getConditionname() != null) {

					for (String str : bedconditions.getConditionname().split(",")) {

						if (str == null) {
							continue;
						}
						if (str.equals("0")) {

							continue;
						}
						int id = Integer.parseInt(str);
						String conditionname = bedDao.getIpdConditionName(str);
						Bed bed2 = new Bed();
						bed2.setId(id);
						if(loginInfo.getClinicid1().equals("raiprachi")) {
							conditionname=conditionname.split("/")[0];
						}
						
						bed2.setConditionname(conditionname);
						bed2.setConditionid(str);
						finalConditions.add(bed2);

					}
				}
				ipdForm.setFinalConditions(finalConditions);

				ArrayList<Client> ipdCondtitionList = new ArrayList<Client>(); 
				session.setAttribute("finalConditions", finalConditions);
				//diagnosis code ends
				
				//chief complains code starts
				ArrayList<Master> finalChifcomplain = new ArrayList<Master>();
				Master master=ipdDAO.getChiefComplainsIds(apmtId);
				if (master.getChiefcomplains() != null) {

					for (String str : master.getChiefcomplains().split(",")) {

						if (str == null) {
							continue;
						}
						if (str.equals("0")) {

							continue;
						}
						int id = Integer.parseInt(str);
						String chiefname = ipdDAO.getIpdChiefComplainname(str);
						Master master2 = new Master();
						master2.setId(id);
						
						master2.setChiefcomplains(chiefname);
						master2.setChiefcomplainid(str);
						finalChifcomplain.add(master2);

					}
				}
				ipdForm.setFinalChifcomplain(finalChifcomplain);
				session.setAttribute("finalChifcomplain", finalChifcomplain);
				//chief Complains code ends
				
				
				
				//history code starts
				ArrayList<Master> finalhistory = new ArrayList<Master>();
				Master master1=ipdDAO.getHistoryIds(apmtId);
				if (master1.getPasthistory() != null) {

					for (String str : master1.getPasthistory().split(",")) {

						if (str == null) {
							continue;
						}
						if (str.equals("0")) {

							continue;
						}
						int id = Integer.parseInt(str);
						String historyname = ipdDAO.getIpdChiefComplainname(str);
						Master master2 = new Master();
						master2.setId(id);
						
						master2.setPasthistory(historyname);
						master2.setHistoryid(str);
						finalhistory.add(master2);

					}
				}
				ipdForm.setFinalhistory(finalhistory);
				session.setAttribute("finalhistory", finalhistory);
				//history  code ends
				

				//Investigation code starts
				ArrayList<Master> finalinvestigation = new ArrayList<Master>();
				Master master2=ipdDAO.getInvestigationIds(apmtId);
				if (master2.getInvestigstion_id() != null) {

					for (String str : master2.getInvestigstion_id().split(",")) {

						if (str == null) {
							continue;
						}
						if (str.equals("0")) {

							continue;
						}
						int id = Integer.parseInt(str);
						String investiname = ipdDAO.getInvestigationNames(str);
						Master master3 = new Master();
						master3.setId(id);
						
						master3.setInvestigation_name(investiname);
						master3.setInvestigation_type_id(str);
						finalinvestigation.add(master3);

					}
				}
				ipdForm.setFinalinvestigation(finalinvestigation);
				session.setAttribute("finalinvestigation", finalinvestigation);
				//investigation  code ends
				
				
				//Notes code starts
				ArrayList<Master> finalnotes = new ArrayList<Master>();
				Master mast=ipdDAO.getNotesIds(apmtId);
				if (mast.getNotes() != null) {

					for (String str : mast.getNotes().split(",")) {

						if (str == null) {
							continue;
						}
						if (str.equals("0")) {

							continue;
						}
						int id = Integer.parseInt(str);
						String notesname = ipdDAO.getIpdChiefComplainname(str);
						Master master4 = new Master();
						master4.setId(id);
						
						master4.setNotes(notesname);
						master4.setNotesid(str);
						finalnotes.add(master4);

					}
				}
				ipdForm.setFinalnotes(finalnotes);
				session.setAttribute("finalnotes", finalnotes);
				//notes  code ends
				
				
				//Observation code starts
				ArrayList<Master> finalobservation = new ArrayList<Master>();
				Master mast1=ipdDAO.getObservationIds(apmtId);
				if (mast1.getNotes() != null) {

					for (String str : mast1.getNotes().split(",")) {

						if (str == null) {
							continue;
						}
						if (str.equals("0")) {

							continue;
						}
						int id = Integer.parseInt(str);
						String observname = ipdDAO.getIpdChiefComplainname(str);
						Master master4 = new Master();
						master4.setId(id);
						
						master4.setNotes(observname);
						master4.setNotesid(str);
						finalobservation.add(master4);

					}
				}
				ipdForm.setFinalobservation(finalobservation);
				session.setAttribute("finalobservation", finalobservation);
				//Observation  code ends
				
				
				//Treatment Plan Code Starts here
				ArrayList<Master> finalTreatment = new ArrayList<Master>();
				Master mas=ipdDAO.getTreatmentIds(apmtId);
				if (mas.getChargename() != null) {

					for (String str : mas.getChargename().split(",")) {

						if (str == null) {
							continue;
						}
						if (str.equals("0")) {

							continue;
						}
						int id = Integer.parseInt(str);
						Master chargename = ipdDAO.getTreatmentPlan(str);
						Master master4 = new Master();
						master4.setId(id);
						
						master4.setChargename(chargename.getChargename());
						master4.setCharge(chargename.getCharge());
						master4.setChargetype(chargename.getChargetype());
						master4.setChargeid(str);
						finalTreatment.add(master4);

					}
				}
				
				ipdForm.setFinalTreatment(finalTreatment);
				session.setAttribute("finalTreatment", finalTreatment);
				//Treatment Ends here
				ArrayList<Master> investigationList = masterDAO.getConsultantNotesTemplateList("Investigations");//Consultant Form Investigations
				ipdForm.setInvestigationList(investigationList);
				
				InvestigationDAO investigationDAO = new JDBCInvestigationDAO(connection);
				ArrayList<InvstTemplate> templateList = investigationDAO.getTemplateList();
				ipdForm.setInvestigationtemplatelist(templateList);
				
				ArrayList<Priscription> dischargeParentPriscIds = ipdDAO.getCFParentPriscIds(apmtId);
	    		ArrayList<Priscription> dischargePriscList = new ArrayList<>();
				ArrayList<Priscription> treatmentivendischargePriscList = new ArrayList<>();
				String parentpriscid ="";
	    		
				String treatmentIds="0,";
				if(dischargeParentPriscIds.size()>0){
	    			//parentpriscid = ipdDAO.getParentPriscId(selectedid);
					parentpriscid = ""+dischargeParentPriscIds.get(dischargeParentPriscIds.size()-1).getId();
					dischargePriscList = ipdDAO.getDichargePriscriptionList(dischargeParentPriscIds.get(dischargeParentPriscIds.size()-1).getIds(),apmtId);
				}
	    		ipdForm.setParentpriscid(parentpriscid);
	    		
	    		ipdForm.setDischargePriscList(dischargePriscList);
				session.setAttribute("priscList", dischargePriscList);
				
				int size = dischargePriscList.size();
				if (size > 0) {
					String totalchildmedids = dischargePriscList.get(size - 1).getTotalchildmedids();
					ipdForm.setTotalchildmedids(totalchildmedids);
				} else {
					ipdForm.setTotalchildmedids("0");
				}
				
				//Priscription
				ArrayList<Master> dosagenoteList = masterDAO.getDosageNoteList();
				ipdForm.setDosagenoteList(dosagenoteList);
				
				EmrDAO emrDAO = new JDBCEmrDAO(connection);
				ArrayList<Priscription> medicinetimelist = emrDAO.getMedicineTimeList(); 
				ipdForm.setMedicinetimelist(medicinetimelist); 
				
				ArrayList<Master> priscUnitList = masterDAO.getPriscUnitList();
				ipdForm.setPriscUnitList(priscUnitList);
				
				ArrayList<Master> discharge_default_list = masterDAO.getConsultantNotesTemplateList("Discharge Default");//Consultant Form Default
				ipdForm.setDischarge_default_list(discharge_default_list);
				
				ArrayList<Master> history_list = masterDAO.getConsultantNotesTemplateList("Past History");//Consultant Form Default
				ipdForm.setPast_history_list(history_list);
				
				ArrayList<Master> nursing_advice_list = masterDAO.getConsultantNotesTemplateList("Nursing Advice");//Consultant Form Nursing Advice
				ipdForm.setNursing_advice_list(nursing_advice_list);
				
				ArrayList<Master> examination_finding_list=masterDAO.getConsultantNotesTemplateList("Examination Findings");
				ipdForm.setExamination_finding_list(examination_finding_list);
				
				ArrayList<Master>on_diet_list=masterDAO.getIpdTemplateListNamesList("On Diet");
				ipdForm.setOn_diet_list(on_diet_list);
				ArrayList<Master>physio_list=masterDAO.getIpdTemplateListNamesList("Physio");
				ipdForm.setPhysio_list(physio_list);
				ArrayList<Master>karma_list=masterDAO.getIpdTemplateListNamesList("Karma");
				ipdForm.setKarma_list(karma_list);
				ArrayList<Master>procedure_list=masterDAO.getIpdTemplateListNamesList("Procedurebams");
				ipdForm.setProcedure_list(procedure_list);
				Client bmidata = clientDAO.getBMIData(clientId, apmtId);
				ipdForm.setPulse(bmidata.getPulse());
				ipdForm.setSys_bp(bmidata.getSysbp());
				ipdForm.setSpo(bmidata.getSpo());
				ipdForm.setTemp(bmidata.getTemprature());
				ipdForm.setSugarFasting(bmidata.getSugarfasting());
				ipdForm.setHeight(bmidata.getHeight());
				ipdForm.setWeight(bmidata.getWeight());
				ipdForm.setBmi(bmidata.getBmi());
				ipdForm.setAllergynotes(bmidata.getAllergynotes());
				ipdForm.setRespiratory_rate(bmidata.getRespiratory_rate());
				ipdForm.setUserid(bmidata.getUserid());
				ipdForm.setClientid(clientId);
				ipdForm.setApmtId(apmtId);
				ipdForm.setPractitionerid(""+notAvailableSlot.getDiaryUserId());
				ipdForm.setThirdParty(client.getTypeName());
				
				ArrayList<Master> pkgsList = investigationDAO.getInvPaksLists();
				ipdForm.setPkgsList(pkgsList);
				ArrayList<Master> invsTypeList = emrDAO.getInvesigationTypeList();
				ipdForm.setInvsTypeList(invsTypeList);
				ArrayList<Master> invstReportTypeList = emrDAO.getInvstReportTypeList();
				ipdForm.setInvstReportTypeList(invstReportTypeList);
				ArrayList<Master> invstUnitList = emrDAO.getInvstUnitList();
				ipdForm.setInvstUnitList(invstUnitList);
				ArrayList<Master> invSectionList = investigationDAO.getInvestigationSectionList();
				ipdForm.setInvSectionList(invSectionList);
				session.setAttribute("ipdFormCF", ipdForm);
				ipdForm.setIsConsultationForm(1);
				ipdForm.setApmtId(apmtId);
				DischargeOutcomeDAO dao = new JDBCDischargeOutcomeDAO(connection);
				DutyDAO dutyDAO=new JDBCDutyDAO(connection);
				ArrayList<Bams> mainArrayList =new ArrayList<>();
				
				//priscription print for prachi clinic
				if(loginInfo.getClinicid1().equals("raiprachi")) {
				ArrayList<Priscription> priscriptionListprachi =emrDAO.getPriscriptionListbyclientId(datetime,clientId);
				ipdForm.setPriscriptionListprachi(priscriptionListprachi);
				ipdForm.setRequestlocationid("CENTRAL DRUG STORE");
				}
				
				
				if(loginInfo.isBams1() || loginInfo.isMbbs()) {
					
						String mainDate=notAvailableSlot.getCommencing();
						String fromdate = mainDate + " 00:00:00";
						String todate = mainDate + " 23:59:59";
						Bams bams = new Bams();
						ArrayList<Bams> vitals = dao.getVitals(fromdate, todate, ipdForm.getClientid());
						bams.setVitalList(vitals);
						ArrayList<Priscription> priscriptionList = emrDAO.getPrintPriscListWIthDate(parentpriscid,
								loginInfo, fromdate, todate,ipdForm.getClientid());
						bams.setPriscList(priscriptionList);
						ArrayList<Investigation> investList = investigationDAO.getAllInvestigation(fromdate, todate,
								ipdForm.getId(),ipdForm.getApmtId());
						bams.setInvestList(investList);
						ArrayList<Duty> punchKarma = dutyDAO.getPunchkarmaList(mainDate, mainDate, ipdForm.getId(),ipdForm.getApmtId());
						bams.setPunchList(punchKarma);
						ArrayList<Bed> physioList=bedDao.getPhysioList(mainDate,ipdForm.getId(),ipdForm.getApmtId());
						bams.setPhysioList(physioList);
						
						Bams karmaprocedure=dao.getKarmaproceduredata(ipdForm.getClientid(),ipdForm.getApmtId());
						ipdForm.setKarmaprocedure(karmaprocedure);
						
						bams.setShowDate(DateTimeUtils.getInvoiceCommencingDate(mainDate));
						if (!( priscriptionList.isEmpty() && investList.isEmpty()
								&& punchKarma.isEmpty()&& physioList.isEmpty() && vitals.isEmpty() )) {
							mainArrayList.add(bams);
						}
						
						
					}
					int treatmentepisode_id=ipdDAO.getTreatmentId(ipdForm.getId());
					int treatmentstatus=ipdDAO.gettreatmentStatus(ipdForm.getClientid());
					Ipd ipd=ipdDAO.getDietData(treatmentepisode_id,ipdForm.getApmtId(),treatmentstatus);
					ipdForm.setDiet(ipd);
					ipdForm.setMainArrayList(mainArrayList);
					session.setAttribute("mainArrayList", mainArrayList);
					Client client2=clientDAO.getDeclarationData(ipdForm.getId());
					ipdForm.setDecData(client2);
					
					if(loginInfo.getClinicid1().equals("raiprachi") && !datetime.equals("")) {
						String date=datetime.split(" ")[0];
						date=DateTimeUtils.getCommencingDate1(date);
						String time=datetime.split(" ")[1];
						
						//date=date+" "+time;
						ipdForm.setDate1(date);
					}
			}
			catch (Exception e) {
				e.printStackTrace();
			}

		}
		
		public String saveconsultationForm() throws Exception{
			Connection connection = null;
			try {
				request.setCharacterEncoding("UTF-8");
				response.setCharacterEncoding("UTF-8");
				connection = DriverManager.getConnection(
						"" + Constants.DB_HOST + ":3306/" + loginInfo.getClinicUserid()
								+ "?useUnicode=true&characterEncoding=UTF-8",
						"" + Constants.DB_USER + "", "" + Constants.DB_PWD + "");
				IpdDAO ipdDAO = new JDBCIpdDAO(connection);
				ClientDAO clientDAO = new JDBCClientDAO(connection);
				String clientId = ipdForm.getClientid();
				String apmtId = ipdForm.getApmtId();
				String whopay = ipdForm.getWhopay();
				String thirdParty = ipdForm.getThirdParty();
				String LSplRFI=ipdForm.getLSplRFI();
				String LCylRFI=ipdForm.getLCylRFI();
				String LAxisRFI=ipdForm.getLAxisRFI();
				String RSplRFI=ipdForm.getRSplRFI();
				String RCylRFI=ipdForm.getRCylRFI();
				String RAxisRFI=ipdForm.getRAxisRFI();
				String remark=ipdForm.getRemark();
				//Constation Form Save/Update Start
				
				
				Client client2 = clientDAO.getClientDetails(clientId);
				//Diagnosis and chief complains Start
				int res = ipdDAO.deleteFinalConditionsCF(apmtId);
				String allconditions = "0";
				if (ipdForm.getConditions() != null) {
					for (Bed bedcondition : ipdForm.getConditions()) {
						if (bedcondition == null) {
							continue;
						}
						allconditions = allconditions + "," + bedcondition.getConditionid();
					}
				}
				
				
				String chief_complains="0";
				if(ipdForm.getChiefcomplain() !=null) {
					
					for(Master master : ipdForm.getChiefcomplain()) {
						if(master == null) {
							continue;
						}
						chief_complains=chief_complains+","+master.getId();
					}
					
				}
				
				// history starts here
				String history="0";
				if(ipdForm.getHistories() !=null) {
					for(Master master1 : ipdForm.getHistories()) {
						if(master1==null) {
							continue;
						}
						history=history+","+master1.getId();
					}
				}
				
				// Investigation Starts Here
				
				String investigation="0";
				if(ipdForm.getInvestigations() !=null) {
					for(Master master1 : ipdForm.getInvestigations()) {
						if(master1==null) {
							continue;
						}
						investigation=investigation+","+master1.getId();
					}
				}
				
				// Notes starts here
				
				String notes="0";
				if(ipdForm.getNotes() !=null) {
					for(Master master1 : ipdForm.getNotes()) {
						if(master1==null) {
							continue;
						}
						notes=notes+","+master1.getId();
					}
				}
				
				
				//Observation starts here
				String observation="0";
				if(ipdForm.getObservation() !=null) {
					for(Master master1 : ipdForm.getObservation()) {
						if(master1==null) {
							continue;
						}
						observation=observation+","+master1.getId();
					}
				}
				
				
				//Treatment starts here
				String treatment="0";
				if(ipdForm.getCharges() !=null) {
					for(Master master1 : ipdForm.getCharges()) {
						if(master1==null) {
							continue;
						}
						treatment=treatment+","+master1.getId();
						CompleteAppointment completeAppointment = new CompleteAppointment();
						CompleteAptmDAO completeAptmDAO = new JDBCCompleteAptmDAO(connection);
					     String treatcharge=ipdForm.getTreatcharge();
					     completeAppointment.setCharges(master1.getCharge());
					     completeAppointment.setMasterchargetype(ipdForm.getChargetype());
					     completeAppointment.setClientId(clientId);
					     completeAppointment.setUser(client2.getFullname());
					     completeAppointment.setApmtType(master1.getChargename());
					     completeAppointment.setCommencing(DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone()).split(" ")[0]);
					     completeAppointment.setAdditionalcharge_id(""+master1.getId());
						int result = completeAptmDAO.saveCompleteApmt(completeAppointment, apmtId, loginInfo.getId());
						
						
					 
					     
					}
					saveTreatmentPlan(clientId,client2.getFullname(),apmtId);
				}
				
				
				int result = ipdDAO.savefinalConditionCF(apmtId,allconditions,chief_complains,history,investigation,notes,observation,treatment);
				
				String ids=ipdDAO.getDiagnosisids(apmtId);
				String diagnosis=ipdDAO.getDiagnosisText(ids);
				String chiefcomplain=ipdDAO.getChiefComplainsbyid(chief_complains);
				String historytext=ipdDAO.getChiefComplainsbyid(history);
				String investigationtext=ipdDAO.getInvestigationNameByid(investigation);
				String notestext=ipdDAO.getNotestext(notes);
				String observtext=ipdDAO.setObservText(observation);
				String treatmentname=ipdDAO.setTreatmentName(treatment);
				//Dignosis End
				
				String age = DateTimeUtils.getAge1(client2.getDob());
				NotAvailableSlotDAO notAvailableSlotDAO = new JDBCNotAvailableSlotDAO(connection);
				String practionerId = notAvailableSlotDAO.getPractIdFromApmtId(apmtId);
				UserProfileDAO userProfileDAO = new JDBCUserProfileDAO(connection);
				UserProfile user = new UserProfile();
				user = userProfileDAO
						.getUserprofileDetails(DateTimeUtils.convertToInteger(practionerId));
				UserProfile user1=userProfileDAO.getUserprofileDetails(1);
				int result1=0;
				Bed bed = new Bed();
				//bed.setChiefcomplains(new String(DateTimeUtils.isNull(ipdForm.getChiefcomplains()).getBytes("iso-8859-1"), "UTF-8"));
				bed.setChiefcomplains(new String(DateTimeUtils.isNull(chiefcomplain).getBytes("iso-8859-1"), "UTF-8"));
				bed.setInvestigation(new String(DateTimeUtils.isNull(investigationtext).getBytes("iso-8859-1"), "UTF-8"));
				bed.setDiscadvnotes(new String(DateTimeUtils.isNull(ipdForm.getDiscadvnotes()).getBytes("iso-8859-1"), "UTF-8"));
				//bed.setNotes(new String(DateTimeUtils.isNull(ipdForm.getExample()).getBytes("iso-8859-1"), "UTF-8"));
				bed.setNotes(new String(DateTimeUtils.isNull(notestext+"\n<br><br>"+ipdForm.getNotes_text()).getBytes("iso-8859-1"), "UTF-8"));
				bed.setObservation(new String(DateTimeUtils.isNull(observtext).getBytes("iso-8859-1"), "UTF-8"));
				//bed.setHistory(new String(DateTimeUtils.isNull(ipdForm.getExamplehist()).getBytes("iso-8859-1"), "UTF-8"));
				bed.setHistory(new String(DateTimeUtils.isNull(historytext).getBytes("iso-8859-1"), "UTF-8"));
				bed.setKunal_manual_medicine_text(new String(DateTimeUtils.isNull(ipdForm.getNotes_text()).getBytes("iso-8859-1"), "UTF-8"));
				bed.setClientid(clientId);
				bed.setWhopay(whopay);
				bed.setTpid(thirdParty);
				bed.setAppointmentid(apmtId);
				bed.setUserid(loginInfo.getUserId());
				bed.setDatetime(DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone()));
				bed.setExamination(new String(DateTimeUtils.isNull(ipdForm.getExamination()).getBytes("iso-8859-1"), "UTF-8"));
				bed.setOndiet(new String(DateTimeUtils.isNull(ipdForm.getOndiet()).getBytes("iso-8859-1"), "UTF-8"));
				bed.setKarma(new String(DateTimeUtils.isNull(ipdForm.getKarma()).getBytes("iso-8859-1"), "UTF-8"));
				bed.setProcesurebams(new String(DateTimeUtils.isNull(ipdForm.getProcedurebams()).getBytes("iso-8859-1"), "UTF-8"));
				
				bed.setLSplRFI(DateTimeUtils.isNull(LSplRFI));
				bed.setLCylRFI(DateTimeUtils.isNull(LCylRFI));
				bed.setLAxisRFI(DateTimeUtils.isNull(LAxisRFI));
				bed.setRSplRFI(DateTimeUtils.isNull(RSplRFI));
				bed.setRCylRFI(DateTimeUtils.isNull(RCylRFI));
				bed.setRAxisRFI(DateTimeUtils.isNull(RAxisRFI));
				bed.setPriscription(new String(DateTimeUtils.isNull(ipdForm.getConsNote()).getBytes("iso-8859-1"), "UTF-8"));
				bed.setEyereading("");
				bed.setRemark(remark);
				bed.setTreatmentgiven(treatmentname);
			
				bed.setConsNote("<html><body>"
						+ "<div class='col-lg-12 col-xs-12 col-sm-12'>"
						+ "  <img class='img-responsive logoste1' src='liveData/clinicLogo/"+user.getUserImageFileName()+"' />"
					
						+ "<label style='font-size: 25px;text-align:center;'><b>"+user1.getClinicname()+"</b></label></div>"
						+ "<div class='col-lg-12 col-xs-12 col-sm-12'>"
						+ "<label style='font-size: 15px;text-align:center;'><b>"+user1.getClinicowner()+"</b></label></div>"
						+ "<div class='col-lg-12 col-xs-12 col-sm-12'>"
						+ "<label style='font-size: 15px;text-align:center;'><b>"+user1.getAddress()+"</b></label></div>"
						+ "<div class='col-lg-12 col-xs-12 col-sm-12'>"
					    + "<label style='font-size: 15px;text-align:center;'><b>"+user1.getMobile()+"</b></label></div>"
					    + "<div class='col-lg-12 col-xs-12 col-sm-12'>"
					    + "<label style='font-size: 15px;text-align:center;'><b>"+user1.getEmail()+"</b></label>"
						+ "</div><br><br>"
					    + "<hr>"
						+ "<div class='col-lg-12 col-xs-12 col-sm-12'>"
						+ "<label>Patient Name : "+client2.getFullname()+"</label>"
						+ "</div><div class='col-lg-12 col-xs-12 col-sm-12'>"
						+ "<label>UHID : "+client2.getAbrivationid()+"</label></div>"
						+ "<div class='col-lg-12 col-xs-12 col-sm-12'>"
						+ "<label>Age/Sex : "+age+"/"+client2.getGender()+"</label></div>"    
						+ "<div class='col-lg-12 col-xs-12 col-sm-12'>"
						+ "<label>Consultant : "+user.getFullname()+"</label></div>"
						+ "<div class='col-lg-12 col-xs-12 col-sm-12'>"
						+"<label>Consultation Date : "+DateTimeUtils.getUKCurrentDataTime("India")+"</label></div><br>"
						+"<hr>"
						+(new String("<b>VITALS</b><br> Pulse: "+ipdForm.getPulse())
						+"    BP: "+ipdForm.getSys_bp()+"    SPO2: "+ipdForm.getSpo()+"    Temp: "+ipdForm.getTemp()+"    Blood-Sugar: "+ipdForm.getSugarFasting()+"    Ht: "+ipdForm.getHeight()+"    Wt: "+ipdForm.getWeight()+"    BMI: "+ipdForm.getBmi())
						
						+"\n <br><br>"+(new String(DateTimeUtils.isNull("<b>CHIEF COMPLAINTS</b><br>"+chiefcomplain).getBytes("iso-8859-1"), "UTF-8"))
						+"\n <br><br><b>OBSERVATION</b><br>"+(new String(DateTimeUtils.isNull(observtext).getBytes("iso-8859-1"), "UTF-8"))
						+"\n <br><br><b>HISTORY</b><br>"+(new String(DateTimeUtils.isNull(historytext).getBytes("iso-8859-1"), "UTF-8")+"</body></html>")
						+"\n <br><br><b>INVESTIGATION<b><br>"+(new String(DateTimeUtils.isNull(investigationtext).getBytes("iso-8859-1"), "UTF-8"))
						+"\n <br><br><b>Diagnosis</b><br>"+diagnosis+"\n<br><br>"
						+"\n <br><br><b>TREATMENT PLAN</b><br>"+(new String(DateTimeUtils.isNull(treatmentname).getBytes("iso-8859-1"), "UTF-8"))
						+"\n <br><br><b>ADVICE MEDICINE</b><br>"+(new String(DateTimeUtils.isNull(ipdForm.getConsNote()).getBytes("iso-8859-1"), "UTF-8"))
					   // +"\n <br><br><b>PRESCRIPTION TEXT</b><br>"+(new String(DateTimeUtils.isNull(ipdForm.getKunal_manual_medicine_text()).getBytes("iso-8859-1"), "UTF-8"))
				        +"\n <br><br><b>ADVICE/FOLLOW UP</b><br>"+(new String(DateTimeUtils.isNull(ipdForm.getDiscadvnotes()).getBytes("iso-8859-1"), "UTF-8"))
				        +"\n <br><br><b>NOTES</b><br>"+(new String(DateTimeUtils.isNull(notestext+"\n<br>"+ipdForm.getNotes_text()).getBytes("iso-8859-1"), "UTF-8"))
				        +"\n <br><br><b>REMARK</b><br>"+(new String(DateTimeUtils.isNull(remark).getBytes("iso-8859-1"), "UTF-8")+"</body></html>")
						
						
				);
				
				bed.setClientemail("<html><body>"
						+ "<div class='col-lg-12 col-xs-12 col-sm-12'>"
						+ "  <img class='img-responsive logoste1' src='liveData/clinicLogo/"+user.getUserImageFileName()+"' />"
					
						+ "<label style='font-size: 25px;text-align:center;'><b>"+user1.getClinicname()+"</b></label></div>"
						+ "<div class='col-lg-12 col-xs-12 col-sm-12'>"
						+ "<label style='font-size: 15px;text-align:center;'><b>"+user1.getClinicowner()+"</b></label></div>"
						+ "<div class='col-lg-12 col-xs-12 col-sm-12'>"
						+ "<label style='font-size: 15px;text-align:center;'><b>"+user1.getAddress()+"</b></label></div>"
						+ "<div class='col-lg-12 col-xs-12 col-sm-12'>"
					    + "<label style='font-size: 15px;text-align:center;'><b>"+user1.getMobile()+"</b></label></div>"
					    + "<div class='col-lg-12 col-xs-12 col-sm-12'>"
					    + "<label style='font-size: 15px;text-align:center;'><b>"+user1.getEmail()+"</b></label>"
						+ "</div><br><br>"
					    + "<hr>"
						+ "<div class='col-lg-12 col-xs-12 col-sm-12'>"
						+ "<label>Patient Name : "+client2.getFullname()+"</label>"
						+ "</div><div class='col-lg-12 col-xs-12 col-sm-12'>"
						+ "<label>UHID : "+client2.getAbrivationid()+"</label></div>"
						+ "<div class='col-lg-12 col-xs-12 col-sm-12'>"
						+ "<label>Age/Sex : "+age+"/"+client2.getGender()+"</label></div>"    
						+ "<div class='col-lg-12 col-xs-12 col-sm-12'>"
						+ "<label>Consultant : "+user.getFullname()+"</label></div>"
						+ "<div class='col-lg-12 col-xs-12 col-sm-12'>"
						+"<label>Consultation Date : "+DateTimeUtils.getUKCurrentDataTime("India")+"</label></div><br>"
						+"<hr>"
						+(new String("<b>VITALS</b><br> Pulse: "+ipdForm.getPulse())
						+"    BP: "+ipdForm.getSys_bp()+"    SPO2: "+ipdForm.getSpo()+"    Temp: "+ipdForm.getTemp()+"    Blood-Sugar: "+ipdForm.getSugarFasting()+"    Ht: "+ipdForm.getHeight()+"    Wt: "+ipdForm.getWeight()+"    BMI: "+ipdForm.getBmi())
						
						+"\n <br><br>"+(new String(DateTimeUtils.isNull("<b>CHIEF COMPLAINTS</b><br>"+chiefcomplain).getBytes("iso-8859-1"), "UTF-8"))
						+"\n <br><br><b>OBSERVATION</b><br>"+(new String(DateTimeUtils.isNull(observtext).getBytes("iso-8859-1"), "UTF-8"))
						+"\n <br><br><b>HISTORY</b><br>"+(new String(DateTimeUtils.isNull(historytext).getBytes("iso-8859-1"), "UTF-8")+"</body></html>")
						+"\n <br><br><b>INVESTIGATION<b><br>"+(new String(DateTimeUtils.isNull(investigationtext).getBytes("iso-8859-1"), "UTF-8"))
						+"\n <br><br><b>Diagnosis</b><br>"+diagnosis+"\n<br><br>"
						+"\n <br><br><b>TREATMENT PLAN</b><br>"+(new String(DateTimeUtils.isNull(treatmentname).getBytes("iso-8859-1"), "UTF-8"))
						+"\n <br><br><b>ADVICE MEDICINE</b><br>"+(new String(DateTimeUtils.isNull(ipdForm.getConsNote()).getBytes("iso-8859-1"), "UTF-8"))
					   // +"\n <br><br><b>PRESCRIPTION TEXT</b><br>"+(new String(DateTimeUtils.isNull(ipdForm.getKunal_manual_medicine_text()).getBytes("iso-8859-1"), "UTF-8"))
				        +"\n <br><br><b>ADVICE/FOLLOW UP</b><br>"+(new String(DateTimeUtils.isNull(ipdForm.getDiscadvnotes()).getBytes("iso-8859-1"), "UTF-8"))
				        +"\n <br><br><b>NOTES</b><br>"+(new String(DateTimeUtils.isNull(notestext+"\n<br>"+ipdForm.getNotes_text()).getBytes("iso-8859-1"), "UTF-8"))
				  
						
						
				);
				
				int consulationFormId = ipdDAO.getConsulationFormId(apmtId);
				if(consulationFormId>0){
					//update
					int resultupdt = ipdDAO.updateConsultationForm(bed,consulationFormId);
				}else{
					//insert
					   result1 = ipdDAO.insertConsultationForm(bed);
					  if(loginInfo.getClinicid1().equals("dentalcl")) {
					   createPdf1(result1,client2.getEmail());
					  }
					//  createPdf1(result1,client2.getEmail());
				}
				Bed bed1=new Bed();
				bed1.setPhysio(new String(DateTimeUtils.isNull(ipdForm.getPhysio()).getBytes("iso-8859-1"), "UTF-8"));
				bed1.setFakebackdate(ipdForm.getFromDate());
	    		bed1.setClientid(clientId);
	    		bed1.setOpdid(apmtId);
	    		BedDao bedDao=new JDBCBedDao(connection);
	    		//save ipd physio data for bams
	    		int re=bedDao.saveOpdPhsioDetails(bed1);
				
				
				//Save bmi code start
				String bamsapmtdate=clientDAO.getappointmentdate(apmtId);
				String datetime= DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone());
				bamsapmtdate=bamsapmtdate+" "+datetime.split(" ")[1];
				Client bmidata = new Client();
				bmidata.setPulse(ipdForm.getPulse());
				bmidata.setSysbp(ipdForm.getSys_bp());
				bmidata.setSpo(ipdForm.getSpo());
				bmidata.setTemprature(ipdForm.getTemp());
				bmidata.setSugarfasting(ipdForm.getSugarFasting());
				bmidata.setHeight(ipdForm.getHeight());
				bmidata.setWeight(ipdForm.getWeight());
				bmidata.setBmi(ipdForm.getBmi());
				bmidata.setRespiratory_rate(ipdForm.getRespiratory_rate());
				bmidata.setAppointmentid(apmtId);
				bmidata.setClientid(clientId);
				bmidata.setAllergynotes(ipdForm.getAllergynotes());
				if(loginInfo.isBams1() || loginInfo.isMbbs()) {
					bmidata.setDate(bamsapmtdate);
				}
				int bmiResult = clientDAO.deleteBMIData(apmtId);
				bmiResult = clientDAO.saveBMIPatient(bmidata);
				if(loginInfo.getJobTitle().equals("RMO")) {
					int bmiupdt=clientDAO.updateBmidata(loginInfo,bmiResult);
				}
				//bmi save code End
				
				//Save template Start
				if (!DateTimeUtils.isNull(ipdForm.getExamtempname()).equals("")) {
					int chiefCompainId = ipdDAO.getIpdTemplateId("Examination Findings"); //Consultant Form Chief Complaints
					if(chiefCompainId>0){
						int res5 = ipdDAO.saveIPDTemplate(ipdForm.getExamtempname(), ""+chiefCompainId, ipdForm.getDepartment(),
								new String(DateTimeUtils.isNull(ipdForm.getExamination()).getBytes("iso-8859-1"), "UTF-8"));
					}
				}
				if (!DateTimeUtils.isNull(ipdForm.getChiefcomplatetempname()).equals("")) {
					int chiefCompainId = ipdDAO.getIpdTemplateId("Chief Complaints"); //Consultant Form Chief Complaints
					if(chiefCompainId>0){
						int res5 = ipdDAO.saveIPDTemplate(ipdForm.getChiefcomplatetempname(), ""+chiefCompainId, ipdForm.getDepartment(),
								new String(DateTimeUtils.isNull(ipdForm.getChiefcomplains()).getBytes("iso-8859-1"), "UTF-8"));
					}
				}
				
				if (!DateTimeUtils.isNull(ipdForm.getInvestigationtempname()).equals("")) {
					int chiefCompainId = ipdDAO.getIpdTemplateId("Investigations");
					if(chiefCompainId>0){
						int res5 = ipdDAO.saveIPDTemplate(ipdForm.getInvestigationtempname(), ""+chiefCompainId, ipdForm.getDepartment(),
								new String(DateTimeUtils.isNull(ipdForm.getInvestigation()).getBytes("iso-8859-1"), "UTF-8"));
					}
				}
				
				if (!DateTimeUtils.isNull(ipdForm.getNursingadvicetempname()).equals("")) {
					int chiefCompainId = ipdDAO.getIpdTemplateId("Nursing Advice");
					if(chiefCompainId>0){
						int res5 = ipdDAO.saveIPDTemplate(ipdForm.getNursingadvicetempname(), ""+chiefCompainId, ipdForm.getDepartment(),
								new String(DateTimeUtils.isNull(ipdForm.getDiscadvnotes()).getBytes("iso-8859-1"), "UTF-8"));
					}
				}
				
				if (!DateTimeUtils.isNull(ipdForm.getDisdefaulttempname()).equals("")) {
					int chiefCompainId = ipdDAO.getIpdTemplateId("Discharge Default");
					if(chiefCompainId>0){
						int res5 = ipdDAO.saveIPDTemplate(ipdForm.getDisdefaulttempname(), ""+chiefCompainId, ipdForm.getDepartment(),
								new String(DateTimeUtils.isNull(ipdForm.getExample()).getBytes("iso-8859-1"), "UTF-8"));
					}
				}
				
				if (!DateTimeUtils.isNull(ipdForm.getPasthistory()).equals("")) {
					int chiefCompainId = ipdDAO.getIpdTemplateId("Past History");
					if(chiefCompainId>0){
						int res5 = ipdDAO.saveIPDTemplate(ipdForm.getPasthistory(), ""+chiefCompainId, ipdForm.getDepartment(),
								new String(DateTimeUtils.isNull(ipdForm.getExample()).getBytes("iso-8859-1"), "UTF-8"));
					}
				}
				if (!DateTimeUtils.isNull(ipdForm.getOndiettempname()).equals("")) {
					int chiefCompainId = ipdDAO.getIpdTemplateId("On Diet");
					if(chiefCompainId>0){
						int res5 = ipdDAO.saveIPDTemplate(ipdForm.getOndiettempname(), ""+chiefCompainId, ipdForm.getDepartment(),
								new String(DateTimeUtils.isNull(ipdForm.getOndiet()).getBytes("iso-8859-1"), "UTF-8"));
					}
				}
				//Template save end
				session.setAttribute("printCFClientid", clientId);
				session.setAttribute("printCFapmtId", apmtId);
				
				//Follow Up
				String followupdate1 = request.getParameter("followupdate1");
				String bkapmtipd = request.getParameter("bkapmtipd");
				
				if(!DateTimeUtils.isNull(followupdate1).equals("")){
					
					
					Client client = new Client();
					client.setClientId(clientId);
					client.setOpdid(apmtId);
					client.setIpdid("0");
					client.setType("2");
					client.setPractid(practionerId);
					client.setLocation("EMR");
					client.setFollowupdate(DateTimeUtils.getCommencingDate1(followupdate1));
					DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
					Calendar cal = Calendar.getInstance();
					String toDaysDate = dateFormat.format(cal.getTime());
					client.setDate(toDaysDate);
					
					int followup = clientDAO.savefollowup(client);
					boolean flag = false;
					if (followup > 0 && DateTimeUtils.booleanCheck(bkapmtipd).equals("1")) {
						followupdate1 = DateTimeUtils.getCommencingDate1(followupdate1);
						AccountsDAO accountsDAO = new JDBCAccountsDAO(connection);
						whopay = clientDAO.getWhoPayName(clientId);
						AppointmentType appointmentType = accountsDAO.getFollowUpIdCharge(whopay);
						if (appointmentType.getCharges() != null) {
							
							NotAvailableSlot n = notAvailableSlotDAO.getMveDiaryUserDetails("" + practionerId,followupdate1);

							String stime = n.getSTime();
							int slotid = n.getId();
							String duration = n.getDuration();
							boolean chkapmtexsist = notAvailableSlotDAO.chkmveapmtaxsist("" + practionerId,
									followupdate1);
							if (chkapmtexsist) {
								stime = notAvailableSlotDAO.getmveapmtendtime("" + practionerId, followupdate1);
							}
							SimpleDateFormat df = new SimpleDateFormat("HH:mm");
							Date d = df.parse(stime);
							Calendar cal1 = Calendar.getInstance();
							cal1.setTime(d);
							cal1.add(Calendar.MINUTE, 5);
							String endtime = df.format(cal1.getTime());

							//Client client2 = clientDAO.getClientDetails(clientId);

							/*UserProfileDAO userProfileDAO = new JDBCUserProfileDAO(connection);
							UserProfile user = new UserProfile();
							user = userProfileDAO
									.getUserprofileDetails(DateTimeUtils.convertToInteger(practionerId));*/
							NotAvailableSlot notAvailableSlot = new NotAvailableSlot();
							notAvailableSlot.setApmtSlotId(slotid);
							notAvailableSlot.setCommencing(followupdate1);
							notAvailableSlot.setSTime(stime);
							notAvailableSlot.setEndTime(endtime);
							notAvailableSlot.setDiaryUserId((DateTimeUtils.convertToInteger(practionerId)));
							notAvailableSlot.setDiaryUser(user.getFullname());
							notAvailableSlot.setLocation("1");
							notAvailableSlot.setDept(user.getDiciplineName());
							notAvailableSlot.setClient(client2.getFullname());
							notAvailableSlot.setClientId(clientId);
							notAvailableSlot.setApmtDuration("00:05");
							notAvailableSlot.setApmtType("" + appointmentType.getId());
							notAvailableSlot.setRoom("Room1");
							notAvailableSlot.setPayBy("Client");
							notAvailableSlot.setAddedBy("" + loginInfo.getUserId());
							notAvailableSlot.setCondition(user.getDiciplineName());
							notAvailableSlot.setVaccineApmt(true);
							notAvailableSlot.setNotes("");
							notAvailableSlot.setTreatmentEpisodeId("0");
							notAvailableSlot.setUsedsession("0");
							notAvailableSlot.setOtid(0);
							notAvailableSlot.setOtplan("" + 0);
							notAvailableSlot.setProcedure("" + 0);
							notAvailableSlot.setSurgeon("" + 0);
							notAvailableSlot.setAnesthesia("" + 0);
							notAvailableSlot.setIpdno("" + 0);
							notAvailableSlot.setWardid("" + 0);
							notAvailableSlot.setAssistaffcharge("" + 0);
							notAvailableSlot.setSic("" + 0);
							notAvailableSlot.setPsurcharge("" + 0);
							notAvailableSlot.setPanetcharge("" + 0);
							notAvailableSlot.setMobstatus("0");
							DateFormat dateFormat1 = new SimpleDateFormat("HH:mm:ss");
							Calendar cal2 = Calendar.getInstance();
							notAvailableSlot.setOpdbooktime(dateFormat1.format(cal2.getTime()));
							notAvailableSlot.setSpeciality(user.getDiciplineName());
							int x = notAvailableSlotDAO.saveAppointment(notAvailableSlot);
						}
					}
				}
				
				 
				//email1();
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			if(loginInfo.getClinicid1().equals("raiprachi")) {
				
			 return "printpriscription1";
			}else {
			return "printConsulatationForm";
			}
	       
		}
		
		private void saveTreatmentPlan(String clientId, String clientname, String apmtid)throws Exception {
			Connection connection = null;
			try {

				connection = Connection_provider.getconnection();
				AdditionalDAO additionalDAO = new JDBCAdditionalDAO(connection);
				AccountsDAO accountsDAO = new JDBCAccountsDAO(connection);
			
				
				String date = DateTimeUtils.getDateinSimpleFormate(new Date());
				String location = "1";
				String date_time = DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone());
				// String creditDebitCharge = accountsForm.getCreditDebitCharge();
				String payby = "0";	
				String stemp[] = date.split(" ");

				String temp[] = stemp[0].split("-");
				date = temp[2] + "-" + temp[1] + "-" + temp[0];
				
				//date = DateTimeUtils.getCommencingDate1(accountsForm.getDate());

				if (payby.equalsIgnoreCase("1")) {
					payby = Constants.PAY_BY_THIRD_PARTY;
				} else {
					payby = Constants.PAY_BY_CLIENT;
				}

				
				
				
				ArrayList<CompleteAppointment> assesmentList = additionalDAO.getCompleteApmtList(clientId,loginInfo.getId());
							for (CompleteAppointment appointment : assesmentList) {
					//  27 Jan 2018 pass date1 instead of date
					int invoiceid = additionalDAO.saveInvoce(clientId, clientname,
										"", date, location, apmtid,loginInfo.getUserId());
					String date1= appointment.getCommencing();
					
//					int result = additionalDAO.saveAssessment(clientId, clientname,
//							type, date1, invoiceid, appointment);
					if(loginInfo.getIskunal()==1){
						appointment.setIskunal(true);
					}else{
						appointment.setIskunal(false);
					}
					appointment.setDate(DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone()));
					appointment.setUserid(loginInfo.getUserId());
					CompleteAptmDAO aptmDAO=new JDBCCompleteAptmDAO(connection);
					int result=aptmDAO.saveInvoiceAssesment(appointment, invoiceid);
					int log=aptmDAO.saveManualChargeLog(appointment,invoiceid);
				}
				CompleteAptmDAO completeAptmDAO = new JDBCCompleteAptmDAO(
						connection);

				int result = completeAptmDAO.deleteComplteApmt(loginInfo.getId());
				String raiseChargeType = "1";
				CommonOpdIpdReport commonOpdIpdReport=new CommonOpdIpdReport();
				commonOpdIpdReport.patientTranssection(clientId,date_time);
				//if(accountsForm.getClraradv().equals("1")){
					 int advupdate=accountsDAO.updateAdvancerefundzero(clientId);
				// }
				//accountsForm.setClientId(clientId);
				//accountsForm.setClient(clientname);
				//accountsForm.setPayby("Client");
				session.setAttribute("clientId", clientId);
				session.setAttribute("clientname", clientname);
				session.setAttribute("payby", payby);
				session.setAttribute("raiseChargeType", raiseChargeType);
				session.setAttribute("location", location);

			} catch (Exception e) {
				e.printStackTrace();
			}finally{
				
				connection.close();
			}
			
		}

		private void email1() throws Exception{

			
			Connection connection = null;
			
			try{
				int loginId = loginInfo.getId();
				connection = Connection_provider.getconnection();
				AccountsDAO accountsDAO = new JDBCAccountsDAO(connection);
				String id = request.getParameter("id");
				String to = "priyankagadbail90@gmail.com";
				String cc = "";
				String subject = "hello file";
				String notes = "dfbefewhfewh";
				String filename1="";
				String filename = DateTimeUtils.isNull((String)session.getAttribute("pdfFileName"));
				if((filename).contains("/")){
					String[] temp1 = filename.split("/");
					filename1 = temp1[1];	
				}
				
			//	int invoiceid = (Integer)session.getAttribute("chargesInvoiceid");
				DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
				Date date = new Date();
				String d1 = dateFormat.format(date);
				String[] temp = d1.split("\\s+");
				String date1 = temp[0];
				String time = temp[1];
				String clientid = request.getParameter("clientid");
				String type = request.getParameter("type");
				
				int result = accountsDAO.saveEmailLogDetails(to, cc, subject, notes, filename1,0,DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone()),time,type);
				
				//set deliver status
				int update = accountsDAO.updateDeliverStatus(0,"2");
				String status = "Email";
				//int upPaymentStatus = accountsDAO.updatePaymentDeliverStatus(id,status);
				
				EmailLetterLog emailLetterLog = new EmailLetterLog();
				emailLetterLog.setClientId(clientid);
				emailLetterLog.setType(status);
				
				EmbeddedImageEmailUtil.sendMailAttachment(connection,loginId,to, cc, subject, notes, filename,loginInfo,emailLetterLog,"0");
				
				
				
			}catch(Exception e){
				e.printStackTrace();
			}finally{
				
				connection.close();
			}
			
	
					
		}

		private void createPdf1(int result1, String email) {
			
			
	    	String smsUrl = "http://157.119.43.54:8081/PdfProject/generate-pdf/"+result1+"/"+loginInfo.getClinicid1()+"/"+email;
			
			//String smsUrl = "http://localhost:8080/generate-pdf/"+result1+"/"+loginInfo.getClinicid1()+"/"+email;
			
			try{
				URL url = new URL(smsUrl);	// get url from url string
				
				URLConnection urlConnection = url.openConnection();	// open url connection
				urlConnection.connect();	// connect to url
				
				HttpURLConnection httpConnection = (HttpURLConnection) urlConnection;	// get http url connection object from url connection
				
				int responseCode = httpConnection.getResponseCode();
				
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		}

		public String printConsulatationForm(){
			Connection connection = null;
			try {
				connection = Connection_provider.getconnection();
				String clientId = request.getParameter("clientId");
				String apmtId = request.getParameter("apmtId");
				String id=request.getParameter("id");
				if(!DateTimeUtils.isNull((String)session.getAttribute("printCFClientid")).equals("")){
					clientId=(String)session.getAttribute("printCFClientid");
					apmtId=(String)session.getAttribute("printCFapmtId");
					session.removeAttribute("printCFClientid");
					session.removeAttribute("printCFapmtId");
				}
				getConsultationNote(clientId,apmtId,id);
				
				Clinic clinic = new Clinic();
				ClinicDAO clinicDAO = new JDBCClinicDAO(connection);
				AccountsDAO accountsDAO = new JDBCAccountsDAO(connection);

				ArrayList<Clinic> locationAdressList = accountsDAO.getLocationAddress(loginInfo.getClinicid());
				if(loginInfo.getClinicid1().equals("manasclinic")) {
					NotAvailableSlotDAO notAvailableSlotDAO=new JDBCNotAvailableSlotDAO(connection);
					String clinicstaff_id=notAvailableSlotDAO.getclinicstaff(loginInfo.getId());
					clinic = clinicDAO.getCliniclistDetails(Integer.parseInt(clinicstaff_id));
				}else {
					clinic = clinicDAO.getCliniclistDetails(loginInfo.getClinicid());
				}

				//clinic = clinicDAO.getCliniclistDetails(loginInfo.getClinicid());
				if(loginInfo.getClinicid1().equals("manasclinic")) {
					ipdForm.setClinicName(clinic.getManas_clinic_name());
				}else {
					ipdForm.setClinicName(clinic.getClinicName());
				}
				//ipdForm.setClinicName(clinic.getClinicName());
				if(loginInfo.isSimpliclinic()) {
					String clinicOwner=clinic.getClinicOwner().split(",")[0];
					String clinicOwner1=clinic.getClinicOwner().split(",")[1];
					ipdForm.setClinicOwner(clinicOwner);
					ipdForm.setClinicOwner1(clinicOwner1);
					String OWNER_QUALIFICATION=clinic.getOwner_qualification().split("/")[0];
					String OWNER_QUALIFICATION1=clinic.getOwner_qualification().split("/")[1];
					ipdForm.setOwner_qualification(OWNER_QUALIFICATION);
					ipdForm.setOwner_qualification1(OWNER_QUALIFICATION1);
				}else {
					ipdForm.setClinicOwner(clinic.getClinicOwner());
					ipdForm.setOwner_qualification(clinic.getOwner_qualification());
				}
			
				
				ipdForm.setLocationAdressList(locationAdressList);
				ipdForm.setAddress(clinic.getAddress());
				ipdForm.setLandLine(clinic.getLandLine());
				ipdForm.setClinicemail(clinic.getEmail());
				ipdForm.setWebsiteUrl(clinic.getWebsiteUrl());
				ipdForm.setClinicLogo(clinic.getUserImageFileName());
				
			} catch (Exception e) {
				e.printStackTrace();
			}
			if(loginInfo.isSimpliclinic()) {
				return "printconsultation";
			}else if(loginInfo.isBams1() || loginInfo.isMbbs()){
				return "bams_opdconsultpage";
			}else if(loginInfo.isTotehosp()) {
				return "printConsultationtote";
			}else {
			    return "printConsultationPage";
			}
		}

public String savecfmedicine(){
	try {
		Connection connection= Connection_provider.getconnection();
		BedDao bedDao = new JDBCBedDao(connection);
		BufferedReader br=request.getReader();
		String line="";
		String inputjson="";
		if((line=br.readLine())!=null){
			inputjson=inputjson+line;
		}
		JSONObject jsonObject= new JSONObject(inputjson);
		String id=jsonObject.getString("id");
		String val=jsonObject.getString("val");
		String column=jsonObject.getString("column");
		String priscqtys=jsonObject.getString("priscqtys");
		int res=bedDao.saveCFMedicineFieldsData(id, column, val,priscqtys);
		
		String responsetext=jsonObject.toString();
		response.setContentType("application/json");
		response.setHeader("Cache-Control", "no-cache");
		response.getWriter().write(responsetext);
		
	} catch (Exception e) {
		e.printStackTrace();
	}
	return null;
}

public String getinvestigationlistforCF() {
	
	try {
		BufferedReader br=request.getReader();
		String line="";
		String inputjson="";
		if((line=br.readLine())!=null){
			inputjson=inputjson+line;
		}
		JSONObject jsonObject= new JSONObject(inputjson);
		String ids=jsonObject.getString("ids");
		Connection connection = Connection_provider.getconnection();
		String list = "";
		InvestigationDAO investigationDAO = new JDBCInvestigationDAO(connection);
		list = investigationDAO.getListOFCFInvestigation(ids);
		JSONObject jsonobj = new JSONObject();
		jsonobj.put("textdata", list);
		
		String responsetext=jsonobj.toString();
		response.setContentType("application/json");
		response.setHeader("Cache-Control", "no-cache");
		response.getWriter().write(responsetext);
	} catch (Exception e) {
		e.printStackTrace();
	}
	return null;
}


public String editdischargedate() throws Exception{
	
	
	try {
		
		String selectedid=request.getParameter("id");
		Connection connection=Connection_provider.getconnection();
		IpdDAO ipdDAO = new JDBCIpdDAO(connection);
		
		Bed bed=ipdDAO.editdischargeDate(selectedid);
		String ddate=bed.getDischargedate();
		String data="";
		
		if(ddate.equals("0")) {
			String datetime=DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone());
			String temp[] = datetime.split(" ");
			String date = DateTimeUtils.getCommencingDate1(temp[0]);
			ipdForm.setAdmissiondate(date);
			ipdForm.setDischargedate(date);
			String time[] = temp[1].split(":");
			String hh = time[0];
			String mm = time[1];
			ipdForm.setHour(hh);
			ipdForm.setMinute(mm);
		    data=bed.getId()+"~~"+date+"~~"+hh+"~~"+mm;
			
		}else {
			String temp[] = bed.getDischargedate().split(" ");
			String date = DateTimeUtils.getCommencingDate1(temp[0]);
			ipdForm.setDischargedate(date);
			String time[] = temp[1].split(":");
			String hh = time[0];
			String mm = time[1];
		    data=bed.getId()+"~~"+date+"~~"+hh+"~~"+mm;
		}
		 
		response.setContentType("text/html");
		response.setHeader("Cache-Control", "no-cache");
		response.getWriter().write(""+data+"");
		 
		 
		
	} catch (Exception e) {
		// TODO: handle exception
		e.printStackTrace();
	}
	
	return null;
}

public String updatedischargedate() throws Exception{
	   
	Connection connection = null;
	
	try {
		connection=Connection_provider.getconnection();
		
		String clientid=request.getParameter("clientid");
	    String dischrgedate=request.getParameter("dischargedate");
	    String hour=request.getParameter("hour");
	    String min=request.getParameter("min");
	    
	    String date = DateTimeUtils.getCommencingDate1(ipdForm.getDischargedate()) + " " + hour + ":" + min + ":20" ;

		
		IpdDAO ipdDAO = new JDBCIpdDAO(connection);
		
		int result=ipdDAO.getupdateDischrgeDate(clientid,date);
		
		
		response.setContentType("text/html"); 
		response.setHeader("Cache-Control", "no-cache"); 
		response.getWriter().write(""+result+"");
		
	} catch (Exception e) {
		// TODO: handle exception
		e.printStackTrace();
	}
	
	return null;
}


public String ipdfakeConsultreport() throws Exception{
	

	Connection connection = null;
	try {
		connection = Connection_provider.getconnection();
		IpdDAO ipdDAO = new JDBCIpdDAO(connection);
		MasterDAO masterDAO = new JDBCMasterDAO(connection);
		String fromdate = ipdForm.getFromdate();
		String todate = ipdForm.getTodate();
		String doctorid=ipdForm.getPractitionerid();
		String userid=DateTimeUtils.isNull(ipdForm.getUserid());
		String searchtext=ipdForm.getSearchtext();
		String actiontype=DateTimeUtils.isNull(request.getParameter("IpdpatientType"));
		String fakclientid=request.getParameter("fake_clientid");
		String fuhid=request.getParameter("uhid");
		String deptid=DateTimeUtils.isNull(ipdForm.getDisciplineid());
		NotAvailableSlotDAO notAvailableSlotDAO = new JDBCNotAvailableSlotDAO(connection);
	    String action=DateTimeUtils.isNull(request.getParameter("action"));
		
		String fdeptname=masterDAO.departmentid(deptid);
		
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
	
	if(!loginInfo.getClinicid1().equals("chraibgh")) {	
		if(!deptid.equals("")) {
			
			String deptdate=ipdDAO.getdepmnDate(deptid);
			if(!deptdate.equals("")) {
				deptdate=deptdate.split(" ")[0];
			   }
			fromdate=deptdate;
		}
	}
		
		
	 if(actiontype.equals("1")) {
		 String fdate=ipdDAO.getfakedatebyClientid(fakclientid);
		 fdate=DateTimeUtils.isNull(fdate);
		  if(!fdate.equals("")) {
			  fdate=fdate.split(" ")[0];
		   }
		  actiontype=DateTimeUtils.isNull(actiontype);
		  if(!fdate.equals("")) {
		    if(actiontype.equals("1")){
			  fromdate=fdate;
			  searchtext=fakclientid;
		}
	  }
		
	}
	 
	 
	 if(action.equals("fake")) {
		 String com=(String) session.getAttribute("commencingdte");
		 com = DateTimeUtils.getCommencingDate1(com);
		 fromdate=com;
	 }
		
		ArrayList<DiaryManagement> userList = notAvailableSlotDAO.getUserList(loginInfo.getId());
		ipdForm.setUserList(userList);
		
		ArrayList<DiaryManagement> ipdfakecinsultdeptlist = notAvailableSlotDAO.getipdfakeDepartment();
		ipdForm.setIpdconsultdeptlist(ipdfakecinsultdeptlist);
		
		int count = ipdDAO.getipdFakeconsultcount(fromdate,todate,doctorid);
		
		ipdForm.setTotalRecords(count);
		ArrayList<Bed> ipdfakeconsultReport = ipdDAO.getIpdConsultFake(fromdate,todate,doctorid,userid,searchtext,deptid);
		ipdForm.setIpdReport(ipdfakeconsultReport);
		
		ArrayList<DiaryManagement> admitteduserlist = notAvailableSlotDAO.getadmittedbyUserList();
		ipdForm.setAdmittedbyuserList(admitteduserlist);
		String datetime=DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone());
		String temp[] = datetime.split(" ");
		String date = DateTimeUtils.getCommencingDate1(temp[0]);
		ipdForm.setAdmissiondate(date);
		ipdForm.setDischargedate(date);
		String time[] = temp[1].split(":");
		String hh = time[0];
		String mm = time[1];
		ipdForm.setHour(hh);
		ipdForm.setMinute(mm);


        ipdForm.setHourList(PopulateList.hourListNew());
        ipdForm.setMinuteList(PopulateList.getMinuteList());  
		
		ipdForm.setFromdate(DateTimeUtils.getCommencingDate1(fromdate));
		ipdForm.setTodate(DateTimeUtils.getCommencingDate1(todate));
		
		Clinic clinic = new Clinic();
		ClinicDAO clinicDAO = new JDBCClinicDAO(connection);
		AccountsDAO accountsDAO = new JDBCAccountsDAO(connection);
		ArrayList<Clinic> locationAdressList = accountsDAO.getLocationAddress(loginInfo.getClinicid());
		clinic = clinicDAO.getCliniclistDetails(loginInfo.getClinicid());
		ipdForm.setClinicName(clinic.getClinicName());
		ipdForm.setClinicOwner(clinic.getClinicOwner());
		ipdForm.setOwner_qualification(clinic.getOwner_qualification());
		ipdForm.setLocationAdressList(locationAdressList);
		ipdForm.setAddress(clinic.getAddress());
		ipdForm.setLandLine(clinic.getLandLine());
		ipdForm.setClinicemail(clinic.getEmail());
		ipdForm.setWebsiteUrl(clinic.getWebsiteUrl());
		ipdForm.setClinicLogo(clinic.getUserImageFileName());
		ArrayList<Bed> ipdgendercountlist=ipdDAO.ipdDgetIpdgenderList(fromdate,todate);
		ipdForm.setIpdgendercountlist(ipdgendercountlist);
	} catch (Exception e) {
		e.printStackTrace();
	}
	return "ipdfakeconsultreport";

	
	
	
	
}

public String printnote() throws Exception{
	Connection connection = null;
	try {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		connection = Connection_provider.getconnection();
		String clientId = request.getParameter("clientId");
		String apmtId = request.getParameter("apmtId");
		String id=request.getParameter("id");
		String datetime=request.getParameter("date_time");
		datetime=datetime.split(" ")[0];	
		IpdDAO ipdDAO = new JDBCIpdDAO(connection);
		AccountsDAO accountsDAO = new JDBCAccountsDAO(connection);
		ClientDAO clientDAO=new JDBCClientDAO(connection);
		Bed bed = ipdDAO.getConsultationFormDetails(apmtId,id);
		ArrayList<Bed>dailynoteslist=ipdDAO.getDailyNotelist(apmtId,id,datetime);
		session.setAttribute("dailynoteslist", dailynoteslist);		
		ipdForm.setChiefcomplains(bed.getChiefcomplains());
		ipdForm.setExample(bed.getNotes());
		ipdForm.setInvestigation(bed.getInvestigation());
		ipdForm.setDiscadvnotes(bed.getDiscadvnotes());
		ipdForm.setKunal_manual_medicine_text(bed.getKunal_manual_medicine_text());
		ipdForm.setExamination(bed.getExamination());
		ipdForm.setOndiet(bed.getOndiet());
		ipdForm.setKarma(bed.getKarma());
		ipdForm.setProcedurebams(bed.getProcesurebams());
		
		session.setAttribute("examplenote", ipdForm.getExample());
		
		
		Client client = clientDAO.getClientDetails(clientId);
	
	String fullname = client.getTitle() + " " + client.getFirstName() + " "+client.getMiddlename()+" " + client.getLastName();
	ipdForm.setClient(fullname);
	ipdForm.setRegno(client.getAbrivationid());
	String whopay=client.getWhopay();
	ipdForm.setAbrivationid(client.getAbrivationid());
	ipdForm.setPatientIdAbrivation(client.getPatientIdAbrivation());
	if(whopay==null){
		whopay="";
	}
	if(!whopay.equals("Client")){
		ThirdPartyDAO thirdPartyDAO = new JDBCThirdPartyDAO(connection);
		if(DateTimeUtils.numberCheck(bed.getTpid()).equals("0") && (!DateTimeUtils.numberCheck(bed.getBedid()).equals("0"))){
			String typeName = client.getTypeName();
			if(!DateTimeUtils.numberCheck(typeName).equals("0")){
				bed.setTpid(typeName);
				ipdForm.setTpid(bed.getTpid());
				//int result = ipdDAO.updateAdm_Tp(selectedid,typeName);
				//result = ipdDAO.updateAdmNew_Tp(selectedid,typeName);
			}
			
		}
		ThirdParty thirdParty = thirdPartyDAO.getThirdPartyDetails(bed.getTpid());
		ipdForm.setThirdParty(thirdParty.getCompanyName());
	}else{
		ipdForm.setThirdParty("Self");
	}
	String birthtime= "00:00:00";
	if(!DateTimeUtils.isNull(client.getBirthtime()).equals("")){
		birthtime=client.getBirthtime().replaceAll(" ", "");
	}
			
	 String hh1="";
	 String mm1="";
	 String apmpm1="";
	 if(!birthtime.equals("00:00:00")){
		 String time[] = birthtime.split(":");
		 hh1 = time[0];
		 hh1=hh1.replaceAll(" ", "");
		 mm1 = time[1];
		 mm1=mm1.replaceAll(" ", "");
		 	int hourOfDay1=Integer.parseInt(hh1);
		   int minute1=Integer.parseInt(mm1);
		    apmpm1 =  ((hourOfDay1 > 12) ? hourOfDay1 % 12 : hourOfDay1) + ":" + (minute1 < 10 ? ("0" + minute1) : minute1) + " " + ((hourOfDay1 >= 12) ? "PM" : "AM");	 
	 }
	//lokesh
	String agegender="";
	String dob = client.getDob();
	String age = DateTimeUtils.getAge1(client.getDob());
/*	String age1[]= age.split("(?<=\\D)(?=\\d)|(?<=\\d)(?=\\D)");
	age= age1[0];
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
	}*/
	if(!birthtime.equals("00:00:00") && !loginInfo.isBalgopal()){
		agegender = age +"("+apmpm1+")"+ " / " + client.getGender();
	}else{
	agegender = age + " / " + client.getGender();	
	}
	ipdForm.setAge(age);
	ipdForm.setAgegender(agegender);
	ipdForm.setRelativename(client.getEmergencyContName());
	ipdForm.setRelationcont(client.getEmergencyContNo());
	ipdForm.setRelation(client.getRelation());
	
	boolean isfamilyd=false;
	
	if(ipdForm.getRelativename()!=null){
		 
		if(ipdForm.getRelativename().equals("")){
			 
			ipdForm.setRelativename(null);
		}
	}

	if(ipdForm.getRelationcont()!=null){
		 
		if(ipdForm.getRelationcont().equals("")){
			 
			ipdForm.setRelationcont(null);
		}
	}

	if(ipdForm.getRelation()!=null){
		 
		if(ipdForm.getRelation().equals("")){
			 
			ipdForm.setRelation(null);
		}
	}
	
	if(ipdForm.getRelativename()==null&&ipdForm.getRelationcont()==null&&ipdForm.getRelation()==null){
		isfamilyd=true;
	}
	/*if(ipdForm.getRelationcont()==null){
		isfamilyd=true;
	}
	if(ipdForm.getRelation()==null){
		isfamilyd=true;
	}
	*/
	if(isfamilyd){
		ipdForm.setFamilyDetails("");
	} else {
		ipdForm.setFamilyDetails("ee");
	}
	
	String numcount=ipdDAO.getNumofAdmissionCount(ipdForm.getClientid());
	ipdForm.setNum_admission(numcount);
	ipdForm.setDob(client.getDob());
	ipdForm.setAddress(client.getAddress()+", "+client.getTown()+"-"+client.getPostCode()+", "+DateTimeUtils.isNull(client.getCounty())  );
	if(client.getAlternate_mobno()!=null){
		ipdForm.setContact(client.getMobNo()+","+client.getAlternate_mobno());
	}else {
		ipdForm.setContact(client.getMobNo());
	}
	  ipdForm.setMothername(client.getMothername());
	  ipdForm.setFathername(client.getFathername());
	  ipdForm.setBirthplace(client.getBirthplace());
		
		ClinicDAO clinicDAO = new JDBCClinicDAO(connection);
		Clinic clinic = new Clinic();
		clinic = clinicDAO.getCliniclistDetails(loginInfo.getClinicid());
		ArrayList<Clinic> locationAdressList = accountsDAO.getLocationAddress(loginInfo.getClinicid());
		ipdForm.setClinicName(clinic.getClinicName());
		ipdForm.setClinicOwner(clinic.getClinicOwner());
		ipdForm.setOwner_qualification(clinic.getOwner_qualification());
		ipdForm.setLocationAdressList(locationAdressList);
		ipdForm.setAddress(clinic.getAddress());
		ipdForm.setLandLine(clinic.getLandLine());
		ipdForm.setClinicemail(clinic.getEmail());
		ipdForm.setWebsiteUrl(clinic.getWebsiteUrl());
		ipdForm.setClinicLogo(clinic.getUserImageFileName());
		
		
	} catch (Exception e) {
		e.printStackTrace();
	}
	
	
	
	return "printn";
}



public String addnewdoctor() throws Exception {
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
		String doctor = jsonObject.getString("doctor");
		String share = jsonObject.getString("share");
		Master master = new Master();

		master.setDoctorName(doctor);
		master.setShare(share);
		
		int res = masterDAO.addNewDoctor(master);

		response.setContentType("application/json");
		response.setHeader("Cache-Control", "no-cache");
		response.getWriter().write("1");
	} catch (Exception e) {
		e.printStackTrace();
	} finally {

		connection.close();
	}
	return null;
}



public String consultationNotesForOphthamologist()throws Exception{
	Connection connection = null;
	try {
		connection = Connection_provider.getconnection();
		String clientId = request.getParameter("clientId");
		String apmtId = request.getParameter("apmtId");
		
		String fromDate=ipdForm.getFromDate();
		
		if(DateTimeUtils.isNull(fromDate).equals("")){
			DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			fromDate = dateFormat.format(new Date()); 
		}else{
			fromDate = DateTimeUtils.getCommencingDate1(fromDate);
		}
        
		ipdForm.setFromDate(DateTimeUtils.getCommencingDate1(fromDate));
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		connection = Connection_provider.getconnection();
		IpdDAO ipdDAO = new JDBCIpdDAO(connection);
		BedDao bedDao = new JDBCBedDao(connection);
		IpdLogDAO ipdLogDAO = new JDBCIpdLogDAO(connection);
		ClientDAO clientDAO = new JDBCClientDAO(connection);
		MasterDAO masterDAO = new JDBCMasterDAO(connection);
		EmrDAO emrDAO = new JDBCEmrDAO(connection);
		UserProfileDAO userProfileDAO = new JDBCUserProfileDAO(connection);
		NotAvailableSlotDAO notAvailableSlotDAO = new JDBCNotAvailableSlotDAO(connection);
		listOfExtraData();
		ArrayList<Bed> consultationhistoryList = ipdDAO.getConsultantNoteHistory(clientId);
		ipdForm.setConsulthistorylist(consultationhistoryList);
		Client client = clientDAO.getClientDetails(clientId);
		String datetime =ipdDAO.getDateTime(clientId,apmtId);
		ipdForm.setContact(client.getMobNo());
		ipdForm.setAddress(client.getAddress());
		ipdForm.setMiddlename(client.getMiddlename());
		if(loginInfo.isBams1()) {
			datetime=datetime.split(" ")[0];
			ipdForm.setDatetime(datetime);
		}else {
			ipdForm.setDatetime(datetime);
		}
		//ipdForm.setDatetime(datetime);
		if (client.getWhopay().equals(Constants.PAY_BY_CLIENT)) {
			ipdForm.setWhopay("Self");
		} else {
			ThirdParty thirdParty = client.getTpDetails();
			ipdForm.setWhopay(thirdParty.getCompanyName());
		}
		Bed bed = ipdDAO.getConsultationFormDetails(apmtId,"");
		if(bed.getId()>0){
			ipdForm.setChiefcomplains(bed.getChiefcomplains());
			ipdForm.setLSplRFI(bed.getLSplRFI());
			ipdForm.setLCylRFI(bed.getLCylRFI());
			ipdForm.setLAxisRFI(bed.getLAxisRFI());
			ipdForm.setRSplRFI(bed.getRSplRFI());
			ipdForm.setRCylRFI(bed.getRCylRFI());
			ipdForm.setRAxisRFI(bed.getRAxisRFI());
			ipdForm.setEyereading(bed.getEyereading());
		}else{
			ipdForm.setChiefcomplains("");
			ipdForm.setChiefcomplains("");
			ipdForm.setLSplRFI("");
			ipdForm.setLCylRFI("");
			ipdForm.setLAxisRFI("");
			ipdForm.setRSplRFI("");
			ipdForm.setRCylRFI("");
			ipdForm.setRAxisRFI("");
			ipdForm.setEyereading("");
			
		}
		
		ArrayList<Master> chief_complaints_list = masterDAO.getConsultantNotesTemplateList("Chief Complaints");
		ipdForm.setChief_complaints_list(chief_complaints_list);
		ArrayList<Master> investigationList = masterDAO.getConsultantNotesTemplateList("Investigations");//Consultant Form Investigations
		ipdForm.setInvestigationList(investigationList);
		InvestigationDAO investigationDAO = new JDBCInvestigationDAO(connection);
		ArrayList<InvstTemplate> templateList = investigationDAO.getTemplateList();
		ipdForm.setInvestigationtemplatelist(templateList);
		ArrayList<Master> pkgsList = investigationDAO.getInvPaksLists();
		ipdForm.setPkgsList(pkgsList);
		ArrayList<Master> invsTypeList = emrDAO.getInvesigationTypeList();
		ipdForm.setInvsTypeList(invsTypeList);
		ArrayList<Master> invstReportTypeList = emrDAO.getInvstReportTypeList();
		ipdForm.setInvstReportTypeList(invstReportTypeList);
		ArrayList<Master> invstUnitList = emrDAO.getInvstUnitList();
		ipdForm.setInvstUnitList(invstUnitList);
		ArrayList<Master> invSectionList = investigationDAO.getInvestigationSectionList();
		ipdForm.setInvSectionList(invSectionList);
		//set treatment episode
		String fullname = client.getTitle() + " " + client.getFirstName() + " "+client.getMiddlename()+" " + client.getLastName();
		ipdForm.setClient(fullname);
		ipdForm.setRegno(client.getAbrivationid());
		String whopay=client.getWhopay();
		ipdForm.setAbrivationid(client.getAbrivationid());
		ipdForm.setPatientIdAbrivation(client.getPatientIdAbrivation());
		if(whopay==null){
			whopay="";
		}
		if(!whopay.equals("Client")){
			ThirdPartyDAO thirdPartyDAO = new JDBCThirdPartyDAO(connection);
			ThirdParty thirdParty = thirdPartyDAO.getThirdPartyDetails(client.getTypeName());
			ipdForm.setThirdParty(thirdParty.getCompanyName());
		}else{
			ipdForm.setThirdParty("Self");
		}
		String birthtime=client.getBirthtime().replaceAll(" ", "");
		String hh1="";
		String mm1="";
		String apmpm1="";
		if(!birthtime.equals("00:00:00")){
			String time[] = birthtime.split(":");
			hh1 = time[0];
			hh1=hh1.replaceAll(" ", "");
			mm1 = time[1];
			mm1=mm1.replaceAll(" ", "");
			int hourOfDay1=Integer.parseInt(hh1);
			int minute1=Integer.parseInt(mm1);
			apmpm1 =  ((hourOfDay1 > 12) ? hourOfDay1 % 12 : hourOfDay1) + ":" + (minute1 < 10 ? ("0" + minute1) : minute1) + " " + ((hourOfDay1 >= 12) ? "PM" : "AM");	 
		}
		String agegender="";
		String dob = client.getDob();
		String age = DateTimeUtils.getAge1(client.getDob());
		if(!birthtime.equals("00:00:00")){
			agegender = age +"("+apmpm1+")"+ " / " + client.getGender();
		}else{
			agegender = age + " / " + client.getGender();	
		}
		ipdForm.setAge(age);
		ipdForm.setAgegender(agegender);
		ipdForm.setRelativename(client.getEmergencyContName());
		ipdForm.setRelationcont(client.getEmergencyContNo());
		ipdForm.setRelation(client.getRelation());
		NotAvailableSlot notAvailableSlot=new NotAvailableSlot();
		//rahul chnge below condition add id2.equals("")
	    notAvailableSlot=notAvailableSlotDAO.getAvailableSlotdata(Integer.parseInt(apmtId));
		
		UserProfile userProfile = userProfileDAO.getUserprofileDetails(notAvailableSlot.getDiaryUserId());
		Master discipline = masterDAO.getDisciplineData(userProfile.getDiciplineName());
		ipdForm.setDiscipline(discipline.getDiscipline());
		ipdForm.setDoctor_name(userProfile.getInitial() + " " + userProfile.getFirstname() + " " + userProfile.getLastname());
		ipdForm.setDoctor_phone(userProfile.getMobile());
		ipdForm.setClientid(clientId);
		ipdForm.setNewipdabbr(notAvailableSlot.getOpdAbbrId());
		ipdForm.setIpdseqno(""+notAvailableSlot.getId());
		ipdForm.setApmtId(apmtId);
	} catch (Exception e) {
		e.printStackTrace();
	}
	return "consultationOphthamo";
	//return "discharge";
}

public String printophtomoConsultationForm() throws Exception{
	
	Connection connection = null;
	try {
		connection = Connection_provider.getconnection();
		String clientId = request.getParameter("clientId");
		String apmtId = request.getParameter("apmtId");
		String id=request.getParameter("id");
		if(!DateTimeUtils.isNull((String)session.getAttribute("printCFClientid")).equals("")){
			clientId=(String)session.getAttribute("printCFClientid");
			apmtId=(String)session.getAttribute("printCFapmtId");
			session.removeAttribute("printCFClientid");
			session.removeAttribute("printCFapmtId");
		}
		getConsultationNote(clientId,apmtId,id);
		
		Clinic clinic = new Clinic();
		ClinicDAO clinicDAO = new JDBCClinicDAO(connection);
		AccountsDAO accountsDAO = new JDBCAccountsDAO(connection);

		ArrayList<Clinic> locationAdressList = accountsDAO.getLocationAddress(loginInfo.getClinicid());
		if(loginInfo.getClinicid1().equals("manasclinic")) {
			NotAvailableSlotDAO notAvailableSlotDAO=new JDBCNotAvailableSlotDAO(connection);
			String clinicstaff_id=notAvailableSlotDAO.getclinicstaff(loginInfo.getId());
			clinic = clinicDAO.getCliniclistDetails(Integer.parseInt(clinicstaff_id));
		}else {
			clinic = clinicDAO.getCliniclistDetails(loginInfo.getClinicid());
		}

		//clinic = clinicDAO.getCliniclistDetails(loginInfo.getClinicid());
		if(loginInfo.getClinicid1().equals("manasclinic")) {
			ipdForm.setClinicName(clinic.getManas_clinic_name());
		}else {
			ipdForm.setClinicName(clinic.getClinicName());
		}
		//ipdForm.setClinicName(clinic.getClinicName());
		if(loginInfo.isSimpliclinic()) {
			String clinicOwner=clinic.getClinicOwner().split(",")[0];
			String clinicOwner1=clinic.getClinicOwner().split(",")[1];
			ipdForm.setClinicOwner(clinicOwner);
			ipdForm.setClinicOwner1(clinicOwner1);
			String OWNER_QUALIFICATION=clinic.getOwner_qualification().split("/")[0];
			String OWNER_QUALIFICATION1=clinic.getOwner_qualification().split("/")[1];
			ipdForm.setOwner_qualification(OWNER_QUALIFICATION);
			ipdForm.setOwner_qualification1(OWNER_QUALIFICATION1);
		}else {
			ipdForm.setClinicOwner(clinic.getClinicOwner());
			ipdForm.setOwner_qualification(clinic.getOwner_qualification());
		}
	
		
		ipdForm.setLocationAdressList(locationAdressList);
		ipdForm.setAddress(clinic.getAddress());
		ipdForm.setLandLine(clinic.getLandLine());
		ipdForm.setClinicemail(clinic.getEmail());
		ipdForm.setWebsiteUrl(clinic.getWebsiteUrl());
		ipdForm.setClinicLogo(clinic.getUserImageFileName());
		
	} catch (Exception e) {
		e.printStackTrace();
	}
	
	    return "printopthomoConsultationPage";
	
	
	
	
}

public String saveOphtoconsultationForm(){
	Connection connection = null;
	try {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		connection = DriverManager.getConnection(
				"" + Constants.DB_HOST + ":3306/" + loginInfo.getClinicUserid()
						+ "?useUnicode=true&characterEncoding=UTF-8",
				"" + Constants.DB_USER + "", "" + Constants.DB_PWD + "");
		IpdDAO ipdDAO = new JDBCIpdDAO(connection);
		ClientDAO clientDAO = new JDBCClientDAO(connection);
		String clientId = ipdForm.getClientid();
		String apmtId = ipdForm.getApmtId();
		String whopay = ipdForm.getWhopay();
		String thirdParty = ipdForm.getThirdParty();
		String LSplRFI=ipdForm.getLSplRFI();
		String LCylRFI=ipdForm.getLCylRFI();
		String LAxisRFI=ipdForm.getLAxisRFI();
		String RSplRFI=ipdForm.getRSplRFI();
		String RCylRFI=ipdForm.getRCylRFI();
		String RAxisRFI=ipdForm.getRAxisRFI();
		
		
		//Diagnosis Start
		int res = ipdDAO.deleteFinalConditionsCF(apmtId);
		String allconditions = "0";
		if (ipdForm.getConditions() != null) {
			for (Bed bedcondition : ipdForm.getConditions()) {
				if (bedcondition == null) {
					continue;
				}
				allconditions = allconditions + "," + bedcondition.getConditionid();
			}
		}
		int result1 = ipdDAO.savefinalConditionCF(apmtId,allconditions,"0","0","0","0","0","0");
		
		String ids=ipdDAO.getDiagnosisids(apmtId);
		String diagnosis=ipdDAO.getDiagnosisText(ids);
		//Dignosis End
		//Constation Form Save/Update Start
		Bed bed = new Bed();
		bed.setChiefcomplains(new String(DateTimeUtils.isNull(ipdForm.getChiefcomplains()).getBytes("iso-8859-1"), "UTF-8"));
		bed.setClientid(clientId);
		bed.setWhopay(whopay);
		bed.setTpid(thirdParty);
		bed.setAppointmentid(apmtId);
		bed.setUserid(loginInfo.getUserId());
		bed.setDatetime(DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone()));
		bed.setLSplRFI(DateTimeUtils.isNull(LSplRFI));
		bed.setLCylRFI(DateTimeUtils.isNull(LCylRFI));
		bed.setLAxisRFI(DateTimeUtils.isNull(LAxisRFI));
		bed.setRSplRFI(DateTimeUtils.isNull(RSplRFI));
		bed.setRCylRFI(DateTimeUtils.isNull(RCylRFI));
		bed.setRAxisRFI(DateTimeUtils.isNull(RAxisRFI));
		bed.setExamination(new String(DateTimeUtils.isNull(ipdForm.getExamination()).getBytes("iso-8859-1"), "UTF-8"));
		bed.setOndiet(new String(DateTimeUtils.isNull(ipdForm.getOndiet()).getBytes("iso-8859-1"), "UTF-8"));
		bed.setKarma(new String(DateTimeUtils.isNull(ipdForm.getKarma()).getBytes("iso-8859-1"), "UTF-8"));
		bed.setProcesurebams(new String(DateTimeUtils.isNull(ipdForm.getProcedurebams()).getBytes("iso-8859-1"), "UTF-8"));
		bed.setInvestigation(new String(DateTimeUtils.isNull(ipdForm.getInvestigation()).getBytes("iso-8859-1"), "UTF-8"));
		bed.setDiscadvnotes(new String(DateTimeUtils.isNull(ipdForm.getDiscadvnotes()).getBytes("iso-8859-1"), "UTF-8"));
		bed.setNotes(new String(DateTimeUtils.isNull(ipdForm.getExample()).getBytes("iso-8859-1"), "UTF-8"));
		bed.setKunal_manual_medicine_text(new String(DateTimeUtils.isNull(ipdForm.getKunal_manual_medicine_text()).getBytes("iso-8859-1"), "UTF-8"));
	    bed.setEyereading(new String(DateTimeUtils.isNull(ipdForm.getEyereading()).getBytes("iso-8859-1"), "UTF-8"));
	    bed.setPriscription(new String(DateTimeUtils.isNull(ipdForm.getConsNote()).getBytes("iso-8859-1"), "UTF-8"));
	    bed.setHistory(new String(DateTimeUtils.isNull(ipdForm.getExamplehist()).getBytes("iso-8859-1"), "UTF-8"));
	    bed.setConsNote((new String("<b>VITALS</b> <br> L.Spl RFI: "+ipdForm.getLSplRFI())+"  L.Cyl RFI: "+ipdForm.getLCylRFI()+" L.Axis RFI: "+ipdForm.getLAxisRFI()+" R.Spl RFI: "+ipdForm.getRSplRFI()+" R.Cyl RFI: "+ipdForm.getRCylRFI()+" R.Axis RFI: "+ipdForm.getRAxisRFI()+"<br><br>\n"
	            +"<b>PRESENT COMPLAINTS<b><br>"+(new String(DateTimeUtils.isNull("<b></b>"+ipdForm.getChiefcomplains()).getBytes("iso-8859-1"), "UTF-8"))+"\n<br>"
	            +"<b>HISTORY</b><br>"+(new String(DateTimeUtils.isNull("<b></b>"+ipdForm.getExamplehist()).getBytes("iso-8859-1"), "UTF-8"))
	    		+"\n<br><b>DIAGNOSIS</b><br>"+diagnosis+"\n<br><br>"
	    		+"<b>INVESTIGATION<b><br>"+(new String(DateTimeUtils.isNull(ipdForm.getInvestigation()).getBytes("iso-8859-1"), "UTF-8"))+"\n<br>"
				+"<b>FOLLOW UP<b><br>"+(new String(DateTimeUtils.isNull(ipdForm.getDiscadvnotes()).getBytes("iso-8859-1"), "UTF-8"))+"\n"+(new String(DateTimeUtils.isNull(ipdForm.getExample()).getBytes("iso-8859-1"), "UTF-8"))+"\n"+(new String(DateTimeUtils.isNull(ipdForm.getKunal_manual_medicine_text()).getBytes("iso-8859-1"), "UTF-8"))
				+"\n"+(new String(DateTimeUtils.isNull(ipdForm.getConsNote()).getBytes("iso-8859-1"), "UTF-8"))+"<br>"
				+"<b>VISION<b><br>"+(new String(DateTimeUtils.isNull(ipdForm.getEyereading()).getBytes("iso-8859-1"), "UTF-8"))));
		int consulationFormId = ipdDAO.getConsulationFormId(apmtId);
		if(consulationFormId>0){
			//update
			int result = ipdDAO.updateConsultationForm(bed,consulationFormId);
		}else{
			//insert
			int result = ipdDAO.insertConsultationForm(bed);
		}
		session.setAttribute("printCFClientid", clientId);
		session.setAttribute("printCFapmtId", apmtId);
		
		//Save template Start
		if (!DateTimeUtils.isNull(ipdForm.getChiefcomplatetempname()).equals("")) {
			int chiefCompainId = ipdDAO.getIpdTemplateId("Chief Complaints"); //Consultant Form Chief Complaints
			if(chiefCompainId>0){
				int res5 = ipdDAO.saveIPDTemplate(ipdForm.getChiefcomplatetempname(), ""+chiefCompainId, ipdForm.getDepartment(),
						new String(DateTimeUtils.isNull(ipdForm.getChiefcomplains()).getBytes("iso-8859-1"), "UTF-8"));
			}
		}
		//Follow Up
		String followupdate1 = request.getParameter("followupdate1");
		String bkapmtipd = request.getParameter("bkapmtipd");
		
		if(!DateTimeUtils.isNull(followupdate1).equals("")){
			NotAvailableSlotDAO notAvailableSlotDAO = new JDBCNotAvailableSlotDAO(connection);
			String practionerId = notAvailableSlotDAO.getPractIdFromApmtId(apmtId);
			Client client = new Client();
			client.setClientId(clientId);
			client.setOpdid(apmtId);
			client.setIpdid("0");
			client.setType("2");
			client.setPractid(practionerId);
			client.setLocation("EMR");
			client.setFollowupdate(DateTimeUtils.getCommencingDate1(followupdate1));
			DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			Calendar cal = Calendar.getInstance();
			String toDaysDate = dateFormat.format(cal.getTime());
			client.setDate(toDaysDate);
			
			int followup = clientDAO.savefollowup(client);
			boolean flag = false;
			if (followup > 0 && DateTimeUtils.booleanCheck(bkapmtipd).equals("1")) {
				followupdate1 = DateTimeUtils.getCommencingDate1(followupdate1);
				AccountsDAO accountsDAO = new JDBCAccountsDAO(connection);
				whopay = clientDAO.getWhoPayName(clientId);
				AppointmentType appointmentType = accountsDAO.getFollowUpIdCharge(whopay);
				if (appointmentType.getCharges() != null) {
					
					NotAvailableSlot n = notAvailableSlotDAO.getMveDiaryUserDetails("" + practionerId,followupdate1);

					String stime = n.getSTime();
					int slotid = n.getId();
					String duration = n.getDuration();
					boolean chkapmtexsist = notAvailableSlotDAO.chkmveapmtaxsist("" + practionerId,
							followupdate1);
					if (chkapmtexsist) {
						stime = notAvailableSlotDAO.getmveapmtendtime("" + practionerId, followupdate1);
					}
					SimpleDateFormat df = new SimpleDateFormat("HH:mm");
					Date d = df.parse(stime);
					Calendar cal1 = Calendar.getInstance();
					cal1.setTime(d);
					cal1.add(Calendar.MINUTE, 5);
					String endtime = df.format(cal1.getTime());

					Client client2 = clientDAO.getClientDetails(clientId);

					UserProfileDAO userProfileDAO = new JDBCUserProfileDAO(connection);
					UserProfile user = new UserProfile();
					user = userProfileDAO
							.getUserprofileDetails(DateTimeUtils.convertToInteger(practionerId));
					NotAvailableSlot notAvailableSlot = new NotAvailableSlot();
					notAvailableSlot.setApmtSlotId(slotid);
					notAvailableSlot.setCommencing(followupdate1);
					notAvailableSlot.setSTime(stime);
					notAvailableSlot.setEndTime(endtime);
					notAvailableSlot.setDiaryUserId((DateTimeUtils.convertToInteger(practionerId)));
					notAvailableSlot.setDiaryUser(user.getFullname());
					notAvailableSlot.setLocation("1");
					notAvailableSlot.setDept(user.getDiciplineName());
					notAvailableSlot.setClient(client2.getFullname());
					notAvailableSlot.setClientId(clientId);
					notAvailableSlot.setApmtDuration("00:05");
					notAvailableSlot.setApmtType("" + appointmentType.getId());
					notAvailableSlot.setRoom("Room1");
					notAvailableSlot.setPayBy("Client");
					notAvailableSlot.setAddedBy("" + loginInfo.getUserId());
					notAvailableSlot.setCondition(user.getDiciplineName());
					notAvailableSlot.setVaccineApmt(true);
					notAvailableSlot.setNotes("");
					notAvailableSlot.setTreatmentEpisodeId("0");
					notAvailableSlot.setUsedsession("0");
					notAvailableSlot.setOtid(0);
					notAvailableSlot.setOtplan("" + 0);
					notAvailableSlot.setProcedure("" + 0);
					notAvailableSlot.setSurgeon("" + 0);
					notAvailableSlot.setAnesthesia("" + 0);
					notAvailableSlot.setIpdno("" + 0);
					notAvailableSlot.setWardid("" + 0);
					notAvailableSlot.setAssistaffcharge("" + 0);
					notAvailableSlot.setSic("" + 0);
					notAvailableSlot.setPsurcharge("" + 0);
					notAvailableSlot.setPanetcharge("" + 0);
					notAvailableSlot.setMobstatus("0");
					DateFormat dateFormat1 = new SimpleDateFormat("HH:mm:ss");
					Calendar cal2 = Calendar.getInstance();
					notAvailableSlot.setOpdbooktime(dateFormat1.format(cal2.getTime()));
					notAvailableSlot.setSpeciality(user.getDiciplineName());
					int x = notAvailableSlotDAO.saveAppointment(notAvailableSlot);
				}
			}
		}
		
		
		
	} catch (Exception e) {
		e.printStackTrace();
	}
	//return "printConsulatationForm";
	return "printconsultationOphthamo";
}


public String previouscounsultation()throws Exception{
	
	Connection connection=null;
	try {
		String datetime=request.getParameter("date");
		String clientId=request.getParameter("clientId");
		String apmtid=request.getParameter("apmtid");
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		connection = DriverManager.getConnection(
				"" + Constants.DB_HOST + ":3306/" + loginInfo.getClinicUserid()
						+ "?useUnicode=true&characterEncoding=UTF-8",
				"" + Constants.DB_USER + "", "" + Constants.DB_PWD + "");
		IpdDAO ipdDAO = new JDBCIpdDAO(connection);
		StringBuffer str = new StringBuffer();
		ArrayList<Bed> ipdConditionids = (ArrayList<Bed>) session.getAttribute("finalConditions");
		ArrayList<Bed> consultationdetailslist= ipdDAO.getConsultationFormDetailslistbydate(apmtid,clientId,datetime);
		ArrayList<Priscription> priscList = (ArrayList<Priscription>) session.getAttribute("priscList");
		for (Bed bed : consultationdetailslist) {
			
		
		str.append("<div id='consulthist"+bed.getId()+"' style='float:left;width:500px;overflow-y: auto;height: 600px;'>");
		str.append(bed.getConsNote());
		/*
		 * str.
		 * append("<div class='col-lg-12 col-xs-12 col-md-12'><h5>INVESTIGATIONS</h5></div> <div class='col-lg-12 col-md-12 col-xs-12 col-sm-12'><div class='form-group' style='margin-top: 10px;'>"
		 * ); str.append(""+bed.getInvestigation()+""); str.append("</div></div>");
		 * str.append("	<div class='col-lg-12 col-xs-12 col-md-12'" +
		 * "					style='margin-bottom: 5px;'>" +
		 * "					<h5>CHIEF/PRESENT COMPLAINTS AND REASON</h5>" +
		 * "				</div>");
		 * 
		 * 
		 * 
		 * 
		 * str.append("<div class='col-lg-12 col-md-12 col-xs-12 col-sm-12'>" +
		 * "					<div class='form-group' style='margin-top: 10px;'>" +
		 * "						"+bed.getChiefcomplains()+"" +
		 * "					</div>\n" + "				</div>");
		 * 
		 * str.append("<div class='col-lg-12 col-xs-12 col-md-12'>" +
		 * "					<h5>DIAGNOSIS</h5>" + "				</div>");
		 * 
		 * str.
		 * append("<div class='col-lg-12 col-md-12 col-xs-12 col-sm-12\" style=\"padding-bottom: 15px;'>"
		 * + "					<table class='table'>" + "						<tbody>"
		 * + "							<tr>" +
		 * "								<td>Sr. No</td>" +
		 * "								<td>Diagnosis</td>" +
		 * "							</tr>");
		 * 
		 * int i=0; for (Bed bed1 : ipdConditionids) {
		 * 
		 * str.append("<tr>" +
		 * "											<td>"+(++i)+"</td>" +
		 * "											<td>"+bed1.getConditionname()+
		 * "</td>");
		 * 
		 * i++; str.append("</tr>"); } str.append("</tbody>" +
		 * "					</table>" + "				</div>");
		 * 
		 * 
		 * str.append("<div class='form-inline'>" +
		 * "							<div class='form-group'>" +
		 * "								<label for='exampleInputName2'><b>PRESCRIPTION TEXT</b></label>"
		 * + "							</div>" + "						</div>" +
		 * "						<div class='form-group'>"+bed.
		 * getKunal_manual_medicine_text()+"");
		 * 
		 * str.append("</div>");
		 * 
		 * 
		 */
		
	    str.append("</div>");
		str.append("~~"+bed.getId());
		}
		response.setContentType("text/html");
		response.setHeader("Cache-Control", "no-cache");

		response.getWriter().write("" + str.toString() + "");
	} catch (Exception e) {
		e.printStackTrace();
		
	}
	
	return null;
}


public String saveEyeReading()throws Exception{
	Connection connection=null;
	try {
		connection = DriverManager.getConnection(
				"" + Constants.DB_HOST + ":3306/" + loginInfo.getClinicUserid()
						+ "?useUnicode=true&characterEncoding=UTF-8",
				"" + Constants.DB_USER + "", "" + Constants.DB_PWD + "");
		String left_dist=request.getParameter("left_dist");
		String left_nr=request.getParameter("left_nr");
		String with_glass1=request.getParameter("with_glass1");
		String withoutgls1=request.getParameter("withoutgls1");
		String pin1=request.getParameter("pin1");
		String iop1=request.getParameter("iop1");
		String right_dist=request.getParameter("right_dist");
		String right_nr=request.getParameter("right_nr");
		String with_glass2=request.getParameter("with_glass2");
		String withoutgls2=request.getParameter("withoutgls2");
		String pin2=request.getParameter("pin2");
		String iop2=request.getParameter("iop2");
		
		
		StringBuffer str=new StringBuffer();
	
		str.append("<table border='2'>");
		
		str.append("<tr>");		
		 
		str.append("<th>Eye</th>");
		str.append("<th>Distance</th>");
		str.append("<th>Near</th>");
		str.append("<th>With Glasses</th>");
		str.append("<th>Without Glasses</th>");
		str.append("<th>PIN</th>");
		str.append("<th>IOP</th>");
		str.append("</tr>");
	    str.append("<tr>");
	    str.append("<td>Left Eye</td>");
		str.append("<td>"+left_dist+"</td><td>"+left_nr+"</td>");
		str.append("<td>"+with_glass1+"</td><td>"+withoutgls1+"</td>");
		str.append("<td>"+pin1+"</td><td>"+iop1+"</td>");
		str.append("</tr>");
		str.append("</tr>");
	    str.append("<tr>");
	    str.append("<td>Right Eye</td>");
		str.append("<td>"+right_dist+"</td><td>"+right_nr+"</td>");
		str.append("<td>"+with_glass2+"</td><td>"+withoutgls2+"</td>");
		str.append("<td>"+pin2+"</td><td>"+iop2+"</td>");
		str.append("</tr>");
		
	
		str.append("</table>");
		response.setContentType("text/html");
		response.setHeader("Cache-Control", "no-cache");
        response.getWriter().write("" + str.toString() + "");
	} catch (Exception e) {
		e.printStackTrace();
	}finally {
		connection.close();
	}
	
	return null;
}


public String printPriscriptionForm()throws Exception{
	Connection connection=null;
	try {
		String clientId=request.getParameter("clientId");
		String apmtId=request.getParameter("apmtId");
		System.out.println(clientId);		
		EmrDAO emrDAO = new JDBCEmrDAO(connection);
		if(!DateTimeUtils.isNull((String)session.getAttribute("printCFClientid")).equals("")){
			clientId=(String)session.getAttribute("printCFClientid");
			apmtId=(String)session.getAttribute("printCFapmtId");
			session.removeAttribute("printCFClientid");
			session.removeAttribute("printCFapmtId");
		}
		getConsultationNote(clientId,apmtId,"");
		
	} catch (Exception e) {
		e.printStackTrace();
	}
	return "printpriscription";
}


}

