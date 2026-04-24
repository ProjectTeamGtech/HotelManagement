package com.apm.Report.web.action;

import java.io.File;
import java.io.FileOutputStream;
import java.sql.Connection;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.allcolor.yahp.converter.CYaHPConverter;
import org.allcolor.yahp.converter.IHtmlToPdfTransformer;
import org.apache.struts2.ServletActionContext;

import com.apm.Accounts.eu.bi.AccountsDAO;
import com.apm.Accounts.eu.blogic.jdbc.JDBCAccountsDAO;
import com.apm.Accounts.eu.entity.Accounts;
import com.apm.DiaryManagement.eu.bi.NotAvailableSlotDAO;
import com.apm.DiaryManagement.eu.blogic.jdbc.JDBCNotAvailableSlotDAO;
import com.apm.DiaryManagement.eu.entity.DiaryManagement;
import com.apm.DiaryManagement.web.action.EmbeddedImageEmailUtil;
import com.apm.Log.eu.entity.EmailLetterLog;
import com.apm.Report.eu.bi.ChargesReportDAO;
import com.apm.Report.eu.bi.CommissionDAO;
import com.apm.Report.eu.blogic.jdbc.JDBCChargesReportDAO;
import com.apm.Report.eu.blogic.jdbc.JDBCCommissionDAO;
import com.apm.Report.eu.entity.Commission;
import com.apm.Report.web.form.CommissionForm;
import com.apm.common.eu.blogic.jdbc.Connection_provider;
import com.apm.common.utils.DateTimeUtils;
import com.apm.common.utils.Pagination;
import com.apm.common.web.action.BaseAction;
import com.apm.common.web.common.helper.LoginHelper;
import com.apm.common.web.common.helper.LoginInfo;
import com.apm.main.common.constants.Constants;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.Preparable;

public class CommissionAction extends BaseAction implements Preparable, ModelDriven<CommissionForm> {

	HttpServletRequest request = (HttpServletRequest) ActionContext.getContext().get(ServletActionContext.HTTP_REQUEST);
	HttpServletResponse response = (HttpServletResponse) ActionContext.getContext()
			.get(ServletActionContext.HTTP_RESPONSE);
	HttpSession session = request.getSession(true);
	LoginInfo loginInfo = LoginHelper.getLoginInfo(request);
	CommissionForm commissionForm = new CommissionForm();

	int practitionerID = loginInfo.getId();

	public String execute() throws Exception {
		if (!verifyLogin(request)) {
			return "login";
		}

		/*
		 * Connection connection = null; try{ connection =
		 * Connection_provider.getconnection(); CommissionDAO commissionDAO = new
		 * JDBCCommissionDAO(connection); ArrayList<Commission> practitionerList =
		 * commissionDAO.getPractitionerList();
		 * commissionForm.setPractitionerList(practitionerList);
		 * 
		 * 
		 * }catch(Exception e){ e.printStackTrace(); }
		 */

		return SUCCESS;
	}

	public String PractitionerList() throws Exception {
		if (!verifyLogin(request)) {
			return "login";
		}

		String fromDate = commissionForm.getFromDate();
		String toDate = commissionForm.getToDate();

		if (fromDate.equals("")) {
			DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
			Calendar cal = Calendar.getInstance();
			cal.add(Calendar.DATE, -5);
			String checkDate = dateFormat.format(cal.getTime());
			fromDate = checkDate;
			commissionForm.setFromDate(fromDate);
		}

		if (toDate.equals("")) {
			DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
			Calendar cal = Calendar.getInstance();
			String checkDate = dateFormat.format(cal.getTime());
			toDate = checkDate;
			commissionForm.setToDate(toDate);
		}

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
			CommissionDAO commissionDAO = new JDBCCommissionDAO(connection);
			ChargesReportDAO chargesReportDAO = new JDBCChargesReportDAO(connection);
			// Adarsh

			String action = request.getParameter("action");
			commissionForm.setAction(action);
			/*                                                 this code commnted for practioner share
			 * if(action.equals("0")){ DateFormat dateFormat = new
			 * SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); Calendar cal =
			 * Calendar.getInstance(); String date = dateFormat.format(cal.getTime());
			 * 
			 * int result =
			 * chargesReportDAO.saveMisReportLog("Practitioners Share Report",loginInfo.
			 * getUserId(),fromDate,toDate,date,"execute"); ArrayList<Commission>
			 * practitionerList =
			 * commissionDAO.getPractitionerList(fromDate,toDate,commissionForm.getDiaryUser
			 * (),commissionForm.getLocation()); //ArrayList<Commission> practitionerList =
			 * new ArrayList<Commission>();
			 * commissionForm.setPractitionerList(practitionerList);
			 * 
			 * 
			 * ArrayList<Commission> practitionerList =
			 * commissionDAO.getPractitionerShareList(fromDate,toDate,commissionForm.
			 * getDiaryUser(),commissionForm.getLocation());
			 * ArrayList<Commission>vsitingchargeList =
			 * commissionDAO.getVisitingChargeList(fromDate,toDate,commissionForm.
			 * getDiaryUser(),commissionForm.getLocation());
			 * ArrayList<Commission>ipcconsultingList =
			 * commissionDAO.getIpdConsultingList(fromDate,toDate,commissionForm.
			 * getDiaryUser(),commissionForm.getLocation());
			 * ArrayList<Commission>surgeonChargeList =
			 * commissionDAO.getSurgeonChargeList(fromDate,toDate,commissionForm.
			 * getDiaryUser(),commissionForm.getLocation());
			 * 
			 * 
			 * commissionForm.setPractitionerList(practitionerList);
			 * 
			 * commissionForm.setVsitingchargeList(vsitingchargeList);
			 * commissionForm.setIpcconsultingList(ipcconsultingList);
			 * commissionForm.setSurgeonChargeList(surgeonChargeList); return
			 * "prctcmsnrept"; }else{ DateFormat dateFormat = new
			 * SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); Calendar cal =
			 * Calendar.getInstance(); String date = dateFormat.format(cal.getTime());
			 * 
			 * int result =
			 * chargesReportDAO.saveMisReportLog("Practitioners Share Report",loginInfo.
			 * getUserId(),fromDate,toDate,date,"execute"); ArrayList<Commission>
			 * practitionerList =
			 * commissionDAO.getPractitionerList(fromDate,toDate,commissionForm.getDiaryUser
			 * (),commissionForm.getLocation()); //ArrayList<Commission> practitionerList =
			 * new ArrayList<Commission>();
			 * commissionForm.setPractitionerList(practitionerList);
			 * 
			 * return "opdpractshare" ; }
			 */   //end commneted code for seperate method opd commisiion share and practitioneroner share
			
			if(action.equals("0")){
				DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			      Calendar cal = Calendar.getInstance();
			      String date = dateFormat.format(cal.getTime());  
				
				int result = chargesReportDAO.saveMisReportLog("Practitioners Share Report",loginInfo.getUserId(),fromDate,toDate,date,"execute");
		/*	ArrayList<Commission> practitionerList = commissionDAO.getPractitionerList(fromDate,toDate,commissionForm.getDiaryUser(),commissionForm.getLocation());
				//ArrayList<Commission> practitionerList = new ArrayList<Commission>();
				commissionForm.setPractitionerList(practitionerList);
				
				*/
				ArrayList<Commission> practitionerList = commissionDAO.getPractitionerShareList(fromDate,toDate,commissionForm.getDiaryUser(),commissionForm.getLocation());
				ArrayList<Commission>vsitingchargeList = commissionDAO.getVisitingChargeList(fromDate,toDate,commissionForm.getDiaryUser(),commissionForm.getLocation());
				ArrayList<Commission>ipcconsultingList = commissionDAO.getIpdConsultingList(fromDate,toDate,commissionForm.getDiaryUser(),commissionForm.getLocation());
				ArrayList<Commission>surgeonChargeList = commissionDAO.getSurgeonChargeList(fromDate,toDate,commissionForm.getDiaryUser(),commissionForm.getLocation());
				
				
				commissionForm.setPractitionerList(practitionerList);
				
				commissionForm.setVsitingchargeList(vsitingchargeList);
				commissionForm.setIpcconsultingList(ipcconsultingList);
				commissionForm.setSurgeonChargeList(surgeonChargeList);
				return "prctcmsnrept";
			}

			/*
			 * DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); Calendar
			 * cal = Calendar.getInstance(); String date = dateFormat.format(cal.getTime());
			 * 
			 * int result =
			 * chargesReportDAO.saveMisReportLog("Practitioners Share Report",loginInfo.
			 * getUserId(),fromDate,toDate,date,"execute"); ArrayList<Commission>
			 * practitionerList =
			 * commissionDAO.getPractitionerList(fromDate,toDate,commissionForm.getDiaryUser
			 * (),commissionForm.getLocation()); //ArrayList<Commission> practitionerList =
			 * new ArrayList<Commission>();
			 * commissionForm.setPractitionerList(practitionerList);
			 * 
			 * 
			 * ArrayList<Commission>vsitingchargeList =
			 * commissionDAO.getVisitingChargeList(fromDate,toDate,commissionForm.
			 * getDiaryUser(),commissionForm.getLocation());
			 * ArrayList<Commission>ipcconsultingList =
			 * commissionDAO.getIpdConsultingList(fromDate,toDate,commissionForm.
			 * getDiaryUser(),commissionForm.getLocation());
			 * 
			 * ArrayList<Commission>surgeonChargeList =
			 * commissionDAO.getSurgeonChargeList(fromDate,toDate,commissionForm.
			 * getDiaryUser(),commissionForm.getLocation());
			 * 
			 * commissionForm.setVsitingchargeList(vsitingchargeList);
			 * commissionForm.setIpcconsultingList(ipcconsultingList);
			 * commissionForm.setSurgeonChargeList(surgeonChargeList);
			 */

			// select surgeon, sum(psurcharge),location from apm_available_slot where
			// psurcharge!=0 group by surgeon
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			connection.close();
		}
		return SUCCESS;
	}

	public String PractitionerListOpd() throws Exception {
		if (!verifyLogin(request)) {
			return "login";
		}

		String fromDate = commissionForm.getFromDate();
		String toDate = commissionForm.getToDate();

		if (fromDate.equals("")) {
			DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
			Calendar cal = Calendar.getInstance();
			cal.add(Calendar.DATE, -5);
			String checkDate = dateFormat.format(cal.getTime());
			fromDate = checkDate;
			commissionForm.setFromDate(fromDate);
		}

		if (toDate.equals("")) {
			DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
			Calendar cal = Calendar.getInstance();
			String checkDate = dateFormat.format(cal.getTime());
			toDate = checkDate;
			commissionForm.setToDate(toDate);
		}

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
			CommissionDAO commissionDAO = new JDBCCommissionDAO(connection);
			ChargesReportDAO chargesReportDAO = new JDBCChargesReportDAO(connection);
			// Adarsh

			String action = request.getParameter("action");
			commissionForm.setAction(action);

			DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Calendar cal = Calendar.getInstance();
			String date = dateFormat.format(cal.getTime());

			int result = chargesReportDAO.saveMisReportLog("Practitioners Share Report", loginInfo.getUserId(),
					fromDate, toDate, date, "execute");
			ArrayList<Commission> practitionerList = commissionDAO.getPractitionerList(fromDate, toDate,
					commissionForm.getDiaryUser(), commissionForm.getLocation());
			// ArrayList<Commission> practitionerList = new ArrayList<Commission>();
			commissionForm.setPractitionerList(practitionerList);

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			connection.close();
		}
		return "opdpractshare";
	}

	public String showClientDetails() throws Exception {

		if (!verifyLogin(request)) {
			return "login";
		}

		int practitionerId = Integer.parseInt(request.getParameter("practitionerId"));

		Connection connection = null;

		try {

			connection = Connection_provider.getconnection();
			CommissionDAO commissionDAO = new JDBCCommissionDAO(connection);
			ArrayList<Commission> clientCommissionList = commissionDAO.getClientCommissionList(practitionerId);

			StringBuffer str = new StringBuffer();
			str.append("<table class='table-bordered table-condensed width-full modal-content ' aria-hidden='true' > ");

			str.append("<thead>");
			str.append("<tr>");

			str.append("<th>Sr.No.</th> ");
			str.append("<th>Client No</th> ");
			str.append("<th colspan ='2'>Client Name</th> ");
			str.append("<th colspan ='2'>Charges For(Appt Type)</th> ");
			str.append("<th>Appt Date</th> ");
			str.append("<th>Appt Status</th> ");
			str.append("<th>Appt Charges</th> ");
			str.append("<th>Invoice To</th> ");
			str.append("<th>Inv No</th> ");
			str.append("<th colspan ='2'>Inv Date</th> ");
			str.append("<th colspan ='2'>Paid Date</th> ");
			str.append("<th>Payment Mode</th> ");
			str.append("<th>Commission /Fee</th> ");

			str.append("</tr>");
			str.append("</thead>");
			str.append("<tbody>");
			int srno = 1;
			for (Commission commission : clientCommissionList) {
				str.append("<tr class= 'bg-info'>");
				str.append("<td>" + srno + "</td>");
				str.append("<td>" + commission.getClientId() + "</td>");
				str.append("<td colspan ='2'>" + commission.getClientName() + "</td>");
				str.append("<td colspan ='2'>" + commission.getAppType() + "</td>");
				str.append("<td>" + commission.getCommensing() + "</td>");
				str.append("<td>" + commission.getAppStatus() + "</td>");
				str.append("<td>" + Constants.getCurrency(loginInfo) + commission.getCharge() + "</td>");
				str.append("<td>" + commission.getInvoiceTo() + "</td>");
				str.append("<td>" + commission.getInvoiceNo() + "</td>");
				str.append("<td colspan ='2'>" + commission.getInvoiceDate() + "</td>");
				str.append("<td colspan ='2'>" + commission.getPaidDate() + "</td>");
				str.append("<td>" + commission.getPaymode() + "</td>");
				str.append("<td>" + Constants.getCurrency(loginInfo) + commission.getCommssionFee() + "</td>");
				str.append("</tr>");
				srno++;
			}

			str.append("</tbody>");
			str.append("</table>");
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

	/*
	 * public String commissionList(){ if(!verifyLogin(request)){ return "login"; }
	 * //String pId = (String) session.getAttribute("practitionerID"); String pId =
	 * commissionForm.getDiaryUser(); int practitionerId = Integer.parseInt(pId);
	 * 
	 * Connection connection = null; try{ connection =
	 * Connection_provider.getconnection(); CommissionDAO commissionDAO = new
	 * JDBCCommissionDAO(connection);
	 * 
	 * ArrayList<Commission> commissionList =
	 * commissionDAO.getCommissionList(practitionerId);
	 * 
	 * commissionForm.setCommissionList(commissionList);
	 * 
	 * for(Commission commission : commissionList){
	 * commissionForm.setTotalCharge(commission.getTotalCharge());
	 * commissionForm.setCommissionCharge(commission.getCommissionCharge());
	 * commissionForm.setDNACharge(commission.getDNACharge());
	 * commissionForm.setCACharge(commission.getCACharge()); }
	 * 
	 * 
	 * 
	 * }catch(Exception e){ e.printStackTrace(); } return SUCCESS; }
	 */

	public String previewReport() throws Exception {
		if (!verifyLogin(request)) {
			return "login";
		}

		String fromDate = request.getParameter("fromDate");
		String toDate = request.getParameter("toDate");
		String practitionerId = "";

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
			CommissionDAO commissionDAO = new JDBCCommissionDAO(connection);
			ArrayList<Commission> practitionerList = (ArrayList<Commission>) session.getAttribute("practcommision");

			StringBuffer str = new StringBuffer();

			String currentDate = DateTimeUtils.getDateinSimpleFormate(new Date());
			String temp[] = currentDate.split(" ");
			String temptime[] = currentDate.split("at");
			String temp1[] = temp[0].split("-");
			String date = temp1[0] + "-" + temp1[1] + "-" + temp1[2];
			String time = temptime[1];

			str.append("<div style='font-size: 15px; font-weight: bold;'> Commission Report </div>");
			str.append("<div style='font-size: 10px; font-weight: bold;'> Date: " + date + " </div>");
			str.append("<div style='font-size: 10px; font-weight: bold;'> Time: " + time + " </div>");
			str.append("<br>");

			str.append("<table cellpadding='0' cellspacing='0' style='width: 100%;font-size: 10px;'>");

			str.append("<thead>");
			str.append("<tr>");

			str.append("<th>Sr.No.</th> ");
			// str.append("<th>Practitioner No</th> ");
			str.append("<th>Practitioner Name</th> ");
			str.append("<th>Clinic/Location</th> ");
			str.append("<th>Total Appt Nos (S=D+C+I)</th> ");
			str.append("<th>Appt-DNA (D)</th> ");
			str.append("<th>Appt -Completed (C)</th> ");
			str.append("<th>Appt -Incomplete (I)</th> ");
			str.append("<th>Charges Invoiced</th> ");
			str.append("<th>Invoice Paid </th> ");
			str.append("<th>Total Appt Amt X (S) )</th> ");
			str.append("<th>Commission Rate-DNA</th> ");
			str.append("<th>Commission Rate-Completed</th> ");
			str.append("<th>Total Commission /Fee (for Invoice Paid)</th> ");

			str.append("</tr>");
			str.append("</thead>");
			str.append("<tbody>");
			int srno = 1;
			for (Commission commission : practitionerList) {
				str.append("<tr class= 'bg-info'>");
				str.append("<td>" + srno + "</td>");
				// str.append("<td>"+commission.getPractitionerId()+"</td>");
				str.append("<td>" + commission.getPractitionerName() + "</td>");
				str.append("<td>" + commission.getClinicLocation() + "</td>");
				str.append("<td>" + commission.getTotalAppNo() + "</td>");
				str.append("<td>" + commission.getTotalDNA() + "</td>");
				str.append("<td>" + commission.getTotalCompleted() + "</td>");
				str.append("<td>" + commission.getTotalIncompleted() + "</td>");
				str.append("<td>" + commission.getTotalChargeInvoiced() + "</td>");
				str.append("<td>" + commission.getTotalInvoicePaid() + "</td>");
				str.append("<td>" + commission.getTotalCharge() + "</td>");
				str.append("<td>" + commission.getDNACharge() + "</td>");
				str.append("<td>" + commission.getCACharge() + "</td>");
				str.append("<td>" + commission.getTotalCommission() + "</td>");
				str.append("</tr>");
				srno++;

				practitionerId = commission.getPractitionerId();
			}

			str.append("</table><br><br>");

			String filePath = request.getRealPath("/liveData/commissionReport/");
//			System.out.println(filePath);
			String filename = "Commission_" + date + ".pdf";
			// String filename ="commission.pdf";

			htmlToPdfFile(str.toString(), filePath, filename);

			session.setAttribute("commissionFileName", filePath + "/" + filename);

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

	public String sendEmail() throws Exception {

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

			String filename = (String) session.getAttribute("commissionFileName");

			// int result = emailTemplateDAO.saveEmailLogDetails(to, cc, subject, notes,
			// date1,time);
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

	public CommissionForm getModel() {
		// TODO Auto-generated method stub
		return commissionForm;
	}

	public void prepare() throws Exception {

		Connection connection = null;

		try {

			connection = Connection_provider.getconnection();
			AccountsDAO accountsDAO = new JDBCAccountsDAO(connection);
			NotAvailableSlotDAO notAvailableSlotDAO = new JDBCNotAvailableSlotDAO(connection);

			ArrayList<Accounts> locationList = accountsDAO.getLocationList(loginInfo.getClinicid());
			ArrayList<DiaryManagement> userList = notAvailableSlotDAO.getUserList(loginInfo.getId());
			commissionForm.setUserList(userList);
			commissionForm.setLocationList(locationList);

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			connection.close();
		}
	}

	public String OPDPractitionerList() throws Exception {
		if (!verifyLogin(request)) {
			return "login";
		}

		String fromDate = commissionForm.getFromDate();
		String toDate = commissionForm.getToDate();

		if (fromDate.equals("")) {
			DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
			Calendar cal = Calendar.getInstance();
			cal.add(Calendar.DATE, -5);
			String checkDate = dateFormat.format(cal.getTime());
			fromDate = checkDate;
			commissionForm.setFromDate(fromDate);
		}

		if (toDate.equals("")) {
			DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
			Calendar cal = Calendar.getInstance();
			String checkDate = dateFormat.format(cal.getTime());
			toDate = checkDate;
			commissionForm.setToDate(toDate);
		}

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
			CommissionDAO commissionDAO = new JDBCCommissionDAO(connection);
			ChargesReportDAO chargesReportDAO = new JDBCChargesReportDAO(connection);
			//

			String action = request.getParameter("action");
			commissionForm.setAction(action);

			DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Calendar cal = Calendar.getInstance();
			String date = dateFormat.format(cal.getTime());

			int result = chargesReportDAO.saveMisReportLog("OPD Practitioner Share Report", loginInfo.getUserId(),
					fromDate, toDate, date, "OPDPractitionerList");

			ArrayList<Commission> practitionerList = commissionDAO.getPractitionerShareList(fromDate, toDate,
					commissionForm.getDiaryUser(), commissionForm.getLocation());
			commissionForm.setPractitionerList(practitionerList);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			connection.close();
		}
		return "opdpractshare";
	}

}
