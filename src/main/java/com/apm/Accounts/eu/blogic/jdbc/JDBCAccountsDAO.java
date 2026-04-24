package com.apm.Accounts.eu.blogic.jdbc;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Vector;

import org.apache.struts2.convention.annotation.Results;

import com.a.a.a.b.i;
import com.apm.Accounts.eu.bi.AccountsDAO;
import com.apm.Accounts.eu.bi.ChargesAccountProcessingDAO;
import com.apm.Accounts.eu.bi.StatementDAO;
import com.apm.Accounts.eu.entity.Accounts;
import com.apm.Accounts.eu.entity.ChargesInvoice;
import com.apm.Accounts.eu.entity.Invoice;
import com.apm.Appointment.eu.entity.Appointment;
import com.apm.Appointment.eu.entity.AppointmentType;
import com.apm.AssesmentForms.eu.bi.AssessmentFormDAO;
import com.apm.AssesmentForms.eu.blogic.jdbc.JDBCAssessmentFormDAO;
import com.apm.DiaryManagement.eu.bi.ClientDAO;
import com.apm.DiaryManagement.eu.blogic.jdbc.JDBCClientDAO;
import com.apm.DiaryManagement.eu.entity.Client;
import com.apm.DiaryManagement.eu.entity.CompleteAppointment;
import com.apm.DiaryManagement.eu.entity.DiaryManagement;
import com.apm.DiaryManagement.eu.entity.NotAvailableSlot;
import com.apm.DiaryManagement.eu.entity.Priscription;
import com.apm.DiaryManagement.web.common.ApmDate;
import com.apm.Emr.eu.bi.InvestigationDAO;
import com.apm.Emr.eu.blogic.jdbc.JDBCInvestigationDAO;
import com.apm.Emr.eu.entity.Investigation;
import com.apm.Ipd.eu.bi.BedDao;
import com.apm.Ipd.eu.bi.IpdDAO;
import com.apm.Ipd.eu.blogic.jdbc.JDBCBedDao;
import com.apm.Ipd.eu.blogic.jdbc.JDBCIpdDAO;
import com.apm.Ipd.eu.entity.Bed;
import com.apm.Log.eu.entity.LogDetail;
import com.apm.Master.eu.bi.MasterDAO;
import com.apm.Master.eu.blogic.jdbc.JDBCMasterDAO;
import com.apm.Master.eu.entity.Master;
import com.apm.Pharmacy.web.action.Tra;
import com.apm.Registration.eu.bi.UserProfileDAO;
import com.apm.Registration.eu.blogic.jdbc.JDBCUserProfileDAO;
import com.apm.Registration.eu.entity.Clinic;
import com.apm.Registration.eu.entity.UserProfile;
import com.apm.ThirdParties.eu.bi.ThirdPartyDAO;
import com.apm.ThirdParties.eu.blogic.jdbc.JDBCThirdPartyDAO;
import com.apm.ThirdParties.eu.entity.ThirdParty;
import com.apm.common.eu.blogic.jdbc.Connection_provider;
import com.apm.common.eu.blogic.jdbc.JDBCBaseDAO;
import com.apm.common.utils.DateTimeUtils;
import com.apm.common.utils.Pagination;
import com.apm.common.web.common.helper.LoginInfo;
import com.apm.main.common.constants.Constants;


import ij.measure.ResultsTable;
import oracle.jdbc.util.Login;

public class JDBCAccountsDAO extends JDBCBaseDAO implements AccountsDAO{
	
	public JDBCAccountsDAO(Connection connection){
		this.connection = connection;
	}

	public ArrayList<Accounts> getAccountList(String clientId,String payby,Pagination pagination,String transactionType, 
			String location, String thirdParty,String raiseChargeType,String fromdate,String todate, String newipdid,String addOnTableName) {
		
		double debitTotal=0.0;
		double creditTotal = 0.0;
		double balanceTotal = 0.0;
		String selctedTransType="";
		DateTimeUtils dateTimeUtils = new DateTimeUtils();
		PreparedStatement preparedStatement = null;
		ArrayList<Accounts>list = new ArrayList<Accounts>();
		StringBuffer sql = new StringBuffer();
		String pending= "Pending Payment";String paid = "Payments";
		
		if(pagination.sortColumn==null){
			if(payby.equals(Constants.PAY_BY_CLIENT)){
				sql.append("SELECT commencing,user,apmtType,sum(charge),invoiceid,paid,count(apm_invoice_assesments.id),apm_invoice.practitionerid,apm_invoice_assesments.paybuy,apm_invoice_assesments.clientId,chargetype,howpaid,apm_invoice.appointmentid,location,apm_invoice.apmt_charge_type,apm_invoice.policy_excess FROM apm_invoice_assesments"+addOnTableName+" as apm_invoice_assesments ");
				sql.append("inner join apm_invoice on apm_invoice.id = apm_invoice_assesments.invoiceid ");
				sql.append("where  apm_invoice_assesments.clientid="+clientId+" and apm_invoice_assesments.paybuy = 0 and apm_invoice.chargetype like('%"+transactionType+"%') and apm_invoice.location like ('%"+location+"%') and apm_invoice.apmt_charge_type like ('%"+raiseChargeType+"%') ");
				sql.append("and apm_invoice_assesments.active=1 and commencing between '"+fromdate+"' and '"+todate+"' ");
				if(!newipdid.equals("")){
					sql.append("  and apm_invoice_assesments.ipdid='"+newipdid+"'  ");
				}
				sql.append("group by invoiceid order by apm_invoice.id desc ");
				
			}else if(payby.equals(Constants.PAY_BY_THIRD_PARTY)){
				sql.append("SELECT commencing,user,apmtType,sum(charge),invoiceid,paid,count(apm_invoice_assesments.id),apm_invoice.practitionerid,apm_invoice_assesments.paybuy,apm_invoice_assesments.clientId,chargetype,howpaid,apm_invoice.appointmentid,location,apm_invoice.apmt_charge_type,apm_invoice.policy_excess FROM apm_invoice_assesments"+addOnTableName+" as apm_invoice_assesments ");
				sql.append("inner join apm_invoice on apm_invoice.id = apm_invoice_assesments.invoiceid ");
				sql.append("where  apm_invoice_assesments.clientid="+clientId+" and apm_invoice_assesments.paybuy = 1 and apm_invoice.chargetype like('%"+transactionType+"%') and apm_invoice.location like ('%"+location+"%') and apm_invoice_assesments.thirdPartyId like ('"+thirdParty+"%') and apm_invoice.apmt_charge_type like ('%"+raiseChargeType+"%')");
				sql.append("and apm_invoice_assesments.active=1 and commencing between '"+fromdate+"' and '"+todate+"' ");
				if(!newipdid.equals("")){
					sql.append(" and apm_invoice_assesments.ipdid='"+newipdid+"'  ");
				}
				sql.append("group by invoiceid order by apm_invoice.id desc ");
			}else{
				if(location.equals("")){
					sql.append("SELECT commencing,user,apmtType,sum(charge),invoiceid,paid,count(apm_invoice_assesments.id),apm_invoice.practitionerid,apm_invoice_assesments.paybuy,apm_invoice_assesments.clientId,chargetype,howpaid,apm_invoice.appointmentid,location,apm_invoice.apmt_charge_type,apm_invoice.policy_excess  FROM apm_invoice_assesments"+addOnTableName+" as apm_invoice_assesments ");
					sql.append("inner join apm_invoice on apm_invoice.id = apm_invoice_assesments.invoiceid ");
					sql.append("where  apm_invoice_assesments.clientid="+clientId+" and apm_invoice.chargetype like('%"+transactionType+"%')  and apm_invoice_assesments.thirdPartyId like ('"+thirdParty+"%') and apm_invoice.apmt_charge_type like ('%"+raiseChargeType+"%')");
					sql.append("and apm_invoice_assesments.active=1 and commencing between '"+fromdate+"' and '"+todate+"'  ");
					if(!newipdid.equals("")){
						sql.append(" and apm_invoice_assesments.ipdid='"+newipdid+"'  ");
					}
					sql.append("group by invoiceid order by apm_invoice.id desc ");
				}else{
					sql.append("SELECT commencing,user,apmtType,sum(charge),invoiceid,paid,count(apm_invoice_assesments.id),apm_invoice.practitionerid,apm_invoice_assesments.paybuy,apm_invoice_assesments.clientId,chargetype,howpaid,apm_invoice.appointmentid,location,apm_invoice.apmt_charge_type,apm_invoice.policy_excess  FROM apm_invoice_assesments"+addOnTableName+" as apm_invoice_assesments ");
					sql.append("inner join apm_invoice on apm_invoice.id = apm_invoice_assesments.invoiceid ");
					sql.append("where  apm_invoice_assesments.clientid="+clientId+" and apm_invoice.chargetype like('%"+transactionType+"%') and apm_invoice.location like ('%"+location+"%')  and apm_invoice_assesments.thirdPartyId like ('"+thirdParty+"%') and apm_invoice.apmt_charge_type like ('%"+raiseChargeType+"%')");
					sql.append("and apm_invoice_assesments.active=1 and commencing between '"+fromdate+"' and '"+todate+"'  ");
					if(!newipdid.equals("")){
						sql.append(" and apm_invoice_assesments.ipdid='"+newipdid+"'  ");
					}
					sql.append("group by invoiceid order by apm_invoice.id desc ");
				}
				
			}
		}else{
			if(payby.equals(Constants.PAY_BY_CLIENT)){
				sql.append("SELECT commencing,user,apmtType,sum(charge),invoiceid,paid,count(apm_invoice_assesments.id),apm_invoice.practitionerid,apm_invoice_assesments.paybuy,apm_invoice_assesments.clientId,chargetype,howpaid,apm_invoice.appointmentid,location,apm_invoice.apmt_charge_type,apm_invoice.policy_excess FROM apm_invoice_assesments"+addOnTableName+" as apm_invoice_assesments ");
				sql.append("inner join apm_invoice on apm_invoice.id = apm_invoice_assesments.invoiceid ");
				sql.append("where  apm_invoice_assesments.clientid="+clientId+" and apm_invoice_assesments.paybuy = 0 and apm_invoice.chargetype like('%"+transactionType+"%') and apm_invoice.location like ('%"+location+"%')  and apm_invoice_assesments.thirdPartyId like ('"+thirdParty+"%') and apm_invoice.apmt_charge_type like ('%"+raiseChargeType+"%') ");
				sql.append("and apm_invoice_assesments.active=1 and commencing between '"+fromdate+"' and '"+todate+"' ");
				if(!newipdid.equals("")){
					sql.append(" and apm_invoice_assesments.ipdid='"+newipdid+"'  ");
				}
				sql.append("group by invoiceid ");
			}else if(payby.equals(Constants.PAY_BY_THIRD_PARTY)){
				sql.append("SELECT commencing,user,apmtType,sum(charge),invoiceid,paid,count(apm_invoice_assesments.id),apm_invoice.practitionerid,apm_invoice_assesments.paybuy,apm_invoice_assesments.clientId,chargetype,howpaid,apm_invoice.appointmentid,location,apm_invoice.apmt_charge_type,apm_invoice.policy_excess FROM apm_invoice_assesments"+addOnTableName+" as apm_invoice_assesments ");
				sql.append("inner join apm_invoice on apm_invoice.id = apm_invoice_assesments.invoiceid ");
				sql.append("where  apm_invoice_assesments.clientid="+clientId+" and apm_invoice_assesments.paybuy = 1 and apm_invoice.chargetype like('%"+transactionType+"%') and apm_invoice.location like ('%"+location+"%')  and apm_invoice_assesments.thirdPartyId like ('"+thirdParty+"%') and apm_invoice.apmt_charge_type like ('%"+raiseChargeType+"%')");
				sql.append("and apm_invoice_assesments.active=1 and commencing between '"+fromdate+"' and '"+todate+"' ");
				if(!newipdid.equals("")){
					sql.append(" and apm_invoice_assesments.ipdid='"+newipdid+"'  ");
				}
				sql.append("group by invoiceid ");
			}else{
				if(location.equals("")){
					sql.append("SELECT commencing,user,apmtType,sum(charge),invoiceid,paid,count(apm_invoice_assesments.id),apm_invoice.practitionerid,apm_invoice_assesments.paybuy,apm_invoice_assesments.clientId,chargetype,howpaid,apm_invoice.appointmentid,location,apm_invoice.apmt_charge_type,apm_invoice.policy_excess  FROM apm_invoice_assesments"+addOnTableName+" as apm_invoice_assesments ");
					sql.append("inner join apm_invoice on apm_invoice.id = apm_invoice_assesments.invoiceid ");
					sql.append("where  apm_invoice_assesments.clientid="+clientId+" and apm_invoice.chargetype like('%"+transactionType+"%')  and apm_invoice_assesments.thirdPartyId like ('"+thirdParty+"%') and apm_invoice.apmt_charge_type like ('%"+raiseChargeType+"%')");
					sql.append("and apm_invoice_assesments.active=1 and commencing between '"+fromdate+"' and '"+todate+"' ");
					if(!newipdid.equals("")){
						sql.append(" and apm_invoice_assesments.ipdid='"+newipdid+"'  ");
					}
					sql.append("group by invoiceid order by apm_invoice.id desc ");
				}else{
					sql.append("SELECT commencing,user,apmtType,sum(charge),invoiceid,paid,count(apm_invoice_assesments.id),apm_invoice.practitionerid,apm_invoice_assesments.paybuy,apm_invoice_assesments.clientId,chargetype,howpaid,apm_invoice.appointmentid,location,apm_invoice.apmt_charge_type,apm_invoice.policy_excess  FROM apm_invoice_assesments"+addOnTableName+" as apm_invoice_assesments ");
					sql.append("inner join apm_invoice on apm_invoice.id = apm_invoice_assesments.invoiceid ");
					sql.append("where  apm_invoice_assesments.clientid="+clientId+" and apm_invoice.chargetype like('%"+transactionType+"%') and apm_invoice.location like ('%"+location+"%')  and apm_invoice_assesments.thirdPartyId like ('"+thirdParty+"%') and apm_invoice.apmt_charge_type like ('%"+raiseChargeType+"%')");
					sql.append("and apm_invoice_assesments.active=1 and commencing between '"+fromdate+"' and '"+todate+"' ");
					if(!newipdid.equals("")){
						sql.append(" and apm_invoice_assesments.ipdid='"+newipdid+"'  ");
					}
					sql.append("group by invoiceid order by apm_invoice.id desc ");
				}
			}
		}
		
		
		
		String sql1 = sql.toString(); 
		sql1 = pagination.getSQLQuery(sql.toString());
		
		try{
			preparedStatement = connection.prepareStatement(sql1);
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()){
				Accounts accounts = new Accounts();
				accounts.setCommencing(dateTimeUtils.changeDateFormatToddmmyyyy(rs.getString(1)));
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
				}
				else{
					accounts.setWhoPay("Self");
				}
				accounts.setClientid(rs.getInt(10));
				accounts.setChargeType(rs.getString(11));
				accounts.setHowPaid(rs.getString(12));
				accounts.setApptId(rs.getString(13));
				
				String locationName = getLocationName(rs.getInt(14));
				accounts.setLocationName(locationName);
				accounts.setApmtChargeType(rs.getInt(15));
				accounts.setPolicyExcess(rs.getString(16));
				String treamentEpisodeName = getTreatmentEpisodeName(rs.getString(13));
				accounts.setTreatmentEpisodeName(treamentEpisodeName);
				accounts.setLocationName(locationName);
				
				
				accounts.setSelectedPayby(payby);
				accounts.setSelectedLocation(location);
				
				
				boolean chargesInvoiced = checkChargeInvoicedNew(accounts.getInvoiceid(),addOnTableName);
				accounts.setChargesInvoiced(chargesInvoiced);
				
				
				if(!chargesInvoiced){
					if(!rs.getString(11).equals("Submit")){
						double credit = getCreditAmount(rs.getString(5),rs.getString(9));
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
						
						list.add(accounts);
					}
				}
				
				
			}
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	
	private boolean checkChargeInvoicedNew(int invoiceid, String addOnTableName) {

		PreparedStatement preparedStatement = null;
		boolean result = false;
		StringBuffer sql = new StringBuffer();
		
		sql.append("SELECT apm_invoice.id FROM apm_invoice inner join apm_charges_assesment"+addOnTableName+" as apm_charges_assesment on ");
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

	private String getLocationName(int id) {
		PreparedStatement preparedStatement = null;
		String result = "";
		String sql = "SELECT location FROM apm_clinic_location where id = "+id+" ";
		
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

	public ArrayList<Accounts> getAssesmentList(int invoiceid,int payby) {
		PreparedStatement preparedStatement = null;
		DateTimeUtils dateTimeUtils = new DateTimeUtils();
		ArrayList<Accounts>list = new ArrayList<Accounts>();
		String temPayBy = "";
		if(payby == 0){
			temPayBy = Constants.PAY_BY_CLIENT;
		}else{
			temPayBy = Constants.PAY_BY_THIRD_PARTY;
		}
		
		String sql = "SELECT id,commencing,user,apmtType,charge,practitionerName,appointmentid,apmtType,practitionerId,quantity,chargeId FROM apm_invoice_assesments where invoiceid = "+invoiceid+" and paybuy = '"+payby+"' and apm_invoice_assesments.active=1 ";
		
		try{
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()){
				Accounts accounts = new Accounts();
				accounts.setId(rs.getInt(1));
				accounts.setCommencing(rs.getString(2));
				accounts.setClientName(rs.getString(3));
				accounts.setAppointmentType(rs.getString(4));
				//accounts.setCharges(dateTimeUtils.changeFormat(Double.parseDouble(rs.getString(5))* rs.getInt(10)));
				accounts.setCharges(DateTimeUtils.changeFormat(Double.parseDouble(rs.getString(5))));
				accounts.setPractitionerName(rs.getString(6));
				String treamentEpisodeName = getTreatmentEpisodeName(rs.getString(7));
				accounts.setTreatmentEpisodeName(treamentEpisodeName);
				String chargeType = getChargeTypeOfApmt(rs.getString(4));
				if(rs.getString(4).equalsIgnoreCase(Constants.POLICYEXCESS)){
					chargeType = Constants.POLICYEXCESS; 
				}
				
				if(chargeType==null){
					accounts.setChargeType("");
				}else{
					accounts.setChargeType(chargeType);
				}
				accounts.setAppointmentType(rs.getString(8));
				String practionerFullName = getPractitionerFullName(rs.getString(9));
				accounts.setPractitionerName(practionerFullName);
				
				accounts.setQuantity(rs.getInt(10));
				accounts.setChargeid(rs.getString(11));
				list.add(accounts);
			}
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		return list;
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

	public int updatePayment(String invoiceid, String payAmount,
			String howpaid, String invoiceDate,String paid) {
		
		PreparedStatement preparedStatement = null;
		int result = 0;
		String sql = "update apm_invoice set date=?,payamount=?,paid="+paid+",howpaid=? where id=?";
		
		try{
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, invoiceDate);
			preparedStatement.setString(2, payAmount);
			preparedStatement.setString(3, howpaid);
			preparedStatement.setString(4, invoiceid);
			
			result = preparedStatement.executeUpdate();
		}catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public int getPayAmount(int invoiceid) {
		PreparedStatement preparedStatement = null;
		int result = 0;
		String sql = "SELECT payamount FROM apm_invoice where id = "+invoiceid+" ";
		
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
	
	public double getPaymentAmount(int invoiceid,String paybuy){
		PreparedStatement preparedStatement = null;
		double result = 0;
		String sql = "SELECT charge,quantity FROM apm_invoice_assesments where invoiceid = "+invoiceid+" and paybuy = "+paybuy+" and active=1";
		
		try{
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()){
				double charge =  rs.getDouble(1) * rs.getInt(2);
				result =  result + charge;
			}
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}
	

	public int updatePayBy(int invoiceid, String payby) {
		PreparedStatement preparedStatement = null;
		int result = 0;
		String sql = "update apm_invoice_assesments set paybuy = "+payby+" where invoiceid = "+invoiceid+" ";
		
		try{
			preparedStatement = connection.prepareStatement(sql);
			result = preparedStatement.executeUpdate();
		}catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public int insertPayment(String invoiceid, String payAmount,
			String howpaid, String invoiceDate, String paid, String whoPay,String clientId,String payBuy) {
		PreparedStatement preparedStatement = null;
		int result = 0;
		String sql = "insert into apm_invoice_payment(invoice_id,last_update_date,payby,pay_mode,amount,clientId,payBuy,thirdPatryId) value(?,?,?,?,?,?,?,?)";
		
		try{
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, invoiceid);
			preparedStatement.setString(2, invoiceDate);
			preparedStatement.setString(3, whoPay);
			preparedStatement.setString(4, howpaid);
			preparedStatement.setString(5, payAmount);
			preparedStatement.setString(6, clientId);
			preparedStatement.setString(7, payBuy);
			if(payBuy.equalsIgnoreCase("1")){
			String thirdPartyId = getThirdPartyId(clientId);
			preparedStatement.setString(8,thirdPartyId);
			}
			else{
				preparedStatement.setString(8,"0");
			}
			result = preparedStatement.executeUpdate();
		}catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	private String getThirdPartyId(String clientId) {
		String id = null;
		PreparedStatement preparedStatement = null;
		
		String sql = "select third_party_name_id from apm_patient where id = "+clientId+"";
		try{
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()){
				
				 id = rs.getString(1);
				}
			
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		return id;
	}

	public int updateTransaction(String invoiceid,double credit,double debit) {
		PreparedStatement preparedStatement = null;
		int result = 0;
		String sql = "update apm_invoice set credit=?,debit=? where id ="+invoiceid+" ";
		
		try{
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setDouble(1, credit);
			preparedStatement.setDouble(2, debit);
			
			
			result = preparedStatement.executeUpdate();
		}catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public double getCreditAmount(String invoiceid,String payBuy) {
		PreparedStatement preparedStatement = null;
		double result = 0;
		String sql = "SELECT sum(amount) FROM apm_invoice_payment where invoice_id = "+invoiceid+" and payBuy = "+payBuy+" ";
		
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

	public String getThirdartyName(String clientId) {
		PreparedStatement preparedStatement = null;
		String result = "";
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT apm_third_party_details.company_name FROM apm_third_party_details inner join  apm_patient ");
		sql.append("on apm_patient.third_party_id = apm_third_party_details.id where apm_patient.id = "+clientId+" ");
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

	public int getTotalAccountCount(String clientId, String payby,String transactionType, String location, 
			String thirdParty,String raiseChargeType,String fromdate,String todate, String newipdid,String addOnTableName) {
		PreparedStatement preparedStatement = null;
		int result = 0;
		
	//	String sql = "SELECT count(DISTINCT invoiceid)FROM apm_invoice_assesments inner join apm_invoice on apm_invoice.id = apm_invoice_assesments.invoiceid where  apm_invoice_assesments.clientid=1 and apm_invoice_assesments.paybuy = 0";
		
		StringBuffer sql = new StringBuffer();
		
		if(payby.equals(Constants.PAY_BY_CLIENT)){
			sql.append("SELECT count(DISTINCT invoiceid) FROM apm_invoice_assesments"+addOnTableName+" as apm_invoice_assesments ");
			sql.append("inner join apm_invoice on apm_invoice.id = apm_invoice_assesments.invoiceid ");
			sql.append("where  apm_invoice_assesments.clientid="+clientId+" and apm_invoice_assesments.paybuy = 0 and apm_invoice.chargetype like('%"+transactionType+"%') and apm_invoice.location like ('%"+location+"%') and commencing between '"+fromdate+"' and '"+todate+"' and apm_invoice.apmt_charge_type like ('%"+raiseChargeType+"%') ");
		}else if(payby.equals(Constants.PAY_BY_THIRD_PARTY)){
			sql.append("SELECT count(DISTINCT invoiceid) FROM apm_invoice_assesments"+addOnTableName+" as apm_invoice_assesments ");
			sql.append("inner join apm_invoice on apm_invoice.id = apm_invoice_assesments.invoiceid ");
			sql.append("where  apm_invoice_assesments.clientid="+clientId+" and apm_invoice_assesments.paybuy = 1 and apm_invoice.chargetype like('%"+transactionType+"%') and apm_invoice.location like ('%"+location+"%') and apm_invoice_assesments.thirdPartyId like ('"+thirdParty+"%') and apm_invoice.apmt_charge_type like ('%"+raiseChargeType+"%') and commencing between '"+fromdate+"' and '"+todate+"' ");
		}else{
			sql.append("SELECT count(DISTINCT invoiceid) FROM apm_invoice_assesments"+addOnTableName+" as apm_invoice_assesments ");
			sql.append("inner join apm_invoice on apm_invoice.id = apm_invoice_assesments.invoiceid ");
			sql.append("where  apm_invoice_assesments.clientid="+clientId+" and apm_invoice.chargetype like('%"+transactionType+"%') and apm_invoice.location like ('%"+location+"%') and apm_invoice_assesments.thirdPartyId like ('"+thirdParty+"%') and apm_invoice.apmt_charge_type like ('%"+raiseChargeType+"%') and commencing between '"+fromdate+"' and '"+todate+"' ");
		}
		if(!newipdid.equals("")){
			sql.append(" and apm_invoice_assesments.ipdid='"+newipdid+"'  ");
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

	public ArrayList<Accounts> getInvoiceList(String selectedid,String invoiceid) {
		PreparedStatement preparedStatement = null;
		ArrayList<Accounts>list = new ArrayList<Accounts>();
		String sql = "SELECT * FROM apm_invoice_assesments where invoiceid="+invoiceid+" and  id  not in("+selectedid+") ";
		
		try{
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()){
				Accounts accounts = new Accounts();
				accounts.setInvoiceid(rs.getInt(1));
				list.add(accounts);
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
		
		return list;
	}

	public int setInvoiceInactive(String assesmentid) {
		PreparedStatement preparedStatement = null;
		int result = 0;
		String sql = "update apm_invoice_assesments set active=? where id = "+assesmentid+" ";
		
		try{
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, 0);
			result = preparedStatement.executeUpdate();
			
			
		}catch(Exception e){
			e.printStackTrace();
		}
		return result;
	}

	public int updateInvoiceChargeType(int invoiceid,int payby) {
		PreparedStatement preparedStatement = null;
		int result = 0;
		String action = "Invoice";
		
		
		String sql = "update apm_invoice set chargetype=? where id = "+invoiceid+" ";
		
		try{
			preparedStatement = connection.prepareStatement(sql);
			
			preparedStatement.setString(1, action);
			result = preparedStatement.executeUpdate();
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return result;
	}
	
	public int updateSubmitInvoiceChargeType(int invoiceid,String submitNotes) {
		PreparedStatement preparedStatement = null;
		int result = 0;
		String action = "Submit";
		String sql = "update apm_invoice set chargetype=?,submitnotes=? where id = "+invoiceid+" ";
		
		try{
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, action);
			preparedStatement.setString(2, submitNotes);
			result = preparedStatement.executeUpdate();
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return result;
	}

	
	public ArrayList<Accounts> gettransactionList(String clientId,String invoiceId) {
		PreparedStatement preparedStatement = null;
		
		ArrayList<Accounts>list = new ArrayList<Accounts>();
		String sql = "select invoice_id,last_update_date,pay_mode,amount from apm_invoice_payment where clientId = "+clientId+" and invoice_id = '"+invoiceId+"' ";

		try{
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()){
				Accounts accounts = new Accounts();
				accounts.setInvoiceNo(rs.getString(1));
				accounts.setDate(rs.getString(2));
				accounts.setPaymentmode(rs.getString(3));
				accounts.setAmount(rs.getDouble(4));
				list.add(accounts);
			}
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	public ArrayList<Accounts> getClientDetails(String clientId) {
		PreparedStatement preparedStatement = null;
		ArrayList<Accounts>list = new ArrayList<Accounts>();
		String sql = "SELECT title,firstname,surname,address,town,postcode,mobno,email,middlename,abrivationid,dob FROM apm_patient where id= "+clientId+" ";
		
		try{
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()){
				Accounts accounts = new Accounts();
				accounts.setInitial(rs.getString(1));
				accounts.setFirstname(rs.getString(2));
				accounts.setLastname(rs.getString(3));
				accounts.setAddress(rs.getString(4));
				accounts.setCity(rs.getString(5));
				accounts.setPostcode(rs.getString(6));
				accounts.setMobno(rs.getString(7));
				accounts.setEmail(rs.getString(8));
				accounts.setMiddlename(rs.getString(9));
				accounts.setAbrivationid(rs.getString(10));
				accounts.setDob(DateTimeUtils.getAge1(rs.getString(11)));
				list.add(accounts);
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
		
		return list;
	}

	public ArrayList<Accounts> getLocationList(int id) {
		PreparedStatement preparedStatement = null;
		ArrayList<Accounts>list = new ArrayList<Accounts>();
		String sql = "SELECT id,location,postcode,city FROM apm_clinic_location where userid = "+id+"";
		
		try{
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()){
				Accounts accounts = new Accounts();
				accounts.setId(rs.getInt(1));
				accounts.setLocation(rs.getString(4) + " " + "("+rs.getString(2)+")");
				
				
				list.add(accounts);
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
		
		return list;
	}

	public ArrayList<Accounts> getThirdPartyList(int id) {
		PreparedStatement preparedStatement = null;
		ArrayList<Accounts>list = new ArrayList<Accounts>();
		String sql = "SELECT id,company_name FROM apm_third_party_details";
		
		try{
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()){
				Accounts accounts = new Accounts();
				accounts.setId(rs.getInt(1));
				accounts.setThirdParty(rs.getString(2));
				
				list.add(accounts);
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
		
		return list;
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
			sql.append("SELECT commencing,user,apmtType,sum(charge),invoiceid,paid,count(apm_invoice_assesments.id),apm_invoice.practitionerid,apm_invoice_assesments.paybuy,apm_invoice_assesments.clientId,chargetype,howpaid,apm_invoice.appointmentid  FROM apm_invoice_assesments ");
			sql.append("inner join apm_invoice on apm_invoice.id = apm_invoice_assesments.invoiceid ");
			sql.append("where  apm_invoice_assesments.clientid="+clientId+" and apm_invoice_assesments.paybuy = 0 and apm_invoice.chargetype like('%"+transactionType+"%') and apm_invoice.location like ('%"+location+"%') and apm_invoice_assesments.thirdPartyId like ('"+thirdParty+"') ");
			sql.append("and apm_invoice_assesments.active=1 group by invoiceid ");
			
		}else if(payby.equals(Constants.PAY_BY_THIRD_PARTY)){
			sql.append("SELECT commencing,user,apmtType,sum(charge),invoiceid,paid,count(apm_invoice_assesments.id),apm_invoice.practitionerid,apm_invoice_assesments.paybuy,apm_invoice_assesments.clientId,chargetype,howpaid,apm_invoice.appointmentid FROM apm_invoice_assesments ");
			sql.append("inner join apm_invoice on apm_invoice.id = apm_invoice_assesments.invoiceid ");
			sql.append("where  apm_invoice_assesments.clientid="+clientId+" and apm_invoice_assesments.paybuy = 1 and apm_invoice.chargetype like('%"+transactionType+"%') and apm_invoice.location like ('%"+location+"%') and apm_invoice_assesments.thirdPartyId like ('"+thirdParty+"') ");
			sql.append("and apm_invoice_assesments.active=1 group by invoiceid ");
		}else{
			sql.append("SELECT commencing,user,apmtType,sum(charge),invoiceid,paid,count(apm_invoice_assesments.id),apm_invoice.practitionerid,apm_invoice_assesments.paybuy,apm_invoice_assesments.clientId,chargetype,howpaid,apm_invoice.appointmentid  FROM apm_invoice_assesments ");
			sql.append("inner join apm_invoice on apm_invoice.id = apm_invoice_assesments.invoiceid ");
			sql.append("where  apm_invoice_assesments.clientid="+clientId+" and apm_invoice.chargetype like('%"+transactionType+"%') and apm_invoice.location like ('%"+location+"%') and apm_invoice_assesments.thirdPartyId like ('"+thirdParty+"') ");
			sql.append("and apm_invoice_assesments.active=1 group by invoiceid ");
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
				accounts.setCreditCharge(rs.getString(4));
				accounts.setInvoiceid(rs.getInt(5));
				accounts.setPaid(rs.getBoolean(6));
				
				accounts.setNumberOfChages(rs.getInt(7));
				String practitionerFullName = getPractitionerFullName(rs.getString(8));
				accounts.setPractitionerName(practitionerFullName);
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
				double credit = getCreditAmount(rs.getString(5),rs.getString(9));
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
				
				boolean chargesInvoiced = checkChargeInvoiced(accounts.getInvoiceid());
				accounts.setChargesInvoiced(chargesInvoiced);
				
				if(debit!=0 && chargesInvoiced==false){
					list.add(accounts);
				}
				
			}
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		return list;
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
			sql.append("SELECT commencing,user,apmtType,sum(charge),invoiceid,paid,count(apm_invoice_assesments.id),apm_invoice.practitionerid,apm_invoice_assesments.paybuy,apm_invoice_assesments.clientId,chargetype,howpaid,apm_invoice.appointmentid  FROM apm_invoice_assesments ");
			sql.append("inner join apm_invoice on apm_invoice.id = apm_invoice_assesments.invoiceid ");
			sql.append("where  apm_invoice_assesments.clientid="+clientId+" and apm_invoice_assesments.paybuy = 0 and apm_invoice.chargetype like('%"+transactionType+"%') and apm_invoice.location like ('%"+location+"%') and apm_invoice_assesments.thirdPartyId like ('"+thirdParty+"') ");
			sql.append("and apm_invoice_assesments.active=1 group by invoiceid ");
			
		}else if(payby.equals(Constants.PAY_BY_THIRD_PARTY)){
			sql.append("SELECT commencing,user,apmtType,sum(charge),invoiceid,paid,count(apm_invoice_assesments.id),apm_invoice.practitionerid,apm_invoice_assesments.paybuy,apm_invoice_assesments.clientId,chargetype,howpaid,apm_invoice.appointmentid FROM apm_invoice_assesments ");
			sql.append("inner join apm_invoice on apm_invoice.id = apm_invoice_assesments.invoiceid ");
			sql.append("where  apm_invoice_assesments.clientid="+clientId+" and apm_invoice_assesments.paybuy = 1 and apm_invoice.chargetype like('%"+transactionType+"%') and apm_invoice.location like ('%"+location+"%') and apm_invoice_assesments.thirdPartyId like ('"+thirdParty+"') ");
			sql.append("and apm_invoice_assesments.active=1 group by invoiceid ");
		}else{
			sql.append("SELECT commencing,user,apmtType,sum(charge),invoiceid,paid,count(apm_invoice_assesments.id),apm_invoice.practitionerid,apm_invoice_assesments.paybuy,apm_invoice_assesments.clientId,chargetype,howpaid,apm_invoice.appointmentid  FROM apm_invoice_assesments ");
			sql.append("inner join apm_invoice on apm_invoice.id = apm_invoice_assesments.invoiceid ");
			sql.append("where  apm_invoice_assesments.clientid="+clientId+" and apm_invoice.chargetype like('%"+transactionType+"%') and apm_invoice.location like ('%"+location+"%') and apm_invoice_assesments.thirdPartyId like ('"+thirdParty+"') ");
			sql.append("and apm_invoice_assesments.active=1 group by invoiceid ");
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
				accounts.setCreditCharge(rs.getString(4));
				accounts.setInvoiceid(rs.getInt(5));
				accounts.setPaid(rs.getBoolean(6));
				
				accounts.setNumberOfChages(rs.getInt(7));
				String practitionerFullName = getPractitionerFullName(rs.getString(8));
				accounts.setPractitionerName(practitionerFullName);
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
				double credit = getCreditAmount(rs.getString(5),rs.getString(9));
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
				
				boolean chargesInvoiced = checkChargeInvoiced(accounts.getInvoiceid());
				accounts.setChargesInvoiced(chargesInvoiced);
				
				if(debit==0 && chargesInvoiced==false){
					list.add(accounts);
				}
				
			}
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	public int saveTempChargeAccounts(int invoiceid, String loginsessionid) {
		PreparedStatement preparedStatement = null;
		int result  = 0;
		String sql = "insert into apm_tempcharge_account(invoiceid,usersessionid) values(?,?)";
		
		try{
			
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, invoiceid);
			preparedStatement.setString(2, loginsessionid);
			result = preparedStatement.executeUpdate();
			
		}catch(Exception e){
			e.printStackTrace();
		}
		return result;
	}

	public ArrayList<Accounts> getAllChargeList(String payby, String clientid) {
		PreparedStatement preparedStatement = null;
		ArrayList<Accounts>list = new ArrayList<Accounts>();
		StringBuffer sql = new StringBuffer();
		if(payby.equals(Constants.PAY_BY_THIRD_PARTY)){
			sql.append("SELECT apm_invoice.id FROM apm_invoice inner join apm_invoice_assesments on ");
			sql.append("apm_invoice_assesments.invoiceid = apm_invoice.id where apm_invoice.clientid="+clientid+" and ");
			sql.append("apm_invoice_assesments.paybuy=1 group by invoiceid ");
		}else if(payby.equals(Constants.PAY_BY_CLIENT)){
			sql.append("SELECT apm_invoice.id FROM apm_invoice inner join apm_invoice_assesments on ");
			sql.append("apm_invoice_assesments.invoiceid = apm_invoice.id where apm_invoice.clientid="+clientid+" and ");
			sql.append("apm_invoice_assesments.paybuy=0 and apm_invoice.chargetype='Charge'  group by invoiceid ");
		}
		
		try{
			
			preparedStatement = connection.prepareStatement(sql.toString());
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()){
				Accounts accounts = new Accounts();
				accounts.setInvoiceid(rs.getInt(1));
				list.add(accounts);
			}
						
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
		
		
		return list;
	}

	public int deleteChargeAccounts(String loginsessionid) {
		PreparedStatement preparedStatement  = null;
		int result = 0;
		//String sql = "truncate apm_tempcharge_account";
		
		try{
			String sql ="delete from apm_tempcharge_account where usersessionid='"+loginsessionid+"'";
			preparedStatement = connection.prepareStatement(sql);
			result = preparedStatement.executeUpdate();
			
		}catch(Exception e){
			e.printStackTrace();
		}
		return result;
	}

	public ArrayList<Accounts> getChargeAccountPendingList(String clientId,
			String payby, Pagination pagination, String transactionType,
			String location, String thirdParty,String raiseChargeType, String fromDate, String toDate, String newipdid) {
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
			sql.append("SELECT commencing,user,apmtType,sum(charge),invoiceid,paid,count(apm_invoice_assesments.id),apm_invoice.practitionerid,apm_invoice_assesments.paybuy,apm_invoice_assesments.clientId,chargetype,howpaid,apm_invoice.appointmentid,apm_invoice.apmt_charge_type,apm_invoice.policy_excess  FROM apm_invoice_assesments ");
			sql.append("inner join apm_invoice on apm_invoice.id = apm_invoice_assesments.invoiceid ");
			sql.append("where  apm_invoice_assesments.clientid="+clientId+" and apm_invoice_assesments.paybuy = 0 and apm_invoice.chargetype like('%"+transactionType+"%') and apm_invoice.location like ('%"+location+"%')  and apm_invoice.apmt_charge_type like ('%"+raiseChargeType+"%') ");
			sql.append("and apm_invoice_assesments.active=1 ");
			//  29 March 2018/06-12-2019 after selectall in create invoice show list according to date bt this list not show according to date so added date filter 
			if(fromDate!=null && toDate!=null){
				sql.append("and commencing between '"+fromDate+"' and '"+toDate+"' ");
			}
			if(!newipdid.equals("")){
				sql.append("  and apm_invoice_assesments.ipdid='"+newipdid+"'  ");
			}
			sql.append("group by invoiceid ");
			
		}else if(payby.equals(Constants.PAY_BY_THIRD_PARTY)){
			sql.append("SELECT commencing,user,apmtType,sum(charge),invoiceid,paid,count(apm_invoice_assesments.id),apm_invoice.practitionerid,apm_invoice_assesments.paybuy,apm_invoice_assesments.clientId,chargetype,howpaid,apm_invoice.appointmentid,apm_invoice.apmt_charge_type,apm_invoice.policy_excess FROM apm_invoice_assesments ");
			sql.append("inner join apm_invoice on apm_invoice.id = apm_invoice_assesments.invoiceid ");
			sql.append("where  apm_invoice_assesments.clientid="+clientId+" and apm_invoice_assesments.paybuy = 1 and apm_invoice.chargetype like('%"+transactionType+"%') and apm_invoice.location like ('%"+location+"%') and apm_invoice_assesments.thirdPartyId like ('"+thirdParty+"%') and apm_invoice.apmt_charge_type like ('%"+raiseChargeType+"%') ");
			sql.append("and apm_invoice_assesments.active=1  ");
			//  29 March 2018/06-12-2019 after selectall in create invoice show list according to date bt this list not show according to date so added date filter 
			if(fromDate!=null && toDate!=null){
				sql.append("and commencing between '"+fromDate+"' and '"+toDate+"' ");
			}
			if(!newipdid.equals("")){
				sql.append("  and apm_invoice_assesments.ipdid='"+newipdid+"'  ");
			}
			sql.append("group by invoiceid ");
		}else{
			sql.append("SELECT commencing,user,apmtType,sum(charge),invoiceid,paid,count(apm_invoice_assesments.id),apm_invoice.practitionerid,apm_invoice_assesments.paybuy,apm_invoice_assesments.clientId,chargetype,howpaid,apm_invoice.appointmentid,apm_invoice.apmt_charge_type,apm_invoice.policy_excess  FROM apm_invoice_assesments ");
			sql.append("inner join apm_invoice on apm_invoice.id = apm_invoice_assesments.invoiceid ");
			sql.append("where  apm_invoice_assesments.clientid="+clientId+" and apm_invoice.chargetype like('%"+transactionType+"%') and apm_invoice.location like ('%"+location+"%') and apm_invoice.apmt_charge_type like ('%"+raiseChargeType+"%') ");
			sql.append("and apm_invoice_assesments.active=1  ");
			//  29 March 2018/06-12-2019 after selectall in create invoice show list according to date bt this list not show according to date so added date filter 
			if(fromDate!=null && toDate!=null){
				sql.append("and commencing between '"+fromDate+"' and '"+toDate+"' ");
			}
			if(!newipdid.equals("")){
				sql.append("  and apm_invoice_assesments.ipdid='"+newipdid+"'  ");
			}
			if(!DateTimeUtils.isNull(thirdParty).equals("")){
				sql.append("and apm_invoice_assesments.thirdPartyId like ('"+thirdParty+"%') ");
			}
			sql.append("group by invoiceid ");
		}
		String sql1 = sql.toString();
		//sql1 = pagination.getSQLQuery(sql.toString());
		
		try{
			preparedStatement = connection.prepareStatement(sql1);
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()){
				Accounts accounts = new Accounts();
				accounts.setCommencing(dateTimeUtils.changeDateFormatToddmmyyyy(rs.getString(1)));
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
				}
				else{
					accounts.setWhoPay("Self");
				}
				accounts.setClientid(rs.getInt(10));
				accounts.setChargeType(rs.getString(11));
				accounts.setHowPaid(rs.getString(12));
				accounts.setApptId(rs.getString(13));
				accounts.setApmtChargeType(rs.getInt(14));
				accounts.setPolicyExcess(rs.getString(15));
				String treamentEpisodeName = getTreatmentEpisodeName(rs.getString(13));
				accounts.setTreatmentEpisodeName(treamentEpisodeName);
				double credit = getCreditAmount(rs.getString(5),rs.getString(9));
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
				
				boolean chargesInvoiced = checkChargeInvoiced(accounts.getInvoiceid());
				
				if(debit!=0 && chargesInvoiced== false){
				list.add(accounts);
				}
				
			}
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	public ArrayList<Accounts> getChargeAccountPaidList(String clientId,
			String payby, Pagination pagination, String transactionType,
			String location, String thirdParty,String raiseChargeType, String fromDate, String toDate, String newipdid) {
		
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
			sql.append("SELECT commencing,user,apmtType,sum(charge),invoiceid,paid,count(apm_invoice_assesments.id),apm_invoice.practitionerid,apm_invoice_assesments.paybuy,apm_invoice_assesments.clientId,chargetype,howpaid,apm_invoice.appointmentid,apm_invoice.apmt_charge_type,apm_invoice.policy_excess  FROM apm_invoice_assesments ");
			sql.append("inner join apm_invoice on apm_invoice.id = apm_invoice_assesments.invoiceid ");
			sql.append("where  apm_invoice_assesments.clientid="+clientId+" and apm_invoice_assesments.paybuy = 0 and apm_invoice.chargetype like('%"+transactionType+"%') and apm_invoice.location like ('%"+location+"%') and apm_invoice.apmt_charge_type like ('%"+raiseChargeType+"%') ");
			sql.append("and apm_invoice_assesments.active=1 ");
			//  29 March 2018/06-12-2019 after selectall in create invoice show list according to date bt this list not show according to date so added date filter 
			if(fromDate!=null && toDate!=null){
				sql.append("and commencing between '"+fromDate+"' and '"+toDate+"' ");
			}
			if(!DateTimeUtils.isNull(thirdParty).equals("")){
				sql.append("and apm_invoice_assesments.thirdPartyId like ('"+thirdParty+"') ");
			}
			if(!newipdid.equals("")){
				sql.append("  and apm_invoice_assesments.ipdid='"+newipdid+"'  ");
			}
			sql.append("group by invoiceid ");
			
		}else if(payby.equals(Constants.PAY_BY_THIRD_PARTY)){
			sql.append("SELECT commencing,user,apmtType,sum(charge),invoiceid,paid,count(apm_invoice_assesments.id),apm_invoice.practitionerid,apm_invoice_assesments.paybuy,apm_invoice_assesments.clientId,chargetype,howpaid,apm_invoice.appointmentid,apm_invoice.apmt_charge_type,apm_invoice.policy_excess FROM apm_invoice_assesments ");
			sql.append("inner join apm_invoice on apm_invoice.id = apm_invoice_assesments.invoiceid ");
			sql.append("where  apm_invoice_assesments.clientid="+clientId+" and apm_invoice_assesments.paybuy = 1 and apm_invoice.chargetype like('%"+transactionType+"%') and apm_invoice.location like ('%"+location+"%') and apm_invoice_assesments.thirdPartyId like ('"+thirdParty+"%') and apm_invoice.apmt_charge_type like ('%"+raiseChargeType+"%') ");
			sql.append("and apm_invoice_assesments.active=1 ");
			//  29 March 2018/06-12-2019 after selectall in create invoice show list according to date bt this list not show according to date so added date filter 
			if(fromDate!=null && toDate!=null){
				sql.append("and commencing between '"+fromDate+"' and '"+toDate+"' ");
			}
			if(!newipdid.equals("")){
				sql.append("  and apm_invoice_assesments.ipdid='"+newipdid+"'  ");
			}
			sql.append("group by invoiceid ");
		}else{
			sql.append("SELECT commencing,user,apmtType,sum(charge),invoiceid,paid,count(apm_invoice_assesments.id),apm_invoice.practitionerid,apm_invoice_assesments.paybuy,apm_invoice_assesments.clientId,chargetype,howpaid,apm_invoice.appointmentid,apm_invoice.apmt_charge_type,apm_invoice.policy_excess  FROM apm_invoice_assesments ");
			sql.append("inner join apm_invoice on apm_invoice.id = apm_invoice_assesments.invoiceid ");
			sql.append("where  apm_invoice_assesments.clientid="+clientId+" and apm_invoice.chargetype like('%"+transactionType+"%') and apm_invoice.location like ('%"+location+"%') and apm_invoice.apmt_charge_type like ('%"+raiseChargeType+"%') ");
			sql.append("and apm_invoice_assesments.active=1 ");
			//  29 March 2018 after selectall in create invoice show list according to date bt this list not show according to date so added date filter 
			if(fromDate!=null && toDate!=null){
				sql.append("and commencing between '"+fromDate+"' and '"+toDate+"' ");
			}
			if(!newipdid.equals("")){
				sql.append("  and apm_invoice_assesments.ipdid='"+newipdid+"'  ");
			}
			if(!DateTimeUtils.isNull(thirdParty).equals("")){
				sql.append("and apm_invoice_assesments.thirdPartyId like ('"+thirdParty+"%') ");
			}
			sql.append("group by invoiceid ");
		}
		String sql1 = sql.toString();
		//sql1 = pagination.getSQLQuery(sql.toString());
		
		try{
			preparedStatement = connection.prepareStatement(sql1);
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()){
				Accounts accounts = new Accounts();
				accounts.setCommencing(dateTimeUtils.changeDateFormatToddmmyyyy(rs.getString(1)));
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
				}
				else{
					accounts.setWhoPay("Self");
				}
				accounts.setClientid(rs.getInt(10));
				accounts.setChargeType(rs.getString(11));
				accounts.setHowPaid(rs.getString(12));
				accounts.setApptId(rs.getString(13));
				accounts.setApmtChargeType(rs.getInt(14));
				accounts.setPolicyExcess(rs.getString(15));
				String treamentEpisodeName = getTreatmentEpisodeName(rs.getString(13));
				accounts.setTreatmentEpisodeName(treamentEpisodeName);
				double credit = getCreditAmount(rs.getString(5),rs.getString(9));
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
				
				boolean chargesInvoiced = checkChargeInvoiced(accounts.getInvoiceid());
				
				if(debit!=0 && chargesInvoiced== false){
					list.add(accounts);
				}
				
			}
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		return list;
		
	}

	public ArrayList<Accounts> getChargeAccountList(String clientId,
			String payby, Pagination pagination, String transactionType,
			String location, String thirdParty,String raiseChargeType, String fromDate, String toDate, String newipdid) {
		double debitTotal=0.0;
		double creditTotal = 0.0;
		double balanceTotal = 0.0;
		String selctedTransType="";
		PreparedStatement preparedStatement = null;
		DateTimeUtils dateTimeUtils = new DateTimeUtils();
		ArrayList<Accounts>list = new ArrayList<Accounts>();
		StringBuffer sql = new StringBuffer();
		String pending= "Pending Payment";String paid = "Payments";
		
		if(payby.equals(Constants.PAY_BY_CLIENT)){
			sql.append("SELECT commencing,user,apmtType,sum(charge),invoiceid,paid,count(apm_invoice_assesments.id),apm_invoice.practitionerid,apm_invoice_assesments.paybuy,apm_invoice_assesments.clientId,chargetype,howpaid,apm_invoice.appointmentid,apm_invoice.apmt_charge_type,apm_invoice.policy_excess  FROM apm_invoice_assesments ");
			sql.append("inner join apm_invoice on apm_invoice.id = apm_invoice_assesments.invoiceid ");
			sql.append("where  apm_invoice_assesments.clientid="+clientId+" and apm_invoice_assesments.paybuy = 0 and apm_invoice.chargetype like('%"+transactionType+"%') and (apm_invoice.location like ('%"+location+"%') or apm_invoice.location like ('%0%')) and apm_invoice.apmt_charge_type like ('%"+raiseChargeType+"%') ");
			//sql.append("and apm_invoice_assesments.thirdPartyId like ('"+thirdParty+"') ");
			sql.append("and apm_invoice_assesments.active=1  ");
			//  29 March 2018/06-12-2019 after selectall in create invoice show list according to date bt this list not show according to date so added date filter 
			if(fromDate!=null && toDate!=null){
				sql.append("and commencing between '"+fromDate+"' and '"+toDate+"' ");
			}
			if(!newipdid.equals("")){
				sql.append("  and apm_invoice_assesments.ipdid='"+newipdid+"'  ");
			}
			sql.append("group by invoiceid ");
			
		}else if(payby.equals(Constants.PAY_BY_THIRD_PARTY)){
			sql.append("SELECT commencing,user,apmtType,sum(charge),invoiceid,paid,count(apm_invoice_assesments.id),apm_invoice.practitionerid,apm_invoice_assesments.paybuy,apm_invoice_assesments.clientId,chargetype,howpaid,apm_invoice.appointmentid,apm_invoice.apmt_charge_type,apm_invoice.policy_excess FROM apm_invoice_assesments ");
			sql.append("inner join apm_invoice on apm_invoice.id = apm_invoice_assesments.invoiceid ");
			sql.append("where  apm_invoice_assesments.clientid="+clientId+" and apm_invoice_assesments.paybuy = 1 and apm_invoice.chargetype like('%"+transactionType+"%') and (apm_invoice.location like ('%"+location+"%') or apm_invoice.location like ('%0%')) and apm_invoice_assesments.thirdPartyId like ('"+thirdParty+"%') and apm_invoice.apmt_charge_type like ('%"+raiseChargeType+"%') ");
			sql.append("and apm_invoice_assesments.active=1 ");
			
			//  29 March 2018/06-12-2019 after selectall in create invoice show list according to date bt this list not show according to date so added date filter 
			if(fromDate!=null && toDate!=null){
				sql.append("and commencing between '"+fromDate+"' and '"+toDate+"' ");
			}
			if(!newipdid.equals("")){
				sql.append("  and apm_invoice_assesments.ipdid='"+newipdid+"'  ");
			}
			sql.append("group by invoiceid ");
			
		}else{
			sql.append("SELECT commencing,user,apmtType,sum(charge),invoiceid,paid,count(apm_invoice_assesments.id),apm_invoice.practitionerid,apm_invoice_assesments.paybuy,apm_invoice_assesments.clientId,chargetype,howpaid,apm_invoice.appointmentid,apm_invoice.apmt_charge_type,apm_invoice.policy_excess FROM apm_invoice_assesments ");
			sql.append("inner join apm_invoice on apm_invoice.id = apm_invoice_assesments.invoiceid ");
			sql.append("where  apm_invoice_assesments.clientid="+clientId+" and apm_invoice.chargetype like('%"+transactionType+"%') and (apm_invoice.location like ('%"+location+"%') or apm_invoice.location like ('%0%')) and apm_invoice.apmt_charge_type like ('%"+raiseChargeType+"%') ");
			if(!DateTimeUtils.isNull(thirdParty).equals("")){
				sql.append("and apm_invoice_assesments.thirdPartyId like ('"+thirdParty+"%') ");
			}
			sql.append("and apm_invoice_assesments.active=1  ");
			
			//  29 March 2018/06-12-2019 after selectall in create invoice show list according to date bt this list not show according to date so added date filter 
			if(fromDate!=null && toDate!=null){
				sql.append("and commencing between '"+fromDate+"' and '"+toDate+"' ");
			}
			if(!newipdid.equals("")){
				sql.append("  and apm_invoice_assesments.ipdid='"+newipdid+"'  ");
			}
			sql.append("group by invoiceid ");
			
		}
		String sql1 = sql.toString();
		//sql1 = pagination.getSQLQuery(sql.toString());
		
		try{
			preparedStatement = connection.prepareStatement(sql1);
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()){
				Accounts accounts = new Accounts();
				accounts.setCommencing(dateTimeUtils.changeDateFormatToddmmyyyy(rs.getString(1)));
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
				}
				else{
					accounts.setWhoPay("Self");
				}
				accounts.setClientid(rs.getInt(10));
				accounts.setChargeType(rs.getString(11));
				accounts.setHowPaid(rs.getString(12));
				accounts.setApptId(rs.getString(13));
				accounts.setApmtChargeType(rs.getInt(14));
				accounts.setPolicyExcess(rs.getString(15));
				String treamentEpisodeName = getTreatmentEpisodeName(rs.getString(13));
				accounts.setTreatmentEpisodeName(treamentEpisodeName);
				double credit = getCreditAmount(rs.getString(5),rs.getString(9));
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
				
				boolean chargesInvoiced = checkChargeInvoiced(accounts.getInvoiceid());
				
				if(!chargesInvoiced){
					list.add(accounts);
				}
				
				
			}
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	public ArrayList<Accounts> getTempChagesInvoiceList(String loginsessionid) {
		PreparedStatement preparedStatement = null;
		ArrayList<Accounts>list = new ArrayList<Accounts>();
		String sql = "SELECT invoiceid FROM apm_tempcharge_account where usersessionid ='"+loginsessionid+"'";
		
		try{
			
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()){
				Accounts accounts = new Accounts();
				accounts.setInvoiceid(rs.getInt(1));
				list.add(accounts);
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
		return list;
	}

	public String getTempChagesInvoiceStringData(String loginsessionid) {
		PreparedStatement preparedStatement = null;
		String result = "";
		StringBuffer str = new StringBuffer();
		String sql = "SELECT invoiceid FROM apm_tempcharge_account where usersessionid ='"+loginsessionid+"' ";
		
		try{
			
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()){
				str.append(rs.getInt(1) + ",");
				
			}
			
			if(str.length() > 0){
				result = str.substring(0,str.length()-1);
			}
			
		}catch(Exception e){
			
		}
		
		return result;
	}
	
	
	public ArrayList<Accounts> getChargesInvoiceList(String chargesStr,String chargeType) {
		
		double debitTotal=0.0;
		double creditTotal = 0.0;
		double balanceTotal = 0.0;
		String selctedTransType="";
		
		PreparedStatement preparedStatement = null;
		DateTimeUtils dateTimeUtils = new DateTimeUtils();
		ArrayList<Accounts>list = new ArrayList<Accounts>();
		StringBuffer sql = new StringBuffer();
		
		sql.append("SELECT commencing,user,apmtType,sum(charge),invoiceid,paid,count(apm_invoice_assesments.id),apm_invoice.practitionername,apm_invoice_assesments.paybuy,apm_invoice_assesments.clientId,chargetype,howpaid,apm_invoice.appointmentid,apmt_charge_type,apm_invoice.policy_excess ");
		sql.append("FROM apm_invoice_assesments inner join apm_invoice on apm_invoice.id = apm_invoice_assesments.invoiceid ");
		sql.append("where apm_invoice_assesments.active=1 ");
		sql.append("and invoiceid in("+chargesStr+")  and apm_invoice.payamount=0  group by invoiceid ");
		
		try{
			preparedStatement = connection.prepareStatement(sql.toString());
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()){
				Accounts accounts = new Accounts();
				accounts.setCommencing(rs.getString(1));
				accounts.setClientName(rs.getString(2));
				accounts.setAppointmentType(rs.getString(3));
				if(rs.getString(4)==null){
					accounts.setCreditCharge(dateTimeUtils.changeFormat(Double.parseDouble("0.0")));
				}else{
					accounts.setCreditCharge(dateTimeUtils.changeFormat(Double.parseDouble(rs.getString(4))));
				}
				
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
				accounts.setApmtChargeType(rs.getInt(14));
				accounts.setPolicyExcess(rs.getString(15));
				String treamentEpisodeName = getTreatmentEpisodeName(rs.getString(13));
				accounts.setTreatmentEpisodeName(treamentEpisodeName);
				double credit = getCreditAmount(rs.getString(5),rs.getString(9));
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
				
				//accounts.setSelectedPayby(payby);
				
				list.add(accounts);
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return list;
	}

	public int updateChargeType(String id,String chargeType) {
		PreparedStatement preparedStatement = null;
		int result = 0;
		String sql = "Update apm_invoice set chargetype=? where id="+id+" ";
		
		try{
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, chargeType);
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

	public int saveChargesInvoice(String payby, String commencing,int clientid,double debit,double discount,
			String notes,int tpid,String location,String lastModified,int loginid,String invpstype,String invceype,int rinv,String fromdate,String todate,String doctorid, int ipdid,int preDate,String sessioncount) {
		PreparedStatement preparedStatement = null;
		int result = 0;
		String sql = "insert into apm_charges_invoice(payby,date,discount,debit,clientid,"
				+ "chargetype,notes,tpid,location,updatedby,invpstype,itype,resetinv,fromdate,"
				+ "todate,practid,ipdid,time,predayornot,date_time,session) "
				+ "values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		int invoiceid = 0;
		
		if(payby.equals("1") || payby.equals(Constants.PAY_BY_THIRD_PARTY)){
			payby = Constants.PAY_BY_THIRD_PARTY;
		}else{
			payby = Constants.PAY_BY_CLIENT;
		}
		
		try{
			
			//HH:mm:ss
			DateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
			Calendar cal = Calendar.getInstance();
			String time = dateFormat.format(cal.getTime());			
			/*
			 * int physioopd=patientphysioIpdOrNot(doctorid); if(physioopd==1) {
			 * invceype="18"; }
			 */
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
			preparedStatement.setInt(10, loginid);
			preparedStatement.setString(11, invpstype);
			preparedStatement.setString(12, invceype);
			preparedStatement.setInt(13, rinv);
			preparedStatement.setString(14, fromdate);
			preparedStatement.setString(15, todate);
			preparedStatement.setString(16, doctorid);
			preparedStatement.setInt(17,ipdid);
			preparedStatement.setString(18, time);
			preparedStatement.setString(19, ""+preDate);
			preparedStatement.setString(20, commencing+" "+time);
			preparedStatement.setString(21, sessioncount);
			result = preparedStatement.executeUpdate();
			
			if(result == 1){
				ResultSet resultSet = preparedStatement.getGeneratedKeys();
				if(resultSet.next()){
					invoiceid = resultSet.getInt(1);
					generateSeqOfBill(""+invoiceid, invceype);
					setVacinationChargeprocedure(""+invoiceid, ""+clientid);
					generateLMHSeqDateWise(invoiceid,invceype,commencing);
				}
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
		return invoiceid;
	}

	public int patientphysioIpdOrNot(String doctorid) {
		int result=0;
		try {
			String sql="select showdeptlist from apm_discipline inner join apm_user on apm_user.discription=apm_discipline .id where apm_user.id='"+doctorid+"'";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				result = rs.getInt(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
		
	}

	private void generateLMHSeqDateWise(int invoiceid, String invceype, String cdate) {
		try {
			String abrivationid = "";
			
			int seqno = checkifSequenceExistLMH(cdate,invceype);
			
			String clinicabrivation = getInvoiceAbrivation(invceype);
			
			String tempd[] = cdate.split("-");
			String y = tempd[0];
			String m = tempd[1];
			String d = tempd[2];
			String newseq="";
			int yr = Integer.parseInt(y)%1000;
			yr=Integer.parseInt(y);
			if(String.valueOf(seqno).length()==1){
				 newseq="000"+seqno;
			}else if(String.valueOf(seqno).length()==2){
				 newseq="00"+seqno;
			}else if(String.valueOf(seqno).length()==3){
				 newseq="0"+seqno;
			}else{
				newseq=""+seqno;
			}
			abrivationid = clinicabrivation + yr + m +d + "" + newseq;
			
			saveLMHInvoiceSequnce(invoiceid,abrivationid);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private String getInvoiceAbrivation(String invceype) {
		String itype="OB";
		try {
			String sql="SELECT abrivation FROM apm_invoice_type where id='"+invceype+"'";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				itype = rs.getString(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return itype;
	}

	private void saveLMHInvoiceSequnce(int invoiceid, String abrivationid) {
		try {
			String sql="update apm_charges_invoice set dayseqno='"+abrivationid+"' where id='"+invoiceid+"'";
			PreparedStatement ps= connection.prepareStatement(sql);
			ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private int checkifSequenceExistLMH(String cdate, String invceype) {
		PreparedStatement preparedStatement = null;
		int result = 0;
		String sql = "select seqno from invoice_sequence where commencing = '"+cdate+"' and itype='"+invceype+"' ";
		try{
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			if(rs.next()){
				result = rs.getInt(1);
				result++;
				String querry ="update invoice_sequence set seqno='"+result+"' where commencing='"+cdate+"' and itype='"+invceype+"'";
				PreparedStatement preparedStatement2 = connection.prepareStatement(querry);
				preparedStatement2.executeUpdate();
			}else{
				result =1;
				String querry ="insert into invoice_sequence(commencing,itype,seqno) values(?,?,?)";
				PreparedStatement preparedStatement2 = connection.prepareStatement(querry);
				preparedStatement2.setString(1, cdate);
				preparedStatement2.setString(2, invceype);
				preparedStatement2.setString(3, ""+result);
				preparedStatement2.executeUpdate();
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	
	}

	public int saveChargesAssesment(int invoiceid,int chargeinvoiceid) {
		PreparedStatement preparedStatement = null;
		int result = 0;
		String sql = "insert into apm_charges_assesment(invoiceid,chargeinvoiceid) values(?,?) ";
		
		try{
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, invoiceid);
			preparedStatement.setInt(2, chargeinvoiceid);
			result = preparedStatement.executeUpdate();
			if(result>0){
				addInvoiceIdToApm_Invoice(invoiceid, chargeinvoiceid);
			}
			
		}catch(Exception e){
			e.printStackTrace();
//			StringWriter sw = new StringWriter();
//	         PrintWriter pw = new PrintWriter(sw);
//	         e.printStackTrace(pw);
//			Tra.exceptionMail(sw.toString());
		}
		return result;
	}
private void addInvoiceIdToApm_Invoice(int invoiceid,int chargeinvoiceid){
	PreparedStatement preparedStatement = null;
	
	String sql = "update apm_invoice set charges_invoice_id=? where id=? ";
	
	try{
		preparedStatement = connection.prepareStatement(sql);
		preparedStatement.setInt(1, chargeinvoiceid);
		preparedStatement.setInt(2, invoiceid);
		int result = preparedStatement.executeUpdate();
		
	}catch(Exception e){
		e.printStackTrace();
//		StringWriter sw = new StringWriter();
//         PrintWriter pw = new PrintWriter(sw);
//         e.printStackTrace(pw);
//		Tra.exceptionMail(sw.toString());
	}
	
}
	public ArrayList<Accounts> getChargesTempInvoiceList(String chargeType,
			String clientId, String loginsessionid) {
		PreparedStatement preparedStatement = null;
		ArrayList<Accounts>list = new ArrayList<Accounts>();
		StringBuffer sql = new StringBuffer();
		
		sql.append("select apm_invoice.id from apm_invoice inner join apm_tempcharge_account ");
		sql.append("on apm_invoice.id = apm_tempcharge_account.invoiceid ");
		sql.append("where apm_invoice.clientid="+clientId+" and apm_invoice.payamount=0 and usersessionid='"+loginsessionid+"' ");
		
		try{
			
			preparedStatement = connection.prepareStatement(sql.toString());
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()){
				Accounts accounts = new Accounts();
				accounts.setInvoiceid(rs.getInt(1));
				list.add(accounts);
			}
			
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return list;
	}

	public int delateTempCharges(String invoiceid, String loginsessionid) {
		PreparedStatement preparedStatement = null;
		int result = 0;
		String sql = "delete from apm_tempcharge_account where invoiceid = "+invoiceid+" and usersessionid='"+loginsessionid+"' ";
		try{
			preparedStatement = connection.prepareStatement(sql);
			result = preparedStatement.executeUpdate();
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
				
		return result;
	}

	public ArrayList<Accounts> getCashdeskChargeList(String allChargeList,String clientid,LoginInfo loginInfo) {
		
		double debitTotal=0.0;
		double creditTotal = 0.0;
		double balanceTotal = 0.0;
		String selctedTransType="";
		
		PreparedStatement preparedStatement = null;
		DateTimeUtils dateTimeUtils = new DateTimeUtils();
		ArrayList<Accounts>list = new ArrayList<Accounts>();
		StringBuffer sql = new StringBuffer();
		
		if(loginInfo.getClinicid1().equals("dentalcl")) {
			sql.append("SELECT commencing,user,apmtType,charge,invoiceid,paid,apm_invoice_assesments.id,apm_invoice.practitionername,apm_invoice_assesments.paybuy,apm_invoice_assesments.clientId,chargetype,howpaid,apm_invoice.appointmentid,apm_invoice.apmt_charge_type ");
			sql.append("FROM apm_invoice_assesments inner join apm_invoice on apm_invoice.id = apm_invoice_assesments.invoiceid ");
			sql.append("where apm_invoice_assesments.active=1 and invoiceid in("+allChargeList+") and apm_invoice.credit=0 and apm_invoice_assesments.clientid = "+clientid+" ");
			
		}else {
		sql.append("SELECT commencing,user,apmtType,sum(charge),invoiceid,paid,count(apm_invoice_assesments.id),apm_invoice.practitionername,apm_invoice_assesments.paybuy,apm_invoice_assesments.clientId,chargetype,howpaid,apm_invoice.appointmentid,apm_invoice.apmt_charge_type ");
		sql.append("FROM apm_invoice_assesments inner join apm_invoice on apm_invoice.id = apm_invoice_assesments.invoiceid ");
		sql.append("where apm_invoice_assesments.active=1 and invoiceid in("+allChargeList+") and apm_invoice.credit=0 and apm_invoice_assesments.clientid = "+clientid+" ");
		sql.append("group by invoiceid ");
		}
		try{
			preparedStatement = connection.prepareStatement(sql.toString());
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()){
				Accounts accounts = new Accounts();
				accounts.setCommencing(rs.getString(1));
				accounts.setClientName(rs.getString(2));
				accounts.setAppointmentType(rs.getString(3));
				accounts.setCreditCharge(rs.getString(4));
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
				accounts.setApmtChargeType(rs.getInt(14));
				String treamentEpisodeName = getTreatmentEpisodeName(rs.getString(13));
				accounts.setTreatmentEpisodeName(treamentEpisodeName);
				double credit = getCreditAmount(rs.getString(5),rs.getString(9));
				double totalPayment =0.0;
				if(loginInfo.getClinicid1().equals("dentalcl")) {
				 totalPayment=Double.parseDouble(rs.getString(4));
				}else {
				 totalPayment = getPaymentAmount(rs.getInt(5),rs.getString(9));
				}
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
				
				//accounts.setSelectedPayby(payby);
				
				list.add(accounts);
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return list;
		
		
	}

	public ArrayList<Accounts> getCashdeskChargesTempInvoiceList(String clientId) {
		PreparedStatement preparedStatement = null;
		ArrayList<Accounts>list = new ArrayList<Accounts>();
		StringBuffer sql = new StringBuffer();
		
		sql.append("select apm_invoice.id from apm_invoice inner join apm_tempcharge_account ");
		sql.append("on apm_invoice.id = apm_tempcharge_account.invoiceid ");
		sql.append("where apm_invoice.clientid="+clientId+" ");
		
		try{
			
			preparedStatement = connection.prepareStatement(sql.toString());
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()){
				Accounts accounts = new Accounts();
				accounts.setInvoiceid(rs.getInt(1));
				list.add(accounts);
			}
			
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return list;
	}

	public int saveChargesPayment(String clientId, int invoiceid,
			String totalamount, String paymode,int tpid,String paymentNote,String date,
			int creditInvoiceid,String userid,String chequno,String bankname, int preDate, 
			String commencing, String invcetype, String upino, String remark) {
		PreparedStatement preparedStatement = null;
		
		int result = 0;

		try{
			
			String discamt=getchargeinvoicediscamt(invoiceid);
			boolean chargeinvcid=isinvoiceidexist(invoiceid);
			if(chargeinvcid) {
				discamt="0";
			}
		
		String sql = "insert into apm_charges_payment(clientid,chargeinvoiceid,payment,paymode,date,tpid,paymentnote,crdinvoiceid,userid,chequeno,bankname,upino,remark,discamt) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?) ";
		
			String datetime = date;
			if(preDate==1){
				datetime = commencing +" "+date.split(" ")[1];
			}
			
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, clientId);
			preparedStatement.setInt(2, invoiceid);
			preparedStatement.setString(3, totalamount);
			preparedStatement.setString(4, paymode);
			preparedStatement.setString(5, datetime);
			preparedStatement.setInt(6, tpid);
			preparedStatement.setString(7, paymentNote);
			preparedStatement.setInt(8, creditInvoiceid);
			preparedStatement.setString(9, userid);
			preparedStatement.setString(10, chequno);
			preparedStatement.setString(11, bankname);
			preparedStatement.setString(12, upino);
			preparedStatement.setString(13, remark);
			preparedStatement.setString(14, discamt);
			
			result = preparedStatement.executeUpdate();
			
			if (result == 1) {
				ResultSet resultSet = preparedStatement.getGeneratedKeys();
				if (resultSet.next()) {
					result = resultSet.getInt(1);
					if(!paymode.equals("prepayment")){
						saveToPhysicalHISPayment(String.valueOf(invoiceid), "", clientId, date, paymode, totalamount, "",String.valueOf(result));
					}
					savePaymentLMHSeq(invoiceid,invcetype,commencing,result);
					
				}
			}

			
		}catch(Exception e){
			e.printStackTrace();
//			StringWriter sw = new StringWriter();
//	         PrintWriter pw = new PrintWriter(sw);
//	         e.printStackTrace(pw);
//			Tra.exceptionMail(sw.toString());
		}
		return result;
	}
	
	private boolean isinvoiceidexist(int invoiceid) {
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

	private String getchargeinvoicediscamt(int invoiceid) {
		PreparedStatement preparedStatement = null;
		String result = "";
		String sql = "SELECT discamt FROM apm_charges_invoice where id=  "+invoiceid+" ";
		
		try{
			
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			if(rs.next()){
				result = rs.getString(1) ;
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
		return result;
	}

	private void savePaymentLMHSeq(int invoiceid, String invceype, String cdate, int result) {
		try {
			String abrivationid = "";
			
			int seqno = checkifPaymentSequenceExistLMH(cdate,invceype);
			
			String clinicabrivation = getInvoiceAbrivation(invceype);
			clinicabrivation = clinicabrivation+"R";
			
			String tempd[] = cdate.split("-");
			String y = tempd[0];
			String m = tempd[1];
			String d = tempd[2];
			String newseq="";
			int yr = Integer.parseInt(y)%1000;
			yr=Integer.parseInt(y);
			if(String.valueOf(seqno).length()==1){
				 newseq="000"+seqno;
			}else if(String.valueOf(seqno).length()==2){
				 newseq="00"+seqno;
			}else if(String.valueOf(seqno).length()==3){
				 newseq="0"+seqno;
			}else{
				newseq=""+seqno;
			}
			abrivationid = clinicabrivation + yr + m +d + "" + newseq;
			
			saveLMHPaymentSequnce(result,abrivationid);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void saveLMHPaymentSequnce(int result, String abrivationid) {
		try {
			String sql="update apm_charges_payment set paySequence='"+abrivationid+"' where id='"+result+"'";
			PreparedStatement ps= connection.prepareStatement(sql);
			ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	
	}

	private int checkifPaymentSequenceExistLMH(String cdate, String invceype) {

		PreparedStatement preparedStatement = null;
		int result = 0;
		String sql = "select seqno from payment_sequence where commencing = '"+cdate+"' and itype='"+invceype+"' ";
		try{
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			if(rs.next()){
				result = rs.getInt(1);
				result++;
				String querry ="update payment_sequence set seqno='"+result+"' where commencing='"+cdate+"' and itype='"+invceype+"'";
				PreparedStatement preparedStatement2 = connection.prepareStatement(querry);
				preparedStatement2.executeUpdate();
			}else{
				result =1;
				String querry ="insert into payment_sequence(commencing,itype,seqno) values(?,?,?)";
				PreparedStatement preparedStatement2 = connection.prepareStatement(querry);
				preparedStatement2.setString(1, cdate);
				preparedStatement2.setString(2, invceype);
				preparedStatement2.setString(3, ""+result);
				preparedStatement2.executeUpdate();
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	
	
	}

	public String getClientName(String clientId) {
		PreparedStatement preparedStatement = null;
		String result = "";
		String sql = "SELECT title,firstname,surname FROM apm_patient where id=  "+clientId+" ";
		
		try{
			
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			if(rs.next()){
				result = rs.getString(1) + " " +rs.getString(2) + " " +rs.getString(3);
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
		return result;
	}

	public Accounts getAppointmentDetailsl(String invoiceid) {
		PreparedStatement preparedStatement = null;
		Accounts accounts = new Accounts();
		StringBuffer sql = new StringBuffer();
		
		sql.append("SELECT treatmentEpisodeId, apm_available_slot.commencing FROM apm_available_slot inner join apm_invoice on ");
		sql.append("apm_invoice.appointmentid = apm_available_slot.id where apm_invoice.id = "+invoiceid+" ");
		
		try{
			
			preparedStatement = connection.prepareStatement(sql.toString());
			ResultSet rs = preparedStatement.executeQuery();
			if(rs.next()){
				String treatmentName = getTreatmentName(rs.getInt(1));
				accounts.setTreatmentEpisodeName(treatmentName);
				accounts.setCommencing(rs.getString(2));
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return accounts;
	}

	private String getTreatmentName(int treatmentepisodeid) {
		PreparedStatement preparedStatement = null;
		String result = "";
		String sql = "SELECT name FROM apm_treatment_episode where id= "+treatmentepisodeid+" ";
		
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
	
	public ArrayList<Master>getEstimateMasterAssessmentList(String clientid,int loginid){
		PreparedStatement preparedStatement = null;
		ArrayList<Master>list = new ArrayList<Master>();
		StringBuffer sql = new StringBuffer();
		
		sql.append("SELECT masterchargetype FROM apm_patient_complete_apmt where clientid = "+clientid+" and loginid="+loginid+" group by masterchargetype ");
		
		
		try{
			preparedStatement = connection.prepareStatement(sql.toString());
			ResultSet  rs = preparedStatement.executeQuery();
			while(rs.next()){
				Master master = new Master();
				master.setName(rs.getString(1));
				
				Vector<Accounts> assesmentList = getEstimateFilteredPreviewChargesList(master.getName(),clientid,loginid);
				master.setAssesmentList(assesmentList);
				
				list.add(master);
			}
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		return list;
	}
	
	public ArrayList<Master>getdchargeMasterAssessmentList(String clientid,String curdate,LoginInfo loginInfo,boolean isprntchrg,String ipdid){
		PreparedStatement preparedStatement = null;
		ArrayList<Master>list = new ArrayList<Master>();
		StringBuffer sql = new StringBuffer();
		StringBuffer buffer=new StringBuffer();
		//sql.append("SELECT masterchargetype FROM apm_invoice_assesments where clientid = "+clientid+" group by masterchargetype ");
		sql.append("SELECT masterchargetype,std_charge_id ,sum(charge*quantity) FROM apm_invoice_assesments ");
		sql.append("inner join apm_invoice on apm_invoice.id = apm_invoice_assesments.invoiceid ");
		sql.append("where apm_invoice_assesments.clientid = "+clientid+" and invoiced=0 and masterchargetype  IS NOT NULL ");
		
		if(loginInfo.getId()!=1){
			sql.append("and chargetype!='Submit' ");
		}
		if(ipdid!=null){
			if(!ipdid.equals("")){
				sql.append("and apm_invoice_assesments.ipdid='"+ipdid+"' ");
			}else{
				
			}
		}else{
			
		}
		sql.append("group  by masterchargetype  order by FIELD(masterchargetype,'Bed Charge','Ipd Registration Charge') desc,masterchargetype");//order by FIELD(masterchargetype,'Bed Charge','Ipd Registration Charge') desc,masterchargetype
		
		
		
		try{
			preparedStatement = connection.prepareStatement(sql.toString());
			ResultSet  rs = preparedStatement.executeQuery();
			double dchtotal  = 0;
			int i=0;
			double totaldisc=0;
			
			while(rs.next()){
				Master master = new Master();
				master.setName(rs.getString(1));
				if(rs.getInt(2)>0){
					master.setStdchargests(true);
				}else{
					master.setStdchargests(false);
				}
				String breakage =  getMasterBreakage(master.getName());
				if(breakage.equals("")){
					breakage="0";
				}
				master.setKrackage(breakage);;
				Vector<Accounts> assesmentList = null;
				ArrayList<Master> prntpwardwisechargelist = null;
				/*if(isprntchrg){
				 prntpwardwisechargelist = getprintpreWardList(clientid,master.getName(),curdate,loginInfo,isprntchrg,ipdid);
				 master.setPrntpwardwisechargelist(prntpwardwisechargelist);
				}else{*/
				// assesmentList = getdchargeFilteredPreviewChargesList(master.getName(),clientid,curdate,loginInfo,isprntchrg,ipdid,0,"");
			    // master.setAssesmentList(assesmentList);
				/*}*/
				
				
				double subtotal=0;
				 //subtotal = getdMasterSubtotal(master.getName(),clientid,ipdid);
				// subtotal = getdMasterSubtotalprnt(master.getName(),clientid,ipdid);
				master.setCharge(DateTimeUtils.changeFormat(subtotal));
				//  25 Sep 2018 to hide subtotal from main total if there all charges all submited in charge detailsss
				boolean flag = checkChargeSubmited(master.getName(),clientid,curdate);
				if(flag){
					dchtotal = dchtotal + subtotal;
				}
				master.setHidecode(i);
				i=i+1;
				master.setDchargetotal(DateTimeUtils.changeFormat(dchtotal));
				int size1=0;
				/*if(isprntchrg){
					size1 = prntpwardwisechargelist.size();
				}else{*/
					//size1 = assesmentList.size();
				/*}*/
				 
				double disc=0;
				/*
				 * if(!isprntchrg){ if(assesmentList.size()>0){
				 * disc=assesmentList.get(size1-1).getTotalDisc(); } }
				 */			
				totaldisc=totaldisc+disc;
				master.setTotaldiscount(totaldisc);
				master.setIsprntchrg(isprntchrg);
				buffer=buffer.append("'"+rs.getString(1)+"',");
				master.setMasterchargnames(""+buffer);
				master.setSubtotal(rs.getDouble(3));
				list.add(master);
			}
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		return list;
	}
	
	private ArrayList<Master> getprintpreWardList(String clientid, String name, String curdate, LoginInfo loginInfo, boolean isprntchrg, String ipdid) {
		
		PreparedStatement preparedStatement = null;
		ArrayList<Master>list = new ArrayList<Master>();
		StringBuffer sql = new StringBuffer();
		 boolean discstatus=false;
		IpdDAO ipdDAO = new JDBCIpdDAO(connection);
				
		try{
			int i=0;
			int isbedcharge=0;
			int isinvestigationcharge=0;
			if(name!=null){
				if(name.equals("Bed Charge")){
					isbedcharge=1;
				}
			}
			//  18 Sep 2018 if master charge is bed charge then it go to if condition
			//And set i =1;
			// and if i=0 means there is no ward present so its work normally
			if(isbedcharge==1){
				sql.append("SELECT masterchargetype,wardid FROM apm_invoice_assesments ");
				sql.append("inner join apm_invoice on apm_invoice.id = apm_invoice_assesments.invoiceid ");
				sql.append("where apm_invoice_assesments.clientid = "+clientid+" and chargetype!='Submit' and masterchargetype  IS NOT NULL ");
				if(ipdid!=null){
					if(!ipdid.equals("")){
						sql.append("and apm_invoice_assesments.ipdid='"+ipdid+"' ");
					}
				}
				sql.append("group by masterchargetype order by FIELD(masterchargetype,'Bed Charge','Ipd Registration Charge') desc,masterchargetype asc  ");
				preparedStatement = connection.prepareStatement(sql.toString());
				ResultSet  rs = preparedStatement.executeQuery();
				while(rs.next()){
					i=1;
					Master master = new Master();
					master.setName(rs.getString(1));
					String wardname=ipdDAO.getIpdWardName(rs.getString(2));
					Vector<Accounts> assesmentList = getdchargeFilteredPreviewChargesList(master.getName(),clientid,curdate,loginInfo,isprntchrg,ipdid,1,rs.getString(2));
					master.setAssesmentList(assesmentList);
					master.setWardname(wardname);
					double subtotal = getdMasterSubtotal(master.getName(),clientid,ipdid);
					master.setCharge(DateTimeUtils.changeFormat(subtotal));
					master.setIsbedcharge(1);
					int size1 = assesmentList.size();
					{
						 discstatus=assesmentList.get(size1-1).isDiscstatus();
						 
					}
					master.setDiscstatus(discstatus);
					list.add(master);
				}
			// had to remove making bill summary very slow 27/11/2018	
				
				
			}
			if(i==0){
				Master master = new Master();
				master.setName(name);
				String wardname="";
				Vector<Accounts> assesmentList = getdchargeFilteredPreviewChargesList(master.getName(),clientid,curdate,loginInfo,isprntchrg,ipdid,0,"");
				master.setAssesmentList(assesmentList);
				master.setWardname(wardname);
				double subtotal = getdMasterSubtotal(master.getName(),clientid,ipdid);
				master.setCharge(DateTimeUtils.changeFormat(subtotal));
				master.setIsbedcharge(0);
				int size1 = assesmentList.size();
				{
					 discstatus=assesmentList.get(size1-1).isDiscstatus();
					 
				}
				master.setDiscstatus(discstatus);
				list.add(master);
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	private double getdMasterSubtotalprnt(String name, String clientid, String ipdid) {
		PreparedStatement preparedStatement = null;
		double result = 0;
		StringBuffer sql = new StringBuffer();
		
		sql.append("select charge,quantity,chargedisc,unitcharge from  apm_invoice_assesments   ");
		sql.append("inner join apm_invoice on apm_invoice.id = apm_invoice_assesments.invoiceid ");
		sql.append("where  masterchargetype = '"+name+"' and apm_invoice_assesments.clientid = "+clientid+"  and chargetype!='Submit' ");
		if(ipdid!=null){
			if(!ipdid.equals("")){
				sql.append("and apm_invoice_assesments.ipdid='"+ipdid+"' ");
			}
		}
		try{
			preparedStatement = connection.prepareStatement(sql.toString());
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()){
				if(rs.getDouble(3)>0){
					result = result + (rs.getDouble(4)*rs.getInt(2));
				}else{
				result = result + (rs.getDouble(1)*rs.getInt(2));
				}
			}
			
		}catch (Exception e) {
			// TODO: handle exception
		}
		
		return result;
	}

	private boolean checkChargeSubmited(String name, String clientid, String curdate) {
		boolean flag =false;
		try {
			StringBuffer sql = new StringBuffer();
			sql.append("SELECT apmtType,charge,apm_invoice_assesments.practitionerName,apm_invoice_assesments.practitionerId,paybuy, ");
			sql.append("apm_invoice_assesments.id,commencing,apm_invoice_assesments.appointmentid,quantity,apm_invoice_assesments.id, ");
			sql.append("apm_invoice_assesments.chargeid,apm_invoice_assesments.std_charge_id,apm_invoice_assesments.ipdid,newshftcharge,logid,wardid ");
			sql.append(" FROM apm_invoice_assesments ");
			sql.append("inner join apm_invoice on apm_invoice.id = apm_invoice_assesments.invoiceid ");
			sql.append(" where masterchargetype = '"+name+"' and apm_invoice_assesments.clientid="+clientid+" ");
			sql.append("and chargetype!='Submit' ");
			sql.append("order by commencing asc ");
			PreparedStatement preparedStatement = connection.prepareStatement(sql.toString());
			ResultSet rs = preparedStatement.executeQuery();
			if (rs.next()) {
				flag = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}

		public Vector<Accounts> getdchargeFilteredPreviewChargesList(String name,String clientid,String curdate, LoginInfo loginInfo, boolean isprntchrg, String ipdid2, int j, String wardid) {
			PreparedStatement preparedStatement = null;
			Vector<Accounts>list = new Vector<Accounts>();
			BedDao bedDao = new JDBCBedDao(connection);
			IpdDAO ipdDAO = new JDBCIpdDAO(connection);
			/*String sql = "SELECT apmtType,charge,practitionerName,practitionerId,paybuy,id,commencing,appointmentid,quantity,apm_invoice_assesments.id,apm_invoice_assesments.chargeid,apm_invoice_assesments.std_charge_id,ipdid,newshftcharge,logid,wardid  FROM apm_invoice_assesments" +
					" where masterchargetype = '"+name+"' and clientid="+clientid+" ";*/
			
			StringBuffer sql = new StringBuffer();
			/*
			 * sql.
			 * append("SELECT apmtType,charge,apm_invoice_assesments.practitionerName,apm_invoice_assesments.practitionerId,paybuy, "
			 * ); sql.
			 * append("apm_invoice_assesments.id,commencing,apm_invoice_assesments.appointmentid,quantity,apm_invoice_assesments.id, "
			 * ); sql.append(
			 * "apm_invoice_assesments.chargeid,apm_invoice_assesments.std_charge_id,apm_invoice_assesments.ipdid,newshftcharge,logid,wardid,pkgcode,discreq,chargedisc,unitcharge,manualcharge,showing_date"
			 * ); if(isprntchrg){ if(!name.equals("IMPLANTS/CONSUMABLES")){
			 * sql.append(" ,sum(quantity)"); } ; }
			 * sql.append(" masterchargetype FROM apm_invoice_assesments "); sql.
			 * append("inner join apm_invoice on apm_invoice.id = apm_invoice_assesments.invoiceid "
			 * ); sql.append(" where masterchargetype in('"
			 * +name+"') and apm_invoice_assesments.clientid="+clientid+" ");
			 * if(ipdid2!=null){ if(!ipdid2.equals("")){
			 * sql.append("and apm_invoice_assesments.ipdid='"+ipdid2+"' "); }else{
			 * 
			 * } }else{
			 * 
			 * } if(j!=0){ if(wardid==null){
			 * sql.append("and apm_invoice_assesments.wardid is null "); }else{
			 * sql.append("and apm_invoice_assesments.wardid='"+wardid+"' "); }
			 * 
			 * } if(loginInfo.getId()!=1){ sql.append("and chargetype!='Submit' "); }
			 * //sql.append("and chargetype!='Submit' "); if(isprntchrg){
			 * if(!name.equals("IMPLANTS/CONSUMABLES")){
			 * sql.append("group by apmtType,charge,wardid "); } }
			 * sql.append("order by commencing asc ");
			 */
			
			sql.append("SELECT apmtType,charge,apm_invoice_assesments.practitionerName,apm_invoice_assesments.practitionerId,paybuy, apm_invoice_assesments.id,commencing,apm_invoice_assesments.appointmentid,quantity,apm_invoice_assesments.id, apm_invoice_assesments.chargeid,apm_invoice_assesments.std_charge_id,apm_invoice_assesments.ipdid,newshftcharge,logid,wardid,pkgcode,discreq,chargedisc,unitcharge,manualcharge,showing_date,masterchargetype ");
			if(isprntchrg){ 
				if(!name.equals("IMPLANTS/CONSUMABLES")){
                   sql.append(" ,sum(quantity)"); 
		      }
			}
			sql.append("FROM apm_invoice_assesments ");
			sql.append(" inner join apm_invoice on apm_invoice.id = apm_invoice_assesments.invoiceid") ;
			sql.append(" where masterchargetype in("+name+")  and apm_invoice_assesments.clientid='"+clientid+"'"); 
			sql.append(" and invoiced=0 ");
			if(isprntchrg){
			  if(!name.equals("IMPLANTS/CONSUMABLES")){
				  sql.append("group by apmtType,charge,wardid "); 
				} 
			}
		    sql.append("order by commencing asc ");
			
			
			ClientDAO clientDAO = new JDBCClientDAO(connection);
			Client client = clientDAO.getClientDetails(clientid);
			ThirdPartyDAO thirdPartyDAO = new JDBCThirdPartyDAO(connection);
			try{
				preparedStatement = connection.prepareStatement(sql.toString());
				ResultSet rs = preparedStatement.executeQuery();
				String tempcurdate = curdate;
				double quantity=0;
				double totdisc=0;
				while(rs.next()){
					String masterchargetype = name;
					String apptName = rs.getString(1);
					String  sectionroom = "";
					if(masterchargetype.equals("Pathology") || masterchargetype
							.equals("Radiology")){

						 sectionroom = getSectionRoom(apptName);
						if(apptName.contains("~")){
							String tmp[] = apptName.split("~");
							apptName = tmp[0];
						}
						
						//set investigation alternate name
						String tpid = client.getTypeName();
						ThirdParty thirdParty = thirdPartyDAO.getThirdPartyDetails(tpid);
						
							String alternamename = getInvestigationAltName(rs.getString(1));
							if(alternamename !=null){
								if(!alternamename.equals("")){
									apptName = alternamename;
								}
							}
						}

					
					//String apptName = rs.getString(1);
					String charge="0.0";
					if(isprntchrg){
						if(rs.getDouble(19)>0){
							 charge = rs.getString(20);
						}else{
							 charge = rs.getString(2);
						}
					}else{
						 charge = rs.getString(2);
					}
					if(charge==null){
						charge = "0.0";
					}
					
					String practitionerName = rs.getString(3);
					String practitionerid = rs.getString(4);
					int payby = rs.getInt(5);
					int addtitonalCharge = 1;
					String chargeid = rs.getString(6);
					String date = rs.getString(7);
					int apptid = rs.getInt(8);
					if(isprntchrg){
						if(!name.equals("IMPLANTS/CONSUMABLES")){
							//manoj sir change rs.getdouble(23) to rs.getdouble(9)
						 quantity = rs.getDouble(24);
						}else{
							 quantity = rs.getDouble(9);
						}
					}else{
					 quantity = rs.getDouble(9);
					}
					int subchargeid = rs.getInt(10);
					int apmtchargeid = rs.getInt(11);;
					String ipdid = rs.getString(13);
					String newshftcharge = rs.getString(14);
					String logid = rs.getString(15);
					
					Accounts accounts = new Accounts();
					
				/*	if(addtitonalCharge==0){
						Accounts acc = getAppointmentDetailsl(chargeid);
						if(acc.getCommencing()!=null){
							accounts.setCommencing(DateTimeUtils.getInvoiceCommencingDate(acc.getCommencing()));
							accounts.setAppointmentType(acc.getTreatmentEpisodeName() + " " +apptName);
						}
						
						
						boolean dna = getDna(apptid);
						accounts.setDna(dna);
						
					}else{
						String invoicedate = date;
						accounts.setCommencing(DateTimeUtils.getInvoiceCommencingDate(invoicedate));
						accounts.setAppointmentType("Additional Charge("+apptName+")");
					}*/
					accounts.setAppointmentid(apptid);
					accounts.setCharges(DateTimeUtils.changeFormat(Double.parseDouble(charge)));
					accounts.setChargeTotal(DateTimeUtils.changeFormat(Double.parseDouble(charge)*quantity));
					accounts.setPractitionerName(practitionerName);
					String practitionerFullName = getPractitionerFullName(practitionerid);
					accounts.setPractitionerName(practitionerFullName);
					/* accounts.setQuantity(quantity); */
					if(loginInfo.isSaimed()) {
						accounts.setQty(quantity);
					}else {
						int qu=(int)quantity ;
						accounts.setQuantity(qu);
					}
					accounts.setIpdid(ipdid);
					accounts.setStdchargeid(rs.getString(12));
					accounts.setAssesmentid(Integer.toString(subchargeid));
					accounts.setCommencing(DateTimeUtils.getCommencingDate1(date));
					totdisc=totdisc+(rs.getDouble(19)*quantity);
					accounts.setTotalDisc(totdisc);
					boolean isSTdCharge =getIfSTdCharge(rs.getString(12));
					accounts.setStdcharge("0");
					accounts.setCurstatus(0);
					accounts.setNewshftcharge(newshftcharge);
					accounts.setOriginalchargename(rs.getString(1));

					Bed bed = getBedLogData(logid);
					String wardname=ipdDAO.getIpdWardName(bed.getWardid());
					String bedname = ipdDAO.getIpdBedName(bed.getBedid());
					accounts.setWard(wardname + " / " + bedname);
					accounts.setWardid(bed.getWardid());
					if(logid==null){
						logid = "0";
					}else if(logid.equals("")){
						logid = "0";
					}
					
					if(!logid.equals("0")){
						String lastShiftedLog = getlastShiftedLog(ipdid,logid);
						
						accounts.setCommencing(bed.getLastmodified());
						if(!lastShiftedLog.equals("")){
							String temp[] = lastShiftedLog.split(" ");
							curdate = DateTimeUtils.getCommencingDate1(temp[0]) ;
						}else{
							curdate = tempcurdate;
						}
					}
					accounts.setDiscount(rs.getDouble(19));
					String pname = "";
					if(practitionerid!=null ){
						if(!practitionerid.equals("")){
						UserProfileDAO userProfileDAO = new JDBCUserProfileDAO(connection);
						UserProfile userProfile = userProfileDAO.getUserprofileDetails(Integer.parseInt(practitionerid));
						pname = userProfile.getInitial() + " " + userProfile.getFirstname() + " " + userProfile.getLastname();
					}
					}

					
					if(isSTdCharge){
						
						String startEndTime= getStdChargeStartEndTime(subchargeid,ipdid); 
						
				
						
						
						if(startEndTime!=null){
							accounts.setStdcharge("1");
							String tempc[] = startEndTime.split("~");
							if(tempc.length>1){
								String t1[] = tempc[0].split(",");
								String t2[] = tempc[1].split(",");
								String resultdatetime = "";
								for(int i=0;i<t2.length;i++){
									resultdatetime = resultdatetime + t1[i] + " - " + t2[i] + " , "; 
								}
								if(resultdatetime.length()!=0){
									resultdatetime = resultdatetime.substring(0,resultdatetime.length()-2);
								}
								int curstatus = getstdonoffcurstatus(subchargeid,ipdid);
								accounts.setCurstatus(curstatus);
								if(curstatus==1){
									resultdatetime = resultdatetime + " , " + t1[t1.length-1];
								}
								if(!pname.equals("")){
									//accounts.setAppointmentType(pname + " / " + apptName+" ("+resultdatetime+") ");
									accounts.setAppointmentType(apptName+" ("+resultdatetime+") ");
								}else{
									accounts.setAppointmentType(apptName+" ("+resultdatetime+") ");
								}
								
							}else{
								accounts.setAppointmentType(apptName + " ("+tempc[0]+")");
								int curstatus = getstdonoffcurstatus(subchargeid,ipdid);
								accounts.setCurstatus(curstatus);
							}
							
						} else {
							accounts.setAppointmentType(apptName + " ("+accounts.getCommencing()+" - "+curdate+")");
						}
						
					}else {
						if(!logid.equals("0")){
							accounts.setAppointmentType(apptName + " ("+accounts.getCommencing()+" - "+curdate+")");
						}else{
							accounts.setAppointmentType(apptName + " ("+accounts.getCommencing()+"");
						}
						
					}
					accounts.setAptmname(apptName);
					accounts.setPayBy(payby);
					accounts.setChargeid(chargeid);
					//accounts.setAppointmentType(apptName);
					if(rs.getString(17).equals("0")){
						
					String codeforapt=getcodeforapt(rs.getString(12),apmtchargeid);
					if(loginInfo.getIskunal()==1){
						if(codeforapt==null){
							codeforapt="";
						}
						else if(codeforapt.equals("")){
							
								accounts.setApmtcode("NO CODE");
							
						
						}else{
							accounts.setApmtcode(codeforapt);
						}				
					
					}else{
						if(codeforapt==null){
							
							accounts.setApmtcode("");
						}else{
							accounts.setApmtcode(codeforapt);
						}
					}
					
					}else{
						accounts.setApmtcode(rs.getString(17));
					}
					
					accounts.setClientid(Integer.parseInt(clientid));
					accounts.setDiscreq(rs.getBoolean(18));
					accounts.setManualcharge(rs.getString(21));
					accounts.setShowdate(rs.getString(22));
					accounts.setMasterchargetype(rs.getString(23));
				
					list.add(accounts);
				}
				
			}catch(Exception e){
				e.printStackTrace();
			}
			return list;
		
	}

	

	private String getlastShiftedLog(String ipdid, String logid) {
			PreparedStatement preparedStatement = null;
			String result = "";
			String sql = "SELECT lastmodified FROM ipd_bed_change_log where admissionid = "+ipdid+" and id>"+logid+" limit 0,1 ";
			
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

	private Bed getBedLogData(String logid) {
			PreparedStatement preparedStatement = null;
			Bed bed = new Bed();
			String sql = "SELECT wardid,bedid,lastmodified FROM ipd_bed_change_log where id="+logid+" ";
			
			try{
				preparedStatement = connection.prepareStatement(sql);
				ResultSet rs = preparedStatement.executeQuery();
				if(rs.next()){
					bed.setWardid(rs.getString(1));
					bed.setBedid(rs.getString(2));
					String temp[] = rs.getString(3).split(" ");
					bed.setLastmodified(DateTimeUtils.getCommencingDate1(temp[0]));
				}
				
			}catch (Exception e) {
				// TODO: handle exception
			}
			return bed;
		}

	private Vector<Accounts> getEstimateFilteredPreviewChargesList(String name,String clientid,int loginid) {
		PreparedStatement preparedStatement = null;
		Vector<Accounts>list = new Vector<Accounts>();
		String sql = "SELECT apmtType,charge,practitionerName,practitionerId,paybuy,id,commencing,appointmentid,quantity  FROM apm_patient_complete_apmt" +
				" where masterchargetype = '"+name+"' and clientid="+clientid+" and loginid="+loginid+" ";
		
		try{
			preparedStatement = connection.prepareStatement(sql.toString());
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()){
				
				String apptName = rs.getString(1);
				String charge = rs.getString(2);
				if(charge==null){
					charge = "0.0";
				}
				String practitionerName = rs.getString(3);
				String practitionerid = rs.getString(4);
				int payby = rs.getInt(5);
				int addtitonalCharge = 1;
				String chargeid = rs.getString(6);
				String date = rs.getString(7);
				int apptid = rs.getInt(8);
				int quantity = rs.getInt(9);
				
				
				
				Accounts accounts = new Accounts();
				
			/*	if(addtitonalCharge==0){
					Accounts acc = getAppointmentDetailsl(chargeid);
					if(acc.getCommencing()!=null){
						accounts.setCommencing(DateTimeUtils.getInvoiceCommencingDate(acc.getCommencing()));
						accounts.setAppointmentType(acc.getTreatmentEpisodeName() + " " +apptName);
					}
					
					
					boolean dna = getDna(apptid);
					accounts.setDna(dna);
					
				}else{
					String invoicedate = date;
					accounts.setCommencing(DateTimeUtils.getInvoiceCommencingDate(invoicedate));
					accounts.setAppointmentType("Additional Charge("+apptName+")");
				}*/
				
				accounts.setCharges(DateTimeUtils.changeFormat(Double.parseDouble(charge)));
				accounts.setChargeTotal(DateTimeUtils.changeFormat(Double.parseDouble(charge)*quantity));
				accounts.setPractitionerName(practitionerName);
				String practitionerFullName = getPractitionerFullName(practitionerid);
				accounts.setPractitionerName(practitionerFullName);
				accounts.setQuantity(quantity);
				
				accounts.setPayBy(payby);
				accounts.setAppointmentType(apptName);
				list.add(accounts);
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
		return list;
	}
	
	public ArrayList<Master> getAdvanceMasterAssessmentList(int invoiceid){
		//invoiceid--;
		PreparedStatement preparedStatement = null;
		ArrayList<Master>list = new ArrayList<Master>();
		StringBuffer sql = new StringBuffer();
		
		sql.append("SELECT masterchargetype FROM apm_credit_account_assesments where invoiceid = "+invoiceid+" and apmtType!='Pre_Payment' group by masterchargetype ");
		
		
		try{
			preparedStatement = connection.prepareStatement(sql.toString());
			ResultSet  rs = preparedStatement.executeQuery();
			while(rs.next()){
				Master master = new Master();
				master.setName(rs.getString(1));
				
				Vector<Accounts> assesmentList = getAdvanceFilteredPreviewChargesList(invoiceid,master.getName());
				master.setAssesmentList(assesmentList);
				
				list.add(master);
			}
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		return list;
	}
	
	public Vector<Accounts> getAdvancePreviewChargesList(int invoiceid) {
		//invoiceid--;
		PreparedStatement preparedStatement = null;
		DateTimeUtils dateTimeUtils = new DateTimeUtils();
		Vector<Accounts>list = new Vector<Accounts>();
		//String sql = "SELECT apmtType,charge,practitionerName,practitionerId,paybuy FROM apm_invoice_assesments where invoiceid = "+invoiceid+" ";
		
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT apmtType,charge,quantity,commencing FROM apm_credit_account_assesments where invoiceid = "+invoiceid+" and apmtType!='Pre_Payment' ");		
		try{
			preparedStatement = connection.prepareStatement(sql.toString());
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()){
				
				String apptName = rs.getString(1);
				String charge = rs.getString(2);
				if(charge==null){
					charge = "0.0";
				}
				
				int quantity = rs.getInt(3);
				String date = rs.getString(4);
				
				
				
				Accounts accounts = new Accounts();
				String invoicedate = date;
				accounts.setCommencing(DateTimeUtils.getInvoiceCommencingDate(invoicedate));
				accounts.setCharges(dateTimeUtils.changeFormat(Double.parseDouble(charge)));
				accounts.setChargeTotal(dateTimeUtils.changeFormat(Double.parseDouble(charge)*quantity));
				accounts.setQuantity(quantity);
				accounts.setAppointmentType(apptName);
				list.add(accounts);
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
		return list;
	}

	

	private Vector<Accounts> getAdvanceFilteredPreviewChargesList(
			int invoiceid, String masterchargetype) {
		PreparedStatement preparedStatement = null;
		DateTimeUtils dateTimeUtils = new DateTimeUtils();
		Vector<Accounts>list = new Vector<Accounts>();
		//String sql = "SELECT apmtType,charge,practitionerName,practitionerId,paybuy FROM apm_invoice_assesments where invoiceid = "+invoiceid+" ";
		
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT apmtType,charge,quantity,commencing FROM apm_credit_account_assesments where invoiceid = "+invoiceid+" and masterchargetype = '"+masterchargetype+"' order by apmtType ");		
		try{
			preparedStatement = connection.prepareStatement(sql.toString());
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()){
				
				String apptName = rs.getString(1);
				String charge = rs.getString(2);
				if(charge==null){
					charge = "0.0";
				}
				
				int quantity = rs.getInt(3);
				String date = rs.getString(4);
				
				
				
				Accounts accounts = new Accounts();
				String invoicedate = date;
				accounts.setCommencing(DateTimeUtils.getInvoiceCommencingDate(invoicedate));
				accounts.setCharges(dateTimeUtils.changeFormat(Double.parseDouble(charge)));
				accounts.setChargeTotal(dateTimeUtils.changeFormat(Double.parseDouble(charge)*quantity));
				accounts.setQuantity(quantity);
				accounts.setAppointmentType(apptName);
				list.add(accounts);
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
		return list;
	}
	
	public ArrayList<Master> getKunalMasterAssessmentList(int invoiceid, String filterbydate,LoginInfo loginInfo){
		PreparedStatement preparedStatement = null;
		boolean discstatus=false;
		ArrayList<Master>list = new ArrayList<Master>();
		StringBuffer sql = new StringBuffer();
		double subtotal=0;
		
		sql.append("select masterchargetype,discper,discrs,tpkg from  apm_charges_invoice  inner join apm_charges_assesment on ");
		sql.append("apm_charges_assesment.chargeinvoiceid = apm_charges_invoice.id ");
		sql.append("inner join apm_invoice on apm_invoice.id = apm_charges_assesment.invoiceid ");
		sql.append("inner join apm_invoice_assesments on apm_invoice_assesments.invoiceid = apm_invoice.id ");
		sql.append("where apm_charges_invoice.id = "+invoiceid+" group by masterchargetype ");
		
		
		/*
		 * sql.
		 * append("select masterchargetype,discper,discrs,tpkg from  apm_charges_invoice inner join apm_invoice_assesments_view on "
		 * );
		 * sql.append(" apm_invoice_assesments_view.invoiced = apm_charges_invoice.id "
		 * ); sql.append("where apm_charges_invoice.id = "
		 * +invoiceid+" group by masterchargetype ");
		 */
		
		if(!loginInfo.isInvoice_charge_seqno()){
		if(loginInfo.getIskunal()==0){
			sql.append("order by FIELD(masterchargetype,'Bed Charge','Ipd Registration Charge') desc,masterchargetype asc ");
		}else{
			sql.append("order by sqno ");
		}
		}else{
			sql.append("order by sqno ");
		}
		
		try{
			double temptaxtype1=0,temptaxtype2=0,temptaxtype3=0;
			preparedStatement = connection.prepareStatement(sql.toString());
			ResultSet  rs = preparedStatement.executeQuery();
			while(rs.next()){
				Master master = new Master();
				master.setName(DateTimeUtils.isNull(rs.getString(1)));
			
				String breakage =  getMasterBreakage(master.getName());
				/*if(master.getName().equals("Pathlab")){
					master.setName("Pathology");
				}*/
				master.setKrackage(breakage);;
				Vector<Accounts> assesmentList= new  Vector<Accounts>();
				if (loginInfo.getClinicUserid().equals("aureus") && rs.getInt(4)>0) {
					
				}else{
					
				
				if(loginInfo.getIskunal()==1){
					assesmentList=getFilteredPreviewChargesListForKunal(invoiceid, master.getName(), filterbydate,loginInfo);
				}else if(rs.getInt(4)>0){
					assesmentList=getFilteredPreviewChargesListForKunal(invoiceid, master.getName(), filterbydate,loginInfo);
				}
				else if(loginInfo.isInvoice_groupby()){
					assesmentList=getFilteredPreviewChargesListForKunal(invoiceid, master.getName(), filterbydate,loginInfo);
				}
				else{
					assesmentList = getFilteredPreviewChargesList(invoiceid,master.getName(),filterbydate);
				}
				}
				master.setAssesmentList(assesmentList);
				//  kunal ipd print
				String discper=rs.getString(2);
				double discrs=rs.getDouble(3);
				
				if(discper==null){
					discper="0";
				}
				if(discper.equals("")){
					discper="0";
				}
				if(loginInfo.getIskunal()==1){
					if(!discper.equals("0")){
				 subtotal = getMasterSubtotalKunal(invoiceid,master.getName(),0);
					}
					else if(discrs!=0){
						subtotal = getMasterSubtotalKunal(invoiceid,master.getName(),0);
					}else{ 
						subtotal = getMasterSubtotal(invoiceid,master.getName(),0);
					}
				}else if(rs.getInt(4)>0){
					if(!discper.equals("0")){
						 subtotal = getMasterSubtotalKunal(invoiceid,master.getName(),0);
							}
							else if(discrs!=0){
								subtotal = getMasterSubtotalKunal(invoiceid,master.getName(),0);
							}else{ 
								subtotal = getMasterSubtotal(invoiceid,master.getName(),0);
							}
				}
				else{ 
				subtotal = getMasterSubtotal(invoiceid,master.getName(),0);
			}
				master.setCharge(DateTimeUtils.changeFormat(subtotal));
				// 
				int size1 = assesmentList.size();
				if(assesmentList.size()>0){
				
				{
					 discstatus=assesmentList.get(size1-1).isDiscstatus();
					 double t1=assesmentList.get(size1-1).getTaxtype1();
					 master.setTaxtype1(t1+temptaxtype1);
					 temptaxtype1=master.getTaxtype1();
					 master.setTaxtype2(assesmentList.get(size1-1).getTaxtype2()+temptaxtype2);
					 temptaxtype2=master.getTaxtype2();
					 master.setTaxtype3(assesmentList.get(size1-1).getTaxtype3()+temptaxtype3);
					 temptaxtype3=master.getTaxtype3();
				}
				}
				master.setDiscstatus(discstatus);
				master.setInvoiceid(invoiceid);
				
				if(loginInfo.getClinicUserid().equals("pcsadmin")){
					
					master.setCharge(DateTimeUtils.changeFormat(assesmentList.get(size1-1).getTotalunitCharge()));
				}
				
				
				list.add(master);
			}
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		return list;
	}

	
	private String getMasterBreakage(String name) {
		PreparedStatement preparedStatement = null;
		String result = "";
		String sql = "select breakage from apm_newchargetype where name = '"+name+"' ";
		
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
	
	
	public ArrayList<Master> getMasterAssessmentPmntRptList(int invoiceid, String fdate, String tdate, String uid){
		PreparedStatement preparedStatement = null;
		boolean discstatus=false;
		ArrayList<Master>list = new ArrayList<Master>();
		StringBuffer sql = new StringBuffer();
		
		
		  sql.append("select masterchargetype ,sum(charge * quantity) from apm_charges_payment inner join apm_invoice_assesments on "); 
		  sql.append("apm_invoice_assesments.invoiced = apm_charges_payment.chargeinvoiceid "); 
		  sql.append("where ");
		  sql.append(" apm_charges_payment.chargeinvoiceid = "+invoiceid+" ");
		 
		
		//sql.append("select masterchargetype ,sum(charge * quantity) from apm_invoice_assesments_view where invoiced='"+invoiceid+"' ");
		
		  if(uid==null||uid.equals("0")||uid.equals("")){
		 
		}else{ sql.append(" and apm_charges_payment.userid='"+uid+"'   "); }
		 
		sql.append("group by masterchargetype ");
		
		try{
			preparedStatement = connection.prepareStatement(sql.toString());
			ResultSet  rs = preparedStatement.executeQuery();
			while(rs.next()){
				Master master = new Master();
				master.setName(rs.getString(1));
				
				Vector<Accounts> assesmentList = getFilteredPreviewChargesPmntRptList(invoiceid,master.getName(),uid);
				master.setAssesmentList(assesmentList);
				
				double subtotal = rs.getDouble(2);
				master.setCharge(DateTimeUtils.changeFormat(subtotal));
				// 
				int size1 = assesmentList.size();
				{
					 discstatus=assesmentList.get(size1-1).isDiscstatus();
					 
				}
				master.setDiscstatus(discstatus);
				master.setInvoiceid(invoiceid);
				list.add(master);
			}
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		return list;
	}
	
	public Vector<Accounts> getFilteredPreviewChargesPmntRptList(int invoiceid,String masterchargetype,String uid) {
		boolean discstatus=false;
		PreparedStatement preparedStatement = null;
		DateTimeUtils dateTimeUtils = new DateTimeUtils();
		IpdDAO ipdDAO = new JDBCIpdDAO(connection);
		BedDao bedDao = new JDBCBedDao(connection);
		ThirdPartyDAO thirdPartyDAO = new JDBCThirdPartyDAO(connection);
		Vector<Accounts>list = new Vector<Accounts>();
		//String sql = "SELECT apmtType,charge,practitionerName,practitionerId,paybuy FROM asmnt_view where invoiceid = "+invoiceid+" ";
		
		/*StringBuffer sql = new StringBuffer();
		sql.append("SELECT apmtType,charge,apm_invoice.practitionerName,apm_invoice.practitionerId,paybuy,apmt_charge_type,apm_invoice.id,asmnt_view.commencing,apm_invoice.appointmentid,asmnt_view.quantity,asmnt_view.id,asmnt_view.chargeid,asmnt_view.std_charge_id,asmnt_view.ipdid,apm_invoice.ginvstid,unitcharge,chargedisc,asmnt_view.logid,pkgcode ");
		sql.append("FROM apm_invoice inner join asmnt_view on ");
		sql.append("asmnt_view.invoiceid = apm_invoice.id inner join apm_charges_assesment ");
		sql.append("on apm_charges_assesment.invoiceid = apm_invoice.id ");
		sql.append("where apm_charges_assesment.chargeinvoiceid="+invoiceid+" and masterchargetype = '"+masterchargetype+"'  ");
		if(filterbydate.equals("1")){
			sql.append("order by asmnt_view.commencing ");
		}else{
			sql.append("order by apmtType ");
		}
		
*/	
		
		StringBuffer sql = new StringBuffer();
		/*
		 * sql.
		 * append("select apmtType,charge,quantity,chargedisc,unitcharge from apm_charges_payment inner join apm_invoice_assesments on "
		 * ); sql.
		 * append("apm_invoice_assesments.invoiced = apm_charges_payment.chargeinvoiceid "
		 * ); sql.append("where  ");
		 * sql.append(" apm_charges_payment.chargeinvoiceid = "+invoiceid+" ");
		 */
		sql.append("select apmtType,charge,quantity,chargedisc,unitcharge from apm_invoice_assesments ");
		
		sql.append(" where invoiced='"+invoiceid+"'  ");
		
		/*
		 * if(uid==null||uid.equals("0")||uid.equals("")){
		 * 
		 * }else{ sql.append(" and  apm_charges_payment.userid='"+uid+"' "); }
		 */
		sql.append("and masterchargetype = '"+masterchargetype+"' group by id order by apmtType  ");

		try{
			preparedStatement = connection.prepareStatement(sql.toString());
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()){
				
				Accounts accounts = new Accounts();
				
				String apptName = rs.getString(1);
				String  sectionroom = "";
				if(masterchargetype.equals("INVESTIGATION")){
					 sectionroom = getSectionRoom(apptName);
					if(apptName.contains("~")){
						String tmp[] = apptName.split("~");
						apptName = tmp[0];
					}
					
					//set investigation alternate name
					String tpid = getInvoiceTpId(invoiceid);
					ThirdParty thirdParty = thirdPartyDAO.getThirdPartyDetails(tpid);
					if(thirdParty.isMaintp()){
						String alternamename = getInvestigationAltName(rs.getString(1));
						if(alternamename !=null){
							if(!alternamename.equals(" ")){
								apptName = alternamename;
							}
						}
					}
					
					if(sectionroom==null){
						
					}
					else if(!sectionroom.equals("")){
						apptName = apptName + " (Room Nos : "+sectionroom+")";
					}
					
					
				}
				double charge=rs.getDouble(2),
						chargedisc=rs.getDouble(4),
						unitcharge=rs.getDouble(5);
				int quantity=rs.getInt(3);
				if(unitcharge==0){
					unitcharge = charge;
				}
				accounts.setCharges(""+charge);
				accounts.setQuantity(quantity);
				accounts.setUnitcharge(""+unitcharge);
				accounts.setChargedisc(""+chargedisc);
				accounts.setAppointmentType(apptName);
				list.add(accounts);
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
		return list;
	}
	

	public ArrayList<Master> getMasterAssessmentList(int invoiceid, String filterbydate){
		PreparedStatement preparedStatement = null;
		boolean discstatus=false;
		ArrayList<Master>list = new ArrayList<Master>();
		StringBuffer sql = new StringBuffer();
		
		/*sql.append("select masterchargetype from  apm_charges_invoice  inner join apm_charges_assesment on ");
		sql.append("apm_charges_assesment.chargeinvoiceid = apm_charges_invoice.id ");
		sql.append("inner join apm_invoice on apm_invoice.id = apm_charges_assesment.invoiceid ");
		sql.append("inner join apm_invoice_assesments on apm_invoice_assesments.invoiceid = apm_invoice.id ");
		sql.append("where apm_charges_invoice.id = "+invoiceid+" group by masterchargetype ");
		sql.append("order by FIELD(masterchargetype, 'Registration Charge','Ipd Registration Charge') desc,masterchargetype asc ");
		 */
		
		sql.append("select masterchargetype,sum(charge*quantity) from  apm_charges_assesment  inner join apm_invoice on ");
		sql.append("apm_invoice.id = apm_charges_assesment.invoiceid ");
		sql.append("inner join apm_invoice_assesments on ");
		sql.append("apm_invoice_assesments.invoiceid = apm_invoice.id where apm_charges_assesment.chargeinvoiceid = "+invoiceid+" ");
		sql.append("group by masterchargetype ");
		
	/*	boolean isinvoiced = checknewchargeinvoiced(invoiceid);
		if(isinvoiced){
			sql = new StringBuffer();
			sql.append("select masterchargetype,sum(charge*quantity) from  apm_invasment ");
			sql.append("where invoiced = "+invoiceid+" group by masterchargetype ");
		}*/
		
		
		try{
			preparedStatement = connection.prepareStatement(sql.toString());
			ResultSet  rs = preparedStatement.executeQuery();
			while(rs.next()){
				Master master = new Master();
				master.setName(rs.getString(1));
				
				Vector<Accounts> assesmentList = getFilteredPreviewChargesList(invoiceid,master.getName(),filterbydate);
				master.setAssesmentList(assesmentList);
				
				//double subtotal = getMasterSubtotal(invoiceid,master.getName(),0);
				double subtotal = rs.getDouble(2);
				master.setCharge(DateTimeUtils.changeFormat(subtotal));
				// 
				int size1 = assesmentList.size();
				{
					 discstatus=assesmentList.get(size1-1).isDiscstatus();
					 
				}
				master.setDiscstatus(discstatus);
				master.setInvoiceid(invoiceid);
				list.add(master);
			}
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		return list;
	}
	
	
	public ArrayList<Master> getMasterAssessmentListForCA(int invoiceid, String filterbydate){
		PreparedStatement preparedStatement = null;
		boolean discstatus=false;
		ArrayList<Master>list = new ArrayList<Master>();
		StringBuffer sql = new StringBuffer();
		
		/*sql.append("select masterchargetype from  apm_charges_invoice  inner join apm_charges_assesment on ");
		sql.append("apm_charges_assesment.chargeinvoiceid = apm_charges_invoice.id ");
		sql.append("inner join apm_invoice on apm_invoice.id = apm_charges_assesment.invoiceid ");
		sql.append("inner join apm_invoice_assesments on apm_invoice_assesments.invoiceid = apm_invoice.id ");
		sql.append("where apm_charges_invoice.id = "+invoiceid+" group by masterchargetype ");
		sql.append("order by FIELD(masterchargetype, 'Registration Charge','Ipd Registration Charge') desc,masterchargetype asc ");
		 */
		
		sql.append("select masterchargetype,sum(charge*quantity) from  apm_charges_assesment  inner join apm_invoice on ");
		sql.append("apm_invoice.id = apm_charges_assesment.invoiceid ");
		sql.append("inner join apm_invoice_assesments on ");
		sql.append("apm_invoice_assesments.invoiceid = apm_invoice.id where apm_charges_assesment.chargeinvoiceid = "+invoiceid+" ");
		sql.append("group by masterchargetype ");
		
	/*	boolean isinvoiced = checknewchargeinvoiced(invoiceid);
		if(isinvoiced){
			sql = new StringBuffer();
			sql.append("select masterchargetype,sum(charge*quantity) from  apm_invasment ");
			sql.append("where invoiced = "+invoiceid+" group by masterchargetype ");
		}*/
		
		
		try{
			preparedStatement = connection.prepareStatement(sql.toString());
			ResultSet  rs = preparedStatement.executeQuery();
			while(rs.next()){
				Master master = new Master();
				master.setName(rs.getString(1));
				
				Vector<Accounts> assesmentList = getFilteredPreviewChargesListForCA(invoiceid,master.getName(),filterbydate);
				master.setAssesmentList(assesmentList);
				
				//double subtotal = getMasterSubtotal(invoiceid,master.getName(),0);
				double subtotal = rs.getDouble(2);
				master.setCharge(DateTimeUtils.changeFormat(subtotal));
				// 
				int size1 = assesmentList.size();
				{
					 discstatus=assesmentList.get(size1-1).isDiscstatus();
					 
				}
				master.setDiscstatus(discstatus);
				master.setInvoiceid(invoiceid);
				list.add(master);
			}
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		return list;
	}
	
	
	private boolean checknewchargeinvoiced(int invoiceid) {
		PreparedStatement preparedStatement = null;
		boolean result = false;
		String sql = "select id from apm_invoice_assesments where invoiced="+invoiceid+" ";
		
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

	private double getdMasterSubtotal(String masterchargetype,String clietid, String ipdid) {
		PreparedStatement preparedStatement = null;
		double result = 0;
		StringBuffer sql = new StringBuffer();
		
		sql.append("select charge,quantity from  apm_invoice_assesments   ");
		sql.append("inner join apm_invoice on apm_invoice.id = apm_invoice_assesments.invoiceid ");
		sql.append("where  masterchargetype = '"+masterchargetype+"' and apm_invoice_assesments.clientid = "+clietid+"  and chargetype!='Submit' ");
		if(ipdid!=null){
			if(!ipdid.equals("")){
				sql.append("and apm_invoice_assesments.ipdid='"+ipdid+"' ");
			}
		}
		try{
			preparedStatement = connection.prepareStatement(sql.toString());
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()){
				result = result + (rs.getDouble(1)*rs.getInt(2));
			}
			
		}catch (Exception e) {
			// TODO: handle exception
		}
		
		return result;
	}
	
	private double getMasterSubtotal(int invoiceid, String masterchargetype, int wardid) {
		PreparedStatement preparedStatement = null;
		double result = 0;
		double charge=0;
		StringBuffer sql = new StringBuffer();
		
		/*
		 * sql.
		 * append("select charge,quantity,chargedisc,unitcharge from  apm_charges_invoice  inner join apm_charges_assesment on "
		 * );
		 * sql.append("apm_charges_assesment.chargeinvoiceid = apm_charges_invoice.id "
		 * ); sql.
		 * append("inner join apm_invoice on apm_invoice.id = apm_charges_assesment.invoiceid "
		 * ); sql.
		 * append("inner join apm_invoice_assesments on apm_invoice_assesments.invoiceid = apm_invoice.id "
		 * ); sql.append("where apm_charges_invoice.id = "
		 * +invoiceid+" and masterchargetype = '"+masterchargetype+"' ");
		 */
		sql.append("select charge,quantity,chargedisc,unitcharge from apm_invoice inner join apm_invoice_assesments on ");
	    sql.append("apm_invoice_assesments.invoiceid = apm_invoice.id where apm_invoice_assesments.invoiced = '"+invoiceid+"' and ");
	    sql.append("masterchargetype = '"+masterchargetype+"' ");
		if(wardid!=0){
			sql.append("and apm_invoice_assesments.wardid='"+wardid+"' ");
		}
		
		try{
			preparedStatement = connection.prepareStatement(sql.toString());
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()){
				if(rs.getDouble(3)>0){
					charge=rs.getDouble(4);
				}else{
					charge=rs.getDouble(1);
				}
				result = result + (charge*rs.getInt(2));
			}
			
		}catch (Exception e) {
			// TODO: handle exception
		}
		
		return result;
	}

	public Vector<Accounts> getFilteredPreviewChargesList(int invoiceid,String masterchargetype,String filterbydate) {
		boolean discstatus=false;
		PreparedStatement preparedStatement = null;
		DateTimeUtils dateTimeUtils = new DateTimeUtils();
		IpdDAO ipdDAO = new JDBCIpdDAO(connection);
		BedDao bedDao = new JDBCBedDao(connection);
		ThirdPartyDAO thirdPartyDAO = new JDBCThirdPartyDAO(connection);
		Vector<Accounts>list = new Vector<Accounts>();
		//String sql = "SELECT apmtType,charge,practitionerName,practitionerId,paybuy FROM apm_invoice_assesments where invoiceid = "+invoiceid+" ";
		
		StringBuffer sql = new StringBuffer();
		/*
		 * sql.
		 * append("SELECT apmtType,charge,apm_invoice.practitionerName,apm_invoice.practitionerId,paybuy,apmt_charge_type,apm_invoice.id,apm_invoice_assesments.commencing,apm_invoice.appointmentid,apm_invoice_assesments.quantity,apm_invoice_assesments.id,apm_invoice_assesments.chargeid,apm_invoice_assesments.std_charge_id,apm_invoice_assesments.ipdid,apm_invoice.ginvstid,unitcharge,chargedisc,apm_invoice_assesments.logid,pkgcode,apm_invoice_assesments.tax1,apm_invoice_assesments.tax2,apm_invoice_assesments.tax3 ,chargedescript,manualcharge,showing_date "
		 * ); sql.append("FROM apm_invoice inner join apm_invoice_assesments on "); sql.
		 * append("apm_invoice_assesments.invoiceid = apm_invoice.id inner join apm_charges_assesment "
		 * ); sql.append("on apm_charges_assesment.invoiceid = apm_invoice.id ");
		 * sql.append("where apm_charges_assesment.chargeinvoiceid="
		 * +invoiceid+" and masterchargetype = '"+masterchargetype+"'  ");
		 */ /* @rahul remove inner join apm_charges_assesment */
		
		
		sql.append("SELECT apmtType,charge,apm_invoice.practitionerName,apm_invoice.practitionerId,paybuy,apmt_charge_type,apm_invoice.id,apm_invoice_assesments.commencing,apm_invoice.appointmentid,apm_invoice_assesments.quantity,apm_invoice_assesments.id,apm_invoice_assesments.chargeid,apm_invoice_assesments.std_charge_id,apm_invoice_assesments.ipdid,apm_invoice.ginvstid,unitcharge,chargedisc,apm_invoice_assesments.logid,pkgcode,apm_invoice_assesments.tax1,apm_invoice_assesments.tax2,apm_invoice_assesments.tax3 ,chargedescript,manualcharge,showing_date ");
		sql.append("FROM apm_invoice inner join apm_invoice_assesments on ");
		sql.append("apm_invoice_assesments.invoiceid = apm_invoice.id ");
		sql.append("where apm_invoice_assesments.invoiced="+invoiceid+" and masterchargetype = '"+masterchargetype+"'  ");
		
		/*
		 * sql.
		 * append("SELECT apmtType,charge,apm_invoice.practitionerName,apm_invoice.practitionerId,paybuy,apmt_charge_type,apm_invoice.id, "
		 * +
		 * "		apm_invoice_assesments_view.commencing,apm_invoice.appointmentid,apm_invoice_assesments_view.quantity,apm_invoice_assesments_view.id,"
		 * +
		 * "		apm_invoice_assesments_view.chargeid,apm_invoice_assesments_view.std_charge_id,apm_invoice_assesments_view.ipdid,apm_invoice.ginvstid,"
		 * +
		 * "		unitcharge,chargedisc,apm_invoice_assesments_view.logid,pkgcode,apm_invoice_assesments_view.tax1,apm_invoice_assesments_view.tax2,"
		 * +
		 * "		apm_invoice_assesments_view.tax3 ,chargedescript,manualcharge,showing_date "
		 * ); sql.append("from apm_invoice_assesments_view inner join apm_invoice on ");
		 * sql.append("apm_invoice.id=apm_invoice_assesments_view.invoiceid ");
		 * sql.append("where invoiced="+invoiceid+" and masterchargetype = '"
		 * +masterchargetype+"'  ");
		 */
		

		/*boolean isinvoiced = checknewchargeinvoiced(invoiceid);
		if(isinvoiced){
			sql = new StringBuffer();
			sql.append("SELECT apmtType,charge,apm_invoice.practitionerName,apm_invoice.practitionerId,paybuy,apmt_charge_type,apm_invoice.id, ");
			sql.append("apm_invasment.commencing,apm_invoice.appointmentid,apm_invasment.quantity,apm_invasment.id,chargeid,std_charge_id, ");
			sql.append("apm_invasment.ipdid,apm_invoice.ginvstid,unitcharge,chargedisc,logid,pkgcode ");
			sql.append("from apm_invasment ");
			sql.append("inner join apm_invoice on apm_invoice.id = apm_invasment.invoiceid ");
			sql.append("where invoiced = "+invoiceid+" ");
					
		}*/
		if(filterbydate.equals("1")){
			sql.append("order by apm_invoice_assesments.commencing ");
		}else{
			sql.append("order by apmtType ");
		}
		
		
		double temptaxtype1=0,temptaxtype2=0,temptaxtype3=0,totalunit=0;
		try{
			preparedStatement = connection.prepareStatement(sql.toString());
			ResultSet rs = preparedStatement.executeQuery();
			
			InvestigationDAO investigationDAO= new JDBCInvestigationDAO(connection);
			while(rs.next()){
				
				String apptName = rs.getString(1);
				String  sectionroom = "";
				if(masterchargetype.equals("INVESTIGATION")){
					 sectionroom = getSectionRoom(apptName);
					if(apptName.contains("~")){
						String tmp[] = apptName.split("~");
						apptName = tmp[0];
					}
					
					//set investigation alternate name
					String tpid = getInvoiceTpId(invoiceid);
					ThirdParty thirdParty = thirdPartyDAO.getThirdPartyDetails(tpid);
					if(thirdParty.isMaintp()){
						String alternamename = getInvestigationAltName(rs.getString(1));
						if(alternamename !=null){
							if(!alternamename.equals(" ")){
								apptName = alternamename;
							}
						}
					}
					
					if(sectionroom==null){
						
					}
					else if(!sectionroom.equals("")){
						apptName = apptName + " (Room Nos : "+sectionroom+")";
					}
					
					
				}
				String charge ="";
				if(rs.getDouble(17)>0){
					charge= rs.getString(16);
				}else{
				charge= rs.getString(2);
				}
				if(charge==null){
					charge = "0.0";
				}
				String practitionerName = rs.getString(3);
				String practitionerid = rs.getString(4);
				int payby = rs.getInt(5);
				int addtitonalCharge = rs.getInt(6);
				String chargeid = rs.getString(7);
				String date = rs.getString(8);
				int apptid = rs.getInt(9);
				int quantity = rs.getInt(10);
				int subchargeid = rs.getInt(11);
				int apmtchargeid = rs.getInt(12);;
				
				
				Accounts accounts = new Accounts();
				
				if(addtitonalCharge==0){
					Accounts acc = getAppointmentDetailsl(chargeid);
					if(acc.getCommencing()!=null){
						accounts.setCommencing(DateTimeUtils.getInvoiceCommencingDate(acc.getCommencing()));
						accounts.setAppointmentType(acc.getTreatmentEpisodeName() + " " +apptName);
					}
					
					
					boolean dna = getDna(apptid);
					accounts.setDna(dna);
					
				}else{
					String invoicedate = date;
					accounts.setCommencing(DateTimeUtils.getInvoiceCommencingDate(invoicedate));
					accounts.setAppointmentType("Additional Charge("+apptName+")");
				}
				
				accounts.setCharges(dateTimeUtils.changeFormat(Double.parseDouble(charge)));
				accounts.setChargeTotal(dateTimeUtils.changeFormat(Double.parseDouble(charge)*quantity));
				accounts.setPractitionerName(practitionerName);
				String practitionerFullName = getPractitionerFullName(practitionerid);
				accounts.setPractitionerName(practitionerFullName);
				accounts.setQuantity(quantity);
				accounts.setSubchargeid(subchargeid);
				
				boolean isSTdCharge =getIfSTdCharge(rs.getString(13));
				if(isSTdCharge){
					/*String startEndTime= getStdChargeStartEndTime(subchargeid,rs.getString(14));
					Accounts account= showonofftime(subchargeid, rs.getString(14));
					
					if(startEndTime!=null){
						String tempc[] = startEndTime.split("~");
						String t1[] = tempc[0].split(",");
						String t2[] = tempc[1].split(",");
						
						String resultdatetime = "";
						for(int i=0;i<t2.length;i++){
							resultdatetime = resultdatetime + t1[i] + " - " + t2[i] + " , "; 
						}
						
						if(resultdatetime.length()!=0){
							resultdatetime = resultdatetime.substring(0,resultdatetime.length()-1);
						}
						int curstatus = getstdonoffcurstatus(subchargeid,rs.getString(14));
						if(curstatus==1){
							resultdatetime = resultdatetime + " , " + t1[t1.length-1];
						}
						
						accounts.setAppointmentType(apptName+" ("+resultdatetime+") ");
					} else {
						accounts.setAppointmentType(apptName);
					}*/
					// 05-08-2019 hidede above code as per dipanjay requirement.
					accounts.setAppointmentType(apptName);
				}else {
					accounts.setAppointmentType(apptName);
				}
				
				accounts.setPayBy(payby);
				
				
				String tpcode = getTpChargeCode(apmtchargeid);
				accounts.setTpcode(tpcode);
				
				/*if(payby==0){
					accounts.setTpcode(" ");
				}*/
				
				//  22 dec 2017 hide package charges
				int ginvstid = checkIsInvestigationPackage(rs.getString(15));
				accounts.setPkginvissid(String.valueOf(ginvstid));
				
				//lokesh 04/12/18
				
				Investigation investigation= investigationDAO.getEditInvestigation(rs.getString(15));
				ArrayList<Investigation> invstlist= new ArrayList<Investigation>();
				if(investigation!=null){
					if(investigation.getPackagename()!=null){
						if(!investigation.getPackagename().equals("0")){
							invstlist= investigationDAO.getInvestigationsFrompakages(investigation.getInvreq(), investigation.getPackagename());

						}
					}
					
				}
				accounts.setInvstlist(invstlist);
				double unitcharge = rs.getDouble(16);
				double chargedisc = rs.getDouble(17);
				
			if(DateTimeUtils.isNull(accounts.getTpcode()).equals("")) {	
				if( rs.getString(19)!=null){
					accounts.setTpcode(rs.getString(19));
				}
			}	
				
				if(accounts.getTpcode()==null){
					accounts.setTpcode("0");
				}
				if(accounts.getTpcode().equals("")){
					accounts.setTpcode("No Code");
				}
				if(accounts.getTpcode().equals("0")){
					accounts.setTpcode("No Code");
				}
				
				accounts.setIpdid(""+rs.getInt(14));
				
				//  03 July 2018 
				//Bed bed = getBedLogData(""+rs.getInt(14));
				//Bed bed = getBedLogData(""+rs.getInt(14));
				
				
				if(isSTdCharge){
					String logid = rs.getString(18);

					Bed bed = getBedLogData(logid);
					String wardname=ipdDAO.getIpdWardName(bed.getWardid());
					String bedname = ipdDAO.getIpdBedName(bed.getBedid());
					accounts.setWard(wardname + " / " + bedname);
				}else{
					Bed bed = bedDao.getEditIpdData(""+rs.getInt(14));
					String wardname=ipdDAO.getIpdWardName(bed.getWardid());
					String bedname = ipdDAO.getIpdBedName(bed.getBedid());
					accounts.setWard(wardname + " / " + bedname);
				}
									
				
				if(unitcharge==0){
					unitcharge = rs.getDouble(2);
				}
				accounts.setUnitcharge(DateTimeUtils.changeFormat(unitcharge));
				accounts.setChargedisc(DateTimeUtils.changeFormat(chargedisc));
//				 if(chargedisc>0){
						discstatus=true;
//					}
					accounts.setDiscstatus(true);
					
				accounts.setTax1(""+rs.getDouble(20));
				accounts.setTax2(""+rs.getDouble(21));
				accounts.setTax3(""+rs.getDouble(22));
				MasterDAO masterDAO = new JDBCMasterDAO(connection);
				if(!accounts.getTax1().equals("0.0")){
					accounts.setTaxname1(masterDAO.getTaxnamebyType("1"));
				} 
				if(!accounts.getTax2().equals("0.0")){
					accounts.setTaxname2(masterDAO.getTaxnamebyType("2"));
				}
				if(!accounts.getTax3().equals("0.0")){
					accounts.setTaxname3(masterDAO.getTaxnamebyType("3"));
				}
				
				{
					double totaltax=rs.getDouble(20)+rs.getDouble(21)+rs.getDouble(22);
					double unitprice=0;
					if(true){
						unitprice=(Double.parseDouble(accounts.getCharges())/(100.0+totaltax))*100.0;
						accounts.setUnitcharge(DateTimeUtils.changeFormat(unitprice));
					}
					if(unitprice>0.0){
						double taxtype1,taxtype2,taxtype3;
						taxtype1=((rs.getDouble(20))*accounts.getQuantity()/100.0)*unitprice;
						temptaxtype1=taxtype1+temptaxtype1;
						accounts.setTaxtype1((temptaxtype1));
						
						temptaxtype1=accounts.getTaxtype1();
						taxtype2=(rs.getDouble(21)*accounts.getQuantity()/100.0)*unitprice;
						accounts.setTaxtype2((taxtype2+temptaxtype2));
						temptaxtype2=accounts.getTaxtype2();
						taxtype3=(rs.getDouble(22)*accounts.getQuantity()/100.0)*unitprice;
						accounts.setTaxtype3((taxtype3+temptaxtype3));
						temptaxtype3=accounts.getTaxtype3();
					}
				}
				String chargedescript=rs.getString(23);
				if(chargedescript==null){
					chargedescript="";
				}
				accounts.setChargedescription(chargedescript.replaceAll("<br>", ""));
				accounts.setTotalunitCharge(totalunit+DateTimeUtils.convertToDouble(accounts.getUnitcharge()));
				totalunit=accounts.getTotalunitCharge();
				accounts.setManualcharge(rs.getString(24));
				accounts.setShowdate(rs.getString(25));
				list.add(accounts);
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
		return list;
	}
	public Vector<Accounts> getFilteredPreviewChargesListForCA(int invoiceid,String masterchargetype,String filterbydate) {
		boolean discstatus=false;
		PreparedStatement preparedStatement = null;
		DateTimeUtils dateTimeUtils = new DateTimeUtils();
		IpdDAO ipdDAO = new JDBCIpdDAO(connection);
		BedDao bedDao = new JDBCBedDao(connection);
		ThirdPartyDAO thirdPartyDAO = new JDBCThirdPartyDAO(connection);
		Vector<Accounts>list = new Vector<Accounts>();
		//String sql = "SELECT apmtType,charge,practitionerName,practitionerId,paybuy FROM apm_invoice_assesments where invoiceid = "+invoiceid+" ";
		
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT apmtType,charge,apm_invoice.practitionerName,apm_invoice.practitionerId,paybuy,apmt_charge_type,apm_invoice.id,apm_invoice_assesments.commencing,apm_invoice.appointmentid,sum(apm_invoice_assesments.quantity),apm_invoice_assesments.id,apm_invoice_assesments.chargeid,apm_invoice_assesments.std_charge_id,apm_invoice_assesments.ipdid,apm_invoice.ginvstid,unitcharge,chargedisc,apm_invoice_assesments.logid,pkgcode,apm_invoice_assesments.tax1,apm_invoice_assesments.tax2,apm_invoice_assesments.tax3 ,chargedescript ");
		sql.append("FROM apm_invoice inner join apm_invoice_assesments on ");
		sql.append("apm_invoice_assesments.invoiceid = apm_invoice.id inner join apm_charges_assesment ");
		sql.append("on apm_charges_assesment.invoiceid = apm_invoice.id ");
		sql.append("where apm_charges_assesment.chargeinvoiceid="+invoiceid+" and masterchargetype = '"+masterchargetype+"'  ");
		
		/*boolean isinvoiced = checknewchargeinvoiced(invoiceid);
		if(isinvoiced){
			sql = new StringBuffer();
			sql.append("SELECT apmtType,charge,apm_invoice.practitionerName,apm_invoice.practitionerId,paybuy,apmt_charge_type,apm_invoice.id, ");
			sql.append("apm_invasment.commencing,apm_invoice.appointmentid,apm_invasment.quantity,apm_invasment.id,chargeid,std_charge_id, ");
			sql.append("apm_invasment.ipdid,apm_invoice.ginvstid,unitcharge,chargedisc,logid,pkgcode ");
			sql.append("from apm_invasment ");
			sql.append("inner join apm_invoice on apm_invoice.id = apm_invasment.invoiceid ");
			sql.append("where invoiced = "+invoiceid+" ");
					
		}*/
		sql.append(" group by apmtType ");
		if(filterbydate.equals("1")){
			sql.append("order by apm_invoice_assesments.commencing ");
		}else{
			sql.append("order by apmtType ");
		}
		
		
		double temptaxtype1=0,temptaxtype2=0,temptaxtype3=0,totalunit=0;
		try{
			preparedStatement = connection.prepareStatement(sql.toString());
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()){
				
				String apptName = rs.getString(1);
				String  sectionroom = "";
				if(masterchargetype.equals("INVESTIGATION")){
					 sectionroom = getSectionRoom(apptName);
					if(apptName.contains("~")){
						String tmp[] = apptName.split("~");
						apptName = tmp[0];
					}
					
					//set investigation alternate name
					String tpid = getInvoiceTpId(invoiceid);
					ThirdParty thirdParty = thirdPartyDAO.getThirdPartyDetails(tpid);
					if(thirdParty.isMaintp()){
						String alternamename = getInvestigationAltName(rs.getString(1));
						if(alternamename !=null){
							if(!alternamename.equals(" ")){
								apptName = alternamename;
							}
						}
					}
					
					if(sectionroom==null){
						
					}
					else if(!sectionroom.equals("")){
						apptName = apptName + " (Room Nos : "+sectionroom+")";
					}
					
					
				}
				String charge ="";
				if(rs.getDouble(17)>0){
					charge= rs.getString(16);
				}else{
				charge= rs.getString(2);
				}
				if(charge==null){
					charge = "0.0";
				}
				String practitionerName = rs.getString(3);
				String practitionerid = rs.getString(4);
				int payby = rs.getInt(5);
				int addtitonalCharge = rs.getInt(6);
				String chargeid = rs.getString(7);
				String date = rs.getString(8);
				int apptid = rs.getInt(9);
				int quantity = rs.getInt(10);
				int subchargeid = rs.getInt(11);
				int apmtchargeid = rs.getInt(12);;
				
				
				Accounts accounts = new Accounts();
				
				if(addtitonalCharge==0){
					Accounts acc = getAppointmentDetailsl(chargeid);
					if(acc.getCommencing()!=null){
						accounts.setCommencing(DateTimeUtils.getInvoiceCommencingDate(acc.getCommencing()));
						accounts.setAppointmentType(acc.getTreatmentEpisodeName() + " " +apptName);
					}
					
					
					boolean dna = getDna(apptid);
					accounts.setDna(dna);
					
				}else{
					String invoicedate = date;
					accounts.setCommencing(DateTimeUtils.getInvoiceCommencingDate(invoicedate));
					accounts.setAppointmentType("Additional Charge("+apptName+")");
				}
				
				accounts.setCharges(dateTimeUtils.changeFormat(Double.parseDouble(charge)));
				accounts.setChargeTotal(dateTimeUtils.changeFormat(Double.parseDouble(charge)*quantity));
				accounts.setPractitionerName(practitionerName);
				String practitionerFullName = getPractitionerFullName(practitionerid);
				accounts.setPractitionerName(practitionerFullName);
				accounts.setQuantity(quantity);
				accounts.setSubchargeid(subchargeid);
				
				boolean isSTdCharge =getIfSTdCharge(rs.getString(13));
				if(isSTdCharge){
					/*String startEndTime= getStdChargeStartEndTime(subchargeid,rs.getString(14));
					Accounts account= showonofftime(subchargeid, rs.getString(14));
					
					if(startEndTime!=null){
						String tempc[] = startEndTime.split("~");
						String t1[] = tempc[0].split(",");
						String t2[] = tempc[1].split(",");
						
						String resultdatetime = "";
						for(int i=0;i<t2.length;i++){
							resultdatetime = resultdatetime + t1[i] + " - " + t2[i] + " , "; 
						}
						
						if(resultdatetime.length()!=0){
							resultdatetime = resultdatetime.substring(0,resultdatetime.length()-1);
						}
						int curstatus = getstdonoffcurstatus(subchargeid,rs.getString(14));
						if(curstatus==1){
							resultdatetime = resultdatetime + " , " + t1[t1.length-1];
						}
						
						accounts.setAppointmentType(apptName+" ("+resultdatetime+") ");
					} else {
						accounts.setAppointmentType(apptName);
					}*/
					// 05-08-2019 hidede above code as per dipanjay requirement.
					accounts.setAppointmentType(apptName);
				}else {
					accounts.setAppointmentType(apptName);
				}
				
				accounts.setPayBy(payby);
				
				
				String tpcode = getTpChargeCode(apmtchargeid);
				accounts.setTpcode(tpcode);
				
				if(payby==0){
					accounts.setTpcode(" ");
				}
				
				//  22 dec 2017 hide package charges
				int ginvstid = checkIsInvestigationPackage(rs.getString(15));
				accounts.setPkginvissid(String.valueOf(ginvstid));
				
				//lokesh 04/12/18
				InvestigationDAO investigationDAO= new JDBCInvestigationDAO(connection);
				Investigation investigation= investigationDAO.getEditInvestigation(rs.getString(15));
				ArrayList<Investigation> invstlist= new ArrayList<Investigation>();
				if(investigation!=null){
					if(investigation.getPackagename()!=null){
						if(!investigation.getPackagename().equals("0")){
							invstlist= investigationDAO.getInvestigationsFrompakages(investigation.getInvreq(), investigation.getPackagename());

						}
					}
					
				}
				accounts.setInvstlist(invstlist);
				double unitcharge = rs.getDouble(16);
				double chargedisc = rs.getDouble(17);
				
				if(!rs.getString(19).equals("0")){
					accounts.setTpcode(rs.getString(19));
				}
				
				if(accounts.getTpcode()==null){
					accounts.setTpcode("0");
				}
				if(accounts.getTpcode().equals("")){
					accounts.setTpcode("No Code");
				}
				if(accounts.getTpcode().equals("0")){
					accounts.setTpcode("No Code");
				}
				
				accounts.setIpdid(""+rs.getInt(14));
				
				//  03 July 2018 
				//Bed bed = getBedLogData(""+rs.getInt(14));
				//Bed bed = getBedLogData(""+rs.getInt(14));
				
				
				if(isSTdCharge){
					String logid = rs.getString(18);

					Bed bed = getBedLogData(logid);
					String wardname=ipdDAO.getIpdWardName(bed.getWardid());
					String bedname = ipdDAO.getIpdBedName(bed.getBedid());
					accounts.setWard(wardname + " / " + bedname);
				}else{
					Bed bed = bedDao.getEditIpdData(""+rs.getInt(14));
					String wardname=ipdDAO.getIpdWardName(bed.getWardid());
					String bedname = ipdDAO.getIpdBedName(bed.getBedid());
					accounts.setWard(wardname + " / " + bedname);
				}
									
				
				if(unitcharge==0){
					unitcharge = rs.getDouble(2);
				}
				accounts.setUnitcharge(DateTimeUtils.changeFormat(unitcharge));
				accounts.setChargedisc(DateTimeUtils.changeFormat(chargedisc));
//				 if(chargedisc>0){
						discstatus=true;
//					}
					accounts.setDiscstatus(true);
					
				accounts.setTax1(""+rs.getDouble(20));
				accounts.setTax2(""+rs.getDouble(21));
				accounts.setTax3(""+rs.getDouble(22));
				MasterDAO masterDAO = new JDBCMasterDAO(connection);
				if(!accounts.getTax1().equals("0.0")){
					accounts.setTaxname1(masterDAO.getTaxnamebyType("1"));
				} 
				if(!accounts.getTax2().equals("0.0")){
					accounts.setTaxname2(masterDAO.getTaxnamebyType("2"));
				}
				if(!accounts.getTax3().equals("0.0")){
					accounts.setTaxname3(masterDAO.getTaxnamebyType("3"));
				}
				
				{
					double totaltax=rs.getDouble(20)+rs.getDouble(21)+rs.getDouble(22);
					double unitprice=0;
					if(true){
						unitprice=(Double.parseDouble(accounts.getCharges())/(100.0+totaltax))*100.0;
						accounts.setUnitcharge(DateTimeUtils.changeFormat(unitprice));
					}
					if(unitprice>0.0){
						double taxtype1,taxtype2,taxtype3;
						taxtype1=((rs.getDouble(20))*accounts.getQuantity()/100.0)*unitprice;
						temptaxtype1=taxtype1+temptaxtype1;
						accounts.setTaxtype1((temptaxtype1));
						
						temptaxtype1=accounts.getTaxtype1();
						taxtype2=(rs.getDouble(21)*accounts.getQuantity()/100.0)*unitprice;
						accounts.setTaxtype2((taxtype2+temptaxtype2));
						temptaxtype2=accounts.getTaxtype2();
						taxtype3=(rs.getDouble(22)*accounts.getQuantity()/100.0)*unitprice;
						accounts.setTaxtype3((taxtype3+temptaxtype3));
						temptaxtype3=accounts.getTaxtype3();
					}
				}
				String chargedescript=rs.getString(23);
				if(chargedescript==null){
					chargedescript="";
				}
				accounts.setChargedescription(chargedescript.replaceAll("<br>", ""));
				accounts.setTotalunitCharge(totalunit+DateTimeUtils.convertToDouble(accounts.getUnitcharge()));
				totalunit=accounts.getTotalunitCharge();
				list.add(accounts);
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
		return list;
	}

	public String getInvestigationAltName(String name) {
		PreparedStatement preparedStatement = null;
		String result = "";
		String sql = "SELECT CGHS FROM apm_investigation_type where name = '"+name+"' ";
		
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

	private String getInvoiceTpId(int invoiceid) {
		PreparedStatement preparedStatement = null;
		String result = "";
		String sql = "select tpid from apm_charges_invoice where id = "+invoiceid+" ";
		
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

	private int checkIsInvestigationPackage(String string) {
		int packageid=0;
		try {
			//  22 dec 2017 hide package charges for that get package id 
			StringBuilder builder = new StringBuilder();
			builder.append("select apm_invoice.ginvstid,pkg from apm_invoice ");
			builder.append("inner join apm_client_parent_investigation on ");
			builder.append("apm_client_parent_investigation.id = apm_invoice.ginvstid ");
			builder.append("where pkg>0 and apm_client_parent_investigation.id='"+string+"' ");
			PreparedStatement preparedStatement = connection.prepareStatement(builder.toString());
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				packageid = rs.getInt(2);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return packageid;
	}

	public int getstdonoffcurstatus(int subchargeid,String ipdid){
		PreparedStatement preparedStatement = null;
		int result = 0;
		String sql="select status from apm_std_onoff_charge where assesmentid="+subchargeid+" and ipdid="+ipdid+" ";
		
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


	public String getStdChargeStartEndTime(int subchargeid,String ipdid) {

		String str=null;
		try {
			
			String sql="select ondatetime,offdatetime from apm_std_onoff_charge where assesmentid="+subchargeid+" and ipdid="+ipdid+" ";
			PreparedStatement ps=connection.prepareStatement(sql);
			ResultSet rs=ps.executeQuery();
			while(rs.next()){
				
				 String ondate= rs.getString(1);
				 String offdate= rs.getString(2);
				// str=new String[]{ondate,offdate};
				 
				 str = ondate + "~" + offdate;
			} 
			if(str.equals("~")){
				str=null;
			}
		} catch (Exception e) {

			e.printStackTrace();
		}
		return str;
	}

	public boolean getIfSTdCharge(String chargeId) {

		try {
			
			String sql="select id from apm_appointment_type where id="+chargeId+" and stdcharge=1 and onoff=1 ";
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

	private String getTpChargeCode(int apmtchargeid) {
		PreparedStatement preparedStatement = null;
		String result = "";
		String sql = "SELECT code FROM apm_appointment_type where id = "+apmtchargeid+" ";
		
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

	public Vector<Accounts> getPreviewChargesList(int invoiceid) {
		PreparedStatement preparedStatement = null;
		DateTimeUtils dateTimeUtils = new DateTimeUtils();
		Vector<Accounts>list = new Vector<Accounts>();
		double chargedisc=0;
		//String sql = "SELECT apmtType,charge,practitionerName,practitionerId,paybuy FROM apm_invoice_assesments where invoiceid = "+invoiceid+" ";
		
		StringBuffer sql = new StringBuffer();
		/*sql.append("SELECT apmtType,charge,apm_invoice.practitionerName,apm_invoice.practitionerId,paybuy,apmt_charge_type,apm_invoice.id,apm_invoice_assesments.commencing,apm_invoice.appointmentid,apm_invoice_assesments.quantity,apm_invoice_assesments.id,apm_invoice_assesments.masterchargetype,apm_invoice_assesments.chargedisc,apm_invoice_assesments.unitcharge ");
		sql.append("FROM apm_invoice inner join apm_invoice_assesments on ");
		sql.append("apm_invoice_assesments.invoiceid = apm_invoice.id inner join apm_charges_assesment ");
		sql.append("on apm_charges_assesment.invoiceid = apm_invoice.id ");
		sql.append("where apm_charges_assesment.chargeinvoiceid="+invoiceid+" ");
		*/
		
		/*
		 * sql.
		 * append("SELECT apmtType,charge,apm_invoice.practitionerName,apm_invoice.practitionerId,paybuy,apmt_charge_type,apm_invoice.id,apm_invoice_assesments_view.commencing,apm_invoice.appointmentid,apm_invoice_assesments_view.quantity,apm_invoice_assesments_view.id,apm_invoice_assesments_view.masterchargetype,apm_invoice_assesments_view.chargedisc,apm_invoice_assesments_view.unitcharge "
		 * ); sql.append("FROM apm_invoice inner join apm_invoice_assesments_view on ");
		 * sql.append("apm_invoice_assesments_view.invoiceid = apm_invoice.id  ");
		 * sql.append("where apm_invoice_assesments_view.invoiced="+invoiceid+" ");
		 */
		
		/*
		 * sql.
		 * append("SELECT apmtType,charge,apm_invoice.practitionerName,apm_invoice.practitionerId,paybuy,apmt_charge_type,apm_invoice.id,apm_invoice_assesments.commencing,apm_invoice.appointmentid,apm_invoice_assesments.quantity,apm_invoice_assesments.id,apm_invoice_assesments.masterchargetype,apm_invoice_assesments.chargedisc,apm_invoice_assesments.unitcharge "
		 * ); sql.append("FROM apm_invoice inner join apm_invoice_assesments on ");
		 * sql.append("apm_invoice_assesments.invoiceid = apm_invoice.id "); sql.
		 * append("inner join apm_charges_assesment on apm_charges_assesment.invoiceid = apm_invoice.id "
		 * ); sql.append("where apm_charges_assesment.chargeinvoiceid="+invoiceid+" ");
		 */                                                                                  /* @rahul chnage remove inner of apm_charges_assesment */
		
		sql.append("SELECT apmtType,charge,apm_invoice.practitionerName,apm_invoice.practitionerId,paybuy,apmt_charge_type,apm_invoice.id,apm_invoice_assesments.commencing,apm_invoice.appointmentid,apm_invoice_assesments.quantity,apm_invoice_assesments.id,apm_invoice_assesments.masterchargetype,apm_invoice_assesments.chargedisc,apm_invoice_assesments.unitcharge ");
		sql.append("FROM apm_invoice inner join apm_invoice_assesments on ");
		sql.append("apm_invoice_assesments.invoiceid = apm_invoice.id ");
		sql.append("where apm_invoice_assesments.invoiced="+invoiceid+" ");
		try{
			preparedStatement = connection.prepareStatement(sql.toString());
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()){
				
				String apptName = rs.getString(1);
				String charge ="";
				if(rs.getDouble(13)>0){
					charge= rs.getString(14);
				}else{
				charge= rs.getString(2);
				}
				if(charge==null){
					charge = "0.0";
				}
				chargedisc=rs.getDouble(13);
				String practitionerName = rs.getString(3);
				String practitionerid = rs.getString(4);
				int payby = rs.getInt(5);
				int addtitonalCharge = rs.getInt(6);
				String chargeid = rs.getString(7);
				String date = rs.getString(8);
				int apptid = rs.getInt(9);
				int quantity = rs.getInt(10);
				int subchargeid = rs.getInt(11);
				String masterchargetype = rs.getString(12);
				/*String sectionroom = "";
				if(masterchargetype.equals("INVESTIGATION")){
					 sectionroom = getSectionRoom(apptName);
				}*/
				
				Accounts accounts = new Accounts();
				//accounts.setSectionroom(sectionroom);
				if(addtitonalCharge==0){
					Accounts acc = getAppointmentDetailsl(chargeid);
					if(acc.getCommencing()!=null){
						accounts.setCommencing(DateTimeUtils.getInvoiceCommencingDate(acc.getCommencing()));
						accounts.setAppointmentType(acc.getTreatmentEpisodeName() + " " +apptName);
					}
					
					
					boolean dna = getDna(apptid);
					accounts.setDna(dna);
					
				}else{
					String invoicedate = date;
					accounts.setCommencing(DateTimeUtils.getInvoiceCommencingDate(invoicedate));
					accounts.setAppointmentType("Additional Charge("+apptName+")");
				}
				
				accounts.setCharges(dateTimeUtils.changeFormat(Double.parseDouble(charge)));
				accounts.setChargeTotal(dateTimeUtils.changeFormat(Double.parseDouble(charge)*quantity));
				accounts.setPractitionerName(practitionerName);
				String practitionerFullName = getPractitionerFullName(practitionerid);
				accounts.setPractitionerName(practitionerFullName);
				accounts.setQuantity(quantity);
				accounts.setSubchargeid(subchargeid);
				accounts.setChargedisc(String.valueOf(chargedisc));
				accounts.setPayBy(payby);
				accounts.setAppointmentType(apptName);
				accounts.setApmt_id(rs.getString(9));
				list.add(accounts);
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
		return list;
	}

	

	private String getSectionRoom(String apptName) {
		PreparedStatement preparedStatement = null;
		String result = "";
		String sql = "SELECT apm_investigation_section.room FROM apm_client_investigation inner join  apm_client_parent_investigation on "
				+ " apm_client_parent_investigation.id = apm_client_investigation.parentid "
				+ " inner join apm_investigation_type on apm_investigation_type.id = apm_client_parent_investigation.invsttypeid "
				+ "inner join apm_investigation_section on  apm_investigation_section.id = apm_investigation_type.sectionid "
				+ " where invstname = '"+apptName+"' or invsttype = '"+apptName+"'  limit 0,1 ";
		
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

	private boolean getDna(int appointmentID) {
		PreparedStatement preparedStatement = null;
		boolean result = false;
		String sql = "SELECT dna FROM apm_available_slot where id = "+appointmentID+" ";
		
		try{
			
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			if(rs.next()){
				result = rs.getBoolean(1);
			}
			
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
		
		return result;
	}

	private int getAppointmentid(String invoiceid) {
		PreparedStatement preparedStatement = null;
		int result = 0;
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT apm_invoice.appointmentid FROM apm_charges_assesment inner join apm_invoice on ");
		sql.append("apm_charges_assesment.invoiceid = apm_invoice.id and apm_invoice.id = "+invoiceid+" ");
		
		try{
			
			preparedStatement= connection.prepareStatement(sql.toString());
			ResultSet rs = preparedStatement.executeQuery();
			if(rs.next()){
				result = rs.getInt(1);
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
		
				
		return result;
	}

	public Client getClientData(String clientId) {
		PreparedStatement preparedStatement = null;
		Client client = new Client();
		String sql ="SELECT title,firstname,surname,dob,address,email,policyno,mobno,town,postcode,second_line_address,gender,middlename,abrivationid,refrence,birthtime,fathername,mothername,patientcategory FROM apm_patient where id= "+clientId+" ";
		
		try{
			
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			if(rs.next()){
				client.setTitle(rs.getString(1));
				client.setFirstName(rs.getString(2));
				client.setLastName(rs.getString(3));
				client.setDob(rs.getString(4));
				client.setAddress(rs.getString(5));
				client.setEmail(rs.getString(6));
				if(rs.getString(7)==null){
					client.setPolicyNo("");
				}else{
					client.setPolicyNo(rs.getString(7));
				}
				
				client.setMobNo(rs.getString(8));
				client.setTown(rs.getString(9));
				client.setPostCode(rs.getString(10));
				client.setSecondLineaddress(rs.getString(11));
				client.setId(Integer.parseInt(clientId));
				client.setGender(rs.getString(12));
				client.setMiddlename(rs.getString(13));
				client.setAbrivationid(rs.getString(14));
				
				if(rs.getString(14)!=null){
					client.setAbrivationid(rs.getString(14));
				}else{
					client.setAbrivationid(clientId);
				}
				if(rs.getString(15)!=null){
					client.setReference(rs.getString(15));
					
				}else{
					client.setReference("");
				}
				client.setBirthtime(rs.getString(16));
				client.setFathername(DateTimeUtils.isNull(rs.getString(17)));
				client.setMothername(DateTimeUtils.isNull(rs.getString(18)));
				client.setPatientcategory(DateTimeUtils.isNull(rs.getString(19)));
//				client.setWhopay(rs.getString(15));
//				client.setCompname(rs.getString(16));
//				client.setNeisno(rs.getString(17));
//				client.setDesignationbytp(rs.getString(18));
//				client.setRelationvbytpe(rs.getString(19));
//				client.setUnitstation(rs.getString(20));
//				client.setClaimbytp(rs.getString(21));
//				client.setColliery(rs.getString(22));
//				client.setAreabytp(rs.getString(23));
//				client.setThirdPartyType(rs.getString(24));
				
				
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return client;
	}

	public ArrayList<Accounts> getchargesInvoiceList(int invoiceid) {
		PreparedStatement preparedStatement = null;
		ArrayList<Accounts>list = new ArrayList<Accounts>();
		String sql = "SELECT invoiceid FROM apm_charges_assesment where chargeinvoiceid = "+invoiceid+" ";
		
		try{
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()){
				Accounts accounts = new Accounts();
				accounts.setInvoiceid(rs.getInt(1));
				list.add(accounts);
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
		return list;
	}

	public int getThirdPartyID(String clientId) {
		PreparedStatement preparedStatement = null;
		int result = 0;
		String sql = "SELECT third_party_name_id FROM apm_patient where id= '"+clientId+"' ";
		
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

	public int saveEmailLogDetails(String to, String cc, String subject,String notes, String filename, int invoiceid,String date,String time,String type) {
		PreparedStatement preparedStatement = null;
		int result = 0;
		String sql = "insert into apm_email_log(invoice_id,to1,cc,subject,body_text,date,time,filename,type) values (?,?,?,?,?,?,?,?,?)";
		
		try{
			
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, invoiceid);
			preparedStatement.setString(2, to);
			preparedStatement.setString(3, cc);
			preparedStatement.setString(4, subject);
			preparedStatement.setString(5, notes);
			preparedStatement.setString(6, date);
			preparedStatement.setString(7, time);
			preparedStatement.setString(8, filename);
			preparedStatement.setString(9, type);
			
			result = preparedStatement.executeUpdate();
			
			
		}catch(Exception e){
			e.printStackTrace();
		}
		return result;
	}

	public String getLocationName(String location) {
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

	public String getLocationID(int invoiceid) {
		PreparedStatement preparedStatement = null;
		String result = "";
		String sql = "SELECT location FROM apm_charges_invoice where id ="+invoiceid+" ";
		
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

	public int updateDeliverStatus(int invoiceid,String status) {
		PreparedStatement preparedStatement = null;
		int result = 0;
		String sql = "update apm_charges_invoice set deliverstatus=? where id=?";
		
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

	public int updatePaymentDeliverStatus(String id,String status) {
		PreparedStatement preparedStatement = null;
		int result = 0;
		String sql = "update apm_charges_payment set deliver_status=? where id="+id+"";
		
		try{
			
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, status);
			
			result = preparedStatement.executeUpdate();
		}catch(Exception e){
			e.printStackTrace();
		}
		return result;
	}

	public int getTotalLocation() {
		PreparedStatement preparedStatement = null;
		int result = 0;
		String sql = "SELECT count(*) FROM apm_clinic_location ";
		
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

	public ArrayList<Clinic> getLocationAddress(int clinicid) {
		PreparedStatement preparedStatement = null;
		ArrayList<Clinic> locationAdressList = new ArrayList<Clinic>();
		String sql = "SELECT address,postcode,city,hospname FROM apm_clinic_location where userid ="+clinicid+" and addressType = 'Registered'";
		
		try{
			
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()){
				Clinic clinic = new Clinic();
				clinic.setAddress(rs.getString(1));
				clinic.setPinCode(rs.getString(2));
				clinic.setLocationname(rs.getString(3));
				clinic.setClinicName(rs.getString(4));
				locationAdressList.add(clinic);
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return locationAdressList;
	}

	public ArrayList<Clinic> getLetterHeadDetails(int clinicid) {
		PreparedStatement preparedStatement = null;
		ArrayList<Clinic> locationAdressList = new ArrayList<Clinic>();
		String sql = "SELECT address,postcode,city,jobgroupid FROM his_other_letterhead where userid ="+clinicid+" and addressType = 'Registered'";
		
		try{
			
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()){
				Clinic clinic = new Clinic();
				clinic.setAddress(rs.getString(1));
				clinic.setPinCode(rs.getString(2));
				clinic.setLocationname(rs.getString(3));
				clinic.setUserType(rs.getInt(4));
				locationAdressList.add(clinic);
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return locationAdressList;
	}

	
	
	
	
	
	public double getDiscount(int invoiceid) {
		PreparedStatement preparedStatement = null;
		double result = 0.0;
		String sql = "SELECT discount FROM apm_charges_invoice where id ="+invoiceid+" ";
		
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

	public double getTotalPaymentReceived(int invoiceid) {
		PreparedStatement preparedStatement = null;
		double result = 0.0;
		String sql = "SELECT sum(payment) FROM apm_charges_payment where chargeinvoiceid ="+invoiceid+" ";
		
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

	public String getNotes(int invoiceid) {
		PreparedStatement preparedStatement = null;
		String result = "";
		String sql = "SELECT notes FROM apm_charges_invoice where id ="+invoiceid+" ";
		
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

	public int getTPId(int invoiceid) {
		PreparedStatement preparedStatement = null;
		int result = 0;
		String sql = "SELECT tpid FROM apm_charges_invoice where id ="+invoiceid+" ";
		
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

	public ThirdParty getTpDetails(int tpid) {
		PreparedStatement preparedStatement = null;
		ThirdParty thirdParty = new ThirdParty();
		String sql = "select company_name,address,town,country,postcode,telephone,company_email,second_line_address,unit,area from apm_third_party_details where id = "+tpid+"";
	//	sql = pagination.getSQLQuery(sql);
		try{
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()){
				
				
				
				thirdParty.setCompanyName(rs.getString(1));
				thirdParty.setAddress(rs.getString(2));
				thirdParty.setTown(rs.getString(3));
				thirdParty.setCountry(rs.getString(4));
				thirdParty.setPostcode(rs.getString(5));
				thirdParty.setTelephoneLine(rs.getString(6));
				thirdParty.setEmail(rs.getString(7));
				thirdParty.setSecondLineAddress(rs.getString(8));
				thirdParty.setUnit(rs.getString(9));
				thirdParty.setArea(rs.getString(10));
				
				
			}
			
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		return thirdParty;
	}


	public ThirdParty getTpAccountDetails(String clientId) {
		PreparedStatement preparedStatement = null;
		ThirdParty thirdParty = new ThirdParty();
		StringBuffer sql = new StringBuffer();
		
		sql.append("SELECT creditDuration FROM apm_third_party_details inner join apm_patient on ");
		sql.append("apm_patient.third_party_name_id = apm_third_party_details.id and  apm_patient.id = "+clientId+" ");
		
		try{
			
			preparedStatement = connection.prepareStatement(sql.toString());
			ResultSet rs = preparedStatement.executeQuery();
			
			if(rs.next()){
				thirdParty.setCreditDuration(rs.getString(1));
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
		
		
		return thirdParty;
	}

	public int getAdditionalChargeValue(int invoiceid) {
		PreparedStatement preparedStatement = null;
		int result = 0;
		String sql = "SELECT apmt_charge_type FROM apm_invoice where id ="+invoiceid+" ";
		
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

	
	public ArrayList<Accounts> getSumitedChargesList(int invoiceid) {
		PreparedStatement preparedStatement = null;
		ArrayList<Accounts>list = new ArrayList<Accounts>();
		String sql = "SELECT invoiceid FROM apm_charges_assesment where chargeinvoiceid= "+invoiceid+" ";
		
		try{
			
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()){
				Accounts accounts = new Accounts();
				accounts.setInvoiceNo(rs.getString(1));
				accounts.setCharges(rs.getString(1));
				
				list.add(accounts);
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
		return list;
	}


	public int updateModifiedInvoiceChargesChargeType(String charges) {
		PreparedStatement preparedStatement = null;
		int result = 0;
		String sql = "update apm_invoice set chargetype=? where id = ?";
		
		try{
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, "Submit");
			preparedStatement.setString(2, charges);
			
			result = preparedStatement.executeUpdate(); 
		
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return result;
	}


	public int deleteChargesOfChargesAssesment(String charges) {
		PreparedStatement preparedStatement = null;
		int result = 0;
		String sql = "delete  FROM apm_charges_assesment where invoiceid= "+charges+" ";
		
		try{
			preparedStatement = connection.prepareStatement(sql);
			result = preparedStatement.executeUpdate();
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return result;
	}

	
	public int saveUpdateCharges(String str, int invoiceid) {
		PreparedStatement preparedStatement = null;
		int result = 0;
//		String sql = "insert into apm_charges_assesment(invoiceid,chargeinvoiceid) values(?,?)";
		String sql="update apm_charges_assesment  set invoiceid=?,chargeinvoiceid=? where invoiceid="+invoiceid+"";
		
		try{
			
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, str);
			preparedStatement.setInt(2, invoiceid);
			
			result = preparedStatement.executeUpdate();
			
		}catch(Exception e){
			e.printStackTrace();
		}
		return result;
	}

	
	public double getSumOfCharges(String str) {
		PreparedStatement preparedStatement = null;
		double result = 0;
		String sql = "SELECT  charge,quantity FROM apm_invoice_assesments where invoiceid = "+str+" ";
		
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

	
	public int updateDebitAmount(double sumofCharges, int invoiceid,String invoicedate, String notes,String practid) {
		PreparedStatement preparedStatement = null;
		int result = 0;
		String sql = "update apm_charges_invoice  set debit=?,date=?,notes=?,practid=?  where id = ? ";
		
		try{
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setDouble(1, sumofCharges);
			preparedStatement.setString(2, invoicedate);
			preparedStatement.setString(3, notes);
			preparedStatement.setInt(5, invoiceid);
			preparedStatement.setString(4, practid);
			result = preparedStatement.executeUpdate();
			
		}catch(Exception e){
			e.printStackTrace();
		}
		return result;
	}

	public String getInvoiceDate(int invoiceid) {
		PreparedStatement preparedStatement = null;
		String result = "";
		String sql = "SELECT date FROM apm_charges_invoice where id = "+invoiceid+" ";
		
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

	public int checkPolicyExcess(int invoiceid) {
		PreparedStatement preparedStatement = null;
		int result = 0;
		String sql = "SELECT policy_excess FROM apm_invoice where id = "+invoiceid+" ";
		
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

	public double getPolicyamount(int invoiceid) {
		PreparedStatement preparedStatement = null;
		double result = 0;
		String sql = "SELECT charge FROM apm_invoice_assesments where invoiceid = "+invoiceid+" and apmtType = '"+Constants.POLICYEXCESS+"'";
		
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

	public int updatePolicyAmount(double amt, int invoiceid) {
		PreparedStatement preparedStatement = null;
		int result = 0;
		String sql = "Update apm_charges_invoice set debit = ? where id="+invoiceid+" ";
		
		try{
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setDouble(1, amt);
			result = preparedStatement.executeUpdate();
			
		}catch(Exception e){
			e.printStackTrace();
		}
		return result;
	}

	public CompleteAppointment getInvoiceDetails(int invoiceid) {
		PreparedStatement preparedStatement = null;
		CompleteAppointment c1 = new CompleteAppointment();
		String sql = "SELECT id,clientid,practitionerid,clientname,practitionername,date,chargetype,appointmentid,location,policy_excess FROM apm_invoice where id= "+invoiceid+" ";
		
		try{
			
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()){
				c1.setId(rs.getInt(1));
				c1.setClientId(rs.getString(2));
				c1.setPractitionerId(rs.getString(3));
				c1.setUser(rs.getString(4));
				c1.setPractitionerName(rs.getString(5));
				c1.setInvoiceDate(rs.getString(6));
				c1.setChargeType("Charge");
				c1.setAppointmentid(rs.getString(8));
				c1.setLocation(rs.getString(9));
				c1.setPolicyExcess(rs.getString(10));
				
				
				
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
		return c1;
	}

	public CompleteAppointment getInvoiceAssessmentDetails(int invoiceid) {
		PreparedStatement preparedStatement = null;
		CompleteAppointment c1 = new CompleteAppointment();
		String sql = "SELECT id,user,apmtType,charge,startTime,duration,practitionerId,practitionerName,clientId,commencing,appointmentid,markAppointment,active FROM apm_invoice_assesments where invoiceid = "+invoiceid+" and apmtType = '"+Constants.POLICYEXCESS+"' ";
		
		try{
			
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()){
				c1.setId(rs.getInt(1));
				c1.setUser(rs.getString(2));
				c1.setApmtType(rs.getString(3));
				c1.setCharges(rs.getString(4));
				c1.setStartTime(rs.getString(5));
				c1.setDuration(rs.getString(6));
				c1.setPractitionerId(rs.getString(7));
				c1.setPractitionerName(rs.getString(8));
				c1.setClientId(rs.getString(9));
				c1.setCommencing(rs.getString(10));
				c1.setAppointmentid(rs.getString(11));
				c1.setPayBuy("0");
				c1.setMarkAppointment(rs.getString(12));
				
				
				
				
				
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
		return c1;
	}

	public int getpayBy(int invoiceid) {
		PreparedStatement preparedStatement = null;
		int result = 0;
		String sql = "SELECT paybuy FROM apm_invoice_assesments where invoiceid = "+invoiceid+" ";
		
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

	public Accounts checkInvoiceHasPolicyExcess(int invoiceid) {
		PreparedStatement preparedStatement = null;
		Accounts accounts = new Accounts();
		StringBuffer sql = new StringBuffer();
		
		sql.append("SELECT apm_invoice.appointmentid FROM apm_charges_assesment inner join  apm_charges_invoice on ");
		sql.append("apm_charges_invoice.id = apm_charges_assesment.chargeinvoiceid inner join apm_invoice on ");
		sql.append("apm_charges_assesment.invoiceid = apm_invoice.id inner join apm_invoice_assesments on ");
		sql.append("apm_invoice_assesments.invoiceid = apm_invoice.id ");
		sql.append("where apm_charges_invoice.id = "+invoiceid+" ");
		
		try{
			
			preparedStatement = connection.prepareStatement(sql.toString());
			ResultSet rs = preparedStatement.executeQuery();
			if(rs.next()){
				int appointmentid = rs.getInt(1);
				accounts = checkPolicyExcessExist(appointmentid);
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
		
		return accounts;
	}

	private Accounts checkPolicyExcessExist(int appointmentid) {
		PreparedStatement preparedStatement = null;
		Accounts accounts = new Accounts();
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT apmtType,apm_invoice_assesments.charge FROM apm_invoice_assesments inner join apm_invoice on ");
		sql.append("apm_invoice_assesments.invoiceid = apm_invoice.id where apm_invoice.appointmentid = "+appointmentid+" and ");
		sql.append("apmtType = '"+Constants.POLICYEXCESS+"' ");
		
		try{
			preparedStatement = connection.prepareStatement(sql.toString());
			ResultSet rs = preparedStatement.executeQuery();
			if(rs.next()){
				accounts.setPolicyExcess(true);
				accounts.setCharges(rs.getString(2));
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return accounts;
	}

	public int updateChargesInvoiceDebitAmount(int invoiceid, double debit) {
		PreparedStatement preparedStatement = null;
		int result = 0;
		String sql = "update apm_charges_invoice set debit=? where id = ? ";
		
		try{
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setDouble(1, debit);
			preparedStatement.setInt(2, invoiceid);
			
			result = preparedStatement.executeUpdate();
			
		}catch(Exception e){
			e.printStackTrace();
		}
		return result;
	}

	public Vector<Accounts> getAdditionalPreviewChargesList(String invoiceid) {
		PreparedStatement preparedStatement = null;
		DateTimeUtils dateTimeUtils = new DateTimeUtils();
		Vector<Accounts>list = new Vector<Accounts>();
		String sql = "SELECT apmtType,charge,practitionerName,practitionerId,paybuy,commencing FROM apm_invoice_assesments where invoiceid = "+invoiceid+" ";
		
		try{
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()){
				Accounts accounts = new Accounts();
				int additional = getAdditionalChargeValue(Integer.parseInt(invoiceid));
				if(additional == 1){
					accounts.setAppointmentType("Additional Charge("+rs.getString(1)+")");

				}
				
				accounts.setCharges(dateTimeUtils.changeFormat(Double.parseDouble(rs.getString(2))));
				accounts.setPractitionerName(rs.getString(3));
				String practitionerFullName = getPractitionerFullName(rs.getString(4));
				accounts.setPractitionerName(practitionerFullName);
				
				int appointmentID = getAppointmentid(invoiceid);
				boolean dna = getDna(appointmentID);
				accounts.setDna(dna);
				accounts.setPayBy(rs.getInt(5));
				accounts.setCommencing(DateTimeUtils.getInvoiceCommencingDate(rs.getString(6)));

				accounts.setAppointmentType(rs.getString(1));
				list.add(accounts);
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
		return list;
	}

	public ArrayList<Accounts> getCreditChargeAccountList(String clientId,int invoiceid,int loginid) {
		PreparedStatement preparedStatement = null;
		ArrayList<Accounts>list = new ArrayList<Accounts>();
		//StringBuffer sql = new StringBuffer();
		
		/*sql.append("SELECT apm_credit_account_assesments.id,commencing,apm_credit_account_assesments.charge,apmtType,quantity, ipdid, masterchargetype FROM apm_credit_account_assesments inner join apm_credit_account ");
		sql.append("on apm_credit_account_assesments.invoiceid = apm_credit_account.id and apm_credit_account_assesments.clientid = "+clientId+" and apm_credit_account_assesments.invoiceid = "+invoiceid+" ");*/
		
		String sql = "SELECT id,commencing,charge,apmtType,quantity FROM apm_patient_complete_apmt where clientId = "+clientId+" and loginid="+loginid+" ";
		
		try{
			preparedStatement = connection.prepareStatement(sql.toString());
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()){
				Accounts accounts = new Accounts();
				accounts.setId(rs.getInt(1));
				accounts.setCommencing(rs.getString(2));
				accounts.setCharges(DateTimeUtils.changeFormat(Double.parseDouble(rs.getString(3))*rs.getInt(5)));
				accounts.setAppointmentType(rs.getString(4));
				
				list.add(accounts);
				
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return list;
	}

	public int getClientOfInvoice(int invoiceid) {
		PreparedStatement preparedStatement = null;
		int result = 0;
		String sql = "SELECT clientid FROM apm_invoice where id="+invoiceid+" ";
		
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

	public boolean checkCreditAmount(String clientId) {
		PreparedStatement preparedStatement = null;
		boolean result = false;
		String sql = "SELECT balance FROM apm_credit_account where client_id = "+clientId+" ";
		
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

	public double getBlanceAmount(String clientId) {
		PreparedStatement preparedStatement = null;
		double result = 0;
		String sql = "SELECT balance FROM apm_credit_account where client_id = "+clientId+" and payment_mode is not null  order by id  desc limit 1 ";
		
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

	public int deleteTempChagesInvoiceStringData(String loginsessionid) {
		PreparedStatement preparedStatement = null;
		int result = 0;
		//String sql = "truncate apm_tempcharge_account";
		
		try{
			String sql ="delete from apm_tempcharge_account where usersessionid='"+loginsessionid+"'";
			preparedStatement = connection.prepareStatement(sql);
			result = preparedStatement.executeUpdate();
			
			
		}catch(Exception e){
			e.printStackTrace();
		}
		return 0;
	}

	public String getAppointmentInvoiceid(String appointmentid) {
		PreparedStatement preparedStatement = null;
		String result = "";
		
		ArrayList<Invoice>list = getTotalInvoice(appointmentid);
		
		String sql = "SELECT id FROM apm_invoice where appointmentid = "+appointmentid+"  order by id desc limit 1 ";
		
		
		
		try{
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			if(rs.next()){
				result = rs.getString(1);
			}
			
			if(list.size()>1){
				/*int checkpolicyExcess = checkPolicyExcess(appointmentid); 
				if(checkpolicyExcess==0){*/
					result = getSelfInvoiceId(appointmentid);
				//}
			}
			
		}catch(Exception e){
			e.printStackTrace();
//			StringWriter sw = new StringWriter();
//	         PrintWriter pw = new PrintWriter(sw);
//	         e.printStackTrace(pw);
//			Tra.exceptionMail(sw.toString());
		}
		return result;
	}

	private int checkPolicyExcess(String appointmentid) {
		PreparedStatement preparedStatement = null;
		int result = 0;
		String sql = "select policy_excess from apm_invoice where appointmentid = "+appointmentid+" group by  appointmentid ";
		
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

	private String getSelfInvoiceId(String appointmentid) {
		PreparedStatement preparedStatement = null;
		String result = "";
		String sql = "SELECT invoiceid FROM apm_invoice_assesments where appointmentid = "+appointmentid+" and paybuy=0 group by invoiceid ";
		
		try{
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			if(rs.next()){
				result = rs.getString(1);
			}
			
		}catch(Exception e){
			e.printStackTrace();
//			StringWriter sw = new StringWriter();
//	         PrintWriter pw = new PrintWriter(sw);
//	         e.printStackTrace(pw);
//			Tra.exceptionMail(sw.toString());
		}
		return result;
	}

	public  ArrayList<Invoice> getTotalInvoice(String appointmentid) {
		PreparedStatement preparedStatement = null;
		ArrayList<Invoice>list = new ArrayList<Invoice>();
		String sql = "SELECT id FROM apm_invoice_assesments where appointmentid = "+appointmentid+" group by invoiceid ";
		
		try{
			
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()){
				Invoice invoice = new Invoice();
				invoice.setId(rs.getInt(1));
				
				list.add(invoice);
			}
			
			
		}catch(Exception e){
			e.printStackTrace();
		}
		return list;
	}

	public double getInvoiceDebitAmmount(int invoiceid) {
		PreparedStatement preparedStatement = null;
		double result = 0;
		String sql = "SELECT debit FROM apm_charges_invoice where id = "+invoiceid+" ";
		
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

	public ArrayList<Accounts> getchargeAssesmenyList(int invoiceid) {
		PreparedStatement preparedStatement = null;
		ArrayList<Accounts>list = new ArrayList<Accounts>();
		String sql = "SELECT invoiceid FROM apm_charges_assesment where chargeinvoiceid = "+invoiceid+" ";
		
		try{
				preparedStatement = connection.prepareStatement(sql);
				ResultSet rs = preparedStatement.executeQuery();
				while(rs.next()){
					Accounts accounts = new Accounts();
					accounts.setInvoiceid(rs.getInt(1));
					
					list.add(accounts);
				}
				
		}catch(Exception e){
			e.printStackTrace();
		}
		return list;
	}

	public int deleteChargeAssesmentList(int invoiceid) {
		PreparedStatement preparedStatement = null;
		int result = 0;
		String sql = "delete FROM apm_charges_assesment where chargeinvoiceid = "+invoiceid+" ";
		
		try{
			
			preparedStatement = connection.prepareStatement(sql);
			result = preparedStatement.executeUpdate();
			
		}catch(Exception e){
			e.printStackTrace();
		}
		return result;
	}

	public int deleteChargeInvoice(int invoiceid) {
		PreparedStatement preparedStatement = null;
		int result = 0;
		String sql = "delete FROM apm_charges_invoice where id = "+invoiceid+" ";
		
		
		try{
				
				preparedStatement = connection.prepareStatement(sql);
				result = preparedStatement.executeUpdate();
				
			}catch(Exception e){
				e.printStackTrace();
			}
		
		return result;
	}

	public String getChargeLocation(String appointmentid) {
		PreparedStatement preparedStatement = null;
		String result = "";
		String sql = "SELECT location FROM apm_invoice where appointmentid = "+appointmentid+" ";
		
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

	public NotAvailableSlot getClientsAppointmentData(String appointmentid) {
		PreparedStatement preparedStatement = null;
		NotAvailableSlot availableSlot = new NotAvailableSlot();
		String sql  = "SELECT clientid,whopay,diaryuserid FROM apm_available_slot where id = "+appointmentid+" ";
		
		try{
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			if(rs.next()){
				availableSlot.setClientId(rs.getString(1));
				availableSlot.setPayBy(rs.getString(2));
				availableSlot.setDiaryUser(rs.getString(3));
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
		return availableSlot;
	}

	public String getCashDeskLocation(String invoiceid) {
		PreparedStatement preparedStatement = null;
		String result = "";
		String sql = "select location from apm_invoice where id = "+invoiceid+" ";
		
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

	
	public ArrayList<Accounts> getOnlyThirdPartyList(int id) {
		PreparedStatement preparedStatement = null;
		ArrayList<Accounts>list = new ArrayList<Accounts>();
		String sql = "SELECT id,company_name FROM apm_third_party_details where third_party_id=2";
		
		try{
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()){
				Accounts accounts = new Accounts();
				accounts.setId(rs.getInt(1));
				accounts.setThirdParty(rs.getString(2));
				
				list.add(accounts);
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
		
		return list;
	}

	
	public ArrayList<Accounts> getOnlyGpList(int id) {
		PreparedStatement preparedStatement = null;
		ArrayList<Accounts>list = new ArrayList<Accounts>();
		String sql = "SELECT id,gpname FROM apm_gp_details ";
		
		try{
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()){
				Accounts accounts = new Accounts();
				accounts.setId(rs.getInt(1));
				accounts.setThirdParty(rs.getString(2));
				
				list.add(accounts);
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
		
		return list;
	}

	public String getPaymentMode(int invoiceid) {
		PreparedStatement preparedStatement = null;
		String result = "";
		String sql = "SELECT paymode FROM apm_charges_payment where chargeinvoiceid = "+invoiceid+" ";
		
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

	
	public int getPatientipdid(int clientid) {
		PreparedStatement preparedStatement = null;
		int result = 0;
		StringBuffer sql = new StringBuffer();
		
		/*sql.append("SELECT ipdid FROM apm_charges_invoice inner join apm_charges_assesment on ");
		sql.append("apm_charges_assesment.chargeinvoiceid = apm_charges_invoice.id inner join apm_invoice_assesments on ");
		sql.append("apm_invoice_assesments.invoiceid = apm_charges_assesment.invoiceid ");
		sql.append("where apm_charges_invoice.id = "+invoiceid+" and ipdid>0 ");
		sql.append("group by ipdid ");*/
		
		sql.append("SELECT id FROM ipd_addmission_form where clientid = "+clientid+" order by id desc limit 0,1 ");
		
		try{
			preparedStatement = connection.prepareStatement(sql.toString());
			ResultSet rs = preparedStatement.executeQuery();
			if(rs.next()){
				result = rs.getInt(1);
			}
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
		
	}

	public ArrayList<Invoice> getCaInvoiceList(String invoicesearchid,
			String fromDate, String toDate, String invoicetype,String invcategory,String invpaid, String payby1) {
		PreparedStatement preparedStatement = null;
		ArrayList<Invoice>list = new ArrayList<Invoice>();
		ArrayList<Invoice>ipdlist = new ArrayList<Invoice>();
		ArrayList<Invoice>opdlist = new ArrayList<Invoice>();
		ArrayList<Invoice>hdList = new ArrayList<Invoice>();
		AccountsDAO accountsDAO = new JDBCAccountsDAO(connection);
		
		String sql = "";
		
		if(false){
			sql = "SELECT id,payby,discount,clientid,date,resetinv FROM apm_charges_invoice where resetinv="+invoicesearchid+" ";
		}else{
			if(invcategory.equals(Constants.PRIMARY_INVOICE)){
				if(invoicetype.equals("0")){
					sql = "SELECT id,payby,discount,clientid,date,resetinv,disctype,discamt,itype,debit,isdeleted FROM apm_charges_invoice where date between '"+fromDate+"' and '"+toDate+"' and invpstype=0";	
				}else {
					sql = "SELECT id,payby,discount,clientid,date,resetinv,disctype,discamt,itype,debit,isdeleted FROM apm_charges_invoice where date between '"+fromDate+"' and '"+toDate+"' and invpstype=0 and itype="+invoicetype+" ";
				}
				
				
			}else if(invcategory.equals(Constants.SECONDARY_INVOICE)){
				if(invoicetype.equals("0")){
					sql = "SELECT id,payby,discount,clientid,date,resetinv,disctype,discamt,itype,debit,isdeleted FROM apm_charges_invoice where date between '"+fromDate+"' and '"+toDate+"' and invpstype=1";	
				}else {
					sql = "SELECT id,payby,discount,clientid,date,resetinv,disctype,discamt,itype,debit,isdeleted FROM apm_charges_invoice where date between '"+fromDate+"' and '"+toDate+"' and invpstype=1 and itype="+invoicetype+" ";
				}
			}else{
				if(invoicetype.equals("0")){
					sql = "SELECT id,payby,discount,clientid,date,resetinv,disctype,discamt,itype,debit,isdeleted FROM apm_charges_invoice where date between '"+fromDate+"' and '"+toDate+"'";	
				}else {
					sql = "SELECT id,payby,discount,clientid,date,resetinv,disctype,discamt,itype,debit,isdeleted FROM apm_charges_invoice where date between '"+fromDate+"' and '"+toDate+"' and itype="+invoicetype+" ";
				}
			}
			
		}
		if(!payby1.equals("")){
			sql=sql+"    and payby='"+payby1+"'";
		}
		invoicesearchid=DateTimeUtils.isNull(invoicesearchid);
		if(!invoicesearchid.equals("")){
			sql=sql+"    and id='"+invoicesearchid+"'";
		}
		
		try{
			IpdDAO ipdDAO= new JDBCIpdDAO(connection);
			BedDao bedDao= new JDBCBedDao(connection);
			ChargesAccountProcessingDAO chargesAccountProcessingDAO= new JDBCChargeAccountProcesDAO(connection);
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			StatementDAO statementDAO= new JDBCStatementDAO(connection);
			while(rs.next()){
				
				Invoice invoice = new Invoice();
				invoice.setId(rs.getInt(1));
				invoice.setPayby(rs.getString(2));
				invoice.setDiscount(rs.getDouble(3));
				invoice.setClientid(rs.getInt(4));
				String date = rs.getString(5);
				String resetinv = rs.getString(6);
				invoice.setResetinv(resetinv);
				double payAmount = 0;
				double discount = invoice.getDiscount();
				String payby = invoice.getPayby();
				
				invoice.setItype(rs.getString("itype"));
				
				//set Location
				String locationid = accountsDAO.getLocationID(invoice.getId());
				String locationName = accountsDAO.getLocationName(locationid);
				invoice.setLocationName(locationName);
				
				//setting master chartype data
				ArrayList<Master>masterAssessmentList = accountsDAO.getMasterAssessmentListForCA(invoice.getId(),"0");
				invoice.setMasterAssessmentList(masterAssessmentList);
				
				Vector<Accounts>assesmentList = accountsDAO.getPreviewChargesList(invoice.getId());
				invoice.setAssesmentList(assesmentList);
				
				//double totalAmount =  accountsDAO.getInvoiceDebitAmmount(invoiceid);
				double totalAmount = 0;
				for(Accounts totalAcc : assesmentList){
					double charge = Double.parseDouble(totalAcc.getCharges()) * totalAcc.getQuantity();
					totalAmount = totalAmount + charge;
					
				}
				
				
				//discount calculations
				invoice.setCancelled(rs.getString("isdeleted"));
				int chkdisctype=rs.getInt("disctype");
				//0 for % and 1 for Rs
				double discountAmount=0;
				double originalDebit=0;
				if(chkdisctype==0){
					discountAmount= (rs.getDouble("discamt")/100.0)*rs.getDouble("debit");
				}else{
					discountAmount=rs.getDouble("discamt");
				}
				originalDebit=((rs.getDouble("debit")-discountAmount)*100.0)/100.0;
				
				double balnce=(originalDebit-getTotalPaymentReceived(invoice.getId()))+getTotalRefundOverInvoice(invoice.getId());
				balnce=(balnce*100.0)/100.0;
				
				
				
				
                String invoicenameid=accountsDAO.getInvoiceTypeId(invoice.getId());
				
				String invoicename=accountsDAO.getInvoiceName(invoicenameid);
				
				invoice.setInvoicenameid(invoicenameid);
				invoice.setInvoicename(invoicename);
				
				//invoice.setAssesmentList(assesmentList);
				invoice.setTotalAmount(totalAmount);
				
				Client client = accountsDAO.getClientData(Integer.toString(invoice.getClientid()));
				invoice.setClient(client.getTitle() + " " + client.getFirstName() + " "+client.getMiddlename()+" " + client.getLastName());
				invoice.setAddress(client.getAddress() + "," + client.getTown() + "," + client.getPostCode());
				
				String age = DateTimeUtils.getAge(client.getDob());
				invoice.setAgegender(age + "/" + client.getGender());
				
				invoice.setDob(client.getDob());
				invoice.setEmail(client.getEmail());
				invoice.setMobno(client.getMobNo());
				invoice.setPolicyNo(client.getPolicyNo());
				
				invoice.setInvoiceid(invoice.getId());
				invoice.setDate(DateTimeUtils.getInvoiceCommencingDate(date));
				invoice.setCreditAmt(DateTimeUtils.changeFormat(payAmount));
				
				//Decimal Account
				invoice.setTotalAmountx(DateTimeUtils.changeFormat(totalAmount));
				
				double credit =  0 ;
				if(discount  >0){
					
					double r1 = (totalAmount*discount)/100;
					int disctype=rs.getInt(7);
					double discamt= rs.getDouble(8);
					if(disctype==0){
						discamt= r1;
					}else{
						r1= discamt;
					}
					
					/*double dicsAmount = (discount*totalAmount)/100;*/
					credit = totalAmount - r1;
					
					invoice.setBalance(Double.parseDouble(new DecimalFormat("##.##").format(credit)));
					
					//Decimal Account
					invoice.setBalancex(DateTimeUtils.changeFormat(credit));
					
				}else{
					credit = totalAmount - payAmount;
					invoice.setBalance(totalAmount);
					
					//Decimal Account
					invoice.setBalancex(DateTimeUtils.changeFormat(totalAmount));
				}
				
				
				if(payAmount >0){
					
					invoice.setDebitAmounnt(DateTimeUtils.changeFormat(credit));					

				}else{
					invoice.setDebitAmounnt(DateTimeUtils.changeFormat(totalAmount));
				}
				
				double totalPaymentReceived = accountsDAO.getTotalPaymentReceived(invoice.getId());
				invoice.setCreditAmt(DateTimeUtils.changeFormat(totalPaymentReceived));
				//double credit = totalAmount - payAmount;
				double discountAmt = (totalAmount * (discount/100));
				
				int disctype=rs.getInt(7);
				double discamt= rs.getDouble(8);
				if(disctype==0){
					discountAmt = (totalAmount * (discount/100));
				}else{
					discountAmt= discamt;
				}
				
				
				double amountDue = totalAmount - discountAmt;
				amountDue = amountDue - totalPaymentReceived;
				
				invoice.setDiscAmmount(DateTimeUtils.changeFormat(discamt));
				
				invoice.setBalance(amountDue);
				invoice.setBalancex(DateTimeUtils.changeFormat(amountDue));
				
				//policy excess code
				if(payby.equals(Constants.PAY_BY_THIRD_PARTY)){
					Accounts accounts = accountsDAO.checkInvoiceHasPolicyExcess(invoice.getId());
					
					if(accounts.isPolicyExcess()){
						
						double balance = amountDue - Double.parseDouble(accounts.getCharges());
						//totalAmount = totalAmount - Double.parseDouble(accounts.getCharges());
						invoice.setPolicyexcesscode(1);
						invoice.setPolicyExcess(DateTimeUtils.changeFormat(Double.valueOf(accounts.getCharges())));
						//accountsForm.setBalancex(dateTimeUtils.changeFormat(balance));
					}
				}
				ArrayList<Accounts>prepaymentList = getPrePaymentList(rs.getInt(1),"0");
				invoice.setPrepaymentList(prepaymentList);
				ArrayList<Accounts> refundList = getRefundList(rs.getInt(1));
				ArrayList<Accounts>transactionList = chargesAccountProcessingDAO.gettransactionList(""+rs.getInt(1));
				invoice.setTransactionList(transactionList);
				invoice.setRefundList(refundList);
				
				
				double creditAmount = statementDAO.getCreditAmount(rs.getInt(1));
				invoice.setBalanceN(""+DateTimeUtils.changeFormat(creditAmount));
				invoice.setPayAmount(totalPaymentReceived);
				
				//Decimal Account
				invoice.setPayAmountx(DateTimeUtils.changeFormat(totalPaymentReceived));
				
				String notes = accountsDAO.getNotes(invoice.getId());
				invoice.setSubmitInvoiceNotes(notes);
				String paymode = accountsDAO.getPaymentMode(invoice.getId());
				invoice.setPaymode(paymode);
				int ipdid = accountsDAO.getPatientipdid(rs.getInt("clientid"));
				Bed bed2= bedDao.getEditIpdData(""+ipdid);
				invoice.setIpseqno(bed2.getIpdseqno());
				
				if(invoice.getItype().equals("2")){

					Bed bed = bedDao.getEditIpdData(Integer.toString(ipdid));
					
					String temp[] = bed.getAdmissiondate().split(" ");
					String adate = DateTimeUtils.getCommencingDate1(temp[0]);
					String time[] = temp[1].split(":");
					String hh = time[0];
					String mm = time[1];
					
					adate = adate + " " + hh + ":" + mm;
					invoice.setAdmissionDate(adate);
					
					ClientDAO clientDAO = new JDBCClientDAO(connection);
					String dischargeDate = bedDao.dischargeEnDateOfPatient(""+(bed.getTreatmentepisodeid()));
					
					if(!dischargeDate.equals("")){
						 String dtemp[] = dischargeDate.split(" ");
						 adate = DateTimeUtils.getCommencingDate1(dtemp[0]);
						 String dtime[] = temp[1].split(":");
						 hh = dtime[0];
						 mm = dtime[1];
						
						 adate = adate + " " + hh + ":" + mm;
						 invoice.setDischargeDate(adate);
					}else{
						invoice.setDischargeDate("");
					}
					
				
				}
				
				if(payby.equalsIgnoreCase("Third Party")){
					int tpid = accountsDAO.getTPId(invoice.getId());
					ThirdParty thirdParty = new ThirdParty();
					thirdParty = accountsDAO.getTpDetails(tpid);
					
					invoice.setPayeename(thirdParty.getCompanyName());
					//String address = thirdParty.getAddress()+"," +thirdParty.getTown() +" " +thirdParty.getPostcode();
					invoice.setPayeeadress(thirdParty.getAddress());
					invoice.setPayeeTown(thirdParty.getTown());
					invoice.setPayeePostcode(thirdParty.getPostcode());
					invoice.setPayeeEmail(thirdParty.getEmail());
					invoice.setPayeeConatctNo(thirdParty.getTelephoneLine());
					
					
					
				}
				else{
					invoice.setPayeename(client.getTitle() + " " + client.getFirstName() + " "+client.getMiddlename()+" " + client.getLastName());
					if(client.getSecondLineaddress()!=null){
						invoice.setPayeeadress(client.getAddress() + "," + client.getSecondLineaddress());
					}else{
						invoice.setPayeeadress(client.getAddress());
					}
					
					invoice.setPayeeTown(client.getTown());
					invoice.setPayeePostcode(client.getPostCode());
					invoice.setPayeeEmail(client.getEmail());
					invoice.setPayeeConatctNo(client.getMobNo());
					
					
					
//					System.out.println(ipdid);
					
					if(ipdid>0){}else{
						invoice.setAdmissionDate("");
						invoice.setDischargeDate("");
					}
					
					invoice.setIpdid(ipdid);
					
					//set prepared by
					//set prepared by
					int preparedbyid = accountsDAO.getInvoicePreparedBy(invoice.getId());
					if(preparedbyid!=0){
						UserProfileDAO userProfileDAO = new JDBCUserProfileDAO(connection);
						UserProfile userProfile = userProfileDAO.getUserprofileDetails(preparedbyid);
						String fullname = userProfile.getInitial() + " " + userProfile.getFirstname() + " " + userProfile.getLastname();
						invoice.setPreparedby(fullname);
					}else{
						invoice.setPreparedby("");
					}
					
				}
						
				
				//ArrayList<ChargesInvoice>chargeinvList = getChargeInvoice(invoice.getId());
				int checkipdinvoice = checkIpdInvoice(invoice.getId());
				
	/*			if(invoicetype.equals("IPD")){
					
					if(checkipdinvoice>0){
						invoice.setCheckipdinvoice(1);
						ipdlist.add(invoice);
					}
				}else if(invoicetype.equals("OPD") || invoicetype.equals("HD")){
					if(checkipdinvoice==0){
						int practitonerid = getInvoicePractitonerId(invoice.getId());
						if(practitonerid==3){
							invoice.setCheckipdinvoice(2);
							opdlist.add(invoice);
						}else{
							invoice.setCheckipdinvoice(3);
							hdList.add(invoice);
						}
						
					}
				}else{
					if(checkipdinvoice>0){
						invoice.setCheckipdinvoice(1);
					}else{
						int practitonerid = getInvoicePractitonerId(invoice.getId());
						if(practitonerid==3){
							invoice.setCheckipdinvoice(2);
						}else{
							invoice.setCheckipdinvoice(3);
						}
					}
					
					
				    
				    if(invpaid.equals("1")&&!(invoice.getBalancex().equals("0.00"))){
				    	continue;
				    }else if(invpaid.equals("2")&&(invoice.getBalancex().equals("0.00"))){
				    	continue;
				    }
				    
					list.add(invoice);
				}
					
				*/
				//showing seqno instead of invoice no.  
				AccountsDAO accountsDAO2=new JDBCAccountsDAO(connection);
			    int ipdopdseq=accountsDAO.getIpdOpdSeqNo((invoice.getId()));
			    invoice.setIpdopdseq(String.valueOf(ipdopdseq));
				invoice.setBalancex(""+balnce);
				list.add(invoice);
				
			}
			
			/*if(invoicetype.equals("IPD")){
				
				return ipdlist;
			}else if(invoicetype.equals("OPD")){
				return opdlist;
			}else if(invoicetype.equals("HD")){
				return hdList;
			}
			else{
				return list;
			}*/
			
			
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		return list;
	}

	private int getInvoicePractitonerId(int id) {
		PreparedStatement preparedStatement = null;
		int result = 0;
		StringBuffer sql = new StringBuffer();
		
		sql.append("select apm_invoice.practitionerid from  apm_charges_invoice  inner join apm_charges_assesment on ");
		sql.append("apm_charges_assesment.chargeinvoiceid = apm_charges_invoice.id ");
		sql.append("inner join apm_invoice on apm_invoice.id = apm_charges_assesment.invoiceid ");
		sql.append("inner join apm_invoice_assesments on apm_invoice_assesments.invoiceid = apm_invoice.id ");
		sql.append("where apm_charges_invoice.id = "+id+" group by ipdid ");
		
		try{
			preparedStatement = connection.prepareStatement(sql.toString());
			ResultSet rs = preparedStatement.executeQuery();
			if(rs.next()){
				result = rs.getInt(1);
			}
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	private int checkIpdInvoice(int id) {
		PreparedStatement preparedStatement = null;
		int result = 0;
		StringBuffer sql = new StringBuffer();
		
		sql.append("select apm_invoice.ipdid from  apm_charges_invoice  inner join apm_charges_assesment on ");
		sql.append("apm_charges_assesment.chargeinvoiceid = apm_charges_invoice.id ");
		sql.append("inner join apm_invoice on apm_invoice.id = apm_charges_assesment.invoiceid ");
		sql.append("inner join apm_invoice_assesments on apm_invoice_assesments.invoiceid = apm_invoice.id ");
		sql.append("where apm_charges_invoice.id = "+id+" group by ipdid ");
		
		try{
			preparedStatement = connection.prepareStatement(sql.toString());
			ResultSet rs = preparedStatement.executeQuery();
			if(rs.next()){
				result = rs.getInt(1);
			}
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	private ArrayList<ChargesInvoice> getChargeInvoice(int invoiceid) {
		PreparedStatement preparedStatement = null;
		ArrayList<ChargesInvoice> list = new ArrayList<ChargesInvoice>();
		
		return null;
	}

	public int getInvoicePreparedBy(int invoiceid) {
		PreparedStatement preparedStatement = null;
		int result = 0;
		String sql = "SELECT updatedby FROM apm_charges_invoice where id = "+invoiceid+" ";
		
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

	public String getChargesClientid(String invoiceid) {
		PreparedStatement preparedStatement = null;
		String result = "";
		String sql = "SELECT clientid FROM apm_invoice where id = "+invoiceid+" ";
		
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

	public ArrayList<Master> getChargesEstimateMasterAssessmentList(
			String invoiceid) {
		PreparedStatement preparedStatement = null;
		ArrayList<Master>list = new ArrayList<Master>();
		StringBuffer sql = new StringBuffer();
		
		sql.append("SELECT masterchargetype,commencing FROM apm_invoice_assesments where invoiceid = "+invoiceid+" group by masterchargetype ");
		
		
		try{
			preparedStatement = connection.prepareStatement(sql.toString());
			ResultSet  rs = preparedStatement.executeQuery();
			while(rs.next()){
				Master master = new Master();
				master.setName(rs.getString(1));
				master.setDatetime(rs.getString(2));
				
				Vector<Accounts> assesmentList = getChargesEstimateFilteredPreviewChargesList(master.getName(),invoiceid);
				master.setAssesmentList(assesmentList);
				
				list.add(master);
			}
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		return list;
	}

	private Vector<Accounts> getChargesEstimateFilteredPreviewChargesList(
			String name, String invoiceid) {
		PreparedStatement preparedStatement = null;
		Vector<Accounts>list = new Vector<Accounts>();
		String sql = "SELECT apmtType,charge,practitionerName,practitionerId,paybuy,id,commencing,appointmentid,quantity  FROM apm_invoice_assesments" +
				" where masterchargetype = '"+name+"' and invoiceid="+invoiceid+" ";
		
		try{
			preparedStatement = connection.prepareStatement(sql.toString());
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()){
				
				String apptName = rs.getString(1);
				String charge = rs.getString(2);
				if(charge==null){
					charge = "0.0";
				}
				String practitionerName = rs.getString(3);
				String practitionerid = rs.getString(4);
				int payby = rs.getInt(5);
				int addtitonalCharge = 1;
				String chargeid = rs.getString(6);
				String date = rs.getString(7);
				int apptid = rs.getInt(8);
				double quantity = rs.getDouble(9);
				
				
				
				Accounts accounts = new Accounts();
				
		
				accounts.setCharges(DateTimeUtils.changeFormat(Double.parseDouble(charge)));
				accounts.setChargeTotal(DateTimeUtils.changeFormat(Double.parseDouble(charge)*quantity));
				accounts.setPractitionerName(practitionerName);
				String practitionerFullName = getPractitionerFullName(practitionerid);
				accounts.setPractitionerName(practitionerFullName);
				/* accounts.setQuantity(quantity); */
				accounts.setQty(quantity);
				accounts.setPayBy(payby);
				accounts.setAppointmentType(apptName);
				list.add(accounts);
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
		return list;
	}

	public ArrayList<Master> getInvoiceTypeList() {
		PreparedStatement preparedStatement = null;
		ArrayList<Master>list = new ArrayList<Master>();
		String sql = "SELECT id,type FROM apm_invoice_type where service=0 ";
		
		try{
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()){
				Master master = new Master();
				master.setId(rs.getInt(1));
				master.setName(rs.getString(2));
				list.add(master);
			}
			
		}catch (Exception e) {
			// TODO: handle exception
		}
		
		return list;
	}

	public int getMaxResetInv() {
		PreparedStatement preparedStatement = null;
		int result = 0;
		String sql = "SELECT resetinv FROM apm_charges_invoice order by id desc limit 0,1 ";
		
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

	public int getMaxResetCreditInv() {
		PreparedStatement preparedStatement = null;
		int result = 0;
		String sql = "SELECT resetinv FROM apm_credit_account where payment_mode IS NOT NULL  order by id desc limit 0,1 ";
		
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

	public String getInvoiceTypeId(int invoiceid) {

		String id="";
		try {
			
			String sql="select itype FROM apm_charges_invoice where id="+invoiceid+"";
			PreparedStatement ps=connection.prepareStatement(sql);
			ResultSet rs=ps.executeQuery();
			while(rs.next()){
				
				id=rs.getString(1);
			}
		
		} catch (Exception e) {

		   e.printStackTrace();
		}
		
		return id;
	}

	public String getInvoiceName(String invoicenameid) {

		String invoicename="";
		try {
			
			String sql="select type from apm_invoice_type where id="+invoicenameid+"";
			PreparedStatement ps=connection.prepareStatement(sql);
			ResultSet rs=ps.executeQuery();
			
			while(rs.next()){
				
				invoicename=rs.getString(1);
			}
			
		} catch (Exception e) {

		   e.printStackTrace();
		}
		
		return invoicename;
	}

	public Accounts getFromtodateforHD(int invoiceid) {

		Accounts accounts=new Accounts();
		try {
			
			String sql="SELECT fromdate,todate from apm_charges_invoice where id="+invoiceid+"";
			PreparedStatement ps=connection.prepareStatement(sql);
			ResultSet rs=ps.executeQuery();
			
			while(rs.next()) {
				
				  accounts.setFromDate(rs.getString(1));
				  accounts.setToDate(rs.getString(2));
			}
				
		} catch (Exception e) {

			e.printStackTrace();
		}
		
		return accounts;
	}

	public int updateHDfromDatetoDate(String fromdate, String todate,
			int invoiceid) {
		
		int result=0;
		
		try {
			
			String sql="update apm_charges_invoice set fromdate=?,todate=? where id="+invoiceid+"";
			PreparedStatement ps=connection.prepareStatement(sql);
			ps.setString(1, fromdate);
			ps.setString(2, todate);
			
			result=ps.executeUpdate();
		} catch (Exception e) {

			e.printStackTrace();
		}
		return result;
	}

	public String getInvoicIdfromAppointment(String appointmentid) {

		String inoiceid="0";
		try {
			String sql="select id from apm_invoice where appointmentid="+appointmentid+"";
			PreparedStatement ps=connection.prepareStatement(sql);
			ResultSet rs=ps.executeQuery();
			
			while(rs.next()){
				 inoiceid=rs.getString(1);
			}
		} catch (Exception e) {

			e.printStackTrace();
		}
		
		return inoiceid;
	}

	public String getChargeInvoiceId(String invoiceid) {

		String chargeInvoiceId="0";
		try {
			
			String sql="SELECT chargeinvoiceid from apm_charges_assesment where invoiceid="+invoiceid+"";
			PreparedStatement ps=connection.prepareStatement(sql);
			ResultSet rs=ps.executeQuery();
			while(rs.next()){
				  chargeInvoiceId=rs.getString(1);
				
			}
		} catch (Exception e) {

			e.printStackTrace();
		}
		return chargeInvoiceId;
	}

	public Accounts getInvoiceChargesDetails(String chargeInvoiceid) {

		Accounts accounts=new Accounts();
		try {
			
			String sql="select payby, date, discount, debit, clientid, predayornot,itype "
					+ "from apm_charges_invoice where id="+chargeInvoiceid+"";
			PreparedStatement ps=connection.prepareStatement(sql);
			ResultSet rs=ps.executeQuery();
			while(rs.next()){
			
				accounts.setPayby(rs.getString(1));
				accounts.setDate(rs.getString(2));
				accounts.setDiscount(rs.getDouble(3));
				accounts.setAmount(rs.getDouble(4));
				accounts.setClientid(rs.getInt(5));
				accounts.setPredayornot(rs.getInt(6));
				accounts.setItype(rs.getString(7));
			}
			
		} catch (Exception e) {

			e.printStackTrace();
			
		}
		
		return accounts;
	}

	public ArrayList<Accounts> getAllMedicineBill(String clientId) {

		ArrayList<Accounts> list=new ArrayList<Accounts>();
		
		try {
			
			String sql="SELECT id,date from apm_medicine_bill where clientid="+clientId+"";
			PreparedStatement ps=connection.prepareStatement(sql);
			ResultSet rs=ps.executeQuery();
			
			while(rs.next()){
				
				Accounts accounts=new Accounts();
				accounts.setId(rs.getInt(1));
				String date=DateTimeUtils.getCommencingDate1(rs.getString(2));
				accounts.setNotes(""+accounts.getId()+" "+date+"");
				accounts.setPriscid(getPriscId(accounts.getId()));
				if(!isBillExists(rs.getInt(1))){
					list.add(accounts);
				}
			}
			        
			
		} catch (Exception e) {

			e.printStackTrace();
		}
		
		return list;
	}

	
	private boolean isBillExists(int billno) {
		
		try {
			String sql="select id from apm_invoice where billno="+billno+"";
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
	

	public String getPriscId(int bill){
		
		String res="";
		try {
			
			String sql="SELECT id from apm_client_parent_priscription where billno="+bill+"";
			PreparedStatement ps=connection.prepareStatement(sql);
			ResultSet rs=ps.executeQuery();
			
			while(rs.next()){
				 
				res=rs.getString(1);
			}
			
		} catch (Exception e) {

			e.printStackTrace();
		}
		
		return res;
	}

	public int saveInvoiceMedicine(CompleteAppointment completeAppointment,
			int billno) {

		int result=0;
		
		try {
			
			String sql="insert into apm_invoice (clientid,clientname,date,chargetype,billno,location) values (?,?,?,?,?,?)";
			PreparedStatement ps=connection.prepareStatement(sql);
			ps.setString(1,completeAppointment.getClientId() );
			ps.setString(2,completeAppointment.getClient());
			ps.setString(3, completeAppointment.getInvoiceDate());
			ps.setString(4, "Charge");
			ps.setInt(5, billno);
			ps.setInt(6, 1);
			result=ps.executeUpdate();
			
			if(result>0){
				
				 ResultSet rs=ps.getGeneratedKeys();
				 while(rs.next()){
					 
					  result=rs.getInt(1);
				 }
			}
			
		} catch (Exception e) {

			e.printStackTrace();
		}
		
		return result;
	}

	public int saveInvoiceCharges(Priscription priscription, int invoiceid) {

		  int result=0;
		  try {
			  
			  String clientId = priscription.getClientId();
			  
			//set department to charges
				UserProfileDAO userProfileDAO = new JDBCUserProfileDAO(connection);
				AssessmentFormDAO assessmentFormDAO = new JDBCAssessmentFormDAO(connection);
				String practionerId = "0";
				String condition = "0";
				
				//get ipd details
				int bedid = assessmentFormDAO.getIpdBedno(clientId);
				if(bedid!=0){
					IpdDAO ipdDAO = new JDBCIpdDAO(connection);
					BedDao bedDao=new JDBCBedDao(connection);
					
					String admissionid = assessmentFormDAO.getAdmissionid(clientId);
					
					Bed bed = bedDao.getEditIpdData(admissionid);
					practionerId = bed.getPractitionerid();
				    UserProfile	userProfile = userProfileDAO.getUserprofileDetails(Integer.parseInt(practionerId));
					
				    condition = userProfile.getDiciplineName();
					
				}else{
					ClientDAO clientDAO = new JDBCClientDAO(connection);
					NotAvailableSlot notAvailableSlot = clientDAO.getLastAppointmentdetails(clientId);
					if(notAvailableSlot.getDiaryUserId()!=0){
						practionerId = Integer.toString(notAvailableSlot.getDiaryUserId());
					}
					
					
					//check if doctor placed with machine
					
					UserProfile userProfile = userProfileDAO.getUserprofileDetails(notAvailableSlot.getDiaryUserId());
					
					if(userProfile.getJobgroup()==null){
						userProfile.setJobgroup("");
					}
					if(userProfile.getJobgroup().equals("4")){
						practionerId = userProfile.getDoctor();
					}
					
					if(practionerId==null){
						practionerId="0";
					}
					
					userProfile = userProfileDAO.getUserprofileDetails(Integer.parseInt(practionerId));
					condition = userProfile.getDiciplineName();
					
					if(practionerId.equals("0")){
						condition = "0";
					}
					
				}
				
			  
		   
		   String sql="insert into apm_invoice_assesments (invoiceid, user, apmtType, charge, practitionerId, practitionerName, clientId, commencing, paybuy,active, quantity, masterchargetype,thirdPartyId,department)" +
		     " values (?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		   
		   PreparedStatement ps=connection.prepareStatement(sql);
		   ps.setInt(1, invoiceid);
		   ps.setString(2,priscription.getClientname());
		   ps.setString(3, priscription.getMdicinenametxt());
		   ps.setString(4, priscription.getMrp());
		   ps.setString(5, priscription.getPrectionerid());
		   ps.setString(6, priscription.getFullname());
		   ps.setString(7, priscription.getClientId());
		   ps.setString(8, priscription.getDate());
		   ps.setString(9, priscription.getWhopay());
		   ps.setString(10, "1");
		   ps.setInt(11,priscription.getSaleqty());
		   ps.setString(12, "Medicine Charge");
		   ps.setString(13, priscription.getTpid());
		   ps.setString(14, condition);
		   result=ps.executeUpdate();
		   
		  } catch (Exception e) {

		   e.printStackTrace();
		  }
		  
		  return result;
		 }

	public String getInvoicePayBy(int invoiceid) {

		String payby="";
		try {
			
			String sql="select payby from apm_charges_invoice where id="+invoiceid+"";
			PreparedStatement ps=connection.prepareStatement(sql);
			ResultSet rs=ps.executeQuery();
			
			while(rs.next()){
				
				payby=rs.getString(1);
			}
		} catch (Exception e) {

			e.printStackTrace();
		}
		
		return payby;
	}
	
	
	public ArrayList<Accounts> getAssesmentList(String invoiceid) {
		PreparedStatement preparedStatement = null;
		ArrayList<Accounts> list = new ArrayList<Accounts>();
		String sql = "SELECT id,commencing,user,apmtType,charge,practitionerName,appointmentid,apmtType,practitionerId,quantity,chargeId FROM apm_invoice_assesments where invoiceid = "+invoiceid+" and apm_invoice_assesments.active=1 ";
		
		try{
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()){
				Accounts accounts = new Accounts();
				accounts.setId(rs.getInt(1));
				accounts.setCommencing(rs.getString(2));
				accounts.setClientName(rs.getString(3));
				accounts.setAppointmentType(rs.getString(4));
				//accounts.setCharges(dateTimeUtils.changeFormat(Double.parseDouble(rs.getString(5))* rs.getInt(10)));
				accounts.setCharges(DateTimeUtils.changeFormat(Double.parseDouble(rs.getString(5))));
				accounts.setPractitionerName(rs.getString(6));
				String treamentEpisodeName = getTreatmentEpisodeName(rs.getString(7));
				accounts.setTreatmentEpisodeName(treamentEpisodeName);
				String chargeType = getChargeTypeOfApmt(rs.getString(4));
				if(rs.getString(4).equalsIgnoreCase(Constants.POLICYEXCESS)){
					chargeType = Constants.POLICYEXCESS; 
				}
				
				if(chargeType==null){
					accounts.setChargeType("");
				}else{
					accounts.setChargeType(chargeType);
				}
				accounts.setAppointmentType(rs.getString(8));
				String practionerFullName = getPractitionerFullName(rs.getString(9));
				accounts.setPractitionerName(practionerFullName);
				
				accounts.setQuantity(rs.getInt(10));
				accounts.setChargeid(rs.getString(11));
				list.add(accounts);
			}
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	public int updateTpCharges(Accounts accounts,AppointmentType appointmentType) {

		int result=0;
		try {
			
			String sql="update apm_invoice_assesments set charge=?,paybuy=?,thirdPartyId=?,chargeId=? where id="+accounts.getId()+"";
			PreparedStatement ps=connection.prepareStatement(sql);
			ps.setString(1, appointmentType.getCharges());
			ps.setInt(2, accounts.getPayBy());
			ps.setString(3, appointmentType.getTpid());
			ps.setInt(4, appointmentType.getId());
			
			result=ps.executeUpdate();
			
		} catch (Exception e) {

			e.printStackTrace();
		}
		
		return result;
	}

	public int updateInvoicePayeeandCharges(String invoicepayby, int invoiceid,
			double sumtTotal,String tpid) {

		int result=0;
		
		try {
			String sql="update apm_charges_invoice set payby=?,debit=?,tpid=? where id="+invoiceid+"";
			PreparedStatement ps=connection.prepareStatement(sql);
			ps.setString(1, invoicepayby);
			ps.setDouble(2, sumtTotal);
			ps.setString(3, tpid);
			result=ps.executeUpdate();
			
		} catch (Exception e) {

			e.printStackTrace();
		}
		
		return result;
	}

	public int updateDeleteStatus(int invoiceid,String notes, String userid, String date) {

		int result=0;
		try {
			
			String sql="update apm_charges_invoice set isdeleted=1,debit=0,cancelNotes=?,cancelUserid=?,cancelDT=? where id="+invoiceid+"";
			PreparedStatement ps=connection.prepareStatement(sql);
			ps.setString(1, notes);
			ps.setString(2, userid);
			ps.setString(3, date);
			result=ps.executeUpdate();
		} catch (Exception e) {

			e.printStackTrace();
		}
		
		return result;
	}

	public int deleteChargesPayment(int invoiceid) {

		int result=0;
		try {
			String sql="delete from apm_charges_payment where chargeinvoiceid="+invoiceid+"";
			PreparedStatement ps=connection.prepareStatement(sql);
			
			result=ps.executeUpdate();
			
		} catch (Exception e) {

			e.printStackTrace();
		}
		
		return result;
	}

	public String getChequeNumber(int invoiceid) {

		String chequeNo="";
		
		try {
			
			String sql="select chequeno from apm_charges_payment where chargeinvoiceid="+invoiceid+" ";
			PreparedStatement ps=connection.prepareStatement(sql);
			ResultSet rs=ps.executeQuery();
			while(rs.next()){
				 
				  chequeNo=rs.getString(1);
			}
			
		} catch (Exception e) {

			e.printStackTrace();
		}
		
		return chequeNo;
	}

	public Accounts getAdvanceInvoiceDetails(String invoiceid) {
		PreparedStatement preparedStatement = null;
		Accounts accounts = new Accounts();
		String sql = "SELECT apm_credit_account.id, date, client_id, payby,  payment_mode,  apm_credit_account.userid,refundnote,concat(apm_user.firstname,' ',apm_user.lastname),apm_credit_account.credit_note FROM apm_credit_account left join apm_user on apm_user.id= practitioner_id where apm_credit_account.id = "+invoiceid+" ";
		
		try{
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			if(rs.next()){
				accounts.setId(rs.getInt(1));
				accounts.setDate(rs.getString(2));
				accounts.setClientid(rs.getInt(3));
				accounts.setPayby(rs.getString(4));
				accounts.setPaymentmode(rs.getString(5));
				accounts.setUserid(rs.getString(6));
				accounts.setNotes(rs.getString(7));
				if(rs.getString(8)!=null){
				accounts.setPractitionerName("Dr."+rs.getString(8));
				}else{
					accounts.setPractitionerName("");	
				}
				accounts.setRemark(rs.getString(9));
			}
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		return accounts;
	}

	public int getPrePaymentID(String clientId) {
		PreparedStatement preparedStatement = null;
		int result = 0;
		String sql = "SELECT id FROM apm_credit_account where client_id="+clientId+" and charge!=0 order by id desc limit 0,1 ";
		
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

	public int updatePrePaymentID(int paymentid, int creditinvoiceid) {
		PreparedStatement preparedStatement = null;
		int result = 0;
		String sql = "update apm_charges_payment set prepaymentid="+creditinvoiceid+" where id = "+paymentid+" ";
		
		try{
			preparedStatement = connection.prepareStatement(sql);
			result = preparedStatement.executeUpdate();
			
		}catch (Exception e) {
			// TODO: handle exception
		}
		
		return result;
	}

	public ArrayList<Accounts> getPrePaymentList(int invoiceid,String clientId) {
		PreparedStatement preparedStatement = null;
		ArrayList<Accounts>list = new ArrayList<Accounts>();
		StringBuffer sql = new StringBuffer();
		
		sql.append("SELECT apm_credit_account.id,payment,credit_note,apm_charges_payment.date,advance_ipdid,apm_credit_account.client_id,apm_credit_account.balance  FROM apm_credit_account inner join apm_charges_payment on ");
		sql.append("apm_charges_payment.prepaymentid = apm_credit_account.id ");
		sql.append("where chargeinvoiceid = "+invoiceid+" ");
		
		try{
			preparedStatement = connection.prepareStatement(sql.toString());
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()){
				Accounts accounts = new Accounts();
				accounts.setId(rs.getInt(1));
				list = getAdvancePaymentList(accounts.getId(),""+rs.getInt(5),invoiceid,rs.getString(6));
				Accounts acc=getPaymentAgainstlist(""+invoiceid);
				acc.setIpdid(DateTimeUtils.isNull(rs.getString(5)));		
				if(acc!=null){
					if(Double.parseDouble(acc.getAmountx())>0){
						list.add(acc);
					}
				}
				/*accounts.setAmountx(DateTimeUtils.changeFormat(rs.getDouble(2)));
				accounts.setNotes(rs.getString(3));
				String date = rs.getString(4);
				String temp[] = date.split(" ");
				accounts.setDate(DateTimeUtils.getCommencingDate1(temp[0]));
				
				list.add(accounts);
				*/
			}
			
		}catch (Exception e) {
			// TODO: handle exception
		}
			
			
		return list;
	}

	private ArrayList<Accounts> getAdvancePaymentList(int id,String ipdid,int invoiceid,String clientId) {
		PreparedStatement preparedStatement = null;
		ArrayList<Accounts>list = new ArrayList<Accounts>();
		//String sql = "SELECT  id,charge,credit_note,date,refinvoiceid,payment_mode FROM apm_credit_account where advance_ipdid='"+ipdid+"' and  payment_mode!='prepayment' and id<="+id+" and client_id='"+clientId+"' order by id desc ";
		String sql = "SELECT  id,charge,credit_note,date,refinvoiceid,payment_mode,balance FROM apm_credit_account where advance_ipdid='"+ipdid+"' and  payment_mode is not null and id<="+id+" and client_id='"+clientId+"' order by id desc ";
		try{
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()){
				if(rs.getDouble(7)==0){
					break;
				}
				if(DateTimeUtils.isNull(rs.getString(6)).equals("prepayment")){
					continue;
				}
				
				Accounts accounts = new Accounts();
				accounts.setId(rs.getInt(1));
				accounts.setIpdid(ipdid);
				accounts.setAmountx(DateTimeUtils.changeFormat(rs.getDouble(2)));
				accounts.setNotes(rs.getString(3));
				String date = rs.getString(4);
				String temp[] = date.split(" ");
				accounts.setDate(DateTimeUtils.getCommencingDate1(temp[0])+ " "+ temp[1]);
				accounts.setPhysical_payment_id(getPhysicalpaymentIdAdvRef(rs.getString(1)));
				double charge = rs.getDouble(2);
				accounts.setAdvref(0);
				accounts.setPaymentmode(rs.getString(6));
				
				if(charge>0){
					list.add(accounts);
				}
				
				int refinvoiceid = rs.getInt(5);
				if(refinvoiceid>0){
					
					Accounts accounts2 = getRefundData(invoiceid);
					accounts2.setAdvref(1);
					if(accounts2.getAmount()>0){
						list.add(accounts2);
					}
					//break;
					//  16 april 2018 when above break then it not execute after that loop... if advance is there then it not show in invoice
					continue;
				}
			}
			
		}catch (Exception e) {
			// TODO: handle exception
		}
		return list;
	}

	public Accounts getRefundData(int refinvoiceid) {
		PreparedStatement preparedStatement = null;
		Accounts accounts = new Accounts();
		String sql = "SELECT id,debit,credit_note,date,refinvoiceid FROM apm_credit_account where refinvoiceid =  "+refinvoiceid+" ";
		
		try{
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()){
				accounts.setId(rs.getInt(1));
				accounts.setAmountx(DateTimeUtils.changeFormat(rs.getDouble(2)));
				accounts.setNotes(rs.getString(3));
				String date = rs.getString(4);
				String temp[] = date.split(" ");
				accounts.setDate(DateTimeUtils.getCommencingDate1(temp[0]));
				accounts.setAmount(rs.getDouble(2));
			}
		}catch (Exception e) {
			// TODO: handle exception
		}
		return accounts;
	}

	public int getIpdIdFromInvoice(int invoiceid) {

		int result=0;
		try {
			
			StringBuffer buffer=new StringBuffer();
			buffer.append("select apm_invoice_assesments.ipdid from apm_invoice_assesments inner join apm_charges_assesment ");
			buffer.append("on apm_invoice_assesments.invoiceid=apm_charges_assesment.invoiceid inner join ");
			buffer.append("apm_charges_invoice on apm_charges_invoice.id=apm_charges_assesment.chargeinvoiceid ");
			buffer.append("where apm_charges_invoice.id ="+invoiceid+"; ");
			
			PreparedStatement ps=connection.prepareStatement(buffer.toString());
			ResultSet rs=ps.executeQuery();
			
			while(rs.next()){
				 
				  result =rs.getInt(1);
				  break;
			}
			
		} catch (Exception e) {

			e.printStackTrace();
		}
		
		return result;
	}

	public ArrayList<Master> getBankNameList() {
		PreparedStatement preparedStatement = null;
		ArrayList<Master>list = new ArrayList<Master>();
		String sql = "select id,name from bankname_list ";
		
		try{
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()){
				Master master = new Master();
				master.setId(rs.getInt(1));
				master.setName(rs.getString(2));
				
				list.add(master);
			}
			
		}catch (Exception e) {
			// TODO: handle exception
		}
		return list;
	}

	public int getPeactOpdID(int invoiceid) {
		PreparedStatement preparedStatement = null;

		int result = 0;
		StringBuffer sql = new StringBuffer();
		
		sql.append("SELECT appointmentid FROM apm_charges_assesment inner join apm_invoice on ");
		sql.append("apm_invoice.id = apm_charges_assesment.invoiceid where chargeinvoiceid = "+invoiceid+" ");
		sql.append("order by apm_charges_assesment.id desc limit 0,1 ");
		
		try{
			preparedStatement = connection.prepareStatement(sql.toString());
			ResultSet rs = preparedStatement.executeQuery();
			if(rs.next()){
				result = getopdpractid(rs.getInt(1));
			}
			
			
		}catch (Exception e) {
			// TODO: handle exception
		}
		
		return result;
	}

	private int getopdpractid(int appointmentid) {
		PreparedStatement preparedStatement = null;
		int result = 0 ;
		String sql = "SELECT diaryuserid FROM apm_available_slot where id = "+appointmentid+" ";
		
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

	public Accounts getpaymentdetails(int invoiceid) {
		PreparedStatement preparedStatement = null;
		Accounts accounts = new Accounts();
		String sql = "select payment,paymode,prepaymentid from apm_charges_payment where chargeinvoiceid = "+invoiceid+" and paymode='prepayment' ";
		
		try{
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			if(rs.next()){
				accounts.setPayAmountx(rs.getString(1));
				accounts.setPaymentmode(rs.getString(2));
				accounts.setPrepaymentid(rs.getString(3));
				
			}
			
		}catch (Exception e) {
			// TODO: handle exception
		}
		return accounts;
	}

	public boolean chekpaymentdone(int invoiceid) {
		PreparedStatement preparedStatement = null;
		boolean result = false;
		String sql = "select * from apm_charges_payment where chargeinvoiceid = "+invoiceid+" and paymode='prepayment' ";
		
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


	public int updatePercentageAmount(String invoiceId, String discval,
			String disctype, String userid, String datetime) {
		PreparedStatement preparedStatement = null;
		int result = 0;
		String sql = " ";
		
		if(disctype.equals("0")){
			sql = "update apm_charges_invoice set discount=?,disctype=?,disc_given_userid=?,disc_given_date=? where id="+invoiceId+" ";
		}else{
			sql = "update apm_charges_invoice set discamt=?,disctype=?,disc_given_userid=?,disc_given_date=? where id="+invoiceId+" ";
		}
		
		try{
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, discval);
			preparedStatement.setString(2, disctype);
			preparedStatement.setString(3, userid);
			preparedStatement.setString(4, datetime);
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

	public Accounts getAccDiscData(int invoiceid) {
		PreparedStatement preparedStatement = null;
		Accounts accounts = new Accounts();
		String sql = "SELECT disctype,discamt,ipdid FROM apm_charges_invoice where id = "+invoiceid+" ";
		
		try{
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			if(rs.next()){
				accounts.setDisctype(rs.getString(1));
				accounts.setDiscamt(rs.getString(2));
				accounts.setIpdid(rs.getString(3));
			}
			
		}catch (Exception e) {
			// TODO: handle exception
		}
		return accounts;
	}

	/*public int updateRecPayment(String id, String amount) {
		PreparedStatement preparedStatement = null;
		int result = 0;
		String sql = "update apm_charges_payment set payment=? where id= "+id+" ";
		
		try{
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, amount);
			
			result = preparedStatement.executeUpdate();
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}*/
	public int updateRecPayment(String id, String amount,String paymode) {
		  PreparedStatement preparedStatement = null;
		  int result = 0;
		  String sql = "update apm_charges_payment set payment=?,paymode=? where id= "+id+" ";
		  
		  try{
		   preparedStatement = connection.prepareStatement(sql);
		   preparedStatement.setString(1, amount);
		   preparedStatement.setString(2, paymode);
		   
		   result = preparedStatement.executeUpdate();
		   
		  }catch (Exception e) {
		   e.printStackTrace();
		  }
		  
		  return result;
		 }

	public int delchargeAssesment(String id) {
		PreparedStatement preparedStatement = null;
		int result = 0;
		String sql = "delete FROM apm_invoice_assesments where invoiceid = "+id+" ";
		
		try{
			
			preparedStatement = connection.prepareStatement(sql);
			result = preparedStatement.executeUpdate();
		}catch (Exception e) {
			// TODO: handle exception
		}
		return result;
	}

	public int delChargeid(String id) {
		PreparedStatement preparedStatement = null;
		int result = 0;
		String sql = "delete  FROM apm_invoice where id = "+id+" ";
		
		try{
			
			preparedStatement = connection.prepareStatement(sql);
			result = preparedStatement.executeUpdate();
		}catch (Exception e) {
			// TODO: handle exception
		}
		return result;
	}

	public int updateChargeqty(String id, String qty) {
		PreparedStatement preparedStatement = null;
		int result = 0;
		String sql = "update apm_invoice_assesments set quantity="+qty+" where id="+id+" ";
		
		try{
			preparedStatement = connection.prepareStatement(sql);
			result = preparedStatement.executeUpdate();
			
		}catch (Exception e) {
			// TODO: handle exception
		}
		return result;
	}

	public int insertchargeDetailsLog(CompleteAppointment completeAppointment, String qty, String status,
			String userid,String datetime) {
		PreparedStatement preparedStatement = null;
		int result = 0;
		String sql = "insert into charge_details_log(chargeid, invoicedid, apmtType, charge, clientid, quantity, masterchargetype, invoiced, status, updated, userid,ipdid,datetime) value(?,?,?,?,?,?,?,?,?,?,?,?,?)";
		
		try{
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, completeAppointment.getId());
			preparedStatement.setString(2, completeAppointment.getInvoiceid());
			preparedStatement.setString(3, completeAppointment.getApmtType());
			preparedStatement.setString(4, completeAppointment.getCharges());
			preparedStatement.setString(5, completeAppointment.getClientId());
			preparedStatement.setDouble(6, completeAppointment.getQuantity());
			preparedStatement.setString(7, completeAppointment.getMasterchargetype());
			preparedStatement.setInt(8, completeAppointment.getInvoiced());
			preparedStatement.setString(9, status);
			preparedStatement.setString(10, qty);
			preparedStatement.setString(11, userid);
			preparedStatement.setInt(12, completeAppointment.getIpdid());
			preparedStatement.setString(13, datetime);
			result = preparedStatement.executeUpdate();
		}catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public String getAssessmentIdList(String id) {
		PreparedStatement preparedStatement = null;
		String result = "";
		String sql = "SELECT id FROM apm_invoice_assesments where invoiceid = "+id+" ";
		
		try{
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()){
				result = result + rs.getString(1) + ",";
			}
			
			result = result.substring(0,result.length()-1);
		}catch (Exception e) {
			// TODO: handle exception
		}
		return result;
	}

	public int deleteStandardCharge(String assesmentidList) {
		PreparedStatement preparedStatement = null;
		int result = 0;
		String sql = "delete from apm_std_onoff_charge where assesmentid in("+assesmentidList+") ";
		
		try{
			preparedStatement = connection.prepareStatement(sql);
			result = preparedStatement.executeUpdate();
			
		}catch (Exception e) {
			// TODO: handle exception
		}
		return result;
	}

	public Accounts getAssesmentDetails(String assesmentid) {
		PreparedStatement preparedStatement = null;
		Accounts accounts = new Accounts();
		String sql = "select id, quantity,commencing,apmttype,tpcommencing,showing_date from apm_invoice_assesments where id = "+assesmentid+" ";
		
		try{
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			if(rs.next()){
				accounts.setId(rs.getInt(1));
				accounts.setQuantity(rs.getInt(2));
				accounts.setCommencing(rs.getString(3));
				accounts.setCharges(rs.getString(4));
				accounts.setTpcommencing(rs.getString(5));
				accounts.setShowdate(rs.getString(6));
			}
			
			
		}catch (Exception e) {
			// TODO: handle exception
		}
		return accounts;
	}

	public int getStdchargeid(String assesmentid, String ipdid) {
		PreparedStatement preparedStatement = null;
		int result = 0;
		String sql = "SELECT id FROM apm_std_onoff_charge where assesmentid = "+assesmentid+" and ipdid = "+ipdid+" ";
		
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

	public int updateStdChargeDateTime(int onofid, String ondate, String offdate) {
		PreparedStatement preparedStatement = null;
		int result = 0;
		String sql = "update apm_std_onoff_charge set ondatetime=?,offdatetime=? where id="+onofid+" ";
		
		try{
			
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, ondate);
			preparedStatement.setString(2, offdate);
			result = preparedStatement.executeUpdate();
			
			
			
		}catch (Exception e) {
			// TODO: handle exception
		}
		return result;
	}

	public int updateAssessmentCommencing(String commencing, String assesmentid,String commtime,String ipdid) {
		PreparedStatement preparedStatement = null;
		int result = 0;
		String sql = "update apm_invoice_assesments set commencing=?,tpcommencing=?,ipdid=? where id="+assesmentid+" ";
		
		try{
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, commencing);
			preparedStatement.setString(2, commencing+" "+commtime);
			preparedStatement.setString(3, ipdid);
			result = preparedStatement.executeUpdate();
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public ArrayList<Accounts> getbedchargeList(String clientid, String fdate,
			String tdate) {
		PreparedStatement preparedStatement = null;
		ArrayList<Accounts>list = new ArrayList<Accounts>();
		String sql = "SELECT id FROM apm_invoice_assesments where clientid = "+clientid+" and masterchargetype = 'Bed Charge' " +
				" and commencing between '"+fdate+"' and '"+tdate+"' ";
		
		try{
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			
			while(rs.next()){
				Accounts accounts = new Accounts();
				accounts.setId(rs.getInt(1));
				
				list.add(accounts);
			}
			
		}catch (Exception e) {
			// TODO: handle exception
		}
		return list;
	}

	public int updateBedLogDate(String id, String fdate) {
		PreparedStatement preparedStatement = null;
		int result = 0;
		String sql = "update ipd_bed_change_log set lastmodified=? where id="+id+" ";
		
		try{
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, fdate);
			result = preparedStatement.executeUpdate();
			
			
		}catch (Exception e) {
			// TODO: handle exception
		}
		return result;
	}

	public String getIpdFinalDiagnosis(String fdid) {
		PreparedStatement preparedStatement = null;
		String result = "";
		String sql = "SELECT name FROM apm_diagnosis where id in("+fdid+")";
		
		
		
		try{
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()){
				result = result + rs.getString(1) + " , ";
			}
			
			if(result.length()!=0){
				result = result.substring(0,result.length() - 2);
			}
		}catch (Exception e) {
			// TODO: handle exception
		}
		return result;
	}

	public String getFdID(int ipdid) {
		PreparedStatement preparedStatement = null;
		String result = "";
		String sql = "SELECT conditionid FROM apm_final_diagnosis where ipdid = "+ipdid+" ";
		
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

	
	public int deleteChargeAssesmentOnly(String id) {
		PreparedStatement preparedStatement = null;
		int result = 0;
		String sql = "delete FROM apm_invoice_assesments where id = "+id+" ";
		
		try{
		preparedStatement = connection.prepareStatement(sql);
		result = preparedStatement.executeUpdate();
			
		}catch (Exception e) {
			// TODO: handle exception
		}
		return result;
	}

	public Accounts getInvoiceDeleteInfo(int invoiceid) {
		PreparedStatement preparedStatement = null;
		Accounts accounts = new Accounts();
		String sql = "SELECT isdeleted,cancelNotes,cancelUserid,cancelDT,dayseqno "
				+ "FROM apm_charges_invoice where id ="+invoiceid+" ";
		
		try{
			
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			if(rs.next()){
				accounts.setDeleted(rs.getInt(1));
				accounts.setCancelNotes(rs.getString(2));
				accounts.setCancelUserid(rs.getString(3));
				accounts.setCancelDT(rs.getString(4));
				accounts.setDayseqno(rs.getString(5));
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return accounts;
	}

	public String getIpdidofClient(String clientid) {

		String res="0";
		try {
			
			String sql="select id from ipd_addmission_form where clientid="+clientid+" and bedid!=0";
			PreparedStatement ps=connection.prepareStatement(sql);
			ResultSet rs =ps.executeQuery();
			while(rs.next()){
				 res =rs.getString(1);
			}
			
		} catch (Exception e) {

			e.printStackTrace();
		}
		return res;
	}

	public int updateAutochargeFlag(String ipdid, String flag) {

		int res=0;
		try {
			
			String sql="update ipd_addmission_form set autocharge="+flag+" where id="+ipdid+" ";
			PreparedStatement ps=connection.prepareStatement(sql);
			res =ps.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return res;
	}

	public int saveShareChargeToDr(Accounts accounts) {
		PreparedStatement preparedStatement = null;
		int result = 0;
		String sql = "insert into his_share_charges(pract_id,clientid,chargeid,invoiceid,chargename,datetime,commission_type,amount,remark,userid,all_or_indi,total_charge,balance_charge,commission) value(?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		
		try{
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, ""+accounts.getPractitionerId());
			preparedStatement.setString(2, ""+accounts.getClientid());
			preparedStatement.setString(3, accounts.getChargeid());
			preparedStatement.setString(4, ""+accounts.getInvoiceid());
			preparedStatement.setString(5, accounts.getCharges());
			preparedStatement.setString(6, accounts.getDate());
			preparedStatement.setString(7, accounts.getCommission_type());
			preparedStatement.setString(8, accounts.getAmountx());
			preparedStatement.setString(9, accounts.getRemark());
			preparedStatement.setString(10, accounts.getUserid());
			preparedStatement.setString(11, accounts.getAll_or_indivisual());
			preparedStatement.setString(12, accounts.getTotalAmountx());
			preparedStatement.setString(13, accounts.getShared_amount());
			preparedStatement.setString(14, accounts.getCommission());
			result = preparedStatement.executeUpdate();
		}catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public int editShareChargeToDr(Accounts accounts) {
		int res=0;
		try {
			
			String sql="update his_share_charges set pract_id="+accounts.getPractitionerId()+",commission_type='"+accounts.getCommission_type()+"',amount='"+accounts.getAmountx()+"',remark='"+accounts.getRemark()+"',edit_userid='"+accounts.getUserid()+"',edit_datetime='"+accounts.getDate()+"' where id="+accounts.getId()+" ";
			PreparedStatement ps=connection.prepareStatement(sql);
			res =ps.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return res;
	}

	public ArrayList<NotAvailableSlot> getOtDetailsList(String clientId) {
		PreparedStatement preparedStatement = null;
		ArrayList<NotAvailableSlot>list = new ArrayList<NotAvailableSlot>();
		IpdDAO ipdDAO = new JDBCIpdDAO(connection);
		BedDao bedDao = new JDBCBedDao(connection);
		ClientDAO clientDAO = new JDBCClientDAO(connection);
		String sql = "select id, diaryusername,whopay,commencing, starttime,endtime,category,ipdno,procedures,apmttypetext,charge,clientid,otnotes,otmsg "
				+ " from apm_available_slot where clientid = "+clientId+"  and category !='0' ";
		
		try{
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()){
				NotAvailableSlot n = new NotAvailableSlot();
				n.setId(rs.getInt(1));
				n.setOtname(rs.getString(2));
				n.setWhopay(rs.getString(3));
				n.setCommencing(rs.getString(4) + " " + rs.getString(5) + " - " + rs.getString(6));
				n.setCategory(rs.getString(7));
				
				Bed bed = bedDao.getEditIpdData(rs.getString(8));
				
				
				String wardname=ipdDAO.getIpdWardName(bed.getWardid());
				String bedname = ipdDAO.getIpdBedName(bed.getBedid());
				
				n.setWardbed(wardname + " / " + bedname);
				n.setProcedure(rs.getString(9));
				n.setApmttypetext(rs.getString(10));
				n.setChargeamout(DateTimeUtils.changeFormat(rs.getDouble(11)));
				
				
				n.setClientId(rs.getString(12));
				
				Client client = clientDAO.getClientDetails(rs.getString(12));
				String fullname = client.getTitle() + " " + client.getFirstName() + " "+client.getMiddlename()+" " + client.getLastName();
				
				n.setClientName(fullname);
				
				String status = rs.getString(13);
				if(status==null){
					n.setStatus("0");
				}else{
					n.setStatus("1");
				}
				
				n.setOtmsg(rs.getString(14));
				list.add(n);
				
			}
			
		}catch (Exception e) {
			// TODO: handle exception
		}
		
		
		return list;
	}

	public String getOtsmsUsers(String id) {
		PreparedStatement preparedStatement = null;
		String result = "";
		String sql = "select surgeon,anesthesia from apm_available_slot where id = "+id+" ";
		
		NotAvailableSlot n = new NotAvailableSlot();
		try{
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			if(rs.next()){
				n.setSurgeon(rs.getString(1));
				n.setAnesthesia(rs.getString(2));
			}
			
			sql = "select asistantdoctor from apm_ot_parent where apmtid = "+id+" ";
			preparedStatement = connection.prepareStatement(sql);
			StringBuffer str = new StringBuffer();
			ResultSet rs1 = preparedStatement.executeQuery();
			while(rs1.next()){
				str.append(rs1.getString(1) + ",");
			}
			
			if(str.length()!=0){
				result = str.toString() + n.getSurgeon() + "," + n.getAnesthesia();
			}else{
				result =  n.getSurgeon() + "," + n.getAnesthesia();
			}
			
		}catch (Exception e) {
			// TODO: handle exception
		}
		
		
				
		return result;
	}

	public int updateOtmsgStatus(String id,int loginid) {
		PreparedStatement preparedStatement = null;
		int result = 0;
		String sql = "update apm_available_slot set otmsg=1,otaccid="+loginid+" where id="+id+" ";
		
		try{
			preparedStatement = connection.prepareStatement(sql);
			result = preparedStatement.executeUpdate();
			
		}catch (Exception e) {
			// TODO: handle exception
		}
		return result;
	}

	public int getOpdIDFromInvoiceId(int invoiceid) {
		PreparedStatement preparedStatement = null;

		int result = 0;
		StringBuffer sql = new StringBuffer();
		
		sql.append("SELECT appointmentid FROM apm_charges_assesment inner join apm_invoice on ");
		sql.append("apm_invoice.id = apm_charges_assesment.invoiceid where chargeinvoiceid = "+invoiceid+" ");
		sql.append("order by apm_charges_assesment.id desc limit 0,1 ");
		
		try{
			preparedStatement = connection.prepareStatement(sql.toString());
			ResultSet rs = preparedStatement.executeQuery();
			if(rs.next()){
				result = rs.getInt(1);
			}
			
			
		}catch (Exception e) {
			// TODO: handle exception
		}
		
		return result;
	}

	public boolean checkChargeStatus(String string) {
		boolean flag = false;
		try {
			String sql ="select id from apm_charges_assesment where invoiceid in ("+string+") and invoiceid!=0";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				flag = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}

	public int getInvoiceTypeDrId(int invoiceid) {
		int id=0;
		try {
			
			String sql="select practid FROM apm_charges_invoice where id="+invoiceid+"";
			PreparedStatement ps=connection.prepareStatement(sql);
			ResultSet rs=ps.executeQuery();
			while(rs.next()){
				
				id=rs.getInt(1);
			}
		
		} catch (Exception e) {

		   e.printStackTrace();
		}
		
		return id;
	}

	public String getPractitionerofInvoice(int invoiceid) {
		String res=null;
		try {
			
			String sql="SELECT practid from apm_charges_invoice where id="+invoiceid+" ";
			PreparedStatement ps=connection.prepareStatement(sql);
			ResultSet rs =ps.executeQuery();
			while(rs.next()){
				
				 res =rs.getString(1);
			}
			
		} catch (Exception e) {

			e.printStackTrace();
		}
		
		return res;
	}

	public int updatePerDicount(int invoiceid) {
		PreparedStatement preparedStatement = null;
		int result = 0;
		String sql = "update apm_charges_invoice set discount=0 where id="+invoiceid+" ";
		
		try{
			preparedStatement = connection.prepareStatement(sql);
			result = preparedStatement.executeUpdate();
			
		}catch (Exception e) {
			// TODO: handle exception
		}
		return result;
	}

	public int updateAmtDiscount(int invoiceid) {
		PreparedStatement preparedStatement = null;
		int result = 0;
		String sql = "update apm_charges_invoice set discamt=0 where id="+invoiceid+" ";
		
		try{
			preparedStatement = connection.prepareStatement(sql);
			result = preparedStatement.executeUpdate();
			
		}catch (Exception e) {
			// TODO: handle exception
		}
		return result;
		
	}

	public String getInvoiceTime(int invoiceid) {

		String res="";
		try {
			
			String sql="SELECT invoiceDate from apm_invoice_log where invoiceId="+invoiceid+"";
			PreparedStatement ps=connection.prepareStatement(sql);
			ResultSet rs=ps.executeQuery();
			while(rs.next()){
				
				 String dateTime= rs.getString(1);
				 res= dateTime.split(" ")[1];
			}
			
		} catch (Exception e) {

			e.printStackTrace();
		}
		
		return res;
	}

	

	public Vector<Accounts> getRefundPreviewChargesList(int invoiceid) {
		//invoiceid--;
				PreparedStatement preparedStatement = null;
				DateTimeUtils dateTimeUtils = new DateTimeUtils();
				Vector<Accounts>list = new Vector<Accounts>();
				//String sql = "SELECT apmtType,charge,practitionerName,practitionerId,paybuy FROM apm_invoice_assesments where invoiceid = "+invoiceid+" ";
				
				StringBuffer sql = new StringBuffer();
				//sql.append("SELECT apmtType,charge,quantity,commencing FROM apm_credit_account_assesments where invoiceid = "+invoiceid+" and advref='1' ");		
				//sql.append("SELECT apmtType,charge,quantity,commencing,advref,refinvoiceid,debit,balance FROM apm_credit_account_assesments where invoiceid = "+invoiceid+" ");		
				sql.append("SELECT apmtType,apm_credit_account_assesments.charge,quantity,commencing,apm_credit_account_assesments.advref,refinvoiceid,debit,balance,manualinvoiceid ");
				sql.append("FROM apm_credit_account_assesments ");
				sql.append("inner join apm_credit_account on apm_credit_account_assesments.invoiceid = apm_credit_account.id ");
				sql.append("where apm_credit_account_assesments.invoiceid = '"+invoiceid+"' ");
				try{
					preparedStatement = connection.prepareStatement(sql.toString());
					ResultSet rs = preparedStatement.executeQuery();
					while(rs.next()){
						
						String apptName = rs.getString(1);
						String charge = rs.getString(2);
						if(charge==null){
							charge = "0.0";
						}
						
						int quantity = rs.getInt(3);
						String date = rs.getString(4);
						
						Accounts accounts = new Accounts();
						String invoicedate = date;
						accounts.setCommencing(DateTimeUtils.getInvoiceCommencingDate(invoicedate));
						accounts.setCharges(dateTimeUtils.changeFormat(Double.parseDouble(charge)));
						accounts.setChargeTotal(dateTimeUtils.changeFormat(Double.parseDouble(charge)*quantity));
						accounts.setQuantity(quantity);
						accounts.setAdvref(rs.getInt(5));
						//accounts.setAppointmentType(apptName);
						if(rs.getInt(5)>0){
							if(rs.getString(9)==null){
								accounts.setAppointmentType("Refund");
							}else{
								if(rs.getString(9).equals("")){
									accounts.setAppointmentType("Refund");
								}else{
									accounts.setAppointmentType("Refund"+" (Invoice Id: "+rs.getString(9)+")");
								}
							}
							
						}else{
							if(rs.getInt(6)>0){
								accounts.setAppointmentType("Refund");
							}else{
								accounts.setAppointmentType("Pre-Payment");
							}
							
						}
						
						list.add(accounts);
					}
					
				}catch(Exception e){
					e.printStackTrace();
				}
				return list;
	}

	public Accounts getInvoicePaymentData(String invoiceid, String pid) {
		PreparedStatement preparedStatement = null;
		Accounts accounts = new Accounts();
		String sql = "SELECT id, clientid, chargeinvoiceid, payment, paymode, date, tpid, deliver_status, paymentnote, crdinvoiceid, chequeno, userid, prepaymentid, bankname FROM apm_charges_payment where id = "+pid+" ";
		
		try{
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			if(rs.next()){
				accounts.setId(rs.getInt(1));
				accounts.setClientid(rs.getInt(2));
				accounts.setDate(rs.getString(6));
				accounts.setAmount(rs.getDouble(4));
				accounts.setPaymentmode(rs.getString(5));
				accounts.setNotes(rs.getString(9));
				accounts.setUserid(rs.getString(12));
			}
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		return accounts;
	}

	public String getInvoiceLogTime(int invoiceid) {

		String res="";
		try {
			
			String sql="SELECT invoiceDate from apm_charges_invoice_log where invoiceChargeId="+invoiceid+"";
			PreparedStatement ps=connection.prepareStatement(sql);
			ResultSet rs=ps.executeQuery();
			while(rs.next()){
				
				 String dateTime= rs.getString(1);
				 res= dateTime.split(" ")[1];
			}
			
		} catch (Exception e) {

			e.printStackTrace();
		}
		
		return res;
	}

	public ArrayList<Accounts> getRefundList(int invoiceid) {
		ArrayList<Accounts> arrayList = new ArrayList<Accounts>();
		try {
			StringBuffer stringBuffer = new StringBuffer();
			/*stringBuffer.append("select refund_request_parent.id,given_date,sum(charge*quantity) from refund_request_parent ");
			stringBuffer.append("inner join refund_request_child on refund_request_child.parentid = refund_request_parent.id ");
			stringBuffer.append("where manualinvoiceid='"+invoiceid+"' and status=2 group by refund_request_parent.id ");*/
			
			stringBuffer.append("select refund_request_parent.id,given_date,sum(refund_request_child.charge*quantity),apm_credit_account.id from refund_request_parent ");
			stringBuffer.append("inner join refund_request_child on refund_request_child.parentid = refund_request_parent.id ");
			stringBuffer.append("inner join apm_credit_account on apm_credit_account.refundrequestid = refund_request_parent.id ");
			stringBuffer.append("where refund_request_parent.manualinvoiceid='"+invoiceid+"' and status=2 group by refund_request_parent.id ");
			
			PreparedStatement preparedStatement = connection.prepareStatement(stringBuffer.toString());
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				Accounts accounts = new Accounts();
				accounts.setId(rs.getInt(1));
				String reqdate = "";
				if(rs.getString(2)!=null){
					String[] data = rs.getString(2).split(" ");
					reqdate = DateTimeUtils.getCommencingDate1(data[0]) +" "+data[1];
				}
				accounts.setDate(reqdate);
				accounts.setAmountx(rs.getString(3));
				accounts.setInvoicee(rs.getString(4));
				arrayList.add(accounts);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return arrayList;
	}

	public ArrayList<Master> getLedgerServicesList(int invoiceid) {
		PreparedStatement preparedStatement = null;
		ArrayList<Master>list = new ArrayList<Master>();
		StringBuffer sql = new StringBuffer();
		
		/*
		 * sql.
		 * append("SELECT masterchargetype,sum(charge*quantity),apm_invoice_assesments.id FROM apm_charges_assesment inner join apm_charges_invoice on "
		 * );
		 * sql.append("apm_charges_assesment.chargeinvoiceid = apm_charges_invoice.id "
		 * ); sql.
		 * append("inner join apm_invoice_assesments on apm_charges_assesment.invoiceid = apm_invoice_assesments.invoiceid "
		 * ); sql.append("where apm_charges_invoice.id = "+invoiceid+" ");
		 * sql.append("group by masterchargetype ");
		 */ //@ rahul remove apm_charges_assesment table beacuse servieces name and charges repetaed for sram hosp
		
		sql.append("SELECT masterchargetype,sum(charge*quantity),apm_invoice_assesments.id FROM apm_charges_invoice inner join ");
		sql.append("apm_invoice_assesments on apm_invoice_assesments.invoiced = apm_charges_invoice.id where ");
		sql.append("apm_charges_invoice.id = "+invoiceid+" group by masterchargetype ");
		try{
			preparedStatement = connection.prepareStatement(sql.toString());
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()){
				Master master = new Master();
				master.setName(rs.getString(1));
				master.setCharge(rs.getString(2));
				master.setId(rs.getInt(3));
				
				list.add(master);
			}
			
		}catch (Exception e) {
			// TODO: handle exception
		}
		return list;
	}

	public ArrayList<Master> getcashdeskLedgerServicesList(String allChargeList, String clientId) {
		PreparedStatement preparedStatement = null;
		DateTimeUtils dateTimeUtils = new DateTimeUtils();
		ArrayList<Master>list = new ArrayList<Master>();
		StringBuffer sql = new StringBuffer();
		
		sql.append("SELECT masterchargetype,sum(charge*quantity),apm_invoice_assesments.id ");
		sql.append("FROM apm_invoice_assesments inner join apm_invoice on apm_invoice.id = apm_invoice_assesments.invoiceid ");
		sql.append("where apm_invoice_assesments.active=1 and invoiceid in("+allChargeList+") and apm_invoice.credit=0 and apm_invoice_assesments.clientid = "+clientId+" ");
		sql.append("group by masterchargetype  ");
		try{
			preparedStatement = connection.prepareStatement(sql.toString());
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()){
				Master master = new Master();
				master.setName(rs.getString(1));
				master.setCharge(rs.getString(2));
				master.setId(rs.getInt(3));
				
				list.add(master);
			}
			
		}catch (Exception e) {
			// TODO: handle exception
		}
		return list;
	}

	public Accounts getDiscInvoiceDetails(String invoiceId) {
		PreparedStatement preparedStatement = null;
		Accounts accounts = new Accounts();
		String sql = "select clientid,debit from apm_charges_invoice where id = "+invoiceId+" ";
		
		try{
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			if(rs.next()){
				accounts.setClientid(rs.getInt(1));
				accounts.setDebitAmount(rs.getDouble(2));
				
				
			}
			
			
		}catch (Exception e) {
			// TODO: handle exception
		}
		return accounts;
	}

	public ArrayList<Accounts> getDiscountGivenUserList() {
		PreparedStatement preparedStatement = null;
		ArrayList<Accounts>list = new ArrayList<Accounts>();
		String sql = "select id,initial,firstname,lastname,jobtitle from apm_user where usertype=4  and jobtitle!='Pharmacist' and jobtitle!='Reception' and jobtitle!='Nurse' and jobtitle!='RMO' and jobtitle!='OT' and jobtitle!='OT STAFF' or id='1'";
		
		try{
			
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()){
				Accounts diaryManagement = new Accounts();
				diaryManagement.setId(rs.getInt(1));
				if(rs.getString(2)==null || rs.getString(2).equals(""))
				{
					diaryManagement.setPractitionerName(rs.getString(3) + " " +rs.getString(4));

				}
				else{
					diaryManagement.setPractitionerName(rs.getString(2) + " " + rs.getString(3) + " " +rs.getString(4));

				}				
				list.add(diaryManagement);
				
			}
			
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		return list;
	}

	public Accounts getInvoiceDetails(String invoiceId) {
		PreparedStatement preparedStatement = null;
		Accounts accounts = new Accounts();
		String sql = "";
		StringBuffer buffer = new StringBuffer();
		buffer.append("select apm_charges_invoice.id, date, clientid, tpid, location,  ");
		buffer.append("invpstype, itype, resetinv, ");
		buffer.append("practid, ipdid,concat(title,' ',firstname,' ',surname),disc_request ");
		buffer.append("from apm_charges_invoice   ");
		buffer.append("inner join apm_patient on apm_patient.id = clientid ");
		buffer.append("where apm_charges_invoice.id = "+invoiceId+" ");
		try{
			preparedStatement = connection.prepareStatement(buffer.toString());
			ResultSet rs = preparedStatement.executeQuery();
			if(rs.next()){
				accounts.setId(rs.getInt(1));
				accounts.setDate(rs.getString(2));
				accounts.setClientid(rs.getInt(3));
				accounts.setTpid(rs.getInt(4));
				accounts.setLocation(rs.getString(5));
				accounts.setInvpstype(rs.getString(6));
				accounts.setItype(rs.getString(7));
				accounts.setResetinv(rs.getString(8));
				accounts.setPractitionerId(rs.getInt(9));
				accounts.setIpdid(rs.getString(10));
				accounts.setClientName(rs.getString(11));
				accounts.setDisc_request(""+rs.getInt(12));
				
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		return accounts;
	}

	public int updatePercentageAmountOfInvoice(String invoiceId, String discval, String disctype, String userid,
			String datetime) {
		PreparedStatement preparedStatement = null;
		int result = 0;
		String sql = " ";
		
		if(disctype.equals("0")){
			sql = "update apm_charges_invoice set discount=?,disctype=?,disc_given_userid=?,disc_given_date=?,disc_request=? where id="+invoiceId+" ";
		}else{
			sql = "update apm_charges_invoice set discamt=?,disctype=?,disc_given_userid=?,disc_given_date=?,disc_request=? where id="+invoiceId+" ";
		}
		
		try{
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, discval);
			preparedStatement.setString(2, disctype);
			preparedStatement.setString(3, userid);
			preparedStatement.setString(4, datetime);
			preparedStatement.setString(5, "3");
			result = preparedStatement.executeUpdate();
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public String getDiscApproveUserid(String invoiceId) {
		String name = "";
		try {
			String sql ="SELECT approved_userid FROM discount_request where invoiceid='"+invoiceId+"'";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				name=rs.getString(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return name;
	}

	public int getpaymentInvoiceID(String id) {
		PreparedStatement preparedStatement = null;
		int result = 0;
		String sql = "select chargeinvoiceid from apm_charges_payment where id = "+id+" ";
		
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
	public ArrayList<Master> getBillMasterAssessmentList(int invoiceid,String filterbydate){
		PreparedStatement preparedStatement = null;
		ArrayList<Master>list = new ArrayList<Master>();
		StringBuffer sql = new StringBuffer();
		boolean discstatus=false;
		
		sql.append("select masterchargetype from  apm_charges_invoice  inner join apm_charges_assesment on ");
		sql.append("apm_charges_assesment.chargeinvoiceid = apm_charges_invoice.id ");
		sql.append("inner join apm_invoice on apm_invoice.id = apm_charges_assesment.invoiceid ");
		sql.append("inner join apm_invoice_assesments on apm_invoice_assesments.invoiceid = apm_invoice.id ");
		sql.append("where apm_charges_invoice.id = "+invoiceid+" group by masterchargetype order by FIELD(masterchargetype,'Bed Charge','Ipd Registration Charge') desc,masterchargetype asc ");
		
		try{
			preparedStatement = connection.prepareStatement(sql.toString());
			ResultSet  rs = preparedStatement.executeQuery();
			while(rs.next()){
				Master master = new Master();
				master.setName(rs.getString(1));
				ArrayList<Master> wardwisechargelist = getBillSummaryWardList(invoiceid,master.getName(),filterbydate,rs.getString(1));
				//Vector<Accounts> assesmentList = getBillFilteredPreviewChargesList(invoiceid,master.getName(),filterbydate,rs.getInt(2));
				//master.setAssesmentList(assesmentList);
				double subtotal = getMasterSubtotal(invoiceid,master.getName(),0);
				master.setCharge(DateTimeUtils.changeFormat(subtotal));
				master.setWardwisechargelist(wardwisechargelist);
				Vector<Accounts> assesmentList=new Vector<Accounts>();
				
				int size1 = wardwisechargelist.size();
				{
					 discstatus=wardwisechargelist.get(size1-1).isDiscstatus();
					//assesmentList = wardwisechargelist.get(size1-1).getAssesmentList();
					 
				}
				
				for (Master master1 : wardwisechargelist) {
					assesmentList.addAll(master1.getAssesmentList());
				}
				
				master.setDiscstatus(discstatus);
				master.setAssesmentList(assesmentList);
				list.add(master);
			}
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		return list;
	}
	
	private ArrayList<Master> getBillSummaryWardList(int invoiceid, String name, String filterbydate, String mastercharge) {
		PreparedStatement preparedStatement = null;
		ArrayList<Master>list = new ArrayList<Master>();
		StringBuffer sql = new StringBuffer();
		 boolean discstatus=false;
		IpdDAO ipdDAO = new JDBCIpdDAO(connection);
				
		try{
			int i=0;
			int isbedcharge=0;
			int isinvestigationcharge=0;
			if(mastercharge!=null){
				if(mastercharge.equals("Bed Charge")){
					isbedcharge=1;
				}else if(mastercharge.equals("INVESTIGATION")){
					isinvestigationcharge=1;
				}
			}
			//  18 Sep 2018 if master charge is bed charge then it go to if condition
			//And set i =1;
			// and if i=0 means there is no ward present so its work normally
			if(isbedcharge==1){
				sql.append("select masterchargetype,wardid from  apm_charges_invoice  inner join apm_charges_assesment on ");
				sql.append("apm_charges_assesment.chargeinvoiceid = apm_charges_invoice.id ");
				sql.append("inner join apm_invoice on apm_invoice.id = apm_charges_assesment.invoiceid ");
				sql.append("inner join apm_invoice_assesments on apm_invoice_assesments.invoiceid = apm_invoice.id ");
				sql.append("where apm_charges_invoice.id = "+invoiceid+" and masterchargetype='"+mastercharge+"' group by wardid ");
				preparedStatement = connection.prepareStatement(sql.toString());
				ResultSet  rs = preparedStatement.executeQuery();
				while(rs.next()){
					i=1;
					Master master = new Master();
					master.setName(rs.getString(1));
					String wardname=ipdDAO.getIpdWardName(rs.getString(2));
					Vector<Accounts> assesmentList = getBillFilteredPreviewChargesList(invoiceid,master.getName(),filterbydate,1,rs.getString(2));
					master.setAssesmentList(assesmentList);
					master.setWardname(wardname);
					double subtotal = getMasterSubtotal(invoiceid,master.getName(),rs.getInt(2));
					master.setCharge(DateTimeUtils.changeFormat(subtotal));
					master.setIsbedcharge(1);
					int size1 = assesmentList.size();
					{
						 discstatus=assesmentList.get(size1-1).isDiscstatus();
						 
					}
					master.setDiscstatus(discstatus);
					list.add(master);
				}
			// had to remove making bill summary very slow 27/11/2018	
			}else if(false){
				sql.append("  select distinct (apm_investigation_type.department) from  apm_charges_invoice ");
				sql.append("  inner join apm_charges_assesment on apm_charges_assesment.chargeinvoiceid = apm_charges_invoice.id ");
				sql.append("  inner join apm_invoice on apm_invoice.id = apm_charges_assesment.invoiceid ");
				sql.append("  inner join apm_invoice_assesments on apm_invoice_assesments.invoiceid = apm_invoice.id ");
				sql.append("  left join apm_investigation_type on apm_investigation_type.name=apm_invoice_assesments.apmtType ");
				sql.append("  where apm_charges_invoice.id = "+invoiceid+" and masterchargetype='"+mastercharge+"'  ");
				
				preparedStatement=connection.prepareStatement(sql.toString());
				ResultSet rs= preparedStatement.executeQuery();
				while(rs.next()){
					i=1;
					Master master = new Master();
					master.setName(mastercharge);
					Vector<Accounts> assesmentList=new Vector<Accounts>();
					
					if(rs.getInt(1)==5){
						master.setWardname("Pathology");
					}else if(rs.getInt(1)==7){
						master.setWardname("Radiology");
					}else{
						master.setWardname("Other");
					}
					master.setIsbedcharge(2);
					assesmentList= getAssmentOfInvestigationDeptWise(String.valueOf(invoiceid), rs.getInt(1), mastercharge);
					master.setAssesmentList(assesmentList);
					double subtotal=getMasterSubtotal(invoiceid, mastercharge, 0);
					master.setCharge(DateTimeUtils.changeFormat(subtotal));
					int size1 = assesmentList.size();
					{
						 discstatus=assesmentList.get(size1-1).isDiscstatus();
						 
					}
					master.setDiscstatus(discstatus);
					list.add(master);
				}
				
				
				
			}
			if(i==0){
				Master master = new Master();
				master.setName(mastercharge);
				String wardname="";
				Vector<Accounts> assesmentList = getBillFilteredPreviewChargesList(invoiceid,master.getName(),filterbydate,0,"");
				master.setAssesmentList(assesmentList);
				master.setWardname(wardname);
				double subtotal = getMasterSubtotal(invoiceid,master.getName(),0);
				master.setCharge(DateTimeUtils.changeFormat(subtotal));
				master.setIsbedcharge(0);
				int size1 = assesmentList.size();
				{
					 discstatus=assesmentList.get(size1-1).isDiscstatus();
					 
				}
				master.setDiscstatus(discstatus);
				list.add(master);
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	public Vector<Accounts> getBillFilteredPreviewChargesList(int invoiceid,String masterchargetype, String filterbydate, int wardid, String string) {
		PreparedStatement preparedStatement = null;
		DateTimeUtils dateTimeUtils = new DateTimeUtils();
		Vector<Accounts>list = new Vector<Accounts>();
		boolean discstatus=false;
		//String sql = "SELECT apmtType,charge,practitionerName,practitionerId,paybuy FROM apm_invoice_assesments where invoiceid = "+invoiceid+" ";
		BedDao bedDao = new JDBCBedDao(connection);
		IpdDAO ipdDAO = new JDBCIpdDAO(connection);
		ThirdPartyDAO thirdPartyDAO = new JDBCThirdPartyDAO(connection);
		StringBuffer sql = new StringBuffer();
		/*
		 * sql.
		 * append("SELECT apmtType,charge,apm_invoice.practitionerName,apm_invoice.practitionerId,paybuy,apmt_charge_type,apm_invoice.id,apm_invoice_assesments.commencing,apm_invoice.appointmentid,sum(apm_invoice_assesments.quantity),apm_invoice_assesments.id,apm_invoice_assesments.chargeid,apm_invoice_assesments.std_charge_id,apm_invoice_assesments.ipdid,apm_invoice.ginvstid,unitcharge,chargedisc "
		 * ); sql.append("FROM apm_invoice inner join apm_invoice_assesments on "); sql.
		 * append("apm_invoice_assesments.invoiceid = apm_invoice.id inner join apm_charges_assesment "
		 * ); sql.append("on apm_charges_assesment.invoiceid = apm_invoice.id ");
		 * sql.append("where apm_charges_assesment.chargeinvoiceid="
		 * +invoiceid+" and masterchargetype = '"+masterchargetype+"'  ");
		 */
		
		sql.append("SELECT apmtType,charge,apm_invoice.practitionerName,apm_invoice.practitionerId,paybuy,apmt_charge_type,apm_invoice.id,apm_invoice_assesments.commencing,apm_invoice.appointmentid,sum(apm_invoice_assesments.quantity),apm_invoice_assesments.id,apm_invoice_assesments.chargeid,apm_invoice_assesments.std_charge_id,apm_invoice_assesments.ipdid,apm_invoice.ginvstid,unitcharge,chargedisc ");
		sql.append("FROM apm_invoice inner join apm_invoice_assesments on ");
		sql.append("apm_invoice_assesments.invoiceid = apm_invoice.id  ");
		sql.append("where apm_invoice_assesments.invoiced="+invoiceid+" and masterchargetype = '"+masterchargetype+"'  ");
		if(wardid!=0){
			if(string==null){
				sql.append("and apm_invoice_assesments.wardid is null ");
			}else{
				sql.append("and apm_invoice_assesments.wardid='"+string+"' ");
			}
			
		}
		
		sql.append("group by apmtType,charge+0.0 ");
		
		if(filterbydate.equals("1")){
			sql.append("order by apm_invoice_assesments.commencing ");
		}else{
			sql.append("order by apmtType ");
		}
		
		
		try{
			preparedStatement = connection.prepareStatement(sql.toString());
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()){
				String tpid="";
				String apptName = rs.getString(1);
				String  sectionroom = "";
				ThirdParty thirdParty =new ThirdParty();
				if(masterchargetype.equals("INVESTIGATION")){
					 sectionroom = getSectionRoom(apptName);
					if(apptName.contains("~")){
						String tmp[] = apptName.split("~");
						apptName = tmp[0];
					}
					
					//set investigation alternate name
					tpid = getInvoiceTpId(invoiceid);
				
					 thirdParty = thirdPartyDAO.getThirdPartyDetails(tpid);
					if(thirdParty.isMaintp()){
						String alternamename = getInvestigationAltName(rs.getString(1));
						if(alternamename !=null){
							if(!alternamename.equals("")){
								apptName = alternamename;
							}
						}
					}
					if(sectionroom==null){
						
					}
					else if(!sectionroom.equals("")){
						apptName = apptName + " (Room Nos : "+sectionroom+")";
					}
					
					
				}
				String charge ="";
				if(rs.getDouble(17)>0){
					charge= rs.getString(16);
				}else{
				charge= rs.getString(2);
				}
				if(charge==null){
					charge = "0.0";
				}
				String practitionerName = rs.getString(3);
				String practitionerid = rs.getString(4);
				int payby = rs.getInt(5);
				int addtitonalCharge = rs.getInt(6);
				String chargeid = rs.getString(7);
				String date = rs.getString(8);
				int apptid = rs.getInt(9);
				int quantity = rs.getInt(10);
				int subchargeid = rs.getInt(11);
				int apmtchargeid = rs.getInt(12);;
				
				
				Accounts accounts = new Accounts();
				
				if(addtitonalCharge==0){
					Accounts acc = getAppointmentDetailsl(chargeid);
					if(acc.getCommencing()!=null){
						accounts.setCommencing(DateTimeUtils.getInvoiceCommencingDate(acc.getCommencing()));
						accounts.setAppointmentType(acc.getTreatmentEpisodeName() + " " +apptName);
					}
					
					
					boolean dna = getDna(apptid);
					accounts.setDna(dna);
					
				}else{
					String invoicedate = date;
					accounts.setCommencing(DateTimeUtils.getInvoiceCommencingDate(invoicedate));
					accounts.setAppointmentType("Additional Charge("+apptName+")");
				}
				
				accounts.setCharges(dateTimeUtils.changeFormat(Double.parseDouble(charge)));
				accounts.setChargeTotal(dateTimeUtils.changeFormat(Double.parseDouble(charge)*quantity));
				accounts.setPractitionerName(practitionerName);
				String practitionerFullName = getPractitionerFullName(practitionerid);
				accounts.setPractitionerName(practitionerFullName);
				accounts.setQuantity(quantity);
				accounts.setSubchargeid(subchargeid);
				
				boolean isSTdCharge =getIfSTdCharge(rs.getString(13));
				if(isSTdCharge){
					/*String startEndTime= getStdChargeStartEndTime(subchargeid,rs.getString(14));
					
					
					if(startEndTime!=null){
						String tempc[] = startEndTime.split("~");
						String t1[] = tempc[0].split(",");
						String t2[] = tempc[1].split(",");
						
						String resultdatetime = "";
						for(int i=0;i<t2.length;i++){
							resultdatetime = resultdatetime + t1[i] + " - " + t2[i] + " , "; 
						}
						
						if(resultdatetime.length()!=0){
							resultdatetime = resultdatetime.substring(0,resultdatetime.length()-1);
						}
						int curstatus = getstdonoffcurstatus(subchargeid,rs.getString(14));
						if(curstatus==1){
							resultdatetime = resultdatetime + " , " + t1[t1.length-1];
						}
						
						accounts.setAppointmentType(apptName+" ("+resultdatetime+") ");
					} else {
						accounts.setAppointmentType(apptName);
					}*/
					//  05-08-2019 commented above code as per dipanjay sir requirement
					accounts.setAppointmentType(apptName);
				}else {
					accounts.setAppointmentType(apptName);
					/*int thirdpartyid=Integer.parseInt(tpid);
					if(thirdpartyid!=0 && masterchargetype.equals("INVESTIGATION")){
						 String apptName1=gettpservicename(tpid,thirdParty.getShortname(),apptName);
					
					
					}*/
				}
				
				accounts.setPayBy(payby);
				
				
				String tpcode = getTpChargeCode(apmtchargeid);
				accounts.setTpcode(tpcode);
				
				/*if(payby==0){
					accounts.setTpcode(" ");
				}*/
				
				//  22 dec 2017 hide package charges
				int ginvstid = checkIsInvestigationPackage(rs.getString(15));
				accounts.setPkginvissid(String.valueOf(ginvstid));
				
				double unitcharge = rs.getDouble(16);
				double chargedisc = rs.getDouble(17);
				
				if(unitcharge==0){
					unitcharge = rs.getDouble(2);
				}
				accounts.setUnitcharge(DateTimeUtils.changeFormat(unitcharge));
				accounts.setChargedisc(DateTimeUtils.changeFormat(chargedisc));
				
				accounts.setIpdid(""+rs.getInt(14));
				
				//  03 July 2018 
				//Bed bed = getBedLogData(""+rs.getInt(14));
				//Bed bed = getBedLogData(""+rs.getInt(14));
				Bed bed = bedDao.getEditIpdData(""+rs.getInt(14));
				String wardname=ipdDAO.getIpdWardName(bed.getWardid());
				String bedname = ipdDAO.getIpdBedName(bed.getBedid());
				accounts.setWard(wardname + " / " + bedname);
				
//				if(chargedisc>0){
					discstatus=true;
//				}
				accounts.setDiscstatus(true);



				
				list.add(accounts);
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
		return list;
	}

	private String gettpservicename(String tpid, String shortname, String apptName) {
		PreparedStatement pst=null;
		String result="";
		try {
           String sql="SELECT "+shortname+" FROM apm_investigation_type where name='"+apptName+"'";
           pst=connection.prepareStatement(sql);
           ResultSet rs=pst.executeQuery();
           while(rs.next()){
        	   result=rs.getString(1);
           }
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}

	public int getMaxInvoiceTypePaymentNo(int re, String invcetype) {
		PreparedStatement preparedStatement = null;
		int result = 0;
		String cname = "";
		if(invcetype.equals("1")){
			cname = "opdno";
		}
		if(invcetype.equals("2")){
			cname = "ipdno";
		}
		if(invcetype.equals("3")){
			cname = "invno";
		}
		if(invcetype.equals("5")){
			cname = "advno";
		}
		
		
		String sql = "select max("+cname+") from apm_charges_payment ";
		try{
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			if(rs.next()){
				result = rs.getInt(1);
				result++;
			}
		}catch (Exception e) {
			// TODO: handle exception
		}
		return result;
	}

	public int updateInvoicetypePaymentNo(int id, int maxno, String invcetype) {
		PreparedStatement preparedStatement = null;
		int result = 0;
		String cname = "";
		if(invcetype.equals("1")){
			cname = "opdno";
		}
		if(invcetype.equals("2")){
			cname = "ipdno";
		}
		if(invcetype.equals("3")){
			cname = "invno";
		}
		if(invcetype.equals("5")){
			cname = "advno";
		}
		
		int year = Calendar.getInstance().get(Calendar.YEAR);
		String sql = "update apm_charges_payment set "+cname+"="+maxno+",cyear="+year+" where id = "+id+" ";
		
		try{
			preparedStatement = connection.prepareStatement(sql);
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

	public double checkParkingChargeApplied(int invoiceid) {
		double res=0;
		try {
			StringBuffer buffer = new StringBuffer();
			buffer.append("select sum(apm_invoice_assesments.charge) from apm_charges_invoice ");
			buffer.append("inner join apm_charges_assesment on apm_charges_assesment.chargeinvoiceid = apm_charges_invoice.id ");
			buffer.append("inner join apm_invoice on apm_invoice.id = apm_charges_assesment.invoiceid ");
			buffer.append("inner join apm_invoice_assesments on apm_invoice_assesments.invoiceid = apm_invoice.id ");
			buffer.append("where apm_charges_invoice.id ='"+invoiceid+"' and apm_invoice_assesments.apmtType='Parking charge' and isparkingcharge='0' ");
			PreparedStatement preparedStatement = connection.prepareStatement(buffer.toString());
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				res = rs.getDouble(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return res;
	}

	public int updateDiscountWithParking(int invoiceid, double discamt) {
		PreparedStatement preparedStatement = null;
		int result = 0;
		try{
			String sql = "update apm_charges_invoice set discamt='"+discamt+"',isparkingcharge='1',disctype='1' where id="+invoiceid+" ";
			preparedStatement = connection.prepareStatement(sql);
			result = preparedStatement.executeUpdate();
		}catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public int checkIsParking(int invoiceid) {
		int res = 0;
		try {
			String sql="SELECT isparkingcharge from apm_charges_invoice where id="+invoiceid+"";
			PreparedStatement ps=connection.prepareStatement(sql);
			ResultSet rs=ps.executeQuery();
			while(rs.next()) {
				res = rs.getInt(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return res;
	}

	public int getMaxIpdseqNo(String a_year) {
		int res =0;
		try {
			String sql="select max(ipdseqno) from apm_charges_invoice";
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

	public int getMaxVaccinationseqNo(String a_year) {
		int res =0;
		try {
			String sql="select max(vaccinationseqno) from apm_charges_invoice";
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
	
	
	public int getMaxOpdseqNo(String a_year) {
		int res =0;
		try {
			String sql="select max(opdseqno) from apm_charges_invoice";
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
//lokesh 12-9-2018
	public int updateInvoiceSeqNo(String string, int res, int invoiceid, String a_year) {
		PreparedStatement preparedStatement = null;
		int result = 0;
		try{
			String sql ="";
			if(string.equals("1")||string.equals("0")||string.equals("8")){
				sql = "update apm_charges_invoice set opdseqno='"+res+"',a_year='"+a_year+"' where id="+invoiceid+" ";
			}else if(string.equals("2")){
				sql = "update apm_charges_invoice set ipdseqno='"+res+"',a_year='"+a_year+"' where id="+invoiceid+" ";
			}else if(string.equals("3")){
				sql = "update apm_charges_invoice set invstseqno='"+res+"',a_year='"+a_year+"' where id="+invoiceid+" ";
			}else if(string.equals("4")){
				sql = "update apm_charges_invoice set medseqno='"+res+"',a_year='"+a_year+"' where id="+invoiceid+" ";
			}else if(string.equals("5")){
				sql = "update apm_charges_invoice set advnrefseqno='"+res+"',a_year='"+a_year+"' where id="+invoiceid+" ";
			}else if(string.equals("6")){
				sql = "update apm_charges_invoice set vaccinationseqno='"+res+"',a_year='"+a_year+"' where id="+invoiceid+" ";
			}
			
			
			preparedStatement = connection.prepareStatement(sql);
			result = preparedStatement.executeUpdate();
		}catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public int getIpdOpdSeqNo(int invoiceid) {
		PreparedStatement ps= null;
		int res=0;
		String sql="select itype,ipdseqno,opdseqno,invstseqno,medseqno,advnrefseqno,vaccinationseqno from apm_charges_invoice where id='"+invoiceid+"'";
		try {
			ps= connection.prepareStatement(sql);
			ResultSet rs= ps.executeQuery();
			while(rs.next()){
				if((rs.getInt(1)==1)||(rs.getInt(1)==0)||(rs.getInt(1)==8)){
					res=rs.getInt(3);
				}else if(rs.getInt(1)==2){
					res= rs.getInt(2);
				}else if(rs.getInt(1)==3){
					res= rs.getInt(4);
				}else if(rs.getInt(1)==4){
					res= rs.getInt(5);
				}else if(rs.getInt(1)==5){
					res= rs.getInt(6);
				}else if(rs.getInt(1)==6){
					res= rs.getInt(7);
				}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return res;
	}

	public int getMaxInvstseqNo(String a_year) {
		int res =0;
		try {
			String sql="select max(invstseqno) from apm_charges_invoice";
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

	public int getMaxMedseqNo(String a_year) {
		int res =0;
		try {
			String sql="select max(medseqno) from apm_charges_invoice";
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

	public int getMaxAdvnRefseqNo(String a_year) {
		int res =0;
		try {
			String sql="select max(advnrefseqno) from apm_charges_invoice";
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

	public String getIpdOpdSeqNoWithType(int invoiceid) {
		String result="";
		PreparedStatement ps= null;
		try {
			String sql=" select itype,ipdseqno,opdseqno,invstseqno,medseqno,advnrefseqno,vaccinationseqno,f_seq_no from apm_charges_invoice where id='"+invoiceid+"' ";
			ps= connection.prepareStatement(sql);
			ResultSet rs= ps.executeQuery();
			while(rs.next()){
				if(("1").equals(rs.getString(1))){
					result =rs.getString(3)+ "(OPD) ";
				}else if(("2").equals(rs.getString(1))){
					result =rs.getString(2)+ "(IPD) ";
				}else if(("3").equals(rs.getString(1))){
					result =rs.getString(4)+ "(Investigation) ";
				}else if(("4").equals(rs.getString(1))){
					result =rs.getString(5)+ "(Medicine) ";
				}else if(("5").equals(rs.getString(1))){
					result =rs.getString(6)+ "(Adv & Ref) ";
				}else if(("6").equals(rs.getString(1))){
					result =rs.getString(7)+ "(Vaccination) ";
				}else if(("8").equals(rs.getString(1))){
					result =rs.getString(3)+ "(Daycare) ";
				}else if(("11").equals(rs.getString(1))){
					result = "(Dietary) ";
				}
				result=rs.getString(8);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	private Vector<Accounts> getAssmentOfInvestigationDeptWise(String invoiceid,int depttype,String mastercharge){
		Vector<Accounts> list= new Vector<Accounts>();
		PreparedStatement ps= null;
		try {
			boolean discstatus=false;
			//String sql = "SELECT apmtType,charge,practitionerName,practitionerId,paybuy FROM apm_invoice_assesments where invoiceid = "+invoiceid+" ";
			BedDao bedDao = new JDBCBedDao(connection);
			IpdDAO ipdDAO = new JDBCIpdDAO(connection);
			StringBuffer sql = new StringBuffer();
			sql.append("  SELECT apmtType,apm_invoice_assesments.charge,apm_invoice.practitionerName,apm_invoice.practitionerId,paybuy,apmt_charge_type,apm_invoice.id,apm_invoice_assesments.commencing,apm_invoice.appointmentid,sum(apm_invoice_assesments.quantity),apm_invoice_assesments.id,apm_invoice_assesments.chargeid,apm_invoice_assesments.std_charge_id,apm_invoice_assesments.ipdid,apm_invoice.ginvstid,unitcharge,chargedisc ");
			sql.append("  from apm_charges_invoice inner join apm_charges_assesment on apm_charges_assesment.chargeinvoiceid = apm_charges_invoice.id ");
			sql.append("  inner join apm_invoice on apm_invoice.id = apm_charges_assesment.invoiceid ");
			sql.append("  inner join apm_invoice_assesments on apm_invoice_assesments.invoiceid = apm_invoice.id ");
			sql.append("  left join apm_investigation_type on apm_investigation_type.name=apm_invoice_assesments.apmtType ");
			sql.append("  where apm_charges_invoice.id = "+invoiceid+" and masterchargetype='"+mastercharge+"' and apm_investigation_type.department='"+depttype+"' group by apmtType,apm_invoice_assesments.charge ");
			
			ps= connection.prepareStatement(sql.toString());
			ResultSet rs= ps.executeQuery();
			while(rs.next()){
				Accounts accounts= new Accounts();
				accounts.setAppointmentType(rs.getString(1));
				String apptName=rs.getString(1);
				
				String charge = rs.getString(2);
				if(charge==null){
					charge = "0.0";
				}
				String practitionerName = rs.getString(3);
				String practitionerid = rs.getString(4);
				int payby = rs.getInt(5);
				int addtitonalCharge = rs.getInt(6);
				String chargeid = rs.getString(7);
				String date = rs.getString(8);
				int apptid = rs.getInt(9);
				int quantity = rs.getInt(10);
				int subchargeid = rs.getInt(11);
				int apmtchargeid = rs.getInt(12);;
				
				
				if(addtitonalCharge==0){
					Accounts acc = getAppointmentDetailsl(chargeid);
					if(acc.getCommencing()!=null){
						accounts.setCommencing(DateTimeUtils.getInvoiceCommencingDate(acc.getCommencing()));
						accounts.setAppointmentType(acc.getTreatmentEpisodeName() + " " +apptName);
					}
					
					
					boolean dna = getDna(apptid);
					accounts.setDna(dna);
					
				}






				accounts.setCharges(DateTimeUtils.changeFormat(Double.parseDouble(charge)));
				accounts.setChargeTotal(DateTimeUtils.changeFormat(Double.parseDouble(charge)*quantity));
				accounts.setPractitionerName(practitionerName);
				String practitionerFullName = getPractitionerFullName(practitionerid);
				accounts.setPractitionerName(practitionerFullName);
				accounts.setQuantity(quantity);
				accounts.setSubchargeid(subchargeid);
				
				boolean isSTdCharge =getIfSTdCharge(rs.getString(13));
				if(isSTdCharge){
					String startEndTime= getStdChargeStartEndTime(subchargeid,rs.getString(14));
					
					
					if(startEndTime!=null){
						String tempc[] = startEndTime.split("~");
						String t1[] = tempc[0].split(",");
						String t2[] = tempc[1].split(",");
						
						String resultdatetime = "";
						for(int i=0;i<t2.length;i++){
							resultdatetime = resultdatetime + t1[i] + " - " + t2[i] + " , "; 
						}
						
						if(resultdatetime.length()!=0){
							resultdatetime = resultdatetime.substring(0,resultdatetime.length()-1);
						}
						int curstatus = getstdonoffcurstatus(subchargeid,rs.getString(14));
						if(curstatus==1){
							resultdatetime = resultdatetime + " , " + t1[t1.length-1];
						}
						
						accounts.setAppointmentType(apptName+" ("+resultdatetime+") ");
					} else {
						accounts.setAppointmentType(apptName);
					}
					
				}else {
					accounts.setAppointmentType(apptName);
				}
				
				accounts.setPayBy(payby);
				
				
				String tpcode = getTpChargeCode(apmtchargeid);
				accounts.setTpcode(tpcode);
				
				if(payby==0){
					accounts.setTpcode(" ");
				}
				
				//  22 dec 2017 hide package charges
				int ginvstid = checkIsInvestigationPackage(rs.getString(15));
				accounts.setPkginvissid(String.valueOf(ginvstid));
				
				double unitcharge = rs.getDouble(16);
				double chargedisc = rs.getDouble(17);
				
				if(unitcharge==0){
					unitcharge = rs.getDouble(2);
				}
				accounts.setUnitcharge(DateTimeUtils.changeFormat(unitcharge));
				accounts.setChargedisc(DateTimeUtils.changeFormat(chargedisc));
				
				accounts.setIpdid(""+rs.getInt(14));
				
				//  03 July 2018 
				//Bed bed = getBedLogData(""+rs.getInt(14));
				//Bed bed = getBedLogData(""+rs.getInt(14));
				Bed bed = bedDao.getEditIpdData(""+rs.getInt(14));
				String wardname=ipdDAO.getIpdWardName(bed.getWardid());
				String bedname = ipdDAO.getIpdBedName(bed.getBedid());
				accounts.setWard(wardname + " / " + bedname);
				
//				if(chargedisc>0){
					discstatus=true;
//				}
				accounts.setDiscstatus(discstatus);



				
				list.add(accounts);
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	public String getAdvanceRefrundSerialNo(String reciptno) {
		String result="";
		PreparedStatement ps= null;
		try {
			String sql=" select sno from his_invoice_payment_sno where invoiceid='"+reciptno+"'";
			ps= connection.prepareStatement(sql);
			ResultSet rs= ps.executeQuery();
			while(rs.next()){
				result= rs.getString(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public int getpractIdFromInvoice(String invid) {
		int res=0;
		PreparedStatement ps= null;
		try {
			String sql="select practid from apm_charges_invoice where id='"+invid+"'";
			ps= connection.prepareStatement(sql);
			ResultSet rs= ps.executeQuery();
			while(rs.next()){
				res= rs.getInt(1);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return res;
	}

	public String gettypenamebyid(String thirdPartyType) {
		String res="";
		PreparedStatement ps= null;
		try {
			String sql="select apm_third_party_details.Shortname from apm_third_party_details inner join apm_patient on apm_patient.third_party_name_id=apm_third_party_details.id where apm_patient.third_party_name_id="+thirdPartyType+" group by type";
			ps= connection.prepareStatement(sql);
			ResultSet rs= ps.executeQuery();
			while(rs.next()){
				res= rs.getString(1);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return res;
	}

	public Client getTPClientData(String clientId) {
		PreparedStatement preparedStatement = null;
		Client client = new Client();
		String sql ="SELECT whopay,companyname,neiscardno,designation,relationofuser,unit_station,claimid,colliery,areatp,third_party_name_id,policyholder FROM apm_patient where id= "+clientId+" ";
		
		try{
			
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			if(rs.next()){
			client.setWhopay(rs.getString(1));
			client.setCompname(rs.getString(2));
			client.setNeisno(rs.getString(3));
			client.setDesignationbytp(rs.getString(4));
			client.setRelationvbytpe(rs.getString(5));
			client.setUnitstation(rs.getString(6));
			client.setClaimbytp(rs.getString(7));
			client.setColliery(rs.getString(8));
			client.setAreabytp(rs.getString(9));
			client.setThirdPartyType(rs.getString(10));
			client.setPolicyholder(rs.getString(11));
		}
	}catch(Exception e){
		e.printStackTrace();
	}
	return client;
}

	public String gettpnamebyid(String thirdPartyType) {
		String res="";
		PreparedStatement ps= null;
		try {
			String sql="select apm_third_party_details.type from apm_third_party_details inner join apm_patient on apm_patient.third_party_name_id=apm_third_party_details.id where apm_patient.third_party_name_id="+thirdPartyType+" group by type";
			ps= connection.prepareStatement(sql);
			ResultSet rs= ps.executeQuery();
			while(rs.next()){
				res= rs.getString(1);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return res;
	}

	public String getPkgStr(int invoiceid) {
		PreparedStatement preparedStatement = null;
		String result = "";
		StringBuffer sql = new StringBuffer();
		
		sql.append("select tpkg from apm_charges_invoice inner join apm_charges_assesment on ");
		sql.append("apm_charges_assesment.chargeinvoiceid = apm_charges_invoice.id inner join apm_invoice on ");
		sql.append("apm_invoice.id = apm_charges_assesment.invoiceid inner join apm_invoice_assesments on ");
		sql.append("apm_invoice_assesments.invoiceid = apm_invoice.id ");
		sql.append("where apm_charges_invoice.id = "+invoiceid+" and tpkg !=0 group by tpkg ");
		
		try{
			preparedStatement = connection.prepareStatement(sql.toString());
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()){
				result = result + rs.getString(1) + ",";
			}
			if(!result.equals("")){
				result = result.substring(0, result.length()-1);
			}
			
		}catch (Exception e) {
			// TODO: handle exception
		}
		return result;
	}
	
	
	public ArrayList<Master> getPkgMasterAssessmentList(String pkgstr,int invoiceid){
		PreparedStatement preparedStatement = null;
		boolean discstatus=false;
		ArrayList<Master>list = new ArrayList<Master>();
		StringBuffer sql = new StringBuffer();
		
		sql.append("select masterchargetype,sum(charge*quantity) from  tp_invoice_assesments where tpkg in("+pkgstr+")  ");
		sql.append(" group by masterchargetype order by sqno ");
		
		
		try{
			preparedStatement = connection.prepareStatement(sql.toString());
			ResultSet  rs = preparedStatement.executeQuery();
			while(rs.next()){
				Master master = new Master();
				master.setName(rs.getString(1));
					String breakage =  getMasterBreakage(master.getName());
				
				master.setKrackage(breakage);;
				Vector<Accounts> assesmentList = getPkgFilteredPreviewChargesList(pkgstr,master.getName(),invoiceid);
				master.setAssesmentList(assesmentList);
				
				double subtotal = 0;
				master.setCharge(DateTimeUtils.changeFormat(subtotal));
				int size1 = assesmentList.size();
				{
					 discstatus=assesmentList.get(size1-1).isDiscstatus();
					 
				}
				master.setDiscstatus(discstatus);
						if(breakage.equals("1")){
							master.setMedpkgamt(rs.getDouble(2));
						}
				list.add(master);
			}
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		return list;
	}

	private Vector<Accounts> getPkgFilteredPreviewChargesList(String pkgstr, String name,int invoiceid) {
		PreparedStatement preparedStatement = null;
		Vector<Accounts>list = new Vector<Accounts>();
		String sql = "select apmtType,charge,sum(quantity),chargeid,chargedisc,commencing,std_charge_id,tpkg "
				+ " from tp_invoice_assesments where tpkg in("+pkgstr+") "
						+ " and masterchargetype = '"+name+"' group by apmtType";
		ThirdPartyDAO thirdPartyDAO = new JDBCThirdPartyDAO(connection);
		try{
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()){
				Accounts a = new Accounts();
				String apptName = rs.getString(1);
				String  sectionroom = "";
				if(name.equals("Pathology") || name
						.equals("Radiology")){

					 sectionroom = getSectionRoom(apptName);
					if(apptName.contains("~")){
						String tmp[] = apptName.split("~");
						apptName = tmp[0];
					}
					
					//set investigation alternate name
					String tpid = getInvoiceTpId(invoiceid);
					ThirdParty thirdParty = thirdPartyDAO.getThirdPartyDetails(tpid);
					
						String alternamename = getInvestigationAltName(rs.getString(1));
						if(alternamename !=null){
							if(!alternamename.equals("")){
								apptName = alternamename;
							}
						}
						
					}

		

					

				a.setAppointmentType(apptName);
				a.setUnitcharge(String.format("%.2f", rs.getDouble(2)));
				a.setQuantity(rs.getInt(3));
				
				/*
				String tpcode = getTpChargeCode(rs.getInt(4));
				a.setTpcode(tpcode);
				
				if(a.getTpcode()==null){
					a.setTpcode("0");
				}
				if(a.getTpcode().equals("")){
					a.setTpcode("No Code");
				}
				if(a.getTpcode().equals("0")){
					a.setTpcode("No Code");
				}*/
				String codeforapt=getcodeforapt(rs.getString(7),rs.getInt(4));
					if(codeforapt==null){
						
						a.setTpcode("");
					}else{
						a.setTpcode(codeforapt);
					}
				if(a.getTpcode().equals("")){
					a.setTpcode(rs.getString(8));
				}
				a.setChargedisc(rs.getString(5));
				boolean discstatus=false;
//				if(rs.getDouble(5)>0){
					discstatus=true;
//				}
				a.setDiscstatus(discstatus);
				/*if(rs.getString(6)!=null){
				a.setCommencing(DateTimeUtils.getInvoiceCommencingDate(rs.getString(6)));
				
				}*/
				list.add(a);
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		return list;
	}

	public ArrayList<Accounts> getTpServicesList(String invoiceid, String sname,LoginInfo loginInfo) {
		PreparedStatement preparedStatement = null;
		ArrayList<Accounts>list = new ArrayList<Accounts>();
		
StringBuffer sql = new StringBuffer();
		
		sql.append("select apmtType,charge,quantity,chargeid,(charge * quantity),apm_invoice_assesments.commencing,(unitcharge*quantity),unitcharge,chargedisc,std_charge_id,pkgcode from apm_charges_invoice inner join apm_charges_assesment on ");
		sql.append("apm_charges_assesment.chargeinvoiceid = apm_charges_invoice.id inner join apm_invoice on ");
		sql.append("apm_invoice.id = apm_charges_assesment.invoiceid inner join apm_invoice_assesments on ");
		sql.append("apm_invoice_assesments.invoiceid = apm_invoice.id ");
		sql.append("where apm_charges_invoice.id = "+invoiceid+" and masterchargetype='"+sname+"' order by commencing");
		
		double sum = 0;
		try{
			preparedStatement = connection.prepareStatement(sql.toString());
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()){
				Accounts a = new Accounts();
				a.setAppointmentType(rs.getString(1));
					if(rs.getDouble(9)!=0){
					a.setUnitcharge(rs.getString(7));
					}else{
						a.setUnitcharge(rs.getString(2));
					}
				
				a.setQuantity(rs.getInt(3));
				
				a.setChargeid(rs.getString(4));
				if(rs.getDouble(9)!=0){
					a.setChargeofqty(rs.getString(7));
					}else{
						a.setChargeofqty(rs.getString(5));
					}
//				a.setChargeofqty(rs.getString(5));
				a.setUnitchargeofqty(rs.getString(7));
				
				
					if(rs.getDouble(9)!=0){
					sum = sum + rs.getDouble(7);
					}else{
						sum = sum + rs.getDouble(5);
					}
				
				
				
				
					if(rs.getDouble(9)!=0){
					a.setChargeTotal(DateTimeUtils.changeFormat(rs.getDouble(7)));
					}else{
						a.setChargeTotal(DateTimeUtils.changeFormat(rs.getDouble(5)));
					}
				
				a.setCommencing(rs.getString(6));
				
				String tpcode = getTpChargeCode(rs.getInt(4));
				a.setTpcode(tpcode);
				
				
				if(a.getTpcode()==null){
					a.setTpcode("0");
				}
				if(a.getTpcode().equals("")){
					a.setTpcode("No Code");
				}
				if(a.getTpcode().equals("0")){
					a.setTpcode("No Code");
				}
				
				a.setStdchargeid(rs.getString(10));
				a.setPkgcode(rs.getString(11));
				a.setTpstotal(DateTimeUtils.changeFormat(sum));
				list.add(a);
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		String pkgstr = getPkgStr(Integer.parseInt(invoiceid));
		ArrayList<Accounts>tpsList = getTpsList(pkgstr,sname);
		
		list.addAll(tpsList);
		return list;

		
		
	}

	private ArrayList<Accounts> getTpsList(String pkgstr,String sname) {
		PreparedStatement preparedStatement = null;
		ArrayList<Accounts>list = new ArrayList<Accounts>();
		String sql = "select apmtType,charge,quantity,chargeid,(charge * quantity),commencing,std_charge_id,pkgcode "
				+ " from tp_invoice_assesments where tpkg in("+pkgstr+") "
				+ " and masterchargetype = '"+sname+"'; ";
		double sum = 0;
		try{
			preparedStatement = connection.prepareStatement(sql.toString());
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()){
				Accounts a = new Accounts();
				a.setAppointmentType(rs.getString(1));
				a.setUnitcharge(rs.getString(2));
				a.setQuantity(rs.getInt(3));
				a.setChargeid(rs.getString(4));
				a.setChargeTotal(DateTimeUtils.changeFormat(0));
				a.setCommencing(rs.getString(6));
				a.setStdchargeid(rs.getString(7));
				/*String tpcode = getTpChargeCode(rs.getInt(4));
				a.setTpcode(tpcode);
				
				
				if(a.getTpcode()==null){
					a.setTpcode("0");
				}
				if(a.getTpcode().equals("")){
					a.setTpcode("No Code");
				}
				if(a.getTpcode().equals("0")){
					a.setTpcode("No Code");
				}*/
				String codeforapt=getcodeforapt(rs.getString(7),rs.getInt(4));
				if(codeforapt==null){
					
					a.setTpcode("");
				}else{
					a.setTpcode(codeforapt);
				}
			if(a.getTpcode().equals("")){
				a.setTpcode(rs.getString(8));
			}
				sum = sum + rs.getDouble(5);
				a.setPkgtotal(DateTimeUtils.changeFormat(sum));
				
				list.add(a);
			}
			
		}catch (Exception e) {
			// TODO: handle exception
		}
		return list;
	}

	public double getPkgTotal(String invoiceid, String sname) {
		PreparedStatement preparedStatement = null;
		double result = 0;
		
		String pkgstr = getPkgStr(Integer.parseInt(invoiceid));
		String sql = "select sum(charge * quantity) "
				+ " from tp_invoice_assesments where tpkg in("+pkgstr+") "
				+ " and masterchargetype = '"+sname+"' ";
		
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

	public double getTpsTotal(String invoiceid, String sname,LoginInfo loginInfo) {
		
		PreparedStatement preparedStatement = null;
		double result = 0;
		StringBuffer sql = new StringBuffer();
		sql.append("select sum(charge * quantity),sum(unitcharge * quantity),chargedisc from apm_charges_invoice inner join apm_charges_assesment on ");
		sql.append("apm_charges_assesment.chargeinvoiceid = apm_charges_invoice.id inner join apm_invoice on ");
		sql.append("apm_invoice.id = apm_charges_assesment.invoiceid inner join apm_invoice_assesments on ");
		sql.append("apm_invoice_assesments.invoiceid = apm_invoice.id ");
		sql.append("where apm_charges_invoice.id = "+invoiceid+" and masterchargetype='"+sname+"' group by apm_charges_invoice.id");

		
		try{
			preparedStatement = connection.prepareStatement(sql.toString());
			ResultSet rs = preparedStatement.executeQuery();
			if(rs.next()){
				if(rs.getDouble(3)!=0){
					result = rs.getDouble(2);
				}else {
					result = rs.getDouble(1);
				}
				
			}
		}catch (Exception e) {
				e.printStackTrace();
		}
		
		
		return result;
	
	}

	public int getidforlist(String string) {
		PreparedStatement preparedStatement = null;
		int result=0;
		String sql="SELECT id FROM apm_invoice_type where service=0 and type='"+string+"'";
		try{
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			if(rs.next()){
				result = rs.getInt(1);
			}
			}catch (Exception e) {
			}
		return result;
	}

	public String getrefdrname(String reference) {
		PreparedStatement preparedStatement = null;
		String result="";
		String sql="SELECT name FROM reference where id="+reference+"";
		try{
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			if(rs.next()){
				result = rs.getString(1);
			}
			}catch (Exception e) {
			}
		return result;
	}
	public String getcodeforapt(String stdchargeid, int apmtchargeid) {
		PreparedStatement preparedStatement = null;
		String result="";
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT code FROM apm_appointment_type where ");
		if(stdchargeid==null){
			stdchargeid="0";
		}
		if(stdchargeid.equals("0")){
			sql.append("id="+apmtchargeid+" ");
		}else{
			sql.append("id="+stdchargeid+" ");
		}
		try{
			preparedStatement = connection.prepareStatement(sql.toString());
			ResultSet rs = preparedStatement.executeQuery();
			if(rs.next()){
				result = rs.getString(1);
				
			}
			}catch (Exception e) {
				e.printStackTrace();
			}
		return result;
	}

	public ArrayList<Master> getTpMasterAssesmentList(String pkgstr,LoginInfo loginInfo) {
		
		PreparedStatement preparedStatement = null;
		ArrayList<Master>list = new ArrayList<Master>();
		StringBuffer sql = new StringBuffer();
		
		//sql.append("SELECT masterchargetype,clientid FROM apm_invoice_assesments where clientid = "+clientid+" group by masterchargetype ");
		sql.append("SELECT masterchargetype,clientid,sum(charge+0.0*quantity) FROM tp_invoice_assesments where tpkg in ('"+pkgstr+"') ");
		sql.append("group by masterchargetype order by sqno ");
		/*sql.append("inner join apm_invoice on apm_invoice.id = apm_invoice_assesments.invoiceid ");
		sql.append("where tpkg in ("+pkgstr+") and chargetype!='Submit' ");
		*/
		
		
		try{
			preparedStatement = connection.prepareStatement(sql.toString());
			ResultSet  rs = preparedStatement.executeQuery();
			double dchtotal  = 0;
			while(rs.next()){
				Master master = new Master();
				master.setName(rs.getString(1));
				String clientid = rs.getString(2);
				
				String breakage =  getMasterBreakage(master.getName());
				master.setKrackage(breakage);;
		
				
				
				Vector<Accounts> assesmentList = getPrintchargeFilteredPreviewChargesList(master.getName(),clientid,pkgstr,loginInfo);
				master.setAssesmentList(assesmentList);
				
				double subtotal = getdMasterSubtotal(master.getName(),clientid,"");
				master.setCharge(DateTimeUtils.changeFormat(0.00));
				//  25 Sep 2018 to hide subtotal from main total if there all charges all submited in charge detailsss
				boolean flag = checkChargeSubmited(master.getName(),clientid,"");
				if(flag){
					dchtotal = dchtotal + subtotal;
				}
				master.setDchargetotal(DateTimeUtils.changeFormat(dchtotal));
				if(breakage.equals("1")){
					master.setMedpkgamt(rs.getDouble(3));
				}
				list.add(master);
			}
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		return list;
	}

	private Vector<Accounts> getPrintchargeFilteredPreviewChargesList(String name, String clientid,String pkgstr,LoginInfo loginInfo) {
		PreparedStatement preparedStatement = null;
		Vector<Accounts>list = new Vector<Accounts>();
		String sql = "select apmtType,charge,quantity,chargeid,commencing,thirdpartyid,sum(quantity),(sum(quantity)*charge),std_charge_id,tpkg "
				+ " from tp_invoice_assesments where tpkg in("+pkgstr+") "
						+ " and masterchargetype = '"+name+"' group by apmtType ";
		
		ThirdPartyDAO thirdPartyDAO = new JDBCThirdPartyDAO(connection);
		ClientDAO clientDAO = new JDBCClientDAO(connection);
		Client client = clientDAO.getClientDetails(clientid);
		
		
		String masterchargetype = name;
		
		try{
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()){
				Accounts a = new Accounts();
				
				String apptName = rs.getString(1);
				String  sectionroom = "";
				if(masterchargetype.equals("Pathology") || masterchargetype
						.equals("Radiology")){

					 sectionroom = getSectionRoom(apptName);
					if(apptName.contains("~")){
						String tmp[] = apptName.split("~");
						apptName = tmp[0];
					}
					
					//set investigation alternate name
					String tpid = client.getTypeName();
					ThirdParty thirdParty = thirdPartyDAO.getThirdPartyDetails(tpid);
					
						String alternamename = getInvestigationAltName(rs.getString(1));
						if(alternamename !=null){
							if(!alternamename.equals("")){
								apptName = alternamename;
							}
						}
					}

				
				a.setAppointmentType(apptName);
				a.setUnitcharge(rs.getString(2));
				//a.setQuantity(rs.getInt(7));
				if(loginInfo.isSaimed()) {
					a.setQty(rs.getDouble(7));
				}else {
					int qu=(int)rs.getDouble(7) ;
					a.setQuantity(qu);
				}
				//a.setQty(rs.getDouble(7));
				a.setCommencing(DateTimeUtils.getCommencingDate1(rs.getString(5)));
				
				String tpcode = getTpChargeCode(rs.getInt(4));
				a.setTpcode(tpcode);
				
				
				
				/*if(a.getTpcode()==null){
					a.setTpcode("0");
				}
				if(a.getTpcode().equals("")){
					a.setTpcode("No Code");
				}
				if(a.getTpcode().equals("0")){
					a.setTpcode("No Code");
				}*/
				String codeforapt=getcodeforapt(rs.getString(9),rs.getInt(4));
				if(codeforapt==null){
					
					a.setTpcode("");
				}else{
					a.setTpcode(codeforapt);
				}
			if(a.getTpcode().equals("")){
				a.setTpcode(rs.getString(10));
			}
				list.add(a);
			}
		}catch (Exception e) {
			// TODO: handle exception
		}
		
		return list;
	}

	public String getTpPkgStr(String clientId) {
		PreparedStatement preparedStatement = null;
		String result = "";
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT tpkg FROM apm_invoice_assesments inner join apm_invoice on ");
		sql.append("apm_invoice.id = apm_invoice_assesments.invoiceid ");
		sql.append("where chargetype!='Submit' and tpkg !=0 and apm_invoice_assesments.clientid = "+clientId+" ");
		
		try{
			preparedStatement = connection.prepareStatement(sql.toString());
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()){
				result = result + rs.getString(1) + ",";
			}
			if(!result.equals("")){
				result = result.substring(0, result.length()-1);
			}
			
		}catch (Exception e) {
			// TODO: handle exception
		}
		return result;
			
		
		
		
		
	}

	public Client getDisandadmiss(String clientId) {
		Client client=new Client();
		PreparedStatement preparedStatement = null;
		String result = "";
		StringBuffer sql = new StringBuffer();
		sql.append("select admissiondsate,dischargedate from ipd_addmission_form ");
		sql.append("inner join apm_treatment_episode on apm_treatment_episode.clientid=ipd_addmission_form.clientid ");
		sql.append("where ipd_addmission_form.clientid = "+clientId+" ");
		
		try{
			preparedStatement = connection.prepareStatement(sql.toString());
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				if(rs.getString(1)==null){
					client.setAdmissiondate("");
					client.setAdmissionwithtime("");
				}else{
					String date=rs.getString(1); 
					String temp[] = date.split(" ");
					String date1 = temp[0];
					String commencingdate=DateTimeUtils.getCommencingDate1(date1);
				client.setAdmissiondate(commencingdate);
				client.setAdmissionwithtime(date);
				}
				if(rs.getString(2)==null){
					client.setDischargedate("");
					client.setDischargewithtime("");
				}else{
					String date=rs.getString(2);
					String temp[] = date.split(" ");
					String date1 = temp[0];
					String commencingdate=DateTimeUtils.getCommencingDate1(date1);
				client.setDischargedate(commencingdate);
				client.setDischargewithtime(date);
				}
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		return client;
	}

	public int getTPIdbyclient(String clientId) {
		int res=0;
		try {
			
			String sql="select tpid from apm_charges_invoice where clientid='"+clientId+"'";
			PreparedStatement ps=connection.prepareStatement(sql);
			ResultSet rs=ps.executeQuery();
			while(rs.next()){
				res=rs.getInt(1);
			}
		} catch (Exception e) {
		e.printStackTrace();
		}
		return res;
	}

	public String getTpDetailsbyclientid(int clientid) {
		String res="0";
		try {
			
			String sql="select third_party_name_id from apm_patient where id="+clientid+"";
			PreparedStatement ps=connection.prepareStatement(sql);
			ResultSet rs=ps.executeQuery();
			while(rs.next()){
				res=""+rs.getInt(1);
			}
		} catch (Exception e) {
		e.printStackTrace();
		}
		return res;
	}

	public String getcompany_name(int parseInt) {
		String res="";
		try {
			
			String sql="select company_name from apm_third_party_details inner join  apm_patient on apm_patient.third_party_name_id=apm_third_party_details.id where apm_third_party_details.id="+parseInt+" group by apm_third_party_details.id ";
			PreparedStatement ps=connection.prepareStatement(sql);
			ResultSet rs=ps.executeQuery();
			while(rs.next()){
				res=rs.getString(1);
			}
		} catch (Exception e) {
		e.printStackTrace();
		}
		return res;
	}	
	
	
	//sub charge list
	
	public ArrayList<Accounts> getSubServicesList(String clientid, String sname,String pkgstr,LoginInfo loginInfo) {
		PreparedStatement preparedStatement = null;
		ArrayList<Accounts>list = new ArrayList<Accounts>();
		
StringBuffer sql = new StringBuffer();
		
	
	sql.append("select apmtType,charge,quantity,chargeid,(charge * quantity),apm_invoice_assesments.commencing,apm_invoice_assesments.std_charge_id,pkgcode from apm_invoice_assesments ");
	sql.append("inner join apm_invoice on apm_invoice.id = apm_invoice_assesments.invoiceid ");
	sql.append("where apm_invoice_assesments.clientid = "+clientid+" and chargetype!='submit' and masterchargetype='"+sname+"' order by commencing");
	
		double sum = 0;
		ThirdPartyDAO thirdPartyDAO = new JDBCThirdPartyDAO(connection);
		ClientDAO clientDAO = new JDBCClientDAO(connection);
		Client client = clientDAO.getClientDetails(clientid);
		 
		String masterchargetype = sname;
		
		
		
		try{
			preparedStatement = connection.prepareStatement(sql.toString());
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()){
				Accounts a = new Accounts();
				
				String apptName = rs.getString(1);
				String  sectionroom = "";
				if(masterchargetype.equals("Pathology") || masterchargetype
						.equals("Radiology")){

					 sectionroom = getSectionRoom(apptName);
					if(apptName.contains("~")){
						String tmp[] = apptName.split("~");
						apptName = tmp[0];
					}
					
					//set investigation alternate name
					String tpid = client.getTypeName();
					ThirdParty thirdParty = thirdPartyDAO.getThirdPartyDetails(tpid);
					
						String alternamename = getInvestigationAltName(rs.getString(1));
						if(alternamename !=null){
							if(!alternamename.equals("")){
								apptName = alternamename;
							}
						}
					}

				
				
				a.setAppointmentType(apptName);
				a.setUnitcharge(rs.getString(2));
				//a.setQuantity(rs.getInt(3));
				if(loginInfo.isSaimed()) {
					a.setQty(rs.getDouble(3));
				}else {
					int qu=(int)rs.getDouble(3) ;
					a.setQuantity(qu);
				}
				a.setChargeTotal(DateTimeUtils.changeFormat(rs.getDouble(5)));
				String datef=DateTimeUtils.getCommencingDate1(rs.getString(6));
				a.setCommencing(rs.getString(6));
				a.setChargeofqty(a.getChargeTotal());
				a.setChargeid(rs.getString(4));
				a.setStdchargeid(rs.getString(7));
				a.setPkgcode(rs.getString(8));
				/*String tpcode = getTpChargeCode(rs.getInt(4));
				a.setTpcode(tpcode);
				
				
				if(a.getTpcode()==null){
					a.setTpcode("0");
				}
				if(a.getTpcode().equals("")){
					a.setTpcode("No Code");
				}
				if(a.getTpcode().equals("0")){
					a.setTpcode("No Code");
				}*/
				String codeforapt=getcodeforapt(rs.getString(7),rs.getInt(4));
				if(codeforapt==null){
					
					a.setTpcode("");
				}else{
					a.setTpcode(codeforapt);
				}
			if(a.getTpcode().equals("")){
				a.setTpcode(rs.getString(8));
			}
				sum = sum + rs.getDouble(5);
				a.setTpstotal(DateTimeUtils.changeFormat(sum));
				list.add(a);
			}
		}catch (Exception e) {
			// TODO: handle exception
		}
		
		
		ArrayList<Accounts>tpsList = getTpsList(pkgstr,sname);
		
		list.addAll(tpsList);
		return list;

		
		
	}

	public double getsubchargeTotal(String clientid, String masterchargename) {
		PreparedStatement preparedStatement = null;
		double result = 0;
		StringBuffer sql = new StringBuffer();
		sql.append("select sum(charge * quantity) from apm_invoice_assesments ");
		sql.append("inner join apm_invoice on apm_invoice.id = apm_invoice_assesments.invoiceid ");
		sql.append("where apm_invoice_assesments.clientid = "+clientid+" and chargetype!='submit' and masterchargetype='"+masterchargename+"' ");
		
		
		try{
			preparedStatement = connection.prepareStatement(sql.toString());
			ResultSet rs = preparedStatement.executeQuery();
			if(rs.next()){
				result = rs.getDouble(1);
			}
		}catch (Exception e) {
			// TODO: handle exception
		}
		
		
		return result ;
	
	}

	public int updateOpdPaymentStatus(int appointmentid,int invoiceid) {
		PreparedStatement preparedStatement = null;
		int result = 0;
		String sql = "update apm_available_slot set opdpmnt="+invoiceid+" where id = "+appointmentid+" ";
		
		try{
			
			preparedStatement = connection.prepareStatement(sql);
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

	public String getDrNamebyClientId(String clientId) {
		
		String res="";
		try {
			
			String sql="select concat(initial,' ',firstname,' ',lastname,' (',owner_qualification,')') from apm_user "
					+ "inner join ipd_addmission_form on ipd_addmission_form.practitionerid=apm_user.id where ipd_addmission_form.clientid='"+clientId+"'"
					+ "group by lastname";
			PreparedStatement ps=connection.prepareStatement(sql);
			ResultSet rs=ps.executeQuery();
			while(rs.next()){
				res=rs.getString(1);
			}
		} catch (Exception e) {
		e.printStackTrace();
		}
		return res;
	}

	public int getPaymentAppoinetmentId(String invoiceid) {
		PreparedStatement preparedStatement = null;
		int result = 0;
		String sql = "select appointmentid from apm_charges_assesment inner join apm_invoice on "
				+ " apm_invoice.id = apm_charges_assesment.invoiceid "
				+ " where apm_charges_assesment.chargeinvoiceid = "+invoiceid+" ";
		
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

	public int resetOpdStatus(int appointmentid, int invoiceid) {
		PreparedStatement preparedStatement = null;
		int result = 0;
		String sql = "update apm_available_slot set chrgstatus=0, opdpmnt=0,drcompleted=0 where id="+appointmentid+" ";
		
		try{
			preparedStatement = connection.prepareStatement(sql);
			result = preparedStatement.executeUpdate();
		
		}catch (Exception e) {
			// TODO: handle exception
		}
		return result;
			
	}

	public int updateAutochargeFlagClient(String clientid, String flag) {
		int res=0;
		try {
			
			String sql="update apm_patient set isautocharge="+flag+" where id="+clientid+" ";
			PreparedStatement ps=connection.prepareStatement(sql);
			res =ps.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return res;
	}

	public Vector<Accounts> getPreviewChargesListKunal(int invoiceid) {
		PreparedStatement preparedStatement = null;
		DateTimeUtils dateTimeUtils = new DateTimeUtils();
		Vector<Accounts>list = new Vector<Accounts>();
		//String sql = "SELECT apmtType,charge,practitionerName,practitionerId,paybuy FROM apm_invoice_assesments where invoiceid = "+invoiceid+" ";
		
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT apmtType,charge,apm_invoice.practitionerName,apm_invoice.practitionerId,paybuy,apmt_charge_type,apm_invoice.id, ");
		sql.append("apm_invoice_assesments.commencing,apm_invoice.appointmentid,apm_invoice_assesments.quantity,apm_invoice_assesments.id, ");
		sql.append("apm_invoice_assesments.masterchargetype,sum(quantity),unitcharge,discper,sum(chargedisc),discrs ");
		sql.append("FROM apm_invoice inner join apm_invoice_assesments on ");
		sql.append("apm_invoice_assesments.invoiceid = apm_invoice.id inner join apm_charges_assesment ");
		sql.append("on apm_charges_assesment.invoiceid = apm_invoice.id ");
		sql.append("where apm_charges_assesment.chargeinvoiceid="+invoiceid+" group by apmtType,(charge+0.0) ");
		
		
		try{
			preparedStatement = connection.prepareStatement(sql.toString());
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()){
				String charge ="";
				String apptName = rs.getString(1);
				String discper=rs.getString(15);
				double discrs=rs.getDouble(17);
				if(discper==null){
					discper="0";
				}
				if(discper.equals("")){
					discper="0";
				}
				if(!discper.equals("0")){
					 charge = rs.getString(14);	
				}else if(discrs!=0){
					 charge = rs.getString(14);
				}
				else{
					 charge = rs.getString(2);
				}
				
//				
				if(charge==null){
					charge = "0.0";
				}
				String practitionerName = rs.getString(3);
				String practitionerid = rs.getString(4);
				int payby = rs.getInt(5);
				int addtitonalCharge = rs.getInt(6);
				String chargeid = rs.getString(7);
				String date = rs.getString(8);
				int apptid = rs.getInt(9);
				int quantity = rs.getInt(13);
				int subchargeid = rs.getInt(11);
				String masterchargetype = rs.getString(12);
				/*String sectionroom = "";
				if(masterchargetype.equals("INVESTIGATION")){
					 sectionroom = getSectionRoom(apptName);
				}*/
				
				Accounts accounts = new Accounts();
				//accounts.setSectionroom(sectionroom);
				if(addtitonalCharge==0){
					Accounts acc = getAppointmentDetailsl(chargeid);
					if(acc.getCommencing()!=null){
						accounts.setCommencing(DateTimeUtils.getInvoiceCommencingDate(acc.getCommencing()));
						accounts.setAppointmentType(acc.getTreatmentEpisodeName() + " " +apptName);
					}
					
					
					boolean dna = getDna(apptid);
					accounts.setDna(dna);
					
				}else{
					String invoicedate = date;
					accounts.setCommencing(DateTimeUtils.getInvoiceCommencingDate(invoicedate));
					accounts.setAppointmentType("Additional Charge("+apptName+")");
				}
				
				accounts.setCharges(dateTimeUtils.changeFormat(Double.parseDouble(charge)));
				accounts.setChargeTotal(dateTimeUtils.changeFormat(Double.parseDouble(charge)*quantity));
				accounts.setPractitionerName(practitionerName);
				String practitionerFullName = getPractitionerFullName(practitionerid);
				accounts.setPractitionerName(practitionerFullName);
				accounts.setQuantity(quantity);
				accounts.setSubchargeid(subchargeid);
				accounts.setPayBy(payby);
				accounts.setAppointmentType(apptName);
				accounts.setDiscamt(rs.getString(16));
				accounts.setMasterchargetype(masterchargetype);
//				Accounts accounts2=getMasternameforDisc(invoiceid);
				
//				if(Integer.parseInt(discper)>0){
//					
//					
//					accounts.setMasterchargedisc(accounts2.getMasterchargetype());
//				}
//				accounts.setDiscpercent(String.valueOf(accounts2.getDiscount()));
//				double discountamt=Double.parseDouble(rs.getString(16));
//				accounts.setDiscountamt(accounts2.getDiscountamt());
				list.add(accounts);
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
		return list;
	}



	public Vector<Accounts> getFilteredPreviewChargesListForKunal(int invoiceid,String masterchargetype,String filterbydate, LoginInfo loginInfo) {
		boolean discstatus=false;
		PreparedStatement preparedStatement = null;
		DateTimeUtils dateTimeUtils = new DateTimeUtils();
		IpdDAO ipdDAO = new JDBCIpdDAO(connection);
		BedDao bedDao = new JDBCBedDao(connection);
		ThirdPartyDAO thirdPartyDAO = new JDBCThirdPartyDAO(connection);
		Vector<Accounts>list = new Vector<Accounts>();
		//String sql = "SELECT apmtType,charge,practitionerName,practitionerId,paybuy FROM apm_invoice_assesments where invoiceid = "+invoiceid+" ";
		
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT apmtType,charge,apm_invoice.practitionerName,apm_invoice.practitionerId,paybuy,apmt_charge_type,apm_invoice.id,apm_invoice_assesments.commencing,apm_invoice.appointmentid,sum(apm_invoice_assesments.quantity),apm_invoice_assesments.id,apm_invoice_assesments.chargeid,apm_invoice_assesments.std_charge_id,apm_invoice_assesments.ipdid,apm_invoice.ginvstid,unitcharge,chargedisc,apm_invoice_assesments.logid,pkgcode,sum(quantity),unitcharge,discper,discrs ,apm_invoice_assesments.tax1,apm_invoice_assesments.tax2,apm_invoice_assesments.tax3 ,chargedescript,tpkg,manualcharge,showing_date ");
		sql.append("FROM apm_invoice inner join apm_invoice_assesments on ");
		sql.append("apm_invoice_assesments.invoiceid = apm_invoice.id inner join apm_charges_assesment ");
		sql.append("on apm_charges_assesment.invoiceid = apm_invoice.id ");
		sql.append("where apm_charges_assesment.chargeinvoiceid="+invoiceid+" and masterchargetype = '"+masterchargetype+"'  ");
		sql.append(" group by apm_invoice_assesments.apmtType,(charge+0.0) ");
		if(filterbydate.equals("1")){
			sql.append("order by apm_invoice_assesments.commencing ");
		}else{
			sql.append("order by apmtType ");
		}
		
		try{
			preparedStatement = connection.prepareStatement(sql.toString());
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()){
				
				String apptName = rs.getString(1);
				String  sectionroom = "";
				if(masterchargetype.equals("Pathology") || masterchargetype
						.equals("Radiology")){

					 sectionroom = getSectionRoom(apptName);
					if(apptName.contains("~")){
						String tmp[] = apptName.split("~");
						apptName = tmp[0];
					}
					
					//set investigation alternate name
					String tpid = getInvoiceTpId(invoiceid);
					ThirdParty thirdParty = thirdPartyDAO.getThirdPartyDetails(tpid);
					
						String alternamename = getInvestigationAltName(rs.getString(1));
						if(alternamename !=null){
							if(!alternamename.equals("")){
								apptName = alternamename;
							}
						
					}
					
					if(sectionroom==null){
						
					}
					else if(!sectionroom.equals("")){
						apptName = apptName + " (Room Nos : "+sectionroom+")";
					}
					
					
				}
				String charge="";
//				  ipd print kunal
				String discper=rs.getString(22);
				double discrs=rs.getDouble(23);
				if(discper==null){
					discper="0";
				}
				if(discper.equals("")){
					discper="0";
				}
				if(!discper.equals("0")){
					
						if(loginInfo.getIskunal()==1){
							 charge = rs.getString(21);
						}else{
							charge = rs.getString(21);
						}
						if(rs.getInt(28)>0){
							if(loginInfo.isPackage_access()){
								 charge = rs.getString(21);
							}else{
								charge = rs.getString(21);
							}
						}
				}else if (discrs!=0) {
					if(loginInfo.getIskunal()==1){
						 charge = rs.getString(21);
					}else{
						charge = rs.getString(21);
					}
					if(rs.getInt(28)>0){
						if(loginInfo.isPackage_access()){
							 charge = rs.getString(21);
						}else{
							charge = rs.getString(2);
						}
					}
				}				
				else{
					charge = rs.getString(2);
				}
				
				
				
				
				
				
				if(charge==null){
					charge = "0.0";
				}
				String practitionerName = rs.getString(3);
				String practitionerid = rs.getString(4);
				int payby = rs.getInt(5);
				int addtitonalCharge = rs.getInt(6);
				String chargeid = rs.getString(7);
				String date = rs.getString(8);
				int apptid = rs.getInt(9);
				int quantity=0;
//				shubbham
				if(loginInfo.getIskunal()==1 ){
					 quantity = rs.getInt(20);
				}else{
					 quantity = rs.getInt(10);	
				}
				
				int subchargeid = rs.getInt(11);
				int apmtchargeid = rs.getInt(12);;
				
				
				Accounts accounts = new Accounts();
				
				if(addtitonalCharge==0){
					Accounts acc = getAppointmentDetailsl(chargeid);
					if(acc.getCommencing()!=null){
						accounts.setCommencing(DateTimeUtils.getInvoiceCommencingDate(acc.getCommencing()));
						accounts.setAppointmentType(acc.getTreatmentEpisodeName() + " " +apptName);
					}else{
						accounts.setCommencing(DateTimeUtils.getInvoiceCommencingDate(date));
					}
					
					
					boolean dna = getDna(apptid);
					accounts.setDna(dna);
					
				}else{
					String invoicedate = date;
					accounts.setCommencing(DateTimeUtils.getInvoiceCommencingDate(invoicedate));
					accounts.setAppointmentType("Additional Charge("+apptName+")");
				}
				
				accounts.setCharges(dateTimeUtils.changeFormat(Double.parseDouble(charge)));
				accounts.setChargeTotal(dateTimeUtils.changeFormat(Double.parseDouble(charge)*quantity));
				accounts.setPractitionerName(practitionerName);
				String practitionerFullName = getPractitionerFullName(practitionerid);
				accounts.setPractitionerName(practitionerFullName);
				accounts.setQuantity(quantity);
				accounts.setSubchargeid(subchargeid);
				
				boolean isSTdCharge =getIfSTdCharge(rs.getString(13));
				if(isSTdCharge){
					String startEndTime= getStdChargeStartEndTime(subchargeid,rs.getString(14));
					
					
					if(startEndTime!=null){
						String tempc[] = startEndTime.split("~");
						String t1[] = tempc[0].split(",");
						String t2[] = tempc[1].split(",");
						
						String resultdatetime = "";
						for(int i=0;i<t2.length;i++){
							resultdatetime = resultdatetime + t1[i] + " - " + t2[i] + " , "; 
						}
						
						if(resultdatetime.length()!=0){
							resultdatetime = resultdatetime.substring(0,resultdatetime.length()-1);
						}
						int curstatus = getstdonoffcurstatus(subchargeid,rs.getString(14));
						if(curstatus==1){
							resultdatetime = resultdatetime + " , " + t1[t1.length-1];
						}
						
						accounts.setAppointmentType(apptName+" ("+resultdatetime+") ");
					} else {
						accounts.setAppointmentType(apptName);
					}
					
				}else {
					accounts.setAppointmentType(apptName);
				}
				
				accounts.setPayBy(payby);
				
				String stdchargeid=rs.getString(13);
//				String tpcode = getTpChargeCode(apmtchargeid,stdchargeid);
//				accounts.setTpcode(tpcode);
				
				/*if(payby==0){
					accounts.setTpcode(" ");
				}*/
				
				//  22 dec 2017 hide package charges
				int ginvstid = checkIsInvestigationPackage(rs.getString(15));
				accounts.setPkginvissid(String.valueOf(ginvstid));
				
				//lokesh 04/12/18
				InvestigationDAO investigationDAO= new JDBCInvestigationDAO(connection);
				Investigation investigation= investigationDAO.getEditInvestigation(rs.getString(15));
				ArrayList<Investigation> invstlist= new ArrayList<Investigation>();
				if(investigation!=null){
					if(investigation.getPackagename()!=null){
						if(!investigation.getPackagename().equals("0")){
							invstlist= investigationDAO.getInvestigationsFrompakages(investigation.getInvreq(), investigation.getPackagename());

						}
					}
					
				}
				accounts.setInvstlist(invstlist);
				double unitcharge = rs.getDouble(16);
				double chargedisc = rs.getDouble(17);
				
//				if(!rs.getString(19).equals("0")){
//					accounts.setTpcode(rs.getString(19));
//				}
//				
//				if(accounts.getTpcode()==null){
//					accounts.setTpcode("0");
//				}
//				if(accounts.getTpcode().equals("")){
//					accounts.setTpcode("No Code");
//				}
//				if(accounts.getTpcode().equals("0")){
//					accounts.setTpcode("No Code");
//				}
				String pkgcode=rs.getString(19);
				if(pkgcode==null){
					pkgcode="0";
				}
				 if(pkgcode.equals("")){
					pkgcode="0";
				}
				if(pkgcode.equals("0")){
					
					String codeforapt=getcodeforapt(rs.getString(13),rs.getInt(12));
					if(loginInfo.getIskunal()==1 ){
						if(codeforapt==null){
							codeforapt="";
						}
						else if(codeforapt.equals("")){
							
							accounts.setTpcode("NO CODE");
							
						
						}else{
							accounts.setTpcode(codeforapt);
						}				
					
					}else{
						if(codeforapt==null){
							
							accounts.setTpcode("");
						}else{
							accounts.setTpcode(pkgcode);
						}
					}
					
					}else{
						accounts.setTpcode(pkgcode);
					}
				accounts.setIpdid(""+rs.getInt(14));
				
				//  03 July 2018 
				//Bed bed = getBedLogData(""+rs.getInt(14));
				//Bed bed = getBedLogData(""+rs.getInt(14));
				
				
				if(isSTdCharge){
					String logid = rs.getString(18);

					Bed bed = getBedLogData(logid);
					String wardname=ipdDAO.getIpdWardName(bed.getWardid());
					String bedname = ipdDAO.getIpdBedName(bed.getBedid());
					accounts.setWard(wardname + " / " + bedname);
				}else{
					Bed bed = bedDao.getEditIpdData(""+rs.getInt(14));
					String wardname=ipdDAO.getIpdWardName(bed.getWardid());
					String bedname = ipdDAO.getIpdBedName(bed.getBedid());
					accounts.setWard(wardname + " / " + bedname);
				}
									
				
				if(unitcharge==0){
					unitcharge = rs.getDouble(2);
				}
				accounts.setUnitcharge(DateTimeUtils.changeFormat(unitcharge));
				accounts.setChargedisc(DateTimeUtils.changeFormat(chargedisc));
//				 if(chargedisc>0){
						discstatus=true;
//					}
					accounts.setDiscstatus(discstatus);
					
					accounts.setTax1(""+rs.getDouble(24));
					accounts.setTax2(""+rs.getDouble(25));
					accounts.setTax3(""+rs.getDouble(26));
					MasterDAO masterDAO = new JDBCMasterDAO(connection);
					if(!accounts.getTax1().equals("0.0")){
						accounts.setTaxname1(masterDAO.getTaxnamebyType("1"));
					} 
					if(!accounts.getTax2().equals("0.0")){
						accounts.setTaxname2(masterDAO.getTaxnamebyType("2"));
					}
					if(!accounts.getTax3().equals("0.0")){
						accounts.setTaxname3(masterDAO.getTaxnamebyType("3"));
					}	
					
					String chargedescript=rs.getString(27);
					if(chargedescript==null){
						chargedescript="";
					}
					accounts.setChargedescription(chargedescript.replaceAll("<br>", ""));
					accounts.setTpkg(rs.getInt(28));
					accounts.setManualcharge(rs.getString(29));
					accounts.setShowdate(rs.getString(30));
				list.add(accounts);
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
		return list;
	}

	public String getTpChargeCode(int apmtchargeid, String stdchargeid) {
		PreparedStatement preparedStatement = null;
		String result = "";
		String sql ="";
		if(apmtchargeid==0){
			 sql = "SELECT code FROM apm_appointment_type where id = "+stdchargeid+" ";
		}else{
		 sql = "SELECT code FROM apm_appointment_type where id = "+apmtchargeid+" ";
		}
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

	public ArrayList<Master> getInvoicedChargeidList(int invoiceid) {
		PreparedStatement preparedStatement = null;
		ArrayList<Master>list = new ArrayList<Master>();
		StringBuffer sql = new StringBuffer();
		
		sql.append("select apm_invoice_assesments.id from apm_charges_assesment inner join apm_invoice on ");
		sql.append("apm_invoice.id = apm_charges_assesment.invoiceid inner join apm_invoice_assesments on ");
		sql.append("apm_invoice_assesments.invoiceid = apm_invoice.id ");
		sql.append("where apm_charges_assesment.chargeinvoiceid = "+invoiceid+" ");
	
		try{
			
			preparedStatement = connection.prepareStatement(sql.toString());
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()){
				Master m = new Master();
				m.setId(rs.getInt(1));
				
				list.add(m);
			}
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		
		return list;
	}

	public int updateChargeInvoideid(int id, int invoiceid) {
		PreparedStatement preparedStatement = null;
		int result = 0;
		String sql = "update apm_invoice_assesments set invoiced="+invoiceid+" where id = "+id+" ";
		
		try{
			
			preparedStatement = connection.prepareStatement(sql);
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

	public ArrayList<Accounts> getchargelist(String mastername) {
		
		return null;
	}
	private double getMasterSubtotalKunal(int invoiceid, String masterchargetype, int wardid) {
		PreparedStatement preparedStatement = null;
		double result = 0;
		double addresult=0;
		StringBuffer sql = new StringBuffer();
		
		sql.append("select unitcharge,sum(quantity) from  apm_charges_invoice  inner join apm_charges_assesment on ");
		sql.append("apm_charges_assesment.chargeinvoiceid = apm_charges_invoice.id ");
		sql.append("inner join apm_invoice on apm_invoice.id = apm_charges_assesment.invoiceid ");
		sql.append("inner join apm_invoice_assesments on apm_invoice_assesments.invoiceid = apm_invoice.id ");
		sql.append("where apm_charges_invoice.id = "+invoiceid+" and masterchargetype = '"+masterchargetype+"' group by apmtType,(charge+0.0) ");
		if(wardid!=0){
			sql.append("and apm_invoice_assesments.wardid='"+wardid+"' ");
		}
		
		try{
			preparedStatement = connection.prepareStatement(sql.toString());
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()){
				result = rs.getDouble(1)*rs.getInt(2);
				addresult=addresult+result;
			}
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		return addresult;
	}

	public ArrayList<Accounts>getMasternameforDisc(int invoiceid) {
		ArrayList<Accounts>list = new ArrayList<Accounts>();
		PreparedStatement preparedStatement=null;
		StringBuffer sql=new StringBuffer();
		sql.append("SELECT  apm_invoice_assesments.masterchargetype,discper,sum(chargedisc),discrs ");
		sql.append("FROM apm_invoice ");
		sql.append("inner join apm_invoice_assesments on apm_invoice_assesments.invoiceid = apm_invoice.id ");
		sql.append(" inner join apm_charges_assesment on apm_charges_assesment.invoiceid = apm_invoice.id ");
		sql.append(" where apm_charges_assesment.chargeinvoiceid="+invoiceid+" and discper is not null and tpkg=0 group by masterchargetype ");
		
		try{
			preparedStatement = connection.prepareStatement(sql.toString());
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()){
				Accounts accounts=new Accounts();
				String mastername=rs.getString(1);
				boolean isPresent = mastername.indexOf("SURGICAL MANAGEMENT (") != -1 ? true : false;
				if(isPresent){
					accounts.setMasterchargedisc("SURGICAL MANAGEMENT");
				}else{
				accounts.setMasterchargedisc(rs.getString(1));
				}
				if(rs.getString(2)==null){
					accounts.setDiscpercent("0");
				}else{
				accounts.setDiscpercent(rs.getString(2));
				}
				if(rs.getString(4)==null){
					accounts.setDiscoutrs("0");
				}else{
					accounts.setDiscoutrs(rs.getString(4));
				}
				accounts.setDiscountamtt(rs.getDouble(3));
				list.add(accounts);
			}
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		ArrayList<Accounts>newdisclist=getSurgicalAmt(invoiceid);
		list.addAll(newdisclist);
		return list;
	}
	private ArrayList<Accounts> getSurgicalAmt(int invoiceid) {
		PreparedStatement preparedStatement=null;
		double result=0;
		ArrayList<Accounts>list = new ArrayList<Accounts>();
		StringBuffer sql=new StringBuffer();
		sql.append("SELECT  apm_invoice_assesments.masterchargetype,discper,sum(chargedisc),discrs  ");
		sql.append("FROM apm_invoice ");
		sql.append("inner join apm_invoice_assesments on apm_invoice_assesments.invoiceid = apm_invoice.id ");
		sql.append(" inner join apm_charges_assesment on apm_charges_assesment.invoiceid = apm_invoice.id ");
		sql.append(" where apm_charges_assesment.chargeinvoiceid="+invoiceid+" and discper is not null and tpkg>0 group by discper,discrs ");
		
		try{
			preparedStatement = connection.prepareStatement(sql.toString());
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()){
				Accounts accounts=new Accounts();
				String mastername=rs.getString(1);
				boolean isPresent = mastername.indexOf("SURGICAL MANAGEMENT (") != -1 ? true : false;
				if(isPresent){
					accounts.setMasterchargedisc("SURGICAL MANAGEMENT");
				}else{
				accounts.setMasterchargedisc(rs.getString(1));
				}
				if(rs.getString(2)==null){
					accounts.setDiscpercent("0");
				}else{
				accounts.setDiscpercent(rs.getString(2));
				}
				if(rs.getString(4)==null){
					accounts.setDiscoutrs("0");
				}else{
					accounts.setDiscoutrs(rs.getString(4));
				}
				accounts.setDiscountamtt(rs.getDouble(3));
				list.add(accounts);
			}
	}catch (Exception e) {
		e.printStackTrace();
	}
		return list;
	}
	public double getsubchargeFinalTotal(String clientId, String masterchargename) {
		PreparedStatement preparedStatement = null;
		double result = 0;
		StringBuffer sql = new StringBuffer();
		sql.append("select sum(charge * quantity) from tp_invoice_assesments ");
		sql.append("inner join apm_invoice on apm_invoice.id = tp_invoice_assesments.invoiceid ");
		sql.append("where tp_invoice_assesments.clientid = "+clientId+" and chargetype!='submit' and masterchargetype='"+masterchargename+"' ");
		
		
		try{
			preparedStatement = connection.prepareStatement(sql.toString());
			ResultSet rs = preparedStatement.executeQuery();
			if(rs.next()){
				result = rs.getDouble(1);
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		
		return result ;
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
				genearteSeqOfPaymentByFinancialYear(his_payId);
			}
		} catch (Exception e) {
			e.printStackTrace();
//			StringWriter sw = new StringWriter();
//	         PrintWriter pw = new PrintWriter(sw);
//	         e.printStackTrace(pw);
//			Tra.exceptionMail(sw.toString());
		}
		return res;
	}
	
	public int getItype(String invoiceid){
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

	public String getPhysicalpaymentIdAdvRef(String invoiceid){
		String res="0";
		try {
			PreparedStatement ps= connection.prepareStatement(" select id from his_payment_record_physical where adv_ref_id='"+invoiceid+"'   ");
			ResultSet rs= ps.executeQuery();
			while(rs.next()){
				res=""+rs.getString(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return res;
	}
	
	
	public String getPhysicalpaymentId(String payid){
		String res="0";
		try {
			PreparedStatement ps= connection.prepareStatement(" select sequence_id from his_payment_record_physical where payment_id='"+payid+"'   ");
			ResultSet rs= ps.executeQuery();
			while(rs.next()){
				res=""+rs.getString(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return res;
	}

	public int getTpkgId(String clientId, int ipdid,String tablename) {
		int res=0;
		try {
			String sql=" select tpkg from "+tablename+" where clientId="+clientId+" and ipdid="+ipdid+" group by tpkg";
			PreparedStatement ps= connection.prepareStatement(sql);
			ResultSet rs= ps.executeQuery();
			while(rs.next()){
				res=rs.getInt(1);
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		return res;
	}
	public int getMaxPaymentSeqNo(String year) {
		int res=0;
		try {
			String sql=" select max(seq) from his_payment_sequence where year='"+year+"'";
			PreparedStatement ps= connection.prepareStatement(sql);
			ResultSet rs= ps.executeQuery();
			while(rs.next()){
				res=rs.getInt(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return res;
	}

	public int paymentSequenceGenerationProccess(int paymentid) {
		int result=0;
		try {
			DateFormat dateFormat = new SimpleDateFormat("yyyy");
			Calendar cal = Calendar.getInstance();
			String year = dateFormat.format(cal.getTime());
			int maxSeqNo=getMaxPaymentSeqNo(year);
			maxSeqNo=maxSeqNo+1;
			inserLatestSeqNo(year, maxSeqNo);
			addSeqNoToHisPayment(paymentid, year+"/"+maxSeqNo);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	private int inserLatestSeqNo(String year,int sequence){
		int res=0;
		try {
			PreparedStatement ps= connection.prepareStatement(" insert into his_payment_sequence(year,seq) values(?,?) ");
			ps.setString(1, year);
			ps.setInt(2, sequence);
			res=ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return res;
	}
	
	private int addSeqNoToHisPayment(int paymentid, String seqNo){
		int res=0;
		try {
			PreparedStatement ps= connection.prepareStatement("update his_payment_record_physical set sequence_id='"+seqNo+"' where id='"+paymentid+"'");
			res=ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return res;
	}

	public int reverseCharges(int invoiceid) {
		int res=0;
		try {
			int chargeinvid=0;
			
			PreparedStatement preparedStatement=null;
			StringBuffer buffer=new StringBuffer();
			buffer.append("select apm_invoice.id from apm_charges_assesment ");
			buffer.append("inner join apm_invoice on apm_invoice.id=apm_charges_assesment.invoiceid ");
			buffer.append("where chargeinvoiceid="+invoiceid+" ");
			preparedStatement = connection.prepareStatement(buffer.toString());
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()){
				chargeinvid = rs.getInt(1);
				res=updateChargeType(chargeinvid);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return res;
	}

	private int updateChargeType(int chargeinvid) {
		PreparedStatement preparedStatement = null;
		int result = 0;
		String chargetype="Charge";
		String sql = "update apm_invoice set chargetype='"+chargetype+"' where id = "+chargeinvid+" ";
		
		try{
			
			preparedStatement = connection.prepareStatement(sql);
			result = preparedStatement.executeUpdate();
			
		}catch (Exception e) {
			e.printStackTrace();;
		}
		return result;
	}

	public Accounts getApm_invoice_Assesments(String id) {
		Accounts accounts=new Accounts();
		PreparedStatement preparedStatement=null;
		String sql="select tpkg,tpfdate,tptodate,ipdid,id from apm_invoice_assesments where invoiceid='"+id+"' group by invoiceid ";
		try {
			preparedStatement=connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			if(rs.next()){
			accounts.setTpkg(rs.getInt(1));
			if(rs.getString(2)!=null){
			accounts.setTpfdate(rs.getString(2));
			}
			if(rs.getString(3)!=null){
			accounts.setTptodate(rs.getString(3));
			}
			accounts.setIpdid(rs.getString(4));
			}
			accounts.setId(rs.getInt(5));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return accounts;
	}

	public int saveTpServiceData(Accounts a, LoginInfo loginInfo) {
		PreparedStatement p = null;
		int result = 0;
		String sql = "insert into tp_temp_service_data (apmtType, charge, quantity, chargeid, chargeofquantity, commencing, unitchargeofquantity,  sessionid,std_charge_id,pkgcode) values(?,?,?,?,?,?,?,?,?,?) ";
		
		try{
			p = connection.prepareStatement(sql);
			p.setString(1, a.getAppointmentType());
			p.setString(2, a.getUnitcharge());
			//p.setInt(3, a.getQuantity());
			p.setDouble(3, a.getQty());
			p.setString(4, a.getChargeid());
			p.setString(5, a.getChargeofqty());
			p.setString(6, a.getCommencing());
			p.setString(7, a.getUnitchargeofqty());
			p.setString(8, loginInfo.getLoginsessionid());
			p.setString(9, a.getStdchargeid());
			p.setString(10, a.getPkgcode());
			result = p.executeUpdate();
			
		}catch (Exception e) {
			// TODO: handle exception
		}
		return result;
	}

	public int deleteTpTempSessionData(String loginsessionid) {
		PreparedStatement p = null;
		int result = 0;
		String sql = "delete from tp_temp_service_data where sessionid = '"+loginsessionid+"' ";
	
		try{
			
			p = connection.prepareStatement(sql);
			result = p.executeUpdate();
			
			
		}catch (Exception e) {
			// TODO: handle exception
		}
		return result;
	}

	public ArrayList<Accounts> getTpTempAssesmentList(LoginInfo loginInfo) {
		PreparedStatement preparedStatement = null;
		ArrayList<Accounts>list = new ArrayList<Accounts>();
		String sql = "select commencing,apmtType,quantity,charge,chargeofquantity,chargeid,unitchargeofquantity,std_charge_id,pkgcode "
				+ " from tp_temp_service_data "
				+ "  where sessionid = '"+loginInfo.getLoginsessionid()+"' "
						+ " order by commencing ";
		
		try{
			
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()){
				Accounts a = new Accounts();
				a.setCommencing(DateTimeUtils.getCommencingDate1(rs.getString(1)));
				a.setAppointmentType(rs.getString(2));
				//a.setQuantity(rs.getInt(3));
				if(loginInfo.isSaimed()) {
					a.setQty(rs.getDouble(3));
				}else {
					int qu=(int)rs.getDouble(3) ;
					a.setQuantity(qu);
				}
				//a.setQty(rs.getDouble(3));
				a.setUnitcharge(DateTimeUtils.changeFormat(rs.getDouble(4)));
				a.setChargeTotal(DateTimeUtils.changeFormat(rs.getDouble(5)));
				
				
				String pkgcode=rs.getString(9);
				if(pkgcode==null){
					pkgcode="0";
				}
				 if(pkgcode.equals("")){
					pkgcode="0";
				}
				if(pkgcode.equals("0")){
					
					String codeforapt=getcodeforapt(rs.getString(8),rs.getInt(6));
					if(loginInfo.getIskunal()==1 ){
						if(codeforapt==null){
							codeforapt="";
						}
						else if(codeforapt.equals("")){
							
								a.setTpcode("NO CODE");
							
						
						}else{
							a.setTpcode(codeforapt);
						}				
					
					}else{
						if(codeforapt==null){
							
							a.setTpcode("");
						}else{
							a.setTpcode(pkgcode);
						}
					}
					
					}else{
						a.setTpcode(pkgcode);
					}
				
				list.add(a);
				
			}
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	public int reveseAssesmentAmount(int invoiceid) {
		int res=0;
		try {
			int chargeinvid=0;
			
			PreparedStatement preparedStatement=null;
			StringBuffer buffer=new StringBuffer();
			buffer.append("select id,chargedisc,unitcharge,clientId,ipdid from apm_invoice_assesments ");
			buffer.append("where invoiced="+invoiceid+" ");
			preparedStatement = connection.prepareStatement(buffer.toString());
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()){
				Accounts accounts=new Accounts();
				accounts.setAssesmentid(rs.getString(1));
				accounts.setChargedisc(rs.getString(2));
				accounts.setUnitcharge(rs.getString(3));
				accounts.setClientid(rs.getInt(4));
				accounts.setIpdid(rs.getString(5));
				
				if(rs.getDouble(2)!=0){
					res=updateAssesmentsAmount(accounts);
				}
				res=updateAssesmentsInvoiced(accounts);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return res;
	}
	private int updateAssesmentsInvoiced(Accounts accounts) {
		PreparedStatement preparedStatement = null;
		int result = 0;
		String sql = "update apm_invoice_assesments set discreq=0, discapprove=0,invoiced=0 where id = "+accounts.getAssesmentid()+" ";
		
		try{
			
			preparedStatement = connection.prepareStatement(sql);
			result = preparedStatement.executeUpdate();
			
		}catch (Exception e) {
			e.printStackTrace();;
		}
		return result;
	}

	private int updateAssesmentsAmount(Accounts accounts) {
		PreparedStatement preparedStatement = null;
		int result = 0;
		String sql = "update apm_invoice_assesments set charge='"+accounts.getUnitcharge()+"',chargedisc=0,unitcharge=0,discper=0,discrs=0,discreq=0, discapprove=0,invoiced=0 where id = "+accounts.getAssesmentid()+" ";
		
		try{
			
			preparedStatement = connection.prepareStatement(sql);
			result = preparedStatement.executeUpdate();
			int parentid=checkindividualdisc(accounts);
			if(parentid>0){
				int r=deleteDiscountReq(parentid);
			}
		}catch (Exception e) {
			e.printStackTrace();;
		}
		return result;
	}

	private int deleteDiscountReq(int parentid) {
		int res=0;
		PreparedStatement preparedStatement = null;
		String sql = "update discount_request set deleted=1 where id = "+parentid+" ";
		
		try{
			
			preparedStatement = connection.prepareStatement(sql);
			res= preparedStatement.executeUpdate();
			
		}catch (Exception e) {
			e.printStackTrace();;
		}
		return res; 
	}

	private int checkindividualdisc(Accounts accounts) {
		int res =0;
		try {
			StringBuffer buffer = new StringBuffer();
			buffer.append("select parent_discreq_id from child_discount_request ");
			buffer.append("where child_ipdid="+accounts.getIpdid()+" and child_clientid="+accounts.getClientid()+" and assesment_id="+accounts.getAssesmentid()+" ORDER BY id DESC LIMIT 1 ");
			PreparedStatement preparedStatement = connection.prepareStatement(buffer.toString());
			ResultSet rs = preparedStatement.executeQuery();
			if (rs.next()) {
				res = rs.getInt(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return res;
	}

	public void updateInvoiceid(int invoiceid) {
		PreparedStatement preparedStatement = null;
		String sql = "update apm_invoice_assesments set unitcharge=0 where id = "+invoiceid+" ";
		
		try{
			
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.executeUpdate();
			
		}catch (Exception e) {
			e.printStackTrace();;
		}
	}

	public ArrayList<Accounts> getUnitchargeList(int invoiceid) {
		PreparedStatement p = null;
		ArrayList<Accounts>list = new ArrayList<Accounts>();
		String sql = "SELECT unitcharge,id FROM apm_invoice_assesments where invoiced="+invoiceid+" and unitcharge !=0 ";
		
		try{
			p = connection.prepareStatement(sql);
			ResultSet rs = p.executeQuery();
			while(rs.next()){
				Accounts a = new Accounts();
				a.setUnitcharge(rs.getString(1));
				a.setId(rs.getInt(1));
				
				list.add(a);
			}
		}catch (Exception e) {
			// TODO: handle exception
		}
		
		return list;
	}

	public int updateDiscountedCharge(String unitcharge, int id) {
		PreparedStatement preparedStatement = null;
		int result = 0;
		String sql = "update apm_invoice_assesments set discper=0,discrs=0,unitcharge=0,charge="+unitcharge+" where id = "+id+" ";
		
		try{
			
			preparedStatement = connection.prepareStatement(sql);
			result = preparedStatement.executeUpdate();
		}catch (Exception e) {
			// TODO: handle exception
		}
		return result;
	}

	public String gettextdiagnosis(int ipdid) {
		String sql = "SELECT kunal_final_diagnosis_text FROM ipd_addmission_form where id="+ipdid+"";
		PreparedStatement p=null;
		String res="";
		try{
			p = connection.prepareStatement(sql);
			ResultSet rs = p.executeQuery();
			while(rs.next()){
				res=rs.getString(1);
			}
		}		catch (Exception e) {
			e.printStackTrace();
		}
		return res;
	}

	public String getMultiplePaymentIdAgainstInvoice(String invoiceid) {
		String result="";
		try {
			PreparedStatement ps= connection.prepareStatement("select sequence_id from his_payment_record_physical where invoiceid='"+invoiceid+"'");
			ResultSet rs= ps.executeQuery();
			int i=0;
			while(rs.next()){
				if(i==0){
					result=rs.getString(1);	
				}else{
					result=result+","+rs.getString(1);	
				}
				i=i+1;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public void genearteSeqOfPaymentByFinancialYear(int paymentid) {
		try {
			DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
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
		  //seq no genlogic
	    	int maxSeqNo=getMaxPaymentSeqNo(yearrangenew);
			maxSeqNo=maxSeqNo+1;
			inserLatestSeqNo(yearrangenew, maxSeqNo);
			
			String yearcomb[]=yearrangenew.split("-");
			//2019-2020 will be 1920
			String yr =yearcomb[0].substring(2,4)+"-"+yearcomb[1].substring(2, 4);
		/*	String yr=yearcomb[0].split("")[2]+""+yearcomb[0].split("")[3]+"-"+yearcomb[1].split("")[2]+""+yearcomb[1].split("")[3];*/
			addSeqNoToHisPayment(paymentid, yr+"/"+maxSeqNo);
		    
		    
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	public String getSeqNoForKunal(String commencingDate, String invid, String itype) {
		String abrivation = "", prefix = "";
		//date in dd-mm-yyyy
		try {
			PreparedStatement ps= connection.prepareStatement("  select  f_seq_no from apm_charges_invoice where id='"+invid+"'  ");
			ResultSet rs=ps.executeQuery();
			while (rs.next()) {
				abrivation=rs.getString(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return abrivation;
	}

	
	public void generateSeqOfBill(String billid, String itype) {
		try {
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
			}else if(itype.equals("10")){
				type="RB";
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
		    int maxSeqNo=getMaxBillSeqNo(yearrangenew,type);
		    maxSeqNo=maxSeqNo+1;
		    inserLatestSeqNoOfBill(yearrangenew, maxSeqNo, type);
		    String yearcomb[]=yearrangenew.split("-");
			//2019-2020 will be 1920
			String yr =yearcomb[0].substring(2,4)+"-"+yearcomb[1].substring(2, 4);
			String seqno="";
			seqno=type+yr+"/"+maxSeqNo;
			saveSeqToBill(billid, seqno);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public int getMaxBillSeqNo(String year, String type){
		int seqno=0;
		try {
			PreparedStatement ps=connection.prepareStatement("select max(maxseq) from invoice_seq_table where year='"+year+"' and itype='"+type+"' ");
			ResultSet rs=ps.executeQuery();
			while (rs.next()) {
				seqno=rs.getInt(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return seqno;
	}
	
	public int inserLatestSeqNoOfBill(String year,int sequence,String type){
		int res=0;
		try {
			PreparedStatement ps= connection.prepareStatement(" insert into invoice_seq_table(year,maxseq,itype) values(?,?,?) ");
			ps.setString(1, year);
			ps.setInt(2, sequence);
			ps.setString(3, type);
			res=ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return res;
	}
	
	private int saveSeqToBill(String billno, String seqno){
		int res=0;
		try {
			PreparedStatement ps= connection.prepareStatement("   update apm_charges_invoice set f_seq_no='"+seqno+"' where id='"+billno+"'  ");
			res=ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return res;
	}
	
	
	public void setVacinationChargeprocedure(String invoiceid,String clientid){
		try {
			PreparedStatement ps= connection.prepareStatement(" select chargeId,commencing from apm_invoice_assesments where masterchargetype='VACCINATION' and clientId='"+clientid+"' ");
			ResultSet rs=ps.executeQuery();
			while (rs.next()) {
				String date=rs.getString(2);
				String vaccine=rs.getString(1);
				PreparedStatement ps2=connection.prepareStatement("select id from apm_vacination_master where charge_name='"+vaccine+"' and  charge_name!='null' ");
				ResultSet rs2=ps2.executeQuery();
				while (rs2.next()) {
					PreparedStatement ps3= connection.prepareStatement(" update apm_vacination_data set givendate='"+date+"' where clientid='"+clientid+"' and masterid='"+rs2.getInt(1)+"' ");
					int x= ps3.executeUpdate();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public Accounts getPaymentAgainstlist(String invoiceid) {
		Accounts accounts= null;
		try {
			 String sql="SELECT  id,debit,credit_note,date,refinvoiceid,payment_mode FROM apm_credit_account where refinvoiceid='"+invoiceid+"'";
			 PreparedStatement ps=connection.prepareStatement(sql);
			 ResultSet rs=ps.executeQuery();
			 while (rs.next()) {
				accounts=new Accounts();
				
				accounts.setId(rs.getInt(1));
				accounts.setAmountx(DateTimeUtils.changeFormat(rs.getDouble(2)));
				accounts.setNotes(rs.getString(3));
				String date = rs.getString(4);
				String temp[] = date.split(" ");
				accounts.setDate(DateTimeUtils.getCommencingDate1(temp[0]));
				accounts.setAdvref(1);
				accounts.setPhysical_payment_id(getPhysicalpaymentIdAdvRef(rs.getString(1)));
				accounts.setPaymentmode(rs.getString(6));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return accounts;
	}

	public double getAdvanceAndRefundAmt(String fromdate, String todate, String userid, String type,String paymode) {
		double amt=0;
		try {
			StringBuffer buffer= new  StringBuffer();
			 buffer.append(" select sum(his_payment_record_physical.amount)  from his_payment_record_physical  ");
			 buffer.append("  inner join apm_credit_account on apm_credit_account.id=adv_ref_id  where adv_or_refund='"+type+"' ");
			 buffer.append(" and his_payment_record_physical.commencing between '"+fromdate+" 00:00:00' and '"+todate+" 23:59:59' ");
			 if(!(userid.equals("0")||userid.equals(""))){
				 buffer.append("  and userid='"+userid+"' ");
			 }
			 if(type.equals("Advance")){
				 buffer.append("  and apm_credit_account.payment_mode !='prepayment' ");
			 }
			 paymode=DateTimeUtils.isNull(paymode);
			 if(!(paymode.equals("")||paymode.equals("0"))){
				 buffer.append(" and payment_mode='"+paymode+"' ");
			 }
			 
			 PreparedStatement ps= connection.prepareStatement(buffer.toString());
			 ResultSet rs=ps.executeQuery();
			 while (rs.next()) {
				amt= Math.round((rs.getDouble(1)*100.0)/100.0);
			}
			 
			 
		} catch (Exception e) {
			e.printStackTrace();
		}
		return amt;
	}

	public String getOpdPracName(String clientId) {
		String res="";
		try {
			
						PreparedStatement ps=connection.prepareStatement("select diaryusername from apm_available_slot where clientid='"+clientId+"' order by commencing desc ");
						ResultSet rs=ps.executeQuery();
						while (rs.next()) {
							res=rs.getString(1);
						}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return res;
	}

	public double getRequestedDiscountAmt(int invoiceid, int i) {
		double amt =0;
		try {
			StringBuffer buffer = new StringBuffer();
			buffer.append("select disc_type, disc_amount, invoiceamount from discount_request ");
			buffer.append("inner join apm_charges_invoice on apm_charges_invoice.id = invoiceid ");
			buffer.append("where disc_request='"+i+"' ");
			buffer.append("and invoiceid='"+invoiceid+"' ");
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

	public boolean getRequestedDiscountStatus(int invoiceid) {
		boolean amt =false;
		try {
			StringBuffer buffer = new StringBuffer();
			buffer.append("select disc_type, disc_amount, invoiceamount from discount_request ");
			buffer.append("inner join apm_charges_invoice on apm_charges_invoice.id = invoiceid ");
			buffer.append("where disc_request in (1,2) ");
			buffer.append("and invoiceid='"+invoiceid+"' ");
			PreparedStatement preparedStatement = connection.prepareStatement(buffer.toString());
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				amt = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return amt;
	}

	public double getRequestedDiscountAmount(int invoiceid) {
		double amt =0;
		try {
			StringBuffer buffer = new StringBuffer();
			buffer.append("select disc_type, disc_amount, invoiceamount from discount_request ");
			buffer.append("inner join apm_charges_invoice on apm_charges_invoice.id = invoiceid ");
			buffer.append("where disc_request in (1,2) ");
			buffer.append("and invoiceid='"+invoiceid+"' ");
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

	public double getInvoiceBalanceAmount(String id) {
		PreparedStatement preparedStatement = null;
		DateTimeUtils dateTimeUtils = new DateTimeUtils();
		ChargesAccountProcessingDAO chargesAccountProcessingDAO = new JDBCChargeAccountProcesDAO(connection);
		StatementDAO statementDAO = new JDBCStatementDAO(connection);
		double result = 0.0;
		try{
			String sql = "";
			sql = "select id,payby,date,clientid,debit,deliverstatus,discount,isdeleted,disctype,discamt,cancelNotes,disc_approve,disc_request,iclosed,ipost from apm_charges_invoice where  id = "+id+" ";
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()){
				int disctype = rs.getInt(9);
				double discamt = rs.getDouble(10);
				double debit = rs.getDouble(5);
				double total = rs.getDouble(5);
				double discount = rs.getDouble(7);
				double r1 = (total*discount)/100;
				if(disctype==1){
					r1 = discamt;
				}
				total = total-r1;
				result = result + total;
				
				double creditAmount = chargesAccountProcessingDAO.getCreditAmount(rs.getDouble(1));
				//if refund against invoice
				double refundAmt = statementDAO.getRefundAmtAgainsInvoice(rs.getInt(1));
				creditAmount = creditAmount - refundAmt;
				double balance = result - creditAmount;
				result = balance;
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return result;

	}

	public double getInRequestRefundBalance(String id) {
		double amt =0;
		try {
			StringBuffer buffer = new StringBuffer();
			buffer.append("select sum(charge*quantity) from refund_request_parent ");
			buffer.append("inner join refund_request_child on refund_request_child.parentid =refund_request_parent.id ");
			buffer.append("where manualinvoiceid="+id+" and isfromdisc=0 and status in (0,1) ");
			buffer.append("group by manualinvoiceid ");
			PreparedStatement preparedStatement = connection.prepareStatement(buffer.toString());
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				amt = rs.getDouble(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return amt;
	}

	public int resetAppliedDiscount(int invoiceid) {
		PreparedStatement preparedStatement = null;
		int result = 0;
		String sql = "update apm_charges_invoice set disctype=0,discamt=0 where id="+invoiceid+" ";
		
		try{
			
			preparedStatement = connection.prepareStatement(sql);
			result = preparedStatement.executeUpdate();
			
		}catch (Exception e) {
			// TODO: handle exception
		}
		return result;
	}

	public int updatechargePaymrnt(int invoiceid) {
		int id=0;
		double payment=0;
		int result=0;
		try {
			String sql1="select id,payment from apm_charges_payment where chargeinvoiceid="+invoiceid+"";
			PreparedStatement ps1=connection.prepareStatement(sql1);
			ResultSet rs=ps1.executeQuery();
			while(rs.next()){
				id=rs.getInt(1);
				payment=rs.getDouble(2);
			
			String sql="update apm_charges_payment set payment=0,cancelinv=1,original_pay="+payment+" where id="+id+"";
			PreparedStatement ps=connection.prepareStatement(sql);
			
			result=ps.executeUpdate();
			}
		} catch (Exception e) {

			e.printStackTrace();
		}
		
		return result;
	}

	public int updatecreditaccountsts(int crinvoiceid) {

		int result=0;
		try {
			String sql="update apm_credit_account set cancelpay=1 where id="+crinvoiceid+"";
			PreparedStatement ps=connection.prepareStatement(sql);
			
			result=ps.executeUpdate();
			
		} catch (Exception e) {

			e.printStackTrace();
		}
		
		return result;
	}

	public AppointmentType getFollowUpIdCharge(String whopay) {
		AppointmentType appointment= new AppointmentType();
		try {
			String sql="";
			if(whopay.equals(Constants.PAY_BY_CLIENT)){
				sql="select id,name,duration,charges from apm_appointment_type  where  isfollowupcharge='1' and tpid=0" ;
			}else if (whopay.equals(Constants.PAY_BY_CLIENT)) {
				sql="select id,name,duration,charges from apm_appointment_type  where  isfollowupcharge='1' and tpid>0" ;
			}else{
				sql="select id,name,duration,charges from apm_appointment_type  where  isfollowupcharge='1'" ;
			}
			 
			PreparedStatement ps=connection.prepareStatement(sql);
			ResultSet rs=ps.executeQuery();
			while (rs.next()) {
				appointment.setId(rs.getInt(1));
				appointment.setName(rs.getString(2));
				appointment.setDuration(rs.getString(3));
				appointment.setCharges(""+rs.getDouble(4));
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return appointment;
	}

	public int getInvoiceIdOfApmt(String apmtId) {
		int invoiceid=0;
		try {
			PreparedStatement ps= connection.prepareStatement(" select id from apm_invoice where appointmentid='"+apmtId+"'  ");
			ResultSet rs=ps.executeQuery();
			int res=0;
			while (rs.next()) {
				res=rs.getInt(1);
			}
			if(res>0){
				PreparedStatement ps2= connection.prepareStatement("select chargeinvoiceid from apm_charges_assesment where invoiceid='"+res+"'	");
				ResultSet rs2=ps2.executeQuery();
				while (rs2.next()) {
					invoiceid=rs2.getInt(1);
				}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return invoiceid;
	}

	public int getPaymentammount(String invoiceid) {
		int res=0;
		try {
			String sql="";
			if(!invoiceid.equals("0")) {
			  sql="select  sum(payment) from apm_charges_payment where  chargeinvoiceid='"+invoiceid+"' ";
			}
			PreparedStatement ps=connection.prepareStatement(sql);
			ResultSet rs=ps.executeQuery();
			while (rs.next()) {
				res=rs.getInt(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return res;
	}

	public double getDiscamtofInvoice(String invoiceid) {
		double discamt=0;
		try {
			PreparedStatement ps= connection.prepareStatement(" select disctype,discount,discamt,debit from apm_charges_invoice where id='"+invoiceid+"'");
			ResultSet rs=ps.executeQuery();
			while (rs.next()) {
				if(rs.getInt(1)==1){
					discamt=rs.getDouble(3);
				}else{
					double debit=rs.getDouble(4);
					discamt=(rs.getDouble(2)/100.0)*debit;
					
				}
			}
			discamt=Math.round((discamt*100.0)/100.0);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return discamt;
	}

	public double getDebitfromInvoice(String invoiceid) {
		double amt=0;
		try {
			PreparedStatement ps= connection.prepareStatement(" select debit from apm_charges_invoice where id='"+invoiceid+"' and isdeleted='0' ");
			ResultSet rs=ps.executeQuery();
			while (rs.next()) {
				amt=rs.getDouble(1);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return amt;
	
	}

	public Accounts getonofftime(int result,String ipdid) {
			Accounts accounts= new Accounts();
			try {
				String sql="select ondatetime, offdatetime from apm_std_onoff_charge  where ipdid="+ipdid+" and chargeid="+result+" and status=1 ";
				PreparedStatement ps=connection.prepareStatement(sql);
				ResultSet rs=ps.executeQuery();
				while (rs.next()) {
					accounts.setOndatetime(rs.getString(1));
					accounts.setOffdatetime(rs.getString(2));
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			return accounts;
	}
	public Accounts showonofftime(int result,String ipdid) {
		Accounts accounts= new Accounts();
		try {
			String sql="select ondatetime, offdatetime,id from apm_std_onoff_charge  where ipdid="+ipdid+" and chargeid="+result+"  ";
			PreparedStatement ps=connection.prepareStatement(sql);
			ResultSet rs=ps.executeQuery();
			while (rs.next()) {
				accounts.setOndatetime(rs.getString(1));
				accounts.setOffdatetime(rs.getString(2));
				accounts.setId(rs.getInt(3));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return accounts;
}

	public int savesmstemplate(String msg,String tempname) {
		int res=0;
		try {
			PreparedStatement ps= connection.prepareStatement(" insert into sms_template(template_name,template_msg) values(?,?) ");
			ps.setString(1, tempname);
			ps.setString(2, msg);
			res=ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return res;
	}

	public String getsmstemplate(String id) {
		String res="";
		try {
			PreparedStatement ps= connection.prepareStatement("select  template_msg from sms_template where  id='"+id+"' ");
			ResultSet rs=ps.executeQuery();
			while (rs.next()) {
				res=rs.getString(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return res;
	}

	public ArrayList<Accounts> getDiscountedCharges(Accounts accounts) {
		
		double totcharge=0;
		ArrayList<Accounts> list=new ArrayList<Accounts>();
		try {
			String sql="select id,apmtType,charge,clientId,ipdid,masterchargetype,wardid,quantity from apm_invoice_assesments  where ipdid="+accounts.getIpdid()+" "
					+ "and clientId="+accounts.getClientid()+" and masterchargetype='"+accounts.getMasterchargetype()+"' and apmtType='"+accounts.getAptmname()+"' and wardid='"+accounts.getWardid()+"' and charge="+accounts.getUnitcharges()+" and invoiced=0 ";
			PreparedStatement ps=connection.prepareStatement(sql);
			ResultSet rs=ps.executeQuery();
			while (rs.next()) {
				Accounts accounts2= new Accounts();
				accounts2.setId(rs.getInt(1));
				accounts2.setAptmname(rs.getString(2));
				accounts2.setCharges(rs.getString(3));
				accounts2.setClientid(rs.getInt(4));
				accounts2.setIpdid(rs.getString(5));
				accounts2.setMasterchargetype(rs.getString(6));
				accounts2.setWardid(rs.getString(7));
				totcharge=totcharge+rs.getDouble(3);
				accounts2.setQuantity(rs.getInt(8));
				accounts2.setTotalAmount(totcharge);
				list.add(accounts2);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	public int insertChildDiscData(Accounts acc,String disctype,int parentid,double disc) {
		PreparedStatement preparedStatement = null;
		double discamt=0;
		double discountedamt=0;
		int result = 0;
		String sql = "insert into child_discount_request(parent_discreq_id, child_commencing, child_apmttyp, child_apmtname, child_ipdid, child_clientid, "
				+ "child_unitcharge, child_disctype, child_discount, child_chargedisc, child_wardid,assesment_id, child_discamt,child_quantity) "
				+ "value(?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		
		try{
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, parentid);
			preparedStatement.setString(2, acc.getCommencing());
			preparedStatement.setString(3, acc.getMasterchargetype());
			preparedStatement.setString(4, acc.getAptmname());
			preparedStatement.setString(5, acc.getIpdid());
			preparedStatement.setInt(6, acc.getClientid());
			double sumcharge=acc.getQuantity()*Double.parseDouble(acc.getCharges());
			preparedStatement.setString(7, String.valueOf(sumcharge));
			preparedStatement.setInt(8, Integer.parseInt(disctype));
			preparedStatement.setDouble(9, disc);
			
			if(disctype.equals("0")){
				 discamt=(Double.parseDouble(acc.getCharges())/100)*disc;
				 if(acc.getQuantity()>1){
						discamt=discamt*acc.getQuantity();
					}
				 discountedamt=sumcharge-discamt;
			}else{
				if(acc.getQuantity()>1){
					disc=disc*acc.getQuantity();
				}
				discamt=disc;
				discountedamt=sumcharge-discamt;
			}
			preparedStatement.setDouble(10, discamt);
			preparedStatement.setDouble(13, discountedamt);
			preparedStatement.setString(11, acc.getWardid());
			preparedStatement.setInt(12, acc.getId());
			preparedStatement.setInt(14, acc.getQuantity());
			result = preparedStatement.executeUpdate();
			int res=updatediscreq(acc.getId());
		}catch (Exception e) {
			e.printStackTrace();
		}
		return result;

	}

	private int updatediscreq(int id) {
		int result=0;
		try {
			String sql="update apm_invoice_assesments set discreq=1 where id="+id+"";
			PreparedStatement ps=connection.prepareStatement(sql);
			
			result=ps.executeUpdate();
			
		} catch (Exception e) {

			e.printStackTrace();
		}
		
		return result;
	}

	public int insertDiscountRequest(LoginInfo loginInfo, String disctype,String clientid) {
		PreparedStatement preparedStatement = null;
		int result=0;
		int parentid=0;
		String sql="insert into discount_request(invoiceid,requested_userid, requested_date,disc_type,clientid) values(?,?,?,?,?)";
		
		try{
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, 0);
			preparedStatement.setString(2, loginInfo.getUserId());
			String date = DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone());
			preparedStatement.setString(3, date);
			preparedStatement.setString(4, disctype);
			preparedStatement.setString(5, clientid);
			result = preparedStatement.executeUpdate();
			
			if(result == 1){
				ResultSet resultSet = preparedStatement.getGeneratedKeys();
				if(resultSet.next()){
					parentid = resultSet.getInt(1);
				}
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		return parentid;
	}

	public Accounts gettotoalinvdisc(int parentid) {
		Accounts accounts= new Accounts();
		try {
			String sql="select sum(child_unitcharge),sum(child_chargedisc) from child_discount_request  where parent_discreq_id="+parentid+" ";
			PreparedStatement ps=connection.prepareStatement(sql);
			ResultSet rs=ps.executeQuery();
			while (rs.next()) {
				accounts.setTotalAmount(rs.getDouble(1));
				accounts.setTotaldiscountammount(rs.getDouble(2));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return accounts;
	}

	public int updateDiscountRequest(Accounts accounts, int parentid) {
		int result=0;
		try {
			String sql="update discount_request set disc_amount=?,invoiceamount=? where id="+parentid+"";
			PreparedStatement ps=connection.prepareStatement(sql);
			ps.setString(1, accounts.getInddiscounttotal());
			ps.setDouble(2,accounts.getTotalAmount());
			result=ps.executeUpdate();
			
		} catch (Exception e) {

			e.printStackTrace();
		}
		
		return result;
	}

	public Accounts getsinglediscountcharge(Accounts accounts) {
		Accounts accounts2= new Accounts();
		double totcharge=0;
		ArrayList<Accounts> list=new ArrayList<Accounts>();
		try {
			String sql="select id,apmtType,charge,clientId,ipdid,masterchargetype,wardid,quantity from apm_invoice_assesments  where id="+accounts.getChargeid()+" and invoiced=0";
			PreparedStatement ps=connection.prepareStatement(sql);
			ResultSet rs=ps.executeQuery();
			while (rs.next()) {
				accounts2.setId(rs.getInt(1));
				accounts2.setAptmname(rs.getString(2));
				accounts2.setCharges(rs.getString(3));
				accounts2.setClientid(rs.getInt(4));
				accounts2.setIpdid(rs.getString(5));
				accounts2.setMasterchargetype(rs.getString(6));
				accounts2.setWardid(rs.getString(7));
				totcharge=totcharge+rs.getDouble(3);
				accounts2.setTotalAmount(totcharge);
				accounts2.setQuantity(rs.getInt(8));
				list.add(accounts2);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return accounts2;
	}

	public ArrayList<Accounts> getdiscountcharges(String parentid) {
		ArrayList<Accounts> list=new ArrayList<Accounts>();
		try {
			String sql="select child_apmttyp,child_apmtname,sum(child_unitcharge),sum(child_chargedisc),sum(child_quantity),child_discount,child_disctype"
					+ " from child_discount_request  where parent_discreq_id="+parentid+" group by child_apmttyp,child_apmtname ";
			PreparedStatement ps=connection.prepareStatement(sql);
			ResultSet rs=ps.executeQuery();
			while (rs.next()) {
				Accounts accounts= new Accounts();
				accounts.setMasterchargetype(rs.getString(1));
				accounts.setAptmname(rs.getString(2));
				accounts.setTotalAmount(rs.getDouble(3));
				accounts.setTotalDisc(rs.getDouble(4));
				accounts.setQuantity(rs.getInt(5));
				accounts.setDiscount(rs.getDouble(6));
				accounts.setDisctype(rs.getString(7));
				list.add(accounts);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	public Accounts getStdChargeChildData(int id) {
		Accounts accounts = new Accounts();
		try {
			String sql ="select id, child_masterid, child_parentid, onuserid, offuserid, oncommencing, offcommencing, ondate, offdate from apm_std_onoff_charge_child where child_parentid="+id+" order by id desc limit 1 ";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				accounts.setId(rs.getInt(1));
				accounts.setOndatetime(rs.getString(8));
				accounts.setOffdatetime(rs.getString(9));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return accounts;
	}

	public int insertStdChargeChild(String chargeid, String parentid, String userId, String ukCurrentDataTime,
			String ondatetime) {
		PreparedStatement preparedStatement = null;
		int result=0;
		try{
			String sql="insert into apm_std_onoff_charge_child(child_masterid,child_parentid, onuserid,oncommencing,ondate) values(?,?,?,?,?)";
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, chargeid);
			preparedStatement.setString(2, parentid);
			preparedStatement.setString(3, userId);
			preparedStatement.setString(4, ukCurrentDataTime);
			preparedStatement.setString(5, ondatetime);
			result = preparedStatement.executeUpdate();
			if(result == 1){
				ResultSet resultSet = preparedStatement.getGeneratedKeys();
				if(resultSet.next()){
					result = resultSet.getInt(1);
				}
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public int updateStdChargeChild(String userId, String ukCurrentDataTime, String childid, String offdatetime) {
		int result=0;
		try {
			String sql="update apm_std_onoff_charge_child set offuserid=?,offcommencing=?,offdate=? where id="+childid+"";
			PreparedStatement ps=connection.prepareStatement(sql);
			ps.setString(1, userId);
			ps.setString(2, ukCurrentDataTime);
			ps.setString(3, offdatetime);
			result=ps.executeUpdate();
			
		} catch (Exception e) {

			e.printStackTrace();
		}
		
		return result;
	}

	public int updateAssesmentData(CompleteAppointment completeAppointment) {
		int result=0;
		try {
			String sql="update apm_invoice_assesments set charge=?,chargedisc=?,unitcharge=?,discrs=?, discper=?,discapprove=? where id="+completeAppointment.getId()+"";
			PreparedStatement ps=connection.prepareStatement(sql);
			ps.setString(1, completeAppointment.getCharges());
			ps.setString(2, completeAppointment.getChargedisc());
			ps.setString(3, completeAppointment.getUnitcharge());
			if(completeAppointment.getDisc_type().equals("0")){
				ps.setString(4, "0");
				ps.setDouble(5, completeAppointment.getDiscount());
			}else{
				ps.setString(5, "0");
				ps.setDouble(4, completeAppointment.getDiscount());
			}
			ps.setString(6, "1");
			result=ps.executeUpdate();
			
		} catch (Exception e) {

			e.printStackTrace();
		}
		
		return result;
	}

	public ArrayList<Master>getIndDisMasterAssessmentList(String clientid,String curdate,LoginInfo loginInfo,boolean isprntchrg,String ipdid){
		PreparedStatement preparedStatement = null;
		ArrayList<Master>list = new ArrayList<Master>();
		StringBuffer sql = new StringBuffer();
		
		//sql.append("SELECT masterchargetype FROM apm_invoice_assesments where clientid = "+clientid+" group by masterchargetype ");
		sql.append("SELECT masterchargetype,std_charge_id FROM apm_invoice_assesments ");
		sql.append("inner join apm_invoice on apm_invoice.id = apm_invoice_assesments.invoiceid ");
		sql.append("where apm_invoice_assesments.clientid = "+clientid+" and chargetype!='Submit' ");
		if(ipdid!=null){
			if(!ipdid.equals("")){
				sql.append("and apm_invoice_assesments.ipdid='"+ipdid+"' ");
			}
		}
		sql.append("group by masterchargetype order by sqno ");
		
		
		try{
			preparedStatement = connection.prepareStatement(sql.toString());
			ResultSet  rs = preparedStatement.executeQuery();
			double dchtotal  = 0;
			int i=0;
			while(rs.next()){
				Master master = new Master();
				master.setName(rs.getString(1));
				if(rs.getInt(2)>0){
					master.setStdchargests(true);
				}else{
					master.setStdchargests(false);
				}
				String breakage =  getMasterBreakage(master.getName());
				master.setKrackage(breakage);;
		
				
				Vector<Accounts> assesmentList = getIndDiscFilteredPreviewChargesList(master.getName(),clientid,curdate,loginInfo,isprntchrg,ipdid);
				master.setAssesmentList(assesmentList);
				
				double subtotal = getdMasterSubtotal(master.getName(),clientid,ipdid);
				master.setCharge(DateTimeUtils.changeFormat(subtotal));
				//  25 Sep 2018 to hide subtotal from main total if there all charges all submited in charge detailsss
				boolean flag = checkChargeSubmited(master.getName(),clientid,curdate);
				if(flag){
					dchtotal = dchtotal + subtotal;
				}
				master.setHidecode(i);
				i=i+1;
				master.setDchargetotal(DateTimeUtils.changeFormat(dchtotal));
				list.add(master);
			}
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		return list;
	}

	private Vector<Accounts> getIndDiscFilteredPreviewChargesList(String name, String clientid, String curdate,
			LoginInfo loginInfo, boolean isprntchrg, String ipdid2) {

		PreparedStatement preparedStatement = null;
		Vector<Accounts>list = new Vector<Accounts>();
		BedDao bedDao = new JDBCBedDao(connection);
		IpdDAO ipdDAO = new JDBCIpdDAO(connection);
		/*String sql = "SELECT apmtType,charge,practitionerName,practitionerId,paybuy,id,commencing,appointmentid,quantity,apm_invoice_assesments.id,apm_invoice_assesments.chargeid,apm_invoice_assesments.std_charge_id,ipdid,newshftcharge,logid,wardid  FROM apm_invoice_assesments" +
				" where masterchargetype = '"+name+"' and clientid="+clientid+" ";*/
		
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT apmtType,charge,apm_invoice_assesments.practitionerName,apm_invoice_assesments.practitionerId,paybuy, ");
		sql.append("apm_invoice_assesments.id,commencing,apm_invoice_assesments.appointmentid,quantity,apm_invoice_assesments.id, ");
		sql.append("apm_invoice_assesments.chargeid,apm_invoice_assesments.std_charge_id,apm_invoice_assesments.ipdid,newshftcharge,logid,wardid,pkgcode");
		if(isprntchrg){
			if(!name.equals("IMPLANTS/CONSUMABLES")){
				sql.append(" ,sum(quantity)");
				}
		;
		}
		sql.append(" FROM apm_invoice_assesments ");
		sql.append("inner join apm_invoice on apm_invoice.id = apm_invoice_assesments.invoiceid ");
		sql.append(" where masterchargetype = '"+name+"' and apm_invoice_assesments.clientid="+clientid+" and discreq=0 and discapprove=0 ");
		if(ipdid2!=null){
			if(!ipdid2.equals("")){
				sql.append("and apm_invoice_assesments.ipdid='"+ipdid2+"' ");
			}
		}
		sql.append("and chargetype!='Submit' ");
		if(isprntchrg){
			if(!name.equals("IMPLANTS/CONSUMABLES")){
			sql.append("group by apmtType,charge ");
			}
		}
		sql.append("order by commencing asc ");
		
		
		ClientDAO clientDAO = new JDBCClientDAO(connection);
		Client client = clientDAO.getClientDetails(clientid);
		ThirdPartyDAO thirdPartyDAO = new JDBCThirdPartyDAO(connection);
		try{
			preparedStatement = connection.prepareStatement(sql.toString());
			ResultSet rs = preparedStatement.executeQuery();
			String tempcurdate = curdate;
			int quantity=0;
			while(rs.next()){
				String masterchargetype = name;
				String apptName = rs.getString(1);
				String  sectionroom = "";
				if(masterchargetype.equals("Pathology") || masterchargetype
						.equals("Radiology")){

					 sectionroom = getSectionRoom(apptName);
					if(apptName.contains("~")){
						String tmp[] = apptName.split("~");
						apptName = tmp[0];
					}
					
					//set investigation alternate name
					String tpid = client.getTypeName();
					ThirdParty thirdParty = thirdPartyDAO.getThirdPartyDetails(tpid);
					
						String alternamename = getInvestigationAltName(rs.getString(1));
						if(alternamename !=null){
							if(!alternamename.equals("")){
								apptName = alternamename;
							}
						}
					}

				
				//String apptName = rs.getString(1);
				String charge = rs.getString(2);
				if(charge==null){
					charge = "0.0";
				}
				String practitionerName = rs.getString(3);
				String practitionerid = rs.getString(4);
				int payby = rs.getInt(5);
				int addtitonalCharge = 1;
				String chargeid = rs.getString(6);
				String date = rs.getString(7);
				int apptid = rs.getInt(8);
				if(isprntchrg){
					if(!name.equals("IMPLANTS/CONSUMABLES")){
					 quantity = rs.getInt(18);
					}else{
						 quantity = rs.getInt(9);
					}
				}else{
				 quantity = rs.getInt(9);
				}
				int subchargeid = rs.getInt(10);
				int apmtchargeid = rs.getInt(11);;
				String ipdid = rs.getString(13);
				String newshftcharge = rs.getString(14);
				String logid = rs.getString(15);
				
				Accounts accounts = new Accounts();
				
			/*	if(addtitonalCharge==0){
					Accounts acc = getAppointmentDetailsl(chargeid);
					if(acc.getCommencing()!=null){
						accounts.setCommencing(DateTimeUtils.getInvoiceCommencingDate(acc.getCommencing()));
						accounts.setAppointmentType(acc.getTreatmentEpisodeName() + " " +apptName);
					}
					
					
					boolean dna = getDna(apptid);
					accounts.setDna(dna);
					
				}else{
					String invoicedate = date;
					accounts.setCommencing(DateTimeUtils.getInvoiceCommencingDate(invoicedate));
					accounts.setAppointmentType("Additional Charge("+apptName+")");
				}*/
				
				accounts.setCharges(DateTimeUtils.changeFormat(Double.parseDouble(charge)));
				accounts.setChargeTotal(DateTimeUtils.changeFormat(Double.parseDouble(charge)*quantity));
				accounts.setPractitionerName(practitionerName);
				String practitionerFullName = getPractitionerFullName(practitionerid);
				accounts.setPractitionerName(practitionerFullName);
				accounts.setQuantity(quantity);
				accounts.setIpdid(ipdid);
				accounts.setStdchargeid(rs.getString(12));
				accounts.setAssesmentid(Integer.toString(subchargeid));
				accounts.setCommencing(DateTimeUtils.getCommencingDate1(date));
				
				boolean isSTdCharge =getIfSTdCharge(rs.getString(12));
				accounts.setStdcharge("0");
				accounts.setCurstatus(0);
				accounts.setNewshftcharge(newshftcharge);
				accounts.setOriginalchargename(rs.getString(1));

				Bed bed = getBedLogData(logid);
				String wardname=ipdDAO.getIpdWardName(bed.getWardid());
				String bedname = ipdDAO.getIpdBedName(bed.getBedid());
				accounts.setWard(wardname + " / " + bedname);
				accounts.setWardid(rs.getString(16));
				if(logid==null){
					logid = "0";
				}else if(logid.equals("")){
					logid = "0";
				}
				
				if(!logid.equals("0")){
					String lastShiftedLog = getlastShiftedLog(ipdid,logid);
					
					accounts.setCommencing(bed.getLastmodified());
					if(!lastShiftedLog.equals("")){
						String temp[] = lastShiftedLog.split(" ");
						curdate = DateTimeUtils.getCommencingDate1(temp[0]) ;
					}else{
						curdate = tempcurdate;
					}
				}
				
				String pname = "";
				if(practitionerid!=null ){
					UserProfileDAO userProfileDAO = new JDBCUserProfileDAO(connection);
					UserProfile userProfile = userProfileDAO.getUserprofileDetails(Integer.parseInt(practitionerid));
					pname = userProfile.getInitial() + " " + userProfile.getFirstname() + " " + userProfile.getLastname();
				}
				

				
				if(isSTdCharge){
					
					String startEndTime= getStdChargeStartEndTime(subchargeid,ipdid); 
					
					
					
					if(startEndTime!=null){
						accounts.setStdcharge("1");
						String tempc[] = startEndTime.split("~");
						if(tempc.length>1){
							String t1[] = tempc[0].split(",");
							String t2[] = tempc[1].split(",");
							String resultdatetime = "";
							for(int i=0;i<t2.length;i++){
								resultdatetime = resultdatetime + t1[i] + " - " + t2[i] + " , "; 
							}
							if(resultdatetime.length()!=0){
								resultdatetime = resultdatetime.substring(0,resultdatetime.length()-2);
							}
							int curstatus = getstdonoffcurstatus(subchargeid,ipdid);
							accounts.setCurstatus(curstatus);
							if(curstatus==1){
								resultdatetime = resultdatetime + " , " + t1[t1.length-1];
							}
							if(!pname.equals("")){
								//accounts.setAppointmentType(pname + " / " + apptName+" ("+resultdatetime+") ");
								accounts.setAppointmentType(apptName+" ("+resultdatetime+") ");
							}else{
								accounts.setAppointmentType(apptName+" ("+resultdatetime+") ");
							}
							
						}else{
							accounts.setAppointmentType(apptName + " ("+tempc[0]+")");
							int curstatus = getstdonoffcurstatus(subchargeid,ipdid);
							accounts.setCurstatus(curstatus);
						}
						
					} else {
						accounts.setAppointmentType(apptName + " ("+accounts.getCommencing()+" - "+curdate+")");
					}
					
				}else {
					if(!logid.equals("0")){
						accounts.setAppointmentType(apptName + " ("+accounts.getCommencing()+" - "+curdate+")");
					}else{
						accounts.setAppointmentType(apptName + " ("+accounts.getCommencing()+"");
					}
					
				}
				accounts.setAptmname(apptName);
				accounts.setPayBy(payby);
				accounts.setChargeid(chargeid);
				//accounts.setAppointmentType(apptName);
				if(rs.getString(17).equals("0")){
					
				String codeforapt=getcodeforapt(rs.getString(12),apmtchargeid);
				if(loginInfo.getIskunal()==1 ){
					if(codeforapt==null){
						codeforapt="";
					}
					else if(codeforapt.equals("")){
						
							accounts.setApmtcode("NO CODE");
						
					
					}else{
						accounts.setApmtcode(codeforapt);
					}				
				
				}else{
					if(codeforapt==null){
						
						accounts.setApmtcode("");
					}else{
						accounts.setApmtcode(rs.getString(17));
					}
				}
				
				}else{
					accounts.setApmtcode(rs.getString(17));
				}
				
				
				
		
				list.add(accounts);
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
		return list;
	

	}

	public int updatestdOnFFDateTime(String ipdid, String stdchrgeId, String date, String onoroff) {
		//onoroff 0 for on
		int res=0;
		try {
			String col="";
			if(onoroff.equals("0")){
				col="ondatetime";
			}else{
				col="offdatetime";
			}
			String sql=" update apm_std_onoff_charge set "+col+"=?  where ipdid='"+ipdid+"' and chargeid='"+stdchrgeId+"'";
			PreparedStatement ps= connection.prepareStatement(sql);
			ps.setString(1, date);
			res=ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return res;
	}

	public int updateChildOnOffDatetime(String datetime, String childid, String onoroff) {
		//onoroff 0 for on
				int res=0;
				try {
					String x[]=datetime.split(" ");
					String col="",col1="";
					if(onoroff.equals("0")){
						col="ondate";
						col1="oncommencing";
					}else{
						col="offdate";
						col1="offcommencing";
					}
					String sql=" update apm_std_onoff_charge_child set "+col+"=? , "+col1+"=?  where id='"+childid+"'";
					PreparedStatement ps= connection.prepareStatement(sql);
					ps.setString(1, datetime);
					ps.setString(2, DateTimeUtils.getCommencingDate1(x[0])+" "+x[1]);
					res=ps.executeUpdate();
				} catch (Exception e) {
					e.printStackTrace();
				}
				return res;
	}

	public ArrayList<Accounts> getOnOffChildList(String parentid) {
		ArrayList<Accounts> list= new ArrayList<Accounts>();
		try {
			String sql="select id, ondate,offdate from apm_std_onoff_charge_child where child_parentid='"+parentid+"' and child_parentid!='0' ";
			PreparedStatement ps= connection.prepareStatement(sql);
			ResultSet rs=ps.executeQuery();
			while (rs.next()) {
				Accounts accounts= new Accounts();
				accounts.setChildid(rs.getString(1));
				accounts.setOndatetime(DateTimeUtils.isNull(rs.getString(2)));
				accounts.setOffdatetime(DateTimeUtils.isNull(rs.getString(3)));
				accounts.setId(Integer.parseInt(parentid));
				list.add(accounts);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	public Accounts getOnOffChargesChildDates(String parentid) {
		Accounts accounts= new Accounts();
		try {
			String sql="select id, ondate,offdate from apm_std_onoff_charge_child where child_parentid='"+parentid+"'";
			PreparedStatement ps=connection.prepareStatement(sql);
			ResultSet rs=ps.executeQuery();
			int i=0;
			while (rs.next()) {
				if(i==0){
					accounts.setOndatetime(rs.getString(2));
					accounts.setOffdatetime(rs.getString(3));
				}else{
					accounts.setOndatetime(accounts.getOndatetime()+","+rs.getString(2));
					accounts.setOffdatetime(accounts.getOffdatetime()+","+rs.getString(3));
				}
				i=i+1;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return accounts;
	}

	public int delChildOnOffCharge(String childId) {
		int res=0;
		try {
			PreparedStatement ps= connection.prepareStatement(" delete from apm_std_onoff_charge_child where id='"+childId+"'");
			res=ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return res;
	}
	
	public Accounts getOnOffChargesChildDateNEW(String id) {
		Accounts accounts= new Accounts();
		try {
			String sql="select id, ondate,offdate,child_parentid from apm_std_onoff_charge_child where id='"+id+"'";
			PreparedStatement ps=connection.prepareStatement(sql);
			ResultSet rs=ps.executeQuery();
			int i=0;
			while (rs.next()) {
				if(true){
					accounts.setOndatetime(rs.getString(2));
					accounts.setOffdatetime(rs.getString(3));
					accounts.setParentId(rs.getString(4));
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return accounts;
	}
	
	public Accounts getOnOffChargesParent(String id) {
		Accounts accounts= new Accounts();
		try {
			String sql="select id, ipdid, chargeid, status, assesmentid from apm_std_onoff_charge where id='"+id+"'";
			PreparedStatement ps=connection.prepareStatement(sql);
			ResultSet rs=ps.executeQuery();
			int i=0;
			while (rs.next()) {
				if(true){
					accounts.setId(rs.getInt(1));
					accounts.setIpdid(rs.getString(2));
					accounts.setChargeid(rs.getString(3));
					accounts.setAssesmentid(rs.getString(5));
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return accounts;
	}

	public String getChargeTpid(String chargeid) {
		String result="";
		try {
			String sql ="select thirdPartyId from apm_invoice_assesments where id="+chargeid+"";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				result=rs.getString(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public ArrayList<Accounts> getChargeAssesments(int invoiceid) {
		ArrayList<Accounts> list= new ArrayList<Accounts>();
		try {
			String sql="select id, invoiceid, chargeinvoiceid from apm_charges_assesment where chargeinvoiceid='"+invoiceid+"' group by invoiceid";
			PreparedStatement ps= connection.prepareStatement(sql);
			ResultSet rs=ps.executeQuery();
			while (rs.next()) {
				Accounts accounts= new Accounts();
				accounts.setId(rs.getInt(1));
				accounts.setInvoiceid(rs.getInt(2));
				accounts.setChargeid(rs.getString(3));;
				list.add(accounts);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	public int checkChargeAssements(Accounts accounts) {
		int result=0;
		try {
			String sql ="select id, invoiceid, chargeinvoiceid from apm_charges_assesment where id!="+accounts.getId()+" and invoiceid="+accounts.getInvoiceid()+"";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				result=deleteChargeAssesments(rs.getInt(1));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	private int deleteChargeAssesments(int id) {
		int res=0;
		try {
			PreparedStatement ps= connection.prepareStatement(" delete from apm_charges_assesment where id='"+id+"'");
			res=ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return res;
	}

	public boolean getdiscountapprovests(int invoiceid) {
		boolean res=false;
		try {
			String sql="select discapprove from apm_invoice_assesments  where invoiced="+invoiceid+" ";
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

	public String getinddiscounttotal(int parentid, String str) {
		String result="0";
		try {
			String sql ="select sum(child_chargedisc) from child_discount_request  where parent_discreq_id="+parentid+" and child_discount="+str+" ";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				result=str+"("+rs.getString(1)+")";
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public CompleteAppointment chargeAssementDetails(String assemntId) {
		CompleteAppointment appointment= new CompleteAppointment();
		try {
			String sql="select * from apm_invoice_assesments where id='"+assemntId+"'"; 
			PreparedStatement ps=connection.prepareStatement(sql);
			ResultSet rs=ps.executeQuery();
			while ((rs.next())) {
				appointment.setId(rs.getInt(1));
				appointment.setInvoiceid(DateTimeUtils.isNull(rs.getString("invoiceid")));
				appointment.setApmtType(DateTimeUtils.isNull(rs.getString("apmtType")));
				appointment.setUser(DateTimeUtils.isNull(rs.getString("user")));
				appointment.setCharges(""+(rs.getInt("charge")));
				appointment.setQuantity(rs.getInt("quantity"));
				appointment.setDisc_amount(String.valueOf(rs.getInt("discrs")));
				appointment.setDiscperc(String.valueOf(rs.getInt("discper")));
				appointment.setChargeInvoiceId(DateTimeUtils.convertToInteger(getChargeInvoiceId(appointment.getInvoiceid())));
				appointment.setChargedisc(""+(rs.getInt("chargedisc")));
				appointment.setApmtType(DateTimeUtils.isNull(rs.getString("apmtType")));
				appointment.setMasterchargetype(DateTimeUtils.isNull(rs.getString("masterchargetype")));
				appointment.setCommencing(rs.getString("commencing"));
				appointment.setPractitionerId(rs.getString("practitionerId"));
				appointment.setDuration(rs.getString("duration"));
				appointment.setClientId(rs.getString("clientId"));
				appointment.setAppointmentid(rs.getString("appointmentid"));
				appointment.setPayBuy(rs.getString("paybuy"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return appointment;
	}
	
	
	private double getTotalRefundOverInvoice(int invoiceid){
		double amt=0;
		try {
			StringBuffer buffer= new StringBuffer();
			buffer.append("  select sum(debit) from apm_credit_account where manualinvoiceid='"+invoiceid+"' and advref='1'  ");
			PreparedStatement ps=connection.prepareStatement(buffer.toString());
			ResultSet rs=ps.executeQuery();
			while (rs.next()) {
				amt=rs.getDouble(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return amt;
	}

	public String getDrNamebyIPDId(String ipdid) {
		String res="";
		try {
			
			String sql="select concat(initial,' ',firstname,' ',lastname,' (',owner_qualification,')') from apm_user "
					+ "inner join ipd_addmission_form on ipd_addmission_form.practitionerid=apm_user.id where ipd_addmission_form.id='"+ipdid+"'"
					+ "group by lastname";
			PreparedStatement ps=connection.prepareStatement(sql);
			ResultSet rs=ps.executeQuery();
			while(rs.next()){
				res=rs.getString(1);
			}
		} catch (Exception e) {
		e.printStackTrace();
		}
		return res;
	}
	public int resetOpdInvoiceStatus(int appointmentid, int invoiceid) {
		PreparedStatement preparedStatement = null;
		int result = 0;
		String sql = "update apm_available_slot set  opdpmnt=0,drcompleted=0 where id="+appointmentid+" ";
		
		try{
			preparedStatement = connection.prepareStatement(sql);
			result = preparedStatement.executeUpdate();
		
		}catch (Exception e) {
			// TODO: handle exception
		}
		return result;
			
	}

	public int getAppointmentIdFromInvoiceid(String id) {
		int result=0;
		try {
			String sql ="select appointmentid from apm_invoice  where id="+id+" ";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				result=rs.getInt(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public int updateOpdchrgststaus(int appointmnt) {
		PreparedStatement preparedStatement = null;
		int result = 0;
		String sql = "update apm_available_slot  set chrgstatus=0 where id="+appointmnt+" ";
		
		try{
			preparedStatement = connection.prepareStatement(sql);
			result = preparedStatement.executeUpdate();
		
		}catch (Exception e) {
			// TODO: handle exception
		}
		return result;
	}

	public String getFinancialSeqNoOfInvoice(String invoiceid) {
		String seqNo="";
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(" select f_seq_no from apm_charges_invoice where id='"+invoiceid+"'");
			ResultSet rs=preparedStatement.executeQuery();
			while (rs.next()) {
				seqNo=DateTimeUtils.isNull(rs.getString(1));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return seqNo;
	}

	public Clinic getLocationAddresswithnocon(String userId) {
		PreparedStatement preparedStatement = null;
		Clinic clinic = new Clinic();
		String sql = "SELECT address,postcode,city,hospname FROM "+userId+".apm_clinic_location where userid =1 and addressType = 'Registered'";
		
		try{
			
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()){
				clinic.setAddress(rs.getString(1));
				clinic.setPinCode(rs.getString(2));
				clinic.setLocationname(rs.getString(3));
				clinic.setClinicName(rs.getString(4));
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return clinic;
	}

	public String getInvoicePaymentNote(int invoiceid) {
		String paymentnote="";
		try {
			String sql ="select paymentnote from apm_charges_payment where chargeinvoiceid ="+invoiceid+" order by id desc limit 1";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				paymentnote = DateTimeUtils.isNull(rs.getString(1));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return paymentnote;
	}

	public String getInvoicePrefix(String invoiceType) {
		String prefix="";
		try {
			String sql=" select prefix from apm_invoice_type where id='"+invoiceType+"' ";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				prefix = DateTimeUtils.isNull(rs.getString(1));
				if(prefix.equals("")){
					if(invoiceType.equals("5")){
						prefix="ADV";
					}else if(invoiceType.equals("8")){
						prefix="OPR";
					}else if(invoiceType.equals("9")){
						prefix="IPR";
					}else if(invoiceType.equals("2")){
						prefix="IB";
					}else if(invoiceType.equals("3")){
						prefix="INVB";
					}else if(invoiceType.equals("6")){
						prefix="VACB";
					}else{
						prefix="INV";
					}
				}
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return prefix;
	}

	public ArrayList<Accounts> getCashInvoiceList(String fromDate, String toDate, Pagination pagination) {
		ArrayList<Accounts> list= new ArrayList<Accounts>();
		try {
			StringBuffer buffer=new StringBuffer();
			buffer.append("select apm_charges_invoice.id,payby,apm_charges_invoice.date,apm_charges_invoice.clientid,debit,disctype,discamt,itype ");
			buffer.append("from apm_charges_invoice ");
			buffer.append("inner join apm_charges_payment on apm_charges_payment.chargeinvoiceid=apm_charges_invoice.id ");
			buffer.append("where apm_charges_invoice.date between '"+fromDate+"' AND '"+toDate+"' ");
			buffer.append("and  (debit)-((discamt)+((discount/100)*(debit)))=payment and paymode='Cash'" );
			PreparedStatement ps= connection.prepareStatement(buffer.toString());
			ResultSet rs=ps.executeQuery();
			while (rs.next()) {
				Accounts accounts= new Accounts();
				accounts.setId(rs.getInt(1));
				accounts.setPaymentmode(rs.getString(2));
				accounts.setCommencing(DateTimeUtils.getCommencingDate1(rs.getString(3)));
				accounts.setClientid(rs.getInt(4));
				accounts.setDebitAmount(rs.getDouble(5));
				accounts.setItype(""+rs.getInt(8));
				if(rs.getInt(6)==1){
					accounts.setDiscount(rs.getDouble(7));
				}else{
					accounts.setDiscount(rs.getDouble(5)*rs.getDouble(7)/100);
				}
				accounts.setClientName(getClientName(rs.getString(4)));
				list.add(accounts);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	public int getCashInvoiceListcount(String fromDate, String toDate) {
		int count=0;
		try {
			
			StringBuffer buffer=new StringBuffer();
			buffer.append("select count(*) ");
			buffer.append("from apm_charges_invoice ");
			buffer.append("inner join apm_charges_payment on apm_charges_payment.chargeinvoiceid=apm_charges_invoice.id ");
			buffer.append("where apm_charges_invoice.date between '"+fromDate+"' AND '"+toDate+"' ");
			buffer.append("and  debit=payment and paymode='Cash'" );
			PreparedStatement ps= connection.prepareStatement(buffer.toString());
			ResultSet rs=ps.executeQuery();
			while (rs.next()) {
			count=rs.getInt(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return count;
	}

	@Override
	public String getMultiplePaymentSequenceLMH(int invoiceid) {

		String result="";
		try {
			PreparedStatement ps= connection.prepareStatement("select paySequence from apm_charges_payment where chargeinvoiceid='"+invoiceid+"'");
			ResultSet rs= ps.executeQuery();
			int i=0;
			while(rs.next()){
				if(i==0){
					result=rs.getString(1);	
				}else{
					result=result+","+rs.getString(1);	
				}
				i=i+1;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	
	}

	@Override
	public String getLMHOPDSequence(int opdid) {
		String opdSequence="";
		try {
			String sql="";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				opdSequence = resultSet.getString(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return opdSequence;
	}

	@Override
	public Accounts getchargeDetails(String chargeid) {
		// TODO Auto-generated method stub
		
		PreparedStatement preparedStatement = null;
		Accounts accounts=new Accounts();
		String sql = "SELECT clientId,chargedisc,invoiced from apm_invoice_assesments where id="+chargeid+"";
		
		try{
			
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()){
				accounts.setClientid(rs.getInt(1));
				accounts.setChargedisc(rs.getString(2));
				accounts.setInvoicee(rs.getString(3));
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
	    return accounts;
	}

	@Override
	public int saveRenderdata(Accounts accounts) {
		// TODO Auto-generated method stub
		
		PreparedStatement pmt=null;
		int result=0;
		
		try {
			
			String sql="insert into apm_render_charges(charge_name, charge_id, charge_amount, charge_discount, user_id, date_time,doctor_id,Patient_id,shared_amount,invoiced)values(?,?,?,?,?,?,?,?,?,?)";
			pmt=connection.prepareStatement(sql);
			
			pmt.setString(1, accounts.getChargename());
			pmt.setString(2, accounts.getChargeid());
			pmt.setString(3, accounts.getCharges());
			pmt.setString(4, accounts.getChargedisc());
			pmt.setString(5,accounts.getUserid());
			pmt.setString(6, accounts.getDate_time());
			pmt.setString(7, accounts.getDocid());
			pmt.setInt(8, accounts.getClientid());
			pmt.setString(9, accounts.getShared_amount());
			pmt.setString(10, accounts.getInvoicee());
			
		    result=pmt.executeUpdate();
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		return result;
		
		}

	@Override
	public ArrayList<Accounts> getrenderChargesrpt(String fromDate, String toDate) {
		// TODO Auto-generated method stub
		ArrayList<Accounts> list= new ArrayList<Accounts>();
		try {
			StringBuffer buffer=new StringBuffer();
			buffer.append("select id,charge_name,charge_id,charge_amount,charge_discount,doctor_id,Patient_id,user_id,date_time,shared_amount,invoiced from apm_render_charges where date_time between '"+fromDate+"' AND '"+toDate+" 59:59:59' ");
			
			PreparedStatement ps= connection.prepareStatement(buffer.toString());
			ResultSet rs=ps.executeQuery();
			while (rs.next()) {
				Accounts accounts= new Accounts();
				
				String clientname=getclientName(rs.getString(7));
				String doctorname=getdoctorName(rs.getString(6));
				
				accounts.setId(rs.getInt(1));
				accounts.setChargename(rs.getString(2));
				accounts.setChargeid(rs.getString(3));
				accounts.setCharges(rs.getString(4));
				//accounts.setChargedisc(rs.getString(5));
				accounts.setDocid(doctorname);
				accounts.setClientName(clientname);
				accounts.setUserid(rs.getString(8));
				accounts.setDate_time(rs.getString(9));
				accounts.setShared_amount(rs.getString(10));
				accounts.setInvoicee(rs.getString(11));
				
				//get discount,disctype data
				Accounts accounts1=getinvoiceCharges(rs.getString(11));
				
				double charge=Double.parseDouble(rs.getString(4));
				double chargedisc=0;
				accounts.setDiscountx(""+chargedisc);
				double shareamt=Double.parseDouble(rs.getString(10));
				
				
				//double totalamt=charge-charge*(chargedisc/(100));
				double totalamt=charge;
				double balnceamt=totalamt-totalamt*(shareamt/100);
				//accounts.setBalance(balnceamt);
				
				double amount_paid=getAmountbychargeid(rs.getString(3));
				accounts.setPayAmount(amount_paid);
				balnceamt=balnceamt-amount_paid;
				accounts.setBalance(balnceamt);
				
				if(accounts1.getDiscount()!=0){
					if(accounts1.getDisctype().equals("0")){
						
						chargedisc=accounts1.getDiscount();
						accounts.setDiscountx(chargedisc+"%");
						totalamt=charge-charge*(chargedisc/(100));
					}
					else{
						
					int count=getcountforInvoiceCharges(rs.getString(11));
					double result=accounts1.getDiscount()/count;
					totalamt=charge-result;
					accounts.setDiscountx("Rs."+chargedisc+"%");
							 
					}
					
				}
				
			
			if(!accounts.getInvoicee().equals("0")){
				if(!accounts1.getDiscamt().equals("0")){
					
					int count=getcountforInvoiceCharges(rs.getString(11));
					double result=accounts1.getDiscount()/count;
					totalamt=charge-result;
					accounts.setDiscountx("Rs."+chargedisc+"%");
					
				}
			
			}	
				accounts.setTotalAmount(totalamt);
				
				list.add(accounts);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	
	}

	
  private int getcountforInvoiceCharges(String invoiced) {
		
			
			PreparedStatement preparedStatement = null;
			int result = 0;
			String sql = "select count(*) from apm_invoice_assesments where invoiced='"+invoiced+"' ";

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

	private Accounts getinvoiceCharges(String invoiced) {
		// TODO Auto-generated method stub
		
		PreparedStatement preparedStatement = null;
		Accounts accounts=new Accounts();
		String sql = "SELECT discount,debit,disctype,discamt from apm_charges_invoice where id="+invoiced+"";
		
		try{
			
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()){
				accounts.setDiscount(rs.getDouble(1));
				accounts.setDebitAmount(rs.getDouble(2));
				accounts.setDisctype(rs.getString(3));;
				accounts.setDiscamt(rs.getString(4));
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
	    return accounts;
		
	}

	private double getAmountbychargeid(String chargeid) {
		// TODO Auto-generated method stub
		double paid_amount=0;
		PreparedStatement preparedStatement = null;
		Accounts accounts=new Accounts();
		String sql = "SELECT sum(amount_paid) from apm_render_payment where chargeid="+chargeid+"";
		
		try{
			
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()){
			     paid_amount=Double.parseDouble(rs.getString(1));
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
		return paid_amount;
	}

	private String getdoctorName(String docid) {
		// TODO Auto-generated method stub
		
		PreparedStatement pmt=null;
        
		String docname="";
         
         try {
			
			   String sql="select name from reference where id='"+docid+"'";
			   pmt=connection.prepareStatement(sql);
			   ResultSet rs=pmt.executeQuery();
			
			   while(rs.next()){
				docname=rs.getString(1);
		      }
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		  return docname ;
		  
	}

	private String getclientName(String clientid) {
		
		PreparedStatement pmt=null;
        
		String clientname="";
         
         try {
			
			   String sql="select concat(title,' ',firstname,' ',surname) from apm_patient where id='"+clientid+"'";
			   pmt=connection.prepareStatement(sql);
			
			   ResultSet rs=pmt.executeQuery();
			
			   while(rs.next()){
				
				   clientname=rs.getString(1);
				
				}
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		  return clientname ;
	}

	@Override
	public int getdoctorData(String doctorid) {
		// TODO Auto-generated method stub
		int share_amount=0;
		PreparedStatement preparedStatement = null;
		Accounts accounts=new Accounts();
		String sql = "SELECT name,sharepercentage from reference where id="+doctorid+"";
		
		try{
			
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()){
			
				share_amount=Integer.parseInt(rs.getString(2));
				
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
	    return share_amount;
		
	
	}

	@Override
	public int saveRenderPaymentdata(String chargeid, String balcnceamt, String date_time, String paymenttype,int genearkey) {
		// TODO Auto-generated method stub
		
		PreparedStatement pmt=null;
		int result=0;
		
		try {
			
			String sql="insert into apm_render_payment(amount_paid,chargeid,payment_mode,current_date_time,parent_id)values(?,?,?,?,?)";
			pmt=connection.prepareStatement(sql);
			
			pmt.setString(1, balcnceamt);
			pmt.setString(2, chargeid);
			pmt.setString(3, paymenttype);
			pmt.setString(4, date_time);
			pmt.setInt(5, genearkey);
			
		    result=pmt.executeUpdate();
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public int savetoParentrenderpayment(Accounts accounts,String userid) {
		// TODO Auto-generated method stub
		

		PreparedStatement pmt=null;
		int result=0;
		
		try {
			
			String sql="insert into apm_parent_render_payment(total_amount,user_id,date_time)values(?,?,?)";
			pmt=connection.prepareStatement(sql);
			
			pmt.setString(1, accounts.getCharges());
			pmt.setString(2, userid);
			pmt.setString(3, accounts.getDate_time());
			
			
		    result=pmt.executeUpdate();
		    
		    if(result == 1){
				ResultSet resultSet = pmt.getGeneratedKeys();
				if(resultSet.next()){
					result = resultSet.getInt(1);
				}
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return result;
		
		
	}

	@Override
	public String getTransactionNo(int invoiceid) {
		PreparedStatement preparedStatement = null;
		String result = "";
		String sql = "SELECT upino FROM apm_charges_payment where chargeinvoiceid = "+invoiceid+" ";
		
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

	@Override
	public String getTokennoforOPD(int opdid, int opdparctid, String date) {
		PreparedStatement preparedStatement = null;
		String result = "";
		String sql = "SELECT seqno FROM apm_available_slot_seqno where apmtid = "+opdid+" and pid='"+opdparctid+"' and commencing='"+date+"' ";
		
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

	@Override
	public Vector<Accounts> getdebitTransectionlist(String clientId) {
		PreparedStatement preparedStatement = null;
		Vector<Accounts>list = new Vector<Accounts>();
		StringBuffer sql = new StringBuffer();
		
		sql.append("select debit,discount,disctype,discamt from apm_charges_invoice where clientid="+clientId+" ");
				
		try{
			
			preparedStatement = connection.prepareStatement(sql.toString());
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()){
				Accounts accounts = new Accounts();
				accounts.setDebitAmount(rs.getDouble(1));
				accounts.setDiscount(rs.getDouble(2));
				accounts.setDisctype(rs.getString(3));
				accounts.setDiscamt(rs.getString(4));
				list.add(accounts);
			}
			
			
		}catch(Exception e){
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public double getPaymentList(String clientId) {
		PreparedStatement preparedStatement = null;
		double result = 0;
		
		try {
			String sql = "SELECT sum(payment) FROM apm_charges_payment where clientid = '"+clientId+"' ";
			preparedStatement = connection.prepareStatement(sql.toString());
			ResultSet rs = preparedStatement.executeQuery();
			if(rs.next()){
				result = rs.getDouble(1);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}

	@Override
	public double getAllAdvanceamount(String clientId) {
		PreparedStatement preparedStatement = null;
		double result = 0;
		
		try {
			String sql = "SELECT sum(charge) FROM apm_credit_account where client_id = "+clientId+" ";
			preparedStatement = connection.prepareStatement(sql.toString());
			ResultSet rs = preparedStatement.executeQuery();
			if(rs.next()){
				result = rs.getDouble(1);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
		
	}

	@Override
	public int savePatientTransection(double balnace, double debit, double prepayment, double advance,
			String clientId, String date_time ) {
		PreparedStatement pmt=null;
		int result=0;
		
		try {
			
			String sql="insert into patient_transection(balance,advance,payment,debit,clientid,datetime)values(?,?,?,?,?,?)";
			pmt=connection.prepareStatement(sql);
			
			pmt.setDouble(1, balnace);
			pmt.setDouble(2, advance);
			pmt.setDouble(3, prepayment);
			pmt.setDouble(4, debit);
			pmt.setString(5, clientId);
			pmt.setString(6, date_time);
			result=pmt.executeUpdate();
		    
		
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return result;
		
	}

	@Override
	public double getDebitamountbyid(String clientId) {
		PreparedStatement preparedStatement = null;
		double result = 0;
		StringBuffer sql=new StringBuffer();
		try {
		sql.append("SELECT sum(debit) FROM apm_credit_account where client_id = "+clientId+" and  advref='1' ");
			preparedStatement = connection.prepareStatement(sql.toString());
			ResultSet rs = preparedStatement.executeQuery();
			if(rs.next()){
				result = rs.getDouble(1);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}

	@Override
	public Accounts getPatientTransactiondata(String clientId) {
		PreparedStatement preparedStatement=null;
		Accounts accounts=new Accounts();
		StringBuffer sql=new StringBuffer();
		try {
			sql.append("select balance,advance from patient_transection where clientid='"+clientId+"'");
			preparedStatement=connection.prepareStatement(sql.toString());
			ResultSet rs=preparedStatement.executeQuery();
			while(rs.next()) {
				accounts.setBalance(rs.getDouble(1));
				accounts.setAdvance(rs.getDouble(2));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return accounts;
	}

	@Override
	public double getAdvanceamtbyrefinvid(String clientId) {
		PreparedStatement preparedStatement = null;
		double result = 0;
		StringBuffer sql=new StringBuffer();
		try {
		sql.append("select sum(charge) from apm_credit_account where refinvoiceid=0 and client_id='"+clientId+"'; ");
			preparedStatement = connection.prepareStatement(sql.toString());
			ResultSet rs = preparedStatement.executeQuery();
			if(rs.next()){
				result = rs.getDouble(1);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}

	@Override
	public boolean checkClientexist(String clientId) {
		PreparedStatement preparedStatement=null;
		boolean result=false;
		StringBuffer sql=new StringBuffer();
		try {
			sql.append("select clientid from patient_transection where clientid='"+clientId+"'");
			preparedStatement=connection.prepareStatement(sql.toString());
			ResultSet rs=preparedStatement.executeQuery();
			if (rs.next()) {
				
				result =true;
			}

			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}

	@Override
	public int updatePatientTransaction(double balance, double debit, double payment, double advanceamt,
			String clientId,String date_time ) {
		PreparedStatement preparedStatement=null;
		int res=0;
		StringBuffer sql=new StringBuffer();
		try {
			sql.append("update patient_transection set balance="+balance+", advance="+advanceamt+", datetime='"+date_time+"' where clientid='"+clientId+"'");
			preparedStatement=connection.prepareStatement(sql.toString());
			res=preparedStatement.executeUpdate();
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return res;
	}

	@Override
	public int updateAdvancerefundzero(String clientId) {
		PreparedStatement preparedStatement=null;
		int res=0;
		StringBuffer sql=new StringBuffer();
		try {
			sql.append("update patient_transection set  advance=0.0 where clientid='"+clientId+"'");
			preparedStatement=connection.prepareStatement(sql.toString());
			res=preparedStatement.executeUpdate();
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return res;
	}

	@Override
	public double getChargesSumAssessment(String clientId) {
		PreparedStatement preparedStatement=null;
		double sum=0.0;
		StringBuffer sql=new StringBuffer();
		try {
			sql.append("select sum(charge) from apm_invoice_assesments where clientId='"+clientId+"' and invoiced=0");
			preparedStatement=connection.prepareStatement(sql.toString());
			ResultSet rs=preparedStatement.executeQuery();
			while(rs.next()) {
				sum=rs.getDouble(1);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return sum;
	}

	@Override
	public ArrayList<Master> getMasterAssessmentPmntRptList1(String inv1, String fromDate, String toDate,
			String userid) {

		PreparedStatement preparedStatement = null;
		boolean discstatus=false;
		ArrayList<Master>list = new ArrayList<Master>();
		StringBuffer sql = new StringBuffer();
	
		sql.append("select masterchargetype ,sum(charge * quantity),chargeinvoiceid from apm_charges_payment inner join apm_invoice_assesments on ");
		sql.append("apm_invoice_assesments.invoiced = apm_charges_payment.chargeinvoiceid ");
		sql.append("where ");
		sql.append(" apm_charges_payment.chargeinvoiceid in ("+inv1+") and chargeinvoiceid !=0 ");
		if(userid==null||userid.equals("0")||userid.equals("")){
			
		}else{
			sql.append(" and apm_charges_payment.userid='"+userid+"'   ");
		}
		sql.append("group by masterchargetype,apm_charges_payment.chargeinvoiceid ");
		
		try{
			preparedStatement = connection.prepareStatement(sql.toString());
			ResultSet  rs = preparedStatement.executeQuery();
			while(rs.next()){
				Master master = new Master();
				master.setName(rs.getString(1));
				
				
				/*
				 * int invoiceid=rs.getInt(3); Vector<Accounts> assesmentList =
				 * getFilteredPreviewChargesPmntRptList(invoiceid,master.getName(),userid);
				 * master.setAssesmentList(assesmentList);
				 */
			    double subtotal = rs.getDouble(2);
				master.setInvoiceid(rs.getInt(3));
				master.setCharge(DateTimeUtils.changeFormat(subtotal));
				list.add(master);
				/*
				 * int size1 = assesmentList.size(); {
				 * discstatus=assesmentList.get(size1-1).isDiscstatus();
				 * 
				 * } master.setDiscstatus(discstatus); master.setInvoiceid(invoiceid);
				 */
				
			}
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		return list;
	
	}

	@Override
	public ArrayList<Clinic> getLocationAddressForManasclinic(int id) {
		PreparedStatement preparedStatement = null;
		ArrayList<Clinic> locationAdressList = new ArrayList<Clinic>();
		String sql = "SELECT address,city FROM apm_user where id ="+id+" ";
		
		try{
			
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()){
				Clinic clinic = new Clinic();
				clinic.setAddress(rs.getString(1));
				clinic.setLocationname(rs.getString(2));
	
				locationAdressList.add(clinic);
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return locationAdressList;
		
	}

	@Override
	public ArrayList<Master> getIpdInvoiceTypeList(String invoicetypeid) {
		PreparedStatement preparedStatement = null;
		ArrayList<Master>list = new ArrayList<Master>();
		String sql = "SELECT id,type FROM apm_invoice_type where service=0 and id='"+invoicetypeid+"' ";
		
		try{
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()){
				Master master = new Master();
				master.setId(rs.getInt(1));
				master.setName(rs.getString(2));
				list.add(master);
			}
			
		}catch (Exception e) {
			// TODO: handle exception
		}
		
		return list;
		
	}
	
	
	
	@Override
	public Vector<Accounts> getFilteredPreviewChargesPmntRptList1(String inv1, String mastcharge, String userid) {
		boolean discstatus=false;
		PreparedStatement preparedStatement = null;
		DateTimeUtils dateTimeUtils = new DateTimeUtils();
		IpdDAO ipdDAO = new JDBCIpdDAO(connection);
		BedDao bedDao = new JDBCBedDao(connection);
		ThirdPartyDAO thirdPartyDAO = new JDBCThirdPartyDAO(connection);
		Vector<Accounts>list = new Vector<Accounts>();
		//String sql = "SELECT apmtType,charge,practitionerName,practitionerId,paybuy FROM asmnt_view where invoiceid = "+invoiceid+" ";
		
		/*StringBuffer sql = new StringBuffer();
		sql.append("SELECT apmtType,charge,apm_invoice.practitionerName,apm_invoice.practitionerId,paybuy,apmt_charge_type,apm_invoice.id,asmnt_view.commencing,apm_invoice.appointmentid,asmnt_view.quantity,asmnt_view.id,asmnt_view.chargeid,asmnt_view.std_charge_id,asmnt_view.ipdid,apm_invoice.ginvstid,unitcharge,chargedisc,asmnt_view.logid,pkgcode ");
		sql.append("FROM apm_invoice inner join asmnt_view on ");
		sql.append("asmnt_view.invoiceid = apm_invoice.id inner join apm_charges_assesment ");
		sql.append("on apm_charges_assesment.invoiceid = apm_invoice.id ");
		sql.append("where apm_charges_assesment.chargeinvoiceid="+invoiceid+" and masterchargetype = '"+masterchargetype+"'  ");
		if(filterbydate.equals("1")){
			sql.append("order by asmnt_view.commencing ");
		}else{
			sql.append("order by apmtType ");
		}
		
*/	
		
		StringBuffer sql = new StringBuffer();
		/*
		 * sql.
		 * append("select apmtType,charge,quantity,chargedisc,unitcharge from apm_charges_payment inner join apm_invoice_assesments on "
		 * ); sql.
		 * append("apm_invoice_assesments.invoiced = apm_charges_payment.chargeinvoiceid "
		 * ); sql.append("where  ");
		 * sql.append(" apm_charges_payment.chargeinvoiceid = "+invoiceid+" ");
		 */
		sql.append("select apmtType,charge,quantity,chargedisc,unitcharge,masterchargetype,invoiced from apm_invoice_assesments ");
		
		sql.append(" where invoiced in("+inv1+") and invoiced !=0  ");
		
		/*
		 * if(uid==null||uid.equals("0")||uid.equals("")){
		 * 
		 * }else{ sql.append(" and  apm_charges_payment.userid='"+uid+"' "); }
		 */
		sql.append(" group by id order by apmtType  ");

		try {
			preparedStatement = connection.prepareStatement(sql.toString());
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()){
				
				Accounts accounts = new Accounts();
				
				String apptName = rs.getString(1);
				String  sectionroom = "";
				if(rs.getString(6).equals("INVESTIGATION")){
					 sectionroom = getSectionRoom(apptName);
					if(apptName.contains("~")){
						String tmp[] = apptName.split("~");
						apptName = tmp[0];
					}
					
					//set investigation alternate name
					String tpid = getInvoiceTpId(rs.getInt(7));
					ThirdParty thirdParty = thirdPartyDAO.getThirdPartyDetails(tpid);
					if(thirdParty.isMaintp()){
						String alternamename = getInvestigationAltName(rs.getString(1));
						if(alternamename !=null){
							if(!alternamename.equals(" ")){
								apptName = alternamename;
							}
						}
					}
					
					if(sectionroom==null){
						
					}
					else if(!sectionroom.equals("")){
						apptName = apptName + " (Room Nos : "+sectionroom+")";
					}
					
					
				}
				double charge=rs.getDouble(2),
						chargedisc=rs.getDouble(4),
						unitcharge=rs.getDouble(5);
				int quantity=rs.getInt(3);
				if(unitcharge==0){
					unitcharge = charge;
				}
				accounts.setCharges(""+charge);
				accounts.setQuantity(quantity);
				accounts.setUnitcharge(""+unitcharge);
				accounts.setChargedisc(""+chargedisc);
				accounts.setAppointmentType(apptName);
				accounts.setMasterchargetype(rs.getString(6));
				accounts.setInvoiceid(rs.getInt(7));
				list.add(accounts);
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public int saveChargesInvoice(String payby, String commencing,int clientid,double debit,double discount,
			String notes,int tpid,String location,String lastModified,int loginid,String invpstype,String invceype,int rinv,String fromdate,String todate,String doctorid, int ipdid,int preDate,String sessioncount, LoginInfo loginInfo) {
		PreparedStatement preparedStatement = null;
		int result = 0;
		String sql = "insert into apm_charges_invoice(payby,date,discount,debit,clientid,"
				+ "chargetype,notes,tpid,location,updatedby,invpstype,itype,resetinv,fromdate,"
				+ "todate,practid,ipdid,time,predayornot,date_time,session) "
				+ "values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		int invoiceid = 0;
		
		if(payby.equals("1") || payby.equals(Constants.PAY_BY_THIRD_PARTY)){
			payby = Constants.PAY_BY_THIRD_PARTY;
		}else{
			payby = Constants.PAY_BY_CLIENT;
		}
		
		try{
			
			//HH:mm:ss
			DateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
			Calendar cal = Calendar.getInstance();
			String time = dateFormat.format(cal.getTime());
			/*
			 * if(loginInfo.isBalgopal()) { int physioopd=patientphysioIpdOrNot(doctorid);
			 * if(physioopd==1) { invceype="18"; } }
			 */		
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
			preparedStatement.setInt(10, loginid);
			preparedStatement.setString(11, invpstype);
			preparedStatement.setString(12, invceype);
			preparedStatement.setInt(13, rinv);
			preparedStatement.setString(14, fromdate);
			preparedStatement.setString(15, todate);
			preparedStatement.setString(16, doctorid);
			preparedStatement.setInt(17,ipdid);
			preparedStatement.setString(18, time);
			preparedStatement.setString(19, ""+preDate);
			preparedStatement.setString(20, commencing+" "+time);
			preparedStatement.setString(21, sessioncount);
			result = preparedStatement.executeUpdate();
			
			if(result == 1){
				ResultSet resultSet = preparedStatement.getGeneratedKeys();
				if(resultSet.next()){
					invoiceid = resultSet.getInt(1);
					generateSeqOfBill(""+invoiceid, invceype);
					setVacinationChargeprocedure(""+invoiceid, ""+clientid);
					generateLMHSeqDateWise(invoiceid,invceype,commencing);
				}
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
		return invoiceid;
		
	}

	@Override
	public ArrayList<Master> getdepartmentchargeswiserevenuelist(String fromDate, String toDate, String mastercharge) {
		PreparedStatement preparedStatement = null;
		ArrayList<Master>list = new ArrayList<Master>();
		StringBuffer sql = new StringBuffer();
		StringBuffer buffer=new StringBuffer();
		fromDate=DateTimeUtils.getCommencingDate(fromDate);
		toDate=DateTimeUtils.getCommencingDate(toDate);
		
		sql.append("select count(*),apmtType,charge,sum(charge) from apm_invoice_assesments where masterchargetype='"+mastercharge+"' and commencing between '"+fromDate+"'  and '"+toDate+" 23:59:59' and invoiced != 0 and invoiced is not null group by apmtType");
		/*
		 * sql.
		 * append("select count(*),apmtType,charge,apm_charges_invoice.id from apm_charges_invoice "
		 * ); sql.
		 * append("inner join apm_invoice_assesments on apm_invoice_assesments.invoiced=apm_charges_invoice.id where masterchargetype='"
		 * +mastercharge+"' and date between '"+fromDate+"' and '"
		 * +toDate+"' group by invoiced ");
		 */
		try {
			preparedStatement = connection.prepareStatement(sql.toString());
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()){
				
				Master master=new Master();
				
				master.setCount(rs.getInt(1));
				master.setAppointmentType(rs.getString(2));
				master.setCharge(rs.getString(3));
				String code=getcode(rs.getString(2),mastercharge);
				master.setCode(code);
				double revenue=Double.parseDouble(rs.getString(4));
				master.setRevenue(revenue);
				/*
				 * buffer=buffer.append(rs.getString(4)+","); master.setInvoiceids(""+buffer);
				 */
				list.add(master);
			}
			
		
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		return list;
	}

	private String getcode(String chargename,String mastercharge) {
		PreparedStatement preparedStatement=null;
		String code="";
		StringBuffer sql=new StringBuffer();
		try {
			sql.append("select code from apm_appointment_type where name='"+chargename+"' ");
			if(!mastercharge.equals("") && !mastercharge.equals("0")){
				sql.append("and chargeType='"+mastercharge+"' ");
			}
			sql.append("limit 0,1 ");
			preparedStatement=connection.prepareStatement(sql.toString());
			ResultSet rs=preparedStatement.executeQuery();
			while(rs.next()) {
				code=rs.getString(1);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return code;
	}

	@Override
	public ArrayList<Master> getchargeswiserevenuelist(String fromDate, String toDate, String mastercharge,String searchtext) {
		PreparedStatement preparedStatement = null;
		ArrayList<Master>list = new ArrayList<Master>();
		StringBuffer sql = new StringBuffer();
		StringBuffer buffer=new StringBuffer();
		fromDate=DateTimeUtils.getCommencingDate(fromDate);
		toDate=DateTimeUtils.getCommencingDate(toDate);
		
		//sql.append("select quantity,apmtType,charge from apm_invoice_assesments where commencing between '"+fromDate+"'  and '"+toDate+" 23:59:59' ");
        //ADDED INNER JOINT APPOINTMENTTYPE
		sql.append("select quantity,apmtType,charge,count(apmtType),sum(charge*quantity) from apm_invoice_assesments inner join apm_appointment_type on apm_appointment_type.id=apm_invoice_assesments.chargeId where commencing between '"+fromDate+"'  and '"+toDate+" 23:59:59' ");

		if(!mastercharge.equals("") && !mastercharge.equals("0")){
			sql.append("and masterchargetype='"+mastercharge+"' ");
		}
		if(!searchtext.equals("") && !searchtext.equals("0")){
			sql.append("and (name like '%"+searchtext+"%' or code ='"+searchtext+"')"+ " ");
		}
		sql.append("and invoiced != 0 and invoiced IS NOT NULL group by apmtType ");
		/*
		 * sql.
		 * append("select count(*),apmtType,charge,apm_charges_invoice.id from apm_charges_invoice "
		 * ); sql.
		 * append("inner join apm_invoice_assesments on apm_invoice_assesments.invoiced=apm_charges_invoice.id where masterchargetype='"
		 * +mastercharge+"' and date between '"+fromDate+"' and '"
		 * +toDate+"' group by invoiced ");
		 */
		try {
			preparedStatement = connection.prepareStatement(sql.toString());
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()){
				
				Master master=new Master();
				
				master.setCount(rs.getInt(1));
				master.setAppointmentType(rs.getString(2));
				master.setCharge(rs.getString(3));
				String code=getcode(rs.getString(2),mastercharge);
				master.setCode(code);
				master.setTotalcount(rs.getInt(4));
				double revenue=master.getTotalcount()*Double.parseDouble(master.getCharge());
				master.setRevenue(rs.getDouble(5));
				
				/*
				 * buffer=buffer.append(rs.getString(4)+","); master.setInvoiceids(""+buffer);
				 */
				list.add(master);
			}
			
		
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public int totalCount(String fromDate, String toDate, String mastercharge) {
		int count=0;
		try {
			
			StringBuffer buffer=new StringBuffer();
			fromDate=DateTimeUtils.getCommencingDate(fromDate);
			toDate=DateTimeUtils.getCommencingDate(toDate);
			buffer.append("select count(*) ");
			buffer.append("from apm_invoice_assesments ");
			buffer.append("where commencing between '"+fromDate+"' AND '"+toDate+"' ");
			if(!mastercharge.equals("") && !mastercharge.equals("0")){
				buffer.append("and masterchargetype='"+mastercharge+"' ");
			}
			buffer.append("and invoiced != 0 and invoiced IS NOT NULL" );
			PreparedStatement ps= connection.prepareStatement(buffer.toString());
			ResultSet rs=ps.executeQuery();
			while (rs.next()) {
			count=rs.getInt(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return count;
		
	}
	@Override
	public ArrayList<Accounts> getProcedureListbyapmtid(String apptid) {
	      ArrayList<Accounts> list=new ArrayList<Accounts>();
	      PreparedStatement pst=null;
	      try {
			String sql="select * from ot_procedure where otid='"+apptid+"'";
			pst=connection.prepareStatement(sql);
			ResultSet rs=pst.executeQuery();
			while(rs.next()) {
				
				Accounts accounts=new Accounts();
				accounts.setId(rs.getInt(1));
				accounts.setProcedureid(rs.getString(3));
				String procedure_name=getProcedureNamebyid(rs.getString(3));
				accounts.setProcedure_name(procedure_name);
				list.add(accounts);
			}
	    	  
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	private String getProcedureNamebyid(String procedureid) {
		PreparedStatement preparedStatement=null;
		String procedure_name="";
		StringBuffer sql=new StringBuffer();
		try {
			sql.append("select name from apm_newchargetype where id='"+procedureid+"' ");
			preparedStatement=connection.prepareStatement(sql.toString());
			ResultSet rs=preparedStatement.executeQuery();
			while(rs.next()) {
				procedure_name=rs.getString(1);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return procedure_name;
	}

	@Override
	public int updateOtid(int invoiceid, int appointmentid) {
		PreparedStatement preparedStatement=null;
		int res=0;
		StringBuffer sql=new StringBuffer();
		try {
			sql.append("update apm_charges_invoice set ot_id="+appointmentid+" where id='"+invoiceid+"'");
			preparedStatement=connection.prepareStatement(sql.toString());
			res=preparedStatement.executeUpdate();
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return res;
		
	}

	@Override
	public String getPrintDatebyinvoiceid(int invoiceid) {
		PreparedStatement preparedStatement=null;
		String printdate="";
		StringBuffer sql=new StringBuffer();
		try {
			sql.append("select date from apm_charges_payment where chargeinvoiceid='"+invoiceid+"' ");
			preparedStatement=connection.prepareStatement(sql.toString());
			ResultSet rs=preparedStatement.executeQuery();
			while(rs.next()) {
				printdate=rs.getString(1);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return printdate;
	}

	@Override
	public String getInvoiceNote(int invoiceid) {
		String invoicenote="";
		try {
			String sql ="select notes from apm_charges_invoice where id ="+invoiceid+" order by id desc limit 1";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				invoicenote = DateTimeUtils.isNull(rs.getString(1));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return invoicenote;
		
	}


}



