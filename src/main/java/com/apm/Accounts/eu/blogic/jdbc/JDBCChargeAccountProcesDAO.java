package com.apm.Accounts.eu.blogic.jdbc;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.apm.Accounts.eu.bi.AccountsDAO;
import com.apm.Accounts.eu.bi.ChargesAccountProcessingDAO;
import com.apm.Accounts.eu.bi.StatementDAO;
import com.apm.Accounts.eu.entity.Accounts;
import com.apm.DiaryManagement.eu.entity.CompleteAppointment;
import com.apm.Emr.web.action.PrescriptionAction;
import com.apm.Master.eu.entity.Master;
import com.apm.Pharmacy.web.action.Tra;
import com.apm.Registration.eu.bi.ClinicDAO;
import com.apm.Registration.eu.blogic.jdbc.JDBCClinicDAO;
import com.apm.Registration.eu.entity.Clinic;
import com.apm.common.eu.blogic.jdbc.JDBCBaseDAO;
import com.apm.common.utils.DateTimeUtils;
import com.apm.common.utils.Pagination;
import com.apm.common.web.common.helper.LoginHelper;
import com.apm.common.web.common.helper.LoginInfo;
import com.apm.main.common.constants.Constants;
import com.opensymphony.xwork2.ActionContext;

public class JDBCChargeAccountProcesDAO extends JDBCBaseDAO implements ChargesAccountProcessingDAO{
	
	public JDBCChargeAccountProcesDAO(Connection connection){
		this.connection = connection;
	}

	public ArrayList<Accounts> getChargesAccountProcessingList(String clientId,
			String payby, String fromDate, String toDate,Pagination pagination,String actionType,String selectedInvoiceid) {
		PreparedStatement preparedStatement = null;
		DateTimeUtils dateTimeUtils = new DateTimeUtils();
		StatementDAO statementDAO = new JDBCStatementDAO(connection);
		ArrayList<Accounts>list = new ArrayList<Accounts>();
		String sql = "";
		double result = 0.0;
		HttpServletRequest request = (HttpServletRequest) ActionContext.getContext().get(ServletActionContext.HTTP_REQUEST);
		LoginInfo loginInfo = LoginHelper.getLoginInfo(request);
		if(pagination.sortColumn==null){
			if(!fromDate.equals("") && !toDate.equals("")){
				
				sql = "select id,payby,date,clientid,debit,deliverstatus,discount,isdeleted,disctype,discamt,cancelNotes,disc_approve,disc_request,iclosed,ipost,practid from apm_charges_invoice where clientid = "+clientId+" and date between '"+fromDate+"' AND '"+toDate+"' and payby like ('%"+payby+"%') order by id desc " ;
			}else{
				sql = "select id,payby,date,clientid,debit,deliverstatus,discount,isdeleted,disctype,discamt,cancelNotes,disc_approve,disc_request,iclosed,ipost,practid from apm_charges_invoice where clientid = "+clientId+"  and payby like ('%"+payby+"%') order by id desc" ;
			}
			
		}else{
			if(!fromDate.equals("") && !toDate.equals("")){
				
				sql = "select id,payby,date,clientid,debit,deliverstatus,discount,isdeleted,disctype,discamt,cancelNotes,disc_approve,disc_request,iclosed,ipost,practid from apm_charges_invoice where clientid = "+clientId+" and date between '"+fromDate+"' AND '"+toDate+"' and payby like ('%"+payby+"%')  " ;
			}else{
				sql = "select id,payby,date,clientid,debit,deliverstatus,discount,isdeleted,disctype,discamt,cancelNotes,disc_approve,disc_request,iclosed,ipost,practid from apm_charges_invoice where clientid = "+clientId+"  and payby like ('%"+payby+"%') " ;
			}
		}
		
		if(actionType.equals("viewacc")){
			sql = "select id,payby,date,clientid,debit,deliverstatus,discount,isdeleted,disctype,discamt,cancelNotes,disc_approve,disc_request,iclosed,ipost,practid from apm_charges_invoice where clientid = "+clientId+"  and id = "+selectedInvoiceid+" ";
		}
		
	
		
		 
		sql = pagination.getSQLQuery(sql.toString());
		try{
			AccountsDAO accountsDAO=new JDBCAccountsDAO(connection);
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()){
				
				result = 0;
				
				Accounts accounts = new Accounts();
				accounts.setId(rs.getInt(1));
				accounts.setPayby(rs.getString(2));
				accounts.setDate(dateTimeUtils.changeDateFormatToddmmyyyy(rs.getString(3)));
				accounts.setClientid(rs.getInt(4));
				accounts.setDebitAmount(rs.getDouble(5));
				accounts.setDeliverstatus(rs.getString(6));
				accounts.setDeleted(rs.getInt(8));
				
				accounts.setDisctype(rs.getString(9));
				accounts.setDiscamt(dateTimeUtils.changeFormat(rs.getDouble(10)));
				int disctype = rs.getInt(9);
				double discamt = rs.getDouble(10);
				accounts.setNotes(rs.getString(11));
				double debit = rs.getDouble(5);
				double total = rs.getDouble(5);
				double discount = rs.getDouble(7);
				double r1 = (total*discount)/100;
				if(disctype==1){
					r1 = discamt;
				}
				total = total-r1;
				result = result + total;
				
				
				double creditAmount = getCreditAmount(rs.getDouble(1));
				//if refund against invoice
				double refundAmt = statementDAO.getRefundAmtAgainsInvoice(rs.getInt(1));
				creditAmount = creditAmount - refundAmt;
				accounts.setCreditCharge(dateTimeUtils.changeFormat(creditAmount));
				double balance = result - creditAmount;
				accounts.setBalance(balance);
				int noOfCharges = getNoOfCharges(rs.getInt(1));
				accounts.setNumberOfChages(noOfCharges);
				accounts.setDiscount(discount);
				
				
				
				
				//Decimal Amount
				accounts.setDebitAmountx(dateTimeUtils.changeFormat(rs.getDouble(5)));
				accounts.setBalancex(dateTimeUtils.changeFormat(balance));
				accounts.setDiscountx(Double.toString(discount));
				
				accounts.setDisc_approve(rs.getString(12));
				accounts.setDisc_request(rs.getString(13));
				
				accounts.setIclosed(rs.getString(14));
				if(loginInfo.isBalgopal()) {
					//for balgopal @manoj sir
					String date_time = DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone()).split(" ")[0];
				    date_time=DateTimeUtils.getCommencingDate2(date_time);
				    long diff=DateTimeUtils.getDifferenceOfTwoDate(DateTimeUtils.getCommencingDate2(rs.getString(3)), date_time);
				    if(diff<7) {
				    	accounts.setIpost("0");
				    }else {
				    	accounts.setIpost(rs.getString(15));
				    }
				    
				}else {
					accounts.setIpost(rs.getString(15));
				}
				//accounts.setIpost(rs.getString(15));
				
				accounts.setPractitionerId(rs.getInt(16));
				//showing seq no instead of invoice id 21/09/2018
				
			    String ipdopdseq=accountsDAO.getIpdOpdSeqNoWithType(rs.getInt(1));
			    String itype= accountsDAO.getInvoiceTypeId(rs.getInt(1));
			    if(loginInfo.isPrachi_clinic()){
			    	if(itype.equals("7")){
			    		accounts.setItype("6");
			    	}else{
			    		accounts.setItype(itype);
			    	}
			    }else{
			    	accounts.setItype(itype);
			    }
//			    accounts.setItype(itype);
			    accounts.setIpdopdseq(ipdopdseq);
			    accounts.setDeleteddis(iscanceleddisc(String.valueOf(accounts.getId())));
			    int additionaldiscallow = checkRefundDoneAgainstInvoice(rs.getInt(1));
			    accounts.setAdditionaldiscallow(additionaldiscallow);
			    
				list.add(accounts);
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
		
		return list;

	}

	
	private int getNoOfCharges(int id) {
		PreparedStatement preparedStatement = null;
		int result = 0;
		String sql = "SELECT count(id) FROM apm_charges_payment where chargeinvoiceid = "+id+" ";
		
		try{
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()){
				result = rs.getInt(1);
			}
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}

	public double getCreditAmount(double id) {
		PreparedStatement preparedStatement = null;
		double result = 0;
		String sql = "SELECT sum(payment) FROM apm_charges_payment where chargeinvoiceid = "+id+" ";
		
		try{
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()){
				result = rs.getDouble(1);
			}
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}

	public ArrayList<Accounts> getAssesmentList(int invoiceid) {
		PreparedStatement preparedStatement = null;
		DateTimeUtils dateTimeUtils = new DateTimeUtils();
		ArrayList<Accounts>list = new ArrayList<Accounts>();
		//String sql = "select invoiceid,paybuy from apm_charges_assesment where chargeinvoiceid = "+invoiceid+" " ;
		StringBuffer sql = new StringBuffer();
		sql.append("select apm_charges_assesment.invoiceid,paybuy,apm_invoice_assesments.commencing,apm_invoice_assesments.wardid,apm_invoice_assesments.id from apm_charges_assesment inner join apm_invoice_assesments ");
		sql.append("on apm_invoice_assesments.invoiceid = apm_charges_assesment.invoiceid where chargeinvoiceid = "+invoiceid+" group by apm_charges_assesment.invoiceid ");
		double sumTotal = 0;
		try{
			preparedStatement = connection.prepareStatement(sql.toString());
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()){
				Accounts accounts = new Accounts();
				accounts.setInvoiceNo(rs.getString(1));
				String invoiceId = rs.getString(1);
				double total = getAmountTotal(invoiceId);
				accounts.setTotalAmount(total);
				sumTotal = sumTotal + total;
				String date = getDateOfInvoice(invoiceId);
				if(rs.getString(3)!=null){
					if(!rs.getString(3).equals("")){
						accounts.setDate(DateTimeUtils.changeDateFormatToddmmyyyy(rs.getString(3)));
					}else{
						accounts.setDate("");
					}
				}else{
					accounts.setDate("");
				}
				
				
				accounts.setDebitTotal(sumTotal);
				String ward=rs.getString(4);
				if(ward==null){
					ward="0";
				}
				if(ward.equals("")){
					ward="0";
				}
				accounts.setAssesmentid(rs.getString(5));		
				accounts.setWard(ward);
				accounts.setPayBy(rs.getInt(2));
				
				//Decimal Amount
				accounts.setTotalAmountx(dateTimeUtils.changeFormat(total));
				accounts.setDebitTotalx(dateTimeUtils.changeFormat(sumTotal));
				
				list.add(accounts);
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
		
		return list;
	}

	private String getDateOfInvoice(String invoiceId) {
		PreparedStatement preparedStatement = null;
		String date = null;
		String sql = "SELECT date FROM apm_invoice  where id = "+invoiceId+"";
		
		try{
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()){
				date = rs.getString(1);
			}
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		return date;
	}

	private double getAmountTotal(String invoiceId) {
		PreparedStatement preparedStatement = null;
		double result = 0;
		String sql = "SELECT charge,quantity,discrs,discper FROM apm_invoice_assesments  where invoiceid = "+invoiceId+" and active=1 ";
		double charge=0;
		try{
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()){
				if(rs.getInt(4)>0 || rs.getInt(3)>0){
					 charge =rs.getDouble(1);
				}else{
				 charge = rs.getDouble(1) * rs.getInt(2);
				}
				result = result + charge;
			}
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}

	public ArrayList<Accounts> getChildAssesmentList(int invoiceid) {
		PreparedStatement preparedStatement = null;
		ArrayList<Accounts>list = new ArrayList<Accounts>();
		DateTimeUtils dateTimeUtils = new DateTimeUtils();
		
		
		//String sql = "SELECT id,commencing,user,apmtType,charge,practitionerName,appointmentid,apmtType,practitionerId FROM apm_invoice_assesments where invoiceid = "+invoiceid+" and active=1 ";
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT apm_invoice_assesments.id,commencing,user,apmtType,charge,apm_invoice_assesments.practitionerName,apm_invoice_assesments.appointmentid,apmtType,apm_invoice_assesments.practitionerId,location,quantity,apm_invoice_assesments.wardid,masterchargetype ");
		sql.append("FROM apm_invoice_assesments ");
		sql.append("inner join apm_invoice on apm_invoice.id = apm_invoice_assesments.invoiceid ");
		sql.append("where invoiceid = "+invoiceid+" and active = 1 ");
		
		
		try{
			preparedStatement = connection.prepareStatement(sql.toString());
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()){
				Accounts accounts = new Accounts();
				accounts.setId(rs.getInt(1));
				accounts.setCommencing(rs.getString(2));
				accounts.setClientName(rs.getString(3));
				accounts.setAppointmentType(rs.getString(4));
				//accounts.setCharges(dateTimeUtils.changeFormat(Double.parseDouble(rs.getString(5))* rs.getInt(11)));
				accounts.setCharges(dateTimeUtils.changeFormat(Double.parseDouble(rs.getString(5))));
				accounts.setPractitionerName(rs.getString(6));
				String treamentEpisodeName = getTreatmentEpisodeName(rs.getString(7));
				accounts.setTreatmentEpisodeName(treamentEpisodeName);
				
				String chargeType = getChargeTypeOfApmt(rs.getString(4));
				if(chargeType == null){
					chargeType = "";
				}
				accounts.setChargeType(chargeType);
				accounts.setAppointmentType(rs.getString(8));
				String fullPractitionerName = getPractionerFullName(rs.getString(9));
				accounts.setPractitionerName(fullPractitionerName);
				
				ClinicDAO clinicListDAO = new JDBCClinicDAO(connection);
				Clinic clinic = clinicListDAO.getLocationDetails(rs.getInt(10));
				accounts.setLocationName(clinic.getLocationname());
				String ward= rs.getString(12);
				if(ward==null){
					ward="0";
				}
				if(ward.equals("")){
					ward="0";
				}
				accounts.setWard(ward);
				accounts.setQuantity(rs.getInt(11));
				accounts.setMasterchargetype(rs.getString(13));
				list.add(accounts);
			}
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	private String getPractionerFullName(String id) {
		PreparedStatement preparedStatement = null;
		String result = "";
		String sql = "SELECT initial,firstname,lastname FROM apm_user where id= "+id+" ";
		
		try{
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			if(rs.next()){
				result = rs.getString(1) + " " + rs.getString(2) + " " +rs.getString(3);
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
		return result;
	}

	private String getTreatmentEpisodeName(String id) {
		String treatmentEpsodeId = null;
		String treatmentEpsodeName = null;
		PreparedStatement preparedStatement = null;
		String zero = "0";
		String sql = "select treatmentEpisodeId from apm_available_slot where id = "+id+"";
		try{
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()){
				
				treatmentEpsodeId = rs.getString(1);
				if(treatmentEpsodeId.equals(zero)){
					treatmentEpsodeName = "No Treatment Episode";
				}
				else{
				treatmentEpsodeName = getTreatmentEpisode(treatmentEpsodeId);
				}
				}
			
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		return treatmentEpsodeName;
	}

	private String getTreatmentEpisode(String treatmentEpsodeId) {
		
		String treatmentEpsodeName = null;
		PreparedStatement preparedStatement = null;
		
		String sql = "select name from apm_treatment_episode where id = "+treatmentEpsodeId+"";
		try{
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()){
				
				treatmentEpsodeName = rs.getString(1);
				
			
			}	
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		return treatmentEpsodeName;
	}
	
	private String getChargeTypeOfApmt(String apmtType) {
		String chargeType = null;
		PreparedStatement preparedStatement = null;
		
		String sql = "select chargeType from apm_appointment_type where name = '"+apmtType+"'";
		try{
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()){
				
				chargeType = rs.getString(1);
				}
			
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		return chargeType;
	}

	public ArrayList<Accounts> gettransactionList(String invoiceid) {
		PreparedStatement preparedStatement = null;
		DateTimeUtils dateTimeUtils = new DateTimeUtils();
		
		ArrayList<Accounts>list = new ArrayList<Accounts>();
		String sql = "select id,chargeinvoiceid,date,paymode,payment,crdinvoiceid from apm_charges_payment where chargeinvoiceid = "+invoiceid+" and payment!='prepayment' ";
//		String sql="select id,chargeinvoiceid,date,paymode,payment,crdinvoiceid from apm_charges_payment where chargeinvoiceid <= "+invoiceid+" and paymode='prepayment' ";
		try{
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()){
				Accounts accounts = new Accounts();
				accounts.setId(rs.getInt(1));
				accounts.setInvoiceNo(rs.getString(2));
				accounts.setDate(rs.getString(3));
				
				String reqdate = "";
				if(rs.getString(3)!=null){
					String[] data = rs.getString(3).split(" ");
					reqdate = DateTimeUtils.getCommencingDate1(data[0]) +" "+data[1];
				}
				accounts.setFromDate(reqdate);
				
				accounts.setPaymentmode(rs.getString(4));
				accounts.setAmount(rs.getDouble(5));
				
				if(rs.getInt(6)>0){
					double creditAmount = getReportCreditAmount(rs.getInt(6));
					accounts.setCreditAmount(creditAmount);
				}else{
					accounts.setCreditAmount(0.0);
				}
				
				//Decimal Amount
				accounts.setAmountx(dateTimeUtils.changeFormat(rs.getDouble(5)));
				
				list.add(accounts);
			}
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	public double getReportCreditAmount(int crdInvoice) {
		PreparedStatement preparedStatement = null;
		double result = 0;
		String sql = "select charge from apm_credit_account  where id = "+crdInvoice+" ";
		
		try{
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			if(rs.next()){
				result = rs.getDouble(1);
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
		return result;
	}

	public int savePayment(String invoiceid, String payAmount, String howpaid,
			String invoiceDate, String clientId,int tpid,String paymentNote,String date,int creditInvoiceid,String chequeNo,String userid,String bankname,String discamt) {
		PreparedStatement preparedStatement = null;
		int result = 0;
		
		String time=date.split(" ")[1];
		invoiceDate=DateTimeUtils.getCommencingDate1(invoiceDate);
		invoiceDate=invoiceDate+" "+time;
		try{
			boolean chargeinvcid=isinvoiceidexist(invoiceid);
			if(chargeinvcid) {
				discamt="0";
			}
		String sql = "insert into apm_charges_payment(clientid,chargeinvoiceid,payment,paymode,date,tpid,paymentnote,crdinvoiceid,chequeno,userid,bankname,discamt) value(?,?,?,?,?,?,?,?,?,?,?,?)";
		
	
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, clientId);
			preparedStatement.setString(2, invoiceid);
			preparedStatement.setString(3, payAmount);
			preparedStatement.setString(4, howpaid);
			
			//changeing date to invoicedate so as to book back dated payment 15/11/2024
			preparedStatement.setString(5, invoiceDate);
			preparedStatement.setInt(6, tpid);
			preparedStatement.setString(7, paymentNote);
			preparedStatement.setInt(8, creditInvoiceid);
			preparedStatement.setString(9, chequeNo);
			preparedStatement.setString(10, userid);
			preparedStatement.setString(11, bankname);
			preparedStatement.setString(12, discamt);
			result = preparedStatement.executeUpdate();
			
			if (result == 1) {
				ResultSet resultSet = preparedStatement.getGeneratedKeys();
				if (resultSet.next()) {
					result = resultSet.getInt(1);
					if(!howpaid.equals("prepayment")){
						saveToPhysicalHISPayment(String.valueOf(invoiceid), "", clientId, date, howpaid, payAmount, "",String.valueOf(result));
					}
				}
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	private boolean isinvoiceidexist(String invoiceid) {
		boolean check=false;
		PreparedStatement ps= null;
		try {
			String sql="select * from apm_charges_payment where chargeinvoiceid="+invoiceid+"";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			if (rs.next()) {
				check = true;
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return check;
	}

	public int getTotalChargesAccountProcessingCount(String clientId,
			String payby, String fromDate, String toDate) {
		PreparedStatement preparedStatement = null;
		int result = 0;
		
		String sql = "";
		
		if(!fromDate.equals("") && !toDate.equals("")){
			
			sql = "select id,payby,date,clientid,debit from apm_charges_invoice where clientid = "+clientId+" and date between '"+fromDate+"' AND '"+toDate+"' and payby like ('%"+payby+"%')" ;
		}else{
			sql = "select id,payby,date,clientid,debit from apm_charges_invoice where clientid = "+clientId+"  and payby like ('%"+payby+"%')" ;
		}

		try{
			preparedStatement = connection.prepareStatement(sql);
			
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()){
				result = rs.getInt(1);
			}
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public ArrayList<Accounts> getChargesAccountProcessingPendingList(
			String clientId, String payby, String fromDate, String toDate,
			Pagination pagination) {
		
	PreparedStatement preparedStatement = null;
	DateTimeUtils dateTimeUtils = new DateTimeUtils();
	double result = 0.0;

	ArrayList<Accounts>list = new ArrayList<Accounts>();
	StatementDAO statementDAO = new JDBCStatementDAO(connection);
	String sql = "";
	
	if(pagination.sortColumn==null){
		if(!fromDate.equals("") && !toDate.equals("")){

			sql = "select id,payby,date,clientid,debit,discount,isdeleted,disctype,discamt,disc_approve,disc_request,iclosed,ipost,practid from apm_charges_invoice where clientid = "+clientId+" and date between '"+fromDate+"' AND '"+toDate+"' and payby like ('%"+payby+"%') order by id desc " ;
		}else{
			sql = "select id,payby,date,clientid,debit,discount,isdeleted,disctype,discamt,disc_approve,disc_request,iclosed,ipost,practid from apm_charges_invoice where clientid = "+clientId+"  and payby like ('%"+payby+"%') order by id desc " ;
		}
		
	}else{
		
		if(!fromDate.equals("") && !toDate.equals("")){

			sql = "select id,payby,date,clientid,debit,discount,isdeleted,disctype,discamt,disc_approve,disc_request,iclosed,ipost,practid from apm_charges_invoice where clientid = "+clientId+" and date between '"+fromDate+"' AND '"+toDate+"' and payby like ('%"+payby+"%')  " ;
		}else{
			sql = "select id,payby,date,clientid,debit,discount,isdeleted,disctype,discamt,disc_approve,disc_request,iclosed,ipost,practid from apm_charges_invoice where clientid = "+clientId+"  and payby like ('%"+payby+"%')  " ;
		}
	}
	
	
	
	
	sql = pagination.getSQLQuery(sql.toString());
	try{
		AccountsDAO accountsDAO=new JDBCAccountsDAO(connection);
		preparedStatement = connection.prepareStatement(sql);
		ResultSet rs = preparedStatement.executeQuery();
		while(rs.next()){
			Accounts accounts = new Accounts();
			accounts.setId(rs.getInt(1));
			accounts.setPayby(rs.getString(2));
			accounts.setDate(DateTimeUtils.changeDateFormatToddmmyyyy(rs.getString(3)));
			accounts.setClientid(rs.getInt(4));
			accounts.setDebitAmount(rs.getDouble(5));
			
			accounts.setDisctype(rs.getString(9));
			accounts.setDiscamt(dateTimeUtils.changeFormat(rs.getDouble(10)));
			int disctype = rs.getInt(9);
			double discamt = rs.getDouble(10);

			double debit = rs.getDouble(5);
			double total = rs.getDouble(5);
			double discount = rs.getDouble(6);
			double r1 = (total*discount)/100;
			if(disctype==1){
				r1 = discamt;
			}
			total = total-r1;
			result = result + total;
			
			double creditAmount = getCreditAmount(rs.getDouble(1));
			//if refund against invoice
			double refundAmt = statementDAO.getRefundAmtAgainsInvoice(rs.getInt(1));
			creditAmount = creditAmount - refundAmt;
			accounts.setCreditCharge(dateTimeUtils.changeFormat(creditAmount));
			double balance = result - creditAmount;
			accounts.setBalance(balance);
			int noOfCharges = getNoOfCharges(rs.getInt(1));
			accounts.setNumberOfChages(noOfCharges);
			accounts.setDiscount(rs.getDouble(6));
			accounts.setDeleted(rs.getInt(7));
			//Decimal Amount
			accounts.setDebitAmountx(dateTimeUtils.changeFormat(rs.getDouble(5)));
			accounts.setBalancex(dateTimeUtils.changeFormat(balance));
			accounts.setDiscountx(dateTimeUtils.changeFormat(rs.getDouble(6)));
			String discapp = rs.getString(10);
			accounts.setDisc_approve(rs.getString(10));
			accounts.setDisc_request(rs.getString(11));
			
			accounts.setIclosed(rs.getString(12));
			accounts.setIpost(rs.getString(13));
			accounts.setPractitionerId(rs.getInt(14));
			//showing seq no instead of invoice id 21/09/2018
			
		    String ipdopdseq=accountsDAO.getIpdOpdSeqNoWithType(rs.getInt(1));
		    accounts.setIpdopdseq(ipdopdseq);
		    int additionaldiscallow = checkRefundDoneAgainstInvoice(rs.getInt(1));
		    accounts.setAdditionaldiscallow(additionaldiscallow);
			if(balance !=0){
				list.add(accounts);
			}
		}
		
	}catch(Exception e){
		e.printStackTrace();
	}
	
	
	return list;

	}

	private int checkRefundDoneAgainstInvoice(int int1) {
		int res=0;
		try {
			String sql ="select status from refund_request_parent where  manualinvoiceid='"+int1+"' and status=2";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				res =1;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return res;
	}

	public ArrayList<Accounts> getChargesAccountProcessingPaidList(
			String clientId, String payby, String fromDate, String toDate,
			Pagination pagination) {
		PreparedStatement preparedStatement = null;
		DateTimeUtils dateTimeUtils = new DateTimeUtils();
		ArrayList<Accounts>list = new ArrayList<Accounts>();
		StatementDAO statementDAO = new JDBCStatementDAO(connection);
		double result = 0.0;
		String sql = "";
		
		if(pagination.sortColumn==null){
			if(!fromDate.equals("") && !toDate.equals("")){
				
				sql = "select id,payby,date,clientid,debit,discount,isdeleted,disctype,discamt,cancelNotes,disc_approve,disc_request,iclosed,ipost,practid from apm_charges_invoice where clientid = "+clientId+" and date between '"+fromDate+"' AND '"+toDate+"' and payby like ('%"+payby+"%') order by id desc " ;
			}else{
				sql = "select id,payby,date,clientid,debit,discount,isdeleted,disctype,discamt,cancelNotes,disc_approve,disc_request,iclosed,ipost,practid from apm_charges_invoice where clientid = "+clientId+"  and payby like ('%"+payby+"%') order by id desc " ;
			}
			
		}else{
			
			if(!fromDate.equals("") && !toDate.equals("")){
				
				sql = "select id,payby,date,clientid,debit,discount,isdeleted,disctype,discamt,cancelNotes,disc_approve,disc_request,iclosed,ipost,practid from apm_charges_invoice where clientid = "+clientId+" and date between '"+fromDate+"' AND '"+toDate+"' and payby like ('%"+payby+"%')  " ;
			}else{
				sql = "select id,payby,date,clientid,debit,discount,isdeleted,disctype,discamt,cancelNotes,disc_approve,disc_request,iclosed,ipost,practid from apm_charges_invoice where clientid = "+clientId+"  and payby like ('%"+payby+"%')  " ;
			}
		}
		
		
		
		
		sql = pagination.getSQLQuery(sql.toString());
		try{
			AccountsDAO accountsDAO=new JDBCAccountsDAO(connection);
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()){
				Accounts accounts = new Accounts();
				accounts.setId(rs.getInt(1));
				accounts.setPayby(rs.getString(2));
				accounts.setDate(dateTimeUtils.changeDateFormatToddmmyyyy(rs.getString(3)));
				accounts.setClientid(rs.getInt(4));
				accounts.setDebitAmount(rs.getDouble(5));
				
				accounts.setDisctype(rs.getString(9));
				accounts.setDiscamt(dateTimeUtils.changeFormat(rs.getDouble(10)));
				int disctype = rs.getInt(9);
				double discamt = rs.getDouble(10);
				accounts.setNotes(rs.getString(11));
				
				double debit = rs.getDouble(5);
				double total = rs.getDouble(5);
				double discount = rs.getDouble(6);
				double r1 = (total*discount)/100;
				if(disctype==1){
					r1 = discamt;
				}
				total = total-r1;
				result = result + total;
				
				double creditAmount = getCreditAmount(rs.getDouble(1));
				//if refund against invoice
				double refundAmt = statementDAO.getRefundAmtAgainsInvoice(rs.getInt(1));
				creditAmount = creditAmount - refundAmt;
				
				accounts.setCreditCharge(dateTimeUtils.changeFormat(creditAmount));
				double balance = result - creditAmount;
				accounts.setBalance(balance);
				int noOfCharges = getNoOfCharges(rs.getInt(1));
				accounts.setNumberOfChages(noOfCharges);
				accounts.setDiscount(discount);
				accounts.setDeleted(rs.getInt(7));
				
				//Decimal Amount
				accounts.setDebitAmountx(dateTimeUtils.changeFormat(rs.getDouble(5)));
				accounts.setBalancex(dateTimeUtils.changeFormat(balance));
				accounts.setDiscountx(dateTimeUtils.changeFormat(discount));
				
				accounts.setDisc_approve(rs.getString(11));
				accounts.setDisc_request(rs.getString(12));
				
				accounts.setIclosed(rs.getString(13));
				accounts.setIpost(rs.getString(14));
				accounts.setPractitionerId(rs.getInt(15));
				//showing seq no instead of invoice id 21/09/2018
				
			    String ipdopdseq=accountsDAO.getIpdOpdSeqNoWithType(rs.getInt(1));
			    accounts.setIpdopdseq(ipdopdseq);
			    int additionaldiscallow = checkRefundDoneAgainstInvoice(rs.getInt(1));
			    accounts.setAdditionaldiscallow(additionaldiscallow);
				if(balance ==0){
					list.add(accounts);
				}
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
		
		return list;
	}

	public double getDiscount(int invoiceid) {
		double discount = 0;
		PreparedStatement preparedStatement = null;
		
		String sql = "select discount from apm_charges_invoice where id = "+invoiceid+"";
		try{
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()){
				
				discount = rs.getInt(1);
				}
			
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		return discount;

	}

	public Accounts getClientDetails(String clientId) {
		PreparedStatement preparedStatement = null;
		
		String sql = "select title,firstname,surname,dob,address,policyno from apm_patient where id = "+clientId+" ";
		Accounts accounts = new Accounts();
		try{
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()){
				String name = rs.getString(1)+ " " +rs.getString(2) + " " +rs.getString(3);
				accounts.setClientName(name);
				accounts.setClientDob(rs.getString(4));
				accounts.setClientAdrs(rs.getString(5));
				accounts.setPolicyNo(rs.getString(6));
			}
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		return accounts;
	}
	
	
	public ArrayList<Accounts> getChargesAccountProcessingPendingPrintList(
			String clientId, String payby, String fromDate, String toDate) {
		
		PreparedStatement preparedStatement = null;
		DateTimeUtils dateTimeUtils = new DateTimeUtils();
		ArrayList<Accounts>list = new ArrayList<Accounts>();
		String sql = "";
		double result = 0.0;
		if(!fromDate.equals("") && !toDate.equals("")){
			
			sql = "select id,payby,date,clientid,debit,deliverstatus,discount from apm_charges_invoice where clientid = "+clientId+" and date between '"+fromDate+"' AND '"+toDate+"' and payby like ('%"+payby+"%') order by id desc " ;
		}else{
			sql = "select id,payby,date,clientid,debit,deliverstatus,discount from apm_charges_invoice where clientid = "+clientId+"  and payby like ('%"+payby+"%') order by id desc " ;
		}
		
		try{
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()){
				Accounts accounts = new Accounts();
				accounts.setId(rs.getInt(1));
				accounts.setPayby(rs.getString(2));
				accounts.setDate(rs.getString(3));
				accounts.setClientid(rs.getInt(4));
				accounts.setDebitAmount(rs.getDouble(5));
				accounts.setDeliverstatus(rs.getString(6));
				
				double debit = rs.getDouble(5);
				double total = rs.getDouble(5);
				double discount = rs.getDouble(7);
				double r1 = (total*(discount/100));
				total = total-r1;
				result = result + total;
				double creditAmount = getCreditAmount(rs.getDouble(1));
				accounts.setCreditCharge(dateTimeUtils.changeFormat(creditAmount));
				double balance = result - creditAmount;
				accounts.setBalance(balance);
				int noOfCharges = getNoOfCharges(rs.getInt(1));
				accounts.setNumberOfChages(noOfCharges);
				accounts.setDiscount(discount);
				
				//Decimal Amount
				accounts.setDebitAmountx(dateTimeUtils.changeFormat(rs.getDouble(5)));
				accounts.setBalancex(dateTimeUtils.changeFormat(balance));
				accounts.setDiscountx(dateTimeUtils.changeFormat(discount));
				
				if(balance !=0){
					list.add(accounts);
				}
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
		
		return list;
	}

	public ArrayList<Accounts> getChargesAccountProcessingPaidPrintList(
			String clientId, String payby, String fromDate, String toDate) {
		PreparedStatement preparedStatement = null;
		DateTimeUtils dateTimeUtils = new DateTimeUtils();
		ArrayList<Accounts>list = new ArrayList<Accounts>();
		double result = 0.0;
		String sql = "";
		
		if(!fromDate.equals("") && !toDate.equals("")){
			
			sql = "select id,payby,date,clientid,debit,deliverstatus,discount from apm_charges_invoice where clientid = "+clientId+" and date between '"+fromDate+"' AND '"+toDate+"' and payby like ('%"+payby+"%') order by id desc " ;
		}else{
			sql = "select id,payby,date,clientid,debit,deliverstatus,discount from apm_charges_invoice where clientid = "+clientId+"  and payby like ('%"+payby+"%') order by id desc " ;
		}
	
		try{
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()){
				Accounts accounts = new Accounts();
				accounts.setId(rs.getInt(1));
				accounts.setPayby(rs.getString(2));
				accounts.setDate(rs.getString(3));
				accounts.setClientid(rs.getInt(4));
				accounts.setDebitAmount(rs.getDouble(5));
				accounts.setDeliverstatus(rs.getString(6));	
				
				double debit = rs.getDouble(5);
				double total = rs.getDouble(5);
				double discount = rs.getDouble(7);
				double r1 = (total*(discount/100));
				total = total-r1;
				result = result + total;
				double creditAmount = getCreditAmount(rs.getDouble(1));
				accounts.setCreditCharge(Double.toString(creditAmount));
				double balance = result - creditAmount;
				accounts.setBalance(balance);
				int noOfCharges = getNoOfCharges(rs.getInt(1));
				accounts.setNumberOfChages(noOfCharges);
				accounts.setDiscount(discount);
				
				//Decimal Amount
				accounts.setDebitAmountx(dateTimeUtils.changeFormat(rs.getDouble(5)));
				accounts.setBalancex(dateTimeUtils.changeFormat(balance));
				accounts.setDiscountx(dateTimeUtils.changeFormat(discount));
				
				if(balance ==0){
					list.add(accounts);
				}
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
		
		return list;
	}

	public ArrayList<Accounts> getChargesAccountProcessingPrintList(
			String clientId, String payby, String fromDate, String toDate) {
		PreparedStatement preparedStatement = null;
		DateTimeUtils dateTimeUtils = new DateTimeUtils();
		
		ArrayList<Accounts>list = new ArrayList<Accounts>();
		String sql = "";
		double result = 0.0;
		if(!fromDate.equals("") && !toDate.equals("")){
			
			sql = "select id,payby,date,clientid,debit,discount from apm_charges_invoice where clientid = "+clientId+" and date between '"+fromDate+"' AND '"+toDate+"' and payby like ('%"+payby+"%') order by id desc " ;
		}else{
			sql = "select id,payby,date,clientid,debit,discount from apm_charges_invoice where clientid = "+clientId+"  and payby like ('%"+payby+"%') order by id desc" ;
		}
		
		 
		
		try{
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()){
				Accounts accounts = new Accounts();
				accounts.setId(rs.getInt(1));
				accounts.setPayby(rs.getString(2));
				accounts.setDate(rs.getString(3));
				accounts.setClientid(rs.getInt(4));
				accounts.setDebitAmount(rs.getDouble(5));
				double debit = rs.getDouble(5);
				double total = rs.getDouble(5);
				double discount = rs.getDouble(6);
				double r1 = (total*(discount/100));
				total = total-r1;
				result = result + total;
				double creditAmount = getCreditAmount(rs.getDouble(1));
				accounts.setCreditCharge(dateTimeUtils.changeFormat(creditAmount));
				double balance = result - creditAmount;
				accounts.setBalance(balance);
				int noOfCharges = getNoOfCharges(rs.getInt(1));
				accounts.setNumberOfChages(noOfCharges);
				accounts.setDiscount(discount);
				
				//Decimal Amount
				accounts.setDebitAmountx(dateTimeUtils.changeFormat(rs.getDouble(5)));
				accounts.setBalancex(dateTimeUtils.changeFormat(balance));
				accounts.setDiscountx(dateTimeUtils.changeFormat(discount));
				
				list.add(accounts);
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
		return list;
	}

	
	
	public String getClientFullName(String clientId) {
		PreparedStatement preparedStatement = null;
		String result = "";
		String sql = "select concat (title, ' ', firstname, ' ' ,surname) from apm_patient where id = "+clientId+" ";
		
		try{
			
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			if(rs.next()){
				result = rs.getString(1);
			}
			
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return result;
	}

	
	public Accounts getInvoiceDetails(int invoiceid) {
		PreparedStatement preparedStatement = null;
		Accounts accounts = new Accounts();
		String sql = "SELECT date,notes,practid,f_seq_no,itype FROM apm_charges_invoice where id = "+invoiceid+" ";
		
		try{
			
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			if(rs.next()){
				accounts.setCommencing(rs.getString(1));
				accounts.setNotes(rs.getString(2));
				accounts.setPractitionerId(rs.getInt(3));
				accounts.setNewsr(rs.getString(4));
				accounts.setItype(rs.getString(5));
			}
			
			
		}catch(Exception e){
			e.printStackTrace();
		}
		return accounts;
	}

	
	public boolean checkIsPaymentExist(String invoiceid) {
		PreparedStatement preparedStatement = null;
		boolean result = false;
		String sql = "SELECT * FROM apm_charges_payment where chargeinvoiceid = "+invoiceid+" ";
		
		try{
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			if(rs.next()){
				result = true;
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
		return result;
	}

	
	public int updateDiscount(String invoiceid,String discount) {
		PreparedStatement preparedStatement = null;
		int result = 0;
		String sql = "update apm_charges_invoice set discount=? where id = ?  ";
		
		try{
			
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, discount);
			preparedStatement.setString(2, invoiceid);
			
			result = preparedStatement.executeUpdate();
			
		}catch(Exception e){
			e.printStackTrace();
		}
		return result;
	}

	
	public int checkIsAdditionalCharge(int invoiceid) {
		PreparedStatement preparedStatement = null;
		int result = 0;
		String sql = "SELECT apmt_charge_type FROM apm_invoice  where id = "+invoiceid+" ";
		
		try{
			
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			if(rs.next()){
				result = rs.getInt(1);
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
		
		return result;
	}

	public String getLedgrServiceIds(String name) {
		PreparedStatement preparedStatement = null;
		String result = "";
		String sql = "select id from apm_invoice_type where type = '"+name+"' ";
		
		try{
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			if(rs.next()){
				result = rs.getString(1);
			}
			
			
		}catch (Exception e) {
			// TODO: handle exception
		}
		return result;
	}

	public String getledgerID(String serviceid,String pmode,String bnkname) {
		PreparedStatement preparedStatement = null;
		String result = "";
		String sql = "select * from ledger_master where services like('%,"+serviceid+",%') and paymode = '"+pmode+"' and bnkname="+bnkname+" ";
		
		try{
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			if(rs.next()){
				result = rs.getString(1);
			}
			
			
		}catch (Exception e) {
			// TODO: handle exception
		}
		return result;
	}

	public double getLedgerBalance(String ledgerid) {
		PreparedStatement preparedStatement = null;
		double result = 0;
		String sql = "select balance from ledger_sheet where ledgerid = "+ledgerid+" order by id desc limit 0,1 ";
		
		try{
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			if(rs.next()){
				result = rs.getDouble(1);
			}
			
		}catch (Exception e) {
			// TODO: handle exception
		}
		return result;
	}

	public int saveLedger(String partyid, String product, String debit, String credit, double lbal, String ledgerid,
			String commencing,String invoiceid,int paymntid, String otherclientid, String procurementid, String proc_paymentid, String phar_billno, String phar_paymentid,int vendorid,int paymentvendorid, String location) {
		PreparedStatement preparedStatement = null;
		int result = 0;
		String gcommencing = commencing.split(" ")[0];
		String sql = "insert into ledger_sheet(clientid, product, debit, credit, balance, ledgerid, commencing,invid,gcommencing,paymntid,otherclientid,proc_invoiceid,proc_paymentid,phar_billno,phar_paymentid,vendorid,payment_vendorid,inv_locationid) "
				+ "values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?) ";
		
		try{
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, partyid);
			preparedStatement.setString(2, product);
			preparedStatement.setString(3, debit);
			preparedStatement.setString(4, credit);
			preparedStatement.setDouble(5, lbal);
			preparedStatement.setString(6, ledgerid);
			preparedStatement.setString(7, commencing);
			preparedStatement.setString(8, invoiceid);
			preparedStatement.setString(9, gcommencing);
			preparedStatement.setInt(10, paymntid);
			preparedStatement.setString(11, otherclientid);
			
			preparedStatement.setString(12, procurementid);
			preparedStatement.setString(13, proc_paymentid);
			preparedStatement.setString(14, phar_billno);
			preparedStatement.setString(15, phar_paymentid);
			preparedStatement.setInt(16, vendorid);
			preparedStatement.setInt(17, paymentvendorid);
			preparedStatement.setString(18, location);
			result = preparedStatement.executeUpdate();
			
			if (result == 1) {
				ResultSet resultSet = preparedStatement.getGeneratedKeys();
				if (resultSet.next()) {
					result = resultSet.getInt(1);
				}
			}
			
		}catch (Exception e) {
			e.printStackTrace();
//			StringWriter sw = new StringWriter();
//	         PrintWriter pw = new PrintWriter(sw);
//	         e.printStackTrace(pw);
//			Tra.exceptionMail(sw.toString());
		}
		
		return result;
	}
	
	public int saveExpenceLedger(String partyid, String product, String debit, String credit, double lbal, String ledgerid,
			String commencing,String invoiceid,int paymntid,
			int expncetype,String epayment,int parentid,int expenceid) {
		PreparedStatement preparedStatement = null;
		int result = 0;
		String gcommencing = commencing.split(" ")[0];
		String sql = "insert into ledger_sheet(clientid, product, debit, credit, balance, ledgerid, commencing,invid,gcommencing,paymntid,expnctype,xpayment,parentid,expenceid) "
				+ "values(?,?,?,?,?,?,?,?,?,?,?,?,?,?) ";
		
		try{
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, partyid);
			preparedStatement.setString(2, product);
			preparedStatement.setString(3, debit);
			preparedStatement.setString(4, credit);
			preparedStatement.setDouble(5, lbal);
			preparedStatement.setString(6, ledgerid);
			preparedStatement.setString(7, commencing);
			preparedStatement.setString(8, invoiceid);
			preparedStatement.setString(9, gcommencing);
			preparedStatement.setInt(10, paymntid);
			preparedStatement.setInt(11, expncetype);
			preparedStatement.setString(12, epayment);
			preparedStatement.setInt(13, parentid);
			preparedStatement.setInt(14, expenceid);
			result = preparedStatement.executeUpdate();
			
			if (result == 1) {
				ResultSet resultSet = preparedStatement.getGeneratedKeys();
				if (resultSet.next()) {
					result = resultSet.getInt(1);
				}
			}
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}



	public boolean checkDiscEsist(String invoiceId, String ledgerid) {
		PreparedStatement preparedStatement = null;
		boolean result = false;
		String sql = "select * from ledger_sheet where ledgerid  = "+ledgerid+" and product = '"+invoiceId+"' ";
		
		try{
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			if(rs.next()){
				result = true;
			}
			
		}catch (Exception e) {
			// TODO: handle exception
		}
		return result;
	}

	public double getledgerDebitAmount(String invoiceId, String ledgerid) {
		PreparedStatement preparedStatement = null;
		double result = 0;
		String sql = "select debit from ledger_sheet where ledgerid  = "+ledgerid+" and product = '"+invoiceId+"' ";
		
		try{
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			if(rs.next()){
				result = rs.getDouble(1);
			}
			
		}catch (Exception e) {
			// TODO: handle exception
		}
		return result;
	}

	public int updateLedgerDebitDisc(String invoiceId, String ledgerid,double debit) {
		PreparedStatement preparedStatement = null;
		int result = 0;
		String sql = "update ledger_sheet set debit = '"+debit+"' where ledgerid  = "+ledgerid+" and product = '"+invoiceId+"' ";
		
		try{
			preparedStatement = connection.prepareStatement(sql);
			result = preparedStatement.executeUpdate();
			
		}catch (Exception e) {
			// TODO: handle exception
		}
		return result;
	}

	public ArrayList<Master> getLedgerBalanceList(String ledgerid) {
		PreparedStatement preparedStatement = null;
		ArrayList<Master>list   = new ArrayList<Master>();
		String sql = "select id, balance from ledger_sheet where ledgerid  = "+ledgerid+"  ";
		
		try{
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()){
				Master master = new Master();
				master.setId(rs.getInt(1));
				master.setCharge(rs.getString(2));
				
				list.add(master);
			}
			
		}catch (Exception e) {
			// TODO: handle exception
		}
		return list;
	}

	public int updateLedgerBalance(int id, double balance) {
		PreparedStatement preparedStatement = null;
		int result = 0;
		String sql = "update ledger_sheet set balance='"+balance+"' where id = "+id+" ";
		try{
			preparedStatement = connection.prepareStatement(sql);
			result = preparedStatement.executeUpdate();
			
		}catch (Exception e) {
			// TODO: handle exception
		}
		
		return result;
	}

	
	public double getAheadLedgerBalance(String ledgerid) {
		PreparedStatement preparedStatement = null;
		double result = 0;
		String sql = "select balance from ledger_ahead_sheet where ledgerid = "+ledgerid+" order by id desc limit 0,1 ";
		
		try{
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			if(rs.next()){
				result = rs.getDouble(1);
			}
			
		}catch (Exception e) {
			// TODO: handle exception
		}
		return result;
	}

	public int updateInvoiceLedgerDebit(double sumofCharges, int invoiceid) {
		PreparedStatement preparedStatement = null;
		int result = 0;
		String sql = "update ledger_sheet set credit = '"+sumofCharges+"' where invid = "+invoiceid+" and credit!=0 ";
		
		try{
			preparedStatement = connection.prepareStatement(sql);
			result = preparedStatement.executeUpdate();
			
		}catch (Exception e) {
			// TODO: handle exception
		}
		return result;
	}

	public int updateLedgerPaymentMode(String ledgerid, String paymode, String id) {
		PreparedStatement preparedStatement = null;
		int result = 0;
		String sql = "update ledger_sheet set ledgerid="+ledgerid+" where paymntid="+id+" ";
		
		try{
			
			preparedStatement = connection.prepareStatement(sql);
			result = preparedStatement.executeUpdate();
		}catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public int updateIclosed(String id,String date) {
		PreparedStatement preparedStatement = null;
		int result = 0;
		String sql =  "update apm_charges_invoice set iclosed=1,iclosedate='"+date+"' where id="+id+" ";
		
		try{
			preparedStatement = connection.prepareStatement(sql);
			result = preparedStatement.executeUpdate();
			
		}catch (Exception e) {
			// TODO: handle exception
		}
		
		

		
		return result;
	}

	public int updateIpost(String id,String date) {
		PreparedStatement preparedStatement = null;
		int result = 0;
		String sql =  "update apm_charges_invoice set ipost=1,ipostdate='"+date+"' where id="+id+" ";
		
		try{
			preparedStatement = connection.prepareStatement(sql);
			result = preparedStatement.executeUpdate();
			
		}catch (Exception e) {
			// TODO: handle exception
		}
		
		

		
		return result;
	}

	
	public ArrayList<Accounts> getHospitalRevenueList(String fromDate,String toDate,String status) {
		PreparedStatement preparedStatement = null;
		DateTimeUtils dateTimeUtils = new DateTimeUtils();
		StatementDAO statementDAO = new JDBCStatementDAO(connection);
		ArrayList<Accounts>list = new ArrayList<Accounts>();
		StringBuffer sql = new StringBuffer();
		double result = 0.0;
		
		if(status==null){
			status = "1";
		}
		
		toDate = toDate + " 23:59:59";
		
		//sql = "select id,payby,date,clientid,debit,deliverstatus,discount,isdeleted,disctype,discamt,cancelNotes,disc_approve,disc_request,iclosed,ipost from apm_charges_invoice where clientid = "+clientId+" and date between '"+fromDate+"' AND '"+toDate+"' and payby like ('%"+payby+"%')  " ;	try{
		sql.append("select id,payby,date,clientid,debit,deliverstatus,discount,isdeleted,disctype, ");
		sql.append("discamt,cancelNotes,disc_approve,disc_request,iclosed,ipost ");
		sql.append("from apm_charges_invoice ");
		if(status.equals("1")){
			sql.append("where ipostdate between '"+fromDate+"' AND '"+toDate+"' and ipost = 1 ");
		}else{
			
			
			sql.append("where iclosedate between '"+fromDate+"' AND '"+toDate+"' and iclosed = 1 ");
		}
		
		
		
		try{
			preparedStatement = connection.prepareStatement(sql.toString());
			ResultSet rs = preparedStatement.executeQuery();
			
			double totaldebit = 0;
			double totalpayment = 0;
			StringBuffer ss = new StringBuffer();
			while(rs.next()){
				
				result = 0;
				
				Accounts accounts = new Accounts();
				accounts.setId(rs.getInt(1));
				accounts.setPayby(rs.getString(2));
				accounts.setDate(dateTimeUtils.changeDateFormatToddmmyyyy(rs.getString(3)));
				accounts.setClientid(rs.getInt(4));
				accounts.setDebitAmount(rs.getDouble(5));
				accounts.setDeliverstatus(rs.getString(6));
				accounts.setDeleted(rs.getInt(8));
				
				accounts.setDisctype(rs.getString(9));
				accounts.setDiscamt(dateTimeUtils.changeFormat(rs.getDouble(10)));
				int disctype = rs.getInt(9);
				double discamt = rs.getDouble(10);
				accounts.setNotes(rs.getString(11));
				double debit = rs.getDouble(5);
				totaldebit = totaldebit + debit;
				double total = rs.getDouble(5);
				double discount = rs.getDouble(7);
				double r1 = (total*discount)/100;
				if(disctype==1){
					r1 = discamt;
				}
				total = total-r1;
				result = result + total;
				
				
				double creditAmount = getCreditAmount(rs.getDouble(1));
				totalpayment  = totalpayment + creditAmount;
				//if refund against invoice
				double refundAmt = statementDAO.getRefundAmtAgainsInvoice(rs.getInt(1));
				creditAmount = creditAmount - refundAmt;
				accounts.setCreditCharge(dateTimeUtils.changeFormat(creditAmount));
				double balance = result - creditAmount;
				accounts.setBalance(balance);
				int noOfCharges = getNoOfCharges(rs.getInt(1));
				accounts.setNumberOfChages(noOfCharges);
				accounts.setDiscount(discount);
				
				
				
				
				//Decimal Amount
				accounts.setDebitAmountx(dateTimeUtils.changeFormat(rs.getDouble(5)));
				accounts.setBalancex(dateTimeUtils.changeFormat(balance));
				accounts.setDiscountx(Double.toString(discount));
				
				accounts.setDisc_approve(rs.getString(12));
				accounts.setDisc_request(rs.getString(13));
				
				accounts.setIclosed(rs.getString(14));
				accounts.setIpost(rs.getString(15));
				
				accounts.setDebitTotalx(DateTimeUtils.changeFormat(totaldebit));
				accounts.setCreditTotalx(DateTimeUtils.changeFormat(totalpayment));
			
				ss.append(rs.getString(1) + ",");
				accounts.setInvoiceidstr(ss.toString());
				
				
				list.add(accounts);
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
		
		return list;

	}

	public ArrayList<Accounts> getRefundList(String id) {
		PreparedStatement preparedStatement = null;
		ArrayList<Accounts>list = new ArrayList<Accounts>();
		String sql = "SELECT id,date,payment_mode,debit FROM apm_credit_account where manualinvoiceid = "+id+" ";
		
		try{
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()){
				Accounts accounts = new Accounts();
				accounts.setId(rs.getInt(1));
				accounts.setDate(rs.getString(2));
				accounts.setPaymentmode(rs.getString(3));
				accounts.setAmountx(DateTimeUtils.changeFormat(rs.getDouble(4)));
				
				list.add(accounts);			}
			
		}catch (Exception e) {
			// TODO: handle exception
		}
		return list;
	}

	public double getRefundTotal(String invoicestr) {
		PreparedStatement preparedStatement = null;
		double result = 0;
		invoicestr = invoicestr.substring(0,invoicestr.length()-1);
		String sql = "SELECT sum(debit) FROM apm_credit_account where manualinvoiceid  in("+invoicestr+") ";
		
		try{
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			if(rs.next()){
				result = rs.getDouble(1);
			}
			
		}catch (Exception e) {
			// TODO: handle exception
		}
		return result;
	}

	public String getExpenceLedgerId(String category) {
		PreparedStatement preparedStatement = null;
		String result = "0";
		String sql = "select id from ledger_master where name = '"+category+"' ";
		
		try{
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			if(rs.next()){
				result = rs.getString(1);
			}
			
		}catch(Exception e){
			
		}
		return result;
	}

	
	//lokesh-----for making ipost =0 so that Posted cond can be modified
	public int updateIpostToPost(String id,String date,String user) {
		PreparedStatement preparedStatement = null;
		int result = 0;
		String sql =  "update apm_charges_invoice set ipost=0,new_ipostdate='"+date+"',user='"+user+"'  where id="+id+" ";
		
		try{
			preparedStatement = connection.prepareStatement(sql);
			result = preparedStatement.executeUpdate();
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	private boolean iscanceleddisc(String invid){
		boolean res=false;
		PreparedStatement ps= null;
		try {
			String sql="select deleted from  discount_request where invoiceid ='"+invid+"'";
			ps= connection.prepareStatement(sql);
			ResultSet rs= ps.executeQuery();
			while(rs.next()){
				res=rs.getBoolean(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return res;
	}


	public int changeWard_in_apm_invoice_assesments(String id,String wardid) {
		PreparedStatement ps= null;
		int x=0;
		String sql="update  apm_invoice_assesments set wardid='"+wardid+"' where id='"+id+"' ";
		try {
			ps= connection.prepareStatement(sql);
			x=ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return x;
	}
	public String getledgerIDNew(String serviceid, String pmode, String bnkname, String purchase, int vendorid) {
		PreparedStatement preparedStatement = null;
		String result = "";
		StringBuffer buffer = new StringBuffer();
		boolean flag = false;
		//when payment from purchase 
		if(purchase!=null){
			if(purchase.equals("1")){
				flag= true;
			}
		}
		//String sql = "select * from ledger_master where services like('%,"+serviceid+",%') and paymode = '"+pmode+"' and bnkname='"+bnkname+"' and purchase='"+purchase+"' ";
		if(flag){
			if(serviceid!=null){
				if(serviceid.equals("0")){
					buffer.append("select * from ledger_master where services like('%"+serviceid+",%') and paymode = '"+pmode+"' and bnkname='"+bnkname+"' and purchase='"+purchase+"' and vendorid='"+vendorid+"' ");
				}else{
					buffer.append("select * from ledger_master where services like('%,"+serviceid+",%') and paymode = '"+pmode+"' and bnkname='"+bnkname+"' and purchase='"+purchase+"' and vendorid='"+vendorid+"' ");
				}
			}else{
				buffer.append("select * from ledger_master where services like('%,"+serviceid+",%') and paymode = '"+pmode+"' and bnkname='"+bnkname+"' and purchase='"+purchase+"' and vendorid='"+vendorid+"' ");
			}
		}else{
			buffer.append("select * from ledger_master where services like('%,"+serviceid+",%') and paymode = '"+pmode+"' and bnkname='"+bnkname+"' and purchase='"+purchase+"' and vendorid='"+vendorid+"' ");
		}
		try{
			preparedStatement = connection.prepareStatement(buffer.toString());
			ResultSet rs = preparedStatement.executeQuery();
			if(rs.next()){
				result = rs.getString(1);
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}


	public int changeWard_in_apm_invoice_assesments_byinvid(String id, String wardid) {
		PreparedStatement ps= null;
		int x=0;
		String sql="update  apm_invoice_assesments set wardid='"+wardid+"' where invoiceid='"+id+"' ";
		try {
			ps= connection.prepareStatement(sql);
			x=ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return x;
	}
	public int getLedgerSheetId(String ledgerid, String procurementid, String colmnname) {
		int res=0;
		try {
			String sql ="select id from ledger_sheet where ledgerid='"+ledgerid+"' and "+colmnname+"='"+procurementid+"' ";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				res = rs.getInt(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return res;

	}

	public int updateMasterChargeInAsseessment(String id, String mastercharge) {
		PreparedStatement ps= null;
		int x=0;
		String sql="update  apm_invoice_assesments set masterchargetype='"+mastercharge+"' where id='"+id+"' ";
		try {
			ps= connection.prepareStatement(sql);
			x=ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return x;
	}
	
	public int isCompulasryConsultant(String chargetype){
		int res=0;
		PreparedStatement ps= null;
		try {
			String sql="select compulsay_consultant from apm_newchargetype where name='"+chargetype+"' ";
			ps= connection.prepareStatement(sql);
			ResultSet rs=ps.executeQuery();
			while(rs.next()){
				res= rs.getInt(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return res;
	}

	public int updateofficicaledgerstatus(int saveledger) {
		PreparedStatement preparedStatement = null;
		int result = 0;
		String sql = "update ledger_sheet set ofcialledgerid=1 where id="+saveledger+" ";
		
		try{
			preparedStatement = connection.prepareStatement(sql);
			result = preparedStatement.executeUpdate();
			
		}catch (Exception e) {
			// TODO: handle exception
		}
		return 0;
	}
	
	
	private int saveToPhysicalHISPayment(String invoiceid,String advrefid,String clientid,String commencing,String paymode, String amount,String itype,String payment_id){
		int res=0;
		try {
			int itype1=getItype(invoiceid);
			
			String sql="insert into his_payment_record_physical(invoiceid,adv_ref_id,clientid,paymode,amount,itype,commencing,payment_id) values(?,?,?,?,?,?,?,?)";
			PreparedStatement ps= connection.prepareStatement(sql);
			ps.setString(1, invoiceid);
			ps.setString(2, advrefid);
			ps.setString(3, clientid);
			ps.setString(4, paymode);
			ps.setString(5, amount);
			ps.setInt(6, itype1);
			ps.setString(7, commencing);
			ps.setString(8, payment_id);
			res= ps.executeUpdate();
			
			int his_payId=0;
			if(res>0){
				ResultSet resultSet=ps.getGeneratedKeys();
				if(resultSet.next()){
					his_payId=resultSet.getInt(1);
				}
				
				AccountsDAO accountsDAO= new JDBCAccountsDAO(connection);
				accountsDAO.genearteSeqOfPaymentByFinancialYear(his_payId);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return res;
	}
	
	private int getItype(String invoiceid){
		int res=0;
		try {
			PreparedStatement ps= connection.prepareStatement("select itype from apm_charges_invoice where id='"+invoiceid+"'");
			ResultSet rs= ps.executeQuery();
			while(rs.next()){
				res= rs.getInt(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return res;
	}

	public int updateLedgerSheetExpenceID(int saveledger, int expenceparentid) {
		int res =0;
		try {
			String sql="update ledger_sheet set expenceid='"+expenceparentid+"' where id='"+saveledger+"'";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			res = preparedStatement.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return res;
	}


	public int updateInvoicePost(String date4, String userId, String podate) {
		PreparedStatement preparedStatement = null;
		StatementDAO statementDAO = new JDBCStatementDAO(connection);
		String sql = "";
		int result = 0;
		sql = "select id,payby,date,clientid,debit,deliverstatus,discount,isdeleted,disctype,discamt,cancelNotes,disc_approve,disc_request,iclosed,ipost from apm_charges_invoice where ipost=0 and date='"+date4+"' and itype=1 ";
		try{
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()){
				double result1 = 0.0;
				int disctype = rs.getInt(9);
				double discamt = rs.getDouble(10);
				double total = rs.getDouble(5);
				double discount = rs.getDouble(7);
				double r1 = (total*discount)/100;
				if(disctype==1){
					r1 = discamt;
				}
				total = total-r1;
				result1 = result1 + total;
				
				double creditAmount = getCreditAmount(rs.getDouble(1));
				//if refund against invoice
				double refundAmt = statementDAO.getRefundAmtAgainsInvoice(rs.getInt(1));
				creditAmount = creditAmount - refundAmt;
				double balance = result1 - creditAmount;
				if(balance==0){
					result = updateIpost(rs.getString(1), podate);
				}
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
		return result;
	}	
		

	public CompleteAppointment getChargeInvoiceData(String invoiceid) {
		CompleteAppointment completeAppointment=new CompleteAppointment();
		try {
			String sql="select debit,disctype, discamt,disc_approve from apm_charges_invoice where id='"+invoiceid+"' ";
			PreparedStatement ps= connection.prepareStatement(sql);
			ResultSet rs= ps.executeQuery();
			while(rs.next()){
				completeAppointment.setDebit(rs.getDouble(1));
				completeAppointment.setDisc_type(rs.getString(2));
				completeAppointment.setDisc_amount(rs.getString(3));
				completeAppointment.setDisc_approve(rs.getInt(4));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return completeAppointment;
	}

	public int updategivendisc(String invoiceid,double actualamt) {
		int res =0;
		try {
			String sql="update apm_charges_invoice set disctype=0,discamt=0,disc_approve=0,disc_approve_userid='', disc_approve_date='', disc_request=0, disc_given_userid='', disc_given_date='' where id='"+invoiceid+"'";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			res = preparedStatement.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return res;
	}

	public int deletediscreq(String invoiceid) {
		PreparedStatement preparedStatement = null;
		int result = 0;
		String sql = "delete from discount_request where invoiceid="+invoiceid+" ";
		
		try{
			preparedStatement = connection.prepareStatement(sql);
			result = preparedStatement.executeUpdate();
			
		}catch (Exception e) {
			e.printStackTrace();
		}

		return result;
	}

	public ArrayList<Accounts> getPaymentList(int invoiceid) {
		PreparedStatement preparedStatement = null;
		ArrayList<Accounts>list = new ArrayList<Accounts>();
		String sql = "select id,date,payment,paymode from apm_charges_payment where chargeinvoiceid = "+invoiceid+" ";
		
		try{
			
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()){
				Accounts a = new Accounts();
				a.setId(rs.getInt(1));
				a.setCommencing(rs.getString(2));
				a.setAmountx(rs.getString(3));
				a.setPaymentmode(rs.getString(4));
				
				list.add(a);
			}
		}catch (Exception e) {
			// TODO: handle exception
		}
		return list;
	}

	public String getInvoiceDoctorid(int invoiceid) {
		PreparedStatement preparedStatement = null;
		String result = "";
		String sql = "select practid from apm_charges_invoice where id = "+invoiceid+" ";
		
		try{
			
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			if(rs.next()){
				result = rs.getString(1);
			}
		}catch (Exception e) {
			// TODO: handle exception
		}
		return result;
	}

	public boolean checkInvoicerequset(String selectedInvoiceid) {
		PreparedStatement preparedStatement = null;
		boolean result = false;
		String sql = "select * from apm_invoice_assesments where invoiced='"+selectedInvoiceid+"' and discreq>0  group by invoiced ";
		
		try{
			
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			if(rs.next()){
				result = true;
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public boolean checkRefundAgainstInvoice(String selectedInvoiceid) {
		PreparedStatement preparedStatement = null;
		boolean result = false;
		String sql = "select * from refund_request_parent where manualinvoiceid='"+selectedInvoiceid+"' and status=2 ";
		
		try{
			
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			if(rs.next()){
				result = true;
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public int getMobStatusFromAppoitmentId(String appointmentid, String patientid) {
		PreparedStatement preparedStatement = null;
		int result = 0;
		String sql = "select mobstatus from apm_available_slot where id='"+appointmentid+"' and clientId='"+patientid+"' ";
		
		try{
			
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			if(rs.next()){
				result = rs.getInt(1);
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public ArrayList<Accounts> gettransactionListNew(String invoiceid, String clientId, int prepaymentid) {

		PreparedStatement preparedStatement = null;
		DateTimeUtils dateTimeUtils = new DateTimeUtils();
		StringBuffer buffer=new StringBuffer();
		ArrayList<Accounts>list = new ArrayList<Accounts>();
//		String sql = "select id,chargeinvoiceid,date,paymode,payment,crdinvoiceid from apm_charges_payment where chargeinvoiceid = "+invoiceid+" and payment!='prepayment' ";
//		String sql="select id,chargeinvoiceid,date,paymode,payment,crdinvoiceid from apm_charges_payment where chargeinvoiceid <= "+invoiceid+" and paymode='prepayment' and clientid="+clientId+" and prepaymentid>="+prepaymentid+" ";
		buffer.append("select id,chargeinvoiceid,date,paymode,payment,crdinvoiceid from apm_charges_payment where chargeinvoiceid <= "+invoiceid+" and paymode='prepayment' and clientid="+clientId+" ");
		if(prepaymentid!=0){
		buffer.append("and prepaymentid>="+prepaymentid+"");
		}
		try{
			preparedStatement = connection.prepareStatement(buffer.toString());
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()){
				Accounts accounts = new Accounts();
				accounts.setId(rs.getInt(1));
				accounts.setInvoiceNo(rs.getString(2));
				accounts.setDate(rs.getString(3));
				
				String reqdate = "";
				if(rs.getString(3)!=null){
					String[] data = rs.getString(3).split(" ");
					reqdate = DateTimeUtils.getCommencingDate1(data[0]) +" "+data[1];
				}
				accounts.setFromDate(reqdate);
				
				accounts.setPaymentmode(rs.getString(4));
				accounts.setAmount(rs.getDouble(5));
				
				if(rs.getInt(6)>0){
					double creditAmount = getReportCreditAmount(rs.getInt(6));
					accounts.setCreditAmount(creditAmount);
				}else{
					accounts.setCreditAmount(0.0);
				}
				
				//Decimal Amount
				accounts.setAmountx(dateTimeUtils.changeFormat(rs.getDouble(5)));
				
				list.add(accounts);
			}
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	
	}

	public Master searchChargeByCode(String searchByCode, String ipdtpid) {
		Master master = new Master();
		try {
			StringBuffer buffer = new StringBuffer();
			buffer.append("select id,chargeType from apm_appointment_type where code=? and tpid=?");
			
			PreparedStatement preparedStatement = connection.prepareStatement(buffer.toString());
			preparedStatement.setString(1, searchByCode);
			preparedStatement.setString(2, ipdtpid);
			ResultSet resultSet = preparedStatement.executeQuery();
			while(resultSet.next()){
				master.setId(resultSet.getInt(1));
				master.setChargetype(resultSet.getString(2));
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return master;
	}

	@Override
	public int getMasterChargeIdFromName(String chargetype) {
		int res=0;
		try {
			String sql ="select id from apm_newChargeType where name=?";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, chargetype);
			ResultSet resultSet = preparedStatement.executeQuery();
			while(resultSet.next()){
				res = resultSet.getInt(1);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return res;
	}

	@Override
	public void getChargeAssesmentEntry(int invoiceid) {
		try {
			AccountsDAO accountsDAO=new JDBCAccountsDAO(connection);
			String sql ="select * from apm_charges_assesment where chargeinvoiceid="+invoiceid+"";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			ResultSet resultSet = preparedStatement.executeQuery();
			
			if(resultSet.next()){
				
			}else {
				Accounts accounts = getInvoiceDetails(invoiceid);
				String itype=accounts.getItype();
				String type="OB";
				if(itype==null){
					itype="";
				}
				if(itype.equals("2")){
					type="IB";
				}else if(itype.equals("3")){
					type="INVB";
				}else if(itype.equals("6")){
					type="VACB";
				}else if(itype.equals("7")){
					type="PRB";
				}
				
				
				//type=getInvoicePrefix(itype);
				SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
			    Calendar cal = Calendar.getInstance();
			    String todaydate = dateFormat.format(cal.getTime());
			    String endfinancialyeardate="31-03-"+todaydate.split("-")[2];
			    
			    SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
	            Date today = sdf.parse(todaydate);
	            Date lastdayoffinyr = sdf.parse(endfinancialyeardate);
			    String yearrangenew="";
			    if(today.after(lastdayoffinyr)){
			    	int year=Integer.parseInt(todaydate.split("-")[2]);
			    	String yearrange=year+"-"+(year+1);
			    	yearrangenew=yearrange;
			    	
			    }else{
			    	int year=Integer.parseInt(todaydate.split("-")[2]);
			    	String yearrange=(year-1)+"-"+year; 
			    	yearrangenew=yearrange;
			    }
				
				String maxfrominvoice=accounts.getNewsr().split("/")[1];
			    int maxSeqNo=accountsDAO.getMaxBillSeqNo(yearrangenew,type);
			    
			    if(DateTimeUtils.convertToInteger(maxfrominvoice)==maxSeqNo) {
			    	int sequenceid=getSquenceId(yearrangenew,type,maxSeqNo);
			    	updateSequnceNo(sequenceid);
			    
					String sql1="update apm_charges_invoice set clientid=0 where id="+invoiceid+"";
					PreparedStatement preparedStatement2=connection.prepareStatement(sql1);
					int res=preparedStatement2.executeUpdate();
					
			    	
			    }
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void updateSequnceNo(int sequenceid) {
		PreparedStatement preparedStatement = null;
		int result = 0;
		String sql = "update invoice_seq_table set maxseq=? where id = ?  ";
		
		try{
			
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, "0");
			preparedStatement.setInt(2, sequenceid);
			
			result = preparedStatement.executeUpdate();
			
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	private int getSquenceId(String yearrangenew, String type, int maxSeqNo) {
		int res=0;
		try {
			String sql ="select id from invoice_seq_table where itype='"+type+"' and year='"+yearrangenew+"' and maxseq="+maxSeqNo+" order by id desc limit 1";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			ResultSet resultSet = preparedStatement.executeQuery();
			while(resultSet.next()){
				res = resultSet.getInt(1);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return res;
	}

	@Override
	public int updateremark(String invoiceid, String remark) {
		PreparedStatement preparedStatement = null;
		int result = 0;
		String sql = "update discount_request set remark=? where invoiceid = ?  ";
		
		try{
			
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, remark);
			preparedStatement.setString(2, invoiceid);
			
			result = preparedStatement.executeUpdate();
			
		}catch(Exception e){
			e.printStackTrace();
		}
		return result;
	}



}
