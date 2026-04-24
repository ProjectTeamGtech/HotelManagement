package com.apm.Appointment.web.action;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionMessage;
import org.apache.struts2.ServletActionContext;

import com.apm.Accounts.eu.bi.AccountsDAO;
import com.apm.Accounts.eu.bi.ChargesAccountProcessingDAO;
import com.apm.Accounts.eu.blogic.jdbc.JDBCAccountsDAO;
import com.apm.Accounts.eu.blogic.jdbc.JDBCChargeAccountProcesDAO;
import com.apm.Accounts.eu.entity.Accounts;
import com.apm.Appointment.eu.bi.AppointmentDAO;
import com.apm.Appointment.eu.bi.AppointmentTypeDAO;
import com.apm.Appointment.eu.blogic.jdbc.JDBCAppointmentDAO;
import com.apm.Appointment.eu.blogic.jdbc.JDBCAppointmentTypeDAO;
import com.apm.Appointment.eu.entity.Appointment;
import com.apm.Appointment.eu.entity.AppointmentType;
import com.apm.Appointment.web.form.AppointmentForm;
import com.apm.Appointment.web.form.AppointmentTypeForm;
import com.apm.DiaryManagement.eu.bi.NotAvailableSlotDAO;
import com.apm.DiaryManagement.eu.blogic.jdbc.JDBCNotAvailableSlotDAO;
import com.apm.DiaryManagement.eu.entity.NotAvailableSlot;
import com.apm.DiaryManagement.eu.entity.Priscription;
import com.apm.Expence.eu.bi.ExpenManagementDAO;
import com.apm.Expence.eu.bi.blogic.jdbc.JDBCExpenceManagementDAO;
import com.apm.Expence.web.action.ExpenceManagementAction;
import com.apm.Master.eu.bi.DischargeOutcomeDAO;
import com.apm.Master.eu.bi.MasterDAO;
import com.apm.Master.eu.blogic.jdbc.JDBCDischargeOutcomeDAO;
import com.apm.Master.eu.blogic.jdbc.JDBCMasterDAO;
import com.apm.Master.eu.entity.Master;
import com.apm.Registration.eu.bi.ClinicDAO;
import com.apm.Registration.eu.bi.UserProfileDAO;
import com.apm.Registration.eu.blogic.jdbc.JDBCClinicDAO;
import com.apm.Registration.eu.blogic.jdbc.JDBCUserProfileDAO;
import com.apm.Registration.eu.entity.Clinic;
import com.apm.Registration.eu.entity.Location;
import com.apm.Report.eu.bi.SummaryReportDAO;
import com.apm.Report.eu.blogic.jdbc.JDBCSummaryReportDAO;
import com.apm.Report.eu.entity.SummaryReport;
import com.apm.ThirdParties.eu.bi.ThirdPartyDAO;
import com.apm.ThirdParties.eu.blogic.jdbc.JDBCThirdPartyDAO;
import com.apm.ThirdParties.eu.entity.ThirdParty;
import com.apm.TreatmentEpisode.eu.bi.TreatmentEpisodeDAO;
import com.apm.TreatmentEpisode.eu.blogic.jdbc.JDBCTreatmentEpisode;
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
import com.opensymphony.xwork2.Preparable;

public class AppointmentTypeAction extends BaseAction implements Preparable,
		ModelDriven<AppointmentTypeForm> {

	AppointmentTypeForm appointmentTypeForm = new AppointmentTypeForm();
	HttpServletRequest request = (HttpServletRequest) ActionContext
			.getContext().get(ServletActionContext.HTTP_REQUEST);
	HttpServletResponse response = (HttpServletResponse) ActionContext
			.getContext().get(ServletActionContext.HTTP_RESPONSE);
	HttpSession session = request.getSession(true);
	LoginInfo loginInfo = LoginHelper.getLoginInfo(request);

	private Pagination pagination = new Pagination(100, 1);
    private String mastername;
	
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
			if(appointmentTypeForm.getOrderby()==null){
				appointmentTypeForm.setOrderby("name");
			}
			if(appointmentTypeForm.getOrderby().equals("")){
				appointmentTypeForm.setOrderby("name");
			}
			if(appointmentTypeForm.getOrder()==null){
				appointmentTypeForm.setOrder("asc");
			}
			if(appointmentTypeForm.getOrder().equals("")){
				appointmentTypeForm.setOrder("asc");
			}
			connection = Connection_provider.getconnection();
			SummaryReportDAO summaryReportDAO=new JDBCSummaryReportDAO(connection);
			ArrayList<SummaryReport>wardlist= summaryReportDAO.getWardlist();
			appointmentTypeForm.setWardlist(wardlist);
			AppointmentTypeDAO appointmentTypeDAO = new JDBCAppointmentTypeDAO(
					connection);
			
			String viewaccess=request.getParameter("onlyviewss");
			if(viewaccess==null){
				
				viewaccess="0";
			}if(viewaccess.equals("")){
				viewaccess="0";
			}
			if(viewaccess.equals("1")){
				viewaccess="1";
			}
			String rptviewaccess=request.getParameter("viewaccess");
			if(rptviewaccess!=null){
				if(rptviewaccess.equals("1")){
					viewaccess="1";
				}
			}
			appointmentTypeForm.setViewaccess(viewaccess);
			
			
			AppointmentDAO appointmentDAO=new JDBCAppointmentDAO(connection);
			ArrayList<Master>masterChageTypeList = appointmentDAO.getmasterChageTypeList(loginInfo);
			appointmentTypeForm.setNewChargeTypeList(masterChageTypeList);
			int totalCount = appointmentTypeDAO.getTotalApmtTypeCount(
					appointmentTypeForm.getSearchText(),
					appointmentTypeForm.getPayby(),
					appointmentTypeForm.getThirdParty(),appointmentTypeForm.getWard(),appointmentTypeForm.getChargeType(),viewaccess,appointmentTypeForm.getOrderby(),appointmentTypeForm.getOrder());
			pagination.setPreperties(totalCount);

			ArrayList<AppointmentType> appointmentTypeList = appointmentTypeDAO
					.getAppointmentTypeList(pagination,
							appointmentTypeForm.getSearchText(),
							appointmentTypeForm.getPayby(),
							appointmentTypeForm.getThirdParty(),appointmentTypeForm.getWard(),appointmentTypeForm.getChargeType(),viewaccess,appointmentTypeForm.getOrderby(),appointmentTypeForm.getOrder());
			pagination.setPage_records(appointmentTypeList.size());
			appointmentTypeForm.setTotalRecords(totalCount);
			appointmentTypeForm.setPagerecords(Integer.toString(pagination
					.getPage_records()));
			appointmentTypeForm.setAppointmentTypeList(appointmentTypeList);
			session.setAttribute("pagination", pagination);
			
			mastername = request.getParameter("selectedid");
			if (mastername != null) {

				session.setAttribute("mastername", mastername);
			} else {
				mastername = (String) session.getAttribute("mastername");
			}		
//			String viewaccess=request.getParameter("onlyviewss");
//			if(viewaccess==null){
//				
//				viewaccess="0";
//			}if(viewaccess.equals("")){
//				viewaccess="0";
//			}
//			if(viewaccess.equals("1")){
//				viewaccess="1";
//			}
//			String rptviewaccess=request.getParameter("viewaccess");
//			if(rptviewaccess!=null){
//				if(rptviewaccess.equals("1")){
//					viewaccess="1";
//				}
//			}
			appointmentTypeForm.setViewaccess(viewaccess);
			appointmentTypeForm.setMastername(mastername);
			appointmentTypeForm.setWard(appointmentTypeForm.getWard());
			appointmentTypeForm.setChargeType(appointmentTypeForm.getChargeType());
		} catch (Exception e) {
			e.printStackTrace();

		} finally {
			connection.close();
		}

		return SUCCESS;

	}
	
	
	public String ocrpts() throws Exception{
		
		
		if (!verifyLogin(request)) {
			return "login";
		}
		
		String fromDate = appointmentTypeForm.getFromDate();
		String toDate = appointmentTypeForm.getToDate();	
		
		if(fromDate.equals("")){
			DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
			Calendar cal = Calendar.getInstance();
			cal.add(Calendar.DATE, -7); 
			fromDate = dateFormat.format(cal.getTime());
			appointmentTypeForm.setFromDate(fromDate);
		}
		if(toDate.equals("")){
			DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
			Calendar cal = Calendar.getInstance();
			//cal.add(Calendar.DATE, -7); 
			toDate = dateFormat.format(cal.getTime());
			appointmentTypeForm.setToDate(toDate);
		}
		
		
		if(!fromDate.equals("")){
			String temp[]= fromDate.split("/");
			fromDate = temp[2]+"-"+temp[1]+"-"+temp[0];
		}
		if(!toDate.equals("")){
			String temp1[]= toDate.split("/");
			toDate = temp1[2]+"-"+temp1[1]+"-"+temp1[0];
		}

		Connection connection = null;
		try {
			connection = Connection_provider.getconnection();
			AppointmentTypeDAO appointmentTypeDAO = new JDBCAppointmentTypeDAO(connection);
			
			ArrayList<Accounts>ocreptData = appointmentTypeDAO.getOcreptData(fromDate,toDate);
			appointmentTypeForm.setOcreptData(ocreptData);
			
			if(ocreptData.size()>0){
				Accounts a = ocreptData.get(ocreptData.size()-1);
				appointmentTypeForm.setDebitx(DateTimeUtils.changeFormat(a.getDebitTotal()));
				appointmentTypeForm.setCreditx(DateTimeUtils.changeFormat(a.getCreditTotal()));
			}
			
		}catch (Exception e) {
			// TODO: handle exception
		}finally{
			
			connection.close();
		}
		
		return "ocrpt";
	}
	
	public String newahead() throws Exception{
		
		if (!verifyLogin(request)) {
			return "login";
		}

		Connection connection = null;
		try {
			connection = Connection_provider.getconnection();
			AppointmentTypeDAO appointmentTypeDAO = new JDBCAppointmentTypeDAO(connection);
			
			int saveid = appointmentTypeDAO.saveNewAccountHead(appointmentTypeForm.getHdnnewahead(),appointmentTypeForm.getHdnnewobal(),appointmentTypeForm.getActype());
			
			//group count
			int count = appointmentTypeDAO.getGroupCount();
			int grpid = saveid;
			String servicestr = ""+saveid+"";
			if(count!=0){
				grpid = Integer.parseInt(appointmentTypeForm.getAheadname22());
				servicestr = appointmentTypeDAO.getServiceidstr(grpid);
				
				servicestr = servicestr + "," + saveid;
			}
			
			boolean chheadidexsist = appointmentTypeDAO.checkheadid(grpid);
			
			if(!chheadidexsist){
				int savgrpid = appointmentTypeDAO.saveheadgroupid(servicestr,grpid);
			}else{
				int upd = appointmentTypeDAO.updateServicestr(grpid,servicestr);
			}
			
			
			
			//save opening bal
			double lbal = appointmentTypeDAO.getLedgerBalance(saveid); 
			lbal = lbal + Double.parseDouble(appointmentTypeForm.getHdnnewobal());
			String credit = "0";
			String lddebit = appointmentTypeForm.getHdnnewobal();
			String product = "xxxxx";
			String partyid = "xxxxx";
			String ldcommencing = DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone());
			String gcommencing = ldcommencing.split(" ")[0];
			//int saveledger = appointmentTypeDAO.saveLedgerAhead(partyid,product,"0",credit,lbal,saveid,ldcommencing,gcommencing);
			
			addActionMessage("Record updates successfully!!");
			appointmentTypeForm.setMessage("Record updates successfully!!");
		}catch (Exception e) {
			// TODO: handle exception
		}finally{
			
			connection.close();
		}
		
		
		return ahead();
	}
	
	public String tb(){
		if (!verifyLogin(request)) {
			return "login";
		}
		
		String fromDate = appointmentTypeForm.getFromDate();
		String toDate = appointmentTypeForm.getToDate();	
		
		if(fromDate.equals("")){
			DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
			Calendar cal = Calendar.getInstance();
			cal.add(Calendar.DATE, -7); 
			fromDate = dateFormat.format(cal.getTime());
			appointmentTypeForm.setFromDate(fromDate);
		}
		if(toDate.equals("")){
			DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
			Calendar cal = Calendar.getInstance();
			//cal.add(Calendar.DATE, -7); 
			toDate = dateFormat.format(cal.getTime());
			appointmentTypeForm.setToDate(toDate);
		}
		
		
		if(!fromDate.equals("")){
			String temp[]= fromDate.split("/");
			fromDate = temp[2]+"-"+temp[1]+"-"+temp[0];
		}
		if(!toDate.equals("")){
			String temp1[]= toDate.split("/");
			toDate = temp1[2]+"-"+temp1[1]+"-"+temp1[0];
		}

		Connection connection = null;
		
		try {
			connection = Connection_provider.getconnection();
			AppointmentTypeDAO appointmentTypeDAO = new JDBCAppointmentTypeDAO(connection);
			
			ArrayList<Master>debitaheadList = appointmentTypeDAO.getDebitheadList(fromDate,toDate);
			appointmentTypeForm.setDebitaheadList(debitaheadList);
			
			if(debitaheadList.size()>0){
				String cctotal = debitaheadList.get(debitaheadList.size()-1).getCctotal();
				String ddtotal = debitaheadList.get(debitaheadList.size()-1).getDdtotal();
				
				appointmentTypeForm.setCctotal(cctotal);
				appointmentTypeForm.setDdtotal(ddtotal);
			}
			
			
			
			ArrayList<Master>creditaheadList = appointmentTypeDAO.getCreditAheadList(fromDate,toDate);
			appointmentTypeForm.setCreditaheadList(creditaheadList);
			
			//creditor list
			/*ArrayList<Accounts>screditorList = appointmentTypeDAO.getScreditorList(fromDate,toDate);
			appointmentTypeForm.setScreditorList(screditorList);
			if(screditorList.size()>0){
				Accounts aa = screditorList.get(screditorList.size()-1);
				appointmentTypeForm.setCreditx(aa.getTotalAmountx());
			}*/
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return "tb";
	}
	
	public String ahead() throws Exception{
		
		Connection connection = null;
		try {
			connection = Connection_provider.getconnection();
			AppointmentTypeDAO appointmentTypeDAO = new JDBCAppointmentTypeDAO(connection);
			
			ArrayList<Master>aheadNameList = appointmentTypeDAO.getAheadNameList();
			appointmentTypeForm.setAheadNameList(aheadNameList);
			
			ArrayList<Master>aheadserviceList = appointmentTypeDAO.getAheadServiceNameList();
			appointmentTypeForm.setAheadserviceList(aheadserviceList);
			appointmentTypeForm.setAheadname(DateTimeUtils.isNull(appointmentTypeForm.getAheadname()));
			
			Master master = appointmentTypeDAO.getdbSelectedAheadServices(appointmentTypeForm.getAheadname());
			
			
			
			
			/*ArrayList<Master>ledgersheetList = appointmentTypeDAO.getledgersheetList(master);
			for(Master m : ledgersheetList){
				int checkaccountentry = appointmentTypeDAO.checkAheadSheetEntery(m.getDate(),appointmentTypeForm.getAheadname());
				double lbal = 0; 
				
				String credit = "0";
				String lddebit = "0";
				if(master.getActype().equals("1")){
					credit = m.getCharge();
					lddebit = "0";
				}else{
					lddebit = m.getCharge();
					credit = "0";
				}
				String product = "xxxxx";
				String partyid = "xxxxx";
				String ldcommencing = DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone());
				String gcommencing = m.getDate();
				if(checkaccountentry==0){
					int saveledger = appointmentTypeDAO.saveLedgerAhead(partyid,product,lddebit,credit,lbal,Integer.parseInt(appointmentTypeForm.getAheadname()),ldcommencing,gcommencing);
				}else{
					int upd = appointmentTypeDAO.updateLedgerAheadData(credit,lddebit,checkaccountentry);
				}
			}*/
			
			//  17-07-2020 hide this code as per said manoj sir
			//ArrayList<Accounts>ledgerreport = appointmentTypeDAO.getAheadreportData(appointmentTypeForm.getAheadname(),appointmentTypeForm.getHdnsearchtext());
			ArrayList<Accounts>ledgerreport = new ArrayList<Accounts>();
			appointmentTypeForm.setLedgerreport(ledgerreport);
			appointmentTypeForm.setDbselectedservices(master.getServices());
			
			
			
			/*if(!dbselectedservices.equals("")){
				double aheaddebit = appointmentTypeDAO.getAheadDebit(appointmentTypeForm.getAheadname());
				double aheadbal = appointmentTypeDAO.getAheadBalance(appointmentTypeForm.getAheadname());
				double lbalance = appointmentTypeDAO.getLastLedgerdetails(dbselectedservices,tmp.length-1);
			
			double lbal = aheadbal; 
			lbal = lbal + lbalance;
			String credit = "0";
			String lddebit = ""+lbalance+"";
			String product = "xxxxx";
			String partyid = "xxxxx";
			String ldcommencing = DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone());
			if(lbalance>aheaddebit){
				
				int saveledger = appointmentTypeDAO.saveLedgerAhead(partyid,product,lddebit,credit,lbal,Integer.parseInt(appointmentTypeForm.getAheadname()),ldcommencing);
				
			}
			
			
			
			
			
			
			
			}*/
				
		}catch (Exception e) {
			// TODO: handle exception
		}finally{
			
			connection.close();
		}
			
		
		return "ahead";
	}
	
	public String saveahead() throws Exception{
		
		if (!verifyLogin(request)) {
			return "login";
		}

		Connection connection = null;
		try {
			connection = Connection_provider.getconnection();
			AppointmentTypeDAO appointmentTypeDAO = new JDBCAppointmentTypeDAO(connection);
			
			
			int res = appointmentTypeDAO.updateAheadServices(appointmentTypeForm.getAheadname(),appointmentTypeForm.getHdnaheadserviceid());
			
			addActionMessage("Record updates successfully!!");
			appointmentTypeForm.setMessage("Record updates successfully!!");
			
			
			
		}catch (Exception e) {
			// TODO: handle exception
		}finally{
			
			connection.close();
		}
		
		return ahead();
	}
	
	public String sveled() throws Exception{
		
		if (!verifyLogin(request)) {
			return "login";
		}

		Connection connection = null;
		try {
			connection = Connection_provider.getconnection();
			AppointmentTypeDAO appointmentTypeDAO = new JDBCAppointmentTypeDAO(connection);
			
			
			
			
			int res = appointmentTypeDAO.updateLedgerServices(appointmentTypeForm.getLedgername(),appointmentTypeForm.getHdnledgerserviceid());
			
			addActionMessage("Record updates successfully!!");
			appointmentTypeForm.setMessage("Record updates successfully!!");
			
			
		}catch (Exception e) {
			// TODO: handle exception
		}finally{
			
			connection.close();
		}
		
		return ledg();
	}
	
	public String viewledrport() throws Exception{
		
		if (!verifyLogin(request)) {
			return "login";
		}
		
		
		String fromDate = appointmentTypeForm.getFromDate();
		String toDate = appointmentTypeForm.getToDate();	
		
		if(fromDate.equals("")){
			DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
			Calendar cal = Calendar.getInstance();
			cal.add(Calendar.DATE, -2); 
			fromDate = dateFormat.format(cal.getTime());
			appointmentTypeForm.setFromDate(fromDate);
		}
		if(toDate.equals("")){
			DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
			Calendar cal = Calendar.getInstance();
			//cal.add(Calendar.DATE, -7); 
			toDate = dateFormat.format(cal.getTime());
			appointmentTypeForm.setToDate(toDate);
		}
		
		
		if(!fromDate.equals("")){
			String temp[]= fromDate.split("/");
			fromDate = temp[2]+"-"+temp[1]+"-"+temp[0];
		}
		if(!toDate.equals("")){
			String temp1[]= toDate.split("/");
			toDate = temp1[2]+"-"+temp1[1]+"-"+temp1[0];
		}


		Connection connection = null;
		try {
			connection = Connection_provider.getconnection();
			AppointmentTypeDAO appointmentTypeDAO = new JDBCAppointmentTypeDAO(connection);
			
		
			
			String ledger = request.getParameter("ledger");
			String lname = request.getParameter("lname");
//			System.out.println(ledger);
			
			ArrayList<Accounts>ledgerreport = appointmentTypeDAO.getledgerreportData(fromDate,toDate,
					appointmentTypeForm.getLedgername(),appointmentTypeForm.getServicename(),
					appointmentTypeForm.getActype(),appointmentTypeForm.getBnkname());
			appointmentTypeForm.setLedgerreport(ledgerreport);
			//appointmentTypeForm.setLedgername(lname);
			
			appointmentTypeForm.setLedgername(appointmentTypeForm.getLedgername());
			ArrayList<Master>ledgerList = appointmentTypeDAO.getLedgerList1("0");

			
			
			appointmentTypeForm.setLedgerList(ledgerList);

			if(ledgerreport.size()>0){
				Accounts a = ledgerreport.get(ledgerreport.size()-1);
				appointmentTypeForm.setDebitx(DateTimeUtils.changeFormat(a.getDebitAmount()));
				appointmentTypeForm.setCreditx(DateTimeUtils.changeFormat(a.getCreditAmount()));
			}
			
			//lokesh
			String ledgername="All Ledger";
			if(appointmentTypeForm.getLedgername()!=null){
			if(!appointmentTypeForm.getLedgername().equals("0")){
			ledgername = appointmentTypeDAO.getLedgerName(appointmentTypeForm.getLedgername());
			}}
			appointmentTypeForm.setLedgerreportname(ledgername);
			
			Clinic clinic = new Clinic();
			ClinicDAO clinicDAO = new JDBCClinicDAO(connection);
			clinic = clinicDAO.getCliniclistDetails(loginInfo.getClinicid());
			AccountsDAO accountsDAO = new JDBCAccountsDAO(connection);
			ArrayList<Clinic> locationAdressList = accountsDAO.getLocationAddress(loginInfo.getClinicid());
			
		
			
			appointmentTypeForm.setClinicName(clinic.getClinicName());
			appointmentTypeForm.setClinicOwner(clinic.getClinicOwner());
			appointmentTypeForm.setOwner_qualification(clinic.getOwner_qualification());
			appointmentTypeForm.setLocationAdressList(locationAdressList);
			appointmentTypeForm.setAddress(clinic.getAddress());
			appointmentTypeForm.setLandLine(clinic.getLandLine());
			appointmentTypeForm.setClinicemail(clinic.getEmail());
			appointmentTypeForm.setWebsiteUrl(clinic.getWebsiteUrl());
			appointmentTypeForm.setClinicLogo(clinic.getUserImageFileName());
			
		}catch (Exception e) {
			e.printStackTrace();
		}finally{
			
			connection.close();
		}
		
		return "viewledrport";
	}
	
	public String newledg() throws Exception{
		
		if (!verifyLogin(request)) {
			return "login";
		}

		Connection connection = null;
		try {
			connection = Connection_provider.getconnection();
			AppointmentTypeDAO appointmentTypeDAO = new JDBCAppointmentTypeDAO(connection);
			
			String howpaid = appointmentTypeForm.getHdnhowpaid();
//			System.out.println(howpaid);
			
			int res = appointmentTypeDAO.saveNewLedger(appointmentTypeForm.getHdnnewledger(),howpaid,appointmentTypeForm.getBnkname(),appointmentTypeForm.getLtype());
			
			String ledgerstr = appointmentTypeDAO.getLedgerStr(appointmentTypeForm.getAheadname());
			ledgerstr = ledgerstr + ""+res+"" + ",";
			int u = appointmentTypeDAO.updateAheadServices(appointmentTypeForm.getAheadname(), ledgerstr);
			
			//add opening balance to ledger
			double tamt = Double.parseDouble(appointmentTypeForm.getOpbal());
			ChargesAccountProcessingDAO chargesAccountProcessingDAO = new JDBCChargeAccountProcesDAO(connection);
			ExpenManagementDAO expenManagementDAO = new JDBCExpenceManagementDAO(
					connection);
			String column = "rno";
			int rno = expenManagementDAO.getExpenceMaxno(column);
			
			
			String ledgerid = appointmentTypeForm.getHdnnewledger();
			double lbal = chargesAccountProcessingDAO.getAheadLedgerBalance(ledgerid);
			lbal = lbal - tamt;
			 String credit = ""+tamt+"";
			String ldebit = "0";
			String product = appointmentTypeForm.getHdnnewledger();
			String partyid = "xxxx";
			String lcommencing = DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone());
			//int saveledger = appointmentTypeDAO.saveLedgerAhead(partyid,product,ldebit,credit,lbal,Integer.parseInt(ledgerid),lcommencing);
			
			int expnctype = 2;
			int saveledger = chargesAccountProcessingDAO.saveExpenceLedger(partyid, product, ldebit, credit, lbal, ledgerid,
					lcommencing, "0",0,expnctype,"Receipt",0,0);
			
			//insert into official ledgers
			ledgerid = chargesAccountProcessingDAO.getExpenceLedgerId(appointmentTypeForm.getHdnnewledger());
			 lbal = chargesAccountProcessingDAO.getAheadLedgerBalance(ledgerid);
			lbal = lbal + tamt;
			 credit = "0";
			 ldebit = ""+tamt+"";
			 product = "xxxxx";
			 partyid = "xxxxx";
			 lcommencing = DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone());
			//int saveledger = appointmentTypeDAO.saveLedgerAhead(partyid,product,ldebit,credit,lbal,Integer.parseInt(ledgerid),lcommencing,"");
			 expnctype = 1;
			 saveledger = chargesAccountProcessingDAO.saveExpenceLedger(partyid, product, ldebit, credit, lbal, ledgerid,
					lcommencing, "0",0,expnctype,"Receipt",0,0);
			
			ArrayList<Master>ledgerList = appointmentTypeDAO.getManageLedgerList("0");
			appointmentTypeForm.setLedgerList(ledgerList);
			
			
			
			addActionMessage("Record updates successfully!!");
			appointmentTypeForm.setMessage("Record updates successfully!!");
			
		}catch (Exception e) {
			// TODO: handle exception
		}finally{
			
			connection.close();
		}
		
		return ledg();
	}
	
	public String ledg() throws Exception {
		

		if (!verifyLogin(request)) {
			return "login";
		}

		Connection connection = null;
		try {
			connection = Connection_provider.getconnection();
			AppointmentTypeDAO appointmentTypeDAO = new JDBCAppointmentTypeDAO(connection);
			
			String dbselectedservices = appointmentTypeDAO.getdbSelectedServices(appointmentTypeForm.getLedgername());
			appointmentTypeForm.setDbselectedservices(dbselectedservices);
			
			ArrayList<Master>aheadNameList = appointmentTypeDAO.getAheadNameList();
			appointmentTypeForm.setAheadNameList(aheadNameList);
			
			
		}catch (Exception e) {
			// TODO: handle exception
		}finally{
			
			connection.close();
		}
		
		return "ledger";
	}

	public String party() throws Exception{
		String clientId = request.getParameter("clientId");
//		System.out.println(clientId);

		Connection connection = null;
		try {
			connection = Connection_provider.getconnection();

			AppointmentTypeDAO appointmentTypeDAO = new JDBCAppointmentTypeDAO(
					connection);

			String companyname = appointmentTypeDAO.getTpCompanyName(clientId);

			response.setContentType("text/html");
			response.setHeader("Cache-Control", "no-cache");
			response.getWriter().write("" + companyname + "");

		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			
			connection.close();
		}

		return null;
	}

	public String tp() throws Exception{
//		System.out.println("hello");
		Connection connection = null;
		try {
			connection = Connection_provider.getconnection();

			AppointmentTypeDAO appointmentTypeDAO = new JDBCAppointmentTypeDAO(connection);
			String commencing = DateTimeUtils.getCommencingDate(request.getParameter("commencing"));
			String selectedid = request.getParameter("selectedid");
			String clientId = request.getParameter("clientId");
			String whopay = request.getParameter("whopay");
			boolean isot = Boolean.parseBoolean(request.getParameter("isot"));
			String otprocedureplaned = request.getParameter("otprocedureplaned");
			String docid=request.getParameter("docid");
			long  newdiffer=0;
			//changes made for balgopal opd charges to be set by deafault 13/08/2024
              String apmt_type="";
			  if(loginInfo.isBalgopal())
			  {
				  
				String appointmentype= opdappointment(clientId,commencing,docid,selectedid,whopay);
			    apmt_type=appointmentype;
			  }else if(loginInfo.isMarkhosp()) {
				  

				  long differ=0; 
				  
				  String apmt_name="";
				  String last_opd_date=appointmentTypeDAO.getLastopddatebyclientid(clientId); 
				  String last_charge_apmt=appointmentTypeDAO.getLastChargedappointment(clientId);
				  String current_date=DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone()).split(" ")[0]; 
				  int count=appointmentTypeDAO.getavailableslotclientcount(clientId);
				  

				  if(last_opd_date.equals("")) 
				  { 
					  differ=DateTimeUtils.getDiffofTwoDates(current_date, commencing); 
				  }else {
			          differ=DateTimeUtils.getDiffofTwoDates(last_opd_date, commencing); 
			      }
				  
				  
				  
                   // new changes in markhosp appointment 
				  
				  if(!last_charge_apmt.equals("")) {
					  
					  newdiffer=DateTimeUtils.getDiffofTwoDates(last_charge_apmt, commencing); 
				  }
				  
				  if(!last_opd_date.equals("")) {
				  String temp1[]=last_opd_date.split("-");
				  String month1=temp1[1];
				  String year1=temp1[0];
				  
				  String temp2[]=commencing.split("-");
				  String month2=temp2[1];
				  String year2=temp2[0];
				  
				  if(!month1.equals(month2)) {
					  int month_days=DateTimeUtils.getlastmonthdate(Integer.parseInt(month1),Integer.parseInt(year1));
					  
					  if(month_days==28) {
						  differ=differ-3;
						  newdiffer=newdiffer-3;
					  }else if(month_days==29) { 
						  differ=differ-2;
						  newdiffer=newdiffer-2;
					  }else if(month_days==30) {
						  differ=differ-1;
						  newdiffer=newdiffer-1;
					  }else if(month_days==31) {
						  differ=differ+1;
						  newdiffer=newdiffer+1;
					  }
				  }
				 }
				  long opddays=0;
				  long dischargedays=0;
				  
				  NotAvailableSlot notAvailable=appointmentTypeDAO.getAdmissiondatebyid(clientId);
				  String dischargedate=DateTimeUtils.isNull(notAvailable.getDischargedate());
				  
				  if(!dischargedate.equals("")) {
					  
					  dischargedays=DateTimeUtils.getDatesum(notAvailable.getDischargedate());
					  
				  }
				  
				  if(!last_opd_date.equals("")) {
					  opddays=DateTimeUtils.getDatesum(last_opd_date);
				  }
				  
				  if(dischargedays>opddays) {
					  apmt_name="Free Follow Up";
				     // docid="0"; 
				  }else {
				    
					  if(selectedid.equals("97")) {
					  
					     apmt_name="SECL ON CASH OPD";
					    // docid="0";
				     }   else if(selectedid.equals("98")) {
					     apmt_name="SECL Patient OPD";
					  //   docid="0";
				     }  else if(selectedid.equals("99")) {
					     apmt_name="CGHS OPD";
					   //  docid="0";
				  
				     }  else if(selectedid.equals("0")) {
				  
				    	  if(last_opd_date.equals("") && differ==0) {
					            apmt_name="New OPD";
				           }else if(newdiffer>7) {
					            apmt_name="Follow UP";
				           }else if(newdiffer<=7){
					            apmt_name="7 DAY FREE FOLLOWUP";
					           
				           }
				  }
				  apmt_type=appointmentTypeDAO.getApppointmentIdbyDocid(docid,apmt_name,selectedid);
			  }
			}else if(loginInfo.isAyushman()) {
				  long differ=0; 
				  String apmt_name="";
				  String last_opd_date=appointmentTypeDAO.getLastopddatebyclientid(clientId); 
				  String current_date=DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone()).split(" ")[0]; 
				  int count=appointmentTypeDAO.getavailableslotclientcount(clientId);
				  
				

				  if(last_opd_date.equals("")) 
				  { 
					  differ=DateTimeUtils.getDiffofTwoDates(current_date, commencing); 
				  }else {
			          differ=DateTimeUtils.getDiffofTwoDates(last_opd_date, commencing); 
			      }
				  
				  if(!last_opd_date.equals("")) {
				  String temp1[]=last_opd_date.split("-");
				  String month1=temp1[1];
				  String year1=temp1[0];
				  
				  String temp2[]=commencing.split("-");
				  String month2=temp2[1];
				  String year2=temp2[0];
				  int updtopd_count=0;
				  
				  if(!month1.equals(month2)) {
					  int month_days=DateTimeUtils.getlastmonthdate(Integer.parseInt(month1),Integer.parseInt(year1));
					  
					  if(month_days==28) {
						  differ=differ-3;
					  }else if(month_days==29) { 
						  differ=differ-2;
					  }else if(month_days==30) {
						  differ=differ-1;
					  }else if(month_days==31) {
						  differ=differ+1;
						 
					  }
				  }
				} 
				  
				  if(differ<=6 && count==1) {
					apmt_type="3204";
					// updtopd_count=appointmentTypeDAO.updateOpd_count(0,clientId);
				  }else {
					apmt_type="0";
					 //updtopd_count=appointmentTypeDAO.updateOpd_count(1,clientId);
				  }
			}
			else {
				  apmt_type="0";
			}
			
//			System.out.println(isot);
//			System.out.println(otprocedureplaned);

			/*
			 * ArrayList<AppointmentType> appointmentTypeList = appointmentTypeDAO
			 * .gettpAppointmentTypeList(selectedid, whopay,isot,otprocedureplaned);
			 */
			ArrayList<AppointmentType>appointmentTypeList=new ArrayList<AppointmentType>();
			if(loginInfo.getClinicid1().equals("manasclinic") && loginInfo.getSuperadminid()!=1) {
				NotAvailableSlotDAO notAvailableSlotDAO=new JDBCNotAvailableSlotDAO(connection);
				String clinicstaff_id=notAvailableSlotDAO.getclinicstaff(loginInfo.getId());
				appointmentTypeList = appointmentTypeDAO
						.getManasclinictpAppointmentTypeList(selectedid, whopay,isot,otprocedureplaned,clinicstaff_id);
			}else {
				appointmentTypeList = appointmentTypeDAO
						.gettpAppointmentTypeList(selectedid, whopay,isot,otprocedureplaned,loginInfo,docid,newdiffer);
			}

			StringBuffer str = new StringBuffer();
          
			if(loginInfo.isBalgopal()) {
				if(!loginInfo.getJobTitle().equals("PHYSIO RECEPTION")) {
					str.append("<select onchange='setAppointmentTypeTimeAjax(this.value)' name='duration' id='apmtType' class='form-control showToolTip chosen' >");
               }else {
					str.append("<select onchange='setAppointmentTypeTimeAjax(this.value)' name='duration' id='apmtType' class='form-control showToolTip chosen' >");
				}
			}else {
			str.append("<select onchange='setAppointmentTypeTimeAjax(this.value)' name='duration' id='apmtType' class='form-control showToolTip chosen' >");
			}
			str.append("<option value='0'>Select Appointment Type</option>");
			for (AppointmentType appointmentType : appointmentTypeList) {
				str.append("<option value='" + appointmentType.getId() + "'>"
						+ appointmentType.getName() + "</option>");
			}
			str.append("</select>");
            str.append("~~"+apmt_type);
		/*	str.append("<label id='apmtTypeError' class='text-danger'></label>");
			str.append("<div class='col-lg-12 col-md-12'>");
			str.append("<label id='apmtTypeDuration' class='text-danger durane'></label>");
			str.append("</div>");*/

			response.setContentType("text/html");
			response.setHeader("Cache-Control", "no-cache");
			response.getWriter().write("" + str.toString() + "");

		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			
			connection.close();
		}

		return null;
	}


	private String opdappointment(String clientid, String commencing, String docid, String tpid, String payby) {
		   Connection connection=null;
		   String apmttype="";
		try { connection = Connection_provider.getconnection();
		  AppointmentTypeDAO appointmentTypeDAO = new JDBCAppointmentTypeDAO(connection);
		  TreatmentEpisodeDAO treatmentEpisodeDAO = new JDBCTreatmentEpisode(connection);
		  long differ=0; 
		  String last_opd_date=appointmentTypeDAO.getLastopddatebyclientid(clientid); 
		  String current_date=DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone()).split(" ")[0]; 
		  int count=appointmentTypeDAO.getavailableslotclientcount(clientid);
	     
		
		
		  if(last_opd_date.equals("")) 
		  { 
			  
			  differ=DateTimeUtils.getDiffofTwoDates(current_date, commencing); 
			 
		  }else {
	          differ=DateTimeUtils.getDiffofTwoDates(last_opd_date, commencing); 
	      }
		  
		  
		
		  long opddays=0;
		  long admitdays=0;
		  long dischargedays=0;
		  long commencingdays=0;
		  NotAvailableSlot notAvailableSlot=appointmentTypeDAO.getAdmissiondatebyid(clientid);
		  
		  if(!last_opd_date.equals("")) {
		  String temp1[]=last_opd_date.split("-");
		  String month1=temp1[1];
		  String year1=temp1[0];
		  
		  String temp2[]=commencing.split("-");
		  String month2=temp2[1];
		  String year2=temp2[0];
		  
		  if(!month1.equals(month2)) {
			  int month_days=DateTimeUtils.getlastmonthdate(Integer.parseInt(month1),Integer.parseInt(year1));
			  
			  if(month_days==28) {
				  differ=differ-3;
			  }else if(month_days==29) { 
				  differ=differ-2;
			  }else if(month_days==30) {
				  differ=differ-1;
			  }else if(month_days==31) {
				  differ=differ+1;
					 
			  }
		   }
		  }
		  
		  String admitdate=DateTimeUtils.isNull(notAvailableSlot.getAdmitdate()).split(" ")[0];
		   if(!admitdate.equals("")) {
		       admitdays=DateTimeUtils.getDatesum(admitdate);
		    }
		   if(!last_opd_date.equals("")) {
			   opddays=DateTimeUtils.getDatesum(last_opd_date);
			}
		   if(!DateTimeUtils.isNull(notAvailableSlot.getDischargedate()).equals("")) {
			   dischargedays=DateTimeUtils.getDatesum(notAvailableSlot.getDischargedate());
		   }
		   
		   commencingdays=DateTimeUtils.getDatesum(commencing);
		  
		   if(dischargedays!=0  && dischargedays<=commencingdays) {
			   //&& !notAvailableSlot.getWhopay().equals("0")
			   if((!notAvailableSlot.getWhopay().equals("86") && !notAvailableSlot.getWhopay().equals("94")) && (!docid.equals("22") && !docid.equals("39")) ) {
				   long diff=0;
				   diff=DateTimeUtils.getDiffofTwoDates(notAvailableSlot.getDischargedate(), commencing); 
				   
			    if(diff<=7) {
				 apmttype="3";
			    }else {
			      apmttype="1";	
			    }
			     int updtopd_count=appointmentTypeDAO.updateOpd_count(0,clientid);
			   
			   }else if(docid.equals("22") || docid.equals("39")) {
					  if(differ==0 || differ>3 ) {
						  if(docid.equals("22")) {
							  apmttype="24164";   
						  }else {
						      apmttype="21998"; 
						  }
					  }else {
						     apmttype="3";
					  }
			   }else {
				   if((differ==0 && count==0 )|| differ>15) { 
	    	            apmttype="1"; 
	               }else if(last_opd_date.equals(commencing) && count!=0) {
	    	            apmttype="3"; 
	               }else if(differ<=7) {
	    	          if(count==1 || count==3) {
	    		          apmttype="3";   
	    	       }else if(count==2) {
	    		         apmttype="2"; 	 
	    	       }else if(count==4) {
	    		        apmttype="1";
	    	       }
	              }
			   }
			  
			  
		  } else if(docid.equals("22") || docid.equals("39")) {
			  if(differ==0 || differ>3 ) {
				  if(docid.equals("22")) {
					  apmttype="24164";   
				  }else {
				      apmttype="21998"; 
				  }
			  }else {
				     apmttype="3";
			  }
		  }
		  
		/*  if(!docid.equals("22") && !docid.equals("39") && (payby.equals("Client") || tpid.equals("94") ||  tpid.equals("86"))) {
			  
			  if(tpid.equals("86") || tpid.equals("94") ) {
				  if((differ==0 && count==0 )|| differ>7) { 
	    	            apmttype="17103"; 
	               }else if(last_opd_date.equals(commencing) && count!=0) {
	    	            apmttype="17105"; 
	               }else if(differ<=7) {
	    	          if(count==1 || count==3) {
	    		          apmttype="17105";   
	    	       }else if(count==2) {
	    		         apmttype="17104"; 	 
	    	       }else if(count==4) {
	    		        apmttype="17103";
	    	       }
	              }
			  }*/else {
	               if((differ==0 && count==0 )|| differ>15) { 
	    	            apmttype="1"; 
	               }else if(last_opd_date.equals(commencing) && count!=0) {
	    	            apmttype="3"; 
	               }else if(differ<=7) {
	    	          if(count==1 || count==3) {
	    		          apmttype="3";   
	    	       }else if(count==2) {
	    		         apmttype="2"; 	 
	    	       }else if(count==4 || count==0) {
	    		        apmttype="1";
	    	       }
	              }else if(differ>7 && differ<=15) {
	            	  if(count==3 || count==2) {
	            		  apmttype="1";
	            	  }if(count==1) {
	            		  apmttype="2"; 
	            	  }
	              }
	         }
		  
		  
				/*
				 * if(!docid.equals("22") && !docid.equals("39") && !tpid.equals("94") &&
				 * !tpid.equals("86") && payby.equals("Third Party")) { // boolean
				 * admitdate=treatmentEpisodeDAO.isAdmitdateexist(clientid); }
				 */
			/*
			 * else if(differ<=7 && count%2==0) { notAvailableSlot.setApmtType("2"); }else
			 * if(differ>7 && differ<=15 && count%2!=0) { notAvailableSlot.setApmtType("2");
			 * }
			 */
	      System.out.println(differ); 
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return apmttype;
	}

	
	public String add() throws SQLException {
		Connection connection = null;
		try {
			connection = Connection_provider.getconnection();

			AppointmentTypeDAO appointmentTypeDAO = new JDBCAppointmentTypeDAO(
					connection);
			ArrayList<AppointmentType> colorList = appointmentTypeDAO
					.getColorList();

			appointmentTypeForm.setColorList(colorList);
			appointmentTypeForm.setMastername(mastername);
			DischargeOutcomeDAO outcomeDAO = new JDBCDischargeOutcomeDAO(connection);
			ArrayList<Master> newChargeTypeList = outcomeDAO.getMasterChargeTypeList();
			appointmentTypeForm.setNewChargeTypeList(newChargeTypeList);
			ArrayList<Master> clinicStafflist = new ArrayList<Master>();
			UserProfileDAO userProfileDAO = new JDBCUserProfileDAO(connection);
			clinicStafflist = userProfileDAO.getClinicStafflist();
			appointmentTypeForm.setClinicstafflist(clinicStafflist);
			
			
		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			connection.close();
		}

		return "addAppointmentPage";
	}

	public String save() throws SQLException {
		if (!verifyLogin(request)) {
			return "login";
		}
		Connection connection = null;
		int result = 0;
		try {
			
			connection = DriverManager.getConnection(
					"" + Constants.DB_HOST + ":3306/" + loginInfo.getClinicUserid()
							+ "?useUnicode=true&characterEncoding=UTF-8",
					"" + Constants.DB_USER + "", "" + Constants.DB_PWD + "");
			request.setCharacterEncoding("UTF-8");
			response.setCharacterEncoding("UTF-8");
			AppointmentType appointmentType = new AppointmentType();
			AppointmentTypeDAO appointmentTypeDAO = new JDBCAppointmentTypeDAO(
					connection);
			/*
			 * String colorWhite = "FFFFFF"; String color = "";
			 * if(appointmentTypeForm.getColor().equals(colorWhite)) {
			 * appointmentTypeForm.setColor(color); }
			 */
			if(loginInfo.isBams1()) {
				String chargetype=appointmentTypeDAO.getChargeTypebyid(appointmentTypeForm.getChargeType());
				System.out.println(chargetype);
				appointmentType.setChargeType(chargetype);
			}else {
				appointmentType.setChargeType(appointmentTypeForm.getChargeType());
			}
			
			appointmentType.setName(appointmentTypeForm.getName());
			appointmentType
					.setDescription(appointmentTypeForm.getDescription());
			appointmentType.setCategory(appointmentTypeForm.getCategory());
			appointmentType.setDuration(appointmentTypeForm.getDuration());
			// appointmentType.setColor(appointmentTypeForm.getColor());
			appointmentType.setCharges(appointmentTypeForm.getCharges());
			appointmentType.setLocation(appointmentTypeForm.getLocation());
		
			appointmentType
					.setReportField(appointmentTypeForm.getReportField());

			appointmentType.setBasecharge(appointmentTypeForm.getBasecharge());
			appointmentType.setCode(appointmentTypeForm.getCode());
			//  20 July 2018
			appointmentType.setShareablecharge(appointmentTypeForm.getShareablecharge());
			appointmentType.setClinic_name_id(appointmentTypeForm.getClinic_name_id());
			appointmentType.setSessionno(appointmentTypeForm.getSessionno());
			if(appointmentType.getShareablecharge()!=null){
				if(appointmentType.getShareablecharge().equals("true")){
					appointmentType.setShareablecharge("1");
				}else{
					appointmentType.setShareablecharge("0");
				}
			}else{
				appointmentType.setShareablecharge("0");
			}
			
			result = appointmentTypeDAO.saveAppointmentType(appointmentType);
			appointmentTypeForm
					.setMessage("Appointment Type Added Sucessfully!!");
			addActionMessage("Appointment Type Added Sucessfully!!");
			appointmentTypeForm.setMastername(mastername);
			setFormData();
        
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			connection.close();
		}
		return SUCCESS;
	}

	public String back() throws Exception {
		setFormData();
		appointmentTypeForm.setMastername(mastername);
		return SUCCESS;
	}

	public void setFormData() throws Exception{
		Connection connection = null;
		int result = 0;
		try {
			connection = Connection_provider.getconnection();
			AppointmentType appointmentType = new AppointmentType();
			AppointmentTypeDAO appointmentTypeDAO = new JDBCAppointmentTypeDAO(
					connection);
			if(appointmentTypeForm.getOrderby()==null){
				appointmentTypeForm.setOrderby("name");
			}
			if(appointmentTypeForm.getOrderby().equals("")){
				appointmentTypeForm.setOrderby("name");
			}
			if(appointmentTypeForm.getOrder()==null){
				appointmentTypeForm.setOrder("asc");
			}
			if(appointmentTypeForm.getOrder().equals("")){
				appointmentTypeForm.setOrder("asc");
			}
			SummaryReportDAO summaryReportDAO=new JDBCSummaryReportDAO(connection);
			ArrayList<SummaryReport>wardlist= summaryReportDAO.getWardlist();
			appointmentTypeForm.setWardlist(wardlist);
			pagination = (Pagination) session.getAttribute("pagination");
			int totalCount = appointmentTypeDAO.getTotalApmtTypeCount(
					appointmentTypeForm.getSearchText(),
					appointmentTypeForm.getPayby(),
					appointmentTypeForm.getThirdParty(),"","","","","");
			pagination.setPreperties(totalCount);

			ArrayList<AppointmentType> appointmentTypeList = appointmentTypeDAO
					.getAppointmentTypeList(pagination,
							appointmentTypeForm.getSearchText(),
							appointmentTypeForm.getPayby(),
							appointmentTypeForm.getThirdParty(),"","","","","");
			pagination.setPage_records(appointmentTypeList.size());
			appointmentTypeForm.setTotalRecords(totalCount);
			appointmentTypeForm.setPagerecords(Integer.toString(pagination
					.getPage_records()));
			appointmentTypeForm.setAppointmentTypeList(appointmentTypeList);
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			
			connection.close();
		}
	}

	public String edit() throws SQLException {
		if (!verifyLogin(request)) {
			return "login";
		}
		int id = Integer.parseInt(request.getParameter("selectedid"));
		Connection connection = null;
		AppointmentType appointmentType = new AppointmentType();
		try {

			connection = Connection_provider.getconnection();

			AppointmentTypeDAO appointmentTypeDAO = new JDBCAppointmentTypeDAO(
					connection);

			ArrayList<AppointmentType> colorList = appointmentTypeDAO
					.getColorList();
         
			appointmentTypeForm.setColorList(colorList);
			appointmentType = appointmentTypeDAO.getAppointment(id);
			appointmentTypeForm.setId(appointmentType.getId());
			appointmentTypeForm.setName(appointmentType.getName());
			appointmentTypeForm
					.setDescription(appointmentType.getDescription());
			appointmentTypeForm.setCategory(appointmentType.getCategory());
			appointmentTypeForm.setDuration(appointmentType.getDuration());
			// appointmentTypeForm.setColor(appointmentType.getColor());
			appointmentTypeForm.setCharges(appointmentType.getCharges());
			appointmentTypeForm.setLocation(appointmentType.getLocation());
			appointmentTypeForm.setChargeType(appointmentType.getChargeType());
			appointmentTypeForm
					.setReportField(appointmentType.getReportField());
			appointmentTypeForm.setBasecharge(appointmentType.getBasecharge());
			appointmentTypeForm.setUserid(loginInfo.getUserId());
			if(appointmentType.getShareablecharge()!=null){
				if(appointmentType.getShareablecharge().equals("1")){
					appointmentType.setShareablecharge("true");
				}else{
					appointmentType.setShareablecharge("false");
				}
			}else{
				appointmentType.setShareablecharge("false");
			}
			appointmentTypeForm.setShareablecharge(appointmentType.getShareablecharge());
			appointmentTypeForm.setCode(appointmentType.getCode());
		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			connection.close();
		}

		return "editPage";
	}

	public String update() throws SQLException {
		if (!verifyLogin(request)) {
			return "login";
		}
		Connection connection = null;
		int result = 0;
		try {
			int id = appointmentTypeForm.getId();
			connection = Connection_provider.getconnection();
			AppointmentType appointmentType = new AppointmentType();
			AppointmentTypeDAO appointmentTypeDAO = new JDBCAppointmentTypeDAO(
					connection);
			String colorWhite = "FFFFFF";
			String color = "";
			String apmt = "Appointment Type";
			if (appointmentTypeForm.getChargeType().equals(apmt)) {
				if (appointmentTypeForm.getColor().equals(colorWhite)) {
					appointmentTypeForm.setColor(color);
				}
			}
			String userid= loginInfo.getUserId();
			appointmentType.setName(appointmentTypeForm.getName());
			appointmentType
					.setDescription(appointmentTypeForm.getDescription());
			appointmentType.setCategory(appointmentTypeForm.getCategory());
			appointmentType.setDuration(appointmentTypeForm.getDuration());
			// appointmentType.setColor(appointmentTypeForm.getColor());
			appointmentType.setCharges(appointmentTypeForm.getCharges());
			appointmentType.setLocation(appointmentTypeForm.getLocation());
			appointmentType.setChargeType(appointmentTypeForm.getChargeType());
			appointmentType
					.setReportField(appointmentTypeForm.getReportField());
			appointmentType.setBasecharge(appointmentTypeForm.getBasecharge());
			appointmentType.setCode(appointmentTypeForm.getCode());
			String Charge= appointmentTypeForm.getCharges();
			String appid= String.valueOf(appointmentTypeForm.getId());
			
			//  20 July 2018
			appointmentType.setShareablecharge(appointmentTypeForm.getShareablecharge());
			if(appointmentType.getShareablecharge()!=null){
				if(appointmentType.getShareablecharge().equals("true")){
					appointmentType.setShareablecharge("1");
				}else{
					appointmentType.setShareablecharge("0");
				}
			}else{
				appointmentType.setShareablecharge("0");
			}
			//shubbham
			
			AppointmentType appointment=new AppointmentType();
			appointment=appointmentTypeDAO.getMasterCharge(String.valueOf(id));
			String tpid=appointment.getTpid();
			if(tpid==null){
				tpid="0";
			}if(tpid.equals("")){
				tpid="0";
			}
			ThirdParty thirdParty=new ThirdParty();
			if(!tpid.equals("0")){
				ThirdPartyDAO thirdPartyDAO=new JDBCThirdPartyDAO(connection);
				thirdParty=thirdPartyDAO.getThirdPartyDetails(tpid);
			}
			appointmentType.setColumnchargename(thirdParty.getChargecolumnname());
			//lokesh
			String previouscharge= appointmentTypeDAO.getpreviousCharge(id,thirdParty.getChargecolumnname());
			result = appointmentTypeDAO.updateAppointmentType(appointmentType,
					id);
			if(loginInfo.isLmh()) {//Start new  for lmh rquirement
				ArrayList<AppointmentType> allpayerlist = appointmentTypeDAO
						.getPayerlist(id);
				
				for (AppointmentType appointmentType2 : allpayerlist) {
					 if(!appointmentType2.getDiscountShare().equals("0")) {
						   double discountamt =  Double.parseDouble(Charge) * (Double.parseDouble(appointmentType2.getDiscountShare())/100);
						   Double mrp1=Double.parseDouble(Charge)-discountamt;
						   appointmentType.setCharges(Double.toString(mrp1));
						   appointmentType.setDiscountAmt(mrp1);
						   appointmentType.setActualAmount(Charge);
					 }else {
						 appointmentType.setCharges((Charge));
					 }
					 result = appointmentTypeDAO.updateAppointmentType1(appointmentType,
								appointmentType2.getId());
				}
			}//end for lmh rquirement
			
			if(result==1){
				int log=0;
				log= appointmentTypeDAO.insertappointmentlog(userid, Charge, appid,previouscharge);
			}
			
			appointmentTypeForm
					.setMessage("Appointment Type Updated Sucessfully!!");
			addActionMessage("Appointment Type Updated Sucessfully!!");
			setFormData();
			appointmentTypeForm.setMastername(mastername);

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			connection.close();
		}
		return "updatecharges";
	}

	public String delete() throws SQLException {
		if (!verifyLogin(request)) {
			return "login";
		}
		int result = 0;
		int id = Integer.parseInt(request.getParameter("selectedid"));
		Connection connection = null;
		AppointmentType appointmentType = new AppointmentType();
		try {

			connection = Connection_provider.getconnection();

			AppointmentTypeDAO appointmentTypeDAO = new JDBCAppointmentTypeDAO(
					connection);

			result = appointmentTypeDAO.deleteAppoitmentType(id,
					appointmentType);
			appointmentTypeForm
					.setMessage("Appointment Type Removed Sucessfully!!");
			addActionMessage("Appointment Type Removed Sucessfully!!");
			setFormData();
			appointmentTypeForm.setMastername(mastername);
		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			connection.close();
		}

		return SUCCESS;
	}

	public String additionalCharge() throws SQLException {
		if (!verifyLogin(request)) {
			return "login";
		}
		Connection connection = null;
		try {

			String who = request.getParameter("who");

			connection = Connection_provider.getconnection();
			AppointmentDAO appointmentDAO = new JDBCAppointmentDAO(connection);
			ArrayList<AppointmentType> additionalChargeList = appointmentDAO
					.getAdditionalChaergeTypeList(who);

			StringBuffer str = new StringBuffer();
			str.append("<select class='form-control' name='addChargeType' id='addChargeType' onchange='setAdditionalChargeAjax(this.value)'>");
			str.append("<option value='0'>Select Additional Charge Type</option>");
			for (AppointmentType appointmentType : additionalChargeList) {
				str.append("<option value='" + appointmentType.getId() + "'>"
						+ appointmentType.getName() + "</option>");

			}
			str.append("</select>");

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

	public String additionalCharge1() throws SQLException {
		if (!verifyLogin(request)) {
			return "login";
		}
		Connection connection = null;
		try {

			String who = request.getParameter("who");

			connection = Connection_provider.getconnection();
			AppointmentDAO appointmentDAO = new JDBCAppointmentDAO(connection);
			ArrayList<AppointmentType> additionalChargeList = appointmentDAO
					.getAdditionalChaergeTypeList(who);

			StringBuffer str = new StringBuffer();
			str.append("<select class='form-control' name='addChargeType' id='addChargeType1' onchange='setAdditionalChargeAjax1(this.value)'>");
			str.append("<option value='0'>Select Additional Charge Type</option>");
			for (AppointmentType appointmentType : additionalChargeList) {
				str.append("<option value='" + appointmentType.getId() + "'>"
						+ appointmentType.getName() + "</option>");

			}
			str.append("</select>");

			response.setContentType("text/html");
			response.setHeader("Cache-Control", "no-cache");
			response.getWriter().write("" + str.toString() + "");

		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			
			connection.close();
		}

		return null;
	}

	public String charge() throws Exception{
		if (!verifyLogin(request)) {
			return "login";
		}
		Connection connection = null;
		try {

			String selectedAppointmentID = request.getParameter("selectedAppointmentID");
			String masterchargetype = request.getParameter("masterchargetype");

			connection = Connection_provider.getconnection();
			AppointmentDAO appointmentDAO = new JDBCAppointmentDAO(connection);

			String charge = appointmentDAO.getSelectedCharge(selectedAppointmentID,masterchargetype);
			//physio therapy total session 
			String totalsession=appointmentDAO.getTotalsessionno(selectedAppointmentID,masterchargetype);
			if(totalsession.equals("")) {
				totalsession="0";
			}
			int issharable = appointmentDAO.getSelectedSharableChargeStatus(selectedAppointmentID);
			int noneditamt = appointmentDAO.getNonEditAmtSts(selectedAppointmentID);
			//for Ayushman
		   if(loginInfo.isAyushman()) {
			 if(loginInfo.isUnit_price()) {
				 noneditamt=0;
			}else {
				 noneditamt=1;
			}
			//end ayushman 
		 }	
			response.setContentType("text/html");
			response.setHeader("Cache-Control", "no-cache");
			response.getWriter().write("" + charge + "~"+issharable+"~"+noneditamt+"~"+loginInfo.isAdd_charge_amt_edit()+"~"+totalsession);

		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			
			connection.close();
		}

		return null;
	}
	
	
	

	public void prepare() throws Exception {
		appointmentTypeForm
				.setApmtDurationList(PopulateList.apmtDurationList());
		Connection connection = null;
		try {
			connection = Connection_provider.getconnection();
			NotAvailableSlotDAO notAvailableSlotDAO = new JDBCNotAvailableSlotDAO(
					connection);
			AppointmentTypeDAO appointmentTypeDAO = new JDBCAppointmentTypeDAO(connection);
			ArrayList<Location> locationList = notAvailableSlotDAO
					.getLocationList(loginInfo.getId());
			appointmentTypeForm.setLocationList(locationList);
			MasterDAO masterDAO = new JDBCMasterDAO(connection);

			AccountsDAO accountsDAO = new JDBCAccountsDAO(connection);
			ArrayList<Accounts> thirdPartyList = accountsDAO
					.getThirdPartyList(loginInfo.getId());
			// accountsForm.setLocationList(locationList);
			appointmentTypeForm.setThirdPartyList(thirdPartyList);
			DischargeOutcomeDAO outcomeDAO = new JDBCDischargeOutcomeDAO(
					connection);
			ArrayList<Master> masterlist = outcomeDAO.getMasterList(loginInfo);
			appointmentTypeForm.setMasterlist(masterlist);
			
			ArrayList<Master>newChargeTypeList = outcomeDAO.getNewChargeTypeList();
			appointmentTypeForm.setNewChargeTypeList(newChargeTypeList);
			mastername = (String) session.getAttribute("mastername");
			
			
			ArrayList<Master>ledgerList = appointmentTypeDAO.getManageLedgerList("0");
			
			appointmentTypeForm.setLedgerList(ledgerList);
			ArrayList<Master>ledgerserviceList = appointmentTypeDAO.getLedgerServiceList();
			appointmentTypeForm.setLedgerserviceList(ledgerserviceList);
			
			 ArrayList<Master>bankNameList = masterDAO.getBankNameList();
			 appointmentTypeForm.setBankNameList(bankNameList);
			 connection = Connection_provider.getconnection();
				SummaryReportDAO summaryReportDAO=new JDBCSummaryReportDAO(connection);
				ArrayList<SummaryReport>wardlist= summaryReportDAO.getWardlist();
				appointmentTypeForm.setWardlist(wardlist);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			connection.close();
		}

	}
	
	public String balancesheethorizontal() throws Exception{
		 return "balancesheethorizontal";
	}
	public String balancesheetvertical() throws Exception{
		 return "balancesheetvertical";
	}
	public String profitlosshorizontal() throws Exception{
		 return "profitlosshorizontal";
	}
	public String profitlossvertical() throws Exception{
		 return "profitlossvertical";
	}
	

	public AppointmentTypeForm getModel() {

		return appointmentTypeForm;
	}
	
	
	public String setnoneditable() throws SQLException{
	try {
		Connection connection = null;
		String id= request.getParameter("id");
		String checked=request.getParameter("checked");
		if(checked==null){
			checked="0";
		}
		if(checked.equals("")){
			checked="0";
		}
		if(checked.equals("true")){
			checked="1";
		}else{
			checked="0";
		}
		
			connection = Connection_provider.getconnection();
			MasterDAO masterDAO=new JDBCMasterDAO(connection);
			int res=masterDAO.setnoneditable(id,checked);
			response.setContentType("text/html");
			response.setHeader("Cache-Control", "no-cache");
		
				response.getWriter().write(""+res+"");
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		
		
		
		
		return null;
	}
	
	public String searchcreatecharge(){
		Connection connection =null;
		String searchtxt=request.getParameter("searchtext");
		
		String viewaccess=request.getParameter("viewaccess");
		try {
			connection= Connection_provider.getconnection();
			MasterDAO masterDAO = new JDBCMasterDAO(connection);
			ArrayList<AppointmentType> list= new ArrayList<AppointmentType>();
			list= masterDAO.getseachedCharge(searchtxt);
			StringBuffer buffer= new StringBuffer();
			
			for(AppointmentType p:list){
				buffer.append("<tr>");
				buffer.append("<td>"+p.getTpName()+"</td>");
				buffer.append("<td>"+p.getWardname()+"</td>");
				buffer.append("<td>"+p.getChargeType()+"</td>");
				buffer.append("<td>"+p.getName()+"</td>");
				buffer.append("<td class='text-center'>"+p.getCode()+"</td>");
				
				buffer.append("<td class='text-right'>"+p.getCharges()+"</td>");
				if(!viewaccess.equals("1")){
				buffer.append("<td class='text-left'>"+p.getDuration()+"</td>");
				buffer.append("<td class='text-center hidden-print'>");
				if(DateTimeUtils.numberCheck(p.getTpid()).equals("0")){
					buffer.append("<s:a href='#'onclick=opencPopup('editAppointmentType?selectedid="+p.getId()+"')><i class='fa fa-edit'></i></s:a>");
				}
				buffer.append("</td>");
				
				buffer.append(" <td class='text-center hidden-print'><a href=deleteAppointmentType?selectedid="+p.getId()+"   cssClass='text-danger'><i class='fa fa-trash-o'></i></a> </td>");
				
					if(p.getNoneditamt()==1){
						buffer.append("<td class='text-center hidden-print'><input checked='checked' type='checkbox' class='case setchk' value='"+p.getId()+"'class='form-control' name='nonedit' id='nonedit' onchange='setnoneditable("+p.getId()+",this.checked)'/></td>");
					}else{
						buffer.append("<td class='text-center hidden-print'><input  type='checkbox' class='case setchk' value='"+p.getId()+"'class='form-control' name='nonedit' id='nonedit' onchange='setnoneditable("+p.getId()+",this.checked)'/></td>");
					}
				}
				buffer.append("</tr>");
			}
			 response.setContentType("text/html");
		     response.setHeader("Cache-Control", "no-cache");
		     response.getWriter().write(""+buffer.toString()+""); 
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}