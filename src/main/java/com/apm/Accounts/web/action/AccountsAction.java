package com.apm.Accounts.web.action;

import java.sql.Connection;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.apm.Accounts.eu.bi.AccountsDAO;
import com.apm.Accounts.eu.blogic.jdbc.JDBCAccountsDAO;
import com.apm.Accounts.eu.entity.Accounts;
import com.apm.Accounts.web.form.AccountsForm;
import com.apm.DiaryManagement.eu.bi.ClientDAO;
import com.apm.DiaryManagement.eu.bi.CompleteAptmDAO;
import com.apm.DiaryManagement.eu.blogic.jdbc.JDBCClientDAO;
import com.apm.DiaryManagement.eu.blogic.jdbc.JDBCCompleteAptmDAO;
import com.apm.DiaryManagement.eu.entity.Client;
import com.apm.DiaryManagement.eu.entity.CompleteAppointment;
import com.apm.DiaryManagement.eu.entity.Priscription;
import com.apm.DiaryManagement.web.action.SmsService;
import com.apm.Emr.eu.bi.EmrDAO;
import com.apm.Emr.eu.blogic.jdbc.JDBCEmrDAO;
import com.apm.Ipd.eu.bi.IpdDAO;
import com.apm.Ipd.eu.blogic.jdbc.JDBCIpdDAO;
import com.apm.Ipd.eu.entity.Ipd;
import com.apm.Log.eu.entity.EmailLetterLog;
import com.apm.Log.eu.entity.LogDetail;
import com.apm.Master.eu.entity.Master;
import com.apm.Pharmacy.eu.bi.PharmacyDAO;
import com.apm.Pharmacy.eu.blogic.jdbc.JDBCPharmacyDAO;
import com.apm.Registration.eu.bi.ClinicDAO;
import com.apm.Registration.eu.bi.UserProfileDAO;
import com.apm.Registration.eu.blogic.jdbc.JDBCClinicDAO;
import com.apm.Registration.eu.blogic.jdbc.JDBCUserProfileDAO;
import com.apm.Registration.eu.entity.Clinic;
import com.apm.Registration.eu.entity.Location;
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

public class AccountsAction extends BaseAction implements  Preparable, ModelDriven<AccountsForm>{
	
	HttpServletRequest request = (HttpServletRequest) ActionContext.getContext().get(ServletActionContext.HTTP_REQUEST);
	HttpServletResponse response = (HttpServletResponse)ActionContext.getContext().get(ServletActionContext.HTTP_RESPONSE);
	HttpSession session = request.getSession(true);
	LoginInfo loginInfo = LoginHelper.getLoginInfo(request);
	
	AccountsForm accountsForm = new AccountsForm();
	private Pagination pagination = new Pagination(500, 1);
	
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
			
			connection = Connection_provider.getconnection();
			AccountsDAO accountsDAO = new JDBCAccountsDAO(connection);
			ArrayList<Accounts>accountList = new ArrayList<Accounts>();
			ArrayList<Accounts>allChargeList  = new ArrayList<Accounts>();
			ArrayList<Accounts> allBillList=new ArrayList<Accounts>();
			ClientDAO clientDAO = new JDBCClientDAO(connection);
			IpdDAO ipdDAO = new JDBCIpdDAO(connection);
		//	EmrDAO emrDAO = new JDBCEmrDAO(connection); 
			
		/*	String regLocationID = emrDAO.getRegisteredLocationId();
			accountsForm.setLocation(regLocationID);*/
			
			String payby = accountsForm.getPayby();
			String clientId = accountsForm.getClientId();
			String client = accountsForm.getClient();
			String transactionType = accountsForm.getTransactionType();
			String location = accountsForm.getLocation();
			if(!accountsForm.getAppointmentid().equals("")){
				location = accountsDAO.getChargeLocation(accountsForm.getAppointmentid());
			}
			String thirdParty = accountsForm.getThirdParty();
			String raiseChargeType = accountsForm.getRaiseChargeType();
			String autoselect=accountsForm.getAutoselect();
			if(autoselect==null){
				autoselect="0";
			}
			accountsForm.setAutoselect(autoselect);
			//set selected clientid from session
			if(clientId!=null){
				
				if(!clientId.equals("")){
					
					clientId = request.getParameter("clientId");
					accountsForm.setClientId(clientId);
					Client clients = clientDAO.getSelectedSessionClientDetails(clientId);
					accountsForm.setClient(clients.getClientName());
					client = clients.getClientName();
				}
				else {
					clientId=null;
				}
			}
			
			
			if(clientId!=null){
				
				if(clientId.equals("")){
					 clientId=null;
				}
			}
			
			
			if(clientId==null){
			if(session.getAttribute("sessionselectedclientid")!=null){
				clientId = (String)session.getAttribute("sessionselectedclientid");
				accountsForm.setClientId(clientId);
				Client clients = clientDAO.getSelectedSessionClientDetails(clientId);
				accountsForm.setClient(clients.getClientName());
				client = clients.getClientName();
			 }
			}
		   
			if(clientId==null){
				if(session.getAttribute("clientId")!=null){
					clientId = (String)session.getAttribute("clientId");
					accountsForm.setClientId(clientId);
					Client clients = clientDAO.getSelectedSessionClientDetails(clientId);
					accountsForm.setClient(clients.getClientName());
					client = clients.getClientName();
				 }
				}
			
			Client clients =new Client();
			if(clientId!=null){
				
				if(!clientId.equals("")){
					accountsForm.setClientId(clientId);
				 clients = clientDAO.getSelectedSessionClientDetails(clientId);
					accountsForm.setClient(clients.getClientName());
					client = clients.getClientName();
				}
				else {
					clientId=null;
				}
			}
			
			
			if(clientId==null){
				
				clientId = request.getParameter("clientId");
				accountsForm.setClientId(clientId);
				 clients = clientDAO.getSelectedSessionClientDetails(clientId);
				accountsForm.setClient(clients.getClientName());
				client = clients.getClientName();
				
			}
			
			
			/*if(payby==null){
				payby = request.getParameter("payby");
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
			*/
			if(clients!=null) {
				payby=clients.getWhopay();
			}
			if(payby == null || payby == ""){
				payby = (String)session.getAttribute("payby");
			}
			if(location==null || location == ""){
				location = (String)session.getAttribute("location");
			}
			
			/*if(payby == null || payby == ""){
				payby = (String)session.getAttribute("payby");
			}
			if(clientId==null || clientId == ""){
				clientId = (String)session.getAttribute("clientId");
			}
			if(client == null){
				client = (String)session.getAttribute("clientname");
			}
			if(location==null || location == ""){
				location = (String)session.getAttribute("location");
			}*/
			
			/*if(raiseChargeType == null){
				raiseChargeType = (String)session.getAttribute("raiseChargeType");
			}
			
			String defaultVal = "All";
			if(transactionType == null){
				transactionType = defaultVal;
			}
			if(thirdParty == null){
				thirdParty = defaultVal;
			}
			if(location == null){
				location = defaultVal;
			}
			if(raiseChargeType == null){
				raiseChargeType = defaultVal;
			}*/
			
			payby=DateTimeUtils.isNull(payby);
			
			session.setAttribute("clientId", clientId);
			session.setAttribute("client", client);
			session.setAttribute("payby", payby);
			session.setAttribute("transactionType", transactionType);
			session.setAttribute("location", location);
			session.setAttribute("thirdParty", thirdParty);
			session.setAttribute("raiseChargeType", raiseChargeType);
			session.setAttribute("pagination", pagination);
			
			String defaultVal = "All";
			
			if(transactionType.equals(defaultVal)){
				transactionType = "";
			}
			if(location.equals(defaultVal)){
				location = "";
			}
			if(raiseChargeType.equals(defaultVal)){
				raiseChargeType = "";
			}
			if(thirdParty.equals(defaultVal)){
				thirdParty = "";
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
			    calendar.add(Calendar.DATE, -30);
			    SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd");
			    Date date=calendar.getTime();
			    fromDate=format.format(date);
			    String fod=DateTimeUtils.getInvoiceCommencingDate(fromDate);
			    accountsForm.setFromDate(fod); 
			    
			}
			else {
				accountsForm.setFromDate(fromDate); 
				fromDate=DateTimeUtils.getInvoiceCommencingDate(fromDate);
			}
			if(toDate==null){
				Calendar calendar=Calendar.getInstance();
				SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd");
			    Date date=calendar.getTime();
			    toDate=format.format(date);
			    String tod=DateTimeUtils.getInvoiceCommencingDate(toDate);
			    accountsForm.setToDate(tod);
			}else {
				accountsForm.setToDate(toDate);
				toDate=DateTimeUtils.getInvoiceCommencingDate(toDate);
			}
			
			ArrayList<Ipd> ipdseqlist=ipdDAO.getAllIpdList(clientId);
			accountsForm.setIpdseqlist(ipdseqlist);
			
			//new ipdid for filters
			String newipdid= accountsForm.getIpdidnew();
			if(newipdid==null){
				newipdid="";
			}
			
			session.setAttribute("fromDate", fromDate);
			session.setAttribute("toDate", toDate);
			
			allBillList=accountsDAO.getAllMedicineBill(clientId);
	    	accountsForm.setAllBillList(allBillList);
			String accountlistid="0";
			String chargelistid="0";
			
    	if(!clientId.equals("")){
	    		String addOnTableName="";
				boolean dateWithinRange1 = DateTimeUtils.validateDateBetweenRange(fromDate);
				boolean dateWithinRange2 = DateTimeUtils.validateDateBetweenRange(toDate);
				if(dateWithinRange1 && dateWithinRange2){
					addOnTableName="_view";
				}
    			
				int totalCount = accountsDAO.getTotalAccountCount(clientId,payby,transactionType,location,thirdParty,raiseChargeType,fromDate,toDate,newipdid,addOnTableName);
				pagination.setPreperties(totalCount);
				
				
				/*if(transactionType.equals(Constants.PENDING_PEYMENT)){
					accountList = accountsDAO.getAccountPendingList(clientId,payby,pagination,transactionType,location,thirdParty);
					totalCount = accountList.size();
				}
				else if(transactionType.equals(Constants.PAID)){
				accountList = accountsDAO.getAccountPaidList(clientId,payby,pagination,transactionType,location,thirdParty);
				totalCount = accountList.size();
				}
				else{
					accountList = accountsDAO.getAccountList(clientId,payby,pagination,transactionType,location,thirdParty);
					
				}*/
				
				accountList = accountsDAO.getAccountList(clientId,payby,pagination,transactionType,location,thirdParty,raiseChargeType,fromDate,toDate,newipdid,addOnTableName);
				
				pagination.setPage_records(accountList.size());
				accountsForm.setTotalRecords(totalCount);
				accountsForm.setPagerecords(Integer.toString(pagination.getPage_records()));
				accountsForm.setClient(client);
				accountsForm.setAccountList(accountList);
				accountsForm.setClientId1(clientId);
				accountsForm.setPayby1(payby);
				accountsForm.setPayby(payby);
				accountsForm.setRaiseChargeType(raiseChargeType);
				accountsForm.setClient1(client);
				accountsForm.setLocation(location);
				accountsForm.setSelectedLocation(location);
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
				
					accountlistid = accountlistid +","+ accounts.getInvoiceid();
				
				
				}
			}
    	
    	
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
	
		int countLocation = accountsDAO.getTotalLocation();
		if(countLocation==1){
			accountsForm.setLocation("1");
		}
		
			
	
		//select all code
		boolean selectAll = accountsForm.isHdnSelectAll();
		session.setAttribute("selectAll", selectAll);
		
		if(selectAll == true){
			//ArrayList<Accounts>allChargeList = accountsDAO.getAllChargeList(accountsForm.getPayby(),accountsForm.getClientId());
			
			if(transactionType.equals(Constants.PENDING_PEYMENT)){
				allChargeList = accountsDAO.getChargeAccountPendingList(clientId,payby,pagination,transactionType,location,thirdParty,raiseChargeType,fromDate,toDate,newipdid);
				
			}
			else if(transactionType.equals(Constants.PAID)){
				allChargeList = accountsDAO.getChargeAccountPaidList(clientId,payby,pagination,transactionType,location,thirdParty,raiseChargeType,fromDate,toDate,newipdid);
			
			}
			else{
				allChargeList = accountsDAO.getChargeAccountList(clientId,payby,pagination,transactionType,location,thirdParty,raiseChargeType,fromDate,toDate,newipdid);
				
			}
			
			int delete = accountsDAO.deleteChargeAccounts(loginInfo.getLoginsessionid());
			
			for(Accounts accounts : allChargeList){
				int result = accountsDAO.saveTempChargeAccounts(accounts.getInvoiceid(),loginInfo.getLoginsessionid());
				chargelistid = chargelistid +","+accounts.getInvoiceid();
			}
			session.setAttribute("allChargeList", allChargeList);
		}else{
			int delete = accountsDAO.deleteChargeAccounts(loginInfo.getLoginsessionid());
		}
		
		
		
		if(location.equals("")){
			accountsForm.setLocation("All");
			accountsForm.setSelectedLocation("All");
		}
		if(payby.equals("")){
			accountsForm.setPayby("All");
			accountsForm.setSelectedPayBy("All");
		}
			
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		finally{
			connection.close();
		}
		
		return SUCCESS;
	}
	
	
   public String addmedicinebill() throws Exception {
		
		Connection connection=null;
		try {
			connection=Connection_provider.getconnection();
			PharmacyDAO pharmacyDAO=new JDBCPharmacyDAO(connection);
			AccountsDAO accountsDAO=new JDBCAccountsDAO(connection);
			CompleteAptmDAO completeAptmDAO = new JDBCCompleteAptmDAO(connection);
			
			ClientDAO clientDAO=new JDBCClientDAO(connection);
			int billno=accountsForm.getBillno();
			String clientid=accountsForm.getClientId();
			String payby=accountsForm.getPayby();
			
			Client client=clientDAO.getClientDetails(clientid);
			String invoiceDate=DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone()).split(" ")[0];
			String fullname=client.getTitle()+" "+client.getFirstName()+" "+client.getLastName();
			CompleteAppointment completeAppointment=pharmacyDAO.getBillDetails(billno);
			completeAppointment.setInvoiceDate(invoiceDate);
			completeAppointment.setClient(fullname);
			int invoiceid=accountsDAO.saveInvoiceMedicine(completeAppointment,billno);
			
			completeAppointment.setMasterchargetype("MEDICINES");
			ArrayList<Priscription> chargesList=pharmacyDAO.getMedicineChargesList(billno,loginInfo);
			
			for(Priscription priscription:chargesList){
				priscription.setDate(invoiceDate);
				
				/* ps.setInt(1, invoiceid);
				   ps.setString(2,priscription.getClientname());
				   ps.setString(3, priscription.getMdicinenametxt());
				   ps.setString(4, priscription());
				   ps.setString(5, priscription.getPrectionerid());
				   ps.setString(6, priscription.getFullname());
				   ps.setString(7, priscription.getClientId());
				   ps.setString(8, priscription.getDate());
				   ps.setString(9, priscription.getWhopay());
				   ps.setString(10, "1");
				   ps.setInt(11,priscription.getSaleqty());
				   ps.setString(12, "Medicine Charge");
				   ps.setString(13, priscription.getTpid());
				   ps.setString(14, condition);*/
				completeAppointment.setApmtType(priscription.getMdicinenametxt());
				completeAppointment.setCharges(priscription.getMrp());
				completeAppointment.setQuantity(priscription.getSaleqty());
				completeAppointment.setPractitionerId(priscription.getPractitionerid());
				completeAppointment.setClientId(clientid);
				completeAppointment.setCommencing(priscription.getDate());
				//completeAppointment.setUnitcharge(""+priscription.getSaleqty()+"");
				completeAppointment.setLogid(loginInfo.getUserId());
				
				//int result=accountsDAO.saveInvoiceCharges(priscription,invoiceid);
				
				int result = completeAptmDAO.saveInvoiceAssesment(completeAppointment, invoiceid);
				
			}
			
		} catch (Exception e) {

			e.printStackTrace();
		}
		finally{
			
			connection.close();
		}
		
		return "save";
	}
	
	
	
	
	
	public String input() throws Exception {
		if(!verifyLogin(request)){
			return "login";
		}
		
		
		return INPUT;
	}
	
	public void setFormData() throws Exception{
		
		
		//redirect to execute
				Connection connection = null;
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
				String fromDate=(String)session.getAttribute("fromDate");
				String toDate=(String)session.getAttribute("toDate");
				
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
					
					//select all code
					boolean selectAll = (Boolean) session.getAttribute("selectAll");
					accountsForm.setHdnSelectAll(selectAll);
					ArrayList<Accounts>allChargeList = new ArrayList<Accounts>();
					
					allChargeList = accountsDAO.getTempChagesInvoiceList(loginInfo.getLoginsessionid());
					
					session.setAttribute("allChargeList", allChargeList);
					
					
				}
				}catch(Exception e){
					
				}finally{
					
					connection.close();
				}
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
		String raiseChargeType = accountsForm.getRaiseChargeType();
		String fromDate=(String)session.getAttribute("fromDate");
		String todDate=(String)session.getAttribute("toDate");

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
			
			String addOnTableName="";
			/*boolean dateWithinRange1 = DateTimeUtils.validateDateBetweenRange(fromDate);
			boolean dateWithinRange2 = DateTimeUtils.validateDateBetweenRange(toDate);
			if(dateWithinRange1 && dateWithinRange2){
				addOnTableName="_view";
			}*/
			
			int totalCount = accountsDAO.getTotalAccountCount(clientId,payby,transactionType,location,thirdParty,raiseChargeType,fromDate,todDate,"",addOnTableName);
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
				accountList = accountsDAO.getAccountList(clientId,payby,pagination,transactionType,location,thirdParty,raiseChargeType,fromDate,todDate,"",addOnTableName);
				
			}			pagination.setPage_records(accountList.size());
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
			
			//select all code
			boolean selectAll = (Boolean) session.getAttribute("selectAll");
			accountsForm.setHdnSelectAll(selectAll);
			ArrayList<Accounts>allChargeList = new ArrayList<Accounts>();
			
			allChargeList = accountsDAO.getTempChagesInvoiceList(loginInfo.getLoginsessionid());
			
			session.setAttribute("allChargeList", allChargeList);
			
			}catch(Exception e){
				
			}finally{
				
				connection.close();
			}
		}
		return SUCCESS;
	}
	public String payment() throws SQLException{
		if(!verifyLogin(request)){
			return "login";
		}
		Connection connection  = null;
		try{
			
			connection  = Connection_provider.getconnection();
			AccountsDAO accountsDAO = new JDBCAccountsDAO(connection);
			
			String payby = request.getParameter("payby");
			String client = request.getParameter("client");
			String numberOfChages = request.getParameter("numberOfChages");
			String creditCharge = request.getParameter("creditCharge");
			String debitAmt = request.getParameter("debitamt");
			String creditAmt = request.getParameter("creditamt");
			
			int invoiceid = Integer.parseInt(request.getParameter("invoiceid"));
			int clientid = Integer.parseInt(request.getParameter("clientid"));
			
			
			accountsForm.setClient(client);
			accountsForm.setClientId(Integer.toString(clientid));
			ArrayList<Accounts>assesmentList = accountsDAO.getAssesmentList(invoiceid,Integer.parseInt(payby));
			accountsForm.setAssesmentList(assesmentList);
			accountsForm.setCreditCharge(creditCharge);
			accountsForm.setInvoiceid(invoiceid);
			accountsForm.setPayby(payby);
			
			accountsForm.setCreditAmt(creditAmt);
			accountsForm.setDebitAmt(debitAmt);
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		finally{
			connection.close();
		}
		
		return "payment";
	}
	
	public String raiseinvoice() throws SQLException{
		if(!verifyLogin(request)){
			return "login";
		}
		Connection connection  = null;
		try{
			
			connection  = Connection_provider.getconnection();
			AccountsDAO accountsDAO = new JDBCAccountsDAO(connection);
			
			String payby = request.getParameter("payby");
			String client = request.getParameter("client");
			String numberOfChages = request.getParameter("numberOfChages");
			String creditCharge = request.getParameter("creditCharge");
			String debitAmt = request.getParameter("debitamt");
			String creditAmt = request.getParameter("creditamt");
			
			int invoiceid = Integer.parseInt(request.getParameter("invoiceid"));
			int clientid = Integer.parseInt(request.getParameter("clientid"));
			
			
			accountsForm.setClient(client);
			accountsForm.setClientId(Integer.toString(clientid));
			ArrayList<Accounts>assesmentList = accountsDAO.getAssesmentList(invoiceid,Integer.parseInt(payby));
			accountsForm.setAssesmentList(assesmentList);
			accountsForm.setCreditCharge(creditCharge);
			accountsForm.setInvoiceid(invoiceid);
			accountsForm.setPayby(payby);
			
			accountsForm.setCreditAmt(creditAmt);
			accountsForm.setDebitAmt(debitAmt);
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		finally{
			connection.close();
		}
		
		return "raiseinvoice";
	}
	
	public String raisesubmitinvoice() throws SQLException{
		if(!verifyLogin(request)){
			return "login";
		}
		Connection connection  = null;
		try{
			
			connection  = Connection_provider.getconnection();
			AccountsDAO accountsDAO = new JDBCAccountsDAO(connection);
			
			String payby = request.getParameter("payby");
			String client = request.getParameter("client");
			String numberOfChages = request.getParameter("numberOfChages");
			String creditCharge = request.getParameter("creditCharge");
			String debitAmt = request.getParameter("debitamt");
			String creditAmt = request.getParameter("creditamt");
			
			int invoiceid = Integer.parseInt(request.getParameter("invoiceid"));
			int clientid = Integer.parseInt(request.getParameter("clientid"));
			
			
			accountsForm.setClient(client);
			accountsForm.setClientId(Integer.toString(clientid));
			ArrayList<Accounts>assesmentList = accountsDAO.getAssesmentList(invoiceid,Integer.parseInt(payby));
			accountsForm.setAssesmentList(assesmentList);
			accountsForm.setCreditCharge(creditCharge);
			accountsForm.setInvoiceid(invoiceid);
			accountsForm.setPayby(payby);
			
			accountsForm.setCreditAmt(creditAmt);
			accountsForm.setDebitAmt(debitAmt);
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		finally{
			connection.close();
		}
		
		return "raisesubmitinvoice";
	}
	
	public String createinvoice() throws SQLException{
		if(!verifyLogin(request)){
			return "login";
		}
		String totalAssesment[] = accountsForm.getTotalassesment().split(",");
		String invoiceid = request.getParameter("invoiceid");
		int selectesPayby = Integer.parseInt(request.getParameter("payby"));
		
		Connection connection  = null;
		try{
			
			connection  = Connection_provider.getconnection();
			AccountsDAO accountsDAO = new JDBCAccountsDAO(connection);
		
			
			
			StringBuffer numList = new StringBuffer();
//			System.out.println(totalAssesment.length);
			for(int i=1;i<totalAssesment.length;i++){
				numList.append(totalAssesment[i] + ",");
			}
			
			if(numList.length() > 0){
				String num = numList.substring(0, numList.length()-1);
//				System.out.println(num);
				ArrayList<Accounts>invoiceList = accountsDAO.getInvoiceList(num,invoiceid);
				
				for(Accounts accounts : invoiceList){
					int update = accountsDAO.setInvoiceInactive(Integer.toString(accounts.getInvoiceid()));
				}
			}
			
			
			int result = accountsDAO.updateInvoiceChargeType(accountsForm.getInvoiceid(),selectesPayby);
			
			
			//send to charges_invoice
			double debit = accountsForm.getDebitTotal();
			int discount=0;
			String payBY = "";
			if(Integer.parseInt(accountsForm.getPayby())==0){
				payBY = Constants.PAY_BY_CLIENT;
			}else{
				payBY = Constants.PAY_BY_THIRD_PARTY;
			}
			
			
			String temp[] = accountsForm.getInvoiceDate().split("-");
			String commencing = temp[2] + "-" + temp[1] + "-" + temp[0];
			
			/*int chargesInvoiceid = accountsDAO.saveChargesInvoice(payBY,commencing,Integer.parseInt(accountsForm.getClientId()),debit,discount, accountsForm.getSubmitInvoiceNotes());
			int save = accountsDAO.saveChargesAssesment(Integer.parseInt(invoiceid),chargesInvoiceid);
			*/
			//int delete = accountsDAO.deleteChargeAccounts(loginInfo.getLoginsessionid());
			
			
			addActionMessage(""+accountsForm.getClient()+" Invoice Raised successfully!!");
			accountsForm.setHdnSelectedID(invoiceid);
			setFormData();
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			connection.close();
		}
		
		return "createinvoice";
	}
	
	
	
	
	// submit invoice
	
	public String submitinvoice() throws SQLException{
		if(!verifyLogin(request)){
			return "login";
		}
		String totalAssesment[] = accountsForm.getTotalassesment().split(",");
		String invoiceid = request.getParameter("invoiceid");
		
		Connection connection  = null;
		try{
			
			connection  = Connection_provider.getconnection();
			AccountsDAO accountsDAO = new JDBCAccountsDAO(connection);
		
			
			
		
			
			int result = accountsDAO.updateSubmitInvoiceChargeType(accountsForm.getInvoiceid(),accountsForm.getSubmitInvoiceNotes());
			
			int delete = accountsDAO.deleteChargeAccounts(loginInfo.getLoginsessionid());
			addActionMessage(""+accountsForm.getClient()+" Invoice Submited successfully!!");
			accountsForm.setHdnSelectedID(invoiceid);
			setFormData();
			
			
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			connection.close();
		}
		
		return "submitinvoice";
	}
	
	public String pay() throws SQLException{
		if(!verifyLogin(request)){
			return "login";
		}
		Connection connection  = null;
		try{
			
			connection  = Connection_provider.getconnection();
			AccountsDAO accountsDAO = new JDBCAccountsDAO(connection);
			
			String invoiceid = request.getParameter("invoiceid");
			String payAmount = request.getParameter("payAmount");
			String howpaid = request.getParameter("howpaid");
			String invoiceDate = request.getParameter("invoiceDate");
			String paid = request.getParameter("paid");
			String whoPay = request.getParameter("whoPay");
			String clientId = request.getParameter("clientId");
			String payBuy = accountsForm.getPayby();
			if(payBuy==null){
				payBuy= accountsForm.getPayby1();
				
				accountsForm.setPayby(payBuy);
			}
			if(payBuy.equals("0")){
				whoPay  = accountsForm.getClient();
			}else{
				whoPay = accountsDAO.getThirdartyName(accountsForm.getClientId());
			}
			
			int result = accountsDAO.updatePayment(invoiceid,payAmount,howpaid,invoiceDate,paid);
			int resultPayment = accountsDAO.insertPayment(invoiceid,payAmount,howpaid,invoiceDate,paid,whoPay,clientId,payBuy);
			double credit = accountsDAO.getCreditAmount(invoiceid,payBuy);
			double totalPayment = accountsDAO.getPaymentAmount(Integer.parseInt(invoiceid),payBuy);
			double debit = totalPayment - credit;
			int resultUpdate = accountsDAO.updateTransaction(invoiceid,credit,debit);
			
			addActionMessage(""+accountsForm.getClient()+" Payment done successfully!!");
			
			
				//Sms to Patient
			  ClientDAO clientDAO= new JDBCClientDAO(connection);
			  Client client=clientDAO.getClientDetails(clientId);
			  String clientname= client.getTitle()+" "+client.getFirstName()+" "+client.getLastName();
			  ClinicDAO clinicDAO=new JDBCClinicDAO(connection);
			  Clinic clinic=clinicDAO.getCliniclistDetails(loginInfo.getClinicid());
			  String itype = accountsDAO.getInvoiceTypeId(Integer.parseInt(invoiceid));
			  boolean isPaymentSms= clinicDAO.isPaymentSMSActive(loginInfo.getClinicid());
			  if((itype.equals("1")&&loginInfo.isOpd_payamnt_sms())||(itype.equals("2")&&loginInfo.isIpd_payamnt_sms())||(itype.equals("3")||itype.equals("6")||itype.equals(""))||(itype.equals("5")&&loginInfo.isAdv_payamnt_sms())){
				  UserProfileDAO userProfileDAO = new JDBCUserProfileDAO(connection);
				  String templateid = userProfileDAO.getSMSTemplateID("Payment");
				  String msg="Thanks "+clientname+" for payment of Rupees "+payAmount+" from- "+clinic.getClinicName()+"";
				  SmsService service= new SmsService();
				  service.sendSms(msg, client.getMobNo(), loginInfo, new EmailLetterLog(),templateid);
			  
			  }
			
			//redirect to execute
			accountsForm.setHdnSelectedID(invoiceid);
			setFormData();
			
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
		
		return "pay";
	}
	
	public String invoice() throws SQLException{
		if(!verifyLogin(request)){
			return "login";
		}
		Connection connection  = null;
		try{
			
			connection  = Connection_provider.getconnection();
			AccountsDAO accountsDAO = new JDBCAccountsDAO(connection);
			/*CompleteAptmDAO completeAptmDAO = new JDBCCompleteAptmDAO(connection);
			
			
			CompleteAppointment completeAppointment = new CompleteAppointment();
			
			completeAppointment = completeAptmDAO.getInsuranceCompanyName(accountsForm.getClientId());
			accountsForm.setClientId(accountsForm.getClientId());
			accountsForm.setInsuranceCompany("Company : "+completeAppointment.getInsuranceCompanyName() + " Owner : " + completeAppointment.getInsuranceCompanyOwnerName() + "Address: " + completeAppointment.getInsuranceCompanyAddress());
			accountsForm.setPayby(accountsForm.getPayby());
			String paybuy = accountsForm.getPayby();
			session.setAttribute("paybuy", paybuy);
			System.out.println(paybuy);*/
			
			
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		finally{
			connection.close();
		}
		
		return "invoice";
	}
	
	public String preview() throws SQLException{
		if(!verifyLogin(request)){
			return "login";
		}
		Connection connection  = null;
		String payby = request.getParameter("payby");
		int invoiceid = Integer.parseInt(request.getParameter("invoiceid"));
		try{
			
			connection  = Connection_provider.getconnection();
			AccountsDAO accountsDAO = new JDBCAccountsDAO(connection);
			
			ArrayList<Accounts>assesmentList = accountsDAO.getAssesmentList(accountsForm.getInvoiceid(),Integer.parseInt(payby));
			
			int charges = 0;
			for(Accounts accounts : assesmentList){
				charges = charges + Integer.parseInt(accounts.getCharges().trim());
			}
			for(Accounts accounts : assesmentList){
				String date = accounts.getCommencing();
				accountsForm.setCommencing(date);
			}
			String tempcharges = Integer.toString(charges).trim();
			int payAmount = accountsDAO.getPayAmount(accountsForm.getInvoiceid());
			
			int debitAmounnt = Integer.parseInt(request.getParameter("debitamt"));
			int credit = Integer.parseInt(request.getParameter("creditamt"));
			accountsForm.setCreditCharge(tempcharges);
			accountsForm.setCreditAmt(request.getParameter("creditamt"));
			accountsForm.setAssesmentList(assesmentList);
			accountsForm.setPayAmount(payAmount);
			accountsForm.setDebitAmounnt(Integer.toString(debitAmounnt));
		//	accountsForm.setDiscount(request.getParameter("discountany"));
			int totalAmt = debitAmounnt + credit;
			accountsForm.setTotalAmount(totalAmt);
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		finally{
			connection.close();
		}
		return "preview";
	}
	
	public String payby() throws SQLException{
		if(!verifyLogin(request)){
			return "login";
		}
		
		Connection connection  = null;
		try{
			
			connection  = Connection_provider.getconnection();
			AccountsDAO accountsDAO = new JDBCAccountsDAO(connection);
			
			int invoiceid = Integer.parseInt(request.getParameter("invoiceid"));
			String payby = request.getParameter("payby");
		
			
			int result = accountsDAO.updatePayBy(invoiceid,payby);
			
//			System.out.println(payby);
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		finally{
			connection.close();
		}
		return null;
	}
	
	public String transactions() throws Exception{
		if(!verifyLogin(request)){
			return "login";
		}

		String pay = request.getParameter("payby");
		String invoiceid = request.getParameter("invoiceid");
		String debitamt = request.getParameter("debitAmount");
		String creditamt = request.getParameter("creditAmount");
		String clientid = request.getParameter("clientid");
		String client = request.getParameter("client");
		Connection connection = null;
		try{
			connection = Connection_provider.getconnection();
			AccountsDAO accountsDAO = new JDBCAccountsDAO(connection);
			ArrayList<Accounts>transactionList = accountsDAO.gettransactionList(clientid,invoiceid);
			StringBuffer str = new StringBuffer();
			int count =1;
			str.append("<div><b>Invoice No: </b>"+invoiceid+" <b>Client: </b> "+client+"  <b>Credit: </b> "+creditamt+" <b>Debit: </b>"+debitamt+"</div>");
			str.append("</br>");
			str.append("<table class='table table-hover table-condensed table-bordered table-striped'> ");
			
			str.append("<thead>");
			str.append("<tr>");
			str.append("<th>Date</th> ");
			str.append("<th>Type</th> ");
			str.append("<th>Payment Mode</th> ");
			str.append("<th>Charge</th> ");
			str.append("</tr>");
			str.append("</thead>");
			str.append("<tbody>");
			for(Accounts accounts:transactionList){
			str.append("<tr>");
			str.append("<td>"+count+"</td>");
			str.append("<td>"+accounts.getDate()+"</td>");
			str.append("<td>"+accounts.getPaymentmode()+"</td>");
			str.append("<td>"+accounts.getAmount()+"</td>");
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
	
	public String showAllAssessment() throws Exception{
		if(!verifyLogin(request)){
			return "login";
		}
		
		int invoiceid = Integer.parseInt(request.getParameter("invoiceid"));
		String payby = request.getParameter("payby");
		Connection connection  = null;
		
		try{
			
			connection  = Connection_provider.getconnection();
			AccountsDAO accountsDAO = new JDBCAccountsDAO(connection);
			
			ArrayList<Accounts>assesmentList = accountsDAO.getAssesmentList(invoiceid,Integer.parseInt(payby));
			int additCharge = accountsDAO.getAdditionalChargeValue(invoiceid);
			StringBuffer str = new StringBuffer();
			
			str.append("<table class='table table-hover table-condensed table-bordered' > ");
			
			str.append("<thead>");
			str.append("<tr>");
			//str.append("<td colspan='1'></td>");
			str.append("<th colspan ='3'></th> ");
			str.append("<th colspan ='2'>Charge Type</th> ");
			str.append("<th></th> ");
			
			str.append("<th>Unit Price</th> ");
			
			
			str.append("<th colspan='4'>Qty.</th> ");
			str.append("<th>Total</th>");
			
			str.append("</tr>");
			str.append("</thead>");
			str.append("<tbody>");	
			for(Accounts accounts:assesmentList){
			
			str.append("<tr class='info'>");
			
			//str.append("<td colspan='1'><input type='checkbox' id='chkcharge"+accounts.getId()+"' name='chkcharge"+accounts.getId()+"' value='"+accounts.getId()+"' class='selectClass1'></td>");
			str.append("<td  colspan ='3'></td>");
			if(additCharge == 1){
				str.append("<td  colspan ='2'> "+accounts.getAppointmentType()+" </td>");

				str.append("<td></td>");
			}
			else{
				if(accounts.getChargeType().equalsIgnoreCase(Constants.POLICYEXCESS)){
					str.append("<td  colspan ='2'>"+accounts.getAppointmentType()+"</td>");

				}
				else{
					str.append("<td  colspan ='2'>"+accounts.getChargeType()+" ("+accounts.getAppointmentType()+")</td>");
	
				}

				str.append("<td>"+accounts.getTreatmentEpisodeName()+"<br>Practitioner: "+accounts.getPractitionerName()+ "</td>");

			}
			
			
			if(loginInfo.getUserType()==2){
				str.append("<td style='display:block'>"+accounts.getCharges()+"</td>");
			}else if(Integer.parseInt(payby)==0 && loginInfo.getUserType()==4){
				str.append("<td style='display:block'>"+accounts.getCharges()+"</td>");
			}else if(Integer.parseInt(payby)==1 && loginInfo.getUserType()==4){
				str.append("<td style='display:none'>"+accounts.getCharges()+"</td>");
			}
			
			
			str.append("<td colspan='4'>"+accounts.getQuantity()+"</td>");
			double chargeTotal = Double.parseDouble(accounts.getCharges()) * accounts.getQuantity();
			str.append("<td colspan='4'>"+DateTimeUtils.changeFormat(chargeTotal)+"</td>");
			str.append("</tr>");
			
			
			}
			str.append("<tbody>");
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
	
	public String delete() throws Exception{
		Connection connection  = null;

		try{
			
			connection  = Connection_provider.getconnection();
			AccountsDAO accountsDAO = new JDBCAccountsDAO(connection);
			int del = accountsDAO.deleteChargeAccounts(loginInfo.getLoginsessionid());
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			
			connection.close();
		}
		
		return null;
	}
	
	

	public AccountsForm getModel() {
		
		return accountsForm;
	}


	public void prepare() throws Exception {
		
		Connection connection  = null;
		
		try{
			
			connection  = Connection_provider.getconnection();
			AccountsDAO accountsDAO = new JDBCAccountsDAO(connection);
			EmrDAO emrDAO = new JDBCEmrDAO(connection);
			
			ArrayList<Accounts>locationList = accountsDAO.getLocationList(loginInfo.getClinicid());
			ArrayList<Accounts>thirdPartyList = accountsDAO.getThirdPartyList(loginInfo.getId());
			accountsForm.setLocationList(locationList);
			accountsForm.setThirdPartyList(thirdPartyList);
			
			
			String regLocationID = emrDAO.getRegisteredLocationId();
			accountsForm.setLocation(regLocationID);
			
			ArrayList<Master>invoiceTypeList = accountsDAO.getInvoiceTypeList();
			accountsForm.setInvoiceTypeLis(invoiceTypeList);
			
		}
		catch(Exception e){
			e.printStackTrace();
		}
		
	}

}
