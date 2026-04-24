package com.apm.ThirdParties.eu.blogic.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import com.apm.Accounts.eu.bi.AccountsDAO;
import com.apm.Accounts.eu.bi.StatementDAO;
import com.apm.Accounts.eu.blogic.jdbc.JDBCAccountsDAO;
import com.apm.Accounts.eu.blogic.jdbc.JDBCStatementDAO;
import com.apm.Accounts.eu.entity.Accounts;
import com.apm.DiaryManagement.eu.entity.Client;
import com.apm.Report.eu.bi.SummaryReportDAO;
import com.apm.Report.eu.blogic.jdbc.JDBCSummaryReportDAO;
import com.apm.ThirdParties.eu.bi.OutstandingReportDAO;
import com.apm.ThirdParties.eu.bi.ThirdPartyDAO;
import com.apm.ThirdParties.eu.entity.OutstandingReport;
import com.apm.ThirdParties.eu.entity.ThirdParty;
import com.apm.common.eu.blogic.jdbc.JDBCBaseDAO;
import com.apm.common.utils.DateTimeUtils;
import com.apm.common.utils.Pagination;
import com.apm.main.common.constants.Constants;

public class JDBCOutstandingReportDAO extends JDBCBaseDAO implements OutstandingReportDAO {
	
	public JDBCOutstandingReportDAO(Connection connection){
			
			this.connection = connection;
		}

	public ArrayList<OutstandingReport> getOutStandingReportList(int clinicId,Pagination pagination) {
		PreparedStatement preparedStatement = null;
		ArrayList<OutstandingReport>list = new ArrayList<OutstandingReport>();
		String sql = "select id,company_name,acc_warning_limit,outInvoiceLimit,telephone,company_email from apm_third_party_details";
		sql = pagination.getSQLQuery(sql);
		try{
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()){
				OutstandingReport outstandingReport = new OutstandingReport();
				outstandingReport.setThirdPartyId(rs.getInt(1));
				outstandingReport.setThirdPartyName(rs.getString(2));
				outstandingReport.setCreditWarningLimit(rs.getDouble(3));
				outstandingReport.setOutstandingInvoiceLimit(rs.getDouble(4));
				outstandingReport.setThirdPartyContactno(rs.getString(5));
				outstandingReport.setThirdPartEmail(rs.getString(6));
				double creditTotal = getCredit(rs.getString(1)); 
				
				outstandingReport.setPaidAmount(creditTotal);
				double totalAmout = getTotalAmount(rs.getInt(1));
				double unpaidAmount = totalAmout - creditTotal;
				outstandingReport.setUnpaidAmout(unpaidAmount);
				String note = getNote(rs.getInt(1));
				outstandingReport.setNotes(note);
				list.add(outstandingReport);
				
				
			}
			
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		return list;
	}

	private String getNote(int id) {
		String notes = "";
		PreparedStatement preparedStatement = null;
		
		String sql = "select max(notes) from apm_thirdparty_outstanding_commrecord where thirdparty_id = "+id+"";
		try{
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()){
				
				
				notes = rs.getString(1);
				}
			
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		return notes;
	}

	private double getTotalAmount(int id) {
		double totalAmout = 0.0;
		PreparedStatement preparedStatement = null;
		
		String sql = "select sum(debit) from apm_charges_invoice where payby = 'Third Party' and tpid = "+id+"";
		try{
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()){
				
				
				totalAmout = rs.getDouble(1);
				}
			
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		return totalAmout;
	}

	private double getCredit(String id) {
		double creditTotal = 0.0;
		PreparedStatement preparedStatement = null;
		
		String sql = "select sum(payment) from apm_charges_payment where tpid = "+id+"";
		try{
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()){
				
				
				creditTotal = rs.getDouble(1);
				}
			
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		return creditTotal;
	}

	public ArrayList<OutstandingReport> getOutStandingReportActionList(int id,
			String id2,Pagination pagination) {
		PreparedStatement preparedStatement = null;
		ArrayList<OutstandingReport>list = new ArrayList<OutstandingReport>();
		String sql = "select id,type,date,time,thirdparty_id,thirdparty_company,thirdParty_email,thirdParty_contact_no,notes,clinicId from apm_thirdparty_outstanding_commrecord where thirdparty_id = "+id2+" ORDER BY id DESC";
		sql = pagination.getSQLQuery(sql);
		try{
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()){
				OutstandingReport outstandingReport = new OutstandingReport();
				outstandingReport.setId(rs.getInt(1));
				outstandingReport.setType(rs.getString(2));
				outstandingReport.setDate(rs.getString(3));
				outstandingReport.setTime(rs.getString(4));
				outstandingReport.setThirdPartyId(rs.getInt(5));
				outstandingReport.setThirdPartyName(rs.getString(6));
				outstandingReport.setThirdPartEmail(rs.getString(7));
				outstandingReport.setThirdPartyContactno(rs.getString(8));
				outstandingReport.setNotes(rs.getString(9));
				outstandingReport.setClinicId(rs.getInt(10));
				list.add(outstandingReport);
				
				
			}
			
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		return list;
	}

	public int saveEmailSendDetails(OutstandingReport outstandingReport) {
		int result = 0;
		PreparedStatement pstm = null;
		String sql = "insert into apm_thirdparty_outstanding_commrecord(type,date,time,thirdparty_id,thirdparty_company,thirdParty_email,notes,clinicId,ccEmail) values (?,?,?,?,?,?,?,?,?)";
		try {
			pstm = connection.prepareStatement(sql);
			pstm.setString(1, "Email");
			pstm.setString(2, outstandingReport.getDate());
			pstm.setString(3, outstandingReport.getTime());
			pstm.setInt(4, outstandingReport.getThirdPartyId());
			pstm.setString(5, outstandingReport.getThirdPartyName());
			pstm.setString(6, outstandingReport.getThirdPartEmail());
			pstm.setString(7, outstandingReport.getNotes());
			pstm.setInt(8, outstandingReport.getClinicId());
			pstm.setString(9, outstandingReport.getCcEmail());
			
			result = pstm.executeUpdate();
			
		}catch (Exception e) {
			
			e.printStackTrace();
			// TODO: handle exception
		}
		
		return result;
	}

	public int saveSmsSendDetails(OutstandingReport outstandingReport) {
		int result = 0;
		PreparedStatement pstm = null;
		String sql = "insert into apm_thirdparty_outstanding_commrecord(type,date,time,thirdparty_id,thirdparty_company,thirdParty_contact_no,notes,clinicId) values (?,?,?,?,?,?,?,?)";
		try {
			pstm = connection.prepareStatement(sql);
			pstm.setString(1, "SMS");
			pstm.setString(2, outstandingReport.getDate());
			pstm.setString(3, outstandingReport.getTime());
			pstm.setInt(4, outstandingReport.getThirdPartyId());
			pstm.setString(5, outstandingReport.getThirdPartyName());
			pstm.setString(6, outstandingReport.getThirdPartyContactno());
			pstm.setString(7, outstandingReport.getNotes());
			pstm.setInt(8, outstandingReport.getClinicId());
			
			result = pstm.executeUpdate();
			
		}catch (Exception e) {
			
			e.printStackTrace();
			// TODO: handle exception
		}
		
		return result;
	}

	public int saveCallRecordDetails(OutstandingReport outstandingReport) {
		int result = 0;
		PreparedStatement pstm = null;
		String sql = "insert into apm_thirdparty_outstanding_commrecord(type,date,time,thirdparty_id,thirdparty_company,thirdParty_contact_no,notes,clinicId) values (?,?,?,?,?,?,?,?)";
		try {
			pstm = connection.prepareStatement(sql);
			pstm.setString(1, "Call");
			pstm.setString(2, outstandingReport.getDate());
			pstm.setString(3, outstandingReport.getTime());
			pstm.setInt(4, outstandingReport.getThirdPartyId());
			pstm.setString(5, outstandingReport.getThirdPartyName());
			pstm.setString(6, outstandingReport.getThirdPartyContactno());
			pstm.setString(7, outstandingReport.getNotes());
			pstm.setInt(8, outstandingReport.getClinicId());
			
			result = pstm.executeUpdate();
			
		}catch (Exception e) {
			
			e.printStackTrace();
			// TODO: handle exception
		}
		
		return result;
	}

	public int getTotalOutStandingCount(int id) {
		PreparedStatement preparedStatement = null;
		int result = 0;
		
		String sql = "select count(*) from apm_third_party_details";

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

	public int getTotalOutStandingActionCount(int id, String id2) {
		PreparedStatement preparedStatement = null;
		StatementDAO statementDAO = new JDBCStatementDAO(connection);
		int result = 0;
		
		String sql = "select count(*) from apm_thirdparty_outstanding_commrecord where thirdparty_id = "+id2+" ";

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

	
	public ArrayList<OutstandingReport> getOutStandingReportOfClientList(int id,
			Pagination pagination, String payby,String clientid,String fromdate,String todate) {
		PreparedStatement preparedStatement = null;
		StatementDAO statementDAO = new JDBCStatementDAO(connection);
		ArrayList<OutstandingReport>list = new ArrayList<OutstandingReport>();
		//String sql = "select id,title,firstname,surname,mobno,email from apm_patient";
		
		todate = todate + " 23:59:59";
		
		StringBuffer sql = new StringBuffer();
		//opd query
		/*sql.append("select apm_patient.id,title,firstname,surname,mobno,email, ");
		sql.append("sum(debit),sum(discount),apm_charges_invoice.id,sum(discamt) ");
		sql.append("from apm_patient inner join apm_charges_invoice on apm_patient.id = apm_charges_invoice.clientid ");
		sql.append("where payby='Client' and apm_patient.lastmodified between '"+fromdate+"' and '"+todate+"' ");*/
		
		sql.append("select apm_patient.id,title,firstname,surname,mobno,email, sum(debit),sum(discount),apm_charges_invoice.id,sum(discamt) ");
		sql.append("from apm_patient inner join apm_charges_invoice on apm_patient.id = apm_charges_invoice.clientid ");
		sql.append("inner join ipd_addmission_form on  ipd_addmission_form.clientid = apm_charges_invoice.clientid ");
		sql.append("where payby='Client' and bedid!=0 ");
		sql.append("and admissiondsate between '"+fromdate+"' and '"+todate+"' ");
		
		if(clientid!=null){
			if(!clientid.equals("")){
				sql.append("and apm_patient.id="+clientid+" ");
			}
		}
		
		sql.append("group by apm_charges_invoice.clientid  order by firstname ");
			
		
		try{
			preparedStatement = connection.prepareStatement(sql.toString());
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()){
				OutstandingReport outstandingReport = new OutstandingReport();
				
				//	outstandingReport = getDebitAmountOfClient(rs.getString(1),payby);
					outstandingReport.setThirdPartyId(rs.getInt(1));
					outstandingReport.setThirdPartyName(rs.getString(2) + " " +rs.getString(3) + " "+rs.getString(4)+" "+ "(Self)");
					outstandingReport.setCreditWarningLimit(outstandingReport.getBalance());
					outstandingReport.setOutstandingInvoiceLimit(outstandingReport.getBalance());
					outstandingReport.setThirdPartyContactno(rs.getString(5));
					outstandingReport.setThirdPartEmail(rs.getString(6));
					
					//calulate debit with discount
					double total = rs.getDouble(7);
					double discount = rs.getDouble(8);
					double r1 = (total*discount)/100;
					total = total - r1;
					
					//minus discount amt
					double discamt = rs.getDouble(10);
					r1 = discamt;
					total = total - r1;
					
					
					double credit = getSumOfClientCreditAmountClient(rs.getString(1));
					double refundAmt1 = statementDAO.getSumOfReundAmt(rs.getString(1));
					credit =  credit - refundAmt1;
					double balance = total - credit;
						
					
					
					outstandingReport.setPaidAmount(credit);
					outstandingReport.setUnpaidAmout(balance);
					outstandingReport.setDebit(total);
					
					
					
					String note = getNote(rs.getInt(1));
					outstandingReport.setNotes(note);
					outstandingReport.setPayby("Client");
					
					//Decimal Account
					outstandingReport.setCreditWarningLimitx(outstandingReport.getBalancex());
					outstandingReport.setOutstandingInvoiceLimitx(outstandingReport.getBalancex());
					
					outstandingReport.setPaidAmountx(DateTimeUtils.changeFormat(credit));
					outstandingReport.setUnpaidAmoutx(DateTimeUtils.changeFormat(balance));
					outstandingReport.setDebitx(DateTimeUtils.changeFormat(total));
					
					
					String type = "ipd";
					int whopay = 0;
					SummaryReportDAO summaryReportDAO = new JDBCSummaryReportDAO(connection);
					double selfcharge = summaryReportDAO.getSelfOpdCharge(rs.getString(1),fromdate,todate,type,whopay);
					outstandingReport.setSelfcharge(DateTimeUtils.changeFormat(selfcharge));
					
					double advance = summaryReportDAO.getAdvanceAmount(fromdate,todate,rs.getString(1));
					outstandingReport.setAdvance(DateTimeUtils.changeFormat(advance));
					
					outstandingReport.setRefundAmt1(DateTimeUtils.changeFormat(refundAmt1));
					
					double advcash = getSumOfClientCreditAmountClient(rs.getString(1)) + advance;
					double selefbalance = 0;
					double selfcredit = 0;
					if(advcash>selfcharge){
						selfcredit =   advcash - selfcharge;
						selefbalance =   0;
					}else{
						selefbalance =   selfcharge - advcash;
					}
					
					outstandingReport.setSelfcredit(DateTimeUtils.changeFormat(selfcredit));
					
					if(total!=credit){
						
						ArrayList<Accounts>clientInvoiceList = getClientInvoiceList(rs.getInt(1));
						outstandingReport.setClientInvoiceList(clientInvoiceList);
						
						list.add(outstandingReport);
					}
			}
		
			}catch (Exception e) {
				e.printStackTrace();
			}
			return list;
		
	}
	
	
	
	private ArrayList<Accounts> getClientInvoiceList(int clientid) {
		PreparedStatement preparedStatement = null;
		ArrayList<Accounts>list = new ArrayList<Accounts>();
		String sql = "select id,debit,date,discount,disctype,discamt from apm_charges_invoice where clientid = "+clientid+" and payby = 'Client' ";
		
		try{
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()){
				Accounts accounts = new Accounts();
				accounts.setInvoiceid(rs.getInt(1));
				
				double debit = rs.getDouble(2);
				accounts.setCommencing(DateTimeUtils.getCommencingDate1(rs.getString(3)));
				
				double credit = getPaidAmountTotal(rs.getString(1));
				
				double total = rs.getDouble(2);
				double discount = rs.getDouble(4);
				double r1 = (total*discount)/100;
				
				int disctype1 = rs.getInt(5);
				double discamt = rs.getDouble(6);
				if(disctype1==1){
					r1 = discamt;
				}
				
				total = total-r1;
				
				double balance = total - credit;
				
				accounts.setDebitAmountx(DateTimeUtils.changeFormat(total));
				accounts.setCreditTotalx(DateTimeUtils.changeFormat(credit));
				accounts.setBalancex(DateTimeUtils.changeFormat(balance));
				
				
				if(total!=credit){
					list.add(accounts);
				}
				
				
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}

		return list;
	}
	
	
private double getSumOfClientCreditAmountClient(String clientid){
		
		PreparedStatement preparedStatement = null;
		double result = 0;
		//String sql = "select sum(payment) from apm_charges_payment where chargeinvoiceid = "+invoiceid+" ";
		StringBuffer sql = new StringBuffer();
		sql.append("select sum(payment) from apm_charges_payment inner join ");
		sql.append("apm_charges_invoice on apm_charges_payment.chargeinvoiceid = apm_charges_invoice.id ");
		sql.append("where payby='Client' and apm_charges_invoice.clientid = "+clientid+" ");
		sql.append("group by apm_charges_invoice.clientid ");
		
		try{
			
			preparedStatement = connection.prepareStatement(sql.toString());
			ResultSet rs = preparedStatement.executeQuery();
			
			if(rs.next()){
				result = rs.getDouble(1);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return result;
	}

	

	private double getSumOfCreditAmountClient(String clientid){
		
		PreparedStatement preparedStatement = null;
		double result = 0;
		//String sql = "select sum(payment) from apm_charges_payment where chargeinvoiceid = "+invoiceid+" ";
		StringBuffer sql = new StringBuffer();
		sql.append("select sum(payment) from apm_charges_payment inner join ");
		sql.append("apm_charges_invoice on apm_charges_payment.chargeinvoiceid = apm_charges_invoice.id ");
		sql.append("where payby='Third Party' and apm_charges_invoice.tpid = "+clientid+" ");
		sql.append("group by apm_charges_invoice.clientid ");
		
		try{
			
			preparedStatement = connection.prepareStatement(sql.toString());
			ResultSet rs = preparedStatement.executeQuery();
			
			if(rs.next()){
				result = rs.getDouble(1);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return result;
	}


	private OutstandingReport getDebitAmountOfClient(String id, String payby) {
		PreparedStatement preparedStatement = null;
		DateTimeUtils dateTimeUtils = new DateTimeUtils();
		OutstandingReport outstandingReport = new OutstandingReport();
		String sql = "SELECT sum(debit) FROM apm_charges_invoice where payby = '"+payby+"' and clientid = "+id+"";		
		try{
			preparedStatement = connection.prepareStatement(sql);
			
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()){
				double debit = rs.getDouble(1);
				double d1  = getAmountTotal(id,payby);
				double credit = getPaidAmountOfClient(id,payby);
				double balance = d1 - credit;
				outstandingReport.setCredit(credit);
				outstandingReport.setDebit(debit);
				outstandingReport.setBalance(balance);
				
				//Decimal Account
				outstandingReport.setCreditx(dateTimeUtils.changeFormat(credit));
				outstandingReport.setDebitx(dateTimeUtils.changeFormat(debit));
				outstandingReport.setBalancex(dateTimeUtils.changeFormat(balance));
				
			}
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		return outstandingReport;
	}

	private double getAmountTotal(String id, String payby) {
		PreparedStatement preparedStatement = null;
		double result = 0.0;
		String sql = "SELECT debit,discount FROM apm_charges_invoice where payby = '"+payby+"' and clientid = "+id+"";		
		
		try{
			preparedStatement = connection.prepareStatement(sql);
			
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()){
				double total = rs.getDouble(1);
				double discount = rs.getDouble(2);
				double r1 = (total*(discount/100));
				total = total-r1;
				result = result + total;
				
				
			}
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	private double getPaidAmountOfClient(String id, String payby) {
		PreparedStatement preparedStatement = null;
		double result = 0.0;
		//String sql = "SELECT sum(payment) FROM apm_charges_payment where clientid = "+id+" and ";
		String sql = "SELECT sum(payment) FROM apm_charges_payment inner join apm_charges_invoice on apm_charges_invoice.id = apm_charges_payment.chargeinvoiceid where apm_charges_invoice.payby = '"+payby+"' and apm_charges_payment.clientid = "+id+"";
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

	private OutstandingReport getDebitAmount(String id,String payby) {
		PreparedStatement preparedStatement = null;
		DateTimeUtils dateTimeUtils = new DateTimeUtils();
		double creditTotal = 0.0;
		OutstandingReport outstandingReport = new OutstandingReport();
		String sql = "SELECT id FROM apm_charges_invoice where payby = '"+payby+"' and tpid = "+id+"";		
		try{
			preparedStatement = connection.prepareStatement(sql);
			
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()){
				
				double credit = getPaidAmountTotal(rs.getString(1));
				//creditTotal = credit + creditTotal;
				double debit = getTotalDebitAmount(rs.getString(1));
				double d1  = getAmountTPTotal(rs.getString(1));
				double balance = d1 - credit;
				outstandingReport.setCredit(credit);
				outstandingReport.setDebit(debit);
				outstandingReport.setBalance(balance);
				
				//Decimal Account
				outstandingReport.setCreditx(dateTimeUtils.changeFormat(creditTotal));
				outstandingReport.setDebitx(dateTimeUtils.changeFormat(debit));
				outstandingReport.setBalancex(dateTimeUtils.changeFormat(balance));
				
			}
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		return outstandingReport;
	}

	private double getAmountTPTotal(String id) {
		PreparedStatement preparedStatement = null;
		double result = 0.0;
		String sql = "SELECT debit,discount FROM apm_charges_invoice where id = "+id+"";		
		
		try{
			preparedStatement = connection.prepareStatement(sql);
			
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()){
				double total = rs.getDouble(1);
				double discount = rs.getDouble(2);
				double r1 = (total*discount)/100;
				total = total - r1;
				result = result + total;
				
				
			}
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	private double getTotalDebitAmount(String id) {
		PreparedStatement preparedStatement = null;
		double result = 0.0;
		String sql = "SELECT debit FROM apm_charges_invoice where id = "+id+" ";		
		
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

	private double getPaidAmountTotal(String id) {
		PreparedStatement preparedStatement = null;
		double result = 0.0;
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

	
	public ArrayList<OutstandingReport> getOutstandingReportOfTPList(int id,
			Pagination pagination, String payby,String thirdpartyid,String fromdate,String todate) {
		PreparedStatement preparedStatement = null;
		DateTimeUtils dateTimeUtils = new DateTimeUtils();
		ArrayList<OutstandingReport>list = new ArrayList<OutstandingReport>();
	//	String sql = "select id,company_name,acc_warning_limit,outInvoiceLimit,telephone,company_email,creditReminderDuration from apm_third_party_details";
		
		todate = todate + " 23:59:59";
		
		StringBuffer sql = new StringBuffer();
		
		/*sql.append("select  apm_third_party_details.id,company_name,acc_warning_limit,outInvoiceLimit,telephone,company_email,creditReminderDuration, ");
		sql.append("sum(debit),sum(discount),apm_charges_invoice.id,sum(discamt) ");
		sql.append("from apm_third_party_details ");
		sql.append("inner join apm_charges_invoice on apm_charges_invoice.tpid = apm_third_party_details.id ");
		sql.append("inner join apm_patient on apm_patient.id = apm_charges_invoice.clientid ");
		sql.append("where payby='Third Party' and  apm_patient.lastmodified between '"+fromdate+"' and '"+todate+"' ");*/
		
		sql.append("select  apm_third_party_details.id,company_name,acc_warning_limit,outInvoiceLimit,telephone,company_email,creditReminderDuration, ");
		sql.append("sum(debit),sum(discount),apm_charges_invoice.id,sum(discamt) from apm_third_party_details ");
		sql.append("inner join apm_charges_invoice on apm_charges_invoice.tpid = apm_third_party_details.id ");
		sql.append("inner join apm_patient on apm_patient.id = apm_charges_invoice.clientid ");
		sql.append("inner join ipd_addmission_form on  ipd_addmission_form.clientid = apm_charges_invoice.clientid ");
		sql.append("where payby='Third Party' and bedid!=0 ");
		sql.append("and  admissiondsate between '"+fromdate+"' and '"+todate+"' ");
		
		if(thirdpartyid!=null){
			if(!thirdpartyid.equals("0")){
				sql.append("and apm_charges_invoice.tpid="+thirdpartyid+" ");
			}
		}
		
		
		sql.append("group by apm_charges_invoice.tpid order by company_name ");

		
		try{
			preparedStatement = connection.prepareStatement(sql.toString());
			ResultSet rs = preparedStatement.executeQuery();
			
			int i = 1;
			while(rs.next()){
				
			
			
				
				OutstandingReport outstandingReport = new OutstandingReport();
				
				//outstandingReport = getDebitAmount(rs.getString(1),payby);
				outstandingReport.setThirdPartyId(rs.getInt(1));
				outstandingReport.setThirdPartyName(rs.getString(2)+" "+" (Third Party)");
				/*outstandingReport.setCreditWarningLimit(rs.getDouble(3));
				outstandingReport.setOutstandingInvoiceLimit(rs.getDouble(4));*/
				outstandingReport.setThirdPartyContactno(rs.getString(5));
				outstandingReport.setThirdPartEmail(rs.getString(6));
			//	outstandingReport.setCreditReminderDuration(rs.getString(7));
				
				//calulate debit with discount
				double total = rs.getDouble(8);
				double discount = rs.getDouble(9);
				double r1 = (total*discount)/100;
				total = total - r1;
				
				//minus discount amt
				double discamt = rs.getDouble(11);
				r1 = discamt;
				total = total - r1;
				
				double credit = getSumOfCreditAmount(rs.getString(1));
				double balance = total - credit;
					
				
				
				outstandingReport.setPaidAmount(credit);
				outstandingReport.setUnpaidAmout(balance);
				outstandingReport.setDebit(total);
				
				
				
				String note = getNote(rs.getInt(1));
				outstandingReport.setNotes(note);
				outstandingReport.setPayby("Third Party");
				
				//Decimal Account
				outstandingReport.setCreditWarningLimitx(dateTimeUtils.changeFormat(rs.getDouble(3)));
				outstandingReport.setOutstandingInvoiceLimitx(dateTimeUtils.changeFormat(rs.getDouble(4)));
				outstandingReport.setPaidAmountx(DateTimeUtils.changeFormat(credit));
				outstandingReport.setUnpaidAmoutx(DateTimeUtils.changeFormat(balance));
				outstandingReport.setDebitx(DateTimeUtils.changeFormat(total));
				
				
				ArrayList<Client>clientList =getInvoicedClientList(rs.getInt(1));
				outstandingReport.setClientList(clientList);

				
				if(balance!=0){
					list.add(outstandingReport);
				}
				
				
				
				i++;
				
//				System.out.println(i);
				
			}
		
			}catch (Exception e) {
				e.printStackTrace();
			}
			return list;
	}
	
	
	private ArrayList<Client> getInvoicedClientList(int tpid) {
		
		PreparedStatement preparedStatement = null;
		ArrayList<Client>list = new ArrayList<Client>();
		
		StringBuffer sql = new StringBuffer();
		sql.append("select  apm_patient.id,title,firstname,surname,mobno,email, sum(debit),sum(discount),apm_charges_invoice.id ");
		sql.append("from apm_patient inner join ");
		sql.append("apm_charges_invoice on apm_patient.id = apm_charges_invoice.clientid where payby='Third Party' ");
		sql.append("and tpid = "+tpid+" group by clientid order by firstname,surname ");
		
		
		try{
			
			preparedStatement = connection.prepareStatement(sql.toString());
			ResultSet rs = preparedStatement.executeQuery();
			
			while(rs.next()){
				
				Client client = new Client();
				client.setId(rs.getInt(1));
				client.setTitle(rs.getString(2));
				client.setFirstName(rs.getString(3));
				client.setLastName(rs.getString(4));
				client.setMobNo(rs.getString(5));
				client.setEmail(rs.getString(6));
				
				
				double debit = rs.getDouble(7);
				double credit = getClientCredit(client.getId(),tpid);
				double balance = debit - credit;
				
				client.setDebitSum(DateTimeUtils.changeFormat(debit));
				client.setCreditSum(DateTimeUtils.changeFormat(credit));
				client.setBalanceSum(DateTimeUtils.changeFormat(balance));
				
				ArrayList<Accounts>invoiceList = getInvoiceList(client.getId(),tpid);
				client.setInvoiceList(invoiceList);
				
				list.add(client);
			}
		
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
		
		return list;
	}
	
	
	

	private double getClientCredit(int clientid, int tpid) {
		PreparedStatement preparedStatement = null;
		double result = 0;
		String sql = "SELECT sum(payment),tpid FROM apm_charges_payment where clientid = "+clientid+" and tpid="+tpid+" group by clientid ";
		
		try{
			
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			if(rs.next()){
				result = rs.getDouble(1);			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return result;
	}

	private ArrayList<Accounts> getInvoiceList(int clientid, int tpid) {
		
		PreparedStatement preparedStatement = null;
		ArrayList<Accounts>list = new ArrayList<Accounts>();
		String sql = "select id,debit,date,discount from apm_charges_invoice where clientid = "+clientid+" and tpid = "+tpid+" and payby = 'Third Party'";
		
		try{
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()){
				Accounts accounts = new Accounts();
				accounts.setInvoiceid(rs.getInt(1));
				
				double debit = rs.getDouble(2);
				accounts.setCommencing(DateTimeUtils.getCommencingDate1(rs.getString(3)));
				
				double credit = getPaidAmountTotal(rs.getString(1));
				
				double total = rs.getDouble(2);
				double discount = rs.getDouble(4);
				double r1 = (total*discount)/100;
				total = total-r1;
				
				double balance = total - credit;
				
				accounts.setDebitAmountx(DateTimeUtils.changeFormat(total));
				accounts.setCreditTotalx(DateTimeUtils.changeFormat(credit));
				accounts.setBalancex(DateTimeUtils.changeFormat(balance));
				accounts.setClientid(clientid);
				
				
				if(total!=credit){
					list.add(accounts);
				}
				
				
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	
		return list;
	}

	private double getSumOfCreditAmount(String tpid){
		
		PreparedStatement preparedStatement = null;
		double result = 0;
		//String sql = "select sum(payment) from apm_charges_payment where chargeinvoiceid = "+invoiceid+" ";
		StringBuffer sql = new StringBuffer();
		sql.append("select sum(payment) from apm_charges_payment inner join ");
		sql.append("apm_charges_invoice on apm_charges_payment.chargeinvoiceid = apm_charges_invoice.id ");
		sql.append("where payby='Third Party' and apm_charges_invoice.tpid = "+tpid+" ");
		sql.append("group by apm_charges_invoice.tpid ");
		
		try{
			
			preparedStatement = connection.prepareStatement(sql.toString());
			ResultSet rs = preparedStatement.executeQuery();
			
			if(rs.next()){
				result = rs.getDouble(1);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return result;
	}

	
	public ArrayList<OutstandingReport> getPendingInvoiceList(String clientId,
			String tpId,int duration) {
		PreparedStatement preparedStatement = null;
		DateTimeUtils dateTimeUtils = new DateTimeUtils();
		ArrayList<OutstandingReport>list = new ArrayList<OutstandingReport>();
		String sql = "select id,debit,date,discount from apm_charges_invoice where clientid = "+clientId+" and tpid = "+tpId+" and payby = 'Third Party'";

		
		try{
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			
			while(rs.next()){
				
				OutstandingReport outstandingReport = new OutstandingReport();
				outstandingReport.setInvoiceNo(rs.getString(1));
				outstandingReport.setDebit(rs.getDouble(2));
				
				double credit = getPaidAmountTotal(rs.getString(1));
				outstandingReport.setCredit(credit);
				double debit = rs.getDouble(2);
				
				double total = rs.getDouble(2);
				double discount = rs.getDouble(4);
				double r1 = (total*(discount/100));
				total = total-r1;
				
				double balance = total - credit;
				
				//double balance = debit - credit;
				outstandingReport.setBalance(balance);
				
				DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
				Calendar cal  = Calendar.getInstance();
				cal.setTime(df.parse(rs.getString(3)));
				cal.add(Calendar.DATE, duration); 
				String checkDate = df.format(cal.getTime());
				
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	        	Date date1 = sdf.parse(rs.getString(3));
	        	Date date2 = sdf.parse(checkDate);
	        	Date currentdate = new Date();
	        	outstandingReport.setInvoiceDate(date2);
	        	outstandingReport.setCurrentdate(currentdate);
	        	outstandingReport.setDiscount(discount);
	        	
	        	//Decimal Account
	        	outstandingReport.setDebitx(dateTimeUtils.changeFormat(rs.getDouble(2)));
	        	outstandingReport.setCreditx(dateTimeUtils.changeFormat(credit));
	        	outstandingReport.setBalancex(dateTimeUtils.changeFormat(balance));
	        	outstandingReport.setDiscountx(dateTimeUtils.changeFormat(discount));
	        	
				
				
				if(balance!= 0){
					list.add(outstandingReport);
				}
				
			}
		
			}catch (Exception e) {
				e.printStackTrace();
			}
			return list;
	}

	
	public int savePayment(String tpId, String clientId, String modeofPayment,
			String invoiceNo, String payamount,String date) {
		int result = 0;
		PreparedStatement pstm = null;
		String sql = "insert into apm_charges_payment(clientid,chargeinvoiceid,payment,paymode,date,tpid) values (?,?,?,?,?,?)";
		try {
			pstm = connection.prepareStatement(sql);
			pstm.setString(1,clientId);
			pstm.setString(2,invoiceNo);
			pstm.setString(3,payamount);
			pstm.setString(4,modeofPayment);
			pstm.setString(5, date);
			pstm.setString(6,tpId);
			
			
			result = pstm.executeUpdate();
			
		}catch (Exception e) {
			
			e.printStackTrace();
		}
		
		return result;
	}

	
	public int saveTPPayment(String tpId, String clientId,
			String modeofPayment, String paymentTotal) {
		int result = 0;
		PreparedStatement pstm = null;
		String sql = "insert into apm_tp_payment(tp_id,client_id,payment_mode,payment_amount,date_time) values (?,?,?,?,?)";
		try {
			pstm = connection.prepareStatement(sql);
			pstm.setString(1,tpId);
			pstm.setString(2,clientId);
			pstm.setString(3,modeofPayment);
			pstm.setString(4,paymentTotal);
			//pstm.setString(5, date);
			result = pstm.executeUpdate();
			
		}catch (Exception e) {
			
			e.printStackTrace();
			
		}
		
		return result;
	}

	
	public ArrayList<OutstandingReport> getPendingClientSelfInvoiceList(
			String clientId) {
		PreparedStatement preparedStatement = null;
		DateTimeUtils dateTimeUtils = new DateTimeUtils();
		ArrayList<OutstandingReport>list = new ArrayList<OutstandingReport>();
		String sql = "select id,debit,discount from apm_charges_invoice where clientid = "+clientId+" and payby = 'Client'";

		
		try{
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			
			while(rs.next()){
				
				OutstandingReport outstandingReport = new OutstandingReport();
				outstandingReport.setInvoiceNo(rs.getString(1));
				outstandingReport.setDebit(rs.getDouble(2));
				
				double credit = getPaidAmountTotal(rs.getString(1));
				outstandingReport.setCredit(credit);
				double debit = rs.getDouble(2);
				
				
				double total = rs.getDouble(2);
				double discount = rs.getDouble(3);
				double r1 = (total*(discount/100));
				total = total-r1;
				
				double balance = total - credit;
				outstandingReport.setBalance(balance);
				int noOfCharges = getNoOfCharges(rs.getInt(1));
				outstandingReport.setNoOfCharges(noOfCharges);
				outstandingReport.setDiscount(discount);
				int invoiceId = getInvoiceId(rs.getInt(1));
				String type = getTypeOfCharge(invoiceId);
				outstandingReport.setChargeType(type);
				
				//Decimal Amount
				
				outstandingReport.setDebitx(dateTimeUtils.changeFormat(rs.getDouble(2)));
				outstandingReport.setCreditx(dateTimeUtils.changeFormat(credit));
				outstandingReport.setBalancex(dateTimeUtils.changeFormat(balance));
				outstandingReport.setDiscountx(dateTimeUtils.changeFormat(discount));
				
				if(balance!= 0){
				list.add(outstandingReport);
				}
				
			}
		
			}catch (Exception e) {
				e.printStackTrace();
			}
			return list;
	}

	private String getTypeOfCharge(int invoiceId) {
		PreparedStatement preparedStatement = null;
		String result = "";
		String sql = "SELECT apmtType FROM apm_invoice_assesments where invoiceid = "+invoiceId+" ";
		
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

	public int getInvoiceId(int id) {
		PreparedStatement preparedStatement = null;
		int result = 0;
		String sql = "SELECT invoiceid FROM apm_charges_assesment where chargeinvoiceid = "+id+" ";
		
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

	
	public int getCreditDurationDate(String tpId) {
		PreparedStatement preparedStatement = null;
		int result = 0;
		String sql = "SELECT creditReminderDuration FROM apm_third_party_details where id = "+tpId+" ";
		
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

	public ArrayList<Accounts> getSelectedInvoiceList(
			String invoiceList,String clientid) {
		PreparedStatement preparedStatement = null;
		ArrayList<Accounts>list = new ArrayList<Accounts>();
		
		String sql = "select id,debit,date,discount FROM  apm_charges_invoice where id in("+invoiceList+") and clientid = "+clientid+" ";
		

		
		try{
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()){
				Accounts accounts = new Accounts();
				accounts.setInvoiceid(rs.getInt(1));
				
				double debit = rs.getDouble(2);
				accounts.setCommencing(DateTimeUtils.getCommencingDate1(rs.getString(3)));
				
				double credit = getPaidAmountTotal(rs.getString(1));
				
				double total = rs.getDouble(2);
				double discount = rs.getDouble(4);
				double r1 = (total*discount)/100;
				total = total-r1;
				
				double balance = total - credit;
				
				accounts.setCreditAmount(credit);
				accounts.setDebitAmount(debit);
				accounts.setBalance(balance);
				
				accounts.setDebitAmountx(DateTimeUtils.changeFormat(total));
				accounts.setCreditTotalx(DateTimeUtils.changeFormat(credit));
				accounts.setBalancex(DateTimeUtils.changeFormat(balance));
				
				list.add(accounts);
			}
				
			
		}catch(Exception e){
			e.printStackTrace();
		}
		return list;
	}

	public ArrayList<Client> getInvoicedClientidList(String invoiceList) {
		PreparedStatement preparedStatement = null;
		ArrayList<Client>list = new ArrayList<Client>();
		String sql = "select clientid FROM  apm_charges_invoice where id in("+invoiceList+") group by clientid" ;
		
		try{
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()){
				AccountsDAO accountsDAO = new JDBCAccountsDAO(connection);
				String clientid = rs.getString(1);
				Client client = accountsDAO.getClientData(clientid);
				
				ArrayList<Accounts>selecttedInvoiceList = getSelectedInvoiceList(invoiceList,clientid);
				client.setSelecttedInvoiceList(selecttedInvoiceList);
				
				double cr = 0;
				double dbt = 0;
				double blnce = 0;
				
			
					
					for(Accounts accounts : selecttedInvoiceList){
						cr = cr + accounts.getCreditAmount();
						dbt = dbt + accounts.getDebitAmount();
						blnce = blnce + accounts.getBalance();
					}
				
				
					client.setCreditTotalx(DateTimeUtils.changeFormat(cr));
					client.setDebitTotalx(DateTimeUtils.changeFormat(dbt));
					client.setBalanceTotalx(DateTimeUtils.changeFormat(blnce));
				
				list.add(client);
				
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return list;
	}

	public String getClientId(String invid) {
		PreparedStatement preparedStatement = null;
		String result = "";
		String sql = "SELECT clientid FROM apm_charges_invoice where id = "+invid+" ";
		
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

	public int saveAltAmount(OutstandingReport outstandingReport) {
		PreparedStatement preparedStatement = null;
		int result = 0;
		String sql = "insert into his_tp_allocation(commencing, tpid, amount, pmnttype, chequeno, chequetype, bankname, handoverto) values(?,?,?,?,?,?,?,?)";
		
		try{
			
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, outstandingReport.getDate());
			preparedStatement.setInt(2, outstandingReport.getThirdPartyId());
			preparedStatement.setString(3, outstandingReport.getAllotamount());
			
			preparedStatement.setString(4, outstandingReport.getPaymentType());
			preparedStatement.setString(5, outstandingReport.getCheqNo());
			preparedStatement.setString(6, outstandingReport.getCheqType());
			preparedStatement.setString(7, outstandingReport.getBankname());
			preparedStatement.setString(8, outstandingReport.getHandoverTo());
			
			result = preparedStatement.executeUpdate();
		}catch (Exception e) {
			// TODO: handle exception
		}
		return result;
	}

	public ArrayList<OutstandingReport> getAllotList(String fromDate,
			String toDate) {
		
		toDate = toDate + " 23:59:59";
		
		PreparedStatement preparedStatement = null;
		ArrayList<OutstandingReport>list = new ArrayList<OutstandingReport>();
		StringBuffer sql = new StringBuffer();
		
		sql.append("SELECT his_tp_allocation.id, commencing, company_name, amount,apm_third_party_details.id FROM his_tp_allocation  inner join apm_third_party_details on ");
		sql.append("apm_third_party_details.id = his_tp_allocation.tpid where commencing between '"+fromDate+"' and '"+toDate+"' order by his_tp_allocation.id desc ");
		
		try{
			
			preparedStatement = connection.prepareStatement(sql.toString());
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()){
				OutstandingReport outstandingReport = new OutstandingReport();
				outstandingReport.setId(rs.getInt(1));
				outstandingReport.setDate(rs.getString(2));
				outstandingReport.setThirdPartyName(rs.getString(3));
				outstandingReport.setAllotamount(rs.getString(4));
				outstandingReport.setThirdPartyId(rs.getInt(5));
				
				list.add(outstandingReport);				
			}
		}catch (Exception e) {
			// TODO: handle exception
		}
		return list;
	}

	public ArrayList<Accounts> getAltInvoiceList(String tpid,String recno) {
		PreparedStatement preparedStatement = null;
		ArrayList<Accounts>list = new ArrayList<Accounts>();
		StringBuffer sql = new StringBuffer();
		
		sql.append("SELECT apm_charges_invoice.id,concat(title,' ',firstname,' ',surname),date,debit,discount,apm_charges_invoice.clientid,dedtn, tds, stmdcine, recdamt, runbal FROM apm_charges_invoice ");
		sql.append("inner join apm_patient on apm_patient.id = apm_charges_invoice.clientid ");
		sql.append("where tpid = "+tpid+"  ");
		AccountsDAO accountsDAO = new JDBCAccountsDAO(connection);
		double altsum = 0;
		try{
			
			preparedStatement = connection.prepareStatement(sql.toString());
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()){
				Accounts accounts = new Accounts();
				accounts.setInvoiceNo(rs.getString(1));
				accounts.setClientName(rs.getString(2));
				accounts.setDate(rs.getString(3));
				
				//calulate debit with discount
				double total = rs.getDouble(4);
				double discount = rs.getDouble(5);
				double r1 = (total*discount)/100;
				accounts.setDiscAmmount(DateTimeUtils.changeFormat(r1));
				total = total - r1;
				
				double credit = accountsDAO.getTotalPaymentReceived(rs.getInt(1));
				double balance = total - credit;
					
				accounts.setClientid(rs.getInt(6));
				accounts.setDedtn(DateTimeUtils.changeFormat(rs.getDouble(7)));
				accounts.setTds(DateTimeUtils.changeFormat(rs.getDouble(8)));
				accounts.setStmdcine(DateTimeUtils.changeFormat(rs.getDouble(9)));
				accounts.setRecdamt(DateTimeUtils.changeFormat(rs.getDouble(10)));
				accounts.setRunbal(DateTimeUtils.changeFormat(rs.getDouble(11)));
				
				double mdcinecharge = getMedicineCharge(accounts.getInvoiceNo());
				accounts.setMdicinecharge(DateTimeUtils.changeFormat(mdcinecharge));
				double hospcharge = total - mdcinecharge;
				accounts.setHospchargel(DateTimeUtils.changeFormat(hospcharge));
				
				accounts.setCreditTotalx(DateTimeUtils.changeFormat(credit));
				accounts.setDebitAmountx(DateTimeUtils.changeFormat(total));
				accounts.setBalancex(DateTimeUtils.changeFormat(balance));
				
				if(balance>0){
					double altmnt = getsumofAltAmt(rs.getString(1),recno);
					altsum = altsum + altmnt;
					accounts.setAltsum(altsum);
					list.add(accounts);
				}
				
				
			}
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		return list;
	}

	private double getsumofAltAmt(String invoiceid,String altrecno) {
		PreparedStatement preparedStatement = null;
		double result = 0;
		String sql = "select stmdcine,recdamt from apm_charges_invoice where id = "+invoiceid+" and altrecno = "+altrecno+"  ";
		
		try{
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			if(rs.next()){
				result = rs.getDouble(1) + rs.getDouble(2);
			}
			
			
		}catch (Exception e) {
			// TODO: handle exception
		}
		
		return result;
	}

	private double getMedicineCharge(String invoiceNo) {
		PreparedStatement preparedStatement = null;
		double result = 0;
		StringBuffer sql = new StringBuffer();
		
		sql.append("select charge,quantity from  apm_charges_invoice  inner join apm_charges_assesment ");
		sql.append("on apm_charges_assesment.chargeinvoiceid = apm_charges_invoice.id ");
		sql.append("inner join apm_invoice on apm_invoice.id = apm_charges_assesment.invoiceid ");
		sql.append("inner join apm_invoice_assesments on apm_invoice_assesments.invoiceid = apm_invoice.id ");
		sql.append("where apm_charges_invoice.id = "+invoiceNo+" and masterchargetype='Medicine Charge'" );		
		
		try{
			preparedStatement = connection.prepareStatement(sql.toString());
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()){
				
				result = result + (rs.getDouble(1) * rs.getInt(2));
			}
						
		}catch (Exception e) {
			// TODO: handle exception
		}
		return result;
	}

	public int updateInvoiceAllocation(String invoiceno, String dedtn,
			String tds, String stmdcine, String recdamt, String runbal,
			String recno) {
		// TODO Auto-generated method stub
		return 0;
	}

	public int updateInvoiceAllocation(String invoiceno, String dedtn,
			String tds, String stmdcine, String recdamt, String runbal,
			String recno, String date) {
		PreparedStatement preparedStatement = null;
		int result = 0;
		String sql = "update apm_charges_invoice set dedtn=?, tds=?, stmdcine=?, recdamt=?, runbal=?, altrecno=?, altdate=? where id="+invoiceno+" ";
		
		try{
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, dedtn);
			preparedStatement.setString(2, tds);
			preparedStatement.setString(3, stmdcine);
			preparedStatement.setString(4, recdamt);
			preparedStatement.setString(5, runbal);
			preparedStatement.setString(6, recno);
			preparedStatement.setString(7, date);
			
			result = preparedStatement.executeUpdate();
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}

	public boolean checkInvoiceExist(String invoiceno) {
		PreparedStatement preparedStatement = null;
		boolean result = false;
		String sql = "SELECT * FROM apm_charges_payment where chargeinvoiceid = "+invoiceno+" ";
		
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

	public int getLastPaymentId(String invoiceno) {
		PreparedStatement preparedStatement = null;
		int result = 0;
		String sql = "SELECT id FROM apm_charges_payment where chargeinvoiceid = "+invoiceno+" order by id desc limit 0,1 ";
		
		try{
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			if(rs.next()){
				result = rs.getInt(1);			}
			
		}catch (Exception e) {
			// TODO: handle exception
		}
		return result;
	}

	public int updatePayment(int lastpamntid, String date, String payment) {
		PreparedStatement preparedStatement = null;
		int result = 0;
		String sql = "update apm_charges_payment set payment=?,date=? where id="+lastpamntid+" ";
		
		try{
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, payment);
			preparedStatement.setString(2, date);
			
			result = preparedStatement.executeUpdate();
			
		}catch (Exception e) {
			// TODO: handle exception
		}
		return result;
	}


	
	
	



	


}
