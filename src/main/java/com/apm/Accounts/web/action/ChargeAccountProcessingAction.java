package com.apm.Accounts.web.action;


import java.io.File;
import java.io.FileOutputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.allcolor.yahp.converter.CYaHPConverter;
import org.allcolor.yahp.converter.IHtmlToPdfTransformer;
import org.apache.struts2.ServletActionContext;

import com.apm.Accounts.eu.bi.AccountsDAO;
import com.apm.Accounts.eu.bi.AdditionalDAO;
import com.apm.Accounts.eu.bi.CashDeskDAO;
import com.apm.Accounts.eu.bi.ChargesAccountProcessingDAO;
import com.apm.Accounts.eu.bi.StatementDAO;
import com.apm.Accounts.eu.blogic.jdbc.JDBCAccountsDAO;
import com.apm.Accounts.eu.blogic.jdbc.JDBCAdditionalDAO;
import com.apm.Accounts.eu.blogic.jdbc.JDBCCashDeskDAO;
import com.apm.Accounts.eu.blogic.jdbc.JDBCChargeAccountProcesDAO;
import com.apm.Accounts.eu.blogic.jdbc.JDBCStatementDAO;
import com.apm.Accounts.eu.entity.Accounts;
import com.apm.Accounts.web.form.AccountsForm;
import com.apm.Appointment.eu.bi.AppointmentDAO;
import com.apm.Appointment.eu.bi.AppointmentTypeDAO;
import com.apm.Appointment.eu.blogic.jdbc.JDBCAppointmentDAO;
import com.apm.Appointment.eu.blogic.jdbc.JDBCAppointmentTypeDAO;
import com.apm.Appointment.eu.entity.AppointmentType;
import com.apm.DiaryManagement.eu.bi.ClientDAO;
import com.apm.DiaryManagement.eu.bi.CompleteAptmDAO;
import com.apm.DiaryManagement.eu.bi.NotAvailableSlotDAO;
import com.apm.DiaryManagement.eu.blogic.jdbc.JDBCClientDAO;
import com.apm.DiaryManagement.eu.blogic.jdbc.JDBCCompleteAptmDAO;
import com.apm.DiaryManagement.eu.blogic.jdbc.JDBCNotAvailableSlotDAO;
import com.apm.DiaryManagement.eu.entity.Client;
import com.apm.DiaryManagement.eu.entity.CompleteAppointment;
import com.apm.DiaryManagement.eu.entity.DiaryManagement;
import com.apm.DiaryManagement.web.action.SmsService;
import com.apm.Expence.eu.bi.ExpenManagementDAO;
import com.apm.Expence.eu.bi.blogic.jdbc.JDBCExpenceManagementDAO;
import com.apm.Ipd.eu.bi.BedDao;
import com.apm.Ipd.eu.bi.IpdDAO;
import com.apm.Ipd.eu.blogic.jdbc.JDBCBedDao;
import com.apm.Ipd.eu.blogic.jdbc.JDBCIpdDAO;
import com.apm.Ipd.eu.entity.Bed;
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
import com.apm.common.utils.CommonOpdIpdReport;
import com.apm.common.utils.DateTimeUtils;
import com.apm.common.utils.Pagination;
import com.apm.common.web.action.BaseAction;
import com.apm.common.web.common.helper.LoginHelper;
import com.apm.common.web.common.helper.LoginInfo;
import com.apm.main.common.constants.Constants;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.Preparable;

public class ChargeAccountProcessingAction extends BaseAction implements Preparable , ModelDriven<AccountsForm>  {
	HttpServletRequest request = (HttpServletRequest) ActionContext.getContext().get(ServletActionContext.HTTP_REQUEST);
	HttpServletResponse response = (HttpServletResponse)ActionContext.getContext().get(ServletActionContext.HTTP_RESPONSE);
	HttpSession session = request.getSession(true);
	LoginInfo loginInfo = LoginHelper.getLoginInfo(request);
	DateTimeUtils dateTimeUtils = new DateTimeUtils();
	AccountsForm accountsForm = new AccountsForm();
	private Pagination pagination = new Pagination(10, 1);
	public Pagination getPagination() {
		return pagination;
	}
	

	public void setPagination(Pagination pagination) {
		this.pagination = pagination;
	}
	
	
	public String execute() throws Exception{
		if(!verifyLogin(request)){
			return "login";
		}
	
		
		Connection connection = null;
		try{
			
			connection = Connection_provider.getconnection();
			ChargesAccountProcessingDAO chargesAccountProcessingDAO = new JDBCChargeAccountProcesDAO(connection);
			ClientDAO clientDAO = new JDBCClientDAO(connection);
			AccountsDAO accountsDAO = new JDBCAccountsDAO(connection);
			String clientId = accountsForm.getClientId();
			String invoiceid = accountsForm.getSelectedInvoiceid();
			/*if(clientId.equals("")){
				clientId = (String)session.getAttribute("clientId");
			}*/
			
			
			String client = accountsForm.getClient();
			String payby = accountsForm.getPayby();
			
			/*if(payby.equals("")){
				payby = (String)session.getAttribute("payby");
			}*/
			
			String fromDate = accountsForm.getFromDate();
			String toDate = accountsForm.getToDate();
			
			//set selected clientid from session
			if(session.getAttribute("sessionselectedclientid")!=null && clientId.equals("")){
				clientId = (String)session.getAttribute("sessionselectedclientid");
				accountsForm.setClientId(clientId);
				
				//accountsForm.setClient(clients.getClientName());
				
			} 
			if(invoiceid==null || invoiceid.equals(""))
			{
				invoiceid=(String)session.getAttribute("sessionselectedinvoice");
				accountsForm.setSelectedInvoiceid(invoiceid);
				/*accountsForm.setActionType("viewacc");*/
			}else{
				session.removeAttribute("sessionselectedinvoice");
			}
			if(session.getAttribute("sessionactiontype")!=null){
				if(session.getAttribute("sessionactiontype").equals("1")){
					accountsForm.setActionType("viewacc");
					session.removeAttribute("sessionactiontype");
				}
				session.removeAttribute("sessionactiontype");
			}
			session.setAttribute("sessionselectedclientid", clientId);
			Client clients = clientDAO.getSelectedSessionClientDetails(clientId);
			client = clients.getClientName();
			
			accountsForm.setClient(client);
			session.setAttribute("clientId", clientId);
			session.setAttribute("client", client);
			session.setAttribute("payby", payby);
			session.setAttribute("fromDate", fromDate);
			session.setAttribute("toDate", toDate);
			session.setAttribute("transactionType", accountsForm.getTransactionType());

			
//			if(!fromDate.equals("")){
//				String temp[]= fromDate.split("/");
//				fromDate = temp[2]+"-"+temp[1]+"-"+temp[0];
//			}
//			if(!toDate.equals("")){
//				String temp1[]= toDate.split("/");
//				toDate = temp1[2]+"-"+temp1[1]+"-"+temp1[0];
//			}
			if(fromDate == null){
				DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
				Calendar cal = Calendar.getInstance();
				fromDate = dateFormat.format(cal.getTime());   
			}else{
				if(fromDate.equals("")) {
					DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
				    Calendar cal = Calendar.getInstance();
				    fromDate = dateFormat.format(cal.getTime());   
				}  
			}
			if(toDate== null){
				DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
				Calendar cal = Calendar.getInstance(); 
				toDate = dateFormat.format(cal.getTime());
			}else{
				if(toDate.equals("")){
				    DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
				    Calendar cal = Calendar.getInstance(); 
				    toDate = dateFormat.format(cal.getTime());
				}
			}
			fromDate = DateTimeUtils.getCommencingDate1(fromDate);
			toDate = DateTimeUtils.getCommencingDate1(toDate);
			
			
			String defaultVal = "All";
			if(payby.equals(defaultVal)){
				payby = "";
			}
			
			
			ArrayList<Accounts>chargeProcessingList = new ArrayList<Accounts>();
			int totalCount = chargesAccountProcessingDAO.getTotalChargesAccountProcessingCount(clientId,payby,fromDate,toDate);
			pagination.setPreperties(totalCount);
			
			if(accountsForm.getTransactionType().equals(Constants.PENDING_PEYMENT)){
				chargeProcessingList = chargesAccountProcessingDAO.getChargesAccountProcessingPendingList(clientId,payby,fromDate,toDate,pagination);
			}else if(accountsForm.getTransactionType().equals(Constants.PAID)){
				chargeProcessingList = chargesAccountProcessingDAO.getChargesAccountProcessingPaidList(clientId,payby,fromDate,toDate,pagination);
			}else{
				if(!accountsForm.getActionType().equals("")){
					pagination.setPreperties(1);
				}
				chargeProcessingList = chargesAccountProcessingDAO.getChargesAccountProcessingList(clientId,payby,fromDate,toDate,pagination,accountsForm.getActionType(),accountsForm.getSelectedInvoiceid());
			}
			
			boolean  discreq=false;
			discreq=chargesAccountProcessingDAO.checkInvoicerequset(accountsForm.getSelectedInvoiceid());
			accountsForm.setDiscstatus1(discreq);
			pagination.setPage_records(chargeProcessingList.size());
			accountsForm.setTotalRecords(chargeProcessingList.size());
			accountsForm.setPagerecords(Integer.toString(pagination.getPage_records()));
			accountsForm.setChargeProcessingList(chargeProcessingList);
			boolean refundstatus=chargesAccountProcessingDAO.checkRefundAgainstInvoice(accountsForm.getSelectedInvoiceid());
			accountsForm.setRefundstatus(refundstatus);
			session.setAttribute("pagination", pagination);
			
			//createPdf(accountsForm.getTransactionType(), clientId, payby, fromDate, toDate);
			
			CompleteAppointment completeAppointment = new CompleteAppointment();
			CompleteAptmDAO completeAptmDAO = new JDBCCompleteAptmDAO(connection);
			completeAppointment = completeAptmDAO.getInsuranceCompanyName(clientId);
			accountsForm.setInsuranceCompany(completeAppointment.getInsuranceCompanyName());
			accountsForm.setThirdPartyAddress(completeAppointment.getInsuranceCompanyAddress());
			accountsForm.setThirdPartyContacttno(completeAppointment.getThirdPartyContacttno());
			accountsForm.setThirdPartyPostcode(completeAppointment.getThirdPartyPostcode());
			accountsForm.setThirdPartyemail(completeAppointment.getThirdPartyemail());
			fromDate = DateTimeUtils.getCommencingDate1(fromDate);  
			toDate = DateTimeUtils.getCommencingDate1(toDate);
			accountsForm.setFromDate(fromDate);
			accountsForm.setToDate(toDate);
			
			
			
			
			ArrayList<Accounts> clientDetailsList = accountsDAO.getClientDetails(clientId);
				for(Accounts a1:clientDetailsList){
					accountsForm.setInitial(a1.getInitial());
					accountsForm.setFirstname(a1.getFirstname());
					accountsForm.setLastname(a1.getLastname());
					accountsForm.setAddress(a1.getAddress());
					accountsForm.setCity(a1.getCity());
					accountsForm.setClientId(clientId);
					accountsForm.setPostcode(a1.getPostcode());
					accountsForm.setEmail(a1.getEmail());
					accountsForm.setMobno(a1.getMobno());
					accountsForm.setAbrivationid(a1.getAbrivationid());
				}
				
				ArrayList<Accounts> discountgivenuserlist = accountsDAO.getDiscountGivenUserList();
				accountsForm.setDiscountgivenuserlist(discountgivenuserlist);
			
		}
		
		
		catch(Exception e){
			e.printStackTrace();
		}finally{
			
			connection.close();
		}
		
		
		return "showChargesProcessingPage";
	}
	
	
	
	public String rfundtrans() throws Exception{
		
		String id = request.getParameter("invoiceid");
		
		Connection connection  = null;
		try{
			
			connection  = Connection_provider.getconnection();
			AccountsDAO accountsDAO = new JDBCAccountsDAO(connection);
			ChargesAccountProcessingDAO chargesAccountProcessingDAO = new JDBCChargeAccountProcesDAO(connection);
			
			ArrayList<Accounts>refundList = chargesAccountProcessingDAO.getRefundList(id);
			
			StringBuffer str = new StringBuffer();
			for(Accounts a : refundList){
				str.append("<tr>");
				
				str.append("<td>"+a.getId()+"</td>");
				str.append("<td>"+a.getDate()+"</td>");
				str.append("<td>"+a.getPaymentmode()+"</td>");
				str.append("<td>"+a.getAmountx()+"</td>");
				
				str.append("</tr>");
			}
			
			response.setContentType("text/html");
			response.setHeader("Cache-Control", "no-cache");
			
			response.getWriter().write(""+str.toString()+""); 
			
			
		}	catch(Exception e){
			e.printStackTrace();
		}finally{
			
			connection.close();
		}
		
		return null;
	}
	
	
	
	public String hosprevnue(){
		
		Connection connection = null;
		if(!verifyLogin(request)){
			return "login";
		}
		String fromDate = accountsForm.getFromDate();
		String toDate = accountsForm.getToDate();	
		
		if(fromDate.equals("")){
			DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
			Calendar cal = Calendar.getInstance();
			cal.add(Calendar.DATE, -7); 
			fromDate = dateFormat.format(cal.getTime());
			accountsForm.setFromDate(fromDate);
		}
		if(toDate.equals("")){
			DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
			Calendar cal = Calendar.getInstance();
			//cal.add(Calendar.DATE, -7); 
			toDate = dateFormat.format(cal.getTime());
			accountsForm.setToDate(toDate);
		}
		
		
		if(!fromDate.equals("")){
			String temp[]= fromDate.split("/");
			fromDate = temp[2]+"-"+temp[1]+"-"+temp[0];
		}
		if(!toDate.equals("")){
			String temp1[]= toDate.split("/");
			toDate = temp1[2]+"-"+temp1[1]+"-"+temp1[0];
		}
		try{
			connection = Connection_provider.getconnection();
			ChargesAccountProcessingDAO chargesAccountProcessingDAO = new JDBCChargeAccountProcesDAO(connection);
			
			String status = accountsForm.getStatus();
			
			ArrayList<Accounts>chargeProcessingList = chargesAccountProcessingDAO.getHospitalRevenueList(fromDate,toDate,status);
			
			accountsForm.setChargeProcessingList(chargeProcessingList);
			if(chargeProcessingList.size()>0){
				Accounts aa = chargeProcessingList.get(chargeProcessingList.size()-1);
				accountsForm.setDebitTotalx(aa.getDebitTotalx());
				accountsForm.setCreditTotalx(aa.getCreditTotalx());
				
				String invoicestr = aa.getInvoiceidstr();
				double refundtotal = chargesAccountProcessingDAO.getRefundTotal(invoicestr);
				
				accountsForm.setRefundtotalx(DateTimeUtils.changeFormat(refundtotal));
			}
		}
		catch(Exception e){
			e.printStackTrace();
		}finally{
			
			try {
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		
		return "hosprevnue";
				
				
	}
	
	public String iclosed() throws Exception{
		
		String id = request.getParameter("id");
		
		Connection connection  = null;
		try{
			
			connection  = Connection_provider.getconnection();
			AccountsDAO accountsDAO = new JDBCAccountsDAO(connection);
			ChargesAccountProcessingDAO chargesAccountProcessingDAO = new JDBCChargeAccountProcesDAO(connection);
			
			String date = DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone());
			
			int res = chargesAccountProcessingDAO.updateIclosed(id,date);
			
			
			
		}	catch(Exception e){
			e.printStackTrace();
		}finally{
			
			connection.close();
		}
		
		return execute();
	}
	
public String ipost() throws Exception{
		
		String id = request.getParameter("id");
		
		Connection connection  = null;
		try{
			
			connection  = Connection_provider.getconnection();
			AccountsDAO accountsDAO = new JDBCAccountsDAO(connection);
			ChargesAccountProcessingDAO chargesAccountProcessingDAO = new JDBCChargeAccountProcesDAO(connection);
			
			String date = DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone());
			int res = chargesAccountProcessingDAO.updateIpost(id, date);
			
			
			
		}	catch(Exception e){
			e.printStackTrace();
		}finally{
			
			connection.close();
		}
		
		return execute();
	}
	
	public String editdisc() throws Exception{
		//var url = "editdiscProcessingAccount?invoiceId="+invoiceId+"&discval="+discval+"&disctype="+disctype+" ";
		String invoiceId = request.getParameter("invoiceId");
		String discval = request.getParameter("discval");
		String disctype = request.getParameter("disctype");
		
		String isneededrefund = request.getParameter("isneededrefund");
		String refund_reason = request.getParameter("refund_reason");
		String refundamount = request.getParameter("refundamount");
		String isdirectdiscount= request.getParameter("isdirectdiscount");
		String direct_discount_reason = request.getParameter("direct_discount_reason");
		if(!verifyLogin(request)){
			return "login";
		}
		Connection connection  = null;
		try{
			if(isdirectdiscount!=null){
				if(isdirectdiscount.equals("")){
					isdirectdiscount ="0";
				}
			}else{
				isdirectdiscount="0";
			}
			connection  = Connection_provider.getconnection();
			AccountsDAO accountsDAO = new JDBCAccountsDAO(connection);
			AdditionalDAO additionalDAO = new JDBCAdditionalDAO(connection);
			AppointmentDAO appointmentDAO = new JDBCAppointmentDAO(connection);
			Accounts accounts = accountsDAO.getDiscInvoiceDetails(invoiceId);
			String userid = loginInfo.getUserId();
			if(isdirectdiscount.equals("1")){
				String datetime = DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone());
				int result = appointmentDAO.updateRequestInvoiceDiscout(invoiceId);
				int discid = appointmentDAO.saveDiscountRequest(invoiceId,userid,datetime,disctype,discval,""+accounts.getDebitAmount(),userid,direct_discount_reason,accounts.getClientid());
				int res11 = appointmentDAO.updateApproveInvoiceDiscout(invoiceId,userid,datetime,direct_discount_reason);
				int res1 = appointmentDAO.updateInvoiceDiscout(""+discid,userid,datetime,direct_discount_reason);
			}else if(isdirectdiscount.equals("2")){
				double discamt = appointmentDAO.getDiscountRequestAmount(invoiceId);
				int res = appointmentDAO.updateDiscRequestAmt(invoiceId,discamt);
				int res2= appointmentDAO.updateRefundRequestStatus(invoiceId);
				String datetime = DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone());
				int result = appointmentDAO.updateRequestInvoiceDiscout(invoiceId);
				int discid = appointmentDAO.saveDiscountRequest(invoiceId,userid,datetime,disctype,discval,""+accounts.getDebitAmount(),userid,direct_discount_reason,accounts.getClientid());
				int res11 = appointmentDAO.updateApproveInvoiceDiscout(invoiceId,userid,datetime,direct_discount_reason);
				int res1 = appointmentDAO.updateInvoiceDiscout(""+discid,userid,datetime,direct_discount_reason);
			}
			
			String datetime = DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone());
			int p = accountsDAO.updatePercentageAmountOfInvoice(invoiceId,discval,disctype,userid,datetime);
			
			/*double discdebit = Double.parseDouble(disctype);*/
			/*if(accountsForm.getDisctype().equals("0")){
				discdebit = (accounts.getDebitAmount() * Double.parseDouble(accountsForm.getDiscount())) /100;
			}*/
			double discdebit = Double.parseDouble(discval);
			if(disctype.equals("0")){
				discdebit = (accounts.getDebitAmount() * Double.parseDouble(discval)) /100;
			}
			
			ChargesAccountProcessingDAO chargesAccountProcessingDAO = new JDBCChargeAccountProcesDAO(connection);
			String serviceid = chargesAccountProcessingDAO.getLedgrServiceIds("Discount");
			String ledgerid = chargesAccountProcessingDAO.getledgerID(serviceid,"0","0");
			
			boolean checkdiscexsist = chargesAccountProcessingDAO.checkDiscEsist(invoiceId,ledgerid);
			if(!checkdiscexsist){
				double lbal = chargesAccountProcessingDAO.getLedgerBalance(ledgerid);
				lbal = lbal + discdebit;
				String credit = ""+discdebit+"";
				String lddebit = "0";
				String product = invoiceId;
				String partyid = ""+accounts.getClientid()+"";
				String ldcommencing = DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone());
				int saveledger = chargesAccountProcessingDAO.saveLedger(partyid,product,lddebit,credit,lbal,ledgerid,ldcommencing,invoiceId,0,"0","0","0","0","0",0,0,"0");
				
				//second effect
				lbal = 0;
				 credit = "0";
				 lddebit = ""+discdebit+"";
				 product = invoiceId;
				 partyid = ""+accounts.getClientid()+"";
				 ldcommencing = DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone());
				 saveledger = chargesAccountProcessingDAO.saveLedger(partyid,product,lddebit,credit,lbal,ledgerid,ldcommencing,invoiceId,0,"0","0","0","0","0",0,0,"0");

			}else{
				double ledgerdebit = chargesAccountProcessingDAO.getledgerDebitAmount(invoiceId,ledgerid);
				if(ledgerdebit<discdebit){
					double diffamr = discdebit - ledgerdebit;
					ledgerdebit = ledgerdebit + diffamr;
					int upd = chargesAccountProcessingDAO.updateLedgerDebitDisc(invoiceId,ledgerid,ledgerdebit);
					
					ArrayList<Master>balancelist = chargesAccountProcessingDAO.getLedgerBalanceList(ledgerid);
					for(Master master : balancelist){
						double balance = Double.parseDouble(master.getCharge()) + diffamr;
						int u = chargesAccountProcessingDAO.updateLedgerBalance(master.getId(),balance);
					}
					
				}
				
				if(ledgerdebit>discdebit){
					double diffamr = ledgerdebit - discdebit;
					ledgerdebit = ledgerdebit - diffamr;
					int upd = chargesAccountProcessingDAO.updateLedgerDebitDisc(invoiceId,ledgerid,ledgerdebit);
					
					ArrayList<Master>balancelist = chargesAccountProcessingDAO.getLedgerBalanceList(ledgerid);
					for(Master master : balancelist){
						double balance = Double.parseDouble(master.getCharge()) - diffamr;
						int u = chargesAccountProcessingDAO.updateLedgerBalance(master.getId(),balance);
					}
				}
				
			}
			
			if(isneededrefund.equals("1")){
				Accounts accounts2 =  accountsDAO.getInvoiceDetails(invoiceId);
				if(accounts2.getDisc_request()!=null){
					if(accounts2.getDisc_request().equals("3")){
						String discaproveuserid = accountsDAO.getDiscApproveUserid(invoiceId);
						String datetime2 = DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone());
						String date = DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone()).split(" ")[0];
						int result = additionalDAO.saveRequestRefundFromDiscount(invoiceId,accounts2,datetime2,date,refund_reason,refundamount,userid,discaproveuserid);
						int res = additionalDAO.saveRequestRefundFromDiscuntChild(invoiceId,accounts2,datetime2,date,userid,result,loginInfo.getId(),refundamount);
						/*UserProfileDAO userProfileDAO = new JDBCUserProfileDAO(connection);
						//already approved request
						ArrayList<UserProfile> arrayList = userProfileDAO.getRefundApproveUserList("1");
						for (UserProfile userProfile : arrayList) {
							int updatetstatus = userProfileDAO.updateBlankNotificationids("noti_ref_pay_ids",userProfile.getUserid());
							int updatecount = userProfileDAO.updateNotificationCount("noti_ref_pay_count",userProfile.getUserid());
							int updateids = userProfileDAO.updateNotificationIdValue("noti_ref_pay_ids",userProfile.getUserid(),""+result);
						}*/
					}
				}
			}
			
			
			
		}catch (Exception e) {
			e.printStackTrace();
		}finally{
			
			connection.close();
		}
		
		return null;
	}
	
	
	public String updatesave() throws SQLException{
//		System.out.println("hello");
		
		if(!verifyLogin(request)){
			return "login";
		}
		Connection connection  = null;
		try{
			
			connection  = Connection_provider.getconnection();
			AccountsDAO accountsDAO = new JDBCAccountsDAO(connection);
			AccountLogDAO accountLogDAO = new JDBCAccountLogDAO(connection);
			AppointmentTypeDAO appointmentTypeDAO=new JDBCAppointmentTypeDAO(connection);	
			ClientDAO clientDAO=new JDBCClientDAO(connection);
			BedDao bedDao=new JDBCBedDao(connection);
			
			ArrayList<Accounts>chrgesList = accountsDAO.getSumitedChargesList(accountsForm.getInvoiceid());
			
			int appointmentid = accountsDAO.getPaymentAppoinetmentId(""+accountsForm.getInvoiceid()+"");
			
			String fromdate=accountsForm.getHdfromDate();
			String todate=accountsForm.getHdtoDate();
			double sumtTotal=0;
			String invoicepayby=accountsForm.getInvoicepayby();
			String tpid=accountsForm.getTpid();
			String clientId=accountsForm.getClientId();
			String beforeinvoicepayby=accountsDAO.getInvoicePayBy(accountsForm.getInvoiceid());
			
			if(!invoicepayby.equals(beforeinvoicepayby)){
				int wardid=0;
				
				int ipdid=accountsDAO.getIpdIdFromInvoice(accountsForm.getInvoiceid());
				if(ipdid>0){
					Bed bed=bedDao.getEditIpdData(String.valueOf(ipdid));
					wardid= Integer.parseInt(bed.getWardid());
				}
				
				if(invoicepayby.equals("Third Party")){
					       
					 	for(Accounts accounts:chrgesList){
					 		
					 			ArrayList<Accounts> invoiceAssesmentList=accountsDAO.getAssesmentList(accounts.getInvoiceNo());
					 			
					 			sumtTotal=0;
					 			for(Accounts acc:invoiceAssesmentList){
					 				acc.setPayBy(1);
					 				 AppointmentType appointmentType=appointmentTypeDAO.getChargesDetailsByName(acc.getAppointmentType(),acc.getChargeType(),tpid,wardid);
					 				 if(appointmentType!=null){
					 					  
					 					if(appointmentType.getName().equals(acc.getAppointmentType())){
						 					 
					 						 double tot=Double.parseDouble(appointmentType.getCharges())* acc.getQuantity();
						 					 sumtTotal=sumtTotal+tot;
						 					 //update charges to tp charges
						 					 int result=accountsDAO.updateTpCharges(acc,appointmentType);
						 				 } else {
						 					 //discount code here
						 					   int val=Integer.parseInt(appointmentType.getCharges());
						 					   int discamt=val*20/100;
						 					   acc.setCharges(""+discamt+"");
						 					   sumtTotal=sumtTotal+discamt;
							 				   int result=accountsDAO.updateTpCharges(acc,appointmentType);
							 				   
						 				 }
					 				 } else {
					 					 
					 					   //charges Not Found of this type
					 				 }
					 				 
					 			}
					 			//update invoice payby and total  
					 			int result=accountsDAO.updateInvoicePayeeandCharges(invoicepayby,accountsForm.getInvoiceid(),sumtTotal,tpid);
					 	}
				} else {
					
					for(Accounts accounts:chrgesList){
						tpid="0";	
						ArrayList<Accounts> invoiceAssesmentList=accountsDAO.getAssesmentList(accounts.getInvoiceNo());
						sumtTotal=0;
			 			for(Accounts acc:invoiceAssesmentList){
			 				acc.setPayBy(0);
			 				
			 				 AppointmentType appointmentType=appointmentTypeDAO.getChargesDetailsByName(acc.getAppointmentType(), acc.getChargeType(), tpid, wardid);
			 				 if(appointmentType!=null){
			 					 
			 					if(appointmentType.getName().equals(acc.getAppointmentType())){
				 					 
			 						 double tot=Double.parseDouble(appointmentType.getCharges())* acc.getQuantity();
				 					 sumtTotal=sumtTotal+tot;
				 					 int result=accountsDAO.updateTpCharges(acc,appointmentType);
				 				 } else {
				 					  //Discount Code Here
				 					   int val=Integer.parseInt(appointmentType.getCharges());
				 					   int discamt=val*20/100;
				 					   acc.setCharges(""+discamt+"");
				 					   sumtTotal=sumtTotal+discamt;
					 				   int result=accountsDAO.updateTpCharges(acc,appointmentType);
				 				 }
			 					 
			 				 } else {
			 					 
			 					 double tot=Double.parseDouble(acc.getCharges())* acc.getQuantity();
			 					 sumtTotal=sumtTotal+tot;
			 					 //Charges Not Found 
			 				 }
			 			}
			 			
			 			//update invoice payby and total  
			 			int result=accountsDAO.updateInvoicePayeeandCharges(invoicepayby,accountsForm.getInvoiceid(),sumtTotal,tpid);
						
					}
					
				}
				
				
				//update client payee
				int result=clientDAO.updatePayeeofPatient(clientId,invoicepayby, tpid);
				
			}
			
			int updateinv=accountsDAO.updateHDfromDatetoDate(fromdate,todate,accountsForm.getInvoiceid());
			
			for(Accounts accounts : chrgesList){
				int updateChargeType = accountsDAO.updateModifiedInvoiceChargesChargeType(accounts.getCharges());
				
//				int deleteCharges = accountsDAO.deleteChargesOfChargesAssesment(accounts.getCharges());
				
				// log data			
				//String chargeType = "Charge";
				//int updateChargeType1 = accountLogDAO.updateChargeType(accounts.getCharges(), chargeType);
			}
			
			String tempArray[] = accountsForm.getTotalassesment().split(",");
			
			double sumofCharges = 0;
			
			if(tempArray.length!=0){
				for(String str : tempArray){
					if(Integer.parseInt(str)!=0){
						
						 sumofCharges += accountsDAO.getSumOfCharges(str);
						
						int save = accountsDAO.saveUpdateCharges(str,accountsForm.getInvoiceid());
					}
				}
			}
			
			if(accountsForm.getInvoiceDate()!=null){
				
				if(!accountsForm.getInvoiceDate().equals("")){
					
					String dater=DateTimeUtils.getCommencingDate1(accountsForm.getInvoiceDate());
					accountsForm.setInvoiceDate(dater);
				}
				
			}
			
			
			
			int update = accountsDAO.updateDebitAmount(sumofCharges,accountsForm.getInvoiceid(),accountsForm.getInvoiceDate(),accountsForm.getSubmitInvoiceNotes(),accountsForm.getDoctorid());
			ChargesAccountProcessingDAO chargesAccountProcessingDAO = new JDBCChargeAccountProcesDAO(connection);
			
			if(sumofCharges==0){
				 int upstatus = accountsDAO.resetOpdStatus(appointmentid,0);
			}
				
			if(update>0){
				int upledger = chargesAccountProcessingDAO.updateInvoiceLedgerDebit(sumofCharges,accountsForm.getInvoiceid());
			}
			
			
			// log data
			
			int resultLog = accountLogDAO.updateDebitAmount(sumofCharges,accountsForm.getInvoiceid(),accountsForm.getInvoiceDate(),accountsForm.getSubmitInvoiceNotes(),DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone()));
			
			setFormData();
			//  20/11/2018 Save modify user details
			CompleteAptmDAO completeAptmDAO=new JDBCCompleteAptmDAO(connection);
			String datetime = DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone());
			int res=completeAptmDAO.saveModifyInvoiceLog(loginInfo.getUserId(),datetime,String.valueOf(accountsForm.getInvoiceid()),1,"0");
			
	
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			connection.close();
		}
		
		return "updatesave";
	}
	
	
	
	
	
	public String reset() throws Exception{
		
		if(!verifyLogin(request)){
			return "login";
		}
		Connection connection  = null;
		try{
			
			connection  = Connection_provider.getconnection();
			AccountsDAO accountsDAO = new JDBCAccountsDAO(connection);
			
			
			String clientId = request.getParameter("clientId");
			int invoiceid = Integer.parseInt(request.getParameter("invoiceid"));
			String notes= request.getParameter("notes");
			session.setAttribute("sessionselectedclientid", clientId);
//			System.out.println(invoiceid);
			
			movepaymenttoadvance(clientId,invoiceid,connection);
			int rrr = accountsDAO.resetAppliedDiscount(invoiceid);
			
			//update opd status
			 int appointmentid = accountsDAO.getPaymentAppoinetmentId(""+invoiceid+"");
			 int upstatus = accountsDAO.resetOpdInvoiceStatus(appointmentid,invoiceid);

			 //update if charge discounted
			 ArrayList<Accounts>unitchargeList = accountsDAO.getUnitchargeList(invoiceid);
			 
//			
//			ArrayList<Accounts>chargeAssesmentList = accountsDAO.getchargeAssesmenyList(invoiceid);
//			
//			for(Accounts a : unitchargeList){
//				
//				int u = accountsDAO.updateDiscountedCharge(a.getUnitcharge(),a.getId());
//			}
			
			/*for(Accounts accounts : chargeAssesmentList){
				int update = accountsDAO.updateChargeType(Integer.toString(invoiceid), "Charge");
			}*/
//			if(loginInfo.getIskunal()==1){
				int res=accountsDAO.reverseCharges(invoiceid);
				int res1=accountsDAO.reveseAssesmentAmount(invoiceid);
				accountsDAO.updateInvoiceid(invoiceid);
//			}
			int delete = accountsDAO.deleteChargeAssesmentList(invoiceid);
			String userid = loginInfo.getUserId();
			String date ="";
			DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
			Calendar cal = Calendar.getInstance();
			date = dateFormat.format(cal.getTime());	
			//update status of invoice
			int result=accountsDAO.updateDeleteStatus(invoiceid,notes,userid,date);
			
			//int del = accountsDAO.deleteChargeInvoice(invoiceid);
//			result=accountsDAO.deleteChargesPayment(invoiceid); 
			result=accountsDAO.updatechargePaymrnt(invoiceid);
			session.setAttribute("sessionselectedinvoice", String.valueOf(invoiceid));
			accountsForm.setMessage("The invoice has been deleted Sucessfully!!");
			addActionMessage("The invoice has been deleted Sucessfully!!");
			session.setAttribute("sessionactiontype", request.getParameter("cancelinv"));
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			
			connection.close();
		}
		
		return "reset";
	}
	
	
	private void movepaymenttoadvance(String clientId, int invoiceid, Connection connection) {
		try{
			
			ChargesAccountProcessingDAO chargesAccountProcessingDAO = new JDBCChargeAccountProcesDAO(connection);
			ArrayList<Accounts>paymentList = chargesAccountProcessingDAO.getPaymentList(invoiceid);
			AdditionalDAO additionalDAO = new JDBCAdditionalDAO(connection);
			AccountsDAO accountsDAO = new JDBCAccountsDAO(connection);
			 ClinicDAO clinicDAO=new JDBCClinicDAO(connection);
			 ClientDAO clientDAO = new JDBCClientDAO(connection);
			 double balance =0;
			for(Accounts a : paymentList){
				String date = a.getCommencing();
				
				Client client = clientDAO.getClientDetails(clientId);
				String clientname = client.getTitle() + " " +client.getFirstName() + " " + client.getLastName();
				
				String type = "Pre_Payment";
				String creditnote = "";
				String charge = a.getAmountx();
				String payBuy = "Client";
				String paymode = a.getPaymentmode();
				
				 balance = additionalDAO.getCreditTotal(clientId);
				
				
					
					//reset invoice
					creditnote= request.getParameter("paymentNote");
					int resetinv = accountsDAO.getMaxResetInv();
					int resetcreditinv = accountsDAO.getMaxResetCreditInv();
					int rinv = 0;
					if(resetinv>resetcreditinv){
						rinv = resetinv + 1;
					}else{
						rinv = resetcreditinv + 1;
					}
					String useridpay=additionalDAO.getpaymentUserId(invoiceid);
					balance = balance + Double.parseDouble(charge);
					int crinvoiceid = additionalDAO.saveCreditRecord(clientId, type, date, creditnote,payBuy,charge,paymode,balance,rinv,useridpay,invoiceid);
					int flg=accountsDAO.updatecreditaccountsts(crinvoiceid);
					
					additionalDAO.createSeqnogenProccessForAdvAndRef("0", ""+crinvoiceid, "","");
					//lokesh
					if(crinvoiceid>0){
					String practid = chargesAccountProcessingDAO.getInvoiceDoctorid(invoiceid);
						int pr= additionalDAO.setPractionerinCreditacc(crinvoiceid, practid);
					}
					
					CompleteAppointment appointment = new CompleteAppointment();
					appointment.setApmtType("payment converted to advance");
					appointment.setChargedescription(a.getAmountx());
					int result = additionalDAO.saveCreditAssessment(clientId, clientname, type, date, crinvoiceid,appointment);
					
					int save = additionalDAO.saveCreditAssessmentRecord(clientId, "", type, date, crinvoiceid,charge,accountsForm.getAdvref());
					
					int maxno = additionalDAO.getMaxAdvno(crinvoiceid);
					String invoicetype = "Advance";
					int paymentids = 0;
					int u = additionalDAO.updateAdvMaxno(crinvoiceid,maxno,invoicetype,paymentids);

					//Advance Ledger
					if(crinvoiceid>0){
						String serviceid = chargesAccountProcessingDAO.getLedgrServiceIds("Advance & Refund");
						String ledgerid = chargesAccountProcessingDAO.getledgerID(serviceid,paymode,accountsForm.getBnkname());
						
						double lbal = chargesAccountProcessingDAO.getLedgerBalance(ledgerid);
						lbal = lbal + Double.parseDouble(charge);
						String credit = "0";
						String ldebit = charge;
						String product = "Advance";
						String partyid = clientId;
						String lcommencing = DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone());
						int saveledger = chargesAccountProcessingDAO.saveLedger(partyid,product,ldebit,credit,lbal,ledgerid,lcommencing,""+crinvoiceid+"",0,"0","0","0","0","0",0,0,"0");
						
						//second effect
						lbal = 0;
						 credit = charge;
						 ldebit = "0";
						 product = "Advance";
						 partyid = clientId;
						 lcommencing = DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone());
						 saveledger = chargesAccountProcessingDAO.saveLedger(partyid,product,ldebit,credit,lbal,ledgerid,lcommencing,""+crinvoiceid+"",0,"0","0","0","0","0",0,0,"0");
						
					}
					
			}
//			    Clinic clinic=clinicDAO.getCliniclistDetails(loginInfo.getClinicid());
//			    Client client = clientDAO.getClientDetails(clientId);
//				String clientname = client.getTitle() + " " +client.getFirstName() + " " + client.getLastName();
//			     String msg=""+clientname+", Your Invoice has been cancelled and paid amount Rs. "+balance+" has been move into Advance. From- "+clinic.getClinicName()+"";
//			     SmsService service= new SmsService();
//			     service.sendSms(msg, client.getMobNo(), loginInfo, new EmailLetterLog());
			
		}catch (Exception e) {
			// TODO: handle exception
		}
		
	}


	public String modifys() throws SQLException{
		
		if(!verifyLogin(request)){
			return "login";
		}
		Connection connection  = null;
		try{
			
			connection  = Connection_provider.getconnection();
			AccountsDAO accountsDAO = new JDBCAccountsDAO(connection);
			ClientDAO clientDAO=new JDBCClientDAO(connection);  
			StatementDAO statementDAO = new JDBCStatementDAO(connection);
			CompleteAptmDAO completeAptmDAO = new JDBCCompleteAptmDAO(connection);
			DateTimeUtils dateTimeUtils = new DateTimeUtils();
			
			
			String payby = request.getParameter("payby");
			String numberOfChages = request.getParameter("numberOfChages1");
			String creditCharge = request.getParameter("creditCharge1");
			String debitAmt = request.getParameter("debitamt1");
			String creditAmt = request.getParameter("creditamt1");
			String balance = request.getParameter("balance1");
			String clientId = request.getParameter("clientId");
			String discount1 = request.getParameter("discount1");
			
			if(balance!=null){
				if(balance.equals("")){
					balance ="0";
				}
			}else{
				balance ="0";
			}
			
			accountsForm.setNewbalnace(Double.parseDouble(balance));
			
			ArrayList<Bed> wardlist=new ArrayList<Bed>();
			BedDao bedDao = new  JDBCBedDao(connection);
			wardlist=bedDao.getAllWardList(pagination=null);	
			accountsForm.setWardlist(wardlist);
		
			
			
			
			
			int invoiceid = Integer.parseInt(request.getParameter("invoiceid1"));
			
			String invoicepayby=accountsDAO.getInvoicePayBy(invoiceid);
					
					Client patient=clientDAO.getClientDetails(clientId);
					if(patient.getWhopay().equals("Third Party")){
						
						String tpname=patient.getTpDetails().getCompanyName();
						accountsForm.setTpname(tpname);
						String tpid=patient.getTypeName();
						accountsForm.setTpid(tpid);
					}else {
						accountsForm.setTpname("0");
						accountsForm.setTpid("0");
					}
					
			Accounts accountsfromtodate=accountsDAO.getFromtodateforHD(invoiceid);
			
			if(accountsfromtodate.getFromDate()!=null){
				
				if(payby!=null){
					
					if(payby.equals("Third Party")){
						accountsForm.setInvcetype("HD");
						accountsForm.setHdfromDate(accountsfromtodate.getFromDate());
						accountsForm.setHdtoDate(accountsfromtodate.getToDate());
					} else {
						accountsForm.setInvcetype("");
					}
				}
				
			} else {
				accountsForm.setInvcetype("");
			}
			accountsForm.setInvoicepayby(invoicepayby);
			
			double debitAmount = completeAptmDAO.getModifyInvoiceTotalDebitAmmount(invoiceid);
			
			balance = dateTimeUtils.changeFormat(debitAmount);
			
			ChargesAccountProcessingDAO chargesAccountProcessingDAO = new JDBCChargeAccountProcesDAO(connection);
			ArrayList<Accounts>assesmentList = chargesAccountProcessingDAO.getAssesmentList(invoiceid);

			//ArrayList<Accounts>assesmentList = accountsDAO.getAssesmentList(invoiceid,Integer.parseInt(payby));
			double discount = chargesAccountProcessingDAO.getDiscount(invoiceid);

			accountsForm.setAssesmentList(assesmentList);
			accountsForm.setCreditCharge(creditCharge);
			accountsForm.setInvoiceid(invoiceid);
			accountsForm.setPayby(payby);
			accountsForm.setNumberOfChages(Integer.parseInt(numberOfChages));
			accountsForm.setCreditAmt(creditAmt);
			accountsForm.setDebitAmt(balance);
			accountsForm.setBalance(Double.parseDouble(balance));
			accountsForm.setClientId(clientId);
			accountsForm.setDiscount(discount1);			
			
			accountsForm.setNumberOfChages(assesmentList.size());
			
			//Decimal Account
			accountsForm.setBalancex(balance);
			
		//	String clientId = accountsForm.getClientId();
			String client = accountsForm.getClient();
		//	String payby = accountsForm.getPayby();
			String fromDate = accountsForm.getFromDate();
			String toDate = accountsForm.getToDate();
			
			session.setAttribute("clientId", clientId);
			session.setAttribute("client", client);
			session.setAttribute("payby", payby);
			session.setAttribute("fromDate", fromDate);
			session.setAttribute("toDate", toDate);
			session.setAttribute("transactionType", accountsForm.getTransactionType());
			
			session.setAttribute("fromDate", "");
			session.setAttribute("toDate", "");
			session.setAttribute("transactionType", "");
			
			Accounts accounts = chargesAccountProcessingDAO.getInvoiceDetails(invoiceid);
			accountsForm.setInvoiceDate(DateTimeUtils.getCommencingDate1(accounts.getCommencing()));
			accountsForm.setSubmitInvoiceNotes(accounts.getNotes());
			
			//  06 Jun 2018 for invoice select at least one when all charges are deleted
			if(assesmentList.size()<=0){
				accountsForm.setIsforrefund("1");
			}
			
			
			//lokesh allow to select pract from 
			NotAvailableSlotDAO notAvailableSlotDAO= new JDBCNotAvailableSlotDAO(connection);
			ArrayList<DiaryManagement> userList = notAvailableSlotDAO.getUserAccountList(loginInfo.getClinicid());
			accountsForm.setUserList(userList);
			accountsForm.setDoctorid(String.valueOf(accounts.getPractitionerId()));
			
			
			MasterDAO masterDAO= new JDBCMasterDAO(connection);
			ArrayList<Master> taxlist= new ArrayList<Master>();
			taxlist= masterDAO.getMasterList("his_tax_master");
			accountsForm.setTaxlist(taxlist);
			//  26-06-2019
			/*double paidamt = statementDAO.getCreditAmount(invoiceid);
			double refundamt = statementDAO.getRefundChargeAmt(invoiceid);
			double actualamt = paidamt - refundamt;
			int paymentdone =0;
			if(paidamt>0){
				paymentdone =1;
			}
			accountsForm.setPaidamt(DateTimeUtils.changeFormat(actualamt));
			accountsForm.setPaymentdone(""+paymentdone);*/
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		finally{
			connection.close();
		}
		
		return "modifyinvoice";
		
		
	}
	
	
	public void createPdf(String transactionType,String clientId,String payby,String fromDate,String toDate) throws Exception{
		
		Connection connection = null;
		
		try{
			
			connection = Connection_provider.getconnection();
			ChargesAccountProcessingDAO chargesAccountProcessingDAO = new JDBCChargeAccountProcesDAO(connection);
			
			Clinic clinic = new Clinic();
			ClinicDAO clinicDAO  = new JDBCClinicDAO(connection);
			clinic = clinicDAO.getCliniclistDetails(loginInfo.getClinicid());
			
			StringBuffer str = new StringBuffer();
			str.append("<style>");
			str.append(".prew-table {");
			str.append("background:#FFFFFF;");
			str.append("color:#666666;");
			str.append("font-family:Arial,Helvetica,sans-serif;");
			str.append("font-size:62.5%;");
			str.append("border-left:1px solid #DFD8D4;");
			str.append("font-size:1em;}");
			str.append(".prew-table caption {");
			str.append("font-size:1.4em;");
			str.append("font-weight:bold;");
			str.append("padding:6px;");
			str.append("text-align:left;}");
			str.append(".prew-table th {");
			str.append("padding:6px 6px 6px 12px;");
			str.append("text-align:left;");
			str.append("font-weight:bold;");
			str.append("font-size: small;");
			str.append("border-bottom:1px solid #DFD8D4;");
			str.append("border-right:1px solid #DFD8D4;");
			str.append("border-top:1px solid #DFD8D4;");
			str.append("background-color: rgb(236, 233, 233);}");
			str.append(".prew-table td {");
			str.append("padding:6px 6px 6px 0px;");
			str.append("border-bottom:1px solid #DFD8D4;");
			str.append("border-right:1px solid #DFD8D4;");
			str.append("font-size: small;}");
			str.append("</style>");
			
			str.append("<div id='login' class='block_div'>");
			str.append("<div style='font-size: 20px; font-weight: bold;'>"+clinic.getClinicName()+"</div>");
			str.append("<div style='font-size: 18px; font-weight: bold;'>"+clinic.getClinicOwner()+" , "+clinic.getOwner_qualification()+"</div>");
			str.append("<div style='font-size: 18px; font-weight: bold;'>"+clinic.getAddress()+"</div>");
			str.append("<div style='font-size: 16px; font-weight: normal;'>Tel/Fax:"+clinic.getLandLine()+"</div>");
			str.append("<div style='font-size: 16px; font-weight: normal;'>E: "+clinic.getEmail()+" W: "+clinic.getWebsiteUrl()+"</div><br><br>");
			
			str.append("<table cellpadding='0' cellspacing='0' class='prew-table'  style='width:100%; border-top:1px solid #DFD8D4;'>");
			str.append("<col width='50%'>");
			str.append("<col width='50%'>");
			str.append("<tr><td align='center' colspan='2' style='font-weight: bold; font-size: 16px;' >Statement Of Account </td></tr>");
			str.append("<tr>");
			/*str.append("<td style='font-weight: bold;'>");
			str.append("<label>Mr Steven Guise</label><br>");
			str.append("<label>25 Melbourne Close</label><br>");
			str.append("<label>NUNEATON</label><br>");
			str.append("<label>CV11 4RX</label><br>");
			str.append("</td>");*/
			
			str.append("<td>");
			str.append("<table  width='100%' style=' font-size:16px; '>");
			str.append("<tr><td style='font-weight: bold;'>Account No</td>");
			str.append("<td>02796554023</td></tr>");
			str.append("<tr><td style='font-weight: bold;'>Date</td>");
			str.append("<td>14/07/2014</td></tr>");
			str.append("<tr><td style='font-weight: bold;'>Page</td>");
			str.append("<td>1</td></tr>");
			str.append("</table></td></tr></table><br><br>");
			
			str.append("<table cellpadding='0' cellspacing='0' class='prew-table'  style='width:100%; '>");
			str.append("<tr>");
			str.append("<th>Date</th>");
			str.append("<th>Transaction</th>");
			str.append("<th>Payee</th>");
			str.append("<th>Status</th>");
			str.append("<th>Credit</th>");
			str.append("<th>Debit</th>");
			str.append("<th>Balance</th>");
			str.append("</tr>");
			
			ArrayList<Accounts>chargeProcessingList = new ArrayList<Accounts>();
			
			if(accountsForm.getTransactionType().equals(Constants.PENDING_PEYMENT)){
				chargeProcessingList = chargesAccountProcessingDAO.getChargesAccountProcessingPendingPrintList(clientId,payby,fromDate,toDate);
			}else if(accountsForm.getTransactionType().equals(Constants.PAID)){
				chargeProcessingList = chargesAccountProcessingDAO.getChargesAccountProcessingPaidPrintList(clientId,payby,fromDate,toDate);
			}else{
				chargeProcessingList = chargesAccountProcessingDAO.getChargesAccountProcessingPrintList(clientId,payby,fromDate,toDate);
			}
			
			for(Accounts accounts : chargeProcessingList){
				
				str.append("<tr>");
				str.append("<td>"+accounts.getDate()+"</td>");
				str.append("<td>Invoice(0000"+accounts.getId()+")</td>");
				str.append("<td>"+accounts.getPayby()+"</td>");
				if(accounts.getBalance()==0){
					str.append("<td>Paid</td>");
				}else{
					str.append("<td>Not Paid</td>");
				}
				str.append("<td>"+accounts.getCreditCharge()+"</td>");
				str.append("<td>"+accounts.getDebitAmount()+"</td>");
				str.append("<td>"+accounts.getBalance()+"</td>");
				
				str.append("</tr>");
			}
			
			
			str.append("</table><br><br>");
			
			String filePath = request.getRealPath("//invoice//");
			String filename = "statement.pdf";
			accountsForm.setFilename(filename);
			htmlToPdfFile(str.toString(), filePath, filename);

		}catch(Exception e){
			e.printStackTrace();
		}finally{
			
			connection.close();
		}
		
	}
	
	
	public String input(){
		if(!verifyLogin(request)){
			return "login";
		}
		return "showChargesProcessingPage";
	}
	
	public void setFormData() throws Exception{
		String clientId = (String) session.getAttribute("clientId");
		String client = (String) session.getAttribute("client");
		String payby = (String) session.getAttribute("payby");
		String fromDate = (String) session.getAttribute("fromDate");
		String toDate = (String) session.getAttribute("toDate");
		String transactionType = (String)session.getAttribute("transactionType");
		pagination = (Pagination) session.getAttribute("pagination");
		
		if(!fromDate.equals("")){
			String temp[]= fromDate.split("/");
			fromDate = temp[2]+"-"+temp[1]+"-"+temp[0];
		}
		if(!toDate.equals("")){
			String temp1[]= toDate.split("/");
			toDate = temp1[2]+"-"+temp1[1]+"-"+temp1[0];
		}
		
		String defaultVal = "All";
		if(payby.equals(defaultVal)){
			payby = "";
		}
		
		Connection connection = null;
		try{
			
			connection = Connection_provider.getconnection();
			ChargesAccountProcessingDAO chargesAccountProcessingDAO = new JDBCChargeAccountProcesDAO(connection);
			ArrayList<Accounts>chargeProcessingList = new ArrayList<Accounts>();
			int totalCount = chargesAccountProcessingDAO.getTotalChargesAccountProcessingCount(clientId,payby,fromDate,toDate);
			pagination.setPreperties(totalCount);
			
			if(transactionType.equals(Constants.PENDING_PEYMENT)){
				chargeProcessingList = chargesAccountProcessingDAO.getChargesAccountProcessingPendingList(clientId,payby,fromDate,toDate,pagination);
			}else if(transactionType.equals(Constants.PAID)){
				chargeProcessingList = chargesAccountProcessingDAO.getChargesAccountProcessingPaidList(clientId,payby,fromDate,toDate,pagination);
			}else{
				chargeProcessingList = chargesAccountProcessingDAO.getChargesAccountProcessingList(clientId,payby,fromDate,toDate,pagination,accountsForm.getActionType(),accountsForm.getSelectedInvoiceid());
			}
			
			
			pagination.setPage_records(chargeProcessingList.size());
			accountsForm.setTotalRecords(chargeProcessingList.size());
			accountsForm.setPagerecords(Integer.toString(pagination.getPage_records()));
			accountsForm.setChargeProcessingList(chargeProcessingList);
			
			session.setAttribute("pagination", pagination);
			
			accountsForm.setClientId(clientId);
			accountsForm.setClient(client);
			accountsForm.setFromDate(fromDate);
			accountsForm.setToDate(toDate);
			
			String clientName = chargesAccountProcessingDAO.getClientFullName(clientId); 
			accountsForm.setClient(clientName);
			
			accountsForm.setTransactionType(transactionType);
			if(payby.equals("")){
				payby = "All";
			}
			accountsForm.setPayby(payby);
			CompleteAppointment completeAppointment = new CompleteAppointment();
			CompleteAptmDAO completeAptmDAO = new JDBCCompleteAptmDAO(connection);
			completeAppointment = completeAptmDAO.getInsuranceCompanyName(clientId);
			accountsForm.setInsuranceCompany(completeAppointment.getInsuranceCompanyName());
			accountsForm.setThirdPartyAddress(completeAppointment.getInsuranceCompanyAddress());
			accountsForm.setThirdPartyContacttno(completeAppointment.getThirdPartyContacttno());
			accountsForm.setThirdPartyPostcode(completeAppointment.getThirdPartyPostcode());
			accountsForm.setThirdPartyemail(completeAppointment.getThirdPartyemail());
			
			AccountsDAO accountsDAO = new JDBCAccountsDAO(connection);
			
			ArrayList<Accounts> clientDetailsList = accountsDAO.getClientDetails(clientId);
				for(Accounts a1:clientDetailsList){
					accountsForm.setInitial(a1.getInitial());
					accountsForm.setFirstname(a1.getFirstname());
					accountsForm.setLastname(a1.getLastname());
					accountsForm.setAddress(a1.getAddress());
					accountsForm.setCity(a1.getCity());
					accountsForm.setClientId(clientId);
					accountsForm.setPostcode(a1.getPostcode());
					accountsForm.setEmail(a1.getEmail());
					accountsForm.setMobno(a1.getMobno());
				}
			
		}
		catch(Exception e){
			e.printStackTrace();
		}finally{
			
			connection.close();
		}
		
		
	}
	
	public String move() throws Exception{
		if(!verifyLogin(request)){
			return "login";
		}
		String clientId = (String) session.getAttribute("clientId");
		String client = (String) session.getAttribute("client");
		String payby = (String) session.getAttribute("payby");
		String fromDate = (String) session.getAttribute("fromDate");
		String toDate = (String) session.getAttribute("toDate");
		String transactionType = (String)session.getAttribute("transactionType");
		//pagination = (Pagination) session.getAttribute("pagination");
		
		if(!fromDate.equals("")){
			String temp[]= fromDate.split("/");
			fromDate = temp[2]+"-"+temp[1]+"-"+temp[0];
		}
		if(!toDate.equals("")){
			String temp1[]= toDate.split("/");
			toDate = temp1[2]+"-"+temp1[1]+"-"+temp1[0];
		}
		
		String defaultVal = "All";
		if(payby.equals(defaultVal)){
			payby = "";
		}
		
		Connection connection = null;
		try{
			
			connection = Connection_provider.getconnection();
			ChargesAccountProcessingDAO chargesAccountProcessingDAO = new JDBCChargeAccountProcesDAO(connection);
			ArrayList<Accounts>chargeProcessingList = new ArrayList<Accounts>();
			int totalCount = chargesAccountProcessingDAO.getTotalChargesAccountProcessingCount(clientId,payby,fromDate,toDate);
			pagination.setPreperties(totalCount);
			
			if(transactionType.equals(Constants.PENDING_PEYMENT)){
				chargeProcessingList = chargesAccountProcessingDAO.getChargesAccountProcessingPendingList(clientId,payby,fromDate,toDate,pagination);
			}else if(transactionType.equals(Constants.PAID)){
				chargeProcessingList = chargesAccountProcessingDAO.getChargesAccountProcessingPaidList(clientId,payby,fromDate,toDate,pagination);
			}else{
				chargeProcessingList = chargesAccountProcessingDAO.getChargesAccountProcessingList(clientId,payby,fromDate,toDate,pagination,accountsForm.getActionType(),accountsForm.getSelectedInvoiceid());
			}
			
			pagination.setPage_records(chargeProcessingList.size());
			accountsForm.setTotalRecords(chargeProcessingList.size());
			accountsForm.setPagerecords(Integer.toString(pagination.getPage_records()));
			accountsForm.setChargeProcessingList(chargeProcessingList);
			session.setAttribute("pagination", pagination);
			
			accountsForm.setClientId(clientId);
			accountsForm.setClient(client);
			accountsForm.setFromDate(fromDate);
			accountsForm.setToDate(toDate);
			accountsForm.setPayby(payby);
			if(payby.equals("")){
				payby = "All";
			}
			accountsForm.setPayby(payby);
			CompleteAppointment completeAppointment = new CompleteAppointment();
			CompleteAptmDAO completeAptmDAO = new JDBCCompleteAptmDAO(connection);
			completeAppointment = completeAptmDAO.getInsuranceCompanyName(clientId);
			accountsForm.setInsuranceCompany(completeAppointment.getInsuranceCompanyName());
			accountsForm.setThirdPartyAddress(completeAppointment.getInsuranceCompanyAddress());
			accountsForm.setThirdPartyContacttno(completeAppointment.getThirdPartyContacttno());
			accountsForm.setThirdPartyPostcode(completeAppointment.getThirdPartyPostcode());
			accountsForm.setThirdPartyemail(completeAppointment.getThirdPartyemail());
			
			AccountsDAO accountsDAO = new JDBCAccountsDAO(connection);
			
			ArrayList<Accounts> clientDetailsList = accountsDAO.getClientDetails(clientId);
				for(Accounts a1:clientDetailsList){
					accountsForm.setInitial(a1.getInitial());
					accountsForm.setFirstname(a1.getFirstname());
					accountsForm.setLastname(a1.getLastname());
					accountsForm.setAddress(a1.getAddress());
					accountsForm.setCity(a1.getCity());
					accountsForm.setClientId(clientId);
					accountsForm.setPostcode(a1.getPostcode());
					accountsForm.setEmail(a1.getEmail());
					accountsForm.setMobno(a1.getMobno());
				}
			
		}
		catch(Exception e){
			e.printStackTrace();
		}finally{
			
			connection.close();
		}
		
		return "showChargesProcessingPage";
	}
	
	
	
	
	public String showallpharmacyssessment() throws Exception{
		if(!verifyLogin(request)){
			return "login";
		}
		
		int invoiceid = Integer.parseInt(request.getParameter("invoiceId"));
		
		Connection connection  = null;
		
		try{
			
			connection  = Connection_provider.getconnection();
			PharmacyDAO pharmacyDAO=new JDBCPharmacyDAO(connection);
			ArrayList<Accounts>assesmentList = pharmacyDAO.getAssesmentList(invoiceid);
			StringBuffer str = new StringBuffer();
			str.append("<table class='table-bordered table-condensed width-full'> ");
			
			str.append("<thead>");
			str.append("<tr>");
			str.append("<th>Charge No.</th> ");
			str.append("<th>Total</th> ");
			
			str.append("</tr>");
			str.append("</thead>");
			str.append("<tbody>");
			for(Accounts accounts:assesmentList){
				str.append("<tr class= 'bg-info'>");
				str.append("<td>Charges(0000"+accounts.getInvoiceNo()+ ") "+accounts.getDate()+"<a href = 'javascript: void(0);' onclick = showInnerChildDiv('divid"+accounts.getInvoiceNo()+"','"+accounts.getInvoiceNo()+"','"+accounts.getPayBy()+"');><i class='fa fa-arrow-down'></i></a></td>");
				str.append("<td>"+accounts.getTotalAmountx()+"</td>");
				str.append("</tr>");
				
				str.append("<tr id = 'divid"+accounts.getInvoiceNo()+"' style='display: none'>");
				str.append("<td colspan='7' id = 'divid1"+accounts.getInvoiceNo()+"'></td>");
				str.append("</tr>");

			
			}
			
			str.append("</tbody>");
			str.append("</table>");
			response.setContentType("text/html");
			response.setHeader("Cache-Control", "no-cache");
			
			response.getWriter().write(""+str.toString()+""); 
		}
		catch(Exception e){
			e.printStackTrace();
		}finally{
			
			connection.close();
		}
		return null;
	}
	
	
	
	
	
	public String showAllAssessment() throws Exception{
		if(!verifyLogin(request)){
			return "login";
		}
		
		int invoiceid = Integer.parseInt(request.getParameter("invoiceId"));
		
		Connection connection  = null;
		
		try{
			
			connection  = Connection_provider.getconnection();
			AccountsDAO accountsDAO = new JDBCAccountsDAO(connection);
			ChargesAccountProcessingDAO chargesAccountProcessingDAO = new JDBCChargeAccountProcesDAO(connection);
			ArrayList<Accounts>assesmentList = chargesAccountProcessingDAO.getAssesmentList(invoiceid);
			StringBuffer str = new StringBuffer();
			str.append("<table class='table-bordered table-condensed width-full'> ");
			
			str.append("<thead>");
			str.append("<tr>");
			str.append("<th>Charge No.</th> ");
			str.append("<th>Total</th> ");
			
			str.append("</tr>");
			str.append("</thead>");
			str.append("<tbody>");
			for(Accounts accounts:assesmentList){
				str.append("<tr class= 'bg-info'>");
				str.append("<td>Charges(0000"+accounts.getInvoiceNo()+ ") "+accounts.getDate()+"<a href = 'javascript: void(0);' onclick = showInnerChildDiv('divid"+accounts.getInvoiceNo()+"','"+accounts.getInvoiceNo()+"','"+accounts.getPayBy()+"');><i class='fa fa-arrow-down'></i></a></td>");
				str.append("<td>"+accounts.getTotalAmountx()+"</td>");
				str.append("</tr>");
				
				str.append("<tr id = 'divid"+accounts.getInvoiceNo()+"' style='display: none'>");
				str.append("<td colspan='7' id = 'divid1"+accounts.getInvoiceNo()+"'></td>");
				str.append("</tr>");

			
			}
			
			str.append("</tbody>");
			str.append("</table>");
			response.setContentType("text/html");
			response.setHeader("Cache-Control", "no-cache");
			
			response.getWriter().write(""+str.toString()+""); 
		}
		catch(Exception e){
			e.printStackTrace();
		}finally{
			
			connection.close();
		}
		return null;
	}
	
	public String showchildassessmentpharmacy() throws Exception{
		if(!verifyLogin(request)){
			return "login";
		}
		
		int invoiceid = Integer.parseInt(request.getParameter("invoiceId"));
		int payby = Integer.parseInt(request.getParameter("payby"));
		
		Connection connection  = null;
		
		try{
			
			connection  = Connection_provider.getconnection();
			AccountsDAO accountsDAO = new JDBCAccountsDAO(connection);
			PharmacyDAO pharmacyDAO=new JDBCPharmacyDAO(connection);
			ChargesAccountProcessingDAO chargesAccountProcessingDAO = new JDBCChargeAccountProcesDAO(connection);
			ArrayList<Accounts>assesmentList = pharmacyDAO.getAssesmentList(invoiceid);
			StringBuffer str = new StringBuffer();
			  
			     
			//str.append("<div class='modal fade' tabindex='-1' role='dialog' aria-labelledby='myModalLabel' aria-hidden='true'>");
			//str.append("<div class='modal-dialog modal-lg'>");
			//str.append("  <div class='modal-content'>");
			//str.append(" <div class='modal-header'>");
	
			str.append("<table class='table-bordered table-condensed width-full modal-content ' aria-hidden='true' > ");
			
			str.append("<thead>");
			str.append("<tr>");
			str.append("<th colspan ='3'></th> ");
			str.append("<th colspan ='2'>Charge Type</th> ");
			str.append("<th>Description</th> ");
			
			if(loginInfo.getUserType()==2){
				str.append("<th style='display:bock'>Charge</th> ");
			}else if(payby==0 && loginInfo.getUserType()==4){
				str.append("<th style='display:bock'>Charge</th> ");
			}else if(payby==1 && loginInfo.getUserType()==4){
				str.append("<th style='display:none'>Charge</th> ");
			}
			
			str.append("<th>Qty.</th> ");
			str.append("<th>Cost</th> ");
			str.append("<th colspan='4'></th> ");
			str.append("</tr>");
			str.append("</thead>");
			
			str.append("<tbody>");
			for(Accounts accounts:assesmentList){
			str.append("<tr class = 'bg-warning'>");
			
			str.append("<td  colspan ='3'></td>");
			str.append("<td  colspan ='2'> "+accounts.getAppointmentType()+ " ("+accounts.getLocationName()+") </td>");
			if(!accounts.getPractitionerName().equals("")){
				str.append("<td>"+accounts.getTreatmentEpisodeName()+"<br>Practitioner: "+accounts.getPractitionerName()+ "</td>");
			}else{
				str.append("<td></td>");
			}
			
			if(loginInfo.getUserType()==2){
				str.append("<td style='display:block'>"+accounts.getCharges()+"</td>");
			}else if(payby==0 && loginInfo.getUserType()==4){
				str.append("<td style='display:block'>"+accounts.getCharges()+"</td>");
			}else if(payby==1 && loginInfo.getUserType()==4){
				str.append("<td style='display:none'>"+accounts.getCharges()+"</td>");
			}
			
			str.append("<td>"+accounts.getQuantity()+"</td>");
			double chargeTotal = Double.parseDouble(accounts.getCharges()) * accounts.getQuantity(); 
			str.append("<td>"+chargeTotal+"</td>");
			
			str.append("<td colspan='4'></td> ");
			str.append("</tr>");
			
			}
			str.append("</tbody>");
			str.append("</table>");
			//str.append("</div>");
			//str.append("</div>");
			//str.append("</div>");
			//str.append("</div>");
			response.setContentType("text/html");
			response.setHeader("Cache-Control", "no-cache");
			
			response.getWriter().write(""+str.toString()+""); 
		}
		catch(Exception e){
			e.printStackTrace();
		}finally{
			
			connection.close();
		}
		return null;
	}
	
	
	
	public String showChildAssessment() throws Exception{
		if(!verifyLogin(request)){
			return "login";
		}
		
		int invoiceid = Integer.parseInt(request.getParameter("invoiceId"));
		int payby = Integer.parseInt(request.getParameter("payby"));
		
		Connection connection  = null;
		
		try{
			
			connection  = Connection_provider.getconnection();
			AccountsDAO accountsDAO = new JDBCAccountsDAO(connection);
			ChargesAccountProcessingDAO chargesAccountProcessingDAO = new JDBCChargeAccountProcesDAO(connection);
			ArrayList<Accounts>assesmentList = chargesAccountProcessingDAO.getChildAssesmentList(invoiceid);
			StringBuffer str = new StringBuffer();
			  
			     
			//str.append("<div class='modal fade' tabindex='-1' role='dialog' aria-labelledby='myModalLabel' aria-hidden='true'>");
			//str.append("<div class='modal-dialog modal-lg'>");
			//str.append("  <div class='modal-content'>");
			//str.append(" <div class='modal-header'>");
	
			str.append("<table class='table-bordered table-condensed width-full modal-content ' aria-hidden='true' > ");
			
			str.append("<thead>");
			str.append("<tr>");
			str.append("<th colspan ='3'></th> ");
			str.append("<th colspan ='2'>Charge Type</th> ");
			str.append("<th>Description</th> ");
			
			if(loginInfo.getUserType()==2){
				str.append("<th style='display:bock'>Charge</th> ");
			}else if(payby==0 && loginInfo.getUserType()==4){
				str.append("<th style='display:bock'>Charge</th> ");
			}else if(payby==1 && loginInfo.getUserType()==4){
				str.append("<th style='display:none'>Charge</th> ");
			}
			
			str.append("<th>Qty.</th> ");
			str.append("<th>Cost</th> ");
			str.append("<th colspan='4'></th> ");
			str.append("</tr>");
			str.append("</thead>");
			
			str.append("<tbody>");
			for(Accounts accounts:assesmentList){
			str.append("<tr class = 'bg-warning'>");
			
			str.append("<td  colspan ='3'></td>");
			str.append("<td  colspan ='2'> "+accounts.getAppointmentType()+ " ("+accounts.getLocationName()+") </td>");
			if(!accounts.getPractitionerName().equals("")){
				str.append("<td>"+accounts.getTreatmentEpisodeName()+"<br>Practitioner: "+accounts.getPractitionerName()+ "</td>");
			}else{
				str.append("<td></td>");
			}
			
			if(loginInfo.getUserType()==2){
				str.append("<td style='display:block'>"+accounts.getCharges()+"</td>");
			}else if(payby==0 && loginInfo.getUserType()==4){
				str.append("<td style='display:block'>"+accounts.getCharges()+"</td>");
			}else if(payby==1 && loginInfo.getUserType()==4){
				str.append("<td style='display:none'>"+accounts.getCharges()+"</td>");
			}
			
			str.append("<td>"+accounts.getQuantity()+"</td>");
			double chargeTotal = Double.parseDouble(accounts.getCharges()) * accounts.getQuantity(); 
			str.append("<td>"+chargeTotal+"</td>");
			
			str.append("<td colspan='4'></td> ");
			str.append("</tr>");
			
			}
			str.append("</tbody>");
			str.append("</table>");
			//str.append("</div>");
			//str.append("</div>");
			//str.append("</div>");
			//str.append("</div>");
			response.setContentType("text/html");
			response.setHeader("Cache-Control", "no-cache");
			
			response.getWriter().write(""+str.toString()+""); 
		}
		catch(Exception e){
			e.printStackTrace();
		}finally{
			
			connection.close();
		}
		return null;
	}
	
	public String modpayment() throws Exception{
		Connection connection = null;
		try{
			connection = Connection_provider.getconnection();
			AccountsDAO accountsDAO = new JDBCAccountsDAO(connection);
			ChargesAccountProcessingDAO chargesAccountProcessingDAO = new JDBCChargeAccountProcesDAO(connection);
			
			ArrayList<Accounts> transactionlist = (ArrayList<Accounts>)session.getAttribute("paymnttransactionList");
			
			for(Accounts accounts : transactionlist){
				
				String id = request.getParameter("recpayid"+accounts.getId());
				String amount = request.getParameter("pymentamt"+accounts.getId());
				
				/*int upd = accountsDAO.updateRecPayment(id,amount);*/
				  String paymode= request.getParameter("paymentmode"+accounts.getId());
				    int upd = accountsDAO.updateRecPayment(id,amount,paymode);
				    
				    
				    //update ledger payment mode
				    int invoiceid = accountsDAO.getpaymentInvoiceID(id);
				    String itype = accountsDAO.getInvoiceTypeId(invoiceid);
					String serviceid = itype;
					String ledgerid = chargesAccountProcessingDAO.getledgerID(serviceid, paymode,
							"0");
					
					int u = chargesAccountProcessingDAO.updateLedgerPaymentMode(ledgerid,paymode,id);
				
			}
			
		}catch (Exception e) {
			// TODO: handle exception
		}finally{
			
			connection.close();
		}
		
		return execute();
	}
	
/*	public String transactions(){
		if(!verifyLogin(request)){
			return "login";
		}

		
		String invoiceid = request.getParameter("invoiceid");
		String clientId = request.getParameter("clientId");
		String payby = request.getParameter("payby");
		
		session.setAttribute("sessionselectedclientid", clientId);
		
		if(clientId.equals("")){
			clientId = (String)session.getAttribute("clientId");
		}
		
		Connection connection = null;
		try{
			connection = Connection_provider.getconnection();
			AccountsDAO accountsDAO = new JDBCAccountsDAO(connection);
			ChargesAccountProcessingDAO chargesAccountProcessingDAO = new JDBCChargeAccountProcesDAO(connection);
			
			Accounts clientDetails = chargesAccountProcessingDAO.getClientDetails(clientId);
			ArrayList<Accounts>transactionList = chargesAccountProcessingDAO.gettransactionList(invoiceid);
			
			double discount = accountsDAO.getDiscount(Integer.parseInt(invoiceid));
			
			StringBuffer str = new StringBuffer();
			int count =1;
			str.append("<b>Invoice No: </b>"+invoiceid+"  ");
			str.append("</br>");
			str.append("<table class='table-bordered table-hover table-condensed width-full'> ");
			
			str.append("<thead>");
			str.append("<tr>");
			str.append("<th>Sr.No.</th> ");
			str.append("<th>Date</th> ");
			str.append("<th>Payment Mode</th> ");
			str.append("<th>Charge</th> ");
			str.append("<th>View Payment</th> ");
			str.append("</tr>");
			str.append("</thead>");
			
			str.append("<tbody>");
			for(Accounts accounts:transactionList){
				str.append("<input type='hidden' name='recpayid"+accounts.getId()+"' id='recpayid"+accounts.getId()+"' value='"+accounts.getId()+"'>");
			str.append("<tr>");
			
			str.append("<td>"+count+"</td>");
			str.append("<td>"+accounts.getDate()+"</td>");
			str.append("<td>"+accounts.getPaymentmode()+"</td>");
			if(loginInfo.getUserType()==2 || loginInfo.getAcaccess().equals("1")){
				str.append("<td><input type='number'  class='form-control' name='pymentamt"+accounts.getId()+"' id='pymentamt"+accounts.getId()+"' value='"+accounts.getAmountx()+"'></td>");
			}else{
				str.append("<td>"+accounts.getAmountx()+"</td>");
			}
			
			//str.append("<td><a href = 'createReportProcessingAccount?clientId="+clientId+"&invoiceId="+invoiceid+"&amount="+accounts.getAmount()+"&payby="+payby+"&id="+accounts.getId()+"&crdAmount="+accounts.getCreditAmount()+"'> View Report </a></td>");
			str.append("<td><a href = 'printinvoicepaymentCharges?invoiceid="+invoiceid+"&action=viewpayment&discount="+discount+"&payby="+payby+"&amount="+accounts.getAmount()+"&pid="+accounts.getId()+"'> View Receipt </a></td>");
			str.append("</tr>");
			count++;
			}
			
			if(transactionList.size() == 0 || transactionList == null){
				
				
				str.append("<tr>");
				str.append("<td colspan='4'>No Transaction Done!!</td> ");
				
				str.append("</tr>");
			}
			str.append("</tbody>");
			str.append("</table>");
			
			session.setAttribute("paymnttransactionList", transactionList);
			
			response.setContentType("text/html");
			response.setHeader("Cache-Control", "no-cache");
			
			response.getWriter().write(""+str.toString()+""); 
			
			
			
			
		}catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}
	*/
	 public String transactions() throws Exception{
		  if(!verifyLogin(request)){
		   return "login";
		  }

		  
		  String invoiceid = request.getParameter("invoiceid");
		  String clientId = request.getParameter("clientId");
		  String payby = request.getParameter("payby");
		  
		  session.setAttribute("sessionselectedclientid", clientId);
		  
		  if(clientId.equals("")){
		   clientId = (String)session.getAttribute("clientId");
		  }
		  
		  Connection connection = null;
		  try{
		   connection = Connection_provider.getconnection();
		   AccountsDAO accountsDAO = new JDBCAccountsDAO(connection);
		   ChargesAccountProcessingDAO chargesAccountProcessingDAO = new JDBCChargeAccountProcesDAO(connection);
		   
		   Accounts clientDetails = chargesAccountProcessingDAO.getClientDetails(clientId);
		   ArrayList<Accounts>transactionList = chargesAccountProcessingDAO.gettransactionList(invoiceid);
		   
		   double discount = accountsDAO.getDiscount(Integer.parseInt(invoiceid));
		   int seqno=accountsDAO.getIpdOpdSeqNo(Integer.parseInt(invoiceid));
		   StringBuffer str = new StringBuffer();
		   int count =1;
		   str.append("<b>Invoice No: </b>"+seqno+"  ");
		   str.append("</br>");
		   str.append("<table class='table-bordered table-hover table-condensed width-full'> ");
		   
		   str.append("<thead>");
		   str.append("<tr>");
		   str.append("<th>Sr.No.</th> ");
		   str.append("<th>Date</th> ");
		   str.append("<th>Payment Mode</th> ");
		   str.append("<th>Charge</th> ");
		   str.append("<th>View Payment</th> ");
		   str.append("</tr>");
		   str.append("</thead>");
		   
		   str.append("<tbody>");
		   for(Accounts accounts:transactionList){
		    str.append("<input type='hidden' name='recpayid"+accounts.getId()+"' id='recpayid"+accounts.getId()+"' value='"+accounts.getId()+"'>");
		   str.append("<tr>");
		   
		   str.append("<td>"+count+"</td>");
		   str.append("<td>"+accounts.getDate()+"</td>");
		   
		   str.append("<td>");
		   str.append("<select name='paymentmode"+accounts.getId()+"' id='paymentmode"+accounts.getId()+"' class='form-control'> ");
		     if(DateTimeUtils.isNull(accounts.getPaymentmode()).equals("Cash")){
		      str.append("<option selected='selected' value='Cash'>Cash</option>");
		      str.append("<option value='NEFT'>NEFT</option>"); 
		      str.append("<option  value='Cheque'>Cheque</option>");
		      str.append("<option  value='D/Card'>D/Card</option>");
		      str.append("<option  value='UPI'>UPI</option>");
		      
		     } else if(DateTimeUtils.isNull(accounts.getPaymentmode()).equals("NEFT")){
		      str.append("<option selected='selected' value='NEFT'>NEFT</option>"); 
		      str.append("<option  value='Cash'>Cash</option>");
		      str.append("<option  value='Cheque'>Cheque</option>");
		      str.append("<option  value='D/Card'>D/Card</option>");
		      str.append("<option  value='UPI'>UPI</option>");
		      
		     }else if(DateTimeUtils.isNull(accounts.getPaymentmode()).equals("Cheque")) {
		         str.append("<option selected='selected' value='Cheque'>Cheque</option>");
		         str.append("<option  value='Cash'>Cash</option>");
		         str.append("<option  value='NEFT'>NEFT</option>");
		         str.append("<option  value='D/Card'>D/Card</option>");
		         str.append("<option  value='UPI'>UPI</option>");
		         
		     }else if(DateTimeUtils.isNull(accounts.getPaymentmode()).equals("D/Card")){
		      str.append("<option selected='selected' value='D/Card'>D/Card</option>");
		         str.append("<option  value='Cash'>Cash</option>");
		         str.append("<option  value='NEFT'>NEFT</option>");
		         str.append("<option  value='Cheque'>Cheque</option>");
		         str.append("<option  value='UPI'>UPI</option>");
		         
		     }else if(DateTimeUtils.isNull(accounts.getPaymentmode()).equals("UPI")){
			    	 str.append("<option selected='selected' value='UPI'>UPI</option>");
			    	 str.append("<option  value='D/Card'>D/Card</option>");
			         str.append("<option  value='Cash'>Cash</option>");
			         str.append("<option  value='NEFT'>NEFT</option>");
			         str.append("<option  value='Cheque'>Cheque</option>");
			        
			     }
		    
		   str.append("</select>");
		   str.append("</td>");
		   //str.append("<td>"+accounts.getPaymentmode()+"</td>");
		   if(loginInfo.getUserType()==2 || loginInfo.getAcaccess().equals("1")){
		    str.append("<td><input type='number' readonly='readonly'  class='form-control' name='pymentamt"+accounts.getId()+"' id='pymentamt"+accounts.getId()+"' value='"+accounts.getAmountx()+"'></td>");
		   }else{
		    str.append("<td>"+accounts.getAmountx()+"</td>");
		   }
		   
		   //str.append("<td><a href = 'createReportProcessingAccount?clientId="+clientId+"&invoiceId="+invoiceid+"&amount="+accounts.getAmount()+"&payby="+payby+"&id="+accounts.getId()+"&crdAmount="+accounts.getCreditAmount()+"'> View Report </a></td>");
		   str.append("<td><a href = 'printinvoicepaymentCharges?invoiceid="+invoiceid+"&action=viewpayment&discount="+discount+"&payby="+payby+"&amount="+accounts.getAmount()+"&pid="+accounts.getId()+"'> View Receipt </a></td>");
		   str.append("</tr>");
		   count++;
		   }
		   
		   if(transactionList.size() == 0 || transactionList == null){
		    
		    
		    str.append("<tr>");
		    str.append("<td colspan='4'>No Transaction Done!!</td> ");
		    
		    str.append("</tr>");
		   }
		   str.append("</tbody>");
		   str.append("</table>");
		   
		   session.setAttribute("paymnttransactionList", transactionList);
		   
		   response.setContentType("text/html");
		   response.setHeader("Cache-Control", "no-cache");
		   
		   response.getWriter().write(""+str.toString()+""); 
		   
		   
		   
		   
		  }catch(Exception e){
		   e.printStackTrace();
		  }finally{
				
				connection.close();
			}
		  return null;
		 }
	public String showSubAssessment() throws Exception{
		if(!verifyLogin(request)){
			return "login";
		}
		
		int invoiceid = Integer.parseInt(request.getParameter("invoiceId"));
		
		Connection connection  = null;
		
		try{
			
			connection  = Connection_provider.getconnection();
			ChargesAccountProcessingDAO chargesAccountProcessingDAO = new JDBCChargeAccountProcesDAO(connection);
			ArrayList<Accounts>assesmentList = chargesAccountProcessingDAO.getChildAssesmentList(invoiceid);
			StringBuffer str = new StringBuffer();
			ArrayList<Bed> wardlist=new ArrayList<Bed>();
			BedDao bedDao = new  JDBCBedDao(connection);
			wardlist=bedDao.getAllWardList(pagination=null);
			AppointmentDAO appointmentDAO= new JDBCAppointmentDAO(connection);
			ArrayList<Master>masterChageTypeList = appointmentDAO.getmasterChageTypeList(loginInfo);
			accountsForm.setWardlist(wardlist);
		
			str.append("<table class='table table-hover table-condensed table-bordered' > ");
			
			str.append("<thead>");
			str.append("<tr>");
			str.append("<th colspan ='3'></th> ");
			str.append("<th colspan ='2'>Charge Type</th> ");
			str.append("<th>Description</th> ");
			str.append("<th>Unit Price</th> ");
			str.append("<th>Qty.</th> ");
			str.append("<th>Cost</th> ");
			str.append("<th colspan='4'></th> ");
			str.append("<th>Ward</th>");
			str.append("<th>Charge Type</th>");
			str.append("</tr>");
			str.append("</thead>");
			str.append("<tbody>");
			for(Accounts accounts:assesmentList){
			str.append("<tr style='background-color: rgb(240, 204, 252);'>");
			
			str.append("<td  colspan ='3'></td>");
			
			int isAdditionalCharge = chargesAccountProcessingDAO.checkIsAdditionalCharge(invoiceid);
			
			
				str.append("<td  colspan ='2'>"+accounts.getChargeType()+" ("+accounts.getAppointmentType()+")</td>");
				if(isAdditionalCharge==0){
					str.append("<td id='chargeid'>"+accounts.getMasterchargetype()+"<br>Practitioner:  "+accounts.getPractitionerName()+ "</td>");
				}else{
					str.append("<td id='chargeid'>"+accounts.getMasterchargetype()+"</td>");
				}
				
					
			str.append("<td>"+accounts.getCharges()+"</td>");
			str.append("<td>"+accounts.getQuantity()+"</td>");
			double chargeTotal = Double.parseDouble(accounts.getCharges()) * accounts.getQuantity();
			str.append("<td>"+DateTimeUtils.changeFormat(chargeTotal)+"</td>");
			str.append("<td colspan='4'></td> ");
			str.append("<td>");
			if(!accounts.getWard().equals("")){
				if(loginInfo.getUserType()==2 || loginInfo.getAcaccess().equals("1")){
				str.append("<select class='form-control' onchange = 'changeWardininvoiceAss("+accounts.getId()+",this.value)'>");
				str.append("<option value='0'>All Ward</option>");
				for(Bed b:wardlist){
					if(accounts.getWard().equals(String.valueOf(b.getId()))){
						str.append("<option selected='selected' value="+b.getId()+">"+b.getWardname()+"</option>");
					}else{
						str.append("<option  value="+b.getId()+">"+b.getWardname()+"</option>");
					}
				}
				str.append("</select>");
			}
			}
			str.append("</td>");
			str.append("<td>");
		
			if(loginInfo.getUserType()==2 || loginInfo.getAcaccess().equals("1")){
				str.append("<select class='form-control' onchange='changeApptChgargeMaster("+accounts.getId()+",this.value)'>");
				for(Master master:masterChageTypeList){
					if(accounts.getMasterchargetype().equals(master.getName())){
						str.append("<option selected='selected' value='"+master.getName()+"'>"+master.getName()+"</option>");
					}else{
						str.append("<option  value='"+master.getName()+"'>"+master.getName()+"</option>");
					}
					
				}
				str.append("</select>");
			}
			str.append("</td>");
			str.append("</tr>");
			
			}
			str.append("</tbody>");
			str.append("</table>");
			response.setContentType("text/html");
			response.setHeader("Cache-Control", "no-cache");
			
			response.getWriter().write(""+str.toString()+""); 
		}
		catch(Exception e){
			e.printStackTrace();
		}finally{
			
			connection.close();
		}
		return null;
	}
	
	public String payment() throws SQLException{
		if(!verifyLogin(request)){
			return "login";
		}
		Connection connection  = null;
		try{
			
			connection  = Connection_provider.getconnection();
			AccountsDAO accountsDAO = new JDBCAccountsDAO(connection);
			DateTimeUtils dateTimeUtils = new DateTimeUtils();
			
			String payby = request.getParameter("payby");
			String numberOfChages = request.getParameter("numberOfChages");
			String creditCharge = request.getParameter("creditCharge");
			String debitAmt = request.getParameter("debitamt");
			String creditAmt = request.getParameter("creditamt");
			String balance = request.getParameter("balance");
			String clientId = request.getParameter("clientId");
			String discount1 = request.getParameter("discount");
			
			String fromDate=accountsForm.getInvoiceDate();
	    	
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
	    	accountsForm.setInvoiceDate(DateTimeUtils.getCommencingDate1(fromDate));
			int invoiceid = Integer.parseInt(request.getParameter("invoiceid"));
			double totalPaymentReceived = accountsDAO.getTotalPaymentReceived(invoiceid);
			
			ChargesAccountProcessingDAO chargesAccountProcessingDAO = new JDBCChargeAccountProcesDAO(connection);
			ArrayList<Accounts>assesmentList = chargesAccountProcessingDAO.getAssesmentList(invoiceid);

			//ArrayList<Accounts>assesmentList = accountsDAO.getAssesmentList(invoiceid,Integer.parseInt(payby));
			double discount = chargesAccountProcessingDAO.getDiscount(invoiceid);
			double totalAmount = 0;
			totalAmount = Double.parseDouble(balance);
		/*	if(discount>0){
				if(totalPaymentReceived==0){
					double r = (totalAmount*discount)/100;
					totalAmount = totalAmount - r;
				}
				
				
			}*/

			accountsForm.setAssesmentList(assesmentList);
			accountsForm.setCreditCharge(dateTimeUtils.changeFormat(Double.parseDouble(creditCharge)));
			accountsForm.setInvoiceid(invoiceid);
			accountsForm.setPayby(payby);
			accountsForm.setNumberOfChages(Integer.parseInt(numberOfChages));
			accountsForm.setCreditAmt(creditAmt);
			accountsForm.setDebitAmt(dateTimeUtils.changeFormat(Double.parseDouble(debitAmt)));
			accountsForm.setBalance(Double.parseDouble(balance));
			accountsForm.setClientId(clientId);
			
			//set discount
			accountsForm.setDiscount(dateTimeUtils.changeFormat(Double.parseDouble(discount1)));
			Accounts accountsdisc = accountsDAO.getAccDiscData(invoiceid);
			int disctype = Integer.parseInt(accountsdisc.getDisctype());
			double discamt = Double.parseDouble(accountsdisc.getDiscamt());
			accountsForm.setDisctype(accountsdisc.getDisctype());
			if(disctype==1){
				accountsForm.setDiscount(dateTimeUtils.changeFormat(Double.parseDouble(accountsdisc.getDiscamt())));
			}
			
			//Decimal Account
			
			accountsForm.setBalancex(dateTimeUtils.changeFormat(Double.parseDouble(Double.toString(totalAmount))));
			
			accountsForm.setNumberOfChages(assesmentList.size());
			
			
			ArrayList<Master>ledgerservicesList = accountsDAO.getLedgerServicesList(invoiceid);
			accountsForm.setLedgerservicesList(ledgerservicesList);
			
			//set pre-payment mode
			
			accountsForm.setCreditDebitCharge("0");
			boolean checkCreditAmount = accountsDAO.checkCreditAmount(accountsForm.getClientId());
			accountsForm.setBalanceAmt(checkCreditAmount);
			if(checkCreditAmount==true){
				double balanceAmmount = accountsDAO.getBlanceAmount(accountsForm.getClientId());
				String amount = DateTimeUtils.changeFormat(balanceAmmount);
				accountsForm.setBalanceAmount(amount);
			}else{
				accountsForm.setBalanceAmount("0.00");
			}
			
			boolean statusrequestdiscamt = accountsDAO.getRequestedDiscountStatus(invoiceid);
			double discountamt = accountsDAO.getRequestedDiscountAmount(invoiceid);
			accountsForm.setStatusrequestdiscamt(statusrequestdiscamt);
			accountsForm.setDiscountamt(discountamt);
			String notes = accountsDAO.getNotes(invoiceid);
			accountsForm.setNotes(notes);
		}catch (Exception e) {
			e.printStackTrace();
		}
		finally{
			connection.close();
		}
		
		return "payment";
	}
	
	
	//for pharmacy paymemt 
	
	public String paymentpharmacy() throws SQLException{
		if(!verifyLogin(request)){
			return "login";
		}
		Connection connection  = null;
		try{
			
			connection  = Connection_provider.getconnection();
			AccountsDAO accountsDAO = new JDBCAccountsDAO(connection);
			PharmacyDAO pharmacyDAO=new JDBCPharmacyDAO(connection);
			DateTimeUtils dateTimeUtils = new DateTimeUtils();
			
			String payby = request.getParameter("payby");
			String numberOfChages = request.getParameter("numberOfChages");
			String creditCharge = request.getParameter("creditCharge");
			String debitAmt = request.getParameter("debitamt");
			String creditAmt = request.getParameter("creditamt");
			String balance = request.getParameter("balance");
			String clientId = request.getParameter("clientId");
			String discount1 = request.getParameter("discount");
			
			int invoiceid = Integer.parseInt(request.getParameter("invoiceid"));
			double totalPaymentReceived = pharmacyDAO.getTotalPaymentReceived(invoiceid);
			
			ChargesAccountProcessingDAO chargesAccountProcessingDAO = new JDBCChargeAccountProcesDAO(connection);
			
			
			ArrayList<Accounts>assesmentList = pharmacyDAO.getAssesmentList(invoiceid);

			//ArrayList<Accounts>assesmentList = accountsDAO.getAssesmentList(invoiceid,Integer.parseInt(payby));
			double discount = pharmacyDAO.getDiscount(invoiceid);
			double totalAmount = 0;
			totalAmount = Double.parseDouble(balance);
		/*	if(discount>0){
				if(totalPaymentReceived==0){
					double r = (totalAmount*discount)/100;
					totalAmount = totalAmount - r;
				}
				
				
			}*/

			accountsForm.setAssesmentList(assesmentList);
			accountsForm.setCreditCharge(dateTimeUtils.changeFormat(Double.parseDouble(creditCharge)));
			accountsForm.setInvoiceid(invoiceid);
			accountsForm.setPayby(payby);
			accountsForm.setNumberOfChages(Integer.parseInt(numberOfChages));
			accountsForm.setCreditAmt(creditAmt);
			accountsForm.setDebitAmt(dateTimeUtils.changeFormat(Double.parseDouble(debitAmt)));
			accountsForm.setBalance(Double.parseDouble(balance));
			accountsForm.setClientId(clientId);
			accountsForm.setDiscount(dateTimeUtils.changeFormat(Double.parseDouble(discount1)));
			
			//Decimal Account
			
			accountsForm.setBalancex(dateTimeUtils.changeFormat(Double.parseDouble(Double.toString(totalAmount))));
			
			accountsForm.setNumberOfChages(assesmentList.size());
			
			
			//set pre-payment mode
			
			accountsForm.setCreditDebitCharge("0");
			boolean checkCreditAmount = accountsDAO.checkCreditAmount(accountsForm.getClientId());
			accountsForm.setBalanceAmt(checkCreditAmount);
			if(checkCreditAmount==true){
				double balanceAmmount = accountsDAO.getBlanceAmount(accountsForm.getClientId());
				String amount = DateTimeUtils.changeFormat(balanceAmmount);
				accountsForm.setBalanceAmount(amount);
			}else{
				accountsForm.setBalanceAmount("0.00");
			}
			
			
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		finally{
			connection.close();
		}
		
		return "paymentpharmacy";
	}

	
	
	
	
	public String payAmt() throws SQLException{
		if(!verifyLogin(request)){
			return "login";
		}
		Connection connection  = null;
		try{
			
			connection  = Connection_provider.getconnection();
			ChargesAccountProcessingDAO chargesAccountProcessingDAO = new JDBCChargeAccountProcesDAO(connection);
			AccountsDAO accountsDAO = new JDBCAccountsDAO(connection);
			CashDeskDAO cashDeskDAO = new JDBCCashDeskDAO(connection);
			AccountLogDAO accountLogDAO = new JDBCAccountLogDAO(connection);
			String date_time = DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone());
			
		
			String discamt=request.getParameter("discount");
			String invoiceid = request.getParameter("invoiceid");
			String payAmount = request.getParameter("amount");
			String howpaid = request.getParameter("howpaid");
			String invoiceDate = request.getParameter("invoiceDate");
			String clientId = request.getParameter("clientId");
			String ccdamt = request.getParameter("ccdamt");
			String chequeno=request.getParameter("chequeno");
			String bankname= request.getParameter("bankname");
			double payAmount1 = Double.parseDouble(payAmount);	
			int paymentid = 0;
			
			if(clientId.equals("")){
				clientId = (String)session.getAttribute("clientId");
			}
			
			String payby = request.getParameter("payby");
			
			session.setAttribute("chargesInvoiceid", Integer.parseInt(invoiceid));
			session.setAttribute("clientId", clientId);
			session.setAttribute("payAmount",payAmount);
			session.setAttribute("payby", payby);
//			session.setAttribute("previewstatus", "1");
			session.setAttribute("clientwhopaymode", accountsForm.getPayby());
			if(howpaid.equals("ccd")){
				int clientid = Integer.parseInt(clientId);
				int tResult = cashDeskDAO.saveCashDeskTransaction(clientid,payAmount1);
				}
			
			
			boolean ispayment = chargesAccountProcessingDAO.checkIsPaymentExist(invoiceid);
			if(!ispayment){
				if(!accountsForm.getDiscount().equals("")){
					//int res = chargesAccountProcessingDAO.updateDiscount(invoiceid,accountsForm.getDiscount());
					String userid = loginInfo.getUserId();
					String datetime = DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone());
					int p = accountsDAO.updatePercentageAmount(invoiceid,accountsForm.getDiscount(),accountsForm.getDisctype(),userid,datetime);
					
					if(accountsForm.getDiscount().equals("0.00") || accountsForm.getDiscount().equals("0,0")){
						accountsForm.setDiscount("0");
					}
					//session.setAttribute("discount", Integer.parseInt(accountsForm.getDiscount()));
					session.setAttribute("discount", Double.parseDouble(accountsForm.getDiscount()));
					
					double discdebit = Double.parseDouble(accountsForm.getDiscount());
					if(accountsForm.getDisctype().equals("0")){
						discdebit = (payAmount1 * Double.parseDouble(accountsForm.getDiscount())) /100;
					}
					
					//discount ledger
					String serviceid = chargesAccountProcessingDAO.getLedgrServiceIds("Discount");
					String ledgerid = chargesAccountProcessingDAO.getledgerID(serviceid,"0","0");
					
					double lbal = chargesAccountProcessingDAO.getLedgerBalance(ledgerid);
					lbal = lbal + discdebit;
					String credit = ""+discdebit+"";
					String debit = "0";
					String product = invoiceid;
					String partyid = clientId;
					String commencing = DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone());
					int saveledger = chargesAccountProcessingDAO.saveLedger(partyid,product,debit,credit,lbal,ledgerid,commencing,invoiceid,0,"0","0","0","0","0",0,0,"0");
					
					
					//second effect
					 credit = "0";
					 debit = ""+discdebit+"";
					 product = invoiceid;
					 partyid = clientId;
					 commencing = DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone());
					 saveledger = chargesAccountProcessingDAO.saveLedger(partyid,product,debit,credit,lbal,ledgerid,commencing,invoiceid,0,"0","0","0","0","0",0,0,"0");
					
				}
			}
			
			
			//save credit amount
			int creditinvoiceid = 0;
			session.setAttribute("creditAmount", 0.0);
			if(!accountsForm.getCrdAmount().equals("")){
				session.setAttribute("creditAmount", Double.parseDouble(accountsForm.getCrdAmount()));
				
				connection = Connection_provider.getconnection();
				AdditionalDAO additionalDAO = new JDBCAdditionalDAO(connection);
				
				double balance = additionalDAO.getCreditTotal(accountsForm.getClientId());
				balance = balance + Double.parseDouble(accountsForm.getCrdAmount());
				
				//reset invoice
				int resetinv = accountsDAO.getMaxResetInv();
				int resetcreditinv = accountsDAO.getMaxResetCreditInv();
				int rinv = 0;
				if(resetinv>resetcreditinv){
					rinv = resetinv + 1;
				}else{
					rinv = resetcreditinv + 1;
				}
				creditinvoiceid = additionalDAO.saveCreditRecord(accountsForm.getClientId(), "", DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone()), accountsForm.getCreditNote(),accountsForm.getPayby(),accountsForm.getCrdAmount(),accountsForm.getHowpaid(),balance,rinv,loginInfo.getUserId(),0);
				
				int save = additionalDAO.saveCreditAssessmentRecord(accountsForm.getClientId(), "", "", DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone()), creditinvoiceid,accountsForm.getCrdAmount(),accountsForm.getAdvref());
				
				additionalDAO.createSeqnogenProccessForAdvAndRef(accountsForm.getInvcetype(), ""+creditinvoiceid, accountsForm.getHowpaid(),"");
			}
					
			
			int thirdPartyID = accountsDAO.getThirdPartyID(clientId);
			AdditionalDAO additionalDAO = new JDBCAdditionalDAO(connection);
			
			
			 if(!accountsForm.getHdnmorehowpaid().equals("0")){
				 double cbalance = additionalDAO.getCreditTotal(clientId);
				 payAmount = ""+cbalance+"";
				 accountsForm.setAmount(payAmount);
			 }
			 paymentid = chargesAccountProcessingDAO.savePayment(invoiceid,payAmount,howpaid,invoiceDate,clientId,thirdPartyID,accountsForm.getPaymentNote(),DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone()),creditinvoiceid,chequeno,loginInfo.getUserId(),bankname,discamt);
			 
			 
			 //get appointmentid
			 int appointmentid = accountsDAO.getPaymentAppoinetmentId(invoiceid);
			 int upstatus = accountsDAO.updateOpdPaymentStatus(appointmentid,Integer.parseInt(invoiceid));
			
			
			//logdata
			int result1 = accountLogDAO.saveChargesPayment(clientId,Integer.parseInt(invoiceid),payAmount,howpaid,thirdPartyID,invoiceDate,DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone()),accountsForm.getPaymentNote());
			
			 //add more payment
			 if(!accountsForm.getHdnmorehowpaid().equals("0")){
				 int morepaymentid = chargesAccountProcessingDAO.savePayment(invoiceid,accountsForm.getHdnmorepaudamount(),accountsForm.getHdnmorehowpaid(),invoiceDate,clientId,thirdPartyID,accountsForm.getPaymentNote(),DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone()),creditinvoiceid,chequeno,loginInfo.getUserId(),bankname, discamt);
				 
				 String itype = accountsDAO.getInvoiceTypeId(Integer.parseInt(invoiceid));
				 
					//update invoice type payment autono
				 if(itype.equals("2")){
					 int maxno = additionalDAO.getMaxAdvno(paymentid);
						String invoicetype = "IPD";
						int paymentids = 0;
						boolean paymentid_exist=additionalDAO.PaymentidPresentOrNot(Integer.parseInt(invoiceid));
						if(!paymentid_exist) {
						 int u = additionalDAO.updateAdvMaxno(Integer.parseInt(invoiceid),maxno,invoicetype,paymentid);
						} 
				 }else{
						int maxno = accountsDAO.getMaxInvoiceTypePaymentNo(morepaymentid,itype);
						int u = accountsDAO.updateInvoicetypePaymentNo(morepaymentid,maxno,itype);
					 
				 }
				
				
					//String serviceid = chargesAccountProcessingDAO.getLedgrServiceIds(master.getName());
					String serviceid = itype;
				 String ledgerid = chargesAccountProcessingDAO.getledgerID(serviceid,accountsForm.getHdnmorehowpaid(),accountsForm.getHdnbnkname());
				 
				 double lbal = chargesAccountProcessingDAO.getLedgerBalance(ledgerid);
					lbal = lbal + Double.parseDouble(accountsForm.getHdnmorepaudamount());
					String credit = "0";
					String ldebit = accountsForm.getHdnmorepaudamount();
					String product = ""+invoiceid+"";
					String partyid = accountsForm.getClientId();
					String lcommencing = DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone());
					int saveledger = chargesAccountProcessingDAO.saveLedger(partyid,product,ldebit,credit,lbal,ledgerid,lcommencing,""+invoiceid+"",morepaymentid,"0","0","0","0","0",0,0,"0");
					
					//second effect
					lbal = 0;
					 credit = accountsForm.getClientId();
					 ldebit = "0";
					 product = ""+invoiceid+"";
					 partyid = accountsForm.getClientId();
					 lcommencing = DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone());
					 saveledger = chargesAccountProcessingDAO.saveLedger(partyid,product,ldebit,credit,lbal,ledgerid,lcommencing,""+invoiceid+"",morepaymentid,"0","0","0","0","0",0,0,"0");
			 }
			 
			//ledger services
			 ExpenManagementDAO expenManagementDAO = new JDBCExpenceManagementDAO(connection);
				String ledgerservicestr = accountsForm.getLedgerservicestr();
//				System.out.println(ledgerservicestr);
				String ledgertemp[] = ledgerservicestr.split(",");
				ArrayList<Master>ledgerservicesList = accountsDAO.getLedgerServicesList(Integer.parseInt(invoiceid));
				
				int l = 1;
				if(Integer.parseInt(invoiceid)>0){
					String itype = accountsDAO.getInvoiceTypeId(Integer.parseInt(invoiceid));
						//String serviceid = chargesAccountProcessingDAO.getLedgrServiceIds(master.getName());
					String serviceid = itype;
						String ledgerid = chargesAccountProcessingDAO.getledgerID(serviceid,howpaid,accountsForm.getBnkname());
						
						double lbal = chargesAccountProcessingDAO.getLedgerBalance(ledgerid);
						lbal = lbal + Double.parseDouble(payAmount);
						String credit = "0";
						String debit = payAmount;
						String product = "xxxxx";
						String partyid = clientId;
						String commencing = DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone());
						int saveledger = chargesAccountProcessingDAO.saveLedger(partyid,product,debit,credit,lbal,ledgerid,commencing,invoiceid,paymentid,"0","0","0","0","0",0,0,"0");
						
						
						if("2".equals(itype)){
							expenManagementDAO.savePatientToLedgerMaster(""+accountsForm.getClientId());
							int clientLedgerId=expenManagementDAO.getLedgerIdOfClient(""+accountsForm.getClientId());
							ledgerid=""+clientLedgerId;
						}else{
							
						}
						//second effect
						lbal = 0;
						 credit = payAmount;
						 debit = "0";
						 product = "xxxxx";
						 partyid = clientId;
						 commencing = DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone());
						 saveledger = chargesAccountProcessingDAO.saveLedger(partyid,product,debit,credit,lbal,ledgerid,commencing,invoiceid,paymentid,"0","0","0","0","0",0,0,"0");
				}
				
				//update invoice type payment autono
				String itype = accountsDAO.getInvoiceTypeId(Integer.parseInt(invoiceid));
				 if(itype.equals("2")){
					 int maxno = additionalDAO.getMaxAdvno(paymentid);
						String invoicetype = "IPD";
						int paymentids = 0;
						boolean paymentid_exist=additionalDAO.PaymentidPresentOrNot(Integer.parseInt(invoiceid));
						//for ayushman payment insert repeat thats why 
						if(!paymentid_exist) {
							int u = additionalDAO.updateAdvMaxno(Integer.parseInt(invoiceid),maxno,invoicetype,paymentid);
						}
						//int u = additionalDAO.updateAdvMaxno(Integer.parseInt(invoiceid),maxno,invoicetype,paymentid);
						 
				 }else{
					 int maxno = accountsDAO.getMaxInvoiceTypePaymentNo(paymentid,itype);
						int u = accountsDAO.updateInvoicetypePaymentNo(paymentid,maxno,itype);
					
				 }
				
			//Sms to Patient
			  ClientDAO clientDAO= new JDBCClientDAO(connection);
			  Client client=clientDAO.getClientDetails(clientId);
			  String clientname= client.getTitle()+" "+client.getFirstName()+" "+client.getLastName();
			  ClinicDAO clinicDAO=new JDBCClinicDAO(connection);
			  Clinic clinic=clinicDAO.getCliniclistDetails(loginInfo.getClinicid());
			  boolean isPaymentSms= clinicDAO.isPaymentSMSActive(loginInfo.getClinicid());
			  if((itype.equals("1")&&loginInfo.isOpd_payamnt_sms())||(itype.equals("2")&&loginInfo.isIpd_payamnt_sms())||(itype.equals("3")||itype.equals("6")||itype.equals(""))||(itype.equals("5")&&loginInfo.isAdv_payamnt_sms())){
				  UserProfileDAO userProfileDAO = new JDBCUserProfileDAO(connection);
				  String templateid = userProfileDAO.getSMSTemplateID("Payment");
				  String msg="Thanks "+clientname+" for payment of Rupees "+payAmount+" from- "+clinic.getClinicName()+"";
				  SmsService service= new SmsService();
				  service.sendSms(msg, client.getMobNo(), loginInfo, new EmailLetterLog(),templateid);
			  
			  }
			
			
			accountsForm.setHdnSelectedID(invoiceid);
			
			addActionMessage("Payment done successfully!!");
			//setFormData();
		
		
			
			//set debit ammount
			if(accountsForm.getHowpaid().equals(Constants.PREPYMENT)){
				
				
				
			//	String clientId  = accountsForm.getClientId();
				String date = DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone());
				
				
				String type = "Pre_Payment";
				String creditnote = "";
				String charge = accountsForm.getAmount();
				String payBuy = accountsForm.getPayby();
				String paymode = accountsForm.getHowpaid();
				
				double balance = additionalDAO.getCreditTotal(clientId);
				balance = balance - Double.parseDouble(charge);
				
				//reset invoice
				int resetinv = accountsDAO.getMaxResetInv();
				int resetcreditinv = accountsDAO.getMaxResetCreditInv();
				int rinv = 0;
				if(resetinv>resetcreditinv){
					rinv = resetinv + 1;
				}else{
					rinv = resetcreditinv + 1;
				}
				int crinvoiceid = additionalDAO.saveDebitRecord(clientId, type, date, creditnote,payBuy,charge,paymode,balance,accountsForm.getAdvref(),rinv,loginInfo.getUserId(),null,null,"",accountsForm.getInvcetype(),loginInfo,null);
				int prepaymentid = accountsDAO.getPrePaymentID(clientId);
				int upd = accountsDAO.updatePrePaymentID(paymentid,prepaymentid);
				
				int save = additionalDAO.saveCreditAssessmentRecord(clientId, "", type, date, crinvoiceid,charge,accountsForm.getAdvref());
				
				additionalDAO.createSeqnogenProccessForAdvAndRef(accountsForm.getInvcetype(), ""+crinvoiceid, paymode,"");
				
				//refund against invoice
				if(accountsForm.getClraradv().equals("1")){
				 resetinv = accountsDAO.getMaxResetInv();
				 resetcreditinv = accountsDAO.getMaxResetCreditInv();
				 rinv = 0;
				if(resetinv>resetcreditinv){
					rinv = resetinv + 1;
				}else{
					rinv = resetcreditinv + 1;
				}
				double refbalance = additionalDAO.getCreditTotal(clientId);
				 balance = 0;
				 charge = Double.toString(refbalance);
				 crinvoiceid = additionalDAO.saveDebitRecord(clientId, type, date, creditnote,payBuy,charge,paymode,balance,accountsForm.getAdvref(),rinv,loginInfo.getUserId(),null,null,invoiceid,accountsForm.getInvcetype(),loginInfo, accountsForm.getRefpayMode());
				 save = additionalDAO.saveCreditAssessmentRecord(clientId, "", type, date, crinvoiceid,charge,accountsForm.getAdvref());
				 additionalDAO.createSeqnogenProccessForAdvAndRef(accountsForm.getInvcetype(), ""+crinvoiceid, paymode,invoiceid);
				  upd = additionalDAO.updateRefundInvoice(invoiceid,crinvoiceid);
				  int maxno = additionalDAO.getMaxAdvno(crinvoiceid);
					String invoicetype = "Refund";
					int paymentids = 0;
					boolean checkceinvoieid=additionalDAO.checkcrinvoiceidexist(crinvoiceid);
					if(!checkceinvoieid) {
					   int u = additionalDAO.updateAdvMaxno(crinvoiceid,maxno,invoicetype,paymentids);
					}
					//Refund Ledger
					if(crinvoiceid>0){
						String serviceid = chargesAccountProcessingDAO.getLedgrServiceIds("Refund");
						String ledgerid = chargesAccountProcessingDAO.getledgerID(serviceid,paymode,accountsForm.getBnkname());
						
						double lbal = chargesAccountProcessingDAO.getLedgerBalance(ledgerid);
						lbal = lbal + Double.parseDouble(charge);
						String credit = charge;
						String ldebit = "0";
						String product = "Refund";
						String partyid = clientId;
						String lcommencing = DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone());
						int saveledger = chargesAccountProcessingDAO.saveLedger(partyid,product,ldebit,credit,lbal,ledgerid,lcommencing,invoiceid,0,"0","0","0","0","0",0,0,"0");
						
						//second effect
						lbal = 0;
						 credit = "0";
						 ldebit = charge;
						 product = "Refund";
						 partyid = clientId;
						 lcommencing = DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone());
						 saveledger = chargesAccountProcessingDAO.saveLedger(partyid,product,ldebit,credit,lbal,ledgerid,lcommencing,invoiceid,0,"0","0","0","0","0",0,0,"0");
						
					}
				
				}
			}
			CommonOpdIpdReport commonOpdIpdReport=new CommonOpdIpdReport();
			commonOpdIpdReport.patientTranssection(accountsForm.getClientId(),date_time);
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			
			connection.close();
		}
		
		
		return "pay";
	}
	
public String print() throws Exception{
		
		Connection connection = null;
		try{
			
				
				connection = Connection_provider.getconnection();
				ChargesAccountProcessingDAO chargesAccountProcessingDAO = new JDBCChargeAccountProcesDAO(connection);
				ArrayList<Accounts>chargeProcessingList = new ArrayList<Accounts>();
			
			
			if(!verifyLogin(request)){
				return "login";
			}
			String clientId = accountsForm.getClientId();
			String client = accountsForm.getClient();
			String payby = accountsForm.getPayby();
			String fromDate = accountsForm.getFromDate();
			String toDate = accountsForm.getToDate();
			
			session.setAttribute("clientId", clientId);
			session.setAttribute("client", client);
			session.setAttribute("payby", payby);
			session.setAttribute("fromDate", fromDate);
			session.setAttribute("toDate", toDate);
			session.setAttribute("transactionType", accountsForm.getTransactionType());

			
			if(!fromDate.equals("")){
				String temp[]= fromDate.split("/");
				fromDate = temp[2]+"-"+temp[1]+"-"+temp[0];
			}
			if(!toDate.equals("")){
				String temp1[]= toDate.split("/");
				toDate = temp1[2]+"-"+temp1[1]+"-"+temp1[0];
			}
			
			
			
			
			String defaultVal = "All";
			if(payby.equals(defaultVal)){
				payby = "";
			}
			
			String action = "print";
			
			if(accountsForm.getTransactionType().equals(Constants.PENDING_PEYMENT)){
				chargeProcessingList = chargesAccountProcessingDAO.getChargesAccountProcessingPendingPrintList(clientId,payby,fromDate,toDate);
			}else if(accountsForm.getTransactionType().equals(Constants.PAID)){
				chargeProcessingList = chargesAccountProcessingDAO.getChargesAccountProcessingPendingPrintList(clientId,payby,fromDate,toDate);
			}else{
				chargeProcessingList = chargesAccountProcessingDAO.getChargesAccountProcessingPrintList(clientId,payby,fromDate,toDate);
			}
			
			
			accountsForm.setChargeProcessingList(chargeProcessingList);
			
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			
			connection.close();
		}
		
		return "printstatement";
	}


	public String printreport() throws Exception{
		if(!verifyLogin(request)){
			return "login";
		}
		int invoiceid = (Integer)session.getAttribute("invoiceid");
		String clientid = (String)session.getAttribute("clientId");
		double payAmount = (Double)session.getAttribute("payAmount");
		String payby = (String)session.getAttribute("payby");
		String id = accountsForm.getId();
		String status = "Print";
		
		ArrayList<Accounts>previewChargesList = new ArrayList<Accounts>();
		Vector<Accounts>assesmentList = new Vector<Accounts>();
		
		Connection connection = null;
		try{
			
			connection = Connection_provider.getconnection();
			AccountsDAO accountsDAO = new JDBCAccountsDAO(connection);
			int up = accountsDAO.updatePaymentDeliverStatus(id, status);
			ArrayList<Accounts>chargesInvoiceList = accountsDAO.getchargesInvoiceList(invoiceid);
			
		/*	for(Accounts accounts : chargesInvoiceList){
				
				Accounts acc = accountsDAO.getAppointmentDetailsl(Integer.toString(accounts.getInvoiceid()));
				previewChargesList = accountsDAO.getPreviewChargesList(acc,Integer.toString(accounts.getInvoiceid()));
				
				assesmentList.addAll(previewChargesList);
				
			}*/
			
			assesmentList = accountsDAO.getPreviewChargesList(invoiceid);
			
			int totalAmount = 0;
			for(Accounts totalAcc : assesmentList){
				totalAmount = totalAmount + Integer.parseInt(totalAcc.getCharges());
				
			}
			
			Client client = accountsDAO.getClientData(clientid);
			accountsForm.setClient(client.getTitle() + " " + client.getFirstName() + " " + client.getLastName());
			accountsForm.setAddress(client.getAddress());
			accountsForm.setDob(client.getDob());
			accountsForm.setClientId(clientid);
			accountsForm.setEmail(client.getEmail());
			accountsForm.setMobno(client.getMobNo());
			accountsForm.setPolicyNo(client.getPolicyNo());
			accountsForm.setPayby(payby);
			
			session.setAttribute("client",client.getTitle() + " " + client.getFirstName() + " " + client.getLastName() );
			session.setAttribute("address", client.getAddress());
			session.setAttribute("dob", client.getDob());
			session.setAttribute("policyNo", client.getPolicyNo());
			session.setAttribute("email", client.getEmail());
			
			
			accountsForm.setAssesmentList(assesmentList);
			accountsForm.setTotalAmount(totalAmount);
			
			accountsForm.setInvoiceid(invoiceid);
			
			accountsForm.setCreditAmt(Double.toString(payAmount));
			
			double credit = totalAmount - payAmount;
			if(payAmount >0){
				
				accountsForm.setDebitAmounnt(Double.toString(credit));
			}else{
				accountsForm.setDebitAmounnt(Integer.toString(totalAmount));
			}
			
		}catch(Exception e){
			
		}finally{
			
			connection.close();
		}
		
		
		return "printreport";
	}


		
	public String createReport() throws SQLException{
		if(!verifyLogin(request)){
			return "login";
		}
			DateTimeUtils dateTimeUtils = new DateTimeUtils();
			String id = request.getParameter("id");
			int invoiceid = Integer.parseInt(request.getParameter("invoiceId"));
			String clientid = request.getParameter("clientId");
			String payby = request.getParameter("payby");
			double creditAmount = Double.parseDouble(request.getParameter("crdAmount"));
			session.setAttribute("creditAmount", 0.0);
			if(creditAmount>0){
				session.setAttribute("creditAmount", creditAmount);
				accountsForm.setCrdAmount(DateTimeUtils.changeFormat(creditAmount));
			}else{
				accountsForm.setCrdAmount("0");
			}
			
			if(clientid.equals("")){
				clientid = (String)session.getAttribute("clientId");
			}
			
			double payAmount = Double.parseDouble(request.getParameter("amount"));
			
			session.setAttribute("invoiceid", invoiceid);
			session.setAttribute("chargesInvoiceid", invoiceid);
			session.setAttribute("clientid", clientid);
			session.setAttribute("payAmount", payAmount);
			session.setAttribute("payby", payby);
			
			ArrayList<Accounts>previewChargesList = new ArrayList<Accounts>();
			Vector<Accounts>assesmentList = new Vector<Accounts>();
			
			Connection connection = null;
			try{
				
				connection = Connection_provider.getconnection();
				AccountsDAO accountsDAO = new JDBCAccountsDAO(connection);
				double discount = accountsDAO.getDiscount(invoiceid);
				//set Location
				String locationid = accountsDAO.getLocationID(invoiceid);
				String locationName = accountsDAO.getLocationName(locationid);
				accountsForm.setLocationName(locationName);
				
			/*	ArrayList<Accounts>chargesInvoiceList = accountsDAO.getchargesInvoiceList(invoiceid);
				
				for(Accounts accounts : chargesInvoiceList){
					
					Accounts acc = accountsDAO.getAppointmentDetailsl(Integer.toString(accounts.getInvoiceid()));
					previewChargesList = accountsDAO.getPreviewChargesList(acc,Integer.toString(accounts.getInvoiceid()));
					
					assesmentList.addAll(previewChargesList);
					
				}*/
				
				
				String notes = accountsDAO.getNotes(invoiceid);
				String paymode = accountsDAO.getPaymentMode(invoiceid);
				accountsForm.setPaymode(paymode);
				accountsForm.setSubmitInvoiceNotes(notes);
				
				//setting master chartype data
				ArrayList<Master>masterAssessmentList = accountsDAO.getMasterAssessmentList(invoiceid,"0");
				accountsForm.setMasterAssessmentList(masterAssessmentList);
				
				assesmentList = accountsDAO.getPreviewChargesList(invoiceid);
				
				double totalAmount = 0;
				for(Accounts totalAcc : assesmentList){
					//totalAmount = totalAmount + Double.parseDouble(totalAcc.getCharges());
					double charge = Double.parseDouble(totalAcc.getCharges()) * totalAcc.getQuantity();
					totalAmount = totalAmount + charge;
					
				}
			
				Client client = accountsDAO.getClientData(clientid);
				accountsForm.setClient(client.getTitle() + " " + client.getFirstName() + " " + client.getLastName());
				accountsForm.setAddress(client.getAddress() + "," + client.getTown() + "," + client.getPostCode());
				accountsForm.setDob(client.getDob());
				accountsForm.setClientId(clientid);
				accountsForm.setEmail(client.getEmail());
				accountsForm.setMobno(client.getMobNo());
				accountsForm.setPolicyNo(client.getPolicyNo());
				accountsForm.setPayby(payby);
				accountsForm.setPinCode(client.getPostCode());
				accountsForm.setCity(client.getTown());
				
				session.setAttribute("client",client.getTitle() + " " + client.getFirstName() + " " + client.getLastName() );
				session.setAttribute("address", client.getAddress());
				session.setAttribute("dob", client.getDob());
				session.setAttribute("policyNo", client.getPolicyNo());
				session.setAttribute("email", client.getEmail());
				
				//accountsForm.setAssesmentList(assesmentList);
				accountsForm.setTotalAmount(totalAmount);
				
				//Decimal Account
				accountsForm.setTotalAmountx(dateTimeUtils.changeFormat(totalAmount));
				
				accountsForm.setInvoiceid(invoiceid);
				
				accountsForm.setCreditAmt(dateTimeUtils.changeFormat(payAmount));
				accountsForm.setId(id);
				accountsForm.setDiscount(dateTimeUtils.changeFormat(discount));
				
				/*double credit = totalAmount - payAmount;
				double result = 0;
				double r1 = (totalAmount*(discount/100));
				totalAmount = totalAmount-r1;
				result = totalAmount - payAmount;
				
				//result = Double.parseDouble(new DecimalFormat("##.##").format(result));
				
				if(discount>0){
					accountsForm.setBalance(result);
					//Decimal Account
					accountsForm.setBalancex(dateTimeUtils.changeFormat(result));
				}else {
					accountsForm.setBalance(totalAmount);
					//Decimal Account
					accountsForm.setBalancex(dateTimeUtils.changeFormat(totalAmount));
				}
				*/
				
				
			/*	
				if(payAmount >0){
					
					accountsForm.setDebitAmounnt(dateTimeUtils.changeFormat(credit));	
				}else{
					accountsForm.setDebitAmounnt(dateTimeUtils.changeFormat(totalAmount));
				}
				*/
				
				double totalPaymentReceived = accountsDAO.getTotalPaymentReceived(invoiceid);
				//double totalPaymentReceived = payAmount;
				accountsForm.setCreditAmt(dateTimeUtils.changeFormat(totalPaymentReceived));
				//double credit = totalAmount - payAmount;
				double discountAmt = (totalAmount * (discount/100));
				double amountDue = totalAmount - discountAmt;
				amountDue = amountDue - totalPaymentReceived;
				//amountDue = Double.parseDouble(new DecimalFormat("##.##").format(amountDue));
				if(payAmount >0){
					
					accountsForm.setBalance(amountDue);
					
					//Decimal Account
					accountsForm.setBalancex(dateTimeUtils.changeFormat(amountDue));
				}else{
					accountsForm.setDebitAmounnt(dateTimeUtils.changeFormat(totalAmount));
					accountsForm.setBalance(totalAmount);
					
					//Decimal Account
					accountsForm.setBalancex(dateTimeUtils.changeFormat(totalAmount));
					
				}
				
				
				//policy excess code
				if(payby.equals(Constants.PAY_BY_THIRD_PARTY)){
					Accounts accounts = accountsDAO.checkInvoiceHasPolicyExcess(invoiceid);
					
					if(accounts.isPolicyExcess()){
						
						double balance = amountDue - Double.parseDouble(accounts.getCharges());
						totalAmount = amountDue - Double.parseDouble(accounts.getCharges());
						accountsForm.setPolicyexcesscode(1);
						accountsForm.setPolicyExcess(DateTimeUtils.changeFormat(Double.valueOf(accounts.getCharges())));
						//accountsForm.setBalancex(dateTimeUtils.changeFormat(balance));
					}
				}
				
				
				accountsForm.setPayAmount(totalPaymentReceived);
				
				//Decimal Account
				accountsForm.setPayAmountx(dateTimeUtils.changeFormat(totalPaymentReceived));

				//String address = accountsDAO.getLocationAddress(locationid,loginInfo.getId());
				ArrayList<Clinic> locationAdressList = accountsDAO.getLocationAddress(loginInfo.getClinicid());

				Clinic clinic = new Clinic();
				ClinicDAO clinicDAO  = new JDBCClinicDAO(connection);
				clinic = clinicDAO.getCliniclistDetails(loginInfo.getClinicid());
				accountsForm.setClinicName(clinic.getClinicName());
				accountsForm.setClinicOwner(clinic.getClinicOwner());
				accountsForm.setOwner_qualification(clinic.getOwner_qualification());
				//accountsForm.setClinicaddress(address);
				//accountsForm.setAddress(clinic.getAddress());
				accountsForm.setLandLine(clinic.getLandLine());
			//	accountsForm.setEmail(clinic.getEmail());
				accountsForm.setWebsiteUrl(clinic.getWebsiteUrl());
				accountsForm.setDiscount(dateTimeUtils.changeFormat(discount));
				accountsForm.setLocationAdressList(locationAdressList);
				accountsForm.setClinicLogo(clinic.getUserImageFileName());

				
				if(payby.equalsIgnoreCase("Third Party")){
					int tpid = accountsDAO.getTPId(invoiceid);
					ThirdParty thirdParty = new ThirdParty();
					thirdParty = accountsDAO.getTpDetails(tpid);
					
					accountsForm.setPayeename(thirdParty.getCompanyName());
					//String address = thirdParty.getAddress()+"," +thirdParty.getTown() +" " +thirdParty.getPostcode();
					accountsForm.setPayeeadress(thirdParty.getAddress());
					accountsForm.setPayeeTown(thirdParty.getTown());
					accountsForm.setPayeePostcode(thirdParty.getPostcode());
					accountsForm.setPayeeEmail(thirdParty.getEmail());
					accountsForm.setPayeeConatctNo(thirdParty.getTelephoneLine());
					accountsForm.setEmail(thirdParty.getEmail());
					
					
				}
				else{
					accountsForm.setPayeename(client.getTitle() + " " + client.getFirstName() + " " + client.getLastName());
					accountsForm.setPayeeadress(client.getAddress());
					accountsForm.setPayeeTown(client.getTown());
					accountsForm.setPayeePostcode(client.getPostCode());
					accountsForm.setPayeeEmail(client.getEmail());
					accountsForm.setPayeeConatctNo(client.getMobNo());
					accountsForm.setEmail(client.getEmail());
				}
				
				String date = accountsDAO.getInvoiceDate(invoiceid);
				accountsForm.setDate(DateTimeUtils.getInvoiceCommencingDate(date));
				
				
				//set prepared by
				int preparedbyid = accountsDAO.getInvoicePreparedBy(invoiceid);
				if(preparedbyid!=0){
					UserProfileDAO userProfileDAO = new JDBCUserProfileDAO(connection);
					UserProfile userProfile = userProfileDAO.getUserprofileDetails(preparedbyid);
					String fullname = userProfile.getInitial() + " " + userProfile.getFirstname() + " " + userProfile.getLastname();
					accountsForm.setPreparedby(fullname);
				}else{
					accountsForm.setPreparedby("");
				}
				
				createPdf1();
				
				
			}catch(Exception e){
				e.printStackTrace();
			}finally{
				
				connection.close();
			}
			
			
		
		return "createReport";
	}
	private void createPdf1() throws Exception {
		int invoiceid = (Integer)session.getAttribute("invoiceid");
		String clientid = (String)session.getAttribute("clientid");
		String payby = request.getParameter("payby");
		
		
		
		double payAmount = (Double)session.getAttribute("payAmount");
		
//		String discount = request.getParameter("discount");
			DateTimeUtils dateTimeUtils = new DateTimeUtils();
		
			
			ArrayList<Accounts>previewChargesList = new ArrayList<Accounts>();
			Vector<Accounts>assesmentList = new Vector<Accounts>();
			
			Connection connection = null;
			
			try{
				connection = Connection_provider.getconnection();
				AccountsDAO accountsDAO = new JDBCAccountsDAO(connection);
				Clinic clinic = new Clinic();
				ClinicDAO clinicDAO  = new JDBCClinicDAO(connection);
				clinic = clinicDAO.getCliniclistDetails(loginInfo.getClinicid());
				String notes = accountsDAO.getNotes(invoiceid);
				String paymode = accountsDAO.getPaymentMode(invoiceid);
				//set Location
				String locationid = accountsDAO.getLocationID(invoiceid);
				String locationName = accountsDAO.getLocationName(locationid);
			//	String address = accountsDAO.getLocationAddress(locationid,loginInfo.getId());
				ArrayList<Clinic> locationAdressList = accountsDAO.getLocationAddress(loginInfo.getClinicid());

				String clientName = accountsDAO.getClientName(clientid);
				Client client = accountsDAO.getClientData(clientid);
				StringBuffer str = new StringBuffer();
				str.append("<style>");
				str.append(".prew-table {");
				str.append("background:#FFFFFF;");
				str.append("color:#666666;");
				str.append("font-family:Arial,Helvetica,sans-serif;");
				str.append("font-size:62.5%;");
				str.append("font-size:1em;}");
				str.append(".prew-table caption {");
				str.append("font-size:1.4em;");
				str.append("font-weight:bold;");
				str.append("padding:6px;");
				str.append("text-align:left;}");
				str.append(".prew-table th {");
				str.append("padding:6px 6px 6px 12px;");
				str.append("text-align:left;");
				str.append("font-weight:bold;");
				str.append("font-size: small;");
				str.append("background-color: rgb(236, 233, 233);}");
				str.append(".prew-table td {");
				str.append("padding:6px 6px 6px 0px;");
				str.append("font-size: small;}");
				
				str.append("</style>");
				
				str.append("<div id='login' class='block_div'>");
				str.append("<div style='font-size: 26px; font-weight: bold;'>"+clinic.getClinicName()+"</div>");
				if(!clinic.getClinicOwner().equals("")){
					str.append("<div style='font-size: 20px; font-weight: bold;'>"+clinic.getClinicOwner()+" , "+clinic.getOwner_qualification()+"</div>");
				}
				
				
				str.append("<div style='font-size: 16px; font-weight: bold;'>");
				for(Clinic c1 : locationAdressList){
					str.append(c1.getAddress());
				}
				str.append("</div>");
				str.append("<div style='font-size: 16px; font-weight: normal;'>Tel/Fax:"+clinic.getLandLine()+"</div>");
				if(!clinic.getWebsiteUrl().equals("")){
					str.append("<div style='font-size: 16px; font-weight: normal;'>E: "+clinic.getEmail()+" W: "+clinic.getWebsiteUrl()+"</div><br><br>");
				}else{
					str.append("<div style='font-size: 16px; font-weight: normal;'>E: "+clinic.getEmail()+" </div><br><br>");
				}
				
			
				if(payby.equalsIgnoreCase("Third Party")){
					int tpid = accountsDAO.getTPId(invoiceid);
					ThirdParty thirdParty = new ThirdParty();
					thirdParty = accountsDAO.getTpDetails(tpid);
					
					accountsForm.setPayeename(thirdParty.getCompanyName());
					String address1 = thirdParty.getAddress()+"," +thirdParty.getTown() +" " +thirdParty.getPostcode();
					
				}
				else{
					accountsForm.setPayeename(client.getTitle() + " " + client.getFirstName() + " " + client.getLastName());
					accountsForm.setPayeeadress(client.getAddress());
					
				}
				
				if(payby.equals(Constants.PAY_BY_THIRD_PARTY)){
					int tpid = accountsDAO.getTPId(invoiceid);
					ThirdParty thirdParty = new ThirdParty();
					thirdParty = accountsDAO.getTpDetails(tpid);
					String address1 = thirdParty.getAddress();
					
					if(payAmount > 0){
						str.append("<div style='font-size: 11px; font-weight: normal; padding-left: 20px;'><label>"+thirdParty.getCompanyName()+"</label> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp <font style='font-size: 20px; font-weight: bold;padding-left: 300px; '>Payment</font> <br>"+address1+"<br> "+thirdParty.getTown()+"<br> "+thirdParty.getPostcode()+"");
					}else{
						str.append("<div style='font-size: 11px; font-weight: normal; padding-left: 20px;'><label>"+thirdParty.getCompanyName()+"</label>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp  <font style='font-size: 20px; font-weight: bold;padding-left: 300px; '>Invoice</font> <br>"+address1+" "+thirdParty.getTown()+"<br> "+thirdParty.getPostcode()+"");
					}
				}
				else{
					if(payAmount > 0){
						str.append("<div style='font-size: 11px; font-weight: normal; padding-left: 20px;'><label>"+client.getTitle() + " " + client.getFirstName() + " " + client.getLastName()+"</label> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp <font style='font-size: 20px; font-weight: bold;padding-left: 300px; '>Payment</font> <br>"+client.getAddress()+" <br> "+client.getTown()+" <br> "+client.getPostCode()+" ");
					}else{
						str.append("<div style='font-size: 11px; font-weight: normal; padding-left: 20px;'><label>"+client.getTitle() + " " + client.getFirstName() + " " + client.getLastName()+"</label> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp <font style='font-size: 20px; font-weight: bold;padding-left: 300px; '>Invoice</font> <br>"+client.getAddress()+" <br> "+client.getTown()+" <br> "+client.getPostCode()+" ");
					}
				}
				
				
				String date = accountsDAO.getInvoiceDate(invoiceid);
				str.append("<table cellpadding='0' cellspacing='0' style='width: 100%;font-size: 11px;' >");
				str.append("<tr>");
				str.append("<td></td>");
				str.append("<td></td>");
				str.append("<td></td>");
				str.append("<td>Unit Cost</td>");
				str.append("<td>Qty</td>");
				str.append("<td>Total</td>");
				str.append("</tr>");
				
				str.append("<tr>");
				str.append("<td colspan='4'>Invoice No: 0000"+invoiceid+" Date: "+date+" Account No : 0000"+client.getId()+"</td>");
				
				
				str.append("</tr>");
				
				
				str.append("<tr>");
				str.append("<td colspan='6'>");
				str.append("<table width='50%'>");
				str.append("<tr>");
				str.append("<td style='font-weight: bold;'>Client</td>");
				str.append("<td>"+client.getTitle() + " " + client.getFirstName() + " " + client.getLastName()+"</td>");
				str.append("</tr>");
				
				
				
				str.append("<tr>");
				str.append("<td style='font-weight: bold;'>Client Address</td>");
				str.append("<td>"+client.getAddress() + "," + client.getTown() + "," + client.getPostCode()+"</td>");
				str.append("</tr>");
				
				
				str.append("<tr>");
				str.append("<td style='font-weight: bold;'>D.O.B.</td>");
				str.append("<td>"+client.getDob()+"</td>");
				str.append("</tr>");
				
				/*str.append("<tr>");
				str.append("<td style='font-weight: bold;'>Contact No.</td>");
				str.append("<td>"+client.getMobNo()+"</td>");
				str.append("</tr>");*/
				
			/*	str.append("<tr>");
				str.append("<td style='font-weight: bold;'>Email ID</td>");
				str.append("<td>"+client.getEmail()+"</td>");
				str.append("</tr>");*/
				
				if(payby.equals(Constants.PAY_BY_THIRD_PARTY)){
					str.append("<tr>");
					str.append("<td style='font-weight: bold;'>Policy No</td>");
					str.append("<td>"+client.getPolicyNo()+"</td>");
					str.append("</tr>");
				}
				
				
				str.append("</table>");
				str.append("</td>");
				str.append("</tr>");
				
			/*	ArrayList<Accounts>chargesInvoiceList = accountsDAO.getchargesInvoiceList(invoiceid);
				
				for(Accounts accounts : chargesInvoiceList){
					int additional_charge = accountsDAO.getAdditionalChargeValue(accounts.getInvoiceid());
					if(additional_charge == 1){
						previewChargesList = accountsDAO.getAdditionalPreviewChargesList(Integer.toString(accounts.getInvoiceid()));

					}
					else{
					Accounts acc = accountsDAO.getAppointmentDetailsl(Integer.toString(accounts.getInvoiceid()));
					previewChargesList = accountsDAO.getPreviewChargesList(acc,Integer.toString(accounts.getInvoiceid()));
					}
					assesmentList.addAll(previewChargesList);
					
					
				}*/
				
				assesmentList = accountsDAO.getPreviewChargesList(invoiceid);
				
				double totalAmount = 0;
				
				for(Accounts accounts : assesmentList){
					
					str.append("<tr>");
					str.append("<td>"+accounts.getCommencing()+"</td>");
					/*if(accounts.isDna()){
						str.append("<td>"+accounts.getAppointmentType()+" <span style='color:red'>(DNA)</span></td>");
					}else{
						str.append("<td>"+accounts.getAppointmentType()+"</td>");
					}*/
					str.append("<td>"+accounts.getAppointmentType()+"</td>");
					str.append("<td></td>");
					str.append("<td>"+accounts.getCharges()+"</td>");
					str.append("<td>"+accounts.getQuantity()+"</td>");
					str.append("<td>"+accounts.getCharges()+"</td>");
					str.append("<td></td>");
					str.append("</tr>");
					
					/*str.append("<tr>");
					str.append("<td>Practitioner</td>");
					str.append("<td>"+accounts.getPractitionerName()+"</td>");
					str.append("<td></td>");
					str.append("<td></td>");
					str.append("<td></td>");*/
					
					double charge = Double.parseDouble(accounts.getCharges()) * accounts.getQuantity();
					totalAmount = totalAmount + charge;
					
					//totalAmount = totalAmount + Double.parseDouble(accounts.getCharges());
				}
				
				double credit = 0;
				double balance = 0;
				double totalPaymentReceived = accountsDAO.getTotalPaymentReceived(invoiceid);
				
				double discount = accountsDAO.getDiscount(invoiceid);

				if(discount >0){
					double dicsAmount = (discount*totalAmount)/100;
					credit = totalAmount - dicsAmount;
					
					
					balance = credit - totalPaymentReceived;
					//balance = (Double.parseDouble(new DecimalFormat("##.##").format(balance)));
					
				}else{
					credit = totalAmount - totalPaymentReceived;
					//accountsForm.setBalance(totalAmount);
					balance = credit;
				}
				
				
				
				
				//double credit = totalAmount - payAmount;
				double debitAmt = 0;
				
				
				if(totalPaymentReceived >0){
					
					debitAmt = Double.parseDouble(new DecimalFormat("##.##").format(credit));
					
				}else{
					credit = 0;
					debitAmt = totalAmount;
				}
				
				str.append("<tr><td colspan='6'><hr></td></tr>");
				str.append("<tr>");
				str.append("<td colspan='4' align='right' style='font-weight: bold; padding-right: 50px; '>Total</td>");
				str.append("<td colspan='6' align='right' style='font-weight: bold; padding-right: 50px; '>"+Constants.getCurrency(loginInfo) +" "+dateTimeUtils.changeFormat(totalAmount)+"</td></tr>");
				str.append("<tr>");
				str.append("<td colspan='4' align='right' style='font-weight: bold; '>Total Payment Received</td>");
				str.append("<td colspan='6' align='right' style='font-weight: bold; padding-right: 50px; '>"+Constants.getCurrency(loginInfo)+" "+dateTimeUtils.changeFormat(totalPaymentReceived)+"</td>");
				str.append("</tr>");
				
				if(payby.equals(Constants.PAY_BY_CLIENT)){
					str.append("<tr>");
					str.append("<td colspan='4' align='right' style='font-weight: bold; '>Discount</td>");
					str.append("<td colspan='6' align='right' style='font-weight: bold; padding-right: 50px; '>"+Constants.getCurrency(loginInfo)+" "+dateTimeUtils.changeFormat(discount)+"%</td>");
					str.append("</tr>");
				}
				
				//policy excess
				
				if(payby.equals(Constants.PAY_BY_THIRD_PARTY)){
					Accounts accounts = accountsDAO.checkInvoiceHasPolicyExcess(invoiceid);
					
					if(accounts.isPolicyExcess()){
						
						 balance = accountsForm.getBalance() - Double.parseDouble(accounts.getCharges());
						//totalAmount = totalAmount - Double.parseDouble(accounts.getCharges());
						 	str.append("<tr>");
							str.append("<td colspan='4' align='right' style='font-weight: bold; '>Policy Excess</td>");
							str.append("<td colspan='6' align='right' style='font-weight: bold; padding-right: 50px; '>-"+Constants.getCurrency(loginInfo)+" "+dateTimeUtils.changeFormat(Double.parseDouble(accounts.getCharges()))+"</td>");
							str.append("</tr>");
					}
				}

				
				
				
				str.append("<tr>");
				str.append("<td colspan='4' align='right' style='font-weight: bold; '>Balance Outstanding</td>");
				str.append("<td colspan='6' align='right' style='font-weight: bold; padding-right: 50px; '>"+Constants.getCurrency(loginInfo)+" "+dateTimeUtils.changeFormat(balance)+"</td>");
				str.append("</tr>");
				
				double creditAmount = (Double)session.getAttribute("creditAmount");
				 if(creditAmount>0){
				
				 	str.append("<tr>");
					str.append("<td colspan='4' align='right' style='font-weight: bold; '> Credit Balance</td>");
					str.append("<td colspan='6' align='right' style='font-weight: bold; padding-right: 50px; '>"+Constants.getCurrency(loginInfo) + DateTimeUtils.changeFormat(creditAmount)+"</td>");
					str.append("</tr>");
				 }
				 
				 
				 
					str.append("<tr>");
					str.append("<td colspan='6' align='left' style='font-weight: bold; padding-right: 50px; '>Payment Mode:"+paymode+"</td>");
					str.append("</tr>");
				
				str.append("<tr>");
				str.append("<td colspan='6' align='left' style='font-weight: bold; padding-right: 50px; '>Notes:"+notes+"</td>");
				str.append("</tr>");
				str.append("</table>");
				
				String filePath = request.getRealPath("//invoice//");
				//String filename = "Invoice_"+clientid+"_"+invoiceid+".pdf";
				String filename = invoiceid+"_"+clientName+".pdf";

				String filePath1 = request.getRealPath("//liveData//invoiceData//");
				//String filename1 = "Invoice_"+clientid+"_"+invoiceid+".pdf";
				String filename1 = invoiceid+"_"+clientName+".pdf";

				htmlToPdfFile(str.toString(), filePath, filename);
				htmlToPdfFile(str.toString(), filePath1, filename1);
				
				session.setAttribute("pdfFileName", filePath+"/"+filename);
				
				accountsForm.setFilename(filename1);
//				System.out.println("pdf done");
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			
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

	public AccountsForm getModel() {
		// TODO Auto-generated method stub
		return accountsForm;
	}

	public void prepare() throws Exception {
		
		Connection connection = null;
		try{
			connection = Connection_provider.getconnection();
			AppointmentDAO appointmentDAO = new JDBCAppointmentDAO(connection);
			MasterDAO masterDAO = new JDBCMasterDAO(connection);
			AccountsDAO accountsDAO = new JDBCAccountsDAO(connection);
			ArrayList<AppointmentType>additionalChargeList = appointmentDAO.getAdditionalChaergeTypeList("");
			accountsForm.setAdditionalChargesList(additionalChargeList);
			
			ArrayList<Master>masterChageTypeList = appointmentDAO.getmasterChageTypeList(loginInfo);
			accountsForm.setMasterChageTypeList(masterChageTypeList);
			accountsForm.setMasterchargetype("Appointment Charge");
			
			 ArrayList<Master>bankNameList = masterDAO.getBankNameList();
			 accountsForm.setBankNameList(bankNameList);
			 
			 ArrayList<Accounts> discountgivenuserlist = accountsDAO.getDiscountGivenUserList();
				accountsForm.setDiscountgivenuserlist(discountgivenuserlist);
				
				
				
				ArrayList<Master> invoiceTypeList = accountsDAO
						.getInvoiceTypeList();
				accountsForm.setInvoiceTypeLis(invoiceTypeList);
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			
			connection.close();
		}
		
	}

	public String approveinvoicediscout() throws Exception{
		Connection connection = null;
		try {
			connection = Connection_provider.getconnection();
			AppointmentDAO appointmentDAO = new JDBCAppointmentDAO(connection);
			String invoiceid = request.getParameter("invoiceId");
			String userid = loginInfo.getUserId();
			String datetime = DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone());
			int res = appointmentDAO.updateApproveInvoiceDiscout(invoiceid,userid,datetime,"");
			response.setContentType("text/html");
			response.setHeader("Cache-Control", "no-cache");
			response.getWriter().write(""); 
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			
			connection.close();
		}
		return null;
	}
	
	public String requestfordiscount() throws Exception{
		Connection connection = null;
		try {
			connection = Connection_provider.getconnection();
			AccountsDAO accountsDAO=new JDBCAccountsDAO(connection);
			AppointmentDAO appointmentDAO = new JDBCAppointmentDAO(connection);
			UserProfileDAO userProfileDAO1 = new JDBCUserProfileDAO(connection);
			ClientDAO clientDAO = new JDBCClientDAO(connection);
			String invoiceid = request.getParameter("invoiceId");
			String disctype = request.getParameter("disctype");
			String discval = request.getParameter("discval");
			String invoiceamount = request.getParameter("invoiceamount");
			String discountgivenuserid = request.getParameter("discountgivenuserid");
			String discount_reason = request.getParameter("discount_reason");
			String userid = loginInfo.getUserId();
			String datetime = DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone());
			Accounts accounts = accountsDAO.getDiscInvoiceDetails(invoiceid);
			//int res = appointmentDAO.updateApproveInvoiceDiscout(invoiceid,userid,datetime);
			int result = appointmentDAO.updateRequestInvoiceDiscout(invoiceid);
			int res = appointmentDAO.saveDiscountRequest(invoiceid,userid,datetime,disctype,discval,invoiceamount,discountgivenuserid,discount_reason,accounts.getClientid());
			
			ArrayList<UserProfile> arrayList = userProfileDAO1.getDiscApproveUserList();
			for (UserProfile userProfile : arrayList) {
				int updatetstatus = userProfileDAO1.updateBlankNotificationids("noti_dis_reqtd_ids",userProfile.getUserid());
				int updatecount = userProfileDAO1.updateNotificationCount("noti_dis_reqtd_count",userProfile.getUserid());
				int updateids = userProfileDAO1.updateNotificationIdValue("noti_dis_reqtd_ids",userProfile.getUserid(),""+res);
			}
			
			if(loginInfo.isDisc_approve_sms()){
				discountgivenuserid = DateTimeUtils.numberCheck(discountgivenuserid);
				UserProfile userProfile = userProfileDAO1.getUserprofileDetails(Integer.parseInt(discountgivenuserid));
				if(!DateTimeUtils.isNull(userProfile.getMobile()).equals("")){
					Connection connection1 = DriverManager.getConnection(""+Constants.DB_HOST+":3306/"+loginInfo.getClinicUserid()+"",""+Constants.DB_USER+"",""+Constants.DB_PWD+"");
					UserProfileDAO userProfileDAO = new JDBCUserProfileDAO(connection1);
					String linkaddress = userProfileDAO.getClinicLinkAddress(loginInfo.getClinicUserid());
					Client client = clientDAO.getSelectedSessionClientDetails(""+accounts.getClientid());
					
					String patientname =client.getClientName();
					SmsService s = new SmsService();
					String discmsg ="";
					if(disctype.equals("0")){
						discmsg =discval+"per";
					}else{
						discmsg ="Rs."+discval;
					}
					String msg ="Discount request of "+patientname+" requested by "+userid+" of "+discmsg+" on amount "+invoiceamount+". For approve please click on link";
					msg=msg+" "+"http://"+linkaddress+":8080/YUVICARE/discountapproveMainDashBoard?userid="+userProfile.getUserid()+"amp;clinicuserid="+loginInfo.getClinicUserid()+"amp;discid="+res+"amp;invoiceid="+invoiceid+"";
					String templateid="";
					s.sendSms(msg, userProfile.getMobile(), loginInfo, new EmailLetterLog(),templateid);
					connection1.close();
				}
			}
			
			response.setContentType("text/html");
			response.setHeader("Cache-Control", "no-cache");
			response.getWriter().write(""); 
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			
			connection.close();
		}
		return null;
	}

	public String discountrequestdashboard() throws Exception {
		Connection connection = null;
		try {
			connection = Connection_provider.getconnection();
			AppointmentDAO appointmentDAO = new JDBCAppointmentDAO(connection);
			String filter_status =(String)session.getAttribute("discount_dashboard_filter_status");
			String fromdate = (String)session.getAttribute("discount_dashboard_fromdate");
			String todate = (String)session.getAttribute("discount_dashboard_todate");
			String countdata =(String) session.getAttribute("discount_dashboard_countdata");
			if(fromdate==null){
				fromdate = accountsForm.getFromdate();
			}else{
				session.removeAttribute("discount_dashboard_fromdate");
			}
			
			if(todate==null){
				todate = accountsForm.getTodate();
			}else{
				session.removeAttribute("discount_dashboard_todate");
			}
			if(filter_status==null){
				filter_status = accountsForm.getFilter_status();
			}else{
				session.removeAttribute("discount_dashboard_filter_status");
			}
			
			if(countdata==null){
				countdata = request.getParameter("countdata");
			}else{
				session.removeAttribute("discount_dashboard_countdata");
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
			
			if(filter_status!=null){
				if(filter_status.equals("")){
					filter_status = "0";
				}
			}else{
				filter_status = "0";
			}
			boolean accessofapprove= false;
			if(loginInfo.isRefund_dashboard() || loginInfo.getUserType()==2 || loginInfo.isRef_dis_pay()){
				accessofapprove =true;
			}
			
			if(countdata!=null){
				if(countdata.equals("")){
					countdata = "0";
				}
			}else{
				countdata = "0";
			}
			accountsForm.setCountdata(countdata);
			ArrayList<CompleteAppointment> parentdiscountrequestlist = appointmentDAO.getParentDiscountRequestList(fromdate,todate,accessofapprove,loginInfo.getId(),filter_status,loginInfo.getUserId(),countdata); 
			int non_approve_count = appointmentDAO.getApproveAppliedCount(fromdate,todate,accessofapprove,loginInfo.getId(),filter_status,loginInfo.getUserId(),"1");
			int non_applied_count = appointmentDAO.getApproveAppliedCount(fromdate,todate,accessofapprove,loginInfo.getId(),filter_status,loginInfo.getUserId(),"2");
			int deleted_count=appointmentDAO.getApproveAppliedCount(fromdate,todate,accessofapprove,loginInfo.getId(),filter_status,loginInfo.getUserId(),"3");
			accountsForm.setParentdiscountrequestlist(parentdiscountrequestlist);
			accountsForm.setFromdate(DateTimeUtils.getCommencingDate1(fromdate));
			accountsForm.setTodate(DateTimeUtils.getCommencingDate1(todate));
			accountsForm.setFilter_status(filter_status);
			accountsForm.setCountdata(countdata);
			accountsForm.setNon_approve_count(non_approve_count);
			accountsForm.setNon_applied_count(non_applied_count);
			accountsForm.setDelete_count(deleted_count);
			
			UserProfileDAO userProfileDAO = new JDBCUserProfileDAO(connection);
			if(loginInfo.getUserType()==2 || loginInfo.isRef_dis_pay()){
				int updatetstatus = userProfileDAO.updateNotificationIds("noti_dis_reqtd_ids",loginInfo.getUserId(),"0");
				int updatecount = userProfileDAO.updateNotificationIds("noti_dis_reqtd_count",loginInfo.getUserId(),"0");
			}
			int updatetstatus = userProfileDAO.updateNotificationIds("noti_dis_apprd_ids",loginInfo.getUserId(),"0");
			int updatecount = userProfileDAO.updateNotificationIds("noti_dis_apprd_count",loginInfo.getUserId(),"0");
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			connection.close();
		}
		//return "refundrequestdashboard";
		return "discountrequestdashboard";
	}
	
	public String aprovedinvoicediscount() throws Exception{
	  	Connection connection =null;
	  	try {
		   connection = Connection_provider.getconnection();
		   AppointmentDAO appointmentDAO = new JDBCAppointmentDAO(connection);
		   UserProfileDAO userProfileDAO = new JDBCUserProfileDAO(connection);
		   String invoiceid = request.getParameter("invoiceId");
		   String approve_notes = request.getParameter("approve_notes");
		   String id = request.getParameter("id");
		   String userid = loginInfo.getUserId();
		   String datetime = DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone());
		   String isgroupdiscount = request.getParameter("isgroupdiscount");
		   if(isgroupdiscount.equals("0")){
			   int res = appointmentDAO.updateApproveInvoiceDiscout(invoiceid,userid,datetime,approve_notes);
			   int res1 = appointmentDAO.updateInvoiceDiscout(id,userid,datetime,approve_notes);
		   }else{
			   for (String newid : id.split(",")) {
				   if(newid.equals("0")){
					   continue;
				   }
				   CompleteAppointment appointment = appointmentDAO.getRequestDiscountDataFromId(newid);
				   int res = appointmentDAO.updateApproveInvoiceDiscout(appointment.getInvoiceid(),userid,datetime,approve_notes);
				   int res1 = appointmentDAO.updateInvoiceDiscout(newid,userid,datetime,approve_notes);
			   }
			   
		   }
		   if(isgroupdiscount.equals("0")){
			   CompleteAppointment appointment = appointmentDAO.getDeleteDiscountReqData(id);
			   int updatetstatus = userProfileDAO.updateBlankNotificationids("noti_dis_apprd_ids",appointment.getRequested_userid());
			   int updatecount = userProfileDAO.updateNotificationCount("noti_dis_apprd_count",appointment.getRequested_userid());
			   int updateids = userProfileDAO.updateNotificationIdValue("noti_dis_apprd_ids",appointment.getRequested_userid(),id);
		   }
		   
		   
		   String fromdate = request.getParameter("fromdate");
		   String todate = request.getParameter("todate");
		   String filter_status = request.getParameter("filter_status");
		   String countdata = request.getParameter("countdata");
		   session.setAttribute("discount_dashboard_fromdate", fromdate);
		   session.setAttribute("discount_dashboard_todate", todate);
		   session.setAttribute("discount_dashboard_filter_status", filter_status);
		   session.setAttribute("discount_dashboard_countdata", countdata);
	  } catch (Exception e) {
		  e.printStackTrace();
	  }finally{
		  connection.close();
	  }
	  return discountrequestdashboard();
	}
	
	public String getdiscountdata() throws Exception{
		Connection connection = null;
		try {
			connection = Connection_provider.getconnection();
			AppointmentDAO appointmentDAO = new JDBCAppointmentDAO(connection);
			String invoiceid = request.getParameter("invoiceId");
			
			CompleteAppointment appointment = appointmentDAO.getRequestDiscountData(invoiceid);
			StringBuffer buffer = new StringBuffer();
			buffer.append(""+appointment.getDisc_type()+"");
			buffer.append("~");
			buffer.append(""+appointment.getDisc_amount()+"");
			buffer.append("~");
			buffer.append(""+appointment.getPractitionerId()+"");
			buffer.append("~");
			buffer.append(""+appointment.getRemark()+"");
			buffer.append("~");
			buffer.append(""+appointment.getTotal()+"");
			
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
	//lokesh-----for making ipost =0 so that Posted cond can be modified to posted
public String ipostToPost() throws Exception{
		
		String id = request.getParameter("id");
		
		Connection connection  = null;
		try{
			
			connection  = Connection_provider.getconnection();
			AccountsDAO accountsDAO = new JDBCAccountsDAO(connection);
			ChargesAccountProcessingDAO chargesAccountProcessingDAO = new JDBCChargeAccountProcesDAO(connection);
			
			String date = DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone());
			int res = chargesAccountProcessingDAO.updateIpostToPost(id, date,loginInfo.getUserId());
			
			
			
		}	catch(Exception e){
			e.printStackTrace();
		}finally{
			
			connection.close();
		}
		
		return execute();
	}
public String changeWardidinCharges(){
	String invoicechrgeid=request.getParameter("chargeid");
	String wardid=request.getParameter("wardid");
	Connection connection= null;
	try {
		connection=Connection_provider.getconnection();
		ChargesAccountProcessingDAO chargesAccountProcessingDAO= new JDBCChargeAccountProcesDAO(connection);
		int result=chargesAccountProcessingDAO.changeWard_in_apm_invoice_assesments(invoicechrgeid, wardid);
		
		response.setContentType("text/html");
		response.setHeader("Cache-Control", "no-cache");
		response.getWriter().write(""+result+""); 
	} catch (Exception e) {
		e.printStackTrace();
	}
	return null;
}

public String changeWardidinChargesByInvid(){
	String invoicechrgeid=request.getParameter("chargeid");
	String wardid=request.getParameter("wardid");
	Connection connection= null;
	try {
		connection=Connection_provider.getconnection();
		ChargesAccountProcessingDAO chargesAccountProcessingDAO= new JDBCChargeAccountProcesDAO(connection);
		CompleteAptmDAO completeAptmDAO=new JDBCCompleteAptmDAO(connection);
		AccountsDAO accountsDAO=new JDBCAccountsDAO(connection);
		String datetime = DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone());
		String invoiceid= accountsDAO.getChargeInvoiceId(invoicechrgeid);
		int res=completeAptmDAO.saveModifyInvoiceLog(loginInfo.getUserId(),datetime,invoiceid,1,invoicechrgeid);
		int result=chargesAccountProcessingDAO.changeWard_in_apm_invoice_assesments_byinvid(invoicechrgeid, wardid);
		
		response.setContentType("text/html");
		response.setHeader("Cache-Control", "no-cache");
		response.getWriter().write(""+result+""); 
	} catch (Exception e) {
		e.printStackTrace();
	}
	return null;
}

public String changeapptchgargemaster(){
	try{
		Connection connection= null;
		connection= Connection_provider.getconnection();
		String mastercharge=request.getParameter("mastercharge");
		String id= request.getParameter("chargeid");
		ChargesAccountProcessingDAO chargesAccountProcessingDAO= new JDBCChargeAccountProcesDAO(connection);
		int result=chargesAccountProcessingDAO.updateMasterChargeInAsseessment(id, mastercharge);
		response.setContentType("text/html");
		response.setHeader("Cache-Control", "no-cache");
		response.getWriter().write(""+result); 
	}catch (Exception e) {
		e.printStackTrace();
	}
	
	return null;
}
public String discountzero(){
	try{
		Connection connection= null;
		connection= Connection_provider.getconnection();
		String invoiceid=request.getParameter("invoiceId");
		ChargesAccountProcessingDAO chargesAccountProcessingDAO= new JDBCChargeAccountProcesDAO(connection);
		CompleteAppointment appointment=chargesAccountProcessingDAO.getChargeInvoiceData(invoiceid);
		if(Double.parseDouble(appointment.getDisc_amount())>0){
			double actualamt=appointment.getDebit()+Double.parseDouble(appointment.getDisc_amount());
			int updatedisc=chargesAccountProcessingDAO.updategivendisc(invoiceid,actualamt);
			int deletediscreq=chargesAccountProcessingDAO.deletediscreq(invoiceid);
		}
		response.setContentType("text/html");
		response.setHeader("Cache-Control", "no-cache");
		response.getWriter().write(""); 
	}catch (Exception e) {
		e.printStackTrace();
	}
	
	return null;
}

public String update() throws Exception{
	
	
	try{
		Connection connection= null;
		connection= Connection_provider.getconnection();
		
		String invoiceid=request.getParameter("invoiceeid");
		String remark=request.getParameter("remark");
		ChargesAccountProcessingDAO chargesAccountProcessingDAO= new JDBCChargeAccountProcesDAO(connection);
		
		int updatedisc=chargesAccountProcessingDAO.updateremark(invoiceid,remark);
			
		
		response.setContentType("text/html");
		response.setHeader("Cache-Control", "no-cache");
		response.getWriter().write(updatedisc);
		
	}catch (Exception e) {
		e.printStackTrace();
	}
	
	return null;
	
	
	
	
}

}
