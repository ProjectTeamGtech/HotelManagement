package com.apm.Report.eu.blogic.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

import org.apache.struts2.convention.annotation.Results;

import com.apm.Accounts.eu.bi.AccountsDAO;
import com.apm.Accounts.eu.bi.AdditionalDAO;
import com.apm.Accounts.eu.bi.StatementDAO;
import com.apm.Accounts.eu.blogic.jdbc.JDBCAccountsDAO;
import com.apm.Accounts.eu.blogic.jdbc.JDBCAdditionalDAO;
import com.apm.Accounts.eu.blogic.jdbc.JDBCStatementDAO;
import com.apm.Accounts.eu.entity.Accounts;
import com.apm.DiaryManagement.eu.bi.ClientDAO;
import com.apm.DiaryManagement.eu.bi.CompleteAptmDAO;
import com.apm.DiaryManagement.eu.bi.DiaryManagementDAO;
import com.apm.DiaryManagement.eu.bi.FinderDAO;
import com.apm.DiaryManagement.eu.blogic.jdbc.JDBCClientDAO;
import com.apm.DiaryManagement.eu.blogic.jdbc.JDBCCompleteAptmDAO;
import com.apm.DiaryManagement.eu.blogic.jdbc.JDBCDiaryManagentDAO;
import com.apm.DiaryManagement.eu.blogic.jdbc.JDBCFinderDAO;
import com.apm.DiaryManagement.eu.entity.Client;
import com.apm.DiaryManagement.eu.entity.CompleteAppointment;
import com.apm.DiaryManagement.eu.entity.NotAvailableSlot;
import com.apm.Emr.eu.bi.EmrDAO;
import com.apm.Emr.eu.blogic.jdbc.JDBCEmrDAO;
import com.apm.Ipd.eu.bi.BedDao;
import com.apm.Ipd.eu.bi.IpdDAO;
import com.apm.Ipd.eu.blogic.jdbc.JDBCBedDao;
import com.apm.Ipd.eu.blogic.jdbc.JDBCIpdDAO;
import com.apm.Ipd.eu.entity.Bed;
import com.apm.Master.eu.entity.Master;
import com.apm.Registration.eu.bi.UserProfileDAO;
import com.apm.Registration.eu.blogic.jdbc.JDBCUserProfileDAO;
import com.apm.Registration.eu.entity.UserProfile;
import com.apm.Report.eu.bi.ChargesReportDAO;
import com.apm.Report.eu.bi.SummaryReportDAO;
import com.apm.Report.eu.entity.ChargesReport;
import com.apm.Report.eu.entity.MisReport;
import com.apm.Report.eu.entity.SummaryReport;
import com.apm.ThirdParties.eu.bi.OutstandingReportDAO;
import com.apm.ThirdParties.eu.bi.ThirdPartyDAO;
import com.apm.ThirdParties.eu.blogic.jdbc.JDBCOutstandingReportDAO;
import com.apm.ThirdParties.eu.blogic.jdbc.JDBCThirdPartyDAO;
import com.apm.ThirdParties.eu.entity.ThirdParty;
import com.apm.common.eu.blogic.jdbc.JDBCBaseDAO;
import com.apm.common.utils.DateTimeUtils;
import com.apm.common.utils.Pagination;
import com.apm.common.web.common.helper.LoginInfo;
import com.apm.main.common.constants.Constants;


public class JDBCChargesReportDAO extends JDBCBaseDAO implements ChargesReportDAO{
	
	public JDBCChargesReportDAO(Connection connection){
		this.connection = connection;
	}

	public ArrayList<Accounts> getChargesReportList(String fromDate,String toDate,String payby,String tpid,String invoiceStatus,String orderby,String order,String jobtitle) {
		
		double creditTotal = 0.0;
		double balanceTotal = 0.0;
		DateTimeUtils dateTimeUtils = new DateTimeUtils();
		PreparedStatement preparedStatement = null;
		ArrayList<Accounts> list = new ArrayList<Accounts>();
		
		StringBuffer sql = new StringBuffer();
		
		
		
		if(!fromDate.equals("") && !toDate.equals("")){
			
			sql.append("SELECT commencing,user,apmtType,sum(charge*quantity),invoiceid,paid,count(apm_invoice_assesments.id),apm_invoice.practitionerid,apm_invoice_assesments.paybuy,apm_invoice_assesments.clientId,apm_invoice.chargetype,howpaid,apm_invoice.appointmentid,department,apm_invoice_assesments.thirdPartyId,wardid,abrivationid,f_seq_no  FROM apm_invoice_assesments ");
			sql.append("inner join apm_invoice on apm_invoice.id = apm_invoice_assesments.invoiceid ");
			//sql.append("inner join apm_third_party_details on apm_third_party_details.id = apm_invoice_assesments.thirdPartyId ");
			sql.append("inner join apm_patient on apm_patient.id = apm_invoice_assesments.clientId ");
			
			sql.append("where  commencing between '"+fromDate+"' AND '"+toDate+"' ");
			sql.append("and apm_invoice_assesments.active=1  ");
		}else{
			sql.append("SELECT commencing,user,apmtType,sum(charge*quantity),invoiceid,paid,count(apm_invoice_assesments.id),apm_invoice.practitionerid,apm_invoice_assesments.paybuy,apm_invoice_assesments.clientId,chargetype,howpaid,apm_invoice.appointmentid,department,apm_invoice_assesments.thirdPartyId,wardid,abrivationid  FROM apm_invoice_assesments ");
			sql.append("inner join apm_invoice on apm_invoice.id = apm_invoice_assesments.invoiceid ");
			sql.append("where apm_invoice_assesments.active=1  ");		
		}
		
		
		
		if(payby!=null){
			if(!payby.equals("All")){
				
			if(!payby.equals("0")){
				sql = new StringBuffer();
				if(payby.equals(Constants.PAY_BY_THIRD_PARTY)){
					payby = "1";
				}else if(payby.equals(Constants.PAY_BY_CLIENT)){
					payby = "0";
				}else{
					payby="";
				}
			}
				
				if(!fromDate.equals("") && !toDate.equals("")){
					sql = new StringBuffer();
					sql.append("SELECT commencing,user,apmtType,sum(charge*quantity),invoiceid,paid,count(apm_invoice_assesments.id),apm_invoice.practitionerid,apm_invoice_assesments.paybuy,apm_invoice_assesments.clientId,chargetype,howpaid,apm_invoice.appointmentid,department,apm_invoice_assesments.thirdPartyId,wardid,abrivationid  FROM apm_invoice_assesments ");
					sql.append("inner join apm_invoice on apm_invoice.id = apm_invoice_assesments.invoiceid ");
					//sql.append("inner join apm_third_party_details on apm_third_party_details.id = apm_invoice_assesments.thirdPartyId ");
					sql.append("inner join apm_patient on apm_patient.id = apm_invoice_assesments.clientId ");
					sql.append("where commencing between '"+fromDate+"' AND '"+toDate+"' ");
					
					if(payby.equals("1") && !tpid.equals("0")){
						
						sql.append("and apm_invoice_assesments.active=1 and apm_invoice_assesments.paybuy='"+payby+"' and apm_invoice_assesments.thirdPartyId="+tpid+"   ");
					}else{
						sql.append("and apm_invoice_assesments.active=1 and apm_invoice_assesments.paybuy='"+payby+"'  ");
					}
					
				}else{
					sql = new StringBuffer();
					sql.append("SELECT commencing,user,apmtType,sum(charge*quantity),invoiceid,paid,count(apm_invoice_assesments.id),apm_invoice.practitionerid,apm_invoice_assesments.paybuy,apm_invoice_assesments.clientId,chargetype,howpaid,apm_invoice.appointmentid,department,apm_invoice_assesments.thirdPartyId,wardid,abrivationid  FROM apm_invoice_assesments ");
					sql.append("inner join apm_invoice on apm_invoice.id = apm_invoice_assesments.invoiceid ");
					
					if(payby.equals("1") && !tpid.equals("0")){
						
						sql.append("where apm_invoice_assesments.active=1 and apm_invoice_assesments.paybuy='"+payby+"' and apm_invoice_assesments.thirdPartyId="+tpid+"   ");
					}else{
						sql.append("where apm_invoice_assesments.active=1 and apm_invoice_assesments.paybuy='"+payby+"'  ");
					}
				}
				
			
				
				
			}
	}
		if(jobtitle!=null){
			
			if(!jobtitle.equals("0")){
				sql.append(" and department='"+jobtitle+"' group by invoiceid ");
			}else{
				sql.append(" group by invoiceid ");
			}
		}else{
			
			sql.append(" group by invoiceid ");
		}
			
		
		
		sql.append("order by "+orderby+" "+order+" ");
		
		String sql1 = sql.toString();
		
		try{
			preparedStatement = connection.prepareStatement(sql1);
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()){
				double debitTotal=0.0;
				Accounts accounts = new Accounts();
				accounts.setCommencing(DateTimeUtils.getCommencingDate1(rs.getString(1)));
				accounts.setClientName(rs.getString(2));
				accounts.setAppointmentType(rs.getString(3));
				accounts.setCreditCharge(rs.getString(4));
				accounts.setInvoiceid(rs.getInt(5));
				accounts.setPaid(rs.getBoolean(6));
				
				accounts.setNumberOfChages(rs.getInt(7));
				String practitionerFullName = getPractitionerFullName(rs.getString(8));
				accounts.setPractitionerName(practitionerFullName);
				accounts.setPayby(rs.getString(9));
				if(rs.getString(9).equals("1")){
					accounts.setWhoPay("Third Party");
					
					CompleteAptmDAO completeAptmDAO = new JDBCCompleteAptmDAO(connection);
					CompleteAppointment completeAppointment =  completeAptmDAO.getInsuranceCompanyName(rs.getString(10));
					String tpName = completeAppointment.getInsuranceCompanyName();
					accounts.setTpName(tpName);
					
				}
				else{
					accounts.setWhoPay("Self");
				}
				accounts.setClientid(rs.getInt(10));
				accounts.setChargeType(rs.getString(11));
				accounts.setHowPaid(rs.getString(12));
				accounts.setApptId(rs.getString(13));
				
				String locationName = getChargeDepartmentName(rs.getInt(8));
				accounts.setLocationName(locationName);
				
				String treamentEpisodeName = getTreatmentEpisodeName(rs.getString(13));
				accounts.setTreatmentEpisodeName(treamentEpisodeName);
				accounts.setLocationName(locationName);
				
				
				
				
				
				boolean chargesInvoiced = checkChargeInvoiced(accounts.getInvoiceid());
				accounts.setChargesInvoiced(chargesInvoiced);
				
				
				//if(chargesInvoiced==false){
					double credit = 0.0;
					debitTotal =  rs.getDouble(4); 
			/*		int credit = getCreditAmount(rs.getString(5),rs.getString(9));
					int totalPayment = getPaymentAmount(rs.getInt(5),rs.getString(9));
					int debit = totalPayment - credit;
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
					
					accounts.setDebitTotal(debitTotal);
					
					accounts.setDebitTotalx(dateTimeUtils.changeFormat(debitTotal));
					accounts.setCreditTotalx(dateTimeUtils.changeFormat(credit));
					accounts.setBalanceTotalx(dateTimeUtils.changeFormat(debitTotal));
					BedDao bedDao=new JDBCBedDao(connection);
				//}
				String wardname=bedDao.getWardName(rs.getString(16));
				accounts.setWard(wardname);
				accounts.setAbrivationid(rs.getString(17));
				if(invoiceStatus.equals("Invoiced")){
					if(chargesInvoiced){
						list.add(accounts);
					}
				}else if(invoiceStatus.equals("Not Invoiced")){
					if(!chargesInvoiced){
						list.add(accounts);
					}
				}else{
					list.add(accounts);
				}
				
				
			}
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	public String getChargeDepartmentName(int id) {
		PreparedStatement preparedStatement = null;
		String result = "";
		//String sql = "SELECT discipline FROM apm_discipline where id = "+id+" ";
		String sql = "select discipline from apm_user inner join  apm_discipline "
				+ " on apm_discipline.id = apm_user.discription where apm_user.id = "+id+" ";
		
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

	private int getPaymentAmount(int invoiceid, String paybuy) {
		PreparedStatement preparedStatement = null;
		int result = 0;
		String sql = "SELECT sum(charge) FROM apm_invoice_assesments where invoiceid = "+invoiceid+" and paybuy = "+paybuy+" and active=1";
		
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

	private boolean checkChargeInvoiced(int invoiceid) {
		PreparedStatement preparedStatement = null;
		boolean result = false;
		StringBuffer sql = new StringBuffer();
		
		sql.append("SELECT apm_invoice.id FROM apm_invoice inner join apm_charges_assesment on ");
		sql.append("apm_invoice.id =  apm_charges_assesment.invoiceid where apm_invoice.id= "+invoiceid+" ");
		
		
		
		try{
			
			preparedStatement = connection.prepareStatement(sql.toString());
			ResultSet rs = preparedStatement.executeQuery();
			if(rs.next()){
				result = true;
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

	private String getLocationName(int location) {
		PreparedStatement preparedStatement = null;
		String result = "";
		String sql = "SELECT location FROM apm_clinic_location where id = "+location+" ";
		
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

	private String getPractitionerFullName(String id) {
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
	
	
	
	
	public ArrayList<Accounts> getAuditorsInvoiceReportList(String fromDate,String toDate,
			String payby,String paymentStatus,String thirdparty,String orderby,String order,String invcategory,String invcetype) {
		PreparedStatement preparedStatement = null;
		ArrayList<Accounts>list = new ArrayList<Accounts>();
		StringBuffer sql = new StringBuffer();
		
		sql.append("SELECT apm_charges_invoice.id,payby,date,apm_charges_invoice.clientid,debit,deliverstatus,discount,tpid,firstname,resetinv,disctype,discamt,apm_charges_invoice.ipdid ");
		sql.append("FROM apm_charges_invoice ");
		sql.append("inner join apm_patient on apm_patient.id = apm_charges_invoice.clientid ");
		sql.append("where itype="+invcetype+" and date between '"+fromDate+"' AND '"+toDate+"' ");
		

		if(!invcategory.equals("2")){
			sql.append("and apm_charges_invoice.invpstype="+invcategory+" ");
		}
		
		
		if(payby!=null){
			if(payby.equals("0")){
				orderby = "resetinv";
			}
			if(!payby.equals("0")){
				
				sql = new StringBuffer();
				if(payby.equals(Constants.PAY_BY_CLIENT)){
					
						
					sql.append("SELECT apm_charges_invoice.id,payby,date,apm_charges_invoice.clientid,debit,deliverstatus,discount,tpid,firstname,resetinv,disctype,discamt ,apm_charges_invoice.ipdid ");
					sql.append("FROM apm_charges_invoice ");
					sql.append("inner join apm_patient on apm_patient.id = apm_charges_invoice.clientid ");
					sql.append("where itype="+invcetype+" and payby='Client' and date between '"+fromDate+"' AND '"+toDate+"' ");
					
						
						if(!invcategory.equals("2")){
							sql.append("and apm_charges_invoice.invpstype="+invcategory+" ");
						}
					
				}else{
					if(payby.equals(Constants.PAY_BY_THIRD_PARTY)){
						sql = new StringBuffer();
						
						sql.append("SELECT apm_charges_invoice.id,payby,date,apm_charges_invoice.clientid,debit,deliverstatus,discount,tpid,firstname,resetinv,disctype,discamt,apm_charges_invoice.ipdid ");
						sql.append("FROM apm_charges_invoice ");
						sql.append("inner join apm_patient on apm_patient.id = apm_charges_invoice.clientid ");
						sql.append("where itype="+invcetype+" and payby='Third Party' and date between '"+fromDate+"' AND '"+toDate+"'  ");
							
							if(!invcategory.equals("2")){
								sql.append("and apm_charges_invoice.invpstype="+invcategory+" ");
							}
					}
				
			}
			
		}
			
			
			if(!thirdparty.equals("0")){
				sql = new StringBuffer();
				
				sql = new StringBuffer();
				
				sql.append("SELECT apm_charges_invoice.id,payby,date,apm_charges_invoice.clientid,debit,deliverstatus,discount,tpid,firstname,resetinv,disctype,discamt,apm_charges_invoice.ipdid ");
				sql.append("FROM apm_charges_invoice ");
				sql.append("inner join apm_patient on apm_patient.id = apm_charges_invoice.clientid ");
				sql.append("inner join apm_third_party_details on apm_third_party_details.id = apm_charges_invoice.tpid ");
				sql.append("where itype="+invcetype+" and payby='Third Party' and apm_third_party_details.id="+thirdparty+" and date between '"+fromDate+"' AND '"+toDate+"'  ");
					
					if(!invcategory.equals("2")){
						sql.append("and apm_charges_invoice.invpstype="+invcategory+" ");
					}
				
			}
				
			
	}
		
		
		
		
		
		sql.append("order by "+orderby+" "+order+" ");
		
		 
		try{
			preparedStatement = connection.prepareStatement(sql.toString());
			ResultSet rs = preparedStatement.executeQuery();
			IpdDAO ipdDAO= new JDBCIpdDAO(connection);
			while(rs.next()){
				double balance = 0;
				double result = 0.0;
				double creditAmount = 0;
				
				Accounts accounts = new Accounts();
				accounts.setId(rs.getInt(1));
				accounts.setPayby(rs.getString(2));
				accounts.setDate(DateTimeUtils.getCommencingDate1(rs.getString(3)));
				accounts.setClientid(rs.getInt(4));
				
				double totaldebitamount = 0;
				
				double prepaymentAmount = getPrePaymentAmount(rs.getInt(1));
				double debitamount = rs.getDouble(5);
				
				if(accounts.getPayby().equals(Constants.PAY_BY_CLIENT)){
					totaldebitamount = debitamount - prepaymentAmount;
				}else{
					totaldebitamount = rs.getDouble(5);
				}
				
				accounts.setIpdid(ipdDAO.getipdseqno(rs.getString(13)));
				accounts.setDebitAmount(totaldebitamount);
				accounts.setDebitAmountx(DateTimeUtils.changeFormat(totaldebitamount));
				
				accounts.setDeliverstatus(rs.getString(6));
				
				double debit = rs.getDouble(5);
				double total = totaldebitamount;
				double discount = rs.getDouble(7);
				int disctype=rs.getInt(11);
				double discamt= rs.getDouble(12);
				
				
				double r1 = (total*discount)/100;
				
				if(disctype==0){
					discamt= r1;
				}else{
					r1= discamt;
				}
				
				accounts.setDiscount(rs.getDouble(7));
				accounts.setDiscAmmount(DateTimeUtils.changeFormat(discamt));
				total = total-r1;
				result = result + total;
				
				if(accounts.getPayby().equals(Constants.PAY_BY_CLIENT)){
					creditAmount = getAuditCreditAmount(rs.getDouble(1));
				}else{
					creditAmount = getCreditAmount(rs.getDouble(1));
				}
				
				accounts.setCreditAmount(creditAmount);
				accounts.setCreditCharge(Double.toString(creditAmount));
				accounts.setCreditTotalx(DateTimeUtils.changeFormat(creditAmount));
				
				balance = result - creditAmount;
				if(balance<0){
					balance=0;
				}
				accounts.setBalance(balance);
				accounts.setBalancex(DateTimeUtils.changeFormat(balance));
				
				int noOfCharges = getNoOfCharges(rs.getInt(1));
				accounts.setNumberOfChages(noOfCharges);
				
				String clientname = getClientFullName(rs.getString(4));
				accounts.setClientName(clientname);
				String payee = rs.getString(2);
				String payeename = null;
				if(payee.equalsIgnoreCase("Third Party")){
					payeename = getTpname(rs.getInt(8));
					
				}
				else{
					payeename = clientname;
				}
				accounts.setPayeeName(payeename);
				accounts.setResetinv(rs.getString(10));
				//showing seqno instead of invoice no.  
				AccountsDAO accountsDAO=new JDBCAccountsDAO(connection);
			    String ipdopdseq=accountsDAO.getIpdOpdSeqNoWithType(rs.getInt(1));
			    accounts.setIpdopdseq((ipdopdseq));				
				
				if(paymentStatus!=null){
					if(paymentStatus.equals("Paid")){
						if(balance==0){
							list.add(accounts);
						}
					}else if(paymentStatus.equals("Not Paid")){
						if(balance!=0){
							list.add(accounts);
						}
					}else{
						list.add(accounts);
					}
				}else{
					list.add(accounts);
				}
				
				
				
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
		
		return list;
	}
	
	
	
	
	
	

	private double getPrePaymentAmount(int id) {
		PreparedStatement preparedStatement = null;
		double result = 0;
		String sql = "SELECT sum(payment) FROM apm_charges_payment where chargeinvoiceid = "+id+" and paymode ='prepayment' ";
		
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

	private double getAuditCreditAmount(double id) {
		PreparedStatement preparedStatement = null;
		double result = 0;
		String sql = "SELECT sum(payment) FROM apm_charges_payment where chargeinvoiceid = "+id+" and paymode!='prepayment' ";
		
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

	public ArrayList<Accounts> getInvoiceReportList(String fromDate,String toDate,String payby,String paymentStatus,String thirdparty,String orderby,String order,String invcategory,String invoicetype, LoginInfo loginInfo) {
		PreparedStatement preparedStatement = null;
		ArrayList<Accounts>list = new ArrayList<Accounts>();
		StringBuffer sql = new StringBuffer();
		StatementDAO statementDAO = new JDBCStatementDAO(connection);
		IpdDAO ipdDAO=new JDBCIpdDAO(connection);
//		if(!fromDate.equals("") && !toDate.equals("")){
//			sql.append("select apm_charges_invoice.id,payby,date,clientid,debit,deliverstatus,discount,tpid,firstname,apm_charges_invoice.disctype,discamt,apm_charges_invoice.itype,apm_patient.abrivationid from apm_charges_invoice inner join ");
//			sql.append("apm_patient on apm_patient.id = apm_charges_invoice.clientid ");
//			sql.append("where date between '"+fromDate+"' AND '"+toDate+"' ");
//			if(!invcategory.equals("2")){
//				sql.append("and apm_charges_invoice.invpstype="+invcategory+" ");
//			}
//			//sql.append("select id,payby,date,clientid,debit,deliverstatus,discount,tpid from apm_charges_invoice where date between '"+fromDate+"' AND '"+toDate+"'  ") ;
//		}else{
//			sql.append("select id,payby,date,clientid,debit,deliverstatus,discount,tpid,apm_charges_invoice.itype from apm_charges_invoice ") ;
//			if(!invcategory.equals("2")){
//				sql.append("where apm_charges_invoice.invpstype="+invcategory+" ");
//			}
//		}
		
		if(payby==null){
			payby="0";
		}
		if(thirdparty==null){
			thirdparty="0";
		}
		if(payby!=null){
			if(payby.equals("0")){
				orderby = "firstname";
			}
		}
			
		// adarsh
		/*if(payby!=null){
			if(!payby.equals("0") ){
				payby = thirdparty ;
				//sql.append("and payby='"+thirdparty+"'");
			}else if(!payby.equals("0")){
				sql.append("and payby='"+thirdparty+"' " );
			}
		}
	
		}*/
		
//			if(!payby.equals("0")){
				
				sql = new StringBuffer();
//				if(payby.equals(Constants.PAY_BY_CLIENT)){
//					if(!fromDate.equals("") && !toDate.equals("")){
//						
//						//sql.append("select id,payby,date,clientid,debit,deliverstatus,discount,tpid from apm_charges_invoice where payby='"+payby+"' and  date between '"+fromDate+"' AND '"+toDate+"' order by id desc  ") ;
//						sql.append("select apm_charges_invoice.id,payby,date,clientid,debit,deliverstatus,discount,tpid,firstname,apm_charges_invoice.disctype,discamt,apm_charges_invoice.itype,apm_patient.abrivationid from apm_charges_invoice inner join ");
//						sql.append("apm_patient on apm_patient.id = apm_charges_invoice.clientid ");
//						sql.append("where payby='Client' and  date between '"+fromDate+"' AND '"+toDate+"' ");
//						if(!invcategory.equals("2")){
//							sql.append("and apm_charges_invoice.invpstype="+invcategory+" ");
//						}
//					}else{
//						sql.append("select apm_charges_invoice.id,payby,date,clientid,debit,deliverstatus,discount,tpid,firstname,apm_charges_invoice.disctype,discamt,apm_charges_invoice.itype,apm_patient.abrivationid from apm_charges_invoice inner join ");
//						sql.append("apm_patient on apm_patient.id = apm_charges_invoice.clientid ");
//						sql.append("where payby='Client' ");
//						if(!invcategory.equals("2")){
//							sql.append("and apm_charges_invoice.invpstype="+invcategory+" ");
//						}
//					}
//				}else{
//					
//					if(!fromDate.equals("") && !toDate.equals("")){
//						
//						//sql = "select id,payby,date,clientid,debit,deliverstatus,discount,tpid from apm_charges_invoice where payby='"+payby+"' and  date between '"+fromDate+"' AND '"+toDate+"' order by id desc " ;
						/*sql.append("select apm_charges_invoice.id,apm_charges_invoice.payby,apm_charges_invoice.date,");
						sql.append("apm_charges_invoice.clientid,debit,deliverstatus,discount,apm_charges_invoice.tpid,");
						sql.append("company_name,apm_charges_invoice.disctype,discamt,apm_charges_invoice.itype,");
						sql.append("apm_patient.abrivationid,sum(payment),concat(apm_user.initial,' ',apm_user.firstname,' ',apm_user.lastname)  ");
						sql.append("from apm_charges_invoice ");
						sql.append("inner join apm_patient on apm_patient.id = apm_charges_invoice.clientid ");
						sql.append("left join apm_user on apm_user.id =apm_charges_invoice.practid ");
						sql.append("left join apm_third_party_details on apm_third_party_details.id = apm_charges_invoice.tpid ");
						sql.append("left join apm_charges_payment on apm_charges_payment.chargeinvoiceid = apm_charges_invoice.id ");
						sql.append("where apm_charges_invoice.date between '"+fromDate+"' AND '"+toDate+"' ");
						if(payby.equals(Constants.PAY_BY_CLIENT)){
							sql.append("and payby='Client' ");
						}else if(payby.equals(Constants.PAY_BY_THIRD_PARTY)){
							sql.append("and payby='Third Party' ");
						}
						if(!invcategory.equals("2")){
							sql.append("and apm_charges_invoice.invpstype="+invcategory+" ");
						}
						if(!thirdparty.equals("0")){
							sql.append(" and apm_third_party_details.id="+thirdparty+" ");	
						}*/
				
				/* @rahul because slow report opening remove left join */
				/*
				 * sql.
				 * append("select apm_charges_invoice.id,apm_charges_invoice.payby,apm_charges_invoice.date,apm_charges_invoice.clientid,apm_charges_invoice.debit,deliverstatus, "
				 * ); sql.
				 * append("apm_charges_invoice.discount,apm_charges_invoice.tpid,company_name,apm_charges_invoice.disctype,apm_charges_invoice.discamt,apm_charges_invoice.itype, "
				 * ); sql.
				 * append("apm_patient.abrivationid,sum(payment),concat(apm_user.initial,' ',apm_user.firstname,' ',apm_user.lastname), "
				 * ); sql.append("apm_charges_invoice.ipdid,apm_charges_invoice.isdeleted  ");
				 * sql.append("from apm_charges_invoice "); sql.
				 * append("inner join apm_patient on apm_patient.id = apm_charges_invoice.clientid "
				 * );
				 * sql.append("left join apm_user on apm_user.id =apm_charges_invoice.practid "
				 * ); sql.
				 * append("left join apm_third_party_details on apm_third_party_details.id = apm_charges_invoice.tpid "
				 * ); sql.
				 * append("left join apm_charges_payment on apm_charges_payment.chargeinvoiceid = apm_charges_invoice.id "
				 * ); sql.append("where apm_charges_invoice.date between '"+fromDate+"' AND '"
				 * +toDate+"' ");
				 */
				/* @end rahul because slow report opening remove left join */
				
				/* @rahul new code */
				/*
				 * again change inner join to left join beacues when create invoice it not
				 * showing in report and remove paymode!='prepayment' frm date 
				 */
				sql.append("select apm_charges_invoice.id,apm_charges_invoice.payby,apm_charges_invoice.date,apm_charges_invoice.clientid,apm_charges_invoice.debit,deliverstatus, ");
				sql.append("apm_charges_invoice.discount,apm_charges_invoice.tpid,apm_charges_invoice.disctype,apm_charges_invoice.discamt,apm_charges_invoice.itype, ");
				sql.append("apm_patient.abrivationid, ");
				sql.append("apm_charges_invoice.ipdid,apm_charges_invoice.isdeleted,practid  ");
				sql.append("from apm_charges_invoice ");
				sql.append("left join apm_charges_payment on apm_charges_payment.chargeinvoiceid=apm_charges_invoice.id ");    
				sql.append("inner join apm_patient on apm_patient.id = apm_charges_invoice.clientid ");
				sql.append("where apm_charges_invoice.date between '"+fromDate+"' AND '"+toDate+"' ");  //add inner join apm_charges_payment and chnage date to apm payment 
//					}else{
//						sql.append("select apm_charges_invoice.id,payby,date,clientid,debit,deliverstatus,discount,tpid,company_name,apm_charges_invoice.disctype,discamt,apm_charges_invoice.itype,apm_patient.abrivationid from apm_charges_invoice inner join ");
//						sql.append("apm_third_party_details on apm_third_party_details.id = apm_charges_invoice.tpid ");
//						sql.append("where payby='Third Party' ");
//						if(!invcategory.equals("2")){
//							sql.append("and apm_charges_invoice.invpstype="+invcategory+" ");
//						}
//					}
//				}
//				
//				
//			}
			
//			if(!thirdparty.equals("0")){
//				sql = new StringBuffer();
//				
//				if(!fromDate.equals("") && !toDate.equals("")){
//					
//					//sql.append("select id,payby,date,clientid,debit,deliverstatus,discount,tpid,company_name from apm_charges_invoice where payby='Third Party' and tpid="+thirdparty+" and  date between '"+fromDate+"' AND '"+toDate+"'  ") ;
//					sql.append("select apm_charges_invoice.id,payby,date,clientid,debit,deliverstatus,discount,tpid,company_name,apm_charges_invoice.disctype,discamt,apm_charges_invoice.itype,apm_patient.abrivationid from apm_charges_invoice inner join ");
//					sql.append("apm_patient on apm_patient.id = apm_charges_invoice.clientid ");
//					sql.append("inner join apm_third_party_details on apm_third_party_details.id = apm_charges_invoice.tpid ");
//					sql.append("where payby='Third Party' and  date between '"+fromDate+"' AND '"+toDate+"' and apm_third_party_details.id="+thirdparty+"  ");
//					if(!invcategory.equals("2")){
//						sql.append("and apm_charges_invoice.invpstype="+invcategory+" ");
//					}
//				}else{
//					sql.append("select id,payby,date,clientid,debit,deliverstatus,discount,tpid,company_name,apm_charges_invoice.disctype,discamt,apm_charges_invoice.itype,apm_patient.abrivationid from apm_charges_invoice where payby='Third Party' and tpid="+thirdparty+"  ") ;
//					if(!invcategory.equals("2")){
//						sql.append("and apm_charges_invoice.invpstype="+invcategory+" ");
//					}
//				}
//				
//			}
			
			
//		}
		if(invoicetype!=null){
		   if(!invoicetype.equals("0")){
			   sql.append(" and  apm_charges_invoice.itype="+invoicetype+" ");
		   }
		}
		if(!payby.equals("0")){
			sql.append("and apm_charges_invoice.payby= '"+payby+"' ");
		}
		//sql.append(" group by apm_charges_invoice.id  ");
		/*sql.append("order by "+orderby+" "+order+" ");*/
		sql.append("order by apm_charges_invoice.id desc ");
		OutstandingReportDAO outstandingReportDAO=new JDBCOutstandingReportDAO(connection);
		 
		try{
			preparedStatement = connection.prepareStatement(sql.toString());
			ResultSet rs = preparedStatement.executeQuery();
			double payment=0;
			BedDao bedDao = new JDBCBedDao(connection);
			EmrDAO em=new JDBCEmrDAO(connection);
			AccountsDAO accountsDAO=new JDBCAccountsDAO(connection);
			while(rs.next()){
				double balance = 0;
				double result = 0.0;
				double creditAmount = 0;
				
				Accounts accounts = new Accounts();
				accounts.setId(rs.getInt(1));
				accounts.setPayby(rs.getString(2));
				accounts.setDate(DateTimeUtils.getCommencingDate1(rs.getString(3)));
				accounts.setClientid(rs.getInt(4));
				
				accounts.setDebitAmount(rs.getDouble(5));
				accounts.setDebitAmountx(DateTimeUtils.changeFormat(rs.getDouble(5)));
				
				accounts.setDeliverstatus(rs.getString(6));
				
				double debit = rs.getDouble(5);
				double total = rs.getDouble(5);
				int disctype = rs.getInt(9);
				double discamt = rs.getDouble(10);
				double discount = rs.getDouble(7);
				double r1 = (total*discount)/100;
				if(disctype==1){
					r1 = discamt;
				}
				accounts.setDiscAmmount(DateTimeUtils.changeFormat(r1));
				
				total = total-r1;
				result = result + total;
				
				creditAmount = getCreditAmount(rs.getDouble(1));
				
				//if refund against invoice
				double refundAmt = statementDAO.getRefundAmtAgainsInvoice(rs.getInt(1));
				accounts.setRefundAmountx(DateTimeUtils.changeFormat(refundAmt));
				creditAmount = creditAmount - refundAmt;
				
				accounts.setCreditAmount(creditAmount);
				accounts.setCreditCharge(Double.toString(creditAmount));
				accounts.setCreditTotalx(DateTimeUtils.changeFormat(creditAmount));
				
				
				balance = result - creditAmount;
				accounts.setBalance(balance);
				accounts.setBalancex(DateTimeUtils.changeFormat(balance));
				
				int noOfCharges = getNoOfCharges(rs.getInt(1));
				accounts.setNumberOfChages(noOfCharges);
				accounts.setDiscount(rs.getDouble(7));
				String clientname = getClientFullName(rs.getString(4));
				accounts.setClientName(clientname);
				String payee = rs.getString(2);
				String payeename = null;
				if(payee.equalsIgnoreCase("Third Party")){
					payeename = getTpname(rs.getInt(8));
					
				}
				else{
					payeename = clientname;
				}
				accounts.setPayeeName(payeename);
				//showing seq no instead of invoice id 21/09/2018
				
			    String ipdopdseq=accountsDAO.getIpdOpdSeqNoWithType(rs.getInt(1));
			    String inName=accountsDAO.getInvoiceName(rs.getString(11));
			    accounts.setInvoicetype(inName);
			    accounts.setIpdopdseq(ipdopdseq);
			    accounts.setAbrivationid(rs.getString(12));
			    accounts.setTotalAmount(debit);
			    String practionerName=getPractionerName(rs.getInt(15));
			    accounts.setPractitionerName(practionerName);
			    int invoiceid=outstandingReportDAO.getInvoiceId(rs.getInt(1));
			    int appointmentid=accountsDAO.getAppointmentIdFromInvoiceid(""+invoiceid);
			    if(rs.getInt(13)>0){
			    	accounts.setIpdid(ipdDAO.getipdseqno(rs.getString(13)));
			    	if(loginInfo.getIpd_abbr_access() == 1){
			    		String newipdabbr = ipdDAO.getIpdAbrivationIds(rs.getInt(13));
			    		accounts.setIpdid(newipdabbr);
					}
			    	
			    	Bed bed= bedDao.getTreatmentNAdmissiondate(rs.getString(13));
			    	String dischargeDate=DateTimeUtils.isNull(ipdDAO.getIpdDischargeDate(bed.getTreatmentepisodeid()));
			    	String admissionDate="";
			    	if(DateTimeUtils.isNull(bed.getAdmissiondate()).contains(":")){
			    		 admissionDate=DateTimeUtils.getCommencingDate1(bed.getAdmissiondate().split(" ")[0])+" "+bed.getAdmissiondate().split(" ")[1] ;
			    	}
			    	accounts.setAdmissiondate(admissionDate);
			    	if(dischargeDate.contains(":")){
			    		dischargeDate=DateTimeUtils.getCommencingDate1(dischargeDate.split(" ")[0])+" "+dischargeDate.split(" ")[1] ;
			    	}
			    	accounts.setDischargedate(dischargeDate);
			    }else{
			    	accounts.setIpdid(""+em.getOpdAbbrevationId(appointmentid));
			    }
			    /*if(loginInfo.isBalgopal()){*/
			    	accounts.setIpdopdseq(accountsDAO.getFinancialSeqNoOfInvoice(""+rs.getInt(1)));
			    	String paymentNote = accountsDAO.getInvoicePaymentNote(rs.getInt(1));
			    	String note=accountsDAO.getInvoiceNote(rs.getInt(1));
			    	accounts.setPaymentNote(paymentNote);
			    	accounts.setNotes(note);
				/*}*/
			    
				if(paymentStatus!=null){
					if(paymentStatus.equals("Paid")){
						if(balance==0){
							list.add(accounts);
						}
					}else if(paymentStatus.equals("Not Paid")){
						if(balance!=0){
							list.add(accounts);
						}
					}else{
						list.add(accounts);
					}
				}else{
					list.add(accounts);
				}
				
				if(rs.getInt(14)>0){
					accounts.setCancelNotes("Cancelled");
				}
				
				
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
		
		return list;
	}
	private String getPractionerName(int practid) {
		PreparedStatement preparedStatement = null;
		String result = "";
		String sql = "SELECT concat(apm_user.initial,' ',apm_user.firstname,' ',apm_user.lastname) FROM apm_user where id = "+practid+"";
		
		try{
			preparedStatement = connection.prepareStatement(sql);
			
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()){
				result = rs.getString(1);
			}
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		return result;
		
	}

	public String getTpname(int id) {
		PreparedStatement preparedStatement = null;
		String result = "";
		String sql = "SELECT company_name FROM apm_third_party_details where id = "+id+"";
		
		try{
			preparedStatement = connection.prepareStatement(sql);
			
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()){
				result = rs.getString(1);
			}
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public String getClientFullName(String id) {
		PreparedStatement preparedStatement = null;
		String result = "";
		String sql = "SELECT title,firstname,surname FROM apm_patient where  id = "+id+"";
		
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
	
	
	public String getClientUHID(String id) {
		PreparedStatement preparedStatement = null;
		String result = "";
		String sql = "SELECT abrivationid FROM apm_patient where  id = "+id+"";
		
		try{
			preparedStatement = connection.prepareStatement(sql);
			
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()){
				result = rs.getString(1);
			}
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	public int getNoOfCharges(int id) {
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
	
	
	
	public ArrayList<Accounts> getAdvPaymentModeWiseList(String fromDate, String toDate,
			int itype, String payby, String howpaid, String invoicecategory,
			String userid){
		
		PreparedStatement preparedStatement = null;
		ArrayList<Accounts> list = new ArrayList<Accounts>();
		StringBuffer str = new StringBuffer();
		
		toDate = toDate + " 23:59:59";
		
		str.append("SELECT sum(charge),payment_mode ");
		str.append("FROM apm_credit_account inner join apm_patient on apm_patient.id = apm_credit_account.client_id ");
		str.append("where payment_mode IS NOT NULL and date between '"+fromDate+"' and '"+toDate+"' and charge>0 ");
		
		if(payby!=null){
			
		
			
			if(!howpaid.equals("0")){
				str.append("and apm_credit_account.payment_mode='"+howpaid+"' ");
			}
			
			/*if(!userid.equals("0")){
				str.append("and apm_credit_account.userid='"+userid+"' ");
			}*/
			
			if(userid!=null){
				if(userid.equals("")||userid.equals("0") ){
					
				}else{
					str.append("and apm_credit_account.userid='"+userid+"' ");
				}
				
				
				
			}
			
			
		}
		
		str.append("group by payment_mode ");
		
		try{
			preparedStatement = connection.prepareStatement(str.toString());
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()){
				Accounts accounts = new Accounts();
				accounts.setAmount(rs.getDouble(1));
				accounts.setPayAmountx(DateTimeUtils.changeFormat(rs.getDouble(1)));
				accounts.setPaymentmode(rs.getString(2));
				
				list.add(accounts);
			}
			
		}catch (Exception e) {
			// TODO: handle exception
		}
		
		
		return list;
	}
	
	public ArrayList<Accounts> getPaymentModeWiseList(String fromDate, String toDate,
			int itype, String payby, String howpaid, String invoicecategory,
			String userid){
		
		PreparedStatement preparedStatement = null;
		ArrayList<Accounts> list = new ArrayList<Accounts>();
		StringBuffer str = new StringBuffer();
		
		toDate = toDate + " 23:59:59";
		
		str.append("select sum(payment),paymode FROM apm_charges_payment ");
		str.append("inner join apm_charges_invoice on apm_charges_invoice.id = apm_charges_payment.chargeinvoiceid ");
		str.append("where apm_charges_payment.date between '"+fromDate+"' AND '"+toDate+"' and paymode!='prepayment' ");
		str.append("and itype = "+itype+" ");
		
		if(payby!=null){
			if(!payby.equals("0")){
				if(!payby.equals("Client")){
					str.append("and apm_charges_payment.tpid=0");
				}else{
					str.append("and apm_charges_payment.tpid!=0");
				}
			}
		
			
			if(!howpaid.equals("0")){
				str.append("and apm_charges_payment.paymode='"+howpaid+"' ");
			}
			
			/*if(!userid.equals("0")){
				str.append("and apm_charges_payment.userid='"+userid+"' ");
			}*/
			
			if(userid!=null){
				if(userid.equals("")||userid.equals("0") ){
					
				}else{
					str.append("and apm_charges_payment.userid='"+userid+"' ");
				}
				
			}
			
			
		}
		
		str.append("group by paymode ");
		
		try{
			preparedStatement = connection.prepareStatement(str.toString());
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()){
				Accounts accounts = new Accounts();
				accounts.setAmount(rs.getDouble(1));
				accounts.setPayAmountx(DateTimeUtils.changeFormat(rs.getDouble(1)));
				accounts.setPaymentmode(rs.getString(2));
				
				list.add(accounts);
			}
			
		}catch (Exception e) {
			// TODO: handle exception
		}
		
		
		return list;
	}

	public ArrayList<Accounts> getPaymentReportList(String fromDate,
			String toDate,String payby,String howpaid,String orderby,String order,String invcategory,String userid,String selectedInvctype, String sortfilter) {
		String tp = "Third Party";String self = "Client";
		PreparedStatement preparedStatement = null;
		ArrayList<Accounts>list = new ArrayList<Accounts>();
		StatementDAO statementDAO = new JDBCStatementDAO(connection);
		StringBuffer sql = new StringBuffer();
		//discamt colomn ambigueous so apm_chages_payment.discamt 
		if(payby!=null){
			if(!payby.equals("0")){
				if(payby.equals(Constants.PAY_BY_CLIENT)){
					sql.append("select apm_charges_payment.id,apm_charges_payment.clientid,chargeinvoiceid,payment,paymode,apm_charges_payment.date,apm_charges_payment.tpid,payby,firstname,apm_charges_payment.userid,apm_charges_invoice.id, concat(apm_user.initial,' ',apm_user.firstname,' ',apm_user.lastname),apm_charges_invoice.itype,apm_charges_payment.ipdno,apm_charges_payment.opdno,apm_charges_payment.invno,apm_charges_payment.cyear,concat(his_invoice_payment_sno.cyear,'/',his_invoice_payment_sno.sno),paymentnote,chequeno,cancelinv, ");
					sql.append("debit,discount,apm_charges_invoice.disctype,apm_charges_payment.discamt,apm_charges_invoice.date,apm_charges_invoice.time,practid ");
					sql.append("FROM apm_charges_payment ");
					sql.append("inner join apm_charges_invoice on apm_charges_invoice.id = apm_charges_payment.chargeinvoiceid ");
		  			sql.append(" left join apm_user on apm_user.id =apm_charges_invoice.practid ");
					sql.append("inner join apm_patient on apm_patient.id = apm_charges_payment.clientid ");
					sql.append(" left join his_invoice_payment_sno on his_invoice_payment_sno.paymentid= apm_charges_payment.id ");
					
				}else{
					sql.append("select apm_charges_payment.id,apm_charges_payment.clientid,chargeinvoiceid,payment,paymode,apm_charges_payment.date,apm_charges_payment.tpid,payby,company_name,apm_charges_payment.userid,apm_charges_invoice.id, concat(apm_user.initial,' ',apm_user.firstname,' ',apm_user.lastname),apm_charges_invoice.itype,apm_charges_payment.ipdno,apm_charges_payment.opdno,apm_charges_payment.invno,apm_charges_payment.cyear,concat(his_invoice_payment_sno.cyear,'/',his_invoice_payment_sno.sno),paymentnote,chequeno,cancelinv, ");
					sql.append("debit,discount,apm_charges_invoice.disctype,apm_charges_payment.discamt,apm_charges_invoice.date,apm_charges_invoice.time,practid ");
					sql.append("FROM apm_charges_payment ");
					sql.append("inner join apm_charges_invoice on apm_charges_invoice.id = apm_charges_payment.chargeinvoiceid ");
					sql.append(" left join apm_user on apm_user.id =apm_charges_invoice.practid ");
					sql.append("inner join apm_third_party_details on apm_third_party_details.id = apm_charges_payment.tpid ");
					sql.append(" left join his_invoice_payment_sno on his_invoice_payment_sno.paymentid= apm_charges_payment.id ");
				}
			}else{
				sql.append("select apm_charges_payment.id,apm_charges_payment.clientid,chargeinvoiceid,payment,paymode,apm_charges_payment.date,apm_charges_payment.tpid,payby,apm_charges_payment.clientid,apm_charges_payment.userid,apm_charges_invoice.id, concat(apm_user.initial,' ',apm_user.firstname,' ',apm_user.lastname),apm_charges_invoice.itype,apm_charges_payment.ipdno,apm_charges_payment.opdno,apm_charges_payment.invno,apm_charges_payment.cyear,concat(his_invoice_payment_sno.cyear,'/',his_invoice_payment_sno.sno),paymentnote,chequeno,cancelinv, ");
				sql.append("debit,discount,apm_charges_invoice.disctype,apm_charges_payment.discamt,apm_charges_invoice.date,apm_charges_invoice.time,practid ");
				sql.append("FROM apm_charges_payment ");
				sql.append("inner join apm_charges_invoice on apm_charges_invoice.id = apm_charges_payment.chargeinvoiceid ");
				sql.append(" left join apm_user on apm_user.id =apm_charges_invoice.practid ");
				sql.append(" left join his_invoice_payment_sno on his_invoice_payment_sno.paymentid= apm_charges_payment.id ");
			}
		}else{
			sql.append("select apm_charges_payment.id,apm_charges_payment.clientid,chargeinvoiceid,payment,paymode,apm_charges_payment.date,apm_charges_payment.tpid,payby,apm_charges_payment.clientid,apm_charges_payment.userid,apm_charges_invoice.id, concat(apm_user.initial,' ',apm_user.firstname,' ',apm_user.lastname),apm_charges_invoice.itype,apm_charges_payment.ipdno,apm_charges_payment.opdno,apm_charges_payment.invno,apm_charges_payment.cyear,concat(his_invoice_payment_sno.cyear,'/',his_invoice_payment_sno.sno),paymentnote,chequeno,cancelinv, ");
			sql.append("debit,discount,apm_charges_invoice.disctype,apm_charges_payment.discamt,apm_charges_invoice.date,apm_charges_invoice.time,practid ");
			sql.append("FROM apm_charges_payment ");
			sql.append("inner join apm_charges_invoice on apm_charges_invoice.id = apm_charges_payment.chargeinvoiceid ");
			sql.append(" left join apm_user on apm_user.id =apm_charges_invoice.practid ");
			sql.append(" left join his_invoice_payment_sno on his_invoice_payment_sno.paymentid= apm_charges_payment.id ");
			
		}
	
	
		
		
		if(!fromDate.equals("") && !toDate.equals("")){
			
			/*if(fromDate.equals(toDate)){
				toDate = toDate + " " + "23:59:59";
			}else{
				if(!toDate.equals("")){
					String dt = toDate;
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
					Calendar c = Calendar.getInstance();
					try {
						c.setTime(sdf.parse(dt));
						c.add(Calendar.DATE, 1);  // number of days to add
						dt = sdf.format(c.getTime());  // dt is now the new date
						toDate = dt + " " + "23:59:59";
					} catch (ParseException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			
			}*/
			toDate = toDate + " " + "23:59:59";
			
			//sql.append("select id,clientid,chargeinvoiceid,payment,paymode,date,tpid from apm_charges_payment where date between '"+fromDate+"' AND '"+toDate+"' order by id desc ") ;
			
			sql.append("where apm_charges_payment.date between '"+fromDate+"' AND '"+toDate+"' ");
			
			
			if(payby!=null){
				if(!payby.equals("0") && !howpaid.equals("0") ){
					sql.append("and payby='"+payby+"' and paymode='"+howpaid+"' ");
				}else if(!payby.equals("0")){
					sql.append("and payby='"+payby+"' " );
				}else if(!howpaid.equals("0")){
					sql.append("and paymode='"+howpaid+"' " );
				}
			}
		}else{
			
			if(payby!=null){
				if(!payby.equals("0") && !howpaid.equals("0") ){
					sql.append("where payby='"+payby+"' and paymode='"+howpaid+"' ");
				}else if(!payby.equals("0")){
					sql.append("where payby='"+payby+"' " );
				}else if(!howpaid.equals("0")){
					sql.append("where paymode='"+howpaid+"' " );
				}
			}
			
		}
		
		if(!invcategory.equals("2")){
			
			sql.append("and apm_charges_invoice.invpstype="+invcategory+" ");
		}
		
		if(!userid.equals("0")){	 
			sql.append(" and apm_charges_payment.userid='"+userid+"' ");
		}
		if(payby!=null){
		/*if(!userid.equals("0")){*/
		
		
			
		if(!selectedInvctype.equals("0")){
			String temp[] = selectedInvctype.split(",");
			if(temp.length>1){
				//selectedInvctype = selectedInvctype.substring(selectedInvctype.length()-1);
				sql.append(" and itype in("+selectedInvctype+") ");
			}
		}
		}
		
		sql.append("and paymode!='prepayment' ");
		if(sortfilter.equals("0")){
			sql.append(" order by itype ");
		}else if(sortfilter.equals("0")){
			sql.append(" order by chargeinvoiceid ");
		}else{
			sql.append(" order by apm_charges_payment.id ");
		}
		//sql.append("and paymode!='prepayment' order by chargeinvoiceid ");
		try{
			preparedStatement = connection.prepareStatement(sql.toString());
			ResultSet rs = preparedStatement.executeQuery();
			
			double opdtotal = 0;
			double ipdtotal = 0;
			double pathlabtotal = 0;
			double mdcinetotal = 0;
			double advreftotal = 0;
			double daycaretotal=0; 
			double balancetotal=0;
			double registrationTotal=0;
			AccountsDAO accountsDAO = new JDBCAccountsDAO(connection);
			String invoiceids="0";
			while(rs.next()){
				double result = 0.0;
				Accounts accounts = new Accounts();
				accounts.setId(rs.getInt(1));
				accounts.setClientid(rs.getInt(2));
				String clientName = getClientFullName(rs.getInt(2));
				String abrivationid =getClientAbrivationId(rs.getInt(2));
				accounts.setAbrivationid(abrivationid);
				accounts.setClientName(clientName);
				accounts.setInvoiceid(rs.getInt(3));
				accounts.setAmount(rs.getDouble(4));
				accounts.setAmountx(DateTimeUtils.changeFormat(rs.getDouble(4)));
				accounts.setPaymentmode(rs.getString(5));
				accounts.setDate(DateTimeUtils.getIndianDateTimeFormat(rs.getString(6)));
				accounts.setInvoicedate(DateTimeUtils.getCommencingDate1(rs.getString(26))+" "+rs.getString(27));
				String whopay = getWhoPay(rs.getInt(3));
				accounts.setWhoPay(whopay);
				if(whopay.equals(tp)){
					String invoicee = getThirdPartyCompanyName(rs.getString(7));
					accounts.setInvoicee(invoicee);
				}else{
					accounts.setInvoicee(clientName);
				}
				accounts.setUserid(rs.getString(10));
				
				int invoiceid = rs.getInt(11);
				invoiceids = invoiceids +","+invoiceid;
				accounts.setInvoiceids(invoiceids);
				accounts.setInvoiceid(invoiceid);
				
				//ArrayList<Master>masterAssessmentList = accountsDAO.getMasterAssessmentPmntRptList(invoiceid,fromDate,toDate,userid);
				//accounts.setMasterAssessmentList(masterAssessmentList);
				
				String invoicenameid=accountsDAO.getInvoiceTypeId(invoiceid);
				if(invoicenameid.equals("1")){
					opdtotal = opdtotal + accounts.getAmount();
				}
				
				if(invoicenameid.equals("2")){
					ipdtotal = ipdtotal + accounts.getAmount();
				}
				
				if(invoicenameid.equals("3")){
					pathlabtotal = pathlabtotal + accounts.getAmount();
				}
				
				if(invoicenameid.equals("4")||invoicenameid.equals("6")||invoicenameid.equals("7")){
					mdcinetotal = mdcinetotal + accounts.getAmount();
				}

				if(invoicenameid.equals("8")){
					daycaretotal = daycaretotal + accounts.getAmount();
				}
				
				if(invoicenameid.equals("10")){
					registrationTotal = registrationTotal + accounts.getAmount();
				}
				
				String itype=rs.getString(13);
				String ipdid=rs.getString(14);
				String opdid=rs.getString(15);
				String invstid=rs.getString(16);
				String cyear=rs.getString(17);
				String type1="";
				/*if(itype!=null){
					if(itype.equals("2")){
						type1=cyear+"/"+ipdid;
					}else if(itype.equals("1")){
						type1=cyear+"/"+opdid;
					}else if(itype.equals("3")){
						type1=cyear+"/"+invstid;
					}else{
						type1=String.valueOf(accounts.getId());
					}*/
				if(rs.getString(18)!=null){
				type1= rs.getString(18);
				accounts.setNewsr(type1);	
				}else{
					accounts.setNewsr(String.valueOf(accounts.getId()));	
				}
				accounts.setOpdtotal(DateTimeUtils.changeFormat(opdtotal));
				accounts.setIpdtotal(DateTimeUtils.changeFormat(ipdtotal));
				accounts.setPathlabtotal(DateTimeUtils.changeFormat(pathlabtotal));
				accounts.setMdcinetotal(DateTimeUtils.changeFormat(mdcinetotal));
				accounts.setDaycaretotal(DateTimeUtils.changeFormat(daycaretotal));
				accounts.setRegistrationTotal(DateTimeUtils.changeFormat(registrationTotal));
				accounts.setPractitionerName(rs.getString(12));
				
				String invoicename=accountsDAO.getInvoiceName(invoicenameid);
				accounts.setInvoicenameid(invoicename);
				//showing seqno instead of invoice no.  
				//AccountsDAO accountsDAO2=new JDBCAccountsDAO(connection);
			    int ipdopdseq=accountsDAO.getIpdOpdSeqNo((rs.getInt(3)));
			    accounts.setIpdopdseq(String.valueOf(ipdopdseq));
			    
			    String finseq=accountsDAO.getFinancialSeqNoOfInvoice(""+rs.getInt(3));
				accounts.setBghseqId(finseq);
			    
			    String paymentnote=rs.getString(19);
			    String chequeno=rs.getString(20);
			    if(rs.getString(5).equals("Cheque")){
					   accounts.setPaymentNote(chequeno);
				   }else {
					accounts.setPaymentNote(paymentnote);
				}
			    accounts.setCancelsts(rs.getString(21));
			    accounts.setPhysical_payment_id(getPhysicalpaymentId(rs.getString(1)));
			    
			    
			    double total = rs.getDouble(22);
				int disctype = rs.getInt(24);
				double discamt = rs.getDouble(25);
				double discount = rs.getDouble(23);
				double r1 = (total*discount)/100;
				if(disctype==1){
					r1 = discamt;
				}
				accounts.setDiscAmmount(DateTimeUtils.changeFormat(r1));
				accounts.setDebitAmountx(DateTimeUtils.changeFormat(total));
				total = total-r1;
				result = result + total;
				
				double creditAmount = getCreditAmount(rs.getDouble(11));
				
				//if refund against invoice
				double refundAmt = statementDAO.getRefundAmtAgainsInvoice(invoiceid);
				accounts.setRefundAmountx(DateTimeUtils.changeFormat(refundAmt));
				creditAmount = creditAmount - refundAmt;
				
				accounts.setCreditAmount(creditAmount);
				accounts.setCreditCharge(Double.toString(creditAmount));
				accounts.setCreditTotalx(DateTimeUtils.changeFormat(creditAmount));
				
				
				double balance = result - creditAmount;
				accounts.setBalance(balance);
				accounts.setBalancex(DateTimeUtils.changeFormat(balance));
			    accounts.setBalanceTotal(balancetotal+balance);
			    balancetotal=accounts.getBalanceTotal();
			    
			    String locationName = getChargeDepartmentName(rs.getInt(28));
			    accounts.setLocationName(locationName);
			    if(rs.getInt(7)>0){
			    	String payer = getThirdPartyCompanyName(rs.getString(7));
					accounts.setPayer(payer);
			    }
			   
			    
				list.add(accounts);
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
		
		return list;
	}
	private String getClientAbrivationId(int id) {
		PreparedStatement preparedStatement = null;
		String result = "";
		String sql = "SELECT abrivationid FROM apm_patient where  id = "+id+"";
		
		try{
			preparedStatement = connection.prepareStatement(sql);
			
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()){
				result = rs.getString(1);
			}
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	private String getWhoPay(int id) {
		PreparedStatement preparedStatement = null;
		String result = "";
		String sql = "SELECT payby from apm_charges_invoice where  id = "+id+"";
		
		try{
			preparedStatement = connection.prepareStatement(sql);
			
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()){
				result = rs.getString(1);
			}
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	public String getThirdPartyCompanyName(String companyName) {
		PreparedStatement preparedStatement = null;
		String result = "";
		String sql = "SELECT company_name FROM apm_third_party_details where  id = "+companyName+"";
		
		try{
			preparedStatement = connection.prepareStatement(sql);
			
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()){
				result = rs.getString(1);
			}
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	public String getClientFullName(int id) {
		PreparedStatement preparedStatement = null;
		String result = "";
		String sql = "SELECT title,firstname,surname FROM apm_patient where  id = "+id+"";
		
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

	public int moveInvoiceToScecondary(String hdnprimaryinvoice) {
		PreparedStatement preparedStatement = null;
		int result = 0;
		
		int invpstype = getInvpstype(hdnprimaryinvoice);
		
		String sql = "update apm_charges_invoice  set invpstype="+invpstype+" where id in("+hdnprimaryinvoice+") ";
		
		try{
			preparedStatement = connection.prepareStatement(sql);
			result = preparedStatement.executeUpdate();
			
		}catch (Exception e) {
			// TODO: handle exception
		}
		return result;
	}

	private int getInvpstype(String invoicesid) {
		PreparedStatement preparedStatement = null;
		int result = 0;
		String sql = "select invpstype from apm_charges_invoice where id in("+invoicesid+") group by invpstype ";
		
		try{
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			if(rs.next()){
				result = rs.getInt(1);
			}
			
			if(result==0){
				result = 1;
				return result;
			}
			
			if(result==1){
				result = 0;
				return result;
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public ArrayList<Accounts> getAdvanceInvoiceReportList(String fromDate,
			String toDate, String payby, String paymentStatus,
			String thirdParty, String orderby, String order,
			String invoicecategory, String invcetype,String userid) {
		PreparedStatement preparedStatement = null;
		ArrayList<Accounts>list = new ArrayList<Accounts>();
		//String sql = "SELECT id,date,payby,payment_mode,charge,debit,apm_credit_accountbalance FROM apm_credit_account where client_id = "+clientId+" and payment_mode IS NOT NULL";
		
		toDate = toDate + " 23:59:59";
		
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT apm_credit_account.id,date,payby,payment_mode,charge,debit,balance,third_party_name_id,concat(title,' ',firstname,' ',surname),advref,resetinv,payment_mode,userid,apm_credit_account.cyear,apm_credit_account.advno,apm_credit_account.credit_note,apm_credit_account.invoiceid,apm_credit_account.cancelpay FROM apm_credit_account inner join ");
		sql.append("apm_patient on apm_patient.id = apm_credit_account.client_id where payment_mode IS NOT NULL and date between '"+fromDate+"' and '"+toDate+"' and charge>0 ");
		if(userid!=null){
			sql.append(" and userid='"+userid+"' ");
		}
		
		sql.append("order by resetinv ");
		
		
		try{
			
			preparedStatement = connection.prepareStatement(sql.toString());
			ResultSet rs = preparedStatement.executeQuery();
			
			double advtotal = 0;
			while(rs.next()){
				Accounts accounts = new Accounts();
				accounts.setId(rs.getInt(1));
				String date = DateTimeUtils.getIndianDateTimeFormat(rs.getString(2));
				accounts.setDate(date);
				String temp[] = date.split(" ");		
				accounts.setCommencing(temp[0]);
				accounts.setPayby(rs.getString(3));
				accounts.setPaymentmode(rs.getString(4));
				accounts.setCharges(DateTimeUtils.changeFormat(Double.parseDouble(rs.getString(5))));
				advtotal = advtotal + rs.getDouble(5);
				accounts.setDebitTotalx(DateTimeUtils.changeFormat(Double.parseDouble(rs.getString(6))));
				accounts.setAdvreftotal(DateTimeUtils.changeFormat(advtotal));
				
				double credit = rs.getDouble(5);
				double debit = rs.getDouble(6);
				double balance = 0;
				
				accounts.setDebitAmount(debit);
				accounts.setCreditAmount(credit);
				accounts.setBalance(balance);
				
				if(rs.getString(3).equals(Constants.PAY_BY_THIRD_PARTY)){
					
					CompleteAptmDAO completeAptmDAO = new JDBCCompleteAptmDAO(connection);
				    CompleteAppointment	completeAppointment = completeAptmDAO.getInsuranceCompanyName(rs.getString(8));
				    accounts.setClientName(completeAppointment.getInsuranceCompanyName());
				}
				else{
					
					accounts.setClientName(rs.getString(9));
				}
				
				
				accounts.setBalancex(DateTimeUtils.changeFormat(balance));
				accounts.setAdvref(rs.getInt(10));
				accounts.setResetinv(rs.getString(11));
				accounts.setPaymentmode(rs.getString(12));
				accounts.setUserid(rs.getString(13));
				accounts.setAdvno(rs.getString(14)+"/"+rs.getString(15));
				accounts.setRemark(rs.getString(16));
				String receipt=getpaymentreceipt(rs.getInt(17));
				accounts.setReceiptid(receipt);
				accounts.setCancelsts(rs.getString(18));
				list.add(accounts);
			}
			
			
		}catch(Exception e){
			e.printStackTrace();
		}
		return list;
	}

	private String getpaymentreceipt(int int1) {
		PreparedStatement preparedStatement = null;
		String sql = "select id from apm_charges_payment where chargeinvoiceid="+int1+" ";
		
		String result = "";
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

	public ArrayList<Accounts> getDoctorShareReport(String fromdate,
			String todate,String pracId,String jobtitle) {

		ArrayList<Accounts> superInvoiceList=new ArrayList<Accounts>();
		UserProfileDAO userProfileDAO=new JDBCUserProfileDAO(connection);
		StringBuffer buffer=new StringBuffer();
		try {
			
			buffer.append("select id from apm_charges_invoice where date between '"+fromdate+"' and '"+todate+"' order by id desc ");
			 /*buffer.append("select apm_charges_invoice.id,apm_invoice_assesments.appointmentid from apm_invoice_assesments ");
			   buffer.append("inner join apm_charges_assesment on apm_invoice_assesments.invoiceid=apm_charges_assesment.invoiceid ");
			   buffer.append("inner join apm_charges_invoice on apm_charges_assesment.chargeinvoiceid = apm_charges_invoice.id and ");
			   buffer.append("date between '"+fromdate+"' and '"+todate+"' order by id desc ");*/

			PreparedStatement ps=connection.prepareStatement(buffer.toString());
			ResultSet rs=ps.executeQuery();
			
			while(rs.next()){
				
				Accounts accounts=new Accounts();
				int chargeinvoiceid=rs.getInt(1);
				String apmtid="0";
				accounts.setId(chargeinvoiceid);
				
				ArrayList<Accounts> practionerList=  getPractionerListofInvoice(chargeinvoiceid,pracId,jobtitle,apmtid);
			    for(Accounts acc:practionerList){
			    	acc.setId(chargeinvoiceid);
			    	
			    	String fullname=userProfileDAO.getFullName(String.valueOf(acc.getPractitionerId()));
			    	acc.setPractitionerName(fullname);
			    	
			    	ArrayList<Accounts> assesmentListCharges= getAllChargesByPracIdandInvoiceid(acc.getInvoiceid(),acc.getPractitionerId(),acc.getDiscount(),acc.getAppointmentid());
			    	ArrayList<Accounts> sharedChargeList=new ArrayList<Accounts>();
			    	boolean flag=false;
			    	double totalSub=0.0;
			    	double totalDisc=0.0;
			    	double shareTotal=0.0;
			    	int totalQty=0;
			    	if(assesmentListCharges.size()>0){
			    	for(Accounts acc1:assesmentListCharges) {
			    		acc1.setId(chargeinvoiceid);
			    		
			    		 boolean isshared= isshared(acc1.getChargeType(), acc.getPractitionerId());
			    		 if(isshared){
			    				totalQty=totalQty+acc1.getQuantity();
					    		totalSub=totalSub+acc1.getTotalAmount();
					    		totalDisc=totalDisc+acc1.getDiscount();
					    		shareTotal=shareTotal+acc1.getShareAmt();
			    			 
				    		   sharedChargeList.add(acc1);
				    		   flag=true;
				    	 }
			    		
			    	}
			    	}
			    	if(flag){
			    		
			    		String tsub=DateTimeUtils.changeFormat(totalSub);
			    		String tshar=DateTimeUtils.changeFormat(shareTotal);
			    		
			    		acc.setTotalSub(Double.parseDouble(tsub));
			    		acc.setTotalDisc(totalDisc);
			    		acc.setShareTotal(Double.parseDouble(tshar));
			    		acc.setTotalQty(totalQty);
			    		acc.setSharedChargeList(sharedChargeList);
			    		superInvoiceList.add(acc);
			    	}
			    }
			   
					
			}
			
			
		} catch (Exception e) {

			e.printStackTrace();
		}
		return superInvoiceList;
	}

	private ArrayList<Accounts> getPractionerListofInvoice(int chargeinvoiceid,String pracId,String jobtitle,String appointmentid) {
		
	   ArrayList<Accounts> list=new ArrayList<Accounts>();
	   StringBuffer buffer=new StringBuffer();
	   try {
		   buffer.append("select apm_invoice_assesments.invoiceid,apm_invoice_assesments.practitionerid,apm_charges_invoice.discount,apm_invoice_assesments.appointmentid from apm_invoice_assesments ");
		   buffer.append("inner join apm_charges_assesment on apm_invoice_assesments.invoiceid=apm_charges_assesment.invoiceid ");
		   buffer.append("inner join apm_charges_invoice on apm_charges_assesment.chargeinvoiceid = apm_charges_invoice.id ");
		   buffer.append("where apm_charges_invoice.id="+chargeinvoiceid+" ");
		   /*if(appointmentid!=null){
   	    	if(!appointmentid.equals("0")){
   	    		   NotAvailableSlot otobj = getOtAppointmentDetails(Integer.parseInt(appointmentid));
  		    	    
  					if(otobj.getSurgeon().equals("0")){
  						 buffer.append("inner join apm_user on apm_invoice_assesments.practitionerid=apm_user.id ");
  						 buffer.append("where apm_charges_invoice.id="+chargeinvoiceid+" ");
  						 
  						 if(jobtitle.equals("0")){
  						    buffer.append("and apm_user.jobtitle='Practitioner' ");
  					   } else {
  						   buffer.append("and apm_user.jobtitle!='Practitioner' ");
  					   }
  				  } else {
  					  
  					  buffer.append("where apm_charges_invoice.id="+chargeinvoiceid+" ");
  				  }
   	    	  }
     	   }*/
		  
		  
		   
		  
		   
		   if(!pracId.equals("0")){
			   
			    buffer.append("and apm_invoice_assesments.practitionerid="+pracId+" ");
		   }
		   
		   buffer.append("group by apm_invoice_assesments.invoiceid;");
		   PreparedStatement ps=connection.prepareStatement(buffer.toString());
		    ResultSet rs=ps.executeQuery();
		    
		    while(rs.next()){
		    	  
		    	    Accounts accounts=new Accounts();
		    	    accounts.setInvoiceid(rs.getInt(1));
		    	    accounts.setPractitionerId(rs.getInt(2));
		    	    accounts.setDiscount(rs.getDouble(3));
		    	    accounts.setAppointmentid(rs.getInt(4));
		    	    
		    	    String apmtid = rs.getString(4);
		    	    ArrayList<Accounts>otdoctorlist = new ArrayList<Accounts>();
		    	    if(apmtid!=null){
		    	    	if(!apmtid.equals("0")){
		    	    		   NotAvailableSlot otobj = getOtAppointmentDetails(Integer.parseInt(apmtid));
		   		    	    
		   					if(!otobj.getSurgeon().equals("0")){
		   						otdoctorlist = getOtDoctorList(accounts.getInvoiceid(),accounts.getDiscount(),Integer.parseInt(apmtid));
		   					}
		    	    	}
		    	    }
		    	 
		    	    
					
		    	   
		    	    list.add(accounts);
		    	    if(otdoctorlist.size()!=0){
		    	    	list.addAll(otdoctorlist);
		    	    }
 
		    }
		    
		    
	   } catch (Exception e) {

		   e.printStackTrace();
	   }	
	  return list;	
	}
	
	
	
	private ArrayList<Accounts> getOtDoctorList(int invoiceid, double discount,int apmtid) {
		
		ArrayList<Accounts>list = new ArrayList<Accounts>();
		 NotAvailableSlot otobj = getOtAppointmentDetails(apmtid);
		 DiaryManagementDAO diaryManagementDAO = new JDBCDiaryManagentDAO(connection);
		 String stafflistid = diaryManagementDAO.getAsistantDoctorList(apmtid);
		 stafflistid = stafflistid + "," + otobj.getAnesthesia() + "," + otobj.getSurgeon();
		 
		 String temps[] = stafflistid.split(",");
			for(int b=0;b<temps.length;b++){
				//if(b>0){
					String selectedpractid = temps[b];
					
					Accounts accounts = new Accounts();
					accounts.setInvoiceid(invoiceid);
					accounts.setDiscount(discount);
					accounts.setPractitionerId(Integer.parseInt(selectedpractid));
					accounts.setAppointmentid(apmtid);
					
					list.add(accounts);
				//}
			}
		 
		return list;
	}

	private boolean isshared(String chargeType,int doctorid) {
		
		try {
			String sql="select id from apm_shared_charges where chargetype='"+chargeType+"' and userid like('%"+doctorid+"%') ";
			PreparedStatement ps=connection.prepareStatement(sql);
			ResultSet rs=ps.executeQuery();
			
			while(rs.next()){
				
				return true;
			}
			
		} catch (Exception e) {

			e.printStackTrace();
		}
		
		return false;
	}
	
	
	public Accounts getSharedAssesmentAccount(int id) {
		
		Accounts accounts=new Accounts();
		try {
			
			String sql="select invoiceid,user,apmtType,charge,clientId,quantity,ipdid from apm_invoice_assesments where id="+id+"";
			PreparedStatement ps=connection.prepareStatement(sql);
			ResultSet rs=ps.executeQuery();
			while(rs.next()){
				
				   accounts.setInvoiceid(rs.getInt(1));
				   accounts.setClientName(rs.getString(2));
				   accounts.setAppointmentType(rs.getString(3));
				   accounts.setCharges(rs.getString(4));
				   accounts.setClientid(rs.getInt(5));
				   accounts.setQuantity(rs.getInt(6));
				   accounts.setIpdid(rs.getString(7));
				   
				   double total=Integer.parseInt(accounts.getCharges())*accounts.getQuantity();
				   accounts.setTotalAmount(total);
				   accounts.setDiscount(0.0);
				   accounts.setShareAmt(0.0);
			}
			
			
		} catch (Exception e) {

			e.printStackTrace();
		}
		
		return accounts;
	}
	
	
	public ArrayList<Accounts> getAllChargesByPracIdandInvoiceid(int invoiceid,int practitionerid,double discount,int apmtid){
		
		ArrayList<Accounts> list=new ArrayList<Accounts>();
		UserProfileDAO userProfileDAO=new JDBCUserProfileDAO(connection);
		ThirdPartyDAO thirdPartyDAO=new JDBCThirdPartyDAO(connection);
		try {
			
			String sql="select id,user,apmtType,charge,practitionerId,clientId,quantity,ipdid,masterchargetype,commencing,paybuy,thirdPartyId,appointmentid from apm_invoice_assesments where invoiceid="+invoiceid+" and  practitionerId="+practitionerid+"";
			ChargesReportDAO chargesReportDAO = new JDBCChargesReportDAO(connection);
			if(apmtid!=0){
			NotAvailableSlot otobj = chargesReportDAO.getOtAppointmentDetails(apmtid);
			if(!otobj.getSurgeon().equals("0")){
				 sql="select id,user,apmtType,charge,practitionerId,clientId,quantity,ipdid,masterchargetype,commencing,paybuy,thirdPartyId,appointmentid from apm_invoice_assesments where invoiceid="+invoiceid+" and appointmentid="+apmtid+"";
			}
			}
			
			PreparedStatement ps=connection.prepareStatement(sql);
			ResultSet rs=ps.executeQuery();
			SimpleDateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd");
			SimpleDateFormat format=new SimpleDateFormat("dd-MMM");
			while(rs.next()){
				
				    Accounts accounts=new Accounts();
				    accounts.setId(rs.getInt(1));
				    accounts.setClientName(rs.getString(2));
				    accounts.setAppointmentType(rs.getString(3));
				    accounts.setCharges(rs.getString(4));
				    accounts.setPractitionerId(rs.getInt(5));
				    accounts.setClientid(rs.getInt(6));
				    accounts.setQuantity(rs.getInt(7));
				    accounts.setIpdid(rs.getString(8));
				    accounts.setChargeType(rs.getString(9));
				    accounts.setCommencing(rs.getString(10));
				    accounts.setPayBy(rs.getInt(11));
				    accounts.setTpid(rs.getInt(12));
				    accounts.setAppointmentid(rs.getInt(13));
				    Calendar calendar=Calendar.getInstance();
				    if(accounts.getCommencing()!=null){
				    
				    Date date=dateFormat.parse(accounts.getCommencing()); 
				    calendar.setTime(date);
				    }
				    String month=format.format(calendar.getTime());
				    accounts.setMonth(month);
				    String wardname=getWardFromIpd(accounts.getIpdid());
				    accounts.setWard(wardname);
				    
				    
				    int ipdid=rs.getInt(8);
				    int userper=0;
				    
				 /*  boolean isprocdures=isProcedureChargeType(accounts.getChargeType()); 
				   if(isprocdures && accounts.getAppointmentid()>0){
							 //for ot   
							    NotAvailableSlot notAvailableSlot=getOtAppointmentDetails(accounts.getAppointmentid());
							    
							    if(accounts.getPayBy()==0){
							    	  //Self
							    	UserProfile userProfile=userProfileDAO.getUserprofileDetails(Integer.parseInt(notAvailableSlot.getSurgeon()));
							    	userper=userProfile.getSurgeonCharge();
							    		
							    } else {
							    	ThirdParty thirdParty=thirdPartyDAO.getThirdPartyDetails(String.valueOf(accounts.getTpid()));  
							    	   //tp
							    	userper=thirdParty.getSurgeonShare();
							    	
						       }
							   
					} else {*/
						
						if(accounts.getPayBy()==0){
					    	//self
					    	UserProfile userProfile=userProfileDAO.getUserprofileDetails(practitionerid);
					    	if(ipdid>1){
					    		//ipd
					    		userper=userProfile.getIpdCharge();
					    	} else {
					    		// opd
					    		userper=userProfile.getCompAppCharge();
					    	}
					    } else {
					    	ThirdParty thirdParty=thirdPartyDAO.getThirdPartyDetails(String.valueOf(accounts.getTpid()));
					    	//thirparty
					    	if(ipdid>1){
					    		//ipd
					    		userper=thirdParty.getIpdShare();
					    	} else {
					    		// opd
					    		userper=thirdParty.getOpdShare();
					    	}
					    }
						
					//}
						double totalAmt=0;
						if(accounts.getCharges()!=null){
						    if(!accounts.getCharges().equals("")){
						    	  totalAmt=Double.parseDouble(accounts.getCharges());
						    }
						} 
				    double perval=totalAmt*userper/100;
				    accounts.setCharges(DateTimeUtils.changeFormat(perval));
				   
				    totalAmt=perval*accounts.getQuantity();
				    accounts.setTotalAmount(totalAmt);
				    double discTotal=totalAmt*discount/100;
				    double shareamt=totalAmt-discTotal;
				    
				    accounts.setShareAmt(shareamt);
				    accounts.setDiscount(discTotal);
				    
				    
				    list.add(accounts);
			}
			
			
		} catch (Exception e) {

			e.printStackTrace();
		}
		return list;
	}
	
	

	public int getUserSharePer(int pracId) {
		
		int result=0;
		try {
			String sql="select completeAppCharge from apm_user where id="+pracId+"";
			PreparedStatement ps=connection.prepareStatement(sql);
			ResultSet rs=ps.executeQuery();
			
			while(rs.next()){
				  result=rs.getInt(1);
			}
			
		} catch (Exception e) {

			e.printStackTrace();
		}
		return result;
	}
	
	
	
	
	private String getWardFromIpd(String ipdid) {
		
		String wardname="";
		BedDao bedDao=new JDBCBedDao(connection);
		try {
			
		     	Bed bed=bedDao.getEditIpdData(ipdid);
		     	wardname=bedDao.getWard(bed.getWardid()).getWardname();
		 
		} catch (Exception e) {

			e.printStackTrace();
		}
		return wardname;
	}
	
	
	public NotAvailableSlot getOtAppointmentDetails(int appointemntid){
		
		NotAvailableSlot notAvailableSlot=new NotAvailableSlot();
		
		try {
			
			String sql="select commencing,status,diaryuserid,diaryusername,reasonforblock,clientId,charge,otid,category,procedures,surgeon,anesthesia from apm_available_slot where id="+appointemntid+"";
			PreparedStatement ps=connection.prepareStatement(sql);
			ResultSet rs=ps.executeQuery();
			
			while(rs.next()){
				 
				 notAvailableSlot.setCommencing(rs.getString(1));
				 notAvailableSlot.setStatus(rs.getString(2));
				 notAvailableSlot.setDiaryUserId(rs.getInt(3));
				 notAvailableSlot.setDiaryUser(rs.getString(4));
				 notAvailableSlot.setReasonforblock(rs.getString(5));
				 notAvailableSlot.setClientId(rs.getString(6));
				 notAvailableSlot.setCharge(rs.getDouble(7));
				 notAvailableSlot.setOtid(rs.getInt(8));
				 notAvailableSlot.setCategory(rs.getString(9));
				 notAvailableSlot.setProcedure(rs.getString(10));
				 notAvailableSlot.setSurgeon(rs.getString(11));
				 notAvailableSlot.setAnesthesia(rs.getString(12));
				
			}
			
		} catch (Exception e) {

			e.printStackTrace();
		}
		
		
		return notAvailableSlot;
	}
	
	
	public int getAssistantDoctorId(int id){
		
		int result=0;
		try {
			
			String sql="select assistantdoctor from apm_ot_parent where apmtid="+id+"";
			PreparedStatement ps=connection.prepareStatement(sql);
			ResultSet rs=ps.executeQuery();
			
			while(rs.next()){
				  
				result=rs.getInt(1);
			}
			
			
		} catch (Exception e) {

			e.printStackTrace();
		}
		
		
		return result;
	}
	
	
	
    public boolean isProcedureChargeType(String chargeType){
    	
    	boolean isresult=false;
    	try {
		
    		String sql="select procedures from apm_newchargetype where name='"+chargeType+"'";
    		PreparedStatement ps=connection.prepareStatement(sql);
    		ResultSet rs=ps.executeQuery();
    		while(rs.next()){
    			 
    			 isresult=rs.getBoolean(1);
    		}
    		
		} catch (Exception e) {

			e.printStackTrace();
			
		}
    	
    	return isresult;
    }
	
    public ArrayList<Accounts> getSmallPaymentReportList(String fromDate,
			String toDate,String payby,String howpaid,String orderby,String order,String invcategory,String userid, String selectedInvctype,LoginInfo loginInfo) {
		String tp = "Third Party";String self = "Client";
		PreparedStatement preparedStatement = null;
		ArrayList<Accounts>list = new ArrayList<Accounts>();
		StringBuffer sql = new StringBuffer();
		ClientDAO clientDAO=new JDBCClientDAO(connection);
		
		if(payby!=null){
			if(!payby.equals("0")){
				if(payby.equals(Constants.PAY_BY_CLIENT)){
					sql.append("select apm_charges_payment.id,apm_charges_payment.clientid,chargeinvoiceid,payment,paymode,apm_charges_payment.date,apm_charges_payment.tpid,payby,firstname,apm_charges_payment.userid,apm_charges_invoice.itype,apm_charges_payment.ipdno,apm_charges_payment.opdno,apm_charges_payment.invno,apm_charges_payment.cyear,paymentnote,chequeno,cancelinv,cancelNotes,concat(his_invoice_payment_sno.cyear,'/',his_invoice_payment_sno.sno),apm_charges_invoice.discamt,discount,debit,disctype  ");
					sql.append("FROM apm_charges_payment ");
					sql.append("inner join apm_charges_invoice on apm_charges_invoice.id = apm_charges_payment.chargeinvoiceid ");
					sql.append("inner join apm_patient on apm_patient.id = apm_charges_payment.clientid ");
					sql.append(" left join his_invoice_payment_sno on his_invoice_payment_sno.paymentid= apm_charges_payment.id ");
				}else{
					sql.append("select apm_charges_payment.id,apm_charges_payment.clientid,chargeinvoiceid,payment,paymode,apm_charges_payment.date,apm_charges_payment.tpid,payby,company_name,apm_charges_payment.userid,apm_charges_invoice.itype,apm_charges_payment.ipdno,apm_charges_payment.opdno,apm_charges_payment.invno,apm_charges_payment.cyear,paymentnote,chequeno,cancelinv,cancelNotes,concat(his_invoice_payment_sno.cyear,'/',his_invoice_payment_sno.sno),apm_charges_invoice.discamt,discount,debit,disctype  ");
					sql.append("FROM apm_charges_payment ");
					sql.append("inner join apm_charges_invoice on apm_charges_invoice.id = apm_charges_payment.chargeinvoiceid ");
					sql.append("inner join apm_third_party_details on apm_third_party_details.id = apm_charges_payment.tpid ");
					sql.append(" left join his_invoice_payment_sno on his_invoice_payment_sno.paymentid= apm_charges_payment.id ");
				}
			}else{
				sql.append("select apm_charges_payment.id,apm_charges_payment.clientid,chargeinvoiceid,payment,paymode,apm_charges_payment.date,apm_charges_payment.tpid,payby,apm_charges_payment.userid,apm_charges_invoice.itype,apm_charges_payment.ipdno,apm_charges_payment.opdno,apm_charges_payment.invno,apm_charges_payment.cyear,paymentnote,chequeno,cancelinv,cancelNotes,concat(his_invoice_payment_sno.cyear,'/',his_invoice_payment_sno.sno),apm_charges_invoice.discamt,discount,debit,disctype  ");  //change spm_charges_payment to spm_charges_invoice
				sql.append("FROM apm_charges_payment ");
				sql.append("inner join apm_charges_invoice on apm_charges_invoice.id = apm_charges_payment.chargeinvoiceid ");
				sql.append(" left join his_invoice_payment_sno on his_invoice_payment_sno.paymentid= apm_charges_payment.id ");
			}
		}else{
			sql.append("select apm_charges_payment.id,apm_charges_payment.clientid,chargeinvoiceid,payment,paymode,apm_charges_payment.date,apm_charges_payment.tpid,payby,apm_charges_payment.userid,apm_charges_invoice.itype,apm_charges_payment.ipdno,apm_charges_payment.opdno,apm_charges_payment.invno,apm_charges_payment.cyear,paymentnote,chequeno,cancelinv,cancelNotes,concat(his_invoice_payment_sno.cyear,'/',his_invoice_payment_sno.sno),apm_charges_invoice.discamt,discount,debit,disctype ");
			sql.append("FROM apm_charges_payment ");
			sql.append("inner join apm_charges_invoice on apm_charges_invoice.id = apm_charges_payment.chargeinvoiceid ");
			sql.append(" left join his_invoice_payment_sno on his_invoice_payment_sno.paymentid= apm_charges_payment.id ");
		}
	
	
		
		
		if(!fromDate.equals("") && !toDate.equals("")){
			
			/*if(fromDate.equals(toDate)){
				toDate = toDate + " " + "23:59:59";
			}else{
				if(!toDate.equals("")){
					String dt = toDate;
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
					Calendar c = Calendar.getInstance();
					try {
						c.setTime(sdf.parse(dt));
						c.add(Calendar.DATE, 1);  // number of days to add
						dt = sdf.format(c.getTime());  // dt is now the new date
						toDate = dt + " " + "23:59:59";
					} catch (ParseException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			
			}*/
			if(!loginInfo.isAyushman() || !loginInfo.isParihar()) {
				toDate = toDate + " " + "23:59:59";
			}
			//toDate = toDate + " " + "23:59:59";
			
			//sql.append("select id,clientid,chargeinvoiceid,payment,paymode,date,tpid from apm_charges_payment where date between '"+fromDate+"' AND '"+toDate+"' order by id desc ") ;
			
			sql.append("where apm_charges_payment.date between '"+fromDate+"' AND '"+toDate+"' ");
			
			
			if(payby!=null){
				if(!payby.equals("0") && !howpaid.equals("0") ){
					sql.append("and payby='"+payby+"' and paymode='"+howpaid+"' ");
				}else if(!payby.equals("0")){
					sql.append("and payby='"+payby+"' " );
				}else if(!howpaid.equals("0")){
					sql.append("and paymode='"+howpaid+"' " );
				}
			}
		}else{
			
			if(payby!=null){
				if(!payby.equals("0") && !howpaid.equals("0") ){
					sql.append("where payby='"+payby+"' and paymode='"+howpaid+"' ");
				}else if(!payby.equals("0")){
					sql.append("where payby='"+payby+"' " );
				}else if(!howpaid.equals("0")){
					sql.append("where paymode='"+howpaid+"' " );
				}
			}
			
		}
		
		if(!invcategory.equals("2")){
			
			sql.append("and apm_charges_invoice.invpstype="+invcategory+" ");
		}
		
		if(userid!=null){
			if(!userid.equals("0")){
				sql.append("and apm_charges_payment.userid='"+userid+"' ");
			}
		}
		if(!selectedInvctype.equals("0")){
			sql.append(" and itype in("+selectedInvctype+") ");
		}
		sql.append("and paymode!='prepayment' order by "+orderby+" "+order+" ");
		 
		try{
			double totalOfTotal=0;
			preparedStatement = connection.prepareStatement(sql.toString());
			ResultSet rs = preparedStatement.executeQuery();
			AccountsDAO accountsDAO = new JDBCAccountsDAO(connection);
			double totalAmt = 0;
			double totaldiscamt=0;
			//double discount_amount_by_per=0;
			while(rs.next()){
				Accounts accounts = new Accounts();
				accounts.setId(rs.getInt(1));
				accounts.setClientid(rs.getInt(2));
				String clientName = getClientFullName(rs.getInt(2));
				accounts.setClientName(clientName);
				accounts.setInvoiceid(rs.getInt(3));
				
				String invoicenameid=accountsDAO.getInvoiceTypeId(rs.getInt(3));
				int ipdopdseq=accountsDAO.getIpdOpdSeqNo((rs.getInt(3)));
				String invoicename=accountsDAO.getInvoiceName(invoicenameid);
				accounts.setInvoicenameid(invoicename);
				accounts.setIpdopdseq(""+ipdopdseq);
				
				if(loginInfo.isBalgopal() || loginInfo.isSeq_no_gen()){
					accounts.setIpdopdseq(accountsDAO.getFinancialSeqNoOfInvoice(rs.getString(3)));
				}
				
				String abrivationid =getClientAbrivationId(rs.getInt(2));
				accounts.setAbrivationid(abrivationid);
				
				//accounts.setAmount(rs.getDouble(4));
				if(!rs.getString(10).equals("15") && !rs.getString(10).equals("18")) {
					//for physio ipd
					accounts.setAmount(rs.getDouble(4));
					accounts.setFinalTotal(totalOfTotal+accounts.getAmount());
				
				}
				//accounts.setFinalTotal(totalOfTotal+accounts.getAmount());
				totalOfTotal=accounts.getFinalTotal();
				accounts.setAmountx(DateTimeUtils.changeFormat(rs.getDouble(4)));
				accounts.setPaymentmode(rs.getString(5));
				accounts.setDate(DateTimeUtils.getIndianDateTimeFormat(rs.getString(6)));
				String whopay = getWhoPay(rs.getInt(3));
				accounts.setWhoPay(whopay);
				if(whopay.equals(tp)){
					String invoicee = getThirdPartyCompanyName(rs.getString(7));
					accounts.setInvoicee(invoicee);
				}
				else{
					accounts.setInvoicee(clientName);
				}
				accounts.setUserid(rs.getString(9));
				String itype=rs.getString(10);
				String ipdid=rs.getString(11);
				String opdid=rs.getString(12);
				String invstid=rs.getString(13);
				String cyear=rs.getString(14);
				
//				if(itype!=null){
//					if(itype.equals("2")){
//						type1=cyear+"/"+ipdid;
//					}else if(itype.equals("1")){
//						type1=cyear+"/"+opdid;
//					}else if(itype.equals("3")){
//						type1=cyear+"/"+invstid;
//					}else{
//						type1=String.valueOf(accounts.getId());
//					}
//				accounts.setNewsr(type1);	
//				}else{
					accounts.setNewsr(String.valueOf(accounts.getId()));	
//				}
					String paymentnote=rs.getString(15);
					String chequeno=rs.getString(16);
					if(rs.getString(5).equals("Cheque"))
					{
						accounts.setPaymentNote(chequeno);
					}else{
						accounts.setPaymentNote(paymentnote);
					}
					accounts.setCancelsts(rs.getString(17));
					accounts.setCancelNotes(DateTimeUtils.isNull(rs.getString(18)));
					String type1="";
					if(rs.getString(19)!=null){
						type1= rs.getString(19);
						accounts.setNewsr(type1);	
					}else{
						accounts.setNewsr(String.valueOf(accounts.getId()));	
					}
					accounts.setPhysical_payment_id(getPhysicalpaymentId(rs.getString(1)));
					//for ayushman
					if(loginInfo.isAyushman() || loginInfo.isMatrusevasang() || loginInfo.isBalgopal()) {
						Accounts accounts1=getServiceName(rs.getString(3));
						 //String serviceamt=getServiceAmt(rs.getString(3));
							/*
							 * String apmtType=""; String temp[] =
							 * accounts1.getAppointmentType().split(","); for (String string : temp) {
							 * if(!string.equals(" ")){ String data=string; if(apmtType.equals("")){
							 * apmtType=data; }else{ apmtType=apmtType+", "+data+"\n"; } } }
							 */
						accounts.setAppointmentType(accounts1.getAppointmentType());
						accounts.setChargename(accounts1.getDepartment());
						int totalsession=clientDAO.gettotalsessionByClientid(rs.getInt(2));
						int session=clientDAO.getsessionByClientId(rs.getInt(2));
						
						accounts.setTotalsession(totalsession);
						accounts.setSession(session);
						
						//accounts.setDiscamt(rs.getString(21));
						if(rs.getString(23).equals("1")) {
							accounts.setDiscamt(rs.getString(20));
							
						}else {
							double discount_amount_by_per=(rs.getDouble(22)*rs.getDouble(21))/100;
							accounts.setDiscamt(Double.toString(discount_amount_by_per));
						}
						double totalpayment=gettotalpayment(rs.getString(3));
						double balanceamt=rs.getDouble(22) - totalpayment;
						accounts.setBalance(balanceamt);
						/*
						 * if(!rs.getString(21).equals("0")) {
						 * discount_amount_by_per=rs.getDouble(22)/100*rs.getDouble(21);
						 * accounts.setDiscountbyrs(discount_amount_by_per); }
						 */
						 
					}
					list.add(accounts);
				
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		
		
		return list;
	}

	private double gettotalpayment(String chargeinvoiceid) {
		PreparedStatement preparedStatement = null;
		double result  = 0;
		StringBuffer buffer = new StringBuffer();
		buffer.append("select sum(payment) from apm_charges_payment where chargeinvoiceid= '"+chargeinvoiceid+"' ");
		
		try{
			preparedStatement = connection.prepareStatement(buffer.toString());
			ResultSet rs = preparedStatement.executeQuery();
			if(rs.next()){
				result = rs.getDouble(1);
			}
			
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	private Accounts getServiceName(String invoiced) {
		String apmttype="";
		Accounts accounts = new Accounts();
	    StringBuffer buffer=new StringBuffer();
    	try {
		
    		String sql="select apmtType,charge,masterchargetype from apm_invoice_assesments where invoiced='"+invoiced+"' ";
    		PreparedStatement ps=connection.prepareStatement(sql);
    		ResultSet rs=ps.executeQuery();
    		while(rs.next()){
    			
    			 //apmttype=apmttype+","+rs.getString(2);
    			 buffer=buffer.append(rs.getString(1)+"("+rs.getString(2)+") ,");
    			 accounts.setAppointmentType(buffer.toString());
    			 accounts.setDepartment(rs.getString(3));
    		}
    		
		} catch (Exception e) {

			e.printStackTrace();
			
		}
    	
    	return accounts;
		
	}

	public double getRefForInvoicet(String fromDate, String toDate, int i,
			String payby, String howpaid, String invoicecategory, String userid) {
		PreparedStatement preparedStatement = null;
		double result  = 0;
		toDate = toDate + " 23:59:59";
		String sql = "select sum(debit) from apm_credit_account where date between '"+fromDate+"' and '"+toDate+"' " +
				" and refinvoiceid !=0 and apm_credit_account.userid='"+userid+"' ";
		StringBuffer buffer = new StringBuffer();
		buffer.append("select sum(debit) from apm_credit_account where date between '"+fromDate+"' and '"+toDate+"' ");
		buffer.append(" and refinvoiceid !=0 ");
		if(userid!=null){
			buffer.append("and apm_credit_account.userid='"+userid+"' ");
		}
		
		try{
			preparedStatement = connection.prepareStatement(buffer.toString());
			ResultSet rs = preparedStatement.executeQuery();
			if(rs.next()){
				result = rs.getDouble(1);
			}
			
			
		}catch (Exception e) {
			// TODO: handle exception
		}
		return result;
	}

	public double getRefOnly(String fromDate, String toDate, int i,
			String payby, String howpaid, String invoicecategory, String userid) {
		PreparedStatement preparedStatement = null;
		double result  = 0;
		toDate = toDate + " 23:59:59";
		String sql = "select sum(debit) from apm_credit_account where date between '"+fromDate+"' and '"+toDate+"' " +
				" and advref=1 and apm_credit_account.userid='"+userid+"' ";
		StringBuffer buffer = new StringBuffer();
		buffer.append("select sum(debit) from apm_credit_account where date between '"+fromDate+"' and '"+toDate+"' ");
		buffer.append(" and advref=1 ");
		if(userid!=null){
			if(userid.equals("")||userid.equals("0")){
				
			}else{
				buffer.append("and apm_credit_account.userid='"+userid+"' ");
			}
			
		}
		
		try{
			preparedStatement = connection.prepareStatement(buffer.toString());
			ResultSet rs = preparedStatement.executeQuery();
			if(rs.next()){
				result = rs.getDouble(1);
			}
			
			
		}catch (Exception e) {
			// TODO: handle exception
		}
		return result;
	}
	
	
	public ArrayList<Accounts> getAdvancePaymentReportList(String fromDate,
			String toDate, String payby, String paymentStatus,
			String thirdParty, String orderby, String order,
			String invoicecategory, String invcetype,String userid,String paymode, String shiftedFromCancel,String howpaid) {
		PreparedStatement preparedStatement = null;
		ArrayList<Accounts>list = new ArrayList<Accounts>();
		ClientDAO  clientDAO=new JDBCClientDAO(connection);
		paymode=DateTimeUtils.isNull(howpaid);
		//String sql = "SELECT id,date,payby,payment_mode,charge,debit,apm_credit_accountbalance FROM apm_credit_account where client_id = "+clientId+" and payment_mode IS NOT NULL";
		paymode=DateTimeUtils.isNull(paymode);
		toDate = toDate + " 23:59:59";
		
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT apm_credit_account.id,apm_credit_account.date,apm_credit_account.payby,payment_mode,charge,apm_credit_account.debit,balance,third_party_name_id,concat(title,' ',firstname,' ',surname),advref,apm_credit_account.resetinv,payment_mode,apm_credit_account.userid,apm_credit_account.cyear,apm_credit_account.advno,concat(his_invoice_payment_sno.cyear,'/',his_invoice_payment_sno.sno),apm_credit_account.credit_note,apm_credit_account.invoiceid, apm_credit_account.cancelpay,itype,apm_charges_payment.id,apm_credit_account.client_id,advance_ipdid,invtypenew FROM apm_credit_account  ");
		sql.append(" left join apm_charges_invoice on apm_charges_invoice.id = apm_credit_account.invoiceid ");
		sql.append(" left join apm_charges_payment on apm_charges_payment.chargeinvoiceid=apm_credit_account.invoiceid ");
		sql.append(" left join apm_patient on apm_patient.id = apm_credit_account.client_id ");
		sql.append(" left join his_invoice_payment_sno on his_invoice_payment_sno.invoiceid=apm_credit_account.id ");
		sql.append(" where payment_mode IS NOT NULL and apm_credit_account.date between '"+fromDate+"' and '"+toDate+"' and charge>0 and his_invoice_payment_sno.paymentid=0  and payment_mode!='prepayment' ");
		
		if(userid!=null){
			if(userid.equals("0")||userid.equals("")){
				
			}else{
				sql.append("and apm_credit_account.userid='"+userid+"' ");	
			}
			
		}
		if(!(paymode.equals("")||paymode.equals("0"))){
			
			sql.append("  and payment_mode='"+paymode+"' ");	
		}
		sql.append("  and cancelpay='"+shiftedFromCancel+"' ");
		sql.append(" group by apm_credit_account.id order by resetinv ");
		
		try{
			double tot=0;
			IpdDAO ipdDAO = new JDBCIpdDAO(connection);
			preparedStatement = connection.prepareStatement(sql.toString());
			ResultSet rs = preparedStatement.executeQuery();
			AdditionalDAO additionalDAO= new JDBCAdditionalDAO(connection);
			double advtotal = 0;
			while(rs.next()){
				Accounts accounts = new Accounts();
				accounts.setId(rs.getInt(1));
				accounts.setBghseqId(""+additionalDAO.getBalgopalSeqNum(""+accounts.getId()));
				
				//new finac year se
				String finseq=additionalDAO.getBalgopalFinacialSeqNum(""+accounts.getId());
				accounts.setBghseqId(finseq);
				
				String date = DateTimeUtils.getIndianDateTimeFormat(rs.getString(2));
				accounts.setDate(date);
				String temp[] = date.split(" ");		
				accounts.setCommencing(temp[0]);
				String payidby=rs.getString(3);
				if(payidby.equals(Constants.PAY_BY_CLIENT)) {
					payidby="Self";
				}else {
					payidby="TP";
				}
				
				accounts.setPayby(payidby);
				accounts.setPaymentmode(rs.getString(4));
				accounts.setCharges(DateTimeUtils.changeFormat(Double.parseDouble(rs.getString(5))));
				advtotal = advtotal + rs.getDouble(5);
				accounts.setDebitTotalx(DateTimeUtils.changeFormat(Double.parseDouble(rs.getString(6))));
				accounts.setAdvreftotal(DateTimeUtils.changeFormat(advtotal));
				
				double credit = rs.getDouble(5);
				double debit = rs.getDouble(6);
				double balance = 0;
				if(!rs.getString(24).equals("15") && !rs.getString(24).equals("18")) {
					//for physio ipd
				accounts.setDebitAmount(debit);
				accounts.setCreditAmount(credit);
				accounts.setBalance(balance);
				}
				if(rs.getString(3).equals(Constants.PAY_BY_THIRD_PARTY)){
					
					CompleteAptmDAO completeAptmDAO = new JDBCCompleteAptmDAO(connection);
				    CompleteAppointment	completeAppointment = completeAptmDAO.getInsuranceCompanyName(rs.getString(8));
				    accounts.setClientName(completeAppointment.getInsuranceCompanyName());
				}
				else{
					
					accounts.setClientName(rs.getString(9));
				}
				
				
				accounts.setBalancex(DateTimeUtils.changeFormat(balance));
				accounts.setAdvref(rs.getInt(10));
				accounts.setResetinv(rs.getString(11));
				accounts.setPaymentmode(rs.getString(12));
				accounts.setUserid(rs.getString(13));
				if(rs.getString(16)!=null){
					accounts.setAdvno(rs.getString(16));
				}else{
				accounts.setAdvno(rs.getString(14)+"/"+rs.getString(15));
				}
				accounts.setRemark(rs.getString(17));
				accounts.setPhysical_payment_id(getPhysicalpaymentIdAdvRef(rs.getString(1)));
				accounts.setCancelsts(rs.getString(19));
				String receipt=getnewpaymentreceipt(rs.getInt(18));
				String newreceipt=getPhysicalpaymentIdbyinvoiceid(rs.getInt(18));
				accounts.setNewshftcharge(newreceipt);
				if(rs.getInt(20)==1){
					accounts.setReceiptid(rs.getString(21));
				}else{
				accounts.setReceiptid(receipt);
				
				}
				//for payment report
				accounts.setItype("5");
				accounts.setAmountx(rs.getString(5));
				double payAmt = rs.getDouble(5);
				tot = tot + payAmt;
				accounts.setTotalamt(String.valueOf(tot));
				Client client = clientDAO.getClientDetails(rs.getString(22));
				accounts.setAbrivationid(client.getAbrivationid());
				
				int ipdid=rs.getInt(23);
				if(ipdid>0){
					String newipdabbr = ipdDAO.getIpdAbrivationIds(ipdid);
		    		accounts.setIpdid(newipdabbr);
				}
				
				list.add(accounts);
			}
			
			
		}catch(Exception e){
			e.printStackTrace();
		}
		return list;
	}
	public String getPhysicalpaymentIdbyinvoiceid(int payid){
		String res="0";
		try {
			PreparedStatement ps= connection.prepareStatement(" select id from his_payment_record_physical where invoiceid='"+payid+"'   ");
			ResultSet rs= ps.executeQuery();
			while(rs.next()){
				res=""+rs.getInt(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return res;
	}
	private String getnewpaymentreceipt(int int1) {
		PreparedStatement preparedStatement = null;
		String sql = "select concat(his_invoice_payment_sno.cyear,'/',his_invoice_payment_sno.sno) from his_invoice_payment_sno where invoiceid="+int1+" ";
		
		String result = "";
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

	public ArrayList<Accounts> getRefundPaymentReportList(String fromDate,
			String toDate, String payby, String paymentStatus,
			String thirdParty, String orderby, String order,
			String invoicecategory, String string, String userid,String selectedInvctype, String paymode) {
		PreparedStatement preparedStatement = null;
		ArrayList<Accounts>list = new ArrayList<Accounts>();
		//String sql = "SELECT id,date,payby,payment_mode,charge,debit,apm_credit_accountbalance FROM apm_credit_account where client_id = "+clientId+" and payment_mode IS NOT NULL";
		paymode=DateTimeUtils.isNull(paymode);
		toDate = toDate + " 23:59:59";
		
		StringBuffer sql = new StringBuffer();

		sql.append("SELECT apm_credit_account.id,date,payby,payment_mode, ");
		sql.append("charge,debit,balance,third_party_name_id,concat(title,' ',firstname,' ',surname),advref,resetinv,payment_mode,userid,concat(his_invoice_payment_sno.cyear,'/',his_invoice_payment_sno.sno),apm_credit_account.manualinvoiceid,client_id  ");
		sql.append("FROM apm_credit_account inner join apm_patient on apm_patient.id = apm_credit_account.client_id ");
		sql.append(" left join his_invoice_payment_sno on his_invoice_payment_sno.invoiceid=apm_credit_account.id ");
		sql.append("where payment_mode IS NOT NULL and date between '"+fromDate+"' and '"+toDate+"' and ");
		sql.append("advref=1 and his_invoice_payment_sno.paymentid=0  ");
		if(userid!=null){
			if(userid.equals("0")||userid.equals("")){
				
			}else{
				sql.append(" and userid = '"+userid+"'"); 
			}
			
		}
		
		if(!selectedInvctype.equals("0")){
			if(selectedInvctype.contains("0,")){
				String val="";
				for (String str : selectedInvctype.split(",")) {
					if(str.equals("0")){
						continue;
					}
					if(val.equals("")){
						val=str;
					}else{
						val=val+","+str;
					}
					selectedInvctype=val;
				}
			}
		}
//			String temp1[] = selectedInvctype.split(",");
//			if(temp1.length>1){
		if(!selectedInvctype.equals("0")){
				//selectedInvctype = selectedInvctype.substring(selectedInvctype.length()-1);
				sql.append(" and invtypenew in("+selectedInvctype+") ");
		}
//		if(selectedInvctype!=null){
//			if(selectedInvctype.equals("0") || selectedInvctype.equals("")){
//				
//			}else{
//				sql.append(" and invoice_type = '"+selectedInvctype+"'"); 
//			}
//		}
		
		if(!(paymode.equals("")||paymode.equals("0"))){
			sql.append("  and payment_mode='"+paymode+"' ");	
		}
		
		sql.append(" order by id desc ");
		try{
			
			preparedStatement = connection.prepareStatement(sql.toString());
			ResultSet rs = preparedStatement.executeQuery();
			AccountsDAO accountsDAO = new JDBCAccountsDAO(connection);
			ClientDAO clientDAO= new JDBCClientDAO(connection);
			double advtotal = 0;
			while(rs.next()){
				Accounts accounts = new Accounts();
				accounts.setId(rs.getInt(1));
				String date = DateTimeUtils.getIndianDateTimeFormat(rs.getString(2));
				accounts.setDate(date);
				String temp[] = date.split(" ");		
				accounts.setCommencing(temp[0]);
				String payidby=rs.getString(3);
				if(payidby.equals(Constants.PAY_BY_CLIENT)) {
					payidby="Self";
				}else {
					payidby="TP";
				}
				
				accounts.setPayby(payidby);
				accounts.setPaymentmode(rs.getString(4));
				accounts.setCharges(DateTimeUtils.changeFormat(Double.parseDouble(rs.getString(5))));
				advtotal = advtotal + rs.getDouble(5);
				accounts.setDebitTotalx(DateTimeUtils.changeFormat(Double.parseDouble(rs.getString(6))));
				accounts.setAdvreftotal(DateTimeUtils.changeFormat(advtotal));
				Client client= clientDAO.getClientDetails(rs.getString(16));
				accounts.setAbrivationid(client.getAbrivationid());
				
				double credit = rs.getDouble(5);
				double debit = rs.getDouble(6);
				double balance = 0;
				
				
				if(!rs.getString(10).equals("15") && !rs.getString(10).equals("18")) {
				 //for physio ipd and opd	
				 accounts.setDebitAmount(debit);
				 accounts.setCreditAmount(credit);
				 accounts.setBalance(balance);
				}
				if(rs.getString(3).equals(Constants.PAY_BY_THIRD_PARTY)){
					
					CompleteAptmDAO completeAptmDAO = new JDBCCompleteAptmDAO(connection);
				    CompleteAppointment	completeAppointment = completeAptmDAO.getInsuranceCompanyName(rs.getString(8));
				    accounts.setClientName(completeAppointment.getInsuranceCompanyName());
				}
				else{
					
					accounts.setClientName(rs.getString(9));
				}
				
				
				accounts.setBalancex(DateTimeUtils.changeFormat(balance));
				accounts.setAdvref(rs.getInt(10));
				accounts.setResetinv(rs.getString(11));
				accounts.setPaymentmode(rs.getString(12));
				accounts.setUserid(rs.getString(13));
				accounts.setRefid(rs.getString(14));
				if(rs.getString(15)==null){
					accounts.setManualinvoiceid("0");
				}else{
					if(rs.getString(15).equals("")){
						accounts.setManualinvoiceid("0");
					}else{
						 int ipdopdseq=accountsDAO.getIpdOpdSeqNo((rs.getInt(15)));
						 accounts.setIpdopdseq(String.valueOf(ipdopdseq));
						 accounts.setManualinvoiceid(rs.getString(15));
						 String invoicenameid=accountsDAO.getInvoiceTypeId(rs.getInt(15));
						 String invoicename=accountsDAO.getInvoiceName(invoicenameid);
						 accounts.setInvoicenameid(invoicename);
					}
				}
				accounts.setPhysical_payment_id(getPhysicalpaymentIdAdvRef(rs.getString(1)));
				list.add(accounts);
			}
			
			//  28 May 2018 for test
			/*ArrayList<Accounts>reuinvoiceList = getRefInvoiceList( fromDate,
					 toDate,  payby,  paymentStatus,
					 thirdParty,  orderby,  order,
					 invoicecategory,  string,  userid);
			
			list.addAll(reuinvoiceList);*/
			
		}catch(Exception e){
			e.printStackTrace();
		}
		return list;
	}

	private ArrayList<Accounts> getRefInvoiceList(String fromDate,
			String toDate, String payby, String paymentStatus,
			String thirdParty, String orderby, String order,
			String invoicecategory, String string, String userid) {
		// TODO Auto-generated method stub
		PreparedStatement preparedStatement = null;
		ArrayList<Accounts>list = new ArrayList<Accounts>();
		//String sql = "SELECT id,date,payby,payment_mode,charge,debit,apm_credit_accountbalance FROM apm_credit_account where client_id = "+clientId+" and payment_mode IS NOT NULL";
		
		toDate = toDate + " 23:59:59";
		
		StringBuffer sql = new StringBuffer();

		sql.append("SELECT apm_credit_account.id,date,payby,payment_mode, ");
		sql.append("charge,debit,balance,third_party_name_id,concat(title,' ',firstname,' ',surname),advref,resetinv,payment_mode,userid ");
		sql.append("FROM apm_credit_account inner join apm_patient on apm_patient.id = apm_credit_account.client_id ");
		sql.append("where payment_mode IS NOT NULL and date between '"+fromDate+"' and '"+toDate+"' and ");
		sql.append("refinvoiceid>0  and userid = '"+userid+"' order by id desc ");
		try{
			
			preparedStatement = connection.prepareStatement(sql.toString());
			ResultSet rs = preparedStatement.executeQuery();
			
			double advtotal = 0;
			while(rs.next()){
				Accounts accounts = new Accounts();
				accounts.setId(rs.getInt(1));
				String date = DateTimeUtils.getIndianDateTimeFormat(rs.getString(2));
				accounts.setDate(date);
				String temp[] = date.split(" ");		
				accounts.setCommencing(temp[0]);
				accounts.setPayby(rs.getString(3));
				accounts.setPaymentmode(rs.getString(4));
				accounts.setCharges(DateTimeUtils.changeFormat(Double.parseDouble(rs.getString(5))));
				advtotal = advtotal + rs.getDouble(5);
				accounts.setDebitTotalx(DateTimeUtils.changeFormat(Double.parseDouble(rs.getString(6))));
				accounts.setAdvreftotal(DateTimeUtils.changeFormat(advtotal));
				
				double credit = rs.getDouble(5);
				double debit = rs.getDouble(6);
				double balance = 0;
				
				accounts.setDebitAmount(debit);
				accounts.setCreditAmount(credit);
				accounts.setBalance(balance);
				
				if(rs.getString(3).equals(Constants.PAY_BY_THIRD_PARTY)){
					
					CompleteAptmDAO completeAptmDAO = new JDBCCompleteAptmDAO(connection);
				    CompleteAppointment	completeAppointment = completeAptmDAO.getInsuranceCompanyName(rs.getString(8));
				    accounts.setClientName(completeAppointment.getInsuranceCompanyName());
				}
				else{
					
					accounts.setClientName(rs.getString(9));
				}
				
				
				accounts.setBalancex(DateTimeUtils.changeFormat(balance));
				accounts.setAdvref(rs.getInt(10));
				accounts.setResetinv(rs.getString(11));
				accounts.setPaymentmode(rs.getString(12));
				accounts.setUserid(rs.getString(13));
				list.add(accounts);
			}
			
			
			
		}catch(Exception e){
			e.printStackTrace();
		}
		return list;
	}

	public ArrayList<Master> getAccountUserList() {
		PreparedStatement preparedStatement = null;
		ArrayList<Master>list = new ArrayList<Master>();
		String sql = "select userid,jobtitle from apm_user where jobtitle = 'Accounts' ";
		
		try{
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()){
				Master master = new Master();
				master.setUserid(rs.getString(1));
				list.add(master);
			}
			
		}catch (Exception e) {
			// TODO: handle exception
		}
		return list;
	}
	// Adarsh
	public int saveMisReportLog(String string, String userId, String fromDate, String toDate,String date, String string1
			) {
		int result=0;
		try {
			String sql = "insert into mis_report_log(report_name, userid, from_date, to_date, date, method_name) values(?,?,?,?,?,?)";
			PreparedStatement ps = connection.prepareStatement(sql);
			MisReport misReport = new MisReport();
			ps.setString(1, string);
			ps.setString(2, userId);
			ps.setString(3, fromDate);
			ps.setString(4, toDate);
			ps.setString(5, date);
			ps.setString(6, string1);
			result = ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	public ArrayList<Accounts> getChargesReportDeatiled(String fromDate, String toDate, String payby, String tpid,
			   String invoiceStatus, String orderby, String order, String jobtitle, String apmttype,String ChargesType,String user,String opdipd,String clientid,String searchinv,String dept) {
			  ArrayList<Accounts> list= new ArrayList<Accounts>();
			  PreparedStatement ps= null;
			  try {
			   StringBuffer buffer= new StringBuffer();
			   
			   buffer.append("SELECT commencing,concat(initial,' ',apm_user.firstname,' ',lastname),apmtType,charge,apm_invoice_assesments.invoiceid, ");
			   buffer.append("paid,apm_invoice_assesments.id,apm_charges_invoice.practid,apm_invoice_assesments.paybuy, ");
			   buffer.append("apm_invoice_assesments.clientId,apm_invoice.chargetype,howpaid,apm_invoice.appointmentid,apm_user.discription, ");
			   buffer.append("apm_invoice_assesments.thirdPartyId,apm_invoice.date,apm_invoice_assesments.masterchargetype, ");
			   buffer.append("apm_invoice_assesments.quantity,apm_charges_assesment.chargeinvoiceid  ,apm_charges_invoice.debit, ");
			   buffer.append("apm_charges_invoice.discamt,apm_charges_invoice.date,apm_charges_invoice.id , apm_charges_invoice.itype, ");
			   buffer.append("apm_invoice_assesments.practitionerId,apm_invoice_assesments.wardid,ipdseqno,apm_invoice_assesments.chargedisc, ");
			   buffer.append("apm_invoice_assesments.ipdid,f_seq_no,apm_newchargetype.id ");
			   buffer.append(" FROM apm_invoice_assesments  inner join apm_invoice on apm_invoice.id = apm_invoice_assesments.invoiceid ");
			   buffer.append(" inner join apm_charges_assesment on apm_charges_assesment.invoiceid= apm_invoice_assesments.invoiceid ");
			   buffer.append(" inner join apm_charges_invoice on apm_charges_invoice.id=apm_charges_assesment.chargeinvoiceid ");
			   buffer.append(" left join apm_user on apm_charges_invoice.practid = apm_user.id ");
			   buffer.append(" inner join apm_newchargetype on apm_newchargetype.name = apm_invoice_assesments.masterchargetype");
			   buffer.append(" where apm_invoice.date between '"+fromDate+"' AND '"+toDate+"'  ");
			   if(!searchinv.equals("")){
				   buffer.append(" and apm_charges_invoice.id='"+searchinv+"' ");
			   }
			   buffer.append(" and  apm_invoice_assesments.active=1 ");
			   if(!apmttype.equals("")){
				   buffer.append(" and apmtType in( "+apmttype+" )");
			   }
			   if(!ChargesType.equals("")){
				   buffer.append(" and apm_newchargetype.id = "+ChargesType+"");
			   }
			   if(!user.equals("")){
				   buffer.append(" and apm_charges_invoice.practid='"+user+"' ");
			   }
			   if(!opdipd.equals("")){
				   if(opdipd.equals("0")){
					   buffer.append("  and apm_charges_invoice.itype ='2' ");
				   }else if(opdipd.equals("2")){
					   buffer.append("  and apm_charges_invoice.itype ='3' ");
				   }else if(opdipd.equals("3")){
					   buffer.append("  and apm_charges_invoice.itype ='8' ");
				   }
				   else{
					   buffer.append(" and apm_charges_invoice.itype ='1' ");
				   }
			   }
			   buffer.append("  and apm_charges_invoice.chargetype='Submit' ");
			   if(!clientid.equals("")){
				   buffer.append(" and apm_invoice.clientid='"+clientid+"'");
			   }
			   if(dept==null){
				   dept="0";   
			   }
			   if(dept.equals("")){
				   dept="0";
			   }
			   if(!dept.equals("0")){
				   buffer.append(" and apm_user.discription='"+dept+"' ");
			   }
			   CompleteAptmDAO completeAptmDAO = new JDBCCompleteAptmDAO(connection);
			   String sql="";
			   sql= buffer.toString();
			   ps=connection.prepareStatement(sql);
			   ResultSet rs= ps.executeQuery();
			   double totaloftotal=0;
			   AccountsDAO accountsDAO = new JDBCAccountsDAO(connection);
			   UserProfileDAO userProfileDAO= new JDBCUserProfileDAO(connection);
			   ClientDAO clientDAO= new JDBCClientDAO(connection);
			   SummaryReportDAO summaryReportDAO = new JDBCSummaryReportDAO(connection);
			   while(rs.next()){
				   Accounts accounts = new Accounts();
				   accounts.setCommencing(DateTimeUtils.getCommencingDate1(rs.getString(1)));
				   int invcid=rs.getInt(23);
				   if(rs.getString(2)==null){
					   String usernamebyid=getUserNameById(invcid);
					   accounts.setUsername(usernamebyid);
				   }else if(rs.getString(2).equals("")){
					   String usernamebyid=getUserNameById(invcid);
					   accounts.setUsername(usernamebyid);
				   }else{
					   accounts.setUsername(rs.getString(2));
				   }
				   accounts.setAppointmentType(rs.getString(3));
				   accounts.setCreditCharge(rs.getString(4));
				   accounts.setInvoiceid(rs.getInt(5));
				   accounts.setPaid(rs.getBoolean(6));
				   accounts.setNumberOfChages(rs.getInt(7));
				   accounts.setPractitionerName(rs.getString(8));
				   if(accounts.getPractitionerName()==null){
					   accounts.setPractitionerName("");
				   }
				   if(rs.getString(9)!=null){
					   if(rs.getString(9).equals("1")){
						   accounts.setWhoPay("Third Party");
				     	   CompleteAppointment completeAppointment =  completeAptmDAO.getInsuranceCompanyName(rs.getString(10));
				     	   String tpName = completeAppointment.getInsuranceCompanyName();
				     	   accounts.setTpName(tpName);
					   }
				   }else{
					   accounts.setWhoPay("Self");
				   }
				   accounts.setClientid(rs.getInt(10));
				   String clientname= clientDAO.getClientFullName(String.valueOf(accounts.getClientid()));
				   accounts.setClientName(clientname);
				   accounts.setChargeType(rs.getString(11));
				   accounts.setHowPaid(rs.getString(12));
				   accounts.setApptId(rs.getString(13));
			    
				   String locationName = getChargeDepartmentName(rs.getInt(8));
				   accounts.setLocationName(locationName);
			    
				   String treamentEpisodeName = getTreatmentEpisodeName(rs.getString(13));
				   accounts.setTreatmentEpisodeName(treamentEpisodeName);
				   
				   boolean chargesInvoiced = checkChargeInvoiced(accounts.getInvoiceid());
				   accounts.setChargesInvoiced(chargesInvoiced);
				   accounts.setInvdate((DateTimeUtils.getCommencingDate1(rs.getString(16))));
				   accounts.setChargeType(rs.getString(17));
				   accounts.setQuantity(rs.getInt(18));
				   accounts.setTotalAmount(rs.getDouble(18)*rs.getDouble(4));
				   accounts.setTotalrecammount(accounts.getTotalAmount()+totaloftotal);
				   totaloftotal= accounts.getTotalrecammount();
				   accounts.setNewsr(rs.getString(19));
				   if(rs.getDouble(28)>0){
					   accounts.setDiscount(rs.getDouble(28));
				   }else{
					   accounts.setDiscount(rs.getDouble(21));
				   }
				   accounts.setDebitAmount(rs.getDouble(20));
				   accounts.setInvdate(DateTimeUtils.getCommencingDate1(rs.getString(22)));
				   accounts.setItype(accountsDAO.getInvoiceName(rs.getString(24)));
			    
				   String chgreuser= userProfileDAO.getFullName(rs.getString(25));
				   accounts.setFirstname(chgreuser);
				   String wardname=completeAptmDAO.getwardnamebywardid(rs.getInt(26));
				   if(wardname==null){
					   wardname="";
				   }
				   accounts.setWard(wardname);
				   accounts.setIpdopdseq(rs.getString(27));
				   accounts.setAbrivationid(getClientUHID(""+accounts.getClientid()));
				   String ipdabrivationid = summaryReportDAO.getPatientIPDAbrivationId(rs.getString(29));
				   accounts.setIpdabrivationid(ipdabrivationid);
				   accounts.setFseqno(rs.getString(30));
				   list.add(accounts);
			  }
		} catch (Exception e) {
			e.printStackTrace();
		}
	return list;
}
	
	//lokesh 27-7-2018
	public ArrayList<Accounts> getpaymentreciptlist(String fromdate, String todate, String paymenttype, String userid,String invoicetype) {
		ArrayList<Accounts> paymentrecieptlist= new ArrayList<Accounts>();
		PreparedStatement ps= null;
		try {
			fromdate= DateTimeUtils.getCommencingDate1(fromdate);
			todate= DateTimeUtils.getCommencingDate1(todate)+" 23.59.59";
			
			StringBuffer buffer= new StringBuffer();
			buffer.append("   select a.id, a.invoiceid, a.paymentid, a.invtype, a.sno, a.cyear,    ");
			buffer.append("    b.date, b.client_id, b.payment_mode,b.charge,   ");
			buffer.append("    c.date, c.clientid,  c.paymode, c.payment,  ");
			buffer.append("    concat(e.firstname,' ',e.surname), concat('',f.firstname,' ',f.lastname) ,b.debit  ");
			
			buffer.append("    from his_invoice_payment_sno a   ");
			
			buffer.append("    left join apm_credit_account b on b.id= a.invoiceid   ");
			buffer.append("    left join apm_charges_payment  c on c.id = a.paymentid   ");
			
			buffer.append("    left join apm_patient e on e.id =b.client_id or e.id =c.clientid   ");
			buffer.append("    left join apm_user f on f.userid= b.userid or f.userid=c.userid   ");
			
			buffer.append("    where a.sno is not null   ");
			
			if(fromdate!=null&&todate!=null){
				buffer.append("    and (( b.date between '"+fromdate+"' and '"+todate+"')  or (   c.date between '"+fromdate+"' and '"+todate+"'))  ");
			}
			if(!paymenttype.equals("")){
				buffer.append("   and (( b.payment_mode='"+paymenttype+"') or (c.paymode='"+paymenttype+"'))    ");
			}
			if(!userid.equals("")){
				buffer.append("   and ((b.userid='"+userid+"') or (c.userid='"+userid+"'))   ");
			}
			if(!invoicetype.equals("")){
				buffer.append("   and  a.invtype='"+invoicetype+"' ");
			}
			
			ps= connection.prepareStatement(buffer.toString());
			ResultSet rs=  ps.executeQuery();
			while(rs.next()){
				
				if(rs.getString(4).equals("Advance")){
					Accounts accounts= new Accounts();
					String date[]= rs.getString(7).split(" ");
					accounts.setDate(DateTimeUtils.getCommencingDate1(date[1])+" "+date[0]);
					accounts.setId(rs.getInt(5));
					accounts.setAmount(rs.getDouble(10));
					accounts.setPaymentmode(rs.getString(9));
					accounts.setClientName(rs.getString(15));
					accounts.setUsername(rs.getString(16));
					accounts.setInvoicetype("Advance");
					paymentrecieptlist.add(accounts);
					
				}/*else if(rs.getString(4).equals("Refund")){
					Accounts accounts= new Accounts();
					String date[]= rs.getString(7).split(" ");
					accounts.setDate(DateTimeUtils.getCommencingDate1(date[1])+" "+date[0]);
					accounts.setId(rs.getInt(5));
					accounts.setAmount(rs.getDouble(17));
					accounts.setPaymentmode(rs.getString(9));
					accounts.setClientName(rs.getString(15));
					accounts.setUsername(rs.getString(16));
					accounts.setInvoicetype("Refund");
					paymentrecieptlist.add(accounts);
					
				}*/else if(rs.getString(4).equals("IPD")){
					Accounts accounts= new Accounts();
					String date[]= rs.getString(11).split(" ");
					accounts.setDate(DateTimeUtils.getCommencingDate1(date[1])+" "+date[0]);
					accounts.setId(rs.getInt(5));
					accounts.setAmount(rs.getDouble(14));
					accounts.setPaymentmode(rs.getString(13));
					accounts.setClientName(rs.getString(15));
					accounts.setUsername(rs.getString(16));
					accounts.setInvoicetype("IPD");
					paymentrecieptlist.add(accounts);
					
				}else{
					
				}	
				
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	
	return paymentrecieptlist;
}

	public int getcountofinvoice(String fromdate, String todate, String paymenttype, String userid,
			String invoicetype) {
		ArrayList<Accounts> paymentrecieptlist= new ArrayList<Accounts>();
		int count=0;
		PreparedStatement ps= null;
		try {
			fromdate= DateTimeUtils.getCommencingDate1(fromdate);
			todate= DateTimeUtils.getCommencingDate1(todate)+" 23.59.59";
			
			StringBuffer buffer= new StringBuffer();
			
			if(invoicetype.equals("Advance")){
			buffer.append("   select sum(b.charge)  ");
			}
			if(invoicetype.equals("Refund")){
			buffer.append("   select sum(b.debit)  ");	
			}
			if(invoicetype.equals("IPD")){
				buffer.append("   select sum(c.payment)  ");	
			}
			buffer.append("    from his_invoice_payment_sno a   ");
			
			buffer.append("    left join apm_credit_account b on b.id= a.invoiceid   ");
			buffer.append("    left join apm_charges_payment  c on c.id = a.paymentid   ");
			
			buffer.append("    left join apm_patient e on e.id =b.client_id or e.id =c.clientid   ");
			buffer.append("    left join apm_user f on f.userid= b.userid or f.userid=c.userid   ");
			
			buffer.append("    where a.sno is not null   ");
			
			if(fromdate!=null&&todate!=null){
				buffer.append("    and (( b.date between '"+fromdate+"' and '"+todate+"')  or (   c.date between '"+fromdate+"' and '"+todate+"'))  ");
			}
			if(!paymenttype.equals("")){
				buffer.append("   and (( b.payment_mode='"+paymenttype+"') or (c.paymode='"+paymenttype+"'))    ");
			}
			if(!userid.equals("")){
				buffer.append("   and ((b.userid='"+userid+"') or (c.userid='"+userid+"'))   ");
			}
			if(!invoicetype.equals("")){
				buffer.append("   and  a.invtype='"+invoicetype+"' ");
			}
			
			ps= connection.prepareStatement(buffer.toString());
			ResultSet rs=  ps.executeQuery();
			while(rs.next()){
				
				count= rs.getInt(1);
					
				}	
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return count;
	}
	
	public ArrayList<Client> getallInvoicedListOfClient(String fromdate, String todate){
		ArrayList<Client> list=new ArrayList<Client>();
		StringBuffer buffer=new StringBuffer();
		buffer.append("select apm_patient.id,concat(title,' ',firstname,' ',surname) from apm_charges_invoice ");
		buffer.append(" inner join apm_patient on apm_patient.id=apm_charges_invoice.clientid  ");
		buffer.append("  where apm_charges_invoice.date between '"+fromdate+"' and '"+todate+"' group by clientid ");
		PreparedStatement ps= null;
		try {
			ps= connection.prepareStatement(buffer.toString());
			ResultSet rs= ps.executeQuery();
			while(rs.next()){
				Client client= new  Client();
				client.setId(rs.getInt(1));
				client.setClientName(rs.getString(2));
				list.add(client);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	public String getUserNameById(int id){
		PreparedStatement ps= null;
		String username="";
		try {
			String sql="select concat(initial,' ',firstname,' ',lastname) from apm_charges_invoice inner join apm_user on apm_charges_invoice.practid=apm_user.id where apm_charges_invoice.id="+id+" ";
			ps= connection.prepareStatement(sql);
			ResultSet rs= ps.executeQuery();
			while(rs.next()){
				username=rs.getString(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return username;
		
	}

	public ArrayList<Accounts> getCreditBalanceReportList(String fromDate, String toDate, String payby,
			String paymentStatus, String thirdParty, String orderby, String order, String invoicecategory,
			String string, String userid) {
		PreparedStatement preparedStatement = null;
		ArrayList<Accounts>list = new ArrayList<Accounts>();
		StringBuffer sql = new StringBuffer();
		
		sql.append("select apm_charges_invoice.id,apm_charges_invoice.debit,sum(apm_charges_payment.payment),discount,apm_charges_invoice.discamt,fullname,userid,apm_charges_invoice.date,(debit-sum(payment)-apm_charges_invoice.discamt-(debit/apm_charges_invoice.discount)) ");
		sql.append(" ,payby,itype from apm_charges_invoice ");
		sql.append("inner join apm_patient on apm_patient.id = apm_charges_invoice.clientid ");
		sql.append("inner join apm_charges_payment on apm_charges_payment.chargeinvoiceid=apm_charges_invoice.id ");
		sql.append(" where apm_charges_invoice.date between '"+fromDate+"' and '"+toDate+"'  ");
		//sql.append("and (debit-payment-(discamt+(discount/100)*debit))>0 ");
		if(userid!=null){
			sql.append("and userid='"+userid+"' ");
		}
		sql.append("group by apm_charges_invoice.id having (debit-sum(payment)-(apm_charges_invoice.discamt+(discount/100)*debit))>0 ");
		try{
			preparedStatement = connection.prepareStatement(sql.toString());
			ResultSet rs = preparedStatement.executeQuery();
			double balanceTotal =0;
			AccountsDAO accountsDAO= new JDBCAccountsDAO(connection);
			while(rs.next()){
				Accounts accounts=new Accounts();
				accounts.setClientName(rs.getString(6));
				accounts.setInvoiceid(rs.getInt(1));
				accounts.setInvdate(DateTimeUtils.getCommencingDate1(rs.getString(8)));
				accounts.setDebitAmount(rs.getLong(2));
				accounts.setCreditAmount(rs.getDouble(3));
				if(rs.getDouble(4)!=0){
					accounts.setDiscount(rs.getDouble(4));
				}else if(rs.getDouble(5)!=0){
					accounts.setDiscount(rs.getDouble(5));
				}else{
					accounts.setDiscount(0);
				}
				accounts.setBalance(rs.getDouble(9));
				if(rs.getDouble(9)==0){
					double discount = accounts.getDiscount();
					double bal =0;
					if(discount==0){
						bal = rs.getDouble(2) - rs.getDouble(3) ;
					}else{
						bal = rs.getDouble(2) - rs.getDouble(3) -discount;
					}
					accounts.setBalance(bal);
				}
				balanceTotal = balanceTotal + accounts.getBalance();
				accounts.setBalanceTotal(balanceTotal);
				accounts.setUserid(rs.getString(7));
				accounts.setPayby(rs.getString(10));
				accounts.setInvoicetype(accountsDAO.getInvoiceName(rs.getString(11)));
					list.add(accounts);
				}
	
	}catch (Exception e) {
	e.printStackTrace();
}
		return list;
	}

	public int dropAssesmentView() {
		PreparedStatement preparedStatement = null;
		int result = 0;
		String sql = "drop view asmnt_view ";
		
		try{
			
			preparedStatement = connection.prepareStatement(sql);
			result = preparedStatement.executeUpdate();
			
		}catch (Exception e) {
			// TODO: handle exception
		}
		return result;
	}

	public int createAssesmentView(String fromDate, String toDate) {
		PreparedStatement preparedStatement = null;
		int result = 0;
		String sql = "create view  asmnt_view as select * from apm_invoice_assesments where commencing between '"+fromDate+"' and '"+toDate+"' ";
		
		try{
			
			preparedStatement = connection.prepareStatement(sql);
			result = preparedStatement.executeUpdate();
			
		}catch (Exception e) {
			// TODO: handle exception
		}
		return result;
	}
	
	private String getPhysicalpaymentId(String payid){
		String res="0";
		try {
			PreparedStatement ps= connection.prepareStatement(" select id from his_payment_record_physical where payment_id='"+payid+"'   ");
			ResultSet rs= ps.executeQuery();
			while(rs.next()){
				res=""+rs.getInt(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return res;
	}
	
	private String getPhysicalpaymentIdAdvRef(String invoiceid){
		String res="0";
		try {
			PreparedStatement ps= connection.prepareStatement(" select id from his_payment_record_physical where adv_ref_id='"+invoiceid+"'   ");
			ResultSet rs= ps.executeQuery();
			while(rs.next()){
				res=""+rs.getInt(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return res;
	}

	public double getRequestedDiscountAmt(String fromDate, String toDate, String userid, int i) {
		double amt =0;
		try {
			toDate =toDate+" "+"59:59:59";
			StringBuffer buffer = new StringBuffer();
			buffer.append("select disc_type, disc_amount, invoiceamount from discount_request ");
			buffer.append("inner join apm_charges_invoice on apm_charges_invoice.id = invoiceid ");
			buffer.append("where disc_request='"+i+"' and discount_request.deleted=0 ");
			buffer.append("and requested_date between '"+fromDate+"' and '"+toDate+"' ");
			if(userid!=null){
				if(userid.equals("")||userid.equals("0")){
					
				}else{
					buffer.append("and requested_userid='"+userid+"' ");
				}
			}
			PreparedStatement preparedStatement = connection.prepareStatement(buffer.toString());
			ResultSet rs = preparedStatement.executeQuery();
			
			while (rs.next()) {
				double d =0;
				if(rs.getString(1).equals("0")){
					d = (rs.getDouble(3)*rs.getDouble(2))/100;
				}else{
					d = rs.getDouble(2);
				}
				amt = amt + d;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return amt;
	}

	public ArrayList<NotAvailableSlot> getDistlevelopdcount(String fromDate, String toDate) {
		PreparedStatement preparedStatement = null;
		ArrayList<NotAvailableSlot>list = new ArrayList<NotAvailableSlot>();
		String sql = "select name from admin.all_district ";
		
		try{
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()){
				NotAvailableSlot n = new NotAvailableSlot();
	
				String dbname = rs.getString(1);
				
				int opdcount = getdistopdcnt(dbname,fromDate,toDate);
				int opdcompletedcount = getopdcompletedcnt(dbname,fromDate,toDate);
				int opdinvoiced = getopdinvoicedcnt(dbname,fromDate,toDate);
				
				n.setDistrict(dbname);
				n.setOpdnt(""+opdcount+"");
				n.setCompletedcnt(""+opdcompletedcount+"");
				n.setInvoicedcnt(""+opdinvoiced+"");
				
				list.add(n);
				
			}
		}catch (Exception e) {
			// TODO: handle exception
		}
		return list;
	}

	private int getopdinvoicedcnt(String dbname, String fromDate, Object toDate) {
		PreparedStatement preparedStatement = null;
		String sql = "select count(*) from "+dbname+".apm_invoice_assesments where commencing between "
				+ " '"+fromDate+"' and '"+toDate+"' and invoiced !=0 ";
		
		int result = 0;
		try{
			
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			if(rs.next()){
				result = rs.getInt(1);
			}
		}catch (Exception e) {
			// TODO: handle exception
		}
		
		return result;
	}

	private int getopdcompletedcnt(String dbname, String fromDate, Object toDate) {
		PreparedStatement preparedStatement = null;
		String sql = "select count(*) from "+dbname+".apm_invoice where commencing between "
				+ " '"+fromDate+"' and '"+toDate+"' and chargeType='Submit' ";
		
		int result = 0;
		try{
			
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			if(rs.next()){
				result = rs.getInt(1);
			}
		}catch (Exception e) {
			// TODO: handle exception
		}
		
		return result;
	}

	private int getdistopdcnt(String dbname, String fromDate, Object toDate) {
		PreparedStatement preparedStatement = null;
		String sql = "select count(*) from "+dbname+".apm_available_slot where commencing between "
				+ " '"+fromDate+"' and '"+toDate+"' ";
		
		int result = 0;
		try{
			
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			if(rs.next()){
				result = rs.getInt(1);
			}
		}catch (Exception e) {
			// TODO: handle exception
		}
		
		return result;
	}

	public ArrayList<Accounts> getRefundPaymentReportListIpdOpd(String fromDate, String toDate, String payby,
			String paymentStatus, String thirdParty, String orderby, String order, String invoicecategory,
			String string, String userid, int isipdopd,String selectedInvctype, String paymode, boolean islmh) {
	/*	isipdopd zero for opd and 1 for ipd*/
	toDate = toDate + " 23:59:59";
	fromDate=fromDate+" 00:00:00";
	PreparedStatement preparedStatement = null;
	ArrayList<Accounts>list = new ArrayList<Accounts>();
		StringBuffer sql = new StringBuffer();

		sql.append("SELECT apm_credit_account.id,date,payby,payment_mode, ");
		sql.append("charge,debit,balance,third_party_name_id,concat(title,' ',firstname,' ',surname),advref,resetinv,payment_mode,userid,concat(his_invoice_payment_sno.cyear,'/',his_invoice_payment_sno.sno) ,client_id ");
		sql.append("FROM apm_credit_account inner join apm_patient on apm_patient.id = apm_credit_account.client_id ");
		sql.append(" left join his_invoice_payment_sno on his_invoice_payment_sno.invoiceid=apm_credit_account.id ");
		sql.append("where payment_mode IS NOT NULL and date between '"+fromDate+"' and '"+toDate+"' and ");
		sql.append("advref=1 and his_invoice_payment_sno.paymentid=0 and invtype='Refund' ");
		if(userid!=null){
			if(userid.equals("0")||userid.equals("")){
				
			}else{
				sql.append(" and userid = '"+userid+"'"); 
			}
		}
		if(islmh){
			sql.append(" and debit!='0' ");
		}else{
			if(isipdopd>0){
				sql.append(" and   advance_ipdid!='0'  and debit!='0' ");
			}else{
				sql.append(" and   advance_ipdid='0'  and debit!='0' ");
			}
		}
		
//		if(selectedInvctype!=null){
//			if(selectedInvctype.equals("0") || selectedInvctype.equals("")){
//				
//			}else{
//				sql.append(" and invoice_type = '"+selectedInvctype+"'"); 
//			}
//		}
		if(!selectedInvctype.equals("0")){
			if(selectedInvctype.contains("0,")){
				String val="";
				for (String str : selectedInvctype.split(",")) {
					if(str.equals("0")){
						continue;
					}
					if(val.equals("")){
						val=str;
					}else{
						val=val+","+str;
					}
					selectedInvctype=val;
				}
			}
		}
		if(!selectedInvctype.equals("0")){
//			String temp[] = selectedInvctype.split(",");
//			if(temp.length>1){
				//selectedInvctype = selectedInvctype.substring(selectedInvctype.length()-1);
				sql.append(" and invtypenew in("+selectedInvctype+") ");
//			}
		}
		
		paymode=DateTimeUtils.isNull(paymode);
		if(!(paymode.equals("")||paymode.equals("0"))){
			sql.append("  and payment_mode='"+paymode+"' ");	
		}
		
		sql.append(" order by id desc ");
		try{
			
			ClientDAO clientDAO= new JDBCClientDAO(connection);
			preparedStatement = connection.prepareStatement(sql.toString());
			ResultSet rs = preparedStatement.executeQuery();
			AdditionalDAO additionalDAO= new JDBCAdditionalDAO(connection);
			double advtotal = 0;
			while(rs.next()){
				Accounts accounts = new Accounts();
				accounts.setId(rs.getInt(1));
				accounts.setBghseqId(""+additionalDAO.getBalgopalSeqNum(""+accounts.getId()));
				
				//new finac year se
				String finseq=additionalDAO.getBalgopalFinacialSeqNum(""+accounts.getId());
				accounts.setBghseqId(finseq);
				
				String date = DateTimeUtils.getIndianDateTimeFormat(rs.getString(2));
				accounts.setDate(date);
				String temp[] = date.split(" ");		
				accounts.setCommencing(temp[0]);
				
				String payidby=rs.getString(3);
				if(payidby.equals(Constants.PAY_BY_CLIENT)) {
					payidby="Self";
				}else {
					payidby="TP";
				}
				
				accounts.setPayby(payidby);
				accounts.setPaymentmode(rs.getString(4));
				accounts.setCharges(DateTimeUtils.changeFormat(Double.parseDouble(rs.getString(5))));
				advtotal = advtotal + rs.getDouble(5);
				accounts.setDebitTotalx(DateTimeUtils.changeFormat(Double.parseDouble(rs.getString(6))));
				accounts.setAdvreftotal(DateTimeUtils.changeFormat(advtotal));
				
				
				Client client= clientDAO.getClientDetails(rs.getString(15));
				accounts.setAbrivationid(client.getAbrivationid());
				
				double credit = rs.getDouble(5);
				double debit = rs.getDouble(6);
				double balance = 0;
				
				accounts.setDebitAmount(debit);
				accounts.setCreditAmount(credit);
				accounts.setBalance(balance);
				
				if(rs.getString(3).equals(Constants.PAY_BY_THIRD_PARTY)){
					
					CompleteAptmDAO completeAptmDAO = new JDBCCompleteAptmDAO(connection);
				    CompleteAppointment	completeAppointment = completeAptmDAO.getInsuranceCompanyName(rs.getString(8));
				    accounts.setClientName(completeAppointment.getInsuranceCompanyName());
				}
				else{
					
					accounts.setClientName(rs.getString(9));
				}
				
				
				accounts.setBalancex(DateTimeUtils.changeFormat(balance));
				accounts.setAdvref(rs.getInt(10));
				accounts.setResetinv(rs.getString(11));
				accounts.setPaymentmode(rs.getString(12));
				accounts.setUserid(rs.getString(13));
				accounts.setRefid(rs.getString(14));
				accounts.setPhysical_payment_id(getPhysicalpaymentIdAdvRef(rs.getString(1)));
				list.add(accounts);
			}
			
			//  28 May 2018 for test
			/*ArrayList<Accounts>reuinvoiceList = getRefInvoiceList( fromDate,
					 toDate,  payby,  paymentStatus,
					 thirdParty,  orderby,  order,
					 invoicecategory,  string,  userid);
			
			list.addAll(reuinvoiceList);*/
			
		}catch(Exception e){
			e.printStackTrace();
		}
		return list;
	}

	public double getPaymentReportInvoiceAmt(String invoiceids) {
		double amt =0;
		try {
			String sql ="select sum(debit) from apm_charges_invoice where id in("+invoiceids+") ";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				amt = Math.round(rs.getDouble(1)*100.0)/100.0;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return amt;
	}

	public ArrayList<Accounts> getInvoiceReportListForPaymentReport(String fromDate, String toDate, String payby,
			String howpaid, String orderby, String order, String invcategory, String userid,
			String selectedInvctype, String sortfilter,LoginInfo loginInfo) {
		PreparedStatement preparedStatement = null;
		ArrayList<Accounts>list = new ArrayList<Accounts>();
		StringBuffer sql = new StringBuffer();
		StatementDAO statementDAO = new JDBCStatementDAO(connection);
		sql.append("select apm_charges_invoice.id,payby,date,clientid,debit,deliverstatus,discount,tpid,apm_patient.firstname,apm_charges_invoice.disctype,discamt,apm_charges_invoice.itype,userid from apm_charges_invoice inner join ");
		sql.append("apm_patient on apm_patient.id = apm_charges_invoice.clientid  inner join apm_user on apm_user.id=apm_charges_invoice.updatedby   ");
		if(loginInfo.isTime_utility_report()) {
			//for time wise
			sql.append("where date_time between '"+fromDate+"' AND '"+toDate+"' and (debit-(discount/100)*debit+discamt)=0 ");
		}else {
			sql.append("where date between '"+fromDate+"' AND '"+toDate+"' and (debit-(discount/100)*debit+discamt)=0 ");
		}
		//sql.append("where date between '"+fromDate+"' AND '"+toDate+"' and (debit-(discount/100)*debit+discamt)=0 ");
		if(payby!=null){
			if(!payby.equals("0")){
				if(payby.equals(Constants.PAY_BY_CLIENT)){
					sql.append("and payby='Client' ");
				}else{
					sql.append("and payby='Third Party' ");
				}
			}
		}
		if(!invcategory.equals("2")){
			sql.append("and apm_charges_invoice.invpstype="+invcategory+" ");
		}
		if(!selectedInvctype.equals("0")){
			String temp[] = selectedInvctype.split(",");
			if(temp.length>1){
				sql.append(" and itype in("+selectedInvctype+") ");
			}
		}
		sql.append("order by apm_charges_invoice.id desc ");
		try{
			AccountsDAO accountsDAO=new JDBCAccountsDAO(connection);
			preparedStatement = connection.prepareStatement(sql.toString());
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()){
				double balance = 0;
				double result = 0.0;
				double creditAmount = 0;
				
				Accounts accounts = new Accounts();
				accounts.setId(rs.getInt(1));
				accounts.setPayby(rs.getString(2));
				accounts.setDate(DateTimeUtils.getCommencingDate1(rs.getString(3)));
				accounts.setClientid(rs.getInt(4));
				
				accounts.setDebitAmount(rs.getDouble(5));
				accounts.setDebitAmountx(DateTimeUtils.changeFormat(rs.getDouble(5)));
				
				accounts.setDeliverstatus(rs.getString(6));
				
				double debit = rs.getDouble(5);
				double total = rs.getDouble(5);
				int disctype = rs.getInt(10);
				double discamt = rs.getDouble(11);
				double discount = rs.getDouble(7);
				double r1 = (total*discount)/100;
				if(disctype==1){
					r1 = discamt;
				}
				accounts.setUserid(rs.getString(13));
				accounts.setDiscAmmount(DateTimeUtils.changeFormat(r1));
				
				total = total-r1;
				result = result + total;
				
				creditAmount = getCreditAmount(rs.getDouble(1));
				
				//if refund against invoice
				double refundAmt = statementDAO.getRefundAmtAgainsInvoice(rs.getInt(1));
				accounts.setRefundAmountx(DateTimeUtils.changeFormat(refundAmt));
				creditAmount = creditAmount - refundAmt;
				
				accounts.setCreditAmount(creditAmount);
				accounts.setCreditCharge(Double.toString(creditAmount));
				accounts.setCreditTotalx(DateTimeUtils.changeFormat(creditAmount));
				
				
				balance = result - creditAmount;
				accounts.setBalance(balance);
				accounts.setBalancex(DateTimeUtils.changeFormat(balance));
				
				int noOfCharges = getNoOfCharges(rs.getInt(1));
				accounts.setNumberOfChages(noOfCharges);
				accounts.setDiscount(rs.getDouble(7));
				String clientname = getClientFullName(rs.getString(4));
				String clientabr= getClientUHID(rs.getString(4));
				accounts.setAbrivationid(clientabr);
				accounts.setClientName(clientname);
				String payee = rs.getString(2);
				String payeename = null;
				if(payee.equalsIgnoreCase("Third Party")){
					payeename = getTpname(rs.getInt(8));
					
				}
				else{
					payeename = clientname;
				}
				accounts.setPayeeName(payeename);
				//showing seq no instead of invoice id 21/09/2018
				
			    String ipdopdseq=accountsDAO.getIpdOpdSeqNoWithType(rs.getInt(1));
			    accounts.setIpdopdseq(ipdopdseq);
			    if(loginInfo.isBalgopal() || loginInfo.isSeq_no_gen()){
			    	accounts.setIpdopdseq(accountsDAO.getFinancialSeqNoOfInvoice(rs.getString(1)));
			    }
			  //for ayushman
				if(loginInfo.isAyushman() || loginInfo.isMatrusevasang()) {
					Accounts accounts1=getServiceName(rs.getString(1));
					 //String serviceamt=getServiceAmt(rs.getString(3));
						/*
						 * String apmtType=""; String temp[] =
						 * accounts1.getAppointmentType().split(","); for (String string : temp) {
						 * if(!string.equals(" ")){ String data=string; if(apmtType.equals("")){
						 * apmtType=data; }else{ apmtType=apmtType+", "+data+"\n"; } } }
						 */
					accounts.setAppointmentType(accounts1.getAppointmentType());
				}
			    list.add(accounts);
				
				
				
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
		
		return list;
	}
	
	
	public ArrayList<Accounts> creditInvoiceReportList(String fromDate, String toDate, String payby,
			String paymentStatus, String thirdParty, String orderby, String order, String invoicecategory,
			String string, String userid) {
		PreparedStatement preparedStatement = null;
		ArrayList<Accounts>list = new ArrayList<Accounts>();
		StringBuffer sql = new StringBuffer();
		
		sql.append("select apm_charges_invoice.id,apm_charges_invoice.debit,sum(apm_charges_payment.payment),discount,apm_charges_invoice.discamt,fullname,userid,apm_charges_invoice.date,(debit-sum(payment)-apm_charges_invoice.discamt-(debit/discount)) ");
		sql.append(" ,payby,itype,(apm_charges_invoice.discamt+(discount/100)*debit),f_seq_no from apm_charges_invoice ");
		sql.append("inner join apm_patient on apm_patient.id = apm_charges_invoice.clientid ");
		sql.append("left join apm_charges_payment on apm_charges_payment.chargeinvoiceid=apm_charges_invoice.id ");
		sql.append(" where apm_charges_invoice.date between '"+fromDate+"' and '"+toDate+"'  ");
		//sql.append("and (debit-payment-(discamt+(discount/100)*debit))>0 ");
		/*if(userid!=null){
			sql.append("and userid='"+userid+"' ");
		}*/
		sql.append("group by apm_charges_invoice.id  ");
		try{
			preparedStatement = connection.prepareStatement(sql.toString());
			ResultSet rs = preparedStatement.executeQuery();
			double balanceTotal =0;
			AccountsDAO accountsDAO= new JDBCAccountsDAO(connection);
			while(rs.next()){
				Accounts accounts=new Accounts();
				accounts.setClientName(rs.getString(6));
				accounts.setInvoiceid(rs.getInt(1));
				accounts.setInvdate(DateTimeUtils.getCommencingDate1(rs.getString(8)));
				accounts.setDebitAmount(rs.getLong(2));
				accounts.setCreditAmount(rs.getDouble(3));
				
				
				int payment=rs.getInt(3);
				int debit=rs.getInt(2);
				int discountAmt=rs.getInt(12);
				if(((debit-payment-discountAmt))<=1){
					continue;
				}
				
				if(rs.getDouble(4)!=0){
					accounts.setDiscount(rs.getDouble(4));
				}else if(rs.getDouble(5)!=0){
					accounts.setDiscount(rs.getDouble(5));
				}else{
					accounts.setDiscount(0);
				}
				accounts.setBalance(rs.getDouble(9));
				if(rs.getDouble(9)==0){
					double discount = accounts.getDiscount();
					double bal =0;
					if(discount==0){
						bal = rs.getDouble(2) - rs.getDouble(3) ;
					}else{
						bal = rs.getDouble(2) - rs.getDouble(3) -discount;
					}
					accounts.setBalance(bal);
				}
				balanceTotal = balanceTotal + accounts.getBalance();
				accounts.setBalanceTotal(balanceTotal);
				accounts.setUserid(rs.getString(7));
				accounts.setPayby(rs.getString(10));
				accounts.setInvoicetype(accountsDAO.getInvoiceName(rs.getString(11)));
				accounts.setBghseqId(rs.getString(13));
					list.add(accounts);
				}
	
	}catch (Exception e) {
	e.printStackTrace();
}
		return list;
	}

	@Override
	public String getDepartmentName(int int1) {
		PreparedStatement preparedStatement = null;
		String result = "";
		String sql = "SELECT discipline FROM apm_discipline where id = "+int1+" ";
		
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

	@Override
	public ArrayList<Client> getChargeTypeList() {
		ArrayList<Client> list = new ArrayList<Client>();
		try {
			String sql = "SELECT id,name FROM apm_newchargetype where procedures=0";
			PreparedStatement ps = connection.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				Client client = new Client();
				client.setId(rs.getInt(1));
				client.setTypeName(rs.getString(2));
				list.add(client);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public ArrayList<Accounts> getDeptPaymentReportList(String fromDate, String toDate, String payby, String howpaid,
			String orderby, String order, String invcategory, String userid, String selectedInvctype,
			String sortfilter, String chargeType, String condition, String tpid,LoginInfo loginInfo) {
		String tp = "Third Party";String self = "Client";
		PreparedStatement preparedStatement = null;
		ArrayList<Accounts>list = new ArrayList<Accounts>();
		StatementDAO statementDAO = new JDBCStatementDAO(connection);
		StringBuffer sql = new StringBuffer();
		
		
		sql.append("select apm_charges_payment.id,apm_charges_payment.clientid,chargeinvoiceid,payment,paymode,"
				+ "apm_charges_payment.date,apm_charges_payment.tpid,payby,apm_charges_payment.clientid,apm_charges_payment.userid,"
				+ "apm_charges_invoice.id, concat(apm_user.initial,' ',apm_user.firstname,' ',apm_user.lastname),"
				+ "apm_charges_invoice.itype,apm_charges_payment.ipdno,apm_charges_payment.opdno,apm_charges_payment.invno,"
				+ "apm_charges_payment.cyear,concat(his_invoice_payment_sno.cyear,'/',his_invoice_payment_sno.sno),paymentnote,"
				+ "chequeno,cancelinv, ");
		sql.append("debit,discount,apm_charges_invoice.disctype,discamt,apm_charges_invoice.date,apm_charges_invoice.time,practid");
		sql.append(" FROM apm_charges_payment");
		sql.append(" inner join apm_charges_invoice on apm_charges_invoice.id = apm_charges_payment.chargeinvoiceid ");
		sql.append(" left join apm_user on apm_user.id =apm_charges_invoice.practid ");
		sql.append(" left join his_invoice_payment_sno on his_invoice_payment_sno.paymentid= apm_charges_payment.id ");
		//sql.append("left join apm_discipline on apm_discipline.id = apm_user.discription ");
		toDate = toDate + " " + "23:59:59";
		sql.append("where apm_charges_payment.date between '"+fromDate+"' AND '"+toDate+"' ");
		
		if(payby!=null){
			if(!payby.equals("0") && !howpaid.equals("0") ){
				sql.append("and payby='"+payby+"' and paymode='"+howpaid+"' ");
			}else if(!payby.equals("0")){
				sql.append("and payby='"+payby+"' " );
			}else if(!howpaid.equals("0")){
				sql.append("and paymode='"+howpaid+"' " );
			}
		}
		
		if(!DateTimeUtils.numberCheck(selectedInvctype).equals("0")){
			sql.append(" and itype in("+selectedInvctype+") ");
		}
		if(!condition.equals("0")){
			sql.append("and apm_user.discription='"+condition+"' ");
		}
		if(!tpid.equals("0")){
			sql.append("and apm_charges_payment.tpid='"+tpid+"' ");
		}
		
		sql.append("and paymode!='prepayment' order by apm_charges_payment.id desc");
		try{
			preparedStatement = connection.prepareStatement(sql.toString());
			ResultSet rs = preparedStatement.executeQuery();
			
			double opdtotal = 0;
			double ipdtotal = 0;
			double pathlabtotal = 0;
			double mdcinetotal = 0;
			double advreftotal = 0;
			double daycaretotal=0; 
			double balancetotal=0;
			double registrationTotal=0;
			AccountsDAO accountsDAO = new JDBCAccountsDAO(connection);
			String invoiceids="0";
			int c=0;
			while(rs.next()){
				double result = 0.0;
				Accounts accounts = new Accounts();
				int invoiceid = rs.getInt(11);
				Accounts chargedetails = getInvoiceMasterChargeList(invoiceid,fromDate,toDate,userid,chargeType);
				accounts.setMasterchargetype(chargedetails.getMasterchargetype());
				
				if(DateTimeUtils.isNull(chargedetails.getMasterchargetype()).equals("")){
					continue;
				}
				
				accounts.setId(rs.getInt(1));
				accounts.setClientid(rs.getInt(2));
				String clientName = getClientFullName(rs.getInt(2));
				String abrivationid =getClientAbrivationId(rs.getInt(2));
				accounts.setAbrivationid(abrivationid);
				accounts.setClientName(clientName);
				accounts.setInvoiceid(rs.getInt(3));
				accounts.setAmount(rs.getDouble(4));
				accounts.setAmountx(DateTimeUtils.changeFormat(rs.getDouble(4)));
				accounts.setPaymentmode(rs.getString(5));
				accounts.setDate(DateTimeUtils.getIndianDateTimeFormat(rs.getString(6)));
				accounts.setInvoicedate(DateTimeUtils.getCommencingDate1(rs.getString(26))+" "+rs.getString(27));
				String whopay = getWhoPay(rs.getInt(3));
				accounts.setWhoPay(whopay);
				if(whopay.equals(tp)){
					String invoicee = getThirdPartyCompanyName(rs.getString(7));
					accounts.setInvoicee(invoicee);
				}else{
					accounts.setInvoicee(clientName);
				}
				accounts.setUserid(rs.getString(10));
				
				
				invoiceids = invoiceids +","+invoiceid;
				accounts.setInvoiceids(invoiceids);
				accounts.setInvoiceid(invoiceid);
				
				/*ArrayList<Master>masterAssessmentList = accountsDAO.getMasterAssessmentPmntRptList(invoiceid,fromDate,toDate,userid);
				accounts.setMasterAssessmentList(masterAssessmentList);*/
				
				String invoicenameid=accountsDAO.getInvoiceTypeId(invoiceid);
				if(invoicenameid.equals("1")){
					opdtotal = opdtotal + accounts.getAmount();
				}
				
				if(invoicenameid.equals("2")){
					ipdtotal = ipdtotal + accounts.getAmount();
				}
				
				if(invoicenameid.equals("3")){
					pathlabtotal = pathlabtotal + accounts.getAmount();
				}
				
				if(invoicenameid.equals("4")||invoicenameid.equals("6")||invoicenameid.equals("7")){
					mdcinetotal = mdcinetotal + accounts.getAmount();
				}

				if(invoicenameid.equals("8")){
					daycaretotal = daycaretotal + accounts.getAmount();
				}
				
				if(invoicenameid.equals("10")){
					registrationTotal = registrationTotal + accounts.getAmount();
				}
				
				String itype=rs.getString(13);
				String ipdid=rs.getString(14);
				String opdid=rs.getString(15);
				String invstid=rs.getString(16);
				String cyear=rs.getString(17);
				String type1="";
				/*if(itype!=null){
					if(itype.equals("2")){
						type1=cyear+"/"+ipdid;
					}else if(itype.equals("1")){
						type1=cyear+"/"+opdid;
					}else if(itype.equals("3")){
						type1=cyear+"/"+invstid;
					}else{
						type1=String.valueOf(accounts.getId());
					}*/
				if(rs.getString(18)!=null){
					type1= rs.getString(18);
					accounts.setNewsr(type1);	
				}else{
					accounts.setNewsr(String.valueOf(accounts.getId()));	
				}
				accounts.setOpdtotal(DateTimeUtils.changeFormat(opdtotal));
				accounts.setIpdtotal(DateTimeUtils.changeFormat(ipdtotal));
				accounts.setPathlabtotal(DateTimeUtils.changeFormat(pathlabtotal));
				accounts.setMdcinetotal(DateTimeUtils.changeFormat(mdcinetotal));
				accounts.setDaycaretotal(DateTimeUtils.changeFormat(daycaretotal));
				accounts.setRegistrationTotal(DateTimeUtils.changeFormat(registrationTotal));
				accounts.setPractitionerName(rs.getString(12));
				
				String invoicename=accountsDAO.getInvoiceName(invoicenameid);
				accounts.setInvoicenameid(invoicename);
				//showing seqno instead of invoice no.  
				//AccountsDAO accountsDAO2=new JDBCAccountsDAO(connection);
			    int ipdopdseq=accountsDAO.getIpdOpdSeqNo((rs.getInt(3)));
			    accounts.setIpdopdseq(String.valueOf(ipdopdseq));
			    
			    String finseq=accountsDAO.getFinancialSeqNoOfInvoice(""+rs.getInt(3));
				accounts.setBghseqId(finseq);
			    
			    String paymentnote=rs.getString(19);
			    String chequeno=rs.getString(20);
			    if(rs.getString(5).equals("Cheque")){
					 accounts.setPaymentNote(chequeno);
				}else {
					accounts.setPaymentNote(paymentnote);
				}
			    accounts.setCancelsts(rs.getString(21));
			    accounts.setPhysical_payment_id(getPhysicalpaymentId(rs.getString(1)));
			    
			    
			    double total = rs.getDouble(22);
				int disctype = rs.getInt(24);
				double discamt = rs.getDouble(25);
				double discount = rs.getDouble(23);
				double r1 = (total*discount)/100;
				if(disctype==1){
					r1 = discamt;
				}
				accounts.setDiscAmmount(DateTimeUtils.changeFormat(r1));
				accounts.setDebitAmountx(DateTimeUtils.changeFormat(total));
				total = total-r1;
				result = result + total;
				
				double creditAmount = getCreditAmount(rs.getDouble(11));
				
				//if refund against invoice
				double refundAmt = statementDAO.getRefundAmtAgainsInvoice(invoiceid);
				accounts.setRefundAmountx(DateTimeUtils.changeFormat(refundAmt));
				creditAmount = creditAmount - refundAmt;
				
				accounts.setCreditAmount(creditAmount);
				accounts.setCreditCharge(Double.toString(creditAmount));
				accounts.setCreditTotalx(DateTimeUtils.changeFormat(creditAmount));
				
				
				double balance = result - creditAmount;
				accounts.setBalance(balance);
				accounts.setBalancex(DateTimeUtils.changeFormat(balance));
			    accounts.setBalanceTotal(balancetotal+balance);
			    balancetotal=accounts.getBalanceTotal();
			    
			    String locationName="";
			    if(loginInfo.isPhysio()) {
			    	int locationid=getLocationIdByclientid(rs.getInt(2));
			    	locationName=getDepartmentnameByid(locationid);
			    }else {
			     locationName = getChargeDepartmentName(rs.getInt(28));
			    
			    }
			    accounts.setLocationName(locationName);
			    if(rs.getInt(7)>0){
			    	String payer = getThirdPartyCompanyName(rs.getString(7));
					accounts.setPayer(payer);
			    }
			   
			    double totalAmount=chargedetails.getTotalAmount();
				double unitChargeTotal=chargedetails.getTotalbillamount();
				double chargeDiscount=chargedetails.getDiscount();
				double nonDiscChargeAmt =0;
				if(unitChargeTotal==0){
					unitChargeTotal = totalAmount;
				}else {
					nonDiscChargeAmt = getNonDiscountChargeAmt(invoiceid,fromDate,toDate,userid,chargeType);
				}
				
				if(r1>0){
					chargeDiscount = r1;
				}
				double prePayAmt = getPreviousPaymentAmt(invoiceid,rs.getInt(1));
				double netPayableAmt = (unitChargeTotal - chargeDiscount) - prePayAmt +nonDiscChargeAmt; 
				double balAmt = netPayableAmt - accounts.getAmount();
				
				accounts.setNetPayableAmt(""+netPayableAmt);
				accounts.setInvAmt(""+(unitChargeTotal+nonDiscChargeAmt));
				accounts.setDiscAmt(""+chargeDiscount);
				accounts.setPreAmt(""+prePayAmt);
				accounts.setBalAmt(""+balAmt);
				list.add(accounts);
				System.out.println(c++);
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
		
		return list;
	}
	@Override
	public Vector<Accounts> getDeptPaymentReportList1(String fromDate, String toDate, String payby, String howpaid,
			String orderby, String order, String invoicecategory, String userid, String selectedInvctype,
			String sortfilter, String chargeType, String condition, String tpid, LoginInfo loginInfo) {
		String tp = "Third Party";String self = "Client";
		PreparedStatement preparedStatement = null;
		Vector<Accounts>list = new Vector<Accounts>();
		StatementDAO statementDAO = new JDBCStatementDAO(connection);
		AccountsDAO accountsDAO = new JDBCAccountsDAO(connection);
		StringBuffer sql = new StringBuffer();
		StringBuffer buffer=new StringBuffer();
		
		try {
			sql.append("SELECT apm_charges_invoice.id,discount,debit,disctype,discamt,date,time,practid,clientid,apm_patient.fullname,abrivationid FROM apm_charges_invoice");
			sql.append(" inner join apm_patient on apm_patient.id=apm_charges_invoice.clientid ");
			sql.append(" inner join apm_user on apm_user.id =apm_charges_invoice.practid where date between '"+fromDate+"' and '"+toDate+"' ");
					
			if(!condition.equals("0")){
				sql.append("and apm_user.discription='"+condition+"' ");
			}if(!payby.equals("0")){
				sql.append("and payby='"+payby+"' " );
			}if(!tpid.equals("0")){
				sql.append("and tpid='"+tpid+"' ");
			}
			if(!DateTimeUtils.numberCheck(selectedInvctype).equals("0")){
				sql.append(" and itype in("+selectedInvctype+") ");
			}
			/*
			 * sql.
			 * append("select masterchargetype,invoiced,clientId,sum(charge * quantity),sum(quantity * unitcharge),sum(chargedisc) from apm_invoice_assesments where invoiced!=0 and "
			 * ); sql.append("commencing between '"+fromDate+"' and '"
			 * +toDate+"' group by invoiced ");
			 */
			preparedStatement = connection.prepareStatement(sql.toString());
			ResultSet rs = preparedStatement.executeQuery();
			int res =0;
			double total=0;
			double unitCharge=0;
			double discount=0;
			double balancetotal=0;
			while(rs.next()){
				double result = 0.0;
				Accounts accounts=new Accounts();
				int invoiceid=rs.getInt(2);
				Accounts accinvoice=getInvoiveData(invoiceid);
				
				 buffer=buffer.append(rs.getString(1)+",");
				 accounts.setDiscount(rs.getDouble(2));
				 accounts.setDebitAmount(rs.getDouble(3));
				 accounts.setDisctype(rs.getString(4));
				 accounts.setDiscamt(rs.getString(5));
				 accounts.setDate(rs.getString(6)+" "+rs.getString(7));
				 accounts.setPractitionerId(rs.getInt(8));
				 accounts.setClientid(rs.getInt(9));
				 accounts.setInvoiceid(rs.getInt(1));
				 accounts.setClientName(rs.getString(10));
				 accounts.setAbrivationid(rs.getString(11));
				/*
				 * double ttotal=0; ttotal = ttotal + rs.getDouble(4); unitCharge = unitCharge +
				 * rs.getDouble(5); discount = discount + rs.getDouble(6);
				 * 
				 * accounts.setTotalAmount(ttotal); accounts.setTotalbillamount(unitCharge);
				 * accounts.setDiscount(discount);
				 */
				
				/*
				 * accounts.setInvoicedate(accinvoice.getInvoicedate());
				 * accounts.setMasterchargetype(rs.getString(1)); String
				 * invoicename=accountsDAO.getInvoiceName(""+accinvoice.getItype());
				 */
				
				//Accounts accpayment=getPaymentData(invoiceid);
				
				/*
				 * Accounts accpayment=new Accounts(); accounts.setDate(accpayment.getDate());
				 * accounts.setPaymentmode(accpayment.getPaymentmode());
				 * accounts.setAmount(accpayment.getAmount());
				 * 
				 * String locationName="";
				 * 
				 * if(loginInfo.isPhysio()) { int
				 * locationid=getLocationIdByclientid(accpayment.getClientid());
				 * locationName=getDepartmentnameByid(locationid); }else { locationName
				 * =getChargeDepartmentName(accinvoice.getPractitionerId()); }
				 * 
				 * accounts.setLocationName(locationName); if(accpayment.getTpid()>0){ String
				 * payer = getThirdPartyCompanyName(""+accpayment.getTpid());
				 * accounts.setPayer(payer); }
				 * 
				 * String clientName = getClientFullName(rs.getInt(3)); String abrivationid
				 * =getClientAbrivationId(rs.getInt(3)); accounts.setAbrivationid(abrivationid);
				 * accounts.setClientName(clientName);
				 * 
				 * accounts.setInvoicenameid(invoicename);
				 * accounts.setBghseqId(accinvoice.getFseqno());
				 */
				
				
				/*
				 * total =Double.parseDouble(accinvoice.getDebitAmountx()); int disctype =
				 * Integer.parseInt(accinvoice.getDisctype()); double discamt
				 * =Double.parseDouble(accinvoice.getDiscamt()); discount =
				 * accinvoice.getDiscount(); double r1 = (total*discount)/100; if(disctype==1){
				 * r1 = discamt; } accounts.setDiscAmmount(DateTimeUtils.changeFormat(r1));
				 * accounts.setDebitAmountx(DateTimeUtils.changeFormat(total)); total =
				 * total-r1; result = result + total;
				 */
				
				/*
				 * //double creditAmount = getCreditAmount(accinvoice.getId()); double
				 * creditAmount=0; //if refund against invoice double refundAmt =
				 * statementDAO.getRefundAmtAgainsInvoice(invoiceid);
				 * accounts.setRefundAmountx(DateTimeUtils.changeFormat(refundAmt));
				 * creditAmount = creditAmount - refundAmt;
				 * 
				 * accounts.setCreditAmount(creditAmount);
				 * accounts.setCreditCharge(Double.toString(creditAmount));
				 * accounts.setCreditTotalx(DateTimeUtils.changeFormat(creditAmount));
				 * 
				 * double balance = result - creditAmount; accounts.setBalance(balance);
				 * accounts.setBalancex(DateTimeUtils.changeFormat(balance));
				 * accounts.setBalanceTotal(balancetotal+balance);
				 * balancetotal=accounts.getBalanceTotal();
				 * 
				 * double totalAmount=accounts.getTotalAmount(); double
				 * unitChargeTotal=accounts.getTotalbillamount(); double
				 * chargeDiscount=accounts.getDiscount(); double nonDiscChargeAmt =0;
				 * if(unitChargeTotal==0){ unitChargeTotal = totalAmount; }else {
				 * nonDiscChargeAmt =
				 * getNonDiscountChargeAmt(invoiceid,fromDate,toDate,userid,chargeType); }
				 * 
				 * if(r1>0){ chargeDiscount = r1; } //double prePayAmt =
				 * getPreviousPaymentAmt(invoiceid,accpayment.getId()); double prePayAmt=0;
				 * double netPayableAmt = (unitChargeTotal - chargeDiscount) - prePayAmt
				 * +nonDiscChargeAmt; double balAmt = netPayableAmt - accounts.getAmount();
				 * 
				 * accounts.setNetPayableAmt(""+netPayableAmt);
				 * accounts.setInvAmt(""+(unitChargeTotal+nonDiscChargeAmt));
				 * accounts.setDiscAmt(""+chargeDiscount); accounts.setPreAmt(""+prePayAmt);
				 * accounts.setBalAmt(""+balAmt);
				 */
				accounts.setInvoiceids(""+buffer);
				list.add(accounts);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		
		return list;
	}
	private Accounts getPaymentData(int invoiceid) {
		PreparedStatement preparedStatement = null;
		StringBuffer sql = new StringBuffer();
		Accounts accounts = new Accounts();
		try {
			sql.append("select date,paymode,tpid,id,payment from apm_charges_payment ");
			sql.append("where chargeinvoiceid = "+invoiceid+" ");
			preparedStatement = connection.prepareStatement(sql.toString());
			ResultSet rs = preparedStatement.executeQuery();
			
			
			while(rs.next()) {
               accounts.setDate(rs.getString(1));
               accounts.setPaymentmode(rs.getString(2));
               accounts.setTpid(rs.getInt(3));
               accounts.setId(rs.getInt(4));
               accounts.setAmount(rs.getDouble(5));
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return accounts;
		
		
	}

	private Accounts getInvoiveData(int invoiceid) {
		PreparedStatement preparedStatement = null;
		StringBuffer sql = new StringBuffer();
		Accounts accounts = new Accounts();
		try {
			sql.append("select date,time,itype,f_seq_no,practid,debit,discount,disctype,discamt,id from apm_charges_invoice ");
			sql.append("where id = "+invoiceid+" ");
			preparedStatement = connection.prepareStatement(sql.toString());
			ResultSet rs = preparedStatement.executeQuery();
			
			
			while(rs.next()) {
				accounts.setInvoicedate(DateTimeUtils.getCommencingDate1(rs.getString(1))+" "+rs.getString(2));
                accounts.setItype(rs.getString(3));
                accounts.setFseqno(rs.getString(4));
                accounts.setPractitionerId(rs.getInt(5));
                accounts.setDebitAmountx(rs.getString(6));
                accounts.setDiscount(rs.getDouble(7));
                accounts.setDisctype(rs.getString(8));
                accounts.setDiscamt(rs.getString(9));
                accounts.setId(rs.getInt(10));
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return accounts;
	}

	private int getLocationIdByclientid(int clientid) {
		PreparedStatement preparedStatement = null;
		int result = 0;
		String sql = "select dept from department_opd where clientid= "+clientid+" ";
		
		
		try{
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			if(rs.next()){
				result = rs.getInt(1);
			}
			
		}catch (Exception e) {
			// TODO: handle exception
		}
		return result;
	}

	private String getDepartmentnameByid(int id) {
		PreparedStatement preparedStatement = null;
		String result = "";
		String sql = "SELECT discipline FROM apm_discipline where id = "+id+" ";
		
		
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

	private double getNonDiscountChargeAmt(int invoiceid, String fromDate, String toDate, String userid,
			String chargeType) {

		PreparedStatement preparedStatement = null;
		StringBuffer sql = new StringBuffer();
		
		/*
		 * sql.
		 * append("select masterchargetype,sum(charge * quantity),sum(quantity * unitcharge),sum(chargedisc) from apm_invoice_assesments "
		 * ); sql.append("where invoiced = "+invoiceid+" and unitcharge=0 ");
		 */
		sql.append("select masterchargetype,sum(charge * quantity) from apm_invoice_assesments ");
		sql.append("where invoiced = "+invoiceid+" and unitcharge=0 ");
		if(!chargeType.equals("0")){
			sql.append("and masterchargetype='"+chargeType+"' ");
		}
		sql.append("group by masterchargetype ");
		
		double amount=0;
		try{
			preparedStatement = connection.prepareStatement(sql.toString());
			ResultSet  rs = preparedStatement.executeQuery();
			
			while(rs.next()){
				amount = amount + rs.getDouble(2);
			}
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		
	
		return amount;
	}

	private double getPreviousPaymentAmt(int invoiceid, int paymentid) {

		PreparedStatement preparedStatement = null;
		double result = 0;
		String sql = "SELECT sum(payment) FROM apm_charges_payment where chargeinvoiceid = "+invoiceid+" and  id<'"+paymentid+"'";
		
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

	private Accounts getInvoiceMasterChargeList(int invoiceid, String fromDate, String toDate, String userid, String chargeType) {

		PreparedStatement preparedStatement = null;
		StringBuffer sql = new StringBuffer();
		
		/*sql.append("select masterchargetype,sum(charge * quantity),sum(quantity * unitcharge),sum(chargedisc) from apm_charges_payment inner join apm_invoice_assesments on ");
		sql.append("apm_invoice_assesments.invoiced = apm_charges_payment.chargeinvoiceid ");
		sql.append("where apm_charges_payment.chargeinvoiceid = "+invoiceid+" ");
		if(!chargeType.equals("0")){
			sql.append("and masterchargetype='"+chargeType+"' ");
		}
		sql.append("group by masterchargetype ");*/
		
		sql.append("select masterchargetype,sum(charge * quantity),sum(quantity * unitcharge),sum(chargedisc) from apm_invoice_assesments ");
		sql.append("where invoiced = "+invoiceid+" ");
		if(!chargeType.equals("0")){
			sql.append("and masterchargetype='"+chargeType+"' ");
		}
		sql.append("group by masterchargetype ");
		
		String masterCharge="";
		Accounts accounts = new Accounts();
		try{
			preparedStatement = connection.prepareStatement(sql.toString());
			ResultSet  rs = preparedStatement.executeQuery();
			int res =0;
			double total=0;
			double unitCharge=0;
			double discount=0;
			while(rs.next()){
				if(res==0){
					masterCharge = rs.getString(1);
				}else{
					masterCharge = masterCharge +","+rs.getString(1);
				}
				res++;
				total = total + rs.getDouble(2);
				unitCharge = unitCharge + rs.getDouble(3);
				discount = discount + rs.getDouble(4);
				
				accounts.setMasterchargetype(masterCharge);
				accounts.setTotalAmount(total);
				accounts.setTotalbillamount(unitCharge);
				accounts.setDiscount(discount);
			}
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		return accounts;
	
	}

	@Override
	public Map<Integer, Accounts> getInvoiceDetailsList(String fromDate, String toDate, String clientid) {
		Map<Integer, Accounts> map = new HashMap<Integer, Accounts>();
		try {
			StringBuffer buffer = new StringBuffer();
			buffer.append("select id,paid,chargetype,howpaid,appointmentid,date from apm_invoice ");
			buffer.append("where apm_invoice.date between '"+fromDate+"' AND '"+toDate+"' ");
			if(!clientid.equals("")){
				buffer.append(" and apm_invoice.clientid='"+clientid+"'");
			}
			PreparedStatement preparedStatement = connection.prepareStatement(buffer.toString());
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				Accounts accounts = new Accounts();
				accounts.setId(rs.getInt(1));
				accounts.setPaid(rs.getBoolean(2));
				accounts.setChargeType(rs.getString(3));
				accounts.setHowPaid(rs.getString(4));
				accounts.setAppointmentid(rs.getInt(5));
				accounts.setDate(rs.getString(6));
				map.put(accounts.getId(), accounts);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return map;
	}

	@Override
	public ArrayList<Accounts> getChargesReportDetailedList(Map<Integer, Accounts> invoiceDetailsList, String ids) {
		ArrayList<Accounts> accountsList = new ArrayList<>();
		try {
			ClientDAO clientDAO = new JDBCClientDAO(connection);
			SummaryReportDAO summaryReportDAO = new JDBCSummaryReportDAO(connection);
			CompleteAptmDAO completeAptmDAO = new JDBCCompleteAptmDAO(connection);
			AccountsDAO accountsDAO = new JDBCAccountsDAO(connection);
			UserProfileDAO userProfileDAO = new JDBCUserProfileDAO(connection);
			StringBuffer buffer = new StringBuffer();
			buffer.append("SELECT commencing,apmtType,charge,invoiceid,id,paybuy,clientId,thirdPartyId,masterchargetype, ");
			buffer.append("quantity,practitionerId,wardid,chargedisc,ipdid,invoiced ");
			buffer.append("FROM apm_invoice_assesments where active=1 ");
			buffer.append("and invoiceid in ("+ids+") ");
			//System.out.println(ids);
			PreparedStatement preparedStatement = connection.prepareStatement(buffer.toString());
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				Accounts accounts = new Accounts();
				int apm_invoice_id = rs.getInt(4);
				Accounts invoiceAccount = invoiceDetailsList.get(apm_invoice_id);
				accounts.setInvdate(invoiceAccount.getDate());
				accounts.setChargeType(invoiceAccount.getChargeType());
				accounts.setCommencing(DateTimeUtils.getCommencingDate1(rs.getString(1)));
				accounts.setInvoiceid(apm_invoice_id);
				int invoiced = rs.getInt(15);
				//boolean chargesInvoiced = checkChargeInvoiced(apm_invoice_id);
				boolean chargesInvoiced = false;
				if(invoiced>0){
					chargesInvoiced = true;
				}
				accounts.setChargesInvoiced(chargesInvoiced);
				accounts.setClientid(rs.getInt(7));
				String clientname= clientDAO.getClientFullName(String.valueOf(accounts.getClientid()));
				accounts.setClientName(clientname);
				accounts.setAbrivationid(getClientUHID(""+accounts.getClientid()));
				if(rs.getString(6)!=null){
					if(rs.getString(6).equals("1")){
						accounts.setWhoPay("Third Party");
				     	CompleteAppointment completeAppointment =  completeAptmDAO.getInsuranceCompanyName(rs.getString(7));
				     	String tpName = completeAppointment.getInsuranceCompanyName();
				     	accounts.setTpName(tpName);
					}
				}else{
					accounts.setWhoPay("Self");
				}
				
				String ipdabrivationid = summaryReportDAO.getPatientIPDAbrivationId(rs.getString(14));
				accounts.setIpdabrivationid(ipdabrivationid);
				String wardname=completeAptmDAO.getwardnamebywardid(rs.getInt(12));
				if(wardname==null){
					wardname="";
				}
				accounts.setWard(wardname);
				accounts.setAppointmentType(rs.getString(2));
				accounts.setCreditCharge(rs.getString(3));
				accounts.setQuantity(rs.getInt(10));
				accounts.setTotalAmount(rs.getDouble(10)*rs.getDouble(3));
				
				Accounts chargeInvoiceAccount = getChargeInvoiceAccountDetails(invoiced);
				if(chargeInvoiceAccount.getId()==0){
					continue;
				}
				accounts.setFseqno(chargeInvoiceAccount.getFseqno());
				String locationName = getChargeDepartmentName(chargeInvoiceAccount.getPractitionerId());
				accounts.setLocationName(locationName);
				
				String practitionerName= userProfileDAO.getFullName(""+chargeInvoiceAccount.getPractitionerId());
				accounts.setUsername(practitionerName);
				accounts.setDebitAmount(chargeInvoiceAccount.getDebitAmount());
				
				if(rs.getDouble(13)>0){
					accounts.setDiscount(rs.getDouble(13));
				}else{
					accounts.setDiscount(chargeInvoiceAccount.getDiscount());
				}
				accounts.setItype(accountsDAO.getInvoiceName(chargeInvoiceAccount.getItype()));
				
				String chgreuser= userProfileDAO.getFullName(rs.getString(11));
				accounts.setFirstname(chgreuser);
				accountsList.add(accounts);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return accountsList;
	}

	private Accounts getChargeInvoiceAccountDetails(int invoiced) {
		Accounts accounts = new Accounts();
		try {
			StringBuffer buffer = new StringBuffer();
			buffer.append("select practid,debit,discamt,id,itype,f_seq_no from apm_charges_invoice ");
			buffer.append("where id="+invoiced+" and apm_charges_invoice.chargetype='Submit'  ");
			PreparedStatement preparedStatement = connection.prepareStatement(buffer.toString());
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				accounts.setPractitionerId(rs.getInt(1));
				accounts.setDebitAmount(rs.getDouble(2));
				accounts.setDiscount(rs.getDouble(3));
				accounts.setId(rs.getInt(4));
				accounts.setItype(rs.getString(5));
				accounts.setFseqno(rs.getString(6));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return accounts;
	}

	@Override
	public ArrayList<Accounts> getChargesReportDetailedLists(String fromDate, String toDate) {
		ArrayList<Accounts> accountsList = new ArrayList<>();
		try {
			ClientDAO clientDAO = new JDBCClientDAO(connection);
			SummaryReportDAO summaryReportDAO = new JDBCSummaryReportDAO(connection);
			CompleteAptmDAO completeAptmDAO = new JDBCCompleteAptmDAO(connection);
			AccountsDAO accountsDAO = new JDBCAccountsDAO(connection);
			UserProfileDAO userProfileDAO = new JDBCUserProfileDAO(connection);
			StringBuffer buffer = new StringBuffer();
			buffer.append("SELECT commencing,apmtType,charge,invoiceid,apm_invoice_assesments.id,paybuy, ");
			buffer.append("apm_invoice_assesments.clientId,thirdPartyId,masterchargetype,quantity, ");
			buffer.append("apm_invoice_assesments.practitionerId,wardid,chargedisc,apm_invoice_assesments.ipdid,invoiced, ");
			buffer.append("apm_invoice.date,apm_invoice.chargetype,  ");
			buffer.append("practid,apm_charges_invoice.debit,discamt,itype,f_seq_no ");
			buffer.append("FROM apm_invoice ");
			buffer.append("inner join  apm_invoice_assesments on apm_invoice.id = apm_invoice_assesments.invoiceid ");
			buffer.append("inner join apm_charges_invoice on apm_charges_invoice.id = apm_invoice_assesments.invoiced ");
			buffer.append("where active=1 and apm_invoice.date between '"+fromDate+"' AND '"+toDate+"' ");
			buffer.append("and apm_charges_invoice.chargetype='Submit' ");
			PreparedStatement preparedStatement = connection.prepareStatement(buffer.toString());
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				Accounts accounts = new Accounts();
				int apm_invoice_id = rs.getInt(4);
				accounts.setInvdate(rs.getString(16));
				accounts.setChargeType(rs.getString(17));
				
				accounts.setCommencing(DateTimeUtils.getCommencingDate1(rs.getString(1)));
				accounts.setInvoiceid(apm_invoice_id);
				int invoiced = rs.getInt(15);
				boolean chargesInvoiced = false;
				if(invoiced>0){
					chargesInvoiced = true;
				}
				accounts.setChargesInvoiced(chargesInvoiced);
				accounts.setClientid(rs.getInt(7));
				String clientname= clientDAO.getClientFullName(String.valueOf(accounts.getClientid()));
				accounts.setClientName(clientname);
				accounts.setAbrivationid(getClientUHID(""+accounts.getClientid()));
				if(rs.getString(6)!=null){
					if(rs.getString(6).equals("1")){
						accounts.setWhoPay("Third Party");
				     	CompleteAppointment completeAppointment =  completeAptmDAO.getInsuranceCompanyName(rs.getString(7));
				     	String tpName = completeAppointment.getInsuranceCompanyName();
				     	accounts.setTpName(tpName);
					}
				}else{
					accounts.setWhoPay("Self");
				}
				
				String ipdabrivationid = summaryReportDAO.getPatientIPDAbrivationId(rs.getString(14));
				accounts.setIpdabrivationid(ipdabrivationid);
				String wardname=completeAptmDAO.getwardnamebywardid(rs.getInt(12));
				if(wardname==null){
					wardname="";
				}
				accounts.setWard(wardname);
				accounts.setAppointmentType(rs.getString(2));
				accounts.setCreditCharge(rs.getString(3));
				accounts.setQuantity(rs.getInt(10));
				accounts.setTotalAmount(rs.getDouble(10)*rs.getDouble(3));
				
				accounts.setFseqno(rs.getString(22));
				String locationName = getChargeDepartmentName(rs.getInt(18));
				accounts.setLocationName(locationName);
				
				String practitionerName= userProfileDAO.getFullName(""+rs.getInt(18));
				accounts.setUsername(practitionerName);
				accounts.setDebitAmount(rs.getDouble(19));
				
				if(rs.getDouble(13)>0){
					accounts.setDiscount(rs.getDouble(13));
				}else{
					accounts.setDiscount(rs.getDouble(20));
				}
				accounts.setItype(accountsDAO.getInvoiceName(rs.getString(21)));
				
				String chgreuser= userProfileDAO.getFullName(rs.getString(11));
				accounts.setFirstname(chgreuser);
				accountsList.add(accounts);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return accountsList;
	}

	

	@Override
	public String getInvoiceFinacialSequence(String fromDate, int i, String string ,String toDate,LoginInfo loginInfo) {
		// TODO Auto-generated method stub
		Accounts accounts = new Accounts();
		String seqNo="";
		try {
			
			StringBuffer buffer = new StringBuffer();
			
			buffer.append("select f_seq_no from apm_charges_invoice ");
			if(loginInfo.isLmh() || loginInfo.isAmravati()){
				/*
				 * fromDate=fromDate+" 01:00:00"; toDate=toDate+" 23:00:00";
				 * buffer.append("where iclosedate between '"+fromDate+"' AND '"+toDate+"' ");
				 * buffer.append("and itype ='"+i+"' order by f_seq_no "+string+" limit 1  ");
				 */
				buffer.append("inner join apm_invoice_assesments on apm_invoice_assesments.invoiced=apm_charges_invoice.id ");
				buffer.append("where commencing between '"+fromDate+"' AND '"+toDate+"' ");
				buffer.append("and itype ='"+i+"' order by f_seq_no "+string+" limit 1  ");
			}else{
				   buffer.append("where date between '"+fromDate+"' AND '"+toDate+"' ");
				   buffer.append("and itype ='"+i+"' order by f_seq_no "+string+" limit 1  ");
			}
			PreparedStatement preparedStatement = connection.prepareStatement(buffer.toString());
			ResultSet rs=preparedStatement.executeQuery();
			while (rs.next()) {
				
				//accounts.setFseqno(rs.getString(1));
				seqNo=DateTimeUtils.isNull(rs.getString(1));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return seqNo;
	}

	@Override
	public String getAdvanceInvoiceFinacialSequence(String fromDate, int i, String string, String toDate ,String userid) {
		// TODO Auto-generated method stub
		
		String seqNo="";
		try {
			
			AdditionalDAO additionalDAO= new JDBCAdditionalDAO(connection);
			StringBuffer buffer = new StringBuffer();
			toDate = toDate + " 23:59:59";
			
			buffer.append("SELECT apm_credit_account.id FROM apm_credit_account ");
            buffer.append("left join apm_charges_invoice on apm_charges_invoice.id = apm_credit_account.invoiceid ");
            buffer.append("left join apm_charges_payment on apm_charges_payment.chargeinvoiceid=apm_credit_account.invoiceid ");
            buffer.append("left join his_invoice_payment_sno on his_invoice_payment_sno.invoiceid=apm_credit_account.id ");
            buffer.append("where payment_mode IS NOT NULL and apm_credit_account.date between '"+fromDate+"' ");
            buffer.append("and '"+toDate+"' and charge>0 and his_invoice_payment_sno.paymentid=0 ");
            buffer.append("and payment_mode!='prepayment' and cancelpay='0' order by apm_credit_account.id "+string+" limit 1 ");
			
			PreparedStatement preparedStatement = connection.prepareStatement(buffer.toString());
			ResultSet rs=preparedStatement.executeQuery();
			while (rs.next()) {
				
				//accounts.setFseqno(rs.getString(1));
				//seqNo=DateTimeUtils.isNull(rs.getString(1));
				seqNo=additionalDAO.getBalgopalFinacialSeqNum(""+rs.getString(1));
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return seqNo;
	}

	@Override
	public String getOpdRefundReport(String fromDate, String string, String toDate ,int selectedInvctype,LoginInfo loginInfo) {
		// TODO Auto-generated method stub
		
		String seqNo="";
		toDate = toDate + " 23:59:59";
		fromDate=fromDate+" 00:00:00";
		
		try {
			
			AdditionalDAO additionalDAO= new JDBCAdditionalDAO(connection);
			StringBuffer buffer = new StringBuffer();
			
		if(loginInfo.isBalgopal()){
			toDate = toDate + " 23:59:59";
			fromDate=fromDate+" 00:00:00";
			
			buffer.append("SELECT apm_credit_account.id FROM apm_credit_account inner join apm_patient on ");
            buffer.append("apm_patient.id = apm_credit_account.client_id left join his_invoice_payment_sno ");
            buffer.append("on his_invoice_payment_sno.invoiceid=apm_credit_account.id ");
            buffer.append("where payment_mode IS NOT NULL and date between '"+fromDate+"' ");
            buffer.append("and '"+toDate+"' and advref=1 and his_invoice_payment_sno.paymentid=0 ");
            buffer.append("and debit!='0'  and invtypenew in ("+selectedInvctype+")  order by apm_credit_account.id "+string+" limit 1  ");
			
		}else{
              
			fromDate=fromDate+" 01:00:00";
			toDate=toDate+" 23:00:00";
			
			buffer.append("SELECT apm_credit_account.id FROM apm_credit_account inner join apm_patient on ");
            buffer.append("apm_patient.id = apm_credit_account.client_id left join his_invoice_payment_sno ");
            buffer.append("on his_invoice_payment_sno.invoiceid=apm_credit_account.id ");
            buffer.append("where payment_mode IS NOT NULL and iclosedate between '"+fromDate+"' ");
            buffer.append("and '"+toDate+"' and advref=1 and his_invoice_payment_sno.paymentid=0 ");
            buffer.append("and debit!='0'  and invtypenew in ("+selectedInvctype+")  order by apm_credit_account.id "+string+" limit 1  ");
		}    
            PreparedStatement preparedStatement = connection.prepareStatement(buffer.toString());
			ResultSet rs=preparedStatement.executeQuery();
			while (rs.next()) {
				
				//accounts.setFseqno(rs.getString(1));
				//seqNo=DateTimeUtils.isNull(rs.getString(1));
				seqNo=additionalDAO.getBalgopalFinacialSeqNum(""+rs.getString(1));
				
			}
            
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		
		return seqNo;
	}

	@Override
	public String getIpdRefundReport(String fromDate, String string, String toDate, int selectedInvctype,LoginInfo loginInfo) {
		// TODO Auto-generated method stub
		
		String seqNo="";
		/*toDate = toDate + " 23:59:59";
		fromDate=fromDate+" 00:00:00";*/
		
		try {
			
			AdditionalDAO additionalDAO= new JDBCAdditionalDAO(connection);
			StringBuffer buffer = new StringBuffer();
			
			if(loginInfo.isBalgopal()){
				toDate = toDate + " 23:59:59";
				fromDate=fromDate+" 00:00:00";
				
				buffer.append("SELECT apm_credit_account.id FROM apm_credit_account inner join apm_patient on ");
	            buffer.append("apm_patient.id = apm_credit_account.client_id left join his_invoice_payment_sno ");
	            buffer.append("on his_invoice_payment_sno.invoiceid=apm_credit_account.id ");
	            buffer.append("where payment_mode IS NOT NULL and date between '"+fromDate+"' ");
	            buffer.append("and '"+toDate+"' and advref=1 and his_invoice_payment_sno.paymentid=0 ");
	            buffer.append("and debit!='0'  and invtypenew in ("+selectedInvctype+")  order by apm_credit_account.id "+string+" limit 1  ");
			}else{
				fromDate=fromDate+" 01:00:00";
				toDate=toDate+" 23:00:00";
				
			buffer.append("SELECT apm_credit_account.id FROM apm_credit_account inner join apm_patient on ");
            buffer.append("apm_patient.id = apm_credit_account.client_id left join his_invoice_payment_sno ");
            buffer.append("on his_invoice_payment_sno.invoiceid=apm_credit_account.id ");
            buffer.append("where payment_mode IS NOT NULL and iclosedate between '"+fromDate+"' ");
            buffer.append("and '"+toDate+"' and advref=1 and his_invoice_payment_sno.paymentid=0 ");
            buffer.append("and debit!='0'  and invtypenew in ("+selectedInvctype+")  order by apm_credit_account.id "+string+" limit 1  ");
		}      
            PreparedStatement preparedStatement = connection.prepareStatement(buffer.toString());
			ResultSet rs=preparedStatement.executeQuery();
			
            while (rs.next()) {
				
				//accounts.setFseqno(rs.getString(1));
				//seqNo=DateTimeUtils.isNull(rs.getString(1));
				seqNo=additionalDAO.getBalgopalFinacialSeqNum(""+rs.getString(1));
				
			}
			
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		return seqNo;
	}

	@Override
	public int getSittingAndFollowUpCount(String fromDate, String toDate, String department, 
			String patientCategory, String sittingOrFollowUp, String sittingName, String proceduerMasterName, String procedureName) {
		int res =0;
		try {
			StringBuffer buffer = new StringBuffer();
			buffer.append("select count(*) from sitting_followuplist ");
			buffer.append("inner join apm_patient on apm_patient.id = sitting_followuplist.patient_id ");
			buffer.append("inner join apm_discipline on apm_discipline.id = sitting_followuplist.dept_id ");
			buffer.append("inner join sittingfollowup_master on sittingfollowup_master.id = sitting_followuplist.sitting ");
			buffer.append("inner join apm_newchargetype on apm_newchargetype.id = sitting_followuplist.proceduremaster_id ");
			buffer.append("inner join apm_appointment_type on apm_appointment_type.id = sitting_followuplist.procedure_id ");
			if(sittingOrFollowUp.equals("0")){
				buffer.append("where date_time between '"+fromDate+"' and '"+toDate+" 59:59:59' ");
			}else{
				buffer.append("where followup_date!='' and STR_TO_DATE(followup_date,'%d-%m-%Y') between '"+fromDate+"' and '"+toDate+"' ");
			}
			if(!patientCategory.equals("")) {
				buffer.append(" and apm_patient.patientcategory='"+patientCategory+"' ");
			}
			if(!department.equals("")){
				buffer.append(" and sitting_followuplist.dept_id="+department+" ");
			}
			if(!sittingName.equals("")){
				buffer.append("and sittingfollowup_master.id='"+sittingName+"' ");
			}
			if(!proceduerMasterName.equals("")){
				buffer.append("and apm_newchargetype.id='"+proceduerMasterName+"' ");
			}
			if(!procedureName.equals("")){
				buffer.append("and apm_appointment_type.id='"+procedureName+"' ");
			}
			buffer.append(" group by sitting_followuplist.dept_id ");
			PreparedStatement preparedStatement = connection.prepareStatement(buffer.toString());
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				res = rs.getInt(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return res;
	}

	@Override
	public int getProcedureCount(String fromDate, String toDate,String patientCategory,String sittingOrFollowUp,String sittingName,String proceduerMasterName,String procedureName,String department) {
		
		int res =0;
		try {
			StringBuffer buffer = new StringBuffer();
			
			//buffer.append("select sum(sitting_num) from sitting_followuplist ");
			//buffer.append("where date_time between '"+fromDate+"' and '"+toDate+" 59:59:59' ");
			
			buffer.append("select sum(sitting_num) from sitting_followuplist ");
			buffer.append("inner join apm_patient on apm_patient.id = sitting_followuplist.patient_id ");
			buffer.append("inner join apm_discipline on apm_discipline.id = sitting_followuplist.dept_id ");
			buffer.append("inner join sittingfollowup_master on sittingfollowup_master.id = sitting_followuplist.sitting ");
			buffer.append("inner join apm_newchargetype on apm_newchargetype.id = sitting_followuplist.proceduremaster_id ");
			buffer.append("inner join apm_appointment_type on apm_appointment_type.id = sitting_followuplist.procedure_id ");
			
			if(sittingOrFollowUp.equals("0")){
				buffer.append("where date_time between '"+fromDate+"' and '"+toDate+" 59:59:59' ");
			}else{
				buffer.append("where followup_date!='' and STR_TO_DATE(followup_date,'%d-%m-%Y') between '"+fromDate+"' and '"+toDate+"' ");
			}
			if(!patientCategory.equals("")) {
				buffer.append(" and apm_patient.patientcategory='"+patientCategory+"' ");
			}
			if(!department.equals("")){
				buffer.append(" and sitting_followuplist.dept_id="+department+" ");
			}
			if(!sittingName.equals("")){
				buffer.append("and sittingfollowup_master.id='"+sittingName+"' ");
			}
			if(!procedureName.equals("")){
				buffer.append("and apm_appointment_type.id='"+procedureName+"' ");
			}
			if(!proceduerMasterName.equals("")){
				buffer.append("and apm_newchargetype.id='"+proceduerMasterName+"' ");
			}
			
			PreparedStatement preparedStatement = connection.prepareStatement(buffer.toString());
			ResultSet rs = preparedStatement.executeQuery();
			
			while (rs.next()) {
				res = rs.getInt(1);
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return res;
	}
	
	@Override
	public ArrayList<NotAvailableSlot> getSittingAndFollowUpList(String fromDate, String toDate, String department,
			String patientCategory, Pagination pagination, String sittingOrFollowUp, 
			String sittingName, String proceduerMasterName, String procedureName) {
		ArrayList<NotAvailableSlot> arrayList = new ArrayList<>();
		try {
			StringBuffer buffer = new StringBuffer();
			buffer.append("select abrivationid,concat(title,' ',firstname,' ',surname),dob,gender,mobNo,address, ");
			buffer.append("patient_id,followup_date,remark, user_id, date_time,discipline,sittingFollowup, ");
			buffer.append("apm_newchargetype.name,apm_appointment_type.name,patientcategory,sitting_num,remark,diagnosis ");
			buffer.append("from sitting_followuplist ");
			buffer.append("inner join apm_patient on apm_patient.id = sitting_followuplist.patient_id ");
			buffer.append("inner join apm_discipline on apm_discipline.id = sitting_followuplist.dept_id ");
			buffer.append("inner join sittingfollowup_master on sittingfollowup_master.id = sitting_followuplist.sitting ");
			buffer.append("inner join apm_newchargetype on apm_newchargetype.id = sitting_followuplist.proceduremaster_id ");
			buffer.append("inner join apm_appointment_type on apm_appointment_type.id = sitting_followuplist.procedure_id ");
			if(sittingOrFollowUp.equals("0")){
				buffer.append("where date_time between '"+fromDate+"' and '"+toDate+" 59:59:59' ");
			}else{
				buffer.append("where followup_date!='' and STR_TO_DATE(followup_date,'%d-%m-%Y') between '"+fromDate+"' and '"+toDate+"' ");
			}
			if(!patientCategory.equals("")) {
				buffer.append(" and apm_patient.patientcategory='"+patientCategory+"' ");
			}
			if(!department.equals("")){
				buffer.append(" and sitting_followuplist.dept_id="+department+" ");
			}
			if(!sittingName.equals("")){
				buffer.append("and sittingfollowup_master.id='"+sittingName+"' ");
			}
			if(!proceduerMasterName.equals("")){
				buffer.append("and apm_newchargetype.id='"+proceduerMasterName+"' ");
			}
			if(!procedureName.equals("")){
				buffer.append("and apm_appointment_type.id='"+procedureName+"' ");
			}
			String sqls = pagination.getSQLQuery(buffer.toString());
			PreparedStatement preparedStatement = connection.prepareStatement(sqls);
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				NotAvailableSlot availableSlot = new NotAvailableSlot();
				availableSlot.setAbrivationid(rs.getString(1));
				availableSlot.setClientName(rs.getString(2));
				String age = DateTimeUtils.getAge1(rs.getString(3));
				availableSlot.setAgegender(age +" / " + rs.getString(4));
				availableSlot.setMobno(rs.getString(5));
				availableSlot.setAddress(rs.getString(6));
				availableSlot.setClientId(rs.getString(7));
				availableSlot.setFollowupDate(rs.getString(8));
				availableSlot.setRemark(rs.getString(9));
				availableSlot.setUser_id(rs.getString(10));
				availableSlot.setDatetime(DateTimeUtils.getIndianDateTimeFormat(rs.getString(11)));
				availableSlot.setDisciplineName(rs.getString(12));
				availableSlot.setSittingName(rs.getString(13));
				availableSlot.setProcedureMasterName(rs.getString(14));
				availableSlot.setProcedureName(rs.getString(15));
				availableSlot.setPatientcategory(rs.getString(16));
				availableSlot.setSittingnum(rs.getString(17));
				availableSlot.setRemark(rs.getString(18));
				availableSlot.setDiagnosisarea(rs.getString(19));
				arrayList.add(availableSlot);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return arrayList;
	}

	@Override
	public Vector<Accounts> getchargesPaymentlistbyinvid(String inv1, String fromDate, String toDate,String outsourceid,String selectedInvctype, String chargeType, String condition,  String tpid, String payby, String howpaid,LoginInfo loginInfo) {
		PreparedStatement preparedStatement=null;
		AccountsDAO accountsDAO = new JDBCAccountsDAO(connection);
		StatementDAO statementDAO = new JDBCStatementDAO(connection);
		Vector<Accounts> list=new Vector<Accounts>();
		StringBuffer buffer=new StringBuffer();
		String tp = "Third Party";
		try {
			/*
			 * buffer.
			 * append("select apm_charges_payment.date, apm_charges_invoice.debit,apm_charges_invoice.discamt,apm_charges_invoice.disctype,payment,apm_charges_invoice.date,apm_charges_invoice.time,apm_charges_invoice.practid,apm_charges_invoice.clientid,apm_charges_invoice.id,fullname,abrivationid,apm_charges_invoice.payby,apm_charges_payment.tpid,paymode,outsource from apm_charges_payment "
			 * ); buffer.
			 * append("inner join apm_charges_invoice on apm_charges_invoice.id=apm_charges_payment.chargeinvoiceid "
			 * ); buffer.
			 * append("inner join apm_patient on apm_patient.id=apm_charges_payment.clientid "
			 * ); buffer.
			 * append("inner join apm_invoice_assesments on apm_invoice_assesments.clientId=apm_charges_payment.clientid "
			 * ); buffer.append("where apm_invoice_assesments.commencing between '"
			 * +fromDate+"' and '"+toDate+"' and apm_charges_payment.chargeinvoiceid in("
			 * +inv1+")");
			 */
			 buffer.append("select apm_charges_payment.date, apm_charges_invoice.debit,apm_charges_invoice.discamt,apm_charges_invoice.disctype,payment,apm_charges_invoice.date,apm_charges_invoice.time,apm_charges_invoice.practid,apm_charges_invoice.clientid,apm_charges_invoice.id,fullname,abrivationid,apm_charges_invoice.payby,apm_charges_payment.tpid,paymode,original_pay,cancelinv,discount,chargeinvoiceid from apm_charges_payment ");
			 buffer.append("inner join apm_charges_invoice on apm_charges_invoice.id=apm_charges_payment.chargeinvoiceid ");
			 buffer.append("inner join apm_patient on apm_patient.id=apm_charges_payment.clientid ");
			 buffer.append("inner join apm_invoice_assesments on apm_invoice_assesments.clientId=apm_charges_payment.clientid ");
			 buffer.append("where apm_charges_payment.chargeinvoiceid in("+inv1+")");
			 if(!outsourceid.equals("")) {
				 buffer.append(" and outsource='"+outsourceid+"'");
			 }
				/*
				 * if(payby!=null){ if(!payby.equals("0") && !howpaid.equals("0") ){
				 * b``uffer.append("and payby='"+payby+"' and paymode='"+howpaid+"' "); }else
				 * if(!payby.equals("0")){ buffer.append("and payby='"+payby+"' " ); }else
				 * if(!howpaid.equals("0")){ buffer.append("and paymode='"+howpaid+"' " ); } }
				 * 
				 * if(!DateTimeUtils.numberCheck(selectedInvctype).equals("0")){
				 * buffer.append(" and itype in("+selectedInvctype+") "); }
				 * if(!condition.equals("0")){
				 * buffer.append("and apm_user.discription='"+condition+"' "); }
				 * if(!tpid.equals("0")){
				 * buffer.append("and apm_charges_payment.tpid='"+tpid+"' "); }
				 */
				
				buffer.append("and paymode!='prepayment' group by  apm_charges_payment.chargeinvoiceid order by apm_charges_payment.id desc");
			 preparedStatement=connection.prepareStatement(buffer.toString());
			 ResultSet rs=preparedStatement.executeQuery();
			 while(rs.next()) {
				 Accounts accounts=new Accounts();
				 accounts.setPaymentdate(rs.getString(1));
				 accounts.setDebitAmount(rs.getDouble(2));
				 accounts.setDiscamt(rs.getString(3));
				 if(rs.getString(4).equals("0")) {
					 double discount_amount_by_per=(rs.getDouble(2)*rs.getDouble(18))/100;
					 Double netpayamt=(accounts.getDebitAmount()-(discount_amount_by_per));
					 accounts.setPayAmount(rs.getDouble(5));
					 Double balance=netpayamt-accounts.getPayAmount();
					 accounts.setNetPayableAmt(""+netpayamt);
					 accounts.setBalAmt(""+balance);
				 }else {
					 Double netpayamt=(accounts.getDebitAmount()-Double.parseDouble(accounts.getDiscamt()));
					 accounts.setPayAmount(rs.getDouble(5));
					 Double balance=netpayamt-accounts.getPayAmount();
					 accounts.setNetPayableAmt(""+netpayamt);
					 accounts.setBalAmt(""+balance);
				 }
					/*
					 * Double
					 * netpayamt=(accounts.getDebitAmount()-Double.parseDouble(accounts.getDiscamt()
					 * )); accounts.setPayAmount(rs.getDouble(5)); Double
					 * balance=netpayamt-accounts.getPayAmount();
					 * accounts.setNetPayableAmt(""+netpayamt); accounts.setBalAmt(""+balance);
					 */
				 accounts.setDisctype(rs.getString(4));
				 accounts.setDate(rs.getString(6)+" "+rs.getString(7));
				 accounts.setPractitionerId(rs.getInt(8));
				 accounts.setClientid(rs.getInt(9));
				 accounts.setInvoiceid(rs.getInt(10));
				 accounts.setInvoiceid1(rs.getString(10));
				 accounts.setClientName(rs.getString(11));
				 accounts.setAbrivationid(rs.getString(12));
				 accounts.setPayby(rs.getString(13));
				 String invoicenameid=accountsDAO.getInvoiceTypeId(rs.getInt(10));
				 String invoicename=accountsDAO.getInvoiceName(invoicenameid);
					accounts.setInvoicenameid(invoicename);
				 String whopay = getWhoPay(rs.getInt(10));
					accounts.setWhoPay(whopay);
					if(whopay.equals(tp)){
						String invoicee = getThirdPartyCompanyName(rs.getString(14));
						accounts.setInvoicee(invoicee+"(Third Party)");
					}
					else{
						accounts.setInvoicee("Self");
					}
				 String locationName="";	
			
				 String deptid=getDepartmentidforlmh(rs.getString(1),rs.getString(10));	   //change 2 parameter to 9 to 10 
				 locationName=getDepartmentnameforlmh(deptid);
				 String thirdpartyname = getThirdPartyCompanyName(rs.getString(14));
				 accounts.setThirdParty(thirdpartyname);
				 //locationName = getChargeDepartmentName(rs.getInt(8));
				
				//String masterchargetype=getmasterchargetypebyinvid(rs.getInt(10));
				//accounts.setMasterchargetype(masterchargetype);
				double refundAmt = statementDAO.getRefundAmtAgainsInvoice(rs.getInt(10));
				accounts.setRefundAmountx(DateTimeUtils.changeFormat(refundAmt));
				accounts.setLocationName(deptid);
				accounts.setPaymentmode(rs.getString(15));
				if(rs.getInt(17)==1) {
				accounts.setOriginal_pay(rs.getDouble(16));
				}
				accounts.setCreditInvoiceid(rs.getInt(19));
				/*
				 * String outsource=getOutSourcenamebyid(rs.getString(16));
				 * accounts.setOutsource(outsource);
				 */
			    list.add(accounts);
			 }
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	private String getDepartmentnameforlmh(String deptid) {
		PreparedStatement preparedStatement = null;
		String deptname="";
		try {
			String sql="select discipline from apm_discipline where id='"+deptid+"' ";
			preparedStatement=connection.prepareStatement(sql);
			ResultSet rs=preparedStatement.executeQuery();
			while(rs.next()) {
				deptname=rs.getString(1);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return deptname;
	}

	private String getDepartmentidforlmh(String date, String clientid) {
		PreparedStatement preparedStatement = null;
		String dept="";
		try {
			//String sql="select dept from department_opd where commencing='"+date.split(" ")[0]+"' and clientId='"+clientid+"'";
			String sql="select masterchargetype from apm_invoice_assesments where commencing='"+date.split(" ")[0]+"' and invoiced='"+clientid+"'";
			
			preparedStatement=connection.prepareStatement(sql);
			ResultSet rs=preparedStatement.executeQuery();
			while(rs.next()) {
				dept=rs.getString(1);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dept;
	}

	private String getOutSourcenamebyid(String id) {
		PreparedStatement preparedStatement = null;
		String outsource="";
		try {
			String sql="select name from apm_outsource where id="+id+"";
			preparedStatement=connection.prepareStatement(sql);
			ResultSet rs=preparedStatement.executeQuery();
			while(rs.next()) {
				outsource=rs.getString(1);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return outsource;
	}

	private String getmasterchargetypebyinvid(int invoiceid) {
		PreparedStatement preparedStatement = null;
		StringBuffer sql = new StringBuffer();
		sql.append("select masterchargetype from apm_invoice_assesments ");
		sql.append("where invoiced = "+invoiceid+" ");
		String masterCharge="";
		
		try {
			preparedStatement = connection.prepareStatement(sql.toString());
			ResultSet  rs = preparedStatement.executeQuery();
			int res =0;
			while(rs.next()) {
			if(res==0){
				masterCharge = rs.getString(1);
			}else{
				masterCharge = masterCharge +","+rs.getString(1);
			}
			res++;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return masterCharge;
	}

	@Override
	public ArrayList<NotAvailableSlot> getDailyOpdColorList(String fromDate, String toDate) {
		ArrayList<NotAvailableSlot> List = new ArrayList<NotAvailableSlot>();
		try {
			ClientDAO clientDAO = new JDBCClientDAO(connection);
			
			StringBuffer buffer = new StringBuffer();
			buffer.append("SELECT color,datetime,abrivationid,clientname from opd_color_report ");
			buffer.append("where datetime between '"+fromDate+"' AND '"+toDate+" 59:59:59' ");
		
			PreparedStatement preparedStatement = connection.prepareStatement(buffer.toString());
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				NotAvailableSlot notAvailableSlot=new NotAvailableSlot();
				
				notAvailableSlot.setColor(rs.getString(1));
				notAvailableSlot.setDate_time(rs.getString(2));
				notAvailableSlot.setAbrivationid(rs.getString(3));
				notAvailableSlot.setClientName(rs.getString(4));
				
				List.add(notAvailableSlot);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return List;
	}

	@Override
	public ArrayList<NotAvailableSlot> getIpdSittingAndFollowUpList(String fromDate, String toDate, String department,
			String patientCategory, Pagination pagination, String sittingOrFollowUp, String sittingName,
			String proceduerMasterName, String procedureName,String deptid) {
		ArrayList<NotAvailableSlot> arrayList = new ArrayList<>();
		try {
			StringBuffer buffer = new StringBuffer();
			buffer.append("select abrivationid,concat(title,' ',firstname,' ',surname),dob,gender,mobNo,address, ");
			buffer.append("patient_id,followup_date,remark, user_id, date_time,discipline, ");
			buffer.append("patientcategory,sitting_num,remark,diagnosis,sitting ");
			buffer.append("from ipd_sitting_followuplist ");
			buffer.append("inner join apm_patient on apm_patient.id = ipd_sitting_followuplist.patient_id ");
			buffer.append("inner join apm_discipline on apm_discipline.id = ipd_sitting_followuplist.dept_id ");
			//buffer.append("inner join sittingfollowup_master on sittingfollowup_master.id = ipd_sitting_followuplist.sitting ");
			//buffer.append("inner join apm_newchargetype on apm_newchargetype.id = ipd_sitting_followuplist.proceduremaster_id ");
			//buffer.append("inner join apm_appointment_type on apm_appointment_type.id = ipd_sitting_followuplist.procedure_id ");
			buffer.append("where date_time between '"+fromDate+"' and '"+toDate+" 59:59:59' and ipd_sitting_followuplist.dept_id='"+deptid+"' ");
			
			String sqls = pagination.getSQLQuery(buffer.toString());
			PreparedStatement preparedStatement = connection.prepareStatement(sqls);
			ResultSet rs = preparedStatement.executeQuery();
			EmrDAO emrDAO2= new JDBCEmrDAO(connection); 
			while (rs.next()) {
				NotAvailableSlot availableSlot = new NotAvailableSlot();
				availableSlot.setAbrivationid(rs.getString(1));
				availableSlot.setClientName(rs.getString(2));
				String age = DateTimeUtils.getAge1(rs.getString(3));
				availableSlot.setAgegender(age +" / " + rs.getString(4));
				availableSlot.setMobno(rs.getString(5));
				availableSlot.setAddress(rs.getString(6));
				availableSlot.setClientId(rs.getString(7));
				availableSlot.setFollowupDate(rs.getString(8));
				availableSlot.setRemark(rs.getString(9));
				availableSlot.setUser_id(rs.getString(10));
				availableSlot.setDatetime(DateTimeUtils.getIndianDateTimeFormat(rs.getString(11)));
				availableSlot.setDisciplineName(rs.getString(12));
				//availableSlot.setProcedureMasterName(rs.getString(14));
				//availableSlot.setProcedureName(rs.getString(15));
				availableSlot.setPatientcategory(rs.getString(13));
				availableSlot.setSittingnum(rs.getString(14));
				availableSlot.setRemark(rs.getString(15));
				availableSlot.setDiagnosisarea(rs.getString(16));
				String ipdsessioname=emrDAO2.sessionname(rs.getString(17));
				availableSlot.setSittingName(ipdsessioname);
				arrayList.add(availableSlot);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return arrayList;
		
	}

	@Override
	public Vector<Accounts> getDeptPaymentReportListForLmh(String fromDate, String toDate, String payby,
			String howpaid, String orderby, String order, String invoicecategory, String userid,
			String selectedInvctype, String sortfilter, String chargeType, String condition, String tpid,
			LoginInfo loginInfo) {
		String tp = "Third Party";String self = "Client";
		PreparedStatement preparedStatement = null;
		Vector<Accounts>list = new Vector<Accounts>();
		StatementDAO statementDAO = new JDBCStatementDAO(connection);
		AccountsDAO accountsDAO = new JDBCAccountsDAO(connection);
		StringBuffer sql = new StringBuffer();
		StringBuffer buffer=new StringBuffer();
		
		try {
			sql.append("SELECT apm_charges_invoice.id,discount,debit,disctype,discamt,date,time,practid,apm_charges_invoice.clientid,apm_patient.fullname,abrivationid,masterchargetype,apmtType FROM apm_charges_invoice");
			sql.append(" inner join apm_patient on apm_patient.id=apm_charges_invoice.clientid ");
			sql.append(" inner join apm_invoice_assesments on apm_invoice_assesments.invoiced=apm_charges_invoice.id where date between '"+fromDate+"' and '"+toDate+"' ");
					
			if(!chargeType.equals("0")){
				sql.append("and masterchargetype='"+chargeType+"' ");
			}if(!payby.equals("0") && !payby.equals("") ){
				sql.append("and payby='"+payby+"' " );
			} /*commenting beacuase vspm multiple payer type search
				 * if(!tpid.equals("0")){ sql.append("and tpid='"+tpid+"' "); }
				 */
			if(!tpid.equals("0")){//then this code for vspm multiple payer type search
				sql.append("and tpid in ("+tpid+") ");
			}
			if(!DateTimeUtils.numberCheck(selectedInvctype).equals("0")){
				sql.append(" and itype in("+selectedInvctype+") ");
			}
			sql.append(" group by invoiced order by apm_charges_invoice.id desc");
			/*
			 * sql.
			 * append("select masterchargetype,invoiced,clientId,sum(charge * quantity),sum(quantity * unitcharge),sum(chargedisc) from apm_invoice_assesments where invoiced!=0 and "
			 * ); sql.append("commencing between '"+fromDate+"' and '"
			 * +toDate+"' group by invoiced ");
			 */
			preparedStatement = connection.prepareStatement(sql.toString());
			ResultSet rs = preparedStatement.executeQuery();
			int res =0;
			double total=0;
			double unitCharge=0;
			double discount=0;
			double balancetotal=0;
			double discount_amount_by_per=0;
			while(rs.next()){
				double result = 0.0;
				Accounts accounts=new Accounts();
				int invoiceid=rs.getInt(2);
				Accounts accinvoice=getInvoiveData(invoiceid);
				
				 buffer=buffer.append(rs.getString(1)+",");
				 accounts.setDiscount(rs.getDouble(2));
				 accounts.setDebitAmount(rs.getDouble(3));
				 accounts.setDisctype(rs.getString(4));
				 accounts.setDiscamt(rs.getString(5));
				 accounts.setDate(rs.getString(6)+" "+rs.getString(7));
				 accounts.setPractitionerId(rs.getInt(8));
				 accounts.setClientid(rs.getInt(9));
				 accounts.setInvoiceid(rs.getInt(1));
				 accounts.setInvoiceid1(rs.getString(1));
				 accounts.setClientName(rs.getString(10));
				 accounts.setAbrivationid(rs.getString(11));
				 accounts.setMasterchargetype(rs.getString(12));
				 String code=getapmtcode(rs.getString(12),rs.getString(13));
				 if(accounts.getDisctype().equals("0")) {
						discount_amount_by_per=(rs.getDouble(3)*rs.getDouble(2))/100;
						accounts.setDiscAmmount(Double.toString(discount_amount_by_per));
					}else {
						accounts.setBilldiscamount(rs.getDouble(5));
						accounts.setDiscAmmount(Double.toString(rs.getDouble(5)));
					}
				/*
				 * double ttotal=0; ttotal = ttotal + rs.getDouble(4); unitCharge = unitCharge +
				 * rs.getDouble(5); discount = discount + rs.getDouble(6);
				 * 
				 * accounts.setTotalAmount(ttotal); accounts.setTotalbillamount(unitCharge);
				 * accounts.setDiscount(discount);
				 */
				
				/*
				 * accounts.setInvoicedate(accinvoice.getInvoicedate());
				 * accounts.setMasterchargetype(rs.getString(1)); String
				 * invoicename=accountsDAO.getInvoiceName(""+accinvoice.getItype());
				 */
				
				//Accounts accpayment=getPaymentData(invoiceid);
				
				/*
				 * Accounts accpayment=new Accounts(); accounts.setDate(accpayment.getDate());
				 * accounts.setPaymentmode(accpayment.getPaymentmode());
				 * accounts.setAmount(accpayment.getAmount());
				 * 
				 * String locationName="";
				 * 
				 * if(loginInfo.isPhysio()) { int
				 * locationid=getLocationIdByclientid(accpayment.getClientid());
				 * locationName=getDepartmentnameByid(locationid); }else { locationName
				 * =getChargeDepartmentName(accinvoice.getPractitionerId()); }
				 * 
				 * accounts.setLocationName(locationName); if(accpayment.getTpid()>0){ String
				 * payer = getThirdPartyCompanyName(""+accpayment.getTpid());
				 * accounts.setPayer(payer); }
				 * 
				 * String clientName = getClientFullName(rs.getInt(3)); String abrivationid
				 * =getClientAbrivationId(rs.getInt(3)); accounts.setAbrivationid(abrivationid);
				 * accounts.setClientName(clientName);
				 * 
				 * accounts.setInvoicenameid(invoicename);
				 * accounts.setBghseqId(accinvoice.getFseqno());
				 */
				
				
				/*
				 * total =Double.parseDouble(accinvoice.getDebitAmountx()); int disctype =
				 * Integer.parseInt(accinvoice.getDisctype()); double discamt
				 * =Double.parseDouble(accinvoice.getDiscamt()); discount =
				 * accinvoice.getDiscount(); double r1 = (total*discount)/100; if(disctype==1){
				 * r1 = discamt; } accounts.setDiscAmmount(DateTimeUtils.changeFormat(r1));
				 * accounts.setDebitAmountx(DateTimeUtils.changeFormat(total)); total =
				 * total-r1; result = result + total;
				 */
				
				/*
				 * //double creditAmount = getCreditAmount(accinvoice.getId()); double
				 * creditAmount=0; //if refund against invoice double refundAmt =
				 * statementDAO.getRefundAmtAgainsInvoice(invoiceid);
				 * accounts.setRefundAmountx(DateTimeUtils.changeFormat(refundAmt));
				 * creditAmount = creditAmount - refundAmt;
				 * 
				 * accounts.setCreditAmount(creditAmount);
				 * accounts.setCreditCharge(Double.toString(creditAmount));
				 * accounts.setCreditTotalx(DateTimeUtils.changeFormat(creditAmount));
				 * 
				 * double balance = result - creditAmount; accounts.setBalance(balance);
				 * accounts.setBalancex(DateTimeUtils.changeFormat(balance));
				 * accounts.setBalanceTotal(balancetotal+balance);
				 * balancetotal=accounts.getBalanceTotal();
				 * 
				 * double totalAmount=accounts.getTotalAmount(); double
				 * unitChargeTotal=accounts.getTotalbillamount(); double
				 * chargeDiscount=accounts.getDiscount(); double nonDiscChargeAmt =0;
				 * if(unitChargeTotal==0){ unitChargeTotal = totalAmount; }else {
				 * nonDiscChargeAmt =
				 * getNonDiscountChargeAmt(invoiceid,fromDate,toDate,userid,chargeType); }
				 * 
				 * if(r1>0){ chargeDiscount = r1; } //double prePayAmt =
				 * getPreviousPaymentAmt(invoiceid,accpayment.getId()); double prePayAmt=0;
				 * double netPayableAmt = (unitChargeTotal - chargeDiscount) - prePayAmt
				 * +nonDiscChargeAmt; double balAmt = netPayableAmt - accounts.getAmount();
				 * 
				 * accounts.setNetPayableAmt(""+netPayableAmt);
				 * accounts.setInvAmt(""+(unitChargeTotal+nonDiscChargeAmt));
				 * accounts.setDiscAmt(""+chargeDiscount); accounts.setPreAmt(""+prePayAmt);
				 * accounts.setBalAmt(""+balAmt);
				 */
				accounts.setInvoiceids(""+buffer);
				accounts.setChargename(rs.getString(13));
				accounts.setCode(code);
				list.add(accounts);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		
		return list;
		
	}

	private String getapmtcode(String mastercharge, String name) {
		String code="";
		try {
			String sql="select code from apm_appointment_type where name='"+name+"' and chargeType='"+mastercharge+"'";
			PreparedStatement pst=connection.prepareStatement(sql);
			ResultSet rs=pst.executeQuery();
			while(rs.next()) {
				code=rs.getString(1);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return code;
	}

	@Override
	public ArrayList<NotAvailableSlot> getMainIpdSittingAndFollowUpList(String fromDate, String toDate,
			String department, String patientCategory, Pagination pagination, String sittingOrFollowUp,
			String sittingName, String proceduerMasterName, String procedureName) {
		FinderDAO finderDAO = new JDBCFinderDAO(connection);
		ChargesReportDAO chargesReportDAO = new JDBCChargesReportDAO(connection);
		PreparedStatement preparedStatement = null;
		ArrayList<NotAvailableSlot> list = new ArrayList<NotAvailableSlot>();
		ArrayList<NotAvailableSlot> ipssittingAndFolloUpList=new ArrayList<NotAvailableSlot>();
		StringBuffer buffer = new StringBuffer();


		try {
			
			buffer.append("select id,dept_id from ipd_sitting_followuplist ");
			buffer.append("where date_time between '" + fromDate + "' and '" + toDate + " 59:59:59' ");
			
			if(!department.equals("")){
				buffer.append(" and ipd_sitting_followuplist.dept_id="+department+" ");
			}
			
			buffer.append("group by dept_id  ");
			String sqls = pagination.getSQLQuery(buffer.toString());
			preparedStatement = connection.prepareStatement(sqls);
			ResultSet rs = preparedStatement.executeQuery();
			
			while (rs.next()) {
				NotAvailableSlot notavailable = new NotAvailableSlot();
				notavailable.setDepartment(rs.getString(2));
                String deptname=finderDAO.getdepartementname(rs.getString(2));
                notavailable.setDepartmentname(deptname);
                
                ipssittingAndFolloUpList=chargesReportDAO.getIpdSittingAndFollowUpList(fromDate,
          			  toDate,department,patientCategory,pagination,sittingOrFollowUp,sittingName,
          			  proceduerMasterName,procedureName,rs.getString(2));
				notavailable.setSessionsittingAndFolloUpList(ipssittingAndFolloUpList);

				list.add(notavailable);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return list;
	}

	@Override
	public int getIpdSessionCount(String fromDate, String toDate, String department, String patientCategory,
			String sittingOrFollowUp, String sittingName, String proceduerMasterName, String procedureName) {
		int res =0;
		try {
			StringBuffer buffer = new StringBuffer();
			buffer.append("select count(*) from ipd_sitting_followuplist ");
			buffer.append("inner join apm_patient on apm_patient.id = ipd_sitting_followuplist.patient_id ");
			buffer.append("inner join apm_discipline on apm_discipline.id = ipd_sitting_followuplist.dept_id ");
			if(sittingOrFollowUp.equals("0")){
				buffer.append("where date_time between '"+fromDate+"' and '"+toDate+" 59:59:59' ");
			}else{
				buffer.append("where followup_date!='' and STR_TO_DATE(followup_date,'%d-%m-%Y') between '"+fromDate+"' and '"+toDate+"' ");
			}
			if(!department.equals("")){
				buffer.append(" and ipd_sitting_followuplist.dept_id="+department+" ");
			}
			PreparedStatement preparedStatement = connection.prepareStatement(buffer.toString());
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				res = rs.getInt(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return res;
	
	}

	@Override
	public ArrayList<Accounts> getIpdSmallPaymentReportList(String fromDate, String toDate, String payby,
			String howpaid, String orderby, String order, String invcategory, String userid, String selectedInvctype,
			LoginInfo loginInfo,String itype) {
		
		String tp = "Third Party";String self = "Client";
		PreparedStatement preparedStatement = null;
		ArrayList<Accounts>list = new ArrayList<Accounts>();
		StringBuffer sql = new StringBuffer();
		ClientDAO clientDAO=new JDBCClientDAO(connection);
		
			sql.append("select masterchargetype,sum(payment),itype from apm_invoice_assesments inner join apm_charges_invoice on apm_charges_invoice.id = apm_invoice_assesments.invoiced ");
			sql.append("inner join apm_charges_payment on apm_charges_payment.chargeinvoiceid=apm_charges_invoice.id ");
	
	
		
		
		if(!fromDate.equals("") && !toDate.equals("")){
			
			/*if(fromDate.equals(toDate)){
				toDate = toDate + " " + "23:59:59";
			}else{
				if(!toDate.equals("")){
					String dt = toDate;
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
					Calendar c = Calendar.getInstance();
					try {
						c.setTime(sdf.parse(dt));
						c.add(Calendar.DATE, 1);  // number of days to add
						dt = sdf.format(c.getTime());  // dt is now the new date
						toDate = dt + " " + "23:59:59";
					} catch (ParseException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			
			}*/
			if(!loginInfo.isAyushman() || !loginInfo.isParihar()) {
				toDate = toDate + " " + "23:59:59";
			}
			//toDate = toDate + " " + "23:59:59";
			
			//sql.append("select id,clientid,chargeinvoiceid,payment,paymode,date,tpid from apm_charges_payment where date between '"+fromDate+"' AND '"+toDate+"' order by id desc ") ;
			
			sql.append("where commencing between '"+fromDate+"' AND '"+toDate+"' ");
			
			
			if(payby!=null){
				if(!payby.equals("0") && !howpaid.equals("0") ){
					sql.append("and payby='"+payby+"' and paymode='"+howpaid+"' ");
				}else if(!payby.equals("0")){
					sql.append("and payby='"+payby+"' " );
				}else if(!howpaid.equals("0")){
					sql.append("and paymode='"+howpaid+"' " );
				}
			}
		}else{
			
			if(payby!=null){
				if(!payby.equals("0") && !howpaid.equals("0") ){
					sql.append("where payby='"+payby+"' and paymode='"+howpaid+"' ");
				}else if(!payby.equals("0")){
					sql.append("where payby='"+payby+"' " );
				}else if(!howpaid.equals("0")){
					sql.append("where paymode='"+howpaid+"' " );
				}
			}
			
		}
		
		if(!invcategory.equals("2")){
			
			sql.append("and apm_charges_invoice.invpstype="+invcategory+" ");
		}
		
		/*
		 * if(userid!=null){ if(!userid.equals("0")){
		 * sql.append("and apm_charges_payment.userid='"+userid+"' "); } }
		 */
		if(!itype.equals("")){
			sql.append(" and itype ='"+itype+"' ");
		}else {
			sql.append(" and itype in(15,18) ");
		}
		//sql.append("and paymode!='prepayment' order by "+orderby+" "+order+" group by group by masterchargetype");
		 sql.append("group by masterchargetype");
		try{
			
			preparedStatement = connection.prepareStatement(sql.toString());
			ResultSet rs = preparedStatement.executeQuery();
			ChargesReportDAO chargesReportDAO = new JDBCChargesReportDAO(connection);
			AccountsDAO accountsDAO= new JDBCAccountsDAO(connection);
			double totalamt=0;
			while(rs.next()){
				Accounts accounts = new Accounts();
                accounts.setMasterchargetype(rs.getString(1));
                accounts.setAmount(rs.getDouble(2));
                accounts.setItype(rs.getString(3));
                ArrayList<Accounts> ipdpaymentList=chargesReportDAO.getIpdSmallPaymentDataList(fromDate,toDate,payby,
            			 howpaid ,orderby, order, invcategory, userid,selectedInvctype,
            			loginInfo,accounts.getMasterchargetype(),accounts.getItype());
                accounts.setPaymentmode(""+ipdpaymentList.get(ipdpaymentList.size()-1).getPaymentmode());
                accounts.setIpdsmallPaymentReportListCollection(ipdpaymentList);
				
				list.add(accounts);
				
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		
		
		return list;
		
	}

	@Override
	public ArrayList<Accounts> getIpdSmallPaymentDataList(String fromDate, String toDate, String payby, String howpaid,
			String orderby, String order, String invcategory, String userid, String selectedInvctype,
			LoginInfo loginInfo,String masterchargetype,String invoicetype) {
		//For balgopal
		String tp = "Third Party";String self = "Client";
		PreparedStatement preparedStatement = null;
		ArrayList<Accounts>list = new ArrayList<Accounts>();
		StringBuffer sql = new StringBuffer();
		ClientDAO clientDAO=new JDBCClientDAO(connection);
		
		if(payby!=null){
			if(!payby.equals("0")){
				if(payby.equals(Constants.PAY_BY_CLIENT)){
					sql.append("select apm_charges_payment.id,apm_charges_payment.clientid,chargeinvoiceid,payment,paymode,apm_charges_payment.date,apm_charges_payment.tpid,payby,firstname,apm_charges_payment.userid,apm_charges_invoice.itype,apm_charges_payment.ipdno,apm_charges_payment.opdno,apm_charges_payment.invno,apm_charges_payment.cyear,paymentnote,chequeno,cancelinv,cancelNotes,concat(his_invoice_payment_sno.cyear,'/',his_invoice_payment_sno.sno),apm_charges_payment.discamt,discount,debit,disctype  ");
					sql.append("FROM apm_charges_payment ");
					sql.append("inner join apm_charges_invoice on apm_charges_invoice.id = apm_charges_payment.chargeinvoiceid ");
					sql.append("inner join apm_invoice_assesments on apm_invoice_assesments.invoiced=apm_charges_invoice.id ");
                    sql.append("inner join apm_patient on apm_patient.id = apm_charges_payment.clientid ");
					sql.append(" left join his_invoice_payment_sno on his_invoice_payment_sno.paymentid= apm_charges_payment.id ");
				}else{
					sql.append("select apm_charges_payment.id,apm_charges_payment.clientid,chargeinvoiceid,payment,paymode,apm_charges_payment.date,apm_charges_payment.tpid,payby,company_name,apm_charges_payment.userid,apm_charges_invoice.itype,apm_charges_payment.ipdno,apm_charges_payment.opdno,apm_charges_payment.invno,apm_charges_payment.cyear,paymentnote,chequeno,cancelinv,cancelNotes,concat(his_invoice_payment_sno.cyear,'/',his_invoice_payment_sno.sno),apm_charges_payment.discamt,discount,debit,disctype  ");
					sql.append("FROM apm_charges_payment ");
					sql.append("inner join apm_charges_invoice on apm_charges_invoice.id = apm_charges_payment.chargeinvoiceid ");
					sql.append("inner join apm_invoice_assesments on apm_invoice_assesments.invoiced=apm_charges_invoice.id ");
                    sql.append("inner join apm_third_party_details on apm_third_party_details.id = apm_charges_payment.tpid ");
					sql.append(" left join his_invoice_payment_sno on his_invoice_payment_sno.paymentid= apm_charges_payment.id ");
				}
			}else{
				sql.append("select apm_charges_payment.id,apm_charges_payment.clientid,chargeinvoiceid,payment,paymode,apm_charges_payment.date,apm_charges_payment.tpid,payby,apm_charges_payment.userid,apm_charges_invoice.itype,apm_charges_payment.ipdno,apm_charges_payment.opdno,apm_charges_payment.invno,apm_charges_payment.cyear,paymentnote,chequeno,cancelinv,cancelNotes,concat(his_invoice_payment_sno.cyear,'/',his_invoice_payment_sno.sno),apm_charges_payment.discamt,discount,debit,disctype  ");
				sql.append("FROM apm_charges_payment ");
				sql.append("inner join apm_charges_invoice on apm_charges_invoice.id = apm_charges_payment.chargeinvoiceid ");
				sql.append("inner join apm_invoice_assesments on apm_invoice_assesments.invoiced=apm_charges_invoice.id ");
				sql.append(" left join his_invoice_payment_sno on his_invoice_payment_sno.paymentid= apm_charges_payment.id ");
			}
		}else{
			sql.append("select apm_charges_payment.id,apm_charges_payment.clientid,chargeinvoiceid,payment,paymode,apm_charges_payment.date,apm_charges_payment.tpid,payby,apm_charges_payment.userid,apm_charges_invoice.itype,apm_charges_payment.ipdno,apm_charges_payment.opdno,apm_charges_payment.invno,apm_charges_payment.cyear,paymentnote,chequeno,cancelinv,cancelNotes,concat(his_invoice_payment_sno.cyear,'/',his_invoice_payment_sno.sno),apm_charges_payment.discamt,discount,debit,disctype ");
			sql.append("FROM apm_charges_payment ");
			sql.append("inner join apm_charges_invoice on apm_charges_invoice.id = apm_charges_payment.chargeinvoiceid ");
			sql.append("inner join apm_invoice_assesments on apm_invoice_assesments.invoiced=apm_charges_invoice.id ");
            sql.append(" left join his_invoice_payment_sno on his_invoice_payment_sno.paymentid= apm_charges_payment.id ");
		}
	
	
		
		
		if(!fromDate.equals("") && !toDate.equals("")){
			
			/*if(fromDate.equals(toDate)){
				toDate = toDate + " " + "23:59:59";
			}else{
				if(!toDate.equals("")){
					String dt = toDate;
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
					Calendar c = Calendar.getInstance();
					try {
						c.setTime(sdf.parse(dt));
						c.add(Calendar.DATE, 1);  // number of days to add
						dt = sdf.format(c.getTime());  // dt is now the new date
						toDate = dt + " " + "23:59:59";
					} catch (ParseException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			
			}*/
			if(!loginInfo.isAyushman() || !loginInfo.isParihar()) {
				toDate = toDate + " " + "23:59:59";
			}
			
			if(!masterchargetype.equals("") && !invoicetype.equals("")) {
			    sql.append("where apm_charges_payment.date between '"+fromDate+"' AND '"+toDate+"' and masterchargetype='"+masterchargetype+"' and itype ='"+invoicetype+"' ");  //masterchargetype='"+masterchargetype+"'
	
			}else {
			    sql.append("where apm_charges_payment.date between '"+fromDate+"' AND '"+toDate+"' ");  //masterchargetype='"+masterchargetype+"'
			}
			
		}else{
			
			if(payby!=null){
				if(!payby.equals("0") && !howpaid.equals("0") ){
					sql.append("where payby='"+payby+"' and paymode='"+howpaid+"' ");
				}else if(!payby.equals("0")){
					sql.append("where payby='"+payby+"' " );
				}else if(!howpaid.equals("0")){
					sql.append("where paymode='"+howpaid+"' " );
				}
			}
			
		}
		
		sql.append("and paymode!='prepayment' order by "+orderby+" "+order+" ");
		 
		try{
			double totalOfTotal=0;
			preparedStatement = connection.prepareStatement(sql.toString());
			ResultSet rs = preparedStatement.executeQuery();
			AccountsDAO accountsDAO = new JDBCAccountsDAO(connection);
			double totalAmt = 0;
			double totaldiscamt=0;
			//double discount_amount_by_per=0;
			while(rs.next()){
				Accounts accounts = new Accounts();
				accounts.setId(rs.getInt(1));
				accounts.setClientid(rs.getInt(2));
				String clientName = getClientFullName(rs.getInt(2));
				accounts.setClientName(clientName);
				accounts.setInvoiceid(rs.getInt(3));
				
				String invoicenameid=accountsDAO.getInvoiceTypeId(rs.getInt(3));
				int ipdopdseq=accountsDAO.getIpdOpdSeqNo((rs.getInt(3)));
				String invoicename=accountsDAO.getInvoiceName(invoicenameid);
				accounts.setInvoicenameid(invoicename);
				accounts.setIpdopdseq(""+ipdopdseq);
				
				if(loginInfo.isBalgopal() || loginInfo.isSeq_no_gen()){
					accounts.setIpdopdseq(accountsDAO.getFinancialSeqNoOfInvoice(rs.getString(3)));
				}
				
				String abrivationid =getClientAbrivationId(rs.getInt(2));
				accounts.setAbrivationid(abrivationid);
				
				accounts.setAmount(rs.getDouble(4));
				accounts.setFinalTotal(totalOfTotal+accounts.getAmount());
				totalOfTotal=accounts.getFinalTotal();
				accounts.setAmountx(DateTimeUtils.changeFormat(rs.getDouble(4)));
				accounts.setPaymentmode(rs.getString(5));
				accounts.setDate(DateTimeUtils.getIndianDateTimeFormat(rs.getString(6)));
				String whopay = getWhoPay(rs.getInt(3));
				accounts.setWhoPay(whopay);
				if(whopay.equals(tp)){
					String invoicee = getThirdPartyCompanyName(rs.getString(7));
					accounts.setInvoicee(invoicee);
				}
				else{
					accounts.setInvoicee(clientName);
				}
				accounts.setUserid(rs.getString(9));
				String itype=rs.getString(10);
				String ipdid=rs.getString(11);
				String opdid=rs.getString(12);
				String invstid=rs.getString(13);
				String cyear=rs.getString(14);
				
//				if(itype!=null){
//					if(itype.equals("2")){
//						type1=cyear+"/"+ipdid;
//					}else if(itype.equals("1")){
//						type1=cyear+"/"+opdid;
//					}else if(itype.equals("3")){
//						type1=cyear+"/"+invstid;
//					}else{
//						type1=String.valueOf(accounts.getId());
//					}
//				accounts.setNewsr(type1);	
//				}else{
					accounts.setNewsr(String.valueOf(accounts.getId()));	
//				}
					String paymentnote=rs.getString(15);
					String chequeno=rs.getString(16);
					if(rs.getString(5).equals("Cheque"))
					{
						accounts.setPaymentNote(chequeno);
					}else{
						accounts.setPaymentNote(paymentnote);
					}
					accounts.setCancelsts(rs.getString(17));
					accounts.setCancelNotes(DateTimeUtils.isNull(rs.getString(18)));
					String type1="";
					if(rs.getString(19)!=null){
						type1= rs.getString(19);
						accounts.setNewsr(type1);	
					}else{
						accounts.setNewsr(String.valueOf(accounts.getId()));	
					}
					accounts.setPhysical_payment_id(getPhysicalpaymentId(rs.getString(1)));
					//for ayushman
					if(loginInfo.isAyushman() || loginInfo.isMatrusevasang() || loginInfo.isBalgopal()) {
						Accounts accounts1=getServiceName(rs.getString(3));
						 //String serviceamt=getServiceAmt(rs.getString(3));
							/*
							 * String apmtType=""; String temp[] =
							 * accounts1.getAppointmentType().split(","); for (String string : temp) {
							 * if(!string.equals(" ")){ String data=string; if(apmtType.equals("")){
							 * apmtType=data; }else{ apmtType=apmtType+", "+data+"\n"; } } }
							 */
						accounts.setAppointmentType(accounts1.getAppointmentType());
						accounts.setChargename(accounts1.getDepartment());
						int totalsession=clientDAO.gettotalsessionByClientid(rs.getInt(2));
						int session=clientDAO.getsessionByClientId(rs.getInt(2));
						
						accounts.setTotalsession(totalsession);
						accounts.setSession(session);
						
						//accounts.setDiscamt(rs.getString(21));
						if(rs.getString(23).equals("1")) {
							accounts.setDiscamt(rs.getString(20));
							
						}else {
							double discount_amount_by_per=rs.getDouble(22)/100*rs.getDouble(21);
							accounts.setDiscamt(Double.toString(discount_amount_by_per));
						}
						
						 
					}
					list.add(accounts);
				
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		
		
		return list;
		
	}

	@Override
	public ArrayList<Accounts> getMainSmallPaymentList(String fromDate, String toDate, String payby, String howpaid,
			String orderby, String order, String invoicecategory, String userid, String string, LoginInfo loginInfo) {
		ArrayList<Accounts>list = new ArrayList<Accounts>();
		StringBuffer sql = new StringBuffer();
		toDate = toDate + " " + "23:59:59";
		PreparedStatement preparedStatement = null;
		try {
			sql.append("select itype from apm_charges_invoice where itype in(15,18) and date between '"+fromDate+"' AND '"+toDate+"' group by itype ");
            
			
			preparedStatement = connection.prepareStatement(sql.toString());
			ResultSet rs = preparedStatement.executeQuery();
			ChargesReportDAO chargesReportDAO = new JDBCChargesReportDAO(connection);
			AccountsDAO accountsDAO= new JDBCAccountsDAO(connection);
			
			while(rs.next()) {
			  Accounts accounts = new Accounts();
			  accounts.setItype(rs.getString(1));
              String invName=accountsDAO.getInvoiceName(rs.getString(1));
              accounts.setInvoicetype(invName);
			  ArrayList<Accounts>innerlist=chargesReportDAO.getIpdSmallPaymentReportList(fromDate, toDate, payby, howpaid, orderby, order, invoicecategory, userid, string, loginInfo,accounts.getItype());
			  accounts.setIpdsmallPaymentinnerlist(innerlist);
			  list.add(accounts);
			  
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		return list;
	}

	@Override
	public ArrayList<Accounts> getDeptlist(String fromDate, String toDate,String department) {
		ArrayList<Accounts>list = new ArrayList<Accounts>();
		StringBuffer sql = new StringBuffer();
		toDate = toDate + " " + "23:59:59";
		PreparedStatement preparedStatement = null;
		StringBuffer buffer=new StringBuffer();
		try {
			
           //sql.append("select department_opd.clientid,dept,department_opd.commencing,discipline from department_opd inner join apm_invoice_assesments on apm_invoice_assesments.commencing=department_opd.commencing inner join apm_discipline on apm_discipline.id=department_opd.dept where apm_invoice_assesments.commencing between '"+fromDate+"' AND '"+toDate+"'  ");
           sql.append("select department_opd.clientid,dept,department_opd.commencing,discipline from department_opd inner join apm_discipline on apm_discipline.id=department_opd.dept where commencing between '"+fromDate+"' AND '"+toDate+"'");
           if(!department.equals("") && !department.equals("0")) {
        	   sql.append("and dept='"+department+"' ");
           }
			sql.append("order by department_opd.id desc ");
			preparedStatement = connection.prepareStatement(sql.toString());
			ResultSet rs = preparedStatement.executeQuery();
			ChargesReportDAO chargesReportDAO = new JDBCChargesReportDAO(connection);
			AccountsDAO accountsDAO= new JDBCAccountsDAO(connection);
			double totalrev=0;
			while(rs.next()) {
			  Accounts accounts = new Accounts();
			  buffer=buffer.append(rs.getString(1)+",");
              accounts.setClientids(""+buffer);
              accounts.setDepartment(rs.getString(2));
              accounts.setCommencing(rs.getString(3));
              accounts.setDeptid(rs.getString(4));
              
              list.add(accounts);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}	
 		return list;
	}

	@Override
	public ArrayList<Accounts> getDeptPaymentReportListForNkp(String fromDate, String toDate, String payby,
			String howpaid, String orderby, String order, String invoicecategory, String userid,
			String selectedInvctype, String sortfilter, String chargeType, String condition, String tpid,
			LoginInfo loginInfo, String cliidss) {
		String tp = "Third Party";String self = "Client";
		PreparedStatement preparedStatement = null;
		ArrayList<Accounts>list = new ArrayList<Accounts>();
		StatementDAO statementDAO = new JDBCStatementDAO(connection);
		AccountsDAO accountsDAO = new JDBCAccountsDAO(connection);
		StringBuffer sql = new StringBuffer();
		StringBuffer buffer=new StringBuffer();
		
		try {
			sql.append("SELECT apm_charges_invoice.id,discount,debit,disctype,discamt,date,time,practid,apm_charges_invoice.clientid,apm_patient.fullname,abrivationid,masterchargetype,apmtType FROM apm_charges_invoice");
			sql.append(" inner join apm_patient on apm_patient.id=apm_charges_invoice.clientid ");
			//sql.append("inner join department_opd on department_opd.clientId=apm_charges_invoice.clientid ");
			sql.append(" inner join apm_invoice_assesments on apm_invoice_assesments.invoiced=apm_charges_invoice.id where apm_invoice_assesments.clientId in("+cliidss+") and apm_invoice_assesments.commencing between '"+fromDate+"' AND '"+toDate+" 23:59:59' ");
					
			if(!chargeType.equals("0")){
				sql.append("and masterchargetype='"+chargeType+"' ");
			}if(!payby.equals("0") && !payby.equals("") ){
				sql.append("and payby='"+payby+"' " );
			} /*commenting beacuase vspm multiple payer type search
				 * if(!tpid.equals("0")){ sql.append("and tpid='"+tpid+"' "); }
				 */
			if(!tpid.equals("0")){//then this code for vspm multiple payer type search
				sql.append("and tpid in ("+tpid+") ");
			}
			if(!DateTimeUtils.numberCheck(selectedInvctype).equals("0")){
				sql.append(" and itype in("+selectedInvctype+") ");
			}
			sql.append(" group by invoiced order by apm_charges_invoice.id desc");
			/*
			 * sql.
			 * append("select masterchargetype,invoiced,clientId,sum(charge * quantity),sum(quantity * unitcharge),sum(chargedisc) from apm_invoice_assesments where invoiced!=0 and "
			 * ); sql.append("commencing between '"+fromDate+"' and '"
			 * +toDate+"' group by invoiced ");
			 */
			preparedStatement = connection.prepareStatement(sql.toString());
			ResultSet rs = preparedStatement.executeQuery();
			int res =0;
			double total=0;
			double unitCharge=0;
			double discount=0;
			double balancetotal=0;
			while(rs.next()){
				double result = 0.0;
				Accounts accounts=new Accounts();
				int invoiceid=rs.getInt(2);
				Accounts accinvoice=getInvoiveData(invoiceid);
				
				 buffer=buffer.append(rs.getString(1)+",");
				 accounts.setDiscount(rs.getDouble(2));
				 accounts.setDebitAmount(rs.getDouble(3));
				 accounts.setDisctype(rs.getString(4));
				 accounts.setDiscamt(rs.getString(5));
				 accounts.setDate(rs.getString(6)+" "+rs.getString(7));
				 accounts.setPractitionerId(rs.getInt(8));
				 accounts.setClientid(rs.getInt(9));
				 accounts.setInvoiceid(rs.getInt(1));
				 accounts.setClientName(rs.getString(10));
				 accounts.setAbrivationid(rs.getString(11));
				 accounts.setMasterchargetype(rs.getString(12));
				 String code=getapmtcode(rs.getString(12),rs.getString(13));
				/*
				 * double ttotal=0; ttotal = ttotal + rs.getDouble(4); unitCharge = unitCharge +
				 * rs.getDouble(5); discount = discount + rs.getDouble(6);
				 * 
				 * accounts.setTotalAmount(ttotal); accounts.setTotalbillamount(unitCharge);
				 * accounts.setDiscount(discount);
				 */
				
				/*
				 * accounts.setInvoicedate(accinvoice.getInvoicedate());
				 * accounts.setMasterchargetype(rs.getString(1)); String
				 * invoicename=accountsDAO.getInvoiceName(""+accinvoice.getItype());
				 */
				
				//Accounts accpayment=getPaymentData(invoiceid);
				
				/*
				 * Accounts accpayment=new Accounts(); accounts.setDate(accpayment.getDate());
				 * accounts.setPaymentmode(accpayment.getPaymentmode());
				 * accounts.setAmount(accpayment.getAmount());
				 * 
				 * String locationName="";
				 * 
				 * if(loginInfo.isPhysio()) { int
				 * locationid=getLocationIdByclientid(accpayment.getClientid());
				 * locationName=getDepartmentnameByid(locationid); }else { locationName
				 * =getChargeDepartmentName(accinvoice.getPractitionerId()); }
				 * 
				 * accounts.setLocationName(locationName); if(accpayment.getTpid()>0){ String
				 * payer = getThirdPartyCompanyName(""+accpayment.getTpid());
				 * accounts.setPayer(payer); }
				 * 
				 * String clientName = getClientFullName(rs.getInt(3)); String abrivationid
				 * =getClientAbrivationId(rs.getInt(3)); accounts.setAbrivationid(abrivationid);
				 * accounts.setClientName(clientName);
				 * 
				 * accounts.setInvoicenameid(invoicename);
				 * accounts.setBghseqId(accinvoice.getFseqno());
				 */
				
				
				/*
				 * total =Double.parseDouble(accinvoice.getDebitAmountx()); int disctype =
				 * Integer.parseInt(accinvoice.getDisctype()); double discamt
				 * =Double.parseDouble(accinvoice.getDiscamt()); discount =
				 * accinvoice.getDiscount(); double r1 = (total*discount)/100; if(disctype==1){
				 * r1 = discamt; } accounts.setDiscAmmount(DateTimeUtils.changeFormat(r1));
				 * accounts.setDebitAmountx(DateTimeUtils.changeFormat(total)); total =
				 * total-r1; result = result + total;
				 */
				
				/*
				 * //double creditAmount = getCreditAmount(accinvoice.getId()); double
				 * creditAmount=0; //if refund against invoice double refundAmt =
				 * statementDAO.getRefundAmtAgainsInvoice(invoiceid);
				 * accounts.setRefundAmountx(DateTimeUtils.changeFormat(refundAmt));
				 * creditAmount = creditAmount - refundAmt;
				 * 
				 * accounts.setCreditAmount(creditAmount);
				 * accounts.setCreditCharge(Double.toString(creditAmount));
				 * accounts.setCreditTotalx(DateTimeUtils.changeFormat(creditAmount));
				 * 
				 * double balance = result - creditAmount; accounts.setBalance(balance);
				 * accounts.setBalancex(DateTimeUtils.changeFormat(balance));
				 * accounts.setBalanceTotal(balancetotal+balance);
				 * balancetotal=accounts.getBalanceTotal();
				 * 
				 * double totalAmount=accounts.getTotalAmount(); double
				 * unitChargeTotal=accounts.getTotalbillamount(); double
				 * chargeDiscount=accounts.getDiscount(); double nonDiscChargeAmt =0;
				 * if(unitChargeTotal==0){ unitChargeTotal = totalAmount; }else {
				 * nonDiscChargeAmt =
				 * getNonDiscountChargeAmt(invoiceid,fromDate,toDate,userid,chargeType); }
				 * 
				 * if(r1>0){ chargeDiscount = r1; } //double prePayAmt =
				 * getPreviousPaymentAmt(invoiceid,accpayment.getId()); double prePayAmt=0;
				 * double netPayableAmt = (unitChargeTotal - chargeDiscount) - prePayAmt
				 * +nonDiscChargeAmt; double balAmt = netPayableAmt - accounts.getAmount();
				 * 
				 * accounts.setNetPayableAmt(""+netPayableAmt);
				 * accounts.setInvAmt(""+(unitChargeTotal+nonDiscChargeAmt));
				 * accounts.setDiscAmt(""+chargeDiscount); accounts.setPreAmt(""+prePayAmt);
				 * accounts.setBalAmt(""+balAmt);
				 */
				accounts.setInvoiceids(""+buffer);
				accounts.setChargename(rs.getString(13));
				accounts.setCode(code);
				list.add(accounts);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		
		return list;
		
	}

	@Override
	public ArrayList<Accounts> getchargesPaymentlistbyinvidforNkpphysio(String inv1, String fromDate, String toDate,
			String outsourceid, String selectedInvctype, String chargeType, String condition, String tpid, String payby,
			String howpaid, LoginInfo loginInfo) {
		PreparedStatement preparedStatement=null;
		AccountsDAO accountsDAO = new JDBCAccountsDAO(connection);
		StatementDAO statementDAO = new JDBCStatementDAO(connection);
		ArrayList<Accounts> list=new ArrayList<Accounts>();
		StringBuffer buffer=new StringBuffer();
		String tp = "Third Party";
		try {
			/*
			 * buffer.
			 * append("select apm_charges_payment.date, apm_charges_invoice.debit,apm_charges_invoice.discamt,apm_charges_invoice.disctype,payment,apm_charges_invoice.date,apm_charges_invoice.time,apm_charges_invoice.practid,apm_charges_invoice.clientid,apm_charges_invoice.id,fullname,abrivationid,apm_charges_invoice.payby,apm_charges_payment.tpid,paymode,outsource from apm_charges_payment "
			 * ); buffer.
			 * append("inner join apm_charges_invoice on apm_charges_invoice.id=apm_charges_payment.chargeinvoiceid "
			 * ); buffer.
			 * append("inner join apm_patient on apm_patient.id=apm_charges_payment.clientid "
			 * ); buffer.
			 * append("inner join apm_invoice_assesments on apm_invoice_assesments.clientId=apm_charges_payment.clientid "
			 * ); buffer.append("where apm_invoice_assesments.commencing between '"
			 * +fromDate+"' and '"+toDate+"' and apm_charges_payment.chargeinvoiceid in("
			 * +inv1+")");
			 */
			 buffer.append("select apm_charges_payment.date, apm_charges_invoice.debit,apm_charges_invoice.discamt,apm_charges_invoice.disctype,payment,apm_charges_invoice.date,apm_charges_invoice.time,apm_charges_invoice.practid,apm_charges_invoice.clientid,apm_charges_invoice.id,fullname,abrivationid,apm_charges_invoice.payby,apm_charges_payment.tpid,paymode,original_pay,cancelinv from apm_charges_payment ");
			 buffer.append("inner join apm_charges_invoice on apm_charges_invoice.id=apm_charges_payment.chargeinvoiceid ");
			 buffer.append("inner join apm_patient on apm_patient.id=apm_charges_payment.clientid ");
			 //buffer.append("inner join apm_invoice_assesments on apm_invoice_assesments.clientId=apm_charges_payment.clientid ");
			 buffer.append("where apm_charges_payment.chargeinvoiceid in("+inv1+") and apm_charges_invoice.date between '"+fromDate+"' AND '"+toDate+" 23:59:59' ");
			 if(!outsourceid.equals("")) {
				 buffer.append(" and outsource='"+outsourceid+"'");
			 }
				/*
				 * if(payby!=null){ if(!payby.equals("0") && !howpaid.equals("0") ){
				 * buffer.append("and payby='"+payby+"' and paymode='"+howpaid+"' "); }else
				 * if(!payby.equals("0")){ buffer.append("and payby='"+payby+"' " ); }else
				 * if(!howpaid.equals("0")){ buffer.append("and paymode='"+howpaid+"' " ); } }
				 * 
				 * if(!DateTimeUtils.numberCheck(selectedInvctype).equals("0")){
				 * buffer.append(" and itype in("+selectedInvctype+") "); }
				 * if(!condition.equals("0")){
				 * buffer.append("and apm_user.discription='"+condition+"' "); }
				 * if(!tpid.equals("0")){
				 * buffer.append("and apm_charges_payment.tpid='"+tpid+"' "); }
				 */
				
				buffer.append("and paymode!='prepayment' group by  apm_charges_payment.chargeinvoiceid order by apm_charges_payment.id desc");
			 preparedStatement=connection.prepareStatement(buffer.toString());
			 ResultSet rs=preparedStatement.executeQuery();
			 while(rs.next()) {
				 Accounts accounts=new Accounts();
				 accounts.setPaymentdate(rs.getString(1));
				 accounts.setDebitAmount(rs.getDouble(2));
				 accounts.setDiscamt(rs.getString(3)); 
				 Double netpayamt=(accounts.getDebitAmount()-Double.parseDouble(accounts.getDiscamt()));
				 accounts.setPayAmount(rs.getDouble(5));
				 Double balance=netpayamt-accounts.getPayAmount();
				 accounts.setNetPayableAmt(""+netpayamt);
				 accounts.setBalAmt(""+balance);
				 accounts.setDisctype(rs.getString(4));
				 accounts.setDate(rs.getString(6)+" "+rs.getString(7));
				 accounts.setPractitionerId(rs.getInt(8));
				 accounts.setClientid(rs.getInt(9));
				 accounts.setInvoiceid(rs.getInt(10));
				 accounts.setClientName(rs.getString(11));
				 accounts.setAbrivationid(rs.getString(12));
				 accounts.setPayby(rs.getString(13));
				 String invoicenameid=accountsDAO.getInvoiceTypeId(rs.getInt(10));
				 String invoicename=accountsDAO.getInvoiceName(invoicenameid);
					accounts.setInvoicenameid(invoicename);
				 String whopay = getWhoPay(rs.getInt(10));
					accounts.setWhoPay(whopay);
					if(whopay.equals(tp)){
						String invoicee = getThirdPartyCompanyName(rs.getString(14));
						accounts.setInvoicee(invoicee+"(Third Party)");
					}
					else{
						accounts.setInvoicee("Self");
					}
				 String locationName="";	
			
				 String deptid=getDepartmentidforlmh(rs.getString(1),rs.getString(10));	   //change 2 parameter to 9 to 10 
				 locationName=getDepartmentnameforlmh(deptid);
				 String thirdpartyname = getThirdPartyCompanyName(rs.getString(14));
				 accounts.setThirdParty(thirdpartyname);
				 //locationName = getChargeDepartmentName(rs.getInt(8));
				
				//String masterchargetype=getmasterchargetypebyinvid(rs.getInt(10));
				//accounts.setMasterchargetype(masterchargetype);
				double refundAmt = statementDAO.getRefundAmtAgainsInvoice(rs.getInt(10));
				accounts.setRefundAmountx(DateTimeUtils.changeFormat(refundAmt));
				accounts.setLocationName(deptid);
				accounts.setPaymentmode(rs.getString(15));
				if(rs.getInt(17)==1) {
				accounts.setOriginal_pay(rs.getDouble(16));
				}
				/*
				 * String outsource=getOutSourcenamebyid(rs.getString(16));
				 * accounts.setOutsource(outsource);
				 */
			    list.add(accounts);
			 }
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public double getNetAdvance(String fromDate, String toDate) {
		Double netadvance=0.0;
		try {
			String sql="select sum(payment) from apm_charges_payment where date between '"+fromDate+"' and '"+toDate+" 23:59:59' and paymode='prepayment' ";
			PreparedStatement pst=connection.prepareStatement(sql);
			ResultSet rs=pst.executeQuery();
			while(rs.next()) {
				netadvance=rs.getDouble(1);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return netadvance;
		
	}

	@Override
	public ArrayList<Accounts> getInvestigationDetailedRevenueRpt(String fromDate, String toDate,
			String type, String string, String itype1, String search, LoginInfo loginInfo) {
		PreparedStatement preparedStatement = null;
		String tp = "Third Party";String self = "Client";
		ArrayList<Accounts>list = new ArrayList<Accounts>();
		StringBuffer sql = new StringBuffer();
		ChargesReportDAO chargesReportDAO = new JDBCChargesReportDAO(connection);
		ClientDAO clientDAO=new JDBCClientDAO(connection);
		
		try {
			
			sql.append("select apm_charges_payment.id,apm_charges_payment.clientid,chargeinvoiceid,payment,paymode,apm_charges_payment.date,apm_charges_payment.tpid,payby,apm_charges_payment.userid,apm_charges_invoice.itype,apm_charges_payment.ipdno,apm_charges_payment.opdno,apm_charges_payment.invno,apm_charges_payment.cyear,paymentnote,chequeno,cancelinv,cancelNotes,concat(his_invoice_payment_sno.cyear,'/',his_invoice_payment_sno.sno),apm_charges_invoice.discamt,discount,debit,disctype  ");  //change spm_charges_payment to spm_charges_invoice
			sql.append("FROM apm_charges_payment ");
			sql.append("inner join apm_charges_invoice on apm_charges_invoice.id = apm_charges_payment.chargeinvoiceid ");
			sql.append(" left join his_invoice_payment_sno on his_invoice_payment_sno.paymentid= apm_charges_payment.id ");
			sql.append("where apm_charges_payment.date between '"+fromDate+"' AND '"+toDate+" 59:59:59' ");
			sql.append(" and itype= 3 and paymode!='prepayment' ");
			
			
			double totalOfTotal=0;
			preparedStatement = connection.prepareStatement(sql.toString());
			ResultSet rs = preparedStatement.executeQuery();
			AccountsDAO accountsDAO = new JDBCAccountsDAO(connection);
			double totalAmt = 0;
			double totaldiscamt=0;
			//double discount_amount_by_per=0;
			while(rs.next()){
				Accounts accounts = new Accounts();
				accounts.setId(rs.getInt(1));
				accounts.setClientid(rs.getInt(2));
				String clientName = getClientFullName(rs.getInt(2));
				accounts.setClientName(clientName);
				accounts.setInvoiceid(rs.getInt(3));
				
				String invoicenameid=accountsDAO.getInvoiceTypeId(rs.getInt(3));
				int ipdopdseq=accountsDAO.getIpdOpdSeqNo((rs.getInt(3)));
				String invoicename=accountsDAO.getInvoiceName(invoicenameid);
				accounts.setInvoicenameid(invoicename);
				accounts.setIpdopdseq(""+ipdopdseq);
				
				if(loginInfo.isBalgopal() || loginInfo.isSeq_no_gen()){
					accounts.setIpdopdseq(accountsDAO.getFinancialSeqNoOfInvoice(rs.getString(3)));
				}
				
				String abrivationid =getClientAbrivationId(rs.getInt(2));
				accounts.setAbrivationid(abrivationid);
				
				//accounts.setAmount(rs.getDouble(4));
				if(!rs.getString(10).equals("15") && !rs.getString(10).equals("18")) {
					//for physio ipd
					accounts.setAmount(rs.getDouble(4));
					accounts.setFinalTotal(totalOfTotal+accounts.getAmount());
				
				}
				//accounts.setFinalTotal(totalOfTotal+accounts.getAmount());
				totalOfTotal=accounts.getFinalTotal();
				accounts.setAmountx(DateTimeUtils.changeFormat(rs.getDouble(4)));
				accounts.setPaymentmode(rs.getString(5));
				accounts.setDate(DateTimeUtils.getIndianDateTimeFormat(rs.getString(6)));
				String whopay = getWhoPay(rs.getInt(3));
				accounts.setWhoPay(whopay);
				if(whopay.equals(tp)){
					String invoicee = getThirdPartyCompanyName(rs.getString(7));
					accounts.setInvoicee(invoicee);
				}
				else{
					accounts.setInvoicee(clientName);
				}
				accounts.setUserid(rs.getString(9));
				String itype=rs.getString(10);
				String ipdid=rs.getString(11);
				String opdid=rs.getString(12);
				String invstid=rs.getString(13);
				String cyear=rs.getString(14);
				
//				if(itype!=null){
//					if(itype.equals("2")){
//						type1=cyear+"/"+ipdid;
//					}else if(itype.equals("1")){
//						type1=cyear+"/"+opdid;
//					}else if(itype.equals("3")){
//						type1=cyear+"/"+invstid;
//					}else{
//						type1=String.valueOf(accounts.getId());
//					}
//				accounts.setNewsr(type1);	
//				}else{
					accounts.setNewsr(String.valueOf(accounts.getId()));	
//				}
					String paymentnote=rs.getString(15);
					String chequeno=rs.getString(16);
					if(rs.getString(5).equals("Cheque"))
					{
						accounts.setPaymentNote(chequeno);
					}else{
						accounts.setPaymentNote(paymentnote);
					}
					accounts.setCancelsts(rs.getString(17));
					accounts.setCancelNotes(DateTimeUtils.isNull(rs.getString(18)));
					String type1="";
					if(rs.getString(19)!=null){
						type1= rs.getString(19);
						accounts.setNewsr(type1);	
					}else{
						accounts.setNewsr(String.valueOf(accounts.getId()));	
					}
					accounts.setPhysical_payment_id(getPhysicalpaymentId(rs.getString(1)));
					//for ayushman
					if(loginInfo.isAyushman() || loginInfo.isMatrusevasang() || loginInfo.isBalgopal()) {
						Accounts accounts1=getServiceName(rs.getString(3));
						 //String serviceamt=getServiceAmt(rs.getString(3));
							/*
							 * String apmtType=""; String temp[] =
							 * accounts1.getAppointmentType().split(","); for (String string : temp) {
							 * if(!string.equals(" ")){ String data=string; if(apmtType.equals("")){
							 * apmtType=data; }else{ apmtType=apmtType+", "+data+"\n"; } } }
							 */
						accounts.setAppointmentType(accounts1.getAppointmentType());
						accounts.setChargename(accounts1.getDepartment());
						int totalsession=clientDAO.gettotalsessionByClientid(rs.getInt(2));
						int session=clientDAO.getsessionByClientId(rs.getInt(2));
						
						accounts.setTotalsession(totalsession);
						accounts.setSession(session);
						
						//accounts.setDiscamt(rs.getString(21));
						if(rs.getString(23).equals("1")) {
							accounts.setDiscamt(rs.getString(20));
							
						}else {
							double discount_amount_by_per=(rs.getDouble(22)*rs.getDouble(21))/100;
							accounts.setDiscamt(Double.toString(discount_amount_by_per));
						}
						double totalpayment=gettotalpayment(rs.getString(3));
						double balanceamt=rs.getDouble(22) - totalpayment;
						accounts.setBalance(balanceamt);
						/*
						 * if(!rs.getString(21).equals("0")) {
						 * discount_amount_by_per=rs.getDouble(22)/100*rs.getDouble(21);
						 * accounts.setDiscountbyrs(discount_amount_by_per); }
						 */
						 
					}
					list.add(accounts);
				
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
}
