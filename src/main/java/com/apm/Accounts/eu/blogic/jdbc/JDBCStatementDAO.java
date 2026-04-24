package com.apm.Accounts.eu.blogic.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.apm.Accounts.eu.bi.AccountsDAO;
import com.apm.Accounts.eu.bi.StatementDAO;
import com.apm.Accounts.eu.entity.Accounts;
import com.apm.Accounts.eu.entity.Charges;
import com.apm.Accounts.eu.entity.ChargesInvoice;
import com.apm.Accounts.eu.entity.Invoice;
import com.apm.DiaryManagement.eu.bi.ClientDAO;
import com.apm.DiaryManagement.eu.bi.CompleteAptmDAO;
import com.apm.DiaryManagement.eu.blogic.jdbc.JDBCClientDAO;
import com.apm.DiaryManagement.eu.blogic.jdbc.JDBCCompleteAptmDAO;
import com.apm.DiaryManagement.eu.entity.CompleteAppointment;
import com.apm.Registration.eu.bi.UserProfileDAO;
import com.apm.Registration.eu.blogic.jdbc.JDBCUserProfileDAO;
import com.apm.common.eu.blogic.jdbc.JDBCBaseDAO;
import com.apm.common.utils.DateTimeUtils;
import com.apm.common.utils.Pagination;
import com.apm.main.common.constants.Constants;

public class JDBCStatementDAO extends JDBCBaseDAO implements StatementDAO{
	
	public JDBCStatementDAO(Connection connection){
		this.connection = connection;
	}

	public int getTotalAccountCount(String clientId, String payby,
			String transactionType, String location, String thirdParty) {
		PreparedStatement preparedStatement = null;
		int result = 0;
		
		
		StringBuffer sql = new StringBuffer();
		
		if(payby.equals(Constants.PAY_BY_CLIENT)){
			sql.append("SELECT count(DISTINCT invoiceid) FROM apm_invoice_assesments ");
			sql.append("inner join apm_invoice on apm_invoice.id = apm_invoice_assesments.invoiceid ");
			sql.append("where apm_invoice_assesments.clientid="+clientId+" and apm_invoice_assesments.paybuy = 0 and apm_invoice.chargetype like('%"+transactionType+"%') and apm_invoice.location like ('%"+location+"%') and apm_invoice_assesments.thirdPartyId like ('"+thirdParty+"')");
			
			
		}else if(payby.equals(Constants.PAY_BY_THIRD_PARTY)){
			sql.append("SELECT count(DISTINCT invoiceid) FROM apm_invoice_assesments ");
			sql.append("inner join apm_invoice on apm_invoice.id = apm_invoice_assesments.invoiceid ");
			sql.append("where apm_invoice_assesments.clientid="+clientId+" and apm_invoice_assesments.paybuy = 1 and apm_invoice.chargetype like('%"+transactionType+"%') and apm_invoice.location like ('%"+location+"%') and apm_invoice_assesments.thirdPartyId like ('"+thirdParty+"')");
			
		}else{
			sql.append("SELECT count(DISTINCT invoiceid) FROM apm_invoice_assesments ");
			sql.append("inner join apm_invoice on apm_invoice.id = apm_invoice_assesments.invoiceid ");
			sql.append("where apm_invoice_assesments.clientid="+clientId+" and apm_invoice.chargetype like('%"+transactionType+"%') and apm_invoice.location like ('%"+location+"%') and apm_invoice_assesments.thirdPartyId like ('"+thirdParty+"')");
			
		}
		try{
			preparedStatement = connection.prepareStatement(sql.toString());
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()){
				result = rs.getInt(1);
			}
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public ArrayList<Accounts> getAccountPendingList(String clientId,
			String payby, Pagination pagination, String transactionType,
			String location, String thirdParty) {
		double debitTotal=0.0;
		double creditTotal = 0.0;
		double balanceTotal = 0.0;
		String selctedTransType="";
		PreparedStatement preparedStatement = null;
		DateTimeUtils dateTimeUtils = new DateTimeUtils();
		ArrayList<Accounts>list = new ArrayList<Accounts>();
		StringBuffer sql = new StringBuffer();
		String pending= "Pending Payment";String paid = "Payments";
		if(transactionType.equals(pending) || transactionType.equals(paid)){
			selctedTransType = transactionType;
			transactionType = "";
		}
		if(payby.equals(Constants.PAY_BY_CLIENT)){
			sql.append("SELECT commencing,user,apmtType,sum(charge),invoiceid,paid,count(apm_invoice_assesments.id),apm_invoice.practitionername,apm_invoice_assesments.paybuy,apm_invoice_assesments.clientId,chargetype,howpaid,apm_invoice.appointmentid  FROM apm_invoice_assesments ");
			sql.append("inner join apm_invoice on apm_invoice.id = apm_invoice_assesments.invoiceid ");
			sql.append("where apm_invoice_assesments.clientid="+clientId+" and apm_invoice_assesments.paybuy = 0 and apm_invoice.chargetype like('%"+transactionType+"%') and apm_invoice.location like ('%"+location+"%') and apm_invoice_assesments.thirdPartyId like ('"+thirdParty+"') ");
			sql.append("group by invoiceid ");
			
		}else if(payby.equals(Constants.PAY_BY_THIRD_PARTY)){
			sql.append("SELECT commencing,user,apmtType,sum(charge),invoiceid,paid,count(apm_invoice_assesments.id),apm_invoice.practitionername,apm_invoice_assesments.paybuy,apm_invoice_assesments.clientId,chargetype,howpaid,apm_invoice.appointmentid FROM apm_invoice_assesments ");
			sql.append("inner join apm_invoice on apm_invoice.id = apm_invoice_assesments.invoiceid ");
			sql.append("where apm_invoice_assesments.clientid="+clientId+" and apm_invoice_assesments.paybuy = 1 and apm_invoice.chargetype like('%"+transactionType+"%') and apm_invoice.location like ('%"+location+"%') and apm_invoice_assesments.thirdPartyId like ('"+thirdParty+"') ");
			sql.append("group by invoiceid ");
		}else{
			sql.append("SELECT commencing,user,apmtType,sum(charge),invoiceid,paid,count(apm_invoice_assesments.id),apm_invoice.practitionername,apm_invoice_assesments.paybuy,apm_invoice_assesments.clientId,chargetype,howpaid,apm_invoice.appointmentid  FROM apm_invoice_assesments ");
			sql.append("inner join apm_invoice on apm_invoice.id = apm_invoice_assesments.invoiceid ");
			sql.append("where apm_invoice_assesments.clientid="+clientId+" and apm_invoice.chargetype like('%"+transactionType+"%') and apm_invoice.location like ('%"+location+"%') and apm_invoice_assesments.thirdPartyId like ('"+thirdParty+"') ");
			sql.append("group by invoiceid ");
		}
		String sql1 = sql.toString();
		sql1 = pagination.getSQLQuery(sql.toString());
		
		try{
			preparedStatement = connection.prepareStatement(sql1);
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()){
				Accounts accounts = new Accounts();
				accounts.setCommencing(rs.getString(1));
				accounts.setClientName(rs.getString(2));
				accounts.setAppointmentType(rs.getString(3));
				accounts.setCreditCharge(dateTimeUtils.changeFormat(Double.parseDouble(rs.getString(4))));
				accounts.setInvoiceid(rs.getInt(5));
				accounts.setPaid(rs.getBoolean(6));
				
				accounts.setNumberOfChages(rs.getInt(7));
				accounts.setPractitionerName(rs.getString(8));
				accounts.setPayby(rs.getString(9));
				if(rs.getString(9).equals("1")){
					accounts.setWhoPay("Third Party");
				}
				else{
					accounts.setWhoPay("Self");
				}
				accounts.setClientid(rs.getInt(10));
				accounts.setChargeType(rs.getString(11));
				accounts.setHowPaid(rs.getString(12));
				accounts.setApptId(rs.getString(13));
				String treamentEpisodeName = getTreatmentEpisodeName(rs.getString(13));
				accounts.setTreatmentEpisodeName(treamentEpisodeName);
				int credit = getCreditAmount(rs.getString(5),rs.getString(9));
				double totalPayment = getPaymentAmount(rs.getInt(5),rs.getString(9));
				double d1 = getDebitTotal(rs.getInt(5),rs.getString(9),clientId);
				
				double debit = totalPayment - credit;
				accounts.setPayAmount(credit);
				accounts.setTotalAmount(credit+debit);
				double total = credit+debit;
				
				accounts.setDebitAmount(debit);
				
				debitTotal = total + debitTotal;
				creditTotal = creditTotal + credit;
				balanceTotal = balanceTotal + debit;
				accounts.setDebitTotal(debitTotal);
				accounts.setCreditTotal(creditTotal);
				accounts.setBalanceTotal(balanceTotal);
				
				//Decimal Amount
				accounts.setPayAmountx(dateTimeUtils.changeFormat(credit));
				accounts.setTotalAmountx(dateTimeUtils.changeFormat(total));
				accounts.setDebitAmountx(dateTimeUtils.changeFormat(debit));
				accounts.setDebitTotalx(dateTimeUtils.changeFormat(debitTotal));
				accounts.setCreditTotalx(dateTimeUtils.changeFormat(creditTotal));
				accounts.setBalanceTotalx(dateTimeUtils.changeFormat(balanceTotal));
				
				accounts.setSelectedPayby(payby);
				
				if(debit!=0){
				list.add(accounts);
				}
				
			}
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	private double getDebitTotal(int int1, String payby, String clientId) {
		PreparedStatement preparedStatement = null;
		int result = 0;
		String sql = "SELECT debit FROM apm_charges_invoice where where clientid = "+clientId+"  and payby like ('%"+payby+"%')";
		
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

	private double getPaymentAmount(int invoiceid, String paybuy) {
		PreparedStatement preparedStatement = null;
		double result = 0;
		String sql = "SELECT charge,quantity FROM apm_invoice_assesments where invoiceid = "+invoiceid+" and paybuy = "+paybuy+"";
		
		try{
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()){
				double charge = rs.getDouble(1) * rs.getInt(2);
				result = result + charge;
			}
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}

	private int getCreditAmount(String invoiceid, String payBuy) {
		PreparedStatement preparedStatement = null;
		int result = 0;
		String sql = "SELECT sum(amount) FROM apm_invoice_payment where invoice_id = "+invoiceid+" and payBuy = "+payBuy+" ";
		
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

	private String getTreatmentEpisodeName(String id) {
		String treatmentEpsodeId = null;
		String treatmentEpsodeName = null;
		PreparedStatement preparedStatement = null;
		
		String sql = "select treatmentEpisodeId from apm_available_slot where id = "+id+"";
		try{
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()){
				
				treatmentEpsodeId = rs.getString(1);
				treatmentEpsodeName = getTreatmentEpisode(treatmentEpsodeId);
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

	

	public ArrayList<Accounts> getAccountPaidList(String clientId,
			String payby, Pagination pagination, String transactionType,
			String location, String thirdParty) {
		double debitTotal=0.0;
		double creditTotal = 0.0;
		double balanceTotal = 0.0;
		String selctedTransType="";
		PreparedStatement preparedStatement = null;
		DateTimeUtils dateTimeUtils = new DateTimeUtils();
		ArrayList<Accounts>list = new ArrayList<Accounts>();
		StringBuffer sql = new StringBuffer();
		String pending= "Pending Payment";String paid = "Payments";
		if(transactionType.equals(pending) || transactionType.equals(paid)){
			selctedTransType = transactionType;
			transactionType = "";
		}
		if(payby.equals(Constants.PAY_BY_CLIENT)){
			sql.append("SELECT commencing,user,apmtType,sum(charge),invoiceid,paid,count(apm_invoice_assesments.id),apm_invoice.practitionername,apm_invoice_assesments.paybuy,apm_invoice_assesments.clientId,chargetype,howpaid,apm_invoice.appointmentid  FROM apm_invoice_assesments ");
			sql.append("inner join apm_invoice on apm_invoice.id = apm_invoice_assesments.invoiceid ");
			sql.append("where apm_invoice_assesments.clientid="+clientId+" and apm_invoice_assesments.paybuy = 0 and apm_invoice.chargetype like('%"+transactionType+"%') and apm_invoice.location like ('%"+location+"%') and apm_invoice_assesments.thirdPartyId like ('"+thirdParty+"') ");
			sql.append("group by invoiceid ");
			
		}else if(payby.equals(Constants.PAY_BY_THIRD_PARTY)){
			sql.append("SELECT commencing,user,apmtType,sum(charge),invoiceid,paid,count(apm_invoice_assesments.id),apm_invoice.practitionername,apm_invoice_assesments.paybuy,apm_invoice_assesments.clientId,chargetype,howpaid,apm_invoice.appointmentid FROM apm_invoice_assesments ");
			sql.append("inner join apm_invoice on apm_invoice.id = apm_invoice_assesments.invoiceid ");
			sql.append("where apm_invoice_assesments.clientid="+clientId+" and apm_invoice_assesments.paybuy = 1 and apm_invoice.chargetype like('%"+transactionType+"%') and apm_invoice.location like ('%"+location+"%') and apm_invoice_assesments.thirdPartyId like ('"+thirdParty+"') ");
			sql.append("group by invoiceid ");
		}else{
			sql.append("SELECT commencing,user,apmtType,sum(charge),invoiceid,paid,count(apm_invoice_assesments.id),apm_invoice.practitionername,apm_invoice_assesments.paybuy,apm_invoice_assesments.clientId,chargetype,howpaid,apm_invoice.appointmentid  FROM apm_invoice_assesments ");
			sql.append("inner join apm_invoice on apm_invoice.id = apm_invoice_assesments.invoiceid ");
			sql.append("where apm_invoice_assesments.clientid="+clientId+" and apm_invoice.chargetype like('%"+transactionType+"%') and apm_invoice.location like ('%"+location+"%') and apm_invoice_assesments.thirdPartyId like ('"+thirdParty+"') ");
			sql.append("group by invoiceid ");
		}
		String sql1 = sql.toString();
		sql1 = pagination.getSQLQuery(sql.toString());
		
		try{
			preparedStatement = connection.prepareStatement(sql1);
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()){
				Accounts accounts = new Accounts();
				accounts.setCommencing(rs.getString(1));
				accounts.setClientName(rs.getString(2));
				accounts.setAppointmentType(rs.getString(3));
				accounts.setCreditCharge(dateTimeUtils.changeFormat(Double.parseDouble(rs.getString(4))));
				accounts.setInvoiceid(rs.getInt(5));
				accounts.setPaid(rs.getBoolean(6));
				
				accounts.setNumberOfChages(rs.getInt(7));
				accounts.setPractitionerName(rs.getString(8));
				accounts.setPayby(rs.getString(9));
				if(rs.getString(9).equals("1")){
					accounts.setWhoPay("Third Party");
				}
				else{
					accounts.setWhoPay("Self");
				}
				accounts.setClientid(rs.getInt(10));
				accounts.setChargeType(rs.getString(11));
				accounts.setHowPaid(rs.getString(12));
				accounts.setApptId(rs.getString(13));
				String treamentEpisodeName = getTreatmentEpisodeName(rs.getString(13));
				accounts.setTreatmentEpisodeName(treamentEpisodeName);
				int credit = getCreditAmount(rs.getString(5),rs.getString(9));
				double totalPayment = getPaymentAmount(rs.getInt(5),rs.getString(9));
				double debit = totalPayment - credit;
				accounts.setPayAmount(credit);
				accounts.setTotalAmount(credit+debit);
				double total = credit+debit;
				
				accounts.setDebitAmount(debit);
				
				debitTotal = total + debitTotal;
				creditTotal = creditTotal + credit;
				balanceTotal = balanceTotal + debit;
				accounts.setDebitTotal(debitTotal);
				accounts.setCreditTotal(creditTotal);
				accounts.setBalanceTotal(balanceTotal);
				
				//Decimal Amount
				accounts.setPayAmountx(dateTimeUtils.changeFormat(credit));
				accounts.setTotalAmountx(dateTimeUtils.changeFormat(total));
				accounts.setDebitAmountx(dateTimeUtils.changeFormat(debit));
				accounts.setDebitTotalx(dateTimeUtils.changeFormat(debitTotal));
				accounts.setCreditTotalx(dateTimeUtils.changeFormat(creditTotal));
				accounts.setBalanceTotalx(dateTimeUtils.changeFormat(balanceTotal));
				
				accounts.setSelectedPayby(payby);
				
				if(debit==0){
					list.add(accounts);
				}
				
			}
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	public ArrayList<Accounts> getAccountList(String clientId, String payby,
			Pagination pagination, String transactionType, String location,
			String thirdParty,String fromDate,String toDate,String ipdid, String addOnTableName) {
		
		String selctedTransType="";
		PreparedStatement preparedStatement = null;
		DateTimeUtils dateTimeUtils = new DateTimeUtils();
		ArrayList<Accounts>list = new ArrayList<Accounts>();
		StringBuffer sql = new StringBuffer();
		String pending= "Pending Payment";String paid = "Payments";
		
		String chargeType = "Submit";
		
		if(payby.equals(Constants.PAY_BY_CLIENT)){
			sql.append("SELECT commencing,user,apmtType,sum(charge),invoiceid,paid,count(apm_invoice_assesments.id),"
					+ "apm_invoice.practitionername,apm_invoice_assesments.paybuy,apm_invoice_assesments.clientId,"
					+ "chargetype,howpaid,apm_invoice.appointmentid,apm_invoice.clientid,location,"
					+ "apm_invoice.shared_amt, apm_invoice.isshared, apm_invoice.indivisual_shared  "
					+ "FROM apm_invoice_assesments"+addOnTableName+" as apm_invoice_assesments ");
			sql.append("inner join apm_invoice on apm_invoice.id = apm_invoice_assesments.invoiceid ");
			sql.append("where apm_invoice_assesments.clientid="+clientId+" and apm_invoice_assesments.paybuy = 0 "
					+ "and apm_invoice.chargetype not like('%"+chargeType+"%') "
							+ "and apm_invoice.chargetype like('%"+transactionType+"%')   ");
			sql.append("and commencing between '"+fromDate+"' and '"+toDate+"' ");
			if(!ipdid.equals("")){
				sql.append("  and apm_invoice_assesments.ipdid='"+ipdid+"'  ");
			}
			sql.append("group by invoiceid order by apm_invoice_assesments.ipdid desc ");
			
		}else if(payby.equals(Constants.PAY_BY_THIRD_PARTY)){
			sql.append("SELECT commencing,user,apmtType,sum(charge),invoiceid,paid,count(apm_invoice_assesments.id),"
					+ "apm_invoice.practitionername,apm_invoice_assesments.paybuy,apm_invoice_assesments.clientId,"
					+ "chargetype,howpaid,apm_invoice.appointmentid,apm_invoice.clientid,location,"
					+ "apm_invoice.shared_amt, apm_invoice.isshared, apm_invoice.indivisual_shared "
					+ "FROM apm_invoice_assesments"+addOnTableName+" as apm_invoice_assesments ");
			sql.append("inner join apm_invoice on apm_invoice.id = apm_invoice_assesments.invoiceid ");
			sql.append("where apm_invoice_assesments.clientid="+clientId+" and apm_invoice_assesments.paybuy = 1 "
					+ "and apm_invoice.chargetype not like('%"+chargeType+"%') and apm_invoice.chargetype like('%"+transactionType+"%')    ");
			sql.append("and commencing between '"+fromDate+"' and '"+toDate+"' ");
			if(!ipdid.equals("")){
				sql.append("  and apm_invoice_assesments.ipdid='"+ipdid+"'  ");
			}
			sql.append("group by invoiceid  order by apm_invoice_assesments.ipdid desc ");
		}else{
			sql.append("SELECT commencing,user,apmtType,sum(charge),invoiceid,paid,count(apm_invoice_assesments.id),"
					+ "apm_invoice.practitionername,apm_invoice_assesments.paybuy,apm_invoice_assesments.clientId,"
					+ "chargetype,howpaid,apm_invoice.appointmentid,apm_invoice.clientid,location,apm_invoice.shared_amt, "
					+ "apm_invoice.isshared, apm_invoice.indivisual_shared  "
					+ "FROM apm_invoice_assesments"+addOnTableName+" as apm_invoice_assesments ");
			sql.append("inner join apm_invoice on apm_invoice.id = apm_invoice_assesments.invoiceid ");
			if(!fromDate.equals("") && !toDate.equals("")){
				sql.append("where apm_invoice_assesments.clientid="+clientId+" and apm_invoice.chargetype not like('%"+chargeType+"%') "
						+ "and apm_invoice.chargetype like('%"+transactionType+"%')   "
								+ "and commencing between '"+fromDate+"' AND '"+toDate+"' ");
			}else{
				sql.append("where apm_invoice_assesments.clientid="+clientId+" and apm_invoice.chargetype not like('%"+chargeType+"%') "
						+ "and apm_invoice.chargetype like('%"+transactionType+"%')   ");
			}
			
			if(!thirdParty.equals("")){
				sql.append("and apm_invoice_assesments.thirdPartyId="+thirdParty+"  ");
			}
			if(!ipdid.equals("")){
				sql.append("  and apm_invoice_assesments.ipdid='"+ipdid+"'  ");
			}
			sql.append("group by invoiceid order by commencing,apm_invoice_assesments.ipdid desc ");
		}
		
		
		
		String sql1 = sql.toString();
		
		//int totalcount = getChargesTotalCount(sql1);
		
	
		
		//sql1 = pagination.getSQLQuery(sql.toString());
		
		try{
			preparedStatement = connection.prepareStatement(sql1);
			ResultSet rs = preparedStatement.executeQuery();
			
			rs.last();
			int totalcount =rs.getRow(); 
			rs.beforeFirst();
			int i = 1;
			
			int credit = 0;
			double debit = 0;
			double totalPayment = 0;
			double total = 0;
			
			double debitTotal=0.0;
			double creditTotal = 0.0;
			double balanceTotal = 0.0;
			
			while(rs.next()){
				
				if(transactionType.equals("")){
					 credit = 0;
					 debit = 0;
					 totalPayment = 0;
					 total = 0;
					
					 debitTotal=0.0;
					 creditTotal = 0.0;
					 balanceTotal = 0.0;
				}
			
				
				Accounts accounts = new Accounts();
				accounts.setCommencing(dateTimeUtils.changeDateFormatToddmmyyyy(rs.getString(1)));
				
				accounts.setAppointmentType(rs.getString(3));
				if(rs.getString(4)!=null){
					accounts.setCreditCharge(dateTimeUtils.changeFormat(Double.parseDouble(rs.getString(4))));
				}else{
					accounts.setCreditCharge("0.00");
				}
				
				accounts.setInvoiceid(rs.getInt(5));
				accounts.setPaid(rs.getBoolean(6));
				
				accounts.setNumberOfChages(rs.getInt(7));
				accounts.setPractitionerName(rs.getString(8));
				accounts.setPayby(rs.getString(9));
				if(rs.getString(9).equals("1")){
					accounts.setWhoPay("Third Party");
					CompleteAptmDAO completeAptmDAO = new JDBCCompleteAptmDAO(connection);
				    CompleteAppointment	completeAppointment = completeAptmDAO.getInsuranceCompanyName(rs.getString(14));
				    accounts.setClientName(completeAppointment.getInsuranceCompanyName());
				}
				else{
					accounts.setWhoPay("Self");
					accounts.setClientName(rs.getString(2));
				}
				accounts.setClientid(rs.getInt(10));
				accounts.setChargeType(rs.getString(11));
				accounts.setHowPaid(rs.getString(12));
				accounts.setApptId(rs.getString(13));
				
				String locationName = getLoactionName(rs.getInt(15));
				accounts.setLocation(locationName);
				
				String treamentEpisodeName = getTreatmentEpisodeName(rs.getString(13));
				accounts.setTreatmentEpisodeName(treamentEpisodeName);
				 credit = getCreditAmount(rs.getString(5),rs.getString(9));
				 totalPayment = getPaymentAmount(rs.getInt(5),rs.getString(9));
				 debit = totalPayment - credit;
				accounts.setPayAmount(credit);
				accounts.setTotalAmount(credit+debit);
				 total = credit+debit;
				
				accounts.setDebitAmount(debit);
				
				debitTotal = total + debitTotal;
				creditTotal = creditTotal + credit;
				balanceTotal = balanceTotal + debit;
				accounts.setDebitTotal(debitTotal);
				accounts.setCreditTotal(creditTotal);
				accounts.setBalanceTotal(balanceTotal);
				
				//Decimal Amount
				accounts.setPayAmountx(dateTimeUtils.changeFormat(credit));
				accounts.setTotalAmountx(dateTimeUtils.changeFormat(total));
				accounts.setDebitAmountx(dateTimeUtils.changeFormat(debit));
				accounts.setDebitTotalx(dateTimeUtils.changeFormat(debitTotal));
				accounts.setCreditTotalx(dateTimeUtils.changeFormat(creditTotal));
				accounts.setBalanceTotalx(dateTimeUtils.changeFormat(balanceTotal));
				
				accounts.setSelectedPayby(payby);
				
				accounts.setTotalChargesCount(totalcount);
				
				if(transactionType.equals("")){
					if(i == totalcount){
						ArrayList<Invoice>invoiceList = getInvoiceList(clientId, payby, transactionType,  location, thirdParty,fromDate,toDate,ipdid);
						accounts.setInvoiceList(invoiceList);
						
					}
				}
				
//				ArrayList<Charges>chargeList = getChargesList(rs.getInt(5));
//				accounts.setChargeList(chargeList);
				
				//AKash 17 oct 2017
				String shared_amount = rs.getString(16);
				String isShared = rs.getString(17);
				String indivisual_shared = rs.getString(18);
				accounts.setShared_amount(shared_amount);
				accounts.setIsShared(isShared);
				accounts.setIndivisual_shared(indivisual_shared);
				
				
				list.add(accounts);
				
				i++;
				
			}
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	
	private ArrayList<Charges> getChargesList(int invoiceid) {
		PreparedStatement preparedStatement = null;
		ArrayList<Charges>list = new ArrayList<Charges>();
		String sql = "SELECT id,apmtType,commencing,charge,quantity,shared_amt, isshared, all_shared FROM apm_invoice_assesments where invoiceid = "+invoiceid+" ";
		
		try{
			
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()){
				Charges charges = new Charges();
				charges.setId(rs.getInt(1));
				charges.setChargeName(rs.getString(2));
				if(rs.getString(3)!=null){
					if(!rs.getString(3).equals("0")){
						charges.setCommencing(DateTimeUtils.changeDateFormatToddmmyyyy(rs.getString(3)));
					}else{
						charges.setCommencing("");
					}
				}else{
					charges.setCommencing("");
				}
				
				charges.setCharge(DateTimeUtils.changeFormat(rs.getDouble(4)));
				charges.setChargeTotal(DateTimeUtils.changeFormat(rs.getDouble(4)*rs.getInt(5)));
				charges.setChargeinvid(Integer.toString(invoiceid));
				charges.setQuantity(rs.getInt(5));
				charges.setShared_amt(rs.getString(6));
				charges.setIsshared(rs.getString(7));
				charges.setAll_shared(rs.getString(8));
				list.add(charges);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return list;
	}

	//get invoicelist
	public ArrayList<Invoice> getInvoiceList(String clientId, String payby,
			String transactionType, String location, String thirdParty,String fromDate,String toDate,String ipdid) {
		
		PreparedStatement preparedStatement = null;
		DateTimeUtils dateTimeUtils = new DateTimeUtils();
		ArrayList<Invoice>list = new ArrayList<Invoice>();
		double inddisctot=0;
		double debit=0;
		double discount=0;	
		
		String sql = "";
		StringBuffer buffer= new StringBuffer();
		
		if(!fromDate.equals("") && !toDate.equals("")){
			
			sql = "select id,payby,date,clientid,debit,location,discount,isdeleted,disctype,discamt from apm_charges_invoice where clientid="+clientId+" and date between '"+fromDate+"' AND '"+toDate+"' and payby like ('%"+payby+"%') order by id desc " ;
			buffer.append(" select id,payby,date,clientid,debit,location,discount,isdeleted,disctype,discamt from apm_charges_invoice where clientid="+clientId+" and date between '"+fromDate+"' AND '"+toDate+"' and payby like ('%"+payby+"%') ");
			if(!ipdid.equals("")){
				buffer.append(" and ipdid='"+ipdid+"'  ");
			}
			buffer.append(" order by id desc     ");
			sql=buffer.toString();
		}else{
			buffer.append(" select id,payby,date,clientid,debit,location,discount,isdeleted,disctype,discamt from apm_charges_invoice where clientid="+clientId+"  and payby like ('%"+payby+"%') ");
			if(!ipdid.equals("")){
				buffer.append(" and ipdid='"+ipdid+"'  ");
			}
			buffer.append(" order by id desc     ");
			sql=buffer.toString();
		}
		
		
		try{
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			UserProfileDAO userProfileDAO = new JDBCUserProfileDAO(connection);
			while(rs.next()){
				Invoice accounts = new Invoice();
				accounts.setId(rs.getInt(1));
				accounts.setPayby(rs.getString(2));
				accounts.setDate(dateTimeUtils.changeDateFormatToddmmyyyy(rs.getString(3)));
				accounts.setClientid(rs.getInt(4));
				
				
				String locationName = getLoactionName(rs.getInt(6));
				accounts.setLocation(locationName);
				
				int disctype = rs.getInt(9);
				double discamt = rs.getDouble(10);
				double inddiscount=0;
				boolean inddisc=false;;
				boolean islmh = userProfileDAO.getIsLMH();
				
				if(islmh){
					inddisc = true;
				}else{
					inddisc=getIndDiscStatus(rs.getInt(1));
				}
				
				accounts.setInddiscsts(inddisc);
				if(inddisc){
					inddiscount=getsumofInvoiceAssesmentDisc(rs.getInt(1));
					inddisctot=getsumofInvoiceAssesment(rs.getInt(1));
					accounts.setInddisctot(String.valueOf(inddiscount));
				}else{
					accounts.setInddisctot("0");
				}
				debit = rs.getDouble(5);
				if(inddisc){
					accounts.setIndwodisctot(String.valueOf(inddisctot));
				}else {
					accounts.setIndwodisctot("0");
				}
				accounts.setCharges(DateTimeUtils.changeFormat(debit));
				
				 discount = rs.getDouble(7);
				accounts.setDiscount(discount);
				accounts.setDeleted(rs.getInt(8));
				double debitdic = (discount*debit)/100;
				double newdiscount =0.0;
				if(disctype==1){
					debitdic = discamt;
					newdiscount = rs.getDouble(10);
				}else{
					newdiscount = rs.getDouble(7);
				}
//				if(inddisc){
//					newdiscount=discount;
//					debitdic =newdiscount;
//				}
				accounts.setNewdiscount(newdiscount);
				accounts.setDebitAmount(debit);
				accounts.setDebitAmountx(dateTimeUtils.changeFormat(debit));
				debit = debit - debitdic;
				
				accounts.setDiscount(debitdic);
				double creditAmount = getCreditAmount(rs.getInt(1));
				
				
				//if refund against invoice
				double refundAmt = getRefundAmtAgainsInvoice(rs.getInt(1));
				creditAmount = creditAmount - refundAmt;
				accounts.setCreditCharge(dateTimeUtils.changeFormat(creditAmount));
				
				double balance = debit - creditAmount;
				accounts.setBalance(balance);
				int noOfCharges = getNoOfCharges(rs.getInt(1));
				accounts.setNumberOfChages(noOfCharges);
				
				//Decimal Amount
				
				accounts.setBalancex(dateTimeUtils.changeFormat(balance));
				accounts.setRefundAmountx(dateTimeUtils.changeFormat(refundAmt));
				if(accounts.getPayby().equals(Constants.PAY_BY_CLIENT)){
					
					String clientName = getClientFullName(accounts.getClientid());
					
					accounts.setClientName(clientName);
				}else{
					
					CompleteAptmDAO completeAptmDAO = new JDBCCompleteAptmDAO(connection);
				    CompleteAppointment	completeAppointment = completeAptmDAO.getInsuranceCompanyName(Integer.toString(accounts.getClientid()));
				    accounts.setClientName(completeAppointment.getInsuranceCompanyName());
				}
				
				
//				ArrayList<ChargesInvoice>chargeInvoiceList = getChargeInvoiceList(accounts.getId());
//				accounts.setChargeInvoiceList(chargeInvoiceList);
				//showing seq no instead of invoice id 21/09/2018
				AccountsDAO accountsDAO=new JDBCAccountsDAO(connection);
			    String ipdopdseq=accountsDAO.getIpdOpdSeqNoWithType(rs.getInt(1));
			    accounts.setIpdopdseq(ipdopdseq);
				list.add(accounts);
				
			}
			
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
		
		
		return list;
	}
	
	
	
	
	private double getsumofInvoiceAssesmentDisc(int invoiceid) {
		double res=0;
		try {
			String sql="select sum(chargedisc) from apm_invoice_assesments where invoiced="+invoiceid+"";
			PreparedStatement ps=connection.prepareStatement(sql);
			ResultSet rs=ps.executeQuery();
			while (rs.next()) {
				res=rs.getDouble(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return res;
	}

	private double getsumofInvoiceAssesment(int invoiceid) {
		double res=0;
		try {
			String sql="select charge,unitcharge,chargedisc,quantity from apm_invoice_assesments where invoiced="+invoiceid+"";
			PreparedStatement ps=connection.prepareStatement(sql);
			ResultSet rs=ps.executeQuery();
			while (rs.next()) {
				if(rs.getDouble(3)>0){
					res=res+(rs.getDouble(2)*rs.getInt(4));
				}else{
					res=res+(rs.getDouble(1)*rs.getInt(4));
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return res;
	}

	public boolean getIndDiscStatus(int invoiceid) {
		boolean res=false;
		try {
			String sql="select discapprove from apm_invoice_assesments where invoiced="+invoiceid+" group by discapprove ";
			PreparedStatement ps=connection.prepareStatement(sql);
			ResultSet rs=ps.executeQuery();
			while (rs.next()) {
				if(rs.getInt(1)>0){
					res=true;
							break;
				}else{
					res=false;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return res;
	}

	private ArrayList<ChargesInvoice> getChargeInvoiceList(int chargeinvoiceid) {
		PreparedStatement preparedStatement = null;
		ArrayList<ChargesInvoice>list = new ArrayList<ChargesInvoice>();
		String sql = "SELECT invoiceid FROM apm_charges_assesment where chargeinvoiceid = "+chargeinvoiceid+" ";
		
		try{
			
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()){
				ChargesInvoice invoice = new ChargesInvoice();
				invoice.setChargeInvoiceid(rs.getString(1));
				invoice.setInvoiceid(chargeinvoiceid);
				
				String commencing = getAppointmentCommencing(rs.getString(1));
				if(!commencing.endsWith("")){
					invoice.setCommencing(DateTimeUtils.getCommencingDate1(commencing));
				}else{
					invoice.setCommencing("");
				}
				
				
				double total = getInvoiceTotal(invoice.getChargeInvoiceid());
				invoice.setInvoiceDebitx(DateTimeUtils.changeFormat(total));
				
				ArrayList<Charges>chargeList = getChargesList(rs.getInt(1));
				invoice.setChargeInvoList(chargeList);
				
				list.add(invoice);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return list;
	}

	private String getAppointmentCommencing(String invoiceid) {
		PreparedStatement preparedStatement = null;
		String result = "";
		StringBuffer sql = new StringBuffer();
		
		sql.append("SELECT apm_available_slot.commencing FROM apm_invoice inner join apm_available_slot on ");
		sql.append("apm_available_slot.id = apm_invoice.appointmentid ");
		sql.append("where apm_invoice.id = "+invoiceid+" ");
		
		try{
			
			preparedStatement = connection.prepareStatement(sql.toString());
			ResultSet rs = preparedStatement.executeQuery();
			if(rs.next()){
				result = rs.getString(1);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return result;
	}

	private double getInvoiceTotal(String chargeInvoiceid) {
		PreparedStatement preparedStatement = null;
		double result = 0;
		String sql = "SELECT charge,quantity FROM apm_invoice_assesments where invoiceid = "+chargeInvoiceid+" ";
		
		try{
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()){
				double charge = rs.getDouble(1) * rs.getInt(2);
				result = result + charge;
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
		return result;
	}

	private String getClientFullName(int clientid) {
		PreparedStatement preparedStatement = null;
		String result = "";
		String sql = "SELECT title,firstname,surname FROM apm_patient where  id = "+clientid+"";
		
		try{
			preparedStatement = connection.prepareStatement(sql);
			
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()){
				result = rs.getString(1)+" "+rs.getString(2)+" "+rs.getString(3);
			}
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		return result;
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
		
		
		public double getCreditAmount(int id) {
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
		

	private int getChargesTotalCount(String sql) {
		PreparedStatement preparedStatement = null;
		int result = 0;
		try{
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()){
				result++;
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
		return result;
	}

	public ArrayList<Invoice> getPaidInvoiceList(String clientId, String payby,
			String transactionType, String location, String thirdParty,
			String fromDate, String toDate,String ipdid) {
		PreparedStatement preparedStatement = null;
		DateTimeUtils dateTimeUtils = new DateTimeUtils();
		ArrayList<Invoice>list = new ArrayList<Invoice>();
		
		String sql = "";
		StringBuffer buffer=new StringBuffer();
		if(!fromDate.equals("") && !toDate.equals("")){
			
			buffer.append("  select id,payby,date,clientid,debit,location,discount,disctype,discamt from apm_charges_invoice where clientid = "+clientId+" and date between '"+fromDate+"' AND '"+toDate+"' and payby like ('%"+payby+"%')   ");
			if(!ipdid.equals("")){
				buffer.append(" and ipdid='"+ipdid+"'");
			}
			buffer.append("  order by id desc ");
			sql=buffer.toString();
			
		}else{

			buffer.append("  select id,payby,date,clientid,debit,location,discount,disctype,discamt from apm_charges_invoice where clientid = "+clientId+"  and payby like ('%"+payby+"%')   ");
			if(!ipdid.equals("")){
				buffer.append(" and ipdid='"+ipdid+"'");
			}
			buffer.append("  order by id desc ");
			sql=buffer.toString();
		}
		
		try{
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()){
				Invoice accounts = new Invoice();
				accounts.setId(rs.getInt(1));
				accounts.setPayby(rs.getString(2));
				accounts.setDate(dateTimeUtils.changeDateFormatToddmmyyyy(rs.getString(3)));
				accounts.setClientid(rs.getInt(4));
				
				double debit = rs.getDouble(5);
				double discount = rs.getDouble(7);
				accounts.setDiscount(discount);
				double debitdic = (discount*debit)/100;
				debit = debit - debitdic;
				accounts.setDebitAmount(debit);
				
				int disctype = rs.getInt(8);
				double newdiscount =0.0;
				if(disctype==1){
					newdiscount = rs.getDouble(9);
				}else{
					newdiscount = rs.getDouble(7);
				}
				accounts.setNewdiscount(newdiscount);
				
				String locationName = getLoactionName(rs.getInt(6));
				accounts.setLocation(locationName);
				
				

				//if refund against invoice
				double refundAmt = getRefundAmtAgainsInvoice(rs.getInt(1));
				accounts.setRefundAmountx(dateTimeUtils.changeFormat(refundAmt));
				
				double creditAmount = getCreditAmount(rs.getInt(1));
				accounts.setCreditCharge(dateTimeUtils.changeFormat(creditAmount));
				double balance = debit - creditAmount;
				accounts.setBalance(balance);
				int noOfCharges = getNoOfCharges(rs.getInt(1));
				accounts.setNumberOfChages(noOfCharges);
				
				//Decimal Amount
				accounts.setDebitAmountx(dateTimeUtils.changeFormat(debit));
				accounts.setBalancex(dateTimeUtils.changeFormat(balance));
				
				
				if(accounts.getPayby().equals(Constants.PAY_BY_CLIENT)){
					
					String clientName = getClientFullName(accounts.getClientid());
					
					accounts.setClientName(clientName);
				}else{
					
					CompleteAptmDAO completeAptmDAO = new JDBCCompleteAptmDAO(connection);
				    CompleteAppointment	completeAppointment = completeAptmDAO.getInsuranceCompanyName(Integer.toString(accounts.getClientid()));
				    accounts.setClientName(completeAppointment.getInsuranceCompanyName());
				}
				//showing seq no instead of invoice id 21/09/2018
				AccountsDAO accountsDAO=new JDBCAccountsDAO(connection);
			    String ipdopdseq=accountsDAO.getIpdOpdSeqNoWithType(rs.getInt(1));
			    accounts.setIpdopdseq(ipdopdseq);
				if(balance==0){
					list.add(accounts);
				}
				
				
			}
			
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
		
		
		return list;
	}

	public ArrayList<Invoice> getPendingInvoiceList(String clientId,
			String payby, String transactionType, String location,
			String thirdParty, String fromDate, String toDate,String ipdid) {
		PreparedStatement preparedStatement = null;
		DateTimeUtils dateTimeUtils = new DateTimeUtils();
		ArrayList<Invoice>list = new ArrayList<Invoice>();
		
		String sql = "";
		StringBuffer buffer= new StringBuffer();
		if(!fromDate.equals("") && !toDate.equals("")){
			
		//	sql = "select id,payby,date,clientid,debit,location,discount from apm_charges_invoice where clientid = "+clientId+" and date between '"+fromDate+"' AND '"+toDate+"' and payby like ('%"+payby+"%') order by id desc " ;
		buffer.append(" select id,payby,date,clientid,debit,location,discount,disctype,discamt from apm_charges_invoice where clientid = "+clientId+" and date between '"+fromDate+"' AND '"+toDate+"' and payby like ('%"+payby+"%')    ");
		if(!ipdid.equals("")){
			buffer.append(" and ipdid='"+ipdid+"' ");
		}
		buffer.append(" order by id desc ");
		sql=buffer.toString();
		}else{
			sql = "" ;
			buffer.append(" select id,payby,date,clientid,debit,location,discount,disctype,discamt from apm_charges_invoice where clientid = "+clientId+"  and payby like ('%"+payby+"%')  ");
			if(!ipdid.equals("")){
				buffer.append(" and ipdid='"+ipdid+"' ");
			}
			buffer.append("   order by id desc  ");
			sql=buffer.toString();
		}
		
		try{
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()){
				Invoice accounts = new Invoice();
				accounts.setId(rs.getInt(1));
				accounts.setPayby(rs.getString(2));
				accounts.setDate(dateTimeUtils.changeDateFormatToddmmyyyy(rs.getString(3)));
				accounts.setClientid(rs.getInt(4));
				
				double debit = rs.getDouble(5);
				double discount = rs.getDouble(7);
				accounts.setDiscount(discount);
				double debitdic = (discount*debit)/100;
				debit = debit - debitdic;
				accounts.setDebitAmount(debit);
				
				int disctype = rs.getInt(8);
				double newdiscount =0.0;
				if(disctype==1){
					newdiscount = rs.getDouble(9);
				}else{
					newdiscount = rs.getDouble(7);
				}
				accounts.setNewdiscount(newdiscount);
				
				String locationName = getLoactionName(rs.getInt(6));
				accounts.setLocation(locationName);
				
				//if refund against invoice
				double refundAmt = getRefundAmtAgainsInvoice(rs.getInt(1));
				accounts.setRefundAmountx(dateTimeUtils.changeFormat(refundAmt));
				
				double creditAmount = getCreditAmount(rs.getInt(1));
				accounts.setCreditCharge(dateTimeUtils.changeFormat(creditAmount));
				double balance = debit - creditAmount;
				accounts.setBalance(balance);
				int noOfCharges = getNoOfCharges(rs.getInt(1));
				accounts.setNumberOfChages(noOfCharges);
				
				//Decimal Amount
				accounts.setDebitAmountx(dateTimeUtils.changeFormat(debit));
				accounts.setBalancex(dateTimeUtils.changeFormat(balance));
				
				if(accounts.getPayby().equals(Constants.PAY_BY_CLIENT)){
					
					String clientName = getClientFullName(accounts.getClientid());
					
					accounts.setClientName(clientName);
				}else{
					
					CompleteAptmDAO completeAptmDAO = new JDBCCompleteAptmDAO(connection);
				    CompleteAppointment	completeAppointment = completeAptmDAO.getInsuranceCompanyName(Integer.toString(accounts.getClientid()));
				    accounts.setClientName(completeAppointment.getInsuranceCompanyName());
				}
				//showing seq no instead of invoice id 21/09/2018
				AccountsDAO accountsDAO=new JDBCAccountsDAO(connection);
			    String ipdopdseq=accountsDAO.getIpdOpdSeqNoWithType(rs.getInt(1));
			    accounts.setIpdopdseq(ipdopdseq);
				if(balance!=0){
					list.add(accounts);
				}
				
				
			}
			
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
		
		
		return list;
	}

	private String getLoactionName(int location) {
		PreparedStatement preparedStatement = null;
		String result = "";
		String sql = "SELECT location FROM apm_clinic_location where id = "+location+"";
		
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

	public double getLastCreditBalance(String clientId) {
		PreparedStatement preparedStatement = null;
		double result = 0;
		String sql = "SELECT balance FROM apm_credit_account  where client_id="+clientId+" and payment_mode IS NOT NULL" +
				" order by id desc limit 0,1 ";
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

	public Accounts getInvoiceDetails(String invoiceid) {
		double debitTotal=0.0;
		double creditTotal = 0.0;
		double balanceTotal = 0.0;
		String selctedTransType="";
		PreparedStatement preparedStatement = null;
		DateTimeUtils dateTimeUtils = new DateTimeUtils();
		
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT commencing,user,apmtType,sum(charge),invoiceid,paid,count(apm_invoice_assesments.id),apm_invoice.practitionername,apm_invoice_assesments.paybuy,apm_invoice_assesments.clientId,chargetype,howpaid,apm_invoice.appointmentid,apm_invoice.shared_amt FROM apm_invoice_assesments ");
		sql.append("inner join apm_invoice on apm_invoice.id = apm_invoice_assesments.invoiceid ");
		sql.append("where apm_invoice.id ='"+invoiceid+"' ");
		Accounts accounts = new Accounts();
		try{
			preparedStatement = connection.prepareStatement(sql.toString());
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()){
				
				accounts.setCommencing(rs.getString(1));
				accounts.setClientName(rs.getString(2));
				accounts.setAppointmentType(rs.getString(3));
				accounts.setCreditCharge(dateTimeUtils.changeFormat(Double.parseDouble(rs.getString(4))));
				accounts.setInvoiceid(rs.getInt(5));
				accounts.setPaid(rs.getBoolean(6));
				
				accounts.setNumberOfChages(rs.getInt(7));
				accounts.setPractitionerName(rs.getString(8));
				accounts.setPayby(rs.getString(9));
				if(rs.getString(9).equals("1")){
					accounts.setWhoPay("Third Party");
				}
				else{
					accounts.setWhoPay("Self");
				}
				accounts.setClientid(rs.getInt(10));
				accounts.setChargeType(rs.getString(11));
				accounts.setHowPaid(rs.getString(12));
				accounts.setApptId(rs.getString(13));
				String treamentEpisodeName = getTreatmentEpisodeName(rs.getString(13));
				accounts.setTreatmentEpisodeName(treamentEpisodeName);
				int credit = getCreditAmount(rs.getString(5),rs.getString(9));
				double totalPayment = getPaymentAmount(rs.getInt(5),rs.getString(9));
				double d1 = getDebitTotal(rs.getInt(5),rs.getString(9),rs.getString(10));
				
				double debit = totalPayment - credit;
				accounts.setPayAmount(credit);
				accounts.setTotalAmount(credit+debit);
				double total = credit+debit;
				
				accounts.setDebitAmount(debit);
				
				debitTotal = total + debitTotal;
				creditTotal = creditTotal + credit;
				balanceTotal = balanceTotal + debit;
				accounts.setDebitTotal(debitTotal);
				accounts.setCreditTotal(creditTotal);
				accounts.setBalanceTotal(balanceTotal);
				String Share_amount = rs.getString(14);
				if(Share_amount==null){
					Share_amount = "0";
				}else if(Share_amount.equals("")){
					Share_amount = "0";
				}
				
				double balance = total - Double.parseDouble(Share_amount);
				
				//Decimal Amount
				accounts.setPayAmountx(dateTimeUtils.changeFormat(credit));
				accounts.setTotalAmountx(dateTimeUtils.changeFormat(total));
				accounts.setDebitAmountx(dateTimeUtils.changeFormat(debit));
				accounts.setDebitTotalx(dateTimeUtils.changeFormat(debitTotal));
				accounts.setCreditTotalx(dateTimeUtils.changeFormat(creditTotal));
				accounts.setBalanceTotalx(dateTimeUtils.changeFormat(balanceTotal));
				accounts.setBalance(balance);
				accounts.setShared_amount(Share_amount);
				ArrayList<Accounts> arrayList = getShareChargeByType(invoiceid,"0");
				accounts.setSharelist(arrayList);
			}
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		return accounts;
	}

	private ArrayList<Accounts> getShareChargeByType(String invoiceid,String chargeid) {
		PreparedStatement preparedStatement = null;
		double result = 0;
		ArrayList<Accounts> arrayList = new ArrayList<Accounts>();
		UserProfileDAO userProfileDAO = new JDBCUserProfileDAO(connection);
		try{
			//String sql = "SELECT id,pract_id,datetime,commission_type,amount,remark,userid from his_share_charges where invoiceid='"+invoiceid+"' ";
			StringBuilder builder = new StringBuilder();
			builder.append("SELECT id,pract_id,datetime,commission_type,amount,remark,userid,commission from his_share_charges ");
			if(chargeid.equals("0")){
				builder.append("where invoiceid='"+invoiceid+"' ");
			}else{
				builder.append("where chargeid='"+chargeid+"' ");
			}
			
			preparedStatement = connection.prepareStatement(builder.toString());
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()){
				Accounts accounts = new Accounts();
				accounts.setId(rs.getInt(1));
				accounts.setPractitionerId(rs.getInt(2));
				String name = userProfileDAO.getFullName(rs.getString(2));
				accounts.setPractitionerName(name);
				accounts.setDate(rs.getString(3));
				accounts.setCommission_type(rs.getString(4));
				accounts.setAmountx(rs.getString(5));
				accounts.setRemark(rs.getString(6));
				accounts.setUserid(rs.getString(7));
				accounts.setCommission(rs.getString(8));
				arrayList.add(accounts);
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		return arrayList;
	}

	public Accounts getInvoiceChargeDetails(String chargeid) {
		double debitTotal=0.0;
		double creditTotal = 0.0;
		double balanceTotal = 0.0;
		String selctedTransType="";
		PreparedStatement preparedStatement = null;
		DateTimeUtils dateTimeUtils = new DateTimeUtils();
		
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT commencing,user,apmtType,sum(charge),invoiceid,paid,count(apm_invoice_assesments.id),apm_invoice.practitionername,apm_invoice_assesments.paybuy,apm_invoice_assesments.clientId,chargetype,howpaid,apm_invoice.appointmentid,apm_invoice_assesments.shared_amt,apm_invoice_assesments.quantity FROM apm_invoice_assesments ");
		sql.append("inner join apm_invoice on apm_invoice.id = apm_invoice_assesments.invoiceid ");
		sql.append("where apm_invoice_assesments.id ='"+chargeid+"' ");
		Accounts accounts = new Accounts();
		try{
			preparedStatement = connection.prepareStatement(sql.toString());
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()){
				
				accounts.setCommencing(rs.getString(1));
				accounts.setClientName(rs.getString(2));
				accounts.setAppointmentType(rs.getString(3));
				accounts.setCreditCharge(dateTimeUtils.changeFormat(Double.parseDouble(rs.getString(4))));
				accounts.setInvoiceid(rs.getInt(5));
				accounts.setPaid(rs.getBoolean(6));
				
				accounts.setNumberOfChages(rs.getInt(7));
				accounts.setPractitionerName(rs.getString(8));
				accounts.setPayby(rs.getString(9));
				if(rs.getString(9).equals("1")){
					accounts.setWhoPay("Third Party");
				}
				else{
					accounts.setWhoPay("Self");
				}
				accounts.setClientid(rs.getInt(10));
				accounts.setChargeType(rs.getString(11));
				accounts.setHowPaid(rs.getString(12));
				accounts.setApptId(rs.getString(13));
				/*String treamentEpisodeName = getTreatmentEpisodeName(rs.getString(13));
				accounts.setTreatmentEpisodeName(treamentEpisodeName);*/
				/*int credit = getCreditAmount(rs.getString(5),rs.getString(9));
				double totalPayment = getPaymentAmount(rs.getInt(5),rs.getString(9));
				double d1 = getDebitTotal(rs.getInt(5),rs.getString(9),rs.getString(10));
				
				double debit = totalPayment - credit;
				accounts.setPayAmount(credit);
				accounts.setTotalAmount(credit+debit);
				double total = credit+debit;
				
				accounts.setDebitAmount(debit);
				
				debitTotal = total + debitTotal;
				creditTotal = creditTotal + credit;
				balanceTotal = balanceTotal + debit;
				accounts.setDebitTotal(debitTotal);
				accounts.setCreditTotal(creditTotal);
				accounts.setBalanceTotal(balanceTotal);*/
				double total = Double.parseDouble(rs.getString(4)) * rs.getInt(15);
				String Share_amount = rs.getString(14);
				if(Share_amount==null){
					Share_amount = "0";
				}else if(Share_amount.equals("")){
					Share_amount = "0";
				}
				
				double balance = total - Double.parseDouble(Share_amount);
				
				//Decimal Amount
				//accounts.setPayAmountx(dateTimeUtils.changeFormat(credit));
				accounts.setTotalAmountx(dateTimeUtils.changeFormat(total));
				//accounts.setDebitAmountx(dateTimeUtils.changeFormat(debit));
				accounts.setDebitTotalx(dateTimeUtils.changeFormat(debitTotal));
				accounts.setCreditTotalx(dateTimeUtils.changeFormat(creditTotal));
				accounts.setBalanceTotalx(dateTimeUtils.changeFormat(balanceTotal));
				accounts.setBalance(balance);
				accounts.setShared_amount(Share_amount);
				ArrayList<Accounts> arrayList = getShareChargeByType("0",chargeid);
				accounts.setSharelist(arrayList);
			}
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		return accounts;
	}

	public int updateInvoiceSharedStatus(String invoiceid, double total_shared) {
		PreparedStatement preparedStatement = null;
		int result = 0;
		try{
			String sql = "update apm_invoice set isshared='1',shared_amt='"+total_shared+"' where id = "+invoiceid+"";
			preparedStatement = connection.prepareStatement(sql);
			result = preparedStatement.executeUpdate();
			
			String sql1 = "update apm_invoice_assesments set all_shared='1' where invoiceid = "+invoiceid+"";
			PreparedStatement preparedStatement2 = connection.prepareStatement(sql1);
			result = preparedStatement2.executeUpdate();
		}catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public int updateChargeSharedStatus(String invoiceid, String chargeid, double total_shared) {
		PreparedStatement preparedStatement = null;
		int result = 0;
		try{
			String sql = "update apm_invoice set indivisual_shared='1' where id = "+invoiceid+"";
			preparedStatement = connection.prepareStatement(sql);
			result = preparedStatement.executeUpdate();
			
			String sql1 = "update apm_invoice_assesments set isshared='1',shared_amt='"+total_shared+"' where id = "+chargeid+"";
			PreparedStatement preparedStatement2 = connection.prepareStatement(sql1);
			result = preparedStatement2.executeUpdate();
		}catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public double getSumOfReundAmt(String clientId) {
		PreparedStatement preparedStatement = null;
		double result = 0;
		String sql = "SELECT sum(debit) FROM apm_credit_account  where client_id = "+clientId+" ";
		
		try{
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			if(rs.next()){
				result = rs.getDouble(1);
			}
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		
		return result;
	}

	public double getRefundAmtAgainsInvoice(int invoiceid) {
		PreparedStatement preparedStatement = null;
		double result = 0;
		String sql = "SELECT sum(debit) FROM apm_credit_account where manualinvoiceid = "+invoiceid+" ";
		
		try{
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			if(rs.next()){
				result = rs.getDouble(1);
			}
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		
		return result;
	}

	public double getRefundChargeAmt(int invoiceid) {
		PreparedStatement preparedStatement = null;
		double result = 0;
		String sql = "select sum(charge*quantity) from invoice_delete_charges where ispaydonestatus=1 and actualinvoiceid="+invoiceid+"  ";
		try{
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			if(rs.next()){
				result = rs.getDouble(1);
			}
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		
		return result;
	}

}
