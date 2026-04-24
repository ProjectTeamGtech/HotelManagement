package com.apm.Report.web.action;

import java.io.File;
import java.io.FileOutputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

import javax.mail.Flags.Flag;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.allcolor.yahp.converter.CYaHPConverter;
import org.allcolor.yahp.converter.IHtmlToPdfTransformer;
import org.apache.struts2.ServletActionContext;

import com.apm.Accounts.eu.bi.AccountsDAO;
import com.apm.Accounts.eu.bi.AdditionalDAO;
import com.apm.Accounts.eu.blogic.jdbc.JDBCAccountsDAO;
import com.apm.Accounts.eu.blogic.jdbc.JDBCAdditionalDAO;
import com.apm.Accounts.eu.entity.Accounts;
import com.apm.DiaryManagement.eu.bi.ClientDAO;
import com.apm.DiaryManagement.eu.bi.FinderDAO;
import com.apm.DiaryManagement.eu.bi.NotAvailableSlotDAO;
import com.apm.DiaryManagement.eu.blogic.jdbc.JDBCClientDAO;
import com.apm.DiaryManagement.eu.blogic.jdbc.JDBCFinderDAO;
import com.apm.DiaryManagement.eu.blogic.jdbc.JDBCNotAvailableSlotDAO;
import com.apm.DiaryManagement.eu.entity.Client;
import com.apm.DiaryManagement.eu.entity.CompleteAppointment;
import com.apm.DiaryManagement.eu.entity.DiaryManagement;
import com.apm.DiaryManagement.eu.entity.NotAvailableSlot;
import com.apm.DiaryManagement.web.action.EmbeddedImageEmailUtil;
import com.apm.Emr.eu.bi.EmrDAO;
import com.apm.Emr.eu.bi.InvestigationDAO;
import com.apm.Emr.eu.blogic.jdbc.JDBCEmrDAO;
import com.apm.Emr.eu.blogic.jdbc.JDBCInvestigationDAO;
import com.apm.Emr.eu.entity.Investigation;
import com.apm.Inventory.eu.entity.Product;
import com.apm.Ipd.eu.bi.IpdDAO;
import com.apm.Ipd.eu.blogic.jdbc.JDBCIpdDAO;
import com.apm.Ipd.eu.entity.Bed;
import com.apm.Ipd.eu.entity.Ipd;
import com.apm.Log.eu.entity.EmailLetterLog;
import com.apm.Master.eu.bi.InvestigationMasterDAO;
import com.apm.Master.eu.bi.MasterDAO;
import com.apm.Master.eu.bi.PackageMasterDAO;
import com.apm.Master.eu.bi.TreatmentTypeDAO;
import com.apm.Master.eu.blogic.jdbc.JDBCInvestigationMasterDAO;
import com.apm.Master.eu.blogic.jdbc.JDBCMasterDAO;
import com.apm.Master.eu.blogic.jdbc.JDBCPackageMasterDAO;
import com.apm.Master.eu.blogic.jdbc.JDBCTreatmentTypeDAO;
import com.apm.Master.eu.entity.Discharge;
import com.apm.Master.eu.entity.Master;
import com.apm.Master.eu.entity.PackageMaster;
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
import com.apm.Registration.eu.entity.UserProfile;
import com.apm.Report.eu.bi.ChargesReportDAO;
import com.apm.Report.eu.bi.CommissionDAO;
import com.apm.Report.eu.bi.SummaryReportDAO;
import com.apm.Report.eu.blogic.jdbc.JDBCChargesReportDAO;
import com.apm.Report.eu.blogic.jdbc.JDBCCommissionDAO;
import com.apm.Report.eu.blogic.jdbc.JDBCSummaryReportDAO;
import com.apm.Report.eu.entity.Commission;
import com.apm.Report.eu.entity.MisReport;
import com.apm.Report.eu.entity.Report;
import com.apm.Report.eu.entity.SummaryReport;
import com.apm.Report.web.form.SummaryReportForm;
import com.apm.common.eu.blogic.jdbc.Connection_provider;
import com.apm.common.utils.DateTimeUtils;
import com.apm.common.utils.Pagination;
import com.apm.common.web.action.BaseAction;
import com.apm.common.web.common.helper.LoginHelper;
import com.apm.common.web.common.helper.LoginInfo;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.Preparable;

public class SummaryReportAction extends BaseAction implements Preparable, ModelDriven<SummaryReportForm> {

	SummaryReportForm summaryReportForm = new SummaryReportForm();
	HttpServletRequest request = (HttpServletRequest) ActionContext.getContext().get(ServletActionContext.HTTP_REQUEST);
	HttpServletResponse response = (HttpServletResponse) ActionContext.getContext()
			.get(ServletActionContext.HTTP_RESPONSE);
	HttpSession session = request.getSession(true);
	LoginInfo loginInfo = LoginHelper.getLoginInfo(request);
	String fromDate = summaryReportForm.getFromDate();
	String toDate = summaryReportForm.getToDate();
	private Pagination pagination = new Pagination(15, 1);
	public Pagination getPagination() {
		return pagination;
	}


	public void setPagination(Pagination pagination) {
		this.pagination = pagination;
	}
	public String execute() {
		if (!verifyLogin(request)) {
			return "login";
		}

		Connection connection = null;
		try {
			connection = Connection_provider.getconnection();
			SummaryReportDAO summaryReportDAO = new JDBCSummaryReportDAO(connection);
			ChargesReportDAO chargesReportDAO = new JDBCChargesReportDAO(connection);

			String fromDate = summaryReportForm.getFromDate();
			String toDate = summaryReportForm.getToDate();
			if (fromDate == null) {
				fromDate = "";
			}
			if (fromDate.equals("")) {
				DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
				Calendar cal = Calendar.getInstance();
				cal.add(Calendar.DATE, -30);
				fromDate = dateFormat.format(cal.getTime());
				// summaryReportForm.setFromDate(fromDate);
			}
			if (toDate == null) {
				toDate = "";
			}
			if (toDate.equals("")) {
				DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
				Calendar cal = Calendar.getInstance();
				// cal.add(Calendar.DATE, -7);
				toDate = dateFormat.format(cal.getTime());
				// summaryReportForm.setToDate(toDate);
			}
			DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Calendar cal = Calendar.getInstance();
			String date = dateFormat.format(cal.getTime());

			int result = chargesReportDAO.saveMisReportLog("DNA Analysis Report", loginInfo.getUserId(), fromDate,
					toDate, date, "execute");

			ArrayList<SummaryReport> DNAAnalysisReport = summaryReportDAO.getDNAAnalysisReport(fromDate, toDate);
			summaryReportForm.setFromDate(fromDate);
			summaryReportForm.setToDate(toDate);
			summaryReportForm.setDNAAnalysisReport(DNAAnalysisReport);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return SUCCESS;
	}

	public String userDNAAnalysis() throws Exception {
		if (!verifyLogin(request)) {
			return "login";
		}
		int practitionerId = Integer.parseInt(request.getParameter("diaryUser"));
		Connection connection = null;
		try {
			connection = Connection_provider.getconnection();
			SummaryReportDAO summaryReportDAO = new JDBCSummaryReportDAO(connection);
			String fromDate = summaryReportForm.getFromDate();
			String toDate = summaryReportForm.getToDate();
			if (fromDate == null) {
				fromDate = "";
			}
			if (fromDate.equals("")) {
				DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
				Calendar cal = Calendar.getInstance();
				cal.add(Calendar.DATE, -30);
				fromDate = dateFormat.format(cal.getTime());
				// summaryReportForm.setFromDate(fromDate);
			}
			if (toDate == null) {
				toDate = "";
			}
			if (toDate.equals("")) {
				DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
				Calendar cal = Calendar.getInstance();
				// cal.add(Calendar.DATE, -7);
				toDate = dateFormat.format(cal.getTime());
				// summaryReportForm.setToDate(toDate);
			}
			ArrayList<SummaryReport> DNAAnalysisReport = summaryReportDAO.getDNAAnalysisByPractitioner(practitionerId,
					fromDate, toDate);
			summaryReportForm.setFromDate(fromDate);
			summaryReportForm.setToDate(toDate);
			summaryReportForm.setDNAAnalysisReport(DNAAnalysisReport);

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			connection.close();
		}

		return SUCCESS;
	}

	public String appDNAKept() throws Exception {
		if (!verifyLogin(request)) {
			return "login";
		}

		Connection connection = null;
		try {
			connection = Connection_provider.getconnection();
			SummaryReportDAO summaryReportDAO = new JDBCSummaryReportDAO(connection);

			/*
			 * if(chargesReportForm.getOrderby().equals("")){
			 * chargesReportForm.setOrderby("commencing"); }
			 */

			if (fromDate.equals("")) {
				DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
				Calendar cal = Calendar.getInstance();
				cal.add(Calendar.DATE, -7);
				fromDate = dateFormat.format(cal.getTime());
				summaryReportForm.setFromDate(fromDate);
			}
			if (toDate.equals("")) {
				DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
				Calendar cal = Calendar.getInstance();
				// cal.add(Calendar.DATE, -7);
				toDate = dateFormat.format(cal.getTime());
				summaryReportForm.setToDate(toDate);
			}

			/*
			 * if(summaryReportForm.getOrderby().equals("")){
			 * summaryReportForm.setOrderby("commencing"); }
			 */

			if (!fromDate.equals("")) {
				String temp[] = fromDate.split("/");
				fromDate = temp[2] + "-" + temp[1] + "-" + temp[0];
			}
			if (!toDate.equals("")) {
				String temp1[] = toDate.split("/");
				toDate = temp1[2] + "-" + temp1[1] + "-" + temp1[0];
			}

			ArrayList<SummaryReport> appkeptDNAList = summaryReportDAO.getAppKeptDNAList(fromDate, toDate);
			summaryReportForm.setAppkeptDNAList(appkeptDNAList);

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			connection.close();
		}
		return INPUT;
	}

	public String appKeptDNA() throws Exception {
		if (!verifyLogin(request)) {
			return "login";
		}

		String fromDate = summaryReportForm.getFromDate();
		String toDate = summaryReportForm.getToDate();

		session.setAttribute("fromDate", fromDate);
		session.setAttribute("toDate", toDate);

		if (!fromDate.equals("")) {
			String temp[] = fromDate.split("/");
			fromDate = temp[2] + "-" + temp[1] + "-" + temp[0];
		}
		if (!toDate.equals("")) {
			String temp1[] = toDate.split("/");
			toDate = temp1[2] + "-" + temp1[1] + "-" + temp1[0];
		}

		Connection connection = null;
		try {
			connection = Connection_provider.getconnection();
			SummaryReportDAO summaryReportDAO = new JDBCSummaryReportDAO(connection);
			ArrayList<SummaryReport> appkeptDNAList = summaryReportDAO.getAppKeptDNAList(fromDate, toDate);
			summaryReportForm.setAppkeptDNAList(appkeptDNAList);

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			connection.close();
		}

		return INPUT;
	}

	public String ipd() throws SQLException {

		if (!verifyLogin(request)) {
			return "login";
		}

		Connection connection = null;
		try {
			connection = Connection_provider.getconnection();
			SummaryReportDAO summaryReportDAO = new JDBCSummaryReportDAO(connection);
			ChargesReportDAO chargesReportDAO = new JDBCChargesReportDAO(connection);
			String fromDate = summaryReportForm.getFromDate();
			String toDate = summaryReportForm.getToDate();
			/*
			 * if(chargesReportForm.getOrderby().equals("")){
			 * chargesReportForm.setOrderby("commencing"); }
			 */

			if (fromDate.equals("")) {
				DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
				Calendar cal = Calendar.getInstance();
				//cal.add(Calendar.DATE, -180);
				cal.add(Calendar.DATE, -7);
				fromDate = dateFormat.format(cal.getTime());
				summaryReportForm.setFromDate(fromDate);
			}
			if (toDate.equals("")) {
				DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
				Calendar cal = Calendar.getInstance();
				// cal.add(Calendar.DATE, -7);
				toDate = dateFormat.format(cal.getTime());
				summaryReportForm.setToDate(toDate);
			}

			if (summaryReportForm.getOrderby().equals("")) {
				summaryReportForm.setOrderby("commencing");
			}

			if (!fromDate.equals("")) {
				String temp[] = fromDate.split("/");
				fromDate = temp[2] + "-" + temp[1] + "-" + temp[0];
			}
			if (!toDate.equals("")) {
				String temp1[] = toDate.split("/");
				toDate = temp1[2] + "-" + temp1[1] + "-" + temp1[0];
			}
			DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Calendar cal = Calendar.getInstance();
			String date = dateFormat.format(cal.getTime());

			int result = chargesReportDAO.saveMisReportLog("IPD Daily Report", loginInfo.getUserId(), fromDate, toDate,
					date, "ipd");
			ArrayList<SummaryReport> odReportList = summaryReportDAO.getODReportList(fromDate, toDate,
					summaryReportForm.getPayby(), summaryReportForm.getDiaryUser(), summaryReportForm.getLocation(),
					summaryReportForm.getThirdParty(), summaryReportForm.getDischrgeOutcomes(),
					summaryReportForm.getDischargeStatus(), summaryReportForm.getDischarge(),
					summaryReportForm.getIpdopd(), summaryReportForm.getBalancelimit(), 0,summaryReportForm.getPresentipd(),loginInfo);
			summaryReportForm.setOdReportList(odReportList);

			Clinic clinic = new Clinic();
			ClinicDAO clinicDAO = new JDBCClinicDAO(connection);
			clinic = clinicDAO.getCliniclistDetails(loginInfo.getClinicid());
			AccountsDAO accountsDAO = new JDBCAccountsDAO(connection);
			ArrayList<Clinic> locationAdressList = accountsDAO.getLocationAddress(loginInfo.getClinicid());

			summaryReportForm.setClinicName(clinic.getClinicName());
			summaryReportForm.setClinicOwner(clinic.getClinicOwner());
			summaryReportForm.setOwner_qualification(clinic.getOwner_qualification());
			summaryReportForm.setLocationAdressList(locationAdressList);
			summaryReportForm.setAddress(clinic.getAddress());
			summaryReportForm.setLandLine(clinic.getLandLine());
			summaryReportForm.setClinicemail(clinic.getEmail());
			summaryReportForm.setWebsiteUrl(clinic.getWebsiteUrl());
			summaryReportForm.setClinicLogo(clinic.getUserImageFileName());

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			connection.close();
		}

		return "ipddailyreport";
	}

	public String odreport() throws SQLException {

		if (!verifyLogin(request)) {
			return "login";
		}

		Connection connection = null;
		try {
			connection = Connection_provider.getconnection();
			SummaryReportDAO summaryReportDAO = new JDBCSummaryReportDAO(connection);

			String fromDate = summaryReportForm.getFromDate();
			String toDate = summaryReportForm.getToDate();
			/*
			 * if(chargesReportForm.getOrderby().equals("")){
			 * chargesReportForm.setOrderby("commencing"); }
			 */

			if (fromDate.equals("")) {
				DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
				Calendar cal = Calendar.getInstance();
				cal.add(Calendar.DATE, -2);
				fromDate = dateFormat.format(cal.getTime());
				summaryReportForm.setFromDate(fromDate);
			}
			if (toDate.equals("")) {
				DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
				Calendar cal = Calendar.getInstance();
				// cal.add(Calendar.DATE, -7);
				toDate = dateFormat.format(cal.getTime());
				summaryReportForm.setToDate(toDate);
			}

			if (summaryReportForm.getOrderby().equals("")) {
				summaryReportForm.setOrderby("commencing");
			}

			if (!fromDate.equals("")) {
				String temp[] = fromDate.split("/");
				fromDate = temp[2] + "-" + temp[1] + "-" + temp[0];
			}
			if (!toDate.equals("")) {
				String temp1[] = toDate.split("/");
				toDate = temp1[2] + "-" + temp1[1] + "-" + temp1[0];
			}
			ChargesReportDAO chargesReportDAO = new JDBCChargesReportDAO(connection);
			DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Calendar cal = Calendar.getInstance();
			String date = dateFormat.format(cal.getTime());

			int result = chargesReportDAO.saveMisReportLog("Outcome Discharge / Report", loginInfo.getUserId(),
					fromDate, toDate, date, "odreport");
			ArrayList<SummaryReport> odReportList = summaryReportDAO.getODReportList(fromDate, toDate,
					summaryReportForm.getPayby(), summaryReportForm.getDiaryUser(), summaryReportForm.getLocation(),
					summaryReportForm.getThirdParty(), summaryReportForm.getDischrgeOutcomes(),
					summaryReportForm.getDischargeStatus(), summaryReportForm.getDischarge(),
					summaryReportForm.getIpdopd(), summaryReportForm.getBalancelimit(), loginInfo.getIpd_abbr_access(),summaryReportForm.getPresentipd(),loginInfo);
			summaryReportForm.setOdReportList(odReportList);

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			connection.close();
		}

		return "odreport";
	}

	public String returningpts() throws SQLException {

		if (!verifyLogin(request)) {
			return "login";
		}

		Connection connection = null;
		try {
			connection = Connection_provider.getconnection();
			SummaryReportDAO summaryReportDAO = new JDBCSummaryReportDAO(connection);
			ChargesReportDAO chargesReportDAO = new JDBCChargesReportDAO(connection);
			String fromDate = summaryReportForm.getFromDate();
			String toDate = summaryReportForm.getToDate();
			/*
			 * if(chargesReportForm.getOrderby().equals("")){
			 * chargesReportForm.setOrderby("commencing"); }
			 */

			if (fromDate.equals("")) {
				DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
				Calendar cal = Calendar.getInstance();
				cal.add(Calendar.DATE, -7);
				fromDate = dateFormat.format(cal.getTime());
				summaryReportForm.setFromDate(fromDate);
			}
			if (toDate.equals("")) {
				DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
				Calendar cal = Calendar.getInstance();
				// cal.add(Calendar.DATE, -7);
				toDate = dateFormat.format(cal.getTime());
				summaryReportForm.setToDate(toDate);
			}

			if (summaryReportForm.getOrderby().equals("")) {
				summaryReportForm.setOrderby("commencing");
			}

			if (!fromDate.equals("")) {
				String temp[] = fromDate.split("/");
				fromDate = temp[2] + "-" + temp[1] + "-" + temp[0];
			}
			if (!toDate.equals("")) {
				String temp1[] = toDate.split("/");
				toDate = temp1[2] + "-" + temp1[1] + "-" + temp1[0];
			}

			DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Calendar cal = Calendar.getInstance();
			String date = dateFormat.format(cal.getTime());

			int result = chargesReportDAO.saveMisReportLog("Returning Patients", loginInfo.getUserId(), fromDate,
					toDate, date, "returningpts");
			ArrayList<SummaryReport> returningPtsList = summaryReportDAO.getReturingPtsList(fromDate, toDate,
					summaryReportForm.getDiaryUser(), summaryReportForm.getLocation(), summaryReportForm.getCondition(),
					summaryReportForm.getSourceOfIntro(), summaryReportForm.getReferal(),
					summaryReportForm.getOrderby(), summaryReportForm.getOrder());
			summaryReportForm.setReturningPtsList(returningPtsList);

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			connection.close();
		}

		return "returningpts";
	}

	public String treatmentReferral() throws SQLException {

		if (!verifyLogin(request)) {
			return "login";
		}

		Connection connection = null;
		try {
			connection = Connection_provider.getconnection();
			SummaryReportDAO summaryReportDAO = new JDBCSummaryReportDAO(connection);
			ChargesReportDAO chargesReportDAO = new JDBCChargesReportDAO(connection);
			String fromDate = summaryReportForm.getFromDate();
			String toDate = summaryReportForm.getToDate();
			/*
			 * if(chargesReportForm.getOrderby().equals("")){
			 * chargesReportForm.setOrderby("commencing"); }
			 */

			if (fromDate.equals("")) {
				DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
				Calendar cal = Calendar.getInstance();
				cal.add(Calendar.DATE, -7);
				fromDate = dateFormat.format(cal.getTime());
				summaryReportForm.setFromDate(fromDate);
			}
			if (toDate.equals("")) {
				DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
				Calendar cal = Calendar.getInstance();
				// cal.add(Calendar.DATE, -7);
				toDate = dateFormat.format(cal.getTime());
				summaryReportForm.setToDate(toDate);
			}

			if (!fromDate.equals("")) {
				String temp[] = fromDate.split("/");
				fromDate = temp[2] + "-" + temp[1] + "-" + temp[0];
			}
			if (!toDate.equals("")) {
				String temp1[] = toDate.split("/");
				toDate = temp1[2] + "-" + temp1[1] + "-" + temp1[0];
			}
			DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Calendar cal = Calendar.getInstance();
			String date = dateFormat.format(cal.getTime());

			int result = chargesReportDAO.saveMisReportLog("Treatment State By Referral", loginInfo.getUserId(),
					fromDate, toDate, date, "treatmentReferral");

			ArrayList<SummaryReport> treatmentReferralList = summaryReportDAO.getTreatmentReferralList(
					summaryReportForm.getPayby(), summaryReportForm.getDiaryUser(), summaryReportForm.getThirdParty(),
					summaryReportForm.getGpid(), summaryReportForm.getCondition(), summaryReportForm.getReferal(),
					fromDate, toDate);
			summaryReportForm.setTreatmentReferralList(treatmentReferralList);

			session.setAttribute("treatmentReferralList", treatmentReferralList);

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			connection.close();
		}

		return "referalTreatment";
	}

	public String treatmentByReferral() throws Exception {
		if (!verifyLogin(request)) {
			return "login";
		}

		Connection connection = null;
		int referalId = Integer.parseInt(summaryReportForm.getReferal());

		try {
			connection = Connection_provider.getconnection();
			SummaryReportDAO summaryReportDAO = new JDBCSummaryReportDAO(connection);
			ArrayList<SummaryReport> treatmentReferralList = summaryReportDAO.getTreatmentByReferral(referalId);
			summaryReportForm.setTreatmentReferralList(treatmentReferralList);

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			connection.close();
		}

		return "referalTreatment";
	}

	public String previewReport() throws Exception {
		if (!verifyLogin(request)) {
			return "login";
		}

		Connection connection = null;
		int practitionerId = Integer.parseInt(request.getParameter("diaryUser"));
		String clientId = "";
		int appointmentId = 0;

		try {
			connection = Connection_provider.getconnection();
			SummaryReportDAO summaryReportDAO = new JDBCSummaryReportDAO(connection);
			ArrayList<SummaryReport> DNAAnalysisReport = summaryReportDAO.getDNAAnalysisByPractitioner(practitionerId,
					"", "");

			StringBuffer str = new StringBuffer();

			String currentDate = DateTimeUtils.getDateinSimpleFormate(new Date());
			String temp[] = currentDate.split(" ");
			String temptime[] = currentDate.split("at");
			String temp1[] = temp[0].split("-");
			String date = temp1[0] + "-" + temp1[1] + "-" + temp1[2];
			String time = temptime[1];

			str.append("<div style='font-size: 15px; font-weight: bold;'> DNA Analysys Report </div>");
			str.append("<div style='font-size: 10px; font-weight: bold;'> Date: " + date + " </div>");
			str.append("<div style='font-size: 10px; font-weight: bold;'> Time: " + time + " </div>");
			str.append("<br>");

			str.append("<table cellpadding='0' cellspacing='0' style='width: 100%;font-size: 10px;'>");

			str.append("<thead>");
			str.append("<tr>");

			str.append("<th>Sr.No.</th> ");
			str.append("<th>Client No</th> ");
			str.append("<th>Client Name</th> ");
			str.append("<th>Appointment Type</th> ");
			str.append("<th>Appointment Date</th> ");
			str.append("<th>Clinic/Location</th> ");
			str.append("<th>DNA Reason</th> ");
			str.append("<th>Charge</th> ");
			str.append("<th>Mobile No</th> ");
			str.append("<th>Email Id</th> ");
			str.append("<th>Address</th> ");
			str.append("<th>Town</th> ");
			str.append("<th>Country</th> ");

			str.append("</tr>");
			str.append("</thead>");
			str.append("<tbody>");
			int srno = 1;
			for (SummaryReport summaryReport : DNAAnalysisReport) {
				str.append("<tr class= 'bg-info'>");
				str.append("<td>" + srno + "</td>");
				str.append("<td>" + summaryReport.getClientId() + "</td>");
				str.append("<td>" + summaryReport.getClientname() + "</td>");
				str.append("<td>" + summaryReport.getApmttypetext() + "</td>");
				str.append("<td>" + summaryReport.getCommencing() + "</td>");
				str.append("<td>" + summaryReport.getLocation() + "</td>");
				str.append("<td>" + summaryReport.getDnareason() + "</td>");
				str.append("<td>" + summaryReport.getCharge() + "</td>");
				str.append("<td>" + summaryReport.getMobno() + "</td>");
				str.append("<td>" + summaryReport.getEmail() + "</td>");
				str.append("<td>" + summaryReport.getAddress() + "</td>");
				str.append("<td>" + summaryReport.getTown() + "</td>");
				str.append("<td>" + summaryReport.getCountry() + "</td>");
				str.append("</tr>");
				srno++;

				clientId = summaryReport.getClientId();
				appointmentId = summaryReport.getAppointmentId();
			}

			str.append("</table><br><br>");

			String filePath = request.getRealPath("/liveData/DNAAnalysysReport/");
//			System.out.println(filePath);
			String filename = "DNAAnalysys_" + date + ".pdf";
			// String filename ="commission.pdf";

			htmlToPdfFile(str.toString(), filePath, filename);

			session.setAttribute("dnaAnalysisFileName", filePath + "/" + filename);

//			System.out.println("PDF Created..");

			response.setContentType("text/html");
			response.setHeader("Cache-Control", "no-cache");
			response.getWriter().write("" + filename + "");

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			connection.close();
		}

		return null;
	}

	public String sendEmailDNAAnalysis() throws Exception {
		if (!verifyLogin(request)) {
			return "login";
		}
		Connection connection = null;
		try {

			connection = Connection_provider.getconnection();

			String to = request.getParameter("to");
			String cc = request.getParameter("cc");
			String subject = request.getParameter("subject");
			String notes = request.getParameter("emailBody");
			String file = request.getParameter("file");

			String filename = (String) session.getAttribute("dnaAnalysisFileName");

			// int result = emailTemplateDAO.saveEmailLogDetails(to, cc,
			// subject, notes, date1,time);

			String type = "Email";
			int appointmentid = 0;
			int invoiceid = 0;
			EmailLetterLog emailLetterLog = new EmailLetterLog();
			emailLetterLog.setAppointmentid(appointmentid);
			emailLetterLog.setInvoiceid(invoiceid);
			emailLetterLog.setType(type);

			EmbeddedImageEmailUtil.sendMailAttachment(connection, loginInfo.getId(), to, cc, subject, notes, filename,
					loginInfo, emailLetterLog, "0");

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			connection.close();
		}
		return null;
	}

	public String previewAPPTVsDnaReport() throws Exception {
		if (!verifyLogin(request)) {
			return "login";
		}

		Connection connection = null;
		String fromDate = request.getParameter("fromDate");
		String toDate = request.getParameter("toDate");
		String clientId = "";
		int appointmentId = 0;

		session.setAttribute("fromDate", fromDate);
		session.setAttribute("toDate", toDate);

		if (!fromDate.equals("")) {
			String temp[] = fromDate.split("/");
			fromDate = temp[2] + "-" + temp[1] + "-" + temp[0];
		}
		if (!toDate.equals("")) {
			String temp1[] = toDate.split("/");
			toDate = temp1[2] + "-" + temp1[1] + "-" + temp1[0];
		}

		try {
			connection = Connection_provider.getconnection();
			SummaryReportDAO summaryReportDAO = new JDBCSummaryReportDAO(connection);
			ArrayList<SummaryReport> appkeptDNAList = summaryReportDAO.getAppKeptDNAList(fromDate, toDate);

			StringBuffer str = new StringBuffer();

			String currentDate = DateTimeUtils.getDateinSimpleFormate(new Date());
			String temp[] = currentDate.split(" ");
			String temptime[] = currentDate.split("at");
			String temp1[] = temp[0].split("-");
			String date = temp1[0] + "-" + temp1[1] + "-" + temp1[2];
			String time = temptime[1];

			str.append("<div style='font-size: 15px; font-weight: bold;'> Appointment Vs DNA Report </div>");
			str.append("<div style='font-size: 10px; font-weight: bold;'> Date: " + date + " </div>");
			str.append("<div style='font-size: 10px; font-weight: bold;'> Time: " + time + " </div>");
			str.append("<br>");

			str.append("<table cellpadding='0' cellspacing='0' style='width: 100%;font-size: 10px;'>");

			str.append("<thead>");
			str.append("<tr>");

			str.append("<th>Sr.No.</th> ");
			str.append("<th>Client No</th> ");
			str.append("<th>Client Name</th> ");
			str.append("<th>Practitioner Name</th> ");
			str.append("<th>Total Appt Nos (S=D+C+I)</th> ");
			str.append("<th>Appt-DNA (D)</th> ");
			str.append("<th>Appt -Completed (C)</th> ");
			str.append("<th>Appt -Incomplete (I)</th> ");
			str.append("<th>Mobile No</th> ");
			str.append("<th>Email Id</th> ");
			str.append("<th>Address</th> ");
			str.append("<th>Town</th> ");
			str.append("<th>Country</th> ");

			str.append("</tr>");
			str.append("</thead>");
			str.append("<tbody>");
			int srno = 1;
			for (SummaryReport summaryReport : appkeptDNAList) {
				str.append("<tr class= 'bg-info'>");
				str.append("<td>" + srno + "</td>");
				str.append("<td>" + summaryReport.getClientId() + "</td>");
				str.append("<td>" + summaryReport.getClientname() + "</td>");
				str.append("<td>" + summaryReport.getPractitionerName() + "</td>");
				str.append("<td>" + summaryReport.getTotalApp() + "</td>");
				str.append("<td>" + summaryReport.getTotalDNA() + "</td>");
				str.append("<td>" + summaryReport.getTotalComplete() + "</td>");
				str.append("<td>" + summaryReport.getTotalIncomplete() + "</td>");
				str.append("<td>" + summaryReport.getMobno() + "</td>");
				str.append("<td>" + summaryReport.getEmail() + "</td>");
				str.append("<td>" + summaryReport.getAddress() + "</td>");
				str.append("<td>" + summaryReport.getTown() + "</td>");
				str.append("<td>" + summaryReport.getCountry() + "</td>");
				str.append("</tr>");
				srno++;

				clientId = summaryReport.getClientId();
				// appointmentId = summaryReport.getAppointmentId();
			}

			str.append("</table><br><br>");

			String filePath = request.getRealPath("/liveData/ApptVsDNAReport/");
//			System.out.println(filePath);
			String filename = "ApptVsDNA_" + date + ".pdf";
			// String filename ="commission.pdf";
			session.setAttribute("appVsDnaFileName", filePath + "/" + filename);

			htmlToPdfFile(str.toString(), filePath, filename);

//			System.out.println("PDF Created..");

			response.setContentType("text/html");
			response.setHeader("Cache-Control", "no-cache");
			response.getWriter().write("" + filename + "");

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			connection.close();
		}
		return null;
	}

	public String sendEmailappVsDna() throws Exception {

		if (!verifyLogin(request)) {
			return "login";
		}
		Connection connection = null;
		try {

			connection = Connection_provider.getconnection();

			String to = request.getParameter("to");
			String cc = request.getParameter("cc");
			String subject = request.getParameter("subject");
			String notes = request.getParameter("emailBody");
			String file = request.getParameter("file");

			String filename = (String) session.getAttribute("appVsDnaFileName");

			// int result = emailTemplateDAO.saveEmailLogDetails(to, cc,
			// subject, notes, date1,time);
			String type = "Email";
			int appointmentid = 0;
			int invoiceid = 0;
			EmailLetterLog emailLetterLog = new EmailLetterLog();
			emailLetterLog.setAppointmentid(appointmentid);
			emailLetterLog.setInvoiceid(invoiceid);
			emailLetterLog.setType(type);

			EmbeddedImageEmailUtil.sendMailAttachment(connection, loginInfo.getId(), to, cc, subject, notes, filename,
					loginInfo, emailLetterLog, "0");

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			connection.close();
		}
		return null;
	}

	public String previewReferalTreatmentReport() throws Exception {
		if (!verifyLogin(request)) {
			return "login";
		}

		Connection connection = null;
		int referal = Integer.parseInt(request.getParameter("referal"));
		String clientId = "";
		int appointmentId = 0;

		try {
			connection = Connection_provider.getconnection();
			SummaryReportDAO summaryReportDAO = new JDBCSummaryReportDAO(connection);
			ArrayList<SummaryReport> treatmentReferralList = (ArrayList<SummaryReport>) session
					.getAttribute("treatmentReferralList");

			StringBuffer str = new StringBuffer();

			String currentDate = DateTimeUtils.getDateinSimpleFormate(new Date());
			String temp[] = currentDate.split(" ");
			String temptime[] = currentDate.split("at");
			String temp1[] = temp[0].split("-");
			String date = temp1[0] + "-" + temp1[1] + "-" + temp1[2];
			String time = temptime[1];

			str.append("<div style='font-size: 15px; font-weight: bold;'> Referal Treatment Report </div>");
			str.append("<div style='font-size: 10px; font-weight: bold;'> Date: " + date + " </div>");
			str.append("<div style='font-size: 10px; font-weight: bold;'> Time: " + time + " </div>");
			str.append("<br>");

			str.append("<table cellpadding='0' cellspacing='0' style='width: 100%;font-size: 10px;'>");

			str.append("<thead>");
			str.append("<tr>");

			str.append("<th>Payed By</th> ");
			str.append("<th>Practitoner</th> ");
			str.append("<th>TP Name</th> ");
			str.append("<th>Gp Name</th> ");
			str.append("<th>Client No</th> ");
			str.append("<th>Client Name</th> ");

			str.append("<th>Address</th> ");
			str.append("<th>Town</th> ");

			str.append("<th>Treatment Name</th> ");
			str.append("<th>Ref_date</th> ");
			str.append("<th>SOI</th> ");
			str.append("<th>Condition</th> ");
			str.append("<th>Referred By</th> ");

			str.append("</tr>");
			str.append("</thead>");
			str.append("<tbody>");
			int srno = 1;
			for (SummaryReport summaryReport : treatmentReferralList) {
				str.append("<tr class= 'bg-info'>");
				str.append("<td>" + summaryReport.getPayby() + "</td>");
				str.append("<td>" + summaryReport.getPractitionerName() + "</td>");
				str.append("<td>" + summaryReport.getTpName() + "</td>");
				str.append("<td>" + summaryReport.getGpName() + "</td>");
				str.append("<td>" + summaryReport.getClientId() + "</td>");
				str.append("<td>" + summaryReport.getClientname() + " <br> " + summaryReport.getEmail() + " / "
						+ summaryReport.getMobno() + "</td>");

				str.append("<td>" + summaryReport.getAddress() + "</td>");
				str.append("<td>" + summaryReport.getTown() + "</td>");

				str.append("<td>" + summaryReport.getTreatmentName() + "</td>");
				str.append("<td>" + summaryReport.getRef_date() + "</td>");
				str.append("<td>" + summaryReport.getRef_source() + "</td>");
				str.append("<td>" + summaryReport.getCondition() + "</td>");
				str.append("<td>" + summaryReport.getReferal() + "</td>");

				str.append("</tr>");
				srno++;

				clientId = summaryReport.getClientId();
				// appointmentId = summaryReport.getAppointmentId();
			}

			str.append("</table><br><br>");

			String filePath = request.getRealPath("/liveData/ReferalTreatmentReport/");
//			System.out.println(filePath);
			String filename = "ReferalTreatment_" + date + ".pdf";
			// String filename ="commission.pdf";

			htmlToPdfFile(str.toString(), filePath, filename);
			session.setAttribute("referalFileName", filePath + "/" + filename);

//			System.out.println("PDF Created..");

			response.setContentType("text/html");
			response.setHeader("Cache-Control", "no-cache");
			response.getWriter().write("" + filename + "");

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			connection.close();
		}
		return null;
	}

	public String sendEmailreferal() throws Exception {

		if (!verifyLogin(request)) {
			return "login";
		}
		Connection connection = null;
		try {

			connection = Connection_provider.getconnection();

			String to = request.getParameter("to");
			String cc = request.getParameter("cc");
			String subject = request.getParameter("subject");
			String notes = request.getParameter("emailBody");
			String file = request.getParameter("file");

			String filename = (String) session.getAttribute("referalFileName");

			// int result = emailTemplateDAO.saveEmailLogDetails(to, cc,
			// subject, notes, date1,time);
			String type = "Email";
			int appointmentid = 0;
			int invoiceid = 0;
			EmailLetterLog emailLetterLog = new EmailLetterLog();
			emailLetterLog.setAppointmentid(appointmentid);
			emailLetterLog.setInvoiceid(invoiceid);
			emailLetterLog.setType(type);

			EmbeddedImageEmailUtil.sendMailAttachment(connection, loginInfo.getId(), to, cc, subject, notes, filename,
					loginInfo, emailLetterLog, "0");

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			connection.close();
		}
		return null;
	}

	public void htmlToPdfFile(String htmlString, String filepath, String fileaName) throws Exception {

		CYaHPConverter converter = new CYaHPConverter();
		File fout = new File(filepath + "/" + fileaName);
		FileOutputStream out = new FileOutputStream(fout);
		Map properties = new HashMap();
		ArrayList headerFooterList = new ArrayList();

		properties.put(IHtmlToPdfTransformer.PDF_RENDERER_CLASS, IHtmlToPdfTransformer.FLYINGSAUCER_PDF_RENDERER);
		// properties.put(IHtmlToPdfTransformer.FOP_TTF_FONT_PATH, fontPath);
		converter.convertToPdf(htmlString, IHtmlToPdfTransformer.A4L, (java.util.List) headerFooterList,
				"file:///temp/", out, properties);
		out.flush();
		out.close();
	}

	public SummaryReportForm getModel() {
		// TODO Auto-generated method stub
		return summaryReportForm;
	}

	public String ipdopdRefundReport() throws SQLException {
		Connection connection = null;
		try {
			connection = Connection_provider.getconnection();
			AdditionalDAO additionalDAO = new JDBCAdditionalDAO(connection);
			String fromdate = summaryReportForm.getFromDate();
			String todate = summaryReportForm.getToDate();
			String ipdopdwise = summaryReportForm.getIpdopdwise();
			String invoiceid=DateTimeUtils.isNull(summaryReportForm.getInvoiceid())	;
			if (ipdopdwise == null) {
				ipdopdwise = "0";
			} else if (ipdopdwise.equals("")) {
				ipdopdwise = "0";
			}
			if (fromdate == null) {
				DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
				Calendar cal = Calendar.getInstance();
				fromdate = dateFormat.format(cal.getTime());
				fromdate = DateTimeUtils.getCommencingDate1(fromdate);
			} else {
				if (fromdate.equals("")) {
					DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
					Calendar cal = Calendar.getInstance();
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
			if (ipdopdwise.equals("9") || ipdopdwise.equals("8")) {
				ArrayList<CompleteAppointment> parentrefundrequestlist = new ArrayList<CompleteAppointment>();
				summaryReportForm.setParentrefundrequestlist(parentrefundrequestlist);
			} else {
				ArrayList<CompleteAppointment> parentrefundrequestlist = additionalDAO.getRefundReportList(fromdate,
						todate, ipdopdwise,loginInfo,invoiceid);
				summaryReportForm.setParentrefundrequestlist(parentrefundrequestlist);
			}

			ArrayList<CompleteAppointment> autorefundlist = new ArrayList<CompleteAppointment>();
			ArrayList<Accounts> ipdRefundList = new ArrayList<Accounts>();
			ArrayList<Accounts> opdRefundList = new ArrayList<Accounts>();
			if (loginInfo.isBalgopal()) {
				boolean flag = true;
				if (ipdopdwise.equals("9")) {
					flag = false;
					ipdRefundList = additionalDAO.getAutoRefundListNew(fromdate, todate, "9");
				} else if (ipdopdwise.equals("8")) {
					flag = false;
					opdRefundList = additionalDAO.getAutoRefundListNew(fromdate, todate, "8");
				} else if (ipdopdwise.equals("0")) {
					ipdRefundList = additionalDAO.getAutoRefundListNew(fromdate, todate, "9");
					opdRefundList = additionalDAO.getAutoRefundListNew(fromdate, todate, "8");
				}

			} else {
				autorefundlist = additionalDAO.getAutoRefundList(fromdate, todate, ipdopdwise);
			}
			summaryReportForm.setIpdRefundList(ipdRefundList);
			summaryReportForm.setOpdRefundList(opdRefundList);
			summaryReportForm.setAutorefundlist(autorefundlist);
			summaryReportForm.setFromDate(DateTimeUtils.getCommencingDate1(fromdate));
			summaryReportForm.setToDate(DateTimeUtils.getCommencingDate1(todate));
			summaryReportForm.setIpdopdwise(ipdopdwise);
			Clinic clinic = new Clinic();
			ClinicDAO clinicDAO = new JDBCClinicDAO(connection);
			clinic = clinicDAO.getCliniclistDetails(loginInfo.getClinicid());
			AccountsDAO accountsDAO = new JDBCAccountsDAO(connection);
			ArrayList<Clinic> locationAdressList = accountsDAO.getLocationAddress(loginInfo.getClinicid());
			summaryReportForm.setClinicName(clinic.getClinicName());
			summaryReportForm.setClinicOwner(clinic.getClinicOwner());
			summaryReportForm.setOwner_qualification(clinic.getOwner_qualification());
			summaryReportForm.setLocationAdressList(locationAdressList);
			summaryReportForm.setAddress(clinic.getAddress());
			summaryReportForm.setLandLine(clinic.getLandLine());
			summaryReportForm.setClinicemail(clinic.getEmail());
			summaryReportForm.setWebsiteUrl(clinic.getWebsiteUrl());
			summaryReportForm.setClinicLogo(clinic.getUserImageFileName());
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			connection.close();
		}
		return "refundreport";
	}

	public String ipdBillRegister() {
		return "ipdBillRegister";
	}

	public String serviceRegisterDetails() {
		return "serviceRegisterDetails";
	}

	public String departmentWiseAnalysisReport() {
		return "departmentWiseAnalysisReport";
	}

	public void prepare() throws Exception {
		Connection connection = null;

		try {

			connection = Connection_provider.getconnection();
			NotAvailableSlotDAO notAvailableSlotDAO = new JDBCNotAvailableSlotDAO(connection);
			ClientDAO clientDAO = new JDBCClientDAO(connection);
			ArrayList<DiaryManagement> userList = notAvailableSlotDAO.getUserList(loginInfo.getId());
			summaryReportForm.setUserList(userList);

			SummaryReportDAO summaryReportDAO = new JDBCSummaryReportDAO(connection);
			ArrayList<SummaryReport> referalList = summaryReportDAO.getReferalList();
			summaryReportForm.setReferalList(referalList);

			connection = Connection_provider.getconnection();
			AccountsDAO accountsDAO = new JDBCAccountsDAO(connection);

			ArrayList<Accounts> thirdPartyList = accountsDAO.getOnlyThirdPartyList(loginInfo.getId());
			summaryReportForm.setThirdPartyList(thirdPartyList);

			ArrayList<Accounts> gpList = accountsDAO.getOnlyGpList(loginInfo.getId());
			summaryReportForm.setGpList(gpList);

			TreatmentTypeDAO treatmentTypeDAO = new JDBCTreatmentTypeDAO(connection);
			ArrayList<TreatmentType> treatmentTypeList = treatmentTypeDAO.getConditionList();
			summaryReportForm.setTreatmentTypeList(treatmentTypeList);

			ArrayList<Client> sourceOfIntroList = clientDAO.getSourceOfIntroList();
			summaryReportForm.setSourceOfIntroList(sourceOfIntroList);

			ArrayList<Accounts> locationList = accountsDAO.getLocationList(loginInfo.getClinicid());
			summaryReportForm.setLocationList(locationList);

			EmrDAO emrDAO = new JDBCEmrDAO(connection);
			ArrayList<Discharge> dischargeOutcomeList = emrDAO.getDischrageOutcomeList();
			summaryReportForm.setDischargeOutcomeList(dischargeOutcomeList);

			ArrayList<Discharge> dischargeStatusList = emrDAO.getDischrageStatusList();
			summaryReportForm.setDischargeStatusList(dischargeStatusList);

			// lettrehead

			Clinic clinic = new Clinic();
			ClinicDAO clinicDAO = new JDBCClinicDAO(connection);
			clinic = clinicDAO.getCliniclistDetails(loginInfo.getClinicid());
			summaryReportForm.setClinicName(clinic.getClinicName());
			summaryReportForm.setClinicOwner(clinic.getClinicOwner());
			summaryReportForm.setOwner_qualification(clinic.getOwner_qualification());

			summaryReportForm.setAddress(clinic.getAddress());
			summaryReportForm.setLandLine(clinic.getLandLine());
			summaryReportForm.setClinicemail(clinic.getEmail());
			summaryReportForm.setWebsiteUrl(clinic.getWebsiteUrl());
			summaryReportForm.setClinicLogo(clinic.getUserImageFileName());

		} catch (Exception e) {

		} finally {
			connection.close();
		}
	}

	public String chargesharereport() throws Exception {
		Connection connection = null;
		try {
			//   06 oct 2017 Prepare Charge Report
			connection = Connection_provider.getconnection();
			UserProfileDAO profileDAO = new JDBCUserProfileDAO(connection);
			SummaryReportDAO summaryReportDAO = new JDBCSummaryReportDAO(connection);
			ChargesReportDAO chargesReportDAO = new JDBCChargesReportDAO(connection);
			ClientDAO clientDAO = new JDBCClientDAO(connection);
			String fromdate = summaryReportForm.getFromDate();
			String todate = summaryReportForm.getToDate();
			String practitionerId = summaryReportForm.getPractitionerId();
			String clientid = summaryReportForm.getClientId();
			if (fromdate == null) {
				DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
				Calendar cal = Calendar.getInstance();
				fromdate = dateFormat.format(cal.getTime());
				fromdate = DateTimeUtils.getCommencingDate1(fromdate);
			} else {
				if (fromdate.equals("")) {
					DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
					Calendar cal = Calendar.getInstance();
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
			if (practitionerId != null) {
				if (practitionerId.equals("")) {
					practitionerId = null;
				} else if (practitionerId.equals("0")) {
					practitionerId = null;
				}
			}
			if (clientid != null) {
				if (clientid.equals("")) {
					clientid = null;
				} else if (clientid.equals("0")) {
					clientid = null;
				}
			}

			DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Calendar cal = Calendar.getInstance();
			String date = dateFormat.format(cal.getTime());

			int result = chargesReportDAO.saveMisReportLog("Charge Share Report", loginInfo.getUserId(), fromdate,
					todate, date, "chargesharereport");
			ArrayList<Accounts> chargesharelist = summaryReportDAO.getChargesShareList(fromdate, todate, practitionerId,
					clientid);
			summaryReportForm.setChargesharelist(chargesharelist);
			ArrayList<Master> doctorlist = profileDAO.getDoctorList();
			summaryReportForm.setDoctorlist(doctorlist);
			ArrayList<Client> clientlist = clientDAO.getAllPatient();
			summaryReportForm.setClientlist(clientlist);
			summaryReportForm.setFromDate(DateTimeUtils.getCommencingDate1(fromdate));
			summaryReportForm.setToDate(DateTimeUtils.getCommencingDate1(todate));
			summaryReportForm.setClientId(clientid);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			connection.close();
		}
		return "chargesharereport";
	}

	public String billingreport() throws Exception {
		Connection connection = null;
		try {
			//   16 feb 2018 Prepare Billing Report
			connection = Connection_provider.getconnection();
			SummaryReportDAO summaryReportDAO = new JDBCSummaryReportDAO(connection);
			ChargesReportDAO chargesReportDAO = new JDBCChargesReportDAO(connection);
			AccountsDAO accountsDAO = new JDBCAccountsDAO(connection);
			String fromdate = summaryReportForm.getFromDate();
			String todate = summaryReportForm.getToDate();
			String itype = summaryReportForm.getItype();
			String payee = summaryReportForm.getPaymentmode();
			if (fromdate == null) {
				DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
				Calendar cal = Calendar.getInstance();
				fromdate = dateFormat.format(cal.getTime());
				fromdate = DateTimeUtils.getCommencingDate1(fromdate);
			} else {
				if (fromdate.equals("")) {
					DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
					Calendar cal = Calendar.getInstance();
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

			if (itype == null) {
				itype = "0";
			}
			if (payee == null) {
				payee = "0";
			}
			DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Calendar cal = Calendar.getInstance();
			String date = dateFormat.format(cal.getTime());

			int result = chargesReportDAO.saveMisReportLog("Billing Report", loginInfo.getUserId(), fromdate, todate,
					date, "billingreport");
			ArrayList<Accounts> bilingreportlist = summaryReportDAO.getBillingReport(fromdate, todate, itype, payee);
			summaryReportForm.setBilingreportlist(bilingreportlist);
			summaryReportForm.setFromDate(DateTimeUtils.getCommencingDate1(fromdate));
			summaryReportForm.setToDate(DateTimeUtils.getCommencingDate1(todate));

			ArrayList<Master> invoicetypelist = accountsDAO.getInvoiceTypeList();
			summaryReportForm.setInvoicetypelist(invoicetypelist);
			summaryReportForm.setItype(itype);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			connection.close();
		}
		return "billingreport";
	}

	public String discountreport() throws Exception {
		Connection connection = null;
		try {
			//   16 feb 2018 Prepare Billing Report
			connection = Connection_provider.getconnection();
			SummaryReportDAO summaryReportDAO = new JDBCSummaryReportDAO(connection);
			ChargesReportDAO chargesReportDAO = new JDBCChargesReportDAO(connection);
			String invTpe = DateTimeUtils.isNull(summaryReportForm.getItype());
			String fromdate = summaryReportForm.getFromDate();
			String todate = summaryReportForm.getToDate();
			if (fromdate == null) {
				DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
				Calendar cal = Calendar.getInstance();
				fromdate = dateFormat.format(cal.getTime());
				fromdate = DateTimeUtils.getCommencingDate1(fromdate);
			} else {
				if (fromdate.equals("")) {
					DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
					Calendar cal = Calendar.getInstance();
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
			DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Calendar cal = Calendar.getInstance();
			String date = dateFormat.format(cal.getTime());

			int result = chargesReportDAO.saveMisReportLog("Discount Report", loginInfo.getUserId(), fromdate, todate,
					date, "discountreport");
			ArrayList<Accounts> discountreportlist = summaryReportDAO.getDiscountReport(fromdate, todate, invTpe);
			ArrayList<Accounts> discountchargereportlist = summaryReportDAO.getDiscounchargetReport(fromdate, todate,
					invTpe);
			summaryReportForm.setDiscountchargereportlist(discountchargereportlist);
			if (discountreportlist.size() != 0) {
				Accounts acc = discountreportlist.get(discountreportlist.size() - 1);
				summaryReportForm.setTotalbillamount(acc.getTotalbillamount());
				summaryReportForm.setTotalrecammount(acc.getTotalrecammount());
				summaryReportForm.setTotaldiscountammount(acc.getTotaldiscountammount());
				summaryReportForm.setTotalrefundamount(acc.getTotalrefundamount());
			} else {
				summaryReportForm.setTotalbillamount(0.0);
				summaryReportForm.setTotalrecammount(0.0);
				summaryReportForm.setTotaldiscountammount(0.0);
				summaryReportForm.setTotalrefundamount(0.0);
			}
			AccountsDAO accountsDAO = new JDBCAccountsDAO(connection);
			ArrayList<Master> invoicetypelist = accountsDAO.getInvoiceTypeList();
			summaryReportForm.setInvoicetypelist(invoicetypelist);
			summaryReportForm.setDiscountreportlist(discountreportlist);
			summaryReportForm.setFromDate(DateTimeUtils.getCommencingDate1(fromdate));
			summaryReportForm.setToDate(DateTimeUtils.getCommencingDate1(todate));

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			connection.close();
		}
		return "discountreport";
	}

	public String cancelinvoicereport() throws Exception {
		Connection connection = null;
		try {

			connection = Connection_provider.getconnection();
			SummaryReportDAO summaryReportDAO = new JDBCSummaryReportDAO(connection);
			ChargesReportDAO chargesReportDAO = new JDBCChargesReportDAO(connection);
			AccountsDAO accountsDAO=new JDBCAccountsDAO(connection);
			String fromdate = summaryReportForm.getFromDate();
			String todate = summaryReportForm.getToDate();
			ArrayList<Master> invoicetypelist = accountsDAO.getInvoiceTypeList();
			summaryReportForm.setInvoicetypelist(invoicetypelist);
			String ipdopdfilter = DateTimeUtils.isNull(summaryReportForm.getItype());
			if (fromdate == null) {
				DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
				Calendar cal = Calendar.getInstance();
				fromdate = dateFormat.format(cal.getTime());
				fromdate = DateTimeUtils.getCommencingDate1(fromdate);
			} else {
				if (fromdate.equals("")) {
					DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
					Calendar cal = Calendar.getInstance();
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
			DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Calendar cal = Calendar.getInstance();
			String date = dateFormat.format(cal.getTime());

			// int result = chargesReportDAO.saveMisReportLog("Cancel Invoice
			// Report",loginInfo.getUserId(),fromdate,todate,date,"cancelinvoicereport");
			ArrayList<Accounts> cancelinvoicereportlist = summaryReportDAO.getCancelInvoiceReport(fromdate, todate,
					ipdopdfilter);
			summaryReportForm.setCancelinvoicereportlist(cancelinvoicereportlist);
			summaryReportForm.setFromDate(DateTimeUtils.getCommencingDate1(fromdate));
			summaryReportForm.setToDate(DateTimeUtils.getCommencingDate1(todate));

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			connection.close();
		}
		return "cancelinvoicereport";
	}

	public String ipdmonthlyreport() throws Exception {
		Connection connection = null;
		try {
			connection = Connection_provider.getconnection();
			SummaryReportDAO summaryReportDAO = new JDBCSummaryReportDAO(connection);
			MisChartDAO misChartDAO = new JDBCMisChartDAO(connection);
			ChargesReportDAO chargesReportDAO = new JDBCChargesReportDAO(connection);
			String fromdate = summaryReportForm.getFromDate();
			String todate = summaryReportForm.getToDate();

			if (fromdate == null) {
				DateFormat dateFormat = new SimpleDateFormat("MM");
				Calendar cal = Calendar.getInstance();
				fromdate = dateFormat.format(cal.getTime());

			} else {
				if (fromdate.equals("")) {
					DateFormat dateFormat = new SimpleDateFormat("MM");
					Calendar cal = Calendar.getInstance();
					fromdate = dateFormat.format(cal.getTime());

				} /*
					 * else { fromdate =
					 * DateTimeUtils.getCommencingDate1(fromdate); }
					 */
			}
			if (todate == null) {
				DateFormat dateFormat = new SimpleDateFormat("yyyy");
				Calendar cal = Calendar.getInstance();
				todate = dateFormat.format(cal.getTime());

			} else {
				if (todate.equals("")) {
					DateFormat dateFormat = new SimpleDateFormat("yyyy");
					Calendar cal = Calendar.getInstance();
					todate = dateFormat.format(cal.getTime());

				} /*
					 * else { todate = DateTimeUtils.getCommencingDate1(todate);
					 * }
					 */
			}

			String newfromdate = fromdate;
			String newtodate = todate;
			fromdate = "01-" + newfromdate + "-" + newtodate + "";
			todate = "31-" + newfromdate + "-" + newtodate + "";
			fromdate = DateTimeUtils.getCommencingDate1(fromdate);
			todate = DateTimeUtils.getCommencingDate1(todate);

			DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Calendar cal = Calendar.getInstance();
			String date = dateFormat.format(cal.getTime());

			int result = chargesReportDAO.saveMisReportLog("IPD Monthly Report", loginInfo.getUserId(), fromdate,
					todate, date, "ipdmonthlyreport");

			int totalopdseen = misChartDAO.getBookedAppointment(fromdate, todate);
			int ipdnewadmission = misChartDAO.getIpdNewAdmission(fromdate, todate);
			/*
			 * ArrayList<Accounts> chargereportlist =
			 * summaryReportDAO.getChargeReportDepartmentWise(fromdate,todate);
			 */
			ArrayList<Accounts> chargereportlist = summaryReportDAO.getInvoiceReportDepartmentWise(fromdate, todate);
			String ipddebitcount = summaryReportDAO.getDepartmentWiseDebit(fromdate, todate, "2");
			String opddebitcount = summaryReportDAO.getDepartmentWiseDebit(fromdate, todate, "1");
			String investigationdebitcount = summaryReportDAO.getDepartmentWiseDebit(fromdate, todate, "3");
			String medicinedebitcount = summaryReportDAO.getDepartmentWiseDebit(fromdate, todate, "0");
			String netipddebitcount = summaryReportDAO.getNetDepartmentWiseDebit(fromdate, todate, "2", 1);
			String netopddebitcount = summaryReportDAO.getNetDepartmentWiseDebit(fromdate, todate, "1", 1);
			String netinvestigationdebitcount = summaryReportDAO.getNetDepartmentWiseDebit(fromdate, todate, "3", 1);
			String netmedicinedebitcount = summaryReportDAO.getNetDepartmentWiseDebit(fromdate, todate, "0", 1);
			summaryReportForm.setMedicinedebitcount(medicinedebitcount);
			summaryReportForm.setNetmedicinedebitcount(netmedicinedebitcount);

			summaryReportForm.setNetipddebitcount(netipddebitcount);
			summaryReportForm.setNetopddebitcount(netopddebitcount);
			summaryReportForm.setNetinvestigationdebitcount(netinvestigationdebitcount);

			ArrayList<NotAvailableSlot> otprocedurechargelist = summaryReportDAO.getTotalOTSurgeries(fromdate, todate);

			summaryReportForm.setOtprocedurechargelist(otprocedurechargelist);

			int size = otprocedurechargelist.size();
			if (size > 0) {
				int totolintitaldischarge = Integer.parseInt(otprocedurechargelist.get(size - 1).getTotal());
				summaryReportForm.setTotalprocedure(totolintitaldischarge);
			} else {
				summaryReportForm.setTotalprocedure(0);
			}

			//   04-April-2018 bed occupancy
			Ipd ipd = summaryReportDAO.getBedOccupancyrate(fromdate, todate);
			summaryReportForm.setTotalbed(ipd.getTotalbed());
			summaryReportForm.setTotalpatient(ipd.getTotalpatient());
			summaryReportForm.setTotalbedoccupancy(ipd.getTotalbedoccupancy());
			double averagestay = 0;
			if (ipdnewadmission > 0) {
				averagestay = ipd.getTotalpatient() / ipdnewadmission;
			}

			summaryReportForm.setAveragestay(Math.round(averagestay * 100.0) / 100.0);
			summaryReportForm.setTotalopdseen(totalopdseen);
			summaryReportForm.setIpdnewadmission(ipdnewadmission);
			summaryReportForm.setChargereportlist(chargereportlist);
			summaryReportForm.setIpddebitcount(String.valueOf(ipddebitcount));
			summaryReportForm.setOpddebitcount(String.valueOf(opddebitcount));
			summaryReportForm.setInvestigationdebitcount(String.valueOf(investigationdebitcount));

			summaryReportForm.setFromDate(newfromdate);
			summaryReportForm.setToDate(newtodate);
			// letter head
			Clinic clinic = new Clinic();
			ClinicDAO clinicDAO = new JDBCClinicDAO(connection);
			AccountsDAO accountsDAO = new JDBCAccountsDAO(connection);
			ArrayList<Clinic> locationAdressList = accountsDAO.getLocationAddress(loginInfo.getClinicid());
			clinic = clinicDAO.getCliniclistDetails(loginInfo.getClinicid());
			summaryReportForm.setClinicName(clinic.getClinicName());
			summaryReportForm.setClinicOwner(clinic.getClinicOwner());
			summaryReportForm.setOwner_qualification(clinic.getOwner_qualification());
			summaryReportForm.setLocationAdressList(locationAdressList);
			summaryReportForm.setAddress(clinic.getAddress());
			summaryReportForm.setLandLine(clinic.getLandLine());
			summaryReportForm.setClinicemail(clinic.getEmail());
			summaryReportForm.setWebsiteUrl(clinic.getWebsiteUrl());
			summaryReportForm.setClinicLogo(clinic.getUserImageFileName());
			String month = "";
			//   04 Oct 2018
			if (newfromdate.equals("01")) {
				month = "January";
			} else if (newfromdate.equals("02")) {
				month = "February";
			} else if (newfromdate.equals("03")) {
				month = "March";
			} else if (newfromdate.equals("04")) {
				month = "April";
			} else if (newfromdate.equals("05")) {
				month = "May";
			} else if (newfromdate.equals("06")) {
				month = "June";
			} else if (newfromdate.equals("07")) {
				month = "July";
			} else if (newfromdate.equals("08")) {
				month = "August";
			} else if (newfromdate.equals("09")) {
				month = "September";
			} else if (newfromdate.equals("10")) {
				month = "October";
			} else if (newfromdate.equals("11")) {
				month = "November";
			} else if (newfromdate.equals("12")) {
				month = "December";
			}
			summaryReportForm.setMonth(month);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			connection.close();
		}
		return "ipdmonthlyreport";
	}

	public String ipddailyadddis() throws Exception {
		Connection connection = null;
		try {
			//   22 feb 2018 Prepare Billing Report
			connection = Connection_provider.getconnection();
			SummaryReportDAO summaryReportDAO = new JDBCSummaryReportDAO(connection);
			String fromdate = summaryReportForm.getFromDate();
			String todate = summaryReportForm.getToDate();
			if (fromdate == null) {
				DateFormat dateFormat = new SimpleDateFormat("MM");
				Calendar cal = Calendar.getInstance();
				fromdate = dateFormat.format(cal.getTime());

			} else {
				if (fromdate.equals("")) {
					DateFormat dateFormat = new SimpleDateFormat("MM");
					Calendar cal = Calendar.getInstance();
					fromdate = dateFormat.format(cal.getTime());

				}
			}
			if (todate == null) {
				DateFormat dateFormat = new SimpleDateFormat("yyyy");
				Calendar cal = Calendar.getInstance();
				todate = dateFormat.format(cal.getTime());

			} else {
				if (todate.equals("")) {
					DateFormat dateFormat = new SimpleDateFormat("yyyy");
					Calendar cal = Calendar.getInstance();
					todate = dateFormat.format(cal.getTime());

				}
			}
			String toDaysDate = null;
			{
				DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
				Calendar cal = Calendar.getInstance();
				cal.add(Calendar.DATE, 0);
				toDaysDate = dateFormat.format(cal.getTime());
			}

			String newfromdate = fromdate;
			String newtodate = todate;
			fromdate = "01-" + newfromdate + "-" + newtodate + "";
			todate = "31-" + newfromdate + "-" + newtodate + "";
			fromdate = DateTimeUtils.getCommencingDate1(fromdate);
			todate = DateTimeUtils.getCommencingDate1(todate);

			IpdDAO ipdDAO = new JDBCIpdDAO(connection);
			MisChartDAO misChartDAO = new JDBCMisChartDAO(connection);

			Calendar cal = Calendar.getInstance();

			// set the year,month and day to something else
			int newmonth = Integer.parseInt(newfromdate) - 1;
			cal.set(Integer.parseInt(newtodate), newmonth, 1);
			/* int maxDay = cal.getActualMaximum(Calendar.DAY_OF_MONTH); */

			String[] lastday = getLastDayOfMonth(Integer.parseInt(newtodate), Integer.parseInt(newfromdate)).split("-");
			int maxDay = Integer.parseInt(lastday[2]);
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
			String date = df.format(cal.getTime());
			for (int i = 1; i <= maxDay; i++) {
				String cuurdate = "";
				if (i == 1) {
					/* cal.set(Calendar.DAY_OF_MONTH, i + 1); */
					String today = df.format(cal.getTime());
					cuurdate = today;
					Boolean flag1 = ipdDAO.checkPatientAdmorDis(today);
					int dischargepatients = misChartDAO.getDischargePatients(today, today);
					int ipdnewadmission = misChartDAO.getIpdNewAdmission(today, today);
					// Ipd ippd = ipdDAO.getTotalAdmDisCount();
					int totaladmision = ipdnewadmission;
					int totaldischarge = dischargepatients;

					if (flag1) {
						// update
						int res = ipdDAO.updatePatientAdmandDis(today, ipdnewadmission, totaladmision,
								dischargepatients, totaldischarge);
					} else {
						// insert
						int res = ipdDAO.savePatientAdmandDis(today, ipdnewadmission, totaladmision, dischargepatients,
								totaldischarge);
					}
				} else {
					String yesterday = df.format(cal.getTime());
					cal.set(Calendar.DAY_OF_MONTH, i);
					String today = df.format(cal.getTime());
					cuurdate = today;
					Boolean flag1 = ipdDAO.checkPatientAdmorDis(today);
					int dischargepatients = misChartDAO.getDischargePatients(today, today);
					int ipdnewadmission = misChartDAO.getIpdNewAdmission(today, today);
					Ipd ippd = ipdDAO.getTotalAdmDisCount(date, today, yesterday);
					int totaladmision = ipdnewadmission + ippd.getTotaladmission();
					int totaldischarge = dischargepatients + ippd.getTotaldischarge();
					if (flag1) {
						// update
						int res = ipdDAO.updatePatientAdmandDis(today, ipdnewadmission, totaladmision,
								dischargepatients, totaldischarge);
					} else {
						// insert
						int res = ipdDAO.savePatientAdmandDis(today, ipdnewadmission, totaladmision, dischargepatients,
								totaldischarge);
					}
				}
				String curdate = DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone()).split(" ")[0];
				if (cuurdate.equals(curdate)) {
					break;
				}

			}

			ArrayList<Accounts> dailyIPDAdmsDischList = summaryReportDAO.getDailyIPDAdmsDischList(fromdate, todate);
			dailyIPDAdmsDischList.size();
			for (Accounts a : dailyIPDAdmsDischList) {
				String d = a.getDate();
				if (toDaysDate.equals(a.getDate())) {
					summaryReportForm.setTodaysadm(a.getTotaladmission());
					summaryReportForm.setTodaysdis(a.getTotaldischarge());
				}

			}
			summaryReportForm.setDailyIPDAdmsDischList(dailyIPDAdmsDischList);

			summaryReportForm.setFromDate(newfromdate);
			summaryReportForm.setToDate(newtodate);
			Clinic clinic = new Clinic();
			ClinicDAO clinicDAO = new JDBCClinicDAO(connection);
			AccountsDAO accountsDAO = new JDBCAccountsDAO(connection);
			ArrayList<Clinic> locationAdressList = accountsDAO.getLocationAddress(loginInfo.getClinicid());
			clinic = clinicDAO.getCliniclistDetails(loginInfo.getClinicid());
			summaryReportForm.setClinicName(clinic.getClinicName());
			summaryReportForm.setClinicOwner(clinic.getClinicOwner());
			summaryReportForm.setOwner_qualification(clinic.getOwner_qualification());
			summaryReportForm.setLocationAdressList(locationAdressList);
			summaryReportForm.setAddress(clinic.getAddress());
			summaryReportForm.setLandLine(clinic.getLandLine());
			summaryReportForm.setClinicemail(clinic.getEmail());
			summaryReportForm.setWebsiteUrl(clinic.getWebsiteUrl());
			summaryReportForm.setClinicLogo(clinic.getUserImageFileName());

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			connection.close();
		}
		return "ipddailyadddis";
	}

	public static String getLastDayOfMonth(int year, int month) throws Exception {
		DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

		Date date = sdf.parse(year + "-" + (month < 10 ? ("0" + month) : month) + "-01");

		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);

		calendar.add(Calendar.MONTH, 1);
		calendar.set(Calendar.DAY_OF_MONTH, 1);
		calendar.add(Calendar.DATE, -1);

		Date lastDayOfMonth = calendar.getTime();

		return sdf.format(lastDayOfMonth);
	}

	public String paymentreport() throws Exception {
		Connection connection = null;
		try {
			connection = Connection_provider.getconnection();
			SummaryReportDAO summaryReportDAO = new JDBCSummaryReportDAO(connection);
			ChargesReportDAO chargesReportDAO = new JDBCChargesReportDAO(connection);
			String fromdate = summaryReportForm.getFromDate();
			String todate = summaryReportForm.getToDate();
			String itype = summaryReportForm.getItype();
			String howpaid = summaryReportForm.getHowpaid();
			howpaid = DateTimeUtils.numberCheck(howpaid);
			
			if (fromdate == null) {
				DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
				Calendar cal = Calendar.getInstance();
				fromdate = dateFormat.format(cal.getTime());
				fromdate = DateTimeUtils.getCommencingDate1(fromdate);
			} else {
				if (fromdate.equals("")) {
					DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
					Calendar cal = Calendar.getInstance();
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

			if (itype != null) {
				if (itype.equals("") || itype.equals("0")) {
					itype = null;
				}
			} else {
				itype = null;
			}
			
			if (howpaid != null) {
				if (howpaid.equals("") || howpaid.equals("0")) {
					howpaid = null;
				}
			} else {
				howpaid = null;
			}

			DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Calendar cal = Calendar.getInstance();
			String date = dateFormat.format(cal.getTime());

			int result = chargesReportDAO.saveMisReportLog("Payment Report", loginInfo.getUserId(), fromdate, todate,
					date, "paymentreport");
			if (itype == null) {
				itype = "0";
			}
			double payment=0;
			double pay1=0;
			double pay2=0;
			
			AccountsDAO accountsDAO = new JDBCAccountsDAO(connection);
			ArrayList<Master> invoicetypelist = accountsDAO.getInvoiceTypeList();
			summaryReportForm.setInvoicetypelist(invoicetypelist);
			
			ArrayList<Accounts> paymentreportlist = new ArrayList<Accounts>();
			ArrayList<Accounts> paymentreportlist1 = new ArrayList<Accounts>();
			if (itype.equals("5")) {
				paymentreportlist = chargesReportDAO.getAdvancePaymentReportList(fromdate, todate, "", "", "", "", "",
						"", "", "", "", "0",howpaid);
				int size = paymentreportlist.size();
				if (size > 0) {
					pay1 = Double.parseDouble(DateTimeUtils.numberCheck(paymentreportlist.get(size - 1).getTotalamt()));
				}
			} else if(itype.equals("") || itype.equals("0")){
				paymentreportlist1 = chargesReportDAO.getAdvancePaymentReportList(fromdate, todate, "", "", "", "", "",
						"", "", "", "", "0",howpaid);
				paymentreportlist = summaryReportDAO.getPaymentReportList(fromdate, todate, itype,howpaid);
				int size = paymentreportlist.size();
				if (size > 0) {
					pay1 = Double.parseDouble(DateTimeUtils.numberCheck(paymentreportlist.get(size - 1).getTotalamt()));
				}
				int size1 = paymentreportlist1.size();
				if (size1 > 0) {
					pay2 = Double.parseDouble(DateTimeUtils.numberCheck(paymentreportlist1.get(size1 - 1).getTotalamt()));
				}
				paymentreportlist.addAll(paymentreportlist1);
			}else{
				paymentreportlist = summaryReportDAO.getPaymentReportList(fromdate, todate, itype,howpaid);
				int size = paymentreportlist.size();
				if (size > 0) {
					pay1 = Double.parseDouble(DateTimeUtils.numberCheck(paymentreportlist.get(size - 1).getTotalamt()));
				}
			}
			payment=pay1+pay2;
			if(!DateTimeUtils.isNull(howpaid).equals("")) {
				summaryReportForm.setPaymentmode(howpaid);
			}else {
				summaryReportForm.setPaymentmode("Total");
			}
			summaryReportForm.setTotalReceived(payment);
			summaryReportForm.setPaymentreportlist(paymentreportlist);
			summaryReportForm.setFromDate(DateTimeUtils.getCommencingDate1(fromdate));
			summaryReportForm.setToDate(DateTimeUtils.getCommencingDate1(todate));
			
			Clinic clinic = new Clinic();
			ClinicDAO clinicDAO = new JDBCClinicDAO(connection);
			clinic = clinicDAO.getCliniclistDetails(loginInfo.getClinicid());
			
			ArrayList<Clinic> locationAdressList = accountsDAO.getLocationAddress(loginInfo.getClinicid());
			summaryReportForm.setClinicName(clinic.getClinicName());
			summaryReportForm.setClinicOwner(clinic.getClinicOwner());
			summaryReportForm.setOwner_qualification(clinic.getOwner_qualification());
			summaryReportForm.setLocationAdressList(locationAdressList);
			summaryReportForm.setAddress(clinic.getAddress());
			summaryReportForm.setLandLine(clinic.getLandLine());
			summaryReportForm.setClinicemail(clinic.getEmail());
			summaryReportForm.setWebsiteUrl(clinic.getWebsiteUrl());
			summaryReportForm.setClinicLogo(clinic.getUserImageFileName());
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			connection.close();
		}
		return "newpaymentreport";
	}

	public String userwisepaymentreport() throws Exception {
		Connection connection = null;
		try {
			connection = Connection_provider.getconnection();
			SummaryReportDAO summaryReportDAO = new JDBCSummaryReportDAO(connection);
			ChargesReportDAO chargesReportDAO = new JDBCChargesReportDAO(connection);
			String fromdate = summaryReportForm.getFromDate();
			String todate = summaryReportForm.getToDate();
			String itype = summaryReportForm.getItype();
			if (fromdate == null) {
				DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
				Calendar cal = Calendar.getInstance();
				cal.add(Calendar.DATE, -30);
				fromdate = dateFormat.format(cal.getTime());
				fromdate = DateTimeUtils.getCommencingDate1(fromdate);
			} else {
				if (fromdate.equals("")) {
					DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
					Calendar cal = Calendar.getInstance();
					cal.add(Calendar.DATE, 0);
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

			/*
			 * if(itype!=null){ if(itype.equals("") || itype.equals("0")){
			 * itype= null; } }else{ itype = null; }
			 */
			DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Calendar cal = Calendar.getInstance();
			String date = dateFormat.format(cal.getTime());

			int result = chargesReportDAO.saveMisReportLog("User Wise Payment Report", loginInfo.getUserId(), fromdate,
					todate, date, "userwisepaymentreport");
			ArrayList<Accounts> userwisepaymentreportlist = new ArrayList<Accounts>();

			ArrayList<String> userlist = summaryReportDAO.getPaymentreceiveUserid(fromdate, todate);
			summaryReportForm.setFromDate(DateTimeUtils.getCommencingDate1(fromdate));
			summaryReportForm.setToDate(DateTimeUtils.getCommencingDate1(todate));
			ArrayList<String> uniquelist = new ArrayList<String>();
			for (String userid : userlist) {
				int size = uniquelist.size();
				if (size > 0) {
					boolean flag = false;
					for (String string : uniquelist) {
						if (string.equals(userid)) {
							flag = true;
							break;
						}
					}
					if (!flag) {
						uniquelist.add(userid);
					}
				} else {
					uniquelist.add(userid);
				}
			}

			summaryReportForm.setUniquelist(uniquelist);
			String name = summaryReportForm.getUsername();
			// double
			// gtamtcash=0.0,gtamtccard,gtamtcheque,gtamtdcard,gtamtneft,gtamttotal,gtrefundcash,gtrefundccard,gtrefunddcard,gtrefundcheque,gtrefundneft,gtrefundtotal;
			double ttcard = 0, ttcash = 0, ttcheque = 0, ttneft = 0, ttother = 0, ttupi = 0;
			double adttcard = 0, adttcash = 0, adttcheque = 0, adttneft = 0, adttother = 0 , adttupi=0;
			double rettcard = 0, rettcash = 0, rettcheque = 0, rettneft = 0, rettother = 0,rettupi=0;
			double tcard = 0, tcash = 0, tcheque = 0, tneft = 0, tother = 0, tnettotal = 0,tupi= 0;
			double gtrec = 0, gtref = 0, gtadv = 0;
			double totalopd = 0, totalipd = 0, totalinvst = 0, totalitypeamount = 0;
			double totaldebitamt=0,totalbalanceamt=0;
			ArrayList<SummaryReport> userwisepaymentlist = new ArrayList<SummaryReport>();
			int s = 0;
			for (String userid : uniquelist) {
				if (!name.equals("0")) {
					userid = name;
					s = 1;
				}
				SummaryReport sr = new SummaryReport();
				
				String payment = summaryReportDAO.getTotalPaidByUser(fromdate, todate, userid);
				String advance = summaryReportDAO.getTotalAdvanceByUser(fromdate, todate, userid);
				String refund = summaryReportDAO.getTotalRefundByUser(fromdate, todate, userid);
				String fullname = summaryReportDAO.getUserNamebyId(userid);
				String paymentbycash = summaryReportDAO.getTotalPaymentPaymode(fromdate, todate, userid, "Cash");
				String paymentbyCheque = summaryReportDAO.getTotalPaymentPaymode(fromdate, todate, userid, "Cheque");
				String paymentbyCCard = summaryReportDAO.getTotalPaymentPaymode(fromdate, todate, userid, "C/Card");
				String paymentbyDCard = summaryReportDAO.getTotalPaymentPaymode(fromdate, todate, userid, "D/Card");
				String paymentbyNEFT = summaryReportDAO.getTotalPaymentPaymode(fromdate, todate, userid, "NEFT");
				String paymentbyOther = summaryReportDAO.getTotalPaymentPaymode(fromdate, todate, userid, "Other");
				String paymentbyUPI = summaryReportDAO.getTotalPaymentPaymode(fromdate, todate, userid, "UPI");
				double paymentbycard1 = (Double.parseDouble(paymentbyCCard) + Double.parseDouble(paymentbyDCard));
				String paymentbycard = String.valueOf(paymentbycard1);

				// gtamtcash=Double.parseDouble(paymentbycash)+gtamtcash ;
				String advancebycash = summaryReportDAO.getTotalAdvancePaymode(fromdate, todate, userid, "Cash");
				String advancebyCheque = summaryReportDAO.getTotalAdvancePaymode(fromdate, todate, userid, "Cheque");
				String advancebyCCard = summaryReportDAO.getTotalAdvancePaymode(fromdate, todate, userid, "C/Card");
				String advancebyDCard = summaryReportDAO.getTotalAdvancePaymode(fromdate, todate, userid, "D/Card");
				String advancebyNEFT = summaryReportDAO.getTotalAdvancePaymode(fromdate, todate, userid, "NEFT");
				String advancebyOther = summaryReportDAO.getTotalAdvancePaymode(fromdate, todate, userid, "Other");
				String advancebyUPI = summaryReportDAO.getTotalAdvancePaymode(fromdate, todate, userid, "UPI");
				double advancebycard1 = (Double.parseDouble(advancebyCCard) + Double.parseDouble(advancebyDCard));
				String advancebycard = String.valueOf(advancebycard1);

				String refundbycash = summaryReportDAO.getTotalRefundPaymode(fromdate, todate, userid, "Cash");
				String refundbyCheque = summaryReportDAO.getTotalRefundPaymode(fromdate, todate, userid, "Cheque");
				String refundbyCCard = summaryReportDAO.getTotalRefundPaymode(fromdate, todate, userid, "C/Card");
				String refundbyDCard = summaryReportDAO.getTotalRefundPaymode(fromdate, todate, userid, "D/Card");
				String refundbyNEFT = summaryReportDAO.getTotalRefundPaymode(fromdate, todate, userid, "NEFT");
				String refundbyOther = summaryReportDAO.getTotalRefundPaymode(fromdate, todate, userid, "Other");
				String refundbyUPI = summaryReportDAO.getTotalRefundPaymode(fromdate, todate, userid, "UPI");

				String opd = summaryReportDAO.getTotalItypePayment(fromdate, todate, userid, 1);
				String ipd = summaryReportDAO.getTotalItypePayment(fromdate, todate, userid, 2);
				String investigation = summaryReportDAO.getTotalItypePayment(fromdate, todate, userid, 3);

				totalopd = totalopd + Double.parseDouble(opd);
				totalipd = totalipd + Double.parseDouble(ipd);
				totalinvst = totalinvst + Double.parseDouble(investigation);

				summaryReportForm.setTotalopdamount("" + totalopd);
				summaryReportForm.setTotalipdamount("" + totalipd);
				summaryReportForm.setTotalinvstamount("" + totalinvst);
				summaryReportForm.setTotalitypeamount("" + (totalopd + totalipd + totalinvst));

				sr.setTotalopdamount("" + opd);
				sr.setTotalipdamount("" + ipd);
				sr.setTotalinvstamount("" + investigation);
				sr.setTotalitypeamount(
						"" + (Double.parseDouble(opd) + Double.parseDouble(ipd) + Double.parseDouble(investigation)));
				double refundbycard1 = (Double.parseDouble(refundbyCCard) + Double.parseDouble(refundbyDCard));
				String refundbycard = String.valueOf(refundbycard1);
				ttcash = ttcash + Double.parseDouble(paymentbycash);
				ttcard = ttcard + Double.parseDouble(paymentbycard);
				ttcheque = ttcheque + Double.parseDouble(paymentbyCheque);
				ttneft = ttneft + Double.parseDouble(paymentbyNEFT);
				ttother = ttother + Double.parseDouble(paymentbyOther);
				ttupi = ttupi +  Double.parseDouble(paymentbyUPI);
				
				adttcash = adttcash + Double.parseDouble(advancebycash);
				adttcard = adttcard + Double.parseDouble(advancebycard);
				adttcheque = adttcheque + Double.parseDouble(advancebyCheque);
				adttneft = adttneft + Double.parseDouble(advancebyNEFT);
				adttother = adttother + Double.parseDouble(advancebyOther);
				adttupi = adttupi + Double.parseDouble(advancebyUPI);
				
				rettcash = rettcash + Double.parseDouble(refundbycash);
				rettcard = rettcard + Double.parseDouble(refundbycard);
				rettcheque = rettcheque + Double.parseDouble(refundbyCheque);
				rettneft = rettneft + Double.parseDouble(refundbyNEFT);
				rettother = rettother + Double.parseDouble(refundbyOther);
				rettupi = rettupi +  Double.parseDouble(refundbyUPI);
				
				gtrec = ttcash + ttcard + ttcheque + ttneft + ttother + ttupi;
				gtref = adttcash + adttcard + adttcheque + adttneft + adttother + adttupi;
				gtadv = rettcash + rettcard + rettcheque + rettneft + rettother + rettupi;

				summaryReportForm.setGtrec(gtrec);
				summaryReportForm.setGtref(gtref);
				summaryReportForm.setGtadv(gtadv);
				summaryReportForm.setTtcash(ttcash);
				summaryReportForm.setTtcard(ttcard);
				summaryReportForm.setTtcheque(ttcheque);
				summaryReportForm.setTtneft(ttneft);
				summaryReportForm.setTtother(ttother);
				summaryReportForm.setTtupi(ttupi);
				
				summaryReportForm.setAdttcash(adttcash);
				summaryReportForm.setAdttcard(adttcard);
				summaryReportForm.setAdttcheque(adttcheque);
				summaryReportForm.setAdttneft(adttneft);
				summaryReportForm.setAdttother(adttother);
				summaryReportForm.setAdttupi(adttupi);
				
				summaryReportForm.setRettcash(rettcash);
				summaryReportForm.setRettcard(rettcard);
				summaryReportForm.setRettcheque(rettcheque);
				summaryReportForm.setRettneft(rettneft);
				summaryReportForm.setRettother(rettother);
				summaryReportForm.setRettupi(rettupi);

				double totalpayment = Double.parseDouble(paymentbycash) + Double.parseDouble(paymentbyCCard)
						+ Double.parseDouble(paymentbyDCard) + Double.parseDouble(paymentbyNEFT)
						+ Double.parseDouble(paymentbyCheque) + Double.parseDouble(paymentbyOther)+ Double.parseDouble(paymentbyUPI);
				double totalrefund = Double.parseDouble(refundbycash) + Double.parseDouble(refundbyCCard)
						+ Double.parseDouble(refundbyCheque) + Double.parseDouble(refundbyDCard)
						+ Double.parseDouble(refundbyNEFT) + Double.parseDouble(refundbyOther) + Double.parseDouble(refundbyUPI);
				double totaladvance = Double.parseDouble(advancebycash) + Double.parseDouble(advancebyCCard)
						+ Double.parseDouble(advancebyCheque) + Double.parseDouble(advancebyDCard)
						+ Double.parseDouble(advancebyNEFT) + Double.parseDouble(advancebyOther) + Double.parseDouble(advancebyUPI);

				double totalcashpayment = Double.parseDouble(paymentbycash) + Double.parseDouble(advancebycash)
						- Double.parseDouble(refundbycash);
				double totalchequepayment = Double.parseDouble(paymentbyCheque) + Double.parseDouble(advancebyCheque)
						- Double.parseDouble(refundbyCheque);
				double totalccardpayment = Double.parseDouble(paymentbyCCard) + Double.parseDouble(advancebyCCard)
						- Double.parseDouble(refundbyCCard);
				double totaldcardpayment = Double.parseDouble(paymentbyDCard) + Double.parseDouble(advancebyDCard)
						- Double.parseDouble(refundbyDCard);
				double totalneftpayment = Double.parseDouble(paymentbyNEFT) + Double.parseDouble(advancebyNEFT)
						- Double.parseDouble(refundbyNEFT);
				double totalcardpayment = totalccardpayment + totaldcardpayment;

				double totalotherpayment = Double.parseDouble(paymentbyOther) + Double.parseDouble(advancebyOther)
						- Double.parseDouble(refundbyOther);
				
				double totalupipayment = Double.parseDouble(paymentbyUPI) + Double.parseDouble(advancebyUPI)
						- Double.parseDouble(refundbyUPI);
				
				double nettotal = totalcashpayment + totalccardpayment + totalchequepayment + totaldcardpayment
						+ totalneftpayment + totalotherpayment + totalupipayment;
				tcash = tcash + totalcashpayment;
				tcard = tcard + totalcardpayment;
				tcheque = tcheque + totalchequepayment;
				tneft = tneft + totalneftpayment;
				tother = tother + totalotherpayment;
				tupi = tupi + totalupipayment;
				tnettotal = tnettotal + nettotal;

				summaryReportForm.setTcash(tcash);
				summaryReportForm.setTcard(tcard);
				summaryReportForm.setTcheque(tcheque);
				summaryReportForm.setTneft(tneft);
				summaryReportForm.setTother(tother);
				summaryReportForm.setTupi(tupi);
				summaryReportForm.setTnettotal(tnettotal);

				sr.setPayment(payment);
				sr.setAdvance(advance);
				sr.setRefund(refund);
				sr.setPaymentbycash(paymentbycash);
				sr.setPaymentbyCCard(paymentbycard);
				sr.setPaymentbyCheque(paymentbyCheque);
				sr.setPaymentbyDCard(paymentbyDCard);
				sr.setPaymentbyNEFT(paymentbyNEFT);
				sr.setPaymentbyOther(paymentbyOther);
				sr.setPaymentbyUPI(paymentbyUPI);
				
				sr.setAdvancebycash(advancebycash);
				sr.setAdvancebyCheque(advancebyCheque);
				sr.setAdvancebyCCard(advancebycard);
				sr.setAdvancebyDCard(advancebyDCard);
				sr.setAdvancebyNEFT(advancebyNEFT);
				sr.setAdvancebyOther(advancebyOther);
				sr.setAdvancebyUPI(advancebyUPI);
				
				sr.setRefundbycash(refundbycash);
				sr.setRefundbyCCard(refundbycard);
				sr.setRefundbyDCard(refundbyDCard);
				sr.setRefundbyNEFT(refundbyNEFT);
				sr.setRefundbyCheque(refundbyCheque);
				sr.setRefundbyOther(refundbyOther);
				sr.setRefundbyUPI(refundbyUPI);
				
				sr.setTotalcashpayment(totalcashpayment);
				sr.setTotalchequepayment(totalchequepayment);
				sr.setTotalccardpayment(totalcardpayment);
				sr.setTotaldcardpayment(totaldcardpayment);
				sr.setTotalneftpayment(totalneftpayment);
				sr.setTotalotherpayment(totalotherpayment);
				sr.setTotalupipayment(totalupipayment);
				
				sr.setTotaladvance(totaladvance);
				sr.setTotalrefund(totalrefund);
				sr.setTotalpayment(totalpayment);
				sr.setNettotal(nettotal);
				sr.setUsername(fullname);
				sr.setUserid(userid);
				
				Accounts report = summaryReportDAO.getInvoiceBalDebitDetails(fromdate, todate, userid);
				totaldebitamt = totaldebitamt + report.getDebitAmount();
				totalbalanceamt = totalbalanceamt+report.getBalanceTotal();
				
				sr.setDebitamttotal(DateTimeUtils.changeFormat(report.getDebitAmount()));
				sr.setBalanceamttotal(DateTimeUtils.changeFormat(report.getBalanceTotal()));
				
				userwisepaymentlist.add(sr);
				if (s == 1) {
					break;
				}
			}
			summaryReportForm.setDebitamttotal(DateTimeUtils.changeFormat(totaldebitamt));
			summaryReportForm.setBalanceamttotal(DateTimeUtils.changeFormat(totalbalanceamt));
			summaryReportForm.setPaymentreportlist(userwisepaymentreportlist);
			summaryReportForm.setUserwisepaymentlist(userwisepaymentlist);
			/*
			 * int size= userwisepaymentreportlist.size(); if(size>0){ String
			 * payment= userwisepaymentreportlist.get(size-1).getTotalamt();
			 * 
			 * summaryReportForm.setTotalReceived(Double.parseDouble(payment));
			 * } else { summaryReportForm.setTotalReceived(0.00); }
			 */

			Clinic clinic = new Clinic();
			ClinicDAO clinicDAO = new JDBCClinicDAO(connection);
			AccountsDAO accountsDAO = new JDBCAccountsDAO(connection);
			ArrayList<Clinic> locationAdressList = accountsDAO.getLocationAddress(loginInfo.getClinicid());
			clinic = clinicDAO.getCliniclistDetails(loginInfo.getClinicid());
			summaryReportForm.setClinicName(clinic.getClinicName());
			summaryReportForm.setClinicOwner(clinic.getClinicOwner());
			summaryReportForm.setOwner_qualification(clinic.getOwner_qualification());
			summaryReportForm.setLocationAdressList(locationAdressList);
			summaryReportForm.setAddress(clinic.getAddress());
			summaryReportForm.setLandLine(clinic.getLandLine());
			summaryReportForm.setClinicemail(clinic.getEmail());
			summaryReportForm.setWebsiteUrl(clinic.getWebsiteUrl());
			summaryReportForm.setClinicLogo(clinic.getUserImageFileName());

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			connection.close();
		}
		return "userwisepaymentreport";
	}

	public String currentpatients() throws Exception {
		if (!verifyLogin(request)) {
			return "login";
		}

		Connection connection = null;
		try {
			String issecondrey = request.getParameter("issecondery");
			if (issecondrey == null) {
				issecondrey = "no";
			}
			connection = Connection_provider.getconnection();
			SummaryReportDAO summaryReportDAO = new JDBCSummaryReportDAO(connection);
			String ward = summaryReportForm.getWard();
			int days = summaryReportForm.getDays();
			if (days == 0) {
				days = 0;
			}

			String location = DateTimeUtils.numberCheck((String) session.getAttribute("ipdlocation"));

			String fromDate = summaryReportForm.getFromDate();
			String toDate = summaryReportForm.getToDate();
			/*
			 * if(chargesReportForm.getOrderby().equals("")){
			 * chargesReportForm.setOrderby("commencing"); }
			 */

			int imlc = summaryReportForm.getIsmlc();
			if (toDate.equals("")) {
				DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
				Calendar cal = Calendar.getInstance();
				// cal.add(Calendar.DATE, -7);
				toDate = dateFormat.format(cal.getTime());
				summaryReportForm.setToDate(toDate);
			}

			if (fromDate.equals("")) {
				DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
				Calendar cal = Calendar.getInstance();
				cal.add(Calendar.DATE, -200);
				fromDate = dateFormat.format(cal.getTime());
				summaryReportForm.setFromDate(fromDate);
			}

			if (summaryReportForm.getOrderby().equals("")) {
				summaryReportForm.setOrderby("commencing");
			}

			if (!fromDate.equals("")) {
				String temp[] = fromDate.split("/");
				fromDate = temp[2] + "-" + temp[1] + "-" + temp[0];
			}
			if (!toDate.equals("")) {
				String temp1[] = toDate.split("/");
				toDate = temp1[2] + "-" + temp1[1] + "-" + temp1[0];
			}
			if (summaryReportForm.getPayby() == null) {
				summaryReportForm.setPayby("0");
			}
			if (ward == null) {
				ward = "0";
			}

			if (summaryReportForm.getDiaryUser() == null) {
				summaryReportForm.setDiaryUser("0");
			}
			if (summaryReportForm.getDiaryUser() == "") {

				summaryReportForm.setDiaryUser("0");
			}
			// if(summaryReportForm.getLocation()==null){
			// summaryReportForm.setLocation("0");
			// }
			//

			if (summaryReportForm.getOrderby1() == null) {
				summaryReportForm.setOrderby1("");
			}
			String orderpract = summaryReportForm.getOrderby1();

			if (summaryReportForm.getDiaryUser().equals("0")) {
				issecondrey = "no";
			}
			String paymentmode = summaryReportForm.getPaymentmode();
			if (paymentmode == null) {
				paymentmode = "";
			}
			ChargesReportDAO chargesReportDAO = new JDBCChargesReportDAO(connection);
			DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Calendar cal = Calendar.getInstance();
			String date = dateFormat.format(cal.getTime());

			int result = chargesReportDAO.saveMisReportLog("Current Patient Report", loginInfo.getUserId(), fromDate,
					toDate, date, "currentPatients");
			String diaryu = summaryReportForm.getDiaryUser();
			ArrayList<SummaryReport> currentPatientsList = summaryReportDAO.getCurrentPatientsList(
					summaryReportForm.getDiaryUser(), ward, fromDate, toDate, "no", days, orderpract,
					loginInfo.getIpd_abbr_access(), paymentmode, imlc, location);
			ArrayList<SummaryReport> Seconerylist = summaryReportDAO.getCurrentPatientsList(
					summaryReportForm.getDiaryUser(), ward, fromDate, toDate, issecondrey, days, orderpract,
					loginInfo.getIpd_abbr_access(), paymentmode, imlc, location);
			if (issecondrey.equals("yes")) {
				if (Seconerylist.size() != 0) {

					if (currentPatientsList.size() == 0) {
						currentPatientsList.addAll(Seconerylist);
					}

					for (SummaryReport s : Seconerylist) {
						if (!currentPatientsList.contains(s)) {
							currentPatientsList.add(s);
						}

					}
				}
			}

			ArrayList<SummaryReport> wardlist = summaryReportDAO.getWardlist();
			summaryReportForm.setCurrentPatientsList(currentPatientsList);
			summaryReportForm.setWardlist(wardlist);
			Clinic clinic = new Clinic();
			ClinicDAO clinicDAO = new JDBCClinicDAO(connection);
			AccountsDAO accountsDAO = new JDBCAccountsDAO(connection);
			ArrayList<Clinic> locationAdressList = accountsDAO.getLocationAddress(loginInfo.getClinicid());
			clinic = clinicDAO.getCliniclistDetails(loginInfo.getClinicid());
			summaryReportForm.setClinicName(clinic.getClinicName());
			summaryReportForm.setClinicOwner(clinic.getClinicOwner());
			summaryReportForm.setOwner_qualification(clinic.getOwner_qualification());
			summaryReportForm.setLocationAdressList(locationAdressList);
			summaryReportForm.setAddress(clinic.getAddress());
			summaryReportForm.setLandLine(clinic.getLandLine());
			summaryReportForm.setClinicemail(clinic.getEmail());
			summaryReportForm.setWebsiteUrl(clinic.getWebsiteUrl());
			summaryReportForm.setClinicLogo(clinic.getUserImageFileName());
			summaryReportForm.setIssecondery(issecondrey);

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			connection.close();
		}

		return "currentpatients";
	}

	public String deathreport() throws SQLException {

		if (!verifyLogin(request)) {
			return "login";
		}

		Connection connection = null;
		try {
			connection = Connection_provider.getconnection();
			SummaryReportDAO summaryReportDAO = new JDBCSummaryReportDAO(connection);

			String fromDate = summaryReportForm.getFromDate();
			String toDate = summaryReportForm.getToDate();

			/*
			 * if(chargesReportForm.getOrderby().equals("")){
			 * chargesReportForm.setOrderby("commencing"); }
			 * 
			 */
			if (fromDate.equals("")) {
				DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
				Calendar cal = Calendar.getInstance();
				cal.add(Calendar.DATE, -7);
				fromDate = dateFormat.format(cal.getTime());
				summaryReportForm.setFromDate(fromDate);
			}
			if (toDate.equals("")) {
				DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
				Calendar cal = Calendar.getInstance();
				// cal.add(Calendar.DATE, -7);
				toDate = dateFormat.format(cal.getTime());
				summaryReportForm.setToDate(toDate);
			}

			if (summaryReportForm.getOrderby().equals("")) {
				summaryReportForm.setOrderby("commencing");
			}

			if (!fromDate.equals("")) {
				String temp[] = fromDate.split("/");
				fromDate = temp[2] + "-" + temp[1] + "-" + temp[0];
			}
			if (!toDate.equals("")) {
				String temp1[] = toDate.split("/");
				toDate = temp1[2] + "-" + temp1[1] + "-" + temp1[0];
			}
			if (summaryReportForm.getPayby() == null) {
				summaryReportForm.setPayby("0");
			}

			if (summaryReportForm.getDiaryUser() == null) {
				summaryReportForm.setDiaryUser("0");
			}
			if (summaryReportForm.getDiaryUser() == "") {

				summaryReportForm.setDiaryUser("0");
			}
			if (summaryReportForm.getLocation() == null) {
				summaryReportForm.setLocation("0");
			}
			SummaryReport sr = new SummaryReport();
			ChargesReportDAO chargesReportDAO = new JDBCChargesReportDAO(connection);
			DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Calendar cal = Calendar.getInstance();
			String date = dateFormat.format(cal.getTime());

			int result = chargesReportDAO.saveMisReportLog("DeathReport", loginInfo.getUserId(), fromDate, toDate, date,
					"deathReport");
			String diaryu = summaryReportForm.getDiaryUser();
			ArrayList<SummaryReport> deathReportList = summaryReportDAO.getDeathReportList(fromDate, toDate,
					summaryReportForm.getPayby(), diaryu, "0", summaryReportForm.getThirdParty(), "8", "3", "1", "IPD",
					summaryReportForm.getBalancelimit());
			String name = summaryReportForm.getDiaryUser();

			summaryReportForm.setDeathReportList(deathReportList);
			int size = deathReportList.size();
			int daeth48count = 0;
			if (size > 0) {
				daeth48count = deathReportList.get(size - 1).getDeath48count();
				summaryReportForm.setDeath48count(daeth48count);
			}
			Clinic clinic = new Clinic();
			ClinicDAO clinicDAO = new JDBCClinicDAO(connection);
			AccountsDAO accountsDAO = new JDBCAccountsDAO(connection);
			ArrayList<Clinic> locationAdressList = accountsDAO.getLocationAddress(loginInfo.getClinicid());
			MisChartDAO mischartdao = new JDBCMisChartDAO(connection);
			int dischargecount = mischartdao.getDischargePatients(fromDate, toDate);
			int deathcount = mischartdao.getTotalDeaths(fromDate, toDate);

			double grossDeathRate = deathcount * 100.0 / dischargecount;

			if (Double.isNaN(grossDeathRate)) {
				grossDeathRate = 0.00;
			}
			grossDeathRate = Math.round(grossDeathRate * 100.0) / 100.0;
			/*
			 * double netdeath=Math.round(((double)daeth48count
			 * /deathcount*100)*100.0)/100.0;
			 */
			/*
			 * double netdeath=Math.round(((double)daeth48count /dischargecount
			 * - (deathcount-daeth48count)*100)*100.0)/100.0;
			 */
			double temp = dischargecount - (deathcount - daeth48count);
			double netdeath = (daeth48count / temp) * 100.0;
			if (Double.isNaN(netdeath)) {
				netdeath = 0.00;
			}
			netdeath = Math.round(netdeath * 100.0) / 100.0;
			summaryReportForm.setNetdeathrate(netdeath);
			summaryReportForm.setGrossDeathRate(grossDeathRate);
			clinic = clinicDAO.getCliniclistDetails(loginInfo.getClinicid());
			summaryReportForm.setClinicName(clinic.getClinicName());
			summaryReportForm.setClinicOwner(clinic.getClinicOwner());
			summaryReportForm.setOwner_qualification(clinic.getOwner_qualification());
			summaryReportForm.setLocationAdressList(locationAdressList);
			summaryReportForm.setAddress(clinic.getAddress());
			summaryReportForm.setLandLine(clinic.getLandLine());
			summaryReportForm.setClinicemail(clinic.getEmail());
			summaryReportForm.setWebsiteUrl(clinic.getWebsiteUrl());
			summaryReportForm.setClinicLogo(clinic.getUserImageFileName());

		} catch (Exception e) {

			e.printStackTrace();
		} finally {
			connection.close();
		}

		return "deathreport";
	}

	public String bedoccupancyreport() throws Exception {
		Connection connection = null;
		try {
			connection = Connection_provider.getconnection();
			SummaryReportDAO summaryReportDAO = new JDBCSummaryReportDAO(connection);
			String fromdate = summaryReportForm.getFromDate();
			String todate = summaryReportForm.getToDate();
			if (fromdate == null) {
				DateFormat dateFormat = new SimpleDateFormat("MM");
				Calendar cal = Calendar.getInstance();
				fromdate = dateFormat.format(cal.getTime());

			} else {
				if (fromdate.equals("")) {
					DateFormat dateFormat = new SimpleDateFormat("MM");
					Calendar cal = Calendar.getInstance();
					fromdate = dateFormat.format(cal.getTime());

				}
			}
			if (todate == null) {
				DateFormat dateFormat = new SimpleDateFormat("yyyy");
				Calendar cal = Calendar.getInstance();
				todate = dateFormat.format(cal.getTime());

			} else {
				if (todate.equals("")) {
					DateFormat dateFormat = new SimpleDateFormat("yyyy");
					Calendar cal = Calendar.getInstance();
					todate = dateFormat.format(cal.getTime());

				}
			}

			String newfromdate = fromdate;
			String newtodate = todate;

			/*
			 * YearMonth yearMonth = YearMonth.of( Integer.parseInt(newtodate) ,
			 * Integer.parseInt(newfromdate)); LocalDate last =
			 * yearMonth.atEndOfMonth(); String yearmonthh=
			 * String.valueOf(last);
			 * 
			 * String[] yeartemp =yearmonthh.split("-");
			 */

			fromdate = "01-" + newfromdate + "-" + newtodate + "";
			todate = 31 + "-" + newfromdate + "-" + newtodate + "";
			fromdate = DateTimeUtils.getCommencingDate1(fromdate);
			todate = DateTimeUtils.getCommencingDate1(todate);

			ChargesReportDAO chargesReportDAO = new JDBCChargesReportDAO(connection);
			DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Calendar cal = Calendar.getInstance();
			String date = dateFormat.format(cal.getTime());

			int result = chargesReportDAO.saveMisReportLog("Bed Occupancy Report", loginInfo.getUserId(), fromDate,
					toDate, date, "bedoccupancyreport");

			ArrayList<SummaryReport> bedoccupancylist = summaryReportDAO.getBedOccupancyList(fromdate, todate);

			ArrayList<SummaryReport> newbedoccupancylist = summaryReportDAO.CalculateBedOccupancyList(fromdate, todate,
					"31");
			summaryReportForm.setNewbedoccupancylist(newbedoccupancylist);
			summaryReportForm.setBedoccupancylist(bedoccupancylist);

			summaryReportForm.setFromDate(newfromdate);
			summaryReportForm.setToDate(newtodate);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			connection.close();
		}
		return "bedoccupancyreport";
	}

	public String ismlcreport() throws SQLException {

		if (!verifyLogin(request)) {
			return "login";
		}

		Connection connection = null;
		try {
			connection = Connection_provider.getconnection();
			SummaryReportDAO summaryReportDAO = new JDBCSummaryReportDAO(connection);

			String fromDate = summaryReportForm.getFromDate();
			String toDate = summaryReportForm.getToDate();
			/*
			 * if(chargesReportForm.getOrderby().equals("")){
			 * chargesReportForm.setOrderby("commencing"); }
			 */

			if (fromDate.equals("")) {
				DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
				Calendar cal = Calendar.getInstance();
				cal.add(Calendar.DATE, -30);
				fromDate = dateFormat.format(cal.getTime());
				summaryReportForm.setFromDate(fromDate);
			}
			if (toDate.equals("")) {
				DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
				Calendar cal = Calendar.getInstance();
				// cal.add(Calendar.DATE, -7);
				toDate = dateFormat.format(cal.getTime());
				summaryReportForm.setToDate(toDate);
			}

			if (summaryReportForm.getOrderby().equals("")) {
				summaryReportForm.setOrderby("commencing");
			}

			if (!fromDate.equals("")) {
				String temp[] = fromDate.split("/");
				fromDate = temp[2] + "-" + temp[1] + "-" + temp[0];
			}
			if (!toDate.equals("")) {
				String temp1[] = toDate.split("/");
				toDate = temp1[2] + "-" + temp1[1] + "-" + temp1[0];
			}
			if (summaryReportForm.getPayby() == null) {
				summaryReportForm.setPayby("0");
			}

			if (summaryReportForm.getDiaryUser() == null) {
				summaryReportForm.setDiaryUser("0");
			}
			if (summaryReportForm.getDiaryUser() == "") {

				summaryReportForm.setDiaryUser("0");
			}
			if (summaryReportForm.getLocation() == null) {
				summaryReportForm.setLocation("0");
			}
			SummaryReport sr = new SummaryReport();
			ChargesReportDAO chargesReportDAO = new JDBCChargesReportDAO(connection);
			DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Calendar cal = Calendar.getInstance();
			String date = dateFormat.format(cal.getTime());

			int result = chargesReportDAO.saveMisReportLog("MLCReport", loginInfo.getUserId(), fromDate, toDate, date,
					"mlcReport");
			String diaryu = summaryReportForm.getDiaryUser();
			ArrayList<SummaryReport> mlcList = summaryReportDAO.getMLCReportList(fromDate, toDate,
					summaryReportForm.getPayby(), diaryu, "0", summaryReportForm.getThirdParty(), "8", "3", "1", "IPD",
					summaryReportForm.getBalancelimit());
			String name = summaryReportForm.getDiaryUser();

			summaryReportForm.setMlclist(mlcList);
			int size = mlcList.size();
			int daeth48count = 0;
			if (size > 0) {
				daeth48count = mlcList.get(size - 1).getDeath48count();
				summaryReportForm.setDeath48count(daeth48count);
			}
			Clinic clinic = new Clinic();
			ClinicDAO clinicDAO = new JDBCClinicDAO(connection);
			AccountsDAO accountsDAO = new JDBCAccountsDAO(connection);
			ArrayList<Clinic> locationAdressList = accountsDAO.getLocationAddress(loginInfo.getClinicid());
			MisChartDAO mischartdao = new JDBCMisChartDAO(connection);
			int dischargecount = mischartdao.getDischargePatients(fromDate, toDate);
			int deathcount = mischartdao.getTotalDeaths(fromDate, toDate);

			double grossDeathRate = (double) deathcount * 100 / dischargecount;
			if (Double.isNaN(grossDeathRate)) {
				grossDeathRate = 0.00;
			}

			double netdeath = (double) daeth48count / deathcount * 100;
			if (Double.isNaN(netdeath)) {
				netdeath = 0.00;
			}
			summaryReportForm.setNetdeathrate(netdeath);
			summaryReportForm.setGrossDeathRate(grossDeathRate);
			clinic = clinicDAO.getCliniclistDetails(loginInfo.getClinicid());
			summaryReportForm.setClinicName(clinic.getClinicName());
			summaryReportForm.setClinicOwner(clinic.getClinicOwner());
			summaryReportForm.setOwner_qualification(clinic.getOwner_qualification());
			summaryReportForm.setLocationAdressList(locationAdressList);
			summaryReportForm.setAddress(clinic.getAddress());
			summaryReportForm.setLandLine(clinic.getLandLine());
			summaryReportForm.setClinicemail(clinic.getEmail());
			summaryReportForm.setWebsiteUrl(clinic.getWebsiteUrl());
			summaryReportForm.setClinicLogo(clinic.getUserImageFileName());

		} catch (Exception e) {

			e.printStackTrace();
		} finally {
			connection.close();
		}

		return "mlcreport";
	}

	public String refferedbyreport() throws Exception {
		if (!verifyLogin(request)) {
			return "login";
		}

		Connection connection = null;
		try {
			connection = Connection_provider.getconnection();
			SummaryReportDAO summaryReportDAO = new JDBCSummaryReportDAO(connection);
			String fromDate = summaryReportForm.getFromDate();
			String toDate = summaryReportForm.getToDate();
			String diaryUser = summaryReportForm.getDiaryUser();
			String opdipd = "0";
			String refferdby = summaryReportForm.getReffedby();
			String itype = summaryReportForm.getItype();
			String admissionOrdischarge=summaryReportForm.getAdmissionordischarge();
			if (fromDate == null) {
				DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
				Calendar cal = Calendar.getInstance();
				//cal.add(Calendar.DATE, -7);
				fromDate = dateFormat.format(cal.getTime());
				fromDate = DateTimeUtils.getCommencingDate1(fromDate);
			} else if (fromDate.equals("")) {
				DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
				Calendar cal = Calendar.getInstance();
				//cal.add(Calendar.DATE, -7);
				fromDate = dateFormat.format(cal.getTime());
				fromDate = DateTimeUtils.getCommencingDate1(fromDate);
			} else {
				fromDate = DateTimeUtils.getCommencingDate1(fromDate);
			}
			if (toDate == null) {
				DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
				Calendar cal = Calendar.getInstance();
				toDate = dateFormat.format(cal.getTime());
				toDate = DateTimeUtils.getCommencingDate1(toDate);
			} else if (toDate.equals("")) {
				DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
				Calendar cal = Calendar.getInstance();
				toDate = dateFormat.format(cal.getTime());
				toDate = DateTimeUtils.getCommencingDate1(toDate);
			} else {
				toDate = DateTimeUtils.getCommencingDate1(toDate);
			}

			if (summaryReportForm.getReffedby() == null) {
				refferdby = "0";
			} else if (summaryReportForm.getReffedby().equals("")) {
				refferdby = "0";
			}
			if (itype == null) {
				itype = "0";
			}
			if (admissionOrdischarge == null) {
				admissionOrdischarge = "0";
			}
			ArrayList<SummaryReport> refferedbylist;
			if (itype.equals("0")) {
				refferedbylist = summaryReportDAO.getRefferedByList(opdipd, diaryUser, refferdby, fromDate, toDate,admissionOrdischarge);
			} else {
				refferedbylist = summaryReportDAO.getRefferedByOPDList(opdipd, diaryUser, refferdby, fromDate, toDate);
			}
			
			double totalbillamt=0;
			double totalshareamt=0;
			for (SummaryReport summaryReport : refferedbylist) {
				if(summaryReport.getPayment()==null) {
					summaryReport.setPayment("0");
				}
				totalbillamt=totalbillamt+Double.parseDouble(summaryReport.getPayment());
				totalshareamt=totalshareamt+summaryReport.getShareAmt();
			}
			
			summaryReportForm.setTotalBillAmt(totalbillamt);
			summaryReportForm.setTotalShareAmt(totalshareamt);
			//summaryReportForm.setRefferedbylist(refferedbylist);
			summaryReportForm.setRefferedbylist(refferedbylist);
			ArrayList<SummaryReport> refferer = summaryReportDAO.getReferalList();
			summaryReportForm.setReferalList(refferer);
			Clinic clinic = new Clinic();
			ClinicDAO clinicDAO = new JDBCClinicDAO(connection);
			AccountsDAO accountsDAO = new JDBCAccountsDAO(connection);
			ArrayList<Clinic> locationAdressList = accountsDAO.getLocationAddress(loginInfo.getClinicid());
			clinic = clinicDAO.getCliniclistDetails(loginInfo.getClinicid());
			summaryReportForm.setClinicName(clinic.getClinicName());
			summaryReportForm.setClinicOwner(clinic.getClinicOwner());
			summaryReportForm.setOwner_qualification(clinic.getOwner_qualification());
			summaryReportForm.setLocationAdressList(locationAdressList);
			summaryReportForm.setAddress(clinic.getAddress());
			summaryReportForm.setLandLine(clinic.getLandLine());
			summaryReportForm.setClinicemail(clinic.getEmail());
			summaryReportForm.setWebsiteUrl(clinic.getWebsiteUrl());
			summaryReportForm.setClinicLogo(clinic.getUserImageFileName());
			summaryReportForm.setFromDate(DateTimeUtils.getCommencingDate1(fromDate));
			summaryReportForm.setToDate(DateTimeUtils.getCommencingDate1(toDate));
			summaryReportForm.setItype(itype);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			connection.close();
		}
		return "refferedreport";
	}

	public String showpatientsreport() throws Exception {

		if (!verifyLogin(request)) {
			return "login";
		}
		Connection connection = null;
		String fromDate = null;
		String toDate = null;
		String type = null;
		String dept = null;
		String city = null;
		try {
			connection = Connection_provider.getconnection();
			fromDate = summaryReportForm.getFromDate();
			toDate = summaryReportForm.getToDate();
			type = summaryReportForm.getType();
			dept = summaryReportForm.getDept();
			city = summaryReportForm.getCity();
			if (type == null) {
				type = "IPD";
			}
			if (type.equals("")) {
				type = "IPD";
			}
			if (dept == null) {
				dept = "";
			}
			if (fromDate.equals("")) {
				DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
				Calendar cal = Calendar.getInstance();
				cal.add(Calendar.DATE, -30);
				fromDate = dateFormat.format(cal.getTime());
				summaryReportForm.setFromDate(fromDate);
			}
			if (toDate.equals("")) {
				DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
				Calendar cal = Calendar.getInstance();
				// cal.add(Calendar.DATE, -7);
				toDate = dateFormat.format(cal.getTime());
				summaryReportForm.setToDate(toDate);
			}
			if (!fromDate.equals("")) {
				String temp[] = fromDate.split("/");
				fromDate = temp[2] + "-" + temp[1] + "-" + temp[0];
			}
			if (!toDate.equals("")) {
				String temp1[] = toDate.split("/");
				toDate = temp1[2] + "-" + temp1[1] + "-" + temp1[0];
			}
			if (dept.equals("0")) {
				dept = "";
			}
			if (city == null) {
				city = "0";
			}
			if (city.equals("")) {
				city = "0";
			}

			ArrayList<SummaryReport> list = new ArrayList<SummaryReport>();
			SummaryReportDAO summaryReportDAO = new JDBCSummaryReportDAO(connection);
			list = summaryReportDAO.getPatients(fromDate, toDate, dept, type, city);

			ArrayList<SummaryReport> deptlist = new ArrayList<SummaryReport>();
			deptlist = summaryReportDAO.getdepartments();

			ArrayList<SummaryReport> locationlist = new ArrayList<SummaryReport>();
			locationlist = summaryReportDAO.getallLocations();

			summaryReportForm.setPatientlist(list);
			summaryReportForm.setDeptlist(deptlist);
			summaryReportForm.setLocationlist(locationlist);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return "showpatientsreport";
	}

	public String opdipdconversionrevenue() throws Exception {
		Connection connection = null;
		try {
			connection = Connection_provider.getconnection();
			SummaryReportDAO summaryReportDAO = new JDBCSummaryReportDAO(connection);
			ChargesReportDAO chargesReportDAO = new JDBCChargesReportDAO(connection);
			String fromdate = summaryReportForm.getFromDate();
			String todate = summaryReportForm.getToDate();
			String itype = summaryReportForm.getItype();
			if (fromdate == null) {
				DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
				Calendar cal = Calendar.getInstance();
				fromdate = dateFormat.format(cal.getTime());
				fromdate = DateTimeUtils.getCommencingDate1(fromdate);
			} else {
				if (fromdate.equals("")) {
					DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
					Calendar cal = Calendar.getInstance();
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

			DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Calendar cal = Calendar.getInstance();
			String date = dateFormat.format(cal.getTime());

			int result = chargesReportDAO.saveMisReportLog("opd ipd conversion revenue Report", loginInfo.getUserId(),
					fromdate, todate, date, "opdipdconversionrevenue");
			String netipddebitcount = summaryReportDAO.getNetDepartmentWiseDebit(fromdate, todate, "0", 0);
			ArrayList<Accounts> opdipdconversionrevenuelist = summaryReportDAO.getOpdIpdConversionRevenue(fromdate,
					todate, netipddebitcount);
			summaryReportForm.setOpdipdconversionrevenuelist(opdipdconversionrevenuelist);
			summaryReportForm.setFromDate(DateTimeUtils.getCommencingDate1(fromdate));
			summaryReportForm.setToDate(DateTimeUtils.getCommencingDate1(todate));
			summaryReportForm.setNetipddebitcount(netipddebitcount);
			int size = opdipdconversionrevenuelist.size();
			if (size > 0) {
				int count = opdipdconversionrevenuelist.get(size - 1).getCount();
				String totaldebitAmount = opdipdconversionrevenuelist.get(size - 1).getTotaldebitAmount();
				summaryReportForm.setCount(count);

				double temp = 0;
				if (Double.parseDouble(totaldebitAmount) > 0) {
					temp = (Double.parseDouble(totaldebitAmount) / Double.parseDouble(netipddebitcount)) * 100;
					temp = Math.round(temp * 100.0) / 100.0;

				}
				summaryReportForm.setTotaldebitAmount(String.valueOf(totaldebitAmount));
				summaryReportForm.setTotaldebitAmountPer(String.valueOf(temp));

			} else {
				summaryReportForm.setCount(0);
				summaryReportForm.setTotaldebitAmount("0");
				summaryReportForm.setTotaldebitAmountPer("0");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			connection.close();
		}
		return "opdipdconversionrevenue";
	}

	public String deptwiseavgcount() {

		if (!verifyLogin(request)) {
			return "login";
		}
		Connection connection = null;
		String month = "";
		String year = "";
		String fromdate = "";
		String todate = "";
		String fromDate = summaryReportForm.getFromDate();
		String toDate = summaryReportForm.getToDate();
		try {
            
			
			if(fromDate.equals("")){
				DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
				Calendar cal = Calendar.getInstance();
				//cal.add(Calendar.DATE, -7); 
				fromDate = dateFormat.format(cal.getTime());
				summaryReportForm.setFromDate(fromDate);
			}
			if(toDate.equals("")){
				DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
				Calendar cal = Calendar.getInstance();
				//cal.add(Calendar.DATE, -7); 
				toDate = dateFormat.format(cal.getTime());
				summaryReportForm.setToDate(toDate);
			}
			
			if(!fromDate.equals("")){
				String temp[]= fromDate.split("/");
				fromDate = temp[2]+"-"+temp[1]+"-"+temp[0];
			}
			if(!toDate.equals("")){
				String temp1[]= toDate.split("/");
				toDate = temp1[2]+"-"+temp1[1]+"-"+temp1[0];
			}
			
			year = summaryReportForm.getYear();
			month = summaryReportForm.getMonth();
			if (year != null && month != null) {
				fromdate = "01-" + month + "-" + year;
				todate = "31-" + month + "-" + year;
			} else {
				DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
				Calendar cal = Calendar.getInstance();
				String currdate = dateFormat.format(cal.getTime());
				month = currdate.split("-")[1];
				year = currdate.split("-")[2];
				fromdate = "01-" + month + "-" + year;
				todate = "31-" + month + "-" + year;
			}
			// for getting current month

			/*
			 * DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
			 * Calendar cal = Calendar.getInstance(); String currdate =
			 * dateFormat.format(cal.getTime());
			 * 
			 * String currdate1[]= currdate.split("-"); String
			 * currmonth=currdate1[1]; String fromdate1[]= fromdate.split("-");
			 * String fromdatemonth= fromdate1[1]; String curryear=
			 * currdate1[2]; String fromdateyear= fromdate1[2]; double diff=
			 * (Integer.parseInt(curryear)-Integer.parseInt(fromdateyear))*12+
			 * Integer.parseInt(currmonth)-Integer.parseInt(fromdatemonth);
			 * 
			 * month= summaryReportForm.getMonth();
			 */

			connection = Connection_provider.getconnection();
			SummaryReportDAO summaryReportDAO = new JDBCSummaryReportDAO(connection);
			ArrayList<SummaryReport> deptlist = new ArrayList<SummaryReport>();
			ArrayList<SummaryReport> avg_list = new ArrayList<SummaryReport>();
			deptlist = summaryReportDAO.getdepartments();
			Double totalot = 0.0, totalopd = 0.0, totalipd = 0.0;
			for (SummaryReport sr : deptlist) {
				String deptname = "";
				String deptid = "";
				deptname = sr.getDiscipline_name();
				deptid = sr.getDiscipline_id();
				SummaryReport smopd = new SummaryReport();
				SummaryReport smipd = new SummaryReport();
				SummaryReport smot = new SummaryReport();
				SummaryReport sm = new SummaryReport();
				SummaryReport newcount=new SummaryReport();
				SummaryReport oldcount=new SummaryReport();
				if(loginInfo.isLmh()) {
					
					smopd = summaryReportDAO.getdeptcountlistopd(deptid, fromDate, toDate,loginInfo);
					newcount=summaryReportDAO.getnewPatientcount(deptid, fromDate, toDate);
					oldcount=summaryReportDAO.getoldPatientcount(deptid, fromDate, toDate);
				}else {
				 todate = DateTimeUtils.getCommencingDate1(todate);
				 todate=todate+" 23:59:59";
				 fromdate= DateTimeUtils.getCommencingDate1(fromdate);
				smopd = summaryReportDAO.getdeptcountlistopd(deptid, fromdate, todate,loginInfo);
				}
				smipd = summaryReportDAO.getdeptcountlistipd(deptid, fromdate, todate);
				smot = summaryReportDAO.getdeptcountlistot(deptid, fromdate, todate);
				double avgopd, avgipd, avgot;

				/*
				 * if(fromdateyear.equals(curryear)){ avgopd=
				 * (double)(Double.parseDouble(smopd.getCount())/diff);
				 * avgipd=(double) (Double.parseDouble(smipd.getCount())/diff);
				 * avgot=(double)(Double.parseDouble(smot.getCount())/diff); }
				 * else{ avgopd=
				 * (double)(Double.parseDouble(smopd.getCount())/12);
				 * avgipd=(double) (Double.parseDouble(smipd.getCount())/12);
				 * avgot= (double)(Double.parseDouble(smot.getCount())/12);
				 * 
				 * } avgopd= Double.parseDouble(new
				 * DecimalFormat("##.##").format(avgopd)); avgipd=
				 * Double.parseDouble(new
				 * DecimalFormat("##.##").format(avgipd)); avgot=
				 * Double.parseDouble(new DecimalFormat("##.##").format(avgot));
				 */
				sm.setDepartment(deptname);
				sm.setAvgipd(Double.parseDouble(smipd.getCount()));
				sm.setAvgopd(Double.parseDouble(smopd.getCount()));
				sm.setAvgot(Double.parseDouble(smot.getCount()));
				sm.setOtlist(smot.getOtlist());
				sm.setDiagnosis(smipd.getDiagnosis());
				if(loginInfo.isLmh()) {
				sm.setNewcount(Double.parseDouble(newcount.getCount()));
				sm.setOldcount(Double.parseDouble(oldcount.getCount()));
				}
				avg_list.add(sm);
				totalipd = totalipd + sm.getAvgipd();
				totalopd = totalopd + sm.getAvgopd();
				totalot = totalot + sm.getAvgot();
			}
			if (year == null && month == null) {
				summaryReportForm.setYear("2018");
				summaryReportForm.setMonth("01");
			}

			summaryReportForm.setTotalipd(totalipd);
			summaryReportForm.setTotalopd(totalopd);
			summaryReportForm.setTotalot(totalot);
			summaryReportForm.setYear(year);
			summaryReportForm.setMonth(month);
			summaryReportForm.setAvg_list(avg_list);
			Clinic clinic = new Clinic();
			ClinicDAO clinicDAO = new JDBCClinicDAO(connection);
			clinic = clinicDAO.getCliniclistDetails(loginInfo.getClinicid());
			summaryReportForm.setClinicName(clinic.getClinicName());
			summaryReportForm.setClinicOwner(clinic.getClinicOwner());
			summaryReportForm.setOwner_qualification(clinic.getOwner_qualification());

			summaryReportForm.setAddress(clinic.getAddress());
			summaryReportForm.setLandLine(clinic.getLandLine());
			summaryReportForm.setClinicemail(clinic.getEmail());
			summaryReportForm.setWebsiteUrl(clinic.getWebsiteUrl());
			summaryReportForm.setClinicLogo(clinic.getUserImageFileName());

		} catch (Exception e) {
			e.printStackTrace();
		}
        if(loginInfo.isLmh()) {
        	return "newcount";
        }else {
		    return "deptwiseavgcount";
        }
	}

	public String Labreport() {
		Connection connection = null;
		try {
			String fromDate = "", toDate = "", Testname = "", abovebelow = "", value = "";

			fromDate = summaryReportForm.getFromDate();
			toDate = summaryReportForm.getToDate();
			Testname = summaryReportForm.getTestname();
			abovebelow = summaryReportForm.getAbovebelow();
			value = summaryReportForm.getObsvalue();

			if (fromDate == null) {
				DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
				Calendar cal = Calendar.getInstance();
				fromDate = dateFormat.format(cal.getTime());

			} else {
				if (fromDate.equals("")) {
					DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
					Calendar cal = Calendar.getInstance();
					fromDate = dateFormat.format(cal.getTime());

				} else {

				}
			}
			if (toDate == null) {
				DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
				Calendar cal = Calendar.getInstance();
				toDate = dateFormat.format(cal.getTime());

			} else {
				if (toDate.equals("")) {
					DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
					Calendar cal = Calendar.getInstance();
					toDate = dateFormat.format(cal.getTime());

				}
			}

			if (Testname == null) {
				Testname = "";
			}
			if (abovebelow == null) {
				abovebelow = "";
			}
			if (value == null) {
				value = "";
			}

			connection = Connection_provider.getconnection();
			Investigation investigation = new Investigation();
			ArrayList<Investigation> list = new ArrayList<Investigation>();
			SummaryReportDAO summaryReportDAO = new JDBCSummaryReportDAO(connection);
			list = summaryReportDAO.getLabreport(fromDate, toDate, Testname, abovebelow, value);
			summaryReportForm.setInvestigationlist(list);
			ArrayList<Master> inestigationnamelist = new ArrayList<Master>();
			MasterDAO masterDAO = new JDBCMasterDAO(connection);
			inestigationnamelist = masterDAO.getInvestigationNameList1();
			summaryReportForm.setInestigationnamelist(inestigationnamelist);
			summaryReportForm.setFromDate(fromDate);
			summaryReportForm.setToDate(toDate);
			summaryReportForm.setTestname(Testname);
			summaryReportForm.setObsvalue(value);
			summaryReportForm.setAbovebelow(abovebelow);

			// lettrehead

			Clinic clinic = new Clinic();
			ClinicDAO clinicDAO = new JDBCClinicDAO(connection);
			clinic = clinicDAO.getCliniclistDetails(loginInfo.getClinicid());
			summaryReportForm.setClinicName(clinic.getClinicName());
			summaryReportForm.setClinicOwner(clinic.getClinicOwner());
			summaryReportForm.setOwner_qualification(clinic.getOwner_qualification());

			summaryReportForm.setAddress(clinic.getAddress());
			summaryReportForm.setLandLine(clinic.getLandLine());
			summaryReportForm.setClinicemail(clinic.getEmail());
			summaryReportForm.setWebsiteUrl(clinic.getWebsiteUrl());
			summaryReportForm.setClinicLogo(clinic.getUserImageFileName());

		} catch (Exception e) {
			e.printStackTrace();
		}

		return "Labreport";
	}

	public String investigationCount() {
		Connection connection = null;
		try {

			String fromDate = "", toDate = "", Testname = "", abovebelow = "", value = "";

			fromDate = summaryReportForm.getFromDate();
			toDate = summaryReportForm.getToDate();
			Testname = summaryReportForm.getTestname();

			if (fromDate == null) {
				DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
				Calendar cal = Calendar.getInstance();
				fromDate = dateFormat.format(cal.getTime());

			} else {
				if (fromDate.equals("")) {
					DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
					Calendar cal = Calendar.getInstance();
					fromDate = dateFormat.format(cal.getTime());

				} else {

				}
			}
			if (toDate == null) {
				DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
				Calendar cal = Calendar.getInstance();
				toDate = dateFormat.format(cal.getTime());

			} else {
				if (toDate.equals("")) {
					DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
					Calendar cal = Calendar.getInstance();
					toDate = dateFormat.format(cal.getTime());

				}
			}

			if (Testname == null) {
				Testname = "";
			}

			// testname is department name
			// investigationlist is departmentlist
			connection = Connection_provider.getconnection();
			Investigation investigation = new Investigation();
			ArrayList<Investigation> list = new ArrayList<Investigation>();
			SummaryReportDAO summaryReportDAO = new JDBCSummaryReportDAO(connection);
			list = summaryReportDAO.getNewInvestigationReport(fromDate, toDate, Testname);
			summaryReportForm.setInvestigationlist(list);
			ArrayList<Master> inestigationnamelist = new ArrayList<Master>();
			InvestigationMasterDAO investigationmasterDAO = new JDBCInvestigationMasterDAO(connection);

			inestigationnamelist = investigationmasterDAO.getAllSectionList();
			summaryReportForm.setInestigationnamelist(inestigationnamelist);
			summaryReportForm.setFromDate(fromDate);
			summaryReportForm.setToDate(toDate);
			summaryReportForm.setTestname(Testname);

			// lettrehead

			Clinic clinic = new Clinic();
			ClinicDAO clinicDAO = new JDBCClinicDAO(connection);
			clinic = clinicDAO.getCliniclistDetails(loginInfo.getClinicid());
			summaryReportForm.setClinicName(clinic.getClinicName());
			summaryReportForm.setClinicOwner(clinic.getClinicOwner());
			summaryReportForm.setOwner_qualification(clinic.getOwner_qualification());

			summaryReportForm.setAddress(clinic.getAddress());
			summaryReportForm.setLandLine(clinic.getLandLine());
			summaryReportForm.setClinicemail(clinic.getEmail());
			summaryReportForm.setWebsiteUrl(clinic.getWebsiteUrl());
			summaryReportForm.setClinicLogo(clinic.getUserImageFileName());
		} catch (Exception e) {
			e.printStackTrace();
		}

		return "investigationCount";
	}

	public String newIpdCurrentPatient() {
		Connection connection = null;
		try {

			String fromDate = summaryReportForm.getFromDate();
			String toDate = summaryReportForm.getToDate();

			if (fromDate == null) {
				DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
				Calendar cal = Calendar.getInstance();
				fromDate = dateFormat.format(cal.getTime());

			} else {
				if (fromDate.equals("")) {
					DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
					Calendar cal = Calendar.getInstance();
					fromDate = dateFormat.format(cal.getTime());

				} else {

				}
			}
			if (toDate == null) {
				DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
				Calendar cal = Calendar.getInstance();
				toDate = dateFormat.format(cal.getTime());

			} else {
				if (toDate.equals("")) {
					DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
					Calendar cal = Calendar.getInstance();
					toDate = dateFormat.format(cal.getTime());

				}
			}
			if (summaryReportForm.getAdm() == null) {
				summaryReportForm.setAdm("all");
			}
			String x = summaryReportForm.getAdm();

			String practitioner = "";
			practitioner = summaryReportForm.getDiaryUser();
			connection = Connection_provider.getconnection();
			SummaryReportDAO summaryReportDAO = new JDBCSummaryReportDAO(connection);
			ArrayList<SummaryReport> list = new ArrayList<SummaryReport>();
			list = summaryReportDAO.getnewIpdCurrentPatientlist(fromDate, toDate, practitioner, x);
			summaryReportForm.setCurrentPatientsList(list);
			summaryReportForm.setFromDate(fromDate);
			summaryReportForm.setToDate(toDate);
			summaryReportForm.setDiaryUser(practitioner);

			// lettrehead

			Clinic clinic = new Clinic();
			ClinicDAO clinicDAO = new JDBCClinicDAO(connection);
			clinic = clinicDAO.getCliniclistDetails(loginInfo.getClinicid());
			summaryReportForm.setClinicName(clinic.getClinicName());
			summaryReportForm.setClinicOwner(clinic.getClinicOwner());
			summaryReportForm.setOwner_qualification(clinic.getOwner_qualification());

			summaryReportForm.setAddress(clinic.getAddress());
			summaryReportForm.setLandLine(clinic.getLandLine());
			summaryReportForm.setClinicemail(clinic.getEmail());
			summaryReportForm.setWebsiteUrl(clinic.getWebsiteUrl());
			summaryReportForm.setClinicLogo(clinic.getUserImageFileName());

		} catch (Exception e) {
			e.printStackTrace();

		}

		return "newIpdCurrentPatient";
	}

	public String newchargesharereport() throws Exception {
		Connection connection = null;
		try {

			connection = Connection_provider.getconnection();
			UserProfileDAO profileDAO = new JDBCUserProfileDAO(connection);
			SummaryReportDAO summaryReportDAO = new JDBCSummaryReportDAO(connection);
			ChargesReportDAO chargesReportDAO = new JDBCChargesReportDAO(connection);
			ClientDAO clientDAO = new JDBCClientDAO(connection);
			String fromdate = summaryReportForm.getFromDate();
			String todate = summaryReportForm.getToDate();
			String practitionerId = summaryReportForm.getPractitionerId();
			String clientid = summaryReportForm.getClientId();
			if (fromdate == null) {
				DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
				Calendar cal = Calendar.getInstance();
				fromdate = dateFormat.format(cal.getTime());
				fromdate = DateTimeUtils.getCommencingDate1(fromdate);
			} else {
				if (fromdate.equals("")) {
					DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
					Calendar cal = Calendar.getInstance();
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
			if (practitionerId != null) {
				if (practitionerId.equals("")) {
					practitionerId = null;
				} else if (practitionerId.equals("0")) {
					practitionerId = null;
				}
			}
			if (clientid != null) {
				if (clientid.equals("")) {
					clientid = null;
				} else if (clientid.equals("0")) {
					clientid = null;
				}
			}

			DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Calendar cal = Calendar.getInstance();
			String date = dateFormat.format(cal.getTime());

			ArrayList<Accounts> chargesharelist = summaryReportDAO.getShareChargeList(fromdate, todate, practitionerId,
					clientid);
			summaryReportForm.setChargesharelist(chargesharelist);
			/*
			 * ArrayList<Master> doctorlist = profileDAO.getDoctorList();
			 * summaryReportForm.setDoctorlist(doctorlist); ArrayList<Client>
			 * clientlist = clientDAO.getAllPatient();
			 * summaryReportForm.setClientlist(clientlist);
			 */
			summaryReportForm.setFromDate(DateTimeUtils.getCommencingDate1(fromdate));
			summaryReportForm.setToDate(DateTimeUtils.getCommencingDate1(todate));
			summaryReportForm.setClientId(clientid);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			connection.close();
		}
		return "newchargesharereport";
	}

	public String invstpackagerpt() {
		Connection connection = null;
		try {
			String fromdate = summaryReportForm.getFromDate();
			String todate = summaryReportForm.getToDate();
			String practitionerId = summaryReportForm.getPractitionerId();
			String pkgid = summaryReportForm.getPkgid();
			if (fromdate == null) {
				DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
				Calendar cal = Calendar.getInstance();
				fromdate = dateFormat.format(cal.getTime());

			} else {
				if (fromdate.equals("")) {
					DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
					Calendar cal = Calendar.getInstance();
					fromdate = dateFormat.format(cal.getTime());

				}
			}
			if (todate == null) {
				DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
				Calendar cal = Calendar.getInstance();
				todate = dateFormat.format(cal.getTime());

			} else {
				if (todate.equals("")) {
					DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
					Calendar cal = Calendar.getInstance();
					todate = dateFormat.format(cal.getTime());

				}
			}
			if (practitionerId == null) {
				practitionerId = "";
			}
			if (pkgid == null) {
				pkgid = "";
			}
			connection = Connection_provider.getconnection();
			ArrayList<Investigation> reportlist = new ArrayList<Investigation>();
			SummaryReportDAO summaryReportDAO = new JDBCSummaryReportDAO(connection);
			reportlist = summaryReportDAO.getinvstPkgList(fromdate, todate, practitionerId, pkgid);
			ArrayList<PackageMaster> masterlist = new ArrayList<PackageMaster>();
			PackageMasterDAO packageMasterDAO = new JDBCPackageMasterDAO(connection);
			masterlist = packageMasterDAO.getAllPackage(null, null);
			summaryReportForm.setInvestigationlist(reportlist);
			summaryReportForm.setPkgmasterlist(masterlist);
			summaryReportForm.setFromDate(fromdate);
			summaryReportForm.setToDate(todate);
			summaryReportForm.setPkgid(pkgid);
			summaryReportForm.setPractitionerId(practitionerId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "invstpackagerpt";
	}

	public String newstockreport1() {
		Connection connection = null;
		try {
			String fromdate = summaryReportForm.getFromDate();
			String todate = summaryReportForm.getToDate();
			String practitionerId = summaryReportForm.getPractitionerId();
			String pkgid = summaryReportForm.getPkgid();
			if (fromdate == null) {
				DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
				Calendar cal = Calendar.getInstance();
				fromdate = dateFormat.format(cal.getTime());

			} else {
				if (fromdate.equals("")) {
					DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
					Calendar cal = Calendar.getInstance();
					fromdate = dateFormat.format(cal.getTime());

				}
			}
			if (todate == null) {
				DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
				Calendar cal = Calendar.getInstance();
				todate = dateFormat.format(cal.getTime());

			} else {
				if (todate.equals("")) {
					DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
					Calendar cal = Calendar.getInstance();
					todate = dateFormat.format(cal.getTime());

				}
			}

			connection = Connection_provider.getconnection();

			ArrayList<Product> stocklist = new ArrayList<Product>();
			SummaryReportDAO summaryReportDAO = new JDBCSummaryReportDAO(connection);
			stocklist = summaryReportDAO.getallstockreportlist(fromdate, todate);
			summaryReportForm.setProductlist(stocklist);
			Product product = stocklist.get(stocklist.size() - 1);
			double total = (Math.round(product.getTotalAmt() * 100.0) / 100.0);
			summaryReportForm.setTotalamt(total);
			summaryReportForm.setFromDate(fromdate);
			summaryReportForm.setToDate(todate);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return "newstockreport1";
	}

	public String practshareopdnew() {
		String fromDate, toDate;
		fromDate = summaryReportForm.getFromDate();
		toDate = summaryReportForm.getToDate();
		if (fromDate == null) {
			fromDate = "";
		}
		if (toDate == null) {
			toDate = "";
		}
		if (fromDate.equals("")) {
			DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
			Calendar cal = Calendar.getInstance();
			fromDate = dateFormat.format(cal.getTime());
		}
		if (toDate.equals("")) {
			DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
			Calendar cal = Calendar.getInstance();
			toDate = dateFormat.format(cal.getTime());
		}

		Connection connection = null;
		try {
			connection = Connection_provider.getconnection();
			SummaryReportDAO summaryReportDAO = new JDBCSummaryReportDAO(connection);
			ArrayList<SummaryReport> list = new ArrayList<SummaryReport>();
			list = summaryReportDAO.getpractshareopdnew(fromDate, toDate);
			summaryReportForm.setReportlist(list);
			summaryReportForm.setFromDate(fromDate);
			summaryReportForm.setToDate(toDate);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return "practshareopdnew";
	}

	public String wardwiseavgstay() {
		String fromDate, toDate;
		fromDate = summaryReportForm.getFromDate();
		toDate = summaryReportForm.getToDate();
		if (fromDate == null) {
			fromDate = "";
		}
		if (toDate == null) {
			toDate = "";
		}
		if (fromDate.equals("")) {
			DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
			Calendar cal = Calendar.getInstance();
			fromDate = dateFormat.format(cal.getTime());
		}
		if (toDate.equals("")) {
			DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
			Calendar cal = Calendar.getInstance();
			toDate = dateFormat.format(cal.getTime());
		}
		Connection connection = null;
		try {
			connection = Connection_provider.getconnection();
			ArrayList<SummaryReport> list = new ArrayList<SummaryReport>();
			SummaryReportDAO summaryReportDAO = new JDBCSummaryReportDAO(connection);
			list = summaryReportDAO.getwardwiseavgstayReport(fromDate, toDate);
			summaryReportForm.setReportlist(list);
			summaryReportForm.setFromDate(fromDate);
			summaryReportForm.setToDate(toDate);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "wardwiseavgstay";
	}

	public String totalrevenue() {

		String fromdate = summaryReportForm.getFromDate();
		String todate = summaryReportForm.getToDate();

		String refresh = request.getParameter("refresh");
		String yearly = request.getParameter("yearly");
		if (refresh == null) {
			refresh = "";
		}
		if (yearly == null) {
			yearly = "";
		}
		if (fromdate == null) {
			DateFormat dateFormat = new SimpleDateFormat("MM");
			Calendar cal = Calendar.getInstance();
			fromdate = dateFormat.format(cal.getTime());

		} else {
			if (fromdate.equals("")) {
				DateFormat dateFormat = new SimpleDateFormat("MM");
				Calendar cal = Calendar.getInstance();
				fromdate = dateFormat.format(cal.getTime());

			} /*
				 * else { fromdate = DateTimeUtils.getCommencingDate1(fromdate);
				 * }
				 */
		}
		if (todate == null) {
			DateFormat dateFormat = new SimpleDateFormat("yyyy");
			Calendar cal = Calendar.getInstance();
			todate = dateFormat.format(cal.getTime());

		} else {
			if (todate.equals("")) {
				DateFormat dateFormat = new SimpleDateFormat("yyyy");
				Calendar cal = Calendar.getInstance();
				todate = dateFormat.format(cal.getTime());

			} /*
				 * else { todate = DateTimeUtils.getCommencingDate1(todate); }
				 */
		}

		String newfromdate = fromdate;
		String newtodate = todate;
		fromdate = "01-" + newfromdate + "-" + newtodate + "";
		todate = "31-" + newfromdate + "-" + newtodate + "";
		fromdate = DateTimeUtils.getCommencingDate1(fromdate);
		todate = DateTimeUtils.getCommencingDate1(todate);
		String isnelson = DateTimeUtils.isNull(request.getParameter("isnelson"));
		Connection connection = null;
		try {

			connection = Connection_provider.getconnection();

			SummaryReportDAO summaryReportDAO = new JDBCSummaryReportDAO(connection);
			MisChartDAO misChartDAO = new JDBCMisChartDAO(connection);
			if (refresh.equals("1")) {
				String ipddebitcount = summaryReportDAO.getDepartmentWiseDebit(fromdate, todate, "2");
				String opddebitcount = summaryReportDAO.getDepartmentWiseDebit(fromdate, todate, "1");
				String investigationdebitcount = summaryReportDAO.getDepartmentWiseDebit(fromdate, todate, "3");
				String medicinedebitcount = summaryReportDAO.getDepartmentWiseDebit(fromdate, todate, "0");
				String netipddebitcount = summaryReportDAO.getNetDepartmentWiseDebit(fromdate, todate, "2", 1);
				String netopddebitcount = summaryReportDAO.getNetDepartmentWiseDebit(fromdate, todate, "1", 1);
				String netinvestigationdebitcount = summaryReportDAO.getNetDepartmentWiseDebit(fromdate, todate, "3",
						1);
				String netmedicinedebitcount = summaryReportDAO.getNetDepartmentWiseDebit(fromdate, todate, "0", 1);

				int totalopdseen = misChartDAO.getBookedAppointment(fromdate, todate);
				int ipdnewadmission = misChartDAO.getIpdNewAdmission(fromdate, todate);

				Ipd ipd = summaryReportDAO.getBedOccupancyrate(fromdate, todate);

				double averagestay = 0;
				if (ipdnewadmission > 0) {
					averagestay = ipd.getTotalpatient() / ipdnewadmission;
				}

				summaryReportForm.setTotalbed(ipd.getTotalbed());
				summaryReportForm.setTotalpatient(ipd.getTotalpatient());
				summaryReportForm.setTotalbedoccupancy(ipd.getTotalbedoccupancy());

				summaryReportForm.setTotalbed(ipd.getTotalbed());
				summaryReportForm.setTotalpatient(ipd.getTotalpatient());
				summaryReportForm.setTotalbedoccupancy(ipd.getTotalbedoccupancy());
				summaryReportForm.setAveragestay(Math.round(averagestay * 100.0) / 100.0);
				summaryReportForm.setTotalopdseen(totalopdseen);
				summaryReportForm.setIpdnewadmission(ipdnewadmission);

				summaryReportForm.setIpddebitcount(String.valueOf(ipddebitcount));
				summaryReportForm.setOpddebitcount(String.valueOf(opddebitcount));
				summaryReportForm.setInvestigationdebitcount(String.valueOf(investigationdebitcount));

				summaryReportForm.setMedicinedebitcount(medicinedebitcount);
				summaryReportForm.setNetmedicinedebitcount(netmedicinedebitcount);

				summaryReportForm.setNetipddebitcount(netipddebitcount);
				summaryReportForm.setNetopddebitcount(netopddebitcount);
				summaryReportForm.setNetinvestigationdebitcount(netinvestigationdebitcount);

				summaryReportForm.setFromDate(fromdate);
				summaryReportForm.setToDate(todate);

				// pharmacy
				PharmacyDAO pharmacyDAO = new JDBCPharmacyDAO(connection);

				double totalCredit = pharmacyDAO.getTotalCredit("0", fromdate, todate);
				double totalCollection = pharmacyDAO.getTotalCollectionToday("0", fromdate, todate);
				double todaycash = pharmacyDAO.getCashToday("0", fromdate, todate);
				double todaycard = pharmacyDAO.getCardToday("0", fromdate, todate);
				double todaydisc = pharmacyDAO.getTodayDisc("0", fromdate, todate);
				double chequepayment = pharmacyDAO.getChequePayment("0", fromdate, todate);
				double neftpayment = pharmacyDAO.getNeftPayment("0", fromdate, todate);
				double cashReturn = pharmacyDAO.getReturnToday("0", fromdate, todate);
				double creditReturn = pharmacyDAO.getCreditReturn("0", fromdate, todate);

				if (Double.isNaN(totalCredit)) {
					totalCredit = 0.0;
				}

				summaryReportForm.setTotalcredit(Math.ceil(totalCredit));
				summaryReportForm.setCreditReturn(Math.ceil(creditReturn));
				summaryReportForm.setTodaycard(Math.ceil(todaycard));
				summaryReportForm.setTodaycash(Math.ceil(todaycash));
				summaryReportForm.setChequepayments(Math.ceil(chequepayment));
				summaryReportForm.setNeftpayment(Math.ceil(neftpayment));
				summaryReportForm.setTodayreturn(Math.ceil(cashReturn));
				summaryReportForm.setTodaydisc(Math.ceil(todaydisc));
				summaryReportForm.setTotalpayment(Math.ceil(totalCollection));

				int delete = summaryReportDAO.deleteRevenueData(fromdate);
				int save = summaryReportDAO.insertRevenueData(summaryReportForm);
			} else if (yearly.equals("1")) {
				summaryReportForm.setYearly("1");
				String date1[] = fromdate.split("-");
				int year1 = Integer.parseInt(date1[0]);
				String year = String.valueOf(year1 + 1);
				String newfromdate1 = date1[0] + "-04-01";
				String newtodate1 = year + "-03-01";
				ArrayList<SummaryReportForm> listreport = summaryReportDAO.getTotalRevenueList(newfromdate1,
						newtodate1);
				summaryReportForm.setTotalrevenuelist(listreport);

				MisReport misReport = misChartDAO.getTotolRevenueForGraph(newfromdate1, newtodate1);
				session.setAttribute("graphicalopdrevenuereportt", misReport);
			}
			SummaryReportForm summaryReportForm2 = summaryReportDAO.getTotaldataOfRevenueReport(fromdate, todate);
			summaryReportForm.setTotalbedoccupancy(summaryReportForm2.getTotalbedoccupancy());
			summaryReportForm.setAveragestay(summaryReportForm2.getAveragestay());
			summaryReportForm.setTotalopdseen(summaryReportForm2.getTotalopdseen());
			summaryReportForm.setIpdnewadmission(summaryReportForm2.getIpdnewadmission());
			summaryReportForm.setIpddebitcount(summaryReportForm2.getIpddebitcount());
			summaryReportForm.setOpddebitcount(summaryReportForm2.getOpddebitcount());
			summaryReportForm.setInvestigationdebitcount(summaryReportForm2.getInvestigationdebitcount());
			summaryReportForm.setMedicinedebitcount(summaryReportForm2.getMedicinedebitcount());
			summaryReportForm.setNetipddebitcount(summaryReportForm2.getNetipddebitcount());
			summaryReportForm.setNetopddebitcount(summaryReportForm2.getNetopddebitcount());
			summaryReportForm.setNetinvestigationdebitcount(summaryReportForm2.getNetinvestigationdebitcount());
			summaryReportForm.setNetmedicinedebitcount(summaryReportForm2.getNetmedicinedebitcount());

			summaryReportForm.setTotalcredit(summaryReportForm2.getTotalcredit());
			summaryReportForm.setCreditReturn(summaryReportForm2.getCreditReturn());
			summaryReportForm.setTodaycard(summaryReportForm2.getTodaycard());
			summaryReportForm.setTodaycash(summaryReportForm2.getTodaycash());
			summaryReportForm.setChequepayments(summaryReportForm2.getChequepayments());
			summaryReportForm.setNeftpayment(summaryReportForm2.getNeftpayment());
			summaryReportForm.setTodayreturn(summaryReportForm2.getTodayreturn());
			summaryReportForm.setTodaydisc(summaryReportForm2.getTodaydisc());
			summaryReportForm.setTotalpayment(summaryReportForm2.getTotalpayment());

			String month = "";
			if (newfromdate.equals("01")) {
				month = "January";
			} else if (newfromdate.equals("02")) {
				month = "February";
			} else if (newfromdate.equals("03")) {
				month = "March";
			} else if (newfromdate.equals("04")) {
				month = "April";
			} else if (newfromdate.equals("05")) {
				month = "May";
			} else if (newfromdate.equals("06")) {
				month = "June";
			} else if (newfromdate.equals("07")) {
				month = "July";
			} else if (newfromdate.equals("08")) {
				month = "August";
			} else if (newfromdate.equals("09")) {
				month = "September";
			} else if (newfromdate.equals("10")) {
				month = "October";
			} else if (newfromdate.equals("11")) {
				month = "November";
			} else if (newfromdate.equals("12")) {
				month = "December";
			}
			summaryReportForm.setMonth(month);
			summaryReportForm.setFromDate(newfromdate);
			summaryReportForm.setToDate(newtodate);

			if (isnelson.equals("1")) {
				return "nelsontotalrevenue";
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return "totalrevenue";
	}

	public String investigationRevenue() {
		return "";
	}

	public String ipdpatienttpreport() {
		String fromdate, todate;
		try {
			Connection connection = Connection_provider.getconnection();
			SummaryReportDAO summaryReportDAO = new JDBCSummaryReportDAO(connection);
			ArrayList<SummaryReport> reportList = new ArrayList<SummaryReport>();
			reportList = summaryReportDAO.getIPDreportTPWisePatients();
			summaryReportForm.setReportlist(reportList);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "ipdpatienttpreport";
	}

	public String tppackagereport() {
		try {
			String frommdate = "", todate = "";
			frommdate = summaryReportForm.getFromDate();
			todate = summaryReportForm.getToDate();
			if (frommdate == null || todate == null) {
				frommdate = "";
				todate = "";
			}
			if (frommdate.equals("") || todate.equals("")) {
				DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
				Calendar cal = Calendar.getInstance();
				frommdate = dateFormat.format(cal.getTime());
				todate = frommdate;
			}
			Connection connection = Connection_provider.getconnection();
			SummaryReportDAO summaryReportDAO = new JDBCSummaryReportDAO(connection);
			ArrayList<SummaryReport> reportlist = new ArrayList<SummaryReport>();
			reportlist = summaryReportDAO.getPackageReportList(frommdate, todate);
			summaryReportForm.setReportlist(reportlist);
			summaryReportForm.setFromDate(frommdate);
			summaryReportForm.setToDate(todate);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "tppackagereport";
	}

	public String ipdopdrevenueinvstrevnue() {
		try {
			String frommdate = "", todate = "", type = "",itype="",search="";
			frommdate = summaryReportForm.getFromDate();
			todate = summaryReportForm.getToDate();
			if (frommdate == null || todate == null) {
				frommdate = "";
				todate = "";
			}
			if (frommdate.equals("") || todate.equals("")) {
				DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
				Calendar cal = Calendar.getInstance();
				frommdate = dateFormat.format(cal.getTime());
				todate = frommdate;
			}
			type = summaryReportForm.getType();
			if (type == null) {
				type = "";
			}
			itype=summaryReportForm.getItype();
			if(itype==null){
				itype="";
			}
			search=summaryReportForm.getSearch();
			if(search==null){
				search="";
			}
			Connection connection = Connection_provider.getconnection();
			SummaryReportDAO summaryReportDAO = new JDBCSummaryReportDAO(connection);
			ArrayList<SummaryReport> reportList = summaryReportDAO.getIPDOPDInvestigationRevenue(
					DateTimeUtils.getCommencingDate1(frommdate), DateTimeUtils.getCommencingDate1(todate), type, "",itype,search);
			summaryReportForm.setReportlist(reportList);
			summaryReportForm.setFromDate(frommdate);
			summaryReportForm.setToDate(todate);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "ipdopdrevenueinvstrevnue";
	}

	public String paymentreciptreport() {
		try {
			Connection connection = Connection_provider.getconnection();
			SummaryReportDAO summaryReportDAO = new JDBCSummaryReportDAO(connection);
			AccountsDAO accountsDAO = new JDBCAccountsDAO(connection);
			String fromdate = summaryReportForm.getFromDate();
			String todate = summaryReportForm.getToDate();
			String howpaid = summaryReportForm.getHowpaid();
			String itype = summaryReportForm.getItype();
			String paymentStatus = summaryReportForm.getPaymentStatus();
			String amountgreaterfilter = summaryReportForm.getAmountgreaterfilter();
			howpaid = DateTimeUtils.numberCheck(howpaid);
			itype = DateTimeUtils.numberCheck(itype);
			paymentStatus = DateTimeUtils.numberCheck(paymentStatus);
			amountgreaterfilter = DateTimeUtils.numberCheck(amountgreaterfilter);
			if (fromdate == null) {
				DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
				Calendar cal = Calendar.getInstance();
				fromdate = dateFormat.format(cal.getTime());
			} else {
				if (fromdate.equals("")) {
					DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
					Calendar cal = Calendar.getInstance();
					fromdate = dateFormat.format(cal.getTime());
				}
			}
			if (todate == null) {
				DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
				Calendar cal = Calendar.getInstance();
				todate = dateFormat.format(cal.getTime());
			} else {
				if (todate.equals("")) {
					DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
					Calendar cal = Calendar.getInstance();
					todate = dateFormat.format(cal.getTime());
				}
			}
			fromdate = DateTimeUtils.getCommencingDate1(fromdate);
			todate = DateTimeUtils.getCommencingDate1(todate);
			ArrayList<Accounts> invoiceList = new ArrayList<Accounts>();
			invoiceList = summaryReportDAO.getPaymentReciptReportList(fromdate, todate, howpaid, itype, paymentStatus,
					amountgreaterfilter);
			double totalbalance=0;
			double totalinvoiceamt=0;
			double totalpaidamt=0;
			double totaldiscamt=0;
			double cashtotal=0;
			double upitotal=0;
			double totaladvanceAmt = 0;
		
			for (Accounts accounts : invoiceList) {
				totalbalance=totalbalance+accounts.getBalance();
				totalpaidamt=totalpaidamt+accounts.getCreditAmount();
				totalinvoiceamt=totalinvoiceamt+accounts.getDebitAmount();
				//totaldiscamt=totaldiscamt+Double.parseDouble(accounts.getDiscamt());
			}
			ArrayList<Accounts>paymentlist=summaryReportDAO.getPaymentModelist(fromdate, todate, howpaid, itype, paymentStatus,
					amountgreaterfilter);
			
			for (Accounts accounts : paymentlist) {
				if(accounts.getPaymentmode().equals("Cash")){
					cashtotal = cashtotal + accounts.getAmount();
				}
				if(accounts.getPaymentmode().equals("UPI")){
					upitotal = upitotal + accounts.getAmount();
				}
			}
			
			ArrayList<Accounts>discountlist=summaryReportDAO.getDiscountlist(fromdate, todate, howpaid, itype, paymentStatus,
					amountgreaterfilter);
			
			for (Accounts accounts : discountlist) {
				totaldiscamt=totaldiscamt+Double.parseDouble(accounts.getDiscamt());
			}
			
			ArrayList<Accounts>advanceList=summaryReportDAO.getAdvancelist(fromdate, todate, howpaid, itype, paymentStatus,
					amountgreaterfilter);
			
			for (Accounts accounts : advanceList) {
				if(accounts.getPaymentmode().equals("Cash")){
					cashtotal = cashtotal + accounts.getAmount();
				}
				if(accounts.getPaymentmode().equals("UPI")){
					upitotal = upitotal + accounts.getAmount();
				}
			}
			for(Accounts accounts : advanceList){
				totaladvanceAmt = totaladvanceAmt+accounts.getAmount();
			}
			
			double refOnly = summaryReportDAO.getRefundOnly(fromdate,todate,4,howpaid, itype, paymentStatus,amountgreaterfilter);
			double totalAmt = cashtotal+upitotal;
			summaryReportForm.setCalcdebittotal(totalAmt-refOnly);
			summaryReportForm.setTotalamt(totalAmt);
			summaryReportForm.setBalanceamttotal(Double.toString(totalbalance));
			summaryReportForm.setTotalPaidAmt(totalpaidamt);
			summaryReportForm.setInvstamount(Double.toString(totalinvoiceamt));
			summaryReportForm.setTotaldiscountammount(totaldiscamt);
			summaryReportForm.setTotalCash(cashtotal);
			summaryReportForm.setTotalUpi(upitotal);
			summaryReportForm.setAdvanceAmt(totaladvanceAmt);
			summaryReportForm.setTotalrefundamount(refOnly);
			
			summaryReportForm.setInvoiceList(invoiceList);
			fromdate = DateTimeUtils.getCommencingDate1(fromdate);
			todate = DateTimeUtils.getCommencingDate1(todate);
			summaryReportForm.setFromDate(fromdate);
			summaryReportForm.setToDate(todate);

			ArrayList<Master> invoicetypelist = accountsDAO.getInvoiceTypeList();
			summaryReportForm.setInvoicetypelist(invoicetypelist);
			summaryReportForm.setHowpaid(howpaid);
			summaryReportForm.setItype(itype);
			summaryReportForm.setPaymentStatus(paymentStatus);
			summaryReportForm.setAmountgreaterfilter(amountgreaterfilter);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "paymentreciptreport";
	}

	public String wardwiserevenuerept() {
		try {
			/*
			 * String month = DateTimeUtils.isNull(summaryReportForm.getFromDate()); String
			 * year = DateTimeUtils.isNull(summaryReportForm.getToDate());
			 */
			String fromdate = summaryReportForm.getFromDate();
			String todate = summaryReportForm.getToDate();
			if (fromdate == null) {
				DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
				Calendar cal = Calendar.getInstance();
				fromdate = dateFormat.format(cal.getTime());
			} else {
				if (fromdate.equals("")) {
					DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
					Calendar cal = Calendar.getInstance();
					fromdate = dateFormat.format(cal.getTime());
				}
			}
			if (todate == null) {
				DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
				Calendar cal = Calendar.getInstance();
				todate = dateFormat.format(cal.getTime());
			} else {
				if (todate.equals("")) {
					DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
					Calendar cal = Calendar.getInstance();
					todate = dateFormat.format(cal.getTime());
				}
			}
			fromdate = DateTimeUtils.getCommencingDate1(fromdate);
			todate = DateTimeUtils.getCommencingDate1(todate);
			/*
			 * if (month.equals("")) { month = "08"; year = "2019"; } String fromdate =
			 * "01-" + month + "-" + year; String todate = "31-" + month + "-" + year;
			 *///COMMENTING BECAUUSE WE NEED DATE WISE REPORT NOT MONTH WISE
			Connection connection = Connection_provider.getconnection();
			SummaryReportDAO summaryReportDAO = new JDBCSummaryReportDAO(connection);
			ArrayList<SummaryReport> List = summaryReportDAO.getwardwiserevenueReport(fromdate, todate);
			summaryReportForm.setReportlist(List);
			fromdate = DateTimeUtils.getCommencingDate1(fromdate);
			todate = DateTimeUtils.getCommencingDate1(todate);
			summaryReportForm.setFromDate(fromdate);
			summaryReportForm.setToDate(todate);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "wardwiserevenuerept";
	}

	public String deptwise_ot() {
		try {
			String month = DateTimeUtils.isNull(summaryReportForm.getFromDate());
			String year = DateTimeUtils.isNull(summaryReportForm.getToDate());
			if (month.equals("")) {
				month = "08";
				year = "2019";
			}
			String fromdate = "01-" + month + "-" + year;
			String todate = "31-" + month + "-" + year;
			Connection connection = Connection_provider.getconnection();
			SummaryReportDAO summaryReportDAO = new JDBCSummaryReportDAO(connection);
			ArrayList<SummaryReport> List = summaryReportDAO.getdeptwiseOtReport(fromdate, todate);
			summaryReportForm.setReportlist(List);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "deptwise_ot";
	}

	public String invstabsvalreport() {
		try {
			Connection connection = Connection_provider.getconnection();
			ClientDAO clientDAO = new JDBCClientDAO(connection);
			EmrDAO emrDAO = new JDBCEmrDAO(connection);
			String fromdate = DateTimeUtils.isNull(summaryReportForm.getFromDate());
			String todate = DateTimeUtils.isNull(summaryReportForm.getToDate());
			if (fromdate.equals("")) {
				DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
				Calendar cal = Calendar.getInstance();
				fromdate = dateFormat.format(cal.getTime());
				todate = dateFormat.format(cal.getTime());
			}
			String clientId = DateTimeUtils.isNull(summaryReportForm.getClientId());
			String isIpdOpd = DateTimeUtils.isNull(summaryReportForm.getIpdopd());
			String investigationTypeId = DateTimeUtils.isNull(summaryReportForm.getType());
			ArrayList<Investigation> list = new ArrayList<Investigation>();
			InvestigationDAO investigationDAO = new JDBCInvestigationDAO(connection);
			
				Investigation investigation = new Investigation();
				investigation.setFromdate(fromdate);
				investigation.setTodate(todate);
				investigation.setInvsttypeid(investigationTypeId);
				investigation.setDepartment(isIpdOpd);
				investigation.setClientId(clientId);
				list = investigationDAO.investigationCriticalValueReport(investigation);
			
			summaryReportForm.setInvestigationlist(list);

			ArrayList<Client> clientlist = clientDAO.getAllPatient();
			summaryReportForm.setClientlist(clientlist);

			ArrayList<Master> invsttypeList = emrDAO.getInvesigationTypeList();
			summaryReportForm.setInestigationnamelist(invsttypeList);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return "invstabsvalreport";
	}
public String vistingconreport(){
	try {
		String fromdate = DateTimeUtils.isNull(summaryReportForm.getFromDate());
		String todate = DateTimeUtils.isNull(summaryReportForm.getToDate());
		String userId= (summaryReportForm.getDiaryUser());
		if(userId==null||userId.equals("")){
			userId=null;
		}
		if (fromdate.equals("")) {
			DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
			Calendar cal = Calendar.getInstance();
			fromdate = dateFormat.format(cal.getTime());
			todate = dateFormat.format(cal.getTime());
		}
		summaryReportForm.setFromDate(fromdate);
		summaryReportForm.setToDate(todate);
		Connection connection = Connection_provider.getconnection();
		IpdDAO ipdDAO = new JDBCIpdDAO(connection);
		UserProfileDAO userProfileDAO=new JDBCUserProfileDAO(connection);
		 ArrayList<Bed> visitingConsultList=ipdDAO.getVisitingConsultList(null,null,DateTimeUtils.getCommencingDate1(fromdate),DateTimeUtils.getCommencingDate1(todate),userId,null);
		 summaryReportForm.setVisitingConList(visitingConsultList);
		 ArrayList<UserProfile> visitingConsultDoctors = userProfileDAO.getVisitingPractitinerList(); 
		 summaryReportForm.setUserConList(visitingConsultDoctors);
		 
	} catch (Exception e) {
		e.printStackTrace();
	}
	return "vistingconreport";
}


public String pcsgstreport(){
	String fromdate = DateTimeUtils.isNull(summaryReportForm.getFromDate());
	String todate = DateTimeUtils.isNull(summaryReportForm.getToDate());
	String clientId= DateTimeUtils.isNull(summaryReportForm.getClientId());
	
	if (fromdate.equals("")) {
		DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
		Calendar cal = Calendar.getInstance();
		fromdate = dateFormat.format(cal.getTime());
		todate = dateFormat.format(cal.getTime());
	}
	summaryReportForm.setFromDate(fromdate);
	summaryReportForm.setToDate(todate);
	
	try {
		Connection connection=Connection_provider.getconnection();
		SummaryReportDAO summaryReportDAO= new JDBCSummaryReportDAO(connection);
		ClientDAO clientDAO= new JDBCClientDAO(connection);
		summaryReportForm.setBilingreportlist(summaryReportDAO.pcsGSTReport(fromdate, todate, clientId));
		ArrayList<Client> clientlist = clientDAO.getAllPatient();
		summaryReportForm.setClientlist(clientlist);
		
		if(summaryReportForm.getBilingreportlist().size()>0){
			summaryReportForm.setT1(summaryReportForm.getBilingreportlist().get(summaryReportForm.getBilingreportlist().size()-1).getTaxname1());
			summaryReportForm.setT2(summaryReportForm.getBilingreportlist().get(summaryReportForm.getBilingreportlist().size()-1).getTaxname2());
			summaryReportForm.setT3(summaryReportForm.getBilingreportlist().get(summaryReportForm.getBilingreportlist().size()-1).getTaxname3());
		}
		
	} catch (Exception e) {
		e.printStackTrace();
	}
	
	
	return "pcsgstreport";
}

public String birthplacereport(){
	try {
		Connection connection= Connection_provider.getconnection();
		SummaryReportDAO summaryReportDAO= new JDBCSummaryReportDAO(connection);
		String searchText=DateTimeUtils.isNull(summaryReportForm.getSearch());
		String order = DateTimeUtils.numberCheck(summaryReportForm.getOrderby());
		String fromDate = DateTimeUtils.isNull(summaryReportForm.getFromDate());
		String toDate = DateTimeUtils.isNull(summaryReportForm.getToDate());
		if(order.equals("1")){
			if(fromDate.equals("")){
				DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
				Calendar cal = Calendar.getInstance();
				fromDate = dateFormat.format(cal.getTime());
				toDate = dateFormat.format(cal.getTime());
			}
			fromDate = DateTimeUtils.getCommencingDate1(fromDate);
			toDate = DateTimeUtils.getCommencingDate1(toDate);
		}else{
			if(fromDate.equals("")){
				DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
				Calendar cal = Calendar.getInstance();
				fromDate = dateFormat.format(cal.getTime());
				toDate = dateFormat.format(cal.getTime());
			}else{
				String[] date1= fromDate.split("-");
				fromDate = date1[2]+"/"+date1[1]+"/"+date1[0]; 
				String[] date2= toDate.split("-");
				toDate = date2[2]+"/"+date2[1]+"/"+date2[0]; 
			}
		}
		Client client= new Client();
		client.setSearchText(searchText);
		int count=summaryReportDAO.getBirthPlaceListDataCount(client,fromDate,toDate,order);
		
		ArrayList<SummaryReport> wardlist = summaryReportDAO.getWardlist();
		summaryReportForm.setWardlist(wardlist);
		pagination.setPreperties(count);
		summaryReportForm.setClientlist(summaryReportDAO.getBirthPlaceListData(pagination, client,fromDate,toDate,order));
		pagination.setTotal_records(summaryReportForm.getClientlist().size());
		summaryReportForm.setTotalComplete(count);
		
		if(order.equals("1")){
			fromDate = DateTimeUtils.getCommencingDate1(fromDate);
			toDate = DateTimeUtils.getCommencingDate1(toDate);
			summaryReportForm.setFromDate(fromDate);
			summaryReportForm.setToDate(toDate);
		}else{
			String[] date1= fromDate.split("/");
			fromDate = date1[2]+"-"+date1[1]+"-"+date1[0]; 
			String[] date2= toDate.split("/");
			toDate = date2[2]+"-"+date2[1]+"-"+date2[0]; 
			summaryReportForm.setFromDate(fromDate);
			summaryReportForm.setToDate(toDate);
		}
		summaryReportForm.setOrderby(order);
	} catch (Exception e) {
		e.printStackTrace();
	}
	return "birthplacereport";
}

public String DepartmentWiseDiscountReport() throws Exception{
	
	
	Connection connection = null;
	try {
		//   16 feb 2018 Prepare Billing Report
		connection = Connection_provider.getconnection();
		SummaryReportDAO summaryReportDAO = new JDBCSummaryReportDAO(connection);
		ChargesReportDAO chargesReportDAO = new JDBCChargesReportDAO(connection);
		FinderDAO finderDAO = new JDBCFinderDAO(connection);
		String invTpe = DateTimeUtils.isNull(summaryReportForm.getItype());
		String fromdate = summaryReportForm.getFromDate();
		String todate = summaryReportForm.getToDate();
		String patientcategory=DateTimeUtils.isNull(summaryReportForm.getPatientcategory());
		String dept= DateTimeUtils.isNull(summaryReportForm.getChargeType());
		String searchtext= DateTimeUtils.isNull(summaryReportForm.getSearch());
		if (fromdate == null) {
			DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
			Calendar cal = Calendar.getInstance();
			fromdate = dateFormat.format(cal.getTime());
			fromdate = DateTimeUtils.getCommencingDate1(fromdate);
		} else {
			if (fromdate.equals("")) {
				DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
				Calendar cal = Calendar.getInstance();
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
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Calendar cal = Calendar.getInstance();
		String date = dateFormat.format(cal.getTime());

		
		ArrayList<Accounts> discountreportlist = summaryReportDAO.getDiscountReport(fromdate, todate, invTpe);
		ArrayList<Accounts>invoicelist=summaryReportDAO.getinvoicelist(fromdate, todate,
				invTpe,patientcategory,dept,searchtext);
		/*
		 * ArrayList<Accounts> departmentwisediscountchargereportlist =
		 * summaryReportDAO.getDepartmentWiseDiscounchargetReport(fromdate, todate,
		 * invTpe,patientcategory,dept,searchtext);
		 */
		double totaldiscount=0;
		double totalreceved=0;
		double billdiscount=0;
		double chargediscount=0;
		for (Accounts accounts : invoicelist) {
			/*
			 * for (Accounts accounts2 : accounts.getDiscountchargereportlist()) {
			 * totaldiscount=totaldiscount+Double.parseDouble(accounts2.getChargedisc())+
			 * accounts.getBilldiscamount();
			 * totalreceved=totalreceved+Double.parseDouble(accounts2.getDiscAmmount()); }
			 */
			 billdiscount=billdiscount+accounts.getBilldiscamount();
			 chargediscount=chargediscount+Double.parseDouble(accounts.getChargedisc());
			 totaldiscount=totaldiscount+Double.parseDouble(accounts.getChargedisc())+accounts.getBilldiscamount();
			 totalreceved=totalreceved+Double.parseDouble(accounts.getDiscAmmount());
			 
		}
		summaryReportForm.setChargedisc(chargediscount);
		summaryReportForm.setBilldisc(billdiscount);
		summaryReportForm.setTotaldiscountammount(totaldiscount);
		summaryReportForm.setTotalReceived(totalreceved);
		summaryReportForm.setInvoiceList(invoicelist);
		
		AccountsDAO accountsDAO = new JDBCAccountsDAO(connection);
		ArrayList<Master> invoicetypelist = accountsDAO.getInvoiceTypeList();
		summaryReportForm.setInvoicetypelist(invoicetypelist);
		ArrayList<NotAvailableSlot> tpNameList = finderDAO.gettpNameListAll();
		summaryReportForm.setTpNameList(tpNameList);
		ArrayList<Client> chargeTypeList  = chargesReportDAO.getChargeTypeList();
		summaryReportForm.setChargeTypeList(chargeTypeList);
		summaryReportForm.setDiscountreportlist(discountreportlist);
		summaryReportForm.setFromDate(DateTimeUtils.getCommencingDate1(fromdate));
		summaryReportForm.setToDate(DateTimeUtils.getCommencingDate1(todate));

	} catch (Exception e) {
		e.printStackTrace();
	} finally {
		connection.close();
	}
	return "departmentwisediscountreport";
	
	}

public String investigationdetailrevenue() {
	try {
		String frommdate = "", todate = "", type = "",itype="",search="";
		frommdate = summaryReportForm.getFromDate();
		todate = summaryReportForm.getToDate();
		if (frommdate == null || todate == null) {
			frommdate = "";
			todate = "";
		}
		if (frommdate.equals("") || todate.equals("")) {
			DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
			Calendar cal = Calendar.getInstance();
			frommdate = dateFormat.format(cal.getTime());
			todate = frommdate;
		}
		type = summaryReportForm.getType();
		if (type == null) {
			type = "";
		}
		itype=summaryReportForm.getItype();
		if(itype==null){
			itype="";
		}
		search=summaryReportForm.getSearch();
		if(search==null){
			search="";
		}
		Connection connection = Connection_provider.getconnection();
		SummaryReportDAO summaryReportDAO = new JDBCSummaryReportDAO(connection);
		ChargesReportDAO chargesReportDAO = new JDBCChargesReportDAO(connection);
		ArrayList<Accounts> InvDetailedRevenueReportList = chargesReportDAO.getInvestigationDetailedRevenueRpt(
				DateTimeUtils.getCommencingDate1(frommdate), DateTimeUtils.getCommencingDate1(todate), type, "",itype,search,loginInfo);
		summaryReportForm.setInvDetailedrevenue(InvDetailedRevenueReportList);
		if(InvDetailedRevenueReportList.size()!=0){
		  summaryReportForm.setTotalamt(InvDetailedRevenueReportList.get(InvDetailedRevenueReportList.size()-1).getFinalTotal());
		}
		summaryReportForm.setFromDate(frommdate);
		summaryReportForm.setToDate(todate);
	} catch (Exception e) {
		e.printStackTrace();
	}
	return "investigationdetailrevenue";
}


}
