package com.apm.DiaryManagement.web.action;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.print.attribute.standard.MediaSize.ISO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;

import com.apm.Accounts.eu.bi.AccountsDAO;
import com.apm.Accounts.eu.blogic.jdbc.JDBCAccountsDAO;
import com.apm.Accounts.eu.entity.Accounts;
import com.apm.Appointment.eu.bi.AppointmentDAO;
import com.apm.Appointment.eu.bi.AppointmentTypeDAO;
import com.apm.Appointment.eu.blogic.jdbc.JDBCAppointmentDAO;
import com.apm.Appointment.eu.blogic.jdbc.JDBCAppointmentTypeDAO;
import com.apm.Appointment.eu.entity.Appointment;
import com.apm.Appointment.eu.entity.AppointmentType;
import com.apm.AssesmentForms.eu.bi.AssessmentFormDAO;
import com.apm.AssesmentForms.eu.bi.ImportImageForAssessmentDAO;
import com.apm.AssesmentForms.eu.blogic.jdbc.JDBCAssessmentFormDAO;
import com.apm.AssesmentForms.eu.blogic.jdbc.JDBCImportImageAssessmentDAO;
import com.apm.AssesmentForms.eu.entity.Assessment;
import com.apm.Diagnosis.eu.bi.DiagnosisDAO;
import com.apm.Diagnosis.eu.blogic.jdbc.JDBCDiagnosisDAO;
import com.apm.Diagnosis.eu.entity.Diagnosis;
import com.apm.DiaryManagement.eu.bi.ClientDAO;
import com.apm.DiaryManagement.eu.bi.CompleteAptmDAO;
import com.apm.DiaryManagement.eu.bi.DiaryManagementDAO;
import com.apm.DiaryManagement.eu.bi.FinderDAO;
import com.apm.DiaryManagement.eu.bi.NotAvailableSlotDAO;
import com.apm.DiaryManagement.eu.blogic.jdbc.JDBCClientDAO;
import com.apm.DiaryManagement.eu.blogic.jdbc.JDBCCompleteAptmDAO;
import com.apm.DiaryManagement.eu.blogic.jdbc.JDBCDiaryManagentDAO;
import com.apm.DiaryManagement.eu.blogic.jdbc.JDBCFinderDAO;
import com.apm.DiaryManagement.eu.blogic.jdbc.JDBCNotAvailableSlotDAO;
import com.apm.DiaryManagement.eu.entity.Client;
import com.apm.DiaryManagement.eu.entity.CompleteAppointment;
import com.apm.DiaryManagement.eu.entity.DiaryManagement;
import com.apm.DiaryManagement.eu.entity.NotAvailableSlot;
import com.apm.DiaryManagement.eu.entity.Priscription;
import com.apm.DiaryManagement.eu.entity.Tdcode;
import com.apm.DiaryManagement.web.common.ApmDate;
import com.apm.DiaryManagement.web.common.DateOfWeek;
import com.apm.DiaryManagement.web.common.Month;
import com.apm.DiaryManagement.web.form.NotAvailableSlotForm;
import com.apm.Emr.eu.bi.ConsultationNoteDAO;
import com.apm.Emr.eu.bi.EmrDAO;
import com.apm.Emr.eu.bi.InvestigationDAO;
import com.apm.Emr.eu.blogic.jdbc.JDBCConsultationNoteDAO;
import com.apm.Emr.eu.blogic.jdbc.JDBCEmrDAO;
import com.apm.Emr.eu.blogic.jdbc.JDBCInvestigationDAO;
import com.apm.Emr.eu.entity.Emr;
import com.apm.Inventory.eu.bi.InventoryProductDAO;
import com.apm.Inventory.eu.bi.InventoryVendorDAO;
import com.apm.Inventory.eu.blogic.jdbc.JDBCInventoryProductDAO;
import com.apm.Inventory.eu.blogic.jdbc.JDBCInventoryVendorDAO;
import com.apm.Ipd.eu.bi.BedDao;
import com.apm.Ipd.eu.bi.IpdDAO;
import com.apm.Ipd.eu.blogic.jdbc.JDBCBedDao;
import com.apm.Ipd.eu.blogic.jdbc.JDBCIpdDAO;
import com.apm.Ipd.eu.entity.Bed;
import com.apm.Log.eu.bi.AccountLogDAO;
import com.apm.Log.eu.bi.LogDAO;
import com.apm.Log.eu.blogic.jdbc.JDBCAccountLogDAO;
import com.apm.Log.eu.blogic.jdbc.JDBCLogDAO;
import com.apm.Log.eu.entity.EmailLetterLog;
import com.apm.Master.eu.bi.MasterDAO;
import com.apm.Master.eu.bi.TreatmentTypeDAO;
import com.apm.Master.eu.blogic.jdbc.JDBCMasterDAO;
import com.apm.Master.eu.blogic.jdbc.JDBCTreatmentTypeDAO;
import com.apm.Master.eu.entity.Discharge;
import com.apm.Master.eu.entity.Master;
import com.apm.Master.eu.entity.TreatmentType;
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
import com.apm.Report.eu.entity.MisReport;
import com.apm.ThirdParties.eu.bi.ThirdPartyDAO;
import com.apm.ThirdParties.eu.blogic.jdbc.JDBCThirdPartyDAO;
import com.apm.ThirdParties.eu.entity.ThirdParty;
import com.apm.Tools.eu.bi.SMSTemplateDAO;
import com.apm.Tools.eu.blogic.jdbc.JDBCSMSTemplateDao;
import com.apm.Tools.eu.entity.EmailTemplate;
import com.apm.Tools.web.action.AllTemplateAction;
import com.apm.TreatmentEpisode.eu.bi.TreatmentEpisodeDAO;
import com.apm.TreatmentEpisode.eu.blogic.jdbc.JDBCTreatmentEpisode;
import com.apm.TreatmentEpisode.eu.entity.TreatmentEpisode;
import com.apm.common.eu.blogic.jdbc.Connection_provider;
import com.apm.common.utils.DateTimeUtils;
import com.apm.common.utils.Pagination;
import com.apm.common.web.action.BaseAction;
import com.apm.common.web.action.LoginAction;
import com.apm.common.web.common.helper.LoginHelper;
import com.apm.common.web.common.helper.LoginInfo;
import com.apm.common.web.utils.PopulateData;
import com.apm.common.web.utils.PopulateList;
import com.apm.main.common.constants.Constants;
import com.apm.sendemail.java4s.DBScheduler;
import com.apm.sendemail.java4s.Testing;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.Preparable;

import atg.taglib.json.util.JSONObject;
import oracle.net.ano.DataIntegrityService;

public class NotAvailableSlotAction extends BaseAction implements Preparable, ModelDriven<NotAvailableSlotForm> {

	private static final Logger log = Logger.getLogger(LoginAction.class);

	HttpServletRequest request = (HttpServletRequest) ActionContext.getContext().get(ServletActionContext.HTTP_REQUEST);
	HttpServletResponse response = (HttpServletResponse) ActionContext.getContext()
			.get(ServletActionContext.HTTP_RESPONSE);
	HttpSession session = request.getSession(true);
	NotAvailableSlotForm notAvailableSlotForm = new NotAvailableSlotForm();
	private Pagination pagination = new Pagination(5, 1);
	private Pagination pg = null;
	String req_datetime="";
	public Pagination getPagination() {
		return pagination;
	}

	public void setPagination(Pagination pagination) {
		this.pagination = pagination;
	}

	LoginInfo loginInfo = LoginHelper.getLoginInfo(request);

	public String execute() throws Exception {

		if (!verifyLogin(request)) {
			return "login";
		}

		// show practice manager
		Connection connection = null;
		try {

			String selecteduserid = request.getParameter("selecteduserid");
			String selectedCommencing = request.getParameter("selectedCommencing");
			// System.out.println(selecteduserid);

			connection = Connection_provider.getconnection();
			NotAvailableSlotDAO notAvailableSlotDAO = new JDBCNotAvailableSlotDAO(connection);

			// start coding
			ArrayList<DiaryManagement> userList = notAvailableSlotDAO.getUserList(loginInfo.getId());
			notAvailableSlotForm.setUserList(userList);
			notAvailableSlotForm.setDiaryUser(selecteduserid);

			/*
			 * String currentDate = DateTimeUtils.getDateinSimpleFormate(new
			 * Date()); String temp[] = currentDate.split(" ");
			 * 
			 * String temp1[] = temp[0].split("-"); String date = temp1[0] + "/"
			 * + temp1[1] + "/" + temp1[2];
			 */

			String date = DateTimeUtils.getDashboardTodayDate(loginInfo.getTimeZone());
			notAvailableSlotForm.setCommencing(date);

			ClinicDAO clinicDAO = new JDBCClinicDAO(connection);
			String checkValue = clinicDAO.IsMailSend(loginInfo.getId());
			notAvailableSlotForm.setCheckMailSend(checkValue);

		} catch (Exception e) {
			e.printStackTrace();
		} finally {

			connection.close();
		}

		String actionType = request.getParameter("actionType");
		if (actionType != null) {
			if (actionType.equals("mob")) {

				return "mobweek";
			}
		}

		return SUCCESS;
	}

	public String today() {

		String action = request.getParameter("action");
		// System.out.println(action);

		session.removeAttribute("sessioncommencing");

		if (action.equals("dashboard")) {

			return "todayallUser";
		}

		if (action.equals("week")) {

			return "todayweek";
		}
		if (action.equals("day")) {

			return "todayday";

		}

		return null;
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
			/*
			 * String currentDate = DateTimeUtils.getDateinSimpleFormate(new
			 * Date()); String temp[] = currentDate.split(" "); String temp1[] =
			 * temp[0].split("-"); String date = temp1[0] + "/" + temp1[1] + "/"
			 * + temp1[2];
			 */
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
			LoginInfo loginInfo = LoginHelper.getLoginInfo(request);
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
				// session.setAttribute("pgview", "display");
				// session.setAttribute("openedb", "dsplay");
				//
				// pagination = (Pagination)session.getAttribute("pagination");
				// if(pagination==null){
				// pagination = new Pagination(5, 1);
				// }
				//
				// pagination.setPage_size(0);
				// int totalcount =
				// notAvailableSlotDAO.getUserTotalCount(opendb,notAvailableSlotForm.getSelectedjobgroup());
				//
				// pagination.setPreperties(3);
				//
				// int s = 0;
				// int e = 3;
				// if(session.getAttribute("scount")!=null){
				// s = (Integer) session.getAttribute("scount");
				// e = (Integer) session.getAttribute("ecount");
				//
				// s = s+3;
				// e = e+3;
				// }
				//
				// session.setAttribute("scount", s);
				// session.setAttribute("ecount", e);
				//
				// ArrayList<DiaryManagement>list =
				// notAvailableSlotDAO.getIdDashBoardUserList(loginInfo.getId(),pagination,notAvailableSlotForm.getCommencing(),opendb,notAvailableSlotForm.getSelectedjobgroup());
				//
				// DiaryManagement ld = list.get(list.size()-1);
				// int lastid = ld.getId();
				//
				// StringBuffer str = new StringBuffer();
				// for(int i=s;i<e;i++){
				// if(i<=list.size()-1){
				// DiaryManagement d = list.get(i);
				// str.append(d.getId() + ",");
				//
				// if(d.getId()==lastid){
				// session.removeAttribute("scount");
				// }
				// }
				//
				// }
				// String resultstr = "";
				// if(list.size()>0){
				// resultstr = str.toString();
				// if(!resultstr.equals("")){
				// resultstr = resultstr.substring(0,resultstr.length()-1);
				// }
				//
				//
				// }
				//
				//
				// ArrayList<DiaryManagement>userList =
				// notAvailableSlotDAO.getDisplayDashBoardUserList(loginInfo.getId(),pagination,notAvailableSlotForm.getCommencing(),opendb,notAvailableSlotForm.getSelectedjobgroup(),"");
				// //pagination.setPage_records(userList.size());
				// pagination.setPage_records(3);
				//
				ArrayList<DiaryManagement> userList = notAvailableSlotDAO.getUserListwithdept(loginInfo.getId(), "");
				notAvailableSlotForm.setUserList(userList);
				//
				//
				//
				// session.setAttribute("userListSize", userList.size());
				// session.setAttribute("allDiaryUser", userList);
				// session.setAttribute("sessioncommencing",
				// notAvailableSlotForm.getCommencing());
				//
				// notAvailableSlotForm.setLocationid(notAvailableSlotForm.getSelectedLocation());
				//
				// notAvailableSlotForm.setTotalRecords(totalcount);
				// notAvailableSlotForm.setPagerecords(Integer.toString(pagination.getPage_records()));
				//
				String drids = "";
				ArrayList<DiaryManagement> list = notAvailableSlotDAO.getIdDashBoardUserList(loginInfo.getId(),
						pagination, notAvailableSlotForm.getCommencing(), opendb,
						notAvailableSlotForm.getSelectedjobgroup());
				//for token Display
				ArrayList<DiaryManagement> tokenuserList=new ArrayList<>();
				if(loginInfo.getClinicid1().equals("manasclinic") && loginInfo.getId()!=1) {
					tokenuserList = notAvailableSlotDAO.getManasClinicUserList(loginInfo);
				}else {
				    tokenuserList = notAvailableSlotDAO.getUserListwithdeptToken(loginInfo.getId(), "");
				}
				//ArrayList<DiaryManagement> tokenuserList = notAvailableSlotDAO.getUserListwithdeptToken(loginInfo.getId(), "");

				for (DiaryManagement diaryManagement : tokenuserList) {
					// boolean
					// isappoint=notAvailableSlotDAO.checkisappointment(diaryManagement.getId(),notAvailableSlotForm.getCommencing());
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
				String uhid = (String)session.getAttribute("mobuseruid");
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
					// if(dept!=null){
					// userList =
					// notAvailableSlotDAO.getUserListwithdept(loginInfo.getId(),dept);
					// }else{
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
				UserProfileDAO userProfileDAO=new JDBCUserProfileDAO(connection);
				String selecteduserid = request.getParameter("doctor");
				String logintimedrid = (String) session.getAttribute("logintimedrid");
				UserProfile userProfile=new UserProfile();
				if (!DateTimeUtils.isNull(logintimedrid).equals("")) {
					selecteduserid = logintimedrid;
					//session.removeAttribute("logintimedrid");
					userProfile=userProfileDAO.getUserprofileDetails(Integer.parseInt(selecteduserid));
					loginInfo.setActionType(DateTimeUtils.isNull(actionType));
				}
				ArrayList<DiaryManagement> userList = notAvailableSlotDAO.getUserList(loginInfo.getId());
				notAvailableSlotForm.setUserList(userList);
				notAvailableSlotForm.setDiaryUser(selecteduserid);
				notAvailableSlotForm.setDiciplineName(userProfile.getDiciplineName());
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

	public String otdb() {

		if (!verifyLogin(request)) {
			return "login";
		}

		session.setAttribute("openedb", "otdb");

		return "ot";
	}

	public String staff() {

		if (!verifyLogin(request)) {
			return "login";
		}

		session.setAttribute("openedb", "staff");

		return "staff";
	}

	public String opd() {

		if (!verifyLogin(request)) {
			return "login";
		}

		session.setAttribute("openedb", "opd");

		return "opd";
	}

	public String allUser() throws SQLException {
		if (!verifyLogin(request)) {

			return "login";
		}

		Connection connection = null;
		try {
			connection = Connection_provider.getconnection();
			int selectedid = loginInfo.getId();
			ClinicDAO clinicListDAO = new JDBCClinicDAO(connection);
			Clinic cliniclist = clinicListDAO.getCliniclistDetails(selectedid);
			String withpayment = cliniclist.getWithpayment();
			String withoutpayment = cliniclist.getWithoutpayment();
			notAvailableSlotForm.setWithpayment(cliniclist.getWithpayment());
			notAvailableSlotForm.setWithoutpayment(cliniclist.getWithoutpayment());
			session.setAttribute("pgview", "dashboard");
			session.removeAttribute("sessionadmissionid");

			String selectedCommencing = request.getParameter("selectedCommencing");

			NotAvailableSlotDAO notAvailableSlotDAO = new JDBCNotAvailableSlotDAO(connection);
			LoginInfo loginInfo = LoginHelper.getLoginInfo(request);
			// start coding
			/*
			 * String commencing = notAvailableSlotForm.getCommencing(); String
			 * temp[] = commencing.split("/"); commencing = temp[2] + "-" +
			 * temp[1] + "-" + temp[0];
			 */
			// ArrayList<DiaryManagement>userList =
			// notAvailableSlotDAO.getUserList(loginInfo.getId(),commencing);

			/*
			 * String currentDate = DateTimeUtils.getDateinSimpleFormate(new
			 * Date()); String temp[] = currentDate.split(" "); String temp1[] =
			 * temp[0].split("-"); String date = temp1[0] + "/" + temp1[1] + "/"
			 * + temp1[2];
			 */

			String date = DateTimeUtils.getDashboardTodayDate(loginInfo.getTimeZone());

			if (session.getAttribute("sessioncommencing") != null) {
				String sessioncommencing = (String) session.getAttribute("sessioncommencing");
				notAvailableSlotForm.setCommencing(sessioncommencing);
			} else {
				notAvailableSlotForm.setCommencing(date);
			}

			String opendb = (String) session.getAttribute("openedb");
			int totalCount = notAvailableSlotDAO.getUserTotalCount(opendb, notAvailableSlotForm.getSelectedjobgroup());
			pagination.setPreperties(totalCount);
			ArrayList<DiaryManagement> userList = notAvailableSlotDAO.getDashBoardUserList(loginInfo.getId(),
					pagination, notAvailableSlotForm.getCommencing(), opendb,
					notAvailableSlotForm.getSelectedjobgroup());
			pagination.setPage_records(userList.size());
			session.setAttribute("pagination", pagination);

			notAvailableSlotForm.setTotalRecords(totalCount);
			notAvailableSlotForm.setPagerecords(Integer.toString(pagination.getPage_records()));

			ArrayList<Location> locationList = notAvailableSlotDAO.getLocationList(loginInfo.getId());

			notAvailableSlotForm.setUserList(userList);
			notAvailableSlotForm.setDiaryUser("0");
			session.setAttribute("userListSize", userList.size());
			session.setAttribute("allDiaryUser", userList);
			notAvailableSlotForm.setLocationList(locationList);
			notAvailableSlotForm.setLocationid(notAvailableSlotForm.getSelectedLocation());

			ArrayList<Client> thirdPartyTypeList = new ArrayList<Client>();
			ArrayList<Client> thirdPartyTypeNameList = new ArrayList<Client>();
			ArrayList<Client> clientOccupationList = new ArrayList<Client>();
			ArrayList<Client> refrenceList = new ArrayList<Client>();
			ArrayList<String> initialList = new ArrayList<String>();
			ArrayList<Client> sourceOfIntroList = new ArrayList<Client>();
			ArrayList<Client> surgeryList = new ArrayList<Client>();
			ArrayList<Bed> wardlist = new ArrayList<Bed>();

			connection = Connection_provider.getconnection();
			ClientDAO clientDAO = new JDBCClientDAO(connection);
			BedDao bedDao = new JDBCBedDao(connection);
			wardlist = bedDao.getAllWardList(pg);
			notAvailableSlotForm.setWardlist(wardlist);
			//for ayushman we comment below code beacause ot dash not opened
			/*
			 * if (wardlist.size() != 0) { for (Bed bed1 : wardlist) { if
			 * (bed1.getWardname().equals("CASUALTY")) { wardlist.remove(bed1); } } }
			 */
			thirdPartyTypeList = clientDAO.getThirdPartyType();
			notAvailableSlotForm.setThirdPartyTypeList(thirdPartyTypeList);

			thirdPartyTypeNameList = clientDAO.getThirdPartyTypeName();
			notAvailableSlotForm.setThirdPartyTypeNameList(thirdPartyTypeNameList);

			clientOccupationList = clientDAO.getOccupationList();
			Client client1 = new Client();
			client1.getOther();
			clientOccupationList.add(client1);

			notAvailableSlotForm.setClientOccupationList(clientOccupationList);

			refrenceList = clientDAO.getReferenceList();
			Client client2 = new Client();
			client2.getOther();
			refrenceList.add(client2);
			notAvailableSlotForm.setRefrenceList(refrenceList);

			surgeryList = clientDAO.getSurgeryList();
			Client client3 = new Client();
			client3.getOther();
			surgeryList.add(client3);
			notAvailableSlotForm.setSurgeryList(surgeryList);

			initialList = clientDAO.getInitialList();
			notAvailableSlotForm.setInitialList(initialList);

			sourceOfIntroList = clientDAO.getSourceOfIntroList();
			notAvailableSlotForm.setSourceOfIntroList(sourceOfIntroList);
			
			ArrayList<Master> procedureList = notAvailableSlotDAO.getProcedureList(""+4);
			notAvailableSlotForm.setProcedure_List(procedureList);

			ClinicDAO clinicDAO = new JDBCClinicDAO(connection);
			String checkValue = clinicDAO.IsMailSend(loginInfo.getId());
			notAvailableSlotForm.setCheckMailSend(checkValue);
			notAvailableSlotForm.setHourList(PopulateList.hourList());
			notAvailableSlotForm.setMinuteList(PopulateList.getMinuteList());

			if (opendb.equals("staff")) {
				notAvailableSlotForm.setJobgroup(notAvailableSlotForm.getSelectedjobgroup());
				return "staffdb";
			} else if (opendb.equals("otdb")) {
				return "otdb";
			} else {
				session.setAttribute("openedb", "opd");
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {

			connection.close();
		}
		return "allUser";
	}

	public String day() throws SQLException {

		if (!verifyLogin(request)) {
			return "login";
		}

		// show practice manager
		Connection connection = null;
		try {
			connection = Connection_provider.getconnection();
			NotAvailableSlotDAO notAvailableSlotDAO = new JDBCNotAvailableSlotDAO(connection);

			// start coding
			ArrayList<DiaryManagement> userList = notAvailableSlotDAO.getUserList(loginInfo.getId());

			notAvailableSlotForm.setUserList(userList);

			String temp[] = notAvailableSlotForm.getCommencing().split("/");

			int wyear = Integer.parseInt(temp[2]);
			int month = Integer.parseInt(temp[1]);
			int day = Integer.parseInt(temp[0]);

			String cweekName = DateTimeUtils.getWeekName(wyear, month, day);
			notAvailableSlotForm.setDayWeekName(cweekName);

		} catch (Exception e) {
			e.printStackTrace();
		} finally {

			connection.close();
		}

		return "day";
	}

	// set client has arrived
	public String setreset() throws SQLException {
		if (!verifyLogin(request)) {
			return "login";
		}
		int selectedid = Integer.parseInt(request.getParameter("selectedid"));
		int status = Integer.parseInt(request.getParameter("status"));
		// current date and time in dd/mm/yyyy
		String currentDate = DateTimeUtils.getDateinSimpleFormate(new Date());
		String datetemp[] = currentDate.split(" ");
		String temp1[] = datetemp[0].split("-");
		String date = temp1[0] + "/" + temp1[1] + "/" + temp1[2];
		String time = (datetemp[2] + " " + datetemp[3]);
		Connection connection = null;
		try {
			connection = Connection_provider.getconnection();
			NotAvailableSlotDAO notAvailableSlotDAO = new JDBCNotAvailableSlotDAO(connection);

			int result = notAvailableSlotDAO.updateClientHasArrived(selectedid, status);
			int res = notAvailableSlotDAO.saveDateOfOPDEvents("" + selectedid, "patient_arrived_time");

			NotAvailableSlot n = notAvailableSlotDAO.getApmtDetailsForLog(selectedid);

			String commencingTemp = n.getCommencing();
			String apmtstatus = "Arrived";
			int logsave = notAvailableSlotDAO.saveApmtInLog(selectedid, date, time, loginInfo.getUserId(),
					n.getClientId(), commencingTemp, n.getSTime(), apmtstatus,
					DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone()));

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			connection.close();
		}

		return null;
	}

	public String work() throws Exception {
		if (!verifyLogin(request)) {
			return "login";
		}
		int selectedid = Integer.parseInt(request.getParameter("selectedid"));
		String status = request.getParameter("status");
		String cancelnotes = request.getParameter("cancelnotes");

		// current date and time in dd/mm/yyyy
		String currentDate = DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone());

		Connection connection = null;
		try {
			connection = Connection_provider.getconnection();
			NotAvailableSlotDAO notAvailableSlotDAO = new JDBCNotAvailableSlotDAO(connection);
			int upd = notAvailableSlotDAO.updateWorkCompleted(selectedid, status, currentDate, cancelnotes);
			/* adarsh changes */
			/*
			 * int upd =
			 * notAvailableSlotDAO.updateWorkCompleted(selectedid,status,
			 * currentDate);
			 */

		} catch (Exception e) {
			// TODO: handle exception
		} finally {

			connection.close();
		}

		return null;
	}

	// set client is being seen
	public String clientIsBeingSeen() throws SQLException {
		if (!verifyLogin(request)) {
			return "login";
		}
		int selectedid = Integer.parseInt(request.getParameter("selectedid"));
		int status = Integer.parseInt(request.getParameter("status"));

		// current date and time in dd/mm/yyyy
		String currentDate = DateTimeUtils.getDateinSimpleFormate(new Date());
		String datetemp[] = currentDate.split(" ");
		String temp1[] = datetemp[0].split("-");
		String date = temp1[0] + "/" + temp1[1] + "/" + temp1[2];
		String time = (datetemp[2] + " " + datetemp[3]);

		Connection connection = null;
		try {
			connection = Connection_provider.getconnection();
			NotAvailableSlotDAO notAvailableSlotDAO = new JDBCNotAvailableSlotDAO(connection);

			int result = notAvailableSlotDAO.updateClientIsBeingSeen(selectedid, status);
			int res = notAvailableSlotDAO.saveDateOfOPDEvents("" + selectedid, "patient_being_seen_time");

			NotAvailableSlot n = notAvailableSlotDAO.getApmtDetailsForLog(selectedid);

			String commencingTemp = n.getCommencing();
			String apmtstatus = "Being Seen";
			int logsave = notAvailableSlotDAO.saveApmtInLog(selectedid, date, time, loginInfo.getUserId(),
					n.getClientId(), commencingTemp, n.getSTime(), apmtstatus,
					DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone()));

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			connection.close();
		}

		return null;
	}

	public String resetNotArrived() throws SQLException {
		if (!verifyLogin(request)) {
			return "login";
		}
		int selectedid = Integer.parseInt(request.getParameter("selectedid"));
		int status = Integer.parseInt(request.getParameter("status"));
		// current date and time in dd/mm/yyyy
		String currentDate = DateTimeUtils.getDateinSimpleFormate(new Date());
		String datetemp[] = currentDate.split(" ");
		String temp1[] = datetemp[0].split("-");
		String date = temp1[0] + "/" + temp1[1] + "/" + temp1[2];
		String time = (datetemp[2] + " " + datetemp[3]);
		Connection connection = null;
		try {
			connection = Connection_provider.getconnection();
			NotAvailableSlotDAO notAvailableSlotDAO = new JDBCNotAvailableSlotDAO(connection);

			int result = notAvailableSlotDAO.updateResetNotArrived(selectedid, status);
			NotAvailableSlot n = notAvailableSlotDAO.getApmtDetailsForLog(selectedid);

			String commencingTemp = n.getCommencing();

			String apmtstatus = "Reset To Not Arrived";
			int logsave = notAvailableSlotDAO.saveApmtInLog(selectedid, date, time, loginInfo.getUserId(),
					n.getClientId(), commencingTemp, n.getSTime(), apmtstatus,
					DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone()));

		} catch (Exception e) {
			e.printStackTrace();
		} finally {

			connection.close();
		}

		return null;
	}

	public String dragAndDrop() throws SQLException {
		if (!verifyLogin(request)) {
			return "login";
		}
		String practitionerid = request.getParameter("practitionerid");
		String startTime = request.getParameter("evalTime");
		String availableSlotID = request.getParameter("availableSlotID");
		String endTime = request.getParameter("endTime");
		String commencingTemp = request.getParameter("commencingDate");
		String location = request.getParameter("location");

		// current date and time in dd/mm/yyyy
		String currentDate = DateTimeUtils.getDateinSimpleFormate(new Date());
		String datetemp[] = currentDate.split(" ");
		String temp1[] = datetemp[0].split("-");
		String date = temp1[0] + "/" + temp1[1] + "/" + temp1[2];
		String time = (datetemp[2] + " " + datetemp[3]);
		String tempPractitionerID[] = practitionerid.split("/");
		if (tempPractitionerID.length > 1) {
			Connection connection = null;

			try {
				connection = Connection_provider.getconnection();
				NotAvailableSlotDAO notAvailableSlotDAO = new JDBCNotAvailableSlotDAO(connection);
				String slotid = tempPractitionerID[0];
				practitionerid = tempPractitionerID[1];

				String commencingDate = notAvailableSlotDAO.getSlotCommencingDate(slotid);

				int result = notAvailableSlotDAO.UpdateDragAndDropData(availableSlotID, practitionerid, startTime,
						endTime, slotid, location, commencingDate);
				String clientId = notAvailableSlotDAO.getClientId(availableSlotID);
				String apmtstatus = "Modified";
				int logsave = notAvailableSlotDAO.saveApmtInLog(Integer.parseInt(availableSlotID), commencingDate, time,
						loginInfo.getUserId(), clientId, commencingDate, startTime, apmtstatus,
						DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone()));

				String opendb = (String) session.getAttribute("openedb");
				int status = notAvailableSlotDAO.getStatus(availableSlotID, opendb);

				if (status == 0) {
					session.setAttribute("appointmentid", Integer.parseInt(availableSlotID));

					/*
					 * Clinic clinic = new Clinic(); ClinicDAO clinicDAO = new
					 * JDBCClinicDAO(connection); clinic =
					 * clinicDAO.getCliniclistDetails(loginInfo.getId()); String
					 * logo = clinic.getUserImageFileName(); String filePath =
					 * ""; if(logo == null){ logo = ""; }if(logo.equals("")){
					 * filePath = request.getRealPath("/img/anker-logo.jpg/");
					 * 
					 * } else{ filePath =
					 * request.getRealPath("/liveData/clinicLogo/"+logo+"/"); }
					 */
					// String filePath =
					// request.getRealPath("/Design/images/logo.png/");
					// session.setAttribute("logopath", filePath);

					/*
					 * String twitter =
					 * request.getRealPath("/img/Entypo_f309(0)_32.png/");
					 * session.setAttribute("twitter", twitter);
					 * 
					 * String fb =
					 * request.getRealPath("/img/Entypo_f30c(0)_32.png/");
					 * session.setAttribute("fb", fb);
					 * 
					 * String gml =
					 * request.getRealPath("/img/Entypo_f30f(0)_32.png/");
					 * session.setAttribute("gml", gml);
					 */

					/*
					 * DBScheduler dbScheduler = new DBScheduler();
					 * dbScheduler.callScheduler(connection,loginInfo,request,
					 * Integer.parseInt(availableSlotID));
					 */
				}

				NotAvailableSlot notAvailableSlot = notAvailableSlotDAO
						.getAvailableSlotdata(Integer.parseInt(availableSlotID));

				String updatedAppointmentData = notAvailableSlot.getCommencing() + "/" + notAvailableSlot.getsTime()
						+ "/" + notAvailableSlot.getEndTime() + "/" + notAvailableSlot.getDiaryUser() + "/"
						+ notAvailableSlot.getClientName() + "/" + notAvailableSlot.getDiaryUserId() + "/"
						+ notAvailableSlot.getId() + "/" + notAvailableSlot.getClientId();

				response.setContentType("text/html");
				response.setHeader("Cache-Control", "no-cache");
				response.getWriter().write("" + updatedAppointmentData + "");
				session.setAttribute("connection", connection);

			} catch (Exception e) {
				e.printStackTrace();
				log.debug("***************" + e.getMessage());
			} finally {
				connection.close();
			}
		}

		return null;
	}

	public String saveotcharge() throws Exception {

		Connection connection = null;
		try {

			connection = Connection_provider.getconnection();
			AppointmentTypeDAO appointmentTypeDAO = new JDBCAppointmentTypeDAO(connection);
			MasterDAO masterDAO = new JDBCMasterDAO(connection);
			String chargetypeid = request.getParameter("chargetype");
			String patientId = request.getParameter("patientId");
			String otplaned = request.getParameter("otplaned");
			;
			String otduration = request.getParameter("otduration");

			String potcharge = request.getParameter("potcharge");
			String psurcharge = request.getParameter("psurcharge");
			String panetcharge = request.getParameter("panetcharge");
			String sic = request.getParameter("sic");
			String assistaffcharge = request.getParameter("assistaffcharge");
			if (potcharge.equals("")) {
				potcharge = "0";
			}

			if (psurcharge.equals("")) {
				psurcharge = "0";
			}
			if (panetcharge.equals("")) {
				panetcharge = "0";
			}
			if (sic.equals("")) {
				sic = "0";
			}
			if (assistaffcharge.equals("")) {
				assistaffcharge = "0";
			}

			//   25 Jan 2018
			String chargetype = masterDAO.getMasterChargeType(chargetypeid);

			ClientDAO clientDAO = new JDBCClientDAO(connection);

			Client client = clientDAO.getClientDetails(patientId);
			// get ipd details
			AssessmentFormDAO assessmentFormDAO = new JDBCAssessmentFormDAO(connection);
			int bedid = assessmentFormDAO.getIpdBedno(patientId);
			String wardid = "0";
			String whopay = client.getWhopay();
			String tpid = "0";
			if (bedid != 0) {
				BedDao bedDao = new JDBCBedDao(connection);

				String ipdid = assessmentFormDAO.getAdmissionid(patientId);
				Bed bed = bedDao.getEditIpdData(ipdid);
				wardid = bed.getWardid();

			}

			if (otplaned.equals("Unplaned")) {
				wardid = "0";
			}

			String chargeName = "";
			String charge = "";
			String otchargetype = "";
			//   23 April 2018 //add assisting staff charge
			/* for(int i=1;i<=4;i++){ */
			for (int i = 1; i <= 5; i++) {
				if (i == 1) {
					chargeName = Constants.OT_CHARGE;
					charge = potcharge;
					otchargetype = "" + i + "";
				}
				if (i == 2) {
					chargeName = Constants.SURGEON_CHARGE;
					charge = psurcharge;
					otchargetype = "" + i + "";

				}
				if (i == 3) {
					chargeName = Constants.ANISTHESIA_CHARGE;
					charge = panetcharge;
					otchargetype = "" + i + "";
				}
				if (i == 4) {
					chargeName = Constants.SIC_CHARGE;
					charge = sic;
					otchargetype = "" + i + "";
				}
				if (i == 5) {
					chargeName = Constants.ASSISTING_STAFF_CHARGE;
					charge = assistaffcharge;
					otchargetype = "" + i + "";
				}

				boolean checkchargeexsist = appointmentTypeDAO.checkChargeExisist(chargetype, chargeName, wardid, tpid,
						otchargetype);
				if (checkchargeexsist) {
					int upd = appointmentTypeDAO.updateOtcharge(chargetype, chargeName, charge, wardid, tpid,
							otchargetype, otduration);
				} else {
					int res = appointmentTypeDAO.saveOtCharges(chargetype, chargeName, charge, wardid, tpid,
							otchargetype, otduration);
				}

			}

			String otchargeid = appointmentTypeDAO.getotChargeId(chargetype, Constants.OT_CHARGE, wardid, tpid);

			response.setContentType("text/html");
			response.setHeader("Cache-Control", "no-cache");
			response.getWriter().write("" + otchargeid + "");

		} catch (Exception e) {
			e.printStackTrace();
		} finally {

			connection.close();
		}

		return null;
	}

	public String newotcharge() throws Exception {

		Connection connection = null;

		try {

			connection = Connection_provider.getconnection();
			IpdDAO ipdDAO = new JDBCIpdDAO(connection);
			MasterDAO masterDAO = new JDBCMasterDAO(connection);
			NotAvailableSlotDAO notAvailableSlotDAO = new JDBCNotAvailableSlotDAO(connection);
			String chargetypeid = request.getParameter("chargetype");
			String patientId = request.getParameter("patientId");
			String otplaned = request.getParameter("otplaned");
            String otprocedurecharge=request.getParameter("otcharge");
			String editAppointId = request.getParameter("editAppointId");
			String procedureid=request.getParameter("procedureid");
			//   25 Jan 2018
			String chargetype = masterDAO.getMasterChargeType(chargetypeid);
			// System.out.println(patientId);

			ArrayList<Master> list = ipdDAO.getProcedureChargeList(chargetype, patientId, otplaned);

			String str = "";
			for (Master master : list) {
				str = str + master.getCharge() + ",";
			}
			str = str + "0," + "";
			String otcharge = "";
			String surgeoncharge = "";
			String anicharge = "";
			String sic = "";
			String anifees = "";
			String anidoctor = "";
			String assistaffcharge = "";
			if (!editAppointId.equals("0")) {

				str = "";
				NotAvailableSlot notAvailableSlot = notAvailableSlotDAO.getOTData(editAppointId);
				otcharge = notAvailableSlot.getChargeamout();
				surgeoncharge = notAvailableSlot.getPsurcharge();
				anicharge = notAvailableSlot.getPanetcharge();
				sic = notAvailableSlot.getSic();
				anifees = notAvailableSlot.getAnifees();
				anidoctor = notAvailableSlot.getAnesthesia();
				assistaffcharge = notAvailableSlot.getAssistaffcharge();

				// str = otcharge + "," + surgeoncharge + "," + anicharge + ","
				// + sic + "," + anifees + "," + anidoctor+","+assistaffcharge;
			}
			str = otcharge + "," + surgeoncharge + "," + anicharge + "," + sic + "," + anifees + "," + anidoctor + ","
					+ assistaffcharge+","+otprocedurecharge;

			response.setContentType("text/html");
			response.setHeader("Cache-Control", "no-cache");
			response.getWriter().write("" + str.toString() + "");

		} catch (Exception e) {
			e.printStackTrace();
		} finally {

			connection.close();
		}

		return null;
	}

	public String saveani() throws Exception {

		Connection connection = null;
		try {

			connection = Connection_provider.getconnection();
			AppointmentTypeDAO appointmentTypeDAO = new JDBCAppointmentTypeDAO(connection);
			ClientDAO clientDAO = new JDBCClientDAO(connection);
			UserProfileDAO userProfileDAO = new JDBCUserProfileDAO(connection);
			String doctor = request.getParameter("doctor");

			int res = appointmentTypeDAO.saveAniDoctor(doctor);
			// ArrayList<Client> anesthesiaList = clientDAO.getAnesthesiaList();
			ArrayList<Client> anesthesiaList = userProfileDAO.getAllAnethesiaList();
			StringBuffer str = new StringBuffer();

			str.append("<input type hidden name='hdnanidoctorid' id='hdnanidoctorid' value='" + res + "'>");

			str.append(
					"<select onchange='showdoctornameforfees()' name='otanesthesia' id='otanesthesia' class='form-control showToolTip chosen' >");
			str.append("<option value='0'>Select Anesthesia</option>");
			for (Client client : anesthesiaList) {
				str.append("<option value='" + client.getId() + "'>" + client.getReference() + "</option>");
			}
			str.append("</select>");

			response.setContentType("text/html");
			response.setHeader("Cache-Control", "no-cache");
			response.getWriter().write("" + str.toString() + "");

		} catch (Exception e) {
			// TODO: handle exception
		} finally {

			connection.close();
		}

		return null;
	}

	public String departproc() throws Exception {

		Connection connection = null;
		try {

			//connection = Connection_provider.getconnection();
			connection = DriverManager.getConnection(
					"" + Constants.DB_HOST + ":3306/" + loginInfo.getClinicUserid()
							+ "?useUnicode=true&characterEncoding=UTF-8",
					"" + Constants.DB_USER + "", "" + Constants.DB_PWD + "");
			request.setCharacterEncoding("UTF-8");
			response.setCharacterEncoding("UTF-8");
			IpdDAO ipdDAO = new JDBCIpdDAO(connection);
			NotAvailableSlotDAO notAvailableSlotDAO = new JDBCNotAvailableSlotDAO(connection);
			String id = request.getParameter("id");
			String action = request.getParameter("action");
			String procedureid=request.getParameter("procedureid");
			ArrayList<Master> procedureList = notAvailableSlotDAO.getProcedureList(id);

			StringBuffer str = new StringBuffer();
              if(action.equals("1")) {
            	  str.append("<select onchange='getCFprocedure1(this.value)' name='otprocedureplaned' id='otprocedureplaned' class='form-control showToolTip chosen' >");

              }else {
			str.append(
					"<select onchange='filterOtCharges(this.value)' name='otprocedureplaned' id='otprocedureplaned' class='form-control showToolTip chosen' >");
              }
			str.append("<option value='0'>Select Procedure</option>");
			for (Master master : procedureList) {
				//   06-jan-2018
				//   25 jan 2018
				str.append("<option value='" + master.getId() + "'>" + master.getName() + "</option>");
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

			response.setContentType("text/html");
			response.setHeader("Cache-Control", "no-cache");
			response.getWriter().write("" + str.toString() + "");

		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	public String procedure() throws Exception {
		Connection connection = null;

		try {

			connection = Connection_provider.getconnection();
			IpdDAO ipdDAO = new JDBCIpdDAO(connection);
			MasterDAO masterDAO = new JDBCMasterDAO(connection);

			String chargetypeid = request.getParameter("chargetype");
			String patientId = request.getParameter("patientId");

			// System.out.println(patientId);

			String chargetype = masterDAO.getMasterChargeType(chargetypeid);
			ArrayList<Master> list = ipdDAO.getOtFilteredChargeList(chargetype, patientId);
			StringBuffer str = new StringBuffer();

			str.append(
					"<select onchange='setAppointmentTypeTimeAjax(this.value)' name='duration' id='apmtType' class='form-control showToolTip chosen' >");
			//str.append("<option value='0'>Select Charge</option>");
			for (Master master : list) {
				//   06-jan-2017
				// str.append("<option
				// value='"+master.getId()+"'>"+master.getName()+"</option>");
				// str.append("<option
				// value='"+master.getId()+"'>"+chargetype+"</option>");
				str.append("<option value='" + master.getId() + "'>" + master.getChargetype() + "</option>");
			}
			str.append("</select>");

			/*
			 * str.
			 * append("<label id='apmtTypeError' class='text-danger'></label>");
			 * str.append("<div class='col-lg-12 col-md-12'>"); str.
			 * append("<label id='apmtTypeDuration' class='text-danger durane'></label>"
			 * ); str.append("</div>");
			 */

			response.setContentType("text/html");
			response.setHeader("Cache-Control", "no-cache");
			response.getWriter().write("" + str.toString() + "");

		} catch (Exception e) {
			e.printStackTrace();
		} finally {

			connection.close();
		}

		return null;
	}

	public String dnafalse() throws Exception {

		int selectedid = Integer.parseInt(request.getParameter("selectedid"));

		// System.out.println(selectedid);

		Connection connection = null;
		try {
			connection = Connection_provider.getconnection();
			NotAvailableSlotDAO notAvailableSlotDAO = new JDBCNotAvailableSlotDAO(connection);
			NotAvailableSlot notAvailableSlot = notAvailableSlotDAO.getAvailableSlotDnadata(selectedid);

			String dnapercentage = "0.00";
			String tpnotes = "";
			boolean dnaOffset = false;

			if (notAvailableSlot.getWhopay().equals(Constants.PAY_BY_CLIENT)) {
				/*
				 * dnapercentage = notAvailableSlotDAO.getDNAAercentage();
				 * 
				 * double peramt = 0;
				 */

				// double charge = notAvailableSlot.getCharge() * peramt;

				// notAvailableSlot.setCharge(dnapercentage);
				dnapercentage = "0.00";

			} else {
				// System.out.println("tp");
				// dnapercentage =
				// notAvailableSlotDAO.getTPDNALimit(notAvailableSlot.getClientId());
				ThirdParty thirdParty = notAvailableSlotDAO.getTPDNALimit(notAvailableSlot.getClientId());
				dnaOffset = notAvailableSlot.isDnaOffset();

				if (thirdParty.isDnaForAll()) {
					dnapercentage = thirdParty.getDnaLimit();
				} else {
					dnapercentage = notAvailableSlotDAO.getSpecificDnaCharge(selectedid);
				}

				TreatmentEpisodeDAO treatmentEpisodeDAO = new JDBCTreatmentEpisode(connection);
				tpnotes = treatmentEpisodeDAO.getTPNotes(Integer.toString(selectedid));

				// double peramt = (notAvailableSlot.getCharge() *
				// dnapercentage)/100;
				// notAvailableSlot.setCharge(dnapercentage);
			}

			if (dnapercentage.length() == 1) {
				dnapercentage = dnapercentage + ".00";
			}

			response.setContentType("text/html");
			response.setHeader("Cache-Control", "no-cache");
			response.getWriter().write("" + dnapercentage + "/" + tpnotes + "/" + dnaOffset);

		} catch (Exception e) {
			e.printStackTrace();
		} finally {

			connection.close();
		}

		return null;
	}

	public String dnaper() throws Exception {

		int selectedid = Integer.parseInt(request.getParameter("selectedid"));

		// System.out.println(selectedid);

		Connection connection = null;
		try {
			connection = Connection_provider.getconnection();
			NotAvailableSlotDAO notAvailableSlotDAO = new JDBCNotAvailableSlotDAO(connection);
			NotAvailableSlot notAvailableSlot = notAvailableSlotDAO.getAvailableSlotDnadata(selectedid);

			int dnapercentage = 0;

			/*
			 * if(notAvailableSlot.getWhopay().equals(Constants.PAY_BY_CLIENT)){
			 * dnapercentage = notAvailableSlotDAO.getDNAAercentage();
			 * 
			 * double peramt = (notAvailableSlot.getCharge() *
			 * dnapercentage)/100;
			 * 
			 * //double charge = notAvailableSlot.getCharge() * peramt;
			 * 
			 * notAvailableSlot.setCharge(peramt);
			 * 
			 * }else{ System.out.println("tp"); dnapercentage =
			 * notAvailableSlotDAO.getTPDNALimit(notAvailableSlot.getClientId())
			 * ;
			 * 
			 * double peramt = (notAvailableSlot.getCharge() *
			 * dnapercentage)/100; notAvailableSlot.setCharge(peramt); }
			 */

			double charge = notAvailableSlotDAO.getInvoiceCharge(selectedid);
			int payby = notAvailableSlotDAO.getDNAInvoicePayBy(selectedid);
			boolean dnaOffset = false;
			if (payby == 1) {

				ThirdParty thirdParty = notAvailableSlotDAO.getTPDNALimit(notAvailableSlot.getClientId());
				dnaOffset = notAvailableSlot.isAppointmentDnaOffset();
			}

			TreatmentEpisodeDAO treatmentEpisodeDAO = new JDBCTreatmentEpisode(connection);
			String tpNotes = treatmentEpisodeDAO.getTPNotes(Integer.toString(selectedid));

			response.setContentType("text/html");
			response.setHeader("Cache-Control", "no-cache");
			response.getWriter()
					.write("" + DateTimeUtils.changeFormat(charge) + "~" + payby + "~" + notAvailableSlot.getDnaReason()
							+ "~" + notAvailableSlot.getDnaNotes() + "~" + tpNotes + "~" + dnaOffset);

		} catch (Exception e) {
			e.printStackTrace();
		} finally {

			connection.close();
		}

		return null;
	}

	// client did not attent confirmation
	public String didnotAttend() throws SQLException {
		if (!verifyLogin(request)) {
			return "login";
		}
		int selectedid = Integer.parseInt(request.getParameter("selectedid"));

		String notes = request.getParameter("dnaNotes");
		boolean dna = Boolean.parseBoolean(request.getParameter("dna"));
		String dnaReason = request.getParameter("dnaReason");
		// current date and time in dd/mm/yyyy
		String currentDate = DateTimeUtils.getDateinSimpleFormate(new Date());
		String datetemp[] = currentDate.split(" ");
		String temp1[] = datetemp[0].split("-");
		String date = temp1[0] + "/" + temp1[1] + "/" + temp1[2];
		String time = (datetemp[2] + " " + datetemp[3]);

		String dnaCharge = request.getParameter("dnaCharge");
		String dnapayby = request.getParameter("dnapayby");
		boolean globalDna = Boolean.parseBoolean(request.getParameter("globalDna"));
		String editdnaCharge = request.getParameter("editdnaCharge");

		String tepisodeid = "";

		Connection connection = null;
		try {
			connection = Connection_provider.getconnection();
			NotAvailableSlotDAO notAvailableSlotDAO = new JDBCNotAvailableSlotDAO(connection);
			ClientDAO clientDAO = new JDBCClientDAO(connection);

			NotAvailableSlot notAvailableSlot = notAvailableSlotDAO.getAvailableSlotDnadata(selectedid);
			tepisodeid = notAvailableSlot.getTreatmentEpisodeId();

			int result = notAvailableSlotDAO.updateDNA(selectedid, notes, dna, dnaReason, dnaCharge);
			boolean dnaOffset = false;

			if (dna == true) {

				String clientId = notAvailableSlotDAO.getClientId(Integer.toString(selectedid));
				Client client = clientDAO.getClientDetails(clientId);
				NotAvailableSlot n = notAvailableSlotDAO.getApmtDetailsForLog(selectedid);
				/*
				 * String commencingtemp[] = n.getCommencing().split(" ");
				 * String tempC[] = commencingtemp[0].split("-");
				 */
				String commencingTemp = n.getCommencing();
				String apmtstatus = "DNA";
				int logsave = notAvailableSlotDAO.saveApmtInLog(selectedid, date, time, loginInfo.getUserId(), clientId,
						commencingTemp, n.getSTime(), apmtstatus,
						DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone()));
				// int savedna =
				// notAvailableSlotDAO.saveDnaLog(clientId,date,time,loginInfo.getUserId(),selectedid);

				String dnapercentage = "0.00";

				if (notAvailableSlot.getWhopay().equals(Constants.PAY_BY_CLIENT)) {
					// dnapercentage = notAvailableSlotDAO.getDNAAercentage();

					// double peramt = Double.parseDouble(dnaCharge);

					// double charge = notAvailableSlot.getCharge() * peramt;

					// notAvailableSlot.setCharge(peramt);

					dnapercentage = dnaCharge;

					// send SMS
					String message = "You had an appointment with " + loginInfo.getClinicName() + " on " + date
							+ ". You have missed the appointment. We hope your doing well.";
					SmsService s = new SmsService();
					String templateid="";
					s.sendSms(message, client.getMobNo(), loginInfo, new EmailLetterLog(),templateid);

				} else {
					// System.out.println("tp");

					dnaOffset = Boolean.parseBoolean(request.getParameter("dnaOffset"));

					int updatednaoffset = notAvailableSlotDAO.updateDnaOffset(dnaOffset,
							notAvailableSlot.getAppointmentTypeid());

					// set dna offset when dna appointment saved
					if (dnaOffset == true) {

						int updateAppointmentDnaoffset = notAvailableSlotDAO.updateAppointmentDnaoffset(selectedid,
								dnaOffset);

						int updateoffset = notAvailableSlotDAO.IncraeseDnaOffset(selectedid, 0);

						ArrayList<NotAvailableSlot> dnaOffsetList = notAvailableSlotDAO
								.getDnaOffsetList(notAvailableSlot.getTreatmentEpisodeId());

						int i = 1;
						for (NotAvailableSlot nt : dnaOffsetList) {

							int updateusedsession = notAvailableSlotDAO.updateAppointmentUsedSession(nt.getId(), i);

							i++;
						}

						int updatetepisodeusedsession = notAvailableSlotDAO.updateupdateTpEpisodeusedsession(tepisodeid,
								dnaOffsetList.size());

						/*
						 * for(NotAvailableSlot nt : dnaOffsetList){
						 * 
						 * int usedsession =
						 * Integer.parseInt(nt.getUsedsession());
						 * 
						 * if(Integer.parseInt(nt.getUsedsession())>1){
						 * usedsession = usedsession - 1; }
						 * 
						 * int u =
						 * notAvailableSlotDAO.updateAppointmentUsedSession(nt.
						 * getId(),usedsession); }
						 * 
						 * int updatetepisodeusedsession =
						 * notAvailableSlotDAO.updateupdateTpEpisodeusedsession(
						 * tepisodeid,dnaOffsetList.size());
						 */

						/*
						 * int usedsession =
						 * notAvailableSlotDAO.getTpEpisodeUsedSession(
						 * tepisodeid); if(usedsession>0){ usedsession =
						 * usedsession - 1; }
						 */

						// dna moify if dna offset changed
					} else if (dnaOffset == false) {
						// System.out.println(dnaOffset);
						int updateAppointmentDnaoffset = notAvailableSlotDAO.updateAppointmentDnaoffset(selectedid,
								false);

						ArrayList<NotAvailableSlot> dnaoffsetList = notAvailableSlotDAO
								.getDnaOffsetList(notAvailableSlot.getTreatmentEpisodeId());
						int i = 1;
						for (NotAvailableSlot nt : dnaoffsetList) {

							int updateusedsession = notAvailableSlotDAO.updateAppointmentUsedSession(nt.getId(), i);

							i++;
						}

						int updatetepisodeusedsession = notAvailableSlotDAO.updateupdateTpEpisodeusedsession(tepisodeid,
								dnaoffsetList.size());

					}

					if (Double.parseDouble(dnaCharge) == 0) {
						ThirdParty thirdParty = notAvailableSlotDAO.getTPDNALimit(notAvailableSlot.getClientId());
						if (thirdParty.isDnaForAll()) {
							dnapercentage = thirdParty.getDnaLimit();
						} else {
							dnapercentage = notAvailableSlotDAO.getSpecificDnaCharge(selectedid);
						}

					} else {
						dnapercentage = dnaCharge;
					}

					// notAvailableSlot.setCharge(dnapercentage);
				}

				notAvailableSlot.setPayBy(dnapayby);

				if (globalDna == false) {
					int r = notAvailableSlotDAO.updateDNA(selectedid, notes, dna, dnaReason, dnapercentage);
					n = notAvailableSlotDAO.getApmtDetailsForLog(selectedid);
					int invoiceid = notAvailableSlotDAO.saveDnaCharge(notAvailableSlot, selectedid);
					// int save =
					// notAvailableSlotDAO.saveApmInvoiceAssesment(notAvailableSlot,invoiceid,selectedid);
					int save = notAvailableSlotDAO.saveDnaApmInvoiceAssesment(notAvailableSlot, invoiceid, selectedid,
							dnapercentage);

				} else {
					// modify dna data
					int r = notAvailableSlotDAO.updateDNA(selectedid, notes, dna, dnaReason, editdnaCharge);
					n = notAvailableSlotDAO.getApmtDetailsForLog(selectedid);
					int invoiceid = notAvailableSlotDAO.getDNAInvoiceid(selectedid);
					int update = notAvailableSlotDAO.updateDNAInvoiceAssesment(editdnaCharge, dnapayby, invoiceid);
				}

			}

			else {

				int delete = notAvailableSlotDAO.deleteDnaInvoiceAssesment(selectedid);
				int del = notAvailableSlotDAO.deleteDnaInvoice(selectedid);

				// set dna offset when dna appointment reset

				if (notAvailableSlot.getWhopay().equals(Constants.PAY_BY_THIRD_PARTY)) {

					ThirdParty thirdParty = notAvailableSlotDAO.getTPDNALimit(notAvailableSlot.getClientId());
					dnaOffset = notAvailableSlot.isDnaOffset();

					// if(dnaOffset==true){

					int updateAppointmentDnaoffset = notAvailableSlotDAO.updateAppointmentDnaoffset(selectedid, false);

					ArrayList<NotAvailableSlot> dnaoffsetList = notAvailableSlotDAO
							.getDnaOffsetList(notAvailableSlot.getTreatmentEpisodeId());
					int i = 1;
					for (NotAvailableSlot nt : dnaoffsetList) {

						int updateusedsession = notAvailableSlotDAO.updateAppointmentUsedSession(nt.getId(), i);

						i++;
					}

					int updatetepisodeusedsession = notAvailableSlotDAO.updateupdateTpEpisodeusedsession(tepisodeid,
							dnaoffsetList.size());

					/*
					 * int usedsession =
					 * notAvailableSlotDAO.getTpEpisodeUsedSession(tepisodeid);
					 * usedsession = usedsession + 1; int updateoffset =
					 * notAvailableSlotDAO.IncraeseDnaOffset(selectedid,
					 * usedsession); int updatetepisodeusedsession =
					 * notAvailableSlotDAO.updateupdateTpEpisodeusedsession(
					 * tepisodeid,usedsession);
					 */
					// }

				}

			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {

			connection.close();
		}

		return null;
	}

	public NotAvailableSlotForm getModel() {

		return notAvailableSlotForm;
	}

	public String saveAppoinment() throws SQLException {
		if (!verifyLogin(request)) {
			return "login";
		}
		Connection connection = null;
		int result = 0;

		String slotId = request.getParameter("slotId");
		String commencing = request.getParameter("commencing");
		/*
		 * String temp[] = commencing.split("/"); commencing =
		 * temp[2]+"-"+temp[1]+"-"+temp[0];
		 */
		String location = request.getParameter("location");
		String room = request.getParameter("room");
		String sTime = request.getParameter("sTime");
		String endTime = request.getParameter("endTime");
		String apmtDuration = request.getParameter("apmtDuration");
		String diaryuserId = request.getParameter("diaryuserId");
		String client = request.getParameter("client");
		String dept = request.getParameter("dept");
		String apmtType = request.getParameter("apmtType");
		String notes = request.getParameter("notes");
		String diaryUser = request.getParameter("diaryUser");
		String clientId = request.getParameter("clientId");
		String treatmentEpisodeId = request.getParameter("treatmentEpisodeId");
		String condition = request.getParameter("condition");
		String whopay = request.getParameter("whopay");
		String stafflistid = request.getParameter("stafflistid");
		boolean isot = Boolean.parseBoolean(request.getParameter("isot"));
		String bookwithpayment = request.getParameter("bookwithpayment");

		// ot variables
		String otplaned = request.getParameter("otplaned");
		String otprocedureplaned1 = request.getParameter("otprocedureplaned");

		String otsurgeonname = request.getParameter("otsurgeonname");
		String otanesthesia = request.getParameter("otanesthesia");
		String otipdnos = request.getParameter("otipdnos");

		String psurcharge = request.getParameter("psurcharge");
		String panetcharge = request.getParameter("panetcharge");
		String anifees = request.getParameter("anifees");
		String sic = request.getParameter("sic");

		// take payment data
		String invcetype = request.getParameter("invcetype");
		String howpaid = request.getParameter("howpaid");
		String totalamount = request.getParameter("totalamount");
		String discount = request.getParameter("discount");
		String payAmount = request.getParameter("payAmount");
		String disctype = request.getParameter("disctype");
		// String paymentNote = document.getElementById('paymentNote').value;

		String status = null;
		int selectedid = Integer.parseInt(request.getParameter("selectedid"));
		String selectedot = request.getParameter("selectedot");

		int seqno = 1;

		if (anifees.equals("")) {
			anifees = "0";
		}

		if (psurcharge.equals("")) {
			psurcharge = "0";
		}
		if (panetcharge.equals("")) {
			panetcharge = "0";
		}
		if (sic.equals("")) {
			sic = "0";
		}
		// if(assistaffcharge.equals("")){
		// assistaffcharge="0";
		// }
		/*
		 * int slotId1 = 0; int diaryuserId1 = 0;
		 * 
		 * if(selectedid == 0){ slotId1 = Integer.parseInt(slotId); diaryuserId1
		 * = Integer.parseInt(diaryuserId); }
		 */

		NotAvailableSlot beforeUpdateData = new NotAvailableSlot();

		try {
			connection = Connection_provider.getconnection();
			MasterDAO masterDAO = new JDBCMasterDAO(connection);
			NotAvailableSlot notAvailableSlot = new NotAvailableSlot();
			notAvailableSlot.setApmtSlotId(Integer.parseInt(slotId));
			notAvailableSlot.setDiaryUserId(Integer.parseInt(diaryuserId));
			IpdDAO ipdDAO = new JDBCIpdDAO(connection);
			UserProfileDAO userProfileDAO = new JDBCUserProfileDAO(connection);
			UserProfile userProfile = userProfileDAO.getUserprofileDetails(notAvailableSlot.getDiaryUserId());
			String dusername = userProfile.getInitial() + " " + userProfile.getFirstname() + " "
					+ userProfile.getLastname();
			notAvailableSlot.setDiaryUser(dusername);

			//   25 Jan 2018
			String otprocedureplaned = masterDAO.getMasterChargeType(otprocedureplaned1);

			//   set ipdno while booking ot appointment
			if (otprocedureplaned1 != null) {
				if (!otprocedureplaned1.equals("0")) {
					int lastipdid = ipdDAO.getLastIpdId(clientId);
					if (otipdnos == null) {
						otipdnos = String.valueOf(lastipdid);
					} else if (otipdnos.equals("")) {
						otipdnos = String.valueOf(lastipdid);
					}
				}
			}

			notAvailableSlot.setDept(dept);
			notAvailableSlot.setLocation(location);
			notAvailableSlot.setRoom(room);
			notAvailableSlot.setCommencing(commencing);
			notAvailableSlot.setSTime(sTime);
			notAvailableSlot.setEndTime(endTime);
			notAvailableSlot.setApmtDuration(apmtDuration);
			notAvailableSlot.setClient(client);
			notAvailableSlot.setApmtType(apmtType);
			notAvailableSlot.setNotes(notes);
			notAvailableSlot.setClientId(clientId);
			notAvailableSlot.setTreatmentEpisodeId(treatmentEpisodeId);
			notAvailableSlot.setAddedBy(loginInfo.getUserId());
			notAvailableSlot.setModifiedBy(loginInfo.getUserId());
			notAvailableSlot.setCondition(condition);
			notAvailableSlot.setPayBy(whopay);
			notAvailableSlot.setStafflistid(stafflistid);

			NotAvailableSlotDAO notAvailableSlotDAO = new JDBCNotAvailableSlotDAO(connection);
			DiaryManagementDAO diaryManagementDAO = new JDBCDiaryManagentDAO(connection);
			BedDao bedDao = new JDBCBedDao(connection);
			String zero = "0";
			int appointmentid = 0;
			// 2014-08-08
			// current date and time in dd/mm/yyyy
			String currentDate = DateTimeUtils.getDateinSimpleFormate(new Date());
			String datetemp[] = currentDate.split(" ");
			String temp1[] = datetemp[0].split("-");
			String date = temp1[0] + "/" + temp1[1] + "/" + temp1[2];
			String commencingtemp[] = commencing.split(" ");
			String tempC[] = commencingtemp[0].split("-");
			String commencingTemp = commencing;
			String time = (datetemp[2] + " " + datetemp[3]);

			// ot set get
			notAvailableSlot.setOtplan(otplaned);
			notAvailableSlot.setProcedure(otprocedureplaned);
			notAvailableSlot.setSurgeon(otsurgeonname);
			notAvailableSlot.setAnesthesia(otanesthesia);
			notAvailableSlot.setIpdno(otipdnos);

			notAvailableSlot.setPsurcharge(psurcharge);
			notAvailableSlot.setPanetcharge(panetcharge);
			notAvailableSlot.setAnifees(anifees);
			notAvailableSlot.setSic(sic);

			if (otplaned.equals("Planed")) {
				Bed bed = bedDao.getEditIpdData(otipdnos);
				notAvailableSlot.setWardid(bed.getWardid());
			}

			// update client bpayby

			ClientDAO clientDAO = new JDBCClientDAO(connection);

			int updatewhopay = clientDAO.updateWhoPay(notAvailableSlot.getClientId(), notAvailableSlot.getPayBy());

			if (selectedid == 0) {

				if (!isot) {
					notAvailableSlot.setOtplan("0");
					notAvailableSlot.setProcedure("0");
					notAvailableSlot.setSurgeon("0");
					notAvailableSlot.setAnesthesia("0");
					notAvailableSlot.setIpdno("0");
					notAvailableSlot.setWardid("0");

					notAvailableSlot.setPsurcharge("0");
					notAvailableSlot.setPanetcharge("0");
					notAvailableSlot.setAnifees("0");
					notAvailableSlot.setSic("0");

				}

				int usedsession = diaryManagementDAO.getTempUsedSession(notAvailableSlot.getTreatmentEpisodeId());
				usedsession = usedsession + 1;

				if (treatmentEpisodeId.equals(zero)) {
					usedsession = 0;
				}
				notAvailableSlot.setUsedsession(Integer.toString(usedsession));

				boolean checkEventExist = notAvailableSlotDAO.checkEventAllreadyExist(notAvailableSlot.getCommencing(),
						location, diaryuserId, notAvailableSlot.getSTime(), notAvailableSlot.getEndTime());
				if (checkEventExist) {
					checkEventExist = cheForWeekRepeatAfterEventExist(notAvailableSlot.getCommencing(), location,
							diaryuserId, notAvailableSlot.getSTime(), notAvailableSlot.getEndTime());
				}
				if (!checkEventExist) {

					// save abrivation seq no
					String cdate = DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone()).split(" ")[0];
					boolean checkifseq = notAvailableSlotDAO.checkifSequenceExist(cdate,
							notAvailableSlot.getDiaryUserId());
					String abrivationid = "";
					// String clinicabrivation =
					// clientDAO.getClinicAbrivation(loginInfo.getClinicid());
					String tempd[] = cdate.split("-");
					String y = tempd[0];
					String m = tempd[1];
					String d = tempd[2];

					// boolean ch =
					// notAvailableSlotDAO.checkEventAllreadyExist(notAvailableSlot.getCommencing(),location,
					// Integer.toString(notAvailableSlot.getDiaryUserId()),notAvailableSlot.getSTime(),notAvailableSlot.getEndTime());
					// if(!ch){
					appointmentid = notAvailableSlotDAO.saveAppointment(notAvailableSlot);
					// }

					if (checkifseq) {
						seqno = notAvailableSlotDAO.getSqeunceNumber(cdate, notAvailableSlot.getDiaryUserId());
						seqno++;
						int r = notAvailableSlotDAO.InserCdateSeq(cdate, seqno, appointmentid,
								notAvailableSlot.getDiaryUserId());
						// SNH170609001
						/*
						 * int yr = Integer.parseInt(y)%1000; abrivationid =
						 * clinicabrivation + yr + m +d + "00" + seqno;
						 */
					} else {

						seqno = 1;
						int r = notAvailableSlotDAO.InserCdateSeq(cdate, seqno, appointmentid,
								notAvailableSlot.getDiaryUserId());
						// String seqno = clientDAO.getSqeunceNumber(cdate);
						/*
						 * int yr = Integer.parseInt(y)%1000; abrivationid =
						 * clinicabrivation + yr + m +d + "00" + seqno;
						 */
					}

					Client client2 = clientDAO.getClientDetails(notAvailableSlot.getClientId());
					if (!client2.getCasualtyid().equals("0")) {

						int updc = bedDao.updateCasualtyid(Integer.parseInt(notAvailableSlot.getClientId()));
					}
					if (isot) {
						String temp[] = stafflistid.split(",");
						for (int i = 0; i < temp.length; i++) {
							if (i > 0) {
								int otid = notAvailableSlotDAO.saveParenrotData(notAvailableSlot.getCommencing(),
										selectedot, temp[i], appointmentid);
							}

							// block slot for ot
							stafflistid = stafflistid + "," + notAvailableSlot.getAnesthesia() + ","
									+ notAvailableSlot.getSurgeon();
							/*
							 * String temps[] = stafflistid.split(","); for(int
							 * b=0;b<temps.length;b++){ if(b>0){ String
							 * selectedpractid = temps[b];
							 * 
							 * UserProfile userProfile2 =
							 * userProfileDAO.getUserprofileDetails(Integer.
							 * parseInt(selectedpractid)); String fullname =
							 * userProfile2.getInitial() + " " +
							 * userProfile2.getFirstname() + " " +
							 * userProfile2.getLastname();
							 * notAvailableSlot.setDiaryUser(fullname); int
							 * apmtSlotid =
							 * notAvailableSlotDAO.getOtAppointmentSlotID(
							 * notAvailableSlot.getCommencing(),Integer.parseInt
							 * (selectedpractid),notAvailableSlot.getSTime(),
							 * notAvailableSlot.getEndTime(),notAvailableSlot.
							 * getLocation());
							 * notAvailableSlot.setApmtSlotId(apmtSlotid);
							 * checkEventExist =
							 * notAvailableSlotDAO.checkEventAllreadyExist(
							 * notAvailableSlot.getCommencing(),location,
							 * selectedpractid,notAvailableSlot.getSTime(),
							 * notAvailableSlot.getEndTime());
							 * if(!checkEventExist){
							 * notAvailableSlot.setStatus("1");
							 * notAvailableSlot.setDiaryUserId(Integer.parseInt(
							 * selectedpractid));
							 * notAvailableSlot.setBlockot(appointmentid);
							 * notAvailableSlot.setReasonforblock("OT Booked");
							 * int block = notAvailableSlotDAO.saveBlockSlot(
							 * notAvailableSlot, "opd"); } } }
							 */

						}

						// send sms to accountants
						double charge = notAvailableSlotDAO.getCharge(notAvailableSlot.getApmtType());
						ArrayList<Master> accUserList = notAvailableSlotDAO.getAccountUserList();
						for (Master mstr : accUserList) {
							UserProfile u = userProfileDAO.getUserprofileDetails(mstr.getId());
							String accountant = u.getInitial() + " " + u.getFirstname() + " " + u.getLastname();

							String otmsg = accountant + "it has been informed you that " + notAvailableSlot.getClient()
									+ " has OT " + notAvailableSlot.getProcedure() + " scheduled on date "
									+ notAvailableSlot.getCommencing() + " of " + Constants.getCurrency(loginInfo)
									+ DateTimeUtils.changeFormat(charge) + " kindly check payment";

							SendSms os = new SendSms();
							os.send(otmsg, u.getMobile(), loginInfo, new EmailLetterLog());
						}
					}

					if (bookwithpayment.equals("1")) {
						saveOpdCharge(appointmentid, notAvailableSlot, invcetype, howpaid, totalamount, discount,
								payAmount, connection, disctype);
					}
					status = "Booked";
					int logsave = notAvailableSlotDAO.saveApmtInLog(appointmentid, date, time, loginInfo.getUserId(),
							clientId, commencingTemp, sTime, status,
							DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone()));
					// }

					/*
					 * else{ //book for ot String temp[] =
					 * stafflistid.split(","); int otid =
					 * notAvailableSlotDAO.saveParenrotData(notAvailableSlot.
					 * getCommencing(),selectedot); for(int
					 * i=0;i<temp.length;i++){ if(i>0){ String selectedpractid =
					 * temp[i];
					 * notAvailableSlot.setDiaryUserId(Integer.parseInt(
					 * selectedpractid)); UserProfile userProfile2 =
					 * userProfileDAO.getUserprofileDetails(Integer.parseInt(
					 * selectedpractid)); String fullname =
					 * userProfile2.getInitial() + " " +
					 * userProfile2.getFirstname() + " " +
					 * userProfile2.getLastname();
					 * notAvailableSlot.setDiaryUser(fullname); int apmtSlotid =
					 * notAvailableSlotDAO.getAppointmentSlotID(notAvailableSlot
					 * .getCommencing(),Integer.parseInt(selectedpractid),
					 * notAvailableSlot.getSTime(),notAvailableSlot.getEndTime()
					 * ,notAvailableSlot.getLocation());
					 * notAvailableSlot.setApmtSlotId(apmtSlotid);
					 * checkEventExist =
					 * notAvailableSlotDAO.checkEventAllreadyExist(
					 * notAvailableSlot.getCommencing(),location,
					 * selectedpractid,notAvailableSlot.getSTime(),
					 * notAvailableSlot.getEndTime()); if(!checkEventExist){
					 * 
					 * notAvailableSlot.setOtid(otid); appointmentid =
					 * notAvailableSlotDAO.saveAppointment(notAvailableSlot); }
					 * }
					 * 
					 * }
					 * 
					 * System.out.println("ot code"); }
					 */

				}

				// check apmtexist
				boolean isapmtexist = notAvailableSlotDAO.checkApmtExist(notAvailableSlot.getClientId());
				if (isapmtexist) {
					int sts = 1;
					int updp = notAvailableSlotDAO.updateNewPtsStatus(notAvailableSlot.getClientId(), sts);
				} else {
					int sts = 0;
					int updp = notAvailableSlotDAO.updateNewPtsStatus(notAvailableSlot.getClientId(), sts);
				}

				/*
				 * if(appointmentid!=0){
				 * 
				 * }
				 */
				int updatusessession = diaryManagementDAO.updateTreatmentSession(usedsession,
						notAvailableSlot.getTreatmentEpisodeId());

				log.debug("*****************appointment2");
				session.setAttribute("appointmentid", appointmentid);

				/*
				 * Clinic clinic = new Clinic(); ClinicDAO clinicDAO = new
				 * JDBCClinicDAO(connection); clinic =
				 * clinicDAO.getCliniclistDetails(loginInfo.getId()); String
				 * logo = clinic.getUserImageFileName(); String filePath =
				 * request.getRealPath("/liveData/clinicLogo/"+logo+"/");
				 * session.setAttribute("logopath", filePath);
				 */
				/*
				 * String twitter =
				 * request.getRealPath("/img/Entypo_f309(0)_32.png/");
				 * session.setAttribute("twitter", twitter);
				 * 
				 * String fb =
				 * request.getRealPath("/img/Entypo_f30c(0)_32.png/");
				 * session.setAttribute("fb", fb);
				 * 
				 * String gml =
				 * request.getRealPath("/img/Entypo_f30f(0)_32.png/");
				 * session.setAttribute("gml", gml);
				 */

				/*
				 * DBScheduler dbScheduler = new DBScheduler();
				 * dbScheduler.callScheduler(connection,loginInfo,request,
				 * appointmentid);
				 */
				// result =
				// notAvailableSlotDAO.saveCharge(notAvailableSlot,apmtType,result);
			} else {
				int selectedTreatmentEpisodeId = notAvailableSlotDAO.getSelecedTreatmentEpisodeId(selectedid);

				if (selectedTreatmentEpisodeId > 0) {

					if (selectedTreatmentEpisodeId != Integer.parseInt(notAvailableSlot.getTreatmentEpisodeId())) {
						int usedsession = diaryManagementDAO
								.getTempUsedSession(notAvailableSlot.getTreatmentEpisodeId());
						usedsession = usedsession + 1;
						if (treatmentEpisodeId.equals(zero)) {
							usedsession = 0;
						}
						int updatusessession = diaryManagementDAO.updateTreatmentSession(usedsession,
								notAvailableSlot.getTreatmentEpisodeId());

						int prevTreatmentSession = notAvailableSlotDAO
								.getPreviousTreatmentUsedSession(selectedTreatmentEpisodeId);
						prevTreatmentSession = prevTreatmentSession - 1;
						int updatePreviousTreatmentEpisode = notAvailableSlotDAO
								.updatePreviousTreatmentEpisode(prevTreatmentSession, selectedTreatmentEpisodeId);

						notAvailableSlot.setUsedsession(Integer.toString(usedsession));

					}

				}

				int usedsession = diaryManagementDAO.getTempUsedSession(notAvailableSlot.getTreatmentEpisodeId());
				// usedsession = usedsession+1;
				if (treatmentEpisodeId.equals(zero)) {
					usedsession = 0;
				}
				notAvailableSlot.setUsedsession(Integer.toString(usedsession));
				beforeUpdateData = notAvailableSlotDAO.getApmtDetailsForLog(selectedid);
				int update = notAvailableSlotDAO.updateAppointment(notAvailableSlot, selectedid);
				// update oT data
				if (!notAvailableSlot.getOtplan().equals("0")) {
					int upot = notAvailableSlotDAO.updateOTdata(notAvailableSlot, selectedid);

					// delete asistant doctor
					int delasisdoctor = notAvailableSlotDAO.deleteAsistantDoctor(selectedid);

					String temp[] = stafflistid.split(",");
					for (int i = 0; i < temp.length; i++) {
						if (i > 0) {

							int otid = notAvailableSlotDAO.saveParenrotData(notAvailableSlot.getCommencing(),
									selectedot, temp[i], selectedid);
						}
					}

					// block slot for ot
					stafflistid = stafflistid + "," + notAvailableSlot.getAnesthesia() + ","
							+ notAvailableSlot.getSurgeon();
					int delblockot = notAvailableSlotDAO.deleteBlockOt(Integer.toString(selectedid));
					String temps[] = stafflistid.split(",");
					/*
					 * for(int b=0;b<temps.length;b++){ if(b>0){ String
					 * selectedpractid = temps[b];
					 * 
					 * UserProfile userProfile2 =
					 * userProfileDAO.getUserprofileDetails(Integer.parseInt(
					 * selectedpractid)); String fullname =
					 * userProfile2.getInitial() + " " +
					 * userProfile2.getFirstname() + " " +
					 * userProfile2.getLastname();
					 * notAvailableSlot.setDiaryUser(fullname); int apmtSlotid =
					 * notAvailableSlotDAO.getOtAppointmentSlotID(
					 * notAvailableSlot.getCommencing(),Integer.parseInt(
					 * selectedpractid),notAvailableSlot.getSTime(),
					 * notAvailableSlot.getEndTime(),notAvailableSlot.
					 * getLocation());
					 * notAvailableSlot.setApmtSlotId(apmtSlotid); boolean
					 * checkEventExist =
					 * notAvailableSlotDAO.checkEventAllreadyExist(
					 * notAvailableSlot.getCommencing(),location,
					 * selectedpractid,notAvailableSlot.getSTime(),
					 * notAvailableSlot.getEndTime()); if(!checkEventExist){
					 * notAvailableSlot.setStatus("1");
					 * notAvailableSlot.setDiaryUserId(Integer.parseInt(
					 * selectedpractid));
					 * notAvailableSlot.setBlockot(selectedid);
					 * notAvailableSlot.setReasonforblock("OT Booked"); int
					 * block =
					 * notAvailableSlotDAO.saveBlockSlot(notAvailableSlot,
					 * "opd"); } } }
					 */

					// send sms to accountants
					double charge = notAvailableSlotDAO.getCharge(notAvailableSlot.getApmtType());
					ArrayList<Master> accUserList = notAvailableSlotDAO.getAccountUserList();
					for (Master mstr : accUserList) {
						UserProfile u = userProfileDAO.getUserprofileDetails(mstr.getId());
						String accountant = u.getInitial() + " " + u.getFirstname() + " " + u.getLastname();

						String otmsg = accountant + "it has been informed you that " + notAvailableSlot.getClient()
								+ " has OT " + notAvailableSlot.getProcedure() + " scheduled on date "
								+ notAvailableSlot.getCommencing() + " of " + Constants.getCurrency(loginInfo)
								+ DateTimeUtils.changeFormat(charge) + " kindly check payment";

						SendSms os = new SendSms();
						os.send(otmsg, u.getMobile(), loginInfo, new EmailLetterLog());
					}
				}

				appointmentid = selectedid;
				status = "Modified";
				int logsave = notAvailableSlotDAO.saveApmtInLog(appointmentid, date, time, loginInfo.getUserId(),
						clientId, commencingTemp, sTime, status,
						DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone()));

				// System.out.println("hello");
				CompleteAptmDAO completeAptmDAO = new JDBCCompleteAptmDAO(connection);
				int results = completeAptmDAO.deleteComplteApmt(loginInfo.getId());

				if (selectedid > 0) {
					// set all treatment episode sessions

					TreatmentEpisodeDAO treatmentEpisodeDAO = new JDBCTreatmentEpisode(connection);
					ArrayList<TreatmentEpisode> treatmentEpisodeList = treatmentEpisodeDAO
							.getTreatmentEpisodeList(clientId);
					for (TreatmentEpisode treatmentEpisode : treatmentEpisodeList) {

						ArrayList<NotAvailableSlot> dnaoffsetList = notAvailableSlotDAO
								.getDnaOffsetList(Integer.toString(treatmentEpisode.getId()));
						int i = 1;
						for (NotAvailableSlot nt : dnaoffsetList) {

							int updateusedsession = notAvailableSlotDAO.updateAppointmentUsedSession(nt.getId(), i);

							i++;

						}

						int updatetepisodeusedsession = notAvailableSlotDAO.updateupdateTpEpisodeusedsession(
								Integer.toString(treatmentEpisode.getId()), dnaoffsetList.size());
					}

				}

				log.debug("*****************appointment3");
				session.setAttribute("appointmentid", selectedid);

				/*
				 * String filePath =
				 * request.getRealPath("/Design/images/logo.png/");
				 * session.setAttribute("logopath", filePath);
				 */
				/*
				 * DBScheduler dbScheduler = new DBScheduler();
				 * dbScheduler.callScheduler(connection,loginInfo,request,
				 * appointmentid);
				 */

			}

			// update client condition
			int updatecondition = notAvailableSlotDAO.updateClientCondition(notAvailableSlot.getClientId(), condition);

			boolean wholeweek = Boolean.parseBoolean(request.getParameter("wholeweek"));
			int weekNumber = Integer.parseInt(request.getParameter("weekNumber"));

			// repeat code

			if (weekNumber > 0) {
				boolean monday = Boolean.parseBoolean(request.getParameter("monday"));
				boolean tuesday = Boolean.parseBoolean(request.getParameter("tuesday"));
				boolean wednesday = Boolean.parseBoolean(request.getParameter("wednesday"));
				boolean thursday = Boolean.parseBoolean(request.getParameter("thursday"));
				boolean friday = Boolean.parseBoolean(request.getParameter("friday"));
				boolean saturday = Boolean.parseBoolean(request.getParameter("saturday"));
				boolean sunday = Boolean.parseBoolean(request.getParameter("sunday"));

				weekNumber = Integer.parseInt(request.getParameter("weekNumber"));

				String dt = notAvailableSlot.getCommencing();
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				Calendar c = Calendar.getInstance();
				c.setTime(sdf.parse(dt));

				for (int i = 0; i < weekNumber; i++) {

					for (int j = 0; j <= 6; j++) {

						c.add(Calendar.DATE, 1); // number of days to add
						dt = sdf.format(c.getTime()); // dt is now the new date

						notAvailableSlot.setCommencing(dt);
						commencingTemp = dt;

						String wcdate[] = dt.split("-");

						// set weekname
						int wyear = Integer.parseInt(wcdate[0]);
						int month = Integer.parseInt(wcdate[1]);
						int day = Integer.parseInt(wcdate[2]);

						String cweekName = DateTimeUtils.getWeekName(wyear, month, day);

						// System.out.println(dt);
						// System.out.println(cweekName);

						if (monday && cweekName.equals(Constants.MONDAY)) {

							// common for all
							int apmtSlotid = notAvailableSlotDAO.getAppointmentSlotID(notAvailableSlot.getCommencing(),
									notAvailableSlot.getDiaryUserId(), notAvailableSlot.getSTime(),
									notAvailableSlot.getEndTime(), notAvailableSlot.getLocation());
							if (apmtSlotid != 0) {
								notAvailableSlot.setApmtSlotId(apmtSlotid);
								boolean checkEventExist = notAvailableSlotDAO.checkEventAllreadyExist(
										notAvailableSlot.getCommencing(), location, diaryuserId,
										notAvailableSlot.getSTime(), notAvailableSlot.getEndTime());
								if (checkEventExist) {
									checkEventExist = cheForWeekRepeatAfterEventExist(notAvailableSlot.getCommencing(),
											location, diaryuserId, notAvailableSlot.getSTime(),
											notAvailableSlot.getEndTime());
								}
								if (!checkEventExist) {
									int usedsession = diaryManagementDAO
											.getTempUsedSession(notAvailableSlot.getTreatmentEpisodeId());
									int sessions = diaryManagementDAO
											.getTempSessions(notAvailableSlot.getTreatmentEpisodeId());
									if (usedsession == sessions) {
										if (sessions > 0) {
											break;
										}
									}
									usedsession = usedsession + 1;
									if (treatmentEpisodeId.equals(zero)) {
										usedsession = 0;
									}
									notAvailableSlot.setUsedsession(Integer.toString(usedsession));
									appointmentid = notAvailableSlotDAO.saveAppointment(notAvailableSlot);
									int updatusessession = diaryManagementDAO.updateTreatmentSession(usedsession,
											notAvailableSlot.getTreatmentEpisodeId());
									// Log Save
									status = "Booked";
									int logsave = notAvailableSlotDAO.saveApmtInLog(appointmentid, date, time,
											loginInfo.getUserId(), clientId, commencingTemp, sTime, status,
											DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone()));
								} else {
									break;
								}

							} else {
								// break;
							}

						} else if (tuesday && cweekName.equals(Constants.TUESDAY)) {
							// common for all
							int apmtSlotid = notAvailableSlotDAO.getAppointmentSlotID(notAvailableSlot.getCommencing(),
									notAvailableSlot.getDiaryUserId(), notAvailableSlot.getSTime(),
									notAvailableSlot.getEndTime(), notAvailableSlot.getLocation());
							if (apmtSlotid != 0) {
								notAvailableSlot.setApmtSlotId(apmtSlotid);
								boolean checkEventExist = notAvailableSlotDAO.checkEventAllreadyExist(
										notAvailableSlot.getCommencing(), location, diaryuserId,
										notAvailableSlot.getSTime(), notAvailableSlot.getEndTime());
								if (checkEventExist) {
									checkEventExist = cheForWeekRepeatAfterEventExist(notAvailableSlot.getCommencing(),
											location, diaryuserId, notAvailableSlot.getSTime(),
											notAvailableSlot.getEndTime());
								}
								if (!checkEventExist) {
									int usedsession = diaryManagementDAO
											.getTempUsedSession(notAvailableSlot.getTreatmentEpisodeId());
									int sessions = diaryManagementDAO
											.getTempSessions(notAvailableSlot.getTreatmentEpisodeId());
									if (usedsession == sessions) {
										if (sessions > 0) {
											break;
										}
									}
									usedsession = usedsession + 1;
									if (treatmentEpisodeId.equals(zero)) {
										usedsession = 0;
									}
									notAvailableSlot.setUsedsession(Integer.toString(usedsession));
									appointmentid = notAvailableSlotDAO.saveAppointment(notAvailableSlot);
									int updatusessession = diaryManagementDAO.updateTreatmentSession(usedsession,
											notAvailableSlot.getTreatmentEpisodeId());
									// Log Save
									status = "Booked";
									int logsave = notAvailableSlotDAO.saveApmtInLog(appointmentid, date, time,
											loginInfo.getUserId(), clientId, commencingTemp, sTime, status,
											DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone()));
								} else {
									break;
								}

							} else {
								// break;
							}
						} else if (wednesday && cweekName.equals(Constants.WEDNEDAY)) {
							// common for all
							int apmtSlotid = notAvailableSlotDAO.getAppointmentSlotID(notAvailableSlot.getCommencing(),
									notAvailableSlot.getDiaryUserId(), notAvailableSlot.getSTime(),
									notAvailableSlot.getEndTime(), notAvailableSlot.getLocation());
							if (apmtSlotid != 0) {
								notAvailableSlot.setApmtSlotId(apmtSlotid);
								boolean checkEventExist = notAvailableSlotDAO.checkEventAllreadyExist(
										notAvailableSlot.getCommencing(), location, diaryuserId,
										notAvailableSlot.getSTime(), notAvailableSlot.getEndTime());
								if (checkEventExist) {
									checkEventExist = cheForWeekRepeatAfterEventExist(notAvailableSlot.getCommencing(),
											location, diaryuserId, notAvailableSlot.getSTime(),
											notAvailableSlot.getEndTime());
								}
								if (!checkEventExist) {
									int usedsession = diaryManagementDAO
											.getTempUsedSession(notAvailableSlot.getTreatmentEpisodeId());
									int sessions = diaryManagementDAO
											.getTempSessions(notAvailableSlot.getTreatmentEpisodeId());
									if (usedsession == sessions) {
										if (sessions > 0) {
											break;
										}
									}
									usedsession = usedsession + 1;
									if (treatmentEpisodeId.equals(zero)) {
										usedsession = 0;
									}
									notAvailableSlot.setUsedsession(Integer.toString(usedsession));
									appointmentid = notAvailableSlotDAO.saveAppointment(notAvailableSlot);
									int updatusessession = diaryManagementDAO.updateTreatmentSession(usedsession,
											notAvailableSlot.getTreatmentEpisodeId());
									// Log Save
									status = "Booked";
									int logsave = notAvailableSlotDAO.saveApmtInLog(appointmentid, date, time,
											loginInfo.getUserId(), clientId, commencingTemp, sTime, status,
											DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone()));
								} else {
									break;
								}

							} else {
								// break;
							}
						} else if (thursday && cweekName.equals(Constants.THUSRDAY)) {
							// common for all
							int apmtSlotid = notAvailableSlotDAO.getAppointmentSlotID(notAvailableSlot.getCommencing(),
									notAvailableSlot.getDiaryUserId(), notAvailableSlot.getSTime(),
									notAvailableSlot.getEndTime(), notAvailableSlot.getLocation());
							if (apmtSlotid != 0) {
								notAvailableSlot.setApmtSlotId(apmtSlotid);
								boolean checkEventExist = notAvailableSlotDAO.checkEventAllreadyExist(
										notAvailableSlot.getCommencing(), location, diaryuserId,
										notAvailableSlot.getSTime(), notAvailableSlot.getEndTime());
								if (checkEventExist) {
									checkEventExist = cheForWeekRepeatAfterEventExist(notAvailableSlot.getCommencing(),
											location, diaryuserId, notAvailableSlot.getSTime(),
											notAvailableSlot.getEndTime());
								}
								if (!checkEventExist) {
									int usedsession = diaryManagementDAO
											.getTempUsedSession(notAvailableSlot.getTreatmentEpisodeId());
									int sessions = diaryManagementDAO
											.getTempSessions(notAvailableSlot.getTreatmentEpisodeId());
									if (usedsession == sessions) {
										if (sessions > 0) {
											break;
										}
									}
									usedsession = usedsession + 1;
									if (treatmentEpisodeId.equals(zero)) {
										usedsession = 0;
									}
									notAvailableSlot.setUsedsession(Integer.toString(usedsession));
									appointmentid = notAvailableSlotDAO.saveAppointment(notAvailableSlot);
									int updatusessession = diaryManagementDAO.updateTreatmentSession(usedsession,
											notAvailableSlot.getTreatmentEpisodeId());
									// Log Save
									status = "Booked";
									int logsave = notAvailableSlotDAO.saveApmtInLog(appointmentid, date, time,
											loginInfo.getUserId(), clientId, commencingTemp, sTime, status,
											DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone()));
								} else {
									break;
								}

							} else {
								// break;
							}
						} else if (friday && cweekName.equals(Constants.FRIDAY)) {
							// common for all
							int apmtSlotid = notAvailableSlotDAO.getAppointmentSlotID(notAvailableSlot.getCommencing(),
									notAvailableSlot.getDiaryUserId(), notAvailableSlot.getSTime(),
									notAvailableSlot.getEndTime(), notAvailableSlot.getLocation());
							if (apmtSlotid != 0) {
								notAvailableSlot.setApmtSlotId(apmtSlotid);
								boolean checkEventExist = notAvailableSlotDAO.checkEventAllreadyExist(
										notAvailableSlot.getCommencing(), location, diaryuserId,
										notAvailableSlot.getSTime(), notAvailableSlot.getEndTime());
								if (checkEventExist) {
									checkEventExist = cheForWeekRepeatAfterEventExist(notAvailableSlot.getCommencing(),
											location, diaryuserId, notAvailableSlot.getSTime(),
											notAvailableSlot.getEndTime());
								}
								if (!checkEventExist) {
									int usedsession = diaryManagementDAO
											.getTempUsedSession(notAvailableSlot.getTreatmentEpisodeId());
									int sessions = diaryManagementDAO
											.getTempSessions(notAvailableSlot.getTreatmentEpisodeId());
									if (usedsession == sessions) {
										if (sessions > 0) {
											break;
										}
									}
									usedsession = usedsession + 1;
									if (treatmentEpisodeId.equals(zero)) {
										usedsession = 0;
									}
									notAvailableSlot.setUsedsession(Integer.toString(usedsession));
									appointmentid = notAvailableSlotDAO.saveAppointment(notAvailableSlot);
									int updatusessession = diaryManagementDAO.updateTreatmentSession(usedsession,
											notAvailableSlot.getTreatmentEpisodeId());
									// Log Save
									status = "Booked";
									int logsave = notAvailableSlotDAO.saveApmtInLog(appointmentid, date, time,
											loginInfo.getUserId(), clientId, commencingTemp, sTime, status,
											DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone()));
								} else {
									break;
								}

							} else {
								// break;
							}
						} else if (saturday && cweekName.equals(Constants.SATURDAY)) {
							// common for all
							int apmtSlotid = notAvailableSlotDAO.getAppointmentSlotID(notAvailableSlot.getCommencing(),
									notAvailableSlot.getDiaryUserId(), notAvailableSlot.getSTime(),
									notAvailableSlot.getEndTime(), notAvailableSlot.getLocation());
							if (apmtSlotid != 0) {
								notAvailableSlot.setApmtSlotId(apmtSlotid);
								boolean checkEventExist = notAvailableSlotDAO.checkEventAllreadyExist(
										notAvailableSlot.getCommencing(), location, diaryuserId,
										notAvailableSlot.getSTime(), notAvailableSlot.getEndTime());
								if (checkEventExist) {
									checkEventExist = cheForWeekRepeatAfterEventExist(notAvailableSlot.getCommencing(),
											location, diaryuserId, notAvailableSlot.getSTime(),
											notAvailableSlot.getEndTime());
								}
								if (!checkEventExist) {
									int usedsession = diaryManagementDAO
											.getTempUsedSession(notAvailableSlot.getTreatmentEpisodeId());
									int sessions = diaryManagementDAO
											.getTempSessions(notAvailableSlot.getTreatmentEpisodeId());
									if (usedsession == sessions) {
										if (sessions > 0) {
											break;
										}
									}
									usedsession = usedsession + 1;
									if (treatmentEpisodeId.equals(zero)) {
										usedsession = 0;
									}
									notAvailableSlot.setUsedsession(Integer.toString(usedsession));
									appointmentid = notAvailableSlotDAO.saveAppointment(notAvailableSlot);
									int updatusessession = diaryManagementDAO.updateTreatmentSession(usedsession,
											notAvailableSlot.getTreatmentEpisodeId());
									// Log Save
									status = "Booked";
									int logsave = notAvailableSlotDAO.saveApmtInLog(appointmentid, date, time,
											loginInfo.getUserId(), clientId, commencingTemp, sTime, status,
											DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone()));
								} else {
									break;
								}

							} else {
								// break;
							}
						} else if (sunday && cweekName.equals(Constants.SUNDAY)) {
							// common for all
							int apmtSlotid = notAvailableSlotDAO.getAppointmentSlotID(notAvailableSlot.getCommencing(),
									notAvailableSlot.getDiaryUserId(), notAvailableSlot.getSTime(),
									notAvailableSlot.getEndTime(), notAvailableSlot.getLocation());
							if (apmtSlotid != 0) {
								notAvailableSlot.setApmtSlotId(apmtSlotid);
								boolean checkEventExist = notAvailableSlotDAO.checkEventAllreadyExist(
										notAvailableSlot.getCommencing(), location, diaryuserId,
										notAvailableSlot.getSTime(), notAvailableSlot.getEndTime());
								if (checkEventExist) {
									checkEventExist = cheForWeekRepeatAfterEventExist(notAvailableSlot.getCommencing(),
											location, diaryuserId, notAvailableSlot.getSTime(),
											notAvailableSlot.getEndTime());
								}
								if (!checkEventExist) {
									int usedsession = diaryManagementDAO
											.getTempUsedSession(notAvailableSlot.getTreatmentEpisodeId());
									int sessions = diaryManagementDAO
											.getTempSessions(notAvailableSlot.getTreatmentEpisodeId());
									if (usedsession == sessions) {
										if (sessions > 0) {
											break;
										}
									}
									usedsession = usedsession + 1;
									if (treatmentEpisodeId.equals(zero)) {
										usedsession = 0;
									}
									notAvailableSlot.setUsedsession(Integer.toString(usedsession));
									appointmentid = notAvailableSlotDAO.saveAppointment(notAvailableSlot);
									int updatusessession = diaryManagementDAO.updateTreatmentSession(usedsession,
											notAvailableSlot.getTreatmentEpisodeId());
									// Log Save
									status = "Booked";
									int logsave = notAvailableSlotDAO.saveApmtInLog(appointmentid, date, time,
											loginInfo.getUserId(), clientId, commencingTemp, sTime, status,
											DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone()));
								} else {
									break;
								}

							} else {
								// break;
							}
						} else if (!monday && !tuesday && !wednesday && !thursday && !friday && !saturday && !sunday) {
							// common for all
							int apmtSlotid = notAvailableSlotDAO.getAppointmentSlotID(notAvailableSlot.getCommencing(),
									notAvailableSlot.getDiaryUserId(), notAvailableSlot.getSTime(),
									notAvailableSlot.getEndTime(), notAvailableSlot.getLocation());
							if (apmtSlotid != 0) {
								notAvailableSlot.setApmtSlotId(apmtSlotid);
								boolean checkEventExist = notAvailableSlotDAO.checkEventAllreadyExist(
										notAvailableSlot.getCommencing(), location, diaryuserId,
										notAvailableSlot.getSTime(), notAvailableSlot.getEndTime());
								if (checkEventExist) {
									checkEventExist = cheForWeekRepeatAfterEventExist(notAvailableSlot.getCommencing(),
											location, diaryuserId, notAvailableSlot.getSTime(),
											notAvailableSlot.getEndTime());
								}
								if (!checkEventExist) {
									int usedsession = diaryManagementDAO
											.getTempUsedSession(notAvailableSlot.getTreatmentEpisodeId());
									int sessions = diaryManagementDAO
											.getTempSessions(notAvailableSlot.getTreatmentEpisodeId());
									if (usedsession == sessions) {
										if (sessions > 0) {
											break;
										}
									}
									usedsession = usedsession + 1;
									if (treatmentEpisodeId.equals(zero)) {
										usedsession = 0;
									}
									notAvailableSlot.setUsedsession(Integer.toString(usedsession));
									appointmentid = notAvailableSlotDAO.saveAppointment(notAvailableSlot);
									int updatusessession = diaryManagementDAO.updateTreatmentSession(usedsession,
											notAvailableSlot.getTreatmentEpisodeId());
									// Log Save
									status = "Booked";
									int logsave = notAvailableSlotDAO.saveApmtInLog(appointmentid, date, time,
											loginInfo.getUserId(), clientId, commencingTemp, sTime, status,
											DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone()));
								} else {
									break;
								}

							} else {
								// break;
							}
						}

					}
				}
			}

			session.setAttribute("connection", connection);

			notAvailableSlot = notAvailableSlotDAO.getAvailableSlotdata(appointmentid);

			String diaryuseridname = notAvailableSlotDAO.getDiaryUserIdName(notAvailableSlot.getDiaryUserId());

			String updatedAppointmentData = notAvailableSlot.getCommencing() + "/" + notAvailableSlot.getsTime() + "/"
					+ notAvailableSlot.getEndTime() + "/" + notAvailableSlot.getDiaryUser() + "/"
					+ notAvailableSlot.getClientName() + "/" + notAvailableSlot.getDiaryUserId() + "/"
					+ notAvailableSlot.getId() + "/" + notAvailableSlot.getClientId() + "/" + loginInfo.getClinicid()
					+ "/" + loginInfo.getUserType() + "/" + loginInfo.getClinicUserid() + "/" + diaryuseridname;

			// send sms
			ClinicDAO clinicDAO = new JDBCClinicDAO(connection);
			boolean isSMSActive = clinicDAO.isSmsActive(loginInfo.getId());
			if (isSMSActive) {

			}
			// autosms(Integer.toString(appointmentid), status
			// ,beforeUpdateData.getSTime() ,beforeUpdateData.getCommencing() );

			// autosms(Integer.toString(appointmentid), status
			// ,beforeUpdateData.getSTime()
			// ,beforeUpdateData.getCommencing(),seqno );
			// @ruchi check smscheck then send msg
			autosmsCheckSms(Integer.toString(appointmentid), status, beforeUpdateData.getSTime(),
					beforeUpdateData.getCommencing(), seqno);

			response.setContentType("text/html");
			response.setHeader("Cache-Control", "no-cache");
			response.getWriter().write("" + updatedAppointmentData + "");

		} catch (Exception e) {
			e.printStackTrace();
			log.debug("***************" + e.getMessage());
		}

		finally {
			connection.close();
		}

		return null;
	}

	private void saveOpdCharge(int appointmentid, NotAvailableSlot notAvailableSlot, String invcetype, String howpaid,
			String totalamount, String discounts, String payAmount, Connection connection, String disctype)
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

			ClientDAO clientDAO = new JDBCClientDAO(connection);
			Client client = clientDAO.getClientDetails(notAvailableSlot.getClientId());
			String clientname = client.getTitle() + " " + client.getFirstName() + " " + client.getLastName();

			CompleteAppointment completeAppointment = new CompleteAppointment();
			completeAppointment.setClientId(notAvailableSlot.getClientId());
			completeAppointment.setPractitionerId(Integer.toString(notAvailableSlot.getDiaryUserId()));
			completeAppointment.setUser(clientname);
			completeAppointment.setInvoiceDate(date);
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
			String apmtTYpeText = notAvailableSlotDAO.getAppointmentTypeText(notAvailableSlot.getApmtType());
			completeAppointment.setApmtType(apmtTYpeText);
			completeAppointment.setQuantity(1);
			completeAppointment.setCommencing(notAvailableSlot.getCommencing());
			completeAppointment.setMasterchargetype("Appointment Charge");

			// insert into apm_invoice
			int selfInvoice = completeAptmDAO.saveAmpmInvoice(completeAppointment, loginInfo.getId(),
					loginInfo.getUserId());

			// insert in apm_invoice_assessment
			completeAppointment.setCharges(totalamount);

			NotAvailableSlot not = notAvailableSlotDAO.getOTData(Integer.toString(appointmentid));
			if (!notAvailableSlot.getProcedure().equals("0")) {
				completeAppointment.setCharges(not.getChargeamout());
				completeAppointment.setMasterchargetype(notAvailableSlot.getProcedure());
			}
			int result = completeAptmDAO.saveInvoiceAssesment(completeAppointment, selfInvoice);

			// save ot more charges

			if (!notAvailableSlot.getProcedure().equals("0")) {

				completeAppointment.setMasterchargetype(notAvailableSlot.getProcedure());

				completeAppointment.setApmtType(Constants.SURGEON_CHARGE);
				completeAppointment.setCharges(notAvailableSlot.getPsurcharge());
				result = completeAptmDAO.saveInvoiceAssesment(completeAppointment, selfInvoice);

				completeAppointment.setApmtType(Constants.ANISTHESIA_CHARGE);
				completeAppointment.setCharges(notAvailableSlot.getPanetcharge());
				result = completeAptmDAO.saveInvoiceAssesment(completeAppointment, selfInvoice);

				completeAppointment.setApmtType(Constants.SIC_CHARGE);
				completeAppointment.setCharges(notAvailableSlot.getSic());
				result = completeAptmDAO.saveInvoiceAssesment(completeAppointment, selfInvoice);

			}

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
					DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone()), loginInfo.getId(), "0", invcetype,
					rinv, null, null, String.valueOf(notAvailableSlot.getDiaryUserId()), ipdid,0,"0");
			// update discount
			String userid = loginInfo.getUserId();
			String datetime = DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone());
			int p = accountsDAO.updatePercentageAmount(Integer.toString(invoiceid), discounts, disctype, userid,
					datetime);
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

			Accounts accounts = accountsDAO.getInvoiceChargesDetails(""+invoiceid);
			
			// save payment
			int re = accountsDAO.saveChargesPayment(notAvailableSlot.getClientId(), invoiceid, payAmount, howpaid,
					thirdPartyID, "", DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone()), 0,
					loginInfo.getUserId(), chequeno, bankname,accounts.getPredayornot(),accounts.getDate(),accounts.getItype(),"","");
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

		} catch (Exception e) {
			e.printStackTrace();
		} finally {

			connection.close();
		}

	}

	public String delete() throws Exception {
		if (!verifyLogin(request)) {
			return "login";
		}
		String selectedid = request.getParameter("selectedid");
		String cancelApmtNote = request.getParameter("cancelApmtNote");
		Connection connection = null;
		// current date and time in dd/mm/yyyy
		String currentDate = DateTimeUtils.getDateinSimpleFormate(new Date());
		String datetemp[] = currentDate.split(" ");
		String temp1[] = datetemp[0].split("-");
		String date = temp1[0] + "/" + temp1[1] + "/" + temp1[2];
		String time = (datetemp[2] + " " + datetemp[3]);

		try {
			connection = Connection_provider.getconnection();
			NotAvailableSlotDAO notAvailableSlotDAO = new JDBCNotAvailableSlotDAO(connection);
			ClinicDAO clinicDAO = new JDBCClinicDAO(connection);

			String opendb = (String) session.getAttribute("openedb");
			int status = notAvailableSlotDAO.getStatus(selectedid, opendb);
			if (status == 0) {
				int treatmeId = notAvailableSlotDAO.getSelecedTreatmentEpisodeId(Integer.parseInt(selectedid));
				int usedSession = notAvailableSlotDAO.getUsedSession(treatmeId, selectedid);
				ArrayList<NotAvailableSlot> usedSessionList = new ArrayList<NotAvailableSlot>();
				usedSessionList = notAvailableSlotDAO.getUsedSessionList(treatmeId, usedSession);

				for (NotAvailableSlot n : usedSessionList) {
					int updateAllPrevious = notAvailableSlotDAO.updateAllPrevious(n.getId(), treatmeId);
				}
				int updatesession = notAvailableSlotDAO.updateSessions(treatmeId);
			}
			NotAvailableSlot n = notAvailableSlotDAO.getApmtDetailsForLog(Integer.parseInt(selectedid));

			String commencingTemp = n.getCommencing();

			String apmtstatus = "Cancelled";

			// send sms
			ClientDAO clientDAO = new JDBCClientDAO(connection);
			NotAvailableSlot notAvailableSlot = notAvailableSlotDAO.getApmtDetailsForLog(Integer.parseInt(selectedid));
			UserProfileDAO userProfileDAO = new JDBCUserProfileDAO(connection);
			Client client = clientDAO.getClientDetails(notAvailableSlot.getClientId());
			UserProfile userProfile = userProfileDAO.getUserprofileDetails(notAvailableSlot.getDiaryUserId());

			int otid = notAvailableSlotDAO.checkotAppointment(selectedid);
			if (otid > 0) {
				int result = notAvailableSlotDAO.deleteOtApmt(otid);
			} else {
				if (opendb.equals("opd")) {
					int logsave = notAvailableSlotDAO.saveCancelApmtInLog(Integer.parseInt(selectedid),
							DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone()), time, loginInfo.getUserId(),
							n.getClientId(), commencingTemp, n.getSTime(), apmtstatus, cancelApmtNote,
							DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone()), n);

					int result = notAvailableSlotDAO.DeleteBlockedSlot(selectedid, opendb);
					// String message =
					// AllTemplateAction.getDeletedAppointmentSMSText(notAvailableSlot.getClientId(),
					// Integer.parseInt(selectedid), connection, loginInfo);
					// String message = "Appointment has cancelled";
					// if(loginInfo.getCountry().equals("India")){
					// SmsService s = new SmsService();
					// if(status==0){
					// s.sendSms(message, client.getMobNo(), loginInfo, new
					// EmailLetterLog());
					// boolean
					// isSMSActive=clinicDAO.isSmsActive(loginInfo.getId());
					// if(isSMSActive){
					// s.sendSms(message, userProfile.getMobile(), loginInfo,
					// new EmailLetterLog());
					// }
					//
					// }
					// }
				}

			}

			boolean isapmtexist = notAvailableSlotDAO.checkApmtExist(n.getClientId());
			if (isapmtexist) {
				int sts = 1;
				int updp = notAvailableSlotDAO.updateNewPtsStatus(n.getClientId(), sts);
			} else {
				int sts = 0;
				int updp = notAvailableSlotDAO.updateNewPtsStatus(n.getClientId(), sts);
			}

		} catch (Exception e) {
			log.debug("sendsms" + e.getMessage());
			// e.printStackTrace();
		} finally {

			connection.close();
		}

		return null;

	}

	public String sendsms() throws Exception {

		Connection connection = null;
		String appointmentid = request.getParameter("apmtid");
		String mobno = request.getParameter("mobno");
		String smstxt = request.getParameter("smstxt");

		try {
			connection = Connection_provider.getconnection();
			// send sms
			ClientDAO clientDAO = new JDBCClientDAO(connection);
			NotAvailableSlotDAO notAvailableSlotDAO = new JDBCNotAvailableSlotDAO(connection);
			NotAvailableSlot notAvailableSlot = notAvailableSlotDAO
					.getApmtDetailsForLog(Integer.parseInt(appointmentid));
			UserProfileDAO userProfileDAO = new JDBCUserProfileDAO(connection);
			Client client = clientDAO.getClientDetails(notAvailableSlot.getClientId());
			UserProfile userProfile = userProfileDAO.getUserprofileDetails(notAvailableSlot.getDiaryUserId());

			String message = smstxt;

			if (loginInfo.getCountry().equals("India")) {
				SmsService s = new SmsService();
				String templateid="";
				s.sendSms(message, mobno, loginInfo, new EmailLetterLog(),templateid);
				// s.sendSms(message, userProfile.getMobile(), loginInfo, new
				// EmailLetterLog());
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {

			connection.close();
		}

		return null;
	}

	public void autosms(String appointmentid, String status, String starttime, String apmtdate, int seqNO)
			throws Exception {

		// String appointmentid = request.getParameter("appointmentid");

		Connection connection = null;

		try {
			connection = Connection_provider.getconnection();
			// send sms
			ClientDAO clientDAO = new JDBCClientDAO(connection);
			NotAvailableSlotDAO notAvailableSlotDAO = new JDBCNotAvailableSlotDAO(connection);
			NotAvailableSlot notAvailableSlot = notAvailableSlotDAO
					.getApmtDetailsForLog(Integer.parseInt(appointmentid));
			UserProfileDAO userProfileDAO = new JDBCUserProfileDAO(connection);
			ClinicDAO clinicDAO = new JDBCClinicDAO(connection);
			boolean isSMSActive = clinicDAO.isSmsActive(loginInfo.getId());
			Client client = clientDAO.getClientDetails(notAvailableSlot.getClientId());
			UserProfile userProfile = userProfileDAO.getUserprofileDetails(notAvailableSlot.getDiaryUserId());

			String message = AllTemplateAction.getAppointmentSMSTextNew(notAvailableSlot.getClientId(),
					Integer.parseInt(appointmentid), connection, loginInfo, seqNO);

			String messageapmuser = AllTemplateAction.getAppointmentSMSTextToapmUser(notAvailableSlot.getClientId(),
					Integer.parseInt(appointmentid), connection, loginInfo, seqNO);

			if (status.equals("Modified")) {
				boolean rsult = false;
				if (!starttime.equals(notAvailableSlot.getSTime())) {
					rsult = true;
				}

				if (!apmtdate.equals(notAvailableSlot.getCommencing())) {
					rsult = true;
				}

				if (rsult) {
					message = AllTemplateAction.getAppointmentSMSTextNew(notAvailableSlot.getClientId(),
							Integer.parseInt(appointmentid), connection, loginInfo, seqNO);
					if (loginInfo.getCountry().equals("India")) {
						SmsService s = new SmsService();
						String templateid = userProfileDAO.getSMSTemplateID("Tokan SMS");
						s.sendSms(message, client.getMobNo(), loginInfo, new EmailLetterLog(),templateid);
						if (isSMSActive) {
							templateid = userProfileDAO.getSMSTemplateID("Appointment SMS DR");
							s.sendSms(messageapmuser, userProfile.getMobile(), loginInfo, new EmailLetterLog(),templateid);
						}
					}
				}

			} else {
				if (loginInfo.getCountry().equals("India")) {
					SmsService s = new SmsService();
					String templateid = userProfileDAO.getSMSTemplateID("Tokan SMS");
					s.sendSms(message, client.getMobNo(), loginInfo, new EmailLetterLog(),templateid);
					// s.sendSms(message, client.getMobNo(), loginInfo, new
					// EmailLetterLog());
					if (isSMSActive) {
						templateid = userProfileDAO.getSMSTemplateID("Appointment SMS DR");
						s.sendSms(messageapmuser, userProfile.getMobile(), loginInfo, new EmailLetterLog(),templateid);
					}
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {

			connection.close();
		}

	}

	public void autosmsCheckSms(String appointmentid, String status, String starttime, String apmtdate, int seqNO)
			throws Exception {

		// String appointmentid = request.getParameter("appointmentid");

		Connection connection = null;

		try {
			connection = Connection_provider.getconnection();
			// send sms
			ClientDAO clientDAO = new JDBCClientDAO(connection);
			NotAvailableSlotDAO notAvailableSlotDAO = new JDBCNotAvailableSlotDAO(connection);
			NotAvailableSlot notAvailableSlot = notAvailableSlotDAO
					.getApmtDetailsForLog(Integer.parseInt(appointmentid));
			UserProfileDAO userProfileDAO = new JDBCUserProfileDAO(connection);
			ClinicDAO clinicDAO = new JDBCClinicDAO(connection);
			boolean isSMSActive = clinicDAO.isSmsActive(loginInfo.getId());
			Client client = clientDAO.getClientDetails(notAvailableSlot.getClientId());
			UserProfile userProfile = userProfileDAO.getUserprofileDetails(notAvailableSlot.getDiaryUserId());

			String message = AllTemplateAction.getAppointmentSMSTextNew(notAvailableSlot.getClientId(),
					Integer.parseInt(appointmentid), connection, loginInfo, seqNO);

			String messageapmuser = AllTemplateAction.getAppointmentSMSTextToapmUser(notAvailableSlot.getClientId(),
					Integer.parseInt(appointmentid), connection, loginInfo, seqNO);

			EmrDAO emrDAO = new JDBCEmrDAO(connection);
			Clinic clinic = emrDAO.getsmsCheckConditionList(loginInfo.getClinicid());

			if (status.equals("Modified")) {
				boolean rsult = false;
				if (!starttime.equals(notAvailableSlot.getSTime())) {
					rsult = true;
				}

				if (!apmtdate.equals(notAvailableSlot.getCommencing())) {
					rsult = true;
				}

				if (rsult) {
					message = AllTemplateAction.getAppointmentSMSTextNew(notAvailableSlot.getClientId(),
							Integer.parseInt(appointmentid), connection, loginInfo, seqNO);
					if (loginInfo.getCountry().equals("India")) {
						SmsService s = new SmsService();
						if (clinic.isSmscheck()) {
							String templateid = userProfileDAO.getSMSTemplateID("Tokan SMS");
							s.sendSms(message, client.getMobNo(), loginInfo, new EmailLetterLog(),templateid);
						}

						if (isSMSActive) {
							if (clinic.isSmscheckdoctor()) {
								String templateid = userProfileDAO.getSMSTemplateID("Appointment SMS DR");
								s.sendSms(messageapmuser, userProfile.getMobile(), loginInfo, new EmailLetterLog(),templateid);
							}

						}
					}
				}

			} else {
				if (loginInfo.getCountry().equals("India")) {
					SmsService s = new SmsService();
					if (clinic.isSmscheck()) {
						String templateid = userProfileDAO.getSMSTemplateID("Tokan SMS");
						s.sendSms(message, client.getMobNo(), loginInfo, new EmailLetterLog(),templateid);
						// s.sendSms(message, client.getMobNo(), loginInfo, new
						// EmailLetterLog());
					}
					if (isSMSActive) {
						if (clinic.isSmscheckdoctor()) {
							String templateid = userProfileDAO.getSMSTemplateID("Appointment SMS DR");
							s.sendSms(messageapmuser, userProfile.getMobile(), loginInfo, new EmailLetterLog(),templateid);
						}
					}
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {

			connection.close();
		}

	}

	public String saveBlock() throws SQLException {
		if (!verifyLogin(request)) {
			return "login";
		}
		Connection connection = null;
		int result = 0;

		String slotId = request.getParameter("slotId");
		String commencing = request.getParameter("commencing");
		commencing = DateTimeUtils.getCommencingDate(commencing);

		String location = request.getParameter("location");
		String room = request.getParameter("room");
		String sTime = request.getParameter("sTime");
		String endTime = request.getParameter("endTime");
		String apmtDuration = request.getParameter("apmtDuration");
		String diaryuserId = request.getParameter("diaryuserId");

		String reasonforblock = request.getParameter("reasonforblock");
		String notes = request.getParameter("notes");
		String diaryUser = request.getParameter("diaryUser");
		String status = request.getParameter("status");

		int selectedid = Integer.parseInt(request.getParameter("selectedid"));
		int slotId1 = 0;
		int diaryuserId1 = 0;

		if (selectedid == 0) {
			slotId1 = Integer.parseInt(slotId);
			diaryuserId1 = Integer.parseInt(diaryuserId);
		}

		String opendb = (String) session.getAttribute("openedb");

		try {
			connection = Connection_provider.getconnection();
			UserProfileDAO profileDAO = new JDBCUserProfileDAO(connection);
			NotAvailableSlot notAvailableSlot = new NotAvailableSlot();
			notAvailableSlot.setApmtSlotId(slotId1);
			notAvailableSlot.setDiaryUserId(diaryuserId1);
			notAvailableSlot.setDiaryUser(profileDAO.getFullName(diaryuserId));

			notAvailableSlot.setLocation("1");
			notAvailableSlot.setRoom(room);
			notAvailableSlot.setCommencing(commencing);
			notAvailableSlot.setSTime(sTime);
			notAvailableSlot.setEndTime(endTime);
			notAvailableSlot.setApmtDuration(apmtDuration);

			notAvailableSlot.setReasonforblock(reasonforblock);
			notAvailableSlot.setNotes(notes);
			notAvailableSlot.setStatus(status);

			NotAvailableSlotDAO notAvailableSlotDAO = new JDBCNotAvailableSlotDAO(connection);
			if (selectedid == 0) {
				result = notAvailableSlotDAO.saveBlockSlot(notAvailableSlot, opendb);
			} else {
				int update = notAvailableSlotDAO.updateBlockSlot(notAvailableSlot, selectedid, opendb);

			}

			boolean wholeweek = Boolean.parseBoolean(request.getParameter("wholeweek"));
			int weekNumber = Integer.parseInt(request.getParameter("weekNumber"));

			// repeat code

			if (weekNumber > 0) {
				boolean monday = Boolean.parseBoolean(request.getParameter("monday"));
				boolean tuesday = Boolean.parseBoolean(request.getParameter("tuesday"));
				boolean wednesday = Boolean.parseBoolean(request.getParameter("wednesday"));
				boolean thursday = Boolean.parseBoolean(request.getParameter("thursday"));
				boolean friday = Boolean.parseBoolean(request.getParameter("friday"));
				boolean saturday = Boolean.parseBoolean(request.getParameter("saturday"));
				boolean sunday = Boolean.parseBoolean(request.getParameter("sunday"));

				weekNumber = Integer.parseInt(request.getParameter("weekNumber"));

				String dt = notAvailableSlot.getCommencing();
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				Calendar c = Calendar.getInstance();
				c.setTime(sdf.parse(dt));

				notAvailableSlot.setDiaryUserId(Integer.parseInt(diaryuserId));

				for (int i = 0; i < weekNumber; i++) {

					for (int j = 0; j <= 6; j++) {

						c.add(Calendar.DATE, 1); // number of days to add
						dt = sdf.format(c.getTime()); // dt is now the new date

						notAvailableSlot.setCommencing(dt);

						String wcdate[] = dt.split("-");

						// set weekname
						int wyear = Integer.parseInt(wcdate[0]);
						int month = Integer.parseInt(wcdate[1]);
						int day = Integer.parseInt(wcdate[2]);

						String cweekName = DateTimeUtils.getWeekName(wyear, month, day);

						// System.out.println(dt);
						// System.out.println(cweekName);

						if (monday && cweekName.equals(Constants.MONDAY)) {

							// common for all
							int apmtSlotid = notAvailableSlotDAO.getAppointmentSlotID(notAvailableSlot.getCommencing(),
									notAvailableSlot.getDiaryUserId(), notAvailableSlot.getSTime(),
									notAvailableSlot.getEndTime(), notAvailableSlot.getLocation());
							if (apmtSlotid != 0) {
								notAvailableSlot.setApmtSlotId(apmtSlotid);
								boolean checkEventExist = notAvailableSlotDAO.checkEventAllreadyExist(
										notAvailableSlot.getCommencing(), location, diaryuserId,
										notAvailableSlot.getSTime(), notAvailableSlot.getEndTime());
								if (checkEventExist) {
									checkEventExist = cheForWeekRepeatAfterEventExist(notAvailableSlot.getCommencing(),
											location, diaryuserId, notAvailableSlot.getSTime(),
											notAvailableSlot.getEndTime());
								}
								if (!checkEventExist) {
									result = notAvailableSlotDAO.saveBlockSlot(notAvailableSlot, opendb);
								} else {
									break;
								}

							} else {
								// break;
							}

						} else if (tuesday && cweekName.equals(Constants.TUESDAY)) {
							// common for all
							int apmtSlotid = notAvailableSlotDAO.getAppointmentSlotID(notAvailableSlot.getCommencing(),
									notAvailableSlot.getDiaryUserId(), notAvailableSlot.getSTime(),
									notAvailableSlot.getEndTime(), notAvailableSlot.getLocation());
							if (apmtSlotid != 0) {
								notAvailableSlot.setApmtSlotId(apmtSlotid);
								boolean checkEventExist = notAvailableSlotDAO.checkEventAllreadyExist(
										notAvailableSlot.getCommencing(), location, diaryuserId,
										notAvailableSlot.getSTime(), notAvailableSlot.getEndTime());
								if (checkEventExist) {
									checkEventExist = cheForWeekRepeatAfterEventExist(notAvailableSlot.getCommencing(),
											location, diaryuserId, notAvailableSlot.getSTime(),
											notAvailableSlot.getEndTime());
								}
								if (!checkEventExist) {
									result = notAvailableSlotDAO.saveBlockSlot(notAvailableSlot, opendb);
								} else {
									break;
								}

							} else {
								// break;
							}
						} else if (wednesday && cweekName.equals(Constants.WEDNEDAY)) {
							// common for all
							int apmtSlotid = notAvailableSlotDAO.getAppointmentSlotID(notAvailableSlot.getCommencing(),
									notAvailableSlot.getDiaryUserId(), notAvailableSlot.getSTime(),
									notAvailableSlot.getEndTime(), notAvailableSlot.getLocation());
							if (apmtSlotid != 0) {
								notAvailableSlot.setApmtSlotId(apmtSlotid);
								boolean checkEventExist = notAvailableSlotDAO.checkEventAllreadyExist(
										notAvailableSlot.getCommencing(), location, diaryuserId,
										notAvailableSlot.getSTime(), notAvailableSlot.getEndTime());
								if (checkEventExist) {
									checkEventExist = cheForWeekRepeatAfterEventExist(notAvailableSlot.getCommencing(),
											location, diaryuserId, notAvailableSlot.getSTime(),
											notAvailableSlot.getEndTime());
								}
								if (!checkEventExist) {
									result = notAvailableSlotDAO.saveBlockSlot(notAvailableSlot, opendb);
								} else {
									break;
								}

							} else {
								// break;
							}
						} else if (thursday && cweekName.equals(Constants.THUSRDAY)) {
							// common for all
							int apmtSlotid = notAvailableSlotDAO.getAppointmentSlotID(notAvailableSlot.getCommencing(),
									notAvailableSlot.getDiaryUserId(), notAvailableSlot.getSTime(),
									notAvailableSlot.getEndTime(), notAvailableSlot.getLocation());
							if (apmtSlotid != 0) {
								notAvailableSlot.setApmtSlotId(apmtSlotid);
								boolean checkEventExist = notAvailableSlotDAO.checkEventAllreadyExist(
										notAvailableSlot.getCommencing(), location, diaryuserId,
										notAvailableSlot.getSTime(), notAvailableSlot.getEndTime());
								if (checkEventExist) {
									checkEventExist = cheForWeekRepeatAfterEventExist(notAvailableSlot.getCommencing(),
											location, diaryuserId, notAvailableSlot.getSTime(),
											notAvailableSlot.getEndTime());
								}
								if (!checkEventExist) {
									result = notAvailableSlotDAO.saveBlockSlot(notAvailableSlot, opendb);
								} else {
									break;
								}

							} else {
								// break;
							}
						} else if (friday && cweekName.equals(Constants.FRIDAY)) {
							// common for all
							int apmtSlotid = notAvailableSlotDAO.getAppointmentSlotID(notAvailableSlot.getCommencing(),
									notAvailableSlot.getDiaryUserId(), notAvailableSlot.getSTime(),
									notAvailableSlot.getEndTime(), notAvailableSlot.getLocation());
							if (apmtSlotid != 0) {
								notAvailableSlot.setApmtSlotId(apmtSlotid);
								boolean checkEventExist = notAvailableSlotDAO.checkEventAllreadyExist(
										notAvailableSlot.getCommencing(), location, diaryuserId,
										notAvailableSlot.getSTime(), notAvailableSlot.getEndTime());
								if (checkEventExist) {
									checkEventExist = cheForWeekRepeatAfterEventExist(notAvailableSlot.getCommencing(),
											location, diaryuserId, notAvailableSlot.getSTime(),
											notAvailableSlot.getEndTime());
								}
								if (!checkEventExist) {
									result = notAvailableSlotDAO.saveBlockSlot(notAvailableSlot, opendb);
								} else {
									break;
								}

							} else {
								// break;
							}
						} else if (saturday && cweekName.equals(Constants.SATURDAY)) {
							// common for all
							int apmtSlotid = notAvailableSlotDAO.getAppointmentSlotID(notAvailableSlot.getCommencing(),
									notAvailableSlot.getDiaryUserId(), notAvailableSlot.getSTime(),
									notAvailableSlot.getEndTime(), notAvailableSlot.getLocation());
							if (apmtSlotid != 0) {
								notAvailableSlot.setApmtSlotId(apmtSlotid);
								boolean checkEventExist = notAvailableSlotDAO.checkEventAllreadyExist(
										notAvailableSlot.getCommencing(), location, diaryuserId,
										notAvailableSlot.getSTime(), notAvailableSlot.getEndTime());
								if (checkEventExist) {
									checkEventExist = cheForWeekRepeatAfterEventExist(notAvailableSlot.getCommencing(),
											location, diaryuserId, notAvailableSlot.getSTime(),
											notAvailableSlot.getEndTime());
								}
								if (!checkEventExist) {
									result = notAvailableSlotDAO.saveBlockSlot(notAvailableSlot, opendb);
								} else {
									break;
								}

							} else {
								// break;
							}
						} else if (sunday && cweekName.equals(Constants.SUNDAY)) {
							// common for all
							int apmtSlotid = notAvailableSlotDAO.getAppointmentSlotID(notAvailableSlot.getCommencing(),
									notAvailableSlot.getDiaryUserId(), notAvailableSlot.getSTime(),
									notAvailableSlot.getEndTime(), notAvailableSlot.getLocation());
							if (apmtSlotid != 0) {
								notAvailableSlot.setApmtSlotId(apmtSlotid);
								boolean checkEventExist = notAvailableSlotDAO.checkEventAllreadyExist(
										notAvailableSlot.getCommencing(), location, diaryuserId,
										notAvailableSlot.getSTime(), notAvailableSlot.getEndTime());
								if (checkEventExist) {
									checkEventExist = cheForWeekRepeatAfterEventExist(notAvailableSlot.getCommencing(),
											location, diaryuserId, notAvailableSlot.getSTime(),
											notAvailableSlot.getEndTime());
								}
								if (!checkEventExist) {
									result = notAvailableSlotDAO.saveBlockSlot(notAvailableSlot, opendb);
								} else {
									break;
								}

							} else {
								// break;
							}
						} else if (!monday && !tuesday && !wednesday && !thursday && !friday && !saturday && !sunday) {
							// common for all
							int apmtSlotid = notAvailableSlotDAO.getAppointmentSlotID(notAvailableSlot.getCommencing(),
									notAvailableSlot.getDiaryUserId(), notAvailableSlot.getSTime(),
									notAvailableSlot.getEndTime(), notAvailableSlot.getLocation());
							if (apmtSlotid != 0) {
								notAvailableSlot.setApmtSlotId(apmtSlotid);
								boolean checkEventExist = notAvailableSlotDAO.checkEventAllreadyExist(
										notAvailableSlot.getCommencing(), location, diaryuserId,
										notAvailableSlot.getSTime(), notAvailableSlot.getEndTime());
								if (!checkEventExist) {
									result = notAvailableSlotDAO.saveBlockSlot(notAvailableSlot, opendb);

								} else {
									break;
								}

							} else {
								// break;
							}
						}

					}
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			connection.close();
		}
		return null;
	}

	public boolean cheForWeekRepeatAfterEventExist(String commencing, String location, String diaryuserId,
			String starttime, String endtime) throws Exception {
		boolean checkEventExist = true;
		Connection connection = null;
		try {
			connection = Connection_provider.getconnection();
			NotAvailableSlotDAO notAvailableSlotDAO = new JDBCNotAvailableSlotDAO(connection);
			int coutnEsistingSlot = notAvailableSlotDAO.coutnEsistingSlot(commencing, location, diaryuserId, starttime,
					endtime, 0);

			if (coutnEsistingSlot == 1) {
				String existStartTime = notAvailableSlotDAO.getExistStartTime(commencing, location, diaryuserId,
						starttime, endtime, 0);

				String duration = DateTimeUtils.getDuration(starttime, endtime);

				// System.out.println(duration);

				String sumoftime = DateTimeUtils.getSumofTime(starttime, duration);

				if (sumoftime.equals(existStartTime)) {
					checkEventExist = false;
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {

			connection.close();
		}
		return checkEventExist;

	}

	public void prepare() throws Exception {

		Connection connection = null;
		SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");  
	    Date reuestdate = new Date();
	    req_datetime=formatter.format(reuestdate);
		ArrayList<Client> thirdPartyTypeList = new ArrayList<Client>();
		ArrayList<Client> thirdPartyTypeNameList = new ArrayList<Client>();
		ArrayList<Client> gpList = new ArrayList<Client>();
		ArrayList<Client> clientOccupationList = new ArrayList<Client>();
		ArrayList<Client> refrenceList = new ArrayList<Client>();
		ArrayList<String> initialList = new ArrayList<String>();
		ArrayList<Client> sourceOfIntroList = new ArrayList<Client>();
		ArrayList<Client> condtitionList = new ArrayList<Client>();
		ArrayList<Client> diagnosisList = new ArrayList<Client>();
		ArrayList<Client> surgeryList = new ArrayList<Client>();
		ArrayList<Master> multiotImgList = new ArrayList<Master>();

		ArrayList<Bed> wardlist = new ArrayList<Bed>();

		try {
			session.setAttribute("multiotImgList", multiotImgList);
			connection = Connection_provider.getconnection();
			
			ClientDAO clientDAO = new JDBCClientDAO(connection);
			AppointmentDAO appointmentDAO = new JDBCAppointmentDAO(connection);
			AccountsDAO accountsDAO = new JDBCAccountsDAO(connection);
			UserProfileDAO userProfileDAO = new JDBCUserProfileDAO(connection);
			PharmacyDAO pharmacyDAO = new JDBCPharmacyDAO(connection);
			gpList = clientDAO.getGpList();
			notAvailableSlotForm.setGpList(gpList);

			condtitionList = clientDAO.getTreatmentTypeList();
			notAvailableSlotForm.setCondtitionList(condtitionList);

			// diagnosisList=clientDAO.getEmrTreatmentTypeList();
			notAvailableSlotForm.setDiagnosisList(diagnosisList);
			thirdPartyTypeList = clientDAO.getThirdPartyType();
			notAvailableSlotForm.setThirdPartyTypeList(thirdPartyTypeList);

			String date = DateTimeUtils.getDashboardTodayDate(loginInfo.getTimeZone());
			notAvailableSlotForm.setPriscdate(date);
			notAvailableSlotForm.setPriscdateandtime(DateTimeUtils.getPriscDatetime(loginInfo.getTimeZone()));
			notAvailableSlotForm.setHourList(PopulateList.hourList());
			notAvailableSlotForm.setMinuteList(PopulateList.getMinuteList());

			SMSTemplateDAO smsTemplateDAO = new JDBCSMSTemplateDao(connection);
			ArrayList<EmailTemplate> smsTemplateList = smsTemplateDAO.getAllSMSTemplates();
			notAvailableSlotForm.setSmsTemplateList(smsTemplateList);

			thirdPartyTypeNameList = clientDAO.getThirdPartyTypeName();
			notAvailableSlotForm.setThirdPartyTypeNameList(thirdPartyTypeNameList);

			clientOccupationList = clientDAO.getOccupationList();
			Client client1 = new Client();
			client1.getOther();
			clientOccupationList.add(client1);
			notAvailableSlotForm.setClientOccupationList(clientOccupationList);
			if (loginInfo.getUserType() == 5) {
				notAvailableSlotForm.setDuration("1");
			}
			surgeryList = clientDAO.getSurgeryList();
			Client client3 = new Client();
			client3.getOther();
			surgeryList.add(client3);
			notAvailableSlotForm.setSurgeryList(surgeryList);

			refrenceList = clientDAO.getReferenceList();
			Client client2 = new Client();
			client2.getOther();
			refrenceList.add(client2);
			notAvailableSlotForm.setRefrenceList(refrenceList);

			initialList = clientDAO.getInitialList();
			notAvailableSlotForm.setInitialList(initialList);

			sourceOfIntroList = clientDAO.getSourceOfIntroList();
			notAvailableSlotForm.setSourceOfIntroList(sourceOfIntroList);

			NotAvailableSlotDAO notAvailableSlotDAO = new JDBCNotAvailableSlotDAO(connection);

			ArrayList<Location> locationList = notAvailableSlotDAO.getLocationList(loginInfo.getId());
			notAvailableSlotForm.setLocationList(locationList);

			ArrayList<AppointmentType> appointmentTypeList = new ArrayList<AppointmentType>();
			if (loginInfo.getUserType() == 5) {
				appointmentTypeList = notAvailableSlotDAO.getAppointmentTypeListForOPD();
			}
			notAvailableSlotForm.setAppointmentTypeList(appointmentTypeList);

			TreatmentEpisodeDAO treatmentEpisodeDAO = new JDBCTreatmentEpisode(connection);
			ArrayList<TreatmentEpisode> sourceOfReferralList = treatmentEpisodeDAO.getSourceOfReferralList();
			notAvailableSlotForm.setSourceOfReferralList(sourceOfReferralList);

			notAvailableSlotForm.setCountry("United Kingdom");

			ArrayList<String> weekNameList = new ArrayList<String>();
			weekNameList.add("Monday");
			weekNameList.add("Tuesday");
			weekNameList.add("Wednesday");
			weekNameList.add("Thursday");
			weekNameList.add("Friday");
			weekNameList.add("Saturday");
			weekNameList.add("Sunday");

			notAvailableSlotForm.setWeekNameList(weekNameList);
			ThirdPartyDAO thirdPartyDAO = new JDBCThirdPartyDAO(connection);
			ArrayList<ThirdParty> tpnameList = new ArrayList<ThirdParty>();
			tpnameList = thirdPartyDAO.getTPNameList();
			notAvailableSlotForm.setTpnameList(tpnameList);

			MasterDAO masterDAO = new JDBCMasterDAO(connection);
			ArrayList<Master> disciplineList = masterDAO.getDisciplineDataList();
			notAvailableSlotForm.setDisciplineList(disciplineList);

			EmrDAO emrDAO = new JDBCEmrDAO(connection);
			ArrayList<Discharge> dischargeOutcomeList = emrDAO.getDischrageOutcomeList();
			notAvailableSlotForm.setDischargeOutcomeList(dischargeOutcomeList);

			ArrayList<Discharge> dischargeStatusList = emrDAO.getDischrageStatusList();
			notAvailableSlotForm.setDischargeStatusList(dischargeStatusList);

			ArrayList<Master> mdicinecategoryList = emrDAO.getmedicineCategoryList();
			notAvailableSlotForm.setMdicinecategoryList(mdicinecategoryList);

			ArrayList<Master> dosageList = emrDAO.getDosageList();
			notAvailableSlotForm.setDosageList(dosageList);

			ArrayList<Master> mdicneTypeList = emrDAO.getMedicineTypeList();
			notAvailableSlotForm.setMdicneTypeList(mdicneTypeList);

			TreatmentTypeDAO treatmentTypeDAO = new JDBCTreatmentTypeDAO(connection);
			// ArrayList<TreatmentType> treatmentTypeList =
			// treatmentTypeDAO.getConditionList();
			ArrayList<TreatmentType> treatmentTypeList = new ArrayList<TreatmentType>();
			notAvailableSlotForm.setTreatmentTypeList(treatmentTypeList);

			// ArrayList<Master>medicineList = masterDAO.getMedicineList();
			ArrayList<Master> medicineList = new ArrayList<Master>();
			notAvailableSlotForm.setMedicineList(medicineList);

			// followup appointment list
			DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			Calendar cal = Calendar.getInstance();
			cal.add(Calendar.DATE, 1);
			String followupdate = dateFormat.format(cal.getTime());

			ArrayList<Priscription> followupApmtList = notAvailableSlotDAO.getFollowupApmtList(followupdate);
			notAvailableSlotForm.setFollowupApmtList(followupApmtList);
			notAvailableSlotForm.setFollowupapmtsize(followupApmtList.size());

			ArrayList<Master> masterChageTypeList = appointmentDAO.getmasterChageTypeList(loginInfo);
			notAvailableSlotForm.setMasterChageTypeList(masterChageTypeList);
			notAvailableSlotForm.setMasterchargetype("Appointment Charge");

			// ArrayList<UserProfile>staffList =
			// notAvailableSlotDAO.getOTstaffList();
			//   25 dec 2017 set ot surgeon list from referer table instead
			// of apm_user
			ArrayList<UserProfile> surgeonlist = userProfileDAO.getAllPractitionerList("1", null, null, null, null);
			notAvailableSlotForm.setSurgeonlist(surgeonlist);
			notAvailableSlotForm.setOtsurgeonname(Integer.toString(loginInfo.getId()));

			ArrayList<UserProfile> staffList = notAvailableSlotDAO.getOTstaffList();
			notAvailableSlotForm.setStaffList(staffList);
			/*
			 * ArrayList<Client> anesthesiaList = clientDAO.getAnesthesiaList();
			 * notAvailableSlotForm.setAnesthesiaList(anesthesiaList);
			 */

			ArrayList<Client> anesthesiaList = userProfileDAO.getAllAnethesiaList();
			notAvailableSlotForm.setAnesthesiaList(anesthesiaList);

			notAvailableSlotForm.setCountryList(PopulateList.countryList());
			notAvailableSlotForm.setCountry("United Kingdom");
			if (loginInfo.getCountry().equals("India")) {
				notAvailableSlotForm.setCountry("India");
			}

			ArrayList<Priscription> parentPriscList = new ArrayList<Priscription>();
			notAvailableSlotForm.setParentPriscList(parentPriscList);

			ArrayList<Master> dosagenoteList = masterDAO.getDosageNoteList();
			notAvailableSlotForm.setDosagenoteList(dosagenoteList);

			// investigation

			InvestigationDAO investigationDAO = new JDBCInvestigationDAO(connection);

			ArrayList<Master> invsTypeList = emrDAO.getInvesigationTypeList();
			notAvailableSlotForm.setInvsTypeList(invsTypeList);

			ArrayList<Master> invstReportTypeList = emrDAO.getInvstReportTypeList();
			notAvailableSlotForm.setInvstReportTypeList(invstReportTypeList);

			ArrayList<Master> invstUnitList = emrDAO.getInvstUnitList();
			notAvailableSlotForm.setInvstUnitList(invstUnitList);

			ArrayList<Master> cbcIdList = investigationDAO.getCbcIdList();
			notAvailableSlotForm.setCbcIdList(cbcIdList);

			ArrayList<String> jobTitleList = userProfileDAO.getJobTitleList();
			notAvailableSlotForm.setJobTitleList(jobTitleList);
			UserProfile userProfile = userProfileDAO.getUserprofileDetails(loginInfo.getId());
			notAvailableSlotForm.setJobtitle("Pathlab");

			ArrayList<Master> jobGroupList = userProfileDAO.getJobGroupList("0");
			notAvailableSlotForm.setJobGroupList(jobGroupList);

			ArrayList<Master> invoiceTypeList = accountsDAO.getInvoiceTypeList();
			notAvailableSlotForm.setInvoiceTypeLis(invoiceTypeList);
			notAvailableSlotForm.setInvcetype("1");

			ArrayList<Priscription> templateNameList = emrDAO.getTemplateNameList(loginInfo);
			notAvailableSlotForm.setTemplateNameList(templateNameList);

			ArrayList<Master> procedureList = notAvailableSlotDAO.getProcedureList(userProfile.getDiciplineName());
			notAvailableSlotForm.setProcedureList(procedureList);

			ArrayList<Master> otherTemplateList = masterDAO.getEmrTemplateList(userProfile.getDiciplineName());
			notAvailableSlotForm.setOtherTemplateList(otherTemplateList);

			// set ot asset list
			ArrayList<Master> assetList = emrDAO.getOtAssetList();
			notAvailableSlotForm.setAssetList(assetList);

			ArrayList<Priscription> oteqtemplateNameList = emrDAO.getoteqTemplateNameList();
			notAvailableSlotForm.setOteqtemplateNameList(oteqtemplateNameList);

			// investigation section master
			ArrayList<Master> invSectionList = investigationDAO.getInvestigationSectionList();
			notAvailableSlotForm.setInvSectionList(invSectionList);

			// set editor image list
			ImportImageForAssessmentDAO imageForAssessmentDAO = new JDBCImportImageAssessmentDAO(connection);
			ArrayList<Assessment> importImageList = imageForAssessmentDAO.getImportImageList();
			notAvailableSlotForm.setImportImageList(importImageList);

			// ArrayList<AppointmentType>additionalChargeList =
			// appointmentDAO.getAdditionalChaergeTypeList("");
			ArrayList<AppointmentType> additionalChargeList = new ArrayList<AppointmentType>();
			notAvailableSlotForm.setAdditionalChargeList(additionalChargeList);

			// investigation pkg list
			ArrayList<Master> pkgsList = investigationDAO.getInvPaksLists();
			notAvailableSlotForm.setPkgsList(pkgsList);

			// //set state and city list
			InventoryVendorDAO vendorDAO = new JDBCInventoryVendorDAO(connection);
			ArrayList<Master> stateList = vendorDAO.getAllStateList();
			notAvailableSlotForm.setStatelist(stateList);
			ArrayList<Master> cityList = vendorDAO.getAllCityList();
			notAvailableSlotForm.setCitylist(cityList);

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

			//  
			ArrayList<DiaryManagement> doctorList = notAvailableSlotDAO.getUserList(loginInfo.getId());
			notAvailableSlotForm.setDoctorList(doctorList);

			// jitu
			ArrayList<Master> specializationTemplateList = masterDAO.getMasterSpecializationList();
			notAvailableSlotForm.setSpecializationTemplateList(specializationTemplateList);
			ArrayList<Master> priscUnitList = masterDAO.getPriscUnitList();
			notAvailableSlotForm.setPriscUnitList(priscUnitList);

			// set template name list

			notAvailableSlotForm.setVisitingtimeList(PopulateList.startTimeList());

			// department ot list
			ArrayList<Master> otdepartmentList = notAvailableSlotDAO.getotDepartmenrList();
			notAvailableSlotForm.setOtdepartmentList(otdepartmentList);
			notAvailableSlotForm.setOtdepartment(userProfile.getDiciplineName());

			ArrayList<Master> schedulerlist = masterDAO.getAllSchedulerTask(null, null);
			notAvailableSlotForm.setSchedulerlist(schedulerlist);
			ArrayList<Master> schedulersubtasklist = masterDAO.getAllSchedulerSubtask(null, null);
			notAvailableSlotForm.setSchedulersubtasklist(schedulersubtasklist);

			IpdDAO ipdDAO = new JDBCIpdDAO(connection);

			// vital Master List
			ArrayList<Master> vitalMasterList = ipdDAO.getVitalMasterByCategory("1");
			notAvailableSlotForm.setVitalMasterList(vitalMasterList);

			// vital intake/ output
			ArrayList<Master> vitalMasterIOList = ipdDAO.getVitalMasterByCategory("2");
			notAvailableSlotForm.setVitalMasterIOList(vitalMasterIOList);

			// vital IV list
			ArrayList<Master> vitalMasterIVList = ipdDAO.getVitalMasterByCategory("3");
			notAvailableSlotForm.setVitalMasterIVList(vitalMasterIVList);

			// vital Equipment List
			ArrayList<Master> vitalMasterEquipmentList = ipdDAO.getVitalMasterByCategory("4");
			notAvailableSlotForm.setVitalMasterEquipmentList(vitalMasterEquipmentList);

			ArrayList<Master> bankNameList = masterDAO.getBankNameList();
			notAvailableSlotForm.setBankNameList(bankNameList);

			//   11 April 2018
			ArrayList<Priscription> medicinetimelist = emrDAO.getMedicineTimeList();
			notAvailableSlotForm.setMedicinetimelist(medicinetimelist);
			/*
			 * InventoryProductDAO inventoryProductDAO = new
			 * JDBCInventoryProductDAO(connection); ArrayList<Master>
			 * warehouseList= inventoryProductDAO.getWareHouseList();
			 * notAvailableSlotForm.setWarehouseList(warehouseList);
			 */
			notAvailableSlotForm.setWardlist(wardlist);

			ArrayList<Master> nimaidoselist = masterDAO.getnimaidoselistt();
			ArrayList<Master> nimaiqtylist = masterDAO.getnimaiqtylist();
			ArrayList<Master> nimairemarklist = masterDAO.getnimairemarlist();

			notAvailableSlotForm.setNimaidoselist(nimaidoselist);
			notAvailableSlotForm.setNimaiqtylist(nimaiqtylist);
			notAvailableSlotForm.setNimairemarklist(nimairemarklist);

			ArrayList<Master> requestlocationlist = pharmacyDAO.getAllLocationNew();
			notAvailableSlotForm.setRequestlocationlist(requestlocationlist);
			if (loginInfo.isPrisc_location_list()) {
				int default_location = pharmacyDAO.getByDefaultPharmacyLocation();
				notAvailableSlotForm.setRequestlocationid("" + default_location);
			}
			
			notAvailableSlotForm.setBirthPlaceList(masterDAO.getBirthPlaceList());
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			connection.close();
		}

		notAvailableSlotForm.setCountryList(PopulateList.countryList());

		notAvailableSlotForm.setStartTimeList(PopulateList.startTimeList());
		notAvailableSlotForm.setEndTimeList(PopulateList.endTimeList());
		notAvailableSlotForm.setApmtDurationList(PopulateList.apmtDurationList());
		notAvailableSlotForm.setApmtBlockDurationList(PopulateList.apmBlocktDurationList());

	}

	public String newAppoinment() {
		return "newAppoinment";
	}

	// email send code
	public String autoemail() throws SQLException {
		if (!verifyLogin(request)) {
			return "login";
		}

		Connection connection = null;

		try {
			int seqno = 1;
			connection = Connection_provider.getconnection();

			int appointmentid = Integer.parseInt(request.getParameter("appointmentid"));

			DiaryManagementDAO diaryManagementDAO = new JDBCDiaryManagentDAO(connection);

			ArrayList<NotAvailableSlot> appointmentList = diaryManagementDAO
					.getAppintmentSendMailDetails(appointmentid);

			DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Calendar cal = Calendar.getInstance();
			// cal.add(Calendar.DATE, -1);

			String date = dateFormat.format(cal.getTime());

			AllTemplateAction allTemplateAction = new AllTemplateAction();

			for (NotAvailableSlot notAvailableSlot : appointmentList) {
				UserProfile userProfile = notAvailableSlot.getUserDEtails();
				Client client = notAvailableSlot.getClientDetails();
				Location location = notAvailableSlot.getLocationDetails();
				log.debug("********************hello world3");

				allTemplateAction.emailSend(userProfile.getFullname(), client.getFirstName(),
						notAvailableSlot.getSTime(), notAvailableSlot.getDuration(), userProfile.getEmail(),
						client.getEmail(), notAvailableSlot.getCommencing(), location.getLocationname(),
						location.getContactNo(), location.getAddress(), location.getLocationid(),
						notAvailableSlot.getClientId(), userProfile.getQualification(), appointmentid,
						userProfile.getDiciplineName(), connection, loginInfo);

				// set email to admin
				ClinicDAO clinicDAO = new JDBCClinicDAO(connection);
				Clinic clinic = clinicDAO.getCliniclistDetails(loginInfo.getClinicid());
				allTemplateAction.emailSend(userProfile.getFullname(), client.getFirstName(),
						notAvailableSlot.getSTime(), notAvailableSlot.getDuration(), userProfile.getEmail(),
						clinic.getEmail(), notAvailableSlot.getCommencing(), location.getLocationname(),
						location.getContactNo(), location.getAddress(), location.getLocationid(),
						notAvailableSlot.getClientId(), userProfile.getQualification(), appointmentid,
						userProfile.getDiciplineName(), connection, loginInfo);

				// send sms
				ClientDAO clientDAO = new JDBCClientDAO(connection);
				boolean isSMSActive = clinicDAO.isSmsActive(loginInfo.getId());
				NotAvailableSlotDAO notAvailableSlotDAO = new JDBCNotAvailableSlotDAO(connection);
				NotAvailableSlot notAvailableSlot1 = notAvailableSlotDAO.getApmtDetailsForLog(appointmentid);
				UserProfileDAO userProfileDAO = new JDBCUserProfileDAO(connection);
				Client clients = clientDAO.getClientDetails(notAvailableSlot1.getClientId());
				UserProfile userProfiles = userProfileDAO.getUserprofileDetails(notAvailableSlot.getDiaryUserId());
				String cdate = DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone()).split(" ")[0];
				boolean checkifseq = notAvailableSlotDAO.checkifSequenceExist(cdate,
						notAvailableSlot1.getDiaryUserId());
				if (checkifseq) {
					seqno = notAvailableSlotDAO.getSqeunceNumber(cdate, notAvailableSlot.getDiaryUserId());
					seqno++;
				} else {
					seqno = 1;
				}
				String message = AllTemplateAction.getAppointmentSMSText(notAvailableSlot1.getClientId(), appointmentid,
						connection, loginInfo);
				String message1 = AllTemplateAction.getAppointmentSMSTextToapmUser(notAvailableSlot.getClientId(),
						appointmentid, connection, loginInfo, seqno);

				if (loginInfo.getCountry().equals("India")) {
					SmsService s = new SmsService();
					String templateid = userProfileDAO.getSMSTemplateID("Appointment SMS");
					s.sendSms(message, clients.getMobNo(), loginInfo, new EmailLetterLog(),templateid);
					if (isSMSActive) {
						templateid = userProfileDAO.getSMSTemplateID("Appointment SMS DR");
						s.sendSms(message1, userProfiles.getMobile(), loginInfo, new EmailLetterLog(),templateid);
					}
				}

			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			connection.close();
		}

		return null;
	}

	public void emailSend(String practitionerName, String clientName, String startTime, String duration,
			String practitionerEmailId, String clientEmailId, String date, String location, String contactNo,
			String address, String locationID, String clientId, String userQualification, int appointmentid,
			String discipline) throws SQLException {

		Connection connection = null;

		try {
			log.debug("********************hello world5");

			connection = Connection_provider.getconnection();

			int activity = 1;

			ClinicDAO clinicDAO = new JDBCClinicDAO(connection);

			Clinic locationdirection = new Clinic();
			locationdirection = clinicDAO.getLocationDetails(Integer.parseInt(locationID));
			Clinic clinic = new Clinic();
			int clinicID = loginInfo.getId();
			clinic = clinicDAO.getCliniclistDetails(loginInfo.getClinicid());
			String qualification = clinic.getOwner_qualification();

			String subject = "Appointment Details";

			StringBuffer str = new StringBuffer();

			/*
			 * String filePath = (String)session.getAttribute("logopath"); File
			 * file = new File(filePath);
			 * 
			 * String twitter = (String)session.getAttribute("twitter"); file =
			 * new File(twitter); String imgt =
			 * "<a href='www.twitter.com'><img src=\"cid:imaget\" /></a>";
			 * 
			 * String fb = (String)session.getAttribute("fb"); file = new
			 * File(fb); String imgf =
			 * "<a href='www.facebook.com'><img src=\"cid:imagef\" /></a>";
			 * 
			 * String gml = (String)session.getAttribute("gml"); file = new
			 * File(gml); String imgg =
			 * "<a href='accounts.google.com'><img src=\"cid:imageg\" /></a>";
			 */

			ClientDAO clientDAO = new JDBCClientDAO(connection);
			Client client2 = clientDAO.getClientDetails(clientId);
			String addr = "";

			if (!discipline.equals("")) {
				discipline = "Chartered" + " " + discipline;
				discipline = "(" + discipline + ")";
			}

			// Client Mail

			AllTemplateAction allTemplateAction = new AllTemplateAction();

			int locationId = Integer.parseInt(locationID);

			String headerData = allTemplateAction.header(clinic.getClinicName(), clinic.getClinicOwner(),
					clinic.getOwner_qualification(), locationdirection.getAddress(), clinic.getEmail(),
					locationdirection.getContactNo(), clinic.getWebsiteUrl(), loginInfo.getClinicid(), connection);

			DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
			Date date1 = new Date();
			String nowDate = dateFormat.format(date1);

			String body1Data = allTemplateAction.commonBody1(nowDate, clientName, address, addr, client2.getTown(),
					client2.getPostCode(), client2.getCounty());

			date = DateTimeUtils.changeDateFormatToddmmyyyy(date);
			date = DateTimeUtils.changeDateFormatTemplate(date);

			String onAppointBookingBody = allTemplateAction.OnAppointBookingBody(clinic.getClinicOwner(),
					clinic.getWebsiteUrl(), practitionerName, clientName, startTime, duration, practitionerEmailId,
					clientEmailId, date, locationId, connection);

			String body2Data = allTemplateAction.commonBody2(practitionerName, userQualification, discipline);

			String footerData = allTemplateAction.commonFooter(clinic.getClinicName());

			log.debug("********************hello world6");

			// String img = "<img src=\"cid:image1\" width=\"20%\"
			// height=\"10%\" />";
			// String data =
			// img+headerData+body1Data+onAppointBookingBody+body2Data+footerData+"
			// "+imgt+" "+imgf+" "+imgg;

			String data = headerData + body1Data + onAppointBookingBody + body2Data + footerData + " ";

			// System.out.println(data);

			// EmbeddedImageEmailUtil.sendMail(clientEmailId, "", subject,
			// str1.toString());

			/*
			 * String twitter =
			 * request.getRealPath("/img/Entypo_f309(0)_32.png/"); File file1 =
			 * new File(twitter); String imgt =
			 * "<a href='www.twitter.com'><img src=\"cid:imaget\" /></a>";
			 * String fb = request.getRealPath("/img/Entypo_f30c(0)_32.png/");
			 * file1 = new File(fb); String imgf =
			 * "<a href='www.facebook.com'><img src=\"cid:imagef\" /></a>";
			 * String gml = request.getRealPath("/img/Entypo_f30f(0)_32.png/");
			 * file1 = new File(gml); String imgg =
			 * "<a href='www.gmail.com'><img src=\"cid:imageg\" /></a>"; data =
			 * data+" "+imgt+" "+imgf+" "+imgg;
			 */

			int id = loginInfo.getId();
			/*
			 * if(file.exists()){
			 * 
			 * Map<String, String> inlineImages = new HashMap<String, String>();
			 * inlineImages.put("imaget", twitter); inlineImages.put("imagef",
			 * fb); inlineImages.put("imageg", gml);
			 * 
			 * // Map<String, String> inlineImages = new HashMap<String,
			 * String>(); inlineImages.put("image1", filePath);
			 * EmbeddedImageEmailUtil.send(connection,id,clientEmailId, "",
			 * subject, data, inlineImages,loginInfo);
			 * 
			 * } else{
			 * EmbeddedImageEmailUtil.sendMail(connection,id,clientEmailId, "",
			 * subject, data,loginInfo);
			 * 
			 * }
			 */
			// EmailLetterLogDAO emailLetterLogDAO = new
			// JDBCEmailLetterLogDAO(connection);
			// String from = clinic.getEmail();
			String type = "Email";
			int invoiceid = 0;
			// int result = emailLetterLogDAO.saveEmailLetterLogDetails(from,
			// clientEmailId, subject, type, invoiceid,appointmentid);
			EmailLetterLog emailLetterLog = new EmailLetterLog();
			emailLetterLog.setAppointmentid(appointmentid);
			emailLetterLog.setInvoiceid(invoiceid);
			emailLetterLog.setType(type);

			emailLetterLog.setClientId(clientId);

			// EmbeddedImageEmailUtil.sendMailForForgotPassword(clientEmailId,
			// "", subject, "hello world");

			EmbeddedImageEmailUtil.sendMail(connection, id, clientEmailId, "", subject, data, loginInfo,
					emailLetterLog);

			// Activity Set
			NotAvailableSlotDAO notAvailableSlotDAO = new JDBCNotAvailableSlotDAO(connection);
			String CheckMailToSend = clinicDAO.IsMailSend(loginInfo.getId());
			if (CheckMailToSend.equals("true")) {
				int result1 = notAvailableSlotDAO.saveActivity(Integer.parseInt(clientId), activity);
			}

			// mailTLS.sendMail(clientEmailId,subject, str1.toString());
		} catch (Exception e) {
			e.printStackTrace();
			log.debug("********************hello world9" + e.getMessage());
		} finally {

			connection.close();
		}

	}

	// 29th march unnati

	public String getPrintDataOfWeek() throws SQLException {
		if (!verifyLogin(request)) {
			return "login";
		}
		Connection connection = null;

		DateTimeUtils dateTimeUtils = new DateTimeUtils();
		String practionerId = request.getParameter("practitionerId");
		String fromDate = request.getParameter("date");
		String practioner = request.getParameter("practitioner");
		ApmDate d1 = dateTimeUtils.getApmDate(fromDate, 6);
		int toDay = d1.getDate();
		int toMonth = d1.getMonth();
		int toYear = d1.getYear();
		notAvailableSlotForm.setDate(fromDate);

		String temp[] = fromDate.split("/");
		fromDate = temp[2] + "-" + temp[1] + "-" + temp[0];
		String todd = "";
		String tomm = "";
		todd = Integer.toString(toDay);
		tomm = Integer.toString(toMonth);
		if (toDay <= 9) {

			todd = "0" + todd;
		}
		if (toMonth <= 9) {

			tomm = "0" + tomm;
		}
		String toDate = Integer.toString(toYear) + "-" + tomm + "-" + todd;
		String toDate1 = todd + "/" + tomm + "/" + Integer.toString(toYear);

		notAvailableSlotForm.setToDate(toDate1);
		try {
			connection = Connection_provider.getconnection();
			NotAvailableSlotDAO notAvailableSlotDAO = new JDBCNotAvailableSlotDAO(connection);

			ArrayList<NotAvailableSlot> practitionerApmtList = notAvailableSlotDAO.getPrintDataOfWeek(practionerId,
					fromDate, toDate);
			notAvailableSlotForm.setPractitionerApmtList(practitionerApmtList);
			notAvailableSlotForm.setPractitonerName(practioner);

			StringBuffer str = new StringBuffer();

			str.append("<table width = '50%' id = 'printData' cellpadding='0' cellspacing='0' class='my-table' > ");
			str.append("<tr>");
			str.append("<th>Practitioner</th> ");
			str.append("<th>Date</th> ");
			str.append("<th>Time</th> ");
			str.append("<th>Duration</th> ");
			str.append("<th>Patient</th> ");
			str.append("<th>Appointment Type</th> ");
			str.append("<th>Note</th> ");

			str.append("</tr>");

			for (NotAvailableSlot notAvailableSlot : practitionerApmtList) {
				str.append("<tr>");
				str.append("<td>" + notAvailableSlot.getDiaryUser() + "</td>");
				str.append("<td>" + notAvailableSlot.getCommencing() + "</td>");
				str.append("<td>" + notAvailableSlot.getSTime() + "</td>");
				str.append("<td>" + notAvailableSlot.getApmtDuration() + "</td>");
				str.append("<td>" + notAvailableSlot.getClient() + "</td>");
				str.append("<td>" + notAvailableSlot.getApmtType() + "</td>");
				str.append("<td>" + notAvailableSlot.getNotes() + "</td>");
				str.append("</tr>");
			}

			response.setContentType("text/html");
			response.setHeader("Cache-Control", "no-cache");

			response.getWriter().write("" + str.toString() + "");

		} catch (Exception e) {
			e.printStackTrace();
		}

		finally {
			connection.close();
		}
		return null;
	}

	public String getPractionerPrintData() throws SQLException {
		if (!verifyLogin(request)) {
			return "login";
		}
		String practionerId = request.getParameter("practitionerId");
		String date = request.getParameter("date");
		String practioner = request.getParameter("practitioner");
		notAvailableSlotForm.setDate(date);
		String temp[] = date.split("/");
		date = temp[2] + "-" + temp[1] + "-" + temp[0];
		Connection connection = null;
		try {
			connection = Connection_provider.getconnection();
			NotAvailableSlotDAO notAvailableSlotDAO = new JDBCNotAvailableSlotDAO(connection);

			ArrayList<NotAvailableSlot> practitionerApmtList = notAvailableSlotDAO
					.getPractitionerPrintData(practionerId, date);
			notAvailableSlotForm.setPractitionerApmtList(practitionerApmtList);
			notAvailableSlotForm.setPractitonerName(practioner);

			StringBuffer str = new StringBuffer();

			str.append("<table width = '50%' id = 'printData' cellpadding='0' cellspacing='0' class='my-table' > ");
			str.append("<tr>");
			str.append("<th>Practitioner</th> ");
			str.append("<th>Date</th> ");
			str.append("<th>Time</th> ");
			str.append("<th>Duration</th> ");
			str.append("<th>Patient</th> ");
			str.append("<th>Appointment Type</th> ");
			str.append("<th>Note</th> ");

			str.append("</tr>");

			for (NotAvailableSlot notAvailableSlot : practitionerApmtList) {
				str.append("<tr>");
				str.append("<td>" + notAvailableSlot.getDiaryUser() + "</td>");
				str.append("<td>" + notAvailableSlot.getCommencing() + "</td>");
				str.append("<td>" + notAvailableSlot.getSTime() + "</td>");
				str.append("<td>" + notAvailableSlot.getApmtDuration() + "</td>");
				str.append("<td>" + notAvailableSlot.getClient() + "</td>");
				str.append("<td>" + notAvailableSlot.getApmtType() + "</td>");
				str.append("<td>" + notAvailableSlot.getNotes() + "</td>");
				str.append("</tr>");
			}

			response.setContentType("text/html");
			response.setHeader("Cache-Control", "no-cache");

			response.getWriter().write("" + str.toString() + "");

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			connection.close();
		}
		return null;
	}

	public void checkAvailable() throws SQLException {
		Connection connection = null;
		try {
			connection = Connection_provider.getconnection();
			NotAvailableSlotDAO notAvailableSlotDAO = new JDBCNotAvailableSlotDAO(connection);

			String avlbltyDate = request.getParameter("avlbltyDate");
			avlbltyDate = DateTimeUtils.getCommencingDate(avlbltyDate);
			String chdiaryUser = request.getParameter("chdiaryUser");
			String chlocation = request.getParameter("chlocation");

			ArrayList<NotAvailableSlot> availableSlotList = notAvailableSlotDAO.getAvailableSlotList(avlbltyDate,
					chdiaryUser, chlocation);

			StringBuffer str = new StringBuffer();

			str.append("<thead>");
			str.append("<tr>");
			str.append("<th>Practitioner</th> ");
			str.append("<th>Location</th> ");
			str.append("<th>Start Time</th> ");
			str.append("<th>End Time</th> ");
			str.append("<th>Check Availability</th> ");
			str.append("</thead>");

			str.append("<tbody>");
			for (NotAvailableSlot notAvailableSlot : availableSlotList) {

				String dataString = "" + notAvailableSlot.getId() + "/" + notAvailableSlot.getDiaryUserId() + "/"
						+ notAvailableSlot.getDiaryUser() + "/" + notAvailableSlot.getLocation() + "/"
						+ notAvailableSlot.getsTime() + "/" + notAvailableSlot.getEndTime() + "/" + avlbltyDate + "";

				str.append("<tr>");
				str.append("<td>" + notAvailableSlot.getDiaryUser() + "</td>");
				str.append("<td>" + notAvailableSlot.getLocation() + "</td>");
				str.append("<td>" + notAvailableSlot.getsTime() + "</td>");
				str.append("<td>" + notAvailableSlot.getEndTime() + "</td>");
				str.append("<td><a href='#' id='" + dataString
						+ "' onclick='setCheckedAppointmentData(this.id)'><font color='Blue'><u>Check Availability</u></font> </a></td>");

				str.append("</tr>");
			}

			str.append("</tbody>");
			response.setContentType("text/html");
			response.setHeader("Cache-Control", "no-cache");

			response.getWriter().write("" + str.toString() + "");

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			connection.close();
		}

	}

	public String showallAvailability() throws Exception {
		if (!verifyLogin(request)) {
			return "login";
		}
		String date = request.getParameter("date");
		String diaryuserId = request.getParameter("diaryuserId");
		String location = request.getParameter("location");
		String starttime = request.getParameter("starttime");
		String endtime = request.getParameter("endtime");
		String diaryuser = request.getParameter("diaryuser");
		String slotId = request.getParameter("slotid");

		Connection connection = null;
		try {
			connection = Connection_provider.getconnection();
			NotAvailableSlotDAO notAvailableSlotDAO = new JDBCNotAvailableSlotDAO(connection);
			ArrayList<NotAvailableSlot> allAvailableSlotList = notAvailableSlotDAO.getAllAvailableSlotList(date,
					diaryuserId, location);

			StringBuffer str = new StringBuffer();

			str.append("<thead>");
			str.append("<tr>");
			str.append("<th>Practitioner</th> ");
			str.append("<th>Location</th> ");
			str.append("<th>Start Time</th> ");
			str.append("<th>End Time</th> ");
			str.append("<th>Select Slot</th> ");
			str.append("</tr>");
			str.append("</thead>");

			str.append("</tbody>");
			int count = 1;
			if (allAvailableSlotList.size() == 0) {
				String dataString = "" + slotId + "/" + diaryuserId + "/" + diaryuser + "/" + location + "/" + starttime
						+ "/" + endtime + "/" + date + "";

				str.append("<tr>");
				str.append("<td>" + diaryuserId + "</td>");
				str.append("<td>" + location + "</td>");
				str.append("<td>" + starttime + "</td>");
				str.append("<td>" + endtime + "</td>");
				str.append("<td><a href='#' id='" + dataString
						+ "' onclick='setSelectedAppointmentData(this.id)'><font color='Blue'><u>Select Slot</u></fonr> </a></td>");

				str.append("</tr>");
			}

			for (NotAvailableSlot notAvailableSlot : allAvailableSlotList) {

				String s1 = notAvailableSlot.getsTime();
				String dataString = "" + notAvailableSlot.getId() + "/" + notAvailableSlot.getDiaryUserId() + "/"
						+ notAvailableSlot.getDiaryUser() + "/" + notAvailableSlot.getLocation() + "/" + starttime + "/"
						+ notAvailableSlot.getsTime() + "/" + date + "";
				if (!starttime.equals(s1) && count == 1) {
					str.append("<tr>");
					str.append("<td>" + notAvailableSlot.getDiaryUser() + "</td>");
					str.append("<td>" + notAvailableSlot.getLocation() + "</td>");
					str.append("<td>" + starttime + "</td>");
					str.append("<td>" + notAvailableSlot.getsTime() + "</td>");
					str.append("<td><a href='#' id='" + dataString
							+ "' onclick='setSelectedAppointmentData(this.id)'><font color='Blue'><u>Set</u></fonr> </a></td>");

					str.append("</tr>");
				}
				count = count + 1;
			}

			for (int i = 0; i < allAvailableSlotList.size() - 1; i++) {
				String dataString = "" + allAvailableSlotList.get(i).getId() + "/"
						+ allAvailableSlotList.get(i).getDiaryUserId() + "/"
						+ allAvailableSlotList.get(i).getDiaryUser() + "/" + allAvailableSlotList.get(i).getLocation()
						+ "/" + allAvailableSlotList.get(i).getEndTime() + "/"
						+ allAvailableSlotList.get(i + 1).getsTime() + "/" + date + "";

				str.append("<tr>");
				str.append("<td>" + allAvailableSlotList.get(i).getDiaryUser() + "</td>");
				str.append("<td>" + allAvailableSlotList.get(i).getLocation() + "</td>");
				str.append("<td>" + allAvailableSlotList.get(i).getEndTime() + "</td>");
				str.append("<td>" + allAvailableSlotList.get(i + 1).getsTime() + "</td>");
				str.append("<td><a href='#' id='" + dataString
						+ "' onclick='setSelectedAppointmentData(this.id)'><font color='Blue'><u>Select Slot </u></fonr> </a></td>");

				str.append("</tr>");
			}
			int c = allAvailableSlotList.size() - 1;
			for (int i = 0; i < allAvailableSlotList.size(); i++) {
				String dataString = "" + allAvailableSlotList.get(i).getId() + "/"
						+ allAvailableSlotList.get(i).getDiaryUserId() + "/"
						+ allAvailableSlotList.get(i).getDiaryUser() + "/" + allAvailableSlotList.get(i).getLocation()
						+ "/" + allAvailableSlotList.get(i).getEndTime() + "/" + endtime + "/" + date + "";

				if (i == c) {
					str.append("<tr>");
					str.append("<td>" + allAvailableSlotList.get(i).getDiaryUser() + "</td>");
					str.append("<td>" + allAvailableSlotList.get(i).getLocation() + "</td>");
					str.append("<td>" + allAvailableSlotList.get(i).getEndTime() + "</td>");
					str.append("<td>" + endtime + "</td>");
					str.append("<td><a href='#' id='" + dataString
							+ "' onclick='setSelectedAppointmentData(this.id)'><font color='Blue'><u>Select Slot </u></fonr> </a></td>");

					str.append("</tr>");
				}
			}
			str.append("</tbody>");
			response.setContentType("text/html");
			response.setHeader("Cache-Control", "no-cache");

			response.getWriter().write("" + str.toString() + "");

		} catch (Exception e) {
			e.printStackTrace();
		} finally {

			connection.close();
		}
		return null;
	}

	public String getAllPrintData() throws SQLException, ParseException {
		if (!verifyLogin(request)) {
			return "login";
		}

		String action = request.getParameter("action");

		String date = notAvailableSlotForm.getPrintCommencing();
		String location = notAvailableSlotForm.getPrintLocation();
		String diaryuser = notAvailableSlotForm.getPreviewdiaryuser();
		String diaryuserid = request.getParameter("previewdiaryuser");
		String docid = request.getParameter("docid");
		String endDate = "";
		if(!DateTimeUtils.isNull(docid).equals("")) {
			diaryuser=docid;
			diaryuserid=docid;
			notAvailableSlotForm.setPreviewdiaryuser(docid);	
		}
		if(date.equals("")) {
			DateFormat currentdate = new SimpleDateFormat("dd/MM/yyyy");
			Calendar cal = Calendar.getInstance();
			// cal.add(Calendar.DATE, -7);
			date = currentdate.format(cal.getTime());
		}
		String temp[] = date.split("/");
		date = temp[2] + "-" + temp[1] + "-" + temp[0];

		int wyear = Integer.parseInt(temp[2]);
		int month = Integer.parseInt(temp[1]);
		int day = Integer.parseInt(temp[0]);

		String cweekName = DateTimeUtils.getWeekName(wyear, month, day);

		notAvailableSlotForm.setCommencing(cweekName + ":" + " " + notAvailableSlotForm.getPrintCommencing());

		if (action.equals("week")) {
			String dateStr = date;// this date of dashboard calender date
			DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			Date sdate = (Date) dateFormat.parse(dateStr);
			// System.out.println(sdate);

			Calendar cal = Calendar.getInstance();
			cal.setTime(sdate);
			cal.add(Calendar.DATE, 7);
			endDate = dateFormat.format(cal.getTime());

		}

		Connection connection = null;
		try {
			connection = Connection_provider.getconnection();
			NotAvailableSlotDAO notAvailableSlotDAO = new JDBCNotAvailableSlotDAO(connection);
			MisChartDAO misChartDAO = new JDBCMisChartDAO(connection);
			// Adarsh for preview
			String fromDate = notAvailableSlotForm.getFromDate();
			String toDate = notAvailableSlotForm.getToDate();
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
					notAvailableSlotForm.setFromDate(fromDate);
				} else {
					String tempe[] = fromDate.split("/");
					fromDate = tempe[2] + "-" + tempe[1] + "-" + tempe[0];
					/* fromDate = DateTimeUtils.getCommencingDate1(fromDate); */
				}
			}
			if (toDate == null) {
				DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
				Calendar cal = Calendar.getInstance();
				// cal.add(Calendar.DATE, -7);
				toDate = dateFormat.format(cal.getTime());
				toDate = DateTimeUtils.getCommencingDate1(toDate);
			} else {
				if (toDate.equals("")) {
					DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
					Calendar cal = Calendar.getInstance();
					// cal.add(Calendar.DATE, -7);
					toDate = dateFormat.format(cal.getTime());
					toDate = DateTimeUtils.getCommencingDate1(toDate);
					notAvailableSlotForm.setToDate(toDate);
				} else {
					String temp1[] = toDate.split("/");
					toDate = temp1[2] + "-" + temp1[1] + "-" + temp1[0];
					/* toDate = DateTimeUtils.getCommencingDate1(toDate); */
				}
			}
			// @ Adarsh
			// set diaryuserlist
			ArrayList<DiaryManagement> userList=new ArrayList<DiaryManagement>();
			if(loginInfo.getClinicid1().equals("manasclinic") && loginInfo.getSuperadminid()!=1) {
				String clinicstaff_id=notAvailableSlotDAO.getclinicstaff(loginInfo.getId());
				userList = notAvailableSlotDAO.getManasClinicUserList(loginInfo.getId(),clinicstaff_id);
			}else {
				userList = notAvailableSlotDAO.getUserList(loginInfo.getId());
			}
			//userList = notAvailableSlotDAO.getUserList(loginInfo.getId());
			notAvailableSlotForm.setUserList(userList);

			String openedb = (String) session.getAttribute("openedb");

			/*
			 * ArrayList<NotAvailableSlot> practitionerApmtList =
			 * notAvailableSlotDAO.getAllPractitionerPrintData(date, endDate, location,
			 * diaryuser, action, openedb, notAvailableSlotForm.getPreviewdiaryuser(),
			 * fromDate, toDate,loginInfo);
			 */
			ArrayList<NotAvailableSlot>practitionerApmtList=new ArrayList<NotAvailableSlot>();
			
			if(loginInfo.getClinicid1().equals("manasclinic") && loginInfo.getSuperadminid()!=1) {
				String clinicstaff_id=notAvailableSlotDAO.getclinicstaff(loginInfo.getId());
				practitionerApmtList = notAvailableSlotDAO.getManasclinicAllPractitionerPrintData(date,
						endDate, location, diaryuser, action, openedb, notAvailableSlotForm.getPreviewdiaryuser(), fromDate,
						toDate,loginInfo,clinicstaff_id);
			}else {
			    practitionerApmtList = notAvailableSlotDAO.getAllPractitionerPrintData(date,
						endDate, location, diaryuser, action, openedb, notAvailableSlotForm.getPreviewdiaryuser(), fromDate,
						toDate,loginInfo);
			}

			notAvailableSlotForm.setPractitionerApmtList(practitionerApmtList);
			session.setAttribute("practitionerApmtList", practitionerApmtList);
			ArrayList<MisReport> opdappointmenttype = new ArrayList<MisReport>();
			ArrayList<MisReport> appointmenttypeid = misChartDAO.getAppointmentTypeID();
			int totalopdcount=0;
			for (MisReport string : appointmenttypeid) {
				// MisReport mistotal =
				// misChartDAO.getOPDAppointmentforpreview(fromDate,
				// toDate,""+string.getId(),diaryuserid);
				MisReport mistotal = misChartDAO.getOPDAppointmentCountforpreview(fromDate, toDate, "" + string.getId(),
						diaryuserid);
				if (mistotal.getResult() != null) {
					if (!mistotal.getResult().equals("")) {
						mistotal.setName(string.getName());
					}
					totalopdcount=totalopdcount+ DateTimeUtils.convertToInteger(mistotal.getResult());
					opdappointmenttype.add(mistotal);
				}
				
			}
			notAvailableSlotForm.setTotalopdcount(totalopdcount);
			/* notAvailableSlotForm.settot */
			notAvailableSlotForm.setOpdappointmenttype(opdappointmenttype);
			ArrayList<NotAvailableSlot> dayList = new ArrayList<NotAvailableSlot>();
			if (action.equals("day")) {
				int i = 0;
				for (NotAvailableSlot notAvailableSlot : practitionerApmtList) {
					dayList.add(notAvailableSlot);
					if (i != practitionerApmtList.size() - 1) {
						NotAvailableSlot n = practitionerApmtList.get(i + 1);
						if (!notAvailableSlot.getEndTime().equals(n.getSTime())
								&& !notAvailableSlot.isApmtSlotStimeEmpty()) {
							NotAvailableSlot newNotAvailableSlot = new NotAvailableSlot();
							newNotAvailableSlot.setSTime(notAvailableSlot.getEndTime());
							String duration = DateTimeUtils.getDuration(notAvailableSlot.getEndTime(), n.getSTime());
							newNotAvailableSlot.setApmtDuration(duration);
							newNotAvailableSlot.setActionType(action);
							newNotAvailableSlot.setStatus("2");
							dayList.add(newNotAvailableSlot);

						}
					}

					i++;
				}

				if (location.equals("0")) {
					notAvailableSlotForm.setLocationName("All");
				}

				if (!location.equals("0")) {

					ClinicDAO clinicListDAO = new JDBCClinicDAO(connection);
					Clinic clinic = new Clinic();
					clinic = clinicListDAO.getLocationDetails(Integer.parseInt(location));
					String locationName = clinic.getCity() + " (" + clinic.getLocationname() + ")";
					notAvailableSlotForm.setLocationName(clinic.getLocationname());

					NotAvailableSlot lastn = dayList.get(dayList.size() - 1);
					DiaryManagement diaryManagement = notAvailableSlotDAO.getApmtSlotData(date, diaryuser, location);
					if (!diaryManagement.getEndTime().equals(lastn.getSTime())) {
						String duration = DateTimeUtils.getDuration(lastn.getEndTime(), diaryManagement.getEndTime());
						// System.out.println(duration);
						NotAvailableSlot lastnp = new NotAvailableSlot();
						lastnp.setSTime(lastn.getEndTime());
						lastnp.setApmtDuration(duration);
						lastnp.setActionType(action);
						lastnp.setStatus("2");

						dayList.add(lastnp);
					}

				}

				notAvailableSlotForm.setPractitionerApmtList(dayList);

			}

			notAvailableSlotForm.setActionType(action);

			String diaryUserFullname = notAvailableSlotDAO.getDiaryUserFullname(diaryuser);
			notAvailableSlotForm.setDiaryUser(diaryUserFullname);
			String locationName = notAvailableSlotDAO.getLocationName(location);

			//  
			int size = practitionerApmtList.size();
			if (size > 0) {
				String diaryuser1 = practitionerApmtList.get(size - 1).getDiaryUser();
				notAvailableSlotForm.setDiaryUser(diaryuser1);
				if (diaryuser != null) {
					if (diaryuser.equals("")) {
						diaryuser = "0";
					}
					if (diaryuser.equals("0")) {
						notAvailableSlotForm.setDiaryUser("All");
					}
				}
			} else {
				notAvailableSlotForm.setDiaryUser("All");
			}

			//   15 June 2018

			String temp1[] = fromDate.split("-");
			fromDate = temp1[2] + "/" + temp1[1] + "/" + temp1[0];

			String temp2[] = toDate.split("-");
			toDate = temp2[2] + "/" + temp2[1] + "/" + temp2[0];

			notAvailableSlotForm.setFromDate(fromDate);
			notAvailableSlotForm.setToDate(toDate);

			if (openedb.equals("staff")) {
				return "printstaffdashboard";
			}

			/*
			 * StringBuffer str = new StringBuffer();
			 * 
			 * str.
			 * append("<table width = '100%' id = 'printData' cellpadding='0' cellspacing='0' class='my-table' > "
			 * ); str.append("<tr>"); str.append("<th>Practitioner</th> ");
			 * str.append("<th>Date</th> "); str.append("<th>Time</th> ");
			 * str.append("<th>Duration</th> ");
			 * str.append("<th>Patient</th> ");
			 * str.append("<th>Appointment Type</th> ");
			 * str.append("<th>Note</th> ");
			 * 
			 * str.append("</tr>");
			 * 
			 * for(NotAvailableSlot notAvailableSlot:practitionerApmtList){
			 * str.append("<tr>");
			 * str.append("<td>"+notAvailableSlot.getDiaryUser()+"</td>");
			 * str.append("<td>"+notAvailableSlot.getCommencing()+"</td>");
			 * str.append("<td>"+notAvailableSlot.getSTime()+"</td>");
			 * str.append("<td>"+notAvailableSlot.getApmtDuration()+"</td>");
			 * str.append("<td>"+notAvailableSlot.getClient()+"</td>");
			 * str.append("<td>"+notAvailableSlot.getApmtType()+"</td>");
			 * str.append("<td>"+notAvailableSlot.getNotes()+"</td>");
			 * str.append("</tr>"); } str.append("</table>");
			 * 
			 * response.setContentType("text/html");
			 * response.setHeader("Cache-Control", "no-cache");
			 * 
			 * response.getWriter().write(""+str.toString()+"");
			 */
			Clinic clinic = new Clinic();
			ClinicDAO clinicDAO = new JDBCClinicDAO(connection);
			clinic = clinicDAO.getCliniclistDetails(loginInfo.getClinicid());
			notAvailableSlotForm.setClinicName(clinic.getClinicName());
			notAvailableSlotForm.setClinicOwner(clinic.getClinicOwner());
			notAvailableSlotForm.setOwner_qualification(clinic.getOwner_qualification());
			notAvailableSlotForm.setLandLine(clinic.getLandLine());
			notAvailableSlotForm.setClinicemail(clinic.getEmail());
			notAvailableSlotForm.setWebsiteUrl(clinic.getWebsiteUrl());

			notAvailableSlotForm.setClinicLogo(clinic.getUserImageFileName());
			
			ArrayList<NotAvailableSlot>deptwisegenderlist=notAvailableSlotDAO.getDeptwisegenderList(fromDate,toDate,loginInfo);
			notAvailableSlotForm.setDeptwisegenderlist(deptwisegenderlist);

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			connection.close();
		}

		return "printdashpoard";
	}
	/*
	 * public String getAllPrintData() throws SQLException, ParseException{
	 * if(!verifyLogin(request)){ return "login"; }
	 * 
	 * String action = request.getParameter("action");
	 * 
	 * String date = notAvailableSlotForm.getPrintCommencing(); String location
	 * = notAvailableSlotForm.getPrintLocation(); String diaryuser =
	 * notAvailableSlotForm.getPrintDiaryserid(); String endDate = "";
	 * 
	 * String temp[] = date.split("/"); date = temp[2] + "-" + temp[1] + "-" +
	 * temp[0];
	 * 
	 * int wyear = Integer.parseInt(temp[2]); int month =
	 * Integer.parseInt(temp[1]); int day = Integer.parseInt(temp[0]);
	 * 
	 * 
	 * String cweekName = DateTimeUtils.getWeekName(wyear,month,day);
	 * 
	 * notAvailableSlotForm.setCommencing(cweekName + ":" + " " +
	 * notAvailableSlotForm.getPrintCommencing());
	 * 
	 * if(action.equals("week")){ String dateStr = date;// this date of
	 * dashboard calender date DateFormat dateFormat = new
	 * SimpleDateFormat("yyyy-MM-dd"); Date sdate =
	 * (Date)dateFormat.parse(dateStr); System.out.println(sdate);
	 * 
	 * Calendar cal = Calendar.getInstance(); cal.setTime(sdate);
	 * cal.add(Calendar.DATE, 7); endDate = dateFormat.format(cal.getTime());
	 * 
	 * }
	 * 
	 * Connection connection = null; try{ connection =
	 * Connection_provider.getconnection(); NotAvailableSlotDAO
	 * notAvailableSlotDAO = new JDBCNotAvailableSlotDAO(connection); // Adarsh
	 * for preview String fromDate = notAvailableSlotForm.getFromDate(); String
	 * toDate = notAvailableSlotForm.getToDate(); if(fromDate == null){
	 * DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy"); Calendar cal
	 * = Calendar.getInstance(); //cal.add(Calendar.DATE, -7); fromDate =
	 * dateFormat.format(cal.getTime()); fromDate =
	 * DateTimeUtils.getCommencingDate1(fromDate); } else{
	 * if(fromDate.equals("")){ DateFormat dateFormat = new
	 * SimpleDateFormat("dd-MM-yyyy"); Calendar cal = Calendar.getInstance();
	 * //cal.add(Calendar.DATE, -7); fromDate =
	 * dateFormat.format(cal.getTime());
	 * notAvailableSlotForm.setFromDate(fromDate); }else{ String tempe[]=
	 * fromDate.split("/"); fromDate = tempe[2]+"-"+tempe[1]+"-"+tempe[0];
	 * fromDate = DateTimeUtils.getCommencingDate1(fromDate); } } if(toDate ==
	 * null){ DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
	 * Calendar cal = Calendar.getInstance(); //cal.add(Calendar.DATE, -7);
	 * toDate = dateFormat.format(cal.getTime()); toDate =
	 * DateTimeUtils.getCommencingDate1(toDate); }else{ if(toDate.equals("")){
	 * DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy"); Calendar cal
	 * = Calendar.getInstance(); //cal.add(Calendar.DATE, -7); toDate =
	 * dateFormat.format(cal.getTime()); notAvailableSlotForm.setToDate(toDate);
	 * }else{ String temp1[]= toDate.split("/"); toDate =
	 * temp1[2]+"-"+temp1[1]+"-"+temp1[0]; toDate =
	 * DateTimeUtils.getCommencingDate1(toDate); } }
	 * 
	 * 
	 * if(!fromDate.equals("")){ String temp[]= fromDate.split("/"); fromDate =
	 * temp[2]+"-"+temp[1]+"-"+temp[0]; } if(!toDate.equals("")){ String
	 * temp1[]= toDate.split("/"); toDate = temp1[2]+"-"+temp1[1]+"-"+temp1[0];
	 * } // @ Adarsh //set diaryuserlist
	 * 
	 * ArrayList<DiaryManagement>userList =
	 * notAvailableSlotDAO.getUserList(loginInfo.getId());
	 * notAvailableSlotForm.setUserList(userList);
	 * 
	 * String openedb = (String)session.getAttribute("openedb");
	 * 
	 * ArrayList<NotAvailableSlot>practitionerApmtList =
	 * notAvailableSlotDAO.getAllPractitionerPrintData(date,endDate,location,
	 * diaryuser,action,openedb,notAvailableSlotForm.getPreviewdiaryuser(),
	 * fromDate,toDate);
	 * notAvailableSlotForm.setPractitionerApmtList(practitionerApmtList);
	 * 
	 * 
	 * ArrayList<NotAvailableSlot>dayList = new ArrayList<NotAvailableSlot>();
	 * if(action.equals("day")){ int i = 0; for(NotAvailableSlot
	 * notAvailableSlot : practitionerApmtList){ dayList.add(notAvailableSlot);
	 * if(i!=practitionerApmtList.size()-1){ NotAvailableSlot n =
	 * practitionerApmtList.get(i+1);
	 * if(!notAvailableSlot.getEndTime().equals(n.getSTime()) &&
	 * !notAvailableSlot.isApmtSlotStimeEmpty()){ NotAvailableSlot
	 * newNotAvailableSlot = new NotAvailableSlot();
	 * newNotAvailableSlot.setSTime(notAvailableSlot.getEndTime()); String
	 * duration =
	 * DateTimeUtils.getDuration(notAvailableSlot.getEndTime(),n.getSTime());
	 * newNotAvailableSlot.setApmtDuration(duration);
	 * newNotAvailableSlot.setActionType(action);
	 * newNotAvailableSlot.setStatus("2"); dayList.add(newNotAvailableSlot);
	 * 
	 * 
	 * } }
	 * 
	 * 
	 * 
	 * 
	 * i++; }
	 * 
	 * if(location.equals("0")){ notAvailableSlotForm.setLocationName("All"); }
	 * 
	 * if(!location.equals("0")){
	 * 
	 * ClinicDAO clinicListDAO = new JDBCClinicDAO(connection); Clinic clinic =
	 * new Clinic(); clinic =
	 * clinicListDAO.getLocationDetails(Integer.parseInt(location)); String
	 * locationName = clinic.getCity() + " ("+clinic.getLocationname()+")";
	 * notAvailableSlotForm.setLocationName(clinic.getLocationname());
	 * 
	 * 
	 * NotAvailableSlot lastn = dayList.get(dayList.size()-1); DiaryManagement
	 * diaryManagement =
	 * notAvailableSlotDAO.getApmtSlotData(date,diaryuser,location);
	 * if(!diaryManagement.getEndTime().equals(lastn.getSTime())){ String
	 * duration =
	 * DateTimeUtils.getDuration(lastn.getEndTime(),diaryManagement.getEndTime()
	 * ); System.out.println(duration); NotAvailableSlot lastnp = new
	 * NotAvailableSlot(); lastnp.setSTime(lastn.getEndTime());
	 * lastnp.setApmtDuration(duration); lastnp.setActionType(action);
	 * lastnp.setStatus("2");
	 * 
	 * dayList.add(lastnp); }
	 * 
	 * 
	 * 
	 * 
	 * }
	 * 
	 * notAvailableSlotForm.setPractitionerApmtList(dayList);
	 * 
	 * }
	 * 
	 * 
	 * notAvailableSlotForm.setActionType(action);
	 * 
	 * String diaryUserFullname =
	 * notAvailableSlotDAO.getDiaryUserFullname(diaryuser);
	 * notAvailableSlotForm.setDiaryUser(diaryUserFullname); String locationName
	 * = notAvailableSlotDAO.getLocationName(location);
	 * 
	 * //  int size = practitionerApmtList.size(); if (size > 0) { String
	 * diaryuser1 = practitionerApmtList.get(size - 1).getDiaryUser();
	 * notAvailableSlotForm.setDiaryUser(diaryuser1); }
	 * 
	 * 
	 * if(openedb.equals("staff")){ return "printstaffdashboard"; }
	 * 
	 * 
	 * StringBuffer str = new StringBuffer();
	 * 
	 * str.
	 * append("<table width = '100%' id = 'printData' cellpadding='0' cellspacing='0' class='my-table' > "
	 * ); str.append("<tr>"); str.append("<th>Practitioner</th> ");
	 * str.append("<th>Date</th> "); str.append("<th>Time</th> ");
	 * str.append("<th>Duration</th> "); str.append("<th>Patient</th> ");
	 * str.append("<th>Appointment Type</th> "); str.append("<th>Note</th> ");
	 * 
	 * str.append("</tr>");
	 * 
	 * for(NotAvailableSlot notAvailableSlot:practitionerApmtList){
	 * str.append("<tr>");
	 * str.append("<td>"+notAvailableSlot.getDiaryUser()+"</td>");
	 * str.append("<td>"+notAvailableSlot.getCommencing()+"</td>");
	 * str.append("<td>"+notAvailableSlot.getSTime()+"</td>");
	 * str.append("<td>"+notAvailableSlot.getApmtDuration()+"</td>");
	 * str.append("<td>"+notAvailableSlot.getClient()+"</td>");
	 * str.append("<td>"+notAvailableSlot.getApmtType()+"</td>");
	 * str.append("<td>"+notAvailableSlot.getNotes()+"</td>");
	 * str.append("</tr>"); } str.append("</table>");
	 * 
	 * response.setContentType("text/html"); response.setHeader("Cache-Control",
	 * "no-cache");
	 * 
	 * response.getWriter().write(""+str.toString()+""); Clinic clinic = new
	 * Clinic(); ClinicDAO clinicDAO = new JDBCClinicDAO(connection); clinic =
	 * clinicDAO.getCliniclistDetails(loginInfo.getClinicid());
	 * notAvailableSlotForm.setClinicName(clinic.getClinicName());
	 * notAvailableSlotForm.setClinicOwner(clinic.getClinicOwner());
	 * notAvailableSlotForm.setOwner_qualification(clinic.getOwner_qualification
	 * ()); notAvailableSlotForm.setLandLine(clinic.getLandLine());
	 * notAvailableSlotForm.setClinicemail(clinic.getEmail());
	 * notAvailableSlotForm.setWebsiteUrl(clinic.getWebsiteUrl());
	 * 
	 * notAvailableSlotForm.setClinicLogo(clinic.getUserImageFileName());
	 * 
	 * 
	 * }catch (Exception e) { e.printStackTrace(); } finally{
	 * connection.close(); }
	 * 
	 * return "printdashpoard"; }
	 */

	public String duration() throws Exception {
		if (!verifyLogin(request)) {
			return "login";
		}
		String selectedid = request.getParameter("selectedid");
		Connection connection = null;
		try {
			connection = Connection_provider.getconnection();
			NotAvailableSlotDAO notAvailableSlotDAO = new JDBCNotAvailableSlotDAO(connection);

			String durationStr = notAvailableSlotDAO.getDuration(selectedid);
            String str=durationStr+"~"+selectedid;
			response.setContentType("text/html");
			response.setHeader("Cache-Control", "no-cache");
			response.getWriter().write("" + str + "");

		} catch (Exception e) {
			e.printStackTrace();
		} finally {

			connection.close();
		}

		return null;
	}

	public String multiloc() throws SQLException {

		if (!verifyLogin(request)) {
			return "login";
		}

		// var url =
		// "multilocNotAvailableSlot?title="+title+"&endtime="+endtime+"&userid="+userid+"&commencing="+commencing+"
		// ";
		String title = request.getParameter("title");
		String temp[] = title.split(" ");
		String selectedStarttime = temp[0];

		String diaryuserid = request.getParameter("userid");
		String commencing = request.getParameter("commencing");
		String prevEndTime = request.getParameter("endtime");

		Connection connection = null;
		try {
			connection = Connection_provider.getconnection();
			NotAvailableSlotDAO notAvailableSlotDAO = new JDBCNotAvailableSlotDAO(connection);

			NotAvailableSlot notAvailableSlot = notAvailableSlotDAO.getInitilizedElementData(diaryuserid, commencing,
					selectedStarttime, prevEndTime);

			String data = notAvailableSlot.getApmtSlotId() + "/" + notAvailableSlot.getSTime() + "/"
					+ notAvailableSlot.getEndTime() + "/" + notAvailableSlot.getLocation() + "/"
					+ notAvailableSlot.getDisciplineid();

			response.setContentType("text/html");
			response.setHeader("Cache-Control", "no-cache");
			response.getWriter().write("" + data + "");

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			connection.close();
		}

		return null;
	}

	public String otdoctor() throws Exception {
		String location = request.getParameter("location");
		String sTime = request.getParameter("sTime");
		String endTime = request.getParameter("endTime");
		String commencing = request.getParameter("commencing");

		Connection connection = null;
		try {
			connection = Connection_provider.getconnection();
			NotAvailableSlotDAO notAvailableSlotDAO = new JDBCNotAvailableSlotDAO(connection);

			ArrayList<UserProfile> otdoctorlist = notAvailableSlotDAO.getOTDoctorList();
			StringBuffer str = new StringBuffer();
			for (UserProfile userProfile : otdoctorlist) {
				boolean checkEventExist = notAvailableSlotDAO.checkEventAllreadyExist(commencing, location,
						Integer.toString(userProfile.getId()), sTime, endTime);
				if (!checkEventExist) {
					str.append(userProfile.getId() + ",");
				}
			}

			response.setContentType("text/html");
			response.setHeader("Cache-Control", "no-cache");
			response.getWriter().write("" + str + "");

		} catch (Exception e) {
			e.printStackTrace();
		} finally {

			connection.close();
		}

		return null;
	}

	public String diaryduration() throws SQLException {

		String slotid = request.getParameter("diaryslotid");
		// System.out.println(slotid);

		if (!verifyLogin(request)) {
			return "login";
		}
		Connection connection = null;
		try {
			connection = Connection_provider.getconnection();
			NotAvailableSlotDAO notAvailableSlotDAO = new JDBCNotAvailableSlotDAO(connection);

			String duration = notAvailableSlotDAO.getDiaryDuration(slotid);

			response.setContentType("text/html");
			response.setHeader("Cache-Control", "no-cache");
			response.getWriter().write("" + duration + "");

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			connection.close();
		}

		return null;
	}

	public String eventExist() throws Exception {
		if (!verifyLogin(request)) {
			return "login";
		}
		Connection connection = null;
		try {
			connection = Connection_provider.getconnection();
			NotAvailableSlotDAO notAvailableSlotDAO = new JDBCNotAvailableSlotDAO(connection);

			String commencing = request.getParameter("commencing");
			String location = request.getParameter("location");
			String diaryuserId = request.getParameter("diaryuserId");
			String starttime = request.getParameter("starttime");
			String endtime = request.getParameter("endtime");
			int editAppointId = Integer.parseInt(request.getParameter("editAppointId"));

			boolean checkEventExist = notAvailableSlotDAO.checkEventAllreadyExist(commencing, location, diaryuserId,
					starttime, endtime);
			if (editAppointId != 0) {
				NotAvailableSlot notAvailableSlot = notAvailableSlotDAO.getAvailableSlotdata(editAppointId);

				if (starttime.equals(notAvailableSlot.getsTime()) && endtime.equals(notAvailableSlot.getEndTime())) {
					checkEventExist = false;
				} else {

					checkEventExist = notAvailableSlotDAO.checkEventAllreadyExist(commencing, location, diaryuserId,
							starttime, endtime, editAppointId);
				}
			}

			if (checkEventExist) {
				int coutnEsistingSlot = notAvailableSlotDAO.coutnEsistingSlot(commencing, location, diaryuserId,
						starttime, endtime, editAppointId);

				if (coutnEsistingSlot == 1) {
					String existStartTime = notAvailableSlotDAO.getExistStartTime(commencing, location, diaryuserId,
							starttime, endtime, editAppointId);

					String duration = DateTimeUtils.getDuration(starttime, endtime);

					// System.out.println(duration);

					String sumoftime = DateTimeUtils.getSumofTime(starttime, duration);

					if (sumoftime.equals(existStartTime)) {
						checkEventExist = false;
					}

					// boolean checkDurationExist =
					// notAvailableSlotDAO.checkDurationExist(duration);

					/*
					 * if(checkDurationExist){ checkEventExist = false; }
					 */
				}
				if (editAppointId != 0) {
					if (coutnEsistingSlot == 1 || coutnEsistingSlot == 2) {
						String duration = DateTimeUtils.getDuration(starttime, endtime);

						String existStartTime = notAvailableSlotDAO.getEditExistStartTime(commencing, location,
								diaryuserId, starttime, endtime, editAppointId);

						// System.out.println(duration);

						String sumoftime = DateTimeUtils.getSumofTime(starttime, duration);

						if (sumoftime.equals(existStartTime)) {
							checkEventExist = false;
						}

					}
				}

			}

			response.setContentType("text/html");
			response.setHeader("Cache-Control", "no-cache");
			response.getWriter().write("" + checkEventExist + "");

		} catch (Exception e) {
			e.printStackTrace();
		} finally {

			connection.close();
		}

		return null;
	}

	public String charge() throws Exception {

		String apmtType = request.getParameter("apmtType");
		Connection connection = null;
		try {
			connection = Connection_provider.getconnection();
			NotAvailableSlotDAO notAvailableSlotDAO = new JDBCNotAvailableSlotDAO(connection);

			double charge = notAvailableSlotDAO.getCharge(apmtType);

			response.setContentType("text/html");
			response.setHeader("Cache-Control", "no-cache");
			response.getWriter().write("" + charge + "");

		} catch (Exception e) {
			// TODO: handle exception
		} finally {

			connection.close();
		}

		return null;
	}

	public String otcharge() throws Exception {
		Connection connection = null;
		try {
			String editAppointId = request.getParameter("editAppointId");

			connection = Connection_provider.getconnection();
			NotAvailableSlotDAO notAvailableSlotDAO = new JDBCNotAvailableSlotDAO(connection);

			boolean isotchargeexist = notAvailableSlotDAO.checkOtChargeExist(editAppointId);

			String str = "0";

			if (isotchargeexist) {
				str = "1";
			}

			response.setContentType("text/html");
			response.setHeader("Cache-Control", "no-cache");
			response.getWriter().write("" + str + "");

		} catch (Exception e) {
			// TODO: handle exception
		} finally {

			connection.close();
		}

		return null;
	}

	public String diagnosis() throws Exception {

		Connection connection = null;
		try {
			String editAppointId = request.getParameter("editAppointId");

			StringBuffer buffer = new StringBuffer();
			connection = Connection_provider.getconnection();
			NotAvailableSlotDAO notAvailableSlotDAO = new JDBCNotAvailableSlotDAO(connection);

			String id = notAvailableSlotDAO.getSelectedDiagnosisID(editAppointId);

			String multipleconditions = notAvailableSlotDAO.getMultipleDiagnosis(editAppointId);
			if (multipleconditions != null) {

				int index = 0;
				for (String con : multipleconditions.split(",")) {

					if (con.equals("0")) {
						continue;
					}

					ArrayList<Diagnosis> list = notAvailableSlotDAO.getMultipleConditions("");
					buffer.append("<tr>");
					buffer.append("<td>");
					buffer.append("<select class='form-control chosen' name='diagnosises[" + index + "].conditionid'>");
					buffer.append("<option value='0'>Select Condition</option>");
					for (Diagnosis diagnosis : list) {

						int iid = Integer.parseInt(con);
						if (iid == diagnosis.getId()) {
							buffer.append("<option value='" + diagnosis.getId() + "' selected='selected'>"
									+ diagnosis.getName() + "</option>");
						} else {

							buffer.append(
									"<option value='" + diagnosis.getId() + "'>" + diagnosis.getName() + "</option>");
						}
					}
					buffer.append("</select>");
					buffer.append("</td>");
					buffer.append(
							"<td><a href='#' onclick=deleteMoreCon('innertable') type='button'><i class='fa fa-trash'></i></td>");
					buffer.append("</tr>");

					index++;
				}

			}

			response.setContentType("text/html");
			response.setHeader("Cache-Control", "no-cache");
			response.getWriter().write("" + id + "~" + buffer.toString() + "");

		} catch (Exception e) {
			e.printStackTrace();
		} finally {

			connection.close();
		}

		return null;
	}

	public String updatecon() throws Exception {
		if (!verifyLogin(request)) {
			return "login";
		}
		Connection connection = null;
		try {
			connection = Connection_provider.getconnection();
			NotAvailableSlotDAO notAvailableSlotDAO = new JDBCNotAvailableSlotDAO(connection);

			// var url =
			// "updateconNotAvailableSlot?editAppointId="+editAppointId+"&patientId="+patientId+"&conid="+conid+"
			// ";
			String editAppointId = String.valueOf(notAvailableSlotForm.getApmtSlotId());
			String patientId = notAvailableSlotDAO.getClientId(editAppointId);
			String conid = notAvailableSlotForm.getConditionid();
			String moreconditions = "0";

			String lastmodified = DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone()).split(" ")[0];
			if (notAvailableSlotForm.getDiagnosises() != null) {
				int dd = notAvailableSlotDAO.deleteOpdConditionifExistsReport(editAppointId);
				for (Diagnosis diagnosis : notAvailableSlotForm.getDiagnosises()) {

					moreconditions = moreconditions + "," + diagnosis.getConditionid();
					dd = notAvailableSlotDAO.addOpdConditionReport(notAvailableSlotForm.getApmtSlotId(), patientId,
							diagnosis.getConditionid(), lastmodified);
				}
			}

			int changeDiagnosis = notAvailableSlotDAO.updateConsultationEmr(editAppointId, conid);
			if (!moreconditions.equals("0")) {
				int multiconditions = notAvailableSlotDAO.addMultiConditionstoEmr(editAppointId, moreconditions);
			}
			int apmt = notAvailableSlotDAO.updateApmtDiagnosis(editAppointId, conid);
			int cid = notAvailableSlotDAO.updateClientCondition(patientId, conid);

		} catch (Exception e) {
			e.printStackTrace();
		} finally {

			connection.close();
		}

		return "todayallUser";
	}

	public String setPendingAmountOfClient() throws Exception {
		if (!verifyLogin(request)) {
			return "login";
		}
		Connection connection = null;
		try {
			connection = Connection_provider.getconnection();
			DiaryManagementDAO diaryManagementDAO = new JDBCDiaryManagentDAO(connection);

			String clientId = request.getParameter("clientId");

			/*
			 * double balanceTotal =
			 * notAvailableSlotDAO.getPendingBalanceTotal(clientId); String
			 * btotal = DateTimeUtils.changeFormat(balanceTotal);
			 */

			double debit = diaryManagementDAO.getClientDebitTotal(clientId);
			double payment = diaryManagementDAO.getClientPayment(clientId);

			double balance = debit - payment;

			response.setContentType("text/html");
			response.setHeader("Cache-Control", "no-cache");
			response.getWriter().write("" + DateTimeUtils.changeFormat(balance) + "");

		} catch (Exception e) {
			e.printStackTrace();
		} finally {

			connection.close();
		}

		return null;
	}

	public String department() throws Exception {
		if (!verifyLogin(request)) {
			return "login";
		}
		Connection connection = null;
		try {
			connection = Connection_provider.getconnection();
			NotAvailableSlotDAO notAvailableSlotDAO = new JDBCNotAvailableSlotDAO(connection);
			ClientDAO clientDAO = new JDBCClientDAO(connection);

			String location = request.getParameter("location");
			// System.out.println(location);

			ArrayList<Client> condtitionList = clientDAO.getDepartmentTreatmentTypeList(location);

			StringBuffer str = new StringBuffer();

			str.append(
					"<select id='condition' name='treatmentType' class='form-control showToolTip chosen amber' tabindex='115'"
							+ "data-toggle='tooltip' onchange='updateClientCondition(this.value)'>");

			str.append("<option value='0'>Select Condition</option>");
			for (Client client : condtitionList) {
				str.append("<option value='" + client.getId() + "'>" + client.getTreatmentType() + "</option>");
			}

			str.append("</select>");

			response.setContentType("text/html");
			response.setHeader("Cache-Control", "no-cache");
			response.getWriter().write("" + str.toString() + "");

		} catch (Exception e) {
			// TODO: handle exception
		} finally {

			connection.close();
		}
		return null;
	}

	public String getConditionPatient() throws Exception {
		if (!verifyLogin(request)) {
			return "login";
		}
		Connection connection = null;
		try {
			connection = Connection_provider.getconnection();
			NotAvailableSlotDAO notAvailableSlotDAO = new JDBCNotAvailableSlotDAO(connection);

			String clientId = request.getParameter("clientId");
			String id = notAvailableSlotDAO.getConditionPatient(clientId);

			response.setContentType("text/html");
			response.setHeader("Cache-Control", "no-cache");
			response.getWriter().write("" + id + "");

		} catch (Exception e) {
			e.printStackTrace();
		} finally {

			connection.close();
		}

		return null;
	}

	public String rewraped() throws Exception {

		if (!verifyLogin(request)) {
			return "login";
		}
		Connection connection = null;
		try {

			String eventid = request.getParameter("id");

			connection = Connection_provider.getconnection();
			NotAvailableSlotDAO notAvailableSlotDAO = new JDBCNotAvailableSlotDAO(connection);
			DiaryManagementDAO diaryManagementDAO = new JDBCDiaryManagentDAO(connection);
			ClientDAO clientDAO = new JDBCClientDAO(connection);
			String opendb = (String) session.getAttribute("openedb");
			ArrayList<NotAvailableSlot> wrapedLiest = diaryManagementDAO.getWrapedEventData(eventid, opendb);

			String str = "";

			for (NotAvailableSlot notAvailableSlot : wrapedLiest) {

				if (notAvailableSlot.getImagename() == null) {
					notAvailableSlot.setImagename("");
				}

				Client client = clientDAO.getPatientBMIData(notAvailableSlot.getClientId(), Integer.parseInt(eventid));

				str = notAvailableSlot.getId() + "#" + notAvailableSlot.getClientName() + "#"
						+ notAvailableSlot.getApmtType() + "#" + notAvailableSlot.getSTime() + "#"
						+ notAvailableSlot.getEndTime() + "#" + notAvailableSlot.getDuration() + "#"
						+ notAvailableSlot.getStatus() + "#" + notAvailableSlot.getNotes() + "#"
						+ notAvailableSlot.getArrivedStatus() + "#" + notAvailableSlot.isDna() + "#"
						+ notAvailableSlot.getClientId() + "#" + notAvailableSlot.getCommencing() + "#"
						+ notAvailableSlot.getDiaryUserId() + "#" + notAvailableSlot.getCharge() + "#"
						+ notAvailableSlot.getReasonforblock() + "#" + notAvailableSlot.getTreatmentEpisodeId() + "#"
						+ notAvailableSlot.getApmttypetext() + "#" + notAvailableSlot.getClientName() + "#"
						+ notAvailableSlot.getUsedsession() + "#" + notAvailableSlot.getTptypeid() + "#"
						+ notAvailableSlot.getTpnameid() + "#" + notAvailableSlot.getPractitionerEmail() + "#"
						+ notAvailableSlot.getClientEmail() + "#" + notAvailableSlot.getLocid() + "#"
						+ notAvailableSlot.getLocation() + "#" + notAvailableSlot.getCondition() + "#"
						+ notAvailableSlot.getWhopay() + "#" + notAvailableSlot.isAppointmentCompleted() + "#"
						+ notAvailableSlot.isChargeCompleted() + "#" + notAvailableSlot.getDiaryUser() + "#"
						+ notAvailableSlot.getApmtSlotId() + "#" + notAvailableSlot.getTpName() + "#"
						+ loginInfo.getEmail() + "#" + notAvailableSlot.getOtid() + "#"
						+ notAvailableSlot.getImagename() + "#" + client.getHeight() + "#" + client.getWeight() + "#"
						+ client.getBmi() + "#" + client.getPulse() + "#" + client.getSysbp() + "#" + client.getDiabp()
						+ "#" + notAvailableSlot.getOtplan() + "#" + notAvailableSlot.getProcedure() + "#"
						+ notAvailableSlot.getSurgeon() + "#" + notAvailableSlot.getAnesthesia() + "#"
						+ notAvailableSlot.getIpdno() + "#" + notAvailableSlot.getWardid() + "#"
						+ notAvailableSlot.getAsistantdoclist() + "#" + notAvailableSlot.getToken() + "#"
						+ client.getSugarfasting() + "#" + client.getPostmeal() + "#" + client.getTemprature() + "#"
						+ client.getSpo() + "#" + client.getHead_cir();

				response.setContentType("text/html");
				response.setHeader("Cache-Control", "no-cache");
				response.getWriter().write(str);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {

			connection.close();
		}

		return null;
	}

	public String dischistory() throws Exception {
		// var url = "dischistoryNotAvailableSlot?apmtid="+editAppointId+" ";
		if (!verifyLogin(request)) {
			return "login";
		}
		Connection connection = null;
		try {
			connection = Connection_provider.getconnection();
			NotAvailableSlotDAO notAvailableSlotDAO = new JDBCNotAvailableSlotDAO(connection);

			String apmtid = request.getParameter("apmtid");

			ConsultationNoteDAO consultationNoteDAO = new JDBCConsultationNoteDAO(connection);
			String treatmentEpisodeid = consultationNoteDAO.getTreatmentEpisodeid(apmtid);

			TreatmentEpisode treatmentEpisode = consultationNoteDAO
					.getTreatmentEpisodeDischargeData(treatmentEpisodeid);

			String str = treatmentEpisode.getTrtmentStatus() + "#" + treatmentEpisode.getOutcomes() + "#"
					+ treatmentEpisode.getDschargestatus() + "#" + treatmentEpisodeid;

			response.setContentType("text/html");
			response.setHeader("Cache-Control", "no-cache");
			response.getWriter().write(str);

		} catch (Exception e) {
			e.printStackTrace();
		} finally {

			connection.close();
		}

		return null;
	}

	public String consnote() throws Exception {

		if (!verifyLogin(request)) {
			return "login";
		}
		Connection connection = null;
		try {
			connection = Connection_provider.getconnection();
			NotAvailableSlotDAO notAvailableSlotDAO = new JDBCNotAvailableSlotDAO(connection);
			EmrDAO emrDAO = new JDBCEmrDAO(connection);

			String apmtid = request.getParameter("apmtid");
			Emr emr = emrDAO.getAppointmentidConsultationNote(apmtid);
			// System.out.println(apmtid);

			int connoteid = emr.getId();
			session.setAttribute("connoteid", connoteid);
			// System.out.println(connoteid);

			response.setContentType("text/html");
			response.setHeader("Cache-Control", "no-cache");
			response.getWriter().write(emr.getConsNote());

		} catch (Exception e) {
			e.printStackTrace();
		} finally {

			connection.close();
		}

		return null;
	}

	public String discharge() throws SQLException {
		// var url =
		// "dischargeNotAvailableSlot?consnote="+notes+"&apmtid="+editAppointId+"&outcomes="+outcomes+"&dschargestatus="+dschargestatus+"&chkdischarge="+chkdischarge+"
		// ";
		if (!verifyLogin(request)) {
			return "login";
		}
		Connection connection = null;
		try {
			connection = Connection_provider.getconnection();
			NotAvailableSlotDAO notAvailableSlotDAO = new JDBCNotAvailableSlotDAO(connection);
			EmrDAO emrDAO = new JDBCEmrDAO(connection);

			String consnote = request.getParameter("consnote");
			String apmtid = request.getParameter("apmtid");
			String outcomes = request.getParameter("outcomes");
			String dschargestatus = request.getParameter("dschargestatus");
			boolean chkdischarge = Boolean.parseBoolean(request.getParameter("chkdischarge"));

			NotAvailableSlot notAvailableSlot = notAvailableSlotDAO.getApmtConsNoteData(apmtid);

			Emr emr = new Emr();

			emr.setPatientId(Integer.parseInt(notAvailableSlot.getClientId()));
			emr.setPractitionerId(notAvailableSlot.getDiaryUserId());
			emr.setCondition_id(notAvailableSlot.getCondition());
			emr.setAppointmentid(Integer.parseInt(apmtid));
			emr.setConsNote(consnote);

			int connoteid = (Integer) session.getAttribute("connoteid");

			if (connoteid == 0) {
				int result = emrDAO.savePatientConsultationNote(emr,
						DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone()));
			} else {
				int res = emrDAO.updatePatientConsultationNote(emr, connoteid,
						DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone()));
			}

			// update treatment episode discharge status
			int status = 0;
			if (chkdischarge) {
				status = 1;
			}
			ConsultationNoteDAO consultationNoteDAO = new JDBCConsultationNoteDAO(connection);
			String treatmentEpisodeid = notAvailableSlot.getTreatmentEpisodeId();
			int upd = emrDAO.updateTreatmentEpisodeSischargeStatus(outcomes, dschargestatus, status, treatmentEpisodeid,
					DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone()), "", "", "", "", "", "");

			if (status == 1) {
				AllTemplateAction allTemplateAction = new AllTemplateAction();
				allTemplateAction.sendDischargeEmail(notAvailableSlot.getClientId(), notAvailableSlot.getCondition(),
						Integer.toString(notAvailableSlot.getDiaryUserId()), apmtid, loginInfo, connection,
						treatmentEpisodeid);
			}

			// System.out.println(consnote);

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			connection.close();
		}

		return null;
	}

	public String loggedinuser() throws Exception {

		if (!verifyLogin(request)) {
			return "login";
		}

		Connection connection = null;
		try {
			connection = DriverManager.getConnection("" + Constants.DB_HOST + ":3306/admin", ""+Constants.DB_USER+"", ""+Constants.DB_PWD+"");
			NotAvailableSlotDAO notAvailableSlotDAO = new JDBCNotAvailableSlotDAO(connection);
			String apmuserlist = notAvailableSlotDAO.getApmLoggedUserList(loginInfo.getDbName());
			session.setAttribute("apmuserlist", apmuserlist);

			response.setContentType("text/html");
			response.setHeader("Cache-Control", "no-cache");
			response.getWriter().write(apmuserlist);

		} catch (Exception e) {
			e.printStackTrace();
		} finally {

			connection.close();
		}

		return null;
	}

	public String currency() throws IOException {

		response.setContentType("text/html");
		response.setHeader("Cache-Control", "no-cache");
		response.getWriter().write(Constants.getCurrency(loginInfo));

		return null;
	}

	public String mdicine() throws Exception {

		if (!verifyLogin(request)) {
			return "login";
		}
		Connection connection = null;
		try {
			connection = Connection_provider.getconnection();
			NotAvailableSlotDAO notAvailableSlotDAO = new JDBCNotAvailableSlotDAO(connection);

			String selectedid = request.getParameter("selectedid");

			ArrayList<Master> mdicineList = notAvailableSlotDAO.getMedicineList(selectedid);

			StringBuffer str = new StringBuffer();
			/* str.append(" <label for='exampleInputEmail1'>M.Name</label>"); */
			str.append("<select class='form-control showToolTip chosen' name='mdicinename' id='mdicinename'>");
			str.append("<option value='0'>Select Medicine</option>");
			for (Master master : mdicineList) {
				str.append("<option value='" + master.getId() + "'>" + master.getGenericname() + "</option>");
			}

			str.append("</select>");
			response.setContentType("text/html");
			response.setHeader("Cache-Control", "no-cache");
			response.getWriter().write(str.toString());

		} catch (Exception e) {
			e.printStackTrace();
		} finally {

			connection.close();
		}

		return null;
	}

	public String updateClientCondition() throws Exception {
		if (!verifyLogin(request)) {
			return "login";
		}
		Connection connection = null;
		try {
			connection = Connection_provider.getconnection();
			NotAvailableSlotDAO notAvailableSlotDAO = new JDBCNotAvailableSlotDAO(connection);

			String clientId = request.getParameter("clientId");
			String conditionID = request.getParameter("id");
			int update = notAvailableSlotDAO.updateCondition(clientId, conditionID);

		} catch (Exception e) {
			e.printStackTrace();
		} finally {

			connection.close();
		}

		return null;
	}

	public String searchconditions() throws Exception {

		Connection connection = null;

		StringBuffer buffer = new StringBuffer();
		try {
			connection = Connection_provider.getconnection();
			String ids = request.getParameter("ids");
			String text = request.getParameter("text");

			String str[] = ids.split(",");

			NotAvailableSlotDAO availableSlotDAO = new JDBCNotAvailableSlotDAO(connection);

			ArrayList<Diagnosis> diagnosisList = availableSlotDAO.getMultipleConditions(text);

			if (ids == "0") {

				for (Diagnosis diagnosis : diagnosisList) {

					buffer.append("<input class='case' type='checkbox'  id='ch" + diagnosis.getId() + "' name='ch"
							+ diagnosis.getId() + "' onclick='tempstore(" + diagnosis.getId() + ")' value='"
							+ diagnosis.getId() + "'>");
					buffer.append(diagnosis.getName());
					buffer.append("<br>");
				}
			} else {

				for (String sid : ids.split(",")) {

					if (sid == "0") {
						continue;
					}

					int tid = Integer.parseInt(sid);

					for (Diagnosis diagnosis : diagnosisList) {

						if (tid == diagnosis.getId()) {
							buffer.append("<input class='case' type='checkbox'  id='ch" + diagnosis.getId()
									+ "' name='ch" + diagnosis.getId() + "' onclick='tempstore(" + diagnosis.getId()
									+ ")' checked='checked' value='" + diagnosis.getId() + "'>");
							buffer.append(diagnosis.getName());
							buffer.append("<br>");
						} else {
							buffer.append("<input class='case' type='checkbox'  id='ch" + diagnosis.getId()
									+ "' name='ch" + diagnosis.getId() + "' onclick='tempstore(" + diagnosis.getId()
									+ ")' value='" + diagnosis.getId() + "'>");
							buffer.append(diagnosis.getName());
							buffer.append("<br>");
						}
					}

				}

			}

			response.setContentType("text/html");
			response.setHeader("Cache-Control", "no-cache");
			response.getWriter().write(buffer.toString());

		} catch (Exception e) {

			e.printStackTrace();
		} finally {

			connection.close();
		}
		return null;
	}

	public String addnewrow() throws Exception {

		Connection connection = null;

		try {
			connection = Connection_provider.getconnection();

			NotAvailableSlotDAO notAvailableSlotDAO = new JDBCNotAvailableSlotDAO(connection);
			String rowcount = request.getParameter("rowcount");

			StringBuffer buffer = new StringBuffer();
			int index = Integer.parseInt(rowcount);

			ArrayList<Diagnosis> list = notAvailableSlotDAO.getMultipleConditions("");
			buffer.append("<tr>");
			buffer.append("<td>");
			buffer.append("<select class='form-control chosen-select' name='diagnosises[" + index + "].conditionid'>");
			buffer.append("<option value='0'>Select Condition</option>");
			for (Diagnosis diagnosis : list) {

				buffer.append("<option value='" + diagnosis.getId() + "'>" + diagnosis.getName() + "</option>");
			}
			buffer.append("</select>");
			buffer.append("</td>");
			buffer.append(
					"<td><a href='#' onclick=deleteMoreCon('innertable') type='button'><i class='fa fa-trash'></i></td>");
			buffer.append("</tr>");

			response.setContentType("text/html");
			response.setHeader("Cache-Control", "no-cache");
			response.getWriter().write(buffer.toString());

		} catch (Exception e) {

			e.printStackTrace();

		} finally {

			connection.close();
		}

		return null;
	}

	public String blankletterhead() throws Exception {

		Connection connection = null;
		try {
			connection = Connection_provider.getconnection();
			AccountsDAO accountsDAO = new JDBCAccountsDAO(connection);
			NotAvailableSlotDAO availableSlotDAO = new JDBCNotAvailableSlotDAO(connection);
			String clientid = request.getParameter("patientid");
			String appointmentid = request.getParameter("appointmentid");// appointmentid="+editAppointId+"
			int isfromlmhreg=0;
			if(loginInfo.isLmh()){
				String lmh_register_patientid = (String) session.getAttribute("lmh_register_patientid");
				if(!DateTimeUtils.isNull(lmh_register_patientid).equals("")){
					clientid = lmh_register_patientid;
					session.removeAttribute("lmh_register_patientid");
					isfromlmhreg =1;
				}
				String lmh_register_apmtid = (String) session.getAttribute("lmh_register_apmtid");
				if(!DateTimeUtils.isNull(lmh_register_apmtid).equals("")){
					appointmentid = lmh_register_apmtid;
					session.removeAttribute("lmh_register_apmtid");
					isfromlmhreg =1;
				}
			}
			notAvailableSlotForm.setIsfromlmhreg(isfromlmhreg);
			if (appointmentid == null) {
				appointmentid = "0";
			}
			int lastapmt = 0;
			if (appointmentid.equals("0")) {
				lastapmt = availableSlotDAO.getLastAppointmentId(clientid);
				appointmentid = String.valueOf(lastapmt);
			}

			String opdidagnosis = availableSlotDAO.getAllDiagnosisofOpd("" + appointmentid);
			if (opdidagnosis != null) {
				if (!opdidagnosis.equals("")) {
					notAvailableSlotForm.setFinalDiagnosis("" + opdidagnosis);
					session.setAttribute("finaldiagnosis", notAvailableSlotForm.getFinalDiagnosis());
				}
			}
			ClientDAO clientDAO = new JDBCClientDAO(connection);
			MasterDAO masterDAO = new JDBCMasterDAO(connection);

			Client client = clientDAO.getClientDetails(clientid);

			Clinic clinic = new Clinic();
			ClinicDAO clinicDAO = new JDBCClinicDAO(connection);
			UserProfileDAO userProfileDAO = new JDBCUserProfileDAO(connection);

			String diaryuserid = availableSlotDAO.getDiaryUserId(appointmentid);
			String fullname = "";
			if (client.getMiddlename() == "" || client.getMiddlename() == null) {
				fullname = client.getTitle() + " " + client.getFirstName() + " " + client.getLastName();
			} else {
				if(loginInfo.isAyushman() ) {
					fullname = client.getTitle() + " " + client.getFirstName() + " " + client.getLastName();
				}else {
					fullname = client.getTitle() + " " + client.getFirstName() + " " + client.getMiddlename() + " "
							+ client.getLastName();
				}
				
				/*
				 * fullname = client.getTitle() + " " + client.getFirstName() + " " +
				 * client.getMiddlename() + " " + client.getLastName();
				 */
			}
			ArrayList<Clinic> locationAdressList = accountsDAO.getLocationAddress(loginInfo.getClinicid());
			// String agegender=client.getAge()+"/"+client.getGender();
			String agegender = "";
			String dob = client.getDob();
			String age2 = DateTimeUtils.getAge1(client.getDob());
			/*
			 * if(Integer.parseInt(age2)<2){ if(Integer.parseInt(age2)<1){
			 * String monthdays= DateTimeUtils.getMonthandDays(client.getDob());
			 * agegender=monthdays; }else{ String monthdays=
			 * DateTimeUtils.getMonthandDays(client.getDob()); agegender= age2 +
			 * " Years" + " "+monthdays; } } else { agegender = age2; }
			 */
			String address = client.getAddress() + "," + client.getTown() + "(" + client.getPostCode() + "), "+client.getCounty()+ ", "
					+ client.getCountry();
			Client bmi = clientDAO.getPatientBMIData(clientid, Integer.parseInt(appointmentid));

			UserProfile userProfile = userProfileDAO.getUserprofileDetails(Integer.parseInt(diaryuserid));
			Master master = masterDAO.getDisciplineData(userProfile.getDiciplineName());
			clinic = clinicDAO.getCliniclistDetails(loginInfo.getClinicid());
			notAvailableSlotForm.setAgegender(age2 + "/" + client.getGender());
			notAvailableSlotForm.setAbrivationid(client.getAbrivationid());
			notAvailableSlotForm.setClientId(clientid);
			notAvailableSlotForm.setCity(address);
			notAvailableSlotForm.setWebsiteUrl(clinic.getWebsiteUrl());
			notAvailableSlotForm.setClinicName(clinic.getClinicName());
			notAvailableSlotForm.setUserQualification(userProfile.getQualification());
			notAvailableSlotForm.setClinicemail(clinic.getEmail());
			notAvailableSlotForm.setClinicaddress(clinic.getAddress());
			notAvailableSlotForm.setClinicPhone(clinic.getLandLine());
			notAvailableSlotForm.setMobile(client.getMobNo());
			notAvailableSlotForm.setPractitonerName(userProfile.getFullname());
			notAvailableSlotForm.setJobtitle(master.getDiscipline());
			notAvailableSlotForm.setOwner_qualification(clinic.getOwner_qualification());
			notAvailableSlotForm.setSTime(clinic.getStarttime());
			notAvailableSlotForm.setEndTime(clinic.getEndtime());
			notAvailableSlotForm.setClient(fullname);
			notAvailableSlotForm.setFathername(client.getMiddlename());
			notAvailableSlotForm.setRegisterno(clinic.getRegisterno());
			notAvailableSlotForm.setWeight(bmi.getWeight());
			notAvailableSlotForm.setHeight(bmi.getHeight());
			notAvailableSlotForm.setPulse(bmi.getPulse());
			notAvailableSlotForm.setSysbp(bmi.getSysbp());
			notAvailableSlotForm.setDiabp(bmi.getDiabp());
			notAvailableSlotForm.setOpdid(appointmentid);
			notAvailableSlotForm.setClinicLogo(clinic.getUserImageFileName());
			notAvailableSlotForm.setBmi(bmi.getBmi());
			notAvailableSlotForm.setTempr(bmi.getTemprature());
			notAvailableSlotForm.setSpo2(bmi.getSpo());
			notAvailableSlotForm.setBsa(bmi.getHead_cir());
			// notAvailableSlotForm.setJobtitle(loginInfo.getJobTitle());
			notAvailableSlotForm.setPatientBmi(bmi);
			notAvailableSlotForm.setAllergynotes(bmi.getAllergynotes());
			String dd[] = DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone()).split(" ");
			String printedBy = loginInfo.getUserId() + " " + DateTimeUtils.getCommencingDate1(dd[0]) + " " + dd[1];
			notAvailableSlotForm.setPrintedBy(printedBy);

			notAvailableSlotForm.setBlankletterhead(userProfile.getBlankletterhead());
			notAvailableSlotForm.setUseregno(userProfile.getRegisterno());
			Client charge=availableSlotDAO.getChargebyapmtid(appointmentid);
			notAvailableSlotForm.setCharges(charge.getCharges());
			notAvailableSlotForm.setApmtType(charge.getApmttypetext());
			notAvailableSlotForm.setCommencing(DateTimeUtils.getCommencingDate1(charge.getCommencing()));
			notAvailableSlotForm.setSTime(charge.getStime());
			
			String reqdatetime=availableSlotDAO.getdatetimebyid(clientid,appointmentid);
			String reqdate=reqdatetime.split(" ")[0];
			String tokenno=accountsDAO.getTokennoforOPD(Integer.parseInt(appointmentid),Integer.parseInt(diaryuserid),reqdate);
		    notAvailableSlotForm.setTokenno(tokenno);
			reqdate=DateTimeUtils.getCommencingDate1(reqdate);
			String time=reqdatetime.split(" ")[1];
			reqdatetime=reqdate+" | "+time;
		    notAvailableSlotForm.setReqdatetime(reqdatetime);
		    
		    

		} catch (Exception e) {

			e.printStackTrace();
		} finally {

			connection.close();
		}
        
		if(loginInfo.isAyushman() ) {
		 return "blankLetterHeadayush";	
		}else if(loginInfo.isTotehosp()){
		 return "blankletterheadtote";	
		}else {
		return "blankLetterHead";
		}

	}
	

	// public String savebmi() throws Exception {
	//
	// Connection connection=null;
	// try {
	// connection=Connection_provider.getconnection();
	//// / var
	// url="savebmiNotAvailableSlot?height="+height+"&weight="+weight+"&bmi="+bmi+"&pulse="+pulse+"&sysbp="+sysbp+"&diabp="+diabp+"";
	// ClientDAO clientDAO=new JDBCClientDAO(connection);
	// String height=request.getParameter("height");
	// String weight=request.getParameter("weight");
	// String bmi=request.getParameter("bmi");
	// String pulse=request.getParameter("pulse");
	// String sysbp=request.getParameter("sysbp");
	// String diabp=request.getParameter("diabp");
	// String patientId=request.getParameter("patientId");
	// String sugarfasting=request.getParameter("sugarfasting");
	// String postmeal=request.getParameter("postmeal");
	// String temprature=request.getParameter("temprature");
	// String spo=request.getParameter("spo");
	// String head_cir=request.getParameter("bsa");
	// Client client=new Client();
	// client.setHeight(height);
	// client.setWeight(weight);
	// client.setBmi(bmi);
	// client.setPulse(pulse);
	// client.setSysbp(sysbp);
	// client.setDiabp(diabp);
	// client.setClientid(patientId);
	// client.setSugarfasting(sugarfasting);
	// client.setPostmeal(postmeal);
	// client.setTemprature(temprature);
	// client.setSpo(spo);
	// client.setHead_cir(head_cir);
	// int result=clientDAO.saveBMIPatient(client);
	//
	// Client client2 = clientDAO.getClientDetails(patientId);
	// int ageinmonth = DateTimeUtils.getmonthsfromdob(client2.getDob());
	// if(ageinmonth<=60 && ageinmonth>=0){
	// if(height!=null){
	// if(!height.equals("") && !height.equals("0")){
	// Client client4 = new Client();
	// client4.setHeightdata(height);
	// client4.setWeightdata(weight);
	// client4.setBmidata(bmi);
	// client4.setHeadcircumferncedata(head_cir);
	// client4.setClientid(patientId);
	// client4.setMonth(""+ageinmonth);
	// client4.setUserid(loginInfo.getUserId());
	// String datetime =
	// DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone());
	// client4.setDate(datetime);
	// int id = clientDAO.check_child_growth_data(patientId, ""+ageinmonth);
	// int res1 = 0;
	// if (id > 0) {
	// client4.setId(id);
	// // update
	// res1 = clientDAO.updateChildGrowthData(client4, "height");
	// } else {
	// // insert
	// res1 = clientDAO.saveChildGrowthData(client4, "height");
	// }
	// }
	// }
	// if(weight!=null){
	// if(!weight.equals("") && !weight.equals("0")){
	// Client client4 = new Client();
	// client4.setHeightdata(height);
	// client4.setWeightdata(weight);
	// client4.setBmidata(bmi);
	// client4.setHeadcircumferncedata(head_cir);
	// client4.setClientid(patientId);
	// client4.setMonth(""+ageinmonth);
	// client4.setUserid(loginInfo.getUserId());
	// String datetime =
	// DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone());
	// client4.setDate(datetime);
	// int id = clientDAO.check_child_growth_data(patientId, ""+ageinmonth);
	// int res1 = 0;
	// if (id > 0) {
	// client4.setId(id);
	// // update
	// res1 = clientDAO.updateChildGrowthData(client4, "weight");
	// } else {
	// // insert
	// res1 = clientDAO.saveChildGrowthData(client4, "weight");
	// }
	// }
	// }
	// if(bmi!=null){
	// if(!bmi.equals("") && !bmi.equals("0")){
	// Client client4 = new Client();
	// client4.setHeightdata(height);
	// client4.setWeightdata(weight);
	// client4.setBmidata(bmi);
	// client4.setHeadcircumferncedata(head_cir);
	// client4.setClientid(patientId);
	// client4.setMonth(""+ageinmonth);
	// client4.setUserid(loginInfo.getUserId());
	// String datetime =
	// DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone());
	// client4.setDate(datetime);
	// int id = clientDAO.check_child_growth_data(patientId, ""+ageinmonth);
	// int res1 = 0;
	// if (id > 0) {
	// client4.setId(id);
	// // update
	// res1 = clientDAO.updateChildGrowthData(client4, "bmi");
	// } else {
	// // insert
	// res1 = clientDAO.saveChildGrowthData(client4, "bmi");
	// }
	// }
	// }
	// if(head_cir!=null){
	// if(!head_cir.equals("") && !head_cir.equals("0")){
	// Client client4 = new Client();
	// client4.setHeightdata(height);
	// client4.setWeightdata(weight);
	// client4.setBmidata(bmi);
	// client4.setHeadcircumferncedata(head_cir);
	// client4.setClientid(patientId);
	// client4.setMonth(""+ageinmonth);
	// client4.setUserid(loginInfo.getUserId());
	// String datetime =
	// DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone());
	// client4.setDate(datetime);
	// int id = clientDAO.check_child_growth_data(patientId, ""+ageinmonth);
	// int res1 = 0;
	// if (id > 0) {
	// client4.setId(id);
	// // update
	// res1 = clientDAO.updateChildGrowthData(client4, "headcircumfernce");
	// } else {
	// // insert
	// res1 = clientDAO.saveChildGrowthData(client4, "headcircumfernce");
	// }
	// }
	// }
	// }
	//
	// response.setContentType("text/html");
	// response.setHeader("Cache-Control", "no-cache");
	// response.getWriter().write("");
	//
	// } catch (Exception e) {
	//
	// e.printStackTrace();
	// }finally{
	//
	// connection.close();
	// }
	//
	// return null;
	// }

	public String getbmidata() throws Exception {

		Connection connection = null;

		try {
			connection = Connection_provider.getconnection();
			ClientDAO clientDAO = new JDBCClientDAO(connection);
			String clientid = request.getParameter("clientid");
			String apmtid = request.getParameter("apmtid");

			Client client = clientDAO.getPatientBMIData(clientid, Integer.parseInt(apmtid));

			String data = client.getHeight() + "~" + client.getWeight() + "~" + client.getBmi() + "~"
					+ client.getPulse() + "~" + client.getSysbp() + "~" + client.getDiabp() + "~"
					+ client.getSugarfasting() + "~" + client.getPostmeal() + "~" + client.getTemprature() + "~"
					+ client.getSpo() + "~" + client.getHead_cir();

			response.setContentType("text/html");
			response.setHeader("Cache-Control", "no-cache");
			response.getWriter().write(data);

		} catch (Exception e) {

			e.printStackTrace();
		} finally {

			connection.close();
		}

		return null;
	}

	public String mrd() {

		return "mrd";
	}

	public void setotformdata(String apmtid) throws Exception {
		Connection connection = null;
		try {
			connection = Connection_provider.getconnection();
			NotAvailableSlotDAO notAvailableSlotDAO = new JDBCNotAvailableSlotDAO(connection);
			IpdDAO ipddao = new JDBCIpdDAO(connection);
			NotAvailableSlot np = notAvailableSlotDAO.getOTData(apmtid);

			notAvailableSlotForm.setIpdno(np.getIpdno());
			notAvailableSlotForm.setClient(np.getClientName());
			notAvailableSlotForm.setAgegender(np.getAgegender());
			notAvailableSlotForm.setWardbed(np.getWardbed());
			notAvailableSlotForm.setOperation(np.getApmttypetext());
			notAvailableSlotForm.setSurgeon(np.getSurgeon());
			notAvailableSlotForm.setAnesthesia(np.getAnesthesia());
			notAvailableSlotForm.setAsistantdoclist(np.getAsistantdoclist());
			notAvailableSlotForm.setCommencing(DateTimeUtils.getCommencingDate2(np.getCommencing()));
			notAvailableSlotForm.setAdmitdate(np.getAdmitdate());
			notAvailableSlotForm.setId(Integer.parseInt(apmtid));
			notAvailableSlotForm.setProcedure(np.getProcedure());
			notAvailableSlotForm.setSurgeonid(np.getSurgeonid());

			notAvailableSlotForm.setTimeofincision(np.getTimeofincision());
			notAvailableSlotForm.setAnsintime(np.getAnsintime());

			String otnotes = np.getOtnotes();
			session.setAttribute("otnotes", otnotes);
			notAvailableSlotForm.setOtnotes(np.getOtnotes());

			String imageData = np.getImgdata();
			session.setAttribute("imageData", imageData);

			notAvailableSlotForm.setAbrivationid(np.getAbrivationid());
			notAvailableSlotForm.setAddress(np.getAddress());
			String mob = np.getMobno();
			notAvailableSlotForm.setMobile(np.getMobno());
			if (loginInfo.getIpd_abbr_access() == 1) {
				String newipdabbr = ipddao.getIpdAbrivationIds(Integer.parseInt(np.getIpdid()));
				notAvailableSlotForm.setNewipdabbr(newipdabbr);
				notAvailableSlotForm.setIpdid(np.getIpdid());
			} else {
				notAvailableSlotForm.setIpdid(np.getIpdid());
				notAvailableSlotForm.setNewipdabbr((np.getIpdid()));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public String otemplate() throws Exception {
		String id = request.getParameter("id");

		Connection connection = null;
		try {
			connection = Connection_provider.getconnection();
			NotAvailableSlotDAO notAvailableSlotDAO = new JDBCNotAvailableSlotDAO(connection);
			String text = notAvailableSlotDAO.getTemplateText(id);

			response.setContentType("text/html");
			response.setHeader("Cache-Control", "no-cache");
			response.getWriter().write(text);
		} catch (Exception e) {
			// TODO: handle exception
		} finally {

			connection.close();
		}

		return null;
	}

	public String saveoptimalform() throws Exception {
		Connection connection = null;
		try {
			connection = Connection_provider.getconnection();
			NotAvailableSlotDAO notAvailableSlotDAO = new JDBCNotAvailableSlotDAO(connection);
			AccountsDAO accountsDAO = new JDBCAccountsDAO(connection);
			NotAvailableSlotDAO availableSlotDAO = new JDBCNotAvailableSlotDAO(connection);

			ClientDAO clientDAO = new JDBCClientDAO(connection);
			MasterDAO masterDAO = new JDBCMasterDAO(connection);
			NotAvailableSlot notAvailableSlot = new NotAvailableSlot();
			notAvailableSlot.setRe_unaided_d(notAvailableSlotForm.getRe_unaided_d());
			notAvailableSlot.setRe_unaided_n(notAvailableSlotForm.getRe_unaided_n());
			notAvailableSlot.setRe_withglass_d(notAvailableSlotForm.getRe_withglass_d());
			notAvailableSlot.setRe_withglass_n(notAvailableSlotForm.getRe_withglass_n());
			notAvailableSlot.setRe_gtph_d(notAvailableSlotForm.getRe_gtph_d());
			notAvailableSlot.setRe_gtph_n(notAvailableSlotForm.getRe_gtph_n());
			notAvailableSlot.setLe_unaided_d(notAvailableSlotForm.getLe_unaided_d());
			notAvailableSlot.setLe_unaided_n(notAvailableSlotForm.getLe_unaided_n());
			notAvailableSlot.setLe_withglass_d(notAvailableSlotForm.getLe_withglass_d());
			notAvailableSlot.setLe_withglass_n(notAvailableSlotForm.getLe_withglass_n());
			notAvailableSlot.setLe_gtph_d(notAvailableSlotForm.getLe_gtph_d());
			notAvailableSlot.setLe_gtph_n(notAvailableSlotForm.getLe_gtph_n());
			notAvailableSlot.setAir_r(notAvailableSlotForm.getAir_r());
			notAvailableSlot.setAir_l(notAvailableSlotForm.getAir_l());
			notAvailableSlot.setPerkins_r(notAvailableSlotForm.getPerkins_r());
			notAvailableSlot.setPerkins_l(notAvailableSlotForm.getPerkins_l());
			notAvailableSlot.setAppl_r(notAvailableSlotForm.getAppl_r());
			notAvailableSlot.setAppl_l(notAvailableSlotForm.getAppl_l());

			notAvailableSlot.setKeratometry1(notAvailableSlotForm.getKeratometry1());
			notAvailableSlot.setKeratometry2(notAvailableSlotForm.getKeratometry2());
			notAvailableSlot.setKeratometry3(notAvailableSlotForm.getKeratometry3());
			notAvailableSlot.setKeratometry4(notAvailableSlotForm.getKeratometry4());
			notAvailableSlot.setKeratometry5(notAvailableSlotForm.getKeratometry5());
			notAvailableSlot.setKeratometry6(notAvailableSlotForm.getKeratometry6());
			notAvailableSlot.setKeratometry7(notAvailableSlotForm.getKeratometry7());
			notAvailableSlot.setKeratometry8(notAvailableSlotForm.getKeratometry8());

			notAvailableSlot.setRe_usingglass_s(notAvailableSlotForm.getRe_usingglass_s());
			notAvailableSlot.setRe_usingglass_c(notAvailableSlotForm.getRe_usingglass_c());
			notAvailableSlot.setRe_usingglass_a(notAvailableSlotForm.getRe_usingglass_a());
			notAvailableSlot.setRe_usingglass_va(notAvailableSlotForm.getRe_usingglass_va());
			notAvailableSlot.setRe_usingglass_nv(notAvailableSlotForm.getRe_usingglass_nv());
			notAvailableSlot.setRe_usingglass_add(notAvailableSlotForm.getRe_usingglass_add());

			notAvailableSlot.setRe_ar_s(notAvailableSlotForm.getRe_ar_s());
			notAvailableSlot.setRe_ar_c(notAvailableSlotForm.getRe_ar_c());
			notAvailableSlot.setRe_ar_a(notAvailableSlotForm.getRe_ar_a());
			notAvailableSlot.setRe_ar_va(notAvailableSlotForm.getRe_ar_va());
			notAvailableSlot.setRe_ar_nv(notAvailableSlotForm.getRe_ar_nv());
			notAvailableSlot.setRe_ar_add(notAvailableSlotForm.getRe_ar_add());

			notAvailableSlot.setRe_ace_s(notAvailableSlotForm.getRe_ace_s());
			notAvailableSlot.setRe_ace_c(notAvailableSlotForm.getRe_ace_c());
			notAvailableSlot.setRe_ace_a(notAvailableSlotForm.getRe_ace_a());
			notAvailableSlot.setRe_ace_va(notAvailableSlotForm.getRe_ace_va());
			notAvailableSlot.setRe_ace_nv(notAvailableSlotForm.getRe_ace_nv());
			notAvailableSlot.setRe_ace_add(notAvailableSlotForm.getRe_ace_add());

			notAvailableSlot.setLe_usingglass_s(notAvailableSlotForm.getLe_usingglass_s());
			notAvailableSlot.setLe_usingglass_c(notAvailableSlotForm.getLe_usingglass_c());
			notAvailableSlot.setLe_usingglass_a(notAvailableSlotForm.getLe_usingglass_a());
			notAvailableSlot.setLe_usingglass_va(notAvailableSlotForm.getLe_usingglass_va());
			notAvailableSlot.setLe_usingglass_nv(notAvailableSlotForm.getLe_usingglass_nv());
			notAvailableSlot.setLe_usingglass_add(notAvailableSlotForm.getLe_usingglass_add());

			notAvailableSlot.setLe_ar_s(notAvailableSlotForm.getLe_ar_s());
			notAvailableSlot.setLe_ar_c(notAvailableSlotForm.getLe_ar_c());
			notAvailableSlot.setLe_ar_a(notAvailableSlotForm.getLe_ar_a());
			notAvailableSlot.setLe_ar_va(notAvailableSlotForm.getLe_ar_va());
			notAvailableSlot.setLe_ar_nv(notAvailableSlotForm.getLe_ar_nv());
			notAvailableSlot.setLe_ar_add(notAvailableSlotForm.getLe_ar_add());

			notAvailableSlot.setLe_ace_s(notAvailableSlotForm.getLe_ace_s());
			notAvailableSlot.setLe_ace_c(notAvailableSlotForm.getLe_ace_c());
			notAvailableSlot.setLe_ace_a(notAvailableSlotForm.getLe_ace_a());
			notAvailableSlot.setLe_ace_va(notAvailableSlotForm.getLe_ace_va());
			notAvailableSlot.setLe_ace_nv(notAvailableSlotForm.getLe_ace_nv());
			notAvailableSlot.setLe_ace_add(notAvailableSlotForm.getLe_ace_add());

			notAvailableSlot.setLens_left1(notAvailableSlotForm.getLens_left1());
			notAvailableSlot.setLens_left2(notAvailableSlotForm.getLens_left2());
			notAvailableSlot.setDiagnosisarea(notAvailableSlotForm.getDiagnosisarea());
			notAvailableSlot.setLens_right1(notAvailableSlotForm.getLens_right1());
			notAvailableSlot.setLens_right2(notAvailableSlotForm.getLens_right2());
			notAvailableSlot.setFollowup(notAvailableSlotForm.getFollowup());

			notAvailableSlot.setUserid(loginInfo.getUserId());
			notAvailableSlot.setDatetime(DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone()));
			notAvailableSlot.setOpdid(notAvailableSlotForm.getOpdid());
			notAvailableSlot.setClientId(notAvailableSlotForm.getClientId());
			// save
			int id = notAvailableSlotDAO.saveOptionalForm(notAvailableSlot);

			// NotAvailableSlot notAvailableSlot2 =
			// notAvailableSlotDAO.getOptionalFormDetails(id);

			String clientid = notAvailableSlotForm.getClientId();
			String appointmentid = notAvailableSlotForm.getOpdid();

			Client client = clientDAO.getClientDetails(clientid);

			Clinic clinic = new Clinic();
			ClinicDAO clinicDAO = new JDBCClinicDAO(connection);
			UserProfileDAO userProfileDAO = new JDBCUserProfileDAO(connection);

			String diaryuserid = availableSlotDAO.getDiaryUserId(appointmentid);
			String fullname = client.getTitle() + " " + client.getFirstName() + " " + client.getLastName();
			ArrayList<Clinic> locationAdressList = accountsDAO.getLocationAddress(loginInfo.getClinicid());
			// String agegender=client.getAge()+"/"+client.getGender();
			String agegender = "";
			String dob = client.getDob();
			String age2 = DateTimeUtils.getAge(client.getDob());
			if (Integer.parseInt(age2) < 2) {
				if (Integer.parseInt(age2) < 1) {
					String monthdays = DateTimeUtils.getMonthandDays(client.getDob());
					agegender = monthdays;
				} else {
					String monthdays = DateTimeUtils.getMonthandDays(client.getDob());
					agegender = age2 + " Years" + " " + monthdays;
				}
			} else {
				agegender = age2;
			}

			Client bmi = clientDAO.getPatientBMIData(clientid, Integer.parseInt(appointmentid));

			UserProfile userProfile = userProfileDAO.getUserprofileDetails(Integer.parseInt(diaryuserid));
			Master master = masterDAO.getDisciplineData(userProfile.getDiciplineName());
			clinic = clinicDAO.getCliniclistDetails(loginInfo.getClinicid());
			notAvailableSlotForm.setAgegender(agegender + "/" + client.getGender());
			notAvailableSlotForm.setAbrivationid(client.getAbrivationid());
			notAvailableSlotForm.setClientId(clientid);
			notAvailableSlotForm.setCity(client.getTown());
			notAvailableSlotForm.setWebsiteUrl(clinic.getWebsiteUrl());
			notAvailableSlotForm.setClinicName(clinic.getClinicName());
			notAvailableSlotForm.setUserQualification(userProfile.getQualification());
			notAvailableSlotForm.setClinicemail(clinic.getEmail());
			notAvailableSlotForm.setClinicaddress(clinic.getAddress());
			notAvailableSlotForm.setClinicPhone(clinic.getLandLine());
			notAvailableSlotForm.setMobile(client.getMobNo());
			notAvailableSlotForm.setPractitonerName(userProfile.getFullname());
			notAvailableSlotForm.setJobtitle(master.getDiscipline());
			notAvailableSlotForm.setOwner_qualification(clinic.getOwner_qualification());
			notAvailableSlotForm.setSTime(clinic.getStarttime());
			notAvailableSlotForm.setEndTime(clinic.getEndtime());
			notAvailableSlotForm.setClient(fullname);
			notAvailableSlotForm.setRegisterno(clinic.getRegisterno());
			notAvailableSlotForm.setWeight(bmi.getWeight());
			notAvailableSlotForm.setHeight(bmi.getHeight());
			notAvailableSlotForm.setPulse(bmi.getPulse());
			notAvailableSlotForm.setSysbp(bmi.getSysbp());
			notAvailableSlotForm.setDiabp(bmi.getDiabp());
			notAvailableSlotForm.setOpdid(appointmentid);

			notAvailableSlotForm.setUseregno(userProfile.getRegisterno());

			notAvailableSlotForm.setBmi(bmi.getBmi());
			// notAvailableSlotForm.setJobtitle(loginInfo.getJobTitle());

			String dd[] = DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone()).split(" ");
			String printedBy = loginInfo.getUserId() + " " + DateTimeUtils.getCommencingDate1(dd[0]) + " " + dd[1];
			notAvailableSlotForm.setPrintedBy(printedBy);
			notAvailableSlotForm.setBlankletterhead(userProfile.getBlankletterhead());

			notAvailableSlotForm.setRe_unaided_d(notAvailableSlotForm.getRe_unaided_d());
			notAvailableSlotForm.setRe_unaided_n(notAvailableSlotForm.getRe_unaided_n());
			notAvailableSlotForm.setRe_withglass_d(notAvailableSlotForm.getRe_withglass_d());
			notAvailableSlotForm.setRe_withglass_n(notAvailableSlotForm.getRe_withglass_n());
			notAvailableSlotForm.setRe_gtph_d(notAvailableSlotForm.getRe_gtph_d());
			notAvailableSlotForm.setRe_gtph_n(notAvailableSlotForm.getRe_gtph_n());
			notAvailableSlotForm.setLe_unaided_d(notAvailableSlotForm.getLe_unaided_d());
			notAvailableSlotForm.setLe_unaided_n(notAvailableSlotForm.getLe_unaided_n());
			notAvailableSlotForm.setLe_withglass_d(notAvailableSlotForm.getLe_withglass_d());
			notAvailableSlotForm.setLe_withglass_n(notAvailableSlotForm.getLe_withglass_n());
			notAvailableSlotForm.setLe_gtph_d(notAvailableSlotForm.getLe_gtph_d());
			notAvailableSlotForm.setLe_gtph_n(notAvailableSlotForm.getLe_gtph_n());
			notAvailableSlotForm.setAir_r(notAvailableSlotForm.getAir_r());
			notAvailableSlotForm.setAir_l(notAvailableSlotForm.getAir_l());
			notAvailableSlotForm.setPerkins_r(notAvailableSlotForm.getPerkins_r());
			notAvailableSlotForm.setPerkins_l(notAvailableSlotForm.getPerkins_l());
			notAvailableSlotForm.setAppl_r(notAvailableSlotForm.getAppl_r());
			notAvailableSlotForm.setAppl_l(notAvailableSlotForm.getAppl_l());

			notAvailableSlotForm.setKeratometry1(notAvailableSlotForm.getKeratometry1());
			notAvailableSlotForm.setKeratometry2(notAvailableSlotForm.getKeratometry2());
			notAvailableSlotForm.setKeratometry3(notAvailableSlotForm.getKeratometry3());
			notAvailableSlotForm.setKeratometry4(notAvailableSlotForm.getKeratometry4());
			notAvailableSlotForm.setKeratometry5(notAvailableSlotForm.getKeratometry5());
			notAvailableSlotForm.setKeratometry6(notAvailableSlotForm.getKeratometry6());
			notAvailableSlotForm.setKeratometry7(notAvailableSlotForm.getKeratometry7());
			notAvailableSlotForm.setKeratometry8(notAvailableSlotForm.getKeratometry8());

			notAvailableSlotForm.setRe_usingglass_s(notAvailableSlotForm.getRe_usingglass_s());
			notAvailableSlotForm.setRe_usingglass_c(notAvailableSlotForm.getRe_usingglass_c());
			notAvailableSlotForm.setRe_usingglass_a(notAvailableSlotForm.getRe_usingglass_a());
			notAvailableSlotForm.setRe_usingglass_va(notAvailableSlotForm.getRe_usingglass_va());
			notAvailableSlotForm.setRe_usingglass_nv(notAvailableSlotForm.getRe_usingglass_nv());
			notAvailableSlotForm.setRe_usingglass_add(notAvailableSlotForm.getRe_usingglass_add());

			notAvailableSlotForm.setRe_ar_s(notAvailableSlotForm.getRe_ar_s());
			notAvailableSlotForm.setRe_ar_c(notAvailableSlotForm.getRe_ar_c());
			notAvailableSlotForm.setRe_ar_a(notAvailableSlotForm.getRe_ar_a());
			notAvailableSlotForm.setRe_ar_va(notAvailableSlotForm.getRe_ar_va());
			notAvailableSlotForm.setRe_ar_nv(notAvailableSlotForm.getRe_ar_nv());
			notAvailableSlotForm.setRe_ar_add(notAvailableSlotForm.getRe_ar_add());

			notAvailableSlotForm.setRe_ace_s(notAvailableSlotForm.getRe_ace_s());
			notAvailableSlotForm.setRe_ace_c(notAvailableSlotForm.getRe_ace_c());
			notAvailableSlotForm.setRe_ace_a(notAvailableSlotForm.getRe_ace_a());
			notAvailableSlotForm.setRe_ace_va(notAvailableSlotForm.getRe_ace_va());
			notAvailableSlotForm.setRe_ace_nv(notAvailableSlotForm.getRe_ace_nv());
			notAvailableSlotForm.setRe_ace_add(notAvailableSlotForm.getRe_ace_add());

			notAvailableSlotForm.setLe_usingglass_s(notAvailableSlotForm.getLe_usingglass_s());
			notAvailableSlotForm.setLe_usingglass_c(notAvailableSlotForm.getLe_usingglass_c());
			notAvailableSlotForm.setLe_usingglass_a(notAvailableSlotForm.getLe_usingglass_a());
			notAvailableSlotForm.setLe_usingglass_va(notAvailableSlotForm.getLe_usingglass_va());
			notAvailableSlotForm.setLe_usingglass_nv(notAvailableSlotForm.getLe_usingglass_nv());
			notAvailableSlotForm.setLe_usingglass_add(notAvailableSlotForm.getLe_usingglass_add());

			notAvailableSlotForm.setLe_ar_s(notAvailableSlotForm.getLe_ar_s());
			notAvailableSlotForm.setLe_ar_c(notAvailableSlotForm.getLe_ar_c());
			notAvailableSlotForm.setLe_ar_a(notAvailableSlotForm.getLe_ar_a());
			notAvailableSlotForm.setLe_ar_va(notAvailableSlotForm.getLe_ar_va());
			notAvailableSlotForm.setLe_ar_nv(notAvailableSlotForm.getLe_ar_nv());
			notAvailableSlotForm.setLe_ar_add(notAvailableSlotForm.getLe_ar_add());

			notAvailableSlotForm.setLe_ace_s(notAvailableSlotForm.getLe_ace_s());
			notAvailableSlotForm.setLe_ace_c(notAvailableSlotForm.getLe_ace_c());
			notAvailableSlotForm.setLe_ace_a(notAvailableSlotForm.getLe_ace_a());
			notAvailableSlotForm.setLe_ace_va(notAvailableSlotForm.getLe_ace_va());
			notAvailableSlotForm.setLe_ace_nv(notAvailableSlotForm.getLe_ace_nv());
			notAvailableSlotForm.setLe_ace_add(notAvailableSlotForm.getLe_ace_add());

			notAvailableSlotForm.setLens_left1(notAvailableSlotForm.getLens_left1());
			notAvailableSlotForm.setLens_left2(notAvailableSlotForm.getLens_left2());
			notAvailableSlotForm.setDiagnosisarea(notAvailableSlotForm.getDiagnosisarea());
			notAvailableSlotForm.setLens_right1(notAvailableSlotForm.getLens_right1());
			notAvailableSlotForm.setLens_right2(notAvailableSlotForm.getLens_right2());
			notAvailableSlotForm.setFollowup(notAvailableSlotForm.getFollowup());

		} catch (Exception e) {
			e.printStackTrace();
		} finally {

			connection.close();
		}
		return "blankLetterHeadprint";
	}

	public String editoptionformdetails() throws Exception {
		Connection connection = null;
		try {
			connection = Connection_provider.getconnection();
			NotAvailableSlotDAO notAvailableSlotDAO = new JDBCNotAvailableSlotDAO(connection);
			AccountsDAO accountsDAO = new JDBCAccountsDAO(connection);
			NotAvailableSlotDAO availableSlotDAO = new JDBCNotAvailableSlotDAO(connection);

			ClientDAO clientDAO = new JDBCClientDAO(connection);
			MasterDAO masterDAO = new JDBCMasterDAO(connection);

			int id = Integer.parseInt(request.getParameter("id"));
			NotAvailableSlot notAvailableSlot = notAvailableSlotDAO.getOptionalFormDetails(id);

			String clientid = notAvailableSlot.getClientId();
			String appointmentid = notAvailableSlot.getOpdid();
			Client client = clientDAO.getClientDetails(clientid);
			Clinic clinic = new Clinic();
			ClinicDAO clinicDAO = new JDBCClinicDAO(connection);
			UserProfileDAO userProfileDAO = new JDBCUserProfileDAO(connection);

			String diaryuserid = availableSlotDAO.getDiaryUserId(appointmentid);
			String fullname = client.getTitle() + " " + client.getFirstName() + " " + client.getLastName();
			ArrayList<Clinic> locationAdressList = accountsDAO.getLocationAddress(loginInfo.getClinicid());
			// String agegender=client.getAge()+"/"+client.getGender();
			String agegender = "";
			String dob = client.getDob();
			String age2 = DateTimeUtils.getAge(client.getDob());
			if (Integer.parseInt(age2) < 2) {
				if (Integer.parseInt(age2) < 1) {
					String monthdays = DateTimeUtils.getMonthandDays(client.getDob());
					agegender = monthdays;
				} else {
					String monthdays = DateTimeUtils.getMonthandDays(client.getDob());
					agegender = age2 + " Years" + " " + monthdays;
				}
			} else {
				agegender = age2;
			}

			Client bmi = clientDAO.getPatientBMIData(clientid, Integer.parseInt(appointmentid));

			UserProfile userProfile = userProfileDAO.getUserprofileDetails(Integer.parseInt(diaryuserid));
			Master master = masterDAO.getDisciplineData(userProfile.getDiciplineName());
			clinic = clinicDAO.getCliniclistDetails(loginInfo.getClinicid());
			notAvailableSlotForm.setAgegender(agegender + "/" + client.getGender());
			notAvailableSlotForm.setAbrivationid(client.getAbrivationid());
			notAvailableSlotForm.setClientId(clientid);
			notAvailableSlotForm.setCity(client.getTown());
			notAvailableSlotForm.setWebsiteUrl(clinic.getWebsiteUrl());
			notAvailableSlotForm.setClinicName(clinic.getClinicName());
			notAvailableSlotForm.setUserQualification(userProfile.getQualification());
			notAvailableSlotForm.setClinicemail(clinic.getEmail());
			notAvailableSlotForm.setClinicaddress(clinic.getAddress());
			notAvailableSlotForm.setClinicPhone(clinic.getLandLine());
			notAvailableSlotForm.setMobile(client.getMobNo());
			notAvailableSlotForm.setPractitonerName(userProfile.getFullname());
			notAvailableSlotForm.setJobtitle(master.getDiscipline());
			notAvailableSlotForm.setOwner_qualification(clinic.getOwner_qualification());
			notAvailableSlotForm.setSTime(clinic.getStarttime());
			notAvailableSlotForm.setEndTime(clinic.getEndtime());
			notAvailableSlotForm.setClient(fullname);
			notAvailableSlotForm.setRegisterno(clinic.getRegisterno());
			notAvailableSlotForm.setWeight(bmi.getWeight());
			notAvailableSlotForm.setHeight(bmi.getHeight());
			notAvailableSlotForm.setPulse(bmi.getPulse());
			notAvailableSlotForm.setSysbp(bmi.getSysbp());
			notAvailableSlotForm.setDiabp(bmi.getDiabp());
			notAvailableSlotForm.setOpdid(appointmentid);

			notAvailableSlotForm.setBmi(bmi.getBmi());
			// notAvailableSlotForm.setJobtitle(loginInfo.getJobTitle());

			String dd[] = DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone()).split(" ");
			String printedBy = loginInfo.getUserId() + " " + DateTimeUtils.getCommencingDate1(dd[0]) + " " + dd[1];
			notAvailableSlotForm.setPrintedBy(printedBy);
			notAvailableSlotForm.setBlankletterhead(userProfile.getBlankletterhead());

			notAvailableSlotForm.setRe_unaided_d(notAvailableSlot.getRe_unaided_d());
			notAvailableSlotForm.setRe_unaided_n(notAvailableSlot.getRe_unaided_n());
			notAvailableSlotForm.setRe_withglass_d(notAvailableSlot.getRe_withglass_d());
			notAvailableSlotForm.setRe_withglass_n(notAvailableSlot.getRe_withglass_n());
			notAvailableSlotForm.setRe_gtph_d(notAvailableSlot.getRe_gtph_d());
			notAvailableSlotForm.setRe_gtph_n(notAvailableSlot.getRe_gtph_n());
			notAvailableSlotForm.setLe_unaided_d(notAvailableSlot.getLe_unaided_d());
			notAvailableSlotForm.setLe_unaided_n(notAvailableSlot.getLe_unaided_n());
			notAvailableSlotForm.setLe_withglass_d(notAvailableSlot.getLe_withglass_d());
			notAvailableSlotForm.setLe_withglass_n(notAvailableSlot.getLe_withglass_n());
			notAvailableSlotForm.setLe_gtph_d(notAvailableSlot.getLe_gtph_d());
			notAvailableSlotForm.setLe_gtph_n(notAvailableSlot.getLe_gtph_n());
			notAvailableSlotForm.setAir_r(notAvailableSlot.getAir_r());
			notAvailableSlotForm.setAir_l(notAvailableSlot.getAir_l());
			notAvailableSlotForm.setPerkins_r(notAvailableSlot.getPerkins_r());
			notAvailableSlotForm.setPerkins_l(notAvailableSlot.getPerkins_l());
			notAvailableSlotForm.setAppl_r(notAvailableSlot.getAppl_r());
			notAvailableSlotForm.setAppl_l(notAvailableSlot.getAppl_l());

			notAvailableSlotForm.setKeratometry1(notAvailableSlot.getKeratometry1());
			notAvailableSlotForm.setKeratometry2(notAvailableSlot.getKeratometry2());
			notAvailableSlotForm.setKeratometry3(notAvailableSlot.getKeratometry3());
			notAvailableSlotForm.setKeratometry4(notAvailableSlot.getKeratometry4());
			notAvailableSlotForm.setKeratometry5(notAvailableSlot.getKeratometry5());
			notAvailableSlotForm.setKeratometry6(notAvailableSlot.getKeratometry6());
			notAvailableSlotForm.setKeratometry7(notAvailableSlot.getKeratometry7());
			notAvailableSlotForm.setKeratometry8(notAvailableSlot.getKeratometry8());

			notAvailableSlotForm.setRe_usingglass_s(notAvailableSlot.getRe_usingglass_s());
			notAvailableSlotForm.setRe_usingglass_c(notAvailableSlot.getRe_usingglass_c());
			notAvailableSlotForm.setRe_usingglass_a(notAvailableSlot.getRe_usingglass_a());
			notAvailableSlotForm.setRe_usingglass_va(notAvailableSlot.getRe_usingglass_va());
			notAvailableSlotForm.setRe_usingglass_nv(notAvailableSlot.getRe_usingglass_nv());
			notAvailableSlotForm.setRe_usingglass_add(notAvailableSlot.getRe_usingglass_add());

			notAvailableSlotForm.setRe_ar_s(notAvailableSlot.getRe_ar_s());
			notAvailableSlotForm.setRe_ar_c(notAvailableSlot.getRe_ar_c());
			notAvailableSlotForm.setRe_ar_a(notAvailableSlot.getRe_ar_a());
			notAvailableSlotForm.setRe_ar_va(notAvailableSlot.getRe_ar_va());
			notAvailableSlotForm.setRe_ar_nv(notAvailableSlot.getRe_ar_nv());
			notAvailableSlotForm.setRe_ar_add(notAvailableSlot.getRe_ar_add());

			notAvailableSlotForm.setRe_ace_s(notAvailableSlot.getRe_ace_s());
			notAvailableSlotForm.setRe_ace_c(notAvailableSlot.getRe_ace_c());
			notAvailableSlotForm.setRe_ace_a(notAvailableSlot.getRe_ace_a());
			notAvailableSlotForm.setRe_ace_va(notAvailableSlot.getRe_ace_va());
			notAvailableSlotForm.setRe_ace_nv(notAvailableSlot.getRe_ace_nv());
			notAvailableSlotForm.setRe_ace_add(notAvailableSlot.getRe_ace_add());

			notAvailableSlotForm.setLe_usingglass_s(notAvailableSlot.getLe_usingglass_s());
			notAvailableSlotForm.setLe_usingglass_c(notAvailableSlot.getLe_usingglass_c());
			notAvailableSlotForm.setLe_usingglass_a(notAvailableSlot.getLe_usingglass_a());
			notAvailableSlotForm.setLe_usingglass_va(notAvailableSlot.getLe_usingglass_va());
			notAvailableSlotForm.setLe_usingglass_nv(notAvailableSlot.getLe_usingglass_nv());
			notAvailableSlotForm.setLe_usingglass_add(notAvailableSlot.getLe_usingglass_add());

			notAvailableSlotForm.setLe_ar_s(notAvailableSlot.getLe_ar_s());
			notAvailableSlotForm.setLe_ar_c(notAvailableSlot.getLe_ar_c());
			notAvailableSlotForm.setLe_ar_a(notAvailableSlot.getLe_ar_a());
			notAvailableSlotForm.setLe_ar_va(notAvailableSlot.getLe_ar_va());
			notAvailableSlotForm.setLe_ar_nv(notAvailableSlot.getLe_ar_nv());
			notAvailableSlotForm.setLe_ar_add(notAvailableSlot.getLe_ar_add());

			notAvailableSlotForm.setLe_ace_s(notAvailableSlot.getLe_ace_s());
			notAvailableSlotForm.setLe_ace_c(notAvailableSlot.getLe_ace_c());
			notAvailableSlotForm.setLe_ace_a(notAvailableSlot.getLe_ace_a());
			notAvailableSlotForm.setLe_ace_va(notAvailableSlot.getLe_ace_va());
			notAvailableSlotForm.setLe_ace_nv(notAvailableSlot.getLe_ace_nv());
			notAvailableSlotForm.setLe_ace_add(notAvailableSlot.getLe_ace_add());

			notAvailableSlotForm.setLens_left1(notAvailableSlot.getLens_left1());
			notAvailableSlotForm.setLens_left2(notAvailableSlot.getLens_left2());
			notAvailableSlotForm.setDiagnosisarea(notAvailableSlot.getDiagnosisarea());
			notAvailableSlotForm.setLens_right1(notAvailableSlot.getLens_right1());
			notAvailableSlotForm.setLens_right2(notAvailableSlot.getLens_right2());
			notAvailableSlotForm.setFollowup(notAvailableSlot.getFollowup());
			notAvailableSlotForm.setId(id);

		} catch (Exception e) {
			e.printStackTrace();
		} finally {

			connection.close();
		}
		return "editoptionalform";
	}

	public String updateoptimalform() throws Exception {
		Connection connection = null;
		try {
			connection = Connection_provider.getconnection();
			NotAvailableSlotDAO notAvailableSlotDAO = new JDBCNotAvailableSlotDAO(connection);
			AccountsDAO accountsDAO = new JDBCAccountsDAO(connection);
			NotAvailableSlotDAO availableSlotDAO = new JDBCNotAvailableSlotDAO(connection);

			ClientDAO clientDAO = new JDBCClientDAO(connection);
			MasterDAO masterDAO = new JDBCMasterDAO(connection);
			NotAvailableSlot notAvailableSlot = new NotAvailableSlot();
			notAvailableSlot.setRe_unaided_d(notAvailableSlotForm.getRe_unaided_d());
			notAvailableSlot.setRe_unaided_n(notAvailableSlotForm.getRe_unaided_n());
			notAvailableSlot.setRe_withglass_d(notAvailableSlotForm.getRe_withglass_d());
			notAvailableSlot.setRe_withglass_n(notAvailableSlotForm.getRe_withglass_n());
			notAvailableSlot.setRe_gtph_d(notAvailableSlotForm.getRe_gtph_d());
			notAvailableSlot.setRe_gtph_n(notAvailableSlotForm.getRe_gtph_n());
			notAvailableSlot.setLe_unaided_d(notAvailableSlotForm.getLe_unaided_d());
			notAvailableSlot.setLe_unaided_n(notAvailableSlotForm.getLe_unaided_n());
			notAvailableSlot.setLe_withglass_d(notAvailableSlotForm.getLe_withglass_d());
			notAvailableSlot.setLe_withglass_n(notAvailableSlotForm.getLe_withglass_n());
			notAvailableSlot.setLe_gtph_d(notAvailableSlotForm.getLe_gtph_d());
			notAvailableSlot.setLe_gtph_n(notAvailableSlotForm.getLe_gtph_n());
			notAvailableSlot.setAir_r(notAvailableSlotForm.getAir_r());
			notAvailableSlot.setAir_l(notAvailableSlotForm.getAir_l());
			notAvailableSlot.setPerkins_r(notAvailableSlotForm.getPerkins_r());
			notAvailableSlot.setPerkins_l(notAvailableSlotForm.getPerkins_l());
			notAvailableSlot.setAppl_r(notAvailableSlotForm.getAppl_r());
			notAvailableSlot.setAppl_l(notAvailableSlotForm.getAppl_l());

			notAvailableSlot.setKeratometry1(notAvailableSlotForm.getKeratometry1());
			notAvailableSlot.setKeratometry2(notAvailableSlotForm.getKeratometry2());
			notAvailableSlot.setKeratometry3(notAvailableSlotForm.getKeratometry3());
			notAvailableSlot.setKeratometry4(notAvailableSlotForm.getKeratometry4());
			notAvailableSlot.setKeratometry5(notAvailableSlotForm.getKeratometry5());
			notAvailableSlot.setKeratometry6(notAvailableSlotForm.getKeratometry6());
			notAvailableSlot.setKeratometry7(notAvailableSlotForm.getKeratometry7());
			notAvailableSlot.setKeratometry8(notAvailableSlotForm.getKeratometry8());

			notAvailableSlot.setRe_usingglass_s(notAvailableSlotForm.getRe_usingglass_s());
			notAvailableSlot.setRe_usingglass_c(notAvailableSlotForm.getRe_usingglass_c());
			notAvailableSlot.setRe_usingglass_a(notAvailableSlotForm.getRe_usingglass_a());
			notAvailableSlot.setRe_usingglass_va(notAvailableSlotForm.getRe_usingglass_va());
			notAvailableSlot.setRe_usingglass_nv(notAvailableSlotForm.getRe_usingglass_nv());
			notAvailableSlot.setRe_usingglass_add(notAvailableSlotForm.getRe_usingglass_add());

			notAvailableSlot.setRe_ar_s(notAvailableSlotForm.getRe_ar_s());
			notAvailableSlot.setRe_ar_c(notAvailableSlotForm.getRe_ar_c());
			notAvailableSlot.setRe_ar_a(notAvailableSlotForm.getRe_ar_a());
			notAvailableSlot.setRe_ar_va(notAvailableSlotForm.getRe_ar_va());
			notAvailableSlot.setRe_ar_nv(notAvailableSlotForm.getRe_ar_nv());
			notAvailableSlot.setRe_ar_add(notAvailableSlotForm.getRe_ar_add());

			notAvailableSlot.setRe_ace_s(notAvailableSlotForm.getRe_ace_s());
			notAvailableSlot.setRe_ace_c(notAvailableSlotForm.getRe_ace_c());
			notAvailableSlot.setRe_ace_a(notAvailableSlotForm.getRe_ace_a());
			notAvailableSlot.setRe_ace_va(notAvailableSlotForm.getRe_ace_va());
			notAvailableSlot.setRe_ace_nv(notAvailableSlotForm.getRe_ace_nv());
			notAvailableSlot.setRe_ace_add(notAvailableSlotForm.getRe_ace_add());

			notAvailableSlot.setLe_usingglass_s(notAvailableSlotForm.getLe_usingglass_s());
			notAvailableSlot.setLe_usingglass_c(notAvailableSlotForm.getLe_usingglass_c());
			notAvailableSlot.setLe_usingglass_a(notAvailableSlotForm.getLe_usingglass_a());
			notAvailableSlot.setLe_usingglass_va(notAvailableSlotForm.getLe_usingglass_va());
			notAvailableSlot.setLe_usingglass_nv(notAvailableSlotForm.getLe_usingglass_nv());
			notAvailableSlot.setLe_usingglass_add(notAvailableSlotForm.getLe_usingglass_add());

			notAvailableSlot.setLe_ar_s(notAvailableSlotForm.getLe_ar_s());
			notAvailableSlot.setLe_ar_c(notAvailableSlotForm.getLe_ar_c());
			notAvailableSlot.setLe_ar_a(notAvailableSlotForm.getLe_ar_a());
			notAvailableSlot.setLe_ar_va(notAvailableSlotForm.getLe_ar_va());
			notAvailableSlot.setLe_ar_nv(notAvailableSlotForm.getLe_ar_nv());
			notAvailableSlot.setLe_ar_add(notAvailableSlotForm.getLe_ar_add());

			notAvailableSlot.setLe_ace_s(notAvailableSlotForm.getLe_ace_s());
			notAvailableSlot.setLe_ace_c(notAvailableSlotForm.getLe_ace_c());
			notAvailableSlot.setLe_ace_a(notAvailableSlotForm.getLe_ace_a());
			notAvailableSlot.setLe_ace_va(notAvailableSlotForm.getLe_ace_va());
			notAvailableSlot.setLe_ace_nv(notAvailableSlotForm.getLe_ace_nv());
			notAvailableSlot.setLe_ace_add(notAvailableSlotForm.getLe_ace_add());

			notAvailableSlot.setLens_left1(notAvailableSlotForm.getLens_left1());
			notAvailableSlot.setLens_left2(notAvailableSlotForm.getLens_left2());
			notAvailableSlot.setDiagnosisarea(notAvailableSlotForm.getDiagnosisarea());
			notAvailableSlot.setLens_right1(notAvailableSlotForm.getLens_right1());
			notAvailableSlot.setLens_right2(notAvailableSlotForm.getLens_right2());
			notAvailableSlot.setFollowup(notAvailableSlotForm.getFollowup());

			notAvailableSlot.setUserid(loginInfo.getUserId());
			notAvailableSlot.setDatetime(DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone()));
			notAvailableSlot.setOpdid(notAvailableSlotForm.getOpdid());
			notAvailableSlot.setClientId(notAvailableSlotForm.getClientId());
			notAvailableSlot.setId(notAvailableSlotForm.getId());
			// save
			int id = notAvailableSlotDAO.updateOptionalForm(notAvailableSlot);

			// NotAvailableSlot notAvailableSlot2 =
			// notAvailableSlotDAO.getOptionalFormDetails(id);

			String clientid = notAvailableSlotForm.getClientId();
			String appointmentid = notAvailableSlotForm.getOpdid();

			Client client = clientDAO.getClientDetails(clientid);

			Clinic clinic = new Clinic();
			ClinicDAO clinicDAO = new JDBCClinicDAO(connection);
			UserProfileDAO userProfileDAO = new JDBCUserProfileDAO(connection);

			String diaryuserid = availableSlotDAO.getDiaryUserId(appointmentid);
			String fullname = client.getTitle() + " " + client.getFirstName() + " " + client.getLastName();
			ArrayList<Clinic> locationAdressList = accountsDAO.getLocationAddress(loginInfo.getClinicid());
			// String agegender=client.getAge()+"/"+client.getGender();
			String agegender = "";
			String dob = client.getDob();
			String age2 = DateTimeUtils.getAge(client.getDob());
			if (Integer.parseInt(age2) < 2) {
				if (Integer.parseInt(age2) < 1) {
					String monthdays = DateTimeUtils.getMonthandDays(client.getDob());
					agegender = monthdays;
				} else {
					String monthdays = DateTimeUtils.getMonthandDays(client.getDob());
					agegender = age2 + " Years" + " " + monthdays;
				}
			} else {
				agegender = age2;
			}

			Client bmi = clientDAO.getPatientBMIData(clientid, Integer.parseInt(appointmentid));

			UserProfile userProfile = userProfileDAO.getUserprofileDetails(Integer.parseInt(diaryuserid));
			Master master = masterDAO.getDisciplineData(userProfile.getDiciplineName());
			clinic = clinicDAO.getCliniclistDetails(loginInfo.getClinicid());
			notAvailableSlotForm.setAgegender(agegender + "/" + client.getGender());
			notAvailableSlotForm.setAbrivationid(client.getAbrivationid());
			notAvailableSlotForm.setClientId(clientid);
			notAvailableSlotForm.setCity(client.getTown());
			notAvailableSlotForm.setWebsiteUrl(clinic.getWebsiteUrl());
			notAvailableSlotForm.setClinicName(clinic.getClinicName());
			notAvailableSlotForm.setUserQualification(userProfile.getQualification());
			notAvailableSlotForm.setClinicemail(clinic.getEmail());
			notAvailableSlotForm.setClinicaddress(clinic.getAddress());
			notAvailableSlotForm.setClinicPhone(clinic.getLandLine());
			notAvailableSlotForm.setMobile(client.getMobNo());
			notAvailableSlotForm.setPractitonerName(userProfile.getFullname());
			notAvailableSlotForm.setJobtitle(master.getDiscipline());
			notAvailableSlotForm.setOwner_qualification(clinic.getOwner_qualification());
			notAvailableSlotForm.setSTime(clinic.getStarttime());
			notAvailableSlotForm.setEndTime(clinic.getEndtime());
			notAvailableSlotForm.setClient(fullname);
			notAvailableSlotForm.setRegisterno(clinic.getRegisterno());
			notAvailableSlotForm.setWeight(bmi.getWeight());
			notAvailableSlotForm.setHeight(bmi.getHeight());
			notAvailableSlotForm.setPulse(bmi.getPulse());
			notAvailableSlotForm.setSysbp(bmi.getSysbp());
			notAvailableSlotForm.setDiabp(bmi.getDiabp());
			notAvailableSlotForm.setOpdid(appointmentid);

			notAvailableSlotForm.setBmi(bmi.getBmi());
			// notAvailableSlotForm.setJobtitle(loginInfo.getJobTitle());

			String dd[] = DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone()).split(" ");
			String printedBy = loginInfo.getUserId() + " " + DateTimeUtils.getCommencingDate1(dd[0]) + " " + dd[1];
			notAvailableSlotForm.setPrintedBy(printedBy);
			notAvailableSlotForm.setBlankletterhead(userProfile.getBlankletterhead());

			notAvailableSlotForm.setRe_unaided_d(notAvailableSlotForm.getRe_unaided_d());
			notAvailableSlotForm.setRe_unaided_n(notAvailableSlotForm.getRe_unaided_n());
			notAvailableSlotForm.setRe_withglass_d(notAvailableSlotForm.getRe_withglass_d());
			notAvailableSlotForm.setRe_withglass_n(notAvailableSlotForm.getRe_withglass_n());
			notAvailableSlotForm.setRe_gtph_d(notAvailableSlotForm.getRe_gtph_d());
			notAvailableSlotForm.setRe_gtph_n(notAvailableSlotForm.getRe_gtph_n());
			notAvailableSlotForm.setLe_unaided_d(notAvailableSlotForm.getLe_unaided_d());
			notAvailableSlotForm.setLe_unaided_n(notAvailableSlotForm.getLe_unaided_n());
			notAvailableSlotForm.setLe_withglass_d(notAvailableSlotForm.getLe_withglass_d());
			notAvailableSlotForm.setLe_withglass_n(notAvailableSlotForm.getLe_withglass_n());
			notAvailableSlotForm.setLe_gtph_d(notAvailableSlotForm.getLe_gtph_d());
			notAvailableSlotForm.setLe_gtph_n(notAvailableSlotForm.getLe_gtph_n());
			notAvailableSlotForm.setAir_r(notAvailableSlotForm.getAir_r());
			notAvailableSlotForm.setAir_l(notAvailableSlotForm.getAir_l());
			notAvailableSlotForm.setPerkins_r(notAvailableSlotForm.getPerkins_r());
			notAvailableSlotForm.setPerkins_l(notAvailableSlotForm.getPerkins_l());
			notAvailableSlotForm.setAppl_r(notAvailableSlotForm.getAppl_r());
			notAvailableSlotForm.setAppl_l(notAvailableSlotForm.getAppl_l());

			notAvailableSlotForm.setKeratometry1(notAvailableSlotForm.getKeratometry1());
			notAvailableSlotForm.setKeratometry2(notAvailableSlotForm.getKeratometry2());
			notAvailableSlotForm.setKeratometry3(notAvailableSlotForm.getKeratometry3());
			notAvailableSlotForm.setKeratometry4(notAvailableSlotForm.getKeratometry4());
			notAvailableSlotForm.setKeratometry5(notAvailableSlotForm.getKeratometry5());
			notAvailableSlotForm.setKeratometry6(notAvailableSlotForm.getKeratometry6());
			notAvailableSlotForm.setKeratometry7(notAvailableSlotForm.getKeratometry7());
			notAvailableSlotForm.setKeratometry8(notAvailableSlotForm.getKeratometry8());

			notAvailableSlotForm.setRe_usingglass_s(notAvailableSlotForm.getRe_usingglass_s());
			notAvailableSlotForm.setRe_usingglass_c(notAvailableSlotForm.getRe_usingglass_c());
			notAvailableSlotForm.setRe_usingglass_a(notAvailableSlotForm.getRe_usingglass_a());
			notAvailableSlotForm.setRe_usingglass_va(notAvailableSlotForm.getRe_usingglass_va());
			notAvailableSlotForm.setRe_usingglass_nv(notAvailableSlotForm.getRe_usingglass_nv());
			notAvailableSlotForm.setRe_usingglass_add(notAvailableSlotForm.getRe_usingglass_add());

			notAvailableSlotForm.setRe_ar_s(notAvailableSlotForm.getRe_ar_s());
			notAvailableSlotForm.setRe_ar_c(notAvailableSlotForm.getRe_ar_c());
			notAvailableSlotForm.setRe_ar_a(notAvailableSlotForm.getRe_ar_a());
			notAvailableSlotForm.setRe_ar_va(notAvailableSlotForm.getRe_ar_va());
			notAvailableSlotForm.setRe_ar_nv(notAvailableSlotForm.getRe_ar_nv());
			notAvailableSlotForm.setRe_ar_add(notAvailableSlotForm.getRe_ar_add());

			notAvailableSlotForm.setRe_ace_s(notAvailableSlotForm.getRe_ace_s());
			notAvailableSlotForm.setRe_ace_c(notAvailableSlotForm.getRe_ace_c());
			notAvailableSlotForm.setRe_ace_a(notAvailableSlotForm.getRe_ace_a());
			notAvailableSlotForm.setRe_ace_va(notAvailableSlotForm.getRe_ace_va());
			notAvailableSlotForm.setRe_ace_nv(notAvailableSlotForm.getRe_ace_nv());
			notAvailableSlotForm.setRe_ace_add(notAvailableSlotForm.getRe_ace_add());

			notAvailableSlotForm.setLe_usingglass_s(notAvailableSlotForm.getLe_usingglass_s());
			notAvailableSlotForm.setLe_usingglass_c(notAvailableSlotForm.getLe_usingglass_c());
			notAvailableSlotForm.setLe_usingglass_a(notAvailableSlotForm.getLe_usingglass_a());
			notAvailableSlotForm.setLe_usingglass_va(notAvailableSlotForm.getLe_usingglass_va());
			notAvailableSlotForm.setLe_usingglass_nv(notAvailableSlotForm.getLe_usingglass_nv());
			notAvailableSlotForm.setLe_usingglass_add(notAvailableSlotForm.getLe_usingglass_add());

			notAvailableSlotForm.setLe_ar_s(notAvailableSlotForm.getLe_ar_s());
			notAvailableSlotForm.setLe_ar_c(notAvailableSlotForm.getLe_ar_c());
			notAvailableSlotForm.setLe_ar_a(notAvailableSlotForm.getLe_ar_a());
			notAvailableSlotForm.setLe_ar_va(notAvailableSlotForm.getLe_ar_va());
			notAvailableSlotForm.setLe_ar_nv(notAvailableSlotForm.getLe_ar_nv());
			notAvailableSlotForm.setLe_ar_add(notAvailableSlotForm.getLe_ar_add());

			notAvailableSlotForm.setLe_ace_s(notAvailableSlotForm.getLe_ace_s());
			notAvailableSlotForm.setLe_ace_c(notAvailableSlotForm.getLe_ace_c());
			notAvailableSlotForm.setLe_ace_a(notAvailableSlotForm.getLe_ace_a());
			notAvailableSlotForm.setLe_ace_va(notAvailableSlotForm.getLe_ace_va());
			notAvailableSlotForm.setLe_ace_nv(notAvailableSlotForm.getLe_ace_nv());
			notAvailableSlotForm.setLe_ace_add(notAvailableSlotForm.getLe_ace_add());

			notAvailableSlotForm.setLens_left1(notAvailableSlotForm.getLens_left1());
			notAvailableSlotForm.setLens_left2(notAvailableSlotForm.getLens_left2());
			notAvailableSlotForm.setDiagnosisarea(notAvailableSlotForm.getDiagnosisarea());
			notAvailableSlotForm.setLens_right1(notAvailableSlotForm.getLens_right1());
			notAvailableSlotForm.setLens_right2(notAvailableSlotForm.getLens_right2());
			notAvailableSlotForm.setFollowup(notAvailableSlotForm.getFollowup());

		} catch (Exception e) {
			e.printStackTrace();
		} finally {

			connection.close();
		}
		return "blankLetterHeadprint";
	}

	public String printoptionform() throws Exception {
		Connection connection = null;
		try {
			connection = Connection_provider.getconnection();
			NotAvailableSlotDAO notAvailableSlotDAO = new JDBCNotAvailableSlotDAO(connection);
			AccountsDAO accountsDAO = new JDBCAccountsDAO(connection);
			NotAvailableSlotDAO availableSlotDAO = new JDBCNotAvailableSlotDAO(connection);

			ClientDAO clientDAO = new JDBCClientDAO(connection);
			MasterDAO masterDAO = new JDBCMasterDAO(connection);

			int id = Integer.parseInt(request.getParameter("id"));
			NotAvailableSlot notAvailableSlot = notAvailableSlotDAO.getOptionalFormDetails(id);

			String clientid = notAvailableSlot.getClientId();
			String appointmentid = notAvailableSlot.getOpdid();
			Client client = clientDAO.getClientDetails(clientid);
			Clinic clinic = new Clinic();
			ClinicDAO clinicDAO = new JDBCClinicDAO(connection);
			UserProfileDAO userProfileDAO = new JDBCUserProfileDAO(connection);

			String diaryuserid = availableSlotDAO.getDiaryUserId(appointmentid);
			String fullname = client.getTitle() + " " + client.getFirstName() + " " + client.getLastName();
			ArrayList<Clinic> locationAdressList = accountsDAO.getLocationAddress(loginInfo.getClinicid());
			// String agegender=client.getAge()+"/"+client.getGender();
			String agegender = "";
			String dob = client.getDob();
			String age2 = DateTimeUtils.getAge(client.getDob());
			if (Integer.parseInt(age2) < 2) {
				if (Integer.parseInt(age2) < 1) {
					String monthdays = DateTimeUtils.getMonthandDays(client.getDob());
					agegender = monthdays;
				} else {
					String monthdays = DateTimeUtils.getMonthandDays(client.getDob());
					agegender = age2 + " Years" + " " + monthdays;
				}
			} else {
				agegender = age2;
			}

			Client bmi = clientDAO.getPatientBMIData(clientid, Integer.parseInt(appointmentid));

			UserProfile userProfile = userProfileDAO.getUserprofileDetails(Integer.parseInt(diaryuserid));
			Master master = masterDAO.getDisciplineData(userProfile.getDiciplineName());
			clinic = clinicDAO.getCliniclistDetails(loginInfo.getClinicid());
			notAvailableSlotForm.setAgegender(agegender + "/" + client.getGender());
			notAvailableSlotForm.setAbrivationid(client.getAbrivationid());
			notAvailableSlotForm.setClientId(clientid);
			notAvailableSlotForm.setCity(client.getTown());
			notAvailableSlotForm.setWebsiteUrl(clinic.getWebsiteUrl());
			notAvailableSlotForm.setClinicName(clinic.getClinicName());
			notAvailableSlotForm.setUserQualification(userProfile.getQualification());
			notAvailableSlotForm.setClinicemail(clinic.getEmail());
			notAvailableSlotForm.setClinicaddress(clinic.getAddress());
			notAvailableSlotForm.setClinicPhone(clinic.getLandLine());
			notAvailableSlotForm.setMobile(client.getMobNo());
			notAvailableSlotForm.setPractitonerName(userProfile.getFullname());
			notAvailableSlotForm.setJobtitle(master.getDiscipline());
			notAvailableSlotForm.setOwner_qualification(clinic.getOwner_qualification());
			notAvailableSlotForm.setSTime(clinic.getStarttime());
			notAvailableSlotForm.setEndTime(clinic.getEndtime());
			notAvailableSlotForm.setClient(fullname);
			notAvailableSlotForm.setRegisterno(clinic.getRegisterno());
			notAvailableSlotForm.setWeight(bmi.getWeight());
			notAvailableSlotForm.setHeight(bmi.getHeight());
			notAvailableSlotForm.setPulse(bmi.getPulse());
			notAvailableSlotForm.setSysbp(bmi.getSysbp());
			notAvailableSlotForm.setDiabp(bmi.getDiabp());
			notAvailableSlotForm.setOpdid(appointmentid);

			notAvailableSlotForm.setBmi(bmi.getBmi());
			// notAvailableSlotForm.setJobtitle(loginInfo.getJobTitle());

			String dd[] = DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone()).split(" ");
			String printedBy = loginInfo.getUserId() + " " + DateTimeUtils.getCommencingDate1(dd[0]) + " " + dd[1];
			notAvailableSlotForm.setPrintedBy(printedBy);
			notAvailableSlotForm.setBlankletterhead(userProfile.getBlankletterhead());

			notAvailableSlotForm.setRe_unaided_d(notAvailableSlot.getRe_unaided_d());
			notAvailableSlotForm.setRe_unaided_n(notAvailableSlot.getRe_unaided_n());
			notAvailableSlotForm.setRe_withglass_d(notAvailableSlot.getRe_withglass_d());
			notAvailableSlotForm.setRe_withglass_n(notAvailableSlot.getRe_withglass_n());
			notAvailableSlotForm.setRe_gtph_d(notAvailableSlot.getRe_gtph_d());
			notAvailableSlotForm.setRe_gtph_n(notAvailableSlot.getRe_gtph_n());
			notAvailableSlotForm.setLe_unaided_d(notAvailableSlot.getLe_unaided_d());
			notAvailableSlotForm.setLe_unaided_n(notAvailableSlot.getLe_unaided_n());
			notAvailableSlotForm.setLe_withglass_d(notAvailableSlot.getLe_withglass_d());
			notAvailableSlotForm.setLe_withglass_n(notAvailableSlot.getLe_withglass_n());
			notAvailableSlotForm.setLe_gtph_d(notAvailableSlot.getLe_gtph_d());
			notAvailableSlotForm.setLe_gtph_n(notAvailableSlot.getLe_gtph_n());
			notAvailableSlotForm.setAir_r(notAvailableSlot.getAir_r());
			notAvailableSlotForm.setAir_l(notAvailableSlot.getAir_l());
			notAvailableSlotForm.setPerkins_r(notAvailableSlot.getPerkins_r());
			notAvailableSlotForm.setPerkins_l(notAvailableSlot.getPerkins_l());
			notAvailableSlotForm.setAppl_r(notAvailableSlot.getAppl_r());
			notAvailableSlotForm.setAppl_l(notAvailableSlot.getAppl_l());

			notAvailableSlotForm.setKeratometry1(notAvailableSlot.getKeratometry1());
			notAvailableSlotForm.setKeratometry2(notAvailableSlot.getKeratometry2());
			notAvailableSlotForm.setKeratometry3(notAvailableSlot.getKeratometry3());
			notAvailableSlotForm.setKeratometry4(notAvailableSlot.getKeratometry4());
			notAvailableSlotForm.setKeratometry5(notAvailableSlot.getKeratometry5());
			notAvailableSlotForm.setKeratometry6(notAvailableSlot.getKeratometry6());
			notAvailableSlotForm.setKeratometry7(notAvailableSlot.getKeratometry7());
			notAvailableSlotForm.setKeratometry8(notAvailableSlot.getKeratometry8());

			notAvailableSlotForm.setRe_usingglass_s(notAvailableSlot.getRe_usingglass_s());
			notAvailableSlotForm.setRe_usingglass_c(notAvailableSlot.getRe_usingglass_c());
			notAvailableSlotForm.setRe_usingglass_a(notAvailableSlot.getRe_usingglass_a());
			notAvailableSlotForm.setRe_usingglass_va(notAvailableSlot.getRe_usingglass_va());
			notAvailableSlotForm.setRe_usingglass_nv(notAvailableSlot.getRe_usingglass_nv());
			notAvailableSlotForm.setRe_usingglass_add(notAvailableSlot.getRe_usingglass_add());

			notAvailableSlotForm.setRe_ar_s(notAvailableSlot.getRe_ar_s());
			notAvailableSlotForm.setRe_ar_c(notAvailableSlot.getRe_ar_c());
			notAvailableSlotForm.setRe_ar_a(notAvailableSlot.getRe_ar_a());
			notAvailableSlotForm.setRe_ar_va(notAvailableSlot.getRe_ar_va());
			notAvailableSlotForm.setRe_ar_nv(notAvailableSlot.getRe_ar_nv());
			notAvailableSlotForm.setRe_ar_add(notAvailableSlot.getRe_ar_add());

			notAvailableSlotForm.setRe_ace_s(notAvailableSlot.getRe_ace_s());
			notAvailableSlotForm.setRe_ace_c(notAvailableSlot.getRe_ace_c());
			notAvailableSlotForm.setRe_ace_a(notAvailableSlot.getRe_ace_a());
			notAvailableSlotForm.setRe_ace_va(notAvailableSlot.getRe_ace_va());
			notAvailableSlotForm.setRe_ace_nv(notAvailableSlot.getRe_ace_nv());
			notAvailableSlotForm.setRe_ace_add(notAvailableSlot.getRe_ace_add());

			notAvailableSlotForm.setLe_usingglass_s(notAvailableSlot.getLe_usingglass_s());
			notAvailableSlotForm.setLe_usingglass_c(notAvailableSlot.getLe_usingglass_c());
			notAvailableSlotForm.setLe_usingglass_a(notAvailableSlot.getLe_usingglass_a());
			notAvailableSlotForm.setLe_usingglass_va(notAvailableSlot.getLe_usingglass_va());
			notAvailableSlotForm.setLe_usingglass_nv(notAvailableSlot.getLe_usingglass_nv());
			notAvailableSlotForm.setLe_usingglass_add(notAvailableSlot.getLe_usingglass_add());

			notAvailableSlotForm.setLe_ar_s(notAvailableSlot.getLe_ar_s());
			notAvailableSlotForm.setLe_ar_c(notAvailableSlot.getLe_ar_c());
			notAvailableSlotForm.setLe_ar_a(notAvailableSlot.getLe_ar_a());
			notAvailableSlotForm.setLe_ar_va(notAvailableSlot.getLe_ar_va());
			notAvailableSlotForm.setLe_ar_nv(notAvailableSlot.getLe_ar_nv());
			notAvailableSlotForm.setLe_ar_add(notAvailableSlot.getLe_ar_add());

			notAvailableSlotForm.setLe_ace_s(notAvailableSlot.getLe_ace_s());
			notAvailableSlotForm.setLe_ace_c(notAvailableSlot.getLe_ace_c());
			notAvailableSlotForm.setLe_ace_a(notAvailableSlot.getLe_ace_a());
			notAvailableSlotForm.setLe_ace_va(notAvailableSlot.getLe_ace_va());
			notAvailableSlotForm.setLe_ace_nv(notAvailableSlot.getLe_ace_nv());
			notAvailableSlotForm.setLe_ace_add(notAvailableSlot.getLe_ace_add());

			notAvailableSlotForm.setLens_left1(notAvailableSlot.getLens_left1());
			notAvailableSlotForm.setLens_left2(notAvailableSlot.getLens_left2());
			notAvailableSlotForm.setDiagnosisarea(notAvailableSlot.getDiagnosisarea());
			notAvailableSlotForm.setLens_right1(notAvailableSlot.getLens_right1());
			notAvailableSlotForm.setLens_right2(notAvailableSlot.getLens_right2());
			notAvailableSlotForm.setFollowup(notAvailableSlot.getFollowup());

		} catch (Exception e) {
			e.printStackTrace();
		} finally {

			connection.close();
		}
		return "blankLetterHeadprint";
	}

	public String otnotes() throws Exception {
		Connection connection = null;
		try {
			connection = Connection_provider.getconnection();
			MasterDAO masterDAO = new JDBCMasterDAO(connection);
			NotAvailableSlotDAO notAvailableSlotDAO = new JDBCNotAvailableSlotDAO(connection);

			String apmtid = request.getParameter("apmtid");

			ArrayList<Master> multiotImgList = notAvailableSlotDAO.getMultiOtImgList(apmtid, "");
			session.setAttribute("multiotImgList", multiotImgList);

			int total = 0;

			int size = multiotImgList.size();
			if (size > 0) {
				total = multiotImgList.get(size - 1).getCount();
			} else {
				total = 0;
			}

			if (apmtid.equals("0")) {
				String clientid = request.getParameter("clientid");
				apmtid = masterDAO.getApmtidFromClientid(clientid);
			}

			session.setAttribute("otnotesapmtid", apmtid);
			setotformdata(apmtid);
			int surgeonid = notAvailableSlotForm.getSurgeonid();
			int specialityid = notAvailableSlotDAO.getSpeciSurgonFromRefernce(surgeonid);
			Master master1 = masterDAO.getDisciplineData(String.valueOf(specialityid));

			String surgical_template = masterDAO.getIpdTemplateId("OT Template");
			// ArrayList<Master> otherTemplateList =
			// masterDAO.getIpdTemplateListNames(surgical_template);
			ArrayList<Master> otherTemplateList = masterDAO.getIpdTemplateBySpeciality(surgical_template,
					master1.getDiscipline());
			notAvailableSlotForm.setOtherTemplateList(otherTemplateList);
			// notAvailableSlotForm.setVisitingtimeList(PopulateList.startTimeList());
			Clinic clinic = new Clinic();
			ClinicDAO clinicDAO = new JDBCClinicDAO(connection);
			clinic = clinicDAO.getCliniclistDetails(loginInfo.getClinicid());
			notAvailableSlotForm.setClinicName(clinic.getClinicName());
			notAvailableSlotForm.setClinicOwner(clinic.getClinicOwner());
			notAvailableSlotForm.setOwner_qualification(clinic.getOwner_qualification());
			notAvailableSlotForm.setLandLine(clinic.getLandLine());
			notAvailableSlotForm.setClinicemail(clinic.getEmail());
			notAvailableSlotForm.setWebsiteUrl(clinic.getWebsiteUrl());
			notAvailableSlotForm.setClinicLogo(clinic.getUserImageFileName());

			if (total == 0) {
				ArrayList<String> tempstringlist = new ArrayList<String>();
				tempstringlist.add("0");
				notAvailableSlotForm.setTempstringlist(tempstringlist);
				notAvailableSlotForm.setTempaddmorecount("0");
				session.setAttribute("otnotes0", "");
				session.setAttribute("imageData0", "");
				session.setAttribute("tempstringlist", tempstringlist);
				for (int i = 0; i <= 10; i++) {
					session.setAttribute("otnotes" + i + "", "");
					session.setAttribute("imageData" + i + "", "");
				}

			} else {
				int t = 0;
				ArrayList<String> tempstringlist = new ArrayList<String>();
				for (Master master : multiotImgList) {
					session.setAttribute("imageData" + t + "", master.getName());
					session.setAttribute("otnotes" + t + "", master.getOtnotes());
					tempstringlist.add("" + t);
					t++;
				}
				notAvailableSlotForm.setTempstringlist(tempstringlist);
				int tt = total - 1;
				notAvailableSlotForm.setTempaddmorecount("" + tt);
				session.setAttribute("tempstringlist", tempstringlist);
			}

			notAvailableSlotForm.setViewtype("Company");

		} catch (Exception e) {
			e.printStackTrace();
		} finally {

			connection.close();
		}

		return "otnotes";
	}

	public String video() throws Exception {

		Connection connection = null;
		try {
			connection = Connection_provider.getconnection();
			MasterDAO masterDAO = new JDBCMasterDAO(connection);
			NotAvailableSlotDAO notAvailableSlotDAO = new JDBCNotAvailableSlotDAO(connection);

			String otnotesapmtid = (String) session.getAttribute("otnotesapmtid");
			String savemoreid = request.getParameter("savemoreid");
			// System.out.println(savemoreid);
			String otnotes2 = notAvailableSlotForm.getNewotnotes();

			// save multi ot img
			/*
			 * boolean checkDataExsist =
			 * notAvailableSlotDAO.checkMultiOtDataExist(otnotesapmtid,
			 * savemoreid); if(!checkDataExsist){ int r =
			 * notAvailableSlotDAO.saveMultiImgData(otnotesapmtid,data22,
			 * savemoreid,otnotes2); }else{ int u =
			 * notAvailableSlotDAO.updateMultimgotData(otnotesapmtid,data22,
			 * savemoreid,otnotes2); }
			 */

		} catch (Exception e) {
			// TODO: handle exception
		} finally {

			connection.close();
		}

		return "otnotes";
	}

	public String otimg() throws Exception {
		Connection connection = null;
		try {
			connection = Connection_provider.getconnection();
			MasterDAO masterDAO = new JDBCMasterDAO(connection);
			NotAvailableSlotDAO notAvailableSlotDAO = new JDBCNotAvailableSlotDAO(connection);
			String otnotesapmtid = (String) session.getAttribute("otnotesapmtid");
			// String otnotes = notAvailableSlotForm.getOtnotes();
			setotformdata(otnotesapmtid);

			String otnotes2 = notAvailableSlotForm.getNewotnotes();
			notAvailableSlotForm.setOtnotes(otnotes2);

			String data22 = notAvailableSlotForm.getImageData();
			String savemoreid = request.getParameter("savemoreid");
			/*
			 * String data = request.getParameter("imageData"+savemoreid);
			 * String imageData = data;
			 * session.setAttribute("imageData"+savemoreid, imageData); String
			 * otnotes = request.getParameter("otnotes"+savemoreid);
			 * session.setAttribute("otnotes"+savemoreid, otnotes);
			 */
			ArrayList<Master> multiotImgList = new ArrayList<Master>();

			String tempcount = request.getParameter("tempaddmorecount");
			if (notAvailableSlotForm.getViewtype().equals("Individual")) {
				tempcount = "0";
			} else {

				if (savemoreid != null) {
					if (!savemoreid.equals("")) {
						// save multi ot img
						boolean checkDataExsist = notAvailableSlotDAO.checkMultiOtDataExist(otnotesapmtid, savemoreid);
						if (!checkDataExsist) {
							int r = notAvailableSlotDAO.saveMultiImgData(otnotesapmtid, data22, savemoreid, otnotes2);
						} else {
							int u = notAvailableSlotDAO.updateMultimgotData(otnotesapmtid, data22, savemoreid,
									otnotes2);
						}
					}
				}

				multiotImgList = notAvailableSlotDAO.getMultiOtImgList(otnotesapmtid, savemoreid);
				session.setAttribute("multiotImgList", multiotImgList);
				int t = 0;
				for (Master master : multiotImgList) {
					session.setAttribute("imageData" + t + "", master.getName());
					String name = master.getOtnotes();
					name = request.getParameter("ottempnotes" + t);
					session.setAttribute("otnotes" + t + "", name);
					/*
					 * if(savemoreid!=null){ if (!savemoreid.equals("")) {
					 * if(Integer.parseInt(savemoreid)==t){ break; } } }
					 */
					t++;
				}

			}

			int count = Integer.parseInt(tempcount);
			ArrayList<String> tempstringlist = new ArrayList<String>();

			// count++;
			for (int i = 0; i <= count; i++) {
				/*
				 * String otnotes0 = request.getParameter("otnotes"+i+"");
				 * String data1 = request.getParameter("imageData"+i+""); String
				 * imageData2 = data22; //session.setAttribute("imageData"+i+"",
				 * imageData2); session.setAttribute("otnotes"+i+"", otnotes0);
				 */
				tempstringlist.add("" + i);
			}
			notAvailableSlotForm.setTempstringlist(tempstringlist);
			notAvailableSlotForm.setTempaddmorecount("" + count);
			session.setAttribute("tempstringlist", tempstringlist);

			int surgeonid = notAvailableSlotForm.getSurgeonid();
			int specialityid = notAvailableSlotDAO.getSpeciSurgonFromRefernce(surgeonid);
			Master master1 = masterDAO.getDisciplineData(String.valueOf(specialityid));

			String surgical_template = masterDAO.getIpdTemplateId("OT Template");
			ArrayList<Master> otherTemplateList = masterDAO.getIpdTemplateBySpeciality(surgical_template,
					master1.getDiscipline());
			/*
			 * String surgical_template =
			 * masterDAO.getIpdTemplateId("OT Template"); ArrayList<Master>
			 * otherTemplateList =
			 * masterDAO.getIpdTemplateListNames(surgical_template);
			 */
			notAvailableSlotForm.setOtherTemplateList(otherTemplateList);
			// notAvailableSlotForm.setVisitingtimeList(PopulateList.startTimeList());
			Clinic clinic = new Clinic();
			ClinicDAO clinicDAO = new JDBCClinicDAO(connection);
			clinic = clinicDAO.getCliniclistDetails(loginInfo.getClinicid());
			notAvailableSlotForm.setClinicName(clinic.getClinicName());
			notAvailableSlotForm.setClinicOwner(clinic.getClinicOwner());
			notAvailableSlotForm.setOwner_qualification(clinic.getOwner_qualification());
			notAvailableSlotForm.setLandLine(clinic.getLandLine());
			notAvailableSlotForm.setClinicemail(clinic.getEmail());
			notAvailableSlotForm.setWebsiteUrl(clinic.getWebsiteUrl());
			notAvailableSlotForm.setClinicLogo(clinic.getUserImageFileName());
			String viewtype = request.getParameter("viewtype");
			notAvailableSlotForm.setViewtype(viewtype);

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			connection.close();
		}
		return "otnotes";
	}

	public String otaddmore() throws Exception {
		Connection connection = null;
		try {
			connection = Connection_provider.getconnection();
			MasterDAO masterDAO = new JDBCMasterDAO(connection);
			NotAvailableSlotDAO notAvailableSlotDAO = new JDBCNotAvailableSlotDAO(connection);
			String otnotesapmtid = (String) session.getAttribute("otnotesapmtid");

			ArrayList<Master> multiotImgList = notAvailableSlotDAO.getMultiOtImgList(otnotesapmtid, "");
			session.setAttribute("multiotImgList", multiotImgList);

			setotformdata(otnotesapmtid);
			/*
			 * String otnotes = notAvailableSlotForm.getOtnotes(); String data =
			 * notAvailableSlotForm.getImageData(); String imageData = data;
			 * session.setAttribute("imageData", imageData);
			 * session.setAttribute("otnotes", otnotes);
			 * notAvailableSlotForm.setOtnotes(otnotes);
			 */
			String actiontype = request.getParameter("actiontype");
			String viewtype = request.getParameter("viewtype");
			String tempcount = request.getParameter("tempaddmorecount");
			ArrayList<String> tempstringlist = new ArrayList<String>();
			if (actiontype.equals("1")) {
				int count = Integer.parseInt(tempcount);
				int tcount = count;
				count++;
				for (int i = 0; i <= count; i++) {
					if (Integer.parseInt(tempcount) == tcount) {
						String ottempnotes = request.getParameter("ottempnotes" + i);
						session.setAttribute("otnotes" + i + "", ottempnotes);
					}
					tempstringlist.add("" + i);
				}
				notAvailableSlotForm.setTempstringlist(tempstringlist);
				notAvailableSlotForm.setTempaddmorecount("" + count);
			} else {
				int count = Integer.parseInt(tempcount);
				for (int i = 0; i <= count; i++) {
					tempstringlist.add("" + i);
				}
				notAvailableSlotForm.setTempstringlist(tempstringlist);
				notAvailableSlotForm.setTempaddmorecount("" + count);
			}

			session.setAttribute("tempstringlist", tempstringlist);

			int surgeonid = notAvailableSlotForm.getSurgeonid();
			int specialityid = notAvailableSlotDAO.getSpeciSurgonFromRefernce(surgeonid);
			Master master1 = masterDAO.getDisciplineData(String.valueOf(specialityid));

			String surgical_template = masterDAO.getIpdTemplateId("OT Template");
			ArrayList<Master> otherTemplateList = masterDAO.getIpdTemplateBySpeciality(surgical_template,
					master1.getDiscipline());
			/*
			 * String surgical_template =
			 * masterDAO.getIpdTemplateId("OT Template"); ArrayList<Master>
			 * otherTemplateList =
			 * masterDAO.getIpdTemplateListNames(surgical_template);
			 */notAvailableSlotForm.setOtherTemplateList(otherTemplateList);

			Clinic clinic = new Clinic();
			ClinicDAO clinicDAO = new JDBCClinicDAO(connection);
			clinic = clinicDAO.getCliniclistDetails(loginInfo.getClinicid());
			notAvailableSlotForm.setClinicName(clinic.getClinicName());
			notAvailableSlotForm.setClinicOwner(clinic.getClinicOwner());
			notAvailableSlotForm.setOwner_qualification(clinic.getOwner_qualification());
			notAvailableSlotForm.setLandLine(clinic.getLandLine());
			notAvailableSlotForm.setClinicemail(clinic.getEmail());
			notAvailableSlotForm.setWebsiteUrl(clinic.getWebsiteUrl());
			notAvailableSlotForm.setClinicLogo(clinic.getUserImageFileName());
			notAvailableSlotForm.setViewtype(viewtype);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			connection.close();
		}
		return "otnotes";
	}

	public String saveotnotes() throws Exception {
		Connection connection = null;
		try {
			connection = Connection_provider.getconnection();
			NotAvailableSlotDAO notAvailableSlotDAO = new JDBCNotAvailableSlotDAO(connection);
			NotAvailableSlot notAvailableSlot = new NotAvailableSlot();
			notAvailableSlot.setTimeofincision(notAvailableSlotForm.getTimeofincision());
			notAvailableSlot.setAnsintime(notAvailableSlotForm.getAnsintime());
			notAvailableSlot.setOtnotes(notAvailableSlotForm.getOtnotes());
			String imageData = (String) session.getAttribute("imageData");
			int upd = notAvailableSlotDAO.updateOtnotes(notAvailableSlot, notAvailableSlotForm.getId(), imageData);

			String tempcount = request.getParameter("tempaddmorecount");
			int count = Integer.parseInt(tempcount);
			for (int i = 0; i <= count; i++) {
				String otnotes = request.getParameter("otnotes" + i);
				String data22 = (String) session.getAttribute("imageData" + i);
				boolean checkDataExsist = notAvailableSlotDAO.checkMultiOtDataExist("" + notAvailableSlotForm.getId(),
						"" + i);
				if (!checkDataExsist) {
					int r = notAvailableSlotDAO.saveMultiImgData("" + notAvailableSlotForm.getId(), data22, "" + i,
							otnotes);
				} else {
					int u = notAvailableSlotDAO.updateMultimgotData("" + notAvailableSlotForm.getId(), data22, "" + i,
							otnotes);
				}
			}
			setotformdata(Integer.toString(notAvailableSlotForm.getId()));

			ArrayList<Master> multiotImgList = notAvailableSlotDAO
					.getMultiOtImgList(Integer.toString(notAvailableSlotForm.getId()), "");
			session.setAttribute("multiotImgList", multiotImgList);

			int total = 0;

			int size = multiotImgList.size();
			if (size > 0) {
				total = multiotImgList.get(size - 1).getCount();
			} else {
				total = 0;
			}

			if (total == 0) {
				ArrayList<String> tempstringlist = new ArrayList<String>();
				tempstringlist.add("0");
				notAvailableSlotForm.setTempstringlist(tempstringlist);
				notAvailableSlotForm.setTempaddmorecount("0");
				session.setAttribute("otnotes0", "");
				session.setAttribute("tempstringlist", tempstringlist);
			} else {
				int t = 0;
				ArrayList<String> tempstringlist = new ArrayList<String>();
				for (Master master : multiotImgList) {
					String name = master.getName();
					String otnotes = master.getOtnotes();
					session.setAttribute("imageData" + t + "", master.getName());
					session.setAttribute("otnotes" + t + "", master.getOtnotes());
					tempstringlist.add("" + t);
					t++;
				}
				notAvailableSlotForm.setTempstringlist(tempstringlist);
				int tt = total - 1;
				notAvailableSlotForm.setTempaddmorecount("" + tt);
				session.setAttribute("tempstringlist", tempstringlist);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return "printotnotes";
	}

	public String gettaskdetails() throws Exception {
		Connection connection = null;
		try {
			String id = request.getParameter("subtask");
			connection = Connection_provider.getconnection();
			MasterDAO masterDAO = new JDBCMasterDAO(connection);
			// NotAvailableSlotDAO notAvailableSlotDAO = new
			// JDBCNotAvailableSlotDAO(connection);
			ArrayList<Master> list = masterDAO.getSubTasklist(id);

			StringBuffer buffer = new StringBuffer();
			buffer.append(
					"<select class='form-control showToolTip chosen' onchange ='selectSubTask(this.value)' name='subtask' id='subtask'>");
			buffer.append("<option value='0'>Select SubTask</option>");

			for (Master master : list) {
				buffer.append("<option value ='" + master.getId() + "'>" + master.getSubtask() + "</option>");
			}
			buffer.append("</select>");
			response.setContentType("text/html");
			response.setHeader("Cache-Control", "no-cache");
			response.getWriter().write(buffer.toString());

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			connection.close();
		}
		return null;

	}

	public String getsubtaskdetails() throws Exception {

		Connection connection = null;
		try {
			String id = request.getParameter("notes");
			connection = Connection_provider.getconnection();
			MasterDAO masterDAO = new JDBCMasterDAO(connection);
			Master master = new Master();
			master.setId(Integer.parseInt(id));
			String notes = masterDAO.getNotesList(master);

			response.setContentType("text/html");
			response.setHeader("Cache-Control", "no-cache");
			response.getWriter().write("" + notes + "");

		} catch (Exception e) {
			e.printStackTrace();
		} finally {

			connection.close();
		}
		return null;

	}

	public String chageotemplate() throws Exception {
		Connection connection = null;
		try {
			connection = Connection_provider.getconnection();
			NotAvailableSlotDAO notAvailableSlotDAO = new JDBCNotAvailableSlotDAO(connection);
			JDBCMasterDAO masterDAO = new JDBCMasterDAO(connection);
			String id = request.getParameter("name");
			String tempsrno = request.getParameter("tempsrno");
			StringBuffer stringBuffer = new StringBuffer();

			Master master1 = masterDAO.getDisciplineData(id);
			String surgical_template = masterDAO.getIpdTemplateId("OT Template");
			ArrayList<Master> otherTemplateList = masterDAO.getIpdTemplateBySpeciality(surgical_template,
					master1.getDiscipline());

			stringBuffer.append("<label for='exampleInputEmail1'>Operation Notes</label>");
			stringBuffer.append("<select name='template" + tempsrno + "' id='template" + tempsrno
					+ "' onchange='getNewOTtemplate(this.value," + tempsrno
					+ ")' class='form-control showToolTip chosen-select'>");
			stringBuffer.append("<option value='0'>Select Template</option>");
			for (Master master : otherTemplateList) {
				stringBuffer.append("<option value='" + master.getId() + "'>" + master.getName() + "</option>");
			}
			stringBuffer.append("</select>");
			response.setContentType("text/html");
			response.setHeader("Cache-Control", "no-cache");
			response.getWriter().write(stringBuffer.toString());
		} catch (Exception e) {
			e.printStackTrace();
		} finally {

			connection.close();
		}

		return null;
	}

	public String addnewprocudure() throws Exception {
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
			String addprocedurename = jsonObject.getString("addprocedurename");
			String addproceduredescription = jsonObject.getString("addproceduredescription");
			Master master = new Master();

			master.setName(addprocedurename);
			master.setDescription(addproceduredescription);
			master.setProcedure(true);
			int res = masterDAO.addNewChargeType(master);

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

	public String printotnotes() throws Exception {
		Connection connection = null;
		try {
			connection = Connection_provider.getconnection();
			NotAvailableSlotDAO notAvailableSlotDAO = new JDBCNotAvailableSlotDAO(connection);
			NotAvailableSlot notAvailableSlot = new NotAvailableSlot();
			String id = request.getParameter("id");
			setotformdata(id);

			ClinicDAO clinicDAO = new JDBCClinicDAO(connection);
			Clinic clinic = clinicDAO.getCliniclistDetails(loginInfo.getClinicid());

			ArrayList<Master> multiotImgList = notAvailableSlotDAO.getMultiOtImgList(id, "");
			session.setAttribute("multiotImgList", multiotImgList);

			int total = 0;

			int size = multiotImgList.size();
			if (size > 0) {
				total = multiotImgList.get(size - 1).getCount();
			} else {
				total = 0;
			}

			if (total == 0) {
				ArrayList<String> tempstringlist = new ArrayList<String>();
				tempstringlist.add("0");
				notAvailableSlotForm.setTempstringlist(tempstringlist);
				notAvailableSlotForm.setTempaddmorecount("0");
				session.setAttribute("otnotes0", "");
				session.setAttribute("tempstringlist", tempstringlist);
			} else {
				int t = 0;
				ArrayList<String> tempstringlist = new ArrayList<String>();
				for (Master master : multiotImgList) {
					String name = master.getName();
					String otnotes = master.getOtnotes();
					session.setAttribute("imageData" + t + "", master.getName());
					session.setAttribute("otnotes" + t + "", master.getOtnotes());
					tempstringlist.add("" + t);
					t++;
				}
				notAvailableSlotForm.setTempstringlist(tempstringlist);
				int tt = total - 1;
				notAvailableSlotForm.setTempaddmorecount("" + tt);
				session.setAttribute("tempstringlist", tempstringlist);

			}
			notAvailableSlotForm.setClinicLogo(clinic.getUserImageFileName());
		} catch (Exception e) {
			e.printStackTrace();
		} finally {

			connection.close();
		}

		return "printotnotes";
	}

	public String setappointmentprocedure() throws Exception {
		Connection connection = null;
		try {

			connection = Connection_provider.getconnection();
			IpdDAO ipdDAO = new JDBCIpdDAO(connection);
			NotAvailableSlotDAO notAvailableSlotDAO = new JDBCNotAvailableSlotDAO(connection);
			MasterDAO masterDAO = new JDBCMasterDAO(connection);
			String editAppointId = request.getParameter("editAppointId");
			NotAvailableSlot notAvailableSlot = notAvailableSlotDAO.getProcedureDepartment(editAppointId);
			ArrayList<Master> procedureList = notAvailableSlotDAO.getProcedureList(notAvailableSlot.getDept());

			StringBuffer str = new StringBuffer();

			str.append(
					"<select onchange='filterOtCharges(this.value)' name='otprocedureplaned' id='otprocedureplaned' class='form-control showToolTip chosen' >");
			str.append("<option value='0'>Select Procedure</option>");
			for (Master master : procedureList) {
				//   30-March-2018
				if (notAvailableSlot.getId() == master.getId()) {
					str.append("<option selected value='" + master.getId() + "'>" + master.getName() + "</option>");
				} else {
					str.append("<option value='" + master.getId() + "'>" + master.getName() + "</option>");
				}
			}
			str.append("</select>");

			str.append("~");

			ArrayList<Master> list = ipdDAO.getOtFilteredChargeList(notAvailableSlot.getProcedure(),
					notAvailableSlot.getClientId());

			str.append(
					"<select onchange='setAppointmentTypeTimeAjax(this.value)' name='duration' id='apmtType' class='form-control showToolTip chosen' >");
			str.append("<option value='0'>Select Charge</option>");
			for (Master master : list) {
				/*
				 * str.append("<option value='"+master.getId()+"'>"+master.
				 * getName()+"</option>");
				 */
				if (notAvailableSlot.getAppointmentTypeid() == master.getId()) {
					str.append(
							"<option selected value='" + master.getId() + "'>" + master.getChargetype() + "</option>");
				} else {
					str.append("<option value='" + master.getId() + "'>" + master.getChargetype() + "</option>");
				}
			}
			str.append("</select>");

			response.setContentType("text/html");
			response.setHeader("Cache-Control", "no-cache");
			response.getWriter().write("" + str.toString() + "");

		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	public String immunizationchart() throws Exception {
		Connection connection = null;
		try {
			connection = Connection_provider.getconnection();
			ClientDAO clientDAO = new JDBCClientDAO(connection);
			String clientid = request.getParameter("clientId");
			NotAvailableSlotDAO notAvailableSlotDAO = new JDBCNotAvailableSlotDAO(connection);
			Client client = clientDAO.getPatient(Integer.parseInt(clientid));
			notAvailableSlotForm.setClientId(clientid);
			notAvailableSlotForm.setAbrivationid(client.getAbrivationid());
			notAvailableSlotForm.setFullname(client.getFullname());
			Boolean flag = notAvailableSlotDAO.checkimmurtizationdata(clientid);
			if (!flag) {
				int id1 = notAvailableSlotDAO.saveimmurtizationdata(clientid);
			}
			Master availableSlot = notAvailableSlotDAO.getImmurtizeData(clientid);
			notAvailableSlotForm.setId(availableSlot.getId());
			notAvailableSlotForm.setBcg(availableSlot.getBcg());
			notAvailableSlotForm.setOpv0(availableSlot.getOpv0());
			notAvailableSlotForm.setHep_b1(availableSlot.getHep_b1());
			notAvailableSlotForm.setDtwp1(availableSlot.getDtwp1());
			notAvailableSlotForm.setIpv1(availableSlot.getIpv1());
			notAvailableSlotForm.setHep_b2(availableSlot.getHep_b2());
			notAvailableSlotForm.setHib1(availableSlot.getHib1());
			notAvailableSlotForm.setRotavirus1(availableSlot.getRotavirus1());
			notAvailableSlotForm.setPcv1(availableSlot.getPcv1());

			notAvailableSlotForm.setDtwp2(availableSlot.getDtwp2());
			notAvailableSlotForm.setIpv2(availableSlot.getIpv2());
			notAvailableSlotForm.setHib2(availableSlot.getHib2());
			notAvailableSlotForm.setRotavirus2(availableSlot.getRotavirus2());
			notAvailableSlotForm.setPcv2(availableSlot.getPcv2());
			notAvailableSlotForm.setDtwp3(availableSlot.getDtwp3());
			notAvailableSlotForm.setIpv3(availableSlot.getIpv3());

			notAvailableSlotForm.setHib3(availableSlot.getHib3());
			notAvailableSlotForm.setRotavirus3(availableSlot.getRotavirus3());
			notAvailableSlotForm.setPcv3(availableSlot.getPcv3());
			notAvailableSlotForm.setOpv1(availableSlot.getOpv1());
			notAvailableSlotForm.setHepb3(availableSlot.getHepb3());
			notAvailableSlotForm.setOpv2(availableSlot.getOpv2());
			notAvailableSlotForm.setMmr1(availableSlot.getMmr1());
			notAvailableSlotForm.setTyphoid_conjugate(availableSlot.getTyphoid_conjugate());
			notAvailableSlotForm.setVaccine(availableSlot.getVaccine());

			notAvailableSlotForm.setHepa1(availableSlot.getHepa1());
			notAvailableSlotForm.setMmr2(availableSlot.getMmr2());
			notAvailableSlotForm.setVaricella1(availableSlot.getVaricella1());
			notAvailableSlotForm.setPcvbooster(availableSlot.getPcvbooster());
			notAvailableSlotForm.setDtwpb1dtapb1(availableSlot.getDtwpb1dtapb1());
			notAvailableSlotForm.setIpvb1(availableSlot.getIpvb1());
			notAvailableSlotForm.setHibb1(availableSlot.getHibb1());
			notAvailableSlotForm.setHepa2(availableSlot.getHepa2());
			notAvailableSlotForm.setBoosteroftyphoid(availableSlot.getBoosteroftyphoid());
			notAvailableSlotForm.setConjugatevaccine(availableSlot.getConjugatevaccine());
			notAvailableSlotForm.setDtwpb2dtapb2(availableSlot.getDtwpb2dtapb2());
			notAvailableSlotForm.setTdaptd(availableSlot.getTdaptd());
			notAvailableSlotForm.setHpv(availableSlot.getHpv());

			notAvailableSlotForm.setBcgdt(availableSlot.getBcgdt());
			notAvailableSlotForm.setOpv0dt(availableSlot.getOpv0dt());
			notAvailableSlotForm.setHep_b1dt(availableSlot.getHep_b1dt());
			notAvailableSlotForm.setDtwp1dt(availableSlot.getDtwp1dt());
			notAvailableSlotForm.setIpv1dt(availableSlot.getIpv1dt());
			notAvailableSlotForm.setHep_b2dt(availableSlot.getHep_b2dt());
			notAvailableSlotForm.setHib1dt(availableSlot.getHib1dt());
			notAvailableSlotForm.setRotavirus1dt(availableSlot.getRotavirus1dt());
			notAvailableSlotForm.setPcv1dt(availableSlot.getPcv1dt());

			notAvailableSlotForm.setDtwp2dt(availableSlot.getDtwp2dt());
			notAvailableSlotForm.setIpv2dt(availableSlot.getIpv2dt());
			notAvailableSlotForm.setHib2dt(availableSlot.getHib2dt());
			notAvailableSlotForm.setRotavirus2dt(availableSlot.getRotavirus2dt());
			notAvailableSlotForm.setPcv2dt(availableSlot.getPcv2dt());
			notAvailableSlotForm.setDtwp3dt(availableSlot.getDtwp3dt());
			notAvailableSlotForm.setIpv3dt(availableSlot.getIpv3dt());

			notAvailableSlotForm.setHib3dt(availableSlot.getHib3dt());
			notAvailableSlotForm.setRotavirus3dt(availableSlot.getRotavirus3dt());
			notAvailableSlotForm.setPcv3dt(availableSlot.getPcv3dt());
			notAvailableSlotForm.setOpv1dt(availableSlot.getOpv1dt());
			notAvailableSlotForm.setHepb3dt(availableSlot.getHepb3dt());
			notAvailableSlotForm.setOpv2dt(availableSlot.getOpv2dt());
			notAvailableSlotForm.setMmr1dt(availableSlot.getMmr1dt());
			notAvailableSlotForm.setTyphoid_conjugatedt(availableSlot.getTyphoid_conjugatedt());
			notAvailableSlotForm.setVaccinedt(availableSlot.getVaccinedt());

			notAvailableSlotForm.setHepa1dt(availableSlot.getHepa1dt());
			notAvailableSlotForm.setMmr2dt(availableSlot.getMmr2dt());
			notAvailableSlotForm.setVaricella1dt(availableSlot.getVaricella1dt());
			notAvailableSlotForm.setPcvboosterdt(availableSlot.getPcvboosterdt());
			notAvailableSlotForm.setDtwpb1dtapb1dt(availableSlot.getDtwpb1dtapb1dt());
			notAvailableSlotForm.setIpvb1dt(availableSlot.getIpvb1dt());
			notAvailableSlotForm.setHibb1dt(availableSlot.getHibb1dt());
			notAvailableSlotForm.setHepa2dt(availableSlot.getHepa2dt());
			notAvailableSlotForm.setBoosteroftyphoiddt(availableSlot.getBoosteroftyphoiddt());
			notAvailableSlotForm.setConjugatevaccinedt(availableSlot.getConjugatevaccinedt());
			notAvailableSlotForm.setDtwpb2dtapb2dt(availableSlot.getDtwpb2dtapb2dt());
			notAvailableSlotForm.setTdaptddt(availableSlot.getTdaptddt());
			notAvailableSlotForm.setHpvdt(availableSlot.getHpvdt());

			notAvailableSlotForm.setRemark1(availableSlot.getRemark1());
			notAvailableSlotForm.setRemark2(availableSlot.getRemark2());
			notAvailableSlotForm.setRemark3(availableSlot.getRemark3());
			notAvailableSlotForm.setRemark4(availableSlot.getRemark4());
			notAvailableSlotForm.setRemark5(availableSlot.getRemark5());
			notAvailableSlotForm.setRemark6(availableSlot.getRemark6());
			notAvailableSlotForm.setRemark7(availableSlot.getRemark7());
			notAvailableSlotForm.setRemark8(availableSlot.getRemark8());
			notAvailableSlotForm.setRemark9(availableSlot.getRemark9());
			notAvailableSlotForm.setRemark10(availableSlot.getRemark10());
			notAvailableSlotForm.setRemark11(availableSlot.getRemark11());
			notAvailableSlotForm.setRemark12(availableSlot.getRemark12());
			notAvailableSlotForm.setRemark13(availableSlot.getRemark13());
			notAvailableSlotForm.setRemark14(availableSlot.getRemark14());
			notAvailableSlotForm.setRemark15(availableSlot.getRemark15());

			notAvailableSlotForm.setClientId(clientid);
			notAvailableSlotForm.setDob(availableSlot.getDob());
			notAvailableSlotForm.setFullname(availableSlot.getFullname());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "immunizationchart";
	}

	public String saveantinatalfromvitals(){
		try {
			String clientid = request.getParameter("clientId");
			String type = DateTimeUtils.isNull(request.getParameter("type"));

			immunizationProcess(clientid, type,true);
			response.setContentType("text/html");
			response.setHeader("Cache-Control", "no-cache");
			response.getWriter().write("1");

		} catch (Exception e) {
			e.printStackTrace();
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
					if (master == null) {
						continue;
					}

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
	
	
	public String savevacinationimmunizationajax() {
		Connection connection = null;
		try {
			connection = Connection_provider.getconnection();
			StringBuilder buffer1 = new StringBuilder();
			BufferedReader reader = request.getReader();
			SmsService s = new SmsService();
			WhatsAppService wsms=new WhatsAppService();
			UserProfileDAO userProfileDAO = new JDBCUserProfileDAO(connection);
			ClinicDAO clinicDAO = new JDBCClinicDAO(connection);
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
			String  templateid = userProfileDAO.getSMSTemplateID("Vaccination Success");
			NotAvailableSlot vaccdata=notAvailableSlotDAO.getpatientdetails(clientid);
			loginInfo.setPatient_name(vaccdata.getName());
		    loginInfo.setSms_type("Vaccination Success");
			if(loginInfo.isParihar()) {
				
				
				String msg="(परिहार हास्पिटल कवर्धा) "+vaccdata.getName()+" आपका "+mastername+" का टीकाकरण आज "+givendate+" को सफलतापुर्वक सम्पन्न हो गया है फोन 07741232577, 9753059148";
				s.sendvaccineSms(msg, vaccdata.getMobno(), loginInfo, new EmailLetterLog(), connection, templateid);
			}else {
				Clinic clinic1 = clinicDAO.getWhatsappActive(loginInfo.getId());
				if(clinic1.getWsms()==1) {
				String message=loginInfo.getClinicName()+":"+vaccdata.getName()+" आपका "+mastername+" का टीकाकरण आज "+givendate+" को सफलतापुर्वक सम्पन्न हो गया है|";
				wsms.sendMsgvacc(loginInfo,vaccdata.getMobno(), message,connection);
				}
				
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

	public String getBMIdata1() {
		Connection connection = null;
		try {
			connection = Connection_provider.getconnection();
			String clientid = request.getParameter("clientid");
			String apmtid = request.getParameter("apmtid");
			ClientDAO clientDAO = new JDBCClientDAO(connection);
			Client client = clientDAO.getPatientBMIData(clientid, Integer.parseInt(apmtid));
			String resp = client.getHeight() + "~" + client.getWeight() + "~" + client.getBmi() + "~"
					+ client.getPulse() + "~" + client.getSysbp() + "~" + client.getDiabp() + "~"
					+ client.getSugarfasting() + "~" + client.getPostmeal() + "~" + client.getTemprature() + "~"
					+ client.getSpo() + "~" + client.getHead_cir();

			response.setContentType("text/html");
			response.setHeader("Cache-Control", "no-cache");
			response.getWriter().write(resp);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public String getallDiagnosis() {
		try {
			String opdid = request.getParameter("opdid");
			Connection connection = Connection_provider.getconnection();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public String changeduedate() {
		try {
			Connection connection = Connection_provider.getconnection();
			NotAvailableSlotDAO notAvailableSlotDAO = new JDBCNotAvailableSlotDAO(connection);
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
			String value = jsonObject.getString("duedate");
			notAvailableSlotDAO.updateDueDate(value, masterid, clientid);

			response.setContentType("application/json");
			response.setHeader("Cache-Control", "no-cache");
			response.getWriter().write("1");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public String makeapmtvaccine() {
		try {
			Connection connection = Connection_provider.getconnection();

			String clientId = request.getParameter("clientId");
			String type = DateTimeUtils.isNull(request.getParameter("type"));
			if(type.equals("")){
				type="0";
			}
			NotAvailableSlotDAO notAvailableSlotDAO = new JDBCNotAvailableSlotDAO(connection);

			notAvailableSlotDAO.startImmnunizationAppTProccess(clientId, loginInfo,type);
			response.setContentType("application/json");
			response.setHeader("Cache-Control", "no-cache");
			response.getWriter().write("1");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public String existmobileno() {
		Connection connection = null;
		try {
			connection = Connection_provider.getconnection();
			String mobno = request.getParameter("mob");
			ClientDAO clientDAO = new JDBCClientDAO(connection);
			Client client = clientDAO.getabravationidfrommob(mobno);

			String uhid = DateTimeUtils.numberCheck(client.getAbrivationid()) + "~~~"
					+ DateTimeUtils.isNull(client.getEmail()) + "~~~" + DateTimeUtils.isNull(client.getFirstName())
					+ "~~~" + DateTimeUtils.isNull(client.getLastName());

			response.setContentType("application/json");
			response.setHeader("Cache-Control", "no-cache");
			response.getWriter().write(DateTimeUtils.numberCheck(uhid));
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;

	}

	public String saveclientdata() {
		Connection connection = null;
		try {

			connection = Connection_provider.getconnection();
			ClientDAO clientDAO = new JDBCClientDAO(connection);
			String email = request.getParameter("email");
			String fname = request.getParameter("fname");
			String lname = request.getParameter("lname");
			String mob = request.getParameter("mob");
			String dob = request.getParameter("dob");
			String gender = request.getParameter("gender");
			String initial = request.getParameter("title");
			String town_village = request.getParameter("town_village");
			String cdate = DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone()).split(" ")[0];
			boolean checkifseq = clientDAO.checkifSequenceExist(cdate);
			String abrivationid = "";

			String clinicabrivation = clientDAO.getClinicAbrivation(loginInfo.getClinicid());
			String tempd[] = cdate.split("-");
			String y = tempd[0];
			String m = tempd[1];
			String d = tempd[2];
			String newseq = "";
			if (checkifseq) {
				int seqno = clientDAO.getSqeunceNumber(cdate);
				seqno++;
				int r = clientDAO.InserCdateSeq(cdate, seqno);
				// SNH170609001
				int yr = Integer.parseInt(y) % 1000;

				if (String.valueOf(seqno).length() == 1) {
					newseq = "00" + seqno;
				} else if (String.valueOf(seqno).length() == 2) {
					newseq = "0" + seqno;
				} else {
					newseq = "" + seqno;
				}
				/*
				 * abrivationid = clinicabrivation + yr + m +d + "00" + seqno;
				 */
				abrivationid = clinicabrivation + yr + m + d + newseq;
			} else {

				int seqno = 1;
				int r = clientDAO.InserCdateSeq(cdate, seqno);
				// String seqno = clientDAO.getSqeunceNumber(cdate);
				int yr = Integer.parseInt(y) % 1000;
				if (String.valueOf(seqno).length() == 1) {
					newseq = "00" + seqno;
				} else if (String.valueOf(seqno).length() == 2) {
					newseq = "0" + seqno;
				} else {
					newseq = "" + seqno;
				}
				abrivationid = clinicabrivation + yr + m + d + newseq;
			}
			Client client2 = new Client();
			client2.setRelation(DateTimeUtils.isNull(request.getParameter("relation")));
			client2.setRelativename(DateTimeUtils.isNull(request.getParameter("relativename")));
			client2.setRelativeno(DateTimeUtils.isNull(request.getParameter("relativecontact")));
			client2.setState(DateTimeUtils.isNull(request.getParameter("state")));
			client2.setCity(DateTimeUtils.isNull(request.getParameter("city")));
			client2.setAddress(DateTimeUtils.isNull(request.getParameter("address")));
			client2.setDocType(DateTimeUtils.isNull(request.getParameter("docType")));
			client2.setPincode(DateTimeUtils.isNull(request.getParameter("pin")));

			String sess = session.getId();
			String proimg = (String) session.getAttribute("propt" + sess);
			String docimg = (String) session.getAttribute("docpt" + sess);
			String relativeImg = (String) session.getAttribute("relativept" + sess);
			client2.setProfileImg(proimg);
			client2.setDocImg(docimg);
			client2.setRelativeImg(relativeImg);

			client2.setTown_village(town_village);
			{
				session.setAttribute("propt" + sess, null);
				session.setAttribute("docpt" + sess, null);
				session.setAttribute("relativept" + sess, null);
			}

			int clientid = clientDAO.insertintoapm_patient(email, fname, lname, mob, dob, abrivationid, gender, initial,
					client2);
			Client client = clientDAO.getClientDetails("" + clientid);

			response.setContentType("application/json");
			response.setHeader("Cache-Control", "no-cache");
			String data = client.getAbrivationid() + "!@" + loginInfo.getClinicUserid();
			response.getWriter().write(data);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;

	}

	public String getstaffsheduleprintdata() throws Exception {
		if (!verifyLogin(request)) {
			return "login";
		}

		Connection connection = null;
		try {
			connection = Connection_provider.getconnection();
			NotAvailableSlotDAO notAvailableSlotDAO = new JDBCNotAvailableSlotDAO(connection);
			
			String date = notAvailableSlotForm.getPrintCommencing();
			String fromDate = notAvailableSlotForm.getFromDate();
			String toDate = notAvailableSlotForm.getToDate();
			if(!DateTimeUtils.isNull(date).equals("")){
				String temp[] = date.split("/");
				date = temp[2] + "-" + temp[1] + "-" + temp[0];
				fromDate = date;
				toDate = date;
				/*int wyear = Integer.parseInt(temp[2]);
				int month = Integer.parseInt(temp[1]);
				int day = Integer.parseInt(temp[0]);
				String cweekName = DateTimeUtils.getWeekName(wyear, month, day);*/
			}else{
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
				if (toDate == null) {
					DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
					Calendar cal = Calendar.getInstance();
					// cal.add(Calendar.DATE, -7);
					toDate = dateFormat.format(cal.getTime());
					toDate = DateTimeUtils.getCommencingDate1(toDate);
				} else {
					if (toDate.equals("")) {
						DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
						Calendar cal = Calendar.getInstance();
						// cal.add(Calendar.DATE, -7);
						toDate = dateFormat.format(cal.getTime());
						toDate = DateTimeUtils.getCommencingDate1(toDate);
						notAvailableSlotForm.setToDate(toDate);
					} else {
						toDate = DateTimeUtils.getCommencingDate1(toDate);
					}
				}
			}
			
			notAvailableSlotForm.setFromDate(DateTimeUtils.getCommencingDate1(fromDate));
			notAvailableSlotForm.setToDate(DateTimeUtils.getCommencingDate1(toDate));
			
			ArrayList<NotAvailableSlot> shedulestaffreportlist = notAvailableSlotDAO.getSheduleStaffReport(fromDate,toDate);
			notAvailableSlotForm.setShedulestaffreportlist(shedulestaffreportlist);
			Clinic clinic = new Clinic();
			ClinicDAO clinicDAO = new JDBCClinicDAO(connection);
			clinic = clinicDAO.getCliniclistDetails(loginInfo.getClinicid());
			notAvailableSlotForm.setClinicName(clinic.getClinicName());
			notAvailableSlotForm.setClinicOwner(clinic.getClinicOwner());
			notAvailableSlotForm.setOwner_qualification(clinic.getOwner_qualification());
			notAvailableSlotForm.setLandLine(clinic.getLandLine());
			notAvailableSlotForm.setClinicemail(clinic.getEmail());
			notAvailableSlotForm.setWebsiteUrl(clinic.getWebsiteUrl());
			notAvailableSlotForm.setClinicLogo(clinic.getUserImageFileName());

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			connection.close();
		}
		return "printstaffdashboard";
	}
	
	public String test() {
		
		return "test";
	}
	
	public String requestnotelist() throws Exception{
		
		Connection connection = null;
		int i=1;
		
		try {
	    	
	    	String id=request.getParameter("ipdadmissionid");
	    	connection = Connection_provider.getconnection();
	    	EmrDAO emrDAO=new JDBCEmrDAO(connection);
	    	FinderDAO finderDAO=new JDBCFinderDAO(connection);
	    	IpdDAO ipdDAO = new JDBCIpdDAO(connection);
	    	NotAvailableSlotDAO notAvailableSlotDAO = new JDBCNotAvailableSlotDAO(connection);
	    	
	    	String fromDate=notAvailableSlotForm.getFromDate();
	    	ArrayList<Bed> requestnotelist = ipdDAO.getpatientRequestNote(id);
	    	
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

	        StringBuffer buffer=new StringBuffer();
	    	
	    	for (Bed bed : requestnotelist) {
	    		
	    		if(bed.getSeen().equals("1")) {
	    			 buffer.append("<tr style='background-color:red;'>");
	       		     buffer.append("<td>"+i+++"</td>");
	    		     buffer.append("<td>"+bed.getDatetime()+"</td>");
	    		     buffer.append("<td>"+bed.getUserid()+"</td>");
	    		     //buffer.append("<td>"+bed.getNotes()+"</td>");
	    		     buffer.append("<td style='text-align: center;cursor:pointer;' onclick='seennote("+bed.getId()+")'>"+bed.getNotes()+"</td>");
	    		     buffer.append("<td><a href='#' onclick='editrequestnote("+bed.getId()+",1)'><i class='fa fa-edit'></i></a></td>");
	   		         buffer.append("<td><a href='#' onclick='deleterequestnote("+bed.getId()+")'><i class='fa fa-trash-o'></i></a></td>"); 
	   	         //buffer.append("<td></td>"); buffer.append("<td></td>"); }
	   				 
	    		     buffer.append("</tr>");
	    		}else {
	    			buffer.append("<tr>");
	   		        buffer.append("<td>"+i+++"</td>");
			        buffer.append("<td>"+bed.getDatetime()+"</td>");
			        buffer.append("<td>"+bed.getUserid()+"</td>");
			        //buffer.append("<td>"+bed.getNotes()+"</td>");
			        buffer.append("<td style='text-align: center;cursor:pointer;' onclick='seennote("+bed.getId()+")'>"+bed.getNotes()+"</td>");
			        buffer.append("<td><a href='#' onclick='editrequestnote("+bed.getId()+",1)'><i class='fa fa-edit'></i></a></td>");
		            buffer.append("<td><a href='#' onclick='deleterequestnote("+bed.getId()+")'><i class='fa fa-trash-o'></i></a></td>"); 
		            buffer.append("</tr>");
	    		}
	    		

	    	}
	    	   response.setContentType("text/html");
			   response.setHeader("Cache-Control", "no-cache");
			   response.getWriter().write(buffer.toString());
			   
	    	
	    	} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

		return null;
		
	}
public String getAllPrintDataforipd() throws Exception{
	

	if (!verifyLogin(request)) {
		return "login";
	}

	String action = request.getParameter("action");

	String date = notAvailableSlotForm.getPrintCommencing();
	String location = notAvailableSlotForm.getPrintLocation();
	String diaryuser = notAvailableSlotForm.getPreviewdiaryuser();
	String diaryuserid = request.getParameter("previewdiaryuser");
	String endDate = "";
	if(date.equals("")) {
		DateFormat currentdate = new SimpleDateFormat("dd/MM/yyyy");
		Calendar cal = Calendar.getInstance();
		// cal.add(Calendar.DATE, -7);
		date = currentdate.format(cal.getTime());
	}
	String temp[] = date.split("/");
	date = temp[2] + "-" + temp[1] + "-" + temp[0];

	int wyear = Integer.parseInt(temp[2]);
	int month = Integer.parseInt(temp[1]);
	int day = Integer.parseInt(temp[0]);

	String cweekName = DateTimeUtils.getWeekName(wyear, month, day);

	notAvailableSlotForm.setCommencing(cweekName + ":" + " " + notAvailableSlotForm.getPrintCommencing());

	if (action.equals("week")) {
		String dateStr = date;// this date of dashboard calender date
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date sdate = (Date) dateFormat.parse(dateStr);
		// System.out.println(sdate);

		Calendar cal = Calendar.getInstance();
		cal.setTime(sdate);
		cal.add(Calendar.DATE, 7);
		endDate = dateFormat.format(cal.getTime());

	}

	Connection connection = null;
	try {
		connection = Connection_provider.getconnection();
		NotAvailableSlotDAO notAvailableSlotDAO = new JDBCNotAvailableSlotDAO(connection);
		MisChartDAO misChartDAO = new JDBCMisChartDAO(connection);
		// Adarsh for preview
		String fromDate = notAvailableSlotForm.getFromDate();
		String toDate = notAvailableSlotForm.getToDate();
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
				notAvailableSlotForm.setFromDate(fromDate);
			} else {
				String tempe[] = fromDate.split("/");
				fromDate = tempe[2] + "-" + tempe[1] + "-" + tempe[0];
				/* fromDate = DateTimeUtils.getCommencingDate1(fromDate); */
			}
		}
		if (toDate == null) {
			DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
			Calendar cal = Calendar.getInstance();
			// cal.add(Calendar.DATE, -7);
			toDate = dateFormat.format(cal.getTime());
			toDate = DateTimeUtils.getCommencingDate1(toDate);
		} else {
			if (toDate.equals("")) {
				DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
				Calendar cal = Calendar.getInstance();
				// cal.add(Calendar.DATE, -7);
				toDate = dateFormat.format(cal.getTime());
				toDate = DateTimeUtils.getCommencingDate1(toDate);
				notAvailableSlotForm.setToDate(toDate);
			} else {
				String temp1[] = toDate.split("/");
				toDate = temp1[2] + "-" + temp1[1] + "-" + temp1[0];
				/* toDate = DateTimeUtils.getCommencingDate1(toDate); */
			}
		}
		// @ Adarsh
		// set diaryuserlist

		ArrayList<DiaryManagement> userList = notAvailableSlotDAO.getUserList(loginInfo.getId());
		notAvailableSlotForm.setUserList(userList);

		String openedb = (String) session.getAttribute("openedb");

		/*
		 * ArrayList<NotAvailableSlot> practitionerApmtList =
		 * notAvailableSlotDAO.getAllPractitionerPrintData(date, endDate, location,
		 * diaryuser, action, openedb, notAvailableSlotForm.getPreviewdiaryuser(),
		 * fromDate, toDate,loginInfo);
		 */
		ArrayList<NotAvailableSlot>practitionerApmtList=new ArrayList<NotAvailableSlot>();
		//for balgopal Physio Ipd
		practitionerApmtList = notAvailableSlotDAO.getPhysioIpdAllPractitionerPrintData(date,
					endDate, location, diaryuser, action, openedb, notAvailableSlotForm.getPreviewdiaryuser(), fromDate,
					toDate,loginInfo);
		

		notAvailableSlotForm.setPractitionerApmtList(practitionerApmtList);
		session.setAttribute("practitionerApmtList", practitionerApmtList);
		ArrayList<MisReport> opdappointmenttype = new ArrayList<MisReport>();
		ArrayList<MisReport> appointmenttypeid = misChartDAO.getAppointmentTypeID();
		int totalopdcount=0;
		for (MisReport string : appointmenttypeid) {
			// MisReport mistotal =
			// misChartDAO.getOPDAppointmentforpreview(fromDate,
			// toDate,""+string.getId(),diaryuserid);
			/*
			 * MisReport mistotal = misChartDAO.getOPDAppointmentCountforpreview(fromDate,
			 * toDate, "" + string.getId(), diaryuserid);
			 */
			// for physio opd count
			MisReport mistotal = misChartDAO.getPhysioOPDAppointmentCountforpreview(fromDate, toDate, "" + string.getId(),
					diaryuserid);
			if (mistotal.getResult() != null) {
				if (!mistotal.getResult().equals("")) {
					mistotal.setName(string.getName());
				}
				totalopdcount=totalopdcount+ DateTimeUtils.convertToInteger(mistotal.getResult());
				opdappointmenttype.add(mistotal);
			}
			
		}
		notAvailableSlotForm.setTotalopdcount(totalopdcount);
		/* notAvailableSlotForm.settot */
		notAvailableSlotForm.setOpdappointmenttype(opdappointmenttype);
		ArrayList<NotAvailableSlot> dayList = new ArrayList<NotAvailableSlot>();
		if (action.equals("day")) {
			int i = 0;
			for (NotAvailableSlot notAvailableSlot : practitionerApmtList) {
				dayList.add(notAvailableSlot);
				if (i != practitionerApmtList.size() - 1) {
					NotAvailableSlot n = practitionerApmtList.get(i + 1);
					if (!notAvailableSlot.getEndTime().equals(n.getSTime())
							&& !notAvailableSlot.isApmtSlotStimeEmpty()) {
						NotAvailableSlot newNotAvailableSlot = new NotAvailableSlot();
						newNotAvailableSlot.setSTime(notAvailableSlot.getEndTime());
						String duration = DateTimeUtils.getDuration(notAvailableSlot.getEndTime(), n.getSTime());
						newNotAvailableSlot.setApmtDuration(duration);
						newNotAvailableSlot.setActionType(action);
						newNotAvailableSlot.setStatus("2");
						dayList.add(newNotAvailableSlot);

					}
				}

				i++;
			}

			if (location.equals("0")) {
				notAvailableSlotForm.setLocationName("All");
			}

			if (!location.equals("0")) {

				ClinicDAO clinicListDAO = new JDBCClinicDAO(connection);
				Clinic clinic = new Clinic();
				clinic = clinicListDAO.getLocationDetails(Integer.parseInt(location));
				String locationName = clinic.getCity() + " (" + clinic.getLocationname() + ")";
				notAvailableSlotForm.setLocationName(clinic.getLocationname());

				NotAvailableSlot lastn = dayList.get(dayList.size() - 1);
				DiaryManagement diaryManagement = notAvailableSlotDAO.getApmtSlotData(date, diaryuser, location);
				if (!diaryManagement.getEndTime().equals(lastn.getSTime())) {
					String duration = DateTimeUtils.getDuration(lastn.getEndTime(), diaryManagement.getEndTime());
					// System.out.println(duration);
					NotAvailableSlot lastnp = new NotAvailableSlot();
					lastnp.setSTime(lastn.getEndTime());
					lastnp.setApmtDuration(duration);
					lastnp.setActionType(action);
					lastnp.setStatus("2");

					dayList.add(lastnp);
				}

			}

			notAvailableSlotForm.setPractitionerApmtList(dayList);

		}

		notAvailableSlotForm.setActionType(action);

		String diaryUserFullname = notAvailableSlotDAO.getDiaryUserFullname(diaryuser);
		notAvailableSlotForm.setDiaryUser(diaryUserFullname);
		String locationName = notAvailableSlotDAO.getLocationName(location);

		//  
		int size = practitionerApmtList.size();
		if (size > 0) {
			String diaryuser1 = practitionerApmtList.get(size - 1).getDiaryUser();
			notAvailableSlotForm.setDiaryUser(diaryuser1);
			if (diaryuser != null) {
				if (diaryuser.equals("")) {
					diaryuser = "0";
				}
				if (diaryuser.equals("0")) {
					notAvailableSlotForm.setDiaryUser("All");
				}
			}
		} else {
			notAvailableSlotForm.setDiaryUser("All");
		}

		//   15 June 2018

		String temp1[] = fromDate.split("-");
		fromDate = temp1[2] + "/" + temp1[1] + "/" + temp1[0];

		String temp2[] = toDate.split("-");
		toDate = temp2[2] + "/" + temp2[1] + "/" + temp2[0];

		notAvailableSlotForm.setFromDate(fromDate);
		notAvailableSlotForm.setToDate(toDate);

		if (openedb.equals("staff")) {
			return "printstaffdashboard";
		}

		/*
		 * StringBuffer str = new StringBuffer();
		 * 
		 * str.
		 * append("<table width = '100%' id = 'printData' cellpadding='0' cellspacing='0' class='my-table' > "
		 * ); str.append("<tr>"); str.append("<th>Practitioner</th> ");
		 * str.append("<th>Date</th> "); str.append("<th>Time</th> ");
		 * str.append("<th>Duration</th> ");
		 * str.append("<th>Patient</th> ");
		 * str.append("<th>Appointment Type</th> ");
		 * str.append("<th>Note</th> ");
		 * 
		 * str.append("</tr>");
		 * 
		 * for(NotAvailableSlot notAvailableSlot:practitionerApmtList){
		 * str.append("<tr>");
		 * str.append("<td>"+notAvailableSlot.getDiaryUser()+"</td>");
		 * str.append("<td>"+notAvailableSlot.getCommencing()+"</td>");
		 * str.append("<td>"+notAvailableSlot.getSTime()+"</td>");
		 * str.append("<td>"+notAvailableSlot.getApmtDuration()+"</td>");
		 * str.append("<td>"+notAvailableSlot.getClient()+"</td>");
		 * str.append("<td>"+notAvailableSlot.getApmtType()+"</td>");
		 * str.append("<td>"+notAvailableSlot.getNotes()+"</td>");
		 * str.append("</tr>"); } str.append("</table>");
		 * 
		 * response.setContentType("text/html");
		 * response.setHeader("Cache-Control", "no-cache");
		 * 
		 * response.getWriter().write(""+str.toString()+"");
		 */
		Clinic clinic = new Clinic();
		ClinicDAO clinicDAO = new JDBCClinicDAO(connection);
		clinic = clinicDAO.getCliniclistDetails(loginInfo.getClinicid());
		notAvailableSlotForm.setClinicName(clinic.getClinicName());
		notAvailableSlotForm.setClinicOwner(clinic.getClinicOwner());
		notAvailableSlotForm.setOwner_qualification(clinic.getOwner_qualification());
		notAvailableSlotForm.setLandLine(clinic.getLandLine());
		notAvailableSlotForm.setClinicemail(clinic.getEmail());
		notAvailableSlotForm.setWebsiteUrl(clinic.getWebsiteUrl());

		notAvailableSlotForm.setClinicLogo(clinic.getUserImageFileName());
		
		ArrayList<NotAvailableSlot>deptwisegenderlist=notAvailableSlotDAO.getDeptwisegenderList(fromDate,toDate,loginInfo);
		notAvailableSlotForm.setDeptwisegenderlist(deptwisegenderlist);

	} catch (Exception e) {
		e.printStackTrace();
	} finally {
		connection.close();
	}

	return "ipdprintdashpoard";

	
}

public String savevacinationimmunizationajaxVet() {
	Connection connection = null;
	try {
		connection = Connection_provider.getconnection();
		StringBuilder buffer1 = new StringBuilder();
		BufferedReader reader = request.getReader();
		SmsService s = new SmsService();
		WhatsAppService wsms=new WhatsAppService();
		UserProfileDAO userProfileDAO = new JDBCUserProfileDAO(connection);
		ClinicDAO clinicDAO = new JDBCClinicDAO(connection);
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
		x = notAvailableSlotDAO.checkvacinationimmunizationajaxDataVet(masterid, clientid);
		if (x == 0) {
			y = notAvailableSlotDAO.savevacinationimmunizationajaxVet(mastername, masterid, clientid, givendate,
					duedate);
		} else {
			y = notAvailableSlotDAO.updatevacinationimmunizationajaxdateVet(String.valueOf(x), givendate, duedate);
		}
		String  templateid = userProfileDAO.getSMSTemplateID("Vaccination Success");
		NotAvailableSlot vaccdata=notAvailableSlotDAO.getpatientdetails(clientid);
		loginInfo.setPatient_name(vaccdata.getName());
	    loginInfo.setSms_type("Vaccination Success");
		if(loginInfo.isParihar()) {
			
			
			String msg="(à¤ªà¤°à¤¿à¤¹à¤¾à¤° à¤¹à¤¾à¤¸à¥�à¤ªà¤¿à¤Ÿà¤² à¤•à¤µà¤°à¥�à¤§à¤¾) "+vaccdata.getName()+" à¤†à¤ªà¤•à¤¾ "+mastername+" à¤•à¤¾ à¤Ÿà¥€à¤•à¤¾à¤•à¤°à¤£ à¤†à¤œ "+givendate+" à¤•à¥‹ à¤¸à¤«à¤²à¤¤à¤¾à¤ªà¥�à¤°à¥�à¤µà¤• à¤¸à¤®à¥�à¤ªà¤¨à¥�à¤¨ à¤¹à¥‹ à¤—à¤¯à¤¾ à¤¹à¥ˆ à¤«à¥‹à¤¨ 07741232577, 9753059148";
			s.sendvaccineSms(msg, vaccdata.getMobno(), loginInfo, new EmailLetterLog(), connection, templateid);
		}else if(loginInfo.isAyushman()) {
			Clinic clinic1 = clinicDAO.getWhatsappActive(loginInfo.getId());
			if(clinic1.getWsms()==1) {
			String message="Ayushman Hospital:"+vaccdata.getName()+" आपका "+mastername+" का टीकाकरण आज "+givendate+" को सफलतापुर्वक सम्पन्न हो गया है|";
			wsms.sendMsgvacc(loginInfo,vaccdata.getMobno(), message,connection);
			}
			
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

public String doctorlist() throws Exception {

	Connection connection = null;
	try {

		//connection = Connection_provider.getconnection();
		connection = DriverManager.getConnection(
				"" + Constants.DB_HOST + ":3306/" + loginInfo.getClinicUserid()
						+ "?useUnicode=true&characterEncoding=UTF-8",
				"" + Constants.DB_USER + "", "" + Constants.DB_PWD + "");
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		IpdDAO ipdDAO = new JDBCIpdDAO(connection);
		NotAvailableSlotDAO notAvailableSlotDAO = new JDBCNotAvailableSlotDAO(connection);
		
		ArrayList<Master> doctorList = notAvailableSlotDAO.getDoctorList();

		StringBuffer str = new StringBuffer();
          
		str.append(
				"<select  name='doctorname' id='doctorname' class='form-control showToolTip chosen' >");
         
		str.append("<option value='0'>Select Doctor</option>");
		for (Master master : doctorList) {
			//   06-jan-2018
			//   25 jan 2018
			str.append("<option value='" + master.getId() + "'>" + master.getDoctorName() + "</option>");
			// str.append("<option
			// value='"+master.getName()+"'>"+master.getName()+"</option>");
		}
		str.append("</select>");

		response.setContentType("text/html");
		response.setHeader("Cache-Control", "no-cache");
		response.getWriter().write("" + str.toString() + "");

	} catch (Exception e) {
		e.printStackTrace();
	}

	return null;
}

public String addprocedures()throws Exception{
	Connection connection = null;
	try {

		//connection = Connection_provider.getconnection();
		connection = DriverManager.getConnection(
				"" + Constants.DB_HOST + ":3306/" + loginInfo.getClinicUserid()
						+ "?useUnicode=true&characterEncoding=UTF-8",
				"" + Constants.DB_USER + "", "" + Constants.DB_PWD + "");
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		IpdDAO ipdDAO = new JDBCIpdDAO(connection);
		NotAvailableSlotDAO notAvailableSlotDAO = new JDBCNotAvailableSlotDAO(connection);
		String procedureid=request.getParameter("procedure");
		String left=request.getParameter("left");
		MasterDAO masterDAO = new JDBCMasterDAO(connection);
		String chargetype = masterDAO.getMasterChargeType(procedureid);
		String data=request.getParameter("data");
		NotAvailableSlot notAvailableSlot=masterDAO.getAppointmentchargedetails(chargetype);
		
		
		String str=chargetype+"("+left+")"+"~~"+notAvailableSlot.getId()+"~~"+notAvailableSlot.getName()+"~~"+notAvailableSlot.getCharge()+"~~"+procedureid+"~~"+data;
		response.setContentType("text/html");
		response.setHeader("Cache-Control", "no-cache");
		response.getWriter().write("" + str.toString() + "");
	} catch (Exception e) {
	e.printStackTrace();
	}
	
	return null;
}

public String addnewcharges()throws Exception{
	Connection connection = null;
	try {

		//connection = Connection_provider.getconnection();
		connection = DriverManager.getConnection(
				"" + Constants.DB_HOST + ":3306/" + loginInfo.getClinicUserid()
						+ "?useUnicode=true&characterEncoding=UTF-8",
				"" + Constants.DB_USER + "", "" + Constants.DB_PWD + "");
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		IpdDAO ipdDAO = new JDBCIpdDAO(connection);
		NotAvailableSlotDAO notAvailableSlotDAO = new JDBCNotAvailableSlotDAO(connection);
		String procedure=request.getParameter("procedure");
		
		String[] parts = procedure.split("\\((?i)Right\\),|\\((?i)Left\\),|\\((?i)B/L\\),");
		
		double charge=0.0;
		 for (String part : parts) {
	            System.out.println(part.trim());
	        	double charges=notAvailableSlotDAO.getProcedureCharges(part);
	        	charge=charge+charges;
	        }
	
		String str=procedure+"~~"+charge;
		response.setContentType("text/html");
		response.setHeader("Cache-Control", "no-cache");
		response.getWriter().write("" + str.toString() + "");
	}catch (Exception e) {
		e.printStackTrace();
	}
	return null;
}


public String mobapp(){
	return "mobweek1";
}

}
