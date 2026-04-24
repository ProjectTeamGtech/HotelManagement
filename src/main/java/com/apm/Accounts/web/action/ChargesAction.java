
package com.apm.Accounts.web.action;

import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
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
import com.apm.Accounts.eu.bi.ChargesAccountProcessingDAO;
import com.apm.Accounts.eu.bi.StatementDAO;
import com.apm.Accounts.eu.blogic.jdbc.JDBCAccountsDAO;
import com.apm.Accounts.eu.blogic.jdbc.JDBCAdditionalDAO;
import com.apm.Accounts.eu.blogic.jdbc.JDBCChargeAccountProcesDAO;
import com.apm.Accounts.eu.blogic.jdbc.JDBCStatementDAO;
import com.apm.Accounts.eu.entity.Accounts;
import com.apm.Accounts.eu.entity.Invoice;
import com.apm.Accounts.web.form.AccountsForm;
import com.apm.DiaryManagement.eu.bi.ClientDAO;
import com.apm.DiaryManagement.eu.bi.CompleteAptmDAO;
import com.apm.DiaryManagement.eu.bi.NotAvailableSlotDAO;
import com.apm.DiaryManagement.eu.blogic.jdbc.JDBCClientDAO;
import com.apm.DiaryManagement.eu.blogic.jdbc.JDBCCompleteAptmDAO;
import com.apm.DiaryManagement.eu.blogic.jdbc.JDBCNotAvailableSlotDAO;
import com.apm.DiaryManagement.eu.entity.Client;
import com.apm.DiaryManagement.eu.entity.CompleteAppointment;
import com.apm.DiaryManagement.eu.entity.DiaryManagement;
import com.apm.DiaryManagement.eu.entity.NotAvailableSlot;
import com.apm.DiaryManagement.web.action.EmbeddedImageEmailUtil;
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
import com.apm.Master.eu.bi.DischargeStatusDAO;
import com.apm.Master.eu.bi.MasterDAO;
import com.apm.Master.eu.blogic.jdbc.JDBCDischargeOutcomeDAO;
import com.apm.Master.eu.blogic.jdbc.JDBCDischargeStatus;
import com.apm.Master.eu.blogic.jdbc.JDBCMasterDAO;
import com.apm.Master.eu.entity.Master;
import com.apm.Pharmacy.web.action.Tra;
import com.apm.Registration.eu.bi.ClinicDAO;
import com.apm.Registration.eu.bi.UserProfileDAO;
import com.apm.Registration.eu.blogic.jdbc.JDBCClinicDAO;
import com.apm.Registration.eu.blogic.jdbc.JDBCUserProfileDAO;
import com.apm.Registration.eu.entity.Clinic;
import com.apm.Registration.eu.entity.UserProfile;
import com.apm.Report.eu.bi.ChargesReportDAO;
import com.apm.Report.eu.blogic.jdbc.JDBCChargesReportDAO;
import com.apm.ThirdParties.eu.entity.ThirdParty;
import com.apm.Tools.eu.bi.EmailTemplateDAO;
import com.apm.Tools.eu.blogic.jdbc.JDBCEmailTemplateDAO;
import com.apm.Tools.eu.entity.EmailTemplate;
import com.apm.common.eu.blogic.jdbc.Connection_provider;
import com.apm.common.utils.CommonOpdIpdReport;
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

public class ChargesAction extends BaseAction implements Preparable, ModelDriven<AccountsForm>{
	HttpServletRequest request = (HttpServletRequest) ActionContext.getContext().get(ServletActionContext.HTTP_REQUEST);
	HttpServletResponse response = (HttpServletResponse)ActionContext.getContext().get(ServletActionContext.HTTP_RESPONSE);
	HttpSession session = request.getSession(true);
	LoginInfo loginInfo = LoginHelper.getLoginInfo(request);
	private Pagination pagination = new Pagination(10, 1);

	public Pagination getPagination() {
		return pagination;
	}

	public void setPagination(Pagination pagination) {
		this.pagination = pagination;
	}

	AccountsForm accountsForm = new AccountsForm();
	public String raiseinvoice() throws Exception{
		if(!verifyLogin(request)){
			return "login";
		}
		Connection connection = null;
		DateTimeUtils dateTimeUtils = new DateTimeUtils();
		try{
			connection = Connection_provider.getconnection();
			AccountsDAO accountsDAO = new JDBCAccountsDAO(connection);
			
			String allChargeList = accountsDAO.getTempChagesInvoiceStringData(loginInfo.getLoginsessionid());
			StringBuffer chargesStr = new StringBuffer();
			
			String chargeType = "Charge";
			ArrayList<Accounts>accountList = accountsDAO.getChargesInvoiceList(allChargeList,chargeType);
			
			double debitTotal = 0.0;
			double balanceTotal = 0.0;
			
			for(Accounts accounts:accountList){
				debitTotal = debitTotal + accounts.getDebitAmount();
			}
			
			accountsForm.setAccountList(accountList);
			accountsForm.setDebitTotal(debitTotal);
			accountsForm.setBalanceTotal(debitTotal);
			accountsForm.setNumberOfChages(accountList.size());
			
			//Decimal Account
			accountsForm.setDebitTotalx(dateTimeUtils.changeFormat(debitTotal));
			accountsForm.setBalanceTotalx(dateTimeUtils.changeFormat(debitTotal));

			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			
			connection.close();
		}
		
		//setFormData();
		return "chargesraiseinvoice";
	}
	
	
	
	public String createinvoice() throws Exception{
		
		if(!verifyLogin(request)){
			return "login";
		}
		Connection connection = null;
		try{
			
			connection = Connection_provider.getconnection();
			AccountsDAO accountsDAO = new JDBCAccountsDAO(connection);
			
			String chargeType = "Invoice";
			
			String totalAssesment[] = accountsForm.getTotalassesment().split(",");
			
			ArrayList<Accounts>invoiceList = new ArrayList<Accounts>();
			
			if(totalAssesment.length > 1){
				for(int i=1;i<totalAssesment.length;i++){
					int update = accountsDAO.updateChargeType(totalAssesment[i],chargeType);
	
				}
			}else{
				invoiceList = accountsDAO.getChargesTempInvoiceList("Charge" ,accountsForm.getClientId(),loginInfo.getLoginsessionid());
				for(Accounts accounts : invoiceList){
					int update = accountsDAO.updateChargeType(Integer.toString(accounts.getInvoiceid()),chargeType);
				}
			}
			
			int delete = accountsDAO.deleteChargeAccounts(loginInfo.getLoginsessionid());
			if(totalAssesment.length>1){
				for(int i=1;i<totalAssesment.length;i++){
					int result = accountsDAO.saveTempChargeAccounts(Integer.parseInt(totalAssesment[i]),loginInfo.getLoginsessionid());
				}
			}else{
				
				for(Accounts accounts : invoiceList){
					int result = accountsDAO.saveTempChargeAccounts(accounts.getInvoiceid(),loginInfo.getLoginsessionid());
				}
			}
			
			addActionMessage(""+accountsForm.getClient()+" Invoice Raised successfully!!");
			setFormData();
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			
			connection.close();
		}
		
		return "createinvoicecharges";
	}
	
	
	public String casdesk() throws Exception{
		if(!verifyLogin(request)){
			return "login";
		}
		Connection connection = null;
		DateTimeUtils dateTimeUtils = new DateTimeUtils();
		try{
			
			connection = Connection_provider.getconnection();
			AccountsDAO accountsDAO = new JDBCAccountsDAO(connection);
			
			//String allChargeList = accountsDAO.getTempChagesInvoiceStringData(loginInfo.getLoginsessionid());
			String allChargeList="";
			if(!DateTimeUtils.isNull(accountsForm.getSelectedCharges()).equals("")){
				allChargeList = accountsForm.getSelectedCharges();
			}else{
				allChargeList = accountsDAO.getTempChagesInvoiceStringData(loginInfo.getLoginsessionid());
			}
			
			boolean flag =false;
			flag = accountsDAO.checkChargeStatus(allChargeList);
			if(flag){
				return "errorcharge";
			}
			
			
			//String assmtid=accountsForm.getSelectedinvcharge();
			ArrayList<Accounts>accountList = accountsDAO.getCashdeskChargeList(allChargeList,accountsForm.getClientId(),loginInfo);
			
			double debitTotal = 0.0;
			double balanceTotal = 0.0;
			double creditTotal = 0.0;
			
			for(Accounts accounts:accountList){
				debitTotal = debitTotal + accounts.getDebitAmount();
				creditTotal = creditTotal + accounts.getCreditTotal();
				
			}
			
			ArrayList<Master>ledgerservicesList = accountsDAO.getcashdeskLedgerServicesList(allChargeList,accountsForm.getClientId());
			accountsForm.setLedgerservicesList(ledgerservicesList);
			session.setAttribute("cashledgerservicesList", ledgerservicesList);
			
			accountsForm.setAccountList(accountList);
			accountsForm.setDebitTotal(debitTotal);
			accountsForm.setBalanceTotal(debitTotal);
			accountsForm.setNumberOfChages(accountList.size());
			
			//Decimal Account
			accountsForm.setDebitTotalx(dateTimeUtils.changeFormat(debitTotal));
			accountsForm.setBalanceTotalx(dateTimeUtils.changeFormat(debitTotal));
			accountsForm.setCreditTotalx(dateTimeUtils.changeFormat(creditTotal));

			
			if(!accountsForm.getLocation().equals("")){
				String locationName = accountsDAO.getLocationName(accountsForm.getLocation());
				accountsForm.setLocationName(locationName);
			}
			
			
			
		
			
			//set view-payment mode
			
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
			
			//  06 dec 2017 set dr name list 
			Bed bed = new Bed();
			NotAvailableSlotDAO notAvailableSlotDAO = new JDBCNotAvailableSlotDAO(connection);
			BedDao bedDao = new JDBCBedDao(connection);
			IpdDAO ipdDAO = new JDBCIpdDAO(connection);
			int apptid=0;
			String practid = "0";
			//int ipdid = ipdDAO.getLastIpdId(accountsForm.getClientId());
			int ipdid = 0;
			if(!DateTimeUtils.isNull(accountsForm.getIpdidnew()).equals("")){
				ipdid = Integer.parseInt(DateTimeUtils.numberCheck(accountsForm.getIpdidnew()));
				accountsForm.setIpdidnew(""+ipdid);
				if(ipdid>0){
					accountsForm.setIspreiousipdid(true);
				}else{
					accountsForm.setIspreiousipdid(false);
				}
			}else{
				ipdid = ipdDAO.getLastIpdId(accountsForm.getClientId());
				accountsForm.setIspreiousipdid(false);
			}
			if(ipdid>0){
				bed = bedDao.getEditIpdData(String.valueOf(ipdid));
				practid = bed.getPractitionerid();
			}else{
				apptid = notAvailableSlotDAO.getLastAppointmentId(accountsForm.getClientId());
				if(apptid>0){
					practid=notAvailableSlotDAO.getDrApptId(apptid);
				}
			}
			ArrayList<DiaryManagement> userList = notAvailableSlotDAO.getUserAccountList(loginInfo.getClinicid());
			accountsForm.setUserList(userList);
			accountsForm.setDoctorid(practid);
			String autoselect=accountsForm.getAutoselect();
			if(autoselect==null){
				autoselect="0";
			}
			
			
			if(autoselect.equals("1")){
				int res=accountsDAO.getidforlist("IPD");
				accountsForm.setInvcetype(String.valueOf(res));
				
			}
			if(autoselect.equals("2")){
				int res=accountsDAO.getidforlist("OPD");
				accountsForm.setInvcetype(String.valueOf(res));
				
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			
			connection.close();
		}
		
		return "cashdesk";
		
	}
	
	public String savecash() throws Exception{
		if(!verifyLogin(request)){
			return "login";
		}
		Connection connection = null;
		try{
			
			connection = Connection_provider.getconnection();
			AccountsDAO accountsDAO = new JDBCAccountsDAO(connection);
			AccountLogDAO accountLogDAO = new JDBCAccountLogDAO(connection);
			ChargesAccountProcessingDAO chargesAccountProcessingDAO = new JDBCChargeAccountProcesDAO(connection);
			IpdDAO ipdDAO = new JDBCIpdDAO(connection);
			String creditDebit = accountsForm.getCreditDebitCharge();
			String creditId = accountsForm.getCreditChargeId();
			String date_time = DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone());
			String sessioncount=accountsForm.getSessioncount();
			if(!accountsForm.getAppointmentid().equals("")){
				String location = accountsDAO.getChargeLocation(accountsForm.getAppointmentid());
				accountsForm.setLocation(location);
			}
			
			if(creditDebit.equalsIgnoreCase("1")){
				AdditionalDAO additionalDAO = new JDBCAdditionalDAO(connection);
				
				
				/*int update = additionalDAO.updateCredit(accountsForm.getPayby(),accountsForm.getDebitTotal(),accountsForm.getSubmitInvoiceNotes(),accountsForm.getHowpaid(),creditId,balance);
				
				ArrayList<Accounts>creditList = additionalDAO.getCreditAccountList(accountsForm.getClientId());
				
				accountsForm.setCreditList(creditList);
				
				double credit = 0;
				
				for(Accounts accounts : creditList){
					credit = credit + Double.parseDouble(accounts.getCharges());
				}
				
				accountsForm.setCreditTotalx(DateTimeUtils.changeFormat(credit));*/
				
				//int res = additionalDAO.saveCreditRecord(acc, type, date, creditnote, payBuy, charge, paymode, balance)
				
				String clientId  = accountsForm.getClientId();
				String date = request.getParameter("invoiceDate");
				
				if(date==null){
					date = DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone());
				} else if(date.equals("")){
					date = DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone());
				} else {
					date = DateTimeUtils.getCommencingDate1(date);
					date = date +" "+DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone()).split(" ")[1];
				}
				
				ClientDAO clientDAO = new JDBCClientDAO(connection);
				Client client = clientDAO.getClientDetails(clientId);
				String clientname = client.getTitle() + " " +client.getFirstName() + " " + client.getLastName();
				
				
				String type = "Pre_Payment";
				String creditnote = "";
				String charge = accountsForm.getAmount();
				String payBuy = accountsForm.getPayby();
				String paymode = accountsForm.getHowpaid();
				;
				double balance = additionalDAO.getCreditTotal(clientId);
				
				if(accountsForm.getAdvref()==0){
					
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
					
					balance = balance + Double.parseDouble(charge);
					int crinvoiceid = additionalDAO.saveCreditRecord(clientId, type, date, creditnote,payBuy,charge,paymode,balance,rinv,loginInfo.getUserId(),0);
					
					additionalDAO.createSeqnogenProccessForAdvAndRef(accountsForm.getInvcetype(), ""+crinvoiceid, paymode,"");
					//lokesh
					if(crinvoiceid>0){
					int pr= additionalDAO.setPractionerinCreditacc(crinvoiceid, accountsForm.getDoctorid());
					}
					ArrayList<CompleteAppointment> assesmentList = additionalDAO.getCompleteApmtList(clientId,loginInfo.getId());
					if (assesmentList.size() > 0) {
						for (CompleteAppointment appointment : assesmentList) {
							int result = additionalDAO.saveCreditAssessment(clientId, clientname, type, date, crinvoiceid,appointment);
						}
					}
					int save = additionalDAO.saveCreditAssessmentRecord(clientId, "", type, date, crinvoiceid,charge,accountsForm.getAdvref());
					
					int maxno = additionalDAO.getMaxAdvno(crinvoiceid);
					String invoicetype = "Advance";
					int paymentids = 0;
					//for ayushman check invoiceid
					boolean checkceinvoieid=additionalDAO.checkcrinvoiceidexist(crinvoiceid);
					if(!checkceinvoieid) {
					  int u = additionalDAO.updateAdvMaxno(crinvoiceid,maxno,invoicetype,paymentids);
					}
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
					
					//Sms to Patient
				    ClinicDAO clinicDAO=new JDBCClinicDAO(connection);
				    Clinic clinic=clinicDAO.getCliniclistDetails(loginInfo.getClinicid());
				    boolean isPaymentSms= clinicDAO.isPaymentSMSActive(loginInfo.getClinicid());
				    if(loginInfo.isAdv_payamnt_sms()){
				     UserProfileDAO userProfileDAO = new JDBCUserProfileDAO(connection);
					 String templateid = userProfileDAO.getSMSTemplateID("Payment");
				     String msg="Thanks "+clientname+" for payment of Rupees "+charge+" from- "+clinic.getClinicName()+"";
				     SmsService service= new SmsService();
				     service.sendSms(msg, client.getMobNo(), loginInfo, new EmailLetterLog(),templateid);
				    
				    }
				}else{
					
					//reset invoice
					int resetinv = accountsDAO.getMaxResetInv();
					int resetcreditinv = accountsDAO.getMaxResetCreditInv();
					int rinv = 0;
					if(resetinv>resetcreditinv){
						rinv = resetinv + 1;
					}else{
						rinv = resetcreditinv + 1;
					}
					
					String manualinvoiceid = (String)session.getAttribute("manualinvoiceid");
					String refundnote = (String)session.getAttribute("refundnote");
					String refundrequestid = (String)session.getAttribute("refundrequestid");
					
					if(manualinvoiceid!=null){
						if(!manualinvoiceid.equals("")){
							int crinvoiceid = additionalDAO.saveDebitRecord(clientId, type, date, creditnote,payBuy,charge,paymode,balance,accountsForm.getAdvref(),rinv,loginInfo.getUserId(),manualinvoiceid,refundnote,"",accountsForm.getInvcetype(),loginInfo,null);
							int save = additionalDAO.saveCreditAssessmentRecord(clientId, "", type, date, crinvoiceid,charge,accountsForm.getAdvref());
							
							additionalDAO.createSeqnogenProccessForAdvAndRef(accountsForm.getInvcetype(), ""+crinvoiceid, paymode,"");
							
							//Refund Ledger
							if(crinvoiceid>0){
								int maxno = additionalDAO.getMaxAdvno(crinvoiceid);
								String invoicetype = "Refund";
								int paymentids = 0;
								boolean checkceinvoieid=additionalDAO.checkcrinvoiceidexist(crinvoiceid);
								if(!checkceinvoieid) {
								  int u = additionalDAO.updateAdvMaxno(crinvoiceid,maxno,invoicetype,paymentids);
								}
								
								
								String serviceid = chargesAccountProcessingDAO.getLedgrServiceIds("Refund");
								String ledgerid = chargesAccountProcessingDAO.getledgerID(serviceid,paymode,accountsForm.getBnkname());
								
								double lbal = chargesAccountProcessingDAO.getLedgerBalance(ledgerid);
								lbal = lbal + Double.parseDouble(charge);
								String credit = charge;
								String ldebit = "0";
								String product = "Refund";
								String partyid = clientId;
								String lcommencing = DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone());
								int saveledger = chargesAccountProcessingDAO.saveLedger(partyid,product,ldebit,credit,lbal,ledgerid,lcommencing,manualinvoiceid,0,"0","0","0","0","0",0,0,"0");
								
								//second effect
								lbal = 0;
								 credit = "0";
								 ldebit = charge;
								 product = "Refund";
								 partyid = clientId;
								 lcommencing = DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone());
								 saveledger = chargesAccountProcessingDAO.saveLedger(partyid,product,ldebit,credit,lbal,ledgerid,lcommencing,manualinvoiceid,0,"0","0","0","0","0",0,0,"0");
								
							}
						
							if(refundrequestid!=null){
								if(!refundrequestid.equals("")){
									 String datetime = DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone());
									int res = additionalDAO.updateRefundRequest(refundrequestid,loginInfo.getUserId(),datetime);
									ArrayList<String>  arrayList = additionalDAO.getRefundRequestId(refundrequestid);
									for (String string : arrayList) {
										int rres = additionalDAO.updateRefundDeleteInvoice(string,datetime,loginInfo.getUserId());
									}
									int ress = additionalDAO.updateDebitRecordRefundId(crinvoiceid,refundrequestid);
									
									// reset appointment status
									//  01-08-2019 color change of OPD appointment and that not needed confirm from adarsh sir and dipanjay sir
									 //int appointmentid = accountsDAO.getPaymentAppoinetmentId(manualinvoiceid);
									 //int upstatus = accountsDAO.resetOpdStatus(appointmentid,0);

									
								}
							}
							session.removeAttribute("manualinvoiceid");
							session.removeAttribute("refundnote");
							session.removeAttribute("refundrequestid");
						}else{
							balance = balance - Double.parseDouble(charge);
							int crinvoiceid = additionalDAO.saveDebitRecord(clientId, type, date, creditnote,payBuy,charge,paymode,balance,accountsForm.getAdvref(),rinv,loginInfo.getUserId(),manualinvoiceid,refundnote,"",accountsForm.getInvcetype(),loginInfo,null);
							int save = additionalDAO.saveCreditAssessmentRecord(clientId, "", type, date, crinvoiceid,charge,accountsForm.getAdvref());
							
							additionalDAO.createSeqnogenProccessForAdvAndRef(accountsForm.getInvcetype(), ""+crinvoiceid, paymode,"");
							//  03 Aug 2018 initial againest advance refund
							int maxno = additionalDAO.getMaxAdvno(crinvoiceid);
							String invoicetype = "Refund";
							int paymentids = 0;
							/*
							 * boolean invoiceid_exist=additionalDAO.InviceidPresentOrNot(crinvoiceid);
							 * 
							 * if(!invoiceid_exist) { //for ayushman int u
							 * =additionalDAO.updateAdvMaxno(crinvoiceid,maxno,invoicetype,paymentids); }
							 */
							 
							// commenting beacuse entry repeated in ayushman
							boolean checkceinvoieid=additionalDAO.checkcrinvoiceidexist(crinvoiceid);
							if(!checkceinvoieid) {
							   int u = additionalDAO.updateAdvMaxno(crinvoiceid,maxno,invoicetype,paymentids);
							}
							//  28 May 2018 Its not change refund from advance -> pay button
							if(refundrequestid!=null){
								if(!refundrequestid.equals("")){
									 String datetime = DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone());
									int res = additionalDAO.updateRefundRequest(refundrequestid,loginInfo.getUserId(),datetime);
									int ress = additionalDAO.updateDebitRecordRefundId(crinvoiceid,refundrequestid);
								}
							}
							session.removeAttribute("manualinvoiceid");
							session.removeAttribute("refundnote");
							session.removeAttribute("refundrequestid");
							
						}
						
					}else{
						balance = balance - Double.parseDouble(charge);
						int crinvoiceid = additionalDAO.saveDebitRecord(clientId, type, date, creditnote,payBuy,charge,paymode,balance,accountsForm.getAdvref(),rinv,loginInfo.getUserId(),manualinvoiceid,refundnote,"",accountsForm.getInvcetype(),loginInfo,null);
						int save = additionalDAO.saveCreditAssessmentRecord(clientId, "", type, date, crinvoiceid,charge,accountsForm.getAdvref());
						
						additionalDAO.createSeqnogenProccessForAdvAndRef(accountsForm.getInvcetype(), ""+crinvoiceid, paymode,"");
						int maxno = additionalDAO.getMaxAdvno(crinvoiceid);
						String invoicetype = "Refund";
						int paymentids = 0;
						boolean checkceinvoieid=additionalDAO.checkcrinvoiceidexist(crinvoiceid);
						if(!checkceinvoieid) {
						  int u = additionalDAO.updateAdvMaxno(crinvoiceid,maxno,invoicetype,paymentids);
						}
						
						//  28 May 2018 Its not change refund from advance -> pay button
						if(refundrequestid!=null){
							if(!refundrequestid.equals("")){
								 String datetime = DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone());
								int res = additionalDAO.updateRefundRequest(refundrequestid,loginInfo.getUserId(),datetime);
								int ress = additionalDAO.updateDebitRecordRefundId(crinvoiceid,refundrequestid);
							}
						}
						session.removeAttribute("manualinvoiceid");
						session.removeAttribute("refundnote");
						session.removeAttribute("refundrequestid");
					}
					
					
				}
				CommonOpdIpdReport commonOpdIpdReport=new CommonOpdIpdReport();
				commonOpdIpdReport.patientTranssection(accountsForm.getClientId(),date_time);
				
				return "showcredit";
			}
			
			String allChargeList = accountsForm.getTotalassesment();

			boolean flag =false;
			flag = accountsDAO.checkChargeStatus(allChargeList);
			if(flag){
				return "errorcharge";
			}
			
			String totalAssesment[] = accountsForm.getTotalassesment().split(",");
			String chargeType = "Submit";
			
			String temp[] = accountsForm.getInvoiceDate().split("-");
			String commencing = temp[2] + "-" + temp[1] + "-" + temp[0];
			//double debit = accountsForm.getDebitTotal();
			//  11-10-2019 for zero amount invoice
			double debit = Double.parseDouble(DateTimeUtils.numberCheck(accountsForm.getDebitTotalx()));
			double discount=Double.parseDouble(accountsForm.getDiscount());
			
			ArrayList<Accounts>invoiceList = new ArrayList<Accounts>();
			int thirdPartyID = accountsDAO.getThirdPartyID(accountsForm.getClientId());
			
			//reset invoice
			int resetinv = accountsDAO.getMaxResetInv();
			int resetcreditinv = accountsDAO.getMaxResetCreditInv();
			int rinv = 0;
			if(resetinv>resetcreditinv){
				rinv = resetinv + 1;
			}else{
				rinv = resetcreditinv + 1;
			}
			String drid = accountsForm.getDoctorid();
			int ipdid=0;
			if(accountsForm.getInvcetype()!=null){
				if(accountsForm.getInvcetype().equals("2")||accountsForm.getInvcetype().equals("8")){
					//ipdid = ipdDAO.getLastIpdId(accountsForm.getClientId());
					if(accountsForm.isIspreiousipdid()){
						ipdid = Integer.parseInt(DateTimeUtils.numberCheck(accountsForm.getIpdidnew()));
					}else{
						ipdid = ipdDAO.getLastIpdId(accountsForm.getClientId());
					}
				}else{
					ipdid = 0;
				}
			}else{
				ipdid = 0;
			}
			int invoiceid=0;
			if(loginInfo.isLmh()) {
			   invoiceid = accountsDAO.saveChargesInvoice(accountsForm.getPayby(),commencing,
						Integer.parseInt(accountsForm.getClientId()),debit,discount,
						accountsForm.getSubmitInvoiceNotes(),thirdPartyID,accountsForm.getLocation(),
						DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone()),loginInfo.getId(),
						accountsForm.getInvoicepstype(),accountsForm.getInvcetype(),rinv,
						null,null,accountsForm.getDoctorid(),ipdid,0,sessioncount);
			}else {
				//logininfo parameter added 
				invoiceid = accountsDAO.saveChargesInvoice(accountsForm.getPayby(),commencing,
						Integer.parseInt(accountsForm.getClientId()),debit,discount,
						accountsForm.getSubmitInvoiceNotes(),thirdPartyID,accountsForm.getLocation(),
						DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone()),loginInfo.getId(),
						accountsForm.getInvoicepstype(),accountsForm.getInvcetype(),rinv,
						null,null,accountsForm.getDoctorid(),ipdid,0,sessioncount,loginInfo);
			}
			
			/*int invoiceid = accountsDAO.saveChargesInvoice(accountsForm.getPayby(),commencing,
					Integer.parseInt(accountsForm.getClientId()),debit,discount,
					accountsForm.getSubmitInvoiceNotes(),thirdPartyID,accountsForm.getLocation(),
					DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone()),loginInfo.getId(),
					accountsForm.getInvoicepstype(),accountsForm.getInvcetype(),rinv,
					null,null,accountsForm.getDoctorid(),ipdid,0,sessioncount);*/
			
			 if(invoiceid>0){
				 String itype = accountsDAO.getInvoiceTypeId(invoiceid);
				  //String serviceid = chargesAccountProcessingDAO.getLedgrServiceIds(itype);
				 String serviceid = itype;
					String ledgerid = chargesAccountProcessingDAO.getledgerID(serviceid,"0","0");
					
					double lbal = chargesAccountProcessingDAO.getLedgerBalance(ledgerid);
					lbal = lbal + debit;
					String credit = ""+debit+"";
					String ldebit = "0";
					String product = "xxxxx";
					String partyid = accountsForm.getClientId();
					String lcommencing = DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone());
					int saveledger = chargesAccountProcessingDAO.saveLedger(partyid,product,ldebit,credit,lbal,ledgerid,lcommencing,""+invoiceid+"",0,"0","0","0","0","0",0,0,"0");
					
					//second effect
					lbal = 0;
				    credit = "0";
				    ldebit = ""+debit+"";
					 product = "xxxxx";
					 partyid = accountsForm.getClientId();
					 lcommencing = DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone());
					 saveledger = chargesAccountProcessingDAO.saveLedger(partyid,product,ldebit,credit,lbal,ledgerid,lcommencing,""+invoiceid+"",0,"0","0","0","0","0",0,0,"0");
					
			  }
			//  28 Aug 2018 for parking charges
			 if(accountsForm.getAppointmentid().equals("")){
					
					if(totalAssesment.length>1){
						for(int i=1;i<totalAssesment.length;i++){
							int save = accountsDAO.saveChargesAssesment(Integer.parseInt(totalAssesment[i]),invoiceid);
							int update = accountsDAO.updateChargeType(totalAssesment[i],chargeType);
							
							//int update1 = accountLogDAO.updateChargeType(totalAssesment[i],chargeType);
			
						}
					}else{
						invoiceList = accountsDAO.getCashdeskChargesTempInvoiceList(accountsForm.getClientId());
							for(Accounts accounts : invoiceList){
								int save = accountsDAO.saveChargesAssesment(accounts.getInvoiceid(),invoiceid);
								int update = accountsDAO.updateChargeType(Integer.toString(accounts.getInvoiceid()),chargeType);
							
								//int update1 = accountLogDAO.updateChargeType(Integer.toString(accounts.getInvoiceid()),chargeType);
							}
						}
					
				}else{
					
					String chargeInvoiceid = accountsDAO.getAppointmentInvoiceid(accountsForm.getAppointmentid());
					int save = accountsDAO.saveChargesAssesment(Integer.parseInt(chargeInvoiceid),invoiceid);
					int update = accountsDAO.updateChargeType(chargeInvoiceid,chargeType);
				}
			 
			//  28 Aug 2018 set parking charges
			 double parkingcharge = accountsDAO.checkParkingChargeApplied(invoiceid);
				if(parkingcharge>0){
					double discamt = Double.parseDouble(accountsForm.getDiscount());
					if(accountsForm.getDisctype().equals("0")){
						discamt = (debit * Double.parseDouble(accountsForm.getDiscount())) /100;
					}
					discamt = discamt+parkingcharge;
					int res = accountsDAO.updateDiscountWithParking(invoiceid,discamt);
					if(res>0){
						accountsForm.setDiscount(DateTimeUtils.changeFormat(discamt));
						accountsForm.setDisctype("1");
					}
				}
			
				//  07 Sep 2018 
				if(accountsForm.getInvcetype()!=null){
					DateFormat dateFormat = new SimpleDateFormat("yyyy");
					Calendar cal = Calendar.getInstance();
					String a_year = dateFormat.format(cal.getTime());
					
					if(accountsForm.getInvcetype().equals("1")||accountsForm.getInvcetype().equals("0")||accountsForm.getInvcetype().equals("8")){
						int res = accountsDAO.getMaxOpdseqNo(a_year);
						res =  res+1;
						int ress = accountsDAO.updateInvoiceSeqNo("1",res,invoiceid,a_year);
					}else if(accountsForm.getInvcetype().equals("2")){
						int res = accountsDAO.getMaxIpdseqNo(a_year);
						res =  res+1;
						int ress = accountsDAO.updateInvoiceSeqNo("2",res,invoiceid,a_year);
					}else if(accountsForm.getInvcetype().equals("3")){
						int res = accountsDAO.getMaxInvstseqNo(a_year);
						res =  res+1;
						int ress = accountsDAO.updateInvoiceSeqNo("3",res,invoiceid,a_year);
					}else if(accountsForm.getInvcetype().equals("4")){
						int res = accountsDAO.getMaxMedseqNo(a_year);
						res =  res+1;
						int ress = accountsDAO.updateInvoiceSeqNo("4",res,invoiceid,a_year);
					}else if(accountsForm.getInvcetype().equals("5")){
						int res = accountsDAO.getMaxAdvnRefseqNo(a_year);
						res =  res+1;
						int ress = accountsDAO.updateInvoiceSeqNo("5",res,invoiceid,a_year);
					}else if(accountsForm.getInvcetype().equals("6")){
						int res = accountsDAO.getMaxVaccinationseqNo(a_year);
						res =  res+1;
						int ress = accountsDAO.updateInvoiceSeqNo("6",res,invoiceid,a_year);
					}
					
				}
			//save log
			int invoiceid1 = accountLogDAO.saveChargesInvoice(accountsForm.getPayby(),commencing,Integer.parseInt(accountsForm.getClientId()),debit,discount,accountsForm.getSubmitInvoiceNotes(),thirdPartyID,accountsForm.getLocation(),invoiceid,DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone()));
			//update discount
			String userid = loginInfo.getUserId();
			String datetime = DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone());
			int p = accountsDAO.updatePercentageAmount(Integer.toString(invoiceid),accountsForm.getDiscount(),accountsForm.getDisctype(),userid,datetime);
			
			//discount ledger
			
			double discdebit = Double.parseDouble(accountsForm.getDiscount());
			if(accountsForm.getDisctype().equals("0")){
				discdebit = (debit * Double.parseDouble(accountsForm.getDiscount())) /100;
			}
			
			if(discdebit>0){
			String serviceid = chargesAccountProcessingDAO.getLedgrServiceIds("Discount");
			String ledgerid = chargesAccountProcessingDAO.getledgerID(serviceid,"0","0");
			
			double lbal = chargesAccountProcessingDAO.getLedgerBalance(ledgerid);
			lbal = lbal + discdebit;
			String credit = ""+discdebit+"";
			String lddebit = "0";
			String product = ""+invoiceid+"";
			String partyid = accountsForm.getClientId();;
			String ldcommencing = DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone());
			int saveledger = chargesAccountProcessingDAO.saveLedger(partyid,product,lddebit,credit,lbal,ledgerid,ldcommencing,""+invoiceid+"",0,"0","0","0","0","0",0,0,"0");
			
			//second effect
			lbal = 0;
			 credit = "0";
			 lddebit = ""+discdebit+"";
			 product = ""+invoiceid+"";
			 partyid = accountsForm.getClientId();;
			 ldcommencing = DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone());
			 saveledger = chargesAccountProcessingDAO.saveLedger(partyid,product,lddebit,credit,lbal,ledgerid,ldcommencing,""+invoiceid+"",0,"0","0","0","0","0",0,0,"0");
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
				resetinv = accountsDAO.getMaxResetInv();
				resetcreditinv = accountsDAO.getMaxResetCreditInv();
				rinv = 0;
				if(resetinv>resetcreditinv){
					rinv = resetinv + 1;
				}else{
					rinv = resetcreditinv + 1;
				}
				
				creditinvoiceid = additionalDAO.saveCreditRecord(accountsForm.getClientId(), "", DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone()), accountsForm.getCreditNote(),accountsForm.getPayby(),accountsForm.getCrdAmount(),accountsForm.getHowpaid(),balance,rinv,loginInfo.getUserId(),0);
				
				int save = additionalDAO.saveCreditAssessmentRecord(accountsForm.getClientId(), "", "", DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone()), creditinvoiceid,accountsForm.getCrdAmount(),accountsForm.getAdvref());

				additionalDAO.createSeqnogenProccessForAdvAndRef(accountsForm.getInvcetype(), ""+creditinvoiceid, accountsForm.getHowpaid(),"");
			}
			
			String chequeno= request.getParameter("chequeno");
			String bankname= request.getParameter("bankname");
			
			AdditionalDAO additionalDAO = new JDBCAdditionalDAO(connection);
			 if(!accountsForm.getHdnmorehowpaid().equals("0")){
				 double cbalance = additionalDAO.getCreditTotal(accountsForm.getClientId());
				String  payAmount = ""+cbalance+"";
				accountsForm.setAmount(payAmount);
			 }
			
			 Accounts accounts = accountsDAO.getInvoiceChargesDetails(""+invoiceid);
			 
			int paymentid = accountsDAO.saveChargesPayment(accountsForm.getClientId(),
					invoiceid,accountsForm.getAmount(),accountsForm.getHowpaid(),thirdPartyID,
					accountsForm.getPaymentNote(),DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone()),
					creditinvoiceid,loginInfo.getUserId(),chequeno,bankname,accounts.getPredayornot(),
					accounts.getDate(),accounts.getItype(),"","");
			
			//update appointment status
			if(accountsForm.getAppointmentid()!=null){
				if(accountsForm.getInvcetype().equals("1")&&(accountsForm.getAppointmentid().equals(""))){
					NotAvailableSlotDAO notAvailableSlotDAO= new JDBCNotAvailableSlotDAO(connection);
					int apptid = notAvailableSlotDAO.getLastAppointmentId(accountsForm.getClientId());
					accountsForm.setAppointmentid(""+apptid);
				}
				if(!accountsForm.getAppointmentid().equals("")){
					int result1 = accountLogDAO.saveChargesPayment(accountsForm.getClientId(),invoiceid,accountsForm.getAmount(),accountsForm.getHowpaid(),thirdPartyID,accountsForm.getInvoiceDate(),DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone()),accountsForm.getPaymentNote());
					int upstatus = accountsDAO.updateOpdPaymentStatus(Integer.parseInt(accountsForm.getAppointmentid()),invoiceid);
				}
			}
			
			//update charge invoiced
			ArrayList<Master>chargeidList = accountsDAO.getInvoicedChargeidList(invoiceid);
			for(Master m : chargeidList){
				int upc = accountsDAO.updateChargeInvoideid(m.getId(),invoiceid);
			}
			
			
			
			 //add more payment
			 if(!accountsForm.getHdnmorehowpaid().equals("0")){
				Accounts accountss = accountsDAO.getInvoiceChargesDetails(""+invoiceid);
				 
				int morepaymentid =  accountsDAO.saveChargesPayment(accountsForm.getClientId(),
							invoiceid,accountsForm.getHdnmorepaudamount(),
							accountsForm.getHdnmorehowpaid(),thirdPartyID,
							accountsForm.getPaymentNote(),
							DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone()),
							creditinvoiceid,loginInfo.getUserId(),chequeno,bankname,
							accountss.getPredayornot(),accountss.getDate(),accountss.getItype(),"","");
				
				/* String itype = accountsDAO.getInvoiceTypeId(invoiceid);*/
				
				 String itype = accounts.getItype();
				
				//update invoice type payment autono
				 if(itype.equals("2")){
					 int maxno = additionalDAO.getMaxAdvno(paymentid);
						String invoicetype = "IPD";
						int paymentids = 0;
						boolean paymentid_exist=additionalDAO.PaymentidPresentOrNot(invoiceid);
						if(!paymentid_exist) {
						  int u = additionalDAO.updateAdvMaxno(invoiceid,maxno,invoicetype,paymentid);
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
					 credit = accountsForm.getHdnmorepaudamount();
					 ldebit = "0";
					 product = ""+invoiceid+"";
					 partyid = accountsForm.getClientId();
					 lcommencing = DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone());
					 saveledger = chargesAccountProcessingDAO.saveLedger(partyid,product,ldebit,credit,lbal,ledgerid,lcommencing,""+invoiceid+"",morepaymentid,"0","0","0","0","0",0,0,"0");
			 }
			 
			//ledger services
			 	
				String ledgerservicestr = accountsForm.getLedgerservicestr();
//				System.out.println(ledgerservicestr);
				String ledgertemp[] = ledgerservicestr.split(",");
				ArrayList<Master>ledgerservicesList = (ArrayList<Master>) session.getAttribute("cashledgerservicesList");
			 
				int l = 1;
				if(invoiceid>0){
						String itype = accountsDAO.getInvoiceTypeId(invoiceid);
						//String serviceid = chargesAccountProcessingDAO.getLedgrServiceIds(master.getName());
						String serviceid = itype;
						String ledgerid = chargesAccountProcessingDAO.getledgerID(serviceid,accountsForm.getHowpaid(),accountsForm.getBnkname());
						
						double lbal = chargesAccountProcessingDAO.getLedgerBalance(ledgerid);
						lbal = lbal + Double.parseDouble(accountsForm.getAmount());
						String credit = "0";
						String ldebit = accountsForm.getAmount();
						String product = "xxxxx";
						String partyid = accountsForm.getClientId();
						String lcommencing = DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone());
						int saveledger = chargesAccountProcessingDAO.saveLedger(partyid,product,ldebit,credit,lbal,ledgerid,lcommencing,""+invoiceid+"",paymentid,"0","0","0","0","0",0,0,"0");
						
						//second effect
						lbal = 0;
						 credit = accountsForm.getAmount();
						 ldebit = "0";
						 product = "xxxxx";
						 partyid = accountsForm.getClientId();
						 lcommencing = DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone());
						 saveledger = chargesAccountProcessingDAO.saveLedger(partyid,product,ldebit,credit,lbal,ledgerid,lcommencing,""+invoiceid+"",paymentid,"0","0","0","0","0",0,0,"0");
			 
			 
				}
				String itype = accountsDAO.getInvoiceTypeId(invoiceid);
				try {
					//update invoice type payment autono
					
					 if(itype.equals("2")){
						 int maxno = additionalDAO.getMaxAdvno(paymentid);
							String invoicetype = "IPD";
							int paymentids = 0;
						////for ayushman payment insert repeat thats why
							boolean paymentid_exist=additionalDAO.PaymentidPresentOrNot(invoiceid);
							if(!paymentid_exist) {
								int u = additionalDAO.updateAdvMaxno(invoiceid,maxno,invoicetype,paymentid);
							}
							//int u = additionalDAO.updateAdvMaxno(invoiceid,maxno,invoicetype,paymentid);
							 
					 }else{
						    int maxno = accountsDAO.getMaxInvoiceTypePaymentNo(paymentid,itype);
							int u = accountsDAO.updateInvoicetypePaymentNo(paymentid,maxno,itype);
						
					 }
				}catch (Exception e) {
					e.printStackTrace();		
					}
			
				
			//Sms to Patient
			  ClientDAO clientDAO= new JDBCClientDAO(connection);
			  Client client=clientDAO.getClientDetails(accountsForm.getClientId());
			  session.setAttribute("clientwhopaymode", client.getWhopay());
			  String clientname= client.getTitle()+" "+client.getFirstName()+" "+client.getLastName();
			  ClinicDAO clinicDAO=new JDBCClinicDAO(connection);
			  Clinic clinic=clinicDAO.getCliniclistDetails(loginInfo.getClinicid());
			  boolean isPaymentSms= clinicDAO.isPaymentSMSActive(loginInfo.getClinicid());
			  if((itype.equals("1")&&loginInfo.isOpd_payamnt_sms())||(itype.equals("2")&&loginInfo.isIpd_payamnt_sms())||(itype.equals("3")||itype.equals("6")||itype.equals(""))||(itype.equals("5")&&loginInfo.isAdv_payamnt_sms())){
				  UserProfileDAO userProfileDAO = new JDBCUserProfileDAO(connection);
				  String templateid = userProfileDAO.getSMSTemplateID("Payment");
				  String msg="Thanks "+clientname+" for payment of Rupees "+accountsForm.getAmount()+" from- "+clinic.getClinicName()+"";
				  SmsService service= new SmsService();
				  service.sendSms(msg, client.getMobNo(), loginInfo, new EmailLetterLog(),templateid);
			  
			  }
			
			
			session.setAttribute("chargesInvoiceid", invoiceid);
			session.setAttribute("clientId", accountsForm.getClientId());
			session.setAttribute("payAmount", accountsForm.getAmount());
			session.setAttribute("payby", accountsForm.getPayby());
			session.setAttribute("discount", discount);
			
			int delete = accountsDAO.deleteChargeAccounts(loginInfo.getLoginsessionid());
			
			session.setAttribute("chargesInvoiceid", invoiceid);
			session.setAttribute("clientId", accountsForm.getClientId());
			session.setAttribute("payAmount", accountsForm.getAmount());
			session.setAttribute("discount", discount);
			
			
			
			//set debit ammount
			if(accountsForm.getHowpaid().equals(Constants.PREPYMENT)){
				
				
				String clientId  = accountsForm.getClientId();
				String date = DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone());
				
				
				String type = "Pre_Payment";
				String creditnote = "";
				String charge = accountsForm.getAmount();
				String payBuy = accountsForm.getPayby();
				String paymode = accountsForm.getHowpaid();
				
				double balance = additionalDAO.getCreditTotal(clientId);
				balance = balance - Double.parseDouble(charge);
				
				//reset invoice
				 resetinv = accountsDAO.getMaxResetInv();
				 resetcreditinv = accountsDAO.getMaxResetCreditInv();
				 rinv = 0;
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
					 double refbalance = additionalDAO.getCreditTotal(clientId);
					 if(refbalance>0){
						resetinv = accountsDAO.getMaxResetInv();
						resetcreditinv = accountsDAO.getMaxResetCreditInv();
						rinv = 0;
						if(resetinv>resetcreditinv){
							rinv = resetinv + 1;
						}else{
							rinv = resetcreditinv + 1;
						}
						
						balance = 0;
						charge = Double.toString(refbalance);
						crinvoiceid = additionalDAO.saveDebitRecord(clientId, type, date, creditnote,payBuy,charge,paymode,balance,accountsForm.getAdvref(),rinv,loginInfo.getUserId(),null,null,String.valueOf(invoiceid),accountsForm.getInvcetype(),loginInfo,accountsForm.getRefpayMode());
						int prepaymentid1=additionalDAO.getprepaymentid(String.valueOf(invoiceid));
						int updt=additionalDAO.updateclientAdvance(prepaymentid1,String.valueOf(invoiceid));
						save = additionalDAO.saveCreditAssessmentRecord(clientId, "", type, date, crinvoiceid,charge,accountsForm.getAdvref());
						additionalDAO.createSeqnogenProccessForAdvAndRef(accountsForm.getInvcetype(), ""+crinvoiceid, paymode,String.valueOf(invoiceid));
						upd = additionalDAO.updateRefundInvoice(Integer.toString(invoiceid),crinvoiceid);
						
						
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
							int saveledger = chargesAccountProcessingDAO.saveLedger(partyid,product,ldebit,credit,lbal,ledgerid,lcommencing,""+invoiceid+"",0,"0","0","0","0","0",0,0,"0");
							
							//second effect
							lbal = 0;
							credit = "0";
							ldebit = charge;
							product = "Refund";
							partyid = clientId;
							lcommencing = DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone());
							saveledger = chargesAccountProcessingDAO.saveLedger(partyid,product,ldebit,credit,lbal,ledgerid,lcommencing,""+invoiceid+"",0,"0","0","0","0","0",0,0,"0");
						}
					}
				}
					 
				
			}
			
			 CommonOpdIpdReport commonOpdIpdReport=new CommonOpdIpdReport();
			 commonOpdIpdReport.patientTranssection(accountsForm.getClientId(),date_time);
			 if(accountsForm.getClraradv().equals("1")){
				 int advupdate=accountsDAO.updateAdvancerefundzero(accountsForm.getClientId());
			 }
			 
			/*int res = chargesAccountProcessingDAO.getMobStatusFromAppoitmentId(accountsForm.getAppointmentid(), accountsForm.getClientId());
			if(res==1){
				sendMailToPatientVideoConferencing(client,accountsForm.getAppointmentid());
			}*/
//			session.setAttribute("previewstatus", "1");
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			
			connection.close();
		}
		if(accountsForm.getInvcetype().equals("18") || accountsForm.getInvcetype().equals("15")) {              //for balgopal physio ipd opd
			return "savecashphysioipd";
		}else {
			return "savecash";
		}
		//return "savecash";
		
	}
	
	private void sendMailToPatientVideoConferencing(Client client, String appointmntid) throws Exception {

		Connection connection = null;
		
		try{
			connection = Connection_provider.getconnection();
			NotAvailableSlotDAO notAvailableSlotDAO = new JDBCNotAvailableSlotDAO(connection);
			UserProfileDAO userProfileDAO1 = new JDBCUserProfileDAO(connection);
			NotAvailableSlot notAvailableSlot = notAvailableSlotDAO.getAvailableSlotdata(DateTimeUtils.convertToInteger(appointmntid));
			
			String drid = ""+notAvailableSlot.getDiaryUserId();
			
			String speciality = userProfileDAO1.getSpeciality(drid);
			
			Connection connection1 = DriverManager.getConnection(""+Constants.DB_HOST+":3306/"+loginInfo.getClinicUserid()+"",""+Constants.DB_USER+"",""+Constants.DB_PWD+"");
			UserProfileDAO userProfileDAO = new JDBCUserProfileDAO(connection1);
			String linkaddress = userProfileDAO.getClinicLinkAddress(loginInfo.getClinicUserid());
			
			String to = client.getEmail();
			String cc = "";
			String subject = "OPD";
			String notes = ""+notAvailableSlot.getClientName()+" your appointment has been confirm for telemed conference call "
					+ "with "+notAvailableSlot.getDiaryUser()+" on date "+DateTimeUtils.getCommencingDate1(notAvailableSlot.getCommencing())+" in between "+notAvailableSlot.getsTime()+" and "+notAvailableSlot.getEndTime()+". Please click on below link. ";
			//notes = notes+" "+"https://"+linkaddress+":8443/YUVICARE/Pureseva?title=&firstname=&lastname=&email=&clinicid="+loginInfo.getClinicUserid()+"&mob=&date=&diaryuserid="+drid+"&gender=&dob=&uhid="+client.getAbrivationid()+"&dept="+speciality+"";
			notes = notes+" "+"<a href='https://"+linkaddress+":8443/YUVICARE/Pureseva?title=&firstname=&lastname=&email=&clinicid="+loginInfo.getClinicUserid()+"&mob=&date=&diaryuserid="+drid+"&gender=&dob=&uhid="+client.getAbrivationid()+"&dept="+speciality+"'><button style='background-color: #008CBA;border: none;color: white;padding: 15px 32px;text-align: center;text-decoration: none;display: inline-block;font-size: 16px;'>Join Video Call</button></a>";
			DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
			Date date = new Date();
			String d1 = dateFormat.format(date);
			String[] temp = d1.split("\\s+");
			String date1 = temp[0];
			String time = temp[1];
			
			String type = "Letter";
			int appointmentid = 0,invoiceid = 0;
			EmailLetterLog emailLetterLog = new EmailLetterLog();
			emailLetterLog.setAppointmentid(appointmentid);
			emailLetterLog.setInvoiceid(invoiceid);
			emailLetterLog.setType(type);
			
			SmsService s = new SmsService();
			String message =""+notAvailableSlot.getClientName()+" your appointment has been confirm for telemed conference call "
					+ "with "+notAvailableSlot.getDiaryUser()+" on date "+DateTimeUtils.getCommencingDate1(notAvailableSlot.getCommencing())+" in between "+notAvailableSlot.getsTime()+" and "+notAvailableSlot.getEndTime()+". Please find link sent on your register email.";
			String templateid="";
			s.sendSms(message, client.getMobNo(), loginInfo, new EmailLetterLog(),templateid);
			
			
			EmbeddedImageEmailUtil.sendMail(connection, loginInfo.getId(), to, cc, subject, notes,loginInfo,emailLetterLog);
			
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
		
	
		
	}

	//submit invoice
	public String raisesubmitinvoice() throws Exception{
		if(!verifyLogin(request)){
			return "login";
		}
		Connection connection = null;
		DateTimeUtils dateTimeUtils = new DateTimeUtils();
		try{
			
			connection = Connection_provider.getconnection();
			AccountsDAO accountsDAO = new JDBCAccountsDAO(connection);
			
			
			//String allChargeList = accountsDAO.getTempChagesInvoiceStringData(loginInfo.getLoginsessionid());
			
			String allChargeList ="";
			if(!DateTimeUtils.isNull(accountsForm.getSelectedCharges()).equals("")){
				allChargeList = accountsForm.getSelectedCharges();
			}else{
				allChargeList = accountsDAO.getTempChagesInvoiceStringData(loginInfo.getLoginsessionid());
			}
			
			accountsForm.setSelectedCharges(allChargeList);
			
			boolean flag =false;
			flag = accountsDAO.checkChargeStatus(allChargeList);
			if(flag){
				return "errorcharge";
			}
			
			StringBuffer chargesStr = new StringBuffer();
			
			String chargeType = "Invoice";
			ArrayList<Accounts>accountList = accountsDAO.getChargesInvoiceList(allChargeList,chargeType);
			
			double debitTotal = 0.0;
			double balanceTotal = 0.0;
			double creditTotal = 0.0;
			
			for(Accounts accounts:accountList){
				debitTotal = debitTotal + accounts.getDebitAmount();
				creditTotal = creditTotal + accounts.getCreditTotal();
			}
			
			accountsForm.setAccountList(accountList);
			accountsForm.setDebitTotal(debitTotal);
			accountsForm.setBalanceTotal(debitTotal);
			accountsForm.setNumberOfChages(accountList.size());
			
			//Decimal Account
			accountsForm.setDebitTotalx(dateTimeUtils.changeFormat(debitTotal));
			accountsForm.setBalanceTotalx(dateTimeUtils.changeFormat(debitTotal));
			accountsForm.setCreditTotalx(dateTimeUtils.changeFormat(creditTotal));
			
			String loc = accountsForm.getLocation();
			String payby = accountsForm.getPayby();
			String clientId = accountsForm.getClientId();
			
			if(loc == null){
				loc = (String)session.getAttribute("location");
				accountsForm.setLocation(loc);
			}
			if(loc.equals("")){
				loc = (String)session.getAttribute("location");
				accountsForm.setLocation(loc);
			}
			if(payby == null || payby.equalsIgnoreCase("")){
				payby = (String)session.getAttribute("payby");
				accountsForm.setPayby(payby);
			}
			if(clientId == null || clientId.equalsIgnoreCase("")){
				clientId = (String)session.getAttribute("clientId");
				accountsForm.setClientId(clientId);
			}
			if(!accountsForm.getLocation().equals("")){
				String locationName = accountsDAO.getLocationName(accountsForm.getLocation());
				accountsForm.setLocationName(locationName);
			}
			
			
			if(accountsForm.getPayby().equals(Constants.PAY_BY_THIRD_PARTY)){
				ThirdParty thirdParty = accountsDAO.getTpAccountDetails(accountsForm.getClientId());
				
				if(thirdParty.getCreditDuration()!=null){
					if(!thirdParty.getCreditDuration().equals("")){
						Calendar cal = Calendar.getInstance();
						SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
						cal.add(cal.DATE, Integer.parseInt(thirdParty.getCreditDuration()));
					    String date = sdf.format(cal.getTime());
						
						String note = "This invoice must be payed by Date "+date+" ";
						accountsForm.setSubmitInvoiceNotes(note);
					}
					
				}
			
			}
			/*ClientDAO clientDAO= new JDBCClientDAO(connection);
			if(loginInfo.getClinicUserid().equals("pcsadmin")){
				accountsForm.setSubmitInvoiceNotes(loginInfo.getInvoice_default_note());
				String invoiceDefaultNote=DateTimeUtils.isNull(loginInfo.getInvoice_default_note());
				String docData=DateTimeUtils.isNull(clientDAO.documentValueFromLog("GST No",clientId));
				Client client = clientDAO.getPatient(DateTimeUtils.convertToInteger(clientId));
				int docId=clientDAO.documentId("GST No");
				if(client.getDocumentID().equals(""+docId)){
					if(!docData.equals("")){
						invoiceDefaultNote=invoiceDefaultNote.replace("GSTIN:", "GSTIN:"+docData);
						
					}
				}
				
				accountsForm.setSubmitInvoiceNotes(invoiceDefaultNote);
				
			}*/
			
			//ArrayList<Accounts> allBillList=accountsDAO.getAllMedicineBill(clientId);
			ArrayList<Accounts> allBillList = new ArrayList<Accounts>();
			accountsForm.setAllBillList(allBillList);
			
			//  06 dec 2017 set dr name list 
			Bed bed = new Bed();
			NotAvailableSlotDAO notAvailableSlotDAO = new JDBCNotAvailableSlotDAO(connection);
			BedDao bedDao = new JDBCBedDao(connection);
			IpdDAO ipdDAO = new JDBCIpdDAO(connection);
			int apptid=0;
			String practid = "0";
			int ipdid = 0;
			if(!DateTimeUtils.isNull(accountsForm.getIpdidnew()).equals("")){
				ipdid = Integer.parseInt(DateTimeUtils.numberCheck(accountsForm.getIpdidnew()));
				accountsForm.setIpdidnew(""+ipdid);
				if(ipdid>0){
					accountsForm.setIspreiousipdid(true);
				}else{
					accountsForm.setIspreiousipdid(false);
				}
			}else{
				ipdid = ipdDAO.getLastIpdId(accountsForm.getClientId());
				accountsForm.setIspreiousipdid(false);
			}
					
			if(ipdid>0){
				bed = bedDao.getEditIpdData(String.valueOf(ipdid));
				practid = bed.getPractitionerid();
			}else{
				apptid = notAvailableSlotDAO.getLastAppointmentId(accountsForm.getClientId());
				if(apptid>0){
					practid=notAvailableSlotDAO.getDrApptId(apptid);
				}
			}
			ArrayList<DiaryManagement> userList = notAvailableSlotDAO.getUserList(loginInfo.getId());
			accountsForm.setUserList(userList);
			accountsForm.setDoctorid(practid);
			String autoselect=accountsForm.getAutoselect();
			if(autoselect==null){
				autoselect="0";
			}
			
			
			if(autoselect.equals("1")){
				int res=accountsDAO.getidforlist("IPD");
				accountsForm.setInvcetype(String.valueOf(res));
				
			}
			if(autoselect.equals("2")){
				int res=accountsDAO.getidforlist("OPD");
				accountsForm.setInvcetype(String.valueOf(res));
				
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			
			connection.close();
		}
		
		return "raisesubmitinvoicecharges";
	}
	
	synchronized public String submitinvoice() throws Exception{
		if(!verifyLogin(request)){
			return "login";
		}
		Connection connection = null;
		try{
			
			connection = Connection_provider.getconnection();
			AccountsDAO accountsDAO = new JDBCAccountsDAO(connection);
			AccountLogDAO accountLogDAO = new JDBCAccountLogDAO(connection);
			String totalAssesment[] = accountsForm.getTotalassesment().split(",");
			String chargeType = "Submit";
			String allChargeList = accountsForm.getSelectedCharges();
			String date_time = DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone());
			boolean flag =false;
			flag = accountsDAO.checkChargeStatus(allChargeList);
			if(flag){
				return "errorcharge";
			}
			String fromdate=accountsForm.getFromDate();
			String todate=accountsForm.getToDate();
			
			String temp[] = accountsForm.getInvoiceDate().split("-");
			String commencing = temp[2] + "-" + temp[1] + "-" + temp[0];
			
			String debitdouble= DateTimeUtils.numberCheck(accountsForm.getDebitTotalx());
			double debit = Double.parseDouble(debitdouble);
			if(accountsForm.getDebitTotalx()!=null){
				if(accountsForm.getDebitTotalx().equals("")){
					debit = accountsForm.getDebitTotal();
				}
			}else{
				debit = accountsForm.getDebitTotal();
			}
			//double debit = accountsForm.getDebitTotal();
			double discount=0;
			
			ArrayList<Accounts>invoiceList = new ArrayList<Accounts>();
			ArrayList<Accounts>previewChargesList = new ArrayList<Accounts>();
			int thirdPartyID = accountsDAO.getThirdPartyID(accountsForm.getClientId());
			
			//reset invoice
			int resetinv = accountsDAO.getMaxResetInv();
			int resetcreditinv = accountsDAO.getMaxResetCreditInv();
			int rinv = 0;
			if(resetinv>resetcreditinv){
				rinv = resetinv + 1;
			}else{
				rinv = resetcreditinv + 1;
			}
			IpdDAO ipdDAO = new JDBCIpdDAO(connection);
			int ipdid=0;
			if(accountsForm.getInvcetype()!=null){
				if(accountsForm.getInvcetype().equals("2")||accountsForm.getInvcetype().equals("8")){
					if(accountsForm.isIspreiousipdid()){
						ipdid = Integer.parseInt(DateTimeUtils.numberCheck(accountsForm.getIpdidnew()));
					}else{
						ipdid = ipdDAO.getLastIpdId(accountsForm.getClientId());
					}
				}else{
					ipdid = 0;
				}
			}else{
				ipdid = 0;
			}
			flag = accountsDAO.checkChargeStatus(allChargeList);
			if(flag){
				return "errorcharge";
			}
			int invoiceid = accountsDAO.saveChargesInvoice(accountsForm.getPayby(),commencing,
					Integer.parseInt(accountsForm.getClientId()),debit,discount,
					accountsForm.getSubmitInvoiceNotes(),thirdPartyID,accountsForm.getLocation(),
					DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone()),
					loginInfo.getId(),accountsForm.getInvoicepstype(),accountsForm.getInvcetype(),
					rinv,fromdate,todate,accountsForm.getDoctorid(),ipdid,0,"0");
			
			
			ExpenManagementDAO expenManagementDAO = new JDBCExpenceManagementDAO(connection);
			
			 ChargesAccountProcessingDAO chargesAccountProcessingDAO = new JDBCChargeAccountProcesDAO(connection);
			//ledger for credit invoice
			 if(invoiceid>0){
				 String itype = accountsDAO.getInvoiceTypeId(invoiceid);
				  //String serviceid = chargesAccountProcessingDAO.getLedgrServiceIds(itype);
				 String serviceid = itype;
					
				 String ledgerid ="";
				 ledgerid= chargesAccountProcessingDAO.getledgerID(serviceid,"0","0"); 
					
					double lbal = chargesAccountProcessingDAO.getLedgerBalance(ledgerid);
					lbal = lbal + debit;
					String credit = ""+debit+"";
					String ldebit = "0";
					String product = "xxxxx";
					String partyid = accountsForm.getClientId();
					String lcommencing = DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone());
					int saveledger = chargesAccountProcessingDAO.saveLedger(partyid,product,ldebit,credit,lbal,ledgerid,lcommencing,""+invoiceid+"",0,"0","0","0","0","0",0,0,"0");
					
					//lokesh Testing code for ledger
					if("2".equals(itype)){
						
							expenManagementDAO.savePatientToLedgerMaster(""+accountsForm.getClientId());
							int clientLedgerId=expenManagementDAO.getLedgerIdOfClient(""+accountsForm.getClientId());
							ledgerid=""+clientLedgerId;
						
					}else{
						ledgerid= chargesAccountProcessingDAO.getledgerID(serviceid,"0","0"); 
					}
					//second effect
					
					lbal = 0;
				    credit = "0";
					 ldebit = ""+debit+"";
					 product = "xxxxx";
					 partyid = accountsForm.getClientId();
					 lcommencing = DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone());
					 saveledger = chargesAccountProcessingDAO.saveLedger(partyid,product,ldebit,credit,lbal,ledgerid,lcommencing,""+invoiceid+"",0,"0","0","0","0","0",0,0,"0");
					
			  }
			//log data			
			int result = accountLogDAO.saveChargesInvoice(accountsForm.getPayby(),commencing,Integer.parseInt(accountsForm.getClientId()),debit,discount,accountsForm.getSubmitInvoiceNotes(),thirdPartyID,accountsForm.getLocation(),invoiceid,DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone()));

			
			
			if(totalAssesment.length>1){
				for(int i=1;i<totalAssesment.length;i++){
					int save = accountsDAO.saveChargesAssesment(Integer.parseInt(totalAssesment[i]),invoiceid);
					int update = accountsDAO.updateChargeType(totalAssesment[i],chargeType);
					
					//int update1 = accountLogDAO.updateChargeType(totalAssesment[i],chargeType);
					
				}
			}else{
					invoiceList = accountsDAO.getChargesTempInvoiceList("" ,accountsForm.getClientId(),loginInfo.getLoginsessionid());
					if(invoiceList.size()!=0){
						for(Accounts accounts : invoiceList){
							int save = accountsDAO.saveChargesAssesment(accounts.getInvoiceid(),invoiceid);
							int update = accountsDAO.updateChargeType(Integer.toString(accounts.getInvoiceid()),chargeType);
							
							//int update1 = accountLogDAO.updateChargeType(Integer.toString(accounts.getInvoiceid()),chargeType);
						}
					}else{
						String selectedCharges =accountsForm.getSelectedCharges();
						for(String accounts : selectedCharges.split(",")){
							int save = accountsDAO.saveChargesAssesment(DateTimeUtils.convertToInteger(accounts),invoiceid);
							int update = accountsDAO.updateChargeType(accounts,chargeType);
							
							//int update1 = accountLogDAO.updateChargeType(Integer.toString(accounts.getInvoiceid()),chargeType);
						}
					}
					
			}
			
			 int appointmentid = accountsDAO.getPaymentAppoinetmentId(""+invoiceid+"");
			 int upstatus = accountsDAO.updateOpdPaymentStatus(appointmentid,invoiceid);
			 
				//update charge invoiced
				ArrayList<Master>chargeidList = accountsDAO.getInvoicedChargeidList(invoiceid);
				for(Master m : chargeidList){
					int upc = accountsDAO.updateChargeInvoideid(m.getId(),invoiceid);
				}

			
			//  28 Aug 2018 set parking charges
			double parkingcharge = accountsDAO.checkParkingChargeApplied(invoiceid);
			if(parkingcharge>0){
				/*double discamt = Double.parseDouble(accountsForm.getDiscount());
				if(accountsForm.getDisctype().equals("0")){
					discamt = (debit * Double.parseDouble(accountsForm.getDiscount())) /100;
				}
				discamt = discamt+parkingcharge;*/
				int res = accountsDAO.updateDiscountWithParking(invoiceid,parkingcharge);
				if(res>0){
					accountsForm.setDiscount(DateTimeUtils.changeFormat(parkingcharge));
					accountsForm.setDisctype("1");
				}
			}
			//  07 sep 2018 set seq no
			if(accountsForm.getInvcetype()!=null){
				DateFormat dateFormat = new SimpleDateFormat("yyyy");
				Calendar cal = Calendar.getInstance();
				String a_year = dateFormat.format(cal.getTime());
				if(accountsForm.getInvcetype().equals("1")||accountsForm.getInvcetype().equals("0")||accountsForm.getInvcetype().equals("8")){
					int res = accountsDAO.getMaxOpdseqNo(a_year);
					res =  res+1;
					int ress = accountsDAO.updateInvoiceSeqNo("1",res,invoiceid,a_year);
				}else if(accountsForm.getInvcetype().equals("2")){
					int res = accountsDAO.getMaxIpdseqNo(a_year);
					res =  res+1;
					int ress = accountsDAO.updateInvoiceSeqNo("2",res,invoiceid,a_year);
				}else if(accountsForm.getInvcetype().equals("3")){
					int res = accountsDAO.getMaxInvstseqNo(a_year);
					res =  res+1;
					int ress = accountsDAO.updateInvoiceSeqNo("3",res,invoiceid,a_year);
				}else if(accountsForm.getInvcetype().equals("4")){
					int res = accountsDAO.getMaxMedseqNo(a_year);
					res =  res+1;
					int ress = accountsDAO.updateInvoiceSeqNo("4",res,invoiceid,a_year);
				}else if(accountsForm.getInvcetype().equals("5")){
					int res = accountsDAO.getMaxAdvnRefseqNo(a_year);
					res =  res+1;
					int ress = accountsDAO.updateInvoiceSeqNo("5",res,invoiceid,a_year);
				}else if(accountsForm.getInvcetype().equals("6")){
					int res = accountsDAO.getMaxVaccinationseqNo(a_year);
					res =  res+1;
					int ress = accountsDAO.updateInvoiceSeqNo("6",res,invoiceid,a_year);
				}
			}
			
			session.setAttribute("creditAmount", 0.0);
			session.setAttribute("chargesInvoiceid", invoiceid);
			session.setAttribute("clientId", accountsForm.getClientId());
			session.setAttribute("payAmount", "0");
			session.setAttribute("payby", accountsForm.getPayby());
			session.setAttribute("discount", discount);
			session.setAttribute("clientwhopaymode", accountsForm.getPayby());
			int delete = accountsDAO.deleteChargeAccounts(loginInfo.getLoginsessionid());
			/*if(totalAssesment.length>1){
				for(int i=1;i<totalAssesment.length;i++){
					int result = accountsDAO.saveTempChargeAccounts(Integer.parseInt(totalAssesment[i]));
				}
			}else{
				
				for(Accounts accounts : invoiceList){
					int result = accountsDAO.saveTempChargeAccounts(accounts.getInvoiceid());
				}
			}*/
			
			
			
			//redirect code
		/*	addActionMessage(""+accountsForm.getClient()+" Invoice Creates & Submited successfully!!");
			String clientName = accountsDAO.getClientName(accountsForm.getClientId());
			accountsForm.setClient(clientName);
			
			session.setAttribute("clientId", accountsForm.getClientId());
			session.setAttribute("client", accountsForm.getClient());
			session.setAttribute("payby", accountsForm.getPayby());
			session.setAttribute("fromDate", "");
			session.setAttribute("toDate", "");
			
			
			String payby = accountsForm.getPayby();
			String fromDate = "";
			String toDate = "";
			
			ArrayList<Accounts>chargeProcessingList = new ArrayList<Accounts>();
			ChargesAccountProcessingDAO chargesAccountProcessingDAO = new JDBCChargeAccountProcesDAO(connection);
			int totalCount = chargesAccountProcessingDAO.getTotalChargesAccountProcessingCount(accountsForm.getClientId(),payby,fromDate,toDate);
			pagination.setPreperties(totalCount);
			
			chargeProcessingList = chargesAccountProcessingDAO.getChargesAccountProcessingList(accountsForm.getClientId(),payby,fromDate,toDate,pagination);
			
			pagination.setPage_records(chargeProcessingList.size());
			accountsForm.setTotalRecords(chargeProcessingList.size());
			accountsForm.setPagerecords(Integer.toString(pagination.getPage_records()));
			accountsForm.setChargeProcessingList(chargeProcessingList);
			
			session.setAttribute("pagination", pagination);
			
			accountsForm.setHdnSelectedID(Integer.toString(invoiceid));*/
//			session.setAttribute("previewstatus", "1");
//			chargesAccountProcessingDAO.getChargeAssesmentEntry(invoiceid);
			CommonOpdIpdReport commonOpdIpdReport=new CommonOpdIpdReport();
			commonOpdIpdReport.patientTranssection(accountsForm.getClientId(),date_time);
		}catch(Exception e){
			e.printStackTrace();
			StringWriter sw = new StringWriter();
	         PrintWriter pw = new PrintWriter(sw);
	         e.printStackTrace(pw);
			Tra.exceptionMail(sw.toString());
		}finally{
			
			connection.close();
		}
		
		return "submitinvoicecharges";
	}
	
	
	/*
	 * public void patientTranssection(String clientId) {
	 * 
	 * Connection connection=null; try {
	 * connection=Connection_provider.getconnection(); AccountsDAO accountsDAO = new
	 * JDBCAccountsDAO(connection); ArrayList<Accounts>debitlist = new
	 * ArrayList<Accounts>();
	 * debitlist=accountsDAO.getdebitTransectionlist(clientId);
	 * 
	 * double debit = 0; for (Accounts accounts : debitlist) {
	 * 
	 * if(accounts.getDisctype().equals("0")) { double discamount
	 * =(accounts.getDebitAmount()*accounts.getDiscount()/100);
	 * debit=debit+(accounts.getDebitAmount()-discamount); }else {
	 * debit=debit+(accounts.getDebitAmount())-Double.parseDouble(accounts.
	 * getDiscamt()); } }
	 * 
	 * double payment=accountsDAO.getPaymentList(clientId); double
	 * debeet=accountsDAO.getDebitamountbyid(clientId); double
	 * advanceamt=accountsDAO.getAdvanceamtbyrefinvid(clientId); double
	 * advance=accountsDAO.getAllAdvanceamount(clientId); double
	 * balnace=debit-payment; double adv=payment+debeet; double
	 * advance1=advance-adv;
	 * 
	 * double prepayment=advance-payment;
	 * 
	 * int result=accountsDAO.savePatientTransection(balnace,debit,payment,advance1,
	 * clientId);
	 * 
	 * 
	 * } catch (Exception e) { e.printStackTrace(); }
	 * 
	 * 
	 * }
	 */

	public String invoice() throws Exception{
		if(!verifyLogin(request)){
			return "login";
		}
		Connection  connection = null;
		
		try{
			connection = Connection_provider.getconnection();
			AccountsDAO accountsDAO = new JDBCAccountsDAO(connection);
			ChargesReportDAO chargesReportDAO = new JDBCChargesReportDAO(connection);
			String fromDate = accountsForm.getFromDate();
			String toDate = accountsForm.getToDate();	

			
			if(fromDate.equals("")){
				DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
				Calendar cal = Calendar.getInstance();
				cal.add(Calendar.DATE, -1); 
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
			
			String invpaid=DateTimeUtils.isNull(accountsForm.getInvpaid());
			String payby=DateTimeUtils.isNull(accountsForm.getPayby1());
			
			
			if(loginInfo.getJobTitle().equals(Constants.SUPER_ADMIN)){
				accountsForm.setInvoicecategory(Constants.SECONDARY_INVOICE);
			}
			DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		      Calendar cal = Calendar.getInstance();
		      String date = dateFormat.format(cal.getTime());  
			
			 int result = chargesReportDAO.saveMisReportLog("CA Report",loginInfo.getUserId(),fromDate,toDate,date,"invoice");
			
			ArrayList<Invoice>caInvoiceList = accountsDAO.getCaInvoiceList(accountsForm.getInvoicesearchid(),fromDate,toDate,accountsForm.getInvoicetype(),accountsForm.getInvoicecategory(),invpaid,payby);
			accountsForm.setCaInvoiceList(caInvoiceList);
			
		}catch (Exception e) {
			e.printStackTrace();
		}finally{
			
			connection.close();
		}
		
		return "careport";
	}
	
	
	public String cestimate() throws Exception{
		if(!verifyLogin(request)){
			return "login";
		}
		
		Connection  connection = null;
		
		try{
			connection = Connection_provider.getconnection();
			AccountsDAO accountsDAO = new JDBCAccountsDAO(connection);
			
			String invoiceid = request.getParameter("id");
//			System.out.println(invoiceid);
			
			String clientid  = accountsDAO.getChargesClientid(invoiceid);
			
	ArrayList<Clinic> locationAdressList = accountsDAO.getLocationAddress(loginInfo.getClinicid());
			
			Clinic clinic = new Clinic();
			ClinicDAO clinicDAO  = new JDBCClinicDAO(connection);
			clinic = clinicDAO.getCliniclistDetails(loginInfo.getClinicid());
			accountsForm.setClinicName(clinic.getClinicName());
			accountsForm.setClinicOwner(clinic.getClinicOwner());
			accountsForm.setOwner_qualification(clinic.getOwner_qualification());
			accountsForm.setLocationAdressList(locationAdressList);
			//accountsForm.setAddress(clinic.getAddress());
			accountsForm.setLandLine(clinic.getLandLine());
			accountsForm.setClinicemail(clinic.getEmail());
			accountsForm.setWebsiteUrl(clinic.getWebsiteUrl());
			
			accountsForm.setLocationAdressList(locationAdressList);
			accountsForm.setClinicLogo(clinic.getUserImageFileName());
			accountsForm.setInvoiceid(Integer.parseInt(invoiceid));
			
			//client details
			
			
			
			Client client = accountsDAO.getClientData(clientid);
			accountsForm.setClient(client.getTitle() + " " + client.getFirstName() + " " + client.getLastName());
			accountsForm.setAddress(client.getAddress());
			accountsForm.setClienttown(client.getTown());
			accountsForm.setClientpostcode(client.getPostCode());
			
			
			
			accountsForm.setDob(client.getDob());
			accountsForm.setClientId(clientid);
			accountsForm.setEmail(client.getEmail());
			accountsForm.setMobno(client.getMobNo());
			accountsForm.setPolicyNo(client.getPolicyNo());
			
			
			//chaarges
			
			//setting master chartype data
			ArrayList<Master>masterAssessmentList = accountsDAO.getChargesEstimateMasterAssessmentList(invoiceid);
			accountsForm.setMasterAssessmentList(masterAssessmentList);
			
			
			double total = 0;
			for(Master master : masterAssessmentList){
				String age = DateTimeUtils.getAge1onAddmission(client.getDob(), master.getDatetime());
				accountsForm.setAgegender(age + "/" + client.getGender());
				accountsForm.setDate(master.getDatetime());
				for(Accounts accounts : master.getAssesmentList()){
					total = total + (Double.parseDouble(accounts.getCharges()) * accounts.getQuantity());
				}
			}
		
//			System.out.println(total);	
			
			accountsForm.setTotalAmountx(DateTimeUtils.changeFormat(total));
			
			accountsForm.setActionType("1");
			String thirdParty = accountsDAO.getTpDetailsbyclientid(Integer.parseInt(clientid));
			String company_name=accountsDAO.getcompany_name(Integer.parseInt(thirdParty));
			ThirdParty thirdPartyobj = new ThirdParty();
			thirdPartyobj = accountsDAO.getTpDetails(Integer.parseInt(thirdParty));
			accountsForm.setPayeename(thirdPartyobj.getCompanyName());
			String preparedby=clinicDAO.getpreparedby(invoiceid);
			if(preparedby==null){
				preparedby="";
			}
			accountsForm.setPreparedby(preparedby);
		}catch (Exception e) {
			// TODO: handle exception
		}finally{
			
			connection.close();
		}
		
		return "estimate";
	}
	
	public String estimate() throws Exception{
		
		if(!verifyLogin(request)){
			return "login";
		}
		
		Connection  connection = null;
		
		try{
			connection = Connection_provider.getconnection();
			AccountsDAO accountsDAO = new JDBCAccountsDAO(connection);
			
			ArrayList<Clinic> locationAdressList = accountsDAO.getLocationAddress(loginInfo.getClinicid());
			
			Clinic clinic = new Clinic();
			ClinicDAO clinicDAO  = new JDBCClinicDAO(connection);
			clinic = clinicDAO.getCliniclistDetails(loginInfo.getClinicid());
			accountsForm.setClinicName(clinic.getClinicName());
			accountsForm.setClinicOwner(clinic.getClinicOwner());
			accountsForm.setOwner_qualification(clinic.getOwner_qualification());
			accountsForm.setLocationAdressList(locationAdressList);
			//accountsForm.setAddress(clinic.getAddress());
			accountsForm.setLandLine(clinic.getLandLine());
			accountsForm.setClinicemail(clinic.getEmail());
			accountsForm.setWebsiteUrl(clinic.getWebsiteUrl());
			
			accountsForm.setLocationAdressList(locationAdressList);
			accountsForm.setClinicLogo(clinic.getUserImageFileName());
			
			
			//client details
			
			String clientId = accountsForm.getClientId();
			
			Client client = accountsDAO.getClientData(clientId);
			accountsForm.setClient(client.getTitle() + " " + client.getFirstName() + " " + client.getLastName());
			accountsForm.setAddress(client.getAddress());
			accountsForm.setClienttown(client.getTown());
			accountsForm.setClientpostcode(client.getPostCode());
			
			String age = DateTimeUtils.getAge(client.getDob());
			accountsForm.setAgegender(age + "/" + client.getGender());
			
			accountsForm.setDob(client.getDob());
			accountsForm.setClientId(clientId);
			accountsForm.setEmail(client.getEmail());
			accountsForm.setMobno(client.getMobNo());
			accountsForm.setPolicyNo(client.getPolicyNo());
			
			
			//chaarges
			
			//setting master chartype data
			ArrayList<Master>masterAssessmentList = accountsDAO.getEstimateMasterAssessmentList(clientId,loginInfo.getId());
			accountsForm.setMasterAssessmentList(masterAssessmentList);
			
			double total = 0;
			for(Master master : masterAssessmentList){
				for(Accounts accounts : master.getAssesmentList()){
					total = total + (Double.parseDouble(accounts.getCharges()) * accounts.getQuantity());
				}
			}
		
//			System.out.println(total);	
			
			accountsForm.setTotalAmountx(DateTimeUtils.changeFormat(total));
			String autoselect=accountsForm.getAutoselect();
			if(autoselect==null){
				autoselect="0";
			}
			int res=accountsDAO.getidforlist("IPD");
			
			if(autoselect.equals("1")){
				accountsForm.setInvcetype(String.valueOf(res));
			}
		}catch (Exception e) {
			// TODO: handle exception
		}finally{
			
			connection.close();
		}
		
		return "estimate";
	}
	
	
	
	
	boolean isOpdInvoice=false;
	boolean istpservices = false;
	int invid=0;
	double discOuunt=0;
	
	public String viewOpdInvoice() throws Exception{
		
		Connection connection=null; 
		try {
			connection=Connection_provider.getconnection();
			AccountsDAO accountsDAO=new JDBCAccountsDAO(connection);
			String appointmentid=request.getParameter("appointmentid");
			String invoiceid=accountsDAO.getInvoicIdfromAppointment(appointmentid);
			String chargeInvoiceid=accountsDAO.getChargeInvoiceId(invoiceid);
			Accounts accounts=accountsDAO.getInvoiceChargesDetails(chargeInvoiceid);
			int chargeinvoiceid=Integer.parseInt(chargeInvoiceid);
			invid=chargeinvoiceid;
			isOpdInvoice=true;
			discOuunt=accounts.getDiscount();
			session.setAttribute("clientId", String.valueOf(accounts.getClientid()));
			session.setAttribute("payby", accounts.getPayby());
			
			
			
		} catch (Exception e) {

			e.printStackTrace();
		}finally{
			
			connection.close();
		}
		
		return viewInvoice();
	}
	
	
	public String tpservices() throws Exception{
		
		String invoiceid = request.getParameter("invoiceid");
		String sname = request.getParameter("sname");
		
		
		
		
		Connection connection = null;
		try{
			
			connection = Connection_provider.getconnection();
			AccountsDAO accountsDAO = new JDBCAccountsDAO(connection);
			
			
			Accounts accounts=accountsDAO.getInvoiceChargesDetails(invoiceid);
			int chargeinvoiceid=Integer.parseInt(invoiceid);
			invid=chargeinvoiceid;
			isOpdInvoice=false;
			istpservices = true;
			discOuunt=accounts.getDiscount();
			session.setAttribute("clientId", String.valueOf(accounts.getClientid()));
			session.setAttribute("payby", accounts.getPayby());
			
			
			ArrayList<Accounts>assesmentList = accountsDAO.getTpServicesList(invoiceid,sname,loginInfo);
			
			//dele session data
			int del = accountsDAO.deleteTpTempSessionData(loginInfo.getLoginsessionid());
			//sort by date
			for(Accounts a : assesmentList){
				int res = accountsDAO.saveTpServiceData(a,loginInfo);
			}
			
			assesmentList = accountsDAO.getTpTempAssesmentList(loginInfo);
			accountsForm.setAssesmentList(assesmentList);
			accountsForm.setMasterchargetype(sname);
			
			double tpstotal = accountsDAO.getTpsTotal(invoiceid,sname,loginInfo);
			accountsForm.setTpstotal(DateTimeUtils.changeFormat(Math.round(tpstotal)));
			
			double okgtotal = accountsDAO.getPkgTotal(invoiceid,sname);
			accountsForm.setPkgtotal(DateTimeUtils.changeFormat(Math.round(okgtotal)));
			
		}catch (Exception e) {
			// TODO: handle exception
		}

		
		
		return viewInvoice();
	}
	
	
	public String viewInvoice() throws Exception{
		
		if(!verifyLogin(request)){
			return "login";
		}
		String action="";
			DateTimeUtils dateTimeUtils = new DateTimeUtils();
			int invoiceid =0;
			double discount=0;
			String payby1 ="";
			if(isOpdInvoice){
				invoiceid=invid;
				discount=discOuunt;
				action="show";
				payby1 = (String)session.getAttribute("payby");
				
			} 
			else if(istpservices){
				invoiceid=invid;
				discount=discOuunt;
				action="show";
				payby1 = (String)session.getAttribute("payby");
			
			}
			else {
				invoiceid = DateTimeUtils.convertToInteger(request.getParameter("invoiceid"));
				if(invoiceid==0){
					invoiceid=(Integer)session.getAttribute("chargesInvoiceid");
				}
				discount = DateTimeUtils.convertToDouble(request.getParameter("discount"));
				if(discount==0){
					if(session.getAttribute("discount")!=null){
					discount = (Double)session.getAttribute("discount");
					}
				}
				action = request.getParameter("action");
				payby1 = request.getParameter("payby");
				if(payby1==null){
					payby1=(String)session.getAttribute("clientwhopaymode");
				}
			}
			
			session.setAttribute("chargesInvoiceid", invoiceid);
			String clientId = (String)session.getAttribute("clientId");
			String payby = request.getParameter("payby");
			if(payby==null){
				payby=(String)session.getAttribute("payby");
			}
			
			String pid = request.getParameter("pid");
			
			String paymentreciptreport = request.getParameter("paymentreciptreport");
			if(paymentreciptreport!=null){
				if(paymentreciptreport.equals("1")){
					payby = request.getParameter("payby");
					clientId =  request.getParameter("reportclintid");
				}
			}
			double payAmount = 0;
			
			/*int invoiceid = (Integer)session.getAttribute("chargesInvoiceid");
			String clientid = (String)session.getAttribute("clientId");
			double payAmount = (Double)session.getAttribute("payAmount");*/
			
			session.setAttribute("chargesInvoiceid", invoiceid);
			session.setAttribute("clientId", clientId);
			session.setAttribute("payAmount", payAmount);
			//  14/09/2019 for duplicate invoiceid due to modify invoice
			
			String billsummary = request.getParameter("billsummary");
			if(billsummary==null){
				if(session.getAttribute("previewstatus")!=null){
				billsummary=(String)session.getAttribute("previewstatus");
				}else{
				billsummary="";
				}
			}
			accountsForm.setBillsummary(billsummary);
			Vector<Accounts>previewChargesList = new Vector<Accounts>();
			Vector<Accounts>assesmentList = new Vector<Accounts>();
			String wardname="";
			String bedname="";
			Connection connection = null;
			try{
				
				connection = Connection_provider.getconnection();
				AccountsDAO accountsDAO = new JDBCAccountsDAO(connection);
				boolean inddisc=accountsDAO.getdiscountapprovests(invoiceid);
//				ArrayList<Accounts> listcharges=accountsDAO.getChargeAssesments(invoiceid);
//				for (Accounts accounts : listcharges) {
//					int r=accountsDAO.checkChargeAssements(accounts);
//				}
				ChargesAccountProcessingDAO chargesAccountProcessingDAO = new JDBCChargeAccountProcesDAO(connection);

				
				if(clientId==null){
					Accounts accounts=accountsDAO.getInvoiceChargesDetails((""+invoiceid));
					clientId=""+accounts.getClientid();
				}
				
				//set Location
				String locationid = accountsDAO.getLocationID(invoiceid);
				String locationName = accountsDAO.getLocationName(locationid);
				accountsForm.setLocationName(locationName);
				
				//ArrayList<Accounts>chargesInvoiceList = accountsDAO.getchargesInvoiceList(invoiceid);
				
				/*for(Accounts accounts : chargesInvoiceList){
					
					Accounts acc = accountsDAO.getAppointmentDetailsl(Integer.toString(accounts.getInvoiceid()));
					previewChargesList = accountsDAO.getPreviewChargesList(acc,Integer.toString(accounts.getInvoiceid()));
					
					assesmentList.addAll(previewChargesList);
					
					
				}*/
				
				//setting master chartype data
				if(billsummary.equals("1")){
					ArrayList<Master>masterAssessmentList = accountsDAO.getBillMasterAssessmentList(invoiceid,"0");
					accountsForm.setMasterAssessmentList(masterAssessmentList);
					accountsForm.setDiscstatus1(masterAssessmentList.get(masterAssessmentList.size()-1).isDiscstatus());
				}else{
					
					  ArrayList<Master>masterAssessmentList =accountsDAO.getKunalMasterAssessmentList(invoiceid,"0",loginInfo);
					  accountsForm.setMasterAssessmentList(masterAssessmentList);
					  accountsForm.setDiscstatus1(masterAssessmentList.get(masterAssessmentList.size()-1).isDiscstatus());
					  if(masterAssessmentList.size()>0){
					  MasterDAO masterDAO= new JDBCMasterDAO(connection);
					  accountsForm.setTaxtype1(masterAssessmentList.get(masterAssessmentList.size()-1).getTaxtype1());
					  accountsForm.setTaxtype1(Math.round((accountsForm.getTaxtype1()*100.0)/100.0)); 
					  accountsForm.setTax1(masterDAO.getTaxnamebyType("1"));
					  accountsForm.setTaxtype2(masterAssessmentList.get(masterAssessmentList.size()-1).getTaxtype2());
					  accountsForm.setTaxtype2(Math.round((accountsForm.getTaxtype2()*100.0)/100.0)); 
					  accountsForm.setTax2(masterDAO.getTaxnamebyType("2"));
					  accountsForm.setTaxtype3(masterAssessmentList.get(masterAssessmentList.size()-1).getTaxtype3()); 
					  accountsForm.setTax3(masterDAO.getTaxnamebyType("3"));
					  accountsForm.setTaxtype3(Math.round((accountsForm.getTaxtype3()*100.0)/100.0)); 
					  }
					 
					
				}
				
				String pkgstr = "nopkg";
				if(payby1.equals(Constants.PAY_BY_THIRD_PARTY)){
					pkgstr = accountsDAO.getPkgStr(invoiceid);
					if(!pkgstr.equals("")){
					ArrayList<Master>pkgAssessmentList = accountsDAO.getPkgMasterAssessmentList(pkgstr,invoiceid);
					accountsForm.setPkgAssessmentList(pkgAssessmentList);
					}
				}
				if(loginInfo.isPackage_access()){
					pkgstr = accountsDAO.getPkgStr(invoiceid);
					if(!pkgstr.equals("")){
					ArrayList<Master>pkgAssessmentList = accountsDAO.getPkgMasterAssessmentList(pkgstr,invoiceid);
					accountsForm.setPkgAssessmentList(pkgAssessmentList);
				}
				}
				accountsForm.setPkgstr(pkgstr);
				if(pkgstr.equals("nopkg")){
					pkgstr="";
				}
				if(pkgstr==null){
					pkgstr="";
				}
				
				//advance payment recipt list
				ArrayList<Accounts>prepaymentList = accountsDAO.getPrePaymentList(invoiceid,clientId);
				accountsForm.setPrepaymentList(prepaymentList);
				
				//refund payment recipt list
				ArrayList<Accounts> refundList = accountsDAO.getRefundList(invoiceid);
				accountsForm.setRefundList(refundList);
				int prepid=0;
				String ipdidsss="0";
				if(prepaymentList.size()!=0){
				 prepid= prepaymentList.get(prepaymentList.size()-1).getId();
				 ipdidsss=DateTimeUtils.isNull(prepaymentList.get(prepaymentList.size()-1).getIpdid());
				}
				
				ArrayList<Accounts>transactionList=null;
				if((ipdidsss.equals("")||ipdidsss.equals("0")) && prepaymentList.size()!=0){
					transactionList = chargesAccountProcessingDAO.gettransactionListNew(""+invoiceid,clientId,prepid);
				}else{
					transactionList = chargesAccountProcessingDAO.gettransactionList(""+invoiceid);	
					
				}
				
				accountsForm.setTransactionList(transactionList);
				
				
				
				if(loginInfo.getIskunal()==1){
					assesmentList = accountsDAO.getPreviewChargesListKunal(invoiceid);
				}else if(!pkgstr.equals("")){
					if(loginInfo.isPackage_access()){
						if(inddisc){
						assesmentList = accountsDAO.getPreviewChargesListKunal(invoiceid);
						}else{
							assesmentList = accountsDAO.getPreviewChargesList(invoiceid);
						}
					}
					
				}
				else{
					assesmentList = accountsDAO.getPreviewChargesList(invoiceid);
					
					String apptid="";
					if(assesmentList.size()!=0){
					Accounts apmt=assesmentList.get(assesmentList.size()-1);
					apptid=apmt.getApmt_id();
					
					}
					ArrayList<Accounts> procedurelist=accountsDAO.getProcedureListbyapmtid(apptid);
					accountsForm.setProcedurelist(procedurelist);
				}
				
				
				//double totalAmount =  accountsDAO.getInvoiceDebitAmmount(invoiceid);
				double totalAmount = 0;
				String sectionroom = "";
				double maintot=0;
				double chargedisc=0;
				ArrayList<String> tempdiscmaster=new ArrayList<String>();
				ArrayList<Accounts> newdisclist=new ArrayList<Accounts>();
				for(Accounts totalAcc : assesmentList){
					double charge = Double.parseDouble(totalAcc.getCharges()) * totalAcc.getQuantity();
					totalAmount = totalAmount + charge;
					if(totalAcc.getChargedisc()==null){
						totalAcc.setChargedisc("0");
					}
					chargedisc=chargedisc+Double.parseDouble(totalAcc.getChargedisc());
				
					if(loginInfo.getIskunal()==1 ){
								totalAmount = totalAmount-Double.parseDouble(totalAcc.getDiscamt());
								maintot=maintot+charge;
//								
//					//sectionroom = totalAcc.getSectionroom();
				}else if(!pkgstr.equals("")){
					if(loginInfo.isPackage_access()){
						if(inddisc){
						totalAmount = totalAmount-Double.parseDouble(totalAcc.getDiscamt());
						maintot=maintot+charge;
						}
					}
					}
					
					System.out.println(totalAcc.getAppointmentType());
				}
//				accountsForm.setAssesmentList(assesmentList);
				if(loginInfo.getIskunal()==1){
				newdisclist=accountsDAO.getMasternameforDisc(invoiceid);
//				Accounts accounts3=accountsDAO.getMasternameforDisc1(invoiceid);
//				newdisclist.add(accounts3);
				}
				if(!pkgstr.equals("")){
					if(loginInfo.isPackage_access()){
						newdisclist=accountsDAO.getMasternameforDisc(invoiceid);
					}
				}
				
				accountsForm.setNewdisclist(newdisclist);
				int inv_practid=accountsDAO.getpractIdFromInvoice(String.valueOf(invoiceid));
				
				String date = accountsDAO.getInvoiceDate(invoiceid);
				String invoiceTime= accountsDAO.getInvoiceTime(invoiceid); 
				String invoicenameid=accountsDAO.getInvoiceTypeId(invoiceid);
				Accounts fromandtodate=accountsDAO.getFromtodateforHD(invoiceid);
				int ipdopdseqno= accountsDAO.getIpdOpdSeqNo(invoiceid);
				String practId= accountsDAO.getPractitionerofInvoice(invoiceid);
				UserProfileDAO profileDAO= new JDBCUserProfileDAO(connection);
				String refdr="";
				accountsForm.setIpdopdseqno(String.valueOf(ipdopdseqno));
				
				if(loginInfo.isBalgopal() || loginInfo.isSeq_no_gen()){
					accountsForm.setIpdopdseqno(accountsDAO.getFinancialSeqNoOfInvoice(""+invoiceid));
				}
				
				accountsForm.setRefereddr(refdr);
				accountsForm.setInvoicenameid(invoicenameid);
				String invoicename=accountsDAO.getInvoiceName(invoicenameid);
				accountsForm.setInvoiceTime(invoiceTime);
				accountsForm.setInvoicename(invoicename);
				String itypenew = accountsDAO.getInvoiceTypeId(invoiceid);
				
				String fromDate="";
				String toDate="";
				int i=0;
				if(invoicename.equals("HD")){
					
					
					/*ArrayList<String> strings=new ArrayList<String>();
					 for(Master master:masterAssessmentList){
						   
						     for(Accounts accounts: master.getAssesmentList()){
						    	 
						    	 if(accounts.getCommencing()!=null){ 
						    	 
						    		 strings.add(accounts.getCommencing());
						    	 }
						    	
						     }       
					 } 
					 
					 ArrayList<Date> dates=new ArrayList<Date>();
						
					 SimpleDateFormat dateFormat=new SimpleDateFormat("dd-MM-yyyy");
					 
					 for(String dstr:strings){
							
			               Date da=dateFormat.parse(dstr);
						   dates.add(da);
					}
					 
					 Collections.sort(dates,new Comparator<Date>() {

							@Override
							public int compare(Date arg0, Date arg1) {
							  
								 if(arg0.getTime()>arg1.getTime()) {
									 
									 return 1;
								 } else if(arg0.getTime()<arg1.getTime()){
									 
									 return -1;
								 } else {
									 return 0;
								 }
							}
						});
					 
					 for(Date da:dates){
				    	 
				    	  String datestr=dateFormat.format(da);
				    	  if(i==0){
				    		  fromDate=datestr;
				    	  }
				    	  
				    	  toDate=datestr;
				    	//  System.out.println(datestr);
				    	  i++;
				     }*/
					 
					 accountsForm.setFromDate(fromandtodate.getFromDate());
		             accountsForm.setToDate(fromandtodate.getToDate());
					
				}
				ArrayList<Clinic> locationAdressList=new ArrayList<>();
				if(loginInfo.getClinicid1().equals("manasclinic")) {
					NotAvailableSlotDAO notAvailableSlotDAO=new JDBCNotAvailableSlotDAO(connection);
					String clinicstaff_id=notAvailableSlotDAO.getclinicstaff(loginInfo.getId());
					locationAdressList = accountsDAO.getLocationAddressForManasclinic(Integer.parseInt(clinicstaff_id));
				}else {
					locationAdressList = accountsDAO.getLocationAddress(loginInfo.getClinicid());
				}
				//ArrayList<Clinic> locationAdressList = accountsDAO.getLocationAddress(loginInfo.getClinicid());
				Client client = accountsDAO.getClientData(clientId);
				accountsForm.setPatientcategory(client.getPatientcategory());
				if(loginInfo.isSjivh()) {
					accountsForm.setClient(client.getFathername());	
					accountsForm.setOwner_name(client.getTitle() + " " + client.getFirstName() + " "+ client.getLastName());
					}else {
					accountsForm.setClient(client.getTitle() + " " + client.getFirstName() + " "+DateTimeUtils.isNull(client.getMiddlename())+" " + client.getLastName());
					} 
				//accountsForm.setClient(client.getTitle() + " " + client.getFirstName() + " "+client.getMiddlename()+" " + client.getLastName());
				accountsForm.setAddress(client.getAddress());
				accountsForm.setClienttown(client.getTown());
				accountsForm.setClientpostcode(client.getPostCode());
				accountsForm.setFathername(client.getFathername());
				
				String age = "";
				String agegender="";
				client.setBirthtime(DateTimeUtils.isNull(client.getBirthtime()));
				String birthtime=client.getBirthtime().replaceAll(" ","");
				if(birthtime==null){
					birthtime="00:00:00";
				}
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
				 
			if(!loginInfo.isBalgopal()) {	 
				if(client.getDob()!=null){
					if(!client.getDob().equals("")){
					  
						age=dateTimeUtils.getAge1(client.getDob());
						if(!birthtime.equals("00:00:00")){
							agegender = age +"("+apmpm1+")"+ " / " + client.getGender();
						}else{
						agegender = age + " / " + client.getGender();	
						}
//						if(Integer.parseInt(age)<2){
//							if(Integer.parseInt(age)<1){
//								String monthdays= DateTimeUtils.getMonthandDays(client.getDob());
//								agegender=monthdays+" / "+client.getGender();
//							}else{
//								String monthdays= DateTimeUtils.getMonthandDays(client.getDob());
//								agegender= age + " Years" + " "+monthdays+" / "+client.getGender();
//							}
//						} else {
//							agegender = age + "Years" + " / " + client.getGender();	
//						}
					}
				}
			}else {
				
	    			//Client client= new Client();
	    			//client= clientDAO.getClientDetails(bed.getClientid());
	    			String dob = client.getDob();
	    			String admndt[]= date.split(" ");
	    			String ageonadmn= DateTimeUtils.getAge1onAddmission(dob,admndt[0]);
	    			agegender=ageonadmn;
	    			agegender = agegender + " / " + client.getGender();
	    			

			}
				
				
				accountsForm.setAgegender(agegender);
				
				accountsForm.setAbrivationid(client.getAbrivationid());
				accountsForm.setDob(client.getDob());
				accountsForm.setClientId(clientId);
				accountsForm.setEmail(client.getEmail());
				accountsForm.setMobno(client.getMobNo());
				accountsForm.setPolicyNo(client.getPolicyNo());
				accountsForm.setPayby(payby1);				
				
				//accountsForm.setAssesmentList(assesmentList);
				accountsForm.setTotalAmount(totalAmount);
				
				accountsForm.setInvoiceid(invoiceid);
//				accountsForm.setAgegender(DateTimeUtils.getAge1onAddmission(client.getDob(), date)+" / "+client.getGender());
				accountsForm.setDate(DateTimeUtils.getInvoiceCommencingDate(date));
				accountsForm.setKuanlInvSeq(accountsDAO.getSeqNoForKunal(accountsForm.getDate(), ""+invoiceid, itypenew));
				accountsForm.setCreditAmt(dateTimeUtils.changeFormat(payAmount));
				if(loginInfo.getIskunal()==1){
					accountsForm.setCreditAmt(dateTimeUtils.changeFormat(Math.round(payAmount)));
				}
				if(!pkgstr.equals("")){
					if(loginInfo.isPackage_access()){
						if(!inddisc){
						accountsForm.setCreditAmt(dateTimeUtils.changeFormat(Math.round(payAmount)));
						}
					}
					}
				//Decimal Account
				if(loginInfo.getIskunal()==1){
					
					accountsForm.setTotalAmountx(dateTimeUtils.changeFormat(maintot));
				}else if(!pkgstr.equals("")){
					if(loginInfo.isPackage_access()){
						if(inddisc){
						accountsForm.setTotalAmountx(dateTimeUtils.changeFormat(maintot));
						}else{
							accountsForm.setTotalAmountx(dateTimeUtils.changeFormat(totalAmount));
						}
					  }
					}else{
				accountsForm.setTotalAmountx(dateTimeUtils.changeFormat(totalAmount));
				}
				
				double totalamt=DateTimeUtils.convertToDouble(accountsForm.getTotalAmountx());
				double totaltax=(accountsForm.getTaxtype1())+accountsForm.getTaxtype2()+accountsForm.getTaxtype3();
				double total=totalamt-totaltax;
				accountsForm.setAmtWithouttax(dateTimeUtils.changeFormat(total));
				
				NumberToWord obj = new NumberToWord();
				accountsForm.setTotalinword(obj.convert((int) totalAmount));
				
				double credit =  0 ;
				Accounts accountsdisc = accountsDAO.getAccDiscData(invoiceid);
				int disctype = Integer.parseInt(accountsdisc.getDisctype());
				//update discount
				if(disctype==1){
					int upd = accountsDAO.updatePerDicount(invoiceid);
				}else{
					int upd = accountsDAO.updateAmtDiscount(invoiceid);
				}
				accountsForm.setDisctype(Integer.toString(disctype));
				double discamt = Double.parseDouble(accountsdisc.getDiscamt());
				if(discount  >0 || disctype>0||chargedisc>0){
//				if(discount  >0 || disctype>0){
					double dicsAmount=0;
					double totdisc=0;
					if(chargedisc==0){
					 totdisc = (discount*totalAmount)/100;
					if(disctype==1){
						totdisc = discamt ;
					}
					
					}
					if (chargedisc>0) {
//						
						totdisc=chargedisc;
					}
					dicsAmount=dicsAmount+totdisc;
					credit = totalAmount - dicsAmount;
					accountsForm.setNetpayamount(dateTimeUtils.changeFormat(credit));
					if(loginInfo.getIskunal()==1){
						accountsForm.setNetpayamount(dateTimeUtils.changeFormat(Math.round(credit)));
					}
					if(!pkgstr.equals("")){
						if(loginInfo.isPackage_access()){
							if(!inddisc){
							accountsForm.setNetpayamount(dateTimeUtils.changeFormat(Math.round(credit)));
							}
						}
					}
					
				    obj = new NumberToWord();
					accountsForm.setTotalinword(obj.convert((int) credit));
					
					accountsForm.setDicsAmount(DateTimeUtils.changeFormat(dicsAmount));
					
					accountsForm.setBalance(Double.parseDouble(new DecimalFormat("##.##").format(credit)));
					
					//Decimal Account
					accountsForm.setBalancex(dateTimeUtils.changeFormat(credit));
					if(loginInfo.getIskunal()==1){
						accountsForm.setBalancex(dateTimeUtils.changeFormat(Math.round(credit)));
					}
					if(!pkgstr.equals("")){
						if(loginInfo.isPackage_access()){
							if(!inddisc){
							accountsForm.setBalancex(dateTimeUtils.changeFormat(Math.round(credit)));
							}
						}
					}	
					
				}
				else{
					accountsForm.setNetpayamount(dateTimeUtils.changeFormat(totalAmount));
					if(loginInfo.getIskunal()==1){
						accountsForm.setNetpayamount(dateTimeUtils.changeFormat(Math.round(totalAmount)));
					}
					if(!pkgstr.equals("")){
						if(loginInfo.isPackage_access()){
							if(!inddisc){
							accountsForm.setNetpayamount(dateTimeUtils.changeFormat(Math.round(totalAmount)));
							}
							
						}
					}	
					credit = totalAmount - payAmount;
					accountsForm.setBalance(totalAmount);
					accountsForm.setDicsAmount(DateTimeUtils.changeFormat(0));
					
					//Decimal Account
					accountsForm.setBalancex(dateTimeUtils.changeFormat(totalAmount));
					if(loginInfo.getIskunal()==1 || loginInfo.isPackage_access()){
						accountsForm.setBalancex(dateTimeUtils.changeFormat(Math.round(totalAmount)));
					}
				}
				
				//double credit = totalAmount - payAmount;
				if(payAmount >0){
					
					accountsForm.setDebitAmounnt(dateTimeUtils.changeFormat(credit));					

				}else{
					accountsForm.setDebitAmounnt(dateTimeUtils.changeFormat(totalAmount));
				}
				
				
			
				
				
				
			/*	//policy excess code
				if(payby1.equals(Constants.PAY_BY_THIRD_PARTY)){
					Accounts accounts = accountsDAO.checkInvoiceHasPolicyExcess(invoiceid);
					
					if(accounts.isPolicyExcess()){
						
						double balance = accountsForm.getBalance() - Double.parseDouble(accounts.getCharges());
						totalAmount = totalAmount - Double.parseDouble(accounts.getCharges());
						accountsForm.setPolicyexcesscode(1);
						accountsForm.setPolicyExcess(DateTimeUtils.changeFormat(Double.valueOf(accounts.getCharges())));
						accountsForm.setBalancex(dateTimeUtils.changeFormat(balance));
					}
				}*/
				
				double totalPaymentReceived = accountsDAO.getTotalPaymentReceived(invoiceid);
				//if refund against invoice
				StatementDAO statementDAO = new JDBCStatementDAO(connection);
				double refundAmt = statementDAO.getRefundAmtAgainsInvoice(invoiceid);
				totalPaymentReceived = totalPaymentReceived - refundAmt;
				if(action.equals("viewpayment")){
					totalPaymentReceived = Double.parseDouble(request.getParameter("amount"));
				}
				accountsForm.setCreditAmt(dateTimeUtils.changeFormat(totalPaymentReceived));
				if(loginInfo.getIskunal()==1){
					accountsForm.setCreditAmt(dateTimeUtils.changeFormat(Math.round(totalPaymentReceived)));	
				}
				if(!pkgstr.equals("")){
					if(loginInfo.isPackage_access()){
						if(!inddisc){
						accountsForm.setCreditAmt(dateTimeUtils.changeFormat(Math.round(totalPaymentReceived)));	
						}
					}	
				}
				//double credit = totalAmount - payAmount;
				double discountAmt = (totalAmount * (discount/100));
				if(disctype==1){
					discountAmt = discamt;
				}
				double amountDue = totalAmount - discountAmt;
				amountDue = amountDue - totalPaymentReceived;
				if(chargedisc>0){
					amountDue=0;
				}
				//amountDue = Double.parseDouble(new DecimalFormat("##.##").format(amountDue));
			/*	if(payAmount >0){
					
					accountsForm.setBalance(amountDue);
					
					//Decimal Account
					accountsForm.setBalancex(dateTimeUtils.changeFormat(amountDue));
				}else{
					accountsForm.setDebitAmounnt(dateTimeUtils.changeFormat(totalAmount));
					accountsForm.setBalance(totalAmount);
					
					//Decimal Account
					accountsForm.setBalancex(dateTimeUtils.changeFormat(totalAmount));
					
				}
				*/
				
				accountsForm.setBalance(amountDue);
				
				//Decimal Account
				accountsForm.setBalancex(dateTimeUtils.changeFormat(amountDue));
				if(loginInfo.getIskunal()==1){
					accountsForm.setBalancex(dateTimeUtils.changeFormat(Math.round(amountDue)));
				}
				if(!pkgstr.equals("")){
					if(loginInfo.isPackage_access()){
						if(!inddisc){
						accountsForm.setBalancex(dateTimeUtils.changeFormat(Math.round(amountDue)));
						}
					}
					}
				if(chargedisc>0){
					
					accountsForm.setBalancex(dateTimeUtils.changeFormat(Math.round(totalAmount-Double.parseDouble(accountsForm.getCreditAmt())-chargedisc)));
				}
				//policy excess code
				if(payby.equals(Constants.PAY_BY_THIRD_PARTY)){
					Accounts accounts = accountsDAO.checkInvoiceHasPolicyExcess(invoiceid);
					
					if(accounts.isPolicyExcess()){
						
						double balance = amountDue - Double.parseDouble(accounts.getCharges());
						//totalAmount = totalAmount - Double.parseDouble(accounts.getCharges());
						accountsForm.setPolicyexcesscode(1);
						accountsForm.setPolicyExcess(DateTimeUtils.changeFormat(Double.valueOf(accounts.getCharges())));
						//accountsForm.setBalancex(dateTimeUtils.changeFormat(balance));
					}
				}
				
				
				
				accountsForm.setPayAmount(totalPaymentReceived);
				
				//Decimal Account
				accountsForm.setPayAmountx(dateTimeUtils.changeFormat(totalPaymentReceived));
				
				
				accountsForm.setInddiscsts(inddisc);
				
				Clinic clinic = new Clinic();
				ClinicDAO clinicDAO  = new JDBCClinicDAO(connection);
				if(loginInfo.getClinicid1().equals("manasclinic")) {
					NotAvailableSlotDAO notAvailableSlotDAO=new JDBCNotAvailableSlotDAO(connection);
					String clinicstaff_id=notAvailableSlotDAO.getclinicstaff(loginInfo.getId());
					clinic = clinicDAO.getCliniclistDetails(Integer.parseInt(clinicstaff_id));
				}else {
					clinic = clinicDAO.getCliniclistDetails(loginInfo.getClinicid());
				}
				//clinic = clinicDAO.getCliniclistDetails(loginInfo.getClinicid());
				if(loginInfo.getClinicid1().equals("manasclinic")) {
					accountsForm.setClinicName(clinic.getManas_clinic_name());
					
				}else {
					accountsForm.setClinicName(clinic.getClinicName());
				}
				//accountsForm.setClinicName(clinic.getClinicName());
				
				accountsForm.setClinicOwner(clinic.getClinicOwner());
				accountsForm.setOwner_qualification(clinic.getOwner_qualification());
				accountsForm.setLocationAdressList(locationAdressList);
				//accountsForm.setAddress(clinic.getAddress());
				//accountsForm.setLandLine(clinic.getLandLine());
				accountsForm.setClinicemail(clinic.getEmail());
				accountsForm.setWebsiteUrl(clinic.getWebsiteUrl());
				accountsForm.setDiscount(dateTimeUtils.changeFormat(discount));
				accountsForm.setLocationAdressList(locationAdressList);
				if(loginInfo.getClinicid1().equals("manasclinic")) {
				    accountsForm.setLandLine(clinic.getMobileNo());
				}else {
					accountsForm.setLandLine(clinic.getLandLine());
					accountsForm.setClinicName(clinic.getClinicName());
				}
				//set balgopal address
				Clinic ccbg =  locationAdressList.get(0);
				session.setAttribute("balgopaladdress", ccbg.getAddress());
				session.setAttribute("balgopalcname", ccbg.getClinicName());
				accountsForm.setClinicLogo(clinic.getUserImageFileName());

				String notes = accountsDAO.getNotes(invoiceid);
				accountsForm.setSubmitInvoiceNotes(notes);
				session.setAttribute("invnotes", notes);
				String paymode = accountsDAO.getPaymentMode(invoiceid);
				String upino=accountsDAO.getTransactionNo(invoiceid);
				accountsForm.setTransactionno(upino);
				accountsForm.setPaymode(paymode);
				
				accountsForm.setInvoice_date(clinic.getInvoice_date());
				
				if(payby1.equalsIgnoreCase("Third Party")){
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
					accountsForm.setUnit(thirdParty.getUnit());
					accountsForm.setArea(thirdParty.getArea());
					
					
					
				}
				else{
					//accountsForm.setPayeename(client.getTitle() + " " + client.getFirstName() + " "+client.getMiddlename()+" " + client.getLastName());
					accountsForm.setPayeename("Self");
					if(client.getSecondLineaddress()!=null){
						accountsForm.setPayeeadress(client.getAddress() + "," + client.getSecondLineaddress());
					}else{
						accountsForm.setPayeeadress(client.getAddress());
					}
					
					accountsForm.setPayeeTown(client.getTown());
					accountsForm.setPayeePostcode(client.getPostCode());
					accountsForm.setPayeeEmail(client.getEmail());
					accountsForm.setPayeeConatctNo(client.getMobNo());
					
				}
				session.setAttribute("payby", payby1);
				session.setAttribute("clientId", clientId);
				session.setAttribute("payAmount", Double.toString(payAmount));
				
				
				//set opd practitionerid
				int opdparctid = accountsDAO.getPeactOpdID(invoiceid);
				int opdid =accountsDAO.getOpdIDFromInvoiceId(invoiceid);
				if(opdparctid==0){
					opdparctid = 1;
				}
				
				if(practId!=null){
					if(!practId.equals("")){
						 opdparctid= Integer.parseInt(practId);
					}
				}
				accountsForm.setOpdid(""+opdid);
				String tokenno=accountsDAO.getTokennoforOPD(opdid,opdparctid,date);
				accountsForm.setTokenno(tokenno);
				//String opdSeq = accountsDAO.getLMHOPDSequence(opdid);
				
				String fullname ="";
				UserProfile userProfile = new UserProfile();
				UserProfileDAO userProfileDAO = new JDBCUserProfileDAO(connection);
				if(opdparctid!=0){
				userProfile = userProfileDAO.getUserprofileDetails(opdparctid);
				if(userProfile.getJobgroup().equals("4")){
					opdparctid = (inv_practid);
					userProfile = userProfileDAO.getUserprofileDetails(opdparctid);
				}
				fullname = userProfile.getInitial() + " " + userProfile.getFirstname() + " " + userProfile.getLastname();
				if(fullname!=null){
					if(fullname.equals("null null null")){
						fullname=null;
					}
					
				}
				}
				accountsForm.setIpdconsultant(fullname);
				accountsForm.setOwner_qualification(userProfile.getQualification());
				accountsForm.setDescription(userProfile.getSpecialization());	    
				
				String invoicelogtime = accountsDAO.getInvoiceLogTime(invoiceid);
				accountsForm.setDateTime(DateTimeUtils.getCommencingDate1(date)+" "+invoicelogtime);
				accountsForm.setMarkinvdate(DateTimeUtils.getCommencingDate1(date));
				createPdf(discount,totalAmount);
				
				String printdate=accountsDAO.getPrintDatebyinvoiceid(invoiceid);
				if(loginInfo.isSramhosp()) {
				if(!printdate.equals("")) {
					String dd1[]=printdate.split(" ");
					String printedBy = loginInfo.getUserId()+" "+DateTimeUtils.getCommencingDate1(dd1[0])+" "+dd1[1];
					accountsForm.setPrintedBy(printedBy);
				  }else {

					String dd[]=DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone()).split(" ");
					String printedBy = loginInfo.getUserId()+" "+DateTimeUtils.getCommencingDate1(dd[0])+" "+dd[1];  
					accountsForm.setPrintedBy(accountsForm.getDateTime());
				
				   }
				
				}
				
				else {

				String dd[]=DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone()).split(" ");
				String printedBy = loginInfo.getUserId()+" "+DateTimeUtils.getCommencingDate1(dd[0])+" "+dd[1];  
				accountsForm.setPrintedBy(printedBy);
				}
				
			
				
				session.setAttribute("creditAmount", 0.0);
				
				String itype = accountsDAO.getInvoiceTypeId(invoiceid);
				int ipdid = 0;
				
				
//				System.out.println(ipdid);
				BedDao bedDao=new JDBCBedDao(connection);
				IpdDAO ipdDAO= new JDBCIpdDAO(connection);
				
				
				if(itype.equals("2")||itype.equals("8")){
					 //ipdid = accountsDAO.getPatientipdid(Integer.parseInt(clientId));
					ipdid = Integer.parseInt(accountsdisc.getIpdid());
					Bed bed = bedDao.getEditIpdData(Integer.toString(ipdid));
					accountsForm.setDaycare(bedDao.isDayCare(""+ipdid));
					refdr=bed.getReferenceid();
					wardname=ipdDAO.getIpdWardName(bed.getWardid());
					bedname= ipdDAO.getIpdBedName(bed.getBedid());
					if(loginInfo.getIpd_abbr_access()==1){
						   String newipdabbr=ipdDAO.getIpdAbrivationIds(ipdid);
						   if(loginInfo.isSramhosp()) {
							   String admissionid=ipdDAO.getnewAdmissionid(ipdid);
							   accountsForm.setNewadmissionid(admissionid);
						   }
						  
					accountsForm.setNewipdabbr(newipdabbr);
					if(bed.getIpdseqno()!=null){
						if(Integer.parseInt(bed.getIpdseqno())>0){
							accountsForm.setIpdseqno(bed.getIpdseqno());
						}else{
							accountsForm.setIpdseqno(""+ipdid);
						}
					}else{
						accountsForm.setIpdseqno(""+ipdid);
					}
					}else{
					if(bed.getIpdseqno()!=null){
						if(Integer.parseInt(bed.getIpdseqno())>0){
							accountsForm.setIpdseqno(bed.getIpdseqno());
							accountsForm.setNewipdabbr(bed.getIpdseqno());
						}else{
							accountsForm.setIpdseqno(""+ipdid);
							accountsForm.setNewipdabbr(""+ipdid);
						}
					}else{
						accountsForm.setIpdseqno(""+ipdid);
						accountsForm.setNewipdabbr(""+ipdid);
						
					}
					}
					//set final diagnosis
					String textdiaggnosis=accountsDAO.gettextdiagnosis(ipdid);
					if(textdiaggnosis==null){
						textdiaggnosis="";
					}
					if(textdiaggnosis.equals("<br>")){
						textdiaggnosis="";
					}
					if(!textdiaggnosis.equals("")){
						accountsForm.setFinalDiagnosis(textdiaggnosis);
					}else{
					String fdid = accountsDAO.getFdID(ipdid);
					String finalDiagnosis = accountsDAO.getIpdFinalDiagnosis(fdid);
					accountsForm.setFinalDiagnosis(finalDiagnosis);
				}
					session.setAttribute("finaldiagnosis", accountsForm.getFinalDiagnosis());
					 userProfileDAO = new JDBCUserProfileDAO(connection);
					 if(bed.getPractitionerid()!=null){
						 if(inv_practid>0){
							 userProfile = userProfileDAO.getUserprofileDetails(inv_practid);
							 fullname = userProfile.getInitial() + " " + userProfile.getFirstname() + " " + userProfile.getLastname();
			
						 }else{
							 userProfile = userProfileDAO.getUserprofileDetails(Integer.parseInt(bed.getPractitionerid()));
							 fullname = userProfile.getInitial() + " " + userProfile.getFirstname() + " " + userProfile.getLastname();
			
						 }
						
					 }
					
					 if(fullname!=null){
							if(fullname.equals("null null null")){
								fullname=null;
							}
							
						}
					 
					 accountsForm.setIpdconsultant(fullname);
					 if(userProfile.getQualification()==null){
							userProfile.setQualification("");
						}else{
						 accountsForm.setDrqualification(userProfile.getQualification());
						}
					 String adate="";
					 String hh="";
					 String mm="";
					 if(bed.getAdmissiondate()!=null){
					String temp[] = bed.getAdmissiondate().split(" ");
					 adate = DateTimeUtils.getCommencingDate1(temp[0]);
					 String time[] = temp[1].split(":");
					 hh = time[0];
					 mm = time[1];
					
					adate = adate + " " + hh + ":" + mm;
					accountsForm.setAdmissionDate(adate);
					if(loginInfo.getIskunal()==1){
						int hourOfDay=Integer.parseInt(hh);
						   int minute=Integer.parseInt(mm);
						   String apmpm =  ((hourOfDay > 12) ? hourOfDay % 12 : hourOfDay) + ":" + (minute < 10 ? ("0" + minute) : minute) + " " + ((hourOfDay >= 12) ? "PM" : "AM");
						   adate= DateTimeUtils.getCommencingDate1(temp[0])+" "+apmpm;
						   accountsForm.setAdmissionDate(adate);
					}
					 }else{
						 accountsForm.setAdmissionDate("");
					 }
					 
					ClientDAO clientDAO = new JDBCClientDAO(connection);
					Bed dischargedata = clientDAO.getdischargedata(Integer.toString(ipdid));
					//String dischargeDate = clientDAO.getIpdDischargeDate(Integer.toString(ipdid));
					String dischargeDate = dischargedata.getDischargeDate();
					String dischargestatus = dischargedata.getDischargeStatus();
					
					//  19/02/2019 discharge status in description on apm_discharge_status
					DischargeStatusDAO statusDAO=new JDBCDischargeStatus(connection);
					if(dischargestatus!=null){
					String dischargehead=statusDAO.getDischargeStatusById(Integer.parseInt(dischargestatus));
					if(dischargehead==null){
						dischargehead="";
					}
					accountsForm.setDischargestatus(dischargestatus);
					accountsForm.setDischargehead(dischargehead);
					}
					if(dischargeDate!=null){
					if(!dischargeDate.equals("")){
						 String dtemp[] = dischargeDate.split(" ");
						 adate = dtemp[0];
						 String dtime[] = dtemp[1].split(":");
						 hh = dtime[0];
						 mm = dtime[1];
						
						 adate = adate + " " + hh + ":" + mm;
						accountsForm.setDischargeDate(adate);
						if(loginInfo.getIskunal()==1){
							int hourOfDay=Integer.parseInt(hh);
							   int minute=Integer.parseInt(mm);
							   String apmpm =  ((hourOfDay > 12) ? hourOfDay % 12 : hourOfDay) + ":" + (minute < 10 ? ("0" + minute) : minute) + " " + ((hourOfDay >= 12) ? "PM" : "AM");
							   dischargeDate= dtemp[0]+" "+apmpm;
							   accountsForm.setDischargeDate(dischargeDate);
						}
					}else{
						accountsForm.setDischargeDate("");
					}
					}else{
						accountsForm.setDischargeDate("");
					}
					
				}else{
					if(accountsForm.getAdmissionDate()==null){
						accountsForm.setAdmissionDate("");
					}
					accountsForm.setDischargeDate("");
					if(itype.equals("1")){
						NotAvailableSlotDAO notAvailableSlotDAO= new JDBCNotAvailableSlotDAO(connection);
						String opdidagnosis=notAvailableSlotDAO.getAllDiagnosisofOpd(""+opdid);
						if(opdidagnosis!=null){
							if(!opdidagnosis.equals("")){
								accountsForm.setFinalDiagnosis(""+opdidagnosis);
								session.setAttribute("finaldiagnosis", accountsForm.getFinalDiagnosis());		
							}
						}
						
					}
				}
				
				accountsForm.setIpdid(ipdid);
				
				//set prepared by
				int preparedbyid = accountsDAO.getInvoicePreparedBy(invoiceid);
				if(preparedbyid!=0){
					
					 userProfile = userProfileDAO.getUserprofileDetails(preparedbyid);
					 fullname = userProfile.getInitial() + " " + userProfile.getFirstname() + " " + userProfile.getLastname();
					accountsForm.setPreparedby(fullname);
				}else{
					accountsForm.setPreparedby("");
				}
				if(userProfile.getUserid()==null){
					accountsForm.setUserid("");
				}else{
					accountsForm.setUserid(userProfile.getUserid());
				}
				Accounts accounts = accountsDAO.getInvoiceDeleteInfo(invoiceid);
				accountsForm.setDeleted(""+accounts.getDeleted());
				accountsForm.setCancelNotes(accounts.getCancelNotes());
				accountsForm.setCancelDT(accounts.getCancelDT());
				accountsForm.setCancelUserid(accounts.getCancelUserid());
				accountsForm.setInvcetype(itype);
				int isparkingcharge = accountsDAO.checkIsParking(invoiceid);
				accountsForm.setIsparkingcharge(""+isparkingcharge);
				accountsForm.setDayseqno(accounts.getDayseqno());
				
				if(refdr==null){
					refdr="0";
				}
				if(refdr.equals("")){
					refdr="0";
				}
				
				UserProfile userProfile2= new UserProfile();
				refdr= userProfileDAO.getReferalDrName(refdr);
				if(refdr==null){
					refdr="";
				}
				accountsForm.setRefereddr(refdr);
				//  showing third party fields in invoice print 10/12/2018
				Client client2 = accountsDAO.getTPClientData(clientId);
				String companyname=accountsDAO.gettypenamebyid(client2.getThirdPartyType());
				String tpname=accountsDAO.gettpnamebyid(client2.getThirdPartyType());
				if(payby1.equalsIgnoreCase("Third Party")){
				accountsForm.setPayedbytp(client2.getWhopay());
				if(client2.getCompname()==null){
					client2.setCompname("");
				}if(client2.getNeisno()==null){
					client2.setNeisno("");
				}if(client2.getDesignationbytp()==null){
					client2.setDesignationbytp("");
				}if(client2.getRelationvbytpe()==null){
					client2.setRelationvbytpe("");
				}if(client2.getUnitstation()==null){
					client2.setUnitstation("");
				}if(client2.getClaimbytp()==null){
					client2.setClaimbytp("");
				}if(client2.getColliery()==null){
					client2.setColliery("");
				}if(client2.getAreabytp()==null){
					client2.setAreabytp("");
				}if(client2.getPolicyholder()==null){
					client2.setPolicyholder("");
				}
				if(companyname.equals("CGHS")){
					
						accountsForm.setStatusfortp(true);
						accountsForm.setCompanyname("CGHS");
					}
					else if(companyname.equals("WCL")){
						
							accountsForm.setStatusfortp(true);
							accountsForm.setCompanyname("WCL");
						}else if(tpname.equals("INSURANCE COMPANY")){
							accountsForm.setStatusfortp(true);
							accountsForm.setCompanyname(companyname);
						}
						else{
							accountsForm.setStatusfortp(false);
						}
				accountsForm.setKuanlInvSeq(accountsDAO.getSeqNoForKunal(accountsForm.getDate(), ""+invoiceid, itypenew));
				accountsForm.setEmployeenamebytp(client2.getCompname());
				accountsForm.setNeiscardno(client2.getNeisno());
				accountsForm.setDesignation(client2.getDesignationbytp());
				accountsForm.setRelationofuser(client2.getRelationvbytpe());
				accountsForm.setUnit_station(client2.getUnitstation());
				accountsForm.setClaimid(client2.getClaimbytp());
				accountsForm.setColliery(client2.getColliery());
				accountsForm.setAreatp(client2.getAreabytp());
				accountsForm.setPolicyholder(client2.getPolicyholder());
				}
				if(!client.getReference().equals("")){
					String refrencedrname=accountsDAO.getrefdrname(client.getReference());
					accountsForm.setRefereddr(refrencedrname);
				}else{
					accountsForm.setRefereddr("");
				}
				
				accountsForm.setPhysical_paymentid(accountsDAO.getMultiplePaymentIdAgainstInvoice(""+invoiceid));
				ClientDAO clientDAO=new JDBCClientDAO(connection);
				if(loginInfo.isBalgopal()){
				Client client3=clientDAO.getPatientBMIData(clientId,opdid);
				accountsForm.setHeight(client3.getHeight());
				accountsForm.setWeight(client3.getWeight());
				accountsForm.setBmi(client3.getBmi());
				accountsForm.setHeadcir(client3.getHead_cir());
				accountsForm.setTempr(client3.getTemprature());
				}
				String notes1=clientDAO.getopdnotes(String.valueOf(opdid));
				session.setAttribute("notes", notes1);
				
				boolean statusrequestdiscamt = accountsDAO.getRequestedDiscountStatus(invoiceid);
				double discountamt = accountsDAO.getRequestedDiscountAmount(invoiceid);
				accountsForm.setStatusrequestdiscamt(statusrequestdiscamt);
				accountsForm.setDiscountamt(discountamt);
				String paymentNote = accountsDAO.getInvoicePaymentNote(invoiceid);
				accountsForm.setPaymentNote(paymentNote);
				
				accountsForm.setPaymentLMHSequence(accountsDAO.getMultiplePaymentSequenceLMH(invoiceid));
				
			
			}catch(Exception e){
				e.printStackTrace();
			}finally{
				
				connection.close();
			}
			
			accountsForm.setActionType(action);
			if(wardname==null){
				wardname="";
			}
			if(bedname==null){
				bedname="";
			}
		accountsForm.setWardname(wardname);
		accountsForm.setBedname(bedname);
		
		
			if(loginInfo.isPcsAdmin()){
				return "pcsbill";
			}
			if(billsummary.equals("1")){
				if(loginInfo.isTotehosp()) {
				return "billsummary_tote";	
				}else {
				return "billsummary";
				}
			}
			
			if(istpservices){
				return "tpservices";
			}
			
			if(action.equals("show")){
				if(loginInfo.isSramhosp() && accountsForm.getInvoicenameid().equals("1")) {
					return "showpreview1";
				}else if(loginInfo.isMarkhosp()){
					String action1=request.getParameter("action");
					
					if(DateTimeUtils.isNull( action1).equals("1")) {
						return "showpreviewmark1";
					}else {
						return "showpreviewmark";
					}
					
				}else {
					return "showpreview";
				}
				
			}else if(action.equals("viewpayment")){
				accountsForm.setInvoiceid(Integer.parseInt(request.getParameter("pid")));
				return "showpreview";
			}
			else if(action.equals("print")){
				return "printpreview"; 
			}
			
			else{
				return "showpreview";
			}
		
			}



	public String advprint() throws Exception{
		if(!verifyLogin(request)){
			return "login";
		}
		String invoiceid = request.getParameter("id");
		
		Connection connection = null;
		Vector<Accounts>previewChargesList = new Vector<Accounts>();
		Vector<Accounts>assesmentList = new Vector<Accounts>();
		try{
			connection = Connection_provider.getconnection();
			AccountsDAO accountsDAO = new JDBCAccountsDAO(connection);
			IpdDAO ipdDAO=new JDBCIpdDAO(connection);
			int ipdopdseqno=accountsDAO.getIpdOpdSeqNo(Integer.parseInt(invoiceid));
			accountsForm.setIpdopdseqno(String.valueOf(ipdopdseqno));
			Accounts accounts = accountsDAO.getAdvanceInvoiceDetails(invoiceid);
			accountsForm.setPhysical_paymentid(accountsDAO.getPhysicalpaymentIdAdvRef(invoiceid));
			AdditionalDAO additionalDAO=new JDBCAdditionalDAO(connection);
			int seqbalgopal=additionalDAO.getBalgopalSeqNum(""+invoiceid);
			if(loginInfo.isBalgopal()){
				accountsForm.setPhysical_paymentid(""+seqbalgopal);
				
				//new finac year se
				String finseq=additionalDAO.getBalgopalFinacialSeqNum(""+invoiceid);
				accountsForm.setPhysical_paymentid(finseq);
			}
			Clinic clinic = new Clinic();
			ClinicDAO clinicDAO  = new JDBCClinicDAO(connection);
			clinic = clinicDAO.getCliniclistDetails(loginInfo.getClinicid());
			accountsForm.setSubmitInvoiceNotes(accounts.getRemark());
			ArrayList<Clinic> locationAdressList = accountsDAO.getLocationAddress(loginInfo.getClinicid());
			//String address = accountsDAO.getLocationAddress(locationid,loginInfo.getId());
			String invoiceseialno= accountsDAO.getAdvanceRefrundSerialNo(invoiceid);
			accountsForm.setClinicName(clinic.getClinicName());
			accountsForm.setClinicOwner(clinic.getClinicOwner());
			accountsForm.setOwner_qualification(clinic.getOwner_qualification());
			//accountsForm.setClinicaddress(address);
			accountsForm.setLandLine(clinic.getLandLine());
			accountsForm.setWebsiteUrl(clinic.getWebsiteUrl());
			accountsForm.setClinicemail(clinic.getEmail());
			accountsForm.setLocationAdressList(locationAdressList);
			accountsForm.setClinicLogo(clinic.getUserImageFileName());
			accountsForm.setPractitionerName(accounts.getPractitionerName());
			
			accountsForm.setAdvrefsrno(invoiceseialno);
			//setting master chartype data
			ArrayList<Master>masterAssessmentList = accountsDAO.getAdvanceMasterAssessmentList(Integer.parseInt(invoiceid));
			accountsForm.setMasterAssessmentList(masterAssessmentList);
			
			assesmentList = accountsDAO.getAdvancePreviewChargesList(Integer.parseInt(invoiceid));
			
			
			double totalAmount = 0;
			String sectionroom = "";
			for(Accounts totalAcc : assesmentList){
				double charge = Double.parseDouble(totalAcc.getCharges()) * totalAcc.getQuantity();
				totalAmount = totalAmount + charge;
				
				// sectionroom = totalAcc.getSectionroom();
			}
			
			
		
			
			String clientid = Integer.toString(accounts.getClientid());
			Client client = accountsDAO.getClientData(clientid);
			accountsForm.setClient(client.getTitle() + " " + client.getFirstName() + " "+client.getMiddlename()+" " + client.getLastName());
/*			if(client.getSecondLineaddress()!=null){
				accountsForm.setAddress(client.getAddress() + "," +client.getSecondLineaddress() + "," + client.getTown() + "," + client.getPostCode());
			}else{
				accountsForm.setAddress(client.getAddress() + "," + client.getTown() + "," + client.getPostCode());
			}*/
			
			accountsForm.setAddress(client.getAddress());
			accountsForm.setClienttown(client.getTown());
			accountsForm.setClientpostcode(client.getPostCode());
			
			/*
			 * String age = DateTimeUtils.getAge1(client.getDob());
			 * accountsForm.setAgegender(age + "/" + client.getGender());
			 */
			
			accountsForm.setDob(client.getDob());
			accountsForm.setAbrivationid(client.getAbrivationid());
			accountsForm.setClientId(clientid);
			accountsForm.setEmail(client.getEmail());
			accountsForm.setMobno(client.getMobNo());
			accountsForm.setPolicyNo(client.getPolicyNo());
			accountsForm.setPayby(accounts.getPayby());
			accountsForm.setInvoiceid(accounts.getId());
			accountsForm.setPaymode(accounts.getPaymentmode());
			accountsForm.setPreparedby(accounts.getUserid());
			accountsForm.setDate(accounts.getDate());
			
			String age="";
			if(loginInfo.isBalgopal()){
			 //for balgopal age as per payment date	
			 if(accounts.getDate()!=null){
				 if(!accounts.getDate().equals("")){
					 String c=accounts.getDate();
					 String h[]= c.split(" ");
					 c= DateTimeUtils.getCommencingDate1(h[0]);
					 age=DateTimeUtils.getAge1onPaymentDate(client.getDob(), h[0]);
					 accountsForm.setAgegender(age + "/" + client.getGender());
				 }
			 }
			}else {
				age = DateTimeUtils.getAgeyear(client.getDob());
				accountsForm.setAgegender(age + "/" + client.getGender());
			}
			//accountsForm.setDiscount(DateTimeUtils.changeFormat(discount));
		
			
			
			//accountsForm.setAssesmentList(assesmentList);
			accountsForm.setTotalAmount(totalAmount);
			
			//Decimal Account
			accountsForm.setTotalAmountx(DateTimeUtils.changeFormat(totalAmount));
			NumberToWord obj = new NumberToWord();
			accountsForm.setTotalinword(obj.convert((int) totalAmount));
			
			//accountsForm.setInvoiceid(invoiceid);
		
			accountsForm.setPayeename(client.getTitle() + " " + client.getFirstName() + " "+client.getMiddlename()+" " + client.getLastName());
			if(client.getSecondLineaddress()!=null){
				accountsForm.setPayeeadress(client.getAddress() +","+client.getSecondLineaddress());
			}else{
				accountsForm.setPayeeadress(client.getAddress());
			}
			
			accountsForm.setPayeeTown(client.getTown());
			accountsForm.setPayeePostcode(client.getPostCode());
			accountsForm.setPayeeEmail(client.getEmail());
			accountsForm.setPayeeConatctNo(client.getMobNo());
			accountsForm.setFathername(client.getFathername());
			accountsForm.setMothername(client.getMothername());
			String ipdabrivation=ipdDAO.getIpdAbrivationIdsByClientid(Integer.parseInt(clientid));
			String ipdid=ipdDAO.getLAstIpdIdByClient(clientid);
//			accountsForm.setIpdabbrivationid(ipdabrivation);
			if(loginInfo.getIpd_abbr_access()==1){
				 String newipdabbr=ipdDAO.getIpdAbrivationIds(Integer.parseInt(ipdid));
				   accountsForm.setNewipdabbr(newipdabbr);
				   accountsForm.setIpdseqno(ipdid);
			}else{
			accountsForm.setIpdseqno(ipdDAO.getipdseqno( ipdid));
			 accountsForm.setNewipdabbr(ipdid);
			}
		}catch (Exception e) {
			e.printStackTrace();
		}finally{
			
			connection.close();
		}
		
		
		
		return "advprint";
	}
	
	public String preview() throws Exception{
		
		if(!verifyLogin(request)){
			return "login";
		}
		
		String action = request.getParameter("action");
		DateTimeUtils dateTimeUtils = new DateTimeUtils();
		
		int invoiceid = (Integer)session.getAttribute("chargesInvoiceid");
		String clientid = (String)session.getAttribute("clientId");
		String pa = (String)session.getAttribute("payAmount");
		double payAmount = Double.parseDouble(pa);
		String payby = (String)session.getAttribute("payby");
		
		double discount = 0;
		
		
		
		
		
		Vector<Accounts>previewChargesList = new Vector<Accounts>();
		Vector<Accounts>assesmentList = new Vector<Accounts>();
		
		Connection connection = null;
		try{
			
			connection = Connection_provider.getconnection();
			AccountsDAO accountsDAO = new JDBCAccountsDAO(connection);
			ChargesAccountProcessingDAO chargesAccountProcessingDAO=new JDBCChargeAccountProcesDAO(connection);
			ArrayList<Accounts> listcharges=accountsDAO.getChargeAssesments(invoiceid);
			for (Accounts accounts : listcharges) {
				int r=accountsDAO.checkChargeAssements(accounts);
			}			
			chargesAccountProcessingDAO.getChargeAssesmentEntry(invoiceid);
			
			String refdr="";
			
			
			if(session.getAttribute("discount")!=null){
				discount = (Double)session.getAttribute("discount");
			}
			else{
				discount = accountsDAO.getDiscount(invoiceid);
			}
			String notes = accountsDAO.getNotes(invoiceid);
			String paymode = accountsDAO.getPaymentMode(invoiceid);
			accountsForm.setPaymode(paymode);
			
			String date = accountsDAO.getInvoiceDate(invoiceid);
			String invoiceTime= accountsDAO.getInvoiceTime(invoiceid);
			accountsForm.setDate(DateTimeUtils.getInvoiceCommencingDate(date));
			accountsForm.setInvoiceTime(invoiceTime);
			String practId= accountsDAO.getPractitionerofInvoice(invoiceid);
			int ipdopdseqno=accountsDAO.getIpdOpdSeqNo(invoiceid);
			accountsForm.setIpdopdseqno(String.valueOf(ipdopdseqno));
			if(loginInfo.isBalgopal() || loginInfo.isSeq_no_gen()){
				accountsForm.setIpdopdseqno(accountsDAO.getFinancialSeqNoOfInvoice(""+invoiceid));
			}
			accountsForm.setSubmitInvoiceNotes(notes);
			session.setAttribute("invnotes", notes);
			String dateTime[]= DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone()).split(" ");
			accountsForm.setDateTime(DateTimeUtils.getCommencingDate1(dateTime[0])+" "+dateTime[1]);
			//set Location
			String locationid = accountsDAO.getLocationID(invoiceid);
			String locationName = accountsDAO.getLocationName(locationid);
			String chequeno=accountsDAO.getChequeNumber(invoiceid);
			
			accountsForm.setLocationName(locationName);
			accountsForm.setChequeno(chequeno);
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
			
			//setting master chartype data
			ArrayList<Master>masterAssessmentList = accountsDAO.getKunalMasterAssessmentList(invoiceid,"0",loginInfo);
			accountsForm.setMasterAssessmentList(masterAssessmentList);
			if(masterAssessmentList.size()>0){
				accountsForm.setDiscstatus1(masterAssessmentList.get(masterAssessmentList.size()-1).isDiscstatus());
				
				MasterDAO masterDAO= new JDBCMasterDAO(connection);
				accountsForm.setTaxtype1(masterAssessmentList.get(masterAssessmentList.size()-1).getTaxtype1());
				accountsForm.setTaxtype1(Math.round((accountsForm.getTaxtype1()*100.0)/100.0));
				accountsForm.setTax1(masterDAO.getTaxnamebyType("1"));
				accountsForm.setTaxtype2(masterAssessmentList.get(masterAssessmentList.size()-1).getTaxtype2());
				accountsForm.setTaxtype2(Math.round((accountsForm.getTaxtype2()*100.0)/100.0));
				accountsForm.setTax2(masterDAO.getTaxnamebyType("2"));
				accountsForm.setTaxtype3(masterAssessmentList.get(masterAssessmentList.size()-1).getTaxtype3());
				accountsForm.setTax3(masterDAO.getTaxnamebyType("3"));
				accountsForm.setTaxtype3(Math.round((accountsForm.getTaxtype3()*100.0)/100.0));
			}
			
			String pkgstr = "nopkg";
			if(payby.equals(Constants.PAY_BY_THIRD_PARTY)){
				pkgstr = accountsDAO.getPkgStr(invoiceid);
				if(!pkgstr.equals("")){
					ArrayList<Master>pkgAssessmentList = accountsDAO.getPkgMasterAssessmentList(pkgstr,invoiceid);
					accountsForm.setPkgAssessmentList(pkgAssessmentList);
				}
				
			}
			accountsForm.setPkgstr(pkgstr);
			if(pkgstr.equals("nopkg")){
				pkgstr="";
			}
			if(pkgstr==null){
				pkgstr="";
			}
			
			//advance payment recipt list
			ArrayList<Accounts>prepaymentList = accountsDAO.getPrePaymentList(invoiceid,clientid);
			accountsForm.setPrepaymentList(prepaymentList);
			if(loginInfo.getIskunal()==1){
				assesmentList = accountsDAO.getPreviewChargesListKunal(invoiceid);
			}else if(!pkgstr.equals("")){
				if(loginInfo.isPackage_access()){
					assesmentList = accountsDAO.getPreviewChargesListKunal(invoiceid);
				}
				
			}else{
				assesmentList = accountsDAO.getPreviewChargesList(invoiceid);
			}
			
			
			String invoicenameid=accountsDAO.getInvoiceTypeId(invoiceid);
			
			accountsForm.setInvoicenameid(invoicenameid);
			if(invoicenameid.equals("3")){
				accountsForm.setInvdtsts(true);
			}else{
				accountsForm.setInvdtsts(false);
			}
			String invoicename=accountsDAO.getInvoiceName(invoicenameid);
			accountsForm.setInvoicename(invoicename);
			Accounts fromandtodate=accountsDAO.getFromtodateforHD(invoiceid);
			
			String fromDate="";
			String toDate="";
			int i=0;
			if(invoicename.equals("HD")){
				
				
				/*ArrayList<String> strings=new ArrayList<String>();
				 for(Master master:masterAssessmentList){
					   
					     for(Accounts accounts: master.getAssesmentList()){
					    	 
					    	 if(accounts.getCommencing()!=null){ 
					    	 
					    		 strings.add(accounts.getCommencing());
					    	 }
					    	
					     }       
				 } 
				 
				 ArrayList<Date> dates=new ArrayList<Date>();
					
				 SimpleDateFormat dateFormat=new SimpleDateFormat("dd-MM-yyyy");
				 
				 for(String dstr:strings){
						
		               Date da=dateFormat.parse(dstr);
					   dates.add(da);
				}
				 
				 Collections.sort(dates,new Comparator<Date>() {

						@Override
						public int compare(Date arg0, Date arg1) {
						  
							 if(arg0.getTime()>arg1.getTime()) {
								 
								 return 1;
							 } else if(arg0.getTime()<arg1.getTime()){
								 
								 return -1;
							 } else {
								 return 0;
							 }
						}
					});
				 
				 for(Date da:dates){
			    	 
			    	  String datestr=dateFormat.format(da);
			    	  if(i==0){
			    		  fromDate=datestr;
			    	  }
			    	  
			    	  toDate=datestr;
			    	//  System.out.println(datestr);
			    	  i++;
			     }*/
				
				 accountsForm.setFromDate(fromandtodate.getFromDate());
	             accountsForm.setToDate(fromandtodate.getToDate());
				
			}
			
			double totalAmount = 0;
			String sectionroom = "";
			double maintot=0;
			double chargedisc=0;
			ArrayList<Accounts> newdisclist=new ArrayList<Accounts>();
//			for(Accounts totalAcc : assesmentList){
//				double charge = Double.parseDouble(totalAcc.getCharges()) * totalAcc.getQuantity();
//				chargedisc=chargedisc+Double.parseDouble(totalAcc.getChargedisc());
//				totalAmount = totalAmount + charge;
//				
//				 //sectionroom = totalAcc.getSectionroom();
//			}
			for(Accounts totalAcc : assesmentList){
				double charge = Double.parseDouble(totalAcc.getCharges()) * totalAcc.getQuantity();
				totalAmount = totalAmount + charge;
				if(totalAcc.getChargedisc()==null){
					totalAcc.setChargedisc("0");
				}
				chargedisc=chargedisc+Double.parseDouble(totalAcc.getChargedisc());
			
				if(loginInfo.getIskunal()==1 ){
							totalAmount = totalAmount-Double.parseDouble(totalAcc.getDiscamt());
							maintot=maintot+charge;
//							
//				//sectionroom = totalAcc.getSectionroom();
			}else if(!pkgstr.equals("")){
				if(loginInfo.isPackage_access()){
					totalAmount = totalAmount-Double.parseDouble(totalAcc.getDiscamt());
					maintot=maintot+charge;
				}
				}
			}
			
			if(loginInfo.getIskunal()==1){
				newdisclist=accountsDAO.getMasternameforDisc(invoiceid);
//				Accounts accounts3=accountsDAO.getMasternameforDisc1(invoiceid);
//				newdisclist.add(accounts3);
				}
				if(!pkgstr.equals("")){
					if(loginInfo.isPackage_access()){
						newdisclist=accountsDAO.getMasternameforDisc(invoiceid);
					}
				}
			
			
			Client client = accountsDAO.getClientData(clientid);
			accountsForm.setClient(client.getTitle() + " " + client.getFirstName() + " "+client.getMiddlename()+" " + client.getLastName());
/*			if(client.getSecondLineaddress()!=null){
				accountsForm.setAddress(client.getAddress() + "," +client.getSecondLineaddress() + "," + client.getTown() + "," + client.getPostCode());
			}else{
				accountsForm.setAddress(client.getAddress() + "," + client.getTown() + "," + client.getPostCode());
			}*/
			accountsForm.setFathername(client.getFathername());
			String dd[]=DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone()).split(" ");
			String printedBy = loginInfo.getUserId()+" "+DateTimeUtils.getCommencingDate1(dd[0])+" "+dd[1];  
			accountsForm.setPrintedBy(printedBy);
			String invoicelogtime = accountsDAO.getInvoiceLogTime(invoiceid);
			accountsForm.setDateTime(DateTimeUtils.getCommencingDate1(dd[0])+" "+invoicelogtime);
			
			accountsForm.setAddress(client.getAddress());
			accountsForm.setClienttown(client.getTown());
			accountsForm.setClientpostcode(client.getPostCode());
			
			//String age = dateTimeUtils.getAge(client.getDob());
			String agegender="";
			String age = DateTimeUtils.getAge1(client.getDob());
			
				agegender = age + "Years" + " / " + client.getGender();	
			accountsForm.setAgegender(agegender);
			accountsForm.setAgegender(DateTimeUtils.getAge1(client.getDob())+"/"+client.getGender());
			accountsForm.setAbrivationid(client.getAbrivationid());
			accountsForm.setDob(client.getDob());
			accountsForm.setClientId(clientid);
			accountsForm.setEmail(client.getEmail());
			accountsForm.setMobno(client.getMobNo());
			accountsForm.setPolicyNo(client.getPolicyNo());
			accountsForm.setPayby(payby);
			accountsForm.setDiscount(dateTimeUtils.changeFormat(discount));
		
			
			
			//accountsForm.setAssesmentList(assesmentList);
			accountsForm.setTotalAmount(totalAmount);
			
			//Decimal Account
			accountsForm.setTotalAmountx(dateTimeUtils.changeFormat(totalAmount));
			double totalamt=DateTimeUtils.convertToDouble(accountsForm.getTotalAmountx());
			double totaltax=(accountsForm.getTaxtype1())+accountsForm.getTaxtype2()+accountsForm.getTaxtype3();
			double total=totalamt-totaltax;
			accountsForm.setAmtWithouttax(dateTimeUtils.changeFormat(total));
			
			accountsForm.setInvoiceid(invoiceid);
			
			
			if(payby.equalsIgnoreCase("Third Party") || payby.equals("1")){
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
				
				
				
			}
			else{
				//accountsForm.setPayeename(client.getTitle() + " " + client.getFirstName() + " "+client.getMiddlename()+" " + client.getLastName());
				accountsForm.setPayeename("Self");
				if(client.getSecondLineaddress()!=null){
					accountsForm.setPayeeadress(client.getAddress() +","+client.getSecondLineaddress());
				}else{
					accountsForm.setPayeeadress(client.getAddress());
				}
				
				accountsForm.setPayeeTown(client.getTown());
				accountsForm.setPayeePostcode(client.getPostCode());
				accountsForm.setPayeeEmail(client.getEmail());
				accountsForm.setPayeeConatctNo(client.getMobNo());
				
			}
			
			Accounts accountsdisc = accountsDAO.getAccDiscData(invoiceid);
			int disctype = Integer.parseInt(accountsdisc.getDisctype());
			//update discount
			if(disctype==1){
				int upd = accountsDAO.updatePerDicount(invoiceid);
			}else{
				int upd = accountsDAO.updateAmtDiscount(invoiceid);
			}
			double discamt = Double.parseDouble(accountsdisc.getDiscamt());
			accountsForm.setDisctype(Integer.toString(disctype));
			
			double totalPaymentReceived = accountsDAO.getTotalPaymentReceived(invoiceid);
			//if refund against invoice
			StatementDAO statementDAO = new JDBCStatementDAO(connection);
			double refundAmt = statementDAO.getRefundAmtAgainsInvoice(invoiceid);
			totalPaymentReceived = totalPaymentReceived - refundAmt;
			accountsForm.setCreditAmt(dateTimeUtils.changeFormat(totalPaymentReceived));
			//double credit = totalAmount - payAmount;
			double discountAmt = (totalAmount * (discount/100));
			if(disctype==1){
				discountAmt = discamt;
			}
			if(chargedisc>0){
			double totdisc=0;
			if(chargedisc==0){
			 totdisc = (discount*totalAmount)/100;
			if(disctype==1){
				totdisc = discamt ;
			}
			
			}
			if (chargedisc>0) {
				
				totdisc=chargedisc;
			}
			discountAmt=discountAmt+totdisc;
			}
			
			accountsForm.setDicsAmount(DateTimeUtils.changeFormat(discountAmt));
			double amountDue = totalAmount - discountAmt;
			accountsForm.setNetpayamount(dateTimeUtils.changeFormat(amountDue));
			NumberToWord obj = new NumberToWord();
			accountsForm.setTotalinword(obj.convert((int) amountDue));
			amountDue = amountDue - totalPaymentReceived;
			//amountDue = Double.parseDouble(new DecimalFormat("##.##").format(amountDue));
			if(payAmount >0){
				
				accountsForm.setBalance(amountDue);
				
				//Decimal Account
				accountsForm.setBalancex(dateTimeUtils.changeFormat(amountDue));
			}else{
				accountsForm.setDebitAmounnt(dateTimeUtils.changeFormat(totalAmount));
				accountsForm.setBalance(amountDue);
				
				//Decimal Account
				accountsForm.setBalancex(dateTimeUtils.changeFormat(amountDue));
				
			}
			
			
			//policy excess code
			if(payby.equals(Constants.PAY_BY_THIRD_PARTY)){
				Accounts accounts = accountsDAO.checkInvoiceHasPolicyExcess(invoiceid);
				
				if(accounts.isPolicyExcess()){
					
					double balance = amountDue - Double.parseDouble(accounts.getCharges());
					//totalAmount = totalAmount - Double.parseDouble(accounts.getCharges());
					accountsForm.setPolicyexcesscode(1);
					accountsForm.setPolicyExcess(DateTimeUtils.changeFormat(Double.valueOf(accounts.getCharges())));
					//accountsForm.setBalancex(dateTimeUtils.changeFormat(balance));
				}
			}
						
			
			accountsForm.setPayAmount(totalPaymentReceived);
			
			//Decimal Account
			accountsForm.setPayAmountx(dateTimeUtils.changeFormat(totalPaymentReceived));
			
			//set deliver status
			int update = accountsDAO.updateDeliverStatus(invoiceid,"1");
			
			//logdata
			//AccountLogDAO accountLogDAO = new JDBCAccountLogDAO(connection);
			//int update1 = accountLogDAO.updateDeliverStatus(invoiceid,"1");
			
			Clinic clinic = new Clinic();
			ClinicDAO clinicDAO  = new JDBCClinicDAO(connection);
			clinic = clinicDAO.getCliniclistDetails(loginInfo.getClinicid());
			
			ArrayList<Clinic> locationAdressList = accountsDAO.getLocationAddress(loginInfo.getClinicid());
			//String address = accountsDAO.getLocationAddress(locationid,loginInfo.getId());
			
			accountsForm.setClinicName(clinic.getClinicName());
			accountsForm.setClinicOwner(clinic.getClinicOwner());
			accountsForm.setOwner_qualification(clinic.getOwner_qualification());
			//accountsForm.setClinicaddress(address);
			accountsForm.setLandLine(clinic.getLandLine());
			accountsForm.setWebsiteUrl(clinic.getWebsiteUrl());
			accountsForm.setClinicemail(clinic.getEmail());
			accountsForm.setLocationAdressList(locationAdressList);
			accountsForm.setClinicLogo(clinic.getUserImageFileName());
			String itypenew = accountsDAO.getInvoiceTypeId(invoiceid);
			
			//set balgopal address
			Clinic ccbg =  locationAdressList.get(0);
			session.setAttribute("balgopaladdress", ccbg.getAddress());
			session.setAttribute("balgopalcname", ccbg.getClinicName());
			accountsForm.setClinicLogo(clinic.getUserImageFileName());
			
			
			//set opd practitionerid
			int opdparctid = accountsDAO.getPeactOpdID(invoiceid);
			int opdid =accountsDAO.getOpdIDFromInvoiceId(invoiceid);
			if(opdparctid==0){
				int inprcatid = accountsDAO.getInvoiceTypeDrId(invoiceid);
				if(inprcatid!=0){
					opdparctid = inprcatid;
				}else{
					opdparctid = 1;
				}
				
			}
			
			if(practId!=null){
				
				if(!practId.equals("")){
					  opdparctid= Integer.parseInt(practId);
				}
			}
			
			String fullname ="";
			UserProfileDAO userProfileDAO = new JDBCUserProfileDAO(connection);
			UserProfile userProfile = new UserProfile();
			if(opdparctid!=0){
			userProfile = userProfileDAO.getUserprofileDetails(opdparctid);
			if(userProfile.getJobgroup().equals("4")){
				opdparctid = Integer.parseInt(userProfile.getDoctor());
				userProfile = userProfileDAO.getUserprofileDetails(opdparctid);
			}
				
			fullname = userProfile.getInitial() + " " + userProfile.getFirstname() + " " + userProfile.getLastname();
			if(fullname!=null){
				if(fullname.equals("null null null")){
					fullname=null;
				}
				
			}
			}
			accountsForm.setOpdid(""+opdid);
			accountsForm.setIpdconsultant(fullname);
			
			IpdDAO ipdDAO=new JDBCIpdDAO(connection);
			createPdf(discount,totalAmount);
			
			String itype = accountsDAO.getInvoiceTypeId(invoiceid);
			int ipdid = 0;
			
//			System.out.println(ipdid);
			
			BedDao bedDao=new JDBCBedDao(connection);
			
			if(itype.equals("2")||itype.equals("8")){
				 ipdid = accountsDAO.getPatientipdid(Integer.parseInt(clientid));
				Bed bed = bedDao.getEditIpdData(Integer.toString(ipdid));
				accountsForm.setDaycare(bedDao.isDayCare(""+ipdid));
				if(loginInfo.getIpd_abbr_access()==1){
					   String newipdabbr=ipdDAO.getIpdAbrivationIds(ipdid);
					   accountsForm.setNewipdabbr(newipdabbr);
					   if(Integer.parseInt(bed.getIpdseqno())>0){
							accountsForm.setIpdseqno(bed.getIpdseqno());
						}else{
							accountsForm.setIpdseqno(""+ipdid);
						}
				}else{
				if(Integer.parseInt(bed.getIpdseqno())>0){
					accountsForm.setIpdseqno(bed.getIpdseqno());
					 accountsForm.setNewipdabbr(bed.getIpdseqno());
				}else{
					accountsForm.setIpdseqno(""+ipdid);
					accountsForm.setNewipdabbr(""+ipdid);
				}
				}
				refdr=bed.getReferenceid();
				
				//set final diagnosis
//				String fdid = accountsDAO.getFdID(ipdid);
//				String finalDiagnosis = accountsDAO.getIpdFinalDiagnosis(fdid);
//				accountsForm.setFinalDiagnosis(finalDiagnosis);
				String textdiaggnosis=accountsDAO.gettextdiagnosis(ipdid);
				if(textdiaggnosis==null){
					textdiaggnosis="";
				}
				if(textdiaggnosis.equals("<br>")){
					textdiaggnosis="";
				}
				if(!textdiaggnosis.equals("")){
					accountsForm.setFinalDiagnosis(textdiaggnosis);
				}else{
				String fdid = accountsDAO.getFdID(ipdid);
				String finalDiagnosis = accountsDAO.getIpdFinalDiagnosis(fdid);
				accountsForm.setFinalDiagnosis(finalDiagnosis);
			}
				session.setAttribute("finaldiagnosis", accountsForm.getFinalDiagnosis());
				 userProfileDAO = new JDBCUserProfileDAO(connection);
				 userProfile = userProfileDAO.getUserprofileDetails(Integer.parseInt(bed.getPractitionerid()));
				 fullname = userProfile.getInitial() + " " + userProfile.getFirstname() + " " + userProfile.getLastname();
				
				 if(fullname!=null){
						if(fullname.equals("null null null")){
							fullname=null;
						}
						
					}
				 accountsForm.setIpdconsultant(fullname);
				if(userProfile.getQualification()==null){
					userProfile.setQualification("");
				}else{
				 accountsForm.setDrqualification(userProfile.getQualification());
				}
				String temp[] = bed.getAdmissiondate().split(" ");
				String adate = DateTimeUtils.getCommencingDate1(temp[0]);
				String time[] = temp[1].split(":");
				String hh = time[0];
				String mm = time[1];
				
				adate = adate + " " + hh + ":" + mm;
				accountsForm.setAdmissionDate(adate);
				if(loginInfo.getIskunal()==1){
					int hourOfDay=Integer.parseInt(hh);
					   int minute=Integer.parseInt(mm);
					   String apmpm =  ((hourOfDay > 12) ? hourOfDay % 12 : hourOfDay) + ":" + (minute < 10 ? ("0" + minute) : minute) + " " + ((hourOfDay >= 12) ? "PM" : "AM");
					   adate= DateTimeUtils.getCommencingDate1(temp[0])+" "+apmpm;
					   accountsForm.setAdmissionDate(adate);
				}else{
					accountsForm.setAdmissionDate("");
				}
				ClientDAO clientDAO = new JDBCClientDAO(connection);
				Bed dischargedata = clientDAO.getdischargedata(Integer.toString(ipdid));
				//String dischargeDate = clientDAO.getIpdDischargeDate(Integer.toString(ipdid));
				String dischargestatus = dischargedata.getDischargeStatus();
				//  19/02/2019 discharge status in description on apm_discharge_status
				DischargeStatusDAO statusDAO=new JDBCDischargeStatus(connection);
				String dischargehead=statusDAO.getDischargeStatusById(Integer.parseInt(dischargestatus));
				if(dischargehead==null){
					dischargehead="";
				}
				accountsForm.setDischargestatus(dischargestatus);
				accountsForm.setDischargehead(dischargehead);
				String dischargeDate = clientDAO.getIpdDischargeDate(Integer.toString(ipdid));
				
				if(!dischargeDate.equals("")){
					 String dtemp[] = dischargeDate.split(" ");
					 adate = DateTimeUtils.getCommencingDate1(dtemp[0]);
					 String dtime[] = dtemp[1].split(":");
					 hh = dtime[0];
					 mm = dtime[1];
					
					 adate = adate + " " + hh + ":" + mm;
					accountsForm.setDischargeDate(adate);
					if(loginInfo.getIskunal()==1){
						int hourOfDay=Integer.parseInt(hh);
						   int minute=Integer.parseInt(mm);
						   String apmpm =  ((hourOfDay > 12) ? hourOfDay % 12 : hourOfDay) + ":" + (minute < 10 ? ("0" + minute) : minute) + " " + ((hourOfDay >= 12) ? "PM" : "AM");
						   dischargeDate= dtemp[0]+" "+apmpm;
						   accountsForm.setDischargeDate(dischargeDate);
					}else{
						accountsForm.setDischargeDate("");
					}
				}else{
					accountsForm.setDischargeDate("");
				}
				
			}else{
				accountsForm.setAdmissionDate("");
				accountsForm.setDischargeDate("");
				if(itype.equals("1")){
					NotAvailableSlotDAO notAvailableSlotDAO= new JDBCNotAvailableSlotDAO(connection);
					String opdidagnosis=notAvailableSlotDAO.getAllDiagnosisofOpd(""+opdid);
					if(opdidagnosis!=null){
						if(!opdidagnosis.equals("")){
							accountsForm.setFinalDiagnosis(""+opdidagnosis);
							session.setAttribute("finaldiagnosis", accountsForm.getFinalDiagnosis());		
						}
					}
					
				}
			}
			
			accountsForm.setIpdid(ipdid);
			
			String datetime[]= DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone()).split(" ");
			accountsForm.setDateTime(DateTimeUtils.getCommencingDate1(datetime[0])+" "+datetime[1]);
			//set prepared by
			//set prepared by
			int preparedbyid = accountsDAO.getInvoicePreparedBy(invoiceid);
			if(preparedbyid!=0){
				
				 userProfile = userProfileDAO.getUserprofileDetails(preparedbyid);
				 fullname = userProfile.getInitial() + " " + userProfile.getFirstname() + " " + userProfile.getLastname();
				accountsForm.setPreparedby(fullname);
			}else{
				accountsForm.setPreparedby("");
			}
			if(userProfile.getUserid()==null){
				accountsForm.setUserid("");
			}else{
				accountsForm.setUserid(userProfile.getUserid());
			}
			int isparkingcharge = accountsDAO.checkIsParking(invoiceid);
			accountsForm.setIsparkingcharge(""+isparkingcharge);
			if(refdr==null){
				refdr="0";
			}
			if(refdr.equals("")){
				refdr="0";
			}
			
			refdr= userProfileDAO.getReferalDrName(refdr);
			if(refdr==null){
				refdr="";
			}
			
			accountsForm.setRefereddr(refdr);
			accountsForm.setInvcetype(itype);
			
			//  showing third party fields in invoice print 10/12/2018
			
			Client client2 = accountsDAO.getTPClientData(clientid);
			String companyname=accountsDAO.gettypenamebyid(client2.getThirdPartyType());
			String tpname=accountsDAO.gettpnamebyid(client2.getThirdPartyType());
			if(client2.getWhopay().equals("Third Party")){
			accountsForm.setPayedbytp(client2.getWhopay());
			if(client2.getCompname()==null){
				client2.setCompname("");
			}if(client2.getNeisno()==null){
				client2.setNeisno("");
			}if(client2.getDesignationbytp()==null){
				client2.setDesignationbytp("");
			}if(client2.getRelationvbytpe()==null){
				client2.setRelationvbytpe("");
			}if(client2.getUnitstation()==null){
				client2.setUnitstation("");
			}if(client2.getClaimbytp()==null){
				client2.setClaimbytp("");
			}if(client2.getColliery()==null){
				client2.setColliery("");
			}if(client2.getAreabytp()==null){
				client2.setAreabytp("");
			}if(client2.getPolicyholder()==null){
				client2.setPolicyholder("");
			}
			
			if(companyname.equals("CGHS")){
				
					accountsForm.setStatusfortp(true);
					accountsForm.setCompanyname("CGHS");
				}
				else if(companyname.equals("WCL")){
					
						accountsForm.setStatusfortp(true);
						accountsForm.setCompanyname("WCL");
					}else if(tpname.equals("INSURANCE COMPANY")){
						accountsForm.setStatusfortp(true);
						accountsForm.setCompanyname(companyname);
					}
					else{
						accountsForm.setStatusfortp(false);
					}
						
			accountsForm.setEmployeenamebytp(client2.getCompname());
			accountsForm.setNeiscardno(client2.getNeisno());
			accountsForm.setDesignation(client2.getDesignationbytp());
			accountsForm.setRelationofuser(client2.getRelationvbytpe());
			accountsForm.setUnit_station(client2.getUnitstation());
			accountsForm.setClaimid(client2.getClaimbytp());
			accountsForm.setColliery(client2.getColliery());
			accountsForm.setAreatp(client2.getAreabytp());
			accountsForm.setPolicyholder(client2.getPolicyholder());
			}
			if(!client.getReference().equals("")){
				String refrencedrname=accountsDAO.getrefdrname(client.getReference());
				accountsForm.setRefereddr(refrencedrname);
			}else{
				accountsForm.setRefereddr("");
			}
			ClientDAO clientDAO=new JDBCClientDAO(connection);
			if(loginInfo.isBalgopal()){
				Client client3=clientDAO.getPatientBMIData(clientid,opdid);
				accountsForm.setHeight(client3.getHeight());
				accountsForm.setWeight(client3.getWeight());
				accountsForm.setBmi(client3.getBmi());
				accountsForm.setHeadcir(client3.getHead_cir());
				accountsForm.setTempr(client3.getTemprature());
				}
			String notes1=clientDAO.getopdnotes(String.valueOf(opdid));
			session.setAttribute("notes", notes1);
			accountsForm.setKuanlInvSeq(accountsDAO.getSeqNoForKunal(accountsForm.getDate(), ""+invoiceid, itypenew));
			boolean statusrequestdiscamt = accountsDAO.getRequestedDiscountStatus(invoiceid);
			double discountamt = accountsDAO.getRequestedDiscountAmount(invoiceid);
			accountsForm.setStatusrequestdiscamt(statusrequestdiscamt);
			accountsForm.setDiscountamt(discountamt);
			
			String paymentNote = accountsDAO.getInvoicePaymentNote(invoiceid);
			accountsForm.setPaymentNote(paymentNote);
			
			accountsForm.setPhysical_paymentid(accountsDAO.getMultiplePaymentIdAgainstInvoice(""+invoiceid));
		
			accountsForm.setPaymentLMHSequence(accountsDAO.getMultiplePaymentSequenceLMH(invoiceid));
			Accounts accounts = accountsDAO.getInvoiceDeleteInfo(invoiceid);
			accountsForm.setDayseqno(accounts.getDayseqno());
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			
			connection.close();
		}
		
		accountsForm.setActionType("show");
		if(loginInfo.isPcsAdmin()){
			return "pcsbill";
		}
		if(action.equals("show")){
			return "showpreview";
		}else if(action.equals("print")){
			return "printpreview"; 
		}
		
		else{
			return "showpreview";
		}
	}
	

	
	
	public void createPdf(double discount,double amountTotal) throws Exception{
		int invoiceid = (Integer)session.getAttribute("chargesInvoiceid");
		String clientid = (String)session.getAttribute("clientId");
		String temppayAmount = (String)session.getAttribute("payAmount");
		
		double payAmount=Double.parseDouble(temppayAmount);
		String payby = (String)session.getAttribute("payby");
		
	//	String discount = request.getParameter("discount");
		DateTimeUtils dateTimeUtils = new DateTimeUtils();
	
		
		Vector<Accounts>previewChargesList = new Vector<Accounts>();
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
			
			if(payby.equalsIgnoreCase("Third Party") || payby.equals("1")){
				int tpid = accountsDAO.getTPId(invoiceid);
				ThirdParty thirdParty = new ThirdParty();
				thirdParty = accountsDAO.getTpDetails(tpid);
				
				accountsForm.setPayeename(thirdParty.getCompanyName());
				String address1 = thirdParty.getAddress()+","+thirdParty.getSecondLineAddress()+ ","+thirdParty.getTown() +" " +thirdParty.getPostcode();
				
			}
			else{
				//accountsForm.setPayeename(client.getTitle() + " " + client.getFirstName() + " " + client.getLastName());
				accountsForm.setPayeename("Self");
				if(client.getSecondLineaddress()!=null){
					accountsForm.setPayeeadress(client.getAddress()+","+client.getSecondLineaddress());
				}else{
					accountsForm.setPayeeadress(client.getAddress());
				}
				
				
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
					str.append("<div style='font-size: 11px; font-weight: normal; padding-left: 20px;'><label>"+client.getTitle() + " " + client.getFirstName() + " " + client.getLastName()+"</label> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp <font style='font-size: 20px; font-weight: bold;padding-left: 300px; '>Payment</font> <br>"+client.getAddress()+ ","+client.getSecondLineaddress()+" <br> "+client.getTown()+" <br> "+client.getPostCode()+" ");
				}else{
					str.append("<div style='font-size: 11px; font-weight: normal; padding-left: 20px;'><label>"+client.getTitle() + " " + client.getFirstName() + " " + client.getLastName()+"</label> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp <font style='font-size: 20px; font-weight: bold;padding-left: 300px; '>Invoice</font> <br>"+client.getAddress()+ ","+client.getSecondLineaddress()+" <br> "+client.getTown()+" <br> "+client.getPostCode()+" ");
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
				str.append("<td>"+accounts.getChargeTotal()+"</td>");
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
			
			double creditAmount = 0;
			if(session.getAttribute("creditAmount")!=null){
				creditAmount = (Double)session.getAttribute("creditAmount");
			}
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

			//htmlToPdfFile(str.toString(), filePath, filename);
			//htmlToPdfFile(str.toString(), filePath1, filename1);
			
			session.setAttribute("pdfFileName", filePath+"/"+filename);
			
			accountsForm.setFilename(filename1);
//			System.out.println("pdf done");
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			
			connection.close();
		}
		
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
			String id = request.getParameter("id");
			String to = request.getParameter("to");
			String cc = request.getParameter("cc");
			String subject = request.getParameter("subject");
			String notes = request.getParameter("notes");
			String filename1="";
			String filename = DateTimeUtils.isNull((String)session.getAttribute("pdfFileName"));
			if((filename).contains("/")){
				String[] temp1 = filename.split("/");
				filename1 = temp1[1];	
			}
			
			int invoiceid = (Integer)session.getAttribute("chargesInvoiceid");
			DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
			Date date = new Date();
			String d1 = dateFormat.format(date);
			String[] temp = d1.split("\\s+");
			String date1 = temp[0];
			String time = temp[1];
			String clientid = request.getParameter("clientid");
			String type = request.getParameter("type");
			
			int result = accountsDAO.saveEmailLogDetails(to, cc, subject, notes, filename1,invoiceid,DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone()),time,type);
			
			//set deliver status
			int update = accountsDAO.updateDeliverStatus(invoiceid,"2");
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
		
	return null;
	}
	
	public String close() throws Exception{
		if(!verifyLogin(request)){
			return "login";
		}
		Connection connection = null;
		
		try{
			connection = Connection_provider.getconnection();
			AccountsDAO accountsDAO = new JDBCAccountsDAO(connection);
			
			String clientid = (String)session.getAttribute("clientId");
			int invoiceid = (Integer)session.getAttribute("chargesInvoiceid");
			String payby = (String)session.getAttribute("payby");
			
			String clientName = accountsDAO.getClientName(clientid);
			accountsForm.setClient(clientName);
		
			
			
			
			
			
			String fromDate = "";
			String toDate = "";
			
			ArrayList<Accounts>chargeProcessingList = new ArrayList<Accounts>();
			ChargesAccountProcessingDAO chargesAccountProcessingDAO = new JDBCChargeAccountProcesDAO(connection);
			int totalCount = chargesAccountProcessingDAO.getTotalChargesAccountProcessingCount(clientid,payby,fromDate,toDate);
			pagination.setPreperties(totalCount);
			
			chargeProcessingList = chargesAccountProcessingDAO.getChargesAccountProcessingList(clientid,payby,fromDate,toDate,pagination,accountsForm.getActionType(),accountsForm.getSelectedInvoiceid());
			
			pagination.setPage_records(chargeProcessingList.size());
			accountsForm.setTotalRecords(chargeProcessingList.size());
			accountsForm.setPagerecords(Integer.toString(pagination.getPage_records()));
			accountsForm.setChargeProcessingList(chargeProcessingList);
			
			session.setAttribute("fromDate", "");
			session.setAttribute("toDate", "");
			session.setAttribute("pagination", pagination);
			
			accountsForm.setHdnSelectedID(Integer.toString(invoiceid));
		}catch(Exception e ){
			e.printStackTrace();
		}finally{
			
			connection.close();
		}
		
		return "closecharges";
	}
	
	public AccountsForm getModel() {
		
		return accountsForm;
	}





	public void setFormData() throws Exception{
		//redirect to execute
		Connection connection = null;
		DateTimeUtils dateTimeUtils = new DateTimeUtils();
		try{
			
			connection = Connection_provider.getconnection();
			AccountsDAO accountsDAO = new JDBCAccountsDAO(connection);
		
		String payby = (String) session.getAttribute("payby");
		String clientId = (String) session.getAttribute("clientId");
		String client = (String) session.getAttribute("client");
		String transactionType = (String) session.getAttribute("transactionType");
		String location = (String) session.getAttribute("location");
		String thirdParty = (String) session.getAttribute("thirdParty");
		String raiseChargeType = (String) session.getAttribute("raiseChargeType"); 
		pagination = (Pagination) session.getAttribute("pagination");
		String defaultVal = "All";
		if(transactionType.equals(defaultVal)){
			transactionType = "";
		}
		if(location.equals(defaultVal)){
			location = "";
		}
		if(thirdParty.equals(defaultVal)){
			thirdParty = "%%";
		}
		
		String fromDate=accountsForm.getFromDate();
		String toDate=accountsForm.getToDate();
	
		if(fromDate!=null){
			
			if(fromDate.equals("")){
				fromDate=null;
			}
		}
		if(toDate!=null){
			if(toDate.equals("")){
				toDate=null;
			}
		}
		
		
		if(fromDate==null){
			
		    Calendar calendar=Calendar.getInstance();
		    calendar.add(Calendar.DATE, -7);
		    SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd");
		    Date date=calendar.getTime();
		    fromDate=format.format(date);
		}
		if(toDate==null){
			Calendar calendar=Calendar.getInstance();
			SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd");
		    Date date=calendar.getTime();
		    toDate=format.format(date);
		}
		
		if(!clientId.equals("")){
			
			String addOnTableName="";
			/*boolean dateWithinRange1 = DateTimeUtils.validateDateBetweenRange(fromDate);
			boolean dateWithinRange2 = DateTimeUtils.validateDateBetweenRange(toDate);
			if(dateWithinRange1 && dateWithinRange2){
				addOnTableName="_view";
			}*/
			
			int totalCount = accountsDAO.getTotalAccountCount(clientId,payby,transactionType,location,thirdParty,raiseChargeType,fromDate,toDate,"",addOnTableName);
			pagination.setPreperties(totalCount);
			ArrayList<Accounts>accountList = new ArrayList<Accounts>();
			if(transactionType.equals(Constants.PENDING_PEYMENT)){
				accountList = accountsDAO.getAccountPendingList(clientId,payby,pagination,transactionType,location,thirdParty);
				totalCount = accountList.size();
			}
			else if(transactionType.equals(Constants.PAID)){
			accountList = accountsDAO.getAccountPaidList(clientId,payby,pagination,transactionType,location,thirdParty);
			totalCount = accountList.size();
			}
			else{
				accountList = accountsDAO.getAccountList(clientId,payby,pagination,transactionType,location,thirdParty,raiseChargeType,fromDate,toDate,"",addOnTableName);
				
			}
			pagination.setPage_records(accountList.size());
			accountsForm.setTotalRecords(totalCount);
			accountsForm.setPagerecords(Integer.toString(pagination.getPage_records()));
			
			accountsForm.setAccountList(accountList);
			accountsForm.setClientId(clientId);
			accountsForm.setPayby(payby);
			accountsForm.setClient(client);
			if(transactionType.equals("")){
				transactionType = "All";
			}
			if(location.equals("")){
				location = "All";
			}
			if(thirdParty.equals("")){
				thirdParty = "All";
			}
			accountsForm.setTransactionType(transactionType);
			accountsForm.setLocation(location);
			accountsForm.setThirdParty(thirdParty);
			CompleteAppointment completeAppointment = new CompleteAppointment();
			CompleteAptmDAO completeAptmDAO = new JDBCCompleteAptmDAO(connection);
			completeAppointment = completeAptmDAO.getInsuranceCompanyName(clientId);
			accountsForm.setInsuranceCompany(completeAppointment.getInsuranceCompanyName() +" " + completeAppointment.getInsuranceCompanyOwnerName() + " " + completeAppointment.getInsuranceCompanyAddress());
			
			
			for(Accounts accounts :accountList ){
				accountsForm.setBalanceTotal(accounts.getBalanceTotal());
				accountsForm.setDebitTotal(accounts.getDebitTotal());
				accountsForm.setCreditTotal(accounts.getCreditTotal());
				
				//Decimal Account
				accountsForm.setBalanceTotalx(accounts.getBalanceTotalx());
				accountsForm.setDebitTotalx(accounts.getDebitTotalx());
				accountsForm.setCreditTotalx(accounts.getCreditTotalx());
				
				ArrayList<Accounts> clientDetailsList = accountsDAO.getClientDetails(clientId);
				for(Accounts a1:clientDetailsList){
					accountsForm.setInitial(a1.getInitial());
					accountsForm.setFirstname(a1.getFirstname());
					accountsForm.setLastname(a1.getLastname());
					accountsForm.setAddress(a1.getAddress());
					accountsForm.setCity(a1.getCity());
					accountsForm.setClientId(clientId);
				}
				
				
				}
		}
		
		
		//select all code
				boolean selectAll = accountsForm.isHdnSelectAll();
				ArrayList<Accounts>allChargeList = new ArrayList<Accounts>();
				
				allChargeList = accountsDAO.getTempChagesInvoiceList(loginInfo.getLoginsessionid());
				
				session.setAttribute("allChargeList", allChargeList);
			
		
		}catch(Exception e){
			
		}finally{
			
			connection.close();
		}
	}
	
public String move(){
	
	return null;
}
	
public String temp() throws Exception{
		
		Connection connection  = null;
		
		try{
			
			connection  = Connection_provider.getconnection();
			AccountsDAO accountsDAO = new JDBCAccountsDAO(connection);
			
			String invoiceid = request.getParameter("invoiceid");
			boolean chk = Boolean.parseBoolean(request.getParameter("chk"));
			
			if(chk==true){
				int save = accountsDAO.saveTempChargeAccounts(Integer.parseInt(invoiceid),loginInfo.getLoginsessionid());
			}else{
				int delete = accountsDAO.delateTempCharges(invoiceid,loginInfo.getLoginsessionid());
			}
			
			
//			System.out.println(chk);
			
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			
			connection.close();
		}
		
		
		return null;
		
	}

	public void prepare() throws Exception {
		Connection connection  = null;
		
		try{
			
			connection  = Connection_provider.getconnection();
			AccountsDAO accountsDAO = new JDBCAccountsDAO(connection);
			MasterDAO masterDAO = new JDBCMasterDAO(connection);
			
			ArrayList<Accounts>locationList = accountsDAO.getLocationList(loginInfo.getClinicid());
			ArrayList<Accounts>thirdPartyList = accountsDAO.getThirdPartyList(loginInfo.getId());
			accountsForm.setLocationList(locationList);
			accountsForm.setThirdPartyList(thirdPartyList);			
			
			EmailTemplateDAO emailTemplateDAO = new JDBCEmailTemplateDAO(connection);
			ArrayList<EmailTemplate> templateNameList = emailTemplateDAO.getEmailTemplateNameList();
			accountsForm.setTemplateNameList(templateNameList);	
			
			ArrayList<Master>invoiceTypeList = accountsDAO.getInvoiceTypeList();
			accountsForm.setInvoiceTypeLis(invoiceTypeList);
			
			 ArrayList<Master>bankNameList = masterDAO.getBankNameList();
			 accountsForm.setBankNameList(bankNameList);
				
		}
		catch(Exception e){
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


//  29 Jan 2018

public String refundprint() throws Exception{
	if(!verifyLogin(request)){
		return "login";
	}
	String invoiceid = request.getParameter("id");
	
	Connection connection = null;
	Vector<Accounts>previewChargesList = new Vector<Accounts>();
	Vector<Accounts>assesmentList = new Vector<Accounts>();
	try{
		connection = Connection_provider.getconnection();
		AccountsDAO accountsDAO = new JDBCAccountsDAO(connection);
		
		Accounts accounts = accountsDAO.getAdvanceInvoiceDetails(invoiceid);
		
		Clinic clinic = new Clinic();
		ClinicDAO clinicDAO  = new JDBCClinicDAO(connection);
		clinic = clinicDAO.getCliniclistDetails(loginInfo.getClinicid());
		
		ArrayList<Clinic> locationAdressList = accountsDAO.getLocationAddress(loginInfo.getClinicid());
		accountsForm.setPhysical_paymentid(accountsDAO.getPhysicalpaymentIdAdvRef(invoiceid));
		//String address = accountsDAO.getLocationAddress(locationid,loginInfo.getId());
		if(loginInfo.isBalgopal()){
			AdditionalDAO additionalDAO= new JDBCAdditionalDAO(connection);
			accountsForm.setPhysical_paymentid(""+additionalDAO.getBalgopalSeqNum(invoiceid));
			
			//new finac year se
			String finseq=additionalDAO.getBalgopalFinacialSeqNum(""+invoiceid);
			accountsForm.setPhysical_paymentid(finseq);
		}
		
		accountsForm.setClinicName(clinic.getClinicName());
		accountsForm.setClinicOwner(clinic.getClinicOwner());
		accountsForm.setOwner_qualification(clinic.getOwner_qualification());
		//accountsForm.setClinicaddress(address);
		accountsForm.setLandLine(clinic.getLandLine());
		accountsForm.setWebsiteUrl(clinic.getWebsiteUrl());
		accountsForm.setClinicemail(clinic.getEmail());
		accountsForm.setLocationAdressList(locationAdressList);
		accountsForm.setClinicLogo(clinic.getUserImageFileName());
		
		//setting master chartype data
		/*ArrayList<Master>masterAssessmentList = accountsDAO.getRefundMasterAssessmentList(Integer.parseInt(invoiceid));
		accountsForm.setMasterAssessmentList(masterAssessmentList);*/
		
		assesmentList = accountsDAO.getRefundPreviewChargesList(Integer.parseInt(invoiceid));
		
		accountsForm.setAssesmentList(assesmentList);
		String advrefsrno= accountsDAO.getAdvanceRefrundSerialNo(invoiceid);
		accountsForm.setAdvrefsrno(advrefsrno);
		double totalAmount = 0;
		String sectionroom = "";
		int size=0;
		size=assesmentList.size();
		if(size>0){
			int advref=assesmentList.get(size-1).getAdvref();
			accountsForm.setAdvref(advref);
		}
		for(Accounts totalAcc : assesmentList){
			double charge = Double.parseDouble(totalAcc.getCharges()) * totalAcc.getQuantity();
			totalAmount = totalAmount + charge;
			
			// sectionroom = totalAcc.getSectionroom();
		}
		
		
	
		
		String clientid = Integer.toString(accounts.getClientid());
		Client client = accountsDAO.getClientData(clientid);
		accountsForm.setClient(client.getTitle() + " " + client.getFirstName() + " "+client.getMiddlename()+" " + client.getLastName());
/*			if(client.getSecondLineaddress()!=null){
			accountsForm.setAddress(client.getAddress() + "," +client.getSecondLineaddress() + "," + client.getTown() + "," + client.getPostCode());
		}else{
			accountsForm.setAddress(client.getAddress() + "," + client.getTown() + "," + client.getPostCode());
		}*/
		
		accountsForm.setAddress(client.getAddress());
		accountsForm.setClienttown(client.getTown());
		accountsForm.setClientpostcode(client.getPostCode());
		
		/*
		 * String age = DateTimeUtils.getAge(client.getDob());
		 * accountsForm.setAgegender(age + "/" + client.getGender());
		 */
		
		accountsForm.setDob(client.getDob());
		accountsForm.setAbrivationid(client.getAbrivationid());
		accountsForm.setClientId(clientid);
		accountsForm.setEmail(client.getEmail());
		accountsForm.setMobno(client.getMobNo());
		accountsForm.setPolicyNo(client.getPolicyNo());
		accountsForm.setPayby(accounts.getPayby());
		accountsForm.setInvoiceid(accounts.getId());
		accountsForm.setPaymode(accounts.getPaymentmode());
		accountsForm.setPreparedby(accounts.getUserid());
		accountsForm.setDate(accounts.getDate());
		//accountsForm.setDiscount(DateTimeUtils.changeFormat(discount));
		String age="";
		if(loginInfo.isBalgopal()){
			 //for balgopal age as per payment date	
			 if(accounts.getDate()!=null){
				 if(!accounts.getDate().equals("")){
					 String c=accounts.getDate();
					 String h[]= c.split(" ");
					 c= DateTimeUtils.getCommencingDate1(h[0]);
					 age=DateTimeUtils.getAge1onPaymentDate(client.getDob(), h[0]);
					 accountsForm.setAgegender(age + "/" + client.getGender());
				 }
			 }
			}else {
				age = DateTimeUtils.getAgeyear(client.getDob());
				accountsForm.setAgegender(age + "/" + client.getGender());
			}
		
		
		//accountsForm.setAssesmentList(assesmentList);
		accountsForm.setTotalAmount(totalAmount);
		
		//Decimal Account
		accountsForm.setTotalAmountx(DateTimeUtils.changeFormat(totalAmount));
		NumberToWord obj = new NumberToWord();
		accountsForm.setTotalinword(obj.convert((int) totalAmount));
		
		//accountsForm.setInvoiceid(invoiceid);
	
		accountsForm.setPayeename(client.getTitle() + " " + client.getFirstName() + " "+client.getMiddlename()+" " + client.getLastName());
		if(client.getSecondLineaddress()!=null){
			accountsForm.setPayeeadress(client.getAddress() +","+client.getSecondLineaddress());
		}else{
			accountsForm.setPayeeadress(client.getAddress());
		}
		
		accountsForm.setPayeeTown(client.getTown());
		accountsForm.setPayeePostcode(client.getPostCode());
		accountsForm.setPayeeEmail(client.getEmail());
		accountsForm.setPayeeConatctNo(client.getMobNo());
		
		accountsForm.setSubmitInvoiceNotes(accounts.getNotes());
	}catch (Exception e) {
		e.printStackTrace();
	}finally{
		
		connection.close();
	}
	
	return "advrefundprint";
}

public String printinvoicepayment() throws Exception{
	if(!verifyLogin(request)){
		return "login";
	}
	String invoiceid = request.getParameter("invoiceid");
	String pid = request.getParameter("pid");
	Connection connection = null;
	try{
		connection = Connection_provider.getconnection();
		AccountsDAO accountsDAO = new JDBCAccountsDAO(connection);
		IpdDAO ipdDAO=new JDBCIpdDAO(connection);
		Accounts accounts = accountsDAO.getInvoicePaymentData(invoiceid,pid);
		int ipdopdseqno=accountsDAO.getIpdOpdSeqNo(Integer.parseInt(invoiceid));
		accountsForm.setIpdopdseqno(String.valueOf(ipdopdseqno));
		int ipdid=accountsDAO.getIpdIdFromInvoice(Integer.parseInt(invoiceid));
		Clinic clinic = new Clinic();
		ClinicDAO clinicDAO  = new JDBCClinicDAO(connection);
		clinic = clinicDAO.getCliniclistDetails(loginInfo.getClinicid());
		
		ArrayList<Clinic> locationAdressList = accountsDAO.getLocationAddress(loginInfo.getClinicid());
		//String address = accountsDAO.getLocationAddress(locationid,loginInfo.getId());
		
		accountsForm.setPhysical_paymentid(accountsDAO.getPhysicalpaymentId(pid));
		accountsForm.setClinicName(clinic.getClinicName());
		accountsForm.setClinicOwner(clinic.getClinicOwner());
		accountsForm.setOwner_qualification(clinic.getOwner_qualification());
		//accountsForm.setClinicaddress(address);
		accountsForm.setLandLine(clinic.getLandLine());
		accountsForm.setWebsiteUrl(clinic.getWebsiteUrl());
		accountsForm.setClinicemail(clinic.getEmail());
		accountsForm.setLocationAdressList(locationAdressList);
		accountsForm.setClinicLogo(clinic.getUserImageFileName());
		
		
	
		
		String clientid = Integer.toString(accounts.getClientid());
		Client client = accountsDAO.getClientData(clientid);
		accountsForm.setClient(client.getTitle() + " " + client.getFirstName() + " "+client.getMiddlename()+" " + client.getLastName());
/*			if(client.getSecondLineaddress()!=null){
			accountsForm.setAddress(client.getAddress() + "," +client.getSecondLineaddress() + "," + client.getTown() + "," + client.getPostCode());
		}else{
			accountsForm.setAddress(client.getAddress() + "," + client.getTown() + "," + client.getPostCode());
		}*/
		
		accountsForm.setAddress(client.getAddress());
		accountsForm.setClienttown(client.getTown());
		accountsForm.setClientpostcode(client.getPostCode());
		
		//String age = DateTimeUtils.getAge(client.getDob());
		//String age = DateTimeUtils.getAgeyear(client.getDob());
		String age="";
		if(loginInfo.isBalgopal()){
		 //for balgopal age as per payment date	
		 if(accounts.getDate()!=null){
			 if(!accounts.getDate().equals("")){
				 String c=accounts.getDate();
				 String h[]= c.split(" ");
				 c= DateTimeUtils.getCommencingDate1(h[0]);
				 age=DateTimeUtils.getAge1onPaymentDate(client.getDob(), h[0]);
				 accountsForm.setAgegender(age + "/" + client.getGender());
			 }
		 }
		}else {
			age = DateTimeUtils.getAgeyear(client.getDob());
			accountsForm.setAgegender(age + "/" + client.getGender());
		}
		//accountsForm.setAgegender(age + "/" + client.getGender());
		
		accountsForm.setDob(client.getDob());
		accountsForm.setAbrivationid(client.getAbrivationid());
		accountsForm.setClientId(clientid);
		accountsForm.setEmail(client.getEmail());
		accountsForm.setMobno(client.getMobNo());
		accountsForm.setPolicyNo(client.getPolicyNo());
		accountsForm.setInvoiceid(Integer.parseInt(invoiceid));
		accountsForm.setPaymode(accounts.getPaymentmode());
		accountsForm.setPreparedby(accounts.getUserid());
		accountsForm.setDate(accounts.getDate());
		//accountsForm.setDiscount(DateTimeUtils.changeFormat(discount));
	
		
		
		//accountsForm.setAssesmentList(assesmentList);
		accountsForm.setTotalAmount(accounts.getAmount());
		
		//Decimal Account
		accountsForm.setTotalAmountx(DateTimeUtils.changeFormat(accounts.getAmount()));
		NumberToWord obj = new NumberToWord();
		accountsForm.setTotalinword(obj.convert((int) accounts.getAmount()));
		
		//accountsForm.setInvoiceid(invoiceid);
	
		accountsForm.setPayeename(client.getTitle() + " " + client.getFirstName() + " "+client.getMiddlename()+" " + client.getLastName());
		if(client.getSecondLineaddress()!=null){
			accountsForm.setPayeeadress(client.getAddress() +","+client.getSecondLineaddress());
		}else{
			accountsForm.setPayeeadress(client.getAddress());
		}
		
		accountsForm.setPayeeTown(client.getTown());
		accountsForm.setPayeePostcode(client.getPostCode());
		accountsForm.setPayeeEmail(client.getEmail());
		accountsForm.setPayeeConatctNo(client.getMobNo());
		accountsForm.setId(""+accounts.getId());
		accountsForm.setSubmitInvoiceNotes(accounts.getNotes());
		accountsForm.setFathername(client.getFathername());
		accountsForm.setMothername(client.getMothername());
		String ipdabrivation=ipdDAO.getIpdAbrivationIdsByClientid(Integer.parseInt(clientid));
		accountsForm.setIpdabbrivationid(ipdabrivation);
		if(loginInfo.getIpd_abbr_access()==1){
			 String newipdabbr=ipdDAO.getIpdAbrivationIds(ipdid);
			   accountsForm.setNewipdabbr(newipdabbr);
			   accountsForm.setIpdseqno(String.valueOf(ipdid));
		}else{
		accountsForm.setIpdseqno(ipdDAO.getipdseqno( String.valueOf(ipdid)));
		 accountsForm.setNewipdabbr(String.valueOf(ipdid));
		}
	}catch (Exception e) {
		e.printStackTrace();
	}finally{
		connection.close();
	}
	
	return "printinvoicepayment";
}


public String updateAdmissionid() throws Exception {
	String admissionid = request.getParameter("admissionid");
	String ipdseqno = request.getParameter("ipdseqno");
	Connection connection=null;
	try {
		connection = Connection_provider.getconnection();
		IpdDAO ipdDAO=new JDBCIpdDAO(connection);
		int update =ipdDAO.updateAdmissionid(admissionid,ipdseqno);
		response.setContentType("text/html");
		response.setHeader("Cache-Control", "no-cache");
		response.getWriter().write("");
	} catch (Exception e) {
		e.printStackTrace();
	}
	return null;
}

}