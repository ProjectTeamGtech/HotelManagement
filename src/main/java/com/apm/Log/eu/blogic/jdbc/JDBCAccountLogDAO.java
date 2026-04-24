package com.apm.Log.eu.blogic.jdbc;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.TimeZone;

import com.apm.Accounts.eu.entity.Accounts;
import com.apm.DiaryManagement.eu.entity.CompleteAppointment;
import com.apm.Log.eu.bi.AccountLogDAO;
import com.apm.Log.eu.bi.LogDAO;
import com.apm.Log.eu.entity.LogDetail;
import com.apm.Pharmacy.web.action.Tra;
import com.apm.common.eu.blogic.jdbc.JDBCBaseDAO;
import com.apm.common.utils.DateTimeUtils;
import com.mysql.jdbc.log.Log;

public class JDBCAccountLogDAO extends JDBCBaseDAO implements AccountLogDAO{
	
	public JDBCAccountLogDAO(Connection connection){
		this.connection = connection;
		
	}
	
	

	public int saveAmpmInvoice(CompleteAppointment completeAppointment,int invoiceId,String status,String lastMOdified) {
		PreparedStatement preparedStatement = null;
		int result = 0;
	//	String status = "Created";
		String sql = "insert into apm_invoice_log(clientid,practitionerid,clientname,practitionername,date,chargetype,appointmentid,location,status,invoiceDate,invoiceId,debit) values(?,?,?,?,?,?,?,?,?,?,?,?)";
		int invoiceid = 0;
		
	/*	SimpleDateFormat sdf = new SimpleDateFormat("d-MM-yyyy hh:mm:ss");
		Calendar now = Calendar.getInstance();
		now.add(Calendar.HOUR, 5);
		now.add(Calendar.MINUTE, 30);
		String date = sdf.format(now.getTime());
		System.out.println(date);*/
		
		
		
		try{
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, completeAppointment.getClientId());
			preparedStatement.setString(2, completeAppointment.getPractitionerId());
			preparedStatement.setString(3, completeAppointment.getUser());
			preparedStatement.setString(4, completeAppointment.getPractitionerName());
			preparedStatement.setString(5, completeAppointment.getInvoiceDate());
			preparedStatement.setString(6, completeAppointment.getChargeType());
			preparedStatement.setInt(7, Integer.parseInt(completeAppointment.getAppointmentid()));
			String location = getLocation(completeAppointment.getAppointmentid());
			preparedStatement.setString(8, location);
			preparedStatement.setString(9, status);
			preparedStatement.setString(10, lastMOdified);
			preparedStatement.setString(11, Integer.toString(invoiceId));
			double totalCharge = getTotalCharge(invoiceId);
			preparedStatement.setDouble(12, totalCharge);
			
			result = preparedStatement.executeUpdate();
			
		
		}catch (Exception e) {
			e.printStackTrace();
//			StringWriter sw = new StringWriter();
//	         PrintWriter pw = new PrintWriter(sw);
//	         e.printStackTrace(pw);
//			Tra.exceptionMail(sw.toString());
		}
		return result;
	}
	
	
	private String getLocation(String appointmentid) {
		String location = null;
		PreparedStatement preparedStatement = null;
		
		String sql = "select location from apm_available_slot where id = "+appointmentid+"";
		try{
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()){
				location = rs.getString(1);
				}
			
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		return location;
	}

	/*public int updateAmpmInvoice(CompleteAppointment completeAppointment) {
		PreparedStatement preparedStatement = null;
		int result = 0;
		String status = "Modified";
		String sql = "insert into apm_invoice_log(clientid,practitionerid,clientname,practitionername,date,chargetype,appointmentid,location,status,invoiceDate,invoiceId) values(?,?,?,?,?,?,?,?,?,?,?)";
		int invoiceid = 0;
		try{
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, completeAppointment.getClientId());
			preparedStatement.setString(2, completeAppointment.getPractitionerId());
			preparedStatement.setString(3, completeAppointment.getUser());
			preparedStatement.setString(4, completeAppointment.getPractitionerName());
			preparedStatement.setString(5, completeAppointment.getInvoiceDate());
			preparedStatement.setString(6, completeAppointment.getChargeType());
			preparedStatement.setInt(7, Integer.parseInt(completeAppointment.getAppointmentid()));
			String location = getLocation(completeAppointment.getAppointmentid());
			preparedStatement.setString(8, location);
			preparedStatement.setString(9, status);
			preparedStatement.setTimestamp(10, DateTimeUtils.getCurrentDateInSQLCasting());
			String invoiceId = "";
			preparedStatement.setString(11, invoiceId);
			result = preparedStatement.executeUpdate();
			
			if(result == 1){
				ResultSet resultSet = preparedStatement.getGeneratedKeys();
				if(resultSet.next()){
					invoiceid = resultSet.getInt(1);  
				}
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		return invoiceid;
	}*/

	
	/*public int ReverseInvoiceEntry(CompleteAppointment completeAppointment,String id) {
		PreparedStatement preparedStatement = null;
		int result = 0;
		String status = "Reversed";
		String sql = "insert into apm_invoice_log(clientid,practitionerid,clientname,practitionername,date,chargetype,appointmentid,location,status,invoiceDate,invoiceId) values(?,?,?,?,?,?,?,?,?,?,?)";
		try{			
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, completeAppointment.getClientId());
			preparedStatement.setString(2, completeAppointment.getPractitionerId());
			preparedStatement.setString(3, completeAppointment.getUser());
			preparedStatement.setString(4, completeAppointment.getPractitionerName());
			preparedStatement.setString(5, completeAppointment.getInvoiceDate());
			preparedStatement.setString(6, completeAppointment.getChargeType());
			preparedStatement.setInt(7, Integer.parseInt(completeAppointment.getAppointmentid()));
			String location = getLocation(completeAppointment.getAppointmentid());
			preparedStatement.setString(8, location);
			preparedStatement.setString(9, status);
			preparedStatement.setTimestamp(10, DateTimeUtils.getCurrentDateInSQLCasting());
			preparedStatement.setString(11, id);
			result = preparedStatement.executeUpdate();
			
	
		
		}catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}*/

	public int saveChargesInvoice(String payby, String commencing,int clientid,double debit,double discount,String notes,int tpid,String location,int invoiceChargeId,String lastModified) {
		PreparedStatement preparedStatement = null;
		int result = 0;
		String status = "Created";
		String sql = "insert into apm_charges_invoice_log(payby,date,discount,debit,clientid,chargetype,notes,tpid,location,status,invoiceDate,invoiceChargeId) values(?,?,?,?,?,?,?,?,?,?,?,?)";
		int invoiceid = 0;
		
		/*SimpleDateFormat sdf = new SimpleDateFormat("d-MM-yyyy hh:mm:ss");
		Calendar now = Calendar.getInstance();
		now.add(Calendar.HOUR, 5);
		now.add(Calendar.MINUTE, 30);
		String date = sdf.format(now.getTime());
		System.out.println(date);*/
		
	
		
		try{
			
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, payby);
			preparedStatement.setString(2, commencing);
			preparedStatement.setDouble(3, discount);
			preparedStatement.setDouble(4, debit);
			preparedStatement.setInt(5, clientid);
			preparedStatement.setString(6, "Submit");
			preparedStatement.setString(7, notes);
			preparedStatement.setInt(8, tpid);
			preparedStatement.setString(9, location);
			preparedStatement.setString(10, status);
			preparedStatement.setString(11, lastModified);
			preparedStatement.setInt(12, invoiceChargeId);
			result = preparedStatement.executeUpdate();
			
			
		}catch(Exception e){
			e.printStackTrace();
//			StringWriter sw = new StringWriter();
//	         PrintWriter pw = new PrintWriter(sw);
//	         e.printStackTrace(pw);
//			Tra.exceptionMail(sw.toString());
		}
		return result;
	}
	
	/*public int updateChargeType(String invoiceId,String chargeType) {
		PreparedStatement preparedStatement = null;
		int result = 0;
		String status = "Modified";
		String sql = "insert into apm_invoice_log(clientid,practitionerid,clientname,practitionername,date,chargetype,appointmentid,location,status,invoiceDate) values(?,?,?,?,?,?,?,?,?,?)";
		
		try{
			preparedStatement = connection.prepareStatement(sql);
			CompleteAppointment completeAppointment = new CompleteAppointment();
			completeAppointment = getInvoiceDetail(invoiceId);
			
			preparedStatement.setString(1, completeAppointment.getClientId());
			preparedStatement.setString(2, completeAppointment.getPractitionerId());
			preparedStatement.setString(3, completeAppointment.getUser());
			preparedStatement.setString(4, completeAppointment.getPractitionerName());
			preparedStatement.setString(5, completeAppointment.getInvoiceDate());
			preparedStatement.setString(6, chargeType);
			preparedStatement.setInt(7, Integer.parseInt(completeAppointment.getAppointmentid()));
			String location = getLocation(completeAppointment.getAppointmentid());
			preparedStatement.setString(8, location);
			preparedStatement.setString(9, status);
			preparedStatement.setTimestamp(10, DateTimeUtils.getCurrentDateInSQLCasting());
			result = preparedStatement.executeUpdate();
			
		}catch(Exception e){
			e.printStackTrace();
		}
		return result;
	}*/

	/*private CompleteAppointment getInvoiceDetail(String invoiceId) {
		PreparedStatement preparedStatement = null;
		CompleteAppointment completeAppointment = new CompleteAppointment();
		String sql = "select clientid,practitionerid,clientname,practitionername,date,chargetype,appointmentid,location from apm_invoice where id = "+invoiceId+"";
		try{
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()){
				completeAppointment.setClientId(rs.getString(1));
				completeAppointment.setPractitionerId(rs.getString(2));
				completeAppointment.setClient(rs.getString(3));
				completeAppointment.setPractitionerName(rs.getString(4));
				completeAppointment.setInvoiceDate(rs.getString(5));
				completeAppointment.setChargeType(rs.getString(6));
				completeAppointment.setAppointmentid(rs.getString(7));
				completeAppointment.setLocation(rs.getString(8));
				
				
			}
			
		}catch(Exception e){
			
		}
		return completeAppointment;
	}*/

	public int saveChargesPayment(String clientId, int invoiceid,String totalamount, String paymode,int tpid,String invoiceDate,String lastModified,String paymentNote) {
		PreparedStatement preparedStatement = null;		
		String status = "Payment";
		int result = 0;
		String sql = "insert into apm_charges_payment_log(clientid,chargeinvoiceid,payment,paymode,date,tpid,status,invoiceDate,paymentnote) values(?,?,?,?,?,?,?,?,?) ";
		
		/*SimpleDateFormat sdf = new SimpleDateFormat("d-MM-yyyy hh:mm:ss");
		Calendar now = Calendar.getInstance();
		now.add(Calendar.HOUR, 5);
		now.add(Calendar.MINUTE, 30);
		String date = sdf.format(now.getTime());
		System.out.println(date);
*/		
		
		
		try{
			
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, clientId);
			preparedStatement.setInt(2, invoiceid);
			preparedStatement.setString(3, totalamount);
			preparedStatement.setString(4, paymode);
			preparedStatement.setString(5, lastModified);
			preparedStatement.setInt(6, tpid);
			preparedStatement.setString(7, status);
			preparedStatement.setString(8, invoiceDate);
			preparedStatement.setString(9, paymentNote);
			
			result = preparedStatement.executeUpdate();
			
			
		}catch(Exception e){
			e.printStackTrace();
//			StringWriter sw = new StringWriter();
//	         PrintWriter pw = new PrintWriter(sw);
//	         e.printStackTrace(pw);
//			Tra.exceptionMail(sw.toString());
		}
		return result;
	}
	
/*	public int updateDeliverStatus(int invoiceid,String status) {
		PreparedStatement preparedStatement = null;
		int result = 0;
		String sql = "update apm_charges_invoice_log set deliverstatus=? where id=?";
		
		try{
			
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, status);
			preparedStatement.setInt(2, invoiceid);
			
			result = preparedStatement.executeUpdate();
		}catch(Exception e){
			e.printStackTrace();
		}
		return result;
	}
	*/
	/*public int updateModifiedInvoiceChargesChargeType(String charges) {
		PreparedStatement preparedStatement = null;
		int result = 0;
		String sql = "update apm_invoice_log set chargetype=? where id = ?";
		
		try{
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, "Charge");
			preparedStatement.setString(2, charges);
			
			result = preparedStatement.executeUpdate(); 
		
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return result;
	}*/
	
	public ArrayList<LogDetail> getAccChargeList(String clientId) {
		PreparedStatement preparedStatement = null;
		ArrayList<LogDetail>list = new ArrayList<LogDetail>();
		String sql = "select invoiceId,practitionername,date,chargetype,status,invoiceDate,debit from apm_invoice_log where clientId = "+clientId+" order by id desc ";
		
		try{
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()){
				LogDetail log = new LogDetail();
				log.setInvoiceNo(rs.getString(1));				
				log.setPractitioner(rs.getString(2));
				log.setDate(rs.getString(3));
				log.setChargeType(rs.getString(4));
				log.setStatus(rs.getString(5));
				log.setInvoiceDate(rs.getString(6));
				log.setTotalCharge(rs.getDouble(7));
				
				list.add(log);
			}
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	private double getTotalCharge(int invoiceId) {
		PreparedStatement preparedStatement = null;
		double result = 0;
		String sql = "select sum(charge) from apm_invoice_assesments where invoiceid = "+invoiceId+"";
		try{
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()){
				result = rs.getDouble(1);
			}
			
		}catch(Exception e){
			
		}
		
		return result;
	}

	public int updateDebitAmount(double sumofCharges, int invoiceid,String invoiceDate, String submitInvoiceNotes,String lastModified) {
		PreparedStatement preparedStatement = null;
		int result = 0;
		String status = "Modified";
		String sql = "insert into apm_charges_invoice_log(payby,date,discount,debit,clientid,chargetype,notes,tpid,location,status,invoiceDate,invoiceChargeId) values(?,?,?,?,?,?,?,?,?,?,?,?)";
			
		/*SimpleDateFormat sdf = new SimpleDateFormat("d-MM-yyyy hh:mm:ss");
		Calendar now = Calendar.getInstance();
		now.add(Calendar.HOUR, 5);
		now.add(Calendar.MINUTE, 30);
		String date = sdf.format(now.getTime());
		System.out.println(date);*/
		
		
		
		try{
			
			preparedStatement = connection.prepareStatement(sql);
			Accounts account = new Accounts();
			account = getChargesInvoiceDetails(invoiceid);
			preparedStatement.setString(1, account.getPayby());
			preparedStatement.setString(2, invoiceDate);
			preparedStatement.setDouble(3, account.getDiscount());
			preparedStatement.setDouble(4, sumofCharges);
			preparedStatement.setInt(5, account.getClientid());
			preparedStatement.setString(6, "Submit");
			preparedStatement.setString(7, submitInvoiceNotes);
			preparedStatement.setString(8, account.getThirdParty());
			preparedStatement.setString(9, account.getLocation());
			preparedStatement.setString(10, status);
			preparedStatement.setString(11, lastModified);
			preparedStatement.setInt(12, invoiceid);
			result = preparedStatement.executeUpdate();
			
			
		}catch(Exception e){
			e.printStackTrace();
		}
		return result;
	}

	private Accounts getChargesInvoiceDetails(int invoiceid) {
		PreparedStatement preparedStatement = null;
		Accounts account = new Accounts();
		String sql = "select payby,discount,clientid,tpid,location,deliverstatus from apm_charges_invoice where id = "+invoiceid+"";
		try{
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()){
				//account.setCommencing(rs.getString(1));
				account.setPayby(rs.getString(1));
				account.setDiscount(rs.getInt(2));
				//account.setDebitAmount(rs.getInt(4));
				account.setClientid(rs.getInt(3));
				//account.setChargeType(rs.getString(6));
				//account.setNotes(rs.getString(7));
				account.setThirdParty(rs.getString(4));
				account.setLocation(rs.getString(5));
				account.setDeliverstatus(rs.getString(6));
				
				
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
		return account;
	}
	
	public ArrayList<LogDetail> getAccInvoiceList(String clientId) {
		PreparedStatement preparedStatement = null;
		String s1 = "1";
		String s2 = "2";
		ArrayList<LogDetail>list = new ArrayList<LogDetail>();
		//String sql = "select invoiceid,commencing,appointmentid,practitionerName,charge from apm_invoice_assesments where clientId = "+clientId+"";
		String sql = "select invoiceChargeId,date,payby,debit,deliverstatus,tpid,status,invoiceDate from apm_charges_invoice_log where clientid = "+clientId+" order by id desc ";
		try{
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()){
				LogDetail log = new LogDetail();
				log.setInvoiceNo(rs.getString(1));
				log.setDate(rs.getString(2));
				log.setPayby(rs.getString(3));
				log.setTotal(rs.getString(4));
				String status = rs.getString(5);
				if(status == null){
					status = "";
				}
				if(status.equals(s1)){
					log.setDeliverstatus("Print");
				}
				else if(status.equals(s2))
				{
					log.setDeliverstatus("Email");
				}
				else{
					log.setDeliverstatus("No Action");
				}
				
				String clientname = getClientFullName(clientId);
				log.setClientname(clientname);
				String payee = rs.getString(3);
				String payeename = null;
				if(payee.equalsIgnoreCase("Third Party")){
					payeename = getTpname(rs.getInt(6));
					
				}
				else{
					payeename = clientname;
				}
				log.setPayeename(payeename);
				log.setStatus(rs.getString(7));
				log.setInvoiceDate(rs.getString(8));
				
				list.add(log);
			}
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	private String getClientFullName(String clientId) {
		PreparedStatement preparedStatement = null;
		String result = "";
		String sql = "SELECT title,firstname,surname FROM apm_patient where  id = "+clientId+"";
		
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
	
	private String getTpname(int id) {
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

	
	public ArrayList<LogDetail> getPaymentList(String clientId) {
		PreparedStatement preparedStatement = null;
		
		ArrayList<LogDetail>list = new ArrayList<LogDetail>();
		String sql = "select id,chargeinvoiceid,payment,paymode,date,deliver_status,status,invoiceDate,paymentnote from apm_charges_payment_log where clientid = "+clientId+" order by id desc ";

		try{
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()){
				LogDetail log = new LogDetail();
				log.setId(rs.getInt(1));
				log.setInvoiceNo(rs.getString(2));
				log.setTotal(rs.getString(3));
				log.setPaymentMode(rs.getString(4));
				log.setDate(rs.getString(5));				
				log.setDeliverstatus(rs.getString(6));
				
				if(rs.getString(6)==null){
					log.setDeliverstatus("No Action");
				}
				log.setStatus(rs.getString(7));
				log.setInvoiceDate(rs.getString(8));
				if(rs.getString(9)!=null){
					log.setPaymentNote(rs.getString(9));
				}
				
				
				
				
				list.add(log);
			}
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	public boolean getInvoiceIdForColor(String invoiceNo) {
		PreparedStatement preparedStatement = null;
		boolean result = false;
		String sql = "select * from apm_charges_assesment where invoiceid = "+invoiceNo+"";
		try{
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()){
				result = true;
			}
			
		}catch(Exception e){
			
		}
		return result;
	}

	public boolean checkColorForPaid(String invoiceNo) {
		PreparedStatement preparedStatement = null;
		boolean result = false;
		String sql = "select chargeinvoiceid from apm_charges_assesment where invoiceid = "+invoiceNo+"";
		try{
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()){
				result = getColorInvoiceId(rs.getString(1));
			}
			
		}catch(Exception e){
			
		}
		return result;
	}

	private boolean getColorInvoiceId(String chargeInvoiceId) {
		PreparedStatement preparedStatement = null;
		boolean result = false;
		String sql = "select * from apm_charges_payment where chargeinvoiceid = "+chargeInvoiceId+"";
		try{
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()){
				double debit = getDebitAmount(chargeInvoiceId);
				double credit = getCreditAmount(chargeInvoiceId);
				double total = debit - credit;
				if(total == 0){
					result = true;
				}				
			}
			
		}catch(Exception e){
			
		}
		return result;
	}

	private double getCreditAmount(String chargeInvoiceId) {
		PreparedStatement preparedStatement = null;
		double result = 0;
		String sql = "select sum(payment) from apm_charges_payment where chargeinvoiceid = "+chargeInvoiceId+"";
		try{
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()){
				result = rs.getDouble(1);
			}
			
		}catch(Exception e){
			
		}
		return result;
	}

	private double getDebitAmount(String chargeInvoiceId) {
		PreparedStatement preparedStatement = null;
		double result = 0;
		String sql = "select debit from apm_charges_invoice where id = "+chargeInvoiceId+"";
		try{
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()){
				result = rs.getDouble(1);
			}
			
		}catch(Exception e){
			
		}
		return result;
	}
}
