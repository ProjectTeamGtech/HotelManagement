package com.apm.Inventory.web.action;

import java.sql.Connection;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.apm.Accounts.eu.bi.ChargesAccountProcessingDAO;
import com.apm.Accounts.eu.blogic.jdbc.JDBCChargeAccountProcesDAO;
import com.apm.DiaryManagement.eu.entity.Breadcrumbs;
import com.apm.Expence.eu.bi.ExpenManagementDAO;
import com.apm.Expence.eu.bi.blogic.jdbc.JDBCExpenceManagementDAO;
import com.apm.Expence.eu.entity.Expence;
import com.apm.Inventory.eu.bi.InventoryProductDAO;
import com.apm.Inventory.eu.bi.InventoryVendorDAO;
import com.apm.Inventory.eu.bi.PoPaymenytDAO;
import com.apm.Inventory.eu.blogic.jdbc.JDBCInventoryProductDAO;
import com.apm.Inventory.eu.blogic.jdbc.JDBCInventoryVendorDAO;
import com.apm.Inventory.eu.blogic.jdbc.JDBCPoPaymengtDAO;
import com.apm.Inventory.eu.entity.Procurement;
import com.apm.Inventory.eu.entity.Product;
import com.apm.Inventory.eu.entity.Vendor;
import com.apm.Inventory.web.form.ProcurementForm;
import com.apm.Master.eu.bi.MasterDAO;
import com.apm.Master.eu.blogic.jdbc.JDBCMasterDAO;
import com.apm.Master.eu.entity.Master;
import com.apm.Pharmacy.eu.bi.PharmacyDAO;
import com.apm.Pharmacy.eu.blogic.jdbc.JDBCPharmacyDAO;
import com.apm.common.eu.blogic.jdbc.Connection_provider;
import com.apm.common.utils.DateTimeUtils;
import com.apm.common.utils.Pagination;
import com.apm.common.web.action.BaseAction;
import com.apm.common.web.common.helper.LoginHelper;
import com.apm.common.web.common.helper.LoginInfo;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.Preparable;

public class PoPaymentAction extends BaseAction implements ModelDriven<ProcurementForm> ,Preparable{
	
	ProcurementForm procurementForm=new ProcurementForm();
	HttpServletRequest request = (HttpServletRequest) ActionContext.getContext().get(ServletActionContext.HTTP_REQUEST);
	HttpServletResponse response = (HttpServletResponse)ActionContext.getContext().get(ServletActionContext.HTTP_RESPONSE);
	HttpSession session = request.getSession(true);
	LoginInfo loginInfo = LoginHelper.getLoginInfo(request);
	Pagination pagination=new Pagination(25,1);
	
	
	public Pagination getPagination() {
		return pagination;
	}


	public void setPagination(Pagination pagination) {
		this.pagination = pagination;
	}


	public String execute() throws Exception {
		Connection connection = null;
		try{
			
			ArrayList<Breadcrumbs> indentflowlist = new ArrayList<Breadcrumbs>();
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
				if(breadcrumbs.getName().equals("Supplier Accounts")){
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
				breadcrumbs.setName("Supplier Accounts");
				breadcrumbs.setOn(true);
				breadcrumbs.setSqno(modulecount);
				breadcrumbs.setUrllink("PoPayment");
				breadcrumbs.setIscurrent(true);
				breadcrumbs.setShowingname("Supplier Accounts");
				indentflowlist.add(breadcrumbs);
			}
			session.setAttribute("indentflowlist",indentflowlist);
			
			
			connection = Connection_provider.getconnection();
			PoPaymenytDAO poPaymenytDAO = new JDBCPoPaymengtDAO(connection);
			InventoryProductDAO inventoryProductDAO=new JDBCInventoryProductDAO(connection);
			
			String categoryid=(String)session.getAttribute("category");
			if(categoryid==null){
				
				categoryid="1";
			}
			/*String location=(String)session.getAttribute("location");
			String loc=(String)session.getAttribute("location");
			if(location==null){
				
				location="0";
			}*/
			String searchtext = procurementForm.getSearchtext();
			if(searchtext!=null){
				if(searchtext.equals("")){
					searchtext=null;
				}
			}
			/*if(loc==null||loc.equals("")){
				if(procurementForm.getLocation()==null||procurementForm.getLocation().equals("")){
					procurementForm.setLocation("0");
				}
				if(procurementForm.getLocation()!=null&&!procurementForm.getLocation().equals("")){
					location= procurementForm.getLocation();
				}
			}
			if(location.equals("All")){
				location="0";
			}*/
			String location = procurementForm.getFilter_location();
			if(location!=null){
				if(location.equals("")){
					location="0";
				}
			}else{
				location="0";
			}
			procurementForm.setFilter_location(location);
			int count = poPaymenytDAO.getTotalPayment(location,searchtext);
			pagination.setPreperties(count);
			procurementForm.setTotalRecords(count);
			ArrayList<Procurement>paymentList = poPaymenytDAO.getPoPaymentList(location,searchtext,pagination);
			procurementForm.setPaymentList(paymentList);
			pagination.setPage_records(paymentList.size());
			procurementForm.setPagerecords(String.valueOf(pagination.getPage_records()));
			
			procurementForm.setCategory_id(categoryid);
			ArrayList<Product> brandnameList= inventoryProductDAO.getAllBrandListByCategory(categoryid);
			PharmacyDAO pharmacyDAO= new JDBCPharmacyDAO(connection);
			ArrayList<Master> locationlist= pharmacyDAO.getAllLocation();
			 procurementForm.setLocationListPharmacy(locationlist);
		    procurementForm.setBrandnameList(brandnameList);
			procurementForm.setSearchtext(searchtext);
			
			double totalorderamt=0;
			double paidpayment=0;
			double returnsettledamt=0;
			double totalpaidamt=0;
			double balanceamt=0;
			if(loginInfo.isParihar()) {
				
			totalorderamt=paymentList.get(paymentList.size() - 1).getTotalorderamt();
			paidpayment=paymentList.get(paymentList.size() - 1).getTotalpaidamt();
			returnsettledamt=paymentList.get(paymentList.size() - 1).getTotalreturnsettledamt();
			totalpaidamt=paymentList.get(paymentList.size() - 1).getTotalpaidamt();
			balanceamt=paymentList.get(paymentList.size() - 1).getTotalbalanceamt();
			
			procurementForm.setTotalorderamt(totalorderamt);
			procurementForm.setTotalpaymentamt(paidpayment);
			procurementForm.setTotalreturnsettledamt(returnsettledamt);
			procurementForm.setTotalpaidamt(totalpaidamt);
			procurementForm.setTotalbalanceamt(balanceamt);
		 }
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			connection.close();
		}
		return super.execute();
	}
	
	
public String pay() throws Exception{
		
		Connection connection = null;
		try{
			connection = Connection_provider.getconnection();
			PoPaymenytDAO poPaymenytDAO = new JDBCPoPaymengtDAO(connection);
			InventoryProductDAO inventoryProductDAO=new JDBCInventoryProductDAO(connection);
			PharmacyDAO pharmacyDAO = new JDBCPharmacyDAO(connection);
			ChargesAccountProcessingDAO chargesAccountProcessingDAO = new JDBCChargeAccountProcesDAO(connection);
			String categoryid=(String)session.getAttribute("category");
			if(categoryid==null){
				categoryid="1";
			}
			procurementForm.setCategory_id(categoryid);
			String ledgername = procurementForm.getLedgername();
			if(ledgername==null){
				ledgername ="0";
			}
			String procurementid=procurementForm.getHdnProcurementid();
			String newvendorid = procurementForm.getVendor_id();
			String date = DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone());
			String sumofreturn = request.getParameter("sumofreturn");
			String total_amt = request.getParameter("total_amt");
			String refundcheck = request.getParameter("refundcheck");
			if(newvendorid!=null){
				if(newvendorid.equals("")){
					newvendorid="0";
				}
			}else{
				newvendorid="0";
			}
			int vendorid = Integer.parseInt(newvendorid);
			if(refundcheck!=null){
				if(refundcheck.equals("on")){
					refundcheck ="1";
				}else{
					sumofreturn="0";
					refundcheck="0";
				}
			}else{
				sumofreturn="0";
				refundcheck ="0";
			}
			double totalamount=0;
			double refundamt=0;
			if(total_amt!=null){
				if(total_amt.equals("")){
					totalamount = 0;
				}else{
					totalamount = Double.parseDouble(total_amt);
				}
			}else{
				totalamount = 0;
			}
			if(sumofreturn!=null){
				if(sumofreturn.equals("")){
					refundamt = 0;
				}else{
					refundamt = Double.parseDouble(sumofreturn);
				}
			}else{
				refundamt = 0;
			}
			double totalpaid=0;
			if(refundcheck.equals("1")){
				totalpaid = totalamount+refundamt;
			}else{
				totalpaid = totalamount;
			}
			int paymentids =poPaymenytDAO.saveParentPaymentData(newvendorid,loginInfo.getUserId(),date,total_amt,sumofreturn,refundcheck,totalpaid,0);
			String paymode = "";
			String bankid ="0";
			int count=0;
			String loction="0";
			for(Procurement procurement: procurementForm.getPayments()){
				  String checked= procurement.getCheck();
				  if(checked!=null){
					  if(checked.equals("1")){
						  String commencing = DateTimeUtils.getCommencingDate(procurementForm.getPaymentDate());
						  procurement.setPaymentDate(commencing);
						  procurement.setProcurementid(procurement.getProcurementid());
						  int rsult = poPaymenytDAO.savePayment(procurement,paymentids); 
						  if(count==0){
							  paymode = procurement.getPaymentType();
							  bankid= procurement.getBankid();
							  loction = procurement.getLocation();
						  }
						  count++;
						 //  04 May 2018
						  String balance = procurement.getBalance();
						  String paymentamount = procurement.getPaymentAmount();
						  if(balance!=null && paymentamount!=null){
							if(!balance.equals("") &&  (!paymentamount.equals(""))){
								double res = Double.parseDouble(paymentamount)- Double.parseDouble(balance);
								if(res<=0){
									int test = poPaymenytDAO.updateProcurmnetPaymentStatus(procurement.getProcurementid());
								}
							}
						  }
					  }
				 }
				
			}
			
			if(!paymode.equals("Cheque")){
				bankid ="0";
			}
			
			String locationname = pharmacyDAO.getLocationName(loction);
			String serviceid = chargesAccountProcessingDAO.getLedgrServiceIds(locationname);
			//String serviceid ="0";
			String ledgerid = chargesAccountProcessingDAO.getledgerIDNew(serviceid, paymode, bankid,"1",0);
			
			
			/*double tot = Double.parseDouble(procurement.getPaymentAmount());*/
			double tot = totalpaid;
			double lbal = chargesAccountProcessingDAO.getLedgerBalance(ledgerid);
			lbal = lbal + tot;
			String credit = "" + tot + "";
			String ldebit = "0";
			String product = "xxxxx";
			String partyid = "0";
			//int vendorid=Integer.parseInt(newvendorid);
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
			
			//create payment voucher
			createPaymentVoucher(newvendorid,tot,connection,saveledger,ledgername,paymentids);
			
			if(refundcheck.equals("1")){
				String grnreturnids = procurementForm.getGrnreturnids();
				if(grnreturnids!=null){
					for (String t : grnreturnids.split(",")) {
						if (t.equals("0")) {
							continue;
						}
						int results = poPaymenytDAO.updateReturnPaymentDoneStatus(t,paymentids); 
					}
				}
			}
			session.setAttribute("sessionprocurementid", procurementid);
			ArrayList<Product> brandnameList= inventoryProductDAO.getAllBrandListByCategory(categoryid);
		    procurementForm.setBrandnameList(brandnameList);
			 
			
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		finally {
			connection.close();
		}
		
		return "pay";
	}
	

	private void createPaymentVoucher(String newvendorid, double tot,Connection connection,int officialledger, String ledgername, int paymentids) {
		
		
		ExpenManagementDAO expenManagementDAO = new JDBCExpenceManagementDAO(connection);
		ChargesAccountProcessingDAO chargesAccountProcessingDAO = new JDBCChargeAccountProcesDAO(connection);
		try{
			
			String uid=loginInfo.getUserId();
			String pcommencing = DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone());
			
			String column = "pno";
			int pno = expenManagementDAO.getExpenceMaxno(column);
			//  07-01-2018 
			String ledgerid = ledgername;
			
			int parentid = expenManagementDAO.saveParentExpData(ledgerid,"Payment","0",
					"",pcommencing,uid,pno,0,0,0,0);
			
			Expence expence = new Expence();
			expence.setAmount("0");
			expence.setLastmodified(DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone()));
			expence.setCurrency("INR");
			expence.setUserid(uid);
			expence.setParantid(""+parentid+"");
			expence.setCredit(""+tot+"");
			expence.setLedgerid(ledgerid);
			expence.setTransid("0");
			
			 DateFormat dateFormat1 = new SimpleDateFormat("dd/MM/yyyy");
			    Date date=new Date();
			 String datestr=dateFormat1.format(date).toString();
			 expence.setCaldate(datestr);
			  
			 String vendorname = expenManagementDAO.getVendorName(Integer.parseInt(newvendorid));
			 expence.setCategory(vendorname);
			
			
			
			int expenceid = expenManagementDAO.addPaymentVoucher(expence,parentid,"Payment");
			
			//  09-01-2019
			int updatestaus = expenManagementDAO.updatePaymentVoucher(expenceid,paymentids);
			
			//ledger entery
			ledgerid =  chargesAccountProcessingDAO.getExpenceLedgerId(expence.getCategory());
			double lbal = chargesAccountProcessingDAO.getAheadLedgerBalance(ledgerid);
			lbal = lbal + tot;
			 String credit = "0";
			String ldebit = ""+tot+"";
			String product = expence.getCategory();
			String partyid = "xxxxx";
			String lcommencing = DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone());
			//int saveledger = appointmentTypeDAO.saveLedgerAhead(partyid,product,ldebit,credit,lbal,Integer.parseInt(ledgerid),lcommencing,"");
			int expnctype = 1;
			int saveledger = chargesAccountProcessingDAO.saveExpenceLedger(partyid, product, ldebit, credit, lbal, ledgerid,
					lcommencing, "0",0,expnctype,"Payment",parentid,expenceid);
			int updateledgersheet = expenManagementDAO.updatePaymentLedgerSheet(saveledger,paymentids);
			
			//insert into official ledger
			 ledgerid = ledgername;
			 lbal = chargesAccountProcessingDAO.getAheadLedgerBalance(ledgerid);
			lbal = lbal - tot;
			 credit = ""+tot+"";
			 ldebit = "0";
			 product = "xxxxx";
			 partyid = "xxxxx";
			 lcommencing = DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone());
			//int saveledger = appointmentTypeDAO.saveLedgerAhead(partyid,product,ldebit,credit,lbal,Integer.parseInt(ledgerid),lcommencing);
			
			 expnctype = 2;
			 saveledger = chargesAccountProcessingDAO.saveExpenceLedger(partyid, product, ldebit, credit, lbal, ledgerid,
					lcommencing, "0",0,expnctype,"Payment",parentid,expenceid);
			 
			 updateledgersheet = expenManagementDAO.updatePaymentLedgerSheet(saveledger,paymentids);
			 
			 //update in ledger_amount table
			 double lamount = expenManagementDAO.getDbLedgerAmount(ledgerid);
			 lamount = lamount - tot;
			 int ula = expenManagementDAO.updateLedgerAmount(ledgerid,lamount);
			 
			 
			 int uol = chargesAccountProcessingDAO.updateofficicaledgerstatus(saveledger);
			 
			 
			
			 
			 //int ue = expenManagementDAO.upddateExpenceId(expenceid,officialledger,"Payment");

			
		}catch (Exception e) {
			// TODO: handle exception
		}
		
	
	
}


	public ProcurementForm getModel() {
		// TODO Auto-generated method stub
		return procurementForm;
	}


	public void prepare() throws Exception {
		  Connection connection=null;
			
			try {
				connection=Connection_provider.getconnection();
			    InventoryVendorDAO inventoryVendorDAO=new JDBCInventoryVendorDAO(connection);
			    InventoryProductDAO inventoryProductDAO= new JDBCInventoryProductDAO(connection);
			    
			    PharmacyDAO pharmacyDAO=new JDBCPharmacyDAO(connection);
				String location=(String) session.getAttribute("location");
				if(location==null){
					location="0";
				}
				
				ArrayList<Vendor> vendorList=inventoryVendorDAO.getAllVendorList(location);
				procurementForm.setVendorList(vendorList);
				
				ArrayList<Master> locationListPharmacy= pharmacyDAO.getAllLocation();
				procurementForm.setLocationListPharmacy(locationListPharmacy);
				 
				 
				 ArrayList<Product> categoryList=inventoryProductDAO.getAllCategories(null);
				 procurementForm.setCategoryList(categoryList);
				
				
				 
				procurementForm.setLocation(location);
				
			} catch (Exception e) {

			   e.printStackTrace();
			   
			}
			finally {
				connection.close();
			}	
	}

}
