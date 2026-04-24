package com.apm.Mis.web.action;

import java.sql.Connection;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.a.a.a.a.a.a;
import com.apm.Accounts.eu.bi.AccountsDAO;
import com.apm.Accounts.eu.blogic.jdbc.JDBCAccountsDAO;
import com.apm.Accounts.eu.entity.Accounts;
import com.apm.DiaryManagement.eu.bi.NotAvailableSlotDAO;
import com.apm.DiaryManagement.eu.blogic.jdbc.JDBCNotAvailableSlotDAO;
import com.apm.DiaryManagement.eu.entity.Client;
import com.apm.DiaryManagement.eu.entity.DiaryManagement;
import com.apm.DiaryManagement.eu.entity.Priscription;
import com.apm.Emr.eu.bi.PrescriptionDAO;
import com.apm.Emr.eu.blogic.jdbc.JDBCPrescriptionDAO;
import com.apm.Emr.eu.entity.Investigation;
import com.apm.Expence.eu.entity.Expence;
import com.apm.Inventory.eu.bi.InventoryProductDAO;
import com.apm.Inventory.eu.bi.ProcurementDAO;
import com.apm.Inventory.eu.blogic.jdbc.JDBCInventoryProductDAO;
import com.apm.Inventory.eu.blogic.jdbc.JDBCProcurementDAO;
import com.apm.Inventory.eu.entity.Product;
import com.apm.Ipd.eu.bi.BedDao;
import com.apm.Ipd.eu.bi.IpdDAO;
import com.apm.Ipd.eu.blogic.jdbc.JDBCBedDao;
import com.apm.Ipd.eu.blogic.jdbc.JDBCIpdDAO;
import com.apm.Ipd.eu.entity.Bed;
import com.apm.Master.eu.entity.Master;
import com.apm.Mis.eu.bi.MisChartDAO;
import com.apm.Mis.eu.blogic.jdbc.JDBCMisChartDAO;
import com.apm.Mis.web.form.MisChartForm;
import com.apm.Pharmacy.eu.bi.PharmacyDAO;
import com.apm.Pharmacy.eu.blogic.jdbc.JDBCPharmacyDAO;
import com.apm.Registration.eu.bi.ClinicDAO;
import com.apm.Registration.eu.bi.UserProfileDAO;
import com.apm.Registration.eu.blogic.jdbc.JDBCClinicDAO;
import com.apm.Registration.eu.blogic.jdbc.JDBCUserProfileDAO;
import com.apm.Registration.eu.entity.Clinic;
import com.apm.Report.eu.bi.ChargesReportDAO;
import com.apm.Report.eu.bi.ClientReportDAO;
import com.apm.Report.eu.bi.ClinicalReportDAO;
import com.apm.Report.eu.blogic.jdbc.JDBCChargesReportDAO;
import com.apm.Report.eu.blogic.jdbc.JDBCClientReportDAO;
import com.apm.Report.eu.blogic.jdbc.JDBCClinicalReportDAO;
import com.apm.Report.eu.entity.MisReport;
import com.apm.Report.eu.entity.Report;
import com.apm.Tools.web.action.TPReferalAction;
import com.apm.TreatmentEpisode.eu.entity.TreatmentEpisode;
import com.apm.common.eu.blogic.jdbc.Connection_provider;
import com.apm.common.utils.DateTimeUtils;
import com.apm.common.web.action.BaseAction;
import com.apm.common.web.common.helper.LoginHelper;
import com.apm.common.web.common.helper.LoginInfo;
import com.apm.main.common.constants.Constants;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ModelDriven;

public class MisChartAction extends BaseAction implements ModelDriven<MisChartForm> {

	HttpServletRequest request = (HttpServletRequest) ActionContext.getContext().get(ServletActionContext.HTTP_REQUEST);
	HttpServletResponse response = (HttpServletResponse) ActionContext.getContext()
			.get(ServletActionContext.HTTP_RESPONSE);
	HttpSession session = request.getSession(true);
	LoginInfo loginInfo = LoginHelper.getLoginInfo(request);

	MisChartForm misChartForm = new MisChartForm();

	public String execute() throws Exception {
		if (!verifyLogin(request)) {
			return "login";
		}
		
		
		
		String fromdate = misChartForm.getFromDate();
		String todate = misChartForm.getToDate();
		String type = misChartForm.getType();
		String filter_ward = misChartForm.getFilter_ward();
		String location = misChartForm.getFilter_location();
		String year_filter = misChartForm.getYear_filter();
		String month_filter = misChartForm.getMonth_filter();
		String kpiarea_filter = misChartForm.getKpiarea_filter();
		
		session.setAttribute("ipdlocation", "0");
		if (location == null) {
			location = "0";
		} else if (location.equals("")) {
			location = "0";
		}
		if (fromdate.equals("")) {
			DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
			Calendar cal = Calendar.getInstance();
			// cal.add(Calendar.DATE, -30);
			fromdate = dateFormat.format(cal.getTime());
			misChartForm.setFromDate(fromdate);

		}
		if (todate.equals("")) {
			DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
			Calendar cal = Calendar.getInstance();
			// cal.add(Calendar.DATE, -7);
			todate = dateFormat.format(cal.getTime());
			misChartForm.setToDate(todate);

		}
		if (filter_ward == null) {
			filter_ward = "0";
		} else if (filter_ward.equals("")) {
			filter_ward = "0";
		}

		if (year_filter == null) {
			year_filter = "2017-2018";
		} else if (year_filter.equals("")) {
			year_filter = "2017-2018";
		}

		if (month_filter == null) {
			month_filter = "01";
		} else if (month_filter.equals("")) {
			month_filter = "01";
		}
		/*if (month_filter != null) {
			if (month_filter.equals("")) {
				month_filter = null;
			}
		}*/

		if (kpiarea_filter != null) {
			if (kpiarea_filter.equals("")) {
				kpiarea_filter = null;
			}else if(kpiarea_filter.equals("0")){
				kpiarea_filter = null;
			}
		}

		fromdate = DateTimeUtils.getCommencingDate1(fromdate);
		todate = DateTimeUtils.getCommencingDate1(todate);
		String action = misChartForm.getAction();
		session.setAttribute("misfromdate", misChartForm.getFromDate());
		session.setAttribute("mistodate", misChartForm.getToDate());
		boolean flag = false;
		Connection connection = null;
		try {
			connection = Connection_provider.getconnection();
			MisChartDAO misChartDAO = new JDBCMisChartDAO(connection);
			PharmacyDAO pharmacyDAO = new JDBCPharmacyDAO(connection);
			IpdDAO ipdDAO = new JDBCIpdDAO(connection);
			NotAvailableSlotDAO notAvailableSlotDAO=new JDBCNotAvailableSlotDAO(connection);
			
			String isfromandroid = request.getParameter("isfromandroid");
			String androidpractuserid = request.getParameter("androidpractuserid");
			if(isfromandroid!=null){
				if(isfromandroid.equals("")){
					isfromandroid="0";
				}
			}else{
					isfromandroid="0";
			}
			
			if(isfromandroid.equals("1")){
				boolean checkmisaccess = misChartDAO.checkMISAccess(androidpractuserid);
				if(!checkmisaccess){
					return "loginerror";
				}
			}
			
			
			
			BedDao bedDao = new JDBCBedDao(connection);
			misChartForm.setMisreport(true);
			String graphName = misChartForm.getGraphName();
			session.setAttribute("graphName", graphName);

			ArrayList<Client> opdPatientReport = new ArrayList<Client>();
			ArrayList<Client> opdPatientCancelReport = new ArrayList<Client>();
			ArrayList<Bed> bedlist = new ArrayList<Bed>();
			ArrayList<Bed> ipdPatientReport = new ArrayList<Bed>();
			ArrayList<Priscription> prescriptionList = new ArrayList<Priscription>();
			ArrayList<Accounts> advancedRefundList = new ArrayList<Accounts>();
			ArrayList<Accounts> accountinfo = new ArrayList<Accounts>();
			ArrayList<Accounts> accountinfo1 = new ArrayList<Accounts>();
			ArrayList<Client> patientbylocation = new ArrayList<Client>();
			ArrayList<Client> clinicalViewList = new ArrayList<Client>();
			ArrayList<Client> patientbycondition = new ArrayList<Client>();
			ArrayList<Client> dailySummaryList = new ArrayList<Client>();
			ArrayList<Client> dailySummaryList1 = new ArrayList<Client>();
			ArrayList<Expence> expenseList = new ArrayList<Expence>();
			ArrayList<Accounts> patientViewByPackage = new ArrayList<Accounts>();
			ArrayList<Expence> paymenmodeList = new ArrayList<Expence>();
			ArrayList<Priscription> pharmacydailysalelist = new ArrayList<Priscription>();
			ArrayList<Investigation> investigationlist = new ArrayList<Investigation>();
			ArrayList<Master> locationListPharmacy = new ArrayList<Master>();
			ArrayList<MisReport> kpiarealist = new ArrayList<MisReport>();
			ArrayList<MisReport> kpilist = new ArrayList<MisReport>();
			ArrayList<MisReport> nabhsubcatagoylist = new ArrayList<MisReport>();
			ArrayList<MisReport>  selfassementtoollist = new ArrayList<MisReport>();
			ArrayList<MisReport> opdappointmenttype = new ArrayList<MisReport>();
			ArrayList<Bed> wardwisedatalist = new ArrayList<Bed>();
			if (action == null) {
				String kpiaction = request.getParameter("kpiaction");
				if(kpiaction==null){
					action = "dailysummary";
				}else if(kpiaction.equals("")){
					action = "dailysummary";
				}else{
					action = "kpireports";
					flag=true;
				}
				
			}

			if (action.equals("yearly")) {

				Calendar calendar2 = Calendar.getInstance();
				Date date = calendar2.getTime();
				SimpleDateFormat format = new SimpleDateFormat("MMM-yyyy");
				String currentMonth = format.format(date);
				misChartForm.setCurrentMonth(currentMonth);
				int totalCompleted = misChartDAO.getTotalCompleted(fromdate, todate);
				int inhousepatients = misChartDAO.getInHousePatients(fromdate, todate);
				int otPatientCount = misChartDAO.getOtPatients(fromdate, todate);
				double totalTemp = inhousepatients + totalCompleted + otPatientCount;
				double ipdper = (inhousepatients * 100) / totalTemp;
				double opdper = (totalCompleted * 100) / totalTemp;
				double otper = (otPatientCount * 100) / totalTemp;
				String stripd = String.valueOf(ipdper);
				String stropd = String.valueOf(opdper);
				String strot = String.valueOf(otper);

				String stripdper = "", stropdper = "", strotper = "";

				if (stripd.length() > 8) {
					stripdper = stripd.substring(0, 5);
				} else {
					stripdper = stripd;
				}
				if (stropd.length() > 8) {
					stropdper = stropd.substring(0, 5);
				} else {
					stropdper = stropd;
				}
				if (strot.length() > 8) {
					strotper = strot.substring(0, 5);
				} else {
					strotper = strot;
				}

				session.setAttribute("ipdper", stripdper);
				session.setAttribute("opdper", stropdper);
				session.setAttribute("otper", strotper);

				// All Graph list
				Client client = misChartDAO.getAllYearReviewList();
				session.setAttribute("allgraph", client);

			}
			if (action.equals("monthly")) {

				DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
				Calendar cal = Calendar.getInstance();
				// cal.add(Calendar.DATE, -1);
				todate = dateFormat.format(cal.getTime());
				todate = DateTimeUtils.getCommencingDate1(todate);
				int ipdOldPatient = misChartDAO.getInHousePatients(fromdate, todate);
				int inhousepatients = misChartDAO.getInHousePatients(fromdate, todate);
				// opd patient data
				int totalOpdPatient = misChartDAO.getTotalOpdPatient(fromdate, todate);
				int totalopdseen = misChartDAO.getBookedAppointment(fromdate, todate);

				// Ot date count
				int otPatientCount = misChartDAO.getOtPatients(fromdate, todate);
				int ipdnewadmission = misChartDAO.getIpdNewAdmission(todate, todate);

				int totalipdopdpatient = totalOpdPatient + ipdnewadmission + ipdOldPatient + otPatientCount;
				misChartForm.setTotalopdseen(totalopdseen);
				misChartForm.setIpdnewadmission(ipdnewadmission);
				misChartForm.setIpdOldPatient(ipdOldPatient);
				misChartForm.setTotalipdopdpatient(totalipdopdpatient);

				totalOpdPatient = misChartDAO.getTotalOpdPatient(fromdate, todate);
				misChartForm.setTotalOpdPatient(totalOpdPatient);
				int totalPatient = misChartDAO.getTptalPatient(fromdate, todate);
				misChartForm.setTotalPatient(totalPatient);

				int totalbeds = misChartDAO.getTotalBeds(fromdate, todate);
				int occupiedbed = misChartDAO.getOccupiedBed(fromdate, todate);
				int availablebed = misChartDAO.getAvailableBed(fromdate, todate);
				availablebed = availablebed - occupiedbed;

				Calendar calendar = Calendar.getInstance();
				int day = calendar.get(Calendar.DAY_OF_MONTH);
				double montwiseAdmission = inhousepatients / 12;
				// double montwiseBedOccupy = totalDays*100 / (day*totalbeds);
				double montwiseBedOccupy = (totalbeds * occupiedbed) / 100;
				montwiseBedOccupy = Double.parseDouble(new DecimalFormat("##.##").format(montwiseBedOccupy));

				// invoice and billing
				int chargeaddedd = misChartDAO.getChargeAddedd(fromdate, todate,filter_ward);
				int invoicegenerated = misChartDAO.getInvoiceGenerated(fromdate, todate,
						misChartForm.getInvoicecategory(), loginInfo.getJobTitle(), filter_ward);
				int paymentrecieved = misChartDAO.getPaymentrecieved(fromdate, todate,
						misChartForm.getInvoicecategory(), loginInfo.getJobTitle(),filter_ward);

				double monthwiseinvoiucegeneated = 0.0;
				if (invoicegenerated != 0) {
					monthwiseinvoiucegeneated = paymentrecieved * 100 / invoicegenerated;
				}
				if (monthwiseinvoiucegeneated > 100) {

					monthwiseinvoiucegeneated = 100.00;
				}
				double montwisepaymentrecieved = paymentrecieved * 100 / chargeaddedd;
				misChartForm.setMontwiseAdmission(montwiseAdmission);
				misChartForm.setMontwiseBedOccupy(montwiseBedOccupy);
				misChartForm.setMonthwiseinvoiucegeneated(monthwiseinvoiucegeneated);
				misChartForm.setMontwisepaymentrecieved(montwisepaymentrecieved);
			}

			if (action.equals("opd")) {

				// opd patient data
				int totalOpdPatient = misChartDAO.getTotalOpdPatient(fromdate, todate);
				int bookedAppointment = misChartDAO.getBookedAppointment(fromdate, todate);
				/*int totaldna = misChartDAO.getTotalDNA(fromdate, todate);*/
				//  08 MAY 2018
				//int totalCompleted = misChartDAO.getTotalCompleted(fromdate, todate);
				int totalCompleted = misChartDAO.getOPDCompletedAppointment(fromdate, todate);
				int totaldna = misChartDAO.getOPDdnaAppointment(fromdate, todate);
				int totalcancel = misChartDAO.getOPDcancelcountAppointment(fromdate, todate);
				// Ot date count
				//int otPatientCount = misChartDAO.getOtPatients(fromdate, todate);
				int otPatientCount = misChartDAO.getOtPatientsCounts(fromdate, todate);
				misChartForm.setOtPatientCount(otPatientCount);

				int noc = totalCompleted + totaldna;
				long notCompleted = bookedAppointment - noc;
				//notCompleted = notCompleted - otPatientCount;

				// Bed Status
				bedDao = new JDBCBedDao(connection);
				
				ArrayList<DiaryManagement> userList=new ArrayList<>();
				
				userList = notAvailableSlotDAO.getUserListforOpdData(loginInfo.getId(),fromdate,todate);
				
				misChartForm.setUserList(userList);
				
				
				
				
				// opd patient list report

				opdPatientReport = misChartDAO.getOpdPatientListReport(fromdate, todate);
				opdPatientCancelReport = misChartDAO.getOpdPatientCancelListReport(fromdate, todate);
				bedlist = bedDao.getAllBedList();
				misChartForm.setOpdPatientReport(opdPatientReport);
				misChartForm.setOpdPatientCancelReport(opdPatientCancelReport);
				misChartForm.setNotCompleted(notCompleted);
				misChartForm.setTotalOpdPatient(totalOpdPatient);
				misChartForm.setBookedAppointment(bookedAppointment);
				misChartForm.setTotaldna(totaldna);
				misChartForm.setTotalcancel(totalcancel);
				misChartForm.setTotalCompleted(totalCompleted);
				misChartForm.setTotalOpdPatient(totalOpdPatient);
				misChartForm.setBookedAppointment(bookedAppointment);
				misChartForm.setTotaldna(totaldna);
				misChartForm.setTotalCompleted(totalCompleted);

			}

			if (action.equals("ipd")) {

				// Ipd Patient list Report
				ipdPatientReport = misChartDAO.getIpdPatientListReport(fromdate, todate);
				misChartForm.setIpdPatientReport(ipdPatientReport);
				
				wardwisedatalist=misChartDAO.getWardWiseData(fromdate, todate);
                misChartForm.setWardwisereport(wardwisedatalist);
				int totalDays = 0;
				int temp = 0;
				for (Bed bed : ipdPatientReport) {

					temp = Integer.parseInt(bed.getTotalDays());
					totalDays = totalDays + temp;
				}

				DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
				Calendar cal = Calendar.getInstance();
				// cal.add(Calendar.DATE, -1);
				String today = dateFormat.format(cal.getTime());
				today = DateTimeUtils.getCommencingDate1(today);
				

				// Ipd patient data
				int newadmission = misChartDAO.getIpdNewAdmission(fromdate, todate);
				int inhousepatients = misChartDAO.getInHousePatients(fromdate, todate);
				int dischargepatients = misChartDAO.getDischargePatients(fromdate, todate);
				int newaddmissiontoday = misChartDAO.getIpdNewAdmissionNew(today);
				misChartForm.setNewadmission(newadmission);
				misChartForm.setInhousepatients(inhousepatients);
				misChartForm.setDischargepatients(dischargepatients);
				misChartForm.setNewaddmissiontoday(newaddmissiontoday);
			}

			/*
			 * if(action.equals("bedstatus")){
			 * 
			 * // bed status data int totalbeds =
			 * misChartDAO.getTotalBeds(fromdate,todate); int undermaintnancebed
			 * = misChartDAO.getUnderMaintnanceBed(fromdate,todate); int
			 * occupiedbed = misChartDAO.getOccupiedBed(fromdate,todate); int
			 * availablebed = misChartDAO.getAvailableBed(fromdate,todate);
			 * availablebed = availablebed - occupiedbed;
			 * misChartForm.setTotalbeds(totalbeds);
			 * misChartForm.setUndermaintnancebed(undermaintnancebed);
			 * misChartForm.setOccupiedbed(occupiedbed);
			 * misChartForm.setAvailablebed(availablebed);
			 * 
			 * }
			 */
			if (action.equals("bedstatus")) {

				// ruchi bed status data
				bedlist = misChartDAO.getBedStatusReport(fromdate, todate);
				misChartForm.setBedlist(bedlist);
				
				wardwisedatalist=misChartDAO.getWardWiseData(fromdate, todate);
				misChartForm.setWardwisereport(wardwisedatalist);

				ArrayList<Bed> bedlist1 = ipdDAO.getAllBedList("0", loginInfo.getClinicid(), loginInfo, "3","0","1","0","0","0");
				int size = bedlist1.size();
				if (size > 0) {
					int totolintitaldischarge = bedlist1.get(size - 1).getTotolintitaldischarge();
					misChartForm.setDischargepatients(totolintitaldischarge);
				} else {

					misChartForm.setDischargepatients(0);
				}

				int totalbeds = misChartDAO.getTotalBeds(fromdate, todate);
				int undermaintnancebed = misChartDAO.getUnderMaintnanceBed(fromdate, todate);
				int occupiedbed = misChartDAO.getOccupiedBed(fromdate, todate);
				int availablebed = misChartDAO.getAvailableBed(fromdate, todate);

				availablebed = availablebed - occupiedbed;
				misChartForm.setTotalbeds(totalbeds);
				misChartForm.setUndermaintnancebed(undermaintnancebed);
				misChartForm.setOccupiedbed(occupiedbed);
				misChartForm.setAvailablebed(availablebed);

			}

			if (action.equals("invoice")) {

				// invoice and billing
				int chargeaddedd = misChartDAO.getChargeAddedd(fromdate, todate,filter_ward);
				int invoicegenerated = misChartDAO.getInvoiceGenerated(fromdate, todate,
						misChartForm.getInvoicecategory(), loginInfo.getJobTitle(),filter_ward);
				int paymentrecieved = misChartDAO.getPaymentrecieved(fromdate, todate,
						misChartForm.getInvoicecategory(), loginInfo.getJobTitle(),filter_ward);
				misChartForm.setChargeaddedd(chargeaddedd);
				misChartForm.setInvoicegenerated(invoicegenerated);
				misChartForm.setPaymentrecieved(paymentrecieved);

				ChargesReportDAO chargesReportDAO = new JDBCChargesReportDAO(connection);
				ArrayList<Accounts> invoiceList = new ArrayList<Accounts>();
				invoiceList = chargesReportDAO.getInvoiceReportList(fromdate, todate, Constants.PAY_BY_CLIENT, null,
						"0", "firstname", "ASC", misChartForm.getInvoicecategory(),"0",loginInfo);
				misChartForm.setInvoiceList(invoiceList);

			}

			if (action.equals("paymentmode")) {

				// payment mode
				int totalPayment = misChartDAO.getTotalPayment(fromdate, todate, misChartForm.getInvoicecategory(),
						loginInfo.getJobTitle());
				int cashpayment = misChartDAO.getCashPayment(fromdate, todate, misChartForm.getInvoicecategory(),
						loginInfo.getJobTitle());
				int chequepayment = misChartDAO.getChequepaymenyt(fromdate, todate, misChartForm.getInvoicecategory(),
						loginInfo.getJobTitle());
				int cardPayment = misChartDAO.getCardpayment(fromdate, todate, misChartForm.getInvoicecategory(),
						loginInfo.getJobTitle());
				int upiPayment = misChartDAO.getUpipayment(fromdate, todate, misChartForm.getInvoicecategory(),
						loginInfo.getJobTitle());
				// int
				// ddpayment=misChartDAO.getDDpaymenyt(fromdate,todate,misChartForm.getInvoicecategory(),loginInfo.getJobTitle());
				// int
				// otherpayment=misChartDAO.getOtherpaymenyt(fromdate,todate,misChartForm.getInvoicecategory(),loginInfo.getJobTitle());

				long otherPayment = misChartDAO.getAllOtherPayment(fromdate, todate);
				misChartForm.setOtherPayment(otherPayment);
				misChartForm.setTotalPayment(totalPayment);
				misChartForm.setCashpayment(cashpayment);
				misChartForm.setChequepayment(chequepayment);
				misChartForm.setCardPayment(cardPayment);
				misChartForm.setOtherPayment(otherPayment);
				misChartForm.setUpipayment(upiPayment);

			}
			if (action.equals("advref")) {

				// Advanced and Refund
				long advanced = misChartDAO.getAdvancedAmount(fromdate, todate);
				long refund = misChartDAO.getRefundAmount(fromdate, todate);
				misChartForm.setAdvanced(advanced);
				misChartForm.setRefund(refund);
				//for ayushman wardwise advance refund
				wardwisedatalist=misChartDAO.getWardWiseData(fromdate, todate);
                misChartForm.setWardwisereport(wardwisedatalist);
				int paymentrecieved = misChartDAO.getPaymentrecieved(fromdate, todate,
						misChartForm.getInvoicecategory(), loginInfo.getJobTitle(), filter_ward);

				long totalPayAll = paymentrecieved + advanced - refund;
				misChartForm.setTotalPayAll(totalPayAll);
				// Advanced and Refund Amount
				advancedRefundList = misChartDAO.getAdvanceRefundList(fromdate, todate, loginInfo);

				int totalPayment = misChartDAO.getTotalPayment(fromdate, todate, misChartForm.getInvoicecategory(),
						loginInfo.getJobTitle());
				int cashpayment = misChartDAO.getCashPayment(fromdate, todate, misChartForm.getInvoicecategory(),
						loginInfo.getJobTitle());
				int chequepayment = misChartDAO.getChequepaymenyt(fromdate, todate, misChartForm.getInvoicecategory(),
						loginInfo.getJobTitle());
				int cardPayment = misChartDAO.getCardpayment(fromdate, todate, misChartForm.getInvoicecategory(),
						loginInfo.getJobTitle());

				misChartForm.setTotalPayment(totalPayment);
				misChartForm.setCashpayment(cashpayment);
				misChartForm.setChequepayment(chequepayment);
				misChartForm.setCardPayment(cardPayment);

			}
			if (action.equals("accountinfo")) {

				// Account Summary
				long expenseTotal = misChartDAO.getExpenseTotal(fromdate, todate);
				misChartForm.setExpenseTotal(expenseTotal);

				int totalPayment = misChartDAO.getTotalPayment(fromdate, todate, misChartForm.getInvoicecategory(),
						loginInfo.getJobTitle());
				int cashpayment = misChartDAO.getCashPayment(fromdate, todate, misChartForm.getInvoicecategory(),
						loginInfo.getJobTitle());
				int chequepayment = misChartDAO.getChequepaymenyt(fromdate, todate, misChartForm.getInvoicecategory(),
						loginInfo.getJobTitle());
				int cardPayment = misChartDAO.getCardpayment(fromdate, todate, misChartForm.getInvoicecategory(),
						loginInfo.getJobTitle());

				misChartForm.setTotalPayment(totalPayment);
				misChartForm.setCashpayment(cashpayment);
				misChartForm.setChequepayment(chequepayment);
				misChartForm.setCardPayment(cardPayment);

				// Account info
				accountinfo = misChartDAO.getAccountInfoList(fromdate, todate);
				accountinfo1 = misChartDAO.getAccountInfoList1(fromdate, todate);

			}

			if (action.equals("expense")) {
				expenseList = misChartDAO.getExpenceCategoryList(fromdate, todate);
				paymenmodeList = misChartDAO.getPaymentModeList(fromdate, todate);
				int index = expenseList.size() - 1;
				misChartForm.setTotalExpence("" + expenseList.get(index).getTotal() + "");
				expenseList.addAll(paymenmodeList);
				misChartForm.setExpenseList(expenseList);

			}

			if (action.equals("dailysummary")) {
				int totalopdseen = misChartDAO.getBookedAppointment(fromdate, todate);
				int ipdnewadmission = misChartDAO.getIpdNewAdmission(fromdate, todate);
				//  01 feb 2018 comment becuase it take more time
				//ArrayList<MisReport> ipdnewaddmissionlist = misChartDAO.getipdnewadmissionlist(fromdate, todate);
				
				//  08 MAY 2018
				int noofcasulity=misChartDAO.getCasualityCount(fromdate, todate);
				int noofdaycare=misChartDAO.getDayCareCount(fromdate, todate);
				misChartForm.setNoofcasuality(noofcasulity);
				misChartForm.setNoofdaycare(noofdaycare);
				int totalopdcompleted = misChartDAO.getOPDCompletedAppointment(fromdate, todate);
				int totalopddna = misChartDAO.getOPDdnaAppointment(fromdate, todate);
				misChartForm.setTotalopdcompleted(totalopdcompleted);
				misChartForm.setTotalopddna(totalopddna);
				String newdate = todate;
				int newadmitted = misChartDAO.getNewRegistration(fromdate, todate);
				DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
				Calendar cal = Calendar.getInstance();
				cal.add(Calendar.DATE, -1);
				todate = dateFormat.format(cal.getTime());
				todate = DateTimeUtils.getCommencingDate1(todate);

				int ipdOldPatient = misChartDAO.getInHousePatients(fromdate, todate);
				
				//  01 feb 2018 comment becuase it take more time
				//ArrayList<MisReport> ipdOldPatientList = misChartDAO.getInHousePatientsList(fromdate, todate);
				
				int totalipdopdpatient = ipdnewadmission + totalopdseen + ipdOldPatient;
				misChartForm.setTotalopdseen(totalopdseen);
				misChartForm.setIpdnewadmission(ipdnewadmission);
				misChartForm.setIpdOldPatient(ipdOldPatient);
				misChartForm.setTotalipdopdpatient(totalipdopdpatient);
				misChartForm.setNewregpatient(newadmitted);
				misChartForm.setTotalopdseen(totalopdseen);
				misChartForm.setIpdnewadmission(ipdnewadmission);
				misChartForm.setIpdOldPatient(ipdOldPatient);
				// Daily Summary
				dailySummaryList = misChartDAO.getAllDailySummaryList(fromdate, todate);
				dailySummaryList1 = misChartDAO.getAllDailySummaryList1(fromdate, todate, loginInfo);
				

				// Ipd patient data
				//int newadmission = misChartDAO.getIpdNewAdmission(fromdate, todate);
				int inhousepatients = misChartDAO.getInHousePatients(fromdate, newdate);
				int dischargepatients = misChartDAO.getDischargePatients(fromdate, newdate);
				/*int newaddmissiontoday = misChartDAO.getIpdNewAdmissionNew(today);
				misChartForm.setNewadmission(newadmission);*/
				misChartForm.setInhousepatients(inhousepatients);
				misChartForm.setDischargepatients(dischargepatients);
				/*misChartForm.setNewaddmissiontoday(newaddmissiontoday);*/
				
				investigationlist = misChartDAO.getInvestigationDetails(fromdate, newdate, filter_ward, loginInfo);
				int size = investigationlist.size();
				if (size > 0) {
					int new_invistigation = investigationlist.get(size - 1).getNew_invistigation();
					int new_collected = investigationlist.get(size - 1).getNew_collected();
					int new_completed = investigationlist.get(size - 1).getNew_completed();
					int new_aproved = investigationlist.get(size - 1).getNew_aproved();
					int deleted_investigation = investigationlist.get(size - 1).getDeleted_investigation();
					int total = new_invistigation + new_collected + new_completed + new_aproved;
					misChartForm.setTotalInvestigation("" + total);
				} else {
					misChartForm.setTotalInvestigation("0");
				}
				
				int chargeaddedd = misChartDAO.getChargeAddedd(fromdate, newdate,filter_ward);
				int invoicegenerated = misChartDAO.getInvoiceGenerated(fromdate, newdate,
						misChartForm.getInvoicecategory(), loginInfo.getJobTitle(), filter_ward);
				int paymentrecieved = misChartDAO.getPaymentrecieved(fromdate, newdate,
						misChartForm.getInvoicecategory(), loginInfo.getJobTitle(),filter_ward);

				misChartForm.setChargeaddedd(chargeaddedd);
				misChartForm.setInvoicegenerated(invoicegenerated);
				misChartForm.setPaymentrecieved(paymentrecieved);
				
				int otPatientCount = misChartDAO.getOtPatientsCounts(fromdate, newdate);
				misChartForm.setOtPatientCount(otPatientCount);
				
				ArrayList<MisReport> appointmenttypeid = misChartDAO.getAppointmentTypeID();
				for (MisReport string : appointmenttypeid) {
					MisReport mistotal = misChartDAO.getOPDAppointment(fromdate, newdate,""+string.getId());
					if(mistotal.getResult()!=null){
						if(mistotal.getResult().equals("") || mistotal.getResult().equals("null")){
							mistotal.setResult("0");
						}
					}else{
						mistotal.setResult("0");
					}
					mistotal.setName(string.getName());
					opdappointmenttype.add(mistotal);
				}
				
				
				int totaldeath = misChartDAO.getTotalDeaths(fromdate, newdate);
				int totaldame = misChartDAO.getTotalDama(fromdate, newdate);
				int totalmlcaddmission = misChartDAO.getTotalMLCAddmission(fromdate, newdate);
				int totalpathlab = misChartDAO.getInvestigationJobTitle(fromdate, newdate,"Pathlab");
				int totalradiology = misChartDAO.getInvestigationJobTitle(fromdate, newdate,"Radiologist");
				int totalmaicrobiology = misChartDAO.getInvestigationJobTitle(fromdate, newdate,"MICROBIOLOGY");
				int totalendoscopy = misChartDAO.getInvestigationJobTitle(fromdate, newdate,"ENDOSCOPY");
				int totalcardiology = misChartDAO.getInvestigationJobTitle(fromdate, newdate,"CARDIOLOGY");
				
				misChartForm.setTotaldeath(totaldeath);
				misChartForm.setTotalDAMA(totaldame);
				misChartForm.setTotalmlcaddmission(totalmlcaddmission);
				misChartForm.setTotalpathlab(totalpathlab);
				misChartForm.setTotalradiology(totalradiology);
				misChartForm.setTotalmaicrobiology(totalmaicrobiology);
				misChartForm.setTotalendoscopy(totalendoscopy);
				misChartForm.setTotalcardiology(totalcardiology);
				
				String ctid = misChartDAO.getInvestSecIdFromName("CT");
				String mriid = misChartDAO.getInvestSecIdFromName("MRI");
				String xrayid = misChartDAO.getInvestSecIdFromName("X-Ray");
				String usgid = misChartDAO.getInvestSecIdFromName("SONOGRAPHY");
				String cardiologyid = misChartDAO.getInvestSecIdFromName("NON INVASIVE CARDIOLOGY");
				
				int ctcount = misChartDAO.getInvestCountBySec(ctid,fromdate,newdate);
				int mricount = misChartDAO.getInvestCountBySec(mriid,fromdate,newdate);
				int xraycount = misChartDAO.getInvestCountBySec(xrayid,fromdate,newdate);
				int sonographycount = misChartDAO.getInvestCountBySec(usgid,fromdate,newdate);
				int totalcardiologycount = misChartDAO.getInvestCountBySec(cardiologyid,fromdate,newdate);
				
				misChartForm.setTotalctinvest(ctcount);
				misChartForm.setTotalmricount(mricount);
				misChartForm.setTotalxraycount(xraycount);
				misChartForm.setTotalsonographycount(sonographycount);
				misChartForm.setTotalcardiologycount(totalcardiologycount);
			}

			if (action.equals("clinicalview")) {

				int requestedprescription = misChartDAO.getRequestedPrescription(fromdate, todate);
				int requestedInvestigation = misChartDAO.getRequestedInvestigation(fromdate, todate);
				int totalpatientbycondition = misChartDAO.getTotalPatientByCondition(fromdate, todate);

				int totalClinicalView = totalpatientbycondition + requestedprescription + requestedInvestigation;
				patientbycondition = misChartDAO.getPaticlentByCondiion(fromdate, todate);

				misChartForm.setTotalClinicalView(totalClinicalView);
				misChartForm.setRequestedprescription(requestedprescription);
				misChartForm.setRequestedInvestigation(requestedInvestigation);

				// Clinical View
				clinicalViewList = misChartDAO.getClinicalViewList(fromdate, todate);
				misChartForm.setClinicalViewList(clinicalViewList);

			}

			if (action.equals("patientview")) {
				if(DateTimeUtils.isNull(misChartForm.getFilter_ward()).equals("OPD")){
					patientbylocation = misChartDAO.patientList(fromdate, todate, "OPD");
				}else if(DateTimeUtils.isNull(misChartForm.getFilter_ward()).equals("IPD")){
					patientbylocation = misChartDAO.patientList(fromdate, todate, "IPD");
				}else{
					patientbylocation = misChartDAO.getpatientbyLocation(fromdate, todate);
				}
				
				int index = patientbylocation.size() - 1;
				misChartForm.setTotalPatient(patientbylocation.get(index).getTotal());
				int totalOpdPatient = misChartDAO.getTotalOpdPatient(fromdate, todate);
				misChartForm.setTotalOpdPatient(totalOpdPatient);

			}
			

			/*
			 * if(action.equals("patientviewbypackage")){ patientbylocation =
			 * misChartDAO.getpatientbyLocation(fromdate,todate); int
			 * index=patientbylocation.size()-1;
			 * misChartForm.setTotalPatient(patientbylocation.get(index).
			 * getTotal()); int totalOpdPatient =
			 * misChartDAO.getTotalOpdPatient(fromdate, todate);
			 * misChartForm.setTotalOpdPatient(totalOpdPatient);
			 * 
			 * }
			 */
			if (action.equals("pharmacysummary")) {
				// @ 
				// String location ="0";
				double totalBalance = pharmacyDAO.getTotalBalanceofUsers(location, fromdate, todate);
				double totalCredit = pharmacyDAO.getTotalCredit(location, fromdate, todate);
				double totalCollection = pharmacyDAO.getTotalCollectionToday(location, fromdate, todate);
				double todaycash = pharmacyDAO.getCashToday(location, fromdate, todate);
				double todaycard = pharmacyDAO.getCardToday(location, fromdate, todate);
				double todaydisc = pharmacyDAO.getTodayDisc(location, fromdate, todate);
				double chequepayment = pharmacyDAO.getChequePayment(location, fromdate, todate);
				double neftpayment = pharmacyDAO.getNeftPayment(location, fromdate, todate);
				double cashReturn = pharmacyDAO.getReturnToday(location, fromdate, todate);
				double creditReturn = pharmacyDAO.getCreditReturn(location, fromdate, todate);
				double hospitalReturn = pharmacyDAO.getHospitalReturn(location, fromdate, todate);
				double totalhospital = pharmacyDAO.getTotalHospital(location, fromdate, todate);
				double totalreturn = cashReturn;
				double plus = todaycash + todaycard + chequepayment + neftpayment;
				double minus = todaydisc;
				double totalClosing = plus - minus;
				misChartForm.setCreditReturn(DateTimeUtils.changeFormat(creditReturn));
				misChartForm.setTotalcredit(DateTimeUtils.changeFormat(totalCredit));
				misChartForm.setHospitalReturn(DateTimeUtils.changeFormat(hospitalReturn));
				misChartForm.setTodaycard(DateTimeUtils.changeFormat(todaycard));
				misChartForm.setTodaycash(DateTimeUtils.changeFormat(todaycash));
				misChartForm.setTodaydisc(DateTimeUtils.changeFormat(todaydisc));
				misChartForm.setTodayReturn(DateTimeUtils.changeFormat(cashReturn));
				misChartForm.setChequepayments(DateTimeUtils.changeFormat(chequepayment));
				misChartForm.setNeftpayment(DateTimeUtils.changeFormat(neftpayment));
				misChartForm.setTotalpayment(DateTimeUtils.changeFormat(totalCollection));
				misChartForm.setBalance(DateTimeUtils.changeFormat(totalBalance));
				misChartForm.setTotal(DateTimeUtils.changeFormat(totalClosing));
				misChartForm.setHospital(DateTimeUtils.changeFormat(totalhospital));
				Priscription priscription = new Priscription();
				priscription.setCreditReturn(DateTimeUtils.changeFormat(creditReturn));
				priscription.setTotalcredit(DateTimeUtils.changeFormat(totalCredit));
				priscription.setHospitalReturn(DateTimeUtils.changeFormat(hospitalReturn));
				priscription.setTodaycard(DateTimeUtils.changeFormat(todaycard));
				priscription.setTodaycash(DateTimeUtils.changeFormat(todaycash));
				priscription.setTodaydisc(DateTimeUtils.changeFormat(todaydisc));
				priscription.setTodayReturn(DateTimeUtils.changeFormat(cashReturn));
				priscription.setChequepayments(DateTimeUtils.changeFormat(chequepayment));
				priscription.setNeftpayment(DateTimeUtils.changeFormat(neftpayment));
				priscription.setTotalpayment(DateTimeUtils.changeFormat(totalCollection));
				priscription.setBalance(DateTimeUtils.changeFormat(totalBalance));
				priscription.setTotal(totalClosing);
				priscription.setHospital(DateTimeUtils.changeFormat(totalhospital));
				priscription.setFromdate(DateTimeUtils.getCommencingDate1(fromdate));
				priscription.setTodate(DateTimeUtils.getCommencingDate1(todate));
				pharmacydailysalelist.add(priscription);
				locationListPharmacy = pharmacyDAO.getAllLocation();
				misChartForm.setPharmacy_location("1");
				misChartForm.setFilter_location(location);
			}

			if (action.equals("investigation")) {
				// @ 
				investigationlist = misChartDAO.getInvestigationDetails(fromdate, todate, filter_ward, loginInfo);
				int size = investigationlist.size();
				if (size > 0) {
					int new_invistigation = investigationlist.get(size - 1).getNew_invistigation();
					int new_collected = investigationlist.get(size - 1).getNew_collected();
					int new_completed = investigationlist.get(size - 1).getNew_completed();
					int new_aproved = investigationlist.get(size - 1).getNew_aproved();
					int deleted_investigation = investigationlist.get(size - 1).getDeleted_investigation();
					int totalrev=misChartDAO.getInvestigationRevenue(fromdate,todate);
					misChartForm.setNew_invistigation(new_invistigation);
					misChartForm.setNew_collected(new_collected);
					misChartForm.setNew_completed(new_completed);
					misChartForm.setNew_aproved(new_aproved);
					misChartForm.setTotalinvrevenue(totalrev);
					int total = new_invistigation + new_collected + new_completed + new_aproved+deleted_investigation;
					misChartForm.setTotalInvestigation("" + total);
					misChartForm.setDeleted_investigation(deleted_investigation);
				} else {
					misChartForm.setNew_invistigation(0);
					misChartForm.setNew_collected(0);
					misChartForm.setNew_completed(0);
					misChartForm.setNew_aproved(0);
					misChartForm.setTotalInvestigation("0");
					misChartForm.setDeleted_investigation(0);
				}
			}

			if (action.equals("prescriptionsummary")) {

				// Prescription list Report
				PrescriptionDAO prescriptionDAO = new JDBCPrescriptionDAO(connection);
				prescriptionList = prescriptionDAO.getAllPriscriptionListWithoutPagination(fromdate, todate);

				misChartForm.setPriscriptionReport(prescriptionList);

				// Prescription data
				int totalcount = prescriptionDAO.getAllPriscriptionCountWithoutLocation(fromdate, todate);
				int totaldelivered = prescriptionDAO.getAllPriscriptionCountDelivered(fromdate, todate);
				int totalnotdelivered = prescriptionDAO.getAllPriscriptionCountNotDelivered(fromdate, todate);
				misChartForm.setTotalprescription(totalcount);
				misChartForm.setDelivered(totaldelivered);
				misChartForm.setNotdelivered(totalnotdelivered);
			}

			if (action.equals("procurementsummary")) {
				String loc = "0";
				Product product = misChartDAO.getAllTotalProcurment(fromdate, todate, loc);
				misChartForm.setGrnwithoutpo(product.getGrnwithoutpo());
				misChartForm.setGrnwithpo(product.getGrnwithpo());
				misChartForm.setTotalgrn(product.getTotalgrn());
			}

			if (action.equals("patientviewpackage")) {

				patientViewByPackage = misChartDAO.getPatientViewByPackage(fromdate, todate);
				Accounts act = misChartDAO.getCashCheque(fromdate, todate);

				misChartForm.setPaymodecash(act.getPaymodecash());
				misChartForm.setPaymodecheque(act.getPaymodecheque());

			}

			if (action.equals("indentsummary")) {
				String loc = "0";
				Product product = misChartDAO.getAllTotalIndent(fromdate, todate, loc);
				misChartForm.setDirect_transfer(product.getDirect_transfer());
				misChartForm.setRequest(product.getRequest());
				misChartForm.setApproved(product.getApproved());
				misChartForm.setRejected(product.getRejected());
				misChartForm.setTransfer(product.getTransfer());
				misChartForm.setReceived(product.getReceived());
				misChartForm.setPocreated(product.getPocreated());
				misChartForm.setPending(product.getPending());
				misChartForm.setReadytotransfer(product.getReadytotransfer());
				misChartForm.setReturned(product.getReturned());
				misChartForm.setTotalindent(product.getTotalindent());
			}

			if (action.equals("kpireports")) {
				ClientReportDAO clientReportDAO = new JDBCClientReportDAO(connection);
				kpiarealist = clientReportDAO.getKPIAreaList("1");
				misChartForm.setKpiarealist(kpiarealist);
				misChartForm.setIsKPI("1");
				kpilist = misChartDAO.getAllKPIList(kpiarea_filter, year_filter, month_filter);
				misChartForm.setKpiarea_filter(kpiarea_filter);
				misChartForm.setYear_filter(year_filter);
				misChartForm.setMonth_filter(month_filter);
				ArrayList<MisReport> graphicalkpilist = misChartDAO.getAllGrahicalKPIList(kpiarea_filter, year_filter, month_filter);
				session.setAttribute("graphicalkpilist", graphicalkpilist);
				String kpimis = misChartForm.getKpimis();
				if(kpimis!=null){
					flag=true;
				}
				nabhsubcatagoylist = misChartDAO.getNABHSubCatList("2");
				
				String kpicat=misChartForm.getNabhsubcatagory();
				String satyear=misChartForm.getSat_year_filter();
				String satmonth=misChartForm.getSat_month_filter();
				
				/*if (kpicat!= null) {
					if(kpicat.equals("")){
						kpicat = null;
					}
				}*/
				
				if (kpicat== null) {
					kpicat ="2";
				}else if(kpicat.equals("")){
					kpicat = "2";
				}
				
				if (satyear == null) {
					DateFormat dateFormat = new SimpleDateFormat("yyyy");
					Calendar cal = Calendar.getInstance(); 
					String todateyear = dateFormat.format(cal.getTime());
					
					DateFormat dateFormat1 = new SimpleDateFormat("MM");
					Calendar cal1 = Calendar.getInstance(); 
					String tomonth = dateFormat1.format(cal1.getTime());
					
					if(Integer.parseInt(tomonth)>=4){
						satyear = todateyear+"-"+(Integer.parseInt(todateyear)+1);
					}else{
						satyear = (Integer.parseInt(todateyear)-1)+"-"+todateyear;
					}
					//satyear = "2017-2018";
				} else if (satyear.equals("")) {
					DateFormat dateFormat = new SimpleDateFormat("yyyy");
					Calendar cal = Calendar.getInstance(); 
					String todateyear = dateFormat.format(cal.getTime());
					
					DateFormat dateFormat1 = new SimpleDateFormat("MM");
					Calendar cal1 = Calendar.getInstance(); 
					String tomonth = dateFormat1.format(cal1.getTime());
					
					if(Integer.parseInt(tomonth)>=4){
						satyear = todateyear+"-"+(Integer.parseInt(todateyear)+1);
					}else{
						satyear = (Integer.parseInt(todateyear)-1)+"-"+todateyear;
					}
					//satyear = "2017-2018";
				}

				if (satmonth == null) {
					satmonth = "01";
				} else if (month_filter.equals("")) {
					satmonth = "01";
				}
				selfassementtoollist = misChartDAO.getAllSelfAssessmentToolList(kpicat,satyear,satmonth); 
				misChartForm.setSat_month_filter(satmonth);
				misChartForm.setSat_year_filter(satyear);
				misChartForm.setNabhsubcatagory(kpicat);
			}
			misChartForm.setOpdappointmenttype(opdappointmenttype);
			misChartForm.setSelfassementtoollist(selfassementtoollist);
			misChartForm.setNabhsubcatagoylist(nabhsubcatagoylist);
			misChartForm.setKpilist(kpilist);
			misChartForm.setKpiarealist(kpiarealist);
			misChartForm.setLocationListPharmacy(locationListPharmacy);
			misChartForm.setPatientbylocation(patientbylocation);
			misChartForm.setOpdPatientReport(opdPatientReport);
			misChartForm.setClinicalViewList(clinicalViewList);
			misChartForm.setBedlist(bedlist);
			misChartForm.setPatientbycondition(patientbycondition);
			misChartForm.setAccountinfo1(accountinfo1);
			misChartForm.setAccountinfo(accountinfo);
			misChartForm.setExpenseList(expenseList);
			misChartForm.setAdvancedRefundList(advancedRefundList);
			misChartForm.setPharmacydailysalelist(pharmacydailysalelist);
			misChartForm.setAction(action);
			misChartForm.setDailySummaryList(dailySummaryList);
			misChartForm.setDailySummaryList1(dailySummaryList1);
			misChartForm.setFilter_ward(filter_ward);
			misChartForm.setPatientviewbypackage(patientViewByPackage);
			misChartForm.setInvestigationlist(investigationlist);
			session.setAttribute("misChartForm", misChartForm);
			session.setAttribute("graphaction", action);
			
			Clinic clinic = new Clinic();
			ClinicDAO clinicDAO = new JDBCClinicDAO(connection);
			AccountsDAO accountsDAO = new JDBCAccountsDAO(connection);
			ArrayList<Clinic> locationAdressList = accountsDAO.getLocationAddress(loginInfo.getClinicid());
			clinic = clinicDAO.getCliniclistDetails(loginInfo.getClinicid());
			misChartForm.setClinicName(clinic.getClinicName());
			misChartForm.setClinicOwner(clinic.getClinicOwner());
			misChartForm.setOwner_qualification(clinic.getOwner_qualification());
			misChartForm.setLocationAdressList(locationAdressList);
			Clinic ccbg =  locationAdressList.get(0);
			session.setAttribute("balgopaladdress", ccbg.getAddress());
			session.setAttribute("balgopalcname", ccbg.getClinicName());
			misChartForm.setAddress(clinic.getAddress());
			misChartForm.setLandLine(clinic.getLandLine());
			misChartForm.setClinicemail(clinic.getEmail());
			misChartForm.setWebsiteUrl(clinic.getWebsiteUrl());
			misChartForm.setClinicLogo(clinic.getUserImageFileName());


		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			
			connection.close();
		}
		// getgraph(action, fromdate, todate);

//		System.out.println(fromdate);
		if(!flag){
			return "success";
		}else{
			return "kpireports";
		}
		
	}

	private String getgraph(String action, String fromdate, String todate) throws Exception {

		Connection connection = null;
		try {
			connection = Connection_provider.getconnection();
			MisChartDAO misChartDAO = new JDBCMisChartDAO(connection);
			/*
			 * if(action==null){ action = (String)
			 * session.getAttribute("graphaction"); }
			 */

			String graphName = misChartForm.getGraphName();
			session.setAttribute("graphName", graphName);

			if (action == null) {
				action = "yearly";
			}

			if (action.equals("opd")) {
				int totalOpdPatient = misChartDAO.getTotalOpdPatient(fromdate, todate);
				int bookedAppointment = misChartDAO.getBookedAppointment(fromdate, todate);
				int totaldna = misChartDAO.getTotalDNA(fromdate, todate);
				int totalCompleted = misChartDAO.getTotalCompleted(fromdate, todate);

				misChartForm.setTotalOpdPatient(totalOpdPatient);
				misChartForm.setBookedAppointment(bookedAppointment);
				misChartForm.setTotaldna(totaldna);
				misChartForm.setTotalCompleted(totalCompleted);

			}
			if (action.equals("ipd")) {
				int newadmission = misChartDAO.getIpdNewAdmission(todate, todate);
				int inhousepatients = misChartDAO.getInHousePatients(fromdate, todate);
				int dischargepatients = misChartDAO.getDischargePatients(fromdate, todate);

				misChartForm.setNewadmission(newadmission);
				misChartForm.setInhousepatients(inhousepatients);
				misChartForm.setDischargepatients(dischargepatients);
			}

			if (action.equals("bedstatus")) {
				int totalbeds = misChartDAO.getTotalBeds(fromdate, todate);
				int undermaintnancebed = misChartDAO.getUnderMaintnanceBed(fromdate, todate);
				int occupiedbed = misChartDAO.getOccupiedBed(fromdate, todate);
				int availablebed = misChartDAO.getAvailableBed(fromdate, todate);
				availablebed = availablebed - occupiedbed;

				misChartForm.setTotalbeds(totalbeds);
				misChartForm.setUndermaintnancebed(undermaintnancebed);
				misChartForm.setOccupiedbed(occupiedbed);
				misChartForm.setAvailablebed(availablebed);
			}

			if (action.equals("invoice")) {
				int chargeaddedd = misChartDAO.getChargeAddedd(fromdate, todate,"");
				int invoicegenerated = misChartDAO.getInvoiceGenerated(fromdate, todate,
						misChartForm.getInvoicecategory(), loginInfo.getJobTitle(),"");
				int paymentrecieved = misChartDAO.getPaymentrecieved(fromdate, todate,
						misChartForm.getInvoicecategory(), loginInfo.getJobTitle(),"");

				misChartForm.setChargeaddedd(chargeaddedd);
				misChartForm.setInvoicegenerated(invoicegenerated);
				misChartForm.setPaymentrecieved(paymentrecieved);

				ChargesReportDAO chargesReportDAO = new JDBCChargesReportDAO(connection);
				ArrayList<Accounts> invoiceList = new ArrayList<Accounts>();
				invoiceList = chargesReportDAO.getInvoiceReportList(fromdate, todate, Constants.PAY_BY_CLIENT, null,
						"0", "firstname", "ASC", misChartForm.getInvoicecategory(),"0",loginInfo);
				misChartForm.setInvoiceList(invoiceList);

			}

			if (action.equals("paymentmode")) {
				int totalPayment = misChartDAO.getTotalPayment(fromdate, todate, misChartForm.getInvoicecategory(),
						loginInfo.getJobTitle());
				int cashpayment = misChartDAO.getCashPayment(fromdate, todate, misChartForm.getInvoicecategory(),
						loginInfo.getJobTitle());
				int chequepayment = misChartDAO.getChequepaymenyt(fromdate, todate, misChartForm.getInvoicecategory(),
						loginInfo.getJobTitle());
				int cardPayment = misChartDAO.getCardpayment(fromdate, todate, misChartForm.getInvoicecategory(),
						loginInfo.getJobTitle());

				misChartForm.setTotalPayment(totalPayment);
				misChartForm.setCashpayment(cashpayment);
				misChartForm.setChequepayment(chequepayment);
				misChartForm.setCardPayment(cardPayment);

			}
			if (action.equals("advref")) {
				int totalPayment = misChartDAO.getTotalPayment(fromdate, todate, misChartForm.getInvoicecategory(),
						loginInfo.getJobTitle());
				int cashpayment = misChartDAO.getCashPayment(fromdate, todate, misChartForm.getInvoicecategory(),
						loginInfo.getJobTitle());
				int chequepayment = misChartDAO.getChequepaymenyt(fromdate, todate, misChartForm.getInvoicecategory(),
						loginInfo.getJobTitle());
				int cardPayment = misChartDAO.getCardpayment(fromdate, todate, misChartForm.getInvoicecategory(),
						loginInfo.getJobTitle());

				misChartForm.setTotalPayment(totalPayment);
				misChartForm.setCashpayment(cashpayment);
				misChartForm.setChequepayment(chequepayment);
				misChartForm.setCardPayment(cardPayment);

			}
			if (action.equals("accountinfo")) {
				int totalPayment = misChartDAO.getTotalPayment(fromdate, todate, misChartForm.getInvoicecategory(),
						loginInfo.getJobTitle());
				int cashpayment = misChartDAO.getCashPayment(fromdate, todate, misChartForm.getInvoicecategory(),
						loginInfo.getJobTitle());
				int chequepayment = misChartDAO.getChequepaymenyt(fromdate, todate, misChartForm.getInvoicecategory(),
						loginInfo.getJobTitle());
				int cardPayment = misChartDAO.getCardpayment(fromdate, todate, misChartForm.getInvoicecategory(),
						loginInfo.getJobTitle());

				misChartForm.setTotalPayment(totalPayment);
				misChartForm.setCashpayment(cashpayment);
				misChartForm.setChequepayment(chequepayment);
				misChartForm.setCardPayment(cardPayment);

			}

			if (action.equals("expense")) {
				ArrayList<Expence> expenseList = misChartDAO.getExpenceCategoryList(fromdate, todate);
				ArrayList<Expence> paymenmodeList = misChartDAO.getPaymentModeList(fromdate, todate);
				expenseList.addAll(paymenmodeList);
				misChartForm.setExpenseList(expenseList);

			}

			if (action.equals("dailysummary")) {
				int totalopdseen = misChartDAO.getBookedAppointment(fromdate, todate);
				int ipdnewadmission = misChartDAO.getIpdNewAdmission(todate, todate);

				DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
				Calendar cal = Calendar.getInstance();
				cal.add(Calendar.DATE, -1);
				todate = dateFormat.format(cal.getTime());
				todate = DateTimeUtils.getCommencingDate1(todate);

				int ipdOldPatient = misChartDAO.getInHousePatients(fromdate, todate);

				misChartForm.setTotalopdseen(totalopdseen);
				misChartForm.setIpdnewadmission(ipdnewadmission);
				misChartForm.setIpdOldPatient(ipdOldPatient);

			}

			if (action.equals("clinicalview")) {
				ArrayList<Client> patientbycondition = misChartDAO.getPaticlentByCondiion(fromdate, todate);
				int requestedprescription = misChartDAO.getRequestedPrescription(fromdate, todate);
				int requestedInvestigation = misChartDAO.getRequestedInvestigation(fromdate, todate);

				misChartForm.setPatientbycondition(patientbycondition);
				misChartForm.setRequestedprescription(requestedprescription);
				misChartForm.setRequestedInvestigation(requestedInvestigation);
			}

			if (action.equals("patientview")) {
				ArrayList<Client> patientbylocation = misChartDAO.getpatientbyLocation(fromdate, todate);
				misChartForm.setPatientbylocation(patientbylocation);
				int totalOpdPatient = misChartDAO.getTotalOpdPatient(fromdate, todate);
				misChartForm.setTotalOpdPatient(totalOpdPatient);
			}
			misChartForm.setAction(action);

			session.setAttribute("misChartForm", misChartForm);
			session.setAttribute("graphaction", action);

		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			
			connection.close();
		}

		return null;
	}

	public String graph() throws Exception {
		if (!verifyLogin(request)) {
			return "login";
		}

		String fromdate = misChartForm.getFromDate();
		String todate = misChartForm.getToDate();
		String action = request.getParameter("action");
		if (action != null) {
			session.setAttribute("graphaction", action);
			String graphName = request.getParameter("name");
			session.setAttribute("graphName", graphName);
		}

		if (fromdate.equals("")) {
			fromdate = (String) session.getAttribute("misfromdate");
			misChartForm.setFromDate(fromdate);
		}

		if (todate.equals("")) {
			todate = (String) session.getAttribute("mistodate");
			misChartForm.setToDate(todate);
		}

		fromdate = DateTimeUtils.getCommencingDate1(fromdate);
		todate = DateTimeUtils.getCommencingDate1(todate);

		Connection connection = null;
		try {
			connection = Connection_provider.getconnection();
			MisChartDAO misChartDAO = new JDBCMisChartDAO(connection);

			// opd patient data
			if (action == null) {
				action = (String) session.getAttribute("graphaction");
			}
			if (action.equals("opd")) {
				int totalOpdPatient = misChartDAO.getTotalOpdPatient(fromdate, todate);
				int bookedAppointment = misChartDAO.getBookedAppointment(fromdate, todate);
				int totaldna = misChartDAO.getTotalDNA(fromdate, todate);
				int totalCompleted = misChartDAO.getTotalCompleted(fromdate, todate);

				misChartForm.setTotalOpdPatient(totalOpdPatient);
				misChartForm.setBookedAppointment(bookedAppointment);
				misChartForm.setTotaldna(totaldna);
				misChartForm.setTotalCompleted(totalCompleted);

			}
			if (action.equals("ipd")) {
				int newadmission = misChartDAO.getIpdNewAdmission(todate, todate);
				int inhousepatients = misChartDAO.getInHousePatients(fromdate, todate);
				int dischargepatients = misChartDAO.getDischargePatients(fromdate, todate);

				misChartForm.setNewadmission(newadmission);
				misChartForm.setInhousepatients(inhousepatients);
				misChartForm.setDischargepatients(dischargepatients);
			}

			if (action.equals("bedstatus")) {
				int totalbeds = misChartDAO.getTotalBeds(fromdate, todate);
				int undermaintnancebed = misChartDAO.getUnderMaintnanceBed(fromdate, todate);
				int occupiedbed = misChartDAO.getOccupiedBed(fromdate, todate);
				int availablebed = misChartDAO.getAvailableBed(fromdate, todate);
				availablebed = availablebed - occupiedbed;

				misChartForm.setTotalbeds(totalbeds);
				misChartForm.setUndermaintnancebed(undermaintnancebed);
				misChartForm.setOccupiedbed(occupiedbed);
				misChartForm.setAvailablebed(availablebed);
			}

			if (action.equals("invoice")) {
				int chargeaddedd = misChartDAO.getChargeAddedd(fromdate, todate,"");
				int invoicegenerated = misChartDAO.getInvoiceGenerated(fromdate, todate,
						misChartForm.getInvoicecategory(), loginInfo.getJobTitle(),"");
				int paymentrecieved = misChartDAO.getPaymentrecieved(fromdate, todate,
						misChartForm.getInvoicecategory(), loginInfo.getJobTitle(),"");

				misChartForm.setChargeaddedd(chargeaddedd);
				misChartForm.setInvoicegenerated(invoicegenerated);
				misChartForm.setPaymentrecieved(paymentrecieved);

				// Charges Report

				ChargesReportDAO chargesReportDAO = new JDBCChargesReportDAO(connection);
				ArrayList<Accounts> invoiceList = new ArrayList<Accounts>();
				invoiceList = chargesReportDAO.getInvoiceReportList(fromdate, todate, Constants.PAY_BY_CLIENT, null,
						"0", "firstname", "ASC", misChartForm.getInvoicecategory(),"0",loginInfo);
				misChartForm.setInvoiceList(invoiceList);

			}

			if (action.equals("paymentmode")) {
				int totalPayment = misChartDAO.getTotalPayment(fromdate, todate, misChartForm.getInvoicecategory(),
						loginInfo.getJobTitle());
				int cashpayment = misChartDAO.getCashPayment(fromdate, todate, misChartForm.getInvoicecategory(),
						loginInfo.getJobTitle());
				int chequepayment = misChartDAO.getChequepaymenyt(fromdate, todate, misChartForm.getInvoicecategory(),
						loginInfo.getJobTitle());
				int cardPayment = misChartDAO.getCardpayment(fromdate, todate, misChartForm.getInvoicecategory(),
						loginInfo.getJobTitle());

				misChartForm.setTotalPayment(totalPayment);
				misChartForm.setCashpayment(cashpayment);
				misChartForm.setChequepayment(chequepayment);
				misChartForm.setCardPayment(cardPayment);

			}

			if (action.equals("expense")) {
				ArrayList<Expence> expenseList = misChartDAO.getExpenceCategoryList(fromdate, todate);
				ArrayList<Expence> paymenmodeList = misChartDAO.getPaymentModeList(fromdate, todate);
				expenseList.addAll(paymenmodeList);
				misChartForm.setExpenseList(expenseList);

			}

			if (action.equals("dailysummary")) {
				int totalopdseen = misChartDAO.getBookedAppointment(fromdate, todate);
				int ipdnewadmission = misChartDAO.getIpdNewAdmission(todate, todate);

				DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
				Calendar cal = Calendar.getInstance();
				cal.add(Calendar.DATE, -1);
				todate = dateFormat.format(cal.getTime());
				todate = DateTimeUtils.getCommencingDate1(todate);

				int ipdOldPatient = misChartDAO.getInHousePatients(fromdate, todate);

				misChartForm.setTotalopdseen(totalopdseen);
				misChartForm.setIpdnewadmission(ipdnewadmission);
				misChartForm.setIpdOldPatient(ipdOldPatient);

			}

			if (action.equals("clinicalview")) {
				ArrayList<Client> patientbycondition = misChartDAO.getPaticlentByCondiion(fromdate, todate);
				int requestedprescription = misChartDAO.getRequestedPrescription(fromdate, todate);
				int requestedInvestigation = misChartDAO.getRequestedInvestigation(fromdate, todate);

				misChartForm.setPatientbycondition(patientbycondition);
				misChartForm.setRequestedprescription(requestedprescription);
				misChartForm.setRequestedInvestigation(requestedInvestigation);
			}

			if (action.equals("patientview")) {
				ArrayList<Client> patientbylocation = misChartDAO.getpatientbyLocation(fromdate, todate);
				misChartForm.setPatientbylocation(patientbylocation);
				int totalOpdPatient = misChartDAO.getTotalOpdPatient(fromdate, todate);
				misChartForm.setTotalOpdPatient(totalOpdPatient);
			}

			session.setAttribute("misChartForm", misChartForm);

		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			
			connection.close();
		}

		return "graph";
	}

	public MisChartForm getModel() {
		// TODO Auto-generated method stub
		return misChartForm;
	}

	public String calculatekpi() throws Exception {
		Connection connection=null;
		try {
			connection=Connection_provider.getconnection();
			MisChartDAO misChartDAO = new JDBCMisChartDAO(connection);
			JDBCClientReportDAO clientReportDAO = new JDBCClientReportDAO(connection);
			String kpiid = request.getParameter("kpiid");
			String input1 = request.getParameter("val1");
			String input2 = request.getParameter("val2");
			String input3 = request.getParameter("val3");
			String input4 = request.getParameter("val4");
			String input5 = request.getParameter("val5");
			String indicatorid = request.getParameter("indicatorid");
			int res = misChartDAO.updateKPITempTable(input1,input2,input3,input4,input5);
			//Report report = clientReportDAO.getMasterIndicator(indicatorid);
			MisReport misReport = misChartDAO.getKPIMasterData(kpiid);
			String result = misChartDAO.getCalKPIResult(misReport);
			response.setContentType("text/html");
			response.setHeader("Cache-Control", "no-cache");
			response.getWriter().write(""+result+""); 	
			
		} catch (Exception e) {

			e.printStackTrace();
		} finally{
			
			connection.close();
		}
		return null;
	}
	
	public String savekpiresult() throws Exception {
		Connection connection=null;
		try {
			connection=Connection_provider.getconnection();
			MisChartDAO misChartDAO = new JDBCMisChartDAO(connection);
			JDBCClientReportDAO clientReportDAO = new JDBCClientReportDAO(connection);
			String kpiid = request.getParameter("kpiid");
			String input1 = request.getParameter("val1");
			String input2 = request.getParameter("val2");
			String input3 = request.getParameter("val3");
			String input4 = request.getParameter("val4");
			String input5 = request.getParameter("val5");
			String indicatorid = request.getParameter("indicatorid");
			String final_result = request.getParameter("result");
			String kpi_year = request.getParameter("kpi_year");
			String kpi_month = request.getParameter("kpi_month");
			String kpi_dataid = request.getParameter("kpi_dataid");
			String target = request.getParameter("target");
			if(input1.equals("undefined")){
				input1 = null;
			}else if(input2.equals("undefined")){
				input2 = null;
			}else if(input3.equals("undefined")){
				input3 = null;
			}else if(input4.equals("undefined")){
				input4 = null;
			}else if(input5.equals("undefined")){
				input5 = null;
			}
			
			DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Calendar cal = Calendar.getInstance();
			String todate = dateFormat.format(cal.getTime());   
			
			//Boolean flag = misChartDAO.checkKPIStatus(kpiid,kpi_year,kpi_month);
			String userid = loginInfo.getUserId();
			
			/*if(flag){
				int result = misChartDAO.updateKPIData(input1,input2,input3,input4,input5,final_result,kpiid,todate,userid);
			}else{
				Report report = clientReportDAO.getMasterIndicator(indicatorid);
				int result = misChartDAO.insertNewKPIRecord(input1,input2,input3,input4,input5,final_result,indicatorid,report,kpi_year,kpi_month);
			}*/
			if(kpi_dataid.equals("0")){
				//Report report = clientReportDAO.getMasterIndicator(indicatorid);
				MisReport misReport = misChartDAO.getKPIMasterData(kpiid);
				misReport.setUserid(userid);
				misReport.setDatetime(todate);
				int result = misChartDAO.insertNewKPIRecord(input1,input2,input3,input4,input5,final_result,indicatorid,misReport,kpi_year,kpi_month,target,null);
			}else{
				int result = misChartDAO.updateKPIData(input1,input2,input3,input4,input5,final_result,kpiid,todate,userid,kpi_dataid,target,null);
			}
			
			/*int res = misChartDAO.updateKPITempTable(input1,input2,input3,input4,input5);
			Report report = clientReportDAO.getMasterIndicator(indicatorid);
			String result = misChartDAO.getCalKPIResult(report);*/
			
			response.setContentType("text/html");
			response.setHeader("Cache-Control", "no-cache");
			response.getWriter().write(""); 	
			
		} catch (Exception e) {

			e.printStackTrace();
		} finally{
			
			connection.close();
		}
		return null;
	}
	
	public void startDataAddingProccessToKPI(String funcName,String year_range, String month,String kpiId){
		try {
			Connection connection=null;
			connection=Connection_provider.getconnection();
			MisChartDAO misChartDAO = new JDBCMisChartDAO(connection);
			String frmDate="",toDate="";
			//month 09 yraer 2020-2021
			int monthNumber=DateTimeUtils.convertToInteger(month);
			String maxYear=year_range.split("-")[1],minYear=year_range.split("-")[0];
			
			if(monthNumber>3){
				frmDate="01-"+month+"-"+minYear;
				toDate="31-"+month+"-"+minYear;
			}else{
				frmDate="01-"+month+"-"+maxYear;
				toDate="31-"+month+"-"+maxYear;
			}
			
			MisReport misReport= new MisReport();
			misReport.setUserid(loginInfo.getUserId());
			misReport.setDatetime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
			misReport.setKpiid(kpiId);
			
			if("redosoftest".equals(funcName)){
				int reDosOfTest=misChartDAO.countOfReDosOfLabTests(frmDate, toDate);
				int totalCountOfTest=misChartDAO.countOfOfLabTests(frmDate, toDate);
				double percentageOfReDos=((double)reDosOfTest/(double)totalCountOfTest)*100.0;
				percentageOfReDos=Math.round((percentageOfReDos*100.0))/100.0;
				boolean flag1 = misChartDAO.iskpiSyytemGen(kpiId); 
				if(flag1){
					int flag = misChartDAO.checkKPIAvailable(DateTimeUtils.convertToInteger(kpiId),year_range,month);
					if(flag==0){
						misChartDAO.insertNewKPIRecord(""+reDosOfTest, ""+totalCountOfTest, "0", "0", "0", ""+percentageOfReDos, "", misReport, year_range, month, "0", "");
						}else{
							misChartDAO.updateKPIData(""+reDosOfTest, ""+totalCountOfTest, "0", "0", "0", ""+percentageOfReDos, kpiId, misReport.getDatetime(), misReport.getUserid(), ""+flag, "", "");
						}
					}
				
			}else if("reschudled_ot".equals(funcName)){
				double percentageOfReschOT=misChartDAO.getRescheduledOTPercentage(frmDate, toDate);
				int reschOts=misChartDAO.getTotalRescheduledOTs(frmDate, toDate);
				int totalOts=misChartDAO.getTotalOTs(frmDate, toDate);
				boolean flag1 = misChartDAO.iskpiSyytemGen(kpiId); 
				if(flag1){
					int flag = misChartDAO.checkKPIAvailable(DateTimeUtils.convertToInteger(kpiId),year_range,month);
					if(flag==0){
						misChartDAO.insertNewKPIRecord(""+reschOts, ""+totalOts, "0", "0", "0", ""+percentageOfReschOT, "", misReport, year_range, month, "0", "");
						}else{
							misChartDAO.updateKPIData(""+reschOts, ""+totalOts, "0", "0", "0", ""+percentageOfReschOT, kpiId, misReport.getDatetime(), misReport.getUserid(), ""+flag, "", "");
						}
					}
				
			}
			
			else if("icu_utilization_rate".equals(funcName)){
				/*double percentageOfReschOT=misChartDAO.getRescheduledOTPercentage(frmDate, toDate);
				int reschOts=misChartDAO.getTotalRescheduledOTs(frmDate, toDate);
				int totalOts=misChartDAO.getTotalOTs(frmDate, toDate);*/
				
				int inpatientCountICU=misChartDAO.getICUInpatientCount(frmDate, toDate);
				int bedCountICU=misChartDAO.getICUBedCount();
				double utilizationRate=((double)inpatientCountICU/(double)bedCountICU)*100.0;
				utilizationRate=Math.round((utilizationRate*100.0))/100.0;
				boolean flag1 = misChartDAO.iskpiSyytemGen(kpiId); 
				if(flag1){
					int flag = misChartDAO.checkKPIAvailable(DateTimeUtils.convertToInteger(kpiId),year_range,month);
					if(flag==0){
						misChartDAO.insertNewKPIRecord(""+inpatientCountICU, ""+bedCountICU, "0", "0", "0", ""+utilizationRate, "", misReport, year_range, month, "0", "");
						}else{
							misChartDAO.updateKPIData(""+inpatientCountICU, ""+bedCountICU, "0", "0", "0", ""+utilizationRate, kpiId, misReport.getDatetime(), misReport.getUserid(), ""+flag, "", "");
						}
					}
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public String getKPIExcelData() throws Exception {
		Connection connection=null;
		try {
			connection=Connection_provider.getconnection();
			MisChartDAO misChartDAO = new JDBCMisChartDAO(connection);
			String kpiid = request.getParameter("kpiid");
			String kpimonth = request.getParameter("kpi_month");
			String year_filter = request.getParameter("year_filter");
			StringBuffer buffer = new StringBuffer();
			
			String funcName=misChartDAO.getFunctionNameOfIndicator(kpiid);
			startDataAddingProccessToKPI(funcName, year_filter, kpimonth,kpiid);
			
			MisReport misReport = misChartDAO.getIndiKPIData1(kpiid,kpimonth,year_filter);
			int i=0;
			
			
			
			
			
			if(misReport.getFname()==null){
				buffer.append("<tr>");
				buffer.append("</tr>");
				buffer.append("~");
				buffer.append("<tr>");
				buffer.append("</tr>");
			}else if(misReport.getFname().equals("")){
				buffer.append("<tr>");
				buffer.append("</tr>");
				buffer.append("~");
				buffer.append("<tr>");
				buffer.append("</tr>");
			}else if(misReport.getFname().equals("timetakenfordischarge")){
				buffer.append("<tr>");
				buffer.append("<th>KPI Area</th>");
				buffer.append("<th>Indicator</th>");
				buffer.append("<th>KPI Year</th>");
				buffer.append("<th>KPI Month</th>");
				buffer.append("<th>Patient name</th>");
				buffer.append("<th>Time Taken</th>");
				buffer.append("</tr>");
				buffer.append("~");
				for (MisReport misReport2 : misReport.getDatalist()) {
					if(i==0){
						buffer.append("<tr>");
						buffer.append("<td>"+misReport.getKpiarea()+"</td>");
						buffer.append("<td>"+misReport.getKpiindicator()+"</td>");
						buffer.append("<td>"+misReport.getKpi_year()+"</td>");
						buffer.append("<td>"+misReport.getKpi_month()+"</td>");
						buffer.append("<td>"+misReport2.getClientname()+"</td>");
						buffer.append("<td>"+misReport2.getAverage()+"</td>");
						buffer.append("</tr>");
					}else{
						buffer.append("<tr>");
						buffer.append("<td></td>");
						buffer.append("<td></td>");
						buffer.append("<td></td>");
						buffer.append("<td></td>");
						buffer.append("<td>"+misReport2.getClientname()+"</td>");
						buffer.append("<td>"+misReport2.getAverage()+"min</td>");
						buffer.append("</tr>");
					}
					i++;
					
				}
				
			}else if(misReport.getFname().equals("mortality")){
				buffer.append("<tr>");
				buffer.append("<th>KPI Area</th>");
				buffer.append("<th>Indicator</th>");
				buffer.append("<th>KPI Year</th>");
				buffer.append("<th>KPI Month</th>");
				buffer.append("<th>Patient name</th>");
				buffer.append("</tr>");
				buffer.append("~");
				for (MisReport misReport2 : misReport.getDatalist()) {
					if(i==0){
						buffer.append("<tr>");
						buffer.append("<td>"+misReport.getKpiarea()+"</td>");
						buffer.append("<td>"+misReport.getKpiindicator()+"</td>");
						buffer.append("<td>"+misReport.getKpi_year()+"</td>");
						buffer.append("<td>"+misReport.getKpi_month()+"</td>");
						buffer.append("<td>"+misReport2.getClientname()+"</td>");
						buffer.append("</tr>");
					}else{
						buffer.append("<tr>");
						buffer.append("<td></td>");
						buffer.append("<td></td>");
						buffer.append("<td></td>");
						buffer.append("<td></td>");
						buffer.append("<td>"+misReport2.getClientname()+"</td>");
						buffer.append("</tr>");
					}
					i++;
				}
			}else if(misReport.getFname().equals("mrdfilemissing")){
				buffer.append("<tr>");
				buffer.append("<th>KPI Area</th>");
				buffer.append("<th>Indicator</th>");
				buffer.append("<th>KPI Year</th>");
				buffer.append("<th>KPI Month</th>");
				buffer.append("<th>Patient name</th>");
				buffer.append("</tr>");
				buffer.append("~");
				for (MisReport misReport2 : misReport.getDatalist()) {
					if(i==0){
						buffer.append("<tr>");
						buffer.append("<td>"+misReport.getKpiarea()+"</td>");
						buffer.append("<td>"+misReport.getKpiindicator()+"</td>");
						buffer.append("<td>"+misReport.getKpi_year()+"</td>");
						buffer.append("<td>"+misReport.getKpi_month()+"</td>");
						buffer.append("<td>"+misReport2.getClientname()+"</td>");
						buffer.append("</tr>");
					}else{
						buffer.append("<tr>");
						buffer.append("<td></td>");
						buffer.append("<td></td>");
						buffer.append("<td></td>");
						buffer.append("<td></td>");
						buffer.append("<td>"+misReport2.getClientname()+"</td>");
						buffer.append("</tr>");
					}
					i++;
				}
			}else if(misReport.getFname().equals("mrddissumnotfound")){
				buffer.append("<tr>");
				buffer.append("<th>KPI Area</th>");
				buffer.append("<th>Indicator</th>");
				buffer.append("<th>KPI Year</th>");
				buffer.append("<th>KPI Month</th>");
				buffer.append("<th>Patient name</th>");
				buffer.append("</tr>");
				buffer.append("~");
				for (MisReport misReport2 : misReport.getDatalist()) {
					if(i==0){
						buffer.append("<tr>");
						buffer.append("<td>"+misReport.getKpiarea()+"</td>");
						buffer.append("<td>"+misReport.getKpiindicator()+"</td>");
						buffer.append("<td>"+misReport.getKpi_year()+"</td>");
						buffer.append("<td>"+misReport.getKpi_month()+"</td>");
						buffer.append("<td>"+misReport2.getClientname()+"</td>");
						buffer.append("</tr>");
					}else{
						buffer.append("<tr>");
						buffer.append("<td></td>");
						buffer.append("<td></td>");
						buffer.append("<td></td>");
						buffer.append("<td></td>");
						buffer.append("<td>"+misReport2.getClientname()+"</td>");
						buffer.append("</tr>");
					}
					i++;
				}
			}
			else if(misReport.getFname().equals("waitingtimelab")){
				buffer.append("<tr>");
				buffer.append("<th>KPI Area</th>");
				buffer.append("<th>Indicator</th>");
				buffer.append("<th>KPI Year</th>");
				buffer.append("<th>KPI Month</th>");
				buffer.append("<th>Patient name</th>");
				buffer.append("<th>Time Taken</th>");
				buffer.append("</tr>");
				buffer.append("~");
				for (MisReport misReport2 : misReport.getDatalist()) {
					if(i==0){
						buffer.append("<tr>");
						buffer.append("<td>"+misReport.getKpiarea()+"</td>");
						buffer.append("<td>"+misReport.getKpiindicator()+"</td>");
						buffer.append("<td>"+misReport.getKpi_year()+"</td>");
						buffer.append("<td>"+misReport.getKpi_month()+"</td>");
						buffer.append("<td>"+misReport2.getClientname()+"</td>");
						buffer.append("<td>"+misReport2.getAverage()+"</td>");
						buffer.append("</tr>");
					}else{
						buffer.append("<tr>");
						buffer.append("<td></td>");
						buffer.append("<td></td>");
						buffer.append("<td></td>");
						buffer.append("<td></td>");
						buffer.append("<td>"+misReport2.getClientname()+"</td>");
						buffer.append("<td>"+misReport2.getAverage()+"min</td>");
						buffer.append("</tr>");
					}
					i++;
				}
			}
			else if(misReport.getFname().equals("waitingtimeopd")){
				buffer.append("<tr>");
				buffer.append("<th>KPI Area</th>");
				buffer.append("<th>Indicator</th>");
				buffer.append("<th>KPI Year</th>");
				buffer.append("<th>KPI Month</th>");
				buffer.append("<th>Patient name</th>");
				buffer.append("<th>Time Taken</th>");
				buffer.append("</tr>");
				buffer.append("~");
				for (MisReport misReport2 : misReport.getDatalist()) {
					if(i==0){
						buffer.append("<tr>");
						buffer.append("<td>"+misReport.getKpiarea()+"</td>");
						buffer.append("<td>"+misReport.getKpiindicator()+"</td>");
						buffer.append("<td>"+misReport.getKpi_year()+"</td>");
						buffer.append("<td>"+misReport.getKpi_month()+"</td>");
						buffer.append("<td>"+misReport2.getClientname()+"</td>");
						buffer.append("<td>"+misReport2.getAverage()+"</td>");
						buffer.append("</tr>");
					}else{
						buffer.append("<tr>");
						buffer.append("<td></td>");
						buffer.append("<td></td>");
						buffer.append("<td></td>");
						buffer.append("<td></td>");
						buffer.append("<td>"+misReport2.getClientname()+"</td>");
						buffer.append("<td>"+misReport2.getAverage()+"min</td>");
						buffer.append("</tr>");
					}
					i++;
				}
			}else if(misReport.getFname().equals("redosoftest")){
				buffer.append("<tr>");
				buffer.append("<th>Month</th>");
				buffer.append("<th>Re-do's of Lab Test </th>");
				buffer.append("<th>Total Number of Test</th>");
				buffer.append("<th>Re-do's of Lab Test Percentage</th>");
				
				buffer.append("</tr>");
				buffer.append("~");
				for (MisReport misReport2 : misReport.getDatalist()) {
					if(i==0){
						buffer.append("<tr>");
						buffer.append("<td>"+misReport.getKpiarea()+"</td>");
						buffer.append("<td>"+misReport.getKpiindicator()+"</td>");
						buffer.append("<td>"+misReport.getKpi_year()+"</td>");
						buffer.append("<td>"+misReport.getKpi_month()+"</td>");
						
						buffer.append("</tr>");
					}else{
						buffer.append("<tr>");
						buffer.append("<td>"+misReport2.getKpi_month()+"</td>");
						buffer.append("<td>"+misReport2.getInput1()+"</td>");
						buffer.append("<td>"+misReport2.getInput2()+"</td>");
						buffer.append("<td>"+misReport2.getResult()+"</td>");
						
						buffer.append("</tr>");
					}
					i++;
				}
			}else if(misReport.getFname().equals("reschudled_ot")){
				buffer.append("<tr>");
				buffer.append("<th>Month</th>");
				buffer.append("<th>Re-Sechuled of OTS</th>");
				buffer.append("<th>Total Number of OTS</th>");
				buffer.append("<th>Re-Sechuled of OTS Percentage</th>");
				
				buffer.append("</tr>");
				buffer.append("~");
				for (MisReport misReport2 : misReport.getDatalist()) {
					if(i==0){
						buffer.append("<tr>");
						buffer.append("<td>"+misReport.getKpiarea()+"</td>");
						buffer.append("<td>"+misReport.getKpiindicator()+"</td>");
						buffer.append("<td>"+misReport.getKpi_year()+"</td>");
						buffer.append("<td>"+misReport.getKpi_month()+"</td>");
						
						buffer.append("</tr>");
					}else{
						buffer.append("<tr>");
						buffer.append("<td>"+misReport2.getKpi_month()+"</td>");
						buffer.append("<td>"+misReport2.getInput1()+"</td>");
						buffer.append("<td>"+misReport2.getInput2()+"</td>");
						buffer.append("<td>"+misReport2.getResult()+"</td>");
						
						buffer.append("</tr>");
					}
					i++;
				}
			}else if(misReport.getFname().equals("icu_utilization_rate")){
				buffer.append("<tr>");
				buffer.append("<th>Month</th>");
				buffer.append("<th>In patient Avg in ICU</th>");
				buffer.append("<th>Total Number of Beds in ICU</th>");
				buffer.append("<th>ICU utiliztion rate </th>");
				
				buffer.append("</tr>");
				buffer.append("~");
				for (MisReport misReport2 : misReport.getDatalist()) {
					if(i==0){
						buffer.append("<tr>");
						buffer.append("<td>"+misReport.getKpiarea()+"</td>");
						buffer.append("<td>"+misReport.getKpiindicator()+"</td>");
						buffer.append("<td>"+misReport.getKpi_year()+"</td>");
						buffer.append("<td>"+misReport.getKpi_month()+"</td>");
						
						buffer.append("</tr>");
					}else{
						buffer.append("<tr>");
						buffer.append("<td>"+misReport2.getKpi_month()+"</td>");
						buffer.append("<td>"+misReport2.getInput1()+"</td>");
						buffer.append("<td>"+misReport2.getInput2()+"</td>");
						buffer.append("<td>"+misReport2.getResult()+"</td>");
						
						buffer.append("</tr>");
					}
					i++;
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
	public String getkpisaveddata() throws Exception {
		Connection connection=null;
		try {
			connection=Connection_provider.getconnection();
			MisChartDAO misChartDAO = new JDBCMisChartDAO(connection);
			String kpiid = request.getParameter("kpiid");
			String kpi_year = request.getParameter("kpi_year");
			String kpi_month = request.getParameter("kpi_month");
			boolean flag1 = misChartDAO.iskpiSyytemGen(kpiid); 
			if(flag1){
				boolean flag = misChartDAO.iskpiDataPresent(kpiid,kpi_year,kpi_month);
				if(!flag){
					MisReport misReport = misChartDAO.getKPIMasterData(kpiid);
					misReport.setUserid(null);
					misReport.setDatetime(null);
					int result = misChartDAO.insertNewKPIRecord("0", "0", "0", "0", "0", "0", misReport.getKpiindicatorid(), misReport, kpi_year, kpi_month, "0",null);
				}
			}
			
			MisReport misReport = misChartDAO.getKPIData(kpiid,kpi_year,kpi_month);
			StringBuilder builder = new StringBuilder();
			builder.append(""+kpiid+"");
			
			builder.append("~");
			if(misReport.getResult()==null){
				builder.append("0");
			}else if(misReport.getResult().equals("")){
				builder.append("0");
			}else{
				builder.append(""+misReport.getResult()+"");
			}
			
			builder.append("~");
			if(misReport.getTarget()==null){
				builder.append("0");
			}else if(misReport.getTarget().equals("")){
				builder.append("0");
			}else{
				builder.append(""+misReport.getTarget()+"");
			}
			
			builder.append("~");
			if(misReport.getInput1()==null){
				builder.append("0");
			}else if(misReport.getInput1().equals("")){
				builder.append("0");
			}else{
				builder.append(""+misReport.getInput1()+"");
			}
			
			builder.append("~");
			if(misReport.getInput2()==null){
				builder.append("0");
			}else if(misReport.getInput2().equals("")){
				builder.append("0");
			}else{
				builder.append(""+misReport.getInput2()+"");
			}
			builder.append("~");
			//builder.append(""+misReport.getInput3()+"");
			if(misReport.getInput3()==null){
				builder.append("0");
			}else if(misReport.getInput3().equals("")){
				builder.append("0");
			}else{
				builder.append(""+misReport.getInput3()+"");
			}
			builder.append("~");
			//builder.append(""+misReport.getInput4()+"");
			if(misReport.getInput4()==null){
				builder.append("0");
			}else if(misReport.getInput4().equals("")){
				builder.append("0");
			}else{
				builder.append(""+misReport.getInput4()+"");
			}
			builder.append("~");
			//builder.append(""+misReport.getInput5()+"");
			if(misReport.getInput5()==null){
				builder.append("0");
			}else if(misReport.getInput5().equals("")){
				builder.append("0");
			}else{
				builder.append(""+misReport.getInput5()+"");
			}
			response.setContentType("text/html");
			response.setHeader("Cache-Control", "no-cache");
			response.getWriter().write(""+builder.toString()+""); 	
			
		} catch (Exception e) {

			e.printStackTrace();
		} finally{
			
			connection.close();
		}
		return null;
	}
	public String savesatresult() throws Exception {
		Connection connection=null;
		try {
			connection=Connection_provider.getconnection();
			MisChartDAO misChartDAO = new JDBCMisChartDAO(connection);
			JDBCClientReportDAO clientReportDAO = new JDBCClientReportDAO(connection);
			String kpiid = request.getParameter("kpiid");
			String input1 = request.getParameter("val1");
			String input2 = request.getParameter("val2");
			String indicatorid = request.getParameter("indicatorid");
			String final_result = request.getParameter("result");
			String kpi_year = request.getParameter("kpi_year");
			String kpi_month = request.getParameter("kpi_month");
			String kpi_dataid = request.getParameter("kpi_dataid");
			String evidence = request.getParameter("evidence");
			String target = request.getParameter("target");
			
			DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Calendar cal = Calendar.getInstance();
			String todate = dateFormat.format(cal.getTime());   
			
			String userid = loginInfo.getUserId();
			
			if(kpi_dataid.equals("0")){
				MisReport misReport = misChartDAO.getKPIMasterData(kpiid);
				misReport.setUserid(userid);
				misReport.setDatetime(todate);
				int result = misChartDAO.insertNewKPIRecord(input1,input2,null,null,null,final_result,indicatorid,misReport,kpi_year,kpi_month,target,evidence);
			}else{
				int result = misChartDAO.updateKPIData(input1,input2,null,null,null,final_result,kpiid,todate,userid,kpi_dataid,target,evidence);
			}
			
			/*int res = misChartDAO.updateKPITempTable(input1,input2,input3,input4,input5);
			Report report = clientReportDAO.getMasterIndicator(indicatorid);
			String result = misChartDAO.getCalKPIResult(report);*/
			
			response.setContentType("text/html");
			response.setHeader("Cache-Control", "no-cache");
			response.getWriter().write(""); 	
			
		} catch (Exception e) {

			e.printStackTrace();
		} finally{
			
			connection.close();
		}
		return null;
	}
}
