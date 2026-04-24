package com.apm.Report.eu.blogic.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.apm.Registration.eu.bi.UserProfileDAO;
import com.apm.Registration.eu.blogic.jdbc.JDBCUserProfileDAO;
import com.apm.Registration.eu.entity.UserProfile;
import com.apm.Report.eu.bi.CommissionDAO;
import com.apm.Report.eu.entity.Commission;
import com.apm.common.eu.blogic.jdbc.JDBCBaseDAO;
import com.apm.common.utils.DateTimeUtils;

public class JDBCCommissionDAO extends JDBCBaseDAO implements CommissionDAO{

	public JDBCCommissionDAO(Connection connection) {
		this.connection = connection;
	}
	
	public ArrayList<Commission> getPractitionerList(String fromDate,String toDate,String diaryuser,String location) {
		PreparedStatement preparedStatement = null;
		Double totalCharge = 0.0,DNACommission=0.0,CACommission=0.0,totalCommission = 0.0;
		//int commissionCharge = 0,practCompAppCharge = 0;
		Double CommssnRateDNA = 0.0,CommssnRateComplete = 0.0;
		ArrayList<Commission> list = new ArrayList<Commission>();
		
		String sql = "";
		
		//  02 feb 2018  add this code and apm_available_slot.procedures='0' to not show ot charges
		if(!fromDate.equals("") && !toDate.equals("")){	
		
			sql = "select apm_user.id,apm_user.firstname,apm_user.lastname,apm_available_slot.location,chargetype from apm_user "
				+ "inner join apm_available_slot on apm_available_slot.diaryuserid = apm_user.id  "
				+ "and commencing between '"+fromDate+"' AND '"+toDate+"' and apm_available_slot.procedures='0' group by apm_user.id";
		}else{
			
			sql = "select apm_user.id,apm_user.firstname,apm_user.lastname,apm_available_slot.location,chargetype from apm_user "
					+ "inner join apm_available_slot on apm_available_slot.diaryuserid = apm_user.id group by apm_user.id where apm_available_slot.procedures='0'";
			}
		
		if(location!=null){
			
			if(!diaryuser.equals("0")){
				sql = "";
				if(!fromDate.equals("") && !toDate.equals("")){	
					
					sql = "select apm_user.id,apm_user.firstname,apm_user.lastname,apm_available_slot.location,chargetype from apm_user "
						+ "inner join apm_available_slot on apm_available_slot.diaryuserid = apm_user.id  "
						+ "and commencing between '"+fromDate+"' AND '"+toDate+"' and apm_available_slot.diaryuserid="+diaryuser+" and apm_available_slot.procedures='0'  group by apm_user.id";
				}else{
					
					sql = "select apm_user.id,apm_user.firstname,apm_user.lastname,apm_available_slot.location,chargetype from apm_user "
							+ "inner join apm_available_slot on apm_available_slot.diaryuserid = apm_user.id and apm_available_slot.diaryuserid="+diaryuser+" and apm_available_slot.procedures='0'  group by apm_user.id ";
					}
			}
			if(!location.equals("0")){
				sql = "";
				if(!fromDate.equals("") && !toDate.equals("")){	
					
					sql = "select apm_user.id,apm_user.firstname,apm_user.lastname,apm_available_slot.location,chargetype from apm_user "
						+ "inner join apm_available_slot on apm_available_slot.diaryuserid = apm_user.id  "
						+ "and commencing between '"+fromDate+"' AND '"+toDate+"' and apm_available_slot.location="+location+" and apm_available_slot.procedures='0' group by apm_user.id";
				}else{
					
					sql = "select apm_user.id,apm_user.firstname,apm_user.lastname,apm_available_slot.location,chargetype from apm_user "
							+ "inner join apm_available_slot on apm_available_slot.diaryuserid = apm_user.id and  apm_available_slot.location="+location+" and apm_available_slot.procedures='0'  group by apm_user.id ";
					}
			}
			
			if(!diaryuser.equals("0") && !location.equals("0")){
				sql = "";
				if(!fromDate.equals("") && !toDate.equals("")){	
					
					sql = "select apm_user.id,apm_user.firstname,apm_user.lastname,apm_available_slot.location,chargetype from apm_user "
						+ "inner join apm_available_slot on apm_available_slot.diaryuserid = apm_user.id  "
						+ "and commencing between '"+fromDate+"' AND '"+toDate+"' and apm_available_slot.location="+location+" and apm_available_slot.diaryuserid="+diaryuser+" and apm_available_slot.procedures='0' group by apm_user.id";
				}else{
					
					sql = "select apm_user.id,apm_user.firstname,apm_user.lastname,apm_available_slot.location,chargetype from apm_user "
							+ "inner join apm_available_slot on apm_available_slot.diaryuserid = apm_user.id and  apm_available_slot.location="+location+" and apm_available_slot.diaryuserid="+diaryuser+" and apm_available_slot.procedures='0'  group by apm_user.id ";
					}
			}
		}
				 
		try{
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()){
				Commission commission = new Commission();
				commission.setPractitionerId(rs.getString(1));
				String userName = ""+rs.getString(2)+" "+rs.getString(3)+"";
				commission.setPractitionerName(userName);
				
				String clinicLocation = getClinicLocation(rs.getString(4));
				commission.setClinicLocation(clinicLocation);
				
				int totalAppNo = getTotalAppointNo(rs.getString(1),fromDate,toDate);				
				commission.setTotalAppNo(totalAppNo);
				
				int totalDNA = getTotalDNA(rs.getString(1),fromDate,toDate);
				commission.setTotalDNA(totalDNA);
				
				int totalCompleted = getTotalCompletedApp(rs.getString(1),fromDate,toDate);
				commission.setTotalCompleted(totalCompleted);
				
				int totalIncompleted = getTotalIncompleteApp(rs.getString(1),fromDate,toDate);
				commission.setTotalIncompleted(totalIncompleted);
				
				ArrayList<Commission> totalChargeInvoicedList = getTotalChargeInvoiced(rs.getString(1),fromDate,toDate);
				int totalChargeInvoiced = totalChargeInvoicedList.size();
				commission.setTotalChargeInvoiced(totalChargeInvoiced);
				
				int totalInvoicePaid = getTotalInvoicePaid(rs.getString(1),fromDate,toDate);
				commission.setTotalInvoicePaid(totalInvoicePaid);
				
				totalCharge = getTotalCharges(rs.getInt(1),fromDate,toDate);				
				commission.setTotalCharge(DateTimeUtils.changeFormat(totalCharge));
				
				CommssnRateDNA = getPractitionerDNACharge(rs.getInt(1));
				commission.setDNACharge(DateTimeUtils.changeFormat(CommssnRateDNA));
				
				CommssnRateComplete = PractitionerCACharge(rs.getInt(1));
				commission.setCACharge(DateTimeUtils.changeFormat(CommssnRateComplete));
				
				int chargetype = rs.getInt(5);
				commission.setChtype(chargetype);
				if(chargetype==1){
					DNACommission = (totalDNA * CommssnRateDNA);
					CACommission = (totalCompleted * CommssnRateComplete);				
					totalCommission = DNACommission+CACommission;
					commission.setTotalCommission(DateTimeUtils.changeFormat(totalCommission));
				}else{
					double totalper = CommssnRateDNA + CommssnRateComplete;		
					int totalcharges = totalDNA+totalCompleted;
					
					totalCommission = (totalCharge*totalper)/100;
					commission.setTotalCommission(DateTimeUtils.changeFormat(totalCommission));
				}
				
				
				list.add(commission);
				
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
		return list;
	}
	
	public ArrayList<Commission> getClientCommissionList(int practitionerId) {
		PreparedStatement preparedStatement = null;
		Double commssionFee = 0.0, commssionRate = 0.0;
		ArrayList<Commission> list = new ArrayList<Commission>();
		String sql = "select id,aptmtype,commencing,clientId,clientname from apm_available_slot where "
		+ "diaryuserid = "+practitionerId+" and status=0 group by id order by clientId";
		
		/*String sql = "select apm_charges_invoice.id,apm_charges_invoice.payby,apm_charges_invoice.date,apm_charges_payment.paymode,"
				+ "apm_charges_payment.date,apm_available_slot.id,apm_available_slot.aptmtype,apm_available_slot.commencing, "
				+ "apm_available_slot.clientId,apm_available_slot.clientname from "
				+ "apm_charges_invoice inner join apm_available_slot on apm_available_slot.clientId = apm_charges_invoice.clientid "
				+ "inner join apm_charges_payment on apm_available_slot.clientId = apm_charges_payment.clientid where "
				+ "apm_available_slot.diaryuserid = "+practitionerId+" group by apm_available_slot.id order by apm_available_slot.clientId";
		*/try{
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			
			while(rs.next()){
				Commission commission = new Commission();
				String appointmentId = rs.getString(1);
				String appType = getAppointmentType(rs.getString(2));
				commission.setAppType(appType);
				commission.setCommensing(rs.getString(3));
				
				commission.setClientId(rs.getString(4));
				//String clientName = rs.getString(10);
				commission.setClientName(rs.getString(5));
				
				String apptStatus = getAppointmentStatus(rs.getString(4),appointmentId);	
				if(apptStatus.equals("")){
					apptStatus = getIncompleteApp(rs.getString(4));
				}
				commission.setAppStatus(apptStatus);
				
				Double charge = getAppCharge(rs.getString(4),appointmentId);
				
				commission.setCharge(DateTimeUtils.changeFormat(charge));
				
				if(apptStatus.equals("Completed")){					
					
					//Commission commission1 = getInvoice(rs.getString(9));
					String invoiceNo = getInvoiceNo(rs.getString(4));
					commission.setInvoiceNo(invoiceNo);
					String invoiceTo = getInvoiceTo(rs.getString(4));
					if(invoiceTo.equals("Client")){
						invoiceTo = "Self";
					}	
					
					/*commssionRate = getCommissionFee(rs.getString(4),appointmentId);	
					charge1 = Integer.parseInt(charge);*/
					/*commssionFee = (charge1 * commssionRate)/100;					
					commission.setCommssionFee(commssionFee);*/
					
					commission.setInvoiceTo(invoiceTo);
					String invoiceDate = getInvoiceDate(rs.getString(4));
					commission.setInvoiceDate(invoiceDate);				
					
					//Commission commission2 = getPaymentMode(rs.getString(9));
					String paymode = getPaymode(rs.getString(4));
					commission.setPaymode(paymode);
					String paidDate = getPaidDate(rs.getString(4));
					commission.setPaidDate(paidDate);
					commssionRate = getCommissionFee(rs.getString(4),appointmentId);
				}
				else if(apptStatus.equals("DNA")){	
					
					commission.setInvoiceNo("");
					commission.setInvoiceTo("");
					commission.setInvoiceDate("");					
					commission.setPaymode("");
					commission.setPaidDate("");
					commssionRate = getCommissionFee(rs.getString(4),appointmentId);
					
				}
				else{
					commission.setInvoiceNo("");
					commission.setInvoiceTo("");
					commission.setInvoiceDate("");					
					commission.setPaymode("");
					commission.setPaidDate("");
					//commission.setCommssionFee(0);
					commssionRate = 0.0;
				}			
				//commssionRate = getCommissionFee(rs.getString(4),appointmentId);	
				//charge1 = Double.parseDouble(charge);
				//commssionFee = commssionRate;					
				commission.setCommssionFee(DateTimeUtils.changeFormat(commssionRate));
				
				
				list.add(commission);
					
			}
			
			/*while(rs.next()){
				Commission commission = new Commission();			
				
				String appointmentId = rs.getString(6);
				String appType = getAppointmentType(rs.getString(7));
				commission.setAppType(appType);
				commission.setCommensing(rs.getString(8));
				
				commission.setClientId(rs.getString(9));
				//String clientName = rs.getString(10);
				commission.setClientName(rs.getString(10));
				
				String apptStatus = getAppointmentStatus(rs.getString(9));				
				commission.setAppStatus(apptStatus);
				
				String charge = getAppCharge(rs.getString(9),appointmentId);
				commission.setCharge(charge);
				
				if(!apptStatus.equals("Incomplete")){					
				
				//Commission commission1 = getInvoice(rs.getString(9));
				commission.setInvoiceNo(rs.getString(1));
				String invoiceTo = rs.getString(2);
				if(invoiceTo.equals("Client")){
					invoiceTo = "Self";
				}
				commission.setInvoiceTo(invoiceTo);
				
				commission.setInvoiceDate(rs.getString(3));				
				
				//Commission commission2 = getPaymentMode(rs.getString(9));
				commission.setPaymode(rs.getString(4));
				commission.setPaidDate(rs.getString(5));
				
				}
				else{
					commission.setInvoiceNo("");
					commission.setInvoiceTo("");
					commission.setInvoiceDate("");					
					commission.setPaymode("");
					commission.setPaidDate("");
				}
				int commssionFee = getCommissionFee(rs.getString(9),appointmentId);
				commission.setCommssionFee(commssionFee);
				
				list.add(commission);
				
			}*/
			
		}catch(Exception e){
			e.printStackTrace();
		}
		return list;
	}


	private String getPaidDate(String clientid) {
		PreparedStatement preparedStatement = null;
		String result = "";
		String sql = "select date from apm_charges_payment where clientid = "+clientid+"";
		try{
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()){
				result = rs.getString(1);
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
		return result;
	}

	private String getPaymode(String clientid) {
		PreparedStatement preparedStatement = null;
		String result = "";
		String sql = "select paymode from apm_charges_payment where clientid = "+clientid+"";
		try{
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()){
				result = rs.getString(1);
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
		return result;
	}

	private String getInvoiceDate(String clientid) {
		PreparedStatement preparedStatement = null;
		String result = "";
		String sql = "select date from apm_charges_invoice where clientid = "+clientid+"";
		try{
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()){
				result = rs.getString(1);
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
		return result;
	}

	private String getInvoiceTo(String clientid) {
		PreparedStatement preparedStatement = null;
		String result = "";
		String sql = "select payby from apm_charges_invoice where clientid = "+clientid+"";
		try{
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()){
				result = rs.getString(1);
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
		return result;
	}

	private String getInvoiceNo(String clientid) {
		PreparedStatement preparedStatement = null;
		String result = "";
		String sql = "select id from apm_charges_invoice where clientid = "+clientid+"";
		try{
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()){
				result = rs.getString(1);
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
		return result;
	}

	private String getAppointmentType(String appTypeId) {
		PreparedStatement preparedStatement = null;
		String result = "";
		String sql = "select name from apm_appointment_type where id = "+appTypeId+"";
		try{
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()){
				result = rs.getString(1);
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
		return result;
	}

	private Double getCommissionFee(String clientId,String appointmentId) {
		PreparedStatement preparedStatement = null;
		Double result = 0.0;
		String sql = "select practitionerid,chargetype from apm_invoice where clientid = "+clientId+" and appointmentid = "+appointmentId+"";
		try{
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()){
				int pid = rs.getInt(1);
				String chargeType = rs.getString(2);
				
				if(chargeType.equals("DNA")){
					result = getPractitionerDNACharge(pid);
					
				}else if(chargeType.equals("Charge") || chargeType.equals("Submit")){
					result = PractitionerCACharge(pid);
					
				}
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
		return result;
	}

	/*private Commission getInvoice(String clientid) {
		PreparedStatement preparedStatement = null;
		Commission commission1 = new Commission();
		String sql = "select id,payby,date from apm_charges_invoice where clientid = "+clientid+"";
		try{
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()){				
				commission1.setInvoiceNo(rs.getString(1));
				commission1.setInvoiceTo(rs.getString(2)); 
				commission1.setInvoiceDate(rs.getString(3));
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
		return commission1;
	}

	private Commission getPaymentMode(String clientid) {
		PreparedStatement preparedStatement = null;
		Commission commission2 = new Commission();
		String sql = "select paymode,date from apm_charges_payment where clientid = "+clientid+"";
		try{
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()){				
				commission2.setPaymode(rs.getString(1));
				commission2.setPaidDate(rs.getString(2)); 
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
		return commission2;
	}*/


	private Double getAppCharge(String clientId,String appointmentId) {
		PreparedStatement preparedStatement = null;
		Double result = 0.0;
		String sql = "select SUM(charge) from apm_invoice_assesments where clientId = "+clientId+" and appointmentid = "+appointmentId+"";
		try{
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()){
				result = rs.getDouble(1);
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
		return result;
	}

	private String getAppointmentStatus(String clientId,String appointmentId) {
		PreparedStatement preparedStatement = null;
		String result = "";
		String sql = "select chargetype from apm_invoice where clientid = "+clientId+" and appointmentid = "+appointmentId+"";
		try{
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			if(rs.next()){
				result = rs.getString(1);
				
				if(result.equals("Charge") || result.equals("Submit")){
					result = "Completed";
				}else if(result.equals("DNA")){
					result = "DNA";
				}else{
				    result = getIncompleteApp(clientId);
					
				}
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
		return result;
	}

	private String getIncompleteApp(String clientId) {
		PreparedStatement preparedStatement = null;
		String result ="";
		String sql = "select apm_available_slot.id from apm_available_slot where not exists "
				+ "(select apm_invoice.appointmentid from apm_invoice where apm_available_slot.id = "
				+ "apm_invoice.appointmentid) and apm_available_slot.clientId = "+clientId+"";
		try{
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()){
				result = "Incomplete";
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
		return result;
	}
	

	private int getTotalInvoicePaid(String userId,String fromDate,String toDate) {
		PreparedStatement preparedStatement = null;
		int result = 0;
		int id = 0,debit = 0,payment = 0,paid=0;
		/*String sql = "SELECT * FROM apm_charges_invoice inner join apm_charges_assesment on "
						+ "apm_charges_invoice.id = apm_charges_assesment.chargeinvoiceid inner join apm_invoice "
						+ "on apm_invoice.id = apm_charges_assesment.invoiceid where apm_invoice.practitionerid = "+userId+" "
						+ "group by apm_charges_invoice.id";*/
		
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT * FROM apm_charges_invoice inner join apm_charges_assesment on ");
		sql.append("apm_charges_invoice.id = apm_charges_assesment.chargeinvoiceid inner join apm_invoice on ");
		sql.append("apm_invoice.id = apm_charges_assesment.invoiceid ");
		sql.append("inner join apm_available_slot on apm_available_slot.id = apm_invoice.appointmentid ");
		sql.append("where apm_invoice.practitionerid = "+userId+" ");
		if(!fromDate.equals("") && !toDate.equals("")){	
			sql.append("and apm_available_slot.commencing between '"+fromDate+"' and '"+toDate+"' ");
		}
		sql.append("group by apm_charges_invoice.id ");
		
		try{
			preparedStatement = connection.prepareStatement(sql.toString());
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()){
			
				 id = rs.getInt(1);
				 debit = rs.getInt(5);
				 payment = getPayment(id);	
				 
				 paid = (debit - payment);
					if(paid == 0){
						result = result + 1;
					}				
			}			
		}catch(Exception e){
			e.printStackTrace();
		}
		return result;
	}

	private int getPayment(int id) {
		PreparedStatement preparedStatement = null;
		int result = 0;
		String sql = "select payment from apm_charges_payment where chargeinvoiceid = "+id+"";
		try{
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()){
				result = rs.getInt(1);
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return result;
	}

	private ArrayList<Commission> getTotalChargeInvoiced(String userId,String fromDate,String toDate) {
		PreparedStatement preparedStatement = null;
		//Commission commission ;
		ArrayList<Commission> list = new ArrayList<Commission>();
		/*String sql = "SELECT count(*) FROM apm_charges_invoice inner join apm_charges_assesment on "
						+ "apm_charges_invoice.id = apm_charges_assesment.chargeinvoiceid inner join apm_invoiuce "
						+ "on apm_invoice.id = apm_charges_assesment.invoiceid where apm_invoice.practitionerid = "+userId+" "
						+ "group by apm_charges_invoice.id";*/
		
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT count(*) FROM apm_charges_invoice inner join apm_charges_assesment on ");
		sql.append("apm_charges_invoice.id = apm_charges_assesment.chargeinvoiceid inner join apm_invoice on ");
		sql.append("apm_invoice.id = apm_charges_assesment.invoiceid ");
		sql.append("inner join apm_available_slot on apm_available_slot.id = apm_invoice.appointmentid ");
		sql.append("where apm_invoice.practitionerid = "+userId+" ");
		
		if(!fromDate.equals("") && !toDate.equals("")){	
			sql.append("and apm_available_slot.commencing between '"+fromDate+"' and '"+toDate+"' ");
		}
		
		sql.append("group by apm_charges_invoice.id ");
		try{
			preparedStatement = connection.prepareStatement(sql.toString());
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()){
				Commission commission = new Commission();
				commission.setId(rs.getInt(1));
				
				list.add(commission);
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
		return list;
	}

	private int getTotalIncompleteApp(String userId,String fromDate,String toDate) {
		PreparedStatement preparedStatement = null;
		int result = 0;
		//String sql = "select count(apm_available_slot.id) from apm_available_slot where not exists (select apm_invoice.appointmentid from apm_invoice where apm_available_slot.id = apm_invoice.appointmentid) and apm_available_slot.diaryuserid = "+userId+" and apm_available_slot.status=0 ";
		String sql = "select count(apm_available_slot.id) from apm_available_slot where apm_available_slot.diaryuserid = "+userId+" and apm_available_slot.status=0 ";

		if(!fromDate.equals("") && !toDate.equals("")){	

			sql = sql + "and commencing between '"+fromDate+"' and '"+toDate+"' ";
		}
		
		//String sql = "select count(apm_available_slot.id) from apm_available_slot where not exists (select apm_invoice.appointmentid from apm_invoice where apm_available_slot.id = apm_invoice.appointmentid and practitionerid = "+userId+")";
		try{
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()){
				result = rs.getInt(1);
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
		return result;
	}

	private int getTotalCompletedApp(String userId,String fromDate,String toDate) {
		PreparedStatement preparedStatement = null;
		ArrayList<String> list = new ArrayList<String>();
		String result = "";
		int result1 = 0;
		StringBuffer sql = new StringBuffer();
		sql.append("select apm_invoice.id from apm_invoice inner join apm_available_slot on ");
		sql.append("apm_available_slot.id = apm_invoice.appointmentid ");
		sql.append("where practitionerid = "+userId+" and (chargetype = 'Charge' or chargetype = 'Submit') ");
		if(!fromDate.equals("") && !toDate.equals("")){	
			sql.append("and commencing between '"+fromDate+"' and '"+toDate+"' ");
		}
		
		sql.append("group by apm_available_slot.id ");
		
		try{
			preparedStatement = connection.prepareStatement(sql.toString());
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()){
				result = rs.getString(1);
				
				list.add(result);
			}
			result1 = list.size();
			
		}catch(Exception e){
			e.printStackTrace();
		}
		return result1;
	}

	private int getTotalDNA(String userId,String fromDate,String toDate) {
		PreparedStatement preparedStatement = null;
		int result = 0;
		String sql = "SELECT count(*) FROM apm_available_slot where diaryuserid = "+userId+" and status = 0 and dna = 1 ";
		if(!fromDate.equals("") && !toDate.equals("")){	
			sql = sql + "and commencing between '"+fromDate+"' and '"+toDate+"' ";
		}

			
		try{
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()){
				result = rs.getInt(1);
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
		return result;
	}

	private String getClinicLocation(String locationId) {
		PreparedStatement preparedStatement = null;
		String result = "";
		String sql = "select location from apm_clinic_location where id = "+locationId+"";
		try{
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()){
				result = rs.getString(1);
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
		return result;
	}

	private int getTotalAppointNo(String userId,String fromDate,String toDate) {
		PreparedStatement preparedStatement = null;
		int result = 0;
		
		String sql = "select count(*) from apm_available_slot where diaryuserid = "+userId+" and status=0 ";
		if(!fromDate.equals("") && !toDate.equals("")){	
			sql = sql + "and commencing between '"+fromDate+"' and '"+toDate+"' ";
		}
	
		try{
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()){
				result = rs.getInt(1);
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
		return result;
	}

	/*public ArrayList<Commission> getCommissionList(int practitionerId) {
		PreparedStatement preparedStatement = null;
		ArrayList<Commission> list = new ArrayList<Commission>();
		int appointmentId = getAppointmentId(practitionerId);
		String result = IsAppointmentPresent(appointmentId);
		int totalCharge = 0,commissionCharge = 0,practCompAppCharge = 0;
		int practDNACharge = 0;
		
		String sql = "select apm_available_slot.id,apm_available_slot.commencing,apm_available_slot.clientname,"
				+ "apm_invoice.chargetype from apm_available_slot "
				+ "inner join apm_invoice on apm_available_slot.id = apm_invoice.appointmentid where "
				+ "(apm_invoice.chargetype = 'DNA' or apm_invoice.chargetype = 'Charge') and apm_invoice.practitionerid = "+practitionerId+"";
		try{
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()){
				Commission commission = new Commission();
				commission.setAppointmentId(rs.getInt(1));
				commission.setCommensing(rs.getString(2));
				commission.setClientName(rs.getString(3));
				//commission.setCharge(rs.getString(4));
				commission.setChargeType(rs.getString(4));
				String chargeType = rs.getString(4);
				
				String Charge = getTotalCharges(rs.getInt(1));				
				commission.setCharge(Charge);
				
				int charge1 = Integer.parseInt(Charge);
				totalCharge = charge1+totalCharge;
								
				if(chargeType.equals("DNA")){
					 practDNACharge = getPractitionerDNACharge(practitionerId);
					 commissionCharge = (totalCharge * practDNACharge)/100;
				}
				else if(chargeType.equals("Charge")){
					practCompAppCharge = PractitionerCACharge(practitionerId);
					commissionCharge = (totalCharge * practCompAppCharge)/100;
				}
				commission.setCommissionCharge(commissionCharge);
				commission.setTotalCharge(totalCharge);
				commission.setCACharge(practCompAppCharge);
				commission.setDNACharge(practDNACharge);
				
				list.add(commission);
				
			}		
						
			
		}catch(Exception e){
			e.printStackTrace();
		}
		return list;
	}
*/
	private Double PractitionerCACharge(int practitionerId) {
		PreparedStatement preparedStatement = null;
		Double result = 0.0;
		String sql = "select completeAppCharge from apm_user where id = "+practitionerId+"";
		try{
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()){
				result = rs.getDouble(1);
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
		return result;
	}

	private Double getPractitionerDNACharge(int practitionerId) {
		PreparedStatement preparedStatement = null;
		Double result = 0.0;
		String sql = "select dnaCharge from apm_user where id = "+practitionerId+"";
		try{
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()){
				result = rs.getDouble(1);
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
		return result;
	}

	private Double getTotalCharges(int userId,String fromDate,String toDate) {
		PreparedStatement preparedStatement = null;
		Double result = 0.0;
		//String sql = "select SUM(charge) from apm_invoice_assesments where practitionerId = "+userId+"";
		
		StringBuffer sql = new StringBuffer();
			sql.append("select SUM(apm_invoice_assesments.charge) from apm_invoice_assesments inner join apm_invoice on ");
			sql.append("apm_invoice.id = apm_invoice_assesments.invoiceid ");
			sql.append("inner join apm_available_slot on apm_available_slot.id = apm_invoice.appointmentid ");
			sql.append("where apm_available_slot.diaryuserid = "+userId+" ");
			
			if(!fromDate.equals("") && !toDate.equals("")){	
				sql.append("and apm_available_slot.commencing between '"+fromDate+"' and '"+toDate+"'  ");
			}
			
		
		try{
			preparedStatement = connection.prepareStatement(sql.toString());
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()){
				result = rs.getDouble(1);
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
		return result;
	}

/*	private String IsAppointmentPresent(int appointmentId) {
		PreparedStatement preparedStatement = null;
		String result = "";
		String sql = "select appointmentid from apm_invoice where appointmentid = "+appointmentId+"";
		try{
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()){
				result = rs.getString(1);
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
		return result;
	}

	private int getAppointmentId(int practitionerId) {
		PreparedStatement preparedStatement = null;
		int result = 0;
		String sql = "select id from apm_available_slot where diaryuserid = "+practitionerId+"";
		try{
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()){
				result = rs.getInt(1);
			}
			
			
		}catch(Exception e){
			e.printStackTrace();
		}
		return result;
	}*/

	public int getTotalCommissionCount() {
		PreparedStatement preparedStatement = null;
		int result = 0;
		String sql = "select count(*) from apm_available_slot";
		try{
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()){
				result = rs.getInt(1);
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
		return result;
	}

	public ArrayList<Commission> getVisitingChargeList(String fromDate, String toDate, String diaryUser,
			String location) {
		PreparedStatement preparedStatement = null;
		ArrayList<Commission>list = new ArrayList<Commission>();
		StringBuffer sql = new StringBuffer();
		
		toDate = toDate + " 23:59:59";
		
		/*sql.append("SELECT ipd_addmission_form.practitionerid,sum(charge*quantity),ipd_addmission_form.department ");
		sql.append("FROM apm_invoice_assesments inner join ipd_addmission_form ");
		sql.append("on ipd_addmission_form.clientid = apm_invoice_assesments.clientid ");
		sql.append("where masterchargetype = 'IPD Visiting Charge'  ");
		sql.append("and admissiondsate between '"+fromDate+"' and '"+toDate+"' ");
		
		if(diaryUser!=null){
			if(!diaryUser.equals("0")){
				sql.append(" and ipd_addmission_form.practitionerid="+diaryUser+" ");
			}
		}
		
		sql.append(" group by ipd_addmission_form.practitionerid ");*/
		sql.append("SELECT practitionerid,sum(charge*quantity),discipline,department FROM apm_invoice_assesments ");
		sql.append("inner join apm_discipline on apm_discipline.id =apm_invoice_assesments.department ");
		//sql.append("where masterchargetype = 'IPD Visiting Charge' ");
		sql.append("where apmtType = 'CONSULTATION CHARGES' ");
		sql.append("and commencing between '"+fromDate+"' and '"+toDate+"' ");
		if(diaryUser!=null){
			if(!diaryUser.equals("0")){
				sql.append(" and practitionerid="+diaryUser+" ");
			}
		}
		sql.append("group by practitionerid ");
		try{
			UserProfileDAO userProfileDAO = new JDBCUserProfileDAO(connection);
			preparedStatement = connection.prepareStatement(sql.toString());
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()){
				Commission commission = new Commission();
				UserProfile userProfile = userProfileDAO.getUserprofileDetails(rs.getInt(1));
				commission.setPractitionerName(userProfile.getFullname());
				
				double revanue = rs.getDouble(2);
				double per=0;
				if(userProfile.getVivitingper()!=null){
					if(!userProfile.getVivitingper().equals("0")){
						per = Double.parseDouble(userProfile.getVivitingper());
					}
					
				}
				
				
				double shareamt = (revanue * per) / 100;
				
				commission.setPer(Double.toString(per));
				commission.setRevanue(DateTimeUtils.changeFormat(revanue));
				commission.setShareamt(DateTimeUtils.changeFormat(shareamt));
				
				commission.setClinicLocation(rs.getString(3));
				
				
				list.add(commission);
				
				
			}
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		
		return list;
	}

	public ArrayList<Commission> getIpdConsultingList(String fromDate, String toDate, String diaryUser,
			String location) {
		PreparedStatement preparedStatement = null;
		ArrayList<Commission>list = new ArrayList<Commission>();
		StringBuffer sql = new StringBuffer();
		
		/*sql.append("SELECT ipd_addmission_form.practitionerid,sum(charge*quantity),ipd_addmission_form.department ");
		sql.append("FROM apm_invoice_assesments inner join ipd_addmission_form ");
		sql.append("on ipd_addmission_form.clientid = apm_invoice_assesments.clientid ");
		sql.append("where apmtType = 'Consultation Charges' group by ipd_addmission_form.practitionerid ");*/
		
	   toDate = toDate + " 23:59:59";
		
		sql.append("SELECT ipd_addmission_form.practitionerid,sum(charge*quantity),ipd_addmission_form.department ");
		sql.append("FROM apm_invoice_assesments inner join ipd_addmission_form ");
		sql.append("on ipd_addmission_form.clientid = apm_invoice_assesments.clientid ");
		sql.append("where apmtType = 'Consultation Charges'  ");
		sql.append("and admissiondsate between '"+fromDate+"' and '"+toDate+"' ");
		
		if(diaryUser!=null){
			if(!diaryUser.equals("0")){
				sql.append(" and ipd_addmission_form.practitionerid="+diaryUser+" ");
			}
		}
		
		sql.append(" group by ipd_addmission_form.practitionerid ");
		
		try{
			UserProfileDAO userProfileDAO = new JDBCUserProfileDAO(connection);
			preparedStatement = connection.prepareStatement(sql.toString());
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()){
				Commission commission = new Commission();
				UserProfile userProfile = userProfileDAO.getUserprofileDetails(rs.getInt(1));
				commission.setPractitionerName(userProfile.getFullname());
				
				double revanue = rs.getDouble(2);
				double per = Double.parseDouble(userProfile.getVivitingper());
				double shareamt = (revanue * per) / 100;
				
				commission.setPer(Double.toString(per));
				commission.setRevanue(DateTimeUtils.changeFormat(revanue));
				commission.setShareamt(DateTimeUtils.changeFormat(shareamt));
				
				commission.setClinicLocation(rs.getString(3));
				
				
				list.add(commission);
				
			}
		}catch (Exception e) {
			// TODO: handle exception
		}
		
		
		return list;
	}

	public ArrayList<Commission> getSurgeonChargeList(String fromDate, String toDate, String diaryUser,
			String location) {
		PreparedStatement preparedStatement = null;
		ArrayList<Commission>list = new ArrayList<Commission>();
		StringBuffer sql = new StringBuffer();
		
		sql.append("select surgeon, sum(psurcharge),location from apm_available_slot where procedures!='0'  ");
		sql.append("and commencing between '"+fromDate+"' AND '"+toDate+"' ");
		
		if(diaryUser!=null){
			if(!diaryUser.equals("0")){
				sql.append(" and surgeon="+diaryUser+" ");
			}
		}
		
		sql.append(" group by surgeon ");
		
		try{
			UserProfileDAO userProfileDAO = new JDBCUserProfileDAO(connection);
			preparedStatement = connection.prepareStatement(sql.toString());
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()){
				Commission commission = new Commission();
				UserProfile userProfile = userProfileDAO.getUserprofileDetails(rs.getInt(1));
				commission.setPractitionerName(userProfile.getFullname());
				
				double revanue = rs.getDouble(2);
				//double per = Double.parseDouble(userProfile.getSurgeonper());  //commented because share not show 
				double per = (userProfile.getSurgeonCharge());
				double shareamt = (revanue * per) / 100;
				
				commission.setPer(Double.toString(per));
				commission.setRevanue(DateTimeUtils.changeFormat(revanue));
				commission.setShareamt(DateTimeUtils.changeFormat(shareamt));
				
				String clinicLocation = getClinicLocation(rs.getString(3));
				commission.setClinicLocation(clinicLocation);
				
				
				list.add(commission);
				
			}
		}catch (Exception e) {
			// TODO: handle exception
		}
		
		
		return list;
	}

	public ArrayList<Commission> getPractitionerShareList(String fromDate, String toDate, String diaryuser,
			String location) {
		if(location==null){
			location="0";
		}
		PreparedStatement preparedStatement = null;
		ArrayList<Commission> list = new ArrayList<Commission>();
		//  New practitioner share report 02 April 2018
		StringBuffer buffer = new StringBuffer();
		buffer.append("SELECT count(apm_invoice_assesments.id),sum(apm_invoice_assesments.charge),concat(initial,' ',firstname,' ',lastname),vivitingper,((vivitingper*sum(apm_invoice_assesments.charge))/100),apm_user.id ");
		buffer.append("FROM apm_invoice_assesments ");
		buffer.append("inner join apm_invoice on apm_invoice_assesments.invoiceid = apm_invoice.id ");
		buffer.append("inner join apm_available_slot on apm_available_slot.id = apm_invoice_assesments.appointmentid ");
		buffer.append("inner join apm_user on apm_available_slot.diaryuserid = apm_user.id ");
		buffer.append("where apm_invoice_assesments.appointmentid!=0 and apm_invoice.chargetype='Submit' and apm_available_slot.procedures='0' and masterchargetype ='Appointment Charge' ");
		buffer.append("and apm_available_slot.commencing between '"+fromDate+"' AND '"+toDate+"' ");
		if(!location.equals("0")){
			buffer.append("and  apm_available_slot.location="+location+" ");
		}
		if(!diaryuser.equals("0")){
			buffer.append("and apm_available_slot.diaryuserid="+diaryuser+" ");
		}
		buffer.append("group by apm_invoice_assesments.practitionerid order by apm_user.id ");
		
		try{
			preparedStatement = connection.prepareStatement(buffer.toString());
			ResultSet rs = preparedStatement.executeQuery();
			int x=0;
			while(rs.next()){
				Commission commission = new Commission();
				commission.setTotalAppNo(rs.getInt(1));
				commission.setTotalCharge(rs.getString(2));
				commission.setPractitionerName(rs.getString(3));
				commission.setCACharge(rs.getString(4));
				commission.setTotalCommission(rs.getString(5));
				commission.setPractitionerId(rs.getString(6));
				x=x+rs.getInt(1);
				commission.setId(x);
				list.add(commission);
				
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
		return list;
	}

}
