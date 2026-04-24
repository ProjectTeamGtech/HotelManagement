package com.apm.Expence.web.action;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.nio.Buffer;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.allcolor.yahp.converter.CYaHPConverter;
import org.allcolor.yahp.converter.IHtmlToPdfTransformer;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.struts2.ServletActionContext;

import com.apm.Accounts.eu.bi.AccountsDAO;
import com.apm.Accounts.eu.bi.ChargesAccountProcessingDAO;
import com.apm.Accounts.eu.blogic.jdbc.JDBCAccountsDAO;
import com.apm.Accounts.eu.blogic.jdbc.JDBCChargeAccountProcesDAO;
import com.apm.Appointment.eu.bi.AppointmentTypeDAO;
import com.apm.Appointment.eu.blogic.jdbc.JDBCAppointmentTypeDAO;
import com.apm.Appointment.eu.entity.Category;
import com.apm.Expence.eu.bi.ExpenManagementDAO;
import com.apm.Expence.eu.bi.blogic.jdbc.JDBCExpenceManagementDAO;
import com.apm.Expence.eu.entity.Expence;
import com.apm.Expence.web.form.ExpengeManagementForm;
import com.apm.Inventory.eu.bi.InventoryProductDAO;
import com.apm.Inventory.eu.bi.PoPaymenytDAO;
import com.apm.Inventory.eu.bi.ProcurementDAO;
import com.apm.Inventory.eu.blogic.jdbc.JDBCInventoryProductDAO;
import com.apm.Inventory.eu.blogic.jdbc.JDBCPoPaymengtDAO;
import com.apm.Inventory.eu.blogic.jdbc.JDBCProcurementDAO;
import com.apm.Inventory.eu.entity.Procurement;
import com.apm.Inventory.eu.entity.Product;
import com.apm.Log.eu.blogic.jdbc.JDBCAccountLogDAO;
import com.apm.Master.eu.bi.MasterDAO;
import com.apm.Master.eu.blogic.jdbc.JDBCMasterDAO;
import com.apm.Master.eu.entity.Master;
import com.apm.Mrd.eu.bi.MrdDAO;
import com.apm.Mrd.eu.blogic.JDBCMrdDAO;
import com.apm.Pharmacy.eu.bi.PharmacyDAO;
import com.apm.Pharmacy.eu.blogic.jdbc.JDBCPharmacyDAO;
import com.apm.Registration.eu.bi.ClinicDAO;
import com.apm.Registration.eu.bi.UserProfileDAO;
import com.apm.Registration.eu.blogic.jdbc.JDBCClinicDAO;
import com.apm.Registration.eu.blogic.jdbc.JDBCUserProfileDAO;
import com.apm.Registration.eu.entity.Clinic;
import com.apm.Registration.eu.entity.UserProfile;
import com.apm.Report.eu.bi.MisDAO;
import com.apm.Report.eu.blogic.jdbc.JDBCMisDAO;
import com.apm.Report.eu.entity.MisReport;
import com.apm.common.eu.blogic.jdbc.Connection_provider;
import com.apm.common.utils.DateTimeUtils;
import com.apm.common.utils.NumberToWord;
import com.apm.common.utils.Pagination;
import com.apm.common.web.action.BaseAction;
import com.apm.common.web.common.helper.LoginHelper;
import com.apm.common.web.common.helper.LoginInfo;
import com.apm.main.common.constants.Constants;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.Preparable;

import atg.taglib.json.util.JSONObject;

public class ExpenceManagementAction extends BaseAction implements Preparable,
		ModelDriven<ExpengeManagementForm> {

	HttpServletRequest request = (HttpServletRequest) ActionContext
			.getContext().get(ServletActionContext.HTTP_REQUEST);
	HttpServletResponse response = (HttpServletResponse) ActionContext
			.getContext().get(ServletActionContext.HTTP_RESPONSE);
	HttpSession session = request.getSession(true);
	LoginInfo loginInfo = LoginHelper.getLoginInfo(request);

	 Pagination pagination=new Pagination(15,1);

	ExpengeManagementForm expengeManagementForm = new ExpengeManagementForm();
	
	
	 public Pagination getPagination() {
			return pagination;
		}

		public void setPagination(Pagination pagination)  {
			this.pagination = pagination;
		}
		

	public String execute() throws Exception {
		if (!verifyLogin(request)) {
			return "login";
		}

		Connection connection = null;
		String action = request.getParameter("action");
		if(action==null){
			action = "0";
		}
		try {
			connection = Connection_provider.getconnection();
			ExpenManagementDAO expenManagementDAO = new JDBCExpenceManagementDAO(connection);
			
			String expenseType =expengeManagementForm.getExpenseType();  
    		String range=expengeManagementForm.getRange();
    		String paymentmode=expengeManagementForm.getMainpaymentmode();
    		String fromdate = expengeManagementForm.getFromdate();
    		String todate = expengeManagementForm.getTodate();
    		
    		if(expenseType==null){
    			expenseType="0";
    		}else if(expenseType.equals("")){
    			expenseType="0";
    		}
    		
    		if(range==null){
    			range="0";
    		}else if(range.equals("")){
    			range="0";
    		}
    		
    		if(paymentmode==null){
    			paymentmode="0";
    		}else if(paymentmode.equals("")){
    			paymentmode="0";
    		}
    		
    		
    		DateFormat dateFormats = new SimpleDateFormat("dd/MM/yyyy");
			Calendar cals = Calendar.getInstance();
			//cals.add(Calendar.DATE, -7); 
			String hdnfrmdate = dateFormats.format(cals.getTime());
			
			expengeManagementForm.setHdnfrmdate(hdnfrmdate);
    		
    		String fromDate = expengeManagementForm.getFromdate();
    		String toDate = expengeManagementForm.getTodate();	
    		
    		if(fromDate.equals("")){
    			DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
    			Calendar cal = Calendar.getInstance();
    			cal.add(Calendar.DATE, -7); 
    			fromDate = dateFormat.format(cal.getTime());
    			expengeManagementForm.setFromdate(fromDate);
    		}
    		if(toDate.equals("")){
    			DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
    			Calendar cal = Calendar.getInstance();
    			//cal.add(Calendar.DATE, -7); 
    			toDate = dateFormat.format(cal.getTime());
    			expengeManagementForm.setTodate(toDate);
    		}
    		
    		
    		if(!fromDate.equals("")){
    			String temp[]= fromDate.split("/");
    			fromDate = temp[2]+"-"+temp[1]+"-"+temp[0];
    		}
    		if(!toDate.equals("")){
    			String temp1[]= toDate.split("/");
    			toDate = temp1[2]+"-"+temp1[1]+"-"+temp1[0];
    		}
			
    		int count=expenManagementDAO.getTotalPaymentVoucher(fromDate,toDate,expenseType,range,paymentmode);
			pagination.setPreperties(count);

			if(action.equals("0")){
				session.setAttribute("vexpenseType", expenseType);	
			}
			if(action.equals("1")){
				expenseType = (String)session.getAttribute("vexpenseType");
			}
			
			ArrayList<Expence> expenceList = expenManagementDAO.getExpenceList(fromDate,toDate,expenseType,range,paymentmode,action);
			expengeManagementForm.setExpenceList(expenceList);
			pagination.setPage_records(expenceList.size());
			expengeManagementForm.setTotalRecords(count);
			expengeManagementForm.setPagerecords(String.valueOf(pagination.getPage_records()));
			
			int size= expenceList.size();
	        if(size>0){
	        	 double expenceTotal = expenceList.get(size-1).getTotalamount();
	        	 expengeManagementForm.setExpenceTotal(""+expenceTotal);
	        	 
	        	 String ctotal = expenceList.get(size-1).getCtotal();
	        	 expengeManagementForm.setCtotal(ctotal);
	      	 } else {
	      		expengeManagementForm.setExpenceTotal("0");
	         }

			/*DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
			Calendar cal = Calendar.getInstance();
			cal.add(Calendar.DATE, -7);
			String fromDate = dateFormat.format(cal.getTime());
			//expengeManagementForm.setFromdate(fromDate);
			expengeManagementForm.setFromdate("");
						
			dateFormat = new SimpleDateFormat("dd/MM/yyyy");
			cal = Calendar.getInstance();
			// cal.add(Calendar.DATE, -7);
			String toDate = dateFormat.format(cal.getTime());
			//expengeManagementForm.setTodate(toDate);
			expengeManagementForm.setTodate("");*/

			/*double total = expenManagementDAO.getExpenceTotal();
			String expenceTotal = DateTimeUtils.changeFormat(total);
			expengeManagementForm.setExpenceTotal(expenceTotal);*/
			
			expengeManagementForm.setExpenseType(expenseType);
			expengeManagementForm.setPaymentmode(paymentmode);
			expengeManagementForm.setRange(range);
			/*expengeManagementForm.setFromdate(DateTimeUtils.getCommencingDate1(fromdate));
			expengeManagementForm.setTodate(DateTimeUtils.getCommencingDate1(todate));*/
			//  04 oct 2017 by default todate set while adding
			String caldate="";
			DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		    Calendar cal = Calendar.getInstance(); 
		    caldate = dateFormat.format(cal.getTime());
			expengeManagementForm.setDate(caldate);
			
			
			
			if(action.equals("1")){
				
			String vexpenseType = (String) session.getAttribute("vexpenseType");
			int vendorid = expenManagementDAO.getExpenceVendorid(vexpenseType);
			
			Master master = expenManagementDAO.getVendorDetails(vendorid);
			expengeManagementForm.setName(master.getName());
			expengeManagementForm.setVaddress(master.getAddress());
				
			 	Clinic clinic = new Clinic();
				ClinicDAO clinicDAO  = new JDBCClinicDAO(connection);
				AccountsDAO accountsDAO = new JDBCAccountsDAO(connection);
				
				ArrayList<Clinic> locationAdressList = accountsDAO.getLocationAddress(loginInfo.getClinicid());
				
				clinic = clinicDAO.getCliniclistDetails(loginInfo.getClinicid());
				expengeManagementForm.setClinicName(clinic.getClinicName());
				expengeManagementForm.setClinicOwner(clinic.getClinicOwner());
				expengeManagementForm.setOwner_qualification(clinic.getOwner_qualification());
				expengeManagementForm.setLocationAdressList(locationAdressList);
				expengeManagementForm.setAddress(clinic.getAddress());
				expengeManagementForm.setLandLine(clinic.getLandLine());
				expengeManagementForm.setClinicemail(clinic.getEmail());
				expengeManagementForm.setWebsiteUrl(clinic.getWebsiteUrl());
				expengeManagementForm.setClinicLogo(clinic.getUserImageFileName());

				
				return "prinexpencereport";
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			connection.close();
		}

		return super.execute();
	}

	public String report() throws Exception{
		if (!verifyLogin(request)) {
			return "login";
		}

		Connection connection = null;

		try {
			connection = Connection_provider.getconnection();
			ExpenManagementDAO expenManagementDAO = new JDBCExpenceManagementDAO(
					connection);

			String date = DateTimeUtils.getUKCurrentDataTime(loginInfo
					.getTimeZone());
			int result = expenManagementDAO.addReportData(
					expengeManagementForm.getTotalExpenceCheckbox(), date,
					expengeManagementForm.getReportName());

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			connection.close();
		}

		return viewreport();
	}
	
	
	public String debitor() throws Exception{
		
		Connection connection = null;
		try {
			
			String debitor = request.getParameter("debitor");

			connection = Connection_provider.getconnection();
			ExpenManagementDAO expenManagementDAO = new JDBCExpenceManagementDAO(
					connection);
			
			int res = expenManagementDAO.saveNewLedgerDebitors(debitor);
			
			ArrayList<Master>debitorList = expenManagementDAO.getDebitorList();
			
			StringBuffer str = new StringBuffer();
			
			str.append("<select  name='debiorname' id='debiorname' class='form-control showToolTip chosen' >");
      		str.append("<option value='0'>Select Debitors</option>");
      		for(Master master : debitorList){
      			str.append("<option value='"+master.getId()+"'>"+master.getName()+"</option>");
      			//str.append("<option value='"+master.getName()+"'>"+master.getName()+"</option>");
      		}
      		str.append("</select>");
      		
      		
      		response.setContentType("text/html");
				response.setHeader("Cache-Control", "no-cache");
				response.getWriter().write(""+str.toString()+""); 
			
		}catch (Exception e) {
			// TODO: handle exception
		}finally {
			connection.close();
		}
		
		return null;
	}

	public String viewreport() throws Exception {

		Connection connection = null;
		try {

			connection = Connection_provider.getconnection();
			ExpenManagementDAO expenManagementDAO = new JDBCExpenceManagementDAO(
					connection);
			ArrayList<Expence> reportList = expenManagementDAO.getReportList();

			expengeManagementForm.setReportList(reportList);

		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			connection.close();
		}

		return "viewexpencereport";
	}

	public String print() throws Exception {

		Connection connection = null;
		try {

			connection = Connection_provider.getconnection();

			Clinic clinic = new Clinic();
			ClinicDAO clinicDAO = new JDBCClinicDAO(connection);
			ExpenManagementDAO expenManagementDAO = new JDBCExpenceManagementDAO(
					connection);

			clinic = clinicDAO.getCliniclistDetails(loginInfo.getClinicid());

			expengeManagementForm.setClinicName(clinic.getClinicName());
			expengeManagementForm.setClinicOwner(clinic.getClinicOwner());
			expengeManagementForm.setCountry(loginInfo.getCountry());

			String id = request.getParameter("id");

			Expence expence = expenManagementDAO.getReportIdList(id);
			expengeManagementForm.setReportName(expence.getReportName());

			ArrayList<Expence> reportExpenList = expenManagementDAO
					.getReportExpenseList(expence.getReportIdList());
			expengeManagementForm.setReportExpenList(reportExpenList);

			double total = expenManagementDAO.getReportExpenceTotal(expence
					.getReportIdList());
			String expenceTotal = DateTimeUtils.changeFormat(total);
			expengeManagementForm.setExpenceTotal(expenceTotal);

			String reportdate = expence.getCaldate();
			String temp[] = reportdate.split(" ");
			expengeManagementForm.setReportDate(temp[0]);

		} catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			connection.close();
		}

		return "prinexpencereport";
	}

	public String addnewrow() throws Exception {

		Connection connection = null;

		try {

			connection = Connection_provider.getconnection();
			ExpenManagementDAO expenManagementDAO = new JDBCExpenceManagementDAO(
					connection);
			
			String xpayment = request.getParameter("xpayment");
			int t=0;
			int rowcount = Integer.parseInt(request.getParameter("rowcount"));
			int index = rowcount;
			index--;
			//  04 oct 2017 by default todate date set
			String caldate="";
			DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		    Calendar cal = Calendar.getInstance(); 
		    caldate = dateFormat.format(cal.getTime());
			
			
			StringBuffer buffer = new StringBuffer();
			buffer.append("<tr>");
			buffer.append("<td class='tdline'><input type='text' class='form-control casedate' id='date"
					+ rowcount + "'  name='voucher[" + index + "].caldate' value='"+caldate+"'/>");
			buffer.append("</td>");
			buffer.append("<td class='tdline'>");
			ArrayList<Expence> categories = expenManagementDAO.getAllCategories(xpayment);
			buffer.append("<select class='form-control chosen-select' id='category'  name='voucher["
					+ index + "].category' onchange='showVendorBalance(this.value)' >");
			buffer.append("<option value='0'>Select Expense Type</option>");
			for (Expence expence : categories) {

				buffer.append("<option value='" + expence.getName() + "'>"
						+ expence.getName() + "</option>");
			}
			buffer.append("</select>");
			buffer.append("</td>");
			
		
			
			 

			buffer.append("<td class='tdline' style='display:none'>");
			buffer.append("<input type='text' class='form-control serheight' name='voucher["
					+ index + "].merchant'>");
			buffer.append("</td>");
			
			buffer.append("<td class='tdline'>");
			buffer.append("<input type='text' class='form-control serheight' name='voucher["
					+ index + "].comments'>");
			buffer.append("</td>");
			
			buffer.append("<td style='display:none' class='tdline'>");
			buffer.append("<select id='basic' class='selectpicker show-tick form-control' data-live-search='true' name='voucher["
					+ index + "].paidby'>");
			buffer.append("<option>Please Select</option><option>Please Select</option><option>Cash</option><option>Cheque</option><option>Credit/Debit Card</option><option>Online Transfer /ECS</option>");
			buffer.append("</select>");
			buffer.append("</td>");
			
			
			buffer.append("<td class='tdline'><input type='text' onchange='showcvcredittotal()' class='form-control caseccc' id='credit"+index+"' name='voucher["
					+ index + "].credit'/>");
			buffer.append("</td>");
			
			
			buffer.append("<td onchange='showcvdbittotal()' class='tdline'><input type='text' class='form-control caseddd' id='amount"+index+"' name='voucher["
					+ index + "].amount'/>");
			buffer.append("</td>");
			
			
			
			
			buffer.append("<td class='tdline' style=''>");
			buffer.append("<select id='basic' class='selectpicker show-tick form-control' name='voucher["+index+"].currency' data-live-search='true'>");
			
			ArrayList<Expence> currencies=expenManagementDAO.getAllCurrencies();
			expengeManagementForm.setCurrencies(currencies);
			buffer.append("<option value='INR'>INR</option>");
			for(Expence expence:currencies) {
				
				buffer.append("<option value='"+expence.getName()+"'>"+expence.getName()+"</option>");
			}
			
			buffer.append("</select>"); 
		
			buffer.append("</td>");
			
			buffer.append("<td class='text-center' id='tdbutton0'>");
			buffer.append("<a href='#' onclick='deleteRow(this)'>");
			buffer.append("<i class='fa fa-minus-circle'");
			buffer.append("style='color:#c50404;font-size:17px;padding-top: 2px;'");
			
			buffer.append("aria-hidden='true'></i>");
			buffer.append("</a></td>");
			 
             
            
			
				
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
	
	public String bnk() throws SQLException{
		Connection connection = null;
		String xpayment = request.getParameter("xpayment");

		try {

			connection = Connection_provider.getconnection();
			MasterDAO masterDAO = new JDBCMasterDAO(connection);
			AppointmentTypeDAO appointmentTypeDAO = new JDBCAppointmentTypeDAO(connection);
			
			String ltype = "1";
			if(xpayment.equals("Contra")){
				ltype = "2";
			}
			ArrayList<Master>bankNameList = appointmentTypeDAO.getLedgerList(ltype)	;		
			StringBuffer buffer = new StringBuffer();
			
			String index = "0";
			buffer.append("<select class='selectpicker show-tick form-control' data-live-search='true' name='voucher["
					+ index + "].category' id='category' >");
			buffer.append("<option value='0'>Select Expense Type</option>");
			for (Master expence : bankNameList) {

				buffer.append("<option value='" + expence.getName() + "'>"
						+ expence.getName() + "</option>");
			}
			buffer.append("</select>");
			
			response.setContentType("text/html");
			response.setHeader("Cache-Control", "no-cache");
			response.getWriter().write(buffer.toString());
			
		}catch (Exception e) {
			// TODO: handle exception
		} finally {
			connection.close();
		}
		
		
		
		return null;
	}

	public String save() throws Exception {

		Connection connection = null;

		try {

			connection = Connection_provider.getconnection();
			ExpenManagementDAO expenManagementDAO = new JDBCExpenceManagementDAO(
					connection);
			
			ChargesAccountProcessingDAO chargesAccountProcessingDAO = new JDBCChargeAccountProcesDAO(connection);
			PoPaymenytDAO poPaymenytDAO = new JDBCPoPaymengtDAO(connection);
			AppointmentTypeDAO appointmentTypeDAO = new JDBCAppointmentTypeDAO(connection);
			String uid=loginInfo.getUserId();
		
			String pledgerid = expengeManagementForm.getLedgername();
			String ptype = expengeManagementForm.getEpayment();
			String pmode = expengeManagementForm.getPmode();
			String paymantto = expengeManagementForm.getPaymantto();
			if(expengeManagementForm.getEpayment().equals("Payment")){
				paymantto = expengeManagementForm.getDebiorname();
			}
			
			if(expengeManagementForm.getEditaction().equals("1")){
				
				ExpenManagementDAO dao = new JDBCExpenceManagementDAO(connection);
				
				String selectedid = (String)session.getAttribute("sessnslectedid");
				Expence expence = dao.getVoucherDetails(selectedid);
				String transtype = expence.getTransid();
				String editledgername = expence.getLedgerid();
				String editcatgoryid = expence.getCategory();
				System.out.println(editledgername);
				System.out.println(editcatgoryid);
				
				String epayment = expence.getXpayment();
				System.out.println(epayment);
				if(epayment.equals("Payment")){
					for (Expence ex : expengeManagementForm.getVoucher()) {
						String amt = ex.getCredit();
						String caldate = DateTimeUtils.getCommencingDate(ex.getCaldate());
						String description = ex.getComments();
						
						//update expence
						String cname = "credit";
						int res = expenManagementDAO.updateExpence(selectedid,amt,caldate,description,cname);
						
						//update debit in expences
						String colname = "debit";
						String catid = expenManagementDAO.getExpLedgid(editcatgoryid);
						res = expenManagementDAO.updateExpenceLedger(catid,amt,colname,selectedid);
						
						//update credit in official ledger
						colname = "credit";
						res = expenManagementDAO.updateExpenceLedger(editledgername,amt,colname,selectedid);
						
						double lamount = expenManagementDAO.getEditLedgerAmount(editledgername);
						int ula = expenManagementDAO.updateLedgerAmount(editledgername, lamount);
					}
				}
				if(epayment.equals("Receipt")){
					
					for (Expence ex : expengeManagementForm.getVoucher()) {
						String amt = ex.getAmount();
						String caldate = DateTimeUtils.getCommencingDate(ex.getCaldate());
						String description = ex.getComments();
						
						//update expence
						String cname = "amount";
						int res = expenManagementDAO.updateExpence(selectedid,amt,caldate,description,cname);
						
						//update credit in expences
						
						String colname = "credit";
						String catid = expenManagementDAO.getExpLedgid(editcatgoryid);
						res = expenManagementDAO.updateExpenceLedger(catid,amt,colname,selectedid);
						
						//update debit in official ledger
						colname = "debit";
						res = expenManagementDAO.updateExpenceLedger(editledgername,amt,colname,selectedid);
						
						double lamount = expenManagementDAO.getEditLedgerAmount(editledgername);
						int ula = expenManagementDAO.updateLedgerAmount(editledgername, lamount);
					}
				}
				
				//update contra
				if(epayment.equals("Contra")){
					for (Expence ex : expengeManagementForm.getVoucher()) {
						if(transtype.equals("1")){
							String amt = ex.getCredit();
							String caldate = DateTimeUtils.getCommencingDate(ex.getCaldate());
							String description = ex.getComments();
							
							//update expence
							String cname = "credit";
							int res = expenManagementDAO.updateExpence(selectedid,amt,caldate,description,cname);
							
							//update debit in expences
							String colname = "debit";
							String catid = expenManagementDAO.getExpLedgid(editcatgoryid);
							res = expenManagementDAO.updateExpenceLedger(catid,amt,colname,selectedid);
							
							//update credit in official ledger
							colname = "credit";
							res = expenManagementDAO.updateExpenceLedger(editledgername,amt,colname,selectedid);
						}else{
							//for contra withdrawl
							String amt = ex.getAmount();
							String caldate = DateTimeUtils.getCommencingDate(ex.getCaldate());
							String description = ex.getComments();
							
							//update expence
							String cname = "amount";
							int res = expenManagementDAO.updateExpence(selectedid,amt,caldate,description,cname);
							
							//update credit in expences
							
							String colname = "credit";
							String catid = expenManagementDAO.getExpLedgid(editcatgoryid);
							res = expenManagementDAO.updateExpenceLedger(catid,amt,colname,selectedid);
							
							//update debit in official ledger
							colname = "debit";
							res = expenManagementDAO.updateExpenceLedger(editledgername,amt,colname,selectedid);
							
						}
						
					}//for
						
				}
				if(epayment.equals("Journal")){
					
					for (Expence ex : expengeManagementForm.getVoucher()) {
						 if(ex.getAmount()==null){
							 ex.setAmount("0");
							}
							
							if(ex.getAmount()!=null){
								if(ex.getAmount().equals("")){
									ex.setAmount("0");
								}
							}
							
							if(ex.getCredit()==null){
								ex.setCredit("0");
							}
							
							if(ex.getCredit()!=null){
								if(ex.getCredit().equals("")){
									ex.setCredit("0");
								}
							}
							
							String caldate = DateTimeUtils.getCommencingDate(ex.getCaldate());
							String description = ex.getComments();
							
							//update expence
							
							
							int res = expenManagementDAO.updateJvExpence(selectedid,ex.getCredit(),ex.getAmount(),caldate,description);
							
							//update credit in expences
							
							
							String catid = expenManagementDAO.getExpLedgid(editcatgoryid);
							res = expenManagementDAO.updateJvExpenceLedger(catid,ex.getCredit(),ex.getAmount(),selectedid);

					
				}
					
			}	
				
			}else{
				
			
			String pcommencing = DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone());
			
			int pno = 0;
			int rno = 0;
			int cno = 0;
			int jno = 0;
			int ono = 0;
			int purno = 0;
			String column = "";
			
			if(expengeManagementForm.getEpayment().equals("Purchase")){
				column = "purno";
				purno = expenManagementDAO.getExpenceMaxno(column);
				
				
			}
			
			if(expengeManagementForm.getEpayment().equals("Opening")){
				column = "ono";
				ono = expenManagementDAO.getExpenceMaxno(column);
			}
		
			if(expengeManagementForm.getEpayment().equals("Payment")){
				column = "pno";
				pno = expenManagementDAO.getExpenceMaxno(column);
			}
			if(expengeManagementForm.getEpayment().equals("Receipt")){
				column = "rno";
				rno = expenManagementDAO.getExpenceMaxno(column);
			}
			
			if(expengeManagementForm.getEpayment().equals("Contra")){
				column = "cno";
				cno = expenManagementDAO.getExpenceMaxno(column);
			}
			if(expengeManagementForm.getEpayment().equals("Journal")){
				column = "jno";
				jno = expenManagementDAO.getExpenceMaxno(column);
			}
			
			
			int parentid = expenManagementDAO.saveParentExpData(pledgerid,ptype,pmode,
					paymantto,pcommencing,uid,pno,rno,cno,jno,purno);
			
			double tamt = 0;
			for (Expence expence : expengeManagementForm.getVoucher()) {
				
				if(expengeManagementForm.getEpayment().equals("Purchase")){
					PharmacyDAO pharmacyDAO = new JDBCPharmacyDAO(connection);
					
					String locationname = pharmacyDAO.getLocationName("36");
					String serviceid = chargesAccountProcessingDAO.getLedgrServiceIds(locationname);
					pledgerid = chargesAccountProcessingDAO.getExpenceLedgerId(expence.getCategory());
					//String ledgerid = chargesAccountProcessingDAO.getledgerIDNew(serviceid, "0", "0","1",vendoridnew);

				}
				

				String credit = expence.getCredit();
				System.out.println(credit);
			    checkCurrencies(expence);
			    
			    expence.setUserid(uid);
			    
			    if(expence.getAmount()==null){
					expence.setAmount("0");
				}
				
				if(expence.getAmount()!=null){
					if(expence.getAmount().equals("")){
						expence.setAmount("0");
					}
				}
				
				if(expence.getCredit()==null){
					expence.setCredit("0");
				}
				
				if(expence.getCredit()!=null){
					if(expence.getCredit().equals("")){
						expence.setCredit("0");
					}
				}
			    
			    tamt =  Double.parseDouble(expence.getAmount());
			    if(tamt==0){
			    	tamt = Double.parseDouble(expence.getCredit());
			    }
				
			    expence.setLedgerid(pledgerid);
			    expence.setTransid(expengeManagementForm.getContratrans());
				int expenceid = expenManagementDAO.addPaymentVoucher(expence,parentid,expengeManagementForm.getEpayment());
				
		if(expengeManagementForm.getEpayment().equals("Purchase")){
					
					
					if(Double.parseDouble(expence.getAmount())!=0){
						String ledgerid = chargesAccountProcessingDAO.getExpenceLedgerId(expence.getCategory());
						int vendorid = expenManagementDAO.checkforvendor(ledgerid);
						double lbal = chargesAccountProcessingDAO.getAheadLedgerBalance(ledgerid);
						lbal = lbal + tamt;
						 credit = "0";
						String ldebit = ""+expence.getAmount()+"";
						String product = expence.getCategory();
						String partyid = "xxxxx";
						String lcommencing = DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone());
						//int saveledger = appointmentTypeDAO.saveLedgerAhead(partyid,product,ldebit,credit,lbal,Integer.parseInt(ledgerid),lcommencing,"");
						int expnctype = 1;
						int saveledger = chargesAccountProcessingDAO.saveExpenceLedger(partyid, product, ldebit, credit, lbal, ledgerid,
								lcommencing, "0",0,expnctype,expengeManagementForm.getEpayment(),parentid,expenceid);
						int paymentids =poPaymenytDAO.saveParentPaymentData(""+vendorid,loginInfo.getUserId(),lcommencing,ldebit,"0","0",0,0);
						
						// code for show balance @Rahul
						Procurement procurement=new Procurement();
						procurement.setPaymentAmount(expence.getAmount());
						int rsult = poPaymenytDAO.savePayment(procurement,paymentids);
						 double lamount = expenManagementDAO.getDbLedgerAmount(ledgerid);
						 lamount = lamount - Double.parseDouble(ldebit);
						 int ula = expenManagementDAO.updateLedgerAmount(ledgerid,lamount);
						 int updateledgersheet = expenManagementDAO.updatePaymentLedgerSheet(saveledger,paymentids);
						

						
					}
					
					
					//second entry
					if(Double.parseDouble(expence.getCredit())!=0){
						
						String ledgerid = chargesAccountProcessingDAO.getExpenceLedgerId(expence.getCategory());
						double lbal = chargesAccountProcessingDAO.getAheadLedgerBalance(ledgerid);
						lbal = lbal - tamt;
						 credit = ""+expence.getCredit()+"";
						String ldebit = "0";
						String product = expence.getCategory();
						String partyid = paymantto;
						String lcommencing = DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone());
						//int saveledger = appointmentTypeDAO.saveLedgerAhead(partyid,product,ldebit,credit,lbal,Integer.parseInt(ledgerid),lcommencing);
						
						int expnctype = 2;
						int saveledger = chargesAccountProcessingDAO.saveExpenceLedger(partyid, product, ldebit, credit, lbal, ledgerid,
								lcommencing, "0",0,expnctype,expengeManagementForm.getEpayment(),parentid,expenceid);
					}
					
				}
				
				
				if(expengeManagementForm.getEpayment().equals("Journal")){
					
					
					if(Double.parseDouble(expence.getAmount())!=0){
						String ledgerid = chargesAccountProcessingDAO.getExpenceLedgerId(expence.getCategory());
						double lbal = chargesAccountProcessingDAO.getAheadLedgerBalance(ledgerid);
						lbal = lbal + tamt;
						 credit = "0";
						String ldebit = ""+expence.getAmount()+"";
						String product = expence.getCategory();
						String partyid = "xxxxx";
						String lcommencing = DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone());
						//int saveledger = appointmentTypeDAO.saveLedgerAhead(partyid,product,ldebit,credit,lbal,Integer.parseInt(ledgerid),lcommencing,"");
						int expnctype = 1;
						int saveledger = chargesAccountProcessingDAO.saveExpenceLedger(partyid, product, ldebit, credit, lbal, ledgerid,
								lcommencing, "0",0,expnctype,expengeManagementForm.getEpayment(),parentid,expenceid);
						
					}
					
					
					//second entry
					if(Double.parseDouble(expence.getCredit())!=0){
						
						String ledgerid = chargesAccountProcessingDAO.getExpenceLedgerId(expence.getCategory());
						double lbal = chargesAccountProcessingDAO.getAheadLedgerBalance(ledgerid);
						lbal = lbal - tamt;
						 credit = ""+expence.getCredit()+"";
						String ldebit = "0";
						String product = expence.getCategory();
						String partyid = paymantto;
						String lcommencing = DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone());
						//int saveledger = appointmentTypeDAO.saveLedgerAhead(partyid,product,ldebit,credit,lbal,Integer.parseInt(ledgerid),lcommencing);
						
						int expnctype = 2;
						int saveledger = chargesAccountProcessingDAO.saveExpenceLedger(partyid, product, ldebit, credit, lbal, ledgerid,
								lcommencing, "0",0,expnctype,expengeManagementForm.getEpayment(),parentid,expenceid);
					}
					
				}
				
				if(expengeManagementForm.getEpayment().equals("Payment")){
					
					String ledgerid = chargesAccountProcessingDAO.getExpenceLedgerId(expence.getCategory());
					double lbal = chargesAccountProcessingDAO.getAheadLedgerBalance(ledgerid);
					lbal = lbal + tamt;
					 credit = "0";
					String ldebit = ""+tamt+"";
					String product = expence.getCategory();
					String partyid = "xxxxx";
					String lcommencing = DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone());
					//int saveledger = appointmentTypeDAO.saveLedgerAhead(partyid,product,ldebit,credit,lbal,Integer.parseInt(ledgerid),lcommencing,"");
					int expnctype = 1;
					int saveledger = chargesAccountProcessingDAO.saveExpenceLedger(partyid, product, ldebit, credit, lbal, ledgerid,
							lcommencing, "0",0,expnctype,expengeManagementForm.getEpayment(),parentid,expenceid);
					
					//check for vendor
					int vendorid = expenManagementDAO.checkforvendor(ledgerid);
					
					//insert into official ledger
					 ledgerid = expengeManagementForm.getLedgername();
					 lbal = chargesAccountProcessingDAO.getAheadLedgerBalance(ledgerid);
					lbal = lbal - tamt;
					 credit = ""+tamt+"";
					 ldebit = "0";
					 product = "xxxxx";
					 partyid = paymantto;
					 lcommencing = DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone());
					//int saveledger = appointmentTypeDAO.saveLedgerAhead(partyid,product,ldebit,credit,lbal,Integer.parseInt(ledgerid),lcommencing);
					
					 expnctype = 2;
					 saveledger = chargesAccountProcessingDAO.saveExpenceLedger(partyid, product, ldebit, credit, lbal, ledgerid,
							lcommencing, "0",0,expnctype,expengeManagementForm.getEpayment(),parentid,expenceid);
					 
					 //update in ledger_amount table
					 double lamount = expenManagementDAO.getDbLedgerAmount(ledgerid);
					 lamount = lamount - tamt;
					 int ula = expenManagementDAO.updateLedgerAmount(ledgerid,lamount);
					 
					 
					 int uol = chargesAccountProcessingDAO.updateofficicaledgerstatus(saveledger);
					 
					 //vendor payment to inventory account
					 String paymode = expenManagementDAO.getLedgerPaymentMode(ledgerid);
					 
					 if(vendorid!=0){
						 createVendorPayment(vendorid,paymode,tamt);
						 
						 System.out.println(vendorid);
					 }
					 
					 
				}
				
				
				if(expengeManagementForm.getEpayment().equals("Receipt")){
					
					String ledgerid = chargesAccountProcessingDAO.getExpenceLedgerId(expence.getCategory());
					double lbal = chargesAccountProcessingDAO.getAheadLedgerBalance(ledgerid);
					lbal = lbal - tamt;
					 credit = ""+tamt+"";
					String ldebit = "0";
					String product = expence.getCategory();
					String partyid = paymantto;
					String lcommencing = DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone());
					//int saveledger = appointmentTypeDAO.saveLedgerAhead(partyid,product,ldebit,credit,lbal,Integer.parseInt(ledgerid),lcommencing);
					
					
					int expnctype = 2;
					int saveledger = chargesAccountProcessingDAO.saveExpenceLedger(partyid, product, ldebit, credit, lbal, ledgerid,
							lcommencing, "0",0,expnctype,expengeManagementForm.getEpayment(),parentid,expenceid);
					
					//insert into official ledgers
					 ledgerid = expengeManagementForm.getLedgername();
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
							lcommencing, "0",0,expnctype,expengeManagementForm.getEpayment(),parentid,expenceid);
					 
					 //update in ledger_amount table
					 double lamount = expenManagementDAO.getDbLedgerAmount(ledgerid);
					 lamount = lamount + tamt;
					 int ula = expenManagementDAO.updateLedgerAmount(ledgerid,lamount);

					 //update official ledger status
					 int uol = chargesAccountProcessingDAO.updateofficicaledgerstatus(saveledger);
				}
				
				
				//contra expence type
				if(expengeManagementForm.getEpayment().equals("Contra")){
					if(expengeManagementForm.getContratrans().equals("2")){
						String ledgerid = chargesAccountProcessingDAO.getExpenceLedgerId(expence.getCategory());
						double lbal = chargesAccountProcessingDAO.getAheadLedgerBalance(ledgerid);
						lbal = lbal - tamt;
						 credit = ""+tamt+"";
						String ldebit = "0";
						String product = expence.getCategory();
						String partyid = paymantto;
						String lcommencing = DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone());
						//int saveledger = appointmentTypeDAO.saveLedgerAhead(partyid,product,ldebit,credit,lbal,Integer.parseInt(ledgerid),lcommencing);
						
						int expnctype = 2;
						int saveledger = chargesAccountProcessingDAO.saveExpenceLedger(partyid, product, ldebit, credit, lbal, ledgerid,
								lcommencing, "0",0,expnctype,expengeManagementForm.getEpayment(),parentid,expenceid);
						
						//insert into offficial ledger
						 ledgerid = expengeManagementForm.getLedgername();
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
								lcommencing, "0",0,expnctype,expengeManagementForm.getEpayment(),parentid,expenceid);
						 
						 int uol = chargesAccountProcessingDAO.updateofficicaledgerstatus(saveledger);
					}
					
					if(expengeManagementForm.getContratrans().equals("1")){
						
						String ledgerid = chargesAccountProcessingDAO.getExpenceLedgerId(expence.getCategory());
						double lbal = chargesAccountProcessingDAO.getAheadLedgerBalance(ledgerid);
						lbal = lbal + tamt;
						 credit = "0";
						String ldebit = ""+tamt+"";
						String product = expence.getCategory();
						String partyid = "xxxxx";
						String lcommencing = DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone());
						//int saveledger = appointmentTypeDAO.saveLedgerAhead(partyid,product,ldebit,credit,lbal,Integer.parseInt(ledgerid),lcommencing,"");
						int expnctype = 1;
						int saveledger = chargesAccountProcessingDAO.saveExpenceLedger(partyid, product, ldebit, credit, lbal, ledgerid,
								lcommencing, "0",0,expnctype,expengeManagementForm.getEpayment(),parentid,expenceid);
						
						
						//insert int official ledger
						 ledgerid = expengeManagementForm.getLedgername();
						 lbal = chargesAccountProcessingDAO.getAheadLedgerBalance(ledgerid);
						lbal = lbal - tamt;
						 credit = ""+tamt+"";
						 ldebit = "0";
						 product = "xxxxx";
						 partyid = paymantto;
						 lcommencing = DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone());
						//int saveledger = appointmentTypeDAO.saveLedgerAhead(partyid,product,ldebit,credit,lbal,Integer.parseInt(ledgerid),lcommencing);
						
						 expnctype = 2;
						 saveledger = chargesAccountProcessingDAO.saveExpenceLedger(partyid, product, ldebit, credit, lbal, ledgerid,
								lcommencing, "0",0,expnctype,expengeManagementForm.getEpayment(),parentid,expenceid);
						 
						 int uol = chargesAccountProcessingDAO.updateofficicaledgerstatus(saveledger);
						
					}
				}
			
			}
		}//else
			
			//crediting ledger
			//if(expengeManagementForm.getPmode().equals("Cash")){
			
	/*		if(expengeManagementForm.getEpayment().equals("Contra")){
				if(expengeManagementForm.getContratrans().equals("1")){
					String ledgerid = expengeManagementForm.getLedgername();
					double lbal = chargesAccountProcessingDAO.getAheadLedgerBalance(ledgerid);
					lbal = lbal - tamt;
					String credit = ""+tamt+"";
					String ldebit = "0";
					String product = expence.getCategory();
					String partyid = paymantto;
					String lcommencing = DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone());
					//int saveledger = appointmentTypeDAO.saveLedgerAhead(partyid,product,ldebit,credit,lbal,Integer.parseInt(ledgerid),lcommencing);
					
					int expnctype = 2;
					int saveledger = chargesAccountProcessingDAO.saveExpenceLedger(partyid, product, ldebit, credit, lbal, ledgerid,
							lcommencing, "0",0,expnctype,expengeManagementForm.getEpayment());
				}
				
				if(expengeManagementForm.getContratrans().equals("2")){
					
					String ledgerid = expengeManagementForm.getLedgername();
					double lbal = chargesAccountProcessingDAO.getAheadLedgerBalance(ledgerid);
					lbal = lbal + tamt;
					String credit = "0";
					String ldebit = ""+tamt+"";
					String product = expence.getCategory();
					String partyid = "xxxxx";
					String lcommencing = DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone());
					//int saveledger = appointmentTypeDAO.saveLedgerAhead(partyid,product,ldebit,credit,lbal,Integer.parseInt(ledgerid),lcommencing,"");
					int expnctype = 1;
					int saveledger = chargesAccountProcessingDAO.saveExpenceLedger(partyid, product, ldebit, credit, lbal, ledgerid,
							lcommencing, "0",0,expnctype,expengeManagementForm.getEpayment());
					
				}
			}*/
				
				/*if(expengeManagementForm.getEpayment().equals("Receipt")){
					String ledgerid = expengeManagementForm.getLedgername();
					double lbal = chargesAccountProcessingDAO.getAheadLedgerBalance(ledgerid);
					lbal = lbal + tamt;
					String credit = "0";
					String ldebit = ""+tamt+"";
					String product = expence.getCategory();
					String partyid = "xxxxx";
					String lcommencing = DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone());
					//int saveledger = appointmentTypeDAO.saveLedgerAhead(partyid,product,ldebit,credit,lbal,Integer.parseInt(ledgerid),lcommencing,"");
					int expnctype = 1;
					int saveledger = chargesAccountProcessingDAO.saveExpenceLedger(partyid, product, ldebit, credit, lbal, ledgerid,
							lcommencing, "0",0,expnctype,expengeManagementForm.getEpayment());
				}
				*/
				
			
		/*	if(expengeManagementForm.getEpayment().equals("Payment")){
					String ledgerid = expengeManagementForm.getLedgername();
					double lbal = chargesAccountProcessingDAO.getAheadLedgerBalance(ledgerid);
					lbal = lbal - tamt;
					String credit = ""+tamt+"";
					String ldebit = "0";
					String product = "xxxxx";
					String partyid = paymantto;
					String lcommencing = DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone());
					//int saveledger = appointmentTypeDAO.saveLedgerAhead(partyid,product,ldebit,credit,lbal,Integer.parseInt(ledgerid),lcommencing);
					
					int expnctype = 2;
					int saveledger = chargesAccountProcessingDAO.saveExpenceLedger(partyid, product, ldebit, credit, lbal, ledgerid,
							lcommencing, "0",0,expnctype,expengeManagementForm.getEpayment());
				}*/
				
			//}
		
			
			
			//update Status of credit
			int t = expenManagementDAO.truncateLedgerAmount();
			int res = expenManagementDAO.refreshLedgerAmount();	
			

		} catch (Exception e) {

			e.printStackTrace();
		} finally {

			connection.close();
		}
		return "save";
	}

	private void createVendorPayment(int vendorid, String paymode, double tamt) {
		Connection connection =null;
		try {
			//  26-12-2018 vendor payment ledger
			connection = Connection_provider.getconnection();
			PoPaymenytDAO poPaymenytDAO = new JDBCPoPaymengtDAO(connection);
			ChargesAccountProcessingDAO chargesAccountProcessingDAO = new JDBCChargeAccountProcesDAO(connection);
			PharmacyDAO pharmacyDAO = new JDBCPharmacyDAO(connection);
			String date = DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone());
			ArrayList<Product> voucherList = poPaymenytDAO.getAllVoucherList(""+vendorid);
			int paymentids =poPaymenytDAO.saveParentPaymentData(""+vendorid,loginInfo.getUserId(),date,DateTimeUtils.changeFormat(tamt),"0","0",tamt,1);
			String bankid ="0";
			int count=0;
			String loction="0";
			String commencing= DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone()).split(" ")[0];
			double tempamt =tamt;
			for (Product product : voucherList) {
				//set locally temp total if tempamt gone in negative
				  double temptotal = tempamt;
				  Procurement procurement = new Procurement();
				  procurement.setProcurementid(product.getProcurementid());
				  tempamt = tempamt - Double.parseDouble(product.getBalance());
				  //if tempamt non negative then set balance amount
				  double paidamt =Double.parseDouble(product.getBalance());
				  if(tempamt<=0){
					  // if it negative then set remaining amount
					  paidamt = temptotal;
				  }
				  procurement.setProcurementid(product.getProcurementid());
				  procurement.setPaymentAmount(DateTimeUtils.changeFormat(paidamt));
				  procurement.setPaymentType(paymode);
				  procurement.setBankName("");
				  procurement.setCheqNo("");
				  procurement.setPaymentDate(commencing);
				  procurement.setCheqType("");
				  procurement.setHandoverTo("");
				  procurement.setVoucherno(product.getVoucherno());
				  procurement.setCheq_receiver("");
				  procurement.setBankid("0");
				  int rsult = poPaymenytDAO.savePayment(procurement,paymentids); 
				  if(count==0){
					  loction = product.getLocation();
				  }
				  count++;
				  if(tempamt>=0){
					  //set status of total payment done 
					  int test = poPaymenytDAO.updateProcurmnetPaymentStatus(procurement.getProcurementid());
				  }
				  if(tempamt<=0){
					  //if paid amount gone to negative it means it clear bill and not have remaing amount 
					  break;
				  }
				 
			}
			
			if(!paymode.equals("Cheque")){
				bankid ="0";
			}
			
			String locationname = pharmacyDAO.getLocationName(loction);
			String serviceid = chargesAccountProcessingDAO.getLedgrServiceIds(locationname);
			//String serviceid ="0";
			String ledgerid = chargesAccountProcessingDAO.getledgerIDNew(serviceid, paymode, bankid,"1",0);
			double tot = tamt;
			double lbal = chargesAccountProcessingDAO.getLedgerBalance(ledgerid);
			lbal = lbal + tot;
			String credit = "" + tot + "";
			String ldebit = "0";
			String product = "xxxxx";
			String partyid = "0";
			String lcommencing = DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone());
			int saveledger = chargesAccountProcessingDAO.saveLedger(partyid, product, ldebit, credit, lbal,
						ledgerid, lcommencing, "0", 0,"0","0",""+paymentids+"","0","0",vendorid,0,loction);
				
			//second effect
			lbal = 0;
			credit = "0";
			ldebit = "" + tot + "";
			product = "xxxxx";
			partyid = "0";
			lcommencing = DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone());
			saveledger = chargesAccountProcessingDAO.saveLedger(partyid, product, ldebit, credit, lbal,
						ledgerid, lcommencing, "0", 0,"0","0",""+paymentids+"","0","0",vendorid,0,loction);
			
			/*if(refundcheck.equals("1")){
				String grnreturnids = procurementForm.getGrnreturnids();
				if(grnreturnids!=null){
					for (String t : grnreturnids.split(",")) {
						if (t.equals("0")) {
							continue;
						}
						int results = poPaymenytDAO.updateReturnPaymentDoneStatus(t,paymentids); 
					}
				}
			}*/
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	private void checkCurrencies(Expence expence) throws Exception{

		String current_country=loginInfo.getCountry();
		Connection connection=null;
		try {
			
			connection=Connection_provider.getconnection();
			
		} catch (Exception e) {

		   e.printStackTrace();
		}
		
		ExpenManagementDAO dao=new JDBCExpenceManagementDAO(connection);
		
		if(expence.getCurrency().equalsIgnoreCase("INR") && current_country.equalsIgnoreCase("India")) {
					
		}
		else if(expence.getCurrency().equalsIgnoreCase("USD") && current_country.equalsIgnoreCase("India")) {
			
                int amt=Integer.parseInt(expence.getAmount());
                int value=dao.getCurrencyValue("USD");
                int total=amt*value;
                expence.setAmount(String.valueOf(total));
                
		}
		else if(expence.getCurrency().equalsIgnoreCase("EUR") && current_country.equalsIgnoreCase("India")) {
			
            int amt=Integer.parseInt(expence.getAmount());
            int value=dao.getCurrencyValue("EUR");
            int total=amt*value;
            expence.setAmount(String.valueOf(total));
    	}
		else if(expence.getCurrency().equalsIgnoreCase("PND") && current_country.equalsIgnoreCase("India")) {
			
            int amt=Integer.parseInt(expence.getAmount());
            int value=dao.getCurrencyValue("PND");
            int total=amt*value;
            expence.setAmount(String.valueOf(total));
            
	    }
		else if(expence.getCurrency().equalsIgnoreCase("INR") && current_country.equalsIgnoreCase("USA")) {
			
            int amt=Integer.parseInt(expence.getAmount());
            int value=dao.getCurrencyValue("USD");
            int total=amt/value;
          //  total=total*amt;
            expence.setAmount(String.valueOf(total));
	    }
        else if(expence.getCurrency().equalsIgnoreCase("INR") && current_country.equalsIgnoreCase("LONDON")) {
			
            int amt=Integer.parseInt(expence.getAmount());
            int value=dao.getCurrencyValue("PND");
            int total=amt/value;
           // total=total*amt;
            expence.setAmount(String.valueOf(total));
	    }
		else {
			
		}
		
	}

	public String getVoucher() throws Exception {

		Connection connection = null;

		try {

			connection = Connection_provider.getconnection();
			String selectedid = request.getParameter("selectedid");

			ExpenManagementDAO dao = new JDBCExpenceManagementDAO(connection);
			Expence expence = dao.getPaymentVoucher(selectedid);

			String data = expence.getId() + "*" + expence.getCaldate() + "*"
					+ expence.getAmount() + "*" + expence.getMerchant() + "*"
					+ expence.getCategory() + "*" + expence.getPaidby() + "*"
					+ expence.getComments() + "*" + expence.getLastmodified()+"*"+expence.getCurrency();
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

	public String update() throws Exception {

		Connection connection = null;

		try {

			connection = Connection_provider.getconnection();
			ExpenManagementDAO expenManagementDAO = new JDBCExpenceManagementDAO(
					connection);
			String uid=loginInfo.getUserId();
			Expence expence = new Expence();
			expence.setId(expengeManagementForm.getId());
			expence.setCaldate(expengeManagementForm.getCaldate());
			expence.setAmount(expengeManagementForm.getAmount());
			expence.setMerchant(expengeManagementForm.getMerchant());
			expence.setCategory(expengeManagementForm.getCategory());
			expence.setPaidby(expengeManagementForm.getPaidby());
			expence.setComments(expengeManagementForm.getComments());
			expence.setCurrency(expengeManagementForm.getCurrency());
			expence.setUserid(uid);

			int result = expenManagementDAO.updatePaymentVoucher(expence);

		} catch (Exception e) {

			e.printStackTrace();
		}
		finally {
			connection.close();
		}
		return "save";
	}

	public String delete() throws Exception{

		Connection connection = null;
		try {
			connection = Connection_provider.getconnection();
			ExpenManagementDAO expenManagementDAO = new JDBCExpenceManagementDAO(
					connection);

			String id = request.getParameter("id");
			String uid=loginInfo.getUserId();

			int res = expenManagementDAO.deleteExpenses(id,uid);

		} catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			connection.close();
		}

		return "save";
	}

	public String printvoucher() throws Exception {

		Connection connection = null;
		try {


			connection = Connection_provider.getconnection();
			ExpenManagementDAO expenManagementDAO = new JDBCExpenceManagementDAO(
					connection);
			DateFormat dateFormat1 = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		    Date date=new Date();
		    String datestr=dateFormat1.format(date).toString();
			String id = request.getParameter("id");
			String ids=loginInfo.getUserId();
			String uname=loginInfo.getUserName();
			
			Clinic clinic = new Clinic();
			ClinicDAO clinicDAO  = new JDBCClinicDAO(connection);
			AccountsDAO accountsDAO = new JDBCAccountsDAO(connection);
			
			ArrayList<Clinic> locationAdressList = accountsDAO.getLocationAddress(loginInfo.getClinicid());
			
			clinic = clinicDAO.getCliniclistDetails(loginInfo.getClinicid());
			expengeManagementForm.setClinicName(clinic.getClinicName());
			expengeManagementForm.setClinicOwner(clinic.getClinicOwner());
			expengeManagementForm.setOwner_qualification(clinic.getOwner_qualification());
			expengeManagementForm.setLocationAdressList(locationAdressList);
			expengeManagementForm.setAddress(clinic.getAddress());
			expengeManagementForm.setLandLine(clinic.getLandLine());
			expengeManagementForm.setClinicemail(clinic.getEmail());
			expengeManagementForm.setWebsiteUrl(clinic.getWebsiteUrl());
			expengeManagementForm.setClinicLogo(clinic.getUserImageFileName());
			
			int parentid = expenManagementDAO.getExpenceParentID(id);
			
			Expence expence = expenManagementDAO.getExpenceParentDetail(parentid);
expengeManagementForm.setParentid(""+parentid+"");			
			
			expengeManagementForm.setEpayment(expence.getPtype());
			
			int oficicledgerid = expenManagementDAO.getOfficiallederid(id);
			String pto = expenManagementDAO.getPtoName(oficicledgerid);
			
			//if its receipt voucher
			Expence p = expenManagementDAO.geteoficialledgerdetails(Integer.parseInt(id),0);
			if(p.getXpayment()!=null){
				if(p.getXpayment().equals("Receipt")){
					pto = p.getCategory() + " ("+p.getPaidby()+")";
				}
			}
		
			
			expengeManagementForm.setPaymantto(pto);
			
			if(expence.getPtype().equals("Payment") || expence.getPtype().equals("Receipt")){
				expengeManagementForm.setShowcd("0");
			}else{
				expengeManagementForm.setShowcd("1");
			}

			ArrayList<Expence> el = expenManagementDAO.getReportExpenseListByid(id,parentid,expengeManagementForm.getEpayment());
			expengeManagementForm.setExpenceList(el);
		
			if(el.size()>=0){
			Expence expenceList = el.get(0);
			expengeManagementForm.setComments(expenceList.getComments());
			expengeManagementForm.setCategory(expenceList.getCategory());
			expengeManagementForm.setCaldate(expenceList.getCaldate());
			expengeManagementForm.setMerchant(expenceList.getMerchant());
			expengeManagementForm.setPaidby(expenceList.getPaidby());
			expengeManagementForm.setCurrency(expenceList.getCurrency());
			expengeManagementForm.setAmount(expenceList.getAmount());
			expengeManagementForm.setCreateby(expenceList.getCreateby());
			expengeManagementForm.setCreatedate(expenceList.getCreatedate());
			expengeManagementForm.setPrintby(ids);
			expengeManagementForm.setPrintdate(datestr);
			expengeManagementForm.setStatus(expenceList.getStatus());
			
			//cal amount
			double total = 0;
			double ctotal = 0;
			for(Expence e : el){
				total = total + Double.parseDouble(e.getAmount());
				ctotal = ctotal + Double.parseDouble(e.getCredit());
			}
			expengeManagementForm.setAmount(""+total+"");
			expengeManagementForm.setCtotal(""+ctotal+"");
			
			NumberToWord obj = new NumberToWord();
			expengeManagementForm.setTotalinword(obj.convert((int) total));
			}
			
			UserProfileDAO userProfileDAO = new JDBCUserProfileDAO(connection);
			int duserid  = userProfileDAO.getDiaryUserId(expence.getUserid());
			UserProfile userProfile = userProfileDAO.getUserprofileDetails(duserid);
			String fullname = userProfile.getInitial() + " " + userProfile.getFirstname() + " " + userProfile.getSurname();
			expengeManagementForm.setCreateby(fullname);
			
			
			
			

		} catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			connection.close();
		}
		return "print";
	}

    public String getsortedexpences() throws Exception {
    	
    	Connection connection=null;
    	
    	try {
			
    		connection=Connection_provider.getconnection();
    		String fromdate="";
    		String todate="";
    		Expence expence=new Expence();
    		String expenseType =expengeManagementForm.getExpenseType();  
    		String range=expengeManagementForm.getRange();
    		String paymentmode=expengeManagementForm.getPaymentmode();
    		if(expengeManagementForm.getFromdate()==null || expengeManagementForm.getTodate()==null)
    		{
    			fromdate="";
        	    todate="";
    		}
    		else
    		{
    		if(expengeManagementForm.getFromdate().equals("") || expengeManagementForm.getTodate().equals(""))
    		{
    			fromdate="";
        	    todate="";
    		}
    		else
    		{
    			 fromdate=DateTimeUtils.getCommencingDate(expengeManagementForm.getFromdate());
        		 todate=DateTimeUtils.getCommencingDate(expengeManagementForm.getTodate());
    		}
    		}
    		
    		expence.setFromdate(fromdate);
    		expence.setTodate(todate);
    		
    		expence.setRangevalue(range);
    		expence.setExpensetypevalue(expenseType);
    		expence.setPaymentmodevalue(paymentmode);
    		
    		
            ExpenManagementDAO dao=new JDBCExpenceManagementDAO(connection);   
            
            ArrayList<Expence> expenceList=dao.getSortedExpenceList(expence);
            
            expengeManagementForm.setExpenceList(expenceList);
            
            double total = dao.getExpenceTotal(fromdate,todate);
			String expenceTotal = DateTimeUtils.changeFormat(total);
			expengeManagementForm.setExpenceTotal(expenceTotal);
			
			String ass=expengeManagementForm.getPaymentmodevalue();
			expengeManagementForm.setExpenseType(expenseType);
			expengeManagementForm.setPaymentmode(paymentmode);
			expengeManagementForm.setRange(range);
			expengeManagementForm.setFromdate(DateTimeUtils.getCommencingDate(fromdate));
			expengeManagementForm.setTodate(DateTimeUtils.getCommencingDate(todate));

		} catch (Exception e) {

		   e.printStackTrace();
		}
    	finally {
    		connection.close();
    	}
    	
    	return SUCCESS;
    }
	
	
    public String printPdf() throws Exception {
    	
    	
    	Connection connection=null;
    	
    	try {
			
    		connection=Connection_provider.getconnection();
    		
    		
    		Clinic clinic = new Clinic();
			ClinicDAO clinicDAO = new JDBCClinicDAO(connection);
			ExpenManagementDAO expenManagementDAO = new JDBCExpenceManagementDAO(
					connection);

			clinic = clinicDAO.getCliniclistDetails(loginInfo.getClinicid());
			
			
			String id = request.getParameter("id");

			Expence expence = expenManagementDAO.getReportIdList(id);
			
			String reportdate = expence.getCaldate();
			String temp[] = reportdate.split(" ");
			
			ArrayList<Expence> reportExpenList = expenManagementDAO
					.getReportExpenseList(expence.getReportIdList());
			
			
   
    		StringBuffer buffer=new StringBuffer();
    		buffer.append(" <table align='center' border='1' cellpadding='0' cellspacing='0' width='800' style='border-collapse: collapse;'>");
    		buffer.append("<tr style='font-family: Arial; font-size: 13px; border-bottom: 1px solid #ffffff;'>");
    		buffer.append("<td bgcolor='#ffffff' style='padding: 0px 30px 40px 30px;'>");
            buffer.append("<table cellpadding='0' cellspacing='0' width='100%'>");    		
    		buffer.append(" <tr>");
            buffer.append("<td style='padding: 0px 0 10px 0;'>");            
            buffer.append("<center><h2>Expense Report</h2><h3>("+expence.getReportName()+")</h3></center> </td></tr>");               
            buffer.append("<tr>");
            buffer.append("<td style='padding: 0px 0 10px 0;'><p>"+clinic.getClinicName()+""
            		+ "</p><p>"+loginInfo.getCountry()+""
            		+ "</p><p>From : "+clinic.getClinicOwner()+""
            		+ "</p><p>Report Date : "+temp[0]+""
            		+ "</p>");
            buffer.append("</td></tr>");
            buffer.append("</table><br />");
            buffer.append("<div>");
            buffer.append("(Summary by Category)"
                        +"<table style='width: 100%;'>"
                        +"<tr style='background-color: #424A5D; color: #ffffff;'>"
                         +"<th style='text-align:left;'>Date</th>"
                          +"<th style='text-align:left;'>Expence Type</th>"
                           +"<th style='text-align:left;'>Merchant</th>"
                            +"<th style='text-align:left;'>Comment</th>"
                            +"<th style='text-align:left;'>Paid With</th>"
                            +"<th style='text-align:left;'>Currency</th>"
                            +"<th style='text-align:left;'>Amount</th>"
                     +"</tr>");
            
            double total = expenManagementDAO.getReportExpenceTotal(expence
					.getReportIdList());
			String expenceTotal = DateTimeUtils.changeFormat(total);
            
            for(Expence expence2:reportExpenList) {
            	
            	buffer.append("<tr>");
            	buffer.append("<td>"+expence2.getCaldate()+"</td>");
            	buffer.append("<td>"+expence2.getCategory()+"</td>");
            	buffer.append("<td>"+expence2.getMerchant()+"</td>");
            	buffer.append("<td>"+expence2.getComments()+"</td>");
            	buffer.append("<td>"+expence2.getPaidby()+"</td>");
            	buffer.append("<td>"+Constants.getCurrency(loginInfo)+""+expence2.getAmount()+"</td>");
            	buffer.append("</tr>");
            }
            
            buffer.append(" <tr>"
            + "<td></td><td></td><td></td><td></td><td><b>TOTAL :</b> </td>");              
            buffer.append("<td>");
            buffer.append("<b>"+Constants.getCurrency(loginInfo)+""+expenceTotal+"</b> </td>");
            buffer.append("</tr></table>");
            buffer.append("</div>");
            buffer.append("<div><p>Signature:</p></div></td>");
            buffer.append("</tr>");
           
       

//            String filePath = request.getRealPath("/liveData/document/");
            String filename = expence.getReportName()+""+id+""+".pdf";

            String workingDirectory = System.getProperty("user.dir");
			String absoluteFilePath = workingDirectory + File.separator + loginInfo.getClinicUserid() + File.separator;
            htmlToPdfFile(buffer.toString(), absoluteFilePath, filename);
            session.setAttribute("filename",filename);
            
            System.out.println("pdf done");
			
            downlaodDoc();
            
		} catch (Exception e) {

		   e.printStackTrace();
		}
    	finally {
    		
    		connection.close();
    	}
    	
    	return null;
    }
    
    
    public String printexcel() throws Exception{
    	
    	
    	Connection connection=null;
    	
    	try {
			
    		connection=Connection_provider.getconnection();
    		
    		
    		Clinic clinic = new Clinic();
			ClinicDAO clinicDAO = new JDBCClinicDAO(connection);
			ExpenManagementDAO expenManagementDAO = new JDBCExpenceManagementDAO(
					connection);

			clinic = clinicDAO.getCliniclistDetails(loginInfo.getClinicid());
			
			
			String id = request.getParameter("id");

			Expence expence = expenManagementDAO.getReportIdList(id);
			
			String reportdate = expence.getCaldate();
			String temp[] = reportdate.split(" ");
			
			ArrayList<Expence> reportExpenList = expenManagementDAO
					.getReportExpenseList(expence.getReportIdList());
			
			HSSFWorkbook workbook = new HSSFWorkbook();
		
			HSSFSheet worksheet = workbook.createSheet("Expence Sheet");
			
			worksheet.setColumnWidth(1, 30*256);
			worksheet.setColumnWidth(2, 30*256);
			worksheet.setColumnWidth(3, 30*256);
			worksheet.setColumnWidth(4, 10*256);
			worksheet.setColumnWidth(5, 10*256);
			worksheet.setColumnWidth(6, 20*256);
			worksheet.setColumnWidth(7, 30*256);
			worksheet.setColumnWidth(8, 20*256);
			worksheet.setColumnWidth(9, 10*256);
			worksheet.setColumnWidth(10, 30*256);
			
			
			HSSFFont font= workbook.createFont();
			HSSFCellStyle cellstyle = workbook.createCellStyle();
			font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
			font.setFontHeightInPoints((short)10);
			cellstyle.setFont(font);
			cellstyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
			
			HSSFRow row=worksheet.createRow((short)0);
			HSSFCell cell=  row.createCell((short)3);
			font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
			font.setFontHeightInPoints((short)13);
			cellstyle.setFont(font);
			
			cell.setCellValue("Expence Report");
            cell.setCellStyle(cellstyle);
            
            font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
			font.setFontHeightInPoints((short)10);
			cellstyle.setFont(font);
            
			HSSFRow row2=worksheet.createRow((short)1);
			HSSFCell cell2= row2.createCell((short)3);
			cell2.setCellValue("("+expence.getReportName()+")");
			cell2.setCellStyle(cellstyle);
			
			
			HSSFRow row3=worksheet.createRow((short)2);
			HSSFCell cell3=row3.createCell((short)1);
			cell3.setCellValue(clinic.getClinicName());
			cell3.setCellStyle(cellstyle);
			
			HSSFRow row4=worksheet.createRow((short)3);
			HSSFCell cell4=row4.createCell((short)1);
			cell4.setCellValue(loginInfo.getCountry());
			cell4.setCellStyle(cellstyle);
			
			
			HSSFRow row5=worksheet.createRow((short)4);
			HSSFCell cell5=row5.createCell((short)1);
			cell5.setCellValue("From: ");
			cell5.setCellStyle(cellstyle);
			
			HSSFCell cell6=row5.createCell((short)2);
			cell6.setCellValue(clinic.getClinicOwner());;
			cell6.setCellStyle(cellstyle);
			
			
			HSSFRow row6=worksheet.createRow((short)5);
			HSSFCell cell7=row6.createCell((short)1);
			cell7.setCellValue("Report Date: ");
			cell7.setCellStyle(cellstyle);
			
			HSSFCell cell8=row6.createCell((short)2);
			cell8.setCellValue(temp[0]);;
			cell8.setCellStyle(cellstyle);
			
			HSSFRow row7=worksheet.createRow((short)6);
			
			HSSFRow row8=worksheet.createRow((short)7);
			HSSFCell cell9=row8.createCell((short)1);
			cell9.setCellValue("(Summary by Category)");
			cell9.setCellStyle(cellstyle);
	
			HSSFRow row9=worksheet.createRow((short)8);
			HSSFCell cell10=row9.createCell((short)1);
			cell10.setCellValue("Date");
			cell10.setCellStyle(cellstyle);
			
			
			HSSFCell cell11=row9.createCell((short)2);
			cell11.setCellValue("Payment");
			cell11.setCellStyle(cellstyle);
			
			HSSFCell cell12=row9.createCell((short)3);
			cell12.setCellValue("Marchant");
			cell12.setCellStyle(cellstyle);
			
			HSSFCell cell13=row9.createCell((short)4);
			cell13.setCellValue("Expence Category");
			cell13.setCellStyle(cellstyle);
			
			HSSFCell cell14=row9.createCell((short)5);
			cell14.setCellValue("Currency");
			cell14.setCellStyle(cellstyle);
			
			
			HSSFCell cell15=row9.createCell((short)6);
			cell15.setCellValue("Amount");
			cell15.setCellStyle(cellstyle);
			
			int index=9;
			
			for(Expence expence2:reportExpenList) {
				
				HSSFRow row10=worksheet.createRow((short)index);
				row10.createCell((short)1).setCellValue(expence2.getCaldate());
				row10.createCell((short)2).setCellValue(expence2.getPaidby());
				row10.createCell((short)3).setCellValue(expence2.getMerchant());
				row10.createCell((short)4).setCellValue(expence2.getCategory());
				row10.createCell((short)5).setCellValue(expence2.getCurrency());
				row10.createCell((short)6).setCellValue(Constants.getCurrency(loginInfo)+" "+expence2.getAmount());
				index++;
				
			}
			
			HSSFRow row11=worksheet.createRow((short)index++);
			HSSFCell cell16=row11.createCell((short)6);
			cell16.setCellValue("Total= ");
			cell16.setCellStyle(cellstyle);
			
			HSSFCell cell17=row11.createCell((short)7);
			cell17.setCellValue(expenManagementDAO.getReportExpenceTotal(expence.getReportIdList()));
    		cell17.setCellStyle(cellstyle);
			
			HSSFRow row12=worksheet.createRow((short)index++);
			HSSFCell cell18=row12.createCell((short)1);
			cell18.setCellValue("Signature: ");
			cell18.setCellStyle(cellstyle);
			
			String fileName=expence.getReportName()+""+id+".xls";
			session.setAttribute("filename", fileName);
			
			String workingDirectory = System.getProperty("user.dir");
			String absoluteFilePath = workingDirectory + File.separator + loginInfo.getClinicUserid() + File.separator +  fileName;
			
			FileOutputStream fileOutputStream=new FileOutputStream(absoluteFilePath);
			workbook.write(fileOutputStream); 
			System.out.println(" xls file created to "+absoluteFilePath);
			
			fileOutputStream.close();
			
			downlaodDoc();
		} catch (Exception e) {

		   e.printStackTrace();
		}
    	finally {
    		connection.close();
    	}
    	
    	return null;
    }
    
    
    
    public void downlaodDoc() throws Exception {
    	
    	String fileName = (String)session.getAttribute("filename");
    	FileInputStream in = null;
		ServletOutputStream out = null;
		HttpServletRequest servletRequest=(HttpServletRequest) ActionContext.getContext().get(ServletActionContext.HTTP_REQUEST);
		HttpServletResponse response = (HttpServletResponse)ActionContext.getContext().get(ServletActionContext.HTTP_RESPONSE);
		
		
		 String workingDirectory = System.getProperty("user.dir");
		 String absoluteFilePath = workingDirectory + File.separator + loginInfo.getClinicUserid() +   File.separator +  fileName;
		//String filepath = request.getRealPath("/liveData/document/"+fileName);
		File file=new File(absoluteFilePath);
		 if(file.exists()){
		//return an application file instead of html page
		//response.setContentType("application/octet-stream");
       response.setHeader("Content-Disposition","attachment;filename="+fileName+"");
	
       	 try {
				in = new FileInputStream(file);
				 out = response.getOutputStream();
	        	 
		        	byte[] outputByte = new byte[4096];
		        	//copy binary contect to output stream
		        	while(in.read(outputByte, 0, 4096) != -1)
		        	{
		        		out.write(outputByte, 0, 4096);
		        	}
		        	
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		 }
       	
       	in.close();
       	out.flush();
       	out.close();
		
    }
    
	
	public ExpengeManagementForm getModel() {
		// TODO Auto-generated method stub
		return expengeManagementForm;
	}

	public void prepare() throws Exception {

		Connection connection = null;

		try {
			connection = Connection_provider.getconnection();
			ExpenManagementDAO expenManagementDAO = new JDBCExpenceManagementDAO(
					connection);
			AppointmentTypeDAO appointmentTypeDAO = new JDBCAppointmentTypeDAO(connection);
			ArrayList<Expence> categories = expenManagementDAO
					.getAllCategories("");
			expengeManagementForm.setCategories(categories);
			
			ArrayList<Expence> currencies=expenManagementDAO.getAllCurrencies();
			expengeManagementForm.setCurrencies(currencies);
			
			//ArrayList<Master>ledgerList = appointmentTypeDAO.getExpenceLedgerList(loginInfo);
			ArrayList<Master>ledgerList = appointmentTypeDAO.getExpenceLedgerAmountList(loginInfo);
			expengeManagementForm.setLedgerList(ledgerList);
			
			
			
			ArrayList<Master>debitorList = expenManagementDAO.getDebitorList();
			expengeManagementForm.setDebitorList(debitorList);

		} catch (Exception e) {
			e.printStackTrace();
		} finally {

			connection.close();
		}
	}
	
	public void htmlToPdfFile(String htmlString, String filepath,
			String fileaName) throws Exception {
		
		

		CYaHPConverter converter = new CYaHPConverter();
		File fout = new File(filepath + "/" + fileaName);
		FileOutputStream out = new FileOutputStream(fout);
		Map properties = new HashMap();
		ArrayList headerFooterList = new ArrayList();

		properties.put(IHtmlToPdfTransformer.PDF_RENDERER_CLASS,
				IHtmlToPdfTransformer.FLYINGSAUCER_PDF_RENDERER);
		// properties.put(IHtmlToPdfTransformer.FOP_TTF_FONT_PATH, fontPath);
		converter.convertToPdf(htmlString, IHtmlToPdfTransformer.A4P,
				(java.util.List) headerFooterList, "file:///temp/", // root for
																	// relative
																	// external
																	// CSS and
																	// IMAGE
		out, properties);
		out.flush();
		out.close();
	}
	
	// Adarsh 13march for cancel Expence
	public String deletedata() throws Exception {
		 Connection connection = null;
		 
		 try {
		  connection = Connection_provider.getconnection();
		  ExpenManagementDAO expenManagementDAO = new JDBCExpenceManagementDAO(
					connection);
		  String id= request.getParameter("id");
		  String delete_reason= request.getParameter("delete_reason");
		  
		  int del=expenManagementDAO.cancelExpense(id,delete_reason);
		
		  //delete from ledger_sheet
		  del = expenManagementDAO.deleteLedgerEntery(id);
		  
		  String ledgerid = expenManagementDAO.getParentExpLedgerId(id);
		  
		  
		  double lamount = expenManagementDAO.getEditLedgerAmount(ledgerid);
			int ula = expenManagementDAO.updateLedgerAmount(ledgerid, lamount);
		  
		  response.setContentType("text/html");
		  response.setHeader("Cache-Control", "no-cache");
		  response.getWriter().write("o"); 
		 } catch (Exception e) {
		  e.printStackTrace();
		 }
		 finally{
		  connection.close();
		 }
		 
		 return null;
		}

	
	public String getVoucherNew() throws Exception {

		Connection connection = null;

		try {

			connection = Connection_provider.getconnection();
			String selectedid = request.getParameter("selectedid");
			
			session.setAttribute("sessnslectedid", selectedid);

			ExpenManagementDAO dao = new JDBCExpenceManagementDAO(connection);
			Expence expence = dao.getVoucherDetails(selectedid);

			String data = expence.getId() + "*" + expence.getCaldate() + "*"
					+ expence.getAmount() + "*" + expence.getMerchant() + "*"
					+ expence.getCategory() + "*" + expence.getPaidby() + "*"
					+ expence.getComments() + "*" + 
					expence.getLastmodified()+"*"+expence.getCurrency()+"*"+
					expence.getPto()+"*"+expence.getLedgerid()+"*"+
					expence.getCredit()+"*"+expence.getTransid()+"*"+
					expence.getXpayment();
			
			
			
			
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
	
	public String showvendorbalance() throws Exception {
		Connection connection = null;
		try {
			if (!verifyLogin(request)) {
				return "login";
			}
			connection = Connection_provider.getconnection();
			PoPaymenytDAO poPaymenytDAO = new JDBCPoPaymengtDAO(connection);
			StringBuilder buffer = new StringBuilder();
			BufferedReader reader = request.getReader();
			String line;
			while ((line = reader.readLine()) != null) {
				buffer.append(line);
			}
			String data = buffer.toString();
			JSONObject jsonObject = new JSONObject(data);
			String name = jsonObject.getString("name");
			
			int vendorid = poPaymenytDAO.getVendorIdFromLegder(name);
			double totalAmt = poPaymenytDAO.getTotalAountoFVendor(""+vendorid);
			double sumofreturn =poPaymenytDAO.getTotalReturedAmount(""+vendorid);
			double sumofpayamount = poPaymenytDAO.getTotalPaid(""+vendorid);
			double balance = totalAmt - sumofpayamount;
			
			JSONObject jsonobj = new JSONObject();
			jsonobj.put("totalAmt", totalAmt);
			jsonobj.put("sumofpayamount", sumofpayamount);
			jsonobj.put("sumofreturn", sumofreturn);
			jsonobj.put("balance", balance);
			
			String response1 = jsonobj.toString();
			response.setContentType("application/json");
			response.setHeader("Cache-Control", "no-cache");
			response.getWriter().write(response1);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public String refreshlegdersetcode() throws Exception{
		if (!verifyLogin(request)) {
			return "login";
		}

		Connection connection = null;
		String action = request.getParameter("action");
		if(action==null){
			action = "0";
		}
		try {
			connection = Connection_provider.getconnection();
			ExpenManagementDAO expenManagementDAO = new JDBCExpenceManagementDAO(connection);
			int t = expenManagementDAO.truncateLedgerAmount();
			int res = expenManagementDAO.refreshLedgerAmount();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			connection.close();
		}
		return "save";
	}

	
}



