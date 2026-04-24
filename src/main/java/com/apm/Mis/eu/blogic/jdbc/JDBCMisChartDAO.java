package com.apm.Mis.eu.blogic.jdbc;

import java.nio.Buffer;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Month;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import com.a.a.a.a.a.b;
import com.apm.Accounts.eu.entity.Accounts;
import com.apm.Appointment.eu.bi.AppointmentTypeDAO;
import com.apm.Appointment.eu.blogic.jdbc.JDBCAppointmentTypeDAO;
import com.apm.Appointment.eu.entity.AppointmentType;
import com.apm.Diagnosis.eu.bi.DiagnosisDAO;
import com.apm.Diagnosis.eu.blogic.jdbc.JDBCDiagnosisDAO;
import com.apm.DiaryManagement.eu.bi.ClientDAO;
import com.apm.DiaryManagement.eu.bi.DiaryManagementDAO;
import com.apm.DiaryManagement.eu.bi.NotAvailableSlotDAO;
import com.apm.DiaryManagement.eu.blogic.jdbc.JDBCClientDAO;
import com.apm.DiaryManagement.eu.blogic.jdbc.JDBCDiaryManagentDAO;
import com.apm.DiaryManagement.eu.blogic.jdbc.JDBCNotAvailableSlotDAO;
import com.apm.DiaryManagement.eu.entity.Client;
import com.apm.DiaryManagement.eu.entity.Priscription;
import com.apm.Emr.eu.bi.InvestigationDAO;
import com.apm.Emr.eu.blogic.jdbc.JDBCInvestigationDAO;
import com.apm.Emr.eu.entity.Investigation;
import com.apm.Expence.eu.entity.Expence;
import com.apm.Inventory.eu.entity.Product;
import com.apm.Ipd.eu.bi.BedDao;
import com.apm.Ipd.eu.bi.IpdDAO;
import com.apm.Ipd.eu.blogic.jdbc.JDBCBedDao;
import com.apm.Ipd.eu.blogic.jdbc.JDBCIpdDAO;
import com.apm.Ipd.eu.entity.Bed;
import com.apm.Master.eu.blogic.jdbc.JDBCTreatmentTypeDAO;
import com.apm.Master.eu.entity.Master;
import com.apm.Mis.eu.bi.MisChartDAO;
import com.apm.Registration.eu.bi.ClinicDAO;
import com.apm.Registration.eu.bi.UserProfileDAO;
import com.apm.Registration.eu.blogic.jdbc.JDBCClinicDAO;
import com.apm.Registration.eu.blogic.jdbc.JDBCUserProfileDAO;
import com.apm.Registration.eu.entity.Clinic;
import com.apm.Registration.eu.entity.UserProfile;
import com.apm.Report.eu.bi.ChargesReportDAO;
import com.apm.Report.eu.blogic.jdbc.JDBCChargesReportDAO;
import com.apm.Report.eu.entity.MisReport;
import com.apm.Report.eu.entity.Report;
import com.apm.Report.web.form.SummaryReportForm;
import com.apm.TreatmentEpisode.eu.bi.TreatmentEpisodeDAO;
import com.apm.TreatmentEpisode.eu.blogic.jdbc.JDBCTreatmentEpisode;
import com.apm.TreatmentEpisode.eu.entity.TreatmentEpisode;
import com.apm.common.eu.blogic.jdbc.JDBCBaseDAO;
import com.apm.common.utils.DateTimeUtils;
import com.apm.common.web.common.helper.LoginInfo;
import com.apm.main.common.constants.Constants;

public class JDBCMisChartDAO extends JDBCBaseDAO implements MisChartDAO {
	
	
	
	

	public JDBCMisChartDAO(Connection connection) {
		this.connection = connection;
		
	}

	public int getTotalOpdPatient(String fromdate, String todate) {
		PreparedStatement preparedStatement = null;
		int result = 0;
		String sql = "select count(*) from apm_available_slot where commencing between  '"
				+ fromdate
				+ "' and '"
				+ todate
				+ "' and status = 0 and otid=0 and dna=0";

		try {
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				result=rs.getInt(1);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public int getBookedAppointment(String fromdate, String todate) {
		PreparedStatement preparedStatement = null;
		int result = 0;
		String sql = "select count(*) from apm_available_slot where commencing between  '"
				+ fromdate + "' and '" + todate + "' and status=0 and procedures='0'";

		try {
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			if (rs.next()) {
				result = rs.getInt(1);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public int getTotalDNA(String fromdate, String todate) {
		PreparedStatement preparedStatement = null;
		int result = 0;
		String sql = "select count(*) from apm_available_slot where commencing between  '"
				+ fromdate + "' and '" + todate + "' and dna=1";

		try {
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			if (rs.next()) {
				result = rs.getInt(1);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public int getTotalCompleted(String fromdate, String todate) {
		PreparedStatement preparedStatement = null;
		int result = 0;
		StringBuffer sql = new StringBuffer();
		sql.append("select count(*) from apm_available_slot inner join apm_invoice ");
		sql.append("on apm_invoice.appointmentid = apm_available_slot.id ");
		sql.append("where dna=0 and commencing between  '" + fromdate + "' and '"+ todate + "' group by appointmentid");
		try {
			preparedStatement = connection.prepareStatement(sql.toString());
			ResultSet rs = preparedStatement.executeQuery();
			int i = 0;
			while (rs.next()) {
				i++;
			}

			result = i;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public int getIpdNewAdmission(String fromdate, String todate) {
		PreparedStatement preparedStatement = null;
		int result = 0;

		todate = todate + " 23:59:59";

		String sql = "SELECT count(*) FROM ipd_addmission_form where admissiondsate  between '"
				+ fromdate + "'  and '" + todate + "' and  casualty='0' and cancel='0' ";

		try {
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			if (rs.next()) {
				result = rs.getInt(1);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	public int getIpdNewAdmissionNew(String today) {
		PreparedStatement preparedStatement = null;
		int result = 0;
        String todaynew= today + " 23:59:59";
		

		String sql = "SELECT count(*) FROM ipd_addmission_form where admissiondsate  between '"
				+ today + "'  and '" + todaynew + "' and  casualty='0' and cancel='0'";

		try {
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			if (rs.next()) {
				result = rs.getInt(1);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public int getInHousePatients(String fromdate, String todate) {
		PreparedStatement preparedStatement = null;
		int result = 0;

		todate = todate + " 23:59:59";


		/*String sql = "SELECT count(*) FROM ipd_addmission_form where admissiondsate  between '"
				+ fromdate + "'  and '" + todate + "' and bedid!=0 ";*/

		String sql = "SELECT count(*) FROM ipd_addmission_form where bedid!=0 ";
		try {
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			if (rs.next()) {
				result = rs.getInt(1);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public int getDischargePatients(String fromdate, String todate) {
		PreparedStatement preparedStatement = null;
		int result = 0;

		todate = todate + " 23:59:59";

		StringBuffer sql = new StringBuffer();
		sql.append("SELECT count(*) FROM ipd_addmission_form inner join apm_treatment_episode ");
		sql.append("on apm_treatment_episode.id = ipd_addmission_form.treatmentepisodeid ");
		sql.append("where dischargedate between '" + fromdate + "'  and '" + todate + "' ");
		//sql.append("and dschargestatus !=0 ");
		sql.append(" and treatmentstatus=1 ");
		try {
			preparedStatement = connection.prepareStatement(sql.toString());
			ResultSet rs = preparedStatement.executeQuery();
			if (rs.next()) {
				result = rs.getInt(1);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	

	public int getTotalBeds(String fromdate, String todate) {
		PreparedStatement preparedStatement = null;
		int result = 0;

		todate = todate + " 23:59:59";

		String sql = "SELECT count(*) FROM apm_ipd_bed where casualty=0  and active='1' "; //we are adding where condition casualiy=0 and active=1 beacuse totalbeds not amtched

		try {
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			if (rs.next()) {
				result = rs.getInt(1);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public int getUnderMaintnanceBed(String fromdate, String todate) {
		PreparedStatement preparedStatement = null;
		int result = 0;

		todate = todate + " 23:59:59";

		String sql = "SELECT count(*) FROM apm_ipd_bed where active = 0 ";

		try {
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			if (rs.next()) {
				result = rs.getInt(1);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public int getOccupiedBed(String fromdate, String todate) {
		PreparedStatement preparedStatement = null;
		int result = 0;

		todate = todate + " 23:59:59";

		String sql = "SELECT count(*) FROM ipd_addmission_form where bedid!=0 ";

		try {
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			if (rs.next()) {
				result = rs.getInt(1);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public int getAvailableBed(String fromdate, String todate) {
		PreparedStatement preparedStatement = null;
		int result = 0;

		todate = todate + " 23:59:59";

		String sql = "SELECT count(*) FROM apm_ipd_bed where active = 1 and casualty=0";

		try {
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			if (rs.next()) {
				result = rs.getInt(1);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public int getChargeAddedd(String fromdate, String todate,String filter_ward) {
		PreparedStatement preparedStatement = null;
		int result = 0;
        StringBuffer sql=new StringBuffer();
		 sql.append("select sum(charge) from apm_invoice_assesments where commencing between '"
				+ fromdate + "' and '" + todate + "' ");
		 if(filter_ward.equals("IPD")) {
			 sql.append(" and ipdid !=''");
		 }else if(filter_ward.equals("OPD")) {
			 sql.append(" and ipdid=''");
		 }

		try {
			preparedStatement = connection.prepareStatement(sql.toString());
			ResultSet rs = preparedStatement.executeQuery();
			if (rs.next()) {
				result = rs.getInt(1);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public int getInvoiceGenerated(String fromdate, String todate,String invcategory,String jobtitle,String filter_ward) {
		PreparedStatement preparedStatement = null;
		int result = 0;

		String sql = "select sum(debit) from apm_charges_invoice where date between '"
				+ fromdate + "' and '" + todate + "' ";
		
		if(!invcategory.equals("2")){
			sql = "select sum(debit) from apm_charges_invoice where date between '"
				+ fromdate + "' and '" + todate + "' and apm_charges_invoice.invpstype="+invcategory+" ";
		}
		
		if(jobtitle.equals(Constants.SUPER_ADMIN)){
			sql = "select sum(debit) from apm_charges_invoice where date between '"
				+ fromdate + "' and '" + todate + "' and apm_charges_invoice.invpstype=1 ";
		}
		if(filter_ward.equals("IPD")) {
		    sql = "select sum(debit) from apm_charges_invoice where date between '"
					+ fromdate + "' and '" + todate + "' and itype='2'  ";
		}
		if(filter_ward.equals("OPD")) {
			sql = "select sum(debit) from apm_charges_invoice where date between '"
						+ fromdate + "' and '" + todate + "' and itype='1'  ";
		}
		

		try {
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			if (rs.next()) {
				result = rs.getInt(1);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public int getPaymentrecieved(String fromdate, String todate,String invcategory,String jobtitle,String filter_ward) {
		PreparedStatement preparedStatement = null;
		int result = 0;

		todate = todate + " 23:59:59";

		/*String sql = "select sum(payment) from apm_charges_payment where date between '"
				+ fromdate + "' and '" + todate + "'  ";
		*/
		StringBuffer sql = new StringBuffer();
		sql.append("select sum(payment) from apm_charges_payment inner join apm_charges_invoice on ");
		sql.append("apm_charges_invoice.id = apm_charges_payment.chargeinvoiceid ");
		sql.append("where apm_charges_payment.date between '"+fromdate+"' and '"+todate+"'  ");
		
		if(jobtitle.equals(Constants.SUPER_ADMIN)){
			invcategory = "1";
		}

		
		if(!invcategory.equals("2")){
			
			sql.append("and apm_charges_invoice.invpstype = "+invcategory+" ");
		}
		
		if(filter_ward.equals("IPD")) {
		   
			sql.append(" and itype='2' ");
		}
		if(filter_ward.equals("OPD")) {
			
			sql.append(" and itype='1' ");
		}
		
		try {
			preparedStatement = connection.prepareStatement(sql.toString());
			ResultSet rs = preparedStatement.executeQuery();
			if (rs.next()) {
				result = rs.getInt(1);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public int getCashPayment(String fromdate, String todate,String invcategory,String jobtitle) {
		PreparedStatement preparedStatement = null;
		int result = 0;

		todate = todate + " 23:59:59";

		/*String sql = "select sum(payment) from apm_charges_payment where date between '"
				+ fromdate + "' and '" + todate + "' and paymode='Cash'  ";*/
		StringBuffer sql = new StringBuffer();
		sql.append("select sum(payment) from apm_charges_payment inner join apm_charges_invoice on ");
		sql.append("apm_charges_invoice.id = apm_charges_payment.chargeinvoiceid ");
		sql.append("where apm_charges_payment.date between '"+fromdate+"' and '"+todate+"' and paymode='Cash'  ");
		
		if(jobtitle.equals(Constants.SUPER_ADMIN)){
			invcategory = "1";
		}
		
		if(!invcategory.equals("2")){
			
			sql.append("and apm_charges_invoice.invpstype = "+invcategory+" ");
		}

		try {
			preparedStatement = connection.prepareStatement(sql.toString());
			ResultSet rs = preparedStatement.executeQuery();
			if (rs.next()) {
				result = rs.getInt(1);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public int getChequepaymenyt(String fromdate, String todate,String invcategory,String jobtitle) {
		PreparedStatement preparedStatement = null;
		int result = 0;

		todate = todate + " 23:59:59";
/*
		String sql = "select sum(payment) from apm_charges_payment where date between '"
				+ fromdate + "' and '" + todate + "' and paymode='Cheque'  ";*/
		
		StringBuffer sql = new StringBuffer();
		sql.append("select sum(payment) from apm_charges_payment inner join apm_charges_invoice on ");
		sql.append("apm_charges_invoice.id = apm_charges_payment.chargeinvoiceid ");
		sql.append("where apm_charges_payment.date between '"+fromdate+"' and '"+todate+"' and paymode='Cheque'  ");
		
		if(jobtitle.equals(Constants.SUPER_ADMIN)){
			invcategory = "1";
		}
		
		if(!invcategory.equals("2")){
			
			sql.append("and apm_charges_invoice.invpstype = "+invcategory+" ");
		}

		try {
			preparedStatement = connection.prepareStatement(sql.toString());
			ResultSet rs = preparedStatement.executeQuery();
			if (rs.next()) {
				result = rs.getInt(1);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public int getDDpaymenyt(String fromdate, String todate,String invcategory,String jobtitle) {
		PreparedStatement preparedStatement = null;
		int result = 0;

		todate = todate + " 23:59:59";
/*
		String sql = "select sum(payment) from apm_charges_payment where date between '"
				+ fromdate + "' and '" + todate + "' and paymode='Cheque'  ";*/
		
		StringBuffer sql = new StringBuffer();
		sql.append("select sum(payment) from apm_charges_payment inner join apm_charges_invoice on ");
		sql.append("apm_charges_invoice.id = apm_charges_payment.chargeinvoiceid ");
		sql.append("where apm_charges_payment.date between '"+fromdate+"' and '"+todate+"' and paymode='DD'  ");
		
		if(jobtitle.equals(Constants.SUPER_ADMIN)){
			invcategory = "1";
		}
		
		if(!invcategory.equals("2")){
			
			sql.append("and apm_charges_invoice.invpstype = "+invcategory+" ");
		}

		try {
			preparedStatement = connection.prepareStatement(sql.toString());
			ResultSet rs = preparedStatement.executeQuery();
			if (rs.next()) {
				result = rs.getInt(1);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	
	public int getOtherpaymenyt(String fromdate, String todate,String invcategory,String jobtitle) {
		PreparedStatement preparedStatement = null;
		int result = 0;

		todate = todate + " 23:59:59";
/*
		String sql = "select sum(payment) from apm_charges_payment where date between '"
				+ fromdate + "' and '" + todate + "' and paymode='Cheque'  ";*/
		
		StringBuffer sql = new StringBuffer();
		sql.append("select sum(payment) from apm_charges_payment inner join apm_charges_invoice on ");
		sql.append("apm_charges_invoice.id = apm_charges_payment.chargeinvoiceid ");
		sql.append("where apm_charges_payment.date between '"+fromdate+"' and '"+todate+"' and paymode='Other'  ");
		
		if(jobtitle.equals(Constants.SUPER_ADMIN)){
			invcategory = "1";
		}
		
		if(!invcategory.equals("2")){
			
			sql.append("and apm_charges_invoice.invpstype = "+invcategory+" ");
		}

		try {
			preparedStatement = connection.prepareStatement(sql.toString());
			ResultSet rs = preparedStatement.executeQuery();
			if (rs.next()) {
				result = rs.getInt(1);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	public int getCardpayment(String fromdate, String todate,String invcategory,String jobtitle) {
		PreparedStatement preparedStatement = null;
		int result = 0;

		todate = todate + " 23:59:59";

		/*String sql = "select sum(payment) from apm_charges_payment where date between '"
				+ fromdate
				+ "' and '"
				+ todate
				+ "' and paymode='C/Card' or paymode='D/Card'  ";*/
		
		StringBuffer sql = new StringBuffer();
		sql.append("select sum(payment) from apm_charges_payment inner join apm_charges_invoice on ");
		sql.append("apm_charges_invoice.id = apm_charges_payment.chargeinvoiceid ");
		sql.append("where apm_charges_payment.date between '"+fromdate+"' and '"+todate+"' and (paymode='C/Card' or paymode='D/Card')  ");
		
		if(jobtitle.equals(Constants.SUPER_ADMIN)){
			invcategory = "1";
		}
		
		if(!invcategory.equals("2")){
			
			sql.append("and apm_charges_invoice.invpstype = "+invcategory+" ");
		}


		try {
			preparedStatement = connection.prepareStatement(sql.toString());
			ResultSet rs = preparedStatement.executeQuery();
			if (rs.next()) {
				result = rs.getInt(1);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public int getTotalPayment(String fromdate, String todate,String invcategory,String jobtitle) {
		PreparedStatement preparedStatement = null;
		int result = 0;

		todate = todate + " 23:59:59";

		/*String sql = "select sum(payment) from apm_charges_payment where date between '"
				+ fromdate + "' and '" + todate + "' ";*/
		StringBuffer sql = new StringBuffer();
		sql.append("select sum(payment) from apm_charges_payment inner join apm_charges_invoice on ");
		sql.append("apm_charges_invoice.id = apm_charges_payment.chargeinvoiceid ");
		sql.append("where apm_charges_payment.date between '"+fromdate+"' and '"+todate+"' ");
		
		if(jobtitle.equals(Constants.SUPER_ADMIN)){
			invcategory = "1";
		}
		
		if(!invcategory.equals("2")){
			
			sql.append("and apm_charges_invoice.invpstype = "+invcategory+" ");
		}

		try {
			preparedStatement = connection.prepareStatement(sql.toString());
			ResultSet rs = preparedStatement.executeQuery();
			if (rs.next()) {
				result = rs.getInt(1);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;

	}

	public ArrayList<Expence> getExpenceCategoryList(String fromdate,
			String todate) {
		PreparedStatement preparedStatement = null;
		ArrayList<Expence> list = new ArrayList<Expence>();
		String sql = "SELECT sum(amount),category,caldate FROM apm_expence_management where caldate between '"
				+ fromdate + "' and '" + todate + "' group by category ";

		try {
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			int total=0;
			while (rs.next()) {
				Expence expence = new Expence();
				expence.setCount(rs.getInt(1));
				expence.setCategory(rs.getString(2));
				total=total+expence.getCount();
				expence.setTotal(total);
				list.add(expence);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return list;
	}

	public ArrayList<Expence> getPaymentModeList(String fromdate, String todate) {
		PreparedStatement preparedStatement = null;
		ArrayList<Expence> list = new ArrayList<Expence>();
		String sql = "SELECT sum(amount),paidby FROM apm_expence_management where caldate between '"
				+ fromdate + "' and '" + todate + "' group by paidby ";

		try {
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				Expence expence = new Expence();
				expence.setCount(rs.getInt(1));
				expence.setCategory("Payment By " + rs.getString(2));

				list.add(expence);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return list;
	}

	public String getTotalExpence(String fromdate, String todate) {
		PreparedStatement preparedStatement = null;
		String result = "";
		String sql = "SELECT sum(amount) FROM apm_expence_management where caldate between '"
				+ fromdate + "' and '" + todate + "' ";

		try {
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			if (rs.next()) {
				result = rs.getString(1);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return result;
	}

	public int getTotalOpdSeen(String fromdate, String todate) {
		// TODO Auto-generated method stub
		return 0;
	}

	public int getPatientByCondiion(String fromdate, String todate) {
		// TODO Auto-generated method stub
		return 0;
	}

	public ArrayList<Client> getPaticlentByCondiion(String fromdate,
			String todate) {
		PreparedStatement preparedStatement = null;
		ArrayList<Client> list = new ArrayList<Client>();
		// String sql = "select id,name,diseasecode,icdcode from apm_condition
		// where name!= '' or name!= null order by name";
		StringBuffer sql = new StringBuffer();
		sql
				.append("SELECT count(*),name,diseasecode,icdcode FROM apm_available_slot inner join apm_condition on ");
		sql.append("apm_condition.id = apm_available_slot.condition_id ");
		sql.append("where condition_id is not null and commencing ");
		sql.append("between '" + fromdate + "' and '" + todate
				+ "'  group by condition_id ");

		try {

			preparedStatement = connection.prepareStatement(sql.toString());
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				Client client = new Client();
				client.setId(rs.getInt(1));
				String condition = rs.getString(2);

				if (rs.getString(3) != null && rs.getString(4) != null) {
					condition = condition + " " + rs.getString(3) + " / "
							+ rs.getString(4);
				}

				else if (rs.getString(4) != null) {
					condition = condition + " / " + rs.getString(4);
				}

				client.setTreatmentType(condition);
				list.add(client);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	public int getRequestedPrescription(String fromdate, String todate) {
		PreparedStatement preparedStatement = null;
		int result = 0;

		todate = todate + " 23:59:59";

		String sql = "SELECT count(*) FROM apm_client_parent_priscription where lastmodified "
				+ "between '" + fromdate + "' and '" + todate + "' ";

		try {
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			if (rs.next()) {
				result = rs.getInt(1);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public int getRequestedInvestigation(String fromdate, String todate) {
		PreparedStatement preparedStatement = null;
		int result = 0;

		todate = todate + " 23:59:59";

		String sql = "SELECT count(*) FROM apm_client_parent_investigation where lastmodified "
				+ "between '" + fromdate + "' and '" + todate + "' ";

		try {
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			if (rs.next()) {
				result = rs.getInt(1);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public int getTotalPatientByCondition(String fromdate, String todate) {
		PreparedStatement preparedStatement = null;
		int result = 0;

		String sql = "SELECT count(*) FROM apm_available_slot where condition_id is not null and commencing "
				+ "between '" + fromdate + "' and '" + todate + "' ";

		try {
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			if (rs.next()) {
				result = rs.getInt(1);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public ArrayList<Client> getpatientbyLocation(String fromdate, String todate) {
		PreparedStatement preparedStatement = null;
		ArrayList<Client> list = new ArrayList<Client>();
		todate = todate + " 23:59:59";
		String sql = "select count(*),town ,county from apm_patient where town is not null and town!='' "
				+ "and lastmodified between '"
				+ fromdate
				+ "' and '"
				+ todate
				+ "' group by town "
				+"  order by count(*) DESC ";
		try {
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			int tot=0;
			while (rs.next()) {
				Client client = new Client();
				client.setId(rs.getInt(1));
				tot=tot+client.getId();
				client.setTown(rs.getString(2));
				client.setState(rs.getString(3));
				client.setTotal(tot);
			
				list.add(client);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return list;
	}

	public int getTptalPatient(String fromdate, String todate) {
		PreparedStatement preparedStatement = null;
		int result = 0;

		todate = todate + " 23:59:59";

		String sql = "select count(*) from apm_patient where town is not null and town!='' "
				+ "and lastmodified between '"
				+ fromdate
				+ "' and '"
				+ todate
				+ "' ";

		try {
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			if (rs.next()) {
				result = rs.getInt(1);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public ArrayList<Client> getOpdPatientListReport(String fromdate,
			String todate) {
		ArrayList<Client> list = new ArrayList<Client>();
		ClientDAO clientDAO= new JDBCClientDAO(connection);
		
		try {
			String sql = "select id,commencing,status,diaryusername,clientname,clientid,dna,apmttypetext,whopay,otid,charge,opdpmnt from apm_available_slot where commencing between '"
					+ fromdate + "' and '" + todate + "'";
			PreparedStatement ps = connection.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {

				Client client = new Client();
				//client= clientDAO.getClientDetails(rs.getString(6));
				client.setAppointmentid(rs.getString(1));
				client.setDnaid(rs.getString(7));
				boolean isCompleted = false;
				if(rs.getInt(1)>0){
					isCompleted=getStatusofApmt(""+rs.getInt(1));
				}
				
				
				int otid=rs.getInt(10);
				if (isCompleted && client.getDnaid().equals("0")){
					client.setApmtStatus("Completed");
				} else if (client.getDnaid().equals("1")){
					client.setApmtStatus("DNA");
				} else if(otid>0){
					client.setApmtStatus("OT");
				}else{
					client.setApmtStatus("Not Completed");
				}
				client.setDob(rs.getString(2));
				client.setDiaryUser(rs.getString(4));
				client.setClientName(rs.getString(5));
				client.setId(rs.getInt(6));
				client.setApmttypetext(rs.getString(8));
				client.setWhopay(rs.getString(9));
				
				client.setInvstid(rs.getString(12));
				if(Integer.parseInt(client.getInvstid())>0){
					client.setApmtcharges(rs.getString(11));
					client.setCharges(rs.getString(11));
				}else{
					client.setApmtcharges("0");
					client.setCharges(rs.getString(11));
				}
				Client client1=clientDAO.getClientDetails(rs.getString(6));
				client.setAbrivationid(client1.getAbrivationid());
				client.setMobNo(client1.getMobNo());
				
				list.add(client);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	private boolean getStatusofApmt(String id) {

		boolean flag = false;

		try {

			String sql = "select id from apm_invoice where appointmentid="+id+" limit 1";
			PreparedStatement ps = connection.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				flag = true;
			} else {
				flag = false;
			}

		} catch (Exception e) {

			e.printStackTrace();
		}

		return flag;
	}
	
	



	public ArrayList<Bed> getIpdPatientListReport(String fromdate, String todate) {

		todate = todate + " 23:59:59";

		ArrayList<Bed> list = new ArrayList<Bed>();
		ClientDAO clientDAO = new JDBCClientDAO(connection);
		UserProfileDAO userProfileDAO = new JDBCUserProfileDAO(connection);
		BedDao bedDao = new JDBCBedDao(connection);
		try {

			String sql = "select ipd_addmission_form.id,ipd_addmission_form.clientid,ipd_addmission_form.practitionerid,ipd_addmission_form.conditionid,ipd_addmission_form.tpid,ipd_addmission_form.treatmentepisodeid,ipd_addmission_form.admissiondsate,apm_patient.abrivationid,ipd_addmission_form.refferedby,ipd_addmission_form.mlcno,ipd_addmission_form.mlcrefdoctor,ipd_addmission_form.wardid,ipd_addmission_form.bedid,ipd_addmission_form.tpid,ipd_addmission_form.cancel from ipd_addmission_form inner join apm_patient on apm_patient.id=ipd_addmission_form.clientid where ipd_addmission_form.admissiondsate between '"+fromdate + "' and '" + todate + "' order by ipd_addmission_form.admissiondsate asc";
				
			 // String sql="select id,clientid,practitionerid,conditionid,tpid,treatmentepisodeid,admissiondsate from ipd_addmission_form where admissiondsate between '"+fromdate + "' and '" + todate + "'";		
				
			PreparedStatement ps = connection.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {

				Bed bed = new Bed();
				bed.setId(rs.getInt(1));
				bed.setClientid(rs.getString(2));
				bed.setPractitionerid(rs.getString(3));
				bed.setConditionid(rs.getString(4));
				bed.setAbrivationid(rs.getString(8));
				bed.setDoanew(rs.getString(7));
				bed.setRefferedby(rs.getString(9));
				bed.setMlcno(rs.getString(10));
				bed.setMlcrefdoctor(rs.getString(11));
				bed.setWardid(rs.getString(12));
				bed.setBedid(rs.getString(13));
				bed.setTpid(rs.getString(14));
				String tpid=rs.getString(14);
				
				
				if(bed.getWardid()!=null)
				{
					if(bed.getWardid().equals("0"))
					{
						bed.setWardname("");
					}
					else
					{
						String wardname=bedDao.getWardName(bed.getWardid());
						
						bed.setWardname(wardname);
					}
					
					
				}
				else
				{
					bed.setWardname("");
				}
				
				if(bed.getBedid()!=null)
				{
					if(bed.getBedid().equals("0"))
					{
						bed.setBedname("");
						
					}
					else
					{
						String bedname=bedDao.getBedName(bed.getBedid());
						bed.setBedname(bedname);
					}
					
					
				}
				else
				{
					bed.setBedname("");
				}
				
				if(!bed.getWardname().equals("")&&!bed.getBedname().equals(""))
				{
					String wardbedname=bed.getWardname()+"/"+bed.getBedname();
					bed.setWardbedname(wardbedname);
				}
				else
				{
					bed.setWardbedname("");
				}
			
				Client client = clientDAO.getClientDetails(bed.getClientid());
				String fullname = client.getFirstName() + " "+client.getMiddlename()+" "
						+ client.getLastName();
				bed.setClientname(fullname);
				if(tpid!=null)
				{
					if(tpid.equals("0"))
					{
						
						bed.setWhopay("Patient");
					}
					else
					{
						
						String tname=bedDao.getThirdPartyName(tpid);
						bed.setWhopay(tname);
					}
					
				}
				else
				{
					bed.setWhopay("Patient");
				}
			
				
				
				
				int userId = Integer.parseInt(bed.getPractitionerid());
				UserProfile userProfile = userProfileDAO
						.getUserprofileDetails(userId);
				String diaryUser = userProfile.getInitial() + " "
						+ userProfile.getFirstname() + " "
						+ userProfile.getLastname();
				bed.setPractitionername(diaryUser);
				bed.setConditionname(bedDao.getIpdConditionName(bed
						.getConditionid()));

				String doadmi = rs.getString(7);
				String doadmission[] = doadmi.split(" ");
				
				bed.setDoa(doadmission[0]);
				
				long duration = 0;

				DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
				Calendar cal = Calendar.getInstance();
				String curdate = dateFormat.format(cal.getTime());
				String status = "";

				String dischargeDate = clientDAO.getIpdDischargeDate(String
						.valueOf(bed.getId()));
				
				
			
				if (dischargeDate != null) {
					if (!dischargeDate.equals("")) {
						
						String dtemp[] = dischargeDate.split(" ");
						dischargeDate = DateTimeUtils
								.getCommencingDate1(dtemp[0]);
						String dodstr=dischargeDate.toString()+" "+dtemp[1].toString();
						bed.setDod(dischargeDate);
						bed.setDodnew(dodstr);
						duration = DateTimeUtils.getDiffofTwoDates(
								bed.getDoa(), dischargeDate);
						status = "Discharged";
					} else {
						bed.setDodnew("");
						duration = DateTimeUtils.getDiffofTwoDates(
								bed.getDoa(), curdate);
						status = "Active";
						bed.setDod("NA");
						

					}
				}
				if(rs.getInt(15)==1){
					status="Admission canceled";
				}
				bed.setStatus(status);
				bed.setTotalDays(String.valueOf(duration));
				list.add(bed);

			}

		} catch (Exception e) {

			e.printStackTrace();
		}

		return list;
	}

	
	
	
	
	
	public ArrayList<Bed> getBedStatusReport(String fromdate, String todate) {

		todate = todate + " 23:59:59";

		ArrayList<Bed> list = new ArrayList<Bed>();
		ClientDAO clientDAO = new JDBCClientDAO(connection);
		UserProfileDAO userProfileDAO = new JDBCUserProfileDAO(connection);
		BedDao bedDao = new JDBCBedDao(connection);
		try {

			String sql = "select id,wardid,bedid,status,clientid,mlcno,secndryconsult,admissiondsate,tpid,practitionerid from ipd_addmission_form where  admissiondsate between '"+fromdate + "' and '" + todate + "'";
				
			 // String sql="select id,clientid,practitionerid,conditionid,tpid,treatmentepisodeid,admissiondsate from ipd_addmission_form where admissiondsate between '"+fromdate + "' and '" + todate + "'";		
				
			PreparedStatement ps = connection.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
		
			
			while (rs.next()) {

				Bed bed = new Bed();
				bed.setId(Integer.parseInt(rs.getString(1)));
				bed.setWardid(rs.getString(2));
				bed.setBedid(rs.getString(3));
				bed.setClientid(rs.getString(5));
				bed.setMlcno(rs.getString(6));
				String tpid=rs.getString(9);
				bed.setAdmissiondate(rs.getString(8));
				String practid=rs.getString(10);
				
				
				if(practid!=null)
				{
					if(practid.equals("0"))
					{
						bed.setPractitionername("");
					}
					else
					{
						
						PreparedStatement pspname = connection
								.prepareStatement("select firstname,lastname,userid from apm_user where id in("+practid+")");
						ResultSet rspname = pspname.executeQuery();
						String practname="";
						while(rspname.next())
						{
							practname=rspname.getString(1)+" "+rspname.getString(2);
						}
						
						bed.setPractitionername(practname);
					}
					
				}
				else
				{
					bed.setPractitionername("");
				}
				
				
				if(tpid!=null)
				{
					if(tpid.equals("0"))
					{
						
						bed.setWhopay("Client");
					}
					else
					{
						
						String tname=bedDao.getThirdPartyName(tpid);
						bed.setWhopay(tname);
					}
					
				}
				else
				{
					bed.setWhopay("Patient");
				}
				
				if(rs.getString(5)!=null)
				{
					PreparedStatement pspatient = connection
							.prepareStatement("select * from apm_patient where id="+rs.getString(5)+"");
					ResultSet rspatient = pspatient.executeQuery();
					
					while (rspatient.next()) {
						if(rspatient.getString(58)!=null)
						{
							bed.setAbrivationid(rspatient.getString(58));
						}
						else
						{
							bed.setAbrivationid("");
						}
						if(rspatient.getString(10)!=null)
						{
							bed.setTown(rspatient.getString(10));
						}
						else
						{
							bed.setTown("");
						}
					}
			    	
				}
				
				
				if(!rs.getString(6).equals(""))
			    {
					bed.setMlcno(rs.getString(6));
			    }
				else
				{
					bed.setMlcno("");
				}
			    
			    if(!rs.getString(7).equals("0"))
			    {
			    	
			    	PreparedStatement pscount = connection
							.prepareStatement("select count(*) from apm_user where id in("+rs.getString(7)+")");
					ResultSet rscount = pscount.executeQuery();
					int total=0;
					while(rscount.next())
					{
						 total=Integer.parseInt( rscount.getString(1));
					}
				
			    	PreparedStatement psc = connection
							.prepareStatement("select firstname,lastname,userid from apm_user where id in("+rs.getString(7)+")");
					ResultSet rsc = psc.executeQuery();
					StringBuilder consname=new StringBuilder();
					
					int start=0;
					
					while (rsc.next()) {
						start++;
						consname.append(rsc.getString(1));
						
						if(start<total)
						{
							consname.append(",");
						}
							
						
					}
			    	
			    	bed.setSecndryconsult(consname.toString());
			    //	bed.setUserid(rs.getString(3));
			    
			    	
			    }
			    else
			    {
			    	bed.setSecndryconsult("");
			    }
			

				if(bed.getBedid()!=null)
				{
					
				
				if(!bed.getBedid().equals("0"))
				{
				PreparedStatement ps0 = connection
						.prepareStatement("select * from apm_ipd_bed where id="+bed.getBedid()+"");
				ResultSet rs0 = ps0.executeQuery();
				while (rs0.next()) {
					int id = rs0.getInt(1);
					String wardid = rs0.getString(2);
					String sectionid = rs0.getString(3);
					String bedname = rs0.getString(4);
					String wardname=null;
					String sectionname=null;
					
									
					PreparedStatement ps1=connection.prepareStatement("select wardname from apm_ipd_ward where id="+wardid+"");
					ResultSet rs2=ps1.executeQuery();
					while(rs2.next()){
						
				           wardname=rs2.getString(1);		
					}
					PreparedStatement ps2=connection.prepareStatement("select sectionname from apm_ipd_section where id="+sectionid+"");
				    ResultSet rs3=ps2.executeQuery();
					
				    while(rs3.next()){
				    	sectionname=rs3.getString(1);
				    }
					
					//String bedstatus = rs.getString(5);
					//Bed bed = new Bed();
					//bed.setId(id);
					//bed.setWardid(wardid);
					//bed.setSectionid(sectionid);
					bed.setBedname(bedname);
					bed.setWardname(wardname);
					bed.setSectionname(sectionname);
					//bed.setBedstatus(bedstatus);
					//beds.add(bed);
					
					IpdDAO ipdDAO = new JDBCIpdDAO(connection);
				//	boolean checkbedstatus  = ipdDAO.checkBedStatus(id);
					
						ClientDAO clientDAO1 = new JDBCClientDAO(connection);
						String clientid = bed.getClientid();
						Client client = clientDAO1.getClientDetails(clientid);
						String clientname = client.getTitle() + " " + client.getFirstName() + " " + client.getLastName();
						bed.setClientname(clientname);
						
						bed.setStatus("1");
					
						//bed.setStatus("0");
					}
					
				}
				}
				list.add(bed);
			}
			
			
			

		} catch (Exception e) {

			e.printStackTrace();
		}

		return list;
	}

	
	
	
	public long getAdvancedAmount(String fromdate, String todate) {

		long result = 0;
		todate = todate + " 23:59:59";
		try {
			String sql = "select sum(charge) from apm_credit_account where date between '"
					+ fromdate + "' and '" + todate + "'";
			PreparedStatement ps = connection.prepareStatement(sql);

			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				result = rs.getLong(1);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public long getRefundAmount(String fromdate, String todate) {

		long result = 0;
		todate = todate + " 23:59:59";
		try {
			String sql = "select sum(debit) from apm_credit_account where date between '"
					+ fromdate + "' and '" + todate + "' and advref=1";
			PreparedStatement ps = connection.prepareStatement(sql);

			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				result = rs.getLong(1);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public long getExpenseTotal(String fromdate, String todate) {
		long result = 0;
		todate = todate + " 23:59:59";
		try {
			String sql = "select sum(amount) from apm_expence_management where caldate between '"
					+ fromdate + "' and '" + todate + "'";
			PreparedStatement ps = connection.prepareStatement(sql);

			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				result = rs.getLong(1);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public int getOtPatients(String fromdate, String todate) {

		int result = 0;

		try {
			String sql = "select count(*) from apm_ot_parent where commencing between '"
					+ fromdate + "' and '" + todate + "' ";
			PreparedStatement ps = connection.prepareStatement(sql);

			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				result = rs.getInt(1);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;

	}

	public ArrayList<Client> getAllDailySummaryList(String fromdate,
			String todate) {

		ClinicDAO clinicListDAO = new JDBCClinicDAO(connection);
		ArrayList<Client> list = new ArrayList<Client>();

		try {

			String sql = "select clientname,diaryusername,location,otid from apm_available_slot where commencing between '"
					+ fromdate + "' and '" + todate + "'";
			PreparedStatement ps = connection.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {

				Client client = new Client();
				client.setClientName(rs.getString(1));
				client.setDiaryUser(rs.getString(2));
				String locationid = rs.getString(3);
				if(locationid==null){
					locationid="";
				}
				if(locationid.equals("")){
					locationid="0";
				}
				int otid = rs.getInt(4);
				Clinic clinic = clinicListDAO.getLocationDetails(Integer
						.parseInt(locationid));
				client.setAddress(clinic.getCity());
				if (otid > 0) {
					client.setClinicalNote("OT");
				} else {
					client.setClinicalNote("OPD");
				}
				list.add(client);
			}

		} catch (Exception e) {

			e.printStackTrace();
		}

		return list;
	}

	public ArrayList<Client> getAllDailySummaryList1(String fromdate,
			String todate, LoginInfo loginInfo) {
		ArrayList<Client> list = new ArrayList<Client>();
		ClinicDAO clinicDAO = new JDBCClinicDAO(connection);
		ClientDAO clientDAO = new JDBCClientDAO(connection);
		UserProfileDAO userProfileDAO = new JDBCUserProfileDAO(connection);
		todate = todate + " 23:59:59";

		try {

			String sql = "select clientid,practitionerid from ipd_addmission_form where admissiondsate between '"
					+ fromdate + "' and '" + todate + "'";
			PreparedStatement ps = connection.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {

				Client client = new Client();

				String clientid = rs.getString(1);
				String practitionerid = rs.getString(2);

				Client client2 = clientDAO.getClientDetails(clientid);

				Clinic clinic = clinicDAO.getLocationDetails(loginInfo
						.getClinicid());
				client.setClientName(client2.getTitle() + " "
						+ client2.getFirstName() + " " + client2.getLastName());
				UserProfile profile = userProfileDAO
						.getUserprofileDetails(Integer.parseInt(practitionerid));

				String fullname = profile.getInitial() + " "
						+ profile.getFirstname() + " " + profile.getLastname();

				client.setDiaryUser(fullname);

				client.setAddress(clinic.getCity());
				client.setClinicalNote("IPD");
				list.add(client);
			}

		} catch (Exception e) {

			e.printStackTrace();
		}

		return list;
	}

	public ArrayList<Accounts> getAdvanceRefundList(String fromdate,
			String todate,LoginInfo loginInfo) {

		todate = todate + " 23:59:59";		
		ArrayList<Accounts> list=new ArrayList<Accounts>();
		ClientDAO clientDAO=new JDBCClientDAO(connection);
		ClinicDAO clinicDAO=new JDBCClinicDAO(connection);
		
		try {
			
			 String sql="select date,client_id,charge,advref,debit,refinvoiceid,payment_mode from apm_credit_account where date between '"+fromdate+"' and '"+todate+"'  and (charge!=0 or debit!=0)";
	         PreparedStatement ps=connection.prepareStatement(sql);
	         
	         ResultSet rs=ps.executeQuery();
	         while(rs.next()) {
	        	 
	        	  Accounts accounts=new Accounts();
	        	 
	        	  String date=rs.getString(1);
                  String clietid=rs.getString(2);
                  String strdates[]=date.split(" ");
                  accounts.setDate(strdates[0]);
                  Client client=clientDAO.getClientDetails(clietid);
                  accounts.setClientName(client.getTitle()+" "+client.getFirstName()+" "+client.getLastName());
                  int adref=rs.getInt(4);
                  int adv=rs.getInt(3);
                  int ref=rs.getInt(5);
                  String refinvoiceid = rs.getString(6);
                  accounts.setPaymentmode(DateTimeUtils.isNull(rs.getString(7)));
                  accounts.setAbrivationid(client.getAbrivationid());
                  if(adref>0) {
                	  
                	  accounts.setAdvref(ref);
                	  accounts.setAmount(adv);
                  }
                  else {
                	
                	  accounts.setAmount(adv);
                	  accounts.setAdvref(ref);
                  }
                                   
                  Clinic clinic=clinicDAO.getLocationDetails(loginInfo.getClinicid());
                  accounts.setAddress(clinic.getCity());
                  accounts.setRefinvoiceid(refinvoiceid);
                  
	        	  list.add(accounts);
	         }
	         
			
	
		} catch (Exception e) {

		   e.printStackTrace();
		}
		
		return list;
	}

	public ArrayList<Accounts> getAccountInfoList(String fromdate, String todate) {

		 ArrayList<Accounts> paymentList=new ArrayList<Accounts>();
		 AppointmentTypeDAO appointmentTypeDAO=new JDBCAppointmentTypeDAO(connection);
		 ClientDAO clientDAO=new JDBCClientDAO(connection);
		 
		// todate = todate + " 23:55:23"; 
		 todate = todate + " 23:59:59"; 
		 try {
			
            String sql="select id,clientid,payment,paymode,date from apm_charges_payment where paymode!='prepayment' and date between '"+fromdate+"' and '"+todate+"'";
			
            PreparedStatement ps=connection.prepareStatement(sql);
            ResultSet rs=ps.executeQuery();
            while(rs.next()) {
            	
            	Accounts accounts=new Accounts();
            	accounts.setId(rs.getInt(1));
            	Client client=clientDAO.getClientDetails(rs.getString(2));
            	accounts.setClientName(client.getTitle()+" "+client.getFirstName()+" "+client.getLastName());
            	accounts.setPayAmount(rs.getDouble(3));
            	accounts.setPaymentmode(rs.getString(4));
            	String dates[]=rs.getString(5).split(" ");
            	accounts.setDate(dates[0]);
            	accounts.setDeliverstatus("Payment");
            	paymentList.add(accounts);	 
                
            	
            }
            ArrayList<Accounts> adreflist=getAdvanceRufundData(fromdate, todate);
            paymentList.addAll(adreflist);             
		} catch (Exception e) {

		   e.printStackTrace();
		}
	
		return paymentList;
		
	}
	public ArrayList<Accounts> getAccountInfoList1(String fromdate, String todate) {

		 ArrayList<Accounts> list=new ArrayList<Accounts>();
		 ClientDAO clientDAO=new JDBCClientDAO(connection);
		 todate = todate + " 23:59:59";	
		 try {
			
			String sql="select admissiondsate,clientid,tpid,aptmtype from ipd_admission_form where admissiondsate between '"+fromdate+"' and '"+todate+"'";
			 
			PreparedStatement ps=connection.prepareStatement(sql);
            ResultSet rs=ps.executeQuery();
            while(rs.next()) {
           	 
           	  Accounts accounts=new Accounts();
           	  String addate=rs.getString(1);
           	  String dates[]=addate.split("");
           	  accounts.setDate(dates[0]);
           	  String clientId=rs.getString(2);
           	  long amt=getPayment(clientId);
              accounts.setAmount(amt);
           	  Client client=clientDAO.getClientDetails(clientId);
           	  accounts.setClientName(client.getTitle()+" "+client.getFirstName()+" "+client.getLastName());
           	  int tpid=rs.getInt(3);
           	  if(tpid>0) {
           		  
           		  accounts.setWhoPay("Third Party");
           		  
           	  }
           	  else {
           		 accounts.setWhoPay("Self");
           	  }
           	  accounts.setAppointmentType("NA");
           	  
              accounts.setNotes("IPD");      
              accounts.setHowPaid("Advanced");
              list.add(accounts);
            }
           
		} catch (Exception e) {

		   e.printStackTrace();
		}
	
		return list;
	}
	
	private long getPayment(String clientID) {
		
		long payment=0;
		
		try {
			String sql="select payment from apm_charges_payment where clientid='"+clientID+"'";
			PreparedStatement ps=connection.prepareStatement(sql);
			ResultSet rs=ps.executeQuery();
			
			while(rs.next()) {
				
				 payment=rs.getLong(1);  
				
			}
			
		} catch (Exception e) {

		   e.printStackTrace();
		}
		
		return payment;
	}

	public Client getAllYearReviewList() {

		Client client=new Client();
		
		
		int otjan=0,otfeb=0,otmar=0,otapr=0,otmay=0,otjune=0,otjuly=0,otaug=0,otsep=0,otoct=0,otnov=0,otdec=0;
		int opdjan=0,opdfeb=0,opdmar=0,opdapr=0,opdmay=0,opdjune=0,opdjuly=0,opdaug=0,opdsep=0,opdoct=0,opdnov=0,opddec=0;
		int ipdjan=0,ipdfeb=0,ipdmar=0,ipdapr=0,ipdmay=0,ipdjune=0,ipdjuly=0,ipdaug=0,ipdsep=0,ipdoct=0,ipdnov=0,ipddec=0;
		int opdcomjan=0,opdcomfeb=0,opdcommar=0,opdcomapr=0,opdcommay=0,opdcomjune=0,opdcomjuly=0,opdcomaug=0,opdcomsep=0,opdcomoct=0,opdcomnov=0,opdcomdec=0;
		int opddnajan=0,opddnafeb=0,opddnamar=0,opddnaapr=0,opddnamay=0,opddnajune=0,opddnajuly=0,opddnaaug=0,opddnasep=0,opddnaoct=0,opddnanov=0,opddnadec=0;
		try {
		
			Calendar calendar=Calendar.getInstance();
			int year=calendar.get(Calendar.YEAR);
			String fromdate=""+year+"-01-01"; 
			
			SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd");
			Date date=calendar.getTime();
			String today=format.format(date);
			
			/*String sql="select id,commencing,otid from apm_available_slot where commencing between '"+fromdate+"' and '"+today+"' and status=0 and dna=0";*/
			//String sql="select id,commencing,otid from apm_available_slot where commencing between '"+fromdate+"' and '"+today+"' and procedures!='0'";
			//select count(*) from apm_available_slot where commencing between  '"
			//+ fromdate + "' and '" + todate + "' and status=0 and procedures='0'
			
			StringBuffer buffer = new StringBuffer();
			//OPD Booked
			buffer.append("select id,commencing,otid from apm_available_slot where commencing between '"+fromdate+"' and '"+today+"' and status=0 and procedures='0' ");
			PreparedStatement ps = connection.prepareStatement(buffer.toString());
			ResultSet rs=ps.executeQuery();
			while(rs.next()) {
				 
				 int id=rs.getInt(1);
			     String commencing=rs.getString(2);
			     int otid=rs.getInt(3);
				 
			     String strtemp[]=commencing.split("-");
				    	if(strtemp[1].equals("01")) {
	                       			    		 
				    	    opdjan++;
				    	}
				    	if(strtemp[1].equals("02")) {
	  			    		 
				    	    opdfeb++;
				    	}
				    	if(strtemp[1].equals("03")) {	
	 			    		 
				    	    opdmar++;
				    	}
				    	if(strtemp[1].equals("04")) {
	 			    		 
				    	    opdapr++;
				    	}
				    	if(strtemp[1].equals("05")) {
	 			    		 
				    	    opdmay++;
				    	}
				    	if(strtemp[1].equals("06")) {
	 			    		 
				    	    opdjune++;
				    	}
				    	if(strtemp[1].equals("07")) {
	 			    		 
				    	    opdjuly++;
				    	}
				    	if(strtemp[1].equals("08")) {
	 			    		 
				    	    opdaug++;
				    	}
				    	if(strtemp[1].equals("09")) {
	 			    		 
				    	    opdsep++;
				    	}
				    	if(strtemp[1].equals("10")) {
	 			    		 
				    	    opdoct++;
				    	}
				    	if(strtemp[1].equals("11")) {
	 			    		 
				    	    opdnov++;
				    	}
				    	if(strtemp[1].equals("12")) {
	 			    		 
				    	    opddec++;
				    	}
			}
			
			StringBuffer buffer1 = new StringBuffer();
			//OPD Completed
			//buffer1.append("select id,commencing,otid from apm_available_slot where commencing between '"+fromdate+"' and '"+today+"' and status=0 and procedures='0' ");
			buffer1.append("select apm_available_slot.id,apm_available_slot.commencing,otid from apm_available_slot ");
			buffer1.append("inner join apm_invoice_assesments on apm_available_slot.id = apm_invoice_assesments.appointmentid ");
			buffer1.append("where apm_available_slot.commencing between '"+fromdate+"' and '"+today+"' and procedures='0'  and dna='0' ");
			buffer1.append("group by apm_invoice_assesments.appointmentid ");
			PreparedStatement opdcomps = connection.prepareStatement(buffer1.toString());
			ResultSet opdcompsrs=opdcomps.executeQuery();
			while(opdcompsrs.next()) {
				 
				 int id=opdcompsrs.getInt(1);
			     String commencing=opdcompsrs.getString(2);
			     int otid=opdcompsrs.getInt(3);
				 
			     String strtemp[]=commencing.split("-");
				    	if(strtemp[1].equals("01")) {
	                       			    		 
				    	    opdcomjan++;
				    	}
				    	if(strtemp[1].equals("02")) {
	  			    		 
				    	    opdcomfeb++;
				    	}
				    	if(strtemp[1].equals("03")) {	
	 			    		 
				    	    opdcommar++;
				    	}
				    	if(strtemp[1].equals("04")) {
	 			    		 
				    	    opdcomapr++;
				    	}
				    	if(strtemp[1].equals("05")) {
	 			    		 
				    	    opdcommay++;
				    	}
				    	if(strtemp[1].equals("06")) {
	 			    		 
				    	    opdcomjune++;
				    	}
				    	if(strtemp[1].equals("07")) {
	 			    		 
				    	    opdcomjuly++;
				    	}
				    	if(strtemp[1].equals("08")) {
	 			    		 
				    	    opdcomaug++;
				    	}
				    	if(strtemp[1].equals("09")) {
	 			    		 
				    	    opdcomsep++;
				    	}
				    	if(strtemp[1].equals("10")) {
	 			    		 
				    	    opdcomoct++;
				    	}
				    	if(strtemp[1].equals("11")) {
	 			    		 
				    	    opdcomnov++;
				    	}
				    	if(strtemp[1].equals("12")) {
	 			    		 
				    	    opdcomdec++;
				    	}
			}
			
			StringBuffer buffer2 = new StringBuffer();
			//OPD Completed
			//buffer1.append("select id,commencing,otid from apm_available_slot where commencing between '"+fromdate+"' and '"+today+"' and status=0 and procedures='0' ");
			buffer2.append("select apm_available_slot.id,apm_available_slot.commencing,otid from apm_available_slot ");
			buffer2.append("inner join apm_invoice_assesments on apm_available_slot.id = apm_invoice_assesments.appointmentid ");
			buffer2.append("where apm_available_slot.commencing between '"+fromdate+"' and '"+today+"' and procedures='0'  and dna!='0' ");
			buffer1.append("group by apm_invoice_assesments.appointmentid ");
			PreparedStatement opddnaps = connection.prepareStatement(buffer2.toString());
			ResultSet opddnars=opddnaps.executeQuery();
			while(opddnars.next()) {
				 
				 int id=opddnars.getInt(1);
			     String commencing=opddnars.getString(2);
			     int otid=opddnars.getInt(3);
				 
			     String strtemp[]=commencing.split("-");
				    	if(strtemp[1].equals("01")) {
	                       			    		 
				    	    opddnajan++;
				    	}
				    	if(strtemp[1].equals("02")) {
	  			    		 
				    	    opddnafeb++;
				    	}
				    	if(strtemp[1].equals("03")) {	
	 			    		 
				    	    opddnamar++;
				    	}
				    	if(strtemp[1].equals("04")) {
	 			    		 
				    	    opddnaapr++;
				    	}
				    	if(strtemp[1].equals("05")) {
	 			    		 
				    	    opddnamay++;
				    	}
				    	if(strtemp[1].equals("06")) {
	 			    		 
				    	    opddnajune++;
				    	}
				    	if(strtemp[1].equals("07")) {
	 			    		 
				    	    opddnajuly++;
				    	}
				    	if(strtemp[1].equals("08")) {
	 			    		 
				    	    opddnaaug++;
				    	}
				    	if(strtemp[1].equals("09")) {
	 			    		 
				    	    opddnasep++;
				    	}
				    	if(strtemp[1].equals("10")) {
	 			    		 
				    	    opddnaoct++;
				    	}
				    	if(strtemp[1].equals("11")) {
	 			    		 
				    	    opddnanov++;
				    	}
				    	if(strtemp[1].equals("12")) {
	 			    		 
				    	    opddnadec++;
				    	}
			}
			
			//OT
			String sql3="select id,commencing,otid from apm_available_slot where commencing between '"+fromdate+"' and '"+today+"' and procedures!='0'";
			PreparedStatement ps2=connection.prepareStatement(sql3);
			
			ResultSet rs2=ps2.executeQuery();
			while(rs2.next()) {
				
			     int id=rs2.getInt(1);
			     String commencing=rs2.getString(2);
			     int otid=rs2.getInt(3);
				 	
			    	String strtemp[]=commencing.split("-");
			    	if(strtemp[1].equals("01")) {
                       			    		 
			    	    otjan++;
			    	}
			    	if(strtemp[1].equals("02")) {
  			    		 
			    	    otfeb++;
			    	}
			    	if(strtemp[1].equals("03")) {
 			    		 
			    	    otmar++;
			    	}
			    	if(strtemp[1].equals("04")) {
 			    		 
			    	    otapr++;
			    	}
			    	if(strtemp[1].equals("05")) {
 			    		 
			    	    otmay++;
			    	}
			    	if(strtemp[1].equals("06")) {
 			    		 
			    	    otjune++;
			    	}
			    	if(strtemp[1].equals("07")) {
 			    		 
			    	    otjuly++;
			    	}
			    	if(strtemp[1].equals("08")) {
 			    		 
			    	    otaug++;
			    	}
			    	if(strtemp[1].equals("09")) {
 			    		 
			    	    otsep++;
			    	}
			    	if(strtemp[1].equals("10")) {
 			    		 
			    	    otoct++;
			    	}
			    	if(strtemp[1].equals("11")) {
 			    		 
			    	    otnov++;
			    	}
			    	if(strtemp[1].equals("12")) {
 			    		 
			    	    otdec++;
			    	}
			     
    		
			}
			
			
			today = today + " 23:59:59";	 
			//IPD
			String sql1="select id,admissiondsate from ipd_addmission_form where admissiondsate between '"+fromdate+"' and '"+today+"' and  casualty='0' and cancel='0'";
			
			PreparedStatement ps1=connection.prepareStatement(sql1);
			ResultSet rs1=ps1.executeQuery();
			
			while(rs1.next()) {

				 String addate=rs1.getString(2);
				 String strdate[]=addate.split(" ");
				 
				 String edate=strdate[0];
				 
				 String strtemp[]=edate.split("-");
			    	if(strtemp[1].equals("01")) {
                    			    		 
			    	    ipdjan++;
			    	}
			    	if(strtemp[1].equals("02")) {
			    		 
			    	    ipdfeb++;
			    	}
			    	if(strtemp[1].equals("03")) {
			    		 
			    	    ipdmar++;
			    	}
			    	if(strtemp[1].equals("04")) {
			    		 
			    	    ipdapr++;
			    	}
			    	if(strtemp[1].equals("05")) {
			    		 
			    	    ipdmay++;
			    	}
			    	if(strtemp[1].equals("06")) {
			    		 
			    	    ipdjune++;
			    	}
			    	if(strtemp[1].equals("07")) {
			    		 
			    	    ipdjuly++;
			    	}
			    	if(strtemp[1].equals("08")) {
			    		 
			    	    ipdaug++;
			    	}
			    	if(strtemp[1].equals("09")) {
			    		 
			    	    ipdsep++;
			    	}
			    	if(strtemp[1].equals("10")) {
			    		 
			    	    ipdoct++;
			    	}
			    	if(strtemp[1].equals("11")) {
			    		 
			    	    ipdnov++;
			    	}
			    	if(strtemp[1].equals("12")) {
			    		 
			    	    ipddec++;
			    	}
      				 	
				
			}
			
			
			
			
			
			
			
			
			
			
			client.setOtjan(otjan);
			client.setOtfeb(otfeb);
			client.setOtmar(otmar);
			client.setOtapr(otapr);
			client.setOtmay(otmay);
			client.setOtjune(otjune);
			client.setOtjuly(otjuly);
			client.setOtaug(otaug);
			client.setOtsep(otsep);
			client.setOtoct(otaug);
			client.setOtnov(otnov);
			client.setOtdec(otdec);
			
			client.setOpdjan(opdjan);
			client.setOpdfeb(opdfeb);
			client.setOpdmar(opdmar);
			client.setOpdapr(opdapr);
			client.setOpdmay(opdmay);
			client.setOpdjune(opdjune);
			client.setOpdjuly(opdjuly);
			client.setOpdaug(opdaug);
			client.setOpdsep(opdsep);
			client.setOpdoct(opdoct);
			client.setOpdnov(opdnov);
			client.setOpddec(opddec);
			
			client.setIpdjan(ipdjan);
			client.setIpdfeb(ipdfeb);
			client.setIpdmar(ipdmar);
			client.setIpdapr(ipdapr);
			client.setIpdmay(ipdmay);
			client.setIpdjune(ipdjune);
			client.setIpdjuly(ipdjuly);
			client.setIpdaug(ipdaug);
			client.setIpdsep(ipdsep);
			client.setIpdoct(ipdoct);
			client.setIpdnov(ipdnov);
			client.setIpddec(ipddec);
			
			client.setOpdcomjan(opdcomjan);
			client.setOpdcomfeb(opdcomfeb);
			client.setOpdcommar(opdcommar);
			client.setOpdcomapr(opdcomapr);
			client.setOpdcommay(opdcommay);
			client.setOpdcomjune(opdcomjune);
			client.setOpdcomjuly(opdcomjuly);
			client.setOpdcomaug(opdcomaug);
			client.setOpdcomsep(opdcomsep);
			client.setOpdcomoct(opdcomoct);
			client.setOpdcomnov(opdcomnov);
			client.setOpdcomdec(opdcomdec);
			
			client.setOpddnajan(opddnajan);
			client.setOpddnafeb(opddnafeb);
			client.setOpddnamar(opddnamar);
			client.setOpddnaapr(opddnaapr);
			client.setOpddnamay(opddnamay);
			client.setOpddnajune(opddnajune);
			client.setOpddnajuly(opddnajuly);
			client.setOpddnaaug(opddnaaug);
			client.setOpddnasep(opddnasep);
			client.setOpddnaoct(opddnaoct);
			client.setOpddnanov(opddnanov);
			client.setOpddnadec(opddnadec);
		
			
		}catch (Exception e) {

		   e.printStackTrace();
		}
		
		
		return client;
	}

	public ArrayList<Expence> getAllExpenseList(String fromdate, String todate) {
		ArrayList<Expence> list=new ArrayList<Expence>();
		
		try {
			long temp=0,sum=0;
			String sql="select caldate,amount,merchant,category,paidby,comments,currency from apm_expence_management where caldate between '"+fromdate+"' and '"+todate+"'";
			PreparedStatement ps=connection.prepareStatement(sql);
			
			ResultSet rs=ps.executeQuery();
			while(rs.next()) {
				
				  Expence expence=new Expence();
				  expence.setCaldate(rs.getString(1));
				  expence.setAmount(rs.getString(2));
				  expence.setMerchant(rs.getString(3));
				  expence.setCategory(rs.getString(4));
				  expence.setPaidby(rs.getString(5));
				  expence.setComments(rs.getString(6));
				  expence.setCurrency(rs.getString(7));
				  temp=rs.getLong(2);
				  sum=sum+temp; 
				  expence.setValue(""+sum+"");
				  list.add(expence);
 			}
		
			
		} catch (Exception e) {

		   e.printStackTrace();
		}
	
		return list;
	}

	public long getAllOtherPayment(String fromdate, String todate) {

		PreparedStatement preparedStatement = null;
		long result = 0;

		todate = todate + " 23:59:59";
        //added line paymode!='upi'
		String sql = "select sum(payment) from apm_charges_payment where date between '"
				+ fromdate + "' and '" + todate + "' and paymode!='Cheque' and paymode!='Cash' and paymode!='C/Card' and paymode!='D/Card' and paymode!='upi' ";

		try {
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			if (rs.next()) {
				result = rs.getLong(1);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public long getTotalNotCompleted(String fromdate, String todate) {
	
        long res=0;
        int temp=0;
        
        try {
		
        	String sql="select id from apm_available_slot where otid=0 and dna=0 and commencing between '"+fromdate+"' and '"+todate+"' ";
        	
    		PreparedStatement ps=connection.prepareStatement(sql);
    		ResultSet rs=ps.executeQuery();
    		while(rs.next()) {
    			
                 int id=rs.getInt(1);
                 String sql2="select id from apm_invoice where appointmentid="+id+"";
                 PreparedStatement ps1=connection.prepareStatement(sql2);
                 ResultSet rs1=ps1.executeQuery();
                 if(rs1.next()) {
                    	
                 }
                 else {
                	 res++;
                 }
                
    		}
    		
		} catch (Exception e) {

		   e.printStackTrace();
		}
        
		return res;
	}
	
	
	public ArrayList<TreatmentEpisode> getTreatmentEpisodeList(String practitionerId,String fromDate,String toDate,String orderby,String order) {
		PreparedStatement preparedStatement = null;
		ArrayList<TreatmentEpisode>list = new ArrayList<TreatmentEpisode>();
		
		DiagnosisDAO diagnosisDAO=new JDBCDiagnosisDAO(connection);
		
		StringBuffer sql = new StringBuffer();
		
		//toDate = toDate + " 23:55:23";
		toDate = toDate + " 23:59:59";
		if(practitionerId!=null){
			if(!practitionerId.equals("0")){
				sql.append("SELECT id,clientid,clientname,practitionerid,name,payby,sessions,lastmodified,invoicee,condition_id  ");
				sql.append("FROM apm_treatment_episode where practitionerid="+practitionerId+" ");
				sql.append("and lastmodified between '"+fromDate+"' AND '"+toDate+"' ");
			}else{
				sql.append("SELECT id,clientid,clientname,practitionerid,name,payby,sessions,lastmodified,invoicee,condition_id  ");
				sql.append("FROM apm_treatment_episode ");
				sql.append("where lastmodified between '"+fromDate+"' AND '"+toDate+"' ");
			}
		}else{
			sql.append("SELECT id,clientid,clientname,practitionerid,name,payby,sessions,lastmodified,invoicee,condition_id  ");
			sql.append("FROM apm_treatment_episode ");
			sql.append("where lastmodified between '"+fromDate+"' AND '"+toDate+"' ");
		}
		
		sql.append("order by "+orderby+" "+order+" ");
		
		/*if(practitionerId!= null || (!fromDate.equals("") && !toDate.equals(""))){					
		
			sql = "SELECT id,clientid,clientname,practitionerid,name,payby,sessions,startdate,invoicee,usedsession "
					+ "FROM apm_treatment_episode where practitionerid like ('%"+practitionerId+"%') or startdate "
					+ "between '"+fromDate+"' AND '"+toDate+"' ";
		}
		else{
			sql = "SELECT id,clientid,clientname,practitionerid,name,payby,sessions,startdate,invoicee,"
					+ "usedsession FROM apm_treatment_episode ";
		}
		*/
		
		try{
			preparedStatement = connection.prepareStatement(sql.toString());
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()){
				TreatmentEpisode treatmentEpisode = new TreatmentEpisode();
				treatmentEpisode.setId(rs.getInt(1));
				treatmentEpisode.setClientId(rs.getInt(2));
				treatmentEpisode.setClientName(rs.getString(3));
				String practitionerName = getPractitionerName(rs.getString(4));
				treatmentEpisode.setDiaryUser(practitionerName);				
				treatmentEpisode.setTreatmentEpisodeName(rs.getString(5));
				treatmentEpisode.setPayby(rs.getString(6));
				treatmentEpisode.setSessions(rs.getString(7));
				treatmentEpisode.setTreatmentStartDate(DateTimeUtils.getIndianDateTimeFormat(rs.getString(8)));
				treatmentEpisode.setInvoicee(rs.getString(9));
				String conditionid=rs.getString(10);
				treatmentEpisode.setCondition(diagnosisDAO.getNameOfDiagnosisFromId(conditionid));
				list.add(treatmentEpisode);
			}
			
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	private String getPractitionerName(String practitionerId) {
		PreparedStatement preparedStatement = null;
		String result = "";
		String sql = "select initial,firstname,lastname from apm_user where id = "+practitionerId+"";
		try{
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()){
				result = rs.getString(1)+" "+rs.getString(2)+" "+rs.getString(3);
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
		return result;
	}
	

	
	public ArrayList<Client> getClinicalViewList(String fromdate,
			String todate) {

		//todate = todate + " 23:55:23"; 
		todate = todate + " 23:59:59"; 
		ArrayList<Client> list=new ArrayList<Client>();
		
		try {
			
			String sql="select title,firstname,surname,address,town,treatment_type from apm_patient where lastModified between '"+fromdate+"' and '"+todate+"'";
			
			PreparedStatement ps=connection.prepareStatement(sql);
			ResultSet rs=ps.executeQuery();
			DiagnosisDAO diagnosisDAO=new JDBCDiagnosisDAO(connection);
			
			while(rs.next()) {
				
				Client client=new Client();
				String name=rs.getString(1)+" "+rs.getString(2)+" "+rs.getString(3);
				client.setClientName(name);
				client.setAddress(rs.getString(4));
				client.setTown(rs.getString(5));
				String conditionId=rs.getString(6);
				String condition=diagnosisDAO.getNameOfDiagnosisFromId(conditionId);
				client.setTreatmentType(condition);
		        list.add(client);		
			}
			
		} catch (Exception e) {

		   e.printStackTrace();
		}
		
		
		return list;

	}


	public ArrayList<Accounts> getAdvanceRufundData(String fromdate,String todate) {
		
		ArrayList<Accounts> list=new ArrayList<Accounts>();
		ClientDAO clientDAO=new JDBCClientDAO(connection);
		try {
			
			String sql="select date,client_id,charge,payment_mode,debit,advref from apm_credit_account where date between '"+fromdate+"' and '"+todate+"' and charge!=0";
			PreparedStatement ps=connection.prepareStatement(sql);
			ResultSet rs=ps.executeQuery();
			while(rs.next()) {
				
				Accounts accounts=new Accounts();
			    String dates[]=rs.getString(1).split(" ");	
				accounts.setDate(dates[0]);
				Client client=clientDAO.getClientDetails(rs.getString(2));
				accounts.setClientName(client.getTitle()+" "+client.getFirstName()+" "+client.getLastName());
				int adref=rs.getInt(6);
				String amt="";
				String status="";
				if(adref>0) {
					
				   amt=rs.getString(5);
			       status="Refund";  		
				}
				else {
				
					amt=rs.getString(3);
					status="Advanced";
				}
				accounts.setPaymentmode(rs.getString(4));
			    accounts.setPayAmount(Double.parseDouble(amt));
				accounts.setDeliverstatus(status);
		        list.add(accounts);		
	   		}
			
	
		} catch (Exception e) {

		   e.printStackTrace();
		   
		}
		
	    return list;
	}

	public ArrayList<Investigation> getInvestigationDetails(String fromdate, String todate, String filter_ward,LoginInfo loginInfo) {
		PreparedStatement preparedStatement = null;
 		
		if (todate != null) {
			todate = todate + " 23:59:59";
		}
		
		ArrayList<Investigation> list = new ArrayList<Investigation>();
		StringBuffer sql = new StringBuffer();
		//String userid=loginInfo.getUserId();
		
		sql.append("SELECT clientid,practitionerid,conditionid,advoice,english,regional,hindi,prepay,postpay,other, ");
		sql.append("apm_client_parent_investigation.lastmodified,reporttype,apm_client_parent_investigation.id,ipdid,upstatus, ");
		sql.append("apm_client_parent_investigation.status,charginvoiceid,dateofupdated,dateofcompleted,compstatus,compdate,deleted FROM apm_client_parent_investigation inner join apm_patient ");
		sql.append("on apm_patient.id = apm_client_parent_investigation.clientid where ");
        sql.append("apm_client_parent_investigation.lastmodified between '"+fromdate+"' and '"+todate+"' ");
        
        if(!filter_ward.equals("0")){
        	if(filter_ward.equals("IPD")){
				sql.append("and ipdid!= 0 and ipdid is not null ");
			}else if(filter_ward.equals("OPD")){
				sql.append("and (ipdid=0 or ipdid is null) ");
			}
        }
		sql.append("order by id desc ");
		String sql1 = sql.toString();
		try {
			UserProfileDAO userProfileDAO = new JDBCUserProfileDAO(connection);
			ClientDAO clientDAO = new JDBCClientDAO(connection);
			UserProfile userProfile = userProfileDAO.getUserprofileDetails(loginInfo.getId());
			preparedStatement = connection.prepareStatement(sql1);
			ResultSet rs = preparedStatement.executeQuery();
			int new_invistigation=0;
			int new_collected=0;
			int new_completed=0;
			int new_aproved=0;
			int deleted_investigation =0;
			while (rs.next()) {
				Investigation investigation = new Investigation();
				investigation.setClientId(rs.getString(1));
				investigation.setPrectionerid(rs.getString(2));
				investigation.setConditionid(rs.getString(3));
				investigation.setAdvoice(rs.getString(4));
				investigation.setEnglish(rs.getString(5));
				investigation.setRegional(rs.getString(6));
				investigation.setHindi(rs.getString(7));
				investigation.setPrepay(rs.getString(8));
				investigation.setPostpay(rs.getString(9));
				investigation.setOtherpay(rs.getString(10));

				if (rs.getString(11) != null) {
					investigation.setDate(DateTimeUtils.getDBDate(rs
							.getString(11)));
				} else {
					investigation.setDate("");
				}

				investigation.setInvstreporttype(rs.getString(12));
				investigation.setId(rs.getInt(13));
				
				/*int checkChargeRaised = investigationDAO.checkChargeRaised(investigation.getId());
				investigation.setCheckChargeRaised(checkChargeRaised);*/

				/*int chargeid = investigationDAO.getChargedInvoiceid(investigation.getId());
				int checkInvoiceCreated = investigationDAO.checkInvoiceCreated(chargeid);
				investigation.setCheckInvoiceCreated(checkInvoiceCreated);*/

				/*String investType = investigationDAO.getInvestType(investigation.getId());
				investigation.setInvsttype(investType);
				
				String sectionid = investigationDAO.getInvstigationSectionId(investigation.getId());
				if(sectionid==null){
					sectionid = "0";
				}
				String invreq = investigationDAO.getInveReqId(investigation.getId());
				investigation.setInvreq(invreq);*/
				
				/*investigation.setSectionid(sectionid);*/

				String ipdid = rs.getString(14);
				
				//Bed bed = bedDao.getEditIpdData(ipdid);

				if (ipdid == null || ipdid.equals("0")) {
					investigation.setIpdid("0");
				} else {
					investigation.setIpdid(ipdid);
				}

				investigation.setUpstatus(rs.getString(15));
				investigation.setStatus(rs.getString(16));
				investigation.setCharginvoiceid(rs.getInt(17));
				investigation.setUpdate_date(rs.getString(18));
				investigation.setComplete_date(rs.getString(19));
				investigation.setCollectstatus(rs.getString(20));
				investigation.setCollect_date(rs.getString(21));
				investigation.setDeleted(rs.getString(22));
				/*if(investigation.getCollect_date()!=null){
					if(!investigation.getCollect_date().equals("")){
						String dd= DateTimeUtils.getDBDate(investigation.getCollect_date());
						investigation.setCollect_date(dd);
					}
				} else {
					investigation.setCollect_date("");
				}
				
				if(investigation.getUpdate_date()!=null){
					
					if(!investigation.getUpdate_date().equals("")){
						String dd= DateTimeUtils.getDBDate(investigation.getUpdate_date());
						investigation.setUpdate_date(dd);
					}
				}
				if(investigation.getComplete_date()!=null){
					
					if(!investigation.getComplete_date().equals("")){
						String dd= DateTimeUtils.getDBDate(investigation.getComplete_date());
						investigation.setComplete_date(dd);
					}
				}*/

				/*String wardname = ipdDAO.getIpdWardName(bed.getWardid());
				investigation.setWardname(wardname);
				String bedname = ipdDAO.getIpdBedName(bed.getBedid());
				investigation.setBedname(bedname);*/
				
				String clientid = investigation.getClientId();
				
				Client client = clientDAO.getClientDetails(clientid);

				String fullname = client.getTitle() + " "
						+ client.getFirstName() + " " + client.getLastName();
				String ageandgender = "";
				if (client.getDob() != null) {
					ageandgender = DateTimeUtils.getAge(client.getDob())
							+ " / " + client.getGender();
				}

				investigation.setFullname(fullname);
				investigation.setAgeandgender(ageandgender);
				investigation.setWhopay(client.getWhopay());
				investigation.setAbrivationid(client.getAbrivationid());

				
				 userProfile = userProfileDAO
						.getUserprofileDetails(Integer.parseInt(investigation
								.getPrectionerid()));
				String practitionerName = userProfile.getInitial() + " "
						+ userProfile.getFirstname() + " "
						+ userProfile.getLastname();

				if (investigation.getPrectionerid().equals("0")) {
					practitionerName = clientDAO.getGPname(client.getGpid());
				}
				investigation.setPractitionerName(practitionerName);
				investigation.setPractitionerMob(userProfile.getMobile());
				investigation.setSpecialization(userProfile.getSpecialization());

				/*boolean isAttachment = investigationDAO.checkAttachment(investigation.getId());
				if (isAttachment) {
					investigation.setIsAttachment("1");
					
					 * ArrayList<Emr>docmntList =
					 * getUploadedeDocmntList(investigation.getId());
					 * investigation.setDocList(docmntList);
					 
				} else {
					investigation.setIsAttachment("0");
				}*/
				if (rs.getString(4)!=null) {
					if (!rs.getString(4).equals("")) {
						investigation.setValidnote("1");
					}else{
						investigation.setValidnote("0");
					}
				}else{
					investigation.setValidnote("0");
				}
				String investigation_status="";
				if(rs.getInt(22)==1){
					deleted_investigation++;
					investigation_status="5";
				}else if(rs.getInt(15)==0 && rs.getInt(20)==0){
					new_invistigation++;
					investigation_status="1";
				}else if(rs.getInt(15)==0 && rs.getInt(20)==1){
					new_collected++;
					investigation_status="2";
				}else if(rs.getInt(15)==1 && rs.getInt(20)==1 && rs.getInt(16)==0){
					new_completed++;
					investigation_status="3";
				}else if(rs.getInt(16)==1){
					new_aproved++;
					investigation_status="4";
				}
				investigation.setDeleted_investigation(deleted_investigation);
				investigation.setInvestigation_status(investigation_status);
				investigation.setNew_invistigation(new_invistigation);
				investigation.setNew_aproved(new_aproved);
				investigation.setNew_collected(new_collected);
				investigation.setNew_completed(new_completed);
				investigation.setFromdate(fromdate);
				investigation.setTodate(todate);
				list.add(investigation);
				
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	public Product getAllTotalProcurment(String fromdate, String todate, String loc) {
		Product product = new Product();
		try {
			StringBuffer buffer = new StringBuffer();
			buffer.append("select inventory_procurement.id,inventory_procurement.longpo from inventory_procurement ");
			//buffer.append("inner join inventory_product on inventory_procurement.prodid=inventory_product.id ");
			buffer.append("where gudreceipt=1 and inventory_procurement.deleted=0 and date between '"+fromdate+"' and '"+todate+"' ");
			if(!loc.equals("0")){
				buffer.append("and inventory_procurement.location='"+loc+"' ");
			}
			buffer.append("and inventory_procurement.voucherno is not null group by inventory_procurement.procurementid ");
			PreparedStatement preparedStatement = connection.prepareStatement(buffer.toString());
			ResultSet rs = preparedStatement.executeQuery();
			int grnwithpo=0;
			int grnwithoutpo =0;
			int totalgrn=0;
			while (rs.next()) {
				if(rs.getInt(2)>0){
					grnwithpo++;
				}else{
					grnwithoutpo++;
				}
			}
			totalgrn = grnwithpo+grnwithoutpo;
			product.setGrnwithoutpo(grnwithoutpo);
			product.setGrnwithpo(grnwithpo);
			product.setTotalgrn(totalgrn);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return product;
		
	}

	public Product getAllTotalIndent(String fromdate, String todate, String loc) {
		Product prod = new Product();
		try {
			//@  Indent total count
			StringBuffer buffer = new StringBuffer();
			buffer.append("select r_status from inventory_parent_transfer_log ");
			buffer.append("where deleted=0 and request_date between '" + fromdate + "' and '" + todate + "'  ");
			
			if(!loc.equals("0")){
				buffer.append("and from_location='"+loc+"' ");
			}
			PreparedStatement preparedStatement = connection.prepareStatement(buffer.toString());
			ResultSet rs = preparedStatement.executeQuery();
			int direct_transfer =0;
			int request =0;
			int rejected =0;
			int pending =0;
			int pocreated =0;
			int transfer=0;
			int approved=0;
			int delivered=0;
			int received=0;
			int readytotransfer=0;
			int returned=0;
			int totalindent=0;
			while (rs.next()) {
				//'0':'Request', '1':'Approved', '2':'Rejected', '3':'Delivered' , '4':'Received', '5':'PO Created', '6':'Pending','7':'Ready To Transfer','8':'Direct Transfer','9':'Return'
				if(rs.getInt(1)==8){
					direct_transfer++;
				}else if(rs.getInt(1)==0){
					request++;
				}else if(rs.getInt(1)==1){
					approved++;
				}else if(rs.getInt(1)==2){
					rejected++;
				}else if(rs.getInt(1)==3){
					delivered++;
				}else if(rs.getInt(1)==4){
					received++;
				}else if(rs.getInt(1)==5){
					pocreated++;
				}else if(rs.getInt(1)==6){
					pending++;
				}else if(rs.getInt(1)==7){
					readytotransfer++;
				}else if(rs.getInt(1)==9){
					returned++;
				}
				totalindent = direct_transfer+request+approved+rejected+delivered+received+pocreated+pending+readytotransfer;//+returned;
				prod.setDirect_transfer(direct_transfer);
				prod.setRequest(request);
				prod.setApproved(approved);
				prod.setRejected(rejected);
				prod.setTransfer(delivered);
				prod.setReceived(received);
				prod.setPocreated(pocreated);
				prod.setPending(pending);
				prod.setReadytotransfer(readytotransfer);
				prod.setReturned(returned);
				prod.setTotalindent(totalindent);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return prod;
	}
	
public ArrayList<Accounts> getPatientViewByPackage(String fromdate,String todate) {
		
		ArrayList<Accounts> list=new ArrayList<Accounts>();
		ClientDAO clientDAO=new JDBCClientDAO(connection);
		todate = todate + " 23:59:59";
		 int countcash=0;
		 int countcheque=0;
		try {
			StringBuilder sql=new StringBuilder();
			sql.append("select  apm_third_party_details.id,company_name,acc_warning_limit,outInvoiceLimit,telephone,company_email,creditReminderDuration,");
			sql.append("sum(debit),sum(discount),apm_charges_invoice.id,apm_charges_invoice.date from apm_third_party_details ");
			sql.append("inner join apm_charges_invoice on apm_charges_invoice.tpid = apm_third_party_details.id ");
			sql.append("inner join apm_patient on apm_patient.id = apm_charges_invoice.clientid ");
			//sql.append("where payby='Third Party' group by tpid order by company_name");
			sql.append("where payby='Third Party' and  apm_charges_invoice.date between '" + fromdate + "' and '" + todate + "'  group by tpid order by company_name");
			PreparedStatement ps=connection.prepareStatement(sql.toString());
			ResultSet rs=ps.executeQuery();
			while(rs.next()) {
				
				Accounts accounts=new Accounts();
			    accounts.setPackagename(rs.getString(2));
			    accounts.setTotalpayment(rs.getString(8));
				String patientcount=getPatientCount(rs.getString(1));
				accounts.setPatientno(patientcount);
				String paymode=getPaymentMode(rs.getString(10));
						if(paymode.equals("Cash"))
						{
							countcash++;
						}
						else if(paymode.equals("Cheque"))
						{
							countcheque++;
						}
				accounts.setPaymodecash(""+countcash);
				accounts.setPaymodecheque(""+countcheque);
		        list.add(accounts);		
	   		}
			
	
		} catch (Exception e) {

		   e.printStackTrace();
		   
		}
		
	    return list;
	}

public Accounts getCashCheque(String fromdate, String todate) {
	Accounts acc = new Accounts();
	todate = todate + " 23:59:59";
	 int countcash=0;
	 int countcheque=0;
	try {
		StringBuilder sql=new StringBuilder();
		sql.append("select  apm_third_party_details.id,company_name,acc_warning_limit,outInvoiceLimit,telephone,company_email,creditReminderDuration,");
		sql.append("sum(debit),sum(discount),apm_charges_invoice.id,apm_charges_invoice.date from apm_third_party_details ");
		sql.append("inner join apm_charges_invoice on apm_charges_invoice.tpid = apm_third_party_details.id ");
		sql.append("inner join apm_patient on apm_patient.id = apm_charges_invoice.clientid ");
		//sql.append("where payby='Third Party' group by tpid order by company_name");
		sql.append("where payby='Third Party' and  apm_charges_invoice.date between '" + fromdate + "' and '" + todate + "'  group by tpid order by company_name");
		PreparedStatement ps=connection.prepareStatement(sql.toString());
		ResultSet rs=ps.executeQuery();
		while(rs.next()) {
			
			String paymode=getPaymentMode(rs.getString(10));
					if(paymode.equals("Cash"))
					{
						countcash++;
					}
					else if(paymode.equals("Cheque"))
					{
						countcheque++;
					}
					acc.setPaymodecash(""+countcash);
					acc.setPaymodecheque(""+countcheque);
	       
		}
		
	} catch (Exception e) {
		e.printStackTrace();
	}
	return acc;
}

private String getPatientCount(String tpid) {
	PreparedStatement preparedStatement = null;
	String result = "";
	String sql = "select count(*) from apm_charges_invoice where apm_charges_invoice.tpid="+tpid+"";
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

private String getPaymentMode(String invoiceid) {
	PreparedStatement preparedStatement = null;
	String result = "";
	String sql = "select paymode from apm_charges_payment where chargeinvoiceid="+invoiceid+"";
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

public ArrayList<MisReport> getAllKPIList(String kpiarea_filter, String year_filter, String month_filter) {
	ArrayList<MisReport> arrayList = new ArrayList<MisReport>();
	try {
		
		StringBuilder builder = new StringBuilder();
		builder.append("select kpi_master.id, kpi_master.areaid, indicatorid, no_of_input, prompt1, prompt2, prompt3, prompt4, prompt5, formula, formula_desc, ");
		builder.append("area,indicator, ");
		builder.append("kpi_indicator.ismannual, kpi_indicator.function_name ");
		builder.append("from kpi_master inner join kpi_area on kpi_area.id = kpi_master.areaid ");
		builder.append("inner join kpi_indicator on kpi_indicator.id = kpi_master.indicatorid ");
		builder.append("inner join nabh_subcatagory on kpi_area.subcatagoryid = nabh_subcatagory.id ");
		builder.append("inner join nabh_Catagory on nabh_Catagory.id = nabh_subcatagory.catagoryid ");
		builder.append("where nabh_Catagory.id='1' ");
		if(kpiarea_filter!=null){
			builder.append("and kpi_master.areaid='"+kpiarea_filter+"' ");
		}
		
		PreparedStatement preparedStatement = connection.prepareStatement(builder.toString());
		ResultSet rs = preparedStatement.executeQuery();
		while (rs.next()) {
			MisReport misReport = new MisReport();
			misReport.setKpiid(rs.getString(1));
			misReport.setKpiareaid(rs.getString(2));
			misReport.setKpiindicatorid(rs.getString(3));
			misReport.setNo_of_input(rs.getString(4));
			misReport.setFormula(rs.getString(10));
			misReport.setFormula_desc(rs.getString(11));
			misReport.setKpiarea(rs.getString(12));
			misReport.setKpiindicator(rs.getString(13));
			misReport.setIsmannual(rs.getString(14));
			misReport.setFname(rs.getString(15));
			int no_of_input =0;
			if(rs.getInt(4)==0){
				no_of_input=0;
			}else{
				no_of_input=rs.getInt(4);
			}
			
			//26 oct 2017
			/*if(rs.getString(14).equals("1")){
				if(rs.getString(15)!=null){
					if(!rs.getString(15).equals("")){
						int kpidataid =  checkKPIAvailable(rs.getInt(1), year_filter, month_filter);
						if(rs.getString(15).equals("mrddissumnotfound")){
							int result = getMrdDisSumRecordNF(rs.getString(1),year_filter,month_filter,""+kpidataid);
						}else if(rs.getString(15).equals("mrdfilemissing")){
							int result = getMrdFileNF(rs.getString(1),year_filter,month_filter,""+kpidataid);
						}else if(rs.getString(15).equals("mortality")){
							int result = getMortalityNF(rs.getString(1),year_filter,month_filter,""+kpidataid);
						}else if(rs.getString(15).equals("timetakenfordischarge")){
							int result = getTimeTakenForDischarge(rs.getString(1),year_filter,month_filter,""+kpidataid);
						}
					}
				}
			}*/
			
			
			MisReport misReport2 = getKPITransactionData(rs.getString(1),year_filter,month_filter);
			ArrayList<String> arrayList2 = new ArrayList<String>();
			ArrayList<MisReport> arrayList3 = new ArrayList<MisReport>();
			
			for(int i=1;i<=no_of_input;i++){
				MisReport misReport3 = new MisReport();
				arrayList2.add("");
				misReport3.setInputData("");
				misReport3.setKpiid(rs.getString(1));
				arrayList3.add(misReport3);
				/*MisReport misReport3 = new MisReport();
				if(misReport2.getIs_active().equals("0")){
					arrayList2.add("");
					misReport3.setInputData("");
					misReport3.setKpiid(rs.getString(1));
					arrayList3.add(misReport3);
				}else{
					if(i==1){
						arrayList2.add(misReport2.getInput1());
						misReport3.setKpiid(rs.getString(1));
						misReport3.setInputData(misReport2.getInput1());
						arrayList3.add(misReport3);
						arrayList2.add(misReport2.getInput1());
						misReport3.setKpiid(rs.getString(1));
						misReport3.setInputData("");
						arrayList3.add(misReport3);
					}else if(i==2){
						arrayList2.add(misReport2.getInput2());
						misReport3.setKpiid(rs.getString(1));
						misReport3.setInputData(misReport2.getInput2());
						arrayList3.add(misReport3);
						arrayList2.add("");
						misReport3.setKpiid(rs.getString(1));
						misReport3.setInputData("");
						arrayList3.add(misReport3);
					}else if(i==3){
						arrayList2.add(misReport2.getInput3());
						misReport3.setKpiid(rs.getString(1));
						misReport3.setInputData(misReport2.getInput3());
						arrayList3.add(misReport3);
						arrayList2.add("");
						misReport3.setKpiid(rs.getString(1));
						misReport3.setInputData("");
						arrayList3.add(misReport3);
					}else if(i==4){
						arrayList2.add(misReport2.getInput4());
						misReport3.setKpiid(rs.getString(1));
						misReport3.setInputData(misReport2.getInput4());
						arrayList3.add(misReport3);
						arrayList2.add("");
						misReport3.setKpiid(rs.getString(1));
						misReport3.setInputData("");
						arrayList3.add(misReport3);
					}else if(i==5){
						arrayList2.add(misReport2.getInput5());
						misReport3.setKpiid(rs.getString(1));
						misReport3.setInputData(misReport2.getInput5());
						arrayList3.add(misReport3);
						arrayList2.add("");
						misReport3.setKpiid(rs.getString(1));
						misReport3.setInputData("");
						arrayList3.add(misReport3);
					}
				}*/
			}
			misReport.setNo_of_inputList(arrayList2);
			misReport.setNoofinputlist(arrayList3);
			
			if(misReport2.getResult()==null){
				misReport.setResult("");
			}else{	
				//misReport.setResult(misReport2.getResult());
				misReport.setResult("");
			}
			
			if(misReport2.getTarget()==null){
				misReport.setTarget("");
			}else{	
				//misReport.setTarget(misReport2.getTarget());
				misReport.setTarget("");
			}
			
			if(misReport2.getKpi_dataid()==null){
				misReport.setKpi_dataid("0");
			}else if(misReport2.getKpi_dataid().equals("")){
				misReport.setKpi_dataid("0");
			}else{	
				misReport.setKpi_dataid(misReport2.getKpi_dataid());
			}
			misReport.setKpi_year(year_filter);
			misReport.setKpi_month(month_filter);
			arrayList.add(misReport);
		}
	} catch (Exception e) {
		e.printStackTrace();
	}
	return arrayList;
}

public int getTimeTakenForDischarge(String kpiid, String year_filter, String month_filter, String kpidataid) {
	int result =0;
	try {
		String[] data = year_filter.split("-");
		String fromdate="";
		String todate ="";
		if(Integer.parseInt(month_filter)<=3){
			fromdate =  data[1]+"-"+month_filter+"-"+"01";
			todate =  data[1]+"-"+month_filter+"-"+"31";
		}else{
			fromdate =  data[0]+"-"+month_filter+"-"+"01";
			todate =  data[0]+"-"+month_filter+"-"+"31";
		}
		todate = todate+" "+"59:59:59";
		int totalcount =0;
		int totalavail =0;
		//SELECT * FROM ochri.apm_treatment_episode where dischargedate>= '2017-09-01' and dischargedate<= '2017-09-30'
		
		String sql1="select id from apm_treatment_episode where dischargedate between '"+fromdate+"' and '"+todate+"' and treatmentstatus=1";
		PreparedStatement preparedStatement1 = connection.prepareStatement(sql1);
		ResultSet rs1 = preparedStatement1.executeQuery();
		double min = 0.0;
		while (rs1.next()) {
			totalcount++;
			String sql ="select id,dis_initiate_time,dis_nursing_time from apm_treatment_episode where id='"+rs1.getString(1)+"' and dschargestatus='3'";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			while (rs.next()) {
					String startdate = rs.getString(2);
					String enddate = rs.getString(3);
					Date d1 = format.parse(startdate);
					Date d2 = format.parse(enddate);
					long diff = d2.getTime() - d1.getTime();
					/*long diffSeconds = diff / 1000 % 60;
					long diffMinutes = diff / (60 * 1000) % 60;
					long diffHours = diff / (60 * 60 * 1000) % 24;
					long diffDays = diff / (24 * 60 * 60 * 1000);*/
					min = min+ diff/60000.0;
			}
		}
		updateKPITempTable(""+min,""+totalcount,"","","");
		MisReport misReport = getKPIMasterData(kpiid);
		String finalresult = getCalKPIResult(misReport);
		if(finalresult==null){
			finalresult = ""+Math.round(0.0 * 100.0) / 100.0;
		}else if(finalresult.equals("")){
			finalresult = ""+Math.round(0.0 * 100.0) / 100.0;
		}else{
			finalresult = ""+Math.round(Double.parseDouble(finalresult) * 100.0) / 100.0;
		}
		if(kpidataid.equals("0")){
			misReport.setUserid(null);
			misReport.setDatetime(null);
			min = Math.round(min * 100.0) / 100.0;
			insertNewKPIRecord(""+min,""+totalcount,"","","",finalresult,misReport.getKpiindicatorid(),misReport,year_filter,month_filter,"0",null);
		}else{
			min = Math.round(min * 100.0) / 100.0;
			updateKPIData(""+min,""+totalcount,"","","",finalresult,kpiid,null,null,kpidataid,"0",null);
		}
	} catch (Exception e) {
		e.printStackTrace();
	}
	return result;
}

public int getMortalityNF(String kpiid, String year_filter, String month_filter, String kpidataid) {
	int result =0;
	try {
		String[] data = year_filter.split("-");
		String fromdate="";
		String todate ="";
		if(Integer.parseInt(month_filter)<=3){
			fromdate =  data[1]+"-"+month_filter+"-"+"01";
			todate =  data[1]+"-"+month_filter+"-"+"31";
		}else{
			fromdate =  data[0]+"-"+month_filter+"-"+"01";
			todate =  data[0]+"-"+month_filter+"-"+"31";
		}
		todate = todate+" "+"59:59:59";
		int totalcount =0;
		int totalavail =0;
		//SELECT * FROM ochri.apm_treatment_episode where dischargedate>= '2017-09-01' and dischargedate<= '2017-09-30'
		String sql1="select id from apm_treatment_episode where dischargedate between '"+fromdate+"' and '"+todate+"'";
		PreparedStatement preparedStatement1 = connection.prepareStatement(sql1);
		ResultSet rs1 = preparedStatement1.executeQuery();
		while (rs1.next()) {
			totalcount++;
			String sql ="select id,dschargestatus from apm_treatment_episode where id='"+rs1.getString(1)+"' and dschargestatus='3'";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
					totalavail++;
			}
		}
		
		
		updateKPITempTable(""+totalavail,""+totalcount,"","","");
		MisReport misReport = getKPIMasterData(kpiid);
		String finalresult = getCalKPIResult(misReport);
		if(finalresult==null){
			finalresult = ""+Math.round(0.0 * 100.0) / 100.0;
		}else if(finalresult.equals("")){
			finalresult = ""+Math.round(0.0 * 100.0) / 100.0;
		}else{
			finalresult = ""+Math.round(Double.parseDouble(finalresult) * 100.0) / 100.0;
		}
		if(kpidataid.equals("0")){
			misReport.setUserid(null);
			misReport.setDatetime(null);
			insertNewKPIRecord(""+totalavail,""+totalcount,"","","",finalresult,misReport.getKpiindicatorid(),misReport,year_filter,month_filter,"0",null);
		}else{
			updateKPIData(""+totalavail,""+totalcount,"","","",finalresult,kpiid,null,null,kpidataid,"0",null);
		}
	} catch (Exception e) {
		e.printStackTrace();
	}
	return result;
}

public int getMrdFileNF(String kpiid, String year_filter, String month_filter, String kpidataid) {
	int result =0;
	try {
		String[] data = year_filter.split("-");
		String fromdate="";
		String todate ="";
		if(Integer.parseInt(month_filter)<=3){
			fromdate =  data[1]+"-"+month_filter+"-"+"01";
			todate =  data[1]+"-"+month_filter+"-"+"31";
		}else{
			fromdate =  data[0]+"-"+month_filter+"-"+"01";
			todate =  data[0]+"-"+month_filter+"-"+"31";
		}
		todate = todate+" "+"59:59:59";
		int totalcount =0;
		int totalavail =0;
		
		String sql1="select id,history from apm_consultation_note where lastmodified between '"+fromdate+"' and '"+todate+"' and appointmentid!='0'";
		PreparedStatement preparedStatement1 = connection.prepareStatement(sql1);
		ResultSet rs1 = preparedStatement1.executeQuery();
		while (rs1.next()) {
				totalcount++;
				if(rs1.getString(2)!=null){
					if(!rs1.getString(2).equals("")){
						totalavail++;
					}
				}
		}
		
		int total = totalcount-totalavail;
		
		updateKPITempTable(""+total,""+totalcount,"","","");
		MisReport misReport = getKPIMasterData(kpiid);
		String finalresult = getCalKPIResult(misReport);
		if(finalresult==null){
			finalresult = ""+Math.round(0.0 * 100.0) / 100.0;
		}else if(finalresult.equals("")){
			finalresult = ""+Math.round(0.0 * 100.0) / 100.0;
		}else{
			finalresult = ""+Math.round(Double.parseDouble(finalresult) * 100.0) / 100.0;
		}
		if(kpidataid.equals("0")){
			misReport.setUserid(null);
			misReport.setDatetime(null);
			insertNewKPIRecord(""+total,""+totalcount,"","","",finalresult,misReport.getKpiindicatorid(),misReport,year_filter,month_filter,"0",null);
		}else{
			updateKPIData(""+total,""+totalcount,"","","",finalresult,kpiid,null,null,kpidataid,"0",null);
		}
	} catch (Exception e) {
		e.printStackTrace();
	}
	return result;
}

public int getMrdDisSumRecordNF(String kpiid, String year_filter, String month_filter,String kpidataid) {
	int result =0;
	try {
		String[] data = year_filter.split("-");
		String fromdate="";
		String todate ="";
		if(Integer.parseInt(month_filter)<=3){
			fromdate =  data[1]+"-"+month_filter+"-"+"01";
			todate =  data[1]+"-"+month_filter+"-"+"31";
		}else{
			fromdate =  data[0]+"-"+month_filter+"-"+"01";
			todate =  data[0]+"-"+month_filter+"-"+"31";
		}
		todate = todate+" "+"59:59:59";
		int totalcount =0;
		int totalavail =0;
		
		String sql1="select treatmentepisodeid from ipd_addmission_form where admissiondsate between '"+fromdate+"' and '"+todate+"'";
		PreparedStatement preparedStatement1 = connection.prepareStatement(sql1);
		ResultSet rs1 = preparedStatement1.executeQuery();
		while (rs1.next()) {
			String sql ="select id,hospcourse from apm_treatment_episode where id='"+rs1.getString(1)+"' and treatmentstatus='1'";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				totalcount++;
				if(rs.getString(2)!=null){
					if(!rs.getString(2).equals("")){
						totalavail++;
					}
				}
			}
		}
		int total = totalcount-totalavail;
		
		updateKPITempTable(""+total,""+totalcount,"","","");
		MisReport misReport = getKPIMasterData(kpiid);
		String finalresult = getCalKPIResult(misReport);
		if(finalresult==null){
			finalresult = ""+Math.round(0.0 * 100.0) / 100.0;
		}else if(finalresult.equals("")){
			finalresult = ""+Math.round(0.0 * 100.0) / 100.0;
		}else{
			finalresult = ""+Math.round(Double.parseDouble(finalresult) * 100.0) / 100.0;
		}
		if(kpidataid.equals("0")){
			misReport.setUserid(null);
			misReport.setDatetime(null);
			insertNewKPIRecord(""+total,""+totalcount,"","","",finalresult,misReport.getKpiindicatorid(),misReport,year_filter,month_filter,"0",null);
		}else{
			updateKPIData(""+total,""+totalcount,"","","",finalresult,kpiid,null,null,kpidataid,"0",null);
		}
	} catch (Exception e) {
		e.printStackTrace();
	}
	return result;
}

public int checkKPIAvailable(int kpiid, String kpi_year, String month) {
	PreparedStatement preparedStatement = null;
	int flag = 0;
	try{
		String sql = "select id from kpi_data where kpiid="+kpiid+" and kpi_year='"+kpi_year+"' and kpi_month='"+month+"' ";
		preparedStatement = connection.prepareStatement(sql);
		ResultSet rs = preparedStatement.executeQuery();
		while(rs.next()){
			flag=rs.getInt(1);
		}
	}catch(Exception e){
		e.printStackTrace();
	}
	return flag;
}



public MisReport getKPITransactionData(String kid, String year_filter, String month_filter) {
	MisReport misReport = new MisReport();
	try {
		
		StringBuilder builder = new StringBuilder();
		builder.append("select id, kpiid, kpi_year, kpi_month, input1, input2, input3, input4, input5, result, comment, userid, datetime, edit_userid, edit_datetime,month_target,evidence ");
		builder.append("from kpi_data where kpiid='"+kid+"' ");
		if(year_filter!=null){
			builder.append("and kpi_year='"+year_filter+"' ");
		}
		if(month_filter!=null){
			builder.append("and kpi_month='"+month_filter+"' ");
		}
		PreparedStatement preparedStatement = connection.prepareStatement(builder.toString());
		ResultSet rs = preparedStatement.executeQuery();
		misReport.setIs_active("0");
		while (rs.next()) {
			misReport.setKpi_dataid(rs.getString(1));
			misReport.setKpiid(rs.getString(2));
			misReport.setKpi_year(rs.getString(3));
			misReport.setKpi_month(rs.getString(4));
			misReport.setInput1(rs.getString(5));
			misReport.setInput2(rs.getString(6));
			misReport.setInput3(rs.getString(7));
			misReport.setInput4(rs.getString(8));
			misReport.setInput5(rs.getString(9));
			misReport.setResult(rs.getString(10));
			misReport.setComment(rs.getString(11));
			misReport.setUserid(rs.getString(12));
			misReport.setDatetime(rs.getString(13));
			misReport.setEdit_userid(rs.getString(14));
			misReport.setEdit_datetime(rs.getString(15));
			misReport.setTarget(rs.getString(16));
			misReport.setEvidence(rs.getString(17));
			misReport.setIs_active("1");
		}
	} catch (Exception e) {
		e.printStackTrace();
	}
	return misReport;
}

public int updateKPITempTable(String input1, String input2, String input3, String input4, String input5) {
	int result = 0;
	try {
		String sql = "insert into kpi_temp (input1, input2, input3, input4, input5) values (?,?,?,?,?)";
		PreparedStatement ps = connection.prepareStatement(sql);
		ps.setString(1, input1);
		ps.setString(2, input2);
		ps.setString(3, input3);
		ps.setString(4, input4);
		ps.setString(5, input5);
		result = ps.executeUpdate();
	} catch (Exception e) {

		e.printStackTrace();
	}

	return result;
}

public String getCalKPIResult(MisReport misReport) {
	PreparedStatement preparedStatement = null;
	String result = "";
	try{
		String sql = "select "+misReport.getFormula()+" from kpi_temp";
		preparedStatement = connection.prepareStatement(sql);
		ResultSet rs = preparedStatement.executeQuery();
		while(rs.next()){
			result = rs.getString(1);
		}
		
		String sql1 = "truncate kpi_temp";
		PreparedStatement preparedStatement2 = connection.prepareStatement(sql1);
		preparedStatement2.executeUpdate();
	}catch(Exception e){
		e.printStackTrace();
	}
	return result;
}


public Boolean checkKPIStatus(String kpiid, String kpi_year, String month) {
	PreparedStatement preparedStatement = null;
	
	boolean flag = false;
	try{
		String sql = "select id from kpi_data where kpiid="+kpiid+" and kpi_year='"+kpi_year+"' and kpi_month='"+month+"' ";
		preparedStatement = connection.prepareStatement(sql);
		ResultSet rs = preparedStatement.executeQuery();
		while(rs.next()){
			flag=true;
		}
	}catch(Exception e){
		e.printStackTrace();
	}
	return flag;
}


public int updateKPIData(String input1, String input2, String input3, String input4, String input5, String final_result,
		String kpiid, String todate, String userid,String kpi_dataid,String target, String evidence) {
	int result = 0;
	try {
		String sql = "update kpi_data set input1='"+input1+"', input2='"+input2+"', input3='"+input3+"', input4='"+input4+"', input5='"+input5+"', result='"+final_result+"',edit_userid='"+userid+"', edit_datetime='"+todate+"',month_target='"+target+"',evidence='"+evidence+"' where id='"+kpi_dataid+"'";
		PreparedStatement ps = connection.prepareStatement(sql);
		result = ps.executeUpdate();
	} catch (Exception e) {

		e.printStackTrace();
	}
	return result;
}


public int insertNewKPIRecord(String input1, String input2, String input3, String input4, String input5,
		String final_result, String indicatorid, MisReport misReport, String kpi_year, String month,String target,String evidence) {
	//id, areaid, indicatorid, input1, input1_promt, input2, input2_promt, input3, input3_promt, input4, input4_promt, input5, input5_promt, result, kpi_year, kpi_month
	
	int result = 0;
	try {
		//String sql = "insert into kpi_data (input1, input2, input3, input4, input5,areaid,indicatorid,result,kpi_year,kpi_month) values (?,?,?,?,?,?,?,?,?,?)";
		String sql = "insert into kpi_data (kpiid, kpi_year, kpi_month, input1, input2, input3, input4, input5, result, userid, datetime,month_target,evidence) values (?,?,?,?,?,?,?,?,?,?,?,?,?)";
		PreparedStatement ps = connection.prepareStatement(sql);
		ps.setString(1, misReport.getKpiid());
		ps.setString(2, kpi_year);
		ps.setString(3, month);
		ps.setString(4, input1);
		ps.setString(5, input2);
		ps.setString(6, input3);
		ps.setString(7, input4);
		ps.setString(8, input5);
		ps.setString(9, final_result);
		ps.setString(10, misReport.getUserid());
		ps.setString(11, misReport.getDatetime());
		ps.setString(12, target);
		ps.setString(13, evidence);
		result = ps.executeUpdate();
	} catch (Exception e) {
		e.printStackTrace();
	}
	return result;
}


public ArrayList<String> getKPIID() {
	ArrayList<String> arrayList = new ArrayList<String>();
	String result = "";
	try{
		PreparedStatement preparedStatement = null;
		String sql = "select id from kpi_master";
		preparedStatement = connection.prepareStatement(sql);
		ResultSet rs = preparedStatement.executeQuery();
		while(rs.next()){
			result = rs.getString(1);
			arrayList.add(result);
		}
	}catch(Exception e){
		e.printStackTrace();
	}
	return arrayList;
}

public boolean getIsKPIDetailsAvailable(String kid, String year_filter, String month_filter) {
	boolean flag = false;
	try{
		//id, input1, input2, input3, input4, input5, result, kpi_month, kpi_year, kpiid
		PreparedStatement preparedStatement = null;
		String sql = "select id from kpi_transaction where kpiid='"+kid+"' and kpi_year='"+year_filter+"' and kpi_month='"+month_filter+"'";
		preparedStatement = connection.prepareStatement(sql);
		ResultSet rs = preparedStatement.executeQuery();
		while(rs.next()){
			flag = true;
		}
		if(flag){
			String sql1 = "insert into kpi_transaction (kpi_month, kpi_year, kpiid) values (?,?,?)";
			PreparedStatement ps = connection.prepareStatement(sql1);
			ps.setString(1, month_filter);
			ps.setString(2, year_filter);
			ps.setString(3, kid);
			ps.executeUpdate();
		}
	}catch(Exception e){
		e.printStackTrace();
	}
	return flag;
}


public MisReport getKPIMasterData(String kpiid) {
	MisReport misReport = new MisReport();
	try {
		StringBuilder builder = new StringBuilder();
		builder.append("select kpi_master.id, areaid, indicatorid, no_of_input, prompt1, prompt2, prompt3, prompt4, prompt5, formula, formula_desc ");
		builder.append("from kpi_master  where id='"+kpiid+"'");
		PreparedStatement preparedStatement = connection.prepareStatement(builder.toString());
		ResultSet rs = preparedStatement.executeQuery();
		while (rs.next()) {
			misReport.setKpiid(rs.getString(1));
			misReport.setKpiareaid(rs.getString(2));
			misReport.setKpiindicatorid(rs.getString(3));
			misReport.setNo_of_input(rs.getString(4));
			misReport.setFormula(rs.getString(10));
			misReport.setFormula_desc(rs.getString(11));
		}
	} catch (Exception e) {
		e.printStackTrace();
	}
	return misReport;
}

public ArrayList<MisReport> getAllGrahicalKPIList(String kpiarea_filter, String year_filter, String month_filter) {
	ArrayList<MisReport> arrayList = new ArrayList<MisReport>();
	try {
		
		StringBuilder builder = new StringBuilder();
		builder.append("select kpi_master.id, kpi_master.areaid, indicatorid, no_of_input, prompt1, prompt2, prompt3, prompt4, prompt5, formula, formula_desc ");
		builder.append(",area,indicator ");
		builder.append("from kpi_master ");
		builder.append("inner join kpi_area on kpi_area.id = kpi_master.areaid  ");
		builder.append("inner join kpi_indicator on kpi_indicator.id = kpi_master.indicatorid ");
		if(kpiarea_filter!=null){
			builder.append("where kpi_master.areaid='"+kpiarea_filter+"' ");
		}
		
		PreparedStatement preparedStatement = connection.prepareStatement(builder.toString());
		ResultSet rs = preparedStatement.executeQuery();
		while (rs.next()) {
			MisReport misReport = new MisReport();
			misReport = getYearWiseData(year_filter,rs.getString(1));
			misReport.setKpiid(rs.getString(1));
			misReport.setKpi_year(year_filter);
			misReport.setKpiarea(rs.getString(12));
			misReport.setKpiindicator(rs.getString(13));
			arrayList.add(misReport);
		}
	} catch (Exception e) {
		e.printStackTrace();
	}
	return arrayList;
}

public MisReport getYearWiseData(String year_filter, String kpiid) {
	MisReport misReport = new MisReport();
	try {
		for(int i=1;i<=12;i++){
			String s="";
			if(i==1){
				s="01";
				String sql ="select result,month_target from kpi_data where kpi_year='"+year_filter+"' and kpi_month='"+s+"' and kpiid='"+kpiid+"'";
				PreparedStatement preparedStatement = connection.prepareStatement(sql);
				ResultSet rs = preparedStatement.executeQuery();
				misReport.setMonth1("0.0");
				misReport.setMonth1_target("0.0");
				while (rs.next()) {
					misReport.setMonth1(""+rs.getDouble(1));
					misReport.setMonth1_target(""+rs.getDouble(2));
				}
			}else if(i==2){
				s="02";
				String sql ="select result,month_target from kpi_data where kpi_year='"+year_filter+"' and kpi_month='"+s+"' and kpiid='"+kpiid+"'";
				PreparedStatement preparedStatement = connection.prepareStatement(sql);
				ResultSet rs = preparedStatement.executeQuery();
				misReport.setMonth2("0.0");
				misReport.setMonth2_target("0.0");
				while (rs.next()) {
					misReport.setMonth2(""+rs.getDouble(1));
					misReport.setMonth2_target(""+rs.getDouble(2));
				}
			}else if(i==3){
				s="03";
				String sql ="select result,month_target from kpi_data where kpi_year='"+year_filter+"' and kpi_month='"+s+"' and kpiid='"+kpiid+"'";
				PreparedStatement preparedStatement = connection.prepareStatement(sql);
				ResultSet rs = preparedStatement.executeQuery();
				misReport.setMonth3("0.0");
				misReport.setMonth3_target("0.0");
				while (rs.next()) {
					misReport.setMonth3(""+rs.getDouble(1));
					misReport.setMonth3_target(""+rs.getDouble(2));
				}
			}else if(i==4){
				s="04";
				String sql ="select result,month_target from kpi_data where kpi_year='"+year_filter+"' and kpi_month='"+s+"' and kpiid='"+kpiid+"'";
				PreparedStatement preparedStatement = connection.prepareStatement(sql);
				ResultSet rs = preparedStatement.executeQuery();
				misReport.setMonth4("0.0");
				misReport.setMonth4_target("0.0");
				while (rs.next()) {
					misReport.setMonth4(""+rs.getDouble(1));
					misReport.setMonth4_target(""+rs.getDouble(2));
				}
			}else if(i==5){
				s="05";
				String sql ="select result,month_target from kpi_data where kpi_year='"+year_filter+"' and kpi_month='"+s+"' and kpiid='"+kpiid+"'";
				PreparedStatement preparedStatement = connection.prepareStatement(sql);
				ResultSet rs = preparedStatement.executeQuery();
				misReport.setMonth5("0.0");
				misReport.setMonth5_target("0.0");
				while (rs.next()) {
					misReport.setMonth5(""+rs.getDouble(1));
					misReport.setMonth5_target(""+rs.getDouble(2));
				}
			}else if(i==6){
				s="06";
				String sql ="select result,month_target from kpi_data where kpi_year='"+year_filter+"' and kpi_month='"+s+"' and kpiid='"+kpiid+"'";
				PreparedStatement preparedStatement = connection.prepareStatement(sql);
				ResultSet rs = preparedStatement.executeQuery();
				misReport.setMonth6("0.0");
				misReport.setMonth6_target("0.0");
				while (rs.next()) {
					misReport.setMonth6(""+rs.getDouble(1));
					misReport.setMonth6_target(""+rs.getDouble(2));
				}
			}else if(i==7){
				s="07";
				String sql ="select result,month_target from kpi_data where kpi_year='"+year_filter+"' and kpi_month='"+s+"' and kpiid='"+kpiid+"'";
				PreparedStatement preparedStatement = connection.prepareStatement(sql);
				ResultSet rs = preparedStatement.executeQuery();
				misReport.setMonth7("0.0");
				misReport.setMonth7_target("0.0");
				while (rs.next()) {
					misReport.setMonth7(""+rs.getDouble(1));
					misReport.setMonth7_target(""+rs.getDouble(2));
				}
			}else if(i==8){
				s="08";
				String sql ="select result,month_target from kpi_data where kpi_year='"+year_filter+"' and kpi_month='"+s+"' and kpiid='"+kpiid+"'";
				PreparedStatement preparedStatement = connection.prepareStatement(sql);
				ResultSet rs = preparedStatement.executeQuery();
				misReport.setMonth8("0.0");
				misReport.setMonth8_target("0.0");
				while (rs.next()) {
					misReport.setMonth8(""+rs.getDouble(1));
					misReport.setMonth8_target(""+rs.getDouble(2));
				}
			}else if(i==9){
				s="09";
				String sql ="select result,month_target from kpi_data where kpi_year='"+year_filter+"' and kpi_month='"+s+"' and kpiid='"+kpiid+"'";
				PreparedStatement preparedStatement = connection.prepareStatement(sql);
				ResultSet rs = preparedStatement.executeQuery();
				misReport.setMonth9("0.0");
				misReport.setMonth9_target("0.0");
				while (rs.next()) {
					misReport.setMonth9(""+rs.getDouble(1));
					misReport.setMonth9_target(""+rs.getDouble(2));
				}
			}else if(i==10){
				s="10";
				String sql ="select result,month_target from kpi_data where kpi_year='"+year_filter+"' and kpi_month='"+s+"' and kpiid='"+kpiid+"'";
				PreparedStatement preparedStatement = connection.prepareStatement(sql);
				ResultSet rs = preparedStatement.executeQuery();
				misReport.setMonth10("0.0");
				misReport.setMonth10_target("0.0");
				while (rs.next()) {
					misReport.setMonth10(""+rs.getDouble(1));
					misReport.setMonth10_target(""+rs.getDouble(2));
				}
			}else if(i==11){
				s="11";
				String sql ="select result,month_target from kpi_data where kpi_year='"+year_filter+"' and kpi_month='"+s+"' and kpiid='"+kpiid+"'";
				PreparedStatement preparedStatement = connection.prepareStatement(sql);
				ResultSet rs = preparedStatement.executeQuery();
				misReport.setMonth11("0.0");
				misReport.setMonth11_target("0.0");
				while (rs.next()) {
					misReport.setMonth11(""+rs.getDouble(1));
					misReport.setMonth11_target(""+rs.getDouble(2));
				}
			}else if(i==12){
				s="12";
				String sql ="select result,month_target from kpi_data where kpi_year='"+year_filter+"' and kpi_month='"+s+"' and kpiid='"+kpiid+"'";
				PreparedStatement preparedStatement = connection.prepareStatement(sql);
				ResultSet rs = preparedStatement.executeQuery();
				misReport.setMonth12("0.0");
				misReport.setMonth12_target("0.0");
				while (rs.next()) {
					misReport.setMonth12(""+rs.getDouble(1));
					misReport.setMonth12_target(""+rs.getDouble(2));
				}
			}
			
		}
	} catch (Exception e) {
		e.printStackTrace();
	}
	return misReport;
}


public MisReport getIndiKPIData1(String kpiid,String month_filter,String year_filter) {
	MisReport misReport = new MisReport();
	try {
		
		StringBuilder builder = new StringBuilder();
		builder.append("select kpi_master.id,area,indicator,kpi_year, kpi_month, input1, input2, input3, input4, input5, result, month_target,ismannual, function_name from kpi_master ");
		builder.append("inner join kpi_data on kpi_data.kpiid = kpi_master.id ");
		builder.append("inner join kpi_area on kpi_area.id = kpi_master.areaid ");
		builder.append("inner join kpi_indicator on kpi_indicator.id = kpi_master.indicatorid ");
		builder.append("where kpi_data.kpi_month='"+month_filter+"' and kpi_data.kpi_year='"+year_filter+"' and kpi_data.kpiid='"+kpiid+"' ");
		PreparedStatement preparedStatement = connection.prepareStatement(builder.toString());
		ResultSet rs = preparedStatement.executeQuery();
		while (rs.next()) {
			misReport.setKpiid(rs.getString(1));
			misReport.setKpiarea(rs.getString(2));
			misReport.setKpiindicator(rs.getString(3));
			misReport.setKpi_year(rs.getString(4));
			misReport.setKpi_month(rs.getString(5));
			misReport.setInput1(rs.getString(6));
			misReport.setInput2(rs.getString(7));
			misReport.setInput3(rs.getString(8));
			misReport.setInput4(rs.getString(9));
			misReport.setInput5(rs.getString(10));
			misReport.setResult(rs.getString(11));
			misReport.setTarget(rs.getString(12));
			misReport.setIsmannual(rs.getString(13));
			misReport.setFname(rs.getString(14));
			ArrayList<MisReport>  arrayList = new ArrayList<MisReport>();
			//27 oct 2017
			
				if(rs.getString(14)!=null){
					if(!rs.getString(14).equals("")){
						int kpidataid =  checkKPIAvailable(rs.getInt(1), year_filter, month_filter);
						if(rs.getString(14).equals("mrddissumnotfound")){
							arrayList = getMrdDisSumRecordNFList(rs.getString(1),year_filter,month_filter,""+kpidataid);
						}else if(rs.getString(14).equals("mrdfilemissing")){
							arrayList = getMrdFileNFList(rs.getString(1),year_filter,month_filter,""+kpidataid);
						}else if(rs.getString(14).equals("mortality")){
							arrayList = getMortalityNFList(rs.getString(1),year_filter,month_filter,""+kpidataid);
						}else if(rs.getString(14).equals("timetakenfordischarge")){
							arrayList = getTimeTakenForDischargeList(rs.getString(1),year_filter,month_filter,""+kpidataid);
						}
						else if(rs.getString(14).equals("waitingtimeopd")){
							/*arrayList = getTimeTakenForDischargeList(rs.getString(1),year_filter,month_filter,""+kpidataid);*/
							arrayList = getWaitingTimeOpdList(rs.getString(1),year_filter,month_filter,""+kpidataid);
						}
					else if(rs.getString(14).equals("waitingtimelab")){
						/*arrayList = getTimeTakenForDischargeList(rs.getString(1),year_filter,month_filter,""+kpidataid);*/
						arrayList = getWaitingTimeLabList(rs.getString(1),year_filter,month_filter,""+kpidataid);
					}else if("redosoftest".equals(rs.getString(14))||"reschudled_ot".equals(rs.getString(14))){
						/*arrayList = getTimeTakenForDischargeList(rs.getString(1),year_filter,month_filter,""+kpidataid);*/
						arrayList = getLabRedoTestList(year_filter,misReport.getKpiid());
					}else if("icu_utilization_rate".equals(rs.getString(14))){
						/*arrayList = getTimeTakenForDischargeList(rs.getString(1),year_filter,month_filter,""+kpidataid);*/
						arrayList = getLabRedoTestList(year_filter,misReport.getKpiid());
					}	
				}
				}
			misReport.setDatalist(arrayList);
		}
	} catch (Exception e) {
		e.printStackTrace();
	}
	return misReport;
}


private ArrayList<MisReport> getLabRedoTestList(String year, String kpiId){
	ArrayList<MisReport> list= new ArrayList<MisReport>();
	try {
		PreparedStatement ps= connection.prepareStatement(" select input1,input2,result,kpi_month from kpi_data where kpi_year='"+year+"' and kpiid='"+kpiId+"'");
		ResultSet rs=ps.executeQuery();
		while ( rs.next()) {
			MisReport misReport= new MisReport();
			misReport.setInput1(rs.getString(1));
			misReport.setInput2(rs.getString(2));
			misReport.setResult(rs.getString(3));
			Month month=Month.of(DateTimeUtils.convertToInteger(rs.getString(4)));
			misReport.setKpi_month(month.toString());
			list.add(misReport);
		}
	} catch (Exception e) {
		e.printStackTrace();
	}
	return list;
}

private ArrayList<MisReport> getWaitingTimeLabList(String kpiid, String year_filter, String month_filter, String kpidataid) {
	ArrayList<MisReport>arrayList = new ArrayList<MisReport>();
	try {
		
		String[] data = year_filter.split("-");
		String fromdate="";
		String todate ="";
		if(Integer.parseInt(month_filter)<=3){
			fromdate =  data[1]+"-"+month_filter+"-"+"01";
			todate =  data[1]+"-"+month_filter+"-"+"31";
		}else{
			fromdate =  data[0]+"-"+month_filter+"-"+"01";
			todate =  data[0]+"-"+month_filter+"-"+"31";
		}
		todate = todate+" "+"59:59:59";
		StringBuffer buffer = new StringBuffer();
		buffer.append("SELECT compdate,dateofcompleted,clientid FROM apm_client_parent_investigation ");
		buffer.append("where lastmodified between '"+fromdate+"' and '"+todate+"' and compdate!='null'and dateofcompleted!='null' and jobtitle='Pathlab'");
		PreparedStatement preparedStatement1 = connection.prepareStatement(buffer.toString());
		ResultSet rs1 = preparedStatement1.executeQuery();
		ClientDAO clientDAO = new JDBCClientDAO(connection);
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		while (rs1.next()) {
			double time=0;
			double temp=0;
			MisReport misReport = new MisReport();
			String enddate = rs1.getString(2);
			String startdate = rs1.getString(1);
			
			Date d1 = format.parse(startdate);
			Date d2 = format.parse(enddate);
			long diff = d2.getTime() - d1.getTime();
			if(enddate!=null && startdate!=null){
				if(!enddate.equals("") && (!startdate.equals(""))){
					time=diff/60000.0;
					 temp = Math.round(time * 100.0) / 100.0;
				}
			}
			
			
		/*	MisReport misReport = new MisReport();*/
			Client client = clientDAO.getSelectedSessionClientDetails(rs1.getString(3));
			misReport.setClientname(client.getClientName());
			misReport.setAverage(String.valueOf(temp));
			arrayList.add(misReport);
		}
		
	} catch (Exception e) {
		e.printStackTrace();
	}
	return arrayList;
}

private ArrayList<MisReport> getWaitingTimeOpdList(String kpiid, String year_filter, String month_filter, String kpidataid) {
	ArrayList<MisReport>arrayList = new ArrayList<MisReport>();
	try {
		StringBuffer buffer = new StringBuffer();
		String[] data = year_filter.split("-");
		String fromdate="";
		String todate ="";
		if(Integer.parseInt(month_filter)<=3){
			fromdate =  data[1]+"-"+month_filter+"-"+"01";
			todate =  data[1]+"-"+month_filter+"-"+"31";
		}else{
			fromdate =  data[0]+"-"+month_filter+"-"+"01";
			todate =  data[0]+"-"+month_filter+"-"+"31";
		}
		todate = todate+" "+"59:59:59";
		buffer.append("select apm_appointment_log.date,apm_invoice_assesments.invoiceid,apm_appointment_log.clientId from apm_appointment_log ");
		buffer.append("inner join apm_invoice_assesments on apm_appointment_log.appmt_id = apm_invoice_assesments.appointmentid ");
		//buffer.append("inner join apm_invoice_assesments on apm_appointment_log.appmt_id = apm_invoice_assesments.appointmentid ");
		buffer.append("where apm_appointment_log.status='Being Seen' and apm_invoice_assesments.appointmentid !='0' and date between '"+fromdate+"' and '"+todate+"'");
		PreparedStatement preparedStatement = connection.prepareStatement(buffer.toString());
		ResultSet rs = preparedStatement.executeQuery();
		ClientDAO clientDAO = new JDBCClientDAO(connection);
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		while (rs.next()) {
			double temp=0;
			String enddate = rs.getString(1);
			/*String enddate = rs.getString(2);*/
			StringBuffer buffer2 = new StringBuffer();
			buffer2.append("select invoiceDate from apm_charges_assesment ");
			buffer2.append("inner join apm_charges_invoice_log on apm_charges_assesment.chargeinvoiceid = apm_charges_invoice_log.id ");
			buffer2.append("where apm_charges_assesment.invoiceid='"+rs.getString(2)+"'");
			preparedStatement = connection.prepareStatement(buffer2.toString());
			ResultSet rs1 = preparedStatement.executeQuery();
			String startdate="";
			while (rs1.next()) {
				double time=0;
				
				startdate = rs1.getString(1);
				
				
				/*startdate = rs1.getString(1);*/
				
				Date d1 = format.parse(startdate);
				Date d2 = format.parse(enddate);
				long diff = d2.getTime() - d1.getTime();
				if(enddate!=null && startdate!=null){
					if(!enddate.equals("") && (!startdate.equals(""))){
					time= diff/60000.0;
					temp = Math.round(time * 100.0) / 100.0;
					}
				}
				
				
			
			}
			if(enddate!=null && startdate!=null){
				if(!enddate.equals("") && (!startdate.equals(""))){
					MisReport misReport = new MisReport();
					Client client = clientDAO.getSelectedSessionClientDetails(rs.getString(3));
					misReport.setClientname(client.getClientName());
					misReport.setAverage(String.valueOf(temp));
					arrayList.add(misReport);
				
				}
			}
		}
	} catch (Exception e) {
		e.printStackTrace();
	}
	return arrayList;
}

public ArrayList<MisReport> getTimeTakenForDischargeList(String kpiid, String year_filter, String month_filter, String kpidataid) {
	ArrayList<MisReport> arrayList = new ArrayList<MisReport>();
	try {
		String[] data = year_filter.split("-");
		String fromdate="";
		String todate ="";
		if(Integer.parseInt(month_filter)<=3){
			fromdate =  data[1]+"-"+month_filter+"-"+"01";
			todate =  data[1]+"-"+month_filter+"-"+"31";
		}else{
			fromdate =  data[0]+"-"+month_filter+"-"+"01";
			todate =  data[0]+"-"+month_filter+"-"+"31";
		}
		todate = todate+" "+"59:59:59";
		
		String sql1="select id from apm_treatment_episode where dischargedate between '"+fromdate+"' and '"+todate+"' and treatmentstatus=1";
		PreparedStatement preparedStatement1 = connection.prepareStatement(sql1);
		ResultSet rs1 = preparedStatement1.executeQuery();
		while (rs1.next()) {
			String sql ="select id,dis_initiate_time,dis_nursing_time,clientname from apm_treatment_episode where id='"+rs1.getString(1)+"' and dschargestatus='3'";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			while (rs.next()) {
				MisReport misReport = new MisReport();
				String startdate = rs.getString(2);
				String enddate = rs.getString(3);
				Date d1 = format.parse(startdate);
				Date d2 = format.parse(enddate);
				long diff = d2.getTime() - d1.getTime();
				double min = diff/60000.0;
				double a = Math.round(min * 100.0) / 100.0;
				misReport.setAverage(""+a);
				misReport.setClientname(rs.getString(4));
				arrayList.add(misReport);
			}
		}
		
	} catch (Exception e) {
		e.printStackTrace();
	}
	return arrayList;
}

public ArrayList<MisReport> getMortalityNFList(String kpiid, String year_filter, String month_filter, String kpidataid) {
	ArrayList<MisReport> arrayList = new ArrayList<MisReport>();
	try {
		String[] data = year_filter.split("-");
		String fromdate="";
		String todate ="";
		if(Integer.parseInt(month_filter)<=3){
			fromdate =  data[1]+"-"+month_filter+"-"+"01";
			todate =  data[1]+"-"+month_filter+"-"+"31";
		}else{
			fromdate =  data[0]+"-"+month_filter+"-"+"01";
			todate =  data[0]+"-"+month_filter+"-"+"31";
		}
		todate = todate+" "+"59:59:59";
		
		String sql1="select id from apm_treatment_episode where dischargedate between '"+fromdate+"' and '"+todate+"'";
		PreparedStatement preparedStatement1 = connection.prepareStatement(sql1);
		ResultSet rs1 = preparedStatement1.executeQuery();
		while (rs1.next()) {
			String sql ="select id,dschargestatus,clientname from apm_treatment_episode where id='"+rs1.getString(1)+"' and dschargestatus='3'";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				MisReport misReport = new MisReport();
				misReport.setClientname(rs.getString(3));
				arrayList.add(misReport);
			}
		}
	} catch (Exception e) {
		e.printStackTrace();
	}
	return arrayList;
}

public  ArrayList<MisReport> getMrdFileNFList(String kpiid, String year_filter, String month_filter, String kpidataid) {
	ArrayList<MisReport> arrayList = new ArrayList<MisReport>();
	ClientDAO clientDAO = new JDBCClientDAO(connection);
	try {
		String[] data = year_filter.split("-");
		String fromdate="";
		String todate ="";
		if(Integer.parseInt(month_filter)<=3){
			fromdate =  data[1]+"-"+month_filter+"-"+"01";
			todate =  data[1]+"-"+month_filter+"-"+"31";
		}else{
			fromdate =  data[0]+"-"+month_filter+"-"+"01";
			todate =  data[0]+"-"+month_filter+"-"+"31";
		}
		todate = todate+" "+"59:59:59";
		
		String sql1="select id,history,patientid from apm_consultation_note where lastmodified between '"+fromdate+"' and '"+todate+"' and appointmentid!='0'";
		PreparedStatement preparedStatement1 = connection.prepareStatement(sql1);
		ResultSet rs1 = preparedStatement1.executeQuery();
		while (rs1.next()) {
				MisReport misReport = new MisReport();
				if(rs1.getString(2)==null){
					Client client = clientDAO.getSelectedSessionClientDetails(rs1.getString(3));
					misReport.setClientname(client.getClientName());
					arrayList.add(misReport);
				}else if(rs1.getString(2).equals("")){
					Client client = clientDAO.getSelectedSessionClientDetails(rs1.getString(3));
					misReport.setClientname(client.getClientName());
					arrayList.add(misReport);
				}
		}
		
	} catch (Exception e) {
		e.printStackTrace();
	}
	return arrayList;
}

public ArrayList<MisReport> getMrdDisSumRecordNFList(String kpiid, String year_filter, String month_filter,String kpidataid) {
	ArrayList<MisReport> arrayList = new ArrayList<MisReport>();
	try {
		String[] data = year_filter.split("-");
		String fromdate="";
		String todate ="";
		if(Integer.parseInt(month_filter)<=3){
			fromdate =  data[1]+"-"+month_filter+"-"+"01";
			todate =  data[1]+"-"+month_filter+"-"+"31";
		}else{
			fromdate =  data[0]+"-"+month_filter+"-"+"01";
			todate =  data[0]+"-"+month_filter+"-"+"31";
		}
		todate = todate+" "+"59:59:59";
		
		String sql1="select treatmentepisodeid from ipd_addmission_form where admissiondsate between '"+fromdate+"' and '"+todate+"'";
		PreparedStatement preparedStatement1 = connection.prepareStatement(sql1);
		ResultSet rs1 = preparedStatement1.executeQuery();
		while (rs1.next()) {
			String sql ="select id,hospcourse,clientname from apm_treatment_episode where id='"+rs1.getString(1)+"' and treatmentstatus='1'";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				MisReport misReport = new MisReport();
				if(rs.getString(2)==null){
					misReport.setClientname(rs.getString(3));
					arrayList.add(misReport);
				}else if(rs.getString(2).equals("")){
					misReport.setClientname(rs.getString(3));
					arrayList.add(misReport);
				}
			}
		}
		
	} catch (Exception e) {
		e.printStackTrace();
	}
	return arrayList;
}
   
public MisReport getKPIData(String kpiarea_filter, String year_filter, String month_filter) {
	MisReport misReport = new MisReport();
	try {
		StringBuilder builder = new StringBuilder();
		builder.append("select kpi_master.id, kpi_master.areaid, indicatorid, no_of_input, prompt1, prompt2, prompt3, prompt4, prompt5, formula, formula_desc,area,indicator,kpi_indicator.ismannual, kpi_indicator.function_name ");
		builder.append("from kpi_master ");
		builder.append("inner join kpi_data on kpi_data.kpiid = kpi_master.id ");
		builder.append("inner join kpi_area on kpi_area.id = kpi_master.areaid ");
		builder.append("inner join kpi_indicator on kpi_indicator.id = kpi_master.indicatorid ");
		builder.append("where kpi_data.kpi_month='"+month_filter+"' and kpi_data.kpi_year='"+year_filter+"' and kpi_data.kpiid='"+kpiarea_filter+"' ");
		PreparedStatement preparedStatement = connection.prepareStatement(builder.toString());
		ResultSet rs = preparedStatement.executeQuery();
		while (rs.next()) {
			misReport.setKpiid(rs.getString(1));
			misReport.setKpiareaid(rs.getString(2));
			misReport.setKpiindicatorid(rs.getString(3));
			misReport.setNo_of_input(rs.getString(4));
			misReport.setFormula(rs.getString(10));
			misReport.setFormula_desc(rs.getString(11));
			misReport.setKpiarea(rs.getString(12));
			misReport.setKpiindicator(rs.getString(13));
			misReport.setIsmannual(rs.getString(14));
			misReport.setFname(rs.getString(15));
			int no_of_input =0;
			if(rs.getInt(4)==0){
				no_of_input=0;
			}else{
				no_of_input=rs.getInt(4);
			}
			
			//27 oct 2017
			if(rs.getString(14).equals("1")){
				if(rs.getString(15)!=null){
					if(!rs.getString(15).equals("")){
						int kpidataid =  checkKPIAvailable(rs.getInt(1), year_filter, month_filter);
						if(rs.getString(15).equals("mrddissumnotfound")){
							int result = getMrdDisSumRecordNF(rs.getString(1),year_filter,month_filter,""+kpidataid);
						}else if(rs.getString(15).equals("mrdfilemissing")){
							int result = getMrdFileNF(rs.getString(1),year_filter,month_filter,""+kpidataid);
						}else if(rs.getString(15).equals("mortality")){
							int result = getMortalityNF(rs.getString(1),year_filter,month_filter,""+kpidataid);
						}else if(rs.getString(15).equals("timetakenfordischarge")){
							int result = getTimeTakenForDischarge(rs.getString(1),year_filter,month_filter,""+kpidataid);
						}else if(rs.getString(15).equals("waitingtimeopd")){
							int result = getWaitingTimeOPD(rs.getString(1),year_filter,month_filter,""+kpidataid);
						}
						else if(rs.getString(15).equals("waitingtimelab")){
							int result = getWaitingTimeLab(rs.getString(1),year_filter,month_filter,""+kpidataid);
						}
					}
				}
			}
			
			
			MisReport misReport2 = getKPITransactionData(rs.getString(1),year_filter,month_filter);
			ArrayList<String> arrayList2 = new ArrayList<String>();
			ArrayList<MisReport> arrayList3 = new ArrayList<MisReport>();
			
			for(int i=1;i<=no_of_input;i++){
				MisReport misReport3 = new MisReport();
				if(misReport2.getIs_active().equals("0")){
					arrayList2.add("");
					misReport3.setInputData("");
					misReport3.setKpiid(rs.getString(1));
					arrayList3.add(misReport3);
					misReport.setInput1("");
					misReport.setInput2("");
					misReport.setInput3("");
					misReport.setInput4("");
					misReport.setInput5("");
				}else{
					if(i==1){
						arrayList2.add(misReport2.getInput1());
						misReport3.setKpiid(rs.getString(1));
						misReport3.setInputData(misReport2.getInput1());
						arrayList3.add(misReport3);
						misReport.setInput1(misReport2.getInput1());
					}else if(i==2){
						arrayList2.add(misReport2.getInput2());
						misReport3.setKpiid(rs.getString(1));
						misReport3.setInputData(misReport2.getInput2());
						arrayList3.add(misReport3);
						misReport.setInput2(misReport2.getInput2());
					}else if(i==3){
						arrayList2.add(misReport2.getInput3());
						misReport3.setKpiid(rs.getString(1));
						misReport3.setInputData(misReport2.getInput3());
						arrayList3.add(misReport3);
						misReport.setInput3(misReport2.getInput3());
					}else if(i==4){
						arrayList2.add(misReport2.getInput4());
						misReport3.setKpiid(rs.getString(1));
						misReport3.setInputData(misReport2.getInput4());
						arrayList3.add(misReport3);
						misReport.setInput4(misReport2.getInput4());
					}else if(i==5){
						arrayList2.add(misReport2.getInput5());
						misReport3.setKpiid(rs.getString(1));
						misReport3.setInputData(misReport2.getInput5());
						arrayList3.add(misReport3);
						misReport.setInput5(misReport2.getInput5());
					}
				}
			}
			misReport.setNo_of_inputList(arrayList2);
			misReport.setNoofinputlist(arrayList3);
			
			if(misReport2.getResult()==null){
				misReport.setResult("");
			}else{	
				misReport.setResult(misReport2.getResult());
			}
			
			if(misReport2.getTarget()==null){
				misReport.setTarget("");
			}else{	
				misReport.setTarget(misReport2.getTarget());
			}
			
			if(misReport2.getKpi_dataid()==null){
				misReport.setKpi_dataid("0");
			}else if(misReport2.getKpi_dataid().equals("")){
				misReport.setKpi_dataid("0");
			}else{	
				misReport.setKpi_dataid(misReport2.getKpi_dataid());
			}
			misReport.setKpi_year(year_filter);
			misReport.setKpi_month(month_filter);
			
		}
	} catch (Exception e) {
		e.printStackTrace();
	}
	return misReport;
}

private int getWaitingTimeLab(String kpiid, String year_filter, String month_filter, String kpidataid) {
	int result =0;
	try {
		String[] data = year_filter.split("-");
		String fromdate="";
		String todate ="";
		if(Integer.parseInt(month_filter)<=3){
			fromdate =  data[1]+"-"+month_filter+"-"+"01";
			todate =  data[1]+"-"+month_filter+"-"+"31";
		}else{
			fromdate =  data[0]+"-"+month_filter+"-"+"01";
			todate =  data[0]+"-"+month_filter+"-"+"31";
		}
		todate = todate+" "+"59:59:59";
		int totalcount =0;
		int totalavail =0;
		
		StringBuffer buffer = new StringBuffer();
		
		
		double min = 0.0;
		/*buffer.append("select apm_appointment_log.date,apm_invoice_assesments.invoiceid from apm_appointment_log ");
		buffer.append("inner join apm_invoice_assesments on apm_appointment_log.appmt_id = apm_invoice_assesments.appointmentid ");
		//buffer.append("inner join apm_invoice_assesments on apm_appointment_log.appmt_id = apm_invoice_assesments.appointmentid ");
		buffer.append("where apm_appointment_log.status='Being Seen' and apm_invoice_assesments.appointmentid !='0' and date between '"+fromdate+"' and '"+todate+"'");*/
		/*SELECT compdate,dateofcompleted FROM apm_client_parent_investigation where lastmodified between '2017-10-01' and '2018-12-01' and compdate!='null'and dateofcompleted!='null' and jobtitle='Pathlab'*/
		buffer.append("SELECT compdate,dateofcompleted,created_datetime FROM apm_client_parent_investigation ");
		buffer.append("where lastmodified between '"+fromdate+"' and '"+todate+"' and compdate!='null'and dateofcompleted!='null' and jobtitle='Pathlab'");
		
		PreparedStatement preparedStatement = connection.prepareStatement(buffer.toString());
		ResultSet rs = preparedStatement.executeQuery();
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		while (rs.next()) {
			String enddate = rs.getString(2);
			String startdate = rs.getString(1);
			//  16-12-2019
			if(!(DateTimeUtils.isNull(startdate).equals("") && DateTimeUtils.isNull(enddate).equals(""))){
				if(startdate.split(" ").length!=2){
					startdate= startdate+" "+rs.getString(3).split(" ")[1];
				}
			}
			
			Date d1 = format.parse(startdate);
			Date d2 = format.parse(enddate);
			long diff = d2.getTime() - d1.getTime();
			if(enddate!=null && startdate!=null){
				if(!enddate.equals("") && (!startdate.equals(""))){
					min = min+ diff/60000.0;
				}
			}
			if(min<0){
				min=0;
			}
			totalcount++;
		}
				
		
	
		updateKPITempTable(""+min,""+totalcount,"","","");
		MisReport misReport = getKPIMasterData(kpiid);
		String finalresult = getCalKPIResult(misReport);
		if(finalresult==null){
			finalresult = ""+Math.round(0.0 * 100.0) / 100.0;
		}else if(finalresult.equals("")){
			finalresult = ""+Math.round(0.0 * 100.0) / 100.0;
		}else{
			finalresult = ""+Math.round(Double.parseDouble(finalresult) * 100.0) / 100.0;
		}
		if(kpidataid.equals("0")){
			misReport.setUserid(null);
			misReport.setDatetime(null);
			min = Math.round(min * 100.0) / 100.0;
			insertNewKPIRecord(""+min,""+totalcount,"","","",finalresult,misReport.getKpiindicatorid(),misReport,year_filter,month_filter,"0",null);
		}else{
			min = Math.round(min * 100.0) / 100.0;
			updateKPIData(""+min,""+totalcount,"","","",finalresult,kpiid,null,null,kpidataid,"0",null);
		}
	} catch (Exception e) {
		e.printStackTrace();
	}
	return result;
}

private int getWaitingTimeOPD(String kpiid, String year_filter, String month_filter, String kpidataid) {
	int result =0;
	try {
		String[] data = year_filter.split("-");
		String fromdate="";
		String todate ="";
		if(Integer.parseInt(month_filter)<=3){
			fromdate =  data[1]+"-"+month_filter+"-"+"01";
			todate =  data[1]+"-"+month_filter+"-"+"31";
		}else{
			fromdate =  data[0]+"-"+month_filter+"-"+"01";
			todate =  data[0]+"-"+month_filter+"-"+"31";
		}
		todate = todate+" "+"59:59:59";
		int totalcount =0;
		int totalavail =0;
		
		StringBuffer buffer = new StringBuffer();
		
		
		double min = 0.0;
		buffer.append("select apm_appointment_log.date,apm_invoice_assesments.invoiceid from apm_appointment_log ");
		buffer.append("inner join apm_invoice_assesments on apm_appointment_log.appmt_id = apm_invoice_assesments.appointmentid ");
		//buffer.append("inner join apm_invoice_assesments on apm_appointment_log.appmt_id = apm_invoice_assesments.appointmentid ");
		buffer.append("where apm_appointment_log.status='Being Seen' and apm_invoice_assesments.appointmentid !='0' and date between '"+fromdate+"' and '"+todate+"'");
		PreparedStatement preparedStatement = connection.prepareStatement(buffer.toString());
		ResultSet rs = preparedStatement.executeQuery();
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		while (rs.next()) {
			String enddate = rs.getString(1);
			
			StringBuffer buffer2 = new StringBuffer();
			buffer2.append("select invoiceDate from apm_charges_assesment ");
			buffer2.append("inner join apm_charges_invoice_log on apm_charges_assesment.chargeinvoiceid = apm_charges_invoice_log.id ");
			buffer2.append("where apm_charges_assesment.invoiceid='"+rs.getString(2)+"'");
			preparedStatement = connection.prepareStatement(buffer2.toString());
			ResultSet rs1 = preparedStatement.executeQuery();
			String startdate="";
			while (rs1.next()) {
				startdate = rs1.getString(1);
			}
			Date d1 = format.parse(startdate);
			Date d2 = format.parse(enddate);
			long diff = d2.getTime() - d1.getTime();
			if(enddate!=null && startdate!=null){
				if(!enddate.equals("") && (!startdate.equals(""))){
					min = min+ diff/60000.0;
				}
			}
		}
				
		/*String sql1="select id from apm_treatment_episode where dischargedate between '"+fromdate+"' and '"+todate+"' and treatmentstatus=1";
		PreparedStatement preparedStatement1 = connection.prepareStatement(sql1);
		ResultSet rs1 = preparedStatement1.executeQuery();
		double min = 0.0;
		while (rs1.next()) {
			totalcount++;
			String sql ="select id,dis_initiate_time,dis_nursing_time from apm_treatment_episode where id='"+rs1.getString(1)+"' and dschargestatus='3'";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			while (rs.next()) {
					String startdate = rs.getString(2);
					String enddate = rs.getString(3);
					Date d1 = format.parse(startdate);
					Date d2 = format.parse(enddate);
					long diff = d2.getTime() - d1.getTime();
					min = min+ diff/60000.0;
			}
		}*/
		totalcount = getBookedAppointment(fromdate,todate);
		updateKPITempTable(""+min,""+totalcount,"","","");
		MisReport misReport = getKPIMasterData(kpiid);
		String finalresult = getCalKPIResult(misReport);
		if(finalresult==null){
			finalresult = ""+Math.round(0.0 * 100.0) / 100.0;
		}else if(finalresult.equals("")){
			finalresult = ""+Math.round(0.0 * 100.0) / 100.0;
		}else{
			finalresult = ""+Math.round(Double.parseDouble(finalresult) * 100.0) / 100.0;
		}
		if(kpidataid.equals("0")){
			misReport.setUserid(null);
			misReport.setDatetime(null);
			min = Math.round(min * 100.0) / 100.0;
			insertNewKPIRecord(""+min,""+totalcount,"","","",finalresult,misReport.getKpiindicatorid(),misReport,year_filter,month_filter,"0",null);
		}else{
			min = Math.round(min * 100.0) / 100.0;
			updateKPIData(""+min,""+totalcount,"","","",finalresult,kpiid,null,null,kpidataid,"0",null);
		}
	} catch (Exception e) {
		e.printStackTrace();
	}
	return result;
}

public boolean iskpiDataPresent(String kpiid, String kpi_year, String kpi_month) {
	boolean flag = false;
	try {
		StringBuilder builder = new StringBuilder();
		builder.append("select kpi_master.id, kpi_master.areaid, indicatorid, no_of_input, prompt1, prompt2, prompt3, prompt4, prompt5, formula, formula_desc,area,indicator,kpi_indicator.ismannual, kpi_indicator.function_name ");
		builder.append("from kpi_master ");
		builder.append("inner join kpi_data on kpi_data.kpiid = kpi_master.id ");
		builder.append("inner join kpi_area on kpi_area.id = kpi_master.areaid ");
		builder.append("inner join kpi_indicator on kpi_indicator.id = kpi_master.indicatorid ");
		builder.append("where kpi_data.kpi_month='"+kpi_month+"' and kpi_data.kpi_year='"+kpi_year+"' and kpi_data.kpiid='"+kpiid+"' and kpi_indicator.ismannual='1' ");
		
		PreparedStatement preparedStatement = connection.prepareStatement(builder.toString());
		ResultSet rs = preparedStatement.executeQuery();
		while (rs.next()) {
			flag = true;
		}
	} catch (Exception e) {
		e.printStackTrace();
	}
	return flag;
}

public boolean iskpiSyytemGen(String kpiid) {
	boolean flag = false;
	try {
		StringBuilder builder = new StringBuilder();
		builder.append("select kpi_master.id,kpi_indicator.ismannual ");
		builder.append("from kpi_master ");
		//builder.append("inner join kpi_data on kpi_data.kpiid = kpi_master.id ");
		//builder.append("inner join kpi_area on kpi_area.id = kpi_master.areaid ");
		builder.append("inner join kpi_indicator on kpi_indicator.id = kpi_master.indicatorid ");
		builder.append("where kpi_master.id='"+kpiid+"' and kpi_indicator.ismannual='1' ");
		PreparedStatement preparedStatement = connection.prepareStatement(builder.toString());
		ResultSet rs = preparedStatement.executeQuery();
		while (rs.next()) {
			flag = true;
		}
	} catch (Exception e) {
		e.printStackTrace();
	}
	return flag;
}


public ArrayList<MisReport> getNABHSubCatList(String catid) {
	//  07 oct 2017 for KPI dashboard select area list
	ArrayList<MisReport>  arrayList = new ArrayList<MisReport>();
	try {
		//String sql ="select id,area from kpi_area";
		StringBuilder builder = new StringBuilder();
		builder.append("select nabh_subcatagory.id,nabh_subcatagory.name from nabh_subcatagory ");
		builder.append("inner join nabh_Catagory on catagoryid = nabh_Catagory.id ");
		if(catid!=null){
			builder.append("where nabh_Catagory.id='"+catid+"'");
		}
		PreparedStatement preparedStatement = connection.prepareStatement(builder.toString());
		ResultSet rs = preparedStatement.executeQuery();
		while (rs.next()) {
			MisReport misReport = new MisReport();
			misReport.setId(rs.getInt(1));
			misReport.setName(rs.getString(2));
			arrayList.add(misReport);
		}
	} catch (Exception e) {
		e.printStackTrace();
	}
	return arrayList;
}


public ArrayList<MisReport> getAllSelfAssessmentToolList(String kpicat, String satyear, String satmonth) {
	ArrayList<MisReport> arrayList = new ArrayList<MisReport>();
	try {
		
		StringBuilder builder = new StringBuilder();
		builder.append("select nabh_subcatagory.id,nabh_subcatagory.name from nabh_subcatagory ");
		builder.append("inner join nabh_Catagory on nabh_Catagory.id = nabh_subcatagory.catagoryid ");
		builder.append("where nabh_Catagory.id='2' ");
		if(!kpicat.equals("0")){
			builder.append("and nabh_subcatagory.id='"+kpicat+"' ");
		}
		
		PreparedStatement preparedStatement = connection.prepareStatement(builder.toString());
		ResultSet rs = preparedStatement.executeQuery();
		while (rs.next()) {
			MisReport misReport = new MisReport();
			misReport.setSubcatid(rs.getString(1));
			misReport.setSubcatname(rs.getString(2));
			boolean flag = checkMasterEntry(rs.getString(1));
			if(flag){
				ArrayList<MisReport> areaarraylist = getSATAreaList(satyear,satmonth,rs.getString(1));
				misReport.setAreaarraylist(areaarraylist);
				misReport.setKpi_year(satyear);
				misReport.setKpi_month(satmonth);
				arrayList.add(misReport);
			}
		}
	} catch (Exception e) {
		e.printStackTrace();
	}
	return arrayList;
}

private boolean checkMasterEntry(String string) {
	boolean flag = false;
	try {
		StringBuilder builder = new StringBuilder();
		builder.append("select nabh_subcatagory.id from nabh_subcatagory ");
		builder.append("inner join kpi_area on kpi_area.subcatagoryid = nabh_subcatagory.id ");
		builder.append("inner join kpi_indicator on kpi_area.id = kpi_indicator.areaid ");
		builder.append("inner join kpi_master on kpi_indicator.id = kpi_master.indicatorid ");
		builder.append("where nabh_subcatagory.id='"+string+"'");
		PreparedStatement preparedStatement = connection.prepareStatement(builder.toString());
		ResultSet rs = preparedStatement.executeQuery();
		while (rs.next()) {
			flag = true;
		}
	} catch (Exception e) {
		e.printStackTrace();
	}
	return flag;
}

private ArrayList<MisReport> getSATAreaList(String satyear, String satmonth, String subcatid) {
	ArrayList<MisReport> arrayList = new ArrayList<MisReport>();
	try {
		StringBuilder builder = new StringBuilder();
		builder.append("SELECT kpi_area.id,kpi_area.area,kpi_area.subcatagoryid FROM kpi_area ");
		//builder.append("inner join nabh_subcatagory on nabh_subcatagory.id = kpi_area.subcatagoryid ");
		builder.append("where kpi_area.subcatagoryid='"+subcatid+"' ");
		PreparedStatement preparedStatement = connection.prepareStatement(builder.toString());
		ResultSet rs = preparedStatement.executeQuery();
		while (rs.next()){
			MisReport misReport = new MisReport();
			misReport.setKpiareaid(rs.getString(1));
			misReport.setKpiarea(rs.getString(2));
			ArrayList<MisReport> satDataList = getSATData(satyear, satmonth, rs.getString(1));
			misReport.setSatDataList(satDataList);
			misReport.setKpi_year(satyear);
			misReport.setKpi_month(satmonth);
			arrayList.add(misReport);
		}
	} catch (Exception e) {
		e.printStackTrace();
	}
	return arrayList;
}

private ArrayList<MisReport> getSATData(String satyear, String satmonth, String areaid) {
	ArrayList<MisReport> arrayList = new ArrayList<MisReport>();
	try {
		StringBuilder builder = new StringBuilder();
		builder.append("SELECT kpi_master.id,kpi_indicator.id, kpi_indicator.areaid, indicator, formula_decription, main_formula, totalinput, ismannual, function_name FROM kpi_indicator ");
		builder.append("inner join kpi_master on kpi_master.indicatorid = kpi_indicator.id ");
		builder.append("where kpi_indicator.areaid='"+areaid+"' ");
		PreparedStatement preparedStatement = connection.prepareStatement(builder.toString());
		ResultSet rs = preparedStatement.executeQuery();
		while (rs.next()) {
			MisReport misReport = new MisReport();
			misReport.setKpiid(rs.getString(1));
			misReport.setKpiindicatorid(rs.getString(2));
			misReport.setKpiindicator(rs.getString(4));
			MisReport misReport2 = getKPITransactionData(rs.getString(1),satyear,satmonth);
			if(misReport2.getInput1()==null){
				misReport.setInput1("2");
			}else{	
				misReport.setInput1(misReport2.getInput1());
			}
			if(misReport2.getInput2()==null){
				misReport.setInput2("2");
			}else{	
				misReport.setInput2(misReport2.getInput2());
			}
			
			if(misReport2.getResult()==null){
				misReport.setResult("");
			}else{	
				misReport.setResult(misReport2.getResult());
			}
			
			if(misReport2.getEvidence()==null){
				misReport.setEvidence("");
			}else{	
				misReport.setEvidence(misReport2.getEvidence());
			}
			
			if(misReport2.getEvidence()==null){
				misReport.setEvidence("");
			}else{	
				misReport.setEvidence(misReport2.getEvidence());
			}
			
			if(misReport2.getTarget()==null){
				misReport.setTarget("");
			}else{	
				misReport.setTarget(misReport2.getTarget());
			}
			
			if(misReport2.getKpi_dataid()==null){
				misReport.setKpi_dataid("0");
			}else if(misReport2.getKpi_dataid().equals("")){
				misReport.setKpi_dataid("0");
			}else{	
				misReport.setKpi_dataid(misReport2.getKpi_dataid());
			}
			misReport.setKpi_year(satyear);
			misReport.setKpi_month(satmonth);
			arrayList.add(misReport);
		}
	} catch (Exception e) {
		e.printStackTrace();
	}
	return arrayList;
	
}

public int getOtPatientsCounts(String fromdate, String todate) {
	int result = 0;
	try {
		/*String sql = "select id from apm_ot_parent where commencing between '"
				+ fromdate + "' and '" + todate + "' group by apmtid";*/
		
		String sql ="SELECT count(*) FROM apm_available_slot where procedures!='0' and commencing between '"+fromdate+"' and '"+todate+"'";
		PreparedStatement ps = connection.prepareStatement(sql);

		ResultSet rs = ps.executeQuery();
		while (rs.next()) {
			result = rs.getInt(1);
		}
	} catch (Exception e) {
		e.printStackTrace();
	}
	return result;
}

public MisReport getOPDAppointment(String fromdate, String newdate, String apptypeid) {
	MisReport misReport = new MisReport();
	try {
		/*String sql = "SELECT id FROM apm_appointment_type where chargeType='Appointment Charge'";*/
		String sql ="SELECT count(*),apmttypetext FROM apm_available_slot where aptmtype ='"+apptypeid+"' and commencing  between '"+fromdate+"' and '"+newdate+"' and status=0 and procedures='0'";
		PreparedStatement preparedStatement = connection.prepareStatement(sql);
		ResultSet rs = preparedStatement.executeQuery();
		misReport.setResult("0");
		while (rs.next()) {
			
			misReport.setName(rs.getString(2));
			if(rs.getString(1)!=null){
				if(!rs.getString(1).equals("")){
					misReport.setResult(rs.getString(1));
				}else{
					misReport.setResult("0");
				}
			}else{
				misReport.setResult("0");
			}
			//  01 feb 2018 comment becuase it take more time
			//ArrayList<MisReport> arrayList = getOPDAppointmentList(fromdate, newdate,apptypeid);
			//misReport.setOpdappointmenttypelist(arrayList);
		}
	} catch (Exception e) {
		e.printStackTrace();
	}
	
	return misReport;
}

public ArrayList<MisReport> getAppointmentTypeID() {
	ArrayList<MisReport> arrayList = new ArrayList<MisReport>();
	try {
		String sql = "SELECT id,name FROM apm_appointment_type where chargeType='Appointment Charge'";
		PreparedStatement preparedStatement = connection.prepareStatement(sql);
		ResultSet rs = preparedStatement.executeQuery();
		while (rs.next()) {
			MisReport misReport = new MisReport();
			misReport.setId(rs.getInt(1));
			misReport.setName(rs.getString(2));
			arrayList.add(misReport);
		}
	} catch (Exception e) {
		e.printStackTrace();
	}
	
	return arrayList;
}

public int getTotalDeaths(String fromdate, String newdate) {
	int result =0;
	try {
		String sql ="SELECT count(*) FROM apm_treatment_episode where dschargestatus='3' and dischargedate between '"+fromdate+"' and '"+newdate+"'";
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

public int getTotalDama(String fromdate, String newdate) {
	int result =0;
	try {
		String sql =" SELECT count(*) FROM apm_treatment_episode where dschargestatus='7' and dischargedate between '"+fromdate+"' and '"+newdate+"';";
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

public int getTotalMLCAddmission(String fromdate, String newdate) {
	int result =0;
	fromdate=fromdate+" 00:59:59";
	newdate=newdate+" 59:59:59";
	try {
		String sql ="SELECT count(*) FROM ipd_addmission_form where admissiondsate between '"+fromdate+"' and '"+newdate+"' and cancel ='0'  and ismlc='1'";
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

public int getInvestigationJobTitle(String fromdate, String newdate, String string) {
	int result =0;
	try {
		//String sql ="SELECT count(*) FROM ipd_addmission_form where admissiondsate between '"+fromdate+"' and '"+newdate+"' and cancel ='0'  and isml='1'";
		StringBuilder builder = new StringBuilder();
		newdate = newdate+" "+"59:59:59";
		/*builder.append("SELECT apm_client_parent_investigation.id FROM apm_client_parent_investigation ");
		builder.append("inner join apm_client_investigation on apm_client_parent_investigation.id= apm_client_investigation.parentid ");
		builder.append("inner join apm_user on apm_client_investigation.practitionerid=apm_user.id ");
		builder.append("WHERE deleted='0' and apm_client_parent_investigation.jobtitle='"+string+"' and apm_client_parent_investigation.lastmodified between '"+fromdate+"' and '"+newdate+"' group by apm_client_investigation.parentid ");*/
		builder.append("select id from apm_client_parent_investigation where lastmodified between '"+fromdate+"' and '"+newdate+"' and jobtitle='"+string+"' and deleted='0'");
		PreparedStatement preparedStatement = connection.prepareStatement(builder.toString());
		ResultSet rs = preparedStatement.executeQuery();
		while (rs.next()) {
			result++;
		}
	} catch (Exception e) {
		e.printStackTrace();
	}
	return result;
}

public String getInvestSecIdFromName(String string) {
	String id ="";
	try {
		String sql ="select id from apm_investigation_section where name='"+string+"'";
		PreparedStatement preparedStatement = connection.prepareStatement(sql);
		ResultSet rs = preparedStatement.executeQuery();
		while (rs.next()) {
			id = rs.getString(1);
		}
	} catch (Exception e) {
		e.printStackTrace();
	}
	return id;
}

public int getInvestCountBySec(String ctid, String fromdate, String newdate) {
	int total =0;
	try {
		StringBuffer buffer = new StringBuffer();
		newdate = newdate +" "+ "59:59:59";
		buffer.append("select count(*) from apm_client_parent_investigation ");
		buffer.append("inner join apm_investigation_type on apm_client_parent_investigation.invsttypeid = apm_investigation_type.id ");
		buffer.append("inner join apm_investigation_section on apm_investigation_type.sectionid = apm_investigation_section.id ");
		buffer.append("where apm_investigation_section.id = '"+ctid+"' and lastmodified between '"+fromdate+"' and '"+newdate+"' and apm_client_parent_investigation.deleted=0  ");
		PreparedStatement preparedStatement = connection.prepareStatement(buffer.toString());
		ResultSet rs = preparedStatement.executeQuery();
		while (rs.next()) {
			total = rs.getInt(1);
		}
	} catch (Exception e) {
		e.printStackTrace();
	}
	return total;
}

public ArrayList<MisReport> getOPDAppointmentList(String fromdate, String newdate, String apptypeid) {
	ArrayList<MisReport> arrayList = new ArrayList<MisReport>();
	try {
		String sql ="SELECT id,apmttypetext,diaryuserid,clientname FROM apm_available_slot where aptmtype ='"+apptypeid+"' and commencing  between '"+fromdate+"' and '"+newdate+"' and status=0 and procedures='0'";
		PreparedStatement preparedStatement = connection.prepareStatement(sql);
		ResultSet rs = preparedStatement.executeQuery();
		int res=0;
		while (rs.next()) {
			MisReport misReport = new MisReport();
			misReport.setResult(String.valueOf(res++));
			misReport.setName(rs.getString(2));
			misReport.setPractname(rs.getString(3));
			misReport.setClientname(rs.getString(4));
			arrayList.add(misReport);
		}
	} catch (Exception e) {
		e.printStackTrace();
	}
	
	return arrayList;
}

public ArrayList<MisReport> getipdnewadmissionlist(String fromdate, String todate) {
	PreparedStatement preparedStatement = null;
	ArrayList<MisReport> arrayList = new ArrayList<MisReport>();
	todate = todate + " 23:59:59";
	StringBuffer buffer = new StringBuffer();
	buffer.append("SELECT apm_patient.fullname,concat(apm_user.initial,' ',apm_user.firstname,' ',apm_user.lastname) FROM ipd_addmission_form ");
	buffer.append("inner join apm_patient on ipd_addmission_form.clientid = apm_patient.id ");
	buffer.append("inner join apm_user on ipd_addmission_form.practitionerid = apm_user.id ");
	buffer.append("where admissiondsate  between '" + fromdate + "'  and '" + todate + "' ");
	try {
		preparedStatement = connection.prepareStatement(buffer.toString());
		ResultSet rs = preparedStatement.executeQuery();
		if (rs.next()) {
			MisReport misReport = new MisReport();
			misReport.setClientname(rs.getString(1));
			misReport.setPractname(rs.getString(2));
			arrayList.add(misReport);
		}

	} catch (Exception e) {
		e.printStackTrace();
	}
	return arrayList;
}

public ArrayList<MisReport> getInHousePatientsList(String fromdate, String todate) {
	PreparedStatement preparedStatement = null;
	int result = 0;
	todate = todate + " 23:59:59";
	ArrayList<MisReport> arrayList = new ArrayList<MisReport>();
	//String sql = "SELECT count(*) FROM ipd_addmission_form where bedid!=0 ";
	
	StringBuffer buffer = new StringBuffer();
	buffer.append("SELECT apm_patient.fullname,concat(apm_user.initial,' ',apm_user.firstname,' ',apm_user.lastname) FROM ipd_addmission_form ");
	buffer.append("inner join apm_patient on ipd_addmission_form.clientid = apm_patient.id ");
	buffer.append("inner join apm_user on ipd_addmission_form.practitionerid = apm_user.id ");
	buffer.append("where bedid!=0 ");
	
	try {
		preparedStatement = connection.prepareStatement(buffer.toString());
		ResultSet rs = preparedStatement.executeQuery();
		if (rs.next()) {
			MisReport misReport = new MisReport();
			misReport.setClientname(rs.getString(1));
			misReport.setPractname(rs.getString(2));
			arrayList.add(misReport);
		}

	} catch (Exception e) {
		e.printStackTrace();
	}
	return arrayList;
}
//@ Adarsh for preview
public MisReport getOPDAppointmentforpreview(String fromDate, String toDate, String apptypeid,String diaryuser) {
	 MisReport misReport = new MisReport();
	 try {
		 /*String sql ="SELECT count(*),apmttypetext FROM apm_available_slot where aptmtype ='"+apptypeid+"' and commencing  between '"+fromDate+"' and '"+toDate+"' and status=0 and procedures='0' and diaryuserid='"+diaryuser+"' group by diaryuserid ";*/
		 StringBuffer buffer = new StringBuffer();
		 buffer.append("SELECT count(*),apmttypetext FROM apm_available_slot where aptmtype ='"+apptypeid+"' and commencing  between '"+fromDate+"' and '"+toDate+"' and status=0 and procedures='0'  ");
		 if(diaryuser!=null){
			 buffer.append("and diaryuserid='"+diaryuser+"' ");
		 }
		 buffer.append("group by diaryuserid ");
		 PreparedStatement preparedStatement = connection.prepareStatement(buffer.toString());
		 ResultSet rs = preparedStatement.executeQuery();
		 while (rs.next()) {
			 misReport.setResult(rs.getString(1));
			 misReport.setName(rs.getString(2));
		 }
	 } catch (Exception e) {
	  e.printStackTrace();
	 }
	 return misReport;
}

public int getOPDCompletedAppointment(String fromdate, String todate) {
	PreparedStatement preparedStatement = null;
	int result = 0;
	StringBuffer buffer = new StringBuffer();
	buffer.append("select count(apm_available_slot.id) from apm_available_slot ");
	buffer.append("inner join apm_invoice_assesments on apm_available_slot.id = apm_invoice_assesments.appointmentid ");
	buffer.append("where apm_available_slot.commencing between '"+fromdate+"' and '"+todate+"' and procedures='0'  and dna='0'  ");
	buffer.append("group by apm_invoice_assesments.appointmentid ");
	try {
		preparedStatement = connection.prepareStatement(buffer.toString());
		ResultSet rs = preparedStatement.executeQuery();
		while (rs.next()) {
			result = result+1;
			
		}

	} catch (Exception e) {
		e.printStackTrace();
	}
	return result;
}

public int getOPDdnaAppointment(String fromdate, String todate) {
	PreparedStatement preparedStatement = null;
	int result = 0;
	StringBuffer buffer = new StringBuffer();
	buffer.append("select count(apm_available_slot.id) from apm_available_slot ");
	buffer.append("inner join apm_invoice_assesments on apm_available_slot.id = apm_invoice_assesments.appointmentid ");
	buffer.append("where apm_available_slot.commencing between '"+fromdate+"' and '"+todate+"' and procedures='0'  and dna!='0' ");
	buffer.append("group by apm_invoice_assesments.appointmentid ");
	try {
		preparedStatement = connection.prepareStatement(buffer.toString());
		ResultSet rs = preparedStatement.executeQuery();
		while (rs.next()) {
			result = result+1;
		}

	} catch (Exception e) {
		e.printStackTrace();
	}
	return result;
}

public ArrayList<String> getInHousePatientList(String todate, String todate2) {
	PreparedStatement preparedStatement = null;
	ArrayList<String> arrayList = new ArrayList<String>();
	todate = todate + " 23:59:59";

	String sql = "SELECT id FROM ipd_addmission_form where bedid!=0 ";
	try {
		preparedStatement = connection.prepareStatement(sql);
		ResultSet rs = preparedStatement.executeQuery();
		while(rs.next()) {
			arrayList.add(rs.getString(1));
		}

	} catch (Exception e) {
		e.printStackTrace();
	}
	return arrayList;
}

public ArrayList<String> getIpdNewAdmissionList(String fromdate, String todate) {
	PreparedStatement preparedStatement = null;
	todate = todate + " 23:59:59";
	ArrayList<String> arrayList = new ArrayList<String>();
	String sql = "SELECT id FROM ipd_addmission_form where admissiondsate  between '"
			+ fromdate + "'  and '" + todate + "' and  casualty='0' and cancel='0' ";

	try {
		preparedStatement = connection.prepareStatement(sql);
		ResultSet rs = preparedStatement.executeQuery();
		while (rs.next()) {
			arrayList.add(rs.getString(1));
		}

	} catch (Exception e) {
		e.printStackTrace();
	}
	return arrayList;
}

public String getDischargePatientList(String fromdate, String todate) {
	PreparedStatement preparedStatement = null;
	String res ="";
	todate = todate + " 23:59:59";

	StringBuffer sql = new StringBuffer();
	sql.append("SELECT ipd_addmission_form.id FROM ipd_addmission_form inner join apm_treatment_episode ");
	sql.append("on apm_treatment_episode.id = ipd_addmission_form.treatmentepisodeid ");
	sql.append("where discharge_end_date between '" + fromdate + "'  and '" + todate + "' ");
	sql.append(" and treatmentstatus=1 ");
	try {
		preparedStatement = connection.prepareStatement(sql.toString());
		ResultSet rs = preparedStatement.executeQuery();
		int i=0;
		while(rs.next()) {
			if(i==0){
				res = rs.getString(1);
			}else{
				res = res +","+rs.getString(1);
			}
			i++;
		}

	} catch (Exception e) {
		e.printStackTrace();
	}
	return res;
}

public ArrayList<String> getDischargePatientList1(String fromdate, String todate) {
	PreparedStatement preparedStatement = null;
	ArrayList<String> arrayList = new ArrayList<String>();
	todate = todate + " 23:59:59";

	StringBuffer sql = new StringBuffer();
	sql.append("SELECT ipd_addmission_form.id FROM ipd_addmission_form inner join apm_treatment_episode ");
	sql.append("on apm_treatment_episode.id = ipd_addmission_form.treatmentepisodeid ");
	sql.append("where discharge_end_date between '" + fromdate + "'  and '" + todate + "' ");
	sql.append(" and treatmentstatus=1 ");
	try {
		preparedStatement = connection.prepareStatement(sql.toString());
		ResultSet rs = preparedStatement.executeQuery();
		int i=0;
		while(rs.next()) {
			arrayList.add(rs.getString(1));
		}

	} catch (Exception e) {
		e.printStackTrace();
	}
	return arrayList;
}

public int getInHousePatientsWardWise(String todate, String todate2, String ipdids, String wardid) {
	PreparedStatement preparedStatement = null;
	int result = 0;

	todate = todate + " 23:59:59";
	String sql = "SELECT count(*) FROM ipd_addmission_form where id in ("+ipdids+") and wardid='"+wardid+"' ";
	try {
		preparedStatement = connection.prepareStatement(sql);
		ResultSet rs = preparedStatement.executeQuery();
		if (rs.next()) {
			result = rs.getInt(1);
		}

	} catch (Exception e) {
		e.printStackTrace();
	}
	return result;
}



public int getAvailableBedWardWise(String date, String todate, String ipdids, String wardid) {
	PreparedStatement preparedStatement = null;
	int result = 0;

	todate = todate + " 23:59:59";

	String sql = "SELECT count(*) FROM apm_ipd_bed where active = 1 and casualty=0 and wardid='"+wardid+"'";

	try {
		preparedStatement = connection.prepareStatement(sql);
		ResultSet rs = preparedStatement.executeQuery();
		if (rs.next()) {
			result = rs.getInt(1);
		}

	} catch (Exception e) {
		e.printStackTrace();
	}
	return result;
}

public String getInHousePatientsWardWiseIds(String todate, String todate2, String ipdids, String wardid) {
	PreparedStatement preparedStatement = null;
	String result = "";

	todate = todate + " 23:59:59";
	String sql = "SELECT id FROM ipd_addmission_form where id in ("+ipdids+") and wardid='"+wardid+"' ";
	try {
		preparedStatement = connection.prepareStatement(sql);
		ResultSet rs = preparedStatement.executeQuery();
		while (rs.next()) {
			if(result.equals("")){
				result = rs.getString(1);
			}else{
				result = result +","+rs.getString(1);
			}
			
		}

	} catch (Exception e) {
		e.printStackTrace();
	}
	return result;
}

public int getInHousePatientsPractWise(String todate, String todate2, String ipdids, String practid) {
	PreparedStatement preparedStatement = null;
	int result = 0;
	todate = todate + " 23:59:59";
	String sql = "SELECT count(*) FROM ipd_addmission_form where id in ("+ipdids+") and practitionerid='"+practid+"' ";
	try {
		preparedStatement = connection.prepareStatement(sql);
		ResultSet rs = preparedStatement.executeQuery();
		if (rs.next()) {
			result = rs.getInt(1);
		}

	} catch (Exception e) {
		e.printStackTrace();
	}
	return result;
}

public String getInHousePatientsPractWiseIds(String todate, String todate2, String ipdids, String practid) {
	PreparedStatement preparedStatement = null;
	String result = "";

	todate = todate + " 23:59:59";
	String sql = "SELECT id FROM ipd_addmission_form where id in ("+ipdids+") and practitionerid='"+practid+"' ";
	try {
		preparedStatement = connection.prepareStatement(sql);
		ResultSet rs = preparedStatement.executeQuery();
		while (rs.next()) {
			if(result.equals("")){
				result = rs.getString(1);
			}else{
				result = result +","+rs.getString(1);
			}
			
		}

	} catch (Exception e) {
		e.printStackTrace();
	}
	return result;
}

public boolean checkMISAccess(String androidpractuserid) {
	boolean flag =false;
	try {
		StringBuffer buffer = new StringBuffer();
		buffer.append("select managemis from apm_user ");
		buffer.append("inner join apm_role_access on apm_role_access.usertype = apm_user.jobtitle ");
		buffer.append("where apm_user.userid='"+androidpractuserid+"' ");
		PreparedStatement preparedStatement = connection.prepareStatement(buffer.toString());
		ResultSet rs = preparedStatement.executeQuery();
		while (rs.next()) {
			flag = rs.getBoolean(1);
		}
	} catch (Exception e) {
		e.printStackTrace();
	}
	return flag;
}

public int getCasualityCount(String fromdate, String todate) {
	int result =0;
	fromdate=fromdate+" 00:59:59";
	todate=todate+" 59:59:59";
	try {
		String sql ="SELECT count(*) FROM ipd_addmission_form where admissiondsate between '"+fromdate+"' and '"+todate+"' and cancel ='0'  and casualty='1'";
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

public int getDayCareCount(String fromdate, String todate) {
	int result =0;
	fromdate=fromdate+" 00:59:59";
	todate=todate+" 59:59:59";
	try {
		String sql ="SELECT count(*) FROM ipd_addmission_form where admissiondsate between '"+fromdate+"' and '"+todate+"' and cancel ='0'  and casualty='2'";
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

public MisReport getOPDAppointmentCountforpreview(String fromDate, String toDate, String apptypeid, String diaryuser) {
	 MisReport misReport = new MisReport();
	 boolean flag = false;
	 try {
		 /*String sql ="SELECT count(*),apmttypetext FROM apm_available_slot where aptmtype ='"+apptypeid+"' and commencing  between '"+fromDate+"' and '"+toDate+"' and status=0 and procedures='0' and diaryuserid='"+diaryuser+"' group by diaryuserid ";*/
		 StringBuffer buffer = new StringBuffer();
		 buffer.append("SELECT count(*),apmttypetext FROM apm_available_slot inner join apm_discipline on apm_discipline.id=apm_available_slot.dept where aptmtype ='"+apptypeid+"' and commencing  between '"+fromDate+"' and '"+toDate+"' and status=0 and procedures='0' and showdeptlist='0' ");
		 if(diaryuser!=null){
			 if(!diaryuser.equals("0")){
			 buffer.append("and diaryuserid='"+diaryuser+"' ");
			 buffer.append("group by diaryuserid ");
			 flag = true;
			 }
		 }
		 if(!flag){
		 buffer.append("group by apmttypetext ");
		 }
		 PreparedStatement preparedStatement = connection.prepareStatement(buffer.toString());
		 ResultSet rs = preparedStatement.executeQuery();
		 while (rs.next()) {
			 misReport.setResult(rs.getString(1));
			 misReport.setName(rs.getString(2));
		 }
	 } catch (Exception e) {
	  e.printStackTrace();
	 }
	 return misReport;
}

public ArrayList<Client> getOpdPatientCancelListReport(String fromdate, String todate) {
	ArrayList<Client> list = new ArrayList<Client>();
	ClientDAO clientDAO= new JDBCClientDAO(connection);
	try {
		String sql = "select id,appmt_id,commencing,status,practitoner,clientid,apmttype,charges from apm_appointment_log where commencing between '"
				+ fromdate + "' and '" + todate + "' and status='Cancelled'";
		PreparedStatement ps = connection.prepareStatement(sql);
		ResultSet rs = ps.executeQuery();
		while (rs.next()) {
			Client client = new Client();
			//client= clientDAO.getClientDetails(rs.getString(6));
			client.setAppointmentid(rs.getString(2));
			
			
			client.setApmtStatus("Cancelled");
			client.setDob(rs.getString(3));
			client.setDiaryUser(rs.getString(5));
			client.setClientName(clientDAO.getClientFullName(rs.getString(6)));
			client.setId(rs.getInt(6));
			client.setApmttypetext(rs.getString(7));
			client.setWhopay(clientDAO.getWhoPayName(rs.getString(6)));
			client.setApmtcharges("0");
			client.setInvstid("0");
			client.setCharges(rs.getString(8));
			Client client1=clientDAO.getClientDetails(rs.getString(6));
			client.setAbrivationid(client1.getAbrivationid());
			client.setMobNo(client1.getMobNo());
			list.add(client);
		}

	} catch (Exception e) {
		e.printStackTrace();
	}
	return list;
}

public int getOPDcancelcountAppointment(String fromdate, String todate) {
	int  res= 0;
	try {
		
		String sql = "select count(*) from apm_appointment_log where commencing between '"
				+ fromdate + "' and '" + todate + "' and status='Cancelled'";
		PreparedStatement ps = connection.prepareStatement(sql);
		ResultSet rs = ps.executeQuery();
		while (rs.next()) {
			res=rs.getInt(1);
		}
	}catch (Exception e) {
		// TODO: handle exception
	}
	return res;
}

public MisReport getTotolRevenueForGraph(String fromdate, String todate) {

	MisReport misReport= new MisReport();
	PreparedStatement ps= null;
	try {
		StringBuffer buffer= new StringBuffer();
		buffer.append(" select date,total_ipds,total_opds,bed_ocupancy,avg_length_stay,ipd_revenue,opd_revenue,investigation_revenue,other_revenue,gr_ipd_payment,gr_opd_payment,gr_invst_payment,gr_other_payment,opdtoipdconv,opdtoipdconvratio,total_revenue,conv_revenue ,  ");
		buffer.append("  totalcredit, credit_return,card_sale,cash_sale ,cheque,neft,cash_return,discount,total_pharm_payment  ");
		buffer.append(" from  total_revenue_report where date between '"+fromdate+"' and '"+todate+"' order by date asc  ");
		String sql=buffer.toString();
		ps= connection.prepareStatement(sql);
		ResultSet rs= ps.executeQuery();
		String allmonth ="0";
		while(rs.next()){
			String month = rs.getString(1).split("-")[1];
			allmonth= allmonth+","+month;
			if(Integer.parseInt(month)==1){
				misReport.setMonth1(month);
				misReport.setMonth1_target(rs.getString(7));
			}else if(Integer.parseInt(month)==2){
				misReport.setMonth2(month);
				misReport.setMonth2_target(rs.getString(7));
			}else if(Integer.parseInt(month)==3){
				misReport.setMonth3(month);
				misReport.setMonth3_target(rs.getString(7));
			}else if(Integer.parseInt(month)==4){
				misReport.setMonth4(month);
				misReport.setMonth4_target(rs.getString(7));
			}else if(Integer.parseInt(month)==5){
				misReport.setMonth5(month);
				misReport.setMonth5_target(rs.getString(7));
			}else if(Integer.parseInt(month)==6){
				misReport.setMonth6(month);
				misReport.setMonth6_target(rs.getString(7));
			}else if(Integer.parseInt(month)==7){
				misReport.setMonth7(month);
				misReport.setMonth7_target(rs.getString(7));
			}else if(Integer.parseInt(month)==8){
				misReport.setMonth8(month);
				misReport.setMonth8_target(rs.getString(7));
			}else if(Integer.parseInt(month)==9){
				misReport.setMonth9(month);
				misReport.setMonth9_target(rs.getString(7));
			}else if(Integer.parseInt(month)==10){
				misReport.setMonth10(month);
				misReport.setMonth10_target(rs.getString(7));
			}else if(Integer.parseInt(month)==11){
				misReport.setMonth11(month);
				misReport.setMonth11_target(rs.getString(7));
			}else if(Integer.parseInt(month)==12){
				misReport.setMonth12(month);
				misReport.setMonth12_target(rs.getString(7));
			}
		}
		
		/*for (int i = 1; i <= 12; i++) {
			String month=""+i;
			boolean flag =false;
			for (String xyz : allmonth.split(",")) {
				if(Integer.parseInt(xyz)==0){
					continue;
				}else if(Integer.parseInt(xyz)==i){
					flag =true;
					continue;
				}
			}
			if(flag){
				continue;
			}else{
				if(Integer.parseInt(month)==1){
					misReport.setMonth1(month);
					misReport.setMonth1_target("0");
				}else if(Integer.parseInt(month)==2){
					misReport.setMonth2(month);
					misReport.setMonth2_target("0");
				}else if(Integer.parseInt(month)==3){
					misReport.setMonth3(month);
					misReport.setMonth3_target("0");
				}else if(Integer.parseInt(month)==4){
					misReport.setMonth4(month);
					misReport.setMonth4_target("0");
				}else if(Integer.parseInt(month)==5){
					misReport.setMonth5(month);
					misReport.setMonth5_target("0");
				}else if(Integer.parseInt(month)==6){
					misReport.setMonth6(month);
					misReport.setMonth6_target("0");
				}else if(Integer.parseInt(month)==7){
					misReport.setMonth7(month);
					misReport.setMonth7_target("0");
				}else if(Integer.parseInt(month)==8){
					misReport.setMonth8(month);
					misReport.setMonth8_target("0");
				}else if(Integer.parseInt(month)==9){
					misReport.setMonth9(month);
					misReport.setMonth9_target("0");
				}else if(Integer.parseInt(month)==10){
					misReport.setMonth10(month);
					misReport.setMonth10_target("0");
				}else if(Integer.parseInt(month)==11){
					misReport.setMonth11(month);
					misReport.setMonth11_target("0");
				}else if(Integer.parseInt(month)==12){
					misReport.setMonth12(month);
					misReport.setMonth12_target("0");
				}
			}
		}*/
	} catch (Exception e) {
		e.printStackTrace();
	}
	return misReport;

}

public ArrayList<Client> patientList(String fromdate, String todate, String type) {
	ArrayList<Client> lis= new ArrayList<Client>();
	StringBuffer buffer= new StringBuffer();
	if(type.equals("OPD")){
		buffer.append("  select  count(*),town ,county from apm_available_slot  inner join apm_patient on apm_available_slot.clientid=apm_patient.id ");
		buffer.append(" where commencing between '"+fromdate+"' and '"+todate+"' and  town is not null and town!=''  and apm_available_slot.status = 0 and otid=0 and dna=0 ");
		buffer.append(" group by town order by count(*) DESC ");
	}else{
		buffer.append(" select count(*),town ,county FROM ipd_addmission_form  inner join apm_patient on ipd_addmission_form.clientid=apm_patient.id ");
		buffer.append(" where admissiondsate   between '2019-10-01' and '2019-10-10' and  casualty='0' and cancel='0'  group by town order by count(*) DESC ");
	}
	try {
		
		
		PreparedStatement preparedStatement = connection.prepareStatement(buffer.toString());
		ResultSet rs = preparedStatement.executeQuery();
		int tot=0;
		while (rs.next()) {
			Client client = new Client();
			client.setId(rs.getInt(1));
			tot=tot+client.getId();
			client.setTown(rs.getString(2));
			client.setState(rs.getString(3));
			client.setTotal(tot);
		
			lis.add(client);
		}

	} catch (Exception e) {
		e.printStackTrace();
	}
	return lis;
}

public int getNewRegistration(String fromdate, String todate) {
	int  res= 0;
	fromdate=fromdate+" 00:00:00";
	todate=todate+" 23:59:59";
	try {
		
		String sql = "select count(*) from apm_patient where regdate between '"
				+ fromdate + "' and '" + todate + "' ";
		PreparedStatement ps = connection.prepareStatement(sql);
		ResultSet rs = ps.executeQuery();
		while (rs.next()) {
			res=rs.getInt(1);
		}
	}catch (Exception e) {
		// TODO: handle exception
	}
	return res;
}

public int getNewRegistrationGenderWise(String fromdate, String todate, String gender) {
	int  res= 0;
	fromdate=fromdate+" 00:00:00";
	todate=todate+" 23:59:59";
	try {
		
		String sql = "select count(*) from apm_patient where regdate between '"
				+ fromdate + "' and '" + todate + "' and gender='"+gender+"' ";
		PreparedStatement ps = connection.prepareStatement(sql);
		ResultSet rs = ps.executeQuery();
		while (rs.next()) {
			res=rs.getInt(1);
		}
	}catch (Exception e) {
		e.printStackTrace();
	}
	return res;

}

public double getDOBWisePatientReg(String fromdate, String todate, String currentdate, String upto20year) {

	int  res= 0;
	fromdate=fromdate+" 00:00:00";
	todate=todate+" 23:59:59";
	try {
		
		String sql = "select count(*) from apm_patient where regdate between '"
				+ fromdate + "' and '" + todate + "' and dob between '"+currentdate+"' and '"+upto20year+"' ";
		PreparedStatement ps = connection.prepareStatement(sql);
		ResultSet rs = ps.executeQuery();
		while (rs.next()) {
			res=rs.getInt(1);
		}
	}catch (Exception e) {
		e.printStackTrace();
	}
	return res;


}

public int saveMainDashboardData(String yesterday, int yesterday_admission, int yesterday_discharge, int total_inhouse,
		int total_vacant_bed, int totalopdseen, int totalopdcompleted, int totalopddna, double totalmale,
		double totalfemale, double upto20, double upto40, double upto60, double above60) {
	int result=0;
	try {
		String currentdate = "";
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Calendar cal = Calendar.getInstance();
		currentdate = dateFormat.format(cal.getTime());
		
		String sql = "insert into maindashboard_data (date,datetime,ipd_admission,ipd_discharge,ipd_inhouse,ipd_vacantbed,"
				+ "opd_booked,opd_completed,opd_dna,total_male,total_female,upto20,"
				+ "upto40,upto60,above60) values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		PreparedStatement ps = connection.prepareStatement(sql);
		ps.setString(1, yesterday);
		ps.setString(2, currentdate);
		ps.setString(3, ""+yesterday_admission);
		ps.setString(4, ""+yesterday_discharge);
		ps.setString(5, ""+total_inhouse);
		ps.setString(6, ""+total_vacant_bed);
		ps.setString(7, ""+totalopdseen);
		ps.setString(8, ""+totalopdcompleted);
		ps.setString(9, ""+totalopddna);
		ps.setString(10, ""+totalmale);
		ps.setString(11, ""+totalfemale);
		ps.setString(12, ""+upto20);
		ps.setString(13, ""+upto40);
		ps.setString(14, ""+upto60);
		ps.setString(15, ""+above60);
		result = ps.executeUpdate();
	} catch (Exception e) {
		e.printStackTrace();
	}
	return result;
}

public boolean checkMaindashboardDataSaved(String newdate) {
	boolean flag = false;
	try {
		String sql ="select * from maindashboard_data where date='"+newdate+"'";
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

public MisReport getMainDashboardData(String newdate1) {
	MisReport misReport = new MisReport();
	try {
		String sql ="select ipd_admission,ipd_discharge,ipd_inhouse,ipd_vacantbed,"
				+ "opd_booked,opd_completed,opd_dna,total_male,total_female,upto20,"
				+ "upto40,upto60,above60 from maindashboard_data where date='"+newdate1+"' ";
		PreparedStatement preparedStatement = connection.prepareStatement(sql);
		ResultSet rs = preparedStatement.executeQuery();
		while (rs.next()) {
			misReport.setIpd_admission(rs.getString(1));
			misReport.setIpd_discharge(rs.getString(2));
			misReport.setIpd_inhouse(rs.getString(3));
			misReport.setIpd_vacantbed(rs.getString(4));
			misReport.setOpd_booked(rs.getString(5));
			misReport.setOpd_completed(rs.getString(6));
			misReport.setOpd_dna(rs.getString(7));
			misReport.setTotal_male(rs.getString(8));
			misReport.setTotal_female(rs.getString(9));
			misReport.setUpto20(rs.getString(10));
			misReport.setUpto40(rs.getString(11));
			misReport.setUpto60(rs.getString(12));
			misReport.setAbove60(rs.getString(13));
		}
	} catch (Exception e) {
		e.printStackTrace();
	}
	return misReport;
}

public int getDischargePatientsWardWise(String fromdate, String todate, String wardid) {

	PreparedStatement preparedStatement = null;
	int result = 0;

	todate = todate + " 23:59:59";

	StringBuffer sql = new StringBuffer();
	sql.append("SELECT count(*) FROM ipd_addmission_form_new inner join apm_treatment_episode ");
	sql.append("on apm_treatment_episode.id = ipd_addmission_form_new.treatmentepisodeid ");
	sql.append("where discharge_end_date between '" + fromdate + "'  and '" + todate + "' ");
	//sql.append("and dschargestatus !=0 ");
	sql.append(" and treatmentstatus=1 and ipd_addmission_form_new.wardid in ("+wardid+") ");
	try {
		preparedStatement = connection.prepareStatement(sql.toString());
		ResultSet rs = preparedStatement.executeQuery();
		if (rs.next()) {
			result = rs.getInt(1);
		}

	} catch (Exception e) {
		e.printStackTrace();
	}
	return result;

}

public int countOfReDosOfLabTests(String fromdate, String todate) {
	int count=0;
	try {
		fromdate=DateTimeUtils.getCommencingDate1(fromdate)+" 00:00:00";
		todate=DateTimeUtils.getCommencingDate1(todate)+" 23:59:00";
		StringBuffer sql = new StringBuffer();
		sql.append("  select apm_investigation_type.name,ipdid,count(*) from apm_client_parent_investigation  ");
		sql.append(" inner join apm_investigation_type on apm_investigation_type.id=apm_client_parent_investigation.invsttypeid ");
		sql.append(" where lastmodified between '"+fromdate+"' and '"+todate+"' ");
		sql.append(" group by ipdid,invsttypeid having count(*)>1 order by ipdid ");
		sql.append("");
		
		PreparedStatement ps= connection.prepareStatement(sql.toString());
		ResultSet rs=ps.executeQuery();
		rs.last();
		count=rs.getRow();
	} catch (Exception e) {
		e.printStackTrace();
	}
	return count;
}

public int countOfOfLabTests(String fromdate, String todate) {
	int count=0;
	try {
		fromdate=DateTimeUtils.getCommencingDate1(fromdate)+" 00:00:00";
		todate=DateTimeUtils.getCommencingDate1(todate)+" 23:59:00";
		StringBuffer sql = new StringBuffer();
		/*sql.append("  select apm_investigation_type.name,ipdid,count(*) from apm_client_parent_investigation  ");
		sql.append(" inner join apm_investigation_type on apm_investigation_type.id=apm_client_parent_investigation.invsttypeid ");
		sql.append(" where lastmodified between '"+fromdate+"' and '"+todate+"' ");
		sql.append(" group by ipdid,invsttypeid having count(*)>1 order by ipdid ");
		sql.append("");*/
		sql.append("select count(*) from apm_client_parent_investigation where lastmodified between '"+fromdate+"' and '"+todate+"' ");
		PreparedStatement ps= connection.prepareStatement(sql.toString());
		ResultSet rs=ps.executeQuery();
		while (rs.next()) {
			count=rs.getInt(1);
		}
	} catch (Exception e) {
		e.printStackTrace();
	}
	return count;
}

public String getFunctionNameOfIndicator(String kpiId) {
	String str="";
	try {
		String sql="select function_name from kpi_master inner join kpi_indicator on kpi_indicator.id=kpi_master.indicatorid where kpi_master.id='"+kpiId+"'";
		PreparedStatement ps= connection.prepareStatement(sql.toString());
		ResultSet rs=ps.executeQuery();
		while (rs.next()) {
			str=rs.getString(1);
		}
	} catch (Exception e) {
		e.printStackTrace();
	}
	return str;
}

public int getTotalRescheduledOTs(String fromDate, String toDate){
	int r=0;
	try {
		fromDate=DateTimeUtils.getCommencingDate1(fromDate);
		toDate=DateTimeUtils.getCommencingDate1(toDate);
		PreparedStatement ps= connection.prepareStatement("select count(DIStinct apmtid) from redscheduled_apmts_log inner join apm_available_slot on apm_available_slot.id=apmtId where category!='0' and date_time between '"+fromDate+"' and '"+toDate+"'");
		ResultSet rs=ps.executeQuery();
		while (rs.next()) {
			r=rs.getInt(1);
		}
		
	} catch (Exception e) {
		e.printStackTrace();
	}
	return r;
}
	

public  int  getTotalOTs(String fromDate, String toDate){
	int r=0;
	try {
		fromDate=DateTimeUtils.getCommencingDate1(fromDate);
		toDate=DateTimeUtils.getCommencingDate1(toDate);
		PreparedStatement ps= connection.prepareStatement("select count(*) from apm_available_slot"
				+ "  where category!='0' and commencing between '"+fromDate+"' and '"+toDate+"'");
		ResultSet rs=ps.executeQuery();
		while (rs.next()) {
			r=rs.getInt(1);
		}
		
	} catch (Exception e) {
		e.printStackTrace();
	}
	return r;
}

public double getRescheduledOTPercentage(String fromdate, String todate) {
	double i=0;
	int rescheduledOts=getTotalRescheduledOTs(fromdate, todate);
	int totalOts=getTotalOTs(fromdate, todate);
	double percentageOfReDos=((double)rescheduledOts/(double)totalOts)*100.0;
	i=Math.round((percentageOfReDos*100.0))/100.0;
	return i;
}

public int getICUInpatientCount(String fromdate, String todate) {
	int finalCount=0;
	try {
		String sql="select commencing,count(distinct ipdid) from apm_invoice_assesments "
				+ "where wardid in (select id from apm_ipd_ward where wardname like '%ICU%') "
				+ "and commencing between '"+fromdate+"' and '"+todate+"' and "
						+ "commencing not like '%at%' group by commencing";
				
		PreparedStatement ps=connection.prepareStatement(sql);
		ResultSet rs=ps.executeQuery();
		int patientCount=0;
		int dateCount=0;
		while (rs.next()) {
			patientCount= patientCount+rs.getInt(2);
			dateCount++;
		}
		finalCount=patientCount/dateCount;
	} catch (Exception e) {
		e.printStackTrace();
	}
	return finalCount;
}


public int getICUBedCount(){
	int finalCount=0;
	try {
		String sql="select count(*) from apm_ipd_bed "
				+ "where wardid in(select id from apm_ipd_ward where wardname like '%ICU%') and active='1' ";
				
				
		PreparedStatement ps=connection.prepareStatement(sql);
		ResultSet rs=ps.executeQuery();
		
		while (rs.next()) {
			finalCount=rs.getInt(1);
		}
		
	} catch (Exception e) {
		e.printStackTrace();
	}
	return finalCount;
}

public int getICUUtilizationRate(String fromdate, String todate) {
	try {
		int icuInpatientCount=getICUInpatientCount(fromdate, todate);
		int icuBedCount=getICUBedCount();
		int utilizationRate=(icuInpatientCount/icuBedCount)*100;
		return utilizationRate;
	} catch (Exception e) {
		e.printStackTrace();
	}
	return 0;
}

public int getIPDInpatientCount(int wardid, String fromdate, String todate) {
	int count=0;
	try {
		String sql="select commencing,count(distinct ipdid) from apm_invoice_assesments "
				+ " where commencing between '"+fromdate+"' and '"+todate+"' and commencing not like '%at%' ";
				if(wardid>0){
					sql=sql+" and wardid ='"+wardid+"'  ";
				}	
				sql=sql+ " group by commencing";
		PreparedStatement ps=connection.prepareStatement(sql);
		ResultSet rs=ps.executeQuery();
		int patientCount=0;
		int dateCount=0;
		while (rs.next()) {
			patientCount= patientCount+rs.getInt(2);
			dateCount++;
		}
		count=patientCount/dateCount;
	} catch (Exception e) {
		e.printStackTrace();
	}
	return count;
}

public int getBedCount(int wardid) {
	int count=0;
	try {
		String sql="select count(*) from apm_ipd_bed "
				+ "where active='1'  ";
		if(wardid>0){
			sql=sql+" and wardid ='"+wardid+"'  ";
		}
		PreparedStatement ps=connection.prepareStatement(sql);
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
public MisReport getPhysioOPDAppointmentCountforpreview(String fromDate, String toDate, String apptypeid,
		String diaryuser) {
	MisReport misReport = new MisReport();
	 boolean flag = false;
	 try {
		 /*String sql ="SELECT count(*),apmttypetext FROM apm_available_slot where aptmtype ='"+apptypeid+"' and commencing  between '"+fromDate+"' and '"+toDate+"' and status=0 and procedures='0' and diaryuserid='"+diaryuser+"' group by diaryuserid ";*/
		 StringBuffer buffer = new StringBuffer();
		 buffer.append("SELECT count(*),apmttypetext FROM apm_available_slot inner join apm_discipline on apm_discipline.id=apm_available_slot.dept where aptmtype ='"+apptypeid+"' and commencing  between '"+fromDate+"' and '"+toDate+"' and status=0 and procedures='0' and showdeptlist='1' ");
		 if(diaryuser!=null){
			 if(!diaryuser.equals("0")){
			 buffer.append("and diaryuserid='"+diaryuser+"' ");
			 buffer.append("group by diaryuserid ");
			 flag = true;
			 }
		 }
		 if(!flag){
		 buffer.append("group by apmttypetext ");
		 }
		 PreparedStatement preparedStatement = connection.prepareStatement(buffer.toString());
		 ResultSet rs = preparedStatement.executeQuery();
		 while (rs.next()) {
			 misReport.setResult(rs.getString(1));
			 misReport.setName(rs.getString(2));
		 }
	 } catch (Exception e) {
	  e.printStackTrace();
	 }
	 return misReport;
}

@Override
public int getUpipayment(String fromdate, String todate, String invcategory, String jobtitle) {
	PreparedStatement preparedStatement = null;
	int result = 0;

	todate = todate + " 23:59:59";

	/*String sql = "select sum(payment) from apm_charges_payment where date between '"
			+ fromdate + "' and '" + todate + "' and paymode='Cash'  ";*/
	StringBuffer sql = new StringBuffer();
	sql.append("select sum(payment) from apm_charges_payment inner join apm_charges_invoice on ");
	sql.append("apm_charges_invoice.id = apm_charges_payment.chargeinvoiceid ");
	sql.append("where apm_charges_payment.date between '"+fromdate+"' and '"+todate+"' and paymode='Upi'  ");
	
	if(jobtitle.equals(Constants.SUPER_ADMIN)){
		invcategory = "1";
	}
	
	if(!invcategory.equals("2")){
		
		sql.append("and apm_charges_invoice.invpstype = "+invcategory+" ");
	}

	try {
		preparedStatement = connection.prepareStatement(sql.toString());
		ResultSet rs = preparedStatement.executeQuery();
		if (rs.next()) {
			result = rs.getInt(1);
		}

	} catch (Exception e) {
		e.printStackTrace();
	}
	return result;
}

@Override
public ArrayList<Bed> getWardWiseData(String fromdate, String todate) {
	ArrayList<Bed> beds = new ArrayList<Bed>();
	
	try {
		
		StringBuffer buffer = new StringBuffer();
		buffer.append("select apm_ipd_ward.id,wardname,increment,procedures  from apm_ipd_ward ");
		buffer.append("inner join apm_ipd_bed on apm_ipd_bed.wardid = apm_ipd_ward.id ");
		buffer.append("group by apm_ipd_bed.wardid order by apm_ipd_ward.id desc ");
		PreparedStatement ps = connection
				.prepareStatement(buffer.toString());
		ResultSet rs = ps.executeQuery();

		while (rs.next()) {
			Bed bed = new Bed();
			int id = rs.getInt(1);
			
			int Admissioncount = getWardwiseAdmissionCount(rs.getInt(1), fromdate, todate);
			int dischargecount=getWardwiseDischargecount(rs.getInt(1),fromdate,todate);
			int inhousecount=getWardeiseInhousecount(rs.getInt(1),fromdate,todate);
			
			int occupiedbed=getWardWiseOccupiedBed(rs.getInt(1),fromdate,todate);
			Bed bed1 =getWardWiseAvailableBed(fromdate, todate,rs.getInt(1));
			int wardwiseadvance=getWardWiseAdvance(fromdate, todate,rs.getInt(1));
			int wardwiserefund=getWardWiseRefund(fromdate, todate,rs.getInt(1));
			//availablebed=availablebed-occupiedbed;
			bed.setWardwiseadmissioncount(Admissioncount);
			bed.setWardwisedischargecount(dischargecount);
			bed.setWardwiseinhousecount(inhousecount);
			bed.setWardwiseoccupied(bed1.getWardwiseoccupied());
			bed.setWardwiseavailablebed(bed1.getWardwiseavailablebed());
			bed.setAdvance(wardwiseadvance);
			bed.setRefund(wardwiserefund);
			
			bed.setWardname(rs.getString(2));
			
			bed.setId(id);
			//bed.setName(wardname);
			beds.add(bed);
		}

	} catch (Exception e) {

		e.printStackTrace();
	}

	return beds;
	
}

private int getWardWiseRefund(String fromdate, String todate, int wardid) {
	int result = 0;
	todate = todate + " 23:59:59";
	try {
		String sql = "select debit from apm_credit_account where date between '"
				+ fromdate + "' and '" + todate + "' and wardid='"+wardid+"' and advref=1 ";
		PreparedStatement ps = connection.prepareStatement(sql);

		ResultSet rs = ps.executeQuery();
		while (rs.next()) {
			result = result+rs.getInt(1);
		}

	} catch (Exception e) {
		e.printStackTrace();
	}
	return result;
	
}

private int getWardWiseAdvance(String fromdate, String todate, int wardid) {
	int result = 0;
	todate = todate + " 23:59:59";
	try {
		String sql = "select charge from apm_credit_account where date between '"+ fromdate + "' and '" + todate + "' and wardid='"+wardid+"' ";
		PreparedStatement ps = connection.prepareStatement(sql);

		ResultSet rs = ps.executeQuery();
		while (rs.next()) {
			result = result+rs.getInt(1);
		}

	} catch (Exception e) {
		e.printStackTrace();
	}
	return result;
	
}

private Bed getWardWiseAvailableBed(String fromdate, String todate,int wardid) {
	PreparedStatement preparedStatement = null;
	int result = 0;
    Bed bed=new Bed();
	todate = todate + " 23:59:59";

	String sql = "SELECT ipd_inhouse,ipd_vacantbed FROM ward_wise_bed_data where wardid='"+wardid+"' and datetime between '"+ fromdate +"'  and '" + todate + "'";

	try {
		preparedStatement = connection.prepareStatement(sql);
		ResultSet rs = preparedStatement.executeQuery();
		if (rs.next()) {
			bed.setWardwiseoccupied(rs.getInt(1));
			bed.setWardwiseavailablebed(rs.getInt(2));
			
		}

	} catch (Exception e) {
		e.printStackTrace();
	}
	return bed;
	
}

private int getWardWiseOccupiedBed(int wardid, String fromdate, String todate) {
	PreparedStatement preparedStatement = null;
	int result = 0;

	todate = todate + " 23:59:59";

	String sql = "SELECT count(*) FROM ipd_addmission_form where admissiondsate  between '"+ fromdate +"'  and '" + todate + "' and bedid!=0 and wardid='"+wardid+"'";

	try {
		preparedStatement = connection.prepareStatement(sql);
		ResultSet rs = preparedStatement.executeQuery();
		if (rs.next()) {
			result = rs.getInt(1);
		}

	} catch (Exception e) {
		e.printStackTrace();
	}
	return result;
	
}

private int getWardeiseInhousecount(int wardid, String fromdate, String todate) {
	PreparedStatement preparedStatement = null;
	int result = 0;

	todate = todate + " 23:59:59";


	/*String sql = "SELECT count(*) FROM ipd_addmission_form where admissiondsate  between '"
			+ fromdate + "'  and '" + todate + "' and bedid!=0 ";*/

	String sql = "SELECT count(*) FROM ipd_addmission_form where bedid!=0 and wardid='"+wardid+"' ";
	try {
		preparedStatement = connection.prepareStatement(sql);
		ResultSet rs = preparedStatement.executeQuery();
		if (rs.next()) {
			result = rs.getInt(1);
		}

	} catch (Exception e) {
		e.printStackTrace();
	}
	return result;
}

private int getWardwiseDischargecount(int wardid, String fromdate, String todate) {
	PreparedStatement preparedStatement = null;
	int result = 0;

	todate = todate + " 23:59:59";

	StringBuffer sql = new StringBuffer();
	sql.append("SELECT count(*) FROM ipd_addmission_form inner join apm_treatment_episode ");
	sql.append("on apm_treatment_episode.id = ipd_addmission_form.treatmentepisodeid ");
	sql.append("where dischargedate between '" + fromdate + "'  and '" + todate + "' and wardid ='"+wardid+"' ");
	//sql.append("and dschargestatus !=0 ");
	sql.append(" and treatmentstatus=1 ");
	try {
		preparedStatement = connection.prepareStatement(sql.toString());
		ResultSet rs = preparedStatement.executeQuery();
		if (rs.next()) {
			result = rs.getInt(1);
		}

	} catch (Exception e) {
		e.printStackTrace();
	}
	return result;
	
}

private int getWardwiseAdmissionCount(int wardid, String fromdate, String todate) {
	PreparedStatement preparedStatement = null;
	int result = 0;

	todate = todate + " 23:59:59";

	String sql = "SELECT count(*) FROM ipd_addmission_form where admissiondsate  between '"
			+ fromdate + "'  and '" + todate + "' and wardid='"+wardid+"' and casualty='0' and cancel='0' ";
	

	try {
		preparedStatement = connection.prepareStatement(sql);
		ResultSet rs = preparedStatement.executeQuery();
		if (rs.next()) {
			result = rs.getInt(1);
		}

	} catch (Exception e) {
		e.printStackTrace();
	}
	return result;
	
}

@Override
public int saveWardwiseMainDashboardData(String yesterday, int yesterday_admission, int total_inhouse1,
		int total_vacant_bed1,int wardid) {
	int result=0;
	try {
		String currentdate = "";
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Calendar cal = Calendar.getInstance();
		currentdate = dateFormat.format(cal.getTime());
		
		String sql = "insert into ward_wise_bed_data (date,datetime,ipd_inhouse,ipd_vacantbed,wardid) values (?,?,?,?,?)";
		PreparedStatement ps = connection.prepareStatement(sql);
		ps.setString(1, yesterday);
		ps.setString(2, currentdate);
		ps.setString(3, ""+total_inhouse1);
		ps.setString(4, ""+total_vacant_bed1);
		ps.setString(5, ""+wardid);
		result = ps.executeUpdate();
	} catch (Exception e) {
		e.printStackTrace();
	}
	return result;
	
}

@Override
public int getInHousePatients1(String todate, String todate2, int wardid) {
	PreparedStatement preparedStatement = null;
	int result = 0;

	todate = todate + " 23:59:59";


	/*String sql = "SELECT count(*) FROM ipd_addmission_form where admissiondsate  between '"
			+ fromdate + "'  and '" + todate + "' and bedid!=0 ";*/

	String sql = "SELECT count(*) FROM ipd_addmission_form where bedid!=0 and wardid='"+wardid+"' ";
	try {
		preparedStatement = connection.prepareStatement(sql);
		ResultSet rs = preparedStatement.executeQuery();
		if (rs.next()) {
			result = rs.getInt(1);
		}

	} catch (Exception e) {
		e.printStackTrace();
	}
	return result;
	
}

@Override
public int getIpdNewAdmission1(String todate, String todate2, int wardid) {
	PreparedStatement preparedStatement = null;
	int result = 0;

	todate = todate + " 23:59:59";

	String sql = "SELECT count(*) FROM ipd_addmission_form where admissiondsate  between '"
			+ todate + "'  and '" + todate2 + "' and  casualty='0' and cancel='0' and wardid='"+wardid+"'";

	try {
		preparedStatement = connection.prepareStatement(sql);
		ResultSet rs = preparedStatement.executeQuery();
		if (rs.next()) {
			result = rs.getInt(1);
		}

	} catch (Exception e) {
		e.printStackTrace();
	}
	return result;
	
}

@Override
public int getDischargePatients1(String fromdate, String todate, int wardid) {
	PreparedStatement preparedStatement = null;
	int result = 0;

	todate = todate + " 23:59:59";

	StringBuffer sql = new StringBuffer();
	sql.append("SELECT count(*) FROM ipd_addmission_form inner join apm_treatment_episode ");
	sql.append("on apm_treatment_episode.id = ipd_addmission_form.treatmentepisodeid ");
	sql.append("where discharge_end_date between '" + fromdate + "'  and '" + todate + "' and wardid='"+wardid+"'");
	//sql.append("and dschargestatus !=0 ");
	sql.append(" and treatmentstatus=1 ");
	try {
		preparedStatement = connection.prepareStatement(sql.toString());
		ResultSet rs = preparedStatement.executeQuery();
		if (rs.next()) {
			result = rs.getInt(1);
		}

	} catch (Exception e) {
		e.printStackTrace();
	}
	return result;
}

@Override
public int getAvailableBed1(String fromdate, String todate, int wardid) {
	PreparedStatement preparedStatement = null;
	int result = 0;

	todate = todate + " 23:59:59";

	String sql = "SELECT count(*) FROM apm_ipd_bed where active = 1 and casualty=0 and wardid='"+wardid+"'";

	try {
		preparedStatement = connection.prepareStatement(sql);
		ResultSet rs = preparedStatement.executeQuery();
		if (rs.next()) {
			result = rs.getInt(1);
		}

	} catch (Exception e) {
		e.printStackTrace();
	}
	return result;
	
}

@Override
public int getDocwiseTotalOpdPatient(String fromdate, String todate, int id) {
	PreparedStatement preparedStatement = null;
	int result = 0;
	String sql = "select count(*) from apm_available_slot where commencing between  '"
			+ fromdate
			+ "' and '"
			+ todate
			+ "' and status = 0 and otid=0 and dna=0 and diaryuserid='"+id+"' ";

	try {
		preparedStatement = connection.prepareStatement(sql);
		ResultSet rs = preparedStatement.executeQuery();
		while (rs.next()) {
			result=rs.getInt(1);
		}

	} catch (Exception e) {
		e.printStackTrace();
	}
	return result;
	
}

@Override
public int getDocwiseBookedAppointment(String fromdate, String todate,int id) {
	PreparedStatement preparedStatement = null;
	int result = 0;
	String sql = "select count(*) from apm_available_slot where commencing between  '"
			+ fromdate + "' and '" + todate + "' and status=0 and procedures='0' and diaryuserid='"+id+"' ";

	try {
		preparedStatement = connection.prepareStatement(sql);
		ResultSet rs = preparedStatement.executeQuery();
		if (rs.next()) {
			result = rs.getInt(1);
		}

	} catch (Exception e) {
		e.printStackTrace();
	}
	return result;
	
}

@Override
public int getDocwiseOPDCompletedAppointment(String fromdate, String todate,int id) {
	PreparedStatement preparedStatement = null;
	int result = 0;
	StringBuffer buffer = new StringBuffer();
	buffer.append("select count(apm_available_slot.id) from apm_available_slot ");
	buffer.append("inner join apm_invoice_assesments on apm_available_slot.id = apm_invoice_assesments.appointmentid ");
	buffer.append("where apm_available_slot.commencing between '"+fromdate+"' and '"+todate+"' and procedures='0'  and dna='0' and diaryuserid='"+id+"' ");
	buffer.append("group by apm_invoice_assesments.appointmentid ");
	try {
		preparedStatement = connection.prepareStatement(buffer.toString());
		ResultSet rs = preparedStatement.executeQuery();
		while (rs.next()) {
			result = result+1;
			
		}

	} catch (Exception e) {
		e.printStackTrace();
	}
	return result;
	
}

@Override
public int getDocwiseOPDdnaAppointment(String fromdate, String todate,int id) {
	PreparedStatement preparedStatement = null;
	int result = 0;
	StringBuffer buffer = new StringBuffer();
	buffer.append("select count(apm_available_slot.id) from apm_available_slot ");
	buffer.append("inner join apm_invoice_assesments on apm_available_slot.id = apm_invoice_assesments.appointmentid ");
	buffer.append("where apm_available_slot.commencing between '"+fromdate+"' and '"+todate+"' and procedures='0'  and dna!='0' and diaryuserid='"+id+"' ");
	buffer.append("group by apm_invoice_assesments.appointmentid ");
	try {
		preparedStatement = connection.prepareStatement(buffer.toString());
		ResultSet rs = preparedStatement.executeQuery();
		while (rs.next()) {
			result = result+1;
		}

	} catch (Exception e) {
		e.printStackTrace();
	}
	return result;
	
}

@Override
public int getDocwiseOPDcancelcountAppointment(String fromdate, String todate,int id) {
	int  res= 0;
	try {
		
		String sql = "select count(*) from apm_appointment_log where commencing between '"
				+ fromdate + "' and '" + todate + "' and status='Cancelled' and diaryuserid='"+id+"' ";
		PreparedStatement ps = connection.prepareStatement(sql);
		ResultSet rs = ps.executeQuery();
		while (rs.next()) {
			res=rs.getInt(1);
		}
	}catch (Exception e) {
		// TODO: handle exception
	}
	return res;
	
}

@Override
public int getDocwiseOtPatientsCounts(String fromdate, String todate, int id) {
	int result = 0;
	try {
		/*String sql = "select id from apm_ot_parent where commencing between '"
				+ fromdate + "' and '" + todate + "' group by apmtid";*/
		
		String sql ="SELECT count(*) FROM apm_available_slot where procedures!='0' and commencing between '"+fromdate+"' and '"+todate+"' and diaryuserid='"+id+"' ";
		PreparedStatement ps = connection.prepareStatement(sql);

		ResultSet rs = ps.executeQuery();
		while (rs.next()) {
			result = rs.getInt(1);
		}
	} catch (Exception e) {
		e.printStackTrace();
	}
	return result;
	
}

@Override
public int getTotalRevnue(String fromdate, String todate, int id) {
	int  res= 0;
	try {
		
		String sql = "select sum(debit) from apm_charges_invoice where date between '"
				+ fromdate + "' and '" + todate + "' and practid='"+id+"' and itype=1 ";
		PreparedStatement ps = connection.prepareStatement(sql);
		ResultSet rs = ps.executeQuery();
		while (rs.next()) {
			res=rs.getInt(1);
		}
	}catch (Exception e) {
		// TODO: handle exception
	}
	return res;
  }

@Override
public int getInvestigationRevenue(String fromdate, String todate) {
	int  res= 0;
	try {
		
		String sql = "select sum(payment) from apm_charges_payment inner join apm_charges_invoice on apm_charges_invoice.id = apm_charges_payment.chargeinvoiceid where apm_charges_payment.date between '"
				+ fromdate + "' and '" + todate + " 59:59:59' and itype=3 ";
		PreparedStatement ps = connection.prepareStatement(sql);
		ResultSet rs = ps.executeQuery();
		while (rs.next()) {
			res=rs.getInt(1);
		}
	}catch (Exception e) {
		// TODO: handle exception
	}
	return res;
}

}
