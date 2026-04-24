package com.apm.Accounts.web.action;

import java.io.File;
import java.io.FileOutputStream;
import java.sql.Connection;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map;
import java.util.TimeZone;
import java.util.Vector;
import java.util.stream.Collectors;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.allcolor.yahp.converter.CYaHPConverter;
import org.allcolor.yahp.converter.IHtmlToPdfTransformer;
import org.apache.struts2.ServletActionContext;
import org.aspectj.weaver.tools.GeneratedClassHandler;

import com.apm.Accounts.eu.bi.AccountsDAO;
import com.apm.Accounts.eu.bi.AdditionalDAO;
import com.apm.Accounts.eu.bi.StatementDAO;
import com.apm.Accounts.eu.blogic.jdbc.JDBCAccountsDAO;
import com.apm.Accounts.eu.blogic.jdbc.JDBCAdditionalDAO;
import com.apm.Accounts.eu.blogic.jdbc.JDBCStatementDAO;
import com.apm.Accounts.eu.entity.Accounts;
import com.apm.Accounts.eu.entity.Invoice;
import com.apm.Accounts.web.form.AccountsForm;
import com.apm.Appointment.eu.bi.AppointmentDAO;
import com.apm.Appointment.eu.bi.AppointmentTypeDAO;
import com.apm.Appointment.eu.blogic.jdbc.JDBCAppointmentDAO;
import com.apm.Appointment.eu.blogic.jdbc.JDBCAppointmentTypeDAO;
import com.apm.Appointment.eu.entity.Appointment;
import com.apm.Appointment.eu.entity.AppointmentType;
import com.apm.AssesmentForms.eu.bi.AssessmentFormDAO;
import com.apm.AssesmentForms.eu.blogic.jdbc.JDBCAssessmentFormDAO;
import com.apm.DiaryManagement.eu.bi.ClientDAO;
import com.apm.DiaryManagement.eu.bi.CompleteAptmDAO;
import com.apm.DiaryManagement.eu.bi.FinderDAO;
import com.apm.DiaryManagement.eu.bi.IpdFakeConsultDAO;
import com.apm.DiaryManagement.eu.bi.NotAvailableSlotDAO;
import com.apm.DiaryManagement.eu.blogic.jdbc.JDBCClientDAO;
import com.apm.DiaryManagement.eu.blogic.jdbc.JDBCCompleteAptmDAO;
import com.apm.DiaryManagement.eu.blogic.jdbc.JDBCFinderDAO;
import com.apm.DiaryManagement.eu.blogic.jdbc.JDBCIpdFakeConsultDAO;
import com.apm.DiaryManagement.eu.blogic.jdbc.JDBCNotAvailableSlotDAO;
import com.apm.DiaryManagement.eu.entity.Breadcrumbs;
import com.apm.DiaryManagement.eu.entity.Client;
import com.apm.DiaryManagement.eu.entity.CompleteAppointment;
import com.apm.DiaryManagement.eu.entity.IpdFakeConsult;
import com.apm.DiaryManagement.eu.entity.NotAvailableSlot;
import com.apm.DiaryManagement.web.action.SendSms;
import com.apm.Emr.eu.bi.InvestigationDAO;
import com.apm.Emr.eu.blogic.jdbc.JDBCInvestigationDAO;
import com.apm.Emr.eu.entity.Investigation;
import com.apm.Inventory.eu.bi.InventoryProductDAO;
import com.apm.Inventory.eu.blogic.jdbc.JDBCInventoryProductDAO;
import com.apm.Inventory.eu.entity.Product;
import com.apm.Ipd.eu.bi.BedDao;
import com.apm.Ipd.eu.bi.IpdDAO;
import com.apm.Ipd.eu.blogic.jdbc.JDBCBedDao;
import com.apm.Ipd.eu.blogic.jdbc.JDBCIpdDAO;
import com.apm.Ipd.eu.entity.Bed;
import com.apm.Ipd.eu.entity.Discharge;
import com.apm.Ipd.eu.entity.Ipd;
import com.apm.Log.eu.entity.EmailLetterLog;
import com.apm.Master.eu.bi.DischargeStatusDAO;
import com.apm.Master.eu.bi.MasterDAO;
import com.apm.Master.eu.blogic.jdbc.JDBCDischargeStatus;
import com.apm.Master.eu.blogic.jdbc.JDBCMasterDAO;
import com.apm.Master.eu.entity.Master;
import com.apm.Master.web.action.SendsmsAction;
import com.apm.Payroll.eu.entity.Salary;
import com.apm.Registration.eu.bi.ClinicDAO;
import com.apm.Registration.eu.bi.UserProfileDAO;
import com.apm.Registration.eu.blogic.jdbc.JDBCClinicDAO;
import com.apm.Registration.eu.blogic.jdbc.JDBCUserProfileDAO;
import com.apm.Registration.eu.entity.Clinic;
import com.apm.Registration.eu.entity.UserProfile;
import com.apm.ThirdParties.eu.bi.ThirdPartyDAO;
import com.apm.ThirdParties.eu.blogic.jdbc.JDBCThirdPartyDAO;
import com.apm.ThirdParties.eu.entity.ThirdParty;
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

public class StatementAction extends BaseAction implements Preparable,  ModelDriven<AccountsForm>{
	
	HttpServletRequest request = (HttpServletRequest) ActionContext.getContext().get(ServletActionContext.HTTP_REQUEST);
	HttpServletResponse response = (HttpServletResponse)ActionContext.getContext().get(ServletActionContext.HTTP_RESPONSE);
	HttpSession session = request.getSession(true);
	LoginInfo loginInfo = LoginHelper.getLoginInfo(request);
	AccountsForm accountsForm = new AccountsForm();
	private Pagination pagination = new Pagination(10, 1);
	public Pagination getPagination() {
		return pagination;
	}


	public void setPagination(Pagination pagination) {
		this.pagination = pagination;
	}
	
	public String execute() throws Exception {
		if(!verifyLogin(request)){
			return "login";
		}
		Connection connection = null;
		try{
			double inddiscount=0;
			connection = Connection_provider.getconnection();
			AccountsDAO accountsDAO = new JDBCAccountsDAO(connection);
			NotAvailableSlotDAO notAvailableSlotDAO = new JDBCNotAvailableSlotDAO(connection);
			StatementDAO statementDAO = new JDBCStatementDAO(connection);
			CompleteAptmDAO completeAptmDAO= new JDBCCompleteAptmDAO(connection);
			ArrayList<Accounts>accountList = new ArrayList<Accounts>();	
			ClientDAO clientDAO = new JDBCClientDAO(connection);
			DateTimeUtils dateTimeUtils = new DateTimeUtils();
			IpdDAO ipdDAO= new JDBCIpdDAO(connection);
			ArrayList<Invoice>invoiceList  = new ArrayList<Invoice>();
			String payby = accountsForm.getSelectedPayBy();
			String clientId = (String)session.getAttribute("autoupdate_clientId");
			if(clientId==null){
				clientId = accountsForm.getClientId();
				if(clientId.equals("")) {
					clientId=(String)session.getAttribute("clientId");
				}
			}else{
				session.removeAttribute("autoupdate_clientId");
			}
			String client = accountsForm.getClient();
			String transactionType = accountsForm.getTransactionType();
			String location = accountsForm.getLocation();
			String thirdParty = accountsForm.getThirdParty();
			
			int appointmentid = notAvailableSlotDAO.getLastAppointmentId(clientId);
			AssessmentFormDAO assessmentFormDAO = new JDBCAssessmentFormDAO(connection);
			int bedid = assessmentFormDAO.getIpdBedno(clientId);
			if(bedid==0){
					NotAvailableSlot notAvailableSlot = notAvailableSlotDAO.getOTData(Integer.toString(appointmentid));
					if(notAvailableSlot.getProcedure()!=null){
						if(!notAvailableSlot.getProcedure().equals("0")){
							UserProfileDAO userProfileDAO = new JDBCUserProfileDAO(connection);
							UserProfile userProfile = userProfileDAO.getUserprofileDetails(notAvailableSlot.getSurgeonid());
							location = userProfile.getDiciplineName();
						}
					}
				}
			
			
			//lokesh 14-11-18 for getting list of ipdid
			ArrayList<Ipd> ipdseqlist=ipdDAO.getAllIpdList(clientId);
			accountsForm.setIpdseqlist(ipdseqlist);
			int ipdid=accountsForm.getIpdid();
			
			
			//new ipdid for filters
			String newipdid= accountsForm.getIpdidnew();
			if(newipdid==null){
				newipdid="";
			}
			
			int lastipdid=ipdDAO.getLastIpdId(clientId);
			accountsForm.setLastipdid(String.valueOf(lastipdid));
			//set selected clientid from session
			
			if(clientId==null) {
			 if(session.getAttribute("sessionselectedclientid")!=null){
				clientId = (String)session.getAttribute("sessionselectedclientid");
				accountsForm.setClientId(clientId);
				Client clients = clientDAO.getSelectedSessionClientDetails(clientId);
				accountsForm.setClient(clients.getClientName());
				client = clients.getClientName();
			 }
			}
			
			
			if(clientId!=null){
				
				if(!clientId.equals("")){
					 
					accountsForm.setClientId(clientId);
					Client clients = clientDAO.getSelectedSessionClientDetails(clientId);
					accountsForm.setClient(clients.getClientName());
					client = clients.getClientName();
				}
				
			}
			
			
			String defaultVal = "All";
//			if(payby==null){
//				payby = request.getParameter("payby");
//			}
			if(payby==null) {
				payby=defaultVal;
			}
			
			if(clientId==null){
				clientId = request.getParameter("clientId");
			}
			if(client==null){
				client = request.getParameter("clientName");
			}
			if(location==null){
				location = request.getParameter("location");
			}
			if(transactionType == null){
				transactionType = defaultVal;
			}
			if(thirdParty == null){
				thirdParty = defaultVal;
			}
			

			if(clientId!=null){
				
				if(clientId.equals("")){
				   
					 clientId=request.getParameter("clientid");
				}
			}
			
			if(clientId!=null){
				
				if(clientId.equals("")){
					clientId=null;
				}
			}
			
			
			if(clientId==null){
				
				clientId=(String)session.getAttribute("clientId");
			}
			
			
			session.setAttribute("clientId", clientId);
			session.setAttribute("client", client);
			session.setAttribute("payby", payby);
			session.setAttribute("transactionType", transactionType);
			session.setAttribute("location", location);
			session.setAttribute("thirdParty", thirdParty);
			session.setAttribute("pagination", pagination);
			
			
			String fromDate = accountsForm.getFromDate();
			String toDate = accountsForm.getToDate();
			
			accountsForm.setIpdid(ipdid);
			
			if(fromDate.equals("")){
				DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
				Calendar cal = Calendar.getInstance();
				cal.add(Calendar.DATE, -30); 
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
			
			
			if(!clientId.equals("")){
				if(transactionType.equals(defaultVal)){
					transactionType = "";
				}
				if(location.equals(defaultVal)){
					location = "";
				}
				if(thirdParty.equals(defaultVal)){
					thirdParty = "";
				}
				accountsForm.setSelectedPayBy(payby);
				
				if(payby.equals(defaultVal)){
					payby = "";
				}
			
				
				
				
				if(fromDate == null){
					fromDate = "";
				}
				if(toDate == null){
					toDate = "";
				}
				if(!fromDate.equals("")){
					String temp[]= fromDate.split("/");
					fromDate = temp[2]+"-"+temp[1]+"-"+temp[0];
				}
				if(!toDate.equals("")){
					String temp1[]= toDate.split("/");
					toDate = temp1[2]+"-"+temp1[1]+"-"+temp1[0];
				}
				
				String addOnTableName="";
				boolean dateWithinRange1 = DateTimeUtils.validateDateBetweenRange(fromDate);
				boolean dateWithinRange2 = DateTimeUtils.validateDateBetweenRange(toDate);
				if(dateWithinRange1 && dateWithinRange2){
					addOnTableName="_view";
				}
				
				accountList = statementDAO.getAccountList(clientId,payby,pagination,transactionType,location,thirdParty,fromDate,toDate,newipdid,addOnTableName);
				
								
				int i =1;
				double b = 0;
				double c = 0;
				double d = 0;
				double totalrefund = 0;
				double totaldisc=0;
				
				
				if(accountList.size()>0 && transactionType.equals("") || transactionType.equals("DNA") || transactionType.equals("Charge")){
					for(Accounts accounts :accountList ){
						
						
						/*accountsForm.setBalanceTotal(accounts.getBalanceTotal());
						accountsForm.setDebitTotal(accounts.getDebitTotal());
						accountsForm.setCreditTotal(accounts.getCreditTotal());*/
						if(i == accounts.getTotalChargesCount() && transactionType.equals("") && accounts.getInvoiceList().size()>0){
							
							for(Invoice invoice : accounts.getInvoiceList()){
								 b = b  + invoice.getBalance();
								 c = c + Double.parseDouble(invoice.getCreditCharge());
								 d = d  + invoice.getDebitAmount();
								 totalrefund = totalrefund + Double.parseDouble(invoice.getRefundAmountx());
							     totaldisc =totaldisc + invoice.getDiscount();
							     if(invoice.isInddiscsts()){
									 inddiscount=inddiscount+Double.parseDouble(invoice.getInddisctot());
									 
								 }
								
								
							}
							
							 b = b + accounts.getBalanceTotal() ;
							 c = c + accounts.getCreditTotal() ;
							 d = d +  accounts.getDebitTotal() ;
						}else{
								
							/* b = accounts.getBalanceTotal() ;
							 c = accounts.getCreditTotal() ;
							 d = accounts.getDebitTotal() ;
*/		
							 b = b + accounts.getBalanceTotal() ;
							 c = c + accounts.getCreditTotal() ;
							 d = d +  accounts.getDebitTotal() ;
						
						}
						
						
						i++;
					
					
					}
					
				}else{
					
					accountList = new ArrayList<Accounts>();
					
					
					if(transactionType.equals(Constants.PAID)){
						invoiceList = statementDAO.getPaidInvoiceList(clientId,  payby, transactionType,  location,  thirdParty, fromDate, toDate, newipdid);
					}else if(transactionType.equals(Constants.PENDING_PEYMENT)){
						invoiceList = statementDAO.getPendingInvoiceList(clientId,  payby, transactionType,  location,  thirdParty, fromDate, toDate, newipdid);
					}else{
						invoiceList = statementDAO.getInvoiceList(clientId,  payby, transactionType,  location,  thirdParty, fromDate, toDate, newipdid);
					}
					
					if(!transactionType.equals("Charge")){
						
						if(!transactionType.equals(Constants.CD_CHARGE)){
							
							accountsForm.setInvoiceList(invoiceList);
							
							for(Invoice invoice : invoiceList){
								 b = b  + invoice.getBalance();
								 c = c + Double.parseDouble(invoice.getCreditCharge());
								 d = d  + invoice.getDebitAmount();
								 totalrefund = totalrefund + Double.parseDouble(invoice.getRefundAmountx());
								 totaldisc =totaldisc + invoice.getDiscount();
								 if(invoice.isInddiscsts()){
									 inddiscount=inddiscount+Double.parseDouble(invoice.getInddisctot());
									 
								 }
							}
						}
						
					}
					
				}
//				d=d+inddiscount;
//				totaldisc=totaldisc+inddiscount;
//				b=b+inddiscount;
				accountsForm.setTotalrefund(totalrefund);
				accountsForm.setBalanceTotal(b);
				accountsForm.setDebitTotal(d);
				accountsForm.setCreditTotal(c);
				accountsForm.setDiscount(DateTimeUtils.changeFormat(totaldisc));
				
				//Decimal Amount
				accountsForm.setBalanceTotalx(dateTimeUtils.changeFormat(b));
				accountsForm.setDebitTotalx(dateTimeUtils.changeFormat(d));
				accountsForm.setCreditTotalx(dateTimeUtils.changeFormat(c));
				
				accountsForm.setAccountList(accountList);
				accountsForm.setClientId1(clientId);
				accountsForm.setPayby1(payby);
				accountsForm.setClient1(client);
				accountsForm.setClient(client);
				CompleteAppointment completeAppointment = new CompleteAppointment();
				ArrayList<Accounts> clientDetailsList = accountsDAO.getClientDetails(clientId);
				for(Accounts a1:clientDetailsList){
					accountsForm.setInitial(a1.getInitial());
					accountsForm.setFirstname(a1.getFirstname());
					accountsForm.setLastname(a1.getLastname());
					accountsForm.setMiddlename(a1.getMiddlename());
					accountsForm.setAddress(a1.getAddress());
					accountsForm.setCity(a1.getCity());
					accountsForm.setClientId(clientId);
					accountsForm.setPostcode(a1.getPostcode());
					accountsForm.setEmail(a1.getEmail());
					accountsForm.setMobno(a1.getMobno());
					accountsForm.setAbrivationid(a1.getAbrivationid());
					accountsForm.setDob(a1.getDob());
				}
				
				if(client==null){
					 client="";
				}
				if(client.equals("")){
				 
					//client=accountsForm.getInitial()+" "+accountsForm.getFirstname()+" "+accountsForm.getMiddlename()+" "+accountsForm.getLastname();
					client=accountsForm.getFirstname();
					accountsForm.setClient(client);
					accountsForm.setClientId(clientId);
				}
				Client client2 = accountsDAO.getTPClientData(clientId);
				String thirdPartyname = accountsDAO.getTpDetailsbyclientid(Integer.parseInt(clientId));
				String company_name=accountsDAO.getcompany_name(Integer.parseInt(thirdPartyname));
				if(!clientId.equals("")){
					completeAppointment = completeAptmDAO.getInsuranceCompanyName(clientId);
					accountsForm.setInsuranceCompany(completeAppointment.getInsuranceCompanyName());
					accountsForm.setThirdPartyAddress(completeAppointment.getInsuranceCompanyAddress());
					accountsForm.setThirdPartyContacttno(completeAppointment.getThirdPartyContacttno());
					accountsForm.setThirdPartyPostcode(completeAppointment.getThirdPartyPostcode());
 					accountsForm.setThirdPartyemail(completeAppointment.getThirdPartyemail());
 					accountsForm.setClientId(clientId);
 					if(client2.getCompname()==null){
 						accountsForm.setEmployeenamebytp("");
 					}
 					if(client2.getNeisno()==null){
 						accountsForm.setNeiscardno("");
 					}
 					if(client2.getColliery()==null){
 						accountsForm.setColliery("");
 					}
 					if(client2.getAreabytp()==null){
 						accountsForm.setAreatp("");
 					}
 					if(client2.getClaimbytp()==null){
 						accountsForm.setClaimid("");
 					}
 					accountsForm.setEmployeenamebytp(client2.getCompname());
 					accountsForm.setThirdParty(company_name);
 					accountsForm.setNeiscardno(client2.getNeisno());
 					accountsForm.setColliery(client2.getColliery());
 					accountsForm.setAreatp(client2.getAreabytp());
 					accountsForm.setClaimid(client2.getClaimbytp());
 					String tpname=accountsDAO.gettpnamebyid(client2.getThirdPartyType());
 					if(tpname.equals("CGHS")){
 						accountsForm.setTpname("CGHS");
 					}else if(tpname.equals("WCL")){
 						accountsForm.setTpname("WCL");
 						
 					}
				}
				
			}
			
			
			boolean isautochargeOn= completeAptmDAO.isAutoChargedOn(clientId);
			if(isautochargeOn){
				accountsForm.setAutocharge("1");
			} else {
				accountsForm.setAutocharge("0");
			}

			//}
			
			//set credit list
			if(transactionType.equals("") || transactionType.equals(Constants.CD_CHARGE)){
				
				AdditionalDAO additionalDAO = new JDBCAdditionalDAO(connection);
				ArrayList<Accounts>creditList = additionalDAO.getCreditAccountList(clientId,newipdid);
				
				accountsForm.setCreditList(creditList);
				double acr = 0;
				double adb = 0;
				double creditbal = 0;
				double totalpayment = 0;
				double abalance = 0;
				if(creditList.size()>0){
					for(Accounts a : creditList){
						acr = acr + Double.parseDouble(a.getCharges());
						adb = adb + Double.parseDouble(a.getDebitTotalx());
						
					}
					
					//acr = accountsForm.getCreditTotal()+ acr;
					//adb = accountsForm.getDebitTotal() + adb;
					adb = accountsForm.getDebitTotal();
					
				//	double balance = accountsDAO.getBlanceAmount(clientId);
					//balance = accountsForm.getBalanceTotal() + balance;
					
				//	accountsForm.setBalanceTotal(balance);
					accountsForm.setDebitTotal(adb);
					//accountsForm.setCreditTotal(acr);
					
					//Decimal Amount
					//accountsForm.setBalanceTotalx(dateTimeUtils.changeFormat(balance));
					//double totalpayment = accountsForm.getCreditTotal() + acr;
					 totalpayment = accountsForm.getCreditTotal();
					accountsForm.setTotalpaymentx(dateTimeUtils.changeFormat(totalpayment));
					accountsForm.setDebitTotalx(dateTimeUtils.changeFormat(adb));
					accountsForm.setCreditTotalx(dateTimeUtils.changeFormat(accountsForm.getCreditTotal()));
					
					 creditbal = statementDAO.getLastCreditBalance(clientId);
					accountsForm.setCreditBalancex(dateTimeUtils.changeFormat(creditbal));
					
					
				
					

				}
				
				
				//set self credit
				double advcash = totalpayment + creditbal;
				double selefbalance = 0;
				double selfcredit = 0;
				boolean flag= false;
				if(adb==0){
					flag = true;
					adb = accountsForm.getBalanceTotal();
				}
				if(advcash>adb){
					//double discref = additionalDAO.getDiscRefundAmount(clientId);
					//if there is direct discount then it also remove from self balance so we get discount from above code
					//double disccal = Double.parseDouble(accountsForm.getDiscount()) - discref;
					double newadb = adb;
					if(!flag){
						newadb = adb - (Double.parseDouble(accountsForm.getDiscount()));
					}
					selfcredit =   advcash - newadb;
					selefbalance =   0;
					
				}else{
					selefbalance =   adb - advcash;
					//  28 May 2018
					//double discref = additionalDAO.getDiscRefundAmount(clientId);
					//selefbalance = selefbalance - discref;
					//  its minus discount double  16-11-2019
					if(!flag){
						selefbalance = selefbalance - (Double.parseDouble(accountsForm.getDiscount()));
					}
					
					//if Total payment is 1000, total charges 2000 and discount 1000 then outstanding must be 0 
					//but after that take advance of 100 then its show outstanding  -100 so for this below code
					if (selefbalance<0) {
						selefbalance = selefbalance + creditbal;
						//  20 Aug 2018 discount first apply and then payment taken
						if(Double.parseDouble(accountsForm.getDiscount())>0){
							if (selefbalance<0) {
								selefbalance=0;
							}
						}
					}
				}
				
				/*double discref = additionalDAO.getDiscRefundAmount(clientId);
				selefbalance = selefbalance - discref;*/
				
				accountsForm.setBalanceTotalx(dateTimeUtils.changeFormat(selefbalance));
				accountsForm.setSelfcredit(dateTimeUtils.changeFormat(selfcredit));
				double totcharge=Double.parseDouble(accountsForm.getDebitTotalx())+inddiscount;
				double totdisc=Double.parseDouble(accountsForm.getDiscount())+inddiscount;
				accountsForm.setDebitTotalx(String.valueOf(totcharge));
				accountsForm.setDiscount(String.valueOf(totdisc));
			}
			
			
			
			//createPdf(accountsForm.getAccountList(),accountsForm.getInvoiceList(),accountsForm.getCreditList(),accountsForm.getBalanceTotalx(),accountsForm.getDebitTotal(),accountsForm.getCreditTotalx(),clientId,client);
			//  05 oct 2017 for set dr list in charge sharing popup
			//UserProfileDAO profileDAO = new JDBCUserProfileDAO(connection);
			//ArrayList<Master> doctorlist = profileDAO.getDoctorList();
			//accountsForm.setDoctorlist(doctorlist);
			
			
			accountsForm.setIpdidnew(newipdid);
			//  03/12/2018 show admission date
			BedDao bedDao=new JDBCBedDao(connection);
			Bed bed = new Bed();
			String ipdid1="";
			for (Ipd ipd1 : ipdseqlist) {
				ipdid1=ipd1.getIpdseqno();
			}
			int ipdidadmiss=ipdDAO.getLastIpdId(clientId);
			if(!newipdid.equals("")){
				 bed = bedDao.getEditIpdData(String.valueOf(newipdid));
			}else{
			 bed = bedDao.getEditIpdData(String.valueOf(ipdidadmiss));
			}
			String admissiondate=bed.getAdmissiondate();
			if(ipdid1==null)
			{
				ipdid1="";
			}
			if(ipdid1.equals("0")){
				ipdid1="";
			}
			if(admissiondate==null)
			{
				admissiondate="";
			}
			if(admissiondate.equals("0")){
				admissiondate="";
			}
			
			if(!admissiondate.equals("")){
				String[]temp=admissiondate.split(" ");
				String admissiondate1=temp[0];
				String admissiontime=DateTimeUtils.getCommencingDate1(admissiondate1);
				accountsForm.setAdmissionDate(admissiontime+" "+temp[1]);
			}else{
				accountsForm.setAdmissionDate("");
			}
			
			
			accountsForm.setHourList(PopulateList.hourList());
			accountsForm.setMinuteList(PopulateList.getMinuteList());
			//ArrayList<Master>packageList = ipdDAO.getPackageList();
			//accountsForm.setPackageList(packageList);
			String curdate = DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone()).split(" ")[0];
			accountsForm.setPkgfromdate(DateTimeUtils.getCommencingDate1(curdate));
			accountsForm.setPkgtodate(DateTimeUtils.getCommencingDate1(curdate));
			String datetime=DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone());
			String temp1[] = datetime.split(" ");
			String time[] = temp1[1].split(":");
			String hh = time[0];
			String mm = time[1];
			accountsForm.setHour(hh);
			accountsForm.setMinute(mm);
			/*ArrayList<Breadcrumbs> indentflowlist = new ArrayList<Breadcrumbs>();
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
				if(breadcrumbs.getName().equals("Patient Account")){
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
				breadcrumbs.setName("Patient Account");
				breadcrumbs.setOn(true);
				breadcrumbs.setSqno(modulecount);
				breadcrumbs.setUrllink("Statement");
				breadcrumbs.setIscurrent(true);
				indentflowlist.add(breadcrumbs);
			}
			session.setAttribute("indentflowlist",indentflowlist);*/
		}catch (Exception e) {
			e.printStackTrace();
		}
		finally{
			connection.close();
		}
		
		return SUCCESS;
	}
	
	
	public String otsm() throws Exception{
		
		String id = request.getParameter("id");
		String clientId = request.getParameter("clientid");
		String procedure = request.getParameter("procedure");
		String amount = request.getParameter("amount");
		
		if(!verifyLogin(request)){
			return "login";
		}
		
		Connection  connection = null;
		
		try{
			connection = Connection_provider.getconnection();
			AccountsDAO accountsDAO = new JDBCAccountsDAO(connection);
			UserProfileDAO userProfileDAO = new JDBCUserProfileDAO(connection);
			ClientDAO clientDAO = new JDBCClientDAO(connection);
			
			String smsuser = accountsDAO.getOtsmsUsers(id);
			
			Client client = clientDAO.getClientDetails(clientId);
			String fullname = client.getTitle() + " " + client.getFirstName() + " "+client.getMiddlename()+" " + client.getLastName();
			
			String temp[] = smsuser.split(",");
			for(int i=0;i<temp.length;i++){
				UserProfile userProfile = userProfileDAO.getUserprofileDetails(Integer.parseInt(temp[i]));
				String doctor = userProfile.getInitial() + " " + userProfile.getFirstname() + " " + userProfile.getLastname();
				
				String msg = doctor + " its has been informed you that " + fullname + " has paid amount " +
						Constants.getCurrency(loginInfo) + amount + " for " + procedure + " treatment";
				
				SendSms sms = new SendSms();
				
				sms.send(msg, userProfile.getMobile(), loginInfo, new EmailLetterLog());
				
				
			}
			
			int upd = accountsDAO.updateOtmsgStatus(id,loginInfo.getId());
			
			accountsForm.setMessage("Message Sent Sucessfully!!");
			addActionMessage("Message Sent Sucessfully!!");
			
//			System.out.println(smsuser);
			
			
			session.setAttribute("otsmsclientId", clientId);
			
		}catch (Exception e) {
			// TODO: handle exception
		}finally{
			
			connection.close();
		}
		
		return otdetails();
	}
	
	
	public String otdetails() throws Exception{
		String clientId = request.getParameter("clientId");
		if(clientId==null){
			clientId = (String)session.getAttribute("otsmsclientId");
		}
		
		if(!verifyLogin(request)){
			return "login";
		}
		
		Connection  connection = null;
		
		try{
			connection = Connection_provider.getconnection();
			AccountsDAO accountsDAO = new JDBCAccountsDAO(connection);
			ArrayList<Clinic> locationAdressList = accountsDAO.getLocationAddress(loginInfo.getClinicid());
			
			//ot details
			ArrayList<NotAvailableSlot>otDetailsList = accountsDAO.getOtDetailsList(clientId);
			accountsForm.setOtDetailsList(otDetailsList);
			
			
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
			
			
		}catch (Exception e) {
			// TODO: handle exception
		}
		
		return "otdetails";
	}
	
	
	public String edbedlog() throws Exception{
		//var url = "edbedlogStatement?clientId="+clientId+"&indx="+indx+"&size="+size+"&frmdate="+frmdate+"&id="+id+" ";
		String clientId = request.getParameter("clientId");
		String indx = request.getParameter("indx");
		String size = request.getParameter("size");
		//String frmdate = request.getParameter("frmdate");
		String id = request.getParameter("id");
		
		if(!verifyLogin(request)){
			return "login";
		}
		
		Connection  connection = null;
		
		try{
			connection = Connection_provider.getconnection();
			AccountsDAO accountsDAO = new JDBCAccountsDAO(connection);
			
			StringBuffer str = new StringBuffer();
			//bedchangeLogList
			ArrayList<Bed>bedchangeLogList = (ArrayList<Bed>)session.getAttribute("bedchangeLogList");
			int lenght = bedchangeLogList.size()-1;
			
			String fromdate = "";
			String todate = "";
			int ind = Integer.parseInt(indx) - 1;
			
			str.append("<input type='hidden' name='bedlogid' id='bedlogid' value='"+id+"'>");
			str.append("<input type='hidden' name='bedlogclientid' id='bedlogclientid' value='"+clientId+"'>");
			str.append("<input type='hidden' name='bedlogindx' id='bedlogindx' value='"+indx+"'>");
			if(Integer.parseInt(indx)>0){
				Bed bed = bedchangeLogList.get(Integer.parseInt(indx));
				fromdate = bed.getDate();
			    bed = bedchangeLogList.get(ind);
				todate = bed.getDate();
				
			
			}else{
				Bed bed = bedchangeLogList.get(Integer.parseInt(indx));
				fromdate = bed.getDate();
				todate = DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone());
				
			}
			
			str.append("<tr>");
			str.append("<td>FromDate : <input type='text' class='form-control'  name='fdate' id='fdate' value='"+fromdate+"'></td>");
			str.append("<td>ToDate : <input type='text' class='form-control'  name='tdate' id='tdate' value='"+todate+"'></td>");
			str.append("</tr>");
			
			
			response.setContentType("text/html");
			response.setHeader("Cache-Control", "no-cache");
			
			response.getWriter().write(""+str.toString()+""); 
			
		}catch (Exception e) {
			e.printStackTrace();
		}finally{
			
			connection.close();
		}
		
		return null;
	}
	
	public String updbed() throws Exception{
		
		String id = request.getParameter("bedlogid");
		String fdate = request.getParameter("fdate");
		String tdate = request.getParameter("tdate");
		String clientid = request.getParameter("bedlogclientid");
		String bedlogindx = request.getParameter("bedlogindx");
		
		int ind = Integer.parseInt(bedlogindx) - 1;
		
		if(!verifyLogin(request)){
			return "login";
		}
		
		Connection  connection = null;
		
		try{
			session.setAttribute("chargedtailclientid", clientid);
			connection = Connection_provider.getconnection();
			AccountsDAO accountsDAO = new JDBCAccountsDAO(connection);
			
			String temp[] = fdate.split(" ");
			fdate = DateTimeUtils.getCommencingDate1(temp[0]);
			
			String temp1[] = tdate.split(" ");
			tdate = DateTimeUtils.getCommencingDate1(temp1[0]);
			
			ArrayList<Bed>bedchangeLogList = (ArrayList<Bed>)session.getAttribute("bedchangeLogList");
			int lenght = bedchangeLogList.size()-1;
			
			Bed bed = bedchangeLogList.get(ind);
			
			int upd = accountsDAO.updateBedLogDate(id,fdate + " " + temp[1]);
			 upd = accountsDAO.updateBedLogDate(Integer.toString(bed.getId()),tdate + " " + temp1[1]);
			
			long diff= DateTimeUtils.getDifferenceOfTwoDateDBFormat(fdate, tdate);
			int  qty =(int) diff;
			
			ArrayList<Accounts>bedchargeList = accountsDAO.getbedchargeList(clientid,fdate,tdate);
			
			for(Accounts accounts : bedchargeList){
				 upd = accountsDAO.updateChargeqty(Integer.toString(accounts.getId()),Integer.toString(qty));
			}
			
			
			
		}catch (Exception e) {
			// TODO: handle exception
		}finally{
			
			connection.close();
		}
		
		return "chargeupdated";
	}
	
	
	boolean isprntchrg = false;
	boolean issubcharge = false;
	
	public String prntsubcharge() throws Exception{

		issubcharge = true;
		String clientId = (String)session.getAttribute("chargedtailclientid");
		accountsForm.setClientId(clientId);
		String sname = request.getParameter("sname");
		accountsForm.setMasterchargetype(sname);
		
		return detail();
	}
	
	public String prntchrg() throws Exception{
		
		isprntchrg = true;
		
		String clientId = (String)session.getAttribute("chargedtailclientid");
		accountsForm.setClientId(clientId);
		accountsForm.setSelectipdid(request.getParameter("ipdid"));
		return detail();
	}
	public String detail() throws Exception{
		
		if(!verifyLogin(request)){
			return "login";
		}
		
		Connection  connection = null;
		
		try{
			connection = Connection_provider.getconnection();
			AccountsDAO accountsDAO = new JDBCAccountsDAO(connection);
			AdditionalDAO additionalDAO = new JDBCAdditionalDAO(connection);
			ArrayList<Clinic> locationAdressList = accountsDAO.getLocationAddress(loginInfo.getClinicid());
			StatementDAO statementDAO=new JDBCStatementDAO(connection);
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
			Clinic ccbg =  locationAdressList.get(0);
			session.setAttribute("balgopaladdress", ccbg.getAddress());
			session.setAttribute("balgopalcname", ccbg.getClinicName());
			accountsForm.setClinicLogo(clinic.getUserImageFileName());
			
			ArrayList<IpdFakeConsult> consultantdoclist=new ArrayList<IpdFakeConsult>();
			/*IpdFakeConsultDAO ipdFakeConsultDAO=new JDBCIpdFakeConsultDAO(connection);
			consultantdoclist=ipdFakeConsultDAO.getDoctorwithsepecialitylist();*/
			UserProfileDAO userProfileDAO = new JDBCUserProfileDAO(connection);
			ArrayList<UserProfile> visitingConsultDoctors = userProfileDAO.getVisitingPractitinerList();
			accountsForm.setConsultantdoclist(visitingConsultDoctors);
			
			//client details
			
			String clientId = DateTimeUtils.isNull(accountsForm.getClientId());
			
			if(clientId.equals("")){
				clientId = (String)session.getAttribute("chargedtailclientid");
			}
		session.setAttribute("chargedtailclientid", clientId);
			
			Client client = accountsDAO.getClientData(clientId);
			accountsForm.setClient(DateTimeUtils.getPatientFullname(client));
			accountsForm.setAddress(client.getAddress());
			accountsForm.setClienttown(client.getTown());
			accountsForm.setClientpostcode(client.getPostCode());
			
			String age = DateTimeUtils.getAge1(client.getDob());
			accountsForm.setAgegender(age + "/" + client.getGender());
			
			accountsForm.setDob(client.getDob());
			accountsForm.setClientId(clientId);
			accountsForm.setEmail(client.getEmail());
			accountsForm.setMobno(client.getMobNo());
			accountsForm.setPolicyNo(client.getPolicyNo());
			accountsForm.setAbrivationid(client.getAbrivationid());
			ClientDAO clientDAO=new JDBCClientDAO(connection);
			IpdDAO ipdDAO=new JDBCIpdDAO(connection);
			String ipdid="";
			if(accountsForm.getSelectipdid()!=null){
				if(!accountsForm.getSelectipdid().equals("")){
					ipdid=accountsForm.getSelectipdid();
				}else{
					ipdid=ipdDAO.getIpdId(clientId);
				}
			}else{
				ipdid=ipdDAO.getIpdId(clientId);
			}
			 
			if(ipdid==null){
				ipdid="";
			}
			if(ipdid.equals("0")){
				ipdid="";
			}
			Client client2 = accountsDAO.getTPClientData(clientId);
			String tpname=accountsDAO.gettpnamebyid(client2.getThirdPartyType());
			//  23-04-2019 showing ipdid list
			ArrayList<Ipd> ipdseqlist=ipdDAO.getAllIpdList(clientId);
			accountsForm.setIpdseqlist(ipdseqlist);
			
			String thirdParty = accountsDAO.getTpDetailsbyclientid(Integer.parseInt(clientId));
			String company_name=accountsDAO.getcompany_name(Integer.parseInt(thirdParty));
			ThirdParty thirdPartyobj = new ThirdParty();
			thirdPartyobj = accountsDAO.getTpDetails(Integer.parseInt(thirdParty));
			accountsForm.setPayeename(thirdPartyobj.getCompanyName());
			accountsForm.setThirdParty(company_name);
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
			if(tpname.equals("CGHS")){
				
					accountsForm.setStatusfortp(true);
					accountsForm.setCompanyname("CGHS");
				}
				else if(tpname.equals("WCL")){
					
						accountsForm.setStatusfortp(true);
						accountsForm.setCompanyname("WCL");
					}else if(tpname.equals("INSURANCE COMPANY")){
						accountsForm.setStatusfortp(true);
						accountsForm.setCompanyname(tpname);
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
			if(!ipdid.equals("")){
			if(loginInfo.getIpd_abbr_access()==1){
				 String newipdabbr=ipdDAO.getIpdAbrivationIds(Integer.parseInt(ipdid));
				   accountsForm.setNewipdabbr(newipdabbr);
				   accountsForm.setIpdseqno(ipdid);
			}else{
			accountsForm.setIpdseqno(ipdDAO.getipdseqno( ipdid));
			 accountsForm.setNewipdabbr(ipdid);
			}
			}
			//setting master chartype data
			String curdate = DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone());
			String temp[] = curdate.split(" ");
			double totaldiscount=0;
			curdate = DateTimeUtils.getCommencingDate1(temp[0]);
			ArrayList<Master> masterAssessmentList = accountsDAO.getdchargeMasterAssessmentList(clientId,curdate,loginInfo,isprntchrg,accountsForm.getSelectipdid());
			accountsForm.setMasterAssessmentList(masterAssessmentList);
			
			String mastername="";
			if(masterAssessmentList.size()!=0){
				Master names=masterAssessmentList.get(masterAssessmentList.size()-1);
				String inv=names.getMasterchargnames();
				
				String temp1[]=inv.split(",");
				for (String string : temp1) {
					if(!string.equals(" ")){
						String data=string;
						 if(mastername.equals("")){
							 mastername=data;
						 }else{
							 mastername=mastername+","+data;
						 }
					}
				  }
				}
			int i=0;
			double masterchargetotl=0;
			Vector<Accounts> reportassesslist=new Vector<Accounts>();
		Vector<Accounts>	assesmentList1 =accountsDAO.getdchargeFilteredPreviewChargesList(mastername,clientId,curdate,loginInfo,isprntchrg,ipdid,0,"");
		ArrayList<Master> masterAssessmentList1=new ArrayList<Master>(); 
		for (Master master : masterAssessmentList) {
			Master m1=new Master();
			m1.setName(master.getName());
			m1.setSubtotal(master.getSubtotal());
			masterchargetotl=masterchargetotl+m1.getSubtotal();
			reportassesslist=new Vector<Accounts>();
			for (Accounts accounts : assesmentList1) {
				master=masterAssessmentList.get(i);
				if(master.getName().equals(accounts.getMasterchargetype())) {
					System.out.println(master.getName()+" "+accounts.getMasterchargetype()+" "+accounts.getAptmname());
					reportassesslist.add(accounts);
				}
			}
			m1.setAssesmentList(reportassesslist);
			masterAssessmentList1.add(m1);
			accountsForm.setMasterAssessmentList(masterAssessmentList1);
			i++;
		}
			
			int size=masterAssessmentList.size();
			if(DateTimeUtils.isNull(ipdid).equals("")){
				ipdid="0";
			}
			accountsForm.setIpdidnew(ipdid);
			String Drname="";
			if(ipdid==null || ipdid.equals("")){
			 Drname=accountsDAO.getDrNamebyClientId(clientId);
			}else{
				 Drname=accountsDAO.getDrNamebyIPDId(ipdid);
			}
			if(Drname.equals("")){
				Drname=accountsDAO.getOpdPracName(clientId);
			}
			accountsForm.setIpdconsultant(Drname);
			String finalDiagnosis="";
			if(!ipdid.equals("")){
			String textdiaggnosis=accountsDAO.gettextdiagnosis(Integer.parseInt(ipdid));
			if(textdiaggnosis==null){
				textdiaggnosis="";
			}
			if(textdiaggnosis.equals("<br>")){
				textdiaggnosis="";
			}
			if(!textdiaggnosis.equals("")){
				accountsForm.setFinalDiagnosis(textdiaggnosis);
				
			}else{
			String fdid = accountsDAO.getFdID(Integer.parseInt(ipdid));
		    finalDiagnosis = accountsDAO.getIpdFinalDiagnosis(fdid);
			}
			accountsForm.setFinalDiagnosis(finalDiagnosis);
		}
			session.setAttribute("finaldiagnosis", accountsForm.getFinalDiagnosis());
			if(masterAssessmentList.size()!=0){
				
				Master master = masterAssessmentList.get(masterAssessmentList.size()-1);
				accountsForm.setDchtotal(""+masterchargetotl);
				accountsForm.setTotaldiscount(master.getTotaldiscount());
				double netamt=Double.parseDouble(accountsForm.getDchtotal())-accountsForm.getTotaldiscount();
				accountsForm.setTotalpaymentx(DateTimeUtils.changeFormat(netamt));
			}
			if(accountsForm.getSelectipdid()==null){
				accountsForm.setSelectipdid("");
			}
			accountsForm.setSelectipdid(accountsForm.getSelectipdid());
			double creditbal = statementDAO.getLastCreditBalance(clientId);
			accountsForm.setCreditAmt(DateTimeUtils.changeFormat(creditbal));
			if(creditbal>(Double.parseDouble(accountsForm.getTotalpaymentx()))){
				accountsForm.setAdvancestatus("Credit Balance");
				double balance=creditbal-(Double.parseDouble(accountsForm.getTotalpaymentx()));
				accountsForm.setBalanceAmount(DateTimeUtils.changeFormat(balance));
				accountsForm.setColorcode("black");
			}else{
				accountsForm.setAdvancestatus("Balance");
				double balance=(Double.parseDouble(accountsForm.getTotalpaymentx()))-creditbal;
				accountsForm.setBalanceAmount(DateTimeUtils.changeFormat(balance));
				accountsForm.setColorcode("red");
			}
			//credit list
			//String chargesids=master.getChargeid();
			ArrayList<Accounts> creditList = additionalDAO.getCreditAccountList(clientId,"");
			accountsForm.setCreditList(creditList);

			double credit = 0;
			String adate="";
			String hh ="";
			String mm="";
			Client client3=new Client();
			client3=accountsDAO.getDisandadmiss(clientId);
			if(!client3.getAdmissiondate().equals("")){
			String temp1[] = client3.getAdmissionwithtime().split(" ");
			 adate = DateTimeUtils.getCommencingDate1(temp1[0]);
			String time[] = temp1[1].split(":");
			 hh = time[0];
			 mm = time[1];
			
			adate = adate + " " + hh + ":" + mm;
			accountsForm.setAdmissionDate(adate);
			if(loginInfo.getIskunal()==1){
				int hourOfDay=Integer.parseInt(hh);
				   int minute=Integer.parseInt(mm);
				   String apmpm =  ((hourOfDay > 12) ? hourOfDay % 12 : hourOfDay) + ":" + (minute < 10 ? ("0" + minute) : minute) + " " + ((hourOfDay >= 12) ? "PM" : "AM");
				   adate= DateTimeUtils.getCommencingDate1(temp1[0])+" "+apmpm;
				   accountsForm.setAdmissionDate(adate);
			}else{
				accountsForm.setAdmissionDate("");
			}
			}else{
				accountsForm.setAdmissionDate("");
			}
			if(client3.getDischargewithtime()==null){
				client3.setDischargewithtime("");
			}
			if(!client3.getDischargewithtime().equals("")){
				 String dtemp[] = client3.getDischargewithtime().split(" ");
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
					   adate= DateTimeUtils.getCommencingDate1(dtemp[0])+" "+apmpm;
					   accountsForm.setDischargeDate(adate);
				}else{
					accountsForm.setDischargeDate("");
				}
			}else{
				accountsForm.setDischargeDate("");
			}
			
			CompleteAptmDAO completeAptmDAO=new JDBCCompleteAptmDAO(connection);
			boolean isautochargeOn= completeAptmDAO.isAutoChargedOn(clientId);
			if(isautochargeOn){
				accountsForm.setAutocharge("1");
			} else {
				accountsForm.setAutocharge("0");
			}
			//showing discharge status   23/02/2019
			String dischargestatus="";
			String dischargehead="";
			
			if(!ipdid.equals("")){
			Bed dischargedata = clientDAO.getdischargedata(ipdid);
			 dischargestatus = dischargedata.getDischargeStatus();
			DischargeStatusDAO statusDAO=new JDBCDischargeStatus(connection);
			 dischargehead=statusDAO.getDischargeStatusById(Integer.parseInt(dischargestatus));
			if(dischargehead==null){
				dischargehead="";
			}
			}
			accountsForm.setDischargestatus(dischargestatus);
			accountsForm.setDischargehead(dischargehead);
			if(client3.getAdmissionwithtime()!=null){
				 if(!client3.getAdmissionwithtime().equals("")){
					 String c=client3.getAdmissionwithtime();
					 String h[]= c.split(" ");
					 //c= DateTimeUtils.getCommencingDate1(h[0]);
					 String d=DateTimeUtils.getAge1onAddmission(accountsForm.getDob(), h[0]);
					 accountsForm.setAgeonaddmission(d);
				 }
			 }
			if(isprntchrg){
				String pkgstr = accountsDAO.getTpPkgStr(clientId);
				double pkgamttot=0;
				ArrayList<Master>mlist = accountsDAO.getTpMasterAssesmentList(pkgstr,loginInfo);
				for (Master master : mlist) {
					pkgamttot=Double.parseDouble(master.getDchargetotal());
				}
				accountsForm.setPkgamttot(pkgamttot);
				accountsForm.setTpMasterAssessmentList(mlist);
				
				
				return "prntchargedetail";
			}
			
			String masterchargename = accountsForm.getMasterchargetype();
			if(issubcharge){
				String pkgstr = accountsDAO.getTpPkgStr(clientId);
				ArrayList<Accounts>assesmentList = accountsDAO.getSubServicesList(clientId,masterchargename,pkgstr,loginInfo);
				
				//dele session data
				int del = accountsDAO.deleteTpTempSessionData(loginInfo.getLoginsessionid());

				//sort by date
				for(Accounts a : assesmentList){
					int res = accountsDAO.saveTpServiceData(a,loginInfo);
				}
				
				assesmentList = accountsDAO.getTpTempAssesmentList(loginInfo);

				accountsForm.setAssesmentList(assesmentList);
				
				double tpstotal = accountsDAO.getsubchargeTotal(clientId,masterchargename);
				double pkgtot= accountsDAO.getsubchargeFinalTotal(clientId,masterchargename);
				accountsForm.setTpstotal(DateTimeUtils.changeFormat(tpstotal));
				accountsForm.setSubpkgamttot(pkgtot);
				
				return "pnrntsubchargedetail";
			}

			
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		return "detailstatement";
	}

	
	


	public String updqty() throws Exception{
		Connection connection  = null;
		String id = request.getParameter("id");
		String qty = request.getParameter("qty");
		
		try{
			
			connection  = Connection_provider.getconnection();
			AccountsDAO accountsDAO = new JDBCAccountsDAO(connection);
			
			int upd = accountsDAO.updateChargeqty(id,qty);
			
		}catch (Exception e) {
			// TODO: handle exception
		}finally{
			
			connection.close();
		}
		
		return null;
	}
	
	public String delcharge() throws Exception{
		Connection connection  = null;
		String id = request.getParameter("id");
		String action = request.getParameter("action");
		
		try{
			
			connection  = Connection_provider.getconnection();
			AccountsDAO accountsDAO = new JDBCAccountsDAO(connection);
			CompleteAptmDAO completeAptmDAO=new JDBCCompleteAptmDAO(connection);
			if(action.equals("0")){
				String assesmentidList = accountsDAO.getAssessmentIdList(id);
				int appointmnt=accountsDAO.getAppointmentIdFromInvoiceid(id);
				if(appointmnt>0){
				int res=accountsDAO.updateOpdchrgststaus(appointmnt);
				}
				Accounts accounts=new Accounts();
				CompleteAptmDAO aptmDAO=new JDBCCompleteAptmDAO(connection);
				String datetime=DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone());
				accounts=accountsDAO.getApm_invoice_Assesments(id);
				CompleteAppointment completeAppointment=completeAptmDAO.getInvoiceAsessmentDetails(accounts.getId());
				int log=accountsDAO.insertchargeDetailsLog(completeAppointment,"0","Delete",loginInfo.getUserId(),datetime);
				
				if(accounts.getTpkg()>0){
				aptmDAO.swapPkgChargeTptoinvoiceList(accounts.getTpfdate(), accounts.getTptodate(), accounts.getIpdid());
				aptmDAO.deletefromtpinvoiceassessment(accounts.getTpfdate(), accounts.getTptodate(), accounts.getIpdid());
				}
//				System.out.println(assesmentidList);
				
				int d = accountsDAO.deleteStandardCharge(assesmentidList);
				
				int del = accountsDAO.delchargeAssesment(id);
				del = accountsDAO.delChargeid(id);
			
			}else{
				int appointmnt=accountsDAO.getAppointmentIdFromInvoiceid(id);
				if(appointmnt>0){
				int res=accountsDAO.updateOpdchrgststaus(appointmnt);
				}
				int dd = accountsDAO.deleteChargeAssesmentOnly(id);
			}
			
			
		}catch (Exception e) {
			// TODO: handle exception
		}finally{
			
			connection.close();
		}
		
		return null;
	}
	
	public String editcharge() throws Exception{
		//var url = "editchargeStatement?ipdid="+ipdid+"&chargeid="+chargeid+"&assesmentid="+assesmentid+" ";
		
		String ipdid = request.getParameter("ipdid");
		String chargeid = request.getParameter("chargeid");
		String assesmentid = request.getParameter("assesmentid");
		String stdcharge =  request.getParameter("stdcharge");
		String newchargename=request.getParameter("chargename");
		String updchargeid=request.getParameter("updchargeid");
		String mastername=request.getParameter("mastername");
		session.setAttribute("chargeipdid", ipdid);
		session.setAttribute("chargechargeid", chargeid);
		session.setAttribute("chargeassesmentid", assesmentid);
		session.setAttribute("stdcharge", stdcharge);
		session.setAttribute("updchargeid", updchargeid);
		
		Connection connection  = null;
		String id = request.getParameter("id");
		
		try{
			
			connection  = Connection_provider.getconnection();
			AccountsDAO accountsDAO = new JDBCAccountsDAO(connection);
			IpdDAO ipdDAO=new JDBCIpdDAO(connection);
			String clientid=ipdDAO.getClientIDFromIPDID(ipdid);
			Accounts accounts = accountsDAO.getAssesmentDetails(assesmentid);
			ArrayList<Accounts> chargelist=accountsDAO.getchargelist(mastername);
			StringBuffer str = new StringBuffer();
//			str.append("<tr>");
//			str.append("<td>Qty : <input type='number' class='form-control'  name='qty"+accounts.getId()+"' id='qty"+accounts.getId()+"' value='"+accounts.getQuantity()+"'></td>");
//			str.append("</tr>");
			ClientDAO clientDAO=new JDBCClientDAO(connection);
			Client client = clientDAO.getClientDetails(clientid);
			String tpid = client.getTypeName();
				 
				String temptpid= ipdDAO.getFollowerTp(tpid); 
				if(temptpid!=null){
					
					if(!temptpid.equals("0")){
						 tpid=temptpid;  
					}
				}
					ThirdPartyDAO thirdPartyDAO = new JDBCThirdPartyDAO(connection);
					ThirdParty thirdParty = thirdPartyDAO.getThirdPartyDetails(tpid);
			ArrayList<Client> apmtmentlist=clientDAO.getApmtNameList(mastername,thirdParty.getChargecolumnname(),tpid);	
			
			boolean isSTdCharge = accountsDAO.getIfSTdCharge(chargeid);
			
			if(isSTdCharge && stdcharge.equals("1")){
				String startEndTime= accountsDAO.getStdChargeStartEndTime(Integer.parseInt(assesmentid),ipdid); 
				session.setAttribute("startEndTime", startEndTime);
				
				
				if(startEndTime!=null){
					String tempc[] = startEndTime.split("~");
					String t1[] = tempc[0].split(",");
					String t2[] = tempc[1].split(",");
					String resultdatetime = "";
					for(int i=0;i<t2.length;i++){
						//resultdatetime = resultdatetime + t1[i] + " - " + t2[i] + " , "; 
						str.append("<tr>");
						str.append("<td>From Date : <input type='text' class='form-control'  name='fdate"+i+"' id='fdate"+i+"' value='"+t1[i]+"' autocomplete='off'></td>");
						str.append("<td>ToDate : <input type='text' class='form-control'  name='tdate"+i+"' id='tdate"+i+"' value='"+t2[i]+"'></td>");
						str.append("</tr>");
						str.append("<tr>");
						str.append("<td>Charge : <input type='text' class='form-control'  name='newchargename' id='newchargename' value='"+newchargename+"'></td>");
						str.append("</tr><br>");
						str.append("<tr>");
						
						str.append("<br><select id='apmttype' name='apmttype' class='form-control chosen'>");
						str.append("<option value='0'>Replace Existing Charge</option>");

						for (Client apmt : apmtmentlist) {
							str.append("<option value='" + apmt.getMasterid() + "'>" + apmt.getMastername() + "</option>");
						}

						str.append("</select>");
						str.append("</tr>");
						str.append("<tr>");
						str.append("<br><td><a href='#' onclick=openPopup('debitAdditional?clientId="+clientid+"')>Add New Charge</a></td>");
						str.append("</tr><br>");
					}
					int curstatus = accountsDAO.getstdonoffcurstatus(Integer.parseInt(assesmentid),ipdid);
					if(curstatus==1){
						//resultdatetime = resultdatetime + " , " + t1[t1.length-1];
						int j = t1.length-1;
						str.append("<tr>");
						str.append("<td>Charge Date : <input type='text' class='form-control'  name='fdate"+j+"' id='fdate"+j+"' value='"+t1[t1.length-1]+"' autocomplete='off'></td>");
						str.append("</tr>");
					}
					
				}
			}else{
				str.append("<tr>");
				str.append("<td>Charge Date : <input type='text' class='form-control'  name='fdate' id='fdate' value='"+DateTimeUtils.getCommencingDate1(accounts.getCommencing())+"' autocomplete='off'></td>");
				str.append("</tr><br>");
				str.append("<tr>");
				str.append("<td>Charge : <input type='text' class='form-control'  name='newchargename' id='newchargename' value='"+newchargename+"'></td>");
				str.append("</tr><br>");
				str.append("<tr>");
				
				str.append("<br><select id='apmttype' name='apmttype' class='form-control chosen'>");
				str.append("<option value='0'>Replace Existing Charge</option>");

				for (Client apmt : apmtmentlist) {
					str.append("<option value='" + apmt.getMasterid() + "'>" + apmt.getMastername() + "</option>");
				}

				str.append("</select>");
				str.append("</tr>");
				str.append("<tr>");
				str.append("<br><td><a href='#' onclick=openPopup('debitAdditional?clientId="+clientid+"')>Add New Charge</a></td>");
				str.append("</tr><br>");
				/*String curdate = DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone());
    			String tempa[] = curdate.split(" ");
    			
				str.append("<tr>");
				str.append("<td>FromDate : <input type='text' class='form-control'  name='tdate' id='tdate' value='"+tempa[0]+"'></td>");
				str.append("</tr>");*/
			}
			if(loginInfo.getClinicUserid().equals("ngppadole"))
			{
				str.append("@#");
				str.append("1");
			}else{
				str.append("@#");
				str.append("0");
			}
			response.setContentType("text/html");
			response.setHeader("Cache-Control", "no-cache");
			
			response.getWriter().write(""+str.toString()+""); 
		}catch (Exception e) {
		e.printStackTrace();
		}finally{
			
			connection.close();
		}
		
		return null;
	}

	
	public String updcharge() throws Exception{
		
		String ipdid = (String)session.getAttribute("chargeipdid");
		String chargeid = (String)session.getAttribute("chargechargeid");
		String assesmentid = (String)session.getAttribute("chargeassesmentid");
		String stdcharge = (String)session.getAttribute("stdcharge");
		String newchargename = request.getParameter("newchargename");
		String updchargeid=(String)session.getAttribute("updchargeid");
		String masterid=request.getParameter("apmttype");
		String showdate=request.getParameter("sdate");
		String paytype=request.getParameter("paytype");
		String originalApmtnmae="";
		Connection connection = null;
		
		try{
					
					connection  = Connection_provider.getconnection();
					AccountsDAO accountsDAO = new JDBCAccountsDAO(connection);
					AppointmentTypeDAO appointmentTypeDAO = new JDBCAppointmentTypeDAO(connection);
					CompleteAptmDAO completeAptmDAO=new JDBCCompleteAptmDAO(connection);
					BedDao bedDao=new JDBCBedDao(connection);
					Bed bed = bedDao.getEditIpdData(ipdid);
					
					Master master= appointmentTypeDAO.getMasterCharges(chargeid);
					
					//session.setAttribute("chargedtailclientid", bed.getClientid());
					
					Accounts accounts = accountsDAO.getAssesmentDetails(assesmentid);
					
					/*String qty = request.getParameter("qty"+accounts.getId());
					
					int upd = accountsDAO.updateChargeqty(assesmentid,qty);*/
					
					boolean isSTdCharge = accountsDAO.getIfSTdCharge(chargeid);
					
					if(isSTdCharge && stdcharge.equals("1")){
					
					String startEndTime= (String) session.getAttribute("startEndTime"); 
					
					
					if(startEndTime!=null){
						String tempc[] = startEndTime.split("~");
						String t1[] = tempc[0].split(",");
						String t2[] = tempc[1].split(",");
						String resultdatetime = "";
						
						String ondate = "";
						String offdate = "";
						int l = t2.length;
						
						int q = 0;
						for(int i=0;i<l;i++){
							
								String sdate = request.getParameter("fdate"+i)+"";
								String edate = request.getParameter("tdate"+i)+"";
								
								
								
								ondate = ondate + request.getParameter("fdate"+i)+" , ";
							
							
								offdate = offdate + request.getParameter("tdate"+i)+" , ";
								
								String temp[] = request.getParameter("fdate"+i).trim().split(" ");
								String temp1[] = request.getParameter("tdate"+i).trim().split(" ");
							
								 //long diff= DateTimeUtils.getDifferenceOfTwoDateDBFormat(DateTimeUtils.getCommencingDate1(temp[0]), DateTimeUtils.getCommencingDate1(temp1[0]));
								int diff = DateTimeUtils.getDifferanceofDateWithTime(sdate, edate, master.getChargehours());
				    			   q = q + diff;
						}
						String datetime=DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone());
						CompleteAppointment completeAppointment=completeAptmDAO.getInvoiceAsessmentDetails(Integer.parseInt(chargeid));
						int log=accountsDAO.insertchargeDetailsLog(completeAppointment,Integer.toString(q),"Quantity",loginInfo.getUserId(),datetime);
						int upd = accountsDAO.updateChargeqty(assesmentid,Integer.toString(q));
						
						int curstatus = accountsDAO.getstdonoffcurstatus(Integer.parseInt(assesmentid),ipdid);
						if(curstatus==1){
							//resultdatetime = resultdatetime + " , " + t1[t1.length-1];
							int j = t1.length-1;
							ondate = ondate  + request.getParameter("fdate"+j);
						}
						
						if(ondate.length()!=0){
							ondate = ondate.substring(0,ondate.length()-2);
						}
						
						if(offdate.length()!=0){
							offdate = offdate.substring(0,offdate.length()-2);
						}
						
						int onofid = accountsDAO.getStdchargeid(assesmentid,ipdid);
						
						upd = accountsDAO.updateStdChargeDateTime(onofid,ondate,offdate);
						
//						System.out.println(ondate);
					}
					
				}else{
					String commencing = request.getParameter("fdate");
					String commtime=accounts.getTpcommencing().split(" ")[1];
					commencing = DateTimeUtils.getCommencingDate1(commencing);
					
					/*String curdate = DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone());
        			String tempa[] = curdate.split(" ");
        			 long diff= DateTimeUtils.getDifferenceOfTwoDateDBFormat(commencing, tempa[0]);
	         			int qty =(int) diff;
	         			
					int upd = accountsDAO.updateAssessmentCommencing(commencing,assesmentid);*/
					// upd = accountsDAO.updateChargeqty(assesmentid,Integer.toString(qty));
					InvestigationDAO investigationDAO= new JDBCInvestigationDAO(connection);
					String datetime=DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone());
					CompleteAppointment completeAppointment=completeAptmDAO.getInvoiceAsessmentDetails(Integer.parseInt(assesmentid));
					int log=accountsDAO.insertchargeDetailsLog(completeAppointment,commencing,"Assesment Date",loginInfo.getUserId(),datetime);
					int upd = accountsDAO.updateAssessmentCommencing(commencing,assesmentid,commtime,ipdid);
					
					Investigation investigation= new Investigation(); 
					investigation=investigationDAO.getApmtTypeName(assesmentid);
					String datenew= commencing+" 00:00:00";
					if(loginInfo.getIskunal()==1){
						int res=  investigationDAO.updateInvstDate(investigation.getInvreq(), investigation.getId(), datenew);
					}
					
				}
					InventoryProductDAO productDAO=new JDBCInventoryProductDAO(connection);
					if(!accounts.getCharges().equals(newchargename)){
						String datetime=DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone());
						CompleteAppointment completeAppointment=completeAptmDAO.getInvoiceAsessmentDetails(Integer.parseInt(assesmentid));
						int log=accountsDAO.insertchargeDetailsLog(completeAppointment,newchargename,"Charge Name",loginInfo.getUserId(),datetime);
						int res1=productDAO.changeChargesName(updchargeid, newchargename);
						
					
					}
					int res2=productDAO.changeShowingdate(updchargeid, showdate);
					IpdDAO ipdDAO=new JDBCIpdDAO(connection);
					ClientDAO clientDAO=new JDBCClientDAO(connection);
					Client client = clientDAO.getClientDetails(bed.getClientid());
					String tpid = client.getTypeName();
						 
						String temptpid= ipdDAO.getFollowerTp(tpid); 
						if(temptpid!=null){
							
							if(!temptpid.equals("0")){
								 tpid=temptpid;  
							}
						}
							ThirdPartyDAO thirdPartyDAO = new JDBCThirdPartyDAO(connection);
							ThirdParty thirdParty = thirdPartyDAO.getThirdPartyDetails(tpid);			
					if(!masterid.equals("0")){
						Client client2=clientDAO.getAppointmentType(masterid,thirdParty.getChargecolumnname());
						String datetime=DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone());
						CompleteAppointment completeAppointment=completeAptmDAO.getInvoiceAsessmentDetails(Integer.parseInt(assesmentid));
						int log=accountsDAO.insertchargeDetailsLog(completeAppointment,client2.getApmtname(),"Appointment Name",loginInfo.getUserId(),datetime);
						int log1=accountsDAO.insertchargeDetailsLog(completeAppointment,client2.getApmtcharges(),"Appointment Charge",loginInfo.getUserId(),datetime);
						int res=clientDAO.updateAssessment(client2,assesmentid,ipdid);						
					}
			
		}catch (Exception e) {
			e.printStackTrace();
		}finally{
			
			connection.close();
		}
		
		
		return "chargeupdated";
	}
	


	private void createPdf(ArrayList<Accounts> accountList,
			ArrayList<Invoice> invoiceList, ArrayList<Accounts> creditList,
			String balanceTotalx, double debitTotal, String creditTotalx,String clientid,String clientName) throws Exception {
		
		
		StringBuffer str = new StringBuffer();
		Connection connection = Connection_provider.getconnection();
		AccountsDAO accountsDAO = new JDBCAccountsDAO(connection);
		CompleteAptmDAO completeAptmDAO = new JDBCCompleteAptmDAO(connection);
		
		ArrayList<Accounts> clientDetailsList = accountsDAO.getClientDetails(clientid);
		for(Accounts a1:clientDetailsList){
			accountsForm.setInitial(a1.getInitial());
			accountsForm.setFirstname(a1.getFirstname());
			accountsForm.setLastname(a1.getLastname());
			accountsForm.setAddress(a1.getAddress());
			accountsForm.setCity(a1.getCity());
			accountsForm.setClientId(clientid);
			accountsForm.setPostcode(a1.getPostcode());
			accountsForm.setEmail(a1.getEmail());
			accountsForm.setMobno(a1.getMobno());
		}
		
		if(!clientid.equals("")){
			CompleteAppointment completeAppointment = completeAptmDAO.getInsuranceCompanyName(clientid);
			accountsForm.setInsuranceCompany(completeAppointment.getInsuranceCompanyName());
			accountsForm.setThirdPartyAddress(completeAppointment.getInsuranceCompanyAddress());
			accountsForm.setThirdPartyContacttno(completeAppointment.getThirdPartyContacttno());
			accountsForm.setThirdPartyPostcode(completeAppointment.getThirdPartyPostcode());
			accountsForm.setThirdPartyemail(completeAppointment.getThirdPartyemail());

		}
		
		str.append("<table cellpadding='0' cellspacing='0' style='width: 80%;'>");
		str.append("<tr>");
			str.append("<td align='center' style='font-weight:bold;font-size16px;'>Account</td>");
		str.append("</tr>");
		str.append("</table>");
		str.append("<br><br>");
		
		str.append("<table cellpadding='0' cellspacing='0' style='width: 80%;font-size: 10px;'>");
		
		str.append("<tr>"); 
		str.append("<td width='50%'>");
			str.append("<b>Client Details : </b><br>");
			str.append("Account No : 0000" + clientid + "<br>");
			str.append("Name : " + clientName + "<br>");
			str.append("Address : " + accountsForm.getAddress() + "<br>");
			str.append("Postcode : " + accountsForm.getPostcode() + "<br>");
			str.append("Contact No : " + accountsForm.getMobno() + "<br>");
			str.append("Email : " + accountsForm.getEmail() + "<br>");
		str.append("</td>");
		str.append("<td width='50%'>");
			str.append("<b>Third Party Details : </b><br>");
			str.append("Name : " + accountsForm.getInsuranceCompany() + "<br>");
			str.append("Address : " + accountsForm.getThirdPartyAddress() + "<br>");
			str.append("Postcode : " + accountsForm.getThirdPartyPostcode() + "<br>");
			str.append("Contact No : " + accountsForm.getThirdPartyContacttno() + "<br>");
			str.append("Email : " + accountsForm.getThirdPartyemail() + "<br>");
		str.append("</td>");
		str.append("</tr>");
		str.append("</table>");
		
		
		str.append("<br><br>");
		
		str.append("<table cellpadding='0' cellspacing='0' style='width: 100%;font-size: 10px;'>");
		str.append("<thead><tr>");
		str.append("<th>Date</th>");
		str.append("<th>Transaction</th>");
		str.append("<th>Payee</th>");
		str.append("<th>Location</th>");
		str.append("<th>Debit (Charge)</th>");
		str.append("<th>Credit (Payment)</th>");
		str.append("<th>Balance</th>");
		str.append("</tr>");
		str.append("</thead>");
		
		str.append("<tbody>");
		
		str.append("<tr><td colspan='7'><hr></td></tr>");
		
		if(accountList.size()>0){
			int c = 1;
			for(Accounts accounts : accountList){
				str.append("<tr>");
				str.append("<td>"+accounts.getCommencing()+"</td>");
				if(accounts.getChargeType().equals("DNA")){
					str.append("<td style='color:red'>"+accounts.getChargeType()+"(0000"+accounts.getInvoiceid()+")</td>");
				}else{
					str.append("<td>"+accounts.getChargeType()+"(0000"+accounts.getInvoiceid()+")</td>");
				}
				
				str.append("<td>"+accounts.getWhoPay()+" ("+accounts.getClientName()+")</td>");
				str.append("<td>"+accounts.getLocation()+"</td>");
				str.append("<td>"+Constants.getCurrency(loginInfo) + accounts.getTotalAmountx() +"</td>");
				str.append("<td>"+Constants.getCurrency(loginInfo) + accounts.getPayAmountx() +"</td>");
				str.append("<td>"+Constants.getCurrency(loginInfo) + accounts.getDebitAmountx() +"</td>");
				
				str.append("</tr>");
				
				if(accounts.getTotalChargesCount()==c){
					for(Invoice invoice : accounts.getInvoiceList()){
						str.append("<tr>");
						str.append("<td>"+invoice.getDate()+"</td>");
						str.append("<td>Invoice(0000"+invoice.getId()+")</td>");
						if(invoice.getPayby().equals("Client")){
							str.append("<td>Self ("+invoice.getClientName()+")</td>");
						}else{
							str.append("<td>"+invoice.getPayby()+" ("+invoice.getClientName()+")</td>");
						}
						
						str.append("<td>"+invoice.getLocation()+"</td>");
						str.append("<td>"+Constants.getCurrency(loginInfo) + invoice.getDebitAmountx() +"</td>");
						str.append("<td>"+Constants.getCurrency(loginInfo) + invoice.getCreditCharge() +"</td>");
						str.append("<td>"+Constants.getCurrency(loginInfo) + invoice.getBalancex() +"</td>");
						str.append("</tr>");

					}
				}
				c++;
				
			}
			
		}else{
			
			if(invoiceList.size()>0){
				for(Invoice invoice : invoiceList){
					str.append("<tr>");
					str.append("<td>"+invoice.getDate()+"</td>");
					str.append("<td>Invoice(0000"+invoice.getId()+")</td>");
					if(invoice.getPayby().equals("Client")){
						str.append("<td>Self ("+invoice.getClientName()+")</td>");
					}else{
						str.append("<td>"+invoice.getPayby()+" ("+invoice.getClientName()+")</td>");
						
					}
					
					str.append("<td>"+invoice.getLocation()+"</td>");
					str.append("<td>"+Constants.getCurrency(loginInfo) + invoice.getDebitAmountx() +"</td>");
					str.append("<td>"+Constants.getCurrency(loginInfo) + invoice.getCreditCharge() +"</td>");
					str.append("<td>"+Constants.getCurrency(loginInfo) + invoice.getBalancex() +"</td>");
					str.append("</tr>");
				}
			}
		}
		
	
		if(creditList.size()>0){
			for(Accounts accounts : creditList){
				str.append("<tr>");
				str.append("<td>"+accounts.getCommencing()+"</td>");
				str.append("<td>"+Constants.CD_CHARGE+"(0000"+accounts.getId()+")</td>");
				if(accounts.getPayby().equals("Client")){
					str.append("<td>Self ("+accounts.getClientName()+")</td>");
				}else{
					str.append("<td>"+accounts.getPayby()+" ("+accounts.getClientName()+")</td>");
				}
				
				str.append("<td>N/A</td>");
				str.append("<td>"+Constants.getCurrency(loginInfo) + accounts.getDebitTotalx() +"</td>");
				str.append("<td>"+Constants.getCurrency(loginInfo) + accounts.getCharges() +"</td>");
				str.append("<td>"+Constants.getCurrency(loginInfo) + accounts.getBalancex() +"</td>");
				str.append("</tr>");
			}
		}
		
		str.append("<tr><td colspan='7'><hr></td></tr>");
		
		str.append("<tr>");
		str.append("<td colspan='3'></td>");
		str.append("<td style='text-align: center;'><b>Total</b></td>");
		str.append("<td>"+Constants.getCurrency(loginInfo) + debitTotal +"</td>");
		str.append("<td>"+Constants.getCurrency(loginInfo) + creditTotalx +"</td>");
		str.append("<td>"+Constants.getCurrency(loginInfo) + balanceTotalx +"</td>");
		
		str.append("</tr>");
		
		str.append("</tbody>");
		str.append("</table>");
		
		
		String filePath = request.getRealPath("//invoice//");
		//String filename = "Invoice_"+clientid+"_"+invoiceid+".pdf";
		String filename = clientid+"_"+clientName+".pdf";
		accountsForm.setFilename(filename);
		htmlToPdfFile(str.toString(), filePath, filename);
		session.setAttribute("pdfFileName", filePath+"/"+filename);
		session.setAttribute("chargesInvoiceid", 0);
		//pdfFileName
//		System.out.println("pdf created");
		
	}


	public String move() throws Exception{
		if(!verifyLogin(request)){
			return "login";
		}
		//redirect to execute
		
		String payby = (String) session.getAttribute("payby");
		String clientId = (String) session.getAttribute("clientId");
		String client = (String) session.getAttribute("client");
		String transactionType = (String) session.getAttribute("transactionType");
		String location = (String) session.getAttribute("location");
		String thirdParty = (String) session.getAttribute("thirdParty");
		//pagination = (Pagination) session.getAttribute("pagination");
		session.setAttribute("pagination", pagination);
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
		
		if(!clientId.equals("")){
			Connection connection  = null;
			try{
				
				connection  = Connection_provider.getconnection();
				AccountsDAO accountsDAO = new JDBCAccountsDAO(connection);
				StatementDAO statementDAO = new JDBCStatementDAO(connection);
			
			
			int totalCount = statementDAO.getTotalAccountCount(clientId,payby,transactionType,location,thirdParty);
			pagination.setPreperties(totalCount);
			ArrayList<Accounts>accountList = new ArrayList<Accounts>();
			if(transactionType.equals(Constants.PENDING_PEYMENT)){
				accountList = statementDAO.getAccountPendingList(clientId,payby,pagination,transactionType,location,thirdParty);
				totalCount = accountList.size();
			}
			else if(transactionType.equals(Constants.PAID)){
			accountList = statementDAO.getAccountPaidList(clientId,payby,pagination,transactionType,location,thirdParty);
			totalCount = accountList.size();
			}
			else{
				//accountList = statementDAO.getAccountList(clientId,payby,pagination,transactionType,location,thirdParty);
				
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
			accountsForm.setInsuranceCompany(completeAppointment.getInsuranceCompanyName());
			accountsForm.setThirdPartyAddress(completeAppointment.getInsuranceCompanyAddress());
			accountsForm.setThirdPartyContacttno(completeAppointment.getThirdPartyContacttno());
			accountsForm.setThirdPartyPostcode(completeAppointment.getThirdPartyPostcode());
			accountsForm.setThirdPartyemail(completeAppointment.getThirdPartyemail());

			for(Accounts accounts :accountList ){
				accountsForm.setBalanceTotal(accounts.getBalanceTotal());
				accountsForm.setDebitTotal(accounts.getDebitTotal());
				accountsForm.setCreditTotal(accounts.getCreditTotal());
				
				//Decimal Amount
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
					accountsForm.setPostcode(a1.getPostcode());
					accountsForm.setEmail(a1.getEmail());
					accountsForm.setMobno(a1.getMobno());
				}
				
				
				}
			
			}
			catch(Exception e){
				
			}finally{
				
				connection.close();
			}
		}
		return SUCCESS;
	}
	
	
	public String switchautocharge() throws Exception{
		
		Connection connection=null;
		try {
			connection=Connection_provider.getconnection();
			AccountsDAO accountsDAO= new JDBCAccountsDAO(connection);
			String clientid= request.getParameter("clientid");
			//String ipdid= accountsDAO.getIpdidofClient(clientid);
			String flag=request.getParameter("flag");
			//int res=accountsDAO.updateAutochargeFlag(ipdid,flag);
			int res=accountsDAO.updateAutochargeFlagClient(clientid,flag);
			
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
	

	public AccountsForm getModel() {
		// TODO Auto-generated method stub
		return accountsForm;
	}
	
	public String chargeinvoice(){
		return "chargeinvoice";
	}


	public void prepare() throws Exception {
		Connection connection  = null;
		
		try{
			
			connection  = Connection_provider.getconnection();
			AccountsDAO accountsDAO = new JDBCAccountsDAO(connection);
			IpdDAO ipdDAO=new JDBCIpdDAO(connection);
			ArrayList<Accounts>locationList = accountsDAO.getLocationList(loginInfo.getClinicid());
			ArrayList<Accounts>thirdPartyList = accountsDAO.getThirdPartyList(loginInfo.getId());
			accountsForm.setLocationList(locationList);
			accountsForm.setThirdPartyList(thirdPartyList);
			//  05 oct 2017 for set dr list in charge sharing popup
			UserProfileDAO profileDAO = new JDBCUserProfileDAO(connection);
			ArrayList<Master> doctorlist = profileDAO.getDoctorList();
			accountsForm.setDoctorlist(doctorlist);
			ArrayList<Master>packageList = ipdDAO.getPackageList();
			accountsForm.setPackageList(packageList);
			
		
			accountsForm.setHourList(PopulateList.hourList());
			accountsForm.setMinuteList(PopulateList.getMinuteList());
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

public String savesharechargetodr() throws Exception{
	Connection connection  = null;
	try{
		connection  = Connection_provider.getconnection();
		AccountsDAO accountsDAO = new JDBCAccountsDAO(connection);
		String clientId = request.getParameter("clientId");
		String doctorId = request.getParameter("doctorId");
		String disctype = request.getParameter("disctype");
		String share_calamount = request.getParameter("share_calamount");
		String sharing_remark = request.getParameter("sharing_remark");
		String share_invoiceid = request.getParameter("share_invoiceid");
		String share_chargeid = request.getParameter("share_chargeid");
		String all_or_indivisual = request.getParameter("all_or_indi");
		String share_chargename = request.getParameter("share_chargename");
		String userid = loginInfo.getUserId();
		String chargetotal = request.getParameter("chargetotal");
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Calendar calendar = Calendar.getInstance();
		String currentdate = dateFormat.format(calendar.getTime());
		Accounts accounts = new Accounts();
		accounts.setClientid(Integer.parseInt(clientId));
		accounts.setPractitionerId(Integer.parseInt(doctorId));
		accounts.setCommission_type(disctype);
		accounts.setAmountx(share_calamount);
		accounts.setRemark(sharing_remark);
		accounts.setInvoiceid(Integer.parseInt(share_invoiceid));
		accounts.setChargeid(share_chargeid);
		accounts.setCharges(share_chargename);
		accounts.setAll_or_indivisual(all_or_indivisual);
		accounts.setUserid(userid);
		accounts.setDate(currentdate);
		accounts.setTotalAmountx(chargetotal);
		int res = accountsDAO.saveShareChargeToDr(accounts);
		response.setContentType("text/html");
		response.setHeader("Cache-Control", "no-cache");
		response.getWriter().write(""); 

	}catch (Exception e) {
		e.printStackTrace();
	}finally{
		
		connection.close();
	}
	
	return null;
}

public String editsharechargetodr() throws Exception{
	Connection connection  = null;
	try{
		connection  = Connection_provider.getconnection();
		AccountsDAO accountsDAO = new JDBCAccountsDAO(connection);
		String doctorId = request.getParameter("doctorId");
		String disctype = request.getParameter("disctype");
		String share_calamount = request.getParameter("share_calamount");
		String sharing_remark = request.getParameter("sharing_remark");
		String id =request.getParameter("id");
		String userid = loginInfo.getUserId();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Calendar calendar = Calendar.getInstance();
		String currentdate = dateFormat.format(calendar.getTime());
		Accounts accounts = new Accounts();
		accounts.setPractitionerId(Integer.parseInt(doctorId));
		accounts.setCommission_type(disctype);
		accounts.setAmountx(share_calamount);
		accounts.setRemark(sharing_remark);
		accounts.setUserid(userid);
		accounts.setDate(currentdate);
		accounts.setId(Integer.parseInt(id));
		int res = accountsDAO.editShareChargeToDr(accounts);
		response.setContentType("text/html");
		response.setHeader("Cache-Control", "no-cache");
		response.getWriter().write(""); 

	}catch (Exception e) {
		e.printStackTrace();
	}finally{
		
		connection.close();
	}
	
	return null;
}

public String getsharechargetodr() throws Exception{
	Connection connection  = null;
	try{
		connection  = Connection_provider.getconnection();
		//AccountsDAO accountsDAO = new JDBCAccountsDAO(connection);
		StatementDAO statementDAO = new JDBCStatementDAO(connection);
		String indiorall = request.getParameter("indiorall");
		String invoiceid = request.getParameter("invoiceid");
		String chargeid = request.getParameter("chargeid");
		StringBuilder builder =  new StringBuilder();
		if(indiorall.equals("0")){
			//Accounts accounts = accountsDAO.
			Accounts accounts = statementDAO.getInvoiceDetails(invoiceid);
			builder.append(""+invoiceid+"");
			builder.append("~");
			builder.append(""+chargeid+"");
			builder.append("~");
			builder.append(""+indiorall+"");
			builder.append("~");
			builder.append("All Charges");
			builder.append("~");
			builder.append(""+accounts.getTotalAmountx()+"");
			builder.append("~");
			builder.append(""+accounts.getShared_amount()+"");
			builder.append("~");
			builder.append(""+accounts.getBalance()+"");
			builder.append("~");
			for (Accounts accounts2 : accounts.getSharelist()) {
				String date = accounts2.getDate();
				String[] datetime = date.split(" ");
				String date1 =  DateTimeUtils.getCommencingDate1(datetime[0]) +" "+datetime[1] ;
				builder.append("<tr>");
				builder.append("<td>"+accounts2.getPractitionerName()+"</td>");
				if(accounts2.getCommission_type().equals("Percent")){
					if(accounts2.getCommission()==null){
						builder.append("<td>Rs."+accounts2.getAmountx()+"</td>");
					}else{
						builder.append("<td>Rs."+accounts2.getAmountx()+"("+accounts2.getCommission()+"%)</td>");
					}
				}else{
						builder.append("<td>Rs."+accounts2.getAmountx()+"</td>");
				}
				builder.append("<td>"+accounts2.getUserid()+" / "+date1+"</td>");
				builder.append("</tr>");
  			}
		}else{
			Accounts accounts = statementDAO.getInvoiceChargeDetails(chargeid);
			builder.append(""+invoiceid+"");
			builder.append("~");
			builder.append(""+chargeid+"");
			builder.append("~");
			builder.append(""+indiorall+"");
			builder.append("~");
			builder.append(""+accounts.getAppointmentType()+"");
			builder.append("~");
			builder.append(""+accounts.getTotalAmountx()+"");
			builder.append("~");
			builder.append(""+accounts.getShared_amount()+"");
			builder.append("~");
			builder.append(""+accounts.getBalance()+"");
			builder.append("~");
			for (Accounts accounts2 : accounts.getSharelist()) {
				String date = accounts2.getDate();
				String[] datetime = date.split(" ");
				String date1 =  DateTimeUtils.getCommencingDate1(datetime[0]) +" "+datetime[1] ;
				builder.append("<tr>");
				builder.append("<td>"+accounts2.getPractitionerName()+"</td>");
				if(accounts2.getCommission_type().equals("Percent")){
					if(accounts2.getCommission()==null){
						builder.append("<td>Rs."+accounts2.getAmountx()+"</td>");
					}else{
						builder.append("<td>Rs."+accounts2.getAmountx()+"("+accounts2.getCommission()+"%)</td>");
					}
				}else{
						builder.append("<td>Rs."+accounts2.getAmountx()+"</td>");
				}
				builder.append("<td>"+accounts2.getUserid()+" / "+date1+"</td>");
				builder.append("</tr>");
  			}
		}
		response.setContentType("text/html");
		response.setHeader("Cache-Control", "no-cache");
		response.getWriter().write(""+builder.toString()+""); 

	}catch (Exception e) {
		e.printStackTrace();
	}finally{
		
		connection.close();
	}
	
	return null;
}

public String savesharechargetodrnew() throws Exception{
	Connection connection  = null;
	try{
		connection  = Connection_provider.getconnection();
		AccountsDAO accountsDAO = new JDBCAccountsDAO(connection);
		StatementDAO statementDAO = new JDBCStatementDAO(connection);
		String clientId = request.getParameter("clientId");
		String doctorId = request.getParameter("doctorId");
		String disctype = request.getParameter("disctype");
		String share_calamount = request.getParameter("share_calamount");
		String sharing_remark = request.getParameter("sharing_remark");
		String share_invoiceid = request.getParameter("share_invoiceid");
		String share_chargeid = request.getParameter("share_chargeid");
		String all_or_indivisual = request.getParameter("all_or_indi");
		String share_chargename = request.getParameter("share_chargename");
		String userid = loginInfo.getUserId();
		String chargetotal = request.getParameter("chargetotal");
		String share_balance = request.getParameter("share_balance");
		String newshare_amount = request.getParameter("newshare_amount");
		String inputamount = request.getParameter("inputamount");
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Calendar calendar = Calendar.getInstance();
		String currentdate = dateFormat.format(calendar.getTime());
		Accounts accounts = new Accounts();
		accounts.setClientid(Integer.parseInt(clientId));
		accounts.setPractitionerId(Integer.parseInt(doctorId));
		accounts.setCommission_type(disctype);
		accounts.setAmountx(share_calamount);
		accounts.setRemark(sharing_remark);
		accounts.setInvoiceid(Integer.parseInt(share_invoiceid));
		accounts.setChargeid(share_chargeid);
		if(all_or_indivisual.equals("0")){
			accounts.setCharges("");
		}else{
			accounts.setCharges(share_chargename);
		}
		accounts.setAll_or_indivisual(all_or_indivisual);
		accounts.setUserid(userid);
		accounts.setDate(currentdate);
		accounts.setTotalAmountx(chargetotal);
		accounts.setShared_amount(share_balance);
		accounts.setCommission(inputamount);
		int res = accountsDAO.saveShareChargeToDr(accounts);
		double total_shared = Double.parseDouble(newshare_amount)+Double.parseDouble(share_calamount);
		if(all_or_indivisual.equals("0")){
			int result = statementDAO.updateInvoiceSharedStatus(share_invoiceid,total_shared);
		}else{
			int result = statementDAO.updateChargeSharedStatus(share_invoiceid,share_chargeid,total_shared);
		}
		response.setContentType("text/html");
		response.setHeader("Cache-Control", "no-cache");
		response.getWriter().write(""); 

	}catch (Exception e) {
		e.printStackTrace();
	}finally{
		
		connection.close();
	}
	
	return null;
}

public String updateinvoicecharges() throws Exception{
	Connection connection = null;
	try {
		connection = Connection_provider.getconnection();
		AccountsDAO accountsDAO = new JDBCAccountsDAO(connection);
		String allchargeids = request.getParameter("allchargeids");
		String clientid = request.getParameter("clientId");
		for (String s : allchargeids.split(",")) {
			if (s.equals("0")) {
				continue;
			}
			String sqty = request.getParameter("changeqtxt"+s);
			int upd = accountsDAO.updateChargeqty(s,sqty);
		}
		session.setAttribute("chargedtailclientid",clientid);
	} catch (Exception e) {
		e.printStackTrace();
	}
	return "updatedcharge";
}

public String changechargesname() throws Exception{
	Connection connection=Connection_provider.getconnection();;
	int res1=0;
	try {
		String chargeid=request.getParameter("chargeid");
		String val=request.getParameter("val");
		InventoryProductDAO productDAO=new JDBCInventoryProductDAO(connection);
		CompleteAptmDAO completeAptmDAO=new JDBCCompleteAptmDAO(connection);
		AccountsDAO accountsDAO=new JDBCAccountsDAO(connection);
		String datetime=DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone());
		CompleteAppointment completeAppointment=completeAptmDAO.getInvoiceAsessmentDetails(Integer.parseInt(chargeid));
		int log=accountsDAO.insertchargeDetailsLog(completeAppointment,val,"Charge Name",loginInfo.getUserId(),datetime);
	 res1=productDAO.changeChargesName(chargeid, val);
	 response.setContentType("text/html");
		response.setHeader("Cache-Control", "no-cache");
		response.getWriter().write("1"); 
	} catch (Exception e) {
		e.printStackTrace();
	}finally {
		connection.close();
	}
	
	return null;
	
}

public String changeUnitCost() throws Exception{
	Connection connection=Connection_provider.getconnection();;
	int res1=0;
	try {
		String chargeid=request.getParameter("chargeid");
		String val=request.getParameter("val");
		InventoryProductDAO productDAO=new JDBCInventoryProductDAO(connection);
		CompleteAptmDAO completeAptmDAO=new JDBCCompleteAptmDAO(connection);
		AccountsDAO accountsDAO=new JDBCAccountsDAO(connection);
		String datetime=DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone());
		CompleteAppointment completeAppointment=completeAptmDAO.getInvoiceAsessmentDetails(Integer.parseInt(chargeid));
		int log=accountsDAO.insertchargeDetailsLog(completeAppointment,val,"Unit Cost",loginInfo.getUserId(),datetime);
	 res1=productDAO.changeUnitCost(chargeid, val);
	 response.setContentType("text/html");
		response.setHeader("Cache-Control", "no-cache");
		response.getWriter().write(""); 
	} catch (Exception e) {
		e.printStackTrace();
	}finally {
		connection.close();
	}
	
	return null;
	
}

public String changeAptmTypeCode() throws Exception{
	Connection connection=Connection_provider.getconnection();;
	int res1=0;
	try {
		String chargeid=request.getParameter("chargeid");
		String val=request.getParameter("val");
		InventoryProductDAO productDAO=new JDBCInventoryProductDAO(connection);
		CompleteAptmDAO completeAptmDAO=new JDBCCompleteAptmDAO(connection);
		AccountsDAO accountsDAO=new JDBCAccountsDAO(connection);
		String datetime=DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone());
		CompleteAppointment completeAppointment=completeAptmDAO.getInvoiceAsessmentDetails(Integer.parseInt(chargeid));
		int log=accountsDAO.insertchargeDetailsLog(completeAppointment,val,"Code",loginInfo.getUserId(),datetime);
	 res1=productDAO.changeApmtcode(chargeid, val);
	 response.setContentType("text/html");
		response.setHeader("Cache-Control", "no-cache");
		response.getWriter().write(""); 
	} catch (Exception e) {
		e.printStackTrace();
	}finally {
		connection.close();
	}
	
	return null;
	
}
public String changeQty() throws Exception{
	Connection connection=Connection_provider.getconnection();;
	int res1=0;
	try {
		String chargeid=request.getParameter("chargeid");
		String val=request.getParameter("val");
		InventoryProductDAO productDAO=new JDBCInventoryProductDAO(connection);
		CompleteAptmDAO completeAptmDAO=new JDBCCompleteAptmDAO(connection);
		AccountsDAO accountsDAO=new JDBCAccountsDAO(connection);
		String datetime=DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone());
		CompleteAppointment completeAppointment=completeAptmDAO.getInvoiceAsessmentDetails(Integer.parseInt(chargeid));
		int log=accountsDAO.insertchargeDetailsLog(completeAppointment,val,"Quantity",loginInfo.getUserId(),datetime);
	 res1=productDAO.changeApmtqty(chargeid, val);
	 response.setContentType("text/html");
		response.setHeader("Cache-Control", "no-cache");
		response.getWriter().write(""); 
	} catch (Exception e) {
		e.printStackTrace();
	}finally {
		connection.close();
	}
	
	return null;
	
}
public String refreshPkgData() throws Exception{
	try {
		

	String ipdid=request.getParameter("ipdid");
	String clientid=request.getParameter("clientid");
	Connection connection=Connection_provider.getconnection();;
	CompleteAptmDAO completeAptmDAO=new JDBCCompleteAptmDAO(connection);
	AccountsDAO accountsDAO=new JDBCAccountsDAO(connection);
	if(loginInfo.getIskunal()==1 || loginInfo.isPackage_access()){
	int pkgstr = accountsDAO.getTpkgId(clientid,Integer.parseInt(ipdid),"apm_invoice_assesments");
	if(pkgstr>0){
		CompleteAppointment dates = completeAptmDAO.getPackageDtaes(String.valueOf(pkgstr));
		completeAptmDAO.swapPkgChargeList(dates.getPkgfdate(), dates.getPkgtdate(), ipdid,pkgstr,clientid);
		completeAptmDAO.deletefromapmminvoiceassessment(dates.getPkgfdate(), dates.getPkgtdate(), ipdid,clientid);
	}
	}
	response.setContentType("text/html");
	response.setHeader("Cache-Control", "no-cache");
	response.getWriter().write(""); 
	} catch (Exception e) {
		e.printStackTrace();
	}
	return null;
	
}

public String confirmdelete() throws Exception{
	Connection connection=Connection_provider.getconnection();;
	int res1=0;
	try {
		String chargeid=request.getParameter("chargeid");
		String ipdid=request.getParameter("ipdid");
		InventoryProductDAO productDAO=new JDBCInventoryProductDAO(connection);
		CompleteAptmDAO completeAptmDAO=new JDBCCompleteAptmDAO(connection);
		AccountsDAO accountsDAO=new JDBCAccountsDAO(connection);
		String datetime=DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone());
		CompleteAppointment completeAppointment=completeAptmDAO.getInvoiceAsessmentDetails(Integer.parseInt(chargeid));
		int res=0;
		boolean flag=completeAptmDAO.checkInvoicedFromAssesments(chargeid);
		if(flag){
		int log=accountsDAO.insertchargeDetailsLog(completeAppointment,"0","Delete",loginInfo.getUserId(),datetime);
		res1=productDAO.deletecharges(chargeid, ipdid);
		res=1;
		}
		
	 response.setContentType("text/html");
		response.setHeader("Cache-Control", "no-cache");
		response.getWriter().write(""+res+""); 
	} catch (Exception e) {
		e.printStackTrace();
	}finally {
		connection.close();
	}
	
	return null;
	
}

public String updateautocharge(){
	Connection connection = null;
	try {
		connection = Connection_provider.getconnection();
		IpdDAO ipdDAO = new JDBCIpdDAO(connection);
		BedDao bedDao = new JDBCBedDao(connection);
		CompleteAptmDAO completeAptmDAO = new JDBCCompleteAptmDAO(connection);
		AppointmentTypeDAO appointmentTypeDAO = new JDBCAppointmentTypeDAO(connection);
		ClientDAO clientDAO = new JDBCClientDAO(connection);
		String clientId = request.getParameter("clientId");
		boolean isAutochargeOn= completeAptmDAO.isAutoChargedOn(clientId);
		int ipdidadmiss=ipdDAO.getLastIpdId(clientId);
		 
		if(isAutochargeOn){
			if(ipdidadmiss!=0){
				Bed bed = bedDao.getEditIpdData(String.valueOf(ipdidadmiss));
				String admissiondate=bed.getAdmissiondate();
				if(admissiondate!=null){
					String admissiondatenew = admissiondate.split(" ")[0];
					Discharge discharge = ipdDAO.getDischargeDetails(bed.getTreatmentepisodeid());
					if(discharge.getDis_discharge_status()==1){
						if(discharge.getDis_discharge_date()!=null){
							String dischargedate[] = discharge.getDis_discharge_date().split(" "); 
							ArrayList<String> arrayList = completeAptmDAO.getMissingAutoChargeDate(ipdidadmiss,clientId,admissiondatenew,dischargedate[0]);
							for (String string : arrayList) {
								int wardid = completeAptmDAO.getIPDWardIDFromLogWithHighPrice(ipdidadmiss,string,admissiondatenew);
								if(wardid!=0){
									bed.setWardid(""+wardid);
									ArrayList<CompleteAppointment> invoicelist=completeAptmDAO.getListofActiveCharges(ipdidadmiss,clientId,loginInfo);
									if(invoicelist.size()!=0){
										boolean flag = completeAptmDAO.checkAutoChargeAppliedStatus(bed.getWardid(),bed.getBedid(),ipdidadmiss,string);
					 			  		if(!flag){
					 			  			String cutofftime = bedDao.getHospitalCutoffTime(loginInfo.getClinicid());
					 			            if(!cutofftime.equals("0")){
					 			            	string = string + " " + cutofftime + ":20" ;
					 			            }else{
					 			            	string = string + " " +  DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone()).split(" ")[1];
					 			            }
											for(CompleteAppointment appointment:invoicelist) {
											 	String temp[] = string.split(" ");
						 						appointment.setAppointmentid("0");
						 						appointment.setChargeType("Charge");
								    			appointment.setLocation("1");
								    			appointment.setIpd("1");
								    			appointment.setInvoiceDate(string);
						 						int invoiceid=completeAptmDAO.saveAmpmInvoice(appointment,loginInfo.getId(),loginInfo.getUserId());
						 						
						 						 int log1 = bedDao.saveBedChangeLog(bed,string,ipdidadmiss,temp[0],string,1);
						 						
						 						Client client = clientDAO.getClientDetails(bed.getClientid());
						 						String fullname=client.getTitle()+" "+client.getFirstName()+" "+client.getLastName();
						 					    appointment.setUser(fullname);
						 						ArrayList<Master> chargeList=appointmentTypeDAO.getBedShiftingStandardChargeList(bed.getWardid(), client.getTypeName(), client.getWhopay(),loginInfo);
						 						for(Master m1:chargeList){
						 							appointment.setCommencing(temp[0]); 
						 					    	appointment.setApmtType(m1.getName());
						 					    	appointment.setCharges(m1.getCharge());
						 					    	appointment.setAdditionalcharge_id(String.valueOf(m1.getId()));
						 					    	appointment.setMasterchargetype("Bed Charge");
						 					    	if(loginInfo.getIskunal()==1){
						 					    		appointment.setMasterchargetype(m1.getMasterchargetype());
						 					    	}
					 					    	    appointment.setWardid(bed.getWardid());
					 					    	    appointment.setBedid(bed.getBedid());
					 					    	    appointment.setIpdid(bed.getId());
					 					    	    appointment.setLogid(Integer.toString(log1));
					 					    	    appointment.setQuantity(1);
					 					    	    int res=completeAptmDAO.saveInvoiceAssesment(appointment, invoiceid);
						 						}  
										 	}
					 			  		}
									}
								}
							}
						}
					}else{
						discharge.setDis_discharge_date(DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone()));
						String dischargedate[] = discharge.getDis_discharge_date().split(" "); 
						ArrayList<String> arrayList = completeAptmDAO.getMissingAutoChargeDate(ipdidadmiss,clientId,admissiondatenew,dischargedate[0]);
						for (String string : arrayList) {
							int wardid = completeAptmDAO.getIPDWardIDFromLogWithHighPrice(ipdidadmiss,string,admissiondatenew);
							if(wardid!=0){
								bed.setWardid(""+wardid);
								ArrayList<CompleteAppointment> invoicelist=completeAptmDAO.getListofActiveCharges(ipdidadmiss,clientId,loginInfo);
								if(invoicelist.size()!=0){
									boolean flag = completeAptmDAO.checkAutoChargeAppliedStatus(bed.getWardid(),bed.getBedid(),ipdidadmiss,string);
				 			  		if(!flag){
				 			  			String cutofftime = bedDao.getHospitalCutoffTime(loginInfo.getClinicid());
				 			            String checkingdate = string;
				 			  			if(!cutofftime.equals("0")){
				 			            	string = string + " " + cutofftime + ":20" ;
				 			            }else{
				 			            	string = string + " " +  DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone()).split(" ")[1];
				 			            }
				 			  			boolean yesflag=false;
				 			            if(checkingdate.equals(dischargedate[0])){
				 			            	String currentdate = DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone());
				 			            	if(!cutofftime.equals("0")){
				 			            		int differ = currentdate.compareTo(string);
				 			            		if(differ<0){
				 			            			yesflag= true;
				 			            		}
				 			            	}
				 			            	
				 			            }
				 			            if(!yesflag){
				 			            	for(CompleteAppointment appointment:invoicelist) {
											 	String temp[] = string.split(" ");
						 						appointment.setAppointmentid("0");
						 						appointment.setChargeType("Charge");
								    			appointment.setLocation("1");
								    			appointment.setIpd("1");
								    			appointment.setInvoiceDate(string);
						 						int invoiceid=completeAptmDAO.saveAmpmInvoice(appointment,loginInfo.getId(),loginInfo.getUserId());
						 						
						 						 int log1 = bedDao.saveBedChangeLog(bed,string,ipdidadmiss,temp[0],string,1);
						 						
						 						Client client = clientDAO.getClientDetails(bed.getClientid());
						 						String fullname=client.getTitle()+" "+client.getFirstName()+" "+client.getLastName();
						 					    appointment.setUser(fullname);
						 						ArrayList<Master> chargeList=appointmentTypeDAO.getBedShiftingStandardChargeList(bed.getWardid(), client.getTypeName(), client.getWhopay(),loginInfo);
						 						for(Master m1:chargeList){
						 							appointment.setCommencing(temp[0]); 
						 					    	appointment.setApmtType(m1.getName());
						 					    	appointment.setCharges(m1.getCharge());
						 					    	appointment.setAdditionalcharge_id(String.valueOf(m1.getId()));
						 					    	appointment.setMasterchargetype("Bed Charge");
						 					    	if(loginInfo.getIskunal()==1){
						 					    		appointment.setMasterchargetype(m1.getMasterchargetype());
						 					    	}
					 					    	    appointment.setWardid(bed.getWardid());
					 					    	    appointment.setBedid(bed.getBedid());
					 					    	    appointment.setIpdid(bed.getId());
					 					    	    appointment.setLogid(Integer.toString(log1));
					 					    	    appointment.setQuantity(1);
					 					    	    int res=completeAptmDAO.saveInvoiceAssesment(appointment, invoiceid);
						 						}  
										 	}
				 			            }
									}
								}
							}
						}
					}
				}
			}
		}
		session.setAttribute("autoupdate_clientId", clientId);
	} catch (Exception e) {
		
	}
	return "updateautocharge";
}
public String indvdiscount(){

	
	if(!verifyLogin(request)){
		return "login";
	}
	
	Connection  connection = null;
	
	try{
		connection = Connection_provider.getconnection();
		AccountsDAO accountsDAO = new JDBCAccountsDAO(connection);
		AdditionalDAO additionalDAO = new JDBCAdditionalDAO(connection);
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
		Clinic ccbg =  locationAdressList.get(0);
		session.setAttribute("balgopaladdress", ccbg.getAddress());
		session.setAttribute("balgopalcname", ccbg.getClinicName());
		accountsForm.setClinicLogo(clinic.getUserImageFileName());
		String showcharge=accountsForm.getShowcharges();
		
		//client details
		
		String clientId = accountsForm.getClientId();
		
		if(clientId.equals("")){
			clientId = (String)session.getAttribute("chargedtailclientid");
		}
	session.setAttribute("chargedtailclientid", clientId);
		
		Client client = accountsDAO.getClientData(clientId);
		accountsForm.setClient(client.getTitle() + " " + client.getFirstName() + " " + client.getLastName());
		accountsForm.setAddress(client.getAddress());
		accountsForm.setClienttown(client.getTown());
		accountsForm.setClientpostcode(client.getPostCode());
		
		String age = DateTimeUtils.getAge1(client.getDob());
		accountsForm.setAgegender(age + "/" + client.getGender());
		
		accountsForm.setDob(client.getDob());
		accountsForm.setClientId(clientId);
		accountsForm.setEmail(client.getEmail());
		accountsForm.setMobno(client.getMobNo());
		accountsForm.setPolicyNo(client.getPolicyNo());
		accountsForm.setAbrivationid(client.getAbrivationid());
		ClientDAO clientDAO=new JDBCClientDAO(connection);
		IpdDAO ipdDAO=new JDBCIpdDAO(connection);
		
		String ipdid=ipdDAO.getIpdId(clientId);
		Client client2 = accountsDAO.getTPClientData(clientId);
		String tpname=accountsDAO.gettpnamebyid(client2.getThirdPartyType());
		String thirdParty = accountsDAO.getTpDetailsbyclientid(Integer.parseInt(clientId));
		String company_name=accountsDAO.getcompany_name(Integer.parseInt(thirdParty));
		ThirdParty thirdPartyobj = new ThirdParty();
		thirdPartyobj = accountsDAO.getTpDetails(Integer.parseInt(thirdParty));
		accountsForm.setPayeename(thirdPartyobj.getCompanyName());
		accountsForm.setThirdParty(company_name);
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
		if(tpname.equals("CGHS")){
			
				accountsForm.setStatusfortp(true);
				accountsForm.setCompanyname("CGHS");
			}
			else if(tpname.equals("WCL")){
				
					accountsForm.setStatusfortp(true);
					accountsForm.setCompanyname("WCL");
				}else if(tpname.equals("INSURANCE COMPANY")){
					accountsForm.setStatusfortp(true);
					accountsForm.setCompanyname(tpname);
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
		if(loginInfo.getIpd_abbr_access()==1){
			 String newipdabbr=ipdDAO.getIpdAbrivationIds(Integer.parseInt(ipdid));
			   accountsForm.setNewipdabbr(newipdabbr);
			   accountsForm.setIpdseqno(ipdid);
		}else{
		accountsForm.setIpdseqno(ipdid);
		 accountsForm.setNewipdabbr(ipdid);
		}
		//setting master chartype data
		String curdate = DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone());
		String temp[] = curdate.split(" ");
		curdate = DateTimeUtils.getCommencingDate1(temp[0]);
		ArrayList<Master> masterAssessmentList=new ArrayList<Master>();
		if (showcharge==null) {
			showcharge="0";
		}
		if(showcharge.equals("1")){
			 masterAssessmentList = accountsDAO.getIndDisMasterAssessmentList(clientId,curdate,loginInfo,true,accountsForm.getSelectipdid());
		}else{
		 masterAssessmentList = accountsDAO.getIndDisMasterAssessmentList(clientId,curdate,loginInfo,false,accountsForm.getSelectipdid());
		}
		accountsForm.setMasterAssessmentList(masterAssessmentList);
		accountsForm.setIpdidnew(ipdid);
		String Drname=accountsDAO.getDrNamebyClientId(clientId);
		if(Drname.equals("")){
			Drname=accountsDAO.getOpdPracName(clientId);
		}
		accountsForm.setIpdconsultant(Drname);
		String textdiaggnosis=accountsDAO.gettextdiagnosis(Integer.parseInt(ipdid));
		if(textdiaggnosis==null){
			textdiaggnosis="";
		}
		if(textdiaggnosis.equals("<br>")){
			textdiaggnosis="";
		}
		if(!textdiaggnosis.equals("")){
			accountsForm.setFinalDiagnosis(textdiaggnosis);
			
		}else{
		String fdid = accountsDAO.getFdID(Integer.parseInt(ipdid));
		String finalDiagnosis = accountsDAO.getIpdFinalDiagnosis(fdid);
		accountsForm.setFinalDiagnosis(finalDiagnosis);
	}
		session.setAttribute("finaldiagnosis", accountsForm.getFinalDiagnosis());
		if(masterAssessmentList.size()!=0){
			
			Master master = masterAssessmentList.get(masterAssessmentList.size()-1);
			accountsForm.setDchtotal(master.getDchargetotal());
			
		}
		if(accountsForm.getSelectipdid()==null){
			accountsForm.setSelectipdid("");
		}
		accountsForm.setSelectipdid(accountsForm.getSelectipdid());
		
		//credit list
		//String chargesids=master.getChargeid();
		ArrayList<Accounts> creditList = additionalDAO.getCreditAccountList(clientId,"");
		accountsForm.setCreditList(creditList);

		double credit = 0;
		
		Client client3=new Client();
		client3=accountsDAO.getDisandadmiss(clientId);
		String temp1[] = client3.getAdmissionwithtime().split(" ");
		String adate = DateTimeUtils.getCommencingDate1(temp1[0]);
		String time[] = temp1[1].split(":");
		String hh = time[0];
		String mm = time[1];
		
		adate = adate + " " + hh + ":" + mm;
		accountsForm.setAdmissionDate(adate);
		if(loginInfo.getIskunal()==1){
			int hourOfDay=Integer.parseInt(hh);
			   int minute=Integer.parseInt(mm);
			   String apmpm =  ((hourOfDay > 12) ? hourOfDay % 12 : hourOfDay) + ":" + (minute < 10 ? ("0" + minute) : minute) + " " + ((hourOfDay >= 12) ? "PM" : "AM");
			   adate= DateTimeUtils.getCommencingDate1(temp1[0])+" "+apmpm;
			   accountsForm.setAdmissionDate(adate);
		}else{
			accountsForm.setAdmissionDate("");
		}
		
		if(!client3.getDischargewithtime().equals("")){
			 String dtemp[] = client3.getDischargewithtime().split(" ");
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
				   adate= DateTimeUtils.getCommencingDate1(dtemp[0])+" "+apmpm;
				   accountsForm.setDischargeDate(adate);
			}else{
				accountsForm.setDischargeDate("");
			}
		}else{
			accountsForm.setDischargeDate("");
		}
		
		//showing discharge status   23/02/2019
		Bed dischargedata = clientDAO.getdischargedata(ipdid);
		String dischargestatus = dischargedata.getDischargeStatus();
		DischargeStatusDAO statusDAO=new JDBCDischargeStatus(connection);
		String dischargehead=statusDAO.getDischargeStatusById(Integer.parseInt(dischargestatus));
		if(dischargehead==null){
			dischargehead="";
		}
		accountsForm.setDischargestatus(dischargestatus);
		accountsForm.setDischargehead(dischargehead);
		

		
	}catch (Exception e) {
		e.printStackTrace();
	}
	
	return "indvdiscount";

}
public String indvdiscreq1(){
Connection  connection = null;
	
	try{
		connection = Connection_provider.getconnection();
		AccountsDAO accountsDAO=new JDBCAccountsDAO(connection);
		String disctype=accountsForm.getDisctype();
		String invdisc=accountsForm.getShowcharges();
		String clientid=accountsForm.getClientId();
		String discounttxt="";
		String multidisc="";
		session.setAttribute("chargedtailclientid", accountsForm.getClientId());
		if(disctype==null){
			disctype="0";
		}
		if(disctype.equals("")){
			disctype="0";
		}
		ArrayList<String> listWithDuplicateValues = new ArrayList<String>();
		int parentid=accountsDAO.insertDiscountRequest(loginInfo,disctype,clientid);
		for (Accounts accounts : accountsForm.getCollectionsal()) {
			if(invdisc.equals("1")){
				if(accounts.getDiscount()!=0){
					ArrayList<Accounts> list=accountsDAO.getDiscountedCharges(accounts);
				for (Accounts accounts2 : list) {
					
						int res=accountsDAO.insertChildDiscData(accounts2,disctype,parentid,accounts.getDiscount());
//						if(!discounttxt.equals("0")){
//							if(!discounttxt.contains(String.valueOf(accounts.getDiscount()))){
//							discounttxt=discounttxt+","+String.valueOf(accounts.getDiscount());
//							}else{
//								discounttxt=String.valueOf(accounts.getDiscount());
//							}
//						}else{
//							discounttxt=String.valueOf(accounts.getDiscount());
//						}
							listWithDuplicateValues.add(String.valueOf(accounts.getDiscount()));
							//  14-11-2019 Not working in older version
							/*ArrayList uniqueList = (ArrayList) listWithDuplicateValues.stream().distinct().collect(Collectors.toList());
							 String join = Arrays.toString(uniqueList.toArray());
							 discounttxt = join.replace("[", "").replace("]", "");*/
//							for (Object object : uniqueList) {
//							if(uniqueList.size()>0){
//								if(discounttxt.equals("0")){
//									discounttxt=object.toString();
//								}else{
//									discounttxt=discounttxt+","+object.toString();
//								}
//									
//								}else{
//								if(discounttxt.equals("0")){
//									discounttxt=object.toString();
//								}
//							}
//				}
				}
			}
		}else{
			if(accounts.getDiscount()!=0){
				Accounts accounts2=accountsDAO.getsinglediscountcharge(accounts);
				int res=accountsDAO.insertChildDiscData(accounts2,disctype,parentid,accounts.getDiscount());
//				if(!discounttxt.equals("0")){
//					if(!discounttxt.equals(String.valueOf(accounts.getDiscount()))){
//					discounttxt=discounttxt+","+String.valueOf(accounts.getDiscount());
//					}else{
//						discounttxt=String.valueOf(accounts.getDiscount());
//					}
//				}else{
//					discounttxt=String.valueOf(accounts.getDiscount());
//				}
				listWithDuplicateValues.add(String.valueOf(accounts.getDiscount()));
				//  14-11-2019 Not working in older version
				/*ArrayList uniqueList = (ArrayList) listWithDuplicateValues.stream().distinct().collect(Collectors.toList());
				 String join = Arrays.toString(uniqueList.toArray());
				 discounttxt = join.replace("[", "").replace("]", "");*/
			}
		}
			
		}
		//  14-11-2019 above code Not working in older version. so helped   for this code
		ArrayList<String> arrayList2 = new ArrayList<String>();
		int i=0;
		for (String discountvalue : listWithDuplicateValues) {
				if(i==0){
					discounttxt =discountvalue;
					arrayList2.add(discountvalue);
				}
				if(i!=0){
					boolean flag = false;
					for (String discountvalue1 : arrayList2) {
						if(discountvalue1.equals(discountvalue)){
							flag= true;
							break;
						}
					}
					if(!flag){
						discounttxt =discounttxt+","+discountvalue;
						arrayList2.add(discountvalue);
					}
				}
			i++;
		}
		Accounts accounts=accountsDAO.gettotoalinvdisc(parentid);
		if(disctype.equals("0")){
			String str[]=discounttxt.split(",");
			if(str.length>1){
				for (String string : str) {
					String joindisc=accountsDAO.getinddiscounttotal(parentid,string);
					if(multidisc.equals("")){
						multidisc=joindisc;
					}else{
						multidisc=multidisc+","+joindisc;
					}
					
				}
				
			}else{
				String joindisc=accountsDAO.getinddiscounttotal(parentid,discounttxt);
				if(multidisc.equals("")){
					multidisc=joindisc;
				}else{
					multidisc=multidisc+","+joindisc;
				}
			}
			accounts.setInddiscounttotal(multidisc);
		}else{
			accounts.setInddiscounttotal(discounttxt);
		}
		int res=accountsDAO.updateDiscountRequest(accounts,parentid);
	} catch (Exception e) {
		e.printStackTrace();
	}
	return "indvdiscreq";
	
}
public String approveinvdiscout() {
Connection  connection = null;
	
	try{
		connection = Connection_provider.getconnection();
		String parentid=request.getParameter("parentid");
		AccountsDAO accountsDAO=new JDBCAccountsDAO(connection);
		ArrayList<Accounts> list=accountsDAO.getdiscountcharges(parentid);
		StringBuffer buffer=new StringBuffer();
		for (Accounts accounts : list) {
			buffer.append("<tr>");
			buffer.append("<td>"+accounts.getAptmname()+" </td>");
			buffer.append("<td>"+accounts.getMasterchargetype()+" </td>");
			
			if(accounts.getDisctype().equals("0")){
				buffer.append("<td>"+accounts.getTotalDisc()+"("+accounts.getDiscount()+" %) </td>");
			}else{
				buffer.append("<td>"+accounts.getTotalDisc()+"("+accounts.getDiscount()+""+Constants.getCurrency(loginInfo)+" ) </td>");
			}
			
			buffer.append("<td>"+accounts.getTotalAmount()+" </td>");
			buffer.append("<td>"+accounts.getQuantity()+" </td>");
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
public String approveinddiscount() throws Exception{
	Connection  connection = null;
	try {
		
		connection = Connection_provider.getconnection();
		AccountsDAO accountsDAO=new JDBCAccountsDAO(connection);
		AppointmentDAO appointmentDAO=new JDBCAppointmentDAO(connection);
		String parentid=request.getParameter("id");
		String approve_notes = request.getParameter("approve_notes");
		String userid = loginInfo.getUserId();
		String datetime = DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone());
		int res1 = appointmentDAO.updateInvoiceDiscout(parentid,userid,datetime,approve_notes);
		ArrayList<CompleteAppointment> list=appointmentDAO.getChargesid(Integer.parseInt(parentid));
		for (CompleteAppointment completeAppointment : list) {
			int res= accountsDAO.updateAssesmentData(completeAppointment);
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
		// TODO: handle exception
	}
	 return "inddiscountdash";
	
}


	public String changepaytype() throws Exception{
		Connection connection=Connection_provider.getconnection();;
		int res1=0;
		try {
			String chargeid=request.getParameter("chargeid");
			String val=request.getParameter("val");
			String clientid=request.getParameter("clientid");
			InventoryProductDAO productDAO=new JDBCInventoryProductDAO(connection);
			CompleteAptmDAO completeAptmDAO=new JDBCCompleteAptmDAO(connection);
			IpdDAO ipdDAO=new JDBCIpdDAO(connection);
			ClientDAO clientDAO=new JDBCClientDAO(connection);
			Client client = clientDAO.getClientDetails(clientid);
			String tpid = client.getTypeName();
				 if(tpid==null){
					 tpid="0";
				 }
				 if(tpid.equals("")){
					 tpid="0";
				 }
			AccountsDAO accountsDAO=new JDBCAccountsDAO(connection);
			String datetime=DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone());
			CompleteAppointment completeAppointment=completeAptmDAO.getInvoiceAsessmentDetails(Integer.parseInt(chargeid));
			int log=accountsDAO.insertchargeDetailsLog(completeAppointment,val,"Payee Change",loginInfo.getUserId(),datetime);
		 res1=productDAO.changePayee(chargeid, val,tpid);
		 response.setContentType("text/html");
			response.setHeader("Cache-Control", "no-cache");
			response.getWriter().write(""); 
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			connection.close();
		}
		
		return null;
		
	}
	
	public String allinvoicelist() {
		Connection connection = null;
		try {
			String fromDate = accountsForm.getFromDate();
			String toDate = accountsForm.getToDate();	
			
			if (fromDate == null) {
				DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
				Calendar cal = Calendar.getInstance();
				fromDate = dateFormat.format(cal.getTime());
				fromDate = DateTimeUtils.getCommencingDate1(fromDate);
			} else {

				if (fromDate.equals("")) {
					DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
					Calendar cal = Calendar.getInstance();
					fromDate = dateFormat.format(cal.getTime());
					fromDate = DateTimeUtils.getCommencingDate1(fromDate);
				} else {
					fromDate = DateTimeUtils.getCommencingDate1(fromDate);
				}
			}

			if (toDate == null) {
				DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
				Calendar cal = Calendar.getInstance();
				toDate = dateFormat.format(cal.getTime());
				toDate = DateTimeUtils.getCommencingDate1(toDate);
			} else {
				if (toDate.equals("")) {
					DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
					Calendar cal = Calendar.getInstance();
					toDate = dateFormat.format(cal.getTime());
					toDate = DateTimeUtils.getCommencingDate1(toDate);
				} else {
					toDate = DateTimeUtils.getCommencingDate1(toDate);
				}
			}
			connection = Connection_provider.getconnection();
			AccountsDAO accountsDAO=new JDBCAccountsDAO(connection);
			int count=accountsDAO.getCashInvoiceListcount(fromDate,toDate);
			pagination.setPreperties(count); 
			ArrayList<Accounts> list=accountsDAO.getCashInvoiceList(fromDate,toDate,pagination);
			pagination.setTotal_records(list.size());
			accountsForm.setDeleteinvoicelist(list);
			accountsForm.setPagerecords(String.valueOf(pagination.getTotal_records()));
			accountsForm.setTotalRecords(count);
			accountsForm.setFromDate( DateTimeUtils.getCommencingDate1(fromDate));
			accountsForm.setToDate( DateTimeUtils.getCommencingDate1(toDate));
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return "deleteinvoice";
	}
	
	
	public String saverender() throws Exception{
		
		try {
			
			Connection connection=Connection_provider.getconnection();
			
			
		    String chargename=request.getParameter("chargename");
			String doctorid=request.getParameter("doctor");
			String charge=request.getParameter("charge");
			String chargeid=request.getParameter("chargeid");
			String userid=loginInfo.getUserId();
			String date_time = DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone());

			Accounts accounts = new Accounts();
			
			accounts.setChargeid(chargeid);
			accounts.setChargename(chargename);
			accounts.setDocid(doctorid);
			accounts.setUserid(userid);
			accounts.setDate_time(date_time);
			accounts.setCharges(charge);
			
			AccountsDAO accountsDAO=new JDBCAccountsDAO(connection);
			Accounts accounts1=accountsDAO.getchargeDetails(chargeid);
			
			int share_amount=accountsDAO.getdoctorData(doctorid);
			
			accounts.setClientid(accounts1.getClientid());
			accounts.setChargedisc(accounts1.getChargedisc());
			accounts.setInvoicee(accounts1.getInvoicee());
			accounts.setShared_amount(Integer.toString(share_amount));
			
			int result=accountsDAO.saveRenderdata(accounts);
			
			response.setContentType("text/html");
			response.setHeader("Cache-Control", "no-cache");
			response.getWriter().write(""+result+"");
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		   return null;
	}
	
	
public String saverenderpayment() throws Exception{
		
		try {
			
			Connection connection=Connection_provider.getconnection();
			AccountsDAO accountsDAO=new JDBCAccountsDAO(connection);
			
		    String paymenttype=request.getParameter("paymenttype");
			String chrge_amount=request.getParameter("charge_amount");
			String chargeid=request.getParameter("chargeid");
			String balcnceamt=request.getParameter("balnceamt");
			String date_time = DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone());
			String userid=loginInfo.getUserId();

			Accounts accounts = new Accounts();
			
			accounts.setChargeid(chargeid);
			accounts.setCharges(chrge_amount);
			accounts.setChargeid(chargeid);
			accounts.setDate_time(date_time);
			accounts.setPaymentmode(paymenttype);
	
			//saveto parent table
			int genearkey=accountsDAO.savetoParentrenderpayment(accounts,userid);
			
			String temp[]=chargeid.split(",");
			System.out.println(temp.length);
			String temp1[]=balcnceamt.split(",");
			int result=0;
	
			for(int i=0;i<temp.length;i++){
				
				chargeid=temp[i];
				balcnceamt=temp1[i];
				
				if(temp1.length==1){
					balcnceamt=chrge_amount;
				}
				
			   result=accountsDAO.saveRenderPaymentdata(chargeid,balcnceamt,date_time,paymenttype,genearkey);
				
			}
			
			response.setContentType("text/html");
			response.setHeader("Cache-Control", "no-cache");
			response.getWriter().write(""+result+"");
			
		} catch (Exception e) {
		   e.printStackTrace();
		}
		
		   return null;
	}	
	
public String openDailynotes() throws Exception{
	Connection connection=null;
	String isnote=request.getParameter("isnote");
	String example=request.getParameter("example");
	String ipdid=request.getParameter("ipdid");
	String payby=request.getParameter("payby");
	String clientId=request.getParameter("clientId");
	try {
	     
		connection=Connection_provider.getconnection();
		IpdDAO ipdDAO = new JDBCIpdDAO(connection);
		MasterDAO masterDAO = new JDBCMasterDAO(connection);
		accountsForm.setIpdid(Integer.parseInt(ipdid));
		accountsForm.setClientId(clientId);
		accountsForm.setPayby(payby);
		//Bed bed=ipdDAO.getPatientdetails(ipdid);
		ArrayList<Master> discharge_default_list = masterDAO.getIpdTemplateListNamesList("Notes");
		accountsForm.setDischarge_default_list(discharge_default_list);
		ArrayList<Accounts> dailynoteslist=ipdDAO.getdailynoteslist(ipdid,clientId,"","");
		accountsForm.setDailynoteslist(dailynoteslist);
	} catch (Exception e) {
		e.printStackTrace();
	}finally {
		connection.close();
	}
	if(isnote.equals("1")) {
		return "dailynewnotes";
	}else {
	return "notes";
	}
}


public String savedailynotes()throws Exception{
	Connection connection=null;
	String example=request.getParameter("example");
	String ipdid=request.getParameter("ipdid");
	String payby="Self";
	String clientId=request.getParameter("clientId");
	String actiontype=request.getParameter("actiontype");
	try {
		connection=Connection_provider.getconnection();
		IpdDAO ipdDAO = new JDBCIpdDAO(connection);
		Bed bed=new Bed();
		bed.setNotes(example);
		bed.setIpdid(ipdid);
		bed.setWhopay(payby);
		bed.setClientid(clientId);
		bed.setUserid(loginInfo.getUserId());
		bed.setDatetime(DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone()));
		bed.setAppointmentid("0");
		
		String notetempname=accountsForm.getDisdefaulttempname();
		
		if(notetempname!=null){
			if(!notetempname.equals("")){
				int res = ipdDAO.saveIPDTemplate(notetempname,"19","1",
						new String(DateTimeUtils.isNull(bed.getNotes()).getBytes("iso-8859-1"), "UTF-8")
						);
			}
		}
		
		if(actiontype.equals("0")) {
		int result = ipdDAO.insertConsultationForm(bed);
		}else {
			String id=request.getParameter("id");
			int update=ipdDAO.updateConsultationForm(bed,Integer.parseInt(id));
		}
	} catch (Exception e) {
		e.printStackTrace();
	}finally {
		connection.close();
	}
	return  openDailynotes();
}

public String editdailynotes()throws Exception{
	Connection connection=null;
	try {
		String id=request.getParameter("id");
		connection=Connection_provider.getconnection();
		IpdDAO ipdDAO = new JDBCIpdDAO(connection);
		Bed bed=ipdDAO.getDailyipdnote(id);
		accountsForm.setExample(bed.getNotes());
		accountsForm.setId(id);
		accountsForm.setIpdid(Integer.parseInt(bed.getIpdid()));
		accountsForm.setClientId(bed.getClientid());
	} catch (Exception e) {
		e.printStackTrace();
	}
			
	return "editdailynote";
}

}