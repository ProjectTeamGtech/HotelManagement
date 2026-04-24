package com.apm.Medical.Reports.eu.blogic.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import com.apm.Medical.Reports.eu.bi.MedicalReportsDAO;
import com.apm.Medical.Reports.eu.entity.MedicalClients;
import com.apm.Medical.Reports.eu.entity.MedicalReports;
import com.apm.common.eu.blogic.jdbc.JDBCBaseDAO;
import com.apm.common.utils.DateTimeUtils;
import com.apm.main.common.constants.Constants;

public class JdbcMedicalReportsDAO extends JDBCBaseDAO implements MedicalReportsDAO{
	
	public JdbcMedicalReportsDAO(Connection connection){
		this.connection = connection;
		
	}

	public ArrayList<MedicalReports> getPendingPaymentList(String payby,String location, String diaryUser, String fromDate, String toDate,String order,String orderField,String thirdparty) {
		PreparedStatement preparedStatement = null;
		ArrayList<MedicalReports>list = new ArrayList<MedicalReports>();
		//String sql = "SELECT id,payby,clientid,tpid,debit,date FROM apm_charges_invoice";
		
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT apm_charges_invoice.id,payby,apm_charges_invoice.clientid,tpid,apm_charges_invoice.debit,apm_charges_invoice.date,concat(apm_patient.title,' ',firstname,' ',surname),apm_charges_invoice.discount,company_name FROM apm_charges_invoice inner join ");
		sql.append("apm_charges_assesment on apm_charges_assesment.chargeinvoiceid = apm_charges_invoice.id ");
		sql.append("inner join apm_invoice on apm_charges_assesment.invoiceid = apm_invoice.id ");
		sql.append("inner join apm_patient on apm_patient.id = apm_charges_invoice.clientid ");
		sql.append("inner join apm_third_party_details on apm_third_party_details.id = apm_patient.third_party_name_id ");
		
		if(payby!=null){
			
			if(!payby.equals("0") && !location.equals("0") && !diaryUser.equals("0") && !thirdparty.equals("0")){
				sql.append("where practitionerid = "+diaryUser+" and apm_charges_invoice.location="+location+" and payby='"+payby+"' and third_party_name_id="+thirdparty+" ");
			}else if(!diaryUser.equals("0") && !location.equals("0") && !thirdparty.equals("0")){
				sql.append("where practitionerid = "+diaryUser+" and apm_charges_invoice.location="+location+" and third_party_name_id="+thirdparty+" ");
			}else if(!diaryUser.equals("0") && !payby.equals("0") && !thirdparty.equals("0")){
				sql.append("where practitionerid = "+diaryUser+"  and payby='"+payby+"' and third_party_name_id="+thirdparty+" ");
			}else if(!location.equals("0") && !payby.equals("0") && !thirdparty.equals("0")){
				sql.append("where apm_charges_invoice.location="+location+" and payby='"+payby+"' and third_party_name_id="+thirdparty+" ");
			}
			
			else if(!thirdparty.equals("0") && !location.equals("0")){
				sql.append("where third_party_name_id="+thirdparty+" and apm_charges_invoice.location="+location+" ");
			}else if(!thirdparty.equals("0") && !payby.equals("0")){
				sql.append("where third_party_name_id="+thirdparty+"  and payby='"+payby+"' ");
			}else if(!thirdparty.equals("0") && !diaryUser.equals("0")){
				sql.append("where third_party_name_id="+thirdparty+"  practitionerid = "+diaryUser+" ");
			}
			
			else if(!payby.equals("0") && !location.equals("0") && !diaryUser.equals("0")){
				sql.append("where practitionerid = "+diaryUser+" and apm_charges_invoice.location="+location+" and payby='"+payby+"' ");
			}else if(!diaryUser.equals("0") && !location.equals("0")){
				sql.append("where practitionerid = "+diaryUser+" and apm_charges_invoice.location="+location+" ");
			}else if(!diaryUser.equals("0") && !payby.equals("0")){
				sql.append("where practitionerid = "+diaryUser+"  and payby='"+payby+"' ");
			}else if(!location.equals("0") && !payby.equals("0")){
				sql.append("where apm_charges_invoice.location="+location+" and payby='"+payby+"' ");
			}else if(!diaryUser.equals("0")){
				sql.append("where practitionerid = "+diaryUser+" ");
			}else if(!payby.equals("0")){
				sql.append("where payby='"+payby+"' ");
			}else if(!location.equals("0")){
				sql.append("where  apm_charges_invoice.location="+location+"  ");
			}else if(!thirdparty.equals("0")){
				sql.append("where  third_party_name_id="+thirdparty+"  ");
			}
			
			
			if(payby.equals("0") && location.equals("0") && diaryUser.equals("0") && thirdparty.equals("0")){
				
				if(!fromDate.equals("") && !toDate.equals("")){
					fromDate = DateTimeUtils.getCommencingDate(fromDate);
					toDate = DateTimeUtils.getCommencingDate(toDate);
					sql.append("and  apm_charges_invoice.date between '"+fromDate+"' and '"+toDate+"' ");
				}
			
			}else{
				
				if(!fromDate.equals("") && !toDate.equals("")){
					fromDate = DateTimeUtils.getCommencingDate(fromDate);
					toDate = DateTimeUtils.getCommencingDate(toDate);
					sql.append(" and apm_charges_invoice.date between '"+fromDate+"' and '"+toDate+"' ");
				}
			}
			
		}else{
			if(!fromDate.equals("") && !toDate.equals("")){
				fromDate = DateTimeUtils.getCommencingDate(fromDate);
				toDate = DateTimeUtils.getCommencingDate(toDate);
				sql.append(" and apm_charges_invoice.date between '"+fromDate+"' and '"+toDate+"' ");
			}
		}
		
		
		
			
		sql.append("group by apm_charges_invoice.id ");
		sql.append("order by  "+orderField+" "+order+" ");
		
		try{
			
			preparedStatement = connection.prepareStatement(sql.toString());
			ResultSet rs = preparedStatement.executeQuery();
			
			while(rs.next()){
				MedicalReports medicalReports = new MedicalReports();
				medicalReports.setInvoiceId(rs.getString(1));
				String payBy = rs.getString(2);
				medicalReports.setPayBy(payBy);
				
				int clientid = rs.getInt(3);
				int tpid = rs.getInt(4);
				
				medicalReports.setClientName(rs.getString(7));
			
				if(payBy.equals(Constants.PAY_BY_CLIENT)){
					MedicalClients medicalClients = getMedicalClients(clientid);
					medicalReports.setClientDetails(medicalClients);
					medicalReports.setId(medicalClients.getId());
					medicalReports.setName(medicalClients.getName());
					medicalReports.setAddress(medicalClients.getAddress());
					medicalReports.setContactNo(medicalClients.getContactNo());
					medicalReports.setEmailid(medicalClients.getEmailid());
					medicalReports.setPostcode(medicalClients.getPostcode());
					
				}else{
					MedicalClients medicalClients = getMedicalTPClients(tpid);
					medicalReports.setId(medicalClients.getId());
					medicalReports.setName(rs.getString(9));
					medicalReports.setAddress(medicalClients.getAddress());
					medicalReports.setContactNo(medicalClients.getContactNo());
					medicalReports.setEmailid(medicalClients.getEmailid());
					medicalReports.setPostcode(medicalClients.getPostcode());
				}
				
				double debit = rs.getDouble(5);
				double discount = rs.getDouble(8);
				double debitdic = (discount*debit)/100;
				debit = debit - debitdic;
				
				
				
				double creditAmount = getPaidAmount(rs.getInt(1));
				
				double balance = debit - creditAmount;
				
				
				medicalReports.setDebit(balance);
		
				double paid = getPaidAmount(rs.getInt(1));
				
				
				
				DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
				Calendar cal = Calendar.getInstance();
				//cal.add(Calendar.DATE, 30); 
				String checkDate = dateFormat.format(cal.getTime());
				
				
				String invoiceDate = rs.getString(6);
				medicalReports.setDate(DateTimeUtils.getCommencingDate1(invoiceDate));
				
				long diffdays = DateTimeUtils.getDiffofTwoDates(invoiceDate, checkDate);
			
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	        	Date date1 = sdf.parse(invoiceDate);
	        	Date date2 = sdf.parse(checkDate);
	        	
	        	if(diffdays<=30){
	        		medicalReports.setAmountBeforeThirty(medicalReports.getDebit());
	        		medicalReports.setAmountBeforeThirtyx(DateTimeUtils.changeFormat(medicalReports.getDebit()));
	        	}else if(diffdays>30 && diffdays<=60){
	        		medicalReports.setAmountAfterThirty(medicalReports.getDebit());
	        		medicalReports.setAmountAfterThirtyx(DateTimeUtils.changeFormat(medicalReports.getDebit()));
	        	}else if(diffdays>60 && diffdays<=100){
	        		medicalReports.setAmountAfterSixty(medicalReports.getDebit());
	        		medicalReports.setAmountAfterSixtyx(DateTimeUtils.changeFormat(medicalReports.getDebit()));
	        	}else{
	        		medicalReports.setAmountAfterHundred(medicalReports.getDebit());
	        		medicalReports.setAmountAfterHundredx(DateTimeUtils.changeFormat(medicalReports.getDebit()));
	        	}
	        	
	        /*	if(date1.compareTo(date2)<0){
	        		System.out.println("Date1 is before Date2");
	        		medicalReports.setAmountBeforeThirty(medicalReports.getDebit());
	        	}else if(date1.compareTo(date2)>0){
	        		System.out.println("Date1 is after Date2");
	        		
	        		cal.add(Calendar.DATE, 60); 
	        		checkDate = dateFormat.format(cal.getTime());
	        		date2 = sdf.parse(checkDate);
	        		
	        		//setting before 60 days
	        		if(date1.compareTo(date2)<0){
	        			medicalReports.setAmountAfterThirty(medicalReports.getDebit());
	        		}else if(date1.compareTo(date2)>0){
	        			
	        			cal.add(Calendar.DATE, 100); 
		        		checkDate = dateFormat.format(cal.getTime());
		        		date2 = sdf.parse(checkDate);
		        		
		        		//setting before 100 days
		        		if(date1.compareTo(date2)<0){
		        			medicalReports.setAmountAfterSixty(medicalReports.getDebit());
		        		}else{
		        			
		        			//setting after 100 days
		        			medicalReports.setAmountAfterHundred(medicalReports.getDebit());
		        		}
	        		}
	        		
	        	}*/
	        	
	        	if(balance!=0){
	        		list.add(medicalReports);
	        	}
	        	
				
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return list;
	}

	
	private double getPaidAmount(int id) {
		PreparedStatement preparedStatement = null;
		double result = 0;
		String sql = "SELECT sum(payment) FROM apm_charges_payment where chargeinvoiceid= "+id+" ";
		
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

	private MedicalClients getMedicalTPClients(int tpid) {
		PreparedStatement preparedStatement = null;
		MedicalClients medicalClients = new MedicalClients();
		String sql = "SELECT id,company_name,address,company_email,telephone,postcode FROM apm_third_party_details where id = "+tpid+" ";
				
		try{
			
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()){
				medicalClients.setId(rs.getInt(1));
				medicalClients.setName(rs.getString(2));
				medicalClients.setAddress(rs.getString(3));
				medicalClients.setEmailid(rs.getString(4));
				medicalClients.setContactNo(rs.getString(5));
				medicalClients.setPostcode(rs.getString(6));
				
				
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
		return medicalClients;
	}

	private MedicalClients getMedicalClients(int clientid) {
		
		PreparedStatement preparedStatement = null;
		MedicalClients medicalClients = new MedicalClients();
		String sql = "SELECT id,title,firstname,surname,mobno,email,address,postcode FROM apm_patient where id = "+clientid+" ";
		
		try{
			
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			if(rs.next()){
				medicalClients.setId(rs.getInt(1));
				String name = rs.getString(2) + " " + rs.getString(3) + " " + rs.getString(4);
				medicalClients.setName(name);
				medicalClients.setContactNo(rs.getString(5));
				medicalClients.setEmailid(rs.getString(6));
				medicalClients.setAddress(rs.getString(7));
				medicalClients.setPostcode(rs.getString(8));
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return medicalClients;
	}
	
	
	
	
	

}
